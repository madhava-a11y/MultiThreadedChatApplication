
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

    private static final int PORT = 12345;
    private static final Set<ClientHandler> clientHandlers = Collections.synchronizedSet(new HashSet<>());

    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        System.out.println("Chat server started on port " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(clientSocket);
                clientHandlers.add(handler);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void broadcast(String message, ClientHandler sender) {
        synchronized (clientHandlers) {
            for (ClientHandler handler : clientHandlers) {
                if (handler != sender) {
                    handler.sendMessage(message);
                }
            }
        }
    }

    static void removeClient(ClientHandler handler) {
        clientHandlers.remove(handler);
    }

    private static class ClientHandler implements Runnable {

        private final Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String clientName;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println("Enter your name:");
                clientName = in.readLine();
                broadcast(clientName + " has joined the chat!", this);
                String message;
                while ((message = in.readLine()) != null) {
                    broadcast(clientName + ": " + message, this);
                }
            } catch (IOException e) {
                // Client disconnected
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                removeClient(this);
                broadcast(clientName + " has left the chat.", this);
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }
    }
}
