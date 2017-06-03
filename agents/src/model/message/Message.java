package model.message;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by jlarque on 26/05/2017.
 */
public class Message implements Serializable {


    private Map<String,String> map;

    public  Message(Map<String,String> map) {
        this.map = map;
    }



    public Map<String,String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}
