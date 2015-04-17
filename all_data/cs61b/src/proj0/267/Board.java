/* Class that functions as a Checkers game */
public class Board{
	private Piece[][] piecelist; //An array  of Piece objects that tracks each Piece object within a 2-D array that matches the checkers board. 
	private boolean[][] highlightgrid; //An array of boolean variables that tracks where to highlight the squares as to match the selected piece
	private boolean isFiresTurn; //A boolean that is true when it is Fire's turn and false otherwise
	private Piece selected; //The piece that has been selected to move by the user
	private int selecxpos, selecypos; //The selected piece's x-position and y-position
	private boolean hasmoved, hasselected, hascaptured; //Boolean values that are true when something has moved, has been selected, or has captured a piece, respectively
	/* In the main method, the Board class runs the game by initializing the board, and then constantly checking for mouse clicks, space clicks, or presses of the
		"N" key, performing different functions when each is pressed, and constantly refreshing the appearance of the board to match the information found within the 
		piecelist array */
	public static void main(String[] args){
		Board b = new Board(false);
		while(true){
			/* When the mouse is clicked, the x and y coordinates of the click are noted, and then select is run on that x and y coordinate, either selecting a piece
				or moving a previously selected piece to that location, possibly capturing an enemy piece in the process.*/
			if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(b.canSelect((int)x, (int)y)){
                	b.select((int)x, (int)y);
                }
            }
            /* When the space bar is clicked, the program checks if the turn can be ended, and if so, passes the turn over to the opposing player.*/
            if (StdDrawPlus.isSpacePressed()) {
            	b.endTurn();
            }
            /* When the "N" key is pressed, the board resets to a new game.*/
            if (StdDrawPlus.isNPressed()){
            	b = new Board(false);
            }
            /* The drawBoard method constantly takes the information in from both the highlightgrid array and the piecelist array and draws the visual appearance of the
            	board based on these sets of information.*/
            b.drawBoard();
            StdDrawPlus.show(10);
		}
	}
	/* In the constructor, the Board program initializes the variables in the beginning, setting up a new piecelist array, a new highlightgrid array, setting variables to 
		their initial values, and draws the InitialBoard */
	public Board(boolean shouldBeEmpty){
		int N = 8;
		piecelist = new Piece[N][N];
		isFiresTurn = true;
		highlightgrid = new boolean[8][8];
		hasmoved = false;
		hasselected = false;
		hascaptured = false;
		drawInitialBoard(shouldBeEmpty, N);
	}

	/* Draws an N by N board and sets it up with the proper pieces if shouldBeEmpty is null, or just draws an N by N empty board*/
	private void drawInitialBoard(boolean shouldBeEmpty, int N){
		if(!shouldBeEmpty){
			for(int i = 0; i < N; i++){
				for(int j = 0; j < N; j++){
					if((j == 0) && (i%2 == 0)){
            			piecelist[i][j] = new Piece(true, this, i, j, "pawn");
            		}
            		if((j == 1) && (i%2 == 1)){
            			piecelist[i][j] = new Piece(true, this, i, j, "shield");
            		}
            		if((j == 2) && (i%2 == 0)){
            			piecelist[i][j] = new Piece(true, this, i, j, "bomb");
            		}
            		if((j == 7) && (i%2 == 1)){
            			piecelist[i][j] = new Piece(false, this, i, j, "pawn");
            		}
            		if((j == 6) && (i%2 == 0)){
            			piecelist[i][j] = new Piece(false, this, i, j, "shield");
            		}
            		if((j == 5) && (i%2 == 1)){
            			piecelist[i][j] = new Piece(false, this, i, j, "bomb");
            		}
				}
			}
		}
	}

	/* Draws the board at any given point, taking in the information from the piecelist and highlightgrid arrays.*/
	private void drawBoard(){
		int N = 8;
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                Piece toDraw = pieceAt(i, j);
                if(toDraw != null){
                	StdDrawPlus.picture(i + .5, j + .5, getname(toDraw), 1, 1);
            	}
            	if(highlightgrid[i][j] == true){
            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        			StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        			Piece p = pieceAt(i, j);
        			StdDrawPlus.picture(i + .5, j + .5, getname(p), 1, 1);
            	}
			}
		}
	}

	/* Highlights a certain square in the grid by altering the highlightgrid value corresponding to that square to true.*/
	private void highlight(int x, int y){
		highlightgrid[x][y] = true;
	}

	/* Returns the piece at a given x-position and y-position, returning null if the piece is outside the given range.*/
	public Piece pieceAt(int x, int y){
		if(withinrange(x, y)){
			return piecelist[x][y];
		}
		else{
			return null;
		}
	}

	/* Determines if a certain space can be selected based on the following criteria:
		1. If a piece is clicked, it can be selected if it's the corresponding player's turn and no piece has been selected yet
		2. If a piece is clicked, it can be selected if it's the corresponding player's turn and a piece has been selected but not moved yet
		3. If a space is clicked, it can be selected if a piece has already been selected and has not moved, AND the clicked space is a valid movement spot for the piece
		4. If a space is clicked, it can be selected if a piece has already been selected and has captured a piece, and is in position to capture another piece
			a. The implementation in (4) allows for multiple captures in a row (multi-capture) */
	public boolean canSelect(int x, int y){
		if(pieceAt(x, y) == null){
			if(hasselected){
				if(!hasmoved && validMove(selecxpos, selecypos, x, y)) return true;
				else if(selected.hasCaptured() && willKill(selecxpos, selecypos, x, y) && validMove(selecxpos, selecypos, x, y)) return true;
				else { return false; }
			}
			else { return false; }
		}
		else if(isFiresTurn){
			if(pieceAt(x, y).isFire()){
				if(!hasselected) return true;
				else if(!hasmoved) return true;
				else return false;
			}
			else{
				return false;
			}
		}
		else{
			if(pieceAt(x, y).isFire()) return false;
			else if(!hasselected) return true;
			else if(!hasmoved) return true;
			else return false;
		}
	}

	/* The willKill method determines if a piece at position (xstart, ystart) will capture a piece of the opposing team if it moves to (xdes, ydes). */
	private boolean willKill(int xstart, int ystart, int xdes, int ydes){
		int xkill = (xstart + xdes)/2;
		int ykill = (ystart + ydes)/2;
		int dx = xdes - xstart;
		int dy = ydes - ystart;
		if(pieceAt(xkill, ykill) != null && (dx * dx + dy * dy == 8)){
			if(pieceAt(xstart, ystart).isFire() ^ pieceAt(xkill, ykill).isFire()){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}

	/* The validMove method determines if a piece at (xi, yi) moving to (xf, yf) is a valid move within the rules of the game, checking for direction for non-kinged pieces,
		for possible capture moves, and for only-diagonal moves. */
	private boolean validMove(int xi, int yi, int xf, int yf){
		if(pieceAt(xf, yf) == null){
			if(pieceAt(xi, yi).isFire() && !pieceAt(xi, yi).isKing()){
				if(yf < yi){
					return false;
				}
			}
			else if(!pieceAt(xi, yi).isFire() && !pieceAt(xi, yi).isKing()){
				if(yf > yi){
					return false;
				}
			}
			double distancesq = (xf - xi) * (xf - xi) + (yf - yi) * (yf - yi);
			if(willKill(xi, yi, xf, yf)){
				return true;
			}
			else if(distancesq == 2){
				return true;
			}
			else return false;
		}
		else{
			return false;
		}
	}

	/* The select method selects the piece or empty space at (x, y), and if it's an empty space, moves a previously selected piece to that location, updating relevant
		information and continuing the highlight for non-bomb capturing pieces, in order to make the multi-capture function more apparent to the user. */
	public void select(int x, int y){
		undohighlight();
		if(pieceAt(x, y) != null){
			selected = piecelist[x][y];
			selecxpos = x;
			selecypos = y;
			hasselected = true;
			highlight(x, y);
		}	
		else{
			if(withinrange(x, y)){
				if(selected == null){}
				else{
					selected.move(x, y);
					selecxpos = x;
					selecypos = y;
					hasmoved = true;
					if(selected.hasCaptured() == true){
						hascaptured = true;
						if(!selected.isBomb()){
						highlight(x, y);
						}
					}
				}
			}
		}
	}

	/* The undohighlight method resets the entire boolean highlightgrid array to false. */
	private void undohighlight(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				highlightgrid[i][j] = false;
			}
		}
	}

	/* The withinrange method checks if a given x and y are within the range of the grid, and returns true if they are, and false otherwise. */
	private boolean withinrange(int x, int y){
		if(((x >= 0) && (x <=7)) && ((y >= 0) && (y <= 7))){
			return true;
		}
		else{
			return false;
		}
	}

	/* Places a Piece object p at the position (x, y) within the piecelist array. */
	public void place(Piece p, int x, int y){
		if(p != null && ((x >= 0) && (x <=7)) && ((y >= 0) && (y <= 7))){
			piecelist[x][y] = p;
		}
	}

	/* Removes the piece at the position (x, y) from within the piecelist array. If there is no piece at the position (x, y), it returns null and prints an error message.
		If the (x, y) position is outside the range of the grid, it returns null and prints an appropriate error message. */
	public Piece remove(int x, int y){
		if(((x >= 0) && (x <=7)) && ((y >= 0) && (y <= 7))){
			if(pieceAt(x, y) != null){
				Piece removed = piecelist[x][y];
				piecelist[x][y] = null;
				return removed;
			}
			System.out.println("Null Space: Cannot Remove");
			return null;
		}
		else{
			System.out.println("Out of Bounds: Cannot Remove");
			return null;
		}
	}

	/* Checks if the turn can be ended by checking if a piece has either moved or performed a capture. */
	public boolean canEndTurn(){
		if(hasmoved || hascaptured){
			return true;
		}
		else return false;
	}

	/* Ends the turn, undoing the highlights on the pieces, setting hasselected, hasmoved, and hascaptured to false, and switching the turn to the opposing player. */
	public void endTurn(){
		if(canEndTurn()){
			undohighlight();
			hasselected = false;
			hasmoved = false;
			hascaptured = false;
			if(isFiresTurn){
				isFiresTurn = false;
			}
			else{
				isFiresTurn = true;
			}
			selected.doneCapturing();
		}
	}

	/* Prints the winner of the match by calculating the number of pieces left to each player. A player wins if his or her opponent has no pieces left. If both players
		 have no pieces left in play, the result is no one. If both players have pieces left in play, a null value is returned. An appropriate message saying who the 
		 winner is is then printed. */
	public String winner(){
		int firecount = 0;
		int watercount = 0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(pieceAt(i, j) != null){
					if(pieceAt(i, j).isFire()){
						firecount += 1;
					}
					else{
						watercount += 1;
					}
				}
			}
		}
		if((firecount + watercount) == 0){
			return "No one";
		}
		else if(firecount == (int)0){
			return "Water";
		}
		else if(watercount == (int)0){
			return "Fire";
		}
		else{
			return null;
		}
	}

	/* Given a piece p, returns the appropriate filename where the image file for the piece is stored. */
	private String getname(Piece p){
		if(p.isFire()){
			if(p.isBomb()){
				if(p.isKing()){
					return "img/bomb-fire-crowned.png";
				}
				else{
					return "img/bomb-fire.png";
				}
			}
			else if(p.isShield()){
				if(p.isKing()){
					return "img/shield-fire-crowned.png";
				}
				else{
					return "img/shield-fire.png";
				}
			}
			else{
				if(p.isKing()){
					return "img/pawn-fire-crowned.png";
				}
				else{
					return "img/pawn-fire.png";
				}
			}
		}
		else{
			if(p.isBomb()){
				if(p.isKing()){
					return "img/bomb-water-crowned.png";
				}
				else{
					return "img/bomb-water.png";
				}
			}
			else if(p.isShield()){
				if(p.isKing()){
					return "img/shield-water-crowned.png";
				}
				else{
					return "img/shield-water.png";
				}
			}
			else{
				if(p.isKing()){
					return "img/pawn-water-crowned.png";
				}
				else{
					return "img/pawn-water.png";
				}
			}
		}
	}	
}