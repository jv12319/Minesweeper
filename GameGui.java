import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class GameGui extends JPanel implements ActionListener{
	
	private Grid grid = new Grid();
	private int[][]countGrid = grid.getCountGrid();
	private boolean[][]bombGrid = grid.getBombGrid();
	private int numRows = grid.getNumRows();
	private int numColumns = grid.getNumColumns();
	private int numBombs = grid.getNumBombs();
	private int cellsLeft = (numRows * numColumns) - numBombs;
	private JButton [][] board;
    
	public GameGui(){
	setLayout(new GridLayout(numRows, numColumns));
	setBackground(Color.WHITE);
	setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	
	board = new JButton[numRows][numColumns];//initializing 
	for(int row = 0; row < numRows; row++){
	    for(int col = 0; col < numColumns; col++){
	        board[row][col] = new JButton();
	        board[row][col].addActionListener(this);
	        board[row][col].setEnabled(true);
	        //testing if my win logic works
	        /*if(bombGrid[row][col]) {
	        	board[row][col].setText("x");
	        } else {
	        	board[row][col].setText("Y");
	        }*/
	        this.add(board[row][col]);
	    }
	}
   }

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton)e.getSource();
		
		
		for (int row = 0; row < numRows; row++) {
	        for (int col = 0; col < numColumns; col++) {
	            if (btnClicked == board[row][col]) {
	            	if(bombGrid[row][col]) {
	            		JOptionPane.showMessageDialog(this, "The game is over! You clicked a bomb!!");
	            		board[row][col].setText("X");
	            		for (int i = 0; i < numRows; i++) {
	            	        for (int j = 0; j < numColumns; j++) {
	            	        	if(bombGrid[i][j]) {
	            	        		board[i][j].setText("X");
	            	        	} else {
	            	        		board[i][j].setText(Integer.toString(countGrid[i][j]));
	            	        	}
	            	        }
	            	        }
	            		playAgain();
	            	} else {
	            		btnClicked.setText(Integer.toString(countGrid[row][col]));
	            		cellsLeft--;
	            		
	            		
	            		}
	            	if(cellsLeft == 0) {
            			JOptionPane.showMessageDialog(this, "Congratulations!! You won!!");
            			playAgain();
            		}
	           }
	        }
	      }
	}
	
	private void playAgain() {
		int yesNo = JOptionPane.showConfirmDialog(null, "Play Again?", "YES or NO", JOptionPane.YES_NO_OPTION);
		if(yesNo == JOptionPane.YES_OPTION){
			clearBoard();
		}
		else{
			JOptionPane.showMessageDialog(this, "Goodbye!");
			System.exit(0);
		}
	}

	private void clearBoard() {
		for(int row=0; row<numRows; row++){
			for(int col=0; col<numColumns; col++){
				board[row][col].setText("");
				board[row][col].setEnabled(true);
			}
		}
		resetGame();
		
	}
	
	private void resetGame() {
		grid = new Grid(); 
	    countGrid = grid.getCountGrid();
	    bombGrid = grid.getBombGrid();
	    numRows = grid.getNumRows();
	    numColumns = grid.getNumColumns();
	    numBombs = grid.getNumBombs();
	    cellsLeft = (numRows * numColumns) - numBombs;
	}

	
}
