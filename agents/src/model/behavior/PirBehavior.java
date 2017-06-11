package model.behavior;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import model.AgentType;
import model.PowerStatus;
import model.database.ItemDAO;
import model.message.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Javi on 03/06/2017.
 */
public class PirBehavior extends Behaviour{

    private Pin pin;
    final List<AID> agentsAid;
    final GpioController gpioSensor = GpioFactory.getInstance();
    final GpioPinDigitalInput sensor = gpioSensor.provisionDigitalInputPin(RaspiPin.GPIO_00, PinPullResistance.PULL_DOWN);


    public PirBehavior (List<AID> agentsAid, Pin pin) {
        this.agentsAid = agentsAid;
        this.pin = pin;
    }

    @Override
    public void action() {


        sensor.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {


                ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
                message.setSender(getAgent().getAID());
                message.setLanguage("Castellano");

                for (AID aid : agentsAid) {
                    message.addReceiver(aid);
                }

                try {
                    if (event.getState().isHigh()) {
                        System.out.println("Motion Detected!");
                        Map<String,String> map = new HashMap<String, String>();
                        map.put("agentType", AgentType.PIR.name());
                        map.put("powerStatus", PowerStatus.HIGH.name());
                        message.setContentObject(new Message(map));
                        Process p = Runtime.getRuntime().exec("sudo python /home/pi/Documents/cleverHome/agents/src/components/sendNotification.py");
                    }

                    else if (event.getState().isLow()) {
                        System.out.println("All is quiet...");
                        Map<String,String> map = new HashMap<String, String>();
                        map.put("agentType", AgentType.PIR.name());
                        map.put("powerStatus", PowerStatus.LOW.name());
                        message.setContentObject(new Message(map));
                    }
                } catch (Exception e) {
                    System.out.println("ERROR PIR");
                }
                getAgent().send(message);

//                // Mandar a agente de base de datos
//                ACLMessage dbMessage = new ACLMessage(ACLMessage.REQUEST);
//                dbMessage.setSender(getAgent().getAID());
//                dbMessage.setLanguage("Castellano");
//
//                for (AID aid : agentsAid) {
//                    dbMessage.addReceiver(aid);
//                }
//
//                try {
//                    if (event.getState().isHigh()) {
//                        Map<String,String> map = new HashMap<>();
//                        map.put("messageType", "updateStatus");
//                        map.put("itemId", "move_sensor");
//                        map.put("newValue", PowerStatus.HIGH.name());
//                        message.setContentObject(new Message(map));
//                    }
//
//                    else if (event.getState().isLow()) {
//                        System.out.println("All is quiet...");
//                        Map<String,String> map = new HashMap<>();
//                        map.put("messageType", "updateStatus");
//                        map.put("itemId", "move_sensor");
//                        map.put("newValue", PowerStatus.LOW.name());
//                        message.setContentObject(new Message(map));
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                getAgent().send(message);
            }

        });


        while (true) {
        }
    }

    @Override
    public boolean done() {
        return false;
    }

    public List<AID> getAgentsAid() {
        return this.getAgentsAid();
    }
}
