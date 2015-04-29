public class Board
{
	private Piece[][] board = new Piece[8][8];
    private int defaultBoardSize = 8;
	private int xPiece = -1;
	private int yPiece = -1;
	private boolean player0Turn = true; 
	private boolean hasMoved = false;
	private boolean hasMovedOnce = false;

	public Board(boolean shouldBeEmpty)
	{
		if(shouldBeEmpty == false)
		{
			addPieces();
		}
	}

	public static void main(String[] args)
	{
		Board newBoard = new Board(false);         
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
    	int xSelected = 0;
    	int ySelected = 0;
    
        while(true) {
			newBoard.drawBoard(8);

			if(StdDrawPlus.mousePressed())
			{
        	    xSelected = (int) StdDrawPlus.mouseX();
    	        ySelected = (int) StdDrawPlus.mouseY();

    	        if(newBoard.canSelect(xSelected, ySelected))
    	        {
    	        	newBoard.select(xSelected, ySelected);
    	        }
    	        // Checks if piece can multi-capture.
        		if(newBoard.hasMoved == true && newBoard.canSelect(xSelected, ySelected))
	    		{
        			if(newBoard.board[xSelected][ySelected].hasCaptured())
        			{
    	        		newBoard.select(xSelected, ySelected);
        			}
        		}
			}
			if(StdDrawPlus.isSpacePressed())
			{	
				if(newBoard.canEndTurn())
				{
		            newBoard.winner();
					newBoard.endTurn();
				}
			}
            StdDrawPlus.show(10);
        }
 	}

	/* Adds the correct pieces to their default locations
	 * on the board. It adds the pieces to the board array
	 * which was initialized to hold all Piece Objects. 
	 */
	private void addPieces()
	{
		for(int i = 0; i < defaultBoardSize; i++)
		{
			for(int j = 0; j < defaultBoardSize; j++)
			{
				if(j % 2 == 0 && i % 2 == 0)
				{
					if(j == 0)
					{	
						board[i][j] = new Piece(true, this, i, j, "pawn");
					}
					else if(j == 2)
					{
						board[i][j] = new Piece(true, this , i, j, "bomb");						
					}
					else if(j == 6)
					{
						board[i][j] = new Piece(false, this , i, j, "shield");
					}
				}
				else
				{
					if(i % 2 != 0)
					{
						if(j == 7)
						{	
							board[i][j] = new Piece(false, this, i, j, "pawn");
						}
						else if(j == 5)
						{
							board[i][j] = new Piece(false, this , i, j, "bomb");						
						}
						else if(j == 1)
						{
							board[i][j] = new Piece(true, this , i, j, "shield");
						}	
					}
				}
			}
		}
	}

	/* This method draws the pieces that are at the locations
	 * decided by the addPieces() method.
	 */
	private void drawPiece(Piece piece, int row, int col)
	{
		if(piece.isFire())
		{
			if (piece.isShield())
			{
				if(piece.isKing() == true)
				{
	            	StdDrawPlus.picture(row + .5, col + .5, "img/shield-fire-crowned.png", 1, 1);
				}
				else
				{
		            StdDrawPlus.picture(row + .5, col + .5, "img/shield-fire.png", 1, 1);
				}
			}
			else if(piece.isBomb())
			{
				if(piece.isKing() == true)
				{
	            	StdDrawPlus.picture(row + .5, col + .5, "img/bomb-fire-crowned.png", 1, 1);
				}
				else
				{
		            StdDrawPlus.picture(row + .5, col + .5, "img/bomb-fire.png", 1, 1);
				}
			}
			else
			{
				if(piece.isKing())
				{
	    	    	StdDrawPlus.picture(row + .5, col + .5, "img/pawn-fire-crowned.png", 1, 1);
				}
	    	    else
    	    	{
    	    		StdDrawPlus.picture(row + .5, col + .5, "img/pawn-fire.png", 1, 1);
    	    	}
			}
		}
		else
		{
			if (piece.isShield())
			{
				if(piece.isKing() == true)
				{
	            	StdDrawPlus.picture(row + .5, col + .5, "img/shield-water-crowned.png", 1, 1);
				}
				else
				{
		            StdDrawPlus.picture(row + .5, col + .5, "img/shield-water.png", 1, 1);
				}	
			}
			else if(piece.isBomb())
			{
				if(piece.isKing() == true)
				{
	            	StdDrawPlus.picture(row + .5, col + .5, "img/bomb-water-crowned.png", 1, 1);
				}
				else
				{
		            StdDrawPlus.picture(row + .5, col + .5, "img/bomb-water.png", 1, 1);
				}
			}
			else
			{
				if(piece.isKing())
				{
	    	    	StdDrawPlus.picture(row + .5, col + .5, "img/pawn-water-crowned.png", 1, 1);
				}
				else
				{
	    	    	StdDrawPlus.picture(row + .5, col + .5, "img/pawn-water.png", 1, 1);
				}
			}			
		}
	}

	/* Method that draws the board. 
	 * Makes a call to drawPiece to draw the pieces on the board. 
	 */
    private void drawBoard(int N) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                // Sets the color of the selected box with the piece. 
                if (board[i][j] != null)
                {
	            	if(i == xPiece && j == yPiece)
                	{
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                	}
	                drawPiece(board[i][j], i, j);
                }
            }
        }
    }

    /* Checks if there is a piece at the location */
	public Piece pieceAt(int x, int y)
	{
		if(x < 0 || y < 0 || x > 7 || y > 7)
		{
			return null;
		}
		return board[x][y];
	}

	public boolean canSelect(int x, int y)
	{
    	if(x < defaultBoardSize && x >= 0 && y < defaultBoardSize && y >= 0)
    	{
    		if(pieceAt(x, y) != null && pieceAt(x, y).isFire() == player0Turn && hasMoved == false)
    		{
    			return true;
    		}
    		else if(xPiece < 0 || yPiece < 0)
			{
				if(pieceAt(x, y) != null && pieceAt(x, y).isFire() == player0Turn && hasMoved == false)
				{
					return true;
				}
			}
    		else if(pieceAt(x, y) == null && isValidMove(x, y))
    		{
    			return true;
    		}
		}
		return false;
	}

	public void select(int x, int y)
	{
		if(pieceAt(x, y) != null)
		{
			xPiece = x;
			yPiece = y;		
		}
		else if(pieceAt(xPiece, yPiece) != null && pieceAt(x, y) == null)
		{
			pieceAt(xPiece, yPiece).move(x, y);
			xPiece = x;
			yPiece = y;
			hasMoved = true;
		}
    }

	/* This method checks to see if the location that is clicked is a 
	 * valid location for the piece to move to. If the piece is a king, 
	 * then it can move in any direction. Also, this method accounts
	 * for whether a piece has been captured or not and considers
	 * that when checking if the mouse-clicked location is valid. 
	 * It also handles the cases for multi-capturing. 
	 */
	private boolean isValidMove(int xSelected, int ySelected)
	{	
		int xChange = 0;
		int yChange = 0;

		// Added hasMoved == false here to ensure that piece only moves once.
		if(board[xSelected][ySelected] == null && hasMoved == false)
		{
			if(xSelected == xPiece + 1 || xSelected == xPiece - 1)
				{
					if(board[xPiece][yPiece] == null)
					{
						return false;
					}
					if(board[xPiece][yPiece].isFire() && player0Turn == true)
					{
						if(board[xPiece][yPiece].isKing())
						{
							if(ySelected == yPiece + 1 || ySelected == yPiece - 1)
							{
								hasMovedOnce = true;
								return true;
							}
						}
						else if(ySelected == yPiece + 1)
						{
							hasMovedOnce = true;
							return true;
						}
					}
					else if(!board[xPiece][yPiece].isFire() && !player0Turn)
					{
						if(board[xPiece][yPiece].isKing())
						{
							if(ySelected == yPiece + 1 || ySelected == yPiece - 1)
							{
								hasMovedOnce = true;
								return true;
							}
						}
						else if(ySelected == yPiece - 1)
						{
							hasMovedOnce = true;
							return true;
						}
					}
				}	
			/* The following portion handles the piece capturing */
			else if(xSelected == xPiece + 2 || xSelected == xPiece - 2)
				{
					if(ySelected == yPiece + 2 || ySelected == yPiece - 2)
					{
						// Checks if there is a piece in between the new location and old place.
						xChange = xSelected - xPiece;
						yChange = ySelected - yPiece;
						if(pieceAt(xPiece + xChange/2, yPiece + yChange/2) != null)
						{
							if(pieceAt(xPiece + xChange/2, yPiece + yChange/2).isFire() != pieceAt(xPiece, yPiece).isFire())
							{
								hasMovedOnce = false;
								return true;					
							}
						}
					}
				}
			}	
		/* Added functionality for multi-capturing.
		 * HasMovedOnce ensures that player cannot select a square that is 
		 * two spaces away if there is no piece in between the desired location
		 * and the piece's current location. 
		 */
		else if(board[xSelected][ySelected] == null && hasMovedOnce == false)
		{
			if(xSelected == xPiece + 2 || xSelected == xPiece - 2)
				{
					if(ySelected == yPiece + 2 || ySelected == yPiece - 2)
					{
						// Checks if there is a piece in between the new location and old place.
						xChange = xSelected - xPiece;
						yChange = ySelected - yPiece;
						if(pieceAt(xPiece + xChange/2, yPiece + yChange/2) != null)
						{
							if(pieceAt(xPiece + xChange/2, yPiece + yChange/2).isFire() != pieceAt(xPiece, yPiece).isFire())
							{
								return true;
							}
						}
						else
						{
							return false;
						}
					}
				}
		}
		return false;			
	}

	public void place(Piece p, int x, int y)
	{
		xPiece = x;
		yPiece = y;
		board[xPiece][yPiece] = p;
		canEndTurn();
	}

	public Piece remove(int x, int y)
	{
		Piece temp = board[x][y];
		board[x][y] = null;
		return temp;
	}

	public boolean canEndTurn()
	{
		return hasMoved;
	}

	public void endTurn()
	{
		player0Turn = !player0Turn;
		if(board[xPiece][yPiece] != null)
		{
			if(board[xPiece][yPiece].hasCaptured())
			{
				board[xPiece][yPiece].doneCapturing();
			}
			xPiece = -1;
			yPiece = -1;
		}
		hasMoved = false;
	} 

	/* Runs throughout the game and returns a String with the winner
	 * after either no pieces remain on the board or only 1 form (fire/water)
	 * of pieces are remaining. 
	 */
	public String winner()
	{
		int firePieces = 0;
		int waterPieces = 0;

		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(board[i][j] != null)
				{
					if(board[i][j].isFire())
					{
						firePieces += 1;	
					}
					else if(!board[i][j].isFire() && board[i][j] != null)
					{
						waterPieces += 1;
					}				
				}
			}
		}

		if(firePieces > 0 && waterPieces == 0)
		{
			return "Fire";
		}
		else if(waterPieces > 0 && firePieces == 0)
		{
			return "Water";
		}
		else if(firePieces == 0 && waterPieces == 0)
		{
			return "No One";
		}
		else
		{
			return null;
		}
	}
}