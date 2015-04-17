public class Board
{
	private  Piece[][] pieces;
	private  int turncount;
	private boolean fireTurn;
	private int hasMovedCounter;
	private Piece selected;
	private int selectedX;
	private int selectedY;



	public static void main(String args[])
	{
		

		

		
		Board b= new Board(false);

		while(b.winner()==null)
		{
			StdDrawPlus.show(15);
			b.drawBoard();
			if(StdDrawPlus.mousePressed())
			{

				double x= StdDrawPlus.mouseX();
				double y= StdDrawPlus.mouseY();
				int a= (int) x;
				int c= (int) y;
				b.drawBoard();
				if(b.canSelect(a, c))
				{
					b.select(a, c);

				}
		

			}
			if(StdDrawPlus.isSpacePressed())
			{
				if(b.canEndTurn())
					b.endTurn();
				b.drawBoard();
			}

			

		}
		b.drawBoard();
		StdDrawPlus.show(15);
		System.out.println(b.winner());
		



	}
	public Board(boolean shouldBeEmpty)
	{
		pieces= new Piece[8][8];
		fireTurn=true;
		hasMovedCounter=0;
		selectedX=-1;
		selectedY=-1;

		if(!shouldBeEmpty)
		{
			for(int i=0; i<8; i++)
			{
				for(int j=0; j<8; j++)
				{
					if(j==0 && i%2==0)
						pieces[i][j]= new Piece(true, this, i, j, "Pawn");
					else if(j==1 && i%2==1)
						pieces[i][j]= new Piece(true, this, i, j, "Shield");
					else if(j==2 && i%2==0)
						pieces[i][j]= new Piece(true, this, i, j, "Bomb");
					else if(j==5 && i%2==1)
						pieces[i][j]= new Piece(false, this, i, j, "Bomb");
					else if(j==6 && i%2==0)
						pieces[i][j]= new Piece(false, this, i, j, "Shield");
					else if(j==7 && i%2==1)
						pieces[i][j]= new Piece(false, this, i, j, "Pawn");
					else
						pieces[i][j]= null;
				}
			}
		}


	}
	private void drawBoard()
	{
		StdDrawPlus.setXscale(0, 8);
		StdDrawPlus.setYscale(0, 8);
		for (int i = 0; i < 8; i++) 
		{
            for (int j = 0; j < 8; j++) 
            {
                if ((i + j) % 2 == 0) 
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                
                	if(pieces[i][j]!=null)
                	{
                		
		            	if(pieces[i][j].isFire())
		            	{
		            		if(pieces[i][j].isBomb())
		            		{
		            			if( pieces[i][j].isKing() )
		            				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png",1,1);
		            			else
		            				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png",1,1);
		            		}
		            		else if(pieces[i][j].isShield())
		            		{
		            			if(pieces[i][j].isKing())
		            				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
		            			else
		            				StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png",1 ,1);
		            		}	
		            		else
		            		{
		            			if(pieces[i][j].isKing())
		            				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png",1 ,1);
		            			else
		            			StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png",1, 1);
		           
		            		}
		            	}
		            	else
		            	{
		            		if(pieces[i][j].isBomb())
		            		{
		            			if(pieces[i][j].isKing())
		            				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png",1 ,1);
		            			else
		            				StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png",1 ,1);
		            		}
		            		else if(pieces[i][j].isShield())
		            		{
		            			if(pieces[i][j].isKing())
		            				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
		            			else
		            				StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
		            		}
		            		else
		            		{
		            			if(pieces[i][j].isKing())
		            				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png",1 ,1);
		            			else
		            				StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png",1 ,1);
		           
		           	 		}
		            	}
                	}
                
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                
                }
            }
	}
	public Piece pieceAt(int x, int y)
	{
		if(x>7||x<0 || y>7 || y<0)
			return null;
		return pieces[x][y];

	}

	private boolean validMove(int xi, int yi, int xf, int yf)
	{
		if(pieceAt(xi, yi)==null)
			return false;
		if(xf>7||xf<0|| yf>7|| yf<0)
			return false;
		if(xi==xf && yi==yf)
			return true; 
		if(Math.abs(xf-xi)!=Math.abs(yf-yi) || pieceAt(xf, yf)!=null)
			return false;

		if(!pieceAt(xi,yi).isKing())
		{
			if(pieceAt(xi,yi).isFire())
			{
				if(yf<=yi)
					return false;
				else
				{
					if(Math.abs(xf-xi)==1)
						return true;
					else if(Math.abs(xf-xi)==2)
					{
						if(pieceAt((xf+xi)/2, (yf+yi)/2)!=null)
							return true;
						else
							return false;
					}
					else
						return false;
				}

					
			}
			else
			{
				if(yf>=yi)
					return false;
				else
				{
					if(Math.abs(xf-xi)==1)
						return true;
					else if(Math.abs(xf-xi)==2)
					{
						if(pieceAt((xf+xi)/2, (yf+yi)/2)!=null)
							return true;
						else
							return false;
					}
					else
						return false;
				}
			}
		}
		else
		{
			if(Math.abs(xf-xi)==1 && Math.abs(yf-yi)==1)
				return true;
			else if(Math.abs(xf-xi)==2 && Math.abs(xf-xi)==2)
			{
				if(pieceAt((xf+xi)/2, (yf+yi)/2)!=null)
					return true;
				else
					return false;
			}
			else
				return false;
		}

	}
	public boolean canSelect(int x, int y)
	{
		if(pieceAt(x, y)!=null)
		{
			if(pieceAt(x, y).isFire()!=fireTurn)
			{
				return false; 
			}
			else
			{
				if(pieceAt(selectedX, selectedY)==null)
				{
					return true;
				}
				else
				{
					if(hasMovedCounter==0)
						return true;
					else
					{
					
						return false;
					}
				}
			}

		}
		else
		{
			if(pieceAt(selectedX, selectedY)==null)
				return false;
			if(validMove(selectedX, selectedY, x, y) && hasMovedCounter==0)
				return true;
			else if(validMove(selectedX, selectedY, x, y) && (Math.abs(selectedX-x)==2 && Math.abs(selectedY-y)==2) && pieceAt(selectedX, selectedY).hasCaptured())
				return true;
			else
				return false;
		}


	}
	public void select(int x, int y)
	{
		if(pieceAt(x, y)==null)
		{
			selected.move(x, y);
			selectedX=x;
			selectedY=y;
			hasMovedCounter++;
		}
		else
		{
			selectedX=x;
			selectedY=y;
			selected= pieceAt(selectedX, selectedY);

			
		}

	}

	public void place(Piece p, int x, int y)
	{
		if(x>7 || y>7)
			return;
		if(p==null)
			return;
		pieces[x][y]=p;
	} 
	public Piece remove(int x, int y)
	{
		if(x>7 || y>7)
		{
			System.out.println("Position selected is off the board");
			return null;
		}
		if(pieces[x][y]==null)
		{
			System.out.println("There is no piece at the position");
			return null;

		}
		Piece a= pieceAt(x,y);
		pieces[x][y]=null;
		return a;

	}
	public boolean canEndTurn()
	{
		if(hasMovedCounter>0)
			return true;
		else
			return false;

	}
	public void endTurn()
	{
		fireTurn=!fireTurn;
		hasMovedCounter=0;
		if(pieceAt(selectedX, selectedY)!=null)
			if(pieceAt(selectedX, selectedY).hasCaptured())
				pieceAt(selectedX, selectedY).doneCapturing();
		selectedX=-1;
		selectedY=-1;

	}
	public String winner()
	{
		int fireCount=0;
		int waterCount=0;

		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				if(pieces[i][j]!=null)
				{
					if(pieces[i][j].isFire())
						fireCount++;
					else
						waterCount++;

				}

			}
		}
		if(fireCount==0 && waterCount==0)
			return "No One";
		else if(fireCount==0 && waterCount!=0)
			return "Water";
		else if(fireCount!=0 && waterCount==0)
			return "Fire";
		else
			return null;

	}
	

















}