import java.lang.Math;

public class Board {

	private Piece[][] boardPieces;

	private boolean firesTurn;
	private boolean madeMove;

	private int xSelected;
	private int ySelected;

	public static void main(String[] args) {
		Board board = new Board(false);
		board.initBoard();

		while(true) {
			board.drawBoard();
			if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                System.out.println((int) x + " " + (int) y);
                if (board.canSelect((int) x, (int) y)) {
                	board.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (board.canEndTurn()) {
            		board.endTurn();
            		System.out.println("Ended turn");
            	} else {
            		System.out.println("Can't end turn");
            	}
            }
            if (board.winner() != null) {
            	break;
            }
            StdDrawPlus.show(50);
		}
	}

	private void initBoard() {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
	}

	private void drawBoard() {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (boardPieces[i][j] != null) {
                	String img = "img/";
                	if (boardPieces[i][j].isShield()) {
                		img = img + "shield-";
                	} else if (boardPieces[i][j].isBomb()) {
                		img = img + "bomb-";
                	} else {
                		img = img + "pawn-";
                	}
                	if (boardPieces[i][j].isFire()) {
                		img = img + "fire";
                	} else {
                		img = img + "water";
                	}
                	if (boardPieces[i][j].isKing()) {
                		img = img + "-crowned";
                	}
                	img = img + ".png";
                    StdDrawPlus.picture(i + .5, j + .5, img, 1, 1);
                }
            }
        }
    }

	public Board(boolean shouldBeEmpty) {
		this.boardPieces = new Piece[8][8];
		
		if (!shouldBeEmpty) {
			String[] pieceTypes = {"pawn", "shield", "bomb"};
			boolean[] suitTypes = {true, false};

			for (int i = 0; i < 6; i++) {
				this.placeTypePiecesAtTypeIndexes(pieceTypes[i % 3], suitTypes[i / 3], i % 2,  Math.abs(i / 3 * 7 - i % 3));
			}
		}

		this.firesTurn = true;
		this.madeMove = false;
		this.xSelected = -1;
		this.ySelected = -1;
	}

	private void placeTypePiecesAtTypeIndexes(String piece, boolean suit, int startingIndex, int y) {
		int x = startingIndex;
		for (; x < 8; x += 2) {
			Piece p = new Piece(suit, this, x, y, piece);
			this.place(p, x, y);
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			return null;
		}
		return this.boardPieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return false;
		}
		if ((x + y) % 2 != 0) {
			return false;
		}
		Piece wantToSelect = this.pieceAt(x,y);
		if (wantToSelect == null) {
			if (xSelected < 0) {
				return false;
			} else if (this.validMove(xSelected, ySelected, x, y)) {
				return true;
			}
		} else if (wantToSelect.isFire() != this.firesTurn) {
			return false;
		} else if (!this.madeMove) {
			return true;
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xf - xi == 0 || yf - yi == 0) {
			return false;
		}
		if (Math.abs(xf - xi) != Math.abs(yf - yi)) {
			return false;
		}
		Piece selected = this.pieceAt(xi, yi);
		if (selected.isKing() || (selected.isFire() && yf > yi) || (!selected.isFire() && yf < yi)) {
			int dx = Math.abs(xf - xi);
			if (dx == 2) {
				Piece captured = this.pieceAt((xi + xf) / 2, (yi + yf) / 2);
				if (captured != null && selected.isFire() != captured.isFire() ||
					 selected.isKing() && captured == null && !this.madeMove ||
					 selected.isKing() && captured != null && selected.isFire() != captured.isFire()) {
					return true;
				}
				return false;
			} else if (dx == 1) {
				if (selected.hasCaptured() || this.madeMove) {
					return false;
				}
				return true;
			} else if (selected.isKing() && !selected.hasCaptured() && !this.madeMove) {
				int incX, incY;
				if (xf > xi) 
					incX = 1;
				else 
					incX = -1;
				if (yf > yi) 
					incY = 1;
				else 
					incY = -1;
				int x = xi + incX, y = yi + incY;
				for (int r = 0; r < Math.abs(xf - xi) - 1; r++) {
					if (this.pieceAt(x, y) != null) {
						return false;
					}
					x += incX;
					y += incY;
				}
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public void select(int x, int y) {
		// Implement special moves
		Piece selected = null;
		if (xSelected >= 0 && this.pieceAt(x,y) == null && (!this.madeMove || this.madeMove && this.pieceAt(xSelected, ySelected).hasCaptured())) {
			selected = this.remove(xSelected, ySelected);
			selected.move(x, y);
			this.madeMove = true;
		}
		if (selected != null && selected.isBomb() && selected.hasCaptured()) {
			xSelected = -1;
			ySelected = -1;
		} else {
			xSelected = x;
			ySelected = y;
		}

		// Select square via color
	}

	public void place(Piece p, int x, int y) {
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			this.boardPieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		Piece removed = pieceAt(x, y);
		this.place(null, x, y);
		return removed;
	}

	public boolean canEndTurn() {
		for (Piece[] rowPieces: this.boardPieces) {
			for (Piece p: rowPieces) {
				if (p != null && p.hasCaptured()) {
					return true;
				}
			}
		}
		return this.madeMove;
	}

	public void endTurn() {
		for (Piece[] rowPieces: this.boardPieces) {
			for (Piece p: rowPieces) {
				if (p != null && this.firesTurn == p.isFire()) {
					p.doneCapturing();
				}
			}
		}
		this.firesTurn = !this.firesTurn;
		this.madeMove = false;
		this.xSelected = -1;
		this.ySelected = -1;
	}

	public String winner() {
		int numFire = 0;
		int numWater = 0;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Piece p = this.pieceAt(x, y);
				if (p != null) {
					numFire += 1 - p.side();
					numWater += p.side();
				}
			}
		}
		if (numFire > 0 && numWater > 0) {
			return null;
		} else if (numFire > 0) {
			return "Fire";
		} else if (numWater > 0) {
			return "Water";
		} else {
			return "No one";
		}

	}
}