import java.util.Random;

public class Grid {
	//Link to me explaining my code:
	//https://youtu.be/z4n5t4AJ5Ms
	
	//Link to me actually using the gui and playing with it
	//https://youtu.be/UG8sa1f6Lxc
	
	private boolean bombGrid [ ][ ];
	private int countGrid [ ][ ];
	private int numRows;
	private int numColumns;
	private int numBombs;
	
	public Grid() {
		numRows = 10;
		numColumns = 10;
		numBombs = 25;
		bombGrid = new boolean[numRows][numColumns];
		countGrid = new int[numRows][numColumns];
		createBombGrid();
		createCountGrid();
	}
	
	public Grid(int rows, int columns){
		numRows = rows;
	    numColumns = columns;
	    numBombs = 25;
	    bombGrid = new boolean[numRows][numColumns];
	    countGrid = new int[numRows][numColumns];
	    createBombGrid();
	    createCountGrid();
	}
	
	public Grid(int rows, int columns, int numBombs){
		numRows = rows;
	    numColumns = columns;
	    this.numBombs = numBombs;
	    bombGrid = new boolean[numRows][numColumns];
	    countGrid = new int[numRows][numColumns];
	    createBombGrid();//mention hardcoded 10
	    createCountGrid();
	}

	
	
	private void createBombGrid() {
		bombGrid = new boolean[numRows][numColumns];
		int bombs = 0;
		while(bombs < numBombs) {
			int row = getRandomNumber(0,numRows);
			int column = getRandomNumber(0,numColumns);
			if(bombGrid[row][column] != true) {
				bombGrid[row][column] = true;
				bombs++;
			}
		}
	}

	private void createCountGrid() {
		countGrid = new int[numRows][numColumns];
		for(int row = 0; row < numRows; row++) {
			for(int column = 0; column < numColumns; column++) {
				//i originally attempted to create each adjacent cell
				//instead of just checking each adj cell
				int counter = 0;
				if(row > 0 && column > 0 && bombGrid[row-1][column-1]) {//top left
					counter++;
				}
				if(row > 0 && bombGrid[row-1][column]) {//top
					counter++;
				}
				if(row > 0 && column < numColumns - 1 && bombGrid[row-1][column+1]) {//top right
					counter++;
				}
				if(column > 0 && bombGrid[row][column -1]) {// left
					counter++;
				}
				if(column < numColumns - 1 && bombGrid[row][column +1]) {// right
					counter++;
				}
				if(row < numRows - 1 && column > 0 && bombGrid[row+1][column -1]) {// bottom left
					counter++;
				}
				if(row < numRows - 1 && bombGrid[row+1][column]) {// bottom 
					counter++;
				}
				if(row < numRows - 1 && column < numColumns - 1 && bombGrid[row+1][column +1]) {// bottom right
					counter++;
				}
				if(bombGrid[row][column]) {
					counter++;
				}
				countGrid[row][column] = counter;
			}
		}
		
	}
	
	public int getRandomNumber(int small, int big) {
		Random random = new Random();
		int num = random.nextInt(big-small);
		return num;
	}

	
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumColumns() {
		return numColumns;
	}
	
	public int getNumBombs() {
		return numBombs;
	}
	
	public boolean [ ][ ] getBombGrid(){
		//iterate through the length of bombGrid and copy its values to new clone
		//array
		boolean [][]cloneBombGrid = new boolean[bombGrid.length][];
		for(int i = 0; i < bombGrid.length; i++) {
			cloneBombGrid[i] = bombGrid[i].clone();
		}
		return cloneBombGrid;
	}
	
	public int[][]  getCountGrid(){
		//iterate through the length of countGrid and copy its values to new clone
		//array
		int [][]cloneCountGrid = new int[countGrid.length][];
		for(int i = 0; i < countGrid.length; i++) {
			cloneCountGrid[i] = countGrid[i].clone();
			//setting each element of the clone array to the corresponding
			//element in the countGrid arr using .clone method
		}
		return cloneCountGrid;
	}
	
	public boolean isBombAtLocation(int row, int column) {
		if(bombGrid[row][column] == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getCountAtLocation(int row, int column) {
		return countGrid[row][column];
	}

}