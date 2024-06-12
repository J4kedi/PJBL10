package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Carrinho implements Serializable{
    private ArrayList<Produto> listaProdutos = new ArrayList<>();
    private Integer usuarioId;

    public Carrinho(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
    
    public void adicionarProduto(Produto p) {
        listaProdutos.add(p);
    }

    public String removerProduto(Produto p) {
        if(listaProdutos.isEmpty()) {
            return "Carrinho vazio!";
        } else {
            listaProdutos.remove(p);
            return "Produto removido com sucesso!";
        }
    }

    public void limpar() {
        listaProdutos.clear();
    }

    public ArrayList<Produto> getProdutos() {
        return listaProdutos;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    @Override
    public String toString() {
        String lista = listaProdutos.stream().toString();

        return lista;
    }
}