public class Board {
	private static final int SIZE = 8;
	private Piece[][] pieces = new Piece[SIZE][SIZE];
	private boolean fireTurn = true;
	private Piece selected = null;
	private boolean moved;
	private boolean shouldBeEmpty;
	private int xCurr = 1;
	private int yCurr = 0;
	private int fCount;
	private int wCount;
	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, SIZE);
		StdDrawPlus.setYscale(0, SIZE);
		Board board = new Board(false);
		board.drawBoard(board.shouldBeEmpty);
		while(true) {
			if(board.winner() != null) {
				System.out.println(board.winner());
				break;
			}
			if(StdDrawPlus.mousePressed()) {
				int x = (int)StdDrawPlus.mouseX();
				int y = (int)StdDrawPlus.mouseY();
				if(board.canSelect(x, y)) {
					board.select(x, y);
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
				}
				if(board.moved)
					board.drawBoard(board.shouldBeEmpty);
			}
			if(StdDrawPlus.isSpacePressed() && board.canEndTurn()) {
				board.endTurn();
			}
			StdDrawPlus.show(100);
		}
	}	

	/* Initializes the array of pieces. */
	public Board(boolean shouldBeEmpty) {
		this.shouldBeEmpty = shouldBeEmpty;
		if(!shouldBeEmpty) {
			this.initArray();
			fCount = 12;
			wCount = 12;
		} else {
			fCount = 0;
			wCount = 0;	
		}	
	}
	/** Draws an empty board. */
	private void drawBoard(boolean drawIcon) {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
		if(!drawIcon)
			drawIcons();
	}

	/** Draws a board with all the images. */
	private void drawIcons() {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if(pieces[i][j] != null) {
					String type = "";
					if(pieces[i][j].isBomb())
						type = "bomb";
					else if(pieces[i][j].isShield())
						type = "shield";
					else
						type = "pawn";
					this.drawImage(i, j, type, pieces[i][j].isFire());
				}
			}
		}
	}

	/** Initializes a two-dimensional array of Piece objects. */
	private void initArray() {
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				if((i + j) % 2 == 0 && (j <= 2 || j >= (SIZE - 3))) {
					pieces[i][j] = this.initPiece(i, j); 
				} else {
					pieces[i][j] = null;
				}
			}
		}
	}
	
	/** Initializes a piece at the coordinates (i, j).*/
	private Piece initPiece(int i, int j) {
		boolean fireType = false;
		if(j <= 2) {
			fireType = true;
		}
		String type = "";
		switch(j) {
			case 0: type = "pawn"; break;
			case 1: case (SIZE - 2): type = "shield"; break;
			case 2: case (SIZE - 3): type = "bomb"; break;
		}
		Piece piece = new Piece(fireType, this, i, j, type);
		return piece;
	}

	/** Draws an image at point (i, j) depending on the type of the object given. */
	private void drawImage(int i, int j, String type, boolean fireType) {
		String picture = "";
		boolean king = pieceAt(i, j).isKing();
		switch(type) {
			case "pawn": if(fireType) {
				picture = "pawn-fire";
			} else {
				picture = "pawn-water";
			} break;
			case "shield": if(fireType) {
				picture = "shield-fire";
			} else {
				picture = "shield-water";
			} break;
			case "bomb": if(fireType) {
				picture = "bomb-fire";
			} else {
				picture = "bomb-water";
			} break;
		}
		if(king)
			picture += "-crowned";
		picture += ".png";
		StdDrawPlus.picture(i + .5, j + .5, "img/" + picture, 1, 1);
	}

	

	/** Methods required to be implemented are the following. */

	/** Method 1: Piece pieceAt(int x, int y). Returns the Piece object at the given coordinates.
	 *  Returns null if no piece exists at those coordinates, or if the coordinates are out of bounds.*/
	public Piece pieceAt(int x, int y) {
		if(x >= SIZE || y >= SIZE || x < 0 || y < 0) {
			return null; 
		}	
		return pieces[x][y];
	}

	/** Method 2: void place(Piece p, int x, int y). This method places an object at the given coordinates.*/
	public void place(Piece p, int x, int y) {
		if(x < SIZE && y < SIZE && p != null) {
			for(int i = 0; i < SIZE; i++) {
				for(int j = 0; j < SIZE; j++) {
					Piece piece = pieces[i][j];
					if(piece == p) {
						this.remove(i, j);
					}
				}
			}
			Piece piece = this.pieceAt(x, y);
			if(piece != null)
				this.remove(x, y);
			if(p.isFire())
				fCount += 1;
			else
				wCount += 1;
			pieces[x][y] = p;
		}
	}

	/** Method 3: Piece remove(int x, int y). Removes a piece from (x, y) and returns it. */
	public Piece remove(int x, int y) {
		Piece piece = pieces[x][y];
		if(piece != null) {
			if(piece.isFire())
				fCount -= 1;
			else
				wCount -= 1;
		}	
		pieces[x][y] = null;
		if(piece == null) {
			System.out.println("Object is null");
			return null;
		}	
		return piece;
	}

	/** Method 4: boolean canSelect(int x, int y). Returns true if the square at (x, y) can
	 *  be selected. */
	public boolean canSelect(int x, int y) {
		Piece piece = this.pieceAt(x, y);
		if((x + y) % 2 != 0 || x >= SIZE || y >= SIZE || x < 0 || y < 0)
			return false;
		if(piece != null) {
			boolean type = piece.isFire();
			if((type == fireTurn) && (selected == null || (selected != null && !moved)))
				return true;
		}
		if(piece == null) {
			if((selected != null) && !moved && validMove(xCurr, yCurr, x, y))
				return true;
			if((selected != null) && selected.hasCaptured() && validMove(xCurr, yCurr, x, y) && isCapture(xCurr, yCurr, x, y))
				return true;				
		}
		return false;
	}

	private boolean isCapture(int xi, int yi, int xf, int yf) {
		Piece piecei = pieceAt(xi, yi);
		Piece mid = pieceAt((xf+xi)/2,(yf+yi)/2);
		if(pieceAt(xf, yf) == null && (xf >= 0 && xf < SIZE)&& mid != null && mid.isFire() != selected.isFire())
			return true;
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece piecei = this.pieceAt(xi, yi);
		int diffx = xf - xi;
		int diffy = yf - yi;
		int midx = (xf + xi)/2;
		int midy = (yf + yi)/2;
		if(piecei != null && !piecei.isKing() && ((piecei.isFire() && diffy < 0) || (!piecei.isFire() && diffy > 0))){
			return false;
		}	
		if(piecei != null) {
			if(!piecei.isKing() && Math.abs(diffx) == 1)
				return true;
			if(fireTurn && diffy > 1 && !piecei.isKing() && (diffx % 2 == 0) && pieceAt(midx, midy) != null) {
				if(!pieceAt(midx, midy).isFire())
					return true;
			}
			if(!fireTurn && diffy < 1 && !piecei.isKing() && (diffx % 2 == 0) && pieceAt(midx, midy) != null) {
				if(pieceAt(midx, midy).isFire())
					return true;
			}
			if(piecei.isKing() && Math.abs(diffx) == 1)
				return true;
			if(piecei.isKing() && Math.abs(diffx) % 2 == 0 && this.pieceAt(midx, midy)!= null) {
				if(this.pieceAt(midx, midy).isFire() != piecei.isFire())
					return true;
			}
		}	
		return false;
	}

	/* Method 5: void select(int x, int y). Selects a given spot on the board. */
	public void select(int x, int y) {
		if(this.pieceAt(x, y) != null) {
			selected = this.pieceAt(x, y);
		} else {
			if(!selected.isBomb()) {
				selected.move(x, y);
				moved = true;
			} else {
				Piece bomb = selected;
				selected.move(x, y);
				moved = true;
				selected = bomb;
			}
			if(!selected.isBomb() && selected.hasCaptured())
				selected = this.pieceAt(x, y);
		}	
		this.xCurr = x;
		this.yCurr = y;
	}

	public boolean canEndTurn() {
		if(selected != null && (moved || selected.hasCaptured())) {
			return true;
		} else if(selected == null && moved) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		this.fireTurn = !this.fireTurn;
		this.xCurr = 1;
		this.yCurr = 0;
		if(selected != null)
			selected.doneCapturing();
		this.selected = null;
		this.moved = false;
	}

	public String winner() {
		if(fCount <= 0 || wCount <= 0) {
			if(fCount > wCount)
				return "Fire";
			else if(wCount > fCount)
				return "Water";
			else
				return "No one";
		}
		return null;
	}
}