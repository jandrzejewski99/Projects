import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class LobbyThread extends Thread {
    List<Player> sockets = Collections.synchronizedList(new ArrayList<>());
    boolean running = true;

    public void run() {
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(8888);
            serverSocket.setSoTimeout(1000);
        } catch (IOException exception) {
            System.out.println("Could not create ServerSocket.");
            exception.printStackTrace();
            running = false;
            return;
        }

        while (running) {
            try {
                if (sockets.size() >= 4) continue;
                Socket socket = serverSocket.accept();
                boolean isHost = true;

                if(sockets.size() > 0) {
                    isHost = false;
                }

                Player player = new Player(socket, isHost, Integer.toString(sockets.size() + 1));
                player.onDisconnected.addListener(removePlayer(player));

                sockets.add(player);
                System.out.println("New player has joined the lobby.");
            } catch (IOException ignored) {

            }
        }
    }

    private Consumer<Player> removePlayer(Player player) {
        sockets.remove(player);
        return null;
    }
}
