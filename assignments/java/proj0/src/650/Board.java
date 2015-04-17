public class Board {
	private static Piece[][] pieces;
	private int turn; // 0 for fire, 1 for water
	private Piece selectedPiece;
	private int pieceX;
	private int pieceY;
	private boolean hasMoved;

	public Board(boolean shouldBeEmpty){
		pieces = new Piece[8][8];
		if(!shouldBeEmpty){
			pieces = new Piece[8][8];
			for(int i = 0; i < 4; i += 1){
				pieces[2*i][0] = new Piece(true, this, 2*i, 0, "pawn");
				pieces[2*i + 1][1] = new Piece(true, this, 2*i + 1, 1, "shield");
				pieces[2*i][2] = new Piece(true, this, 2*i, 2, "bomb");
				pieces[2*i+1][7] = new Piece(false, this, 2*i+1, 7, "pawn");
				pieces[2*i][6] = new Piece(false, this, 2*i, 6, "shield");
				pieces[2*i+1][5] = new Piece(false, this, 2*i+1, 5, "bomb"); 
			}
		}
		hasMoved = false;
	}
	private boolean outOfBounds(int x, int y){
		return (x > 7 || x < 0 || y > 7 || y < 0);
	}
	public Piece pieceAt(int x, int y){
		if(outOfBounds(x,y) || pieces[x][y] == null){
			return null;
		}
		return pieces[x][y];
	}
	
	public boolean canSelect(int x, int y){
		if(outOfBounds(x,y)){
			return false;
		}
		if(pieces[x][y] != null){
			/* checks if correct race is selected for the particular turn */
			if(turn == 1 && pieces[x][y].isFire() || turn == 0 && !pieces[x][y].isFire()){
				return false;
			}
			/* cannot select another piece after current selectedPiece has moved */
			if(selectedPiece != null && hasMoved){
				return false;
			}
			return true;
		}
		else{
			/* cannot select empty square without having previously selected piece */
			if(selectedPiece == null){
				return false;
			}
			/* can no longer move if piece has changed positions without a capture */
			if(hasMoved && !selectedPiece.hasCaptured()){
				return false;
			}
			return validMove(pieceX, pieceY, x, y);
		}
	}
	/* sees if move makes geometric sense with respect to the game rules */
	private boolean validMove(int xi, int yi, int xf, int yf){
		if(outOfBounds(xf,yf)){
			return false;
		}
		int dx = xf - xi;
		int dy = yf - yi;
		Piece take = pieceAt((xf+xi)/2, (yf+yi)/2);
		Piece runIn = pieceAt(xf, yf); // utalized only in cases where simple movement is being analyzed
		int dir;
		/* fire pieces and water pieces move in different directions */
		if(turn == 0) dir = 1;
		else		  dir = -1;
		/* allows simple movement */
		if(!selectedPiece.hasCaptured() && Math.abs(dx) == 1 && dy == (1*dir) && runIn == null){
			return true;
		}
		/* allows capture */
		if(Math.abs(dx) == 2 && dy == (2*dir) && take != null && Math.abs(turn - take.side()) == 1){
			return true;
		}
		/* allows for king's multidirectional movement */
		if(!selectedPiece.hasCaptured() && selectedPiece.isKing() && Math.abs(dx) == 1 && Math.abs(dy) == 1 && runIn == null){
			return true;
		}
		/* allows for king's multidirectional captures */
		if(selectedPiece.isKing() && Math.abs(dx) == 2 && Math.abs(dy) == 2 && take != null && Math.abs(turn - take.side()) == 1){
			return true;
		}
		return false;
	}
	/* selects a piece or moves a selected piece to inputed position. Assumes that canSelect 
	 * has return True */
	public void select(int x, int y){
		pieceX = x;
		pieceY = y;
		if(pieces[x][y] == null){
			selectedPiece.move(x,y);
			hasMoved = true;
		}
		else{
			selectedPiece = pieces[x][y];
		}
	}
	/* if a valid point, places piece upon it. otherwise, does nothing */
	public void place(Piece p, int x, int y){
		if(outOfBounds(x,y)){
			return;
		}
		pieces[x][y] = p;
	}
	/* if input is a valid point with a piece on it, removes piece and returns it. 
	 * Otherwise, it prints messages acknowleding the point to be out of bounds or an 
	 * empty square */
	public Piece remove(int x, int y){
		if(outOfBounds(x,y)){
			System.out.println("out of bounds");
			return null;
		}
		if(pieces[x][y] == null){
			System.out.println("no piece at this point");
			return null;
		}
		Piece temp = pieces[x][y];
		pieces[x][y] = null;
		return temp;
	}

	public boolean canEndTurn(){
		return hasMoved;
	}

	public void endTurn(){
		turn = 1 - turn;
		if(selectedPiece != null){
			selectedPiece.doneCapturing();
		}
		selectedPiece = null;
		hasMoved = false;
	}

	public String winner(){
		boolean fire = false;
		boolean water = false;
		/* checks board for fire and water pieces */
		for(int i = 0; i < 8; i += 1){
			for(int j = 0; j < 8; j += 1){
				Piece p = pieces[i][j];
				if (p != null){
					if(p.isFire()) fire = true;
					else		   water = true;
				}
			}
		}
		if (fire && !water){
			return "Fire";
		}
		else if(!fire && water){
			return "Water";
		}
		else if(!fire && !water){
			return "No one";
		}
		else {
			return null;
		}
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if(pieces[i][j] == selectedPiece && selectedPiece != null){
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);   
                if (pieces[i][j] != null) {
                	Piece p = pieces[i][j];
                	String path = "img/";
                	if(p.isBomb()){
                		path += "bomb";
                	}
                	else if(p.isShield()){
                		path += "shield";
                	}
                	else{
                		path += "pawn";
                	}
                	if(p.isFire()){
                		path += "-fire";
                	}
                	else{
                		path += "-water";
                	}
                	if(p.isKing()){
                		path += "-crowned";
                	}
                	path += ".png";
                	StdDrawPlus.picture(i + .5, j + .5, path, 1, 1);
                }
                else{
                	if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5); 
                }
            }
        }
    }
 
	public static void main(String[] args){
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if(b.canSelect(x,y)){
	                b.select(x,y);
	            }
            }      
            if(StdDrawPlus.isSpacePressed() && b.canEndTurn()){
            	b.endTurn();
            }
            String result = b.winner();
            if(result != null){
            	b.drawBoard(N);
            	System.out.println(result);
            	StdDrawPlus.show(100);
            	break;
            }      
            StdDrawPlus.show(100);
        }
	}
}