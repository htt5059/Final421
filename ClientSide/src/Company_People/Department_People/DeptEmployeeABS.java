package Company_People.Department_People;

import Work.Work;

import java.io.Serializable;

public abstract class DeptEmployeeABS extends DeptPeopleABS implements Serializable {
    public DeptEmployeeABS(String name, String title) {
        super(name, title);
    }
}
