package agents;

import com.pi4j.io.gpio.RaspiPin;
import model.AgentType;
import model.PowerStatus;
import model.behavior.LedBehavior;
import model.message.Message;
import utils.RegisterUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Javi on 10/06/2017.
 */
public class Fan extends model.agents.Led {


    @Override
    public String getId() {
        return "FAN";
    }

    @Override
    public Message getMessage() {
        return null;
    }


    @Override
    public void setup(){
        RegisterUtils.register(this,getId(),getAID());


        try{
            Thread.sleep(300);
        }catch(Exception e){}


    Map mapLow = new HashMap<String,String>();
        mapLow.put("agentType", AgentType.PIR.name());
        mapLow.put("powerStatus", PowerStatus.HIGH.name());

        Map mapHigh = new HashMap<String,String>();
        mapHigh.put("agentType", AgentType.PIR.name());
        mapHigh.put("powerStatus", PowerStatus.LOW.name());

        addBehaviour(new LedBehavior(RaspiPin.GPIO_04,mapLow,mapHigh));

    }
}
