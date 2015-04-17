/**
 *  @author Kuan Chang
 *  @login cs61b-aic
 */

public class Board {
	/** Location of pieces. */
	private int N = 8;
    private Piece[][] pieces = new Piece[N][N];
    private boolean fire_turn = true;
    private int selected_x;
    private int selected_y;
    private boolean move_made = false;
    private boolean capture_made = false;
    private Piece selected_piece;

    public Board(boolean shouldBeEmpty) {
    	if (!shouldBeEmpty)
    		startingBoard(this);
    }

    /** Draws piece. */
    private void drawPiece (int x, int y, Piece piece) { //MODIFY LATER FOR KINGS
    	if(piece.isBomb()){
    		if (piece.isFire()){
    			if(piece.isKing())
    				StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
    			else
    				StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
    		}
    		else{
				if(piece.isKing())
    				StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
    			else
    				StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);

    		}
    	}

    	else if(piece.isShield()){
    		if (piece.isFire()){
    			if(piece.isKing())
    				StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
    			else
    				StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
    		}
    		else{
				if(piece.isKing())
    				StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
    			else
    				StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);

    		}
    	}

    	else {
    		if (piece.isFire()){
    			if(piece.isKing())
    				StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
    			else
    				StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
    		}
    		else{
				if(piece.isKing())
    				StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
    			else
    				StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
    		}
    	}

    }

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private void startingBoard(Board b) {
    	for (int i = 0; i < 8; i += 2) {
    		place(new Piece(true, b, i, 0, "pawn"), i, 0);
    		place(new Piece(true, b, i+1, 1, "shield"), i+1, 1);
    		place(new Piece(true, b, i, 2, "bomb"), i, 2);
    		place(new Piece(false, b, i+1, 5, "bomb"), i+1, 5);
    		place(new Piece(false, b, i, 6, "shield"), i, 6);
    		place(new Piece(false, b, i+1, 7, "pawn"), i+1, 7);
    	}
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0)
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else 
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if (pieceAt(i, j) != null) {
                	drawPiece(i, j, pieceAt(i, j));
                }
            }
        }

        if (selected_piece != null){
       		StdDrawPlus.filledSquare(selected_x + .5, selected_y + .5, .5);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieceAt(i, j) != null) {
                	drawPiece(i, j, pieceAt(i, j));
                }
            }
        }
    }

    public Piece pieceAt(int x, int y) {
    	if (x > 7 || y > 7 || x < 0 || y < 0) {
    		return null;
    	}
    	return pieces[x][y];
    }

    public void place(Piece p, int x, int y) {
    	if (!(x > 7 || y > 7 || x < 0 || y < 0) && p != null) {
    		if (p == selected_piece)
    			remove(selected_x, selected_y);
    		pieces[x][y] = p;
    	}
    }

    public Piece remove(int x, int y) {
    	if (x > 7 || y > 7) {
    		System.out.println("Coordinates out of bounds!");
    		return null;
    	}
		if (pieceAt(x, y) == null){
			System.out.println("No piece at " + x + ", " + y + "!");
		}
		Piece temp = pieceAt(x, y);
		pieces[x][y] = null;

		return temp;
    }

    public String winner () {
    	int fire_count = 0;
    	int water_count = 0;
    	for(int i = 0; i <= 7; i++) {
    		for(int j = 0; j <= 7; j++){
    			if(pieceAt(i,j) != null){
	    			if (pieceAt(i,j).isFire())
	    				fire_count += 1;
	    			else
	    				water_count += 1;
    			}
    		}
    	}

    	if (fire_count == 0 && water_count == 0)
    		return "No one";
    	else if (fire_count == 0)
    		return "Water";
    	else if (water_count == 0)
    		return "Fire";
    	else
    		return null;
    }

    private boolean validMove (int x, int y) { // MODIFY LATER FOR KING PIECES
    	if ((x + y) % 2 != 0)
    		return false; // clicks on red spot
    	else if (fire_turn) { // fire turn
    		if (selected_piece.isKing()) { //if kinged
    			if (Math.abs(x - selected_x) > 2 || Math.abs(y - selected_y) > 2 || Math.abs(y - selected_y) < 1)
		    		return false; // spaces not allowed
		    	else if(Math.abs(x - selected_x) == 1 && Math.abs(y - selected_y) == 1){ // move one over
		    		if (move_made || capture_made) //checks to see if move or capture was already made
		    			return false; 
		    		return true;
		    	}
		    	else if (pieceAt((x+selected_x)/2, (y+selected_y)/2) != null){
		    		if (!pieceAt((x+selected_x)/2, (y+selected_y)/2).isFire() && !move_made)
		    			return true; // piece available to jump and no move made before (capture before doesn't matter)
		    		return false;
		    	}
		    	else
		    		return false;
    		}
			else {
		    	if (Math.abs(x - selected_x) > 2 || y - selected_y > 2 || y - selected_y < 1)
		    		return false; // spaces not allowed
		    	else if(Math.abs(x - selected_x) == 1 && y - selected_y == 1){ // move one over
		    		if (move_made || capture_made) //checks to see if move or capture was already made
		    			return false; 
		    		return true;
		    	}
		    	else if (pieceAt((x+selected_x)/2, (y+selected_y)/2) != null){
		    		if (!pieceAt((x+selected_x)/2, (y+selected_y)/2).isFire() && !move_made)
		    			return true; // piece available to jump and no move made before (capture before doesn't matter)
		    		return false;
		    	}
		    	else
		    		return false;
	    	}
    	}

    	else { // if water turn
	    	if (selected_piece.isKing()){ //if king
	    		if (Math.abs(x - selected_x) > 2 || Math.abs(selected_y - y) > 2 || Math.abs(selected_y - y) < 1)
		    		return false; // spaces not allowed
		    	else if(Math.abs(x - selected_x) == 1 && Math.abs(selected_y - y) == 1){ // move one over
		    		if (move_made || capture_made) //checks to see if move or capture was already made
		    			return false; 
		    		return true;
		    	}
		    	else if (pieceAt((x+selected_x)/2, (y+selected_y)/2) != null){
		    		if (pieceAt((x+selected_x)/2, (y+selected_y)/2).isFire() && !move_made)
		    			return true; // piece available to jump and no move made before (capture before doesn't matter)
		    		return false;
	    	}
	    	else
	    		return false;
	    	}
	    	else {
		    	if (Math.abs(x - selected_x) > 2 || selected_y - y > 2 || selected_y - y < 1)
		    		return false; // spaces not allowed
		    	else if(Math.abs(x - selected_x) == 1 && selected_y - y == 1){ // move one over
		    		if (move_made || capture_made) //checks to see if move or capture was already made
		    			return false; 
		    		return true;
		    	}
		    	else if (pieceAt((x+selected_x)/2, (y+selected_y)/2) != null){
		    		if (pieceAt((x+selected_x)/2, (y+selected_y)/2).isFire() && !move_made)
		    			return true; // piece available to jump and no move made before (capture before doesn't matter)
		    		return false;
		    	}
		    	else
		    		return false;
	    	}
    	}
    }

    public boolean canSelect(int x, int y) {
    	if (x > 7 || y > 7 || x < 0 || y < 0) { // if out of bounds
    		return false;
    	}
    	else if (pieceAt(x, y) != null) { // if piece is selected
    		if (move_made || capture_made) // if move or capture is already made
    			return false;
			else if (fire_turn) { //checks whose turn it is
				if (pieceAt(x, y).isFire()) //checks i
					return true;
				return false;
			}
			else {
				if (pieceAt(x, y).isFire()) //checks i
					return false;
				return true;
			}
		}
		else { // if selected empty space
			if (selected_piece == null) // if no piece was previously selected
				return false;
			else
				return validMove(x, y); // if wants to move or jump
		}
    }

    public void select (int x, int y) {
    	if (pieceAt(x,y) != null){ // if selecting a piece
    		selected_piece = pieceAt(x,y);
    		selected_x = x;
    		selected_y = y;
    	}
    	else { // if moving or jumping
    		place(selected_piece, x, y);
    		selected_x = x;
    		selected_y = y;
    		selected_piece.move(x, y);
    		if(selected_piece.hasCaptured()){
    			if (selected_piece.isBomb())
    				selected_piece = null;
				capture_made = true;
			}
    		else
    			move_made = true;
    	}
    }

    public boolean canEndTurn() {
    	if (move_made || capture_made)
    		return true;
    	return false;
    }

    public void endTurn() {
    	fire_turn = !fire_turn;
    	move_made = false;
    	capture_made = false;
    	if (selected_piece != null) {
	    	selected_piece.doneCapturing();
	    	selected_piece = null;
    	}
    }

	public static void main(String[] args) {
	    Board board = new Board (false);
	    StdDrawPlus.setXscale(0, board.N);
	    StdDrawPlus.setYscale(0, board.N);

	    /** Monitors for mouse presses. Wherever the mouse is pressed,
	        a new piece appears. */

	    while(true) {
	        board.drawBoard(board.N);
	        StdDrawPlus.show(100);

	        if (board.winner() != null){
            	System.out.println("The game has ended. " + board.winner() + " is the winner!");
            	break;
            }

	        if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect((int) x, (int) y))
                	board.select((int) x,(int) y);
            }

            if (StdDrawPlus.isSpacePressed()) {
            	if (board.canEndTurn()){
            		board.endTurn();
            	}
            }
	    }
	}
}