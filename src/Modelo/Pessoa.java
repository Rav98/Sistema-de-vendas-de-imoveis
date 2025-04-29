
package Modelo;

import java.io.Serializable;

public abstract class Pessoa implements Serializable{
    private String cpf;
    private String nome;
    private String email;
    private String fone;

    public Pessoa(String cpf, String nome, String email, String fone) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.fone = fone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getFone() {
        return fone;
    }
}
