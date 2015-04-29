public class Board {
	private Piece[][] board;
	private static int N = 8;
	private boolean fireTurn;
	private Piece selected;
	private int[] selectedCoord; // coordinates of current selection
	private boolean hasMoved; // player has either moved or captured

	// checks if (x, y) is a valid location
	private boolean valid(int x, int y) {
		return x >=0 && x < N && y >= 0 && y < N;
	}

	// makes a new piece and places it on the board
	private void makePiece(boolean isFire, int x, int y, String type) {
		Piece newPiece = new Piece(isFire, this, x, y, type);
		board[x][y] = newPiece;
	}

	public Board(boolean shouldBeEmpty) {
		board = new Piece[N][N];
		if (!shouldBeEmpty) {
			for (int i = 0; i < N; i += 2) {
				makePiece(true, i, 0, "pawn");
				makePiece(true, i, 2, "bomb");
				makePiece(false, i, 6, "shield");
			}
			for (int i = 1; i < N; i += 2) {
				makePiece(true, i, 1, "shield");
				makePiece(false, i, 5, "bomb");
				makePiece(false, i, 7, "pawn");
			}
		}
		fireTurn = true;
		selected = null;
		hasMoved = false;
		selectedCoord = new int[] {-1, -1};
	}

	public Piece pieceAt(int x, int y) {
		if (valid(x,y)) {
			return board[x][y];
		}
		return null;
	}

	public void place(Piece p, int x, int y) {
		if (valid(x,y) && p != null) {
			board[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (!valid(x,y)) {
			System.out.println("(" + x + ", " + y + ") is not a valid location.");
			return null;
		}
		if (board[x][y] == null) {
			System.out.println("No piece at (" + x + ", " + y + ")");
			return null;
		}
		Piece temp = board[x][y];
		board[x][y] = null;
		return temp;
 	}

 	private boolean validMove(int xi, int yi, int xf, int yf) {
 		Piece orig = pieceAt(xi, yi);
 		// (xi, yi) must have a valid piece, and destination must be empty
 		if (orig == null || !valid(xf,yf) || pieceAt(xf, yf) != null) {
 			return false;
 		}
 		int dx = xf - xi;
 		int dy = yf - yi;
 		// either piece has to be a king, or it has to be moving in valid direction
 		if (!(orig.isKing() || (orig.isFire() && dy > 0) || (!orig.isFire() && dy < 0))) {
 			return false;
 		}
 		// moving one square diagonally
 		if ((Math.abs(dx) == 1) && (Math.abs(dy) == 1)) {
 			return true;
 		}
 		// capturing
 		else if ((Math.abs(dx) == 2) && (Math.abs(dy) == 2)) {
 			int midX = xi + dx/2;
 			int midY = yi + dy/2;
 			Piece mid = pieceAt(midX, midY);
 			// piece in middle must be opposite color
 			if (mid != null && mid.isFire() != orig.isFire()) {
 				return true;
 			} 
 		}
 		return false;
 	}

 	public boolean canSelect(int x, int y) {
 		if (!valid(x, y)) {
 			return false;
 		}
 		Piece curr = pieceAt(x, y);
 		// selecting a square with a piece
 		if (curr!= null) {
 			return (curr.isFire() == fireTurn) && ((selected == null) || !hasMoved);
 		}
 		// selecting an empty square
 		else {
 			if (selected == null) {
 				return false;
 			}
 			if (!hasMoved) {
 				return validMove(selectedCoord[0], selectedCoord[1], x, y);
 			} 
 			// multi-capture: must have captured previously, and must be capturing again
 			else {
 				int dx = x - selectedCoord[0];
 				return selected.hasCaptured() && validMove(selectedCoord[0], selectedCoord[1], x, y) && (Math.abs(dx) == 2);
 			}
 		}
 	}

 	public void select(int x, int y) {
 		Piece curr = pieceAt(x,y);
 		selectedCoord[0] = x;
 		selectedCoord[1] = y;
 		// selected a square with a piece
 		if (curr != null) {
 			selected = curr;
 		}
 		// moving a piece to an empty square
 		else if (selected != null) {
 			selected.move(x, y);
 			hasMoved = true;	
 		}
 	}

 	public boolean canEndTurn() {
 		return hasMoved;
 	}

 	public void endTurn() {
 		fireTurn = !fireTurn;
 		hasMoved = false;
 		if (selected != null)
 			selected.doneCapturing();
 		selected = null;
 		selectedCoord[0] = -1;
 		selectedCoord[1] = -1;
 	}

 	public String winner() {
 		int numFire = 0;
 		int numWater = 0;
 		Piece curr = null;
 		for (int i = 0; i < N; i += 1) {
 			for (int j = 0; j < N; j += 1) {
 				curr = pieceAt(i, j);
 				if (curr != null) {
 					if (curr.isFire()) {
 						numFire += 1;
 					}
 					else {
 						numWater += 1;
 					}
 				}
 			}
 		}
 		if (numFire == 0) {
 			if (numWater == 0) {
 				return "No one";
 			}
 			return "Water";
 		}
 		else if (numWater == 0) {
 			return "Fire";
 		}
 		return null;
 	}

 	private void drawBoard() {
 		Piece curr = null;
 		String img = null;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                curr = pieceAt(i, j);
                if (curr != null) {
                	if (curr == selected) {
                		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	}
                	if (curr.isBomb()) 			img = "bomb";
                	else if (curr.isShield()) 	img = "shield";
                	else 						img = "pawn";	
                	if (curr.isFire())			img += "-fire";
                	else						img += "-water";
                	if (curr.isKing())			img += "-crowned";
                    StdDrawPlus.picture(i + .5, j + .5, "img/" + img + ".png", 1, 1);
                }
            }
        }
    }

	public static void main (String[] args) {
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		Board b = new Board(false);
		String endMessage = null;
		while(true) {
            b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                int x = (int)(StdDrawPlus.mouseX());
                int y = (int)(StdDrawPlus.mouseY());
                if (b.canSelect(x, y)) {
                	b.select(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            		endMessage = b.winner();
            		if (endMessage != null) {
            			System.out.println(endMessage);
            			return;
            		}
            	}
            }
            StdDrawPlus.show(10);
        }
	}
}