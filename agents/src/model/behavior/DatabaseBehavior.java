package model.behavior;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import model.database.ItemDAO;
import model.message.Message;

/**
 * Created by victoria on 03/06/2017.
 */
public class DatabaseBehavior extends SimpleBehaviour {

    private final ItemDAO itemDAO;

    public DatabaseBehavior() {
        itemDAO = new ItemDAO();
    }


    @Override
    public void action() {
        while (true) {
            try {
                ACLMessage message = getAgent().blockingReceive();
                if (message != null) {
                    if (message.getContentObject() != null && message.getContentObject() instanceof Message) {
                        Message messageContent = (Message)message.getContentObject();
                        if (compareToUpdateStatus(messageContent)) {
                            String itemId = messageContent.getMap().get("agentType");
                            String newStatus = messageContent.getMap().get("powerStatus");
                            itemDAO.updateStatus(itemId, newStatus);
                        }
                    }
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean done() {
        return false;
    }

    public Boolean compareToUpdateStatus(Message message) {
        return message.getMap().containsKey("agentType") && message.getMap().containsKey("powerStatus");
    }

}
