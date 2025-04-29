package Limite;

import Controle.*;
import Modelo.Comprador;
import Modelo.Corretor;
import Modelo.Imovel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.*;

public class LimiteProposta {

    private ControlePrincipal objControlePrincipal;

    public LimiteProposta(ControlePrincipal objCtrPrin, Imovel imov) {
        JFrame proposta = new JFrame("Proposta");
        proposta.setSize(550, 280);
        proposta.setResizable(false);
        proposta.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        proposta.setLocationRelativeTo(null);
        proposta.setVisible(true);
        objControlePrincipal = objCtrPrin;

        // Passando o array do controle
        ArrayList<Comprador> arrayComprador = objControlePrincipal.getObjCtrComprador().getarrayComprador();
        ArrayList<Corretor> arrayCorretor = objControlePrincipal.getObjCtrCorretor().getArrayCorretor();

        // Tela ---------------------------------------------------------------
        // Campo para pegar texto na interface     
        JTextField textValor = new JTextField(20);

        // Combox corretor e comprador
        String str1[] = new String[arrayComprador.size()];
        String str2[] = new String[arrayCorretor.size()];

        for (int i = 0; i < arrayComprador.size(); i++) {
            str1[i] = ("\nNome:" + arrayComprador.get(i).getNome() + " - CPF:" + arrayComprador.get(i).getCpf());
        }

        for (int i = 0; i < arrayCorretor.size(); i++) {
            str2[i] = ("\nNome: " + arrayCorretor.get(i).getNome() + "  - CRECI: " + arrayCorretor.get(i).getCreci());
        }

        JComboBox comboComprador = new JComboBox(str1);
        JComboBox comboCorretor = new JComboBox(str2);

        //Texto definido para aparecer na interface 
        JLabel Valor = new JLabel("Digite o valor proposto: ");
        JLabel Comprador = new JLabel("Selecione um comprador: ");
        JLabel Corretor = new JLabel("Selecione um corretor: ");
        JButton Registrar = new JButton("Registrar Proposta: ");
        JButton Cancelar = new JButton("Cancelar");

        JPanel propostaPanel = new JPanel();
        propostaPanel.setLayout(new BoxLayout(propostaPanel, BoxLayout.Y_AXIS));
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel p4 = new JPanel(new FlowLayout(FlowLayout.CENTER));

        p1.add(Comprador);
        p1.add(comboComprador);
        p2.add(Corretor);
        p2.add(comboCorretor);
        p3.add(Valor);
        p3.add(textValor);
        p4.add(Registrar);
        p4.add(Cancelar);

        propostaPanel.add(Box.createVerticalGlue());
        propostaPanel.add(p1);
        propostaPanel.add(p2);
        propostaPanel.add(p3);
        propostaPanel.add(p4);
        propostaPanel.add(Box.createVerticalGlue());
        proposta.add(propostaPanel);

        // Atividade do botão
        Registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textValor.getText().isEmpty() || arrayComprador.isEmpty() || arrayCorretor.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                } else {
                    JOptionPane.showMessageDialog(null, "Proposta feita!");
                    objCtrPrin.getObjCtrProposta().criaProposta(imov, Calendar.getInstance(), arrayComprador.get(comboComprador.getSelectedIndex()), arrayCorretor.get(comboCorretor.getSelectedIndex()), Double.parseDouble(textValor.getText()));
                    proposta.dispatchEvent(new WindowEvent(proposta, WindowEvent.WINDOW_CLOSING));
                }
            }
        });
        Cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Cancelado!");
                proposta.dispatchEvent(new WindowEvent(proposta, WindowEvent.WINDOW_CLOSING));
            }
        });
    }
}
