package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import javax.swing.JOptionPane;

import java.util.Enumeration;
import java.util.HashMap;

public class Config {
  private static Config instancia;
  private HashMap<String,Object> data;
  public static final String db_url = "db_url";
  public static final String db_user = "db_user";
  public static final String db_password = "db_password";
  public static final String db_driver = "db_driver";
  public static final String db_database = "db_database";
  public static final String fabrica_Persistencia = "fabrica_Persistencia";
  public static final String ipServidor = "ipServidor";
  public static final String puertoServidor = "puertoServidor";
  public static final String db_nivelTransaccionalidad = "db_nivelTransaccionalidad";
  public static final String file_directory = "file_directory";

  private Config() {
    Properties prop = new Properties();
    InputStream input = null;
    Enumeration<Object> obj = null;
    this.data = new HashMap<String,Object>();

		try {
			input = new FileInputStream("src/utils/config.properties");
      prop.load(input);
      obj = prop.keys();

      while (obj.hasMoreElements()) {
        String key = (String) obj.nextElement();
        if (key == db_nivelTransaccionalidad) {
          switch (prop.getProperty(db_nivelTransaccionalidad).toUpperCase()) {
            case "NONE":
                this.data.put(db_nivelTransaccionalidad,  Connection.TRANSACTION_NONE);
              break;
            case "READ_UNCOMMITTED":
              this.data.put(db_nivelTransaccionalidad,  Connection.TRANSACTION_READ_UNCOMMITTED);
              break;
            case "READ_COMMITTED":
              this.data.put(db_nivelTransaccionalidad,  Connection.TRANSACTION_READ_COMMITTED);
              break;
            case "REPEATABLE_READ":
              this.data.put(db_nivelTransaccionalidad,  Connection.TRANSACTION_REPEATABLE_READ);
              break;
            case "SERIALIZABLE":
              this.data.put(db_nivelTransaccionalidad,  Connection.TRANSACTION_SERIALIZABLE);
              break;
          }
        }else {
          this.data.put(key, prop.getProperty(key));
        }        
      }
		} catch (IOException ex) {
      JOptionPane.showMessageDialog(null, "[Error] No se ha podido cargar la configuracion.");
      System.exit(1);
		}
  }

  public static Config getInstance() {
    if (instancia == null) {
      instancia = new Config();
    }
    return instancia;
  }

  public Object get(String key) {
    return this.data.get(key);
  }

}