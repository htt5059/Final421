package Company_People.Department_People;

import Work.Work;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class Class extends ABS1 implements Serializable {
    private DeptPeopleABS boss;
    private ArrayList<ABS2> subordinates = new ArrayList();
    public Class(String name, String title, DeptPeopleABS boss){
        super(name, title);
        this.boss= boss;
    }

    public void addWorker(ABS2 w){subordinates.add(w);}
    public ArrayList getEmployeeList(){return subordinates;}
    public void addWorkers(Vector<ABS2> list){
        subordinates.addAll(list);
    }
}
