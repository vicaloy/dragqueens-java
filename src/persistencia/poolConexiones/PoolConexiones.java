package persistencia.poolConexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import logica.excepciones.PersistenciaException;
import utils.Config;

public class PoolConexiones implements IPoolConexiones {
	private int tamanio, creadas, tope;
	private Conexion[] conexiones;
	Config config;

	public PoolConexiones() {
		this.config = Config.getInstance();

		try {
			Class.forName((String) config.get(Config.db_driver));
		} catch (ClassNotFoundException e) {
		}

		tamanio = 4;
		tope = 0;
		conexiones = new Conexion[tamanio];
	}

	public synchronized IConexion obtenerConexion(boolean modifica) throws Exception {

		IConexion retorno = null;
		
		try {
			if (tope > 0) {
				// Tengo para prestar
				retorno = conexiones[tope - 1];
				tope--;
			} else {
				if (creadas < tamanio) {
					// Puedo crear
					String url = (String) config.get(Config.db_url);
					String user = (String) config.get(Config.db_user);
					String password = (String) config.get(Config.db_password);
					Connection con = DriverManager.getConnection(url, user, password);
					con.setTransactionIsolation(Integer.valueOf((String) config.get(Config.db_nivelTransaccionalidad)));
					con.setAutoCommit(false);
					retorno = new Conexion(con);
					creadas++;
				} else {
					// No tengo para prestar ni puedo crear
					// Entonces espero
					while (creadas == tamanio || tope == 0) {
						wait();
					}
					retorno = conexiones[tope - 1];
					tope--;
				}
			}
		} catch (SQLException e) {
			throw new PersistenciaException("Error al conectarse a la base de datos: " + "(" + e.getMessage() + ").");
		} catch (InterruptedException e) {
		}
		return retorno;
	}

	public synchronized void liberarConexion(IConexion con, boolean exito) throws Exception {
		try {
			if (exito) {
				((Conexion) con).getConnection().commit();
			} else {
				((Conexion) con).getConnection().rollback();
			}
			conexiones[tope] = (Conexion) con;
			tope++;
			notifyAll();
		} catch (SQLException e) {
			throw new PersistenciaException("Error al liberar la base de datos: " + "(" + e.getMessage() + ").");
		}
	}

}
