package agents;

import jade.core.Agent;
import utils.RegisterUtils;

/**
 * Created by Javi on 02/06/2017.
 */
public class DbAgent extends Agent {

    AgentsDescription agent = AgentsDescription.DB1;



    @Override
    public void setup(){

        RegisterUtils.register(this,agent,getAID());
        addBehaviour(new behabiour(1,1));

    }
}
