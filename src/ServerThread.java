import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    Socket client;

    public ServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter w = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
