
package Limite;

import java.awt.*;
import javax.swing.*;
import Controle.ControlePrincipal;
import java.awt.event.*;

class LimiteComprador extends JPanel{
    private LimitePrincipal objLimPrincipal;
    private ControlePrincipal objCtrPrincipal;
    
    public LimiteComprador(ControlePrincipal objCtrPrin, LimitePrincipal objLimPrin, int operacao) {
        objLimPrincipal = objLimPrin;
        objCtrPrincipal = objCtrPrin;
        this.setSize(720, 480);
        
        switch(operacao){
            case 1:
                cadastrarComprador();
                break;
            default:
                break;
        }
    }
    
    private void cadastrarComprador(){
        String str[] = {"Indiferente", "Telefone", "Email", "Mensagem Whatsapp"};
        JLabel lblCPF = new JLabel("Digite o CPF: ");
        JTextField txtCPF = new JTextField(15);
        JLabel lblNome = new JLabel("Digite o nome: ");
        JTextField txtNome = new JTextField(15);
        JLabel lblEmail = new JLabel("Digite o email: ");
        JTextField txtEmail = new JTextField(15);
        JLabel lblFone = new JLabel("Digite o telefone: ");
        JTextField txtFone = new JTextField(15);
        JLabel lblContato = new JLabel("Selecione o contato preferencial: ");
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
                    objCtrPrincipal.getObjCtrComprador().criaComprador(txtCPF.getText(), txtNome.getText(), txtEmail.getText(), txtFone.getText(), (String) cmbContato.getSelectedItem());
                    JOptionPane.showMessageDialog(null, "Comprador cadastrado");


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
                CardLayout principal = (CardLayout) (cards.getLayout());
                principal.show(cards, "Tela Principal");
            }
        });
    } 
}
