public class Board {
	private Piece[][] pieces;
	private Piece selectedPiece;
	private int fireNumPieces=0;
	private int waterNumPieces=0;
	private int turn = 0;
	private String winner;
	private boolean pieceMoved = false;	

	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty){
			pieces = new Piece[8][8];
		}
		else {
			pieces = new Piece[8][8];
			fireNumPieces=12;
			waterNumPieces=12;

			for (int i=0; i<8; i++) {
				for (int j=0; j<8; j++) {
					if (i%2==0) {
						pieces[i][0] = new Piece(true, this, i, 0, "pawn");
						pieces[i][2] = new Piece(true, this, i, 2, "bomb");
						pieces[i][6] = new Piece(false, this, i, 6, "shield");
					}
					else {
						pieces[i][1] = new Piece(true, this, i, 1, "shield");
						pieces[i][7] = new Piece(false, this, i, 7, "pawn");
						pieces[i][5] = new Piece(false, this, i, 5, "bomb");
					}
				}
			}
		}
	}

	private void drawBoard(int N) {
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if (pieces[i][j]!= null) {
                	if (pieces[i][j].isFire()){
                		if (pieces[i][j].isKing()) {
	                		if (pieces[i][j].isShield()) {
		                   		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
		              	 	}

		              	 	if (pieces[i][j].isBomb()){
		              	 		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
		              	 	}
		              	 	if ((!pieces[i][j].isBomb()) && (!pieces[i][j].isShield())){
		              	 		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
		              	 	}
                		}
	                	else if (pieces[i][j].isShield()) {
	                   		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	              	 	}

	              	 	else if (pieces[i][j].isBomb()){
	              	 		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	              	 	}
	              	 	else if ((!pieces[i][j].isBomb()) && (!pieces[i][j].isShield())){
	              	 		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	              	 	}
              		}
              		else {
              			if (pieces[i][j].isKing()) {
	              			if (pieces[i][j].isShield()) {
		                   		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
		              	 	}
		              	 	if (pieces[i][j].isBomb()){
		              	 		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
		              	 	}
		              	 	if ((!pieces[i][j].isBomb()) && (!pieces[i][j].isShield())){
		              	 		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
		              	 	}
		              	}
		              	else if (pieces[i][j].isShield()) {
	                   		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	              	 	}
	              	 	else if (pieces[i][j].isBomb()){
	              	 		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	              	 	}
	              	 	else if ((!pieces[i][j].isBomb()) && (!pieces[i][j].isShield())){
	              	 		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	              	 	}
              		}	
              	}
            }
        }
    }

	public static void main (String[] args) {
		Board b = new Board(false);

		while (b.winner == null) {
			b.drawBoard(8);
			StdDrawPlus.show(50);
			b.clickedSquare();
			b.spacebar();
			b.winner();
		}
	}

	private void clickedSquare() {
		if (StdDrawPlus.mousePressed()) {
			int x = (int)StdDrawPlus.mouseX();
			int y = (int)StdDrawPlus.mouseY();
			if (canSelect(x,y)) {
				select(x,y);
			}
		}
	}

	private void spacebar() {
		if (StdDrawPlus.isSpacePressed()) {
			if (canEndTurn()) {
				endTurn();
			}
		}
	}

	/* Gets the piece at position (x, y) on the board,or null if there is no piece. 
	 * If (x, y) are out of bounds, returns null. */
	public Piece pieceAt(int x, int y) {
		if ((x>7) || (x<0) || (y>7) || (y<0)) {
			return null;
		}
		else if (this.pieces[x][y]!=null) {
			return this.pieces[x][y];
		}
		return null;

	}


	/* Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. 
	   If p already exists in the current Board, first removes it from its initial position. 
	   If another piece already exists at (x, y), p will replace that piece. 
	   (This method is potentially useful for creating specific test circumstances.) */
	public void place(Piece p, int x, int y) {
		if ((x>7) || (x<0) || (y>7) || (y<0) || (p==null)) {
			return;
		}
		if (p.isFire()){
			this.fireNumPieces += 1;
		}
		else{
			this.waterNumPieces += 1;
		}
		this.pieces[x][y] = p;
	}

	/* Executes a remove. Returns the piece that was removed. 
	   If the input (x, y) is out of bounds, returns null 
	   and prints an appropriate message. If there is no piece at (x, y), 
	   returns null and prints an appropriate message. */
	public Piece remove(int x, int y) {
		if ((x>7) || (x<0) || (y>7) || (y<0)) {
			System.out.println("ERROR: (" + x + ", " + y + ") is out of bounds.");
			return null;
		}
		else if (pieces[x][y]==null) {
			System.out.println("ERROR: There is no piece at (" + x + ", " + y + ").");
			return null;
		}
		else {
			Piece temp = this.pieces[x][y];
			if (temp.isFire()){
				this.fireNumPieces -= 1;
			}
			else {
				this.waterNumPieces -= 1;
			}
			this.pieces[x][y] = null;
			return temp;
		}
	}

	/* Returns true if the piece at (xi, yi) can either move to (xf, yf) 
	 * or capture to (xf, yf), strictly from a geometry/piece-race point of view. 
	 * validMove does not need to take into consideration whose turn it is 
	 * or if a move has already been made, but rather can */
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if ((xi<0) || (xf<0) || (yi<0) || (yf<0) ||
		    (xi>7) || (xf>7) || (yi>7) || (yf>7) || (this.pieceAt(xf,yf)!= null)) {
			return false;
		}

		//right side & final destination is empty square
		else if ((this.pieceAt(xi,yi).side() == turn) && (this.pieceAt(xf,yf)==null)) {
			//if making normal move
			if (Math.abs(xf-xi)==1) {
				//allows king to move one space both directions
				if ((this.pieceAt(xi,yi).isKing()) && (Math.abs(yf-yi)==1) && (!this.pieceAt(xi,yi).hasCaptured())) {
					return true;
				}
				else if ((this.turn == 0) && ((yf-yi)==1) && (!this.pieceAt(xi,yi).hasCaptured())) {
					return true;
				}
				else if ((this.turn == 1) && ((yf-yi)==-1) && (!this.pieceAt(xi,yi).hasCaptured())) {
					return true;
				}
			}
			else if (Math.abs(xf-xi)==2){
				//allows king to move two spaces both directions
				if ((this.pieceAt(xi,yi).isKing()) && (Math.abs(yf-yi)==2) && (!this.pieceAt(xi,yi).hasCaptured())) {
					return true;
				}
				if ((this.turn == 0) && ((yf-yi)==2) && (this.pieceAt(median(xf,xi),median(yf,yi)).side()==1)) {
					return true;
				}
				else if ((this.turn == 1) && ((yf-yi)== -2) && (this.pieceAt(median(xf,xi),median(yf,yi)).side()==0)) {
					return true;
				}			
			}
		}
		return false;
	}


	private int median(int a, int b) {
		return (int)((a+b)/2);
	}


	/* Returns true if the square at (x, y) can be selected. */
	public boolean canSelect(int x, int y) {

		//if haven't selected a piece and tries to select square
		if ((this.selectedPiece==null) && (this.pieceAt(x,y) == null)) {
			return false;
		}

		//outofbounds
		else if ((x>7) || (x<0) || (y>7) || (y<0)) {

			return false;
		}

		//if clicking a piece:
		else if (this.pieceAt(x,y) != null) {
			//if you didn't already select a piece
			if ((this.selectedPiece == null) && (this.pieceAt(x,y).side() == turn)){
				return true;
			}

			//if you select a piece and haven't made a move
			if ((this.selectedPiece != null) && (!this.pieceMoved) && (this.pieceAt(x,y).side() == turn)) {
				return true;
			}
			return false;
		}

		//selecting empty square
		else if (this.pieceAt(x,y) == null) {

			if ((this.selectedPiece!=null) && (!this.pieceMoved) && 
				(validMove(getIndexX(this.pieces, this.selectedPiece), 
						   getIndexY(this.pieces, this.selectedPiece),x,y))) {
				return true;
			}


			else if ((this.selectedPiece!=null) && this.selectedPiece.hasCaptured() &&
				(validMove(getIndexX(this.pieces, this.selectedPiece), 
						   getIndexY(this.pieces, this.selectedPiece),x,y))) {
				return true;
			}

			return false;
		}
		return false;
	}

	/* Returns the X position of a piece */
	private int getIndexX(Piece[][] pArray, Piece p){
		int ans= -1000000;
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if (pArray[i][j] == p){
					ans = i;
				}
			}
		}
		return ans;
	}

	/* Returns the Y position of a piece */
	private int getIndexY(Piece[][] pArray, Piece p){
		int ans= -10000000;
		for (int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				if (pArray[i][j] == p){
					ans = j;
				}
			}
		}
		return ans;
	}


	/* Selects the square at (x, y). 
	 * This method assumes canSelect (x,y) returns true.
	 * If you select an empty square (assuming canSelect returns true), 
	 * you should move your most recently selected piece to that square.
	 * For any piece to perform a capture, that piece must have been selected first */
	public void select(int x, int y) {

		//if haven't selected piece and you click a piece
		if ((selectedPiece == null) && (this.pieceAt(x,y)!= null)) {
			this.selectedPiece = this.pieceAt(x,y);
		}
		//else if have selected piece, and selects another piece
		else if ((selectedPiece!=null) && (this.pieceAt(x,y)!= null)) {
			this.selectedPiece = this.pieceAt(x,y);
		}

		//else if select empty square
		else if (this.pieceAt(x,y) == null) {
			
			this.selectedPiece.move(x,y);
			this.pieceMoved = true;
			this.selectedPiece = this.pieceAt(x,y);
		}
	}

	/* Returns whether or not the the current player can end their turn. 
	   To be able to end a turn, a piece must have moved or performed a capture. */
	public boolean canEndTurn() {
	 	return this.pieceMoved;
	}

	/* Called at the end of each turn. 
	   Handles switching of players and anything else 
	   that should happen at the end of a turn. */
	public void endTurn() {
			if (this.turn == 0) {
				this.turn = 1;
			}
		 	else {
				this.turn = 0;
		 	}

		 	this.pieceMoved = false;

		 	if (this.selectedPiece != null) {
		 		this.selectedPiece.doneCapturing();
		 		this.selectedPiece=null;
		 	}
	}


	/* Returns the winner of the game. 
	"Fire", "Water", "No one" (tie / no pieces on the board), or null if the game is not yet over. 
	Assume there is no stalemate situation 
	in which the current player has pieces but cannot legally move any of them 
	(In this event, just leave it at null). 
	Determine the winner solely by the number of pieces belonging to each team */
	public String winner() {
		if ((this.fireNumPieces == 0) && (this.waterNumPieces > 0)) {
			this.winner = "Water";
			return this.winner;
		}
		else if ((this.waterNumPieces == 0) && (this.fireNumPieces > 0)) {
			this.winner = "Fire";
			return this.winner;
		}
		else if ((this.fireNumPieces == 0) && (this.waterNumPieces == 0)) {
			this.winner = "No one";
			return this.winner;
		}

		else if ((this.fireNumPieces > 0) && (this.waterNumPieces > 0)) {
			return null;
		}
		return "ADD EXTRA CHECKS FOR CONDITIONS!!!!!";
	}
}
