import Facility.MeetingRoomManager;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ServerWorker extends Thread{
    private final Socket mySocket;
    private final Server server;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ServerWorker(Server server, Socket mySocket){
        this.mySocket= mySocket;
        this.server= server;
        try {
            in = new ObjectInputStream(this.mySocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            handleClientSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void handleClientSocket() throws IOException {
        while(true){
            out = new ObjectOutputStream(mySocket.getOutputStream());
            try {
                handleObject(in.readObject());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleObject(Object o) throws IOException {
        if(o==null) return;
        System.out.println("Object recieved " +o.toString());
        List<ServerWorker> list = server.getWokerList();
        for(ServerWorker worker: list){
            System.out.println(worker.mySocket.toString());
            worker.sendObject(o);
        }
    }
    private void sendObject(Object o) throws IOException {
        out.writeObject(o);
    }
}
