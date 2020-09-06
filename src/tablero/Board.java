package tablero;

public class Board {
	
	private int[][] values;
	private Box[][] boxes;
	private int size;
	private int boxSize;
	private boolean[][] fixedcells;

	public Board(int size) {
		
		this.size = (int) Math.pow((int) Math.round(Math.sqrt(size)),2);//Aseguro que el tamaño del tablero tenga raiz cuadrada exacta.
		this.values = new int[this.size][this.size];
		this.boxSize = (int) Math.sqrt(this.size);
		
		this.boxes = new Box[this.boxSize][this.boxSize];
		
		for(int x = 0;x<this.boxSize;x++) {
			for(int y = 0; y<this.boxSize; y++) {
				this.boxes[x][y] = new Box(boxSize);
			}
		}
		
		this.fixedcells = new boolean[this.size][this.size];
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int increment(int row, int column) {
		do {
			if(values[row][column]<this.size) {
				setValue(values[row][column]+1, row, column);
			}
			else {
				setValue(0, row, column);
			}
		}
		while(values[row][column] != 0 && !isValid(row, column));
		
		return values[row][column];
	}
	
	public int decrement(int row, int column) {
		do {
			if(values[row][column]>0) {
				setValue(values[row][column]-1, row, column);
			}
			else {
				setValue(this.size, row, column);
			}
		}
		while(values[row][column] != 0 && !isValid(row, column));
		
		return values[row][column];
	}
	
	public void setValue(int value, int row, int column) {
		this.values[row][column] = value;
		
		int cajaX = 0;
		int cajaY = 0;
		int posicionCajaX = 0;
		int posicionCajaY = 0;
		for(int caja = 1; caja<this.size+1; caja++) {
			if(row<this.boxSize*caja) {
				cajaX = caja-1;
				posicionCajaX = row - this.boxSize * (caja-1);
				break;
			}
		}
		
		for(int caja = 1; caja<this.size+1; caja++) {
			if(column<this.boxSize*caja) {
				cajaY = caja-1;
				posicionCajaY = column - this.boxSize * (caja-1);
				break;
			}
		}
		
		Box caja = this.boxes[cajaX][cajaY];
		caja.setValue(value, posicionCajaX, posicionCajaY);
		if(value == 0)
			this.fixedcells[row][column] = false;
		else {
			this.fixedcells[row][column] = true;
		}
	}
	
	public boolean isValid(int row, int column) {
		
		boolean ret = this.values[row][column] == 0? false : true;
		
		if (this.values[row][column]<=this.size && ret) {
			for (int fila = 0; fila < this.size; fila++) {
				if (values[fila][column] == values[row][column] && fila!=row) {
					ret = false;
				}
			}
			for (int columna = 0; columna < this.size; columna++) {
				if (values[row][columna] == values[row][column] && columna != column) {
					ret = false;
				}
			}
			if (ret) {

				int cajaX = 0;
				int cajaY = 0;
				int posicionCajaX = 0;
				int posicionCajaY = 0;
				
				for (int caja = 1; caja < this.size + 1; caja++) {
					if (row < this.boxSize * caja) {
						cajaX = caja - 1;
						posicionCajaX = row - this.boxSize * (caja-1);
						break;
					}
				}

				for (int caja = 1; caja < this.size + 1; caja++) {
					if (column < this.boxSize * caja) {
						cajaY = caja - 1;
						posicionCajaY = column - this.boxSize * (caja-1);
						break;
					}
				}

				ret = this.boxes[cajaX][cajaY].isValid(posicionCajaX, posicionCajaY);
			} 
		}
		return ret;
	}
	
	public void print() {
		for(int x = 0;x<this.size;x++) {
			for(int y = 0; y<this.size; y++) {
				System.out.print(this.values[x][y]+" ");
			}
			System.out.println("");
		}
	}
	
	public boolean isFixcedCell(int row, int column) {
		return this.fixedcells[row][column];
	}
	
	public void setFixedCell(int row, int column) {
		this.fixedcells[row][column] = true;
	}
	
	public int getValue(int row, int column) {
		return this.values[row][column];
	}

	public void reset() {
		for(int x = 0; x<this.size; x++) {
			for(int y = 0; y<this.size; y++) {
				this.values[x][y] = 0;
				this.fixedcells[x][y] = false;
			}
		}
		
		for(int x = 0; x<this.boxSize; x++) {
			for(int y = 0; y<this.boxSize; y++) {
				this.boxes[x][y].reset();
			}
		}
		
	}

	public int[][] getValues() {
		return this.values;
	}
}
