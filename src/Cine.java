/**
 *
 * @author alumnogreibd
 */

import java.sql.*;
import java.io.FileInputStream;
import java.util.Properties;
import GUI.*;

public class Cine {

    public static void main(String[] args) {

        /*
        //Primero hay  que crear una base de datos en DBeaver llamada "Cine"
        //En la ventana de la derecha del Netbeans hace falta anhadir el driver, entonces
        // si no esta se hace click derecho en "Libraries", "Add Library", y se anhade la PostgreSQL

        //Se crea la conexion
        //Leemos la configuración de un archivo
        Connection c = null;
        Properties prop = new Properties();
        FileInputStream file_prop;
        try {
            //Abrimos el archivo
            file_prop = new FileInputStream("baseDatos.properties");
            prop.load(file_prop);
            file_prop.close();

            //Se hace la conexión
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:" + prop.getProperty("gestor") +
                                            "://" + prop.getProperty("servidor") + 
                                            ":" + prop.getProperty("puerto") +
                                            "/" + prop.getProperty("baseDatos"),
                                            prop.getProperty("usuario"), 
                                            prop.getProperty("clave"));

            System.out.println("Se abrió la conexión");
            System.out.println(prop);

            
            //Se crea un "statement" para comprobar que funciona
            //Este crea una tabla, y se puede eliminar desde el DBeaver
            Statement stmt = c.createStatement();
            String sql = "CREATE TABLE COMPANY " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " AGE            INT     NOT NULL, " +
                " ADDRESS        CHAR(50), " +
                " SALARY         REAL)";
            //Se le anhade el string al "statement" y se ejecuta. Este metodo solo funciona si no se devuelve nada
            stmt.executeUpdate(sql);

            System.out.println("Se creó la tabla");

            //Liberan recursos manualmente. Pero si no se escribiera esto se liberaria automaticamente mas tarde
            stmt.close();
            c.close();
            //Tuve el problema de que esto me "hizo invisible" la conexion en el DBeaver entre "postgres" y "Cine"
            //Para solucionarlo en en DBeaver se hace click derecho encima del usuario
            // "postgres", se hace click en "Editar Connection", en la pestaña que se abre en
            //la mitad izquierda se abre "PostgreSQL", y se marca la casilla de "Show all databases".
            

            //Si hay algun problema se imprime en la consola. Por ejemplo si se crea la tabla dos veces
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }*/
        
        
        //Se crea y abre la ventana de iniciar sesion
        GUI_IniciarSesion inicio= new GUI_IniciarSesion();
        inicio.setVisible(true);
        
        
    }
}
