package Controle;

import Modelo.Vendedor;
import java.io.*;
import java.util.*;

public class ControleVendedor{
    //ArrayList do vendedor
    private ArrayList<Vendedor> arrayVendedor = new ArrayList<>();

    public ControleVendedor() throws Exception{
        desserializaVendedor();
    }

    public ArrayList<Vendedor> getArrayVendedor() {
        return arrayVendedor;
    }
    
    
    public void criaVendedor(String cpf, String nome, String email, String fone, String contatoPref) {
        Vendedor vendedor = new Vendedor(cpf, nome, email, fone, contatoPref);
        arrayVendedor.add(vendedor);
    }
    
    
    private void desserializaVendedor() throws Exception {
        File objFile = new File("vendedores.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("vendedores.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            arrayVendedor = (ArrayList<Vendedor>) objIS.readObject();
            objIS.close();
        }
    }
    
    private void serializaVendedor() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("vendedores.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(arrayVendedor);
        objOS.flush();
        objOS.close();
    }
    
    public void finalize() throws Exception{
        serializaVendedor();
    }
}
