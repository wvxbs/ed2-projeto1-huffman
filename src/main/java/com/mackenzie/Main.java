

package com.mackenzie;

import com.mackenzie.controller.HuffmanController;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Uso: java -jar huffman.jar [-c | -d] <arquivo_entrada> <arquivo_saida>");
            return;
        }

        String modo = args[0];
        String arquivoEntrada = args[1];
        String arquivoSaida = args[2];

        HuffmanController controller = new HuffmanController();
        controller.executar(modo, arquivoEntrada, arquivoSaida);
    }
}