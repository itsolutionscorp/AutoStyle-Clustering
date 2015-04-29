/**
 * @author Owen Jow
 */

public class Board {
	private static final int SIDE_LEN = 8;
	private Piece[][] pieces;
	private int currPlayer; // Fire is player 0; water is player 1
	private boolean isPieceSelected, hasMoved, hasBombed;
	private int selectedX = -1, selectedY = -1, numFirePieces, numWaterPieces;
	
	public static void main(String[] args) {
        StdDrawPlus.setXscale(0, SIDE_LEN);
        StdDrawPlus.setYscale(0, SIDE_LEN);
		
		Board b = new Board(false);
		
        while (true) {
            b.drawBoard(SIDE_LEN);
			
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
				if (b.canSelect((int) x, (int) y)) {
					b.select((int) x, (int) y);
				}
            }
			
			if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
				b.endTurn();
			}
			
            StdDrawPlus.show(1);
        }
	}
	
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[SIDE_LEN][SIDE_LEN];
		
		if (!shouldBeEmpty) {
			for (int i = 0; i <= SIDE_LEN - 2; i += 2) {
				pieces[i][0] = new Piece(true, this, i, 0, "pawn");
				pieces[i][2] = new Piece(true, this, i, 2, "bomb");
				pieces[i][6] = new Piece(false, this, i, 6, "shield");
			}
			
			for (int i = 1; i <= SIDE_LEN - 1; i += 2) {
				pieces[i][1] = new Piece(true, this, i, 1, "shield");
				pieces[i][5] = new Piece(false, this, i, 5, "bomb");
				pieces[i][7] = new Piece(false, this, i, 7, "pawn");
			}
			
			numFirePieces = 12;
			numWaterPieces = 12;
		}
	}
	
	private void drawBoard(int sideLength) {
		for (int i = 0; i < sideLength; i++) {
			for (int j = 0; j < sideLength; j++) {
				if (i == selectedX && j == selectedY) { StdDrawPlus.setPenColor(StdDrawPlus.WHITE); }
				else if ((i + j) % 2 == 0) { StdDrawPlus.setPenColor(StdDrawPlus.GRAY); } 
				else { StdDrawPlus.setPenColor(StdDrawPlus.RED); }
				
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				
                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + 0.5, j + 0.5, getPieceImg(pieces[i][j]), 1, 1);
                }
			}
		}
	}
	
	private boolean validPosition(int x, int y) {
		return ((-1 < x) && (x < 8) && (-1 < y) && (y < 8));
	}

	public Piece pieceAt(int x, int y) {
		if (!validPosition(x, y)) { return null; }
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		if (!validPosition(x, y) || hasBombed || (hasMoved 
				&& !pieceAt(selectedX, selectedY).hasCaptured())) { 
			return false; 
		}
		
		if (pieceAt(x, y) != null) {
			if (currPlayer != pieceAt(x, y).side()) { return false; }
			return (!isPieceSelected || (isPieceSelected && !canEndTurn()));
		} else {
			return (isPieceSelected && validMove(selectedX, selectedY, x, y));
		}
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (pieceAt(xf, yf) != null) { return false; }
		
		Piece currPiece = pieceAt(xi, yi);
		
		if (currPiece.hasCaptured()) {
			return validCapture(currPiece, xi, yi, xf, yf);
		} else {
			if (currPiece.isKing()) { 
				return isDiagonallyAdjacent(xi, yi, xf, yf) 
						|| validCapture(currPiece, xi, yi, xf, yf);
			} else {
				return isDiagonallyForward(currPiece.side(), xi, yi, xf, yf);
			}
		}
	}
	
	private boolean isDiagonallyForward(int side, int xi, int yi, int xf, int yf) {
		if (!isDiagonallyAdjacent(xi, yi, xf, yf) 
				&& !validCapture(pieceAt(xi, yi), xi, yi, xf, yf)) { 
			return false; 
		}
		
		if (side == 0) {
			return (yf > yi);
		} else {
			return (yf < yi);
		}
	}
	
	private boolean isDiagonallyAdjacent(int xi, int yi, int xf, int yf) {
		return (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1);
	}
	
	private boolean validCapture(Piece origPiece, int xi, int yi, int xf, int yf) {
		if (!origPiece.isKing()) {
			if (origPiece.side() == 0 && yf <= yi) { return false; }
			else if (origPiece.side() == 1 && yf >= yi) { return false; }
		}
		
		if (Math.abs(xf - xi) != 2 || Math.abs(yf - yi) != 2) { 
			return false; 
		} 
		
		int middleX = (xf + xi) / 2;
		int middleY = (yf + yi) / 2;
		Piece middlePiece = pieceAt(middleX, middleY);
		
		return (middlePiece != null && currPlayer != middlePiece.side());
	}

	public void select(int x, int y) {
		if (pieceAt(x, y) != null) { 
			isPieceSelected = true; 
		} else {
			Piece selectedPiece = pieceAt(selectedX, selectedY);
			selectedPiece.move(x, y);
			hasMoved = true;
			
			if (selectedPiece.isBomb() && selectedPiece.hasCaptured()) {
				hasBombed = true;
				bombsAway(x, y);
			}
		}

		selectedX = (hasBombed)? -1 : x;
		selectedY = (hasBombed)? -1 : y;
	}
	
	private void bombsAway(int xf, int yf) {
		for (int i = xf - 1; i <= xf + 1; i++) {
			for (int j = yf - 1; j <= yf + 1; j++) {
				if (validPosition(i, j) && pieceAt(i, j) != null
						&& !pieceAt(i, j).isShield()) {
					remove(i, j);
				}
			}
		}
	}

	public void place(Piece p, int x, int y) {
		if (validPosition(x, y) && p != null) {
			pieces[x][y] = p;
			if (p.isFire()) { numFirePieces++; }
			else { numWaterPieces++; }
		}
	}

	public Piece remove(int x, int y) {
		if (!validPosition(x, y)) { 
			System.out.println("Invalid position");
			return null;
		} 
		
		Piece pieceToRemove = pieceAt(x, y);
		
		if (pieceToRemove == null) {
			System.out.println("No piece exists at " + x + ", " + y);
			return null;
		} 
		
		pieces[x][y] = null;
		
		if (pieceToRemove.isFire()) { numFirePieces--; }
		else { numWaterPieces--; }
		
		return pieceToRemove;
	}

	public boolean canEndTurn() {
		return hasMoved;
	}

	public void endTurn() {
		if (!hasBombed) { 
			pieceAt(selectedX, selectedY).doneCapturing(); 
		}
		
		selectedX = -1;
		selectedY = -1;
		isPieceSelected = false;
		hasMoved = false;
		hasBombed = false;
		currPlayer = 1 - currPlayer;
	}

	public String winner() {
		if (numFirePieces > 0 && numWaterPieces > 0) { return null; }
		else if (numFirePieces == 0 && numWaterPieces == 0) { return "No one"; }
		else if (numFirePieces == 0) { return "Water"; }
		else { return "Fire"; }
	}

	private static String getPieceImg(Piece p) {
		String imgStr = "img/";
		
		if (p.isBomb()) { imgStr += "bomb"; }
		else if (p.isShield()) { imgStr += "shield"; }
		else { imgStr += "pawn"; }
		imgStr += (p.isFire())? "-fire" : "-water";
		imgStr += (p.isKing())? "-crowned.png" : ".png";
	
		return imgStr;
	}
}