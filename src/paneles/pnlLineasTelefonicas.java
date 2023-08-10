package paneles;

import Clases.AccionesCrud;
import Clases.DatosTablas;
import Clases.validaciones;
import app.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import paneles.ExtraDLineasTelefonicas.Disponibilidad;

/**
 * @ErickMoncada controlador panel lineas telefonicas
 */
public class pnlLineasTelefonicas extends javax.swing.JPanel {

    public pnlLineasTelefonicas(String NIVEL) {
        initComponents();
        CargarDatosPrincipal();
        lblTotalPlanNuevo.setText("Suma Total de los Plan Nuevos: " + SumarValores("Plan Nuevo") + "$");
        lblTotalPresupuesto.setText("Suma Total de los Presupuestos: " + SumarValores("Presupuesto") + "$");
        Limpiar();
        txtOtro.setVisible(false);
        btgPago.add(rdbSiSeguro);
        btgPago.add(rdbNoSeguro);
        btgFirma.add(rdbSiFirma);
        btgFirma.add(rdbNoFirma);
        btgFirma.add(rdbOtro);
        asignarEventos();
        if ("Lector".equals(NIVEL)) {
            jPanel4.setVisible(false);
            jPanel5.setVisible(false);
            jPanel6.setVisible(false);
            //eliminar opciones del menu desplegable
            jMenuItem1.setVisible(false);
            jMenuItem2.setVisible(false);
        }
        NivelAcceso = NIVEL;
        //---------------------------------se establece que no se pueda pegar texto en los campos
        val.NegarPegado(txtLinea);
        val.NegarPegado(txtNumExpediente);
        val.NegarPegado(txtImei);
        val.NegarPegado(txtOtro);
        val.NegarPegado(txtAnterior);
        val.NegarPegado(txtNuevo);
        val.NegarPegado(txtPresupuesto);
        val.NegarPegado(txtMensual);
        val.NegarPegado(txtBuscar);
        //------------------------------------------------------------------------------
    }
    //se inicializa para la busqueda por medio de Imei
    String Busqueda = "Imei";
    //se Inicializa la variabl del nivel para tneerlo en el Jframe
    String NivelAcceso;
    //se inicializa la clase de validaciones
    validaciones val = new validaciones();
    //se crean los grupos de botones para los radioButton
    ButtonGroup btgPago = new ButtonGroup();
    ButtonGroup btgFirma = new ButtonGroup();
    //inicializar variable de año limite
    int maxyear;

    private String SumarValores(String nombreColumna) {
        //funcion para sumar la cantidad que brinda una columna
        double total = 0;
        int indiceColumna = tblLineas.getColumnModel().getColumnIndex(nombreColumna);
        for (int i = 0; i < tblLineas.getRowCount(); i++) {
            Object value = tblLineas.getValueAt(i, indiceColumna);
            total += ((Number) value).doubleValue();
        }
        return String.valueOf(total);

    }

    private void Limpiar() {
        //funcion para reiniciar todos los valores de la pantalla
        btnCancelar.setVisible(false);
        btnModificar.setVisible(false);
        btnEliminar.setVisible(false);
        btnGuardar.setVisible(true);
        txtLinea.enable(true);

        txtLinea.setText("");
        txtNumExpediente.setText("");
        txtAnterior.setText("");
        txtNuevo.setText("");
        txtPresupuesto.setText("");
        txtMensual.setText("");
        txtOtro.setText("");
        cmbDisponibilidad.setSelectedIndex(-1);
        txtYear.setValue(maxyear);
        txtCuotas.setValue(0);
        txtImei.setText("");
        dtpAsignacion.setDate(null);
        dtpCambio.setDate(null);
        dtpFacturacion.setDate(null);
        btgFirma.clearSelection();
        btgPago.clearSelection();
        txtOtro.setVisible(false);
        txtLinea.requestFocus();
        LimpiarErrores();
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
        val.asignarEventosMouse(lblObligatorio7);
        val.asignarEventosMouse(lblObligatorio8);
        val.asignarEventosMouse(lblObligatorio9);
        val.asignarEventosMouse(lblObligatorio10);
        val.asignarEventosMouse(lblObligatorio11);
        val.asignarEventosMouse(lblObligatorio12);
        val.asignarEventosMouse(lblObligatorio13);
    }

    private void CargarDatosPrincipal() {
        //rellenar datos de la tabla
        DatosTablas Datos = new DatosTablas();
        Datos.CargarTabla(tblLineas, "select * from [VistaLineasTelefonicas]");
        //llenar los datos de los combobox
        CargarListas();
        //obtener la hora del ser5vidor para poner de limite
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT GETDATE() AS HoraActual");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Timestamp serverTime = rs.getTimestamp("HoraActual");
                Date maxDate = new Date(serverTime.getTime());
                String yearString = String.valueOf(maxDate);
                String ultimosCuatroDigitos = yearString.substring(yearString.length() - 4);
                maxyear = Integer.parseInt(ultimosCuatroDigitos);
                dtpAsignacion.setMaxSelectableDate(maxDate);
                dtpCambio.setMaxSelectableDate(maxDate);
                dtpFacturacion.setMaxSelectableDate(maxDate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(pnlSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
        }

        SpinnerNumberModel model = (SpinnerNumberModel) txtYear.getModel();
        NumberFormat format = new DecimalFormat("####");
        format.setGroupingUsed(false);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(txtYear, "####");
        editor.getFormat().applyPattern("####");
        editor.getFormat().setGroupingUsed(false);
        txtYear.setEditor(editor);

    }

    private void CargarListas() {
        // Lista de JComboBox para actualizar datos
        JComboBox[] comboBoxes = {cmbDisponibilidad};

        // Recorrer cada JComboBox y eliminar los elementos
        for (JComboBox comboBox : comboBoxes) {
            DefaultComboBoxModel model = (DefaultComboBoxModel) comboBox.getModel();
            model.removeAllElements();
            model.addElement("");
        }
        //cargar los datos de los combobox
        DatosTablas Datos = new DatosTablas();
        Datos.cargarComboBox("select Disponible from VistaDisponibilidades", "Disponible", cmbDisponibilidad);
    }

    private void LimpiarErrores() {
        //usando la clase de validaciones se establecen los valores en correcto
        val.TXTcorrecto(txtLinea, lblErLinea);
        val.TXTcorrecto(txtNumExpediente, lblErExpediente);
        val.CMBcorrecto(cmbDisponibilidad, lblErDisponible);
        val.GENcorrecto(lblErCuotas);
        val.TXTcorrecto(txtImei, lblErImei);
        val.GENcorrecto(lblErAsignacion);
        val.GENcorrecto(lblErCambio);
        val.GENcorrecto(lblErFacturacion);
        val.GENcorrecto(lblErPago);
        val.TXTcorrecto(txtOtro, lblErFirma);
        val.TXTcorrecto(txtAnterior, lblErAnterior);
        val.TXTcorrecto(txtNuevo, lblErNuevo);
        val.TXTcorrecto(txtPresupuesto, lblErPresupuesto);
        val.TXTcorrecto(txtMensual, lblErMensual);
    }

    private boolean ValidarCampos() {
        int valor1 = 1;
        String error;
        //validar que no este vacio u otro parametro mas
        if (txtLinea.getText().isEmpty() || !txtLinea.getText().matches("\\d{4}-\\d{4}")) {
            //asignar 0 al valor para devolver falso en la validacion
            valor1 = 0;
            //mensaje de error para el respectivo campo de texto
            error = "Escriba un número de teléfono valido";
            //asignar colores de error a cada campo
            val.TXTincorrecto(txtLinea, lblErLinea, error);
        }
        if (txtNumExpediente.getText().isEmpty()) {
            valor1 = 0;
            error = "Escriba un número de expediente";
            val.TXTincorrecto(txtNumExpediente, lblErExpediente, error);
        }
        if (cmbDisponibilidad.getSelectedItem() == null || cmbDisponibilidad.getSelectedItem() == "") {
            valor1 = 0;
            error = "Debe seleccionar la disponibilidad";
            val.CMBincorrecto(cmbDisponibilidad, lblErDisponible, error);
        }
        if ((Integer) txtCuotas.getValue() <= 0) {
            valor1 = 0;
            error = "Las cuotas no pueden ser menor o igual a 0";
            val.GENIncorrecto(lblErCuotas, error);
        }
        if (txtImei.getText().isEmpty() || !txtImei.getText().matches("\\d{15}")) {
            valor1 = 0;
            error = "Escriba un número de IMEI válido";
            val.TXTincorrecto(txtImei, lblErImei, error);
        }

        //se verifica si la fecha esta bien
        if (val.ValidarFechas(dtpAsignacion, lblErAsignacion) == 0) {
            valor1 = 0;
        }
        //se verifica si la fecha esta bien
        if (val.ValidarFechas(dtpCambio, lblErCambio) == 0) {
            valor1 = 0;
        }
        //se verifica si la fecha esta bien
        if (val.ValidarFechas(dtpFacturacion, lblErFacturacion) == 0) {
            valor1 = 0;
        }

        if (btgPago.getSelection() == null) {
            valor1 = 0;
            error = "Debe seleccionar una opción de Pago de Seguro";
            val.GENIncorrecto(lblErPago, error);
        }
        if (rdbOtro.isSelected() && txtOtro.getText().isEmpty()) {
            valor1 = 0;
            error = "EL campo de Otro no puede estar en blanco";
            val.TXTincorrecto(txtOtro, lblErFirma, error);
        } else if (btgFirma.getSelection() == null) {
            valor1 = 0;
            error = "Debe seleccionar una opción de Firma";
            val.GENIncorrecto(lblErFirma, error);
        }

        if (!txtAnterior.getText().isEmpty()) {
            if (Double.parseDouble(txtAnterior.getText()) < 5) {
                valor1 = 0;
                error = "La cantidad del plan anterior no puede ser menor a 5$";
                val.TXTincorrecto(txtAnterior, lblErAnterior, error);
            }
        } else {
            valor1 = 0;
            error = "La cantidad del plan anterior no puede estar en blanco";
            val.TXTincorrecto(txtAnterior, lblErAnterior, error);
        }

        if (!txtNuevo.getText().isEmpty()) {
            if (Double.parseDouble(txtNuevo.getText()) < 5) {
                valor1 = 0;
                error = "La cantidad del plan Nuevo no puede ser menor a 5$";
                val.TXTincorrecto(txtNuevo, lblErNuevo, error);
            }
        } else {
            valor1 = 0;
            error = "La cantidad del plan Nuevo no puede estar en blanco";
            val.TXTincorrecto(txtNuevo, lblErNuevo, error);
        }

        if (txtPresupuesto.getText().isEmpty()) {
            valor1 = 0;
            error = "Escriba la cantidad del presupuesto";
            val.TXTincorrecto(txtPresupuesto, lblErPresupuesto, error);
        }
        if (txtMensual.getText().isEmpty()) {
            valor1 = 0;
            error = "Escriba la cantidad del Reconocimiento Mensual";
            val.TXTincorrecto(txtMensual, lblErMensual, error);
        }

        return valor1 == 1; //Expreciones regulares de los campos
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        cmbBuscar = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblTotalPlanNuevo = new javax.swing.JLabel();
        lblTotalPresupuesto = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLineas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtLinea = new javax.swing.JTextField();
        btnCancelar = new rsbuttom.RSButtonMetro();
        lblErLinea = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNumExpediente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblErExpediente = new javax.swing.JLabel();
        cmbDisponibilidad = new javax.swing.JComboBox<>();
        btnDisponibilidad = new javax.swing.JButton();
        lblErDisponible = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtCuotas = new javax.swing.JSpinner();
        lblErCuotas = new javax.swing.JLabel();
        txtImei = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        lblErImei = new javax.swing.JLabel();
        lblObligatorio = new javax.swing.JLabel();
        lblObligatorio1 = new javax.swing.JLabel();
        lblObligatorio2 = new javax.swing.JLabel();
        lblObligatorio3 = new javax.swing.JLabel();
        lblObligatorio4 = new javax.swing.JLabel();
        btnGuardar = new rsbuttom.RSButtonMetro();
        btnModificar = new rsbuttom.RSButtonMetro();
        btnEliminar = new rsbuttom.RSButtonMetro();
        txtYear = new javax.swing.JSpinner();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        dtpAsignacion = new com.toedter.calendar.JDateChooser();
        lblErAsignacion = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dtpCambio = new com.toedter.calendar.JDateChooser();
        lblErCambio = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dtpFacturacion = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        rdbSiFirma = new javax.swing.JRadioButton();
        rdbNoFirma = new javax.swing.JRadioButton();
        rdbOtro = new javax.swing.JRadioButton();
        txtOtro = new javax.swing.JTextField();
        lblErFirma = new javax.swing.JLabel();
        lblObligatorio9 = new javax.swing.JLabel();
        lblErFacturacion = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        rdbSiSeguro = new javax.swing.JRadioButton();
        rdbNoSeguro = new javax.swing.JRadioButton();
        lblObligatorio8 = new javax.swing.JLabel();
        lblErPago = new javax.swing.JLabel();
        lblObligatorio5 = new javax.swing.JLabel();
        lblObligatorio6 = new javax.swing.JLabel();
        lblObligatorio7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtAnterior = new javax.swing.JTextField();
        txtNuevo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lblErAnterior = new javax.swing.JLabel();
        lblErNuevo = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtPresupuesto = new javax.swing.JTextField();
        txtMensual = new javax.swing.JTextField();
        lblErPresupuesto = new javax.swing.JLabel();
        lblErMensual = new javax.swing.JLabel();
        lblObligatorio10 = new javax.swing.JLabel();
        lblObligatorio11 = new javax.swing.JLabel();
        lblObligatorio12 = new javax.swing.JLabel();
        lblObligatorio13 = new javax.swing.JLabel();

        jMenuItem1.setText("Actualizar datos desplegables");
        jMenuItem1.setName("actualizarTabla"); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        jMenuItem2.setText("Limpiar Campos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        setBackground(new java.awt.Color(255, 255, 255));
        setNextFocusableComponent(txtLinea);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setComponentPopupMenu(jPopupMenu1);
        jPanel1.setNextFocusableComponent(txtLinea);

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        cmbBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IMEI", "Línea de Teléfono", "N. expediente", "Centro de Costo", "Disponibilidad" }));
        cmbBuscar.setSelectedItem("IMEI");
        cmbBuscar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBuscarItemStateChanged(evt);
            }
        });

        jLabel2.setText("Buscar por:");

        jPanel3.setOpaque(false);

        lblTotalPlanNuevo.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalPlanNuevo.setText("Total del Plan Nuevo:");

        lblTotalPresupuesto.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalPresupuesto.setText("Total del Presupuesto:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotalPlanNuevo)
                    .addComponent(lblTotalPresupuesto))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTotalPlanNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalPresupuesto)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(744, 744, 744))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(17, 17, 17))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jScrollPane1.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(800, 403));

        tblLineas.setBackground(new java.awt.Color(204, 255, 204));
        tblLineas.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tblLineas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Expediente", "Firma", "Línea Telefónica", "Cod.Empleado", "Usuario", "Centro Costo", "Estado Equipo", "Facturado", "Cambio de Equipo", "Equipo", "IMEI", "¿Paga Seguro?", "Presupuesto", "Cuotas", "Plan Anterior", "Plan Nuevo", "Disponibilidad", "Jefe", "Categoría", "Reconocido del Plan", "Planilla", "Año de  renovacion", "Asignado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLineas.setGridColor(new java.awt.Color(0, 0, 0));
        tblLineas.setSelectionBackground(new java.awt.Color(51, 153, 0));
        tblLineas.getTableHeader().setReorderingAllowed(false);
        tblLineas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLineasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLineas);
        if (tblLineas.getColumnModel().getColumnCount() > 0) {
            tblLineas.getColumnModel().getColumn(0).setResizable(false);
            tblLineas.getColumnModel().getColumn(0).setPreferredWidth(60);
            tblLineas.getColumnModel().getColumn(1).setMinWidth(60);
            tblLineas.getColumnModel().getColumn(1).setPreferredWidth(70);
            tblLineas.getColumnModel().getColumn(1).setMaxWidth(80);
            tblLineas.getColumnModel().getColumn(2).setMinWidth(80);
            tblLineas.getColumnModel().getColumn(2).setPreferredWidth(90);
            tblLineas.getColumnModel().getColumn(2).setMaxWidth(100);
            tblLineas.getColumnModel().getColumn(3).setMinWidth(70);
            tblLineas.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblLineas.getColumnModel().getColumn(3).setMaxWidth(90);
            tblLineas.getColumnModel().getColumn(4).setMinWidth(100);
            tblLineas.getColumnModel().getColumn(4).setPreferredWidth(130);
            tblLineas.getColumnModel().getColumn(4).setMaxWidth(300);
            tblLineas.getColumnModel().getColumn(5).setMinWidth(100);
            tblLineas.getColumnModel().getColumn(5).setPreferredWidth(150);
            tblLineas.getColumnModel().getColumn(5).setMaxWidth(330);
            tblLineas.getColumnModel().getColumn(6).setMinWidth(80);
            tblLineas.getColumnModel().getColumn(6).setPreferredWidth(90);
            tblLineas.getColumnModel().getColumn(6).setMaxWidth(110);
            tblLineas.getColumnModel().getColumn(7).setMinWidth(80);
            tblLineas.getColumnModel().getColumn(7).setPreferredWidth(130);
            tblLineas.getColumnModel().getColumn(7).setMaxWidth(140);
            tblLineas.getColumnModel().getColumn(8).setMinWidth(80);
            tblLineas.getColumnModel().getColumn(8).setPreferredWidth(130);
            tblLineas.getColumnModel().getColumn(8).setMaxWidth(140);
            tblLineas.getColumnModel().getColumn(9).setMinWidth(90);
            tblLineas.getColumnModel().getColumn(9).setPreferredWidth(110);
            tblLineas.getColumnModel().getColumn(9).setMaxWidth(300);
            tblLineas.getColumnModel().getColumn(10).setMinWidth(130);
            tblLineas.getColumnModel().getColumn(10).setPreferredWidth(130);
            tblLineas.getColumnModel().getColumn(10).setMaxWidth(130);
            tblLineas.getColumnModel().getColumn(11).setMinWidth(60);
            tblLineas.getColumnModel().getColumn(11).setPreferredWidth(60);
            tblLineas.getColumnModel().getColumn(11).setMaxWidth(60);
            tblLineas.getColumnModel().getColumn(12).setMinWidth(50);
            tblLineas.getColumnModel().getColumn(12).setPreferredWidth(90);
            tblLineas.getColumnModel().getColumn(12).setMaxWidth(100);
            tblLineas.getColumnModel().getColumn(13).setMinWidth(50);
            tblLineas.getColumnModel().getColumn(13).setPreferredWidth(60);
            tblLineas.getColumnModel().getColumn(13).setMaxWidth(60);
            tblLineas.getColumnModel().getColumn(14).setMinWidth(50);
            tblLineas.getColumnModel().getColumn(14).setPreferredWidth(90);
            tblLineas.getColumnModel().getColumn(14).setMaxWidth(100);
            tblLineas.getColumnModel().getColumn(15).setMinWidth(50);
            tblLineas.getColumnModel().getColumn(15).setPreferredWidth(90);
            tblLineas.getColumnModel().getColumn(15).setMaxWidth(100);
            tblLineas.getColumnModel().getColumn(16).setMinWidth(80);
            tblLineas.getColumnModel().getColumn(16).setPreferredWidth(90);
            tblLineas.getColumnModel().getColumn(16).setMaxWidth(120);
            tblLineas.getColumnModel().getColumn(17).setMinWidth(60);
            tblLineas.getColumnModel().getColumn(17).setPreferredWidth(60);
            tblLineas.getColumnModel().getColumn(17).setMaxWidth(60);
            tblLineas.getColumnModel().getColumn(18).setMinWidth(60);
            tblLineas.getColumnModel().getColumn(18).setPreferredWidth(60);
            tblLineas.getColumnModel().getColumn(18).setMaxWidth(60);
            tblLineas.getColumnModel().getColumn(19).setMinWidth(60);
            tblLineas.getColumnModel().getColumn(19).setPreferredWidth(110);
            tblLineas.getColumnModel().getColumn(19).setMaxWidth(110);
            tblLineas.getColumnModel().getColumn(20).setMinWidth(60);
            tblLineas.getColumnModel().getColumn(20).setPreferredWidth(80);
            tblLineas.getColumnModel().getColumn(20).setMaxWidth(90);
        }

        jPanel4.setComponentPopupMenu(jPopupMenu1);
        jPanel4.setNextFocusableComponent(txtLinea);
        jPanel4.setOpaque(false);

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Línea Telefónica:");

        txtLinea.setNextFocusableComponent(txtNumExpediente);
        txtLinea.setPreferredSize(new java.awt.Dimension(65, 26));
        txtLinea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLineaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLineaKeyTyped(evt);
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

        lblErLinea.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErLinea.setForeground(new java.awt.Color(255, 0, 0));
        lblErLinea.setText("Error");

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Número de Expediente:");

        txtNumExpediente.setNextFocusableComponent(cmbDisponibilidad);
        txtNumExpediente.setPreferredSize(new java.awt.Dimension(65, 26));
        txtNumExpediente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNumExpedienteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumExpedienteKeyTyped(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Disponibilidad:");

        lblErExpediente.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErExpediente.setForeground(new java.awt.Color(255, 0, 0));
        lblErExpediente.setText("Error");

        cmbDisponibilidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cmbDisponibilidad.setNextFocusableComponent(txtYear);
        cmbDisponibilidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDisponibilidadItemStateChanged(evt);
            }
        });

        btnDisponibilidad.setText("+");
        btnDisponibilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisponibilidadActionPerformed(evt);
            }
        });

        lblErDisponible.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErDisponible.setForeground(new java.awt.Color(255, 0, 0));
        lblErDisponible.setText("Error");

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Año de Renovación:");

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Cuotas:");

        txtCuotas.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtCuotas.setNextFocusableComponent(txtImei);
        txtCuotas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                txtCuotasStateChanged(evt);
            }
        });

        lblErCuotas.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErCuotas.setForeground(new java.awt.Color(255, 0, 0));
        lblErCuotas.setText("Error");

        txtImei.setNextFocusableComponent(dtpAsignacion);
        txtImei.setPreferredSize(new java.awt.Dimension(65, 26));
        txtImei.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtImeiKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtImeiKeyTyped(evt);
            }
        });

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("IMEI:");

        lblErImei.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErImei.setForeground(new java.awt.Color(255, 0, 0));
        lblErImei.setText("Error");

        lblObligatorio.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio.setText("*");
        lblObligatorio.setToolTipText("");

        lblObligatorio1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio1.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio1.setText("*");
        lblObligatorio1.setToolTipText("");

        lblObligatorio2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio2.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio2.setText("*");
        lblObligatorio2.setToolTipText("");

        lblObligatorio3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio3.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio3.setText("*");
        lblObligatorio3.setToolTipText("");

        lblObligatorio4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio4.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio4.setText("*");
        lblObligatorio4.setToolTipText("");

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
        btnGuardar.setNextFocusableComponent(txtBuscar);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
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

        btnEliminar.setBackground(new java.awt.Color(114, 191, 68));
        btnEliminar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnEliminar.setText("Remover");
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

        txtYear.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtYear.setNextFocusableComponent(txtImei);
        txtYear.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                txtYearStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtNumExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblObligatorio1))
                            .addComponent(lblErLinea)
                            .addComponent(lblErExpediente)
                            .addComponent(lblErDisponible)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(143, 143, 143)
                                .addComponent(lblErCuotas))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel18)
                                .addGap(12, 12, 12)
                                .addComponent(txtCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(lblObligatorio3))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cmbDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblObligatorio2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDisponibilidad))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblObligatorio)
                                .addGap(5, 5, 5)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblErImei)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtImei, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblObligatorio4))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObligatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErLinea, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumExpediente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblObligatorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErExpediente, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDisponibilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObligatorio2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErDisponible, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(lblErCuotas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtImei, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblObligatorio4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblErImei, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblObligatorio3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setComponentPopupMenu(jPopupMenu1);
        jPanel5.setNextFocusableComponent(txtLinea);
        jPanel5.setOpaque(false);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Fecha de Asignación:");

        dtpAsignacion.setMinSelectableDate(new java.util.Date(1262329270000L));
        dtpAsignacion.setNextFocusableComponent(dtpCambio);
        dtpAsignacion.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtpAsignacionPropertyChange(evt);
            }
        });

        lblErAsignacion.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErAsignacion.setForeground(new java.awt.Color(255, 0, 0));
        lblErAsignacion.setText("Error");

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Fecha Cambio de Equipo:");

        dtpCambio.setMinSelectableDate(new java.util.Date(1262329270000L));
        dtpCambio.setNextFocusableComponent(dtpFacturacion);
        dtpCambio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtpCambioPropertyChange(evt);
            }
        });

        lblErCambio.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErCambio.setForeground(new java.awt.Color(255, 0, 0));
        lblErCambio.setText("Error");

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Fecha de Facturación:");

        dtpFacturacion.setMinSelectableDate(new java.util.Date(1262329270000L));
        dtpFacturacion.setNextFocusableComponent(rdbSiSeguro);
        dtpFacturacion.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtpFacturacionPropertyChange(evt);
            }
        });

        jPanel2.setComponentPopupMenu(jPopupMenu1);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Firma:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 12, -1, -1));

        rdbSiFirma.setText("SI");
        rdbSiFirma.setName(""); // NOI18N
        rdbSiFirma.setNextFocusableComponent(rdbNoFirma);
        rdbSiFirma.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdbSiFirmaItemStateChanged(evt);
            }
        });
        jPanel2.add(rdbSiFirma, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 6, -1, -1));

        rdbNoFirma.setText("NO");
        rdbNoFirma.setNextFocusableComponent(rdbOtro);
        rdbNoFirma.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdbNoFirmaItemStateChanged(evt);
            }
        });
        jPanel2.add(rdbNoFirma, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 6, -1, -1));

        rdbOtro.setText("Otro");
        rdbOtro.setNextFocusableComponent(txtAnterior);
        rdbOtro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdbOtroItemStateChanged(evt);
            }
        });
        jPanel2.add(rdbOtro, new org.netbeans.lib.awtextra.AbsoluteConstraints(88, 46, -1, -1));

        txtOtro.setBackground(new java.awt.Color(255, 255, 255));
        txtOtro.setForeground(new java.awt.Color(0, 0, 0));
        txtOtro.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtOtro.setPreferredSize(new java.awt.Dimension(65, 26));
        txtOtro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOtroKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtOtroKeyTyped(evt);
            }
        });
        jPanel2.add(txtOtro, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 48, 182, -1));

        lblErFirma.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErFirma.setForeground(new java.awt.Color(255, 0, 0));
        lblErFirma.setText("Error");
        jPanel2.add(lblErFirma, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, -1, 13));

        lblObligatorio9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio9.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio9.setText("*");
        lblObligatorio9.setToolTipText("");
        jPanel2.add(lblObligatorio9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        lblErFacturacion.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErFacturacion.setForeground(new java.awt.Color(255, 0, 0));
        lblErFacturacion.setText("Error");

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("¿Paga seguro?");

        jPanel7.setOpaque(false);

        rdbSiSeguro.setText("SI");
        rdbSiSeguro.setNextFocusableComponent(rdbNoSeguro);
        rdbSiSeguro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdbSiSeguroItemStateChanged(evt);
            }
        });

        rdbNoSeguro.setText("NO");
        rdbNoSeguro.setNextFocusableComponent(rdbSiFirma);
        rdbNoSeguro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdbNoSeguroItemStateChanged(evt);
            }
        });

        lblObligatorio8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio8.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio8.setText("*");
        lblObligatorio8.setToolTipText("");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(rdbSiSeguro)
                .addGap(36, 36, 36)
                .addComponent(rdbNoSeguro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblObligatorio8)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdbSiSeguro)
                    .addComponent(rdbNoSeguro)
                    .addComponent(lblObligatorio8, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        lblErPago.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErPago.setForeground(new java.awt.Color(255, 0, 0));
        lblErPago.setText("Error");

        lblObligatorio5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio5.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio5.setText("*");
        lblObligatorio5.setToolTipText("");

        lblObligatorio6.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio6.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio6.setText("*");
        lblObligatorio6.setToolTipText("");

        lblObligatorio7.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio7.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio7.setText("*");
        lblObligatorio7.setToolTipText("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lblErPago)
                                .addContainerGap())
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblErCambio)
                                    .addComponent(lblErAsignacion)
                                    .addComponent(dtpAsignacion, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                    .addComponent(dtpCambio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dtpFacturacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblErFacturacion)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblObligatorio5)
                                    .addComponent(lblObligatorio6)
                                    .addComponent(lblObligatorio7)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 13, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dtpAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblObligatorio5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dtpCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblObligatorio6, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(dtpFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblObligatorio7, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErFacturacion, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblErPago, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setComponentPopupMenu(jPopupMenu1);
        jPanel6.setNextFocusableComponent(txtLinea);
        jPanel6.setOpaque(false);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Plan Anterior:    $ ");

        txtAnterior.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtAnterior.setNextFocusableComponent(txtNuevo);
        txtAnterior.setPreferredSize(new java.awt.Dimension(65, 26));
        txtAnterior.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAnteriorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAnteriorKeyTyped(evt);
            }
        });

        txtNuevo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtNuevo.setNextFocusableComponent(txtPresupuesto);
        txtNuevo.setPreferredSize(new java.awt.Dimension(65, 26));
        txtNuevo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNuevoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNuevoKeyTyped(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Plan Nuevo:    $ ");

        lblErAnterior.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErAnterior.setForeground(new java.awt.Color(255, 0, 0));
        lblErAnterior.setText("Error");

        lblErNuevo.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErNuevo.setForeground(new java.awt.Color(255, 0, 0));
        lblErNuevo.setText("Error");

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Presupuesto:    $ ");

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Reconocimiento Mensual:    $ ");

        txtPresupuesto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtPresupuesto.setNextFocusableComponent(txtMensual);
        txtPresupuesto.setPreferredSize(new java.awt.Dimension(65, 26));
        txtPresupuesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPresupuestoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPresupuestoKeyTyped(evt);
            }
        });

        txtMensual.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtMensual.setNextFocusableComponent(btnGuardar);
        txtMensual.setPreferredSize(new java.awt.Dimension(65, 26));
        txtMensual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMensualKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMensualKeyTyped(evt);
            }
        });

        lblErPresupuesto.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErPresupuesto.setForeground(new java.awt.Color(255, 0, 0));
        lblErPresupuesto.setText("Error");

        lblErMensual.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErMensual.setForeground(new java.awt.Color(255, 0, 0));
        lblErMensual.setText("Error");

        lblObligatorio10.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio10.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio10.setText("*");
        lblObligatorio10.setToolTipText("");

        lblObligatorio11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio11.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio11.setText("*");
        lblObligatorio11.setToolTipText("");

        lblObligatorio12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio12.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio12.setText("*");
        lblObligatorio12.setToolTipText("");

        lblObligatorio13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio13.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio13.setText("*");
        lblObligatorio13.setToolTipText("");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel13)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblErMensual)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblObligatorio11))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblObligatorio10))
                    .addComponent(lblErAnterior)
                    .addComponent(lblErNuevo)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblObligatorio12))
                    .addComponent(lblErPresupuesto)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtMensual, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblObligatorio13)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(lblObligatorio10, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(lblErAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(lblObligatorio11, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(lblErNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObligatorio12, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErPresupuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMensual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObligatorio13, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErMensual, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 2111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblLineasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLineasMouseClicked
        if (!"Lector".equals(NivelAcceso)) {
            LimpiarErrores();
            CargarListas();
            //se trata de obtener los datos de la tabla para mostrarlos en las casillas respectivas con ayuda de sql
            try {
                AccionesCrud classcrud = new AccionesCrud();
                ResultSet rs = classcrud.Seleccion(tblLineas, "select * from [VistaLineasTelefonicas] where [Linea]=?", "Línea Telefónica");
                while (rs.next()) {
                    txtLinea.setText(rs.getString("Linea"));
                    txtNumExpediente.setText(rs.getString("Expediente"));
                    cmbDisponibilidad.setSelectedItem(rs.getString("Disponible"));
                    txtYear.setValue(rs.getInt("Año Renovacion"));
                    txtCuotas.setValue(rs.getInt("Cuotas"));
                    txtImei.setText(rs.getString("Imei"));
                    txtAnterior.setText(rs.getString("PlanAnterior"));
                    txtNuevo.setText(rs.getString("NuevoPlan"));
                    txtPresupuesto.setText(rs.getString("Presupuesto"));
                    txtMensual.setText(rs.getString("ReconocidoPlan"));

                    //formato para mostrar la fecha en el JDateChooser
                    SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
                    Date fecha;
                    try {
                        fecha = formatofecha.parse(rs.getString("FechaAsignacion"));
                        dtpAsignacion.setDate(fecha);
                    } catch (ParseException ex) {
                        Logger.getLogger(pnlSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        fecha = formatofecha.parse(rs.getString("FechaCambioEquipo"));
                        dtpCambio.setDate(fecha);
                    } catch (ParseException ex) {
                        Logger.getLogger(pnlSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        fecha = formatofecha.parse(rs.getString("FechaFacturacion"));
                        dtpFacturacion.setDate(fecha);
                    } catch (ParseException ex) {
                        Logger.getLogger(pnlSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //switch para saber que radio button seleccionar
                    String rdbFirma = rs.getString("Firma");
                    switch (rdbFirma) {
                        case "SI":
                            btgFirma.setSelected(rdbSiFirma.getModel(), true);
                            break;
                        case "NO":
                            btgFirma.setSelected(rdbNoFirma.getModel(), true);
                            break;
                        default:
                            btgFirma.setSelected(rdbOtro.getModel(), true);
                            txtOtro.setText(rdbFirma);
                    }
                    //switch para saber que radio button seleccionar
                    String rdbPagoSeguro = rs.getString("PagoSeguro");
                    switch (rdbPagoSeguro) {
                        case "SI":
                            btgPago.setSelected(rdbSiSeguro.getModel(), true);
                            break;
                        case "NO":
                            btgPago.setSelected(rdbNoSeguro.getModel(), true);
                            break;
                        default:
                            break;
                    }
                }
                txtLinea.enable(false);
                btnModificar.setVisible(true);
                btnEliminar.setVisible(true);
                btnCancelar.setVisible(true);
                btnGuardar.setVisible(false);
                LimpiarErrores();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }//GEN-LAST:event_tblLineasMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //se utiliza la funcion Eliminar de la clase AccionesCrud enviando el ID
        AccionesCrud classcrud = new AccionesCrud();
        if (classcrud.Eliminar(txtLinea, "exec EliminarLineaTelefonica ?")) {
            DatosTablas Datos = new DatosTablas();
            Datos.CargarTabla(tblLineas, "select * from [VistaLineasTelefonicas]");
            Limpiar();
            lblTotalPlanNuevo.setText("Suma Total de los Plan Nuevos: " + SumarValores("Plan Nuevo") + "$");
            lblTotalPresupuesto.setText("Suma Total de los Presupuestos: " + SumarValores("Presupuesto") + "$");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private Object[] ArregloDatos() {
        //se crea un arreglo de objetos para enviar a la clase de AccionesCrud y la funcion de Guardar_Modificar
        Object[] datos = new Object[15];
        datos[0] = txtLinea.getText();
        datos[1] = txtNumExpediente.getText();
        datos[2] = cmbDisponibilidad.getSelectedItem().toString();
        datos[3] = (Integer) txtYear.getValue();
        datos[4] = (Integer) txtCuotas.getValue();
        datos[5] = txtImei.getText();
        try {
            Date date = dtpAsignacion.getDate();
            long d = date.getTime();
            java.sql.Date fecha = new java.sql.Date(d);
            datos[6] = fecha.toString();
        } catch (Exception e) {
            datos[6] = "";
        }
        try {
            Date date = dtpCambio.getDate();
            long d = date.getTime();
            java.sql.Date fecha = new java.sql.Date(d);
            datos[7] = fecha.toString();
        } catch (Exception e) {
            datos[7] = "";
        }
        try {
            Date date = dtpFacturacion.getDate();
            long d = date.getTime();
            java.sql.Date fecha = new java.sql.Date(d);
            datos[8] = fecha.toString();
        } catch (Exception e) {
            datos[8] = "";
        }
        if (rdbSiSeguro.isSelected() == true) {
            datos[9] = 1;
        } else if (rdbNoSeguro.isSelected() == true) {
            datos[9] = 2;
        }
        if (rdbSiFirma.isSelected() == true) {
            datos[10] = "SI";
        } else if (rdbNoFirma.isSelected() == true) {
            datos[10] = "NO";
        } else if (rdbOtro.isSelected() == true) {
            datos[10] = txtOtro.getText();
        }
        datos[11] = Double.parseDouble(txtAnterior.getText());
        datos[12] = Double.parseDouble(txtNuevo.getText());
        datos[13] = Double.parseDouble(txtPresupuesto.getText());
        datos[14] = Double.parseDouble(txtMensual.getText());
        return datos;
    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        LimpiarErrores();
        if (ValidarCampos()) {
            AccionesCrud classcrud = new AccionesCrud();
            if (classcrud.Guardar_Modificar(ArregloDatos(), "exec [AgregarLineaTelefonica] ?, ? ,?  ,? ,? ,? ,? ,? ,? ,?,?,?,?,?,?")) {
                DatosTablas Datos = new DatosTablas();
                Datos.CargarTabla(tblLineas, "select * from [VistaLineasTelefonicas]");
                lblTotalPlanNuevo.setText("Suma Total de los Plan Nuevos: " + SumarValores("Plan Nuevo") + "$");
                lblTotalPresupuesto.setText("Suma Total de los Presupuestos: " + SumarValores("Presupuesto") + "$");
                Limpiar();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        LimpiarErrores();
        if (ValidarCampos()) {
            AccionesCrud classcrud = new AccionesCrud();
            if (classcrud.Guardar_Modificar(ArregloDatos(), "exec [UpdateLineaTelefonica] ?, ? ,?  ,? ,? ,? ,? ,? ,? ,?,?,?,?,?,?")) {
                DatosTablas Datos = new DatosTablas();
                Datos.CargarTabla(tblLineas, "select * from [VistaLineasTelefonicas]");
                lblTotalPlanNuevo.setText("Suma Total de los Plan Nuevos: " + SumarValores("Plan Nuevo") + "$");
                lblTotalPresupuesto.setText("Suma Total de los Presupuestos: " + SumarValores("Presupuesto") + "$");
                Limpiar();
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    //Funicon para asignar el tipo de busqueda que se va hacer por medio de un switc y los valores de la vista de la BD
    private void cmbBuscarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBuscarItemStateChanged
        //Cada vez que se cambia el estado del combobox se cambia el filtro de busqueda global para la funcion de busqueda
        String elementoSeleccionado = (String) cmbBuscar.getSelectedItem();
        switch (elementoSeleccionado) {
            case "IMEI":
                Busqueda = "Imei";
                break;
            case "Línea de Teléfono":
                Busqueda = "Linea";
                break;
            case "N. expediente":
                Busqueda = "Expediente";
                break;
                case "Centro de Costo":
                Busqueda = "CentroCosto";
                break;
                case "Disponibilidad":
                Busqueda = "Disponible";
                break;
            default:
                break;
        }
        DatosTablas Datos = new DatosTablas();
        Datos.CargarTabla(tblLineas, "select * from [VistaLineasTelefonicas]");
        txtBuscar.setText("");
    }//GEN-LAST:event_cmbBuscarItemStateChanged

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        //cada vez que se precione una tecla se va a buscar junto al filtro de busqueda en la vista correspondiente
        DatosTablas BusquedaTabla = new DatosTablas();
        //se limpia la tabla
        DefaultTableModel modelo = (DefaultTableModel) tblLineas.getModel();
        modelo.setRowCount(0);
        //se muestra los resultados de la busqueda
        BusquedaTabla.CargarTabla(tblLineas, "select * from VistaLineasTelefonicas where " + Busqueda + " LIKE '%" + txtBuscar.getText().trim() + "%'");
        lblTotalPlanNuevo.setText("Suma Total de los Plan Nuevos: " + SumarValores("Plan Nuevo") + "$");
        lblTotalPresupuesto.setText("Suma Total de los Presupuestos: " + SumarValores("Presupuesto") + "$");
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnDisponibilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisponibilidadActionPerformed
        //Abrir Formulario de Disponibilidad
        Disponibilidad CrudDisponible = new Disponibilidad();
        CrudDisponible.setVisible(true);
        CrudDisponible.pack();
        CrudDisponible.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnDisponibilidadActionPerformed

    private void cmbDisponibilidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDisponibilidadItemStateChanged
        //al seleccionar un item se quita el estado de error
        val.CMBcorrecto(cmbDisponibilidad, lblErDisponible);
    }//GEN-LAST:event_cmbDisponibilidadItemStateChanged

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtNumExpedienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumExpedienteKeyTyped
        val.EntradaNumeros(txtNumExpediente, evt, 4);
    }//GEN-LAST:event_txtNumExpedienteKeyTyped

    private void txtNumExpedienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumExpedienteKeyReleased
        //al escribir se quita el estado de error
        val.TXTcorrecto(txtNumExpediente, lblErExpediente);
    }//GEN-LAST:event_txtNumExpedienteKeyReleased

    private void txtLineaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLineaKeyTyped
        // validado para un campo de tipo linea telefonica
        val.EntradaLinea(txtLinea, evt);
    }//GEN-LAST:event_txtLineaKeyTyped

    private void txtLineaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLineaKeyReleased
        //al escribir se quita el estado de error
        val.TXTcorrecto(txtLinea, lblErLinea);
    }//GEN-LAST:event_txtLineaKeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        CargarListas();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Limpiar();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void txtImeiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImeiKeyReleased
        //al escribir se quita el estado de error
        val.TXTcorrecto(txtImei, lblErImei);
    }//GEN-LAST:event_txtImeiKeyReleased

    private void txtImeiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtImeiKeyTyped
        // validado para un campo de tipo IMEI
        val.EntradaNumeros(txtImei, evt, 15);
    }//GEN-LAST:event_txtImeiKeyTyped

    private void txtAnteriorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnteriorKeyReleased
        //al escribir se quita el estado de error
        val.TXTcorrecto(txtAnterior, lblErAnterior);
    }//GEN-LAST:event_txtAnteriorKeyReleased

    private void txtAnteriorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnteriorKeyTyped
        // validado para un campo de tipo monetario
        val.EntradaDinero(txtAnterior, evt);
    }//GEN-LAST:event_txtAnteriorKeyTyped

    private void txtNuevoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoKeyReleased
        //al escribir se quita el estado de error
        val.TXTcorrecto(txtNuevo, lblErNuevo);
    }//GEN-LAST:event_txtNuevoKeyReleased

    private void txtNuevoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNuevoKeyTyped
        // validado para un campo de tipo monetario
        val.EntradaDinero(txtNuevo, evt);
    }//GEN-LAST:event_txtNuevoKeyTyped

    private void txtPresupuestoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPresupuestoKeyReleased
        //al escribir se quita el estado de error
        val.TXTcorrecto(txtPresupuesto, lblErPresupuesto);
    }//GEN-LAST:event_txtPresupuestoKeyReleased

    private void txtPresupuestoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPresupuestoKeyTyped
        // validado para un campo de tipo monetario
        val.EntradaDinero(txtPresupuesto, evt);
    }//GEN-LAST:event_txtPresupuestoKeyTyped

    private void txtMensualKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMensualKeyReleased
        //al escribir se quita el estado de error
        val.TXTcorrecto(txtMensual, lblErMensual);
    }//GEN-LAST:event_txtMensualKeyReleased

    private void txtMensualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMensualKeyTyped
        // validado para un campo de tipo monetario
        val.EntradaDinero(txtMensual, evt);
    }//GEN-LAST:event_txtMensualKeyTyped

    private void txtOtroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtroKeyTyped
        // validado para un campo de tipo texto normal con el parametro de la longitud deseada
        val.EntradaTextoNormal(txtOtro, evt, 50);
    }//GEN-LAST:event_txtOtroKeyTyped

    private void txtOtroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOtroKeyReleased
        //al escribir se quita el estado de error
        val.TXTcorrecto(txtOtro, lblErFirma);
    }//GEN-LAST:event_txtOtroKeyReleased

    private void rdbNoFirmaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdbNoFirmaItemStateChanged
        //al seleccionar este radioButton se pone el estado en correcto y se pone invisible el txt de la opcion otro
        txtOtro.setVisible(false);
        val.GENcorrecto(lblErFirma);
    }//GEN-LAST:event_rdbNoFirmaItemStateChanged

    private void rdbSiFirmaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdbSiFirmaItemStateChanged
        //al seleccionar este radioButton se pone el estado en correcto y se pone invisible el txt de la opcion otro
        txtOtro.setVisible(false);
        val.GENcorrecto(lblErFirma);
    }//GEN-LAST:event_rdbSiFirmaItemStateChanged

    private void rdbOtroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdbOtroItemStateChanged
        //al seleccionar este radioButton se pone el estado en correcto y se pone visible el text junto al focus para escribir
        txtOtro.setVisible(true);
        txtOtro.requestFocus();
        val.GENcorrecto(lblErFirma);
    }//GEN-LAST:event_rdbOtroItemStateChanged

    private void txtCuotasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtCuotasStateChanged
        //al cambiar el numero de cuotas se pone el estado en correcto
        val.GENcorrecto(lblErCuotas);
        int value = (int) txtCuotas.getValue();
        if (value < 0) {
            txtCuotas.setValue(0); // Establecer el valor mínimo si es menor a 0
        } else if (value > 100) {
            txtCuotas.setValue(100); // Establecer el valor máximo si es mayor a 100
        }
    }//GEN-LAST:event_txtCuotasStateChanged

    private void rdbSiSeguroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdbSiSeguroItemStateChanged
        //al seleccionar un radio button de seguros se quita el mensaje de error
        val.GENcorrecto(lblErPago);
    }//GEN-LAST:event_rdbSiSeguroItemStateChanged

    private void rdbNoSeguroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdbNoSeguroItemStateChanged
        //al seleccionar un radio button de seguros se quita el mensaje de error
        val.GENcorrecto(lblErPago);
    }//GEN-LAST:event_rdbNoSeguroItemStateChanged

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        //switch para decidir que validacion establecer cada ves que se preciona una tecla en buscar
        switch (Busqueda) {
            case "Linea":
                val.EntradaLinea(txtBuscar, evt);
                break;
            case "Imei":
                val.EntradaNumeros(txtBuscar, evt, 15);
                break;
            case "Expediente":
                val.EntradaNumeros(txtBuscar, evt, 4);
                break;
                case "Disponible":
                val.EntradaTextoNormal(txtBuscar, evt, 20);
                break;
                case "CentroCosto":
                val.EntradaTextoNormal(txtBuscar, evt, 80);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void dtpAsignacionPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtpAsignacionPropertyChange
        //se quita el error al escribir en el campo de compra
        val.GENcorrecto(lblErAsignacion);
    }//GEN-LAST:event_dtpAsignacionPropertyChange

    private void dtpCambioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtpCambioPropertyChange
        //se quita el error al escribir en el campo de compra
        val.GENcorrecto(lblErCambio);
    }//GEN-LAST:event_dtpCambioPropertyChange

    private void dtpFacturacionPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtpFacturacionPropertyChange
        //se quita el error al escribir en el campo de compra
        val.GENcorrecto(lblErFacturacion);
    }//GEN-LAST:event_dtpFacturacionPropertyChange

    private void txtYearStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_txtYearStateChanged
        //limite entre 2010 y el año actual
        int value = (int) txtYear.getValue();
        if (value < 2010) {
            txtYear.setValue(2010); // Establecer el valor mínimo si es menor a 0
        } else if (value > maxyear) {
            txtYear.setValue(maxyear); // Establecer el valor máximo si es mayor a 100
        }
    }//GEN-LAST:event_txtYearStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonMetro btnCancelar;
    private javax.swing.JButton btnDisponibilidad;
    private rsbuttom.RSButtonMetro btnEliminar;
    private rsbuttom.RSButtonMetro btnGuardar;
    private rsbuttom.RSButtonMetro btnModificar;
    private javax.swing.JComboBox<String> cmbBuscar;
    private javax.swing.JComboBox<String> cmbDisponibilidad;
    private com.toedter.calendar.JDateChooser dtpAsignacion;
    private com.toedter.calendar.JDateChooser dtpCambio;
    private com.toedter.calendar.JDateChooser dtpFacturacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblErAnterior;
    private javax.swing.JLabel lblErAsignacion;
    private javax.swing.JLabel lblErCambio;
    private javax.swing.JLabel lblErCuotas;
    private javax.swing.JLabel lblErDisponible;
    private javax.swing.JLabel lblErExpediente;
    private javax.swing.JLabel lblErFacturacion;
    private javax.swing.JLabel lblErFirma;
    private javax.swing.JLabel lblErImei;
    private javax.swing.JLabel lblErLinea;
    private javax.swing.JLabel lblErMensual;
    private javax.swing.JLabel lblErNuevo;
    private javax.swing.JLabel lblErPago;
    private javax.swing.JLabel lblErPresupuesto;
    private javax.swing.JLabel lblObligatorio;
    private javax.swing.JLabel lblObligatorio1;
    private javax.swing.JLabel lblObligatorio10;
    private javax.swing.JLabel lblObligatorio11;
    private javax.swing.JLabel lblObligatorio12;
    private javax.swing.JLabel lblObligatorio13;
    private javax.swing.JLabel lblObligatorio2;
    private javax.swing.JLabel lblObligatorio3;
    private javax.swing.JLabel lblObligatorio4;
    private javax.swing.JLabel lblObligatorio5;
    private javax.swing.JLabel lblObligatorio6;
    private javax.swing.JLabel lblObligatorio7;
    private javax.swing.JLabel lblObligatorio8;
    private javax.swing.JLabel lblObligatorio9;
    private javax.swing.JLabel lblTotalPlanNuevo;
    private javax.swing.JLabel lblTotalPresupuesto;
    private javax.swing.JRadioButton rdbNoFirma;
    private javax.swing.JRadioButton rdbNoSeguro;
    private javax.swing.JRadioButton rdbOtro;
    private javax.swing.JRadioButton rdbSiFirma;
    private javax.swing.JRadioButton rdbSiSeguro;
    private javax.swing.JTable tblLineas;
    private javax.swing.JTextField txtAnterior;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JSpinner txtCuotas;
    private javax.swing.JTextField txtImei;
    private javax.swing.JTextField txtLinea;
    private javax.swing.JTextField txtMensual;
    private javax.swing.JTextField txtNuevo;
    private javax.swing.JTextField txtNumExpediente;
    private javax.swing.JTextField txtOtro;
    private javax.swing.JTextField txtPresupuesto;
    private javax.swing.JSpinner txtYear;
    // End of variables declaration//GEN-END:variables
}
