/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionglobalfoodtrading.Controlador;

import aplicacionglobalfoodtrading.Modelo.Bodega;
import aplicacionglobalfoodtrading.Modelo.Giro_Negocio;
import aplicacionglobalfoodtrading.Modelo.Info_Emp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Controlador_Info_Emp {
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

    public Controlador_Info_Emp() {
    }
    
    
    public void Conectar() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_gft", "root", "");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar BD");
        }
    }
    
    public int ModificarInformacion(Info_Emp ie){
        int exi;
         String sql = "UPDATE `informacion_empresa` SET `NIT`='"+ie.getNIT()+"',`NOMBRE`='"+ie.getNombre()+"',`DIRECCION`='"+ie.getDireccion()+"',`REG_EMP`='"+ie.getReg_Empresa()+"',`GIRO_NEG`='"+ie.getGiro_negocio()+"',`TELEFONO`='"+ie.getTelefono()+"' WHERE indice =  1";
        try {
            Conectar();
            //System.out.println(sql);
            st = con.createStatement();
            
            if(st.executeUpdate(sql)>0){
                exi = 1;
            }else{
                exi = 0;
            }
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + " en el Controlador _ bodega", "Error", JOptionPane.ERROR_MESSAGE);
            // System.out.println(sql);
            exi = 0;
        }
        return exi;
    }
    
     public ArrayList<Info_Emp> ListadoInformacion() {
        ArrayList<Info_Emp> Listai = new ArrayList();
        try {
            Conectar();
            String sql = "SELECT `indice`, `NIT`, `NOMBRE`, `DIRECCION`, `REG_EMP`, `GIRO_NEG`, `TELEFONO` FROM `informacion_empresa` WHERE `indice` = 1";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Info_Emp i = new Info_Emp();
                i.setNIT(rs.getString(2));
                i.setNombre(rs.getString(3));
                i.setDireccion(rs.getString(4));
                i.setReg_Empresa(rs.getString(5));
                i.setGiro_negocio(rs.getString(6));
                i.setTelefono(rs.getString(7));
                Listai.add(i);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + " en el Controlador _ Informacion Empresa", "Error", JOptionPane.ERROR_MESSAGE);
            // System.out.println(sql);
        }
        return Listai;
    }
    
    public Info_Emp RetornarInfor_Empresa(){
        Info_Emp i = new Info_Emp();
        i = this.ListadoInformacion().get(0);
        return i;
    }
    
    
    
    
}
