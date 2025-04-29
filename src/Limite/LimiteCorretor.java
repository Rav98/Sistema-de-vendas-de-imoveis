package Limite;

import Controle.ControlePrincipal;
import Modelo.Corretor;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Properties;
import javax.swing.*;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.UtilCalendarModel;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.JDatePanelImpl;

class LimiteCorretor extends JPanel {

    // Link para o limite principal e Controle principal
    private LimitePrincipal objLimPrincipal;
    private ControlePrincipal objCtrPrincipal;

    // Construtor
    public LimiteCorretor(ControlePrincipal objCtrPrin, LimitePrincipal objLimPrin, int operacao){
        objLimPrincipal = objLimPrin;
        objCtrPrincipal = objCtrPrin;

        // Configuração Layout
        this.setSize(720, 480);
       
        // Chamar as funções de acordo com a função que chama o cosntrutor
        switch (operacao) {
            case 1:
                cadastrarCorretor();
                break;
            case 2:
                ValorTotalCorretor();
                break;
            case 3:
                VisitasCorretor();
                break;
            default:
                break;
        }
    }

    //Função que calcula o valor total que cada corretor vai receber de comissão
    private void ValorTotalCorretor(){

        // Botões, um para calcular o valor e outro para atualziar a lista de corretores
        JButton BtCalcular = new JButton("Calcular valor total");
        JButton BtVoltar = new JButton("Voltar");

        // Area de exibição de texto
        JTextArea total = new JTextArea();

        // JLabels para informar o campo para o usuario digitar 
        JLabel CRECI = new JLabel("Selecione o corretor desejado: ");
        JLabel dataInicial = new JLabel("Selecione a data inicial: ");
        JLabel dataFinal = new JLabel("Selecione a data final: ");

        // JText para digitar os campos informados pelo JLabels
        JComboBox cmbCorretor = new JComboBox(objCtrPrincipal.getObjCtrCorretor().TodosCorretores());
       
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
        JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        
        // Adicionando os botões na tela
        p1.add(CRECI);
        p1.add(cmbCorretor);
        p2.add(dataInicial);
        p2.add(datePickerInicial);
        p3.add(dataFinal);
        p3.add(datePickerFinal);
        p4.add(BtCalcular);
        p4.add(BtVoltar);
        p5.add(total);
        
        JPanel pStart = new JPanel();
        pStart.setLayout(new BoxLayout(pStart, BoxLayout.Y_AXIS));
        pStart.add(p1);
        pStart.add(p2);
        pStart.add(p3);
        pStart.add(p4);
        this.add(pStart, BorderLayout.PAGE_START);
        this.add(p5, BorderLayout.CENTER);

        // Definindo a ação do botão BtCalcular, que vai colher os dados e chamar a função para calcular o valor total
        BtCalcular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar inicio = (Calendar) datePickerInicial.getModel().getValue();
                Calendar fim = (Calendar) datePickerFinal.getModel().getValue();
                Corretor c = objCtrPrincipal.getObjCtrCorretor().getArrayCorretor().get(cmbCorretor.getSelectedIndex());
                
                total.setText(objCtrPrincipal.getObjCtrCorretor().TotalFatCorretor(objCtrPrincipal.getObjCtrImovel().getListaImovel(), 
                        c, inicio, fim));
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

    private void VisitasCorretor() {
        // Botões, um para calcular o valor e outro para atualziar a lista de corretores
        JButton BtVisitas = new JButton("Listar Visitas");
        JButton BtVoltar = new JButton("Voltar");

        // Area de exibição de texto
        JTextArea total = new JTextArea();

        // JLabels para informar o campo para o usuario digitar 
        JLabel CRECI = new JLabel("Selecione o CRECI do Corretor desejado: ");
        JLabel dataInicial = new JLabel("Selecione a data inicial: ");
        JLabel dataFinal = new JLabel("Selecione a data final: ");

        // JText para digitar os campos informados pelo JLabels
        JComboBox cmbCorretor = new JComboBox(objCtrPrincipal.getObjCtrCorretor().TodosCorretores());
        
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

        // Adicionando os botões na tela
        this.setLayout(new BorderLayout());
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        
        // Adicionando os botões na tela
        p1.add(CRECI);
        p1.add(cmbCorretor);
        p2.add(dataInicial);
        p2.add(datePickerInicial);
        p3.add(dataFinal);
        p3.add(datePickerFinal);
        p4.add(BtVisitas);
        p4.add(BtVoltar);
        p5.add(total);
        
        JPanel pStart = new JPanel();
        pStart.setLayout(new BoxLayout(pStart, BoxLayout.Y_AXIS));
        pStart.add(p1);
        pStart.add(p2);
        pStart.add(p3);
        pStart.add(p4);
        this.add(pStart, BorderLayout.PAGE_START);
        this.add(p5, BorderLayout.CENTER);


        // Definindo a ação do botão BtVisitas, que vai colher os dados e chamar a função para mostrar as visitas
        BtVisitas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar inicio = (Calendar) datePickerInicial.getModel().getValue();
                Calendar fim = (Calendar) datePickerFinal.getModel().getValue();
                Corretor c = objCtrPrincipal.getObjCtrCorretor().getArrayCorretor().get(cmbCorretor.getSelectedIndex());
                
                String listagem = objCtrPrincipal.getObjCtrCorretor().VisitasCorretor
                                (objCtrPrincipal.getObjCtrImovel().getListaImovel(),c, inicio, fim);
                if(listagem.equals("")){
                    JOptionPane.showMessageDialog(null, "Não há visitas no periodo selecionado.");
                }else{
                    total.setText(listagem);
                }
                
                
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

    // Função para cadastrar os corretores no sistema
    private void cadastrarCorretor() {

        // JLabels para informar o significado do campo JText 
        JLabel lblCPF = new JLabel("Digite o CPF: ");
        JTextField txtCPF = new JTextField(15);
        JLabel lblNome = new JLabel("Digite o Nome: ");
        JTextField txtNome = new JTextField(15);
        JLabel lblEmail = new JLabel("Digite o Email: ");
        JTextField txtEmail = new JTextField(15);
        JLabel lblFone = new JLabel("Digite o Telefone: ");
        JTextField txtFone = new JTextField(15);
        JLabel lblCreci = new JLabel("Digite o CRECI: ");
        JTextField txtCreci = new JTextField(15);
        JLabel lblCorretagem = new JLabel("Digite a porcentagem de corretagem: ");
        JTextField txtCorretagem = new JTextField(15);

        // Botões 
        JButton jbCadastrar = new JButton("Cadastrar");
        JButton jbVoltar = new JButton("Voltar");

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
 
        p1.add(lblCPF);
        p1.add(txtCPF);
        p2.add(lblNome);
        p2.add(txtNome);
        p3.add(lblEmail);
        p3.add(txtEmail);
        p4.add(lblFone);
        p4.add(txtFone);
        p5.add(lblCreci);
        p5.add(txtCreci);
        p6.add(lblCorretagem);
        p6.add(txtCorretagem);
        p7.add(jbCadastrar);
        p7.add(jbVoltar);
        this.add(Box.createVerticalGlue());
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(p5);
        this.add(p6);
        this.add(p7);
        this.add(Box.createVerticalGlue());
        

        // Definindo a ação do botão jbCadastrar, que vai cadastrar novos Corretores
        jbCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtCPF.getText().isEmpty() || txtNome.getText().isEmpty() || txtEmail.getText().isEmpty() || txtFone.getText().isEmpty() 
                        || txtCreci.getText().isEmpty() || txtCorretagem.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                }
                else{
                    objCtrPrincipal.getObjCtrCorretor().criaCorretor(txtCPF.getText(), txtNome.getText(),
                            txtEmail.getText(), txtFone.getText(), txtCreci.getText(), Double.parseDouble(txtCorretagem.getText()));
                    JOptionPane.showMessageDialog(null, "Corretor cadastrado!");

                    JPanel cards = objLimPrincipal.cards;
                    CardLayout cardCadComprador = (CardLayout) (cards.getLayout());
                    cardCadComprador.show(cards, "Tela Principal");
                }
            }
        });

        // Definindo a ação do botão jbVoltar, para voltar a tela inicial
        jbVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                JPanel cards = objLimPrincipal.cards;
                CardLayout cardCadComprador = (CardLayout) (cards.getLayout());
                cardCadComprador.show(cards, "Tela Principal");
            }
        });
    }
}
