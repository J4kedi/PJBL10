package classes;

import java.util.HashMap;

public class Pizza extends Produto {
    private String sabor;
    private String tamanho;
    private HashMap<Integer, Ingrediente> ingredientes;

    public Pizza (String nome, String tamanho, String descricao, Double preco, HashMap<Integer, Ingrediente> ingredientes, String sabor, String imagePath) {
        super(nome, preco, descricao, imagePath);
        this.sabor = sabor;
        this.tamanho = tamanho;
        this.ingredientes = ingredientes;
    }

    public String getSabor() {
        return sabor;
    }

    public String getTamanho() {
        return tamanho;
    }

    public HashMap<Integer, Ingrediente> getIngredientes() {
        return ingredientes;
    }
}