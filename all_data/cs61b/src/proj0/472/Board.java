public class Board {
	private boolean shouldBeEmpty;
	private static final int N = 8;
	private Piece[][] pieces = new Piece[N][N];
	private int currentPlayer = 0; //currentPlayer is 0 if "fire", else "water"
	private boolean hasSelected = false;
	private Piece selected;
	private boolean hasMoved;
	private int currentX;
	private int currentY;
	private boolean captured;
	private int lastMove = 0;

	public Board(boolean shouldBeEmpty) {
		this.shouldBeEmpty = shouldBeEmpty;
    	if (!this.shouldBeEmpty) {
			this.initializePieces(N);
		}
	}

	//Draw methods
	private void drawBoard(int N) {
		StdDrawPlus.setScale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                drawFilledSquare(i, j);
                StdDrawPlus.filledSquare(i + 0.5, j + 0.5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (this.pieces[i][j] != null) {
                	drawPiece(i, j, getPath(pieces[i][j]));
                }
        	}
		}
	}

	private void drawPiece(int x, int y, String img_path) {
		StdDrawPlus.picture(x + 0.5, y + 0.5, img_path, 1, 1);
	}

	private void drawFilledSquare(int x, int y) {
		if ((x + y) % 2 == 0) {
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		} else {
			StdDrawPlus.setPenColor(StdDrawPlus.RED);
		}
		StdDrawPlus.filledSquare(x + 0.5, y + 0.5, .5);
	}

	public Piece pieceAt(int x, int y) {
		if ((x < N) && (y < N) && (x >= 0) && (y >= 0)) {
			return pieces[x][y];
		}
		return null;
	}

	public boolean canSelect(int x, int y) {
		Piece currentPiece = this.pieceAt(x, y);
		if ((x + y) % 2 != 0) {
			return false;
		} 

		if (this.hasMoved && (lastMove == 1)) {
			return false;
		}

		if (captured && currentPiece == null && this.validMove(currentX, currentY, x, y)) {
			return true;
		} else if (captured) {
			return false;
		}
		if ((currentPiece != null) && (currentPlayer == currentPiece.side())) {
			if (!this.hasSelected || (this.hasSelected && (!this.hasMoved))) {
				return true;
			}
		}
		else if (currentPiece == null) {
			if (this.hasSelected && !this.hasMoved && this.validMove(currentX, currentY, x, y)) {
				return true;
			} else if (hasSelected && this.pieceAt(currentX, currentY).hasCaptured() && this.validMove(currentX, currentY, x, y) && Math.abs(currentX - x) == 2 && Math.abs(currentY - y) == 2) {
				return true;
			}
		} return false;
	}

	private boolean sameTeam(Piece p, Piece q) {
		return p.isFire() == q.isFire();
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece currentPiece = this.pieceAt(xi, yi);
		Piece captured = this.pieceAt((xf + xi)/2, (yf + yi)/2);

		//Just in case of NPE
		if ((currentPiece == null)) {
			return false;
		}

		int dx = xf - xi;
		int dy = yf - yi;

		if (this.captured) {
			if (currentPiece.isKing()) {
				if ((Math.abs(dx) == 2) && (Math.abs(dy) == 2) && (captured != null) && (currentPiece.isFire() != captured.isFire())) {
					return true;
				} return false;
			} else if (currentPiece.isFire()) {
				if ((Math.abs(dx) == 2) && (dy == 2) && (captured != null) && (currentPiece.isFire() != captured.isFire())) {
					return true;
				} return false;
			} else if (!currentPiece.isFire()) {
				if ((Math.abs(dx) == 2) && (dy == -2) && (captured != null) && (currentPiece.isFire() != captured.isFire())) {
					return true;
				} return false;
			}
		}

		//Checking out of bounds
		if ((xf < 0 || xf > 7 || yf < 0 || yf > 7) || (xf == xi && yf == yi)) {
			return false;
		}

		if (pieceAt(xf, yf) == null) {
			if (Math.abs(dx) == 2) {
				if (currentPiece.isKing()) {
					if(captured != null && !sameTeam(captured, currentPiece) && Math.abs(dy) == 2){
						return true;
					}
				} else if (currentPiece.isFire()) {
					if (dy == 2 && captured != null && !this.sameTeam(captured, currentPiece)) {
						return true;
					}
				} else if (!currentPiece.isFire()) {
					if (dy == -2 && captured != null && !this.sameTeam(captured, currentPiece)) {
						return true;
					}
				}
			} else if (Math.abs(dx) == 1) {
				if (currentPiece.isKing()) {
					if (Math.abs(dy) == 1) {
						return true;
					}
				} else if (currentPiece.isFire()) {
					if (dy == 1) {
						return true;
					}
				} else {
					if (dy == -1) {
						return true;
					}
				}
			} 
		} return false;
	}

	public void select(int x, int y) {
		if (this.pieceAt(x, y) != null) {
			selected = this.pieceAt(x, y);
			currentX = x;
			currentY = y;
			hasSelected = true;
		} else if (selected != null) {
			if (Math.abs(currentX-x) == 2) {
				lastMove = 2;
			} else if (Math.abs(currentX-x) == 1) {
				lastMove = 1;
			}
			hasMoved = true;
			hasSelected = false;
			currentX = x;
			currentY = y;
			selected.move(x, y);
			if (selected.hasCaptured()) {
				captured = true;
			}
		}
	}

	private String getPath(Piece p) {
		String img = "img/";

		if (p.isBomb()) {
			img += "bomb-";
		} else if (p.isShield()) {
			img += "shield-";
		} else {
			img += "pawn-";
		}

		if (p.isFire()) {
			img += "fire";
		} else {
			img += "water";
		}

		if (p.isKing()) {
			img += "-crowned";
		}

		img += ".png";
		return img;
	}

	public void place(Piece p, int x, int y) {
		if ((x < N) && (y < N) && (x >= 0) && (y >= 0)) {
			pieces[x][y] = p;
			String img = this.getPath(p);
			this.drawPiece(x, y, img);
		}
	}

	public Piece remove(int x, int y) {
		if ((x >= N) || (y >= N) || (x < 0) || (y < 0)) {
			System.out.println("Board index out of bounds.");
			return null;
		}

		Piece returnPiece = pieceAt(x, y);
		if (returnPiece == null) {
			System.out.println("No piece at given location.");
			return null;
		} 
		pieces[x][y] = null;
		this.drawFilledSquare(x, y);
		return returnPiece;
	}

	public boolean canEndTurn() {
		return hasMoved;
	}

	public void endTurn() {
		if (this.canEndTurn()) {
			selected.doneCapturing();
			this.hasSelected = false;
			this.selected = null;
			this.hasMoved = false;
			captured = false;
			currentX = -1;
			currentY = -1;
			currentPlayer = 1 - currentPlayer;
			lastMove = 0;
		}
	}

	public String winner() {
		int countWater = 0, countFire = 0;
		for (int i = 0; i < pieces.length; i++) {
			for (int j = 0; j < pieces[i].length; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) {
						countFire += 1;
					} else {
						countWater += 1;
					}
				}
			}
		}

		if ((countWater == 0) && (countFire == 0)) {
			return "No one";
		} else if (countWater == 0) {
			return "Fire";
		} else if (countFire == 0) {
			return "Water";
		} return null;
	}

	private void initializePieces(int N) {
		int i = 0, j = 0;
		while (i < this.N) {
			pieces[i][j] = new Piece(true, this, i, j, "pawn");
			pieces[i + 1][j + 1] = new Piece(true, this, i + 1, j + 1, "shield");
			pieces[i][j + 2] = new Piece(true, this, i, j + 2, "bomb");
			pieces[i + 1][this.N - j - 1] = new Piece(false, this, i + 1, this.N - j - 1, "pawn");
			pieces[i][this.N - j - 2] = new Piece(false, this, i, this.N - j - 2, "shield");
			pieces[i + 1][this.N - j - 3] = new Piece(false, this, i + 1, this.N - j - 3, "bomb");
			i += 2;
		}
	}

	public static void main(String[] args) {
		int N = 8, mousePosX = -1, mousePosY = -1;
		Piece prevPiece = null;
		Board b = new Board(false);
		b.drawBoard(N);
		while(b.winner() == null) {
			StdDrawPlus.show(25);
			if (StdDrawPlus.mousePressed()) {
				mousePosX = (int)(StdDrawPlus.mouseX());
				mousePosY = (int)(StdDrawPlus.mouseY());
				if (b.canSelect(mousePosX, mousePosY)) {
					b.select(mousePosX, mousePosY);
					prevPiece = b.selected;
				}
			}
			if(StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn()) {
					b.endTurn();
				}
			}
		}
		System.out.println(b.winner() + " is the winner!");
	}
}