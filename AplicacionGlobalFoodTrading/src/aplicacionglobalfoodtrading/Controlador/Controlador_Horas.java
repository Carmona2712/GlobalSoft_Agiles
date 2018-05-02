/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionglobalfoodtrading.Controlador;

import aplicacionglobalfoodtrading.Modelo.Cliente;
import aplicacionglobalfoodtrading.Modelo.Hora_Extra;
import aplicacionglobalfoodtrading.Modelo.Pago_Empleado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Controlador_Horas {
    
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

    public Controlador_Horas() {
    }
    
     public void Conectar() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_gft", "root", "");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar BD");
        }
    }
     
     public ArrayList<Hora_Extra> ListadoHoras_Extras() {
        ArrayList<Hora_Extra> ListaH = new ArrayList();
        try {
            Conectar();
            String sql = "SELECT `indice`, `valor` FROM `valor_hora`";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
              Hora_Extra h = new Hora_Extra();
              h.setIndice(rs.getInt(1));
              h.setValor(rs.getFloat(2));
                ListaH.add(h);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + " en el Controlador Horas Extras", "Error", JOptionPane.ERROR_MESSAGE);
            // System.out.println(sql);
        }
        return ListaH;
    }
     
     public Hora_Extra REtornarHoraDiurna(){
         
         Hora_Extra h = null;
         
         for(Hora_Extra ho : this.ListadoHoras_Extras()){
             if(ho.getIndice()==1){
                 h = ho;
             }
         }
         
         return h;
     }
     
     public Hora_Extra REtornarHoraNocturna(){
         
         Hora_Extra h = null;
         
         for(Hora_Extra ho : this.ListadoHoras_Extras()){
             if(ho.getIndice()==2){
                 h = ho;
             }
         }
         
         return h;
     }
     
     
     
     public int ActualizarValorHoraDiurna(float nuevovalor) {
         String sql = "UPDATE `valor_hora` SET `valor`= "+nuevovalor+" WHERE `indice` = 1";
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
    
     
     public int ActualizarValorHoraNocturna(float nuevovalor) {
         String sql = "UPDATE `valor_hora` SET `valor`= "+nuevovalor+" WHERE `indice` = 2";
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
    
    
}
