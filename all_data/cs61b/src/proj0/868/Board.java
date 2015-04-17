public class Board
{
	private  Piece[][] pieces=new Piece[8][8];//hh
	private  boolean empty,turnFire,moved;	
	private  int selectedX,selectedY,selectedEmptyX,selectedEmptyY;
	public Board(boolean shouldBeEmpty)
	{	int y;
		empty=shouldBeEmpty;
		turnFire=true;
		moved=false;
		selectedX=-1;
		selectedY=-1;
		selectedEmptyX=-1;
		selectedEmptyY=-1;
		if(!empty)
		{
			for (int x=0;x<8;x++)
			{
				for(y=0;y<8;y++)
				{
					if ((x+y)%2==0)
					{
						if (y==0)
						{
							pieces[y][x]=new Piece(true,this,x,y,"pawn");//
						}
						else if(y==1)
						{
							pieces[y][x]=new Piece(true,this,x,y,"shield");
						}
						else if(y==2)
						{
							pieces[y][x]=new Piece(true,this,x,y,"bomb");
						}
						else if(y==5)
						{
							pieces[y][x]=new Piece(false,this,x,y,"bomb");
						}
						else if(y==6)
						{
							pieces[y][x]=new Piece(false,this,x,y,"shield");
						}
						else if(y==7)
						{
							pieces[y][x]=new Piece(false,this,x,y,"pawn");
						}
					}
				}
			}
		}
	}
	
	public Piece pieceAt(int x, int y)
	{
		if ((x<8 &&y<8) && (x>=0 && y>=0))
		{
			return pieces[y][x];
		}
		return null;
	}
	public boolean canSelect(int x, int y)
	{
		
		if (pieces[y][x]!=null)
		{	
			if(selectedEmptyY==-1)
			{
				if ((turnFire && pieces[y][x].isFire())||(!turnFire && !pieces[y][x].isFire()))
					{
						return true;
					}
				 
				return false;
			}
		}
		else
		{	
			if(selectedEmptyX!=-1 && pieces[selectedEmptyY][selectedEmptyX]!=null)//completed a move
			{
				selectedX=selectedEmptyX;
				selectedY=selectedEmptyY;
				selectedEmptyX=x;
				selectedEmptyY=y;
				return canSelect(x,y);
			}
			if(selectedX!=-1)
			{
				if (validMove(selectedX,selectedY,x,y))
				{   
					
					return true;
				}
			}
		}
			return false;
		}	
		
	private boolean validMove(int xi, int yi, int xf, int yf)
	{	boolean team=false;
		if (pieces[yi][xi]!=null)
		{team=pieces[yi][xi].isFire();
		if(Math.abs(yf-yi)==2&&Math.abs(xf-xi)==2)
		{	
			if (pieces[yi][xi].isKing())
			{
				if (pieces[(int)((yi+((yf-yi)/2)))][(int)((xi+((xf-xi)/2)))]!=null && pieces[(int)((yi+((yf-yi)/2)))][(int)((xi+((xf-xi)/2)))].isFire()!=team &&pieces[yf][xf]==null)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else if (team)
			{
				if ((yf-yi==2) && pieces[(int)((yi+((yf-yi)/2)))][(int)((xi+((xf-xi)/2)))]!=null  && pieces[(int)((yi+((yf-yi)/2)))][(int)((xi+((xf-xi)/2)))].isFire()==false && pieces[yf][xf]==null)
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
				if ((yf-yi==-2) && pieces[(int)((yi+((yf-yi)/2)))][(int)((xi+((xf-xi)/2)))]!=null && pieces[yf][xf]==null && pieces[(int)((yi+((yf-yi)/2)))][(int)((xi+((xf-xi)/2)))].isFire()==true)
				{
					return true;
				} 
				else
				{
					return false;
				} 
			}
		}
		

	else if(Math.abs(yf-yi)==1&&Math.abs(xf-xi)==1&&!this.pieceAt(xi,yi).hasCaptured()&&!moved)
	{	
		this.pieceAt(xi,yi).doneCapturing();

			if (pieces[yi][xi].isKing())
			{
				if (pieces[yf][xf]==null)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else if (team)
			{
				if ((yf-yi==1)&&(pieces[yf][xf]==null))
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
				if ((yf-yi==-1) && pieces[yf][xf]==null)
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
		return false;
	}
	public void select(int x, int y)
	{
			if(selectedX==-1 || pieces[y][x]!=null)
			{
				selectedX=x;
				selectedY=y;
			}
			else
			{
				selectedEmptyX=x;
				selectedEmptyY=y;
				if (canMove())
				{
					getselectedPiece().move(selectedEmptyX,selectedEmptyY);
					selectedX=selectedEmptyX;
    				selectedY=selectedEmptyY;
    				moved=true;
				}
			}
	}
	public void place(Piece p,int x, int y)
	{
		if (x>=8 || x<0 ||y>=8 ||y<0 || p==null)
		{
			return;
		}
		else
		{
			pieces[y][x]=p;
		}


	}
	private boolean canMove()
	{
		if(selectedEmptyX!=-1&&selectedX!=-1)
		{
			return true;
		}
		return false;
	}
	public Piece remove(int x, int y)
	{
		if((x>=8||y>=8)||(x<0 || y<0))
		{	
			return null;
		}
		Piece p=pieces[y][x];
		pieces[y][x]=null;
		return p;

	}
	public boolean canEndTurn()
	{
	 	if(selectedEmptyX==-1||!moved)
	 	{
	 		return false;
	 	}
	 	return true;

	}
	public void endTurn()
	{
		turnFire=!turnFire;
		if (getselectedPiece()!=null)
		{
			getselectedPiece().doneCapturing();
		}
		selectedX=-1;
		selectedY=-1;
		selectedEmptyX=-1;
		selectedEmptyY=-1;
		moved=false;
	}
	public String winner()
	{
		int x,y,fireCount=0,waterCount=0;
		for(x=0;x<8;x++)
		{
			for (y=0;y<8;y++)
			{
			 	if (pieces[y][x]!=null)
			 	{	
			 		if(pieces[y][x].isFire())
					{
						fireCount+=1;
					}
					else
					{
						waterCount+=1;
					}
				}
			}
		}
		if (fireCount==0 && waterCount==0)
		{
			return "No one";
		}
		else if(fireCount==0)
		{
			return "Water";
		}
		else if(waterCount==0)
		{
			return "Fire";
		}
		else
		{
			return null;
		}

	}
	private void drawBoard(int N) {
		String s="";
        for (int x = 0; x < N; x+=1) {
            for (int y = 0; y < N; y+=1) {
                if ((x + y) % 2 == 0) 
                {   if (selectedX!=-1&&x==selectedX && y==selectedY)
                	{
                		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                	}
                	else
                	{
                		StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                	}
                		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                	
           			if (!empty)
           			{
           				if(pieces[y][x]!=null)
           				{
           					if(pieces[y][x].isFire())
           					{
           						if(pieces[y][x].isBomb())
           						{
           							s="img/bomb-fire";
           						}
           						else if(pieces[y][x].isShield())
           						{
           							s="img/shield-fire";
           						}
           						else
           						{
           							s="img/pawn-fire";
           						}
           					}
           					else
           					{
           						if(pieces[y][x].isBomb())
           						{
           							s="img/bomb-water";
           						}
           						else if(pieces[y][x].isShield())
           						{
           							s="img/shield-water";
           						}
           						else
           						{
           							s="img/pawn-water";
           						}
           					}
           					if (pieces[y][x].isKing())
           					{
           						s+="-crowned";
           					}
           					StdDrawPlus.picture(x + .5, y + .5, s+".png", 1, 1);
           				}
           			}
                
                }
                else                  
                {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                	StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                }
                
            }
        }
    }
    private Piece getselectedPiece()
    {
    	return pieces[selectedY][selectedX];
    }
    private void moveTo(Piece p)
    {
    	p.move(selectedEmptyX,selectedEmptyY);
    	selectedX=selectedEmptyX;
    	selectedY=selectedEmptyY;
    	moved=true;
    }
	public static void main(String args[])
	{	int N=8;
		double x=-1,y=-1;
		StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Piece p;
        Board b=new Board(false);	
        while (true)
        {	
			b.drawBoard(N);
			if(StdDrawPlus.mousePressed())
       		{
       			x=StdDrawPlus.mouseX();
       			y=StdDrawPlus.mouseY();

       			if(x<8 && y<8)
       			{  			
       				if (b.canSelect((int)x,(int)y))
       				{	
       					b.select((int)x,(int)y);
       				}
       			}
       		}
       		if(StdDrawPlus.isSpacePressed())
       			{
       				if(b.canEndTurn())
       				{
       					b.endTurn();
       				}
       			}
       			StdDrawPlus.show(5);
       		
       	}
	}
}