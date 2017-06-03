package agents;

import com.pi4j.io.gpio.RaspiPin;
import model.AgentType;
import model.PowerStatus;
import model.behavior.DatabaseBehavior;
import model.behavior.LedBehavior;
import model.message.Message;
import utils.RegisterUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by victoria on 3/06/17.
 */
public class DatabaseAgent extends model.agents.DatabaseAgent {

    @Override
    public String getId() {
        return "database";
    }

    @Override
    public Message getMessage() {
        return null;
    }

    @Override
    public void setup(){
        RegisterUtils.register(this,getId(),getAID());
        addBehaviour(new DatabaseBehavior());

    }
}
