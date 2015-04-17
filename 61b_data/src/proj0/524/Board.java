public class Board
{
	private Piece pieces[][] = new Piece[8][8];

	private int playersTurn = 0;
	private int hasSelected = 0;
	private int hasMoved;
	private int captured = 0;
	private int bombExploded = 0;

	private Piece tempPiece;
	private int tempPieceX;
	private int tempPieceY;

	private Piece removedPiece;

	private int firePieces;
	private int waterPieces;


	public static void main(String[] args)
	{
		Board thisboard = new Board(false);
		while(thisboard.winner() == null)
		{
			if (StdDrawPlus.mousePressed())
			{
				double x = StdDrawPlus.mouseX();
				double y = StdDrawPlus.mouseY();
				int a = (int) x;
				int b = (int) y;
				if(thisboard.canSelect(a, b) == true)
				{	
					thisboard.select(a, b);
					thisboard.drawBoard(8);
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
					StdDrawPlus.filledSquare(a + .5, b + .5, .5);
					thisboard.drawPieces();
					System.out.println("Has moved: " + thisboard.hasMoved);
					System.out.println("Has selected: " + thisboard.hasSelected);
					System.out.println("Has captured: " + thisboard.captured);
					System.out.println("Did a bomb explode? " + thisboard.bombExploded);
					System.out.println("-------------------");
					StdDrawPlus.show(100);
					System.out.println("Water Pieces = " + thisboard.waterPieces + " Fire Pieces = " + thisboard.firePieces);
				}
			}
			if (StdDrawPlus.isSpacePressed())
			{
				if (thisboard.canEndTurn() == true)
				{
					thisboard.endTurn();
				}
			}
		}
	}
	public Board(boolean shouldBeEmpty)
	{
		if (shouldBeEmpty == true)
		{
			drawBoard(8);
		}
		else if (shouldBeEmpty == false)
		{
			drawBoard(8);
			for (double a = 1.5; a < 8.5; a += 2.0)
			{
				StdDrawPlus.picture(a, 7.5, "img/pawn-water.png", 1, 1);
				int tempx = (int) a;
				int tempy = (int) 7.5;
				pieces[tempx][tempy] = new Piece(false, this, tempx, tempy, "pawn");
				waterPieces++;
			}
			for (double a = 0.5; a < 8.5; a += 2.0)
			{
				StdDrawPlus.picture(a, 6.5, "img/shield-water.png", 1, 1);
				int tempx = (int) a;
				int tempy = (int) 6.5;
				pieces[tempx][tempy] = new Piece(false, this, tempx, tempy, "shield");
				waterPieces++;
			}
			for (double a = 1.5; a < 8.5; a += 2.0)
			{
				StdDrawPlus.picture(a, 5.5, "img/bomb-water.png", 1, 1);
				int tempx = (int) a;
				int tempy = (int) 5.5;
				pieces[tempx][tempy] = new Piece(false, this, tempx, tempy, "bomb");
				waterPieces++;
			}
			//Intializing the location of the fire team:
			for (double a = 0.5; a < 8.5; a += 2.0)
			{
				StdDrawPlus.picture(a, 0.5, "img/pawn-fire.png", 1, 1);
				int tempx = (int) a;
				int tempy = (int) 0.5;
				pieces[tempx][tempy] = new Piece(true, this, tempx, tempy, "pawn");
				firePieces++;
			}
			for (double a = 1.5; a < 8.5; a += 2.0)
			{
				StdDrawPlus.picture(a, 1.5, "img/shield-fire.png", 1, 1);
				int tempx = (int) a;
				int tempy = (int) 1.5;
				pieces[tempx][tempy] = new Piece(true, this, tempx, tempy, "shield");
				firePieces++;
			}
			for (double a = 0.5; a < 8.5; a += 2.0)
			{
				StdDrawPlus.picture(a, 2.5, "img/bomb-fire.png", 1, 1);
				int tempx = (int) a;
				int tempy = (int) 2.5;
				pieces[tempx][tempy] = new Piece(true, this, tempx, tempy, "bomb");
				firePieces++;
			}
		}
	}
	private void drawPieces()
	{
		firePieces = 0;
		waterPieces = 0;
		for (int i = 0 ; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				Piece tempoPiece = pieces[i][j];
				if (tempoPiece != null)
				{
					if (tempoPiece.isFire() == true)
					{
						if (tempoPiece.isKing() == true)
						{
							if (tempoPiece.isShield() == true)
							{
								StdDrawPlus.picture(i + .5, j + .5, "img/" + "shield-fire-crowned.png", 1, 1);
								firePieces++;
							}
							else if(tempoPiece.isBomb() == true)
							{
								StdDrawPlus.picture(i + .5, j + .5, "img/" + "bomb-fire-crowned.png", 1, 1);
								firePieces++;
							}
							else
							{
								StdDrawPlus.picture(i + .5, j + .5, "img/" + "pawn-fire-crowned.png", 1, 1);
								firePieces++;
							}
						}
						else if (tempoPiece.isShield() == true)
						{	
							StdDrawPlus.picture(i + .5, j + .5, "img/" + "shield" + "-fire.png", 1, 1);
							firePieces++;
						}
						else if (tempoPiece.isBomb() == true)
						{
							StdDrawPlus.picture(i + .5, j + .5, "img/" + "bomb" + "-fire.png", 1, 1);
							firePieces++;
						}
						else
						{
							StdDrawPlus.picture(i + .5, j + .5, "img/" + "pawn" + "-fire.png", 1, 1);
							firePieces++;
						}
					}
					if (tempoPiece.isFire() == false)
					{
						if (tempoPiece.isKing() == true)
						{
							if (tempoPiece.isShield() == true)
							{
								StdDrawPlus.picture(i + .5, j + .5, "img/" + "shield-water-crowned.png", 1, 1);
								waterPieces++;
							}
							else if(tempoPiece.isBomb() == true)
							{
								StdDrawPlus.picture(i + .5, j + .5, "img/" + "bomb-water-crowned.png", 1, 1);
								waterPieces++;
							}
							else
							{
								StdDrawPlus.picture(i + .5, j + .5, "img/" + "pawn-water-crowned.png", 1, 1);
								waterPieces++;
							}
						}
						else if (tempoPiece.isShield() == true)
						{
							StdDrawPlus.picture(i + .5, j + .5, "img/" + "shield" + "-water.png", 1, 1);
							waterPieces++;
						}
						else if (tempoPiece.isBomb() == true)
						{
							StdDrawPlus.picture(i + .5, j + .5, "img/" + "bomb" + "-water.png", 1, 1);
							waterPieces++;
						}
						else
						{
							StdDrawPlus.picture(i + .5, j + .5, "img/" + "pawn" + "-water.png", 1, 1);
							waterPieces++;
						}
					}
				}
			}
		}

	}
	private void drawBoard(int N)
	{
		StdDrawPlus.setXscale(0, N);
		StdDrawPlus.setYscale(0, N);
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else			 	  StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
	}
	public Piece pieceAt(int x, int y)
	{
		if(inBounds(x, y) == false)
		{
			return null;
		}
		else if (pieces[x][y] == null)
		{
			return null;
		}
		else if(pieces[x][y] != null)
		{
			return pieces[x][y];
		}
		return null;
	}
	public void place(Piece p, int x, int y)
	{
		//Please work.
		if (p != null && inBounds(x, y) == true)
		{
			if(p.isFire() == true && captured == 0)
			{
				firePieces++;
			}
			else if(p.isFire() == false && captured == 0)
			{
				waterPieces++;
			}
			pieces[x][y] = p;
		}
	}
	private boolean inBounds(int x, int y)
	{
		if (x < 8 && y  < 8 && x >= 0 && y >= 0)
		{
			return true;
		}
		return false;
	}
	public Piece remove(int x, int y)
	{
		if(inBounds(x, y) == true)
		{
			if(pieces[x][y] == null)
			{
				System.out.println("No piece to remove!");
			}
			if(pieces[x][y] != null)
			{
				if(pieces[x][y].isFire() == true)
				{
					firePieces = firePieces - 1;
				}
				else if(pieces[x][y].isFire() == false)
				{
					waterPieces = waterPieces - 1;
				}
				removedPiece = pieces[x][y];
				pieces[x][y] = null;
				return removedPiece;
			}
		}
		return null;
	}
	public void select(int x, int y)
	{
		// //Havent selected a piece, now selecting a piece.
		// if(pieces[x][y] == null && hasSelected == 0)
		// {
		// }
		//Selected a piece, moving it, and captured a piece.
		if (pieces[x][y] == null && hasSelected == 1)
		{
			pieces[tempPieceX][tempPieceY].move(x, y);
			hasMoved = 1;
			tempPieceX = x;
			tempPieceY = y;
			tempPiece = pieces[tempPieceX][tempPieceY];
			if(tempPiece.hasCaptured() == true)
			{
				if (tempPiece.isBomb() == true)
				{
					captured = 1;
					bombExploded = 1;
					remove(tempPieceX, tempPieceY);
				}
				else
				{
					captured = 1;
				}
			}
		}
		//Have not selected a piece yet.
		else if (pieces[x][y] != null)
		{
			tempPiece = pieces[x][y];
			tempPieceX = x;
			tempPieceY = y;
			hasSelected = 1;
			if (pieces[tempPieceX][tempPieceY].hasCaptured() == true)
			{
				captured = 1;
			}
		}
	}
	public boolean canSelect(int x, int y)
	{
		//* A square with a piece may be selected if it is the
		//* corresponding player's turn and one of the following
		//* is true: the player has not selected a piece yet,
		//* the player has selected a piece, but did not move it.
		
		//Square with a piece.
		if(pieces[x][y] != null && playersTurn == pieces[x][y].side() && inBounds(x, y) == true && captured == 1)
		{
			if (hasSelected == 1 && hasMoved == 1)
			{
				return false;
			}
		}
		if (pieces[x][y] != null && playersTurn == pieces[x][y].side() && inBounds(x, y) == true && captured == 0)
		{
			if(hasSelected == 0)
			{
				hasMoved = 0;
				hasSelected = 1;
				return true;
			}
			if (hasSelected == 1 && hasMoved == 0)
			{
				return true;
			}
			return false;
		}

		//* An empty square may be selected if one of the following is true:
		//* During this turn, the player has selcted a Piece which hasn't
		//* moved yet and is selecting an empty spot which is a valid move
		//* for the previously selected Piece.
		//* During this turn, the player has selected a Piece, captured, and
		//* has selected another valid capture destination. When performing
		//* multi-captures, you should only select the active piece once;
		//* all other sections should be valid destination points.

		//Square without a piece.
		if(pieces[x][y] == null && inBounds(x, y) == true)
		{
			//Has selected a piece, haven't moved it yet, and hasn't captured yet.
			if (hasSelected == 1 && hasMoved == 0 && captured == 0 && bombExploded != 1)
			{
				//Fire's move.
				if(playersTurn == 0 || pieces[tempPieceX][tempPieceY].isKing() == true)
				{
					//Up/left movement.
					if (x + 1 == tempPieceX && y - 1 == tempPieceY)
					{
						hasMoved = 1;
						return true;
					}
					//Up/right movement.
					if (x - 1 == tempPieceX && y - 1 == tempPieceY)
					{
						hasMoved = 1;
						return true;
					}
					//Up/left exists a piece.
					//There has to be a piece between the two spots and has to be the opponent's piece.
					if (x + 1 > 0 && x + 1 < 8 && y - 1 > 0 && y - 1 < 8)
					{
						if (pieces[x + 1][y - 1] != null && pieces[x][y] == null && pieces[x + 1][y - 1].side() != playersTurn && tempPieceX > x)
						{
							hasMoved = 1;
							return true;
						}
					}
					//Up/right exists a piece.
					//There has to be a piece between the two spots and has to be the opponent's piece.
					if (x - 1 > 0 && x - 1 < 8 && y - 1 > 0 && y - 1 < 8)
					{
						if(pieces[x - 1][y - 1] != null && pieces[x][y] == null && pieces[x - 1][y - 1].side() != playersTurn && tempPieceX < x)
						{
							hasMoved = 1;
							return true;
						}
					}
				}
				//Water's move.
				if(playersTurn == 1 || pieces[tempPieceX][tempPieceY].isKing() == true)
				{
					//Down/left movement.
					if (x + 1 == tempPieceX && y + 1 == tempPieceY)
					{
						hasMoved = 1;
						return true;
					}
					//Down/right movement.
					if (x - 1 == tempPieceX && y + 1 == tempPieceY)
					{
						hasMoved = 1;
						return true;
					}
					//Down/right exists a piece.
					//There has to be a piece between the two spots and has to be the opponent's piece.
					if (x - 1 > 0 && x - 1 < 8 && y + 1 > 0 && y + 1 < 8)
					{
						if(pieces[x - 1][y + 1] != null && pieces[x][y] == null && pieces[x - 1][y + 1].side() != playersTurn && tempPieceX < x)
						{
							hasMoved = 1;
							return true;
						}
					}
					//Down/left exists a piece.
					//There has to be a piece between the two spots and has to be the opponent's piece.
					if(x + 1 > 0 && x + 1 < 8 && y + 1 > 0 && y + 1 < 8)
					{
						if (pieces[x + 1][y + 1] != null && pieces[x][y] == null && pieces[x + 1][y + 1].side() != playersTurn && tempPieceX > x)
						{
							hasMoved = 1;
							return true;
						}
					}
					return false;
				}
			}
			//Multi-jumping.
			if (hasSelected == 1 && hasMoved == 1 && captured == 1 && bombExploded != 1)
			{
				//Checking the 4 possible jumps
				//Top left corner
				if (validCapture(tempPieceX, tempPieceY, x, y) == true)
				{
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}
	public boolean canEndTurn()
	{
		if (hasMoved == 1)
		{
			return true;
		}
		else if(captured == 1)
		{
			return true;
		}
		else if(hasMoved == 1 && captured == 1)
		{
			return true;
		}
		else if (waterPieces == 0 || firePieces == 0)
		{
			return true;
		}
		return false;
	}
	public void endTurn()
	{
		if (playersTurn == 0)
		{
			if (pieces[tempPieceX][tempPieceY] != null)
			{
				pieces[tempPieceX][tempPieceY].doneCapturing();
			}
			if (waterPieces == 0)
			{
				winner();
			}
			playersTurn = 1;
			System.out.println("Water's turn!");
		}
		else if (playersTurn == 1)
		{
			if (pieces[tempPieceX][tempPieceY] != null)
			{
				pieces[tempPieceX][tempPieceY].doneCapturing();
			}
			if (firePieces == 0)
			{
				winner();
			}
			playersTurn = 0;
			System.out.println("Fire's turn!");
		}
		hasMoved = 0;
		hasSelected = 0;
		captured = 0;
		bombExploded = 0;
	}
	public String winner()
	{
		if (firePieces == 0 && waterPieces != 0)
		{
			System.out.println("WATER WINS");
			return "Water";
		}
		else if (firePieces != 0 && waterPieces == 0)
		{
			System.out.println("FIRE WINS");
			return "Fire";
		}
		else if (firePieces == 0 && waterPieces == 0)
		{
			System.out.println("NO ONE WINS");
			return "No one";
		}
		return null;
	}

	private void countBoard()
	{
		waterPieces = 0;
		firePieces = 0;
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8;j ++)
			{
				if (pieces[i][j].isFire() == true)
				{
					firePieces++;
				}
				else
				{
					waterPieces++;
				}
			}
		}
	}
	private boolean validCapture(int oldx, int oldy, int x, int y)
	{
		// is this move sane?
		if(inBounds(x, y) == true)
		{	
			if ((Math.abs(oldx - x) == 2) && (Math.abs(oldy - y) == 2))
			{
				int checkx = (oldx + x) / 2;
				int checky = (oldy + y) / 2;
				if (pieces[checkx][checky] != null)
				{
					if (pieces[checkx][checky].side() != playersTurn) {
						if (pieces[oldx][oldy].isKing() == true)
						{
							return true;
						}
						else
						{
							if(pieces[oldx][oldy].side() == 0)
							{
								if (y - oldy == 2)
								{
									return true;
								}
							}
							else
							{
								if (y - oldy == -2)
								{
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
}