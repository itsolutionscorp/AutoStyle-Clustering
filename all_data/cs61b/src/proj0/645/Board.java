/* 
*	Brenden Prieto
*	25077650
*/

public class Board{

	private int waterPieces = 0;
	private int firePieces = 0;
	private int selectedX;
	private int selectedY;
	private boolean[][] pieces;
	private Piece[][] checkers;
	private Piece selected = null;
	private boolean player1 = true;
	private boolean hasSelected = false;
	private boolean hasMoved = false;

	private void drawBoard(){
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		for (int x = 0; x < 8; x++){
			for (int y = 0; y < 8; y++){
				if ((x + y) % 2 == 0){
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					StdDrawPlus.filledSquare(x + .5, y + 0.5 , 0.5);
				}
				else{
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
					StdDrawPlus.filledSquare(x + .5, y + 0.5 , 0.5);
				}
			}
		}
	}

	private void redraw(){
		drawBoard();
		String type = "";
		String side = "";
		String addKing = "";

		for(int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (pieces[i][j]){
					/* Finding the type of the piece */
					if (checkers[i][j].isBomb()) { type = "bomb"; }
					else if (checkers[i][j].isShield()) { type = "shield"; }
					else { type = "pawn"; }

					/* Finding the side of the pieece */
					if (checkers[i][j].isFire()) { side = "fire"; }
					else { side = "water"; }

					/* Finding if the piece is a king */
					if (checkers[i][j].isKing()) { addKing = "-crowned"; }
					else { addKing = ""; }

					StdDrawPlus.picture(i + 0.5, j + 0.5, "img/" + type + "-" + side + addKing + ".png", 1, 1);
				}
			}
		}
	}

	private void defaultWater(){
		for (int i = 5; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (i == 5 && (i + j) % 2 == 0){
					checkers[j][i] = new Piece(false, this, j, i, "bomb");
					pieces[j][i] = true;
					waterPieces += 1;
				}
				else if (i == 6 && (i + j) % 2 == 0){
					checkers[j][i] = new Piece(false, this, j, i, "shield");
					pieces[j][i] = true;
					waterPieces += 1;
				}
				else if (i == 7 && (i + j) % 2 == 0) {
					checkers[j][i] = new Piece(false, this, j, i, "pawn");
					pieces[j][i] = true;
					waterPieces += 1;
				}
			}
		}
	}

	private void defaultFire(){
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 8; j++){
				if (i == 0 && (i + j) % 2 == 0){
					checkers[j][i] = new Piece(true, this, j, i, "pawn");
					pieces[j][i] = true;
					firePieces += 1;
				}
				else if (i == 1 && (i + j) % 2 == 0){
					checkers[j][i] = new Piece(true, this, j, i, "shield");
					pieces[j][i] = true;
					firePieces += 1;
				}
				else if (i == 2 && (i + j) % 2 == 0) {
					checkers[j][i] = new Piece(true, this, j, i, "bomb");
					pieces[j][i] = true;
					firePieces += 1;
				}
			}
		}
	}

	public Board(boolean shouldBeEmpty){
		if (shouldBeEmpty) {
			// initializes an empty Board
			pieces = new boolean[8][8];
			checkers = new Piece[8][8];
			firePieces = 0;
			waterPieces = 0;
		}
		else{
			// initializes a Board with default configs.
			
			pieces = new boolean[8][8];
			checkers = new Piece[8][8];
			defaultWater();
			defaultFire();
		}
	}

	public Piece pieceAt(int x, int y){
		/* gets the piece at position (x, y) on the board
		*  or null if there is no piece.  If (x, y) are out
		*  of bounds, returns null
		*/
		if (!isInBounds(x, y)) {
			return null;
		}
		else if (pieces[x][y]){
			return checkers[x][y];
		}
		else{
			return null;
		}

	}

    public boolean canSelect(int x, int y){
	 	
	 	if (player1){
	 		return canPlayer1(x, y);
	 	}
	 	else{
	 		return canPlayer2(x, y);
	 	}
	}

	private boolean canPlayer1(int x, int y){
		/* If there is a piece at the location we want */
		if (pieces[x][y]){
	 		/* If the player has not selected a piece yet and the piece at x, y is a Fire type */
 			if (!hasSelected && checkers[x][y].isFire()) { 

 				return true;
 			}
 			/* If the player has selected a piece but has not moved it yet. */
 			else if (hasSelected && !hasMoved) { return true; }
 		}
 		/* If the player selects an empty square */
 		else{
 			/* If the player has selected a piece but has not yet moved it */
 			if (hasSelected && !hasMoved){
 				/* If the empty spot is a valid move for the selected piece */
 				return validMove(selected.isKing(), selected.isFire(), selectedX, selectedY, x, y);
 			}
 			/* If the player has selected a piece and has captured a piece */
 			else if (hasSelected && selected.hasCaptured() && !selected.isBomb() && Math.abs(selectedX - x) != 1 && Math.abs(selectedY - y) != 1){
 				/* if the player has selected another valid capture destination */
 				return validMove(selected.isKing(), selected.isFire(), selectedX, selectedY, x, y);
 			}
 		}

 		return false;
	}

	private boolean canPlayer2(int x, int y){
		/* If there is a piece at the location we want */
		if (pieces[x][y]){
	 		/* If the player has not selected a piece yet and the piece at x, y is a Water type */
 			if (!hasSelected && !(checkers[x][y].isFire())) { return true; }
 			/* If the player has selected a piece but has not moved it yet. */
 			else if (hasSelected && !hasMoved) { return true; }
 		}
 		/* If the player selects an empty square */
 		else{
 			/* If the player has selected a piece but has not yet moved it */
 			if (hasSelected && !hasMoved){
 				/* If the empty spot is a valid move for the selected piece */
 				return validMove(selected.isKing(), selected.isFire(), selectedX, selectedY, x, y);
 			}
 			/* If the player has selected a piece and has captured a piece */
 			else if (hasSelected && selected.hasCaptured() && !selected.isBomb() && Math.abs(selectedX - x) != 1 && Math.abs(selectedY - y) != 1){
 				/* if the player has selected another valid capture destination */
 				return validMove(selected.isKing(), selected.isFire(), selectedX, selectedY, x, y);
 			}
 		}

 		return false;
	}

	private boolean validMove(boolean isKing, boolean isFire, int x, int y, int xf, int yf){
		/* If there are no pieces at the location we want to go to */
		if (!pieces[xf][yf]){
			/* If we want to capture a piece */
			if ((Math.abs(xf - x) == 2) && (Math.abs(yf - y) == 2)){
				int xOpponent;
				int yOpponent;

				/* Finding the location of the opponent */
				if (xf > x) { xOpponent = x + 1; }
				else { xOpponent = x - 1; }

				if (yf > y) { yOpponent = y + 1; }
				else { yOpponent = y - 1; }

				/* if piece is a Fire type and there is a Fire type piece between it and the next move */
				if (isFire && pieces[xOpponent][yOpponent] && !checkers[xOpponent][yOpponent].isFire()) {
					if ((xf + yf) % 2 == 0) { return true; }
					else { return false; }
				}
				/* if piece is a Water type and there is a Fire type piece between it and the next move */
				else if (!isFire && pieces[xOpponent][yOpponent] && checkers[xOpponent][yOpponent].isFire()){
					if ((xf + yf) % 2 == 0) { return true; }
					else { return false; }
				}
				/* if piece is a Fire type, is a King, and there is a Water type piece between it and the next move */
				else if (isFire && isKing && pieces[xOpponent][yOpponent] && !checkers[xOpponent][yOpponent].isFire()) {
					if ((xf + yf) % 2 == 0) { return true; }
					else { return false; }
				}
				/* if piece is a Water type, is a King, and there is a Fire type piece between it and the next move */
				else if (!isFire && isKing && pieces[xOpponent][yOpponent] && checkers[xOpponent][yOpponent].isFire()) {
					if ((xf + yf) % 2 == 0) { return true; }
					else { return false; }
				}
				else { return false; }
			}
			/* if we just want to move the piece */
			else if ((Math.abs(xf - x) == 1) && (Math.abs(yf - y) == 1)){
				/* if piece is not a King and is a Fire type it must move up the board */
				if (!isKing && isFire && (yf == y + 1)){
					if ((xf + yf) % 2 == 0){ return true; }
			 		else { return false; }
			 	}
			 	/* if piece is not a King and is a Water type it must move down the board */
			 	else if (!isKing && !isFire && (yf == y - 1)){
			 		if ((xf + yf) % 2 == 0){ return true; }
			 		else { return false; }
			 	}
			 	/* if piece is a King, regardless of its type, it can move anywhere */
			 	else if (isKing) {
			 		if ((xf + yf) % 2 == 0){ return true; }
			 		else { return false; }
			 	}
			 	/* For any weird situations when the above cases aren't satisfied */
			 	else{ return false; }
			}
			/* if the final move is < 1 or > 2 then return false */
			else { return false; }
		}
		/* If there are pieces where we want to move */
		else {
			if (xf == selectedX && yf == selectedY){
				return true;
			}
		 	return false;
		}
	}

	public void select(int x, int y){
		/* If there is a piece at x, y then make that our selected piece */
		if (pieces[x][y]){
			selected = pieceAt(x, y);	// Private Piece variable that sets holds the selected piece
			selectedX = x;				// Private int variable that sets the X location of the selected piece
			selectedY = y;				// Private int variable that sets the Y location of the selected piece
			hasSelected = true;
			hasMoved = false;
		}
		else{
			selectedX = x;
			selectedY = y;

			selected.move(x, y);
		}
	}
	/* Checks if the user selected location is within the boundaries of the board */
	private boolean isInBounds(int x, int y){
		return ((x >= 0) && (x <= 7) && (y >= 0) && (y <= 7));
	}

	/* Helper method for the PLACE method */
	private int[] alreadyExists(Piece p){
		/* Checks if piece P is already on the board. 
		*  If it is, the method returns the x and y location in an int array.
		*  If it doesn't, the method returns null.
		*/

		int[] temp = new int[2];

		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (checkers[i][j] == p){
					temp[0] = i;
					temp[1] = j;
					return temp;
				}
			}
		}
		return null;
	}

	public void place(Piece p, int x, int y){

		/* If piece we want to place is not null and it is in bounds */
		if (p != null && isInBounds(x, y)){
			int[] exists = alreadyExists(p);

			/* If the piece is already on the board, remove it */
			if (exists != null){
				int i = exists[0];
				int j = exists[1];
				remove(i, j);
			} 

			/* If there is a piece at the location we want to place our piece, remove it. */
			if (pieces[x][y]){
				remove(x, y);
			}

			if (p.isFire()) { firePieces += 1; } 	// keeps track of the fire pieces. if the piece already
			else{ waterPieces += 1; }				// existed, 1 would be taken away from fire piece and
													// added back here.
			checkers[x][y] = p;		// Sets the piece in our private piece array, at (x, y) to P.
			pieces[x][y] = true;	
			
			hasMoved = true;		// Private boolean variable to keep track of when a player has moved.
		}
	}

	public Piece remove(int x, int y){

		if (!isInBounds(x, y)){
			System.out.println("Index out of bounds.");
			return null;
		}
		else{
			/* If there is not a piece at location x, y on board */
			if (!pieces[x][y]){
				System.out.println("No piece at index (" + x + ", " + y + ").");
				return null;
			}
			else{
				Piece temp = pieceAt(x, y);
				if (checkers[x][y].isFire()) { firePieces -= 1; }	// Private in FIREPIECES keeps track of # Fire type pieces
				else { waterPieces -= 1; }							// Private int WATERPIECES keeps track of # of Water type pieces

				checkers[x][y] = null;	// Removes the piece from the Piece array
				pieces[x][y] = false;	// Removes the piece from the boolean array

				return temp;			// Returns the piece we removed.
			}	
		}
	}

	public boolean canEndTurn(){
	/* If the player has selected a piece and has moved it (or captured another piece)
	*  the turn can be ended. */
		return ((selected != null) && (selected.hasCaptured() || hasMoved));
	}

	public void endTurn(){
		/*   Called at the end of each turn. Handles switching of players and anything
		     else that should happen at the end of a turn.
		*/
		selected.doneCapturing();	// Sets the piece's hasCaptured() value to false;
		selected = null;			// Resets the selected Piece attribute
		selectedX = 1;				// Resets the selected XVALUE attribute to an open space
		selectedY = 0;				// Resets the selected YVALUE attribute to an open space
		hasSelected = false;		
		hasMoved = false;

		/* This step handles changing players */
		if (player1){
			player1 = false;
		}
		else{
			player1 = true;
		}
	}

	private boolean isWinner(){
		if (firePieces == 0 && waterPieces == 0){
			return true;
		}
		else if (firePieces == 0){
			return true;
		}
		else if (waterPieces == 0){
			return true;
		}
		else{
			return false;
		}
	}

	public String winner(){
	/* Determines the winner of the game based on the number of
	*  pieces each player has
	*/
		if (firePieces == 0 && waterPieces == 0){
			return "No one";
		}
		else if (firePieces == 0){
			return "Water";
		}
		else if (waterPieces == 0){
			return "Fire";
		}
		else{
			return null;
		}
	}

	public static void main(String[] args){
		Board test = new Board(false);

		double x;
		double y;
	
		while (!test.isWinner()){
			test.redraw();
			if (StdDrawPlus.mousePressed()){
				x = StdDrawPlus.mouseX();
				y = StdDrawPlus.mouseY();
				
				if (test.canSelect((int)x, (int)y)){
					System.out.println("Location: (" + (int)x + ", " + (int)y + ")");
					test.select((int)x, (int)y);
				}
			}
			else if (StdDrawPlus.isSpacePressed()){
				if (test.canEndTurn()){
					test.endTurn();
				
				}
			}
			StdDrawPlus.show(10);
		}

		System.out.println("Winner: " + test.winner() + "!");
	}
}