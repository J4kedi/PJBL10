package classes;

public class Endereco {
    private Integer id;
    private String cep;
    private String estado;
    private String cidade;
    private String rua;
    private Integer numeroCasa;

    public Endereco(Integer id, String cep, String estado, String cidade, String rua, Integer numeroCasa) {
        this.id = id;
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.numeroCasa = numeroCasa;
    }

    public Integer getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getRua() {
        return rua;
    }

    public Integer getNumeroCasa() {
        return numeroCasa;
    }

    @Override
    public String toString() {
        return rua + ", " + cep + ", " + estado + ", " + cidade + ", " + numeroCasa;
    }
}