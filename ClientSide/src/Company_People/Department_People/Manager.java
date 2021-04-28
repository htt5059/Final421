package Company_People.Department_People;

import Company_People.FrontDesk;
import Work.Work;

import java.util.ArrayList;

public class Manager extends DeptEmployeeABS{
    private final String name;
    private String title ="Manager";
    private FrontDesk frontDesk;
    private Director director;
    private ArrayList<Work> workList = new ArrayList<Work>();
    public Manager(){
        name= "Manager"; director= new Director();
    }
    public Manager(String name, Director d){
        this.name =name; director=d;
    }
}
