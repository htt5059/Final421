package Facility;

import Company_People.Department_People.DeptPeopleABS;

import java.io.Serializable;

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
        MeetingRoomManager.getRoomManager().removeFromQ(this);
        user.roomDone(this);
        user= null;
    }
    public int getRoomNum(){return roomNumber;}

    public String toString(){
        return "Room Number: "+roomNumber;
    }
    public boolean scheduleBefore(MeetingRoom r){
        return isUsed;
    }
    public String getRoomInfo(){
        return "Room: "+roomNumber+"    "+user.toString();
    }
    public boolean IsUsed(){return isUsed;}
    void setRoom(DeptPeopleABS user){
        this.user=user;
        isUsed=true;
    }
    DeptPeopleABS getUser(){return user;}
}
