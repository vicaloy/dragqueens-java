
package persistencia.consultas;

public class Consultas {

	public Consultas() {
	}
	
	//+ Consultas Temporada
	public String insertarTemporada() {
		String query = "Insert into Certamen.Temporadas (nroTemp,anio,cantCapitulos) values (?,?,?);";
		return query;
				
	};
	public String listarTemporadas () {
		String query = "Select nroTemp, anio, CantCapitulos from Certamen.Temporadas;";
		return query;
	};
	public String obtenerTemporadaMasParticipantes () {
		String query = "select t.nroTemp , t.anio, t.cantCapitulos, count(t.nroTemp) cantidad " + 
				"from Certamen.DragQueens dq, Certamen.Temporadas t " + 
				"where t.nroTemp = dq.nroTemp group by t.nroTemp, t.anio, t.cantCapitulos " + 
				"order by cantidad desc limit 1;";
		return query;

	};
	public String obtenerTemporada() {
		String query = "select nroTemp, anio, cantCapitulos from Certamen.Temporadas where nroTemp = (?);";
		return query;
	};
	public String existenTemporadas() {
		String query = "select count(*) from Certamen.Temporadas;";
		return query;
	};
	//-
	
	//+ Consultas DragQueens
	public String insertarDragQueen() {
		String query = "insert into Certamen.DragQueens (nroPart,nombre,cantVictorias,nroTemp) values (?,?,?,?);";
		return query;
	};
	public String existenDragQueens() {
		String query = "select nroTemp from Certamen.DragQueens where nroTemp = (?);";
		return query;
	};
	public String cantidadDragQueens() {
		String query = "select count(*) from Certamen.DragQueens where nroTemp = (?);";
		return query;
	};
	public String obtenerDragQueen() {
		String query = "select nroTemp,nombre,cantVictorias from Certamen.DragQueens where nroPart = (?) and nroTemp = (?);";
		return query;
	};
	public String listarDragQueens() {
		String query = "select nroTemp,nombre,cantVictorias from Certamen.DragQueens where nroTemp = (?);";
		return query;
	};
	public String registrarVictoriaDragQueen() {
		String query = "update Certamen.DragQueens set cantVictorias = cantVictorias+1 where nroTemp = (?) and nroPart = (?);";
		return query;
	};
	public String obtenerGanadora() {
		String query = "select nombre,nropart,cantVictorias from Certamen.DragQueens where nroTemp = (?) order by cantVictorias DESC limit 1;";
		return query;
	};
	//-
	
}
