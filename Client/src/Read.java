import java.io.*;
import java.net.*;

public class Read extends Thread {
    BufferedReader reader;
    Socket socket;
    Client client;

    /** Read constructor.
     *  @param socket - The socket created by the client class
     *  @param client - The client class
     */
    public Read(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;

        try {
            // Initialize the reader to read the input stream
            InputStream in = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(in));
        }
        catch (IOException e) {
            System.err.println("Error getting input stream: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /** Listens for a message from another user and prints that
     *  message along with the users name.
     */
    @Override
    public void run() {
        while(true) {
            try {
                // Obtain response sent by other user
                String response = reader.readLine();
                System.out.println("\n" + response);

                // If client has name print out the name
                if (client.getName() != null) {
                    System.out.printf("(%s): \n", client.getName());
                }
            }
            catch (IOException e) {
                System.err.println("Error reading from server: " + e.getMessage());
                e.printStackTrace();
                break;
            }
        }
    }
}
