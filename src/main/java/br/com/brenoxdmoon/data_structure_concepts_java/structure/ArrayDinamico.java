package br.com.brenoxdmoon.data_structure_concepts_java.structure;

import java.util.Arrays;
import java.util.Objects;

public class ArrayDinamico<T> {
    private Object[] dados;
    private Integer tamanho;
    private Integer capacidade;

    public static final Integer TAMANHO_MAXIMO_ARRAY = Integer.MAX_VALUE - 8;
    private static final Object[] CAPACIDADE_PADRAO_DADOS_VAZIO = {};
    private static final Integer CAPACIDADE_PADRAO = 10;

    public ArrayDinamico(Integer capacidade) {
        this.capacidade = capacidade;
        this.dados = new Object[capacidade];
    }

    public void adicionar(T elemento) {
        if (Objects.equals(tamanho, capacidade)) {
            adicionar(elemento, dados, tamanho);
        }
    }

    private void adicionar(T elemento, Object[] dados, Integer tamanho) {
        if (tamanho == dados.length) {
            dados = aumentarCapacidade();
        }
    }

    private Object[] aumentarCapacidade() {
        Integer capacidadeAntiga = dados.length;
        Integer capacidadeMinima = Math.max(capacidadeAntiga + 1, 0);
        if (capacidadeAntiga == 0 || dados != CAPACIDADE_PADRAO_DADOS_VAZIO) {
            Integer crescimento = capacidadeAntiga >> 1;
            Integer novaCapacidade = novoTamanho(capacidadeAntiga, capacidadeMinima - capacidadeAntiga, crescimento);
            return dados = Arrays.copyOf(dados, novaCapacidade);
        } else {
            return dados = new Object[Math.max(CAPACIDADE_PADRAO, capacidadeMinima)];
        }
    }

    private Integer novoTamanho(Integer capacidadeAntiga, Integer crescimentoMinimo, Integer crescimento) {

        Integer tamanhoPrevisto = capacidadeAntiga + Math.max(crescimentoMinimo, crescimento);
        if (0 < tamanhoPrevisto && tamanhoPrevisto <= TAMANHO_MAXIMO_ARRAY) {
            return tamanhoPrevisto;
        } else {
            return aumentarTamanho(capacidadeAntiga, crescimentoMinimo);
        }
    }

    private Integer aumentarTamanho(Integer capacidadeAntiga, Integer crescimentoMinimo) {
        Integer tamanhoMinimo = capacidadeAntiga + crescimentoMinimo;
        if (tamanhoMinimo < 0) {
            throw new OutOfMemoryError();
        } else if (tamanhoMinimo > TAMANHO_MAXIMO_ARRAY) {
            return TAMANHO_MAXIMO_ARRAY;
        } else {
            return tamanhoMinimo;
        }
    }

    public void removerEm(Integer indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Indice " + indice + " fora dos limites do array");
        }
        for (int i = indice; i < tamanho; i++) {
            dados[i] = dados[i + 1];
        }
        dados[tamanho - 1] = null;
        tamanho--;
    }

    public Object recuperar(Integer indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Indice " + indice + " fora dos limites do array");
        }
        return dados[indice];
    }

    public void expandirCapacidade() {
        dados = Arrays.copyOf(dados, dados.length * 2);
    }

    public Integer tamanho() {
        return tamanho;
    }

    public Integer capacidade() {
        return capacidade;
    }

    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(dados, 0, tamanho));
    }

}
