import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import classes.Carrinho;
import classes.Produto;
import classes.Usuario;
import services.GerenciarDados;

public class Cardapio {
    private JFrame frame = new JFrame("Pizzaria Java");
    private GerenciarDados gerencia;
    private MenuPanel menuPanel;
    private Carrinho carrinho;

    public Cardapio(Usuario usuario, JFrame mainFrame, GerenciarDados gerencia) {
        this.gerencia = gerencia;
        carrinho = gerencia.obterCarrinho(usuario.getId());

        if (carrinho == null) {
            carrinho = new Carrinho(usuario.getId());
        }

        menuPanel = new MenuPanel(usuario.getNivelPermissao(), mainFrame, gerencia, carrinho);
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
        int tamanhoHash = produtos.size();

        cardapioPanel.setLayout(new GridLayout(Math.round(tamanhoHash / 2), 3, 15, 15));
        cardapioPanel.setBounds(10, 70, 760, 480);
        cardapioPanel.setBackground(menuPanel.getCorFundo());

        for (HashMap.Entry<Integer, Produto> p : produtos.entrySet()) {
            Produto produto = p.getValue();
            CardPanel card = new CardPanel(produto, carrinho, frame, gerencia);
            cardapioPanel.add(card);
        }

        frame.add(cardapioPanel);
    }
}