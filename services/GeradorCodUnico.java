package services;

import java.util.ArrayList;
import java.util.Random;

public class GeradorCodUnico {
    private Random gerador = new Random();
    private int codigo;
    private ArrayList<Integer> listaCodigos = new ArrayList<Integer>();

    private void gerarCod() {
        do {
            codigo = gerador.nextInt(100000);
        } while(listaCodigos.contains(codigo));

        listaCodigos.add(codigo);
    }

    public int getCodigo() {
        gerarCod();
        return codigo;
    }
}