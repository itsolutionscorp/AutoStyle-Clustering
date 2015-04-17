public class Board
{
	public static void main(String[] args) 
	{
		Board b = new Board(false);
		b.drawBoard();
		int x = 9;
		int y = 9;

		
		while(b.winner() == null)
		{
			while(StdDrawPlus.mousePressed())
			{
				x = (int) StdDrawPlus.mouseX();
				y = (int) StdDrawPlus.mouseY();

				if(b.canSelect(x, y))
				{
					b.select(x, y);
					b.drawBoard();
				}
				StdDrawPlus.show(20);
			}

			while(StdDrawPlus.isSpacePressed() && b.canEndTurn())
			{
				System.out.println("next players turn!");
				b.endTurn();
			}
		}
	} 

	private Piece[][] pieceArray;
	private Piece currPiece;
	private int currX = -1; 
	private int currY = -1;
	private int N = 8;
	private boolean firstPlayer, hasMoved;


	public Board(boolean shouldBeEmpty)
	{
		pieceArray = new Piece[N][N];

		if(!shouldBeEmpty)
		{
			for(int i = 0; i <= 3; i++)
				pieceArray[i * 2][0] = new Piece(true, this, i * 2, 0, "pawn");
			for(int i = 0; i <= 3; i++)
				pieceArray[i * 2 + 1][1] = new Piece(true, this, i * 2 + 1, 1, "shield");
			for(int i = 0; i <= 3; i++)
				pieceArray[i * 2][2] = new Piece(true, this, i * 2, 2, "bomb");
			for(int i = 0; i <= 3; i++)
				pieceArray[i * 2 + 1][7] = new Piece(false, this, i * 2 + 1, 7, "pawn");
			for(int i = 0; i <= 3; i++)
				pieceArray[i * 2][6] = new Piece(false, this, i * 2, 6, "shield");
			for(int i = 0; i <= 3; i++)
				pieceArray[i * 2 + 1][5] = new Piece(false, this, i * 2 + 1, 5, "bomb");	
		}
		firstPlayer = true; 
		hasMoved = false;
	}

	private void drawBoard()
	{
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        String imagePath;

		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	if(i == currX && j == currY)
            		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            	else
            	{
	                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
	                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
            	}
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                
                if(pieceArray[i][j] != null)
        		{
	        		imagePath = "img/";
	        		Piece p = pieceArray[i][j];
			        
		        	if(p.isBomb())
		           		imagePath += "bomb-";
		           	else if(p.isShield())
		           		imagePath += "shield-";
		           	else //fire pawn
		           		imagePath += "pawn-";

		           	if(p.isFire()) 
		           		imagePath += "fire";
		           	else
		           		imagePath += "water";

		           	if(p.isKing())
		           		imagePath += "-crowned";

		           	imagePath += ".png";
					StdDrawPlus.picture(i + .5, j + .5, imagePath, 1, 1);
				}
            }
        }
	}
	
	public Piece pieceAt(int x, int y)
	{
		if(!checkBounds(x, y))
			return null;
		return pieceArray[x][y];
	}

	private boolean validMove(int xi, int yi, int xf, int yf)
	{
		if(pieceAt(xi, yi) != null && pieceAt(xf, yf) == null)
		{
			Piece capturedPiece = pieceAt((xi + xf) / 2, (yi + yf) / 2);
			if(pieceAt(xi, yi).isKing())
			{
				if((Math.abs(xi - xf) == 1) && (Math.abs(yi - yf) == 1))
					return true;
				else if ((Math.abs(xi - xf) == 2) && (Math.abs(yi - yf) == 2) && capturedPiece != null && (capturedPiece.isFire()) != currPiece.isFire())
					return true;
				else
					return false;
			}
			if(pieceAt(xi, yi).isFire())
			{
				if((Math.abs(xi - xf) == 1) && ((yf - yi) == 1))
					return true;
				else if ((Math.abs(xi - xf) == 2) && ((yf - yi) == 2) && capturedPiece != null && !(capturedPiece.isFire()))
					return true;
				else
					return false;
			}
			else //isFire() = false
			{ 
				if((Math.abs(xi - xf) == 1) && ((yi - yf) == 1))
					return true;
				else if ((Math.abs(xi - xf) == 2) && ((yi - yf) == 2) && capturedPiece != null && (capturedPiece.isFire()))
					return true;
				else
					return false;
			}
		}
		else
			return false;
	}

	public boolean canSelect(int x, int y)
	{
		if(!checkBounds(x, y))
			return false;
		Piece piece = pieceAt(x, y);
		if(currPiece == null)
			return piece != null && piece.isFire() == firstPlayer;
		if(!hasMoved)
			return piece != null || validMove(currX, currY, x, y);
		
		return currPiece.hasCaptured() && !currPiece.isBomb() && validMove(currX, currY, x, y) && Math.abs(currX - x) == 2;
	}

	public void select(int x, int y)
	{
		if(currPiece == null) //
		{
			currPiece = this.pieceAt(x, y);
		}
		else
		{
			if(pieceAt(x, y) != null)
			{
				 currPiece = pieceAt(x, y);
			}
			else //move
			{
				currPiece.move(x, y);
				hasMoved = true;
			} //currPiece.hasCaptured()
		}
		currX = x;
		currY = y;
	}

	public void place(Piece p, int x, int y)
	{
		if (!checkBounds(x, y))
			return;
		pieceArray[x][y] = p;
	}

	public Piece remove(int x, int y)
	{
		if (!checkBounds(x, y))
			return null;
		Piece removedPiece = pieceAt(x, y);
		pieceArray[x][y] = null;
		return removedPiece;
	}
	
	private boolean checkBounds(int x, int y)
	{
		return x < N && y < N && x >= 0 && y >= 0;
	}

	public boolean canEndTurn()
	{
		return hasMoved;
	}

	public void endTurn()
	{
		firstPlayer = !firstPlayer;
		currPiece.doneCapturing();
		currPiece = null;
		currX = -1;
		currY = -1;
		hasMoved = false;
	}

	public String winner()
	{
		boolean fireWinner = false;
		boolean waterWinner = false;

		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				if(pieceArray[i][j] != null)
				{
					if(pieceArray[i][j].isFire())
						fireWinner = true;
					else
						waterWinner = true;
				}
			}
		}

		if(fireWinner && waterWinner)
			return null;
		else if(fireWinner)
			return "Fire";
		else if(waterWinner)
			return "Water";
		else
			return "No one"; 
	}
}