import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import classes.Produto;
import classes.Usuario;
import services.GerenciarDados;

public class Cardapio {
    private MenuPanel menuPanel = new MenuPanel();
    private JFrame frame = new JFrame("Pizzaria Java");
    private GerenciarDados gerencia;

    public Cardapio(Usuario usuario, JFrame mainFrame, GerenciarDados gerencia) {
        this.gerencia = gerencia;

        menuPanel.setBounds(0, 0, 700, 60);

        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(menuPanel.getCorFundo());
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                mainFrame.setVisible(true);
            }
        });

        frame.add(menuPanel);
        exibirCardapio();
    }

    private void exibirCardapio() {
        JPanel cardapioPanel = new JPanel();
        HashMap<Integer, Produto> produtos = gerencia.getProdutos();

        cardapioPanel.setLayout(new GridLayout(produtos.size() % 3, 3, 20, 20));
        cardapioPanel.setBounds(10, 70, 680, 390);
        cardapioPanel.setBackground(menuPanel.getCorFundo());

        for (HashMap.Entry<Integer, Produto> p : produtos.entrySet()) {
            Produto produto = p.getValue();
            CardPanel card = new CardPanel(produto);
            cardapioPanel.add(card);
        }

        frame.add(cardapioPanel);
    }
}