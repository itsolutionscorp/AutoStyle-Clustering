public class Board {
	private Piece[][] pieces = new Piece[8][8];
	private static int FIRE = 0;
	private static int WATER = 1;
	private int player = FIRE;
	private Piece selected;
	private int numFire = 0;
	private int numWater = 0;
	private boolean moved = false;
	private static int N = 8;
	private boolean emptyBoard = false;
	private boolean bombCapture = false;

	private static boolean outOfBounds(int x, int y) {
		if ((x < 0) || (x > 7) || (y < 0) || (x > 7) || ((x + y)%2 != 0)) {
			System.out.println("out of bounds");
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		Board board = new Board(false);
		while(true) {
			board.drawBoard(N);
			if (board.winner() != null) {
				board.endTurn();
				board.drawBoard(N);
				StdDrawPlus.show(100);
				break;
			}
			if (StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				if (board.canSelect((int)x, (int)y)) {
					board.select((int)x, (int)y);
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (board.canEndTurn()) {
					board.endTurn();
				}
			}
			StdDrawPlus.show(100);
		}
	}

	private void drawBoard(int N) {
        	for (int i = 0; i < N; i++) {
            	for (int j = 0; j < N; j++) {
                	if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                	if (pieceAt(i,j) == null) continue;
                	Piece p = pieceAt(i,j);
                	if (selected != null) {
			        	if (selected == p) {
			        		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			        		StdDrawPlus.filledSquare(i +.5, j+.5, .5);
			        	}
			        }
                	if (p.isKing()) {
	                	if (p.isFire()) {
	                		if (p.isBomb()) StdDrawPlus.picture(i + .5, j +.5, "img/bomb-fire-crowned.png", 1, 1);
	                		else if (p.isShield()) StdDrawPlus.picture(i + .5, j +.5, "img/shield-fire-crowned.png", 1, 1);
	                		else StdDrawPlus.picture(i + .5, j +.5, "img/pawn-fire-crowned.png", 1, 1);
	                	}
	                	else {
	                		if (p.isBomb()) StdDrawPlus.picture(i + .5, j +.5, "img/bomb-water-crowned.png", 1, 1);
	                		else if (p.isShield()) StdDrawPlus.picture(i + .5, j +.5, "img/shield-water-crowned.png", 1, 1);
	                		else StdDrawPlus.picture(i + .5, j +.5, "img/pawn-water-crowned.png", 1, 1);
	                	}
					} else {
						if (p.isFire()) {
	                		if (p.isBomb()) StdDrawPlus.picture(i + .5, j +.5, "img/bomb-fire.png", 1, 1);
	                		else if (p.isShield()) StdDrawPlus.picture(i + .5, j +.5, "img/shield-fire.png", 1, 1);
	                		else StdDrawPlus.picture(i + .5, j +.5, "img/pawn-fire.png", 1, 1);
	                	}
	                	else {
	                		if (p.isBomb()) StdDrawPlus.picture(i + .5, j +.5, "img/bomb-water.png", 1, 1);
	                		else if (p.isShield()) StdDrawPlus.picture(i + .5, j +.5, "img/shield-water.png", 1, 1);
	                		else StdDrawPlus.picture(i + .5, j +.5, "img/pawn-water.png", 1, 1);
                		}
					}

            	}
        	}


    	}

    private boolean isGraySquare(int i, int j) {
    	return ((i + j)%2 == 0);
    }

	public Board(boolean shouldBeEmpty) {
		emptyBoard = shouldBeEmpty;
		if (shouldBeEmpty == false) {
			numFire = 12;
			numWater = 12;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (isGraySquare(i, j)) {
						if (j == 0 || j == 7) {
							pieces[i][j] = new Piece(j<4, this, i, j, "pawn");
						}
						if (j == 1 || j == 6) {
							pieces[i][j] = new Piece(j<4, this, i, j, "shield");
						}
						if (j == 2 || j == 5) {
							pieces[i][j] = new Piece(j<4, this, i, j, "bomb");
						}
					}
				}
			}
		}

	}


	public Piece pieceAt(int x, int y) {
		if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		if (bombCapture) {
			return false;
		}
		if (pieceAt(x, y) != null) {
			if (player == pieceAt(x,y).side()) {
				return !moved || selected == null;
			}
		}
		else {
			if (selected != null) {
				int[] selectedCoord = findPiece(selected);
				return validMove(selectedCoord[0], selectedCoord[1], x, y);
			}
		}
		return false;
	}

	public void select(int x, int y) {
			if (selected == null) {
				selected = pieceAt(x, y);
				return;
			}
			if (pieceAt(x, y) != null) {
				selected = pieceAt(x, y);
			} else {
				place(selected, x, y);
				selected.move(x, y);

				if (selected.isBomb() && selected.hasCaptured()) {
					for (int i = x-1; i < x+2; i += 2) {
						for (int j = y-1; j < y + 2; j += 2) {
							if (pieceAt(i, j) != null && !pieceAt(i, j).isShield()) {
								remove(i, j);
							}
						}
					}
					bombCapture = true;
					selected = null; 
					remove(x, y);
				}
				moved = true;
			}
	}

	public void place(Piece p, int x, int y) {
	 	if (p == null || outOfBounds(x, y)) {
	 		return;
	 	} 
	 	int[] position = findPiece(p);

	 	if (position != null) {
	 		pieces[x][y] = remove(position[0], position[1]);
	 	} else {
	 		pieces[x][y] = p;
	 	}

	 	if (p.isFire()) {
	 		numFire++;
	 	} else {
	 		numWater++;
	 	}
	 	emptyBoard = false;
	}

	public Piece remove(int x, int y) {
		if (outOfBounds(x, y)) {
			System.out.println("Input is out of bounds");
			return null; 
		}
		if (pieces[x][y] == null) {
			System.out.println("No piece at input");
			return null;
		}
		Piece removedPiece = pieceAt(x, y);
		if (removedPiece.isFire()) {
			numFire = numFire - 1;
		}  else {
			numWater = numWater - 1;
		}
		pieces[x][y] = null;
		return removedPiece;

	}

	public boolean canEndTurn() {
		if (bombCapture) {
			return true;
		}
		if (selected == null) {
			return moved;
		}
		return (moved || selected.hasCaptured());
	}

	public void endTurn() {
		player = (player + 1) % 2;
		if (selected != null) {
			selected.doneCapturing();
		}
		selected = null;
		moved = false;
		bombCapture = false;
	}

	public String winner() {
		if ((numFire == 0) && (numWater == 0) && !emptyBoard) {
			return "No one";
		}

		if (emptyBoard) {
			return "No one";
		}

		if (numWater == 0) {
			return "Fire";
		}
		if (numFire == 0) {
			return "Water";
		}
		return null;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (moved && !selected.hasCaptured()) return false;
		if (pieceAt(xf, yf) != null) return false;
		int dX = Math.abs(xf-xi);
		int dY = Math.abs(yf-yi);
		if (dX != dY || dX == 0 || dY == 0 || dX > 2 || dY >2) return false;
		if (selected.hasCaptured() && dY != 2) return false;
		if (selected.isFire()) {
			if (yf <= yi && !selected.isKing()) return false;
		} else {
			if (yf >= yi && !selected.isKing()) return false;
		}
		if (dX == 1) {
			return true;
		} else {
			Piece beingCaptured = pieceAt((int) ((xi+xf)/2), (int) ((yi+yf)/2));
			if (beingCaptured == null) return false;
			return beingCaptured.side() != player;
		}
	}

	private int[] findPiece(Piece p) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (pieceAt(i, j) == p) {
					return new int[] {i, j};
				}
			}
		}
		return null;
	}
}