package logica.valueObjects;

import java.io.Serializable;

import logica.Temporada;

public class VOTempMaxParts extends VOTemporada implements Serializable{

	private static final long serialVersionUID = 1L;
	private int cantParticipantes;
	
	public VOTempMaxParts(int nroTemp, int anio, int cantCapitulos, int cantParticipantes) {
		super(nroTemp, anio, cantCapitulos);
		this.cantParticipantes = cantParticipantes;
	}
	
	public VOTempMaxParts(Temporada temporada, int cantParticipantes) {
		super(temporada.getNroTemporada(), temporada.getAnio(), temporada.getCantCapitulos());
		this.cantParticipantes = cantParticipantes;
	}
	
	public int getCantParticipantes () {
		return this.cantParticipantes;
	}

}
