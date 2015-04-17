//Zhaohong Jin//
public class Board {

	private int boardSize = 8; /*default size of the board*/
	private Piece [][] pieceList = new Piece [boardSize][boardSize]; /*data structure of the game */
	private int player = 0;
	private boolean hasMoved = false;
	private boolean hasPerformedCapture = false;
	private Piece selectedPiece;




	public static void main(String [] args){
	/*starts a GUI supported version of the game.                                         
	  */
	Board gameBoard = new Board(false);

	StdDrawPlus.setXscale(0,gameBoard.boardSize);
	StdDrawPlus.setYscale(0,gameBoard.boardSize);

	gameBoard.drawEmpty(gameBoard.boardSize);
	gameBoard.drawPieces();


	while(gameBoard.winner() == null){
		Piece p = null;
		while(gameBoard.canEndTurn() == false){
			if (StdDrawPlus.mousePressed()){
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();	
				if (gameBoard.canSelect(x , y) == true){
					gameBoard.select(x , y);
					if ((gameBoard.selectedPiece!= null) && ((x- gameBoard.getSelectedPieceX())==2) && ((y- gameBoard.getSelectedPieceY()) == 2)){
						gameBoard.hasMoved = true;		
					}
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(gameBoard.getSelectedPieceX() + 0.5, gameBoard.getSelectedPieceY() + 0.5, 0.5);
					gameBoard.update();
					StdDrawPlus.show(10);
				}
			}

		
		}							
		if (StdDrawPlus.isSpacePressed()){
			gameBoard.endTurn();
		}
	}

	String winner =  gameBoard.winner();
	System.out.println(winner);
	}




	public Board(boolean shouldBeEmpty){
	/* - The constructor for Board. If shouldBeEmpty is true, initializes an empty Board. 
	Otherwise, initializes a Board with the default configuration. 
	Note that an empty Board could possibly be useful for testing purposes.             
	 */

	if (shouldBeEmpty == true){
		for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				pieceList[i][j] = null;
			}
		}
	}else{
		for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				if ((i + j)%2==0){
					if (j == 0){
						Piece pawn_fire = new Piece(true, this, i, j, "pawn");
						pieceList[i][j] = pawn_fire;
					}
					if (j == 1){
						Piece shield_fire = new Piece(true, this, i, j, "shield");
						pieceList[i][j] = shield_fire;
					}
					if (j == 2){
						Piece bomb_fire = new Piece(true, this, i, j, "bomb");
						pieceList[i][j] = bomb_fire;
					}
					if (j == 5){
						Piece bomb_water = new Piece(false, this, i, j, "bomb");
						pieceList[i][j] = bomb_water;
					}
					if (j == 6){
						Piece shield_water = new Piece(false, this, i, j, "shield");
						pieceList[i][j] = shield_water;
					}
					if (j == 7){
						Piece pawn_water = new Piece(false, this, i, j, "pawn");
						pieceList[i][j] = pawn_water;
					}
				}
			}
		}
		
		}
	}





	private void drawEmpty(int boardSize){
		for (int i = 0; i<boardSize; i++){
			for (int j = 0; j<boardSize; j++){
				if ((i + j) % 2 == 0){
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}else{
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
			}
		}
	}







	private String getImage(Piece p){ /* inspired by Allen Zhu's response on the Piazza */
		if (p.isKing() == false){
			if (p.isFire() == true){
				if ((p.isBomb() == false) && (p.isShield() == false)){
					String s = "img/pawn-fire.png";
					return s;
				}
				if (p.isBomb() == true){
					String s = "img/bomb-fire.png";
					return s;
				}
				if (p.isShield() == true){
					String s = "img/shield-fire.png";
					return s;
				}
			}
			if (p.isFire() == false){
				if ((p.isBomb() == false) && (p.isShield() == false)){
					String s = "img/pawn-water.png";
					return s;
				}
				if (p.isBomb() == true){
					String s = "img/bomb-water.png";
					return s;
				}
				if (p.isShield() == true){
					String s = "img/shield-water.png";
					return s;
				}
			}
		}else{
			if (p.isFire() == true){
				if ((p.isBomb() == false) && (p.isShield() == false)){
					String s = "img/pawn-fire-crowned.png";
					return s;
				}
				if (p.isBomb() == true){
					String s = "img/bomb-fire-crowned.png";
					return s;
				}
				if (p.isShield() == true){
					String s = "img/shield-fire-crowned.png";
					return s;
				}
			}
			if (p.isFire() == false){
				if ((p.isBomb() == false) && (p.isShield() == false)){
					String s = "img/pawn-water-crowned.png";
					return s;
				}
				if (p.isBomb() == true){
					String s = "img/bomb-water-crowned.png";
					return s;
				}
				if (p.isShield() == true){
					String s = "img/shield-water-crowned.png";
					return s;
				}
			}
	}
	return "";
	}





	private void drawPieces(){    /* call on this method to update the pictrue of the pieces*/
		for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				if (pieceList[i][j] != null){
					String image = this.getImage(pieceList[i][j]);
					StdDrawPlus.picture(i + 0.5, j + 0.5, image, 1, 1);
				}
			}
		}
	}




	private void update(){
		drawEmpty(boardSize);
		drawPieces();

	}




	private int getSelectedPieceX(){
		//Assume selectedPiece is not null, and get the x posiiton of the piece
		for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				if ((pieceList[i][j]!=null) && (pieceList[i][j]==selectedPiece)){
					return i;
				}
			}
		}
		return 0;
	}





	private int getSelectedPieceY(){
		//Assume selectedPiece is not null, and get the y posiiton of the piece
		for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				if ((pieceList[i][j]!=null) && (pieceList[i][j]==selectedPiece)){
					return j;
				}
			}
		}
		return 0;
	}






	public Piece pieceAt(int x, int y){
	/* Gets the piece at position (x, y) on the board, or null if there is no piece. 
	If (x, y) are out of bounds, returns null. 
	*/
	if ((x >= boardSize) || (y >= boardSize)){
		return null;
	}
	for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				if (pieceList[i][j] != null){	
					if ((i == x) && (j == y)){
						return pieceList[i][j];
					}
				}
			}
		}
	return null;
	}






	public boolean canSelect(int x, int y){
	/*Returns true if there is a piece the piece at (x, y) and it can be selected.
	A piece may be selected if it is the corresponding player’s turn and one of the following is true:
	The player has not selected a piece yet.
	The player has selected a piece, but did not move it.
	An empty square may be selected if one of the following is true:
	During this turn, the player has selected a Piece which hasn’t moved yet and is selecting an 
	empty spot which is a valid move for the previously selected Piece.
	During this turn, the player has selected a Piece, captured, and has selected another valid capture destination. 
	(You may select as many captures in a row as long as they are all valid and from the same piece.) 
	*/

	if((x >= boardSize) && (y >= boardSize)){
		return false;
	}

	if((selectedPiece != null) && (this.hasMoved ==false)){
		// keep selecting on the same piece always returns true
		if ((getSelectedPieceX() == x) && (getSelectedPieceY() == y)){
			return true;
		}
	}


	if (pieceList[x][y] != null){
		Piece p = pieceAt(x, y);
		if ((p.isFire() == true) && (player == 0)){
			if (selectedPiece==null){ 
				// The player has not selected a piece yet.
				return true;
			}
			if ((selectedPiece != null) && (hasMoved == false)){
				// The player has selected a piece, but did not move it.
				return true;
			}
			return false;
		}
		if ((p.isFire() == false) && (player == 1)){
			if (selectedPiece == null){
				return true;
			}
			if ((selectedPiece != null) && (hasMoved == false)){
				return true;
			}
			return false;
		}
	}

	if ((selectedPiece != null) && (hasMoved == false) && (selectedPiece.hasCaptured()==false)){
		// the player has selected a Piece which hasn't moved yet and is selecting an empty spot//
		if (validMove(getSelectedPieceX(), getSelectedPieceY(), x, y)==true){
						 //this.hasMoved = true;
			return true;
		}
	}

	if ((selectedPiece != null) && (selectedPiece.isBomb()==false) && (selectedPiece.hasCaptured())){
		selectedPiece.doneCapturing();
	 	if (multipleCapture(getSelectedPieceX(), getSelectedPieceY(), x, y)){
			return true;
		}
	}


	return false;
	}








	private boolean validMove(int xi, int yi, int xf, int yf){
	/* Returns true if the piece at (xi, yi) can either move to (xf, yf) 
	or capture to (xf, yf) in a valid fashion compatible with the rules.
	*/
	if ((xf >= boardSize) && (yf >= boardSize)){
		return false;
	}

	int dx =(int) (0.5 * (xf - xi));
	int dy =(int) (0.5 * (yf - yi));
 	Piece p = pieceAt(xi, yi);
 	Piece p1 = null;
 	Piece p2 = null;

 	// move to an empty spot//
 	if ((Math.abs(xf-xi)==1) && (Math.abs(yf-yi) == 1)){
 		p1 = pieceAt(xf, yf);
 	}

 	// move to perform a capture//
 	if ((Math.abs(xf-xi)==2) && (Math.abs(yf-yi) == 2)){
 		p1 = pieceAt(xi + dx, yi + dy);
 		p2 = pieceAt(xf, yf);

 	}
	if (p != null){
		if (p.isKing() == false){
			// when piece is not a king piece
			if (p.isFire() == true){
				// perform a move
				if (((xf == xi + 1) && (yf == yi + 1)) || ((xf == xi - 1) && (yf == yi + 1))){ 
					if (p1 == null){
						return true;
					}
				}
				// perfrom a capture
				if (((xf == xi + 2) && (yf == yi + 2)) || ((xf == xi - 2) && (yf == yi + 2))){
					if ((p1 != null) && (p1.isFire() == false) && (p.hasCaptured() == false) && (p2 == null)){
						return true;
					}
				}					
			}
			if (p.isFire() == false){
				// perform a move
				if (((xf == xi - 1) && (yf == yi - 1)) || ((xf == xi + 1) && (yf == yi - 1))){
					if (p1 == null){
						return true;
					}
				}
				// perform a capture
				if (((xf == xi - 2) && (yf == yi - 2)) || ((xf == xi + 2) && (yf == yi - 2))){
					if ((p1 != null) && (p1.isFire() == true) && (p.hasCaptured() == false) && (p2 == null)){
						return true;
					}
				}					
			}
		}
		if (p.isKing() == true){
			// when piece is a king piece
			if (p.isFire() == true){
				// perform a move
				if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1)){ 
					if (p1 == null){
						return true;
					}
				}
				// perfrom a capture
				if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2)){
					if ((p1 != null) && (p1.isFire() == false) && (p.hasCaptured() == false) && (p2 == null)){
						return true;
					}
				}					
			}
			if (p.isFire() == false){
				// perform a move
				if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1)){
					if (p1 == null){
						return true;
					}
				}
				// perform a capture
				if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2)){
					if ((p1 != null) && (p1.isFire() == true) && (p.hasCaptured() == false) && (p2 == null)){
						return true;
					}
				}					
			}
		}

		}
	return false;

	}








	private boolean multipleCapture(int xi, int yi, int xf, int yf){
		if (validMove(xi, yi, xf, yf)){
			if ((Math.abs(xf - xi)==2) && (Math.abs(yf - yi)==2)){
				return true;
			}
		}
		return false; 
	}







	public void select(int x, int y){
	/* Selects the piece at (x, y) if possible. Optionally, 
	it is recommended to color the background of the selected square white on the GUI via the pen color function. 
	For any piece to perform a capture, that piece must have been selected first.
	*/
		if (pieceList[x][y] != null){
			selectedPiece = pieceAt(x, y);
        }
        if (pieceList[x][y] == null){
        	selectedPiece.move(x, y);
        	this.hasMoved = true;
        }
	}






	public void place(Piece p, int x, int y){
	/* Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. 
	If p already exists in the current Board, first removes it from its initial position. 
	If another piece already exists at (x, y), p will replace that piece. 
	(This method is potentially useful for creating specific test circumstances.)
	*/
	if ((p != null) && (x < boardSize) && (y < boardSize)){
		if (pieceList[x][y] != null){
			Piece removed = remove(x , y);
		}

		for (int i = 0; i < boardSize; i++){
			for (int j = 0; j < boardSize; j++){
				if (pieceList[i][j] == p){
					Piece removedP = remove(i, j);
					pieceList[x][y] = removedP;
					this.hasMoved = true;
					return;
				}
			}
		}


		pieceList[x][y] = p;



	}

}







	public Piece remove(int x, int y){
	/*Executes a remove. Returns the piece that was removed. If the input (x, y) is out of bounds, 
	returns null and prints an appropriate message. If there is no piece at (x, y), returns null and prints an appropriate message.
	*/
	Piece returnPiece;
	if((x >= boardSize) || (y >= boardSize)){
		System.out.println("input (x , y) out of bounds");
		return null;
	}
	for (int i = 0; i < boardSize; i++){
		for (int j = 0; j < boardSize; j++){
			if (pieceList[i][j] != null){
				if ((i == x) && (j == y)){
					returnPiece = pieceList[i][j];
					pieceList[i][j] = null;
					return returnPiece;
				}
			}
		}
	}
	System.out.println("There is nothing to remove");
	return null;







	}

	public boolean canEndTurn(){
	/* Returns whether or not the the current player can end their turn. To be able to end a turn, a piece must have moved or performed a capture.
	*/
	if ((hasMoved == true) || (hasPerformedCapture == true)){
		return true;
	}
	System.out.println(hasMoved);
	System.out.println(hasPerformedCapture);
	return false;

	}






	public void endTurn(){
	/* Called at the end of each turn. Handles switching of players and anything else that should happen at the end of a turn.
	*/
	if (canEndTurn()){
		player = 1 - player;
		selectedPiece = null;
		hasMoved = false;
		hasPerformedCapture = false;
		for (int i = 0; i<boardSize; i++){
			for (int j = 0; j<boardSize; j++){
				if(pieceList[i][j] != null){
					pieceList[i][j].doneCapturing();	
				}
			}
		}
	}
 	}






	public String winner(){
	/* Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board), 
	or null if the game is not yet over. Assume there is no stalemate situation in which the current player 
	has pieces but cannot legally move any of them (In this event, just leave it at null). 
	Determine the winner solely by the number of pieces belonging to each team.
	*/
	int fireLeft = 0;
	int waterLeft = 0;
	for (int i = 0; i < boardSize; i++){
		for (int j = 0; j<boardSize; j++){
			if (pieceList[i][j]!=null){
				if (pieceList[i][j].isFire() == true){
					fireLeft += 1;
				}
				if (pieceList[i][j].isFire() == false){
				waterLeft +=1;
				}
			}
		}
	}
	if ((fireLeft > waterLeft) && (waterLeft==0)){
		return "Fire";
	}
	if ((fireLeft < waterLeft) && (fireLeft==0)){
		return "Water";
	}
	if ((fireLeft == 0) && (waterLeft == 0)) {
		return "No one";
	}
	return null;

	}
}