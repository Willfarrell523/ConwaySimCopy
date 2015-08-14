public class lifeBoard {

	private int[][] board0;
	private int[][] board1;
	private int boardNum;

	private int[][] getBoard() {
		if (boardNum == 0) {
			return board0;
		} else {
			return board1;
		}
	}

	private void switchBoard() {
		boardNum = (boardNum + 1) % 2;
	}

	public boolean isAlive(int x, int y) {
		int[][] board = getBoard();
		if (board[x][y] > 0) {
			return true;
		} else {
			return false;
		}
	}

	public lifeBoard(int height) {
		board0 = new int[height][height];
		board1 = new int[height][height];
		boardNum = 0;
		// board[startLocX][startLocY] = 7;
	}

	public int getSpot(int x, int y) {
		int[][] board = getBoard();
		return board[x][y];
	}
	
	public void kill(int x, int y)
	{
		int[][] board = getBoard();
		board[x][y] = 0;
	}

	public void incrementLife(int x, int y) {
		int[][] board = getBoard();
		board[x][y]++;
	}

	public void printBoard() {
		int[][] board = getBoard();
		for (int prints = 0; prints <(board.length+1); prints++) {
			System.out.print("+");
			System.out.print("-");
		}
		System.out.println("+");
		for (int row = 0; row < board.length; row++) {
			System.out.print("+");
			System.out.print("|");
			for (int col = 0; col < board[row].length; col++) {
				
				int letter = 64 + board[row][col];
				if(letter == 64)
				{
					System.out.print(' ');
				}
				else
				{
					System.out.print((char)letter);
				}
				

				System.out.print("|");
			}
			System.out.println("+");
		}
//		System.out.print(" ");
		for (int prints = 0; prints <(board.length+1); prints++) {
			System.out.print("+");
			System.out.print("-");
		}
		System.out.print("+");
		
	}

	public int getNeighborsAlive(int x, int y) {
		switchBoard();
		int[][] board = getBoard();
		int n = 0;
		for (int row = -1; row <= 1; row++) {
			for (int col = -1; col <= 1; col++) {
				int checkX = x + row;
				if (row==0&&col==0) {
					continue;
				}
				if (checkX == -1) {
					checkX = board.length-1;
				}
				else if (checkX == board.length) {
					checkX=0;
				}

				int checkY = y + col;
				if (checkY == -1) {
					checkY = board.length-1;
				}
				else if (checkY == board.length) {
					checkY=0;
				}
				
				if (board[checkX][checkY] > 0) {
					n++;
				}
				 
			}
		}
		switchBoard();
		return n;

	}

	public void nextGen() {
		int[][] boardA = getBoard();
		switchBoard();
		int[][] boardB = getBoard();

		for (int i = 0; i < boardA.length; i++) {
			for (int j = 0; j < boardA[0].length; j++) {
				boardB[i][j] = boardA[i][j];
			}
		}

		for (int i = 0; i < boardA.length; i++) {
			for (int j = 0; j < boardA[0].length; j++) {
				int n = getNeighborsAlive(i, j);
				if (boardA[i][j] == 0) {
					if(n == 3)
					{
						boardB[i][j]++;
					}
				}
				else 
				{
					if (n >= 4 || n <= 1) {
						boardB[i][j] = 0;
					} else {
						boardB[i][j]++;
					}
				}
			}
		}
	}
}
