public class Board {

	private Piece pieces[][];
	private boolean isFireTurn = true;
	private boolean hasMovedBoard = false;
	private Piece isSelected = null;
	private int xSelected = 10;
	private int ySelected = 10;
	private boolean playerHasCaptured = false;
	private boolean cantMove = false;

	public static void main(String[] args) {
		Board b = new Board(false);
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
        while(true) {
            b.drawBoard(8);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b == null) {
                }
                else if (b.canSelect((int) x, (int) y)) {
                	b.select((int) x, (int) y);		
                }
            }
            else if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }
            StdDrawPlus.show(10);
        }
	}

    	private void drawBoard(int N) {
        	for (int i = 0; i < N; i++) {
            	for (int j = 0; j < N; j++) {
            		if ((i == xSelected) && (j == ySelected)) {
	                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            		}
                	else if ((i + j) % 2 == 0) {
                		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	}
                	else {
                		StdDrawPlus.setPenColor(StdDrawPlus.RED);
                	}
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	if (this.pieceAt(i, j) == null) {
                		;
                	}
                	else {
                		if (this.pieceAt(i, j).isFire() == true) {
                			if (this.pieceAt(i, j).isKing() == false) {
                				if (this.pieceAt(i, j).isBomb() == true) {
                	    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                				}
                				else if (this.pieceAt(i, j).isShield() == true) {
                	    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                				}
                				else {
                	    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                				}
                			}
                			else {
                				if (this.pieceAt(i, j).isBomb() == true) {
                	    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                				}
                				else if (this.pieceAt(i, j).isShield() == true) {
                	    			StdDrawPlus.picture(i + .5, j + .5, "img/sheild-fire-crowned.png", 1, 1);
                				}
                				else {
                	    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                				}
                			}	
                		}
                		else {
                			if (this.pieceAt(i, j).isKing() == false) {
                				if (this.pieceAt(i, j).isBomb() == true) {
                	    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                				}
                				else if (this.pieceAt(i, j).isShield() == true) {
                	    			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                				}
                				else {
                	    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                				}
                			}
                			else {
                				if (this.pieceAt(i, j).isBomb() == true) {
                	    			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                				}

                				else if (this.pieceAt(i, j).isShield() == true) {
                	    			StdDrawPlus.picture(i + .5, j + .5, "img/sheild-water-crowned.png", 1, 1);
                				}
                				else {
                	    			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                				}
                			}	
                		}  
                	}             	                	
            	}
        	}
    	}

		public Board(boolean shouldBeEmpty) {
			if (shouldBeEmpty == true) {
				pieces = new Piece [8][8];
			}
			else {
				pieces = new Piece [8][8];
				for (int c = 0; c < 8; c = c + 2) {
					pieces[c][0] = new Piece(true, this, c, 0, "pawn");
				}
				for (int c = 1; c < 8; c = c + 2) {
					pieces[c][1] = new Piece(true, this, c, 1, "shield");
				}
				for (int c = 0; c < 8; c = c + 2) {
					pieces[c][2] = new Piece(true, this, c, 2, "bomb");
				}
				for (int c = 1; c < 8; c = c + 2) {
					pieces[c][7] = new Piece(false, this, c, 7, "pawn");
				}
				for (int c = 0; c < 8; c = c + 2) {
					pieces[c][6] = new Piece(false, this, c, 6, "shield");
				}
				for (int c = 1; c < 8; c = c + 2) {
					pieces[c][5] = new Piece(false, this, c, 5, "bomb");
				}

			}
		}

		public Piece pieceAt(int x, int y) {
			if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
				return null;
			}
			else {
				return this.pieces[x][y];
			}
		}
		public boolean canSelect(int x, int y) {
			if (this.isFireTurn == true) {
				if ((this.pieceAt(x, y) != null) && (this.pieceAt(x, y).isFire() == true)) {
					if (this.isSelected == null) {
						return true;
					}
					else if (this.hasMovedBoard == false) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					if ((this.isSelected != null) && (this.hasMovedBoard == false) && (this.pieceAt(x, y) == null) && (this.validMove(this.xSelected, this.ySelected, x, y))) {
						return true;
					}
					else if ((this.isSelected != null) && (isSelected.hasCaptured() == true) && (this.pieceAt(x, y) == null) && (this.validMove(this.xSelected, this.ySelected, x, y))) {
						return true;
					}
					else {
						return false;
					}
				}
			}
			else {
				if ((this.pieceAt(x, y) != null) && (this.pieceAt(x, y).isFire() == false)) {
					if (this.isSelected == null) {
						return true;
					}
					else if (this.hasMovedBoard == false) {
						return true;
					}
					else {
						return false;
					}
				}
				else {
					if ((this.isSelected != null) && (this.hasMovedBoard == false) && (this.pieceAt(x, y) == null) && (this.validMove(this.xSelected, this.ySelected, x, y))) {
						return true;
					}
					else if ((this.isSelected != null) && (isSelected.hasCaptured() == true) && (this.pieceAt(x, y) == null) && (this.validMove(this.xSelected, this.ySelected, x, y))) {
						return true;
					}
					else {
						return false;
					}
				}
			}
		}
		private boolean validMove(int xi, int yi, int xf, int yf) {
			Piece original = this.pieceAt(xi, yi);
			Piece next = this.pieceAt(xf, yf);
			int dx = xf - xi;
			int dy = yf - yi;
			if (this.cantMove == true) {
				return false;
			}
			if ((Math.abs(dx) > 2) || (Math.abs(dy) > 2)) {
				return false;
			}
			else if (Math.abs(dx) != Math.abs(dy)) {
				return false;
			}
			else if (this.isFireTurn == true) {
				if ((!original.isKing()) && (dy < 0)) {
					return false;
				}
				else if ((dy == 1) && (next == null)) {
					return true;
				}
				else if (Math.abs(dy) == 2) {
					return canCapture(xi, yi, xf, yf);
				}
				else {
					return false;
				}
			}
			else if (this.isFireTurn == false) {
				if ((!original.isKing()) && (dy > 0)) {
					return false;
				}
				else if ((dy == -1) && (next == null)) {
					return true;
				}
				else if (Math.abs(dy) == 2) {
					return canCapture(xi, yi, xf, yf);
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		private boolean canCapture(int xi, int yi, int xf, int yf) {
			int capx = ((xi + xf) / 2);
			int capy = ((yi + yf) / 2);
			Piece capturePiece = this.pieceAt(capx, capy);
			if (capturePiece == null) {
				return false;
			}
			else if ((this.isFireTurn == true) && (capturePiece.isFire() == false)) {
				this.playerHasCaptured = true;
				return true;
			}
			else if ((this.isFireTurn == false) && (capturePiece.isFire() == true)) {
				this.playerHasCaptured = true;
				return true;
			}
			else {
				return false;
			}
		}
		public void select(int x, int y) {
			if (this.pieceAt(x, y) == null) {
				if ((this.isSelected != null) && (this.isSelected.isBomb())) {
					this.isSelected.move(x, y);
					xSelected = 10;
					ySelected = 10;
					isSelected = null;
					this.hasMovedBoard = true;
				}
				if (this.isSelected != null) {
				this.hasMovedBoard = true;
				this.isSelected.move(x, y);
				}
				this.isSelected = this.pieceAt(x, y);
				xSelected = x;
				ySelected = y;
			}
			else {
				this.isSelected = this.pieceAt(x, y);
				xSelected = x;
				ySelected = y;
			}
		}
		public void place(Piece p, int x, int y) {
			if ((p == null) || (x < 0) || (x > 7) || (y < 0) || (y > 7)) {
				return;
			}
			else {
				this.pieces[x][y] = p;
			}
		}
		public Piece remove(int x, int y) {
			if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
				System.out.println("Coordinates out of bounds!");
				return null;
			}
			else if (this.pieces[x][y] == null) {
				System.out.println("No piece to remove!");
				return null;
			}
			else {
				Piece removed = this.pieces[x][y];
				this.pieces[x][y] = null;
				return removed;
			}
		}
		public boolean canEndTurn() {
			if ((this.hasMovedBoard == true) || (this.playerHasCaptured == true)) {
				return true;
			}
			else {
				return false;
			}
		}
		public void endTurn() {
			this.isFireTurn = !this.isFireTurn;
			this.xSelected = 10;
			this.ySelected = 10;
			if (this.isSelected != null) {
			this.isSelected.hasCaptured();
			this.isSelected = null;
			}
			this.hasMovedBoard = false;
			this.playerHasCaptured = false;
			this.winner();
			this.cantMove = false;
		}
		public String winner() {
			int firePieces = this.amountPieces(true);
			int waterPieces = this.amountPieces(false);
			if ((firePieces == 0) && (waterPieces == 0)){
				return "No one";
			}
			else if (waterPieces == 0) {
				return "Fire";
			}
			else if (firePieces == 0) {
				return "Water";
			}
			else {
				return null;
			}
		}
		private int amountPieces(boolean isFire) {
			int counter = 0;
			for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++) {
            		if (pieces[i][j] == null) {

            		}
            		else if (pieces[i][j].isFire() == isFire) {
            			counter = counter + 1;
            		}
            	}
			}
			return counter;
		}
} 

