/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author alumnogreibd
 */
public class GUI_MenuCliente extends javax.swing.JDialog {

    private Connection conexion;
    
    /**
     * Creates new form GUI_MenuCliente
     */
    public GUI_MenuCliente(java.awt.Frame parent, boolean modal, Connection c) {
        super(parent, modal);
        initComponents();
        
        this.conexion=c;
        
        botoncomprar.setVisible(false);
        try {
            //Poblamos de datos el desplegable de cines
            populateComboBox();
        } catch (SQLException ex) {
            Logger.getLogger(GUI_MenuCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GUI_MenuCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Se centra
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

        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        campofecha = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        cines = new javax.swing.JComboBox<>();
        check3D = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        campopelicula = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonPanel = new javax.swing.JPanel();
        botoncomprar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jTabbedPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane1.setToolTipText("");
        jTabbedPane1.setOpaque(true);

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 869, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Consultar comida", jPanel2);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("Cine");

        jLabel3.setText("Fecha");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cines.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cinesActionPerformed(evt);
            }
        });

        check3D.setText("Proyección 3D");
        check3D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check3DActionPerformed(evt);
            }
        });

        jLabel4.setText("Película");

        campopelicula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campopeliculaActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable1);

        botoncomprar.setText("Comprar");
        botoncomprar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                botoncomprarStateChanged(evt);
            }
        });
        botoncomprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoncomprarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(check3D)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(26, 26, 26)
                                        .addComponent(cines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(campopelicula, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(campofecha, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 712, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(botoncomprar))))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(botoncomprar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campopelicula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(campofecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(check3D)
                .addGap(50, 50, 50))
        );

        jTabbedPane1.addTab("Cartelera", jPanel1);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logo.png"))); // NOI18N
        jLabel5.setAlignmentX(0.5F);
        jLabel5.setAlignmentY(0.0F);
        jLabel5.setMinimumSize(new java.awt.Dimension(71, 63));
        jLabel5.setPreferredSize(new java.awt.Dimension(80, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
//Que ocurre cuando se cierra la ventana
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        try{
        this.conexion.close();
        }catch(Exception e){}
        this.getParent().setVisible(true);
        ((JFrame)this.getParent()).setState(Frame.NORMAL);
        this.dispose();
    }//GEN-LAST:event_formWindowClosed
     
    //Qué ocurre cuando se selecciona el botón buscar?
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Obtenemos los valores almacenados en los campos de búsqueda
        String searchpeli = campopelicula.getText(); //La pelicula
        String searchfechaold = campofecha.getText();    //El día
        
        //Comprobamos el formato de la fecha antes de continuar
        if (!searchfechaold.matches("\\d{4}-\\d{2}-\\d{2}")) {
            // Display error message to user
            JOptionPane.showMessageDialog(null, "Por favor, introduzca la fecha con el formato yyyy-mm-dd", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } 
        //Hay que pasar el día de tipo de dato string a date para poder buscar en la base
        java.sql.Date searchfecha = java.sql.Date.valueOf(searchfechaold);
        String searchcine = cines.getSelectedItem().toString();   //El cine
        boolean is3D=check3D.isSelected();  //Si se seleccionó la opción de proyección 3D o no
        try {
            // En el siguiente método más abajo se realizará la operación con los datos obtenidos
            searchPeliculas(searchpeli, searchfecha, searchcine, is3D);
        } catch (IOException ex) {
            Logger.getLogger(GUI_MenuCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_MenuCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cinesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cinesActionPerformed

    private void check3DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check3DActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check3DActionPerformed

    private void campopeliculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campopeliculaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campopeliculaActionPerformed

    //Qué hace el boton comprar cuando lo pulsamos?
    private void botoncomprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoncomprarActionPerformed
        //Le pasamos la info de la fila seleccionada
        int selectedRowIndex = jTable1.getSelectedRow();
        
        // Obtenemos los datos
        String cine = cines.getSelectedItem().toString();
        String titulo = jTable1.getValueAt(selectedRowIndex, 0).toString();
        String fecha = jTable1.getValueAt(selectedRowIndex, 1).toString();
        String hora = jTable1.getValueAt(selectedRowIndex, 2).toString();
        String sala = jTable1.getValueAt(selectedRowIndex, 3).toString();
        
        //Escondemos la ventana actual
        this.setVisible(false);
        
        //Creamos una nueva instancia de la ventana de compra de entradas
        //Le pasamos al constructor los datos que necesitaremos dentro de ella
        GUI_compraentradas compraEntradas = new GUI_compraentradas(cine,titulo, fecha, hora, sala,this.conexion);
        compraEntradas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        compraEntradas.setVisible(true);

        // Add a WindowListener to the GUI_compraentradas window
        compraEntradas.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
            // Show the previous window again when the GUI_compraentradas window is closed
            setVisible(true);
        }
    });
   
    }//GEN-LAST:event_botoncomprarActionPerformed

    private void botoncomprarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_botoncomprarStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_botoncomprarStateChanged

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
            java.util.logging.Logger.getLogger(GUI_MenuCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_MenuCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_MenuCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_MenuCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI_MenuCliente dialog = new GUI_MenuCliente(new javax.swing.JFrame(), true,null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botoncomprar;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JTextField campofecha;
    private javax.swing.JTextField campopelicula;
    private javax.swing.JCheckBox check3D;
    private javax.swing.JComboBox<String> cines;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    /*
    *
    *   METODO PARA POBLAR DE CINES EL DESPLEGABLE
    *
    */
    
    private void populateComboBox() throws SQLException, FileNotFoundException, IOException {
        
         //Se intenta la conexion
        Connection c= this.conexion;
    
        try {
        
            // Creamos un statement para ejecutar la consulta de sql
            Statement statement = c.createStatement();
        
            // Query para obtener los nombres de los cines
            String query = "SELECT nombre FROM Cine";
            //Guardamos el resultado en resultSet
            ResultSet resultSet = statement.executeQuery(query);
        
            // lista para almacenar el resultado
            List<String> listaCines = new ArrayList<>();
        
        // Iteramos en el resultset y guardamos cada coincidencia en la lista
        while (resultSet.next()) {
            String nombre = resultSet.getString("nombre");
            listaCines.add(nombre);
        }
        
        // Cerramos todo antes de acabar
        resultSet.close();
        statement.close();
        
        // Poblamos el desplegable con los nombres de la lista
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(listaCines.toArray(new String[0]));
        cines.setModel(comboBoxModel);
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
    
     /*
    *
    *   METODO PARA MOSTRAR LAS COINCIDENCIAS DE LA BÚSQUEDA
    *
    */
    
    //Gestión de las coincidencias de búsqueda
    
    private void searchPeliculas(String searchpeli, Date searchfecha, String searchcine, Boolean is3D) throws FileNotFoundException, IOException, ClassNotFoundException {
    // Preparamos la conexión a la base de datos
    Connection c = this.conexion;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
        
        // Preparamos la consulta
        String sql ="select pelicula.titulo, proyectar.fecha, proyectar.hora, sala.num_sala " +
                    "from public.pelicula " +
                    "join public.proyectar ON pelicula.id = proyectar.id_pelicula " +
                    "join public.cine ON proyectar.id_cine = cine.id " +
                    "join public.sala ON proyectar.num_sala = sala.num_sala " +
                    "where pelicula.titulo like ? and proyectar.fecha = TO_DATE(?, 'YYYY-MM-DD') and cine.nombre = ? and sala.es_3d = ?::boolean;" ;
        
        
        stmt = c.prepareStatement(sql);
        stmt.setString(1, "%"+searchpeli+"%");  //Permitimos que el campo de película sea optativo/no sea correctamente escrito
        stmt.setDate(2, (java.sql.Date) searchfecha);
        stmt.setString(3,searchcine);
        stmt.setBoolean(4,is3D);
        
        rs = stmt.executeQuery();
       
        //Actualizamos la tabla con los resultados
        //Indicamos que la tabla va a ser de solo lectura
        ReadOnlyTableModel model = new ReadOnlyTableModel();
        //Titulamos cada columna
        model.setColumnIdentifiers(new Object[]{"Título", "Fecha", "Hora", "Sala",/* ... */});
        while (rs.next()) { //Recorremos las proyecciones obtenidas en el query
            //Y los insertamos en cada fila
            Object[] rowData = new Object[]{rs.getString("titulo"), rs.getString("fecha"), rs.getString("hora"), rs.getString("num_sala") /* ... */};
            model.addRow(rowData);
        }
        jTable1.setModel(model);
        
        
        //Con el siguiente fragmento, permitimos que cuando un usuario seleccione una fila (osea una proyección)
        //Esta se marque de botón amarillo y a la vez se habilite el botón de comprar para dicha sesión
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {  //Generamos el nuevo listener de la acción de click
    @Override
    public void valueChanged(ListSelectionEvent e) {    //Método que se encarga de ello
        //Contamos el número de filas seleccionadas
        int selectedRowCount = jTable1.getSelectedRowCount();
        //Solamente permitimos que se seleccione una
        if (selectedRowCount == 1) {//Cuando se selecciona
            //Marcamos como visible el botón comprar
            botoncomprar.setVisible(true);
            //Marcamos de amarillo la fila seleccionada
            jTable1.setSelectionBackground(Color.YELLOW);
        } else {
            //Si se deselecciona una fila se esconde el botón
            //Y se devuelve a su color por defecto
            botoncomprar.setVisible(false);
            jTable1.setSelectionBackground(UIManager.getColor("Table.selectionBackground"));
        }
    }   
});      
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        // Close the database resources
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
//Para hacer la tabla de resultados de solo lectura
public class ReadOnlyTableModel extends DefaultTableModel {

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Make all cells read-only
    }

    // Override other methods as needed for your specific use case
}

    
}
