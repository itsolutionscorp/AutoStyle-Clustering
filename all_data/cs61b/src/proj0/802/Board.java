
public class Board {
	private static final int N = 8;
	private Piece[][] pieces;
	private int[] hasSelected;
	private int player; 			// 0 if Fire, 1 if Water
	private boolean hasMoved;
	private int numOfFirePieces = 0;
	private int numOfWaterPieces = 0;
	
//	public static final int N = 8;
//	public Piece[][] pieces;
//	public int[] hasSelected;
//	public int player; 			// 0 if Fire, 1 if Water
//	public boolean hasMoved;
//	public int numOfFirePieces = 0;
//	public int numOfWaterPieces = 0;
	
	/** Loads the game board and initializes the game */
	public static void main(String[] args) {
		Board board = new Board(false);
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        while (true) {
        	board.draw();
        	if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect((int) x, (int) y)) {
                	board.select((int) x, (int) y);
                }
        	}
        	if (StdDrawPlus.isSpacePressed()) {
        		if (board.canEndTurn()) {
        			board.endTurn();
        			if (board.winner() != null) {
        				System.out.println(board.winner());
        				break;
        			}
        		}
        	}
            StdDrawPlus.show(10);
        }
	}
	
	/** Draws the game board with pieces */
	private void draw() {
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                if (hasSelected != null && i == hasSelected[0] && j == hasSelected[1]) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                Piece p = pieceAt(i, j);
                if (p != null) {
                	if (p.isFire()) {
                		if (p.isKing()) {
                			if (p.isShield()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			} else if (p.isBomb()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			}
                		} else {
                			if (p.isShield()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                			} else if (p.isBomb()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                			}
                		}
                	} else {
                		if (p.isKing()) {
                			if (p.isShield()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			} else if (p.isBomb()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                			}
                		} else {
                			if (p.isShield()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                			} else if (p.isBomb()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                			}
                		}
                	}       
                }
            }
		}
	}
	
	/** The constructor for Board. If shouldBeEmpty is true, initializes an empty Board. 
	 * Otherwise, initializes a Board with the default configuration. Note that an empty
	 * Board could possibly be useful for testing purposes.
	 * @param shouldBeEmpty
	 */
	public Board(boolean shouldBeEmpty) {
    	this.pieces = new Piece[N][N];
    	if (!shouldBeEmpty) {
    		for (int i = 0; i < N; i += 2) {
            	place(new Piece(true, this, i, 0, "pawn"), i, 0);
            	place(new Piece(true, this, i + 1, 1, "shield"), i + 1, 1);
            	place(new Piece(true, this, i, 2, "bomb"), i, 2);
            	place(new Piece(false, this, i + 1, N - 1, "pawn"), i + 1, N - 1);
            	place(new Piece(false, this, i, N - 2, "shield"), i, N - 2);
            	place(new Piece(false, this, i + 1, N - 3, "bomb"), i + 1, N - 3);
            }
    	}
    }
	
	/**Gets the piece at position (x, y) on the board, or null if there is no piece. 
	 * If (x, y) are out of bounds, returns null.
	 * @param x
	 * @param y
	 */
	public Piece pieceAt(int x, int y) {
		if (!withinBounds(x, y)) {
			return null;
		}
		return pieces[x][y];
	}
	
	/** Returns true if the square at (x, y) can be selected. */
	public boolean canSelect(int x, int y) {
		if (!withinBounds(x, y)) {
			return false;
		}
		Piece p = pieceAt(x, y);
		if (p != null) {
			if (p.side() == player && !hasMoved) { return true; }
		} else {
			if (!hasMoved) {
				if (hasSelected != null) {
					if (player == 0 && validMoveFire(hasSelected[0], hasSelected[1], x, y)) {
						return true;
					}
					if (player == 1 && validMoveWater(hasSelected[0], hasSelected[1], x, y)) {
						return true;
					}
				}
			} else {
				if (hasSelected == null) { return false; }
				Piece selectedPiece = pieceAt(hasSelected[0], hasSelected[1]);
				if (selectedPiece.hasCaptured()) {
					if (selectedPiece.isKing() && validCaptureKing(hasSelected[0], hasSelected[1], x, y)) {
						return true;
					}
					if (player == 0 && selectedPiece.isFire() && validCaptureFire(hasSelected[0], hasSelected[1], x, y)) {
						return true;
					}
					if (player == 1 && !selectedPiece.isFire() && validCaptureWater(hasSelected[0], hasSelected[1], x, y)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/** Determines whether (xi, yi) to (xf, yf) is a valid move. */
	private boolean validMoveFire(int xi, int yi, int xf, int yf) {
		if (!withinBounds(xi, yi) || !withinBounds(xf, yf)) { return false; }
		if (pieceAt(xf, yf) != null) { return false; }
		Piece p = pieceAt(xi, yi);
		if (!p.isKing()) {
			if ((yf - yi == 1) && (Math.abs(xf - xi) == 1)) { return true; }
			if (validCaptureFire(xi, yi, xf, yf)) {
				return true;
			}
		} else {
			if (Math.abs(yf - yi) == 1 && Math.abs(xf - xi) == 1) { return true; }
			if (validCaptureKing(xi, yi, xf, yf)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean validCaptureFire(int xi, int yi, int xf, int yf) {
		Piece p = pieceAt(xi, yi);
		if (pieceAt(xi + 1, yi + 1) != null && pieceAt(xi + 1, yi + 1).side() + p.side() == 1 && xi + 2 == xf && yi + 2 == yf ||
			pieceAt(xi - 1, yi + 1) != null && pieceAt(xi - 1, yi + 1).side() + p.side() == 1 && xi - 2 == xf && yi + 2 == yf) {
			return true;
		}
		return false;
	}
	
	private boolean validMoveWater(int xi, int yi, int xf, int yf) {
		if (!withinBounds(xi, yi) || !withinBounds(xf, yf)) { return false; }
		if (pieceAt(xf, yf) != null) { return false; }
		Piece p = pieceAt(xi, yi);
		if (!p.isKing()) {
			if ((yf - yi == -1) && (Math.abs(xf - xi) == 1)) { return true; }
			if (validCaptureWater(xi, yi, xf, yf)) {
				return true;
			}
		} else {
			if (Math.abs(yf - yi) == 1 && Math.abs(xf - xi) == 1) { return true; }
			if (validCaptureKing(xi, yi, xf, yf)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean validCaptureWater(int xi, int yi, int xf, int yf) {
		Piece p = pieceAt(xi, yi);
		if (pieceAt(xi - 1, yi - 1) != null && pieceAt(xi - 1, yi - 1).side() + p.side() == 1 && xi - 2 == xf && yi - 2 == yf ||
			pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 1, yi - 1).side() + p.side() == 1 && xi + 2 == xf && yi - 2 == yf) {
			return true;
		}
		return false;
	}
	
	private boolean validCaptureKing(int xi, int yi, int xf, int yf) {
		Piece p = pieceAt(xi, yi);
		if (pieceAt(xi + 1, yi + 1) != null && pieceAt(xi + 1, yi + 1).side() + p.side() == 1 && xi + 2 == xf && yi + 2 == yf ||
			pieceAt(xi - 1, yi + 1) != null && pieceAt(xi - 1, yi + 1).side() + p.side() == 1 && xi - 2 == xf && yi + 2 == yf ||
			pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 1, yi - 1).side() + p.side() == 1 && xi + 2 == xf && yi - 2 == yf ||
			pieceAt(xi - 1, yi - 1) != null && pieceAt(xi - 1, yi - 1).side() + p.side() == 1 && xi - 2 == xf && yi - 2 == yf) {
			return true;
		}
		return false;
	}
	
	/** Selects the square at (x, y). This method assumes canSelect (x,y) returns true. */
	public void select(int x, int y) {
//		if (!canSelect(x, y)) {
//			return;
//		}
		if (pieceAt(x, y) == null) {
			hasMoved = true;
			pieceAt(hasSelected[0], hasSelected[1]).move(x, y);
		}
		if (pieceAt(x, y) != null) {
//			System.out.println("OK");
			hasSelected = new int[] { x, y };
		} else {
			hasSelected = null;
		}
	}
	
	/** Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. 
	 * If another piece already exists at (x, y), p will replace that piece.
	 */
	public void place(Piece p, int x, int y) {
		if (!withinBounds(x, y) || p == null) {
			return;
		}
		pieces[x][y] = p;
		if (p.isFire()) {
			numOfFirePieces += 1;
		}
		if (!p.isFire()) {
			numOfWaterPieces += 1;
		}
		return;
	}
	
	/** Find the coordinates int[] of p in Board. If the board does not contain the piece, return null */
	private int[] find(Piece p) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (pieces[i][j] == p) {
					return new int[] { i, j };
				}
			}
		}
		return null;
	}
	
	/** Executes a remove. Returns the piece that was removed. 
	 * If the input (x, y) is out of bounds, returns null and prints an appropriate message. 
	 * If there is no piece at (x, y), returns null and prints an appropriate message.
	 */
	public Piece remove(int x, int y) {
		if (!withinBounds(x, y)) {
			System.out.println("Index out of bounds.");
			return null;
		} else if (pieceAt(x, y) == null) {
			System.out.println("No piece to remove at index.");
			return null;
		} else {
			Piece p = pieceAt(x, y);
			if (p.isFire()) {
				numOfFirePieces -= 1;
			} else {
				numOfWaterPieces -= 1;
			}
			pieces[x][y] = null;
			return p;
		}
	}
	
	private static boolean withinBounds(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	/** Returns whether or not the the current player can end their turn.
	 * To be able to end a turn, a piece must have moved or performed a capture.
	 */
	public boolean canEndTurn() {
		return hasMoved;
	}
	
	/** Called at the end of each turn. Handles switching of players and anything else that 
	 * should happen at the end of a turn.
	 */
	public void endTurn() {
		player = 1 - player;
		if (hasSelected != null) {
			Piece selectedPiece = pieceAt(hasSelected[0], hasSelected[1]);
			selectedPiece.doneCapturing();
		}
		hasSelected = null;
		hasMoved = false;
	}
	
	/** Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board), 
	 * or null if the game is not yet over. Assume there is no stalemate situation in which the 
	 * current player has pieces but cannot legally move any of them (In this event, just leave it 
	 * at null). Determine the winner solely by the number of pieces belonging to each team.
	 */
	public String winner(){
		if (numOfFirePieces == 0 && numOfWaterPieces == 0) {
			return "No one";
		} else if (numOfWaterPieces == 0) {
			return "Fire";
		} else if (numOfFirePieces == 0) {
			return "Water";
		} else {
			return null;
		}
	}
}
