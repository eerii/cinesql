/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import java.sql.PreparedStatement;

public class GUI_vistasesion_dependiente extends javax.swing.JDialog {
    
    private Connection conexion;

    public GUI_vistasesion_dependiente(java.awt.Frame parent, boolean modal, Connection c) {
        super(parent, modal);
        initComponents();
        
        this.conexion=c;
    }

    
    //Método para buscar la capacidad de la sala en la base de datos
    private String buscarCap(int numsala, String cine) throws IOException {
        //Se intenta la conexion
         Connection c = this.conexion;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
        
            // Query para obtener la capacidad
            String query = "SELECT sala.num_butacas FROM public.sala "+
                    "join public.cine "+
                    "on sala.id_cine = cine.id_cine "+
                    "where sala.num_sala = ? and cine.nombre = ?;";
            
            //Preparamos el statement con los datos recibidos
            statement = c.prepareStatement(query);
            statement.setString(2, cine);
            statement.setInt(1,numsala);
            
            //Guardamos el resultado en resultSet
            rs = statement.executeQuery();
            String salaCapString;
            //Comprobamos si hay resultado
            if (rs.next()) {
                //Obtenemos el valor de la columna de salida num_butacas
                int salaCap = rs.getInt("num_butacas");
                //Casteamos a string
                salaCapString = Integer.toString(salaCap);
            }else{
                salaCapString="";
            }
            // Cerramos todo antes de acabar
            rs.close();
            statement.close();
            return salaCapString;
               
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    //Función que busca en la base de datos y devuelve el número de butacas libres
    private String buscareLibres(String cine, String titulo, String fecha, String hora, int numsala){
        
        //Se intenta la conexion
         Connection c = this.conexion;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;
    
        try {
        
            // Query para obtener las entradas que se emitieron para la película
            String query = "SELECT count(entrada.num_asiento) " +
            "FROM public.entrada " +
            "JOIN public.proyectar ON proyectar.fecha=entrada.fecha " +
            "AND proyectar.hora=entrada.hora " +
            "AND proyectar.id_sala=entrada.id_sala " +
            "AND proyectar.id_cine=entrada.id_cine " +
            "JOIN public.pelicula ON proyectar.id_pelicula=pelicula.id_pelicula " +
            "JOIN public.cine ON cine.id_cine=entrada.id_cine " +
            "JOIN public.sala ON sala.id_sala=entrada.id_sala " +
            "WHERE entrada.fecha=TO_DATE(?, 'YYYY-MM-DD') " +
            "AND entrada.hora=CAST(? AS TIME) " +
            "AND sala.num_sala=? " +
            "AND cine.nombre=? " +
            "AND pelicula.titulo=?";
            
            statement = c.prepareStatement(query);
            statement.setString(1,fecha);
            statement.setString(2, hora);
            statement.setInt(3, numsala);
            statement.setString(4, cine);
            statement.setString(5, titulo);
            
            //Guardamos el resultado en resultSet
            rs = statement.executeQuery();
            int numEnttotal=0;
            if (rs.next()) {
                numEnttotal = rs.getInt("count");
            }
            
            
            //Query con el que obtendremos el número de entradas que ya fueron vendidas            
            String query2= "SELECT entradas_vendidas(?, ?, ?, ?, ?)";
            statement2 = c.prepareStatement(query2);
            statement2.setString(1, cine);
            statement2.setString(2, titulo);
            statement2.setString(3,fecha);
            statement2.setString(4, hora);
            statement2.setInt(5, numsala);
            
            //Guardamos el resultado en resultSet
            rs2 = statement2.executeQuery();
            int numEntvendidas=0;
            if (rs2.next()) {
                numEntvendidas = rs2.getInt(1);
            }
            
            //Almacenamos en este string el número de entradas disponibles y lo devolvemos
            String numEntdisponibles=Integer.toString(numEnttotal-numEntvendidas);
            // Cerramos todo antes de acabar
            rs.close();
            rs2.close();
            statement.close();
            statement2.close();
            
            return numEntdisponibles;
       
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    //Función que busca en la base de datos y devuelve el precio de la entrada
    private String buscarPrecio(String cine, String titulo, String fecha, String hora, int numsala){
        //Se intenta la conexion
        Connection c = this.conexion;
        PreparedStatement statement = null;
        ResultSet rs = null;
    
        try {
        
            // Query para obtener el precio de una entrada de la sesión
            String query = "SELECT getPrecio(?, ?, ?, ?, ?)";
            statement = c.prepareStatement(query);
            statement.setString(1,fecha);
            statement.setString(2, hora);
            statement.setInt(3, numsala);
            statement.setString(4, cine);
            statement.setString(5, titulo);
            
            //Guardamos el resultado en resultSet
            rs = statement.executeQuery();
            String ePrecio;
            if (rs.next()) {
                int numEnt = rs.getInt(1);
                //Casteamos a string
                ePrecio = Integer.toString(numEnt);
            }else{
                ePrecio="";
            }
        
            // Cerramos todo antes de acabar
            rs.close();
            statement.close();
            
            return ePrecio;
       
        
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    
//Constructor principal de la ventana de compra de entradas
    public GUI_vistasesion_dependiente(String cine, String titulo, String fecha, String hora, String numsala1, Connection c) {
        initComponents(); // Inicializamos la vista       
        
        this.conexion=c;//Se guarda la conexion que se esta usando
        
        //Pasamos el valor del numero de sala recibido a int
        int numsala = Integer.parseInt(numsala1);
        //Inicializamos los cuadros de texto con los elementos recibidos
        panelPeli.setText(titulo);
        panelFecha.setText(fecha);
        panelHora.setText(hora);
        panelSala.setText(numsala1);
        panelCine.setText(cine);
        try {
            //Como la capacidad de la sala se busca en un query hay que controlar el caso en que no funcione
            capacidadsala.setText(buscarCap(numsala,cine));
        } catch (IOException ex) {
            Logger.getLogger(GUI_vistasesion_dependiente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Llenamos de valores el desplegable de número de entradas
        //En este caso se permite adquirir de cada vez 1-6 entradas
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) Numerodeentradas.getModel();
        model.removeAllElements();
        for (int i = 1; i <= 6; i++) {
            model.addElement(Integer.toString(i));
        }
        Numerodeentradas.setSelectedItem("1");//Por defecto marcamos que se escoge solo una entrada
        
        //El contenido de entradas libres se busca en la base
        //básicamente será el total de entradas disponibles menos el número de entradas ya compradas
        entradaslibres.setText(buscareLibres(cine,titulo,fecha,hora,numsala));
        precioentrada.setText(buscarPrecio(cine,titulo,fecha,hora,numsala));
        total.setText(precioentrada.getText());
        
        // Lo ponemos todo como read only
        panelPeli.setEditable(false);
        panelFecha.setEditable(false);
        panelHora.setEditable(false);
        panelSala.setEditable(false);
        panelCine.setEditable(false);
        capacidadsala.setEditable(false);
        entradaslibres.setEditable(false);
        precioentrada.setEditable(false);
        total.setEditable(false);
        
        //Otras propiedades de la ventana
        setTitle("Compra de entradas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
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
        botonvolver = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        panelCine = new javax.swing.JTextPane();

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

        botonvolver.setText("Volver");
        botonvolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonvolverActionPerformed(evt);
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

        jLabel13.setText("Cine");

        jScrollPane10.setViewportView(panelCine);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                        .addComponent(botonvolver, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(58, Short.MAX_VALUE))
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
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)
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
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                        .addGap(94, 94, 94))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Numerodeentradas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(botonvolver, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(75, 75, 75))
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
//Qué ocurre cuando se selecciona una opción del desplegable?
//Simplemente tenemos que actualizar el coste total (ctetotal=numentradas*precioporentrada)
    private void NumerodeentradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumerodeentradasActionPerformed
            // Obtiene el número de entradas seleccionado
            String selectedOptionString = (String) Numerodeentradas.getSelectedItem();
            //Variable en la que almacenaremos el numero de entradas que se quieren comprar como int
            int selectedOption=1;
            if (selectedOptionString != null && !selectedOptionString.isEmpty()) {
                selectedOption = Integer.parseInt(selectedOptionString);
            }
            // Obtiene el precio por entrada
            String precioentradastring= precioentrada.getText();
            //Variable en la que almacenaremos el valor del precio por entrada como int
            int precioint =1;
            if (precioentradastring!= null && !precioentradastring.isEmpty()) {
                precioint = Integer.parseInt(precioentradastring);
            }
            //Actualizamos el coste total (numentradas*precioporentrada)
            total.setText(Integer.toString(precioint * selectedOption));
    }//GEN-LAST:event_NumerodeentradasActionPerformed


//Cuando el usuario le da al botón de volver
//Simplemente vuelve a la pantalla anterior de cartelera
    private void botonvolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonvolverActionPerformed
        setVisible(false);
        dispose();
        this.getParent().setVisible(true);
    }//GEN-LAST:event_botonvolverActionPerformed

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
            java.util.logging.Logger.getLogger(GUI_vistasesion_dependiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_vistasesion_dependiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_vistasesion_dependiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_vistasesion_dependiente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI_vistasesion_dependiente dialog = new GUI_vistasesion_dependiente(new javax.swing.JFrame(), true,null);
                
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
    private javax.swing.JButton botonvolver;
    private javax.swing.JTextPane capacidadsala;
    private javax.swing.JTextPane entradaslibres;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane panelCine;
    private javax.swing.JTextPane panelFecha;
    private javax.swing.JTextPane panelHora;
    private javax.swing.JTextPane panelPeli;
    private javax.swing.JTextPane panelSala;
    private javax.swing.JTextPane precioentrada;
    private javax.swing.JTextPane total;
    // End of variables declaration//GEN-END:variables
}
