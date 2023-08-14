package app;

import java.awt.Color;
import paneles.CambiaPanel;
import Clases.Reescalado_Imagenes;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * @ErickMoncada Formulario de la pagina principal
 */
public class Principal extends javax.swing.JFrame {

    //Se inicializa la variable de nivel de acceso
    String NivelDeAcceso;

    public Principal(String NIVEL, String NOMBRE) {
        initComponents();
        //se Recive el nivel de aceso a travez del login y se asigna a una variable global
        NivelDeAcceso = NIVEL;
        //mostrar el menu principal
        new CambiaPanel(pnlPrincipal, new paneles.pnlHome(NOMBRE));
        this.btnSolicitar.setColorNormal(new Color(239, 238, 244));
        this.btnSolicitudes.setColorNormal(new Color(239, 238, 244));
        this.btnUsuariosApp.setColorNormal(new Color(239, 238, 244));
        this.btnCruds.setColorNormal(new Color(239, 238, 244));
        //iniciar funcion para el icono
        setIconImage(reescalar.getIconImage());
        //Iniciar funcion para iconos del submenu , titulo y boton salir
        IconosBarraLateral();
        //iniciar funcion de hora
        Reloj();
        //comprobar que nivel es para el acceso a ajustes
        if ("Lector".equals(NIVEL)) {
            btnUsuariosApp.setVisible(false);
            lblAplicacion.setVisible(false);
        }
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(this);
    }
    //se inicia la clase para reescalar imagenes
    Reescalado_Imagenes reescalar = new Reescalado_Imagenes();

    //asignar los iconos de cada submenu , titulo y boton de salir
    private void IconosBarraLateral() {
        btnSolicitar.setIcon(reescalar.IconoTextoMenu(50, 50, "img1/agregar-usuario.png"));
        btnSolicitudes.setIcon(reescalar.IconoTextoMenu(50, 50, "img1/equipos.png"));
        lblTituloTelefonia.setIcon(reescalar.IconoTextoMenu(50, 50, "img1/Pollos-El-Cortijo.png"));
        btnSalir.setIcon(reescalar.IconoTextoMenu(40, 40, "img1/cerrar-sesion.png"));
        btnUsuariosApp.setIcon(reescalar.IconoTextoMenu(40, 40, "img1/perfil.png"));
    }

    ///funcion para mostrar reloj
    private void Reloj() {
        // Crear un ActionListener para actualizar la hora
        ActionListener actualizarHora = (ActionEvent evento) -> {
            // Obtener la hora actual
            Date horaActual = new Date();
            SimpleDateFormat formatoHora = new SimpleDateFormat("MMM dd yyyy, hh:mm a");
            // Actualizar la etiqueta con la hora actual
            lblHora.setText(formatoHora.format(horaActual));
        };
        // Crear un Timer para actualizar la hora cada segundo
        Timer timer = new Timer(1000, actualizarHora);
        timer.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        pnlMenu = new javax.swing.JPanel();
        btnSolicitar = new rsbuttom.RSButtonMetro();
        jPanel4 = new javax.swing.JPanel();
        lblTelefonia = new javax.swing.JLabel();
        btnSolicitudes = new rsbuttom.RSButtonMetro();
        jPanel5 = new javax.swing.JPanel();
        lblAplicacion = new javax.swing.JLabel();
        btnUsuariosApp = new rsbuttom.RSButtonMetro();
        btnCruds = new rsbuttom.RSButtonMetro();
        pnlCentro = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblHora = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlPrincipal = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnMenu = new javax.swing.JButton();
        lblTituloTelefonia = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Telefonía - Cortijo");
        setMinimumSize(new java.awt.Dimension(700, 700));
        setSize(new java.awt.Dimension(1450, 700));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1450, 700));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        pnlMenu.setBackground(new java.awt.Color(239, 238, 244));
        pnlMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(239, 238, 244)));

        btnSolicitar.setForeground(new java.awt.Color(128, 128, 131));
        btnSolicitar.setText("Solicitar Vacaciones");
        btnSolicitar.setColorHover(new java.awt.Color(204, 204, 204));
        btnSolicitar.setColorNormal(new java.awt.Color(204, 204, 204));
        btnSolicitar.setColorPressed(new java.awt.Color(204, 204, 204));
        btnSolicitar.setColorTextHover(new java.awt.Color(128, 128, 131));
        btnSolicitar.setColorTextNormal(new java.awt.Color(128, 128, 131));
        btnSolicitar.setColorTextPressed(new java.awt.Color(128, 128, 131));
        btnSolicitar.setFocusable(false);
        btnSolicitar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSolicitar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSolicitar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSolicitar.setIconTextGap(25);
        btnSolicitar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSolicitarMousePressed(evt);
            }
        });
        btnSolicitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitarActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(239, 238, 244));

        lblTelefonia.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTelefonia.setForeground(new java.awt.Color(128, 128, 131));
        lblTelefonia.setText("TELEFONÍA");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTelefonia)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(lblTelefonia)
                .addContainerGap())
        );

        btnSolicitudes.setBackground(new java.awt.Color(239, 238, 244));
        btnSolicitudes.setForeground(new java.awt.Color(128, 128, 131));
        btnSolicitudes.setText("Solicitudes de Vacaciones");
        btnSolicitudes.setColorHover(new java.awt.Color(204, 204, 204));
        btnSolicitudes.setColorNormal(new java.awt.Color(239, 238, 244));
        btnSolicitudes.setColorPressed(new java.awt.Color(204, 204, 204));
        btnSolicitudes.setColorTextHover(new java.awt.Color(128, 128, 131));
        btnSolicitudes.setColorTextNormal(new java.awt.Color(128, 128, 131));
        btnSolicitudes.setColorTextPressed(new java.awt.Color(128, 128, 131));
        btnSolicitudes.setFocusable(false);
        btnSolicitudes.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnSolicitudes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSolicitudes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSolicitudes.setIconTextGap(25);
        btnSolicitudes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSolicitudesMousePressed(evt);
            }
        });
        btnSolicitudes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitudesActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(239, 238, 244));

        lblAplicacion.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblAplicacion.setForeground(new java.awt.Color(128, 128, 131));
        lblAplicacion.setText("Aplicación");
        lblAplicacion.setToolTipText("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAplicacion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(lblAplicacion)
                .addContainerGap())
        );

        btnUsuariosApp.setBackground(new java.awt.Color(239, 238, 244));
        btnUsuariosApp.setForeground(new java.awt.Color(128, 128, 131));
        btnUsuariosApp.setText("Usuarios");
        btnUsuariosApp.setColorHover(new java.awt.Color(204, 204, 204));
        btnUsuariosApp.setColorNormal(new java.awt.Color(239, 238, 244));
        btnUsuariosApp.setColorPressed(new java.awt.Color(204, 204, 204));
        btnUsuariosApp.setColorTextHover(new java.awt.Color(128, 128, 131));
        btnUsuariosApp.setColorTextNormal(new java.awt.Color(128, 128, 131));
        btnUsuariosApp.setColorTextPressed(new java.awt.Color(128, 128, 131));
        btnUsuariosApp.setFocusable(false);
        btnUsuariosApp.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnUsuariosApp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUsuariosApp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnUsuariosApp.setIconTextGap(25);
        btnUsuariosApp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnUsuariosAppMousePressed(evt);
            }
        });
        btnUsuariosApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosAppActionPerformed(evt);
            }
        });

        btnCruds.setBackground(new java.awt.Color(239, 238, 244));
        btnCruds.setForeground(new java.awt.Color(128, 128, 131));
        btnCruds.setText("CRUDS");
        btnCruds.setColorHover(new java.awt.Color(204, 204, 204));
        btnCruds.setColorNormal(new java.awt.Color(239, 238, 244));
        btnCruds.setColorPressed(new java.awt.Color(204, 204, 204));
        btnCruds.setColorTextHover(new java.awt.Color(128, 128, 131));
        btnCruds.setColorTextNormal(new java.awt.Color(128, 128, 131));
        btnCruds.setColorTextPressed(new java.awt.Color(128, 128, 131));
        btnCruds.setFocusable(false);
        btnCruds.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCruds.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCruds.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnCruds.setIconTextGap(25);
        btnCruds.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCrudsMousePressed(evt);
            }
        });
        btnCruds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrudsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addGroup(pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSolicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUsuariosApp, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCruds, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnSolicitudes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUsuariosApp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCruds, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weighty = 8.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 1);
        jPanel1.add(pnlMenu, gridBagConstraints);

        pnlCentro.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lblHora.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblHora.setForeground(new java.awt.Color(128, 128, 131));
        lblHora.setText("9:57 A.M. Miercoles");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHora)
                .addContainerGap(1010, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHora)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(null);

        pnlPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        pnlPrincipal.setLayout(new javax.swing.BoxLayout(pnlPrincipal, javax.swing.BoxLayout.LINE_AXIS));
        jScrollPane1.setViewportView(pnlPrincipal);

        javax.swing.GroupLayout pnlCentroLayout = new javax.swing.GroupLayout(pnlCentro);
        pnlCentro.setLayout(pnlCentroLayout);
        pnlCentroLayout.setHorizontalGroup(
            pnlCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        pnlCentroLayout.setVerticalGroup(
            pnlCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCentroLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 0.1;
        jPanel1.add(pnlCentro, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(57, 181, 74));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/menu.png"))); // NOI18N
        btnMenu.setBorder(null);
        btnMenu.setContentAreaFilled(false);
        btnMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        lblTituloTelefonia.setBackground(new java.awt.Color(255, 255, 255));
        lblTituloTelefonia.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblTituloTelefonia.setForeground(new java.awt.Color(255, 255, 255));
        lblTituloTelefonia.setText("Telefonía - Cortijo");

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/cerrar-sesion.png"))); // NOI18N
        btnSalir.setBorder(null);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMenu)
                .addGap(18, 18, 18)
                .addComponent(lblTituloTelefonia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1185, Short.MAX_VALUE)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTituloTelefonia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.2;
        jPanel1.add(jPanel2, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Genera la accion para cambiar de panel USUARIOS llamando a la clase del paquete paneles y cambia los colores de los que se seleccionaron antes
    private void btnSolicitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitarActionPerformed
        new CambiaPanel(pnlPrincipal, new paneles.pnlSolicitar());
        if (this.btnSolicitar.isSelected()) {
            this.btnSolicitar.setColorNormal(new Color(204, 204, 204));
            this.btnSolicitudes.setColorNormal(new Color(239, 238, 244));
            this.btnUsuariosApp.setColorNormal(new Color(239, 238, 244));
            this.btnCruds.setColorNormal(new Color(239, 238, 244));
        } else {
            this.btnSolicitar.setColorNormal(new Color(239, 238, 244));
        }
    }//GEN-LAST:event_btnSolicitarActionPerformed

    //Establecer el estado de seleccionado al correspondiente boton y los demas con el estado desactivado
    private void btnSolicitarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSolicitarMousePressed
        this.btnSolicitar.setSelected(true);
        this.btnSolicitudes.setSelected(false);
        this.btnUsuariosApp.setSelected(false);
        this.btnCruds.setSelected(false);
    }//GEN-LAST:event_btnSolicitarMousePressed

    //Establecer el estado de seleccionado al correspondiente boton y los demas con el estado desactivado
    private void btnSolicitudesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSolicitudesMousePressed
        this.btnSolicitar.setSelected(false);
        this.btnSolicitudes.setSelected(true);
        this.btnUsuariosApp.setSelected(false);
        this.btnCruds.setSelected(false);
    }//GEN-LAST:event_btnSolicitudesMousePressed

    //Genera la accion para cambiar de panel llamando a la clase del paquete paneles y cambia los colores de los que se seleccionaron antes
    private void btnSolicitudesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitudesActionPerformed
        new CambiaPanel(pnlPrincipal, new paneles.pnlSolicitudes());
        if (this.btnSolicitudes.isSelected()) {
            this.btnSolicitudes.setColorNormal(new Color(204, 204, 204));
            this.btnSolicitar.setColorNormal(new Color(239, 238, 244));
            this.btnCruds.setColorNormal(new Color(239, 238, 244));
            this.btnUsuariosApp.setColorNormal(new Color(239, 238, 244));
        } else {
            this.btnSolicitudes.setColorNormal(new Color(239, 238, 244));
        }
    }//GEN-LAST:event_btnSolicitudesActionPerformed

    private Icon rotateIcon(Icon icon, double angleDegrees) {
        //funcion para girar icono
        int width = icon.getIconWidth();
        int height = icon.getIconHeight();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angleDegrees), width / 2, height / 2);
        g2d.drawImage(((ImageIcon) icon).getImage(), transform, null);
        g2d.dispose();
        return new ImageIcon(bufferedImage);
    }

    //Establecer el estado de seleccionado al correspondiente boton y los demas con el estado desactivado
    private void btnUsuariosAppMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUsuariosAppMousePressed
        this.btnSolicitar.setSelected(false);
        this.btnSolicitudes.setSelected(false);
        this.btnUsuariosApp.setSelected(true);
        this.btnCruds.setSelected(false);
    }//GEN-LAST:event_btnUsuariosAppMousePressed

    //Genera la accion para cambiar de panel llamando a la clase del paquete paneles y cambia los colores de los que se seleccionaron antes
    private void btnUsuariosAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosAppActionPerformed
        new CambiaPanel(pnlPrincipal, new paneles.pnlUsuariosAplicacion(NivelDeAcceso));
        if (this.btnUsuariosApp.isSelected()) {
            this.btnSolicitar.setColorNormal(new Color(239, 238, 244));
            this.btnSolicitudes.setColorNormal(new Color(239, 238, 244));
            this.btnCruds.setColorNormal(new Color(239, 238, 244));
            this.btnUsuariosApp.setColorNormal(new Color(204, 204, 204));
        } else {
            this.btnSolicitudes.setColorNormal(new Color(239, 238, 244));
        }
    }//GEN-LAST:event_btnUsuariosAppActionPerformed

    //Accion de boton hamburguesa para desplegar o esconder el menu lateral utilizando la libreria de NefAnimacion
    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        Icon originalIcon = new ImageIcon("src/img1/menu.png");
        int posicion = pnlMenu.getX();
        if (posicion > -1) {
            Animacion.Animacion.mover_izquierda(0, -264, 2, 2, pnlMenu);
            Timer timer = new Timer(400, (ActionEvent e) -> {
                pnlMenu.setVisible(false);
            });
            timer.setRepeats(false);
            timer.start();
            // Girar el icono 90 grados
            Icon rotatedIcon = rotateIcon(originalIcon, 90);
            btnMenu.setIcon(rotatedIcon);
        } else {
            Animacion.Animacion.mover_derecha(-264, 0, 2, 2, pnlMenu);
            pnlMenu.setVisible(true);
            // Establecer el icono de manera normal
            btnMenu.setIcon(originalIcon);
        }
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // Ventana de aviso para cerrar sesion
        int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea Cerrar Sesion?", "Cerrar Sesion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            //Abrir Formulario de Menu Principal
            Login MenuLogin = new Login();
            MenuLogin.setVisible(true);
            MenuLogin.pack();
            MenuLogin.setLocationRelativeTo(null);
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCrudsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCrudsMousePressed
        this.btnSolicitar.setSelected(false);
        this.btnSolicitudes.setSelected(false);
        this.btnUsuariosApp.setSelected(false);
        this.btnCruds.setSelected(true);
    }//GEN-LAST:event_btnCrudsMousePressed

    private void btnCrudsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrudsActionPerformed
         new CambiaPanel(pnlPrincipal, new paneles.pnlCruds());
        if (this.btnCruds.isSelected()) {
            this.btnCruds.setColorNormal(new Color(204, 204, 204));
            this.btnSolicitudes.setColorNormal(new Color(239, 238, 244));
            this.btnUsuariosApp.setColorNormal(new Color(239, 238, 244));
            this.btnSolicitar.setColorNormal(new Color(239, 238, 244));
        } else {
            this.btnSolicitar.setColorNormal(new Color(239, 238, 244));
        }
    }//GEN-LAST:event_btnCrudsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonMetro btnCruds;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnSalir;
    private rsbuttom.RSButtonMetro btnSolicitar;
    private rsbuttom.RSButtonMetro btnSolicitudes;
    private rsbuttom.RSButtonMetro btnUsuariosApp;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAplicacion;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblTelefonia;
    private javax.swing.JLabel lblTituloTelefonia;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlPrincipal;
    // End of variables declaration//GEN-END:variables
}
