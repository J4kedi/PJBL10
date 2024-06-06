import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Collections;

public class MenuPanel extends JPanel {
    private Color corFundo = Color.decode("#06ADBF");
    private Color corTexto = Color.decode("#0B4359");
    private Color corLinhaMenu = Color.BLACK;
    private Color corJanelaAtual = Color.MAGENTA;
    private Font fontePadrao = new Font("SansSerif", Font.PLAIN, 20);

    public MenuPanel() {
        setLayout(null);
        setBackground(corFundo);

        JLabel labelTitulo = new JLabel("Pizzaria");
        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 38));
        labelTitulo.setBounds(5, 5, 140, 40);

        JLabelWithLine linhaMenu = new JLabelWithLine("");
        linhaMenu.setBounds(0, 35, 700, 20);
        linhaMenu.setLineColor(corLinhaMenu);
        
        JLabelWithLine labelCardapio = new JLabelWithLine("Cardápio");
        labelCardapio.setBounds(170, 20, 82, 25);
        labelCardapio.setLineColor(corJanelaAtual);
        labelCardapio.setFont(fontePadrao);
        
        JLabel labelCarrinho = new JLabel("Carrinho");
        labelCarrinho.setBounds(465, 20, 75, 25);
        labelCarrinho.setFont(fontePadrao);
        
        JLabel labelPerfil = new JLabel("Perfil");
        labelPerfil.setBounds(545, 20, 50, 25);
        labelPerfil.setFont(fontePadrao);

        JLabel labelLogout = new JLabel("Logout");
        labelLogout.setBounds(600, 20, 60, 25);
        labelLogout.setFont(fontePadrao);

        ArrayList<JLabel> labels = new ArrayList<>();
        Collections.addAll(labels, labelTitulo, linhaMenu, labelLogout, labelCardapio, labelCarrinho, labelPerfil);

        labels.stream().forEach(label -> {
            label.setForeground(corTexto);
            add(label);
        });
    }

    public Color getCorFundo() {
        return corFundo;
    }

    public Color getCorTexto() {
        return corTexto;
    }

    public Color getCorLinhaMenu() {
        return corLinhaMenu;
    }

    public Color getCorJanelaAtual() {
        return corJanelaAtual;
    }

    public Font getFontePadrao() {
        return fontePadrao;
    }
}