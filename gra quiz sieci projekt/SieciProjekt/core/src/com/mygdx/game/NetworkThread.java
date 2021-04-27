package com.mygdx.game;

public class NetworkThread extends Thread {
    String gameStatus = "LOBBY";
    String question;
    String answerA;
    String answerB;
    String answerC;
    String answerD;
    String playerName;
    String playerAnswer;
    String goodAnswer;
    String read;
    String winner;
    String score;
    String winnerScore;

    public void run() {
        while (!gameStatus.equals("STARTED")) {
            String response = NetworkManager.instance.readText();
            switch (response) {
                case "NEW_HOST":
                    gameStatus = "NEW_HOST";
                    break;
                case "START_GAME_CLIENT":
                    gameStatus = "STARTED";
                    break;
                case "NOT_ENOUGH_PLAYERS":
                    gameStatus = "START_FAILED";
                    break;
            }
        }

        while (true) {
            question = NetworkManager.instance.readText();
            if (question.equals("GAME_FINISH")) {
                break;
            }
            gameStatus = "READING_QUESTION";
            answerA = NetworkManager.instance.readText();
            answerB = NetworkManager.instance.readText();
            answerC = NetworkManager.instance.readText();
            answerD = NetworkManager.instance.readText();

            read = NetworkManager.instance.readText();
            switch (read) {
                case "NO_ANSWERS":
                    break;
                case "GOOD_ANSWER":
                    playerName = NetworkManager.instance.readText();
                    playerAnswer = NetworkManager.instance.readText();
                    break;
                case "BAD_ANSWER":
                    playerName = NetworkManager.instance.readText();
                    playerAnswer = NetworkManager.instance.readText();
                    goodAnswer = NetworkManager.instance.readText();
                    break;
            }
            gameStatus = "PAUSE";

            // WYSWIETLANIE POMIEDZY RUNDAMI

            String roundEnd = NetworkManager.instance.readText();
        }

        gameStatus = "FINISHED";
        score = NetworkManager.instance.readText();
        winner = NetworkManager.instance.readText();
        winnerScore = NetworkManager.instance.readText();
    }

    public String getPauseText() {
        switch (read) {
            case "NO_ANSWERS":
                return "Nikt nie odpowiedział na pytanie.";
            case "GOOD_ANSWER":
                return "Gracz " + playerName + " zaznaczył poprawną odpowiedź: " + playerAnswer;
            case "BAD_ANSWER":
                return "Gracz " + playerName + " zaznaczył złą odpowiedź: " + playerAnswer + ". Poprawna odpowiedź to: " + goodAnswer;
        }

        return null;
    }
}
