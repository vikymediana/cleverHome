package agents;

import jade.core.Agent;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by jlarque on 26/05/2017.
 */
public class Temperature extends Agent {

    static {
        String architecture = System.getProperty("os.arch");
        String library = String.format("/libdht11-%s.so", architecture);
        try (InputStream is = Temperature.class.getResourceAsStream(library)) {
            Path path = File.createTempFile("libdht11", "so").toPath();
            Files.copy(is, path, StandardCopyOption.REPLACE_EXISTING);
            System.load(path.toAbsolutePath().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private int gpio;
    private Optional<Integer> temperature;
    private Optional<Integer> humidity;
    private Optional<LocalDateTime> date;
}
