package app;

import Clases.Password;
import Clases.Reescalado_Imagenes;
import Clases.validaciones;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * @ErickMoncada controlador de recuperar contraseña
 */
public class Recuperar extends javax.swing.JFrame {

    public Recuperar() {
        initComponents();
        //asignar icono mediante otra clase
        Reescalado_Imagenes reescalar = new Reescalado_Imagenes();
        setIconImage(reescalar.getIconImage());
        jpnCodigo.setVisible(false);
        jpnPassword.setVisible(false);
        lblErUsuario.setVisible(false);
        lblErPassword.setVisible(false);
        lblErCodigo.setVisible(false);
        val.NegarPegado(txtUsuario);
        val.NegarPegado(txtCodigo);
        val.NegarPegado(txtNewPassword);
        val.NegarPegado(txtRepPassword);
    }
    //se inicializa la clase de validaciones
    validaciones val = new validaciones();
    //se inicializa la variable del codigo
    int codigo;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        pnlIzquierda = new javax.swing.JPanel();
        icnMenu = new javax.swing.JLabel();
        pnlDerecha = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblInicio = new javax.swing.JLabel();
        btnInicio = new javax.swing.JButton();
        jpnCodigo = new javax.swing.JPanel();
        txtCodigo = new javax.swing.JTextField();
        lblCodigo = new javax.swing.JLabel();
        btnCodigo = new javax.swing.JButton();
        lblErCodigo = new javax.swing.JLabel();
        jpnUsuario = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        lblErUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        jpnPassword = new javax.swing.JPanel();
        lblNewPassword = new javax.swing.JLabel();
        txtNewPassword = new javax.swing.JPasswordField();
        lblRepPassword = new javax.swing.JLabel();
        txtRepPassword = new javax.swing.JPasswordField();
        btnCambiar = new javax.swing.JButton();
        lblErPassword = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Recuperar Contraseña");
        setMaximumSize(new java.awt.Dimension(800, 530));
        setMinimumSize(new java.awt.Dimension(800, 530));
        setPreferredSize(new java.awt.Dimension(800, 530));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 530));

        pnlPrincipal.setBackground(new java.awt.Color(255, 255, 255));
        pnlPrincipal.setPreferredSize(new java.awt.Dimension(800, 500));
        pnlPrincipal.setLayout(null);

        pnlIzquierda.setBackground(new java.awt.Color(57, 181, 74));

        icnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img1/Pollos-El-Cortijo.png"))); // NOI18N

        javax.swing.GroupLayout pnlIzquierdaLayout = new javax.swing.GroupLayout(pnlIzquierda);
        pnlIzquierda.setLayout(pnlIzquierdaLayout);
        pnlIzquierdaLayout.setHorizontalGroup(
            pnlIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlIzquierdaLayout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(icnMenu)
                .addGap(47, 47, 47))
        );
        pnlIzquierdaLayout.setVerticalGroup(
            pnlIzquierdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIzquierdaLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(icnMenu)
                .addContainerGap(152, Short.MAX_VALUE))
        );

        pnlPrincipal.add(pnlIzquierda);
        pnlIzquierda.setBounds(0, 0, 400, 500);

        pnlDerecha.setBackground(new java.awt.Color(255, 255, 255));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 32)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 136, 64));
        lblTitulo.setText("Recuperar Contraseña");

        lblInicio.setText("Inicia sesión:");

        btnInicio.setBackground(new java.awt.Color(255, 255, 255));
        btnInicio.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(114, 191, 68));
        btnInicio.setText("Inicio");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        jpnCodigo.setOpaque(false);

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoKeyTyped(evt);
            }
        });

        lblCodigo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblCodigo.setText("Ingresa el codigó:");

        btnCodigo.setBackground(new java.awt.Color(114, 191, 68));
        btnCodigo.setForeground(new java.awt.Color(255, 255, 255));
        btnCodigo.setText("Confirmar");
        btnCodigo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCodigoActionPerformed(evt);
            }
        });

        lblErCodigo.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErCodigo.setForeground(new java.awt.Color(255, 0, 0));
        lblErCodigo.setText("Error");

        javax.swing.GroupLayout jpnCodigoLayout = new javax.swing.GroupLayout(jpnCodigo);
        jpnCodigo.setLayout(jpnCodigoLayout);
        jpnCodigoLayout.setHorizontalGroup(
            jpnCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCodigoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpnCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblErCodigo)
                    .addGroup(jpnCodigoLayout.createSequentialGroup()
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnCodigoLayout.setVerticalGroup(
            jpnCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnCodigoLayout.createSequentialGroup()
                .addGroup(jpnCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpnUsuario.setOpaque(false);

        lblUsuario.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblUsuario.setText("Usuario");

        lblErUsuario.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErUsuario.setForeground(new java.awt.Color(255, 0, 0));
        lblErUsuario.setText("Error");

        txtUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUsuario.setMaximumSize(new java.awt.Dimension(300, 40));
        txtUsuario.setMinimumSize(new java.awt.Dimension(300, 40));
        txtUsuario.setPreferredSize(new java.awt.Dimension(300, 40));
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });

        btnEnviar.setBackground(new java.awt.Color(0, 136, 64));
        btnEnviar.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnEnviar.setForeground(new java.awt.Color(255, 255, 255));
        btnEnviar.setText("Enviar Código");
        btnEnviar.setBorder(null);
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnUsuarioLayout = new javax.swing.GroupLayout(jpnUsuario);
        jpnUsuario.setLayout(jpnUsuarioLayout);
        jpnUsuarioLayout.setHorizontalGroup(
            jpnUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario)
                    .addComponent(lblErUsuario)
                    .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jpnUsuarioLayout.setVerticalGroup(
            jpnUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnUsuarioLayout.createSequentialGroup()
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpnPassword.setOpaque(false);

        lblNewPassword.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblNewPassword.setText("Contraseña Nueva");

        txtNewPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNewPasswordKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNewPasswordKeyTyped(evt);
            }
        });

        lblRepPassword.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblRepPassword.setText("Repetir Contraseña");

        txtRepPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRepPasswordKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRepPasswordKeyTyped(evt);
            }
        });

        btnCambiar.setBackground(new java.awt.Color(114, 191, 68));
        btnCambiar.setForeground(new java.awt.Color(255, 255, 255));
        btnCambiar.setText("Cambiar Contraseña");
        btnCambiar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCambiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarActionPerformed(evt);
            }
        });

        lblErPassword.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErPassword.setForeground(new java.awt.Color(255, 0, 0));
        lblErPassword.setText("Las Contraseñas no Coinciden");

        javax.swing.GroupLayout jpnPasswordLayout = new javax.swing.GroupLayout(jpnPassword);
        jpnPassword.setLayout(jpnPasswordLayout);
        jpnPasswordLayout.setHorizontalGroup(
            jpnPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPasswordLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnPasswordLayout.createSequentialGroup()
                        .addComponent(txtRepPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpnPasswordLayout.createSequentialGroup()
                        .addGroup(jpnPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNewPassword)
                            .addComponent(txtNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRepPassword)
                            .addComponent(lblErPassword))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnPasswordLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );
        jpnPasswordLayout.setVerticalGroup(
            jpnPasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnPasswordLayout.createSequentialGroup()
                .addComponent(lblNewPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNewPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRepPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtRepPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCambiar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlDerechaLayout = new javax.swing.GroupLayout(pnlDerecha);
        pnlDerecha.setLayout(pnlDerechaLayout);
        pnlDerechaLayout.setHorizontalGroup(
            pnlDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDerechaLayout.createSequentialGroup()
                .addGroup(pnlDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDerechaLayout.createSequentialGroup()
                        .addGroup(pnlDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDerechaLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(lblTitulo))
                            .addGroup(pnlDerechaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblInicio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlDerechaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpnUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jpnPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlDerechaLayout.setVerticalGroup(
            pnlDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDerechaLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(jpnUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblInicio)
                    .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlPrincipal.add(pnlDerecha);
        pnlDerecha.setBounds(400, 0, 400, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 811, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        if (!"".equals(txtUsuario.getText())) {
            Password rec = new Password();
            codigo = rec.EnviarCodigo(txtUsuario.getText());
            //se comprueba si se envió el correo
            switch (codigo) {
                case 0:
                    //codigo en 0 significa usuario no encontrado
                    val.TXTincorrecto(txtUsuario, lblErUsuario, "El Usuario no Existe, verifique el Usuario");
                    break;
                case 01:
                    //codigo en 01 significa datos de smtp mal configurados
                    val.TXTincorrecto(txtUsuario, lblErUsuario, "Servicio de Correo Inoperante");
                    break;
                default:
                    jpnCodigo.setVisible(true);
                    jpnUsuario.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Se ha enviado un código al correo electrónico para poder cambiar la contraseña", "Recuperar Contraseña", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        } else {
            //establecemos en incorrecto el capo
            val.TXTincorrecto(txtUsuario, lblErUsuario, "El usuario no puede estar en blanco");
        }
    }//GEN-LAST:event_btnEnviarActionPerformed

    private void btnCambiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarActionPerformed
        if (txtNewPassword.getText().equals("") || txtNewPassword.getText().length() < 5) {
            val.GENIncorrecto(lblErPassword, "La contraseña debe ser mayor a 5 caracteres (números  o letras)");
        } else if (txtNewPassword.getText().equals(txtRepPassword.getText())) {
            try {
                Password rec = new Password();
                Connection con = Conexion.getConexion();
                String sql = ("{CALL UpdatePassword(?, ?)}");
                CallableStatement stmt = con.prepareCall(sql);
                // Configurar los parámetros de entrada y salida
                stmt.setString(1, txtUsuario.getText().trim());
                //encriptar la contraseña
                stmt.setString(2, rec.Encriptar(txtNewPassword.getText().trim()));
                // Ejecutar el procedimiento almacenado
                stmt.execute();
                // Obtener el resultado de salida
                JOptionPane.showMessageDialog(null, "La Contraseña ha sido actualizada", "Recuperar Contraseña", JOptionPane.INFORMATION_MESSAGE);
                jpnPassword.setVisible(false);
                jpnUsuario.setVisible(true);
                txtUsuario.setText("");
                txtCodigo.setText("");
                txtNewPassword.setText("");
                txtRepPassword.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Hubo un error al tratar de actualizar la contraseña, contacte a soporte", "Recuperar Contraseña", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            val.GENIncorrecto(lblErPassword, "Las Contraseñas no Coinciden");
        }
    }//GEN-LAST:event_btnCambiarActionPerformed

    private void btnCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCodigoActionPerformed
        if ("".equals(txtCodigo.getText())) {
            val.TXTincorrecto(txtCodigo, lblErCodigo, "Escriba el código que se le envió al correo");
        } else if (Integer.parseInt(txtCodigo.getText()) == codigo) {
            jpnPassword.setVisible(true);
            jpnUsuario.setVisible(false);
            jpnCodigo.setVisible(false);
        } else {
            val.TXTincorrecto(txtCodigo, lblErCodigo, "El Código no coincide");
        }
    }//GEN-LAST:event_btnCodigoActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        //regresar al login
        Login LoginFrame = new Login();
        LoginFrame.setVisible(true);
        LoginFrame.pack();
        LoginFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        // validado para un campo de tipo texto normal con el parametro de la longitud deseada
        val.EntradaTextoNormal(txtUsuario, evt, 30);
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtCodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyTyped
        //campo valido para 5 numeros
        val.EntradaNumeros(txtCodigo, evt, 5);
    }//GEN-LAST:event_txtCodigoKeyTyped

    private void txtNewPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNewPasswordKeyTyped
        // validado para un campo de tipo texto normal con el parametro de la longitud deseada
        val.EntradaTextoNormal(txtNewPassword, evt, 50);
    }//GEN-LAST:event_txtNewPasswordKeyTyped

    private void txtRepPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRepPasswordKeyTyped
        // validado para un campo de tipo texto normal con el parametro de la longitud deseada
        val.EntradaTextoNormal(txtRepPassword, evt, 50);
    }//GEN-LAST:event_txtRepPasswordKeyTyped

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
        //establecemos en correcto el campo
        val.TXTcorrecto(txtUsuario, lblErUsuario);
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        //establecemos en correcto el campo
        val.TXTcorrecto(txtCodigo, lblErCodigo);
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtNewPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNewPasswordKeyReleased
        if (!"".equals(txtRepPassword.getText()) && (!txtNewPassword.getText().equals(txtRepPassword.getText()))) {
            val.GENcorrecto(lblErPassword);
        } else {
            val.GENIncorrecto(lblErPassword, "Las Contraseñas no Coinciden");
        }
    }//GEN-LAST:event_txtNewPasswordKeyReleased

    private void txtRepPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRepPasswordKeyReleased
        if (txtNewPassword.getText().equals(txtRepPassword.getText())) {
            val.GENcorrecto(lblErPassword);
        } else {
            val.GENIncorrecto(lblErPassword, "Las Contraseñas no Coinciden");
        }
    }//GEN-LAST:event_txtRepPasswordKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiar;
    private javax.swing.JButton btnCodigo;
    private javax.swing.JButton btnEnviar;
    private javax.swing.JButton btnInicio;
    private javax.swing.JLabel icnMenu;
    private javax.swing.JPanel jpnCodigo;
    private javax.swing.JPanel jpnPassword;
    private javax.swing.JPanel jpnUsuario;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblErCodigo;
    private javax.swing.JLabel lblErPassword;
    private javax.swing.JLabel lblErUsuario;
    private javax.swing.JLabel lblInicio;
    private javax.swing.JLabel lblNewPassword;
    private javax.swing.JLabel lblRepPassword;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlDerecha;
    private javax.swing.JPanel pnlIzquierda;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JPasswordField txtNewPassword;
    private javax.swing.JPasswordField txtRepPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
