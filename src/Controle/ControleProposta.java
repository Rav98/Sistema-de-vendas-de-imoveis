package Controle;

import Modelo.Proposta;
import Modelo.Comprador;
import Modelo.Corretor;
import Limite.LimiteProposta;
import Modelo.Imovel;
import java.util.*;

public class ControleProposta{
    private ControlePrincipal objCtr;
    
    public ControleProposta(ControlePrincipal objCtrPrincipal, Imovel imov) throws Exception{
        objCtr = objCtrPrincipal;
        new LimiteProposta(objCtr, imov);
    }

// Cria proposta e insere no array
    public void criaProposta(Imovel i, Calendar data, Comprador comprador, Corretor corretor, double valor) {
        Proposta proposta = new Proposta(data, comprador, corretor, valor);
        i.registraProposta(proposta);
    }
}
