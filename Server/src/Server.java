import java.io.*;
import java.net.*;
import java.util.*;

/** This class is the Server. When a client gets connected,
 *  an instance of user is created to serve the client.*/
public class Server {
    private int port; // Server port number
    private Set<String> userNames; // Set of the names of users on the server
    private Set<User> users; // Set of the users on the server.

    /** Server constructor.
     *  @param port - The port number(int)
     */
    public Server(int port) {
        this.port = port;
    }

    /** Creates server socket and adds new sockets for every
     *  user that joins the server.
     */
    void execute() {
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.printf("Listening on port: %s%n", port);

            while(true) {
                // Create new socket for next user to join
                Socket socket = serverSocket.accept();
                System.out.println("User Connected");

                // Create new users adding recently created socket
                // as its socket and the server(this) as the server
                User user = new User(socket, this);
                users.add(user);
                user.start(); // Start running the thread
            }
        }
        catch(IOException e) {
            System.err.printf("Server Error: %s%n", e.getMessage());
            e.printStackTrace();
        }
    }

    /** Displays the message sent to everyone but the user
     *  who sent the message.
     *  @param message - The message being sent
     *  @param user - The User who sent the message
     */
    void displayMessage(String message, User user) {
        for(User u : users) {
            if (u != user) {
                user.sendMessage(message);
            }
        }
    }

    /** Adds username of connected user to userNames. */
    void addName(String name) {
        userNames.add(name);
    }

    /** Removes user when from userNames and users when they
     *  disconnect.
     *  @param name - The users username
     *  @param user - The User who disconnected.
     */
    void removeUser(String name, User user) {
        boolean removed = userNames.remove(name);
        if(removed) {
            users.remove(user);
            System.out.printf("%s left the server", name);
        }
    }

    /** Returns a Set of all of the usernames.
     *  @return userNames*/
    Set<String> getUserNames() {
        return this.userNames;
    }

    /** Returns whether the server is active or not
     *  (aka whether there are users in it or not).
     *  @return true if the list is not empty; else false
     */
    boolean active() {
        return !this.users.isEmpty();
    }
}
