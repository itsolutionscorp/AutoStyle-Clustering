// Author: Aaron Tang
// Date: 2/14/15

public class Board {

    private static Piece[][] pieces;
    private int boardSize = 8;
    private boolean redTurn = true;
    private Piece selectedPiece;
    private int selX;
    private int selY;
    private Piece removed;
    private boolean hasMoved = false;
    private Piece capturedPiece;
    private boolean turnDone = false;

	public Board(boolean shouldBeEmpty) {
		//int boardSize = 8;
		pieces = new Piece[boardSize][boardSize];

		if (shouldBeEmpty == false) {
			for (int j = 0; j < boardSize; j = j + 2) {
				pieces[j][0] = new Piece(true, this, j, 0, "pawn");
			}
			for (int j = 1; j < boardSize; j = j + 2) {
				pieces[j][1] = new Piece(true, this, j, 1, "shield");
			}
			for (int j = 0; j < boardSize; j = j + 2) {
				pieces[j][2] = new Piece(true, this, j, 2, "bomb");
			}
			for (int j = 1; j < boardSize; j = j + 2) {
				pieces[j][5] = new Piece(false, this, j, 5, "bomb");
			}
			for (int j = 0; j < boardSize; j = j + 2) {
				pieces[j][6] = new Piece(false, this, j, 6, "shield");
			}
			for (int j = 1; j < boardSize; j = j + 2) {
				pieces[j][7] = new Piece(false, this, j, 7, "pawn");
			}
		}
		drawBoard(boardSize);
		
	}

    private static void drawBoard(int N) {
    	StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i = i + 1) {
            for (int j = 0; j < N; j = j + 1) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                // StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                	if (pieces[i][j].isFire() == true) {
                		if ((pieces[i][j].isShield() == false) && (pieces[i][j].isBomb() == false)) {
                			if (pieces[i][j].isKing() == true) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                			}
                		}
                		if (pieces[i][j].isShield() == true) {
                			if (pieces[i][j].isKing() == true) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                			}
                		}
                		if (pieces[i][j].isBomb() == true) {
                			if (pieces[i][j].isKing() == true) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                			}
                		}
                	}
                	else {
						if ((pieces[i][j].isShield() == false) && (pieces[i][j].isBomb() == false)) {
                			if (pieces[i][j].isKing() == true) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                			}
                		}
                		if (pieces[i][j].isShield() == true) {
                			if (pieces[i][j].isKing() == true) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                			}
                		}
                		if (pieces[i][j].isBomb() == true) {
                			if (pieces[i][j].isKing() == true) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			}
                			else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                			}
                		}
                	}
                }
            }
        }
    }

	public Piece pieceAt(int x, int y) {
		if ((x >= boardSize) || (x < 0) || (y >= boardSize) || (y < 0)) {
			return null;
		}
		else if (pieces[x][y] != null) {
			return pieces[x][y];
		}
		else {
			return null;
		}
	}

	public void place(Piece p, int x, int y) {
		if ((x >= boardSize) || (x < 0) || (y >= boardSize) || (y < 0) || (p == null)) {
			return;
		}
		else {
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if ((x >= boardSize) || (x < 0) || (y >= boardSize) || (y < 0)) {
			System.out.println("Out of bounds, mastermind.");
			return null;
		}
		else if (pieces[x][y] == null) {
			System.out.println("Nothing was there!");
			return null;
		}
		else {
			removed = pieces[x][y];
			pieces[x][y] = null;
			return removed;
		}
	}

	public boolean canSelect(int x, int y) {
		// boundaries of board
		if ((x >= boardSize) || (x < 0) || (y >= boardSize) || (y < 0)) {
			return false;
		}

		// square with a piece selected
		if (pieces[x][y] != null) {
			// make sure the current player only selects his or her pieces
			if (pieces[x][y].isFire() == redTurn) {

				// player has not selected a piece yet
				if (selectedPiece == null) {
					// only select one piece that has moved per turn (for bomb cases, since a bomb resets selectedPiece to null, but a turn has already been made)
					if (this.canEndTurn() == false) {
						return true;
					}
				}

				// player selected a piece before but has not moved/captured, e.g. select a different piece
				else {
					if ((this.hasMoved == false) && (selectedPiece.hasCaptured() == false)) {
						return true;
					}
				}
			}
		}

		// empty square selected
		if (pieces[x][y] == null) {

			// must have a piece selected
			if (selectedPiece == null) {
				return false;
			}

			else {

				// selected piece cannot move more than once
				if (this.hasMoved == true) {

					if (selectedPiece.hasCaptured() == false) {
						return false;
					}
					else if (this.validCapture(selX, selY, x, y) == true) {
						return true;
					}
					return false;
				}
				// only case is when hasMoved and hasCaptured are false
				else {
					if (this.validMove(selX, selY, x, y) == true) {
					// only move if the selected piece has not done a capture yet
						if (selectedPiece.hasCaptured() == true) {
							return false;
						}
						return true;	
					}
					else if (this.validCapture(selX, selY, x, y) == true) {
						return true;
					}
					return false;
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
		// select a piece
		if (pieces[x][y] != null) {
			selectedPiece = this.pieceAt(x, y);
			selX = x;
			selY = y;
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		}
		// move the selected piece to the selected square
		else {
			selectedPiece.move(x, y);
			selX = x;
			selY = y;

			// if a bomb has exploded, make sure that there is no currently selected Piece
			if (this.pieceAt(selX, selY) == null) {
				selectedPiece = null;
			}
			
			hasMoved = true;
			turnDone = true;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		// must choose square inside boardSize
		if ((xf >= boardSize) || (xf < 0) || (yf >= boardSize) || (yf < 0)) {
			return false;
		}
		// must move to an empty square
		else if (pieces[xf][yf] != null) {
			return false;
		}
		// non-capturing movement
		else if (((yf - yi) == 1) || (yf - yi) == -1) {
			// moving upwards diagonally
			if ((pieces[xi][yi].isKing() == true) || (pieces[xi][yi].isFire() == true)) {

				if ((yf - yi) == 1) {
					if (((xf - xi) == 1) || ((xf - xi) == -1)) {
						return true;
					}
				}
			}
			// moving downwards diagonally
			if ((pieces[xi][yi].isKing() == true) || (pieces[xi][yi].isFire() == false)) {

				if ((yf - yi) == -1) {
					if (((xf - xi) == 1) || ((xf - xi) == -1)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean validCapture(int xi, int yi, int xf, int yf) {
		// must choose square inside boardSize
		if ((xf >= boardSize) || (xf < 0) || (yf >= boardSize) || (yf < 0)) {
			return false;
		}
		// must move to an empty square
		else if (pieces[xf][yf] != null) {
			return false;
		}
		// capturing movement
		else if (((yf - yi) == 2) || (yf - yi) == -2) {
			// capturing upwards diagonally
			if ((pieces[xi][yi].isKing() == true) || (pieces[xi][yi].isFire() == true)) {
				if ((yf - yi) == 2) {

					// diagonally up to the right
					if (((xf - xi) == 2) && (pieces[xi + 1][yi + 1] != null)) {
						if (pieces[xi + 1][yi + 1].isFire() != pieces[xi][yi].isFire()) {

							capturedPiece = pieces[xi + 1][yi + 1];
							return true;
						}
					}

					// diagonally up to the left
					if (((xf - xi) == -2) && (pieces[xi - 1][yi + 1] != null)) {
						if (pieces[xi - 1][yi + 1].isFire() != pieces[xi][yi].isFire()) {

							capturedPiece = pieces[xi - 1][yi + 1];
							return true;
						}
					}
				}
			}

			// moving downwards diagonally
			if ((pieces[xi][yi].isKing() == true) || (pieces[xi][yi].isFire() == false)) {
				if ((yf - yi) == -2) {

					// diagonally down to the right
					if (((xf - xi) == 2) && (pieces[xi + 1][yi - 1] != null)) {
						if (pieces[xi + 1][yi - 1].isFire() != pieces[xi][yi].isFire()) {

							capturedPiece = pieces[xi + 1][yi - 1];
							return true;
						}
					}

					// diagonally down to the left
					if (((xf - xi) == -2) && (pieces[xi - 1][yi - 1] != null)) {
						if (pieces[xi - 1][yi - 1].isFire() != pieces[xi][yi].isFire()) {

							capturedPiece = pieces[xi - 1][yi - 1];
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean canEndTurn() {
		return this.turnDone;
	}

	public void endTurn() {
		// the moved piece can be free to move in future turns
		hasMoved = false;
		if (selectedPiece != null) {
			selectedPiece.doneCapturing();
		}

		// the next player can choose a square with a piece to select
		selectedPiece = null;
		selX = 0;
		selY = 0;

		// manages turn order
		if (this.redTurn == true) {
			this.redTurn = false;
		}
		else {
			this.redTurn = true;
		}
		this.turnDone = false;
	}
	
	public String winner() {
		int fireCounter = 0;
		int waterCounter = 0;

		// loop through the array and count up the number of pieces left for each side
		for (int i = 0; i < boardSize; i = i + 1) {
			for (int j = 0; j < boardSize; j = j + 1) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire() == true) {
						fireCounter = fireCounter + 1;
					}
					else {
						waterCounter = waterCounter + 1;
					}
				}
			}
		}

		if ((fireCounter == 0) && (waterCounter == 0)) {
			return "No one";
		}
		else if ((fireCounter > 0) && (waterCounter == 0)) {
			return "Fire";
		}
		else if ((fireCounter == 0) && (waterCounter > 0)) {
			return "Water";
		}
		else {
			return null;
		}
	}


	public static void main(String[] args) {

		// Even though citation not needed, it helps me to say that this is adapted from StdDrawDemo.java.

        Board b = new Board(false);
        
        while(true) {
        	drawBoard(b.boardSize);
        	if (StdDrawPlus.mousePressed()) {
        		double x = StdDrawPlus.mouseX();
        		double y = StdDrawPlus.mouseY();
        		if (b.canSelect(((int) x), ((int) y)) == true) {
        			b.select(((int) x), ((int) y));
        		}
        	}
        	if (StdDrawPlus.isSpacePressed()) {
        		if (b.canEndTurn() == true) {
        			b.endTurn();
        		}
        	}
        	if (b.winner() != null) {
        		System.out.println(b.winner() + " wins!");
        		break;
        	}
        	
        	StdDrawPlus.show(25);
        }
	}
}