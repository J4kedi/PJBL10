package classes;

import java.util.Objects;

public class Ingrediente extends Produto {
    private Double quantidade;

    public Ingrediente(String nome, String descricao, Double precoUnidade, Double quantidade, String imagePath) {
        super(nome, precoUnidade, descricao, imagePath);
        this.quantidade = quantidade;
    }

    public void aumentarQuantidade(Double quantidade) {
        this.quantidade += quantidade;
        System.out.println("Nova quantidade: " + quantidade + "\nValor total: " + getPreco());
    }
    
    public void diminuirQuantidade(Double quantidade) {
        this.quantidade -= quantidade;
        System.out.println("Nova quantidade: " + quantidade + "\nValor total: " + getPreco());
    }

    public Double getQuantidade() {
        return quantidade;
    }

    @Override
    public Double getPreco() {
        Double preco = 0.0;
        preco *= quantidade;
        return preco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingrediente that = (Ingrediente) o;
        return this.getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }

    @Override
    public String toString() {
        return getNome();
    }
}