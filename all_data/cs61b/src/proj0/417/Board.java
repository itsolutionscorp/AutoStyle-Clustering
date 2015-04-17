public class Board
{
	private Piece[][] piece_array;
	private Piece selected_piece;
	private boolean player_turn;
	private boolean piece_moved;
	private Integer[] selected_square;

	public static void main(String[] args)
	{
		Board board = new Board(false);
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		board.drawBoard();
		board.drawPieces();
	}

	private void drawBoard()
	{
		for (int i = 0; i < 8; i++) 
        {
           	for (int j = 0; j < 8; j++) 
           	{
               	if ((i + j) % 2 == 0)
               	{
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);                	
                	}
                	else
                	{
               			StdDrawPlus.setPenColor(StdDrawPlus.RED);
               		}
                		/*if (board.selected_square[0] != null)
                		{
                			if (board.selected_square[0] == i && board.selected_square[1] == j)
                			{
                				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                			}
                		}*/                  
               		StdDrawPlus.filledSquare(i + .5, j + .5, .5);
               		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
       	   		}
        	}
       		StdDrawPlus.show(100);
	}

	private void drawPieces()
	{
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (piece_array[i][j] != null)
				{
					if (piece_array[i][j].isFire())
					{
						if (piece_array[i][j].isBomb())
						{
							if (piece_array[i][j].isKing())
							{
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png", 1, 1);	
							}
							else
							{
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
							}
						}	
						else if (piece_array[i][j].isShield())
						{
							if (piece_array[i][j].isKing())
							{
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1, 1);
							}
							else
							{
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
							}
						}
						else
						{
							if (piece_array[i][j].isKing())
							{
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png", 1, 1);
							}
							else
							{
								StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
							}
						}
					}
					else
					{	
					if (piece_array[i][j].isBomb())
					{
						if (piece_array[i][j].isKing())
						{
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png", 1, 1);	
						}
						else
						{
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
						}
					}	
					else if (piece_array[i][j].isShield())
					{
						if (piece_array[i][j].isKing())
						{
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png", 1, 1);
						}
						else
						{
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
						}
					}
					else
					{
						if (piece_array[i][j].isKing())
						{
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png", 1, 1);
						}
						else
						{
							StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
						}
					}			    
				}

			}
		}
	}
}
	

	public Board(boolean shouldBeEmpty)
	{
		piece_array = new Piece[8][8];
		selected_piece = null;
		piece_moved = false;
		selected_square = new Integer[2];
		player_turn = true;		
		if (shouldBeEmpty)
		{
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					piece_array[i][j] = null;
				}
			}
		}
		else
		{
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					if (j == 0 && (i + j) % 2 == 0)
					{
						piece_array[i][j] = new Piece(true, this, i, j, "pawn");
					}
					else if (j == 1 && (i + j) % 2 == 0)
					{
						piece_array[i][j] = new Piece(true, this, i, j, "shield");
					}
					else if (j == 2 && (i + j) % 2 == 0)
					{
						piece_array[i][j] = new Piece(true, this, i, j, "bomb");
					}
					else if (j == 5 && (i + j) % 2 == 0)
					{
						piece_array[i][j] = new Piece(false, this, i, j, "bomb");
					}
					else if (j == 6 && (i + j) % 2 == 0)
					{
						piece_array[i][j] = new Piece(false, this, i, j, "shield");
					}
					else if (j == 7 && (i + j) % 2 == 0)
					{
						piece_array[i][j] = new Piece(false, this, i, j, "pawn");
					}
					else
					{
						piece_array[i][j] = null;
					}
				}
			}	
		}
	}

	public Piece pieceAt(int x, int y)
	{
		if (x < 8 && x >= 0 && y < 8 && y >= 0)
		{
			return piece_array[x][y];
		}
		else
		{
			return null;
		}
	}

	public void place(Piece p, int x, int y)
	{
		if (x < 8 && x >= 0 && y < 8 && y >= 0)
		{	
			if (p != null)
			{
				for (int i = 0; i < 8; i++)
				{
					for (int j = 0; j < 8; j++)
					{
						if (piece_array[i][j] == p)
						{
							remove(i, j);
						}
					}
				}
				piece_array[x][y] = p;
			}
		}
	}		

	public Piece remove(int x, int y)
	{
		if (x < 8 && x >= 0 && y < 8 && y >= 0)
		{
			Piece var = piece_array[x][y];
			if (piece_array[x][y] != null)
			{
				piece_array[x][y] = null;
				return var;
			}
			else
			{
				System.out.println("There is no piece at these coordinates!");
				return null;				
			}
		}
		else
		{
			System.out.println("These coordinates are out of bounds!");
			return null;
		}
	}

	public String winner()
	{
		int count_fire = 0;
		int count_water = 0;
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j++)
			{
				if (piece_array[i][j] != null)
				{
					if (piece_array[i][j].isFire())
					{
						count_fire += 1;
					}
					else
					{
						count_water += 1;
					}
				}
			}
		}
		if (count_water == 0 && count_fire == 0)
		{
			return "No one";
		}	
		else if (count_water == 0)
		{
			return "Fire";
		}	
		else if (count_fire == 0)
		{
			return "Water";
		}
		else
		{
			return null;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf)
	{
		int x_diff = xf - xi;
		int y_diff = yf - yi;

		if (selected_piece.hasCaptured() == true)
		{
			if (selected_piece.isFire() == true)
			{
				if (y_diff == 2)
				{
					if ((x_diff == -2) && (pieceAt(xf + 1, yf - 1) != null) && (!pieceAt(xf + 1, yf - 1).isFire()))
					{
						return true;
					}
					else if ((x_diff == 2) && (pieceAt(xf - 1, yf - 1) != null) && (!pieceAt(xf - 1, yf - 1).isFire()))
					{
						return true;
					}
				}
				if (selected_piece.isKing())
				{
					if (y_diff == -2)
					{
						if ((x_diff == -2) && (pieceAt(xf - 1, yf + 1) != null) && (!pieceAt(xf - 1, yf + 1).isFire()))
						{
							return true;
						}
						else if ((x_diff == 2) && (pieceAt(xf + 1, yf + 1) != null) && (!pieceAt(xf + 1, yf + 1).isFire()))
						{
							return true;
						}
					}
				}
				return false;
			}
			else
			{
				if (y_diff == -2)
				{
					if ((x_diff == -2) && (pieceAt(xf + 1, yf + 1) != null) && (pieceAt(xf + 1, yf + 1).isFire()))
					{
						return true;
					}
					else if ((x_diff == 2) && (pieceAt(xf - 1, yf + 1) != null) && (pieceAt(xf - 1, yf + 1).isFire()))
					{
						return true;
					}
				}
				else if (selected_piece.isKing())
				{
					if (y_diff == 2)
					{
						if ((x_diff == -2) && (pieceAt(xf - 1, yf - 1) != null) && (pieceAt(xf - 1, yf - 1).isFire()))
						{
							return true;
						}
						else if ((x_diff == 2) && (pieceAt(xf + 1, yf - 1) != null) && (pieceAt(xf + 1, yf - 1).isFire()))
						{
							return true;
						}
					}
				}	
			}
			return false;				
		}

		if (selected_piece.isFire())
		{
			if (((x_diff == 1) || (x_diff == -1)) && (y_diff == 1) && !piece_moved && selected_piece.hasCaptured() == false)
			{
				return true;
			}
			if (y_diff == 2)
			{
				if ((x_diff == -2) && (pieceAt(xf + 1, yf - 1) != null) && (!pieceAt(xf + 1, yf - 1).isFire()))
				{
					return true;
				}
				else if ((x_diff == 2) && (pieceAt(xf - 1, yf - 1) != null) && (!pieceAt(xf - 1, yf - 1).isFire()))
				{
					return true;
				}
			}
			if (selected_piece.isKing())
			{
				if (((x_diff == 1) || (x_diff == -1)) && ((y_diff == 1) || (y_diff == -1)) && !piece_moved && selected_piece.hasCaptured() == false)
				{
					return true;
				}
				else if (y_diff == -2)
				{
					if ((x_diff == -2) && (pieceAt(xf - 1, yf + 1) != null) && (!pieceAt(xf - 1, yf + 1).isFire()))
					{
						return true;
					}
					else if ((x_diff == 2) && (pieceAt(xf + 1, yf + 1) != null) && (!pieceAt(xf + 1, yf + 1).isFire()))
					{
						return true;
					}
				}
			}
		}
	else
	{
		if (((x_diff == 1) || (x_diff == -1)) && (y_diff == -1) && !piece_moved && selected_piece.hasCaptured() == false)
			{
				return true;
			}
		if (y_diff == -2)
		{
			if ((x_diff == -2) && (pieceAt(xf + 1, yf + 1) != null) && (pieceAt(xf + 1, yf + 1).isFire()))
			{
				return true;
			}
			else if ((x_diff == 2) && (pieceAt(xf - 1, yf + 1) != null) && (pieceAt(xf - 1, yf + 1).isFire()))
			{
				return true;
			}
		}
		if (selected_piece.isKing())
		{
			if (((x_diff == 1) || (x_diff == -1)) && ((y_diff == -1) || (y_diff == 1)) && !piece_moved && selected_piece.hasCaptured() == false)
			{
				return true;
			}
			else if (y_diff == 2)
			{
				if ((x_diff == -2) && (pieceAt(xf - 1, yf - 1) != null) && (pieceAt(xf - 1, yf - 1).isFire()))
				{
					return true;
				}
				else if ((x_diff == 2) && (pieceAt(xf + 1, yf - 1) != null) && (pieceAt(xf + 1, yf - 1).isFire()))
				{
					return true;
				}
		}
		}
	}
	return false;
	}

	public boolean canSelect(int x, int y)
	{
		if (pieceAt(x, y) != null)
		{
			if (pieceAt(x, y).isFire() == player_turn)
			{
				if (selected_piece != null && !piece_moved )
				{
					return true;
				}
				if (selected_piece == null)
				{
					return true;
				}
			}				
			else
			{
				return false;
			}
		}
		else
		{
			if (selected_piece != null && selected_piece.hasCaptured() && validMove(selected_square[0], selected_square[1], x, y) && piece_moved == false)
			{
				return true;
			}
			else if (selected_piece != null && !piece_moved && validMove(selected_square[0], selected_square[1], x, y))
			{
				return true;
			}
		}	
		return false;
	}

	public void select(int x, int y)
	{
		if (pieceAt(x, y) != null)
		{
			selected_piece = pieceAt(x, y);
			selected_square[0] = x;
			selected_square[1] = y;
		}
		else if (selected_piece != null)
		{
			// (if !hasCaptured)
			// {

			// }
			place(selected_piece, x, y);
			piece_moved = true;
			selected_square[0] = x;
			selected_square[1] = y;
			selected_piece.move(x, y);
		}
	}

	public boolean canEndTurn()
	{
		if (selected_piece != null && selected_piece.hasCaptured())
		{
			return true;
		}
		else
		{
			return piece_moved;
		}

		// if (piece_moved == true || selected_piece == null)
		// {
		// 	return true;
		// }
		// else if (selected_piece != null)
		// {
		// 	return false;
		// }
		// return (piece_moved || selected_piece.hasCaptured());
	}

	public void endTurn()
	{
		if (canEndTurn())
		{
			piece_moved = false;
			selected_piece.doneCapturing();
			selected_piece = null;
			selected_square[0] = null;
			selected_square[1] = null;
			player_turn = !player_turn;
		}
	}
}