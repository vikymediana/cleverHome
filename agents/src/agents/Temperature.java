package agents;

import com.pi4j.io.gpio.RaspiPin;
import jade.core.Agent;
import model.AgentType;
import model.behavior.PirBehavior;
import model.behavior.TemperatureBehaviuor;
import model.message.Message;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by jlarque on 26/05/2017.
 */
public class Temperature extends model.agents.Agent{


    @Override
    public AgentType getAgentType() {
        return AgentType.TEMPERATURE;
    }

    @Override
    public String getId() {
        return "temperature";
    }

    @Override
    public Message getMessage() {
        return null;
    }

    @Override
    public void setup(){

        try{
            Thread.sleep(300);
        }catch(Exception e){}
        addBehaviour(new TemperatureBehaviuor());
    }

}
