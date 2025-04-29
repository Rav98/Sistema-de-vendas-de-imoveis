package Controle;

import Modelo.Comprador;
import java.io.*;
import java.util.*;

public class ControleComprador {

    // Array do comprador
    private ArrayList<Comprador> arrayComprador = new ArrayList<>();

    public ControleComprador() throws Exception {
        desserializaComprador();
      
    }

    public boolean StatusComprador(String CPF) {
        for (int i = 0; i < arrayComprador.size(); i++) {
            if (arrayComprador.get(i).getCpf().equals(CPF)) {
                return true;
            }
        }
        return false;
    }
    
    public ArrayList getarrayComprador(){
        return arrayComprador; 
    }
    
    
    public Comprador BuscaComprador(String CPF) {
        Comprador encontrado = null;
        for (int i = 0; i < arrayComprador.size(); i++) {
            if (arrayComprador.get(i).getCpf().equals(CPF)) {
                encontrado = arrayComprador.get(i);
            }
        }
        return encontrado;
    }

    public void criaComprador(String cpf, String nome, String email, String fone, String contatoPref) {
        Comprador comprador = new Comprador(cpf, nome, email, fone, contatoPref);
        arrayComprador.add(comprador);
    }

    private void desserializaComprador() throws Exception {
        File objFile = new File("compradores.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("compradores.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            arrayComprador = (ArrayList<Comprador>) objIS.readObject();
            objIS.close();
        }
    }

    private void serializaComprador() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("compradores.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(arrayComprador);
        objOS.flush();
        objOS.close();
    }

    public void finalize() throws Exception {
        serializaComprador();
    }
}
