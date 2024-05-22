package classes;

public class Endereco {
    private String cep;
    private String estado;
    private String cidade;
    private String rua;
    private Integer numeroCasa;

    public Endereco(String cep, String estado, String cidade, String rua, Integer numeroCasa) {
        this.cep = cep;
        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.numeroCasa = numeroCasa;
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
        return "Rua " + rua + ", " + cep + ", " + estado + ", " + cidade;
    }
}