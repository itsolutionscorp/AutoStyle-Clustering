public class Board {

	private static int N = 8;
	private Piece[][] pieces = new Piece[N][N];
	private Piece[] pieceSelected = new Piece[1];
	private int[] selectedPiecePos = new int[2];
	private Piece[] pieceMoved = new Piece[1];
	private boolean pieceCaptured = false;
	private String player;

	public static void main (String[] args) {
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		Board board = new Board(false);
		board.drawBoard(board.N);

		/* Monitors for mouse presses.*/
		while (true) {
		  	board.drawBoard(board.N);
		     	if (StdDrawPlus.mousePressed()) {
		     		double x = StdDrawPlus.mouseX();
		     		double y = StdDrawPlus.mouseY();
		     		if (board.canSelect((int)x, (int)y) == true) {
		     			board.select((int)x, (int)y);
		     			board.pieceMoved[0].doneCapturing();
		     			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
						StdDrawPlus.filledSquare(board.selectedPiecePos[0]+ .5, board.selectedPiecePos[1] + .5, .5);
						StdDrawPlus.picture(board.selectedPiecePos[0] + 0.5, board.selectedPiecePos[1] + 0.5, 
											board.getImage(board.pieceSelected[0]), 1, 1);
		     		}
		   	
		     	}
		      	else if (StdDrawPlus.isSpacePressed()) {
		     		if (board.canEndTurn()) {
		     			board.endTurn();
		     		}
		     	}
			StdDrawPlus.show(50);
			if (board.winner() != null && board.winner()!= "No one") {
				board.player = board.winner();
			}
		  }
	}

	/*constructor for Board.*/
	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty == false) {
		initialPiece(N);
		}
		this.player = "Fire"; 
	}

/*returns whether or not the current player can end their turn. */
	public boolean canEndTurn() {
		if (pieceMoved[0] != null) {
			return true;
		}
		else if (pieceCaptured == true) {
			return true;
		}
		return false; 
	}

/* At the end of game, switch players. */
	public void endTurn() {
		if (this.player == "Fire") {
			this.player = "Water";
		}
		else{
			this.player = "Fire";
		}
		pieceSelected[0] = null;
		selectedPiecePos[0] = -1;
		selectedPiecePos[1] = -1;
		pieceMoved[0] = null;
		pieceCaptured = false;

	}

/*returns the winner of the game. */
	public String winner(){
	 	int fireCount = 0;
	 	int waterCount = 0;
	 	for (int i = 0; i < 8; i ++) {
	 		for (int j = 0; j < 8; j ++) {
	 			if (pieces[i][j] != null && pieces[i][j].isFire() == true) {
	 				fireCount += 1;
	 			}
	 			else if (pieces[i][j] != null && pieces[i][j].isFire() != true) {
	 				waterCount += 1;
	 			}
	 		}
	 	}
	 	if (fireCount == 0 && waterCount != 0) {
	 		return "Water";
	 	}
	 	else if (fireCount != 0 && waterCount == 0) {
	 		return "Fire";
	 	}
	 	else if (fireCount == 0 && waterCount == 0) {
	 		return "No one";
	 	}
	 	return null;
	}

	
/* Get pieces at position(x, y) on the board. */
	public Piece pieceAt(int x, int y) {
		if(x > 7 || y > 7){
			return null;
		}
		else if (pieces[x][y] != null) {
			return pieces[x][y];
		}
		else {
			return null;
		}

	}

/* place p at (x, y). */
	public void place (Piece p, int x, int y) {
	  	if (x > 7 || y > 7) {}
	  	else {
	  		pieces[x][y] = p; //how to change p's position since they are private?
	  		
	  	}
	  }

/*remove the Piece from the board. */
	public Piece remove (int x, int y) {
	 	if (x > 7 || y > 7) {
	 		System.out.println("the input is out of bound");
	 		return null;
	 	}
	 	else {
	 		Piece removed = pieces[x][y];
	 		pieces[x][y] = null;
	 		return removed;
	 	}
	}

	public void select (int x, int y) {
		/* if the selected square has piece.*/
		if (pieces[x][y] != null) {
			pieceSelected[0] = pieces[x][y];
			selectedPiecePos[0] = x;
			selectedPiecePos[1] = y;
		}
		/* if the selected square is empty and is the destination of
		/* already-selected non-bomb piece. */
		else if (pieceSelected[0].isBomb() == false) {
			selectedPiecePos[0] = x;
			selectedPiecePos[1] = y;
			pieceSelected[0].move(x, y);
			pieceMoved[0] = pieces[x][y];
			if (pieces[x][y].hasCaptured() == true) {
				pieceCaptured = true;
			}
		}
		/* if the selected square is empty and is the destination of
		/* already-selected bomb piece. */
		else {
			selectedPiecePos[0] = x;
			selectedPiecePos[1] = y;
			pieceMoved[0] = pieceSelected[0];
			pieceSelected[0].move(x, y);
			if (pieceAt(x, y) != null) {
				pieceMoved[0] = pieces[x][y];
			}
		}

		
	}
/* return whether the player selects the corresponding piece. */
	private boolean matchedPiece (String currentPlayer, Piece k) {
		if (currentPlayer == "Fire" && k.isFire() == true) {
			return true;
		}
		else if (currentPlayer == "Water" && k.isFire() != true) {
			return true;
		}
		return false;
	}

/* return whether the square at (x, y) can be selected. */
	public boolean canSelect(int x, int y) {
	 	if (pieceAt(x, y) != null && matchedPiece(this.player, pieceAt(x, y)) == true) {
	 		if (pieceSelected[0] == null) {
	 			return true;
	 		}
	 		else {
	 			if (pieceMoved[0] == null) {
	 				/*make the previously selected checked grey. */
	 			// 	StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
					// StdDrawPlus.filledSquare(selectedPiecePos[0] + .5, selectedPiecePos[1] + .5, .5);
					// StdDrawPlus.picture(selectedPiecePos[0] + 0.5, selectedPiecePos[1] + 0.5, getImage(pieceSelected[0]), 1, 1);
	 				return true;
	 			}
	 		}
	 	}
	 	else {
	 		if (pieceSelected[0] != null && pieceMoved[0] == null){
	 			if (validMove(selectedPiecePos[0], selectedPiecePos[1], x, y) == true
	 				|| validCaptureMove(selectedPiecePos[0], selectedPiecePos[1], x, y) == true) {
	 				return true;
	 			}
	 		}
	 		else if (pieceSelected[0] != null && pieceCaptured == true) {
	 			if (validCaptureMove(selectedPiecePos[0], selectedPiecePos[1], x, y) == true) {
	 				return true;
	 			}
	 		}
	 		else if (pieceSelected[0] != null && pieceSelected[0].isKing() == true
	 				&& validCaptureMove(selectedPiecePos[0], selectedPiecePos[1], x, y)) {
	 					if (pieceSelected[0].isFire() == true && selectedPiecePos[1] == 7) {
	 						return true;
	 					}
	 					else if (pieceSelected[0].isFire() == false && selectedPiecePos[1] == 0) {
	 						return true;
	 					}
	 				}
	 	}
	 	return false;
	 }

	/* test whether the non-capture move is valid.*/
	private boolean validMove(int xOrig, int yOrig, int xNext, int yNext) {
		/*checke if the next position is out of bound. */
		if (xNext > 7 || yNext > 7) {
			return false;
		}
		else if (pieces[xNext][yNext] != null) {
			return false;
		}
		/* king piece and go either direction.*/
		else if (pieces[xOrig][yOrig].isKing() == true) {
			if ((xNext == xOrig - 1 && yNext == yOrig + 1) || (xNext == xOrig + 1 && yNext == yOrig + 1)
				|| (xNext == xOrig - 1 && yNext == yOrig -1) || (xNext == xOrig + 1 && yNext == yOrig -1)) {
				return true;
			}
			return false;
		}
		/* Firepiece can only go up. */
		else if (pieces[xOrig][yOrig].isFire() == true) {
			if ((xNext == xOrig - 1 && yNext == yOrig + 1) || (xNext == xOrig + 1 && yNext == yOrig + 1)){
				return true;
			}
			else {
				return false;
			}
		}
		/* Waterpiece can only go down.*/
		else if (pieces[xOrig][yOrig].isFire() != true) {
			if ((xNext == xOrig - 1 && yNext == yOrig -1) || (xNext == xOrig + 1 && yNext == yOrig -1)) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}

	/*test whether the intended capture move is valid. */
	private boolean validCaptureMove(int xOrig, int yOrig, int xNext, int yNext){
		/*potential enemy's positions */
		Piece enemy1 = null;
		Piece enemy2 = null;
		Piece enemy3 = null;
		Piece enemy4 = null;
		/*handle special case. */
		if (xOrig == 0 && yOrig == 0) {
			enemy2 = pieces[xOrig + 1][yOrig + 1];
		}
		else if (xOrig == 7 && yOrig == 0) {
			enemy1 = pieces[xOrig - 1][yOrig + 1];
		}
		else if (xOrig == 0 && yOrig == 7) {
			enemy4 = pieces[xOrig + 1][yOrig - 1];
		}
		else if (xOrig == 7 && yOrig == 7) {
			enemy3 = pieces[xOrig - 1][yOrig - 1];
		}

		else if (xOrig == 0) {
			enemy2 = pieces[xOrig + 1][yOrig + 1];
			enemy4 = pieces[xOrig + 1][yOrig - 1];
		}
		else if (yOrig == 0) {
			enemy1 = pieces[xOrig - 1][yOrig + 1];
			enemy2 = pieces[xOrig + 1][yOrig + 1];
		}
		else if (xOrig == 7) {
			enemy1 = pieces[xOrig - 1][yOrig + 1];
			enemy3 = pieces[xOrig - 1][yOrig - 1];
		}
		else if (yOrig == 7) {
			enemy3 = pieces[xOrig - 1][yOrig - 1];
			enemy4 = pieces[xOrig + 1][yOrig - 1];
		}
		else {
			enemy1 = pieces[xOrig - 1][yOrig + 1];
			enemy2 = pieces[xOrig + 1][yOrig + 1];
			enemy3 = pieces[xOrig - 1][yOrig - 1];
			enemy4 = pieces[xOrig + 1][yOrig - 1];
		}

		if (xNext > 7 || yNext > 7) {
			return false;
		}
		/* king piece can go either way.*/
		else if (pieces[xOrig][yOrig].isKing() == true) {
			if(enemy1 != null && enemy1.isFire()!= pieceAt(xOrig, yOrig).isFire()
				&& xNext == xOrig - 2 && yNext == yOrig + 2){
				return true;
			}	
			else if (enemy2 != null && enemy2.isFire() != pieceAt(xOrig, yOrig).isFire()
				&& xNext == xOrig + 2 && yNext == yOrig + 2) {
				return true;
			}
			else if (enemy3 != null && enemy3.isFire() != pieceAt(xOrig, yOrig).isFire()
				&& xNext == xOrig - 2 && yNext == yOrig - 2) {
				return true;
			}
			else if (enemy4 != null && enemy4.isFire() != pieceAt(xOrig, yOrig).isFire()
					&& xNext == xOrig + 2 && yNext == yOrig - 2) {
				return true;
			}
			return false; 
		}
		/*Firepiece can only go up. */
		else if (pieces[xOrig][yOrig].isFire() == true) {
			if (enemy1 != null && enemy1.isFire() == false 
				&& xNext == xOrig - 2 && yNext == yOrig + 2) {
				return true;
			}
			else if (enemy2 != null && enemy2.isFire() != true 
				&& xNext == xOrig + 2 && yNext == yOrig + 2) {
				return true;
			}
			else {
				return false;
			}
		}
		/* waterpiece can only go down. */
		else if (pieces[xOrig][yOrig].isFire() != true) {
			if (enemy3 != null && enemy3.isFire() == true
				&& xNext == xOrig - 2 && yNext == yOrig - 2) {
				return true;
			}
			else if (enemy4 != null && enemy4.isFire() == true 
					&& xNext == xOrig + 2 && yNext == yOrig - 2) {
				return true;
			}
			return false;
		}
		return false;
	}


	/* Draw the board. */
	private void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j ++){
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
				}
				else{
					StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
				}
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (this.pieces[i][j] != null) {
					StdDrawPlus.picture(i + 0.5, j + 0.5, getImage(this.pieces[i][j]), 1, 1);
				}
				}
			}
		}
	

	/* Get image path in correspondence to the Piece's type. */
	private String getImage(Piece k) {
		if (k.isShield()) {
			if (k.isFire()){
				if (k.isKing() == true) {
					return "img/shield-fire-crowned.png";
				}
				return "img/shield-fire.png";
			}
			else{
				if(k.isKing() == true) {
					return "img/shield-water-crowned.png";
				}
				return "img/shield-water.png";
			}
		}
		else if (k.isBomb()) {
			if (k.isFire()) {
				if (k.isKing()) {
					return "img/bomb-fire-crowned.png";
				}
				return "img/bomb-fire.png";
			}
			else{
				if(k.isKing()) {
					return "img/bomb-water-crowned.png";
				}
				return "img/bomb-water.png";
			}
		}
		else {
			if (k.isFire()){
				if (k.isKing()) {
					return "img/pawn-fire-crowned.png";
				}
				return "img/pawn-fire.png";
			}
			else{
				if(k.isKing()) {
					return "img/pawn-water-crowned.png";
				}
				return "img/pawn-water.png";
			}
		}
	}


/*initialize the Piece objects.*/
	private void initialPiece(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j ++){
				if (j == 0 && i % 2 == 0) {
					pieces[i][j] = new Piece(true, this, i, j, "pawn");
				}
				else if (j == 1 && i % 2 != 0) {
					pieces[i][j] = new Piece(true, this, i, j, "shield");
				}
				else if (j == 2 && i % 2 == 0) {
					pieces[i][j] = new Piece(true, this, i, j, "bomb");
				}
				else if (j == 5 && i % 2 != 0) {
					pieces[i][j] = new Piece(false, this, i, j, "bomb");
				}
				else if (j == 6 && i % 2 == 0) {
					pieces[i][j] = new Piece(false, this, i, j, "shield");
				}
				else if (j == 7 && i % 2 != 0) {
					pieces[i][j] = new Piece(false, this, i, j, "pawn");
				}
			}
		}
	}
}


