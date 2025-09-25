package com.mackenzie.controller;

import com.mackenzie.model.Huffman;
import com.mackenzie.view.ConsoleView;
import java.io.File;
import java.io.IOException;

public class HuffmanController {

    private final Huffman model = new Huffman();
    private final ConsoleView view = new ConsoleView();

    private void processoDeCompressao(String arquivoEntrada, String arquivoSaida) throws IOException {
        System.out.println("--- Comprimindo " + arquivoEntrada + "... ---");
        File fEntrada = new File(arquivoEntrada);

        model.analisarFrequencia(arquivoEntrada);
        view.imprimirTabelaFrequencia(model.getTabelaFrequencia());

        model.construirArvore();
        view.imprimirArvore(model.getArvoreHuffman());

        model.gerarTabelaDeCodigos();
        view.imprimirTabelaCodigos(model.getTabelaCodigos());

        model.comprimirArquivo(arquivoEntrada, arquivoSaida);
        File fSaida = new File(arquivoSaida);

        view.imprimirResumoCompressao(fEntrada.length(), fSaida.length());
        System.out.println("\n*** SUCESSO: Arquivo comprimido em " + arquivoSaida + " ***");

    }

    private void processoDeDescompressao(String arquivoEntrada, String arquivoSaida) throws IOException {
        System.out.println("--- Descomprimindo " + arquivoEntrada + "... ---");
        model.descomprimirArquivo(arquivoEntrada, arquivoSaida);
        System.out.println("\n*** SUCESSO: Arquivo descomprimido em " + arquivoSaida + " ***");

    }

    public void executar(String modo, String arquivoEntrada, String arquivoSaida) {
        try {
            if ("-c".equals(modo)) {
                processoDeCompressao(arquivoEntrada, arquivoSaida);
            } else if ("-d".equals(modo)) {
                processoDeDescompressao(arquivoEntrada, arquivoSaida);
            } else {
                System.out.println("Modo inválido. Use '-c' para comprimir ou '-d' para descomprimir.");
            }
        } catch (IOException e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();

        }
    }
}