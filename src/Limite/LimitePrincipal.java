package Limite;

import Controle.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LimitePrincipal extends JFrame {

    private ControlePrincipal objCtrPrincipal;
    public JPanel cards;

    public LimitePrincipal(ControlePrincipal objCtrPrin) {
        // Criação da JFrame
        super("IMOBILIARIA ItaHouse");
        this.setSize(800, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLayout(new BorderLayout());
        objCtrPrincipal = objCtrPrin;

        // Listener para fechar e serializar os dados
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                objCtrPrincipal.finalize();
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        // Criação do Menu ***************************************************************************************************
        JMenuBar menu = new JMenuBar();
        this.setJMenuBar(menu);

        JMenu compradorMenu = new JMenu("Comprador");
        menu.add(compradorMenu);
        JMenuItem cadComprador = new JMenuItem("Cadastrar");
        compradorMenu.add(cadComprador);

        JMenu corretorMenu = new JMenu("Corretor");
        menu.add(corretorMenu);
        JMenuItem cadCorretor = new JMenuItem("Cadastrar");
        JMenuItem valorTotalCorretor = new JMenuItem("Valor total faturado por corretor");
        JMenuItem visitasCorretor = new JMenuItem("Visitas por corretor");
        corretorMenu.add(cadCorretor);
        corretorMenu.add(valorTotalCorretor);
        corretorMenu.add(visitasCorretor);

        JMenu vendedorMenu = new JMenu("Vendedor");
        menu.add(vendedorMenu);
        JMenuItem cadVendedor = new JMenuItem("Cadastrar");
        vendedorMenu.add(cadVendedor);

        JMenu imovelMenu = new JMenu("Imovel");
        menu.add(imovelMenu);
        JMenuItem cadImovel = new JMenuItem("Cadastrar");
        JMenuItem catImovel = new JMenuItem("Catálogo");
        JMenuItem propImovel = new JMenuItem("Propostas pendentes");
        JMenuItem eventosImovel = new JMenuItem("Eventos por imóvel");
        imovelMenu.add(cadImovel);
        imovelMenu.add(catImovel);
        imovelMenu.add(propImovel);
        imovelMenu.add(eventosImovel);

        JMenu relatorioMenu = new JMenu("Relatorios");
        menu.add(relatorioMenu);
        JMenuItem imoveisVendedor = new JMenuItem("Listagem de imovel por vendedor");
        JMenuItem relatorioVendas = new JMenuItem("Vendas por periodo");
        JMenuItem valorTotal = new JMenuItem("Valor total faturado");
        relatorioMenu.add(imoveisVendedor);
        relatorioMenu.add(relatorioVendas);
        relatorioMenu.add(valorTotal);
        //***************************************************************************************************

        // Cards de ações
        cards = new JPanel(new CardLayout());
        this.add(cards, BorderLayout.CENTER);

        JPanel inicial = new JPanel(new BorderLayout());
        cards.add(inicial, "Tela Principal");

        JPanel auxiliar = new JPanel();
        auxiliar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cards.add(auxiliar, "Auxiliar");

        // Ações dos menus
        cadComprador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimiteComprador cadastrarComprador = new LimiteComprador(objCtrPrincipal, LimitePrincipal.this, 1);
                cadastrarComprador.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                cards.add(cadastrarComprador, "Cadastrar Comprador");
                CardLayout cardCadComprador = (CardLayout) (cards.getLayout());
                cardCadComprador.show(cards, "Cadastrar Comprador");
            }
        });

        cadCorretor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimiteCorretor cadastrarCorretor = null;

                cadastrarCorretor = new LimiteCorretor(objCtrPrincipal, LimitePrincipal.this, 1);

                cadastrarCorretor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                cards.add(cadastrarCorretor, "Cadastrar Corretor");
                CardLayout cardCadComprador = (CardLayout) (cards.getLayout());
                cardCadComprador.show(cards, "Cadastrar Corretor");
            }
        });

        cadVendedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimiteVendedor cadastrarVendedor = new LimiteVendedor(objCtrPrincipal, LimitePrincipal.this, 1);
                cadastrarVendedor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                cards.add(cadastrarVendedor, "Cadastrar Corretor");
                CardLayout cardCadVendedor = (CardLayout) (cards.getLayout());
                cardCadVendedor.show(cards, "Cadastrar Corretor");
            }
        });

        cadImovel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimiteImovel cadastrarImovel = new LimiteImovel(objCtrPrincipal, LimitePrincipal.this, 1);
                cadastrarImovel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                cards.add(cadastrarImovel, "Cadastrar Imovel");
                CardLayout cardCadImovel = (CardLayout) (cards.getLayout());
                cardCadImovel.show(cards, "Cadastrar Imovel");
            }
        });

        valorTotalCorretor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimiteCorretor totalcorretor = null;

                totalcorretor = new LimiteCorretor(objCtrPrincipal, LimitePrincipal.this, 2);

                totalcorretor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                cards.add(totalcorretor, "Valor total faturado por corretor");
                CardLayout valorTotalCorretor = (CardLayout) (cards.getLayout());
                valorTotalCorretor.show(cards, "Valor total faturado por corretor");
            }
        });

        visitasCorretor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimiteCorretor visitCorretor = null;

                visitCorretor = new LimiteCorretor(objCtrPrincipal, LimitePrincipal.this, 3);

                visitCorretor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                cards.add(visitCorretor, "Visitas por corretor");
                CardLayout visitasCorretor = (CardLayout) (cards.getLayout());
                visitasCorretor.show(cards, "Visitas por corretor");
            }
        });


        catImovel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimiteImovel catalogo = new LimiteImovel(objCtrPrincipal, LimitePrincipal.this, 2);
                catalogo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                cards.add(catalogo, "Imoveis Catalogo");
                CardLayout cardCatalogo = (CardLayout) (cards.getLayout());
                cardCatalogo.show(cards, "Imoveis Catalogo");
            }
        });

        propImovel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimiteImovel propostasImovel = new LimiteImovel(objCtrPrincipal, LimitePrincipal.this, 3);
                propostasImovel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                cards.add(propostasImovel, "Imoveis Propostas");
                CardLayout cardPropostas = (CardLayout) (cards.getLayout());
                cardPropostas.show(cards, "Imoveis Propostas");
            }
        });

        eventosImovel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimiteImovel eventosImovel = new LimiteImovel(objCtrPrincipal, LimitePrincipal.this, 4);
                eventosImovel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                cards.add(eventosImovel, "Imoveis Eventos");
                CardLayout cardEventos = (CardLayout) (cards.getLayout());
                cardEventos.show(cards, "Imoveis Eventos");
            }
        });

        relatorioVendas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimiteImovel vendasImovel = new LimiteImovel(objCtrPrincipal, LimitePrincipal.this, 5);
                vendasImovel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                cards.add(vendasImovel, "Imoveis Vendas");
                CardLayout cardVendasImovel = (CardLayout) (cards.getLayout());
                cardVendasImovel.show(cards, "Imoveis Vendas");
            }
        });

        imoveisVendedor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimiteImovel imoveisPorVendedor = new LimiteImovel(objCtrPrincipal, LimitePrincipal.this, 6);
                imoveisPorVendedor.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                cards.add(imoveisPorVendedor, "Imoveis Vendedor");
                CardLayout cardImoveisVendedor = (CardLayout) (cards.getLayout());
                cardImoveisVendedor.show(cards, "Imoveis Vendedor");
            }
        });
        valorTotal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LimiteImovel valorTotalVendas = new LimiteImovel(objCtrPrincipal, LimitePrincipal.this, 7);
                valorTotalVendas.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                cards.add(valorTotalVendas, "Valor Total");
                CardLayout cardValorTotal = (CardLayout) (cards.getLayout());
                cardValorTotal.show(cards, "Valor Total");
            }
        });
    }

}
