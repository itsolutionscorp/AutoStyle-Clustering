public class Board{

	private static Piece[][] alive = new Piece[8][8]; //the multidimensional arrays that stores pieces. 
	private int turn = 0; //turn counter. When it is 0 fire plays when it is 1 water plays. // should be reset by the end turn method. 
	private boolean selected = false; // tracks if a player has selected a piece during his turn 
	private boolean hasmoved = false; // keeps track of the movements of a piece during its turn. 
	private int sx; // current piece's x and y coordinates 
	private int sy;
	private boolean captured; // different from hascaptures in Piece method. Boolean made to check it can playagain.
	

	public Board(boolean shouldBeEmpty){ // intialises an empty board. 
		if(shouldBeEmpty){
			for (int i = 0; i < 8; i++) {
        		for (int j = 0; j < 8; j++) {
	                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	                else StdDrawPlus.setPenColor(StdDrawPlus.RED);
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	                alive[i][j] = null;
    	        }
        	}
		}
		else{
	    	for (int i = 0; i < 8; i++) {
	    		for (int j = 0; j < 8; j++) {
			        if ((i + j) % 2 == 0) {
			        	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			        }
			        else {
			        	StdDrawPlus.setPenColor(StdDrawPlus.RED);
			        }
			        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
			        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			    	if((i+j) % 2 == 0){
			    		String type = "";
			    		switch (j) {
			    			case 0:
			    				type = "pawn-fire";
			    				break;
			    			case 1:
			    				type = "shield-fire";
			    				break;
			    			case 2:
			    				type = "bomb-fire";
			    				break;
			    			case 5:
			    				type = "bomb-water";
			    				break;
			    			case 6:
			    				type = "shield-water";
			    				break;
			    			case 7:
			    				type = "pawn-water";
			    				break;
			    			default:
			    				break;
			    		}
			    		if (!type.equals("")) {
			    			alive[i][j] = new Piece(j < 3,this,i,j, type);
			    		}
					}
					else{
						alive[i][j] = null;
					}
				}
			}
		}
	}
	//Used by can select. 
	public Piece pieceAt(int x, int y){ // gets piece at position x, y on the board or null if no piece/out of bound
		if (x < 0 || y < 0 || x >= 8 || y >= 8) {
			return null;
		}
		return alive[x][y];
	}

	public boolean canSelect(int x, int y){// indicates if square can be selected. 
		// if square has piece. True if player has not yet selected piece or has selected piece but has not moved it.
		// if square doesnt have piece. True if player has selected a piece but did not move it and square isempty
		// 								True if next move is also a valid capture
		//it is going to be called in the while loop with the mouse input.  
		if(pieceAt(x,y) != null){//if square has a piece.
			if((turn == 0 && pieceAt(x,y).isFire()) || (turn == 1 && !pieceAt(x,y).isFire())){ 
				if(!selected || selected && !hasmoved){
					return true;
				}
				else{ return false;}
			}
			else{
				return false;
			}
		}
		else{
			if((selected && !hasmoved || iscapture(x, y)) && moveIsValid(x, y)){  
				return true;
			}
			else{
			return false;
			}
		}
	}

	private boolean iscapture(int x, int y){ // Helper method to canSelect. 
		if(selected){//only runs if a piece has been selected
			if(!pieceAt(sx, sy).isKing()){
				if(pieceAt(sx, sy).isFire()){
					if((pieceAt(x-1,y-1) != null && !pieceAt(x-1, y-1).isFire() && (x-2) == sx && sy == (y-2))
						|| (pieceAt(x+1, y-1) != null && !pieceAt(x+1, y-1).isFire() && (x+2) == sx && (y-2) == sy)){
						return true;
					}
					else{return false;}
				}
				else{
					if((pieceAt(x+1, y+1) != null && pieceAt(x+1, y+1).isFire() && (x+2) == sx && (y+2) == sy)
						|| (pieceAt(x-1, y+1) != null && pieceAt(x-1, y+1).isFire() && (x-2) == sx && (y+2) == sy)) {
						return true;
					}
					else{return false;}
				}
			}
			else{
				boolean fire = pieceAt(sx, sy).isFire();
				if((pieceAt(x-1,y-1) != null && pieceAt(x-1, y-1).isFire() != fire && (x-2) == sx && sy == (y-2))
					|| (pieceAt(x+1, y-1) != null && pieceAt(x+1, y-1).isFire() != fire && (x+2) == sx && (y-2) == sy)
					|| (pieceAt(x+1, y+1) != null && pieceAt(x+1, y+1).isFire() != fire && (x+2) == sx && (y+2) == sy)
					|| (pieceAt(x-1, y+1) != null && pieceAt(x-1, y+1).isFire() != fire && (x-2) == sx && (y+2) == sy)){
						return true;
				}
				else{
					return false;
				}
			}
		}			
		else{
			return false;
		}
	}

	private void capture(int x, int y){// going to be called only if iscapture has evaluated to true. 
		captured = true;
		if(pieceAt(sx, sy).isBomb()){
			for(int i = -1; i<=1; i++){
				for(int j = -1; j<=1; j++){
					if(pieceAt(x+i, y+j) != null && !pieceAt(x+i, y+j).isShield()){
						remove(x+i, y+j);
					}
				}
			}
			remove(sx, sy);
		}
		remove((x + sx)/2, (y + sy)/2);
	}

	public void select(int x, int y){// only works if canSelect evalutes to True. 
		// if the square is full, select piece and change color background
		// is square is empty move piece to selected square. Calls the place method to do so.
		if (captured && pieceAt(sx, sy) == null) {
			return;
		}

		if(canSelect(x, y)){
			if(pieceAt(x,y) != null){
				if (selected) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					StdDrawPlus.filledSquare(sx + .5, sy + .5, .5);
					pieceAt(sx, sy).move(sx, sy);
				}

				StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                sx = x;
                sy = y;
                pieceAt(x, y).move(x, y);
                selected = true;
			}
			else{ 
				if(iscapture(x, y)){  
					capture(x, y); 
				}
				if (pieceAt(sx, sy) != null) {
					Piece p = remove(sx, sy);
					place(p, x, y);//moves the piece in piece array
					pieceAt(x,y).move(x, y);//changes the piece's place on the drawn board.
				}
				sx = x;
				sy = y;
				hasmoved = true; 
			}
		}

	}
	private boolean moveIsValid(int x, int y){ //helper to place.  Is going to check whether move is valid. 
		/*going operate using being moved positions and (x, y) being coordinates to destination. 
		**first we want to check if it has a valid move and is not king. 
		**then we will verify the validity of its move it is a king.*/
		if(!pieceAt(sx, sy).isKing()){
			if(hasmoved && captured || !hasmoved && !captured){
				if(pieceAt(sx, sy).isFire()){//fire is moving up,can only move one up in diagonals unless capturing. 
					if(x-1 == sx && y-1 == sy || x+1 == sx && y-1 == sy || iscapture(x, y)){
						return true;
					}
					else{
						System.out.println("firepiece move is not valid"); return false;
					}
				}
				else{
					if(x-1 == sx && y+1 == sy || x+1 == sx && y+1 == sy || iscapture(x, y)){
						return true;
					}
					else{
						System.out.println("waterpiece move is not valid"); return false;
					}
				}
			}
			else{
				return false;
			}		
		}
		else{//can move in any direction
			if(hasmoved && captured || !hasmoved && !captured){
				if(Math.abs(x-sx)==1 && Math.abs(y-sy) == 1 || iscapture(x, y)){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}	
		}
	}

	public void place(Piece p, int x, int y){//CALLED BY: SELECT, moves piece p at position x,y
		// does nothing if coordinates out of bound, or p is null it does nothing. 
		// if p already exist it first removes it from original position. Calls the remove method. 
		// if another piece exists at x,y p will replace that piece
		if (x < 0 || x > 7 || y < 0 || y > 7 || p == null) {
			return;
		}
		alive[x][y] = p;
		p.move(x,y);
	}

	public Piece remove(int x, int y){// removes piece at x, y and returns it. CALLED BY: PLACE
		// if x,y out of bounds or no piece at x,y returns null and prints appropriate message. 
		if(pieceAt(x,y) != null){
			Piece temphold = pieceAt(x, y);
			alive[x][y] = null;
			if((x + y) % 2 == 0){
				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			}
			else {
				StdDrawPlus.setPenColor(StdDrawPlus.RED);
			}
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			return temphold;
		}
		else {
			return null;
		}
	}

	public boolean canEndTurn(){// is true if player has moved a piece or preformed a capture
		if(hasmoved || captured){
			return true;
		}
		else{
			return false;
		}
	}

	public void endTurn(){// is called at the end of each turn. Handles player switch 
		// is ran when spacebar is pressed. 
		// if winning situation call winner method --> would that cause on error though? 
		//accordingly changes the turn counter.
		//
		hasmoved = false;
		selected = false;
		captured = false;
		if(turn == 0) {
			turn = 1;
		}
		else {
			turn = 0;
		}
	}

	public String winner(){// returns the winner of the game. "Fire", "Water", or "No one" or null if game not over.
	// ?? should this always be executed at end of turn?? ??is there a winner when other team not left?? 
	// ??if loops to run at end of each turn??
	int waterpieces = 0;
	int firepieces  = 0;
		for(Piece[] i: alive){
			for(Piece p: i){
				if (p == null) {
					continue;
				}
				if(p.isFire()){
					firepieces += 1;
				}
				else{
					if(!p.isFire()){
						waterpieces += 1;
					}
				}
			}
		}
		if(waterpieces == 0 && firepieces == 0){
			return "No one";
		}
		else{
			if(waterpieces == 0){
				return "Fire";
			}
			else{
				if(firepieces == 0){
					return "Water";
				}
				else{
					return null;
				}
			}
		}
	}

	public static void main(String[] args){ // this is going to need a turn system. 
		boolean notempty = false; 
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board field = new Board(notempty); // board init
		while(true){
			if (StdDrawPlus.mousePressed()) { //only when mouse clicks on a square. 
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int mousx = (int)x;
                int mousy = (int)y;
                field.select(mousx,mousy);  
            }
            else{
            	if(StdDrawPlus.isSpacePressed()){
            		if(field.canEndTurn()){
            			field.endTurn();
            		}
            		if(field.winner() != null){
						System.out.println(field.winner());
						return;
            		}
            	}
            }
            StdDrawPlus.show(100);
		}
	}
}

