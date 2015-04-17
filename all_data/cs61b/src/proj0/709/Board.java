public class Board {

	private static final int N = 8;
	private Piece pieces[][];
	private boolean isFireTurn;
	private Piece selectedPiece;
	private int selectedX;
	private int selectedY;
	private boolean didMove;

	public static void main(String[] args) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        Board b = new Board(false);
        while(b.winner() == null) {
        	b.drawBoard();
        	b.drawPieces();
        	if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) {
                	b.select(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }
            if (StdDrawPlus.isNPressed()) {
            	b = new Board(false);
            }
        	StdDrawPlus.show(50);
        }
        b.drawBoard();
        b.drawPieces();
        StdDrawPlus.show(50);
	}

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[N][N];
		isFireTurn = true;
		didMove = false;
		selectedX = -1;
		selectedY = -1;
		if (!shouldBeEmpty) {
			setUpStandardBoard();
		}
	}

	private void setUpStandardBoard() {
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				switch(j) {
					case 0:
						if (i % 2 == 0) pieces[i][j] = new Piece(true, this, i, j, "pawn");
						break;
					case 1:
						if (i % 2 == 1) pieces[i][j] = new Piece(true, this, i, j, "shield");
						break;
					case 2:
						if (i % 2 == 0) pieces[i][j] = new Piece(true, this, i, j, "bomb");
						break;
					case 5:
						if (i % 2 == 1) pieces[i][j] = new Piece(false, this, i, j, "bomb");
						break;
					case 6:
						if (i % 2 == 0) pieces[i][j] = new Piece(false, this, i, j, "shield");
						break;
					case 7:
						if (i % 2 == 1) pieces[i][j] = new Piece(false, this, i, j, "pawn");
						break;
					default:

				}
			}
		}
	}

	private void drawBoard() {
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (i == selectedX && j == selectedY) 	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) 				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  					StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
	}

	private void drawPieces() {
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Piece p = pieces[i][j];
				if (p != null) {
					String imgName = getPieceImageName(p);
                    StdDrawPlus.picture(i + .5, j + .5, imgName, 1, 1);
				}
			}
		}
	}

	private String getPieceImageName(Piece p) {
		String str = "img/";
		if (p.isBomb()) {
			str += "bomb";
		} else if (p.isShield()) {
			str += "shield";
		} else {
			str += "pawn";
		}
		if (p.isFire()) {
			str += "-fire";
		} else {
			str += "-water";
		}
		if (p.isKing()) {
			str += "-crowned";
		}
		str += ".png";
		return str;
	}

	public Piece pieceAt(int x, int y) {
		if (inBounds(x, y)) {
			return pieces[x][y];
		}
		return null;
	}

	private boolean inBounds(int x, int y) {
		return x >= 0 && y >= 0 && x < pieces.length && y < pieces.length;
	}

	public boolean canSelect(int x, int y) {
		Piece p = pieces[x][y];
		if (p != null) {
			return p.isFire() == isFireTurn && (selectedPiece == null || !didMove);
		} else {
			boolean canMove = selectedPiece != null && (!didMove || (selectedPiece.hasCaptured() && !selectedPiece.isBomb()));
			return canMove && validMove(selectedX, selectedY, x, y);
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece start = pieceAt(xi, yi);
		Piece end = pieceAt(xf, yf);
		boolean isAdjacent = isNDistanceDiagonal(xi, yi, xf, yf, 1);
		boolean isHop = isNDistanceDiagonal(xi, yi, xf, yf, 2);

		if (start == null || !inBounds(xf, yf) || end != null) {
			return false;
		}

		if (isAdjacent) {
			return checkVerticalDirection(start, yi, yf);
		} else if (isHop) {
			Piece middle = pieceAt((xi + xf) / 2, (yi + yf) / 2);
			return middle != null && checkVerticalDirection(start, yi, yf) && start.isFire() != middle.isFire();
		} else {
			return false;
		}
	}

	private boolean checkVerticalDirection(Piece p, int yi, int yf) {
		if (p.isKing()) {
			return true;
		} else if (p.isFire()) {
			return yf > yi;
		}
		return yf < yi;
	}

	private boolean isNDistanceDiagonal(int xi, int yi, int xf, int yf, int n) {
		return Math.abs(xi - xf) == n && Math.abs(yi - yf) == n;
	}

	public void select(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p != null) {
			selectedPiece = p;
			selectedX = x;
			selectedY = y;
		} else {
			didMove = true;
			selectedPiece.move(x, y); // selectedPiece can't be null here because of canSelect and p != null;
			selectedX = x;
			selectedY = y;
		}
	}

	public void place(Piece p, int x, int y) {
		if (inBounds(x, y)) pieces[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if (!inBounds(x, y)) {
			System.out.println("Input is out of bounds");
			return null;
		}
		Piece p = pieceAt(x, y);
		pieces[x][y] = null;
		if (p == null) {
			System.out.println("There is no piece there");
		}
		return p;
	}

	public boolean canEndTurn() {
		return selectedPiece != null && didMove;
	}

	public void endTurn() {
		isFireTurn = !isFireTurn;
		selectedPiece.doneCapturing();
		selectedPiece = null;
		selectedX = -1;
		selectedY = -1;
		didMove = false;
	}

	public String winner() {
		int water = 0;
		int fire = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Piece p = pieceAt(i, j);
				if (p != null) {
					if (p.isFire()) {
						fire++;
					} else {
						water++;
					}
				}
			}
		}

		if (water == 0 && fire == 0) {
			return "No one";
		} else if (water == 0) {
			return "fire";
		} else if (fire == 0) {
			return "water";
		}
		return null;
	}
}