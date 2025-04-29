package Controle;

import Modelo.*;
import Utilitario.Util;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.*;
import javax.swing.JOptionPane;

public class ControleImovel {
// Array do IMOVEL

    private ArrayList<Imovel> arrayImovel = new ArrayList<>();

    public ControleImovel() throws Exception {
        desserializaImovel();
    }

    public void copiaArquivo(File a, File b) throws IOException {
        Files.copy(a.toPath(), b.toPath());
    }

// Cria um novo imovel e insere no aray
    public void criaImovel(int codigo, String tipo, String descricao, String arquivoFoto,
            double preco, double comissao, Calendar dataInclusao, Vendedor vendedor) {
        Imovel imovel = new Imovel(codigo, tipo, descricao, arquivoFoto, preco, comissao, dataInclusao, vendedor);
        arrayImovel.add(imovel);
    }

    public ArrayList<Imovel> getListaImovel() {
        return arrayImovel;
    }

    // Faz a leitura do arquivo que contem os corretores
    private void desserializaImovel() throws Exception {
        File objFile = new File("imoveis.dat");
        if (objFile.exists()) {
            FileInputStream objFileIS = new FileInputStream("imoveis.dat");
            ObjectInputStream objIS = new ObjectInputStream(objFileIS);
            arrayImovel = (ArrayList<Imovel>) objIS.readObject();
            objIS.close();
        }
    }

    // Gera o relarotio de vendas no periodo 
    public String relatorioVenda(Calendar inicio, Calendar fim) {
        String vendas = ("");
        boolean status = false;
        for (int i = 0; i < arrayImovel.size(); i++) {
            if (arrayImovel.get(i).getEstado().equals(Util.VENDIDO)) {
                status = true;
                ArrayList<Proposta> listaProp = arrayImovel.get(i).getListaPropostas();
                double value = arrayImovel.get(i).getPreco();
                boolean ok = false;
                for (Proposta p : listaProp) {
                    if (p.getEstado().equals(Util.ACEITA)) {
                        value = p.getValor();
                        if(p.getData().after(inicio) && p.getData().before(fim)) ok = true;
                        break;
                    }
                }
                if(!ok){
                    status = false;
                    continue;
                }
                vendas += ("\nCodigo: " + arrayImovel.get(i).getCodigo() + "\n"
                        + "Tipo: " + arrayImovel.get(i).getTipo() + "\n"
                        + "Preço R$:" + value + "\n"
                        + "Descrição: " + arrayImovel.get(i).getDescricao() + "\n"
                        + "Data: " + LeData(arrayImovel.get(i).getDataInclusao()) + "\n"
                        + "Vendedor: " + arrayImovel.get(i).getVendedor().getNome() + "\n"
                        + "Valor de comissão: " + arrayImovel.get(i).getComissao() * value + "\n");

            }
        }
        if (arrayImovel.size() == 0) {
            return ("Não existe imoveis cadastrados no sistema");
        } else {
            if (status == true) {
                return vendas;
            } else {
                return ("Não teve vendas de imoveis nesse periodo!");
            }

        }
    }
    
    public String TotalFat(ArrayList<Imovel> imovel, Calendar inicio, Calendar fim) {
        double totalCorretores = 0;
        double totalImobiliaria = 0;
        
        for (int i = 0; i < imovel.size(); i++) {
            ArrayList<Proposta> proposta = imovel.get(i).getListaPropostas();
            for (int j = 0; j < proposta.size(); j++) {
                if (proposta.get(j).getEstado().equals(Util.ACEITA) && proposta.get(j).getData().after(inicio) && proposta.get(j).getData().before(fim)) {
                    double aux = proposta.get(j).getCorretor().getPercCorretagem() * (proposta.get(j).getValor()* imovel.get(i).getComissao());
                    totalImobiliaria += (proposta.get(j).getValor()* imovel.get(i).getComissao()) - aux;
                    totalCorretores += aux;
                }
            }
        }
        return ("Total passado para a imobiliaria: R$" + totalImobiliaria + "\nTotal passado aos corretores: R$: " + totalCorretores);
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

    //Listagem de imoveis por vendedor
    public String RelatorioImoveisVendedor(String CPF) {
        String result = ("");
        if (arrayImovel.size() == 0) {
            JOptionPane.showMessageDialog(null, "Não há vendedores cadastrados no sistema!");
            return ("");
        } else {
            for (int i = 0; i < arrayImovel.size(); i++) {
                if (arrayImovel.get(i).getVendedor().getCpf().equals(CPF)) {
                    result += ("\n\nCodigo do imovel: " + getListaImovel().get(i).getCodigo() + "\nCPF do vendedor: " + CPF + "\nTipo do imovel:" + getListaImovel().get(i).getTipo() + "\nEstado do imovel: " + getListaImovel().get(i).getEstado() + "\nValor do imovel R$: " + arrayImovel.get(i).getPreco() + "\nDescrição do imovel: " + getListaImovel().get(i).getDescricao());
                }
            }
            if (result.equals("")) {
                result = ("CPF não encontrado. Digite um CPF referente a um vendedor.");
            }
        }
        return result;
    }

    // Salva os corretores no arquivo 
    private void serializaImovel() throws Exception {
        FileOutputStream objFileOS = new FileOutputStream("imoveis.dat");
        ObjectOutputStream objOS = new ObjectOutputStream(objFileOS);
        objOS.writeObject(arrayImovel);
        objOS.flush();
        objOS.close();
    }

    public void finalize() throws Exception {
        serializaImovel();
    }
}
