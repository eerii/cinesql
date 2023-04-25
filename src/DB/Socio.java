package DB;

import java.sql.Date;
import java.sql.PreparedStatement;

public class Socio {
    String nombre, apellido1, apellido2, dni, correo, telefono;
    char[] clave;
    Date fecha_nacimiento;

    public Socio(String nombre, String apellido1, String apellido2, String dni, String correo, String telefono,
            String d, String m, String y, char[] clave) {
        if (nombre.isEmpty() || apellido1.isEmpty() || apellido2.isEmpty() || dni.isEmpty() || correo.isEmpty()) {
            throw new IllegalArgumentException("Hay campos obligatorios sin cubrir");
        }
        if (clave.length < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }

        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.dni = dni;
        this.correo = correo;
        this.telefono = telefono;
        this.clave = clave;

        if (!d.isEmpty() && !m.isEmpty() && !y.isEmpty()) {
            this.fecha_nacimiento = Date.valueOf(y + "-" + m + "-" + d);
        } else {
            this.fecha_nacimiento = Date.valueOf("0001-01-01");
        }
    }

    public PreparedStatement crearConsulta(BaseDatos bd) throws Exception {
        PreparedStatement ps = null;
        
        ps = bd.getConnection().prepareStatement("SELECT registrar_socio(?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setString(1, nombre);
        ps.setString(2, apellido1);
        ps.setString(3, apellido2);
        ps.setString(4, dni);
        ps.setString(5, correo);
        ps.setString(6, telefono);
        ps.setDate(7, fecha_nacimiento);
        ps.setString(8, new String(clave));

        return ps;
    }
}
