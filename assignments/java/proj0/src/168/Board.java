public class Board {
	private static Piece[][] gamePieces; //[row][column]
	private boolean empty;
	private boolean currentPlayer; //true = fire, false = water
	private Piece selected; //currently selected piece
	private boolean hasMoved; //true - move made, false - no move yet
	private int numberFire; //number of fire pieces left
	private int numberWater; //number of water pieces left
	private int whiteX;
	private int whiteY;

	public Board(boolean shouldBeEmpty) {
		empty = shouldBeEmpty;
		gamePieces = new Piece[8][8];
		createPieces(8);
		currentPlayer = true;
		whiteX = 100;
		whiteY = 100;
	}

	public Piece pieceAt(int x, int y) {
		if (!((x >= 0 && x <= 7) && (y >= 0 && y <= 7)) || gamePieces[x][y] == null) {
			return null;
		} 
		return gamePieces[x][y];
	}

	public boolean canSelect(int x, int y) { //in progress
		if (!((x >= 0 && x <= 7) && (y >= 0 && y <= 7))) {
			return false;
		}
		Piece current = gamePieces[x][y];

		if (current == null || currentPlayer == current.isFire()) {
			if (current != null) { //selected a piece
				if (selected == null) { //no piece already selected
					return true;
				}
				else if (hasMoved) {
					return false;
				}
				return true;
			}
			else { //empty square
				if (selected != null && !hasMoved && validMove(selected, x, y)) {//piece has not moved
					return true;
				}
				else if (selected != null && selected.hasCaptured() && canCapture(selected, x, y) && validMove(selected, x, y)) {//only true if can capture again
					return true;
				}
			}
		}
		return false;
	}

	private boolean canCapture(Piece p, int xf, int yf) {
		boolean fireCapture = fireCaptureBehavior(p, xf, yf);
		boolean waterCapture = waterCaptureBehavior(p, xf, yf);
		if (p.isKing()) {
			return (fireCapture || waterCapture);
		}
		else {
			if (p.isFire()) {
				return fireCapture;
			}
			else {
				return waterCaptureBehavior(p, xf, yf);
				}
			}
	}

	private boolean fireCaptureBehavior(Piece p, int xf, int yf) {
		if (((xf-2) >= 0) && ((yf - 2) >= 0) && (gamePieces[xf-2][yf-2] == p) && (gamePieces[xf - 1][yf - 1] != null) && (p.isFire() != (gamePieces[xf - 1][yf - 1].isFire()))) {
			return true;
		}
		else if (((xf+2) <= 7) && ((yf - 2) >= 0) && (gamePieces[xf+2][yf-2] == p) && (gamePieces[xf + 1][yf - 1] != null) && (p.isFire() != gamePieces[xf+1][yf-1].isFire())) {
			return true;
		}
		return false;
	}

	private boolean fireStandardMove(Piece p, int xf, int yf) {
		if ((((xf-1) >= 0) && ((yf - 1) >= 0) && (gamePieces[xf-1][yf-1] == p)) 
				|| (((xf+1) <= 7) && ((yf - 1) >= 0) && (gamePieces[xf+1][yf-1] == p))) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean waterCaptureBehavior(Piece p, int xf, int yf) {
		if (((xf-2) >= 0) && ((yf + 2) <= 7) && (gamePieces[xf-2][yf+2] == p) && (gamePieces[xf - 1][yf + 1] != null) && (p.isFire() != gamePieces[xf-1][yf+1].isFire())) {
			return true;
		}
		else if (((xf+2) <= 7) && ((yf + 2) <= 7) && (gamePieces[xf+2][yf+2] == p) && (gamePieces[xf + 1][yf + 1] != null) && (p.isFire() != gamePieces[xf+1][yf+1].isFire())) {
			return true;
		}
		return false;	
	}

	private boolean waterStandardMove(Piece p, int xf, int yf) {
		if ((((xf-1) >= 0) && ((yf + 1) <= 7) && (gamePieces[xf-1][yf+1] == p))
			|| (((xf+1) <= 7) && ((yf + 1) <= 7) && (gamePieces[xf+1][yf+1] == p))) {
			return true;
		}	
		else {
			return false;
		}
	}

	private boolean kingStandardMove(Piece p, int xf, int yf) {
		if ((((xf-1) >= 0) && ((yf + 1) <= 7) && (gamePieces[xf-1][yf+1] == p))
			|| (((xf+1) <= 7) && ((yf + 1) <= 7) && (gamePieces[xf+1][yf+1] == p))
			|| (((xf-1) >= 0) && ((yf - 1) >= 0) && (gamePieces[xf-1][yf-1] == p)) 
			|| (((xf+1) <= 7) && ((yf - 1) >= 0) && (gamePieces[xf+1][yf-1] == p))) {
			return true;
		}
		else {
			return false;
		}
	}

	private boolean validMove(Piece p, int xf, int yf) {//Strictly geometry

		if (gamePieces[xf][yf] == null) { //checks if destination is empty
			if (p.isKing()) {
				if (fireCaptureBehavior(p, xf, yf)) {
					return true;
				}
				else if (waterCaptureBehavior(p, xf, yf)) {
					return true;
				}

				return kingStandardMove(p, xf, yf);
			}
			else {
				if (p.isFire()) {
					if (fireCaptureBehavior(p, xf, yf)) {
						return true;
					}
					return fireStandardMove(p, xf, yf);
				}
				else {
					if (waterCaptureBehavior(p, xf, yf)) {
						return true;
					}
					return waterStandardMove(p, xf, yf);
				}
			}
		}
		return false;
	}

	public void select(int x, int y) { //in progress
		whiteX = x;
		whiteY = y;
		Piece chosen = gamePieces[x][y];
		if (selected == null) {
			selected = chosen;
		}
		else if (chosen != null && !hasMoved) {
			selected = chosen;
		}
		else {
			selected.move(x, y);
			hasMoved = true;
		}
	}

	public void place(Piece p, int x, int y) { 
		if ((x >= 0 && x <= 7) && (y >= 0 && y <= 7)) {
			gamePieces[x][y] = p;
			if (p.isFire()) {
				numberFire += 1;
			}
			else {
				numberWater += 1;
			}
		}
	}

	public Piece remove(int x, int y) { //in progress
		String message = "No piece there!";
		if ((x < 0 || x > 7) || (y < 0 && y > 7)) {
			message = "Out of bounds!";
			System.out.println(message);
			return null;
		}
		else if (pieceAt(x, y) == null) {
			System.out.println(message);
			return null;
		}
		Piece removed = gamePieces[x][y];
		gamePieces[x][y] = null;
		removeType(removed);
 		return removed;
	}

	private void removeType(Piece p) {
		if (p.isFire()) {
			numberFire -= 1;
		}
		else {
			numberWater -= 1;
		}
	}

	public boolean canEndTurn() { //in progress
		if (selected != null && (hasMoved)) {
			return true;
		}
		return false;
	}

	public void endTurn() { //in progress
		currentPlayer = !currentPlayer;
		selected.doneCapturing();
		selected = null;
		hasMoved = false;
		whiteX = 100;
		whiteY = 100;
	}

	public String winner() { //in progress
		boolean noFire = (numberFire == 0);
		boolean noWater = (numberWater == 0);
		if ((numberFire == 0) && (numberWater == 0)) {
			return "No one";
		}
		else if (numberFire == 0) {
			return "Water";
		}
		else if (numberWater == 0) {
			return "Fire";
		}
		else {
			return null;
		}

	}

	// PRIVATE METHODS //

	private void drawBoard(int N) { //CHANGE TO PRIVATE
	    for (int i = 0; i < N; i++) { //rows - y
	        for (int j = 0; j < N; j++) { //columns - x
	        	if (i == whiteX && j == whiteY) {
	        		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	        	}
	            else if ((i + j) % 2 == 0) {
	            	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	            }
	            else {                  
	            	StdDrawPlus.setPenColor(StdDrawPlus.RED);
	            }
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

	         //    if (gamePieces[j][i] != null) {
	         // 	   drawPiece(j, i, gamePieces[j][i]);
	         // 	   StdDrawPlus.picture(j + .5, i + .5, getImagePath(gamePieces[j][i]), 1, 1);
	        	// }
	        }
	    }

	    if (!empty) {
		    for (int y = 0; y < N; y++) { //rows - y //fix this too slow
		        for (int x = 0; x < N; x++) { //columns - x
		            if (gamePieces[x][y] != null) {
		         	   drawPiece(x, y, gamePieces[x][y]);
		        	}

		        }
		    }
		}	
	}

	private void createPieces(int N) { //initializes pieces when game is set up
		if (!empty && (N % 2 == 0)) {

			for (int j = 0; j < N; j++) {
				if (j % 2 == 0) {
					gamePieces[j][0] = new Piece(true, this, j, 0, "pawn");
					gamePieces[j][2] = new Piece(true, this, j, 2, "bomb");
					gamePieces[j][6] = new Piece(false, this, j , 6, "shield");

				}

				else if (j % 2 == 1) {
					gamePieces[j][1] = new Piece(true, this, j , 1, "shield");
					gamePieces[j][5] = new Piece(false, this, j, 5, "bomb");
					gamePieces[j][7] = new Piece(false, this, j, 7, "pawn");

				}
			}
			numberWater = 12;
			numberFire = 12;
		}
	}

	private void drawPiece(int x, int y, Piece p) { //draws a piece
		StdDrawPlus.picture(x + .5, y + .5, getImagePath(p), 1, 1);
	}

	private String getImagePath(Piece p) { //gets image "url"
		String path = "img/";
		if (p.isShield()) {
			path += "shield-";
		}
		else if (p.isBomb()) {
			path += "bomb-";
		}
		else {
			path += "pawn-";
		}

		if (p.isFire()) {
			path += "fire";
		}
		else {
			path += "water";
		}

		if (p.isKing()) {
			path += "-crowned";
		}

		path += ".png";
		return path;
	}

	public static void main(String[] args) {
		StdDrawPlus.setCanvasSize();
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
	
        Board gameBoard = new Board(false);
        gameBoard.drawBoard(8);

        /* Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
        	
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int intx = (int) x;
                int inty = (int) y;
                if (gameBoard.canSelect(intx, inty)) {
                	gameBoard.select(intx, inty);
         	   }
        	}
        	StdDrawPlus.clear();
        	gameBoard.drawBoard(8);
            if (StdDrawPlus.isSpacePressed() && gameBoard.canEndTurn()) {
            	gameBoard.endTurn();
            }       
            gameBoard.winner(); 
            StdDrawPlus.show(100);
        }
    }
}