package Facility;

import Company_People.Department_People.DeptPeopleABS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Scheduler implements Serializable {
    private Thread runningThread;
    private ArrayList waitingRequests;
    private ArrayList waitingThreads;
    private static Object lock= new Object();
    private static int model;
    private static Scheduler scheduler;
    private Scheduler(){
        waitingRequests = new ArrayList();
        waitingThreads = new ArrayList();
        model= new Random().nextInt(100);
    }
    public static Scheduler getScheduler(){
        if (scheduler==null) {
            synchronized (lock) {
                if (scheduler == null)
                    scheduler = new Scheduler();
            }
        }
        System.out.println(model);
        return scheduler;
    }
    public void enter(MeetingRoom room, DeptPeopleABS user) throws InterruptedException {
        Thread thisThread = Thread.currentThread();
        synchronized (this) {
            if (runningThread == null) {
                runningThread = thisThread;
                room.bookRoom(user);
                return;
            }
            waitingThreads.add(thisThread);
            waitingRequests.add(room);
        }
        synchronized (lock) {
            while (thisThread != runningThread) {
                lock.wait();
            }
        }
        synchronized (this) {
            int i = waitingThreads.indexOf(thisThread);
            waitingThreads.remove(i);
            waitingRequests.remove(i);
        }
    }
    synchronized public void done() {
        if (runningThread != Thread.currentThread())
            throw new IllegalStateException("Wrong Thread");
        int waitCount = waitingThreads.size();
        if (waitCount <= 0) {
            runningThread = null;
        } else if (waitCount == 1) {
            runningThread = (Thread)waitingThreads.get(0);
        } else {
            int next = waitCount - 1;
            MeetingRoom nextRequest;
            nextRequest = (MeetingRoom)waitingRequests.get(next);
            for (int i = waitCount-2; i>=0; i--) {
                MeetingRoom r;
                r = (MeetingRoom)waitingRequests.get(i);
                if (r.scheduleBefore(nextRequest)) {
                    next = i;
                    nextRequest = (MeetingRoom)waitingRequests.get(next);
                }
            }
            runningThread = (Thread)waitingThreads.get(next);
        }
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
