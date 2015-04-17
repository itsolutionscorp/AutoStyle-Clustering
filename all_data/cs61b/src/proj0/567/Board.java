public class Board {

	private static int N = 8;
	private Piece[][] pieces = new Piece[N][N];
	private boolean fireTurn = true; //if it is Fire's turn, fireTurn==true.
	private boolean hasMoved = false; //false until a piece has been moved this turn. 
	private Piece currentSelection = null; //current piece selected
	private int currSelX, currSelY; //current selection x and y

/*-------------------------------------------------------------------------------
	This section contains all necessary public methods in order provided in spec. */
	
	public Board(boolean shouldBeEmpty) {
		if (!shouldBeEmpty) {
			initializePieces(N);
		}
	}

	public Piece pieceAt(int x, int y) {
		if (x<0 || x>N-1 || y<0 || y>N-1) {
			return null;
		}
		else if (pieces[x][y] != null) {
			return pieces[x][y];
		}
		else {
			return null;
		}
	}	

	public boolean canSelect(int x, int y) {  
		if (x<0 || x>N-1 || y<0 || y>N-1) {
			return false;
		}

		if (pieceAt(x,y)==null) {  //if you are trying to select an empty space
			if (currentSelection != null) {  //and then if you have a piece currently selected.
				if (validMove(currSelX, currSelY, x, y)) {  //then you can select that piece if it's a valid move.
					return true;
				}
				else {  //if not a valid move then you can't select it.
					return false;
				}
			}
			else { //if you don't have anything currently selected then you can not select an empty space.
				return false;
			}
		}
		else if (pieceAt(x,y).isFire()==fireTurn && !hasMoved) { //if it is your turn and you haven't moved, then you can select your piece.
			return true;
		}
		else {
			return false;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) { 
		Piece p = pieceAt(xi, yi);

		if (xf<0 || xf>N-1 || yf<0 || yf>N-1 || pieceAt(xf,yf)!=null || pieceAt(xi,yi)==null) {
			return false;
		}

		if (currentSelection!=null && pieceAt(xf,yf)!=null) { //you can't move to a place with a piece if you have currently selected a piece.
			return false;
		}

		if (p.isFire() && !p.isKing()) {  //Un-Kinged Fire Pieces
			if (yf==yi+1 && (xf==xi-1 || xf==xi+1) && !hasMoved) { //can move to diagonal space
				return true;
			}
			else if (yf==yi+2 && (xf==xi-2 || xf==xi+2)  && pieceAt( (xi+xf)/2 , yi+1) != null) { //can move 2 diagonal spaces if there is a piece between current and final space.
				if (!pieceAt( (xi+xf)/2 , yi+1).isFire()) {
					if (!pieceAt( (xi+xf)/2 , yi+1).isFire())  { // make sure don't capture own pieces
					return true; //captured piece!
					}
				}
				else {
					return false;
				}

			}
		}
		else if (!p.isFire() && !p.isKing()) { //Un-Kinged Water Pieces
			if (yf==yi-1 && (xf==xi-1 || xf==xi+1) && !hasMoved) { //can move to diagonal space
				return true;
			}
			else if (yf==yi-2 && (xf==xi-2 || xf==xi+2)  && pieceAt( (xi+xf)/2 , yi-1) != null) { //can move 2 diagonal spaces if there is a piece between current and final space.
				if (pieceAt( (xi+xf)/2 , yi-1).isFire())  { // make sure don't capture own pieces
					return true; //captured piece!
				}
				else {
					return false;
				}

			}
		}
		else if (p.isKing()) { //Kinged Pieces
			if ((yf==yi-1 || yf==yi+1) && (xf==xi-1 || xf==xi+1) && !hasMoved) {
				return true;
			}
			else if ((yf==yi-2 || yf==yi+2) && (xf==xi-2 || xf==xi+2) && pieceAt( (xi+xf)/2 , (yi+yf)/2 ) != null) {
				if (pieceAt( (xi+xf)/2 , (yi+yf)/2 ).isFire() != pieceAt(xi,yi).isFire()) {
					return true; //captured piece!
				}
				else {
					return false;
				}
			}
		}
		
		return false;
		
	}

	public void select(int x, int y) { 
		if (currentSelection == null) { //prepping a piece for movement
			currentSelection = pieceAt(x,y);
			currSelX=x;
			currSelY=y;
			
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			drawPiece(currentSelection, currSelX, currSelY);
	    }
	    else { 
	    	if ((currSelX + currSelY) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            else  StdDrawPlus.setPenColor(StdDrawPlus.RED);
            StdDrawPlus.filledSquare(currSelX + .5, currSelY + .5, .5);
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			

	    	if (pieceAt(x,y)!=null) { //if you are selecting one from the same team.
	    		drawPiece(currentSelection, currSelX, currSelY);
	    		currentSelection = pieceAt(x,y);
	    		currSelX=x;
				currSelY=y;
	    		drawPiece(currentSelection, currSelX, currSelY);
	    	}
	    	else { //if you are selecting a place for this piece to move to.
		    	
		    	place(currentSelection, x, y); //meow
		    	
		    	hasMoved=true;

				if (!currentSelection.hasCaptured() || typePiece(currentSelection)!="bomb") drawPiece(currentSelection, currSelX, currSelY);	    	
	    	}
	    }
	    return;
	}

	public void place(Piece p, int x, int y) { 
		if (p == null || x<0 || x>N-1 || y<0 || y>N-1) { //if p is null or out of bounds don't do anything
			return;
		}	

		if (pieceAt(x,y)!=null) {
			remove(x,y);
		}

		if (pieceAt(currSelX, currSelY) != null) { //if the piece already exists on the board.
			p.move(x,y); //meow
			
			currSelX=x;
			currSelY=y;
		}

		pieces[x][y]=p;
		
		return;
	}

	public Piece remove(int x, int y) {
		Piece removedPiece = null;
		if (x<0 || x>N-1 || y<0 || y>N-1) {
			System.out.println("ERROR: It seems the location of piece is out of bounds.");
			return null;
		}
		else if (pieceAt(x,y) == null) {
			System.out.println("ERROR: There does not seem to be a piece there.");
			return null;
		}
		else {
			removedPiece= new Piece(pieceAt(x,y).isFire(), this, x, y, typePiece(pieceAt(x,y)));
			pieces[x][y] = null;
			if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY); //setting the appropiate color to de-select the previous piece
        	else  StdDrawPlus.setPenColor(StdDrawPlus.RED);
        	StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		}
		return removedPiece;
	}

	public boolean canEndTurn() { 
		if (hasMoved) {
			return true;
		}
		else {
			return false;
		}
	}

	public void endTurn() {  
		fireTurn = !fireTurn; //switching turns
		hasMoved = false;  //reseting hasMoved
		if ((currSelX + currSelY) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY); //setting the appropiate color to de-select the previous piece
        else  StdDrawPlus.setPenColor(StdDrawPlus.RED);
        StdDrawPlus.filledSquare(currSelX + .5, currSelY + .5, .5);
        if (!currentSelection.hasCaptured() || typePiece(currentSelection)!="bomb") drawPiece(currentSelection, currSelX, currSelY);
		currentSelection.doneCapturing(); //reseting the hasCaptured Variable
		currentSelection = null;
		currSelX=-1;
		currSelY=-1;
		return;
	}

	public String winner() { 
        int firePiecesleft = 0;
        int waterPiecesleft = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieceAt(i,j) == null) {
            		continue;
            	}
            	else if (pieceAt(i,j).isFire()) {
            		firePiecesleft += 1;
            	}
            	else {
            		waterPiecesleft += 1;
            	}
            }
        }
        if (firePiecesleft==0 && waterPiecesleft==0) {
        	return "No one";
        }
        else if (firePiecesleft>0 && waterPiecesleft==0) {
        	return "Fire";
        }
        else if (firePiecesleft==0 && waterPiecesleft>0) {
        	return "Water";
        }
        else {
        	return null;
        }
	}
/*-------------------------------------------------------------------------------
		The Main Method.														*/
	
	public static void main(String[] args) {
		Board b = new Board(false);
		int x,y;

		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);

		b.drawBoard(N);

		
		b.drawAllPieces(N);
		

		while (true) {
			if (StdDrawPlus.mousePressed()) {
				double dx = StdDrawPlus.mouseX();
				double dy = StdDrawPlus.mouseY();
				if (dx>0 && dy>0) {
					x = (int) dx;
					y = (int) dy;
				}
				else {
					x = -1;
					y = -1;
				}

				if (b.canSelect(x,y)) {
					b.select(x,y);
				}
			}
			if (b.canEndTurn() && StdDrawPlus.isSpacePressed()) {
				b.endTurn();
				if (b.winner()!=null) {
					System.out.println(b.winner());
					break;
				}
			}
			StdDrawPlus.show(100);
		}

	}
/*-------------------------------------------------------------------------------
		This section contains private optional helper methods                  */
	
	private void drawPiece(Piece p, int x, int y) {
		
		if (p.isFire()==true) { //Fire Piece
			if (p.isKing()==true) { //Fire King Piece
				StdDrawPlus.picture(x + .5, y + .5, "img/" + typePiece(p) + "-" + "fire-crowned.png", 1, 1);
			}
			else {  //Fire Regular Piece
				StdDrawPlus.picture(x + .5, y + .5, "img/" + typePiece(p) + "-" + "fire.png", 1, 1);
			}
		}
		else {  //Water Piece
			if (p.isKing()==true) { //Water King Piece
				StdDrawPlus.picture(x + .5, y + .5, "img/" + typePiece(p) + "-" + "water-crowned.png", 1, 1);
			}
			else {  //Water Regular Piece
				StdDrawPlus.picture(x + .5, y + .5, "img/" + typePiece(p) + "-" + "water.png", 1, 1);
			}
		}
	}

	private void drawAllPieces(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if (pieces[i][j]!=null) {
            		drawPiece(pieces[i][j], i, j);
            	}
            }
        }		
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
	}

	private void initializePieces(int N) {
		for (int i=0; i<N; i+=2) {
			pieces[i+1][7] = new Piece(false, this, i+1, 7, "pawn"); //Water Pawns
			pieces[i][6] = new Piece(false, this, i, 6, "shield"); //Water Shields
			pieces[i+1][5] = new Piece(false, this, i+1, 5, "bomb"); //Water Bombs

			pieces[i][2] = new Piece(true, this, i, 2, "bomb"); //Fire Bombs
			pieces[i+1][1] = new Piece(true, this, i+1, 1, "shield"); //Fire Shields
			pieces[i][0] = new Piece(true, this, i, 0, "pawn"); //Fire Pawns
		}
	}

	private String typePiece(Piece p) {
		if (p.isBomb()) {
			return "bomb";
		}
		else if (p.isShield()) {
			return "shield";
		}
		else {
			return "pawn";
		}

	}
}