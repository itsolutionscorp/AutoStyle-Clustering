public class Board {

    private Piece[][] pieces;
    private Piece selected;
    private int xSelected;
    private int ySelected;
    private boolean fireTurn;
    private boolean hasMoved;

	public Board(boolean shouldBeEmpty) {
		fireTurn = true;
		xSelected = -1;
		ySelected = -1;
		hasMoved = false;
		pieces = new Piece[8][8];
		int i = 0;
		if (shouldBeEmpty == false) {
			while (i < 8) {
				if (i % 2 == 0) {
					pieces[i][0] = new Piece(true, this, i, 0, "pawn");
					pieces[i][2] = new Piece(true, this, i, 2, "bomb");
					pieces[i][6] = new Piece(false, this, i, 6, "shield");
				}
				else {
					pieces[i][1] = new Piece(true, this, i, 1, "shield");
					pieces[i][5] = new Piece(false, this, i, 5, "bomb");
					pieces[i][7] = new Piece(false, this, i, 7, "pawn");			
				}
				i+=1;
			}
 		}
	}

	//Checks if anything is OutOfBounds when running methods
	private boolean isOutOfBounds(int x, int y) {
		if ((x < 0 || x > 7) || (y < 0 || y > 7)) {
			return true;
		}
		else {
			return false;
		}
	}

	public Piece pieceAt(int x, int y) {
		if (isOutOfBounds(x, y) == true || pieces[x][y] == null) {
			return null;
		}		
		else {
			return pieces[x][y];
		}
	}

	public boolean canSelect(int x, int y) {
		if (pieceAt(x, y) != null) {
			if (pieceAt(x, y).isFire() == fireTurn) {
				if ((selected == null) || (hasMoved == false)) {
					return true;
				}
				return false;
			}
		}
		else {	
			if (selected != null && hasMoved == false && (validMove(xSelected, ySelected, x, y) || validCapture(xSelected, ySelected, x, y))) {
				return true;
			} 
			else if (selected != null && selected.hasCaptured() && validCapture(xSelected, ySelected, x, y)) {
				return true;
			}
			return false;
		}
		return false;
	}

	//Add on to ValidMove to check for valid-captures
	private boolean validCapture(int xi, int yi, int xf, int yf) {
		if (pieceAt(xi, yi) == null) {
			return false;
		}
		if (pieceAt(xi, yi).isFire()) {
			if (pieceAt(xi, yi).isKing()) {
				if (pieceAt(xi - 1, yi - 1) != null && !pieceAt(xi - 1, yi - 1).isFire() && (xf == (xi - 2)) && (yf == (yi - 2)) && (pieceAt(xf, yf) == null)) {
					return true;
				}
				if (pieceAt(xi - 1, yi + 1) != null && !pieceAt(xi - 1, yi + 1).isFire() && (xf == (xi - 2)) && (yf == (yi + 2)) && (pieceAt(xf, yf) == null)) {
					return true;
				}
				if (pieceAt(xi + 1, yi - 1) != null && !pieceAt(xi + 1, yi - 1).isFire() && (xf == (xi + 2)) && (yf == (yi - 2)) && (pieceAt(xf, yf) == null)) {
					return true;
				}
				if (pieceAt(xi + 1, yi + 1) != null && !pieceAt(xi + 1, yi + 1).isFire() && (xf == (xi + 2)) && (yf == (yi + 2)) && (pieceAt(xf, yf) == null)) {
					return true;
				}
				return false;
			}
			else {
				if (pieceAt(xi - 1, yi + 1) != null && !pieceAt(xi - 1, yi + 1).isFire() && (xf == (xi - 2)) && (yf == (yi + 2)) && (pieceAt(xf, yf) == null)) {
					return true;
				}
				if (pieceAt(xi + 1, yi + 1) != null && !pieceAt(xi + 1, yi + 1).isFire() && (xf == (xi + 2)) && (yf == (yi + 2)) && (pieceAt(xf, yf) == null)) {
					return true;
				}
				return false;
			}	
		}
		else {
			if (pieceAt(xi, yi).isKing()) {
				if (pieceAt(xi - 1, yi - 1) != null && pieceAt(xi - 1, yi - 1).isFire() && (xf == (xi - 2)) && (yf == (yi - 2)) && (pieceAt(xf, yf) == null)) {
					return true;
				}
				if (pieceAt(xi - 1, yi + 1) != null && pieceAt(xi - 1, yi + 1).isFire() && (xf == (xi - 2)) && (yf == (yi + 2)) && (pieceAt(xf, yf) == null)) {
					return true;
				}
				if (pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 1, yi - 1).isFire() && (xf == (xi + 2)) && (yf == (yi - 2)) && (pieceAt(xf, yf) == null)) {
					return true;
				}
				if (pieceAt(xi + 1, yi + 1) != null && pieceAt(xi + 1, yi + 1).isFire() && (xf == (xi + 2)) && (yf == (yi + 2)) && (pieceAt(xf, yf) == null)) {
					return true;
				}
				return false;
			}
			else {
				if (pieceAt(xi - 1, yi - 1) != null && pieceAt(xi - 1, yi - 1).isFire() && (xf == (xi - 2)) && (yf == (yi - 2)) && (pieceAt(xf, yf) == null)) {
					return true;
				}
				if (pieceAt(xi + 1, yi - 1) != null && pieceAt(xi + 1, yi - 1).isFire() && (xf == (xi + 2)) && (yf == (yi - 2)) && (pieceAt(xf, yf) == null)) {
					return true;
				}
				return false;
			}	
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (pieceAt(xi, yi) == null) {
			return false;
		}
		if (pieceAt(xi, yi).isFire()) {
			if (pieceAt(xi, yi).isKing()) {
				if (pieceAt(xi - 1, yi - 1) == null && (xf == (xi - 1)) && (yf == (yi - 1))) {
					return true;
				}
				if (pieceAt(xi - 1, yi + 1) == null && (xf == (xi - 1)) && (yf == (yi + 1))) {
					return true;
				}
				if (pieceAt(xi + 1, yi - 1) == null && (xf == (xi + 1)) && (yf == (yi - 1))) {
					return true;
				}
				if (pieceAt(xi + 1, yi + 1) == null && (xf == (xi + 1)) && (yf == (yi + 1))) {
					return true;
				}
				return false;
			}
			else {
				if (pieceAt(xi - 1, yi + 1) == null && (xf == (xi - 1)) && (yf == (yi + 1))) {
					return true;
				}
				if (pieceAt(xi + 1, yi + 1) == null && (xf == (xi + 1)) && (yf == (yi + 1))) {
					return true;
				}
				return false;
			}
		}
		else {
			if (pieceAt(xi, yi).isKing()) {
				if (pieceAt(xi - 1, yi - 1) == null && (xf == (xi - 1)) && (yf == (yi - 1))) {
					return true;
				}
				if (pieceAt(xi - 1, yi + 1) == null && (xf == (xi - 1)) && (yf == (yi + 1))) {
					return true;
				}
				if (pieceAt(xi + 1, yi - 1) == null && (xf == (xi + 1)) && (yf == (yi - 1))) {
					return true;
				}
				if (pieceAt(xi + 1, yi + 1) == null && (xf == (xi + 1)) && (yf == (yi + 1))) {
					return true;
				}
				return false;
			}
			else {
				if (pieceAt(xi - 1, yi - 1) == null && (xf == (xi - 1)) && (yf == (yi - 1))) {
					return true;
				}
				if (pieceAt(xi + 1, yi - 1) == null && (xf == (xi + 1)) && (yf == (yi - 1))) {
					return true;
				}
				return false;
			}
		}
	}


	public void select(int x, int y) {
		if (pieceAt(x, y) == null) {
			xSelected = x;
			ySelected = y;
			selected.move(x, y);
			hasMoved = true;
			if (selected.isBomb() && selected.hasCaptured()) {
				xSelected = -1;
				ySelected = -1;
			}
		}
		else {
			selected = pieceAt(x, y);
			xSelected = x;
			ySelected = y;
		}
	}

	public void place(Piece p, int x, int y) {

		if ((isOutOfBounds(x, y) == false) || (p != null)) {
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if (isOutOfBounds(x, y)) {
			System.out.println("Invalid selection: Out of bounds!");
			return null;
		}
		if (pieceAt(x, y) == null) {
			System.out.println("No piece at (" + x + ", " + y + ")!");
			return null;
		}
		else {
			Piece holder = pieceAt(x, y);
			pieces[x][y] = null;
			return holder;
		}
	}

	public boolean canEndTurn() {
		if (hasMoved == true || (selected != null && selected.hasCaptured())) {
			return true;
		}
		else {
			return false;
		}
	}

	public void endTurn() {
		/*Handles switching of players and anything else
		 *that should happen at the end of a turn */
		xSelected = -1;
		ySelected = -1;
		hasMoved = false;
		selected.doneCapturing();
		selected = null;
		fireTurn = !fireTurn;
	}

	public String winner() {
		int N = 8;
		int fireCounter = 0;
		int waterCounter = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieceAt(i, j) != null) {
            		if (pieceAt(i, j).isFire()) {
            			fireCounter += 1;
            		}
            		else {
            		waterCounter += 1;
            		}
            	}
            }
        }
        if (fireCounter == 0 && waterCounter == 0) {
        	return "No one";
        }
        if (fireCounter	== 0) {
        	return "Water";
        }	
        if (waterCounter == 0) {
        	return "Fire";
        }
        return null;
	}

	//Gets the correct image for the pieces on the board.
    private String imgType(Piece p) {
    	String img = "img/";
    	if (p.isBomb() == true) {
    		img += "bomb-";
    	}
    	if (p.isShield() == true) {
    		img += "shield-";
    	}
    	if (p.isBomb() != true && p.isShield() != true) {
    		img += "pawn-";
    	}
    	if (p.isFire() == true) {
    		img += "fire";
    	}
    	if (p.isFire() == false) {
    		img += "water";
    	}
    	if (p.isKing() == true) {
    		img += "-crowned";
    	}
    	img += ".png";
    	return img;
    }

    private void drawBoard() {
    	//Adapted from StdDrawDemo
    	int N = 8;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	if (xSelected == i && ySelected == j) {
                		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	}
                	else {
                		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	}
                } else { 
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }

	private void update() {
		//Adapted from StdDrawDemo
		int N = 8;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //Drawing the pieces where they are
            	if (pieceAt(i, j) != null) {
            		StdDrawPlus.picture(i + 0.5, j + 0.5, imgType(pieceAt(i, j)), 1, 1);
            	}
            }
        }
	}

    public static void main(String[] args) {
        // Adapted from StdDrawDemo
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
        	b.drawBoard();
        	b.update();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int ix = (int) x;
                int iy = (int) y;
                if (b.canSelect(ix, iy)) {
                	b.select(ix, iy);
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
            }
            if (StdDrawPlus.isSpacePressed() == true && b.canEndTurn() == true) {
            	b.endTurn();
            	if (b.winner() != null) {
            		break;
            	}
            }         
            StdDrawPlus.show(10);
        }
        System.out.println(b.winner());
    }
}