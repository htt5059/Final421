public class MainServer {
    public static void main(String[] args){
        int port =5059;
        Server server= new Server(port);
        server.start();
    }
}
