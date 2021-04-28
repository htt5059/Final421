package Company_People.Department_People;

import Work.Work;

import java.util.ArrayList;

public class Worker extends ABS2{
    private String name;
    private String title = "Worker";
    private DeptEmployeeABS directedBoss;

    public Worker(String name, DeptEmployeeABS boss){
        this.name = name;
        directedBoss=boss;
    }
}
