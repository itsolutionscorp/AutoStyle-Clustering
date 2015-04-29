public class Board{
	//Location of pieces with names
	private Piece [][] pieces;//double array of pieces with coordiantes
	private boolean who=true;//keeps track of whose turn:true for fire, false for water
	private Piece selectedPiece=null;//holder for selected piece, null if no piece selected 
	private boolean hasMoved=false;//indicator for whether player has moved a piece
	private int selectedx;
	private int selectedy;
	private boolean hasExploded=false;
	public Board(boolean shouldBeEmpty)
	{
		if (!shouldBeEmpty)
		{
			pieces=new Piece[8][8];
			this.firePlacement(pieces);
			this.waterPlacement(pieces);
		}
		else
		{
			pieces=new Piece[8][8];
		}
	}
	
	private void firePlacement(Piece[][] pieces)
	{
		for (int counter=0;counter<7;counter+=2)
		{
			new Piece(true,this,counter,0,"pawn");
			new Piece(true,this,counter,2,"bomb");
			new Piece(true,this,counter+1,1,"shield");
		}

	}

	private void waterPlacement(Piece[][] pieces)
	{
		for (int counter=1;counter<8;counter+=2)
		{
			new Piece(false,this,counter,7,"pawn");
			new Piece(false,this,counter,5,"bomb");
			new Piece(false,this,counter-1,6,"shield");
		}
	}

	private void drawBoard(int N)
	{
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if(pieces[i][j]!=null){
                Piece currentPiece=pieces[i][j];
                if(currentPiece.isFire())
                	drawFire(currentPiece,i,j);
                else
                	drawWater(currentPiece,i,j);
                }
            }
            }
        	}
		

	private void drawFire(Piece current,int xcoord,int ycoord)
	{
		if (current.isKing())
		{
			if(current.isBomb())
				StdDrawPlus.picture(xcoord+0.5,ycoord+.5,"img/bomb-fire-crowned.png",1,1);
			else if(current.isShield())
				StdDrawPlus.picture(xcoord+0.5,ycoord+.5,"img/shield-fire-crowned.png",1,1);
			else
			{
				StdDrawPlus.picture(xcoord+0.5,ycoord+.5,"img/pawn-fire-crowned.png",1,1);
			}
		}
		else
		{
			if(current.isBomb())
			StdDrawPlus.picture(xcoord+0.5,ycoord+0.5,"img/bomb-fire.png",1,1);
			else if(current.isShield())
				StdDrawPlus.picture(xcoord+0.5,ycoord+0.5,"img/shield-fire.png",1,1);
			else
				StdDrawPlus.picture(xcoord+0.5,ycoord+0.5,"img/pawn-fire.png",1,1);
		}
	}

	private void drawWater(Piece current,int xcoord,int ycoord)
	{
		if(current.isKing())
		{

			if(current.isBomb())
				StdDrawPlus.picture(xcoord+0.5,ycoord+.5,"img/bomb-water-crowned.png",1,1);
			else if(current.isShield())
				StdDrawPlus.picture(xcoord+0.5,ycoord+.5,"img/shield-water-crowned.png",1,1);
			else
			{
				StdDrawPlus.picture(xcoord+0.5,ycoord+.5,"img/pawn-water-crowned.png",1,1);
			}
		}
		else
		{
			if(current.isBomb())
			StdDrawPlus.picture(xcoord+0.5,ycoord+0.5,"img/bomb-water.png",1,1);
			else if(current.isShield())
				StdDrawPlus.picture(xcoord+0.5,ycoord+0.5,"img/shield-water.png",1,1);
			else
				StdDrawPlus.picture(xcoord+0.5,ycoord+0.5,"img/pawn-water.png",1,1);
		}
	}

	public Piece pieceAt(int x, int y)
	{
		if(x<0||x>7||y<0||y>7)
			return null;
		else
		{
			return pieces[x][y];
		}
	}

	public boolean canSelect(int x,int y)
	{
		if(pieceAt(x,y)==null)//tests to see if space selected is empty
		{

			if(selectedPiece!=null)//tests to see if player has chosen a piece 
			{
				if(selectedy-y>0 && !selectedPiece.isKing() && selectedPiece.isFire())
					{
					return false; //checks to see if fire piece is moving backwards
				}
				if(selectedy-y<0 && !selectedPiece.isKing() && !selectedPiece.isFire())
					
					return false;//checks to see if water piece is moving backwards
				if(validMove(selectedx,selectedy,x,y) && !hasMoved)
				{
					if(selectedPiece.isBomb()&&selectedPiece.hasCaptured())
						return false;
					return true; //tests to see if move is ok and piece has not captured before
				}
				if(validCapture(selectedx,selectedy,x,y))
				{
					if(selectedPiece.isBomb()&&selectedPiece.hasCaptured())
						return false;//tests to see if its a bomb and has already captured, preventing a multicapture
					return true;//tests to see if capture is ok
				}
			}
		}
		else if (pieceAt(x,y).isFire()==who)//verifies right eam
		{
			if(hasExploded)//sees if the bomb has exploded
				return false;
			if(selectedPiece==null)//sees if piece has been chosen already. if not, then it is a valid move to pick up a piece.
				return true;
			if(!hasMoved)
			{
				selectedPiece=pieceAt(x,y);//sees if a move has been made. otherwise good
				return true;
			}
			
		}
		return false;
	}
	public void select(int x, int y)
	{
		Piece potential=pieceAt(x,y);
		if(potential==null)//checks to see if target is null
			{
				selectedPiece.move(x,y);//calls move
				if(selectedPiece.isBomb()&&selectedPiece.hasCaptured())//checks to see if moving piece has exploded
				{
					selectedPiece=null;//removes references when bomb exploded
					hasExploded=true;//sets a flag up to let board know that a bomb exploded
									//necessary as canSelect relies on selectedPiece 
				}
				hasMoved=true;//sets a flag to let board know a piece was moved and no other move is allowed 
			}
			else
			{
		selectedPiece=potential; 
		selectedx=x;
		selectedy=y;
/*		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);*/
		/*if(selectedPiece.isFire())
		{
			drawFire(selectedPiece,x,y);
		}
		else
		{
			drawWater(selectedPiece,x,y);
		}*/
	}

	
	}
	private boolean validCapture(int x,int y,int x1,int y1)
	{
		if(x1<0||x1>7||y1<0||y1>7)
		{
			return false; //tests for out of bound
		}
		int diffx=Math.abs(x1-x);
		int diffy=Math.abs(y1-y);
		if(diffx!=2 && diffy!=2)
			return false; //tests to make sure movement is two spaces exactly
		Piece potential=pieceAt((x1-x)/2+x,(y1-y)/2+y); //gets space in between initial and destination. should be the potential capture piece

		if(potential==null)
		{
			return false;//false if no piece is present to be capured
		}
		if(potential.isFire()!=who && pieceAt(x1,y1)==null)
			return true; //tests right eam and destination is clear
		if(hasExploded)
			return false;
		return false;
	}
	public Piece remove(int x,int y)
	{
		if(x<0||x>7||y<0||y>7)
		{
			System.out.println("Removal out of bounds");
			return null;
		}
		if (pieceAt(x,y)==null)
		{
			System.out.println("No piece present@"+x+"sdf"+y);
			return null;
		}
		Piece temp=pieceAt(x,y);
		pieces[x][y]=null;
		return temp;
	}
	public String winner()
	{
		boolean firepresent=false;
		boolean waterpresent=false;
		for (int counterx=0;counterx<8;counterx++)
		{
			for(int countery=0;countery<8;countery++)
			{
				if (pieceAt(counterx,countery)!=null)
				{
					if(pieceAt(counterx,countery).isFire())
					{
						firepresent=true;
					}
					if(!pieceAt(counterx,countery).isFire())
						waterpresent=true;
				}
			}
		}
		if(firepresent && waterpresent)
			return null;
		if(firepresent)
			return "Fire";
		if(waterpresent)
			return "Water";
		return "No one";
	}
	public boolean canEndTurn()
	{
		return hasMoved;
	}

	public void endTurn()
	{
		who=!who;
		if(selectedPiece!=null)
		{
		selectedPiece.doneCapturing();
		}
		selectedPiece=null;
		hasMoved=false;
		hasExploded=false;
	}
	private boolean validMove(int x,int y,int x1,int y1)
	{
		if(x1<0||x1>7||y1<0||y1>7)
			return false;
		int diffx=Math.abs(x-x1);
		int diffy=Math.abs(y-y1);

		if(diffx==1 && diffy==1)
		{
			return true;
		}
		if(hasExploded)
			return false;
		return false;
	}

	public void place(Piece p,int x,int y)
	{
		if(p==null||x<0||x>7||y<0||y>7)
			return;
		outerloop:
		for(int counterx=0;counterx<8;counterx++)
 		{
 			for(int countery=0;countery<8;countery++)
 			{
 
 				if(p==pieceAt(counterx,countery))
 				{
 					pieces[counterx][countery]=null;
 					break outerloop;
 				}
 			}
 		}
		pieces[x][y]=p;
		selectedx=x;
		selectedy=y;
	
	}
    public static void main(String[] args)
    {
    	int N=8;
    	StdDrawPlus.setXscale(0,N);
    	StdDrawPlus.setYscale(0,N);
    	Board gameboard=new Board(false);
    	while (true)
    	{
    		gameboard.drawBoard(N);
    		if(StdDrawPlus.mousePressed())
    		{
    			double x=StdDrawPlus.mouseX();
    			double y=StdDrawPlus.mouseY();
    			if(gameboard.canSelect((int) x,(int) y))
    			{
    			gameboard.select((int) x,(int) y);
    			}
    			if(gameboard.winner()!=null)
    			{
    				System.out.println(gameboard.winner());
    				return;
    			}

    		}
    		if(StdDrawPlus.isSpacePressed())
    		{
    			if(gameboard.canEndTurn())
    				gameboard.endTurn();
    		}
    		StdDrawPlus.show(1);
    	}
    
    }
}