package persistencia.daos;

import java.util.ArrayList;

import logica.Temporada;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOTempMaxParts;
import logica.valueObjects.VOTemporada;
import persistencia.poolConexiones.IConexion;

public interface IDAOTemporadas {
	public boolean member(IConexion con, int nroTemp) throws PersistenciaException;

	public void insert(IConexion con, Temporada temp) throws PersistenciaException;

	public Temporada find(IConexion con, int nroTemp) throws Exception;

	public boolean esVacio(IConexion con) throws PersistenciaException;

	public ArrayList<VOTemporada> listarTemporadas(IConexion con) throws PersistenciaException;

	public VOTempMaxParts tempMasParticipantes(IConexion con) throws PersistenciaException;

}
