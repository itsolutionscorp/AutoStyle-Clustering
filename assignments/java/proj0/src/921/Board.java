public class Board{
	private boolean shouldBeEmpty;
	private Piece[][] pieces=new Piece[8][8];
	private boolean isFiresTurn=true;//fire always goes first
	private Piece selectedPiece=null;
	private int selectedx=7;
	private int selectedy=0;
	private boolean isDone=false;

	private void drawBoard(){
		double width=.5/8; //pixel width of the squares
		StdDrawPlus.filledSquare(.5,.5,.5);
		for(int i=0;i<64;i++)
		{
			int height=(i-(i%8))/8;
			int iWidth=(i%8);
			if (height==selectedy && iWidth==selectedx)
			{
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
			else if(i%2==height%2)
				{StdDrawPlus.setPenColor(StdDrawPlus.GRAY);}
			else{
				StdDrawPlus.setPenColor(StdDrawPlus.RED);	
			}
			//StdDrawPlus.filledSquare(width,width,width);
			StdDrawPlus.filledSquare(iWidth*width*2+width,height*width*2+width,width);}	
	}

	private void drawPieces(){
		double width=.5/8;
		for(int i=0;i<64;i++)
		{
			int y=(i-(i%8))/8;
			int x=(i%8);
			Piece curr=pieces[x][y];
			if(curr==null){}
			else{
				String team="-fire";
				if(curr.isFire()==false)
					{team="-water";}
				String isCrowned="";
				if(curr.isKing()==true)
					{isCrowned="-crowned";}
				String type="pawn";
				if(curr.isShield()){type="shield";}
				else if (curr.isBomb()){type="bomb";}
				String fileName="img/"+type+team+isCrowned+".png";
				StdDrawPlus.picture(x*width*2+width, y*width*2+width, fileName, .15,.15);
			}
	}
	}
	public Board(boolean isEmpty){
		shouldBeEmpty=isEmpty;
		//////////////////////LOAD PIECES INTO ARRAY///////////////////////////
		if(!isEmpty){	
			for(int i=0;i<64;i++)
			{
			int height=(i-(i%8))/8;
			int iWidth=(i%8);
				Piece curr;
				if((i%2)==(height%2))
				{
					if(height==0)
					{
						curr=new Piece(true, this, iWidth, height, "pawn");
					}
					else if(height==7)
					{
						curr=new Piece(false, this, iWidth, height, "pawn");
					}
					else if(height==1)
					{
						curr=new Piece(true, this, iWidth, height, "shield");
					}
					else if(height==6)
					{
						curr=new Piece(false, this, iWidth, height, "shield");
					}
					else if(height==2)
					{
						curr=new Piece(true, this, iWidth, height, "bomb");
					}
						else if(height==5)
					{
						curr=new Piece(false, this, iWidth, height, "bomb");
					}
					else{curr=null;}
				}
				else{curr=null;}
				pieces[iWidth][height]=curr;
			}
		}
		//////////////////////RENDER BOARD AND PIECES////////////////////////
		//eventHandler();
		drawBoard();
		drawPieces();
		if (isEmpty==false){drawPieces();}
	}

	public String winner(){
		int numFire=0;
		int numWater=0;
		for(int w=0;w<8;w++)
		{
			for(int h=0;h<8;h++)
			{
				if(pieces[w][h]!=null)
				{
					if(pieces[w][h].isFire())
						numFire++;
					else{numWater++;}
				}	
			}
		}
		if(numFire==0 && numWater==0){return "No one";}
		else if(numFire==0){return "Water";}
		else if (numWater==0){return "Fire";}
		return null;
	}

	private int roundToRange(double x)
	{
		int counter=0;
		for(double i=1;i<9;i++,counter++)
		{
			if(i/8>x)
				{return counter;}
		}
		return -1;
	}

	private void eventHandler(){
		boolean mouseLifted=true;
		boolean spaceLifted=true;
		while(isDone==false){
		if(StdDrawPlus.mousePressed()==true && mouseLifted==true)
		{
			int x=roundToRange(StdDrawPlus.mouseX());
			int y=roundToRange(StdDrawPlus.mouseY());
			if (x>-1 && y>-1)
			{
			if (canSelect(x,y))
			{
				select(x,y);
			}
			}
			mouseLifted=false;
		}
		else if (StdDrawPlus.mousePressed()==false){mouseLifted=true;}
		if(StdDrawPlus.isSpacePressed()==true && spaceLifted==true)
		{
			if(canEndTurn())
			{
				endTurn();
			}
			spaceLifted=false;
		}
		else if (StdDrawPlus.isSpacePressed()==false){spaceLifted=true;}
		drawBoard();
		drawPieces();
		StdDrawPlus.show(10);
		}
	}

	public static void main(String args[])
	{
		blah.hasMoved=false;
		Board thisBoard=new Board(false);
		thisBoard.eventHandler();
	}

	private boolean validMove(int xi,int yi,int xf,int yf)
	{
		int direction=-1;
		if (isFiresTurn){direction=1;}
		if((xf>=0 && xf<=7 && yf>=0 && yf<=7) && (pieces[xf][yf]==null))
		{
		Piece curr=pieces[xi][yi];
		if(curr==null){return false;}
		boolean isKing=curr.isKing();
			if (Math.abs(xf-xi)==1)
			{
				if(isKing)
				{
					if(Math.abs(yf-yi)==1)
					{
						return true;
					}
				}
				else
				{
					if(yf-yi==direction)
					{
						return true;
					}
				}
			}
			else if (Math.abs(xf-xi)==2)
			{
				if(pieces[(xi+xf)/2][(yi+yf)/2]!=null && curr.isFire()!=pieces[(xi+xf)/2][(yi+yf)/2].isFire())
				{
					if(isKing)
					{
						if(Math.abs(yf-yi)==2)
						{
							return true;
						}
					}
					else
					{
						if(yf-yi==2*direction)
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public void select(int x, int y)
	{
		if (selectedPiece==null || (validMove(selectedx,selectedy,x,y)==false) && (x>=0 && x<=7 && y>=0 && y<=7) && pieces[x][y]!=null)
		{
			selectedPiece=pieces[x][y];
		}
		else
		{
			selectedPiece.move(x,y);
			if (winner()!=null)
			{
				System.out.println("THE WINNER IS "+winner()+"!!!");
				isDone=true;
			}
			else{isDone=false;}
		}
		selectedx=x;
		selectedy=y;
	}

	public boolean canSelect(int x, int y)
	{
		if(selectedPiece==null || (blah.hasMoved==false && selectedPiece.hasCaptured()==false))
		{
			if (pieces[x][y]!=null && pieces[x][y].isFire()==isFiresTurn)
			{
				//selectedPiece=pieces[x][y];
				return true;
			}
		}
		if(selectedPiece!=null)
		{
			if (validMove(selectedx,selectedy,x,y))
			{
				if(blah.hasMoved==false)
				{
					return true;
				}
				else if (Math.abs(selectedx-x)==2 && Math.abs(selectedy-y)==2 && selectedPiece.hasCaptured())
				{
					return true;
				}
			}
			else if(pieces[x][y]!=null && pieces[x][y].isFire()==isFiresTurn && blah.hasMoved==false)
				{return true;}
		}
		return false;
	}

	public boolean canEndTurn()
	{
		if (blah.hasMoved)
		{
			return true;
		}
		return false;
	}

	public void endTurn()
	{
		if (isFiresTurn)
		{
		selectedx=0;
		selectedy=7;
		}
		else{selectedx=7;selectedy=0;}
		isFiresTurn=!isFiresTurn;//
		blah.hasMoved=false;
		if(selectedPiece!=null){
		selectedPiece.doneCapturing();}
		selectedPiece=null;
	}
	
	private int[] getXY(Piece p)
	{
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				if (pieces[i][j]==p)
					{return new int[] {i,j};}
			}
		}
		return new int[] {-1,-1};
	}

	public Piece pieceAt(int x, int y)   //works
	{
		return pieces[x][y];
	}

	public void place(Piece p, int x, int y)	//doesn't work
	{
		if (x>=0 && x<=7 && y>=0 && y<=7 && p!=null)
		{
		remove(getXY(p)[0],getXY(p)[1]);
		remove(x,y);
		pieces[x][y]=p;
		}
	}

	public Piece remove(int x, int y) //works
	{
		if (x>=0 && x<=7 && y>=0 && y<=7)
		{
			if(pieces[x][y]!=null)
			{
				Piece curr=pieces[x][y];
				pieces[x][y]=null;
				return curr;
			}
			System.out.println("Error: Attempted to remove null");
			return null;
		}
		System.out.println("Error: Attempted to remove out-of-bounds item");
		return null;
	}

}