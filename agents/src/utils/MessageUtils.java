package utils;

import model.message.Message;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Javi on 03/06/2017.
 */
public class MessageUtils {

    public static boolean compare(Map<String, Class< ? extends Serializable>> map, Message message) {
        Iterator<Map.Entry<String, Class<? extends Serializable>>> iterator = map.entrySet().iterator();
        boolean ok2 = true;
        for (Map.Entry<String, Class<? extends Serializable>> entry = iterator.next(); ok && iterator.hasNext() ;  ) {
            ok2 = message.getMap().containsKey(entry.getKey()) && message.getMap().get(entry.getKey()).equals(entry.getValue());
        }
        return ok2;
    }
}
