public class Board
{
	private Piece[][] grid = new Piece[8][8];
	private Piece selectedPiece = null;
	private int selectedX = 0;
	private int selectedY = 0;
	private int mouseX = 0;
	private int mouseY = 0;
	private int numFirePieces = 0;
	private int numWaterPieces = 0;
	private boolean isFireTurn = true;
	private boolean canStillMove = true;
	private boolean hasMovedOnce = false;
	private boolean reselected = false;

	public static void main(String args[])
	{
		Board b = new Board(false);
		b.playGame();
	}

	private void playGame()
	{
		while (winner() == null)
		{
			mouseX = (int) StdDrawPlus.mouseX();
			mouseY = (int) StdDrawPlus.mouseY();

			if (StdDrawPlus.mousePressed())
			{
				if (canStillMove)
				{
					if (canSelect(mouseX, mouseY))
					{
						select(mouseX, mouseY);
					}
				}
			}
			if (StdDrawPlus.isSpacePressed())
			{
				if (canEndTurn())
				{
					endTurn();
				}
			}
			drawBoard();
		}
	}

	public Board(boolean shouldBeEmpty)
	{
		if (!shouldBeEmpty)
		{
			for (int i = 0; i < grid.length; i++)
			{
				for (int j = i % 2; j < grid[0].length; j += 2)
				{
					boolean isFire = j < 4;
					String type = null;
					if (j == 0 || j == grid.length - 1)
						type = "pawn";
					else if (j == 1 || j == grid.length - 2)
						type = "shield";
					else if (j == 2 || j == grid.length - 3)
						type = "bomb";
					else
						continue;
					place(new Piece(isFire, this, i, j, type), i, j);
				}
			}
		}
	}

	public Piece pieceAt(int x, int y)
	{
		if (x < grid.length && y < grid.length && x >= 0 && y >= 0)
		{
			return grid[x][y];
		}
		else
		{
			return null;
		}
	}

	public boolean canSelect(int x, int y)
	{
		if (pieceAt(x, y) == null)
		{
			if (selectedPiece != null && canStillMove)
			{
				return validMove(selectedPiece, selectedX, selectedY, x, y);
			}
		}
		else if (pieceAt(x, y).isFire() == isFireTurn)
		{
			if (selectedPiece == null)
			{
				return true;
			}
			else if (canStillMove && !selectedPiece.hasCaptured())
			{
				reselected = true;
				return true;
			}
		}
		return false;
	}

	private boolean validMove(Piece piece, int xi, int yi, int xf, int yf)
	{
		if (yf > 7 || xf > 7 || yf < 0 || xf < 0)
		{
			return false;
		}

		int dx = xf - xi;
		int dy = yf - yi;

		int dyflip = dy;

		if (!piece.isFire())
		{
			dyflip = -dyflip;
		}

		if (Math.abs(dx) == 2 && (dyflip == 2 || piece.isKing() && dyflip == -2))
		{
			Piece capturedPiece = pieceAt(dx / 2 + xi, dy / 2 + yi);
			if (capturedPiece != null && capturedPiece.isFire() != piece.isFire())
			{
				return true;
			}
		}
		if (!selectedPiece.hasCaptured())
		{
			if (Math.abs(dx) == 1 && (dyflip == 1 || piece.isKing() && dyflip == -1))
			{
				if (pieceAt(xf, yf) == null && !piece.hasCaptured())
				{
					if (pieceAt(xf, yf) == null)
					{
						return true;
					}
				}
			}
		}
		return false;
	}

	public void select(int x, int y)
	{
		if (selectedPiece != null && !reselected)
		{
			hasMovedOnce = true;
			selectedPiece.move(x, y);
			if (!selectedPiece.hasCaptured())
			{
				canStillMove = false;
			}
			else if (selectedPiece.isBomb())
			{
				canStillMove = false;
				remove(x, y);
			}
		}

		reselected = false;

		selectedPiece = pieceAt(x, y);
		selectedX = x;
		selectedY = y;
	}

	public void place(Piece p, int x, int y)
	{
		if (x > grid.length || y > grid.length || x < 0 || y < 0)
		{
			return;
		}
		if (p.isFire())
		{
			numFirePieces++;
		}
		else
		{
			numWaterPieces++;
		}
		grid[x][y] = p;
	}

	public Piece remove(int x, int y)
	{
		if (x > grid.length || y > grid.length || x < 0 || y < 0)
		{
			System.out.println("location out of bounds");
			return null;
		}
		Piece temp = grid[x][y];
		if (temp == null)
		{
			System.out.println("no piece at (" + x + ", " + y + ")");
			return null;
		}

		if (temp.isFire())
		{
			numFirePieces--;
		}
		else
		{
			numWaterPieces--;
		}

		grid[x][y] = null;
		return temp;
	}

	public boolean canEndTurn()
	{
		return hasMovedOnce;
	}

	public void endTurn()
	{
		isFireTurn = !isFireTurn;
		// for testing purposes
		if (selectedPiece != null)
		{
			selectedPiece.doneCapturing();
		}
		hasMovedOnce = false;
		selectedPiece = null;
		canStillMove = true;
	}

	public String winner()
	{
		if (numFirePieces <= 0 && numWaterPieces <= 0)
		{
			return "No one";
		}
		else if (numFirePieces <= 0)
		{
			return "Water";
		}
		else if (numWaterPieces <= 0)
		{
			return "Fire";
		}

		return null;
	}

	private void drawBoard()
	{
		StdDrawPlus.setXscale(0, grid.length);
		StdDrawPlus.setYscale(0, grid[0].length);

		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[0].length; j++)
			{
				drawTile(i, j);
				Piece piece = pieceAt(i, j);
				if (piece != null)
				{
					drawPiece(piece, i, j);
				}
			}
		}
		StdDrawPlus.show(10);
	}

	private void drawTile(int x, int y)
	{
		if ((x + y) % 2 == 0)
		{
			StdDrawPlus.setPenColor(StdDrawPlus.BOOK_LIGHT_BLUE);
		}
		else
		{
			StdDrawPlus.setPenColor(StdDrawPlus.BOOK_BLUE);
		}
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);

		if (x == mouseX && y == mouseY)
		{
			StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		}
	}

	private void drawPiece(Piece piece, int x, int y)
	{
		if (piece == selectedPiece)
		{
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		}

		String isFire = piece.isFire() ? "-fire" : "-water";
		String isKing = piece.isKing() ? "-crowned" : "";
		String type = "pawn";
		if (piece.isBomb())
		{
			type = "bomb";
		}
		else if (piece.isShield())
		{
			type = "shield";
		}
		StdDrawPlus.picture(x + .5, y + .5, "img/" + type + isFire + isKing + ".png", 1, 1);
	}
}
