package paneles.ExtraUsuarios;

import Clases.AccionesCrud;
import Clases.DatosTablas;
import Clases.Reescalado_Imagenes;
import Clases.validaciones;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import paneles.pnlSolicitudes;

/**
 * @ErickMoncada controlador pantalla planilla
 */
public class Empleados extends javax.swing.JFrame {

    public Empleados() {
        initComponents();
        CargarTabla();
        Limpiar();
        CargarListas();
        asignarEventos();

        //iniciar funcion para el icono
        Reescalado_Imagenes reescalar = new Reescalado_Imagenes();
        setIconImage(reescalar.getIconImage());
        //---------------------------------se establece que no se pueda pegar texto en los campos
        //val.NegarPegado(txtPlanilla);
        //------------------------------------------------------------------------------
    }

    //se inicializa para la busqueda por medio de Categoria
    String Busqueda = "NombreCompleto";
    //cargar clase de validaciones
    validaciones val = new validaciones();

    //Funcion para cargar datos a la tabla
    private void CargarTabla() {
        DatosTablas CrearTabla = new DatosTablas();
        CrearTabla.CargarTabla(tblCentro, "SELECT * from [VistaEmpleadosCrud] where " + Busqueda + "  LIKE '%" + txtBuscar.getText().trim() + "%'");
    }

    private void asignarEventos() {
        //funcion para asignar los eventos a los mensajes de obligatorio con la clase de validaciones
        val.asignarEventosMouse(lblObligatorio);
        val.asignarEventosMouse(lblObligatorio1);
        val.asignarEventosMouse(lblObligatorio2);
        val.asignarEventosMouse(lblObligatorio3);
        val.asignarEventosMouse(lblObligatorio4);
        val.asignarEventosMouse(lblObligatorio5);
        val.asignarEventosMouse(lblObligatorio6);
        val.asignarEventosMouse(lblObligatorioArea);
        val.asignarEventosMouse(lblObligatorioCentro);
    }

    //desactivar botones y solo mostrar btnGurdar
    private void Limpiar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtIdentidad.setText("");
        dtpIngreso.setDate(null);
        dtpNacimiento.setDate(null);
        cmbNivel.setSelectedItem("");
        cmbPuesto.setSelectedItem("");
        cmbCentroCosto.setSelectedItem("");
        cmbArea.setSelectedItem("");
        txtCodigo.setEnabled(true);
        btnGuardar.setVisible(true);
        btnModificar.setVisible(false);
        btnEliminar.setVisible(false);
        btnCancelar.setVisible(false);
        LimpiarErrores();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnEliminar = new rsbuttom.RSButtonMetro();
        btnModificar = new rsbuttom.RSButtonMetro();
        btnGuardar = new rsbuttom.RSButtonMetro();
        jLabel4 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblObligatorio1 = new javax.swing.JLabel();
        lblErCodigo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblObligatorio = new javax.swing.JLabel();
        lblErNombre = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dtpIngreso = new com.toedter.calendar.JDateChooser();
        lblObligatorio5 = new javax.swing.JLabel();
        lblErIngreso = new javax.swing.JLabel();
        lblObligatorio6 = new javax.swing.JLabel();
        lblErNacimiento = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dtpNacimiento = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        txtIdentidad = new javax.swing.JTextField();
        lblObligatorio4 = new javax.swing.JLabel();
        lblErIdentidad = new javax.swing.JLabel();
        cmbNivel = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        lblErNivel = new javax.swing.JLabel();
        lblObligatorio2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cmbPuesto = new javax.swing.JComboBox<>();
        lblErPuesto = new javax.swing.JLabel();
        lblObligatorio3 = new javax.swing.JLabel();
        lblCentro = new javax.swing.JLabel();
        cmbCentroCosto = new javax.swing.JComboBox<>();
        lblErCentroCosto = new javax.swing.JLabel();
        lblObligatorioCentro = new javax.swing.JLabel();
        btnCancelar = new rsbuttom.RSButtonMetro();
        lblArea = new javax.swing.JLabel();
        cmbArea = new javax.swing.JComboBox<>();
        lblErArea = new javax.swing.JLabel();
        lblObligatorioArea = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCentro = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        cmbBuscar = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Planilla-Usuarios");
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

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

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Codigo del Empleado:");

        txtCodigo.setPreferredSize(new java.awt.Dimension(65, 26));
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        lblObligatorio1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio1.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio1.setText("*");
        lblObligatorio1.setToolTipText("");

        lblErCodigo.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErCodigo.setForeground(new java.awt.Color(255, 0, 0));
        lblErCodigo.setText("Error");

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nombre Completo:");

        txtNombre.setPreferredSize(new java.awt.Dimension(65, 26));
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        lblObligatorio.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio.setText("*");
        lblObligatorio.setToolTipText("");

        lblErNombre.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErNombre.setForeground(new java.awt.Color(255, 0, 0));
        lblErNombre.setText("Error");

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Fecha de Ingreso:");

        dtpIngreso.setMinSelectableDate(new java.util.Date(1262329270000L));
        dtpIngreso.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtpIngresoPropertyChange(evt);
            }
        });

        lblObligatorio5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio5.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio5.setText("*");
        lblObligatorio5.setToolTipText("");

        lblErIngreso.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErIngreso.setForeground(new java.awt.Color(255, 0, 0));
        lblErIngreso.setText("Error");

        lblObligatorio6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio6.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio6.setText("*");
        lblObligatorio6.setToolTipText("");

        lblErNacimiento.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErNacimiento.setForeground(new java.awt.Color(255, 0, 0));
        lblErNacimiento.setText("Error");

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Fecha de Nacimiento:");

        dtpNacimiento.setMinSelectableDate(new java.util.Date(1262329270000L));
        dtpNacimiento.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtpNacimientoPropertyChange(evt);
            }
        });

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Identidad:");

        txtIdentidad.setPreferredSize(new java.awt.Dimension(65, 26));
        txtIdentidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdentidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdentidadKeyTyped(evt);
            }
        });

        lblObligatorio4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio4.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio4.setText("*");
        lblObligatorio4.setToolTipText("");

        lblErIdentidad.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErIdentidad.setForeground(new java.awt.Color(255, 0, 0));
        lblErIdentidad.setText("Error");

        cmbNivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cmbNivel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbNivelItemStateChanged(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nivel:");

        lblErNivel.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErNivel.setForeground(new java.awt.Color(255, 0, 0));
        lblErNivel.setText("Error");

        lblObligatorio2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio2.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio2.setText("*");
        lblObligatorio2.setToolTipText("");

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Puesto de Trabajo:");

        cmbPuesto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cmbPuesto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPuestoItemStateChanged(evt);
            }
        });

        lblErPuesto.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErPuesto.setForeground(new java.awt.Color(255, 0, 0));
        lblErPuesto.setText("Error");

        lblObligatorio3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio3.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio3.setText("*");
        lblObligatorio3.setToolTipText("");

        lblCentro.setForeground(new java.awt.Color(0, 0, 0));
        lblCentro.setText("Centro de Costo:");

        cmbCentroCosto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cmbCentroCosto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCentroCostoItemStateChanged(evt);
            }
        });

        lblErCentroCosto.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErCentroCosto.setForeground(new java.awt.Color(255, 0, 0));
        lblErCentroCosto.setText("Error");

        lblObligatorioCentro.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorioCentro.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorioCentro.setText("*");
        lblObligatorioCentro.setToolTipText("");

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

        lblArea.setForeground(new java.awt.Color(0, 0, 0));
        lblArea.setText("Area:");

        cmbArea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cmbArea.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAreaItemStateChanged(evt);
            }
        });

        lblErArea.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErArea.setForeground(new java.awt.Color(255, 0, 0));
        lblErArea.setText("Error");

        lblObligatorioArea.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorioArea.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorioArea.setText("*");
        lblObligatorioArea.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblErNombre)
                                .addContainerGap())
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dtpIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                    .addComponent(lblObligatorio5)
                                                    .addGap(185, 185, 185)
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lblErIdentidad)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                            .addComponent(txtIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(lblObligatorio4))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                            .addComponent(dtpNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(lblObligatorio6))
                                                        .addComponent(lblErNacimiento)))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                                    .addComponent(lblObligatorio)
                                                    .addGap(18, 18, 18)
                                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                            .addComponent(jLabel3)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(lblErNivel)
                                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                    .addComponent(cmbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addGap(18, 18, 18)
                                                                    .addComponent(lblObligatorio2))))
                                                        .addComponent(jLabel17))))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(jLabel6))))
                                    .addComponent(lblErIngreso))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblCentro)
                                        .addComponent(jLabel7))
                                    .addComponent(lblArea, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(cmbCentroCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblObligatorioCentro))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(cmbPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblObligatorio3))
                                            .addComponent(lblErCentroCosto)
                                            .addComponent(lblErPuesto))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(cmbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblObligatorioArea))
                                            .addComponent(lblErArea))
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblObligatorio1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblErCodigo)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblObligatorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblErCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblObligatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(lblErNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtpIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblObligatorio5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblErIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dtpNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblObligatorio6, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblErNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblObligatorio3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblErPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblObligatorio4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCentro, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCentroCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblObligatorioCentro, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblErIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblErCentroCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblObligatorio2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblArea, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblObligatorioArea, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblErNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblErArea, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tblCentro.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tblCentro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Cod. Empleado", "Nombre Completo", "Fecha de Ingreso", "Fecha de Nacimiento", "Identidad del Empleado", "Nivel", "Puesto de Trabajo", "Centro de Costo", "Area"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        cmbBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Codigo", "Identidad" }));
        cmbBuscar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBuscarItemStateChanged(evt);
            }
        });

        jLabel2.setText("Buscar por:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1341, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LimpiarErrores() {
        val.TXTcorrecto(txtCodigo, lblErCodigo);
        val.TXTcorrecto(txtNombre, lblErNombre);
        val.GENcorrecto(lblErIngreso);
        val.GENcorrecto(lblErNacimiento);
        val.TXTcorrecto(txtIdentidad, lblErIdentidad);
        val.CMBcorrecto(cmbNivel, lblErNivel);
        val.CMBcorrecto(cmbPuesto, lblErPuesto);
        val.CMBcorrecto(cmbCentroCosto, lblErCentroCosto);
        val.CMBcorrecto(cmbArea, lblErArea);
    }

    private void CargarListas() {
        // Lista de JComboBox para actualizar datos
        JComboBox[] comboBoxes = {cmbNivel, cmbPuesto, cmbCentroCosto, cmbArea};

        // Recorrer cada JComboBox y eliminar los elementos
        for (JComboBox comboBox : comboBoxes) {
            DefaultComboBoxModel model = (DefaultComboBoxModel) comboBox.getModel();
            model.removeAllElements();
            model.addElement("");
        }
        //cargar los datos de los combobox
        DatosTablas Datos = new DatosTablas();
        Datos.cargarComboBox("select nivel from VistaNivel", "nivel", cmbNivel);
        Datos.cargarComboBox("select puesto from VistaPuestos", "puesto", cmbPuesto);
        Datos.cargarComboBox("select CentroCosto from VistaCentroCosto", "CentroCosto", cmbCentroCosto);
        Datos.cargarComboBox("select Area from VistaArea", "Area", cmbArea);
    }

    private boolean ValidarCampos() {
        int valor1 = 1;
        String error;

        return valor1 == 1; //Expreciones regulares de los campos
    }

    private void restringcion() {
        lblCentro.setVisible(true);
        lblArea.setVisible(true);
        cmbCentroCosto.setVisible(true);
        cmbArea.setVisible(true);
        lblObligatorioCentro.setVisible(true);
        lblObligatorioArea.setVisible(true);
        lblErArea.setVisible(false);
        lblErCentroCosto.setVisible(false);
        cmbCentroCosto.setSelectedItem("");
        cmbArea.setSelectedItem("");
        if (cmbNivel.getSelectedIndex() != -1) {
            if (cmbNivel.getSelectedItem().equals("Oficial")) {
                cmbCentroCosto.setVisible(false);
                lblCentro.setVisible(false);
                lblArea.setVisible(false);
                cmbArea.setVisible(false);
                lblObligatorioCentro.setVisible(false);
                lblObligatorioArea.setVisible(false);
                lblErArea.setVisible(false);
                lblErCentroCosto.setVisible(false);
            } else if (cmbNivel.getSelectedItem().equals("Gerencia")) {
                cmbCentroCosto.setVisible(false);
                lblCentro.setVisible(false);
                lblErCentroCosto.setVisible(false);
                lblObligatorioCentro.setVisible(false);
            } else {
                cmbArea.setVisible(false);
                lblArea.setVisible(false);
                lblErArea.setVisible(false);
                lblObligatorioArea.setVisible(false);
            }
        }
    }
    private void tblCentroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCentroMouseClicked
        Limpiar();
        CargarListas();

        //se trata de obtener los datos de la tabla para mostrarlos en las casillas respectivas con ayuda de sql
        try {
            AccionesCrud classcrud = new AccionesCrud();
            ResultSet rs = classcrud.Seleccion(tblCentro, "select * from [VistaEmpleadosCrud] where [CodEmpleado]=?", "Cod. Empleado");
            while (rs.next()) {
                txtCodigo.setText(rs.getString("CodEmpleado"));
                txtNombre.setText(rs.getString("NombreCompleto"));
                txtIdentidad.setText(rs.getString("IdEmpleado"));
                cmbNivel.setSelectedItem(rs.getString("nivel"));
                restringcion();
                cmbPuesto.setSelectedItem(rs.getString("puesto"));
                cmbCentroCosto.setSelectedItem(rs.getString("CentroCostos"));
                cmbArea.setSelectedItem(rs.getString("Area"));
                //formato para mostrar la fecha en el JDateChooser
                SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
                Date fecha;
                try {
                    fecha = formatofecha.parse(rs.getString("FechaIngreso"));
                    dtpIngreso.setDate(fecha);
                } catch (ParseException ex) {
                    Logger.getLogger(pnlSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    fecha = formatofecha.parse(rs.getString("FechaNacimiento"));
                    dtpNacimiento.setDate(fecha);
                } catch (ParseException ex) {
                    Logger.getLogger(pnlSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            txtCodigo.enable(false);
            btnModificar.setVisible(true);
            btnEliminar.setVisible(true);
            btnCancelar.setVisible(true);
            btnGuardar.setVisible(false);
            LimpiarErrores();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }//GEN-LAST:event_tblCentroMouseClicked

    private Object[] ArregloDatos() {
        //se crea un arreglo de objetos para enviar a la clase de AccionesCrud y la funcion de Guardar_Modificar
        Object[] datos = new Object[9];
        datos[0] = txtCodigo.getText();
        datos[6] = txtNombre.getText();
        try {
            Date date = dtpIngreso.getDate();
            long d = date.getTime();
            java.sql.Date fecha = new java.sql.Date(d);
            datos[1] = fecha.toString();
        } catch (Exception e) {
            datos[1] = "";
        }
        try {
            Date date = dtpNacimiento.getDate();
            long d = date.getTime();
            java.sql.Date fecha = new java.sql.Date(d);
            datos[2] = fecha.toString();
        } catch (Exception e) {
            datos[2] = "";
        }
        datos[3] = txtIdentidad.getText();
        datos[4] = cmbNivel.getSelectedItem().toString();
        datos[5] = cmbPuesto.getSelectedItem().toString();
         if (cmbCentroCosto.getSelectedItem() == null) {
        datos[7] ="";}
         else{
           datos[7] = cmbCentroCosto.getSelectedItem().toString();
         }
        if (cmbArea.getSelectedItem() == null) {
            datos[8] = "";
        }else{
        datos[8] = cmbArea.getSelectedItem().toString();
        }
        return datos;
    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        LimpiarErrores();
        if (ValidarCampos()) {
            AccionesCrud classcrud = new AccionesCrud();
            //se crea un arreglo de objetos para enviar a la clase de AccionesCrud y la funcion de Guardar_Modificar
            if (classcrud.Guardar_Modificar(ArregloDatos(), "exec [AgregarEmpleado] ?, ? ,?  ,? ,? ,? ,? ,? ,?")) {
                CargarTabla();
                Limpiar();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        LimpiarErrores();
        if (ValidarCampos()) {
            AccionesCrud classcrud = new AccionesCrud();
            //se crea un arreglo de objetos para enviar a la clase de AccionesCrud y la funcion de Guardar_Modificar
            if (classcrud.Guardar_Modificar(ArregloDatos(), "exec [ModificarEmpleado] ?, ? ,?  ,? ,? ,? ,? ,? ,?")) {
                CargarTabla();
                Limpiar();
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
  LimpiarErrores();
        if (ValidarCampos()) {
            AccionesCrud classcrud = new AccionesCrud();
            //se crea un arreglo de objetos para enviar a la clase de AccionesCrud y la funcion de Guardar_Modificar
            if (classcrud.Eliminar(txtCodigo, "exec [EliminarEmpleado] ?")) {
                CargarTabla();
                Limpiar();
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        //al escribir se quita el estado de error
        val.TXTcorrecto(txtCodigo, lblErCodigo);
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        val.EntradaNumeros(txtCodigo, evt, 4);
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        //al escribir se quita el estado de error
        val.TXTcorrecto(txtNombre, lblErNombre);
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // validado para un campo de tipo linea telefonica
        val.EntradaSoloLetas(txtNombre, evt, 80);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void dtpIngresoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtpIngresoPropertyChange
        //se quita el error al escribir en el campo de compra
        val.GENcorrecto(lblErIngreso);
    }//GEN-LAST:event_dtpIngresoPropertyChange

    private void dtpNacimientoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtpNacimientoPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_dtpNacimientoPropertyChange

    private void txtIdentidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentidadKeyReleased
        //al escribir se quita el estado de error
        val.TXTcorrecto(txtIdentidad, lblErIdentidad);
    }//GEN-LAST:event_txtIdentidadKeyReleased

    private void txtIdentidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdentidadKeyTyped
        // validado para un campo de tipo IMEI
        val.EntradaNumeros(txtIdentidad, evt, 15);
    }//GEN-LAST:event_txtIdentidadKeyTyped

    private void cmbNivelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbNivelItemStateChanged
        //al seleccionar un item se quita el estado de error
        val.CMBcorrecto(cmbNivel, lblErNivel);
        restringcion();
    }//GEN-LAST:event_cmbNivelItemStateChanged

    private void cmbPuestoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPuestoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPuestoItemStateChanged

    private void cmbCentroCostoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCentroCostoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCentroCostoItemStateChanged

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cmbAreaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAreaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAreaItemStateChanged

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        CargarTabla();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        //switch para decidir que validacion establecer cada ves que se preciona una tecla en buscar
        switch (Busqueda) {
            case "Nombre":
            val.EntradaSoloLetas(txtBuscar, evt, 80);
            break;
            case "Codigo":
            val.EntradaNumeros(txtBuscar, evt, 4);
            break;
            case "Identidad":
            val.EntradaNumeros(txtBuscar, evt,13);
            break;
            default:
            break;
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void cmbBuscarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBuscarItemStateChanged
        //Cada vez que se cambia el estado del combobox se cambia el filtro de busqueda global para la funcion de busqueda
        String elementoSeleccionado = (String) cmbBuscar.getSelectedItem();
        switch (elementoSeleccionado) {
            case "Nombre":
            Busqueda = "NombreCompleto";
            break;
            case "Codigo":
            Busqueda = "CodEmpleado";
            break;
            case "Identidad":
            Busqueda = "IdEmpleado";
            break;
            default:
            break;
        }
        CargarTabla();
        txtBuscar.setText("");
    }//GEN-LAST:event_cmbBuscarItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonMetro btnCancelar;
    private rsbuttom.RSButtonMetro btnEliminar;
    private rsbuttom.RSButtonMetro btnGuardar;
    private rsbuttom.RSButtonMetro btnModificar;
    private javax.swing.JComboBox<String> cmbArea;
    private javax.swing.JComboBox<String> cmbBuscar;
    private javax.swing.JComboBox<String> cmbCentroCosto;
    private javax.swing.JComboBox<String> cmbNivel;
    private javax.swing.JComboBox<String> cmbPuesto;
    private com.toedter.calendar.JDateChooser dtpIngreso;
    private com.toedter.calendar.JDateChooser dtpNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblArea;
    private javax.swing.JLabel lblCentro;
    private javax.swing.JLabel lblErArea;
    private javax.swing.JLabel lblErCentroCosto;
    private javax.swing.JLabel lblErCodigo;
    private javax.swing.JLabel lblErIdentidad;
    private javax.swing.JLabel lblErIngreso;
    private javax.swing.JLabel lblErNacimiento;
    private javax.swing.JLabel lblErNivel;
    private javax.swing.JLabel lblErNombre;
    private javax.swing.JLabel lblErPuesto;
    private javax.swing.JLabel lblObligatorio;
    private javax.swing.JLabel lblObligatorio1;
    private javax.swing.JLabel lblObligatorio2;
    private javax.swing.JLabel lblObligatorio3;
    private javax.swing.JLabel lblObligatorio4;
    private javax.swing.JLabel lblObligatorio5;
    private javax.swing.JLabel lblObligatorio6;
    private javax.swing.JLabel lblObligatorioArea;
    private javax.swing.JLabel lblObligatorioCentro;
    private javax.swing.JTable tblCentro;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtIdentidad;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
