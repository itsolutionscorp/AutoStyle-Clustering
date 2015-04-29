public class Board{
	//PRIVATE instance variables  READY FOR GRADE
	private Piece[][] standardPieces = new Piece[8][8];
	private int widthAndHeight = 8;
	private boolean turn = true; //TRUE = fire turn & FALSE = water turn
	private int highlightX;
	private int highlightY;
	private boolean hasSelected = false; //TRUE when piece has been selected FALSE at End Turn or When Bomb Explodes --> need to be True in order to select a blank space without a capture.  
	private int lastXSelected;
	private int lastYSelected;
	private String selectedPicture = null; //GUI ONLY
	private boolean pieceMoved; //TRUE when piece moves to blank spae the first time False at End Turn --> When TRUE disable moving w/o capture or selecting a piece.  
	private boolean shouldBeEmpty;


	//APi PASSS
	public Board(boolean shouldBeEmpty){
		if (shouldBeEmpty == false){
			for (int i = 0; i < 4; i++){
				this.standardPieces[i*2][2] = new Piece(true, this, i*2, 2, "bomb");
			}
			for (int j = 0; j < 4; j++){
				this.standardPieces[j*2][0] = new Piece(true, this, j*2, 0, "pawn");
			}
			for (int k = 0; k < 4; k++){
				this.standardPieces[k*2+1][1] = new Piece(true, this, k*2 + 1, 1, "shield");
			}
			for (int l = 0; l < 4; l++){
				this.standardPieces[l*2+1][5] = new Piece(false, this, l*2 + 1, 5, "bomb");
			}
			for (int m = 0; m < 4; m++){
				this.standardPieces[m*2][6] = new Piece(false, this, m*2, 6, "shield");
			}
			for (int n = 0; n < 4; n++){
				this.standardPieces[n*2+1][7] = new Piece(false, this, n*2 + 1, 7, "pawn");
			}
		}
	}

	public Piece pieceAt(int x, int y){
		//Gets (x, y) on the board, or null if there is no piece. If (x, y) are out of bounds, returns null.
		//SETS NO VALUES
		if (outOfBounds(x,y)==true){
			return null;
		}
		else if (standardPieces[x][y]!=null){
				 return standardPieces[x][y];
		}
		else {
			return null;
		}
	}

	public boolean canSelect(int x, int y){ 
		//Returns true if there is a piece the piece at (x, y) and it can be selected.
		if (this.outOfBounds(x,y) == true){
			return false;
		} 
		//SELECT FIRST PIECE
		else if (this.pieceMoved == false) { //if havent moved
			if (this.pieceAt(x,y)!=null) { // + select piece
				if (this.turn == true){ // + if fire turn 
					if (pieceAt(x,y).isFire() == true){ // + fire piece
						return true;
					} else {
						return false;
					}
				}
				else { // if water turn
					if (pieceAt(x,y).isFire() == false){ //if select water piece
						return true;
					} else {
						return false;
					}
				}
			}
			//SELECT SPACE AFTER SOMETHING IS SELECTED OR CAPTURE (nothing has moved here)
			else if (this.pieceAt(x,y) == null && hasSelected == true){
				if (this.validMove(this.lastXSelected,this.lastYSelected,x,y) == true && this.hasSelected == true){ //calls --> validMove
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		//DOUBLE CAPTURING (this.pieceAt(lastXSelected,lastYSelected) != null)
	 	else if ((this.pieceAt(lastXSelected,lastYSelected) != null)){  //if there a piece has been moved --> only gets updated after CanSelect method and Endturn goes false
			if (this.pieceAt(lastXSelected,lastYSelected).hasCaptured() == true){ //check if a piece is a bomb (it would have been removed in move.)
					if (this.pieceAt(lastXSelected,lastYSelected).hasCaptured() == true){
						if (this.validMove(this.lastXSelected,lastYSelected,x,y) == true){ 
							return true;
						}		
						else {
							return false;
						}
					}	
					else {
						return false;
					}
				}
				else {
					return false;
				}
		}
		else {
			return false;
		}
	}

	
	//MAKE THIS INDEPENDENT OF CAN SELECT
	//DONT CHECK TURN, DONT CHECK ValidMove, DONT CHECK PIECE TYPE
	//CHECK SPACE OR NOT SPACE
	public void select(int x, int y){ 
		//this.selectedPicture = null;
		highlightX = x;
		highlightY = y;
		Piece p = pieceAt(x,y);
		if (p == null){ 
			if (this.hasSelected==true){
				if ((this.lastXSelected != x) && (this.lastYSelected != y)){
					this.pieceAt(this.lastXSelected,this.lastYSelected).move(x, y);
					this.lastXSelected = x;
					this.lastYSelected = y;
					this.pieceMoved = true;
				}
			}
		} else if (p!=null) {
			this.lastXSelected = x;
			this.lastYSelected = y;
			this.hasSelected = true;
		}
		
		//FOR GUI PUERPOSES ONLY
		if (p != null){
			if (p.isFire() == true){
				if (p.isKing() == true && p.isShield()){
					this.selectedPicture = "img/shield-fire-crowned.png";
				}
				else if (p.isKing() == true && p.isBomb()){
					this.selectedPicture = "img/bomb-fire-crowned.png";
				}
				else if (p.isShield() == true){
					this.selectedPicture = "img/shield-fire.png";
				}
				else if (p.isBomb() == true){
					this.selectedPicture = "img/bomb-fire.png";
				}
				else if (p.isKing() == true){
					this.selectedPicture = "img/pawn-fire-crowned.png";
				}
				else {
					this.selectedPicture = "img/pawn-fire.png";
				}
			}
			else {
				if (p.isKing() == true && p.isShield()){
					this.selectedPicture = "img/shield-water-crowned.png";
				}
				else if (p.isKing() == true && p.isBomb()){
					this.selectedPicture = "img/bomb-water-crowned.png";
				}
				else if (p.isShield() == true){
					this.selectedPicture = "img/shield-water.png";
				}
				else if (p.isBomb() == true){
					this.selectedPicture = "img/bomb-water.png";
				}
				else if (p.isKing() == true){
					this.selectedPicture = "img/pawn-water-crowned.png";
				}
				else {
					this.selectedPicture = "img/pawn-water.png";
				}
			}
		}
	}

	public void place(Piece p,int x,int y){
		if (p == null || this.outOfBounds(x,y) == true){
			return;
		}
		else { //recently moved Piece so that can call move
			if (standardPieces[x][y] != null){
				this.remove(x,y);
			}
			standardPieces[x][y] = p;
		}
		 if (this.pieceAt(x,y).isBomb()==true && this.pieceAt(x,y).hasCaptured()==true){
		 	this.hasSelected=false;
		 }
	}

	public Piece remove(int x ,int y){
		if (this.outOfBounds(x,y)==true){
			System.out.println("The position of x,y selected is not on the 8x8 board.");
			return null;
		}
		else if (this.pieceAt(x,y)==null){
			System.out.println("There is no piece at this position to remove!");
			return null;
		}
		else if (this.pieceAt(x,y)!=null){
			Piece p = this.pieceAt(x,y);
			Piece result = p;
			standardPieces[x][y] = null;
			return result;
		}
		else {
			return null;
		}
	}

	public boolean canEndTurn(){
		if (this.pieceMoved == true){		
			return true;
		}
		else{
			return false;
		}
	}

	public void endTurn(){	
		this.pieceMoved = false;
		if (this.pieceAt(lastXSelected,lastYSelected) != null){
			this.pieceAt(lastXSelected,lastYSelected).doneCapturing();
		}
		this.hasSelected = false;
		this.selectedPicture = null;
		if (this.turn == true){
			this.turn = false;
		}
		else if (this.turn == false){
			this.turn = true;
		}
	}

	public String winner(){
		String result = null;
		int fire = 0;
		int water = 0;
		int i = 0;
		int j = 0;
		while (i < standardPieces.length){
			while (j < standardPieces.length) {
				if (standardPieces[i][j] == null){

				}
				else if (standardPieces[i][j] != null){
					if (standardPieces[i][j].isFire()==true){
						fire += 1;
					}
					else {
						water += 1;
					}
				}
				j= j+1;
			}
			j=0;
			i = i+1;
		}
		if (fire > 0 && water == 0){
			result = "Fire";
		}
		else if (fire == 0 && water > 0){
			result = "Water";
		}
		else if (fire == 0 && water == 0){
			result = "No One";
		}
		return result;
	}

	private void drawBoard(){
		 //N x N grid --> in this case 8
		StdDrawPlus.setXscale(0, widthAndHeight); 
	    StdDrawPlus.setYscale(0, widthAndHeight);

		
		//IF b.shouldBeEmpty == True --> Execute 
		if (shouldBeEmpty){
			int indexHeight = 0; //initial row counter
			int indexWidth = 0; //initial column counter
			while (indexHeight < widthAndHeight){
				while (indexWidth < widthAndHeight){
					//set pen color
					if ((indexHeight+indexWidth) % 2 == 0){
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					}
					else {
						StdDrawPlus.setPenColor(StdDrawPlus.RED);
					}
					StdDrawPlus.filledSquare(indexWidth + .5, indexHeight + .5, .5);
					indexWidth += 1; //increment inner loop
				} //end inner loop
				indexWidth = 0; //reset counter for inner loop
				indexHeight += 1; //increment outer loop
			} //end outer loop
		}
		//IF b.shouldBeEmpty == False --> Execute 
		else {
			// block of code make 8 x 8 grid for checkers 
			StdDrawPlus.setXscale(0, widthAndHeight); 
	        StdDrawPlus.setYscale(0, widthAndHeight);
			int indexHeight = 0; //initial row counter
			int indexWidth = 0; //initial column counter
			while (indexHeight < widthAndHeight){
				while (indexWidth < widthAndHeight){
					//set pen color
					if ((indexHeight+indexWidth) % 2 == 0){
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					}
					else {
						StdDrawPlus.setPenColor(StdDrawPlus.RED);
					}
					StdDrawPlus.filledSquare(indexWidth + .5, indexHeight + .5, .5);
					if (standardPieces[indexWidth][indexHeight] == null){
						
					}
					else {
						if (standardPieces[indexWidth][indexHeight].isFire() == true){
							if (standardPieces[indexWidth][indexHeight].isKing() == true && standardPieces[indexWidth][indexHeight].isShield() == true){
								StdDrawPlus.picture(indexWidth + .5, indexHeight + .5, "img/shield-fire-crowned.png", 1, 1);
							}
							else if (standardPieces[indexWidth][indexHeight].isKing() == true && standardPieces[indexWidth][indexHeight].isBomb() == true){
								StdDrawPlus.picture(indexWidth + .5, indexHeight + .5, "img/bomb-fire-crowned.png", 1, 1);
							}
							else if (standardPieces[indexWidth][indexHeight].isShield() == true){
								StdDrawPlus.picture(indexWidth + .5, indexHeight + .5, "img/shield-fire.png", 1, 1);
							}
							else if (standardPieces[indexWidth][indexHeight].isBomb() == true){
								StdDrawPlus.picture(indexWidth + .5, indexHeight + .5, "img/bomb-fire.png", 1, 1);
							}
							else if (standardPieces[indexWidth][indexHeight].isKing() == true){
								StdDrawPlus.picture(indexWidth + .5, indexHeight + .5, "img/pawn-fire-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(indexWidth + .5, indexHeight + .5, "img/pawn-fire.png", 1, 1);
							}
						}
						else {
							if (standardPieces[indexWidth][indexHeight].isKing() == true && standardPieces[indexWidth][indexHeight].isShield() == true){
								StdDrawPlus.picture(indexWidth + .5, indexHeight + .5, "img/shield-water-crowned.png", 1, 1);
							}
							else if (standardPieces[indexWidth][indexHeight].isKing() == true && standardPieces[indexWidth][indexHeight].isBomb() == true){
								StdDrawPlus.picture(indexWidth + .5, indexHeight + .5, "img/bomb-water-crowned.png", 1, 1);
							}
							else if (standardPieces[indexWidth][indexHeight].isShield() == true){
								StdDrawPlus.picture(indexWidth + .5, indexHeight + .5, "img/shield-water.png", 1, 1);
							}
							else if (standardPieces[indexWidth][indexHeight].isBomb() == true){
								StdDrawPlus.picture(indexWidth + .5, indexHeight + .5, "img/bomb-water.png", 1, 1);
							}
							else if (standardPieces[indexWidth][indexHeight].isKing() == true){
								StdDrawPlus.picture(indexWidth + .5, indexHeight + .5, "img/pawn-water-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(indexWidth + .5, indexHeight + .5, "img/pawn-water.png", 1, 1);
							}
						}
					} 
					indexWidth += 1; //increment inner loop
				} //end inner loop
				indexWidth = 0; //reset counter for inner loop
				indexHeight += 1; //increment outer loop
			} //end outer loop
			//SELECTING LOOP!!!!  GUI ONLY
			if (this.hasSelected == true){
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
           		StdDrawPlus.filledSquare(this.highlightX + .5, this.highlightY + .5, .5);
           		if (this.selectedPicture != null){
           			StdDrawPlus.picture(this.highlightX + .5, this.highlightY + .5, this.selectedPicture, 1, 1);
           		}
			}
		}
	}

	private boolean validMove(int xi,int yi,int xf,int yf){ //NEED TO INCLUDE CAPTURE
		/*Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf) in a valid fashion
		compatible with the rules.*/
		Piece pMoving = pieceAt(xi,yi);
		if (this.outOfBounds(xf,yf) == true || this.outOfBounds(xi,yi) == true){
			return false;
		}		
		else if (pMoving.isFire() == true){

			if (((xf - xi) == 1) && ((yf - yi) == 1) && (this.pieceAt(xf,yf)==null) && pMoving.hasCaptured() == false){
				return true;
			}
			else if (((xf - xi) == 1) && ((yf - yi) == -1) && (this.pieceAt(xf,yf)==null) && (pMoving.isKing()==true) && pMoving.hasCaptured() == false){
				return true;
			}
			else if (((xf - xi) == -1) && ((yf - yi) == 1) && (this.pieceAt(xf,yf)==null) && pMoving.hasCaptured() == false){
				return true;
			}
			else if (((xf - xi) == -1) && ((yf - yi) == -1) && (this.pieceAt(xf,yf)==null) && pMoving.isKing()==true && pMoving.hasCaptured() == false){
				return true;
			} 

			else if (((xf - xi) == 2) && ((yf - yi) == 2) && (this.pieceAt(xf-1,yf-1) != null)){
				if (this.pieceAt(xf,yf)==null)	{
					if (this.pieceAt(xf-1,yf-1).isFire()==false){
						return true;					
					}
					else {
						return false;
					}
				} else {
					return false;
				}
			}
			else if (((xf - xi) == 2) && ((yf - yi) == -2) && (this.pieceAt(xf-1,yf+1) != null) && (pMoving.isKing()==true)){
				if (this.pieceAt(xf,yf)==null)	{
					if (this.pieceAt(xf-1,yf+1).isFire()==false){
						return true;
					}
					else {
						return false;
					}
				} else {
					return false;
				}
			}
			else if (((xf - xi) == -2) && ((yf - yi) == 2) && (this.pieceAt(xf+1,yf-1) != null)){
				if (this.pieceAt(xf,yf)==null)	{	
					if (this.pieceAt(xf+1,yf-1).isFire()==false){
						return true;	
					}
					else {
						return false;
					} 
				} else {
					return false;
				}	
			}
			else if (((xf - xi) == -2) && ((yf - yi) == -2) && (this.pieceAt(xf+1,yf+1) != null) && (pMoving.isKing()==true)){
				if (this.pieceAt(xf,yf)==null)	{	
					if (this.pieceAt(xf+1,yf+1).isFire()==false){
						return true;	
					}
					else {
						return false;
					} 
				} else {
					return false;
				}	
			} 
			//ENDS THE FIRE LOOP
			else {
				return false;
			}
		}
		//STARTS WATER LOOP
		else if (pMoving.isFire() == false){

			if (((xf - xi) == 1) && ((yf - yi) == 1) && (this.pieceAt(xf,yf)==null) && pMoving.isKing()==true && pMoving.hasCaptured() == false){
				return true;
			}
			else if (((xf - xi) == 1) && ((yf - yi) == -1) && (this.pieceAt(xf,yf)==null) && pMoving.hasCaptured() == false){
				return true;
			}
			else if (((xf - xi) == -1) && ((yf - yi) == 1) && (this.pieceAt(xf,yf)==null) && (pMoving.isKing()==true) && pMoving.hasCaptured() == false){
				return true;
			}
			else if (((xf - xi) == -1) && ((yf - yi) == -1) && (this.pieceAt(xf,yf)==null) && pMoving.hasCaptured() == false){
				return true;
			} 

			else if (((xf - xi) == 2) && ((yf - yi) == 2) && (this.pieceAt(xf-1,yf-1) != null) && (pMoving.isKing()==true) ){
				if (this.pieceAt(xf,yf)==null){	
					if (this.pieceAt(xf-1,yf-1).isFire()==true){
						return true;	
					}
					else {
						return false;
					}
				} else {
					return false;
				}	
			}
			else if (((xf - xi) == 2) && ((yf - yi) == -2) && (this.pieceAt(xf-1,yf+1) != null)){
				if (this.pieceAt(xf,yf)==null){	
					if (this.pieceAt(xf-1,yf+1).isFire()==true){
						return true;	
					}
					else {
						return false;
					}
				} else {
					return false;
				}	
			}
			else if (((xf - xi) == -2) && ((yf - yi) == 2) && (this.pieceAt(xf+1,yf-1) != null) && (pMoving.isKing()==true)){
				if (this.pieceAt(xf,yf)==null){	
					if (this.pieceAt(xf+1,yf-1).isFire()==true){
						return true;	
					}
					else {
						return false;
					}
				} else {
					return false;
				}	
			}
			else if (((xf - xi) == -2) && ((yf - yi) == -2) && (this.pieceAt(xf+1,yf+1) != null)){
				if (this.pieceAt(xf,yf)==null){	
					if (this.pieceAt(xf+1,yf+1).isFire()==true){
						return true;			
					}
					else {
						return false;
					}
				} else {
					return false;
				} 
			} else {
			return false;
			}
		} else {
			return false;
		}
	}

	private boolean outOfBounds(int x, int y){
		if (x > (widthAndHeight-1) || y > (widthAndHeight-1) || x < 0 || y < 0) { //in this case 8 is not off but should be probably **** ASK
			return true;
		}
		else {
			return false;
		}
	}



	public static void main(String[] args){
		Board b = new Board(false);
		b.drawBoard();
		while(true) {
            b.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x,(int) y)){
                	b.select((int) x,(int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()==true) {
        		if (b.canEndTurn()==true){
                	b.endTurn();
                }
    		}   
            StdDrawPlus.show(100);
        }

	}
}