
public class Board {
	 private Piece[][] pieces ;
	 private boolean[][] colorWhite;
	 private boolean fireTurn;
	 private boolean selected;
	 private boolean hasMoved;
	 private boolean hasCaptured;
	 private int selectedX;
	 private int selectedY;
	 
	 private static void drawBoard(int N, Board aBoard) {
		 for (int i = 0; i < N; i++) {
			 for (int j = 0; j < N; j++) {
				 if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	             else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
	             StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	             StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	             /*
	             if(aBoard.selected == true){
	            	 StdDrawPlus.filledSquare(aBoard.selectedX + .5, aBoard.selectedY + .5, .5);
		             StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	             }
	             */
	             if(aBoard.colorWhite[j][i] == true){
	            	 StdDrawPlus.filledSquare(i + .5, j + .5, .5);
		             StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	             }
	             if( aBoard.pieces[j][i] != null){
	            	if(aBoard.pieces[j][i].isKing() == false){
	            		 if (aBoard.pieces[j][i].isFire()) {
	            			 if(aBoard.pieces[j][i].isBomb()){
	            				 StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);}
	            			 else if(aBoard.pieces[j][i].isShield()){
	            				 StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);}
	            			 else{
	            				 StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);}
	            		 }
	            		 else{
	            			 if(aBoard.pieces[j][i].isBomb()){
	            				 StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);}
	            			 else if(aBoard.pieces[j][i].isShield()){
	            				 StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);}
	            			 else{
	            				 StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);}
	            		 }
	            	 }
	            	else{
	            		if (aBoard.pieces[j][i].isFire()) {
	            			 if(aBoard.pieces[j][i].isBomb()){
	            				 StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);}
	            			 else if(aBoard.pieces[j][i].isShield()){
	            				 StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);}
	            			 else{
	            				 StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);}
	            		 }
	            		 else{
	            			 if(aBoard.pieces[j][i].isBomb()){
	            				 StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);}
	            			 else if(aBoard.pieces[j][i].isShield()){
	            				 StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);}
	            			 else{
	            				 StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);}
	            		 }
	            	}
	             }
	             
	          }
	      }
	}
	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		Board aBoard = new Board(false);
		
		
		while(aBoard.winner() == null){
			drawBoard(N, aBoard);
			if(StdDrawPlus.isSpacePressed() == false){
				if(StdDrawPlus.mousePressed() == true) {
					int x = (int)StdDrawPlus.mouseX();
					int y = (int)StdDrawPlus.mouseY();
					if(aBoard.canSelect(x, y)){
						aBoard.select(x, y);
					}
					StdDrawPlus.show(10); 
				}
			}
			else {
				if(aBoard.canEndTurn()){
					aBoard.endTurn();
				}
			}
			StdDrawPlus.show(10);
		}
	}
	
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		fireTurn = true;
		selected = false;
		hasCaptured = false;
		hasMoved = false;
		selectedX = -1;
		selectedY = -1;
		colorWhite = new boolean[8][8];
		
		if(shouldBeEmpty == true){
	            drawBoard(8,this);
	            for(int i = 0; i < 8 ; i++){
					for(int j = 0; j < 8; j++){
						pieces[i][j] = null;
					}
				}
		}
		else{
			for(int i = 0; i < 8 ; i++){
				for(int j = 0; j < 8; j++){
					if((j == 0) && (i % 2 == 0)){
						pieces[j][i] = new Piece(true, this, i, j, "pawn");}
					else if((j == 1 ) && (i % 2 == 1)){
						pieces[j][i] = new Piece(true, this, i, j, "shield");}
					else if((j == 2 ) && (i % 2 == 0)){
						pieces[j][i] = new Piece(true, this, i, j, "bomb");}
					else if((j == 5 ) && (i % 2 == 1)){
						pieces[j][i] = new Piece(false, this, i, j, "bomb");}
					else if((j == 6 ) && (i % 2 == 0)){
						pieces[j][i] = new Piece(false, this, i, j, "shield");}
					else if((j == 7 ) && (i % 2 == 1)){
						pieces[j][i] = new Piece(false, this, i, j, "pawn");}
				}
			}
			drawBoard(8,this);
		}
	}
	
	// Gets the piece at position (x, y) on the board, or null if there is no piece.
	// If (x, y) are out of bounds, returns null.
	public Piece pieceAt(int x, int y){
		if( notOutOfBound(x, y) == false){
			return null;
		}
		else if(pieces[y][x] != null){
			return pieces[y][x];
		}
		else{
			return null;
		}
	}
	
	
	// check for out of bound, 
	// return false when it is out of bound
	// return true when it is not
	private boolean notOutOfBound(int x, int y){
		if(x > 7 || y > 7 || x < 0 || y < 0)
			return false;
		return true;
	}
	
	
	
	// Returns true if there is a piece the piece at (x, y) and it can be selected.
	public boolean canSelect(int x, int y){
		if(notOutOfBound(x, y) == false){ // out of bound
			return false;
		}
		if((pieces[y][x] != null) &&(fireTurn == pieces[y][x].isFire())  ){
			if( selected == false){
				return true;
			}
			else{
				if(hasMoved == false){
					return true;
				}
			}
		}
		else if(pieces[y][x] == null){
			if(selected == true && hasMoved == false){
				return validMove(selectedX, selectedY, x, y);
			}
			else if(selected == true && hasMoved == true && hasCaptured == true){
				return validCapturedMove(selectedX, selectedY, x, y);
			}
		}
		
		return false;
	}
	
	
	// 
	private boolean validMove(int xi, int yi, int xf, int yf){
		int centerX = (xf + xi) / 2;
		int centerY = (yf + yi) / 2;
		if(pieces[yf][xf] == null){
			if(pieces[yi][xi].isKing()){
				if(xf == xi + 1 && yf == yi +1){ //  up right
					return true;
				}
				else if(xf == xi + 1 && yf == yi - 1) { // down right
					return true;
				}
				else if(xf == xi - 1 && yf == yi - 1) { // down left
					return true;
				}
				else if(xf == xi - 1 && yf == yi + 1){ // up left
					return true;
				}
				else if(Math.abs(xf - xi) == 2 && Math.abs(yf -yi) == 2){
					if(pieces[centerY][centerX] != null &&
							pieces[centerY][centerX].isFire() != pieces[yi][xi].isFire()){
						return true;
					}
				}
			}
			else{
				if(fireTurn){ // fire
					if((xf == xi + 1 || xf == xi -1) && (yf == yi + 1)){
						return true;
					}
					else if((xf  == xi + 2 || xf  == xi - 2) && yf  == yi + 2){
						if(pieces[centerY][centerX] != null && 
								pieces[centerY][centerX].isFire() == false){
							return true;
						}
					}
				}
				else{ // water
					if((xf == xi + 1 || xf == xi -1) && (yf == yi - 1)){
						return true;
					}
					else if((xf  == xi + 2 || xf  == xi - 2) && yf  == yi - 2){
						if(pieces[centerY][centerX] != null && 
								pieces[centerY][centerX].isFire() == true){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	
	
	
	private boolean validCapturedMove(int xi, int yi, int xf, int yf){
		int centerX = (xf + xi) / 2;
		int centerY = (yf + yi) / 2;
		if(pieces[yf][xf] == null){
			if(pieces[yi][xi].isKing()){
				if(Math.abs(xf - xi) == 2 && Math.abs(yf -yi) == 2){
					if(pieces[centerY][centerX] != null &&
							pieces[centerY][centerX].isFire() != pieces[yi][xi].isFire()){
						return true;
					}
				}
			}
			else{ // not king
				if(fireTurn){ // fire turn
					if((xf  == xi + 2 || xf  == xi - 2) && yf  == yi + 2){
						if(pieces[centerY][centerX] != null && 
								pieces[centerY][centerX].isFire() == false){
							return true;
						}
					}
				}
				else{ // water turn
					if((xf  == xi + 2 || xf  == xi - 2) && yf  == yi - 2){
						if(pieces[centerY][centerX] != null && 
								pieces[centerY][centerX].isFire() == true){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	
	
	
	// Selects the piece at (x, y) if possible.
	public void select(int x, int y){
		if((x != selectedX) || (y != selectedY)){
			if(pieces[y][x] != null){
				colorWhite[y][x] = true;
				selected = true;
				if(selectedX >= 0 && selectedY >= 0){
					colorWhite[selectedY][selectedX] = false;
				}
			}
			else{
				colorWhite[y][x] = true;
				colorWhite[selectedY][selectedX] = false;
				pieces[selectedY][selectedX].move(x, y);
				if(pieces[y][x] != null && pieces[y][x].hasCaptured()){
					hasCaptured = true;
				}
				hasMoved = true;
			}
			selectedX = x;
			selectedY = y;
		}
	}
	
	// Places p at (x, y).
	public void place(Piece p, int x, int y){
		if(notOutOfBound(x, y) && p != null){
			pieces[y][x] = p;
		}
	}
	
	// Executes a remove
	public Piece remove(int x, int y){
		if(notOutOfBound(x, y) == false){
			System.out.println("The indices are out of bound");
			return null;
		}
		else if(pieces[y][x] == null){
			System.out.println("No piece is at this location");
			return null;
		}
		else{
			Piece temp = pieces[y][x];
			pieces[y][x] = null;
			return temp;
		}
		
	}
	
	// Returns whether or not the the current player can end their turn.
	public boolean canEndTurn(){
		if(hasMoved ){
			return true;
		}
		else if(selectedX >= 0 && selectedY >= 0){
			 if(pieces[selectedY][selectedX] != null && ( pieces[selectedY][selectedX].hasCaptured())){
				 colorWhite[selectedY][selectedX] = false;
				 return true;
			 }
		}
		return false;
//		if(hasMoved == false)
//			return false;
//		return true;
	}
	
	// Called at the end of each turn.
	public void endTurn() {
		if(fireTurn){
			fireTurn = false;
		}
		else{
			fireTurn =true;
		}
		if(selectedX >= 0 && selectedY >= 0 && pieces[selectedY][selectedX] != null){
			pieces[selectedY][selectedX].doneCapturing();
		}
		colorWhite[selectedY][selectedX] = false;
		hasCaptured =false;
		hasMoved = false;
		selected = false;
		selectedX = -1;
		selectedY = -1;
	}
	
	//  Returns the winner of the game
	public String winner(){
		int numFire = 0;
		int numWater = 0;
		for(int i = 0; i < 8; i++){
			for ( int j = 0; j < 8; j++){
				if(pieces[j][i] != null ){
					if(pieces[j][i].isFire()){
						numFire++;}
					else{
						numWater++;}
				}
			}
		}
		if(numFire == 0 && numWater > 0){
			return "Water";}
		else if(numFire > 0 && numWater == 0){
			return "Fire";}
		else if( numFire == 0 && numWater == 0){
			return "No one";}
		else {
			return null;}
	}
	



}
