import Work.Work;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class ServerWorker extends Thread{
    private final Socket mySocket;
    private final Server server;
    private ObjectOutputStream out;

    public ServerWorker(Server server, Socket mySocket){
        this.mySocket= mySocket;
        this.server= server;
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
            ObjectInputStream in = new ObjectInputStream(mySocket.getInputStream());
            try {
                //while(in.ready()!=-1) => read is done
                handleObject(in.readObject());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleObject(Object o) throws IOException {
        if(o==null) return;
        System.out.println("Object recieved");
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
