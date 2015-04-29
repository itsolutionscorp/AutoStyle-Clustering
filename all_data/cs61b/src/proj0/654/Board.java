
// @Author Daniel Mockaitis


public class Board
{
	private boolean[][] selectedPieceSquare;
	private boolean selectedEmptySquare;
	private int emptySquareX = 0;
	private int emptySquareY = 0;
	private int formerSelectedX = 0;
	private int formerSelectedY = 0;
	private boolean[][] hasPiece;
	private Piece[][] pieces;
	private static final int size = 8;
	private Board board;
	private int player = 0; //0 == fire, 1 = water
	private boolean didMove = false;
	private Piece selectedPiece = null;
	private int numFirePieces = 0;
	private int numWaterPieces = 0;
	// The board for the game Bomb checkers.

	public Board(boolean shouldBeEmpty) {
	//The constructor for Board. If shouldBeEmpty is true, initializes an empty Board. 
	//Otherwise, initializes a Board with the default configuration. 
	//Note that an empty Board could possibly be useful for testing purposes.
		this.player = 0;
        this.didMove = false;
        this.board = this;
        this.hasPiece = new boolean[size][size];
       	this.pieces = new Piece[size][size];
        this.selectedPieceSquare = new boolean[size][size];
        if (!shouldBeEmpty)
		{
			for (int x = 0; x < size; x++) {
	            for (int y = 0; y < size; y++) {
					boolean isFire = true;
					String type = "pawn";
			        if ( ((x + y) % 2 == 0) && ((y > 4) || (y < 3 )) ) 
			        {
			        	if (y == 0)
			        	{
			        		isFire = true;
			        	}
			        	else if (y == 1)
			        	{
			        		isFire = true;
			        		type = "shield";
			        	}
			        	else if (y == 2)
			        	{
			        		isFire = true;
			        		type = "bomb";
			        	}
			        	else if (y == 5)
			        	{
			        		isFire = false;
			        		type = "bomb";
			        	}
			        	else if (y == 6)
			        	{
			        		isFire = false;
			        		type = "shield";
			        	}
			        	else if (y == 7)
			        	{
			        		isFire = false;
			        	}
						Piece p = new Piece(isFire, this.board, x, y, type );
						place(p,x,y);
					}
					else
					{
						this.hasPiece[x][y] = false;
						this.pieces[x][y] = null;

					}
					this.selectedPieceSquare[x][y] = false;
				}
			}
		}   	            
    }
	

	public Piece pieceAt(int x, int y) { //x = row, y = column
		/*- Gets the piece at position (x, y) on the board, or null if there is no piece. 
		-If (x, y) are out of bounds, returns null.done
		*/
		if ( (x < 0) || (x > 7) || (y > 7) || (y < 0)  ) {return null;}
		if (this.hasPiece[x][y] && this.pieces[x][y] != null)
		{
			return pieces[x][y];
			
		}	 
		else
		{
			return null;
		}

	}

	private void runCheckers()
    {

    	if (this.selectedEmptySquare && this.selectedPiece != null)
    	{
    		if (validMove(formerSelectedX,formerSelectedY,emptySquareX,emptySquareY))
    		{
    			this.selectedPiece.move(emptySquareX,emptySquareY);
    			this.didMove = true;
    			resetSelectedEmptySquare();
    			resetSelectedPieceSquare();
    			this.selectedPieceSquare[emptySquareX][emptySquareY] = true;
    		}
    	}
    }

	public boolean canSelect(int x, int y) {
		/*- Returns true if there is a piece the piece at (x, y) and it can be selected.
		//can select an empty space as well
		A square with a piece may be selected if it is the corresponding player’s turn and 
		one of the following is true:

		The player has not selected a piece yet.
		The player has selected a piece, but did not move it.
		An empty square may be selected if one of the following is true:

		During this turn, the player has selected a Piece which hasn’t moved yet and is selecting
			an empty spot which is a valid move for the previously selected Piece.
		During this turn, the player has selected a Piece, captured, 
			and has selected another valid capture destination. 

		When performing multi-captures, you should only select the active piece once;
		all other selections should be valid destination points.
		*/
		if ( (x < 0) || (x >= 8) || (y >= 8) || (y < 0) ) return false;
		if (this.didMove && !this.selectedPiece.hasCaptured()) return false;
		if ( (this.selectedPiece != null) && this.selectedPiece.hasCaptured() )
		{
			System.out.println("This Piece has Captured");
			if ( (Math.abs(x) - Math.abs(formerSelectedX) == 0) || (Math.abs(y) - Math.abs(formerSelectedY) == 0) ) return false;
			if (validMove(formerSelectedX,formerSelectedY, x, y)) {
				return true;
			}
			else
			{
				return false;
			}

		}
		if ((hasPiece[x][y] == false)) {
			if (this.selectedPiece != null) {
				if (validMove(formerSelectedX,formerSelectedY, x, y)) {
					System.out.println("Valid Move formerx, formery, x ,y "+ formerSelectedX+ " "+formerSelectedY+" "+x+" "+y);
					return true;
				}
			}
		}
		if (this.player == 1 && hasPiece[x][y] && (pieces[x][y].side() == 1)) return true;
		if (this.player == 0 && hasPiece[x][y] && (pieces[x][y].side() == 0)) return true;
		return false;
	}

	private boolean inBounds(int x, int y) {
		if ( (x < 0) || (x >= 8) || (y >= 8) || (y < 0) ) return false;
		return true;
	}
	private boolean validMove(int xi, int yi, int xf, int yf) {
		/*- Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf) 
	  	in a valid fashion compatible with the rules.*/
	  	if (xf > 8 || yf > 8 || xf < 0 || yf < 0) return false;
	  	boolean kingPiece = false;
	  	if (hasPiece[xf][yf]) return false;
		if (selectedPiece.isKing())	 kingPiece = true;

	  	if (this.selectedPiece.side() == 0 || kingPiece) 	
	  	{
	  		if ( (inBounds(xi - 1, yi + 1)) && (! this.selectedPiece.hasCaptured()) )
	  		{
  				if ((xi - 1 == xf) && (yi + 1 == yf)) return true;
  			}

	  		if ( (inBounds(xi + 1, yi + 1)) && (! this.selectedPiece.hasCaptured()) )
  			{
  				if ((xi + 1 == xf) && (yi + 1 == yf)) return true;
  			}
  			if (inBounds(xi + 2, yi + 2))
  			{
  				if (((hasPiece[xi + 1][yi + 1]) && (pieces[xi + 1][yi + 1].side() != this.selectedPiece.side())) && ((xi + 2 == xf) && (yi + 2 == yf))) return true; 
  				
  			}
  			if (inBounds(xi - 2, yi + 2))
			{
				if ( ((hasPiece[xi - 1][yi + 1]) && ((pieces[xi - 1][yi + 1].side() != this.selectedPiece.side()))) && ((xi - 2 == xf) && (yi + 2 == yf))) return true;
			}
	  	}

	  	if (this.selectedPiece.side() == 1 || kingPiece)
	  	{
	  		if ( (inBounds(xi - 1, yi - 1)) && (!this.selectedPiece.hasCaptured()) )
	  		{
  				if ((xi - 1 == xf) && (yi - 1 == yf)) return true;
  			}

  			if ( (inBounds(xi + 1, yi - 1)) && (!this.selectedPiece.hasCaptured()) )
  			{
  				if ((xi + 1 == xf) && (yi - 1 == yf)) return true;
  			}

  			if (inBounds(xi - 2, yi - 2))
  			{
  					if (((hasPiece[xi - 1][yi - 1]) && ((pieces[xi - 1][yi - 1].side() != this.selectedPiece.side()))) && ((xi - 2 == xf) && (yi - 2 == yf))) return true;
  			}

  			if (inBounds(xi + 2, yi - 2))
  			{
  				if (((hasPiece[xi + 1][yi - 1]) && (pieces[xi + 1][yi - 1].side() != this.selectedPiece.side())) && ((xi + 2 == xf) && (yi - 2 == yf))) return true; 
  			}
  			
	  	}

		return false;  
	}

	private void resetSelectedEmptySquare()
	{
		this.selectedEmptySquare = false;
	}
	private void resetSelectedPieceSquare()
	{
		this.selectedPieceSquare[formerSelectedX][formerSelectedY] = false;
	}
	public void select(int x, int y) {
		/*- Selects the piece at (x, y) if possible. Optionally, it is recommended to color the background
		 of the selected square white on the GUI via the pen color function. For any piece to perform a capture, 
		 that piece must have been selected first.*/ 

		if (pieceAt(x,y) != null) // if there is a piece there
		{
			resetSelectedEmptySquare();	// reset any former selected empty squares.
			this.selectedPiece = pieceAt(x,y); // set selected piece = to this.
			System.out.println("Selected Piece "+ selectedPiece);
			System.out.println("This Piece is a Capturer "+ selectedPiece.hasCaptured());
			resetSelectedPieceSquare(); // get rid of the former selected piece at that area
			this.selectedPieceSquare[x][y] = true; // set the selected piece to true for here
			formerSelectedX = x; // change the former variables to the new value
			formerSelectedY = y;
		}
		else if (this.selectedPiece != null) // already has a selected piece, and this piece is a valid move!
		{
			this.resetSelectedEmptySquare();
			this.selectedEmptySquare = true;
			this.emptySquareX = x;
			this.emptySquareY = y;
			runCheckers();
			this.didMove = true;
		}
		else
		{
			this.resetSelectedPieceSquare();
			this.resetSelectedEmptySquare();
		}
	}

	public void place(Piece p, int x, int y) {
		/* - Places p at (x, y). If (x, y) is out of bounds or if p is null, does nothing. 
		If another piece already exists at (x, y), p will replace that piece. 
		(This method is potentially useful for creating specific test circumstances.) */
		if (x < size && y < size && x >= 0 && y >= 0 && p != null)
		{
			if (this.pieces[x][y] != null) {
				// System.out.println("There is a piece at "+ x + " "+y+ " ");
				// System.out.print(pieces[x][y]);
				remove(x,y);
			}

			if (p.isFire()) {
				numFirePieces += 1;
			}
			else { numWaterPieces += 1; }
	        this.pieces[x][y] = p;
	        this.hasPiece[x][y] = true;
	        if (p.hasCaptured()) {
	        	this.formerSelectedX = x;
	        	this.formerSelectedY = y;
	        }
		}
	}

	public Piece remove(int x, int y) {
		/*- Executes a remove. Returns the piece that was removed. If the input (x, y) is out of bounds,
		returns null and prints an appropriate message. If there is no piece at (x, y), 
		returns null and prints an appropriate message. */
		if (x > 8 || y> 8 || x< 0 || y < 0) return null;
		
		if (hasPiece[x][y])
		{
			if (pieces[x][y].isFire()) {
				numFirePieces -= 1;
			}
			else if (!pieces[x][y].isFire()){
				numWaterPieces -= 1;
			}
			Piece copy = pieces[x][y];
			this.hasPiece[x][y] = false;
			this.pieces[x][y] = null;
			// System.out.println("removed " + x + " "+y);
			return copy;
		}
		else
		{
			return null;
		}

	}

	public boolean canEndTurn() {
	/*- Returns whether or not the the current player can end their turn. 
	To be able to end a turn, a piece must have moved or performed a capture. */
	if (this.didMove) return true;
	return false;
	}

	public void endTurn() {
		/*
		- Called at the end of each turn. 
		Handles switching of players and anything else that should happen at the end of a turn. */
		this.player = 1 - player;
		this.didMove = false;
		if (this.selectedPiece != null) this.selectedPiece.doneCapturing();
		selectedPiece = null;
		for (int column = 0; column < size; column++) {
	        for (int row = 0; row < size; row++) {
	        	this.selectedPieceSquare[row][column] = false;
	        	if (this.pieces[row][column] != null)
	        		this.pieces[row][column].doneCapturing();
	        	}}
		resetSelectedPieceSquare();
		resetSelectedEmptySquare();
		System.out.println("############ NEW TURN ############");
	}
		
	public String winner() {
		/*- Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board),
		 or null if the game is not yet over. 
		 Assume there is no stalemate situation in which the current player has pieces but cannot legally move any of them 
		(In this event, just leave it at null). Determine the winner solely by the number of pieces belonging to each team.
		*/
		if (numFirePieces == 0 && numWaterPieces > 0) return "Water";
		if (numWaterPieces == 0 && numFirePieces > 0) return "Fire";
		if (numFirePieces == 0 && numWaterPieces == 0) return "No one";
		return null;
	}

	private void makeBoard() 
	{
        for (int x = 0; x < size; x ++) {
            for (int y = 0; y < size; y++) {
            	if (selectedPieceSquare[x][y]) {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                else if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
                if (hasPiece[x][y])
                {
                	Piece toPlace = pieces[x][y];
                	String team = "water";
                	String king = "";
                	String image = "img/";
                	String typep = "pawn-";
                	if (toPlace.isFire())
            		{
            			team = "fire";
            		}
           			if (toPlace.isKing())
           				king = "-crowned";
           			if (toPlace.isBomb())
           				typep = "bomb-";
           			if(toPlace.isShield())
           				typep = "shield-";
           			image = image + typep + team + king + ".png";
                	StdDrawPlus.picture(x + .5, y + .5, image, 1, 1);

                }

            }
        }

	}

	public static void main(String[] args) {
		StdDrawPlus.setXscale(0, size);
        StdDrawPlus.setYscale(0, size);
    	Board board = new Board(false);
    	while(true) {
            board.makeBoard();
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if ( board.canSelect((int) x,(int) y)){
                	board.select((int) x,(int) y);
                }
            }
            else if (StdDrawPlus.isKeyPressed(32) && board.canEndTurn())
            {
            	board.endTurn();
            }
            StdDrawPlus.show(100);
            String a = board.winner();
   			if (a != null) {
   				board.makeBoard();
 				System.out.println(a);
 				board.makeBoard();
   				break;
   			}
        }
        board.makeBoard();
        StdDrawPlus.show(100);
    }

}	







