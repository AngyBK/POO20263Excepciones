package logica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class BancoTest {

    private Banco banco;
    private int cuenta1;
    private int cuenta2;

    @BeforeEach
    public void setUp() throws Exception {

        banco = new Banco();


        banco.crearUsuario(1, "Juan", "Calderon");

       
        cuenta1 = banco.crearCuenta(1);
        cuenta2 = banco.crearCuenta(1);

    }

    @Test
    public void testCrearUsuarioYCuentas() {

        assertNotEquals(cuenta1, cuenta2);

    }

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