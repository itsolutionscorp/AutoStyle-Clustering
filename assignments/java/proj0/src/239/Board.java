/** Board .java
 */ 


public class Board {
	private static int size = 8;
	private Piece[][] pieces = new Piece[size][size];
	private boolean doDrawPieces = true;
	private int turn = 0; //0 = fire 1 = water
	private boolean boardDrawn = false;
	private Piece selectedPiece;
	private int lastSelectedPieceX = -1;
	private int lastSelectedPieceY = -1;
	private int selectedPieceX = -1;
	private int selectedPieceY = -1;
	private boolean selectedPieceMoved;
	private boolean canEndTurn = false;
	private boolean firstPiecePlaced = false;
	private boolean canMultiCapture = false;

	public Board(boolean shouldBeEmpty) {
		
		if (shouldBeEmpty) {
			this.doDrawPieces = false;
		}
		this.size = 8;
		if (!shouldBeEmpty) {
			Piece p;
			for (int r = 0; r < size; r++) {
				for (int c = 0; c < size; c++) {
					if (r == 7) {
						if (!isEven(c)) {
							//**Todo draw normal pieces
							p = new Piece(false, this, c, r, "pawn");
							pieces[c][r] = p;
						}	
					}
					if (r == 6) {
						if (isEven(c)) {
							//**Todo draw shield pieces
							p = new Piece(false, this, c, r, "shield");
							pieces[c][r] = p;
						}
					}
					if (r == 5) {
						if (!isEven(c)) {
							//**Todo draw bomb pieces
							p = new Piece(false, this, c, r, "bomb");
							pieces[c][r] = p;
						}
					}
					if (r == 2) {
						if (isEven(c)) {
							//**Todo draw bomb pieces
							p = new Piece(true, this, c, r, "bomb");
							pieces[c][r] = p;
						}
					}
					if (r == 1) {
						if (!isEven(c)) {
							//**Todo draw shield pieces
							p = new Piece(true, this, c, r, "shield");
							pieces[c][r] = p;
						}
					}
					if (r == 0) {
						if (isEven(c)) {
							//**Todo draw normal pieces
							p = new Piece(true, this, c, r, "pawn");
							pieces[c][r] = p;
						}
					}
				}	
			}
		}

	}

	private static boolean isEven(int n) {
		return (n % 2 == 0);
	}
	private void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (this.selectedPieceX == i && this.selectedPieceY == j) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				}
				else if (this.lastSelectedPieceX == i && this.lastSelectedPieceY == j) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				}
				else if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				}
			}
		}
		
	}
	private String getImgString(Piece p) {
		String type = "pawn";
		String fireOrNot = "-water";
		String crownedOrNot = "";
		if (p.isFire())
			fireOrNot = "-fire";
		if (p.isBomb())
			type = "bomb";
		if (p.isShield())
			type = "shield";
		if (p.isKing())
			crownedOrNot = "-crowned";
		return "img/" + type + fireOrNot + crownedOrNot + ".png";
	}
	private void placePieces() {
		//Loop through all of the rows on the board
		Piece p;
		if (this.boardDrawn) {
			for (int r = 0; r < size; r++) {
				for (int c = 0; c < size; c++) {
					if (pieces[c][r] != null) {
						StdDrawPlus.picture(c + .5, r + .5, this.getImgString(pieces[c][r]), 1, 1);
					}
				}
			}
			return;
		}
		for (int r = 0; r < size; r++) {
			//Loop through all of the columns on the board
			for (int c = 0; c < size; c++) {
				if (r == 7) {
					if (!isEven(c)) {
						//**Todo draw normal pieces
						p = new Piece(false, this, c, r, "pawn");
						pieces[c][r] = p;
						StdDrawPlus.picture(c + .5, r + .5, this.getImgString(p), 1, 1);
					}	
				}
				if (r == 6) {
					if (isEven(c)) {
						//**Todo draw shield pieces
						p = new Piece(false, this, c, r, "shield");
						pieces[c][r] = p;
						StdDrawPlus.picture(c + .5, r + .5, this.getImgString(p), 1, 1);
					}
				}
				if (r == 5) {
					if (!isEven(c)) {
						//**Todo draw bomb pieces
						p = new Piece(false, this, c, r, "bomb");
						pieces[c][r] = p;
						StdDrawPlus.picture(c + .5, r + .5, this.getImgString(p), 1, 1);
					}
				}
				if (r == 2) {
					if (isEven(c)) {
						//**Todo draw bomb pieces
						p = new Piece(true, this, c, r, "bomb");
						pieces[c][r] = p;
						StdDrawPlus.picture(c + .5, r + .5, this.getImgString(p), 1, 1);
					}
				}
				if (r == 1) {
					if (!isEven(c)) {
						//**Todo draw shield pieces
						p = new Piece(true, this, c, r, "shield");
						pieces[c][r] = p;
						StdDrawPlus.picture(c + .5, r + .5, this.getImgString(p), 1, 1);
					}
				}
				if (r == 0) {
					if (isEven(c)) {
						//**Todo draw normal pieces
						p = new Piece(true, this, c, r, "pawn");
						pieces[c][r] = p;
						StdDrawPlus.picture(c + .5, r + .5, this.getImgString(p), 1, 1);
					}
				}
			}	
		}
		this.boardDrawn = true;
	}
	public Piece pieceAt(int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0)
			return null;
		if (pieces[x][y] != null)
			return pieces[x][y];
		return null;
	}

	private int getPieceXLocation(Piece p) {
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (pieces[c][r] == p)
					return c;
			}
		}
		return -1;
	}
	private int getPieceYLocation(Piece p) {
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (pieces[c][r] == p)
					return r;
			}
		}
		return -1;
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf) {
		//Sanity check to make sure all of these pieces are within the board
		if (xi > 7 || xi < 0 || yi > 7 || yi < 0 || xf > 7 || xf < 0 || yf > 7 || yf < 0)
			return false;
		//Let's also make sure there isn't another piece in that current position
		if (pieceAt(xf, yf) != null)
			return false;
		//We need to first get our piece from the intial position
		Piece p = pieceAt(xi, yi);
		int side = p.side(); //0 is fire (moves upward), 1 is water (moves downward)
		//Now let's find out if it is a king
		if (p.isKing()) {
			//Let us see if they are trying to do a capture
			int potentialCaptureX1 = xi + 2;
			int potentialCaptureX2 = xi - 2;
			int potentialCaptureY1 = yi + 2;
			int potentialCaptureY2 = yi - 2;
			if ((xf == potentialCaptureX1 || xf == potentialCaptureX2) && (yf == potentialCaptureY1 || yf == potentialCaptureY2)) {
				int capturedX = -1;
				int capturedY = -1;
				if (xf == potentialCaptureX1)
					capturedX = xf - 1;
				if (xf == potentialCaptureX2)
					capturedX = xf + 1;
				if (yf == potentialCaptureY1)
					capturedY = yf - 1;
				if (yf == potentialCaptureY2)
					capturedY = yf + 1;
				Piece capturePiece = this.pieceAt(capturedX, capturedY);
				if (capturePiece != null && capturePiece.side() != this.turn)
					return true;
				return false;
			}
			//Since this piece is a king it can move in 4 directions
			if ((xf == 1 + xi || xf == xi - 1) && (yf == yi + 1 || yf == yi - 1) && !this.canMultiCapture)
				return true;
		}
		else {
			//Ok this piece is not a king
			//Lets see if we should be moving up or down now
			if (side == 0) {
				//upward it is
				//Let us see if they are trying to do a capture
				int potentialCaptureX1 = xi + 2;
				int potentialCaptureX2 = xi - 2;
				int potentialCaptureY1 = yi + 2;
				if ((xf == potentialCaptureX1 || xf == potentialCaptureX2) && yf == potentialCaptureY1) {
					int capturedX = -1;
					if (xf == potentialCaptureX1)
						capturedX = xf - 1;
					else
						capturedX = xf + 1;
					int capturedY = yf - 1;
					Piece capturePiece = this.pieceAt(capturedX, capturedY);
					if (capturePiece != null && capturePiece.side() != this.turn)
						return true;
					return false;
				}
				if ((xf == 1 + xi || xf == xi - 1) && yf == yi + 1 && !this.canMultiCapture)
					return true;
			}
			else {
				int potentialCaptureX1 = xi + 2;
				int potentialCaptureX2 = xi - 2;
				int potentialCaptureY1 = yi - 2;
				if ((xf == potentialCaptureX1 || xf == potentialCaptureX2) && yf == potentialCaptureY1) {
					int capturedX = -1;
					if (xf == potentialCaptureX1)
						capturedX = xf - 1;
					else
						capturedX = xf + 1;
					int capturedY = yf + 1;
					Piece capturePiece = this.pieceAt(capturedX, capturedY);
					if (capturePiece != null && capturePiece.side() != this.turn)
						return true;
					return false;
				}	
				//alright fine downward then
				if ((xf == 1 + xi || xf == xi - 1) && yf == yi - 1 && (!this.canMultiCapture))
					return true;
			}
		}
		return false;
	}

	public boolean canSelect(int x, int y) {
		Piece p = this.pieceAt(x, y);
		if (this.canEndTurn && !this.canMultiCapture)
			return false;
		if (p == null) {
			//The player is trying to select an empty square
			if (selectedPiece != null && (!selectedPieceMoved || this.canMultiCapture)) {
				int c = this.selectedPieceX;
				int r = this.selectedPieceY;
				if (c == -1 || r == -1) {
					//System.out.println("Invalid selected piece location (" + c + ", " + r + ")");
					return false;
				}
				if (validMove(c, r, x, y))
					return true;
				System.out.println("That is not a valid move");
				return false;
			}
			System.out.println("Failed: this.canMultiCapture = " + this.canMultiCapture); 
			return false;
		}
		else {
			//System.out.println("p != null in canSelect");	
			int side = p.side();
			if (side != turn)
				return false;
			if ((selectedPiece != null && selectedPieceMoved) || this.canMultiCapture) {
				return false;
			}
			return true;

		}

		
	}
	public void place(Piece p, int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("Error: place() - New coordinates out of bounds");
			return;
		}
		Piece currentPiece = this.pieceAt(x, y); 
		if (currentPiece != null) 
			this.remove(x, y); 
		pieces[x][y] = p;
		if (!this.firstPiecePlaced)
			this.firstPiecePlaced = true;

		//p.move(x, y);
	}

	public Piece remove(int x, int y) {
		Piece p = this.pieceAt(x, y);
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			System.out.println("Index out of bounds.");
			return null;
		}
		else if (p == null) {
			System.out.println("No piece found to remove (" + x + ", " + y + ")");
			return null;
		}
		else {
			if (p.hasCaptured() && this.selectedPiece == p) {
				this.selectedPiece = null;
				this.lastSelectedPieceX = x;
				this.lastSelectedPieceY = y;
			}
			this.pieces[x][y] = null;
			return p;
		}

	}

	private void fillSquareWhite(int x, int y) {
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	}
	
	private void fillSquareGray(int x, int y) {
		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	}

	public void select(int x, int y) {
		Piece p = this.pieceAt(x, y);
		//if (!this.canSelect(x, y)) {
		//	return;
		//}
		 if (p != null) {
			//Is there a previously selected piece. if so let's make it's background white
			if (this.selectedPiece != null) {
				this.lastSelectedPieceX = this.selectedPieceX;
				this.lastSelectedPieceY = this.selectedPieceY;
				//this.fillSquareGray(this.selectedPieceX, this.selectedPieceY);	
			}
			this.selectedPiece = p;
			this.selectedPieceX = x;
			this.selectedPieceY = y;
			//this.fillSquareWhite(x, y);
		}
		else {
			//Ok this player is selecting an empty square
			//p = this.remove(this.selectedPieceX, this.selectedPieceY);
			//this.place(p, x, y);
			if (this.selectedPiece != null) {
				this.canMultiCapture = false;
				this.selectedPiece.move(x, y);
				this.selectedPiece = this.pieceAt(x, y);
				this.selectedPieceX = x;
				this.selectedPieceY = y;
				//this.selectedPiece = null;
				//this.selectedPieceX = -1;
				//this.selectedPieceY = -1; 
				//System.out.println("(select())this.selectedPiece.hasCaptured = " + this.selectedPiece.hasCaptured());
				if (this.selectedPiece != null && this.selectedPiece.hasCaptured()) {
					//Let's see if they can make a multi capture
						System.out.println("Checking if multicapture is possible");
						int potentialCaptureX1 = x + 2;
						int potentialCaptureX2 = x - 2;
						int potentialCaptureY1 = y + 2;	
						int potentialCaptureY2 = y - 2;
						Piece potential1 = this.pieceAt(potentialCaptureX1, potentialCaptureY1);
						Piece potential2 = this.pieceAt(potentialCaptureX1, potentialCaptureY2);
						Piece potential3 = this.pieceAt(potentialCaptureX2, potentialCaptureY1);
						Piece potential4 = this.pieceAt(potentialCaptureX2, potentialCaptureY2);
						if (this.validMove(x, y, potentialCaptureX1, potentialCaptureY1) || this.validMove(x, y, potentialCaptureX1, potentialCaptureY2) || this.validMove(x, y, potentialCaptureX2, potentialCaptureY1) || this.validMove(x, y, potentialCaptureX2, potentialCaptureY2))
							this.canMultiCapture = true;
					}

				}
				this.canEndTurn = true;
			}
	}
	
	public boolean canEndTurn() {
		if (this.canEndTurn)
			return true;
		return false;
	}

	public void endTurn() {
		if (this.canEndTurn()) {
			if (this.turn == 0)
				this.turn = 1;
			else
				this.turn = 0;
			this.canEndTurn = false;

			if (this.selectedPiece != null && this.selectedPiece.hasCaptured())
				this.selectedPiece.doneCapturing();
			this.selectedPiece = null;
			this.selectedPieceX = -1;
			this.selectedPieceY = -1; 
			this.canMultiCapture = false;
		}
	}


	private static void setScale() {
		StdDrawPlus.setXscale(0, size);
		StdDrawPlus.setYscale(0, size);
	}
	
	public String winner() {
		//Let's see who won :P 
		int firePieces = 0;
		int waterPieces = 0;
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (pieces[c][r] != null && pieces[c][r].isFire())
					firePieces++;
				else if (pieces[c][r] != null && !pieces[c][r].isFire())
					waterPieces++;
			}
		}
		if (firePieces > 0 && waterPieces > 0)
			return null;
		else if (firePieces > 0)
			return "Fire";
		else if (waterPieces > 0)
			return "Water";
		else
			return "No one";
	}

	public static void main(String[] args) {
		int size = 8;
		Board b = new Board(false);
		setScale();
		while(true) {
			//Check to see if a mouse was pressed
			if (StdDrawPlus.mousePressed()) {
				int mx = (int)StdDrawPlus.mouseX();
				int my = (int)StdDrawPlus.mouseY();
				if (b.canSelect(mx, my)) {
					b.select(mx, my);
				}
			}
			//Check to see if spacebar is currently being pressed
			if (StdDrawPlus.isSpacePressed()) {
				b.endTurn();
			}
			b.drawBoard(size);
			if (b.doDrawPieces)
				b.placePieces();
			StdDrawPlus.show(50);
			if (b.winner() != null && b.firstPiecePlaced) {
				System.out.println(b.winner());
				return;
			}
		}

	}
		
}
//Board .java 

