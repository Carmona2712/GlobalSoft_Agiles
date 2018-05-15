/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionglobalfoodtrading.Modelo;

/**
 *
 * @author Ricardo Carmona
 */
public class Pedido {
    
    private String id_pedido;
    private String fecha;
    private String id_proveedor;
    private float total_iva;
    private float total_pedido;

    public Pedido() {
    }

    public Pedido(String id_pedido, String fecha, String id_proveedor, float total_iva, float total_pedido) {
        this.id_pedido = id_pedido;
        this.fecha = fecha;
        this.id_proveedor = id_proveedor;
        this.total_iva = total_iva;
        this.total_pedido = total_pedido;
    }

    public String getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(String id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public float getTotal_iva() {
        return total_iva;
    }

    public void setTotal_iva(float total_iva) {
        this.total_iva = total_iva;
    }

    public float getTotal_pedido() {
        return total_pedido;
    }

    public void setTotal_pedido(float total_pedido) {
        this.total_pedido = total_pedido;
    }
    
    
    
    
    
}
