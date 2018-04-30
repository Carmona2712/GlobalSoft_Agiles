/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionglobalfoodtrading.Controlador;

import aplicacionglobalfoodtrading.Modelo.Categoria;
import aplicacionglobalfoodtrading.Modelo.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo Carmona
 */
public class ControladorCliente {
     private static Connection con;
    private static ResultSet rs;
    private static Statement st;

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection aCon) {
        con = aCon;
    }

    public static ResultSet getRs() {
        return rs;
    }

    public static void setRs(ResultSet aRs) {
        rs = aRs;
    }

    public static Statement getSt() {
        return st;
    }

    public static void setSt(Statement aSt) {
        st = aSt;
    }

    public ControladorCliente() {
    }
    
    
    public void Conectar() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_gft", "root", "");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar BD");
        }
    }
    
    
    public int RegistrarCliente(Cliente c) {
        int exi = 0;
        String sql = "INSERT INTO `cliente`(`tipoid`, `identificacion`, `nombres`, `apellidos`, `pais`, `ciudad`, `provincia`, `cod_postal`, `direccion`) VALUES ('"+c.getTipoid()+"','"+c.getIdentificacion()+"','"+c.getNombre()+"','"+c.getApellido()+"','"+c.getPais()+"','"+c.getCiudad()+"','"+c.getProvincia()+"','"+c.getCodPostal()+"')";
        try {
            Conectar();
            //System.out.println(sql);
            System.out.println(sql);
            st = con.createStatement();
            
            if(st.executeUpdate(sql)>0){
                exi = 1;
            }else{
                exi = 0;
            }
 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + " en el Controlador Cliente", "Error", JOptionPane.ERROR_MESSAGE);
            // System.out.println(sql);
            exi = 0;
        }
        
        return exi;
        
    }
    
    
}
