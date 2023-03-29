package persistencia.poolConexiones;

import java.io.File;

import utils.Config;

public class ConexionArchivo implements IConexion {
	private Config config;

	public ConexionArchivo() throws Exception {
		this.config = Config.getInstance();
		File file = new File((String) config.get(Config.file_directory));
		file.mkdir();
	}

	@Override
	public String getConnection() {
		return (String) config.get(Config.file_directory);
	}	

}
