import Company_People.Department_People.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class EmployeeCard extends JFrame{
    private JPanel Card;
    private JButton doneButton;
    private JTextField nameTextField;
    private JTextField titleTextField;
    private JLabel subListLabel;
    private JLabel eListLabel;
    private JList list1;
    private JList list2;
    private DeptPeopleABS p;
    private MyGUI gui;
    public EmployeeCard(Object people, MyGUI gui){
        super("Info Board");
        handlePane(gui);
        handleObject(people);
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doneButton_Listener();
            }
        });
    }
    private void doneButton_Listener(){
        this.dispose();
    }

    private void handlePane(MyGUI gui){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(Card);
        this.pack();
        this.setSize(900,300);
        this.setLocation(500, 200);
        this.setVisible(true);
        this.gui=gui;
    }

    private void handleObject(Object o){
        if(o instanceof Manager){
            nameTextField.setText(((Manager) o).getName());
            titleTextField.setText(((Manager) o).getTitle());
            list1.setListData(new Vector(((Manager) o).getWorkList()));
            list2.setListData(new Vector(((Manager) o).getEmployeeList()));
        }
        if(o instanceof Supervisor){
            Supervisor m = (Supervisor)o ;
            nameTextField.setText(m.getName());
            titleTextField.setText(m.getTitle());
            list1.setListData(new Vector(m.getWorkList()));
            list2.setListData(new Vector(m.getEmployeeList()));
        }
        if (o instanceof Worker){
            nameTextField.setText(((Worker)o).getName());
            titleTextField.setText(((Worker)o).getTitle());
            list1.setListData(new Vector(((Worker)o).getWorkList()));
        }
    }
}
