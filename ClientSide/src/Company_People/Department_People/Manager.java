package Company_People.Department_People;

import Company_People.FrontDesk;
import Work.Work;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Manager extends DeptEmployeeABS implements Serializable {
    private FrontDesk frontDesk;
    private Director director;
    private Vector<ABS1> subList = new Vector();
    public Manager(){
        super("Manager", "Manager");
        director= new Director();
    }
    public Manager(String name, Director d){
        super(name, "Manager");
        director=d;
    }
    public void addFrontDesk(FrontDesk d){
        frontDesk=d;
    }
    public void addEmployee(ABS1 o){
        subList.add(o);
    }
    public Vector getEmployeeList(){return subList;}
    public String toString(){return getName()+" "+getTitle();}
    public void distributeWork(Work w, DeptPeopleABS employee){
        if(subList.size()==1){
            subList.elementAt(0).addWork(w);
            return;
        }
        for(DeptPeopleABS i: subList){
            if (i.equals(employee)){
                i.addWork(w);
            }
        }
    }
}
