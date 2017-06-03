package model.agents;

import model.AgentType;
import model.PowerStatus;
import model.message.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Javi on 03/06/2017.
 */
public abstract class Pir extends Agent {

    PowerStatus powerStatus  = PowerStatus.LOW;

    @Override
    public AgentType getAgentType() {
        return AgentType.PIR;
    }

    @Override
    public Message getMessage() {
        Map<String,String> map = new HashMap<>();
        map.put("powerStatus",powerStatus.name());
        map.put("agentType", getAgentType().name());
        Message message = new Message(map);
        return  message;
    }
}
