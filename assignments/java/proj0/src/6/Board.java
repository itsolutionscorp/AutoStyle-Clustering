/** proj0 board class and methods
 * Zach Strenfel
 * CS61B
 */

public class Board {



	/* create an array for the intial pieces */
	private static int boardSize = 8;
	private Piece[][] pieces = new Piece[boardSize][boardSize];

	/* Current player to move 
	 * 0 is fire, 1 is water */
	private int player = 0;

	/* Boolean that informs whether the player has currently selected a piece*/
	private boolean pMoved = false; 
	private boolean captured = false;

	/*the currently selected piece*/
	private Piece selected;
	private int selectedX;
	private int selectedY; 


	/* creates a new board with info default config or empty */
	public Board(boolean shouldBeEmpty) {
		String pieceType = null;

		/* code to intialize all the pieces in starting position */
		if(shouldBeEmpty == true) {
			emptyPieces(boardSize);
		} else {

		/*~~~Fire Pieces~~~*/
			for(int i=0; i < boardSize; i ++) {
				for(int j=0; j<3; j++) {

					if(j==0) {
						pieceType = "pawn";
					} else if(j == 1){
						pieceType = "shield";
					} else if(j == 2) {
						pieceType = "bomb";
					}

					if(i % 2 == 0){
						if(j % 2 == 0) pieces[i][j] = new Piece(true, this, i, j, pieceType);
						} else {
							if(j % 2 != 0) pieces[i][j] = new Piece(true, this, i, j, pieceType);
						}

				}
			}

		/*~~~~Water Pieces~~~~*/

			for(int i=0; i < boardSize; i ++) {
				for(int j=5; j<boardSize; j++) {

					if(j==7) {
						pieceType = "pawn";
					} else if(j == 6){
						pieceType = "shield";
					} else if(j == 5) {
						pieceType = "bomb";
					}

					if(i % 2 == 0){
						if(j % 2 == 0) pieces[i][j] = new Piece(false, this, i, j, pieceType);
						} else {
							if(j % 2 != 0) pieces[i][j] = new Piece(false, this, i, j, pieceType);
						}

				}
			}

		}
	}

	/* returns an Pieces array of null */
	private void emptyPieces(int N) {
		for(int i=0; i < boardSize; i ++) {
			for(int j=0; j<3; j++) {
				pieces[i][j] = null;
			}
		}
	}

	/* gets piece at position(x,y) or null */
	public Piece pieceAt(int x, int y) {
		if(x >= boardSize || y >= boardSize) return null;
		else if(x < 0 || y < 0) return null;
		else if(pieces[x][y] == null) return null;
		else return pieces[x][y];
	}

	/* check if player selected a viable piece */
	public boolean canSelect(int x, int y) {

		if(pMoved == false) { 
			if(pieceAt(x,y) != null && pieceAt(x,y).side() == player) return true;
			else if(pieceAt(x,y) == null && selected != null) {
				if (validMove(selectedX, selectedY, x, y)) return true;
				else return false;
			} else return false;
		} else if(selected != null && captured == true && pieceAt(x,y) == null) {
			if(Math.abs(selectedX - x) == 2){
				if (validMove(selectedX, selectedY, x, y)) return true;
					else return false;
				} else return false;
		} else return false;
	}

	/* check if player selected a viable move */
	private boolean validMove(int xi, int yi, int xf, int yf) {
			if(pieceAt(xi, yi) == null) return false;

			else if(pieceAt(xi,yi).isKing()) {
				if(Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1) return true;
				else if(Math.abs(xi - xf) == 2 && Math.abs(yi - yf) == 2){
					if(pieceAt(xi + ((xi - xf)/-2), yi +((yi - yf)/-2)) != null && player != pieceAt(xi + ((xi - xf)/-2), yi + ((yi - yf)/-2)).side()) return true;
					else return false;
				} else return false;
			}


			else if(player == 0){
				if(Math.abs(xi - xf) == 1 && (yi - yf) == -1) return true;
				else if(Math.abs(xi - xf) == 2 && (yi - yf) == -2) {
					if(pieceAt(xi + ((xi - xf)/-2), yi + 1) != null && player != pieceAt(xi + ((xi - xf)/-2), yi + 1).side()) return true;
					else return false;
				} else return false;
			} else {
				if(Math.abs(xi - xf) == 1 && (yi - yf) == 1) return true;
				else if(Math.abs(xi - xf) == 2 && (yi - yf) == 2) {
					if(pieceAt(xi + ((xi - xf)/-2), yi - 1) != null && player != pieceAt(xi + ((xi - xf)/-2), yi - 1).side()) return true;
					else return false;
				} else return false;
			} 
		}

	/* select the piece at (x,y) if possible */
	public void select(int x, int y) {
		/* select the other piece if it's the same type */
		if(pieceAt(x,y) != null){
			selected = pieceAt(x,y);
			selectedX = x;
			selectedY = y;
		} 
		/* move to a new space if open */
		else{
			if(Math.abs(selectedX - x) == 2){
				captured = true;
			}
			selected.move(x,y);
			pMoved = true;

		}
	}

	/* place piece at new location (x,y) if viable. replace other piece as needed */
	public void place(Piece p, int x, int y) {
		if(pieceAt(x,y) != null) {
			remove(x,y);
			pieces[x][y] = p;
		} else {
			pieces[x][y] = p;
			
			if(selected != null) {
				pieces[selectedX][selectedY] = null;
				selectedX = x;
				selectedY = y;
			}
		}
	}

	/* removes piece or displays appropriate message */
	public Piece remove(int x, int y) {
		Piece curr = pieceAt(x,y);
		if(pieceAt(x,y) != null) {
			if(pieceAt(x,y) == selected) {
				selected = null;
				pieces[x][y] = null; 
			} else pieces[x][y] = null;
			return curr;
		} return null;
	}




	/*	return whether a player can end their turn or not */
	public boolean canEndTurn() {
		if(pMoved == true) return true;
		else return false;
	}

	public void endTurn() {
		pMoved = false;

		if(selected != null){
			selected.doneCapturing();
			selected = null;
		}

		if(player == 0) player = 1;
		else player = 0;
	}

	// /*determine winner or return null */
	public String winner() {
		int fireCounter = 0;
		int waterCounter = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
            	if(pieces[i][j] != null) {
            		if(pieces[i][j].isFire()) fireCounter += 1;
            		else waterCounter += 1;
            	}
            }
        }

        if(waterCounter == 0 && fireCounter != 0) {
        	return "Fire";
        }
        else if(waterCounter != 0 && fireCounter == 0) {
        	return "Water";
        }
        else if (waterCounter == 0 && fireCounter == 0) {
        	return "No one";
        }
        else return null;
	}

/* ~~~~~~~~~~~~~~~~~~~~ GUI CODE ~~~~~~~~~~~~~~~~~~~~
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    
    /* draws the board and original config */
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                highlightSquare();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //empty board not workng
                if (pieceAt(i,j) != null) {
                	if(pieces[i][j].isFire()){
                		
                		/*for bomb */
                		if(pieces[i][j].isBomb()){
                			if(pieces[i][j].isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                			}
                		/*for shield*/
                		} else if(pieces[i][j].isShield()){
                			if(pieces[i][j].isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                			}
                		/* for pawn */
                		} else {
                			if(pieces[i][j].isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                			}
                		}
                	} else {
                		/*for bomb */
                		if(pieces[i][j].isBomb()){
                			if(pieces[i][j].isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                			}
                		/*for shield*/
                		} else if(pieces[i][j].isShield()){
                			if(pieces[i][j].isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                			}
                		/* for pawn */
                		} else {
                			if(pieces[i][j].isKing()){
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

    private void highlightSquare() {
    	StdDrawPlus.setPenColor(StdDrawPlus.PINK);
    	if(selected != null){
	    	StdDrawPlus.filledSquare(selectedX + .5, selectedY + .5, .5);
    	}
    }


	/** starts a GUI supported version of checkers */
	public static void main(String args[]) {
		
		Board game = new Board(false);
        StdDrawPlus.setXscale(0, boardSize);
        StdDrawPlus.setYscale(0, boardSize);

        while(true) {
            game.drawBoard(boardSize);
            if(StdDrawPlus.mousePressed()) {
                int squareX =(int) StdDrawPlus.mouseX();
                int squareY =(int) StdDrawPlus.mouseY();
                if(game.canSelect(squareX, squareY)){
                	game.select(squareX, squareY);
                }
            }
            if(StdDrawPlus.isSpacePressed()) {
            	if(game.canEndTurn()) {
            		game.endTurn();
            		System.out.println(game.player);
            	}
            }
            StdDrawPlus.show(100);
        }
	}
}

/* thats all folks ╰(.•́͜ʖ•̀.)╯ */







