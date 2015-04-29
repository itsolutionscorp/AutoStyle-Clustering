public class Board {

	private static int N = 8;
	
	private Piece pieces[][];
	private int side;
	
	private int selectedX = -1;
	private int selectedY = -1;
	private boolean moved;
	
	
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[N][N];
		side = 1;
		if (shouldBeEmpty) {
			return;
		} else {
			for (int i = 0; i < N; i += 2) {
				place(new Piece(true, this, i, 0, "pawn"), i, 0);
				place(new Piece(false, this, i+1, N-1, "pawn"), i+1, N-1);
				place(new Piece(true, this, i+1, 1, "shield"), i+1, 1);
				place(new Piece(false, this, i, N-2, "shield"), i, N-2);
				place(new Piece(true, this, i, 2, "bomb"), i, 2);
				place(new Piece(false, this, i+1, N-3, "bomb"), i+1, N-3);
			}
		} 
	}

	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p != null) {
			return p.side() == side && !moved; 
		} else {
			return (pieceAt(selectedX, selectedY) != null && !moved && validMove(selectedX, selectedY, x, y))
				|| (pieceAt(selectedX, selectedY) != null && pieceAt(selectedX, selectedY).hasCaptured() && validCaptureMove(selectedX, selectedY, x, y));
		}
		
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xf < 0 || xf >= N || yf < 0 || yf >= N) {
			return false;
		}
		return validNonCaptureMove(xi, yi, xf, yf) || validCaptureMove(xi, yi, xf, yf);
	}
	
	
	private boolean validNonCaptureMove(int xp, int yp, int xf, int yf) {
		if (pieceAt(xp, yp).isKing()) {
			return Math.abs(xp - xf) == 1 && Math.abs(yp - yf) == 1 && pieceAt(xf, yf) == null;
		} else {
			if (pieceAt(xp, yp).isFire()) {
				return yp - yf == -1 && Math.abs(xp - xf) == 1 && pieceAt(xf, yf) == null;
			} else {
				return yp - yf == 1 && Math.abs(xp - xf) == 1 && pieceAt(xf, yf) == null;
			}
		}
	}
	
	private boolean validCaptureMove(int xp, int yp, int xf, int yf) {
		if (pieceAt(xp, yp).isKing()) {
			return Math.abs(xp - xf) == 2 && Math.abs(yp - yf) == 2 
					&& pieceAt(Math.min(xf, xp)+1, Math.min(yf, yp)+1) != null
					&& pieceAt(xf, yf) == null;
		} else {
			if (pieceAt(xp, yp).isFire()) {
				return yp - yf == -2 && Math.abs(xp - xf) == 2 
					&& pieceAt(Math.min(xf, xp)+1, yp+1) != null && pieceAt(Math.min(xf, xp)+1, yp+1).side() != side
					&& pieceAt(xf, yf) == null;
			} else {
				return yp - yf == 2 && Math.abs(xp - xf) == 2 
					&& pieceAt(Math.min(xf, xp)+1, yp-1) != null && pieceAt(Math.min(xf, xp)+1, yp-1).side() != side
					&& pieceAt(xf, yf) == null;
			}
		}
	}
	
	public void select(int x, int y) {
		Piece newselect = pieceAt(x, y);
		if (newselect != null && (pieceAt(selectedX, selectedY) == null || !moved)) {
			selectedX = x;
			selectedY = y;
		} else {
			pieceAt(selectedX, selectedY).move(x, y);
			moved = true;
			if (pieceAt(x, y) != null) {
				selectedX = x;
				selectedY = y;
			} else {
				selectedX = -1;
				selectedY = -1;
			}
		}
	}
	
	
	public Piece pieceAt(int x, int y) {
		if (x >= N || y >= N || x < 0 || y < 0) {
			return null;
		}
		return pieces[x][y];
	}
	
	public void place(Piece p, int x, int y) {
		if (x >= N || y >= N || p == null) {
			return;
		}
		if (isOnBoard(p)) {
			remove(getPosition(p)[0], getPosition(p)[1]);
		}
		Piece atPlace = pieceAt(x, y);
		if (atPlace != null) {
			remove(x, y);
		}
		pieces[x][y] = p;
		
	}
	
	private boolean isOnBoard(Piece p) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (pieceAt(i, j) == p) {
					return true;
				}
			}
		}
		return false;
	}
	
	private int[] getPosition(Piece p) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (pieceAt(i, j) == p) {
					return new int[]{i,j};
				}
			}
		}
		return null;
	}
	
	
	public Piece remove(int x, int y) {
		if (x >= N || y >= N) {
			return null;
		} 
		Piece atPlace = pieceAt(x, y);
		if (atPlace == null) {
			return null;
		}
//		atPlace.b = null;
		pieces[x][y] = null;
		return atPlace;
	}
	
	public boolean canEndTurn() {
		return moved || (pieceAt(selectedX, selectedY) != null && pieceAt(selectedX, selectedY).hasCaptured());
	}
	
	public void endTurn() {
		side = (side + 1) % 2;
		moved = false;
		if (pieceAt(selectedX, selectedY) != null) {
			pieceAt(selectedX, selectedY).doneCapturing();
		}
		selectedX = -1;
		selectedY = -1;
	}
	
	public String winner() {
		int firenum = 0;
		int waternum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Piece p = pieceAt(i, j);
				if (p != null) {
					if (p.isFire()) {
						firenum++;
					} else {
						waternum++;
					}
				}
			}
		}
		if (firenum == 0 && waternum == 0) {
			return "No one";
		} else if (firenum == 0) {
			return "Water";
		} else if (waternum == 0){
			return "Fire";
		} else {
			return null;
		}
	}
	
	private void drawBoard() {
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieceAt(selectedX, selectedY) != null && (selectedX == i && selectedY == j)) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);  
                } else if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);         
                drawPiece(i, j, pieceAt(i, j));
            }
        }
    }
	
	private void drawPiece(int x, int y, Piece p) {
		if (p == null) {
			return;
		}
		String picture = "img/";
		if (p.isBomb()) {
			picture += "bomb";
		} else if (p.isShield()) {
			picture += "shield";
		} else {
			picture += "pawn";
		}
		if (p.isFire()) {
			picture += "-fire";
		} else {
			picture += "-water";
		}
		if (p.isKing()) {
			picture += "-crowned";
		}
		picture += ".png";
		StdDrawPlus.picture(x + .5, y + .5, picture, 1, 1);
		
	}
	
	
	public static void main(String[] args) {
		Board board = new Board(false);
		while (true) {
			board.drawBoard();
			StdDrawPlus.show(50);
			if (board.winner() != null) {
				break;
			}
			if (StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (board.canSelect(x, y)) {
					board.select(x, y);
				} 
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (board.canEndTurn()) {
					board.endTurn();
				}
			}
		}
	}

}