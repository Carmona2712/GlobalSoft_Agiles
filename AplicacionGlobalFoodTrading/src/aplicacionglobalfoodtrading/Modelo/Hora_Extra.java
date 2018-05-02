/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacionglobalfoodtrading.Modelo;


public class Hora_Extra {
  
    private int indice;

    public Hora_Extra(int indice, float Valor) {
        this.indice = indice;
        this.Valor = Valor;
    }

    public Hora_Extra() {
    }
    private float Valor;

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public float getValor() {
        return Valor;
    }

    public void setValor(float Valor) {
        this.Valor = Valor;
    }
    
    
    
}
