public class Board {

	private Piece[][] pieces;
	private int N;
	private int xSelect;
	private int ySelect;
	private Boolean selection;
	private Boolean playerSelection; //true for fire
	private Boolean moveDone;

	public Board(boolean shouldBeEmpty) {
		this.N = 8;
		this.pieces = new Piece[N][N];
		if(!shouldBeEmpty)
			initialPieces(N);
		selection = false;
        moveDone = false;
		playerSelection = true;
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
                if(selection && (xSelect == i) && (ySelect == j)) {
        			this.select(xSelect, ySelect);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(xSelect + .5, ySelect + .5, .5);
                }
                if (pieceAt(i, j) != null) {		
                	Piece drawer = pieceAt(i, j);
                	String path = "img/";
                	if(drawer.isBomb())
                		path += "bomb";
                	else if(drawer.isShield())
                		path += "shield";
                	else
                		path += "pawn";

                	if(drawer.isFire())
                		path += "-fire";
                	else
                		path += "-water";

                	if(drawer.isKing())
                		path += "-crowned.png";
                	else {
                		path += ".png";
                	}
                    StdDrawPlus.picture(i + .5, j + .5, path, 1, 1);
                }
            }
        }
    }

    private void initialPieces(int N) {
        for(int i = 0; i < N; i = i+2) {
            Piece redPawn = new Piece(true, this, i, 0, "pawn");
            place(redPawn, i, 0);
            Piece redBomb = new Piece(true, this, i, 2, "bomb"); //red bomb
            place(redBomb, i, 2);
            Piece blueShield = new Piece(false, this, i, 6, "shield"); //blue shield
            place(blueShield, i, 6);
        }
        for(int i = 1; i < N; i = i+2) {
            Piece redShield = new Piece(true, this, i, 1, "shield"); //red shield
            place(redShield, i, 1);
            Piece blueBomb = new Piece(false, this, i, 5, "bomb");  //blue bomb
            place(blueBomb, i, 5);
            Piece bluePawn = new Piece(false, this, i, 7, "pawn");  //blue pawn
            place(bluePawn, i, 7);
        }
    }

    public Piece pieceAt(int x, int y) {
    	if(x >= N || y >= N || x < 0 || y < 0)
    		return null;
    	else
    		return pieces[x][y];
    }

    public void place(Piece p, int x, int y) {
    	if(p == null) 
    		return;
    	for(int i = 0; i < this.N; i++) {
    		for(int j = 0; j < this.N; j++) {
    			if(pieceAt(i, j) == p) {
    				this.remove(i, j);
    			}
    		}
    	}
    	this.pieces[x][y] = p;
    }

    public Piece remove(int x, int y) {
    	if(x >= N || y >= N || x < 0 || y < 0) {
    		System.out.println("Failed to remove piece; out of bounds");
    		return null;
    	}
    	Piece a = pieceAt(x, y);
    	if(a == null) {
    		System.out.println("Failed to remove piece; no Piece at requested location");
    		return null;
    	}
    	else {
    		pieces[x][y] = null;
    		return a;
    	}
    }

    public void select(int x, int y) {
    	selection =  true;
    	// xSelect = x;
    	// ySelect = y;  
        if((pieceAt(xSelect, ySelect) != null) && (pieceAt(x,y) == null)) {
            pieceAt(xSelect, ySelect).move(x, y);
            moveDone = true;
        }
        xSelect = x;
        ySelect = y;
    }

    public boolean canSelect(int x, int y) {
    	Piece p = pieceAt(x, y);
        //tests if there is a piece there, and if there is a piece being selected is friendly
    	if((p != null) && (p.isFire() == playerSelection)) {
            //if piece is already selected  
    		if(selection) {
                //if a move is already done, can't select a piece
    			if(moveDone)
    				return false;
    			else
    				return true;
    		}
            //if no piece is selected say yes
    		else
    			return true;
    	}
        //space selected, test if piece already selected
        else if(selection) {
            //test if you can move there
            if(validMove(xSelect, ySelect, x, y)) {
            //if no move done yet, say ok
                if(!moveDone)
                    return true;
                //test if move and successful capture has been done
                else if(moveDone && pieceAt(xSelect, ySelect).hasCaptured() && ((Math.abs(xSelect - x) > 1) && Math.abs(ySelect - y) > 1))
                    return true;
                else
                    return false;
            }
            else
                return false;
        }
    	else
    		return false;
    	
    }

    private boolean validMove(int xi, int yi, int xf, int yf) { 
    	int xdiff = xf - xi;
    	int ydiff = yf - yi;
    	Piece currentPiece = pieceAt(xi, yi);
        if(currentPiece == null)
            return false;
        if(!(((ydiff > 0) == currentPiece.isFire()) || currentPiece.isKing())){
            return false;
        }
        //can't move on top of a piece
    	if(pieceAt(xf, yf) != null)
    		return false;

        //can't move out of bounds
    	else if(xf >= N || yf >= N || xf < 0 || yf < 0)
    		return false;
        //cant move vertically
    	else if((xi == xf) || (yi == yf))
    		return false;
        //can only move properly diagonally
    	else if((Math.abs(xdiff) != Math.abs(ydiff)))
    		return false;
        //can only move less than 2 diagonally
    	else if((Math.abs(xdiff) <= 2) || (Math.abs(ydiff) <= 2)) {
            //capture move condition
			if((Math.abs(xdiff) == 2) || (Math.abs(ydiff) == 2)) {
                //ensure enemy piece is in middle of them
                Piece capPiece = pieceAt(xi + (xdiff/2), yi + (ydiff/2));
				if((capPiece != null) && (capPiece.isFire() != currentPiece.isFire()))
					return true;
				return false;
            }
    		else
    			return true;
        }
    	else
    		return false;	
	}

    public boolean canEndTurn() {
        return moveDone;
    }

    public void endTurn() {
        playerSelection = !playerSelection;
        selection = false;
        moveDone = false;
        if(pieceAt(xSelect,ySelect) != null)
            pieceAt(xSelect, ySelect).doneCapturing();
    }

    public String winner() {
        int fire = countPieces(true);
        int water = countPieces(false);
        if(fire == 0) {
            if(water == 0) {
                return "No one";
            }
            return "Water";
        }
        else if(water == 0){
            return "Fire";
        }
        else 
            return null;                                                              
    }

    private int countPieces(boolean side) {
        Piece a;
        int counter = 0;
        for(int i = 0; i < this.N; i++) {
            for(int j = 0; j < this.N; j++) {
                a = pieceAt(i, j);
                if(a != null) {
                    if(a.isFire() == side) {
                        counter++;
                    }
                }
            }
        }
        return counter; 
    }

    public static void main(String[] args) {
   		Board a = new Board(false);
        StdDrawPlus.setXscale(0, a.N);
        StdDrawPlus.setYscale(0, a.N);
        while(true) {
            a.drawBoard(a.N);
            if(StdDrawPlus.mousePressed()) {
                int mx = (int)StdDrawPlus.mouseX();
                int my = (int)StdDrawPlus.mouseY();
                if(a.canSelect(mx, my))
            	   a.select(mx, my);

            }
            if(StdDrawPlus.isSpacePressed() && a.canEndTurn()) {
                a.endTurn();
            }
            if(a.winner() != null) {
                System.out.println(a.winner());
                return;
            }
            StdDrawPlus.show(25);
        }
	}
}