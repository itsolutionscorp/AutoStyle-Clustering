import java.awt.*;
//Kevin Cheng
public class Board
{
	private Piece[][] map = new Piece[8][8]; 
	private int size = 8;
	private boolean gameOver=false;
	private Color gold = new Color(212, 175, 55);
	private boolean fireTurn = true;
	private Piece selected;
	private int selectedX;
	private int selectedY;		
	private boolean canEndTurn = false;
	private boolean hasMoved = false;
	private String winner = null;

	public Board(boolean shouldBeEmpty)
	{
		if (shouldBeEmpty == false)
		{
			int row = 0;
			setup("pawn", row, 0, true);
			row++;
			setup("shield", row, 1, true);
			row++;
			setup("bomb", row, 0, true);
			row = row + 3;
			setup("bomb", row, 1, false);
			row++;
			setup("shield", row, 0, false);
			row++;
			setup("pawn", row, 1, false);
		}	
	}

	private void drawBoard(int N) 
    {
    	
    	for (int i = 0; i < N; i++) 
        {
            for (int j = 0; j < N; j++) 
            {
            	String temp = "img/";
                if ((i + j) % 2 == 0)
                {
                    StdDrawPlus.setPenColor(gold);  
                } 
                else
                {
                    StdDrawPlus.setPenColor(StdDrawPlus.BLUE);
                }                  
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece current = pieceAt(i,j);
                if ( current != null) 
                {
                	if (current.isBomb() == true)
                	{
                		temp=temp + "bomb";
                	}
                	else if (current.isShield() == true) 
                	{
                		temp=temp + "shield";	
                	}
                	else
                	{
                		temp=temp + "pawn";
                	}
                	if (current.isFire() == true) 
                	{
                		temp=temp + "-fire";
                	}
                	else
                	{
                		temp=temp + "-water";
                	}
                	if (current.isKing() == true)
                	{
                		temp=temp + "-crowned";
                	}
                	temp=temp + ".png";
                    
                    //String hey= "";
                    //hey = hey + i + "," + j;
                    //StdDrawPlus.text(i+.5, j+.5, hey);
                   	StdDrawPlus.picture(i + .5, j + .5, temp, 1, 1);
                }
            }
        }
    }
    private void ended()
    {
    	int fire = 0;
    	int water = 0;
    	for (int row =0; row < size; row++)
    	{
    		for (int col =0; col < size; col++)
    		{
    			Piece temp = map[row][col];
    			if(temp != null)
    			{
					if (temp.isFire())
					{
						fire++;
					}
					else
					{
						water++;
					}
    			}
    		}
    	}
    	if (water == 0 && fire == 0)
    	{
			winner = "No one";
			gameOver=true;
    	}
    	else if (fire ==0)
    	{
			winner = "Water";
			gameOver = true;
    	}
    	else if (water ==0)
    	{
    		winner = "Fire";
			gameOver = true;
    	}
    }

	public static void main (String args[])
	{
		int dim = 8; 
		StdDrawPlus.setXscale(0, dim);
        StdDrawPlus.setYscale(0, dim);
        Board a = new Board(false);

		while (a.gameOver == false)
		{
			a.drawBoard(dim);
			
				if (StdDrawPlus.mousePressed()) 
				{
                	int curX = (int) StdDrawPlus.mouseX();
                	int curY = (int) StdDrawPlus.mouseY();

                	if (a.canSelect(curX, curY))
                	{
                		a.select(curX, curY);
                	}
				}
				if (StdDrawPlus.isSpacePressed() == true && a.canEndTurn == true)
                {
                	System.err.println("trying to end turn");
               		a.endTurn();
                }
			StdDrawPlus.show(10);
			a.ended();
		}
		a.drawBoard(dim);

	}
	private void setup (String type, int y, int start, boolean isFire)
	{
		for (int x = start; x < size; x=x+2)
		{
			map[x][y] = new Piece(isFire, this, x, y, type);
		}
		
	}

	public Piece pieceAt(int x, int y)
	{
		if (x>=size || y >= size || x<0 || y<0)
		{
			return null;
		}
		return map[x][y];
	}
	//cur is piece clicked on
	//selected is previous piece if applicable
	public boolean canSelect( int x, int y)
	{
		if (onBoard(x, y)==false)
		{
			return false;
		}
		Piece cur = map[x][y];
		if (hasMoved && cur != null)
		{
			return false;
		}
		//System.out.println(cur);
		if (selected == null) //no previous piece selected
		{
			if (cur == null) //clicked empty
			{
				return false;
			}
			else
			{
				return cur.isFire() == fireTurn; //return true if click on your piece, else false.
			}
		}
		else
		{
			if (cur == null)
			{
				return validMove(selectedX, selectedY, x, y);
			}
			else 
			{	
				if (selected.hasCaptured() == false)
				{	
					return cur.isFire() == fireTurn;	
				}	
				return false;
			}
		}
	}
	private boolean up(int xi, int yi, int xf, int yf)
	{
		if (onBoard(xf, yf)==false)
		{
			return false;
		}
		boolean owner = selected.isFire();
		if (xi+2 == xf && yi+2 == yf)
		{
			Piece temp = map[xi+1][yi+1];
			return temp != null && temp.isFire() != owner;
		}
		else if (xi-2 == xf && yi+2 == yf)
		{
			Piece temp = map[xi-1][yi+1];
			return temp != null && temp.isFire() != owner;
		}
		else if (xi+1 == xf && yi+1 == yf && hasMoved == false)
		{
			return map[xf][yf]==null;
		}
		else if (xi-1 == xf && yi+1 == yf && hasMoved == false)
		{
			return map[xf][yf]==null;
		}
		return false;
	}
	private boolean down(int xi, int yi, int xf, int yf)
	{
		if (onBoard(xf, yf)==false)
		{
			return false;
		}
		boolean owner = selected.isFire();
		if (xi-2 == xf && yi-2 == yf)
		{
			Piece temp = map[xi-1][yi-1];
			return temp != null && temp.isFire() != owner;
		}
		else if (xi+2 == xf && yi-2 == yf)
		{
			Piece temp = map[xi+1][yi-1];
			return temp != null && temp.isFire() != owner;
		}
		else if (xi-1 == xf && yi-1 == yf && hasMoved == false)
		{
			return map[xf][yf]==null;
		}
		else if (xi+1 == xf && yi-1 == yf && hasMoved == false)
		{
			return map[xf][yf]==null;
		}
		return false;
	}
	//i is piece coordinates f is new coordinates
	private boolean validMove(int xi, int yi, int xf, int yf)
	{
		if (onBoard(xf, yf)==false)
		{
			return false;
		}
		if (Math.abs(xf-xi)>2 || Math.abs(yf-yi)>2)
		{
			return false;
		}
		if (selected.isKing())
		{
			return up(xi, yi, xf, yf) || down(xi, yi, xf, yf) ;
		}
		else if (selected.isFire())
		{
			return up(xi, yi, xf, yf);
		}
		else if (selected.isFire() == false)
		{	
			return down(xi, yi, xf, yf);
		}
		return false;
	}
	
	public void select(int x, int y)
	{
		if(map[x][y]==null)
		{
			place(selected, x, y);
			selected.move(x,y);
			remove(selectedX, selectedY);
			canEndTurn=true;
			hasMoved = true;
		}
		
			selected=map[x][y];
			selectedX=x;
			selectedY=y;
		
		
	}
	public void place(Piece p, int x, int y)
	{
		if (onBoard(x,y))
		{
			map[x][y] = p;
		}
		
		
	}
	public Piece remove(int x, int y)
	{
		if (onBoard(x, y))
		{
			Piece temp = map[x][y];
			map[x][y]= null;
			return temp;
		}
		return null;
	}
	public boolean canEndTurn()
	{
		return canEndTurn;
	}
	
	public void endTurn()
	{
		canEndTurn = false;
		hasMoved = false;
		if (selected != null) 
		{
			selected.doneCapturing();
		}
		selected = null; 
		selectedX=-1;
		selectedY=-1;
		if (fireTurn == true)
		{
			fireTurn = false;
		}
		else 
		{
			fireTurn = true;
		}
		System.out.println("ended turn");
	}
	
	public String winner()
	{
		ended();
		return winner;
	}
	private boolean onBoard(int x, int y)
	{
		if (x<0 || y<0 || x>size-1 || y>size-1)
		{
			return false;
		}
		return true;
	}
}