/**
 *  @author William Wang
 */

public class Board{

	/** Keeps track of pieces on board. */
	private Piece[][] pieces;

	/** Keeps track of size of board. */
	private int size = 8;

	/** Keeps track of the current player (fire - 0, water - 1) */
	private int who = 0;

	/** The number of fire pieces remaining. Used to determine winner. */
	private int numFire = 0;

	/** The number of water pieces remaining. Used to determine winner. */
	private int numWater = 0;

	/** The currently selected piece. */
	private Piece selectedPiece = null;

	/** The x-coodinate of the currently selected piece. */
	private int selectedX = -1;

	/** The y-coordinate of the currently selected piece. */
	private int selectedY = -1;

	/** Keeps track of whether or not the current piece has moved. */
	private boolean moved = false;

	public Board(boolean shouldBeEmpty){
		pieces = new Piece[size][size];
		if (!shouldBeEmpty){
			initPieces();
		}
	}

	private void initPieces(){
		/** Creates initial board position */
		for (int i = 0; i < size; i += 2){
			place(new Piece(true, this, i, 0, "pawn"), i, 0);
		}
		for (int i = 1; i < size; i += 2){
			place(new Piece(true, this, i, 1, "shield"), i, 1);
		}
		for (int i = 0; i < size; i += 2){
			place(new Piece(true, this, i, 2, "bomb"), i, 2);
		}
		for (int i = 1; i < size; i += 2){
			place(new Piece(false, this, i, 5, "bomb"), i, 5);
		}
		for (int i = 0; i < size; i += 2){
			place(new Piece(false, this, i, 6, "shield"), i, 6);
		}
		for (int i = 1; i < size; i += 2){
			place(new Piece(false, this, i, 7, "pawn"), i, 7);
		}
	}

	public Piece pieceAt(int x, int y){
		/** Returns the piece at (x, y) */
		if(!isValidPos(x, y)){
			return null;
		}
		return pieces[x][y];
	}

	public void place(Piece p, int x, int y){
		/** Places a piece at the provided coordinates */
		if(p != null && isValidPos(x, y)){
			if(!hasSelected()){
				if(p.isFire()){
					numFire++;
				}
				else{
					numWater++;
				}
			}
			int[] pieceLoc = searchForPiece(p);
			if(pieceLoc != null){
				// Remove the piece from the board
				pieces[pieceLoc[0]][pieceLoc[1]] = null;
			}
			pieces[x][y] = p;
		}
	}

	private int[] searchForPiece(Piece p){
		/** Searches for a given piece in the board; if
		it exists, returns a 2-element array of the position. */
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				if(pieceAt(i, j) == p){
					return new int[]{i, j};
				}
			}
		}
		return null;
	}

	private boolean isValidPos(int x, int y){
		/** Returns if the position is on the board */
		return x < size && y < size && x >= 0 && y >= 0;
	}

	public boolean canSelect(int x, int y){
		/** Returns if the square can be selected */
		Piece p = pieceAt(x, y);
		if(p != null){
			if(p.side() != who){
				return false;
			}
			if(!hasSelected()){
				return true;
			}
			else if(hasSelected() && !moved){
				return true;
			}
			return false;
		}
		else{
			if(!hasSelected()){
				return false;
			}
			if(hasSelected()){
				if(!moved){
					return validMove(selectedX, selectedY, x, y);
				}
				// Multicapture
				else if(selectedPiece.hasCaptured() && Math.abs(x - selectedX) == 2){
					return validMove(selectedX, selectedY, x, y);
				}
			}
		}
		return false;
	}

	private boolean hasSelected(){
		/** Returns if the player has currently selected a piece */
		return selectedPiece != null;
	}

	public void select(int x, int y){
		/** Selects the square at (x, y). Assumes that canSelect(x, y)
		is true. */
		if(pieceAt(x, y) != null){
			selectedPiece = pieceAt(x, y);
			selectedX = x;
			selectedY = y;
		}
		else{
			if(validMove(selectedX, selectedY, x, y)){
				selectedPiece.move(x, y);
				selectedX = x;
				selectedY = y;
				moved = true;
			}
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf){
		/** Helper method for canSelect. Returns if a move is legal. */
		if (!(isValidPos(xi, yi) && isValidPos(xf, yf))){
			return false;
		}
		if (Math.abs(xf - xi) > 2 || Math.abs(yf - yi) > 2){
			return false;
		}
		if (pieceAt(xf, yf) != null || pieceAt(xi, yi) == null){
			return false;
		}
		Piece p = pieceAt(xi, yi);
		int player = p.side();
		if (p.isKing()){ // Can move any direction
			if (Math.abs(xi - xf) == 1 && Math.abs(yi - yf) == 1){
				return true;
			}
			int xmid = (xi + xf) / 2;
			int ymid = (yi + yf) / 2;
			if (Math.abs(yi - yf) == 2 && Math.abs(xi - xf) == 2){
				return checkCapture(xmid, ymid, player);
			}
			return false;
		}
		/* moveVal allows movement in both directions to be
		represented by one function */
		int moveVal = 1;
		if (p.side() == 1){
			moveVal = -1;
		}
		if (yi + moveVal == yf && Math.abs(xi - xf) == 1){
			return true;
		}
		int xmid = (xi + xf) / 2;
		int ymid = (yi + yf) / 2;
		if (yi + 2 * moveVal == yf && Math.abs(xi - xf) == 2){
			return checkCapture(xmid, ymid, player);
		}
		return false;
	}

	private boolean checkCapture(int x, int y, int player){
		/** Checks if a capture is valid: the captured piece must
		exist and be of the opposite side. */
		Piece p = pieceAt(x, y);
		if (p == null){
			return false;
		}
		return p.side() + player == 1;
	}

	public Piece remove(int x, int y){
		/** Removes the piece at (x, y) if possible. */
		if(!isValidPos(x, y)){
			System.out.println("Out of bounds: Piece could not be removed from (" + x + ", " + y + ")");
			return null;
		}
		else if(pieceAt(x, y) == null){
			System.out.println("Tried to remove piece from (" + x + ", " + y + ") when there was no piece");
			return null;
		}
		Piece removed = pieceAt(x, y);
		pieces[x][y] = null;
		if(removed.isFire()){
			numFire--;
		}
		else{
			numWater--;
		}
		return removed;
	}

	public boolean canEndTurn(){
		/** Returns if the current player can end their turn. */
		if(!hasSelected()){
			return false;
		}
		return (moved || selectedPiece.hasCaptured());
	}

	public void endTurn(){
		/** Changes player and resets the current player's state variables. */
		who = 1 - who;
		selectedX = -1;
		selectedY = -1;
		if(selectedPiece != null){
			selectedPiece.doneCapturing();			
		}
		selectedPiece = null;
		moved = false;
	}

	public String winner(){
		/** Returns a string indicating the winner of the game. */
		if(numFire == 0 && numWater == 0){
			return "No one";
		}
		else if(numFire == 0){
			return "Water";
		}
		else if(numWater == 0){
			return "Fire";
		}
		return null;
	}

	private String getImgStr(Piece p){
		/** Gets the image string of a piece for drawing purposes. */
		String type = "";
		String team = "";
		String crowned = "";
		if (p.isBomb()){
			type = "bomb";
		}
		else if (p.isShield()){
			type = "shield";
		}
		else{
			type = "pawn";
		}
		if(p.isFire()){
			team = "-fire";
		}
		else{
			team = "-water";
		}
		if(p.isKing()){
			crowned = "-crowned";
		}
		return "img/" + type + team + crowned + ".png";
	}

	private void drawBoard(){
		/** Draws the current board. Called in a loop in main. */
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				if ((i + j) % 2 == 0){
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
                else{
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if(i == selectedX && j == selectedY){
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(selectedX + .5, selectedY + .5, .5);
                }
                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, getImgStr(pieces[i][j]), 1, 1);
                }
			}
		}
	}

	public static void main(String[] args){
		Board b = new Board(false);
		StdDrawPlus.setXscale(0, b.size);
		StdDrawPlus.setYscale(0, b.size);
		while(true) { // GUI loop
            if (StdDrawPlus.mousePressed()) {
               	int x = (int)StdDrawPlus.mouseX();
               	int y = (int)StdDrawPlus.mouseY();
               	if(b.canSelect(x, y)){
               	b.select(x, y);
                }
            }
            b.drawBoard();
            if(StdDrawPlus.isSpacePressed()){
            	if(b.canEndTurn()){
            		b.endTurn();
            	}
            }
            StdDrawPlus.show(20);
            if(b.winner() != null){
            	break;
            }
        }
        System.out.println(b.winner());
	}
}
