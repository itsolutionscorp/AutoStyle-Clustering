import javax.swing.GrayFilter;

import com.puppycrawl.tools.checkstyle.checks.indentation.IfHandler;


public class Board {
	private Piece[][] pieceArray;
	private Piece playerSelect;
	private boolean redTurn = true;
	private boolean blueTurn = false;
	private boolean movedOrNot = false;
//	private boolean hasCapturedOrNot = false;
	private int selectx = -1;
	private int selecty = -1;
	
	
	public static void main(String[] args)
	{
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board gameBoard = new Board(false);
		boolean spacePressedOrNot = false;
		
		
		int x = 0;
		int y = 0;
//		while (true)
//		{
			while (gameBoard.winner() == null)
			{
				spacePressedOrNot = StdDrawPlus.isSpacePressed();
  				if (spacePressedOrNot && gameBoard.canEndTurn())
				{
  					spacePressedOrNot = false;
					gameBoard.endTurn();
					gameBoard.drawBoard();
					StdDrawPlus.show(10);
					continue;
				}
				if (StdDrawPlus.mousePressed())
				{
					x = (int) StdDrawPlus.mouseX();
					y = (int) StdDrawPlus.mouseY();
					if(gameBoard.canSelect(x, y))
					{
						gameBoard.select(x, y);
					}
					StdDrawPlus.show(25);
				}
				
			}
			
			System.out.println(gameBoard.winner());
			return;
//			if (gameBoard.canEndTurn())
//			{
//				StdDrawPlus.isSpacePressed();
//				gameBoard.endTurn();
//				gameBoard.drawBoard();
//				StdDrawPlus.show(10);
//			}
			
//		}	
		
		
		
		
		
	}
	
	public Board(boolean shouldBeEmpty) {
		pieceArray = new Piece[8][8];
		if (shouldBeEmpty) 
		{
			for (int j = 0; j < 8; j++)
			{
				for (int i= 0; i < 8; i++)
				{
					 if ((i + j) % 2 == 0) 
						 StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		                else                 
		                StdDrawPlus.setPenColor(StdDrawPlus.RED);
		                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
		                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
				}
			}
		}
		else 
		{
			for (int j = 0; j < 8; j++)
			{
				for (int i= 0; i < 8; i++)
				{
					 if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
		                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
		                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		            
		            pieceArray[j][i] = null;
					if (j == 7 && i % 2 != 0)
					{
						pieceArray[j][i] = new Piece(false, this, i, j, "pawn");
//						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
					}
					if (j == 6 && i % 2 == 0) 
					{
						pieceArray[j][i] = new Piece(false, this, i, j, "shield");
//						StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
					}
					if (j == 5 && i%2 != 0)
					{
						pieceArray[j][i] = new Piece(false, this, i, j, "bomb");
//						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
					}
					if (j == 2 && i%2 == 0)
					{
						pieceArray[j][i] = new Piece(true, this, i, j, "bomb");
//						StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
					}
					if (j == 1 && i % 2 != 0)
					{
						pieceArray[j][i] = new Piece(true, this, i, j, "shield");
//						StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
					}
					if (j == 0 && i % 2 == 0)
					{
						pieceArray[j][i] = new Piece(true, this, i, j, "pawn");
//						StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
					}
				}
			}
			drawBoard();
			
		}
		
		
		
	}//Board
	
	public Piece pieceAt(int x, int y)
	{
		if (!inbonds(x, y))
		{
			return null;
		}
		if (pieceArray[y][x] != null)
		{
			return pieceArray[y][x];
		}
		return null;
	}//pieceAt
	
	private void drawBoard()
	{
		for (int j = 0; j < 8; j++)
		{
			for (int i= 0; i < 8; i++)
			{
				 if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
	                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
	                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
	                
	                drawPiece(i, j);
			}
		}
		
		
	}
	
	private void drawPiece(int i, int j)
	{
		//i = x j = y
		Piece  currPiece = pieceArray[j][i];
		if (currPiece != null)
		{
			if (!currPiece.isKing())
			{
				if (currPiece.isFire() == false)
				{
					if (currPiece.isShield())
					{
						StdDrawPlus.picture(i + .5, j +.5, "img/shield-water.png", 1, 1);
					}
					else if (currPiece.isBomb())
					{	
						StdDrawPlus.picture(i + .5, j +.5, "img/bomb-water.png", 1, 1);
					}
					else
					{
						StdDrawPlus.picture(i + .5, j +.5, "img/pawn-water.png", 1, 1);
					}
				}
				else
				{
					if (currPiece.isShield())
					{
						StdDrawPlus.picture(i + .5, j +.5, "img/shield-fire.png", 1, 1);
					}
					else if (currPiece.isBomb())
					{	
						StdDrawPlus.picture(i + .5, j +.5, "img/bomb-fire.png", 1, 1);
					}
					else
					{
						StdDrawPlus.picture(i + .5, j +.5, "img/pawn-fire.png", 1, 1);
					}
				}
			}
			else
			{
				if (!currPiece.isFire())
				{
					if (currPiece.isShield())
					{
						StdDrawPlus.picture(i + .5, j +.5, "img/shield-water-crowned.png", 1, 1);
					}
					else if (currPiece.isBomb())
					{	
						StdDrawPlus.picture(i + .5, j +.5, "img/bomb-water-crowned.png", 1, 1);
					}
					else
					{
						StdDrawPlus.picture(i + .5, j +.5, "img/pawn-water-crowned.png", 1, 1);
					}
				}
				else
				{
					if (currPiece.isShield())
					{
						StdDrawPlus.picture(i + .5, j +.5, "img/shield-fire-crowned.png", 1, 1);
					}
					else if (currPiece.isBomb())
					{	
						StdDrawPlus.picture(i + .5, j +.5, "img/bomb-fire-crowned.png", 1, 1);
					}
					else
					{
						StdDrawPlus.picture(i + .5, j +.5, "img/pawn-fire-crowned.png", 1, 1);
					}
				}
			}
		}
		
		
	}
	
	
	
	
	public boolean canSelect(int x, int y)
	{
		if (!inbonds(x, y))
		{
			return false;
		}
		
		if (playerSelect == null && movedOrNot == false)
		{
			if (pieceArray[y][x] == null)
			{
				return false;
			}
			if (pieceArray[y][x] != null && pieceArray[y][x].isFire() != redTurn)
			{
				return false;
			}
			return true;
		}	
		else if (playerSelect != null && movedOrNot == false)
		{
			if (pieceArray[y][x] != null && pieceArray[y][x].side() == playerSelect.side())
			{
					return true;
			}
			else if (!playerSelect.isKing())
			{
					return validmove(selectx, selecty, x, y);
			}
			else 
			{
					return validmoveWithKing(selectx, selecty, x, y);
			}
		}
		else if (playerSelect != null && movedOrNot == true) 
		{
			if (!playerSelect.hasCaptured())
			{
				return false;
			}
			else
			{
				if (!playerSelect.isKing())
				{
					return validmoveAfterCaptured(selectx, selecty, x, y);
				}
				else 
				{
					return validmoveWithKingAfterCaptured(selectx, selecty, x, y);
				}
			}
		}
		
		return false;
	}
	
	private boolean validmoveAfterCaptured(int xi, int yi, int xf, int yf)
	{
		int midx = (xf + xi) / 2;
		int midy = (yf + yi) / 2;
		if (!inbonds(xf, yf))
		{
			return false;
		}
		
		if (redTurn)
		{
			if (yf < yi)
			{
				return false;
			}
			
			if(Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2 )
			{
				if (pieceArray[midy][midx] == null)
				{
					return false;
				}
				if (pieceArray[midy][midx] != null && !pieceArray[midy][midx].isFire())
				{
					return true;
				}
			}
		}
		
		if (blueTurn)
		{
			if (yf > yi)
			{
				return false;
			}
			
			if(Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2 )
			{
				if (pieceArray[midy][midx] == null)
				{
					return false;
				}
				if (pieceArray[midy][midx] != null && pieceArray[midy][midx].isFire())
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean validmove(int xi, int yi, int xf, int yf)
	{
		int midx = (xf + xi) / 2;
		int midy = (yf + yi) / 2;
		if (xf > 7 || xf < 0 || yf > 7 || yf < 0)
		{
			return false;
		}
		if (yf == yi || xi ==xf || Math.abs(xf - xi) !=  Math.abs(yf - yi) ||
				Math.abs(xf - xi) > 2 || Math.abs(yf - yi) > 2)
		{
			return false;
		}
		
		
		if (redTurn)
		{
			if (yf < yi)
			{
				return false;
			}
			if(Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2 )
			{
				if (pieceArray[midy][midx] == null)
				{
					return false;
				}
				if (pieceArray[midy][midx] != null && pieceArray[midy][midx].isFire())
				{
					return false;
				}
			}
		}
		
		if (blueTurn)
		{
			if (yf > yi)
			{
				return false;
			}
			if(Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2 )
			{
				if (pieceArray[midy][midx] == null)
				{
					return false;
				}
				if (pieceArray[midy][midx] != null && !pieceArray[midy][midx].isFire())
				{
					return false;
				}
			}
			
		}
		

		if (pieceArray[yf][xf] != null)
		{
			return false;
		}

		return true;
	}
	
	private boolean validmoveWithKing(int xi, int yi, int xf, int yf)
	{
		int midx = (xf + xi) / 2;
		int midy = (yf + yi) / 2;
		if (!inbonds(xf, yf))
		{
			return false;
		}
		if (yf == yi || xi ==xf || Math.abs(xf - xi) !=  Math.abs(yf - yi) ||
				Math.abs(xf - xi) > 2 || Math.abs(yf - yi) > 2)
		{
			return false;
		}
		
		if (redTurn)
		{
			if(Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2 )
			{
				if (pieceArray[midy][midx] == null)
				{
					return false;
				}
				if (pieceArray[midy][midx] != null && pieceArray[midy][midx].isFire())
				{
					return false;
				}
			}
		}
		
		if (blueTurn)
		{	
			if(Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2 )
			{
				if (pieceArray[midy][midx] == null)
				{
					return false;
				}
				if (pieceArray[midy][midx] != null && !pieceArray[midy][midx].isFire())
				{
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean validmoveWithKingAfterCaptured(int xi, int yi, int xf, int yf)
	{
		int midx = (xf + xi) / 2;
		int midy = (yf + yi) / 2;
		if (!inbonds(xf, yf))
		{
			return false;
		}
		
		if (redTurn)
		{
			
			
			if(Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2 )
			{
				if (pieceArray[midy][midx] == null)
				{
					return false;
				}
				if (pieceArray[midy][midx] != null && !pieceArray[midy][midx].isFire())
				{
					return true;
				}
			}
		}
		
		if (blueTurn)
		{
			if(Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2 )
			{
				if (pieceArray[midy][midx] == null)
				{
					return false;
				}
				if (pieceArray[midy][midx] != null && pieceArray[midy][midx].isFire())
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public void select(int x, int y)
	{
		if ( pieceArray[y][x] != null)//新点的不是null
		{
			playerSelect = pieceArray[y][x];
			if (selectx >= 0 && selecty >= 0)
			{
//				StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
//				StdDrawPlus.filledSquare(selectx + .5, selecty + .5, .5);
				setColor("gray", selectx, selecty);
				drawPiece(selectx, selecty);
			}
//			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
//    		StdDrawPlus.filledSquare(x+.5, y+.5, .5);
			setColor("white", x, y);
    		drawPiece(x, y);
		}
		else 
		{
			if (pieceArray[selecty][selectx]!=null)
			{
				pieceArray[selecty][selectx].move(x, y); 
				movedOrNot = true;
			}
			else
			{
				return;
			}
			if (Math.abs(selectx - x) == 2 && Math.abs(selecty - y) == 2)
			{
				setColor("gray", (selectx + x)/2 , (selecty + y)/2);
			}
//			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
//			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
			drawBoard();
			
 			setColor("white", x, y);
			drawPiece(x, y);
//			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
//			StdDrawPlus.filledSquare(selectx + .5, selecty + .5, .5);
			setColor("gray", selectx, selecty);
			
		}
		selectx = x;
		selecty = y;
	}
	
	private void setColor(String color, int x, int y)
	{
		if (color.equals("gray"))
		{
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		}
		else if (color.equals("white"))
		{
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		}
	}
	
	public void place(Piece p, int x, int y) 
	{
		if (p != null && inbonds(x, y))
		{
				pieceArray[y][x] = p;
		}
	}
	
	public Piece remove(int x, int y)
	{
		Piece toRemove;
//		System.out.println("("+x+","+y+")");
		if (!inbonds(x, y))
		{
			System.out.println("Out of bunds.");
			return null;
		}
		
		if (pieceArray[y][x] == null)
		{
			System.out.println( "("+x+","+y+")"+"Nothing to remove.");
			return null;
		}
		
		toRemove = pieceArray[y][x];
		pieceArray[y][x] = null;
		return toRemove;
	}
	
	public boolean canEndTurn() 
	{
		if (movedOrNot == false )
		{
			
			return false;
		}
		
//		if (movedOrNot || (pieceArray[selecty][selectx] != null && pieceArray[selecty][selectx].hasCaptured()))
//		{
//			return true;
//		}
//		if(!movedOrNot)
//		{
//			return false;
//		}
//		else if (selectx < 0 && selecty < 0)
//		{
//			return false;
//		}
//		else if (pieceArray[selecty][selectx] != null &&  !pieceArray[selecty][selectx].hasCaptured())
//		{
//			return false;
//		}
		
		
		return true;
	}
	
	public void endTurn() 
	{
		playerSelect = null;
		movedOrNot = false;
		if (redTurn)
		{
			redTurn = false;
			blueTurn = true;
		}
		else 
		{
			blueTurn = false;
			redTurn = true;
		}
		if (pieceArray[selecty][selectx]!=null)
		{
			pieceArray[selecty][selectx].doneCapturing();
		}
		selectx = -1;
		selecty = -1;
	}
	
	public String winner()
	{
		int blueCounter = 0;
		int redCounter = 0;
		for (int j = 0; j < 8; j++) 
		{
			for (int i = 0; i < 8; i++) 
			{
				if (pieceArray[j][i] != null)
				{
					if (pieceArray[j][i].isFire())
					{
						redCounter+=1;
					}
					else 
					{
						blueCounter+=1;
					}
				}
			}		
		}//for
		
		if (blueCounter == 0 && redCounter == 0)
		{
			return "No one";
		}
		
		if (blueCounter == 0)
		{
			return "Fire";
		}
		
		if (redCounter == 0)
		{
			return "Water";
		}
		
		return null;
		
		
	}
	
	
	private boolean inbonds(int x, int y) 
	{
		if (x > 7 || x < 0 || y > 7 || y < 0)
		{
			return false;
		}
		return true;
	}
	
}
