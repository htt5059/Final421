package Work;

import java.io.Serializable;
import java.util.ArrayList;

public class Decorator extends Work implements Serializable {
    private Work w;
    private ArrayList<Work> workList = new ArrayList<Work>();

    public Decorator(Work w, Work wrappee){
        this.w= w;
        if(wrappee instanceof Installation){
            Installation i = (Installation) wrappee;
            workList.add(i);
        }
        if (wrappee instanceof Maintenance){
            Maintenance m = (Maintenance) wrappee;
            workList.add(m);
        }
    }

    public String getWorkScript(){
        String script=w.getWorkScript()+"\n";
        for(Work i: workList){
            script+=i.getWorkScript()+"\n";
        }
        return script;
    }
}
