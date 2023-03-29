package grafica.controladores;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import grafica.ventanas.RegistrarVictoria;
import logica.IFachada;
import logica.excepciones.DragQueenException;
import logica.excepciones.PersistenciaException;
import logica.excepciones.TemporadaException;

public class ControladorRegistrarVictoria {
	private IFachada fachada;
	private RegistrarVictoria ventana;

	private final String errorNroTemp = "Ingrese n�mero temporada por favor. \n";
	private final String errorNroPart = "Ingrese el número de participante por favor. \n";

	private final String exito = "Se registr� la victoria exitosamente";
	private final String errorConex = "Se perdi� la conexi�n con el servidor, intente nuevamente";

	public ControladorRegistrarVictoria(RegistrarVictoria ventana) {
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

	public void registrarVictoria(int nroTemporada, int nroPart) {

		String error = "";

		if (nroTemporada == 0) {
			error += errorNroTemp;
		}

		if (nroPart == 0) {
			error += errorNroPart;
		}

		if (error.isEmpty()) {

			try {
				fachada.registrarVictoria(nroTemporada, nroPart);
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
			} catch (DragQueenException e) {
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
