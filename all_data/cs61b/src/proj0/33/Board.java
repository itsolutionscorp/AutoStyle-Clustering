/** This is a board for the checkers game
 *
 * Special implementations:
 * Highlighted square will change color in the GUI
 * Restart game with n key in the GUI
 * Stalemate NOT IMPLEMENTED
 */

public class Board {	
	private Piece[][] pieces; //x , y
	private int firePieces;
	private int waterPieces;
	private static int scale = 8;
	private int sX = -1;	//selected x position
	private int sY = -1;	//selected y position
	private boolean playerIsFire;
	private boolean moved = false;
	private Piece selected = null;	//selected Piece

	/** Constructor
 	 * if shouldBeEmpty is true, initializes and empty Board
 	 * Otherwise, initializes a Board with the default config.
 	 */
	public Board(boolean shouldBeEmpty) {
		pieces = new Piece[scale][scale];
		firePieces = 0;
		waterPieces = 0;
		if (!shouldBeEmpty) {
			defaultConfig(pieces);
		}
		else {
			for (int i = 0; i < pieces.length;i++) {
				for (int j = 0; j < pieces[i].length; j++) {
					pieces[i][j] = null;
				}
			}
		}
		playerIsFire = true;
	}

	/** Takes in matrix for playing board pieces
	 *  Sets up the default configuration
	 */
	private void defaultConfig(Piece[][] matrix) {
		int mL = matrix.length;
		for (int i = 0; i < mL; i++) {
			if (i % 2 == 0) {
				waterPieces += 1;
				firePieces += 2;
				matrix[i][0] = new Piece(true, this, i, 0, "pawn"); //fire pawns
				matrix[i][2] = new Piece(true, this, i, 2, "bomb"); //fire bombs
				matrix[i][mL-2] = new Piece(false, this, i, mL-2, "shield"); //water shields
			}
			else { //(i % 2 == 1)
				waterPieces += 2;
				firePieces += 1;
				matrix[i][1] = new Piece(true, this, i, 1, "shield"); //fire shields
				matrix[i][mL-3] = new Piece(false, this, i, mL-3, "bomb"); //water bombs
				matrix[i][mL-1] = new Piece(false, this, i, mL-1, "pawn"); //water pawns	
			}
		}
	}

	/** 
	 * Returns the piece at a given point
	 * Returns null if no piece found or point is OoB
	 */	
	public Piece pieceAt(int x, int y) {
		if (x >= scale || y >= scale || x < 0 || y < 0) return null;
		return pieces[x][y];
	}

	
	public boolean canSelect(int x, int y) {
		if (x >= scale || y >= scale || x < 0 || y < 0) return false; //out of bounds
		if ((x+y) % 2 != 0) return false; //non playable spaces, must be diagonal
		if (pieceAt(x,y) != null) {// square has a piece
			//player must select their side's piece
			if (pieceAt(x,y).isFire() != playerIsFire) return false;
			//if player has not selected, then moved is false;
			if (selected == null || !moved) return true;
			return false;	
		}
		else { //Square does not have piece
			//Piece already selected, all conditions covered in validMove
			if (selected != null && selected == pieceAt(sX,sY)) return validMove(sX,sY,x,y);	
			return false;
		}
	}

	/**
	 * Check if requested move is valid
	 * 
	 */	
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xf >= scale || yf >= scale || xf < 0 || yf < 0) return false; //out of bounds
		if (xi == xf || yi == yf) return false; //Same spot
		if (pieceAt(xf,yf) != null) return false; //must be empty; covers same place
		Piece target = pieceAt(xi,yi); //should never be null b/c of canSelect
		//adjacent condition (1 away)
		if (!moved && Math.abs(xi-xf)==1) {
			//checks if king
			if (target.isKing())
				return Math.abs(yi-yf)==1;
			else if (target.isFire())
				return (yf-yi == 1);
			return (yf-yi == -1);
		}
		//capture condition (2 away)
		if (Math.abs(xi-xf)==2){
			//Piece cannot capture if has only moved 1
			if (moved && !target.hasCaptured()) return false; //***
			//checks for capture
			int midX = (int)((xi+xf)/2);
			int midY = (int)((yi+yf)/2);
			if (pieceAt(midX,midY) == null) return false;
			//checks if opponent
			if (pieceAt(midX,midY).isFire() == target.isFire()) return false;
			//checks if self is king
			if (target.isKing())
				return Math.abs(yi-yf)==2;
			else if (target.isFire())
				return (yf-yi == 2);
			return (yf-yi == -2);
		}
		return false; //otherwise
	}

	/** assumes canSelect is true already 
	 * 	i.e. CALL CAN SELECT B/F THIS METHOD
	 */
	//completed
	public void select(int x, int y) {
		if (x >= scale || y >= scale || x < 0 || y < 0) {
			//System.out.println("select: out of bounds selection");
			return;
		}
		if (pieceAt(x,y) != null){
			selected = pieceAt(x,y);
		}
		else  { //empty space
			moved = true;
			selected.move(x,y); //piece will re-place itself		
		}
		sX = x;
		sY = y;
	}
	

	//Completed
	public void place(Piece p, int x, int y){
		if (x >= scale || y >= scale || p == null || x < 0 || y < 0) return; //extranesou cases
		pieces[x][y] = p;
		if (p.isFire()) firePieces+=1;
		else			waterPieces+=1;
	}
	
	//Completed
	public Piece remove(int x, int y) {
		if (x >= scale  || y >= scale || x < 0 || y < 0) {
			//System.out.println("Requested remove is Out of Bounds!");
			return null;
		}
		Piece temp = pieceAt(x,y);
		if (temp == null){
			//System.out.println("No Piece found!");
			return temp;
		}
		//else if (temp == selected) selected = null;
		pieces[x][y] = null;
		if (temp.isFire()) firePieces-=1;
		else 			   waterPieces-=1;
		return temp;
	}
	
	/**
	 * To end a turn, Player must have moved or performed a capture
	 */
	public boolean canEndTurn() {
		if (moved || (selected != null && selected.hasCaptured())) {
			//System.out.println("canEndTurn: Can end turn!");
			return true;
		}
		//System.out.println("canEndTurn: Cannot end turn!");
		return false;
	}

	/**
	 * Called at the end of each turn
	 * assumes that canEnTurn is already true
	 */
	public void endTurn() {
		playerIsFire = !playerIsFire;
		sX = -1;
		sY = -1;
		moved = false;
		if (selected != null) selected.doneCapturing();
		selected = null;
		//System.out.println("endTurn: Turn ended!");
	}


	public String winner() {
		if (waterPieces == firePieces && waterPieces == 0) {
			return "No one"; //tied game
		}
		else if (waterPieces == 0) {
			return "Fire";
		}
		else if (firePieces == 0){
			return "Water";
		}
		return null; //still pieces left
	}

	private String imageString(Piece p) {
		StringBuilder image = new StringBuilder("img/");
		if 		(p.isShield())	{image.append("shield");}
		else if (p.isBomb())	{image.append("bomb");}
		else 					{image.append("pawn");}
		image.append("-");
		if (p.isFire()) {image.append("fire");}
		else 			{image.append("water");}
		if (p.isKing()) {image.append("-crowned");}
		image.append(".png");
		return image.toString();
	}

	/* draw a board of size n*/
	private void drawBoard(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == sX && j == sY) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else 				  	   StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				if (pieceAt(i,j) != null) 
					StdDrawPlus.picture(i + .5, j + .5, imageString(pieceAt(i,j)), 1, 1);
			}
		}
	}


	/* starts a GUI supported version of the game */
	public static void main(String[] args) {
		Board b = new Board(false);
		StdDrawPlus.setXscale(0, scale);
		StdDrawPlus.setYscale(0, scale);
		while (b.winner() == null) { 
			b.drawBoard(scale);
			if (StdDrawPlus.isNPressed()) {
				b = new Board(false);
			}
			else if (StdDrawPlus.mousePressed()) {
				/* Extract coordinates, canSelect, select */
            	int selectedX = (int) StdDrawPlus.mouseX();
            	int selectedY = (int) StdDrawPlus.mouseY();
            	if (b.canSelect(selectedX,selectedY)) b.select(selectedX,selectedY);
            	//else System.out.println("Selection error! " + selectedX + ", " + selectedY);
            }
            else if(StdDrawPlus.isSpacePressed()) {
            	if (b.canEndTurn()) b.endTurn();
            }
            b.drawBoard(scale);
            StdDrawPlus.show(10);
        }
        System.out.println(b.winner());
	}
}