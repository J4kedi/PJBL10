package services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import classes.Carrinho;
import classes.Ingrediente;
import classes.Produto;
import classes.Usuario;

public class GerenciarDados {
    private static final String FILE_PATH_DADOS = "dados\\dados.ser";
    private HashMap<Integer, ArrayList<Serializable>> dados = new HashMap<>();
    private HashMap<Integer, Usuario> usuarios = new HashMap<>();
    private HashMap<Integer, Produto> produtos = new HashMap<>();
    private HashMap<Integer, Ingrediente> ingredientes = new HashMap<>();
    private HashMap<Integer, Carrinho> carrinhos = new HashMap<>();
    
    public GerenciarDados() {
        carregarDados();
    }

    public void carregarDados() {
        dados = Empacotamento.lerArquivo(FILE_PATH_DADOS);
        carregarUsuarios();
        carregarProdutos();
        carregarIngredientes();
        carregarCarrinhos();
    }

    public void carregarUsuarios() {
        ArrayList<Serializable> listaUsuarios = dados.get(0);
        if (listaUsuarios != null) {
            for (Serializable serializable : listaUsuarios) {
                if (serializable instanceof Usuario) {
                    Usuario usuario = (Usuario) serializable;
                    usuarios.put(usuario.getId(), usuario);
                }
            }
        }
    } 

    public void carregarProdutos() {
        ArrayList<Serializable> listaProdutos = dados.get(1);
        if (listaProdutos != null) {
            for (Serializable serializable : listaProdutos) {
                if (serializable instanceof Produto) {
                    Produto produto = (Produto) serializable;
                    produtos.put(produto.getId(), produto);
                }
            }
        }
    }

    public void carregarIngredientes() {
        ArrayList<Serializable> listaIngredientes = dados.get(2);
        if (listaIngredientes != null) {
            for (Serializable serializable : listaIngredientes) {
                if (serializable instanceof Ingrediente) {
                    Ingrediente ingrediente = (Ingrediente) serializable;
                    ingredientes.put(ingrediente.getId(), ingrediente);
                }
            }
        }
    }

    public void carregarCarrinhos() {
        ArrayList<Serializable> listaCarrinhos = dados.get(3);
        if (listaCarrinhos != null) {
            for (Serializable serializable : listaCarrinhos) {
                if (serializable instanceof Carrinho) {
                    Carrinho carrinho = (Carrinho) serializable;
                    carrinhos.put(carrinho.getUsuarioId(), carrinho);
                }
            }
        }
    }

    public void salvarUsuario(Usuario u) {
        ArrayList<Serializable> listaUsuarios = dados.get(0);
        if (listaUsuarios == null) {
            listaUsuarios = new ArrayList<>();
            dados.put(0, listaUsuarios);
        }
        listaUsuarios.add(u);
        usuarios.put(u.getId(), u);
        serializarDados();
    }

    public void salvarProduto(Produto p) {
        ArrayList<Serializable> listaProdutos = dados.get(1);
        if (listaProdutos == null) {
            listaProdutos = new ArrayList<>();
            dados.put(1, listaProdutos);
        }
        listaProdutos.add(p);
        serializarDados();
    }

    public void salvarIngrediente(Ingrediente i) {
        ArrayList<Serializable> listaIngredientes = dados.get(2);
        if (listaIngredientes == null) {
            listaIngredientes = new ArrayList<>();
            dados.put(2, listaIngredientes);
        }
        listaIngredientes.add(i);
        serializarDados();
    }

    public void salvarCarrinho(Carrinho c) {
        ArrayList<Serializable> listaCarrinhos = dados.get(3);
        if (listaCarrinhos == null) {
            listaCarrinhos = new ArrayList<>();
            dados.put(3, listaCarrinhos);
        }
        carrinhos.put(c.getUsuarioId(), c);

        listaCarrinhos.clear();
        listaCarrinhos.addAll(carrinhos.values());
        serializarDados();
    }

    public Carrinho obterCarrinho(int usuarioId) {
        return carrinhos.get(usuarioId);
    }

    public void serializarDados() {
        Empacotamento.gravarArquivo(dados, FILE_PATH_DADOS);
    }

    public Map<Integer, Usuario> getUsuarios() {
        return usuarios;
    }

    public HashMap<Integer, Produto> getProdutos() {
        return produtos;
    }

    public HashMap<Integer, Ingrediente> getIngredientes() {
        return ingredientes;
    }
}
