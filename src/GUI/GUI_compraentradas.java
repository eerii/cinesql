/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;


public class GUI_compraentradas extends javax.swing.JDialog {
    
    private Connection conexion;

    public GUI_compraentradas(java.awt.Frame parent, boolean modal, Connection c) {
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
        
            // Query para obtener el número disponible de butacas
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
            // Query para obtener las entradas
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
    public GUI_compraentradas(String cine, String titulo, String fecha, String hora, String numsala1, Connection c) {
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
            Logger.getLogger(GUI_compraentradas.class.getName()).log(Level.SEVERE, null, ex);
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
                            .addComponent(jScrollPane3)
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
        //Escondemos la ventana actual
        this.setVisible(false);
        
        //Creamos una nueva instancia de la ventana de compra de comida
        //Le pasamos al constructor los datos que necesitaremos dentro de ella
        GUI_compracomida compraComida = new GUI_compracomida(null, null, null, this.conexion);
        compraComida.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        compraComida.setVisible(true);

        // Add a WindowListener to the GUI_compraentradas window
        compraComida.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
            // Show the previous window again when the GUI_compraentradas window is closed
            setVisible(true);
        }
    });
    }//GEN-LAST:event_compracomidaActionPerformed
//Qué ocurre cuando se selecciona una opción del desplegable?
//Simplemente tenemos que actualizar el coste total (ctetotal=numentradas*precioporentrada)
    private void NumerodeentradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumerodeentradasActionPerformed
            // Obtiene el número de entradas seleccionado
            String selectedOptionString = (String) Numerodeentradas.getSelectedItem();
            //Variable en la que almacenaremos el numero de entradas que se quiere comprar como int
            int selectedOption=1;
            if (selectedOptionString != null && !selectedOptionString.isEmpty()) {
                selectedOption = Integer.parseInt(selectedOptionString);
            }
            // Obtiene el precio por entrada de la sesión
            String precioentradastring= precioentrada.getText();
            //Variable en la que almacenaremos dicho precio como int
            int precioint =1;
            if (precioentradastring!= null && !precioentradastring.isEmpty()) {
                precioint = Integer.parseInt(precioentradastring);
            }
            //Actualizamos el coste total (numentradas*precioporentrada)
            total.setText(Integer.toString(precioint * selectedOption));
    }//GEN-LAST:event_NumerodeentradasActionPerformed

//Función que se encarga de guardar la compra en la base de datos, actualizando las tablas pertinentes
public void actualizarCompras(int numentradas, String cine, String fecha, String hora, int sala, String titulo, int coste) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
    //Se intenta la conexion
        Connection c = this.conexion;
        PreparedStatement statement_entradas = null;
        PreparedStatement last_lp_s= null;
        ResultSet rs = null;
        ResultSet rs2 = null;
    
       
        //En primer lugar se seleccionan las entradas que se van a asociar al usuario en la compra
        //El query va a devolver las primeras N entradas que encuentre en la base, siendo N el número de entradas
        //Que quiere comprar el usuario
        //Almacenaremos su id de producto para luego insertarlos en una nueva línea de producto        
        String queryentradas="select get_available_entries(?, ?, ?, ?, ?, ?)";
        try{
            //Este statement es un poco distinto, ya que lo vamos a recorrer dos veces
            //La primera para controlar que efectivamente hay un número de entradas disponibles suficientes
            //Y la segunda para oficializar la compra
            //Introducimos estos flags para poder recorrer en ambas direcciones el result set
            statement_entradas = c.prepareStatement(queryentradas,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        
            statement_entradas.setString(1,cine);
            statement_entradas.setString(2, fecha);
            statement_entradas.setString(3, hora);
            statement_entradas.setInt(4, sala);
            statement_entradas.setString(5, titulo);
            statement_entradas.setInt(6,numentradas);
        }catch (SQLException e) {
            
            System.err.println("Error al encontrar entradas: " + e.getMessage());
            c.rollback(); //Descartamos los cambios si hubo algún error
        }
            
        //Guardamos el resultado en resultSet
        rs = statement_entradas.executeQuery();
            
        int numencontrado=0;    //Valor con el que comprobaremos si se encontraron suficientes entradas
        try {
            while (rs.next()) {
            //Vamos incrementando
            numencontrado++;
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions that may occur during ResultSet iteration
            System.err.println("Error al iterar el ResultSet: " + e.getMessage());
        }
           
           //Si hay entradas suficientes volvemos a recorrer el result set para oficializar la compra
           //Actualizando las tablas pertinentes
           if (numencontrado == numentradas) {
                
                rs.beforeFirst(); // Reseteamos el puntero del result set a la primera posición para volver a recorrerlo
               

                //antes de nada tenemos que generar una nueva linea para esta venta
                //No confundir con la id_venta, que es diferente para cada inserción. Aquí estamos generando una nueva num_linea,
                //Para poder asociar todas las entradas a la misma compra
                //Primero buscamos el valor numéricamente más grande que haya (porque son secuenciales)                
                //String last_lp="select get_last_lp()";
                //last_lp_s=c.prepareStatement(last_lp);
                //rs2 = last_lp_s.executeQuery();
                
                //En la siguiente variable almacenaremos la nueva id de num_linea
                //Todas las entradas que se inserten en la base en el bucle siguiente
                //Van a tener la misma 
                //int new_last_lp_s=0;
                //if (rs2.next()) { // Aseguramos que haya un resultado en el result set antes de intentar obtenerlo
                //    new_last_lp_s = rs2.getInt(1);     //Obtenemos la máxima
                //    new_last_lp_s = new_last_lp_s + 1; //creamos la nueva. Más tarde se insertará en la tabla de lineas de venta
                //}
                c.setAutoCommit(false); //Por si falla, podremos recuperar la base a su versión previa
                
                
                //Se procesará una entrada por iteración hasta que se guarden las N entradas que quiere el usuario
                String correoUsuario=this.conexion.getMetaData().getUserName(); //El correo (identificador) del usuario que compra
                 // Obtiene el precio por entrada de la sesión
                String precioentradastring= precioentrada.getText();
                //Variable en la que almacenaremos dicho precio como int
                int precioint =1;
                    if (precioentradastring!= null && !precioentradastring.isEmpty()) {
                    precioint = Integer.parseInt(precioentradastring);
                }
                
                PreparedStatement guardaridVenta=this.conexion.prepareStatement(
                        "select guardar_idVenta(?,?,?);");
                guardaridVenta.setString(1, correoUsuario); //correo del usuario
                guardaridVenta.setInt(2, numentradas); //num. de entradas que se compran
                guardaridVenta.setDouble(3, precioint); //precio por entrada
                ResultSet newidven = guardaridVenta.executeQuery();                //Ejecutamos el query                
                int newidVenta = 0; //Variable en la que guardaremos la id de venta 
                if (newidven.next()) {
                        newidVenta = newidven.getInt(1); // get the integer value returned by the stored procedure
                }
                
                int newnumlinea=0;
                while (rs.next()) {
                newnumlinea=newnumlinea+1;
                //Se obtienen las variables necesarias
                int newidproducto=rs.getInt(1); //La id de la entrada que se va a procesar 
                
                
                //Se ejecuta la funcion con estos parametros
                //Esta va a insertar en las tablas pertinentes para oficializar en la base la nueva compra
                PreparedStatement guardarCompra=this.conexion.prepareStatement(
                        "select guardar_compra(?,?,?,?)");
                guardarCompra.setInt(1, newidVenta); //numero de linea
                guardarCompra.setInt(2, newidproducto); //ID del producto
                guardarCompra.setInt(3, newnumlinea);       //Coste del producto individual
                guardarCompra.setInt(4,1);              //Como las entradas no son stackeables, el atributo cantidad valdrá 1
                guardarCompra.execute();                //Ejecutamos el query                
                }
                    } 
            
        c.setAutoCommit(true);  //Rehabilitamos el autocommit una vez sabemos que todo fue bien
           // Cerramos todo antes de acabar
                        rs.close();
                       

                        statement_entradas.close();
         } 

        

//Cuando el usuario le da al botón de finalizar compra
//Se obtiene los datos de la compra, se insertan en la base de datos
//Se cierra la ventana actual y se vuelve a la anterior (GUI_MenuCliente)
    private void finalizarcompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarcompraActionPerformed
        //Tenemos que controlar que el numero de entradas elegidas no sobrepasa al de entradas disponibles
        try {
                //Obtenemos los valores de comprobación
                int numentradas = Integer.parseInt((String) Numerodeentradas.getSelectedItem());
                int entradasdisponibles = Integer.parseInt(entradaslibres.getText());
                //Obtenemos los datos de la entrada necesarios para finalizar la compra
                String cine=panelCine.getText();
                String fecha=panelFecha.getText();
                String hora=panelHora.getText();
                int sala=Integer.parseInt(panelSala.getText());
                String titulo=panelPeli.getText();
                int coste=Integer.parseInt(precioentrada.getText());
                if (numentradas> entradasdisponibles) { //Numero incorrecto. Se gestiona el error
                    JOptionPane.showMessageDialog(GUI_compraentradas.this, "Error: Se seleccionaron más entradas de las disponibles", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{      try {
                    //Número correcto. Guardamos la compra
                    actualizarCompras(numentradas,cine,fecha,hora,sala,titulo,coste);
                    } catch (IOException | ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(GUI_compraentradas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //Actualizamos el número de butacas libres y volvemos a la ventana principal
                    int newedisponibles = entradasdisponibles - numentradas;
                    entradaslibres.setText(Integer.toString(newedisponibles));
                    setVisible(false);
                    dispose();
                    this.getParent().setVisible(true);
                }
            } catch (NumberFormatException ex) {
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
                GUI_compraentradas dialog = new GUI_compraentradas(new javax.swing.JFrame(), true,null);
                
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
