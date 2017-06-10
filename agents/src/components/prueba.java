import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by jlarque on 28/05/2017.
 */


public class prueba {

    public static void main(String[] args) {

        try {
            Process p = Runtime.getRuntime().exec("sudo python AdafruitDHT.py 11 4");
            InputStream inputStream = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line.substring(5,9));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
