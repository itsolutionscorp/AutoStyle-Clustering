public class Board {

	private Piece[][] pieces;
	private Piece selectedPiece;
	private boolean player1turn;
	private int selectedX;
	private int selectedY;
	private boolean hasMoved;
	private boolean mustEnd;
	private boolean captured;

	public static void main (String[] args) {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board b = new Board(false);
		while (true) {
			b.drawBoard(8);

			if (StdDrawPlus.mousePressed()) {
                double x1 = StdDrawPlus.mouseX();
                double y1 = StdDrawPlus.mouseY();
                int x = (int) x1;
                int y = (int) y1;

                if (b.canSelect(x, y)) {
                	b.select(x, y);
                }
            }

            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }
            
            b.winner();

			StdDrawPlus.show(100);
		}
	}

	public Board(boolean shouldBeEmpty) {
		int N = 8;
        pieces = new Piece[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (!shouldBeEmpty) {
                	if (j == 0 && i % 2 == 0) {
	                	pieces[i][j] = new Piece(true, this, i, j, "pawn");
	                }
	                if (j == 1 && i % 2 == 1) {
	                	pieces[i][j] = new Piece(true, this, i, j, "shield");
	                }
	                if (j == 2 && i % 2 == 0) {
	                	pieces[i][j] = new Piece(true, this, i, j, "bomb");
	                }
	                if (j == 7 && i % 2 == 1) {
	                	pieces[i][j] = new Piece(false, this, i, j, "pawn");
	                }
	                if (j == 6 && i % 2 == 0) {
	                	pieces[i][j] = new Piece(false, this, i, j, "shield");
	                }
	                if (j == 5 && i % 2 == 1) {
	                	pieces[i][j] = new Piece(false, this, i, j, "bomb");
	                }
                }
            }
        }
        
        player1turn = true;
        hasMoved = false;

	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                Piece current = pieces[i][j];
                if (current != null) {
                	if (current.isFire()) {

                		if (current.isKing()) {
                			if (current.isBomb()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	                		}

	                		else if (current.isShield()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	                		}

	                		else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	                		}
                		}

                		else {
                			if (current.isBomb()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	                		}

	                		else if (current.isShield()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	                		}

	                		else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	                		}
                		}

                	}

                	else {

                		if (current.isKing()) {
                			if (current.isBomb()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	                		}

	                		else if (current.isShield()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	                		}

	                		else {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	                		}
                		}

                		else {
                			if (current.isBomb()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                		}

	                		else if (current.isShield()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
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
		if (inBound(x, y)) {
			Piece current = pieces[x][y];
			return current;
		}
		else {
			return null;
		}
	}

	public boolean canSelect(int x, int y) {
		Piece current = pieces[x][y];

		if (mustEnd) {
			return false;
		}

		if (current == null) {
			if (selectedPiece != null) {

				if ((!hasMoved) && validMove(selectedX, selectedY, x, y)) {
					return true;
				}
				else if (captured) {
					if (validMove(selectedX, selectedY, x, y)) {
						return true;
					}
				}
			}
			return false;
		}

		else if ((player1turn && current.isFire()) || (!player1turn && !current.isFire())) {
			if (selectedPiece != null) {
				if (!hasMoved) {
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (pieceAt(xf, yf) != null) {
			return false;
		}

		Piece current = pieces[xi][yi];
		Piece middle = pieces[(xi + xf)/2][(yi + yf)/2];

		if (current != null) {
			if (current.isKing()) {
				if (((xf == xi - 1) || (xf == xi + 1)) && ((yf == yi + 1) || (yf == yi - 1))) {
					mustEnd = true;
					return true;
				}
				else if (((xf == xi - 2) || (xf == xi + 2)) && ((yf == yi + 2) || (yf == yi - 2)) && (middle != null) && (middle.isFire() != current.isFire())) {
					return true;
				}
			}

			else if (current.isFire()) {
				if (((xf == xi - 1) || (xf == xi + 1)) && (yf == yi + 1)) {
					mustEnd = true;
					return true;
				}
				else if (((xf == xi - 2) || (xf == xi + 2)) && (yf == yi + 2) && (middle != null) && (middle.isFire() != current.isFire())) {
					return true;
				}
			}

			else {
				if (((xf == xi - 1) || (xf == xi + 1)) && (yf == yi - 1)) {
					mustEnd = true;
					return true;
				}
				else if (((xf == xi - 2) || (xf == xi + 2)) && (yf == yi - 2) && (middle != null) && (middle.isFire() != current.isFire())) {
					return true;
				}
			}
		}

		return false;

	}

	private boolean inBound(int x, int y) {
		if ((x >= 0) && (x <= 7) && (y >= 0) && (y <=7)) {
			return true;
		}
		return false;
	}

	public void select(int x, int y) {
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);

		if (pieceAt(x, y) == null) {
			selectedPiece.move(x, y);
			hasMoved = true;

			if (selectedPiece.hasCaptured() && canMultiCapture(x, y)) {
				captured = true;
				selectedPiece = pieceAt(x, y);
				selectedX = x;
				selectedY = y;
			}
		}

		else {
			selectedPiece = pieceAt(x, y);
			selectedX = x;
			selectedY = y;
		}	
	}

	private boolean canMultiCapture(int x, int y) {
		if (inBound(x - 1, y - 1) && (pieceAt(x - 1, y - 1) != null) && inBound(x - 2, y - 2) && validMove(x, y, x - 2, y - 2)) {
			return true;
		}
		if (inBound(x - 1, y + 1) && (pieceAt(x - 1, y + 1) != null) && inBound(x - 2, y + 2) && validMove(x, y, x - 2, y + 2)) {
			return true;
		}
		if (inBound(x + 1, y - 1) && (pieceAt(x + 1, y - 1) != null) && inBound(x + 2, y - 2) && validMove(x, y, x + 2, y - 2)) {
			return true;
		}
		if (inBound(x + 1, y + 1) && (pieceAt(x + 1, y + 1) != null) && inBound(x + 2, y + 2) && validMove(x, y, x + 2, y + 2)) {
			return true;
		}
		return false;
	}

	public void place(Piece p, int x, int y) {
		if (inBound(x, y)) {
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (inBound(x, y)) {
			Piece current = pieceAt(x, y);
			if (current == null) {
				System.out.println("No piece to remove");
				return null;
			}
			pieces[x][y] = null;
			return current;
		}
		else {
			System.out.println("Tried to remove something that was out of bounds");
			return null;
		}
	}

	public boolean canEndTurn() {
		return hasMoved;
	}

	public void endTurn() {
		if (player1turn) {
			player1turn = false;
		}
		else {
			player1turn = true;
		}
		selectedPiece.doneCapturing();
		selectedPiece = null;
		hasMoved = false;
		mustEnd = false;
	}

	public String winner() {
		int numFire = 0;
		int numWater = 0;

		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if (pieces[i][j] != null) {
            		if (pieces[i][j].isFire()) {
            			numFire += 1;
            		}
            		else {
            			numWater += 1;
            		}
            	}
            }
        }


		if ((numFire == 0) || (numWater == 0)) {
			if (numFire == numWater) {
				return "No one";
			}
			else {
				if (numFire == 0) {
					return "Water";
				}
				else {
					return "Fire";
				}
			}
		}
		return null;
	}

}