package model.behavior;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import model.database.ItemDAO;
import model.message.Message;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Javi on 10/06/2017.
 */
public class TemperatureBehaviuor extends Behaviour {

    private final ItemDAO itemDAO  = new ItemDAO();


    @Override
    public void action() {
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
            }
            try {
                Process p = Runtime.getRuntime().exec("sudo python AdafruitDHT.py 11 4");
                InputStream inputStream = p.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    itemDAO.updateStatus("temperature_sensor",line.substring(5, 9));
                }
            } catch (Exception e) {
            }
        }

    }

    @Override
    public boolean done() {
        return false;
    }

}
