package classes;

public class Cliente extends Usuario{
    private Carrinho carrinho;

    public Cliente(String nome, String cpf, String email, String senha, String username) {
        super(nome, cpf, email, senha, username);
    }

    public void exibirCarrinho() {
        carrinho.exibirCarrinho();
    }
}