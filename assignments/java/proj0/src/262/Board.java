public class Board {
	private int numSquaresSide;
	private Piece[][] squares;
	private int turn; //0 is fire, 1 is water
	private Piece selected;
	private int selectedX;
	private int selectedY;
	private boolean hasMoved;

	public Board(boolean shouldBeEmpty) {
		numSquaresSide = 8;
		squares = new Piece[numSquaresSide][numSquaresSide];
		if (!shouldBeEmpty) {
			//add all pieces in right place
			for (int row = 0; row < squares.length; row++) {
				for (int col = 0; col < squares[row].length; col++) {
					if (col % 2 == 0) {
						if (row == 0) {
							Piece p = new Piece(true, this, col, row, "pawn");								
						} else if (row == 2) {
							Piece p = new Piece(true, this, col, row, "bomb");
						} else if (row == 6) {
							Piece p = new Piece(false, this, col, row, "shield");
						}
					} else {
						if (row == 1) {
							Piece p = new Piece(true, this, col, row, "shield");
						} else if (row == 5) {
							Piece p = new Piece(false, this, col, row, "bomb");
						} else if (row == 7) {
							Piece p = new Piece(false, this, col, row, "pawn");	
						}
					}
				}
			}
		}
		turn = 0;
	}

	public Piece pieceAt(int x, int y) {
		if (x < 0 || y < 0 || x >= numSquaresSide || y >= numSquaresSide) {
			return null;
		}
		return squares[x][y];
	}

	public boolean canSelect(int x, int y) {
		Piece p = pieceAt(x, y);
		//selecting a piece
		if (p != null && p.side() == turn && (selected == null || !hasMoved)) { 
			return true;
		} else if (selected != null) { // selecting a square
			if (!hasMoved && validMove(selectedX, selectedY, x, y)) {
				return true;
			} else if (selected.hasCaptured() && validCapture(selectedX, selectedY, x, y)) {
				return true;
			}
		} 
		return false;
	}

	private boolean validCapture(int xi, int yi, int xf, int yf) {
		Piece p = pieceAt(xi, yi);
		//no need to test if p is null because we already do make sure that selected != null
		// For bombs, hasCaptured() should make sure that a bomb can't capture anymore
		if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2)) {
			if (!p.isKing() && ((p.side() == 0 && yf <= yi) || (p.side() == 1 && yf >= yi))) {
				return false;
			}
			int avgX = (xf + xi) / 2; 
			int avgY = (yf + yi) / 2;
			Piece opp = pieceAt(avgX, avgY);
			if (opp != null && opp.side() != turn) {
				return true;
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece p = pieceAt(xi, yi);
		Piece space = pieceAt(xf, yf);
		//if there is already a piece there, return false
		if (space != null) {
			return false;
		}
		//typical moves of fire
		if (p.side() == 0 || p.isKing()) {
			if ((xf == xi - 1 || xf == xi + 1) && (yf == yi + 1)) {
				return true;
			}
		}
		//typical moves of water
		if (p.side() == 1 || p.isKing()) {
			if ((xf == xi - 1 || xf == xi + 1) && (yf == yi - 1)) {
				return true;
			}
		}
		return validCapture(xi, yi, xf, yf);
	}

	public void select(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p != null) {
			selected = p;
			selectedX = x;
			selectedY = y;
		} else {
			selected.move(x, y);
			selectedX = x;
			selectedY = y;
			hasMoved = true;
		}
	}

	public void place(Piece p, int x, int y) {
		if (x < 0 || y < 0 || x >= numSquaresSide || y >= numSquaresSide) {
			return;
		}
		//not sure if I need this yet, but will see
		if (pieceAt(x, y) != null) {
			squares[x][y] = p;
		}
		squares[x][y] = p;
	}

	public Piece remove(int x, int y) {
		if (x < 0 || y < 0 || x >= numSquaresSide || y >= numSquaresSide) {
			System.out.println("Not a valid square on the board.");
			return null;
		}
		Piece toRemove = squares[x][y];
		if (toRemove == null) {
			System.out.println("There is no piece at this square.");
			return null;
		}
		squares[x][y] = null;
		return toRemove;
	}

	public boolean canEndTurn() {
		if (hasMoved) {
			return true;
		}
		return false; 
	}

	public void endTurn() {
		turn = 1 - turn;
		hasMoved = false;
		selected.doneCapturing();
		selected = null; //makes sure that pieces can be selected
	}

	public String winner() {
		int numFirePieces = 0;
		int numWaterPieces = 0;
		for (int i = 0; i < numSquaresSide; i++) {
			for (int j = 0; j < numSquaresSide; j++) {
				Piece p = pieceAt(i, j);
				if (p != null) {
					if (p.side() == 0) {
						numFirePieces += 1;
					} else {
						numWaterPieces += 1;
					}
				}
			}
		}
		if (numFirePieces == 0 && numWaterPieces == 0) {
			return "No one";
		}
		if (numFirePieces == 0) {
			return "Water";
		} else if (numWaterPieces == 0) {
			return "Fire";
		}
		return null;
	}

	private static void drawBoard(Board b) {
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) 
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece p = b.pieceAt(i, j);
                if (p != null) {
                	if (p.isFire()) {
                		if (p.isShield()) {
                			if (p.isKing()) {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	                		} else {
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	                		}
	                	} else if (p.isBomb()) {
	                		if (p.isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	                		} else {
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	                		}
	                	} else { //will be a pawn in this case
							if (p.isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	                		} else {
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	                		}
	                	}
	                } else {
	                	if (p.isShield()) {
	                		if (p.isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	                		} else {
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	                		}
	                	} else if (p.isBomb()) {
	                		if (p.isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	                		} else {
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                		}
	                	} else { //will be a pawn in this case
							if (p.isKing()) {
	                			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	                		} else {
	                    		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                		}
	                	}
	                }
                }
            }
        }
	}

	public static void main(String[] args) {
		Board b = new Board(false);
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while (b.winner() == null) {
            drawBoard(b);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int xCoord = (int) x;
                int yCoord = (int) y;
                if (b.canSelect(xCoord, yCoord)) {
                	b.select(xCoord, yCoord);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }            
            StdDrawPlus.show(100);
        }
        System.out.println(b.winner());
        System.exit(0);
	}
}