package model.behavior;

import com.pi4j.io.gpio.*;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import model.message.Message;
import utils.MessageUtils;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Javi on 03/06/2017.
 */
public class LedBehavior extends SimpleBehaviour {


    Pin gpioPin;
    final Map<String, Class< ? extends Serializable>> fieldsToLowLed;
    final Map<String, Class< ? extends Serializable>> fieldsToHigLed;

    final GpioController gpioController = GpioFactory.getInstance();
    final GpioPinDigitalOutput pin = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_08, "MyLED", PinState.LOW); // FIXME mismo identificador para todos

    public LedBehavior(Pin gpioPin, Map<String, Class< ? extends Serializable>> fieldsToLowLed, Map<String, Class< ? extends Serializable>> fieldsToHigLed) {
        this.gpioPin = gpioPin;
        this.fieldsToLowLed = fieldsToLowLed;
        this.fieldsToHigLed = fieldsToHigLed;
    }


    @Override
    public void action() {
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

    public boolean compareToLowLed(Message message) {

    return MessageUtils.compare(fieldsToLowLed, message);

    }
    public boolean compareToHighLed(Message message) {
        return MessageUtils.compare(fieldsToHigLed, message);
    }

}