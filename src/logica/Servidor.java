package logica;

import java.net.BindException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import utils.Config;

public class Servidor
{
	public static void main(String[] args)
	{		
		try
		{
			Config config = Config.getInstance();
			String ip = (String) config.get(Config.ipServidor);
			int port = Integer.parseInt((String) config.get(Config.puertoServidor));
			
			LocateRegistry.createRegistry(port);
			
			IFachada fachada = Fachada.getInstancia();
			
			String ruta = "//" + ip + ":" + port + "/fachada";
			System.out.println("Antes de publicar fachada");
			Naming.rebind(ruta, fachada);
			System.out.println("Despues de publicar fachada");
		} catch (BindException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

