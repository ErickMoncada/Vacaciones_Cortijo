package Clases;

import app.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * @ErickMoncada Clase para ejecutar acciones de Crud en todas las pantallas
 */
public class AccionesCrud {

    //Funcion para Validar campos de los paneles Extra
    public boolean Validar(JTextField campo, String elemento) {
        if (campo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, elemento + " no puede estar en blanco", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    //Modificar datos de paneles extra donde solo se tiene que modifica el nombre de un dato a la BD por medio de Procedimientos Almacenados
    public boolean Modificar(JTextField txtdato, JTextField txtID, String exec) {
        String dato = txtdato.getText().trim();
        String id = txtID.getText();
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(exec);
            ps.setString(1, id);
            ps.setString(2, dato);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Actualizado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ups! " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    //Eliminar datos a la BD por medio de Procedimientos Almacenados
    //se recibe el compo de texto donde esta el ID y el comando a ejecutar en sql
    public boolean Eliminar(JTextField txtID, String exec) {
        int option = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el registro?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            // El usuario seleccionó "Sí"
            String id = txtID.getText();
            try {
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(exec);
                ps.setString(1, id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Registro Eliminado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                return true;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Ups! " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            return false;
        }
    }

    /*Cargar datos a los textfield de los paneles Extra cuando se preciona click en una celda
    se Recibe:
    tblCentro= tabla donde se carga los datos del panel requerido
    exec= el comando de sql que se va ejecutar para obtener los datos
     ID= Nombre del TextField del ID
    campo=Nombre del TextField del campo
    ExecCampo= el nombre de la columna del Nombre del Campo en el comando que se va ejecutar para establecerlo al textfield de campo
    ExecCampo= el nombre de la columna del ID en el comando que se va ejecutar para establecerlo al textfield de ID
     */
    public boolean CargarDatoClick(JTable tblCentro, String exec, String ExecCampo, String ExecId, JTextField campo, JTextField ID) {
        try {
            int fila = tblCentro.getSelectedRow();
            String dato = tblCentro.getValueAt(fila, 0).toString();
            PreparedStatement ps;
            ResultSet rs;
            Connection con = Conexion.getConexion();
            ps = con.prepareStatement(exec);
            ps.setString(1, dato);
            rs = ps.executeQuery();
            while (rs.next()) {
                campo.setText(rs.getString(ExecCampo));
                ID.setText(rs.getString(ExecId));
            }
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }

    }

    //Funcion principal para Guardar o Modificar los datos de todos los cruds del app y 
    //se recibe un arreglo de objetos donde estan todos los datos a ingresar al comando de sql ; Tambien recibe el comando de sql
    public boolean Guardar_Modificar(Object[] datos, String exec) {
        int longitud = datos.length;
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(exec);
            for (int i = 0; i < longitud; i++) {
                if (datos[i] instanceof String) {
                    ps.setString(i + 1, (String) datos[i]);
                } else if (datos[i] instanceof Integer) {
                    ps.setInt(i + 1, (Integer) datos[i]);
                } else if (datos[i] instanceof Double) {
                    ps.setDouble(i + 1, (Double) datos[i]);
                }
            }
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro guardado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ups! " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    //Funcion principal para Guardar o Modificar los datos de todos los cruds del app y 
    //se recibe un arreglo de objetos donde estan todos los datos a ingresar al comando de sql ; Tambien recibe el comando de sql
    public boolean Guardar_ModificarSinAviso(Object[] datos, String exec) {
        int longitud = datos.length;
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(exec);
            for (int i = 0; i < longitud; i++) {
                if (datos[i] instanceof String) {
                    ps.setString(i + 1, (String) datos[i]);
                } else if (datos[i] instanceof Integer) {
                    ps.setInt(i + 1, (Integer) datos[i]);
                } else if (datos[i] instanceof Double) {
                    ps.setDouble(i + 1, (Double) datos[i]);
                }
            }
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ups! " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    //seleccionar elemento de las tablas a las que se les de click en los paneles que no son Extra 
    //se recibe  la tabla de donde proceden los datos , el comando de sql para hacer el SELECT con WHERE y el nombre de la columna de la tabla con el ID
    public ResultSet Seleccion(JTable tabla, String exec, String nombreColumna) {
        try {
            int fila = tabla.getSelectedRow();
            int indiceColumna = tabla.getColumnModel().getColumnIndex(nombreColumna);
            String id = tabla.getValueAt(fila, indiceColumna).toString();
            PreparedStatement ps;
            ResultSet rs;
            Connection con = Conexion.getConexion();
            ps = con.prepareStatement(exec);
            ps.setString(1, id);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return null;
        }
    }
     public ResultSet ResultSetDeQuery( String exec,String id) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            Connection con = Conexion.getConexion();
            ps = con.prepareStatement(exec);
            ps.setString(1, id);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return null;
        }
    }
     
       public ResultSet SeleccionNormal(String exec) {
        try {
            PreparedStatement ps;
            ResultSet rs;
            Connection con = Conexion.getConexion();
            ps = con.prepareStatement(exec);
            rs = ps.executeQuery();
            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return null;
        }
    }
}
