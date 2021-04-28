import ClientListener.ClientActionListener;
import ClientListener.ReceivingObjectListener;
import ClientListener.SendingObjectListener;
import Work.Work;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Vector;

public class Main {
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;
    private Object o;
    private ArrayList<ClientActionListener> clientActionListener = new ArrayList<>();

    private void connect() throws IOException, ClassNotFoundException {
        socket = new Socket("localhost", 5059);
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        o = in.readObject();
    }

    public Main() throws IOException, ClassNotFoundException {
        connect();
        System.out.println("Client port is " + socket.getLocalPort());
        receiveObject();
    }

    void sendObject(Object w) throws IOException {
        out.writeObject(w);
    }
    void receiveObject() throws IOException, ClassNotFoundException {
        Object o;
        if((o=in.readObject())!=null) {
            sendObject(o);
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        JFrame frame = new MyGUI("MyGUI");
        frame.setSize(800,800);
        frame.setVisible(true);
        new Main();
    }

    public void addSendingObjectListener(ClientActionListener listener){
        clientActionListener.add(listener);
    }
    public void addReceivingObjectListener(ReceivingObjectListener listener){
        clientActionListener.add(listener);
    }
}
