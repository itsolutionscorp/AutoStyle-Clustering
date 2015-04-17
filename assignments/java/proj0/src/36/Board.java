// @author Kit Freddura cs61b-abk

public class Board{
	private  Piece[][] pieces;
	private boolean fireTurn;
	private int numFirePieces;
	private boolean waterTurn;
	private int numWaterPieces;
	private boolean hasSelected;
	private boolean hasMoved;
	private Piece selectedPiece;
	private int selectX;
	private int selectY;

	public Board(boolean shouldBeEmpty) {
		this.pieces = new Piece[8][8];
		this.fireTurn = true;
		this.waterTurn = false;
		this.hasMoved = false;
		this.selectedPiece = null;
		this.selectX = 20;
		this.selectY = 20;
		if (!shouldBeEmpty) {
			this.initializePieces();
			this.numFirePieces = 12;
			this.numWaterPieces = 12;
		} else {
			this.numFirePieces = 0;
			this.numWaterPieces = 0;
		}
	}

	/* Uses error handling
	 * to catch input that is out of bounds
	 * and returns null if that is the case */
	public Piece pieceAt(int x, int y) {
		try {
			return pieces[x][y];
		}

		catch (Exception expt) {
			return null;
		}
	}

	public void place(Piece p, int x, int y) {
		if (p == null || outOfBounds(x, y)) {
			return;
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] == p) {
					pieces[i][j] = null;
					if (p.isFire()) {
						numFirePieces -= 1;
					} else {
						numWaterPieces -= 1;
					}
				}
			}
		}
		pieces[x][y] = p;
		if (p.isFire()) {
			numFirePieces += 1;
		} else {
			numWaterPieces += 1;
		}
	}

	/* 1) checks right turn and if the selection is not null
	 * 2) runs through tests for selecting a piece
	 * 3) runs through tests for selecting a empty space */
	public boolean canSelect(int x, int y) {
		Piece piece = pieceAt(x, y);
		if (piece != null && rightTurn(piece)) {
			if (!hasSelected) {
				return true;
			} else {
				if (hasMoved) {
				return false;
			} else if (selectedPiece.hasCaptured() || !hasMoved) {
				return true;
			} else {
				return false;
			}
		}
		} else {
			if (hasSelected && ((!hasMoved) || (selectedPiece.hasCaptured() && !selectedPiece.isBomb()))) {
				return canMove(selectX, selectY, x, y);
			} else {
				return false;
			}
		}
	}

	public void select(int x, int y) {
			Piece temp = pieceAt(x, y);
			if (hasSelected && temp == null) {
				hasMoved = true;
				selectedPiece.move(x, y);
			}
			if (temp != null)  {
				selectedPiece = temp;
				hasSelected = true;
			}
			selectX = x;
			selectY = y;
	}

	/* Tests if a piece can move to a certain position.
	 * Checks
	 * 1) is there a piece at xy,yf
	 * 2) is the moving piece a king
	 * 3) is the destination within one diagonal
	 * 4) if not is there a piece inbetween to capture
	 * 5) repeats for non-king pieces
	 */
	private boolean canMove(int xi, int yi, int xf, int yf) {
		if (pieceAt(xf, yf) != null) { 
			return false;
		}
		int xMove = xf - xi;
		int yMove = yf - yi;
		if (selectedPiece.isKing()) {
			if (Math.abs(xMove) == 1 && Math.abs(yMove) == 1 && !selectedPiece.hasCaptured()) {
				return true;
			} else {
				int midX = midpoint(xf, xi);
				int midY = midpoint(yf, yi);
				Piece capturee = pieceAt(midX, midY);
				if (capturee == null) {
					return false;
				} else if (!sameSide(selectedPiece, capturee)) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			if (Math.abs(xMove) != Math.abs(yMove)) {
				return false;
			} else if (xMove == 1 || xMove == -1) {
				if (selectedPiece.isFire()) {
					if (yMove == 1) return true;
					return false;
				} else {
					if (yMove == -1) return true;
					return false;
				}
			} else {
				int midX = midpoint(xf, xi);
				int midY = midpoint(yf, yi);
				Piece capturee = pieceAt(midX, midY);
				if (capturee == null) {
					return false;
				} else if (behind(midX, midY)) {
					return false;
				} else if (!sameSide(selectedPiece, capturee)) {
					return true;
				} else {
					return false;
				}
			}
		}
	}

	/* removes a piece by setting it's position
	 * in the board to null */
	public Piece remove(int x, int y) {
		if (outOfBounds(x, y)) {
			StdOut.println("Remove: Indicies out of bounds");
			return null;
		}
		Piece removed = pieceAt(x, y);
		if (removed == null) {
			StdOut.println("Remove: No such piece at index");
			return null;
		}
		if (removed.isFire()) {
			numFirePieces -= 1;
		} else {
			numWaterPieces -= 1;
		}
		//special handling for if selected 
		//piece is being removed 
		if (selectX == x && selectY == y) {
			selectX = 100;
			selectY = 100;
			hasSelected = false;
		}
		pieces[x][y] = null; //after edge checking, removes piece
		return removed;

	}

	public boolean canEndTurn() {
		if (hasMoved || (selectedPiece != null && selectedPiece.hasCaptured())) { 
			return true;
		}
		return false;
	}

	public void endTurn() {
		if (selectedPiece != null) {
			selectedPiece.doneCapturing();
			selectedPiece = null;
		}
		hasSelected = false;
		selectX = 100;
		selectY = 100;
		hasMoved = false;
		if (fireTurn) {
			fireTurn = false;
			waterTurn = true;
		} else {
			waterTurn = false;
			fireTurn = true;
		}
	}

	public String winner() {
		String win = "";
		if (numWaterPieces ==0 && numFirePieces == 0) {
			win = "No one";
		}else if (numWaterPieces == 0) {
			win = "Fire";
		} else if (numFirePieces == 0) {
			win = "Water";
		} else {
			win = null;
		}
		return win;
	}


	private  void drawBoard(int size) {
		for (int i = 0; i < size; i+=1) {
			for (int j = 0; j < size; j+=1) {
				if (i == selectX && j == selectY) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
					String imgLocation = imgLoc(selectedPiece);
					StdDrawPlus.picture(i + 0.5, j + 0.5, imgLocation, 1, 1);
				} else if ((i+j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				} else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				if (pieces[i][j] != null) {
					Piece curr = pieces[i][j];
					String imgLocation = imgLoc(curr);
					StdDrawPlus.picture(i+ 0.5, j+0.5, imgLocation, 1, 1);

				}
			}
		}
	}

	/* Populates the nested array of pieces with the
	 * initial set up of checkers.
	 * The first three rows of the board are fire pieces
	 * and the last three are water pieces */
	private void initializePieces() {
		int size = pieces.length;
		for (int i = 0; i < size; i+=2) {
			pieces[i][0] = new Piece(true, this, i, 0, "pawn");
			pieces[i][2] = new Piece(true, this, i, 2, "bomb");
			pieces[i][size-2] = new Piece(false, this, i, size-2, "shield");
		}

		for (int i = 1; i < size; i+=2) {
			pieces[i][1] = new Piece(true, this, i, 1, "shield");
			pieces[i][size-1] = new Piece(false, this, i, size-1, "pawn");
			pieces[i][size-3] = new Piece(false, this, i, size-3, "bomb");
		}
	}

	//used to clean up code as this is used often
	//tests if a piece is out of the boards bounds
	private static boolean outOfBounds(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) return true;
		return false;
	}

	//used to find capturable pieces
	//in canMove
	private int midpoint(int x1, int x2){
		return (x1+x2)/2;
	}

	/* Tests to see if the current player
	 * can select the type of piece in question */
	private boolean rightTurn(Piece p) {
		if (p == null) {
			return false;
		}
		if (p.isFire() && fireTurn) {
			return true;
		} else if ((!(p.isFire())) && waterTurn) {
			return true;
		} else {
			return false;
		}
	}

	//tests if two pieces are on the same side
	private boolean sameSide(Piece p1, Piece p2) {
		int sum = p1.side() + p2.side();
		if (sum == 1) return false;
		return true;
	}

	/* Tells if p2 is behind p1 and thus
	 * non-capturable by a non-king piece */
	private boolean behind(int x, int y) {
		Piece p = pieceAt(x, y);
		if (p == null) return true;
		if (selectedPiece.isFire()) {
			if (y <= selectY) return true;
			return false;
		} else {
			if (y >= selectY) return true;
			return false;
		}
	}

	/* To help with putting images on the board
	 * this method assigns a piece it's corresponding
	 * image file location. It is called in the initializer
	 * to make sure that every piece has an image file
	 * and can be used to change the image from a normal
	 * image to it's kinged counter part */
	private String imgLoc(Piece p) {
		String img = "";
		if (p.isFire()) {
			if (p.isKing()) {
				if (p.isBomb()) {
					img = "img/bomb-fire-crowned.png";
				} else if (p.isShield()) {
					img = "img/shield-fire-crowned.png";
				} else {
					img = "img/pawn-fire-crowned.png";
				}
			} else {
				if (p.isBomb()) {
					img = "img/bomb-fire.png";
				} else if (p.isShield()) {
					img = "img/shield-fire.png";
				} else {
					img = "img/pawn-fire.png";
				}
			}
		} else {
			if (p.isKing()) {
				if (p.isBomb()) {
					img = "img/bomb-water-crowned.png";
				} else if (p.isShield()) {
					img = "img/shield-water-crowned.png";
				} else {
					img = "img/pawn-water-crowned.png";
				}
			} else {
				if (p.isBomb()) {
					img = "img/bomb-water.png";
				} else if (p.isShield()) {
					img = "img/shield-water.png";
				} else {
					img = "img/pawn-water.png";
				}
			}
		}
		return img;
	}

	/* Initializes a game
	 * Checks for mouse input or spacebar input
	 * and handles accordingly until win conditions
	 * are met */
	public static void main(String[] args) {
		Board playBoard = new Board(false);
		int boardSize = 8;
		StdDrawPlus.setXscale(0, boardSize);
		StdDrawPlus.setYscale(0, boardSize);
		while (true) {
			playBoard.drawBoard(boardSize);
			if (playBoard.numFirePieces == 0 || playBoard.numWaterPieces == 0) {
				playBoard.winner();
			} else if (StdDrawPlus.isNPressed()) {
				playBoard = new Board(false);
			} else if (StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (playBoard.canSelect(x, y)) {
					playBoard.select(x, y);
				}
			} else if (StdDrawPlus.isSpacePressed()) {
				if (playBoard.canEndTurn()) {
					playBoard.endTurn();
				}
			}
			StdDrawPlus.show(50);
		}
		

	}
}