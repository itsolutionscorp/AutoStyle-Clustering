public class Board {
	private int N;
	private Piece[][] allPieces;
	private int turn;
	private Piece selected;
	private boolean moved;
	private int selectedPX;
	private int selectedPY;
	public static void main(String[] args) {
		Board b = new Board(false);
		
        /** Monitors for mouse presses. Constantly updates board */
		String checkWinner = b.winner();
		while(checkWinner == null) {
	        b.drawBoard();
	        if (StdDrawPlus.mousePressed()) {
	            double x = StdDrawPlus.mouseX();
	            double y = StdDrawPlus.mouseY();
	            if(b.canSelect((int)x,(int)y)){
	            	b.select((int)x,(int)y);
	            }
	        }
	        if(StdDrawPlus.isSpacePressed()){
	        	b.endTurn();
	        }
	        StdDrawPlus.show(10);
	        checkWinner = b.winner();
	    }
		b.drawBoard();
	}
	
	public Board(boolean shouldBeEmpty) {
		N = 8;
		turn = 0;
		allPieces = new Piece[8][8];
		selected = null;
		moved = false;
		if(shouldBeEmpty == false){
			/* Adds initial piece configuration to make a default board*/
			if(N > 0){	
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if(j == 0 && i%2 == 0) {
							allPieces[i][j] = new Piece(true, this, i, j, "pawn");
						}
						else if(j == 1 && i > 0 && i%2 == 1) {
							allPieces[i][j] = new Piece(true, this, i, j, "shield");
						}
						else if(j == 2 && i%2 == 0) {
							allPieces[i][j] = new Piece(true, this, i, j, "bomb");
						}
						else if(j == 5 && i > 0 && i%2 == 1) {
							allPieces[i][j] = new Piece(false, this, i, j, "bomb");
						}
						else if(j == 6 && i%2 == 0) {
							allPieces[i][j] = new Piece(false, this, i, j, "shield");
						}
						else if(j == 7 && i > 0 && i%2 == 1) {
							allPieces[i][j] = new Piece(false, this, i, j, "pawn");
						}
					}
				}
			}
		}
	}
	
	private void drawBoard(){
		//sets scale
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		/* Creates new 8x8 board -- checks piece dual array to draw their positions*/
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0)// && (selectedPX != i && selectedPY != j)
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else //if(selected != null || (selectedPX != i && selectedPY != j))
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                //draws in pieces, too
                if(allPieces[i][j] != null){
                	if(allPieces[i][j].isBomb()){
                		if(allPieces[i][j].isKing())
                			if(allPieces[i][j].isFire())
                				StdDrawPlus.picture(i+0.5,j+0.5,"img/bomb-fire-crowned.png",1,1);
                			else
                				StdDrawPlus.picture(i+0.5,j+0.5,"img/bomb-water-crowned.png",1,1);
                		else
                			if(allPieces[i][j].isFire())
                				StdDrawPlus.picture(i+0.5,j+0.5,"img/bomb-fire.png",1,1);
                			else
                				StdDrawPlus.picture(i+0.5,j+0.5,"img/bomb-water.png",1,1);
                	}
                	else if(allPieces[i][j].isShield()){
                		if(allPieces[i][j].isKing())
                			if(allPieces[i][j].isFire())
                				StdDrawPlus.picture(i+0.5,j+0.5,"img/shield-fire-crowned.png",1,1);
                			else
                				StdDrawPlus.picture(i+0.5,j+0.5,"img/shield-water-crowned.png",1,1);
                		else
                			if(allPieces[i][j].isFire())
                				StdDrawPlus.picture(i+0.5,j+0.5,"img/shield-fire.png",1,1);
                			else
                				StdDrawPlus.picture(i+0.5,j+0.5,"img/shield-water.png",1,1);
                	}
                	else
                		if(allPieces[i][j].isKing())
                			if(allPieces[i][j].isFire())
                				StdDrawPlus.picture(i+0.5,j+0.5,"img/pawn-fire-crowned.png",1,1);
                			else
                				StdDrawPlus.picture(i+0.5,j+0.5,"img/pawn-water-crowned.png",1,1);
                		else
                			if(allPieces[i][j].isFire())
                				StdDrawPlus.picture(i+0.5,j+0.5,"img/pawn-fire.png",1,1);
                			else
                				StdDrawPlus.picture(i+0.5,j+0.5,"img/pawn-water.png",1,1);
                }
            }
        }
	}
	
	public Piece pieceAt(int x, int y) {
		if(x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		}
		if(allPieces[x][y] != null){
			return allPieces[x][y];
		}
		return null;
	}
	
	public boolean canSelect(int x, int y) {
		// Case 1: Selecting a piece
		if(pieceAt(x,y) != null){ //in case the player is trying to select a piece (which should be true for this case, otherwise moves on
			if(moved == false){ //if the player hasn't even selected a piece yet
				if(turn == pieceAt(x,y).side()){ //checks that the piece is on his side
					return true;
				}
				else
					return false;
			}
		}
		// Case 2: Selecting an empty square
		else if(pieceAt(x,y)==null) { // if square is empty
			if(selected!=null && moved == false){ //if a piece has been selected and no move has been done
				if(validMove(selectedPX,selectedPY,x,y)) //checks for a valid move
					return true;
			}
			else if(selected != null && selected.hasCaptured() && validMove(selectedPX,selectedPY,x,y))
				return true; //has selected a piece which has captured another piece and destination is valid
		}
		return false;
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf) {
		// Case 1: Moving one space
		if((xf-xi==1 || xf-xi == -1) && (yf-yi  ==1 || yf-yi ==-1) && moved == false) { //Checks within neighboring squares
			if(pieceAt(xf,yf)!=null){//checks if piece exists on target square
				return false;
			}
			if(pieceAt(xi,yi).isKing()){//checks if king (can move backwards)
					return true;
			}
			else if(pieceAt(xi,yi).isFire()){//for orientation of movement
				if(yf-yi>0)//moving upwards
						return true;
			}
			else{//water pieces
				if(yf-yi<0)//moving downwards
					return true;
			}
			return false;
		}
		//case 2: moving two squares
		else if((xf-xi==2 || xf-xi == -2) && (yf-yi ==2 || yf-yi ==-2)) { //check within distance two diagonally away from square
			if(pieceAt(xf,yf)!=null){//checks if piece exists on target square
				return false;
			}
			if(pieceAt(xi,yi).isKing()){//checks for king
				if(pieceAt((xi+xf)/2,(yi+yf)/2)!=null) //if there is a piece in between
					if(pieceAt((xi+xf)/2,(yi+yf)/2).side() != pieceAt(xi,yi).side()) // if that piece's side is different
						return true;
				return false;
			}
			else if(pieceAt(xi,yi).isFire()){
				if(yf-yi>0){//moving upwards
					if(pieceAt((xi+xf)/2,(yi+yf)/2)!=null) //if there is a piece in between
						if(pieceAt((xi+xf)/2,(yi+yf)/2).side() != pieceAt(xi,yi).side()) // if that piece's side is different
							return true;
					return false;
				}
			}
			else{
				if(yf-yi<0){//moving downwards
					if(pieceAt((xi+xf)/2,(yi+yf)/2)!=null) //if there is a piece in between
						if(pieceAt((xi+xf)/2,(yi+yf)/2).side() != pieceAt(xi,yi).side()) // if that piece's side is different
							return true;
					return false;
				}
			}
		}
		return false;
	}
	
	public void select(int x, int y) {
		if(selected != null){ //if there was previously a selected piece
			if(pieceAt(x,y)!=null && moved == false){ //if there is a piece at x,y (change selection) and no move has been made
				selected = pieceAt(x,y); //updates what piece is selected
				selectedPX = x;
				selectedPY = y;
				/**StdDrawPlus.setPenColor(StdDrawPlus.WHITE); //draw in white box to highlight
				StdDrawPlus.filledSquare(x + .5, y + .5, .5);*/
			}
			else { //if there isn't a piece at x,y (Moving)
				selected.move(x, y);
				allPieces[selectedPX][selectedPY] = null; //makes allPieces at original location to be null
				selectedPX = x;
				selectedPY = y;
				moved = true;
				selected = pieceAt(x,y); //updates selected piece to be previous piece
				selectedPX = x; // updates selected's location
				selectedPY = y;
			}
		}
		else{ //if no piece has previously been selected
			selected = pieceAt(x,y); //newly selected piece becomes "selected"
			selectedPX = x; //update location
			selectedPY = y;
			/**StdDrawPlus.setPenColor(StdDrawPlus.WHITE); //draw in white box to highlight
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);*/
		}
		
	}
	
	public void place(Piece P, int x, int y) {
		if(x <= 7 && y <= 7 && x >= 0 && y >= 0) { //check within bounds
			if(allPieces[x][y] != null){ //if there is a piece in (x,y)
				allPieces[x][y] = null;
				allPieces[x][y] = P;
			}
			else{
				allPieces[x][y] = P;
			}
		}
	}
	
	public Piece remove(int x, int y) {
		if(x < 8 && y < 8 && x >= 0 && y >= 0) {//checks bounds
			if(pieceAt(x,y) == null){
				System.out.println("Could not remove. No piece at (x,y),");
				return null;
			}
			Piece toReturn = pieceAt(x,y);
			allPieces[x][y] = null;
			return toReturn;
		}
		else{
			System.out.println("Could not remove. Out of Bounds");
			return null;
		}
	}
	
	public boolean canEndTurn(){
		if(moved == true){
			return true;
		}
		return false;
	}
	
	public void endTurn(){
		if(canEndTurn()){
        	if(selected!=null)
        		selected.doneCapturing();
			selected = null;
			selectedPX = -1;
			selectedPY = -1;
			if(turn == 0)
				turn = 1;
			else
				turn = 0;
			moved = false;
		}
	}
	
	public String winner(){
		boolean hasFire = false;
		boolean hasWater = false;
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(allPieces[i][j] != null && allPieces[i][j].isFire())
					hasFire = true;
				else if(allPieces[i][j] != null && !allPieces[i][j].isFire())
					hasWater =true;
			}
		}
		if(hasFire==true && hasWater==false){
			System.out.println("Fire Wins");
			return "Fire";
		}
		else if(hasFire==false && hasWater==true){
			System.out.println("Water Wins");
			return "Water";
		}
		else if(hasFire==false && hasWater==false)
			return "No one";
		return null;
	}
}