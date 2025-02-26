package conway;

import conway.Cell;
import conway.Grid;
/*
 * Il core di game of life
 * Non ha dipendenze da dispositivi di input/output
 * Non ha regole di controllo del gioco 
 */

public class Life {
    //La struttura
    private int rows=0;
    private int cols=0;
    private Grid grid;
    private Grid nextGrid;
 
    public Life( int rowsNum, int colsNum ) {
        this.rows   = rowsNum;
        this.cols   = colsNum;
        createGrids();   //crea la struttura a griglia
    }

    public int getRowsNum(){
        return rows;
    }
    public int getColsNum(){
        return cols;
    }

    protected void  createGrids() {
        grid     = new Grid(rows, cols);
        nextGrid = new Grid(rows, cols);;   
        //CommUtils.outyellow("Life | initializeGrids done");
    }

    protected void resetGrids() {
         for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
            	grid.setCellState(i, j, false);
                //setCellState(   i,   j, false );
                //outdev.setCellColor(  i,  j, grid[i][j] );
            	nextGrid.setCellState(i, j, false);
            }
        }
        //CommUtils.outyellow("Life | initGrids done");
    }


    protected int countNeighborsLive(int row, int col) {
        int count = 0;
        if (row-1 >= 0) {
            if (grid.getCell(row - 1, col).isStato()) count++;
        }
        if (row-1 >= 0 && col-1 >= 0) {
            if (grid.getCell(row - 1, col - 1).isStato()) count++;
        }
        if (row-1 >= 0 && col+1 < cols) {
            if (grid.getCell(row - 1, col + 1).isStato()) count++;
        }
        if (col-1 >= 0) {
            if (grid.getCell(row, col - 1).isStato()) count++;
        }
        if (col+1 < cols) {
            if (grid.getCell(row, col + 1).isStato()) count++;
        }
        if (row+1 < rows) {
            if (grid.getCell(row + 1, col).isStato()) count++;
        }
        if (row+1 < rows && col-1 >= 0) {
            if (grid.getCell(row + 1, col - 1).isStato()) count++;
        }
        if (row+1 < rows && col+1 < cols) {
            if (grid.getCell(row + 1, col + 1).isStato()) count++;
        }
        return count;
    }



    protected void computeNextGen( IOutDev outdev ) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int n = countNeighborsLive(i,j);
                applyRules(i, j, n);
                outdev.displayCell("" + ((grid.getCell(i, j).isStato()) ? "0" : "1"));
            }
            outdev.displayCell("\n");  //Va tolta nel caso della GUI?
        }
        copyAndResetGrid( outdev );
        outdev.displayCell("\n");
    }

    protected void copyAndResetGrid( IOutDev outdev ) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
            	grid.setCellState(i, j, nextGrid.getCell(i, j).isStato());
                //outdev.displayCell( ""+grid[i][j] );
            	nextGrid.setCellState(i, j, false);
            }
        }
    }

    protected void applyRules(int row, int col, int numNeighbors) {
        //int numNeighbors = countNeighborsLive(row, col);
        //CELLA VIVA
        if (grid.getCell(row, col).isStato()) {
            if (numNeighbors < 2) { //muore per isolamento
            	nextGrid.setCellState(row, col, false);
            } else if (numNeighbors == 2 || numNeighbors == 3) { //sopravvive
            	nextGrid.setCellState(row, col, true);
            } else if (numNeighbors > 3) { //muore per sovrappopolazione
            	nextGrid.setCellState(row, col, false);
            }
        }
        //CELLA MORTA
        else if (grid.getCell(row, col).isStato() == false) {
            if (numNeighbors == 3) { //riproduzione
            	nextGrid.setCellState(row, col, true);
            }
        }
        //CommUtils.outgreen("Life applyRules " + nextGrid   );
    }

    public void switchCellState(int i, int j){
        if( grid.getCell(i, j).isStato() == false) grid.getCell(i, j).toggleStato();     
        else if( grid.getCell(i, j).isStato() == true) grid.getCell(i, j).toggleStato();
    }

    public  Cell getCellState( int i, int j  ) {
        return   grid.getCell(i, j);
    }

}
