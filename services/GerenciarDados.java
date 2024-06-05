package services;

import java.util.HashMap;
import java.util.Map;
import classes.Usuario;

public class GerenciarDados {
    private static final String FILE_PATH_DADOS = "dados\\dados.ser";
    private HashMap<Integer, Usuario> usuarios = new HashMap<>();
    
    public GerenciarDados(){
        carregarUsuarios();
    }

    public void carregarUsuarios() {
        usuarios = Empacotamento.lerArquivo(FILE_PATH_DADOS);
    }

    public void salvarUsuario(Usuario u) {
        usuarios.put(u.getId(), u);
    }

    public void serializarUsuarios() {
        Empacotamento.gravarArquivo(usuarios, FILE_PATH_DADOS);
    }

    public Map<Integer, Usuario> getUsuarios() {
        return usuarios;
    }
}