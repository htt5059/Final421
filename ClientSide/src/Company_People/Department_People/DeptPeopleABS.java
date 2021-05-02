package Company_People.Department_People;

import Facility.MeetingRoom;
import Facility.Scheduler;
import Work.Work;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class DeptPeopleABS implements DeptPeopleIF, Serializable {
    private ArrayList<MeetingRoom> roomList;
    private String name="";
    private String title="";
    private ArrayList<Work> workList;
    public DeptPeopleABS(){}
    public DeptPeopleABS(String name, String title){
        this.name = name;
        this.title=title;
        roomList= new ArrayList();
        workList = new ArrayList<>();
    }
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

    @Override
    public String toString(){
        return name+"   "+title;
    }

    public ArrayList getWorkList(){return workList;}
    public void bookRoom(MeetingRoom room) {
        try {
            Scheduler.getScheduler().enter(room, this);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void roomDone(MeetingRoom r){
        Scheduler.getScheduler().done();
    }
}
/*
Date date = new Date();
            long timeStamp = date.getTime();
            Time t = new Time(timeStamp);
            int sec = t.getSeconds()+10+t.getMinutes()*60+t.getHours()*3600;
            while(true){
                Time now =new Time(new Date().getTime());
                int nowSec = now.getSeconds()+now.getMinutes()*60+now.getHours()*3600;
                if(nowSec<sec){
                    System.out.println(room.toString());
                }else break;
            }
            room.done();
* */