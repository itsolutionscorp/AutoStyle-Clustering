public class Board{
	private Piece pieces[][];
	private boolean moved;
	private Piece selected;
	private boolean fireturn;
	private boolean hasCaptured;
	private int xPos;
	private int yPos;
	//Hopefully final project
	
	public Board(boolean shouldBeEmpty){
		pieces = new Piece[8][8];
		fireturn = true;
        moved = false;
        selected = null;
		hasCaptured = false;
		xPos = 0;
		yPos = 0;
		if(shouldBeEmpty) return;
		else{
			for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	                if( (i + j) % 2 == 0){	          
	                	if( j < 3){
	                		if(j == 0) pieces[i][j] = new Piece(true, this, i, j, "pawn");
	                		if(j == 1) pieces[i][j] = new Piece(true, this, i, j, "shield");
	                		if(j == 2) pieces[i][j] = new Piece(true, this, i, j, "bomb");	                	} 
	                	if( j > 4){
	                		if(j == 7) pieces[i][j] = new Piece(false, this, i, j, "pawn");
	                		if(j == 6) pieces[i][j] = new Piece(false, this, i, j, "shield");
	                		if(j == 5) pieces[i][j] = new Piece(false, this, i, j, "bomb");
	                	}
	                }
	                else pieces[i][j] = null; 
	            }
        	}
		}
	}

	public boolean canSelect(int x, int y){
		if(x < 0 || x > 7 || y < 0 || y > 7){
			return false;
		}
		else if(selected == null){ //if no piece selected, checks to see if player is choosing right team
			if( pieces[x][y] != null ){
				if( fireturn == pieces[x][y].isFire() ) return true;
				else return false;
			}
			else return false;
		}
		else {
			if( pieces[x][y] == null){ //checks for null space if already selected piece
				if(moved == false){
					if( canCapture(x,y) ) return true;
					else if( selected.isKing() && ((Math.abs(x - xPos) ) < 2) && ((Math.abs(y - yPos) ) < 2) ){ //for kinged pieces
						if(xPos != x && yPos != y) return true;
						else return false;
					}
					else if( ((Math.abs(x - xPos) ) < 2) && xPos != x) { //checks for correct arrangment
						if( selected.isFire() && (y == (yPos + 1) )  ) return true; //checks for for correct direction for team
						else if ( !(selected.isFire()) && (y == yPos - 1) ) return true;
						else return false;
					}
					else return false;
				}

				else if( canCapture(x,y) && hasCaptured ) {
					return true;
				}

				else return false;
			}
			else if(pieces[x][y].isFire() == selected.isFire() && moved == false) return true; //changes team player
			else return false;
		}
	}


	public Piece pieceAt(int x, int y){
		if( x > 7 || x < 0 || y > 7 || y < 0) return null;
		else if( pieces[x][y] == null) return null;
		else return pieces[x][y];
	}


	public void place(Piece p, int x, int y){
		if( x > 7 || x < 0 || y > 7 || y < 0) return;
		else if(p == null) return;
		else {
			pieces[x][y] = p;
			storeSelectedCoord(x,y);
		}
	}

	public Piece remove(int x, int y){
		if( x > 7 || x < 0 || y > 7 || y < 0){
			System.out.println("Choice is out of bounds");
			return null;
		}
		else if (pieces[x][y] == null){
			System.out.println("No piece selected");
			return null;
		}
		else{
			Piece temp = pieces[x][y];
			pieces[x][y] = null;
			return temp;
		}
	}

	//stores x,y coordinates of selected piece
	private void storeSelectedCoord(int x, int y){
		xPos = x;
		yPos = y;
	} 

	public boolean canEndTurn() {
		if( moved ){
			return true;
		}
		else{
		    return false;
		}
	}

	public void endTurn(){
		fireturn = !(fireturn);
		moved = false;
		hasCaptured = false;
		selected.doneCapturing();
		selected = null;
	}

	public String winner(){
		int firecount = 0;
		int watercount = 0;
		for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	            	if( pieces[i][j] != null){
		            	if( pieces[i][j].isFire() ) firecount++;
		            	if( !( pieces[i][j].isFire() ) ) watercount++;
		            }
	            }
	        }
	    if(firecount == 0 && watercount == 0) return "No one";
	    else if(firecount == 0) return "Water";
	    else if(watercount == 0) return "Fire";
		else return null;
	}

	private int xBetween(int x){ //returns x location between selected piece and position x (assuming two squares apart)
		if(x > xPos) return x-1;
		else return x+1;
	}

	private int yBetween(int y){
		if(y > yPos) return y-1;
		else return y+1;
	}

	private boolean pieceInBetween(int x, int y){ //returns true if there is a piece in between (x,y) and the selected square
			if( x == xPos + 2 && y == yPos + 2){
				if(pieceAt(x-1,y-1) != null) return true;
				else return false;
			}
			else if( x == xPos - 2 && y == yPos + 2){
				if(pieceAt(x+1,y-1) != null) return true;
				else return false;
			}
			else if( x == xPos + 2 && y == yPos - 2){
				if(pieceAt(x-1,y+1) != null) return true;
				else return false;
			}
			else if( x == xPos - 2 && y == yPos - 2){
				if(pieceAt(x+1,y+1) != null) return true;
				else return false;
			}
			else return false;
	}

	//checks for capturing opportunities for kings when x,y is clicked
	private boolean kingCanCapture(int x, int y){
		if(selected.isKing() ){
			if( ((Math.abs(x - xPos) ) == 2) && xPos != x && ((Math.abs(y - yPos) ) == 2) ){
						if( pieceInBetween(x,y) ) { //checks for possibly capturing attempt
							if( selected.isFire() && !(pieces[xBetween(x)][yBetween(y)].isFire() ) ) return true;
							else if( !(selected.isFire()) && pieces[xBetween(x)][yBetween(y)].isFire() ) return true;
							else return false;					
						}
						else return false;
			}
			else{
				return false;
			}
		}
		else return false;
	}

	//verifies if there's a capturing opportunity when x,y is clicked
	private boolean canCapture(int x, int y){
			if(kingCanCapture(x,y)) return true;
			else if( ((Math.abs(x - xPos) ) == 2) && xPos != x && ((Math.abs(y - yPos) ) == 2) ){
						if( pieceInBetween(x,y) ) { //checks for possibly capturing attempt
							if( selected.isFire() && !(pieces[xBetween(x)][yBetween(y)].isFire() ) && (yPos < y) ) return true;
							else if( !(selected.isFire()) && pieces[xBetween(x)][yBetween(y)].isFire() && (yPos > y) ) return true;
							else return false;					
						}
						else return false;
			}
			else{
			 return false;
			}
	}

	//highlights selected piece's square
	private void selectedSquare(Piece selected){
		if(selected != null){
			int w = xPos;
			int z = yPos;
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(w + .5, z + .5, .5);
		}
	}

	//drawsemptyboard
	private void drawBoardEmpty(int N){
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5); // source code from StdDrawDemo
            }
        }
        selectedSquare(selected);
	}

	//draws initial board
	private void drawBoard(int N){
		drawBoardEmpty(N);

		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if( pieces[i][j] != null){
	            	if( pieces[i][j].isFire() ){
	            		if(pieces[i][j].isShield() ) {
	            			if(pieces[i][j].isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
	            			else StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	            		}
	            		else if(pieces[i][j].isBomb() ){
	            			if(pieces[i][j].isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	            			else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	            		}
	            		else{
	            			if(pieces[i][j].isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
	            			else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	            		}
	            	}

	            	else{
	            		if(pieces[i][j].isShield() ){
	            			if(pieces[i][j].isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	            			else StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	            		}
	            		else if(pieces[i][j].isBomb() ){
	            			if(pieces[i][j].isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	            			else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	            		}
	            		else{
	            			if(pieces[i][j].isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	            			else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	            		}
	                }
	            }
            }
        }
	}

	public void select(int x, int y){
		if(selected == null){
			selected = pieceAt( x, y );
			storeSelectedCoord( x , y);
		}
		else if( pieceAt(x,y) != null) {
			selected = pieceAt( x, y );
			storeSelectedCoord( x , y);
		}

		else{
			selected.move(x,y);
			moved = true;
			hasCaptured = selected.hasCaptured();
		}

	}

	//moves selected piece on board, including initial capture
	private void moveHelper(int x, int y){
	    select(x,y);
	    selectedSquare(selected);
	}

	public static void main(String[] args){
		boolean empty = false;
		int numsquares = 8;
		StdDrawPlus.setXscale(0, numsquares);
        StdDrawPlus.setYscale(0, numsquares);
        Board board = new Board(empty);
        String winner = board.winner();
        if(empty) board.drawBoardEmpty(8);

        while( (winner == null) && (!empty) ){
        	board.drawBoard(8);
        	if ( StdDrawPlus.mousePressed() ) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if( board.canSelect( (int) x, (int) y ) ){
	                board.moveHelper( (int) x, (int) y);
                }

	        }
	        else if( StdDrawPlus.isSpacePressed() ){
	        	if( board.canEndTurn() ) board.endTurn();
	        }
	        winner = board.winner();
            StdDrawPlus.show(25);
        }
        System.out.println( board.winner() );
        board.drawBoard(8);
    }

}