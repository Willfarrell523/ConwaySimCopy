import javax.swing.*;

public class gooeystuff {

	public static int startLocX;
	public static int startLocY;
	public static int time;
	public static int genNumDis = 0;

	public static void main(String[] arg) {

		int height = Integer.parseInt(JOptionPane.showInputDialog(null,
				"Enter Size of Grid", null));
		System.out.println(height);
		lifeBoard board = new lifeBoard(height);
		board.printBoard();

		int genNum = Integer.parseInt(JOptionPane
				.showInputDialog("Enter number of generations"));

		// TODO fix dialog box appearing twice when illegal number entered.
		boolean errorCheckCell = true;
		int liveCells = 0;
		while (errorCheckCell == true) {
			liveCells = Integer.parseInt(JOptionPane
					.showInputDialog("Enter number of live cells"));
			if (liveCells > height * height) {
				JOptionPane.showMessageDialog(null,
						"Invalid number of live cells, try again");
			} else {
				errorCheckCell = false;
			}
		}

		for (int i = 0; i < liveCells; i++) {
			boolean errorCheckXLoc = true;
			boolean errorCheckYLoc = true;
			while (errorCheckXLoc == true) {
				// liveCellNumX++;
				startLocX = Integer.parseInt(JOptionPane
						.showInputDialog("Enter X coordinate for live cell "
								+ (i + 1)));

				if (startLocX < 0 || startLocX > (height - 1)) {
					JOptionPane.showMessageDialog(null, "Invalid Cooridnate");
				} else {
					errorCheckXLoc = false;
				}
			}
			while (errorCheckYLoc == true) {
				// liveCellNumY++;
				startLocY = Integer.parseInt(JOptionPane
						.showInputDialog("Enter Y coordinate for live cell "
								+ (i + 1)));
				if (startLocY < 0 || startLocY > (height - 1)) {
					JOptionPane.showMessageDialog(null, "Invalid Cooridnate");
				} else {
					errorCheckYLoc = false;
				}

			}
			board.incrementLife(startLocX, startLocY);
			// JOptionPane.showMessageDialog(null, "Starting location: "
			// + startLocX + "," + startLocY);
		}
		
//		public void getTime(int time) {
		int time = Integer.parseInt(JOptionPane.showInputDialog(null,
				"Enter time between generations(In ms)", null));
//		}
		WindowClass window = new WindowClass("Conway's Simulation of Life",
				height, board);
		window.setDisplay();
		for (int i = 0; i < genNum; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			genNumDis++;
			System.out.println("Generation "+genNumDis);
			board.nextGen();
			window.setDisplay();
		}
		JOptionPane.showMessageDialog(null, "Simulation Finished");
	}
}
