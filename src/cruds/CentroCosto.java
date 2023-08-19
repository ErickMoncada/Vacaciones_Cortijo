package cruds;

import Clases.AccionesCrud;
import Clases.DatosTablas;
import Clases.Reescalado_Imagenes;
import Clases.validaciones;
import app.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 * @ErickMoncada controlador pantalla centro de costo
 */
public class CentroCosto extends javax.swing.JFrame {

    public CentroCosto() {

        initComponents();
        CargarTabla();
        CargarListas() ;
        Limpiar();
        asignarEventos();
        //iniciar funcion para el icono
        Reescalado_Imagenes reescalar = new Reescalado_Imagenes();
        setIconImage(reescalar.getIconImage());
        //---------------------------------se establece que no se pueda pegar texto en los campos
        val.NegarPegado(txtNumero);
        val.NegarPegado(txtNombre);
        //------------------------------------------------------------------------------
    }
     private void asignarEventos() {
        //funcion para asignar los eventos a los mensajes de obligatorio con la clase de validaciones
        val.asignarEventosMouse(lblObligatorio2);
        val.asignarEventosMouse(lblObligatorio3);
        val.asignarEventosMouse(lblObligatorio4);
    }

    //cargar clase de validaciones
    validaciones val = new validaciones();

      private void CargarListas() {
        // Lista de JComboBox para actualizar datos
        JComboBox[] comboBoxes = {cmbAreas};

        // Recorrer cada JComboBox y eliminar los elementos
        for (JComboBox comboBox : comboBoxes) {
            DefaultComboBoxModel model = (DefaultComboBoxModel) comboBox.getModel();
            model.removeAllElements();
            model.addElement("");
        }
        //cargar los datos de los combobox
        DatosTablas Datos = new DatosTablas();
        Datos.cargarComboBox("select Area from VistaArea", "Area", cmbAreas);
    }
    //Funcion para cargar datos a la tabla
    private void CargarTabla() {
        DatosTablas CrearTabla = new DatosTablas();
        CrearTabla.CargarTabla(tblCentro, "SELECT * from [VistaCentroCosto]");
    }

    //Funcion para Validar campos
    private boolean Validar() {
        int valor1 = 1;
         String error;
        if (txtNombre.getText().trim().isEmpty()) {
            error = "El nombre no puede estar en balnco";
            val.TXTincorrecto(txtNombre, lblErNombre, error);
            valor1 = 0;
        } 
        if (txtNumero.getText().trim().isEmpty()) {
            error = "El numero no puede estar en balnco";
            val.TXTincorrecto(txtNumero, lblErNumero, error);
            valor1 = 0;
        }
          if (cmbAreas.getSelectedItem() == null || cmbAreas.getSelectedItem() == "") {
            valor1 = 0;
            error = "Debe seleccionar una Area";
            val.CMBincorrecto(cmbAreas, lblErAreas, error);
        }
        return valor1 == 1; 
    }

        private void LimpiarErrores() {
        //usando la clase de validaciones se establecen los valores en correcto
        val.TXTcorrecto(txtNombre, lblErNombre);
        val.TXTcorrecto(txtNumero, lblErNumero);
        val.CMBcorrecto(cmbAreas, lblErAreas);
    }
    //desactivar botones y solo mostrar btnGurdar
    private void Limpiar() {
        btnGuardar.setVisible(true);
        btnModificar.setVisible(false);
        btnEliminar.setVisible(false);
        btnCancelar.setVisible(false);
        txtNumero.enable(true);
        txtNombre.setText("");
        txtNumero.setText("");
        cmbAreas.setSelectedItem("");
        LimpiarErrores();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblNumero = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        btnEliminar = new rsbuttom.RSButtonMetro();
        btnModificar = new rsbuttom.RSButtonMetro();
        btnGuardar = new rsbuttom.RSButtonMetro();
        cmbAreas = new javax.swing.JComboBox<>();
        lblNombre1 = new javax.swing.JLabel();
        lblErAreas = new javax.swing.JLabel();
        lblObligatorio2 = new javax.swing.JLabel();
        lblObligatorio3 = new javax.swing.JLabel();
        btnCancelar = new rsbuttom.RSButtonMetro();
        lblObligatorio4 = new javax.swing.JLabel();
        lblErNombre = new javax.swing.JLabel();
        lblErNumero = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCentro = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Centros de Costo");
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        lblNombre.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre.setText("Nombre de Centro de Costo:");

        txtNombre.setPreferredSize(new java.awt.Dimension(65, 26));
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        lblNumero.setForeground(new java.awt.Color(0, 0, 0));
        lblNumero.setText("Número de Centro de Costo:");

        txtNumero.setPreferredSize(new java.awt.Dimension(65, 26));
        txtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumeroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumeroKeyTyped(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(114, 191, 68));
        btnEliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEliminar.setText("Eliminar");
        btnEliminar.setColorBorde(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEliminar.setColorHover(new java.awt.Color(0, 191, 68));
        btnEliminar.setColorNormal(new java.awt.Color(114, 191, 68));
        btnEliminar.setColorPressed(new java.awt.Color(0, 49, 30));
        btnEliminar.setColorTextHover(new java.awt.Color(51, 51, 51));
        btnEliminar.setColorTextNormal(new java.awt.Color(51, 51, 51));
        btnEliminar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnEliminar.setMaximumSize(new java.awt.Dimension(55, 20));
        btnEliminar.setMinimumSize(new java.awt.Dimension(55, 20));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(114, 191, 68));
        btnModificar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnModificar.setText("Modificar");
        btnModificar.setColorBorde(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnModificar.setColorHover(new java.awt.Color(0, 191, 68));
        btnModificar.setColorNormal(new java.awt.Color(114, 191, 68));
        btnModificar.setColorPressed(new java.awt.Color(0, 49, 30));
        btnModificar.setColorTextHover(new java.awt.Color(51, 51, 51));
        btnModificar.setColorTextNormal(new java.awt.Color(51, 51, 51));
        btnModificar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnModificar.setMaximumSize(new java.awt.Dimension(55, 20));
        btnModificar.setMinimumSize(new java.awt.Dimension(55, 20));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(114, 191, 68));
        btnGuardar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardar.setText("Guardar");
        btnGuardar.setColorBorde(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardar.setColorHover(new java.awt.Color(0, 191, 68));
        btnGuardar.setColorNormal(new java.awt.Color(114, 191, 68));
        btnGuardar.setColorPressed(new java.awt.Color(0, 49, 30));
        btnGuardar.setColorTextHover(new java.awt.Color(51, 51, 51));
        btnGuardar.setColorTextNormal(new java.awt.Color(51, 51, 51));
        btnGuardar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnGuardar.setMaximumSize(new java.awt.Dimension(55, 20));
        btnGuardar.setMinimumSize(new java.awt.Dimension(55, 20));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        cmbAreas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cmbAreas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAreasItemStateChanged(evt);
            }
        });

        lblNombre1.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre1.setText("Área del Centro de Costo:");

        lblErAreas.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErAreas.setForeground(new java.awt.Color(255, 0, 0));
        lblErAreas.setText("Error");

        lblObligatorio2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio2.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio2.setText("*");
        lblObligatorio2.setToolTipText("");

        lblObligatorio3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio3.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio3.setText("*");
        lblObligatorio3.setToolTipText("");

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

        lblObligatorio4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio4.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio4.setText("*");
        lblObligatorio4.setToolTipText("");

        lblErNombre.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErNombre.setForeground(new java.awt.Color(255, 0, 0));
        lblErNombre.setText("Error");

        lblErNumero.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErNumero.setForeground(new java.awt.Color(255, 0, 0));
        lblErNumero.setText("Error");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblNombre1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblErAreas)
                                    .addComponent(cmbAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblErNombre)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblNumero)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblErNumero)
                                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblObligatorio2)
                                    .addComponent(lblObligatorio3))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblObligatorio4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24))))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumero)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObligatorio4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblErNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblObligatorio3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre1)
                    .addComponent(lblObligatorio2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblErAreas, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tblCentro.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tblCentro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Número Centro de Costo", "Nombre de centro de costo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCentro.getTableHeader().setReorderingAllowed(false);
        tblCentro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCentroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCentro);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblCentroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCentroMouseClicked
        //se trata de obtener los datos de la tabla para mostrarlos en las casillas respectivas con ayuda de sql
        try {
            btnGuardar.setVisible(false);
            btnModificar.setVisible(true);
            btnEliminar.setVisible(true);
            btnCancelar.setVisible(true);
            int fila = tblCentro.getSelectedRow();
            String id = tblCentro.getValueAt(fila, 0).toString();
            PreparedStatement ps;
            ResultSet rs;
            Connection con = Conexion.getConexion();
            ps = con.prepareStatement("SELECT CentroCosto,Area from [VistaCentroCosto] where [NumCentroCosto]=?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                txtNombre.setText(rs.getString("CentroCosto"));
                cmbAreas.setSelectedItem(rs.getString("Area"));
            }
            txtNumero.setText(id);
            txtNumero.enable(false);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_tblCentroMouseClicked

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        //se crea un arreglo de objetos para enviar a la clase de AccionesCrud y la funcion de Guardar_Modificar
        if (Validar()) {
            Object[] datos = new Object[3];
            datos[0] = txtNumero.getText().trim();
            datos[1] = txtNombre.getText().trim();
            datos[2] = cmbAreas.getSelectedItem().toString();
            AccionesCrud classcrud = new AccionesCrud();
            if (classcrud.Guardar_Modificar(datos, "exec [AgregarCentroCosto] ?, ?,?")) {
                txtNombre.setText("");
                txtNumero.setText("");
                CargarTabla();
                Limpiar();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        //se crea un arreglo de objetos para enviar a la clase de AccionesCrud y la funcion de Guardar_Modificar
        if (Validar()) {
            Object[] datos = new Object[3];
            datos[0] = txtNumero.getText().trim();
            datos[1] = txtNombre.getText().trim();
            datos[2] = cmbAreas.getSelectedItem().toString();
            AccionesCrud classcrud = new AccionesCrud();
            if (classcrud.Guardar_Modificar(datos, "exec [UpdateCentroCosto] ?, ?,? ")) {
                txtNombre.setText("");
                txtNumero.setText("");
                CargarTabla();
                Limpiar();
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //se utiliza la funcion Eliminar de la clase AccionesCrud enviando el ID
        AccionesCrud classcrud = new AccionesCrud();
        if (classcrud.Eliminar(txtNumero, "exec EliminarCentroCosto ?")) {
            CargarTabla();
            Limpiar();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyTyped
        // validado para un campo de tipo numerico con el parametro de la longitud deseada
        val.EntradaNumeros(txtNumero, evt, 10);
    }//GEN-LAST:event_txtNumeroKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // validado para un campo de tipo texto normal con el parametro de la longitud deseada
        val.EntradaTextoNormal(txtNombre, evt, 80);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void cmbAreasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAreasItemStateChanged
        //al seleccionar un item se quita el estado de error
        val.CMBcorrecto(cmbAreas, lblErAreas);
    }//GEN-LAST:event_cmbAreasItemStateChanged

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        val.TXTcorrecto(txtNombre, lblErNombre);
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtNumeroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroKeyReleased
        val.TXTcorrecto(txtNumero, lblErNumero);
    }//GEN-LAST:event_txtNumeroKeyReleased



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonMetro btnCancelar;
    private rsbuttom.RSButtonMetro btnEliminar;
    private rsbuttom.RSButtonMetro btnGuardar;
    private rsbuttom.RSButtonMetro btnModificar;
    private javax.swing.JComboBox<String> cmbAreas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblErAreas;
    private javax.swing.JLabel lblErNombre;
    private javax.swing.JLabel lblErNumero;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblObligatorio2;
    private javax.swing.JLabel lblObligatorio3;
    private javax.swing.JLabel lblObligatorio4;
    private javax.swing.JTable tblCentro;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNumero;
    // End of variables declaration//GEN-END:variables
}
