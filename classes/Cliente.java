package classes;

import services.Avaliavel;

public class Cliente extends Usuario{
    public Cliente(String nome, String cpf, String email, String senha, String username) {
        super(nome, cpf, email, senha, username);
    }
    
    public void avaliarProduto(Avaliavel produto, Double nota) {
        produto.avaliar(this.getId(), nota);
    }
}