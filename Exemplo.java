import javax.swing.JTextField;
import java.lang.reflect.Field;

public class Exemplo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        // Criar o JTextField
        JTextField inputCpf = new JTextField(20);

        // Obter o nome do campo usando reflexão
        Field campo = Exemplo.class.getDeclaredField("inputCpf");
        String nomeCampo = campo.getName();

        // Separar a primeira letra maiúscula e o restante
        String primeiroCaracter = nomeCampo.substring(0, 1).toUpperCase();
        String restanteCaracteres = nomeCampo.substring(1);

        // Formar o nome desejado para o JTextField
        String nomeDesejado = primeiroCaracter + restanteCaracteres;

        // Definir o nome do JTextField
        inputCpf.setName(nomeDesejado);

        // Imprimir o nome do JTextField
        System.out.println("\nNome do JTextField: " + inputCpf.getName());
        System.out.println("Nome da variavel: " + inputCpf.getClass().getDeclaredFields()[0].getName());
    }

    // Outros campos da classe, incluindo inputCpf
    private static JTextField inputCpf;
    private static JTextField outroCampo;
}
