package Limite;

import Controle.*;
import Modelo.*;
import Utilitario.Util;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import static java.util.Calendar.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilCalendarModel;

class LimiteImovel extends JPanel implements ListSelectionListener {

    private LimitePrincipal objLimPrincipal;
    private ControlePrincipal objCtrPrincipal;
    File selectedFile = null, destination;
    ArrayList<Vendedor> vendedores = new ArrayList<>();
    private JList listaImovel, listaImoveisSubmetidos, listaPropostas;
    private DefaultListModel lm = new DefaultListModel();
    private DefaultListModel lm2 = new DefaultListModel();
    private DefaultListModel lm3 = new DefaultListModel();
    Imovel selecionado = null;

    public LimiteImovel(ControlePrincipal objCtrPrin, LimitePrincipal objLimPrin, int operacao) {
        objLimPrincipal = objLimPrin;
        objCtrPrincipal = objCtrPrin;
        vendedores = objCtrPrincipal.getObjCtrVendedor().getArrayVendedor();
        this.setSize(800, 600);
        this.setLayout(new FlowLayout());

        switch (operacao) {
            case 1:
                cadastrarImovel();
                break;
            case 2:
                catalogoImoveis();
                break;
            case 3:
                propostasPendentes();
                break;
            case 4:
                eventosPorImovel();
                break;
            case 5:
                relatorioVendas();
                break;
            case 6:
                imoveisVendedor();
                break;
            case 7:
                totalFaturado();
            default:
                break;
        }
    }

    //======================================================================================================
    //Case 1
    private void cadastrarImovel() {
        String vends[] = new String[vendedores.size()];
        for (int i = 0; i < vendedores.size(); i++) {
            vends[i] = vendedores.get(i).getCpf();
        }

        JLabel lblCodigo = new JLabel("Digite o código do imóvel: ");
        JTextField txtCodigo = new JTextField(15);
        JLabel lblTipo = new JLabel("Selecione o tipo do imóvel: ");
        JComboBox cmbTipo = new JComboBox(new String[]{Util.CASA, Util.LOTE, Util.APTO, Util.SALA, Util.RURAL});
        JLabel lblDescricao = new JLabel("Digite a descrição do imóvel: ");
        JTextField txtDescricao = new JTextField(50);
        JLabel lblPreco = new JLabel("Digite o preço do imóvel: ");
        JTextField txtPreco = new JTextField(10);
        JLabel lblComissao = new JLabel("Selecione a comissão: ");
        JComboBox cmbComissao = new JComboBox(new Double[]{0.01, 0.02, 0.03, 0.04, 0.05});
        JLabel lblVendedor = new JLabel("Vendedor responsável: ");
        JComboBox cmbVendedores = new JComboBox(vends);

        JButton jbUpar = new JButton("Enviar imagem do imagem (Opcional)");
        JButton jbCadastrar = new JButton("Cadastrar");
        JButton jbVoltar = new JButton("Voltar");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p8 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p1.add(lblCodigo);
        p1.add(txtCodigo);
        p2.add(lblTipo);
        p2.add(cmbTipo);
        p3.add(lblDescricao);
        p3.add(txtDescricao);
        p4.add(lblPreco);
        p4.add(txtPreco);
        p5.add(lblComissao);
        p5.add(cmbComissao);
        p6.add(jbUpar);
        p7.add(lblVendedor);
        p7.add(cmbVendedores);
        p8.add(jbCadastrar);
        p8.add(jbVoltar);
        this.add(Box.createVerticalGlue());
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(p5);
        this.add(p6);
        this.add(p7);
        this.add(p8);
        this.add(Box.createVerticalGlue());

        jbUpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int returnValue = jfc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    selectedFile = jfc.getSelectedFile();
                }
            }
        });

        jbCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtCodigo.getText().isEmpty() || txtDescricao.getText().isEmpty() || txtPreco.getText().isEmpty() || vendedores.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                } else {
                    Vendedor v = null;
                    if (vendedores.size() > 0) {
                        v = vendedores.get(cmbVendedores.getSelectedIndex());
                    }
                    String nomeArq = "";
                    if (selectedFile != null) {
                        nomeArq = txtCodigo.getText();
                    }

                    objCtrPrincipal.getObjCtrImovel().criaImovel(Integer.parseInt(txtCodigo.getText()), (String) cmbTipo.getSelectedItem(), txtDescricao.getText(),
                            nomeArq, Double.parseDouble(txtPreco.getText()), (Double) cmbComissao.getSelectedItem(), Calendar.getInstance(), v);
                    JOptionPane.showMessageDialog(null, "Imóvel cadastrado!");

                    destination = new File("images/" + txtCodigo.getText());
                    try {
                        if (selectedFile != null) {
                            String direc = "images/";
                            File directory = new File(direc);
                            if (!directory.exists()) directory.mkdir();
                            
                            objCtrPrincipal.getObjCtrImovel().copiaArquivo(selectedFile, destination);
                        }
                    } catch (IOException ex) {
                    }

                    JPanel cards = objLimPrincipal.cards;
                    CardLayout principal = (CardLayout) (cards.getLayout());
                    principal.show(cards, "Tela Principal");
                }
            }
        });

        jbVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel cards = objLimPrincipal.cards;
                CardLayout cardCadComprador = (CardLayout) (cards.getLayout());
                cardCadComprador.show(cards, "Tela Principal");
            }
        });

    }

    //======================================================================================================
    //Case 2
    private void catalogoImoveis() {
        JLabel lblTipo = new JLabel("Insira o tipo do imóvel que deseja ver: ");
        JComboBox cmbTipo = new JComboBox(new Object[]{Util.CASA, Util.LOTE, Util.APTO, Util.SALA, Util.RURAL});

        JButton jbProcurar = new JButton("Procurar");
        JButton jbVoltar = new JButton("Voltar");

        this.add(lblTipo);
        this.add(cmbTipo);

        this.add(jbProcurar);
        this.add(jbVoltar);

        listaImovel = new JList(lm);
        this.add(listaImovel);

        listaImovel.addListSelectionListener(this);

        jbProcurar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemSelecao = (String) cmbTipo.getSelectedItem();
                int index = 0;
                int quant = 0;
                lm.clear();
                for (Imovel imov : objCtrPrincipal.getObjCtrImovel().getListaImovel()) {
                    if (imov.getEstado().equals(Util.ATIVO) && imov.getTipo().equals(itemSelecao)) {
                        String itemJList = new String();
                        itemJList = "Imóvel: " + imov.getCodigo() + "\n Descrição: " + imov.getDescricao();
                        int cod = imov.getCodigo();

                        lm.add(index, itemJList);
                        quant++;
                    }
                }
                if (quant == 0) {
                    lm.clear();
                    lm.add(index, "Nenhum imóvel deste tipo foi cadastrado");
                }

            }

        });

        jbVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel cards = objLimPrincipal.cards;
                CardLayout cardCadComprador = (CardLayout) (cards.getLayout());
                cardCadComprador.show(cards, "Tela Principal");
            }
        });

    }

    //======================================================================================================
    //Case 3
    private void propostasPendentes() {
        ArrayList<Imovel> listaImoveis = objCtrPrincipal.getObjCtrImovel().getListaImovel();
        String imoveis[] = new String[listaImoveis.size()];
        for (int i = 0; i < listaImoveis.size(); i++) {
            imoveis[i] = "Imóvel: " + listaImoveis.get(i).getCodigo() + " || Status: " + listaImoveis.get(i).getEstado() + " || Propostas: " + listaImoveis.get(i).getListaPropostas().size();
        }
        listaPropostas = new JList(imoveis);
        listaPropostas.addListSelectionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel lblMsg = new JLabel("Clique em um dos imóveis da lista abaixo para verificar as propostas pendentes: ");

        p1.add(lblMsg);
        p2.add(listaPropostas);
        this.add(Box.createVerticalGlue());
        this.add(p1);
        this.add(p2);
        this.add(Box.createVerticalGlue());
    }

    //======================================================================================================
    //Case 4
    private void eventosPorImovel() {
        JButton btnBuscar = new JButton("Buscar");
        JButton btnVoltar = new JButton("Voltar");

        JLabel lblCodigo = new JLabel("Digite o código do imóvel: ");
        JLabel lblDataInicial = new JLabel("Selecione a data inicial:");
        JLabel lblDataFinal = new JLabel("Selecione a data final:");

        JTextField txtCodigo = new JTextField(20);

        UtilCalendarModel modelI = new UtilCalendarModel();
        Properties pI = new Properties();
        pI.put("text.today", "Today");
        pI.put("text.month", "Month");
        pI.put("text.year", "Year");
        UtilCalendarModel modelF = new UtilCalendarModel();
        Properties pF = new Properties();
        pF.put("text.today", "Today");
        pF.put("text.month", "Month");
        pF.put("text.year", "Year");
        JDatePanelImpl datePanelInicial = new JDatePanelImpl(modelI, pI);
        JDatePanelImpl datePanelFinal = new JDatePanelImpl(modelF, pF);

        JDatePickerImpl datePickerInicial = new JDatePickerImpl(datePanelInicial, new DateComponentFormatter());
        JDatePickerImpl datePickerFinal = new JDatePickerImpl(datePanelFinal, new DateComponentFormatter());

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p8 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p1.add(lblCodigo);
        p1.add(txtCodigo);

        p2.add(lblDataInicial);
        p2.add(datePickerInicial);

        p3.add(lblDataFinal);
        p3.add(datePickerFinal);

        p4.add(btnBuscar);
        p4.add(btnVoltar);

        this.add(Box.createVerticalGlue());
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(Box.createVerticalGlue());

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar inicio = (Calendar) datePickerInicial.getModel().getValue();
                Calendar fim = (Calendar) datePickerFinal.getModel().getValue();

                ArrayList<Imovel> arrayImovel = objCtrPrincipal.getObjCtrImovel().getListaImovel();
                String str = "";

                for (Imovel aux : arrayImovel) {
                    if (aux.getCodigo() == Integer.parseInt(txtCodigo.getText())) {

                        ArrayList<Visita> arrayVisitas = aux.getListaVisitas();
                        for (Visita vis : arrayVisitas) {
                            if (vis.getData().after(inicio) && vis.getData().before(fim)) {
                                if (str != "") {
                                    str += "\n\n";
                                }
                                str += "Visita realizada no dia: " + vis.getData().get(DAY_OF_MONTH) + "/" + vis.getData().get(MONTH) + "/" + vis.getData().get(YEAR)
                                        + "\nCorretor responsável: " + vis.getCorretor().getNome() + " | Creci: " + vis.getCorretor().getCreci()
                                        + "\nComprador visitante: " + vis.getComprador().getNome() + " | CPF: " + vis.getComprador().getCpf();

                            } else {
                                str += "\nNenhuma visita neste período";
                            }
                        }

                        ArrayList<Proposta> arrayPropostas = aux.getListaPropostas();
                        for (Proposta prop : arrayPropostas) {
                            if (prop.getData().after(inicio) && prop.getData().before(fim)) {
                                if (str != "") {
                                    str += "\n\n";
                                }
                                str += "Proposta efetuada pelo comprador: " + prop.getComprador().getNome() + " | CPF: " + prop.getComprador().getCpf()
                                        + " no dia: " + prop.getData().get(DAY_OF_MONTH) + "/" + prop.getData().get(MONTH) + "/" + prop.getData().get(YEAR)
                                        + "\nMediação do corretor: " + prop.getCorretor().getNome() + " | Creci: " + prop.getCorretor().getCreci()
                                        + "\nA proposta tem valor: R$" + prop.getValor()
                                        + "\nSeu estado é: " + prop.getEstado();
                            } else {
                                str += "\nNenhuma proposta neste período";
                            }
                        }
                    }
                }
                if (str.equals("")) {
                    JOptionPane.showMessageDialog(null, "Nenhum evento neste período ou imóvel não encontrado!");
                } else {
                    JOptionPane.showMessageDialog(null, str);
                }

            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel cards = objLimPrincipal.cards;
                CardLayout principal = (CardLayout) (cards.getLayout());
                principal.show(cards, "Tela Principal");
            }
        });

    }

    //======================================================================================================
    //Case 5:
    private void relatorioVendas() {

        // Caledario
        UtilCalendarModel modelI = new UtilCalendarModel();
        Properties pI = new Properties();
        pI.put("text.today", "Today");
        pI.put("text.month", "Month");
        pI.put("text.year", "Year");
        UtilCalendarModel modelF = new UtilCalendarModel();
        Properties pF = new Properties();
        pF.put("text.today", "Today");
        pF.put("text.month", "Month");
        pF.put("text.year", "Year");
        JDatePanelImpl datePanelInicial = new JDatePanelImpl(modelI, pI);
        JDatePanelImpl datePanelFinal = new JDatePanelImpl(modelF, pF);

        JDatePickerImpl datePickerInicial = new JDatePickerImpl(datePanelInicial, new DateComponentFormatter());
        JDatePickerImpl datePickerFinal = new JDatePickerImpl(datePanelFinal, new DateComponentFormatter());


        // JLabel
        JLabel texDataIni = new JLabel("Escolha a data inicial:");
        JLabel texDataFim = new JLabel("Escolha a data final:");
        JButton Gerar = new JButton("Gerar Relatorio");
        JTextArea area = new JTextArea("");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p6 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p1.add(texDataIni);
        p2.add(datePickerInicial);
        p3.add(texDataFim);
        p4.add(datePickerFinal);
        p5.add(Gerar);
        p6.add(area);

        this.add(Box.createVerticalGlue());
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(p5);
        this.add(p6);
        this.add(Box.createVerticalGlue());

        Gerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar inicio = (Calendar) datePickerInicial.getModel().getValue();
                Calendar fim = (Calendar) datePickerFinal.getModel().getValue();
                area.setText(objCtrPrincipal.getObjCtrImovel().relatorioVenda(inicio, fim));
            }
        });
    }

    //======================================================================================================
    //Case 6
    private void imoveisVendedor() {

        JLabel textCPF = new JLabel("Digite o CPF do Vendedor: ");
        JTextField CPF = new JTextField(30);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JButton GerarListagem = new JButton("Gerar Listagem");
        JTextArea area2 = new JTextArea("");

        p1.add(textCPF);
        p2.add(CPF);
        p3.add(GerarListagem);
        p4.add(area2);

        this.add(Box.createVerticalGlue());
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(Box.createVerticalGlue());

        GerarListagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                area2.setText(objCtrPrincipal.getObjCtrImovel().RelatorioImoveisVendedor(CPF.getText()));
            }
        });

    }
    
    //======================================================================================================
    //Case 7
    private void totalFaturado() {
// Botões, um para calcular o valor e outro para atualziar a lista de corretores
        JButton BtCalcular = new JButton("Calcular valor total");
        JButton BtVoltar = new JButton("Voltar");

        // Area de exibição de texto
        JTextArea total = new JTextArea();

        // JLabels para informar o campo para o usuario digitar 
        JLabel dataInicial = new JLabel("Selecione a data inicial: ");
        JLabel dataFinal = new JLabel("Selecione a data final: ");

       
        UtilCalendarModel modelI = new UtilCalendarModel();
        Properties pI = new Properties();
        pI.put("text.today", "Today");
        pI.put("text.month", "Month");
        pI.put("text.year", "Year");
        UtilCalendarModel modelF = new UtilCalendarModel();
        Properties pF = new Properties();
        pF.put("text.today", "Today");
        pF.put("text.month", "Month");
        pF.put("text.year", "Year");
        JDatePanelImpl datePanelInicial = new JDatePanelImpl(modelI, pI);
        JDatePanelImpl datePanelFinal = new JDatePanelImpl(modelF, pF);
        
        JDatePickerImpl datePickerInicial = new JDatePickerImpl(datePanelInicial, new DateComponentFormatter());
        JDatePickerImpl datePickerFinal = new JDatePickerImpl(datePanelFinal, new DateComponentFormatter());
 
        this.setLayout(new BorderLayout());
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        
        // Adicionando os botões na tela
        p1.add(dataInicial);
        p1.add(datePickerInicial);
        p2.add(dataFinal);
        p2.add(datePickerFinal);
        p3.add(BtCalcular);
        p3.add(BtVoltar);
        p4.add(total);
        
        JPanel pStart = new JPanel();
        pStart.setLayout(new BoxLayout(pStart, BoxLayout.Y_AXIS));
        pStart.add(p1);
        pStart.add(p2);
        pStart.add(p3);
        pStart.add(p4);
        this.add(pStart, BorderLayout.PAGE_START);

        // Definindo a ação do botão BtCalcular, que vai colher os dados e chamar a função para calcular o valor total
        BtCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar inicio = (Calendar) datePickerInicial.getModel().getValue();
                Calendar fim = (Calendar) datePickerFinal.getModel().getValue();
                
                total.setText(objCtrPrincipal.getObjCtrImovel().TotalFat(objCtrPrincipal.getObjCtrImovel().getListaImovel(), inicio, fim));
            }
        });
        BtVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JPanel cards = objLimPrincipal.cards;
                CardLayout principal = (CardLayout) (cards.getLayout());
                principal.show(cards, "Tela Principal");
            }
        });

    }

    //======================================================================================================
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == listaImovel) {
            if (e.getValueIsAdjusting() == false) {
                if (listaImovel.getSelectedIndex() != -1) {
                    String index = (String) listaImovel.getSelectedValue();
                    String x;
                    for (int i = 8;; i++) {
                        if (index.charAt(i) == ' ') {
                            x = index.substring(8, i - 1);
                            break;
                        }
                    }
                    int codigo = Integer.parseInt(x);

                    ImageIcon icon = null;
                    for (Imovel imov : objCtrPrincipal.getObjCtrImovel().getListaImovel()) {
                        if (imov.getCodigo() == codigo) {
                            icon = new ImageIcon("images/" + codigo) {
                            };
                            Image image = icon.getImage();
                            Image newimg = image.getScaledInstance(240, 240, java.awt.Image.SCALE_SMOOTH);
                            icon = new ImageIcon(newimg);
                            selecionado = imov;
                            break;
                        }
                    }
                    JFrame telaImovel = new JFrame("Imovel: " + codigo);
                    telaImovel.setSize(600, 400);
                    telaImovel.setResizable(false);
                    telaImovel.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    telaImovel.setLocationRelativeTo(null);
                    telaImovel.setVisible(true);
                    telaImovel.setLayout(new GridLayout(1, 2));

                    JPanel imagem = new JPanel(new BorderLayout());
                    imagem.add(new JLabel(icon), BorderLayout.CENTER);
                    JPanel desc = new JPanel();

                    JLabel lblCodigo = new JLabel("Código do imóvel: ");
                    JTextArea txtCodigo = new JTextArea("" + selecionado.getCodigo());
                    txtCodigo.setEditable(false);
                    JLabel lblDescricao = new JLabel("Descrição do imóvel: ");
                    JTextArea txtDescricao = new JTextArea(selecionado.getDescricao());
                    txtDescricao.setEditable(false);
                    JLabel lblPreco = new JLabel("Preço do imóvel: ");
                    JTextArea txtPreco = new JTextArea("" + selecionado.getPreco());
                    txtPreco.setEditable(false);
                    //JLabel lblEndereco = new JLabel("Endereço do imóvel: ");
                    //JTextArea txtEndereco = new JTextArea(selecionado.getEndereco());

                    JButton jbProposta = new JButton("Fazer proposta");
                    JButton jbVisita = new JButton("Agendar visita");

                    desc.setLayout(new BoxLayout(desc, BoxLayout.Y_AXIS));
                    JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    //JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));

                    p1.add(lblCodigo);
                    p1.add(txtCodigo);
                    p2.add(lblDescricao);
                    p2.add(txtDescricao);
                    p3.add(lblPreco);
                    p3.add(txtPreco);
                    //p4.add(lblEndereco);
                    //p4.add(txtEndereco);
                    p5.add(jbProposta);
                    p5.add(jbVisita);

                    desc.add(Box.createVerticalGlue());
                    desc.add(p1);
                    desc.add(p2);
                    desc.add(p3);
                    //this.add(p4);
                    desc.add(p5);
                    desc.add(Box.createVerticalGlue());

                    telaImovel.add(imagem);
                    telaImovel.add(desc);

                    jbProposta.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                objCtrPrincipal.criarControleProposta(selecionado);
                            } catch (Exception error) {
                            }
                        }
                    });
                    jbVisita.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                objCtrPrincipal.criarControleVisita(selecionado);
                            } catch (Exception error) {
                            }
                        }
                    });
                }
            }

        } else if (e.getSource() == listaPropostas) {
            if (e.getValueIsAdjusting() == false) {
                Imovel imov = objCtrPrincipal.getObjCtrImovel().getListaImovel().get(listaPropostas.getSelectedIndex());
                String str[] = new String[imov.getListaPropostas().size()];

                if (imov.getListaPropostas().size() == 0) {
                    JOptionPane.showMessageDialog(null, "O imovel não possui propostas pendentes.");
                } else {
                    JFrame telaproposta = new JFrame("Lista de Propostas");
                    telaproposta.setSize(600, 400);
                    telaproposta.setResizable(false);
                    telaproposta.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    telaproposta.setLocationRelativeTo(null);
                    telaproposta.setVisible(true);

                    for (int i = 0; i < imov.getListaPropostas().size(); i++) {
                        if (imov.getListaPropostas().get(i).getEstado().equals(Util.SUBMETIDA)) {
                            str[i] = "O comprador: " + imov.getListaPropostas().get(i).getComprador().getNome() + " ofereceu R$" + imov.getListaPropostas().get(i).getValor();
                        }
                    }
                    JButton jbAceitar = new JButton("Aceitar proposta");
                    JButton jbRecusar = new JButton("Recusar proposta");
                    JButton jbVoltar = new JButton("Voltar");
                    JList jl = new JList(str);

                    JPanel jpPrincipal = new JPanel();

                    jpPrincipal.setLayout(new BoxLayout(jpPrincipal, BoxLayout.Y_AXIS));
                    JPanel p0 = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));

                    JLabel lblMsg = new JLabel("Selecione uma das propostas da lista abaixo para aceitá-la ou rejeitá-la");

                    p0.add(lblMsg);
                    p1.add(jl);
                    p2.add(jbAceitar);
                    p2.add(jbRecusar);
                    p2.add(jbVoltar);

                    jpPrincipal.add(Box.createVerticalGlue());
                    jpPrincipal.add(p0);
                    jpPrincipal.add(p1);
                    jpPrincipal.add(p2);
                    jpPrincipal.add(Box.createVerticalGlue());

                    telaproposta.add(jpPrincipal);

                    jbAceitar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (jl.getSelectedIndex() != -1) {
                                imov.aceitaProposta(imov.getListaPropostas().get(jl.getSelectedIndex()));
                                telaproposta.dispatchEvent(new WindowEvent(telaproposta, WindowEvent.WINDOW_CLOSING));
                            }
                        }
                    });
                    jbRecusar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (jl.getSelectedIndex() != -1) {
                                imov.getListaPropostas().get(jl.getSelectedIndex()).setEstado(Util.REJEITADA);
                                telaproposta.dispatchEvent(new WindowEvent(telaproposta, WindowEvent.WINDOW_CLOSING));
                            }
                        }
                    });
                    jbVoltar.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            telaproposta.dispatchEvent(new WindowEvent(telaproposta, WindowEvent.WINDOW_CLOSING));
                        }
                    });
                }
            }
        }

    }
}
