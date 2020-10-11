import java.io.*;
import java.net.*;

public class User extends Thread {
    private Socket socket;
    private Server server;
    private PrintWriter writer;

    /** User constructor.
     *  @param socket - The users socket
     *  @param server - The server the user is connected to
     */
    public User(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    /** Main function of the class. lets the user enter 0 to many
     *  messages and prints them out for other users to see, alerts
     *  when a user has joined, and lets the user leaves/alerts upon
     *  leaving.
     */
    @Override
    public void run() {
        try {
            // Setting out input and output streams
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            OutputStream out = socket.getOutputStream();
            writer = new PrintWriter(out, true);

            printUsers();

            String name = reader.readLine();
            server.addName(name);
            server.displayMessage("New user: " + name, this);

            String serverMessage;
            String clientMessage;
            do {
                clientMessage = reader.readLine();
                serverMessage = String.format("(%s): %s", name, clientMessage);
                server.displayMessage(serverMessage, this);
            } while (!clientMessage.equals("bye"));

            server.removeUser(name, this);
            socket.close();

            server.displayMessage(name + " left", this);
        }
        catch(IOException e) {
            System.err.printf("Thread Error: %s", e.getMessage());
            e.printStackTrace();
        }
    }

    /** Prints all of the users on the server. */
    private void printUsers() {
        if(server.active()) {
            writer.println("Users Online: " + server.getUserNames());
        }
        else {
            writer.println("The server is currently inactive...");
        }
    }

    /** Prints the message on users screen sent by another user.
     *  @param message - The message sent by another user
     */
    public void sendMessage(String message) {
        writer.println(message);
    }
}
