package Clases;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 *
 * @ErickMoncada clase para mostrar y cambiar estilo de campos cuando aparece un
 * error
 */
public class validaciones {

    //se establece que un textfield esta correcto se recive el TextField para el color de fuente y el Label del error para ponerlo invisible
    public void TXTcorrecto(JTextField campo, JLabel msj) {
        campo.setForeground(Color.BLACK);
        msj.setVisible(false);
    }

    //establece que un textfield esta incorrecto se recive el TextField, el Label del error y un String para el mensaje del error
    public void TXTincorrecto(JTextField campo, JLabel msj, String MsjError) {
        campo.setForeground(Color.RED);
        msj.setText(MsjError);
        msj.setVisible(true);
    }

    //se establece un combobox correcto se recive el JComboBox que se esta validando y el Label del error
    public void CMBcorrecto(JComboBox campo, JLabel msj) {
        campo.setForeground(Color.BLACK);
        msj.setVisible(false);
    }

    //se establece que un combobox esta incorrecto se recive el JComboBox que se esta validando y el Label del error
    public void CMBincorrecto(JComboBox campo, JLabel msj, String MsjError) {
        campo.setForeground(Color.RED);
        msj.setText(MsjError);
        msj.setVisible(true);
    }

    // (general) se establece que un campo diferente a textbox o a label esta incorrecto manipulando solo su label de error
    public void GENIncorrecto(JLabel msj, String MsjError) {
        msj.setText(MsjError);
        msj.setVisible(true);
    }

    //se establece que un campo diferente a textbox o a label esta correcto manipulando solo su label de error
    public void GENcorrecto(JLabel msj) {
        msj.setVisible(false);
    }

    public void EntradaLinea(JTextField txtLinea, java.awt.event.KeyEvent evt) {
        // establecer parametros de entrada de teclado de la linea telefonica     
        int key = evt.getKeyChar();
        String text = txtLinea.getText();
        boolean numero = key >= 48 && key <= 57 || key == KeyEvent.VK_BACK_SPACE;
        boolean backSpace = (key == KeyEvent.VK_BACK_SPACE);
        if (text.length() == 9 || !numero) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
            //si son 4 caracteres y no es un retroceso se agrega un guion
        } else if (txtLinea.getText().length() == 4 && !backSpace) {
            txtLinea.setText(text + "-");
        }
    }

    public void EntradaDinero(JTextField txtNumerico, java.awt.event.KeyEvent evt) {
        // establecer parametros de entrada de teclado de campos de dinero 
        int key = evt.getKeyChar();
        //expresion regular que solo permite numeros, tecla de eliminar y un punto
        boolean numero = (key >= 48 && key <= 57) || (key == 46 && !txtNumerico.getText().contains(".")) || key == KeyEvent.VK_BACK_SPACE;
        if (txtNumerico.getText().length() >= 7 || !numero) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public void EntradaTextoNormal(JTextField txtTexto, java.awt.event.KeyEvent evt, int longitud) {
        // establecer parametros de entrada de teclado para letras y numeros
        int key = evt.getKeyChar();
        //perimite escribir solo letras , numeros , espacio y retroceso
        boolean letra = (key >= 65 && key <= 90) || (key >= 97 && key <= 122 || key >= 48 && key <= 57 || key >= 192 && key <= 255 || key == KeyEvent.VK_SPACE || key == KeyEvent.VK_BACK_SPACE);
        if (txtTexto.getText().length() == longitud || !letra) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public void EntradaSoloLetas(JTextField txtTexto, java.awt.event.KeyEvent evt, int longitud) {
        // establecer parametros de entrada de teclado para solo letras   
        int key = evt.getKeyChar();
        //perimite escribir solo letras ,espacio y retroceso
        boolean letra = (key >= 65 && key <= 90) || (key >= 97 && key <= 122 || key >= 192 && key <= 255 || key == KeyEvent.VK_SPACE || key == KeyEvent.VK_BACK_SPACE);
        if (txtTexto.getText().length() == longitud || !letra) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public void EntradaLetrasSinEspacios(JTextField txtTexto, java.awt.event.KeyEvent evt, int longitud) {
        // establecer parametros de entrada de teclado para solo letras   
        int key = evt.getKeyChar();
        //perimite escribir solo letras  y retroceso
        boolean letra = (key >= 65 && key <= 90) || (key >= 97 && key <= 122 || key >= 192 && key <= 255 || key == KeyEvent.VK_BACK_SPACE);
        if (txtTexto.getText().length() == longitud || !letra) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public void EntradaLetrasNumerosSinEspacios(JTextField txtTexto, java.awt.event.KeyEvent evt, int longitud) {
        // establecer parametros de entrada de teclado para solo letras   
        int key = evt.getKeyChar();
        //perimite escribir solo letras  y retroceso
        boolean letra = (key >= 65 && key <= 90) || (key >= 97 && key <= 122 || key >= 192 && key <= 255 || key >= 48 && key <= 57 || key == KeyEvent.VK_BACK_SPACE);
        if (txtTexto.getText().length() == longitud || !letra) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public void EntradaNumeros(JTextField txtTexto, java.awt.event.KeyEvent evt, int longitud) {
        // establecer parametros de entrada de teclado para solo numeros   
        int key = evt.getKeyChar();
        //perimite escribir solo  numeros y retroceso
        boolean letra = (key >= 48 && key <= 57 || key == KeyEvent.VK_BACK_SPACE);
        if (txtTexto.getText().length() == longitud || !letra) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public void EntradaNumeroGuion(JTextField txtTexto, java.awt.event.KeyEvent evt, int longitud) {
        // establecer parametros de entrada de teclado para solo numeros   
        int key = evt.getKeyChar();
        //perimite escribir solo  numeros y retroceso
        boolean letra = (key >= 48 && key <= 57 || key == 45 || key == KeyEvent.VK_BACK_SPACE);
        if (txtTexto.getText().length() == longitud || !letra) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public void EntradaLetrasNumeroGuion(JTextField txtTexto, java.awt.event.KeyEvent evt, int longitud) {
        // establecer parametros de entrada de teclado para letras y numeros y guiones
        int key = evt.getKeyChar();
        //perimite escribir solo letras , numeros y retroceso
        boolean letra = (key >= 65 && key <= 90) || (key >= 97 && key <= 122 || key >= 192 && key <= 255 || key >= 48 && key <= 57 || key == 45 || key == KeyEvent.VK_SPACE || key == KeyEvent.VK_BACK_SPACE);
        if (txtTexto.getText().length() == longitud || !letra) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public void EntradaCorreo(JTextField txtCorreo, java.awt.event.KeyEvent evt) {
        int key = evt.getKeyChar();
        boolean TeclaBuscar = (key >= 65 && key <= 90)
                || // Letras mayúsculas
                (key >= 97 && key <= 122)
                || // Letras minúsculas
                (key >= 48 && key <= 57)
                || // Números
                (key == KeyEvent.VK_BACK_SPACE)
                || // Retroceso
                (key == 64 && !txtCorreo.getText().contains("@"))
                || // Símbolo @
                (key == 46 && !txtCorreo.getText().contains(".")); // Punto
        if (txtCorreo.getText().length() == 80 || !TeclaBuscar) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public void asignarEventosMouse(JLabel label) {
        //funcion para asignar los eventos a los mensajes de obligatorio (para ahorrar lineas de codigo)
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                //Mostrar mensaje de obligatorio cuando se pase el mause por encima
                label.setText("*Obligatorio");
                Font font = new Font("Dialog", Font.PLAIN, 11);
                label.setFont(font);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                //Quitar mensaje de obligatorio cuando se quite el mause por encima
                label.setText("*");
                Font font = new Font("Dialog", Font.BOLD, 18);
                label.setFont(font);
            }
        });
    }

    public void NegarPegado(JComponent campo) {
        //establece a los campos la denegacion de comandos como control + V y shift + INSERT
        campo.getInputMap().put(KeyStroke.getKeyStroke("control V"), "none");
        campo.getInputMap().put(KeyStroke.getKeyStroke("shift INSERT"), "none");
    }

    public int ValidarFechas(JDateChooser dateChooser, JLabel labelError) {
        //se trata de obtener la fecha y si no se puede genera un error
        String error;
        int valor1 = 1;
        if (dateChooser.getDate() != null) {
            Date date = dateChooser.getDate();
            long d = date.getTime();
            int fechamayor = date.compareTo(dateChooser.getMaxSelectableDate());
            int fechamenor = date.compareTo(dateChooser.getMinSelectableDate());
            //se compara la fecha para verificar que no pase el limite de fecha
            if (fechamayor > 0) {
                error = "La fecha seleccionada es mayor a la fecha permitida.";
                GENIncorrecto(labelError, error);
                valor1 = 0;
                //se compara la fecha para verificar que no sea menor al permitido
            } else if (fechamenor < 0) {
                error = "La fecha seleccionada es menor a la fecha permitida.";
                GENIncorrecto(labelError, error);
                valor1 = 0;
            }
        } else {
            error = "La fecha seleccionada no es válida ";
            GENIncorrecto(labelError, error);
            valor1 = 0;
        }
        return valor1;
    }

}
