package ClientListener;

import java.io.IOException;

public interface ReceivingObjectListener extends ClientActionListener {
    void receiving() throws IOException, ClassNotFoundException;
}
