import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import classes.Produto;
import classes.Usuario;
import services.GerenciarDados;

public class Cardapio {
    private JFrame frame = new JFrame("Pizzaria Java");
    private GerenciarDados gerencia;
    private Usuario usuario;
    private MenuPanel menuPanel;

    public Cardapio(Usuario usuario, JFrame mainFrame, GerenciarDados gerencia) {
        this.gerencia = gerencia;
        this.usuario = usuario;
        menuPanel = new MenuPanel(usuario.getNivelPermissao(), mainFrame, gerencia);
        menuPanel.setBounds(0, 0, 800, 60);

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(menuPanel.getCorFundo());
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);
        
        frame.add(menuPanel);

        exibirCardapio();
    }

    private void exibirCardapio() {
        JPanel cardapioPanel = new JPanel();
        HashMap<Integer, Produto> produtos = gerencia.getProdutos();

        cardapioPanel.setLayout(new GridLayout(produtos.size() % 3, 3, 20, 20));
        cardapioPanel.setBounds(10, 70, 780, 490);
        cardapioPanel.setBackground(menuPanel.getCorFundo());

        for (HashMap.Entry<Integer, Produto> p : produtos.entrySet()) {
            Produto produto = p.getValue();
            CardPanel card = new CardPanel(produto);
            cardapioPanel.add(card);
        }

        frame.add(cardapioPanel);
    }
}