package logica;

import java.io.Serializable;

public class DragQueen implements Serializable {
	private static final long serialVersionUID = 1L;
	private int nroPart;
	private String nombre;
	private int cantVictorias;

	public DragQueen(int nroPart, String nombre, int cantVictorias) {
		this.nroPart = nroPart;
		this.nombre = nombre;
		this.cantVictorias = cantVictorias;
	}

	public int getNroParticipante() {
		return this.nroPart;
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getCantVictorias() {
		return this.cantVictorias;
	}

	public int setCantVictorias(int cantVictorias) {
		return this.cantVictorias = cantVictorias;
	}
}
