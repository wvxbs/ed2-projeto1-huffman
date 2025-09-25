/**
 * Projeto 1 de Estrutura de Dados II.
 * Prof. Dr. Jean M. Laine.
 *
 * Integrantes do grupo:
 * - Gabriel Ferreira - RA: 10442043.
 * - Kaiki Bellini Barbosa - RA: 10402509.
 * - Leonardo Binder - RA: 10402289.
 */

package com.mackenzie.view;

import com.mackenzie.model.No;

public class ConsoleView {
    private void imprimirArvoreRecursivo(No no, String prefixo) {
        if (no == null) return;

        String infoNo = no.isFolha() ? "'" + no.getCaractere() + "'" : "Interno";
        System.out.println(prefixo + "-> (" + infoNo + ", " + no.getFrequencia() + ")");

        if (!no.isFolha()) {
            imprimirArvoreRecursivo(no.getEsquerda(), prefixo + "  |");
            imprimirArvoreRecursivo(no.getDireita(), prefixo + "  |");
        }

    }

    public void imprimirTabelaFrequencia(int[] tabela) {
        System.out.println("ETAPA 1: Tabela de Frequencia de Caracteres");
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] > 0) {
                System.out.println("Caractere '" + (char)i + "' (ASCII: " + i + "): " + tabela[i]);
            }
        }
        System.out.println();
    }

    public void imprimirArvore(No raiz) {
        System.out.println("ETAPA 3: Arvore de Huffman");
        imprimirArvoreRecursivo(raiz, "");
        System.out.println();

    }

    public void imprimirTabelaCodigos(String[] tabela) {
        System.out.println("ETAPA 4: Tabela de Codigos de Huffman");
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] != null) {
                System.out.println("Caractere '" + (char)i + "': " + tabela[i]);
            }
        }
        System.out.println();

    }

    public void imprimirResumoCompressao(long tamanhoOriginal, long tamanhoComprimido) {
        System.out.println("ETAPA 5: Resumo da Compressao");
        double taxa = (1.0 - ((double)tamanhoComprimido / tamanhoOriginal)) * 100.0;
        System.out.printf("Tamanho original...: %d bytes\n", tamanhoOriginal);
        System.out.printf("Tamanho comprimido.: %d bytes\n", tamanhoComprimido);
        System.out.printf("Taxa de compressao.: %.2f%%\n", taxa);
        System.out.println();

    }
}