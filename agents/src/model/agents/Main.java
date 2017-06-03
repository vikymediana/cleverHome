package model.agents;

/**
 * Created by victoria on 3/06/17.
 */
public class Main {
    public static void main (String[] args) {
        ItemDAO dao = new ItemDAO();
        dao.updateStatus("move_sensor", "LOW");
    }
}
