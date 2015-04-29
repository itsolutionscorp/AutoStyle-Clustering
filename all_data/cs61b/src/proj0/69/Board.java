public class Board {
	private int size = 8;
	private Piece[][] pieces;

	private boolean isFireturn = true;
	private boolean fireselect = false;
	private boolean firemove = false;
	private boolean waterselect = false;
	private boolean watermove = false;
	private Piece currentselection;
	private int currentselectionx = -1;
	private int currentselectiony = -1;


	public static void main(String[] args) {
		//Draw the freakin board
		Board gameboard = new Board(false);
		double x;
        double y;
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		//Game Logic
        gameboard.drawBoard();
		while(true) {
			/** let's take this nice and slowly...
			1. you need to draw the board (initializing does it for ya)
			2. search for where the mouse clicked
			3. check if the piece is selectable
			4. if it is, select it (notice while selecting (before spacebar), the backgroundcolor is white)
			5. you select another place to move the piece (note it can or cannot be movable)
			6. you press the spacebar and it is the opponent's turn
			*/
			if (gameboard.winner() != null){
				System.out.println(gameboard.winner());
				break;
			}
            while (!StdDrawPlus.mousePressed() && !StdDrawPlus.isSpacePressed()) {
            	
            }
            if (StdDrawPlus.mousePressed()){
            	

            	x = StdDrawPlus.mouseX();
                y = StdDrawPlus.mouseY();

                

                if (gameboard.canSelect((int)x, (int)y)){

                	if ((gameboard.currentselectionx + gameboard.currentselectiony) >= 0){
                		if (gameboard.pieceAt(gameboard.currentselectionx, gameboard.currentselectiony) != null){
                			gameboard.unhighlight(gameboard.currentselectionx, gameboard.currentselectiony);
                		}
                	
                }
                	if (gameboard.pieceAt((int)x, (int)y) != null){

                		gameboard.select((int)x, (int)y);

                		gameboard.highlight((int)x, (int)y);

                	}
                	else{
                		gameboard.cleanthesquare(gameboard.currentselectionx, gameboard.currentselectiony);

                		gameboard.select((int)x, (int)y);
                	
                		gameboard.cleanallBoard();
                		

                		if (gameboard.pieceAt((int)x, (int)y) != null){
                			gameboard.drawonepic((int)x, (int)y);
                			gameboard.highlight((int)x, (int)y);
                			
                		}
                		else {
          					gameboard.currentselection = null;
                			gameboard.currentselectionx = -1;
                			gameboard.currentselectiony = -1;
                			
                			
                		}
                		
                	}
                }
            
            }

                

            if (StdDrawPlus.isSpacePressed()){
            	
            	if (gameboard.canEndTurn()){

            		gameboard.endTurn();
            		if ((gameboard.currentselectionx + gameboard.currentselectiony) >= 0){
            		gameboard.unhighlight(gameboard.currentselectionx, gameboard.currentselectiony);
            	}
            }
        }
              

            StdDrawPlus.show(100);
            }            
        
        

	}
	private void drawonepic(int x, int y){
		String crowned = "";

		if (pieceAt(x,y).isKing()){
			crowned = "-crowned";
					}
		if (pieceAt(x, y).isFire()){
        	if (pieceAt(x, y).isBomb()){
        		StdDrawPlus.picture(x + .5, y + .5, "img/" + "bomb" +"-fire" + crowned +".png", 1, 1);
        	}
        	else if (pieceAt(x, y).isShield()){
        		StdDrawPlus.picture(x + .5, y + .5, "img/" + "shield" +"-fire" + crowned +".png", 1, 1);
      		}
      		else {
      			StdDrawPlus.picture(x + .5, y + .5, "img/" + "pawn" +"-fire" + crowned +".png", 1, 1);
      		}
      	}
      	else {
        	if (pieceAt(x, y).isBomb()){
        		StdDrawPlus.picture(x + .5, y + .5, "img/" + "bomb" +"-water" + crowned +".png", 1, 1);
        	}
        	else if (pieceAt(x, y).isShield()){
        		StdDrawPlus.picture(x + .5, y + .5, "img/" + "shield" +"-water" + crowned +".png", 1, 1);
      		}
      		else {
      			StdDrawPlus.picture(x + .5, y + .5, "img/" + "pawn" +"-water" + crowned +".png", 1, 1);
      		}
      	}
	}


	// clean all boards
	private void cleanallBoard(){

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++){
				if (pieceAt(i, j) == null){
					cleanthesquare(i, j);
				}
			}
		}
	}
	//unhighlight
	private void unhighlight(int x, int y){

		cleanthesquare(x, y);
		drawonepic(x, y);	
    }    	


	// Draw the empty board first;
	private void drawBoard(){

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++){
				cleanthesquare(i,j);
			}
		}


		//Draw Pieces
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++){
				if (pieceAt(i, j) != null) {
					drawonepic(i, j);
				}
					}
				}
			}
	//true if there is piece to capture
	// x and y are the piece's current position
	// a and b is the goal position

	private void cleanthesquare(int x, int y){
		if ((x + y) % 2 == 0) {
				StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
				}
        else{
                StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
                }

            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	}

	private boolean cancapture(int x, int y, int a, int b){
		int btx = (x + a)/2;
		int bty = (y + b)/2;

		return (pieceAt(btx, bty) != null) && (pieceAt(btx, bty).isFire() != currentselection.isFire()) && (pieceAt(a, b) == null);
	}

	//highlight a cell assume the cell must contain a piece
	private void highlight(int x, int y){

		if (pieceAt(x, y) != null){
		String crowned = "";
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare((int) x + 0.5, (int)y + 0.5, .5);
        drawonepic(x, y);
	}
}

	private void initPieces(boolean isFire, Board gameboard) {

		if (isFire) {
		// place the fire pieces
		for (int j = 0; j < 3; j+=1){
			if (j == 0) {
				for (int i = 0; i < size; i++){
					if (i % 2 == 0){
					pieces[i][j] = new Piece(true, gameboard, i, j, "pawn");
				}
				}
			}

			if (j == 1) {
				for (int i = 0; i < size; i++){
					if (i % 2 != 0){
					pieces[i][j] = new Piece(true, gameboard, i, j, "shield");
				}
				}
			}

			if (j == 2) {
				for (int i = 0; i < size; i++){
					if (i % 2 == 0){
					pieces[i][j] = new Piece(true, gameboard, i, j, "bomb");
				}
				}
			}
		}

	}
	else {
		for (int j = 7; j > 4; j-=1){
			if (j == 7) {
				for (int i = 0; i < size; i++){
					if (i % 2 != 0){
					pieces[i][j] = new Piece(false, gameboard, i, j, "pawn");
				}
				}
			}

			if (j == 6) {
				for (int i = 0; i < size; i++){
					if (i % 2 == 0){
					pieces[i][j] = new Piece(false, gameboard, i, j, "shield");
				}
				}
			}

			if (j == 5) {
				for (int i = 0; i < size; i++){
					if (i % 2 != 0){
					pieces[i][j] = new Piece(false, gameboard, i, j, "bomb");
				}
				}
			}
		}
	}
	}



	/** if it should be Empty, initializes an empty board, default otherwise. */
	public Board(boolean shouldBeEmpty){
		
		if (shouldBeEmpty){
			 pieces = new Piece[size][size];
		}

		// in addition to the board, also build the pieces :)
		// place the initial pieces
		else {
			pieces = new Piece[size][size];
			initPieces(true, this);
			initPieces(false, this);

	}
	}

	// Get the piece at position (x, y) at the board

	public Piece pieceAt(int x, int y){
		if (x < size && y < size){
			return pieces[x][y];
		}
		else {
			return null;
		}

	}

	// True if the piece at that location is selectable

	public boolean canSelect(int x, int y) {
		// when can you select something?
		//think in two stages
		/** first stage:  if it is your turn and you haven't selected yet and the place you 
		lick have a piece

		/** after you click the piece: the only place you can click is the place that it is allowed to go
		regular piece, two upper corners if there is no piece there
		king: front and back

		// Note if you captured a piece, you can continue to select the top right corner if there is a piece
			(except the bomb, which is pretty much gone after the first select)
		// buggy
		*/

		// only true if you alread selected a piece
		
		//stage 1: just selecting the piece
		if (pieceAt(x, y) != null){
			
		

			if (pieceAt(x, y).isFire() && isFireturn) {
				if (!firemove){
				return true;
				}
			}

			else if (!pieceAt(x, y).isFire() && !isFireturn) {
				if (!watermove){
					return true;
				}
			
		}

	}
	//stage 2: selecting the appropriate placement

			else{
				if (currentselection != null){
				
				

				if (fireselect && currentselection.isFire()) {

					if (!firemove || currentselection.hasCaptured()){

						// bomb can only move once
						if (firemove && currentselection.isBomb()){
							currentselection = null;
							currentselectionx = -1;
							currentselectiony = -1;
							System.out.println("cannot select");
							return false;
						}
						if ((!firemove  || currentselection.hasCaptured()) && (Math.abs(currentselectionx - x) == 2) && (y - currentselectiony == 2) && cancapture(currentselectionx, currentselectiony, x, y)){
						return true;
					}

						else if (!firemove && ((Math.abs(currentselectionx - x) == 1) && (y - currentselectiony == 1))){
							return true;
					}
						else if (currentselection.isKing()){
							if ((Math.abs(currentselectionx - x) == 2) && (Math.abs(y - currentselectiony) == 2) && cancapture(currentselectionx, currentselectiony, x, y)){
								return true;
					}

							else if (!firemove &&(Math.abs(currentselectionx - x) == 1) && (Math.abs(y - currentselectiony) == 1)){
								return true;
					}

					}
					
					
					
				}
			}

				else if (waterselect && !currentselection.isFire()) {
					if (!watermove || currentselection.hasCaptured()){

						if (watermove && currentselection.isBomb()){
							currentselection = null;
							currentselectionx = -1;
							currentselectiony = -1;
							System.out.println("cannot select");
							return false;
						}
						if ((!watermove  || currentselection.hasCaptured())&&(Math.abs(currentselectionx - x) == 2) && (currentselectiony - y == 2)&& cancapture(currentselectionx, currentselectiony, x, y)){
							return true;
						}
						else if (!watermove &&(Math.abs(currentselectionx - x) == 1) && (currentselectiony - y == 1)){
							return true;

						}
						else if (currentselection.isKing()){
							if ((Math.abs(currentselectionx - x) == 2) && (Math.abs(y - currentselectiony) == 2) && cancapture(currentselectionx, currentselectiony, x, y)){
								return true;
						}

							else if (!watermove &&(Math.abs(currentselectionx - x) == 1) && (Math.abs(y - currentselectiony) == 1)){
								return true;
					}

					}

					}
					
				}
			}
			}	
		
		

	



	return false;
	}


	// selecting the piece

	public void select(int x, int y) {

		if (pieceAt(x, y) != null) {
			
			if (pieceAt(x, y).isFire()) {
				this.fireselect = true;
				currentselection = pieceAt(x, y);
				currentselectionx = x;
				currentselectiony = y;
			}
			else {
				this.waterselect = true;
				currentselection = pieceAt(x, y);
				currentselectionx = x;
				currentselectiony = y;
			}

		}
		else if(currentselection != null) {
			currentselectionx = x;
            currentselectiony = y;
			currentselection.move(x, y);
			

            if(currentselection.isFire()){
            	firemove = true;
            }
            else{
            	watermove = true;
            }
            

		}
}
		
	

	// relocating(placing) the piece if possible, notice the piece is already created

	public void place(Piece p, int x, int y) {

		if (x < size && y < size){
			pieces[x][y] = p;

		}
		else {
			return;
		}

		

	}

	// remove the piece at x/y location and return it
	// if not possiblem return null and print error message

	public Piece remove(int x, int y) {
		if (pieces[x][y] != null) {
			if (x <= size && y <= size){
				Piece removed = pieces[x][y];
				pieces[x][y] = null;
				return removed;
			}
			else {
				System.out.println("Error! out of the board");
			}
		}
		else {
			System.out.println("Error!  no pieces that the cell");
			return null;
		}
		
		return null;
	}

	/** return if the player can end their turn
	// Note the player must make a move */

	public boolean canEndTurn() {
		if ((isFireturn && firemove) || (!isFireturn && watermove)){
			return true;
		}
		return false;
	}

	/** End each turn by switching players */

	public void endTurn() {
		if (currentselection != null){
			currentselection.doneCapturing();
		}
		
		isFireturn = !isFireturn;
		firemove = false;
		watermove = false;
		fireselect = false;
		waterselect = false;

	}

	/** return the winner: "Fire" or "Ice"(Water, but ice sounds way cooler) 
	// and also tie or null if game is not over
	// note if neither player can make a valid move, stop the game and declare the winner 
	/who has the most pieces */

	public String winner() {
		boolean firedead = true;
		boolean waterdead = true;
		for (int i = 0; i < size; i+=1){
			for (int j = 0; j < size; j += 1){
				if (pieceAt(i, j) != null){
					if (pieceAt(i, j).isFire()){
						firedead = false;
					}
					else{
						waterdead = false;
					}
					
				}
				

		}
	}

		if (firedead && waterdead){
			return "No one";
		}
		else if (firedead){
			return "Water";
		}
		else if (waterdead){
			return "Fire";
		}
		else{
			return null;
		}
}
}