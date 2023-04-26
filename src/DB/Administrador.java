package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Administrador {
    private BaseDatos bd;

    public Administrador() throws Exception {
        this.bd = new BaseDatos();
    }

    public ResultSet obtenerCines() throws SQLException {
        PreparedStatement query = this.bd.getConnection().prepareStatement("SELECT nombre, ciudad, id FROM cine");
        return query.executeQuery();
    }

    public ResultSet obtenerTrabajadores(int cine) throws SQLException {
        PreparedStatement query = this.bd.getConnection().prepareStatement("SELECT "
                + "nombre, apellido1, apellido2, correo_electronico, "
                + "experiencia, telefono "
                + "FROM trabajador t, trabajar t2 "
                + "WHERE t.id = t2.id_trabajador and t2.id_cine = ?");

        query.setInt(1, cine);
        return query.executeQuery();
    }

    public ResultSet obtenerDependientes(int cine) throws SQLException {
        PreparedStatement query = this.bd.getConnection().prepareStatement("SELECT nombre,"
                + " apellido1, apellido2, correo_electronico, experiencia, d.idiomas, telefono "
                + "FROM trabajador t, trabajar t2, dependiente d "
                + "WHERE t.id = t2.id_trabajador and t2.id_cine = ? "
                + "and t.id =d.id ");

        query.setInt(1, cine);
        return query.executeQuery();
    }

    public void nuevoTrabajador(String nombre, String apellido1, String apellido2, String correo, String dni,
            String telefono, char[] clave, Integer numIdiomas, Boolean experiencia, Boolean esDependiente)
            throws Exception {
        // Se comprueba si algun campo importante es null
        if (nombre.isEmpty() || apellido1.isEmpty() || correo.isEmpty() || clave.length == 0) {
            throw new Exception("Hay campos importantes sin cumplir.");
        }

        Connection c = this.bd.getConnection();

        // Se le asigna un id
        PreparedStatement query_id = c.prepareStatement("SELECT max(id) FROM trabajador");
        ResultSet maxId = query_id.executeQuery();
        maxId.next();
        Integer id = maxId.getInt(1);
        id++;

        // Se insertan los valores
        PreparedStatement query = c.prepareStatement("INSERT INTO trabajador values(?,?,?,?,?,?,?,?,?)");

        query.setInt(1, id);
        query.setString(2, dni);
        query.setString(3, nombre);
        query.setString(4, apellido1);
        query.setString(5, apellido2);
        query.setString(6, telefono);
        query.setString(7, correo);
        query.setBoolean(8, experiencia);
        query.setString(9, new String(clave));

        query.execute();

        // Si tenemos un dependiente, tambien se inserta en Dependiente
        if (esDependiente) {
            PreparedStatement query_dep = c.prepareStatement("INSERT INTO dependiente values(?,?)");

            query_dep.setInt(1, id);
            query_dep.setInt(2, numIdiomas);

            query_dep.execute();
        }

        // Se le inserta en la tabla de usuarios
        PreparedStatement query_usr = c.prepareStatement("INSERT INTO usuarios VALUES(?,?)");
        query_usr.setString(1, correo);
        query_usr.setString(2, "Dependiente");
        query_usr.execute();

        // Se le crea una conexion
        String sql = "CREATE USER \"" + correo + "\" IN ROLE Dependiente PASSWORD '" + new String(clave) + "'";
        PreparedStatement query_pg = c.prepareStatement(sql);
        query_pg.execute();
    }
}
