public class Board {
	private int min;
	private int scale;
	private Piece[][] pArray;
	private int fPieces;
	private int wPieces;
	private boolean gameOver;
	private boolean fireTurn;
	private Piece selected;
	private int selectedX;
	private int selectedY;
	private boolean hasMoved;

	public static void main(String args[]) {
		Board b = new Board(false);
		b.initWindow();
		b.drawBoard();
		while (!b.gameOver) {
			if ((b.fPieces == 0) || (b.wPieces == 0)) {
				b.gameOver = true;
				break;
			}
			if (StdDrawPlus.mousePressed()) {
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				if (b.canSelect((int) x, (int) y)) {
					b.select((int) x, (int) y);
					b.drawBoard();
				}
			}
			if (StdDrawPlus.isSpacePressed()) {
				if (b.canEndTurn()) {
					b.endTurn();
					b.drawBoard();
				}
			}
		}
		System.out.println(b.winner());
	}

	public Board(boolean shouldBeEmpty) {
		// Initializes the 8x8 game board and pieces, sets turn to fire, starts the game
		this.min = 0;
		this.scale = 8;
		this.pArray = new Piece[this.scale][this.scale];
		if (!shouldBeEmpty) {
			this.initPieces();
		}
		this.countPieces();
		this.gameOver = false;
		this.fireTurn = true;
		this.hasMoved = false;
	}

	// Game logic methods

	public boolean canSelect(int x, int y) {
		// A piece at coordinates can be selected if it is the corresponding turn, and either nothing has been selected yet or nothing has been moved
		// If there is no piece at the coordinates, the target can be selected if there is something selected, the move is valid, and the player either has not moved or the piece has captured another piece on that turn
		if (!((x < this.min) || (y < this.min) || (x >= this.scale) || (y >= this.scale))) {
			if (pieceAt(x, y) != null) {
				if (this.hasMoved == true) {
					return false;
				}
				if (this.fireTurn == pieceAt(x, y).isFire()) {
					if ((this.selected == null) || (this.hasMoved == false)) {
						return true;
					}
				}
			}
			else if (pieceAt(x, y) == null) {
				if (this.selected != null) {
					if (((!this.hasMoved) && (validMove(this.selectedX, this.selectedY, x, y))) || ((this.selected.hasCaptured()) && (validMove(this.selectedX, this.selectedY, x, y)))) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		// Requires: target square is null, move is forward (unless king piece), move is 1 unit diagonal (unless there is an intermediate opposing piece available for capture, in which case it is 2 units)
		// A move can only be performed if the player has not yet moved; a capture can be performed even if a move has been made
		if (pieceAt(xf, yf) == null) {
			if ((pieceAt(xi, yi) != null) && (pieceAt(xi, yi).isFire())) {
				if ((Math.abs(xf - xi) == 1) && ((yf - yi) == 1) && (!this.hasMoved)) {
					return true;
				}
				if ((Math.abs(xf - xi) == 2) && ((yf - yi) == 2) && (pieceAt(xi + (xf - xi)/2, yi + (yf - yi)/2) != null) && (!pieceAt(xi + (xf - xi)/2, yi + (yf - yi)/2).isFire())) {
					return true;
				}
			}
			if ((pieceAt(xi, yi) != null) && (!pieceAt(xi, yi).isFire())) {
				if ((Math.abs(xf - xi) == 1) && ((yf - yi) == -1) && (!this.hasMoved)) {
					return true;
				}
				if ((Math.abs(xf - xi) == 2) && ((yf - yi) == -2) && (pieceAt(xi + (xf - xi)/2, yi + (yf - yi)/2) != null) && (pieceAt(xi + (xf - xi)/2, yi + (yf - yi)/2).isFire())) {
					return true;
				}
			}
			if (pieceAt(xi, yi) != null) {
				if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1) && (pieceAt(xi, yi).isKing()) && (!this.hasMoved)) {
					return true;
				}
				if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2) && (pieceAt(xi, yi).isKing()) && (pieceAt(xi + (xf - xi)/2, yi + (yf - yi)/2) != null) && (pieceAt(xi, yi).isFire() != pieceAt(xi + (xf - xi)/2, yi + (yf - yi)/2).isFire())) {
					return true;
				}
			}
		}
		return false;
	}

	// Piece control methods

	public void select(int x, int y) {
		// If a piece is selected and the target square is empty, moves the piece to the target square, sets hasMoved to true, and deselects the piece if it is a bomb that has exploded
		// If there is no piece at the target square or there is a piece but a different piece has already been selected, selects the target (null or piece)
		if ((this.selected != null) && (pieceAt(x, y) == null)) {
			this.selected.move(x, y);
			this.selectedX = x;
			this.selectedY = y;
			this.hasMoved = true;
			if ((this.selected.isBomb()) && (this.selected.hasCaptured())) {
				this.selected = null;
			}
		}
		else {
			this.selected = pieceAt(x, y);
			this.selectedX = x;
			this.selectedY = y;
		}
	}

	public void place(Piece p, int x, int y) {
		// Places a piece as long as the target is within the board and the piece exists; modifies the piece counter
		if ((x < this.min) || (y < this.min) || (x >= this.scale) || (y >= this.scale)) {
			return;
		}
		else if (p == null) {
			return;
		}
		else {
			this.pArray[x][y] = p;
			if (p.isFire()) {
				this.fPieces += 1;
			}
			else if (!p.isFire()) {
				this.wPieces += 1;
			}
		}
	}

	public Piece remove(int x, int y) {
		// Removes and returns piece at given coordinates; modifies the piece counter; prints a message if out of bounds
		if ((x < this.min) || (y < this.min) || (x >= this.scale) || (y >= this.scale)) {
			System.out.println("Coordinates of removal out of bounds.");
			return null;
		}
		if (pieceAt(x, y) == null) {
			System.out.println("No piece at location.");
			return null;
		}
		Piece p = this.pieceAt(x, y);
		pArray[x][y] = null;
		if (p.isFire()) {
			this.fPieces -= 1;
		}
		else if (!p.isFire()) {
			this.wPieces -= 1;
		}
		return p;
	}

	public Piece pieceAt(int x, int y) {
		// Returns the piece at target coordinates if within board
		if ((x < this.min) || (y < this.min) || (x >= this.scale) || (y >= this.scale)) {
			return null;
		}
		else {
			return this.pArray[x][y];
		}
	}

	// Game control methods

	private void initPieces() {
		// Initializes starting pieces
		for (int i = 0; i < this.scale; i = i + 2) {
			this.place(new Piece(true, this, i, 0, "pawn"), i, 0);
		}
		for (int i = 1; i < this.scale; i = i + 2) {
			this.place(new Piece(true, this, i, 1, "shield"), i, 1);
		}
		for (int i = 0; i < this.scale; i = i + 2) {
			this.place(new Piece(true, this, i, 2, "bomb"), i, 2);
		}
		for (int i = 1; i < this.scale; i = i + 2) {
			this.place(new Piece(false, this, i, 5, "bomb"), i, 5);
		}
		for (int i = 0; i < this.scale; i = i + 2) {
			this.place(new Piece(false, this, i, 6, "shield"), i, 6);
		}
		for (int i = 1; i < this.scale; i = i + 2) {
			this.place(new Piece(false, this, i, 7, "pawn"), i, 7);
		}
	}

	public boolean canEndTurn() {
		// A turn can be ended if the player has moved
		if ((this.hasMoved) || ((this.selected != null) && (this.selected.hasCaptured()))) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		// Changes the turn, resets hasMoved, resets the hasCaptured attribute of the previously moved piece, resets selected piece to null
		this.fireTurn = !this.fireTurn;
		this.hasMoved = false;
		if (this.selected != null) {
			this.selected.doneCapturing();
		}
		this.selected = null;
	}

	private void countPieces() {
		// Counts all pieces on the board
		this.fPieces = 0;
		this.wPieces = 0;
		for (int j = 0; j < this.scale; j++) {
			for (int i = 0; i < this.scale; i++) {
				if (this.pieceAt(i, j) != null) {
					if (this.pieceAt(i, j).isFire()) {
						this.fPieces += 1;
					}
					else {
						this.wPieces += 1;
					}
				}
			}
		}
	}

	public String winner() {
		// Returns the winner, or null if the game is ongoing
		if ((this.fPieces == 0) && (this.wPieces == 0)) {
			return "No one";
		}
		else if (this.fPieces == 0) {
			return "Water";
		}
		else if (this.wPieces == 0) {
			return "Fire";
		}
		return null;
	}

	// Graphics methods

	private void initWindow() {
		// Initializes the GUI
		StdDrawPlus.setCanvasSize();
		StdDrawPlus.setXscale(this.min, this.scale);
		StdDrawPlus.setYscale(this.min, this.scale);
	}

	private void drawBoard() {
		// Draws the checkerboard uses drawPiece to draw pieces; if a box is selected, colors it white
		for (int j = 0; j < this.scale; j++) {
			for (int i = 0; i < this.scale; i++) {
				if ((this.selected != null) && (i == this.selectedX) && (j == this.selectedY)) {
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
				else if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				drawPiece(this.pieceAt(i, j), i, j);
			}
		}
	}

	private void drawPiece(Piece p, int i, int j) {
		// Draws every piece
		if (p != null) {
			if (p.isFire()) {
				if (p.isKing()) {
					if (p.isBomb()) {
						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png", 1, 1, 0);
					}
					else if (p.isShield()) {
						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1, 1, 0);
					}
					else {
						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png", 1, 1, 0);
					}
				}
				else {
					if (p.isBomb()) {
						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1, 0);
					}
					else if (p.isShield()) {
						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1, 0);
					}
					else {
						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1, 0);
					}
				}					
			}
			else {
				if (p.isKing()) {
					if (p.isBomb()) {
						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png", 1, 1, 0);
					}
					else if (p.isShield()) {
						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png", 1, 1, 0);
					}
					else {
						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png", 1, 1, 0);
					}
				}
				else {
					if (p.isBomb()) {
						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1, 0);
					}
					else if (p.isShield()) {
						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1, 0);
					}
					else {
						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1, 0);
					}
				}
			}
		}
	}

}