package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import classes.Usuario;

public class GerenciarDados {
    private static final String FILE_PATH = "../dados/dados.txt";
    private static final File txtUsuario = new File(FILE_PATH);
    private static final String DELIMITER = ",";
    private static Map<Integer, Usuario> usuarios = new HashMap<>();
    
    public GerenciarDados(){
  //      carregarUsuarios();
    }

    private void CreateFile() {
        if () {
            
        }
    }

    public void carregarUsuarios() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                int id = Integer.parseInt(parts[0]);
                String nome = parts[1];
                String email = parts[2];

                System.out.println(line);
            //    Usuario usuario = new Usuario(nome, cpf, email, senha, username, endereco); // Supondo que você tenha um construtor de Usuario
            //    usuarios.put(usuario.getId(), usuario);
            }
        } catch (IOException e) {
            System.out.println("Um erro ocorreu ao ler os usuários.");
            e.printStackTrace();
        }
    }

    public void salvarUsuarios(Usuario u) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            writer.println(u.getId() + DELIMITER + u.getNome() + DELIMITER + u.getEmail());
        } catch (IOException e) {
            System.out.println("Um erro ocorreu ao salvar os usuários.");
            e.printStackTrace();
        }
    }

}