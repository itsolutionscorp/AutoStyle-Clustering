public class Board{
	private Piece[][] pieces;
	private Piece selected;
	private boolean hasMoved;
	private int turn = 0;
	private int xi, yi;
	private boolean hasCaptured;

	public Board(boolean shouldBeEmpty){
		pieces = new Piece[8][8];
		if (!shouldBeEmpty) {
			for (int x = 0; x < 8; x+=2) {
				pieces[x][0] = new Piece(true, this, x, 0, "pawn");
				pieces[x][2] = new Piece(true, this, x, 2, "bomb");
				pieces[x][6] = new Piece(false, this, x, 6, "shield");
			}

			for (int x = 1; x < 8; x+=2) {
				pieces[x][1] = new Piece(true, this, x, 1, "shield");
				pieces[x][5] = new Piece(false, this, x, 5, "bomb");
				pieces[x][7] = new Piece(false, this, x, 7, "pawn");
			}

		}
	}

  private void drawBoard() {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
        else                  StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        if (pieces[i][j] != null) {
          StdDrawPlus.picture(i + .5, j + .5, filename(pieces[i][j]), 1, 1);
        }
	  	}
		}	
  }
  
  private String filename(Piece p) {

  	String filename = "img/";
  	if (p.isBomb()) {
  		filename += "bomb";
  	} else if (p.isShield()) {
  		filename += "shield";
  	} else {
  		filename += "pawn";
  	}

  	filename += "-";

  	if (p.isFire()) {
  		filename += "fire";
  	} else {
  		filename += "water";
  	}


  	if (p.isKing()) {
  		filename += "-crowned";
  	} 

  	filename += ".png";

  	return filename;
  }

  public static void main (String args[]) {
  	//- starts a GUI supported version of the game.
  	
  	int N = 8;
    StdDrawPlus.setXscale(0, 8);
    StdDrawPlus.setYscale(0, 8);
    Board b = new Board(false);
    b.drawBoard();
    while(b.winner() == null) {
      if (StdDrawPlus.mousePressed()) {
        double x = StdDrawPlus.mouseX();
        double y = StdDrawPlus.mouseY();
        if (b.canSelect((int) x, (int) y)) {
        	b.select((int) x, (int) y);

        }
        
    	}            
    	
    	if (StdDrawPlus.isSpacePressed()) {
    		if (b.canEndTurn()) {
    			b.endTurn();
    		}
    	}
    	StdDrawPlus.show(100);
    	b.drawBoard();
		}
		
  }

  public Piece pieceAt(int x, int y) {
  	return pieces[x][y];
  }


  private boolean validMove(int xi, int yi, int xf, int yf) {
  	if (!selected.isKing()){
  		if (Math.abs(xi-xf)==1) {
				if ((turn == 0) && (yf - yi == 1)) {
		    	return true;
		  	} else if ((turn == 1) && (yf - yi == -1)){
		    	return true;
	    	}
	  	} else if (Math.abs(xi-xf)==2 && pieces[(xi+xf)/2][(yi+yf)/2] != null) {
				if ((turn == 0) && (yf - yi == 2) && (!pieces[(xi+xf)/2][(yi+yf)/2].isFire())) {
		    	return true;
				} else if ((turn == 1) && (yf - yi == -2) && (pieces[(xi+xf)/2][(yi+yf)/2].isFire())) {
		    	return true;
	    	}
  		} else {
  			return false;
  		}
  		return false;
  	} else {
  		if (Math.abs(xi-xf)==1) {
				if (Math.abs(yf - yi) == 1) {
		    	return true;
		    } else {
		    	return false;
		    } 	
	  	} else if (Math.abs(xi-xf)==2 && pieces[(xi+xf)/2][(yi+yf)/2] != null) {
				if ((turn == 0) && (Math.abs(yf - yi) == 2) && (!pieces[(xi+xf)/2][(yi+yf)/2].isFire())) {
		    	return true;
				} else if ((turn == 1) && (Math.abs(yf - yi) == 2) && (pieces[(xi+xf)/2][(yi+yf)/2].isFire())) {
		    	return true;
	    	}
  		} else {
  			return false;
  		}
  		return false;
  	}
  }

  private boolean validCapture(int xi, int yi, int xf, int yf) {
  	if (!selected.isKing()) {
  		if (Math.abs(xi-xf)==2 && pieces[(xi+xf)/2][(yi+yf)/2] != null) {
				if ((turn == 0) && (yf - yi == 2) && (!pieces[(xi+xf)/2][(yi+yf)/2].isFire())) {
		    	return true;
				} else if ((turn == 1) && (yf - yi == -2) && (pieces[(xi+xf)/2][(yi+yf)/2].isFire())) {
		    	return true;
	    	} else {
  			return false;
  			}
			}
  	} else {
  		if (Math.abs(xi-xf)==2 && pieces[(xi+xf)/2][(yi+yf)/2] != null) {
				if ((turn == 0) && (Math.abs(yf - yi) == 2) && (!pieces[(xi+xf)/2][(yi+yf)/2].isFire())) {
		    	return true;
				} else if ((turn == 1) && (Math.abs(yf - yi) == 2) && (pieces[(xi+xf)/2][(yi+yf)/2].isFire())) {
		    	return true;
	    	}
  		} else {
  			return false;
  		}
  	}
  	return false;
  }

  public boolean canSelect(int x, int y) {
  	Piece p = pieceAt(x, y);
  	if (p != null && turn == p.side()) {
  		if (selected == null || hasMoved == false) {
  			System.out.println("can select because its the first select on your move");
  			return true;
  		} else {
  			System.out.println("cant select because you already moved this turn!");
  			return false;

  		}
  	} else if (p == null && selected != null && turn == selected.side()) {
  		if (hasMoved == false && validMove(xi, yi, x, y)) {
  			System.out.println("can select because its your turn and its a valid move");
  			return true;
  		} else if (hasMoved && hasCaptured(selected) && validCapture(xi, yi, x, y)) {
  			return true;
  		} else {
  			System.out.println("cant select because not a valid move");
  			return false;
  		}
  	} else {
  		System.out.println("cant select because second click wasnt empty or its the wrong turn");
  		return false;
  	}
  }

  public void select(int x, int y){
  	Piece p = pieces[x][y];
  	xi = x;
  	yi = y;
  	if (p == null){
  		selected.move(x, y);
  		hasMoved = true;
  	}
  	Piece o = pieces[x][y];
  	selected = o;
  	System.out.println(selected);
  }

  private boolean hasCaptured(Piece p) {
  	if (p.hasCaptured()) {
  		return true;
  	} else {
  		return false;
  	}
  }

  private void doneCapturing(Piece p) {
  	if (selected != null && selected.hasCaptured()) {
  		selected.doneCapturing();
  	}
  }

  public void place(Piece p, int x, int y) {
  	pieces[x][y] = p;
  }

  public Piece remove(int x, int y) {
  	if ((x < 0 || x > 8) || (y < 0 || y > 8)) {
  		System.out.println("Out of Bounds");
  		return null;
  	} else if (pieceAt(x, y) == null) {
  		return null;
  	} else {
  		Piece rvm = pieceAt(x, y);
  		pieces[x][y] = null;
  		return rvm;
  	}
  }

  public boolean canEndTurn() {
  	return hasMoved;
  }

  public void endTurn(){
  	if(turn == 1) {
  		turn = 0;
  	} else {
  		turn = 1;
  	}
  	hasMoved = false;
  	doneCapturing(selected);
  	selected = null;
  }

  public String winner(){
  	int numFire = 0;
  	int numWater = 0;
  	for (Piece p[] : pieces) {
  		for (Piece piece : p) {
  			if (piece != null && piece.isFire()) {
  				numFire +=1;
  			} else if (piece != null && !piece.isFire()) {
  				numWater +=1;
  			}
  		}
		}
		if (numWater == 0 && numFire == 0) {
			return "No one";
  	} else if (numFire == 0) {
  		System.out.println("Game OVer");
  		return "Water";
  	} else if (numWater == 0) {
  		return "Fire";
  	} else {
  		return null;
  	}
  }
}