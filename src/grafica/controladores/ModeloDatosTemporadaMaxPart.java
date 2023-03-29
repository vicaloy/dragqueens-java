package grafica.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import logica.valueObjects.VOTempMaxParts;

public class ModeloDatosTemporadaMaxPart extends AbstractTableModel {

	private final Class[] tipoColumnas;
	private final String[] titleColumnas;
	private List<VOTempMaxParts> temporadas;

	public List<VOTempMaxParts> getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(ArrayList<VOTempMaxParts> temp) {
		this.temporadas = temp;
	}

	public ModeloDatosTemporadaMaxPart() {
		temporadas = new ArrayList();
		this.titleColumnas = new String[] { "Nro temporada", "Año", "Cant capítulos", "Cant participantes" };
		this.tipoColumnas = new Class[] { String.class, Integer.class, Integer.class, Integer.class };
	}

	@Override
	public String getColumnName(int column) {
		return titleColumnas[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return tipoColumnas[columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	@Override
	public int getRowCount() {
		return temporadas.size();
	}

	@Override
	public int getColumnCount() {
		return titleColumnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return temporadas.get(rowIndex).getNroTemp();
		case 1:
			return temporadas.get(rowIndex).getAnio();
		case 2:
			return temporadas.get(rowIndex).getCantCapitulos();
		case 3:
			return temporadas.get(rowIndex).getCantParticipantes();
		default:
			return null;
		}
	}

}