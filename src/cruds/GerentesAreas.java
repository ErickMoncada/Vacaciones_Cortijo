package cruds;

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
        Limpiar();
        CargarTabla();
        btnModificar.setVisible(false);
        txtid.setVisible(false);
        txtArea.setVisible(false);

        //iniciar funcion para el icono
        Reescalado_Imagenes reescalar = new Reescalado_Imagenes();
        setIconImage(reescalar.getIconImage());

    }
    //cargar clase de validaciones
    validaciones val = new validaciones();
    //inicializar variable de id del area
    int id;

    private void CargarTabla() {
        DatosTablas CrearTabla = new DatosTablas();
        CrearTabla.CargarTabla(tblCentro, "SELECT [Area],[NombreCompleto] from [VistaArea]");
    }

    private void CargarListas() {
        // Lista de JComboBox para actualizar datos
        JComboBox[] comboBoxes = {cmbGerente};

        // Recorrer cada JComboBox y eliminar los elementos
        for (JComboBox comboBox : comboBoxes) {
            DefaultComboBoxModel model = (DefaultComboBoxModel) comboBox.getModel();
            model.removeAllElements();
            model.addElement("");
        }

        //cargar los datos de los combobox
        DatosTablas Datos = new DatosTablas();
       // Datos.cargarComboBoxGerentes("select NombreCompleto from VistaEmpleados where IdNivel=5 and AreaEmpleado=?", "NombreCompleto", txtid.getText(), cmbGerente);
        Datos.cargarComboBoxEItems("select CodEmpleado,NombreCompleto from VistaEmpleados where IdNivel=5 and AreaEmpleado=?", "NombreCompleto", "CodEmpleado", txtid.getText(),cmbGerente);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtid = new javax.swing.JTextField();
        txtArea = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbGerente = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCentro = new javax.swing.JTable();
        btnCancelar = new rsbuttom.RSButtonMetro();
        lblGerente = new javax.swing.JLabel();

        txtid.setPreferredSize(new java.awt.Dimension(65, 26));

        txtArea.setPreferredSize(new java.awt.Dimension(65, 26));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerentes por Areas");
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        jLabel1.setFont(new java.awt.Font("Dubai", 1, 18)); // NOI18N
        jLabel1.setText("Genrentes por areas");

        cmbGerente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbGerenteItemStateChanged(evt);
            }
        });
        cmbGerente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGerenteActionPerformed(evt);
            }
        });

        btnModificar.setText("Guardar Cambios");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        lblTitulo.setForeground(new java.awt.Color(0, 0, 0));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Gerente del Area:");

        tblCentro.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tblCentro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Areas", "Gerentes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCentro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCentroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCentro);

        btnCancelar.setBackground(new java.awt.Color(114, 191, 68));
        btnCancelar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCancelar.setText("Cancelar");
        btnCancelar.setColorBorde(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCancelar.setColorHover(new java.awt.Color(0, 191, 68));
        btnCancelar.setColorNormal(new java.awt.Color(114, 191, 68));
        btnCancelar.setColorPressed(new java.awt.Color(0, 49, 30));
        btnCancelar.setColorTextHover(new java.awt.Color(51, 51, 51));
        btnCancelar.setColorTextNormal(new java.awt.Color(51, 51, 51));
        btnCancelar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnCancelar.setMaximumSize(new java.awt.Dimension(55, 20));
        btnCancelar.setMinimumSize(new java.awt.Dimension(55, 20));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblGerente.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblGerente.setForeground(new java.awt.Color(0, 0, 0));
        lblGerente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGerente.setText("Gerente del Area:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbGerente, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGerente, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(btnModificar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGerente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbGerente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
       if(!cmbGerente.getSelectedItem().equals("")){
        AccionesCrud classcrud = new AccionesCrud();
        //se trata de crear el usuario con un procedimiento almacenado
        classcrud.Guardar_Modificar(ArregloDatos(cmbGerente, txtArea.getText()), "exec [UpdateGerentes] ?,?");
        Limpiar();
        CargarTabla();
        btnModificar.setVisible(false);
       }else{
       JOptionPane.showMessageDialog(null, "Debe seleccionar un gerente en la lista desplegable", "Error", JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void cmbGerenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGerenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbGerenteActionPerformed

    private void cmbGerenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbGerenteItemStateChanged
        btnModificar.setVisible(true);
    }//GEN-LAST:event_cmbGerenteItemStateChanged

    private void tblCentroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCentroMouseClicked
        //se trata de obtener los datos de la tabla para mostrarlos en las casillas respectivas con ayuda de sql
        AccionesCrud classcrud = new AccionesCrud();
        if (classcrud.CargarDatoClick(tblCentro, "SELECT IdArea,[Area] from VistaArea where Area=?", "Area", "IdArea", txtArea, txtid)) {
            btnCancelar.setVisible(true);
            lblTitulo.setText("Gerente del Area "+txtArea.getText()+": ");
            SeleccionarGerente(txtArea.getText());
            CargarListas();
        }
    }//GEN-LAST:event_tblCentroMouseClicked

    
    private void SeleccionarGerente(String area) {
        try {
            AccionesCrud classcrud = new AccionesCrud();
            ResultSet rs;

            rs = classcrud.ResultSetDeQuery("select NombreCompleto from [VistaArea] where [Area]=?", area);
            while (rs.next()) {

                lblGerente.setText("Gerente Actual: " + rs.getString("NombreCompleto"));
                
            }
lblGerente.setVisible(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

    }
    private void Limpiar() {
        btnCancelar.setVisible(false);
        btnModificar.setVisible(false);
        cmbGerente.setSelectedItem("");
        lblGerente.setVisible(false);
    }
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private Object[] ArregloDatos(JComboBox cmb, String area) {
        Object[] datos = new Object[2];

        int selectedId;
        DatosGerentesAreas selectedData;
        
        datos[0] = txtArea.getText();
        selectedData = (DatosGerentesAreas) cmb.getSelectedItem();
        selectedId = selectedData.getId();
        datos[1] = selectedId;

        return datos;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonMetro btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cmbGerente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblGerente;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblCentro;
    private javax.swing.JTextField txtArea;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
