import Company_People.FrontDesk;
import Work.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Request extends JFrame{
    private JLabel workRequester;
    private JCheckBox installationCheckBox;
    private JCheckBox maintenanceCheckBox;
    private JTextArea textArea1;
    private JButton addOtherWorkButton;
    private JButton doneButton;
    private JPanel Request;
    private ArrayList<String> listWork= new ArrayList();
    private String workScript;
    private MyGUI mainFrame;
    private Work work;
    private FrontDesk frontDesk;

    public Request(MyGUI frame, Work w, FrontDesk frontDesk){
        super("Request Form");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(Request);
        this.pack();
        this.setSize(300,300);
        this.setLocation(500, 200);
        this.setVisible(true);
        mainFrame=frame;
        textArea1.enable(false);
        work= w;
        this.frontDesk=frontDesk;

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uncheckOtherBoxes(e);
            }
        };
        installationCheckBox.addActionListener(listener);
        maintenanceCheckBox.addActionListener(listener);

        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createWork(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        addOtherWorkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createWork(e);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    void uncheckOtherBoxes(ActionEvent e){
        String boxName = e.getActionCommand();
        if (boxName.equals(installationCheckBox.getText())){
            installationCheckBox.setSelected(true);
            maintenanceCheckBox.setSelected(false);
            textArea1.enable(true);
        }
        if (boxName.equals(maintenanceCheckBox.getText())){
            maintenanceCheckBox.setSelected(true);
            installationCheckBox.setSelected(false);
            textArea1.enable(true);
        }
    }
    void createWork(ActionEvent e) throws IOException {
        if (e.getActionCommand().equals(doneButton.getText())){
            frontDesk.notifyBuildDone();
            decorOrCreate();
            mainFrame.updateWorkList(work);
            this.dispose();
        }
        if (e.getActionCommand().equals(addOtherWorkButton.getText())){
            decorOrCreate();
            this.dispose();
            new Request(mainFrame, work, frontDesk);
        }

    }

    void decorOrCreate(){
        if(work==null){
            if (installationCheckBox.isSelected()){
                String script= "Installation "+ textArea1.getText();
                workScript = script;
                frontDesk.getWorkScript(workScript);
                work = frontDesk.announceNewWork();
            }
            if (maintenanceCheckBox.isSelected()){
                String script= "Maintenance "+ textArea1.getText();
                workScript = script;
                frontDesk.getWorkScript(workScript);
                work = frontDesk.announceNewWork();
            }
        }
        else{
            if (installationCheckBox.isSelected()){
                String script= "Installation "+ textArea1.getText();
                workScript = script;
                work = frontDesk.buildWork(work, workScript);
            }
            if (maintenanceCheckBox.isSelected()){
                String script= "Maintenance "+ textArea1.getText();
                workScript = script;
                work = frontDesk.buildWork(work, workScript);
            }
        }
    }
}
