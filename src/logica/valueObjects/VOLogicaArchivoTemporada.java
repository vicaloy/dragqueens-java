package logica.valueObjects;

import java.io.Serializable;

import logica.Temporada;

public class VOLogicaArchivoTemporada implements IVOLogicaArchivo, Serializable {
	private static final long serialVersionUID = 1L;
	private int nroTemp;
	private int anio;
	private int cantCapitulos;
	
	public VOLogicaArchivoTemporada(Temporada temporada) {
		this.nroTemp = temporada.getNroTemporada();
		this.anio = temporada.getAnio();
		this.cantCapitulos = temporada.getCantCapitulos();
	}

	public Temporada getTemporada() throws Exception {
		return new Temporada(this.nroTemp, this.anio, this.cantCapitulos);
	}

	public void setTemporada(Temporada temporada) {
		this.nroTemp = temporada.getNroTemporada();
		this.anio = temporada.getAnio();
		this.cantCapitulos = temporada.getCantCapitulos();
	}
}
