/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionglobalfoodtrading.Controlador;

import aplicacionglobalfoodtrading.Modelo.Pedido;
import aplicacionglobalfoodtrading.Modelo.Producto;
import aplicacionglobalfoodtrading.Modelo.Recurso;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Controlador_Recurso {

    private static Connection con;
    private static ResultSet rs;
    private static Statement st;

    public Controlador_Recurso() {
    }

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

    public void Conectar() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_gft", "root", "");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar BD");
        }
    }

    public int RegistrarPedido(Recurso r) {
        int exi = 0;
        String sql = "INSERT INTO `recurso`(`codigo`, `recurso`, `tipo`, `marca`, `cantidad`, `precio`, `fecha_adq`, `fecha_garantia`, `cod_factura`, `codigo_seguro`, `nombre_seguro`, `imagen`) VALUES ('" + r.getCodigo() + "','" + r.getNom_recurso() + "','" + r.getTipo() + "','" + r.getMaraca() + "'," + r.getCantidad() + "," + r.getPrecio() + ",'" + r.getFecha_adq() + "','" + r.getFecha_garantia() + "','" + r.getCod_factura() + "','" + r.getCod_seguro() + "','" + r.getNombre_seguro() + "','" + r.getImagen() + "')";
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
            JOptionPane.showMessageDialog(null, ex.getMessage() + " en el Controlador Recurso", "Error", JOptionPane.ERROR_MESSAGE);
            // System.out.println(sql);
            exi = 0;
        }

        return exi;

    }

    public ArrayList<Recurso> ListadoRecursoss() {
        ArrayList<Recurso> Listar = new ArrayList();

        try {
            Conectar();
            String sql = "Select * from recurso order by recurso";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Recurso r = new Recurso();
                r.setCod_seguro(rs.getString(1));
                r.setNom_recurso(rs.getString(2));
                r.setTipo(rs.getString(3));
                r.setMaraca(rs.getString(4));
                r.setCantidad(rs.getInt(5));
                r.setPrecio(rs.getInt(6));
                r.setFecha_adq(rs.getString(7));
                r.setFecha_garantia(rs.getString(8));
                r.setCod_factura(rs.getString(9));
                r.setCod_seguro(rs.getString(10));
                r.setNombre_seguro(rs.getString(11));
                r.setImagen(rs.getString(12));
                Listar.add(r);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + " en el Controlador _ de Recursos", "Error", JOptionPane.ERROR_MESSAGE);
            // System.out.println(sql);
        }
        return Listar;
    }

    public Recurso RetornarRecursoxCodigo(String cod) {
        Recurso re = null;
        for (Recurso r : this.ListadoRecursoss()) {
            if (r.getCodigo().equals(cod)) {
                re = r;
            }
        }
        return re;
    }

   

}
