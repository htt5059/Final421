import Company_People.*;
import Company_People.Department_People.*;
import Facility.*;
import Work.Work;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

public class MyGUI extends JFrame {
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
    private Main main;
    private Vector<Manager> managerList = new Vector<>();
    private Vector<Supervisor> supList = new Vector<>();
    private Vector<Worker> workerList = new Vector<>();
    private Director dir;
    private Supervisor supAdmin;
    private Manager manAdmin;
    private FrontDesk frontDesk;


    public MyGUI(String title) throws IOException, ClassNotFoundException {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(ProjectGUI);
        this.pack();
        createMeetingRoomObjects();
        createDeptPeople();
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
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getClickCount()==2 && !e.isConsumed())
                    retrievePeopleInfo();
            }
        });
        bookAMeetingRoomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(managerCheckBox.isSelected()){
                    if (!list2.isSelectionEmpty()){
                        manAdmin.bookRoom(MeetingRoomManager.getRoomManager().getRoom(list2.getSelectedIndex()));
                        if(MeetingRoomManager.getRoomManager().getRoom(list2.getSelectedIndex()).IsUsed()){
                            try {
                                main.sendObject(MeetingRoomManager.getRoomManager().getRoom(list2.getSelectedIndex()));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        list3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getClickCount()==2 && !e.isConsumed()){
                    String r=(String)list3.getSelectedValue();
                    String cmd= "cmd remove r "+r;
                    try {
                        main.sendObject(cmd);
                        Vector<MeetingRoom> temp = MeetingRoomManager.getRoomManager().getWaitingQ();
                        for(MeetingRoom i: temp){
                            if (i.getRoomInfo().equals(r)){
                                i.done();
                                break;
                            }
                        }
                        list3.setListData(MeetingRoomManager.getRoomManager().getWaitingList());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    void retrievePeopleInfo(){
        int item= list1.getSelectedIndex();
        System.out.println(item);
        if(workerCheckBox.isSelected()){
            new EmployeeCard(workerList.elementAt(item), this);
        }
        if(supervisorCheckBox.isSelected()){
            if(item<=supList.size()-1){
                new EmployeeCard(supList.elementAt(item), this);
            }
            if(item>supList.size()-1 && item<=supList.size()+workerList.size()-1){
                new EmployeeCard(workerList.elementAt(item-supList.size()),this);
            }
        }
        if(managerCheckBox.isSelected()){
            if(item<managerList.size()){
                new EmployeeCard(managerList.elementAt(item), this);
            }
            if(item>=managerList.size()&&item<supList.size()+managerList.size()){
                new EmployeeCard(supList.elementAt(item-managerList.size()),this);
            }
            if(item>=supList.size()+managerList.size() && item<supList.size()+managerList.size()+workerList.size()){
                new EmployeeCard(workerList.elementAt(item-managerList.size()-supList.size()), this);
            }
        }
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
            Vector temp = new Vector();
            temp.addAll(supList);
            temp.addAll(workerList);
            list1.setListData(temp);
        }
        if (boxName.equals(workerCheckBox.getText())){
            workerCheckBox.setSelected(true);
            supervisorCheckBox.setSelected(false);
            managerCheckBox.setSelected(false);
            frontDeskRepCheckBox.setSelected(false);
            list1.setListData(workerList);
        }
        if(boxName.equals(managerCheckBox.getText())){
            managerCheckBox.setSelected(true);
            supervisorCheckBox.setSelected(false);
            workerCheckBox.setSelected(false);
            frontDeskRepCheckBox.setSelected(false);
            Vector<DeptPeopleABS> temp= new Vector<>();
            temp.addAll(managerList);
            temp.addAll(supList);
            temp.addAll(workerList);
            list1.setListData(temp);
        }
        if(boxName.equals(frontDeskRepCheckBox.getText())){
            frontDeskRepCheckBox.setSelected(true);
            supervisorCheckBox.setSelected(false);
            workerCheckBox.setSelected(false);
            managerCheckBox.setSelected(false);
            list1.setListData(new Vector());
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
    void updateWorkList(Work w) throws IOException {
        main.sendObject(w);
    }

    void updateWorkListFromSocket(Work w){
        workList.add(w);
        list4.setListData(workList);
    }

    void createMeetingRoomObjects(){
        list2.setListData(MeetingRoomManager.getRoomManager().getRoomList());
    }
    void createDeptPeople(){
        dir= new Director();
        Random rand= new Random();
        int model = rand.nextInt(100);
        Manager m = new Manager("Huy Tran "+model, dir); manAdmin= m;
        DeptPeopleABS supervisorAdmin = new Supervisor("Huy Tran S", m); supAdmin= (Supervisor) supervisorAdmin;
        managerList.add(m);
        supList.add((Supervisor) supervisorAdmin);
        for(int i=0; i<5; i++){
            if (i<2){
                DeptPeopleABS manager = new Manager("Manager"+i,dir);
                managerList.add((Manager)manager);
                DeptPeopleABS s = new Supervisor("Supervisor"+i, manager);
                supList.add((Supervisor)s);
                m.addEmployee((ABS1) s);
                Worker w = new Worker("Worker"+i, s);
                workerList.add(w);
                m.addEmployee(w);
                ((Supervisor) supervisorAdmin).addWorker(w);
            }else if(i<3 && i>=2){
                DeptPeopleABS s = new Supervisor("Supervisor"+i, dir);
                supList.add((Supervisor) s);
                Worker w = new Worker("Worker"+i, s);
                workerList.add(w);
                ((Supervisor) supervisorAdmin).addWorker(w);
                m.addEmployee((ABS1) s);
                m.addEmployee(w);
            }else {
                Worker w= new Worker("Worker"+i, m);
                workerList.add(w);
                ((Supervisor) supervisorAdmin).addWorker(w);
                m.addEmployee(w);
            }
        }
        frontDesk = new FrontDesk(m);
        m.addFrontDesk(frontDesk);
        Manager temp=managerList.elementAt(1);
        temp.addEmployee(supList.elementAt(0));
        supList.elementAt(0).addWorker(workerList.elementAt(1));
        supList.elementAt(0).addWorker(workerList.elementAt(2));
        supList.elementAt(1).addWorker(workerList.elementAt(0));
        supList.elementAt(1).addWorker(workerList.elementAt(3));
    }

    void updateRoomListFromSocket(MeetingRoom o) {
        Vector<MeetingRoom> temp= MeetingRoomManager.getRoomManager().getWaitingQ();
        boolean check =false;
        for(MeetingRoom i: temp){
            if(i.getRoomNum()==o.getRoomNum()){
                check =true;
            }
        }
        if(!check){
            MeetingRoomManager.getRoomManager().addInQ(o);
            list3.setListData(MeetingRoomManager.getRoomManager().getWaitingList());
        }
    }
    void removeRoom(String o){
        System.out.println(o);
        int i=0;
        int max= list3.getModel().getSize();
        Vector<String> temp = new Vector();
        while(i<max){
            String roomInfo= ((String)list3.getModel().getElementAt(i));
            if (!roomInfo.equals(o)){
                temp.add(((String)list3.getModel().getElementAt(i)));
            }
            i++;
        }
        list3.setListData(temp);
        Vector<MeetingRoom> tempQ = MeetingRoomManager.getRoomManager().getWaitingQ();
        for(MeetingRoom r: tempQ){
            if(r.getRoomInfo().equals(o)){
                r.done(); break;
            }
        }
    }
}
