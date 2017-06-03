package agents;

import com.pi4j.io.gpio.RaspiPin;
import jade.core.AID;
import model.behavior.PirBehavior;
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

        System.out.print("PIR SALE");
    }

    public List<AID> getAgentsAid() {
        if (agentsAid == null) {
            agentsAid = RegisterUtils.search(this);
        }
        return agentsAid;
    }
}
