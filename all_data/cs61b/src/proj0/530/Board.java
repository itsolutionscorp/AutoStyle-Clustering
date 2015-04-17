//Open Questions
// I made N a public var. 
//CURRENT PROBLEMS: 
public class Board{ 

	private Piece[][] pieces;
	private int N = 8; 
	private int currentPlayer = 0; 
	private int selectedX = -1;
	private int selectedY = -1; 
	private boolean moved = false; 
	/**
	 * Board class. Represents Board object that starts the main GUI with all the pieces put into correct positions. 
	 * Each Board has methods that provide information about the locations of the Board and Pieces on the Board.
	 * Each piece is attached to a Board. 
	 */
	public static void main(String[] args) {

		Board b = new Board(false); 

		while(b.winner() == null){ 
			if(StdDrawPlus.mousePressed()){ 
				double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int posX = (int) x; 
                int posY = (int) y; 
                
                if(b.canSelect(posX, posY)){ 
                	b.select(posX,posY); 	
                }
  
			}
		
			if(StdDrawPlus.isSpacePressed()){  
				if(b.canEndTurn()){
					b.endTurn(); 
				}
			}
			b.drawBoard(); 
			StdDrawPlus.show(20);
		}


	}

	/**
	 * Constructor for the Board. If shouldBeEmpty is true, intializes an empty Board
	 * Otherwise, initializes a Board with the default config. 
	 * An empty Board is used for testing purposes.
	 */
	public Board(boolean shouldBeEmpty){
		
		if(shouldBeEmpty){
	       	pieces = new Piece[N][N];
		}
		else {
	        pieces = new Piece[N][N];
	       	populateRow(7, 1, false, "pawn"); 
	       	populateRow(6, 0, false, "shield"); 
	       	populateRow(5, 1, false, "bomb"); 
	       	populateRow(2, 0, true, "bomb");
			populateRow(1, 1, true, "shield");
	       	populateRow(0, 0, true, "pawn");
		}

	}

	/**
	 * Helper Methods for Board Constructor 
	 */
	private void drawBoard(){ 
		StdDrawPlus.setXscale(0, N);
	    StdDrawPlus.setYscale(0, N);

		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if(!outOfBounds(selectedX, selectedY) && selectedX == i && selectedY == j){
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE); 
                }
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);

				if(containsPiece(i,j)){ 
					String img = determineType(pieceAt(i,j)); 
					if(pieceAt(i,j).isFire()){ 
						img = img + "-fire";
					}
					else{
						img = img + "-water";
					}
					if(pieceAt(i,j).isKing()){ 
						img = img + "-crowned";
					}
					img = img + ".png"; 
					StdDrawPlus.picture(i + .5, j + .5, "img/" + img , 1, 1); 
				}
			}
		}
	}

	private String determineType(Piece p){ 
		if(p.isBomb()){ 
			return "bomb";
		}	
		else if(p.isShield()){ 
			return "shield"; 
		}
		else{
			return "pawn"; 
		}
	}

	private void populateRow(int y, int start, boolean isFire, String type){
			
			for(int x = 0; x < N; x++){
				if((start + x) % 2 == 0){
					Piece p = new Piece(isFire, this, x, y, type); 
					pieces[x][y] = p; 
				}
			}
	}


	/**
	 * Gets the Piece object at position (x,y) on the Board or null if no Piece or index out of bounds. 
	 */
	public Piece pieceAt(int x, int y){ 

		if(!outOfBounds(x,y)){ 
			return pieces[x][y];
		}
		return null; 
	} 

	private boolean containsPiece(int x, int y){

		if(pieceAt(x,y) != null){ 
			return true;
		}

		return false; 
	}

	private void setNull(int x, int y){ 
		pieces[x][y] = null; 
	}

	private void setPiece(Piece p, int x, int y){ 
		pieces[x][y] = p; 
	}

	/**
	 * Returns true if there is a piece (x,y) and it can be selected.
	 * Can be selected if: 
	 * 1. It is corresponding player's turn. 
	 * 2. The player has selected a piece, but did not move it. 
	 * 
	 * Empty Square can be selected if: 
	 * 1. During the same turn, the player hasn't moved a piece yet and 
	 * is selecting a valid slot for the previously selected piece.
	 * 2. During this turn, a player has captured a piece and has selected another valid
	 * capture desitnation 
	 */
	public boolean canSelect(int x, int y){ 

		if(containsPiece(x,y) && (outOfBounds(selectedX, selectedY) || !moved)){
			if(currentPlayer == 0 && pieceAt(x,y).isFire()){ 
				return true; 
			}
			else if(currentPlayer == 1 && !pieceAt(x,y).isFire()){
				return true;
			}
		}
		else if (!outOfBounds(selectedX, selectedY) && containsPiece(selectedX, selectedY) && (pieceAt(selectedX, selectedY).hasCaptured() || !moved)) {
			if(validMove(x,y)){
				return true; 
			}
		}

		return false; 
	}

	private boolean validMove(int x, int y){ 

		if(pieceAt(selectedX, selectedY).isKing()){ 
			return validMoveFire(selectedX, selectedY, x, y) || validMoveWater(selectedX, selectedY, x, y); 
		}
		else if(pieceAt(selectedX, selectedY).isFire()){ 
			return validMoveFire(selectedX, selectedY, x, y);
		}
		else{ 
			return validMoveWater(selectedX, selectedY, x, y); 
		}
	}

	private boolean validMoveFire(int currX, int currY, int destX, int destY) { 

		if(!pieceAt(selectedX, selectedY).hasCaptured() && validCombAbs(currX, destX, 1) && validComb(currY, destY, 1)) { 
			return true; 
		} 
		else{ 

			if(containsPiece(currX + 1, currY + 1) && validComb(currX, destX, 2) && validComb(currY, destY, 2) && 
				(pieceAt(currX, currY).side() != pieceAt(currX + 1, currY + 1).side())){
				return true; 
			}

			else if(containsPiece(currX - 1, currY + 1) && validComb(currX, destX, -2) && validComb(currY, destY, 2) && 
				(pieceAt(currX, currY).side() != pieceAt(currX - 1, currY + 1).side())){ 
				return true; 
			}

		}
		
		return false; 
	}

	private boolean validMoveWater(int currX, int currY, int destX, int destY){
		if(!pieceAt(selectedX, selectedY).hasCaptured() && validCombAbs(currX, destX, 1) && validComb(currY, destY, -1)) {
			return true; 
		}
		else{ 
			if(containsPiece(currX - 1, currY - 1) && validComb(currX, destX, -2) && validComb(currY, destY, -2) && 
				(pieceAt(currX, currY).side() != pieceAt(currX - 1, currY - 1).side())){
				return true; 
			}

			else if(containsPiece(currX + 1, currY - 1) && validComb(currX, destX, 2) && validComb(currY, destY, -2) && 
				(pieceAt(currX, currY).side() != pieceAt(currX + 1, currY - 1).side())){ 
				return true; 
			}
		}

		return false; 

	}


	private boolean validCombAbs(int curr, int dest, int diff){
		return Math.abs(curr-dest) == diff;

	}

	private boolean validComb(int curr, int dest, int diff){ 
		return dest - curr == diff;
	}


	/**
	 * Selects the piece at (x,y) if possible. 
	 * Optional: Color the background piece white to highlight it a "selected". 
	 * (use penColor function) 
	 * Caution: Piece must be selected first to perform a capture
	 */

	public void select(int x, int y){ 
		if(containsPiece(x,y)){ 
			selectedX = x;
			selectedY = y;
		}
		else{ 
			pieceAt(selectedX, selectedY).move(x, y);
			selectedX = x; 
			selectedY = y; 	
			moved = true; 

		}
	

	}

	/**
	 * Puts Piece in (x,y). If p is null or (x,y) is out of bounds does nothing. 
	 * If p already exists on the current board, removes it from initial position 
	 * DOESN'T MAKE SENSE ----> If piece already exists at (x,y) then p replaces that piece
	 * (for specific testing circumstances?)
	 */
	
	public void place(Piece p, int x, int y){
		if(p!= null && !outOfBounds(x,y)){

			if(pieceAt(x,y) != null){ 
				remove(x, y); 
			}

			int[] coords = getCoords(p); 
			if(coords != null){ 
				Piece toBePlaced = remove(coords[0], coords[1]); 
				setPiece(toBePlaced, x, y); 
			}
			else{
				setPiece(p, x, y); 
			}

		}

		
	}

	private int[] getCoords(Piece p){ 
		int[] coords = new int[2]; 
		boolean check = false;
		for(int i = 0; i < pieces.length; i++){
			for(int j = 0; j < pieces.length; j++){
				if(pieces[i][j] == p){
					check = true; 
					coords[0] = i;
					coords[1] = j; 
				}
			}
		}
		if(check){ 
			return coords; 
		}
		else{ 
			return null; 
		}
	

	}



	private boolean outOfBounds(int x, int y){ 
		return (x < 0 || x > N-1) ||(y < 0 || y > N-1); 

	}

	/**
	 * Removes piece at current position and returns that Piece.
	 * If (x,y) is out of bounds or no piece at that (x,y) returns null.
	 */
	public Piece remove(int x, int y){ 
		if(!outOfBounds(x,y) && containsPiece(x,y)){
			Piece removed = pieceAt(x,y);
			setNull(x,y); 
			return removed; 
		}
		return null; 
	}

	/**
	 * Returns whether the current player can end their turn.
	 * Either: 
	 * 1. Piece must have moved.
	 * 2. Capture must have been performed
	 * @return
	 */

	public boolean canEndTurn(){
		if(outOfBounds(selectedX, selectedY)){ 
			return false;
		}
		else if(moved){
			return true; 
		}

		return false; 

	}

	/**
	 * Handles swithching of players and anything else that should happen at end of a turn. 
	 */
	public void endTurn(){ 
		if(currentPlayer == 0){ 
			currentPlayer = 1; 
		}
		else{
			currentPlayer = 0; 
		}
		
		if(containsPiece(selectedX, selectedY)){ 
			pieceAt(selectedX, selectedY).doneCapturing(); 
		}

		moved = false; 
		selectedX = -1;
		selectedY = -1; 
	}

	/**
	 * Returns winner of game. "Fire", "Water" or "No One"(no pieces on board) 
	 * Returns null if game is not over
	 * Determine winner by number of pieces belonging to each team. 
	 * @return
	 */

	public String winner(){ 
		int waterPiece = 0;
		int firePiece = 0;
		for(int i = 0; i < pieces.length; i++){ 
			for(int j = 0; j < pieces[i].length; j++){
				if(containsPiece(i,j)){ 
					if(pieceAt(i,j).isFire()){ 
						firePiece += 1; 
					}
					else{ 
						waterPiece += 1; 
					}
				}
			}
		}
		if(waterPiece == 0 && firePiece == 0){ 
			return "No one";
		}
		else if(waterPiece == 0){
			return "Fire";
		}
		else if(firePiece == 0){ 
			return "Water";
		}

		return null; 
	}
	


}







