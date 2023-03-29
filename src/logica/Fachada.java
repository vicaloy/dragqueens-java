package logica;

import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import logica.excepciones.DragQueenException;
import logica.excepciones.TemporadaException;
import logica.valueObjects.VODragQueen;
import logica.valueObjects.VODragQueenVictorias;
import logica.valueObjects.VOTempMaxParts;
import logica.valueObjects.VOTemporada;
import persistencia.abstractFactory.FabricaAbstracta;
import persistencia.daos.IDAOTemporadas;
import persistencia.poolConexiones.IConexion;
import persistencia.poolConexiones.IPoolConexiones;
import utils.Config;

public class Fachada extends UnicastRemoteObject implements IFachada {

	private static final long serialVersionUID = 1L;
	private static Fachada instancia;
	private IPoolConexiones iPoolConexiones;
	private IDAOTemporadas iDAOTemporada;


	private Fachada() throws Exception {
		String strClase = (String) Config.getInstance().get(Config.fabrica_Persistencia);
		FabricaAbstracta fa = (FabricaAbstracta) Class.forName(strClase).newInstance();
		iDAOTemporada = fa.crearIDAOTemporadas();
		iPoolConexiones = fa.crearIPoolConexiones();
	};

	public static IFachada getInstancia() throws Exception {
		if (instancia == null) {
			instancia = new Fachada();
		}
		;
		return instancia;
	};

	public void nuevaTemporada(VOTemporada voT) throws Exception {
		boolean modifica = true;
		boolean exito = true;
		IConexion con = this.iPoolConexiones.obtenerConexion(modifica);

		try {
			if (!this.iDAOTemporada.member(con, voT.getNroTemp())) {
				this.iDAOTemporada.insert(con, new Temporada(voT.getNroTemp(), voT.getAnio(), voT.getCantCapitulos()));
				this.iPoolConexiones.liberarConexion(con, exito);
			} else {
				throw new TemporadaException("La temporada ya existe");
			}
		} catch (Exception e) {
			exito = false;
			this.iPoolConexiones.liberarConexion(con, exito);
			throw e;
		}
	};

	public void inscribirDragQueen(VODragQueen voD) throws Exception {
		boolean modifica = true;
		boolean exito = true;
		int cantVictorias = 0;
		IConexion con = this.iPoolConexiones.obtenerConexion(modifica);

		try {
			if (this.iDAOTemporada.member(con, voD.getNroTemp())) {
				Temporada temporada = this.iDAOTemporada.find(con, voD.getNroTemp());
				int ultimo = temporada.getCantParticipantes(con);
				temporada.inscribirDragQueen(con, new DragQueen(ultimo + 1, voD.getNombre(), cantVictorias));
			} else {
				throw new TemporadaException("La temporada no existe");
			}
			this.iPoolConexiones.liberarConexion(con, exito);
		} catch (Exception e) {
			exito = false;
			this.iPoolConexiones.liberarConexion(con, exito);
			throw e;
		}
	};

	public List<VOTemporada> listarTemporadas() throws Exception {
		boolean modifica = false;
		boolean exito = true;
		IConexion con = this.iPoolConexiones.obtenerConexion(modifica);

		try {
			List<VOTemporada> list = iDAOTemporada.listarTemporadas(con);
			this.iPoolConexiones.liberarConexion(con, exito);
			return list;
		} catch (Exception e) {
			exito = false;
			this.iPoolConexiones.liberarConexion(con, exito);
			throw e;
		}
	};

	public List<VODragQueenVictorias> listarDragQueens(int nroTemp) throws Exception {
		boolean modifica = false;
		boolean exito = true;
		IConexion con = this.iPoolConexiones.obtenerConexion(modifica);

		try {
			List<VODragQueenVictorias> list;
			if (this.iDAOTemporada.member(con, nroTemp)) {
				Temporada temporada = this.iDAOTemporada.find(con, nroTemp);
				list = temporada.listarDragQueens(con);
			} else {
				throw new TemporadaException("La temporada no existe");
			}
			this.iPoolConexiones.liberarConexion(con, exito);
			return list;
		} catch (Exception e) {
			exito = false;
			this.iPoolConexiones.liberarConexion(con, exito);
			throw e;
		}
	};

	public VOTempMaxParts tempMasParticipantes() throws Exception {
		boolean modifica = false;
		boolean exito = true;
		IConexion con = this.iPoolConexiones.obtenerConexion(modifica);

		try {
			VOTempMaxParts voTempMaxParts;
			if (!this.iDAOTemporada.esVacio(con)) {
				voTempMaxParts = this.iDAOTemporada.tempMasParticipantes(con);
			} else {
				throw new TemporadaException("No hay temporadas registradas");
			}
			this.iPoolConexiones.liberarConexion(con, exito);
			return voTempMaxParts;
		} catch (Exception e) {
			exito = false;
			this.iPoolConexiones.liberarConexion(con, exito);
			throw e;
		}
	};

	public void registrarVictoria(int nroTemp, int nroPart) throws Exception {
		boolean modifica = true;
		boolean exito = true;
		IConexion con = this.iPoolConexiones.obtenerConexion(modifica);

		try {
			if (this.iDAOTemporada.member(con, nroTemp)) {
				Temporada temporada = this.iDAOTemporada.find(con, nroTemp);
				if (temporada.tieneDragQueen(con, nroPart)) {
					temporada.registrarVictoria(con, nroPart);
				} else {
					throw new DragQueenException("La dragqueen no pertenece a la temporada " + String.valueOf(nroTemp));
				}

			} else {
				throw new TemporadaException("La temporada no existe");
			}
			this.iPoolConexiones.liberarConexion(con, exito);
		} catch (Exception e) {
			exito = false;
			iPoolConexiones.liberarConexion(con, exito);
			throw e;
		}
	};

	public VODragQueenVictorias obtenerGanadora(int nroTemp) throws Exception {
		boolean modifica = false;
		boolean exito = true;
		IConexion con = this.iPoolConexiones.obtenerConexion(modifica);

		try {
			VODragQueenVictorias voDragQueenVictorias = obtenerGanadora(con, nroTemp);
			// VODragQueenVictorias voDragQueenVictorias;
			// if (this.iDAOTemporada.member(con, nroTemp)) {
			// Temporada temporada = this.iDAOTemporada.find(con, nroTemp);
			// voDragQueenVictorias = temporada.obtenerGanadora(con);
			// } else {
			// throw new TemporadaException("La temporada no existe");
			// }
			this.iPoolConexiones.liberarConexion(con, exito);
			return voDragQueenVictorias;
		} catch (Exception e) {
			exito = false;
			this.iPoolConexiones.liberarConexion(con, exito);
			throw e;
		}
	};

	private VODragQueenVictorias obtenerGanadora(IConexion con, int nroTemp) throws Exception {
		VODragQueenVictorias voDragQueenVictorias;
		if (this.iDAOTemporada.member(con, nroTemp)) {
			Temporada temporada = this.iDAOTemporada.find(con, nroTemp);
			voDragQueenVictorias = temporada.obtenerGanadora(con);
		} else {
			throw new TemporadaException("La temporada no existe");
		}
		return voDragQueenVictorias;
	};
}
