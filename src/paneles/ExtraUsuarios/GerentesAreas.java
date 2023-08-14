package paneles.ExtraUsuarios;

import Clases.AccionesCrud;
import Clases.DatosGerentesAreas;
import Clases.DatosTablas;
import Clases.Reescalado_Imagenes;
import Clases.validaciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 * @ErickMoncada controlador pantalla Puestos
 */
public class GerentesAreas extends javax.swing.JFrame {

    public GerentesAreas() {

        initComponents();
        CargarListas();
        btnGuardar.setVisible(false);

        //iniciar funcion para el icono
        Reescalado_Imagenes reescalar = new Reescalado_Imagenes();
        setIconImage(reescalar.getIconImage());

    }
    //cargar clase de validaciones
    validaciones val = new validaciones();

    private void CargarListas() {
        // Lista de JComboBox para actualizar datos
        JComboBox[] comboBoxes = {cmbAdministracion, cmbFinanzas, cmbProduccion, cmbPlantas, cmbCadena};

        // Recorrer cada JComboBox y eliminar los elementos
        for (JComboBox comboBox : comboBoxes) {
            DefaultComboBoxModel model = (DefaultComboBoxModel) comboBox.getModel();
            model.removeAllElements();
            model.addElement("");
        }
        //cargar los datos de los combobox
        DatosTablas Datos = new DatosTablas();
        Datos.cargarComboBoxEItems("select CodEmpleado,NombreCompleto from VistaEmpleados where IdNivel=5 and AreaEmpleado=1", "NombreCompleto", "CodEmpleado", cmbAdministracion);
        Datos.cargarComboBoxEItems("select CodEmpleado,NombreCompleto from VistaEmpleados where IdNivel=5 and AreaEmpleado=2", "NombreCompleto", "CodEmpleado", cmbFinanzas);
        Datos.cargarComboBoxEItems("select CodEmpleado,NombreCompleto from VistaEmpleados where IdNivel=5 and AreaEmpleado=3", "NombreCompleto", "CodEmpleado", cmbProduccion);
        Datos.cargarComboBoxEItems("select CodEmpleado,NombreCompleto from VistaEmpleados where IdNivel=5 and AreaEmpleado=4", "NombreCompleto", "CodEmpleado", cmbPlantas);
        Datos.cargarComboBoxEItems("select CodEmpleado,NombreCompleto from VistaEmpleados where IdNivel=5 and AreaEmpleado=5", "NombreCompleto", "CodEmpleado", cmbCadena);

        SeleccionarGerentes();
    }

    private void SeleccionarGerentes() {
        try {
            AccionesCrud classcrud = new AccionesCrud();
            ResultSet rs;

            rs = classcrud.ResultSetDeQuery("select NombreCompleto from [VistaArea] where [Area]=?", "Administracion");
            while (rs.next()) {

                lblActualAdmin.setText("Gerente Actual: " + rs.getString("NombreCompleto"));
            }

            rs = classcrud.ResultSetDeQuery("select NombreCompleto from [VistaArea] where [Area]=?", "Finanzas");
            while (rs.next()) {
                lblActualFinanza.setText("Gerente Actual: " + rs.getString("NombreCompleto"));
            }

            rs = classcrud.ResultSetDeQuery("select NombreCompleto from [VistaArea] where [Area]=?", "Produccion");
            while (rs.next()) {
                lblActualProd.setText("Gerente Actual: " + rs.getString("NombreCompleto"));
            }

            rs = classcrud.ResultSetDeQuery("select NombreCompleto from [VistaArea] where [Area]=?", "Plantas");
            while (rs.next()) {
                lblActualPla.setText("Gerente Actual: " + rs.getString("NombreCompleto"));
            }

            rs = classcrud.ResultSetDeQuery("select NombreCompleto from [VistaArea] where [Area]=?", "Cadena Suministros");
            while (rs.next()) {
                lblActualCad.setText("Gerente Actual: " + rs.getString("NombreCompleto"));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbAdministracion = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbFinanzas = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbProduccion = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cmbPlantas = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cmbCadena = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        lblActualFinanza = new javax.swing.JLabel();
        lblActualAdmin = new javax.swing.JLabel();
        lblActualProd = new javax.swing.JLabel();
        lblActualPla = new javax.swing.JLabel();
        lblActualCad = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerentes por Areas");
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        jLabel1.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        jLabel1.setText("Genrentes por areas:");

        jLabel2.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Administración:");

        cmbAdministracion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAdministracionItemStateChanged(evt);
            }
        });
        cmbAdministracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAdministracionActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Finanzas:");

        cmbFinanzas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbFinanzasItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Producción:");

        cmbProduccion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbProduccionItemStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Plantas:");

        cmbPlantas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPlantasItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Cadena Suministros:");

        cmbCadena.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCadenaItemStateChanged(evt);
            }
        });

        btnGuardar.setText("Guardar Cambios");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        lblActualFinanza.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lblActualFinanza.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblActualFinanza.setText("Actual Gerente:");

        lblActualAdmin.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lblActualAdmin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblActualAdmin.setText("Actual Gerente:");

        lblActualProd.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lblActualProd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblActualProd.setText("Actual Gerente:");

        lblActualPla.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lblActualPla.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblActualPla.setText("Actual Gerente:");

        lblActualCad.setFont(new java.awt.Font("Dubai", 0, 18)); // NOI18N
        lblActualCad.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblActualCad.setText("Actual Gerente:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblActualFinanza))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblActualProd))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblActualPla))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cmbFinanzas, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbPlantas, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbCadena, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbAdministracion, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblActualAdmin))))))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(158, 158, 158))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(lblActualCad))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(btnGuardar)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblActualAdmin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbAdministracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblActualFinanza))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbFinanzas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblActualProd))
                .addGap(5, 5, 5)
                .addComponent(cmbProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblActualPla))
                .addGap(5, 5, 5)
                .addComponent(cmbPlantas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblActualCad))
                .addGap(2, 2, 2)
                .addComponent(cmbCadena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 87, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        AccionesCrud classcrud = new AccionesCrud();
        //se trata de crear el usuario con un procedimiento almacenado
        if(cmbAdministracion.getSelectedItem()!=""){
        classcrud.Guardar_ModificarSinAviso(ArregloDatos(cmbAdministracion, "Administracion"), "exec [UpdateGerentes] ?,?");
        }
        if(cmbFinanzas.getSelectedItem()!=""){
        classcrud.Guardar_ModificarSinAviso(ArregloDatos(cmbFinanzas, "Finanzas"), "exec [UpdateGerentes] ?,?");
        }
        
        if(cmbProduccion.getSelectedItem()!=""){
        classcrud.Guardar_ModificarSinAviso(ArregloDatos(cmbProduccion, "Produccion"), "exec [UpdateGerentes] ?,?");
        }
        
        if(cmbPlantas.getSelectedItem()!=""){
        classcrud.Guardar_ModificarSinAviso(ArregloDatos(cmbPlantas, "Plantas"), "exec [UpdateGerentes] ?,?");
        }
        
        if(cmbCadena.getSelectedItem()!=""){
        classcrud.Guardar_ModificarSinAviso(ArregloDatos(cmbCadena, "Cadena Suministros"), "exec [UpdateGerentes] ?,?");
        }
        btnGuardar.setVisible(false);
        SeleccionarGerentes();
        cmbAdministracion.setSelectedItem("");
        cmbFinanzas.setSelectedItem("");
        cmbProduccion.setSelectedItem("");
        cmbCadena.setSelectedItem("");
        cmbPlantas.setSelectedItem("");
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cmbPlantasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPlantasItemStateChanged
        btnGuardar.setVisible(true);
    }//GEN-LAST:event_cmbPlantasItemStateChanged

    private void cmbProduccionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbProduccionItemStateChanged
        btnGuardar.setVisible(true);
    }//GEN-LAST:event_cmbProduccionItemStateChanged

    private void cmbFinanzasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbFinanzasItemStateChanged
        btnGuardar.setVisible(true);
    }//GEN-LAST:event_cmbFinanzasItemStateChanged

    private void cmbAdministracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAdministracionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAdministracionActionPerformed

    private void cmbAdministracionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAdministracionItemStateChanged
        btnGuardar.setVisible(true);
    }//GEN-LAST:event_cmbAdministracionItemStateChanged

    private void cmbCadenaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCadenaItemStateChanged
        btnGuardar.setVisible(true);
    }//GEN-LAST:event_cmbCadenaItemStateChanged

    private Object[] ArregloDatos(JComboBox cmb, String area) {
        Object[] datos = new Object[2];
        int selectedId;
        DatosGerentesAreas selectedData;

        datos[0] = area;
        selectedData = (DatosGerentesAreas) cmb.getSelectedItem();
        selectedId = selectedData.getId();
        datos[1] = selectedId;

        return datos;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbAdministracion;
    private javax.swing.JComboBox<String> cmbCadena;
    private javax.swing.JComboBox<String> cmbFinanzas;
    private javax.swing.JComboBox<String> cmbPlantas;
    private javax.swing.JComboBox<String> cmbProduccion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblActualAdmin;
    private javax.swing.JLabel lblActualCad;
    private javax.swing.JLabel lblActualFinanza;
    private javax.swing.JLabel lblActualPla;
    private javax.swing.JLabel lblActualProd;
    // End of variables declaration//GEN-END:variables
}
