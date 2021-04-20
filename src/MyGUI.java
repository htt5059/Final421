import javax.swing.*;

public class MyGUI extends JFrame{
    private JPanel ProjectGUI;
    private JTextField orderTextField;
    private JButton sendMaintenanceRequestButton;
    private JButton bookAMeetingRoomButton;
    private JList list1;
    private JList list2;
    private JCheckBox superVisorCheckBox;
    private JCheckBox workerCheckBox;
    private JCheckBox managerCheckBox;
    private JCheckBox frontDeskRepCheckBox;
    private JList list3;
    private JList list4;
    private JButton doneButton;

    public MyGUI(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(ProjectGUI);
        this.pack();
    }

    public static void main(String[] args) {
        JFrame frame = new MyGUI("MyGUI");
        frame.setSize(800,800);
        frame.setVisible(true);
    }
}
