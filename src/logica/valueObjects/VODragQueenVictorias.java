package logica.valueObjects;

import java.io.Serializable;

public class VODragQueenVictorias extends VODragQueen implements Serializable {

	private static final long serialVersionUID = 1L;
	private int nroPart;
	private int cantVictorias;

	public VODragQueenVictorias(String nombre, int nroTemp, int nroPart, int cantVictorias) {
		super(nombre, nroTemp);
		this.nroPart = nroPart;
		this.cantVictorias = cantVictorias;
	}

	public int getNroParticipante() {
		return this.nroPart;
	}

	public int getCantVictorias() {
		return this.cantVictorias;
	}

}
