package ia;

import tablero.Board;

public class Solver {

	private Board board;

	public void solve(Board board) {
		this.board = board;
		solveCell(0, 0);
		//board.print();
	}

	private boolean solveCell(int row, int column) {

		int value = 0;
		boolean isNextCellSolved = false;
		
		if (!this.board.isFixcedCell(row, column)) { // si no es una posicion fija
			while (!isNextCellSolved && value < this.board.getSize()) {// no se pudo resolver la celda siguiente, todavía
																		// se puede aumentar el valor de esta celda			
				value++;
				this.board.setValue(value, row, column);//aumenta el valor de la celda.

				if (this.board.isValid(row, column)) //verifica que el valor sea valido.
					isNextCellSolved = solveNext(row, column);
			}
			if (value == this.board.getSize() && !isNextCellSolved) { // No se puede seguir aumentando el valor y no se	resolvió.
				if(row == 0 && column == 0) {
					isNextCellSolved = false;//No hay solución.
				}
				else {
					this.board.setValue(0, row, column);//resetea la celda.
				}
			}
		}
		else {//Si es posicion fija la saltea.
			isNextCellSolved = solveNext(row, column);
		}		
		return isNextCellSolved;
	}
	
	private boolean solveNext(int row, int column) {
		boolean isNextCellSolved = false;
		if (column < this.board.getSize() - 1) {
			isNextCellSolved = solveCell(row, column + 1);// siguiente columna
		} else if (row < this.board.getSize() - 1) {
			isNextCellSolved = solveCell(row + 1, 0);// siguiente fila
		} else {
			isNextCellSolved = true;// final
		}
		return isNextCellSolved;
	}
}
