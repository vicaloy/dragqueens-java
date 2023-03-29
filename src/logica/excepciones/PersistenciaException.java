package logica.excepciones;

public class PersistenciaException extends Exception {
	private static final long serialVersionUID = 1L;
	private String msg;

	public PersistenciaException(String nmsg) {
		msg = nmsg;
	}

	public String getMensaje() {
		return msg;
	}

}
