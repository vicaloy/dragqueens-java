package persistencia;

import java.io.*;

import logica.excepciones.PersistenciaException;
import logica.valueObjects.IVOLogicaArchivo;


public class PersistenciaArchivo {
	public void respaldar(String nomArch, IVOLogicaArchivo p) throws PersistenciaException {
		try {
			Boolean append = false;
			FileOutputStream f = new FileOutputStream(nomArch, append);
			ObjectOutputStream o = new ObjectOutputStream(f);
			o.writeObject(p);
			o.close();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new PersistenciaException("No se pudo grabar la información");
		}

	}

	public IVOLogicaArchivo recuperar(String nomArch) {
		try {
			FileInputStream f = new FileInputStream(nomArch);
			ObjectInputStream o = new ObjectInputStream(f);
			IVOLogicaArchivo p = (IVOLogicaArchivo) o.readObject();
			o.close();
			f.close();
			return p;
		} catch (IOException | ClassNotFoundException e) {
			return null;
		}
	}
}