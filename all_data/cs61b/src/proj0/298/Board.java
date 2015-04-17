public class Board
{
	private Piece[][] pieces;
	private int selectedX;
	private int selectedY;
	private boolean hasMoved;
	private boolean isPlaying;
	private int player; //0 for Fire player and 1 for Water player
	private boolean hasCaptured;
	private Piece pieceThatMoved;

	public static void main(String[] args)
	{
		Board field = new Board(false);
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		
		while(field.isPlaying)
		{
			field.drawBoard();
			StdDrawPlus.show(10);
			if(StdDrawPlus.mousePressed())
			{
				int x = (int)StdDrawPlus.mouseX();
				int y = (int)StdDrawPlus.mouseY();
				if(field.canSelect(x,y))
				{
					field.select(x,y);
				}
			}
			if(StdDrawPlus.isSpacePressed())
			{
				if(field.canEndTurn())
				{
					field.endTurn();
				}
			}
		}
		System.out.println(field.winner());
	}

	/*private void printBoard()
	{
		System.out.println("=========================================");
		for (int i = 0; i < 8; i++) 
		{
            for (int j = 0; j < 8; j++) 
           	{
           		System.out.print("[");
           		if(pieces[i][j] != null)
               	{
               		if(pieces[i][j].isFire())
               		{
               			System.out.print("F");
               		}
               		else
               		{
               			System.out.print("W");
               		}
               	}
               	System.out.print("]");
            }
            System.out.println();
        }
	}*/

	private void drawBoard()
	{
		/* This code was taken paritially from the Demo code for drawing a board */
		for (int i = 0; i < 8; i++) 
		{
            for (int j = 0; j < 8; j++) 
           	{
           		if (selectedX == i && selectedY ==j) {
           			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
           	    } else if ((i + j) % 2 == 0){ 
           	    	StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
           	    } else { 
           	    	StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
           	    }
               	StdDrawPlus.filledSquare(i + .5, j + .5, .5);
               	if(pieces[j][i] != null)
               	{
               		if(pieces[j][i].isFire())
               		{
               			if(pieces[j][i].isKing())
               			{
               				if(pieces[j][i].isBomb())
               				{
               					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
               				}
               				else if(pieces[j][i].isShield())
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
                			if(pieces[j][i].isBomb())
               				{
               					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
               				}
               				else if(pieces[j][i].isShield())
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
               			if(pieces[j][i].isKing())
               			{
               				if(pieces[j][i].isBomb())
               				{
               					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
               				}
               				else if(pieces[j][i].isShield())
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
                			if(pieces[j][i].isBomb())
               				{
               					StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
               				}
               				else if(pieces[j][i].isShield())
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
	}

	public Board(boolean shouldBeEmpty)
	{
		this.isPlaying = true;
		this.player = 0;
		this.hasMoved = false;
		this.hasCaptured = false;
		selectedX = -1;
		selectedY = -1;
		if(shouldBeEmpty)
		{
			this.pieces = new Piece[8][8];
		}
		else
		{
			pieces = new Piece[8][8];
			/* Create the fire peices */
			for(int i=0;i<4;i++)
			{
				pieces[0][2*i] = new Piece(true,this,2*i,0,"pawn");
			}
			for(int i=0;i<4;i++)
			{
				pieces[1][2*i+1] = new Piece(true,this,2*i+1,1,"shield");
			}
			for(int i=0;i<4;i++)
			{
				pieces[2][2*i] = new Piece(true,this,2*i,2,"bomb");
			}

			/* Creat the water pieces */
			for(int i=0;i<4;i++)
			{
				pieces[7][2*i+1] = new Piece(false,this,2*i+1,7,"pawn");
			}
			for(int i=0;i<4;i++)
			{
				pieces[6][2*i] = new Piece(false,this,2*i,6,"shield");
			}
			for(int i=0;i<4;i++)
			{
				pieces[5][2*i+1] = new Piece(false,this,2*i+1,5,"bomb");
			}
		}
	}

	public Piece pieceAt(int x, int y)
	{
		if(x > 7 || y > 7 || x < 0 || y < 0)
		{
			return null;
		}
		return pieces[y][x];
	}

	public Piece remove(int x, int y)
	{
		if(x > 7 || y > 7 || x < 0 || y < 0)
		{
			System.out.println("Index out of bounds");
			return null;
		}
		Piece returnMe = pieces[y][x];
		pieces[y][x] = null;
		if(returnMe == null)
		{
			System.out.println("No piece at selected location");
		}
		return returnMe;
	}

	public void place(Piece p, int x, int y)
	{
		if(x > 7 || y > 7 || x < 0 || y < 0 || p == null)
		{
			System.out.println("No piece or out of bounds");
			return;
		}
		for (int i = 0; i < 8; i++) 
		{
            for (int j = 0; j < 8; j++) 
           	{
           		if(pieces[i][j] == p)
           		{
           			pieces[i][j] = null;
           		}
           	}
        }
        //System.out.println("Add piece to ("+x+","+y+")");
        pieces[y][x] = p;
	}

	public String winner()
	{
		//printBoard();
		int fire = 0;
		int water = 0;
		for (int i = 0; i < 8; i++) 
		{
            for (int j = 0; j < 8; j++) 
           	{
           		if(pieces[i][j] != null)
           		{
           			if(pieces[i][j].isFire())
           			{
           				//System.out.println("+1 Fire"); //Used during debugging
           				fire += 1; 
           			}
           			else
           			{
           				//System.out.println("+1 Water"); //Used during debugging
           				water += 1;
           			}
           		}
           	}
        }
        if(water == 0 && fire == 0)
        {
        	isPlaying = false;
        	return "No one";
        }
        if(water == 0)
        {
        	isPlaying = false;
        	return "Fire";
        }
        if(fire == 0)
        {
        	isPlaying = false;
        	return "Water";
        }
        return null;
	}

	public boolean canEndTurn()
	{
		return this.hasMoved;
	}

	public void endTurn()
	{
		if(canEndTurn())
		{
			player = 1 - player;
			hasMoved = false;
			hasCaptured = false;
			pieceThatMoved.doneCapturing();
			pieceThatMoved = null;
			selectedY = -1;
			selectedX = -1;
			System.out.println(winner());
			//System.out.println("Turn ended, it is now player "+player+"'s turn");
		}
	}

	public boolean canSelect(int x, int y)
	{
		if(x < 8 && y < 8 && x > -1 && y > -1)
		{
			if(!hasMoved)
			{
				if(pieceAt(x,y) == null && pieceAt(selectedX,selectedY) != null)
				{
					if(isValidMove(selectedX,selectedY,x,y))
					{
						//System.out.println("Can select X value = "+x+", Y value = "+y);
						return true;
					}
				}
				else if(pieceAt(x,y) != null)
				{ 
					//System.out.println("Piece at");
					if(pieceAt(x,y).side() == player)
					{
						//System.out.println("Can select X value = "+x+", Y value = "+y);
						return true;
					}
				}
				//System.out.println("Can not select X value = "+x+", Y value = "+y);
				return false;
			}
			if(hasCaptured && hasMoved)
			{
				if(pieceAt(x,y) == null && pieceAt(selectedX,selectedY) != null)
				{
					if(isValidMove(selectedX,selectedY,x,y))
					{
						//System.out.println("Can select X value = "+x+", Y value = "+y);
						return true;
					}
				}
				//System.out.println("Can not select X value = "+x+", Y value = "+y+" as you have already moved a different piece");
				return false;
			}
		}
		//System.out.println("Can not select X value = "+x+", Y value = "+y+" as it is out of bounds or you have already moved");
		return false;
	}

	private boolean isValidMove(int xi,int yi,int xf,int yf)
	{
		if(hasCaptured)
		{
			if(pieceAt(xi,yi).isKing())
			{
				if(Math.abs(xi-xf) == 2 && Math.abs(yi-yf) == 2 && pieceAt(xi + (xf - xi)/2, yi + (yf - yi)/2) != null && pieceAt(xi + (xf - xi)/2, yi + (yf - yi)/2).side() != player)
				{
					return true;
				}
				return false;
			}
			else
			{
				if(pieceAt(xi,yi).isFire())
				{
					if(Math.abs(xi-xf) == 2 && yi-yf == -2 && pieceAt(xi + (xf - xi)/2, yi + (yf - yi)/2) != null && pieceAt(xi + (xf - xi)/2, yi + (yf - yi)/2).side() != player)
					{
						return true;
					}
					return false;
				}
				else
				{
					if(Math.abs(xi-xf) == 2 && yi-yf == 2 && pieceAt(xi + (xf - xi)/2, yi + (yf - yi)/2) != null && pieceAt(xi + (xf - xi)/2, yi + (yf - yi)/2).side() != player)
					{
						return true;
					}
					return false;
				}
			}
		}
		else
		{
			if(pieceAt(xi,yi).isKing())
			{
				if(Math.abs(xi-xf) == 1 && Math.abs(yi-yf) == 1)
				{
					return true;
				}
				else if(Math.abs(xi-xf) == 2 && Math.abs(yi-yf) == 2 && pieceAt(xi + (xf - xi)/2, yi + (yf - yi)/2) != null && pieceAt(xi + (xf - xi)/2, yi + (yf - yi)/2).side() != player)
				{
					return true;
				}
				return false;
			}
			else
			{
				if(pieceAt(xi,yi).isFire())
				{
					if(Math.abs(xi-xf) == 1 && yi-yf == -1)
					{
						return true;
					}
					else if(Math.abs(xi-xf) == 2 && yi-yf == -2 && pieceAt(xi + (xf - xi)/2, yi + (yf - yi)/2) != null && pieceAt(xi + (xf - xi)/2, yi + (yf - yi)/2).side() != player)
					{
						return true;
					}
					return false;
				}
				else
				{
					if(Math.abs(xi-xf) == 1 && yi-yf == 1)
					{
						return true;
					}
					else if(Math.abs(xi-xf) == 2 && yi-yf == 2 && pieceAt(xi + (xf - xi)/2, yi + (yf - yi)/2) != null && pieceAt(xi + (xf - xi)/2, yi + (yf - yi)/2).side() != player)
					{
						return true;
					}
					return false;
				}
			}
		}
	}

	public void select(int x, int y)
	{
		if(pieceAt(x,y) != null)
		{
			//System.out.println("Selected point ("+x+","+y+")");
			selectedX = x;
			selectedY = y;
		}
		else if(selectedX > -1)
		{
			pieceThatMoved = pieceAt(selectedX,selectedY);
			pieceThatMoved.move(x,y);
			hasMoved = true;
			hasCaptured = pieceThatMoved.hasCaptured();
			if(!pieceThatMoved.isBomb())
			{
				selectedX = x;
				selectedY = y;
				return;
			}
			hasCaptured = false;
			selectedX = -1;
			selectedY = 1;
		}
	}
}