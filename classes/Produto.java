package classes;

import services.GeradorCodUnico;

public class Produto {
    private GeradorCodUnico gerarId = new GeradorCodUnico();
    private String nome;
    private int id;
    private Double preco;
    private Double avaliacao;

    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
        this.id = gerarId.getCodigo();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }
}