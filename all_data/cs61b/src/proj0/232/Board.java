/**
 * @author Chenkun Zheng
 */



public class Board {

	/* variables
	 */
	private int size = 8;
	private Piece[][] pieces = new Piece[size][size];
	private boolean[][] selected = new boolean[size][size];
	private boolean hasSelected = false;  /* track if any selected pos */
	private boolean hasMoved = false;  /* track whether moved any piece */
	private int xselected = -1; /* track the current selected pos */
	private int yselected = -1; /* track the current selected pos */
	private int numf = 0;  /* track No of fire pieces */
	private int numw = 0;  /* track No of water pieces */
	private boolean isFireturn = true; /* track the game turn */


	/* The constructor of Board, if true, initialize an empty board,
	 * otherwise initialize a Board with default configuration (btw initialize piecesarray)
	 * an empty board for testing purposes
	 */
	public Board(boolean shouldBeEmpty) {
		/* draw empty board */
		if (shouldBeEmpty) {
			this.drBoard();
		}
		/* initialize the default configuration */
		else {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if ((i + j) % 2 == 0) {
						if (j == 0) {
							pieces[i][j] = new Piece(true, this, i, j, "pawn");
							numf += 1;
						}
						if (j == 1) {
							pieces[i][j] = new Piece(true, this, i, j, "shield");
							numf += 1;
						}
						if (j == 2) {
							pieces[i][j] = new Piece(true, this, i, j, "bomb");
							numf += 1;
						}

						if (j == size - 1) {
							pieces[i][j] = new Piece(false, this, i, j, "pawn");
							numw += 1;
						}
						if (j == size - 2) {
							pieces[i][j] = new Piece(false, this, i, j, "shield");
							numw += 1;
						}
						if (j == size - 3) {
							pieces[i][j] = new Piece(false, this, i, j, "bomb");
							numw += 1;
						}
					}
				}
			}
			this.drBoard();
		}
	}

    /* Draw the board at current state
     */
	private void drBoard() {
		StdDrawPlus.setXscale(0, size);
		StdDrawPlus.setYscale(0, size);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				/* fill the color in the square */
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (selected[i][j]) {
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				}

				/* fill pics in the square */
				if (pieces[i][j] != null) {

					/* fire pieces */
					if (pieces[i][j].isFire()) {
						/* crowned */
						if (pieces[i][j].isKing()) {
							if (pieces[i][j].isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
							}
							else if (pieces[i][j].isShield()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
							}
						}
						/* uncrowned */
						else {
							if (pieces[i][j].isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
							}
							else if (pieces[i][j].isShield()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
							}
						}
					}


                    /* water pieces */
					else {

						/* crowned */
						if (pieces[i][j].isKing()) {
							if (pieces[i][j].isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
							}
							else if (pieces[i][j].isShield()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
							}
						}
						/* uncrowned */
						else {
							if (pieces[i][j].isBomb()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
							}
							else if (pieces[i][j].isShield()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
							}
						}
					}
				}
			}
		}
	}

    /* get the piece at x,y on the board,
     * or null if no piece
     * or null if x,y out of bounds
     */
	public Piece pieceAt(int x, int y) {
		if (x > size - 1 || x < 0 || y > size -1 || y < 0) {
			return null;
		}
		else {
			return pieces[x][y];
		}
	}

	/* return true if piece at xi,yi
	 * can either move or capture to xf,yf
	 * only check if the movement itself is valid
	 * only check the geometric rules!!!!
	 */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xi > size - 1 || xi < 0 || yi > size -1 || yi < 0
			|| xf > size - 1 || xf < 0 || yf > size -1 || yf < 0) {
			return false;
		}
		int xmove = xf - xi;
		int ymove = yf - yi;
		/* valid move, no capturing */
		if (Math.abs(xmove) == 1 && Math.abs(ymove) == 1 
			&& this.pieceAt(xi, yi).hasCaptured() == false) {
			/* fire piece */
			if (this.pieceAt(xi, yi).isFire()) {
				/* not king piece */
				if (this.pieceAt(xi, yi).isKing() == false) {
					if (ymove == 1) {
						return true;
					}
				}
				/* king piece */
				if (this.pieceAt(xi, yi).isKing()) {
					return true;
				}
			}
			/* water piece */
			if (this.pieceAt(xi, yi).isFire() == false) {
				/* not king piece */
				if (this.pieceAt(xi, yi).isKing() == false) {
					if (ymove == -1) {
						return true;
					}
				}
				/* king piece */
				if (this.pieceAt(xi, yi).isKing()) {
					return true;
				}
			}
		}

		/* valid move with capturing */
		if (Math.abs(xmove) == 2 && Math.abs(ymove) == 2) {
			/* fire piece */
			if (this.pieceAt(xi, yi).isFire()) {
				/* not king piece */
				if (this.pieceAt(xi, yi).isKing() == false) {
					if (ymove == 2) {
						if (this.pieceAt(xmove / 2 + xi, ymove / 2 + yi) != null) {
							if (this.pieceAt(xmove / 2 + xi, ymove / 2 + yi).isFire() == false) {
							    return true;
						    }
						}
					}
				}
				/* king piece */
				if (this.pieceAt(xi, yi).isKing()) {
					if (this.pieceAt(xmove / 2 + xi, ymove / 2 + yi) != null) {
						if (this.pieceAt(xmove / 2 + xi, ymove / 2 + yi).isFire() == false) {
						    return true;
					    }
					}
				}
			}
			/* water piece */
			if (this.pieceAt(xi, yi).isFire() == false) {
				/* not king piece */
				if (this.pieceAt(xi, yi).isKing() == false) {
					if (ymove == -2) {
						if (this.pieceAt(xmove / 2 + xi, ymove / 2 + yi) != null) {
							if (this.pieceAt(xmove / 2 + xi, ymove / 2 + yi).isFire()) {
							    return true;
						    }
						}
					}
				}
				/* king piece */
				if (this.pieceAt(xi, yi).isKing()) {
					if (this.pieceAt(xmove / 2 + xi, ymove / 2 + yi) != null) {
						if (this.pieceAt(xmove / 2 + xi, ymove / 2 + yi).isFire()) {
						    return true;
					    }
					}
				}
			}
		}
		return false;
	}



    /* return true if the square at x,y can be selected
     */
	public boolean canSelect(int x, int y) {
		if (x > size - 1 || x < 0 || y > size -1 || y < 0) {
			return false;
		}
		/* selecting a piece */
		if (this.pieceAt(x, y) != null){
			if (hasSelected == false && hasMoved == false && this.pieceAt(x, y).isFire() == isFireturn) {
			     /* the player has not selected a piece yet and not after bombed! */
			    return true;
		    }
		    if (this.pieceAt(x, y).isFire() == isFireturn && hasSelected && hasMoved == false) {
		    	/* the player has selected but not moved a piece */
		    	return true;
		    }
		}

		/* selecting an empty square */
		if (this.pieceAt(x, y) == null) {
			if (hasSelected && hasMoved == false) {
				if (this.validMove(xselected, yselected, x, y)){
					/* the player has selected but not moved a piece 
				     * and the move is valid 
				     */
				    return true;
				}		
			}
			if (hasSelected){
				if (this.pieceAt(xselected, yselected).hasCaptured() && this.validMove(xselected, yselected, x, y)) {
					/* the player has selected and captured and 
				     * is now moving to another valid position
				     */
				    return true;
				}
			}
		}
		return false;
	}

    /* select the square at x,y
     * assume canSelect returns true
     * select a piece or an empty square to place capture
     * this is where possible move() is executed!!!
     */
	public void select(int x, int y) {
		/* select a piece */
		if (this.pieceAt(x, y) != null) {
			if (this.hasSelected) {
				selected[xselected][yselected] = false;
			}
			selected[x][y] = true;
			xselected = x;
			yselected = y;
			hasSelected = true;
		}

		/* select an empty square, and move to here!! */
		if (this.pieceAt(x, y) == null) {
			/* if moving a bomb, no select after explosion */
			if (this.pieceAt(xselected, yselected).isBomb()) {
				this.pieceAt(xselected, yselected).move(x, y);
				selected[xselected][yselected] = false;
				if (Math.abs(x - xselected) == 2) {
				    hasSelected = false;
				    hasMoved = true;
				    xselected = -1;
				    yselected = -1;
				}
				if (Math.abs(x - xselected) == 1) {
				    hasSelected = true;
				    hasMoved = true;
				    selected[x][y] = true;
				    xselected = x;
				    yselected = y;
				}
			}
			/* if not moving a bomb, select after movement */
			else {
				this.pieceAt(xselected, yselected).move(x, y);
				selected[xselected][yselected] = false;
				selected[x][y] = true;
				xselected = x;
				yselected = y;
				hasSelected = true;
				hasMoved = true;
			}
		}
	}

    /* place p at x,y
     * if x,y out of bounds or p is null, do nothing
     * if p already exists, firstly remove
     * if another piece at x,y, replace that piece
     * FOR creating specific test circumstance
     * and FOR moving piece and rewriting the pieces array
     */
	public void place(Piece p, int x, int y) {
		if (x >= 0 && x < size && y >= 0 && y < size && p != null) {
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (pieces[i][j] == p) {
						Piece premoved = remove(i, j);
					}
				}
			}
			if (pieceAt(x, y) != null) {
				Piece preplaced = remove(x, y);
			}
			if (p.isFire()) {
				numf += 1;
				pieces[x][y] = p;
			}
			if (p.isFire() == false) {
				numw += 1;
				pieces[x][y] = p;
			}
		}
	}


    /* execute a remove return the piece removed
     * if x,y out of bounds, return null and print an appropriate msg
     * if no piece at x,y return null and print a msg
     */
	public Piece remove(int x, int y) {
		if (x > size - 1 || x < 0 || y > size -1 || y < 0) {
			System.out.println("Piece tried to remove is out of board!");
			return null;
		}
		if (pieces[x][y] == null) {
			System.out.println("No piece at (" + x + "," + y + ") can be removed!" );
			return null;
		}
		if (pieces[x][y].isFire()) {
			numf -= 1;
			Piece pass = pieces[x][y];
			pieces[x][y] = null;
			return pass;
		}
		else {
			numw -= 1;
			Piece pass = pieces[x][y];
			pieces[x][y] = null;
			return pass;
		}
	}

    /* return whether can end their turn
     * to end a turn, piece moved or capture performed
     */
	public boolean canEndTurn() {
		if (hasMoved) {
			return true;
		}
		else {
			return false;
		}
	}

    /* called at the end of each turun
     * handle switching players and anything else should happen
     * !!!reset all the state parameters
     */
	public void endTurn() {
		isFireturn = !isFireturn;
		hasMoved = false;
		hasSelected = false;
		xselected = -1;
		yselected = -1;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				selected[i][j] = false;
				if (this.pieceAt(i, j) != null) {
					pieces[i][j].doneCapturing();
				}
			}
		}
	}

    /* return the winner "Fire" or "Water" 
     * or "No one"(no pieces on board)
     * or null if not yet over
     * determine winner by # of pieces
     */
	public String winner() {
		if (numf > 0 && numw > 0) {
			return null;
		}
		else if (numf == 0 && numw > 0) {
			String winner = "Water";
			return winner;
		}
		else if (numw == 0 && numf > 0) {
			String winner = "Fire";
			return winner;
		}
		else {
			String winner = "No one";
			return winner;
		}
	}




    /*starts a GUI supported version of the game*/
	public static void main(String[] args) {
		boolean isTestgame = false;
		if (isTestgame) {
			Board b = new Board(true);
		}
		if (!isTestgame) {
			Board b = new Board(false);
			while(b.winner() == null) {
				if (StdDrawPlus.mousePressed()) {
					double x = StdDrawPlus.mouseX();
					double y = StdDrawPlus.mouseY();
					if (b.canSelect((int)x, (int)y)) {
						b.select((int)x, (int)y);
						//b.drBoard();
						//StdDrawPlus.show(100);
					}
				}
				if (StdDrawPlus.isSpacePressed()) {
					if(b.canEndTurn()) {
						b.endTurn();
						//b.drBoard();
						//StdDrawPlus.show(100);
					}
				}
				b.drBoard();
				StdDrawPlus.show(100);
			}
			System.out.println("The winner is " + b.winner() + "!");
		}
	}
}