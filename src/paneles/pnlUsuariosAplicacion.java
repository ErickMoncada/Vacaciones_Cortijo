package paneles;

import Clases.AccionesCrud;
import Clases.DatosTablas;
import Clases.Password;
import Clases.validaciones;
import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @ErickMoncada controlador panel usuarios aplicacion
 */
public class pnlUsuariosAplicacion extends javax.swing.JPanel {

    public pnlUsuariosAplicacion(String NIVEL) {
        initComponents();
        CargarDatosPrincipal();
        Limpiar();
        asignarEventos();
        if ("Root".equals(NIVEL)) {
            cmbNivel.addItem("Administrador");
        }
        NivelAcceso = NIVEL;
        //---------------------------------se establece que no se pueda pegar texto en los campos
        val.NegarPegado(txtUsuario);
        val.NegarPegado(txtCorreo);
        val.NegarPegado(txtExpediente);
        val.NegarPegado(txtBuscar);
        //------------------------------------------------------------------------------
    }
    //se Inicializa la variabl del nivel para tneerlo en el Jframe
    String NivelAcceso;
    //se inicializa para la busqueda por medio de Categoria
    String Busqueda = "Usuario";
    //se inicializa la clase de validaciones
    validaciones val = new validaciones();
    //se inicializa la calse de correos
    Password rec = new Password();
    //se inicializa la variable de la contraseña
    String passwordGenerado;

    private void Limpiar() {
        //funcion para reiniciar todos los valores de la pantalla
        btnCancelar.setVisible(false);
        btnModificar.setVisible(false);
        btnGuardar.setVisible(true);

        txtUsuario.enable(true);
        txtUsuario.setText("");
        txtCorreo.setText("");
        txtExpediente.setText("");
        cmbNivel.setSelectedIndex(-1);
        cmbNivel.enable(true);
        LimpiarErrores();
    }

    private void asignarEventos() {
        //funcion para asignar los eventos a los mensajes de obligatorio con la clase de validaciones
        val.asignarEventosMouse(lblObligatorio);
        val.asignarEventosMouse(lblObligatorio1);
        val.asignarEventosMouse(lblObligatorio3);
        val.asignarEventosMouse(lblObligatorio4);
    }

    private void CargarDatosPrincipal() {
        //rellenar datos de la tabla
        BuscarEnTabla();

        // Aplicar el renderizador de celdas a todas las columnas para pintarlos segun el estado
        for (int i = 0; i < tblUsuarios.getColumnCount(); i++) {
            tblUsuarios.getColumnModel().getColumn(i).setCellRenderer(rowRenderer);
        }
        // Agregar el ListSelectionListener para cambiar el color de fondo cuando se selecciona una celda
        tblUsuarios.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblUsuarios.getSelectedRow();
                int selectedColumn = tblUsuarios.getSelectedColumn();
                if (selectedRow >= 0 && selectedColumn >= 0) {
                    tblUsuarios.getColumnModel().getColumn(selectedColumn).setCellRenderer(rowRenderer);
                }
            }
        });
    }

    // Renderizador de celdas personalizado para cambiar el color de la fila
    DefaultTableCellRenderer rowRenderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // Obtener el componente renderizado por defecto
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            //si esta seleccionado se le asigna el color de seleccion
            if (isSelected) {
                cellComponent.setBackground(new Color(51, 153, 0));
            } else {
                // Obtener el estado de la fila
                int indiceColumna = tblUsuarios.getColumnModel().getColumnIndex("Nivel de Acceso");
                String estado = table.getValueAt(row, indiceColumna).toString();

                if (estado.equals("Desactivado")) {
                    cellComponent.setBackground(new Color(197, 135, 118));
                } else {
                    cellComponent.setBackground(new Color(204, 255, 204));
                }

            }
            return cellComponent;
        }
    };

    private void LimpiarErrores() {
        //usando la clase de validaciones se establecen los valores en correcto
        val.GENcorrecto(lblErUsuario);
        val.GENcorrecto(lblErCorreo);
        val.TXTcorrecto(txtCorreo, lblErExpediente);
        val.TXTcorrecto(txtExpediente, lblErNivel);
    }

    private boolean ValidarCampos() {

        int valor1 = 1;
        String error;
        if (txtCorreo.getText().isEmpty() || !txtCorreo.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            valor1 = 0;
            error = "Escriba un correo electronico valido";
            val.TXTincorrecto(txtCorreo, lblErCorreo, error);
        }
        if (txtExpediente.getText().isEmpty()) {
            valor1 = 0;
            error = "Escriba un numero de expediente";
            val.TXTincorrecto(txtExpediente, lblErExpediente, error);
        }
        if (txtUsuario.getText().isEmpty()) {
            valor1 = 0;
            error = "Escriba un Usuario";
            val.TXTincorrecto(txtUsuario, lblErUsuario, error);
        }
        if (cmbNivel.getSelectedItem() == null || cmbNivel.getSelectedItem() == " ") {
            valor1 = 0;
            error = "Debe seleccionar un Nivel de Acceso";
            val.CMBincorrecto(cmbNivel, lblErNivel, error);
        }

        return valor1 == 1; //Expreciones regulares de los campos
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        btnModificar = new rsbuttom.RSButtonMetro();
        btnGuardar = new rsbuttom.RSButtonMetro();
        btnCancelar = new rsbuttom.RSButtonMetro();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtExpediente = new javax.swing.JTextField();
        lblErUsuario = new javax.swing.JLabel();
        lblErCorreo = new javax.swing.JLabel();
        lblErExpediente = new javax.swing.JLabel();
        lblErNivel = new javax.swing.JLabel();
        lblObligatorio = new javax.swing.JLabel();
        lblObligatorio1 = new javax.swing.JLabel();
        lblObligatorio3 = new javax.swing.JLabel();
        lblObligatorio4 = new javax.swing.JLabel();
        cmbNivel = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        cmbBuscar = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        chkLector = new javax.swing.JCheckBox();
        chkDesactivado = new javax.swing.JCheckBox();
        chkAdmin = new javax.swing.JCheckBox();

        jMenuItem1.setText("Limpiar Campos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setComponentPopupMenu(jPopupMenu1);

        txtUsuario.setNextFocusableComponent(txtCorreo);
        txtUsuario.setPreferredSize(new java.awt.Dimension(65, 26));
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
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
        btnGuardar.setText("Crear Usuario");
        btnGuardar.setColorBorde(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnGuardar.setColorHover(new java.awt.Color(0, 191, 68));
        btnGuardar.setColorNormal(new java.awt.Color(114, 191, 68));
        btnGuardar.setColorPressed(new java.awt.Color(0, 49, 30));
        btnGuardar.setColorTextHover(new java.awt.Color(51, 51, 51));
        btnGuardar.setColorTextNormal(new java.awt.Color(51, 51, 51));
        btnGuardar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnGuardar.setMaximumSize(new java.awt.Dimension(55, 20));
        btnGuardar.setMinimumSize(new java.awt.Dimension(55, 20));
        btnGuardar.setNextFocusableComponent(txtBuscar);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(114, 191, 68));
        btnCancelar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCancelar.setText("Cancelar Selección");
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

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Usuario:");

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Correo:");

        txtCorreo.setNextFocusableComponent(txtExpediente);
        txtCorreo.setPreferredSize(new java.awt.Dimension(65, 26));
        txtCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoKeyTyped(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Número de Expediente:");

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Nivel:");

        txtExpediente.setNextFocusableComponent(cmbNivel);
        txtExpediente.setPreferredSize(new java.awt.Dimension(65, 26));
        txtExpediente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtExpedienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtExpedienteKeyTyped(evt);
            }
        });

        lblErUsuario.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErUsuario.setForeground(new java.awt.Color(255, 0, 0));
        lblErUsuario.setText("Error");

        lblErCorreo.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErCorreo.setForeground(new java.awt.Color(255, 0, 0));
        lblErCorreo.setText("Error");

        lblErExpediente.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErExpediente.setForeground(new java.awt.Color(255, 0, 0));
        lblErExpediente.setText("Error");

        lblErNivel.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErNivel.setForeground(new java.awt.Color(255, 0, 0));
        lblErNivel.setText("Error");

        lblObligatorio.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio.setText("*");
        lblObligatorio.setToolTipText("");

        lblObligatorio1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio1.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio1.setText("*");
        lblObligatorio1.setToolTipText("");

        lblObligatorio3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio3.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio3.setText("*");
        lblObligatorio3.setToolTipText("");

        lblObligatorio4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio4.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio4.setText("*");
        lblObligatorio4.setToolTipText("");

        cmbNivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Lector", "Desactivado" }));
        cmbNivel.setNextFocusableComponent(btnGuardar);
        cmbNivel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbNivelItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblErUsuario)
                            .addComponent(lblErCorreo)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblObligatorio)
                                .addGap(43, 43, 43)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblObligatorio1)
                                .addGap(144, 144, 144)
                                .addComponent(jLabel10)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblErExpediente)
                            .addComponent(lblErNivel)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtExpediente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbNivel, 0, 198, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblObligatorio4)
                                    .addComponent(lblObligatorio3, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(449, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObligatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(txtExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObligatorio3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblErUsuario)
                    .addComponent(lblErExpediente))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObligatorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cmbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObligatorio4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblErCorreo)
                    .addComponent(lblErNivel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane1.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(800, 403));

        tblUsuarios.setBackground(new java.awt.Color(204, 255, 204));
        tblUsuarios.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Usuario", "Nombre", "Correo", "Expediente", "Nivel de Acceso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuarios.setGridColor(new java.awt.Color(0, 0, 0));
        tblUsuarios.setSelectionBackground(new java.awt.Color(51, 153, 0));
        tblUsuarios.getTableHeader().setReorderingAllowed(false);
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        cmbBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Usuario", "Nombre", "Expediente", "Correo" }));
        cmbBuscar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBuscarItemStateChanged(evt);
            }
        });

        jLabel2.setText("Buscar por:");

        chkLector.setText("Lector");
        chkLector.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkLectorItemStateChanged(evt);
            }
        });

        chkDesactivado.setText("Desactivados");
        chkDesactivado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkDesactivadoItemStateChanged(evt);
            }
        });

        chkAdmin.setText("Administrador");
        chkAdmin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkAdminItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1148, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkLector)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkDesactivado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkAdmin)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(chkLector)
                    .addComponent(chkDesactivado)
                    .addComponent(chkAdmin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private Object[] ArregloDatos() {
        //---------------------------Generar Contraseña aleatorio de 6 digitos
        int longitud = 6; // Longitud del texto generado
        StringBuilder sb = new StringBuilder();

        String caracteres = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        Random random = new Random();

        for (int i = 0; i < longitud; i++) {
            int indiceAleatorio = random.nextInt(caracteres.length());
            char caracterAleatorio = caracteres.charAt(indiceAleatorio);
            sb.append(caracterAleatorio);
        }
        passwordGenerado = sb.toString();
        //---------------------------Arreglo de datos
        Object[] datos = new Object[5];
        //se crea un arreglo de objetos para enviar a la clase de AccionesCrud y la funcion de Guardar_Modificar
        datos[0] = txtUsuario.getText();
        datos[1] = txtExpediente.getText();
        datos[2] = txtCorreo.getText();
        datos[3] = cmbNivel.getSelectedItem().toString();
        datos[4] = rec.Encriptar(passwordGenerado);

        return datos;
    }
    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        Limpiar();
        //bandera para saber si puede seleccionar
        boolean x;

        int fila = tblUsuarios.getSelectedRow();
        int indiceColumna = tblUsuarios.getColumnModel().getColumnIndex("Nivel de Acceso");
        String nivel = tblUsuarios.getValueAt(fila, indiceColumna).toString();
        // se comprueba si es administrador o no de ser asi no puede seleccionar para modificar administradores
        if ("Root".equals(NivelAcceso)) {
            x = true;

        } else {
            x = !"Administrador".equals(nivel);
        }

        if (x) {
            //se trata de obtener los datos de la tabla para mostrarlos en las casillas respectivas con ayuda de sql
            try {
                AccionesCrud classcrud = new AccionesCrud();
                ResultSet rs = classcrud.Seleccion(tblUsuarios, "select * from [VistaUsuariosApp] where [Usuario]=?", "Usuario");
                while (rs.next()) {
                    txtUsuario.setText(rs.getString("Usuario"));
                    txtCorreo.setText(rs.getString("Correo"));
                    txtExpediente.setText(rs.getString("Expediente"));
                    cmbNivel.setSelectedItem(rs.getString("Nivel"));
                }
                txtUsuario.enable(false);
                btnModificar.setVisible(true);
                btnCancelar.setVisible(true);
                btnGuardar.setVisible(false);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }//GEN-LAST:event_tblUsuariosMouseClicked

    //Funicon para asignar el tipo de busqueda que se va hacer por medio de un switc y los valores de la vista de la BD
    private void cmbBuscarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBuscarItemStateChanged
        //Cada vez que se cambia el estado del combobox se cambia el filtro de busqueda global para la funcion de busqueda
        String elementoSeleccionado = (String) cmbBuscar.getSelectedItem();
        switch (elementoSeleccionado) {
            case "Usuario":
                Busqueda = "Usuario";
                break;
            case "Nombre":
                Busqueda = "Nombre";
                break;
            case "Expediente":
                Busqueda = "Expediente";
                break;
            case "Correo":
                Busqueda = "Correo";
                break;
            default:
                break;
        }
        BuscarEnTabla();
        txtBuscar.setText("");
    }//GEN-LAST:event_cmbBuscarItemStateChanged

    private void BuscarEnTabla() {
        //cada vez que se precione una tecla se va a buscar junto al filtro de busqueda en la vista correspondiente
        DatosTablas BusquedaTabla = new DatosTablas();
        //se limpia la tabla
        DefaultTableModel modelo = (DefaultTableModel) tblUsuarios.getModel();
        modelo.setRowCount(0);
        //se busca si esta con un check box de busqueda
        String agregado = "";
        if (chkAdmin.isSelected()) {
            agregado += " OR " + Busqueda + "  LIKE '%" + txtBuscar.getText().trim() + "%' AND Nivel='Administrador'";
        }
        if (chkLector.isSelected()) {
            agregado += " OR " + Busqueda + "  LIKE '%" + txtBuscar.getText().trim() + "%' AND Nivel='Lector'";
        }
        if (chkDesactivado.isSelected()) {
            agregado += " OR " + Busqueda + "  LIKE '%" + txtBuscar.getText().trim() + "%' AND Nivel='Desactivado'";
        }

        if ("".equals(agregado)) {
            //se muestra los resultados de la busqueda con filtros de checksbox
            BusquedaTabla.CargarTabla(tblUsuarios, "select Usuario,Nombre,Correo,Expediente,Nivel from VistaUsuariosApp where " + Busqueda + "  LIKE '%" + txtBuscar.getText().trim() + "%' AND Nivel!='Root'");
        } else {
            //se muestra los resultados de la busqueda sin filtros de checksbox
            String result = agregado.substring(4);
            BusquedaTabla.CargarTabla(tblUsuarios, "select Usuario,Nombre,Correo,Expediente,Nivel from VistaUsuariosApp where " + result);
        }
    }
    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        BuscarEnTabla();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Limpiar();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        //switch para decidir que validacion establecer cada ves que se preciona una tecla en buscar
        switch (Busqueda) {
            case "Usuario":
                val.EntradaLetrasNumerosSinEspacios(txtUsuario, evt, 30);
                break;
            case "Nombre":
                val.EntradaSoloLetas(txtBuscar, evt, 80);
                break;
            case "Expediente":
                val.EntradaNumeros(txtBuscar, evt, 4);
                break;
            case "Correo":
                val.EntradaCorreo(txtBuscar, evt);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void chkLectorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkLectorItemStateChanged
        //recargar la tabla
        BuscarEnTabla();
    }//GEN-LAST:event_chkLectorItemStateChanged

    private void chkDesactivadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkDesactivadoItemStateChanged
        //recargar la tabla
        BuscarEnTabla();
    }//GEN-LAST:event_chkDesactivadoItemStateChanged

    private void txtExpedienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExpedienteKeyTyped
        // validado para un campo de tipo linea telefonica
        val.EntradaNumeros(txtExpediente, evt, 4);
    }//GEN-LAST:event_txtExpedienteKeyTyped

    private void txtExpedienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExpedienteKeyReleased
        //al escribir se quita el estado de error
        val.TXTcorrecto(txtExpediente, lblErExpediente);
    }//GEN-LAST:event_txtExpedienteKeyReleased

    private void txtCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyTyped
        // validado para un campo de tipo monetario
        val.EntradaCorreo(txtCorreo, evt);
    }//GEN-LAST:event_txtCorreoKeyTyped

    private void txtCorreoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoKeyReleased
        //al escribir se quita el estado de error
        val.TXTcorrecto(txtCorreo, lblErCorreo);
    }//GEN-LAST:event_txtCorreoKeyReleased

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        LimpiarErrores();
        if (ValidarCampos()) {
            AccionesCrud classcrud = new AccionesCrud();
            //se trata de crear el usuario con un procedimiento almacenado
            if (classcrud.Guardar_Modificar(ArregloDatos(), "exec [AgregarUsuarioAplicacion] ?,?,?,?,?")) {
                //se enviar la contraseña y el usuario al correo que se le ah creado la cuenta
                if (rec.EnviarPassword(txtUsuario.getText(), passwordGenerado) == false) {
                    JOptionPane.showMessageDialog(null, "El Usuario ah sido creado pero no se pudo enviar la contraseña por correo porque el servidor de correo está mal configurado,\n cuando el servidor este bien configurado utilize el metodo de recuperar contraseña para obtener una contraseña para este usuario", "Envió de Correo", JOptionPane.INFORMATION_MESSAGE);
                }
                BuscarEnTabla();
                Limpiar();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        LimpiarErrores();
        if (ValidarCampos()) {
            AccionesCrud classcrud = new AccionesCrud();
            //se trata de modificar el usuario con un procedimiento almacenado
            if (classcrud.Guardar_Modificar(ArregloDatos(), "exec [UpdateUsuarioAplicacion] ?,?,?,?,?")) {
                BuscarEnTabla();
                Limpiar();
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void cmbNivelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbNivelItemStateChanged
        //al seleccionar un item se quita el estado de error
        val.CMBcorrecto(cmbNivel, lblErNivel);
    }//GEN-LAST:event_cmbNivelItemStateChanged

    private void chkAdminItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkAdminItemStateChanged
        //recargar la tabla
        BuscarEnTabla();
    }//GEN-LAST:event_chkAdminItemStateChanged

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        //validacion para campos tipo texto sin espacios
        val.EntradaLetrasNumerosSinEspacios(txtUsuario, evt, 30);
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
        //se quita el estado de incorrecto
        val.TXTcorrecto(txtUsuario, lblErUsuario);
    }//GEN-LAST:event_txtUsuarioKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonMetro btnCancelar;
    private rsbuttom.RSButtonMetro btnGuardar;
    private rsbuttom.RSButtonMetro btnModificar;
    private javax.swing.JCheckBox chkAdmin;
    private javax.swing.JCheckBox chkDesactivado;
    private javax.swing.JCheckBox chkLector;
    private javax.swing.JComboBox<String> cmbBuscar;
    private javax.swing.JComboBox<String> cmbNivel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblErCorreo;
    private javax.swing.JLabel lblErExpediente;
    private javax.swing.JLabel lblErNivel;
    private javax.swing.JLabel lblErUsuario;
    private javax.swing.JLabel lblObligatorio;
    private javax.swing.JLabel lblObligatorio1;
    private javax.swing.JLabel lblObligatorio3;
    private javax.swing.JLabel lblObligatorio4;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtExpediente;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
