import javax.swing.*;
import java.awt.*;
import classes.Produto;

public class CardPanel extends JPanel {
    public CardPanel(Produto p) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 160));

        // Redimensionar a imagem
        ImageIcon originalIcon = new ImageIcon(p.getIMAGE_PATH());
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel labelImagem = new JLabel(resizedIcon);
        labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelImagem, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(5, 1));
        infoPanel.setBackground(Color.decode("#06BF6A"));
        
        JLabel labelNome = new JLabel(p.getNome());
        labelNome.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(labelNome);
        
        JLabel labelDescricao = new JLabel(p.getDescricao());
        labelDescricao.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(labelDescricao);
        
        JLabel labelPreco = new JLabel(String.valueOf(p.getPreco()));
        labelPreco.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(labelPreco);

        JLabel labelAvaliacao = new JLabel(String.valueOf(p.getAvaliacao()));
        labelAvaliacao.setHorizontalAlignment(SwingConstants.CENTER);
        infoPanel.add(labelAvaliacao);

        JButton botaoComprar = new JButton("Comprar");
        botaoComprar.setHorizontalAlignment(SwingConstants.CENTER);
        botaoComprar.setPreferredSize(new java.awt.Dimension(200, 30));
        botaoComprar.setBackground(Color.decode("#402232"));
        botaoComprar.setForeground(Color.DARK_GRAY);
        infoPanel.add(botaoComprar);
        
        add(infoPanel, BorderLayout.SOUTH);

        setBackground(Color.decode("#27636A"));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
