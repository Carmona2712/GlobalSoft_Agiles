/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionglobalfoodtrading.Modelo;


public class Recurso {
    private String codigo;
    private String nom_recurso;
    private String tipo;
    private String maraca;
    private int cantidad;
    private int precio;
    private String fecha_adq;
    private String fecha_garantia;
    private String cod_seguro;
    private String imagen;

    public Recurso(String codigo, String nom_recurso, String tipo, String maraca, int cantidad, int precio, String fecha_adq, String fecha_garantia, String cod_seguro, String imagen) {
        this.codigo = codigo;
        this.nom_recurso = nom_recurso;
        this.tipo = tipo;
        this.maraca = maraca;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fecha_adq = fecha_adq;
        this.fecha_garantia = fecha_garantia;
        this.cod_seguro = cod_seguro;
        this.imagen = imagen;
    }

    public Recurso() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNom_recurso() {
        return nom_recurso;
    }

    public void setNom_recurso(String nom_recurso) {
        this.nom_recurso = nom_recurso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMaraca() {
        return maraca;
    }

    public void setMaraca(String maraca) {
        this.maraca = maraca;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getFecha_adq() {
        return fecha_adq;
    }

    public void setFecha_adq(String fecha_adq) {
        this.fecha_adq = fecha_adq;
    }

    public String getFecha_garantia() {
        return fecha_garantia;
    }

    public void setFecha_garantia(String fecha_garantia) {
        this.fecha_garantia = fecha_garantia;
    }

    public String getCod_seguro() {
        return cod_seguro;
    }

    public void setCod_seguro(String cod_seguro) {
        this.cod_seguro = cod_seguro;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    
    
}
