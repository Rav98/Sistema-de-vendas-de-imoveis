//imóveis por vendedor -> lista vendedores e seus imóveis

package Limite;

import Controle.ControlePrincipal;
import Modelo.Imovel;
import Modelo.Vendedor;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;

class LimiteVendedor extends JPanel{
    private LimitePrincipal objLimPrincipal;
    private ControlePrincipal objCtrPrincipal;
    File selectedFile = null, destination;
    ArrayList<Vendedor> vendedores = null;
    
    public LimiteVendedor(ControlePrincipal objCtrPrin, LimitePrincipal objLimPrin, int operacao) {
        objLimPrincipal = objLimPrin;
        objCtrPrincipal = objCtrPrin;
        vendedores=objCtrPrincipal.getObjCtrVendedor().getArrayVendedor();
        this.setSize(720, 480);
        
        switch(operacao){
            case 1:
                cadastrarVendedor();
                break;
            default:
                break;
        }
    }
    
    private void cadastrarVendedor(){
        String str[] = {"Indiferente", "Telefone", "Email", "Mensagem Whatsapp"};
        JLabel lblCPF = new JLabel("Digite o CPF : ");
        JTextField txtCPF = new JTextField(20);
        JLabel lblNome = new JLabel("Digite o nome :");
        JTextField txtNome = new JTextField(20);
        JLabel lblEmail = new JLabel("Digite o email :");
        JTextField txtEmail = new JTextField(20);
        JLabel lblFone = new JLabel("Digite o telefone :");
        JTextField txtFone = new JTextField(19);
        JLabel lblContato = new JLabel("Selecione o contato preferencial :");
        JComboBox cmbContato = new JComboBox(str); 
        JButton jbCadastrar = new JButton("Cadastrar");
        JButton jbVoltar = new JButton("Voltar");
        
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
       
        p1.add(lblCPF);
        p1.add(txtCPF);
        p2.add(lblNome);
        p2.add(txtNome);
        p3.add(lblEmail);
        p3.add(txtEmail);
        p4.add(lblFone);
        p4.add(txtFone);
        p5.add(lblContato);
        p5.add(cmbContato);
        p6.add(jbCadastrar);
        p6.add(jbVoltar);
        this.add(Box.createVerticalGlue());
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.add(p4);
        this.add(p5);
        this.add(p6);
        this.add(Box.createVerticalGlue());
        
        jbCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtCPF.getText().isEmpty() || txtNome.getText().isEmpty() || txtEmail.getText().isEmpty() || txtFone.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                }
                else{
                    objCtrPrincipal.getObjCtrVendedor().criaVendedor(txtCPF.getText(), txtNome.getText(), txtEmail.getText(), txtFone.getText(), (String) cmbContato.getSelectedItem());
                    JOptionPane.showMessageDialog(null, "Vendedor cadastrado");


                    JPanel cards = objLimPrincipal.cards;
                    CardLayout principal = (CardLayout) (cards.getLayout());
                    int opcao = JOptionPane.showConfirmDialog(null, "Deseja cadastrar um imovel para este vendedor? ");

                    if(opcao == JOptionPane.YES_OPTION){
                        LimiteImovel cadastrarImovel = new LimiteImovel(objCtrPrincipal, objLimPrincipal, 1);
                        cadastrarImovel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                        cards.add(cadastrarImovel, "Cadastrar Imovel");
                        principal.show(cards, "Cadastrar Imovel");
                    }
                    else{
                        principal.show(cards, "Tela Principal");
                    }
                }    
            }
        });
        jbVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                
                JPanel cards = objLimPrincipal.cards;
                CardLayout principal = (CardLayout) (cards.getLayout());
                principal.show(cards, "Tela Principal");
            }
        });
    }
    

}
