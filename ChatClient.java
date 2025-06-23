
import java.io.*;
import java.net.*;

public class ChatClient {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Thread to read messages from server
            Thread readThread = new Thread(() -> {
                String msg;
                try {
                    while ((msg = in.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            });
            readThread.start();

            // Main thread sends user input to server
            String input;
            while ((input = userInput.readLine()) != null) {
                out.println(input);
            }
        } catch (IOException e) {
            System.out.println("Unable to connect to server: " + e.getMessage());
        }
    }
}
