import javax.swing.*;

import services.GerenciarDados;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class Cadastro extends JDialog {
    private GerenciarDados gerencia;

    public Cadastro(JFrame parent, GerenciarDados gerencia) {
        super(parent, "Cadastro", true); // Torna o JDialog modal

        this.gerencia = gerencia;

        Color corFundo = Color.decode("#06ADBF");
        Color corTexto = Color.decode("#0B4359");
        
        setLayout(new BorderLayout());
        
        JPanel cadastro = new JPanel(new GridLayout(6, 2));
        cadastro.setBackground(corFundo);

        JTextField inputNome = new JTextField(20);
        JLabel labelNome = new JLabel("Nome: ");

        JTextField inputCpf = new JTextField(20);
        JLabel labelCpf = new JLabel("CPF: ");
        inputCpf.setName("cpf");
        
        JTextField inputEmail = new JTextField(20);
        JLabel labelEmail = new JLabel("Email: ");
        inputEmail.setName("email");
        
        JPasswordField inputSenha = new JPasswordField(20);
        JLabel labelSenha = new JLabel("Senha: ");
        
        JTextField inputUsername = new JTextField(20);
        JLabel labelUsername = new JLabel("Username: ");
        inputUsername.setName("username");

        ArrayList<JTextField> inputs = new ArrayList<>();
        ArrayList<JLabel> labels = new ArrayList<>();
        Collections.addAll(inputs, inputNome, inputCpf, inputEmail, inputSenha, inputUsername);
        Collections.addAll(labels, labelNome, labelCpf, labelEmail, labelSenha, labelUsername);

        IntStream.range(0, Math.min(inputs.size(), labels.size())).forEach(i -> {
            JTextField input = inputs.get(i);
            JLabel label = labels.get(i);
            
            input.setPreferredSize(new java.awt.Dimension(170, 30));
            label.setForeground(corTexto);
            
            cadastro.add(label);
            cadastro.add(input);
        });

        add(cadastro, BorderLayout.CENTER);

        JButton submitButton = new JButton("Cadastrar");
        submitButton.setPreferredSize(new java.awt.Dimension(400, 40));
        submitButton.addActionListener(e -> {
            if (inputs.stream().allMatch(input -> !input.getText().trim().isEmpty())) {                
                inputs.stream().forEach(input -> {
                    String nomeCampo = input.getName();

                    if (nomeCampo != null) {
                        gerencia.getUsuarios().forEach((id, usuario)-> {
                             if(nomeCampo == "cpf" && usuario.getCpf() == input.getText()) {
                                JOptionPane.showMessageDialog(this, "Já existe um usuario com este cpf");
                                dispose();
                            } else if (nomeCampo.equals("email") && usuario.getEmail() == input.getText()) {
                                JOptionPane.showMessageDialog(this, "Já existe um usuario com este email");
                                dispose();
                            } else if (nomeCampo.equals("username") && usuario.getUsername() == input.getText()) {
                                JOptionPane.showMessageDialog(this, "Já existe um usuario com este username");
                                dispose();
                            }
                        });
                    }
                });

                JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
                dispose(); // Fecha a janela após o cadastro
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
            }
        });
        add(submitButton, BorderLayout.SOUTH);
        
        // Configurações da janela
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }  
}
