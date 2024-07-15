package ec.edu.ups.ppw.ProyectoFinal.Services;

public class message {
	
	private int codigo;
	private String mensaje;
	
	public static int OK = 1;
	public static int ERROR = 99;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public message(int codigo, String mensaje) {
		super();
		this.codigo = codigo;
		this.mensaje = mensaje;
	}

}
