package logica;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BancoTest {

    @Test
    void testDepositarExitoso() throws Exception {
        Banco banco = new Banco();
        banco.crearCuenta(1);

        banco.depositar(1, 1000);

        assertTrue(true);
    }

    @Test
    void testDepositarCuentaInexistente() {
        Banco banco = new Banco();

        Exception error = assertThrows(Exception.class, () -> {
            banco.depositar(999, 500);
        });

        assertEquals("La cuenta con numero 999 no existe", error.getMessage());
    }

    @Test
    void testRetirarExitoso() throws Exception {
        Banco banco = new Banco();
        banco.crearCuenta(1);
        banco.depositar(1, 1000);

        banco.retirar(1, 400);

        assertTrue(true);
    }

    @Test
    void testRetirarCuentaInexistente() {
        Banco banco = new Banco();

        Exception error = assertThrows(Exception.class, () -> {
            banco.retirar(999, 500);
        });

        assertEquals("La cuenta con numero 999 no existe", error.getMessage());
    }

    @Test
    void testRetirarSaldoInsuficiente() throws Exception {
        Banco banco = new Banco();
        banco.crearCuenta(1);
        banco.depositar(1, 100);

        Exception error = assertThrows(Exception.class, () -> {
            banco.retirar(1, 500);
        });

        assertEquals("La cuenta con numero 1 no tiene saldo suficiente", error.getMessage());
    }

    @Test
    void testTransferirExitoso() throws Exception {
        Banco banco = new Banco();
        banco.crearCuenta(1);
        banco.crearCuenta(2);

        banco.depositar(1, 1000);

        banco.transferir(1, 2, 300);

        assertTrue(true);
    }

    @Test
    void testTransferirOrigenInexistente() {
        Banco banco = new Banco();

        Exception error = assertThrows(Exception.class, () -> {
            banco.crearCuenta(2);
            banco.transferir(999, 2, 500);
        });

        assertEquals("La cuenta con numero 999 no existe", error.getMessage());
    }

    @Test
    void testTransferirDestinoInexistente() throws Exception {
        Banco banco = new Banco();
        banco.crearCuenta(1);

        Exception error = assertThrows(Exception.class, () -> {
            banco.transferir(1, 999, 500);
        });

        assertEquals("La cuenta con numero 999 no existe", error.getMessage());
    }

    @Test
    void testTransferirSaldoInsuficiente() throws Exception {
        Banco banco = new Banco();
        banco.crearCuenta(1);
        banco.crearCuenta(2);

        banco.depositar(1, 100);

        Exception error = assertThrows(Exception.class, () -> {
            banco.transferir(1, 2, 500);
        });

        assertEquals("La cuenta con numero 1 no tiene saldo suficiente", error.getMessage());
    }
}