/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionglobalfoodtrading.Controlador;

import aplicacionglobalfoodtrading.Modelo.Cliente;
import aplicacionglobalfoodtrading.Modelo.Pago_Empleado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Controlador_Pago_Empleado {
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
    
   
     public Controlador_Pago_Empleado() {
    }
    
    
    
    public void Conectar() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_gft", "root", "");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar BD");
        }
    }

    public int RegistrarPagoEmpleado(Pago_Empleado pe) {
        int exi = 0;
        String sql = "INSERT INTO `pago_empleado`(`codigo_pago`, `fk_empleado`, `fecha_pago`, `mes_pagado`, `horas_extras`, `horas_nocturnas`, `recargo_adicional`, `notas`, `sueldo_base`, `total_recargos`,`Total_Pago`) VALUES ('"+pe.getCodigo_Pago()+"','"+pe.getFk_Empleado()+"','"+pe.getFecha_Pago()+"','"+pe.getMes_pagado()+"',"+pe.getHoras_Extras()+","+pe.getHoras_Nocturnas()+","+pe.getRecargo_Adicional()+",'"+pe.getNotas()+"',"+pe.getSueldo_base()+","+pe.getTotal_recargo()+","+pe.getTotal_Pago()+")";
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
            JOptionPane.showMessageDialog(null, ex.getMessage() + " en el Controlador Pago_Empleado", "Error", JOptionPane.ERROR_MESSAGE);
            // System.out.println(sql);
            exi = 0;
        }
        
        return exi;
        
    }
    
    public ArrayList<Pago_Empleado> ListadoPagoEmpleados() {
        ArrayList<Pago_Empleado> ListaPE = new ArrayList();
        try {
            Conectar();
            String sql = "Select * from `pago_empleado` order by fecha_pago";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
               Pago_Empleado pe = new Pago_Empleado();
               pe.setCodigo_Pago(rs.getString(1));
               pe.setFk_Empleado(rs.getString(2));
               pe.setFecha_Pago(rs.getString(3));
               pe.setMes_pagado(rs.getString(4));
               pe.setHoras_Extras(rs.getInt(5));
               pe.setHoras_Nocturnas(rs.getInt(6));
               pe.setRecargo_Adicional(rs.getFloat(7));
               pe.setNotas(rs.getString(8));
               pe.setSueldo_base(rs.getFloat(9));
               pe.setTotal_recargo(rs.getFloat(10));
               pe.setTotal_Pago(rs.getFloat(11));
                ListaPE.add(pe);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + " en el Controlador Pago Empleado", "Error", JOptionPane.ERROR_MESSAGE);
            // System.out.println(sql);
        }
        return ListaPE;
    }
    
    public Pago_Empleado PagoXCodigo(String cod){
        
        Pago_Empleado pe = null;
        
        for(Pago_Empleado p : this.ListadoPagoEmpleados()){
                if(p.getCodigo_Pago().equals(cod)){
                    pe = p;
                }
        }
        return pe;
    }
    
  
}
