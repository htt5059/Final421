package Facility;

import Company_People.Department_People.DeptPeopleABS;

import java.io.Serializable;
import java.util.ArrayList;

public class MeetingRoom implements Serializable {
    private int roomNumber;
    private int roomSize;
    private DeptPeopleABS user;
    private boolean isUsed= false;

    public MeetingRoom(int roomSize, int number){
        this.roomSize= roomSize;
        roomNumber = number;
    }

    public boolean bookRoom(DeptPeopleABS user){
        this.user= user;
        return isUsed =true;
    }
    public void done(){
        isUsed=false;
        user= null;
        MeetingRoomManager.getRoomManager().removeFromQ(this);
    }
    public int roomNum(){return roomNumber;}

    public String toString(){
        if (user==null)
            return "Room Number: "+roomNumber;
        else return "Room Number: "+roomNumber+"    "+user.toString();
    }
    public boolean scheduleBefore(MeetingRoom r){
        return isUsed;
    }
    public String getUserInfo(){
        return user.toString();
    }
    public boolean IsUsed(){return isUsed;}
}
