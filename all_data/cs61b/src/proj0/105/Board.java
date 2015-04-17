public class Board {
	
	private Piece[][] pieces;
	private boolean moved;
	private boolean isFireTurn;
	private Piece selected;
	private int selectedx;
	private int selectedy;
	/*you should probably revert these lines (and some others) to private
	after finishing testing */

	
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		isFireTurn = true;
		moved = false;
		selected = null;
		selectedx = -1;
		selectedy = -1;
		if (!shouldBeEmpty) {
			for(int i = 0; i < 8; i = i + 2) {
				place(new Piece(true, this, i, 0, "pawn"), i, 0);
			}
			for(int i = 1; i < 8; i = i + 2) {
				place(new Piece(true, this, i, 1, "shield"), i, 1);
			}
			for(int i = 0; i < 8; i = i + 2) {
				place(new Piece(true, this, i, 2, "bomb"), i, 2);
			}
			for(int i = 1; i < 8; i = i + 2) {
				place(new Piece(false, this, i, 5, "bomb"), i, 5);
			}
			for(int i = 0; i < 8; i = i + 2) {
				place(new Piece(false, this, i, 6, "shield"), i, 6);
			}
			for(int i = 1; i < 8; i = i + 2) {
				place(new Piece(false, this, i, 7, "pawn"), i, 7);
			}
		}

	}

	public Piece pieceAt(int x, int y) {	
		boolean xInBounds = (x >= 0) && (x < pieces.length); /* from http://stackoverflow.com/questions/19672301/checking-out-of-bounds-in-java */
		if (xInBounds) {
			boolean yInBounds = (y >= 0) && (y < pieces[x].length);
			if (yInBounds) {
				return pieces[x][y];
			}
		}
		return null;
	}

	public boolean canSelect(int x, int y) {
		Piece piece = pieceAt(x, y);
		if ((piece != null) && (((isFireTurn && piece.isFire()) || (!isFireTurn && !piece.isFire()))) && moved == false) {
			return true;
		}
		if ((piece == null) && (selected != null) && (validMove(selectedx, selectedy, x, y))) {
			return true;
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece piece = pieceAt(xi, yi);
		Piece destination = pieceAt(xf, yf);
		if ((piece != null) && (destination == null)) {
			if (((yf == yi + 1) && ((xf == xi + 1) || (xf == xi - 1))) 
			&& ((piece.isKing()) || piece.isFire()) && (moved == false)) {
				return true;
			}
			if (((yf == yi - 1) && ((xf == xi + 1) || (xf == xi - 1))) 
			&& ((piece.isKing()) || !piece.isFire()) && (moved == false)) {
				return true;
			}
			if (((yf == yi + 2) && (xf == xi + 2)) && ((piece.isKing()) || piece.isFire()) && (piece.hasCaptured() || moved == false)) {
				Piece jumped = pieceAt(xi+1, yi+1);
				if ((jumped != null) && (jumped.isFire() != piece.isFire())) {
					return true;
				}
			}
			if (((yf == yi + 2) && (xf == xi - 2)) && ((piece.isKing()) || piece.isFire()) && (piece.hasCaptured() || moved == false)) {
				Piece jumped = pieceAt(xi-1, yi+1);
				if ((jumped != null) && (jumped.isFire() != piece.isFire())) {
					return true;
				}
			}
			if (((yf == yi - 2) && (xf == xi + 2)) && ((piece.isKing()) || !piece.isFire()) && (piece.hasCaptured() || moved == false)) {
				Piece jumped = pieceAt(xi+1, yi-1);
				if ((jumped != null) && (jumped.isFire() != piece.isFire())) {
					return true;
				}
			}
			if (((yf == yi - 2) && (xf == xi - 2)) && ((piece.isKing()) || !piece.isFire()) && (piece.hasCaptured() || moved == false)) {
				Piece jumped = pieceAt(xi-1, yi-1);
				if ((jumped != null) && (jumped.isFire() != piece.isFire())) {
					return true;
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) == null) {
			selected.move(x, y);
			moved = true;
		}
		selected = pieceAt(x, y);
		selectedx = x;
		selectedy = y;


	}

	public void place(Piece p, int x, int y) {
		boolean xInBounds = (x >= 0) && (x < pieces.length); /* from http://stackoverflow.com/questions/19672301/checking-out-of-bounds-in-java */
		if (xInBounds) {
			boolean yInBounds = (y >= 0) && (y < pieces[x].length);
			if (yInBounds) {
				pieces[x][y] = p;
			}
		}
	}

	public Piece remove(int x, int y) {
		boolean xInBounds = (x >= 0) && (x < pieces.length); /* from http://stackoverflow.com/questions/19672301/checking-out-of-bounds-in-java */
		if (xInBounds) {
			boolean yInBounds = (y >= 0) && (y < pieces[x].length);
			if (yInBounds) {
				Piece removed = pieces[x][y];
				pieces[x][y] = null;
				return removed;
			}
		}
		return null;

	}

	public boolean canEndTurn() {
		if (moved || ((selected != null) && (selected.hasCaptured()))) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		if (selected != null) {
            selected.doneCapturing();
        }
		moved = false;
		selected = null;
		selectedx = -1;
		selectedy = -1;
		isFireTurn = !isFireTurn;

	}

	public String winner() {
		boolean foundfire = false;
		boolean foundwater = false;
		for (int i = 0; (i < 8) && ((foundfire == false) || (foundwater == false)); i += 1) {
			for (int j = 0; (j < 8) && ((foundfire == false) || (foundwater == false)); j += 1) {
				if ((pieceAt(i, j) != null) && (pieceAt(i, j).isFire() == true)) {
					foundfire = true;
				}
				if ((pieceAt(i, j) != null) && (pieceAt(i, j).isFire() == false)) {
					foundwater = true;
				}
			}
		}
		if (foundfire && foundwater) {
			return null;
		}
		if (foundfire) {
			return "Fire";
		}
		if (foundwater) {
			return "Water";
		}
		return "No one";
	}

	private void drawBoard(int n) {
		for (int i = 0; i < n; i += 1) {
			for (int j = 0; j < n; j += 1) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (pieces[i][j] != null) {
					String type;
					String team;
					String king;
					if (pieces[i][j].isBomb()) {
						type = "bomb";
					} else if (pieces[i][j].isShield()) {
						type = "shield";
					} else {
						type = "pawn";
					}
					if (pieces[i][j].isFire()) {
						team = "fire";
					} else {
						team = "water";
					}
					if (pieces[i][j].isKing()) {
						king = "-crowned";
					} else {
						king = "";
					}
					StdDrawPlus.picture(i + .5, j + .5, "img/" + type + "-" + team + king + ".png", 1, 1);
				}
			}
		}
	}




	public static void main(String[] args) {
		int n = 8;
		StdDrawPlus.setYscale(0, n);
		StdDrawPlus.setXscale(0, n);
		Board board = new Board(false);
		board.drawBoard(n);
		String win = board.winner();
		while(win == null) {
			if (StdDrawPlus.mousePressed()) {
				double xd = StdDrawPlus.mouseX();
                double yd = StdDrawPlus.mouseY();
                int x = (int) xd;
                int y = (int) yd;
                if (board.canSelect(x, y)) { 
                	board.select(x, y);
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	StdDrawPlus.filledSquare(x + .5, y + .5, .5);	
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (board.canEndTurn()) {
            		win = board.winner();
            		board.endTurn();
            	}
            }
			StdDrawPlus.show(15);
			board.drawBoard(n);
		}
		System.out.println("The winner is " + win);
	}
}