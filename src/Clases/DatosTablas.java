package Clases;

import app.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 * @ErickMoncada Clase Para caargar datos a tablas y combobox
 */
public class DatosTablas {

    //Funcion para Cargar los datos a las tablas que se requieran 
    //se recibe la tabla donde se van a cargar los datos y el comando de sql de donde se van a extraer
    public void CargarTabla(JTable tabla, String cmd) {
        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0);
        PreparedStatement ps;
        ResultSet rs;
        ResultSetMetaData rsmd;
        int columnas;
        try {
            Connection con = Conexion.getConexion();
            ps = con.prepareStatement(cmd);
            rs = ps.executeQuery();
            rsmd = rs.getMetaData();
            columnas = rsmd.getColumnCount();
            while (rs.next()) {
                Object[] fila = new Object[columnas];
                for (int indice = 0; indice < columnas; indice++) {
                    fila[indice] = rs.getObject(indice + 1);
                }
                modeloTabla.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    //Funcion de la clase DatoTablas para asignar datos a los combobox de las distintas pantallas
    //Se recibe el comando de sql, el campo del select que tiene los datos que se necesitan y el ComboBox donde se van a cargar los datos
    public void cargarComboBox(String cmd, String campo, JComboBox cmbDestino) {
        PreparedStatement ps;
        ResultSet rs;
        try {
            Connection con = Conexion.getConexion();
            ps = con.prepareStatement(cmd);
            rs = ps.executeQuery();
            while (rs.next()) {
                String dato = rs.getString(campo);
                cmbDestino.addItem(dato);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
    public void cargarComboBoxEItems(String cmd, String campo,String idcampo, JComboBox cmbDestino) {
    PreparedStatement ps;
    ResultSet rs;
    try {
        Connection con = Conexion.getConexion();
        ps = con.prepareStatement(cmd);
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(idcampo);
            String dato = rs.getString(campo);
            DatosGerentesAreas item = new DatosGerentesAreas(id, dato);
            cmbDestino.addItem( item);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.toString());
    }
}

    
}
