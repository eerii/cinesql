package DB;

import java.io.FileInputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDatos {
    private static BaseDatos instance = null;
    private Connection c;

    private BaseDatos() {

    }

    public static BaseDatos getInstance() throws Exception {
        if (instance == null) {
            instance = new BaseDatos();
            instance.conectar();
        }
        return instance;
    }
    
    public void conectar() throws Exception {
        //Se intenta la conexion
        Properties prop = new Properties();
        FileInputStream file_prop;

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
            prop.getProperty("user"),
            prop.getProperty("password"));
    }
    
    public Connection getConnection() {
        return c;
    }
}