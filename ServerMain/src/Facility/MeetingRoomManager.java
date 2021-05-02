package Facility;

import java.util.Vector;

public class MeetingRoomManager {
    private Vector<MeetingRoom> roomList = new Vector<>();
    private Vector<MeetingRoom> waitingQ = new Vector<>();
    private static Object lock= new Object();
    private static volatile MeetingRoomManager RoomManager;
    private MeetingRoomManager(){
        createMeetingRoomObjects();
    }
    public static MeetingRoomManager getRoomManager(){
        if (RoomManager==null){
            synchronized(lock) {
                if (RoomManager == null) {
                    RoomManager = new MeetingRoomManager();
                }
            }
        }
        return RoomManager;
    }
    private void createMeetingRoomObjects(){
        for(int i =0 ; i< 5; i++){
            roomList.addElement(new MeetingRoom(5, i));
        }
    }
    public Vector getRoomList(){return roomList;}

    boolean isInQ(MeetingRoom r){
        return waitingQ.contains(r);
    }
    public MeetingRoom getRoom(int num){
        return roomList.elementAt(num);
    }
    public void addInQ(MeetingRoom r){
        for(MeetingRoom i: roomList){
            if(i.getRoomNum()==r.getRoomNum()){
                i.setRoom(r.getUser());
                waitingQ.add(i);
                break;
            }
        }
    }
    public void removeFromQ(MeetingRoom r){
        waitingQ.remove(r);
    }
    public Vector getWaitingList(){
        Vector<String> temp = new Vector();
        for(MeetingRoom i: waitingQ){
            temp.addElement(i.getRoomInfo());
        }
        return temp;
    }
    public Vector getWaitingQ(){
        return waitingQ;
    }
}
