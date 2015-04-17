import java.awt.Point;

public class Board{
	public static void main (String args[]){
		Board b = new Board(false);

        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        while(b.winner() == null) {
            b.drawBoard();
            b.drawPieces();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                
                if(b.canSelect((int) x, (int) y)){
                	b.select((int) x, (int) y);
                }
            }
            if(StdDrawPlus.isSpacePressed()){
            	if(b.canEndTurn()){
            		b.endTurn();
            	}
            }
            StdDrawPlus.show(10);
        }
        b.drawBoard();
        b.drawPieces();
        StdDrawPlus.show(10);
	}

	private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if(selected != null && selected.x == i && selected.y == j)
            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0)
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }

    private void drawPieces(){
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(pieces[i][j] != null){
                	Piece p = pieces[i][j];
                	String img = "img/";

            		if(p.isBomb()){
            			img += "bomb";
                	}else if(p.isShield()){
                		img += "shield";
                	}else{
                		img += "pawn";
                	}

                	if(p.isFire()){
                		img += "-fire";
                	}else{
                		img += "-water";
                	}

                	if(p.isKing()){
                		img += "-crowned";
                	}
                	img += ".png";
	         		StdDrawPlus.picture(i + .5, j + .5, img, 1, 1);
                }
            }
        }
    }
    
	private Piece[][] pieces;
	private static final boolean FIRE = true, WATER = false;
	private boolean turn = FIRE;
	private boolean hasMoved;
	private Point selected;
	
	public Board(boolean shouldBeEmpty){
		/*The constructor for Board. If shouldBeEmpty is true, initializes an empty Board. Otherwise, initializes a Board with the default configuration. Note that an empty Board could possibly be useful for testing purposes.*/
		pieces = new Piece[8][8];
		if(shouldBeEmpty){
			return;
		}
		
		for(int i = 0; i < 8; i+=2){
			pieces[i][0] = new Piece(FIRE, this, i, 0, "pawn");
			pieces[i+1][1] = new Piece(FIRE, this, i+1, 1, "shield");
			pieces[i][2] = new Piece(FIRE, this, i, 2, "bomb");

			pieces[i+1][7] = new Piece(WATER, this, i+1, 7, "pawn");
			pieces[i][6] = new Piece(WATER, this, i, 6, "shield");
			pieces[i+1][5] = new Piece(WATER, this, i+1, 5, "bomb");
		}
	}

	public Piece pieceAt(int x, int y){
		/*Gets the piece at position (x, y) on the board, or null if there is no piece. If (x, y) are out of bounds, returns null.*/
		if(x >= 8 || x < 0 || y >= 8 || y < 0){
			return null;
		}else{
			return pieces[x][y];
		}
	}

	/**
    * Returns true if there is a piece the piece at (x, y) and it can be selected.
	A piece may be selected if it is the corresponding player’s turn
	and one of the following is true:
		The player has not selected a piece yet.
		The player has selected a piece, but did not move it.
	An empty square may be selected if one of the following is true:
		During this turn, the player has selected a Piece which hasn’t moved yet and is selecting an empty spot which is a valid move for the previously selected Piece.
		During this turn, the player has selected a Piece, captured, and has selected another valid capture destination. When performing multi-captures, you should only select the active piece once; all other selections should be valid destination points.
	*/
	public boolean canSelect(int x, int y){
		if(x >= 8 || x < 0 || y >= 8 || y < 0)
			return false;
		if(pieces[x][y] == null){
			return validMove(x, y);
		}else{
			// Selecting a piece: must select own piece, not opponent's
			return pieces[x][y].isFire() == turn && !hasMoved && (selected == null || !pieces[selected.x][selected.y].hasCaptured());
		}
	}
	
	/**
	 * Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf), 
	 * strictly from a geometry/piece-race point of view. validMove does not need to take into consideration 
	 * whose turn it is or if a move has already been made, but rather can. 
	 * @return
	 */
	private boolean validMove(int xf, int yf){
		// Selecting an empty square
		if(selected == null || !legalMoveDirection(xf, yf)){
			return false;
		}else if((canCaptureTo(xf, yf) && (!hasMoved || pieces[selected.x][selected.y].hasCaptured()))
				|| (canMoveTo(xf, yf) && !hasMoved))
			return true;
		else
			return false;
	}

	private boolean legalMoveDirection(int x, int y){
		// If not king, can only move towards opponent's side
		Piece p = pieces[selected.x][selected.y];
		if(!p.isKing()){
			// If not a king, fire can only move up
			if((p.isFire() && y < selected.y) || (!p.isFire() && y > selected.y)){
				return false;
			}
		}
		return true;
	}

	private boolean canCaptureTo(int x, int y){
		// Must move 2-diagonal
		if(Math.abs(selected.x - x) != 2 || Math.abs(selected.y - y) != 2)
			return false;

		Piece enemy = pieces[(selected.x + x)/2][(selected.y + y)/2];
		if(enemy == null || enemy.isFire() == turn || pieces[x][y] != null)
			return false;
		return true;
	}

	private boolean canMoveTo(int x, int y){
		// Must move 1-diagonal
		if(Math.abs(selected.x - x) != 1 || Math.abs(selected.y - y) != 1)
			return false;

		if(pieces[x][y] == null){
			return true;
		}

		return false;
	}

	public void select(int x, int y){
		/*Selects the piece at (x, y) if possible. 
		Optionally, it is recommended to color the background of the 
		selected square white on the GUI via the pen color function. 
		For any piece to perform a capture, that piece must have been 
		selected first.*/
		
		// Performing move or capture
		if(selected != null && pieces[x][y] == null){
			pieces[selected.x][selected.y].move(x, y);
			hasMoved = true;
			
			// Reset selected if bomb exploded
			if(pieces[x][y] == null){
				selected = null;
			}else{
				selected = new Point(x, y);
			}

		}
		// Selecting a piece
		else{
			selected = new Point(x, y);
		}
	}

	public void place(Piece p, int x, int y){
		/*Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. If p already exists in the current Board, first removes it from its initial position. If another piece already exists at (x, y), p will replace that piece. (This method is potentially useful for creating specific test circumstances.)*/
		if(p == null || x >= 8 || x < 0 || y >= 8 || y < 0){
			return;
		}else{
			pieces[x][y] = p;
		}
	}

	public Piece remove(int x, int y){
		/*Executes a remove. Returns the piece that was removed. If the input (x, y) is out of bounds, returns null and prints an appropriate message. If there is no piece at (x, y), returns null and prints an appropriate message.*/
		if(x >= 8 || x < 0 || y >= 8 || y < 0){
			System.out.println("Board.remove: Board location out of bounds");
			return null;
		}else if(pieces[x][y] == null){
			System.out.println("Board.remove: No piece found at " + x + ", " + y);
			return null;
		}else{
			Piece p = pieces[x][y];
			pieces[x][y] = null;
			return p;
		}
	}

	public boolean canEndTurn(){
		/*Returns whether or not the the current player can end their turn. To be able to end a turn, a piece must have moved or performed a capture.*/
		return hasMoved;
	}

	public void endTurn(){
		/*Called at the end of each turn. Handles switching of players 
		and anything else that should happen at the end of a turn.*/
		turn = !turn;
		hasMoved = false;
		if(selected != null && pieces[selected.x][selected.y] != null)
			pieces[selected.x][selected.y].doneCapturing();
		selected = null;
	}

	public String winner(){
		/*Returns the winner of the game. "Fire", "Water", "No one" 
		(tie / no pieces on the board), or null if the game is not yet over. 
		Assume there is no stalemate situation in which the current player 
		has pieces but cannot legally move any of them (In this event, just 
		leave it at null). Determine the winner solely by the number of pieces 
		belonging to each team.*/
		int fire = 0, water = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	if(pieces[i][j] != null){
            		if(pieces[i][j].isFire()){
            			fire++;
            		}else{
            			water++;
            		}
            	}
            }
        }
        
        if(fire > 0 && water > 0)
        	return null;
        else if(fire > 0)
        	return "Fire";
        else if(water > 0)
        	return "Water";
        else
        	return "No one";
	}

}
