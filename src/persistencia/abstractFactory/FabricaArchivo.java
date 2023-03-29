package persistencia.abstractFactory;

import persistencia.daos.DAODragQueensArchivo;
import persistencia.daos.DAOTemporadasArchivo;
import persistencia.daos.IDAODragQueens;
import persistencia.daos.IDAOTemporadas;
import persistencia.poolConexiones.IPoolConexiones;
import persistencia.poolConexiones.PoolConexionesArchivo;

public class FabricaArchivo implements FabricaAbstracta{

	public IDAODragQueens crearIDAODragQueens(int part) {
		return new DAODragQueensArchivo(part);
	}

	@Override
	public IDAOTemporadas crearIDAOTemporadas() {
		return new DAOTemporadasArchivo();
	}
	@Override
	public IPoolConexiones crearIPoolConexiones() {
		return new PoolConexionesArchivo();
	}

}
