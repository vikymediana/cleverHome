package agents;

import com.pi4j.io.gpio.*;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import model.AgentType;
import model.PowerStatus;
import model.behavior.LedBehavior;
import model.message.Message;
import utils.RegisterUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jlarque on 26/05/2017.
 */
public class Led extends model.agents.Led {


    AgentsDescription agent = AgentsDescription.LED1;

    @Override
    public String getId() {
        return "LED1";
    }

    @Override
    public Message getMessage() {
        return null;
    }


    @Override
    public void setup(){

        RegisterUtils.register(this,agent,getAID());

        Map mapLow = new HashMap<String,String>();
        mapLow.put("agentType", AgentType.PIR);
        mapLow.put("powerStatus", PowerStatus.LOW);

        Map mapHigh = new HashMap<String,String>();
        mapLow.put("agentType", AgentType.PIR);
        mapLow.put("powerStatus", PowerStatus.HIGH);

        addBehaviour(new LedBehavior(RaspiPin.GPIO_08,mapLow,null));

    }
}
