package Clases;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * @ErickMoncada Clase Para envio de correos electronicos
 */
public class EnvioCorreos {
    // private Properties mProperties;

    private Session mSession;
    private MimeMessage mCorreo;

    public int createEmail(Object[] datos) {
        Properties mProperties = new Properties();
        // Simple mail transfer protocol
        
        //comprobar si el arreglo de datos esta completo
        boolean C = true;
        for (int x = 0; x <= 10; x++) {
            if (datos[x] == null) {
                C = false;
            }
        }
        if (C==true) {
            mProperties.put("mail.smtp.host", (String) datos[0]);
            mProperties.put("mail.smtp.ssl.trust", (String) datos[1]);
            mProperties.setProperty("mail.smtp.starttls.enable", (String) datos[2]);
            mProperties.setProperty("mail.smtp.port", (String) datos[3]);
            mProperties.setProperty("mail.smtp.user", (String) datos[4]);
            mProperties.setProperty("mail.smtp.ssl.protocols", (String) datos[5]);
            mProperties.setProperty("mail.smtp.auth", (String) datos[6]);
            mSession = Session.getDefaultInstance(mProperties);
            try {
                mCorreo = new MimeMessage(mSession);
                mCorreo.setFrom(new InternetAddress((String) datos[4]));
                mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress((String) datos[7]));
                mCorreo.setSubject((String) datos[8]);
                mCorreo.setText((String) datos[9], "UTF-8");
            } catch (AddressException ex) {
                Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Transport mTransport = mSession.getTransport("smtp");
                mTransport.connect((String) datos[4], (String) datos[10]);
                mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
                mTransport.close();

            } catch (NoSuchProviderException ex) {
                Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
              JOptionPane.showMessageDialog(null, "No se pudo enviar ningún correo porque el servidor de correo está mal configurado", "Envió de Correo", JOptionPane.ERROR_MESSAGE);
              return 0;
        }
        return 1;
    }
}
