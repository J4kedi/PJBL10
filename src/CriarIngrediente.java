import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import classes.Ingrediente;
import services.GerenciarDados;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.IntStream;

public class CriarIngrediente extends JDialog {
    private String PATH_IMAGE;
    private File selectedFile;

    public CriarIngrediente(JFrame parent, GerenciarDados gerencia) {
        super(parent, "Criar Ingrediente", true);

        Color corFundo = Color.decode("#06ADBF");
        Color corTexto = Color.decode("#0B4359");

        setLayout(new BorderLayout());

        JPanel criarIngrediente = new JPanel(new GridLayout(6, 2));
        criarIngrediente.setBackground(corFundo);

        JTextField inputNome = new JTextField();
        JLabel labelNome = new JLabel("Nome do Ingrediente:");

        JTextField inputDescricao = new JTextField();
        JLabel labelDescricao = new JLabel("Descrição:");

        JTextField inputPrecoUnidade = new JTextField();
        JLabel labelPrecoUnidade = new JLabel("Preço por Unidade:");

        JTextField inputQuantidade = new JTextField();
        JLabel labelQuantidade = new JLabel("Quantidade:");

        JLabel labelImagePath = new JLabel("Image Path:");
        JButton chooseImage = new JButton("Escolher Arquivo");
        chooseImage.setPreferredSize(new Dimension(100, 20));

        JFileChooser chooserFilePath = new JFileChooser();
        chooserFilePath.setFileFilter(new FileNameExtensionFilter(
            "JPG, PNG & GIF Images", "jpg", "gif", "png"
        ));

        ArrayList<JTextField> inputs = new ArrayList<>();
        ArrayList<JLabel> labels = new ArrayList<>();
        Collections.addAll(inputs, inputNome, inputDescricao, inputPrecoUnidade, inputQuantidade);
        Collections.addAll(labels, labelNome, labelDescricao, labelPrecoUnidade, labelQuantidade);

        IntStream.range(0, Math.min(inputs.size(), labels.size())).forEach(i -> {
            JTextField input = inputs.get(i);
            JLabel label = labels.get(i);

            input.setPreferredSize(new Dimension(170, 30));
            label.setForeground(corTexto);

            criarIngrediente.add(label);
            criarIngrediente.add(input);
        });

        // Adiciona um ActionListener ao botão chooseImage
        chooseImage.addActionListener(e -> {
            int returnVal = chooserFilePath.showOpenDialog(parent);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooserFilePath.getSelectedFile();
                PATH_IMAGE = "dados/imagens/" + selectedFile.getName();
                System.out.println("Arquivo selecionado: " + PATH_IMAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, escolha uma imagem");
            }
        });

        JButton submitButton = new JButton("Enviar");
        submitButton.setPreferredSize(new Dimension(400, 40));
        submitButton.addActionListener(e -> {
            if (inputs.stream().allMatch(input -> !input.getText().trim().isEmpty()) && PATH_IMAGE != null) {
                try {
                    Files.copy(selectedFile.toPath(), Paths.get(PATH_IMAGE));
                    System.out.println("Arquivo salvo em: " + PATH_IMAGE);
                } catch (FileAlreadyExistsException ep) {
                    System.out.println("Arquivo já existente: " + PATH_IMAGE);
                } catch (IOException ex) {
                   ex.printStackTrace();
                }

                try {
                    Double precoUnidade = Double.valueOf(inputPrecoUnidade.getText());
                    Double quantidade = Double.valueOf(inputQuantidade.getText());
                    Ingrediente ingrediente = new Ingrediente(inputNome.getText(), inputDescricao.getText(), precoUnidade, quantidade, PATH_IMAGE);

                    gerencia.salvarIngrediente(ingrediente);

                    JOptionPane.showMessageDialog(this, "Ingrediente criado com sucesso!");
                    dispose();
                } catch (NumberFormatException er) {
                    JOptionPane.showMessageDialog(this, "Preencha os campos de preço e quantidade com números");
                    er.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
            }
        });

        criarIngrediente.add(labelImagePath);
        criarIngrediente.add(chooseImage);

        add(criarIngrediente, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);

        setSize(400, 350);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}