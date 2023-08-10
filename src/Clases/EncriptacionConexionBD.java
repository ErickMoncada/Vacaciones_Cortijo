package Clases;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @ErickMoncada Clase Para desencriptar cadena de conexión
 */
public class EncriptacionConexionBD {

    // Clave de  desencriptación
    String LLAVE = "CortijoVacaciones2023ErickMoncada";
    private SecretKeySpec CrearCalve(String llave) {
        try {
            byte[] cadena = llave.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            cadena = md.digest(cadena);
            cadena = Arrays.copyOf(cadena, 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(cadena, "AES");
            return secretKeySpec;
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            return null;
        }

    }

    // Des-encriptación
     public String Desencriptar(String desencriptar) {
        try {
            SecretKeySpec secretKeySpec = CrearCalve(LLAVE);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] cadena = Base64.getDecoder().decode(desencriptar);
            byte[] desencriptacion = cipher.doFinal(cadena);
            String cadena_desencriptada = new String(desencriptacion);
            return cadena_desencriptada;
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            return "";
        }
    }
    
}
