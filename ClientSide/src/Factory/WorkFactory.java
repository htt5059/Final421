package Factory;

import Work.*;

public class WorkFactory {
    private Work work;
    public WorkFactory(String text) {
        work= createWork(text);
    }

    Work createWork(String script){
        if(script.matches("Work(.*)")){
            return new Work(script.replaceFirst("Work ", ""));
        }
        if (script.matches("Installation(.*)"))
            return new Installation(script.replaceFirst("Installation ", ""));
        if (script.matches("Maintenance(.*)"))
            return new Maintenance(script.replaceFirst("Maintenance ", ""));
        return null;
    }

    public Work getWork() {return work;}
    public Work buildWork(Work w, String workScript){
        return work= new Decorator(w, this.createWork(workScript));
    }
}
