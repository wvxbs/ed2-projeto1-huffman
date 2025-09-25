/**
 * Projeto 1 de Estrutura de Dados II.
 * Prof. Dr. Jean M. Laine.
 *
 * Integrantes do grupo:
 * - Gabriel Ferreira - RA: 10442043.
 * - Kaiki Bellini Barbosa - RA: 10402509.
 * - Leonardo Binder - RA: 10402289.
 */

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

        long tempoInicio = System.nanoTime();

        model.analisarFrequencia(arquivoEntrada);
        model.construirArvore();
        model.gerarTabelaDeCodigos();
        model.comprimirArquivo(arquivoEntrada, arquivoSaida);

        long tempoFim = System.nanoTime();

        view.imprimirTabelaFrequencia(model.getTabelaFrequencia());
        view.imprimirArvore(model.getArvoreHuffman());
        view.imprimirTabelaCodigos(model.getTabelaCodigos());

        File fSaida = new File(arquivoSaida);
        view.imprimirResumoCompressao(fEntrada.length(), fSaida.length());

        long duracaoNs = tempoFim - tempoInicio;
        double duracaoMs = duracaoNs / 1_000_000.0;
        System.out.printf("Tempo total de compressão: %.2f ms\n\n", duracaoMs);

        System.out.println("*** SUCESSO: Arquivo comprimido em " + arquivoSaida + " ***");
    }

    private void processoDeDescompressao(String arquivoEntrada, String arquivoSaida) throws IOException {
        System.out.println("--- Descomprimindo " + arquivoEntrada + "... ---");

        long tempoInicio = System.nanoTime();

        model.descomprimirArquivo(arquivoEntrada, arquivoSaida);

        long tempoFim = System.nanoTime();
        long duracaoNs = tempoFim - tempoInicio;
        double duracaoMs = duracaoNs / 1_000_000.0;

        System.out.printf("Tempo total de descompressão: %.2f ms\n\n", duracaoMs);

        System.out.println("*** SUCESSO: Arquivo descomprimido em " + arquivoSaida + " ***");
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