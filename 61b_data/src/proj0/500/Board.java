import java.lang.Math.*;
public class Board {

	private boolean empty;
	private boolean turn = true;
	private Piece currPiece = null;
	private int currPieceX = 0;
	private int currPieceY = 0;
	private int player = 0;
	private boolean doneCapture = false;

	private Piece[][] pieces;
	private static int N = 8;

	

	public Board(boolean shouldBeEmpty) {
		empty = shouldBeEmpty;
		pieces = new Piece[N][N];
		if (!empty) {
			for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	            	if (j == 0 && (i%2 == 0)) pieces[i][j] = new Piece(true, this, i, j, "pawn");
	            	if (j == 2 && (i%2 == 0)) pieces[i][j] = new Piece(true, this, i, j, "bomb");
	            	if (j == 1 && (i%2 != 0)) pieces[i][j] = new Piece(true, this, i, j, "shield");
	            	if (j == (N-1) && (i%2 != 0)) pieces[i][j] = new Piece(false, this, i, j, "pawn");
	            	if (j == (N-3) && (i%2 != 0)) pieces[i][j] = new Piece(false, this, i, j, "bomb");
	            	if (j == (N-2) && (i%2 == 0)) pieces[i][j] = new Piece(false, this, i, j, "shield");
	            }
	        }
    	}
	}

	public Piece pieceAt(int x, int y){
		if (x >= N || x < 0 || y>= N || y < 0 ) return null;
		return pieces[x][y];
	}

	public void place(Piece p, int x, int y){
		if (p != null && (x <= (N-1) &&  x >= 0 && y <= (N-1) && y >= 0)) {
		pieces[x][y] = p;
		}
	}


	public boolean canSelect(int x, int y) {
		if (currPiece == null) {
			if (pieceAt(x, y) != null && pieceAt(x,y).side() == player) return true;
			if (pieceAt(x, y) == null) return false;
			 
		}
		else if (currPiece != null || currPiece.isKing()) { 
			if (doneCapture && pieceAt(x,y) != null) return false;
			if (pieceAt(x, y) != null && pieceAt(x,y).side() == player) return true;
			if (validMove(currPieceX, currPieceY, x, y)) {
				return true;
			} 
		}
		return false;
	}
	public void select(int x, int y) {
		if (pieceAt(x, y) != null ) {
			currPiece = pieceAt(x, y);
			currPieceX = x;
			currPieceY = y;
		}
		else {
			currPiece.move(x, y);
			if (!currPiece.hasCaptured()) {
				turn = false;
			}
			else {
				currPieceX = x;
				currPieceY = y;
				doneCapture = true;
			}

		}
	}

	public boolean canEndTurn() {
		if (doneCapture || !turn) return true;
		return false;
	}

	public void endTurn() {
		turn = true;
		doneCapture = false;
		currPiece.doneCapturing();
		currPiece = null;
		player = Math.abs(1-player);
	}

	public Piece remove(int x, int y) {
		if ((x > N-1) || (x < 0)) return null;
		if ((y > N-1) || (y < 0)) return null;
		if (pieceAt(x, y) == null) return null;
		Piece ret = pieceAt(x, y);
		pieces[x][y] = null;
		return ret;
	}

	public String winner() {
		//if (empty) return "No one";
		int fire = 0;
		int water = 0;
		for (int i = 0; i < N; i++) {
	        for (int j = 0; j < N; j++) {
	           	if (pieces[i][j] != null && pieces[i][j].side() == 0) fire += 1;
	           	if (pieces[i][j] != null && pieces[i][j].side() == 1) water += 1;
	        }
	    }
	    if (fire == 0 && water >=1) return "Water";
	    if (water == 0 && fire >=1) return "Fire";
	    if (water == 0 && fire == 0) return "No one";
	    else return null;


	}


	private boolean validMove(int xi , int yi, int xf, int yf ) {
		if (xf > (N-1) || xf < 0) return false;
		if (yf > (N-1) || yf < 0) return false;
		if (pieceAt(xi, yi) == null) return false;

		if (pieceAt(xi, yi).side() == 0 || pieceAt(xi, yi).isKing()) {	
			if ((Math.abs(xi - xf) == 1) && (yi - yf == -1)) {
				if (pieceAt(xf, yf) == null) {
					return true;
				}
			}
			if ((Math.abs(xi - xf) == 2) && (yi - yf == -2)) {
				int midX = (xi + xf)/2;
				int midY = (yi + yf)/2;
				if (pieceAt(midX, midY) != null && pieceAt(midX, midY).side() != 0) return true;
			}
			return false;
		}
		if (pieceAt(xi, yi).side() == 1 || pieceAt(xi, yi).isKing()) {
			if ((Math.abs(xi - xf) == 1) && (yi - yf == 1)) {
				if (pieceAt(xf, yf) == null) {
					return true;
				}
			}
			if ((Math.abs(xi - xf) == 2) && (yi - yf == 2)) {
				int midX = (xi + xf)/2;
				int midY = (yi + yf)/2;
				if (pieceAt(midX, midY) != null && pieceAt(midX, midY).side() != 1) return true;
			}
			return false;
		}
		return false;
	}

	
	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                	if (pieces[i][j].isFire()) {
                		if (pieces[i][j].isBomb()) {
                			if (pieces[i][j].isKing()) {
                   				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                   			} 
                   			else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                   		}
                   		else if (pieces[i][j].isShield()) {
                   			if (pieces[i][j].isKing()) {
                   				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                   			}
                   			else StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                   		}
                   		
                   		else  {
                   			if (pieces[i][j].isKing()) {
                   				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                   			}
                   			else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                   		}	
                   	}
	               	if (!(pieces[i][j].isFire())) {
	                		if (pieces[i][j].isBomb()) {
	                			if (pieces[i][j].isKing()) {
	                   				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	                   			}
	                   			else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	                   		}
	                   		else if (pieces[i][j].isShield()) {
	                   			if (pieces[i][j].isKing()) {
	                   				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	                   			}
	                   			else StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	                   		}
	                   		else  {
	                   			if (pieces[i][j].isKing()) {
	                   				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	                   			}
	                   			else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	                   		}	
	                   	}
            	}
        	}
    	}
    }



    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        
        Board game = new Board(false);
        game.drawBoard(N);

        int a = 0;
        int b = 0;
        int count = 0;
        while(true) {
        	
            game.drawBoard(N);

            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                a = (int) x;
                b = (int) y;
                if (game.canSelect(a, b)) {
            		game.select(a, b);
            	}   
            }

            if (StdDrawPlus.isSpacePressed()) {
            	if (game.canEndTurn()) {
            		game.endTurn();
            	}
            }
            if (game.winner() != null) {
            	game.winner();
            	return;
            }
                        
            StdDrawPlus.show(100);
        }

        
	}
    
	
}