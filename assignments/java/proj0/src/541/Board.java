public class Board{
	private boolean[][] pieces = new boolean[8][8];
	private Piece[][] pieceOnBoard = new Piece[8][8];
	private int turn = 0;
	private boolean roundHasSelect = false;
	private boolean roundHasMove = false;
	private Piece roundPiece;
	private int fireNum = 0;
	private int waterNum = 0;
	
	public static void main(String[] args) {
		// Initialize the board
		
		Board b = new Board(false);
		b.drawBoard(8);
		b.drawPieces();
	    // Start game
	    while(true) {
	   		if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int sx = (int) x;
                int sy = (int) y;
                if (b.canSelect(sx,sy)==true) {
                	b.select(sx,sy);
                }
            }
       	    if (StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) {
            		b.endTurn();
            	}
            }
            b.drawBoard(8);
            b.drawPieces();
            StdDrawPlus.show(100);
	    }
	}
	

	/* The constructor for Board. If shouldBeEmpty is true, 
	initializes an empty Board. Otherwise, initializes a Board 
	with the default configuration. Note that an empty Board 
	could possibly be useful for testing purposes.*/
	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty==false) {
			for (int j=0;j<8;j++) {
		        for (int i=0;i<8;i++) {
		        	if((i + j) % 2 == 0) {
						if (j!=3 && j!=4) {
							pieces[i][j] = true;
							if (j==0) {
								pieceOnBoard[i][j] = new Piece(true,this,i,j,"pawn");
							}
							else if (j==1) {
								pieceOnBoard[i][j] = new Piece(true,this,i,j,"shield");
							}
							else if (j==2) {
								pieceOnBoard[i][j] = new Piece(true,this,i,j,"bomb");
							}
							else if (j==7) {
								pieceOnBoard[i][j] = new Piece(false,this,i,j,"pawn");
							}
							else if (j==6) {
								pieceOnBoard[i][j] = new Piece(false,this,i,j,"shield");
							}
							else if (j==5) {
								pieceOnBoard[i][j] = new Piece(false,this,i,j,"bomb");
							}
						}
						else {
							pieceOnBoard[i][j] = null;
						}
					}
					else {
						pieceOnBoard[i][j] = null;
					}
		        }
		    }
		}
		else {
			for (int i=0;i<8;i++) {
				for (int j=0;j<8;j++) {
					pieceOnBoard[i][j] = null;
				}
			}
		}
	}

	private void drawPieces() {
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		for (int j=0;j<8;j++) {
			for (int i=0;i<8;i++) {
				if (pieceOnBoard[i][j]!=null) {
					if (roundPiece==pieceOnBoard[i][j]) {
						StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
						StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					}
					Piece temp = pieceOnBoard[i][j];
					String name = getName(temp);
					StdDrawPlus.picture(i+0.5,j+0.5,name,1,1);
				}
			}
		}
	}
	// return the filename of p
	private String getName(Piece p) {
		String result;
		if (p.isFire()) {
			if (p.isKing()==false) {
				if (p.isBomb()) {
					result = "img/bomb-fire.png";
				}
				else if (p.isShield()) {
					result = "img/shield-fire.png";
				}
				else {
					result = "img/pawn-fire.png";
				}
			}
			else {
				if (p.isBomb()) {
					result = "img/bomb-fire-crowned.png";
				}
				else if (p.isShield()) {
					result = "img/shield-fire-crowned.png";
				}
				else {
					result = "img/pawn-fire-crowned.png";
				}
			}
		}
		else {
			if (p.isKing()==false) {
				if (p.isBomb()) {
					result = "img/bomb-water.png";
				}
				else if (p.isShield()) {
					result = "img/shield-water.png";
				}
				else {
					result = "img/pawn-water.png";
				}
			}
			else {
				if (p.isBomb()) {
					result = "img/bomb-water-crowned.png";
				}
				else if (p.isShield()) {
					result = "img/shield-water-crowned.png";
				}
				else {
					result = "img/pawn-water-crowned.png";
				}
			}
		}
		return result;
	}

    private void drawBoard(int N) {
    	StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
	    for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	            if ((i + j) % 2 == 0) {
	            	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	            }
	            else {
	            	StdDrawPlus.setPenColor(StdDrawPlus.RED);
	            }
	            StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	        }
	    }
	}
	// Gets the piece at position (x,y) on the Board
	// or null if there is no piece. If (x,y) are out
	// of bounds, returns null
	public Piece pieceAt(int x, int y) {
		if (x<=7 && y<=7 && x>=0 && y>=0 && pieceOnBoard[x][y]!=null) {
			return pieceOnBoard[x][y];
		}
		else {
			return null;
		}
	}

	// Returns true if there is a piece the piece at
	// (x,y) and it can be selected
	// rule: 	turn is correct
	// 			the player has not select or already selected
	//			but did not move.
	//			empty square may be select if:
	//				1. the player has selected a Piece but has not moved
	//				and the spot is valid
	//				2. He has selected a Piece, captured, and has
	//				selected another valid capture destination
	public boolean canSelect(int x, int y) {
		if (x<=7 && y<=7 && x>=0 && y>=0) {
			if (pieceOnBoard[x][y]!=null) {
				if (turn==0 && pieceOnBoard[x][y].isFire()==true) {
					if (roundHasSelect==false) {
						return true;
					}
					if (roundHasSelect==true && roundHasMove==false) {
						return true;
					}
				}
				else if (turn==1 && pieceOnBoard[x][y].isFire()==false) {
					if (roundHasSelect==false) {
						return true;
					}
					if (roundHasSelect==true && roundHasMove==false) {
						return true;
					}
				}
			}
			else if (pieceOnBoard[x][y]==null) {
				if (roundHasSelect==true && roundHasMove==false) {
					for (int j=0;j<8;j++) {
						for (int i=0;i<8;i++) {
							if (roundPiece==pieceOnBoard[i][j] && pieceOnBoard[i][j]!=null) {
								int xi = i;
								int yi = j;
								if (validMove(xi,yi,x,y)==true) {
									return true;
								}
							}
						}
					}
				}
				if (roundHasSelect==true && roundPiece.hasCaptured()==true) {
					for (int j=0;j<8;j++) {
						for (int i=0;i<8;i++) {
							if (roundPiece==pieceOnBoard[i][j] && pieceOnBoard[i][j]!=null) {
								int xi = i;
								int yi = j;
								if (validMove(xi,yi,x,y)==true) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	// simplify canSelect
	private boolean validMove(int xi, int yi, int xf, int yf) {
		boolean result = false;
		Piece temp = pieceOnBoard[xi][yi];
		Piece target = pieceOnBoard[xf][yf];
		int dx = xf-xi;
		int dy = yf-yi;
		//target should be in bound
		if (xf>=0 && yf>=0 && xf<=7 && yf<=7 && temp!=null) {
			//target should be empty
			if (target==null) {
				// while origin is not king
				if (temp.isKing()==false) {
					// For fire side
					if (temp.isFire()) {
						// go to neighbor diagogal
						if (Math.abs(dx)==1 && dy==1) {
							if (!temp.hasCaptured()) {
								result = true;
							}
						}
						// to capture something
						if (Math.abs(dx)==2 && dy==2) {
							Piece toCap = pieceAt(xi+dx/2,yi+dy/2);
							if (toCap!=null) {
								if (temp.side()!=toCap.side()) {
									result = true;
								}
							}
						}
					}
					// For water side
					if (!temp.isFire()) {
						// go to neighbor diagogal
						if (Math.abs(dx)==1 && dy==-1) {
							if (!temp.hasCaptured()) {
								result = true;
							}
						}
						// to capture something
						if (Math.abs(dx)==2 && dy==-2) {
							Piece toCap = pieceAt(xi+dx/2,yi+dy/2);
							if (toCap!=null) {
								if (temp.side()!=toCap.side()) {
									result = true;
								}
							}
						}
					}
				}
				if (temp.isKing()==true) {
					if (Math.abs(dx)==1 && Math.abs(dy)==1) {
						if (!temp.hasCaptured()) {
							result = true;
						}
					}
					if (Math.abs(dx)==2 && Math.abs(dy)==2) {
						Piece toCap = pieceAt(xi+dx/2,yi+dy/2);
						if (toCap!=null) {
							if (temp.side() != toCap.side()) {
								result = true;
							}
						}
					}
				}
			}
		}
		return result;
	}

	// Selects the piece at (x,y) if possible, Optionally, it is
	// recommend to mark it white via pen color function in GUI
	public void select(int x, int y) {
		if (pieceOnBoard[x][y]!=null) {
			roundPiece = pieceOnBoard[x][y];
			roundHasSelect = true;
		}
		else {
			for (int i=0;i<8;i++) {
				for (int j=0;j<8;j++) {
					if (roundPiece == pieceOnBoard[i][j]) {
						pieceOnBoard[i][j].move(x,y);
						roundHasMove = true;
					}
				}
			}
		}
	}

	// place p at (x,y) does nothing if (x,y) is out and p is null
	// remove it from its original and place it at new place
	public void place(Piece p, int x, int y) {
		if (x>=0 && y>=0 && x<=7 && y<=7 && p!=null) {			
			pieceOnBoard[x][y] = p;
			roundPiece = pieceOnBoard[x][y];
		}
	}
	

	
	// Executes a remove, return the piece that was removed
	// if the input (x,y) is outbound, returns null and print
	// no piece at (x,y), returns null and prints
	public Piece remove(int x, int y) {
		if (x>=0 && y>=0 && x<=7 && y<=7 && pieceOnBoard[x][y]!=null) {
			Piece temp = pieceOnBoard[x][y];
			pieceOnBoard[x][y] = null;
			return temp;
		}
		else {
			System.out.println("OutBounds or no piece here!");
			return null;
		}
	}

	public boolean canEndTurn() {
		boolean result = false;
		if (roundHasMove==true) {
			result = true;
		}
		return result;
	}

	public void endTurn() {
		outerloop:
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (roundPiece==pieceOnBoard[i][j]) {
					pieceOnBoard[i][j].doneCapturing();
					break outerloop;
				}
			}
		}
		roundPiece = null;
		roundHasMove = false;
		roundHasSelect = false;
		if (turn==0) {
			turn = 1;
		}
		else if (turn==1) {
			turn = 0;
		}
	}

	public String winner() {
		fireNum = 0;
		waterNum = 0;
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				if (pieceOnBoard[i][j]!=null) {
					if (pieceOnBoard[i][j].isFire()) {
						fireNum += 1;
					}
					else if (!pieceOnBoard[i][j].isFire()) {
						waterNum += 1;
					}
				}
			}
		}
		if (fireNum==0 && waterNum>0) {
			return "Water";
		}
		else if (waterNum==0 && fireNum>0) {
			return "Fire";
		}
		else if (fireNum==waterNum && fireNum==0 && waterNum==0) {
			return "No one";
		}
		else if (fireNum>0 && waterNum>0) {
			return null;
		}
		return null;
	}
}