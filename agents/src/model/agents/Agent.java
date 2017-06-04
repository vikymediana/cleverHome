package model.agents;

import model.message.Message;
import model.AgentType;

/**
 * Created by Javi on 03/06/2017.
 */
public abstract class Agent extends jade.core.Agent {

    public abstract AgentType getAgentType();

    public abstract String getId();

    public abstract Message getMessage();

}
