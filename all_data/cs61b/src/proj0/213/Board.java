public class Board
{
	// Instance variables
	private Piece[][] pieces;
	private static final int N = 8;
	private static final String FIRE = "fire";
	private static final String WATER = "water";
	private int currentSide;
	private Piece selectedPiece;
	private boolean playerHasMoved;
	private int selectedX;
	private int selectedY;

	// ================================== MAIN ================================== //
	// Main Method
	public static void main(String[] args)
	{
        // Create a new board
        Board gameBoard = new Board(false);

        // Sets the scale
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        
        // ==== Plays the game ==== //
        // Updates the view, checks for inputs from the user
	    while(gameBoard.winner() == null) 
	    {
	    	// Draw the board
	        gameBoard.drawBoard();

	        // If mouse pressed
	        if (StdDrawPlus.mousePressed()) 
	        {
	            int x = (int)StdDrawPlus.mouseX();
	            int y = (int)StdDrawPlus.mouseY();

	            // Check if can select
	            if (gameBoard.canSelect(x, y))
	            {

	            	// Select the piece
	            	gameBoard.select(x, y);
	            }
	        }    
	        // Else if space pressed and can end turn, the end gam
	        else if (StdDrawPlus.isSpacePressed() && gameBoard.canEndTurn())
	        {
	        	gameBoard.endTurn();
	        }

			// Pause the next draw
	        StdDrawPlus.show(50);
	    }
	}

	// ================================== PUBLIC CLASS METHODS ================================== //
	// Constructor
	public Board(boolean shouldBeEmpty)
	{
		currentSide = 0;
		playerHasMoved = false;

		// For debug purposes - create empty board
		if (shouldBeEmpty)
		{
			pieces = new Piece[N][N];
		}
		// else start with correct configurations
		else
		{	
			pieces = generateDefaultLayout();
		}
	}

	// pieceAt
	public Piece pieceAt(int x, int y)
	{
		// Check if out of bounds
		if (x < 0 || x >= N || y < 0 || y >= N)
		{
			return null;
		}

		// Else return what is located at that location
		return pieces[x][y];
	}

	// place
	public void place(Piece p, int x,int y)
	{
		// Check if out of bounds
		if (x < 0 || x >= N || y < 0 || y >= N)
		{
			return;
		}

		// Else set that location's value to be p
		pieces[x][y] = p;
	}

	// end turn
	public void endTurn()
	{
		// Check if can actually end turn
		if (!canEndTurn())
		{
			return;
		}

		// Clear selected piece
		if (selectedPiece != null)
		{
			selectedPiece.doneCapturing();
		}
		selectedPiece = null;
		playerHasMoved = false;
		selectedX = -1;
		selectedY = -1;

		// Switch sides
		currentSide ^= 1;
	}

	// can end turn
	public boolean canEndTurn()
	{
		return playerHasMoved;
	}

	// can select
	public boolean canSelect(int x, int y)
	{
		// Check if piece exists on (x,y)
		Piece checkPiece = pieceAt(x,y);

		// If out of bounds, then false
		if (x < 0 || x >= N || y < 0 || y >= N)
		{
			return false;
		}

		// If trying to select a piece
		if (checkPiece != null && checkPiece.side() == currentSide)
		{
			return !playerHasMoved;
		}
		// Else you have selected a piece and are trying to move it to a new empty location assuming player has not moved yet
		else if (selectedPiece != null && checkPiece == null && !playerHasMoved)
		{
			// Check that coordiantes are valid points of movement (taking into account king status)
			return isValidMove(x, y, selectedPiece.isKing());
		}
		// Else we handle the case where a piece has already moved and has captured something, allowing them to perform another capture
		else if (selectedPiece != null && checkPiece == null && (playerHasMoved && selectedPiece.hasCaptured()))
		{
			// Check that it is capturing something - 2 units away
			int xDiff = x - selectedX;
			int yDiff = y - selectedY;
			return (Math.abs(xDiff) == 2 && Math.abs(yDiff) == 2 && isValidMove(x,y, selectedPiece.isKing()));
		}

		// Default to false
		return false;
	}

	// select
	public void select(int x, int y)
	{
		// Check if piece exists on (x,y)
		Piece checkPiece = pieceAt(x,y);

		// Update selected coordinates
		selectedX = x;
		selectedY = y;

		// If trying to select a piece of own team
		if (checkPiece != null)
		{
			// Set selectedPiece
			selectedPiece = checkPiece;
		}

		// Else you have selected a piece and are trying to move it to an empty location 
		else if (selectedPiece != null)
		{
			// Moves the piece
			selectedPiece.move(x, y);
			playerHasMoved = true;

        	// If bomb, and captured something then it should be gone
        	if (selectedPiece.isBomb() && selectedPiece.hasCaptured())
        	{
        		selectedPiece = null;
        	}
		}
	}

	// remove
	public Piece remove(int x, int y)
	{
		// Check if out of bounds
		if (x < 0 || x >= N || y < 0 || y >= N)
		{
			System.out.println("Please enter valid coordinates that are within bounds of the board");
			return null;
		}

		// Else get piece at x
		Piece checkPiece = pieceAt(x,y);

		// If no piece, then print message and return null
		if (checkPiece == null)
		{
			System.out.println("No piece found at given coordinates");
			return null;
		}
		// Else there is a piece, remove it from the board and return it
		else
		{
			pieces[x][y] = null;
			return checkPiece;
		}
	}

	// winner
	public String winner()
	{
		int fireCount = 0;
		int waterCount = 0;

		// Search entire board to see if there is a winner
		for (int x = 0; x < N; ++x)
		{
			for (int y = 0; y < N; ++y)
			{
				Piece checkPiece = pieceAt(x, y);
				// Increment team counts depending on what side the piece is on
				if (checkPiece != null)
				{
					if (checkPiece.side() == 0)
					{
						++fireCount;
					}
					else
					{
						++waterCount;
					}
				}
			}
		}

		if (fireCount > 0 && waterCount > 0)
		{
			return null;
		}
		else if (fireCount == 0 && waterCount == 0)
		{
			return "No one";
		}
		else
		{
			return fireCount > waterCount ? "Fire" : "Water";
		}
	}

	// ================================== PRIVATE METHODS ================================== //
	// isValidMove
	private boolean isValidMove(int x, int y, boolean isKing)
	{
		// Get difference between selected coordinates and entered coordinates
		int xDiff = x - selectedX;
		int yDiff = y - selectedY;

		// If fire(can only going up)
		if (currentSide == 0)
		{
			// If only moving the piece(not capturing) - meaning a xDiff and yDiff of 1
			if ((xDiff == 1 || xDiff == -1) && ((yDiff == 1) || (isKing && yDiff == -1)))
			{
				return true;
			}
			// Else check if trying to capture a piece - meaning a xDiff and yDiff of 2
			else if ((xDiff == 2 || xDiff == -2) && ((yDiff == 2) || (isKing && yDiff == -2)))
			{
				// Check that there is a piece that can be captured
				// We divide xDiff by 2 so that it reduces to 1 in while keeping the same direction, same for yDiff
				int checkX =  selectedX + xDiff/2;
				int checkY = selectedY + yDiff/2;

				// Get piece at
				Piece checkPiece = pieceAt(checkX, checkY);

				// can move only if piece exists and is of the other team
				return (checkPiece != null && checkPiece.side() != currentSide);
			}				
		}
		// Else water, can only go down
		else
		{
			// If only moving the piece(not capturing) - meaning a xDiff and yDiff of 1
			if ((xDiff == 1 || xDiff == -1) && ((yDiff == -1) || (isKing && yDiff == 1)))
			{
				return true;
			}
			// Else check if trying to capture a piece - meaning a xDiff and yDiff of 2
			else if ((xDiff == 2 || xDiff == -2) && ((yDiff == -2) || (isKing && yDiff == 2)))
			{
				// Check that there is a piece that can be captured
				// We divide xDiff by 2 so that it reduces to 1 in while keeping the same direction, same for yDiff
				int checkX =  selectedX + xDiff/2;
				int checkY = selectedY + yDiff/2;

				// Get piece at
				Piece checkPiece = pieceAt(checkX, checkY);

				// can move only if piece exists and is of the other team
				return (checkPiece != null && checkPiece.side() != currentSide);
			}	
		}

		// Else false
		return false;
	}

	// Generates default layout
	private Piece[][] generateDefaultLayout()
	{
		Piece[][] board = new Piece[N][N];

		// Loop through the values of x(for each column, left to right)
		for (int i = 0; i < board.length; ++i)
		{
			// If even row(column)
			if (i % 2 == 0)
			{
				board[i][0] = new Piece(true, this, i, 0, "pawn");
				board[i][2] = new Piece(true, this, i, 2, "bomb");
				board[i][6] = new Piece(false, this, i, 6, "shield");
			}
			// Else odd row
			else
			{
				board[i][1] = new Piece(true, this, i, 1, "shield");
				board[i][5] = new Piece(false, this, i, 5, "bomb");
				board[i][7] = new Piece(false, this, i, 7, "pawn");
			}
		}
		return board;
	}

	// Draws the board
	private void drawBoard()
	{
		// For every row
		for (int i = 0; i < N; i++) 
		{
			// For every column
            for (int j = 0; j < N; j++) 
            {
            	// Determine pen color
                if ((i + j) % 2 == 0) 
            	{
            		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            	}
                else 
                {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                } 
                
                // Check if need to draw a picture for this coordinate
                Piece currentPiece = pieceAt(i, j);
                
                // Check if currentPiece is selectedPiece
                if (selectedPiece != null && currentPiece == selectedPiece)
                {
                	// Then highlight the square
	                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }

                // Draw the square
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);

                 // If null, then no need to draw an image
                if (currentPiece == null)
                {
                	continue;
                }

                // Else is some kind of piece, determine its picture
                String imageFile = "img/";

                // Check what type
                if (currentPiece.isShield())
                {
                	imageFile += "shield";
                }
                else if (currentPiece.isBomb())
                {
                	imageFile += "bomb";
                }
                else
                {
                	imageFile += "pawn";
                }

                // Check what side
                if (currentPiece.isFire())
                {
                	imageFile += "-fire";
                }
                else
                {
                	imageFile += "-water";
                }

                // Check if crowned
                if (currentPiece.isKing())
                {
                	imageFile += "-crowned";
                }

                // Add png extension and draw it
                imageFile += ".png";
                StdDrawPlus.picture(i + .5, j + .5, imageFile, 1, 1);
            }
        }
	}
}