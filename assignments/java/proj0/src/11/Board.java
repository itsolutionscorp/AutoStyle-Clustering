/** 
 *  @author Andrew Gleeson
 */

public class Board {

	private static final int BOARD_SIZE = 8;

	// INSTANCE VARIABLES

	private Piece[][] boardData;

	private Piece selected;

	private boolean waters_turn;
	private boolean has_gone;
	private boolean in_multi;
	private boolean just_started;

	private Piece last_destroyed;

	private boolean gui; //Stupid hack to get around ridiculous design flaws

	// PUBLIC FUNCTIONS
	
	// Starts a GUI supported version of the game.
	public static void main (String args[]) {
		Board b = new Board(false);
		b.gui = true;

		StdDrawPlus.setCanvasSize(1024, 1024);
        StdDrawPlus.setXscale(0, BOARD_SIZE);
        StdDrawPlus.setYscale(0, BOARD_SIZE);


        

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        do {
            drawBoard(b);

			while (!StdDrawPlus.mousePressed() && !StdDrawPlus.isSpacePressed()) {

			}

            if (StdDrawPlus.mousePressed()) {

                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                System.out.println("Can select (" + x + ", " + y + "): " + b.canSelect(x, y));
                if(b.canSelect(x, y)){
                	b.select(x, y);
                }
                
                
            }   

            if (StdDrawPlus.isSpacePressed()) {

                if(b.canEndTurn()){
    				b.endTurn();
    			}
                
            }  
            StdDrawPlus.show(100);         
            
        } while(b.winner() == null);

        System.out.println(b.winner());
	}

	// The constructor for Board. If shouldBeEmpty is true, initializes an empty Board. Otherwise, initializes a 
	// Board with the default configuration. Note that an empty Board could possibly be useful for testing purposes.
	public Board(boolean shouldBeEmpty) {

		boardData = new Piece[BOARD_SIZE][BOARD_SIZE];
		just_started = true;

		if( shouldBeEmpty ) return;

		setup_board();
	}

	// Gets the piece at position (x, y) on the board, or null if there is no piece. If (x, y) are out of bounds, returns null.
	public Piece pieceAt(int x, int y) {
		if( valid_position(x, y) ){
			return boardData[x][y];
		} else {
			return null;
		}
	}

	// Returns true if there is a piece the piece at (x, y) and it can be selected.
	// 	A piece may be selected if it is the corresponding player’s turn and one of the following is true:
	// 		The player has not selected a piece yet.
	// 		The player has selected a piece, but did not move it.
	// 		An empty square may be selected if one of the following is true:
	public boolean canSelect(int x, int y) {

		if(!empty(x, y)){
			if(has_gone || in_multi) return false;
			if (pieceAt(x, y).isFire() == waters_turn) return false;
			if (selected == null) return true;
			if (!has_gone) return true;
		} else {

			if (selected != null && !has_gone) {
				int[] pos = getPosition(selected);
				if(validMoveForPiece(selected, pos[0], pos[1], x, y)){
					return true;
				} else {
					return false;
				}
			}

			if(selected != null && selected.hasCaptured()){
				int[] pos = getPosition(selected);
				if(validMoveForPiece(selected, pos[0], pos[1], x, y)){
					return true;
				} else {
					return false;
				}
			}


		}

		return false;
		//return ( !empty(x, y) ) && ( pieceAt(x, y).isFire() != waters_turn ) && (  || !canEndTurn() );
	}

	// Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf),
	// strictly from a geometry/piece-race point of view. validMove does not need to take into 
	// consideration whose turn it is or if a move has already been made, but rather can. 
	// Update (2/6): validMove is not required, and will not be tested. You may implement this 
	// however you want. It is suggested you use this method to simplify your implementation of canSelect.
	private boolean validMove(int xi, int yi, int xf, int yf) {
		int dx = xf-xi;
		int dy = yf-yi;


		if(!valid_position(xf, yf) || !empty(xf, yf)){
			return false;
		}

		//check if valid place, regardless of other pieces

		if( ( Math.abs(dx) == Math.abs(dy) ) && ( Math.abs(dx) <= 2 ) && (dx != 0) ) { // a diagonal move, of positive magnitude, but not more than 2

			if( Math.abs(dx) == 1 ) { // a non-capture move
				if( empty(xf, yf) ){
					return true;
				} else {
					return false;
				}
			} else { // a capture move
				if( empty(xf, yf) && !empty(xi + dx/2, yi + dy/2) ){ //still need to make sure it is of a different team
					return true;
				} else {
					return false;
				}
			}

		} else {
			return false;
		}
	}

	// Selects the piece at (x, y) if possible. Optionally, it is recommended to color the background 
	// of the selected square white on the GUI via the pen color function. For any piece to perform a capture, 
	// that piece must have been selected first.
	public void select(int x, int y) {

			

			just_started = false;

			if(!empty(x, y)){
				selected = pieceAt(x, y);
				
			}else{

				int[] pos = getPosition(selected);

				
	    		if ( validMoveForPiece(selected, pos[0], pos[1], x, y) ) {
	    			System.out.println("CALLING MOVE");
	    			selected.move(x, y);
	    		}

        	}

        	System.out.println("selected: " + selected);

        	//JUST VISUAL STUFF AFTER
        	if(!gui || (has_gone && !in_multi) )return;

        	
			drawSelected(this);
        	
	}

	// Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. If p already exists 
	// in the current Board, first removes it from its initial position. If another piece already exists at (x, y), 
	// p will replace that piece. (This method is potentially useful for creating specific test circumstances.)		
	public void place(Piece p, int x, int y) {

		System.out.println("(" + x + ", " + y + ")");

		if( valid_position(x, y) && p != null ){

			if(pieceAt(x, y) == p){
				return;
			}

			boolean destroyed = false;
			int[] pos = getPosition(p);

			if ( pos != null ) {
				int dx = x - pos[0];
				int dy = y - pos[1];

				boardData[x][y] = p;
				boardData[pos[0]][pos[1]] = null;

				if( Math.abs(dx) == 1 ){
	    			has_gone = true;
	    			selected = null;
	    		}

				if( Math.abs(dx) == 2 ) { // a capture move
					System.out.println("capture");
					last_destroyed = pieceAt(pos[0] + dx/2, pos[1] + dy/2);

					boardData[x][y] = p;

					if(p.isBomb()){
						
						destroy(x, y, true, false);
						destroyed = true;

					}

					if(!has_valid_jump(p)){
						System.out.println("Done");
						has_gone = true;
						selected = null;
						in_multi = false;
						//drawBoard(this);
					}else{
						has_gone = true;
						in_multi = true;
						System.out.println("in multi");
						//select(x,y);
					}
				}

				boardData[pos[0]][pos[1]] = null;
			}

			//remove(x, y);
			if(!destroyed) boardData[x][y] = p;
			//p.move(x, y);
			if(gui) drawBoard(this);
		}
	}

	// Executes a remove. Returns the piece that was removed. If the input (x, y) is out of bounds, returns null 
	// and prints an appropriate message. If there is no piece at (x, y), returns null and prints an appropriate message.
	public Piece remove(int x, int y) {
		if( !valid_position(x, y) ) {
			System.out.println("(" + x + ", " + y + ") is not a valid position.");
			return null;
		}

		if( empty(x, y) ) {
			System.out.println("(" + x + ", " + y + ") is empty.");
			return null;
		}

		Piece p = pieceAt(x, y);
		destroy(x, y, false, false);
		
		if(gui) drawBoard(this);
		return p;

	}

	// Returns whether or not the the current player can end their turn. To be able to end a turn, a piece must have 
	// moved or performed a capture.
	public  boolean canEndTurn() {
		return has_gone;
	}

	// Called at the end of each turn. Handles switching of players and anything else that should happen at the end of a turn.
	public void endTurn() {
		
			waters_turn = !waters_turn;
			has_gone = false;
			just_started = false;
		
		
	}
 

	// Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board), or null if the game is not yet over. 
	// Assume there is no stalemate situation in which the current player has pieces but cannot legally move any of them 
	// (In this event, just leave it at null). Determine the winner solely by the number of pieces belonging to each team.
	public String winner() {

		if(just_started){
			return null;
		}
		int fire_pieces = 0;
		int water_pieces = 0;
		for (Piece[] row : boardData) {
			for (Piece p : row) {
				if ( p != null ) {
					if ( p.isFire() ) {
						fire_pieces++;
					} else {
						water_pieces++;
					}
				}
			}
		}

		int sum = fire_pieces + water_pieces;
		if ( sum == 0 ) {
			return "No one";
		}

		if ( sum == fire_pieces ) {
			return "Fire";
		}

		if ( sum == water_pieces ) {
			return "Water";
		}	

		return null;
	}
	


	// PRIVATE FUNCTIONS

	private boolean validMoveForPiece(Piece p, int xi, int yi, int xf, int yf) {

		//System.out.println("at: (" + xi + ", " + yi + ")");

		if(!validMove(xi, yi, xf, yf)) {
			return false;
		}

		boolean result = true;

		//UPWARDS MOVES
        if (p.isKing() || p.isFire()){
        	if (yf-yi < 0) {
        		result = false;
        	}
       	}
        
        //DOWNWARDS MOVES
       	if (p.isKing() || !p.isFire()){
       		if (yf-yi > 0) {
        		result = false;
        	}
       	}

       	int dx = xf - xi;
       	int dy = yf - yi;


       	if(Math.abs(dx) == 1 && p.hasCaptured()){
       		return false;
       	}

       	

       	if(dx == 2 && dy == 2){
       		if(p.isFire() != pieceAt(xi + 1, yi + 1).isFire()){
       			result = true;
       		}
       	}

       	if(dx == 2 && dy == -2){
       		if(p.isFire() != pieceAt(xi + 1, yi - 1).isFire()){
       			result = true;
       		}
       	}

       	if(dx == -2 && dy == -2){
       		if(p.isFire() != pieceAt(xi - 1, yi - 1).isFire()){
       			result = true;
       		}
       	}

       	if(dx == -2 && dy == 2){
       		if(p.isFire() != pieceAt(xi - 1, yi + 1).isFire()){
       			result = true;
       		}
       	}


       	return result;

	}

	private boolean has_valid_jump(Piece p){
		int[] pos = getPosition(p);

		if(pos == null){
			return false;
		}

		boolean result = validMoveForPiece(p, pos[0], pos[1], pos[0]+2, pos[1]+2) ||
				validMoveForPiece(p, pos[0], pos[1], pos[0]+2, pos[1]-2) ||
				validMoveForPiece(p, pos[0], pos[1], pos[0]-2, pos[1]+2) ||
				validMoveForPiece(p, pos[0], pos[1], pos[0]-2, pos[1]-2);

		System.out.println("Can jump:" + result);

		return result;

	}

	private boolean has_valid_move(Piece p){
		int[] pos = getPosition(p);

		if(pos == null) return false;

		return 	validMoveForPiece(p, pos[0], pos[1], pos[0]+1, pos[1]+1) ||
				validMoveForPiece(p, pos[0], pos[1], pos[0]+2, pos[1]+2) ||
				validMoveForPiece(p, pos[0], pos[1], pos[0]+1, pos[1]-1) ||
				validMoveForPiece(p, pos[0], pos[1], pos[0]+2, pos[1]-2) ||
				validMoveForPiece(p, pos[0], pos[1], pos[0]-1, pos[1]+1) ||
				validMoveForPiece(p, pos[0], pos[1], pos[0]-2, pos[1]+2) ||
				validMoveForPiece(p, pos[0], pos[1], pos[0]-1, pos[1]-1) ||
				validMoveForPiece(p, pos[0], pos[1], pos[0]-2, pos[1]-2);

	}

	private boolean valid_position(int x, int y) {
		return (x >= 0)
				&& (x < BOARD_SIZE)
				&& (y >= 0)
				&& (y < BOARD_SIZE);
	}

	private boolean empty(int x, int y) {
		return pieceAt(x, y) == null;
	}

	private int[] getPosition(Piece p){
		for (int x = 0; x < BOARD_SIZE; x++) {
			for (int y = 0; y < BOARD_SIZE; y++) {
				if ( boardData[x][y] == p ) {
					int[] pos = {x, y};
					return pos;
				}
			}
		}

		return null;
	}

	private static void drawBoard(Board b) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);

                

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                if ( !b.empty(i, j) ) {
                	if(b.pieceAt(i, j).isFire() != b.waters_turn){
                		StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
                		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                		StdDrawPlus.filledSquare(i + .5, j + .5, .4);
                		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	}
                    StdDrawPlus.picture(i + .5, j + .5, getImage(b.pieceAt(i, j)), 1, 1);
                }
            }
        }

        drawSelected(b);

    }

    private static void drawSelected(Board b){

    		if(b.selected == null){
    			return;
    		}

    		int[] pos = b.getPosition(b.selected);
    		int x = pos[0];
    		int y = pos[1];
    		Piece selected = b.selected;

    		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        	StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        	StdDrawPlus.picture(x + .5, y + .5, b.getImage(selected), 1, 1);

        	StdDrawPlus.setPenRadius(.006);

        	//UPWARDS MOVES
        	if (selected.isKing() || selected.isFire()){
	        	if( b.validMoveForPiece(selected, x, y, x+1, y+1) ){
	        		StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
	        		StdDrawPlus.square(x + 1 + .5, y + 1 + .5, .5);
	        	}

	        	if( b.validMoveForPiece(selected, x, y, x+2, y+2) ){
	        		StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
	        		StdDrawPlus.square(x + 2 + .5, y + 2 + .5, .5);
	        	}

	        	if( b.validMoveForPiece(selected, x, y, x-1, y+1) ){
	        		StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
	        		StdDrawPlus.square(x - 1 + .5, y + 1 + .5, .5);
	        	}

	        	if( b.validMoveForPiece(selected, x, y, x-2, y+2) ){
	        		StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
	        		StdDrawPlus.square(x - 2 + .5, y + 2 + .5, .5);
	        	}
        	}

        	//DOWNWARDS MOVES
        	if (selected.isKing() || !selected.isFire()){
	        	if( b.validMoveForPiece(selected, x, y, x+1, y-1) ){
	        		StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
	        		StdDrawPlus.square(x + 1 + .5, y - 1 + .5, .5);
	        	}

	        	if( b.validMoveForPiece(selected, x, y, x+2, y-2) ){
	        		StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
	        		StdDrawPlus.square(x + 2 + .5, y - 2 + .5, .5);
	        	}

	        	if( b.validMoveForPiece(selected, x, y, x-1, y-1) ){
	        		StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
	        		StdDrawPlus.square(x - 1 + .5, y - 1 + .5, .5);
	        	}

	        	if( b.validMoveForPiece(selected, x, y, x-2, y-2) ){
	        		StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
	        		StdDrawPlus.square(x - 2 + .5, y - 2 + .5, .5);
	        	}
        	}


        	StdDrawPlus.setPenRadius();
    }

    private static String getImage(Piece p) {
    	String url = "img/";

    	if( p.isBomb() ) {
    		url += "bomb";
    	} else if ( p.isShield() ) {
    		url += "shield";
    	} else {
    		url += "pawn";
    	}

    	if( p.isFire() ) {
    		url += "-fire";
    	} else {
    		url += "-water";
    	}

    	if( p.isKing() ) {
    		url += "-crowned";
    	}

    	url += ".png";

    	return url;
    }

    private static String getType(Piece p){
    	if(p.isBomb()){
    		return "bomb";
    	}
    	if(p.isShield()){
    		return "shield";
    	}
    	return "pawn";
    }

    private void setup_board() {

    	/*place(new Piece(true, this, 0, 0, "pawn"), 0, 0);

    	place(new Piece(false, this, 1, 1, "pawn"), 1, 1);
    	place(new Piece(true, this, 3, 3, "pawn"), 3, 3);
    	place(new Piece(false, this, 5, 5, "pawn"), 5, 5);
    	place(new Piece(false, this, 0, 7, "pawn"), 0, 7);
    	if (true) return;*/


    	boolean fire = true;
    	int x = 0;
    	int y = 0;

    	while(y < BOARD_SIZE){
    		if(y == 0 || y == BOARD_SIZE-1){
    			Piece p = new Piece(fire, this, x, y, "pawn");
        		place(p, x, y);
    		}

    		if(y == 1 || y == BOARD_SIZE-2){
    			Piece p = new Piece(fire, this, x, y, "shield");
        		place(p, x, y);
    		}

    		if(y == 2 || y == BOARD_SIZE-3){
    			Piece p = new Piece(fire, this, x, y, "bomb");
        		place(p, x, y);
    		}

    		if(y > 2) {
    			fire = false;
    		}

    		x += 2;

    		if( x >= BOARD_SIZE  ) {
    			y++;
    			x %= BOARD_SIZE;
    			x -= 1;
    		}
    		
    	}

    }

    private void destroy(int x, int y, boolean explode, boolean byBomb){
		if(!empty(x, y)){
       		if(pieceAt(x, y).isBomb() && explode){
       			System.out.println("boom");
       			destroy(x+1, y+1, false, true);
       			destroy(x+1, y-1, false, true);
       			destroy(x-1, y+1, false, true);
       			destroy(x-1, y-1, false, true);
       			boardData[x][y] = null;
       			return;
   			}

   			if(pieceAt(x, y).isShield()){
   				if(!byBomb){
   					boardData[x][y] = null;
   				}
   				return;
   			}

   			boardData[x][y] = null;
        }
	}

}