/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionglobalfoodtrading.Controlador;

import aplicacionglobalfoodtrading.Modelo.Cliente;
import aplicacionglobalfoodtrading.Modelo.Giro_Negocio;
import aplicacionglobalfoodtrading.Modelo.Pedido;
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
public class Controlador_Pedido {

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

    public Controlador_Pedido() {
    }

    public void Conectar() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_gft", "root", "");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar BD");
        }
    }

    public int RegistrarPedido(Pedido p) {
        int exi = 0;
        String sql = "INSERT INTO `pedido`(`id_pedido`, `fecha_pedido`, `id_cliente`, `total_iva`, `total_valor`) VALUES ('"+p.getId_pedido()+"','"+p.getFecha()+"','"+p.getId_cliente()+"',"+p.getTotal_iva()+","+p.getTotal_pedido()+")";
        try {
            Conectar();
            //System.out.println(sql);
            System.out.println(sql);
            st = con.createStatement();

            if (st.executeUpdate(sql) > 0) {
                exi = 1;
            } else {
                exi = 0;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + " en el Controlador Pedido", "Error", JOptionPane.ERROR_MESSAGE);
            // System.out.println(sql);
            exi = 0;
        }

        return exi;

    }
    
    
    
    public ArrayList<Pedido> ListadoPedidos(){
        ArrayList<Pedido> Listap = new ArrayList();
        Controlador_Detalle_Pedido cd = new Controlador_Detalle_Pedido();
         try {
            Conectar();
            String sql = "Select * from pedido order by fecha_pedido";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
               Pedido p = new Pedido();
                p.setId_pedido(rs.getString(1));
                p.setFecha(rs.getString(2));
                p.setId_cliente(rs.getString(3));
                p.setTotal_iva(rs.getFloat(4));
                p.setTotal_pedido(rs.getFloat(5));
                p.setLista_Detalles(cd.ListadoDetallePedidos(p.getId_pedido()));
                Listap.add(p);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + " en el Controlador _ de Giro Negocio", "Error", JOptionPane.ERROR_MESSAGE);
            // System.out.println(sql);
        }
        return Listap;
    }
    
    
    
    
    public Pedido BuscarPedidoxCod(String cod){
        
        Pedido p = null;
        
        
       for(Pedido pe : this.ListadoPedidos()){
            if(pe.getId_pedido().equals(cod)){
                p = pe;
            }
       }
        
     return p;   
    }
    

}
