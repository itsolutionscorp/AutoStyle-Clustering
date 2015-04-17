
public class Board {

	private Piece[][] pieces;
	private boolean fireTurn;
	// Coordinates of the selected square
	private int[] selected = new int[2]; 
	private boolean hasPieceCaptured;
	// Coordinates of where the most recently moved piece is
	private int[] pieceMovedThisTurn = new int[2]; 
	private int N;

	public Board(boolean shouldBeEmpty) {
		fireTurn = true;
		selected[0] = -1; // Set to -1 for no selected square
		hasPieceCaptured = false;
		pieceMovedThisTurn[0] = -1; // Set to null for no piece Moved
		selected[0] = -1;
		pieceMovedThisTurn[0] = -1;
		//Render board of size 8
        N = 8;
        pieces = new Piece[N][N];
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

		if(!shouldBeEmpty) { // If pieces should be added
			for (int i = 0; i < N; i++) {
				if (i % 2 == 0) {
					pieces[i][0] = new Piece(true, this, i, 0, "pawn");
					pieces[i][2] = new Piece(true, this, i, 2, "bomb");
					pieces[i][N-2] = new Piece(false, this, i, N-2, "shield");
				} else {
					pieces[i][N-1] = new Piece(false, this, i, N-1, "pawn");
					pieces[i][N-3] = new Piece(false, this, i, N-3, "bomb");
					pieces[i][1] = new Piece(true, this, i, 1, "shield");
				}
			}
		}
		
	}

	private void drawBoard(int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i + j) % 2 == 0) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else {
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
			}
		}
		for (int i = 0; i < N; i ++) {
			for (int j = 0; j < N; j ++) {
				if (pieces[i][j] != null) {
					Piece p = pieces[i][j];
					if (p.isBomb() && p.isFire() && p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
					if (p.isBomb() && p.isFire() && !p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
					if (p.isBomb() && !p.isFire() && p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
					if (p.isBomb() && !p.isFire() && !p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
					if (p.isShield() && p.isFire() && p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
					if (p.isShield() && p.isFire() && !p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
					if (p.isShield() && !p.isFire() && p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
					if (p.isShield() && !p.isFire() && !p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
					if (!p.isBomb() && !p.isShield() && p.isFire() && p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
					if (!p.isBomb() && !p.isShield() && p.isFire() && !p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
					if (!p.isBomb() && !p.isShield() && !p.isFire() && p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
					if (!p.isBomb() && !p.isShield() && !p.isFire() && !p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
					
				}
			}
		}
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.square(selected[0] + .5, selected[1] + .5, .5);
	}
	
	public static void main(String[] args){
		Board b = new Board(false);
		while(true) {
			int counter = 0;
			while(counter < 100) {
	            b.drawBoard(8);
	            if (StdDrawPlus.mousePressed()) {
	                int x = (int)StdDrawPlus.mouseX();
	                int y = (int)StdDrawPlus.mouseY();
	                if (b.canSelect(x, y)) {
	                	b.select(x, y);
	                }
	                
	                
	            }
	            if (StdDrawPlus.isSpacePressed()) {
	               	if (b.canEndTurn()) {
	               		b.endTurn();
	               	}
	            }
	            StdDrawPlus.show(10);
	            counter++;
			}
			String winner = b.winner();
			if (winner != null) {
				System.out.println(winner + " won!");
			}
        }
	}
	
	public Piece pieceAt(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) return null;
		return pieces[x][y];
	}
	
	public boolean canSelect(int x, int y) {
		if(pieceAt(x, y) != null && pieceAt(x, y).isFire() == fireTurn) { // For a square with a piece
			if (selected[0] == -1 || pieceMovedThisTurn[0] == -1)
				return true;
		} else { // For an empty square
			// Cannot move normally if it has captured
			if (hasPieceCaptured && Math.abs(pieceMovedThisTurn[0] - x) == 1)
				return false;
			Piece selectedPiece = pieceAt(selected[0], selected[1]);
			if (selectedPiece != null 
					&& pieceMovedThisTurn[0] == -1 
					&& validMove(selected[0], selected[1], x, y))
				return true;
			
			if (selectedPiece != null
					&& hasPieceCaptured == true
					&& validMove(selected[0], selected[1], x, y))
				return true;
		}
		return false;
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf) {
		// False if target is out of bounds
		if (xf < 0 || xf >= N || yf < 0 || yf >= N) return false;
		
		// False if target is anything other than one or two 
		// squares diagonally
		if (Math.abs(xf - xi) != Math.abs(yf - yi) ) return false;
		if (Math.abs(xf - xi) < 1 || Math.abs(xf - xi) > 2) return false;
		
		// False if there is no piece at xi, yi
		if (pieces[xi][yi] == null) return false;
		
		Piece piece = pieceAt(xi, yi);
		
		// False if piece is unkinged fire moving down or 
		// unkinged water moving up
		if (piece.isKing() == false){
			if (piece.isFire() && yf < yi) return false;
			if (!piece.isFire() && yf > yi) return false;
		}
		
		// False if there is a piece at the target;
		if (pieceAt(xf, yf) != null) return false;
		
		// If it is moving two squares 
		Piece pieceBetween = pieceAt(Math.min(xi, xf) + 1, Math.min(yi, yf) + 1);
		if (Math.abs(yf - yi) == 2) {
			// False if there is no piece in between
			if (pieceBetween == null) return false;
			// False if the piece in between is an ally
			if (piece.isFire() == pieceBetween.isFire()) return false;
		}
		
		return true;
	}
	
	private boolean validMoveAfterCapture(int xi, int yi, int xf, int yf) {
		// False if target is out of bounds
		if (xf < 0 || xf >= N || yf < 0 || yf >= N) return false;
		
		// False if target is anything other than one or two 
		// squares diagonally
		if (Math.abs(xf - xi) != Math.abs(yf - yi) ) return false;
		if (Math.abs(xf - xi) < 1 || Math.abs(xf - xi) > 2) return false;
		
		// False if there is no piece at xi, yi
		if (pieces[xi][yi] == null) return false;
		
		Piece piece = pieceAt(xi, yi);
		
		// False if piece is unkinged fire moving down or 
		// unkinged water moving up
		if (piece.isKing() == false){
			if (piece.isFire() && yf < yi) return false;
			if (!piece.isFire() && yf > yi) return false;
		}
		
		// False if there is a piece at the target;
		if (pieceAt(xf, yf) != null) return false;
		
		// If it is moving two squares 
		Piece pieceBetween = pieceAt(Math.min(xi, xf) + 1, Math.min(yi, yf) + 1);
		if (Math.abs(yf - yi) == 2) {
			// False if there is no piece in between
			if (pieceBetween == null) return false;
			// False if the piece in between is an ally
			if (piece.isFire() == pieceBetween.isFire()) return false;
		}
		
		return true;
	}

	public void select(int x, int y) {
		if (selected[0] != -1 && validMove(selected[0], selected[1], x, y)) {
			pieceAt(selected[0], selected[1]).move(x, y);
			// Update all tracking variables
	        pieceMovedThisTurn[0] = x;
	        pieceMovedThisTurn[1] = y;
	        //If the piece moved two spaces, then it captured something
	        if (Math.abs(selected[0] - x) == 2) {
	        	hasPieceCaptured = true;
	        }
		}
		
		selected[0] = x;
		selected[1] = y;
	}

	public void place(Piece p, int x, int y) {
		if (p == null) return;
		if (x < 0 || x >= N || y < 0 || y >= N) return;
		pieces[x][y] = p;
		return;
	}
	
	public Piece remove(int x, int y) {
		if (x < 0 || x >= N || y < 0 || y >= N) {
			System.out.println("remove() input out of bounds!");
			return null;
		}
		if (pieces[x][y] == null){
			System.out.println("remove() - no piece at specified coordinates!");
			return null;
		}
		Piece returnedPiece = pieces[x][y];
		pieces[x][y] = null;
		return returnedPiece;
	}
	
	public boolean canEndTurn() {
		return (pieceMovedThisTurn[0] != -1 || hasPieceCaptured);
	}
	
	public void endTurn() {
		if (fireTurn) fireTurn = false;
		else fireTurn = true;
		hasPieceCaptured = false;
		Piece mostRecentPiece = pieceAt(pieceMovedThisTurn[0], pieceMovedThisTurn[1]);
		if (mostRecentPiece != null)
			mostRecentPiece.doneCapturing();
		pieceMovedThisTurn[0] = -1;
		selected[0] = -1;
		
	}
	
	public String winner() {
		int fireCount = 0;
		int waterCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Piece p = pieceAt(i, j);
				if (p != null && p.isFire()) {
					fireCount++;
				}
				else if (p != null && !p.isFire()) {
					waterCount++;
				}
			}
		}
		if (fireCount == 0 && waterCount == 0)
			return "No one";
		if (fireCount > 0 && waterCount > 0)
			return null;
		if (fireCount > 0)
			return "Fire";
		if (waterCount > 0)
			return "Water";
		return null;
	}
}
