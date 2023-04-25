package DB;

import java.io.FileInputStream;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDatos {
    private Connection c;

    public BaseDatos() throws Exception {
        conectar("", null);
    }

    public BaseDatos(String user, char[] clave) throws Exception {
        conectar(user, clave);
    }

    public void conectar(String user, char[] clave) throws Exception {
        //Se intenta la conexion
        Properties prop = new Properties();
        FileInputStream file_prop;

        //Abrimos el archivo
        file_prop = new FileInputStream("baseDatos.properties");
        prop.load(file_prop);
        file_prop.close();

        //Se hace la conexión
        String conn = "jdbc:" + prop.getProperty("gestor") +
            "://" + prop.getProperty("servidor") +
            ":" + prop.getProperty("puerto") +
            "/" + prop.getProperty("baseDatos");
        Class.forName("org.postgresql.Driver");
        
        if (user.isEmpty())
            c = DriverManager.getConnection(conn, prop.getProperty("user"), prop.getProperty("password"));
        else
            c = DriverManager.getConnection(conn, user, new String(clave));
    }

    public Connection getConnection() {
        return c;
    }
}