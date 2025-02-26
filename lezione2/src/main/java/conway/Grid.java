package conway;

import conway.Cell;

public class Grid {

	private static Cell[][] griglia;
	private int rows; 
	private int cols;
	
	public Grid(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.griglia = new Cell[rows][cols];
		
		// Inizializza tutte le celle come morte
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                griglia[i][j] = new Cell(false);
            }
        }
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}
	
	public Cell getCell(int i, int j) {
		return griglia[i][j];
	}
	
	public void setCellState(int i, int j, boolean state) {
		griglia[i][j].toggleStato();
	}
	
    public Cell[][] getGridCopy() {
    		Cell[][] copy = new Cell[rows][cols];
    		for (int i = 0; i < rows; i++) {
	            for (int j = 0; j < cols; j++) {
	                copy[i][j] = new Cell(griglia[i][j].isStato());
	            }
	        }
	        return copy;
	}	
	
	
}
