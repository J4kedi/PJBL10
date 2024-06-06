package services;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Empacotamento {
    public static void gravarArquivo(HashMap<Integer, ArrayList<Serializable>> map, String nomeArq) {
        try {
            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(nomeArq));
            objOutput.writeObject(map); 

            objOutput.close();
        } catch (IOException erro) {
            System.out.println("Erro: " + erro.getMessage());
            erro.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static HashMap<Integer, ArrayList<Serializable>> lerArquivo(String nomeArq) {
        HashMap<Integer, ArrayList<Serializable>> map = new HashMap<>();
        try {
            File arq = new File(nomeArq);
            if (arq.exists()) {
                ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
                map = (HashMap<Integer, ArrayList<Serializable>>) objInput.readObject();
                objInput.close();

                System.out.println("\nDados lidos com sucesso!");
            }
        } catch (IOException erro1) {
            System.out.println("Erro: " + erro1.getMessage());
        } catch (ClassNotFoundException erro2) {
            System.out.println("Erro: " + erro2.getMessage());
        }

        return map;
    }
}
