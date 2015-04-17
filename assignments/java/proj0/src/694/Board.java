public class Board {
	private int N = 8;
	private Piece[][] pieceGrid = new Piece[N][N];
	private String[][][] imageNames = new String[2][2][3];
	private boolean fireTurn = true;
	private boolean waterTurn = false;
	private boolean selected = false;
	private boolean moved = false;
	private Piece selectedPiece = null;
	private int selectedXpos;
	private int selectedYpos;
	private int firePiecesRemoved = 0;
	private int waterPiecesRemoved = 0;
	private boolean emptyBoard;
	private boolean endgame = false;


	public static void main(String[] args) {
			Board x = new Board(false);
			x.gui();
	}

	public Board(boolean shouldBeEmpty) {
		emptyBoard = shouldBeEmpty;
		if (!emptyBoard) {
			for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	            	if ( (Math.abs(i%2) == 0) && ( (j == 0) || (j == 6) || (j == 2)) ) { //initializes Pieces
						if (j == 0) { 
							place(new Piece(true, this, i, j, "pawn"), i, j);
						} else if (j == 6) {
							place( new Piece(false, this, i, j, "shield"), i, j);
						} else if (j == 2){
							place(new Piece(true, this, i, j, "bomb"), i, j);
						}
					}	
					else if ( (Math.abs(i%2) == 1) && ( (j == 1) || (j == 5) || (j == 7) ) ) {
						if (j == 1) {
							place(new Piece(true, this, i, j, "shield"), i ,j);
						} else if (j == 5) {
							place(new Piece(false, this, i, j, "bomb"), i, j);
						} else if (j == 7) {
							place(new Piece(false, this, i, j, "pawn"), i, j);
						}
					}	
	         	}
	        }
		}
	}

	private void gui() {
		int N = 8;
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		drawBoard(N, false, emptyBoard);
		instantiateStringNames();
		if (!emptyBoard) {
			while (!endgame) { //CHANGE 2/12
				drawBoard(N,true, true);
				StdDrawPlus.show(100);
				if (StdDrawPlus.mousePressed()) {
                	double l = StdDrawPlus.mouseX();
                	double h = StdDrawPlus.mouseY();
                	if ( canSelect((int) l, (int) h) ) { 
                		select((int) l, (int) h);
                	}	
                }
                if (StdDrawPlus.isSpacePressed()) {
                	if (canEndTurn()) {
                		endTurn();
                	} 
                }	 
			}
		}	
	 }


	private void instantiateStringNames() {
		imageNames[0][0][0] = "img/bomb-fire.png";
		imageNames[0][0][1] = "img/shield-fire.png";
		imageNames[0][0][2] = "img/pawn-fire.png";
		imageNames[0][1][0] = "img/bomb-fire-crowned.png";
		imageNames[0][1][1] = "img/shield-fire-crowned.png";
		imageNames[0][1][2] = "img/pawn-fire-crowned.png";

		imageNames[1][0][0] = "img/bomb-water.png";
		imageNames[1][0][1] = "img/shield-water.png";
		imageNames[1][0][2] = "img/pawn-water.png";
		imageNames[1][1][0] = "img/bomb-water-crowned.png";
		imageNames[1][1][1] = "img/shield-water-crowned.png";
		imageNames[1][1][2] = "img/pawn-water-crowned.png";
	}



	private void drawBoard(int N, boolean init, boolean empty) {

		for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.MAGENTA);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.ORANGE);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);  
              
                if ( (empty) && (init) ) {
                	if (pieceGrid[x][y] != null) {
                		int crownedOrNah = 0;
	                	Piece tempPiece = pieceAt(x,y);
	                	if ( tempPiece.isKing() ) {
	                		crownedOrNah = 1;
	                	}
	                	if ((selected) && (selectedPiece == tempPiece)) {
           					StdDrawPlus.filledSquare(selectedXpos + .5, selectedYpos + .5 , .5);
                		}
	                	if ( tempPiece.isBomb() ) {
	                		StdDrawPlus.picture(x + .5, y + .5, imageNames[tempPiece.side()][crownedOrNah][0], 1, 1);
	                	}
	                	else if ( tempPiece.isShield() ) {
	                		StdDrawPlus.picture(x + .5, y + .5, imageNames[tempPiece.side()][crownedOrNah][1], 1, 1);
	                	} else {
	                		StdDrawPlus.picture(x + .5, y + .5, imageNames[tempPiece.side()][crownedOrNah][2], 1, 1);
	                	}
	                }
	            }

            }
        }

	}

	public Piece pieceAt(int x, int y) {
		if (inBounds(x,y) && (pieceGrid[x][y] != null)) {
			return pieceGrid[x][y];
		} else {
			return null;
		}
	}

	public void place(Piece p, int x, int y) { 
		if (pieceGrid[x][y] != null ) {
			remove(x,y);
		}
		if ( (p != null) && inBounds(x,y) ){
				pieceGrid[x][y] = p;
				selectedXpos = x;
				selectedYpos = y;
				if (p.isFire()) {
					firePiecesRemoved += 1;
				} else {
					waterPiecesRemoved += 1;
				}
		} 
	}

	public boolean canEndTurn() {
		if ( moved ) {
			return true;
		} else if ( (selectedPiece != null) && selectedPiece.hasCaptured() ){ //CHANGED 2/12
			return true;
		}
		return false;
	}


	public void endTurn() {
		selectedPiece.doneCapturing();
		selectedPiece = null;
		moved = false;
		selected = false;
		selectedXpos = 0;
		selectedYpos = 0;
		if (fireTurn) {
			fireTurn = false;
			waterTurn = true;
		}
		else {
			fireTurn = true;
			waterTurn = false;
		} 
		if (winner() != null) { //CHANGED 2/12
			endgame = true;
		} 

	}

	private boolean inBounds(int x,int y) {
		if ((0 <= x) && (x <= 7) && (0 <= y) && (y <= 7) ) {
			return true;
		}
		return false;
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf) {  
		if (pieceGrid[xf][yf] == null) {
			System.out.println("Trying to move from (" + xi +" " + yi + ") to (" + xf + " " + yf + ")");
			if ( (fireTurn && selectedPiece.isFire() ) || (selectedPiece.isKing() && waterTurn ) ){
				if ( (((xi + 1) == xf) && ((yi + 1) == yf)) || (((xi - 1) == xf) && ((yi + 1) == yf)) )  {
					return true;
				} 
			} 
			if ( (waterTurn && !(selectedPiece.isFire()) )|| (selectedPiece.isKing() && fireTurn ) ) { 
				if ( ((xi - 1) == xf) && ((yi - 1) == yf) || ((xi + 1) == xf) && ((yi - 1) == yf) ) {
					return true;
				}
			}
			if (validCapture(xi,yi,xf,yf)) {
				return true;
			}
		}
	return false;
	}

	private boolean validCapture(int xi, int yi, int xf, int yf) {
		int middle_pieceX = (xi + xf) >>> 1;
		int middle_pieceY = (yi + yf) >>> 1;
		if (pieceGrid[xf][yf] == null) {
			if ( (fireTurn && selectedPiece.isFire() ) || (selectedPiece.isKing() && waterTurn ) ){
				if ( ((xi + 2) == xf) && ((yi + 2) == yf) || ((xi - 2) == xf) && ((yi + 2) == yf) ) {
						if ( inBounds(middle_pieceX, middle_pieceY) && (pieceGrid[middle_pieceX][middle_pieceY] != null) && ((selectedPiece.isFire()) != (pieceAt(middle_pieceX,middle_pieceY).isFire())) ) {
							return true;
						}

				}
			}
			if ( (waterTurn && !(selectedPiece.isFire()) )|| (selectedPiece.isKing() && fireTurn ) ) { 
				if ( ((xi - 2) == xf) && ((yi - 2) == yf) || ((xi + 2) == xf) && ((yi - 2) == yf) ) {
						if  ( inBounds(middle_pieceX, middle_pieceY) && (pieceGrid[middle_pieceX][middle_pieceY] != null) && ((selectedPiece.isFire()) != (pieceAt(middle_pieceX,middle_pieceY).isFire())) ) {
							return true;
						}
				}
			}
		}
	return false;
	}



	public void select(int x, int y) {
		if ( (selected) && (pieceGrid[x][y] == null) ){
			//this moves to a place 
			selectedPiece.move(x,y);
			moved = true; 


		} else if (pieceGrid[x][y] != null){
			selectedPiece = pieceGrid[x][y];
			selected = true;
			selectedXpos = x;
			selectedYpos = y;
			System.out.println("You have selected");
		}
	}

	public boolean canSelect(int x, int y) {
		if (pieceGrid[x][y] != null) {	//this returns if you can select a PIECE 
			if (!moved) {
				if ( (pieceGrid[x][y].isFire() == fireTurn)  ) { //CHANGED 2/12
					return true;
				}
			}
		}
		if (selected) { //this returns if you can select an empty location; should use for placing and stuff
			if (!moved)  {
				if (validMove(selectedXpos, selectedYpos, x, y) ) { //sees if you could do a regular move
					return true;
				}
			}else if ( (selectedPiece.hasCaptured()) && !(selectedPiece.isBomb()) ){
				if (validCapture(selectedXpos, selectedYpos, x, y)) {
					return true;
				}
			}
		}
		System.out.println("Sorry can't select that.");
		return false;
	}

	public Piece remove(int x, int y) {
		if ((0 <= x) && (x <= 7) && (0 <= y) && (y <= 7)) {
			if (pieceGrid[x][y] != null) {
				Piece pieceRemoved = pieceAt(x,y);
				if (pieceRemoved.isFire()) {
					firePiecesRemoved -= 1;
				} else {
					waterPiecesRemoved -= 1;
				}  
				pieceGrid[x][y] = null;
				return pieceRemoved;
			} else {
				System.out.println("There is no Piece at these coordinates.");
				return null;
			}
		}
		System.out.println("These coordinates are out of bounds.");
		return null;
	}

	public String winner() { //CHANGED 2/12
		if ( (firePiecesRemoved == 0) && (waterPiecesRemoved == 0) ){
			return "No one";
		}
		else if (firePiecesRemoved == 0) {
			return "Water";
		}
		else if (waterPiecesRemoved == 0) {
			return "Fire";
		}
		else {
			return null;
		} 
	}


}


