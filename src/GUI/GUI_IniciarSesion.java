package GUI;
import java.awt.Frame;
import java.sql.*;
import javax.swing.*;

import DB.BaseDatos;

/**
 *
 * @author alumnogreibd
 */
public class GUI_IniciarSesion extends javax.swing.JFrame {

    /**
     * Creates new form GUI_IniciarSesion
     */
    public GUI_IniciarSesion() {
        initComponents();
        
        //Se asigna un titulo
        this.setTitle("Cinetse");
        
        //Se  centra en la pantalla
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextPane3 = new javax.swing.JTextPane();
        campoNombre = new javax.swing.JTextField();
        jTextPane4 = new javax.swing.JTextPane();
        campoContrasena = new javax.swing.JPasswordField();
        jTextPane2 = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        botonInicio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(getIconImage());
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Futura", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 22, 62));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logo.png"))); // NOI18N
        jLabel1.setText("CinETSE");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setLayout(new java.awt.GridLayout(2, 2, 20, 20));

        jTextPane3.setEditable(false);
        jTextPane3.setBackground(getBackground());
        jTextPane3.setBorder(null);
        jTextPane3.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        jTextPane3.setText("Nombre de usuario:");
        jTextPane3.setCaretColor(getBackground());
        jTextPane3.setDisabledTextColor(getBackground());
        jTextPane3.setSelectionColor(getBackground());
        jPanel3.add(jTextPane3);

        campoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoNombreActionPerformed(evt);
            }
        });
        jPanel3.add(campoNombre);
        campoNombre.getAccessibleContext().setAccessibleName("");

        jTextPane4.setEditable(false);
        jTextPane4.setBackground(getBackground());
        jTextPane4.setBorder(null);
        jTextPane4.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        jTextPane4.setText("Contraseña:");
        jTextPane4.setCaretColor(getBackground());
        jTextPane4.setDisabledTextColor(getBackground());
        jTextPane4.setSelectionColor(getBackground());
        jPanel3.add(jTextPane4);
        jPanel3.add(campoContrasena);

        jTextPane2.setEditable(false);
        jTextPane2.setBackground(getBackground());
        jTextPane2.setBorder(null);
        jTextPane2.setFont(new java.awt.Font("Bitstream Charter", 0, 18)); // NOI18N
        jTextPane2.setText("Inicio de sesión");
        jTextPane2.setCaretColor(getBackground());
        jTextPane2.setDisabledTextColor(getBackground());
        jTextPane2.setSelectionColor(getBackground());

        jButton1.setActionCommand("Registrarse");
        jButton1.setLabel("Registrarse");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        botonInicio.setText("Entrar");
        botonInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(jTextPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jTextPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoNombreActionPerformed

    
    //Que hace el boton cuando se pulsa?
    private void botonInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonInicioActionPerformed
        // TODO add your handling code here:
        
        //Se leen el nombre y contraseña escritos
        String nombre;
        char[] clave;
        
        nombre=campoNombre.getText();
        clave=campoContrasena.getPassword();
        
        try{
            BaseDatos bd = new BaseDatos(nombre, clave);
            Connection c = bd.getConnection();
            
            //Se crea el menú adecuado
            JDialog menu=crearMenu(c,nombre);

            this.setState(Frame.ICONIFIED);
            menu.setVisible(true);
            
            
        //Si no hubo exito, se muestra un popup de error
        }catch(Exception e){
            
            GUI_Error popup=new GUI_Error(this,true,e.getMessage());
            
            popup.setVisible(true);
        }
        
    }//GEN-LAST:event_botonInicioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        GUI_Registrarse registro=new GUI_Registrarse(this,true);
        
        //Se abre la pestaña de registro y se esconde esta
        this.setState(Frame.ICONIFIED);
        registro.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_IniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_IniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_IniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_IniciarSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GUI_IniciarSesion().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonInicio;
    private javax.swing.JPasswordField campoContrasena;
    private javax.swing.JTextField campoNombre;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPane3;
    private javax.swing.JTextPane jTextPane4;
    // End of variables declaration//GEN-END:variables

    public JDialog crearMenu(Connection c, String nombre) throws Exception{
    
        /*
        //Como los usuarios se loguean como correo, podemos usarlo para obtener su id de usuario
        //Esta se la pasaremos a las siguientes guis para que puedan realizar algunas operaciones
        
        String sql_idu= "SELECT id FROM public.socio WHERE correo_electronico=?";
        PreparedStatement s_id=c.prepareStatement(sql_idu);

        s_id.setString(1,nombre);
        int id_user=0;
        ResultSet resultado_id=s_id.executeQuery();
        if(resultado_id.next()){
            id_user = resultado_id.getInt("id"); //Almacenamos el resultado obtenido
        }
        
        
        // Cargamos el usuario en el fichero de propiedades, para que las demás guis lo puedan usar
        Properties props = new Properties();
        props.setProperty("id_user", Integer.toString(id_user));
        try (OutputStream fis = new FileOutputStream("sesioniniciada.properties")) {
            props.store(fis,null);  //Almacenamos en .properties
            System.out.println("Insertado");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Guardamos la actualización del fichero
        try (FileOutputStream fos = new FileOutputStream("sesioniniciada.properties")) {
            props.store(fos, null);
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        JDialog menu=null;
        
        //Se obtiene el rol del usuario, el cual esta guardado en la base de datos de Usuarios
        String sql= "SELECT rol FROM Usuarios WHERE nombre=?";
        PreparedStatement s=c.prepareStatement(sql);

        s.setString(1,nombre);

        ResultSet resultado=s.executeQuery();

        //Si el resultado no es vacío
        if(resultado.next()){
            
            String rol=resultado.getString("rol");
  
            //Según el rol
            switch(rol){

                case "Superusuario":
                    menu=new GUI_MenuAdministrador(this,true,c);
                    break;

                case "Administrador":
                    menu=new GUI_MenuAdministrador(this,true,c);
                    break;

                case "Dependiente":
                    menu=new GUI_MenuDependiente(this,true,c);
                    break;
                
                case "Cliente":
                    menu=new GUI_MenuCliente(this,true,c);
                    break;

                default:
                    throw new Exception("No se ha encontrado el rol del usuario");
            }
        }else{
                throw new Exception("No se ha encontrado el nombre de usuario en la tabla.");
        }
            
        return menu;
    }
}
