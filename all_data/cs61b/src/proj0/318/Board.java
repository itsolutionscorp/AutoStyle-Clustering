import java.lang.Math;

public class Board {

	private Piece[][] pieces; 
	private String turn = "fire";
	private boolean hasSelected = false;
	private Piece lastSelectedPiece; 
	private int lastSSx;
	private int lastSSy;
	private boolean hasMoved = false;
	private Piece tempPiece;
	private int numfirepieces;
	private int numwaterpieces;
	private static Board b;
	
	public static void main(String[] args) {
		b = new Board(false);
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        while(true) {
            b.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
				int tempxcoord = (int) StdDrawPlus.mouseX(); 
				int tempycoord = (int) StdDrawPlus.mouseY(); 
				if (b.canSelect(tempxcoord, tempycoord)) {
					b.select(tempxcoord, tempycoord);
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(tempxcoord + .5, tempycoord + .5, .5);
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn()) {
					b.endTurn();
				}
			}         
            StdDrawPlus.show(100);
        }
	}


	private void drawBoard(int N) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
			}
		}
		for(int i = 0; i < pieces.length; i++) {
			for(int j = 0; j < pieces.length; j++) {
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()) {
						if (!pieces[i][j].isBomb() && !pieces[i][j].isShield()) {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png" , 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png" , 1, 1);
							}
						} else if (pieces[i][j].isShield()) {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png" , 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png" , 1, 1);
							}
						} else {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png" , 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png" , 1, 1);
							}
						}
					} else {
						if (!pieces[i][j].isBomb() && !pieces[i][j].isShield()) {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png" , 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png" , 1, 1);
							}
						} else if (pieces[i][j].isShield()) {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png" , 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png" , 1, 1);
							}
						} else {
							if (pieces[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png" , 1, 1);
							} else {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png" , 1, 1);
							}
						}
					}
				}
			}
		}
	}

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		if (!shouldBeEmpty) {
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					OnBoardPlacer(j == 0 && i % 2 == 0, true, i, j, "pawn");
					OnBoardPlacer(j == 1 && i % 2 != 0, true, i, j, "shield");
					OnBoardPlacer(j == 2 && i % 2 == 0, true, i, j, "bomb");
					OnBoardPlacer(j == 5 && i % 2 != 0, false, i, j, "bomb");
					OnBoardPlacer(j == 6 && i % 2 == 0, false, i, j, "shield");
					OnBoardPlacer(j == 7 && i % 2 != 0, false, i, j, "pawn");
				} 
			}
		}
	}

	private void OnBoardPlacer(boolean condition, boolean fireornot, int xcoord, int ycoord, String piecetype) {
		if (condition) {
			pieces[xcoord][ycoord] = new Piece(fireornot, this, xcoord, ycoord, piecetype); 
		}
	}

	public Piece pieceAt(int x, int y) {
		if ((x < 0 || x > 7) || (y < 0 || y > 7)) {
			return null;
		} else {
			return pieces[x][y];
		}
	}

	public boolean canSelect(int x, int y) {
		if (pieces[x][y] != null) {
			if ((pieces[x][y].isFire() && turn == "fire") || (!pieces[x][y].isFire() && turn == "water")) {
				if (!hasSelected || (hasSelected && !hasMoved)) {
					return true;
				}
			}
			if (hasSelected & hasMoved) {
				return false;
			}
		} else {
			if ((hasSelected && !hasMoved) && validMove(lastSSx, lastSSy, x, y))  {
				return true;
			} else if (hasSelected && lastSelectedPiece.hasCaptured() && validMove(lastSSx, lastSSy, x, y) && Math.abs(x - lastSSx) == 2 && Math.abs(y - lastSSy) == 2) {
				return true;
			}
		}
		return false;
	}


	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1 && pieces[xf][yf] == null)  {
			if (lastSelectedPiece.isKing()) {
				return true;
			} else if (lastSelectedPiece.isFire() && yf - yi == 1) {
				return true;
			} else if (!lastSelectedPiece.isFire() && yi - yf == 1) {
				return true;
			} else {
				return false;
			}
		} else if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2 && pieces[xf][yf] == null) {
			if (pieces[xi + (xf - xi)/2][yi + (yf - yi)/2] != null) {
				if ((pieces[xi + (xf - xi)/2][yi + (yf - yi)/2].isFire() && turn == "water") || (!pieces[xi + (xf - xi)/2][yi + (yf - yi)/2].isFire() && turn == "fire")) {
					if (lastSelectedPiece.isKing()) {
						return true;
					} else if (lastSelectedPiece.isFire() && yf - yi == 2) {
						return true;
					} else if (!lastSelectedPiece.isFire() && yi - yf == 2) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
		return false;
	}

	public void select(int x, int y) {
		hasSelected = true;
		if (pieces[x][y] != null) {
			lastSelectedPiece = pieces[x][y];
		} else if (lastSelectedPiece != null && pieces[x][y] == null) {
			hasMoved = true;
			lastSelectedPiece.move(x, y);
		}
		lastSSx = x;
		lastSSy = y;
	}

	public void place(Piece p, int x, int y) {
		if (p != null) {
			for(int i = 0; i < pieces.length; i++) {
				for(int j = 0; j < pieces.length; j++) {
					if (pieces[i][j] == p) {
						pieces[i][j] = null;
					}
				}
			}
		}
		if ((x >= 0 && x <= 7) && (y >= 0 && y <= 7)) {
			if (p != null) {
				pieces[x][y] = p;
			}
		}
	}
	

	public Piece remove(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("Index out of bounds for remove.");
			return null;
		} else if (pieces[x][y] == null) {
			System.out.println("There is no piece to remove at this position.");
			return null;
		} else {
			tempPiece = pieces[x][y];
			pieces[x][y] = null;
			return tempPiece;
		}
	}

	public boolean canEndTurn() {
		if (hasMoved) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		if (turn == "fire") {
			turn = "water";
		} else {
			turn = "fire";
		}
		hasSelected = false;
		hasMoved = false;  
		if (lastSelectedPiece != null) {
			lastSelectedPiece.doneCapturing();
			lastSelectedPiece = null;
		}
	}

	public String winner() {
		numfirepieces = 0;
		numwaterpieces = 0;
		for(Piece[] piecearray:pieces) {
			for(Piece pieceobj:piecearray) {
				if (pieceobj != null && pieceobj.isFire()) {
					numfirepieces += 1;
				} else if (pieceobj != null && !pieceobj.isFire()) {
					numwaterpieces += 1;
				}
			}
		}
		if (numfirepieces != 0 && numwaterpieces == 0) {
			return "Fire";
		} else if (numwaterpieces != 0 && numfirepieces == 0) {
			return "Water";
		} else if (numfirepieces == 0 && numwaterpieces == 0) {
			return "No one";
		} else {
			return null;
		}
	}
}