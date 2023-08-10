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
        this.btnUserTel.setColorNormal(new Color(239, 238, 244));
        this.btnEquipos.setColorNormal(new Color(239, 238, 244));
        this.btnAjustes.setColorNormal(new Color(239, 238, 244));
        //iniciar funcion para el icono
        setIconImage(reescalar.getIconImage());
        //Iniciar funcion para iconos del submenu , titulo y boton salir
        IconosBarraLateral();
        //iniciar funcion de hora
        Reloj();
        //comprobar que nivel es para el acceso a ajustes
        if ("Lector".equals(NIVEL)) {
            btnAjustes.setVisible(false);
            lblAplicacion.setVisible(false);
        }
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLocationRelativeTo(this);
    }
    //se inicia la clase para reescalar imagenes
    Reescalado_Imagenes reescalar = new Reescalado_Imagenes();

    //asignar los iconos de cada submenu , titulo y boton de salir
    private void IconosBarraLateral() {
        btnUserTel.setIcon(reescalar.IconoTextoMenu(50, 50, "img1/agregar-usuario.png"));
        btnEquipos.setIcon(reescalar.IconoTextoMenu(50, 50, "img1/equipos.png"));
        lblTituloTelefonia.setIcon(reescalar.IconoTextoMenu(50, 50, "img1/Pollos-El-Cortijo.png"));
        btnSalir.setIcon(reescalar.IconoTextoMenu(40, 40, "img1/cerrar-sesion.png"));
        btnAjustes.setIcon(reescalar.IconoTextoMenu(40, 40, "img1/perfil.png"));
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
        btnUserTel = new rsbuttom.RSButtonMetro();
        jPanel4 = new javax.swing.JPanel();
        lblTelefonia = new javax.swing.JLabel();
        btnEquipos = new rsbuttom.RSButtonMetro();
        jPanel5 = new javax.swing.JPanel();
        lblAplicacion = new javax.swing.JLabel();
        btnAjustes = new rsbuttom.RSButtonMetro();
        btnAjustes1 = new rsbuttom.RSButtonMetro();
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

        btnUserTel.setForeground(new java.awt.Color(128, 128, 131));
        btnUserTel.setText("Solicitar Vacaciones");
        btnUserTel.setColorHover(new java.awt.Color(204, 204, 204));
        btnUserTel.setColorNormal(new java.awt.Color(204, 204, 204));
        btnUserTel.setColorPressed(new java.awt.Color(204, 204, 204));
        btnUserTel.setColorTextHover(new java.awt.Color(128, 128, 131));
        btnUserTel.setColorTextNormal(new java.awt.Color(128, 128, 131));
        btnUserTel.setColorTextPressed(new java.awt.Color(128, 128, 131));
        btnUserTel.setFocusable(false);
        btnUserTel.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnUserTel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnUserTel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnUserTel.setIconTextGap(25);
        btnUserTel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnUserTelMousePressed(evt);
            }
        });
        btnUserTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserTelActionPerformed(evt);
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

        btnEquipos.setBackground(new java.awt.Color(239, 238, 244));
        btnEquipos.setForeground(new java.awt.Color(128, 128, 131));
        btnEquipos.setText("Solicitudes de Vacaciones");
        btnEquipos.setColorHover(new java.awt.Color(204, 204, 204));
        btnEquipos.setColorNormal(new java.awt.Color(239, 238, 244));
        btnEquipos.setColorPressed(new java.awt.Color(204, 204, 204));
        btnEquipos.setColorTextHover(new java.awt.Color(128, 128, 131));
        btnEquipos.setColorTextNormal(new java.awt.Color(128, 128, 131));
        btnEquipos.setColorTextPressed(new java.awt.Color(128, 128, 131));
        btnEquipos.setFocusable(false);
        btnEquipos.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnEquipos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEquipos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEquipos.setIconTextGap(25);
        btnEquipos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEquiposMousePressed(evt);
            }
        });
        btnEquipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEquiposActionPerformed(evt);
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

        btnAjustes.setBackground(new java.awt.Color(239, 238, 244));
        btnAjustes.setForeground(new java.awt.Color(128, 128, 131));
        btnAjustes.setText("Usuarios");
        btnAjustes.setColorHover(new java.awt.Color(204, 204, 204));
        btnAjustes.setColorNormal(new java.awt.Color(239, 238, 244));
        btnAjustes.setColorPressed(new java.awt.Color(204, 204, 204));
        btnAjustes.setColorTextHover(new java.awt.Color(128, 128, 131));
        btnAjustes.setColorTextNormal(new java.awt.Color(128, 128, 131));
        btnAjustes.setColorTextPressed(new java.awt.Color(128, 128, 131));
        btnAjustes.setFocusable(false);
        btnAjustes.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAjustes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAjustes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAjustes.setIconTextGap(25);
        btnAjustes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAjustesMousePressed(evt);
            }
        });
        btnAjustes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjustesActionPerformed(evt);
            }
        });

        btnAjustes1.setBackground(new java.awt.Color(239, 238, 244));
        btnAjustes1.setForeground(new java.awt.Color(128, 128, 131));
        btnAjustes1.setText("CRUDS");
        btnAjustes1.setColorHover(new java.awt.Color(204, 204, 204));
        btnAjustes1.setColorNormal(new java.awt.Color(239, 238, 244));
        btnAjustes1.setColorPressed(new java.awt.Color(204, 204, 204));
        btnAjustes1.setColorTextHover(new java.awt.Color(128, 128, 131));
        btnAjustes1.setColorTextNormal(new java.awt.Color(128, 128, 131));
        btnAjustes1.setColorTextPressed(new java.awt.Color(128, 128, 131));
        btnAjustes1.setFocusable(false);
        btnAjustes1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnAjustes1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAjustes1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnAjustes1.setIconTextGap(25);
        btnAjustes1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnAjustes1MousePressed(evt);
            }
        });
        btnAjustes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjustes1ActionPerformed(evt);
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
                    .addComponent(btnUserTel, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAjustes, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAjustes1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnUserTel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnEquipos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(144, 144, 144)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAjustes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAjustes1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private void btnUserTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserTelActionPerformed
        new CambiaPanel(pnlPrincipal, new paneles.pnlSolicitar());
        if (this.btnUserTel.isSelected()) {
            this.btnUserTel.setColorNormal(new Color(204, 204, 204));
            this.btnEquipos.setColorNormal(new Color(239, 238, 244));
            this.btnAjustes.setColorNormal(new Color(239, 238, 244));
        } else {
            this.btnUserTel.setColorNormal(new Color(239, 238, 244));
        }
    }//GEN-LAST:event_btnUserTelActionPerformed

    //Establecer el estado de seleccionado al correspondiente boton y los demas con el estado desactivado
    private void btnUserTelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUserTelMousePressed
        this.btnUserTel.setSelected(true);
        this.btnEquipos.setSelected(false);
        this.btnAjustes.setSelected(false);
    }//GEN-LAST:event_btnUserTelMousePressed

    //Establecer el estado de seleccionado al correspondiente boton y los demas con el estado desactivado
    private void btnEquiposMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEquiposMousePressed
        this.btnUserTel.setSelected(false);
        this.btnEquipos.setSelected(true);
        this.btnAjustes.setSelected(false);
    }//GEN-LAST:event_btnEquiposMousePressed

    //Genera la accion para cambiar de panel llamando a la clase del paquete paneles y cambia los colores de los que se seleccionaron antes
    private void btnEquiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEquiposActionPerformed
        new CambiaPanel(pnlPrincipal, new paneles.pnlSolicitudes());
        if (this.btnEquipos.isSelected()) {
            this.btnEquipos.setColorNormal(new Color(204, 204, 204));
            this.btnUserTel.setColorNormal(new Color(239, 238, 244));
            this.btnAjustes.setColorNormal(new Color(239, 238, 244));
        } else {
            this.btnEquipos.setColorNormal(new Color(239, 238, 244));
        }
    }//GEN-LAST:event_btnEquiposActionPerformed

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
    private void btnAjustesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjustesMousePressed
        this.btnUserTel.setSelected(false);
        this.btnEquipos.setSelected(false);
        this.btnAjustes.setSelected(true);
    }//GEN-LAST:event_btnAjustesMousePressed

    //Genera la accion para cambiar de panel llamando a la clase del paquete paneles y cambia los colores de los que se seleccionaron antes
    private void btnAjustesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjustesActionPerformed
        new CambiaPanel(pnlPrincipal, new paneles.pnlUsuariosAplicacion(NivelDeAcceso));
        if (this.btnAjustes.isSelected()) {
            this.btnUserTel.setColorNormal(new Color(239, 238, 244));
            this.btnEquipos.setColorNormal(new Color(239, 238, 244));
            this.btnAjustes.setColorNormal(new Color(204, 204, 204));
        } else {
            this.btnEquipos.setColorNormal(new Color(239, 238, 244));
        }
    }//GEN-LAST:event_btnAjustesActionPerformed

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

    private void btnAjustes1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAjustes1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAjustes1MousePressed

    private void btnAjustes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjustes1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAjustes1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rsbuttom.RSButtonMetro btnAjustes;
    private rsbuttom.RSButtonMetro btnAjustes1;
    private rsbuttom.RSButtonMetro btnEquipos;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnSalir;
    private rsbuttom.RSButtonMetro btnUserTel;
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
