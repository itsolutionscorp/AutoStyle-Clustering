/**
 * @author Harry He
 */
public class Board {
	private static final int SIZE_ = 8;
	private Piece[][] pieces_;
	private boolean isFireTurn_;
	private int selectedX_;
	private int selectedY_;
	private boolean hasPieceMoved_;
	
	public Board(boolean shouldBeEmpty) {
		pieces_ = new Piece[SIZE_][SIZE_];
		clearSelection();
		isFireTurn_ = true;
		if (shouldBeEmpty) {
			return;
		}
		for (int i = 0; i < SIZE_; i+=2) {
			pieces_[i][0] = new Piece(true, this, i, 0, "pawn");//Fire normal
			pieces_[i][2] = new Piece(true, this, i, 2, "bomb");//Fire bomb 
			pieces_[i][SIZE_-2] = new Piece(false, this, i, SIZE_-2, "shield"); //Water shield
		}
		for (int i = 1; i < SIZE_; i+=2) {
			pieces_[i][1] = new Piece(true, this, i, 1, "shield"); //Fire normal
			pieces_[i][SIZE_-3] = new Piece(false, this, i, SIZE_-3, "bomb"); // Water bomb 
			pieces_[i][SIZE_-1] = new Piece(false, this, i, SIZE_-1, "pawn"); //Water normal
		}
	}
	
	public Piece pieceAt(int x, int y) {
		if (x < 0 || x >= SIZE_ || y < 0 || y >= SIZE_) {
			return null;
		}
		return  pieces_[x][y];
	}
	
	
	public boolean canSelect(int x, int y) {
		if (outOfBound(x, y)) {
			return false;
		}
		
		if (hasPiece(x, y)) { // Trying to select a piece
			return pieceAt(x, y).isFire() == isFireTurn_ && !hasPieceMoved();
		} else if (hasSelection()) {//if selecting empty box w/ exist selection
			if (!hasPieceMoved() || selectedPiece().hasCaptured()) {
				boolean test = validMove(selectedX_, selectedY_, x, y);
				return test;
			}
		}
		return false;
	}
	
	
	public void select(int x, int y) {
		if (hasPiece(x, y)) {
			makeSelection(x, y);
		} else
		{
			startPieceMoving();
			selectedPiece().move(x, y);
			makeSelection(x, y);
			if (!hasPiece(x, y)) { // Piece is destroyed.
				clearSelection();
			}
		}
	}
	
	
	public void place(Piece p, int x, int y) {
		if (outOfBound(x, y)) {
			return;
		}
		for (int i = 0; i < SIZE_; i++) {
			for (int j = 0; j < SIZE_; j++) {
				if (pieces_[i][j] == p) {
					remove(i, j);
				}
			}
		}
		pieces_[x][y] = p;
	}
	
	
	public Piece remove(int x, int y) {
		if (outOfBound(x, y)) {
			System.out.print("ERROR: Removing out of bound piece");
			return null;
		}
		if (!hasPiece(x, y)) {
			System.out.print("ERROR: Removing piece from a " + 
		                     "location which does not have piece");
		}
		
		Piece piece = pieces_[x][y];
		pieces_[x][y] = null;
		return piece;
	}
	
	
	public boolean canEndTurn() {
		return hasPieceMoved_;
	}
	
	
	public void endTurn() {
			donePieceMoving();
			if (selectedPiece() != null)
				selectedPiece().doneCapturing();
			clearSelection();
			switchTurn();
	}
	
	
	public String winner() {
		int numOfFire = 0;
		int numOfWater = 0;
		
		for (int i = 0; i < SIZE_; i++) {
			for (int j = 0; j < SIZE_; j++) {
				if (pieces_[i][j] != null) {
					if (pieces_[i][j].isFire()) {
						numOfFire++;
					} else {
						numOfWater++;
					}
					if (numOfFire > 0 && numOfWater > 0) {
						return null;
					}
				}
			}
		}
		
		if (numOfFire > numOfWater) {
			return "Fire";
		} else if (numOfFire == numOfWater) {
			return "No one";
		} else {
			return "Water";
		}
	}
	
	
	public static void main(String[] args) {
        StdDrawPlus.setXscale(0, SIZE_);
        StdDrawPlus.setYscale(0, SIZE_);
        //Board board = new Board(false);
        Board board = new Board(false);

        while(true) {
            board.draw();
            StdDrawPlus.show(100);
            
            if (board.winner() != null) {
            	break;
            }
            
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if (board.canSelect(x, y)) {
                	board.select(x, y);
                }
            }
            
            if (StdDrawPlus.isSpacePressed()) {
            	if (board.canEndTurn()) {
            		board.endTurn();
            	}
            }
            
            if (StdDrawPlus.isNPressed()) {
            	board = new Board(false);
            }
        }
	}
	
	private void draw() {
		drawBackGround();
		drawSelection();
		for (int i = 0; i < SIZE_; i++) {
			for (int j = 0; j < SIZE_; j++) {
				drawPiece(i, j);
			}
		}
	}
	
	private void drawSelection() {
		if (hasSelection()) {
        	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        	StdDrawPlus.filledSquare(selectedX_ + .5, selectedY_ + .5, .5);
		}
	}
	/** Draws an N x N board. Adapted from:
     *http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
	 */
	private void drawBackGround() {
	    for (int i = 0; i < SIZE_; i++) {
	        for (int j = 0; j < SIZE_; j++) {
	        	if ((i + j) % 2 == 0) {
	            	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	            } else {
	            	StdDrawPlus.setPenColor(StdDrawPlus.RED);
	            }
	            StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	            
	        }
	    }
	}
	
	/** Draw Piece at (x, y)
	 *  Do Nothing if there is no piece at that location.
	 */
	private void drawPiece(int x, int y) {
		if (x < 0 || y < 0 || x >= SIZE_ || y >= SIZE_)
			return;
		if (pieces_[x][y] == null)
			return;
		String picture = "img/";
		
		if (pieces_[x][y].isBomb()) {
			picture += "bomb-";
		} else if (pieces_[x][y].isShield()) {
			picture += "shield-";
		} else {
			picture += "pawn-";
		}
		
		if (pieces_[x][y].isFire()) {
			picture += "fire";
		} else {
			picture += "water";
		}
		
		if (pieces_[x][y].isKing()) {
			picture += "-crowned";
		}
		
		picture += ".png";
		
		StdDrawPlus.picture(x + .5, y + .5, picture, 1, 1);
	}
	
	
	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (outOfBound(xi, yi)) {
			return false;
		}
		if (outOfBound(xf, yf)) {
			return false;
		}
		
		if (pieces_[xi][yi] == null) {
			return false;
		} else if (pieces_[xf][yf] != null) {
			return false;
		}
		
		// Check if (xi, yi) and (xf, yf) are in diagonal.
		// and whether or not the movement is capture
		if (Math.abs(xi-xf) != Math.abs(yi-yf)) {
			return false;
		} else if (!( (Math.abs(xi-xf) == 1 && !pieces_[xi][yi].hasCaptured())|| 
					   Math.abs(xi-xf) == 2)) {
			return false;
		}
			
		if (Math.abs(xi-xf) == 2) { // if capturing, the middle point should
			int xMid = xf + (xi-xf)/2; // contain an enemy piece.
			int yMid = yf + (yi-yf)/2;
			if (pieces_[xMid][yMid] == null)
				return false;
			if (pieces_[xMid][yMid].isFire() == pieces_[xi][yi].isFire()) {
				return false;
			}
		}
		
		if (!pieces_[xi][yi].isKing()) { // Non king can only go one direction
			if (pieces_[xi][yi].isFire()) { // Fire piece can only go up
				if (yi >= yf) {
					return false;
				}
			} else if (yi <= yf) { // Water piece can only go down
				return false;
			}
		}
		
		return true;
	}
	
	private boolean outOfBound(int x, int y) {
		return x < 0 || x >= SIZE_ || y < 0 || y >= SIZE_;
	}
	
	private boolean hasSelection() {
		return !outOfBound(selectedX_, selectedY_);
	}
	
	private void clearSelection() {
		selectedX_ = -1;
		selectedY_ = -1;
	}
	
	private void makeSelection(int x, int y) {
		if (!outOfBound(x, y)) {
			selectedX_ = x;
			selectedY_ = y;
		}
	}
	
	private void switchTurn() {
		isFireTurn_ = !isFireTurn_;
	}
	
	private Piece selectedPiece() {
		if (hasSelection()){
			return pieces_[selectedX_][selectedY_];
		} else {
			return null;
		}
	}
	
	private boolean hasPieceMoved() {
		return hasPieceMoved_;
	}
	
	private void donePieceMoving() {
		hasPieceMoved_ = false;
	}
	
	private void startPieceMoving() {
		hasPieceMoved_ = true;
	}
	
	private boolean hasPiece(int x, int y) {
		if (outOfBound(x, y)) {
			return false;
		} else {
			return pieces_[x][y] != null;
 		}
	}
}
