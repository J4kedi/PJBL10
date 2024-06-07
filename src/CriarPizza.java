import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.Pizza;
import classes.Produto;
import services.GerenciarDados;

public class CriarPizza extends JDialog {
    public CriarPizza(JFrame parent, GerenciarDados gerencia) {
        super(parent, "Criar Pizza", true);

        Color corFundo = Color.decode("#06ADBF");
        Color corTexto = Color.decode("#0B4359");

        setLayout(new BorderLayout());
        
        JPanel criarPizza = new JPanel(new GridLayout(6, 2));
        criarPizza.setBackground(corFundo);

        JTextField inputNome = new JTextField();
        JLabel labelNome = new JLabel("Nome da Pizza:");

        JTextField inputTamanho = new JTextField();
        JLabel labelTamanho = new JLabel("Tamanho:");

        JTextField inputDescricao = new JTextField();
        JLabel labelDescricao = new JLabel("Descrição:");

        JTextField inputPreco = new JTextField();
        JLabel labelPreco = new JLabel("Preço:");

        JTextField inputSabor = new JTextField();
        JLabel labelSabor = new JLabel("Sabor:");

        JTextField inputImagePath = new JTextField();
        JLabel labelImagePath = new JLabel("Image Path:");

        Pizza pizza = new Pizza(getName(), getName(), getTitle(), null, null, getWarningString(), getName());
    }
}
