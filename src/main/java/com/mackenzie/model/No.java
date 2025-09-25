/**
 * Projeto 1 de Estrutura de Dados II.
 * Prof. Dr. Jean M. Laine.
 *
 * Integrantes do grupo:
 * - Gabriel Ferreira - RA: 10442043.
 * - Kaiki Bellini Barbosa - RA: 10402509.
 * - Leonardo Binder - RA: 10402289.
 */

package com.mackenzie.model;

public class No implements Comparable<No>{
    private char caractere;
    private int frequencia;
    private No esquerda;
    private No direita;

    // nó folha
    public No(char caractere, int frequencia) {
        this.caractere = caractere;
        this.frequencia = frequencia;
    }

    //nó sem caractere
    public No(int frequencia, No esquerda, No direita) {
        this.frequencia = frequencia;
        this.esquerda = esquerda;
        this.direita = direita;
    }

    public char getCaractere() {
        return caractere;
    }

    public void setCaractere(char caractere) {
        this.caractere = caractere;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public boolean isFolha() {
        if(this.esquerda == null && this.direita == null)
            return true;

        return false;
    }

    @Override
    public String toString(){
        String prefixo = "Nó";

        if(isFolha())
            return prefixo + "(" + this.caractere + " " + this.frequencia + ")";
        return prefixo + "(interno, " + this.frequencia + ")";
    }

    @Override
    public int compareTo(No outroNo) {
        return Integer.compare(this.frequencia, outroNo.frequencia);
    }
}
