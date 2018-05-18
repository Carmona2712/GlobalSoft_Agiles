/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionglobalfoodtrading.Modelo;

import java.util.ArrayList;

/**
 *
 * @author Ricardo Carmona
 */
public class Pedido {
    
    private String id_pedido;
    private String fecha;
    private String id_cliente;
    private float total_iva;
    private float total_pedido;
    private ArrayList<Detalle_Pedido> lista_Detalles;

    public Pedido() {
    }

    public Pedido(String id_pedido, String fecha, String id_cliente, float total_iva, float total_pedido, ArrayList<Detalle_Pedido> lista_Detalles) {
        this.id_pedido = id_pedido;
        this.fecha = fecha;
        this.id_cliente = id_cliente;
        this.total_iva = total_iva;
        this.total_pedido = total_pedido;
        this.lista_Detalles = lista_Detalles;
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

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public ArrayList<Detalle_Pedido> getLista_Detalles() {
        return lista_Detalles;
    }

    public void setLista_Detalles(ArrayList<Detalle_Pedido> lista_Detalles) {
        this.lista_Detalles = lista_Detalles;
    }
    
    
    
    
    
}
