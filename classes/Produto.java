package classes;

import java.io.Serializable;
import java.util.HashMap;

import services.Avaliavel;
import services.GeradorCodUnico;

public class Produto implements Serializable, Avaliavel {
    private GeradorCodUnico gerarId = new GeradorCodUnico();
    private String nome;
    private String descricao;
    private int id;
    private Double preco;
    private HashMap<Integer, Double> avaliacoes = new HashMap<>();
    private String IMAGE_PATH;

    public Produto(String nome, Double preco, String descricao, String imagePath) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.IMAGE_PATH = imagePath;
        this.id = gerarId.getCodigo();
        avaliacoes.put(1,  5.00);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getIMAGE_PATH() {
        return IMAGE_PATH;
    }

    @Override
    public void avaliar(Integer usuarioId, Double nota) {
        if (nota >= 0 && nota <= 5) {
            avaliacoes.put(usuarioId, nota);
        } else {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 5.");
        }
    }

    @Override
    public Double getAvaliacao() {
        return avaliacoes.isEmpty() ? 0 : avaliacoes.values().stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }


    @Override
    public String toString() {
        return "[Nome: " + nome + ", Descrição: " + descricao + ", Preço: " + preco + ", Avaliação: " + avaliacoes + "]";
    }
}