package persistencia.abstractFactory;

import persistencia.daos.IDAODragQueens;
import persistencia.daos.IDAOTemporadas;
import persistencia.poolConexiones.IPoolConexiones;

public interface FabricaAbstracta {
	
	public IDAODragQueens crearIDAODragQueens (int part);
	public IDAOTemporadas crearIDAOTemporadas ();
	public IPoolConexiones crearIPoolConexiones();
}
