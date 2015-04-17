public class Board {
	private Piece[][] pieces = new Piece[8][8];
	private boolean[][] select = new boolean[8][8];
	private boolean moved = false;
	private int s_x;
	private int s_y;
	private int turn = 0;
	private int fcount;
	private int wcount;
	/* */

	/* Starts a GUI supported version of the game. */
	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);

        while(true) {
	        for (int i = 0; i < 8; i++) {
		        for (int j = 0; j < 8; j++) {
		            if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		            else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
		            if (b.select[i][j]) {
		            	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		            }
		            StdDrawPlus.filledSquare(i + .5, j + .5, .5);
		            if (b.pieces[i][j] != null ) {

		            	String img;

		            	if (b.pieces[i][j].isBomb()) {
		            		img = "bomb-";
		            	}
		            	else if (b.pieces[i][j].isShield()) {
		            		img = "shield-";
		            	}
		            	else {
		            		img = "pawn-";
		            	}

						if (b.pieces[i][j].isFire()) {
							img += "fire";
						}
						else {
							img += "water";
						}
						if (b.pieces[i][j].isKing()) {
							img += "-crowned";
						}
		            	StdDrawPlus.picture(i + .5, j + .5, "img/"+img+".png", 1, 1);
		            }
		        }
		    }
		    if (StdDrawPlus.mousePressed()) {
	               	double x = StdDrawPlus.mouseX();
	                double y = StdDrawPlus.mouseY();
	                if (b.canSelect((int)x,(int)y)) {
	                	b.select((int)x,(int)y);
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

	/* The constructor for Board. */
	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == false) {
			pieces[0][0] = new Piece(true,this,0,0,"pawn");
	      	pieces[2][0] = new Piece(true,this,2,0,"pawn");
	      	pieces[4][0] = new Piece(true,this,4,0,"pawn");
	      	pieces[6][0] = new Piece(true,this,6,0,"pawn");
	      	pieces[1][1] = new Piece(true,this,1,1,"shield");
	      	pieces[3][1] = new Piece(true,this,3,1,"shield");
	      	pieces[5][1] = new Piece(true,this,5,1,"shield");
	      	pieces[7][1] = new Piece(true,this,7,1,"shield");
		    pieces[0][2] = new Piece(true,this,0,2,"bomb");
		    pieces[2][2] = new Piece(true,this,2,2,"bomb");
		    pieces[4][2] = new Piece(true,this,4,2,"bomb");
		    pieces[6][2] = new Piece(true,this,6,2,"bomb");
		    pieces[1][7] = new Piece(false,this,1,7,"pawn");
	      	pieces[3][7] = new Piece(false,this,3,7,"pawn");
	      	pieces[5][7] = new Piece(false,this,5,7,"pawn");
	      	pieces[7][7] = new Piece(false,this,7,7,"pawn");
	      	pieces[0][6] = new Piece(false,this,0,6,"shield");
	      	pieces[2][6] = new Piece(false,this,2,6,"shield");
	      	pieces[4][6] = new Piece(false,this,4,6,"shield");
	      	pieces[6][6] = new Piece(false,this,6,6,"shield");
		    pieces[1][5] = new Piece(false,this,1,5,"bomb");
		    pieces[3][5] = new Piece(false,this,3,5,"bomb");
		    pieces[5][5] = new Piece(false,this,5,5,"bomb");
		    pieces[7][5] = new Piece(false,this,7,5,"bomb");
	    }
	}

	/*  Gets the piece at position (x, y) on the board, or null if there is no piece. */
	public Piece pieceAt(int x, int y) {
		if (0 <= x && x <= 7 && 0 <= y && y <= 7) {
			if (pieces[x][y] != null) {
				return pieces[x][y];
			}
		}
		return null;
	}

	/* Returns true if there is a piece the piece at (x, y) and it can be selected. */
	public boolean canSelect(int x, int y) {
		/* Case for piece */
		if (pieceAt(x,y) != null) {
			if (moved == false) {
				/* side 0 */
				if (pieceAt(x,y).side() == 0 && turn == 0) {
					return true;
				}
				/* side 1 */
				if (pieceAt(x,y).side() == 1 && turn == 1) {
					return true;
				}
			}
		}
		int count = 0;
		for (int i = 0; i < 8; i++) {
		    for (int j = 0; j < 8; j++) {
				if (select[i][j]) {
					count += 1;
				}
			}
		}
		/* Case for empty square */
		if (pieceAt(x,y) == null && pieceAt(s_x,s_y) != null && count != 0) {
			if (moved == false || (pieceAt(s_x,s_y).hasCaptured() && (y == s_y+2 || y == s_y-2))) {
				if (validMove(s_x,s_y,x,y)) {
					return true;
				}
			}
		}

		return false;
	}

	/* Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf). */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (pieceAt(xf,yf) == null) {
			if (0 <= xf && xf <= 7 && 0 <= yf && yf <= 7) {
				/* side 0 */
				if (pieceAt(xi,yi).side() == 0) {
					if ( (yf == (yi+1)) && ( (xf == (xi+1)) || (xf == (xi-1)) ) ) {
						return true;
					}
					if ( (yf == (yi+2)) && ( (xf == (xi+2)) || (xf == (xi-2)) ) ) {
						if (xf < xi && pieceAt(xf+1,yf-1) != null) {
							if (pieceAt(xf+1,yf-1).side() == 1) {
								return true;
							}
						}
						if (xf > xi && (pieceAt(xf-1,yf-1) != null)) {
							if (pieceAt(xf-1,yf-1).side() == 1) {
								return true;
							}
						}
					}
				}
				/* side 1 */
				if (pieceAt(xi,yi).side() == 1) {
					if (yf == (yi-1) && (xf == (xi+1) || xf == (xi-1))) {
						return true;
					}
					if (yf == (yi-2) && (xf == (xi+2) || xf == (xi-2))) {
						if (xf < xi && pieceAt(xf+1,yf+1) != null) {
							if (pieceAt(xf+1,yf+1).side() == 0) {
								return true;
							}
							
						}
						if (xf > xi && (pieceAt(xf-1,yf+1) != null)) {
							if (pieceAt(xf-1,yf+1).side() == 0) {
								return true;
							}
						}
					}
				}
				/* king */
				if (pieceAt(xi,yi).isKing() == true) {
					if ((yf == (yi-1) || yf == (yi+1)) && (xf == (xi+1) || xf == (xi-1))) {
						return true;
					}
					if ((yf == (yi-2) || yf == (yi+2)) && (xf == (xi+2) || xf == (xi-2))) {
						if (xf < xi && yf < yi && pieceAt(xf+1,yf+1) != null) {
							if (pieceAt(xf+1,yf+1).side() != pieceAt(xi,yi).side()) {
								return true;
							}
						} 
						if (xf > xi && yf > yi && pieceAt(xf-1,yf-1) != null) {
							if (pieceAt(xf-1,yf-1).side() != pieceAt(xi,yi).side()) {
								return true;
							}
						}
						if (xf > xi && yf < yi && pieceAt(xf-1,yf+1) != null) {
							if (pieceAt(xf-1,yf+1).side() != pieceAt(xi,yi).side()) {
								return true;
							}
						}
						if (xf < xi && yf > yi && pieceAt(xf+1,yf-1) != null) {
							if (pieceAt(xf+1,yf-1).side() != pieceAt(xi,yi).side()) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	/* Selects the piece at (x, y) if possible. */
	public void select(int x, int y) {
			/* piece case */
			if (pieceAt(x,y) != null) {
				select = new boolean[8][8];
				select[x][y] = true;
				s_x = x;
				s_y = y;
			}
			/* empty case */
			else {
				pieceAt(s_x,s_y).move(x,y);
				select = new boolean[8][8];
				moved = true;
				if (pieceAt(x,y) != null) {
					select[x][y] = true;
					s_x = x;
					s_y = y;
				}
			}
	}

	/* Places p at (x, y). */
	public void place(Piece p, int x, int y) {
		if (p != null) {
			pieces[x][y] = p;
		}
	}

	/* Starts a GUI supported version of the game. */
	public Piece remove(int x, int y) {
		Piece temp = pieces[x][y];
		pieces[x][y] = null;
		return temp;
	}

	/* Returns whether or not the the current player can end their turn. */
	public boolean canEndTurn() {
		if (moved) {
			return true;
		}
		return false;
	}

	/*  Called at the end of each turn. */
	public void endTurn() {
			if (pieceAt(s_x,s_y) != null) {
				pieceAt(s_x,s_y).doneCapturing();
			}
			if (turn == 0) {
				turn = 1;
			}
			else {
				turn = 0;
			}
			select = new boolean[8][8];
			moved = false;
	}

	/*  Returns the winner of the game. */
	public String winner() {
		String winner = null;

		fcount = 0;
        wcount = 0;
		for (int i = 0; i < 8; i++) {
		    for (int j = 0; j < 8; j++) {
				if (pieces[i][j] != null ) {
				    if (pieces[i][j].side() == 0) {
				        fcount += 1;
				    }

				    if (pieces[i][j].side() == 1) {
				        wcount += 1;
				    }
				}
			}
		}

		if (fcount == 0 && wcount == 0) {
			winner = "No one";
		}
		else if (fcount == 0) {
			winner = "Water";
		}
		else if (wcount == 0) {
			winner = "Fire";
		}

		return winner;
	}

}