package Facility;

import java.util.ArrayList;

public class MeetingRoom {
    private int roomNumber;
    private int roomSize;
    private ArrayList<String> operatingTime = new ArrayList();

    public MeetingRoom(int roomSize, int number){
        this.roomSize= roomSize;
        roomNumber = number;
    }

    public boolean bookRoom(String time){
        if(operatingTime.contains(time)){
            return false;
        }
        return true;
    }
    public void done(String time){
        operatingTime.remove(time);
    }
}
