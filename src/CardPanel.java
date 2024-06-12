import javax.swing.*;
import java.awt.*;
import classes.Carrinho;
import classes.Produto;
import services.GerenciarDados;

public class CardPanel extends JPanel {
    public CardPanel(Produto p, Carrinho c, JFrame parent, GerenciarDados gerencia) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 160));

        ImageIcon originalIcon = new ImageIcon(p.getIMAGE_PATH());
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel labelImagem = new JLabel(resizedIcon);
        labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelImagem, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(5, 1));
        infoPanel.setBackground(Color.decode("#06BF6A"));
        
        JLabel labelNome = new JLabel("Nome: " + p.getNome());
        labelNome.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(labelNome);
        
        JLabel labelDescricao = new JLabel("Descrição: " + p.getDescricao());
        labelDescricao.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(labelDescricao);
        
        JLabel labelPreco = new JLabel("Preço: " + String.valueOf(p.getPreco()));
        labelPreco.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(labelPreco);

        JLabel labelAvaliacao = new JLabel("Avaliação: " + String.valueOf(p.getAvaliacao()));
        labelAvaliacao.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(labelAvaliacao);

        JButton botaoComprar = new JButton("Comprar");
        botaoComprar.setHorizontalAlignment(SwingConstants.CENTER);
        botaoComprar.setPreferredSize(new java.awt.Dimension(200, 30));
        botaoComprar.setBackground(Color.decode("#402232"));
        botaoComprar.setForeground(Color.WHITE);
        infoPanel.add(botaoComprar);

        botaoComprar.addActionListener(e -> {
            c.adicionarProduto(p);
            gerencia.salvarCarrinho(c);
            JOptionPane.showMessageDialog(parent, "Produto adicionado ao carrinho!");
        });
        
        add(infoPanel, BorderLayout.SOUTH);

        setBackground(Color.decode("#27636A"));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
