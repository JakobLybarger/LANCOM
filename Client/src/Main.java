public class Main {
    public static void main(String[] args) {
        if(args.length < 2) return;

        Client client = new Client(args[0], Integer.parseInt(args[1]));
        client.execute();
    }
}
