public class Board
{
	private Piece[][] pieces;
	private static int N = 8;
	private boolean turn; //true = Fire's turn
	private boolean hasMoved;
	private boolean playerHasSelected;
	private Piece initSelected;
	private int[] selectedPos = new int[2];

	public Board(boolean shouldBeEmpty)
	{
		pieces = new Piece[N][N];
		
		if (!shouldBeEmpty)
		{
			for (int i = 0; i < N; i++)
			{
				for (int j = 0; j < N; j++)
				{
					if ((i + j) % 2 == 0)
					{
						if (j <= 2 || j >= N-3)
						{
							boolean isFire = false;
							String type = "";

							if (j == 0) {isFire = true; type = "pawn";}
							else if (j == 1) {isFire = true; type = "shield";}
							else if (j == 2) {isFire = true; type = "bomb";}
							else if (j == N-1) {isFire = false; type = "pawn";}
							else if (j == N-2) {isFire = false; type = "shield";}
							else if (j == N-3) {isFire = false; type = "bomb";}

							pieces[i][j] = new Piece(isFire, this, i, j, type);
						}
					}
				}
			}
		}
		turn = true; //first turn goes to fire
		playerHasSelected = false;
		selectedPos[0] = -1;
		selectedPos[1] = -1;
		initSelected = null;
		hasMoved = false;
	}

	private void drawBoard() 
	{
        for (int i = 0; i < N; i++) 
        {
            for (int j = 0; j < N; j++) 
            {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);

                Piece piece = pieces[i][j];
                if (piece != null) 
                {
                	String filename = createFileName(piece);

                    StdDrawPlus.picture(i + .5, j + .5, filename, 1, 1);
                }
                else if (piece == initSelected && playerHasSelected)
                {
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
            }
        }
    }

    private String createFileName(Piece piece)
    {
    	String filename = "img/";

    	String type;
    	if (piece.isShield()) 		type = "shield";
    	else if (piece.isBomb())	type = "bomb";
    	else 						type = "pawn";

    	String fireOrWater;
    	if (piece.isFire()) fireOrWater = "fire";
        else				fireOrWater = "water";

        filename = filename + type + "-" + fireOrWater;

        if(piece.isKing()) filename = filename + "-crowned";

        filename = filename + ".png";

        return filename;
    }

	public Piece pieceAt(int x, int y)
	{
		if (! (inRange(x) && inRange(y)))	return null;
		else if (pieces[x][y] != null) 		return pieces[x][y];
		else								return null;
	}

	public boolean canSelect(int x, int y)
	{
		if (!playerHasSelected)
		{ //player's first selection must be their piece
			Piece p = pieceAt(x, y);
			if (p != null && (p.isFire() == turn))
			{
				return true;
			}
			return false;
		}
		else
		{ //player's second selection must be a valid move
	//or switching selection to a different one of their pieces
			if (initSelected != null)
			{
				if (validMove(selectedPos[0], selectedPos[1], x, y))
				{
					return true;
				}
				else if ((! initSelected.hasCaptured()) && (! hasMoved))
				{
					Piece p = pieceAt(x, y);
					if (p != null && (p.isFire() == turn))
					{
						return true;
					}
				}
			}
		}
		return false; 
	}

	private boolean validMove(int xi, int yi, int xf, int yf)
	{
		Piece pi = pieceAt(xi, yi);
		Piece pf = pieceAt(xf, yf);

		if (pi != null)
		{
			if (! pi.isKing())
			{//non-kings can't go backwards
				if ( ((yf < yi) && pi.isFire())
					|| ((yf > yi) && !pi.isFire()) )
				{return false;}
			}
			if (pf == null && inRange(xf) && inRange(yf)) 
			//can't be a piece in the destination; destination is on board
			{
				if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1)
				{ //subtype: plain move means no capture
					if (pi.hasCaptured() || hasMoved) return false;
					else return true;
				}
				if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2))
				{ //subtype: capture move only works if theres an 
					//enemy piece in between and the piece is either
					//on a multicapture spree or has not moved yet
					if ((!hasMoved && !pi.hasCaptured()) || (pi.hasCaptured()))
					{
						int avgx = (xf + xi)/2;
						int avgy = (yf + yi)/2;
						Piece pmid = pieceAt(avgx, avgy);
						if (pmid != null && pmid.isFire() != pi.isFire() )
						{
							return true;
						}
					}
				}
			}
		}
		return false; //if there is no piece at xi,yi no move
	}

	public void select(int x, int y)
	{
		Piece p = pieceAt(x, y);
		if (playerHasSelected)
		{
			if (p != null && (p.isFire() == turn))
			{
				initSelected = p;
				hasMoved = false;
			}
			else
			{
				boolean isKingBefore = initSelected.isKing();
				initSelected.move(x, y); //need to end turn immediately after moving if it was promoted to king
				hasMoved = true;
				if (isKingBefore != initSelected.isKing())
				{
					initSelected.doneCapturing();
				}
			}
		}
		else
		{
			playerHasSelected = true;
			initSelected = p;
		}
		selectedPos[0] = x;
		selectedPos[1] = y;
	}

	public void place(Piece p, int x, int y)
	{
		if (p != null && inRange(x) && inRange(y))
		{
			if (pieces[x][y] != p && onBoard(p))
			{
				hasMoved = true;
			}
			pieces[x][y] = p;
		}		
	}
	
	private boolean onBoard(Piece p)
	{
		for (Piece[] subset : pieces)
		{
			for (Piece piece : subset)
			{
				if (piece == p)
				{
					return true;
				}
			}			
		}
		return false;
	}

	public Piece remove(int x, int y)
	{
		if (inRange(x) && inRange(y))
		{
			Piece p = pieces[x][y];
			pieces[x][y] = null;

			if (p != null)
			{
				return p;
			}
		}
		return null;
	}

	private boolean inRange(int x)
	{
		return (x >= 0 && x < N);
	}

	public boolean canEndTurn()
	{
		if (initSelected != null && initSelected.hasCaptured())
		{
			return true;
		}
		if (hasMoved)
		{
			return true;
		}
		return false;
	}

	public void endTurn()
	{
		turn = !turn;
		playerHasSelected = false;
		if (initSelected != null)
		{
			initSelected.doneCapturing();
		}
		initSelected = null;
		selectedPos[0] = -1;
		selectedPos[1] = -1;
		hasMoved = false;
	}

	public String winner()
	{
		int fireCount = 0;
		int waterCount = 0;
		for (int i = 0; i < N; i += 1)
		{
			for (int j = 0; j < N; j += 1)
			{
				if (pieces[i][j] != null)
				{
					if (pieces[i][j].isFire())
					{
						fireCount += 1;
					}
					else
					{
						waterCount += 1;
					}
				}
			}
		}
		if (fireCount == 0 && waterCount == 0)
		{
			return "No one";
		}
		else if (fireCount == 0)
		{
			return "Water";
		}
		else if (waterCount == 0)
		{
			return "Fire";
		}
		else
		{
			return null;
		}
	}

	public static void main(String[] args)
	{
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		Board b = new Board(false);
		b.drawBoard();

		while(b.winner() == null) 
		{
            b.drawBoard();
            if (StdDrawPlus.mousePressed())
            {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();

                int xint = (int) x;
                int yint = (int) y;

                if (b.canSelect(xint, yint))
                {
                	b.select(xint, yint);
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	StdDrawPlus.filledSquare(xint + .5, yint + .5, .5);
                }
            }

            if (StdDrawPlus.isSpacePressed())
            {
            	if (b.canEndTurn())
            	{
            		b.endTurn();
            	}
            }         
            StdDrawPlus.show(100);
        }
        System.out.println(b.winner());
	}
}