import Work.Work;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Process extends JFrame{
    private JPanel Process;
    private JButton finishButton;
    private JButton closeButton;
    private JTextField textField1;
    private final MyGUI mainFrame;
    private final Work work;
    Process(MyGUI frame, Work w){
        super("Process Form");
        mainFrame = frame;
        work =w;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setContentPane(Process);
        this.pack();
        this.setSize(300,300);
        this.setLocation(500, 200);
        this.setVisible(true);
        getWorkScript();

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doneButton();
            }
        });
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finishButton();
            }
        });
    }

    void doneButton(){
        this.dispose();
    }

    void finishButton(){
        mainFrame.removeWork(work);
        this.dispose();
    }

    void getWorkScript(){
        textField1.setText(work.getWorkScript());
    }
}
