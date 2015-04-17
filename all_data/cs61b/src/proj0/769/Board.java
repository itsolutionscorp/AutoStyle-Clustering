public class Board {

	private static int N = 8;
	private Piece[][] pieces;
	private Piece selectedPiece = null;
	private int selectedX = N, selectedY =N;
	private boolean didMove;
	private int currentPlayer;

	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			pieces = new Piece[N][N];
			this.configPieces();
		} else {
			pieces = new Piece[N][N];
		}
	}

	private void configPieces() {
		for (int i = 0; i < N; i += 2) pieces[i][0] = new Piece(true, this, i, 0, "pawn");
        for (int i = 1; i < N; i += 2) pieces[i][1] = new Piece(true, this, i, 1, "shield");
        for (int i = 0; i < N; i += 2) pieces[i][2] = new Piece(true, this, i, 2, "bomb");

        for (int i = 1; i < N; i += 2) pieces[i][7] = new Piece(false, this, i, 7, "pawn");
        for (int i = 0; i < N; i += 2) pieces[i][6] = new Piece(false, this, i, 6, "shield");
        for (int i = 1; i < N; i += 2) pieces[i][5] = new Piece(false, this, i, 5, "bomb");
	}

	public static void main (String[] args) {
        Board board = new Board(false);
		 while(board.winner() == null) {
            board.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
               	if (board.canSelect(x, y)) {
               		board.select(x, y);
               	}
            } 
            StdDrawPlus.show(100); 
            if (StdDrawPlus.isSpacePressed()) {
            	if(board.canEndTurn()) {
            		board.endTurn();
            	}
            }      
        }
        // System.out.println("Winner "+board.winner());
	}

	private void drawBoard() {
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		for (int i = 0; i < N; i ++) {
			for (int j =0; j < N; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				if (i == selectedX && j == selectedY) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				if (pieces[i][j] != null) {
					Piece piece = pieces[i][j];
					String imageAddress = "img/";
					if (piece.isBomb()) {
						imageAddress += "bomb-";
					} else if (piece.isShield()) {
						imageAddress += "shield-";
					} else {
						imageAddress += "pawn-";
					}
					if (piece.isFire()) {
						imageAddress += "fire";
					} else {
						imageAddress += "water";
					}
					if (piece.isKing()) {
						imageAddress += "-crowned";
					}
					imageAddress += ".png";
					StdDrawPlus.picture(i + .5, j + .5, imageAddress, 1, 1);
				}
			}
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x >= N || y >= N) {
			return null;
		}
		return pieces[x][y];
	}

	public void place(Piece p, int x, int y) {
		if (p == null || x >= N || y >= N) {
			// do nothing
		} else {
			pieces[x][y] = p;
		}
	}

	public boolean canSelect(int x, int y) {
		Piece piece = this.pieceAt(x, y);
		if (piece != null && currentPlayer == piece.side()) {
			if (selectedPiece == null || !didMove) {
				return true;
			}
		} else {
			if (selectedPiece!= null && !didMove && validMove(selectedX, selectedY, x, y)) {
				return true;
			}
			if (selectedPiece!= null && selectedPiece.hasCaptured() && validCaptureMove(selectedX, selectedY, x, y)) {
				return true;
			}
		}
		return false;
	}

	public void select(int x, int y) {
		if (selectedPiece == null || ( pieceAt(x, y) != null && !didMove)) {
			selectedPiece = this.pieceAt(x, y);
			selectedX = x;
			selectedY = y;
		} else {
			selectedPiece.move(x, y);
			remove(selectedX, selectedY);
			place(selectedPiece, x, y);
			if (selectedPiece.hasCaptured()) {
				// System.out.println("Capture");
				// Remove captured piece
				this.remove((x + selectedX) / 2, (y + selectedY) / 2);
				if (selectedPiece.isBomb()) {
					for (int i = x - 1; i <= x + 1; i++) {
						for (int j = y - 1; j <= y + 1; j++) {
							Piece piece = this.pieceAt(i, j);
							if (piece != null) {
								if (!piece.isShield()) {
									this.remove(i, j);
								}
							}
						}
					}
				}
			}
			selectedX = x;
			selectedY = y;
			didMove = true;
		}			
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		// System.out.println(xi + ' ' + yi+ ' ' + xf+' '+yf);
		Piece piece = this.pieceAt(xi, yi);

		// Non-capture
		if (piece == null) {
			return false;
		}
		int direction = piece.isFire() ? 1 : -1;
		if ((xf == xi + direction && yf == yi + direction) || (xf == xi - direction && yf == yi + direction)) {
			if (pieceAt(xf, yf) == null) {
				return true;
			}
		}
		if (piece.isKing()) {
			if ((xf == xi - direction && yf == yi - direction) || (xf == xi + direction && yf == yi - direction)) {
				if (pieceAt(xf, yf) == null) {
					return true;
				}
			}
		}

		// Capture
		
		return validCaptureMove(xi, yi, xf, yf);
	}

	private boolean validCaptureMove(int xi, int yi, int xf, int yf) {
		Piece piece = this.pieceAt(xi, yi);

		if (piece == null) {
			return false;
		}
		int direction = piece.isFire() ? 2 : -2;
		int halfDirection = direction / 2;
		if (xf == xi + direction && yf == yi + direction) {
			Piece enemy = pieceAt(xi + halfDirection, yi + halfDirection);
			if (enemy != null && enemy.side() != piece.side()) {
				if (pieceAt(xf, yf) == null) {
					return true;
				}
			}
		}
		if (xf == xi - direction && yf == yi + direction) {
			Piece enemy = pieceAt(xi - halfDirection, yi + halfDirection);
			if (enemy != null && enemy.side() != piece.side()) {
				if (pieceAt(xf, yf) == null) {
					return true;
				}
			}
		}
		if (piece.isKing()) {
			if (xf == xi - direction && yf == yi - direction) {
				Piece enemy = pieceAt(xi - halfDirection,yi - halfDirection);
				if (enemy != null && enemy.side() != piece.side()) {
					if (pieceAt(xf, yf) == null) {
						return true;
					}
				}
			}
			if (xf == xi + direction && yf == yi - direction) {
				Piece enemy = pieceAt(xi + halfDirection, yi - halfDirection);
				if (enemy != null && enemy.side() != piece.side()) {
					if (pieceAt(xf, yf) == null) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean canEndTurn() {
		return didMove;
	}

	public void endTurn() {
		currentPlayer = 1 - currentPlayer;
		didMove = false;
		selectedPiece.doneCapturing();
		selectedPiece = null;

		// Unnecessary
		selectedX = N;
		selectedY = N;
	}

	public Piece remove(int x, int y) {
		if (x >= N || y >= N || x < 0 || y < 0) {
			// do nothing
		}
		Piece p = pieces[x][y];
		pieces[x][y] = null;
		return p;
	}

	public String winner() {
		int fireCount = 0, waterCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0 ; j < N; j ++) {
				Piece piece = this.pieceAt(i, j);
				if (piece == null) {
					continue;
				}
				if (piece.isFire()) {
					fireCount += 1;
				} else {
					waterCount += 1;
				}
			}
		}
		if (fireCount == 0 && waterCount == 0) {
			return "No one";
		} else if (fireCount == 0) {
			return "Water";
		} else if (waterCount == 0) {
			return "Fire";
		} else {
			return null;
		}
	}
}
