import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import classes.Ingrediente;
import classes.Pizza;
import services.GerenciarDados;

public class CriarPizza extends JDialog {
    private String PATH_IMAGE;
    private File selectedFile;

    public CriarPizza(JFrame parent, GerenciarDados gerencia) {
        super(parent, "Criar Pizza", true);

        Color corFundo = Color.decode("#06ADBF");
        Color corTexto = Color.decode("#0B4359");

        setLayout(new BorderLayout());
        
        JPanel criarPizza = new JPanel(new GridLayout(7, 2));
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

        // Cria o JList para ingredientes com seleção múltipla
        DefaultListModel<Ingrediente> listModel = new DefaultListModel<>();
        JList<Ingrediente> listIngredientes = new JList<>(listModel);
        listIngredientes.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JButton chooseImage = new JButton("Escolher Arquivo");

        JFileChooser chooserFilePath = new JFileChooser();
        JLabel labelImagePath = new JLabel("Image Path:");

        JButton submitButton = new JButton("Enviar");
        submitButton.setPreferredSize(new Dimension(400, 40));

        chooserFilePath.setFileFilter(new FileNameExtensionFilter(
            "JPG, PNG & GIF Images", "jpg", "gif", "png", "jpeg"
        ));

        ArrayList<JTextField> inputs = new ArrayList<>();
        ArrayList<JLabel> labels = new ArrayList<>();
        Collections.addAll(inputs, inputNome, inputTamanho, inputDescricao, inputPreco, inputSabor);
        Collections.addAll(labels, labelNome, labelTamanho, labelDescricao, labelPreco, labelSabor);

        for (Map.Entry<Integer, Ingrediente> entry : gerencia.getIngredientes().entrySet()) {
            listModel.addElement(entry.getValue());
        }

        JScrollPane scrollPane = new JScrollPane(listIngredientes);
        JLabel labelIngredientes = new JLabel("Ingredientes:");
        
        chooseImage.addActionListener(e -> {
            int returnVal = chooserFilePath.showOpenDialog(parent);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooserFilePath.getSelectedFile();
                PATH_IMAGE = "dados/imagens/" + selectedFile.getName();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, escolha uma imagem");
            }
        });
        
        submitButton.addActionListener(e -> {
            if (inputs.stream().allMatch(input -> !input.getText().trim().isEmpty()) && PATH_IMAGE != null) {
                try {
                    Files.copy(selectedFile.toPath(), Paths.get(PATH_IMAGE));
                    System.out.println("Arquivo salvo em: " + PATH_IMAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                try {
                    Double preco = Double.valueOf(inputPreco.getText());
                    Pizza pizza = new Pizza(inputNome.getText(), inputTamanho.getText(), inputDescricao.getText(), preco, getSelectedIngredientes(listIngredientes), inputSabor.getText(), PATH_IMAGE);

                    gerencia.salvarProduto(pizza);

                    JOptionPane.showMessageDialog(this, "Pizza criada com sucesso!");
                    dispose();
                } catch (NumberFormatException er) {
                    JOptionPane.showMessageDialog(this, "Preencha o preço com número");
                    er.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
            }
        });

        IntStream.range(0, Math.min(inputs.size(), labels.size())).forEach(i -> {
            JTextField input = inputs.get(i);
            JLabel label = labels.get(i);
            
            label.setForeground(corTexto);
            
            criarPizza.add(label);
            criarPizza.add(input);
        });

        criarPizza.add(labelIngredientes);
        criarPizza.add(scrollPane);

        criarPizza.add(labelImagePath);
        criarPizza.add(chooseImage);
        
        add(criarPizza, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);

        setSize(400, 350);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private HashMap<Integer, Ingrediente> getSelectedIngredientes(JList<Ingrediente> listIngredientes) {
        HashMap<Integer, Ingrediente> selectedIngredientes = new HashMap<>();
        for (Ingrediente item : listIngredientes.getSelectedValuesList()) {
            selectedIngredientes.put(item.getId(), item);
        }
        return selectedIngredientes;
    }
}