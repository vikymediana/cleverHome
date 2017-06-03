package agents;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import model.behavior.PirBehavior;
import model.message.Message;
import utils.RegisterUtils;

import java.util.List;

/**
 * Created by jlarque on 26/05/2017.
 */
public class Pir extends model.agents.Pir {


    private List<AID> agentsAid;

    @Override
    public String getId() {
        return "PIR1";
    }


    @Override
    public void setup(){

	try{
		Thread.sleep(300);
	}catch(Exception e){}
        addBehaviour(new PirBehavior(getAgentsAid(), RaspiPin.GPIO_00));
    }

    public List<AID> getAgentsAid() {
        if (agentsAid == null) {
            agentsAid = RegisterUtils.search(this);
        }
        return agentsAid;
    }
}
