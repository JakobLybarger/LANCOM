import java.io.*;
import java.net.*;

public class Client {
    int port;
    String host;
    String name;

    /** Client constructor.
     *  @param host - The server host name
     *  @param port - The server port
     */
    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /** Create new socket connected to host and port and
     *  let user client know if connected to the server or
     *  not.
     */
    public void execute() {
        try {
            Socket socket = new Socket(host, port);
            System.out.println("Connected");

            new Read(socket, this).start();
            new Write(socket, this).start();
        }
        catch(UnknownHostException e) {
            System.err.println("Server Not Found: " + e.getMessage());
            e.printStackTrace();
        }
        catch(IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /** Lets the user create and set their name.
     *  @param name - The users name
     */
    public void setName(String name) {
        this.name = name;
    }
}
