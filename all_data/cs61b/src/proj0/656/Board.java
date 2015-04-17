




public class Board {

	/** Location of pieces. */
	private Piece [][] piecesinfo; // array of pieces conatian all info of pieces/
	private int turn; //tracks whose turn it is. -1 is fire, 1 is water
	private int isselect; //tracks if something is selected. 0 if not, 1 if yes.
	private int hasmoved; //tracks if someting has moved
	private int bombmove;
	private Piece selectedpiece; //piece that is currently selected on board.
	private int selectedpiece_x; //x coord of selected piece
	private int selectedpiece_y; //y coord of selected piece
	
	

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

        public static void main(String[] args) {
        	Board b = new Board(false);
        	b.drawBoard();
        	b.play();      	
    	}

    	private void play(){

    		while (winner()==null) {
    			drawBoard();
    			StdDrawPlus.show(150);
        		if (StdDrawPlus.mousePressed()) {	
        			System.out.println("");
					int x = (int) StdDrawPlus.mouseX();						
					int y = (int) StdDrawPlus.mouseY();

					
					if (canSelect(x,y)) {
						select(x,y);
					}
					else{
						isselect=0;
						selectedpiece=null;
						StdDrawPlus.show(200);
					}
	        		//GAME STATUS
					System.out.println("selectedpiece is "+selectedpiece);
					if (selectedpiece != null) {
						System.out.println("selectedpiece_x and selectedpiece_y are ("+selectedpiece_x+","+selectedpiece_y+")");
						System.out.println("hasCaptured is "+selectedpiece.hasCaptured());
						System.out.println("validMove "+validMove(selectedpiece_x,selectedpiece_y,x,y));
						System.out.println("the piece at ("+x+","+y+") is a fire piece:"+ selectedpiece.isFire());	
					}
					System.out.println("canSelect returns "+canSelect(x,y));
					System.out.println("hasmoved is currently "+hasmoved);
					System.out.println("isselect is currently "+isselect);
					System.out.println("turn is "+turn);
					System.out.println("bombmove is "+ bombmove);
	        	}

	        	if (canEndTurn() && StdDrawPlus.isSpacePressed()){
					System.out.println("The player's turn has ended by pressing space");
					endTurn();
				}

        	}
        	System.out.println(winner()); 
    	}
 
 		private void drawBoard() {
        	StdDrawPlus.setXscale(0, 8);
        	StdDrawPlus.setYscale(0, 8);
        	for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if ((i + j) % 2 == 0) {
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
						StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					}else{
						StdDrawPlus.setPenColor(StdDrawPlus.CYAN);
						StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					}
					if (piecesinfo[i][j]!=null ) {      //&& piecesinfo[i][j].b == this
						if (piecesinfo[i][j].isKing()){
							if (piecesinfo[i][j].isBomb()) {
								if 	(piecesinfo[i][j].isFire() == true){
									StdDrawPlus.picture(i+0.5,j+0.5,"img/bomb-fire-crowned.png",1,1);
								}else{
									StdDrawPlus.picture(i+0.5,j+0.5,"img/bomb-water-crowned.png",1,1);
								}	
							}else if (piecesinfo[i][j].isShield()) {
								if 	(piecesinfo[i][j].isFire() == true){
									StdDrawPlus.picture(i+0.5,j+0.5,"img/shield-fire-crowned.png",1,1);
								}else{
									StdDrawPlus.picture(i+0.5,j+0.5,"img/shield-water-crowned.png",1,1);
								}	
							}else {
								if 	(piecesinfo[i][j].isFire() == true){
									StdDrawPlus.picture(i+0.5,j+0.5,"img/pawn-fire-crowned.png",1,1);
								}else{
									StdDrawPlus.picture(i+0.5,j+0.5,"img/pawn-water-crowned.png",1,1);
								}
							}
						} 
				//UNCROWNED PIECES
						if (!piecesinfo[i][j].isKing()) {
							if (piecesinfo[i][j].isBomb()) {
								if 	(piecesinfo[i][j].isFire() == true){
									StdDrawPlus.picture(i+0.5,j+0.5,"img/bomb-fire.png",1,1);
								}else{
									StdDrawPlus.picture(i+0.5,j+0.5,"img/bomb-water.png",1,1);
								}	
							}else if (piecesinfo[i][j].isShield()) {
								if 	(piecesinfo[i][j].isFire() == true){
									StdDrawPlus.picture(i+0.5,j+0.5,"img/shield-fire.png",1,1);
								}else{
									StdDrawPlus.picture(i+0.5,j+0.5,"img/shield-water.png",1,1);
								}	
							}else { //pawn
								if 	(piecesinfo[i][j].isFire() == true){
									StdDrawPlus.picture(i+0.5,j+0.5,"img/pawn-fire.png",1,1);
								}else{
									StdDrawPlus.picture(i+0.5,j+0.5,"img/pawn-water.png",1,1);
								}	
							}
						} // end of uncrowned piceces if 
					}// end of check if piece exists														
				} //end of for loop i
			}//end of for loop j
		//	SELECT HIGHLIGHT
	    	if (isselect==1) {
	    		String kind = "";
	    		String crown = "";
	    		String type ="";
	    		if (selectedpiece.isFire() != true) {
					kind = "-water";
				}else{
					kind = "-fire";
				}
				if (selectedpiece.isKing() == true) {
					crown = "-crowned";
				}
				if (selectedpiece.isBomb()){
					type="bomb";
				}else if (selectedpiece.isShield()) {
					type="shield";
				}else {
					type="pawn";
				}
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				StdDrawPlus.filledSquare(selectedpiece_x + .5, selectedpiece_y + .5, .5);
				StdDrawPlus.picture(selectedpiece_x + 0.5, selectedpiece_y + 0.5,"img/" + type + kind + crown +".png",1,1);
	    	}
	    }

    public Board (boolean shouldBeEmpty) {
	/* The constructor for Board. If shouldBeEmpty is true, 
	initializes an empty Board. Otherwise, initializes a Board with 
	the default configuration. Note that an empty Board could possibly 
	be useful for testing purposes. */
	if (shouldBeEmpty == true) {
		piecesinfo = new Piece[8][8];
		turn = -1;
		selectedpiece = null;
		hasmoved = 0;
		bombmove=0;
	}else {
		piecesinfo = new Piece[8][8];
		turn = -1;
		selectedpiece = null;
		hasmoved = 0;
		bombmove=0;
		isselect=0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ( i % 2 == 0 && j == 0){							
					piecesinfo[i][j] = new Piece(true, this,i,j,"pawn"); 			
				}else if ( i % 2 == 0 && j == 2) {
					piecesinfo[i][j] = new Piece(true, this,i,j,"bomb"); 
				}else if ( i % 2 == 0 && j == 6) {
					piecesinfo[i][j] = new Piece(false, this,i,j,"shield"); 
				}else if ( i % 2 != 0 && j == 1) {
					piecesinfo[i][j] = new Piece(true, this,i,j,"shield"); 
				}else if ( i % 2 != 0  && j == 5) {
					piecesinfo[i][j] = new Piece(false, this,i,j,"bomb"); 
				}else if ( i % 2 != 0  && j == 7) {
					piecesinfo[i][j] = new Piece(false, this,i,j,"pawn");
				}else{
					piecesinfo[i][j] = null;
				}	//end of drawing new board.																							
			} //end of for loop i
		}//end of for loop j
	}//end of else statement											
}//end of Board method

	   
		public Piece pieceAt (int x, int y) {
			/* Gets the piece at position (x, y) on the board, or 
			null if there is no piece. If (x, y) are out of bounds, returns null. */
			if (!goodclick(x,y)) {
				return null;
			}else if (piecesinfo[x][y] == null) {
				return null;
			} else {
				return piecesinfo[x][y];
			}
		}

		public boolean canSelect (int x, int y) {
		/*  Returns true if the square at (x, y) can be selected.

		A square with a piece may be selected if it is the corresponding player’s 
		turn and one of the following is true:

		The player has not selected a piece yet.
		The player has selected a piece, but did not move it.
		An empty square may be selected if one of the following is true:

		During this turn, the player has selected a Piece which hasn’t moved yet and is 
		selecting an empty spot which is a valid move for the previously selected Piece.
		During this turn, the player has selected a Piece, captured, and has 
		selected another valid capture destination. When performing multi-captures, you 
		should only select the active piece once; all other selections should be valid 
		destination points. */
			if (bombmove==1){
				return false;
			}
			if (turn==-1){
				if (piecesinfo[x][y] !=null) {
					if (selectedpiece==null && piecesinfo[x][y].isFire()){
						return true;
					}else if (hasmoved==0 && piecesinfo[x][y].isFire()){
						return true;
					}
				}else{
					if (isselect==1 && hasmoved==0 && validMove(selectedpiece_x,selectedpiece_y,x,y)) {
						return true;
					}else if (isselect==1 && selectedpiece.hasCaptured() && validCapture(selectedpiece_x,selectedpiece_y,x,y)){
						return true;
					}
				}
			}else{
				if (piecesinfo[x][y] !=null) {
					if (selectedpiece==null && !piecesinfo[x][y].isFire()){
						return true;
					}else if (hasmoved==0 && !piecesinfo[x][y].isFire()){
						return true;
					}
				}else{
					if (isselect==1 && hasmoved==0 && validMove(selectedpiece_x,selectedpiece_y,x,y)) {
						return true;
					}else if (isselect==1 && selectedpiece.hasCaptured() && validCapture(selectedpiece_x,selectedpiece_y,x,y)){
						return true;
					}
				}
			}
			return false;
		} 
			

	private boolean validCapture (int xi, int yi, int xf, int yf) {
		if (piecesinfo[xf][yf] !=null) {
			return false;
		}else if( xi == xf && yi == yf) { 
			return false;
		}else if (indicatecapture(xi,yi,xf,yf)){
				if (selectedpiece.isKing()){
					if (selectedpiece.isFire()) {
						return firekingcapture_check(xi,yi,xf,yf);
					}else{
						return waterkingcapture_check(xi,yi,xf,yf);
					}
				}else{
					if (selectedpiece.isFire()) {
						return firecapture_check(xi,yi,xf,yf);
					}else{
						return watercapture_check(xi,yi,xf,yf);
					}
				}
		}else {
			return false;
		}
	}

	private boolean validMove (int xi, int yi, int xf, int yf) {
		/*Returns true if the piece at (xi, yi) can either move to (xf, yf) or 
		capture to (xf, yf) in a valid fashion compatible with the rules. */
		if (piecesinfo[xf][yf] !=null) {
			return false;
		}else if( xi == xf && yi == yf) { 
			return false;
		}else{
			if (indicatecapture(xi,yi,xf,yf)){
				if (selectedpiece.isKing()){
					if (selectedpiece.isFire()) {
						return firekingcapture_check(xi,yi,xf,yf);
					}else{
						return waterkingcapture_check(xi,yi,xf,yf);
					}
				}else{
					if (selectedpiece.isFire()) {
						return firecapture_check(xi,yi,xf,yf);
					}else{
						return watercapture_check(xi,yi,xf,yf);
					}
				}
			}else if (!indicatecapture(xi,yi,xf,yf)) {
				if (selectedpiece.isKing()){
					return kingnormalmove_check(xi,yi,xf,yf);		
				}else{
				
					if (selectedpiece.isFire()) {
						return firenormalmove_check(xi,yi,xf,yf);
					}else{
						return waternormalmove_check(xi,yi,xf,yf);
					}
				}
			}else{
				return false;
			}	
		}
	}
			


	private boolean waterkingcapture_check(int xi, int yi, int xf, int yf){
		
		if (xf == xi-2 && yf == yi+2) {				 
				if (piecesinfo[xi-1][yi+1] != null && pieceAt(xi-1,yi+1).isFire()){			
						return true;
				}
		}else if (xf==xi-2 && yf == yi-2) { 
				if (piecesinfo[xi-1][yi-1] != null && pieceAt(xi-1,yi-1).isFire()){
						return true;
				}
		}else if (xf==xi+2 && yf == yi-2) { 
				if (piecesinfo[xi+1][yi-1] != null && pieceAt(xi+1,yi-1).isFire()){
						return true;
				}
		}else if (xf==xi+2 && yf == yi+2) { 
				if (piecesinfo[xi+1][yi+1] != null && pieceAt(xi+1,yi+1).isFire()){
						return true;
				}
		}
		return false;
	}

	private boolean firekingcapture_check(int xi, int yi, int xf, int yf){
		if (xf == xi+2 && yf == yi+2) {	//capture right, up fire
				if (piecesinfo[xi+1][yi+1] != null && !pieceAt(xi+1,yi+1).isFire()){
						return true;
				}
		}else if (xf==xi-2 && yf == yi+2) { //capture left,up fire
				if (piecesinfo[xi-1][yi+1] != null && !pieceAt(xi-1,yi+1).isFire()){
						return true;
				}
		}else if (xf==xi-2 && yf == yi-2) { //capture left, down fire
				if (piecesinfo[xi-1][yi-1] != null && !pieceAt(xi-1,yi-1).isFire()){
						return true;
				}
		}else if (xf==xi+2 && yf == yi-2) { //capture right, down  fire
				if (piecesinfo[xi+1][yi-1] != null && !pieceAt(xi+1,yi-1).isFire()) {
						return true;
				}
		}
		return false;
	}

	private boolean kingnormalmove_check(int xi, int yi, int xf, int yf) {
		if ( xf == xi+1 && yf == yi-1 ){   //normal move 
				return true;			
		}else if (xf == xi+1 && yf == yi+1 ) {
				return true;
		}else if (xf == xi-1 && yf == yi+1 ) {
				return true;
		}else if (xf == xi-1 && yf == yi-1 ) {								
				return true;
		}
		return false;	
	}

	private boolean waternormalmove_check(int xi, int yi, int xf, int yf){
		 if (xf == xi+1 && yf == yi-1) {   //normal move water
				return true;				
		}else if (xf == xi-1 && yf == yi-1 ) { 
				return true;
		}
		return false;
	}

	private boolean firenormalmove_check(int xi, int yi, int xf, int yf){
		if (xf == xi+1 && yf == yi+1) {   //normal move water
				return true;		
		}else if (xf == xi-1 && yf == yi+1 ) { 
				return true;
		}
		return false;
	}

	private boolean firecapture_check(int xi, int yi, int xf, int yf){
		if (xf==xi+2 && yf == yi+2) {
				if (piecesinfo[xi+1][yi+1] != null && !pieceAt(xi+1,yi+1).isFire()){
					return true;
				}
		}else if (xf==xi-2 && yf == yi+2) { 
				if (piecesinfo[xi-1][yi+1] != null && !pieceAt(xi-1,yi+1).isFire()){
					return true;
				}
		}
		return false;
	}

	private boolean watercapture_check(int xi, int yi, int xf, int yf){
		if (xf == xi-2 && yf == yi-2) {				
				if (piecesinfo[xi-1][yi-1] != null && pieceAt(xi-1,yi-1).isFire()) {	
						return true;
				}
		}else if (xf==xi+2 && yf == yi-2) { 
				if (piecesinfo[xi+1][yi-1] != null && pieceAt(xi+1,yi-1).isFire()) {
						return true;
				}
		}
		return false;
	}


	private boolean indicatecapture(int xi, int yi, int xf, int yf){
		if (Math.abs(xf-xi) == 2 && Math.abs(yf-yi) == 2){
			return true;
		}
		return false;	
	}


	public void select(int x, int y) {
		/* Selects the square at (x, y). This method assumes canSelect (x,y) 
		returns true. Optionally, it is recommended to color the background of 
		the selected square white on the GUI via the pen color function. For
		 any piece to perform a capture, that piece must have been selected first.
		  If you select a square with a piece, you are prepping that piece for 
		  movement. If you select an empty square (assuming canSelect returns true),
		   you should move your most recently selected piece to that square. */
		// String kind = "-fire";
		// String crown = "";

			if (piecesinfo[x][y]!=null) {
				System.out.println("the piece at ("+x+","+y+") is now selected");
				selectedpiece=piecesinfo[x][y];
				selectedpiece_x=x;
				selectedpiece_y=y;
				isselect = 1;
			}else if (isselect==1){
				if (selectedpiece.isBomb() && indicatecapture(selectedpiece_x,selectedpiece_y,x,y)){
					bombmove=1;
				}
				selectedpiece.move(x,y);
				hasmoved=1;
				selectedpiece_x=x;
				selectedpiece_y=y;
			}			 
	}

	public void place (Piece p, int x, int y) {
		/* Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. 
		If another piece already exists at (x, y), p will replace that piece. (This method is 
			potentially useful for creating specific test circumstances.) */
		if (p == null){ //doesn't exist
			return;
		}else if (!goodclick(x,y)) { 	//out of bounds
			return;
		}else{
			piecesinfo[x][y]=p;
			//hasmoved=1;
		}
	}

	public Piece remove(int x, int y) {
		/*  Executes a remove. Returns the piece that was removed. If the input (x, y) is out of bounds,
	 	returns null and prints an appropriate message. If there is no piece at (x, y), returns null and 
	 	prints an appropriate message. */
	 	if (!goodclick(x,y)){
	 		System.out.print("The selected (x,y) and are out of bounds.");
	 		return null;
	 	}
	 	if (piecesinfo[x][y] == null){
	 		 System.out.println("There is no piece at the selected (x,y).");
	 		return null;
	 	} else {
	 		Piece die = pieceAt(x,y); //moves piece off board, erase pieceinfo, return piece.
			System.out.println("the piece at ("+x+","+y+") has been removed");
			piecesinfo[x][y]=null;
			System.out.println("piecesinfo[x][y] at ("+x+","+y+") is "+piecesinfo[x][y]);
			return die;
	 	}

	 }

	 public boolean canEndTurn() {
		/* Returns whether or not the the current player can end their turn. To be able to end a turn, 
		a piece must have moved or performed a capture. */
		if (hasmoved==1){
			return true;
		}
		return false;
	}

	private boolean goodclick(int x, int y) {
		//checks if click is within range. false if bad, good if true
		if (x<8 && x>=0 && y<8 && y>=0) {
			return true;
		}
		return false;
	}

	public void endTurn()  {
		/* Called at the end of each turn. Handles switching of players 
		and anything else that should happen at the end of a turn. */
		turn = turn * -1;
		if (selectedpiece != null){
			selectedpiece.doneCapturing();
		}
		hasmoved=0;
		bombmove=0;
		selectedpiece=null;
		isselect=0;
	}

	public String winner()  {
		/* Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board),
	 	or null if the game is not yet over. Assume there is no stalemate situation in which the 
	 	current player has pieces but cannot legally move any of them (In this event, just leave 
	 		it at null). Determine the winner solely by the number of pieces belonging to each team. */
		int countfire = 0;
		int countwater = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (piecesinfo[i][j] != null) {
					if (piecesinfo[i][j].isFire()) {
						countfire = countfire + 1;
					}else {
						countwater = countwater + 1;
					}
				}
			}
		}
		if (countwater == 0 && countfire == 0){
			return "No one";
		}else if (countfire == 0 && countwater != 0) {
			return "Water";
		}else if (countfire != 0 && countwater == 0) {
			return "Fire";
		}
		return null;
	}
}




