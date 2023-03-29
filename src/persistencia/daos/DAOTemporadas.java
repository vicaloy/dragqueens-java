package persistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logica.Temporada;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOTempMaxParts;
import logica.valueObjects.VOTemporada;
import persistencia.consultas.Consultas;
import persistencia.poolConexiones.IConexion;

public class DAOTemporadas implements IDAOTemporadas {

	public DAOTemporadas() {
		// TODO Auto-generated constructor stub
	}

	public boolean member(IConexion icon, int nroTemp) throws PersistenciaException {
		boolean existe = false;

		Consultas consultas = new Consultas();
		String query = consultas.obtenerTemporada();

		try {
			PreparedStatement pstm = ((Connection) icon.getConnection()).prepareStatement(query);
			pstm.setInt(1, nroTemp);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				existe = true;
			}

			rs.close();
			pstm.close();
		} catch (SQLException e) {
			PersistenciaException per = new PersistenciaException("Error al acceder a los datos");
			throw per;
		}
		return existe;

	};

	public void insert(IConexion icon, Temporada temp) throws PersistenciaException {
		Consultas consultas = new Consultas();
		String query = consultas.insertarTemporada();

		try {
			PreparedStatement pstm = ((Connection) icon.getConnection()).prepareStatement(query);
			pstm.setInt(1, temp.getNroTemporada());
			pstm.setInt(2, temp.getAnio());
			pstm.setInt(3, temp.getCantCapitulos());
			pstm.execute();

			pstm.close();
		} catch (SQLException e) {
			PersistenciaException per = new PersistenciaException("Error al acceder a los datos");
			throw per;
		}
	};

	public Temporada find(IConexion icon, int nroTemp) throws Exception {
		Temporada temp = null;

		Consultas consultas = new Consultas();
		String query = consultas.obtenerTemporada();

		try {
			PreparedStatement pstm = ((Connection) icon.getConnection()).prepareStatement(query);
			pstm.setInt(1, nroTemp);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				temp = new Temporada(rs.getInt(1), rs.getInt(2), rs.getInt(3));
			}
			;

			rs.close();
			pstm.close();

		} catch (SQLException e) {
			PersistenciaException per = new PersistenciaException("Error al acceder a los datos");
			throw per;
		}

		return temp;
	};

	public boolean esVacio(IConexion icon) throws PersistenciaException {
		boolean vacio = false;
		Consultas consultas = new Consultas();
		String query = consultas.existenTemporadas();

		try {
			Statement st = ((Connection) icon.getConnection()).createStatement();
			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				vacio = rs.getInt(1) == 0;

			}

			rs.close();
			st.close();

		} catch (SQLException e) {
			PersistenciaException per = new PersistenciaException("Error al acceder a los datos");
			throw per;
		}
		;
		return vacio;

	};

	public ArrayList<VOTemporada> listarTemporadas(IConexion icon) throws PersistenciaException {

		ArrayList<VOTemporada> temporadas = new ArrayList<VOTemporada>();
		Consultas consultas = new Consultas();
		String query = consultas.listarTemporadas();

		try {
			Statement st = ((Connection) icon.getConnection()).createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				VOTemporada temp = new VOTemporada(rs.getInt(1), rs.getInt(2), rs.getInt(3));
				temporadas.add(temp);
			}

			rs.close();
			st.close();

		} catch (SQLException e) {
			PersistenciaException per = new PersistenciaException("Error al acceder a los datos");
			throw per;
		}
		;

		return temporadas;
	};

	public VOTempMaxParts tempMasParticipantes(IConexion icon) throws PersistenciaException {
			VOTempMaxParts tempo = null;
			Consultas consultas = new Consultas();
			String query = consultas.obtenerTemporadaMasParticipantes();
			try {
				Statement st = ((Connection) icon.getConnection()).createStatement();
				ResultSet rs = st.executeQuery(query);
				
				if (rs.next()) {
					tempo = new VOTempMaxParts(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
				};
				
				rs.close();
				st.close();
				
			} catch (SQLException e) {
				PersistenciaException per = new PersistenciaException("Error al acceder a los datos");
				throw per;
			};
			
			return tempo;
		}

}
