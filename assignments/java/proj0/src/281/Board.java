public class Board{
	private static final int BOARD_DIMENSION = 8;
	private Piece[][] pieces; 
	private int fireTeam = 0; //as with piece, side 0 is fire
	private int waterTeam = 1;
	private int selectedX, selectedY, currentPlayer;
	private boolean currentPlayerMoved = false;
	private boolean currentPlayerSelected = false;
	private int firePieces = 0;
	private int waterPieces = 0;
	private boolean bombExploded = false;
	private Piece explodedBomb;
	private boolean gameOver = true;
	
	public Board(boolean shouldBeEmpty){
		emptyBoardDraw();
		currentPlayer = fireTeam;
		pieces = new Piece[BOARD_DIMENSION][BOARD_DIMENSION];
		if(shouldBeEmpty == false){
			firePieces = 12;
			waterPieces = 12;
			for(int i = 0; i < BOARD_DIMENSION; i ++){
				for(int j = 0; j < BOARD_DIMENSION; j ++){
					if((i + j) % 2 == 0){
						defaultPieceConstruct(j, i);
					}
				}
			}
		}
	}
	private void playGame(){
		while(gameOver) {
			drawBoard();
    		if (StdDrawPlus.mousePressed()) {
        		double x = StdDrawPlus.mouseX();
        		double y = StdDrawPlus.mouseY();
        		if(canSelect((int)x, (int)y)){
					select((int)x,(int)y);
				}else{
					continue;
				}
            } 
           	if (StdDrawPlus.isSpacePressed()){
        		endTurn();
        	}
        	if(winner() != null){
                    emptyBoardDraw();
                    drawBoard();
                    gameOver = false;
        	}
                StdDrawPlus.show(100);
        }
		
	}
	//to create the squares on a board
	private void emptyBoardDraw(){
		for (int i = 0; i < BOARD_DIMENSION; i++) {
			for (int j = 0; j < BOARD_DIMENSION; j++) {
		    	if ((i + j) % 2 == 0) 
		    		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		    	else                  
		    		StdDrawPlus.setPenColor(StdDrawPlus.RED);
			StdDrawPlus.filledSquare(i + .5, j + .5, .5);
		    	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
	}
	private void drawBoard(){
            for(int x = 0; x < BOARD_DIMENSION; x ++){
			for(int y = 0; y < BOARD_DIMENSION; y++){
				if(imageName(x,y) == null){
					if ((x + y) % 2 == 0) 
		    			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		    		else                  
		    			StdDrawPlus.setPenColor(StdDrawPlus.RED);
					StdDrawPlus.filledSquare(x + .5, y + .5, .5);
				}
				else
					StdDrawPlus.picture(x + .5, y + .5, imageName(x,y), 1, 1);
			}
		}
				
	}
	//determines what type of piece needs to be constructed based on row location and then creates piece
	private void defaultPieceConstruct(int x, int y){
		if(y == 0){
			pieces[x][y] = new Piece(true, this, x, y, "pawn");
		}
		else if (y == 1){
			pieces[x][y] = new Piece(true, this, x, y, "shield");
		}
		else if (y == 2){
			pieces[x][y] = new Piece(true, this, x, y, "bomb");
		}
		else if (y == 5){
			pieces[x][y] = new Piece(false, this, x, y, "bomb");
		}
		else if (y == 6){
			pieces[x][y] = new Piece(false, this, x, y, "shield");
		}
		else if (y == 7){
			pieces[x][y] = new Piece(false, this, x, y, "pawn");
		}
	}

	//action and return methods//

	public Piece pieceAt(int x, int y){
		if(outOfBounds(x) || outOfBounds(y))
			return null;
		return pieces[x][y];
	}
	public boolean canSelect(int x, int y){
		if(bombExploded)
			return false;
		if(pieceAt(x,y)!= null){ //checks to see if we're selecting a piece or empty spot.
			if(pieceAt(x,y).side() == currentPlayer){ //checks to see if the current player is selecting their own piece
				if(currentPlayerSelected == false){ //check if they have already selected a piece
					return true;
				}
				else if(currentPlayerMoved == false){//If player has selected a piece, then check if they've moved yet
                                    switchHighlightDraw();
                                    return true; //if not, they are changing their selection which is ok.
                                }
				else
					return false;
			}
		}else{
			if(currentPlayerSelected == true && currentPlayerMoved == false && validMove(selectedX, selectedY,
				x, y)) { //if they have already selected a piece but not moved the piece they can move to empty space if valid move
				return true;
			}
			if(currentPlayerSelected == true && currentPlayerMoved == true && pieceAt(selectedX,
				selectedY).hasCaptured() && validMove(selectedX, selectedY, x, y)){ //if they have already selected a piece but not moved the piece they can move to empty space if valid move
				return true;
			}
		}
		return false;
	}
	private boolean validMove(int xi, int yi, int xf, int yf){
		int deltaX = absoluteVal(xf - xi);
		int deltaY = absoluteVal(yf - yi);
		if(deltaX != deltaY) //if deltaX != deltaY it is not moving diagonally
			return false;
		if(pieceAt(xi, yi).hasCaptured() && deltaX == 1){
			return false;
		}
		if(deltaX > 1 || deltaY > 1){
			if(deltaX > 2 || deltaY > 2)
				return false; //cannot skip 3 spaces
			return validCapture(xi,yi,xf,yf);
		}else{
			if(rightDirection(pieceAt(xi,yi), yi, yf) == false){
				return false;
			}
		}
		return true;
	}	
	private boolean validCapture(int xi, int yi, int xf, int yf){
		int midX = midPoint(xi, xf);
		int midY = midPoint(yi, yf);
		Piece p = pieceAt(xi,yi);
		//can skip two spaces if capturing. Checks if capture is valid by checking first if there is a piece
		//to capture, then checks if the piece to be captured is of a opposite side.
		if(pieceAt(midX, midY) != null && pieceAt(midX,midY).side() != p.side()){
			if(rightDirection(p, yi, yf) == false)
				return false;
			return true;
		}
		return false;
	}

	private boolean rightDirection(Piece p, int yi, int yf){
		if(p.side() == 0 && yf < yi && !p.isKing())
			return false;
		if(p.side() == 1 && yf > yi && !p.isKing())
			return false;
		return true;
	}
	private int absoluteVal(int val){
		if(val < 0)
			return val * -1;
		return val;
	}
	private int midPoint(int a, int b){
		return ((a + b) / 2);
	}
	public void select(int x, int y){	
		currentPlayerSelected = true;		
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		Piece p = pieceAt(x,y);
		if(p == null){
			pieceAt(selectedX,selectedY).move(x,y);
			currentPlayerMoved = true;
		}
		selectedX = x;
		selectedY = y;
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	
	}
	public void place(Piece p, int x, int y){
		if(p == null || outOfBounds(x) || outOfBounds(y))
			return;
		else{
			pieces[x][y] = p;
			if(p.isFire()){
				firePieces++;
			}else
				waterPieces++;
		}
	}
	public Piece remove(int x, int y){
		if(outOfBounds(x) || outOfBounds(y))
			return null;
		Piece removed = pieces[x][y];
		if(removed == null){
			System.out.println("Spot already empty.");
			return null;
		}else{
			pieces[x][y] = null;
			if(removed.isBomb() && removed.hasCaptured()){
				bombExploded = true;
				explodedBomb = removed;
			}
			if(removed.isFire()){
				firePieces--;
			}
			else
				waterPieces--;
			return removed;
		}
	}
	public boolean canEndTurn(){
		if(bombExploded)
			return true;
		if(currentPlayerMoved == false)
			return false;
		if(currentPlayerMoved || pieceAt(selectedX,selectedY).hasCaptured()){
			return true;
		}
		return false;
	}
	public void endTurn(){
		if(canEndTurn() == false)
			return;
		currentPlayerMoved = false;
		currentPlayerSelected = false;
		if(!bombExploded)
			pieceAt(selectedX, selectedY).doneCapturing();
		else{
			explodedBomb.doneCapturing();
		}
		if(currentPlayer == fireTeam)
			currentPlayer = waterTeam;
		else
			currentPlayer = fireTeam;
		bombExploded = false;
		explodedBomb = null;
		switchHighlightDraw();
	}
    private void switchHighlightDraw(){
        if(pieceAt(selectedX,selectedY) == null)
            return;
        if ((selectedX + selectedY) % 2 == 0) 
            StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else                  
            StdDrawPlus.setPenColor(StdDrawPlus.RED);
        StdDrawPlus.filledSquare(selectedX + .5, selectedY + .5, .5);
        StdDrawPlus.picture(selectedX + .5, selectedY + .5, imageName(selectedX,selectedY), 1, 1);
    }
	public String winner(){
		if(firePieces == 0 && waterPieces > 0){
			return "Water";
		}else if (firePieces > 0 && waterPieces == 0) {
			return "Fire";
		}else if(firePieces == 0 && waterPieces == 0){
			return"No one";
		}
		return null;
	}

	/**
	*Checks to see if a value is out of the board.
	*/
	private boolean outOfBounds(int val){
		return (val < 0 || val > (BOARD_DIMENSION - 1));
	}
	private String imageName(int x, int y){
		Piece p = pieceAt(x,y);
		if(p != null){
			if(p.isFire()){
				if(p.isBomb()){
					if(p.isKing())
						return "img/bomb-fire-crowned.png"; 
					else
						return "img/bomb-fire.png";
				}
				else if(p.isShield()){
					if(p.isKing())
						return "img/shield-fire-crowned.png";
					else
						return "img/shield-fire.png";
				}
				else{
					if(p.isKing())
						return "img/pawn-fire-crowned.png";
					else
						return "img/pawn-fire.png";
				}
			}
			else{
				if(p.isBomb()){
					if(p.isKing())
						return "img/bomb-water-crowned.png"; 
					else
						return "img/bomb-water.png";
				}
				else if(p.isShield()){
					if(p.isKing())
						return "img/shield-water-crowned.png";
					else
						return "img/shield-water.png";
				}
				else{
					if(p.isKing())
						return "img/pawn-water-crowned.png";
					else
						return "img/pawn-water.png";
				}
			}
				
		}
		return null;
		
	}

	public static void main(String args[]){
            StdDrawPlus.setXscale(0, BOARD_DIMENSION);
            StdDrawPlus.setYscale(0, BOARD_DIMENSION);
            Board gameBoard = new Board(false);
            gameBoard.playGame();
            System.out.println(gameBoard.winner());

	}

}