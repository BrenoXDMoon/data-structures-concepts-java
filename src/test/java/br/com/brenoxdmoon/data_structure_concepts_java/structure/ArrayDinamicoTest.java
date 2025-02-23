package br.com.brenoxdmoon.data_structure_concepts_java.structure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayDinamicoTest {
    private ArrayDinamico<Integer> array;

    @BeforeEach
    void setUp() {
        array = new ArrayDinamico<>(3);
    }

    @Test
    void testAdicionarElementos() {
        array.adicionar(10);
        array.adicionar(20);
        array.adicionar(30);

        assertEquals(3, array.tamanho(), "O tamanho do array deve ser 3 após inserção de 3 elementos.");
        assertEquals(10, array.recuperar(0));
        assertEquals(20, array.recuperar(1));
        assertEquals(30, array.recuperar(2));
    }

    @Test
    void testExpansaoAutomatica() {
        array.adicionar(1);
        array.adicionar(2);
        array.adicionar(3);
        array.adicionar(4);

        assertTrue(array.capacidade() > 3, "A capacidade do array deve aumentar automaticamente.");
        assertEquals(4, array.tamanho(), "O tamanho do array deve ser atualizado corretamente.");
        assertEquals(4, array.recuperar(3));
    }

    @Test
    void testRemoverElemento() {
        array.adicionar(100);
        array.adicionar(200);
        array.adicionar(300);

        array.remover(1);

        assertEquals(2, array.tamanho(), "O tamanho do array deve ser reduzido após remoção.");
        assertEquals(100, array.recuperar(0));
        assertEquals(300, array.recuperar(1));
    }

    @Test
    void testRemoverElementoForaDoIndice() {
        array.adicionar(10);
        array.adicionar(20);

        assertThrows(IndexOutOfBoundsException.class, () -> array.remover(5),
                "Deve lançar exceção ao tentar remover em índice inválido.");
    }

    @Test
    void testRecuperarElemento() {
        array.adicionar(50);
        array.adicionar(60);

        assertEquals(50, array.recuperar(0));
        assertEquals(60, array.recuperar(1));
    }

    @Test
    void testRecuperarElementoForaDoIndice() {
        array.adicionar(5);

        assertThrows(IndexOutOfBoundsException.class, () -> array.recuperar(2),
                "Deve lançar exceção ao acessar um índice inválido.");
    }

    @Test
    void testToStringCorreto() {
        array.adicionar(1);
        array.adicionar(2);
        array.adicionar(3);

        assertEquals("[1, 2, 3]", array.toString(), "A representação do array deve estar correta.");
    }

    @Test
    void testExpandirCapacidade() {
        int capacidadeInicial = array.capacidade();

        array.expandirCapacidade();

        assertTrue(array.capacidade() > capacidadeInicial,
                "A capacidade do array deve dobrar após a expansão.");
    }

    @Test
    void testExpansaoAutomaticaAoAdicionarElementos() {
        ArrayDinamico<Integer> pequenoArray = new ArrayDinamico<>(2);

        pequenoArray.adicionar(1);
        pequenoArray.adicionar(2);
        int capacidadeAntes = pequenoArray.capacidade();

        pequenoArray.adicionar(3);

        assertTrue(pequenoArray.capacidade() > capacidadeAntes,
                "A capacidade do array deve aumentar automaticamente ao atingir o limite.");
    }

}