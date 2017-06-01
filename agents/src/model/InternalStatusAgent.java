package model;

import java.io.Serializable;

/**
 * Created by Javi on 28/05/2017.
 */
public class InternalStatusAgent implements Serializable{

    private AgentType agentType;
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public AgentType getAgentType() {
        return agentType;
    }

    public void setAgentType(AgentType agentType) {
        this.agentType = agentType;
    }

    public InternalStatusAgent(Status status, AgentType agentType) {

        this.agentType = agentType;
        this.status = status;
        System.out.print("Prueba");
    }

    public enum AgentType {
        LED,
        PIR;
    }

    public enum Status {
        ON,
        OFF;
    }

}
