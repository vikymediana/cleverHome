package message;

import java.io.Serializable;

/**
 * Created by jlarque on 26/05/2017.
 */
public class Message implements Serializable {
    private Status status;

    public Message (Status status) {
        setStatus(status);
	System.out.println(status);
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        ON,
        OFF;
    }
}
