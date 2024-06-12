import javax.swing.*;

import classes.Pizza;
import services.GerenciarDados;
import services.Login;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    private static GerenciarDados gerencia = new GerenciarDados();

    public static void main(String[] args) {
        System.out.println(gerencia.getUsuarios());
        System.out.println(gerencia.getProdutos());
        System.out.println(gerencia.getIngredientes());

        gerencia.getProdutos().keySet().stream().forEach(k -> {
            if (gerencia.getProdutos().get(k) instanceof Pizza) {
                Pizza p = (Pizza) gerencia.getProdutos().get(k);

                System.out.println(p.getIngredientes());
            }
        });

        Color corFundo = Color.decode("#06ADBF");
        Color corTexto = Color.decode("#0B4359");
        Color corTextoLink = Color.blue;

        // Criando a janela
        JFrame frame = new JFrame("Pizzaria Java");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(corFundo);

        // Adicionando um WindowListener para capturar o evento de fechamento da janela
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("\nJanela fechada.");
                System.out.println("Serializando dados...");
                gerencia.serializarDados();
                System.out.println("Dados serializados com sucesso!");
            }
        });

        JTextField inputEmail = new JTextField();
        JLabel labelEmail = new JLabel("Email ou Username:");
        inputEmail.setBounds(100, 100, 170, 30);

        labelEmail.setLabelFor(inputEmail);
        labelEmail.setBounds(100, 70, 170, 30);
        labelEmail.setForeground(corTexto);
        
        JPasswordField inputSenha = new JPasswordField();
        JLabel labelSenha = new JLabel("Senha:");
        inputSenha.setBounds(100, 160, 170, 30);
        
        labelSenha.setLabelFor(inputSenha);
        labelSenha.setBounds(100, 130, 170, 30);
        labelSenha.setForeground(corTexto);
        
        JButton submitButton = new JButton("Enviar");
        submitButton.setBounds(100, 210, 80, 20);
        
        JLabel mensagemCadastro = new JLabel("Não é cadastrado?");     
        mensagemCadastro.setBounds(185, 210, 110, 20);
        mensagemCadastro.setForeground(corTexto);
        
        JLabelWithLine mensagemCliqueAqui = new JLabelWithLine("clique aqui!");
        mensagemCliqueAqui.setBounds(295, 210, 70, 20);
        mensagemCliqueAqui.setForeground(corTextoLink);
        mensagemCliqueAqui.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        mensagemCliqueAqui.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Cadastro(frame, gerencia);
            }
        });

        inputEmail.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "submit");
        inputSenha.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "submit");
        inputEmail.getActionMap().put("submit", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButton.doClick();
            }
        });
        inputSenha.getActionMap().put("submit", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButton.doClick();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = inputEmail.getText();
                String senha = new String(inputSenha.getPassword());

                Login logar = new Login(login, senha, gerencia.getUsuarios());

                if (logar.getLogged()) {
                    frame.setVisible(false);
                    new Cardapio(Login.getUsuario(), frame, gerencia);

                    System.out.println("Login bem sucedido!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Credenciais inválidas.");
                    System.out.println("Login mal sucedido!");
                }
            }
        });

        // Adicionando os componentes à janela
        frame.add(inputEmail);
        frame.add(labelEmail);
        frame.add(inputSenha);
        frame.add(labelSenha);
        frame.add(submitButton);
        frame.add(mensagemCadastro);
        frame.add(mensagemCliqueAqui);
        
        // Tamanho da janela
        frame.setSize(700, 500);
        frame.setLayout(null);
        frame.setResizable(false);

        // Mostra a janela
        frame.setVisible(true);
    }
}