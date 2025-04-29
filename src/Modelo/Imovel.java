
package Modelo;

import Modelo.*;
import java.util.Calendar;
import java.util.ArrayList;
import Utilitario.Util;
import java.io.Serializable;

public class Imovel implements Serializable{

    private int codigo;
    private String tipo;
    private String descricao;
    private String arquivoFoto;
    private String estado;
    private double preco;
    private double comissao;
    private Calendar dataInclusao;
    private Vendedor vendedor;
    private ArrayList<Visita> listaVisitas = new ArrayList();
    private ArrayList<Proposta> listaPropostas = new ArrayList();

    public Imovel(int codigo, String tipo, String descricao, String arquivoFoto,
            double preco, double comissao, Calendar dataInclusao, Vendedor vendedor) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.arquivoFoto = arquivoFoto;
        this.preco = preco;
        this.comissao = comissao;
        this.dataInclusao = dataInclusao;
        this.vendedor = vendedor;
        this.estado = Util.ATIVO;
    }

    public void addArquivoFoto(String arquivoFoto){
        this.arquivoFoto= arquivoFoto;
    }
    
    public boolean agendaVisita(Visita v) {
        if (this.estado.equalsIgnoreCase(Util.INATIVO)
                || this.estado.equalsIgnoreCase(Util.VENDIDO)) {
            return false;
        }
        listaVisitas.add(v);
        return true;
    }

    public void realizaVisita(Visita v) {
        v.setEstado(Util.REALIZADA);
    }

    public void cancelaVisita(Visita v) {
        v.setEstado(Util.CANCELADA);
    }

    public ArrayList<Visita> getListaVisitas() {
        return listaVisitas;
    }

    public boolean registraProposta(Proposta p) {
        if (this.estado.equalsIgnoreCase(Util.INATIVO)
                || this.estado.equalsIgnoreCase(Util.VENDIDO)) {
            return false;
        }
        listaPropostas.add(p);
        return true;
    }

    public ArrayList<Proposta> getListaPropostas() {
        return listaPropostas;
    }

    public boolean aceitaProposta(Proposta p) {
        if (this.estado.equalsIgnoreCase(Util.INATIVO)
                || this.estado.equalsIgnoreCase(Util.VENDIDO)) {
            return false;
        }
        if (p.getEstado().equalsIgnoreCase(Util.SUBMETIDA)) {
            this.estado = Util.VENDIDO;
            p.setEstado(Util.ACEITA);
            return true;
        }
        return false;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public String getArquivoFoto(){
        return arquivoFoto;
    }

    public String getEstado() {
        return estado;
    }

    public double getPreco() {
        return preco;
    }

    public double getComissao() {
        return comissao;
    }

    public Calendar getDataInclusao() {
        return dataInclusao;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

}
