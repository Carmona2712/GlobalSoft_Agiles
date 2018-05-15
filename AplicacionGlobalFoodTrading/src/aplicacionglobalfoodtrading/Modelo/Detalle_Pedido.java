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
public class Detalle_Pedido {
    private String fk_codigo_pedido;
    private String id_producto;
    private int cantidad;
    private float iva;
    private float valor_producto;
    private float valortotal;

    public Detalle_Pedido() {
    }

    public Detalle_Pedido(String fk_codigo_pedido, String id_producto, int cantidad, float iva, float valor_producto, float valortotal) {
        this.fk_codigo_pedido = fk_codigo_pedido;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.iva = iva;
        this.valor_producto = valor_producto;
        this.valortotal = valortotal;
    }

    public String getFk_codigo_pedido() {
        return fk_codigo_pedido;
    }

    public void setFk_codigo_pedido(String fk_codigo_pedido) {
        this.fk_codigo_pedido = fk_codigo_pedido;
    }

    public String getId_producto() {
        return id_producto;
    }

    public void setId_producto(String id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getIva() {
        return iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public float getValor_producto() {
        return valor_producto;
    }

    public void setValor_producto(float valor_producto) {
        this.valor_producto = valor_producto;
    }

    public float getValortotal() {
        return valortotal;
    }

    public void setValortotal(float valortotal) {
        this.valortotal = valortotal;
    }
    
    
    
    
}
