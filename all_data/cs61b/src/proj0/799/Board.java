public class Board {

	private Piece[][] pieces;
	private Piece[][] piecesCopy;

	private boolean fireTurn;
	private boolean hasMoved;
	private boolean hasCaptured;
	
	private boolean saveTurn;
	private int saveX;
	private int saveY;
	private Piece savePiece;


	private Piece pieceSelected;
	private int xSelected;
	private int ySelected;



	public Board(boolean shouldBeEmpty ){

		pieces = new Piece[8][8];
		piecesCopy = new Piece[8][8];

		fireTurn = true;
		hasMoved = false;
		hasCaptured = false;

		saveTurn = fireTurn;

		xSelected = -1;
		ySelected = -1;


		if (!shouldBeEmpty){

			//create fire pieces
			putPieces(true, 0, 0, "pawn");
			putPieces(true, 1, 1, "shield");
			putPieces(true, 0, 2, "bomb");

			//create water pieces
			putPieces(false, 1, 5, "bomb");
			putPieces(false, 0, 6, "shield");
			putPieces(false, 1, 7, "pawn");

		}

		saveState();

	}

	public static void main( String args[] ){

		StdDrawPlus.setXscale(0,8);
		StdDrawPlus.setYscale(0,8);
		Board b = new Board(false);
		String win = null;
		boolean cont = true; 

		while ((win == null) || cont) {
			
			b.drawBoard(false, win);

			cont = true;

			//undo when u is pressed
			if(StdDrawPlus.isKeyPressed(85)) b.undo();

			if (StdDrawPlus.mousePressed()){
				int mouseX = (int) StdDrawPlus.mouseX();
				int mouseY = (int) StdDrawPlus.mouseY();
				if(b.canSelect(mouseX, mouseY)) b.select(mouseX, mouseY);
			}

			if(StdDrawPlus.isSpacePressed() && b.canEndTurn()){
				b.endTurn();
				cont = false;
			}

			if(StdDrawPlus.isNPressed()){
				b = new Board(false);
				win = null;
			}

			win = b.winner();

			if(StdDrawPlus.isKeyPressed(27)) b.reset();

			StdDrawPlus.show(10);
			
		}

		b.drawBoard(true, win);
		StdDrawPlus.show(100);

		System.out.println("The winner is: " + win);
	}

	private void drawBoard(boolean gameOver, String win){

		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){

				if ((i == xSelected) && (j == ySelected)) StdDrawPlus.setPenColor(StdDrawPlus.PINK);
				else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
				else StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);

				//color differently if game is over

				if (gameOver) {
					if (win.equals("Fire")) StdDrawPlus.setPenColor(StdDrawPlus.RED);
					else if (win.equals("Water")) StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
					else StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}

				StdDrawPlus.filledSquare(i + .5, j + .5, .5);

				if (pieceAt(i,j) != null) {

					Piece p = pieceAt(i,j);
					String imgPath = "img/";

					if (p.isBomb()) imgPath = imgPath.concat("bomb");
					else if (p.isShield()) imgPath = imgPath.concat("shield");
					else imgPath = imgPath.concat("pawn");

					if (p.isFire()) imgPath = imgPath.concat("-fire");
					else imgPath = imgPath.concat("-water");

					if (p.isKing()) imgPath = imgPath.concat("-crowned");

					imgPath = imgPath.concat(".png");

					StdDrawPlus.picture(i + .5, j + .5, imgPath, 1, 1);
				}
			}
		}

	}

	private void putPieces(boolean isFire, int x, int y, String type){

		for(int i = 0; i<4; i++){
			Piece p = new Piece(isFire, this, x, y, type);
			place(p, x, y);
			x += 2;
		}
	}

	private boolean onBoard(int x, int y){

		return ((x >= 0) && (y >= 0) && (x <= 7) && (y <= 7));
	}

	public Piece pieceAt(int x, int y){

		if (!onBoard(x, y)) return null;

		return pieces[x][y];
	}

	public boolean canSelect(int x, int y){

		Piece p = pieceAt(x, y);

		if (p != null) {
				if (((pieceSelected == null) && !hasCaptured) || !(hasMoved)) return (p.isFire() == fireTurn);			
		} else if (pieceSelected != null){
				if (!hasMoved && !hasCaptured) return (validMove(pieceSelected, xSelected, ySelected, x, y) || validCapture(pieceSelected, xSelected, ySelected, x, y));
				else if (hasCaptured) return validCapture(pieceSelected, xSelected, ySelected, x, y);
		}
		return false;
	}

	private boolean validMove(Piece p, int xi, int yi, int xf, int yf){

		if (!onBoard(xf, yf)) return false;
		else if (pieceAt(xf, yf) != null) return false; 
		else if (p.isKing()) return ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1));
		else if (p.isFire()) return ((yf - yi == 1) && (Math.abs(xf - xi) == 1));
		else return ((yf - yi == -1) && (Math.abs(xf - xi) == 1));
	}

	private boolean validCapture(Piece p, int xi, int yi, int xf, int yf){

		int aveX = (xf + xi)/2;
		int aveY = (yf + yi)/2;

		Piece capt = pieceAt(aveX, aveY);

		if (!onBoard(xf, yf)) return false;
		else if (pieceAt(xf, yf) != null) return false;
		else if (capt == null) return false;
		else if (p.isFire() == capt.isFire()) return false;
		else if (p.isKing()) return ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2));
		else if (p.isFire()) return ((yf - yi == 2) && (Math.abs(xf - xi) == 2));
		else return ((yf - yi == -2) && (Math.abs(xf - xi) == 2));
	}


	public void select(int x, int y ){

		saveState();

		xSelected = x;
		ySelected = y;

		if ((pieceAt(x,y) == null) && (pieceSelected != null)) {
			pieceSelected.move(x, y);
			hasMoved = true;
			hasCaptured = pieceSelected.hasCaptured();
			if (pieceSelected.isBomb() && hasCaptured){
				pieceSelected = null;
			}
		}
		else pieceSelected = pieceAt(x, y);

	}

	public void place(Piece p, int x, int y){

		if (p==null) return;
		if (!onBoard(x, y)) return;

		pieces[x][y] = p;
	}

	public Piece remove(int x, int y){

		if (!onBoard(x, y)) {

			System.out.println("Index out of bounds");
			return null;
		}

		if (pieceAt(x,y) == null) {

			System.out.println("There is no piece there");
			return null;
		}

		Piece p = pieces[x][y];
		pieces[x][y] = null;
		return p;
	}

	public boolean canEndTurn(){

		return hasMoved;
	}
	
	public void endTurn(){

		if (pieceSelected != null) pieceSelected.doneCapturing();
		pieceSelected = null;
		xSelected = -1;
		ySelected = -1;
		hasMoved = false;
		hasCaptured = false;


		fireTurn = !fireTurn;

		//for undo method
		saveState();

	}

	private void undo(){

		if (savePiece != null) savePiece.move(saveX, saveY);
		pieces = piecesCopy;
		fireTurn = saveTurn;
		pieceSelected = savePiece;
		xSelected = saveX;
		ySelected = saveY;
		hasMoved = false;
		hasCaptured = false;

		saveState();

	}


	private void saveState(){

		piecesCopy = new Piece[8][8];
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				piecesCopy[i][j] = pieces[i][j];
			}
		}

		saveTurn = fireTurn;
		saveX = xSelected;
		saveY = ySelected;
		savePiece = pieceSelected;

	}

	private void reset(){

		if(!hasMoved){
			xSelected = -1;
			ySelected = -1;
			pieceSelected = null;
		}
	}

	public String winner(){

		String winner = null;

		boolean fireFound = false;
		boolean waterFound = false;
		boolean canMove = false;

		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				Piece p = pieceAt(i, j);
				if (p != null) {
					if (p.isFire()) fireFound = true;
					else waterFound = true;

					if (p.isFire() == fireTurn) {
						for(int k = -1; k < 2; k++) {
							if(validMove(p, i, j, i+k, j+k) || validMove(p, i, j, i+k, j-k) 
								|| validCapture(p, i, j, i+2*k, j+2*k) || validCapture(p, i, j, i+2*k, j-2*k) ) canMove = true;
						}
					}					
				}
			}
		}

		if ((!fireFound && !waterFound) || (!canMove)) winner = "No one";
		if (fireFound && !waterFound) winner = "Fire";
		if (!fireFound && waterFound) winner = "Water";

		return winner;
	}

	

}