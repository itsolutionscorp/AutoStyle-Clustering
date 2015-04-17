public class Board {

	/** Location of pieces. */
    private Piece[][] pieces;
    private static final int N = 8;
    private boolean fireTurn, madeMove, testing;
    private Piece selectedPiece;
    private int xSelected, ySelected, fireCount, waterCount;

	public static void main (String [] args) {
		Board board = new Board(false);

		if (!board.testing) {
			while(true) {
	            board.drawBoard(board.N);
	            if (StdDrawPlus.mousePressed()) {
	                int x = (int) StdDrawPlus.mouseX();
	                int y = (int) StdDrawPlus.mouseY();
	                if (board.canSelect(x, y) || board.testing) {
	                	board.select(x, y);
	                }
	            }
	            if (StdDrawPlus.isSpacePressed()) {
	            	board.endTurn();
	            	if (board.winner() != null)
	            		break;
	            }            
	            StdDrawPlus.show(20);
	        }

	        System.out.println(board.winner());
		}
	}

	public Board(boolean shouldBeEmpty) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        pieces = new Piece[N][N];
        fireTurn = true;
        xSelected = -1;
        ySelected = -1;

        fireCount = 0;
        waterCount = 0;
        testing = shouldBeEmpty;

        if (!shouldBeEmpty) {
        	fireCount = 12;
        	waterCount = 12;
        	for (int i = 0; i < N; i++) {
	        	for (int j = 0; j < N; j++) {
	        		if ((i + j) % 2 == 0) {
	        			if (j == 0) pieces[i][j] = new Piece(true, this, i, j, "pawn");
	        			if (j == 1) pieces[i][j] = new Piece(true, this, i, j, "shield");
	        			if (j == 2) pieces[i][j] = new Piece(true, this, i, j, "bomb");
	        			if (j == 5) pieces[i][j] = new Piece(false, this, i, j, "bomb");
	        			if (j == 6) pieces[i][j] = new Piece(false, this, i, j, "shield");
	        			if (j == 7) pieces[i][j] = new Piece(false, this, i, j, "pawn");
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
                if (xSelected == i && ySelected == j) StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                Piece piece = pieces[i][j];
                if (piece != null) {
                	String img_path = "img/";
                	if (piece.isShield()) {
                		img_path += "shield";
                	} else if (piece.isBomb()) {
                		img_path += "bomb";
                	} else {
                		img_path += "pawn";
                	}

                	if (piece.isFire()) {
                		img_path += "-fire";
                	} else {
                		img_path += "-water";
                	}

                	if (piece.isKing()) {
                		img_path += "-crowned";
                	}

                	img_path += ".png";

                    StdDrawPlus.picture(i + .5, j + .5, img_path, 1, 1);
                }
            }
        }
    }

	public Piece pieceAt(int x, int y) {
		if (x > N - 1 || y > N - 1)
			return null;

		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		if (x > N - 1 || y > N - 1)
			return false;

		if (selectedPiece == null) {
			// select a valid piece
			Piece piece = pieces[x][y];
			return piece != null && piece.isFire() == fireTurn;
		} else {
			// select a valid piece if not moved yet
			if (madeMove) {
				if (selectedPiece.hasCaptured() && !selectedPiece.isBomb())
					return validMove(xSelected, ySelected, x, y, true);
				return false;
			} else {
				// select a valid place
				Piece piece = pieces[x][y];
				if (piece == null) {
					return validMove(xSelected, ySelected, x, y, false);
				}
				return piece.isFire() == fireTurn;
			}
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf, boolean multi) {
		if (xf > N - 1 || yf > N - 1)
			return false;

		int n = fireTurn ? 1 : -1;

		if ((xi + n == xf || xi - n == xf) && yi + n == yf && !multi) {
			Piece piece = pieces[xf][yf];
			return piece == null;
		}

		if ((xi + (n * 2) == xf || xi - (n * 2) == xf) && yi + (n * 2) == yf) {
			Piece piece = pieces[xf][yf];
			Piece between = null;
			if (xi + (n * 2) == xf) {
				if (xi + n > N - 1 || yi + n > N - 1)
					return false;
				between = pieces[xi + n][yi + n];
			} else {
				if (xi - n > N - 1 || yi + n > N - 1)
					return false;
				between = pieces[xi - n][yi + n];
			}
			return piece == null && between != null && between.isFire() != fireTurn;
		}

		Piece p = pieces[xi][yi];
		if (p == null)
			return false;


		// Ability to move backwards
		if (p.isKing()) {
			n = -n;
			// Same exact code as above just n is flipped
			if ((xi + n == xf || xi - n == xf) && yi + n == yf && !multi) {
				Piece piece = pieces[xf][yf];
				return piece == null;
			}

			if ((xi + (n * 2) == xf || xi - (n * 2) == xf) && yi + (n * 2) == yf) {
				Piece piece = pieces[xf][yf];
				Piece between = null;
				if (xi + (n * 2) == xf) {
					if (xi + n > N - 1 || yi + n > N - 1)
						return false;
					between = pieces[xi + n][yi + n];
				} else {
					if (xi - n > N - 1 || yi + n > N - 1)
						return false;
					between = pieces[xi - n][yi + n];
				}
				return piece == null && between != null && between.isFire() != fireTurn;
			}
		}

		return false;
	}

	public void select(int x, int y) {
		xSelected = x;
		ySelected = y;
		Piece piece = pieces[x][y];
		if (piece != null) {
			selectedPiece = piece;
		} else {
			selectedPiece.move(x, y);
			if (pieces[x][y] == null) {
				xSelected = -1;
				ySelected = -1;
			}
			madeMove = true;
		}
	}

	public void place(Piece p, int x, int y) {
		if (x > N - 1 || y > N - 1)
			return;

		if (p.isFire())
			fireCount++;
		else
			waterCount++;

		pieces[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if (x > N - 1 || y > N - 1) {
			System.out.println("X and Y coordinates are out of bounds.");
			return null;
		}

		Piece piece = pieces[x][y];

		if (piece == null) {
			System.out.println("X and Y coordinates given do not contain a piece.");
			return null;
		}

		if (piece.isFire())
			fireCount--;
		else
			waterCount--;

		pieces[x][y] = null;

		return piece;
	}

	public boolean canEndTurn() {
		return madeMove;
	}

	public void endTurn() {
		if (canEndTurn()) {
			if (selectedPiece != null)
				selectedPiece.doneCapturing();
			fireTurn = !fireTurn;
			madeMove = false;
			selectedPiece = null;
			xSelected = -1;
			ySelected = -1;
		}
	}

	public String winner() {
		if (waterCount == 0 && fireCount == 0)
			return "No one";
		if (waterCount == 0)
			return "Fire";
		if (fireCount == 0)
			return "Water";
		return null;
	}

}