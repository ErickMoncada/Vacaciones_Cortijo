package paneles;

import Clases.AccionesCrud;
import Clases.DatosTablas;
import Clases.validaciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * @ErickMoncada controlador del panel Equipos
 */
public class pnlSolicitudes extends javax.swing.JPanel {

    public pnlSolicitudes() {
        initComponents();
        CargarDatosPrincipal();
        Limpiar();
        //---------------------------------se establece que no se pueda pegar texto en los campos
        val.NegarPegado(txtBuscar);
        //------------------------------------------------------------------------------

    }
    int idNivel = 6;
    int cc = 0;
    int areaempleado = 0;
    int areacc = 0;
    int codigoempleado = 1;
    //inicializar variable del id de la solicitud
    int idSolicitud;
    //inicializar variable del codigo del empleado
    int CodEmpleado;
    //inicializar variable de dias solicitados del empleado
    int DiasSolicitados;
    //se inicializa para la busqueda por medio de Imei
    String Busqueda = "Nombre";

    //se inicializa la clase de validaciones
    validaciones val = new validaciones();

    private void Limpiar() {
        //funcion para reiniciar todos los valores de la pantalla
        panelOpciones.setVisible(false);
    }

    //funcion para reducir la repeticion del select
    private void CargarDatosTabla() {
        //rellenar datos de la tabla
        DatosTablas Datos = new DatosTablas();
        if (cc == 0 && areaempleado != 0) {
            Datos.CargarTabla(tblEquipos, "select * from VistaSolicitudes where IdNivel<" + idNivel + " and AreaCC=" + areaempleado + " and " + Busqueda + " LIKE '%" + txtBuscar.getText() + "%' and Estado=3");
        } else if (cc == 0 && areaempleado == 0) {
            Datos.CargarTabla(tblEquipos, "select * from VistaSolicitudes where IdNivel<" + idNivel + "  and " + Busqueda + " LIKE '%" + txtBuscar.getText() + "%' and Estado=3 or CodEmpleado=" + codigoempleado + " and " + Busqueda + "  LIKE '%" + txtBuscar.getText() + "%' and Estado=3");
        } else {
            Datos.CargarTabla(tblEquipos, "select * from VistaEmpleados where IdNivel<" + idNivel + " and NumCentroCosto=" + cc + " and " + Busqueda + " LIKE '%" + txtBuscar.getText() + "%' or CodEmpleado=" + codigoempleado + " and " + Busqueda + "  LIKE '%" + txtBuscar.getText() + "%'");
        }
    }

    private void CargarDatosPrincipal() {
        CargarDatosTabla();
        //llenar los datos de los combobox
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        panelOpciones = new javax.swing.JPanel();
        btnImprimir = new rsbuttom.RSButtonMetro();
        btnAceptar = new rsbuttom.RSButtonMetro();
        btnDenegar = new rsbuttom.RSButtonMetro();
        jLabel1 = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblNombreAspirante = new javax.swing.JLabel();
        lblNombreSolicitador = new javax.swing.JLabel();
        lblNombre1 = new javax.swing.JLabel();
        lblDias = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEquipos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cmbBuscar = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();

        jMenuItem2.setText("Limpiar Campos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        setBackground(new java.awt.Color(255, 255, 255));

        panelOpciones.setBackground(new java.awt.Color(255, 255, 255));
        panelOpciones.setComponentPopupMenu(jPopupMenu1);

        btnImprimir.setBackground(new java.awt.Color(114, 191, 68));
        btnImprimir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnImprimir.setText("Imprimir Solicitud");
        btnImprimir.setColorBorde(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnImprimir.setColorHover(new java.awt.Color(0, 191, 68));
        btnImprimir.setColorNormal(new java.awt.Color(114, 191, 68));
        btnImprimir.setColorPressed(new java.awt.Color(0, 49, 30));
        btnImprimir.setColorTextHover(new java.awt.Color(51, 51, 51));
        btnImprimir.setColorTextNormal(new java.awt.Color(51, 51, 51));
        btnImprimir.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnImprimir.setMaximumSize(new java.awt.Dimension(55, 20));
        btnImprimir.setMinimumSize(new java.awt.Dimension(55, 20));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnAceptar.setBackground(new java.awt.Color(114, 191, 68));
        btnAceptar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAceptar.setText("Aceptar Solicitud");
        btnAceptar.setColorBorde(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAceptar.setColorHover(new java.awt.Color(0, 191, 68));
        btnAceptar.setColorNormal(new java.awt.Color(114, 191, 68));
        btnAceptar.setColorPressed(new java.awt.Color(0, 49, 30));
        btnAceptar.setColorTextHover(new java.awt.Color(51, 51, 51));
        btnAceptar.setColorTextNormal(new java.awt.Color(51, 51, 51));
        btnAceptar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnAceptar.setMaximumSize(new java.awt.Dimension(55, 20));
        btnAceptar.setMinimumSize(new java.awt.Dimension(55, 20));
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnDenegar.setBackground(new java.awt.Color(114, 191, 68));
        btnDenegar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDenegar.setText("Denegar Solicitud");
        btnDenegar.setColorBorde(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnDenegar.setColorHover(new java.awt.Color(0, 191, 68));
        btnDenegar.setColorNormal(new java.awt.Color(114, 191, 68));
        btnDenegar.setColorPressed(new java.awt.Color(0, 49, 30));
        btnDenegar.setColorTextHover(new java.awt.Color(51, 51, 51));
        btnDenegar.setColorTextNormal(new java.awt.Color(51, 51, 51));
        btnDenegar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnDenegar.setMaximumSize(new java.awt.Dimension(55, 20));
        btnDenegar.setMinimumSize(new java.awt.Dimension(55, 20));
        btnDenegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDenegarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nombre del empleado aspirante a vacaciones:");

        lblNombre.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre.setText("Empleado que solicito las vacaciones:");

        lblNombreAspirante.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        lblNombreAspirante.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreAspirante.setText("Nombre del empleado ");

        lblNombreSolicitador.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        lblNombreSolicitador.setForeground(new java.awt.Color(0, 0, 0));
        lblNombreSolicitador.setText("nombre empleado");

        lblNombre1.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        lblNombre1.setForeground(new java.awt.Color(0, 0, 0));
        lblNombre1.setText("Días Solicitados:");

        lblDias.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        lblDias.setForeground(new java.awt.Color(0, 0, 0));
        lblDias.setText("Dias Solicitados");

        javax.swing.GroupLayout panelOpcionesLayout = new javax.swing.GroupLayout(panelOpciones);
        panelOpciones.setLayout(panelOpcionesLayout);
        panelOpcionesLayout.setHorizontalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelOpcionesLayout.createSequentialGroup()
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDenegar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelOpcionesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelOpcionesLayout.createSequentialGroup()
                                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(lblNombre))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombreSolicitador)
                                    .addComponent(lblNombreAspirante)
                                    .addComponent(lblDias)))
                            .addComponent(lblNombre1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelOpcionesLayout.setVerticalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblNombreAspirante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(lblNombreSolicitador))
                .addGap(3, 3, 3)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre1)
                    .addComponent(lblDias))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDenegar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane1.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(800, 403));

        tblEquipos.setBackground(new java.awt.Color(204, 255, 204));
        tblEquipos.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        tblEquipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "Nombre Empleado", "Fecha Inicio", "Fecha Final", "Días Solicitados", "¿Quien lo solicito?"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEquipos.setGridColor(new java.awt.Color(0, 0, 0));
        tblEquipos.setSelectionBackground(new java.awt.Color(51, 153, 0));
        tblEquipos.getTableHeader().setReorderingAllowed(false);
        tblEquipos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEquiposMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEquipos);
        if (tblEquipos.getColumnModel().getColumnCount() > 0) {
            tblEquipos.getColumnModel().getColumn(0).setMinWidth(0);
            tblEquipos.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblEquipos.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jLabel2.setText("Buscar por:");

        cmbBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Solicitador" }));
        cmbBuscar.setSelectedItem("IMEI");
        cmbBuscar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbBuscarItemStateChanged(evt);
            }
        });

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1352, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblEquiposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEquiposMouseClicked
        //se trata de obtener los datos de la tabla para mostrarlos en las casillas respectivas con ayuda de sql
        try {
            AccionesCrud classcrud = new AccionesCrud();
            ResultSet rs = classcrud.Seleccion(tblEquipos, "select * from [VistaSolicitudes] where [IdSolicitud]=?", "id");
            while (rs.next()) {
                lblNombreAspirante.setText(rs.getString("Nombre"));
                lblNombreSolicitador.setText(rs.getString("Jefe"));
                lblDias.setText(rs.getString("Dias")+" Días");   
                DiasSolicitados=(rs.getInt("Dias"));
                CodEmpleado=(rs.getInt("CodEmpleado"));
                idSolicitud=(rs.getInt("IdSolicitud"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(pnlSolicitudes.class.getName()).log(Level.SEVERE, null, ex);
        }

        panelOpciones.setVisible(true);
    }//GEN-LAST:event_tblEquiposMouseClicked


    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Limpiar();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    //Funicon para asignar el tipo de busqueda que se va hacer por medio de un switc y los valores de la vista de la BD
    private void cmbBuscarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbBuscarItemStateChanged
        //Cada vez que se cambia el estado del combobox se cambia el filtro de busqueda global para la funcion de busqueda
        String elementoSeleccionado = (String) cmbBuscar.getSelectedItem();
        switch (elementoSeleccionado) {
            case "Nombre":
                Busqueda = "Nombre";
                break;
            case "Solicitador":
                Busqueda = "Jefe";
                break;
            default:
                break;
        }
        CargarDatosTabla();
        txtBuscar.setText("");
    }//GEN-LAST:event_cmbBuscarItemStateChanged

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        //cada vez que se precione una tecla se va a buscar junto al filtro de busqueda en la vista correspondiente
        //se limpia la tabla
        DefaultTableModel modelo = (DefaultTableModel) tblEquipos.getModel();
        modelo.setRowCount(0);
        //se muestra los resultados de la busqueda
        CargarDatosTabla();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        //switch para decidir que validacion establecer cada ves que se preciona una tecla en buscar
        switch (Busqueda) {
            case "Nombre":
                val.EntradaSoloLetas(txtBuscar, evt, 80);
                break;
            case "Jefe":
                val.EntradaSoloLetas(txtBuscar, evt, 80);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void btnDenegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDenegarActionPerformed
         AccionesCrud classcrud = new AccionesCrud();
            if (classcrud.Guardar_Modificar(ArregloDatos(), "exec [NegarSolicitud] ?, ? ,? ")) {
                Limpiar();
                CargarDatosTabla();
            }
    }//GEN-LAST:event_btnDenegarActionPerformed

      private Object[] ArregloDatos() {
        //se crea un arreglo de objetos para enviar a la clase de AccionesCrud y la funcion de Guardar_Modificar
        Object[] datos = new Object[3];
       datos[0]=idSolicitud;
       datos[1]=CodEmpleado;
       datos[2]=DiasSolicitados;

        return datos;
    }
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
           AccionesCrud classcrud = new AccionesCrud();
            if (classcrud.Guardar_Modificar(ArregloDatos(), "exec [AceptarSolicitud] ?, ? ,? ")) {
                Limpiar();
                CargarDatosTabla();
            }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed

    }//GEN-LAST:event_btnImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonMetro btnAceptar;
    private rsbuttom.RSButtonMetro btnDenegar;
    private rsbuttom.RSButtonMetro btnImprimir;
    private javax.swing.JComboBox<String> cmbBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDias;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombre1;
    private javax.swing.JLabel lblNombreAspirante;
    private javax.swing.JLabel lblNombreSolicitador;
    private javax.swing.JPanel panelOpciones;
    private javax.swing.JTable tblEquipos;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
