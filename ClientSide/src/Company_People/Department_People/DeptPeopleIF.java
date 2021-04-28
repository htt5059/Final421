package Company_People.Department_People;

import Work.Work;

public interface DeptPeopleIF {
    String getName();
    String getTitle();
    void addWork(Work k);
    String doWork();
    void removeWork(Work k);
}
