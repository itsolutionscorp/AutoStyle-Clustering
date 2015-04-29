public class Board {
	private Piece[][] pieces;
	private int fireOrWater; //0 for fire piece, 1 for water piece
	private Piece selectedPiece;
	private int selectedX;
	private int selectedY;
	private int prevX;
	private int prevY;


	public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);      
		Board b = new Board(false);

		boolean[][] selected = new boolean[8][8];

		while(true) {
			b.updateBoard();

            if (StdDrawPlus.mousePressed()) {
                double row = StdDrawPlus.mouseX(); //x coordinate of mouse represents row
                double col = StdDrawPlus.mouseY(); //y coordinate of mouse represents column
                int r = (int) row;
                int c = (int) col;
                selected[r][c] = true;
                if (b.canSelect(r, c)) {
               		b.select(r, c);
               	}
            }    

            if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }

            if (b.winner() != null) {
            	System.out.println(b.winner());
            }

            StdDrawPlus.show(20);
        }
	}

	public Board(boolean shouldBeEmpty) {
		fireOrWater = 0;
		selectedPiece = null;
		selectedX = 10;
		selectedY = 10;

		prevX = 10;
		prevY = 10;

		pieces =  new Piece[8][8]; //creates 8x8 array for storing pieces


        //create pieces in default layout
        if (!shouldBeEmpty) {
        	//fire pieces
        	for (int x = 0; x < 8; x+=2) {
        		pieces[x][0] = new Piece(true, this, x, 0, "pawn");
			}
			for (int x = 1; x < 8; x+=2) {
				pieces[x][1] = new Piece(true, this, x, 1, "shield");
			}
			for (int x = 0; x < 8; x+=2) {
				pieces[x][2] = new Piece(true, this, x, 2, "bomb");
			}

			//water pieces
			for (int x = 1; x < 8; x+=2) {
				pieces[x][7] = new Piece(false, this, x, 7, "pawn");
			}
			for (int x = 0; x < 8; x+=2) {
				pieces[x][6] = new Piece(false, this, x, 6, "shield");
			}
			for (int x = 1; x < 8; x+=2) {
				pieces[x][5] = new Piece(false, this, x, 5, "bomb");
			}

        }
    }

	public Piece pieceAt(int x, int y) {
		if (x < 8 && x >= 0 && y < 8 && y >= 0) {
			return pieces[x][y];
		}
		return null;
	}

	public boolean canSelect(int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return false;
		}
		Piece p = pieceAt(x, y);

		//handle selecting a piece that will later be moved
		if (p != null && (fireOrWater == p.side())) {
			return ((selectedPiece == null) || (prevX == selectedX && prevY == selectedY));
		}

		//handle wrong player turn
		else if (p != null && (fireOrWater != p.side())) {
			return false;
		}

		//handle selecting an empty location for the currently selected piece to move to
		else {
			if (p == null && selectedPiece != null && validMove(selectedX, selectedY, x, y)) {
				return ((prevX == selectedX && prevY == selectedY) ||
					 (selectedPiece.hasCaptured() && isCaptureMove(selectedX, selectedY, x, y)));
			}
			return false;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		/*account for diagonal moves and captures*/
		boolean fireMove = ((xi + 1 == xf || xi - 1 == xf) && (yi + 1 == yf)); //upward movement
		boolean waterMove = ((xi + 1 == xf || xi - 1 == xf) && (yi - 1 == yf)); //downward movement
		
		if (selectedPiece.isKing()) {
			return (fireMove || waterMove || isCaptureMove(xi, yi, xf, yf));
		}
		else if (fireOrWater == 0) {
			return (fireMove || isCaptureMove(xi, yi, xf, yf));
		}
		else {
			return (waterMove || isCaptureMove(xi, yi, xf, yf));
		}
	}

	private boolean isCaptureMove(int xi, int yi, int xf, int yf) {
		boolean fireCapture = ((xi + 2 == xf || xi - 2 == xf) && (yi + 2 == yf)); //upward capture
		boolean waterCapture = ((xi + 2 == xf || xi - 2 == xf) && (yi - 2 == yf)); //downward capture

		int midX = (xi + xf) / 2;
		int midY = (yi + yf) / 2;

		Piece p = pieceAt(midX, midY);
		boolean pieceInBetween = (p != null && selectedPiece.side() != p.side() && 
				p != selectedPiece && (midX != xi || midY != yi));
		
		if (selectedPiece.isKing()) {
			return ((fireCapture || waterCapture) && pieceInBetween);
		}
		else if (fireOrWater == 0) {
			return (fireCapture && pieceInBetween);
		}
		else {
			return (waterCapture && pieceInBetween);
		}
	}


	public void select(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p == null) {
			selectedPiece.move(x, y);
		}
		else {
			selectedPiece = pieceAt(x, y);		
			//set coordinates of the piece at time of initial selection	
			prevX = x;
			prevY = y;
		}
		selectedX = x;
		selectedY = y;
	}

	public void place(Piece p, int x, int y) {
		if ((x < 8 && x >= 0 && y < 8 && y >= 0) && p != null) {
			if (pieces[x][y] != null) {
				remove(x, y);
			}
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		Piece removed = pieceAt(x, y);
		pieces[x][y] = null;
		return removed;
	}

	public boolean canEndTurn() {
		//true only if player has moved a piece (capturing implies movement)
		return (selectedPiece != null && (prevX != selectedX || prevY != selectedY));
	}

	public void endTurn() {
		if (fireOrWater == 0) {
			fireOrWater = 1;
		}
		else {
			fireOrWater = 0;
		}
		selectedPiece.doneCapturing();
		selectedPiece = null;
		selectedX = 10;
		selectedY = 10;
		prevX = 11;
		prevY = 11;
	}

	public String winner() {
		int fireCounter = 0;
		int waterCounter = 0;
		for (int x = 0; x < 8; x++) {
        	for (int y = 0; y < 8; y++) {
        		Piece p = pieceAt(x, y);
        		if (p != null) {
        			if (p.side() == 0) {
        				fireCounter += 1;
        			}
        			else {
        				waterCounter += 1;
        			}
        		}
        	}
        }

        if (fireCounter != 0 && waterCounter == 0) {
        	return "Fire";
        }
        else if (fireCounter == 0 && waterCounter != 0) {
        	return "Water";
        }
        else if (fireCounter == 0 && fireCounter == waterCounter) {
        	return "No one";
        }
        else {
        	return null;
        }
	}



	/* SELF-IMPLEMENTED METHODS FOR UPDATING BOARD */

	private void updateBoard() {
		for (int x = 0; x < 8; x++) {
        	for (int y = 0; y < 8; y++) {
            	if (selectedPiece != null && x == selectedX && y == selectedY) {
   					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
   				}
   				else if ((x + y) % 2 == 0) {
		        	StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
		        }
		        else {
		        	StdDrawPlus.setPenColor(StdDrawPlus.BOOK_BLUE);
		        }
       			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
   			}
       	}

    	for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
       			Piece p = pieceAt(x, y);
	       		if (p != null) {
					drawPiece(p, x, y);
				}
			}
		}
	}

	private void drawPiece(Piece p, int x, int y) {
		if (p.isFire()) {
			if (p.isBomb()) {
				if (p.isKing()) {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-fire-crowned.png", 1, 1);
				}
				else {StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-fire.png", 1, 1);}
			}
			else if (p.isShield()) {				
				if (p.isKing()) {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-fire-crowned.png", 1, 1);
				}
				else {StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-fire.png", 1, 1);}
			}
			else {
				if (p.isKing()) {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-fire-crowned.png", 1, 1);
				}
				else {StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-fire.png", 1, 1);}
			}
		}
		else {
			if (p.isBomb()) {
				if (p.isKing()) {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-water-crowned.png", 1, 1);
				}
				else {StdDrawPlus.picture(x + 0.5, y + 0.5, "img/bomb-water.png", 1, 1);}
			}
			else if (p.isShield()) {
				if (p.isKing()) {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-water-crowned.png", 1, 1);
				}
				else {StdDrawPlus.picture(x + 0.5, y + 0.5, "img/shield-water.png", 1, 1);}
			}
			else {
				if (p.isKing()) {
					StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-water-crowned.png", 1, 1);
				}
				else {StdDrawPlus.picture(x + 0.5, y + 0.5, "img/pawn-water.png", 1, 1);}
			}
		}
	}


}