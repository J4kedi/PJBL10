import javax.swing.*;
import classes.Endereco;
import classes.Usuario;
import services.GerenciarDados;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.stream.IntStream;

public class Cadastro extends JDialog {
    public Cadastro(JFrame parent, GerenciarDados gerencia) {
        super(parent, "Cadastro", true);

        Color corFundo = Color.decode("#06ADBF");
        Color corTexto = Color.decode("#0B4359");
        
        setLayout(new BorderLayout());
        
        JPanel cadastro = new JPanel(new GridLayout(11, 2));
        cadastro.setBackground(corFundo);

        JTextField inputNome = new JTextField(20);
        JLabel labelNome = new JLabel("Nome: ");

        JTextField inputCpf = new JTextField(20);
        JLabel labelCpf = new JLabel("CPF: ");
        inputCpf.setName("cpf");
        
        JTextField inputEmail = new JTextField(20);
        JLabel labelEmail = new JLabel("Email: ");
        inputEmail.setName("email");
        
        JTextField inputUsername = new JTextField(20);
        JLabel labelUsername = new JLabel("Username: ");
        inputUsername.setName("username");

        JPasswordField inputSenha = new JPasswordField(20);
        JLabel labelSenha = new JLabel("Senha: ");
        
        JPasswordField inputConfirmarSenha = new JPasswordField();
        JLabel labelConfirmarSenha = new JLabel("Confirmar senha: ");

        JTextField inputCep = new JTextField();
        JLabel labelCep = new JLabel("CEP: ");

        JTextField inputEstado = new JTextField();
        JLabel labelEstado = new JLabel("Estado: ");

        JTextField inputCidade = new JTextField();
        JLabel labelCidade = new JLabel("Cidade: ");

        JTextField inputRua = new JTextField();
        JLabel labelRua = new JLabel("Rua: ");

        JTextField inputNumeroCasa = new JTextField();
        JLabel labelNumeroCasa = new JLabel("Número: ");
        inputNumeroCasa.setName("numeroCasa");

        ArrayList<JTextField> inputs = new ArrayList<>();
        ArrayList<JLabel> labels = new ArrayList<>();
        Collections.addAll(inputs, inputNome, inputCpf, inputEmail, inputUsername, inputSenha, inputConfirmarSenha, inputCep, inputEstado, inputCidade, inputRua, inputNumeroCasa);
        Collections.addAll(labels, labelNome, labelCpf, labelEmail, labelUsername, labelSenha, labelConfirmarSenha, labelCep, labelEstado, labelCidade, labelRua, labelNumeroCasa);

        IntStream.range(0, Math.min(inputs.size(), labels.size())).forEach(i -> {
            JTextField input = inputs.get(i);
            JLabel label = labels.get(i);
            
            label.setForeground(corTexto);
            
            cadastro.add(label);
            cadastro.add(input);
        });

        
        add(cadastro, BorderLayout.CENTER);
        
        JButton submitButton = new JButton("Cadastrar");
        submitButton.setPreferredSize(new java.awt.Dimension(400, 40));

        for (JTextField input : inputs) {
            input.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("ENTER"), "submit");
            input.getActionMap().put("submit", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    submitButton.doClick();
                }
            });
        }
        
        submitButton.addActionListener(e -> {
            if (inputs.stream().allMatch(input -> !input.getText().trim().isEmpty())) {
                boolean hasError = inputs.stream().anyMatch(input -> {
                    String nomeCampo = input.getName();

                    if (nomeCampo != null) {
                        for (Map.Entry<Integer, Usuario> entry : gerencia.getUsuarios().entrySet()) {
                            Usuario usuario = entry.getValue();
                            String texto = input.getText();
                            if (nomeCampo.equals("cpf") && usuario.getCpf().equals(texto)) {
                                JOptionPane.showMessageDialog(this, "Já existe um usuário com este CPF.");
                                return true;
                            } else if (nomeCampo.equals("email") && usuario.getEmail().equals(texto)) {
                                JOptionPane.showMessageDialog(this, "Já existe um usuário com este email.");
                                return true;
                            } else if (nomeCampo.equals("username") && usuario.getUsername().equals(texto)) {
                                JOptionPane.showMessageDialog(this, "Já existe um usuário com este username.");
                                return true;
                            } else if (nomeCampo.equals("numeroCasa")) {
                                try {
                                    Integer.parseInt(texto);
                                } catch (NumberFormatException erro) {
                                    JOptionPane.showMessageDialog(this, "Digite um número para a casa");
                                    return true;
                                }
                            }
                        }
                    }
                    return false;
                });

                String senha = new String(((JPasswordField) inputSenha).getPassword());
                String confirmarSenha = new String(((JPasswordField) inputConfirmarSenha).getPassword());

                if (senha.equals(confirmarSenha) && !hasError) {
                    Usuario u = new Usuario(inputNome.getText(), inputCpf.getText(), inputEmail.getText(), senha, inputUsername.getText());
                    Endereco endereco = new Endereco(u.getId(), inputCep.getText(), inputEstado.getText(), inputCidade.getText(), inputRua.getText(), Integer.parseInt(inputNumeroCasa.getText()));
                    u.adicionarEndereco(endereco);

                    gerencia.salvarUsuario(u);
                } else if (!hasError){
                    hasError = true;
                    JOptionPane.showMessageDialog(this, "As senhas precisam ser iguais.");
                }

                if (!hasError) {
                    JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
                    dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
            }
        });
        add(submitButton, BorderLayout.SOUTH);
        
        // Configurações da janela
        setSize(400, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}