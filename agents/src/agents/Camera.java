package agents;

import model.AgentType;
import model.agents.Agent;
import model.message.Message;

/**
 * Created by Javi on 04/06/2017.
 */
public class Camera  extends Agent{
    @Override
    public AgentType getAgentType() {
        return AgentType.CAMERA;
    }

    @Override
    public String getId() {
        return "Camera1";
    }

    @Override
    public Message getMessage() {
        return null;
    }
}
