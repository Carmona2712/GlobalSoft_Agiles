/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionglobalfoodtrading.Controlador;

import aplicacionglobalfoodtrading.Modelo.Categoria;
import aplicacionglobalfoodtrading.Modelo.Cliente;
import aplicacionglobalfoodtrading.Modelo.Empleado_Directo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        String sql = "INSERT INTO `cliente`(`tipoid`, `identificacion`, `nombres`, `apellidos`, `pais`, `ciudad`, `provincia`, `cod_postal`,`Telefono`,`direccion`,`correo`) VALUES ('"+c.getTipoid()+"','"+c.getIdentificacion()+"','"+c.getNombre()+"','"+c.getApellido()+"','"+c.getPais()+"','"+c.getCiudad()+"','"+c.getProvincia()+"','"+c.getCodPostal()+"','"+c.getTelefono()+"','"+c.getDireccion()+"','"+c.getCorreo()+"')";
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
    
    
    
    public ArrayList<Cliente> ListadoClientes() {
        ArrayList<Cliente> Listac = new ArrayList();
        try {
            Conectar();
            String sql = "Select * from cliente order by apellidos";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setTipoid(rs.getString(1));
                c.setIdentificacion(rs.getString(2));
                c.setNombre(rs.getString(3));
                c.setApellido(rs.getString(4));
                c.setPais(rs.getString(5));
                c.setCiudad(rs.getString(6));
                c.setProvincia(rs.getString(7));
                c.setCodPostal(rs.getString(8));
                c.setDireccion(rs.getString(9));
                c.setCorreo(rs.getString(10));
                c.setTelefono(rs.getString(11));
                Listac.add(c);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + " en el Controlador Cliente", "Error", JOptionPane.ERROR_MESSAGE);
            // System.out.println(sql);
        }
        return Listac;
    }
    
    
    public Cliente ClientexID(String id){
        
        Cliente c = null;
        
        for(Cliente cl : this.ListadoClientes()){
            if(cl.getIdentificacion().equals(id)){
                c = cl;
            }
        }
        return c;
    }
    
    
     public int ActualizarCliente(String id, Cliente c) {
        String sql = "UPDATE `cliente` SET `tipoid`='"+c.getTipoid()+"',`nombres`='"+c.getNombre()+"',`apellidos`='"+c.getApellido()+"',`pais`='"+c.getPais()+"',`ciudad`='"+c.getCiudad()+"',`provincia`='"+c.getProvincia()+"',`cod_postal`='"+c.getCodPostal()+"',`direccion`='"+c.getDireccion()+"',`correo`='"+c.getCorreo()+"',`Telefono`='"+c.getTelefono()+"' WHERE identificacion = '"+c.getIdentificacion()+"'";

        //System.out.println(sql);
        Conectar();
        int exi = 0;
        try {
            st = con.createStatement();
            //System.out.println(sql2);
            // st.execute(sql);
            //exi = 1;
            if (st.executeUpdate(sql) > 0) {
                exi = 1;
            } else {
                exi = 0;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            exi = 0;
        }
        return exi;
    }
    
    
    public int EliminarCliente(String id) {
        int exito = 0;
        try {
            Conectar();
            st = con.createStatement();
            String sql = "delete from cliente where identificacion = '" + id + "'";
            //st.execute(sql);
            //exito = 1;
            if (st.executeUpdate(sql) > 0) {
                exito = 1;
            } else {
                exito = 0;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            exito = 0;
        }
        return exito;
    }
    
}
