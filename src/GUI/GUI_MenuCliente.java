/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static java.time.Clock.system;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author alumnogreibd
 */
public class GUI_MenuCliente extends javax.swing.JDialog {

    /**
     * Creates new form GUI_MenuCliente
     */
    public GUI_MenuCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
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

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.getParent().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosed

    
    
    //Aquí determinamos qué ocurrirá cuando un usuario le de al botón Buscar
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Obtenemos los valores almacenados en los campos de búsqueda
        String searchpeli = campopelicula.getText(); //La pelicula
        String searchfechaold = campofecha.getText();    //El día
        
        //Comprobamos el formato de la fecha antes de continuar
        if (!searchfechaold.matches("\\d{4}-\\d{2}-\\d{2}")) {
            // Display error message to user
            JOptionPane.showMessageDialog(null, "Please enter date in the format yyyy-mm-dd", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        //Hay que pasar el día de tipo de dato string a date
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
        String cine = cines.getSelectedItem().toString();   //El cine
        String titulo = jTable1.getValueAt(selectedRowIndex, 0).toString();
        String fecha = jTable1.getValueAt(selectedRowIndex, 1).toString();
        String hora = jTable1.getValueAt(selectedRowIndex, 2).toString();
        String sala = jTable1.getValueAt(selectedRowIndex, 3).toString();
        
        //Escondemos la ventana actual
        this.setVisible(false);
        
        // Create a new instance of GUI_compraentradas as a JFrame with the selected data
        GUI_compraentradas compraEntradas = new GUI_compraentradas(cine,titulo, fecha, hora, sala);
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
                GUI_MenuCliente dialog = new GUI_MenuCliente(new javax.swing.JFrame(), true);
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
        Connection c= null;
        Properties prop = new Properties();
        FileInputStream file_prop;
    
        try {
             //Abrimos el archivo
            file_prop = new FileInputStream("baseDatos.properties");
            prop.load(file_prop);
            file_prop.close();

            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:" + prop.getProperty("gestor") +
                                            "://" + prop.getProperty("servidor") + 
                                            ":" + prop.getProperty("puerto") +
                                            "/" + prop.getProperty("baseDatos"),
                                            "alumnogreibd", 
                                            "greibd2021");
            
        
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
        c.close();
        
        // Poblamos el desplegable con los nombres de la lista
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(listaCines.toArray(new String[0]));
        cines.setModel(comboBoxModel);
        
    } catch (ClassNotFoundException | SQLException ex) {
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
    Connection c = null;
    PreparedStatement stmt = null;
    Properties prop = new Properties();
    FileInputStream file_prop;
    ResultSet rs = null;
    try {
          //Abrimos el archivo
            file_prop = new FileInputStream("baseDatos.properties");
            prop.load(file_prop);
            file_prop.close();

            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:" + prop.getProperty("gestor") +
                                            "://" + prop.getProperty("servidor") + 
                                            ":" + prop.getProperty("puerto") +
                                            "/" + prop.getProperty("baseDatos"),
                                            "alumnogreibd", 
                                            "greibd2021");
            
        // Preparamos la consulta en función de lo elegido en el campo cine
        String sql = "";
        if ("As Cancelas".equals(searchcine)) { //Introdujo el primer cine
            sql = "select distinct pelicula.titulo, proyectar.fecha, proyectar.hora, sala.numsala " +
                    "from public.pelicula " +
                    "join public.proyectar ON pelicula.id_pelicula = proyectar.id_pelicula " +
                    "join public.cine ON proyectar.id_cine = cine.id_cine " +
                    "join public.sala ON proyectar.sala = sala.numsala " +
                    "where pelicula.titulo like ? and proyectar.fecha = TO_DATE(?, 'YYYY-MM-DD') and cine.nombre = 'As Cancelas' and sala.proyeccion3d = ?::boolean;" ;

        } else if ("Vialia".equals(searchcine)) {  //Introdujo el segundo cine
             sql = "select distinct pelicula.titulo, proyectar.fecha, proyectar.hora, sala.numsala " +
                    "from public.pelicula " +
                    "join public.proyectar ON pelicula.id_pelicula = proyectar.id_pelicula " +
                    "join public.cine ON proyectar.id_cine = cine.id_cine " +
                       "join public.sala ON proyectar.sala = sala.numsala " +
                    "where pelicula.titulo like ? and proyectar.fecha = TO_DATE(?, 'YYYY-MM-DD') and cine.nombre = 'Vialia' and sala.proyeccion3d = ?::boolean;" ;
        }
        
        // Prepare the statement and set the parameters for the query
        stmt = c.prepareStatement(sql);
        stmt.setString(1, "%"+searchpeli+"%");
        stmt.setDate(2, (java.sql.Date) searchfecha);
        stmt.setBoolean(3,is3D);
        
        // Execute the query and get the results
        rs = stmt.executeQuery();
       
        //Actualizamos la tabla con los resultados
        //Indicamos que la tabla va a ser de solo lectura
        ReadOnlyTableModel model = new ReadOnlyTableModel();
        
        
        model.setColumnIdentifiers(new Object[]{"Título", "Fecha", "Hora", "Sala",/* ... */});
        while (rs.next()) { //Recorremos las columnas
            //Insertamos los datos recogidos de la sentencia
            Object[] rowData = new Object[]{rs.getString("titulo"), rs.getString("fecha"), rs.getString("hora"), rs.getString("numsala") /* ... */};
            model.addRow(rowData);
        }
        jTable1.setModel(model);
        
        //Para destacar el elemento marcado
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        // Get the number of rows selected
        int selectedRowCount = jTable1.getSelectedRowCount();
        // If only one row is selected
        if (selectedRowCount == 1) {
            // Update the "Comprar" button's visibility to true
            botoncomprar.setVisible(true);
            // Highlight the selected row
            jTable1.setSelectionBackground(Color.YELLOW);
        } else {
            // Update the "Comprar" button's visibility to false
            botoncomprar.setVisible(false);
            // Reset the row selection background color
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
            if (c != null) c.close();
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
