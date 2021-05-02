package Company_People.Department_People;

import Work.Work;

import java.io.Serializable;
import java.util.ArrayList;

public class Worker extends ABS2 implements Serializable {
    private Object directedBoss;

    public Worker(String name, DeptPeopleABS boss){
        super(name, "Worker");
        if (boss instanceof Manager || boss instanceof Supervisor)
            directedBoss=boss;
    }
}
