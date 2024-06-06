package classes;

import java.util.ArrayList;
import services.GeradorCodUnico;
import java.io.Serializable;

public class Usuario implements Serializable {
    private Integer id; 
    private GeradorCodUnico gerador = new GeradorCodUnico();
    private ArrayList<Endereco> enderecos = new ArrayList<>();
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private String username;
    private Integer nivelPermissao = 0;

    public Usuario(String nome, String cpf, String email, String senha, String username) {
        this.nome = nome;
        this.cpf = cpf;
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.id = gerador.getCodigo();
    }

    public void adicionarEndereco(Endereco e) {
        enderecos.add(e);
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public ArrayList<Endereco> getEnderecos() {
        return enderecos;
    }

    public String getEmail() {
        return email;
    }
    
    public String getSenha() {
        return senha;
    }
    
    public String getUsername() {
        return username;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNivelPermissao() {
        return nivelPermissao;
    }

    public void setNivelPermissao(Integer nivelPermissao) {
        this.nivelPermissao = nivelPermissao;
    }

    @Override
    public String toString() {
        return "\n[Nome: " + nome + ", CPF: " + cpf + ", Email: " + email + ", Username: " + username + " " + enderecos + "]";
    }
}