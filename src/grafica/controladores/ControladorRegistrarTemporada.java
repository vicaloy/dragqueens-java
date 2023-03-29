package grafica.controladores;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;

import grafica.ventanas.RegistrarTemporada;
import logica.IFachada;
import logica.excepciones.PersistenciaException;
import logica.excepciones.TemporadaException;
import logica.valueObjects.VOTemporada;

public class ControladorRegistrarTemporada {
	private IFachada fachada;
	private RegistrarTemporada ventana;

	private final String errorNroTemp = "Ingrese n�mero temporada por favor. \n";
	private final String errorAnio = "Ingrese a�o por favor. \n";
	private final String errorCantCap = "Ingrese cantidad de cap�tulos por favor. \n";

	private final String exito = "Se registr� la temporada exitosamente";
	private final String errorConex = "Se perdi� la conexi�n con el servidor, intente nuevamente";

	public ControladorRegistrarTemporada(RegistrarTemporada ventana) {
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

	public void registrarTemporada(int nroTemporada, int anio, int cantCapitulos) {

		String error = "";

		if (nroTemporada == 0) {
			error += errorNroTemp;
		}

		if (anio == 0) {
			error += errorAnio;
		}

		if (cantCapitulos == 0) {
			error += errorCantCap;
		}

		if (error.isEmpty()) {

			VOTemporada temporada = new VOTemporada(nroTemporada, anio, cantCapitulos);

			try {
				fachada.nuevaTemporada(temporada);
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
