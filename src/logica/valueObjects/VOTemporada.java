package logica.valueObjects;

import java.io.Serializable;

import logica.Temporada;

public class VOTemporada implements Serializable {

	private static final long serialVersionUID = 1L;
	private int nroTemp;
	private int anio;
	private int cantCapitulos;

	public VOTemporada(int nroTemp, int anio, int cantCapitulos) {
		this.nroTemp = nroTemp;
		this.anio = anio;
		this.cantCapitulos = cantCapitulos;
	}
	
	public VOTemporada(Temporada temporada) {
		this.nroTemp = temporada.getNroTemporada();
		this.anio = temporada.getAnio();
		this.cantCapitulos = temporada.getCantCapitulos();
	}

	public int getNroTemp() {
		return this.nroTemp;
	}

	public int getAnio() {
		return this.anio;
	}

	public int getCantCapitulos() {
		return this.cantCapitulos;
	}

}
