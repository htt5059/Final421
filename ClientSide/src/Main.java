import Facility.MeetingRoom;
import Facility.Scheduler;
import Work.Work;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Main {
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;
    private Object o;
    private MyGUI gui;
    private Scheduler scheduler;


    public Main(MyGUI gui) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 5059);
        this.gui= gui;
        System.out.println("Client port is " + socket.getLocalPort());
        out= new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        while(true){
            receiveObject();
        }
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
            if (o instanceof Scheduler)
                scheduler= (Scheduler) o;
            if(o instanceof String){
                String cmd= ((String) o).substring(0,13);
                if(cmd.equals("cmd remove r"));
                    gui.removeRoom(((String) o).substring(13));
            }
            if(o instanceof Work)
                gui.updateWorkListFromSocket((Work) o);
            if(o instanceof MeetingRoom)
                gui.updateRoomListFromSocket((MeetingRoom) o);
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        JFrame frame = new MyGUI("MyGUI");
        frame.setSize(800,800);
        frame.setVisible(true);
        new Main((MyGUI)frame);
    }
    void sendObject(MeetingRoom o) throws IOException {
        out.writeObject(o);
    }
}
