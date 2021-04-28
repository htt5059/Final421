import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server extends Thread{
    private final int port;
    private ArrayList<ServerWorker> workerList = new ArrayList<ServerWorker>();

    public Server(int port){
        this.port= port;
    }

    public List<ServerWorker> getWokerList(){
        return workerList;
    }

    public void run() {
        int port = 5059;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true){
                System.out.println("loading...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Running at "+clientSocket.toString());
                ServerWorker worker = new ServerWorker(this, clientSocket);
                workerList.add(worker);
                worker.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void removeWorker(ServerWorker worker){
        workerList.remove(worker);
    }
}
