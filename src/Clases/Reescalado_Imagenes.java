package Clases;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 * @ErickMoncada Clase para cambiar tamaño de Imagenes
 */
public class Reescalado_Imagenes {
    //Funcion para reescalar icono a las medidas que se le asigne
    public ImageIcon IconoTextoMenu(int nuevoAncho, int nuevoAlto, String ruta) {
        // Obtener la imagen del icono
        Image imagenOriginal = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource(ruta));
        // Escalar la imagen al nuevo tamaño deseado
        Image imagenEscalada = imagenOriginal.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
        // Crear un nuevo ImageIcon a partir de la imagen escalada
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        return iconoEscalado;
    }
    //agregar icono de JFrame
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img1/Pollos-El-Cortijo.png"));
        return retValue;
    }
}
