/**
 * Board object for Checkers game
 * 
 * @author Yitz Deng (zintegy)
 */
public class Board
{
    private static final int SIZE = 8;
    private Piece[][] pieces = new Piece[SIZE][SIZE];
    private int turn;
    private Piece clicked;
    private int clickedX;
    private int clickedY;
    private int distanceMoved;
    private int countFire = 0;
    private int countWater = 0;

    // TODO:

    /**
     * Draws the board given a size. Also draws placed pieces on the board.
     * 
     * @param N
     *            Size of the board
     * @return Nothing.
     */
    private void drawBoard(int N)
    {

	for (int i = 0; i < N; i++)
	{
	    for (int j = 0; j < N; j++)
	    {

		if ((i + j) % 2 == 0)
		{
		    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		}
		else
		{
		    StdDrawPlus.setPenColor(StdDrawPlus.RED);
		}

		StdDrawPlus.filledSquare(i + .5, j + .5, .5);

		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

		Piece piece = this.pieceAt(i, j);
		if (piece != null)
		{

		    StdDrawPlus.picture(i + .5, j + .5, this.getImage(piece),
			    1, 1);

		}

	    }

	}

	StdDrawPlus.filledSquare(4, -4, 4);

	StdDrawPlus.setPenColor(StdDrawPlus.BLACK);

	if (this.turn == 0)
	{
	    StdDrawPlus.text(4, -.25, "Fire's turn");
	}
	else
	{
	    StdDrawPlus.text(4, -.25, "Water's turn");
	}
    }

    /**
     * Board constructor. Initiates Board. Assigns pieces to pieces array.
     * 
     * @param defaultStart
     *            Whether to create default board
     */
    public Board(boolean shouldBeEmpty)
    {
	if (!shouldBeEmpty)
	{
	    for (int i = 0; i < 4; i++)
	    {

		this.place(new Piece(true, this, 2 * i, 0, "pawn"), 2 * i, 0);
		this.place(new Piece(true, this, 2 * i + 1, 1, "shield"),
			2 * i + 1, 1);

		this.place(new Piece(true, this, 2 * i, 2, "bomb"), 2 * i, 2);

		this.place(new Piece(false, this, 2 * i + 1, 5, "bomb"),
			2 * i + 1, 5);
		this.place(new Piece(false, this, 2 * i, 6, "shield"), 2 * i, 6);

		this.place(new Piece(false, this, 2 * i + 1, 7, "pawn"),
			2 * i + 1, 7);

	    }
	}

	this.turn = 0;
	this.clicked = null;
	this.distanceMoved = 0;

    }

    public static void main(String[] args)
    {

	Board board = new Board(false);
	String win;
	StdDrawPlus.setXscale(0, SIZE);
	StdDrawPlus.setYscale(0, SIZE);
	board.drawBoard(SIZE);
	game: while (true)
	{

	    while (!(StdDrawPlus.isSpacePressed() && board.canEndTurn()))
	    // Loops until space is pressed and turn can end
	    {
		if (StdDrawPlus.mousePressed())
		{
		    int x = board.getXFromClick();
		    int y = board.getYFromClick();

		    if (board.canSelect(x, y))
		    {

			board.select(x, y);

			board.drawBoard(SIZE);
			board.drawBorder(x, y);
		    }
		    win = board.winner();
		    if (win != null)
		    {
			StdDrawPlus.text(4, 4, "Winner is " + win + "!");
			break game;
		    }

		}

	    }
	    board.endTurn();
	    board.drawBoard(SIZE);

	}
	return;
    }

    /**
     * Returns the piece at location x, y
     * 
     * @param x
     *            column number
     * @param y
     *            row number
     * @return The piece at location (x, y)
     */
    public Piece pieceAt(int x, int y)
    {
	if (x < 0 || x > 7 || y < 0 || y > 7)
	{
	    return null;
	}
	return this.pieces[x][y];
    }

    /**
     * Returns true or false depending on whether the piece can be moved by the
     * current player, or if the empty square is clickable.
     * 
     * @param x
     *            column number
     * @param y
     *            row number
     * @return Whether the square at (x, y) is selectable.
     */
    public boolean canSelect(int x, int y)
    {
	if (x > 7 || x < 0 || y > 7 || y < 0)
	{
	    return false; // out of bounds
	}
	Piece select = this.pieceAt(x, y);
	if (this.clicked != null)
	{
	    if (select == null)
	    {
		return this.validMove(this.clickedX, this.clickedY, x, y);
	    }
	    else if (this.distanceMoved == 0
		    && this.clicked.side() == select.side())
	    {
		return true;
	    }

	}
	else if (select != null)
	{

	    if (this.turn == select.side() && this.distanceMoved == 0)
	    {
		return true;
	    }
	}

	return false; // is neither a valid move nor is a valid select/clicked
	// combination
    }

    /**
     * Returns true if the piece can move from (xi, yi) to (xf, yf) True if: Not
     * false. See below.
     * 
     * False if: Out of bounds || Jump is neither 1 nor 2 spaces away || Ending
     * space is not empty || a 1-move step was already made || any move was made
     * if the current move is a 1-step
     * 
     * @param xi
     *            Initial x
     * @param yi
     *            Initial y
     * @param xf
     *            Final x
     * @param yf
     *            Final y
     * @return Whether the move is valid.
     */
    private boolean validMove(int xi, int yi, int xf, int yf)
    {
	Piece piece = this.pieces[xi][yi];
	if (this.pieces[xf][yf] == null && this.distanceMoved != 1)
	{
	    if (yf > yi && !piece.isFire() && !piece.isKing())
	    {
		return false;
	    }
	    if (yf < yi && piece.isFire() && !piece.isKing())
	    {
		return false;
	    }

	    if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1)
	    {
		if (this.distanceMoved != 0)
		{
		    return false;
		}
		return true;

	    }
	    else if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2)
	    {
		int xAverage = (xi + xf) >>> 1;
		int yAverage = (yi + yf) >>> 1;

		Piece jumped = this.pieces[xAverage][yAverage];
	    if (jumped == null)
	    {
		return false;
	    }
	    if (jumped.side() != piece.side())
	    {
		return true;
	    }
	    else
	    {
		return false;
	    }
	    }
	    else
		// If jump is neither 1 nor 2 spaces away
	    {
		return false;
	    }
	}
	else
	    // If jump ending space is not valid
	{
	    return false;
	}
    }

    /**
     * Selects a square, if possible.
     * 
     * @param x
     *            column number
     * @param y
     *            row number
     */
    public void select(int x, int y)
    {
	Piece select = this.pieceAt(x, y);
	if (this.clicked != null)

	{
	    if (select == null)
	    {

		this.clicked.move(x, y);
		this.distanceMoved += Math.abs(x - this.clickedX);
		this.pieces[x][y] = this.pieces[this.clickedX][this.clickedY];
		this.pieces[this.clickedX][this.clickedY] = null;
		this.clickedX = x;
		this.clickedY = y;

		if (this.clicked.isBomb() && this.clicked.hasCaptured())
		{
		    this.explode();
		}

		return;

	    }

	}

	this.clicked = select;
	this.clickedX = x;
	this.clickedY = y;

    }

    /**
     * Makes an explosion using the last piece. BOOM!
     * 
     */
    private void explode()
    {
	for (int i = -1; i < 2; i++)
	{
	    for (int j = -1; j < 2; j++)
	    {
		int x = this.clickedX + i;
		int y = this.clickedY + j;
		if (x < 0 || x > 7 || y < 0 || y > 7)
		{
		    return;
		}

		Piece temp = this.pieces[x][y];
		if (temp == null)
		{
		    continue;
		}
		if (temp.isShield())
		{
		    continue;
		}
		this.remove(x, y);
	    }
	}
	this.clicked = null;
	this.clickedX = 8;
	this.clickedY = 8;

    }

    /**
     * Places a piece at (x, y), if valid. If p is null or (x, y) is out of
     * bounds, does nothing. If p already exists, removes it from its original
     * position. If another piece is at (x, y), replaces that piece with p.
     * 
     * @param p
     *            The piece to be placed
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     * @return Nothing.
     */
    public void place(Piece p, int x, int y)
    {
	if (x > 7 || x < 0 || y > 7 || y < 0 || p == null)
	{
	    return;
	}
	if (this.pieces[x][y] != null)
	{
	    this.remove(x, y);
	}
	this.pieces[x][y] = p;

	if (p.isFire())
	{
	    this.countFire += 1;
	}
	else
	{
	    this.countWater += 1;
	}
    }

    /**
     * Removes a piece from the board, returns that piece.
     * 
     * @param x
     *            column number
     * @param y
     *            row number
     * @return The piece at location (x, y) being removed.
     */
    public Piece remove(int x, int y)
    {
	Piece temp = this.pieces[x][y];
	if (temp == null)
	{
	    return null;
	}
	if (temp.isFire())
	{
	    this.countFire -= 1;
	}
	else
	{
	    this.countWater -= 1;
	}
	this.pieces[x][y] = null;
	return temp;
    }

    /**
     * Returns whether or not the current player can end their turn. The player
     * must have moved or performed a capture.
     * 
     * @return Whether the turn can end now.
     */
    public boolean canEndTurn()
    {
	if (this.distanceMoved != 0)
	{
	    return true;
	}
	return false;
    }

    /**
     * Ends the turn, handles switching players and anything else at the end of
     * a turn.
     * 
     * @return Nothing.
     */
    public void endTurn()
    {
	this.distanceMoved = 0;
	if (this.clicked != null)
	{
	    this.clicked.doneCapturing();
	    this.clicked = null;
	}
	this.clickedX = 8;
	this.clickedY = 8;
	this.changeTurn();

    }

    /**
     * Returns the winner of the game: "Fire", "Water", or null if the game is
     * not over.
     * 
     * @return Winner!
     */
    public String winner()
    {
	if (this.countFire == 0)
	{
	    if (this.countWater == 0)
	    {
		return "No one";
	    }
	    return "Water";
	}
	else if (this.countWater == 0)
	{
	    return "Fire";
	}
	return null;

    }

    // TODO: Handle situation when current player has pieces but is stuck.

    /**
     * Finds the image for the given piece.
     * 
     * @param piece
     *            the piece of which we want to find an image
     * @return image location
     */
    private String getImage(Piece piece)
    {
	try
	{

	    String image = "img/";
	    if (piece.isBomb())
	    {
		image = image.concat("bomb");
	    }
	    else if (piece.isShield())
	    {
		image = image.concat("shield");
	    }
	    else
	    {
		image = image.concat("pawn");
	    }

	    if (piece.isFire())
	    {
		image = image.concat("-fire");
	    }
	    else
	    {
		image = image.concat("-water");
	    }
	    if (piece.isKing())
	    {
		image = image.concat("-crowned");
	    }
	    image = image.concat(".png");
	    return image;
	}
	catch (NullPointerException e)
	{
	    throw new NullPointerException("Passed in a null piece to getImage");
	}

    }

    /**
     * draws a border around the square specified.
     * 
     * @param x
     *            column number
     * @param y
     *            row number
     * 
     */
    private void drawBorder(int x, int y)
    {

	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

	StdDrawPlus.setPenRadius(0.01);
	StdDrawPlus.square(x + .5, y + .5, .4);

    }

    /**
     * Changes the turn.
     */
    private void changeTurn()
    {
	this.turn = 1 - this.turn;
    }

    /**
     * Finds the x-location of the click
     * 
     * @return x-coordinate of the click
     */
    private int getXFromClick()
    {
	return (int) StdDrawPlus.mouseX();
    }

    /**
     * Finds the y-location of the click
     * 
     * @return y-coordinate of the click
     */
    private int getYFromClick()
    {
	return (int) StdDrawPlus.mouseY();
    }

}
