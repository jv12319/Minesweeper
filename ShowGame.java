import javax.swing.JFrame;

public class ShowGame {

	public static void main(String[] args) {
		GameGui gui = new GameGui();
		JFrame gameFrame = new JFrame();//creating the jframe object
		gameFrame.setSize(500, 500);
		// Set the program to exit when the user
	    // closes the frame
	      gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      gameFrame.add(gui);
	   // Make the frame visible to the user
	      gameFrame.setVisible(true);
	}

}
