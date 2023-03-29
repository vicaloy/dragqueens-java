package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class CrearBD {

	public static void main(String args[]) {
		try {
			inicializar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		;
	};

	public static void inicializar() throws SQLException {
		Properties properties = new Properties();
		InputStream input = null;
		Connection con = null;

		try {
			String file = "src/utils/config.properties";
			input = new FileInputStream(file);
			properties.load(input);
			String db_driver = properties.getProperty("db_driver");
			String db_user = properties.getProperty("db_user");
			String db_password = properties.getProperty("db_password");
			String db_url = properties.getProperty("db_url");

			Class.forName(db_driver);
			con = DriverManager.getConnection(db_url, db_user, db_password);

			CrearEsquema(con);
			CargaBasica(con);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			if (con != null) {
				con.close();
			}
			;
		}
	}

	public static void CrearEsquema(Connection con) throws SQLException {
		String crear = "create database Certamen;";

		String crearTemporadas = "create table Certamen.Temporadas (" + "nroTemp int not null primary key,"
				+ "anio int not null," + "cantCapitulos int" + " );";

		String crearDragQueens = "create table Certamen.DragQueens (" + "nroPart int not null,"
				+ "nombre varchar(60) not null ," + "cantVictorias int,"
				+ "nroTemp int not null references Certamen.Temporadas (nroTemp)," + "primary key (nroPart, nroTemp)"
				+ ");";

		PreparedStatement st = con.prepareStatement(crear);
		st.executeUpdate(crear);
		st = con.prepareStatement(crearTemporadas);
		st.executeUpdate();
		st = con.prepareStatement(crearDragQueens);
		st.executeUpdate();
		st.close();
	};

	public static void CargaBasica(Connection con) {

	};
}
