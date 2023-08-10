package app;

import Clases.Password;
import Clases.Reescalado_Imagenes;
import Clases.validaciones;
import java.awt.Color;
import java.awt.Font;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/*
 * @ErickMoncada controlador de la vista Login
 */
public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
        //asignar icono mediante otra clase
        Reescalado_Imagenes reescalar = new Reescalado_Imagenes();
        setIconImage(reescalar.getIconImage());
        PersonalizacionMensjaes();
        Limpiar();
        val.NegarPegado(txtUsuario);
        val.NegarPegado(txtPassword);
    }
    //se inicializa la clase de validaciones
    validaciones val = new validaciones();

    private void Limpiar() {
        lblErUsuario.setVisible(false);
        lblErPassword.setVisible(false);
    }

    private boolean ValidarCampos() {
        int valor1 = 1;
        String error;
        if (txtUsuario.getText().isEmpty()) {
              //asignar 0 al valor para devolver falso en la validacion
            valor1 = 0;
            //mensaje de error para el respectivo campo de texto
            error = "Escriba un Usuario ";
            //asignar colores de error a cada campo
            val.TXTincorrecto(txtUsuario, lblErUsuario, error);
        }
        if (txtPassword.getText().isEmpty()) {
              //asignar 0 al valor para devolver falso en la validacion
            valor1 = 0;
            //mensaje de error para el respectivo campo de texto
            error = "Escriba una Contraseña ";
            //asignar colores de error a cada campo
            val.TXTincorrecto(txtPassword, lblErPassword, error);
        }
        return valor1 == 1; //Expreciones regulares de los campos
    }

    private void PersonalizacionMensjaes() {
        //se inicia la clase para reescalar imagenes
        Reescalado_Imagenes reescalar = new Reescalado_Imagenes();
        // Cambiar el icono predeterminado para las ventanas de aviso de error
        UIManager.put("OptionPane.warningIcon", reescalar.IconoTextoMenu(40, 40, "img1/advertencia.png"));
        // Cambiar el icono predeterminado para las ventanas de aviso de advertencia
        UIManager.put("OptionPane.informationIcon", reescalar.IconoTextoMenu(40, 40, "img1/alerta.png"));
         // Cambiar el icono predeterminado para las ventanas de Error
        UIManager.put("OptionPane.errorIcon", reescalar.IconoTextoMenu(40, 40, "img1/cancelar.png"));
        //Cambiar el aspecto de los botones de los mensajes predeterminados
        UIManager.put("Button.background", Color.WHITE);
        UIManager.put("Button.foreground", new Color(114, 191, 68));
        // Crear una fuente personalizada
        Font customFont = new Font("Dialog", Font.BOLD, 14);
        // Cambiar la fuente de los botones
        UIManager.put("Button.font", customFont);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnlIzquierda = new javax.swing.JPanel();
        icnMenu = new javax.swing.JLabel();
        pnlDerecha = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnEntrar = new javax.swing.JButton();
        lblRecovery = new javax.swing.JLabel();
        btnRecovery = new javax.swing.JButton();
        lblErUsuario = new javax.swing.JLabel();
        lblErPassword = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicia sesión");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(800, 530));
        setMinimumSize(new java.awt.Dimension(800, 530));
        setName("frmLogin"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(800, 535));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 530));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 530));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 530));
        jPanel1.setLayout(null);

        pnlIzquierda.setBackground(new java.awt.Color(57, 181, 74));
        pnlIzquierda.setMaximumSize(new java.awt.Dimension(400, 500));
        pnlIzquierda.setPreferredSize(new java.awt.Dimension(400, 500));

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
                .addContainerGap(162, Short.MAX_VALUE))
        );

        jPanel1.add(pnlIzquierda);
        pnlIzquierda.setBounds(0, 0, 400, 510);

        pnlDerecha.setBackground(new java.awt.Color(255, 255, 255));
        pnlDerecha.setPreferredSize(new java.awt.Dimension(400, 500));

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 136, 64));
        lblTitulo.setText("Inicia sesión");

        lblCorreo.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblCorreo.setText("Usuario");

        txtUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyTyped(evt);
            }
        });

        lblPassword.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblPassword.setText("Contraseña");

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPasswordKeyTyped(evt);
            }
        });

        btnEntrar.setBackground(new java.awt.Color(0, 136, 64));
        btnEntrar.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        btnEntrar.setForeground(new java.awt.Color(255, 255, 255));
        btnEntrar.setText("Entrar");
        btnEntrar.setBorder(null);
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        lblRecovery.setText("Contraseña olvidada?");

        btnRecovery.setBackground(new java.awt.Color(255, 255, 255));
        btnRecovery.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnRecovery.setForeground(new java.awt.Color(114, 191, 68));
        btnRecovery.setText("Recuperar Contraseña");
        btnRecovery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecoveryActionPerformed(evt);
            }
        });

        lblErUsuario.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErUsuario.setForeground(new java.awt.Color(255, 0, 0));
        lblErUsuario.setText("Error");

        lblErPassword.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lblErPassword.setForeground(new java.awt.Color(255, 0, 0));
        lblErPassword.setText("Error");

        javax.swing.GroupLayout pnlDerechaLayout = new javax.swing.GroupLayout(pnlDerecha);
        pnlDerecha.setLayout(pnlDerechaLayout);
        pnlDerechaLayout.setHorizontalGroup(
            pnlDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDerechaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDerechaLayout.createSequentialGroup()
                        .addGroup(pnlDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCorreo)
                            .addComponent(lblPassword)
                            .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlDerechaLayout.createSequentialGroup()
                                .addComponent(lblRecovery)
                                .addGap(18, 18, 18)
                                .addComponent(btnRecovery)))
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDerechaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblTitulo)
                        .addGap(88, 88, 88))
                    .addGroup(pnlDerechaLayout.createSequentialGroup()
                        .addGroup(pnlDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblErUsuario)
                            .addComponent(lblErPassword))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlDerechaLayout.setVerticalGroup(
            pnlDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDerechaLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblTitulo)
                .addGap(31, 31, 31)
                .addComponent(lblCorreo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(lblPassword)
                .addGap(18, 18, 18)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btnEntrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addGroup(pnlDerechaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRecovery)
                    .addComponent(btnRecovery))
                .addContainerGap())
        );

        jPanel1.add(pnlDerecha);
        pnlDerecha.setBounds(400, 0, 400, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        Password rec = new Password();
        if(ValidarCampos()){
        try {
            Connection con = Conexion.getConexion();
            String sql = ("{CALL IniciarSesion(?, ?, ?,?,?)}");
            CallableStatement stmt = con.prepareCall(sql);
            // Configurar los parámetros de entrada y salida
            stmt.setString(1, txtUsuario.getText().trim());
            //encriptar contraeña
            stmt.setString(2, rec.Encriptar( txtPassword.getText().trim()));
            stmt.registerOutParameter(3, Types.BOOLEAN);
            stmt.registerOutParameter(4, Types.NVARCHAR);
            stmt.registerOutParameter(5, Types.NVARCHAR);
            // Ejecutar el procedimiento almacenado
            stmt.execute();
            // Obtener el resultado de salida
            boolean acceso = stmt.getBoolean(3);
            String NivelAcceso=stmt.getString(4);
            String Nombre=stmt.getString(5);
            if (acceso) {
                //Comprobar si es administrador o lector
               if("Administrador".equals(NivelAcceso) || "Lector".equals(NivelAcceso) || "Root".equals(NivelAcceso)){
                //Abrir Formulario de Menu Principal y se manda el nivel de Acceso
                Principal MenuFrame = new Principal(NivelAcceso,Nombre);
                MenuFrame.setVisible(true);
                MenuFrame.pack();
                MenuFrame.setLocationRelativeTo(null);
                this.dispose();
               }else{
               JOptionPane.showMessageDialog(null, "Este Usuario no tiene el permiso adecuado para entrar al sistema, Contacte a su Jefe ", "Inicio de Sesión",JOptionPane.ERROR_MESSAGE);
               }
            }else{
             JOptionPane.showMessageDialog(null, "Usuario o Contraseña Incorrecta", "Inicio de Sesión",JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
        }
        }
    }//GEN-LAST:event_btnEntrarActionPerformed

    private void btnRecoveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecoveryActionPerformed
        //Abrir Formulario de Recuperar Contraseña
        Recuperar RecoveryFrame = new Recuperar();
        RecoveryFrame.setVisible(true);
        RecoveryFrame.pack();
        RecoveryFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_btnRecoveryActionPerformed
                
    private void txtUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyTyped
        // validado para un campo de tipo texto normal con el parametro de la longitud deseada
        val.EntradaLetrasNumerosSinEspacios(txtUsuario, evt, 30);
    }//GEN-LAST:event_txtUsuarioKeyTyped

    private void txtPasswordKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyTyped
        // validado para un campo de tipo texto normal con el parametro de la longitud deseada
        val.EntradaTextoNormal(txtPassword, evt, 50);
    }//GEN-LAST:event_txtPasswordKeyTyped

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
        //al escribir se quita el estado de error
        val.TXTcorrecto(txtUsuario, lblErUsuario);
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
        //al escribir se quita el estado de error
        val.TXTcorrecto(txtPassword, lblErPassword);
    }//GEN-LAST:event_txtPasswordKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnRecovery;
    private javax.swing.JLabel icnMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblErPassword;
    private javax.swing.JLabel lblErUsuario;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblRecovery;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlDerecha;
    private javax.swing.JPanel pnlIzquierda;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
