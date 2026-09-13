package logica;

public class Cuenta {
	private int numero;
	private int saldo;
	private int estado;
	private int tope; 
	private Usuario usuario;
	
	public int getNumero() {
		return numero;
	}
	public int getSaldo() {
		return saldo;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getTope() {
		return tope;
	}
	public void setTope(int tope) {
		this.tope = tope;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Cuenta(int numero, Usuario usuario) {
		super();
		this.numero = numero;
		this.saldo = 0;
		this.estado = 1;
		this.tope = 0;
		this.usuario = usuario;
	}
	
	public void depositar(int dinero) throws Exception { 
		if(this.estado == 0) {
			throw new Exception("La cuenta con numero " + this.numero + " está bloqueada");
		}else if(this.tope > 0 && dinero > this.tope) {
			throw new Exception("El monto a depositar supera el tope de la cuenta (" + this.tope + ")");
		}else {
			this.saldo += dinero;
		}
	}
	
	public void retirar(int dinero) throws Exception {
		if(this.estado == 0) {
			throw new Exception("La cuenta con numero " + this.numero + " está bloqueada");
		}else if(this.saldo < dinero) {
			throw new Exception("La cuenta con numero " + this.numero + " no tiene saldo suficiente");
		}else if(this.tope > 0 && dinero > this.tope) {
			throw new Exception("El monto a retirar supera el tope de la cuenta (" + this.tope + ")");
		}else {
			this.saldo -= dinero;
		}
	}
	
	@Override
	public String toString() {		
		return this.numero + "\t" + this.saldo + "\t" + this.estado + "\t" + this.tope + "\t" + this.usuario;
	}
}