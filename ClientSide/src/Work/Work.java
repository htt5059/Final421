package Work;

import java.io.Serializable;
import java.util.ArrayList;

public class Work implements Serializable {
    String workScript;
    public Work(){}
    public Work(String text){workScript= text;}
    public String getWorkScript(){ return this.getClass().getName()+": "+workScript;}
    protected String getScript() {return workScript;}
}
