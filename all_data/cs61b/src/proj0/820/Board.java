public class Board
{
	private Piece[][] pieces;
	private int selectedX;
	private int selectedY;
	private boolean fireTurn;
	private boolean moved;

	public Board(boolean shouldBeEmpty)
	{
		pieces = new Piece[8][8];
		selectedX = selectedY = -1;
		fireTurn = true;
		moved = false;
		if (!shouldBeEmpty)
		{
			for (int i = 0; i < 8; i ++)
			{
				if (i % 2 == 0)
				{
					pieces[i][0] = new Piece(true, this, i, 0, "pawn");
					pieces[i][2] = new Piece(true, this, i, 2, "bomb");
					pieces[i][6] = new Piece(false, this, i, 6, "shield");
				}
				else
				{
					pieces[i][1] = new Piece(true, this, i, 1, "shield");	
					pieces[i][5] = new Piece(false, this, i, 5, "bomb");
					pieces[i][7] = new Piece(false, this, i, 7, "pawn");
				}
			} 
		}
	}

	public void place(Piece p, int x, int y)
	{
		if (p == null) return;
		if (!isValidPosition(x, y)) return;

		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (pieces[i][j] == p)
				{
					pieces[i][j] = null;
					break;
				}
			}
		}
		pieces[x][y] = p;
	}

	public Piece pieceAt(int x, int y)
	{
		if (!isValidPosition(x, y))
		{
			return null;
		}
		return pieces[x][y];
	}

	public Piece remove(int x, int y)
	{
		if (!isValidPosition(x, y))
		{
			System.err.println("remove: (" + x + ", " + y + ") out of bounds");
			return null;
		}
		Piece p = pieces[x][y];
		if (p == null)
		{
			System.err.println("remove: no piece at (" + x + ", " + y + ")");
			return null;
		}
		pieces[x][y] = null;
		return p;
	}

	public boolean canSelect(int x, int y)
	{
		if (!isValidPosition(x, y)) return false;
		Piece p = pieceAt(x, y);
		if (p != null)
		{
			if (p.isFire() == fireTurn)
			{
				return !moved;
			}
			else return false;
		}
		else return validMove(selectedX, selectedY, x, y);
	}

	public void select(int x, int y)
	{
		Piece p = pieceAt(x, y);
		if (p == null)
		{
			pieceAt(selectedX, selectedY).move(x, y);
			moved = true;
		}
		selectedX = x;
		selectedY = y;
	}

	public boolean canEndTurn()
	{
		return moved;
	}

	public void endTurn()
	{
		fireTurn = !fireTurn;
		moved = false;
		Piece p = pieceAt(selectedX, selectedY);
		if (p != null) p.doneCapturing();
		selectedX = selectedY = -1;
	}

	public String winner()
	{
		boolean isFire, isWater;
		isFire = isWater = false;
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				Piece p = pieces[i][j];
				if (p != null)
				{
					if (p.isFire()) isFire = true;
					else isWater = true;
				}
				if (isFire && isWater) return null;
			}
		}
		if (isFire)
			return "Fire";
		else if (isWater)
			return "Water";
		return "No one";
	}

	private boolean validMove(int xi, int yi, int xf, int yf)
	{
		Piece p = pieceAt(xi, yi);
		if (p == null) return false;
		if (!isValidPosition(xf, yf) || pieceAt(xf, yf) != null) return false;

		if (!checkDirection(p, yi, yf)) return false;
		if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1 && !moved) // move without capture
		{
			return true;
		}
		else if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2 && (!moved || p.hasCaptured()))
		{
			int xm = (xi + xf) / 2;
			int ym = (yi + yf) / 2;
			Piece q = pieceAt(xm, ym);
			if (q == null) return false;
			return p.isFire() != q.isFire();
		}

		return false;
	}

	private boolean checkDirection(Piece p, int yi, int yf)
	{
		if (p.isKing()) return true;
		else if (p.isFire())
			return yf > yi;
		else
			return yf < yi;
	}

	private boolean isValidPosition(int x, int y)
	{
		return (x >= 0 && x < 8 && y >= 0 && y < 8);
	}

	private void drawBoard()
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				Piece p = pieces[i][j];
				if (selectedX == i && selectedY == j) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				else if ((i + j) % 2 == 0)            StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else                                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				if (p != null)
				{					
					String img = "img/";
					
					if (p.isShield())
						img += "shield";
					else if (p.isBomb())
						img += "bomb";
					else
						img += "pawn";

					if (p.isFire())
						img += "-fire";
					else
						img += "-water";						

					if (p.isKing())
						img += "-crowned";

					img += ".png";
					StdDrawPlus.picture(i + .5, j + .5, img, 1, 1);
				}
			}
		}
	}

	public static void main(String[] args)
	{
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		Board board = new Board(false);
		while (board.winner() == null)
		{
			board.drawBoard();
			if (StdDrawPlus.mousePressed())
			{
				int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (board.canSelect(x, y))
                {
                	board.select(x, y);
                }
			}
			if (StdDrawPlus.isSpacePressed())
			{
				if (board.canEndTurn())
				{
					board.endTurn();
				}
			}
			StdDrawPlus.show(10);
		}
		System.out.println(board.winner());
	}	
}