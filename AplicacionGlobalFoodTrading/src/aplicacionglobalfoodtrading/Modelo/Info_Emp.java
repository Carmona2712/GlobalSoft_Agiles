/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionglobalfoodtrading.Modelo;


public class Info_Emp {
    private String NIT;
    private String Nombre;
    private String direccion;
    private String reg_Empresa;
    private String giro_negocio;
    private String telefono;

    public Info_Emp(String NIT, String Nombre, String direccion, String reg_Empresa, String giro_negocio, String telefono) {
        this.NIT = NIT;
        this.Nombre = Nombre;
        this.direccion = direccion;
        this.reg_Empresa = reg_Empresa;
        this.giro_negocio = giro_negocio;
        this.telefono = telefono;
    }

    public Info_Emp() {
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReg_Empresa() {
        return reg_Empresa;
    }

    public void setReg_Empresa(String reg_Empresa) {
        this.reg_Empresa = reg_Empresa;
    }

    public String getGiro_negocio() {
        return giro_negocio;
    }

    public void setGiro_negocio(String giro_negocio) {
        this.giro_negocio = giro_negocio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
    
    
}
