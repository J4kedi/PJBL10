import classes.Pizza;
import classes.Usuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Cardapio {
    public Cardapio(Usuario usuario, JFrame mainFrame) {
        Color corFundo = Color.decode("#06ADBF");
        Color corTexto = Color.decode("#0B4359");

        // Criando a janela
        JFrame frame = new JFrame("Pizzaria Java");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(corFundo);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainFrame.setVisible(true);
            }
        });

        JLabel labelTitulo = new JLabel("Pizzaria");
        labelTitulo.setFont(new Font("SansSerif", Font.BOLD, 38));
        labelTitulo.setBounds(5, 5, 200, 40);

        ArrayList<JLabel> labels = new ArrayList<>();
        ArrayList<JTextField> inputs = new ArrayList<>();
        Collections.addAll(labels, labelTitulo);
        // Collections.addAll(inputs, labelTitulo);

        labels.stream().forEach(label -> {
            label.setForeground(corTexto);
            frame.add(label);
        });

        ArrayList<Pizza> listaPizzas  = new ArrayList<>();
    }
}