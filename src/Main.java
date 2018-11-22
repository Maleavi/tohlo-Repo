import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(80)) {
            while (true) {
                Socket client = serverSocket.accept();
                ServerThread thread = new ServerThread(client);
                thread.run();
            }
        } catch (IOException e) {

        }
    }
}
