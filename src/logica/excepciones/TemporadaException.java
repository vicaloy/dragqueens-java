package logica.excepciones;

public class TemporadaException extends Exception {
	private static final long serialVersionUID = 1L;
	private String msg;

	public TemporadaException(String nmsg) {
		msg = nmsg;
	}

	public String getMensaje() {
		return msg;
	}

}