public class Board {

	private static Piece[][] pieces;
	private final static int N = 8;
	private Piece selected;
	private int player;
	private boolean moved;
	private int selectedX, selectedY;

	public static void main (String[] args) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false); //There has to be a constructor in the main method!
        // Board b = new Board(true);
        // Piece p1 = new Piece(true, b, 5, 5, "shield");
        // Piece p2 = new Piece(false, b, 6, 6, "pawn");
        // b.pieces[5][5] = p1; b.pieces[6][6] = p2;

		/** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                System.out.println("Mouse pressed!");
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y))
                	b.select((int) x, (int) y);
            }

            if (StdDrawPlus.isSpacePressed()) {
            	System.out.println("Space pressed!");
            	b.endTurn();
            }
            StdDrawPlus.show(100);
        }
	}

	

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] == selected && selected != null) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, getImg(pieces[i][j]), 1, 1);
                }
            }
        }
    }

    private String getImg(Piece p){
    	if (!p.isKing()){
    		if (p.isFire()){
    			if (p.isBomb())
    				return "img/bomb-fire.png";
    			else if (p.isShield())
    				return "img/shield-fire.png";
    			else return "img/pawn-fire.png";
	    	}

    		else{
    			if (p.isBomb())
    				return "img/bomb-water.png";
    			else if (p.isShield())
    				return "img/shield-water.png";
    			else return "img/pawn-water.png";
    		}
    	}
    	
    	else{
    		if (p.isFire()){
    			if (p.isBomb())
    				return "img/bomb-fire-crowned.png";
    			else if (p.isShield())
    				return "img/shield-fire-crowned.png";
    			else return "img/pawn-fire-crowned.png";
	    	}

    		else{
    			if (p.isBomb())
    				return "img/bomb-water-crowned.png";
    			else if (p.isShield())
    				return "img/shield-water-crowned.png";
    			else return "img/pawn-water-crowned.png";
    		}
    	}
	}

	
    //Constructor for the Board
	public Board(boolean shouldBeEmpty) {
		this.pieces = pieces;
		this.selected = selected;
		this.player = player;
		pieces = new Piece[N][N];
		moved = false;
		selectedX = 0;
		selectedY = 0;

		for(int y = 0; y < N; y++){
			for (int x = 0; x < N; x++){

				if (shouldBeEmpty)
					pieces[x][y] = null;

				else if ((y == 0) && ((x % 2) == 0))
					pieces[x][y] = new Piece(true, this, x, y, "pawn");

				else if ((y == 1) && ((x % 2) == 1))
					pieces[x][y] = new Piece(true, this, x, y, "shield");

				else if ((y == 2) && ((x % 2) == 0))
					pieces[x][y] = new Piece(true, this, x, y, "bomb");

				else if ((y == 5) && ((x % 2) == 1))
					pieces[x][y] = new Piece(false, this, x, y, "bomb");

				else if ((y == 6) && ((x % 2) == 0))
					pieces[x][y] = new Piece(false, this, x, y, "shield");

				else if ((y == 7) && ((x % 2) == 1))
					pieces[x][y] = new Piece(false, this, x, y, "pawn");

				else
					pieces[x][y] = null;
			}
		}
	}

	
	private boolean InvalidPosition(int x, int y){
		return (x >= N || y >= N || x < 0 || y < 0);
	}

	
	//Get the piece at position(x, y)
	public Piece pieceAt(int x, int y) {
		if (InvalidPosition(x, y) || pieces[x][y] == null)
			return null;
		return pieces[x][y];
	}

	//Check if position(x, y) is empty or having a piece
	private boolean hasPiece(int x, int y) {
		if (pieces[x][y] == null)
			return false;
		return true;
	}


	private int moveDirection(int x, int y){
		if (this.pieceAt(x, y).isFire()){
			return 1;
		}	
		return -1;
	}
	

	//Check if the position(xf, yf) is a valid move of a certain piece
	private boolean validMove(int xi, int yi, int xf, int yf) {
		Piece p = pieces[xi][yi];
		int direction = moveDirection(xi, yi);
		int xm = (xi + xf) / 2; 
		int ym = (yi + yf) / 2;
		if (!p.isKing()){
			if ((Math.abs(xf - xi) == 1) && ((yf - yi) == direction)){
				if (!hasPiece(xf, yf)){
					return true;
				} else return false;
			} else if ((Math.abs(xf - xi) == 2) && ((yf - yi == 2 * direction))){
				if ((hasPiece(xf, yf)) || (!hasPiece(xm, ym))){
						return false;
				} else if (hasPiece(xm, ym)) {
					if (pieces[xm][ym].isFire() == p.isFire()){
						return false;
					} else{
						return true;
					}
				}
			}

		} else { 
			if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1)){
				if (!hasPiece(xf, yf)){
					return true;
				} else return false;
			} 

			else if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2)){
				if ((hasPiece(xf, yf)) || (!hasPiece(xm, ym))){
					return false;
				} 
				else if (hasPiece(xm, ym)) {
					if (pieces[xm][ym].isFire() == p.isFire()){
			 			return false;
					} 
					else{
						return true;
					}
				}
			}

			return false;
		}
		return false;
	}


	//Whether the piece at position(x, y) can be selected
	public boolean canSelect(int x, int y) {
		
		if (InvalidPosition(x, y)) {
			System.out.println("Invalid position!");
			return false;
		}

		else { //position is valid!
			Piece piece = pieces[x][y];
			if (piece != null) {
				if (piece.side() == player){
					if (selected == null) return true;
					else { //selected != null
						if (!moved)
							return true;
						return false;
					}
				}
				else return false; 

			}

			else {//position(x, y) has no piece
				if ((selected != null) && (!moved)
				   && (validMove(selectedX, selectedY, x, y)))
				return true;

				else if ((selected != null) && selected.hasCaptured() 
						&& (validMove(selectedX, selectedY, x, y))){
						if ((y - selectedY) == moveDirection(selectedX, selectedY))
							return false; 
						return true;
				}

				else return false;
			}
		}	
	}

	
	//Select a piece if possible
	public void select(int x, int y) {
		if (InvalidPosition(x, y)){
			StdOut.printf("(%d, %d) Invalid position!\n",x ,y);
 		}

		// else if (!canSelect(x, y)) {
		// 	 StdOut.printf("This piece (%d, %d) cannot be selected!\n",x ,y);
		// }

		else {
			StdOut.printf("This piece (%d, %d) can be selected!\n", x, y);
			if (this.selected == null){
				this.selected = pieces[x][y];
				selectedX = x;
				selectedY = y;
			}
			else {
				if(pieceAt(x, y) != null){
					this.selected = pieces[x][y];
					selectedX = x;
					selectedY = y;
				}
				else{ 
					this.selected.move(x, y);
					moved = true;
				}
			}
		} 
	}

	
	
	//Place a piece at position(x, y)
	public void place(Piece p, int x, int y) {
		if (!InvalidPosition(x, y) && ! (p == null)){
			remove(x, y);
			pieces[x][y] = p;
		}
	}



	public Piece remove(int x, int y) {
		if (InvalidPosition(x, y)){
			System.out.println("Invalid position!");
			return null;
		}

		else if (pieces[x][y] == null) {
			System.out.println("There is not piece at this place!");
			return null;
		}

		else {
			Piece copy = pieces[x][y];
			pieces[x][y] = null;
			return copy;
		}
	}

	
	public boolean canEndTurn() {
		return (this.selected !=  null && this.moved);
	}

	public void endTurn() {
		if (canEndTurn()) {
			this.player = 1 - this.player;
			this.moved = false;
			selected.doneCapturing();
			selected = null;
		}
	}

	public String winner() {
		int fireNumber = 0;
		int waterNumber = 0;

		for (int i = 0; i < N; i++){
			for (int j = 0; j < N; j++) {
				Piece p = pieces[i][j];
				if (p != null) {
					if (p.isFire())
						fireNumber ++;
					else waterNumber++;
				}
			}
		}
		
		if (fireNumber == 0 && waterNumber == 0) return "No one";
		else if (fireNumber == 0) return "Water";
		else if (waterNumber == 0) return "Fire";
		else return null;
	}
}
