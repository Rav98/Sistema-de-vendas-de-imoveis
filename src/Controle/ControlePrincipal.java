package Controle;

import Limite.*;
import Modelo.Imovel;
import java.text.ParseException;

public class ControlePrincipal {

    // Controle das outras classes
    private ControleComprador objCtrComprador;
    private ControleCorretor objCtrCorretor;
    private ControleImovel objCtrImovel;
    private ControleProposta objCtrProposta = null;
    private ControleVendedor objCtrVendedor;
    private ControleVisita objCtrVisita = null;

    private LimitePrincipal objLimPrincipal;

    // Criação dos controles e do limite
    public ControlePrincipal() throws ParseException, Exception {
        try {
            objCtrComprador = new ControleComprador();
            objCtrCorretor = new ControleCorretor();
            objCtrImovel = new ControleImovel();
            objCtrVendedor = new ControleVendedor();
        } catch (Exception error) {
            System.out.println("Erro na abertura de arquivo " + error);
            System.exit(1);
        }
        objLimPrincipal = new LimitePrincipal(this);
    }

    public void criarControleProposta(Imovel imov) throws ParseException, Exception{
        try{
            objCtrProposta = new ControleProposta(this, imov);
        } catch (Exception error){
            System.out.println("Erro na abertura de arquivo!");
            System.exit(1);
        }
    }
    
    public void criarControleVisita(Imovel imov) throws ParseException, Exception{
        try{
            objCtrVisita = new ControleVisita(this, imov);
        } catch (Exception error){
            System.out.println("Erro na abertura de arquivo!");
            System.exit(1);
        }
    }
    
    // Fechamento dos controles com a serialização dos dados
    public void finalize() {
        try {
            objCtrComprador.finalize();
            objCtrCorretor.finalize();
            objCtrImovel.finalize();
            objCtrVendedor.finalize();
        } catch (Exception error) {
            System.out.println("Erro no fechamento dos arquivos");
        } finally {
            System.exit(0);
        }

    }

    // Getters de cada controle
    public ControleComprador getObjCtrComprador() {
        return objCtrComprador;
    }

    public ControleCorretor getObjCtrCorretor() {
        return objCtrCorretor;
    }

    public ControleImovel getObjCtrImovel() {
        return objCtrImovel;
    }

    public ControleProposta getObjCtrProposta() {
        return objCtrProposta;
    }

    public ControleVendedor getObjCtrVendedor() {
        return objCtrVendedor;
    }

    public ControleVisita getObjCtrVisita() {
        return objCtrVisita;
    }

}
