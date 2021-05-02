package Company_People.Department_People;

import Work.Work;

import java.io.Serializable;

public interface DeptPeopleIF extends Serializable {
    String getName();
    String getTitle();
    void addWork(Work k);
    String doWork();
    void removeWork(Work k);
    String toString();
}
