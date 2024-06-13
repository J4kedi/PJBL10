import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import classes.Carrinho;
import classes.Produto;
import services.CarrinhoVazioException;
import services.GerenciarDados;

public class ExibirCarrinho extends JDialog {
    private GerenciarDados gerencia;
    private Carrinho carrinho;
    private JPanel carrinhoPanel;

    public ExibirCarrinho(JFrame parent, GerenciarDados gerencia, Carrinho carrinho) {
        super(parent, "Carrinho", true);
        this.gerencia = gerencia;
        this.carrinho = carrinho;

        setLayout(new BorderLayout());

        carrinhoPanel = new JPanel();
        carrinhoPanel.setLayout(new GridLayout(0, 1, 10, 10));
        carrinhoPanel.setBackground(Color.decode("#06ADBF"));

        atualizarCarrinho();

        JPanel controlePanel = new JPanel();
        controlePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        controlePanel.setBackground(Color.decode("#06ADBF"));

        JButton botaoComprarTudo = new JButton("Comprar Tudo");
        botaoComprarTudo.setPreferredSize(new Dimension(200, 30));
        botaoComprarTudo.setBackground(Color.decode("#402232"));
        botaoComprarTudo.setForeground(Color.WHITE);
        botaoComprarTudo.addActionListener(e -> {
            try {
                if (carrinho.getProdutos().isEmpty()) {
                    throw new CarrinhoVazioException();
                }
                carrinho.limpar();
                JOptionPane.showMessageDialog(parent, "Todos os produtos foram comprados!");
                gerencia.salvarCarrinho(carrinho);
                dispose();
            } catch (CarrinhoVazioException ce) {
                JOptionPane.showMessageDialog(parent, "Carrinho vazio, favor adicionar itens a ele, antes de comprar!");
                ce.printStackTrace();
            }
        });

        controlePanel.add(botaoComprarTudo);

        add(new JScrollPane(carrinhoPanel), BorderLayout.CENTER);
        add(controlePanel, BorderLayout.SOUTH);

        setSize(500, 600);
        setLocationRelativeTo(parent);
        setResizable(false);
        setVisible(true);
    }

    private void atualizarCarrinho() {
        carrinhoPanel.removeAll();
        List<Produto> produtos = carrinho.getProdutos();

        for (Produto produto : produtos) {
            JPanel produtoPanel = new JPanel(new BorderLayout());
            produtoPanel.setPreferredSize(new Dimension(300, 150));
            produtoPanel.setBackground(Color.decode("#06BF6A"));
            produtoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            ImageIcon originalIcon = new ImageIcon(produto.getIMAGE_PATH());
            Image originalImage = originalIcon.getImage();
            Image resizedImage = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(resizedImage);
            JLabel labelImagem = new JLabel(resizedIcon);
            labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
            produtoPanel.add(labelImagem, BorderLayout.WEST);

            JPanel infoPanel = new JPanel(new GridLayout(4, 1));
            infoPanel.setBackground(Color.decode("#06BF6A"));
            
            JLabel labelNome = new JLabel("Nome: " + produto.getNome());
            labelNome.setHorizontalAlignment(SwingConstants.LEFT);
            infoPanel.add(labelNome);

            JLabel labelDescricao = new JLabel("Descrição: " + produto.getDescricao());
            labelDescricao.setHorizontalAlignment(SwingConstants.LEFT);
            infoPanel.add(labelDescricao);

            JLabel labelPreco = new JLabel("Preço: " + String.valueOf(produto.getPreco()));
            labelPreco.setHorizontalAlignment(SwingConstants.LEFT);
            infoPanel.add(labelPreco);

            JButton botaoRemover = new JButton("Remover");
            botaoRemover.setHorizontalAlignment(SwingConstants.CENTER);
            botaoRemover.setPreferredSize(new Dimension(100, 30));
            botaoRemover.setBackground(Color.decode("#402232"));
            botaoRemover.setForeground(Color.WHITE);
            botaoRemover.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) throws ClassCastException {
                    carrinho.removerProduto(produto);
                    gerencia.salvarCarrinho(carrinho);
                    atualizarCarrinho();
                    carrinhoPanel.revalidate();
                    carrinhoPanel.repaint();
                }
            });

            infoPanel.add(botaoRemover);

            produtoPanel.add(infoPanel, BorderLayout.CENTER);
            carrinhoPanel.add(produtoPanel);
        }
    }
}