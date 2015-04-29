public class Board{
	private Piece[][] pieces;
	private boolean gameInProgress;
	private boolean turn;
	private boolean hasSelected;
	private int selectedX;
	private int selectedY;
	private boolean moved;

	private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                /* colors selected square */
                if (i == selectedX && j == selectedY) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	            
                /* gets the correct image for each piece currently on board */
                Piece p = this.pieces[i][j];
                drawPiece(p, i, j);
            }
        }
    }

/*draws a particular piece in it's proper place*/
    private void drawPiece(Piece p, int x, int y){
    	String side;
    	String type = "pawn";
      	String king = "";
        if (p != null) {
        	if (p.isFire()) {
        		side = "fire";
        	} else {
        		side = "water";
        	}
        	if (p.isBomb()) {
        		type = "bomb";
        	}
        	if (p.isShield()) {
        		type = "shield";
        	}
        	if (p.isKing()) {
        		king = "-crowned";
        	}
            StdDrawPlus.picture(x + .5, y + .5, "img/" + type +"-" + side + king + ".png", 1, 1);
        }
    }

	public Board(boolean shouldBeEmpty){
		gameInProgress = true;
		turn = true;
		selectedX = -1;
		selectedY = -1;
		moved = false;
		hasSelected = false;
		pieces = new Piece[8][8];
		if(!shouldBeEmpty){
			for (int i = 0; i < 8; i++) {
				if ((i + 0) % 2 == 0){
					pieces[i][0] = new Piece(true, this, i, 0, "bomb");
					pieces[i][2] = new Piece(true, this, i, 2, "bomb"); //bomb
					pieces[i][6] = new Piece(false, this, i, 6, "bomb");
				}
				if ((i + 1) % 2 == 0){
					pieces[i][1] = new Piece(true, this, i, 1, "bomb");
					pieces[i][7] = new Piece(false, this, i, 7, "bomb");
					pieces[i][5] = new Piece(false, this, i, 5, "bomb"); //bomb
				}
            }
		}
	}

	private boolean inRange(int i, int x, int y){
		return x-1 < i && i < y+1;
	}

	private boolean canCapture(Piece p, Piece c) {
    	return c != null && p.isFire() != c.isFire();
    }

    private boolean isEmpty(int x, int y){
    	if (!inRange(x, 0, 7) || !inRange(y, 0 ,7)){
    	}
    	return pieceAt(x, y) == null;
    }

    public Piece pieceAt(int x, int y){
    	if (inRange(x, 0, 7) && inRange(y, 0, 7)) {
    		return pieces[x][y];
    	}
    	return null;
    }
	public void place(Piece p, int x, int y){
		if (p != null && inRange(x, 0, 7) && inRange(y, 0, 7)){
			pieces[x][y] = p;
		}
	}

 	public Piece remove(int x, int y){
    	if (inRange(x, 0, 7) && inRange(y, 0, 7)) {
    		Piece p = pieceAt(x, y);
    		if (p != null) {
				pieces[x][y]= null;
		    	return p;
    		}
    		System.out.println("No piece to be removed at location: " + x + ", " +y);
    		return null;
    	}
    	System.out.println("Location: " +x + ", " +y+  " is out of range");
    	return null;
    }

    private boolean validMove(int xi, int yi, int xf, int yf){
    	//should be private//
    	/**assume there is a piece p on the board **/
    	Piece p = pieceAt(xi, yi);
    	Piece f = pieceAt(xf, yf);
    	int xdiff = xf - xi;
    	int ydiff = yf - yi;
    	boolean geometric = xdiff-ydiff==0 || xdiff+ydiff==0;
    	int range = Math.abs(xdiff);
    	boolean direction = ydiff > 0;
    	/** check proper direction */
    	if ((direction && p.isFire()) || (!direction && !p.isFire()) || p.isKing()){
    		/** cannot land on another piece **/
	    	if (f == null) {
	    		/** must move in diagonals */
	    		if (geometric) {
    				/** must capture on opposite team otherwise**/
    				if (range == 2 && (p.hasCaptured() || !moved)) {
	    				Piece c = pieceAt(xi + (xdiff/2), yi + (ydiff/2));
	    				return canCapture(p, c);
		    		} else {
		    			/**if not captured and not moved, can only move 1**/
		    			return !p.hasCaptured() && !moved && range ==1;
		    		}
	    		}
	    	}
	    }
	    return false;
    }

    public boolean canSelect(int x, int y){
        if(isEmpty(selectedX, selectedY) && moved){
            return false;
        }
		if (isEmpty(x,y)) {
			return hasSelected && validMove(selectedX, selectedY, x, y);
		} else {
			return (turn == pieceAt(x, y).isFire()) && 
			((!hasSelected) || (hasSelected && !moved));
		}
    }

    public void select(int x, int y){
        int xi = selectedX;
        int yi = selectedY;
        selectedX = x;
        selectedY = y;
		if(hasSelected && isEmpty(x,y)){
            moved = true;
			pieceAt(xi, yi).move(x, y);
		} else {
			hasSelected = true;
			moved = false;
		}
    }

    public boolean canEndTurn(){
    	return moved || (hasSelected && pieceAt(selectedX, selectedY).hasCaptured());

    }

    public void endTurn(){
    	if (!isEmpty(selectedX, selectedY)){
    		pieceAt(selectedX, selectedY).doneCapturing();	
    	}
    	turn = !turn;
    	moved = false;
    	hasSelected = false;
    	selectedX = -1;
    	selectedY = -1;
    	drawBoard();
    }

    public String winner(){
        if (firePiecesAlive() > 0 && waterPiecesAlive() > 0) {
            return null;
        } else {
            if (firePiecesAlive() == 0 && waterPiecesAlive() ==0){
                return "No one";
            } else {
                if (firePiecesAlive() == 0){
                    return "Water";
                } else {
                    return "Fire";
                }
            }
        }

    }

    private int firePiecesAlive(){
        int x = 0;
         for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!isEmpty(i,j) && pieceAt(i,j).isFire()){
                    x = x + 1;
                }
            }
        }
        return x;
    }

    private int waterPiecesAlive(){
        int x = 0;
         for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!isEmpty(i,j) && !pieceAt(i,j).isFire()){
                    x = x + 1;
                }
            }
        }
        return x;
    }


	public static void main(String[] args) {
		Board board = new Board(false);
        /*Piece p = new Piece(true, board, 0,0, "pawn");
        board.place(p, 0, 0);

        Piece p2 = new Piece(true, board, 1,0, "pawn");
        board.place(p2, 1, 0);

        Piece p3 = new Piece(false, board, 2,1, "pawn");
        board.place(p3, 2, 1); */

        //Board b = new Board(true);
        /*Piece p1 = new Piece(true, board, 0, 0, "pawn");
        board.place(p1, 0, 0);
        Piece p2 = new Piece(true, board, 1, 1, "bomb");
        board.place(p2, 1, 1);
        Piece p3 = new Piece(false, board, 2, 2, "pawn");
        board.place(p3, 2, 2);
        Piece p4 = new Piece(true, board, 4, 4, "pawn");
        board.place(p4, 4, 4);
        Piece p5 = new Piece(false, board, 2, 4, "bomb");
        board.place(p5, 2, 4);
        Piece p6 = new Piece(false, board, 1, 5, "bomb");
        board.place(p6, 1, 5);*/

		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        board.drawBoard();
		while(board.gameInProgress) {
			board.drawBoard();
            if (board.winner() != null) {
                System.out.println(board.winner() + " wins the round.");
                return;
            }
			if (StdDrawPlus.mousePressed()) {
				int x = (int) StdDrawPlus.mouseX();
				int y = (int) StdDrawPlus.mouseY();
				if (board.canSelect(x, y)) {
				board.select(x, y);
				}
			}
			if (StdDrawPlus.isSpacePressed() && board.canEndTurn()) {
				board.endTurn();
			}
			StdDrawPlus.show(10);
		}
	}
}