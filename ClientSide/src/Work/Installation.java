package Work;

import java.io.Serializable;

public class Installation extends Work implements Serializable {

    public Installation(String text) {
        super(text);
    }
    public Installation(Work w){
        super(w.getScript());
    }
}
