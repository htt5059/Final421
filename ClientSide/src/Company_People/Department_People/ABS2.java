package Company_People.Department_People;

import java.io.Serializable;

public abstract class ABS2 extends ABS1 implements Serializable {
    public ABS2(String name, String title) {
        super(name, title);
    }
    public String getTitle(){
        return super.getTitle();
    }
}
