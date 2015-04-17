public class Board
{
	private static Piece[][] pieces;
	private boolean fireTurn = true;
	private Piece selected = null;
	private boolean moved = false;
	private static int xi = -1;
	private static int yi = -1;
	private static int selectX = -1;
	private static int selectY = -1;
	private static int scale = 8;
	private static boolean goAgain = false;
	private static boolean bomb_plosion = false;

	public Board(boolean shouldBeEmpty)
	{
		if (shouldBeEmpty == true)
		{
			pieces = new Piece[scale][scale];
        }

		else
		{
			pieces = new Piece[][]{{new Piece(true, this, 0, 0, "pawn"), null,                                  new Piece(true, this, 0, 2, "bomb"), null, null, null,                                 new Piece(false, this, 0, 6, "shield"), null},
		                           {null,                                new Piece(true, this, 1, 1, "shield"), null,                                null, null, new Piece(false, this, 1, 5, "bomb"), null,                                   new Piece(false, this, 6, 7, "pawn")},
		                           {new Piece(true, this, 2, 0, "pawn"), null,                                  new Piece(true, this, 2, 2, "bomb"), null, null, null,                                 new Piece(false, this, 2, 6, "shield"), null},
		                           {null,                                new Piece(true, this, 3, 1, "shield"), null,                                null, null, new Piece(false, this, 3, 5, "bomb"), null,                                   new Piece(false, this, 6, 7, "pawn")},
		                           {new Piece(true, this, 4, 0, "pawn"), null,                                  new Piece(true, this, 4, 2, "bomb"), null, null, null,                                 new Piece(false, this, 4, 6, "shield"), null},
		                           {null,                                new Piece(true, this, 5, 1, "shield"), null,                                null, null, new Piece(false, this, 5, 5, "bomb"), null,                                   new Piece(false, this, 6, 5, "pawn")},
		                           {new Piece(true, this, 0, 6, "pawn"), null,                                  new Piece(true, this, 6, 2, "bomb"), null, null, null,                                 new Piece(false, this, 6, 6, "shield"), null},
		                           {null,                                new Piece(true, this, 7, 1, "shield"), null,                                null, null, new Piece(false, this, 7, 5, "bomb"), null,                                   new Piece(false, this, 6, 7, "pawn")}};
		}

	}

	private static void drawBoard()
	{
		for (int i = 0; i < scale; i++) 
		{
           	for (int j = 0; j < scale; j++) 
            {
               	if ((i + j) % 2 == 0) 
               	{
               		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
               	}
               	else 
               	{
               		StdDrawPlus.setPenColor(StdDrawPlus.RED);
               	}
               	if ((j == xi && i == yi) || (j == selectX && i == selectY))
               	{
               		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
               	}
                StdDrawPlus.filledSquare(j + .5, i + .5, .5);
            }
        }
	}

	private static void drawPieces(Piece[][] array)
	{
		for (int j = 0; j < scale; j++)
		{
           	for (int i = 0; i < scale; i++) 
            {
           		if (pieces[i][j] != null)
           		{
           			if (pieces[i][j].isFire()) // fire
           			{
           				if (pieces[i][j].isKing()) //king
            			{
            				if (pieces[i][j].isBomb()) //bomb
            				{
            					StdDrawPlus.picture(i + .5, j + .5,"img/bomb-fire-crowned.png" , 1, 1);
           					}

            				else if (pieces[i][j].isShield()) //shield
            				{
            					StdDrawPlus.picture(i + .5, j + .5,"img/shield-fire-crowned.png" , 1, 1);
            				}

            				else //pawn
           					{
           						StdDrawPlus.picture(i + .5, j + .5,"img/pawn-fire-crowned.png" , 1, 1);
           					}
            			}

            			else //non king.
            			{
           					if (pieces[i][j].isBomb()) //bomb
            				{
           						StdDrawPlus.picture(i + .5, j + .5,"img/bomb-fire.png" , 1, 1);
           					}

           					else if (pieces[i][j].isShield()) //shield
           					{
            					StdDrawPlus.picture(i + .5, j + .5,"img/shield-fire.png" , 1, 1);
            				}

            				else //pawn
            				{
            					StdDrawPlus.picture(i + .5, j + .5,"img/pawn-fire.png" , 1, 1);
           					}
           				}
            		}

            		else //not fire
            		{
            			if (pieces[i][j].isKing()) //king
           				{
            				if (pieces[i][j].isBomb()) //bomb
            				{
            					StdDrawPlus.picture(i + .5, j + .5,"img/bomb-water-crowned.png" , 1, 1);
            				}

            				else if (pieces[i][j].isShield()) //shield
            				{
            					StdDrawPlus.picture(i + .5, j + .5,"img/shield-water-crowned.png" , 1, 1);
            				}

            				else //pawn
           					{
            					StdDrawPlus.picture(i + .5, j + .5,"img/pawn-water-crowned.png" , 1, 1);
            				}
            			}

            			else // non king
            			{
            				if (pieces[i][j].isBomb()) //bomb
            				{
            					StdDrawPlus.picture(i + .5, j + .5,"img/bomb-water.png" , 1, 1);
           					}

            				else if (pieces[i][j].isShield()) //shield
            				{
            					StdDrawPlus.picture(i + .5, j + .5,"img/shield-water.png" , 1, 1);
            				}

            				else //pawn
           					{
           						StdDrawPlus.picture(i + .5, j + .5,"img/pawn-water.png" , 1, 1);
           					}
           				}
            		}
            	}
            }
        }
	}

	public Piece pieceAt(int x, int y)
	{
		if (x < 0 || x > 7 || y < 0 || y > 7)
			return null;
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y)
	{
		if (pieces[x][y] != null && !moved)// contains piece
		{
			if (fireTurn)
			{
				if (pieces[x][y].isFire())
				{
					xi = x;
					yi = y;
				}
				return pieces[x][y].isFire();
			}

			else if (!fireTurn)
			{
				if (!pieces[x][y].isFire())
				{
					xi = x;
					yi = y;
				}
				return !pieces[x][y].isFire();
			}

			else
			{
				return false;
			}
		}

		else // empty square
		{
			
			if (selected != null && validMove(xi,yi,x,y,selected) && pieces[x][y] == null)
			{
				selectX = x;
				selectY = y;
				return true;
			}

			else
			{
				return false;	
			}
		}
	}

	private boolean validMove(int xpos, int ypos, int xf, int yf, Piece p)
	{
		if (xf < 0 || xf > 7 || yf < 0 || yf > 7 ||
		p == null || (moved && !p.hasCaptured()) || (!goAgain && moved))
		{	
			return false;
		}

		else
		{
			if (p.isKing())
			{
				if (((xf + 1) == xpos || (xf - 1) == xpos) && ((yf + 1) == ypos || (yf - 1) == ypos))
				{
					return true;
				}
				
				else if (((xf + 2) == xpos || (xf - 2) == xpos) && 
						((yf + 2) == xpos || (yf - 2) == ypos) && 
						pieces[((xf - xpos) / 2) + xpos][((yf - ypos) / 2) + ypos] != null && 
						pieces[((xf - xpos) / 2) + xpos][((yf - ypos) / 2) + ypos].isFire() != p.isFire())
				{
					return true; 
				}
				else
				{
					return false;
				}
			}

			else if (p.isFire() && fireTurn)
			{
				if ((yf - 1) == ypos && ((xpos + 1) == xf || (xpos - 1) == xf))
				{
					return true;
				}
				
				else if (((yf - 2) == ypos) && 
						((xpos - 2) == xf || (xpos + 2) == xf) && 
						pieces[((xf - xpos) / 2) + xpos][ypos + 1] != null &&
						pieces[((xf - xpos) / 2) + xpos][ypos + 1].isFire() != p.isFire())
				{
					return true;
				}
				else
				{
					return false;
				}
			}

			else
			{
				if ((yf + 1) == ypos && ((xpos + 1) == xf || (xpos - 1) == xf))
				{
					return true;
				}
				
				else if (((yf + 2) == ypos) && 
						((xpos - 2) == xf || (xpos + 2) == xf) && 
						pieces[((xf - xpos) / 2) + xpos][ypos - 1] != null &&
						pieces[((xf - xpos) / 2) + xpos][ypos - 1].isFire() != p.isFire())
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
	}

	public void select(int x, int y)
	{
		if (pieces[x][y] == null && selected != null) //move here!
		{
			selectX = x;
			selectY = y;
			this.place(selected, x, y);
		}

		else 
		{
			selected = pieces[x][y];
			xi = x;
			yi = y;
		}
	}

	public void place(Piece p, int x, int y)
	{
		if (x < 0 || x > 7 || y < 0 || y > 7 || p == null)
			return;
		pieces[x][y] = p;
		p.move(x, y);
		this.remove(xi,yi);
		goAgain = false;

		if (Math.abs(selectX - xi) == 2)
		{
			goAgain = true;
			this.remove((((selectX - xi) / 2) + xi),(((selectY - yi) / 2) + yi));
			if(p.isBomb())
			{
				this.remove(x,y);
				for (int m = -1; m < 2; m++)
				{
					for (int n = -1; n < 2; n++)
					{
						if ((x + m) >= 0 && (x + m) <= 7 && (y + n) >= 0 && (y + n) <= 7 && pieces[x + m][y + n] != null && (pieces[x + m][y + n].isFire() != p.isFire()) && !pieces[x + m][y + n].isShield())
						{
							this.remove(x + m, y + n);
						}
					}
				}
				selected = null;
				bomb_plosion = true;
			}
		}
		moved = true;
		xi = selectX;
		yi = selectY;
		selectX = -1;
		selectY = -1;

	}

	public Piece remove(int x, int y)
	{
		if (x < 0 || x > 7 || y < 0 || y > 7)
		{
			System.out.println("Error: Invalid Location");
			return null;
		}

		else if (pieces[x][y] == null)
		{
			System.out.println("Error: No Piece At Location");
			return null;
		}

		else
		{
			Piece temp = pieces[x][y];
			pieces[x][y] = null;
			return temp;
		}
	}

	public boolean canEndTurn()
	{
		return (bomb_plosion || (selected != null && (selected.hasCaptured() || moved)));
	}

	public void endTurn()
	{
		fireTurn = !fireTurn;
		if (selected != null)
			this.selected.doneCapturing();
		this.selected = null;
		this.moved = false;
		this.xi = -1;
		this.yi = -1;
		this.selectX = -1;
		this.selectY = -1;
		bomb_plosion = false;
		
	}

	public String winner()
	{	
		int wCount = 0;
		int fCount = 0;
		for (int i = 0; i < scale; i++) 
			{
            	for (int j = 0; j < scale; j++) 
            	{
            		if (pieces[i][j] != null)
            		{
            			if (pieces[i][j].isFire())
            				fCount++;
            			else
            				wCount++;
            		}
            	}
            }

        if (wCount == 0 && fCount == 0)
        	return "No one";
        else if (fCount == 0)
        	return "Water";
        else if (wCount == 0)
        	return "Fire";
        else
        	return null;
	}

	public static void main(String[] args)
	{
        StdDrawPlus.setXscale(0, scale);
        StdDrawPlus.setYscale(0, scale);
        Board b = new Board(false);

        while(true)
        {
        	drawBoard();
            drawPieces(pieces);

            if (StdDrawPlus.mousePressed())
            {
                int xpos = (int) StdDrawPlus.mouseX();
                int ypos = (int) StdDrawPlus.mouseY();

                if (b.canSelect(xpos,ypos))
            	{
            		b.select(xpos,ypos);
            	}
            }
            	
            if (b.canEndTurn() && StdDrawPlus.isSpacePressed())
           	{
           		b.endTurn();
           	}

           	if (b.winner() != null)
            {
            	return;
           	}
                        
            StdDrawPlus.show(100);
        }
	}
}