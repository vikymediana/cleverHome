package agents;

import com.pi4j.io.gpio.RaspiPin;
import jade.core.AID;
import model.AgentType;
import model.agents.Agent;
import model.behavior.CameraBehavior;
import model.message.Message;
import utils.RegisterUtils;

import java.io.IOException;
import java.util.List;

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

    @Override
    public void setup(){

        try {
            Runtime.getRuntime().exec("raspistill --nopreview -w 448 -h 336 -q 5 -o /tmp/stream/pic.jpg -tl 1000 -t 9999999 -th 0:0:0");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
