import java.lang.Math;
public class Board
{
	private static final int N = 8;
	//true for fire, false for water
	private boolean player = true;
	private boolean hasMoved = false;
	private boolean selectedPiece = false;
	private int selectedX = -1;
	private int selectedY = -1;
	private Piece pieceToMove;
	private String boardToDraw;
	private Piece[][] pieces;
	private boolean noOneMoved = true;
	private boolean multi = false;
	public Board(boolean shouldBeEmpty)
	{
		if (shouldBeEmpty)
		{
			boardToDraw = "empty";
			pieces = new Piece[N][N];
		}
		else
		{
			boardToDraw = "filled";
			pieces = new Piece[N][N];
			for (int i = 1; i<8; i+=2)
			{
				pieces[i][7] = new Piece(false, this, i, 0, "pawn");
				pieces[i][5] = new Piece(false, this, i, 2, "bomb");
				pieces[i][1] = new Piece(true, this, i, 6, "shield");
			}
			for (int i = 0; i<8; i+=2)
			{
				pieces[i][6] = new Piece(false, this, i, 1, "shield");
				pieces[i][2] = new Piece(true, this, i, 5, "bomb");
				pieces[i][0] = new Piece(true, this, i, 7, "pawn");
			}
		}
	}
	
	private void drawBoardEmpty(int scale)
	{
		for (int i = 0; i < scale; i++) 
		{
			for (int j = 0; j < scale; j++) 
			{
				if ((i + j) % 2 == 0) 
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else                  
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
	}
	
	private void drawFullBoard(int scale)
	{
		drawBoardEmpty(scale);
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if (pieces[i][j] != null && pieces[i][j].isFire()) 
				{
					if (pieces[i][j].isBomb())
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
					else if (pieces[i][j].isShield())
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
					else
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
				}
				else if (pieces[i][j] != null && !pieces[i][j].isFire())
				{
					if (pieces[i][j].isBomb())
						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
					else if (pieces[i][j].isShield())
						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
					else
						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
				}
			}
		}
	}
	
	public Piece pieceAt(int x, int y)
	{
		if (x >= N || y>= N || pieces[x][y] == null)
			return null;
		return pieces[x][y];
	}
	
	public boolean canSelect(int x, int y)
	{
		if ((!hasMoved || multi) && x < N && y < N && x>=0 && y>=0)
			if (!selectedPiece)
				return validPiece(x, y);
			else if (pieceAt(x,y) == null && !multi)
				return (validMove(x, y)|| validcaptureMove(x,y));
			else if (pieceAt(x,y) == null && multi)
				return validcaptureMove(x,y);
			else if (!multi)
				return true;
		return false;
	}
	
	private boolean validPiece(int x, int y)
	{
		if ((pieces[x][y] == null))
			return false;
		else if (pieces[x][y].isFire() == player)
			return true;
		if (boardToDraw == "empty" && (noOneMoved == true))
			return true;
		return false;
	}
	
	private boolean validMove(int x, int y)
	{
		int differenceX;
		int differenceY;
		if (pieces[x][y] == null)
			if (pieceAt(selectedX,selectedY) == null)
				return false;
			else if (pieceAt(selectedX,selectedY).isKing())
			{
				differenceY = Math.abs(y - selectedY);
				differenceX = Math.abs(x - selectedX);
				return (differenceX == 1 && differenceY == 1);
			}
			else if (pieceToMove.isFire() == true)
			{
				differenceY = (y - selectedY);
				differenceX = Math.abs(x - selectedX);
				return (differenceX == 1 && differenceY == 1);
			}
			else
			{
				differenceY = (y - selectedY);
				differenceX = Math.abs(x - selectedX);
				return (differenceX == 1 && differenceY == -1);
			}
		return false;
	}
	
	private boolean validcaptureMove(int x, int y)
	{
		int differenceX;
		int differenceY;
		if (pieces[x][y] == null)
		{
			if (pieceAt(selectedX,selectedY) == null)
				return false;
			else if (pieceAt((selectedX + x)/2, (selectedY + y)/2) == null)
				return false;
			else if (pieceAt(selectedX,selectedY).isKing())
			{
				differenceY = Math.abs(y - selectedY);
				differenceX = Math.abs(x - selectedX);
				if ((pieceAt(((selectedX + x)/2), (selectedY + y)/2).isFire()) != pieceToMove.isFire())
					return (differenceX == 2 && differenceY == 2);
			}
			else if (pieceAt(selectedX,selectedY).isFire())
			{
				differenceY = (y - selectedY);
				differenceX = Math.abs(x - selectedX);
				if ((pieceAt((x + selectedX)/2,(selectedY+y)/2).isFire()) != pieceToMove.isFire())
					return (differenceX == 2 && differenceY == 2);
			}
			else
			{
				differenceY = (y - selectedY);
				differenceX = Math.abs(x - selectedX);
				if ((pieceAt((x + selectedX)/2,(selectedY+y)/2).isFire()) != pieceToMove.isFire())
					return (differenceX == 2 && differenceY == -2);
			}
		}
		return false;
	}
	
	public void select(int x, int y)
	{
		if (canSelect(x, y) || multi)
		{
			if (pieceAt(x,y) != null && !multi)
			{
				selectedPiece = true;
				pieceToMove = pieceAt(x,y);
				selectedX = x;
				selectedY = y;
			}
			else 
			{
				if (selectedPiece == true && !multi)
				{
					if (noOneMoved)
						player = pieceToMove.isFire();
					place(pieceToMove, x, y);
					hasMoved = true;
					noOneMoved = false;
					selectedX = x;
					selectedY = y;
					multi = true;
				}
				else if (multi)
				{
					if (pieceAt(selectedX,selectedY) != null)
					{
						pieceToMove = pieceAt(selectedX,selectedY);
						if (validcaptureMove(x, y))
						{
							place(pieceToMove, x, y);
							selectedX = x;
							selectedY = y;
						}
					}
				}
			}
		}
	}
	
	public void place(Piece p, int x, int y)
	{
		if (x < N && y < N && p != null)
		{
			pieces[x][y] = p;
			p.move(x,y);
			if (selectedX != -1)
			{
				remove(selectedX, selectedY);
				if (Math.abs(x-selectedX) == 2)
					remove((selectedX + x)/2, (selectedY + y)/2);
				if (Math.abs(y-selectedY) == 2)
					remove((selectedX + x)/2, (selectedY + y)/2);
				if (p.isBomb() && (Math.abs(x-selectedX) == 2))
				{
					if (x+1 < N && y + 1 < N)
					{
						if (pieces[x+1][y+1]!= null)
						{
							if (!(pieces[x+1][y+1].isShield()))
							{
								remove(x+1, y+1);
							}
						}
					}
					if (x+1 < N && y - 1< N && y-1 >= 0)
					{
						if (pieces[x+1][y-1]!= null)
						{
							if (!(pieces[x+1][y-1].isShield()))
							{
								remove(x+1, y-1);
							}
						}
					}
					if (x-1 < N && y + 1< N && x-1 >= 0)
					{
						if (pieces[x-1][y+1]!= null)
						{
							if (!(pieces[x-1][y+1].isShield()))
							{
								remove(x-1, y+1);
							}
						}
					}
					if (x-1 < N && y - 1 < N && x-1 >=0 && y-1 >= 0)
					{
						if (pieces[x-1][y-1]!= null)
						{
							if (!(pieces[x-1][y-1].isShield()))
							{
								remove(x-1, y-1);
							}
						}
					}
					remove(x,y);
				}
			}
		}
	}

	
	public Piece remove(int x, int y)
	{
		if (x < N && y < N)
		{
			Piece copy = pieces[x][y];
			pieces[x][y] = null;
			return copy;
		}
		System.out.println("outside of bounds");
		return null;
	}
	
	public boolean canEndTurn()
	{
		return hasMoved;
	}
	public void endTurn()
	{
		hasMoved = false;
		selectedPiece = false;
		multi = false;
		if (pieceAt(selectedX, selectedY) != null)
			pieceAt(selectedX, selectedY).doneCapturing();
		if (player == true)
			player = false;
		else
			player = true;
	}
	public String winner()
	{
		int firecounter = 0;
		int watercounter = 0;
		for (int i = 0; i<8; i++)
			for (int j = 0; j<8; j++)
				if (pieces[i][j] != null)
				{
					if (pieceAt(i, j).isFire())
						firecounter++;
					else
						watercounter++;
				}
		if (watercounter == 0 && firecounter > 0)
			return "fire";
		else if (firecounter == 0 && watercounter > 0)
			return "water";
		else if (firecounter > 0 && watercounter > 0)
			return null;
		else
			return "No one";
	}

	public static void main(String args[])
	{
		Board b = new Board(false);
		int scale = 8;
        StdDrawPlus.setXscale(0, scale);
        StdDrawPlus.setYscale(0, scale);
		while(true) 
		{
			if (b.boardToDraw == "empty")
				b.drawBoardEmpty(scale);
			else
				b.drawFullBoard(scale);
			if (StdDrawPlus.mousePressed()) 
			{
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
				b.select(x,y);
			}
			if (StdDrawPlus.isSpacePressed() && b.hasMoved)
				b.endTurn();
			StdDrawPlus.show(10);			
		} 
	}
}