import java.io.*;
import java.net.*;

public class Write extends Thread {
    private PrintWriter writer;
    private Socket socket;
    private Client client;

    /** Write constructor.
     *  @param socket - The socket created by the client class
     *  @param client - The client
     */
    public Write(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;

        try {
            // Initialize output stream and writer
            OutputStream out = socket.getOutputStream();
            writer = new PrintWriter(out, true);
        }
        catch (IOException e) {
            System.err.println("Error getting output stream: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /** Inputs text from the user and prints it. */
    @Override
    public void run() {
        Console con = System.console();

        String name = con.readLine("\nEnter name: ");
        client.setName(name);
        writer.println(name);

        String text;
        do {
            text = con.readLine("(" + name + "): ");
            writer.println(text);
        } while (!text.equals("bye"));

        try {
            socket.close();
        }
        catch (IOException e) {
            System.err.println("Error writing to server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
