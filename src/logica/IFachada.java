package logica;

import java.rmi.Remote;
import java.util.List;

import logica.valueObjects.VODragQueen;
import logica.valueObjects.VODragQueenVictorias;
import logica.valueObjects.VOTempMaxParts;
import logica.valueObjects.VOTemporada;

public interface IFachada extends Remote {

	public void nuevaTemporada(VOTemporada voT) throws Exception;

	public void inscribirDragQueen(VODragQueen voD) throws Exception;

	public List<VOTemporada> listarTemporadas() throws Exception;

	public List<VODragQueenVictorias> listarDragQueens(int nroTemp)
			throws Exception;

	public VOTempMaxParts tempMasParticipantes() throws Exception;

	public void registrarVictoria(int nroTemp, int NroPart)
			throws Exception;

	public VODragQueenVictorias obtenerGanadora(int nroTemp)
			throws Exception;
}