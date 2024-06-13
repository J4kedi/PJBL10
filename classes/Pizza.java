package classes;

import java.util.HashMap;
import java.util.Map;

public class Pizza extends Produto {
    private String sabor;
    private String tamanho;
    private HashMap<Integer, Ingrediente> ingredientes;

    public Pizza (String nome, String tamanho, String descricao, Double preco, HashMap<Integer, Ingrediente> ingredientes, String sabor, String imagePath) {
        super(nome, preco, descricao, imagePath);
        this.sabor = sabor;
        this.tamanho = tamanho.toLowerCase();
        this.ingredientes = ingredientes;
    }

    @Override
    public Double getPreco() {
        Double preco = 0.0;

        if (tamanho == "grande") {
            preco = 49.00;
        } else if (tamanho == "médio") {
            preco = 39.00;
        } else if (tamanho == "pequeno") {
            preco = 29.00;
        }

        for(Map.Entry<Integer, Ingrediente> entrada: ingredientes.entrySet()) {
            preco += entrada.getValue().getPreco();   
        }

        return preco;
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