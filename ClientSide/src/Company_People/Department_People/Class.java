package Company_People.Department_People;

import Work.Work;

import java.util.ArrayList;

public class Class extends ABS1 {
    private String name;
    private String title;
    private Manager manager;
    private ArrayList<ABS2> subordinates = new ArrayList();
    public Class(String name, Manager manager){
        this.name= name;
        this.manager= manager;
    }

    public void addSub(ABS2 w){subordinates.add(w);}

}
