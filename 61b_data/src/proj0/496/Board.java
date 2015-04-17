public class Board {

	private static Piece[][] pieces;
	private static final int N = 8;
	private boolean selected = false;
	private boolean moved = false;
	private int selectedX;
	private int selectedY;
	private boolean fireIsPlaying = true;
	private int firePieces = 0;
	private int waterPieces = 0;
	private boolean initialBoard = true;

	public static void main(String[] args) {
		// New instance of Board.
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		Board b = new Board(false);
		
		while(true) {
			b.defaultBoard(N);
			// When mouse is pressed, check if slot can be selected
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) {
                	b.select(x, y);
                }
            }
            // Space pressed = end of current turn
        	if (StdDrawPlus.isSpacePressed()) {
        		if (b.canEndTurn()) {
        			if (b.pieceAt(b.selectedX, b.selectedY) != null) {
        				b.pieceAt(b.selectedX, b.selectedY).doneCapturing();
        			}
        			b.endTurn();
        		}
        	}
            StdDrawPlus.show(100);
        }
	}

	// --- Generate an empty board --- //
    private void drawEmptyBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }

    // --- Default setting of a board --- //
    private void defaultBoard(int N) {
    	this.drawEmptyBoard(N);
    	this.drawRedBlue(N);
    }

    // --- Draw fire and water figures --- //
    private void drawRedBlue(int N) {
    	String type = "";
    	String crowned;
        for (int j = 0; j < N; j++) {
        	for (int i = 0; i < N; i++) { 
        		Piece p = pieceAt(i, j);
        		if (p != null) {
	        		if(!p.isKing()) crowned = "";
	        		else crowned = "-crowned";

        			if(p.isFire()) {
        				if (p.isBomb()) type = "bomb-fire" + crowned + ".png";
        				else if (p.isShield()) type = "shield-fire" + crowned + ".png";
        				else type = "pawn-fire" + crowned + ".png";
        			}
        			else if (!p.isFire()) {
        				if (p.isBomb()) type = "bomb-water" + crowned + ".png";
        				else if (p.isShield()) type = "shield-water" + crowned + ".png";
        				else type = "pawn-water" + crowned + ".png";
        			}		
        			StdDrawPlus.picture(i + .5, j + .5, "img/" + type, 1, 1);
        		}	   		
           	}	
		}
	}

	// --- Board constructor. Initializes pieces at proper locations. --- //
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[N][N];
		int x = 0;
		boolean fire = true;
		String[] types = {"pawn", "shield", "bomb", "" , "", "bomb", "shield", "pawn" };
		if (!shouldBeEmpty) {
			// Set fire pieces
			for (int y = 0; y < 3; y+=1) {
				if (y == 1) x = 1;
				else x = 0;
			while(x < N) {
				pieces[x][y] = new Piece(fire, this, x, y, types[y]);
				x += 2;
				}
			}
			// Set water pieces
			for (int y = 5; y < N ; y +=1) {
				if (y == 5 || y == (N-1)) x = 1;
				else x = 0;

				while(x < N) {
				pieces[x][y] = new Piece(!fire, this, x, y, types[y]);
				x += 2;
				}
			}
		firePieces = 12;
		waterPieces = 12;	
		initialBoard = false;
		}
	}

	// --- Returns piece at current (x, y) or null --- //
	public Piece pieceAt(int x, int y) {
		if ((x < N && y < N && x >=0 && y>=0) && pieces[x][y] != null) {
			return pieces[x][y];
		}
		return null;
	}

	// --- Places a piece at coordinates (x, y) --- //
	public void place(Piece p, int x, int y) {
		// Check if index out of bounds/ null piece
		if (x < 0 || y < 0 || x >= N || y >= N || p == null) return;
		// Iterate through the board to check if p already exists
		for (int j = 0; j < N; j++) {
			for (int i = 0; i < N; i++) {
				if (pieceAt(x, y) == p) {
					this.remove(x, y);
				} 
			}
		}
		// Place p at (x, y)
		pieces[x][y] = p;
		if (p.isFire()) firePieces += 1;
		else waterPieces += 1;
		moved = true;
	}


	// --- Returns true if the square at (x, y) can be selected. --- //
	public boolean canSelect(int x,  int y) {
		// Check if current player can select a piece
		Piece p = pieceAt(x, y);
		Piece current = pieceAt(selectedX, selectedY);
			// Can select a piece 
			if (p!=null && ((p.isFire() && fireIsPlaying) || (!p.isFire() && !fireIsPlaying))) {
				if (selected==false || (selected && !moved)) {
				return true;
				}
			}
			// Can select an empty square
			else if (p == null && this.selected && this.validMove(selectedX, selectedY, x, y) && !this.moved) {
				return true;
			}
			else if (current != null && p == null && this.selected && current.hasCaptured() && this.moved && this.validMove(selectedX, selectedY, x, y)) {
				return true;

			}
		return false;
	}

	// --- Check if the move from (xi, yi) to (xf, yf) is valid. --- //
	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece p = pieceAt(xi, yi);
		int dx = xf - xi;
		int dy = yf - yi;

		// Piece has to move in diagonal
		// Check if (xf, yf) is out of bounds
		if (xi == xf || yi == yf || xf >= N || yf >= N || p == null) {
			return false;
		}
		// Check if (xf, yf) is not occupied
		if (pieceAt(xf, yf) == null) {
			// If piece is not crowned, it can only move one direction
			if (!p.isKing()) {
				// Jeez, this is so fucked up --- Denero wouldn't be happy ----  //
				if ((p.isFire() && ((dy == 1 && Math.abs(dx) == 1)|| ((dy == 2 && Math.abs(dx) == 2) && pieceAt(xi+(dx/2), yi+(dy/2)) != null)))
					|| (!p.isFire() && ((dy == -1 && Math.abs(dx) == 1) || ((dy == -2 && Math.abs(dx) == 2) && pieceAt(xi+(dx/2), yi+(dy/2)) != null)))) {
					return true;
				}
			}
			else if (p.isKing()) {
				int cDX = Math.abs(dx);
				int cDY = Math.abs(dy);
				Piece capturedPiece = pieceAt(xi+(dx/2), yi+(dy/2));
				if ((cDY == 1 && cDX == 1)|| ( (cDY == 2 && cDX == 2) && capturedPiece != null && (capturedPiece.isFire() == !p.isFire()))) {
					return true;
				}
			}
		}
		// Any other move is invalid
		return false;
		}

	// --- Select either an empty slot or a piece. --- //
	public void select(int x, int y) {
		// Move previously selected piece.
		if (pieceAt(x, y) == null && selected) {
			pieceAt(selectedX, selectedY).move(x, y);
			moved = true;
			boolean result = pieceAt(x, y) == null;
		}
		// Select a piece.
		else {
			this.selected = true;
			moved = false;
		}
		this.selectedX = x;
		this.selectedY = y;
	}

	// --- Removes a piece at appropriate location --- //
	public Piece remove(int x, int y) {
		Piece removed;
		if (x < 0 || y < 0 || x >= N || y >= N || pieceAt(x, y) == null) {
			return null;
		}
		else {
			removed = pieceAt(x, y);
			if (removed != null) {
				if (removed.isFire()) firePieces -= 1;
				else if (!removed.isFire()) waterPieces -= 1;
			}
			pieces[x][y] = null;
			return removed;
		}
	}
	// --- Checks if a current player performed an action --- //
	public boolean canEndTurn() {
		if (moved) return true;
		return false;	
	}

	// --- Switch turns. --- //
	public void endTurn() {
		fireIsPlaying = !fireIsPlaying;
		selected = false;
		moved = false;
	}

	// --- Returns an appripriate winner. --- //
	public String winner() {
		if(firePieces>0 && waterPieces>0) return null;
		else if (firePieces == 0 && waterPieces != 0) return "Water";
		else if (waterPieces == 0 && firePieces!= 0) return "Fire";
		//else if (waterPieces == 0 && firePieces == 0) return "No one";
		else return "No one";
	}
}

