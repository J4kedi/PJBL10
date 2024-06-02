package services;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import classes.Usuario;

public class Empacotamento {
    public static void gravarArquivo(HashMap<Integer, Usuario> map, String nomeArq) {
        try {
            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(nomeArq));
            objOutput.writeObject(map);

            objOutput.close();

            System.out.println("Dados carregados com sucesso!");
        } catch (IOException erro) {
            System.out.println("Erro: " + erro.getMessage());
            erro.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static HashMap<Integer, Usuario> lerArquivo(String nomeArq) {
        HashMap<Integer, Usuario> map = new HashMap<>();
        try {
            File arq = new File(nomeArq);
            if (arq.exists()) {
                ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
                map = (HashMap<Integer, Usuario>) objInput.readObject();
                objInput.close();

                System.out.println("Dados lidos com sucesso!");
            }
        } catch (IOException erro1) {
            System.out.println("Erro: " + erro1.getMessage());
        } catch (ClassNotFoundException erro2) {
            System.out.println("Erro: " + erro2.getMessage());
        }

        return map;
    }
}
