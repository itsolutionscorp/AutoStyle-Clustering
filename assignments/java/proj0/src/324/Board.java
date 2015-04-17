
//Stefan Palombo
public class Board {
	private Piece [] [] pieces = new Piece[8][8];
	//swap after space press
	private boolean fireTurn = true;
	//Reset after space press
	private boolean selectedSquare = false;
	private int sSquareX;
	private int sSquareY;
	private Piece selectedPiece;
	private int selectedX;
	private int selectedY;
	private boolean hasMoved = false;
	private boolean hasCaptured = false;
	
	public Board(boolean shouldBeEmpty) {
		if (shouldBeEmpty) {
			return;
		} else {
			startPieces();
		}
	}

	public String winner() {
		//returns "No one" if no pieces are present
		//returns "Water" if water has pieces but fire does not
		//returns "fire" if fire has pieces and water does not
		//returns null if both teams still have pieces
		int firePieces = 0;
		int waterPieces = 0; 
		//goes through board counts how many pieces from each team are present
		//after declares winner if applicable
		for (int col = 0; col < pieces.length; col += 1) {
			for (int row = 0; row < pieces[col].length; row += 1) {
				Piece piece = pieces[col][row];
				if (piece != null) {
					if (piece.isFire()) {
						firePieces += 1;
					} 
					else {
						waterPieces += 1;
					}
				}
			}
		}
		if (firePieces == 0 && waterPieces == 0) {
			return "No one";
		}
		else if (firePieces == 0) {
			return "Water";
		}
		else if (waterPieces == 0) {
			return "Fire";
		}
		else {
			return null;
		}
	}

	public Piece pieceAt(int x, int y) {
		//returns the piece at given cords; returns null if none is present
		//or cords are out of bounds
		if(!isOutOfBounds(x, y)) {
			return pieces[x][y];
		}
		else {
			return null;
		}
	}


	public boolean canSelect (int x, int y) {
		//returns true if the selected coords. may validly be selected under
		//the checkers rules
		if(isOutOfBounds(x, y)) {
			return false;
		}
		Piece piece = pieces[x][y];
		//if a piece is not selected already, the space being selected has a piece,
		//it belongs to the players team, and the player hasn't moved returns true
		if (piece != null && piece.isFire() == fireTurn) {
			if(selectedPiece == null || !hasMoved) {
				return true;
			}
		}
		//if the space has no piece, a piece has already been selected, player hasn't moved,
		//and the selected space is a valid move for the selected piece returns true
		else if (piece == null && selectedPiece != null){
			if (!hasMoved && validMove(selectedPiece, selectedX, selectedY, x, y)) {
				return true;
			}
			//handels capture after capture
			if(hasCaptured && canCapture(selectedPiece, selectedX, selectedY, x, y)) {
				return true;
			}
		}
		return false;
	}


	private boolean validMove(Piece p, int xi, int yi, int xf, int yf) {
		//returns whether a selected piece is a valid move for piece p 
		if(isOutOfBounds(xf, yf) || pieces[xf][yf] != null) {
			return false;
		}
		//fire goes up the board to water's side
		boolean toWater = yf - yi > 0;
		//assures the piece is either a king or going the right direction
		if (p.isKing() || p.isFire() == toWater) {
			if(distance(xi, xf) == 1 && distance(yi, yf) == 1) {
				return true;
			}
			else if (canCapture(p, xi, yi, xf, yf)) {
				return true;
			}
		}
		return false;

	}

	private boolean canCapture (Piece p, int xi, int yi, int xf, int yf) {
		//checks whether a given move for piece p is a valid capture
		boolean toWater = yf - yi > 0;
		//assures the piece is either a king or going the right direction
		if (p.isKing() || p.isFire() == toWater) {
			if (distance(xi, xf) == 2 && distance(yi, yf) ==2) {
				//assures there is a piece capable of being captured
				Piece posCapture = pieces[ave(xi, xf)][ave(yi, yf)];
				if (posCapture != null && posCapture.isFire() != fireTurn) {
					return true;
				}
			}
		}
		return false;
	}

	private int ave (int p1, int p2) {
		//returns the average of two values
		return ((p1 + p2) >>> 1);
	}

	private int distance (int p1, int p2) {
		//returns the distance of two values
		return Math.abs(p1 - p2);
	}

	public void select (int x, int y) {
		//handles manipulating instance variables in a select and calls move
		//on a piece if applicable
		Piece possPiece = pieces[x][y];
		//selects a specific piece
		if (possPiece != null) {
			selectedPiece = possPiece;
			selectedX = x;
			selectedY = y;
		//moves a piece that has already been selected
		} else {
			pieces[x][y] = selectedPiece;
			selectedPiece.move(x, y);
			pieces[selectedX][selectedY] = null;
			selectedX = x;
			selectedY = y;
			hasMoved = true;
			hasCaptured = selectedPiece.hasCaptured();
		}
	}

	public void place(Piece p, int x, int y) {
		//puts a piece in its given place 
		if(isOutOfBounds(x, y) || p==null) {
			return;
		}
		else {
			this.pieces[x][y] = p;
		}
	}


	public Piece remove (int x, int y) {
		//removes a given piece from the board
		if(isOutOfBounds(x, y)) {
			System.out.println("Given coordinates are off the board.");
			return null;
		}
		Piece hold = pieces[x][y];
		pieces[x][y] = null;
		if(hold == null) {
			System.out.println("No piece to remove.");
			return null;
		}
		return hold;

	}

	public boolean canEndTurn() {
		//returns true if the player may validly end his/her turn
		return (hasMoved || hasCaptured); 
	}

	public void endTurn () {
		//resets all instance variables in preparation for next turn
		selectedPiece.doneCapturing();
		selectedPiece = null;
		selectedX = -1;
		selectedY = -1;
		hasMoved = false;
		hasCaptured = false;
		fireTurn = !fireTurn;
		sSquareX = -1;
		sSquareY = -1;
		selectedSquare = false;
	}

	private boolean isOutOfBounds(int x, int y) {
		//returns whether given x,y-coords. are validly on the board
		return (x > 7 || x < 0 || y > 7 || y < 0);
	}

	
	private void drawBoard () {
		//draws the board
		StdDrawPlus.setPenColor(StdDrawPlus.RED);
		//makes red box behind whole board
		StdDrawPlus.filledSquare( 4, 4, 4);
		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		//draws grey squares in appropriate places
		for (int row = 0; row < 8; row += 1) {
			for (int col = 0; col < 8; col +=1) {
				if ((col + row) % 2 == 0) {
					StdDrawPlus.filledSquare(col + 0.5, row + 0.5, .5);
				}
				Piece piece = pieces[col][row];
				//highlights white
				if (selectedSquare && col == sSquareX && row == sSquareY) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(sSquareX + 0.5, sSquareY + 0.5, .5);
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				//draws pieces if they are present
				if (piece != null) {
					drawPiece(piece, col, row);
				}
			}
		}
	}

	private void startPieces() {
		//sets up starting configuration of pieces
		for (int i = 0; i < 8; i += 1) {
			//goes through every column of squares and places appropriate pieces at
			//appropriate rows. The pattern alternates every column
			if (i % 2 == 0) {
				place(new Piece(true, this, i, 0, "pawn"), i, 0);
				place(new Piece(false, this, i, 6, "shield"), i, 6);
				place(new Piece(true, this, i, 2, "bomb"), i, 2);
			} else {
				place(new Piece(false, this, i, 7, "pawn"), i, 7);
				place(new Piece(true, this, i, 1, "shield"), i, 1);
				place(new Piece(false, this, i, 5, "bomb"), i, 5);
			}
		}
	}


	private void drawPiece(Piece p, int col, int row) {
		//builds a file path based off of piece type then draws the piece
		StringBuilder fileName = new StringBuilder();
		fileName.append("img/");
		if (p.isBomb()) {
			fileName.append("bomb-");
		}
		else if (p.isShield()) {
			fileName.append("shield-");
		} 
		else {
			fileName.append("pawn-");
		}

		if (p.isFire()) {
			fileName.append("fire");
		}
		else {
			fileName.append("water");
		}
		if (p.isKing()) {
			fileName.append("-crowned");
		}
		fileName.append(".png");
		StdDrawPlus.picture(col + 0.5, row + 0.5, fileName.toString(), 1.0, 1.0);
	}


	public static void main(String[] args) {
		//sets board scale to be convinient to 8x8 board
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		Board b = new Board(false);
		while(true) {
			//checks if there is a winner
			b.winner();
			//draws board
			b.drawBoard();
			if (StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (b.canSelect(x, y)) {
					b.sSquareX = x;
					b.sSquareY = y;
					b.selectedSquare = true;
					b.select(x, y);
				}  
			}  
			if(StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
				b.endTurn();
			}
			StdDrawPlus.show(100);
		}


	}


}
