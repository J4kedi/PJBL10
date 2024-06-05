package classes;

import java.io.Serializable;

import services.GeradorCodUnico;

public class Produto implements Serializable {
    private GeradorCodUnico gerarId = new GeradorCodUnico();
    private String nome;
    private String descricao;
    private int id;
    private Double preco;
    private static Double avaliacao;

    public Produto(String nome, Double preco, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.id = gerarId.getCodigo();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public Double getAvaliacao() {
        return avaliacao;
    }

    public static void avaliar(Double nota) {
        avaliacao += nota;
    }
}