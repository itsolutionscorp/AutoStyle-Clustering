public class Board {

	private Piece[][] pieces;
	private Integer[] selected = new Integer[] {null, null}; // [x, y]
	private int player = 0;
	private boolean moved = false;

	public static void main(String args[]) {
		//System.out.println("Welcome to the CS 61B Bomb Checkers game! Press 'n' at any time to restart.");
		Board board = new Board(false);
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);

		while(board.winner() == null) {
			board.drawBoard();
			if(StdDrawPlus.mousePressed()){
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if(board.canSelect(x, y)) {
					board.select(x, y);
				}
			}
			else if(StdDrawPlus.isSpacePressed() && board.canEndTurn()){
				board.endTurn();
			}
			else if(StdDrawPlus.isNPressed()) {
				String[] nargs = new String[0];
				Board.main(nargs);
			}
		}
		board.drawBoard();
		System.out.println("Winner: " + board.winner() + "!");
		
		// I wanted to be able to restart the game afterwards, but autograder said no.
		//System.out.println("Press 'n' to play again :)");
		/**boolean nPressed = false; // jank, but ag didn't like while-true
		while(!nPressed) {
			if(StdDrawPlus.isNPressed()) {
				nPressed = true;
			}
		}
		String[] nargs = new String[0];
		Board.main(nargs);**/ 
	}

	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[8][8];
		if(!shouldBeEmpty) {
			makePieces();
		}
	}

	private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (selected[0] != null && selected[1] != null && selected[0] == i && selected[1] == j) {
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, getPieceImage(pieces[i][j]), 1, 1);
                }
            }
        }
        StdDrawPlus.show(10);
    }

	private void makePieces() {
		for(int j = 0; j < 3; j++) {
			String type = (j == 0) ? "pawn" : ((j == 1) ? "shield" : "bomb");
			for(int i = 0; i < 4; i++) {
				int x = xGivenIJ(i, j);
				pieces[xGivenIJ(i, j)][j] = new Piece(true, this, x, j, type);
				x = xGivenIJ(i, 7 - j);
				pieces[x][7 - j] = new Piece(false, this, x, 7 - j, type);
			}
		}
	}

	private int xGivenIJ(int i, int j) { // helper for makePieces
		if(j % 2 == 0) {
			return i * 2;
		}
		return (i * 2) + 1;
	}

	private String getPieceImage(Piece piece) {
		String pieceType = piece.isBomb() ? "bomb" : (piece.isShield() ? "shield" : "pawn");
		String team = piece.isFire() ? "-fire" : "-water";
		String crowned = piece.isKing() ? "-crowned" : "";
		
		return "img/" + pieceType + team + crowned + ".png";
	}

	public Piece pieceAt(int x, int y) {
		if(outOfBounds(x, y)) {
			return null;
		}
		return pieces[x][y];
	}

	private Piece getSelected() {
		if(selected[0] == null) {
			return null;
		}
		return pieceAt(selected[0], selected[1]);
	}

	public boolean canSelect(int x, int y) {
		if(outOfBounds(x, y)) {
			return false;
		}
		else if(pieces[x][y] != null) {
			return (pieces[x][y].side() == player && moved == false);
		}
		else if(getSelected() != null) {
			if(!moved && (validMove(x, y) || validCap(x, y))) {
				return true;
			}
			else if(getSelected().hasCaptured() && validCap(x, y)) {
				return true;
			}
			return false;
		}
		return false;
	}

	public void select(int x, int y) {
		if(getSelected() != null && pieceAt(x, y) == null) {
			getSelected().move(x, y);
			moved = true;
			selected[0] = x;
			selected[1] = y;
		}
		else {
			selected[0] = x;
			selected[1] = y;
		}
	}

	private boolean validMove(int xf, int yf) {
		if(outOfBounds(xf, yf) || pieces[xf][yf] != null || !validDir(yf)) {
			return false;
		}
		return (abs(selected[0] - xf) == 1 && abs(selected[1] - yf) == 1);
	}

	private boolean validCap(int xf, int yf) {
		if(outOfBounds(xf, yf) || !(abs(selected[0] - xf) == 2 && abs(selected[1] - yf) == 2) || !validDir(yf)) {
			return false;
		}
		int avgx = (xf + selected[0])/2;
		int avgy = (yf + selected[1])/2;
		Piece capPiece = pieceAt(avgx, avgy);
		return (capPiece != null && capPiece.side() != player);
	}

	private boolean validDir(int y) { // returns false if non-kinged piece moving in wrong direction
		Piece sel = getSelected();
		if(sel.isKing()) {
			return true;
		}
		return (sel.isFire() && y > selected[1] || !sel.isFire() && y < selected[1]);
	}

	public void place(Piece p, int x, int y) {
		if(!outOfBounds(x, y)) {
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y) {
		if(outOfBounds(x, y)) {
			System.out.println("Tried to remove from out of bounds.");
			return null;
		}
		else if(pieces[x][y] == null) {
			System.out.println("There was nothing to remove.");
			return null;
		}
		else {
			Piece piece = pieces[x][y];
			pieces[x][y] = null;
			return piece;
		}
	}

	private boolean outOfBounds(int x, int y) {
		return (x < 0 || x > 8 || y < 0 || y > 8);
	}

	public boolean canEndTurn() {
		return moved;
	}

	public void endTurn() {
		moved = false;
		if(getSelected() != null) {
			getSelected().doneCapturing();
		}
		selected[0] = null;
		selected[1] = null;
		player = (player == 0) ? 1 : 0;
	}

	private int abs(int n) {
		if(n < 0) {
			return -n;
		}
		return n;
	}

	public String winner() {
		int fireCount = 0;
		int waterCount = 0;
		//for(int i = 0; i < 8; i++) {
		//	for(int j = 0; j < 8; j++) {
		for(Piece[] row : pieces) {
			for(Piece piece : row) {
				//Piece piece = pieceAt(i, j);
				if(piece != null) {
					if(piece.isFire()) {
						fireCount += 1;
					}
					else {
						waterCount += 1;
					}
				}
			}
		}
		if(fireCount == 0 && waterCount == 0) {
			return "No one";
		}
		if(fireCount == 0) {
			return "Water";
		}
		if(waterCount == 0) {
			return "Fire";
		}
		return null;
	}
}