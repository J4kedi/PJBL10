import javax.swing.*;
import classes.Produto;
import java.awt.*;

public class CardPanel extends JPanel {
    public CardPanel(Produto p) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 200));

        JLabel labelImagem = new JLabel(new ImageIcon(p.getIMAGE_PATH()));
        labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelImagem, BorderLayout.CENTER);

        JLabel labelNome = new JLabel(p.getNome());
        labelNome.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelNome, BorderLayout.SOUTH);
        
        JLabel labelDescricao = new JLabel(p.getDescricao());
        labelDescricao.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelDescricao, BorderLayout.SOUTH);
        
        JLabel labelPreco = new JLabel(String.valueOf(p.getPreco()));
        labelPreco.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelPreco, BorderLayout.SOUTH);

        JLabel labelAvaliacao = new JLabel(String.valueOf(p.getAvaliacao()));
        labelAvaliacao.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelAvaliacao, BorderLayout.SOUTH);
        
        setBackground(Color.decode("#27636A"));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}