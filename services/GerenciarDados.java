package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import classes.Endereco;
import classes.Usuario;

public class GerenciarDados {
    private static final String FILE_PATH_USUARIOS = "dados\\usuarios\\dados.txt";
    private static final String FILE_PATH_ENDERECOS = "dados\\enderecos\\dados.txt";
    private static final File txtUsuario = new File(FILE_PATH_USUARIOS);
    private static final File txtEndereco = new File(FILE_PATH_ENDERECOS);
    private static final String DELIMITER = ",";
    private static Map<Integer, Usuario> usuarios = new HashMap<>();
    
    public GerenciarDados(){
        CreateFile();
        carregarUsuarios();
    }

    private void CreateFile() {
        try {
            if (txtUsuario.createNewFile()) {
                System.out.println("File created: " + txtUsuario.getName());            
                System.out.println("Path: " + txtUsuario.getAbsolutePath());            
            } else {
                System.out.println("Arquivo já existente");
            }
        } catch (IOException e) {
            System.out.println("Erro ao criar arquivo");
            e.printStackTrace();
        }
    }

    public void carregarUsuarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH_USUARIOS))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                String nome = parts[1];
                String cpf = parts[2];
                String email = parts[3];
                String senha = parts[4];
                String username = parts[5];
                String id = parts[0];
                String rua = parts[6];
                String cidade = parts[7];
                String estado = parts[8];

                System.out.println(id);
                System.out.println(rua);
                System.out.println(cidade);
                System.out.println(estado);

                Endereco endereco = new Endereco(Integer.parseInt(id), parts[6], parts[7], parts[8], parts[9], Integer.parseInt(parts[10]));
                Usuario usuario = new Usuario(nome, cpf, email, senha, username);
                usuario.adicionarEndereco(endereco);

                usuarios.put(usuario.getId(), usuario);
            }
        } catch (IOException e) {
            System.out.println("Um erro ocorreu ao ler os usuários.");
            e.printStackTrace();
        }
    }

    public void salvarUsuarios(Usuario u) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH_USUARIOS))) {
            writer.println(u.getId() + DELIMITER + u.getNome() + DELIMITER + u.getCpf() + DELIMITER + u.getEmail() + DELIMITER + u.getSenha() + DELIMITER + u.getUsername() + DELIMITER + u.getEnderecos());
        } catch (IOException e) {
            System.out.println("Um erro ocorreu ao salvar os usuários.");
            e.printStackTrace();
        }
    }
}