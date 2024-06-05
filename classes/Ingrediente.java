package classes;

import java.io.Serializable;

public class Ingrediente implements Serializable {
    private String nome;
    private String descricao;
    private Double precoUnidade;
    private Double quantidade;
    private Double precoTotal;    

    public Ingrediente(String nome, String descricao, Double precoUnidade, Double quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoUnidade = precoUnidade;
        this.quantidade = quantidade;
        caclcularTotal();
    }

    public void aumentarQuantidade(Double quantidade) {
        this.quantidade += quantidade;
        caclcularTotal();
        System.out.println("Nova quantidade: " + quantidade + "\nValor total: " + precoTotal);
    }
    
    public void diminuirQuantidade(Double quantidade) {
        this.quantidade -= quantidade;
        caclcularTotal();
        System.out.println("Nova quantidade: " + quantidade + "\nValor total: " + precoTotal);
    }

    private void caclcularTotal() {
        precoTotal = precoUnidade * quantidade;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPrecoUnidade() {
        return precoUnidade;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }
}