package persistencia.poolConexiones;

import utils.Config;

public class PoolConexionesArchivo implements IPoolConexiones {
	private int cantLectores = 0;
	private boolean escribiendo = false;
	Config config;

	public PoolConexionesArchivo() {
		this.config = Config.getInstance();
	}

	@Override
	public synchronized IConexion obtenerConexion(boolean modifica) throws Exception {
		if (modifica) {
			try {
				while (escribiendo || cantLectores > 0) {
					wait();
				}
				escribiendo = true;
			} catch (InterruptedException e) {
			}
		} else {
			try {
				while (escribiendo) {
					wait();
				}
				cantLectores++;
			} catch (InterruptedException e) {
			}
		}
		return new ConexionArchivo();
	}

	@Override
	public synchronized void liberarConexion(IConexion con, boolean ok) throws Exception {
		if (escribiendo) {
			escribiendo = false;
			notifyAll();
		} else {
			if (cantLectores > 0) {
				cantLectores--;
			}
			if (cantLectores == 0) {
				notifyAll();
			}
		}

	}

}
