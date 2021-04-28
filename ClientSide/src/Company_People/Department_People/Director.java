package Company_People.Department_People;

import java.util.ArrayList;

public class Director extends DeptPeopleABS {
    private final String name;
    private final String title= "Department Director";
    private ArrayList<DeptEmployeeABS> employeeList = new ArrayList<>();
    public Director(){
        name= "Director";
    }
    public Director(String name){
        this.name =name;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void addSubordinate(DeptEmployeeABS e){
        employeeList.add(e);
    }
}
