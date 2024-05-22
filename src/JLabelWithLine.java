import java.awt.*;
import javax.swing.*;

public class JLabelWithLine extends JLabel {
    private Color lineColor;

    public JLabelWithLine(String text) {
        super(text);
        this.lineColor = Color.BLUE; // Cor padrão da linha
    }

    public void setLineColor(Color color) {
        this.lineColor = color;
        repaint(); // Redesenha o componente para refletir a mudança de cor da linha
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenha a linha embaixo do texto
        g.setColor(lineColor);
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }
}