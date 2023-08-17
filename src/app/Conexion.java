package app;

import Clases.EncriptacionConexionBD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 * @ErickMoncada Clase para la conexion con la base de datos
 */
public class Conexion {

    public static Connection getConexion() {
        //obtenemos la cadena encriptada de properties
        Properties properties = ConfigReader.getProperties();
        if (properties != null) {
            //se desencripta con la llave correspondiente desde el archivo config.propertis a la propiedad c
            EncriptacionConexionBD encriptacion = new EncriptacionConexionBD();
            String CadenaConexion = encriptacion.Desencriptar(properties.getProperty("c"));
            //se separa la cadena por comas
            String[] propiedades = CadenaConexion.split(",");
            // Creamos variables para almacenar los valores
            String direccion = null;
            String database = null;
            String username = null;
            String password = null;

            // Iteramos sobre el array para obtener los valores de cada propiedad
            for (String propiedad : propiedades) {
                String[] partes = propiedad.split("=");
                if (partes.length >= 2) {
                    String clave = partes[0].trim();
                    String valor = partes[1].trim();
                    // Asignamos el valor a la variable correspondiente según la clave
                    switch (clave) {
                        case "db.direccion":
                            direccion = valor;
                            break;
                        case "db.database":
                            database = valor;
                            break;
                        case "db.username":
                            username = valor;
                            break;
                        case "db.password":
                            password = valor;
                            break;
                        default:
                            // Manejar caso inesperado
                            break;
                    }
                }
            }
            String url = "jdbc:sqlserver:" + direccion + ";database=" + database + ";user=" + username + ";password=" + password + ";encrypt=true;trustServerCertificate=true";
            try {
                Connection con = DriverManager.getConnection(url);
                return con;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "No se pudo Contactar con la base de datos, Solicite ayuda a su superior", "Vacaciones Cortijo", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } else {
            return null;
        }

    }

}
