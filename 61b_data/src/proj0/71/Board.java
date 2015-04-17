public class Board {

	// Creates all the necessary variables for the Board class to function.
	// Creates an 8x8 2D array.
    private Piece [][] pieces = new Piece [8][8];
    private boolean fireTurn = true;
    private Piece selectedPiece = null;
    private boolean hasMoved = false; 
    private int storedX = -1;
    private int storedY = -1;
 


// If the board should be empty, it does nothing.
// Otherwise the constructor fills in the 2D array with the correct pieces at the correct locations.
    public Board(boolean shouldBeEmpty) {
    	int N = 8;
    	if (shouldBeEmpty == true){
			return;
	    	}
    	else{
    			int x = 0;
				int y = 0;
				while (y<8) {	
					if (x == 8){
						x = 0;
						y += 1;
					}
					if (y <= 8) {
						if ((y == 0) && (x % 2 == 0)){
							pieces[x][y] = new Piece(true, this,  x,y, "pawn");
							x += 1;
						}
						else if ((y==1) && (x % 2 != 0)){
							pieces[(int) x][(int) y] = new Piece(true, this,  x,y, "shield");
							x+=1;
						}
						else if ((y==2) && (x % 2 == 0)){
							pieces[(int) x][(int) y] = new Piece(true, this,  x,y, "bomb");
							x+=1;
						}
						else if ((y==7) && (x % 2 != 0)){
							pieces[(int) x][(int) y] = new Piece(false, this,  x,y, "pawn");
							x+=1;
						}
						else if ((y==6) && (x % 2 == 0)){
							pieces[(int) x][(int) y] = new Piece(false, this,  x,y, "shield");
							x+=1;
						}
						else if ((y==5) && (x % 2 != 0)){
							pieces[(int) x][(int) y] = new Piece(false, this,  x,y, "bomb");
							x+=1;
						}
						else if (y < 8 && x <8){
						pieces[(int) x][(int) y] = null;
						x +=1;
						}
						}
					}
		    	}}



// If the parameter passed in is true, drawBoard when called will draw an empty board.
// Else, if false is passed in, drawBoard will iterate through the array and add images of the pieces at the correct locations

private void drawBoard(boolean empty){
    	int N = 8;
    	StdDrawPlus.setXscale(0, N);
    	StdDrawPlus.setYscale(0, N);
    	if (empty == true){
    		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else   StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
    	}}
    	}
    	else{
    		for (int i=0; i < N; i++) { 
    			for (int j=0; j < N; j++){
    				if ((i+j) % 2 == 0){
    				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
    				}
    				else{
    					StdDrawPlus.setPenColor(StdDrawPlus.RED);
    				}
    				if ( i == storedX && j == storedY) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    			   StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                   	if (pieces[i][j] != null){
                   	 if (!pieces[i][j].isBomb() && !pieces[i][j].isShield() && pieces[i][j].isFire() && pieces[i][j].isKing()){
                   		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);

                   }
                   		else if (pieces[i][j].isShield() && pieces[i][j].isFire()&& pieces[i][j].isKing()){
                   		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);

                   }
                   		else if (pieces[i][j].isBomb() && pieces[i][j].isFire()&& pieces[i][j].isKing()){
                   		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                   }
                        else if (!pieces[i][j].isBomb() && !pieces[i][j].isShield() && !pieces[i][j].isFire()&& pieces[i][j].isKing()){
                   		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);

                   	}
                   		 else if (pieces[i][j].isShield() && !pieces[i][j].isFire()&& pieces[i][j].isKing()){
                   		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                   	}
                   	    else if (pieces[i][j].isBomb() && !pieces[i][j].isFire()&& pieces[i][j].isKing()){
                   		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);

                   	}
                   		else if (!pieces[i][j].isBomb() && !pieces[i][j].isShield() && pieces[i][j].isFire()){
                   		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);

                   }
                   		else if (pieces[i][j].isShield() && pieces[i][j].isFire()){
                   		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);

                   }
                   		else if (pieces[i][j].isBomb() && pieces[i][j].isFire()){
                   		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                   }
                        else if (!pieces[i][j].isBomb() && !pieces[i][j].isShield() && !pieces[i][j].isFire()){
                   		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);

                   	}
                   		 else if (pieces[i][j].isShield() && !pieces[i][j].isFire()){
                   		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                   	}
                   	    else if (pieces[i][j].isBomb() && !pieces[i][j].isFire()){
                   		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);

                   	}
                   	    else if (!pieces[i][j].isBomb() && !pieces[i][j].isShield() && pieces[i][j].isFire() && pieces[i][j].isKing()){
                   		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);

                   }
                   		else if (pieces[i][j].isShield() && pieces[i][j].isFire()&& pieces[i][j].isKing()){
                   		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);

                   }
                   		else if (pieces[i][j].isBomb() && pieces[i][j].isFire()&& pieces[i][j].isKing()){
                   		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                   }
                        else if (!pieces[i][j].isBomb() && !pieces[i][j].isShield() && !pieces[i][j].isFire()&& pieces[i][j].isKing()){
                   		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);

                   	}
                   		 else if (pieces[i][j].isShield() && !pieces[i][j].isFire()&& pieces[i][j].isKing()){
                   		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                   	}
                   	    else if (pieces[i][j].isBomb() && !pieces[i][j].isFire()&& pieces[i][j].isKing()){
                   		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);

                   	} 
                   }
     
    			}
    		}}
    	}

//Returns the piece at location x,y in the 2D array.
// If a piece does not exist it returns null, avoids null pointer exceptions

public Piece pieceAt(int x, int y){
	if ( x > 7 || y > 7 || x < 0 || y < 0){
		return null;
	}
	else if (pieces[x][y] == null){
		return null;
	}

	else{
		return (pieces[x][y]);
	}
}

// Place moves the instance of a Piece from wherever it was before to a new x,y location in the 2D array

public void place(Piece p, int x, int y){
	if (p == null || x > 7 || y > 7){
		return;
	}
	else{
	pieces[x][y] = p;
}
	}

// Removes a piece based on its location in the 2D array and returns the piece which was removed

public Piece remove(int x, int y){

		Piece tempP = pieceAt(x,y);
		pieces[x][y] = null;
		return tempP;
	}
// Select is only called after canSelect, to see if its a valid object to select
// Select checks to see if the variable selectedPiece is null, if it is that means the item being selected from the array is a piece
// and stores the x y location of the piece and the piece itself
// If a piece is already stored, select stores the new coordinates, changes hasMoved to true, and calls move on the selected piece
public void select(int x, int y){
	if (selectedPiece != null && pieceAt(x,y) == null){
		storedX = x;
		storedY = y;
		hasMoved = true;
		selectedPiece.move(x,y);
			}
	else{
		storedY = y;
		storedX = x;
		selectedPiece = pieces[x][y];
	}

}
 // canSelect checks to see if you have already selected a piece, if so you can only select an empty square if that is a valid move or valid capture
// if no piece was selected it allows you to select the piece so long as it is the correct players turn

public boolean canSelect(int x, int y){
	if (selectedPiece != null && selectedPiece.hasCaptured() && validCapture(storedX, storedY, x, y)){
		return true;
	}
	else if(pieceAt(x,y) != null && fireTurn&& pieceAt(x,y).isFire() && !hasMoved){
		return true;
	}
	else if (pieceAt(x,y) != null && !fireTurn &&  !pieceAt(x,y).isFire() && !hasMoved){
		return true;
	}
	else if(selectedPiece != null && !selectedPiece.hasCaptured() && !hasMoved && pieceAt(x,y) == null && (validMove(storedX, storedY, x, y) || validCapture(storedX, storedY, x, y))){
		return true;
	}
	else {
		return false;
	}
}

// valid move checks the type of piece that is selected, and if the target location is a valid move for that particular piece

private boolean validMove(int xi, int yi, int xf, int yf){
		if (pieceAt(xi,yi) != null && pieceAt(xi,yi).isFire() && yi + 1 == yf &&  (xi + 1 == xf ||  xi - 1 == xf) && pieceAt(xf,yf)  == null){
			return true;
		}
		else if (pieceAt(xi,yi) != null && pieceAt(xi,yi).isFire()  && yi + 2 == yf && ((xi + 2== xf  && pieceAt(xi + 1,yi + 1) != null && pieceAt(xi,yi).isFire() != pieceAt(xi + 1,yi + 1).isFire() )|| (xi - 2 == xf  
			&& pieceAt(xi -1,yi+ 1) != null && pieceAt(xi,yi).isFire() != pieceAt(xi - 1,yi + 1).isFire()) && pieceAt(xf,yf)  == null)){
			return true;
		}
		else if (pieceAt(xi,yi)  != null && !pieceAt(xi,yi).isFire() && yi - 1 == yf &&  (xi + 1 == xf ||  xi - 1 == xf) && pieceAt(xf,yf)  == null){
			return true;
		}
		else if (pieceAt(xi,yi) != null && !pieceAt(xi,yi).isFire() && yi - 2 == yf && ((xi + 2== xf && pieceAt(xi + 1,yi - 1) != null && pieceAt(xi,yi).isFire() != pieceAt(xi + 1,yi - 1).isFire() ) || (xi -2 == xf &&
			pieceAt(xi -1,yi - 1) != null && pieceAt(xi,yi).isFire() != pieceAt(xi - 1,yi - 1).isFire())) && pieceAt(xf,yf)  == null) {
			return true;
		} 
		else if (pieceAt(xi,yi) != null && pieceAt(xi,yi).isKing() && ((xi + 2 == xf && yi + 2 == yf && pieceAt(xi+1,yi+1) !=null) || (xi -2 == xf && yi + 2 == yf && 
			pieceAt(xi-1,yi+1)!= null) || ( xi -2 == xf && yi -2 == yf && pieceAt(xi-1,yi-1) != null) || ( xi + 2 == xf && yi - 2 == yf && pieceAt(xi+1,yi-1) != null))) {
			return true;
		}
		else if (pieceAt(xi,yi)  != null && pieceAt(xi,yi).isKing() && (xi + 1 == xf || xi -1 == xf) && (yi + 1 == yf || yi -1 == yf)){
			return true;
}
		else{
			return false;
	}
}


// validCapture checks to see if the type of piece can capture another piece, so long as it is a different piece and the coordinate you are selecting is valid 
// for the piece type
private boolean validCapture(int xi, int yi, int xf, int yf){
		if (pieceAt(xi,yi) != null && pieceAt(xi,yi).isFire()  && yi + 2 == yf && ((xi + 2== xf  && pieceAt(xi + 1,yi + 1) != null && pieceAt(xi,yi).isFire() != pieceAt(xi + 1,yi + 1).isFire() )|| (xi - 2 == xf  
			&& pieceAt(xi -1,yi+ 1) != null && pieceAt(xi,yi).isFire() != pieceAt(xi - 1,yi + 1).isFire()) && pieceAt(xf,yf)  == null)){
				return true;
		}
		else if (pieceAt(xi,yi) != null && !pieceAt(xi,yi).isFire() && yi - 2 == yf && ((xi + 2== xf && pieceAt(xi + 1,yi - 1) != null && pieceAt(xi,yi).isFire() != pieceAt(xi + 1,yi - 1).isFire() ) || (xi -2 == xf &&
			pieceAt(xi -1,yi - 1) != null && pieceAt(xi,yi).isFire() != pieceAt(xi - 1,yi - 1).isFire())) && pieceAt(xf,yf)  == null) {
			return true;
		} 
		else if (pieceAt(xi,yi)  != null && pieceAt(xi,yi).isKing() && ((xi + 2 == xf && yi + 2 == yf && pieceAt(xi+1,yi+1) !=null) || (xi -2 == xf && yi + 2 == yf && 
			pieceAt(xi-1,yi+1)!= null) || ( xi -2 == xf && yi -2 == yf && pieces[xi-1][yi-1] != null) || ( xi + 2 == xf && yi - 2 == yf && pieceAt(xi+1,yi-1) != null))) {
			return true;
		}
		else{
			return false;
		}
}


// checks to see if you hasMoved == true, meaning you have made a move
public boolean canEndTurn(){
	return hasMoved;
}

// endTurn resets all the variables to the default for the next players turn
public void endTurn(){
	fireTurn = !fireTurn;
	selectedPiece.doneCapturing();
    selectedPiece = null;
    hasMoved = false; 
    storedX = -1;
    storedY = -1;

	}


// winner iterates through the 2D array and adds to the waterCounter and fireCounter depending on whether the piece is of what type
public String winner(){
	int waterCounter = 0;
	int fireCounter = 0;
	for (int i =0; i < 8; i+=1){
		for (int j=0 ; j< 8 ; j+=1){
			if (pieceAt(i,j) != null){
			if (pieceAt(i,j).isFire()){
				fireCounter +=1;
			}
			else{
				waterCounter +=1;
			}
		}
	}
}
	if (fireCounter == 0){
		return "Water";
	}
	else if (waterCounter == 0){
		return "Fire";
	}
	else if (fireCounter == 0 && waterCounter == 0){
		return "No one";
		}
	return null;
	}


// main method creates a new board, and in the while loop so long as both players have pieces on the board, it runs
// it draws a new board every iteration, and if a mouse is pressed stores the x y coordinates and checks if the object
// at the xy coordinate can be selected and if it can it is selected
// also checks if spacebar is pressed and if the player canEndTurn, if so ends the turn
public static void main(String args[]){
	Board b = new Board(false);
	while (b.winner() == null){
		b.drawBoard(false);
		if (StdDrawPlus.mousePressed()){
			double x = StdDrawPlus.mouseX();
			double y = StdDrawPlus.mouseY();
			if (b.canSelect((int) x, (int) y)){
				b.select((int) x, (int) y);
			}
		}
		StdDrawPlus.show(10);
		if (StdDrawPlus.isSpacePressed() && b.canEndTurn()){
			b.endTurn();
		}
	}

}

}