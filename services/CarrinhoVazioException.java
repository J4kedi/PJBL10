package services;

public class CarrinhoVazioException extends Exception{
    public CarrinhoVazioException(String mensagem) {
        super(mensagem);
    }

    public CarrinhoVazioException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Carrinho está vazio";
    }
}