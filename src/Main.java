import javax.swing.*;

import classes.Endereco;
import classes.Usuario;
import services.GerenciarDados;
import java.awt.Color;

public class Main {
    
    public static void main(String[] args) {
        GerenciarDados gerencia = new GerenciarDados();

        Endereco enderecoJorge = new Endereco("82340111", "PR", "Curitiba", "Rua do Jorge", 123);
        Usuario jorge = new Usuario("Jorge", "12345678910", "jorge@gmail.com", "123", "jorgeAnao", enderecoJorge);

        gerencia.salvarUsuarios(jorge);

        gerencia.carregarUsuarios();

        Color corFundo = Color.decode("#06ADBF");
        Color corTexto = Color.decode("#0B4359");
        Color corTextoLink = Color.blue;

        // Criando a janela
        JFrame frame = new JFrame("Pizzaria Java");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(corFundo);

        // Criando campo de email 
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

        // Mostra a janela
        frame.setVisible(true);
    }
}