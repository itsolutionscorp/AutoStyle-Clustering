public class Board {


	/* Private Instance Variables of the Board Class. */
	private boolean shouldBeEmpty;
	private Piece[][] pieces;
	private boolean moved;
	private boolean selected;
	private Piece selectedPiece;
	private boolean gameStarted;
	private int turn = 0;
	private boolean[][] picPieces = new boolean[8][8];


	/* Main Method */
	public static void main(String[] args) {
		int length = 8;
		StdDrawPlus.setXscale(0, length);
		StdDrawPlus.setYscale(0, length);
		Board b = new Board(false);
		b.startGame();
	}

	/* Board Constructor */
	public Board(boolean shouldBeEmpty) {
		this.shouldBeEmpty = shouldBeEmpty;
		moved = false;
		selected = false;
		selectedPiece = null;
		gameStarted = false;
		if (this.shouldBeEmpty){
			//Empty board
			pieces = new Piece[][]{
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null, null }
			};
		}
		else {
			//Default Configuration of Board
			pieces = new Piece[][]{
				{ new Piece(true, this, 0, 0, "pawn"), null, new Piece(true, this, 0, 2, "bomb"), null, null, null,  new Piece(false, this, 0, 6, "shield"), null },
				{ null, new Piece(true, this, 1, 1, "shield"), null, null, null, new Piece(false, this, 1, 5, "bomb"), null, new Piece(false, this, 1, 7, "pawn") },
				{ new Piece(true, this, 2, 0, "pawn"), null, new Piece(true, this, 2, 2, "bomb"), null, null, null,  new Piece(false, this, 2, 6, "shield"), null },
				{ null, new Piece(true, this, 3, 1, "shield"), null, null, null, new Piece(false, this, 3, 5, "bomb"), null, new Piece(false, this, 3, 7, "pawn") },
				{ new Piece(true, this, 4, 0, "pawn"), null, new Piece(true, this, 4, 2, "bomb"), null, null, null,  new Piece(false, this, 4, 6, "shield"), null },
				{ null, new Piece(true, this, 5, 1, "shield"), null, null, null, new Piece(false, this, 5, 5, "bomb"), null, new Piece(false, this, 5, 7, "pawn") },
				{ new Piece(true, this, 6, 0, "pawn"), null, new Piece(true, this, 6, 2, "bomb"), null, null, null,  new Piece(false, this, 6, 6, "shield"), null },
				{ null, new Piece(true, this, 7, 1, "shield"), null, null, null, new Piece(false, this, 7, 5, "bomb"), null, new Piece(false, this, 7, 7, "pawn") }
				
			};
		}

	}

	/*Returns the piece at a certain x and y coordinate on the
	 *board. It better be on the board */
	public Piece pieceAt(int x, int y) {
		try{
			return pieces[x][y];
		} catch (java.lang.ArrayIndexOutOfBoundsException e){
			return null;
		}

	}

	/*The most important method in the file. Decides whether a piece can
	 *be selected or not. Soooo many boolean checks. */
	public boolean canSelect(int x, int y){
		Piece piece = pieceAt(x,y);
		if (piece != null){

			//Checks whether the piece chosen corresponds to the correct side, fire or water.
			if (turn != piece.side()) return false;

			if (selectedPiece != null && selectedPiece.hasCaptured()) return false;
			if (selectedPiece != null && !moved) return true;
			if (selectedPiece != null && moved) return false;
			if (selectedPiece == null && selected) return false;
			

			if (moved == false || selected == false) return true;
		}
		else {
			//If a bomb has exploded, you can't move again.
			if (selected && moved && selectedPiece == null) return false;

			//If you've already moved without capturing, don't try moving again.
			if (selected && selectedPiece != null && !selectedPiece.hasCaptured() && moved) return false;

			//If you have selected a piece and you're stalling for some reason.
			if (selected && !moved && selectedPiece != null
				&& validMove(getX(selectedPiece), getY(selectedPiece), x, y)) return true;

			//If a piece has already captured and it's possible to double capture.
			if (selected && selectedPiece != null && selectedPiece.hasCaptured() && moved
				&& validMove(getX(selectedPiece), getY(selectedPiece), x, y) 
				&& Math.abs(x - getX(selectedPiece)) > 1) return true;
		}

		return false;

	}

	/* Select Method */
	public void select(int x, int y){
		if (selectedPiece == null){
			selectedPiece = pieceAt(x,y);
			selected = true; //boolean to see if a piece is selected or not
		}
		else {
			if (pieceAt(x,y) != null){
				selectedPiece = pieceAt(x,y);
				selected = true;
			}
			else {// empty square
				selectedPiece.move(x,y);
				moved = true;
				changePic(x,y);
				if (selectedPiece.isBomb() && selectedPiece.hasCaptured()){
					selectedPiece = null; //Bomb go away
			}
			changePic(x,y);
			}
		
		}
	}

	/* Puts piece on the board. Removes if that piece is already
	 * at another spot, and puts it at x and y. */
	public void place(Piece p, int x, int y){
		while (x <= 7 && y <= 7 && x >= 0 && y >= 0 && p != null){
			if (pieceAt(getX(p), getY(p)) != null) pieces[getX(p)][getY(p)] = null;
			if (pieceAt(x, y) != null) pieces[x][y] = null;
			pieces[x][y] = p;
			break;
		}
	}

	/*Get out of here, piece */
	public Piece remove(int x, int y){
		if (x > 7 || y > 7){
			System.out.println("Index out of bounds of checkerboard");
			return null;
		}
		if (pieceAt(x,y) == null){
			System.out.println("There is no piece at this position");
			return null;
		}
		Piece temp = pieces[x][y];
		pieces[x][y] = null;
		return temp;
	}

	/*If a player can finally end their turn */
	public boolean canEndTurn(){
		if (moved) return true;
		if (selectedPiece != null && selectedPiece.hasCaptured()) return true;
		return false;
	}

	/*Now the player ends their turn, and it's the next turn's player. 
	 *While loop starts over. */
	public void endTurn(){
		moved = false;
		selected = false;
		if (selectedPiece != null) selectedPiece.doneCapturing();
		selectedPiece = null;
		turn = switchTurn();
	}

	/*This is why we play. To win */
	public String winner() {
		int fireCounter = 0;
		int waterCounter = 0;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (this.pieceAt(i,j) != null){
					if (this.pieceAt(i, j).isFire()) fireCounter += 1;
					else waterCounter += 1; 
				}
			}
		}
		if (fireCounter + waterCounter == 0) return "No One";
		if (fireCounter == 0) return "Water";
		if (waterCounter == 0) return "Fire";
		return null;
	}

	/*BEGINNING OF PRIVATE METHODS. YOU SHALL NOT PASS */


	/*Does exactly what the method name says. All the GUI goes in here. */
	private void startGame(){
		this.drawBoard(8);
		while(true){
			if (this.winner() != null) break;
			this.gameStarted = true;
			if (StdDrawPlus.mousePressed()){
				StdDrawPlus.show(15);
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (canSelect(x,y)) {
					select(x,y);
					this.drawBoard(8);
				}
			}
			if (StdDrawPlus.isSpacePressed()){
				StdDrawPlus.show(15);
				if (canEndTurn()) {
					endTurn();
					this.drawBoard(8);
				}
			}
			
			}
		}			


	/*When a piece moves, this method changes the image in the GUI */
	private void changePic(int x, int y){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (pieceAt(j,i) != null) picPieces[j][i] = true;
				else picPieces[j][i] = false;
			}
		}
	}


	/* A method to get the x coordinate of piece P */
	private int getX(Piece p){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (pieceAt(j,i) == p) {
					return j;
			}
		}
	}
		return -1;
	}

	/* A method to get the y coordinate of piece P */
	private int getY(Piece p){
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (pieceAt(j,i) == p) return i;
			}
		}
		return -1;
	}


	/*How do you switch turns? This is how. */
	private int switchTurn(){
		if (turn == 0) return 1;
		return 0;
	}

	/*Checks if you can move to a clicked spot. Lots of conditions to check */
	private boolean validMove(int xi, int yi, int xf, int yf){
		Piece piece = pieceAt(xi, yi);
		if (xf > 7 || yf > 7 || xf < 0 || yf < 0) return false;
		if (pieceAt(xf,yf) != null) return false;
		if (piece.isKing()) return kingValidMove(piece, xf, yf);
		if (piece.isFire()) return fireValidMove(piece, xf, yf);
		return waterValidMove(piece, xf, yf);
	}

	/*Checks king's valid move */
	private boolean kingValidMove(Piece p, int x, int y){
		//Move without capturing (wimp)
		if (Math.abs(getX(p) - x) == 1 && Math.abs(y - getY(p)) == 1) return true;

		//Bottom left capture
		if (x < getX(p) && y < getY(p)){
			Piece left = pieceAt(getX(p) -1, getY(p) - 1);
			if (left == null) return false;
			if (x == getX(left) - 1 && y == getY(left) - 1 && left.isFire() != p.isFire()) return true;
			return false;
		}

		//Top left capture
		if (x < getX(p) && y > getY(p)){
			Piece left = pieceAt(getX(p) -1, getY(p) + 1);
			if (left == null) return false;
			if (x == getX(left) - 1 && y == getY(left) + 1 && left.isFire() != p.isFire()) return true;
			return false;
		}

		//Bottom right capture
		if (x > getX(p) && y < getY(p)){
			Piece right = pieceAt(getX(p) +1, getY(p) - 1);
			if (right == null) return false;
			if (x == getX(right) + 1 && y == getY(right) - 1 && right.isFire() != p.isFire()) return true;
			return false;
		}

		//Top right capture
		if (x > getX(p) && y > getY(p)){
			Piece right = pieceAt(getX(p) +1, getY(p) + 1);
			if (right == null) return false;
			if (x == getX(right) + 1 && y == getY(right) + 1 && right.isFire() != p.isFire()) return true;
			return false;
		}
		return false;
	}

	/*Checks fire moves. BURN */
	private boolean fireValidMove(Piece p, int x, int y){
		if (Math.abs(getX(p) - x) == 1 && y - getY(p) == 1) return true;
		if (x < getX(p)){
			Piece left = pieceAt(getX(p) -1, getY(p) +1);
			if (left == null) return false;
			if (x == getX(left) - 1 && y == getY(left) + 1 && !left.isFire()) return true;
			return false;
		}
		if (x > getX(p)){
			Piece right = pieceAt(getX(p) + 1, getY(p) +1);
			if (right == null) return false;
			if (x == getX(right) + 1 && y == getY(right) + 1 && !right.isFire()) return true;
			return false;
		}
		return false;
	}

	/*Checks water moves. WHOOSH */
	private boolean waterValidMove(Piece p, int x, int y){
		if (Math.abs(getX(p) - x) == 1 && getY(p) - y == 1) return true;
		if (x < getX(p)){
			Piece left = pieceAt(getX(p) -1, getY(p) -1);
			if (left == null) return false;
			if (x == getX(left) - 1 && y == getY(left) - 1 && left.isFire()) return true;
			return false;
		}
		if (x > getX(p)){
			Piece right = pieceAt(getX(p) + 1, getY(p) -1);
			if (right == null) return false;
			if (x == getX(right) + 1 && y == getY(right) - 1 && right.isFire()) return true;
			return false;
		}
		return false;

	}

	/*The graphical beauty of the board is unleashed here. */
	private void drawBoard(int length){
		for (int i = 0; i < length; i++){
			for (int j = 0; j < length; j++){
				if (this.shouldBeEmpty) pieces[i][j] = null;
				if (i % 2 == 0){
					if (j % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					else StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				else {
					if (j % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.RED);
					else StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				if (selectedPiece != null){
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(getX(selectedPiece) + .5, 
											getY(selectedPiece) + .5, .5);
				}
			}
		}
		if (!this.shouldBeEmpty && !this.gameStarted) initialPieces(); //Initial board state w/ no pieces
		if (!this.shouldBeEmpty && this.gameStarted) {//board state when game has started
			for (int i = 0; i < 8; i++) {
            	for (int j = 0; j < 8; j++) {
            		if (picPieces[j][i] == true && pieceAt(j, i) != null){
	            		String img = "img/";
		            	if (pieceAt(j,i).isShield()){
		            		img += "shield";
		            	}else if (pieceAt(j,i).isBomb()) {
		            		img += "bomb";
		            	} else {
		            		img += "pawn";
		            	}
		            	if (pieceAt(j,i).isKing()){
		            		if (pieceAt(j,i).isFire()) img += "-fire-crowned.png";
		            		else img += "-water-crowned.png";
		            	}
		            	else {
		            		if (pieceAt(j,i).isFire()) img += "-fire.png";
		            		else img += "-water.png";
		            	}

		            	/*Once the correct images are found in img/ directory, they're put
		            	 *on the board in this call */
            		 	StdDrawPlus.picture(j + .5, i + .5,
								img, 1, 1);
            		 }
				}
			}
		}
	}

	private void initialPieces() {
		insertFire();
		insertWater();
	}

	/* Put initial fire pieces on the board */
	private void insertFire() {
		for (int i = 0; i < 3; i++){
			for (int j = 0; j < 8; j+=2){
				if (i == 0) {
					StdDrawPlus.picture(j + .5, i + .5,
								"img/pawn-fire.png", 1, 1);
					picPieces[j][i] = true;
				}
				if (i == 1) {
					StdDrawPlus.picture(j + 1.5, i + .5,
								"img/shield-fire.png", 1, 1);
					picPieces[j+1][i] = true;
				}
				if (i == 2) {
					StdDrawPlus.picture(j + .5, i + .5,
								"img/bomb-fire.png", 1, 1);
					picPieces[j][i] = true;
				}
			}
		}
	}

	/*Last, and certainly least, all the initial water pieces are on the board. */
	private void insertWater() {
		for (int i = 5; i < 8; i++){
			for (int j = 0; j < 8; j+=2){
				if (i == 5) {
					StdDrawPlus.picture(j + 1.5, i + .5,
										"img/bomb-water.png", 1, 1);
					picPieces[j+1][i] = true;
				}
				if (i == 6) {
					StdDrawPlus.picture(j + .5, i + .5,
										"img/shield-water.png", 1, 1);
					picPieces[j][i] = true;
				}
				if (i == 7) {
					StdDrawPlus.picture(j + 1.5, i + .5,
										"img/pawn-water.png", 1, 1);
					picPieces[j+1][i] = true;
				}

			}
		}
	}
}