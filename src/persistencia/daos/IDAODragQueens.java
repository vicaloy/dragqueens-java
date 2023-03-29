package persistencia.daos;

import java.util.ArrayList;

import logica.DragQueen;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VODragQueenVictorias;
import persistencia.poolConexiones.IConexion;

public interface IDAODragQueens {
	public void insback(IConexion icon, DragQueen drag) throws PersistenciaException;

	public boolean esVacia(IConexion icon) throws PersistenciaException;

	public int largo(IConexion icon) throws PersistenciaException;

	public DragQueen kesima(IConexion icon, int nroP) throws PersistenciaException;

	public ArrayList<VODragQueenVictorias> listarDragQueens(IConexion icon) throws PersistenciaException;

	public void registrarVictoria(IConexion icon, int nroPart) throws PersistenciaException;

	public VODragQueenVictorias obtenerGanadora(IConexion icon) throws PersistenciaException;

}
