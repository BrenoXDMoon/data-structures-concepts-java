package br.com.brenoxdmoon.data_structure_concepts_java.structure;

import java.util.Arrays;

public class ArrayDinamico<T> {
    private Object[] dados;
    private int tamanho = 0;
    private int capacidade;

    public static final int TAMANHO_MAXIMO_ARRAY = Integer.MAX_VALUE - 8;
    private static final Object[] CAPACIDADE_PADRAO_DADOS_VAZIO = {};
    private static final int CAPACIDADE_PADRAO = 10;

    public ArrayDinamico(int capacidade) {
        this.capacidade = capacidade;
        this.dados = new Object[capacidade];
    }

    public void adicionar(T elemento) {
        if (tamanho == dados.length) {
            adicionar(elemento, dados, tamanho);
        }
        dados[tamanho] = elemento;
        tamanho++;
    }

    private void adicionar(T ignoredElemento, Object[] dados, int tamanho) {
        if (tamanho == dados.length) {
            dados = aumentarCapacidade();
        }
    }

    private Object[] aumentarCapacidade() {
        int capacidadeAntiga = dados.length;
        int capacidadeMinima = Math.max(capacidadeAntiga + 1, 0);
        if (capacidadeAntiga == 0 || dados != CAPACIDADE_PADRAO_DADOS_VAZIO) {
            int crescimento = capacidadeAntiga >> 1;
            int novaCapacidade = novoTamanho(capacidadeAntiga, capacidadeMinima - capacidadeAntiga, crescimento);
            this.capacidade = novaCapacidade;
            return dados = Arrays.copyOf(dados, novaCapacidade);
        } else {
            this.capacidade = Math.max(CAPACIDADE_PADRAO, capacidadeMinima);
            dados = new Object[this.capacidade];
            return dados;
        }
    }

    private int novoTamanho(int capacidadeAntiga, int crescimentoMinimo, int crescimento) {

        int tamanhoPrevisto = capacidadeAntiga + Math.max(crescimentoMinimo, crescimento);
        if (0 < tamanhoPrevisto && tamanhoPrevisto <= TAMANHO_MAXIMO_ARRAY) {
            return tamanhoPrevisto;
        } else {
            return aumentarTamanho(capacidadeAntiga, crescimentoMinimo);
        }
    }

    private int aumentarTamanho(int capacidadeAntiga, int crescimentoMinimo) {
        int tamanhoMinimo = capacidadeAntiga + crescimentoMinimo;
        if (tamanhoMinimo < 0) {
            throw new OutOfMemoryError();
        } else return Math.min(tamanhoMinimo, TAMANHO_MAXIMO_ARRAY);
    }

    public void remover(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice " + indice + " está fora dos limites do array.");
        }

        int novoTamanho = tamanho - 1;
        if(novoTamanho > indice) {
            System.arraycopy(dados, indice + 1, dados, indice, novoTamanho - indice);
        }
        dados[tamanho = novoTamanho] = null;
    }

    public Object recuperar(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Indice " + indice + " fora dos limites do array");
        }
        return dados[indice];
    }

    public void expandirCapacidade() {
        dados = Arrays.copyOf(dados, dados.length * 2);
    }

    public int tamanho() {
        return tamanho;
    }

    public int capacidade() {
        return capacidade;
    }

    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(dados, 0, tamanho));
    }

}
