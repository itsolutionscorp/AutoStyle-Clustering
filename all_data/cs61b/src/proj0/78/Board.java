/*
Author: Chaz Chamberlin
login: cs61b-ada
CS61B UC Berkeley Spring 2015
Professor Hugg

 */

/*
To Do: * Change previouslySelected back to private
       * Make sure all methods are private that should be
       * validMove --> private

       * Move validDistance over to Piece???
       * What other things need to be moved to Piece? h
         hasCaptured and doneCapturing.
       * All pieces can keep moving after capture (should only if next is capture)
       
 */

public class Board {
    private Piece[][] pieces;
    private boolean shouldBeEmpty;
    private int N = 8;
    private Piece previouslySelected;
    private int selectedX = -1;
    private int selectedY = -1;
    private int turn = 0; // 0 = fire, 1 = water  % 2
    private boolean playerHasMoved;
    private boolean playerHasCaptured;

    public Board(boolean shouldBeEmpty) {
	if (shouldBeEmpty)
	    initializeEmptyBoard();
	else
	    initializeBoard();
    }

    /* Initializes board with appropriate pieces */
    private void initializeBoard() {
	// [0][0] is top left
	// [0][1] [0][3] [0][5] [0][7] should be water pawn
	// [1][0] [1][2] [1][4] [1][6] should be water water shield
	pieces = new Piece[N][N];
	
	for (int i = 0; i < N; i += 1) {
	    for (int j = 0; j < N; j += 1) {
		if (j == 0 && i % 2 == 0) {
		    pieces[i][j] = new Piece(true, this, i, j, "pawn");
		} else if (j == 1 && i % 2 != 0) {
		    pieces[i][j] = new Piece(true, this, i, j, "shield");
		} else if (j == 2 && i % 2 == 0) {
		    pieces[i][j] = new Piece(true, this, i, j, "bomb");
		} else if (j == 5 && i % 2 != 0) {
		    pieces[i][j] = new Piece(false, this, i, j, "bomb");
		} else if (j == 6 && i % 2 == 0) {
		    pieces[i][j] = new Piece(false, this, i, j, "shield");
		} else if (j == 7 && i % 2 != 0) {
		    pieces[i][j] = new Piece(false, this, i, j, "pawn");
		}
	    }
	}
	
    }

    private void initializeEmptyBoard() {
	pieces = new Piece[N][N];
    }
    
    private void drawEmptyBoard(int N) {
	for (int i = 0; i < N; i += 1) {
	    for (int j = 0; j < N; j += 1) {
		if ((i + j) % 2 == 0)
		    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		else
		    StdDrawPlus.setPenColor(StdDrawPlus.RED);

		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	    }
	}
    }

    private void drawBoard(int N) {
	for (int i = 0; i < N; i += 1) {
	    for (int j = 0; j < N; j += 1) {
		if (i == selectedX && j == selectedY)
		    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);		    
		else if ((i + j) % 2 == 0)
		    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		else
		    StdDrawPlus.setPenColor(StdDrawPlus.RED);
		StdDrawPlus.filledSquare(i + .5, j + .5, .5);

		if (pieces[i][j] != null)
		    StdDrawPlus.picture(i + .5, j + .5, getImage(pieces[i][j]), 1, 1);

	    }
	}
    }

    /*
     * Selects the square at (x, y). Assumes canSelect is true.
     * If the square contains a piece, the piece is put into previouslySeleced
     * If an empty square is selected (assuming canSelect is true), moves the
     * piece to the location
     */
    public void select(int x, int y) {
	int oldX;
	int oldY;
	if (pieces[x][y] != null) {
	    previouslySelected = pieces[x][y];
	    selectedX = x;
	    selectedY = y;	
	} else {
	    oldX = selectedX;
	    oldY = selectedY;
	    previouslySelected.move(x, y);
	    playerHasMoved = true;
	    if (Math.abs(x - selectedX) == 2 && Math.abs(y - selectedY) == 2)
		playerHasCaptured = true;
	    selectedX = x;
	    selectedY = y;
	}
    }

    /*
     * Square either has piece or not.
     * Piece: Valid if the player has not selected a piece,
     *        or has selected a piece but hasn't moved
     * Empty: Has selected a piece that hasn't moved yet, and
     *        is selecting a square that is a valid move for the
     *        previously selected piece.
     *      Or
     *        Player has captured a piece already and has selected
     *	      another valid capture (double jump).
     */
    public boolean canSelect(int x, int y) {
	if (pieces[x][y] != null) {
	    if (pieces[x][y].side() != turn)
		return false;
	    else if (previouslySelected == null || !playerHasMoved)
		return true;
	    else
		return false;
	}
	if (previouslySelected == null)
	    return false;

	boolean playerValid = (!playerHasMoved || playerHasCaptured);
	if (playerValid && validMove(selectedX, selectedY, x, y))
	    return true;
	else
	    return false;
    }


    /*
     * Returns true if the piece at the old location can
     * move to the new location legally
     */
    private boolean validMove(int oldX, int oldY, int newX, int newY) {
	Piece selectedPiece = pieces[oldX][oldY];
	if (selectedPiece == null || pieces[newX][newY] != null)
	    return false;
	else if (turn == 0 && !(newY > oldY) && !selectedPiece.isKing()) // fire can only move up
	    return false;
	else if (turn == 1 && !(newY < oldY) && !selectedPiece.isKing()) // water can only move down
	    return false;
	else if (!validDistance(oldX, oldY, newX, newY))
	    return false;
	else if ((newX + newY) % 2 != 0) // grey squares are where x+y is even
	    return false;
	else
	    return true;		
    }

    /*
      Returns true if move is 1 diagonal away or 2 diagonals
      away and an enemy is in between
    */
    private boolean validDistance(int oldX, int oldY, int newX, int newY) {
	if (Math.abs(oldX - newX) == 1 && Math.abs(oldY - newY) == 1 && !playerHasCaptured)
	    return true;
	else if (Math.abs(oldX - newX) == 2 && Math.abs(oldY - newY) == 2){
	    int midX = (newX - oldX) / 2 + oldX;
	    int midY = (newY - oldY) / 2 + oldY;
	    if (pieces[midX][midY] != null && pieces[midX][midY].side() != turn) {
		return true;
	    } else
		return false;	       
	}
	else
	    return false;
    }
	
    /*
     * Returns the piece at the given point.
     * (0,0 is bottom left and 7,7 is top right)
     */
    public Piece pieceAt(int x, int y) {
	if (x < 0 || x > N || y < 0 || y > N)
	    return null;
	return pieces[x][y];
    }

    /*
     * Places a piece at the given condition
     */
    public void place(Piece piece, int x, int y) {
	if (x > N || x < 0 || y > N || y < 0)
	    return;
	pieces[x][y] = piece;
    }

    /*
     * Removes a piece and returns it. If coordinates are out of bounds prints message
     * If there is no piece there, returns null and prints message.
     */
    public Piece remove(int x, int y) {
	Piece returnPiece;	
	if ((x < 0 || x > N || y < 0 || y > N) || pieces[x][y] == null) {
	    String errorMessage = "";
	    if (pieces[x][y] == null)
		errorMessage = " Null value";
	    else
		errorMessage = " Out of bounds";
	    System.out.println("Bad remove call for coordinates: "
			       + x + ", " + y + errorMessage);
	    return null;
	}

	returnPiece = pieces[x][y];
	pieces[x][y] = null;
	return returnPiece;
    }

    /*
     * Returns whether the current player can end their turn (by having moved
     */
    public boolean canEndTurn() {
	return this.playerHasMoved | this.playerHasCaptured;
    }

    /*
     * End the current turn and switches players. Resets playerHasMoved
     * Maybe more stuff to do in here.
     * INCOMPLETE
     */
    public void endTurn() {
	turn = (turn + 1) % 2;
	playerHasMoved = false;
	playerHasCaptured = false;
	selectedX = -1;
	selectedY = -1;
	previouslySelected.doneCapturing();
	previouslySelected = null;
    }
	

    /*
     * Returns the winner of the game or null
     */
    public String winner() {
	boolean isFire = false;
	boolean isWater = false;
	for (int i = 0; i < N; i += 1) {
	    for (int j = 0; j < N; j += 1) {
		if (pieces[i][j] != null && pieces[i][j].isFire())
		    isFire = true;
		else if (pieces[i][j] != null && !pieces[i][j].isFire())
		    isWater = true;
	    }
	}
	if (isFire && isWater)
	    return null;
	else if (isFire)
	    return "Fire";
	else if (isWater)
	    return "Water";
	else
	    return "No one";	   		
    }
    
    /*
     * Main running method for GUI
     */
    private void run() {	
	StdDrawPlus.setXscale(0, N);
	StdDrawPlus.setYscale(0, N);
	int x;
	int y;
	
	/*
	 * Keeps playing until the game ends. Checks
	 * for mouse presses
	 */
	while(winner() == null) {
	    if (shouldBeEmpty)
		drawEmptyBoard(N);
	    else
		drawBoard(N);
	    if (StdDrawPlus.mousePressed()) {
		x = (int) StdDrawPlus.mouseX();
		y = (int) StdDrawPlus.mouseY();
		if (canSelect(x, y)) {
		    select(x, y);
		}
	    }
	    if (StdDrawPlus.isSpacePressed() && canEndTurn()) {
		endTurn();
	    }
	    StdDrawPlus.show(15);
	}
	drawBoard(N);
	System.out.println(winner() + " wins!!");
    }

    private String getImage(Piece piece) {
	String image = "img/";
	if (piece.isBomb())
	    image += "bomb";
	else if (piece.isShield())
	    image += "shield";
	else
	    image += "pawn";

	if (piece.isFire())
	    image += "-fire";
	else
	    image += "-water";

	if (piece.isKing())
	    image += "-crowned";

	image += ".png";

	return image;
    }
    
    public static void main(String[] args) {
	Board board = new Board(false);
	board.run();
    }
    
}
