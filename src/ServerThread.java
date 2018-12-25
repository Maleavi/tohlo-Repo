import java.io.*;
import java.net.Socket;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerThread extends Thread{
    Socket client;

    public ServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter w = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {
            while (true) {
                String[] request = r.readLine().split(" ");
                if (request[0].equals("GET") && request[1].equals("/") && request[2].equals("HTTP/1.1")) {
                    DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
                    Date date = new Date();

                    w.write("HTTP/1.1 200 OK\r\n");
                    w.write("Content-Type: text/html\r\n");
                    w.write("Date:" + dateFormat.format(date) + "\r\n");
                    w.write("Connection: keep-alive\r\n");
                    w.write("Transfer-Encoding: chunked\r\n\r\n");
                    w.write("4\r\nkeko\r\n0\r\n");
                    w.flush();
                } else {
                    w.write("HTTP/1.1 400 Bad Request");
                    w.flush();
                    break;
                }
            }
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
