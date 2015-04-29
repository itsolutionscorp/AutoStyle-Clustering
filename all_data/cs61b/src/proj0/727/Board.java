public class Board{

	private Piece [][] pieces;
	private static int size = 8;
	private boolean turn = true;
	private boolean hasMoved = false;
	private boolean hasSelected = false;
	private int finalX = -1;
	private int finalY = -1;
	private Piece initialPiece;
	private int initialX = -1;
	private int initialY = -1;

	public static void main (String args[]){


		Board b = new Board(false);
		StdDrawPlus.setXscale(0, size);
		StdDrawPlus.setYscale(0, size);
		b.drawBoard(size);
		System.out.println();
		System.out.println("It is " + (b.turn ? "Fire's turn" : "Water's turn. Press SPACE to end turn."));

		while(b.winner() == null){

			if(StdDrawPlus.mousePressed()){
				b.finalX = (int)StdDrawPlus.mouseX();
				b.finalY = (int)StdDrawPlus.mouseY();

				if (b.canSelect(b.finalX, b.finalY)){
					//b.highlightSquare(b.finalX, b.finalY);
					b.select(b.finalX, b.finalY);
				}

			}

			if(StdDrawPlus.isSpacePressed()){

				if(b.canEndTurn()){
					b.endTurn();
					System.out.println();
					System.out.println("It is " + (b.turn ? "Fire's turn" : "Water's turn"));
				}
			}

			StdDrawPlus.show(25);

		}

		System.out.println();
		System.out.println(b.winner() + " is the winner!");
		System.out.println();

		//while (b.winner().equals(null)){
			//select a valid piece based on whose turn it is
			//draw the highlight
			//repeat these until a move is made
			//move the piece in the array
			//draw board
			//check if any valid moves are left
			//repeat if necessary
			//end turn

	}

	public Board(boolean shouldBeEmpty){

		this.pieces = new Piece[size][size];

		if (shouldBeEmpty){
			return;
		}

		for (int j = 0; j < size; j += 2){
			pieces[j][0] = new Piece(true, this, j, 0, "pawn");
			pieces[j + 1][size - 1] = new Piece(false, this, j + 1, size - 1, "pawn");
			pieces[j + 1][1] = new Piece(true, this, j + 1, 1, "shield");
			pieces[j][size - 2] = new Piece(false, this, j, size - 2, "shield");		
			pieces[j][2] = new Piece(true, this, j, 2, "bomb");
			pieces[j + 1][size - 3] = new Piece(false, this, j + 1, size - 3, "bomb");	
		}

	}

	private void drawBoard(int n) {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				drawSquare(i, j);
			}
		}

		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if (pieces[i][j] != null) {
					drawPiece(i, j);
				}
			}
		}

	}

	private void drawSquare(int x, int y){

		if ((x + y) % 2 == 0) {
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		} else {
			StdDrawPlus.setPenColor(StdDrawPlus.RED);                
		}
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);

	}

	private void drawPiece(int x, int y){

		StdDrawPlus.picture(x + .5, y + .5, "img/" + (pieces[x][y].isBomb() ? "bomb" : pieces[x][y].isShield() ? "shield" : "pawn") + "-" + (pieces[x][y].isFire() ? "fire" : "water") + ".png", 1, 1);

	}

	private void highlightSquare(int x, int y){
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);	
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		drawPiece(x, y);	
	}

	public Piece pieceAt(int x, int y){

		if (x >= size || x < 0 || y >= size || y < 0){
			return null;
		}
		return pieces[x][y];

	}


	//fix multiple moves

	private boolean validMove(int xi, int yi, int xf, int yf){

		if (xf < 0 || xf > 7 || yf < 0 || yf > 7){
			return false;
		}
		else if (xf == xi && yf == yi){
			return false;
		}
		else if (pieceAt(xf, yf) == null) {
			if(pieceAt(xi, yi) != null && Math.abs(xf - xi) == 2) {
				if(pieceAt(xi, yi).isKing()){
					if(Math.abs(yf - yi) == 2 && pieceAt((xf + xi) / 2, (yf + yi) / 2) != null && pieceAt((xf + xi) / 2, (yf + yi) / 2).isFire() != pieceAt(xi, yi).isFire()){
						return true;
					}
				}
				else if(yf - yi == (2 * (pieceAt(xi, yi).isFire() ? 1 : -1)) && pieceAt((xf + xi) / 2, (yf + yi) / 2) != null && pieceAt((xf + xi) / 2, (yf + yi) / 2).isFire() != pieceAt(xi, yi).isFire()){
					return true;
				}		
			}
			else if (Math.abs(xf - xi) == 1) {
				if(pieceAt(xi, yi).isKing()){
					if(Math.abs(yf - yi) == 1){
						return true;
					}
				}
				else if(yf - yi == (pieceAt(xi, yi).isFire() ? 1 : -1)){
					return true;
				}
			}
		}
		return false;
	}

	public boolean canSelect(int x, int y){

		if((x + y) % 2 == 1){
			return false;
		}

		if ((pieceAt(x, y) != null) && (turn == pieceAt(x, y).isFire()) && (initialPiece == null || (initialPiece != null && hasMoved == false))) {
			return true;
		}
		else if (pieceAt(x, y) == null){

			if(initialPiece != null && hasMoved == false && validMove(initialX, initialY, x, y)){
				return true;

			}

			else if ((initialPiece != null) && initialPiece.hasCaptured() && Math.abs(initialX - x) == 2 && Math.abs(initialY - y) == 2 && validMove(initialX, initialY, x, y)) {
				return true;
			}

		}
		else{

			return false;

		}
		return false;
		
	}
	
	public void select(int x, int y){

		if (pieceAt(x, y) != null){ //if there is a piece there
			initialX = x;
			initialY = y;
			initialPiece = pieceAt(initialX, initialY);
		}
		else{ //if there is no piece there
			finalX = x;
			finalY = y;
			initialPiece.move(x, y);
			hasMoved = true;
			initialX = finalX;
			initialY = finalY;
			finalX = -1;
			finalY = -1;
		}
	}

	public void place(Piece p, int x, int y){

		if (x >= size || x < 0 || y >= size || y < 0 || p == null){
			return;
		}
		pieces[x][y] = p;
		drawPiece(x, y);
	}

	public Piece remove(int x, int y){

		if (x >= size || x < 0 || y >= size || y < 0){
			System.out.println("This space is out of bounds.");
			return null;
		} else if (pieces[x][y] == null){
			System.out.println("There is no piece at this space.");
			return null;
		}

		drawSquare(x, y);
		Piece removedPiece = pieceAt(x, y);
		pieces[x][y] = null;
		return removedPiece;

	}

//todo
	public boolean canEndTurn(){
		return (hasMoved == true);
	}

	public void endTurn(){
		turn = (turn == false);
		hasMoved = false;
		this.initialPiece.doneCapturing();
		initialPiece = null;
		finalX = -1;
		finalY = -1;
		initialX = -1;
		initialY = -1;
	}
	

	public String winner(){
		int fireCount = 0;
		int waterCount = 0;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {

				if (pieceAt(i, j) != null){
					if (pieceAt(i, j).isFire()){
						fireCount++;
					} else {
						waterCount++;
					}
				}
			}
		} 
		if (fireCount == 0 && waterCount == 0){
			return "No one";
		} else if (fireCount == 0){
			return "Water";
		} else if (waterCount == 0){
			return "Fire";
		}
		return null;
	}

}