package model.agents;

import model.AgentType;
import model.PowerStatus;

/**
 * Created by Javi on 03/06/2017.
 */
public abstract class Led extends Agent {

    PowerStatus powerStatus  = PowerStatus.LOW;

    public PowerStatus getPowerStatus() {
        return powerStatus;
    }

    public void setPowerStatus(PowerStatus powerStatus) {
        this.powerStatus = powerStatus;
    }

    @Override
    public AgentType getAgentType() {
        return AgentType.LED;
    }

}
