package classes;

public class Cliente extends Usuario{
    private Carrinho carrinho;

    public Cliente(String nome, String cpf, String email, String senha, String username, Endereco endereco) {
        super(nome, cpf, email, senha, username, endereco);
    }

    public void exibirCarrinho() {
        carrinho.exibirCarrinho();
    }
}