package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 * @ErickMoncada Clase para leer la conexion a la base de datos que esta encriptada en el config.properties
 */
public class ConfigReader {

    public static Properties getProperties() {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(getConfigFilePath())) {
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
             JOptionPane.showMessageDialog(null, "no se pudo encontrar el archivo de Configuracion a la conexion de base de datos", "Vacaciones Cortijo",JOptionPane.ERROR_MESSAGE);
             return null;
        }
    }

    private static String getConfigFilePath() {
        String currentDir = System.getProperty("user.dir");
        return currentDir + "/config.properties" ;       
    }
}
