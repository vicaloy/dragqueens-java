package logica;

import java.util.List;

import logica.excepciones.PersistenciaException;
import logica.valueObjects.VODragQueenVictorias;
import persistencia.abstractFactory.FabricaAbstracta;
import persistencia.daos.IDAODragQueens;
import persistencia.poolConexiones.IConexion;
import utils.Config;;

public class Temporada {

	private int nroTemp;
	private int anio;
	private int cantCapitulos;
	private IDAODragQueens idaoDragQueens;


	public Temporada(int nroTemp, int anio, int cantCapitulos) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		this.nroTemp = nroTemp;
		this.anio = anio;
		this.cantCapitulos = cantCapitulos;
		
		String strClase = (String) Config.getInstance().get(Config.fabrica_Persistencia);
		FabricaAbstracta fa = (FabricaAbstracta) Class.forName(strClase).newInstance();
		this.idaoDragQueens = fa.crearIDAODragQueens(nroTemp);
	}

	public int getNroTemporada() {
		return this.nroTemp;
	}

	public int getAnio() {
		return this.anio;
	}

	public int getCantCapitulos() {
		return this.cantCapitulos;
	}

	public int getCantParticipantes(IConexion icon) throws PersistenciaException {
		return this.idaoDragQueens.largo(icon);
	}

	public boolean tieneDragQueen(IConexion icon, int nroPart) throws PersistenciaException {
		DragQueen dragQueen = this.idaoDragQueens.kesima(icon, nroPart);
		return dragQueen != null;
	}

	public void inscribirDragQueen(IConexion icon, DragQueen drag) throws PersistenciaException {
		this.idaoDragQueens.insback(icon, drag);
	}

	public DragQueen obtenerDragQueen(IConexion icon, int nroPart) throws PersistenciaException {
		return this.idaoDragQueens.kesima(icon, nroPart);
	}

	public List<VODragQueenVictorias> listarDragQueens(IConexion icon) throws PersistenciaException {
		return this.idaoDragQueens.listarDragQueens(icon);
	}

	public void registrarVictoria(IConexion icon, int nroPart) throws PersistenciaException {
		this.idaoDragQueens.registrarVictoria(icon, nroPart);
	}

	public VODragQueenVictorias obtenerGanadora(IConexion icon) throws PersistenciaException {
		return this.idaoDragQueens.obtenerGanadora(icon);
	}

}
