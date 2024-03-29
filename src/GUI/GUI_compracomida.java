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


public class GUI_compracomida extends javax.swing.JDialog {
    
    private Connection conexion;

    public GUI_compracomida(java.awt.Frame parent, boolean modal, Connection c) {
        super(parent, modal);
        initComponents();
        
        this.conexion=c;
    }

    //Función que busca en la base de datos y devuelve el precio de la entrada
    private String buscarPrecio(String producto, String tamanho){
        //Se intenta la conexion
        Connection c = this.conexion;
        PreparedStatement statement = null;
        ResultSet rs = null;
    
        try {        
            // Query para obtener las entradas
            String query = "SELECT getPrecioComida(?, ?)";
            statement = c.prepareStatement(query);
            statement.setString(1, producto);
            statement.setString(2, tamanho);
            
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
    
    
//Constructor principal de la ventana de compra de comida
    public GUI_compracomida(String nombre, String tamanho, String precio, Connection c) {
        initComponents(); // Inicializamos la vista       
        
        this.conexion = c;//Se guarda la conexion que se esta usando
        int aux;
        
        preciounidad.setEditable(false);
        total.setEditable(false);
        //Inicializamos los cuadros de texto con los elementos recibidos
        if(nombre != null && tamanho != null){
            jBotonBuscarComida.setVisible(false);
            panelnombre.setText(nombre);
            paneltamanho.setText(tamanho);
            panelnombre.setEditable(false);
            paneltamanho.setEditable(false);
            
            preciounidad.setText(precio);    
            aux = 0;
  
            
        }else{
            jBotonBuscarComida.setVisible(true);
            panelnombre.setEditable(true);
            paneltamanho.setEditable(true);
            aux = 1;
           
            
            
        } 
        //Llenamos de valores el desplegable de número de productos
            //En este caso se permite adquirir de cada vez 1-6 productos
            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) Numerodeproductos.getModel();
            model.removeAllElements();
            for (int i = 1; i <= 6; i++) {
                model.addElement(Integer.toString(i));
            }
            Numerodeproductos.setSelectedItem("1");//Por defecto marcamos que se escoge solo un producto
            if(aux == 0){
                total.setText(preciounidad.getText());
            }

            
            //Otras propiedades de la ventana
            setTitle("Compra de articulos de comida");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);
    }
   
    private void buscar(String nombre, String tamanho){
        
        //Llenamos de valores el desplegable de número de productos
            //En este caso se permite adquirir de cada vez 1-6 productos
            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) Numerodeproductos.getModel();
            model.removeAllElements();
            for (int i = 1; i <= 6; i++) {
                model.addElement(Integer.toString(i));
            }    
        
            preciounidad.setText(buscarPrecio(nombre, tamanho));

            total.setText(preciounidad.getText());
            // Lo ponemos todo como read only (excepto si no nos pasan argumentos)
            preciounidad.setEditable(false);
            total.setEditable(false);
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
        panelnombre = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        paneltamanho = new javax.swing.JTextPane();
        Numerodeproductos = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        preciounidad = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        total = new javax.swing.JTextPane();
        finalizarcompra = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jBotonBuscarComida = new javax.swing.JButton();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane7.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/logo.png"))); // NOI18N
        jLabel5.setAlignmentX(0.5F);
        jLabel5.setAlignmentY(0.0F);
        jLabel5.setMinimumSize(new java.awt.Dimension(71, 63));
        jLabel5.setPreferredSize(new java.awt.Dimension(80, 80));

        panelnombre.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        panelnombre.setForeground(new java.awt.Color(153, 0, 0));
        jScrollPane1.setViewportView(panelnombre);

        jScrollPane3.setViewportView(paneltamanho);

        Numerodeproductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        Numerodeproductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NumerodeproductosActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(153, 0, 0));
        jLabel3.setText("Precio total");

        jLabel4.setText("Precio por unidad");

        jScrollPane8.setViewportView(preciounidad);

        jScrollPane9.setViewportView(total);

        finalizarcompra.setText("Finalizar compra");
        finalizarcompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finalizarcompraActionPerformed(evt);
            }
        });

        jLabel6.setText("Número de productos");

        jLabel7.setText("Nombre producto");

        jLabel8.setText("Tamaño");

        jLabel11.setText("€");

        jLabel12.setText("€");

        jBotonBuscarComida.setText("Buscar articulo");
        jBotonBuscarComida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonBuscarComidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(finalizarcompra, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(54, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Numerodeproductos, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel11)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(136, 136, 136)
                                        .addComponent(jBotonBuscarComida, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(108, 108, 108))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Numerodeproductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(finalizarcompra))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jBotonBuscarComida)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(81, 81, 81))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//Qué ocurre cuando se selecciona una opción del desplegable?
//Simplemente tenemos que actualizar el coste total (ctetotal=numentradas*precioporentrada)
    private void NumerodeproductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NumerodeproductosActionPerformed
            // Obtiene el número de entradas seleccionado
            String selectedOptionString = (String) Numerodeproductos.getSelectedItem();
            //Variable en la que almacenaremos el numero de entradas que se quiere comprar como int
            int selectedOption = 1;
            if (selectedOptionString != null && !selectedOptionString.isEmpty()) {
                selectedOption = Integer.parseInt(selectedOptionString);
            }
            // Obtiene el precio por entrada de la sesión
            String precioentradastring = preciounidad.getText();
            //Variable en la que almacenaremos dicho precio como int
            double precioint =1;
            if (precioentradastring!= null && !precioentradastring.isEmpty()) {
                precioint = Double.parseDouble(precioentradastring); 
            }
            //Actualizamos el coste total (numentradas*precioporentrada)
            total.setText(Double.toString(precioint * selectedOption));
    }//GEN-LAST:event_NumerodeproductosActionPerformed

//Función que se encarga de guardar la compra en la base de datos, actualizando las tablas pertinentes
public void actualizarCompras(String producto, int numproductos, String cantidad, double coste) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
    //Se intenta la conexion
    Connection c = this.conexion;
    PreparedStatement statement_entradas = null;
    PreparedStatement newidventa= null;
    ResultSet rs = null;
    ResultSet rs2 = null;
    
    try {
        //Almacenaremos su id de producto para luego insertarlos en una nueva línea de producto        
        String queryentradas = "select get_available_entriesComida(?, ?, ?)";

        try{
            //Este statement es un poco distinto, ya que lo vamos a recorrer dos veces
            //La primera para controlar que efectivamente hay un número de entradas disponibles suficientes
            //Y la segunda para oficializar la compra
            //Introducimos estos flags para poder recorrer en ambas direcciones el result set
            statement_entradas = c.prepareStatement(queryentradas,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            statement_entradas.setString(1, producto);
            statement_entradas.setString(2, cantidad);
            statement_entradas.setInt(3,numproductos);
            
            
        }catch (SQLException e) {

            System.err.println("Error al encontrar articulos de comida: " + e.getMessage());
            c.rollback(); //Descartamos los cambios si hubo algún error
        }

        //Guardamos el resultado en resultSet
        rs = statement_entradas.executeQuery();
        int newidproducto=0;
        if (rs.next()) {
                        newidproducto=rs.getInt(1); //La id del producto que se va a procesar 
                }    
        
        //volvemos a recorrer el result set para oficializar la compra
        //Actualizando las tablas pertinentes
                
        rs.beforeFirst(); // Reseteamos el puntero del result set a la primera posición para volver a recorrerlo
        try {

            //antes de nada tenemos que generar una nueva linea para esta venta
            //No confundir con la id_venta, que es diferente para cada inserción. Aquí estamos generando una nueva num_linea,
            //Para poder asociar todas las entradas a la misma compra
            //Primero buscamos el valor numéricamente más grande que haya (porque son secuenciales)                
            //String last_lp = "select get_last_lp()";
            //Guardamos en variables algunos datos que necesitaremos pasar a las funciones
            String correoUsuario=this.conexion.getMetaData().getUserName(); //El correo (identificador) del usuario que compra
            String precio1=preciounidad.getText(); //Coste de cada elemento de la compra
            double precioint = Double.parseDouble(precio1);
            int numprods = Integer.parseInt((String) Numerodeproductos.getSelectedItem());
            
            String query="select guardar_idVenta(?,?,?);";
            newidventa=c.prepareStatement(query);
            newidventa.setString(1, correoUsuario); //correo del usuario
            newidventa.setInt(2, numprods); //num. de prods que se compran
            newidventa.setDouble(3, precioint); //precio por prod
            
            rs2 = newidventa.executeQuery();
             
            int newidVenta = 0; //La función sql devuelve la nueva idVenta de esta compra. La guardaremos en esta variable
                if (rs2.next()) {
                        newidVenta = rs2.getInt(1); // get the integer value returned by the stored procedure
                }
            //En la siguiente variable almacenaremos la nueva id de num_linea
            //Todas las entradas que se inserten en la base en el bucle siguiente
            //Como de cada compra solo podemos comprar un tipo de producto stackeable
            //Solamente vamos a necesitar 1 linea de cada vez
            int newnumlinea=1;
            /*if (rs2.next()) { // Aseguramos que haya un resultado en el result set antes de intentar obtenerlo
*/
            //Van a tener la misma 
            int new_last_lp_s=0;
            if (rs2.next()) { // Aseguramos que haya un resultado en el result set antes de intentar obtenerlo
                new_last_lp_s = rs2.getInt(1);     //Obtenemos la máxima
                new_last_lp_s = new_last_lp_s + 1; //creamos la nueva. Más tarde se insertará en la tabla de lineas de venta
            }
            c.setAutoCommit(false); //Por si falla, podremos recuperar la base a su versión previa

            //Recorremos el bucle del result set
            //Se procesará una entrada por iteración hasta que se guarden las N entradas que quiere el usuario
            while (rs.next()) {                
                //Se obtienen las variables necesarias
                //int newidproducto=rs.getInt(1); //La id de la entrada que se va a procesar 
                //String correoUsuario=this.conexion.getMetaData().getUserName(); //El correo (identificador) del usuario que compra

                //Se ejecuta la funcion con estos parametros
                //Esta va a insertar en las tablas pertinentes para oficializar en la base la nueva compra
                PreparedStatement guardarCompra=this.conexion.prepareStatement(
                        "select guardar_compra(?,?,1,?)");
                guardarCompra.setInt(1, newidVenta); //id de la venta
                guardarCompra.setInt(2, newidproducto); //ID del producto
                guardarCompra.setInt(3,numproductos);              //Numero de elementos (la comida es stackeable)
                
                guardarCompra.execute();                //Ejecutamos el query                
            }
        } catch (SQLException e) {
                        
            System.err.println("Error al obtener del id_producto: " + e.getMessage());
            c.rollback(); 

            c.setAutoCommit(true);  //Rehabilitamos el autocommit una vez sabemos que todo fue bien
        }
            
        c.setAutoCommit(true);  //Rehabilitamos el autocommit una vez sabemos que todo fue bien
        // Cerramos todo antes de acabar
        rs.close();
        rs2.close();

        statement_entradas.close();
    } catch (SQLException ex) {
            Logger.getLogger(GUI_compracomida.class.getName()).log(Level.SEVERE, null, ex);
    }
}
        

//Cuando el usuario le da al botón de finalizar compra
//Se obtiene los datos de la compra, se insertan en la base de datos
//Se cierra la ventana actual y se vuelve a la anterior (GUI_MenuCliente)

    private void finalizarcompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finalizarcompraActionPerformed
        
        try {
                //Obtenemos los valores de comprobación
                int numeproductos = Integer.parseInt((String) Numerodeproductos.getSelectedItem());
                //Obtenemos los datos de la entrada necesarios para finalizar la compra
                String tamanho=paneltamanho.getText();
                String producto =panelnombre.getText();
                double coste = Double.parseDouble(preciounidad.getText());
                
                try {
                    //Número correcto. Guardamos la compra
                    actualizarCompras(producto, numeproductos, tamanho, coste);
                } catch (IOException | ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(GUI_compracomida.class.getName()).log(Level.SEVERE, null, ex);
                }
                setVisible(false);
                //dispose();
                //this.getParent().getParent().setVisible(true);
                //((JFrame)this.getParent().getParent()).setState(Frame.NORMAL);
                this.dispose();
                //this.getParent().getParent().setVisible(true);
                
                //Escondemos la ventana actual
            this.setVisible(false);
                
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(GUI_compracomida.this, "Error: Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_finalizarcompraActionPerformed

    private void jBotonBuscarComidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonBuscarComidaActionPerformed
        // TODO add your handling code here:
        String nombre = panelnombre.getText();
        String tamanho = paneltamanho.getText();
        buscar(nombre, tamanho);
        
    }//GEN-LAST:event_jBotonBuscarComidaActionPerformed

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
            java.util.logging.Logger.getLogger(GUI_compracomida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_compracomida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_compracomida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_compracomida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                GUI_compracomida dialog = new GUI_compracomida(new javax.swing.JFrame(), true,null);
                
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
    private javax.swing.JComboBox<String> Numerodeproductos;
    private javax.swing.JButton finalizarcompra;
    private javax.swing.JButton jBotonBuscarComida;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane panelnombre;
    private javax.swing.JTextPane paneltamanho;
    private javax.swing.JTextPane preciounidad;
    private javax.swing.JTextPane total;
    // End of variables declaration//GEN-END:variables
}
