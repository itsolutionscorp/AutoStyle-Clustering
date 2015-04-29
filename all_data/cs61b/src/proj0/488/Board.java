public class Board
{
	/* Player 0 is Fire. Player 1 is Water
	 * Pointless line of comment so I can submit
	 * 
	 */
	private Piece[][] pieces;
	private int playersTurn;
	private boolean playerMoved;
	private Piece selected;
	private boolean captured;
	private int selectedX;
	private int selectedY;

	
	private void drawPiece(boolean endTurn)
	{
		Piece p;
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				p = pieces[i][j];

				if((i + j) % 2 == 0)
				{
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        			StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        		}

				if(p == null)
				{
					
					continue;
				}
				if(!endTurn && (p.hasCaptured() || p == selected))
				{
					StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		        	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				}
				else
				{
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        			StdDrawPlus.filledSquare(i + .5, j + .5, .5);
        		}

				if(p.isFire())
				{
					if(p.isKing())
					{
						if(p.isBomb())
						{
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
						}
						else if(p.isShield())
						{
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
						}
						else
						{
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
						}
					}
					else
					{
						if(p.isBomb())
						{
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
						}
						else if(p.isShield())
						{
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
						}
						else
						{
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
						}
					}
				}
				else
				{
					if(p.isKing())
					{
						if(p.isBomb())
						{
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
						}
						else if(p.isShield())
						{
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
						}
						else
						{
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
						}
					}
					else
					{
						if(p.isBomb())
						{
							StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
						}
						else if(p.isShield())
						{
							StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
						}
						else
						{
							StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
						}
					}
				}
			}
		}
	}

	public Board(boolean shouldBeEmpty)
	{
		pieces = new Piece[8][8];
		playersTurn = 0;
		playerMoved = false;
		captured = false;

		for (int i = 0; i < 8; i++) 
		{
	        for (int j = 0; j < 8; j++) 
	        {
	            if ((i + j) % 2 == 0) 
	            {
		           	if(!shouldBeEmpty)
		            {
		            	switch(j)
		            	{
		            		case 0:
		            			//StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
		            			pieces[i][j] = new Piece(true, this, i, j, "pawn");
		            			break;
		            		case 1:
		            			//StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
		            			pieces[i][j] = new Piece(true, this, i, j, "shield");
		            			break;
		            		case 2:
		            			//StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
		            			pieces[i][j] = new Piece(true, this, i, j, "bomb");
		            			break;
		            		case 7:
		            			//StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
		            			pieces[i][j] = new Piece(false, this, i, j, "pawn");
		            			break;
		            		case 6:
		            			//StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
		            			pieces[i][j] = new Piece(false, this, i, j, "shield");
		            			break;
		            		case 5:
		            			//StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
		            			pieces[i][j] = new Piece(false, this, i, j, "bomb");
		            			break;
		            		default:
		            			break;
		            	}
		            }
	            }
	        }
	    }
	}

	public Piece pieceAt(int x, int y)
	{
		if(outBounds(x,y))
		{
			return null;
		}
		return pieces[x][y];
	}

	public boolean canSelect(int x, int y)
	{
		if(outBounds(x,y))
		{
			return false;
		}
		else if(pieces[x][y] == null && selected != null)
		{
			if(validMove(selectedX, selectedY, x, y) && (captured || !playerMoved))
			{
				return true;
			}
			return false;
		}
		else
		{
			if(pieces[x][y] == null)
			{
				return false;
			}
			else if(playerMoved)
			{
				return false;
			}
			else if(selected == pieces[x][y])
			{
				return true;
			}
			
			else if((!captured || !playerMoved) && playersTurn == 0 && pieces[x][y].isFire())
			{
				return true;
			}
			else if((!captured || !playerMoved) && playersTurn == 1 && !pieces[x][y].isFire())
			{
				return true;
			}
			return false;
		}
	}

	private boolean validMove(int xi, int yi, int xf, int yf)
	{
		if(xi == xf || yi == yf) 
		{
			return false;
		}
		else if(captured && Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1)
		{
			return false;
		}
		else if(selected.isFire())
		{
			if((yf == yi + 1) && ((xf == xi - 1) || (xf == xi + 1)))
			{
				return true;
			}
			else if(yf == yi + 2)
			{
				if(xf == xi - 2)
				{
					if(pieces[xi - 1][yi + 1] == null)
					{
						return false;
					}
					return !pieces[xi - 1][yi + 1].isFire();
				}
				else if(xf == xi + 2)
				{
					if(pieces[xi + 1][yi + 1] == null)
					{
						return false;
					}
					return !pieces[xi + 1][yi + 1].isFire();
				}
				else
				{
					return false;
				}
			}
			else if(selected.isKing())
			{
				if((yf == yi - 1) && ((xf == xi - 1) || (xf == xi + 1)))
				{
					return true;
				}
				else if(yf == yi - 2)
				{
					if(xf == xi - 2)
					{
						if(pieces[xi - 1][yi - 1] == null)
						{
							return false;
						}
						return !pieces[xi - 1][yi - 1].isFire();
					}
					else if(xf == xi + 2)
					{
						if(pieces[xi + 1][yi - 1] == null)
						{
							return false;
						}
						return !pieces[xi + 1][yi - 1].isFire();
					}
					else
					{
						return false;
					}
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			if((yf == yi - 1) && ((xf == xi - 1) || (xf == xi + 1)))
			{
				return true;
			}
			else if(yf == yi - 2)
			{
				if(xf == xi - 2)
				{
					if(pieces[xi - 1][yi - 1] == null)
					{
						return false;
					}
					return pieces[xi - 1][yi - 1].isFire();
				}
				else if(xf == xi + 2)
				{
					if(pieces[xi + 1][yi - 1] == null)
					{
						return false;
					}
					return pieces[xi + 1][yi - 1].isFire();
				}
				else
				{
					return false;
				}
			}
			else if(selected.isKing())
			{
				if((yf == yi + 1) && ((xf == xi - 1) || (xf == xi + 1)))
				{
					return true;
				}
				else if(yf == yi + 2)
				{
					if(xf == xi - 2)
					{
						if(pieces[xi - 1][yi + 1] == null)
						{
							return false;
						}
						return pieces[xi - 1][yi + 1].isFire();
					}
					else if(xf == xi + 2)
					{
						if(pieces[xi + 1][yi + 1] == null)
						{
							return false;
						}
						return pieces[xi + 1][yi + 1].isFire();
					}
					else
					{
						return false;
					}
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
	}


	//This determines how to kill
	//Finished the iffy portions
	public void select(int x, int y)
	{
		if(pieces[x][y] == null && selected != null)
		{
			//Check if it is my own piece
			if(Math.abs(selectedX - x) != 1 && Math.abs(selectedY - y) != 1)
			{
				if(pieces[(int) ((selectedX + x)/2)][(int) ((selectedY + y) / 2)] != null && (selected.isFire() == pieces[(int) ((selectedX + x)/2)][(int) ((selectedY + y) / 2)].isFire()))
				{
					return;
				}
			}
			//StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			//StdDrawPlus.filledSquare(selectedX + .5, selectedY + .5, .5);
			selected.move(x, y);
			playerMoved = true;
			if(selected.hasCaptured())
			{
				remove((int) ((selectedX + x) / 2), (int) ((selectedY + y) / 2));
				captured = true;
				if(selected.isBomb())
				{
					if(x == 7)
					{
						if(y == 0)
						{
							if(pieces[6][1] != null && !pieces[6][1].isShield())
							{
								remove(6, 1);
							}
						}
						else if(y == 7)
						{
							if(pieces[6][6] != null && !pieces[6][6].isShield())
							{
								remove(6, 6);
							}
						}
						else
						{
							if(pieces[x - 1][y + 1] != null && !pieces[x - 1][y + 1].isShield())
							{
								remove(x - 1, y + 1);
							}
							if(pieces[x - 1][y - 1] != null && !pieces[x - 1][y - 1].isShield())
							{
								remove(x - 1, y - 1);
							}
						}
					}
					else if(x == 0)
					{
						if(y == 0)
						{
							if(pieces[1][1] != null && !pieces[1][1].isShield())
							{
								remove(1, 1);
							}
						}
						else if(y == 7)
						{
							if(pieces[1][6] != null && !pieces[1][6].isShield())
							{
								remove(1, 6);
							}
						}
						else
						{
							if(pieces[x + 1][y + 1] != null && !pieces[x + 1][y + 1].isShield())
							{
								remove(x + 1, y + 1);
							}
							if(pieces[x + 1][y - 1] != null && !pieces[x + 1][y - 1].isShield())
							{
								remove(x + 1, y - 1);
							}
						}
					}
					else if(y == 0)
					{
						if(pieces[x + 1][y + 1] != null && !pieces[x + 1][y + 1].isShield())
						{
							remove(x + 1, y + 1);
						}
						if(pieces[x - 1][y + 1] != null && !pieces[x - 1][y - 1].isShield())
						{
							remove(x - 1, y - 1);
						}
					}
					else if(y == 7)
					{
						if(pieces[x + 1][y - 1] != null && !pieces[x + 1][y - 1].isShield())
						{
							remove(x + 1, y - 1);
						}
						if(pieces[x - 1][y - 1] != null && !pieces[x - 1][y - 1].isShield())
						{
							remove(x - 1, y - 1);
						}
					}
					else
					{
						if(pieces[x + 1][y - 1] != null && !pieces[x + 1][y - 1].isShield())
						{
							remove(x + 1, y - 1);
						}
						if(pieces[x - 1][y - 1] != null && !pieces[x - 1][y - 1].isShield())
						{
							remove(x - 1, y - 1);
						}
						if(pieces[x + 1][y + 1] != null && !pieces[x + 1][y + 1].isShield())
						{
							remove(x + 1, y + 1);
						}
						if(pieces[x - 1][y + 1] != null && !pieces[x - 1][y + 1].isShield())
						{
							remove(x - 1, y + 1);
						}
					}
					remove(selectedX, selectedY);
					selected = null;
					selectedX = 8;
					selectedY = 8;
				}
				else
				{
					captured = true;
				}
			}
			place(selected, x, y);
			selectedX = x;
			selectedY = y;
			playerMoved = true;
			return;
		}
		else if(!captured && !playerMoved)
		{
			if(selected != null)
			{
				//StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				//StdDrawPlus.filledSquare(selectedX + .5, selectedY + .5, .5);
				//drawPiece(selected, selectedX, selectedY);
			}
			//StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	        //StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	        //drawPiece(pieces[x][y], x, y);
	        selected = pieceAt(x, y);
	        selectedX = x;
	        selectedY = y;
		}
	}

	public void place(Piece p, int x, int y)
	{
		if(outBounds(x,y))
		{
			return;
		}
		else if (p == null)
		{
			return;
		}
		else
		{
			for(int i = 0; i < 8; i++)
			{
				for(int j = 0; j < 8; j++)
				{
					if(pieces[i][j] == p)
					{
						pieces[i][j] = null;
						//StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	            		//StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					}
				}
			}
			pieces[x][y] = p;
			//drawPiece(p, x, y);
		}
	}


	public Piece remove(int x, int y)
	{
		if(outBounds(x, y))
		{
			System.out.println("Can't remove because not within bounds.");
			return null;
		}
		else if(pieces[x][y] == null)
		{
			System.out.println("There is no piece there.");
			return null;
		}
		else
		{
			//StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	        //StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	        Piece ret = pieces[x][y];
	        pieces[x][y] = null;
	        return ret;
		}

	}

	private boolean outBounds(int x, int y)
	{
		if(x < 0 || x > 7 || y < 0 || y > 7)
		{
			return true;
		}
		return false;
	}

	public boolean canEndTurn()
	{
		return captured || playerMoved;
	}

	public void endTurn()
	{
		playersTurn = 1 - playersTurn;
		playerMoved = false;
		if(selected != null)
		{
			selected.doneCapturing();
			//StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        	//StdDrawPlus.filledSquare(selectedX + .5, selectedY + .5, .5);
        	//drawPiece(selected, selectedX, selectedY);
			selected = null;
			selectedX = 8;
			selectedY = 8;
		}
		captured = false;
	}

	public String winner()
	{
		boolean empty = true;
		boolean fire = false;
		boolean water = false;
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(pieces[i][j] != null)
				{
					empty = false;
					if(pieces[i][j].isFire())
					{
						fire = true;
					}
					else
					{
						water = true;
					}
				}
			}
		}
		if(empty)
		{
			return "No one";
		}
		else if(fire && water)
		{
			return null;
		}
		else if(fire)
		{
			return "Fire";
		}
		else
		{
			return "Water";
		}
	}

	public static void main(String[] args)
	{
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        for (int i = 0; i < 8; i++) 
		{
	        for (int j = 0; j < 8; j++) 
	        {
	            if ((i + j) % 2 == 0) 
	            {
	            	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	            	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
		        }
		        else
		        {
		        	StdDrawPlus.setPenColor(StdDrawPlus.RED);
	            	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
		        }
		    }
		}

		Board b = new Board(false);

		for (int i = 0; i < 8; i++) 
		{
	        for (int j = 0; j < 8; j++) 
	        {
	        	if ((i + j) % 2 == 0) 
	        	{
	            	switch(j)
	            	{
	            		case 0:
	            			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
	            			break;
	            		case 1:
	            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	            			break;
	            		case 2:
	            			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	            			break;
	            		case 7:
	            			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
	            			break;
	            		case 6:
	            			StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	            			break;
	            		case 5:
	            			StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	            			break;
	            		default:
	            			break;
	            	}
	            }
		    }
		}

        while(true)
        {
        	if(StdDrawPlus.mousePressed())
        	{
        		double x = StdDrawPlus.mouseX();
        		double y = StdDrawPlus.mouseY();
        		if(b.canSelect((int) x, (int) y))
        		{
        			System.out.println("Passed canSelect");
        			b.select((int) x, (int) y);
        			b.drawPiece(false);
        		}
        	}
        	else if(StdDrawPlus.isSpacePressed())
        	{
        		if(b.canEndTurn())
        		{
        			b.drawPiece(true);
        			b.endTurn();


     //    			if(hasCaptured)
					// {
					// 	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			  //       	StdDrawPlus.filledSquare(x + .5, y + .5, .5);
					// }

					// if(isFire)
					// {
					// 	if(isKing)
					// 	{
					// 		if(isBomb)
					// 		{
					// 			StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
					// 		}
					// 		else if(isShield)
					// 		{
					// 			StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
					// 		}
					// 		else
					// 		{
					// 			StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
					// 		}
					// 	}
					// 	else
					// 	{
					// 		if(isBomb)
					// 		{
					// 			StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
					// 		}
					// 		else if(isShield)
					// 		{
					// 			StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
					// 		}
					// 		else
					// 		{
					// 			StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
					// 		}
					// 	}
					// }
					// else
					// {
					// 	if(isKing)
					// 	{
					// 		if(isBomb)
					// 		{
					// 			StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
					// 		}
					// 		else if(isShield)
					// 		{
					// 			StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
					// 		}
					// 		else
					// 		{
					// 			StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
					// 		}
					// 	}
					// 	else
					// 	{
					// 		if(isBomb)
					// 		{
					// 			StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
					// 		}
					// 		else if(isShield)
					// 		{
					// 			StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
					// 		}
					// 		else
					// 		{
					// 			StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
					// 		}
					// 	}
					// }









        			String win = b.winner();
	        		if(win != null)
	        		{
	        			System.out.println("Winner is " + win);
	        			return;
	        		}
        		}
        	}
        	else if(StdDrawPlus.isNPressed())
        	{
        		b = new Board(false);
        	}
        }
	}
}











