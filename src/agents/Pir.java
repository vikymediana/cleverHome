package agents;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import message.Message;

/**
 * Created by jlarque on 26/05/2017.
 */
public class Pir extends Agent {

    private AID ledAgent;
    
    /**
     * Created by jlarque on 26/05/2017.
     */
    public class BehaviourPir extends SimpleBehaviour {
        @Override
        public void action() {
            final GpioController gpioSensor = GpioFactory.getInstance();
            final GpioPinDigitalInput sensor = gpioSensor.provisionDigitalInputPin(RaspiPin.GPIO_00, PinPullResistance.PULL_DOWN);

            sensor.addListener(new GpioPinListenerDigital() {
                @Override
                public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {


                    ACLMessage message = new ACLMessage(ACLMessage.REQUEST);
                    message.setSender(getAID());
                    message.setLanguage("Castellano");
                    message.addReceiver(ledAgent);
                    try {
                        if (event.getState().isHigh()) {
                            System.out.println("Motion Detected!");
                            message.setContentObject(new Message(Message.Status.ON));
                        }

                        if (event.getState().isLow()) {
                            System.out.println("All is quiet...");
                            message.setContentObject(new Message(Message.Status.OFF));
                        }
                    } catch (Exception e) {
                        System.out.println("ERROR PIR");
                    }
			try {
		     		System.out.println("Status enviado: " + ((Message)message.getContentObject()).getStatus());
			}catch(Exception e) {}
                       send(message);
                }

            });


            while (true) {
            }
        }

        @Override
        public boolean done() {
            return false;
        }
    }

    @Override
    public void setup(){


	try{
		Thread.sleep(100);
	}catch(Exception e){}
        
	  System.out.println("Salgo del sleep");

         ServiceDescription servicio = new ServiceDescription();
            servicio.setType("iot");

            // Plantilla de descripcion que busca el agente
            DFAgentDescription descripcion = new DFAgentDescription();
            descripcion.addLanguages("Castellano");

            // Servicio que busca el agente
            descripcion.addServices(servicio);

            
            try {
                DFAgentDescription[] resultados = DFService.search(this, descripcion);
                if (resultados.length > 0) {
                   DFAgentDescription agDescription = resultados[0];
                   ledAgent = agDescription.getName();
                   
                } else {
                    System.out.println("No encontrado");           
                }
            } catch (Exception e) {
                System.out.println("error al buscar el juego: " + e.getMessage());
            }
        
        addBehaviour(new BehaviourPir());
    }
}
