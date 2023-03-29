package persistencia.daos;

import java.util.ArrayList;
import java.io.File;

import logica.Temporada;
import logica.excepciones.PersistenciaException;
import logica.valueObjects.VOTempMaxParts;
import logica.valueObjects.VOTemporada;
import logica.valueObjects.VOLogicaArchivoTemporada;
import logica.valueObjects.IVOLogicaArchivo;
import persistencia.PersistenciaArchivo;
import persistencia.poolConexiones.IConexion;

public class DAOTemporadasArchivo implements IDAOTemporadas {
	private String getRutaArchivo(IConexion con, int nroTemp) {
		return con.getConnection() + "temporada-"+String.valueOf(nroTemp)+".dat";
	}
	
	private Boolean existeArchivo(String ruta) {
		File file = new File(ruta);
		return file.exists();
	}

	public boolean member(IConexion con, int nroTemp) throws PersistenciaException {
		String nombreArchivo = getRutaArchivo(con, nroTemp);
		return existeArchivo(nombreArchivo);
	}

	public void insert(IConexion con, Temporada temp) throws PersistenciaException {
		String nombreArchivo = getRutaArchivo(con, temp.getNroTemporada());
		
		if (!existeArchivo(nombreArchivo)) {
			PersistenciaArchivo pa = new PersistenciaArchivo();
			IVOLogicaArchivo iVOLogicaArchivo = new VOLogicaArchivoTemporada(temp);
			pa.respaldar(nombreArchivo, iVOLogicaArchivo);
		} else {
			throw new PersistenciaException("La temporada ya existe");
		}
	}

	public Temporada find(IConexion con, int nroTemp) throws Exception {
		String nombreArchivo = getRutaArchivo(con, nroTemp);
		
		if (existeArchivo(nombreArchivo)) {
			PersistenciaArchivo pa = new PersistenciaArchivo();
			IVOLogicaArchivo iVOLogicaArchivo = pa.recuperar(nombreArchivo);
			VOLogicaArchivoTemporada vOLogicaArchivoTemporada = (VOLogicaArchivoTemporada) iVOLogicaArchivo;
			return vOLogicaArchivoTemporada.getTemporada();
		} else {
			throw new PersistenciaException("La temporada no existe");
		}
	}

	public boolean esVacio(IConexion con) throws PersistenciaException {
		ArrayList<VOTemporada> array = listarTemporadas(con);
		return array.isEmpty();
	}

	public ArrayList<VOTemporada> listarTemporadas(IConexion con) throws PersistenciaException {
		try {
			ArrayList<VOTemporada> array = new ArrayList<VOTemporada>();
			File carpeta = new File((String)con.getConnection());
			for (final File fileEntry : carpeta.listFiles()) {
				if(fileEntry.getName().contains("temporada")) {
					PersistenciaArchivo pa = new PersistenciaArchivo();
					IVOLogicaArchivo iVOLogicaArchivo = pa.recuperar(fileEntry.getPath());
					VOLogicaArchivoTemporada vOLogicaArchivoTemporada = (VOLogicaArchivoTemporada) iVOLogicaArchivo;
					array.add(new VOTemporada(vOLogicaArchivoTemporada.getTemporada()));
				}
			}
			return array;
		} catch (Exception e) {
			throw new PersistenciaException("algo salio mal");
		}
	}

	public VOTempMaxParts tempMasParticipantes(IConexion con) throws PersistenciaException {
		try {
			File carpeta = new File((String)con.getConnection());
			int intTemporada = 0;
			int largo = 0;
			
			for (final File fileEntry : carpeta.listFiles()) {
				if(fileEntry.getName().contains("dragqueens")) {
					String nroTemp = fileEntry.getName().split("-")[1].split("\\.")[0];
					DAODragQueensArchivo daoDragQueensArchivo = new DAODragQueensArchivo(new Integer(nroTemp));
					int largoActual = daoDragQueensArchivo.largo(con);
					
					if (largoActual > largo) {
						largo = largoActual;
						intTemporada = new Integer(nroTemp);
					}
				}
			}
			
			if(intTemporada==0)
				throw new PersistenciaException("No hay participantes registrados");
			
			return new VOTempMaxParts(find(con, intTemporada), largo);
		}catch (PersistenciaException e) {
			e.printStackTrace();
			throw e;
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaException("algo salio mal");
		}
	}
}
