package paneles;

import Clases.AccionesCrud;
import Clases.DatosTablas;
import Clases.validaciones;
import app.Conexion;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @ErickMoncada controlador del panel Finiquitos
 */
public class pnlFiniquitos extends javax.swing.JPanel {

    public pnlFiniquitos(String NIVEL) {
        initComponents();
        CargarDatosPrincipal();
        Limpiar();
        txtIDFiniquitos.setVisible(false);
        btgEstatus.add(rdbProgreso);
        btgEstatus.add(rdbCerrado);
        btgEstatus.add(rdbActivo);
        asignarEventos();
        if ("Lector".equals(NIVEL)) {
            jPanel1.setVisible(false);
            //eliminar opciones del menu desplegable
            jMenuItem1.setVisible(false);
        }
        NivelAcceso = NIVEL;
        //---------------------------------se establece que no se pueda pegar texto en los campos
        val.NegarPegado(txtCobroDls);
        val.NegarPegado(txtCobroLps);
        val.NegarPegado(txtLinea);
        val.NegarPegado(txtObs1);
        val.NegarPegado(txtObs2);
        val.NegarPegado(txtBuscar);
        //------------------------------------------------------------------------------
    }
    //se inicializa para la busqueda por medio de Categoria
    String Busqueda = "Linea";
    //se Inicializa la variabl del nivel para tneerlo en el Jframe
    String NivelAcceso;
    //se inicializa la clase de validaciones
    validaciones val = new validaciones();
    //se crean los grupos de botones para los radioButton
    ButtonGroup btgEstatus = new ButtonGroup();

    private void Limpiar() {
        //funcion para reiniciar todos los valores de la pantalla
        btnCancelar.setVisible(false);
        btnModificar.setVisible(false);
        btnEliminar.setVisible(false);
        btnGuardar.setVisible(true);

        txtIDFiniquitos.setText("");
        txtCobroDls.setText("");
        txtCobroLps.setText("");
        txtLinea.setText("");
        txtObs1.setText("");
        txtObs2.setText("");
        dtpSolicitud.setDate(null);
        dtpCorte.setDate(null);
        dtpCobro.setDate(null);
        btgEstatus.clearSelection();
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
    }

    private void CargarDatosPrincipal() {
        BuscarEnTabla();
        //obtener la hora del servidor para poner de limite
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT GETDATE() AS HoraActual");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Timestamp serverTime = rs.getTimestamp("HoraActual");
                Date maxDate = new Date(serverTime.getTime());
                dtpSolicitud.setMaxSelectableDate(maxDate);
                dtpCobro.setMaxSelectableDate(maxDate);

                // Sumar 1 año a la fecha para subir el limite de tiempo un año mas
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(maxDate);
                calendar.add(Calendar.YEAR, 1);
                Date maxDatePlusOneYear = calendar.getTime();
                dtpCorte.setMaxSelectableDate(maxDatePlusOneYear);
            }
        } catch (SQLException ex) {
            Logger.getLogger(pnlSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Aplicar el renderizador de celdas a todas las columnas para pintarlos segun el estado
        for (int i = 0; i < tblFiniquitos.getColumnCount(); i++) {
            tblFiniquitos.getColumnModel().getColumn(i).setCellRenderer(rowRenderer);
        }
        // Agregar el ListSelectionListener para cambiar el color de fondo cuando se selecciona una celda
        tblFiniquitos.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblFiniquitos.getSelectedRow();
                int selectedColumn = tblFiniquitos.getSelectedColumn();
                if (selectedRow >= 0 && selectedColumn >= 0) {
                    tblFiniquitos.getColumnModel().getColumn(selectedColumn).setCellRenderer(rowRenderer);
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
                int indiceColumna = tblFiniquitos.getColumnModel().getColumnIndex("Estado");
                String estado = table.getValueAt(row, indiceColumna).toString();

                // Cambiar el color de fondo de la fila según el estado
                if (estado.equals("1")) {
                    //cellComponent.setBackground(new Color(204, 255, 204));
                    cellComponent.setBackground(Color.white);
                }
                if (estado.equals("2")) {
                    cellComponent.setBackground(Color.GREEN);
                }
                if (estado.equals("3")) {
                    cellComponent.setBackground(Color.YELLOW);
                }
            }
            return cellComponent;
        }
    };

    private void LimpiarErrores() {
        //usando la clase de validaciones se establecen los valores en correcto
        val.GENcorrecto(lblErSolicitud);
        val.GENcorrecto(lblErCorte);
        val.GENcorrecto(lblErCobro);
        val.TXTcorrecto(txtCobroDls, lblErValor);
        val.TXTcorrecto(txtLinea, lblErLinea);
        val.GENcorrecto(lblErEstado);
    }

    private boolean ValidarCampos() {

        int valor1 = 1;
        String error;

        //se verifica si la fecha esta bien
        if (val.ValidarFechas(dtpSolicitud, lblErSolicitud) == 0) {
            valor1 = 0;
        }
        if (val.ValidarFechas(dtpCorte, lblErCorte) == 0) {
            valor1 = 0;
        }
        if (val.ValidarFechas(dtpCobro, lblErCobro) == 0) {
            valor1 = 0;
        }

        if (txtCobroDls.getText().isEmpty() || Double.parseDouble(txtCobroDls.getText()) <= 0 || txtCobroLps.getText().isEmpty() || Double.parseDouble(txtCobroLps.getText()) <= 0) {
            valor1 = 0;
            error = "El valor del cobro no puede estar vacío o en 0 (ambos campos)";
            val.TXTincorrecto(txtCobroDls, lblErValor, error);
        }
        if (txtLinea.getText().isEmpty() || !txtLinea.getText().matches("\\d{4}-\\d{4}")) {
            valor1 = 0;
            error = "Escriba un número de teléfono valido";
            val.TXTincorrecto(txtLinea, lblErLinea, error);
        }
        if (btgEstatus.getSelection() == null) {
            valor1 = 0;
            error = "Debe seleccionar una opción de Estado";
            val.GENIncorrecto(lblErEstado, error);
        }

        return valor1 == 1; //Expreciones regulares de los campos
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        txtIDFiniquitos = new javax.swing.JTextField();
        btnModificar = new rsbuttom.RSButtonMetro();
        btnGuardar = new rsbuttom.RSButtonMetro();
        btnEliminar = new rsbuttom.RSButtonMetro();
        btnCancelar = new rsbuttom.RSButtonMetro();
        dtpCorte = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        dtpSolicitud = new com.toedter.calendar.JDateChooser();
        txtObs1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtObs2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dtpCobro = new com.toedter.calendar.JDateChooser();
        lblErSolicitud = new javax.swing.JLabel();
        lblErCorte = new javax.swing.JLabel();
        lblErCobro = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        rdbActivo = new javax.swing.JRadioButton();
        rdbCerrado = new javax.swing.JRadioButton();
        rdbProgreso = new javax.swing.JRadioButton();
        lblErEstado = new javax.swing.JLabel();
        lblObligatorio5 = new javax.swing.JLabel();
        lblObligatorio = new javax.swing.JLabel();
        lblObligatorio1 = new javax.swing.JLabel();
        lblObligatorio2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblObligatorio3 = new javax.swing.JLabel();
        lblObligatorio4 = new javax.swing.JLabel();
        lblErValor = new javax.swing.JLabel();
        lblErLinea = new javax.swing.JLabel();
        txtCobroLps = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtCobroDls = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtLinea = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFiniquitos = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        cmbBuscar = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        chkActivo = new javax.swing.JCheckBox();
        chkCerrado = new javax.swing.JCheckBox();
        chkProgreso = new javax.swing.JCheckBox();

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
        jPanel1.setNextFocusableComponent(dtpSolicitud);

        txtIDFiniquitos.setPreferredSize(new java.awt.Dimension(65, 26));

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
        btnGuardar.setNextFocusableComponent(txtBuscar);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
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

        dtpCorte.setMinSelectableDate(new java.util.Date(1262329267000L));
        dtpCorte.setNextFocusableComponent(dtpCobro);
        dtpCorte.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtpCortePropertyChange(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Fecha de Solicitud\n");

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("por parte de RRHH:");

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Fecha de Corte:");

        dtpSolicitud.setMinSelectableDate(new java.util.Date(1262329267000L));
        dtpSolicitud.setNextFocusableComponent(dtpCorte);
        dtpSolicitud.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtpSolicitudPropertyChange(evt);
            }
        });

        txtObs1.setNextFocusableComponent(txtObs2);
        txtObs1.setPreferredSize(new java.awt.Dimension(65, 26));
        txtObs1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtObs1KeyTyped(evt);
            }
        });

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Observación 1:");

        txtObs2.setNextFocusableComponent(btnGuardar);
        txtObs2.setPreferredSize(new java.awt.Dimension(65, 26));
        txtObs2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtObs2KeyTyped(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Observación 2:");

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Fecha de Cobro:");

        dtpCobro.setMinSelectableDate(new java.util.Date(1262329267000L));
        dtpCobro.setNextFocusableComponent(txtCobroDls);
        dtpCobro.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtpCobroPropertyChange(evt);
            }
        });

        lblErSolicitud.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErSolicitud.setForeground(new java.awt.Color(255, 0, 0));
        lblErSolicitud.setText("Error");

        lblErCorte.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErCorte.setForeground(new java.awt.Color(255, 0, 0));
        lblErCorte.setText("Error");

        lblErCobro.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErCobro.setForeground(new java.awt.Color(255, 0, 0));
        lblErCobro.setText("Error");

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Estado:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        rdbActivo.setText("ACTIVO");
        rdbActivo.setName(""); // NOI18N
        rdbActivo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdbActivoItemStateChanged(evt);
            }
        });
        rdbActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbActivoActionPerformed(evt);
            }
        });
        jPanel2.add(rdbActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        rdbCerrado.setText("CERRADO");
        rdbCerrado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdbCerradoItemStateChanged(evt);
            }
        });
        jPanel2.add(rdbCerrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, -1, -1));

        rdbProgreso.setText("EN PROGRESO");
        rdbProgreso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdbProgresoItemStateChanged(evt);
            }
        });
        jPanel2.add(rdbProgreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, -1, -1));

        lblErEstado.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErEstado.setForeground(new java.awt.Color(255, 0, 0));
        lblErEstado.setText("Error");
        jPanel2.add(lblErEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));

        lblObligatorio5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio5.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio5.setText("*");
        lblObligatorio5.setToolTipText("");
        jPanel2.add(lblObligatorio5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, 12));

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

        jPanel3.setOpaque(false);

        lblObligatorio3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio3.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio3.setText("*");
        lblObligatorio3.setToolTipText("");

        lblObligatorio4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblObligatorio4.setForeground(new java.awt.Color(51, 51, 51));
        lblObligatorio4.setText("*");
        lblObligatorio4.setToolTipText("");

        lblErValor.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErValor.setForeground(new java.awt.Color(255, 0, 0));
        lblErValor.setText("Error");

        lblErLinea.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErLinea.setForeground(new java.awt.Color(255, 0, 0));
        lblErLinea.setText("Error");

        txtCobroLps.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtCobroLps.setNextFocusableComponent(txtLinea);
        txtCobroLps.setPreferredSize(new java.awt.Dimension(65, 26));
        txtCobroLps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCobroLpsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCobroLpsKeyTyped(evt);
            }
        });

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Lps. ");

        txtCobroDls.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        txtCobroDls.setNextFocusableComponent(txtLinea);
        txtCobroDls.setPreferredSize(new java.awt.Dimension(65, 26));
        txtCobroDls.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCobroDlsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCobroDlsKeyTyped(evt);
            }
        });

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Valor de Cobro:  $ ");

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Línea Telefónica:    ");

        txtLinea.setNextFocusableComponent(txtObs1);
        txtLinea.setPreferredSize(new java.awt.Dimension(65, 26));
        txtLinea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLineaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtLineaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblErLinea)
                    .addComponent(lblErValor)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtLinea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(txtCobroDls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCobroLps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblObligatorio4)
                            .addComponent(lblObligatorio3))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCobroDls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(lblObligatorio3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCobroLps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErValor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtLinea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObligatorio4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErLinea))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblErCobro)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dtpCorte, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(dtpSolicitud, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                    .addComponent(lblErSolicitud)
                                    .addComponent(lblErCorte)
                                    .addComponent(dtpCobro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblObligatorio1)
                                    .addComponent(lblObligatorio)
                                    .addComponent(lblObligatorio2))
                                .addGap(50, 50, 50)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtIDFiniquitos, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtObs2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtObs1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtObs1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtObs2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(dtpSolicitud, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblObligatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblErSolicitud)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(dtpCorte, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                                    .addComponent(lblObligatorio1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblErCorte))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIDFiniquitos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblObligatorio2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dtpCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(lblErCobro)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane1.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(800, 403));

        tblFiniquitos.setBackground(new java.awt.Color(204, 255, 204));
        tblFiniquitos.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tblFiniquitos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Solicitud RRHH", "Línea Telefónica", "Usuario", "Fecha de Corte", "Valor del Cobro $", "Valor del Cobro Lps.", "Fecha Cobro", "Observacion 1", "Observacion 2", "Estado"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFiniquitos.setGridColor(new java.awt.Color(0, 0, 0));
        tblFiniquitos.setSelectionBackground(new java.awt.Color(51, 153, 0));
        tblFiniquitos.getTableHeader().setReorderingAllowed(false);
        tblFiniquitos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFiniquitosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFiniquitos);
        if (tblFiniquitos.getColumnModel().getColumnCount() > 0) {
            tblFiniquitos.getColumnModel().getColumn(0).setMinWidth(0);
            tblFiniquitos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblFiniquitos.getColumnModel().getColumn(0).setMaxWidth(0);
            tblFiniquitos.getColumnModel().getColumn(1).setMinWidth(80);
            tblFiniquitos.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblFiniquitos.getColumnModel().getColumn(1).setMaxWidth(130);
            tblFiniquitos.getColumnModel().getColumn(2).setMinWidth(50);
            tblFiniquitos.getColumnModel().getColumn(2).setPreferredWidth(80);
            tblFiniquitos.getColumnModel().getColumn(2).setMaxWidth(100);
            tblFiniquitos.getColumnModel().getColumn(3).setMinWidth(80);
            tblFiniquitos.getColumnModel().getColumn(3).setPreferredWidth(300);
            tblFiniquitos.getColumnModel().getColumn(3).setMaxWidth(300);
            tblFiniquitos.getColumnModel().getColumn(4).setMinWidth(80);
            tblFiniquitos.getColumnModel().getColumn(4).setPreferredWidth(130);
            tblFiniquitos.getColumnModel().getColumn(4).setMaxWidth(140);
            tblFiniquitos.getColumnModel().getColumn(5).setMinWidth(50);
            tblFiniquitos.getColumnModel().getColumn(5).setPreferredWidth(130);
            tblFiniquitos.getColumnModel().getColumn(5).setMaxWidth(150);
            tblFiniquitos.getColumnModel().getColumn(6).setMinWidth(50);
            tblFiniquitos.getColumnModel().getColumn(6).setPreferredWidth(130);
            tblFiniquitos.getColumnModel().getColumn(6).setMaxWidth(150);
            tblFiniquitos.getColumnModel().getColumn(7).setMinWidth(80);
            tblFiniquitos.getColumnModel().getColumn(7).setPreferredWidth(130);
            tblFiniquitos.getColumnModel().getColumn(7).setMaxWidth(140);
            tblFiniquitos.getColumnModel().getColumn(10).setMinWidth(0);
            tblFiniquitos.getColumnModel().getColumn(10).setPreferredWidth(0);
            tblFiniquitos.getColumnModel().getColumn(10).setMaxWidth(0);
        }

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        cmbBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Línea Telefónica", "Nombre Empleado" }));
        cmbBuscar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBuscarItemStateChanged(evt);
            }
        });

        jLabel2.setText("Buscar por:");

        chkActivo.setText("Activos");
        chkActivo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkActivoItemStateChanged(evt);
            }
        });

        chkCerrado.setText("Cerrados");
        chkCerrado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkCerradoItemStateChanged(evt);
            }
        });

        chkProgreso.setText("En Progresos");
        chkProgreso.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkProgresoItemStateChanged(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkActivo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkCerrado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkProgreso)
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
                    .addComponent(chkActivo)
                    .addComponent(chkCerrado)
                    .addComponent(chkProgreso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private Object[] ArregloDatos() {
        Object[] datos = new Object[10];
        //se crea un arreglo de objetos para enviar a la clase de AccionesCrud y la funcion de Guardar_Modificar
        datos[0] = txtIDFiniquitos.getText();
        datos[1] = txtLinea.getText();
        try {
            Date date = dtpSolicitud.getDate();
            long d = date.getTime();
            java.sql.Date fecha = new java.sql.Date(d);
            datos[2] = fecha.toString();
        } catch (Exception e) {
            datos[2] = "";
        }
        try {
            Date date = dtpCorte.getDate();
            long d = date.getTime();
            java.sql.Date fecha = new java.sql.Date(d);
            datos[3] = fecha.toString();
        } catch (Exception e) {
            datos[3] = "";
        }
        datos[4] = Double.parseDouble(txtCobroDls.getText());
        datos[5] = Double.parseDouble(txtCobroLps.getText());
        datos[6] = txtObs1.getText().trim();
        datos[7] = txtObs2.getText().trim();
        try {
            Date date2 = dtpCobro.getDate();
            long d2 = date2.getTime();
            java.sql.Date fecha2 = new java.sql.Date(d2);
            datos[8] = fecha2.toString();
        } catch (Exception e) {
            datos[8] = "";
        }
        if (rdbActivo.isSelected() == true) {
            datos[9] = 1;
        } else if (rdbCerrado.isSelected() == true) {
            datos[9] = 2;
        } else if (rdbProgreso.isSelected() == true) {
            datos[9] = 3;
        }
        return datos;
    }
    private void tblFiniquitosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFiniquitosMouseClicked
        if (!"Lector".equals(NivelAcceso)) {
            LimpiarErrores();
            //se trata de obtener los datos de la tabla para mostrarlos en las casillas respectivas con ayuda de sql
            try {
                AccionesCrud classcrud = new AccionesCrud();
                ResultSet rs = classcrud.Seleccion(tblFiniquitos, "select * from [VistaFiniquitos] where [ID]=?", "ID");
                while (rs.next()) {
                    txtIDFiniquitos.setText(rs.getString("ID"));
                    //formato para mostrar la fecha en el JDateChooser
                    SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
                    Date fecha;
                    try {
                        fecha = formatofecha.parse(rs.getString("Solicitud"));
                        dtpSolicitud.setDate(fecha);
                    } catch (ParseException ex) {
                        Logger.getLogger(pnlSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        fecha = formatofecha.parse(rs.getString("Corte"));
                        dtpCorte.setDate(fecha);
                    } catch (ParseException ex) {
                        Logger.getLogger(pnlSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        fecha = formatofecha.parse(rs.getString("Fecha Cobro"));
                        dtpCobro.setDate(fecha);
                    } catch (ParseException ex) {
                        Logger.getLogger(pnlSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    txtCobroDls.setText(rs.getString("Valor Cobro DLS"));
                    txtCobroLps.setText(rs.getString("Valor Cobro LPS"));
                    txtLinea.setText(rs.getString("Linea"));
                    txtObs1.setText(rs.getString("Observacion 1"));
                    txtObs2.setText(rs.getString("Observacion 2"));
                    //switch para saber que radio button seleccionar
                    String rdbEstado = rs.getString("Estado");
                    switch (rdbEstado) {
                        case "1":
                            btgEstatus.setSelected(rdbActivo.getModel(), true);
                            break;
                        case "2":
                            btgEstatus.setSelected(rdbCerrado.getModel(), true);
                            break;
                        default:
                            btgEstatus.setSelected(rdbProgreso.getModel(), true);
                    }
                }
                txtIDFiniquitos.enable(false);
                btnModificar.setVisible(true);
                btnEliminar.setVisible(true);
                btnCancelar.setVisible(true);
                btnGuardar.setVisible(false);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }//GEN-LAST:event_tblFiniquitosMouseClicked

    //Funicon para asignar el tipo de busqueda que se va hacer por medio de un switc y los valores de la vista de la BD
    private void cmbBuscarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBuscarItemStateChanged
        //Cada vez que se cambia el estado del combobox se cambia el filtro de busqueda global para la funcion de busqueda
        String elementoSeleccionado = (String) cmbBuscar.getSelectedItem();
        switch (elementoSeleccionado) {
            case "Línea Telefónica":
                Busqueda = "Linea";
                break;
            case "Nombre Empleado":
                Busqueda = "Nombre";
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
        DefaultTableModel modelo = (DefaultTableModel) tblFiniquitos.getModel();
        modelo.setRowCount(0);
        //se busca si esta con un check box de busqueda
        String agregado = "";
        if (chkActivo.isSelected()) {
            agregado += " OR " + Busqueda + "  LIKE '%" + txtBuscar.getText().trim() + "%' AND Estado=1";
        }
        if (chkCerrado.isSelected()) {
            agregado += " OR " + Busqueda + "  LIKE '%" + txtBuscar.getText().trim() + "%' AND Estado=2";
        }
        if (chkProgreso.isSelected()) {
            agregado += " OR " + Busqueda + "  LIKE '%" + txtBuscar.getText().trim() + "%' AND Estado=3";
        }

        if ("".equals(agregado)) {
            //se muestra los resultados de la busqueda con filtros de checksbox
            BusquedaTabla.CargarTabla(tblFiniquitos, "select * from VistaFiniquitos where " + Busqueda + "  LIKE '%" + txtBuscar.getText().trim() + "%' ");
        } else {
            //se muestra los resultados de la busqueda sin filtros de checksbox
            String result = agregado.substring(4);
            BusquedaTabla.CargarTabla(tblFiniquitos, "select * from VistaFiniquitos where " + result);
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
            case "Linea":
                val.EntradaLinea(txtBuscar, evt);
                break;
            case "UsuarioDeLinea":
                val.EntradaSoloLetas(txtBuscar, evt, 80);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void chkActivoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkActivoItemStateChanged
        //recargar la tabla
        BuscarEnTabla();
    }//GEN-LAST:event_chkActivoItemStateChanged

    private void chkCerradoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkCerradoItemStateChanged
        //recargar la tabla
        BuscarEnTabla();
    }//GEN-LAST:event_chkCerradoItemStateChanged

    private void chkProgresoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkProgresoItemStateChanged
        //recargar la tabla
        BuscarEnTabla();
    }//GEN-LAST:event_chkProgresoItemStateChanged

    private void rdbProgresoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdbProgresoItemStateChanged
        //al seleccionar este radioButton se pone el estado en correcto
        val.GENcorrecto(lblErEstado);
    }//GEN-LAST:event_rdbProgresoItemStateChanged

    private void rdbCerradoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdbCerradoItemStateChanged
        //al seleccionar este radioButton se pone el estado en correcto
        val.GENcorrecto(lblErEstado);
    }//GEN-LAST:event_rdbCerradoItemStateChanged

    private void rdbActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbActivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbActivoActionPerformed

    private void rdbActivoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdbActivoItemStateChanged
        //al seleccionar este radioButton se pone el estado en correcto
        val.GENcorrecto(lblErEstado);
    }//GEN-LAST:event_rdbActivoItemStateChanged

    private void txtObs2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObs2KeyTyped
        // validado para un campo de tipo texto normal con el parametro de la longitud deseada
        val.EntradaTextoNormal(txtObs2, evt, 80);
    }//GEN-LAST:event_txtObs2KeyTyped

    private void txtObs1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObs1KeyTyped
        // validado para un campo de tipo texto normal con el parametro de la longitud deseada
        val.EntradaTextoNormal(txtObs1, evt, 80);
    }//GEN-LAST:event_txtObs1KeyTyped

    private void txtLineaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLineaKeyTyped
        // validado para un campo de tipo linea telefonica
        val.EntradaLinea(txtLinea, evt);
    }//GEN-LAST:event_txtLineaKeyTyped

    private void txtLineaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLineaKeyReleased
        //al escribir se quita el estado de error
        val.TXTcorrecto(txtLinea, lblErLinea);
    }//GEN-LAST:event_txtLineaKeyReleased

    private void txtCobroDlsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCobroDlsKeyTyped
        // validado para un campo de tipo monetario
        val.EntradaDinero(txtCobroDls, evt);
    }//GEN-LAST:event_txtCobroDlsKeyTyped

    private void txtCobroDlsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCobroDlsKeyReleased
        //al escribir se quita el estado de error
        val.TXTcorrecto(txtCobroDls, lblErValor);
    }//GEN-LAST:event_txtCobroDlsKeyReleased

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        //se utiliza la funcion Eliminar de la clase AccionesCrud enviando el ID
        AccionesCrud classcrud = new AccionesCrud();
        if (classcrud.Eliminar(txtIDFiniquitos, "exec EliminarFiniquito ?")) {
            DatosTablas Datos = new DatosTablas();
            Datos.CargarTabla(tblFiniquitos, "select * from VistaFiniquitos");
            Limpiar();
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        LimpiarErrores();
        if (ValidarCampos()) {
            AccionesCrud classcrud = new AccionesCrud();
            if (classcrud.Guardar_Modificar(ArregloDatos(), "exec [AgregarFiniquito] ?,?,?,?,?,?,?,?,?,?")) {
                BuscarEnTabla();
                Limpiar();
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        LimpiarErrores();
        if (ValidarCampos()) {
            AccionesCrud classcrud = new AccionesCrud();
            if (classcrud.Guardar_Modificar(ArregloDatos(), "exec [UpdateFiniquito] ?,?,?,?,?,?,?,?,?,?")) {
                BuscarEnTabla();
                Limpiar();
            }
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void txtCobroLpsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCobroLpsKeyReleased
        //al escribir se quita el estado de error
        val.TXTcorrecto(txtCobroLps, lblErValor);
    }//GEN-LAST:event_txtCobroLpsKeyReleased

    private void txtCobroLpsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCobroLpsKeyTyped
        // validado para un campo de tipo monetario
        val.EntradaDinero(txtCobroLps, evt);
    }//GEN-LAST:event_txtCobroLpsKeyTyped

    private void dtpSolicitudPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtpSolicitudPropertyChange
        //se quita el error al escribir en el campo de compra
        val.GENcorrecto(lblErSolicitud);
    }//GEN-LAST:event_dtpSolicitudPropertyChange

    private void dtpCortePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtpCortePropertyChange
        //se quita el error al escribir en el campo de compra
        val.GENcorrecto(lblErCorte);
    }//GEN-LAST:event_dtpCortePropertyChange

    private void dtpCobroPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtpCobroPropertyChange
        //se quita el error al escribir en el campo de compra
        val.GENcorrecto(lblErCobro);
    }//GEN-LAST:event_dtpCobroPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonMetro btnCancelar;
    private rsbuttom.RSButtonMetro btnEliminar;
    private rsbuttom.RSButtonMetro btnGuardar;
    private rsbuttom.RSButtonMetro btnModificar;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JCheckBox chkCerrado;
    private javax.swing.JCheckBox chkProgreso;
    private javax.swing.JComboBox<String> cmbBuscar;
    private com.toedter.calendar.JDateChooser dtpCobro;
    private com.toedter.calendar.JDateChooser dtpCorte;
    private com.toedter.calendar.JDateChooser dtpSolicitud;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblErCobro;
    private javax.swing.JLabel lblErCorte;
    private javax.swing.JLabel lblErEstado;
    private javax.swing.JLabel lblErLinea;
    private javax.swing.JLabel lblErSolicitud;
    private javax.swing.JLabel lblErValor;
    private javax.swing.JLabel lblObligatorio;
    private javax.swing.JLabel lblObligatorio1;
    private javax.swing.JLabel lblObligatorio2;
    private javax.swing.JLabel lblObligatorio3;
    private javax.swing.JLabel lblObligatorio4;
    private javax.swing.JLabel lblObligatorio5;
    private javax.swing.JRadioButton rdbActivo;
    private javax.swing.JRadioButton rdbCerrado;
    private javax.swing.JRadioButton rdbProgreso;
    private javax.swing.JTable tblFiniquitos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCobroDls;
    private javax.swing.JTextField txtCobroLps;
    private javax.swing.JTextField txtIDFiniquitos;
    private javax.swing.JTextField txtLinea;
    private javax.swing.JTextField txtObs1;
    private javax.swing.JTextField txtObs2;
    // End of variables declaration//GEN-END:variables
}
