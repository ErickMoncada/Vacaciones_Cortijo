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
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import paneles.ExtraUsuarios.Categoria;
import paneles.ExtraUsuarios.CentroCosto;
import paneles.ExtraUsuarios.Planilla;
import paneles.ExtraUsuarios.PuestoTrabajo;
import paneles.ExtraUsuarios.Ubicacion;

/**
 * @ErickMoncada controlador panel lineas telefonicas
 */
public class pnlSolicitar extends javax.swing.JPanel {

    public pnlSolicitar() {
        initComponents();
        CargarDatosPrincipal();
        Limpiar();
        asignarEventos();
        panelSolicitar.setVisible(false);
        //---------------------------------se establece que no se pueda pegar texto en los campos
        val.NegarPegado(txtBuscar);
        //------------------------------------------------------------------------------
    }

    int idNivel = 6;
    int cc = 0;
    int areaempleado = 0;
    int areacc = 0;
    int codigoempleado = 1;
    int year1 = 0, year2 = 0, year3 = 0, year4 = 0;
    int yearseleccionado;

    //se inicializa para la busqueda por medio de Categoria
    String Busqueda = "CodEmpleado";
    //se Inicializa la variabl del nivel para tneerlo en el Jframe
    String NivelAcceso;
    //se inicializa la clase de validaciones
    validaciones val = new validaciones();

    private void Limpiar() {
        //panelSolicitar.setVisible(false);
        dtpInicio.setVisible(false);
        lblDateInicio.setVisible(false);
        dtpFinal.setVisible(false);
        lblDateFinal.setVisible(false);
        txtDiasSolicitados.setVisible(false);
        txtDiasSolicitados.setText("");
        lblDiasSolicitados.setVisible(false);
        btnSolicitar.setVisible(false);
        lblDiasDisponibles.setVisible(false);

        LimpiarErrores();
    }

    private void asignarEventos() {
        //funcion para asignar los eventos a los mensajes de obligatorio con la clase de validaciones

    }

    private void CargarTabla() {
        //rellenar datos de la tabla
        DatosTablas Datos = new DatosTablas();
        if (cc == 0 && areaempleado != 0) {
            Datos.CargarTabla(tblUsuarios, "select * from VistaEmpleados where IdNivel<" + idNivel + "and AreaCC=" + areaempleado + "or CodEmpleado=" + codigoempleado);
        } else if (cc == 0 && areaempleado == 0) {
            Datos.CargarTabla(tblUsuarios, "select * from VistaEmpleados where IdNivel<" + idNivel + "or CodEmpleado=" + codigoempleado);
        } else {
            Datos.CargarTabla(tblUsuarios, "select * from VistaEmpleados where IdNivel<" + idNivel + "and NumCentroCosto=" + cc + "or CodEmpleado=" + codigoempleado);
        }
    }

    private void CargarDatosPrincipal() {
        //rellenar datos de la tabla
        CargarTabla();
        //obtener la hora del servidor para poner de limite
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT GETDATE() AS HoraActual");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Timestamp serverTime = rs.getTimestamp("HoraActual");
                Date minDate = new Date(serverTime.getTime());
                dtpInicio.setMinSelectableDate(minDate);
                dtpFinal.setMinSelectableDate(minDate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(pnlSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void CargarListas() {
        // Lista de JComboBox para actualizar datos

        // Recorrer cada JComboBox y eliminar los elementos
        //cargar los datos de los combobox
        DatosTablas Datos = new DatosTablas();

    }

    private void LimpiarErrores() {
        //usando la clase de validaciones se establecen los valores en correcto

    }

    private boolean ValidarCampos() {
        int valor = 1;
        Date date1 = dtpInicio.getDate();
        Date date2 = dtpFinal.getDate();

        if (date1.compareTo(date2) > 0) {
            // date1 es posterior a date2
            JOptionPane.showMessageDialog(null, "La fecha de inicio tiene que ser antes de la fecha final", "Error en las fechas", JOptionPane.ERROR_MESSAGE);
            valor = 0;
        }else{
        try {
            int diasSolicitados = Integer.parseInt(txtDiasSolicitados.getText());
            // Comparar las fechas y verificar si el rango de días es mayor o igual a los días solicitados
            if (date1.compareTo(date2) <= 0 && daysBetween(date1, date2) >= diasSolicitados-1) {
                
            } else {
                JOptionPane.showMessageDialog(null, "El rango de días de las fechas debe ser mayor o igual a los días solicitados", "Error en las fechas", JOptionPane.ERROR_MESSAGE);
                valor = 0;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor numérico válido para los días solicitados", "Error en los datos", JOptionPane.ERROR_MESSAGE);
            valor = 0;
        }}

        return valor == 1; //Expreciones regulares de los campos
    }
    // Método para calcular la diferencia en días entre dos fechas

    public static long daysBetween(Date fechaInicio, Date fechaFin) {
        long diff = fechaFin.getTime() - fechaInicio.getTime();
        return diff / (24 * 60 * 60 * 1000); // Convertir milisegundos a días
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        cmbBuscar = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        panelSolicitar = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblDateInicio = new javax.swing.JLabel();
        btnSolicitar = new rsbuttom.RSButtonMetro();
        jLabel12 = new javax.swing.JLabel();
        dtpInicio = new com.toedter.calendar.JCalendar();
        dtpFinal = new com.toedter.calendar.JCalendar();
        lblDateFinal = new javax.swing.JLabel();
        txtDiasSolicitados = new javax.swing.JTextField();
        lblDiasSolicitados = new javax.swing.JLabel();
        cmbYearDiasSolicitados = new javax.swing.JComboBox<>();
        lblCodigoEmpleado = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblDiasDisponibles = new javax.swing.JLabel();
        lblNombreEmpleado = new javax.swing.JLabel();

        jMenuItem2.setLabel("Limpiar Campos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        setBackground(new java.awt.Color(255, 255, 255));
        setComponentPopupMenu(jPopupMenu1);
        setPreferredSize(new java.awt.Dimension(1920, 781));

        jScrollPane1.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(800, 403));

        tblUsuarios.setBackground(new java.awt.Color(204, 255, 204));
        tblUsuarios.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Cod. Empleado", "Nombre", "Dias Disponibles primer año", "Dias Disponibles 2do año", "Dias Disponibles 3er año", "Dias Disponibles 4to año en adelante"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        cmbBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cod. Empleado", "Nombre" }));
        cmbBuscar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBuscarItemStateChanged(evt);
            }
        });

        jLabel2.setText("Buscar por:");

        panelSolicitar.setOpaque(false);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Código Empleado:");

        lblDateInicio.setForeground(new java.awt.Color(0, 0, 0));
        lblDateInicio.setText("Fecha de Inicio:");

        btnSolicitar.setBackground(new java.awt.Color(114, 191, 68));
        btnSolicitar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSolicitar.setText("Solicitar");
        btnSolicitar.setColorBorde(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSolicitar.setColorHover(new java.awt.Color(0, 191, 68));
        btnSolicitar.setColorNormal(new java.awt.Color(114, 191, 68));
        btnSolicitar.setColorPressed(new java.awt.Color(0, 49, 30));
        btnSolicitar.setColorTextHover(new java.awt.Color(51, 51, 51));
        btnSolicitar.setColorTextNormal(new java.awt.Color(51, 51, 51));
        btnSolicitar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSolicitar.setMaximumSize(new java.awt.Dimension(55, 20));
        btnSolicitar.setMinimumSize(new java.awt.Dimension(55, 20));
        btnSolicitar.setNextFocusableComponent(txtBuscar);
        btnSolicitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitarActionPerformed(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Año de los dias Solicitados:");

        lblDateFinal.setForeground(new java.awt.Color(0, 0, 0));
        lblDateFinal.setText("Fecha de Final:");

        txtDiasSolicitados.setPreferredSize(new java.awt.Dimension(65, 26));
        txtDiasSolicitados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDiasSolicitadosKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDiasSolicitadosKeyTyped(evt);
            }
        });

        lblDiasSolicitados.setForeground(new java.awt.Color(0, 0, 0));
        lblDiasSolicitados.setText("Dias Solicitados:");

        cmbYearDiasSolicitados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Primer Año", "Segundo Año", "Tercer Año", "Cuarto Año en adelante" }));
        cmbYearDiasSolicitados.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbYearDiasSolicitadosItemStateChanged(evt);
            }
        });

        lblCodigoEmpleado.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblCodigoEmpleado.setForeground(new java.awt.Color(0, 0, 0));
        lblCodigoEmpleado.setText("CodEmp");

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Nombre de Empleado:");

        lblDiasDisponibles.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblDiasDisponibles.setForeground(new java.awt.Color(0, 0, 0));
        lblDiasDisponibles.setText("Dias Disponibles:");

        lblNombreEmpleado.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        lblNombreEmpleado.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreEmpleado.setText("NomEmp");

        javax.swing.GroupLayout panelSolicitarLayout = new javax.swing.GroupLayout(panelSolicitar);
        panelSolicitar.setLayout(panelSolicitarLayout);
        panelSolicitarLayout.setHorizontalGroup(
            panelSolicitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSolicitarLayout.createSequentialGroup()
                .addGroup(panelSolicitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSolicitarLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(panelSolicitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSolicitarLayout.createSequentialGroup()
                                .addGroup(panelSolicitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblDateInicio)
                                    .addComponent(jLabel4)
                                    .addComponent(lblDateFinal))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelSolicitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelSolicitarLayout.createSequentialGroup()
                                        .addComponent(lblCodigoEmpleado)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lblNombreEmpleado))
                                    .addComponent(dtpInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dtpFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelSolicitarLayout.createSequentialGroup()
                                .addGroup(panelSolicitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblDiasSolicitados)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(panelSolicitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelSolicitarLayout.createSequentialGroup()
                                        .addComponent(cmbYearDiasSolicitados, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblDiasDisponibles))
                                    .addComponent(txtDiasSolicitados, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(panelSolicitarLayout.createSequentialGroup()
                        .addGap(221, 221, 221)
                        .addComponent(btnSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelSolicitarLayout.setVerticalGroup(
            panelSolicitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSolicitarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSolicitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelSolicitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCodigoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSolicitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbYearDiasSolicitados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiasDisponibles, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(panelSolicitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiasSolicitados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDiasSolicitados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSolicitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDateInicio)
                    .addComponent(dtpInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSolicitarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDateFinal)
                    .addComponent(dtpFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(btnSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panelSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cmbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private Object[] ArregloDatos() {
        //se crea un arreglo de objetos para enviar a la clase de AccionesCrud y la funcion de Guardar_Modificar
        Object[] datos = new Object[6];
        try {
            Date date = dtpInicio.getDate();
            long d = date.getTime();
            java.sql.Date fecha = new java.sql.Date(d);
            datos[0] = fecha.toString();
        } catch (Exception e) {
            datos[0] = "";
        }
        try {
            Date date = dtpFinal.getDate();
            long d = date.getTime();
            java.sql.Date fecha = new java.sql.Date(d);
            datos[1] = fecha.toString();
        } catch (Exception e) {
            datos[1] = "";
        }
        datos[2] = txtDiasSolicitados.getText();
        datos[3] = lblCodigoEmpleado.getText();
        datos[4] = yearseleccionado;
        datos[5] = codigoempleado;

        return datos;
    }
    private void btnSolicitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitarActionPerformed
        LimpiarErrores();
        if (ValidarCampos()) {
            AccionesCrud classcrud = new AccionesCrud();
            if (classcrud.Guardar_Modificar(ArregloDatos(), "exec [HacerSolicitud] ?, ? ,?  ,? ,? ,?")) {
                Limpiar();
                CargarTabla();
            }
        }
    }//GEN-LAST:event_btnSolicitarActionPerformed

    //Funicon para asignar el tipo de busqueda que se va hacer por medio de un switc y los valores de la vista de la BD
    private void cmbBuscarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBuscarItemStateChanged
        CargarTabla();
        //Cada vez que se cambia el estado del combobox se cambia el filtro de busqueda global para la funcion de busqueda
        String elementoSeleccionado = (String) cmbBuscar.getSelectedItem();
        switch (elementoSeleccionado) {
            case "Cod. Empleado":
                Busqueda = "CodEmpleado";
                break;
            case "Nombre":
                Busqueda = "NombreCompleto";
                break;
            default:
                break;
        }
        txtBuscar.setText("");
    }//GEN-LAST:event_cmbBuscarItemStateChanged

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        //cada vez que se precione una tecla se va a buscar junto al filtro de busqueda en la vista correspondiente
        //se limpia la tabla
        DefaultTableModel modelo = (DefaultTableModel) tblUsuarios.getModel();
        modelo.setRowCount(0);
        //se muestra los resultados de la busqueda
        //rellenar datos de la tabla
        DatosTablas Datos = new DatosTablas();
        if (cc == 0 && areaempleado != 0) {
            Datos.CargarTabla(tblUsuarios, "select * from VistaEmpleados where IdNivel<" + idNivel + " and AreaCC=" + areaempleado + " and " + Busqueda + " LIKE '%" + txtBuscar.getText() + "%' or CodEmpleado=" + codigoempleado + " and " + Busqueda + "  LIKE '%" + txtBuscar.getText() + "%'");
        } else if (cc == 0 && areaempleado == 0) {
            Datos.CargarTabla(tblUsuarios, "select * from VistaEmpleados where IdNivel<" + idNivel + "  and " + Busqueda + " LIKE '%" + txtBuscar.getText() + "%' or CodEmpleado=" + codigoempleado + " and " + Busqueda + "  LIKE '%" + txtBuscar.getText() + "%'");
        } else {
            Datos.CargarTabla(tblUsuarios, "select * from VistaEmpleados where IdNivel<" + idNivel + " and NumCentroCosto=" + cc + " and " + Busqueda + " LIKE '%" + txtBuscar.getText() + "%' or CodEmpleado=" + codigoempleado + " and " + Busqueda + "  LIKE '%" + txtBuscar.getText() + "%'");
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Limpiar();
        panelSolicitar.setVisible(false);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        switch (Busqueda) {
            case "CodigoEmpleado":
                val.EntradaNumeros(txtBuscar, evt, 5);
                break;
            case "Nombre":
                val.EntradaSoloLetas(txtBuscar, evt, 80);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void txtDiasSolicitadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasSolicitadosKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasSolicitadosKeyReleased

    private void txtDiasSolicitadosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDiasSolicitadosKeyTyped
        val.EntradaNumeros(txtDiasSolicitados, evt, 2);
    }//GEN-LAST:event_txtDiasSolicitadosKeyTyped

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        Limpiar();
        cmbYearDiasSolicitados.setSelectedItem(" ");
        /*
        int fila = tblUsuarios.getSelectedRow();
        int indiceColumna = tblUsuarios.getColumnModel().getColumnIndex("Cod. Empleado");
        String nivel = tblUsuarios.getValueAt(fila, indiceColumna).toString();*/

        //se trata de obtener los datos de la tabla para mostrarlos en las casillas respectivas con ayuda de sql
        try {
            AccionesCrud classcrud = new AccionesCrud();
            ResultSet rs = classcrud.Seleccion(tblUsuarios, "select * from [VistaEmpleados] where [CodEmpleado]=?", "Cod. Empleado");
            while (rs.next()) {
                lblNombreEmpleado.setText(rs.getString("NombreCompleto"));
                lblCodigoEmpleado.setText(rs.getString("CodEmpleado"));
                year1 = (rs.getInt("Year1_Dias"));
                year2 = (rs.getInt("Year2_Dias"));
                year3 = (rs.getInt("Year3_Dias"));
                year4 = (rs.getInt("Year4_Dias"));
            }
            panelSolicitar.setVisible(true);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void cmbYearDiasSolicitadosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbYearDiasSolicitadosItemStateChanged
        String elementoSeleccionado = (String) cmbYearDiasSolicitados.getSelectedItem();
        switch (elementoSeleccionado) {
            case " ":
                Limpiar();
                break;
            case "Primer Año":
                if (year1 > 0) {
                    Limpiar();
                    dtpInicio.setVisible(true);
                    dtpFinal.setVisible(true);
                    lblDateInicio.setVisible(true);
                    lblDateFinal.setVisible(true);
                    lblDiasDisponibles.setText("Dias Disponibles: " + year1);
                    lblDiasDisponibles.setVisible(true);
                    lblDiasSolicitados.setVisible(true);
                    txtDiasSolicitados.setVisible(true);
                    btnSolicitar.setVisible(true);
                    yearseleccionado = 1;
                    /*/ Sumar los dias a la fecha limite
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dtpInicio.getMinSelectableDate());
                calendar.add(Calendar.DAY_OF_WEEK, year1);
                Date diassumados = calendar.getTime();
                    dtpFinal.setMinSelectableDate(diassumados);*/
                } else {
                    Limpiar();
                    lblDiasDisponibles.setText("Dias Disponibles: 0");
                    lblDiasDisponibles.setVisible(true);
                }
                break;
            case "Segundo Año":
                if (year2 > 0) {
                    Limpiar();
                    dtpInicio.setVisible(true);
                    dtpFinal.setVisible(true);
                    lblDateInicio.setVisible(true);
                    lblDateFinal.setVisible(true);
                    lblDiasDisponibles.setText("Dias Disponibles: " + year2);
                    lblDiasDisponibles.setVisible(true);
                    lblDiasSolicitados.setVisible(true);
                    txtDiasSolicitados.setVisible(true);
                    btnSolicitar.setVisible(true);
                    yearseleccionado = 2;
                } else {
                    Limpiar();
                    lblDiasDisponibles.setText("Dias Disponibles: 0");
                    lblDiasDisponibles.setVisible(true);
                }
                break;
            case "Tercer Año":
                if (year3 > 0) {
                    Limpiar();
                    dtpInicio.setVisible(true);
                    dtpFinal.setVisible(true);
                    lblDateInicio.setVisible(true);
                    lblDateFinal.setVisible(true);
                    lblDiasDisponibles.setText("Dias Disponibles: " + year3);
                    lblDiasDisponibles.setVisible(true);
                    lblDiasSolicitados.setVisible(true);
                    txtDiasSolicitados.setVisible(true);
                    btnSolicitar.setVisible(true);
                    yearseleccionado = 3;
                } else {
                    Limpiar();
                    lblDiasDisponibles.setText("Dias Disponibles: 0");
                    lblDiasDisponibles.setVisible(true);
                }
                break;
            case "Cuarto Año en adelante":
                if (year4 > 0) {
                    Limpiar();
                    dtpInicio.setVisible(true);
                    dtpFinal.setVisible(true);
                    lblDateInicio.setVisible(true);
                    lblDateFinal.setVisible(true);
                    lblDiasDisponibles.setText("Dias Disponibles: " + year4);
                    lblDiasDisponibles.setVisible(true);
                    lblDiasSolicitados.setVisible(true);
                    txtDiasSolicitados.setVisible(true);
                    btnSolicitar.setVisible(true);
                    yearseleccionado = 4;
                } else {
                    Limpiar();
                    lblDiasDisponibles.setText("Dias Disponibles: 0");
                    lblDiasDisponibles.setVisible(true);
                }
                break;
            default:
                break;
        }
    }//GEN-LAST:event_cmbYearDiasSolicitadosItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonMetro btnSolicitar;
    private javax.swing.JComboBox<String> cmbBuscar;
    private javax.swing.JComboBox<String> cmbYearDiasSolicitados;
    private com.toedter.calendar.JCalendar dtpFinal;
    private com.toedter.calendar.JCalendar dtpInicio;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCodigoEmpleado;
    private javax.swing.JLabel lblDateFinal;
    private javax.swing.JLabel lblDateInicio;
    private javax.swing.JLabel lblDiasDisponibles;
    private javax.swing.JLabel lblDiasSolicitados;
    private javax.swing.JLabel lblNombreEmpleado;
    private javax.swing.JPanel panelSolicitar;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDiasSolicitados;
    // End of variables declaration//GEN-END:variables
}
