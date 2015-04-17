import java.util.Arrays;

public class Board {
    // private Piece[][] pieces;
    private static final int N = 8;
    private static Board checkers61b;
    private static Piece[][] pieces;
    private Piece placepiece;
    private boolean whoseturn; // true = Fire, false = water
    private boolean selected; // true = piece currently selected
    private boolean moved;
    private int firepieces; // tracks number of pieces
    private int waterpieces;
    private int lastx = -100;
    private int lasty = -100;
 
    public static void main(String[] args) {
    	
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);        
        	}
    	}
    	checkers61b = new Board(false);
    	checkers61b.drawBoard(N);
    	while(true) {
    		if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (checkers61b.canSelect((int)x,(int)y)) {
                	checkers61b.select((int)x,(int)y);
                }
        	}
       		if (StdDrawPlus.isSpacePressed()) {
       			if (checkers61b.canEndTurn() == true) {
       				checkers61b.endTurn();
       		}}
        	checkers61b.drawBoard(N);
        StdDrawPlus.show(100);
    	}
    	

	}

    boolean empty;
    public Board(boolean shouldBeEmpty) {
    	empty = shouldBeEmpty;
    	whoseturn = true; // Fire player starts
        selected = false; // Nothing selected at beginning
        moved = false;
    	pieces = new Piece[N][N]; // [column][row]
    	firepieces = 12; waterpieces = 12;
        if (empty == false) {
    		int b = 0;
    		while (b < N) { 
    			pieces[b][0] = new Piece(true, this, b, 0, "pawn");         // Fire  			
    			pieces[b+1][1] = new Piece(true, this, b+1, 1, "shield");
    			pieces[b][2] = new Piece(true, this, b, 2, "bomb");
    			b = b + 2;
    		}
			int c = 0;
			while (c < N) {
				pieces[c+1][N-1] = new Piece(false, this, c+1, N-1, "pawn");  // Water
				pieces[c][N-2] = new Piece(false, this, c, N-2, "shield");
				pieces[c+1][N-3] = new Piece(false, this, c+1, N-3, "bomb");
				c = c + 2;
			} // Setting up initial board
        }
        
    }

    private void drawBoard(int N) {
		String side = null;
		String piecetype = null;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (pieces[i][j] == null && (i%2 == 0) && (j%2==0)) {
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				}
				if (pieces[i][j] != null) {
					if (pieces[i][j].isFire()){
						side = "fire";
					}
					if (pieces[i][j].isFire() == false) {
						side = "water";
					}
					if (pieces[i][j].isShield()) {
						piecetype = "shield";
					}
					if (pieces[i][j].isBomb()) {
						piecetype = "bomb";
					}
					if (pieces[i][j].isBomb() == false && pieces[i][j].isShield() == false) {
						piecetype = "pawn";
					}
					String total_string = "img/" + piecetype + "-" + side +".png";
					StdDrawPlus.picture(i + 0.5, j + 0.5, total_string, 1, 1);					
				}
			}
		}
    	
	}

    private boolean piecechecker(int x,int y){
    	if (pieces[x][y] == null)
    		return false;
    	else
    		return true;
    } 
	public Piece pieceAt(int x, int y) {
		if (x > N-1 || y > N-1) {
			return null;
		}
		if (pieces[x][y] != null) {
			return pieces[x][y];
		}
		else {
			return null;
		}
    }
   
    public boolean canSelect(int x, int y) {
    	if (piecechecker(x,y)) { 					  										// if there is a piece at x,y
    		if (pieceAt(x,y).isFire() == whoseturn) { 										// if Fire player is selecting fire piece
    			if ((selected == false) || ((selected == true) && (moved == false)))		// if 
    				return true;
    		}
    		else
    			return false;
    	}
    	else if (pieces[x][y] == null) {
    		if ((moved == false) && (selected == true) && validMove(lastx, lasty, x, y))
    			return true;
    	}
		return false;
	}
        
    private boolean validMove(int xi, int yi, int xf, int yf) {
    	if (xi < 0 || xi > 7 || yi < 0 || yi > 7 || xf < 0 || xf > 7 || yf < 0 || yf > 7)
    		return false;
    	else if (pieces[xi][yi].isFire() == whoseturn && pieces[xf][yf] == null) {
    		if (Math.abs(xi-xf) == 1) {
    			/*if (pieces[xi][yi].isKing()) {
    				return true;
    			}*/
    			if (whoseturn == true && yf-yi == 1)
    				return true;
    			else if (whoseturn == false && yf-yi == -1)
    				return true;
    		}
    		else if (Math.abs(xi-xf) == 2) {
    			/*if (pieces[xi][yi].isKing()) {
    				return true;
    			}*/
    			if ((whoseturn == true) && (yf-yi == 2) && (pieces[(xi+xf)/2][(yi+yf)/2].isFire() == false))
    				return true;
    			else if ((whoseturn == false) && (yf-yi == -2) && (pieces[(xi+xf)/2][(yi+yf)/2].isFire() == true))
    				return true;
    		}
    	}
    	return false;
    }

    public void select(int x, int y) {
    	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    	StdDrawPlus.filledSquare(x + .5, y + .5, .5);

		if (piecechecker(x,y)) {
			lastx = x;
			lasty = y; // preps for movement
			selected = true;
			//System.out.println(lastx);
			//System.out.println(lasty);
		}
		else { //selected empty square
			pieces[lastx][lasty].move(x,y); // takes last selected piece, moves to new coordinates
			moved = true;
			//System.out.println("Moved piece!");
			checkers61b.drawBoard(N);
		}
    }
	
    /*private boolean kingchecker(){
    	for (int i =0; i < N; i++) {
    		if (pieces[i][N] )
    	}
    */


    public void place(Piece p, int x, int y) {
    	placepiece = p;
    	if ((x < N || y < N) && (placepiece != null)){
    		pieces[x][y] = placepiece;
    	}
    }
    private Piece removed_piece;
    public Piece remove(int x, int y) {
    	if ((x > N-1 || y > N-1) || (pieces[x][y] == null)) {
    		System.out.print("Error!");
    		return null;
    	}
    	else {
    		removed_piece = pieces[x][y];
    		pieces[x][y] = null;
    		if (removed_piece.isFire())
    			firepieces = firepieces - 1;
    		else
    			waterpieces = waterpieces - 1;
    		return removed_piece;
    	}
    }
	private void bombremover(int x, int y){
		if (pieces[x][y].isShield() == false)
			remove(x,y);
	}
    public boolean canEndTurn() {
    	if (moved = true)
    		return true;
    	else
    		return false;
    }

    public void endTurn() {
		selected = false;
		moved = false;
		whoseturn = !whoseturn;    	
    }

    public String winner() {
    	if (waterpieces==0 && firepieces==0)
    		return "No one";
    	if (waterpieces == 0 && firepieces !=0)
    		return "Fire";
    	if (firepieces == 0 && waterpieces != 0)
    		return "Water";
    	else
    		return null;
    }




}