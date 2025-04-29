
package Modelo;

import java.io.Serializable;

public class Corretor extends Pessoa implements Serializable{
    private String creci;
    private double percCorretagem;    
    
    public Corretor(String cpf, String nome, String email, String fone,
                    String creci, double percCorretagem) {
        super(cpf, nome, email, fone);
        this.creci = creci;
        this.percCorretagem = percCorretagem;
    }

    public String getCreci() {
        return creci;
    }

    public double getPercCorretagem() {
        return percCorretagem;
    }    
    
}
