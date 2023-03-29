package persistencia.poolConexiones;

//import logica.excepciones.PersistenciaException;

public interface IPoolConexiones {

	public IConexion obtenerConexion(boolean modifica) throws Exception;

	public void liberarConexion(IConexion con, boolean ok) throws Exception;
}
