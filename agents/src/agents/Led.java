package agents;

import com.pi4j.io.gpio.*;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import message.Message;
import model.InternalStatusAgent;

/**
 * Created by jlarque on 26/05/2017.
 */
public class Led extends Agent {

    /**
     * Created by jlarque on 26/05/2017.
     */
    public class BehaviourLed extends SimpleBehaviour {
        @Override
        public void action() {
            final GpioController gpio = GpioFactory.getInstance();
            final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_08, "MyLED", PinState.LOW);

            while (true) {
                try {
                    ACLMessage message = blockingReceive();
                    if (message != null) {
                        if (message.getContentObject() != null
                                && message.getContentObject() instanceof  Message) {
                            Message mesageContent = (Message)message.getContentObject();

                            if (mesageContent.getStatus().getStatus() == InternalStatusAgent.Status.OFF){
				                System.out.println("LED: LLEGA MENSAJE : OFF");
                                pin.low();
                            }else {
                                System.out.println("LED: LLEGA MENSAJE : ON");
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

    @Override
    public void setup(){
        
        // Registro del servicio en las paginas amarillas.
        ServiceDescription servicio = new ServiceDescription();
        servicio.setType("iot");
        servicio.setName("Led");
        
        // Descripcion del agente
        DFAgentDescription descripcion = new DFAgentDescription();
        descripcion.setName(getAID());
        descripcion.addLanguages("Castellano");
        
        // Anade dicho servicio a la lista de servicios de la descripcion del agente
        descripcion.addServices(servicio);
        
        try {
            DFService.register(this, descripcion);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        
        addBehaviour(new BehaviourLed());
    }
}
