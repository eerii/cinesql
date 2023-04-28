/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import java.sql.PreparedStatement;

/**
 *
 * @author alumnogreibd
 */
public class Cliente {
    // Correo do cliente
    String correo;
    
    public Cliente(String email) {
        
        if ( email.isEmpty() ) {
            throw new IllegalArgumentException("Hay campos obligatorios sin cubrir");
        }
        
        this.correo = email;
        
    }
    
    public String getCorreo() {
        return this.correo;
    }
    
    public PreparedStatement obtenerComida(BaseDatos bd, Cliente cliente) throws Exception {
        PreparedStatement ps = null;
        
        ps = bd.getConnection().prepareStatement("SELECT comida_comprada(?)");
        ps.setString(1, cliente.correo);
        
        return ps;
    }
    
    public PreparedStatement obtenerEntrada(BaseDatos bd, Cliente cliente) throws Exception {
        PreparedStatement ps = null;
        
        ps = bd.getConnection().prepareStatement("SELECT entrada_comprada(?)");
        ps.setString(1, cliente.correo);

        return ps;
    }
    
}
