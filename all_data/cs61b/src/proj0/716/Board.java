public class Board {
	private Piece[][] position; 
	private boolean madeSelection = false;
	private boolean madeMove = false; 
	private boolean madeCapture = false;
	private int currentPlayer = 0; 
	private Piece selectedPiece;
	private int[] storePrevPosition = new int[2]; 


	public Board(boolean shouldBeEmpty) {
		position = new Piece[8][8];	

		if (!shouldBeEmpty) {
			position = new Piece[8][8];	
			initBoard(8);	

		}
	}

	public Piece pieceAt(int x, int y) {

		if (outOfBounds(x, y) || (position[x][y] == null)) {
			return null;
		}
		else {
			return position[x][y];

		}
	}

	public void place(Piece p, int x, int y) {
		if ((p != null) && (!outOfBounds(x, y))) {
			// remove(x, y);
			position[x][y] = p; 
		}
	}
	


	public Piece remove(int x, int y) {
		if (outOfBounds(x, y)) {
			System.out.println("Out of bounds");
			return null;
		}
		else if (position[x][y] == null) {
			System.out.println("No piece at this position");
			return null;
		}
		else {
			Piece p = position[x][y];
			position[x][y] = null;
			return p; 
		}
	}


	public boolean canSelect(int x, int y) { 
		Piece currentPiece = pieceAt(x, y); 
			if (currentPiece != null) {
				if (currentPiece.side() == currentPlayer) {
					if (!madeSelection || !madeMove) {
						return true;
					}
				}
			}
			else {
				if (madeSelection) {
					if ((!madeMove || selectedPiece.hasCaptured()) && validMove(storePrevPosition[0], storePrevPosition [1], x, y)) { 
						return true; 
					}
				}
			}
		return false;
}



	public void select(int x, int y) {

			if (pieceAt(x, y) != null) {
				selectedPiece = pieceAt(x, y); 
				madeSelection = true;
			}
			else {
				selectedPiece.move(x, y);
				madeMove = true;
			}
		storePrevPosition[0] = x;
		storePrevPosition[1]= y;
		
	}

	public boolean canEndTurn() {
		if (madeMove || madeCapture) {
			return true;
		}
		return false;
	}


	public void endTurn() { 
		if (selectedPiece != null) {
			selectedPiece.doneCapturing();
		}
		selectedPiece = null; 
		madeMove = false;
		madeSelection = false;
		madeCapture = false;		

		if (currentPlayer == 1) {
			currentPlayer = 0;
		}
		else {
			currentPlayer = 1;
		}
	}


	public String winner() { 
		int numFire = 0;
		int numWater = 0;

		Piece p;
		String winStr = null;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				p = pieceAt(i, j);
				if (p != null) {
					if (p.isFire()) {
						numFire++;

					}
					else {
						numWater++;
					}
				}	
			}
		}
			if ((numWater == 0) && (numFire > 0)) {
				winStr = "Fire";
			}
			else if ((numFire == 0) && (numWater > 0)) {
				winStr = "Water";
			}
			else if ((numFire == 0) && (numWater == 0)) {
				winStr = "No one";
			}
		return winStr;
	}


	private boolean validMove(int xi, int yi, int xf, int yf) { 
		Piece p;
		Piece midPiece; 
		if (outOfBounds(xi, yi) || outOfBounds(xf, yf)) {
			return false;
		}
		p = pieceAt(xi, yi);
		if (p == null) {
			return false;
		}
		if ((Math.abs(xi - xf) == 1) && (Math.abs(yi - yf) == 1)) { 
			if (selectedPiece.hasCaptured()) {
				return false;
			}
			if (p.isKing()) {
				return true;
			}			 
			else if (p.isFire()) {
				if (yi < yf) {
					return true;
				}
			}
			else if (!p.isFire()) {
				if (yf < yi) {
					return true;
				}			
			}
		}		
		else if ((Math.abs(xi - xf) == 2) && (Math.abs(yi - yf) == 2)) { 
			midPiece = pieceAt((xi + xf)/2, (yi + yf)/2);
			if (midPiece == null){
				return false;
			}
			if (p.side() != midPiece.side()) { 
				if (p.isKing()){
					return true;
				}
				else if (p.isFire()) {
					if (yi < yf) {
						return true;
					}
				}
				else if (!p.isFire()) {
					if (yf < yi) {
						return true;
					}
				}
			}
		}
		return false;
	}
	


	private boolean outOfBounds(int x, int y) {
		if ((x >= 8) || (y >= 8) || (x < 0) || (y < 0)) {
			return true;
		}
		else{
			return false;
		}
	}

	private void drawBoard(int N) { 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++ ){
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
	}


	private void initBoard(int N) {
		position = new Piece[N][N]; 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) {
					if (j == 0){
						position[i][j] = new Piece(true, this, i, j, "pawn"); 
					} 
					else if (j == 1){
						position[i][j] = new Piece(true, this, i, j, "shield");
					}
					else if (j == 2) {
						position[i][j] = new Piece(true, this, i, j, "bomb");
					}
					else if (j == 5) {
						position[i][j] = new Piece(false, this, i, j, "bomb");
					}
					else if (j == 6) {
						position[i][j] = new Piece(false, this, i, j, "shield");
					}
					else if (j == 7) {
						position[i][j] = new Piece(false, this, i, j, "pawn");
					
					}
				}				
			}

		}
	}


	private void redrawBoard(int N) {
		drawBoard(N);
		Piece p;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				p = pieceAt(i, j);
				if (p != null) {
					if (p.isFire()) {
						if (p.isShield()) {
							if (p.isKing()) {
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
							}
							else { 
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
							}
						}
						else if (p.isBomb()) {
							if (p.isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);	
							}		
						}
						else {
							if (p.isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1); 
							}
						}
					}
					else {
						if (p.isShield()){
							if (p.isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
							}

						}
						else if (p.isBomb()) {
							if (p.isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
							}

						}
						else {
							if (p.isKing()) {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
							}
							else {
								StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
							}

						}
					}
				}
			}
		}
	}


	 public static void main(String args[]) {
	 	
	 	int N = 8;
	 	StdDrawPlus.setScale(0, 8);
		Board createBoard = new Board(false); 

		
		while (true) {
			createBoard.redrawBoard(N);
			Piece p; 

			if (StdDrawPlus.mousePressed()) {
				System.out.println("turn: " + createBoard.currentPlayer);
				
				double xp = StdDrawPlus.mouseX();
				double yp = StdDrawPlus.mouseY();
				int x = (int) xp;
				int y = (int) yp;
				if (createBoard.canSelect(x, y)) {
					createBoard.select(x, y);
				}
				


			}
			if (StdDrawPlus.isSpacePressed()) {
				if (createBoard.canEndTurn()) {
					System.out.println("player " + createBoard.currentPlayer + " can end turn");
					createBoard.endTurn();
				}
			}

			StdDrawPlus.show(100);
			String winner  = createBoard.winner(); 
   
       		if (("No one".equalsIgnoreCase(winner  )) || 
				("Fire".equalsIgnoreCase(winner  )) || 
				("Water".equalsIgnoreCase(winner  ))) {
			    
			    createBoard.redrawBoard(N);
				StdDrawPlus.show(100);
			    System.out.println("winner: " + winner );
			    return;
		    }

		}

	 }


}

