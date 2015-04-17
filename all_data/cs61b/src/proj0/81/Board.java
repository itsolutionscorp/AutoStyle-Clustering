public class Board
{
	private boolean shouldBeEmpty;
	private static Piece[][] gameBoard;
	// private static boolean[][] selectedPieces;
	private Piece lastSelected = null;
	private boolean hasMoved = false;
	// private boolean hasCaptured = false;
	private boolean currPlayer = true; //is it fire? 
	private int storedx;
	private int storedy;

	public static void main(String[] args)
	{	
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);

        while(true) 
        {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();

                if (b.canSelect((int)x, (int)y))
                {
                	b.select((int)x, (int)y);
                }
            }    

            if (StdDrawPlus.isSpacePressed())
            {
            	if (b.canEndTurn())
            		b.endTurn();
            }            
            StdDrawPlus.show(100);

            b.winner();
        }
	}

	private static void drawBoard(int N) 
	{
		/*creates board*/
        for (int i = 0; i < N; i++) 
        {
            for (int j = 0; j < N; j++) 
            {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                Piece p = gameBoard[i][j];
                if (p != null)
                {
                	if (p.isFire())
       				{
       					if (p.isBomb())
       					{
       						if (p.isKing())
        						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png", 1, 1);
       						else
       							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
       					}
       					else if (p.isShield())
       					{
       						if (p.isKing())
        						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1, 1);
       						else
       							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
       					}
       					else //is pawn
       					{
       						if (p.isKing())
        						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png", 1, 1);
       						else	
       							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
       					}
       				}
       				else //all water
       				{
       					if (p.isBomb())
       					{
       						if (p.isKing())
        						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png", 1, 1);
       						else
       							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
       					}
       					else if (p.isShield())
       					{
       						if (p.isKing())
        						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png", 1, 1);
       						else
       							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
       					}	
       					else //is pawn
       					{
       						if (p.isKing())
        						StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png", 1, 1);
       						else	
       							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
       					}
       				}
                }
            }
        }
        /*initalize pieces*/
       //  for (int i = 0; i < N; i++) 
       //  {
       //  	if (i %2 != 0)
       //  	{
		     //   	StdDrawPlus.picture(i + .5, 7.5, "img/pawn-water.png", 1, 1);
		     //   	StdDrawPlus.picture(i + .5, 5.5, "img/bomb-water.png", 1, 1);
		     //   	StdDrawPlus.picture(i + .5, 1.5, "img/shield-fire.png", 1, 1);
       //  	}
       //  	if (i %2 == 0)
       //  	{
 		    //    	StdDrawPlus.picture(i + 0.5, 0.5, "img/pawn-fire.png", 1, 1);
 		    //    	StdDrawPlus.picture(i + 0.5, 2.5, "img/bomb-fire.png", 1, 1);
 		    //    	StdDrawPlus.picture(i + 0.5, 6.5, "img/shield-water.png", 1, 1);
 		    // }
       //  } 
    }

	public Board(boolean shouldBeEmpty)
	{
		this.shouldBeEmpty = shouldBeEmpty;
		gameBoard = new Piece [8][8];
		if (shouldBeEmpty)
		{
			return;
		}
		// selectedPieces = new boolean[8][8];
		for (int i = 0; i < 8; i++) 
        {
        	if (i %2 != 0)
        	{
        		gameBoard[i][7] = new Piece(false, this, i, 7, "pawn");
        		gameBoard[i][5] = new Piece(false, this, i, 5, "bomb");
        		gameBoard[i][1] = new Piece(true, this, i, 1, "shield");
        	}
        	if (i %2 == 0)
        	{
        		gameBoard[i][0] = new Piece(true, this, i, 0, "pawn");
        		gameBoard[i][2] = new Piece(true, this, i, 2, "bomb");
        		gameBoard[i][6] = new Piece(false, this, i, 6, "shield");
 		    }
        } 
	}

	private boolean inBounds(int x, int y)
	{
		return (x >= 0 && x <= 7 && y >= 0 && y<= 7);  
	}

	public Piece pieceAt(int x, int y)
	{
		if (!inBounds(x, y))
		{
			return null;
		}
		else return gameBoard[x][y];
	}

	public void select(int x, int y)
	{
		// selectedPieces[x][y] = true;
		if (pieceAt(x, y) != null)
		{
			lastSelected = pieceAt(x, y);
			storedx= x;
			storedy = y;
		}
		else //an empty grid, so time for movement
		{
			if (lastSelected != null)
			{
				lastSelected.move(x, y);
				storedx = x;
				storedy = y;
				hasMoved = true;
			}
		} 
	}

	public boolean canSelect(int x, int y) 
	{
		//also gotta make sure that the right player is going, check currPlayer
		Piece p = pieceAt(x, y);

		if (p != null && (p.isFire() == currPlayer)) //a piece is selected 
		{
			if (lastSelected == null || !hasMoved)
			{
				return true;
			}
		}
		//a blank piece is selected i.e. pieceAt(x,y) == null
		else if (p == null && lastSelected != null && lastSelected.isFire() == currPlayer) 
	 	{
	 		if (!hasMoved && validMove(storedx, x, storedy, y))
	 		{
	 			return true;
	 		}
	 		else if (lastSelected.hasCaptured() && validMove(storedx, x, storedy, y) 
	 			&& (Math.abs(storedx - x) == 2))
				return true;
		}
		/*I guess this is where you should end turn*/
		// canEndTurn = true;
		return false;
	}

	private boolean allowedMoves(int xi, int xf, int yi, int yf)
	{
		int dX = Math.abs(xi - xf); 
		int dY = Math.abs(yi - yf);
		return ((dX == 1 && dY == 1) || (dX == 2 && dY == 2)); //must be diagonal
	}

	private boolean validMove(int xi, int xf, int yi, int yf)
	{
		Piece p = pieceAt(xi, yi);
		if (p != null)
		{
			if (inBounds(xi, yi) && inBounds(xf, yf) && allowedMoves(xi, xf, yi, yf)
			 	&& (pieceAt(xf, yf) == null)) //positions are on the board
			{	
				if (p.isKing())
				{
					if (Math.abs(xi - xf) == 2) //capturing case
					{
						Piece capturePiece = pieceAt(xi + ((xf - xi)/2), yi + ((yf - yi)/2));
						return (capturePiece != null && capturePiece.isFire() != p.isFire());
					} 
					return true;
				}
				else if (p.isFire() && yf - yi > 0) //fire moves up
				{
					if (yf - yi == 2)
					{
						Piece capturePiece = pieceAt(xi + ((xf - xi)/2), yi + 1);
						return (capturePiece != null && !capturePiece.isFire());
					}
					return true;
				}
				else if (yf - yi < 0) //a water piece
				{
					if (yf - yi == -2)
					{
						Piece capturePiece = pieceAt(xi + ((xf - xi)/2), yi - 1);
						return (capturePiece != null && capturePiece.isFire());
					}
					return true;
				}
			}
		}
		return false;
	}

	public void place(Piece p, int x, int y)
	{
		if (inBounds(x, y))
		{
			gameBoard[x][y] = p;
		}
		return;
	}

	public Piece remove(int x, int y)
	{
		if (inBounds(x, y))
		{
			Piece removed = pieceAt(x, y);
			gameBoard[x][y] = null;
			return removed;
		}
		else
		{
			System.out.println("Out of bounds");
			return null;
		}
	}

	public boolean canEndTurn() 
	{
		return (hasMoved || (lastSelected != null && lastSelected.hasCaptured()));	
	} 

	public void endTurn()
	{
		currPlayer = !currPlayer;
		hasMoved = false;
		lastSelected.doneCapturing();
		lastSelected = null; 
	}

	public String winner()
	{	
		if (fireLeft() && !waterLeft())
			return "Fire";
		else if (!fireLeft() && waterLeft())
			return "Water";
		else if (!fireLeft() && !waterLeft())
			return "No one";
		else
			return null;
	}

	private boolean fireLeft()
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				Piece p = pieceAt(i, j);
				if (p != null && p.isFire())
					return true;
			}
		}
		return false;
	}

	private boolean waterLeft()
	{
		for (int i = 0; i < 8; i ++)
		{
			for (int j = 0; j < 8; j++)
			{
				Piece p = pieceAt(i, j);
				if (p != null && !(p.isFire()))
					return true;
			}
		}
		return false;
	}
}