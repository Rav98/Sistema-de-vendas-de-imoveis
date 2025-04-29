package Controle;

import Modelo.*;
import Utilitario.Util;
import java.io.*;
import java.util.*;

public class ControleCorretor {

    //ArrayList do Corretor
    ArrayList<Corretor> arrayCorretor = new ArrayList<>();

    //Ler o arquivo que foi serealizado
    public ControleCorretor() throws Exception {
        desserializaCorretor();
    }

    public ArrayList<Corretor> getArrayCorretor() {
        return arrayCorretor;
    }

    // Buscar um corretor no array
    public Corretor BuscaCorretor(String creci) {
        Corretor result = null;
        for (int i = 0; i < arrayCorretor.size(); i++) {
            if (arrayCorretor.get(i).getCreci() == creci) {
                result = arrayCorretor.get(i);
            }
        }
        return result;
    }

    // Listar todos os corretores cadastrados
    public String[] TodosCorretores() {
        String resultado[] = new String[arrayCorretor.size()];
        for (int i = 0; i < arrayCorretor.size(); i++) {
            resultado[i] = ("\nNome: " + arrayCorretor.get(i).getNome() + " -- CRECI: " + arrayCorretor.get(i).getCreci());
        }
        return resultado;
    }

    // Função para calcular o totla faturado por corretor em um periodo 
    public String TotalFatCorretor(ArrayList<Imovel> imovel, Corretor corretor, Calendar inicio, Calendar fim) {
        double total = 0;

        for (int i = 0; i < imovel.size(); i++) {
            ArrayList<Proposta> proposta = imovel.get(i).getListaPropostas();
            for (int j = 0; j < proposta.size(); j++) {
                if (proposta.get(j).getCorretor().getCreci().equals(corretor.getCreci())
                        && proposta.get(j).getEstado().equals(Util.ACEITA) && proposta.get(j).getData().after(inicio) && proposta.get(j).getData().before(fim)) {
                    total += corretor.getPercCorretagem() * (proposta.get(j).getValor()* imovel.get(i).getComissao());
                }
            }
        }
        return ("Nome do corretor: " + corretor.getNome() + " - Valor total R$: " + total);
    }
    
    // Função para mostrar as visitas por corretor no periodo
    public String VisitasCorretor(ArrayList<Imovel> imovel, Corretor corretor, Calendar inicio, Calendar fim) {
        String resultado = "";
        for (int i = 0; i < imovel.size(); i++) {
            ArrayList<Visita> visitas = imovel.get(i).getListaVisitas();
            for (int j = 0; j < visitas.size(); j++) {
                if (visitas.get(j).getCorretor().getCreci().equals(corretor.getCreci())
                        && visitas.get(j).getData().after(inicio) && visitas.get(j).getData().before(fim)) {
                    resultado += ("\nCodigo do Imovel: " + imovel.get(i).getCodigo() + "\nData da visita:" + LeData(visitas.get(j).getData()) + "\nVisitado pelo comprador: " + visitas.get(j).getComprador().getNome()
                            + "\nCPF do comprador: " + visitas.get(j).getComprador().getCpf() + "\nCorretor: " + corretor.getNome() + "\nCRECI do Corretor: " + corretor.getCreci()) + "\n";

                }
            }
        }
        return resultado;
    }

    // Recebe uma Calendar e retorna uma String com a data 
    public String LeData(Calendar data) {
        String result = ("");
        String dia, mes, ano;

        dia = String.valueOf(data.get(Calendar.DAY_OF_MONTH));
        mes = String.valueOf(data.get(Calendar.MONTH));
        ano = String.valueOf(data.get(Calendar.YEAR));

        if (mes.equals("11")) {
            mes = ("12");
        }

        if (mes.equals("0")) {
            mes = ("1");
        }

        return result = (dia + "/" + mes + "/" + ano);
    }

    // Cria um novo corretor no sistema
    public void criaCorretor(String cpf, String nome, String email, String fone, String creci, double percCorretagem) {
        Corretor corretor = new Corretor(cpf, nome, email, fone, creci, percCorretagem);
        arrayCorretor.add(corretor);
    }

    // Faz a leitura do arquivo que contem os corretores
    private void desserializaCorretor() throws Exception {
        File objFile = new File("corretores.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("corretores.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            arrayCorretor = (ArrayList<Corretor>) objIS.readObject();
            objIS.close();
        }
    }

    // Salva os corretores no arquivo 
    private void serializaCorretor() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("corretores.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(arrayCorretor);
        objOS.flush();
        objOS.close();
    }

    public void finalize() throws Exception {
        serializaCorretor();
    }
}
