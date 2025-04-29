package Controle;

import Modelo.*;
import Limite.LimiteVisita;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class ControleVisita{
    private ArrayList<Visita> arrayvisita = new ArrayList<>();
    private ControlePrincipal objCtr;
    
    public ControleVisita(ControlePrincipal objCtrPrincipal, Imovel imov) throws Exception{
        objCtr = objCtrPrincipal;
        new LimiteVisita(objCtr, imov);
    }

// Cria nova visita
    public void criavisita(Imovel imov, Calendar data, Comprador comprador, Corretor corretor) {
        Visita visita = new Visita(data, comprador, corretor);
        imov.agendaVisita(visita);
    }
    
    
}
