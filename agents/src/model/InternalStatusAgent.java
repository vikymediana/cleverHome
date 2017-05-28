package model;

/**
 * Created by Javi on 28/05/2017.
 */
public class InternalStatusAgent {

    private AgentType agentType;

    public InternalStatusAgent(AgentType agentType) {
        this.agentType = agentType;
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
