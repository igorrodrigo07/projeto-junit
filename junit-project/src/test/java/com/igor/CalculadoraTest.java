package com.igor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    @Test
    void deveSomarDoisNumeros() {
        Calculadora calc = new Calculadora();
        int resultado = calc.somar(2, 3);

        assertEquals(5, resultado);

        TestRailClient client = new TestRailClient();
        client.enviarResultado(50, 1);
    }

    @Test
    void deveSubtrairDoisNumeros() {
        Calculadora calc = new Calculadora();
        int resultado = calc.subtrair(5, 3);

        assertEquals(2, resultado);

        TestRailClient client = new TestRailClient();
        client.enviarResultado(51, 1);
    }

    @Test
    void deveLancarErroAoDividirPorZero() {
        Calculadora calc = new Calculadora();

        assertThrows(IllegalArgumentException.class, () -> {
            calc.dividir(10, 0);
        });

        TestRailClient client = new TestRailClient();
        client.enviarResultado(52, 1);
    }
}