package presentacion;

import java.util.Scanner;
import logica.Banco;

public class Principal {
	private Banco banco;
	private Scanner sc;
	
	public Principal() {
		this.banco = new Banco();
		this.sc = new Scanner(System.in);
		this.menu();
		this.sc.close();
	}

	private void menu() {
		int op;
		do {
			System.out.println("----\nDigite\n"
					+ "0. Salir\n"
					+ "1. Crear Usuario\n"
					+ "2. Crear Cuenta\n"
					+ "3. Depositar\n"
					+ "4. Retirar\n"
					+ "5. Transferir\n"
					+ "6. Imprimir Usuarios\n"
					+ "7. Imprimir Cuentas\n"
					+ "8. Consultar cuentas de un usuario\n"
					+ "9. Cambiar estado de una cuenta\n"
					+ "10. Actualizar tope de una cuenta\n"
					+ "----\n");
			op = sc.nextInt();
			if(op == 1) {
				this.crearUsuario();
			}else if(op == 2) {
				this.crearCuenta();
			}else if(op == 3) {
				this.depositar();
			}else if(op == 4) {
				this.retirar();
			}else if(op == 5) {
				this.transferir();
			}else if(op == 6) {
				this.imprimirUsuarios();
			}else if(op == 7) {
				this.imprimirCuentas();
			}else if(op == 8) {
				this.consultarCuentasUsuario();
			}else if(op == 9) {
				this.cambiarEstado();
			}else if(op == 10) {
				this.actualizarTope();
			}
		}while(op != 0);
	}

	private void consultarCuentasUsuario() {
		System.out.println("Digite id del usuario: ");
		int id = this.sc.nextInt();
		try {
			System.out.println("Num\tSaldo\tEstado\tTope\tId\tNombre\tApellido");
			System.out.println(this.banco.imprimirCuentasUsuario(id));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void cambiarEstado() {
		System.out.println("Digite numero de cuenta: ");
		int numero = this.sc.nextInt();
		System.out.println("Digite estado (1=Activa, 0=Bloqueada): ");
		int estado = this.sc.nextInt();
		try {
			this.banco.cambiarEstado(numero, estado);
			System.out.println("Estado de la cuenta actualizado exitosamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void actualizarTope() {
		System.out.println("Digite numero de cuenta: ");
		int numero = this.sc.nextInt();
		System.out.println("Digite el nuevo tope (0 = Sin tope): ");
		int tope = this.sc.nextInt();
		try {
			this.banco.actualizarTope(numero, tope);
			System.out.println("Tope de la cuenta actualizado exitosamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void imprimirCuentas() {
		System.out.println("Num\tSaldo\tEstado\tTope\tId\tNombre\tApellido");
		System.out.println(this.banco.imprimirCuentas());
	}

	private void imprimirUsuarios() {
		System.out.println("Id\tNombre\tApellido");
		System.out.println(this.banco.imprimirUsuarios());
	}

	private void transferir() {
		System.out.println("Digite numero origen: ");
		int numeroOrigen = this.sc.nextInt();
		System.out.println("Digite numero destino: ");
		int numeroDestino = this.sc.nextInt();
		System.out.println("Digite dinero: ");
		int dinero = this.sc.nextInt();
		try {
			this.banco.transferir(numeroOrigen, numeroDestino, dinero);
			System.out.println("Transferencia realizada con éxito.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}

	private void retirar() {
		System.out.println("Digite numero: ");
		int numero = this.sc.nextInt();
		System.out.println("Digite dinero: ");
		int dinero = this.sc.nextInt();
		try {
			this.banco.retirar(numero, dinero);
			System.out.println("Retiro exitoso.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}

	private void depositar() {
		System.out.println("Digite numero: ");
		int numero = this.sc.nextInt();
		System.out.println("Digite dinero: ");
		int dinero = this.sc.nextInt();
		try {
			this.banco.depositar(numero, dinero);
			System.out.println("Depósito exitoso.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void crearCuenta() {
		System.out.println("Digite id del usuario: ");
		int id = this.sc.nextInt();
		try {
			System.out.println("Se creo la cuenta con numero " + this.banco.crearCuenta(id));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void crearUsuario() {
		System.out.println("Digite id: ");
		int id = this.sc.nextInt();
		System.out.println("Digite nombre: ");
		String nombre = this.sc.next();
		System.out.println("Digite apellido: ");
		String apellido = this.sc.next();
		try {
			this.banco.crearUsuario(id, nombre, apellido);
			System.out.println("Usuario creado con éxito.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Principal();
	}
}