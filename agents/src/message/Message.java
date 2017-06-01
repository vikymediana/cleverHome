package message;

import model.InternalStatusAgent;

import java.io.Serializable;

/**
 * Created by jlarque on 26/05/2017.
 */
public class Message implements Serializable {
    private InternalStatusAgent status;

    public Message (InternalStatusAgent status) {
        setStatus(status);
        System.out.println("sdfg");
    }
    public InternalStatusAgent getStatus() {
        return status;
    }

    public void setStatus(InternalStatusAgent status) {
        this.status = status;
    }

}
