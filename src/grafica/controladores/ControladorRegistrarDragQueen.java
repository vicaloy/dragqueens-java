package grafica.controladores;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import grafica.ventanas.RegistrarDragQueen;
import logica.IFachada;
import logica.excepciones.PersistenciaException;
import logica.excepciones.TemporadaException;
import logica.valueObjects.VODragQueen;

public class ControladorRegistrarDragQueen {
	private IFachada fachada;
	private RegistrarDragQueen ventana;

	private final String errorNroTemp = "Ingrese n�mero temporada por favor. \n";
	private final String errorNombre = "Ingrese nombre por favor. \n";

	private final String exito = "Se registr� la DragQueen exitosamente";
	private final String errorConex = "Se perdi� la conexi�n con el servidor, intente nuevamente";

	public ControladorRegistrarDragQueen(RegistrarDragQueen ventana) {
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
			ventana.mensajeError(errorConex, false);
		} catch (Exception e) {
			ventana.mensajeError(e.getMessage(), false);
			e.printStackTrace();
		}

	}

	public void registrarDragQueen(int nroTemporada, String nombre) {

		String error = "";

		if (nroTemporada == 0) {
			error += errorNroTemp;
		}

		if (nombre == null || nombre.isEmpty()) {
			error += errorNombre;
		}

		if (error.isEmpty()) {

			VODragQueen dragQueen = new VODragQueen(nombre, nroTemporada);

			try {
				fachada.inscribirDragQueen(dragQueen);
				ventana.mensajeError(exito, true);
				/*
				 * si registro correctamente al participante, mandarle a la ventana un mensaje
				 * de exito y luego cerrarla
				 */
			} catch (TemporadaException e) {
				/*
				 * separar este catch en dos, para mandarle a la ventana mensajes de error
				 * diferentes. Si salta la RemoteException, mandarle a la ventana un mensaje
				 * propio en espa�ol
				 */

				ventana.mensajeError(e.getMensaje(), false);
			} catch (PersistenciaException e) {
				/*
				 * separar este catch en dos, para mandarle a la ventana mensajes de error
				 * diferentes. Si salta la RemoteException, mandarle a la ventana un mensaje
				 * propio en espa�ol
				 */

				ventana.mensajeError(e.getMensaje(), false);
			} catch (RemoteException e) {
				ventana.mensajeError(errorConex, false);
			} catch (Exception e) {
				ventana.mensajeError(e.getMessage(), false);
				e.printStackTrace();
			}

		} else {

			ventana.mensajeError(error, false);
		}
	}

}
