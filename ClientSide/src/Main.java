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
    private MyGUI gui;
    private ArrayList<ClientActionListener> clientActionListener = new ArrayList<>();

    public Main(MyGUI gui) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 5059);
        this.gui= gui;
        System.out.println("Client port is " + socket.getLocalPort());
        out= new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        receiveObject();
    }
    public Main() throws IOException {
        Socket socket = new Socket("localhost", 5059);
        System.out.println("Client port is " + socket.getLocalPort());
        out= new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    void sendObject(Object w) throws IOException {
        out.writeObject(w);
    }
    void receiveObject() throws IOException, ClassNotFoundException {
        Object o;
        if((o=in.readObject())!=null) {
            gui.updateWorkListFromSocket((Work) o);
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        JFrame frame = new MyGUI("MyGUI");
        frame.setSize(800,800);
        frame.setVisible(true);

        new Main((MyGUI)frame);
    }

    public void addSendingObjectListener(ClientActionListener listener){
        clientActionListener.add(listener);
    }
    public void addReceivingObjectListener(ReceivingObjectListener listener){
        clientActionListener.add(listener);
    }
}
