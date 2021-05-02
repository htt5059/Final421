package Company_People;

import Company_People.Department_People.Director;
import Company_People.Department_People.Manager;
import Factory.WorkFactory;
import Work.Work;

import java.io.Serializable;

public class FrontDesk implements Serializable {
    private WorkFactory myFactory;
    private String name;
    private Work work;
    private final String title= "Front Desk Representative";
    private Manager manager;
    private boolean buildDone=false;

    public FrontDesk(Manager m){
        name="Front Desk"; manager= m;
    }
    public FrontDesk(String name, Manager manager){
        this.name= name; this.manager= manager;
    }

    public void getWorkScript(String script){
        myFactory= new WorkFactory(script);
        work=myFactory.getWork();
    }
    public Work announceNewWork(){
        return work;
    }
    public void notifyBuildDone(){
        buildDone=true;
    }

    public String getName(){ return name;}
    public String getTitle(){ return title;}
    public Work buildWork(Work w, String workScript){
        work = myFactory.buildWork(w, workScript);
        if(buildDone){
            manager.addWork(work);
            buildDone=false;
        }
        return work;
    }
}
