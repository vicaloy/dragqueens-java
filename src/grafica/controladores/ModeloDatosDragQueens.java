package grafica.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import logica.valueObjects.VODragQueenVictorias;

public class ModeloDatosDragQueens extends AbstractTableModel {

	private final Class[] tipoColumnas;
	private final String[] titleColumnas;
	private List<VODragQueenVictorias> dragQueens;

	public List<VODragQueenVictorias> getDragQueens() {
		return dragQueens;
	}

	public void setDragQueens(ArrayList<VODragQueenVictorias> drag) {
		this.dragQueens = drag;
	}

	public ModeloDatosDragQueens() {
		dragQueens = new ArrayList();
		this.titleColumnas = new String[] { "Nombre", "Nro temporada", "Nro participante", "Victorias" };
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
		return dragQueens.size();
	}

	@Override
	public int getColumnCount() {
		return titleColumnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return dragQueens.get(rowIndex).getNombre();
		case 1:
			return dragQueens.get(rowIndex).getNroTemp();
		case 2:
			return dragQueens.get(rowIndex).getNroParticipante();
		case 3:
			return dragQueens.get(rowIndex).getCantVictorias();
		default:
			return null;
		}
	}

}