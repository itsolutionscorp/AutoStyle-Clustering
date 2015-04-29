/* Ryan Row */

public class Board {
	private Piece selectedPiece;
	private boolean hasMoved;
	private int playerTurn;
	private Piece[][] pieces;
	private int boardSize = 8;
	private int selectedXpos;
	private int selectedYpos;

	/* Initializes the 24 game pieces if shouldBeEmpty is false. */
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[boardSize][boardSize];
		hasMoved = false;
		playerTurn = 0;
		selectedXpos = -1;
		selectedYpos = -1;
		selectedPiece = null;

		if (!shouldBeEmpty) {
			initializePieces(true, 0, "pawn");
			initializePieces(false, 6, "shield");
			initializePieces(true, 2, "bomb");
		} 
	}

	private void initializePieces(boolean isFire, int row, String type) {
		for (int i = 0; i < boardSize; i+=2) {
			pieces[i][row] = new Piece(isFire, this, i, row, type);
			pieces[i+1][boardSize - row - 1] = new Piece(!isFire, this, i+1, boardSize - row - 1, type);
		}
	}

    private  String getImage(Piece piece) {
    	if (piece.isFire()) {
    		if (piece.isBomb()) {
    			return "img/bomb-fire.png";
    		}
    		if (piece.isShield()) {
    			return "img/shield-fire.png";
    		} else {
    			return "img/pawn-fire.png";
    		}
    	}
    	if (piece.isBomb()) {
    		return "img/bomb-water.png";
    	}
    	if (piece.isShield()) {
    		return "img/shield-water.png";
    	} else {
    		return "img/pawn-water.png";
    		}
    	}

    private  String getKingImage(Piece kingPiece) {
    	if (kingPiece.isFire()) {
    		if (kingPiece.isBomb()) {
    			return "img/bomb-fire-crowned.png";
    		}
    		if (kingPiece.isShield()) {
    			return "img/shield-fire-crowned.png";
    		} else {
    			return "img/pawn-fire-crowned.png";
    		}
    	}
    	if (kingPiece.isBomb()) {
    		return "img/bomb-water-crowned.png";
    	}
    	if (kingPiece.isShield()) {
    		return "img/shield-water-crowned.png";
    	} else {
    		return "img/pawn-water-crowned.png";
    		}
    }

	public Piece pieceAt(int x, int y) {
		if (outOfBounds(x, y)) {
			return null;
		}
		return pieces[x][y];
		}

	public  boolean canSelect(int x, int y) {
		if (outOfBounds(x, y)) {
			return false;
		}

		Piece temp = pieces[x][y];
		if (((temp != null) && (temp.side() != playerTurn))) {
			return false;
		} else if (selectedPiece == null && temp == null) {
			return false;
		} else if ((selectedPiece == null)) {
			return true;
		} else if (temp == null && !validMove(selectedXpos, selectedYpos, x, y)) {
			return false;
		} else if (!hasMoved) {
			return true;
		} else if (hasMoved && validMove(selectedXpos, selectedYpos, x, y)) {
			return true;
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece player = pieces[xi][yi];
		Piece temp = pieces[xf][yf];

		if (outOfPieceBounds(xi, xf, yi, yf)){
			return false;
		}
		if (Math.abs(yf - yi) != Math.abs(xf - xi)) {
			return false;
		}
		if (player == null) {
			return false;
		}
		if (temp != null) {
			return false;
		}
		if (selectedPiece != null && !player.isKing()) {
			if ((yf - yi > 0 && (playerTurn != 0)) || (yf - yi < 0 && (playerTurn != 1))) {
				return false;
			}
		}
		if (Math.abs(yf - yi) == 1 && !hasMoved) {
			return true;
		}
		if (pieceAt((xf+xi)/2, (yf+yi)/2) == null) {
			return false;
		}
		if (pieceAt((xf+xi)/2, (yf+yi)/2).side() == playerTurn) {
			return false;
		}
		if (hasMoved && !selectedPiece.hasCaptured()) {
			return false;
		}
		return true;
	}

	private  boolean outOfPieceBounds(int x1, int x2, int y1, int y2) {
		if (Math.abs(x2 - x1) > 2 || Math.abs(y2 - y1) > 2) {
			return true;
		}
		return false;
	}

	private  boolean outOfBounds(int x, int y) {
		if ((x > 7) || (y > 7) || (y < 0) || (x < 0)) {
			return true;
		}
		return false;
	}

	public  void select(int x, int y) {
		Piece temp = pieces[x][y];
		if (temp != null){
			selectedPiece = temp;
			selectedXpos = x;
			selectedYpos = y;
		} else if (selectedPiece != null) {
			place(selectedPiece, x, y);
			hasMoved = true;
			selectedXpos = x;
			selectedYpos = y;
			selectedPiece.move(x,y);
		}
	}

	public  void place(Piece p, int x, int y) {
		if ((p == null) || outOfBounds(x,y)) {
			return ;
		}
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if (p == pieceAt(i, j)) {
					remove(i, j);
					if (pieceAt(x,y) != null) {
						remove(x, y);
					}
				}
			}
		}
		pieces[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if (outOfBounds(x, y)) {
			System.out.println("The input is out of range.");
			return null;
		}

		Piece removePiece = pieces[x][y];
		if (removePiece == null) {
			System.out.println("There is no piece currently at that square.");
			return null;
		}
		pieces[x][y] = null;
		return removePiece;
	}

	public  boolean canEndTurn() {
		return hasMoved;
	}

	public  void endTurn() {
		playerTurn = 1 - playerTurn;
		hasMoved = false;
		if (selectedPiece != null){
			selectedPiece.doneCapturing();
		}
		selectedPiece = null;
	}

	public String winner() {
		int water = 0;
		int fire = 0;
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				Piece piece = pieceAt(i, j);
				if (piece != null) {
					if (piece.isFire()) {
						fire += 1;
					} else {
						water += 1;
					}

				}
			}
		}
		if (water + fire == 0) {
			return "No one";
		} else if (fire == 0) {
			return "Water";
		} else if (water == 0) {
			return "Fire";
		} else {
			return null;
		}
	}

	private void newGame() {

	}

	private void playGame() {
		StdDrawPlus.setXscale(0, boardSize);
        StdDrawPlus.setYscale(0, boardSize);

        while (true) {
        	drawBoard(boardSize);
        }
    }

	// main will start GUI supported version of the game
	public static void main(String[] args) {
		Board board = new Board(false);
		board.playGame();
	}

	// Draws the game board and pieces (if necessary). 
	private  void drawBoard(int boardSize) {
		Piece tempPiece;
		String image;
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				if ((pieces[i][j] == selectedPiece) && (pieces[i][j] != null)) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.ORANGE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                tempPiece = pieceAt(i, j);
                if (tempPiece != null) {
                	if (tempPiece.isKing()) {
                		image = getKingImage(tempPiece);
                	} else {
                		image = getImage(tempPiece);
                	}
                    StdDrawPlus.picture(i + .5, j + .5, image, 1, 1);
                }
            }
        }
        if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (canSelect((int) x, (int) y)) {
                	select((int) x, (int) y);
                }
            }
        if (StdDrawPlus.isSpacePressed()) {
        	if (canEndTurn()) {
        		endTurn();
        	}
        }

        if (StdDrawPlus.isNPressed()) {
        	Board board = new Board(false);
        	board.playGame();
        }
        StdDrawPlus.show(50);
    }
}







