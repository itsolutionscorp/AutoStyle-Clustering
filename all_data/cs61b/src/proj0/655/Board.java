public class Board {

    private Piece[][] pieces;
    private int player = 0;
    private Piece selectedPiece = null;
	private int selectedX;
	private int selectedY;
    private boolean hasMoved = false;
    private boolean explahded = false;
    private boolean[][] canMove;
    private boolean[][] canCapture;

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private void drawBoard(int N, boolean filled, Board b) {
    	if (N > 0 || N == 0) {
	        for (int i = 0; i < N; i++) {
	            for (int j = 0; j < N; j++) {
	                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	            }
	        }
	        if (filled) {
	    		for (int i = 0; i < N; i++) {
		            for (int j = 0; j < N; j++) {        
			        	if ((j < 3)){
			            	if ((j == 0) && (i % 2 == 0)) {
			            		pieces[i][j] = new Piece(true, b, i, j, "pawn");
			            		canMove[i][j] = true;
			            		canCapture[i][j] = true;
			            	}
			            	else if ((j == 1) && (i % 2 == 1)) {
			            		pieces[i][j] = new Piece(true, b, i, j, "shield");
			            		canMove[i][j] = true;
			            		canCapture[i][j] = true;
			            	}
			            	else if ((j == 2) && (i % 2 == 0)) {
			            		pieces[i][j] = new Piece(true, b, i, j, "bomb");
			            		canMove[i][j] = true;
			            		canCapture[i][j] = true;
			            	}
			            }
			            else if ((j > 4)) {
			            	if ((j == 7) && (i % 2 == 1)) {
			            		pieces[i][j] = new Piece(false, b, i, j, "pawn");
			            		canMove[i][j] = true;
			            		canCapture[i][j] = true;
			            	}
			            	else if ((j == 6) && (i % 2 == 0)) {
			            		pieces[i][j] = new Piece(false, b, i, j, "shield");
			            		canMove[i][j] = true;
			            		canCapture[i][j] = true;
			            	}
			            	else if ((j == 5) && (i % 2 == 1)) {
			            		pieces[i][j] = new Piece(false, b, i, j, "bomb");
			            		canMove[i][j] = true;
			            		canCapture[i][j] = true;
			            	}
			            }
			        		
			    }
			}
			}
			else {
	    		for (int i = 0; i < N; i++) {
		        	for (int j = 0; j < N; j++) { 	
		        	canMove[i][j] = true;
		        	canCapture[i][j] = true;
		        	pieces[i][j] = null;
		        	}
		        }			
			}
		}
		return;
	}

        
	public static void main(String[] args) {
        Board b = new Board(false);
        while (b.winner() == null) {
        	if (StdDrawPlus.mousePressed()) {
        		double x = StdDrawPlus.mouseX();
        		double y = StdDrawPlus.mouseY();
        		int a = (int) x;
        		int c = (int) y;
				if (b.canSelect(a, c)){
					b.select(a, c);	
				}
        	}
    		if (StdDrawPlus.isSpacePressed()) {
    			b.endTurn();
    		}
    		StdDrawPlus.show(20);        	        
    	}
    	System.out.println(b.winner());
	}


	/* The constructor for Board. If shouldBeEmpty is true, initializes an empty Board. Otherwise, initializes a Board with the default configuration. Note that an empty Board could possibly be useful for testing purposes. */
	public Board (boolean shouldBeEmpty) {
		int N = 8;		
		StdDrawPlus.setXscale(0, N);
	    StdDrawPlus.setYscale(0, N);	
	    pieces = new Piece[N][N];
	    canMove = new boolean[N][N];	
	    canCapture = new boolean[N][N];
		if (shouldBeEmpty) {
	    	drawBoard(N, false, this);
		}
		else {
			drawBoard(N, true, this);
	    }
	}

	public Piece pieceAt(int x, int y) {
		if (x > 7 || y > 7 || x < 0 || y < 0) {
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y) {
		if (this.explahded) {
			return false;
		}	
		if (this.pieceAt(x, y) != null && this.isOnCurrentTeam(x, y) && this.hasMoved == false) {
			return true;
		}		
		if (this.hasMoved && !(this.validMove(this.selectedX, this.selectedY, x, y))) {
			return false;
		}		
		if (!this.hasMoved || (this.validMove(this.selectedX, this.selectedY, x, y) && this.selectedPiece != null && this.pieceAt(x, y) == null)) {
			if (this.selectedPiece != null) {
				if (this.validMove(this.selectedX, this.selectedY, x, y)) {
					return true;
				}
				else if ((this.pieceAt(x, y) != null) && this.isOnCurrentTeam(x, y)) {
					return true;
				}
			}
			else if (this.pieceAt(x, y) != null && this.isOnCurrentTeam(x, y)) {
				return true;
			}
		}
		return false;
	}

	private boolean determineOfMoves(Piece p, int x, int y) {
		if (p.isKing()){
			if (this.pieceAt(x-1, y-1) != null && !this.isOnCurrentTeam(x-1, y-1) && (this.pieceAt(2*(x-1), 2*(y-1))==null) && (x-1 < 8) && (y-1 < 8)) {
				return true;
			}
			else if (this.pieceAt(x-1,y+1)  != null && !this.isOnCurrentTeam(x-1,y+1) && (this.pieceAt(2*(x-1),2*(y+1))==null) && x-1 < 8 && y+1 < 8) {
				return true;
			}
			else if (this.pieceAt(x+1,y+1)!=null && !this.isOnCurrentTeam(x+1,y+1) && this.pieceAt(2*(x+1),2*(y+1))==null && x+1 < 8 && y+1 < 8) {
				return true;
			}
			else if (this.pieceAt(x-1,y-1)!=null && !this.isOnCurrentTeam(x-1,y-1) && this.pieceAt(2*(x+1),2*(y-1))==null && x+1 < 8 && y-1 < 8) {
				return true;
			}
			return false;
		}

		if (this.pieceAt(x-1, (this.player*-2) + 1)!=null && !this.isOnCurrentTeam(x-1,(this.player*-2) + 1) && this.pieceAt(2*(x-1), (this.player*-2) + 1)==null && x-1 < 8 && (this.player*-2) + 1 < 8) {
			return true;
		}
		else if (this.pieceAt(x+1,(this.player*-2) + 1)!=null && !this.isOnCurrentTeam(x+1,(this.player*-2) + 1) && this.pieceAt(2*(x+1),((this.player*-2) + 1))==null && x+1 < 8 && (this.player*-2) + 1 < 8) {
			return true;
		}
		return false;		
	}

	public void select(int x, int y) {
		if (this.pieceAt(x, y) != null) {
			if (this.selectedPiece != null) {
				paintSquareBack(this.selectedX, this.selectedY);
				drawUnknownPicture(this.selectedPiece, this.selectedX, this.selectedY);
			} 
			Piece q = this.pieceAt(x, y);
			this.paintWhite(x, y);
			this.drawUnknownPicture(q, x, y);
			this.selectedPiece = q;
			this.selectedX = x;
			this.selectedY = y;
			if (this.selectedPiece.hasCaptured()){
				this.canCapture[x][y] = false;
			}
			if (this.selectedPiece.hasCaptured() && this.determineOfMoves(selectedPiece, x, y)) {
				this.canCapture[x][y] = true;
				this.selectedPiece.doneCapturing();
			}
		}
		else if ((this.selectedPiece != null) && (this.validMove(this.selectedX, this.selectedY, x, y)) && this.pieceAt(x, y) == null) {			
			this.selectedPiece.move(x, y);
			this.canMove[x][y] = false; 
			this.canMove[selectedX][selectedY] = false;
			this.canCapture[x][y] = false;
			if (this.pieceAt(x, y).hasCaptured()) {
				this.canCapture[x][y] = true;
			}
			this.hasMoved = true;
			this.selectedX = x;
			this.selectedY = y;			
			if (this.selectedPiece.hasCaptured() && this.selectedPiece.isBomb()) {
				this.explosion(x, y);
				this.explahded = true;
				this.selectedPiece = null;
			}
		}
	}

	public void place(Piece p, int x, int y) {
		if (((x > 7) || (y > 7)) || (p == null)) {
			return;
		}
		else{
			this.pieces[x][y] = p;
			drawUnknownPicture(p, x, y);

		}	
	}

	public Piece remove(int x, int y) {
		if ((x > 7) || (y > 7)) {
			System.out.println("Index for remove is out of bounds, you idiot...");
			return null;
		}
		else if (pieces[x][y] == null) {
			System.out.println("There is not a piece there, you idiot...");
			return null;
		}
		else {
			Piece p = this.pieceAt(x, y);
			this.pieces[x][y] = null;
			paintSquareBack(x, y);
			return p;
		}
	}

	public boolean canEndTurn() {
		if (this.hasMoved) {
			return true;
		}
		return false;
	}

	public void endTurn() {
		if (this.canEndTurn()) {
			this.player = (-2 * (this.player % 2)) + 1 + this.player;
			this.hasMoved = false;
			if (this.explahded == false) {
				paintSquareBack(this.selectedX, this.selectedY);
				drawUnknownPicture(this.selectedPiece, this.selectedX, this.selectedY);
			}
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					this.canMove[i][j] = true;
					this.canCapture[i][j] = true;
					if (this.pieceAt(i, j) != null && this.pieceAt(i, j).hasCaptured()){
						this.pieceAt(i, j).doneCapturing();
					}
				}
			}
			this.explahded = false;
		}
	}

	public String winner() {
		int fire = 0;
		int water = 0;
		int N = 8;
        for (int i = 0; i < N; i++) {
        	for (int j = 0; j < N; j++) {
        		if (pieces[i][j] != null) {
        			if (pieces[i][j].isFire()) {
        				fire +=1;
        			}
        			else {
        				water +=1;
        			}
        		}
        	}
        }
		if ((fire != 0) && (water != 0)) {
			return null;
		}
		else if ((fire != 0) && (water == 0)) {
			return "Fire";
		}
		else if ((fire == 0) && (water != 0))  {
			return "Water";
		}
		else {
			return "No one";
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf) {
		if (xf < 8 && yf < 8 && this.canMove[xi][yi]) {
			if (this.pieceAt(xi, yi) != null) {
				if ((Math.abs(xf - xi) == 1) && (yf == yi + 1 + (-this.player * 2)) && !(this.pieceAt(xi, yi).isKing())) { //checks to see that the move is in range assuming no piece is going to be taken and the piece is not kinged
					if (this.pieceAt(xf, yf) == null) {
						return true;
					}
				}
				else if ((Math.abs(xf - xi) == 2) && (yf == yi + 2 + (-this.player * 4)) && !(this.pieceAt(xi, yi).isKing())) { //checks to see if the move is intended to take another piece
					if ((this.pieceAt(((xf + xi) / 2), ((yf + yi) / 2)) != null) && !this.isOnCurrentTeam(((xf + xi) / 2), ((yf + yi) / 2))) { //makes sure there is a piece where expected and that the piece is on the other team
						return true;
					}
				}
				else if (this.pieceAt(xi, yi).isKing()) {
					if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1)) { //checks to see that the move is in range assuming no piece is going to be taken and the piece is kinged
						if (this.pieceAt(xf, yf) == null) {
							return true;
						}
					}
					else if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2)) { //checks to see if the move is intended to take another piece
						if ((this.pieceAt(((xf + xi) / 2),((yf + yi) / 2)) != null) && !this.isOnCurrentTeam(((xf + xi) / 2),((yf + yi) / 2))) { //makes sure there is a piece where expected and that the piece is on the other team
							return true;
						}
					}
				}
			}
		}
		else if (xf < 8 && yf < 8 && this.canCapture[xi][yi]) {
			if (this.pieceAt(xi, yi) != null) {
				if (this.pieceAt(xi, yi).isKing()){
					if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2)) { //checks to see if the move is intended to take another piece
						if ((this.pieceAt(((xf + xi) / 2),((yf + yi) / 2)) != null) && !this.isOnCurrentTeam(((xf + xi) / 2),((yf + yi) / 2))) { //makes sure there is a piece where expected and that the piece is on the other team
							return true;
						}
					}
				}
				if ((Math.abs(xf - xi) == 2) && (yf == yi + 2 + (-this.player * 4))) { //checks to see if the move is intended to take another piece
					if ((this.pieceAt(((xf + xi) / 2), ((yf + yi) / 2)) != null) && !this.isOnCurrentTeam(((xf + xi) / 2), ((yf + yi) / 2))) { //makes sure there is a piece where expected and that the piece is on the other team
						return true;
					}
				}				
			}
		}
		return false;
	}

	// paints the space white such that the space will be visually selected
	private void paintWhite(int x, int y) {
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	    StdDrawPlus.filledSquare(x + .5, y + .5, .5);		 
	}

	// draws the picture of the Piece at the given location
	private void drawPicture(int x, int y, String imgPath, int a, int b) {
		StdDrawPlus.picture(x + .5, y + .5, imgPath, a, b);

	}

	/* Determines what the piece is and then draws it in the the correct position */
	private void drawUnknownPicture(Piece q, int x, int y) {
		if (q.isFire()) {
			if (q.isKing()) {
				if (q.isBomb()) {
					drawPicture(x, y, "img/bomb-fire-crowned.png", 1, 1);
				}
				else if (q.isShield()) {
					drawPicture(x, y, "img/shield-fire-crowned.png", 1, 1);
				}
				else {
					drawPicture(x, y, "img/pawn-fire-crowned.png", 1, 1);
				}	
			}
			else if (q.isBomb()) {		 	
				drawPicture(x, y, "img/bomb-fire.png", 1, 1);
			}
			else if (q.isShield()) {
				drawPicture(x, y, "img/shield-fire.png", 1, 1);
			}
			else {
				drawPicture(x, y, "img/pawn-fire.png", 1, 1);
			}
		}
		else {
			if (q.isKing()) {
				if (q.isBomb()) {
					drawPicture(x, y, "img/bomb-water-crowned.png", 1, 1);
				}
				else if (q.isShield()) {
					drawPicture(x, y, "img/shield-water-crowned.png", 1, 1);
				}
				else {
					drawPicture(x, y, "img/pawn-water-crowned.png", 1, 1);
				}	
			}
			else if (q.isBomb()) {
				drawPicture(x, y, "img/bomb-water.png", 1, 1);
			}
			else if (q.isShield()) {
				drawPicture(x, y, "img/shield-water.png", 1, 1);
			}
			else {
				drawPicture(x, y, "img/pawn-water.png", 1, 1);
			}			
		}
	}

	/* Checks to see if the piece at [x][y] is owned by the current player. */
	private boolean isOnCurrentTeam(int x, int y) {
		if (this.pieceAt(x, y).isFire()) {
			if (this.player == 0) {
				return true;
			}
			return false;
		}
		if (this.player == 1) {
			return true;
		}
		return false;
	}

	//repaints the square back to its original color (could be selected/white)
	private void paintSquareBack(int x, int y) {
        if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);		
	}

	private void explosion(int x, int y) {
		for (int i = x - 1; i < x + 2; i ++) {
			for (int j = y - 1; j < y + 2; j++) {
				if (i < 8 && j < 8) {
					if (this.pieceAt(i, j) != null) {
						if (!(this.determineType(pieceAt(i, j)) == "shield")) {
							this.remove(i, j);
						}
					}
				}
			}
		}
	}

	private String determineType(Piece q) {
		if (q.isBomb()) {
			return "bomb";
		}
		else if (q.isShield()) {
			return "shield";
		}
		else {
			return "pawn";
		}	
	}
}	 











