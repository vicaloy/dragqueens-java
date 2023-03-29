package persistencia.daos;

import java.io.File;
import java.util.ArrayList;

import logica.DragQueen;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VODragQueenVictorias;
import logica.valueObjects.VOLogicaArchivoDragQueen;
import logica.valueObjects.VOLogicaArchivoTemporada;
import persistencia.poolConexiones.IConexion;
import persistencia.PersistenciaArchivo;


public class DAODragQueensArchivo implements IDAODragQueens {
	private int nroTemp;
	
	public DAODragQueensArchivo(int part) {
		this.nroTemp = part;
	}
	
	private String getRutaArchivo(IConexion con, int nroTemp) {
		return con.getConnection() + "dragqueens-"+String.valueOf(nroTemp)+".dat";
	}

	private Boolean existeArchivo(String ruta) {
		File file = new File(ruta);
		return file.exists();
	}

	public void insback(IConexion icon, DragQueen drag) throws PersistenciaException {
		String nombreArchivo = getRutaArchivo(icon, nroTemp);
		PersistenciaArchivo pa = new PersistenciaArchivo();
		VOLogicaArchivoDragQueen vologica = null;

		if (existeArchivo(nombreArchivo)) {
			vologica = (VOLogicaArchivoDragQueen) pa.recuperar(nombreArchivo);
		} else {
			vologica = new VOLogicaArchivoDragQueen();
		}
		
		vologica.setDragQueen(drag);
		pa.respaldar(nombreArchivo, vologica);
	}

	public boolean esVacia(IConexion icon) throws PersistenciaException {
		boolean esVacia = true;
		String nombreArchivo = getRutaArchivo(icon, nroTemp);
		if (existeArchivo(nombreArchivo)) {
			PersistenciaArchivo pa = new PersistenciaArchivo();
			VOLogicaArchivoDragQueen vologica = (VOLogicaArchivoDragQueen) pa.recuperar(nombreArchivo);
			if (!vologica.getDragQueens().isEmpty()) {
				esVacia = false;
			}
		}
		return esVacia;
	}

	public int largo(IConexion icon) throws PersistenciaException {
		int largo = 0;
		String nombreArchivo = getRutaArchivo(icon, nroTemp);
		if (existeArchivo(nombreArchivo)) {
			PersistenciaArchivo pa = new PersistenciaArchivo();
			VOLogicaArchivoDragQueen vologica = (VOLogicaArchivoDragQueen) pa.recuperar(nombreArchivo);
			if (!vologica.getDragQueens().isEmpty()) {
				largo = vologica.getDragQueens().size();
			}
		}		
		return largo;
	}

	public DragQueen kesima(IConexion icon, int nroP) throws PersistenciaException {
		DragQueen dq = null;
		String nombreArchivo = getRutaArchivo(icon, nroTemp);
		if (existeArchivo(nombreArchivo)) {
			PersistenciaArchivo pa = new PersistenciaArchivo();
			VOLogicaArchivoDragQueen vologica = (VOLogicaArchivoDragQueen) pa.recuperar(nombreArchivo);
			if (!vologica.getDragQueens().isEmpty()) {
				dq = vologica.getDragQueens().get(nroP - 1);
			}
		}
		return dq;
	}

	public ArrayList<VODragQueenVictorias> listarDragQueens(IConexion icon) throws PersistenciaException {
		ArrayList<VODragQueenVictorias> dqlist = new ArrayList<VODragQueenVictorias>();;
		String nombreArchivo = getRutaArchivo(icon, nroTemp);
		if (existeArchivo(nombreArchivo)) {
			PersistenciaArchivo pa = new PersistenciaArchivo();
			VOLogicaArchivoDragQueen vologica = (VOLogicaArchivoDragQueen) pa.recuperar(nombreArchivo);
			if (!vologica.getDragQueens().isEmpty()) {
				ArrayList<DragQueen> draglist = vologica.getDragQueens();
				for (DragQueen d: draglist) { 
					VODragQueenVictorias nuevodq = new VODragQueenVictorias(d.getNombre(),nroTemp,d.getNroParticipante(),d.getCantVictorias());
					dqlist.add(nuevodq);
				}
			}
		}
		return dqlist;
	}

	public void registrarVictoria(IConexion icon, int nroPart) throws PersistenciaException {
		// TODO Auto-generated method stub
		String nombreArchivo = getRutaArchivo(icon, nroTemp);
		PersistenciaArchivo pa = new PersistenciaArchivo();
		VOLogicaArchivoDragQueen vologica = (VOLogicaArchivoDragQueen) pa.recuperar(nombreArchivo);
		if (!vologica.getDragQueens().isEmpty()) {
			DragQueen dq = vologica.getDragQueens().get(nroPart - 1);
			//DragQueen dqNuevo = new DragQueen(dq.getNroParticipante(),dq.getNombre(),dq.getCantVictorias()+1);
			dq.setCantVictorias(dq.getCantVictorias() + 1);
//			vologica.getDragQueens().set(nroPart, dqNuevo);
			pa.respaldar(nombreArchivo, vologica);
		}
	}

	public VODragQueenVictorias obtenerGanadora(IConexion icon) throws PersistenciaException {
		VODragQueenVictorias ganadora = null;
		String nombreArchivo = getRutaArchivo(icon, nroTemp);

		if (existeArchivo(nombreArchivo)) {
			PersistenciaArchivo pa = new PersistenciaArchivo();
			VOLogicaArchivoDragQueen vologica = (VOLogicaArchivoDragQueen) pa.recuperar(nombreArchivo);
			if (!vologica.getDragQueens().isEmpty()) {
				
				ArrayList<DragQueen> dqlist = vologica.getDragQueens();
							
				int maxVictorias = 0;
				String nombre = null;
				int nroParticipante = 0;
				for (DragQueen d: dqlist) {
					if (d.getCantVictorias() > maxVictorias) {
						maxVictorias = d.getCantVictorias();
						nombre = d.getNombre();
						nroParticipante = d.getNroParticipante();
						ganadora = new VODragQueenVictorias(nombre,nroTemp,nroParticipante,maxVictorias);
					}
				
				}
			}
		}
		return ganadora;
	}

	

}
