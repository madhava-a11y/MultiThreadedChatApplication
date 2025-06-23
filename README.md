Project Description

I built this Java client-server chat application to explore real-time communication using sockets and multithreading. The main objective was to create a simple yet effective group chat system where multiple users can connect to a central server and exchange messages instantly. This project deepened my understanding of Java networking, thread management, and the basics of concurrent programming.


The application consists of two main components: ChatServer.java and ChatClient.java. The server listens for incoming client connections on a specified port (12345 by default) and manages each client in a separate thread. This ensures that multiple clients can interact with the server simultaneously without blocking each other. When a client sends a message, the server broadcasts it to all other connected clients, enabling real-time group chat functionality.


The client application connects to the server, prompts the user for a name, and then allows the user to send and receive messages. Each client runs two threads: one for sending user input to the server and another for listening to incoming messages from the server. This design ensures that the client can receive messages from others even while the user is typing.


I structured the project as follows:


Project Structure

javachatapp/

├── ChatServer.java   // The server application

└── ChatClient.java   // The client application

To develop and test the application, 
I used Visual Studio Code (VS Code) as my IDE. 
VS Code provided a convenient environment for editing, compiling, and running Java code, as well as managing multiple terminal windows for server and client processes.


How to Run
Compile the Java files:
   javac ChatServer.java ChatClient.java
Start the server in one terminal:
   java ChatServer
Open one or more additional terminals and start clients:
java ChatClient


Enter your name when prompted. Each client window acts as a separate user.

Type messages in any client window and press Enter. All connected clients will receive the messages in real time.

The server must be running before any clients can connect. All clients connect to localhost by default, but you can modify the server address in ChatClient.java to support remote connections. To stop the server or clients, simply close their respective terminal windows.


References:
This project was inspired by several online resources. I referred to the GeeksforGeeks Java Sockets Tutorial for understanding the basics of socket programming. The CodeWithHarry YouTube video on Java Socket Programming provided a practical demonstration of client-server communication. I also used ChatGPT to clarify concepts related to multithreading and socket management.

Building this chat application was a valuable learning experience. It gave me hands-on practice with Java's networking libraries and helped me understand how to manage multiple client connections efficiently. The project can be extended with features like private messaging, user authentication, or a graphical user interface. For now, it serves as a solid foundation for anyone interested in learning about real-time communication and concurrent programming in Java.
