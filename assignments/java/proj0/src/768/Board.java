public class Board
{
	private Piece[][] table;
	private boolean fireTurn; //true = fire, false = water
	private Piece fireSelected;
	private int selectedX;
	private int selectedY;
	private Piece waterSelected;
	private boolean fireMoved;
	private boolean waterMoved;
	private int[] justRemovedX;
	private int[] justRemovedY;
	private int removedPieces;

	public Board(boolean shouldBeEmpty)
	{
		fireTurn = true;
		table = new Piece[8][8];
		justRemovedX = new int[24];
		justRemovedY = new int[24];
		removedPieces = 0;

		if(!shouldBeEmpty)
		{
			for (int i = 0; i < 8 ; i++)
			{
				if(i % 2 == 0)
				{
					table[6][i] = new Piece(false, this, i, 6, "shield");
					table[2][i] = new Piece(true, this, i, 2, "bomb");
					table[0][i] = new Piece(true, this, i, 0, "pawn");
				}
				else
				{
					table[7][i] = new Piece(false, this, i, 7, "pawn");
					table[5][i] = new Piece(false, this, i, 5, "bomb");
					table[1][i] = new Piece(true, this, i, 1, "shield");
				}
			}
		}
	}
	
	public Piece pieceAt(int x, int y)
	{
		if (x >= 8 || y >= 8 || table[y][x] == null) 
		{ 
			return null; 
		}
		return table[y][x];	
	}

	private boolean helperValidMove(Piece p, int xi, int yi, int xf, int yf)
	{
		if(xf < 8 && yf < 8)
		{
			if(p.isKing() && Math.abs(xf - xi) == 1
				&& Math.abs(yf - yi) == 1)
			{
				return true;
			}
			else if(p.isFire() && Math.abs(xf - xi) == 1
				&& yf - yi == 1)
			{
				return true;
			}
			else if(!p.isFire() && Math.abs(xf - xi) == 1
				&& yf - yi == -1)
			{
				return true;
			}
		}
		return false;
	}

	private boolean helperValidCapture(Piece p, int xi, int yi, int xf, int yf)
	{
		if(xf < 8 && yf < 8)
		{
			if(xf - xi > 0 && yf - yi > 0 && (p.isKing() || p.isFire())) //northeast capture
			{
				if(p.isFire() && pieceAt(xf - 1, yf - 1) != null 
					&& !pieceAt(xf - 1, yf - 1).isFire())
				{
					return true;
				}
				else if (!p.isFire() && pieceAt(xf - 1, yf - 1) != null 
					&& pieceAt(xf - 1, yf - 1).isFire())
				{
					return true;
				}
			}
			else if(xf - xi > 0 && yf - yi < 0 && (p.isKing() || !p.isFire())) //southeast capture
			{
				if(p.isFire() && pieceAt(xf - 1, yf + 1) != null 
					&& !pieceAt(xf - 1, yf + 1).isFire())
				{
					return true;
				}
				else if (!p.isFire() && pieceAt(xf - 1, yf + 1) != null 
					&& pieceAt(xf - 1, yf + 1).isFire())
				{
					return true;
				}
			}
			else if(xf - xi < 0 && yf - yi > 0 && (p.isKing() || p.isFire())) //northwest capture
			{
				if(p.isFire() && pieceAt(xf + 1, yf - 1) != null 
					&& !pieceAt(xf + 1, yf - 1).isFire())
				{
					return true;
				}
				else if (!p.isFire() && pieceAt(xf + 1, yf - 1) != null 
					&& pieceAt(xf + 1, yf - 1).isFire())
				{
					return true;
				}
			}
			else if(xf - xi < 0 && yf - yi < 0 && (p.isKing() || !p.isFire())) //southwest capture
			{
				if(p.isFire() && pieceAt(xf + 1, yf + 1) != null 
					&& !pieceAt(xf + 1, yf + 1).isFire())
				{
					return true;
				}
				else if (!p.isFire() && pieceAt(xf + 1, yf + 1) != null 
					&& pieceAt(xf + 1, yf + 1).isFire())
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean canSelect(int x, int y)
	{
		if(pieceAt(x, y) != null)
		{
			if(fireTurn)
			{
				if (pieceAt(x, y).isFire() 
					&& ((fireSelected == null ) || (fireSelected != null && !fireMoved)))
				{
					return true;
				}
			}
			else if(!fireTurn)
			{
				if (!pieceAt(x, y).isFire() 
					&& ((waterSelected == null ) || (waterSelected != null && !waterMoved)))
				{
					return true;
				}
			}
		}
		else
		{
			if(fireTurn)
			{
				if(fireSelected != null && !fireMoved 
					&& (helperValidMove(fireSelected, selectedX, selectedY, x, y) 
						|| helperValidCapture(fireSelected, selectedX, selectedY, x, y)))
				{
					return true;
				}
				else if(fireSelected != null && fireSelected.hasCaptured() 
					&& helperValidCapture(fireSelected, selectedX, selectedY, x, y))
				{
					return true;
				}
			}
			else if(!fireTurn)
			{
				if(waterSelected != null && !waterMoved 
					&& (helperValidMove(waterSelected, selectedX, selectedY, x, y) 
						|| helperValidCapture(waterSelected, selectedX, selectedY, x, y)))
				{
					return true;
				}
				else if(waterSelected != null && waterSelected.hasCaptured() 
					&& helperValidCapture(waterSelected, selectedX, selectedY, x, y))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public void select(int x, int y)
	{
		if(fireTurn)
		{
			if(pieceAt(x, y) == null)
			{
				fireSelected.move(x, y);
				fireMoved = true;
			}
			else
			{
				fireSelected = pieceAt(x, y);
				System.out.println("Selected fire piece at ("+x+","+y+")");
			}
		}	
		else if(!fireTurn)
		{
			if(pieceAt(x, y) == null)
			{
				waterSelected.move(x, y);
				waterMoved = true;
			}
			else
			{
				waterSelected = pieceAt(x, y);
				System.out.println("Selected water piece at ("+x+","+y+")");
			}
		}
		
		selectedX = x;
		selectedY = y;
	}

	public void place(Piece p, int x, int y)
	{
		if(p != null && x < 8 && y < 8)
		{
			table[y][x] = p;
			if(p.isFire())
			{
				System.out.println("Placed fire piece at ("+x+","+y+")");
			}
			else
			{
				System.out.println("Placed water piece at ("+x+","+y+")");
			}
		}
	}

	public Piece remove(int x, int y)
	{
		Piece removed = null;
		if(table[y][x] == null)
		{
			System.out.println("Nothing was at ("+x+","+y+") so nothing was removed");
		} 
		else if(x >= 8 || y >= 8)
		{
			System.out.println("("+x+","+y+") is out of bounds so nothing was removed");
		}
		else
		{
			System.out.println("Removed piece at ("+x+","+y+")");
			removed = table[y][x];
			table[y][x] = null;

			//append removed piece to array
			int i = 0;
			for(i = 0; i < 24; i++)
			{
				//since 0 any piece on x=0 cant be removed
				if(justRemovedX[i] == 0) 
				{
					justRemovedX[i] = x;
					justRemovedY[i] = y;
					break;
				}
			}
			removedPieces = i+1;
		}
		return removed;
	}
	
	public boolean canEndTurn()
	{
		return (fireTurn && fireMoved) || (!fireTurn && waterMoved);
	}
	
	public void endTurn()
	{
		if(fireTurn)
		{
			fireTurn = false;
			fireMoved = false;
			fireSelected = null;
		}
		else
		{
			fireTurn = true;
			waterMoved = false;
			waterSelected = null;
		}

		System.out.println("Turn ended");
	}

	public String winner()
	{
		boolean fireExists = false;
		boolean waterExists = false;

		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(table[i][j] != null && table[i][j].isFire() == true)
				{
					fireExists = true;
				}
				else if(table[i][j] != null && table[i][j].isFire() == false)
				{
					waterExists = true;
				}
			}
		}

		if(fireExists && !waterExists)
		{
			return "Fire";
		}
		else if(!fireExists && waterExists)
		{
			return "Water";
		}
		else if(!fireExists && !waterExists)
		{
			return "No one";
		}
		else
		{
			return null;
		}
	}

	private void drawSelect(int x, int y)
	{
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		if(pieceAt(x, y) != null)
		{
			drawPiece(pieceAt(x, y), x, y);
		}
	}

	private void refreshBoard()
	{
		for (int r = 0; r < 8; r++)
		{
			for (int c = 0; c < 8; c++)
			{
				resetSquare(c, r);
				if(table[r][c] != null)
				{
					
					drawPiece(table[r][c], c, r);
				}
			}
		}
	}

	private void drawPiece(Piece p, int x, int y)
	{
		String team = "";
		String type = "";
		String king = "";
		if(p.isFire())
		{
			team = "fire";
		} 
		else
		{
			team = "water";
		}
		if(p.isBomb())
		{
			type = "bomb";
		}
		else if(p.isShield())
		{
			type = "shield";
		}
		else
		{
			type ="pawn";
		}
		if(p.isKing())
		{
			king = "-crowned";
		}
		StdDrawPlus.picture(x + .5, y + .5, "img/"+type+"-"+team+king+".png", 1, 1);
	}

	private void resetSquare(int x, int y)
	{
		if(y % 2 == 0 && x % 2 == 0)
		{
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		}
		else if(y % 2 == 0 && x % 2 == 1)
		{
			StdDrawPlus.setPenColor(StdDrawPlus.RED);
		}
		else if(y % 2 == 1 && x % 2 == 0)
		{
			StdDrawPlus.setPenColor(StdDrawPlus.RED);
		}
		else if(y % 2 == 1 && x % 2 == 1)
		{
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		}
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	}

	public static void main(String[] args) 
	{
		StdDrawPlus.setScale(0, 8);
		Board game = new Board(false);

		//draw board and pieces
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
                StdDrawPlus.filledSquare(j + .5, i + .5, .5);

                if(game.table[i][j] != null)
                {
                	game.drawPiece(game.table[i][j], j, i);
                }
                
            }
        }

        //main game loop until game ends
        boolean prevTurnFire = false;
        while(game.winner() != "Fire" 
        	&& game.winner() != "Water" 
        	&& game.winner() != "No one")
        {

        	if(game.fireTurn && !prevTurnFire) 
	        { 
	        	System.out.println("----------------------------------");
	        	System.out.println("Turn: Fire");
	        	prevTurnFire = true;
	        }
	        else if(!game.fireTurn && prevTurnFire)
	        { 
	        	System.out.println("----------------------------------");
	        	System.out.println("Turn: Water"); 
	        	prevTurnFire = false;
	        }

        	//turn loop
        	while(!game.canEndTurn())
        	{
        		int mouseX = 0;
	        	int mouseY = 0;
	        	while(!StdDrawPlus.mousePressed())
	        	{
	        		//wait for user to select square
	        		//and keep recording X,Y coords of mouse
	        		mouseX = (int)StdDrawPlus.mouseX();
	        		mouseY = (int)StdDrawPlus.mouseY();
	        	}

	        	//mouse is clicked: if you can select than select
	        	if(game.canSelect(mouseX, mouseY))
	        	{
	        		if(game.pieceAt(mouseX, mouseY) == null)
	        		{
	        			game.resetSquare(game.selectedX, game.selectedY);
	        		}
	        		
	        		game.select(mouseX, mouseY);
	        		game.drawSelect(mouseX, mouseY);
	        		
	        		for(int i = 0; i < game.removedPieces; i++)
	        		{
	        			//reset squares for all removed pieces
	        			//will be one repetitive action for object
	        			//that moved
	        			game.resetSquare(game.justRemovedX[i], game.justRemovedY[i]);
	        		}
	        	}
	        	else
	        	{
	        		System.out.println("Can't select: ("+mouseX+","+mouseY+")");
	        	}

	        	StdDrawPlus.show(200);
	        }

	        while(!StdDrawPlus.isSpacePressed())
	        {
	        }

	        game.resetSquare(game.selectedX, game.selectedY);
	        if(game.fireTurn)
	        {
	        	game.drawPiece(game.fireSelected, game.selectedX, game.selectedY);
	        }
	        else
	        {
	        	game.drawPiece(game.waterSelected, game.selectedX, game.selectedY);
	        }
	        game.endTurn();
	        game.justRemovedX = new int[24];
	        game.justRemovedY = new int[24];
	        game.removedPieces = 0;
	        game.refreshBoard();
        }
	}
}