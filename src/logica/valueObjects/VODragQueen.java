package logica.valueObjects;

import java.io.Serializable;

public class VODragQueen implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private int nroTemp;

	public VODragQueen(String nombre, int nroTemp) {
		this.nombre = nombre;
		this.nroTemp = nroTemp;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getNroTemp() {
		return this.nroTemp;
	}

}
