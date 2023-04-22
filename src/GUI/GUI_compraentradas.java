/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author migue
 */
public class GUI_compraentradas extends javax.swing.JDialog {

    /**
     * Creates new form GUI_compraentradas
     */
    public GUI_compraentradas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    
    //Método para buscar la capacidad de la sala en la base de datos
    private String buscarCap(int numsala, String cine) throws IOException {
      
        //Se intenta la conexion
         Connection c = null;
        PreparedStatement statement = null;
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
        
            // Query para obtener los nombres de los cines
            String query = "SELECT sala.numbutacas FROM public.sala "+
                    "join public.cine "+
                    "on sala.id_cine = cine.id_cine "+
                    "where sala.numsala = ? and cine.nombre = ?;";
            
            // Prepare the statement and set the parameters for the query
            statement = c.prepareStatement(query);
            statement.setString(2, cine);
            statement.setInt(1,numsala);
            
            //Guardamos el resultado en resultSet
            rs = statement.executeQuery();
            String salaCapString;
            // Check if there is a result
            if (rs.next()) {
                // Get the value of the 'numbutacas' column from the result set
                int salaCap = rs.getInt("numbutacas");

                // Convert the integer to string
                salaCapString = Integer.toString(salaCap);
            }else{
                salaCapString="";
            }
        
            // Cerramos todo antes de acabar
            rs.close();
            statement.close();
            c.close();
            
            return salaCapString;
       
        
    } catch (ClassNotFoundException | SQLException ex) {
        ex.printStackTrace();
        return "";
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(GUI_compraentradas.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    
    //Función que busca en la base de datos y devuelve el número de butacas libres
    private String buscareLibres(String cine, String titulo, String fecha, String hora, int numsala){
         
        
        //Se intenta la conexion
         Connection c = null;
        PreparedStatement statement = null;
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
        
            // Query para obtener las entradas
            String query = "SELECT count(entrada.numasiento) " +
            "FROM public.entrada " +
            "JOIN public.proyectar ON proyectar.fecha=entrada.fecha " +
            "AND proyectar.hora=entrada.hora " +
            "AND proyectar.sala=entrada.sala " +
            "AND proyectar.id_cine=entrada.id_cine " +
            "JOIN public.pelicula ON proyectar.id_pelicula=pelicula.id_pelicula " +
            "JOIN public.cine ON cine.id_cine=entrada.id_cine " +
            "WHERE entrada.fecha=TO_DATE(?, 'YYYY-MM-DD') " +
            "AND entrada.hora=CAST(? AS TIME) " +
            "AND entrada.sala=? " +
            "AND cine.nombre=? " +
            "AND pelicula.titulo=?";
            
            // Prepare the statement and set the parameters for the query
            statement = c.prepareStatement(query);
            statement.setString(1,fecha);
            statement.setString(2, hora);
            statement.setInt(3, numsala);
            statement.setString(4, cine);
            statement.setString(5, titulo);
            
            //Guardamos el resultado en resultSet
            rs = statement.executeQuery();
            String salaCapString;
            // Check if there is a result
            if (rs.next()) {
                // Get the value of the 'numbutacas' column from the result set
                int numEnt = rs.getInt("count");

                // Convert the integer to string
                salaCapString = Integer.toString(numEnt);
            }else{
                salaCapString="";
            }
        
            // Cerramos todo antes de acabar
            rs.close();
            statement.close();
            c.close();
            
            return salaCapString;
       
        
    } catch (ClassNotFoundException | SQLException ex) {
        ex.printStackTrace();
        return "";
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(GUI_compraentradas.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        } catch (IOException ex) {
            Logger.getLogger(GUI_compraentradas.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    
    //Función que busca en la base de datos y devuelve el precio de la entrada
    private String buscarPrecio(String cine, String titulo, String fecha, String hora, int numsala){
        //Se intenta la conexion
        Connection c = null;
        PreparedStatement statement = null;
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
        
            // Query para obtener las entradas
            String query = "SELECT producto.precio " +
            "FROM public.producto " +
            "JOIN public.entrada ON entrada.id_producto=producto.id_producto " +
            "JOIN public.proyectar ON proyectar.fecha=entrada.fecha " +  
            "JOIN public.pelicula ON proyectar.id_pelicula=pelicula.id_pelicula " +
            "JOIN public.cine ON cine.id_cine=entrada.id_cine " +
            "WHERE entrada.fecha=TO_DATE(?, 'YYYY-MM-DD') " +
            "AND entrada.hora=CAST(? AS TIME) " +
            "AND entrada.sala=? " +
            "AND cine.nombre=? " +
            "AND pelicula.titulo=?";
            
            
            // Prepare the statement and set the parameters for the query
            statement = c.prepareStatement(query);
            statement.setString(1,fecha);
            statement.setString(2, hora);
            statement.setInt(3, numsala);
            statement.setString(4, cine);
            statement.setString(5, titulo);
            
            //Guardamos el resultado en resultSet
            rs = statement.executeQuery();
            String ePrecio;
           // Check if there is a result
            if (rs.next()) {
                // Get the value of the 'numbutacas' column from the result set
                int numEnt = rs.getInt("precio");

                // Convert the integer to string
                ePrecio = Integer.toString(numEnt);
            }else{
                ePrecio="";
            }
        
            // Cerramos todo antes de acabar
            rs.close();
            statement.close();
            c.close();
            
            return ePrecio;
       
        
    } catch (ClassNotFoundException | SQLException ex) {
        ex.printStackTrace();
        return "";
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(GUI_compraentradas.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        } catch (IOException ex) {
            Logger.getLogger(GUI_compraentradas.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public GUI_compraentradas(String cine, String titulo, String fecha, String hora, String numsala1) {
        initComponents(); // Inicializamos la vista       
        //Pasamos numsala a int
        int numsala = Integer.parseInt(numsala1); // Parse the string to an int
        //Almacenamos los strings recibidos por el constructor en su sitio
        panelPeli.setText(titulo);
        panelFecha.setText(fecha);
        panelHora.setText(hora);
        panelSala.setText(numsala1);
        try {
            capacidadsala.setText(buscarCap(numsala,cine));
        } catch (IOException ex) {
            Logger.getLogger(GUI_compraentradas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Llenamos de valores el desplegable de número de entradas
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) Numerodeentradas.getModel();
        model.removeAllElements();
        for (int i = 1; i <= 6; i++) {
            model.addElement(Integer.toString(i));
        }
        Numerodeentradas.setSelectedItem("1");
        entradaslibres.setText(buscareLibres(cine,titulo,fecha,hora,numsala));
        precioentrada.setText(buscarPrecio(cine,titulo,fecha,hora,numsala));
        total.setText(precioentrada.getText());
        
        // Lo ponemos como read only
        panelPeli.setEditable(false);
        panelFecha.setEditable(false);
        panelHora.setEditable(false);
        panelSala.setEditable(false);
        capacidadsala.setEditable(false);
        entradaslibres.setEditable(false);
        precioentrada.setEditable(false);
        total.setEditable(false);
        
        // Set other properties for the window
        setTitle("Compra de entradas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window, not the entire application
        setLocationRelativeTo(null); // Center the window on screen
        setResizable(false);
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelPeli = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        panelFecha = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        panelHora = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        panelSala = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        capacidadsala = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        entradaslibres = new javax.swing.JTextPane();
        jLabel2 = new javax.swing.JLabel();
        Numerodeentradas = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        precioentrada = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        total = new javax.swing.JTextPane();
        finalizarcompra = new javax.swing.JButton();
        compracomida = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane7.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logo.png"))); // NOI18N
        jLabel5.setAlignmentX(0.5F);
        jLabel5.setAlignmentY(0.0F);
        jLabel5.setMinimumSize(new java.awt.Dimension(71, 63));
        jLabel5.setPreferredSize(new java.awt.Dimension(80, 80));

        panelPeli.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        panelPeli.setForeground(new java.awt.Color(153, 0, 0));
        jScrollPane1.setViewportView(panelPeli);

        jScrollPane3.setViewportView(panelFecha);

        jScrollPane4.setViewportView(panelHora);

        jScrollPane5.setViewportView(panelSala);

        jScrollPane6.setViewportView(capacidadsala);

        jScrollPane2.setViewportView(entradaslibres);

        jLabel2.setText("Butacas disponibles");

        Numerodeentradas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Numerodeentradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumerodeentradasActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(153, 0, 0));
        jLabel3.setText("Precio total");

        jLabel4.setText("Precio por entrada");

        jScrollPane8.setViewportView(precioentrada);

        jScrollPane9.setViewportView(total);

        finalizarcompra.setText("Finalizar compra");
        finalizarcompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarcompraActionPerformed(evt);
            }
        });

        compracomida.setText("Añadir complementos");
        compracomida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compracomidaActionPerformed(evt);
            }
        });

        jLabel6.setText("Número de entradas");

        jLabel7.setText("Título");

        jLabel8.setText("Día");

        jLabel9.setText("Hora");

        jLabel10.setText("Sala");

        jLabel1.setText("Capacidad de la sala");

        jLabel11.setText("€");

        jLabel12.setText("€");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(Numerodeentradas, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(compracomida, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(finalizarcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(98, 98, 98)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(compracomida)
                                .addComponent(jLabel11)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(finalizarcompra)
                                .addComponent(jLabel12))))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Numerodeentradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void compracomidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compracomidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compracomidaActionPerformed
//Cuando se selecciona una opción se actualiza el coste total
    private void NumerodeentradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumerodeentradasActionPerformed

            // Obtiene el número de entradas seleccionado
            String selectedOptionString = (String) Numerodeentradas.getSelectedItem();
            System.out.println(selectedOptionString);
            int selectedOption=1;
            if (selectedOptionString != null && !selectedOptionString.isEmpty()) {
                selectedOption = Integer.parseInt(selectedOptionString);
            }
            System.out.println(selectedOption);
            // Obtiene el precio por entrada
            String precioentradastring= precioentrada.getText();
            int precioint =1;
            if (precioentradastring!= null && !precioentradastring.isEmpty()) {
                precioint = Integer.parseInt(precioentradastring);
            }
            //Actualizamos el coste total (numentradas*precioporentrada)
            total.setText(Integer.toString(precioint * selectedOption));
    }//GEN-LAST:event_NumerodeentradasActionPerformed
//Cuando el usuario le da al botón de finalizar compra
    private void finalizarcompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarcompraActionPerformed
        //Tenemos que controlar que el numero de entradas elegidas no sobrepasa al de entradas disponibles
        try {
                //Obtenemos los valores
                int numentradas = Integer.parseInt((String) Numerodeentradas.getSelectedItem());
                int entradasdisponibles = Integer.parseInt(entradaslibres.getText());

                if (numentradas> entradasdisponibles) { //Numero incorrecto. Se gestiona el error
                    JOptionPane.showMessageDialog(GUI_compraentradas.this, "Error: Se seleccionaron más entradas de las disponibles", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{   //Número correcto. Actualizamos el número de butacas libres y volvemos a la ventana principal
                    int newedisponibles = entradasdisponibles - numentradas;
                    entradaslibres.setText(Integer.toString(newedisponibles));
                    setVisible(false);
                    dispose();
                    GUI_MenuCliente gui = new GUI_MenuCliente(null,true);
                    gui.setVisible(true);
                }
            } catch (NumberFormatException ex) {
                // Handle invalid input in ComboBox or TextPane
                JOptionPane.showMessageDialog(GUI_compraentradas.this, "Error: Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_finalizarcompraActionPerformed

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
            java.util.logging.Logger.getLogger(GUI_compraentradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_compraentradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_compraentradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_compraentradas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI_compraentradas dialog = new GUI_compraentradas(new javax.swing.JFrame(), true);
                
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
    private javax.swing.JComboBox<String> Numerodeentradas;
    private javax.swing.JTextPane capacidadsala;
    private javax.swing.JButton compracomida;
    private javax.swing.JTextPane entradaslibres;
    private javax.swing.JButton finalizarcompra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane panelFecha;
    private javax.swing.JTextPane panelHora;
    private javax.swing.JTextPane panelPeli;
    private javax.swing.JTextPane panelSala;
    private javax.swing.JTextPane precioentrada;
    private javax.swing.JTextPane total;
    // End of variables declaration//GEN-END:variables
}
