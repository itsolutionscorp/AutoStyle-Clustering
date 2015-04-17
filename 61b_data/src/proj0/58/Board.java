

public class Board {
	
	private static int dimensions = 8;
	private Piece[][] pieces = new Piece[dimensions][dimensions];
	private boolean isFireTurn = true;
	private boolean hasSelected = false;
	private boolean hasMoved = false;
	private Piece selectedP;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board b = new Board(false);
		b.update();
		
	}
	
	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			setUpPieces(this);
		}
	}
	
	public Piece pieceAt(int x, int y) {
		if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
			return null;
		}
		if (pieces[x][y] != null) {
			return pieces[x][y];
		}
		else {
			return null;
		}
		
	}
	
	public boolean canSelect(int x, int y) {
		if (isFireTurn) {
			if ((pieceAt(x,y) != null) && (pieceAt(x,y).isFire())) {
				if ((!hasSelected) || (!hasMoved)) {
					return true;
				}
			}
			else {
				if ((hasSelected) && (!hasMoved)) {
					if(validMove(getX(selectedP), getY(selectedP), x, y)) {
						return true;
					}
					return false;
				}
				if (hasMoved && selectedP.hasCaptured()) {
					if(validMove(getX(selectedP), getY(selectedP), x, y)) {
						if (isJump(getX(selectedP), getY(selectedP), x, y)) {
							return true;
						}
					}
				}
			}
			return false;
		}
		else {
			if ((pieceAt(x,y) != null) && (!pieceAt(x,y).isFire())) {
				if ((!hasSelected) || (!hasMoved)) {
					return true;
				}
			}
			else {
				if ((hasSelected) && (!hasMoved)) {
					if(validMove(getX(selectedP), getY(selectedP), x, y)) {
						return true;
					}
					return false;
				}
				if (hasMoved && selectedP.hasCaptured()) {
					if(validMove(getX(selectedP), getY(selectedP), x, y)) {
						if (isJump(getX(selectedP), getY(selectedP), x, y)) {
							return true;
						}
					}
				}
			}
			return false;
		}
		
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (pieceAt(xf, yf) == null) {
			if (isDiagonal(xi, yi, xf, yf)) {
				if (isForward(xi, yi, xf, yf)) {
					if (isJump(xi, yi, xf, yf)) {
						if (canCapture(xi,yi,xf,yf)) {
							return true;
						}
						else {
							return false;
						}
					}
					else if (((yf - yi) == 1) || ((yi - yf) == 1)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void select(int x, int y) {
		if (pieceAt(x,y) != null){
			hasSelected = true;
			selectedP = pieceAt(x,y);
			System.out.println(pieceAt(x,y));
		}	
		else {
			selectedP.move(x, y);
			hasMoved = true;
		}
	}
	
	public void place(Piece p, int x, int y) {
		if ((x < dimensions) || (y < dimensions) || (p != null)) {
			if (isItIn(p)) {
				remove(this.getX(p), this.getY(p));
			}
			if (pieces[x][y] != null) {
				remove(x,y);
			}
			pieces[x][y] = p;
		}
	}
	
	public Piece remove(int x, int y) {
		if (pieceAt(x,y) != null){
			Piece removedPiece = pieceAt(x,y);
			pieces[x][y] = null;
			return removedPiece;
		}
		return null;
	}
	
	public boolean canEndTurn() {
		if (hasMoved) {
			return true;
		}
		return false;
	}
	
	public void endTurn() {
		if (canEndTurn()) {
			hasSelected = false;
			hasMoved = false;
			selectedP.doneCapturing();
			isFireTurn = !isFireTurn;
		}
		
	}
	
	public String winner() {
		return null;
	}
	
	//						HELPER METHODS *YAY!*
	// _______________________________________________________________________
	//________________________________________________________________________
	
	
	// the getters for the piece's positions
	private int getX(Piece p) {
		for (int i = 0; i < pieces.length; i++) {
		    for (int j = 0; j < pieces[i].length; j++) {
		    	if (pieces[i][j] == p) {
		    		return i;
		    	}
		    }
		}
		return -1;
	}
	private int getY(Piece p) {
		for (int i = 0; i < pieces.length; i++) {
		    for (int j = 0; j < pieces[i].length; j++) {
		    	if (pieces[i][j] == p) {
		    		return j;
		    	}
		    }
		}
		return -1;
	}
	
	// helper method that checks if piece is on the board
	private boolean isItIn(Piece p) {
		for (int i = 0; i < pieces.length; i++) {
		    for (int j = 0; j < pieces[i].length; j++) {
		        if (pieceAt(i, j) == p) {
		            return true;
		        }
		    }
		}
		return false;
	}
	
	// helper methods for Valid Move
	private boolean canCapture(int xi, int yi, int xf, int yf) {
		int middlex = -1;
		int middley = -1;
		if (xf > xi) {
			middlex = xi + 1;
		}
		else {
			middlex = xi - 1;
		}
		if (yf > yi) {
			middley = yi + 1;
		}
		else {
			middley = yi - 1;
		}
		if (pieceAt(middlex, middley) == null) {
			return false;
		}
		if (pieceAt(middlex, middley).isFire() != pieceAt(xi,yi).isFire()) {
			return true;
		}
		return false;
	}
	private boolean isDiagonal(int xi, int yi, int xf, int yf) {
		int calc = (xf + yf) - (xi + yi);
		if (calc % 2 == 0) {
			return true;
		}
		return false;
	}
	private boolean isForward(int xi, int yi, int xf, int yf) {
		if (pieceAt(xi, yi).isFire()) {
			if (yf > yi) {
				return true;
			}
			else if (pieceAt(xi,yi).isKing()) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if (yf < yi) {
				return true;
			}
			else if (pieceAt(xi,yi).isKing()) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	private boolean isJump(int xi, int yi, int xf, int yf) {
		if (isDiagonal(xi,yi,xf,yf)) {
			if ((yf - yi) ==2) {
				return true;
			}
			else if ((yi - yf) ==2) {
				return true;
			}
		}
		return false;
	}
	
	
	//helper methods to draw the board
	private void setUpPieces(Board b) {
		for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                if ((i + j) % 2 == 0) {
                	if (j == 0) {
                		pieces[i][j] = new Piece(true, b, i, j, "pawn");
                	}
                	if (j == 1) {
                		pieces[i][j] = new Piece(true, b, i, j, "shield");
                	}
                	if (j == 2) {
                		pieces[i][j] = new Piece(true, b, i, j, "bomb");
                	}
                	if (j == 5) {
                		pieces[i][j] = new Piece(false, b, i, j, "bomb");
                	}
                	if (j == 6) {
                		pieces[i][j] = new Piece(false, b, i, j, "shield");
                	}
                	if (j == 7) {
                		pieces[i][j] = new Piece(false, b, i, j, "pawn");
                	}
                }
                else {
                	pieces[i][j] = null;
                }
            }
		}
	}
	private void drawPieces(Piece p, Board b, int i, int j) {
		 if ((!p.isBomb()) && (!p.isShield())) {
			 if (p.isFire()) {
				 if (p.isKing()) {
					 StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
				 }
				 else {
					 StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
				 }	 
			 }
			 else {
				 if (p.isKing()) {
					 StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
				 }
				 else {
					 StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
				 }
			 }
		 }
		 if (p.isBomb()) {
			 if (p.isFire()) {
				 if (p.isKing()) {
					 StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
				 }
				 else {
					 StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
				 }
			 }
			 else {
				 if (p.isKing()) {
					 StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
				 }
				 else {
					 StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
				 }
			 }
		 }
		 if (p.isShield()) {
			 if (p.isFire()) {
				 if (p.isKing()) {
					 StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
				 }
				 else {
					 StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
				 }
			 }
			 else {
				 if (p.isKing()) {
					 StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
				 }
				 else {
					 StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
				 }
			 }
		 }
	}
	private void drawBoard() {
		StdDrawPlus.setXscale(0, dimensions);
        StdDrawPlus.setYscale(0, dimensions);
        
		for (int i = 0; i < dimensions; i++) {
            for (int j = 0; j < dimensions; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                
                if (pieceAt(i, j) != null) {
                	drawPieces(pieceAt(i, j), this, i, j);
                }
            }
        }
	}
	
	public void update() {
		while(true) {
            drawBoard();
            if (StdDrawPlus.isSpacePressed() && canEndTurn()) {
            	endTurn();
            }	
            else if (StdDrawPlus.mousePressed()) {
            	System.out.println(isFireTurn);
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if (pieceAt(x,y) != null) {
                	if (canSelect(x,y)) {
                		select(x,y);
                	}
                }
                else {
                	if (selectedP != null) {
                		if(canSelect(x, y)) {
                			select(x,y);
                			hasMoved = true;
                		}
                	}
                }
            }
            StdDrawPlus.show(50);
        }
	}
}
