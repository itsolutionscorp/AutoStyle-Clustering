

public class Board {
    private  int N = 8;
    private  Piece[][] pieces;
    private  int row = 8;
    private  int col = 8;
    private String turn = "fire"; 
    private boolean hasSelected = false; //switch to private!!!!
    private boolean hasMoved = false;
    private int activeX;
    private int activeY;
    private Piece selected = null;
   
   


    
    public Board (boolean shouldBeEmpty) {
	 int row = 8;
	 int col = 8;
	 pieces = new Piece[row][col];
	  	if (shouldBeEmpty == false) {


	pieces[0][1] = null;
	
	pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
	pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
	pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
	pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
       	pieces[1][1] = new Piece(true, this, 1, 1, "shield");
	pieces[3][1] = new Piece(true, this, 3, 1, "shield");
	pieces[5][1] = new Piece(true, this, 5, 1, "shield");
	pieces[7][1] = new Piece(true, this, 7, 1, "shield");
	pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
	pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
	pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
	pieces[6][2] = new Piece(true, this, 6, 2, "bomb");
	pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
	pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
	pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
	pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
	pieces[0][6] = new Piece(false, this, 0, 6, "shield");
	pieces[2][6] = new Piece(false, this, 2, 6, "shield");
	pieces[4][6] = new Piece(false, this, 4, 6, "shield");
	pieces[6][6] = new Piece(false, this, 6, 6, "shield");
	pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
	pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
	pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
	pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
		}

	
	
}

 

    
    private void drawBoard(int N) {
	  for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5); }
	  }
	  

	  for (int i = 0; i < col; i++) {
	      for (int j = 0; j < row; j++) {
		      Piece p = pieces[i][j];
		      if (p != null) {
			  if (p.isKing()) {

			       if ((!p.isBomb()) && (!p.isShield()) && (p.isFire())) {
			  StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
			      }
		      if ((!p.isBomb()) && (!p.isShield()) && (!p.isFire())) {
			   StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
		      }
		      if ((p.isBomb()) && (p.isFire())) {
			   StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
		      }
		      if ((p.isBomb()) && (!p.isFire())) {
			   StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
		      }
		      if ((p.isShield()) && (p.isFire())) {
			   StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
		      }
		      if ((p.isShield()) && (!p.isFire())) {
			   StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
		      }
			  }
		      else {

		      if ((!p.isBomb()) && (!p.isShield()) && (p.isFire())) {
			  StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
			      }
		      if ((!p.isBomb()) && (!p.isShield()) && (!p.isFire())) {
			   StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
		      }
		      if ((p.isBomb()) && (p.isFire())) {
			   StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
		      }
		      if ((p.isBomb()) && (!p.isFire())) {
			   StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
		      }
		      if ((p.isShield()) && (p.isFire())) {
			   StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
		      }
		      if ((p.isShield()) && (!p.isFire())) {
			   StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
		      }
		  }
		  
	      }
	      }
	  }
    }
    public void place(Piece p, int x, int y) { 
       pieces[x][y] = p;
    }
	    

    public Piece remove(int x, int y) {
	Piece removed = pieces[x][y];
	pieces[x][y] = null;
	hasMoved = true;
	return removed;
    }


    private boolean validMove(int xi, int yi, int xf, int yf) {
	if (turn == "fire") {
	    if (((xf == (xi + 1)) || (xf == (xi - 1))) && (pieces[xi][yi].isFire()) && (yf == (yi + 1))) {
		return true; 
    }
	    if ((xf > xi) && (pieces[xf][yf] == null) && (pieces[xf-1][yf-1] != null) && (!pieces[xf-1][yf-1].isFire())) {	 
		return true; //right capture
	    }
	    if ((xf < xi) && (pieces[xf][yf] == null)  && (pieces[xf+1][yf-1] != null) && (!pieces[xf+1][yf-1].isFire())) {	     		return true;	       
	}
	}

        if (turn == "water") {
	    if (((xf == (xi + 1)) || (xf == (xi - 1))) && (!pieces[xi][yi].isFire()) && (yf == (yi - 1))) {
		return true; 
	    }
	    if ((xf > xi) && (pieces[xf][yf] == null) && (pieces[xf-1][yf+1] != null) && (pieces[xf-1][yf+1].isFire())) {
		 return true; //down right capture
	     }
	    if ((xf < xi) && (pieces[xf][yf] == null) && (pieces[xf+1][yf+1] != null) && (pieces[xf+1][yf+1].isFire())) {
		 return true; //down left capture
	     }
	    
	}
	else { System.out.println("Not a valid move"); }
       
	System.out.println("Not a valid move");
	return false;
    }
    
    
	
    public boolean canSelect(int x, int y) {
      	if ((pieces[x][y] == null) && (hasSelected == true) && (validMove(activeX, activeY, x, y)) && (hasMoved == false)) { 
	    return true;	       
	}

	//	if (pieces[x][y] == null) {
	//	return false;
	//	} //maybe change

	if (turn == "fire") {


	 
       if ((pieces[x][y] != null) && (pieces[x][y].isFire()) && ((hasSelected == false) || (hasMoved == false))) {
	    return true; 
	}
       }

	if (turn == "water") {
	    if ((pieces[x][y] != null) && (!pieces[x][y].isFire()) && ((hasSelected == false) || (hasMoved == false))) {
	    return true;
	    }
       }



	if ((pieces[x][y] == null) && (hasSelected == true) && (validMove(activeX, activeY, x, y)) && (hasMoved == false)) { 
	    return true;	       
	}

	if ((pieces[x][y] == null) && (pieceAt(activeX, activeY) != null) && (validMove(activeX, activeY, x, y))) {
	    return true;
	    
	}
      
	else { return false;
	}
    }


    public void select(int x, int y) {	
	if (pieceAt(x, y) != null) {
	    hasSelected = true; 
	    selected = pieceAt(x, y);
	    activeX = x;
	    activeY = y;
	    System.out.println("Selected square at " + x + ", " + y);
	}
 
	else { 
	 
		
	    hasMoved = true;
	    System.out.println("moved");
	    selected.move(x, y);
	    activeX = x;
	    activeY = y;
	}
        

	}
      
    
    
    public boolean canEndTurn() {
	if (hasMoved) {
	    return true;
	}
	else { return false; 
	}
    }

    public void endTurn() {
	hasMoved = false;
	if (pieces[activeX][activeY] != null) {
	pieces[activeX][activeY].doneCapturing();
	}
	hasSelected = false; 
	
	if (turn == "fire") {
	    turn = "water";
	    System.out.println("It's now water's turn");
	}
	else { turn = "fire";
	     System.out.println("It's now fire's turn");
		}
	selected = null;
       
    }
	
	 
    
	
	
	

    public Piece pieceAt(int x, int y) {
	if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
	    return null; 
	}
	return pieces[x][y];
    }
    

    public String winner() {
	int fCounter = 0;
	int wCounter = 0;
	for (int i = 0; i < N; i = i + 1) {
	    for(int j = 0; j < N; j = j + 1) {
		Piece p = pieces[i][j];
		if (p != null) {
		    if (!pieces[i][j].isFire()) {
			wCounter = wCounter + 1;
		    }
		    else { fCounter = fCounter + 1;
		    }
		}
	    }
	}
	if ((wCounter == 0) && (fCounter == 0)) {
	    return "No one";
		}
	else if ((wCounter == 0) && (fCounter > 0)) {
	    return "Fire";
	}
	else if ((wCounter > 0) && (fCounter == 0)) {
	    return "Water";
	}
	else return null;
	

    }
    
    

    public static void main(String[] args) {
	Board b = new Board(false);
	int N = 8;
	StdDrawPlus.setXscale(0, N);
	StdDrawPlus.setYscale(0, N);
        
	
	while(true) {
	    if (StdDrawPlus.mousePressed()) {
	       int x = (int) StdDrawPlus.mouseX();
	       int y = (int) StdDrawPlus.mouseY();
	       if (b.canSelect(x, y)) {
		   b.select(x,  y);
		    }
	    }
	    if (StdDrawPlus.isSpacePressed()) {
		System.out.println("it worked");
		if (b.canEndTurn()) {
		    b.endTurn();
		}
	    }
	    b.drawBoard(N);
	    StdDrawPlus.show(100);

    

}
}
}


    
       



