package logica.valueObjects;

import java.util.ArrayList;
import java.io.Serializable;

import logica.DragQueen;

public class VOLogicaArchivoDragQueen implements IVOLogicaArchivo, Serializable {
	private static final long serialVersionUID = 1L;
	private ArrayList<DragQueen> dragQueens = new ArrayList<DragQueen>();
	
	public VOLogicaArchivoDragQueen() {
	}

	public VOLogicaArchivoDragQueen(DragQueen dragQueen) {
		this.dragQueens.add(dragQueen);
	}

	public ArrayList<DragQueen> getDragQueens() {
		return dragQueens;
	}

	public void setDragQueen(DragQueen dragQueen) {
		this.dragQueens.add(dragQueen);
	}
	
	public void setMuchasDragQueen(ArrayList<DragQueen> dragQueens) {
		this.dragQueens = dragQueens;
	}
}
