package Company_People.Department_People;

import java.io.Serializable;
import java.util.Vector;

public class Supervisor extends Class implements Serializable {
    public Supervisor(String name, DeptPeopleABS boss) {
        super(name,"Supervisor", boss);
    }
}
