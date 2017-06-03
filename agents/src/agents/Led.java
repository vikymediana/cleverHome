package agents;

import com.pi4j.io.gpio.*;
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
        System.out.print("HAGO ALGOOOO");
        RegisterUtils.register(this,getId(),getAID());

        Map mapLow = new HashMap<String,String>();
        mapLow.put("agentType", AgentType.PIR.name());
        mapLow.put("powerStatus", PowerStatus.LOW.name());

        Map mapHigh = new HashMap<String,String>();
        mapHigh.put("agentType", AgentType.PIR.name());
        mapHigh.put("powerStatus", PowerStatus.HIGH.name());

        addBehaviour(new LedBehavior(RaspiPin.GPIO_08,mapLow,mapHigh));
        System.out.print("ARRANCADOOOO");

    }
}
