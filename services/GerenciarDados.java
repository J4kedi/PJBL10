package services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import classes.Ingrediente;
import classes.Produto;
import classes.Usuario;

public class GerenciarDados {
    private static final String FILE_PATH_DADOS = "dados\\dados.ser";
    private HashMap<Integer, ArrayList<Serializable>> dados = new HashMap<>();
    HashMap<Integer, Usuario> usuarios = new HashMap<>();
    HashMap<Integer, Produto> produtos = new HashMap<>();
    HashMap<Integer, Ingrediente> ingredientes = new HashMap<>();
    
    public GerenciarDados(){
        carregarDados();
    }

    public void carregarDados() {
        dados = Empacotamento.lerArquivo(FILE_PATH_DADOS);
        carregarUsuarios();
        carregarProdutos();
        carregarIngredientes();
    }

    private void carregarUsuarios() {
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

    private void carregarProdutos() {
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