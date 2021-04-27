import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player extends Thread {
    String name;
    Socket socket;
    BufferedReader input;
    PrintWriter output;
    boolean isHost;
    Integer score = 0;
    Integer wrongAnswers = 0;
    boolean paused = false;
    String answer = "";
    Event<Player> onDisconnected = new Event<>();

    public Player(Socket socket, boolean isHost, String name) {
        this.socket = socket;
        this.isHost = isHost;
        this.name = name;

        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            if (isHost) {
                sendMessage("ALLOW_JOIN_HOST");
            } else {
                sendMessage("ALLOW_JOIN");
            }
        } catch (IOException e) {
            System.out.println("Player died: " + e);
        }
    }

    public void run() {
        while (true) {
            try {
                answer = readMessage();
            } catch (IOException exception) {
                System.out.println("Player " + name + " has disconnected.");
                onDisconnected.broadcast(this);
                return;
            }
        }
    }

    public void sendMessage(String text) {
        output.println(text);
    }

    public String readMessage() throws IOException {
        return input.readLine();
    }
}
