/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionglobalfoodtrading.Modelo;

public class Pago_Empleado {

    private String Codigo_Pago;
    private String fk_Empleado;
    private String fecha_Pago;
    private String mes_pagado;
    private int horas_Extras;
    private int Horas_Nocturnas;
    private float Recargo_Adicional;
    private String notas;
    private float sueldo_base;
    private float total_recargo;
    private float total_Pago;

    public Pago_Empleado() {
    }

    public Pago_Empleado(String Codigo_Pago, String fk_Empleado, String fecha_Pago, String mes_pagado, int horas_Extras, int Horas_Nocturnas, float Recargo_Adicional, String notas, float sueldo_base, float total_recargo, float total_Pago) {
        this.Codigo_Pago = Codigo_Pago;
        this.fk_Empleado = fk_Empleado;
        this.fecha_Pago = fecha_Pago;
        this.mes_pagado = mes_pagado;
        this.horas_Extras = horas_Extras;
        this.Horas_Nocturnas = Horas_Nocturnas;
        this.Recargo_Adicional = Recargo_Adicional;
        this.notas = notas;
        this.sueldo_base = sueldo_base;
        this.total_recargo = total_recargo;
        this.total_Pago = total_Pago;
    }

    public String getCodigo_Pago() {
        return Codigo_Pago;
    }

    public void setCodigo_Pago(String Codigo_Pago) {
        this.Codigo_Pago = Codigo_Pago;
    }

    public String getFk_Empleado() {
        return fk_Empleado;
    }

    public void setFk_Empleado(String fk_Empleado) {
        this.fk_Empleado = fk_Empleado;
    }

    public String getFecha_Pago() {
        return fecha_Pago;
    }

    public void setFecha_Pago(String fecha_Pago) {
        this.fecha_Pago = fecha_Pago;
    }

    public String getMes_pagado() {
        return mes_pagado;
    }

    public void setMes_pagado(String mes_pagado) {
        this.mes_pagado = mes_pagado;
    }

    public int getHoras_Extras() {
        return horas_Extras;
    }

    public void setHoras_Extras(int horas_Extras) {
        this.horas_Extras = horas_Extras;
    }

    public int getHoras_Nocturnas() {
        return Horas_Nocturnas;
    }

    public void setHoras_Nocturnas(int Horas_Nocturnas) {
        this.Horas_Nocturnas = Horas_Nocturnas;
    }

    public float getRecargo_Adicional() {
        return Recargo_Adicional;
    }

    public void setRecargo_Adicional(float Recargo_Adicional) {
        this.Recargo_Adicional = Recargo_Adicional;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public float getSueldo_base() {
        return sueldo_base;
    }

    public void setSueldo_base(float sueldo_base) {
        this.sueldo_base = sueldo_base;
    }

    public float getTotal_recargo() {
        return total_recargo;
    }

    public void setTotal_recargo(float total_recargo) {
        this.total_recargo = total_recargo;
    }

    public float getTotal_Pago() {
        return total_Pago;
    }

    public void setTotal_Pago(float total_Pago) {
        this.total_Pago = total_Pago;
    }
    
    
    
    
    
    
}
