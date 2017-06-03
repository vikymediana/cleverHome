package agents;

import com.pi4j.io.gpio.*;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import model.message.Message;

/**
 * Created by Javi on 02/06/2017.
 */


    public class behabiour extends SimpleBehaviour {

        public behabiour(int i, int k) {

        }
        @Override
        public void action() {
            final GpioController gpio = GpioFactory.getInstance();
            final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "MyLED", PinState.LOW);

            while (true) {
                try {
                    ACLMessage message = this.getAgent().blockingReceive();
                    if (message != null) {
                        if (message.getContentObject() != null
                                && message.getContentObject() instanceof Message) {
                            Message mesageContent = (Message)message.getContentObject();

                            if (mesageContent.getStatus().getAgentType() == InternalStatusAgent.AgentType.PIR
                                    && mesageContent.getStatus().getStatus() == InternalStatusAgent.Status.OFF){
                                pin.low();
                            }else if (mesageContent.getStatus().getAgentType() == InternalStatusAgent.AgentType.PIR
                                    && mesageContent.getStatus().getStatus() == InternalStatusAgent.Status.ON) {
                                pin.high();
                            }
                        }
                    }
                }
                catch(Exception e) {
                    System.out.print("ERROR");
                }
            }
        }

        @Override
        public boolean done() {
            return false;
        }
    }
