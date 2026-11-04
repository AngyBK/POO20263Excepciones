
package Pruebas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Banco;

public class BancoTest {

    private Banco banco;
    private int cuenta1;
    private int cuenta2;

    @BeforeEach
    public void setUp() throws Exception {

        banco = new Banco();

        // Crear usuario
        banco.crearUsuario(1, "Juan", "Calderon");

        // Crear dos cuentas para el usuario
        cuenta1 = banco.crearCuenta(1);
        cuenta2 = banco.crearCuenta(1);
    }

    // PRUEBAS DE DEPOSITAR

    @Test
    public void testDepositar() {

        assertDoesNotThrow(() -> {
            banco.depositar(cuenta1, 50000);
        });

    }

    @Test
    public void testDepositarCuentaInexistente() {

        assertThrows(Exception.class, () -> {
            banco.depositar(100, 50000);
        });

    }

    // PRUEBAS DE RETIRAR

    @Test
    public void testRetirar() throws Exception {

        banco.depositar(cuenta1, 50000);

        assertDoesNotThrow(() -> {
            banco.retirar(cuenta1, 20000);
        });

    }

    @Test
    public void testRetirarCuentaInexistente() {

        assertThrows(Exception.class, () -> {
            banco.retirar(100, 20000);
        });

    }

    // PRUEBAS DE TRANSFERIR

    @Test
    public void testTransferir() throws Exception {

        banco.depositar(cuenta1, 50000);

        assertDoesNotThrow(() -> {
            banco.transferir(cuenta1, cuenta2, 20000);
        });

    }

    @Test
    public void testTransferirCuentaOrigenInexistente() {

        assertThrows(Exception.class, () -> {
            banco.transferir(100, cuenta2, 10000);
        });

    }

    @Test
    public void testTransferirCuentaDestinoInexistente() throws Exception {

        banco.depositar(cuenta1, 50000);

        assertThrows(Exception.class, () -> {
            banco.transferir(cuenta1, 100, 10000);
        });

    }

}

