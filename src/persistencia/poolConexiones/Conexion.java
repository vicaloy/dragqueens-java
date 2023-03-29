package persistencia.poolConexiones;

import java.sql.Connection;

public class Conexion implements IConexion {
	private Connection con;

	public Conexion(Connection c) {
		con = c;
	}

	public Connection getConnection() {
		return con;
	}
}
