
public class Board {

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private boolean playerFire = true;
    private boolean selected = false;
    private boolean moved = false;
    private Piece selectedPiece;
    private int selectedPieceX;
    private int selectedPieceY;

    private static void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

				if (pieceArray[i][j] != null) {
					if (pieceArray[i][j].isFire()) {
						if (pieceArray[i][j].isBomb()) {
							if (pieceArray[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
							}
						}
						else if (pieceArray[i][j].isShield()) {
							if (pieceArray[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
							}
						}
						else {
							if (pieceArray[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
							}
						}
					}
					else {
						if (pieceArray[i][j].isBomb()) {
							if (pieceArray[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
							}
						}
						else if (pieceArray[i][j].isShield()) {
							if (pieceArray[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
							}
						}
						else {
							if (pieceArray[i][j].isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
							}
						}
					}
				}
            }
        }
    }      //end of draw board

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board board = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            board.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int xcoord = (int) x;
                int ycoord = (int) y;
                board.canSelect(xcoord, ycoord);
                board.select(xcoord, ycoord);
            }            
            StdDrawPlus.show(100);
        }
    }     //end of main method

	private static Piece[][] pieceArray = new Piece[8][8];


	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty) {
			for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++) {
            		pieceArray[i][j] = null;
            	}
           	}
		}
		else {
			pieceArray = new Piece[8][8];
			for (int i = 0; i < 8; i = i + 2) {
				pieceArray[i][0] = new Piece(true, this, i, 0, "pawn");
				pieceArray[i][2] = new Piece(true, this, i , 2, "bomb");
				pieceArray[i][6] = new Piece(false, this, i , 6, "shield");
			}

			for (int i = 1; i < 8; i = i + 2) {
				pieceArray[i][1] = new Piece(true, this, i, 1, "shield");
				pieceArray[i][5] = new Piece(false, this, i, 5, "bomb");
				pieceArray[i][7] = new Piece(false, this, i, 7, "pawn");
			}
		}	
	}

	public Piece pieceAt(int x, int y) {
		if (x > 8 || y > 8 || x < 0 || y < 0 || pieceArray[x][y] == null) {
			return null;
		}
		else return pieceArray[x][y];
	}

	public boolean canSelect(int x, int y) {
		if (pieceArray[x][y] != null) {
			if (selected == false || moved == false) return true;
			else return false;
		}
		else {
			if (selected == true && moved == false) return true;
			else if (selected == true && selectedPiece.hasCaptured() == true) return true;
			else return false;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xf > 8 || xf < 0 || yf > 8 || yf < 0) return false; //destination out of bounds
		int xp = xi + 1;
		int xm = xi - 1;
		int yp = yi + 1;
		int ym = yi - 1;
		if (xp > 8 || xm < 0 || yp > 8 || yp < 0) return false; //at an edge of the board
		if (pieceArray[xf][yf] == null) { //if destination empty
			if (pieceArray[xi][yi].isKing()) { //king
				if ((xf == xp || xf == xm) && (yf == yp || yf == ym)) { //can move all 4 diagonals
					return true;
				}
				else if (xf > xi && yf > yi) { //trying to go up and right
					if (pieceArray[xp][yp] != null && xf == xp + 1 && yf == yp + 1) { // there's a piece between and trying to go the next space
						return true;
					}
				}
				else if (xf < xi && yf > yi) { //trying to go up and left
					if (pieceArray[xm][yp] != null && xf == xm - 1 && yf == yp + 1) {
						return true;
					}
				}
				else if (xf > xi && yf < yi) { //trying to go down and right
					if (pieceArray[xp][ym] != null && xf == xp + 1 && yf == ym - 1) { // there's a piece between and trying to go the next space
						return true;
					}
				}
				else if (xf < xi && yf < yi) { //trying to go down and left
					if (pieceArray[xm][ym] != null && xf == xm - 1 && yf == ym - 1) {
						return true;
					}
				}
				else return false;
			}
			if (pieceArray[xi][yi].isFire()) {				//fire piece
				if ((xf == xp || xf == xm) && (yf == yp)) { //if going diagonally up
					return true;
				}
				else if (xf > xi && yf > yi) { //trying to go up and right
					if (pieceArray[xp][yp] != null && pieceArray[xp][yp].isFire() == false && xf == xp + 1 && yf == yp + 1) { // there's a piece between and trying to go the next space
						return true;
					}
				}
				else if (xf < xi && yf > yi) { //trying to go up and left
					if (pieceArray[xm][yp] != null && pieceArray[xm][yp].isFire() == false && xf == xm - 1 && yf == yp + 1) {
						return true;
					}
				}
				else return false;
			}
			else {											//water piece
				if ((xf == xp || xf == xm) && (yf == ym)) { //if going diagonally down
					return true;
				}
				else if (xf > xi && yf < yi) { //trying to go down and right
					if (pieceArray[xp][ym] != null && pieceArray[xp][ym].isFire() == true && xf == xp + 1 && yf == ym - 1) { // there's a piece between and trying to go the next space
						return true;
					}
				}
				else if (xf < xi && yf < yi) { //trying to go down and left
					if (pieceArray[xm][ym] != null && pieceArray[xm][ym].isFire() == true && xf == xm - 1 && yf == ym - 1) {
						return true;
					}
				}
				else return false;
			}
		} //end of if case
		return false; //if piece in (xf, yf)
	}   //end of method

	public void select(int x, int y) {
		if (pieceAt(x, y) != null) {
			selectedPiece = pieceAt(x, y);
			selectedPieceX = x;
			selectedPieceY = y;
		}
		else {
			selectedPiece.move(x, y);
			pieceArray[x][y] = selectedPiece;
			remove(selectedPieceX, selectedPieceY);
			moved = true;
		}
	}

	public void place(Piece p, int x, int y) {
		if (x > 8 || y > 8 || p == null) {
			return;
		}
		else pieceArray[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if (x > 8 || y > 8) {
			System.out.println("Square out of bounds");
			return null;
		}
		else if (pieceArray[x][y] == null) {
			System.out.println("No piece there");
			return null;
		}
		else {
			Piece removed = pieceArray[x][y];
			pieceArray[x][y] = null;
			return removed;
		}
	}

	public boolean canEndTurn() {
		if (moved == true) return true;
		else if (selectedPiece.hasCaptured() == false) return true;
		else return false;
	}

	public void endTurn() {
		playerFire = false;
	}

	public String winner() {
		int firePieces = 0;
		int waterPieces = 0;
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieceArray[i][j] == null) continue;
                else if (pieceArray[i][j].isFire()) firePieces += 1;
                else waterPieces += 1;
            }
        }
        if (waterPieces == 0) return "Fire";
        else if (firePieces == 0) return "Water";
        else if (firePieces == 0 && waterPieces == 0) return "No one";
        else return null;
	}
}    //end of class