
public class Board{
	
	//private boolean shouldBeEmpty;
	private static int xMousePressed = -1;
	private static int yMousePressed = -1;
	private static Piece[][] board;
	//private boolean isFireTurn = true; //always fire piece's turn first
	private boolean selected = false;
	private boolean hasMoved = false;
	

	private int prevX;
	private int prevY;

	private String turn = "fire";

	private Piece selectedPiece = null; // prep for movement
	private boolean canSelectAnotherPiece = true;

	// private int numWaterPieces=0;
	// private int numFirePieces=0;



	public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        
        Board b = new Board(false); 

        
        while (true) {
        	b.drawBoard(N);
        	StdDrawPlus.show(10);
        	if (StdDrawPlus.mousePressed()) {
        		int xMouse = (int) StdDrawPlus.mouseX();
        		int yMouse = (int) StdDrawPlus.mouseY();
        		

        		System.out.println(b.pieceAt(xMouse, yMouse));
        		if (b.canSelect(xMouse, yMouse)) {
        			yMousePressed = yMouse;
        			xMousePressed = xMouse;
        			System.out.println("successfully called canSelect");
        			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        			StdDrawPlus.filledSquare(yMouse, xMouse, .5);
        			b.select(xMouse, yMouse);
        			System.out.println("successfully called SELECT");
        		}
        	}
        	if (StdDrawPlus.isSpacePressed()) {
        		System.out.println(b.turn);
        		if (b.canEndTurn()) {
        			b.endTurn();
        		}
        	}
        }


        // int N = 8;
        // StdDrawPlus.setXscale(0, N);
        // StdDrawPlus.setYscale(0, N);
        // pieces = new boolean[N][N];

        // * Monitors for mouse presses. Wherever the mouse is pressed,
        //     a new piece appears. 
        // while(true) {
        //     drawBoard(N);
	}
    
    
    private void drawBoard(int N) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
            	if (selected == true) {
               			StdDrawPlus.filledSquare(xMousePressed+.5, yMousePressed+.5, .5);

               		}
            	
            	if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.PINK);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
  
                if (board[x][y]!= null) {

                	if (board[x][y].isFire() == true) {
                		if (board[x][y].isKing() == true && board[x][y].isShield() == true){
                			StdDrawPlus.picture(x+.5, y+.5, "img/shield-fire-crowned.png", 1, 1);
                		}
                		if (board[x][y].isShield() == true && board[x][y].isKing() == false) {
                			StdDrawPlus.picture(x+.5, y+.5, "img/shield-fire.png", 1, 1);
                		}
                		if (board[x][y].isKing() == true && board[x][y].isBomb() == true) {
                			StdDrawPlus.picture(x+.5, y+.5, "img/bomb-fire-crowned.png", 1, 1);
                		}
                		if (board[x][y].isBomb() == true && board[x][y].isKing() == false) {
                			StdDrawPlus.picture(x+.5, y+.5, "img/bomb-fire.png", 1, 1);
                		}
                		if (board[x][y].isKing() == true && board[x][y].isBomb() == false && board[x][y].isShield() == false) {
                			StdDrawPlus.picture(x+.5, y+.5, "img/pawn-fire-crowned.png", 1, 1);
                		}
                		if (board[x][y].isBomb() == false && board[x][y].isShield() == false && board[x][y].isKing() == false) {
                			StdDrawPlus.picture(x+.5, y+.5, "img/pawn-fire.png", 1, 1);
                		}
               	 	}
                	else if (board[x][y].isFire() == false) {
                		if (board[x][y].isKing() == true && board[x][y].isShield() == true){
                			StdDrawPlus.picture(x+.5, y+.5, "img/shield-water-crowned.png", 1, 1);
                		}
                		if (board[x][y].isShield() == true && board[x][y].isKing() == false) {
                			StdDrawPlus.picture(x+.5, y+.5, "img/shield-water.png", 1, 1);
                			
                		}
                		if (board[x][y].isKing() == true && board[x][y].isBomb() == true){
                			StdDrawPlus.picture(x+.5, y+.5, "img/bomb-water-crowned.png", 1, 1);
                		}
                		if (board[x][y].isBomb() == true && board[x][y].isKing() == false) {
                			StdDrawPlus.picture(x+.5, y+.5, "img/bomb-water.png", 1, 1);
                		}
                		if (board[x][y].isKing() == true && board[x][y].isBomb() == false && board[x][y].isShield() == false){
                			StdDrawPlus.picture(x+.5, y+.5, "img/pawn-water-crowned.png", 1, 1);                	
                		}	
                		if (board[x][y].isBomb() == false && board[x][y].isShield() == false && board[x][y].isKing() == false) {
                			StdDrawPlus.picture(x+.5, y+.5, "img/pawn-water.png", 1, 1);           
                		}
                	}
                	
                	//StdDrawPlus.show(10);
                }
               
               
            

            }
        }
        winner();
    }

	public Board(boolean shouldBeEmpty) {
	//*The constructor for Board. If shouldBeEmpty is true, initializes an empty Board.
	// Otherwise, initializes a Board with the default configuration. Note that an empty 
	//Board could possibly be useful for testing purposes.

		//Henry Yi helped me understand what exactly a board is. He said "Board is everything"
		//and "you can make a separate array for all the pieces" and the world was well.
		//this.shouldBeEmpty = shouldBeEmpty;
		if (shouldBeEmpty == true) {
			board = new Piece[8][8];
			//b = new Board(shouldBeEmpty);
		}
		else {
			//initialize board w/ default configuration. Initialize board with all pieces in the right place.
			//Henry Yi again clarified what "default configuration" meant
			//Default Fire Pieces
			board = new Piece[8][8];
			//b = new Board(shouldBeEmpty);
			for (int x=0; x<8; x+=2) {
				board[x][0] = new Piece(true, this, x, 0, "pawn");
			}
			for (int x=1; x<8; x+=2) {
				board[x][1] = new Piece(true, this, x, 1, "shield");
			}
			for (int x=0; x<8; x+=2) {
				board[x][2] = new Piece(true, this, x, 2, "bomb");
			}
			//Default Water Pieces
			for (int x=1; x<8; x+=2) {
				board[x][5] = new Piece(false, this, x, 5, "bomb");
			}
			for (int x=0; x<8; x+=2) {
				board[x][6] = new Piece(false, this, x, 6, "shield");
			}
			for (int x=1; x<8; x+=2) {
				board[x][7] = new Piece(false, this, x, 7, "pawn");
			}
		}
	}


	public Piece pieceAt(int x, int y) {
	/*Gets the piece at position (x, y) on the board,
	or null if there is no piece.
	If (x, y) are out of bounds, returns null.*/
		if (x>7 || x<0 || y>7 || y<0 || board[x][y] == null) {
			return null;
		}
		else {
			return board[x][y];
		}
	}


 	public boolean canSelect(int x, int y){
	//Returns true if there is a piece at (x, y) and 
	//it can be selected. Read spec for more info.
	//Won Park helped me debug and I learned that I do not need to use b. in front because I'm in the class.
		// if (pieceAt(x, y) == null) {
		// 	if (selected == false) {
		// 		return false;
		// 	}
		//if (selected == true) {
			if (pieceAt(x, y) == null && pieceAt(prevX, prevY)!= null) {
				System.out.println("prevx" + prevX);
				System.out.println("prevY" + prevY);
				if (turn == "fire" && pieceAt(prevX, prevY).isFire()==true || turn == "water" && pieceAt(prevX, prevY).isFire()== false) {
				if (selected == true && /*selectedPiece!= null &&*/ hasMoved==false && validMove(prevX, prevY, x, y)) {
					//selected = true;
					hasMoved = true;
					return true;
				}
				if (selected == true && hasMoved==true && selectedPiece == pieceAt(prevX, prevY) && pieceAt(prevX, prevY).hasCaptured() == true && validMove(prevX, prevY, x, y)) {
					//selected = true;
					hasMoved = true;
					return true;
				}
			}
		}
		if (pieceAt(x, y) != null) {
			if (turn == "fire" && pieceAt(x, y).isFire() == true && canSelectAnotherPiece == true || turn == "water" && pieceAt(x, y).isFire()==false && canSelectAnotherPiece == true){
				// if (selected == true && hasMoved== true) { accounted for in select method
				// 	canSelectAnotherPiece = false;
				// }
				if (selected == true && hasMoved==true && canSelectAnotherPiece == false) {
					return false;
				}


				if (selected == false || selected == true && hasMoved==false){
					//selected = true;
					selectedPiece = pieceAt(x, y);
					// prevX = x;
					// prevY = y;
					return true;
				}	

			}
		}

		System.out.println(prevX);
		System.out.println(prevY);
		//selected = false;
		return false;
	} 	


private boolean validMove(int xi, int yi, int xf, int yf) {
	//Returns true if the piece at (xi, yi) can either move to (xf, yf)
	//or capture to (xf, yf) in a valid fashion compatible with the rules.
		//valid move with no capture
		// if (pieceAt(xi, yi) == null) {
		// 	return false;
		// }
		System.out.println("xi" + xi);
		System.out.println("yi" + yi);
		System.out.println("xf" + xf);
		System.out.println("yf" + yf);
		if (xf<=7&& xf>=0 && yf<=7 && yf>=0) {
			//king pieces can move in 4 directions. First part is valid movement with no capture. Second is valid movement w/ capture.
			if (pieceAt(xi, yi).isKing() == true) { //king with no capture
			if (pieceAt(xf, yf)== null && (xi+1==xf && yi+1==yf || xi+1==xf && yi-1==yf || xi-1==xf && yi-1==yf || xi-1==xf && yi+1==yf)) { //move without capture
					return true;
				}	
			}		

			if (pieceAt(xi, yi).isKing() == true && pieceAt(xi, yi).isFire() == true && turn =="fire") { //king with capture

				if (xi+2 ==xf && yi+2== yf && pieceAt(xi+1, yi+1)!= null && pieceAt(xi+1, yi+1).isFire()==false && pieceAt(xf, yf)==null) { //capture
					return true;
				}

				if (xi+2 ==xf && yi-2== yf && pieceAt(xi+1, yi-1)!= null && pieceAt(xi+1, yi-1).isFire() == false && pieceAt(xf, yf) == null) {
					return true;
				}

				if (xi- 2 ==xf && yi+2== yf && pieceAt(xi-1, yi+1)!= null && pieceAt(xi-1, yi+1).isFire()==false && pieceAt(xf, yf) == null) {
					return true;
				}

				if (xi- 2 ==xf && yi-2== yf && pieceAt(xi -1, yi-1)!= null && pieceAt(xi-1, yi-1).isFire()==false && pieceAt(xf, yf) == null) {
					return true;
				}
				return false;
			}
			if (pieceAt(xi, yi).isKing()==true &&pieceAt(xi, yi).isFire()==false && turn == "water") {


				if (xi+2 ==xf && yi+2== yf && pieceAt(xi+1, yi+1)!= null && pieceAt(xi+1, yi+1).isFire()==true && pieceAt(xf, yf)==null) { //capture
					return true;
				}

				if (xi+2 ==xf && yi-2== yf && pieceAt(xi+1, yi-1)!= null && pieceAt(xi+1, yi-1).isFire() == true && pieceAt(xf, yf) == null) {
					return true;
				}

				if (xi- 2 ==xf && yi+2== yf && pieceAt(xi-1, yi+1)!= null && pieceAt(xi-1, yi+1).isFire()==true && pieceAt(xf, yf) == null) {
					return true;
				}


				if (xi- 2 ==xf && yi-2== yf && pieceAt(xi -1, yi-1)!= null && pieceAt(xi-1, yi-1).isFire()==true && pieceAt(xf, yf) == null) {
					return true;
				}
				return false;
			}			


			if (pieceAt(xi, yi).isFire() == true && turn=="fire" && pieceAt(xi, yi).isKing() == false) { //normal fire pieces
				//fire pieces can only move up and up w/capture

				//System.out.println("Attempting fire captureeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
				if (xi+1==xf && yi+1==yf && pieceAt(xf, yf)== null || xi-1==xf && yi+1==yf && pieceAt(xf, yf) == null){ //pieceAt(xi+1, yi+1)!= null && pieceAt(xi+1, yi+1).isFire()==false && pieceAt(xf, yf)==null) {//nocapture
					return true; 
				}
				if (xi-2==xf && yi+2==yf && pieceAt(xi-1, yi+1)!=null && pieceAt(xi-1, yi+1).isFire() == false && pieceAt(xf, yf)== null) {
					return true;
				}
				if (xi+2==xf && yi+2==yf && pieceAt(xi+1, yi+1)!= null && pieceAt(xi+1, yi+1).isFire() == false && pieceAt(xf, yf)==null) {
					//System.out.println("capturedddddddddddd");
					return true;
				}
			}
			if (pieceAt(xi, yi).isFire() == false && turn == "water" && pieceAt(xi, yi).isKing() == false) { //normal water pieces
				if (xi-1==xf && yi-1==yf && pieceAt(xf, yf)== null || xi+1==xf && yi-1==yf && pieceAt(xf, yf) == null){ //pieceAt(xi+1, yi+1)!= null && pieceAt(xi+1, yi+1).isFire()==false && pieceAt(xf, yf)==null) {//nocapture
					return true; 
				}
				if (xi-2==xf && yi-2==yf && pieceAt(xi-1, yi-1)!=null && pieceAt(xi-1, yi-1).isFire() == true && pieceAt(xf, yf)== null) {
					return true;
				}
				if (xi+2==xf && yi-2==yf && pieceAt(xi+1, yi-1)!= null && pieceAt(xi+1, yi-1).isFire() == true && pieceAt(xf, yf)==null) {
					return true;
				}
			}

		}
		System.out.println("not a valid move");
		return false;
	}


public void select(int x, int y) {
	// //Selects the piece at (x, y) if possible.
	// //Recommended: color the background of the selected square white
	// // on the GUI via pen color function. For any piece to perform 
	// //capture, must be selected first.
	// 	b.selected = true;
	// 	if (board[x][y] == null) {
	selected = true;
	//canSelectAnotherPiece = false;
	if (pieceAt(x, y) != null){
		prevX = x;
		prevY = y;
		selectedPiece = pieceAt(x, y);
		System.out.println("Did not move piece");
	}
	if (pieceAt(x, y)== null) {
		//move the piece to the new location
		System.out.println("Attempted to move a piece");
		System.out.println(selectedPiece);
		System.out.println(pieceAt(prevX, prevY));
		if (selectedPiece == null) {
			System.out.println("selected piece is null");
		}
			System.out.println(selectedPiece);
		selectedPiece= pieceAt(prevX, prevY);
		selectedPiece.move(x, y);
		prevX = x;
		prevY= y;
		canSelectAnotherPiece = false;
		hasMoved = true;
	}
	return;
 }


public void place(Piece p, int x, int y) {
	//Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing.
	//If another piece already exists at (x, y), p will replace that piece.
	//(This method useful for creating specific test circumstances.)
		//String sameType;
	System.out.println("hi2");
		if (x>7 || x<0 || y<0 || y>7 || p == null) {
			return;
		}
	
		else {
			remove(x, y);
			System.out.println("hi3");
			board[x][y] = p;
		}
    }



 	public Piece remove(int x, int y) {
	//Executes a remove. Returns piece that was removed. If index
	//is out of bounds, returns null and prints an appropriate 
	//message. If no piece at (x,, y), return null and print 
	//appropriate message.
		if (x>7 || x<0 || y>7 || y<0) {
			System.out.println("Position (" + x + ", " + y + ") " + "is out of bounds.");
			return null;
		}
		else if (board[x][y] == null) {
			System.out.println("No piece at (" + x + ", " + y + ").");
			return null;
		}
		else {
			//remove and return the piece.

			Piece pointer = board[x][y];
			board[x][y] = null;
			return pointer;
		}
 	}


 	 public boolean canEndTurn() {
// 	// /*Returns whether or not the current player can end their turn To be
// 	// able to end a turn, piece must have moved or performed a capture.
// 	// */
 	 	System.out.println("selected piece " + selectedPiece);
 	 	
	 	if (hasMoved == true) {
	 		return true;
	 	}
	 	else {
	 		return false;
	 	}
	 	// return true;
	}


public void endTurn() {
// 	// /*Called at the end of each turn. Handles switching of players and anything
// 	// else that should happen at the end of a turn.*/
	if (selectedPiece != null) {
		selectedPiece.doneCapturing();
	}
	//this is a comment
	selected = false;
	canSelectAnotherPiece = true;
	selectedPiece = null;
	hasMoved = false;

	if (turn == "fire") {
		turn = "water";
		return;
	}
	if (turn == "water") {
		turn = "fire";
		return;
	}
	System.out.println("Turn ended Now turn is " + turn);
	

 }


public String winner() {
// 	// /*Return winner of game. "Fire", "Water", "No one" (tie/ no pieces on the board)
// 	// or null if game is not oveer yet. Read spec for more. Determine winner by number
// 	// of pieces belonging to each team.*/
// 	// 	return 0;
	int numWaterPieces =0;
	int numFirePieces =0;	
  	for (int m=0; m<8; m+=1) {
 		for (int n=0; n<8; n+=1) {
 			if (board[m][n] != null && board[m][n].isFire() == true) {
 				//increase firepieces counter
 				
 				numFirePieces += 1;
 			}
 			if (board[m][n]!= null && board[m][n].isFire() == false) {
 				//put the piece in the water array
 				
 				numWaterPieces += 1;
 			}
 		}
 	}
 	System.out.println("prevvvvvvvvvvvvvvvvvvvvvvvx" + prevX);
 	System.out.println("preeevyyyyyyyyyyyyyyyyyyyyyyy" + prevY);
 	System.out.println(pieceAt(prevX, prevY));

 	// if (pieceAt(prevX, prevY) == null) {
 	// 	return "No one";
 	// }
 	
 	
 	// if (numFirePieces >0 && numWaterPieces == 0) {
 	// 	return "Fire";
 	// }
 	System.out.println("number of water pieces" + numWaterPieces);
 	System.out.println("num of fire pieces" + numFirePieces);
 	System.out.println("Turn" + turn);

 	
	// if (turn == "fire" && hasMoved == true && numWaterPieces ==0) {
 // 		return "fire";
 // 	}
 // 	if (turn == "water" && hasMoved == true && numFirePieces==0) {
 // 		return "water";
 // 	}
 	if (numFirePieces == 0 && numWaterPieces == 0) {
 		return "No one";
 	}

 	if (numFirePieces == 0 && numWaterPieces > 0) {
 		return "Water";
 	}
 	if (numFirePieces > 0 && numWaterPieces == 0) {
 		return "Fire";
 	}
 	if (numWaterPieces >0 && numFirePieces>0) {
 		return null;
 	}
 	// System.out.println(prevX);
 	// System.out.println(prevY);
 	// numFirePieces = 0;
 	// numWaterPieces = 0;
 	//  	System.out.println("number of water pieces" + numWaterPieces);
 	// System.out.println("num of fire pieces" + numFirePieces);
 	// System.out.println("Turn" + turn);
 return null;
}
} 