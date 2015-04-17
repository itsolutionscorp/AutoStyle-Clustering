public class Board { 
	private Piece[][] pieces; //stores Piece objects
	private boolean[][] yesPieces; //stores if there is a piece at that area or not

	private static int N = 8; //size of board
	private int player = 0; //first player = 0, second player = 1;
	private Piece currPiece; //current piece to be moved

	private boolean switched = true; 
	private boolean selected = false; //to check if piece has been selected
	private boolean moved = false; 
	// private boolean canMove = false;
	private boolean validSpace = true;
	private boolean startOfTurn = true;

	private int currX; //to compare if piece is able to move to certain spot
	private int currY; //to use TestBoard, change to public!

	// Piece[][] pieces; //stores Piece objects
	// boolean[][] yesPieces; //stores if there is a piece at that area or not

	// static int N = 8; //size of board
	// int player = 0; //first player = 0, second player = 1;
	// Piece currPiece; //current piece to be moved

	// boolean switched = true; 
	// boolean selected = false; //to check if piece has been selected
	// boolean moved = false; 
	// // private boolean canMove = false;
	// boolean validSpace = true;
	// boolean startOfTurn = true;

	// int currX; //to compare if piece is able to move to certain spot
	// int currY; //to use TestBoard, change to public!

	public static void main(String[] args) {
		String win = "";
		N = 8;
    	StdDrawPlus.setXscale(0, N);
    	StdDrawPlus.setYscale(0, N);
		Board b = new Board(false); //this stays in main method
		if(b != null) {
			b.drawBoard(N);
		}

		while(b != null) {
			b.drawBoard(N);
			b.updatePieces(N, b);

			if(StdDrawPlus.mousePressed()) {
               	int x = (int)StdDrawPlus.mouseX();
				int y = (int)StdDrawPlus.mouseY();
				if(b.canSelect(x, y)) { //why dont you work??? .______.
					b.cleanWhiteSelection(b);
					b.yesPieces[x][y] = true;
					b.select(x, y);
				}
				// else
				// 	System.out.println("can't be selected"); 
			}

			if(StdDrawPlus.isSpacePressed()) {
				// System.out.println("spacebar registered");
				if(b.canEndTurn()) {
					// System.out.println("tee hee");
					b.cleanWhiteSelection(b);
					b.endTurn();
				}
				// else
				// 	System.out.print("Hah can't end it yet");
			}
			StdDrawPlus.show(100);

			if(b.winner() != null) {
				win = b.winner();
				b.cleanWhiteSelection(b);
				b.drawBoard(N);
				b.updatePieces(N, b);
				StdDrawPlus.show(100);
				break;
			}
		}
		System.out.println(win);
	}

	public Board(boolean shouldBeEmpty) {
		if(shouldBeEmpty == true) { //basically have board drawn before this
			pieces = new Piece[N][N]; //check this
		} 
		else {
			pieces = new Piece[N][N];  //do i need to do this?
			yesPieces = new boolean[N][N];
			this.placeStartPieces(N, this);
		}
	}

	private void cleanWhiteSelection(Board board) {
		for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
            	board.yesPieces[i][j] = false;
            }
        }
	}

    private void drawBoard(int N) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            	
                if(yesPieces[i][j]) {
    				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            	}
            }
        }
    }

    /* Places pieces in initial positions for start of game */
    private void placeStartPieces(int N, Board b) {
    	int row = 0; 
     	for(int col = 0; col < N; col+=2) {
    		pieces[col][row] = new Piece(true, b, col, row, "pawn");
    		//had to flip row/cols around for placing into piece obj array
    	}
    	row = 1; 
    	for(int col = 1; col < N; col+=2) {
    		pieces[col][row] = new Piece(true, b, col, row, "shield");
    	}
    	row = 2; 
    	for(int col = 0; col < N; col+=2) {
    		pieces[col][row] = new Piece(true, b, col, row, "bomb");
    	}
    	row = 5; 
    	for(int col = 1; col < N; col+=2) {
    		pieces[col][row] = new Piece(false, b, col, row, "bomb");
    	}
    	row = 6; 
    	for(int col = 0; col < N; col+=2) {
    		pieces[col][row] = new Piece(false, b, col, row, "shield");
    	}
    	row = 7; 
    	for(int col = 1; col < N; col+=2) {
    		pieces[col][row] = new Piece(false, b, col, row, "pawn");
    	}
    }

    /* Updates the board as needed with the pieces
    *  If no change yet, then update with how board currently looks like */
    private void updatePieces(int N, Board b) {
    	for(int row = 0; row < N; row++) {
    		for(int col = 0; col < N; col++) {
          		Piece check = pieces[col][row];
          		if(check != null) {
	          		if(check.isFire()) {
		          		if(!check.isKing()) {
		          			if(check.isShield()) 
		          				drawPieces("img/shield-fire.png", row, col);
		                 	else if(check.isBomb())
		                 	    drawPieces("img/bomb-fire.png", row, col);
		                 	else
		                 		drawPieces("img/pawn-fire.png", row, col); 
		                }
		                else {
		                	if(check.isShield()) 
		          				drawPieces("img/shield-fire-crowned.png", row, col);
		                 	else if(check.isBomb())
		                 	    drawPieces("img/bomb-fire-crowned.png", row, col);
		                 	else
		                 		drawPieces("img/pawn-fire-crowned.png", row, col); 
		                }
		            }
	                else {
		                if(!check.isKing()) {
		                 	if(check.isShield())
		                 		drawPieces("img/shield-water.png", row, col);
		                 	else if(check.isBomb())
		                 	 	drawPieces("img/bomb-water.png", row, col);
		                 	else
		                 	 	drawPieces("img/pawn-water.png", row, col);
		                }
	                 	else {
							if(check.isShield())
		                 		drawPieces("img/shield-water-crowned.png", row, col);
		                 	else if(check.isBomb())
		                 	 	drawPieces("img/bomb-water-crowned.png", row, col);
		                 	else
		                 	 	drawPieces("img/pawn-water-crowned.png", row, col);
	                 	}
             		}
             	}
        	}
    	}
    } 

    private void drawPieces(String img, int row, int col) {
    	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.picture(col + .5, row + .5, img, 1, 1);        	
    }

	public Piece pieceAt(int x, int y) { //gets piece at x,y if possible
		if(!inBounds(x, y)) //check out of bounds
			return null;
		else if(pieces[x][y] == null)
		{	
			return null;
		}
		else
			return pieces[x][y];
	}

	/* checks for out of bounds */
	private boolean inBounds(int x, int y) {
		if(x >= 0 && x <= N-1 && y >= 0 && y <= N-1)
			return true;
		// System.out.println("out of bounds yo at: " + x + " " + y);
		return false;
	}

	public boolean canSelect(int x, int y) {
		// System.out.println("stats for this piece: selected: " + selected + " " + currX + " " + currY + " " + (x == currX + 2 || x == currX - 2));
		//KEEP THIS LINE COMMENTED OUT WHEN PUTTING INTO AG / WRITING JUNIT TESTS
		// System.out.println("in canselect: x,y; currs: " + x + " " + y + " " + currX + " " + currY);
		if(!inBounds(x, y))
			return false;

			/* if there is something on the board */
			if(pieceAt(x, y) != null && pieceAt(x,y).side() == player) {
				if(!selected) { //hasn't selected piece yet
					return true;
				}
				else if(selected && !moved) //selected piece but didn't move
					return true;
				return false;	
			}
			else { //empty space to move forward/backwards
				// System.out.println("in canselect method empty space " + x + " " + y);
				// System.out.println("stats for this piece: selected: " + selected + " " + currX + " " + currY + " " + (x == currX + 1 || x == currX - 1) + " " + validSpace(x, y));
				//KEEP THIS LINE COMMENTED OUT WHEN PUTTING INTO AG / WRITING JUNIT TESTS

				if(selected && !moved && pieces[x][y] == null && validSpace(x, y)) {
					// System.out.println("had valid space");
					return true; //selected piece, hasn't moved, chose empty space that works
				}
				else if(selected && currPiece.hasCaptured() && (x == currX + 2 || x == currX - 2) && validSpace(x, y)) {
					// System.out.println("has captured and can go again");
					return true; //select pieve, captured, selected another valid capture
				}
				// System.out.println("couldn't fit the other choices");
				return false;
			} //empty space to jump over a piece (further range)
	}

	private boolean validSpace(int x, int y) {
		// System.out.println("in validSpace x,y: " + x + " " + y + " with player " + player);
		if(!inBounds(x, y)) {
			return false;
		}
		if(player == 0 && !currPiece.isKing()) {
			if(currPiece.isFire()) { //checks fire
				if((inBounds(currX-1, currY+1) || inBounds(currX+1, currY+1)) && (x == currX + 1 || x == currX - 1) && y == currY + 1)
					return true; //for moving forward 
				else if((inBounds(currX-2, currY+2) || inBounds(currX+2, currY+2))) { //for trying to capture
					if (x == currX + 2 && y == currY + 2) //upper right side capture
						return pieceToCapture(currX + 1, currY + 1); //is one less b/c looking at the space before the one you select
					else if(x == currX - 2 && y == currY + 2) //upper left side capture
						return pieceToCapture(currX - 1, currY + 1);
					else
						return false; //else you've probably clicked too far out or one of the red spaces
				}
				return false; //if you aren't clicking a fire piece, it can't select
			}
			return false; //if you aren't clicking a fire piece, it can't select--must check if i need this one here as well
		}
		else if(player == 1 && !currPiece.isKing()) {
			if(!currPiece.isFire()) { //checks water
				if((inBounds(currX+1, currY-1) || inBounds(currX-1, currY-1)) && (x == currX + 1 || x == currX - 1) && y == currY - 1) 
					return true;
				else if((inBounds(currX+2, currY-2) || inBounds(currX-2, currY-2))) { 
					if(x == currX + 2 && y == currY - 2) //bottom right
						return pieceToCapture(currX + 1, currY - 1);
					else if(x == currX - 2 && y == currY - 2) //bottom left
						return pieceToCapture(currX - 1, currY - 1);
					else
						return false;
				}
				return false;
			}
			return false;
		}
		else { //for the KINGS
			// System.out.println("THIS IS A KING");
			if((x == currX + 1 || x == currX - 1) && (y == currY + 1 || y == currY - 1)) 
				return true;
			else if(x == currX + 2 && y == currY + 2) 
				return pieceToCapture(currX + 1, currY + 1);
			else if(x == currX + 2 && y == currY - 2) 
				return pieceToCapture(currX + 1, currY - 1);
			else if(x == currX - 2 && y == currY + 2)
				return pieceToCapture(currX - 1, currY + 1);
			else if(x == currX - 2 && y == currY - 2) 
				return pieceToCapture(currX - 1, currY - 1);
			else
				return false; 
		}
	}

	private boolean pieceToCapture(int xVal, int yVal) {
		Piece checkPiece = pieceAt(xVal, yVal);
		// System.out.println("checkPiece " + checkPiece.isFire() + " " + player);
		//KEEP THIS LINE COMMENTED OUT WHEN PUTTING INTO AG / WRITING JUNIT TESTS
		// System.out.println(checkPiece.side() + " " + currPiece.side());

		if(checkPiece == null) {
			// System.out.println("checkPiece is null");
			return false;
		}
		else if(checkPiece.side() == player) {
			// System.out.println("same side! " + checkPiece.side() + " " + player);
			return false;
		}
		else {

			return true;
		}
	}

	public void select(int x, int y) { //assumes canSelect == TRUE
		if(pieceAt(x, y) != null) {
			selected = true;
			currPiece = pieceAt(x, y); 
		}
		// System.out.println("selected! " + x + " " + y + " for " + player);
		// System.out.println("currX,currY,x,y: " + currX + " " + currY + " " + x + " " + y);


		if(currX == x && currY == y) {
			// System.out.println("same nums, will reassign but not change moved");
			currX = x;
			currY = y;
			startOfTurn = false;
		}

		else if(currX != x || currY != y) {
			// System.out.println("not the same numbers");
			if(pieceAt(x, y) == null) {
				currPiece.move(x, y);
				moved = true; //makes it so that have to change players
				// System.out.println("changed moved to true");
			}
			currX = x; //error has to do with placement of these
			currY = y;
		}
	}
	 //add prep piece for movement, empty = move selected piece to that sq

	public void place(Piece p, int x, int y) {
		if(inBounds(x, y) && p != null) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++){
					if(pieces[i][j] == p)
						remove(i, j);
				}
			}
			pieces[x][y] = p;
			// currX = x; //error has to do with placement of these
			// currY = y;
		}
	}

	public Piece remove(int x, int y) { //fix how to remove
		Piece temp = pieceAt(x, y);
		// System.out.println("going to remove! " + temp. + x + " " + y);

		if(!inBounds(x, y)) {
			System.out.println("Coordinates out of bounds.");
			return null;
		}
		else if(temp == null) {
			System.out.println("No piece to remove.");
		}
		else {
			pieces[x][y] = null;
		} 
		return temp;
	}

	public boolean canEndTurn() {
		if(currPiece == null)
			return false;
		if(moved || currPiece.hasCaptured()) {
			// System.out.println("can end turn!");
			return true;
		}
		return false;
	}

	public void endTurn() {
		player = changePlayers(player); 
		moved = false;
		selected = false;
		currPiece.doneCapturing(); //this might break some code
		// System.out.println("in end turn: changed moved back to false");
	}

	/* change between the two players */
	private int changePlayers(int p1) {
		switched = true;
		if(p1 == 0)  {
			return 1;
		}
		else {
			return 0;
		}
	}

	public String winner() {
		if(pieces == null) 
			return "No one";
		else {
			int fireTypes = 0;
			int waterTypes = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					Piece check = pieces[i][j];
					if(check != null && check.isFire()) 
						fireTypes += 1;
					else if(check != null && !check.isFire()) 
						waterTypes += 1;
				}
			}

			if(fireTypes == 0 && waterTypes == 0)
				return "No one";
			if(waterTypes == 0) 
				return "Fire";
			else if(fireTypes == 0)
				return "Water";
			else
				return null;
		} 
			
	}

}











