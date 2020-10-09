public class Main {
    public static void main(String[] args) {
        if(args.length < 3) {
            System.err.println("Error Shutting Down...");
            System.exit(0);
        }

        Server server = new Server(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
        server.execute();
    }
}
