package logica;

import java.util.ArrayList;

public class Banco {
	private ArrayList<Cuenta> cuentas;
	private ArrayList<Usuario> usuarios;

	public Banco() {
		this.cuentas = new ArrayList<Cuenta>();
		this.usuarios = new ArrayList<Usuario>();
	}

	public void crearUsuario(int id, String nombre, String apellido) throws Exception {
		if(this.existeUsuario(id)) {
			throw new Exception("El usuario con id " + id + " ya existe");
		}else {
			this.usuarios.add(new Usuario(id, nombre, apellido));
		}		
	}

	private boolean existeUsuario(int id) {
		for(Usuario usuario : this.usuarios) {
			if(usuario.getId() == id) {
				return true;
			}
		}
		return false;	
	}
	
	public int crearCuenta(int id) throws Exception {
		Usuario u = this.buscarUsuario(id);
		if(u == null) {
			throw new Exception("El usuario con id " + id + " no existe");
		}else {
			Cuenta cuentaNueva = new Cuenta(this.cuentas.size()+1, u);
			this.cuentas.add(cuentaNueva);
			u.getCuentas().add(cuentaNueva);
			return cuentaNueva.getNumero();
		}
	}
	
	private Usuario buscarUsuario(int id){ 
		for(Usuario usuario : this.usuarios) {
			if(usuario.getId() == id) {
				return usuario;
			}
		}
		return null;
	}

	public void depositar(int numero, int dinero) throws Exception {
		Cuenta c = this.buscarCuenta(numero);
		if(c == null) {
			throw new Exception("La cuenta con numero " + numero + " no existe");
		}else {
			c.depositar(dinero);
		}
	}
	
	public void retirar(int numero, int dinero) throws Exception {
		Cuenta c = this.buscarCuenta(numero);
		if(c == null) {
			throw new Exception("La cuenta con numero " + numero + " no existe");
		}else {		
			c.retirar(dinero);
		}
	}

	public void transferir(int numeroOrigen, int numeroDestino, int dinero) throws Exception {
		Cuenta cOrigen = this.buscarCuenta(numeroOrigen);
		if(cOrigen == null) {
			throw new Exception("La cuenta con numero " + numeroOrigen + " no existe");
		}else {
			Cuenta cDestino = this.buscarCuenta(numeroDestino);
			if(cDestino == null) {
				throw new Exception("La cuenta con numero " + numeroDestino + " no existe");
			}else {
				cOrigen.retirar(dinero); 
				try {
					cDestino.depositar(dinero); 
				} catch (Exception e) {
					cOrigen.depositar(dinero); 
					throw new Exception("Fallo en la cuenta destino: " + e.getMessage());
				}
			}
		}
	}
	
	private Cuenta buscarCuenta(int numero) {
		for(Cuenta cuenta : this.cuentas) {
			if(cuenta.getNumero() == numero) {
				return cuenta;
			}
		}
		return null;
	}


	public String imprimirCuentasUsuario(int id) throws Exception {
		Usuario u = this.buscarUsuario(id);
		if(u == null) {
			throw new Exception("El usuario con id " + id + " no existe");
		}
		String mensaje = "";
		for(Cuenta cuenta : u.getCuentas()) {
			mensaje += cuenta + "\n";
		}
		return mensaje.isEmpty() ? "El usuario no tiene cuentas." : mensaje;
	}

	public void cambiarEstado(int numero, int estado) throws Exception {
		Cuenta c = this.buscarCuenta(numero);
		if(c == null) {
			throw new Exception("La cuenta con numero " + numero + " no existe");
		}else {
			c.setEstado(estado);
		}
	}

	public void actualizarTope(int numero, int tope) throws Exception {
		Cuenta c = this.buscarCuenta(numero);
		if(c == null) {
			throw new Exception("La cuenta con numero " + numero + " no existe");
		}else {
			c.setTope(tope);
		}
	}
	
	public String imprimirUsuarios() {
		String mensaje = "";
		for(Usuario usuario : this.usuarios) {
			mensaje += usuario + "\n";
		}
		return mensaje;
	}
	
	public String imprimirCuentas() {
		String mensaje = "";
		for(Cuenta cuenta : this.cuentas) {
			mensaje += cuenta + "\n";
		}
		return mensaje;
	}
}

