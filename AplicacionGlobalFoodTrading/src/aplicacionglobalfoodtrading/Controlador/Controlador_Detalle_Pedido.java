package aplicacionglobalfoodtrading.Controlador;

import aplicacionglobalfoodtrading.Modelo.Detalle_Pedido;
import aplicacionglobalfoodtrading.Modelo.Pedido;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Controlador_Detalle_Pedido {

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

    public Controlador_Detalle_Pedido() {
    }

    public void Conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_gft", "root", "");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al conectar BD");
        }
    }

    public int RegistrarDetalle_Pedido(Detalle_Pedido d) {
        int exi = 0;
        String sql = "INSERT INTO `detalle_pedido`(`fk_id_pedido`, `id_producto`, `cantidad`, `precio`, `iva`, `valor_total`) VALUES ('" + d.getFk_codigo_pedido() + "','" + d.getId_producto() + "'," + d.getCantidad() + "," + d.getValor_producto() + "," + d.getIva() + "," + d.getValortotal() + ")";
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
            JOptionPane.showMessageDialog(null, ex.getMessage() + " en el Controlador Detalle_Pedido", "Error", JOptionPane.ERROR_MESSAGE);
            // System.out.println(sql);
            exi = 0;
        }

        return exi;

    }

    public ArrayList<Detalle_Pedido> ListadoDetallePedidos(String cod) {
        ArrayList<Detalle_Pedido> ListaD = new ArrayList();

        try {
            Conectar();
            String sql = "Select * from detalle_pedido where fk_id_pedido = '" + cod + "'";
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Detalle_Pedido d = new Detalle_Pedido();
                d.setFk_codigo_pedido(rs.getString(1));
                d.setId_producto(rs.getString(2));
                d.setCantidad(rs.getInt(3));
                d.setValor_producto(rs.getFloat(4));
                d.setIva(rs.getFloat(5));
                d.setValortotal(rs.getFloat(6));
                ListaD.add(d);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + " en el Controlador _ de Giro Negocio", "Error", JOptionPane.ERROR_MESSAGE);
            // System.out.println(sql);
        }

        return ListaD;
    }

}
