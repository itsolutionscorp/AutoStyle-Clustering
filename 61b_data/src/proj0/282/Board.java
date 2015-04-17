/** 
 *  @author Viswanath Chatterjee
 */

public class Board {
	private int N = 7; 						//8x8 board for this game, start count from 0
	private Piece[][] pieceArray;			//board is a double array of Piece objects
	private boolean isFireTurn = true;		//if it is Fire's turn, starting with Fire
	private boolean hasSelected = false;	//if player has selected a piece this turn
	private boolean hasMoved = false;		//if a piece has been been moved
	private int currentX = -1;			 	//coordinates of current selection
	private int currentY = -1;
	
	
	public Board (boolean shouldBeEmpty){
		pieceArray = new Piece[N+1][N+1];
		
		for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (!shouldBeEmpty) {
                	if (i%2 == 0 && j == 0 ) pieceArray[i][j] = new Piece(true, this, i, j, "pawn");
                    else if (i%2 == 1 && j == 1 ) pieceArray[i][j] = new Piece(true, this, i, j, "shield");
                    else if (i%2 == 0 && j == 2 ) pieceArray[i][j] = new Piece(true, this, i, j, "bomb");
                    
                    else if (i%2 == 1 && j == N ) pieceArray[i][j] = new Piece(false, this, i, j, "pawn");
                    else if (i%2 == 0 && j == N-1 ) pieceArray[i][j] = new Piece(false, this, i, j, "shield");
                    else if (i%2 == 1 && j == N-2 ) pieceArray[i][j] = new Piece(false, this, i, j, "bomb");
                }
            }
        }
	}
	
	public Piece pieceAt (int x, int y){
		if (x < 0 || x > N || y < 0 || y > N ) return null; //bounds check
		else return pieceArray[x][y];
	}
	
	public boolean canSelect (int x, int y){
		//System.out.println("CanSelect (x, y): (" + x + ", " + y + ")");
		if (x < 0 || x > N || y < 0 || y > N) return false;	//must select on the board

		if (pieceAt(x,y) != null){							//occupied square
			if (pieceAt(x,y).isFire() == isFireTurn){		//piece must belong to player
				if (!hasSelected || !hasMoved) return true;
				else return false;
			}
			else return false;
		}
		
		else{												//empty square
			//System.out.println("CanSelect empty square: hasSelected: " + hasSelected);
			//System.out.println("hasMoved: " + hasMoved);
			if (hasSelected && !hasMoved){
				//System.out.println("hasSelected && !hasMoved, reached here");
				return validMove(currentX, currentY, x, y);
			}
			else if (hasMoved){
				//System.out.println("not (hasSelected && !hasMoved), ( " + currentX + "," + currentY + " )");
				//System.out.println(pieceAt(currentX,currentY) == null);
				if (pieceAt(currentX,currentY) != null && 
				pieceAt(currentX, currentY).hasCaptured()){			//check if potential double capture
					if (canCaptureSomewhere(currentX, currentY)){	//still check for no adjacent moves
						return validMove(currentX, currentY, x, y);
					}
				}
				//System.out.println("can't return true for canSelect");
				return false;										//return false if capturer can't double capture
			}
			return false;								//can't select empty squares unless moving
		}
		
	}
	
	public void select (int x, int y){
		if (pieceArray[x][y] != null){
			////System.out.println("( " + x + " , " + y + ")");
			currentX = x;
			currentY = y;		
			hasSelected = true;
			//System.out.println("selected!");
		}
		else{
			pieceAt(currentX, currentY).move(x,y);
			//System.out.println("move and reselected ( " + x + " , " + y + ")");
			currentX = x;
			currentY = y;
			hasMoved = true;					//confirm that piece has moved
			//System.out.println("hasMoved:" + hasMoved);
		}
	}

	public void place (Piece p, int x, int y){
		if ( !(x < 0 || x > N || y < 0 || y > N) && p != null ){
			if (pieceAt(x,y) != null) remove(x,y);
			pieceArray[x][y] = p;
		}
	}
	
	public Piece remove (int x, int y){
		//System.out.println("Removal coordinates: " + x + ", " + y);
		if (x < 0 || x > N || y < 0 || y > N){
			System.out.println("Off the board! (Out of Bounds)");
			return null;
		}
		else if (pieceAt(x,y) == null) {
			System.out.println("There isn't a piece here! (Null)");
			return null;
		}	
		else{
			Piece temp = pieceArray[x][y];
			pieceArray[x][y] = null;
			
			return temp;
		}
	}
	
	public boolean canEndTurn(){
		if (pieceAt(currentX, currentY) != null){
			//System.out.println("pieceAt(currentX, currentY) != null");
			if (hasMoved || pieceAt(currentX, currentY).hasCaptured()) return true;			//otherwise, can only move once
		}
		//System.out.println("canEndTurn returns false");
		if (hasMoved) return true;															//a bomb must have detonated
		return false;
	}
	
	
	public void endTurn(){
		if (pieceAt(currentX, currentY) != null) pieceAt(currentX, currentY).doneCapturing();
		currentX = -1;
		currentY = -1;
		hasMoved = false;
		hasSelected = false;
		isFireTurn = !isFireTurn;
	}
	
	public String winner(){
		int numFire = 0;
		int numWater = 0;
		
		for (Piece [] u : pieceArray)
			for (Piece element : u){
				if (element != null){
					if (element.isFire()) numFire ++;
					else numWater ++;
				}
			}
		
		//System.out.println("Fire / Water: " + numFire + numWater);
		if (numFire == 0 && numWater == 0) return "No one";
		else if (numFire == 0) return "Water";
		else if (numWater == 0) return "Fire";
		else return null;
	}
	
	private boolean canCaptureSomewhere(int x, int y){			//if a capture is possible somewhere with this piece
		//System.out.println("pieceAt(currentX,currentY) != null && pieceAt(currentX, currentY).hasCaptured()");
		for (int i = -2; i <= 2; i=i+4){
			for (int j = -2; j <= 2; j=j+4){
				if (!(x+i < 0 || x+i > N		
				|| y+j < 0 || y+j > N)){		//don't give validMove impossible final coordinates
					if (validMove(x, y, x+i, y+j)) return true;	
				}
			}
		}
		return false;
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf){
		//System.out.println("validMove reached");
		if ( yf - yi == 1 && (pieceArray[xi][yi].isFire() || pieceArray[xi][yi].isKing()) 			//upward unit move by fire and/or king
		&& (xf - xi == 1 || xf - xi == -1) && !pieceAt(xi,yi).hasCaptured()) {						//adjacent move without having captured
			return true;		
		}
		
		else if ( yf - yi == -1 && (!pieceArray[xi][yi].isFire() || pieceArray[xi][yi].isKing()) 	//downward unit move by water and/or king
		&& (xf - xi == 1 || xf - xi == -1) && !pieceAt(xi,yi).hasCaptured()) {						//adjacent move without having captured
			return true;		
		}
		
		else if (yf - yi == 2){												//attempted upwards capture		
			//System.out.println("Xi: " + xi + ", Yi: " + yi);
			//System.out.println("attempting capture");
			if(pieceArray[xi][yi].isFire() || pieceArray[xi][yi].isKing()){	//fire and/or king
				if (xf - xi == 2){											//attempt to the right
					if (pieceAt(xf-1,yf-1) != null && (pieceAt(xi,yi).isFire() != 
					pieceAt(xf-1,yf-1).isFire())){ 							//enemy in between (right up)
						return true;
					}
					else return false;
				}
				else if (xf - xi == -2){									//attempt to the left
					if (pieceAt(xf+1,yf-1) != null && (pieceAt(xi,yi).isFire() != 
					pieceAt(xf+1,yf-1).isFire())){ 							//enemy in between (left up)
						return true;
					}
					else return false;
				}
				else return false;											//x must be two away
			}
			else return false;
		}
		else if (yf - yi == -2){											//attempted downwards capture
			if(!pieceArray[xi][yi].isFire() || pieceArray[xi][yi].isKing()){//water and/or king
				if (xf - xi == 2){											//attempt to the right
					if (pieceAt(xf-1,yf+1) != null && (pieceAt(xi,yi).isFire() != 
					pieceAt(xf-1,yf+1).isFire())){ 							//enemy in between (right down)
						return true;
					}
					else return false;
				}
				else if (xf - xi == -2){									//attempt to the left
					if (pieceAt(xf+1,yf+1) != null && (pieceAt(xi,yi).isFire() != 
					pieceAt(xf+1,yf+1).isFire())){ 							//enemy in between (left down)
						return true;
					}
					else return false;
				}
				else return false;											//x must be two away
			}
			else return false;
		}
		else return false;													//y more than two away or unchanged
	}
	
	private void drawBoard (){													//color the grid
		StdDrawPlus.setXscale(0, N+1);											//set scale													
        StdDrawPlus.setYscale(0, N+1);
         
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
		}
        updateGUIBoardPieces();
        
	}
	
	private void updateGUIBoardPieces (){
		for (int i = 0; i <= N; i++) {											
            for (int j = 0; j <= N; j++) {
            	if (pieceAt(i, j) != null){											//use image if piece
            		StdDrawPlus.picture(i + .5, j + .5, getImgName(pieceAt(i, j)), 1, 1);
            	}
            }
		}
	}
	
	private String getImgName (Piece p){											//image file name
		String imgType, imgSide, imgKing, imgName;														
		
		if (p.isBomb()) imgType = "bomb";
		else if (p.isShield()) imgType = "shield";
		else imgType = "pawn";

		if (p.isFire()) imgSide = "-fire";
		else imgSide = "-water";
		
		if (p.isKing()) imgKing = "-crowned";
		else imgKing = "";
		
		imgName= "img/" + imgType + imgSide + imgKing + ".png";
		return imgName;
	}
	
	public static void main(String[] args) {
		
		Board b = new Board (false);
																					//draw the board
		
		while(true){
			b.drawBoard();
			
			if (StdDrawPlus.mousePressed()){										//check for click				
				int x = (int) StdDrawPlus.mouseX();									//update current coordinates
				int y = (int) StdDrawPlus.mouseY();
				if (b.canSelect(x, y)){										//check if valid selection
					//System.out.println("canSelect is true");
					b.select(x, y);											//select square
				}
			}
			
			if (b.hasSelected){
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);										//highlight clicked square
				StdDrawPlus.filledSquare(b.currentX + .5, b.currentY + .5, .5);
				b.updateGUIBoardPieces ();														//add icon back
			}
			
			if (StdDrawPlus.isSpacePressed()){													//check for space bar
				//System.out.println("Checking if canEndTurn");
				if (b.canEndTurn()) b.endTurn();												//end turn if possible
				
				if (!(b.winner() == null)){															//check for winner
					System.out.println(b.winner());
					break;
				}
			}
			
			StdDrawPlus.show(20);
		}
	}
}
