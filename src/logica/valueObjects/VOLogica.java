package logica.valueObjects;

import java.util.ArrayList;

import logica.DragQueen;
import logica.Temporada;

public class VOLogica {
	private ArrayList<DragQueen> dragqueens;
	private ArrayList<Temporada> temporadas;
	
	
	public VOLogica(ArrayList<DragQueen> dragqueens, ArrayList<Temporada> temporadas) {
		this.dragqueens = dragqueens;
		this.temporadas = temporadas;
	}


	public void setDragqueens(ArrayList<DragQueen> dragqueens) {
		this.dragqueens = dragqueens;
	}
	public void setTemporadas(ArrayList<Temporada> temporadas) {
		this.temporadas = temporadas;
	}

	public ArrayList<Temporada> getTemporadas() {
		return temporadas;
	}

	public ArrayList<DragQueen> getDragqueens() {
		return dragqueens;
	}


	
}
