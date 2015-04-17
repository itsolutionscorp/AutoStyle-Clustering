import java.lang.Math;

public class Board {
	
	private Piece pieceSelected = null;
	private int pieceSelectedX = -1;
	private int pieceSelectedY = -1;
	private Piece tempPieceSelected = null;
	private boolean canEndTurn = false;
	private int currSide = 0;
	private Piece[][] pieces = new Piece[8][8];
	
	public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board board = new Board(false);
        while(board.winner() == null) {
            board.drawBoard(N);
            if (StdDrawPlus.mousePressed() && board.canSelect((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY())) {    	
            	board.select((int) StdDrawPlus.mouseX(), (int) StdDrawPlus.mouseY());
            }
            else if (StdDrawPlus.isSpacePressed()) {
            	if (board.canEndTurn()) {
            		board.endTurn();
            	}
            }
            else {
            }
            StdDrawPlus.show(10);
        }
        System.out.println(board.winner());
	}
	
	 private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
        for (int x = 0; x < N; x++) {
        	for (int y = 0; y < N; y++) {
	        	String s = "";
	        	if (pieces[x][y] != null) {
		        	if (pieces[x][y].isBomb() == true) {
		        		s += "bomb";
		        	}
		        	else if (pieces[x][y].isShield() == true) {
		        		s += "shield";
		        	}
		        	else {
		        		s += "pawn";
		        	}
		        	s += "-";
		        	if (pieces[x][y].isFire() == true) {
		        		s += "fire";
		        	}
		        	else {
		        		s += "water";
		        	}
		        	if (pieces[x][y].isKing() == true) {
		        		s += "-crowned";
		        	}
		        	if (pieces[x][y] == pieceSelected) {
		        		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		        	}
		            StdDrawPlus.picture(x + .5, y + .5, "img/" + s + ".png", 1, 1);
	        	}
        	}
        }
	 }
	
	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == false) {
	        pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
	        pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
	        pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
	        pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
	        pieces[1][1] = new Piece(true, this, 1, 1, "shield");
	        pieces[3][1] = new Piece(true, this, 3, 1, "shield");
	        pieces[5][1] = new Piece(true, this, 5, 1, "shield");
	        pieces[7][1] = new Piece(true, this, 7, 1, "shield");
	        pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
	        pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
	        pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
	        pieces[6][2] = new Piece(true, this, 6, 2, "bomb");
	        pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
	        pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
	        pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
	        pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
	        pieces[0][6] = new Piece(false, this, 0, 6, "shield");
	        pieces[2][6] = new Piece(false, this, 2, 6, "shield");
	        pieces[4][6] = new Piece(false, this, 4, 6, "shield");
	        pieces[6][6] = new Piece(false, this, 6, 6, "shield");
	        pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
	        pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
	        pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
	        pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
        }
	}
	
	public Piece pieceAt(int x, int y) {
		if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			return pieces[x][y];
		}
		else {
			return null;
		}
	}
	
	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null && pieceAt(x, y).side() == currSide && canEndTurn == false) { //filled square
			return true;
		}
		else if (pieceAt(x, y) == null && pieceSelected != null) { //empty square
			// Regular move
			if (canEndTurn == false && validMove(pieceSelectedX, pieceSelectedY, x, y)) {
				return true;
			}
			// Captured and capturing again
			else if (pieceSelected.hasCaptured() == true
							&& validMove(pieceSelectedX, pieceSelectedY,
								x, y)
									&& Math.abs(pieceSelectedX - x) == 2) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf) {
//		System.out.println("xi: " + xi);
//		System.out.println("yi: " + yi);
//		System.out.println("xf: " + xf);
//		System.out.println("yf: " + yf);
		// Check if King
		if (pieceAt(xi, yi).isKing() == true) {
			// Check if normal move
			if ((xf == xi + 1 || xf == xi - 1)
					&& (yf == yi + 1 || yf == yi - 1)
						&& pieceAt(xf, yf) == null) {
				return true;
			}
			// Check if capture move
			else if ((xf == xi + 2 || xf == xi - 2)
						&& (yf == yi + 2 || yf == yi - 2)
							&& pieceAt((xf+xi)/2, (yf+yi)/2) != null
								&& pieceAt((xf+xi)/2, (yf+yi)/2).side() != pieceAt(xi, yi).side()) {
				return true;
			}
			// No other option
			else {
				return false;
			}
		}
		else {
			// Check if Fire
			if (pieceAt(xi, yi).isFire() == true) {
				// Check if normal fire move
				if ((xf == xi + 1 || xf == xi - 1)
						&& yf == yi + 1
							&& pieceAt(xf, yf) == null) {
					return true;
				}
				// Check if capture fire move
				else if ((xf == xi + 2 || xf == xi - 2)
						&& (yf == yi + 2)
							&& pieceAt((xf+xi)/2, yi+1) != null
								&& pieceAt((xf+xi)/2, yi+1).side() != pieceAt(xi, yi).side()) {
					return true;
				}
				// No other option
				else {
					return false;
				}
			}
			// Check if Water
			else {
				// Check if normal water move
				if ((xf == xi + 1 || xf == xi - 1)
						&& yf == yi - 1
							&& pieceAt(xf, yf) == null) {
					return true;
				}
				// Check if capture water move
				else if ((xf == xi + 2 || xf == xi - 2)
						&& (yf == yi - 2)
							&& pieceAt((xf+xi)/2, yi-1) != null
								&& pieceAt((xf+xi)/2, yi-1).side() != pieceAt(xi, yi).side()) {
					return true;
				}
				// No other option
				else {
					return false;
				}
			}
		}
	}
	
	public void select(int x, int y) {		
		if (pieceAt(x, y) != null) {
			pieceSelected = pieceAt(x, y);
			pieceSelectedX = x;
			pieceSelectedY = y;
		}
		else {
			pieceSelected.move(x, y);
			if (pieceSelected.isBomb() == true && pieceSelected.hasCaptured() == true) {
				tempPieceSelected = pieceSelected;
				pieceSelected = null;
				pieceSelectedX = -1;
				pieceSelectedY = -1;
			}
			canEndTurn = true;
		}
	}
	
	public void place(Piece p, int x, int y) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] == p) {
					pieces[i][j] = null;
				}
			}
		}
		if (x >= 0 && x <= 7 && y >= 0 && y <= 8) {
			pieces[x][y] = p;
			pieceSelectedX = x;
			pieceSelectedY = y;
		}
	}
	
	public Piece remove(int x, int y) {
		if (pieceAt(x, y) != null) {
			Piece temp = pieces[x][y];
			pieces[x][y] = null;
			return temp;
		}
		else if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
			System.out.println("Out of bounds.");
			return null;
		}
		else {
			System.out.println("No such piece at that location.");
			return null;
		}
	}
	
	public boolean canEndTurn() {
		return canEndTurn;
	}
	
	public void endTurn() {
		if (currSide == 0) {
			currSide = 1;
		}
		else {
			currSide = 0;
		}
		if (tempPieceSelected != null) {
			tempPieceSelected.doneCapturing();
			tempPieceSelected = null;
		}
		if (pieceSelected != null) {
			pieceSelected.doneCapturing();
		}
//		System.out.println("new turn");
		pieceSelected = null;
		pieceSelectedX = -1;
		pieceSelectedY = -1;
		canEndTurn = false;
	}

	public String winner() {
		boolean fireExists = false;
		boolean waterExists = false;
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				if (pieces[x][y] != null && pieces[x][y].isFire()) {
					fireExists = true;
				}
				else if (pieces[x][y] != null && pieces[x][y].isFire() == false) {
					waterExists = true;
				}
			}
		}
		if (fireExists == true && waterExists == false) {
			return "Fire";
		}
		else if (fireExists == false && waterExists == true) {
			return "Water";
		}
		else if (fireExists == false && waterExists == false) {
			return "No one";
		}
		else {
			return null;
		}
	}
}
