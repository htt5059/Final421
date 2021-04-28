package ClientListener;

import Work.Work;

import java.io.IOException;

public interface SendingObjectListener extends ClientActionListener{
    void sending(Work w) throws IOException;
}
