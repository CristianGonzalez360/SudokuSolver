package gui;

import ia.Solver;
import tablero.Board;

public class ControladorTablero {

	private Tablero tablero; 
	private Board board;
	
	public ControladorTablero() {
		this.board = new Board(9);
	}
	
	public int incrementar(int row, int column) {
		return board.increment(row, column);
	}
	
	public int disminuir(int row, int column) {
		return board.decrement(row, column);
	}
	
	public void setValue(int value, int row, int column) {
		this.board.setValue(value, row, column);
	}
	
	public void setVista(Tablero tablero) {
		this.tablero = tablero;
	}
	
	public void resolver() {
		Solver s = new Solver();
		s.solve(board);
		tablero.mostrarResultado(board.getValues());
		tablero.bloquear();
	}
	
	public boolean esValido(int row, int column) {
		return this.board.isValid(row, column);
	}

	public void reset() {
		board.reset();
		tablero.desbloquear();
	}
	
	
}