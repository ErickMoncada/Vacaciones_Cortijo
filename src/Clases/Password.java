package Clases;

import app.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * @ErickMoncada Clase Para enviar correos con código o contraseñas
 */
public class Password {

    //se declara los datos del correo que envia el mensaje
    Object[] datos = new Object[11];

    private void datos() {
        //se cargan los datos de smtp desde la base de datos
        try {
            ResultSet rs;
            PreparedStatement ps;
            Connection con = Conexion.getConexion();
            ps = con.prepareStatement("SELECT * FROM VistaAdmin");
            rs = ps.executeQuery();
            if (rs.next()) {
                datos[0] = rs.getString("host");
                datos[1] = rs.getString("host");
                datos[2] = rs.getString("startls");
                datos[3] = rs.getString("puerto");
                datos[5] = rs.getString("sslProtocol");
                datos[6] = rs.getString("auth");
                datos[4] = rs.getString("correo");
                datos[10] = rs.getString("password");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Password.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int EnviarCodigo(String usuario) {
        //se trata de enviar el correo electronico con el código correspondiente
        int codigo = 0;
        try {
            ResultSet rs;
            PreparedStatement ps;
            Connection con = Conexion.getConexion();
            ps = con.prepareStatement("SELECT Usuario, Correo FROM VistaUsuariosApp WHERE Usuario = ? COLLATE Latin1_General_CS_AS");
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            //se comprueba si existe el usuario, de lo contrario se envia codigo 0 que significa que no encontro usuario
            if (rs.next()) {
                datos[7] = rs.getString("Correo");
                datos[8] = "Recuperacion de Contraseña";
                codigo = numeroAleatorio();
                datos[9] = "Tu codigo para recuperar la contraseña es: " + codigo + " sigue los pasos en la aplicacion";
                datos();
                EnvioCorreos envio = new EnvioCorreos();
                if(envio.createEmail(datos)==0){
                codigo=01;//codigo 01 servidor de correo mal configurado
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Password.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codigo;
    }

    public boolean EnviarPassword(String usuario, String password) {
        //se trata de enviar al correo del usuario su contraseña
        try {
            ResultSet rs;
            PreparedStatement ps;
            Connection con = Conexion.getConexion();
            ps = con.prepareStatement("SELECT Usuario, Correo FROM VistaUsuariosApp WHERE Usuario = ?");
            ps.setString(1, usuario);
            rs = ps.executeQuery();
            //se comprueba si existe el usuario, de lo contrario se envia codigo 0 que significa que no encontro usuario
            if (rs.next()) {
                datos[7] = rs.getString("Correo");
                datos[8] = "Usuario creado Telefonia Pollos Cortijo";
                datos[9] = "¡Se ha creado una cuenta para la gestión de telefonía del cortijo con tu dirección de correo electrónico! Tu contraseña generada es: '" + password + "' y tu usuario es: '" + usuario + "'. Por motivos de seguridad, te recomendamos cambiar esta contraseña. Puedes hacerlo fácilmente a través de la aplicación de gestión telefónica.";
                datos();
                EnvioCorreos envio = new EnvioCorreos();
               if(envio.createEmail(datos)==0){
                return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Password.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    private int numeroAleatorio() {
        Random random = new Random();
        int randomNumber = random.nextInt(90000) + 10000; // Genera un número aleatorio entre 10000 y 99999 (ambos inclusive)
        return randomNumber;
    }

    public String Encriptar(String textoOriginal) {
        //funicon para encriptar la contraseña con SHA-256
        try {
            // Obtener una instancia de MessageDigest con el algoritmo deseado (por ejemplo, SHA-256)
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            // Convertir el texto original a un arreglo de bytes
            byte[] bytesTextoOriginal = textoOriginal.getBytes();
            // Calcular el hash del arreglo de bytes
            byte[] hash = messageDigest.digest(bytesTextoOriginal);
            // Convertir el hash a una representación legible en hexadecimal
            StringBuilder hashHex = new StringBuilder();
            for (byte b : hash) {
                hashHex.append(String.format("%02x", b));
            }
            return hashHex.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return "error";
    }
}
