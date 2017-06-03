package model.agents;

import model.AgentType;

/**
 * Created by victoria on 3/06/17.
 */
public abstract class DatabaseAgent extends Agent {

    @Override
    public AgentType getAgentType() {
        return AgentType.DB;
    }
}
