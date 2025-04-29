
package Modelo;

import java.util.Calendar;
import Modelo.*;
import Utilitario.Util;
import java.io.Serializable;

public class Visita implements Serializable{
    private Calendar data;
    private Comprador comprador;
    private Corretor corretor;
    private String estado;

    public Visita(Calendar data, Comprador comprador, Corretor corretor) {
        this.data = data;
        this.comprador = comprador;
        this.corretor = corretor;
        this.estado = Util.AGENDADA;
    }

    public Calendar getData() {
        return data;
        
    }

    public Comprador getComprador() {
        return comprador;
    }

    public Corretor getCorretor() {
        return corretor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
