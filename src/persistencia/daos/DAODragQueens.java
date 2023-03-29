package persistencia.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import logica.DragQueen;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VODragQueenVictorias;
import persistencia.consultas.Consultas;
import persistencia.poolConexiones.IConexion;

public class DAODragQueens implements IDAODragQueens {
	private int nroTemp;

	public DAODragQueens(int nroTemp) {
		this.nroTemp = nroTemp;
	};

	public void insback(IConexion icon, DragQueen drag) throws PersistenciaException {
		Consultas consultas = new Consultas();
		String query = consultas.insertarDragQueen();
		try {
			PreparedStatement pstm = ((Connection) icon.getConnection()).prepareStatement(query);
			pstm.setInt(1, drag.getNroParticipante());
			pstm.setString(2, drag.getNombre());
			pstm.setInt(3, drag.getCantVictorias());
			pstm.setInt(4, this.nroTemp);

			pstm.executeUpdate();
			pstm.close();

		} catch (SQLException e) {
			PersistenciaException per = new PersistenciaException("Error al acceder a los datos");
			throw per;
		}
		;
	};

	public boolean esVacia(IConexion icon) throws PersistenciaException {
		boolean vacio = false;

		Consultas consultas = new Consultas();
		String query = consultas.existenDragQueens();

		try {
			PreparedStatement pstm = ((Connection) icon.getConnection()).prepareStatement(query);
			pstm.setInt(1, this.nroTemp);
			ResultSet rs = pstm.executeQuery();

			if (rs.getInt(1) == 0) {
				vacio = true;
			}
			;

			rs.close();
			pstm.close();

		} catch (SQLException e) {

			PersistenciaException per = new PersistenciaException("Error al acceder a los datos");
			throw per;
		}
		;

		return vacio;

	};

	public int largo(IConexion icon) throws PersistenciaException {
		int cantidad = 0;
		Consultas consultas = new Consultas();
		String query = consultas.cantidadDragQueens();

		try {
			PreparedStatement pstm = ((Connection) icon.getConnection()).prepareStatement(query);
			pstm.setInt(1, this.nroTemp);
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				cantidad = rs.getInt(1);
			}
			;

			rs.close();
			pstm.close();

		} catch (SQLException e) {
			PersistenciaException per = new PersistenciaException("Error al acceder a los datos");
			throw per;
		}
		;

		return cantidad;

	};

	public DragQueen kesima(IConexion icon, int nroP) throws PersistenciaException {
		DragQueen dq = null;

		Consultas consultas = new Consultas();
		String query = consultas.obtenerDragQueen();

		try {
			PreparedStatement pstm = ((Connection) icon.getConnection()).prepareStatement(query);
			pstm.setInt(1, nroP);
			pstm.setInt(2, this.nroTemp);
			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				dq = new DragQueen(rs.getInt(1), rs.getString(2), rs.getInt(3));
			}

			rs.close();
			pstm.close();

		} catch (SQLException e) {

			PersistenciaException per = new PersistenciaException("Error al acceder a los datos");
			throw per;
		}
		return dq;

	};

	public ArrayList<VODragQueenVictorias> listarDragQueens(IConexion icon) throws PersistenciaException {
		ArrayList<VODragQueenVictorias> lista = new ArrayList<VODragQueenVictorias>();

		Consultas consultas = new Consultas();
		String query = consultas.listarDragQueens();

		try {
			PreparedStatement pstm = ((Connection) icon.getConnection()).prepareStatement(query);
			pstm.setInt(1, this.nroTemp);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				VODragQueenVictorias dq = new VODragQueenVictorias(rs.getString(2), this.nroTemp, rs.getInt(1),
						rs.getInt(3));
				lista.add(dq);
			}

			rs.close();
			pstm.close();

		} catch (SQLException e) {
			PersistenciaException per = new PersistenciaException("Error al acceder a los datos");
			throw per;
		}
		;

		return lista;

	};

	public void registrarVictoria(IConexion icon, int nroPart) throws PersistenciaException {
		Consultas consultas = new Consultas();
		String query = consultas.registrarVictoriaDragQueen();
	
		try {
			PreparedStatement pstm = ((Connection) icon.getConnection()).prepareStatement(query);
			pstm.setInt(1, this.nroTemp);
			pstm.setInt(2, nroPart);

			pstm.executeUpdate();
			pstm.close();

		} catch (SQLException e) {
			PersistenciaException per = new PersistenciaException("Error al acceder a los datos");
			throw per;
		}
		;
	};

	public VODragQueenVictorias obtenerGanadora(IConexion icon) throws PersistenciaException {
		VODragQueenVictorias dq = null;

		Consultas consultas = new Consultas();
		String query = consultas.obtenerGanadora();

		try {
			PreparedStatement pstm = ((Connection) icon.getConnection()).prepareStatement(query);
			pstm.setInt(1, this.nroTemp);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				dq = new VODragQueenVictorias(rs.getString(1), this.nroTemp, rs.getInt(2), rs.getInt(3));
			}

			rs.close();
			pstm.close();

		} catch (SQLException e) {
			PersistenciaException per = new PersistenciaException("Error al acceder a los datos");
			throw per;
		}
		;
		return dq;

	}

}
