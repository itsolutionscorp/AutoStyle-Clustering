public class Board {
	/** CS61b checker board
	* @author Tatevik Stepanyan
	*/
	private int size = 8;
	private Piece [][] board;
	private boolean isFirePlayer = true; //tracks player
	private Piece selectedPiece = null;
	private int selectedX;
	private int selectedY;
	private boolean hasCaptured;
	private boolean hasMoved;
	private boolean justCrowned;

	/* Construct a Board */
	public Board(boolean shouldBeEmpty){
		board = new Piece[size][size]; //initializes an empty board
		if (!shouldBeEmpty){ //a board with default configuration
			//place even-rowed pieces 
			for (int row = 0; row < 8; row = row + 2) {
				for (int col = 0; col < 8; col = col + 2){
					if (row == 0){
						place((new Piece (true, this, col, row, "pawn")), col, row);
					} else if (row == 2){
						place((new Piece (true, this, col, row, "bomb")), col, row);	
					} else if (row == 6){
						place((new Piece (false, this, col, row, "shield")), col, row);
					}
				}
			}

			//place odd-rowed pieces 
			for (int row = 1; row < 8; row = row + 2) {
				for (int col = 1; col < 8; col = col + 2){
					if (row == 1){
						place((new Piece(true, this, col, row, "shield")), col, row);
					} else if (row == 5){
						place((new Piece (false, this, col, row, "bomb")), col, row);
					} else if (row == 7){
						place((new Piece(false, this, col, row, "pawn")), col, row);	
					}
				}
			}
		}
		

	}

	/* Gets the piece at position (x, y) */
	public Piece pieceAt(int x, int y){
		if ((x < 0) || (x > 7) || (y < 0) || (y > 7)){
			return null;
		}
		else {
			return board[x][y];
		}
	}

	/* Returns true if the spot at (x, y) that can be selected by the current player */
	public boolean canSelect(int x, int y){
		boolean inBounds = ((x >= 0) && (x < 8) && (y >= 0) && (y < 8));
		boolean greySquare = (((x%2 == 0) && (y%2 == 0)) || ((x%2 != 0) || (y%2 != 0)));
		Piece p = pieceAt(x, y);
		if (inBounds && greySquare){ //(x, y) in bounds
			if (p != null) {
				if ((p.isFire() && isFirePlayer) || (!p.isFire() && !isFirePlayer)) { //there is a piece and it corresponds to the current player
					if ((selectedPiece == null && !hasCaptured) || (selectedPiece != null && !hasMoved)){ //a piece has not been selected yet or the selected piece has not moved yet. No moving is going on
					return true;
					}
				}	
			} else if (p == null){ //a blank grey square
				if (selectedPiece != null && !justCrowned && validMove(selectedX, selectedY, x, y)) { //a piece is trying to be moved in a valid way
					return true;
				} else if (justCrowned && validMove(selectedX, selectedY, x, y)){
					return true;
					}
				} else if(selectedPiece != null && selectedPiece.hasCaptured() && canCapture(selectedX, selectedY, x, y)){ //the piece is doing multiple captures
				  	return true;
				}
			}
		return false;
	}

	/* Returns true if piece at (xi, yi) can move to (xf, yf) or complete a capture to (xf, yf) */
	private boolean validMove(int xi, int yi, int xf, int yf){
		Piece p = pieceAt(xi, yi); //piece at current position
		Piece capturePiece = null;
		int dx = Math.abs(xf - xi);
		int dy = Math.abs(yf - yi);
		if (pieceAt(xf, yf) != null){ //if there is a piece at the target spot
			return false;
		} else if (p.isFire() && (yf > yi)){ //Fire pieces can only move up
			if ((dx == 1) && (dy == 1)){ //not trying to capture
				if (!hasMoved){
					return true;
				}
			} else if ((dx == 2) && (dy == 2)){ //trying to capture
				if (xf < xi) { //capturing to the left
					capturePiece = pieceAt((xi - 1), (yi + 1));
					if (capturePiece != null){ //appropriate capture piece available
						if (p.side() != capturePiece.side()){
						return true;
						}
					}
				}
				else if (xf > xi){ //capturing to the right
					capturePiece = pieceAt((xi + 1), (yi + 1));
					if (((capturePiece != null) && (p.side() != capturePiece.side()))){ //appropriate capture piece available
						return true;
					}
				}
			}
			return false;
		} else if (!p.isFire() && (yf < yi)){ //Water pieces can only move down
			if ((dx == 1) && (dy == 1)){ //not trying to capture
				if (!hasMoved){
					return true;
				}
			} else if ((dx == 2) && (dy == 2)){ //trying to capture
				if (xf < xi) { //capturing to the left
					capturePiece = pieceAt((xi - 1), (yi - 1));
					if (capturePiece != null){ //appropriate capture piece available
						if (p.side() != capturePiece.side()){
						return true;
						}
					}
				}
				else if (xf > xi){ //capturing to the right
					capturePiece = pieceAt((xi + 1), (yi - 1));
					if (capturePiece != null){ //appropriate capture piece available
						if (p.side() != capturePiece.side()){
						return true;
						}
					}
				}
			}
			return false;
		} else if (p.isKing()){ //Crowned pieces can move up or down
			if (pieceAt(xf, yf) != null){ //if there is a piece at landing spot
				return false;
			} else if ((dx == 1) && (dy == 1)){ //not trying to capture
				if (!hasMoved){
					return true;
				}
			} else if ((dx == 2) && (dy == 2) && (yf > yi)){ //capturing up
				if (xf < xi){ //capturing left
					capturePiece = pieceAt((xi - 1), (yi + 1));
					if (capturePiece != null){ //appropriate capture piece available
						if (p.side() != capturePiece.side()){
						return true;
						}
					}
				} else if (xf > xi){ //capturing right
					capturePiece = pieceAt((xi + 1), (yi + 1));
					if (capturePiece != null){ //appropriate capture piece available
						if (p.side() != capturePiece.side()){
						return true;
						}
					}
				}
			} else if ((dx == 2) && (dy == 2) && (yf < yi)){ //capturing down
				if (xf < xi){ //capturing left
					capturePiece = pieceAt((xi - 1), (yi - 1));
					if (capturePiece != null){ //appropriate capture piece available
						if (p.side() != capturePiece.side()){
						return true;
						}
					}
				} else if (xf > xi){ //capturing right
					capturePiece = pieceAt((xi + 1), (yi - 1));
					if (capturePiece != null){ //appropriate capture piece available
						if (p.side() != capturePiece.side()){
						return true;
						}
					}
				}
				
			}
		}	
		return false;
	}

	/* Returns whether or not a piece can capture another piece by moving to (x, y)*/
	private boolean canCapture(int xi, int yi, int xf, int yf){
		if (Math.abs(yf - yi) == 2 && Math.abs(xf - xi) == 2 && validMove(xi, yi, xf, yf)){
			return true;
		}
		return false;
	}

	/* Selects piece at (x, y) assuming player canSelect */
	public void select(int x, int y){
		Piece temp = pieceAt(x, y); //newly selected piece
		if ((temp == null) && (selectedPiece != null)){ //if a piece was already selected and is trying to be moved
			if ((Math.abs(x - selectedX) == 2) && (Math.abs(y - selectedY) == 2)){ //tryng to capture
				hasCaptured = true;
			}
			selectedPiece.move(x, y); //moves piece
			if ((selectedY != 0 && y == 0) || (selectedY != 7 && y == 7)){ //if the original piece was crowned in this move 
				justCrowned = true;
			}
			hasMoved = true; //the new piece at (x,y) has just moved
			selectedPiece = pieceAt(x, y); //reassigns the currently selected piece 
			selectedX = x;
			selectedY = y;
			return;
		} else {
			selectedPiece = temp; //if there is no move, just update the currently selected piece
			selectedX = x;
			selectedY = y;
		}
	}

	/* Places p at (x, y) */
	public void place(Piece p, int x, int y){
		boolean inBounds = ((x >= 0) && (x < 8) && (y >= 0) && (y < 8));
		boolean greySquare = (((x%2 == 0) && (y%2 == 0)) || ((x%2 != 0) && (y%2 != 0)));
		if (!inBounds || !greySquare || p == null){
			return;
		}
		board[x][y] = p;
	}

	/* Removes piece at (x, y) and returns it */
	public Piece remove(int x, int y){
		boolean inBounds = ((x >= 0) || (x < 8) || (y >= 0) || (y < 8));
		boolean greySquare = (((x%2 == 0) && (y%2 == 0)) || ((x%2 != 0) || (y%2 != 0)));
		if (!inBounds || !greySquare){
			System.out.println("Coordinates out of bound");
			return null;
		} else if (pieceAt(x, y) == null){
			System.out.println("No piece exists at these coordinates");
			return null;
		} else {
			Piece removedPiece = pieceAt(x, y);
			board[x][y] = null;
			selectedPiece = null;
			return removedPiece;
		}
	}

	/* Returns whether or not current player can end their turn */
	public boolean canEndTurn(){
		if (hasCaptured || ((selectedPiece != null) && (hasMoved || selectedPiece.hasCaptured()))) {
			return true;
		}
		return false;
	}

	/* Turn is ended and players switched */
	public void endTurn(){
		//Assumes player can end turn
		isFirePlayer = !isFirePlayer;
		if (selectedPiece != null){
			selectedPiece.doneCapturing();
		}
		selectedPiece = null;
		hasMoved = false;
		hasCaptured = false;
		justCrowned = false;
	}

	/* Returns the winner of the game */
	public String winner(){
		int firePieces = 0;
		int waterPieces = 0;
		for (int row = 0; row < 8; row = row + 1) {
			for (int col = 0; col < 8; col = col + 1){
				if ((pieceAt(row, col) != null) && pieceAt(row,col).isFire()){
					firePieces = firePieces + 1;
				} else if ((pieceAt(row, col) != null) && !pieceAt(row,col).isFire()) {
					waterPieces = waterPieces + 1;
				}
			}
		}
		if ((firePieces == 0) && (waterPieces == 0)){
			return "No one";
		} else if (firePieces == 0){
			return "Water";
		} else if (waterPieces == 0){
			return "Fire";
		} else {
			return null;
		}
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece currentPiece = pieceAt(i, j);
                if (currentPiece != null) {
                	if (currentPiece.isFire()){ //Fire piece
                		if (currentPiece.isBomb()){ //A bomb
                			if (currentPiece.isKing()){ //Special image for kings
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                			} else { // A regular bomb
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                			}

                		} else if (currentPiece.isShield()){ //A shield
                			if (currentPiece.isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                			}
                		} else { //Must be a pawn
                			if (currentPiece.isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                			}
                		}
                	} else if (!currentPiece.isFire()){ //Water piece
                		if (currentPiece.isBomb()){ //A bomb
                			if (currentPiece.isKing()){ //Special image for kings
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                			} else { // A regular bomb
                				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                			}

                		} else if (currentPiece.isShield()){ //A shield
                			if (currentPiece.isKing()){
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                			} else {
                				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                			}
                		} else { //Must be a pawn
                			if (currentPiece.isKing()) {
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

	public static void main(String[] args){
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        b.drawBoard(N);

        while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)){
                	b.select((int) x, (int) y);     	
                	StdDrawPlus.filledSquare((int) x + .5, (int) y + .5, .5);
            	}
            } if (StdDrawPlus.isSpacePressed()){
            	if (b.canEndTurn()){
            		b.endTurn();
            	}
            }            
            StdDrawPlus.show(100);
        }

	}

	//** Sources **//
	//http://stackoverflow.com/questions/13832880/initialize-2d-array
	//Andrew Huang helped with basic autograder test 4
	//lab assistant helped with a bug in canCapture
}