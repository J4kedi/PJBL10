package classes;

public class Funcionario extends Usuario {
    private String ctps;
    private String cargo;
    private String setor;

    public Funcionario(String nome, String cpf, String email, String senha, String username, String ctps, String cargo, String setor, Integer nivelPermissao) {
        super(nome, cpf, email, senha, username);
        super.setNivelPermissao(1);
        this.ctps = ctps;
        this.cargo = cargo;
        this.setor = setor;
    }

    public String getCtps() {
        return ctps;
    }

    public String getCargo() {
        return cargo;
    }

    public String getSetor() {
        return setor;
    }
}
