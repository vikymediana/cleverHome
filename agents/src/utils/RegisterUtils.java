package utils;

import agents.AgentsDescription;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Javi on 02/06/2017.
 */
public class RegisterUtils {

    public static void register(Agent agent , AgentsDescription agentDescription, AID aid) {
        // Registro del servicio en las paginas amarillas.
        ServiceDescription servicio = new ServiceDescription();
        servicio.setType("iot");
        servicio.setName(agentDescription.getId());

        // Descripcion del agente
        DFAgentDescription descripcion = new DFAgentDescription();
        descripcion.setName(aid);
        descripcion.addLanguages("Castellano");

        // Anade dicho servicio a la lista de servicios de la descripcion del agente
        descripcion.addServices(servicio);

        try {
            DFService.register(agent, descripcion);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }

    public static List<AID> search(Agent agent) {
        ServiceDescription servicio = new ServiceDescription();
        servicio.setType("iot");
        List<AID> listAid = new ArrayList<>();

        // Plantilla de descripcion que busca el agente
        DFAgentDescription descripcion = new DFAgentDescription();
        descripcion.addLanguages("Castellano");

        // Servicio que busca el agente
        descripcion.addServices(servicio);


        try {
            DFAgentDescription[] resultados = DFService.search(agent, descripcion);
            for (int i = 0 ; i < resultados.length ; i++ )  {
                DFAgentDescription agDescription = resultados[i];
                listAid.add(agDescription.getName());
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        return listAid;
    }
}
