package model.behavior;

import com.pi4j.io.gpio.*;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import model.message.Message;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Javi on 03/06/2017.
 */
public class LedBehavior extends SimpleBehaviour {


    Pin gpioPin;
    final Map<String, Class< ? extends Serializable>> fieldsToLowLed;
    final Map<String, Class< ? extends Serializable>> fieldsToHighLed;

    final GpioController gpioController = GpioFactory.getInstance();


    public LedBehavior(Pin gpioPin, Map<String, Class< ? extends Serializable>> fieldsToLowLed, Map<String, Class< ? extends Serializable>> fieldsToHighLed) {
        this.gpioPin = gpioPin;
        this.fieldsToLowLed = fieldsToLowLed;
        this.fieldsToHighLed = fieldsToHighLed;
    }


    @Override
    public void action() {

        GpioPinDigitalOutput pin = gpioController.provisionDigitalOutputPin(gpioPin, "MyLED" + getAgent().getAID() , PinState.LOW); // FIXME mismo identificador para todos

        while (true) {
            try {
                ACLMessage message = getAgent().blockingReceive();
                if (message != null) {
                    if (message.getContentObject() != null
                            && message.getContentObject() instanceof Message) {
                        Message mesageContent = (Message)message.getContentObject();

                        if (compareToLowLed(mesageContent)){
                            pin.low();
                        }else if (compareToHighLed(mesageContent)) {
                            pin.high();
                        }
                    }
                }
            }
            catch(Exception e) {
                System.out.print("--- ERROR ---");
            }
        }
    }

    @Override
    public boolean done() {
        return false;
    }

    public Boolean compareToLowLed(Message message) {

        Iterator<Map.Entry<String, Class<? extends Serializable>>> iterator = fieldsToLowLed.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Class<? extends Serializable>> entry = iterator.next();
            if ( message.getMap().containsKey(entry.getKey()) && message.getMap().get(entry.getKey()).equals(entry.getValue()) == false ){
                return false;
            }
        }
        return true;
    }
    public Boolean compareToHighLed(Message message) {

        Iterator<Map.Entry<String, Class<? extends Serializable>>> iterator = fieldsToHighLed.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Class<? extends Serializable>> entry = iterator.next();
            if ( message.getMap().containsKey(entry.getKey()) && message.getMap().get(entry.getKey()).equals(entry.getValue()) == false ){
                return false;
            }
        }
        return true;
    }

}
