package Company_People.Department_People;

import Facility.MeetingRoom;
import Work.Work;

import java.util.ArrayList;

public abstract class DeptPeopleABS implements DeptPeopleIF{
    //private Scheduler scheduler;
    private ArrayList<MeetingRoom> roomList = new ArrayList<>();
    private String name;
    private String title;
    private ArrayList<Work> workList = new ArrayList<>();
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void addWork(Work k) {
        workList.add(k);
    }

    @Override
    public String doWork() {
        String workScript="";
        for(Work w: workList){
            workScript+=w.getWorkScript()+"\n";
        }
        return workScript;
    }

    @Override
    public void removeWork(Work k) {
        workList.remove(k);
    }
    private void bookRoom(MeetingRoom room){

    }
}
