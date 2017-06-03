package agents;

/**
 * Created by Javi on 02/06/2017.
 */
public enum AgentsDescription {
    LED1 {
        @Override
        public InternalStatusAgent.AgentType getType() {
            return InternalStatusAgent.AgentType.LED;
        }

    }, PIR1 {
        @Override
        public InternalStatusAgent.AgentType getType() {
            return InternalStatusAgent.AgentType.PIR;
        }

    }, DB1 {
        @Override
        public InternalStatusAgent.AgentType getType() {
            return InternalStatusAgent.AgentType.DB;
        }
    };

    public abstract InternalStatusAgent.AgentType getType();
    public  String getId(){
        return toString();
    }
    public InternalStatusAgent getDefaultInternalStatus() {
        return new InternalStatusAgent(getId(),getType());
    }




}
