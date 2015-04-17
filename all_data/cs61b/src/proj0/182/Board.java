// I would like to give credit to two people that helped me on this project, Kevin Shi and Justin Liu. Justin helped me with the initial "approach" to the project while Kevin explained why I got some specific syntax errors. (That he also had when he was debugging). 

public class Board
{
	
	private boolean[][] highlight;
	private  Piece[][] bits;
	private int currX, currY; 
	private boolean isFTurn; 
	private boolean canEndTurn; 
	private boolean alMoved; 

	
    public static void main(String[] args) {
    	Board hello = new Board(false); 
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            drawBoard(hello);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int a = (int)x; 
                int b = (int)y; 
				if (hello.canSelect(a,b))
				{
					hello.select(a,b); 
				} 
			}
			if(StdDrawPlus.isSpacePressed())
			{
				if(hello.canEndTurn())
				{
					hello.endTurn();
				}

			}
			if(hello.winner() != null )
			{
				return; 
			}         
            StdDrawPlus.show(10);
        }

    }

    private static void drawBoard(Board hello) 
    {
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if (hello.highlight[i][j])
                	{
                		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	}
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
               	if (hello.bits[i][j] != null)
               	{ 
	                if (hello.bits[i][j].isKing())
	                { 
	            		if (hello.bits[i][j].isFire())
	            			{ 
	            				if (hello.bits[i][j].isBomb())
	            					{ 
	            						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
	            					}
	            				else if(hello.bits[i][j].isShield())
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
	            				if (hello.bits[i][j].isBomb())
	            					{ 
	            						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
	            					}
	            				else if(hello.bits[i][j].isShield())
	            					{ 
	            						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
	            					}
	            				else 
	            					{ 
	            						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
	            					}
	            			}
	            	}
	            	else if(hello.bits[i][j].isBomb())
	            	{ 
	            		if (hello.bits[i][j].isFire())
	            			{                 				
	            				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
	            			} 
	            		else 
	            			{ 
	            				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
	            			}

	            	}
	            	else if( hello.bits[i][j].isShield())
	            	{ 
	            		if (hello.bits[i][j].isFire())
	            			{
	            				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
	            			} 
	            		else 
	            			{ 
	            				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
	            			}
	            	}
	            	else
	            	{ 
	            		if (hello.bits[i][j].isFire())
	            		{
	            				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
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
    	
    	bits = new Piece[8][8]; 
    	highlight = new boolean[8][8]; 
    	currX = -1000; 
    	currY = -1000; 
    	isFTurn = true; 
    	canEndTurn = false;
    	alMoved = false; 
    	if (!shouldBeEmpty)
    	{

    		for (int i = 0; i < 8; i++) 
    		{
            	for (int j = 0; j < 8; j++) 
            {
            	if (i % 2 == 0 && j == 0) 
                {
                	bits[i][j] = new Piece(true, this, i, j, "pawn" ); 
                } 

                 else if( i % 2 != 0 && j == 1 )
                {
                	bits[i][j] = new Piece(true, this, i, j, "shield" ); 
                }
                else if( i % 2 == 0 && j == 2)
                {
                	bits[i][j] = new Piece(true, this, i, j, "bomb" ); 
                }
                 else if( i % 2 != 0 && j == 7)
                { 
                	bits[i][j] = new Piece(false, this, i, j, "pawn"); 
                } 
                else if(i % 2 == 0 && j == 6)
                {
                	bits[i][j] = new Piece(false, this, i, j, "shield"); 
                }
                else if( i % 2 != 0 && j == 5)
                { 
                	bits[i][j] = new Piece(false, this, i, j, "bomb"); 
                }

            }
        	}


    	}
    }

    private void nolight()
    {
    	for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
            	highlight[i][j] = false; 
            } 
        }
        currX = -1000; 
    	currY = -1000; 
    }

    private void godLight(int x, int y)
    { 
    	nolight(); 
    	highlight[x][y] = true; 
    	currX = x;
    	currY = y;
    }

    public Piece pieceAt( int x, int y)
    { 
    	if ( x > 7 || x < 0)
			return null; 
		else if( y > 7 || y < 0)
			return null; 
		else if (bits[x][y] == null) 
			return null; 
		else 
			return bits[x][y];
    }

    public void place(Piece p, int x, int y)
    { 
    	if ( x <=7 && x >= 0 && y <= 7 && y >= 0)
		{
			bits[x][y] = p; 
		}
    }

    public Piece remove(int x, int y)
	{ 

		if ( x > 7 || x < 0)
		{ 
			System.out.println("Input X is not in frame"); 
			return null; 
		}
		else if( y > 7 || y < 0)
		{ 
			System.out.println("Inputs Y is not in frame"); 
			return null; 
		}
		else if(bits[x][y] == null)
		{ 
			System.out.println("No Piece!"); 
			return null; 
		}
		else
		{
			Piece temp = bits[x][y]; 
			bits[x][y] = null; 
			return temp; 
		}
	} 

	public boolean canSelect(int x, int y)
	{
		// Piece check 
		if (bits[x][y] != null && isFTurn == bits[x][y].isFire() && !canEndTurn)
		{
			return true;
		}

		// Blank space check 
		if(bits[x][y] == null && currX >= 0 && currY >= 0 && !alMoved)
		{
			if (validMove(currX, currY, x, y))
			{
				return true;
			}
		}

		return false;

	}

	private boolean validMove(int xi, int yi, int xf, int yf)
	{
	Piece name = bits[xi][yi];
	Piece skip;  
	int a = 0; 
	int b = 0; 
	if (bits[xf][yf] == null) 
		{ 
			if (Math.abs(xf - xi) == 1 && Math.abs(yf - yi) == 1 && !canEndTurn)
				{ 
					if(name.isKing())
						{ 
							return true; 
						}

					else if (name.isFire()) 
					{ 
						if ( yf > yi) 
							return true; 
					}
					else 
					{ 
						if ( yf < yi) 
							return true; 

					}

				} 
			if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2) // distance check 
				{ 
					// trying to find the piece... 
					if( (xf - xi) > 0 && (yf - yi) > 0)
					{
						skip = bits[xi + 1][yi + 1]; 
						a = xi + 1; 
						b = yi + 1; 
					}
					else if( (xf - xi) > 0 && (yf - yi) < 0)
					{ 
						a = xi + 1; 
						b = yi - 1; 
						skip = bits[xi + 1][yi - 1]; 
					}
					else if( (xf - xi) < 0 && (yf - yi) < 0)
					{
						a = xi - 1; 
						b = yi - 1; 
						skip = bits[xi - 1][yi - 1]; 
					}
					else 
					{
						a = xi - 1; 
						b = yi + 1; 
						skip = bits[xi - 1][yi + 1]; 
					}


					if (name.isKing() && skip != null) // checking if it's a king and if we're not pointing to null 
						{ 
							if(skip.isFire() != isFTurn) // these can't equal to each other (opposite pieces)
							{
								return true; 
							}
						}
					else if( yf > yi && skip != null && name.isFire()) // (1) We're going up and that the skip isn't equal to null
					{
						if (skip.isFire() != isFTurn)
							return true; 
						
					}
					else if( yi > yf && skip != null && !(name.isFire()))
					{
						if (skip.isFire() != isFTurn)
							return true; 
					}					

				}
		}
	return false; 
	}

	public boolean canEndTurn() 
	{ 
		return canEndTurn; 
	}

	public void select(int x, int y)
	{
		if (bits[x][y] != null)
			{ 
				godLight(x,y); 
				currX = x; 
				currY = y; 
			}
		else
		{
			if (Math.abs(x - currX) == 1 && Math.abs(y - currY) == 1)
				{ 
					place(remove(currX,currY), x, y); // placing the piece in its new location 
					bits[x][y].move(x,y); 
					currX = x; // establishing the current x value of the piece 
					currY = y; // establishing the current y value of the piece 
					alMoved = true; // I have alread moved
					canEndTurn = true; // You have moved so you can end your turn  
					godLight(x,y); 

				}
			else if (Math.abs(x - currX) == 2 && Math.abs(y - currY) == 2)
				{ 
					Piece skip;  
					int a = 0;
					int b = 0;
					// trying to find the piece... 
					if( (x - currX) > 0 && (y - currY) > 0)
					{
						skip = bits[currX + 1][currY + 1]; 
						a = currX + 1; 
						b = currY + 1; 
					}
					else if( (x - currX) > 0 && (y - currY) < 0)
					{ 
						a = currX + 1; 
						b = currY - 1; 
						skip = bits[currX + 1][currY - 1]; 
					}
					else if( (x - currX) < 0 && (y - currY) < 0)
					{
						a = currX - 1; 
						b = currY - 1; 
						skip = bits[currX - 1][currY - 1]; 
					}
					else 
					{
						a = currX - 1; 
						b = currY + 1; 
						skip = bits[currX - 1][currY + 1]; 
					}
					remove(a,b);
					Piece savePiece = remove(currX,currY);
					if (savePiece.isBomb()) {
						//
						if(bits[x-1][y-1] != null)
							if (!bits[x-1][y-1].isShield())
							{
								remove(x-1, y- 1); 
							}
						if (bits[x+1][y+1] != null)
							if (!bits[x+1][y+1].isShield())
							{
								remove(x+1, y + 1); 
							}
						if (bits[x+1][y-1] != null)
							if (!bits[x+1][y-1].isShield())
							{
								remove(x+1, y - 1); 
							}
						if (bits[x-1][y+1] != null)
							if (!bits[x-1][y+1].isShield())
							{
								remove(x-1, y + 1); 
							}
						nolight();
						alMoved = true;
						canEndTurn = true;
					} else  {
						place(savePiece, x, y);
						bits[x][y].move(x,y); 
						currX = x; 
						currY = y; 
						canEndTurn = true;
						godLight(x,y); 
					}
					
				}
		}

	}

	public void endTurn() 
	{ 
		nolight(); 
    	canEndTurn = false;
    	alMoved = false; 
    	isFTurn = !isFTurn;
    	for ( int i = 0; i < 8; i++)
		{ 
			for ( int x = 0; x < 8; x++)
				{ 
    				if (bits[i][x] != null)
    					{ 
    						bits[i][x].doneCapturing(); 
    					}
    			} 
    	} 

	}

	public String winner()
	{ 
		int fCounter = 0; 
		int wCounter = 0; 
		for ( int i = 0; i < 8; i++)
		{ 
			for ( int x = 0; x < 8; x++)
				{ 
					if(bits[i][x] != null)
					{
						if(bits[i][x].isFire())
							fCounter += 1;  
						else 
							wCounter += 1; 

					}

				}
		}

		if ( fCounter > 0 && wCounter == 0)
			{ 
				return "fire"; 
			}
		else if( wCounter > 0 && fCounter == 0)
			{ 
				return "water"; 
			}
		else if( fCounter == 0 && wCounter == 0)
			{ 
				return "no one"; 
			}
		return null; 


	}

}