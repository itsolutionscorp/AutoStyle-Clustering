// 	Dhrushil Badani
public class Board
{

	private boolean empty=false;
	private Piece[][] coords = new Piece[8][8];
	private int no_fire=0, no_water=0, selected_x, selected_y;
	private boolean isFireTurn=true, hasSelected=false, hasMoved=false;

	
	public Board (boolean shouldBeEmpty)
	{
		this.empty=shouldBeEmpty;
		int x,y;
		boolean isFire;
		String type="";
		String[] piece_type={"pawn","shield","bomb","","","bomb","shield","pawn"};
		for (y=0; y<=7; y=y+1)
		{
			for (x=0; x<=7; x=x+1)
			{
				if (y<=2)
					isFire = true;
				else
					isFire = false;

				if ( y!=3 && y!=4 && ( (x+y)%2==0 ) && this.empty==false)
					{
						Piece p=new Piece (isFire, this, x, y, piece_type[y]);
						place(p,x,y);
					}
				else
					{
						coords[x][y]=null;
					}
			}

		}

	}




	
	private void drawBoard()
		{
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		int x,y;
		String path="", type="";
		Piece p;
		for (y=0;y<=7;y=y+1)
		{
			for(x=0;x<=7;x=x+1)
			{


				if ( (x+y)%2==0 )
					{
						StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
					}
				else
					{
						StdDrawPlus.setPenColor(StdDrawPlus.RED);
					}
				StdDrawPlus.filledSquare((x+0.5), (y+0.5), 0.5);

				p=pieceAt(x,y);
				if (p!=null)
					{
						path="img/";
						type="pawn";
						if (p.isBomb())
							{type="bomb";}
						else if (p.isShield())
							{type="shield";}
						path=path+type+"-";

						if (p.isFire())
							{path+="fire";}
						else
							{path+="water";}

						if (p.isKing())
							{path+="-crowned";}

						path+=".png";

						StdDrawPlus.picture(x + .5, y + .5, path, 1, 1);

						

					}


			}
		}

				
		}

	public Piece pieceAt(int x, int y)
	{
		if(x>7 || y>7 || x<0 || y<0)
			{return null;}
		return coords[x][y];
	}

	// xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
		private boolean validMove (int xi, int yi, int xf, int yf)
		{
			if(xf>7 || yf>7 || xf<0 || yf<0) //check that final positions are not out of bound
				{return false;}

			int del_x=xf-xi; int del_y=yf-yi;
			int adel_x=Math.abs(del_x); int adel_y=Math.abs(del_y);
			Piece pi=pieceAt(xi,yi);
			Piece pf=pieceAt(xf,yf);
			Piece pm;
			int xm, ym;
			xm= (xi+xf)/2; ym=(yi+yf)/2;//getting the middle coords	
			pm= pieceAt((int) xm, (int) ym);//getting the middle piece
			boolean cond_capture =  (pm!=null && (pi.isFire() != pm.isFire()));

			
			if (pf!=null || pi==null) //final pos should be empty, initial pos should have apiece
				{return false;}

			if (pi.isKing()==true) //for a King Piece
				{
				if (adel_x==1 && adel_y==1 && pi.hasCaptured()==false)// if it's a non-capture move
					{	pi.doneCapturing();
						return true;}
				else if (adel_x==2 && adel_y==2) //if it's a capture move
					{return cond_capture;
					/* xm= (xi+xf)/2; ym=(yi+yf)/2;//getting the middle coords	
					pm= pieceAt(xm,ym);//getting the middle piece 
					return (pm!=null && (pi.isFire() != pm.isFire())); //check if the capture is valid */
					}
				else //invalid move, neither capture, nor non-capture
					{return false;}
				}

			else if (pi.isFire()==true)//for a Fire Piece, similar ^
				{
				if (adel_x==1 && del_y==1 && pi.hasCaptured()==false)
					{	pi.doneCapturing();
						return true;}
				else if (adel_x==2 && del_y==2)
					{return cond_capture;
					/* xm= (xi+xf)/2; ym=(yi+yf)/2;//getting the middle coords	
					pm= pieceAt(xm,ym);//getting the middle piece 
					return (pm!=null && (pi.isFire() != pm.isFire())); */
					}
				else
					{return false;}
				}

			else if (pi.isFire()==false)////for a Water Piece, similar ^
				{
				if (adel_x==1 && del_y==-1 && pi.hasCaptured()==false)
					{	pi.doneCapturing();
						return true;}
				else if (adel_x==2 && del_y==-2)
					{
					return cond_capture;
					/* xm= (xi+xf)/2; ym=(yi+yf)/2;//getting the middle coords	
					pm= pieceAt(xm,ym);//getting the middle piece 
					return (pm!=null && (pi.isFire() != pm.isFire())); //middle piece ca} //check if the capture is valid */
					}
				else
					{return false;}
				}
			return false;			
		}
		// xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

	public boolean canSelect (int x, int y)
	{	

		if (x<0 || x>7 || y<0 || y>7)
			{return false;}

		Piece p=pieceAt(x,y);
		if (p!=null && p.isFire()==isFireTurn && ((hasSelected == false) ||(hasSelected==true && hasMoved ==false) ))
			{return true;}

		else if (p==null && hasSelected==true)
			{
				if (hasMoved == false && validMove(selected_x, selected_y, x, y) )
					{return true;}
				else if  (hasMoved==true && pieceAt(selected_x, selected_y)!=null && pieceAt(selected_x, selected_y).hasCaptured() && validMove(selected_x, selected_y, x, y))
					{return true;}
				else
					{return false;}
			}

		else
		{return false;}	

			
	}

	public void select(int x, int y)
	{
		if (pieceAt(x,y)==null)
			{
				pieceAt(selected_x, selected_y).move(x,y);
				hasMoved=true;
				
			}
		else
			{
				hasSelected=true;
				hasMoved=false;
			}
		selected_y=y;
		selected_x=x;
			}

	public void place(Piece p, int x, int y)
	{
		if (x>=0 && x<=7 && y>=0 && y<=7 && p!=null)
			{coords[x][y]=p;
			if (p.isFire())
				{no_fire=no_fire+1;}
			else
				{no_water=no_water+1;}

			/*drawBoard(); */}
	}

	public Piece remove(int x, int y)
	{
		if (x<0 || x>7 || y<0 || y>7)
		{
			System.out.println("Error: out of bounds for remove");
			return null;
		}

		Piece p= pieceAt(x,y);
		if (p==null)
		{
			System.out.println("Error: no piece to remove");
			return null;
		}
		else
		{	/** String type="pawn";
			if (p.isBomb())
				{type="bomb";}
			if (p.isShield())
				{type="shield";}
			Piece t=new Piece(p.isFire(),this,x,y,type);
			if (p.isFire())
				{no_fire-=1;}
			else
				{no_water-=1}
			drawBoard(false);
			coords[x][y]=null;
			return t; **/
			coords[x][y]=null;
			if (p.isFire())
				{no_fire= no_fire-1;}
			else
				{no_water=no_water-1;}
			/* drawBoard(); */
			return p;

		}
	}

	public boolean canEndTurn()
	{
		return hasMoved;
	}

	public void endTurn()
	{
		Piece p=pieceAt(selected_x,selected_y);
		if (p!=null)
			{p.doneCapturing();}
		isFireTurn=!(isFireTurn);
		hasMoved=false;
		hasSelected=false;

	}

	public String winner()
	{
		if (no_fire==0 && no_water>0)
			{return "Water";}
		else if (no_water==0 && no_fire>0)
			{return "Fire";}
		else if (no_fire==0 && no_water==0)
			{return "No one"; }
		else
			{return null;}
	}





	public static void main(String[] args)
	{
		Board obj=new Board (false);
		obj.drawBoard();
		double x=0,y=0;
		while(obj.winner()==null)
			{
				if (StdDrawPlus.mousePressed())
				{
					x = StdDrawPlus.mouseX();
                	y = StdDrawPlus.mouseY();
                	StdDrawPlus.show(13);

                

                	if (obj.canSelect((int) x, (int) y))
                		{obj.select ((int) x,(int) y);
                		obj.drawBoard();
                		StdDrawPlus.show(13);
						}
                }
                

               	if (StdDrawPlus.isSpacePressed() && obj.canEndTurn())
                		{obj.endTurn();
                			obj.drawBoard();}
				}
	}
}



	


					

		
