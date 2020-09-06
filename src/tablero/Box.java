package tablero;

public class Box {
	
	private int[][] values;
	private int size;
	
	public Box(int size) {
		this.size = size;
		this.values = new int[this.size][this.size];
	}
	
	public void setValue(int value, int row, int column) {
		this.values[row][column]= value;
	}
	
	public boolean isValid(int row, int column) {
		boolean ret = true;
		for(int fila = 0; fila<this.size; fila++) {
			for(int columna = 0 ; columna<this.size; columna++) {
				if(this.values[row][column] == this.values[fila][columna] && (fila!=row || columna != column)) {
					ret = false;
				}
			}
		}
			
		return ret;
	}

	public void reset() {
		for(int x = 0; x<this.size; x++) {
			for(int y = 0; y<this.size; y++) {
				this.values[x][y] = 0;
			}
		}
		
	}

}
