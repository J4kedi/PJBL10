package classes;

public class Ingrediente extends Produto {
    private Double quantidade;
    private Double precoTotal;    

    public Ingrediente(String nome, String descricao, Double precoUnidade, Double quantidade, String imagePath) {
        super(nome, precoUnidade, descricao, imagePath);
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
        precoTotal = this.getPreco() * quantidade;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }
}