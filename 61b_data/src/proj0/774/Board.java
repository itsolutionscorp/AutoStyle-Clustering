public class Board {

	private Piece[][] pieces;
	private static Board b;
	private Piece selected;
	private int selectedX, selectedY;	
	private boolean moved, capturing;
	private int turn;

	public static void main(String[] args) {
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        b = new Board(false);
        while (b.winner() == null) {
        	b.drawBoard(N);
        	if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                	b.select((int) x, (int) y);
                	if (b.selected == null || b.selected.hasCaptured()) {
                		b.capturing = true;
                	}
	            }
            }
            if (StdDrawPlus.isSpacePressed()) {
	            if (b.canEndTurn()) {
	            	b.endTurn();
	            }
	        }
            StdDrawPlus.show(100);
        }
        b.drawBoard(N);
        StdDrawPlus.show();
        System.out.println(b.winner());
	}

	public Board(boolean shouldBeEmpty) {
		int N = 8;
		pieces = new Piece[N][N];
		selectedX = -1;
		selectedY = -1;
		moved = false;
        capturing = false;
		turn = 0;
		/* Creating the initial setup for the game */
		if (!shouldBeEmpty) {
			for (int i = 0; i < N; i += 1) {
				for (int j = 0; j < N; j += 1) {
					if (i % 2 == 0) {
						if (j == 0) {
							pieces[i][j] = new Piece(true,  this, i, j, "pawn");
						}
						else if (j == 2) {
							pieces[i][j] = new Piece(true, this, i, j, "bomb");
						}
						else if (j == 6) {
							pieces[i][j] = new Piece(false, this, i, j, "shield");
						}
					}
					else {
						if (j == 1) {
							pieces[i][j] = new Piece(true, this, i, j, "shield");
						}
						else if (j == 5) {
							pieces[i][j] = new Piece(false, this, i, j, "bomb");
						}
						else if (j == 7) {
							pieces[i][j] = new Piece(false, this, i, j, "pawn");
						}
					}
				}
			}
		}
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (selected != null && selectedX == i && selectedY == j) {
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                Piece current = pieces[i][j];
                if (current != null) {
                	if (current.isFire()) {
                		if (current.isBomb()) {
                			if (current.isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			}
                			else {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
		                	}
	                	}
	                	else if (current.isShield()) {
	                		if (current.isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	                		}
	                		else {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
		                	}
	                	}
	                	else {
	                		if (current.isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	                		}
	                		else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	                		}
	                	} 
                	}
                	else {
                		if (current.isBomb()) {
                			if (current.isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			}
                			else {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
		                	}
	                	}
	                	else if (current.isShield()) {
	                		if (current.isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	                		}
	                		else {
		                		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
		                	}
	                	}
	                	else {
	                		if (current.isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	                		}
	                		else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                		}
	                	}
                	}
                }
            }
        }
    }

	public Piece pieceAt(int x, int y) {
		int N = 8;
		if (x >= N || y >= N || x < 0 || y < 0) {
			return null;
		}
		return pieces[x][y];
	}

	public void place(Piece p, int x, int y) {
		int N = 8;
		if ((0 <= x && x < N && 0 <= y && y < N)) {
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		int N = 8;
		if (x < 0 || y < 0 || x >= N || y >= N) {
			System.out.println("Coordinate (x, y) is out of bounds of board.");
			return null;
		}
		else if (pieces[x][y] == null) {
			System.out.println("There is no piece here to remove.");
			return null;
		}
		else {
			Piece pHere = pieces[x][y];
			pieces[x][y] = null;
			return pHere;
		}
	}

	public String winner() {
		int N = 8;
		int fireCount = 0;
		int waterCount = 0;
		for (int i = 0; i < N; i += 1) {
			for (int j = 0; j < N; j += 1) {
				if (pieceAt(i, j) != null) {
					if (pieceAt(i, j).isFire()) {
						fireCount += 1;
					}
					else {
						waterCount += 1;
					}
				}
			}
		}
		if (fireCount == 0) {
			if (waterCount == 0) {
				return "No one";
			}
			return "Water";
		}
		else if (waterCount == 0) {
			return "Fire";
		}
		else {
			return null;
		}
	}

	/* Ensures pieces don't try to move backward unless they are kings
	   @param int distance is 1 or 2, a simple move or a capture */
	private boolean checkLegalMoveDirection(int xi, int yi, int xf, int yf, int distance) {
		if (pieceAt(xi, yi).isKing()) {
			return true;
		}
		/* Ensures uncrowned pieces don't try to move backward */
		else if (yf - yi == distance && pieceAt(xi, yi).isFire()) {
			return true;
		}
		else if (yi - yf == distance && !pieceAt(xi, yi).isFire()) {
			return true;
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		/* If trying to move to a place with a piece in it, this is invalid; skip this if statement */
		if (pieceAt(xf, yf) == null) {
			/* Trying to move to a diagonal adjacent space without capturing
			   First condition ensures piece can't move without capturing after
			   capturing */
			if (!capturing && !moved && Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1) {		
				return checkLegalMoveDirection(xi, yi, xf, yf, 1);
			}
			/* Normal captures */
			else if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2 && pieceAt(((xi + xf) / 2), (yi + yf) / 2) != null) {
				/* Ensures player doesn't attempt to jump over its own piece with selected piece */
				if (pieceAt(((xi + xf) / 2), (yi + yf) / 2).isFire() != pieceAt(xi, yi).isFire()) {
					/* First capture [select piece, then empty space] OR Multiple captures [current
					   selected space and desired space are empty spaces] */
					if (!moved || pieceAt(xi, yi).hasCaptured()) {
						return checkLegalMoveDirection(xi, yi, xf, yf, 2);
					}
				}
			}
		}
		return false;
	}

	public boolean canSelect(int x, int y) {
		/* Attempting to select an empty space to move to after selecting a piece */
		if (pieceAt(x, y) == null && selected != null && selected.side() == turn) {
			if (validMove(selectedX, selectedY, x, y)) {
				return true;
			}
		}
		/* Attempting to select a piece to move */
		else if (pieceAt(x, y) != null && !moved && pieceAt(x, y).side() == turn) {
			return true;
		} 
		return false;
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			selectedX = x;
	        selectedY = y;
		    selected = pieces[selectedX][selectedY];
	   	}
	   	else {
	   		selected.move(x, y);
	   		moved = true;
	   		selectedX = x;
            selectedY = y;
            selected = pieceAt(selectedX, selectedY);
	   	}
	}

	public boolean canEndTurn() {
		return capturing || moved;
	}

	public void endTurn() {
		if (selected != null) {
		    selected.doneCapturing();
		}
		turn = 1 - turn;
		selected = null;
		selectedX = -1;
		selectedY = -1;
		moved = false;
		capturing = false;
		
	}



}