package grafica.controladores;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import grafica.ventanas.ObtenerGanadora;
import logica.IFachada;
import logica.excepciones.PersistenciaException;
import logica.excepciones.TemporadaException;
import logica.valueObjects.VODragQueenVictorias;

public class ControladorObtenerGanadora {
	private IFachada fachada;
	private ObtenerGanadora ventana;

	private final String errorConex = "Se perdi� la conexi�n con el servidor, intente nuevamente";

	public ControladorObtenerGanadora(ObtenerGanadora ventana) {
		this.ventana = ventana;
		try {
			Properties p = new Properties();
			String nomArch = "src/utils/config.properties";
			p.load(new FileInputStream(nomArch));
			String servidor = p.getProperty("ipServidor");
			String puerto = p.getProperty("puertoServidor");
			String ruta = "//" + servidor + ":" + puerto + "/fachada";

			fachada = (IFachada) Naming.lookup(ruta);
			ventana.setVisible(true);
		} catch (IOException | NotBoundException e) {

			ventana.mensajeError(errorConex);
		}

	}

	public VODragQueenVictorias listarGanadora(int nroTemp) {
		VODragQueenVictorias part = null;

		try {
			System.out.println("CONTROLADOR, antes de mandar a la fachada");
			part = fachada.obtenerGanadora(nroTemp);
			System.out.println("CONTROLADOR, luego de mandar a la fachada");

		} catch (PersistenciaException e) {
			ventana.mensajeError(e.getMensaje());
		} catch (TemporadaException e) {
			ventana.mensajeError(e.getMensaje());
		} catch (RemoteException e) {
			ventana.mensajeError(errorConex);
		} catch (Exception e) {
			ventana.mensajeError(e.getMessage());
			e.printStackTrace();
		}

		return part;

	}
}
