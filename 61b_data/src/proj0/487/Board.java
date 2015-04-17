public class Board {
	private Piece[][] pieces = new Piece[8][8];
	private boolean isFireTurn = true;
	private Piece selected;
	private int selectedX = 8;
	private int selectedY = 8;
	private int selectedprevX = 8;
	private int selectedprevY = 8;
	private boolean capturePerformed;
	private boolean pieceMoved;
	private boolean gameOver = false;
	private int firePieces;
	private int waterPieces;
	private boolean explosion;
	public static void main(String[] args) {
		Board x = new Board(false);
		x.doGUI();
			}	


	public Board(boolean shouldBeEmpty) {
			if (!shouldBeEmpty) {
				initPieces(0,0,"pawn",true);
				initPieces(1,1,"shield",true);
				initPieces(2,0, "bomb",true);
				initPieces(5,1, "bomb",false);
				initPieces(6,0,"shield",false);
				initPieces(7,1,"pawn",false);				
			}
		}

	private void doGUI() {
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0,8);
		while (!gameOver) {
				drawBoard();
				if (selected != null) {
					changeSquareColor(selectedX,selectedY);
				}
				drawPieces();
				StdDrawPlus.show(100);
				if (explosion) {
					StdAudio.play("explosion.wav"); //found online
				}
				if (StdDrawPlus.mousePressed()) {
					int x = (int) StdDrawPlus.mouseX();
					int y = (int) StdDrawPlus.mouseY();
					if (canSelect(x,y)) {
						select(x,y);
					}
				}
				if (firePieces == 0 || waterPieces == 0) {

					gameOver = true;
				}
				 if (StdDrawPlus.isSpacePressed()) {
					if (canEndTurn()) {
						endTurn();
					}
				}
 
			}
			drawBoard(); 
			drawPieces();
			if (firePieces == 0 && !(waterPieces == 0)) {
				StdDrawPlus.picture(3.5,3.5,"img/watar.jpg",8,8); //found online
			}
			if (waterPieces == 0 && !(firePieces == 0)) {
				StdDrawPlus.picture(3.5,3.5,"img/fyre.png",8,8); //found online
			}
			StdDrawPlus.show(100);
			System.out.println(winner()); 
		}



	private void drawBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {	
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.PINK);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.MAGENTA);
				}
				StdDrawPlus.filledSquare(i+.5, j+.5, .5);
			}
		}
	}

	private void initPieces(int row, int start, String type, boolean isFire) {
		for (int z = start; z < 8; z= z+2) {
			pieces[row][z] = new Piece(isFire, this, z, row, type);
		}
		firePieces = 12;
		waterPieces = 12;
	}

	private void drawPieces() {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				String s = null;
				Piece p = pieces[i][j];
				if (p != null) {
						if (p.isBomb()) {
							s = "bomb";
						}
						else if (p.isShield()) {
							s = "shield";
						}
						else {
							s = "pawn";
						}
						if (p.isFire()) {
							s = s + "-fire";
						}
						else {
							s = s + "-water";
						}
						if (p.isKing()) {
							s = s + "-crowned";
						}
						StdDrawPlus.picture(j+.5,i+.5, "img/" + s + ".png",1,1);
					}
				}	
		}	
	}
	public Piece pieceAt(int x, int y) {
		if (outOfBounds(x,y)) {
	;		return null;
		}
		return pieces[y][x];
	}

	public void place(Piece p, int x, int y) {
		if (outOfBounds(x,y) || p == null) {
			return;
		}
		if (pieceAt(x,y) != null) {
			changeNumberPieces(pieceAt(x,y).isFire(), -1);
			remove(x,y);
		}

		pieces[y][x] = p;
		changeNumberPieces(p.isFire(),1);
		
	}

	public Piece remove(int x, int y) {
		if (outOfBounds(x,y)) {
			System.out.println("Out of bounds!");
			return null;
		}
		Piece p = pieceAt(x,y);
		if (p == null) {
			System.out.println("No pieces found!");
			return p;
		}
		pieces[y][x] = null;
		changeNumberPieces(p.isFire(),-1);
		return p;
	}
	private void changeNumberPieces(boolean isFire, int change) {

		if (isFire) {
			firePieces = firePieces + change;
		}
		else {
			waterPieces = waterPieces + change;
		}
	}
	public boolean canEndTurn() {
		if (pieceMoved || capturePerformed) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		if (canEndTurn()) {
			if (selected != null) { 
				selected.doneCapturing();
			}
			selected = null;
			capturePerformed = false;
			pieceMoved = false;
			isFireTurn = !isFireTurn;
			selectedX = 8;
			selectedY = 8;
			selectedprevX = 8;
			selectedprevY = 8;
			explosion = false;
		}
	}

	public void select(int x, int y) {
			selectedprevX = selectedX;
			selectedprevY = selectedY;
			selectedX = x;
			selectedY = y;
			selected = pieceAt(x,y);
			if (selected == null && selectedprevX != 8) {
						if (pieceAt(selectedprevX,selectedprevY) != null) {
							pieceAt(selectedprevX,selectedprevY).move(selectedX,selectedY);
							pieceMoved = true;
							if (pieceAt(selectedX,selectedY) != null) {
								selected = pieceAt(selectedX,selectedY);
								if (pieceAt(selectedX,selectedY).hasCaptured()) {
									capturePerformed = true;
								}
							}
							else {
								explosion = true;
							}
						}
					}



		}
	public String winner() {
		if (firePieces + waterPieces == 0) {
			return "No one";
		}
		if (firePieces == 0) {
			return "Water";
		}
		if (waterPieces == 0) {
			return "Fire";
		}
		return null;


	}

	public boolean canSelect(int x, int y) {
		if (explosion) {
			return false;
		}
		if (pieceAt(x,y) == null) {
			if (selected != null && !(pieceMoved) && isValidMove(selectedX,selectedY,x,y) && !capturePerformed) {
				return true;
			}
			if (capturePerformed && selected != null && isValidCaptureMove(selectedX,selectedY,x,y)) {
				return true;
			}
			return false;
		}
		if (pieceAt(x,y).isFire() == isFireTurn && !capturePerformed) {
			if (selected == null){
				return true;
			}
			if (!pieceMoved) {
				return true;
			}
		}

		return false;
	}

	private boolean isValidMove(int xi, int yi, int xf, int yf) {
		Piece p = pieceAt(xi,yi);
		int differenceX = xf - xi;
		int differenceY = yf - yi;

		if (outOfBounds(xf,yf) || pieceAt(xf,yf) != null) {
			return false;
		}
		if (p.isFire()) {
			if (Math.abs(differenceX) == 1 && differenceY == 1) {
				return true;
			}
			if (differenceY == -1 && Math.abs(differenceX) == 1) {
				return p.isKing();
			}
			return isValidCaptureMove(xi,yi,xf,yf);
		}
		else {
			if (Math.abs(differenceX) == 1 && differenceY == -1) {
				return true;
			}
			if (Math.abs(differenceX) == 1 && differenceY == 1) {
				return p.isKing();
			}
			return isValidCaptureMove(xi,yi,xf,yf);
		}
	}

	private boolean isValidCaptureMove(int xi, int yi, int xf, int yf) {
		Piece p = pieces[yi][xi];
		int differenceX = xf - xi;
		int differenceY = yf - yi;
		int middleX = (xf + xi) / 2;
		int middleY = (yf + yi) / 2;

		if (outOfBounds(xf,yf) || pieceAt(xf,yf) != null) {
			return false;
		}
		if (p.isFire()) {
			if (pieceAt(middleX,middleY) != null && !(pieceAt(middleX,middleY).isFire())) {
				if (differenceY == 2 && Math.abs(differenceX) == 2) {
					return true;
				}
				if (differenceY == -2 && Math.abs(differenceX) == 2) {
					return p.isKing();
				}
			}

			return false;
		}
		else {
			if (pieceAt(middleX,middleY) != null && pieceAt(middleX,middleY).isFire()) {
				if (differenceY == -2 && Math.abs(differenceX) == 2) {
					return true;
				}
				if (differenceY == 2 && Math.abs(differenceX) == 2) {
					return p.isKing();
					}
				}

			return false;
		}
	}

	private void changeSquareColor(int x, int y) {
		if (pieces[y][x] != null) {
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x +.5, y+.5, .5);


		}
	}

	private boolean outOfBounds(int x, int y) {
		return x>7 || x<0 || y>7 || y < 0;
	}
}