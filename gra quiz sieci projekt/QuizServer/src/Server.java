import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Server {
    static List<Player> players = Collections.synchronizedList(new ArrayList<>());
    static int MINIMUM_PLAYERS = 1;
    static int ROUNDS_DELAY = 3;
    static int QUESTIONS_COUNT = 6;

    public static void main(String[] args) throws InterruptedException {
        LobbyThread lobby = new LobbyThread();
        lobby.start();

        while (true) {
            if (!lobby.running) {
                return;
            }

            if (lobby.sockets.size() > 0) {
                Player host = lobby.sockets.get(0);
                if (!host.isHost) {
                    host.isHost = true;
                    host.sendMessage("NEW_HOST");
                }
                String response;
                try {
                    response = host.readMessage();
                } catch (IOException exception) {
                    lobby.sockets.remove(host);
                    // Host disconnected. Select new host or disable the game if the are no eligible players.
                    if (lobby.sockets.size() > 0) {
                        System.out.println("Finding new host...");
                        continue;
                    } else {
                        System.out.println("Host disconnected. Quitting the game.");
                        lobby.running = false;
                    }
                    return;
                }
                if (response.equals("START_GAME_HOST")) {
                    if (lobby.sockets.size() >= MINIMUM_PLAYERS) {
                        players = lobby.sockets;
                        lobby.running = false;
                        sendToPlayers("START_GAME_CLIENT");
                        break;
                    } else {
                        host.sendMessage("NOT_ENOUGH_PLAYERS");
                    }
                }
            }
        }

        QuestionsManager questionsManager = new QuestionsManager();

        for (Player player : players) {
            player.onDisconnected.addListener((p) -> {
                players.remove(player);
                if (players.size() == 0) {
                    System.out.println("All players have disconnected.");
                }
            });
            player.start();
        }

        int questionsCounter = 1;
        while (questionsCounter <= QUESTIONS_COUNT) {
            if (players.size() == 0) {
                System.out.println("No active players - stopping server...");
                return;
            }
            Question newQuestion = questionsManager.getRandomQuestion();
            sendToPlayers(questionsCounter + ": " + newQuestion.getContent());
            sendToPlayers(newQuestion.getAnswerA());
            sendToPlayers(newQuestion.getAnswerB());
            sendToPlayers(newQuestion.getAnswerC());
            sendToPlayers(newQuestion.getAnswerD());


            TimeOutThread timeOutThread = new TimeOutThread();
            timeOutThread.start();

            Player respondedPlayer = null;
            String answer = "";

            // Reset answers for all players before next rounds starts
            for (Player player : players) {
                player.answer = "";
            }

            timeout:
            while (!timeOutThread.timeOut) {
                for (Player player : players) {
                    if(!player.answer.isBlank() && !player.paused) {
                        respondedPlayer = player;
                        answer = player.answer;

                        break timeout;
                    }
                }
            }

            for (Player player : players) {
                player.paused = false;
            }

            if(respondedPlayer == null) {
                sendToPlayers("NO_ANSWERS");
            } else {
                boolean isCorrect = questionsManager.isCorrect(newQuestion, answer);
                if (isCorrect) {
                    sendToPlayers("GOOD_ANSWER");
                    System.out.println("GOOD ANSWER");
                    System.out.println("SENDING NAME: " + respondedPlayer.name);
                    sendToPlayers(respondedPlayer.name);
                    sendToPlayers(answer);
                    respondedPlayer.score += 1;
                } else {
                    sendToPlayers("BAD_ANSWER");
                    sendToPlayers(respondedPlayer.name);
                    System.out.println("BAD ANSWER");
                    System.out.println("SENDING NAME: " + respondedPlayer.name);
                    sendToPlayers(answer);
                    sendToPlayers(newQuestion.getFullCorrectAnswer());
                    respondedPlayer.score -= 2;

                    respondedPlayer.wrongAnswers++;
                    if(respondedPlayer.wrongAnswers >= 3) {
                        respondedPlayer.paused = true;
                        respondedPlayer.wrongAnswers = 0;
                    }
                }
            }

            questionsCounter++;

            // Set delay between rounds
            TimeUnit.SECONDS.sleep(ROUNDS_DELAY);
            sendToPlayers("END_ROUND");
        }

        sendToPlayers("GAME_FINISH");

        for (Player player : players) {
            player.sendMessage(Integer.toString(player.score));
        }

        Player highestPlayer = null;

        for (Player player : players) {
            if (highestPlayer == null || highestPlayer.score < player.score) {
                highestPlayer = player;
            }
        }

        sendToPlayers(highestPlayer.name);
        sendToPlayers(Integer.toString(highestPlayer.score));
    }

    public static void sendToPlayers(String message) {
        for (Player player : players) {
            player.sendMessage(message);
        }
    }
}
