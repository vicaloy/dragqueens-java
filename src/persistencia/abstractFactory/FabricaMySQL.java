package persistencia.abstractFactory;

import persistencia.daos.DAODragQueens;
import persistencia.daos.DAOTemporadas;
import persistencia.daos.IDAODragQueens;
import persistencia.daos.IDAOTemporadas;
import persistencia.poolConexiones.IPoolConexiones;
import persistencia.poolConexiones.PoolConexiones;

public class FabricaMySQL implements FabricaAbstracta{

	@Override
	public IDAODragQueens crearIDAODragQueens(int part) {
		return new DAODragQueens(part);
	}

	@Override
	public IDAOTemporadas crearIDAOTemporadas() {
		return new DAOTemporadas();
	}
	@Override
	public IPoolConexiones crearIPoolConexiones() {
		return new PoolConexiones();
	}

}
