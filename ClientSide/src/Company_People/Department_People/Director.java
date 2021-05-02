package Company_People.Department_People;

import Work.Work;

import java.io.Serializable;
import java.util.ArrayList;

public class Director extends DeptPeopleABS implements Serializable {
    private final String name;
    private final String title= "Department Director";
    private ArrayList<DeptEmployeeABS> employeeList = new ArrayList<>();
    private ArrayList<Work> workList = new ArrayList<>();
    public Director(){
        name= "Director";
    }
    public Director(String name){
        this.name =name;
    }

    public void addSubordinate(DeptEmployeeABS e){
        employeeList.add(e);
    }
    public void remove(DeptPeopleABS e){employeeList.remove(e);}
}
