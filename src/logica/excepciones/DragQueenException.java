package logica.excepciones;

public class DragQueenException extends Exception {
	private static final long serialVersionUID = 1L;
	private String msg;

	public DragQueenException(String nmsg) {
		msg = nmsg;
	}

	public String getMensaje() {
		return msg;
	}

}