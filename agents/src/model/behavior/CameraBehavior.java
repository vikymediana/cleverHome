package model.behavior;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import model.AgentType;
import model.PowerStatus;
import model.message.Message;

/**
 * Created by Javi on 04/06/2017.
 */
public class CameraBehavior extends SimpleBehaviour {

    @Override
    public void action() {
        while (true) {
            try {
                ACLMessage message = getAgent().blockingReceive();
                if (message != null) {
                    if (message.getContentObject() != null
                            && message.getContentObject() instanceof Message) {
                        Message mesageContent = (Message)message.getContentObject();

                        if (mesageContent.getMap().containsKey("agentType")
                                && mesageContent.getMap().get("agentType").equals(AgentType.PIR.name())
                                && mesageContent.getMap().get("powerStatus").equals(PowerStatus.HIGH.name())){
                            Process p1 = Runtime.getRuntime().exec("python Nombre_del_metodo_tomar_foto");
                            Process p2 = Runtime.getRuntime().exec("python Nombre_del_metodo_mandar_notificacion");
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
}
