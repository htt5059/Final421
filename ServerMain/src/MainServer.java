import Facility.MeetingRoomManager;
import Facility.Scheduler;

public class MainServer {
    public static void main(String[] args){
        int port =5059;
        Scheduler.getScheduler();
        Server server= new Server(port);
        server.start();
    }
}
