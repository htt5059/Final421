package Company_People.Department_People;

import Facility.MeetingRoom;

import java.util.ArrayList;

public abstract class DeptPeopleABS implements DeptPeopleIF{
    //private Scheduler scheduler;
    private ArrayList<MeetingRoom> roomList = new ArrayList<>();
    @Override
    public abstract String getName();

    @Override
    public abstract String getTitle();
    private void bookRoom(MeetingRoom room){

    }
}
