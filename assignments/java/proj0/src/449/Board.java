/*
* Greta Huang
*/

public class Board{

	private Piece[][] board;
	private boolean isFirePlayer;
	private Piece selected;
	private int selectedX;
	private int selectedY;
	private boolean hasMoved; //if one moves - one diag. step, they have not captured - two diag step... and vice versa
	private boolean hasCaptured;
	private int N;
	private static Board b;

	public Board(boolean shouldBeEmpty){
		 board = new Piece[8][8];
		 isFirePlayer = true;
		 selected = null;
		 selectedX = -1;
		 selectedY = -1;
		 hasMoved = false;
		 hasCaptured = false;
		 N = 8;
		 /* Fills board with default pieces */
		 if (!shouldBeEmpty){  
		 	for(int i = 0; i < N; i++){
		 		for(int j = 0; j < N; j++){
		 			if(j == 0 && i % 2 == 0){
		 				board[i][j] = new Piece(true, this, i, j, "pawn");
		 			} else if(j == 1 && i % 2 == 1){
		 				board[i][j] = new Piece(true, this, i, j, "shield");
		 			} else if(j == 2 && i % 2 == 0){
		 				board[i][j] = new Piece(true, this, i, j, "bomb");
		 			} else if(j == 5 && i % 2 == 1){
		 				board[i][j] = new Piece(false, this, i, j, "bomb");
		 			} else if(j == 6 && i % 2 == 0){
		 				board[i][j] = new Piece(false, this, i, j, "shield");
		 			} else if(j == 7 && i % 2 == 1){
		 				board[i][j] = new Piece(false, this, i, j, "pawn");
		 			}
		 		}
		 	}
		 }
	}
	public static void main (String args[]){
		int N = 8;
		b = new Board(false);
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
             	if (b.canSelect(x, y)){
             		b.select(x, y);
             	}
            }   
            /* Checks if the spacebar has been pressed. If so, 
               it attempts to end the turn. */
            if (StdDrawPlus.isSpacePressed()){ 
            	if (b.canEndTurn()){
            		b.endTurn();
            	}
            }         
            StdDrawPlus.show(100);
        }
	}
	private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                /* Highlights selected square with white */
                if (b.selected != null && i == b.selectedX && j == b.selectedY){
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                Piece curr = b.pieceAt(i, j);
                /* Finds the filename of the current piece's corresponding image.
                */
                if (curr != null) {
                	String fileName = "";
                	//finds type of piece
                	if (curr.isShield()){
                		fileName += "img/shield";
                	} else if (curr.isBomb()) {
                		fileName += "img/bomb";
					} else {
						fileName += "img/pawn";
					}
					//finds the piece's "side" (fire or water)
					if (curr.isFire()){
						fileName += "-fire";
					} else {
						fileName += "-water";
					}
					//checks if piece is a king
					if (curr.isKing()){
						fileName += "-crowned";
					}
					fileName += ".png";
                    StdDrawPlus.picture(i + .5, j + .5, fileName, 1, 1);
                }
            }
        }
    }
	
	public Piece pieceAt(int x, int y){
		if(x > N - 1 || y > N - 1){
			return null;
		} else {
			return board[x][y];
		}
	}
	public boolean canSelect(int x, int y){
		if (!hasMoved) {
			if (pieceAt(x,y) != null){ //if selecting a square with a piece in it
				if ((pieceAt(x, y).isFire() && isFirePlayer) || (!pieceAt(x, y).isFire() && !isFirePlayer)){
					if(selected == null && hasCaptured){
						return false;
					}
					if (selected == null || !hasMoved && !hasCaptured){
						return true;
					}
				}
			}
			else if (selected != null) { // if selecting an empty square
				if (validMove(selectedX, selectedY, x, y)){
					return true;
				}
			}
		}
		return false;
	}
	private boolean validMove(int xi, int yi, int xf, int yf){
		/* Assumes that final position is an empty square; canSelect takes care of cases in which the square is not empty
		   Also assumes that a piece has already been selected (for a piece to be moved, it must have been selected) */
		if (!hasMoved){	
			if (Math.abs(xf - xi) == 1){ //if moving (only 1 diagonal step)
				if (!selected.hasCaptured()){
					if(isFirePlayer && ((yf - yi) == 1)){
						return true; //fire players must move down
					} else if (!isFirePlayer && ((yi - yf) == 1)) {
						return true; //water players must move up
					} else if (pieceAt(xi, yi).isKing() && Math.abs(yf - yi) == 1){
						return true; //kings can move in any direction
					}
				}
			}
			else if (Math.abs(xf - xi) == 2){ //if capturing (2 diagonal steps)
				int capturedX = (xi + xf) / 2; 
				int capturedY = (yi + yf) / 2; 
				if (isFirePlayer && ((yf - yi) == 2) && pieceAt(capturedX, capturedY) != null && !pieceAt(capturedX, capturedY).isFire()){
					return true; //fire players must move down for capture
				} else if (!isFirePlayer && ((yi - yf) == 2) && pieceAt(capturedX, capturedY) != null && pieceAt(capturedX, capturedY).isFire()) {
					return true; //water players must move up for capture
				} else if (pieceAt(xi, yi).isKing() && Math.abs(yf - yi) == 2 && pieceAt(capturedX, capturedY) != null && (isFirePlayer != pieceAt(capturedX, capturedY).isFire())) {
					return true; //kings can move in any direction for capture
				}
			}
		}
		return false;
	}
	public void select(int x, int y){
			if (pieceAt(x, y) != null) {  //if a piece is being selected
				selected = pieceAt(x, y);
				selectedX = x;
				selectedY = y;
			} else {   //if an empty space is being selected       
				selected.move(x, y);
				if (Math.abs(x - selectedX) == 1) {
					hasMoved = true;
				}
				if (Math.abs(x - selectedX) == 2) {
					hasCaptured = true;
				}
				selectedX = x;
				selectedY = y;
			}
	}
	public void place(Piece p, int x, int y){
		if (p != null && x < N && y < N) {
			for(int i = 0; i < N; i++){
				for(int j = 0; j < N; j++) {
					if (p == pieceAt(i,j)) {
						board[i][j] = null;
					}
				}
			}
			board[x][y] = p;
		}
	}
	public Piece remove(int x, int y){
		if (x >= N || y >= N){
			System.out.println("Selected remove position is out of bounds.");
			return null;
		} else if (pieceAt(x, y) == null) {
			System.out.println("No pieces to remove.");
			return null;
		} else {
			Piece removed = pieceAt(x, y);
			if (removed == selected){
				selected = null;
			}
			board[x][y] = null;
			return removed;
		}
	}
	public boolean canEndTurn(){
		if (hasMoved || hasCaptured){
			return true;
		}
		return false;
	}
	public void endTurn(){
		 isFirePlayer = !isFirePlayer;
		 if (selected != null){
		 	selected.doneCapturing();
		 }
		 selected = null;
		 selectedX = -1;
		 selectedY = -1;
		 hasMoved = false;
		 hasCaptured = false;
	}
	public String winner(){
		int firePieces = 0;
		int waterPieces = 0;
		for (int i = 0; i < N; i++){  //counts up the amount of fire pieces and water pieces on the board
			for (int j = 0; j < N; j++) {
				if (pieceAt(i, j) != null) {
					if (pieceAt(i, j).isFire()) {
						firePieces++;
					} else {
						waterPieces++;
					}
				}
			}
		}
		if (firePieces == 0 && waterPieces == 0) {
			return "No one";
		} else if (firePieces == 0) {
			return "Water";
		} else if (waterPieces == 0) {
			return "Fire";
		} else {
			return null;
		}
	}
}