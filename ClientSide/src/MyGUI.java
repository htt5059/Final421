import ClientListener.ClientActionListener;
import Company_People.FrontDesk;
import Work.Work;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;

public class MyGUI extends JFrame implements ClientActionListener {
    private JPanel ProjectGUI;
    private JTextField orderTextField;
    private JButton sendMaintenanceRequestButton;
    private JButton bookAMeetingRoomButton;
    private JList list1;
    private JList list2;
    private JCheckBox supervisorCheckBox;
    private JCheckBox workerCheckBox;
    private JCheckBox managerCheckBox;
    private JCheckBox frontDeskRepCheckBox;
    private JList list3;
    private JList list4;
    private JButton doneButton;
    private Vector<Work> workList= new Vector<>();
    private ArrayList<Thread> threadList;
    private Main main;

    private FrontDesk frontDesk;


    public MyGUI(String title) throws IOException, ClassNotFoundException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(ProjectGUI);
        this.pack();
        main = new Main();

        sendMaintenanceRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendRequest(e);
            }
        });
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uncheckOtherBoxes(e);
                if(frontDeskRepCheckBox.isSelected()){
                    frontDesk = new FrontDesk();
                }
            }
        };
        supervisorCheckBox.addActionListener(listener);
        workerCheckBox.addActionListener(listener);
        managerCheckBox.addActionListener(listener);
        frontDeskRepCheckBox.addActionListener(listener);

        list4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                displayWork();
            }
        });
        //main.addSendingObjectListener(this);
    }

    void sendRequest(ActionEvent e){
        if(frontDeskRepCheckBox.isSelected() && frontDesk!=null){
            new Request(this, null, frontDesk);
        }
    }
    void uncheckOtherBoxes(ActionEvent e){
        String boxName = e.getActionCommand();
        if (boxName.equals(supervisorCheckBox.getText())){
            supervisorCheckBox.setSelected(true);
            workerCheckBox.setSelected(false);
            managerCheckBox.setSelected(false);
            frontDeskRepCheckBox.setSelected(false);
        }
        if (boxName.equals(workerCheckBox.getText())){
            workerCheckBox.setSelected(true);
            supervisorCheckBox.setSelected(false);
            managerCheckBox.setSelected(false);
            frontDeskRepCheckBox.setSelected(false);
        }
        if(boxName.equals(managerCheckBox.getText())){
            managerCheckBox.setSelected(true);
            supervisorCheckBox.setSelected(false);
            workerCheckBox.setSelected(false);
            frontDeskRepCheckBox.setSelected(false);
        }
        if(boxName.equals(frontDeskRepCheckBox.getText())){
            frontDeskRepCheckBox.setSelected(true);
            supervisorCheckBox.setSelected(false);
            workerCheckBox.setSelected(false);
            managerCheckBox.setSelected(false);
        }
    }

    void displayWork(){
        int index= list4.getSelectedIndex();
        int i=0;
        String workScript="";
        for(Work w: workList){
            if(i++==index){
                workScript+= w.getWorkScript()+"\n";
                if(frontDeskRepCheckBox.isSelected()){
                    JOptionPane.showConfirmDialog(this, workScript);
                }else{
                    Process p = new Process(this, w);
                }
            }
        }
    }
    void removeWork(Work w){
        workList.remove(w);
        list4.setListData(workList);
    }
    public void updateWorkList(Work w) throws IOException {
        workList.add(w);
        list4.setListData(workList);
        main.sendObject(w);
    }

    /*public static void main(String[] args) throws IOException, ClassNotFoundException {
        JFrame frame = new MyGUI("MyGUI");
        frame.setSize(800,800);
        frame.setVisible(true);
    }*/
}
