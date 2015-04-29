//completed

public class Board
{
	private boolean[][] hasPiece;
	private Piece[][] pieces;
	private int firepieces, waterpieces, currentx, currenty;
	private boolean isFireTurn, hasSelectedPiece, hasMovedPiece;
	private Piece currentpiece;

	private boolean isWithinBounds (int x, int y)
	{
		return (x>=0 && x<=7 && y>=0 && y<=7);
	}

	public Board (boolean shouldBeEmpty)
	{
		this.hasPiece = new boolean[8][8];
		this.pieces = new Piece[8][8];
		int x, y;

		if (shouldBeEmpty == true)
		{
			for (x=0; x<8; x += 1)
			{
				for(y=0; y<8; y += 1)
				{
					this.pieces[x][y] = null;
					this.hasPiece[x][y] = false;
				}
			}
		}
		
		else
		{
			this.pieces = new Piece[8][8];
			this.firepieces = 12;
			this.waterpieces = 12;
			this.isFireTurn = true;
			this.hasSelectedPiece = false;
			this.hasMovedPiece = false;
			this.currentpiece = null;
			this.currentx = -1;
			this.currenty = -1;
			String t = "";
			String[] types = new String[8];
			types[0] = "pawn";
			types[1] = "shield";
			types[2] = "bomb";
			types[3]=  "null";
			types[4] = "null";
			types[5] = "bomb";
			types[6] = "shield";
			types[7] = "pawn";
			for(y = 0; y < 8; y++)
			{
				for(x = 0; x < 8; x++)
				{
					t = types[7-y];
					if (y>=0 && y<=2 && (x+y)%2 == 0)
					{
						this.pieces[x][y] = new Piece(true, this, x, y, t);
						this.hasPiece[x][y] = true;
					}

					else if (y>=5 && y<=7 && (x+y)%2==0)
					{
						this.pieces[x][y] = new Piece(false, this, x, y, t);
						this.hasPiece[x][y] = true;
					}

					else
					{
						this.pieces[x][y] = null;
						this.hasPiece[x][y] = false;
					}
				}
			}
		}
	}

	private void place2(Piece p, int x, int y)
	{
		if (x>=0 && y>=0 && x<=7 && y<=7 && p != null)
		{
			Piece oldpiece = null;
			if (p != null && this.hasPiece[x][y])
			{
				oldpiece = this.pieces[x][y];
				this.remove(x, y);
			}
			this.pieces[x][y] = p;
			this.currentx = x;
			this.currenty = y;
			/*this.hasSelectedPiece = true;*/
			if (this.hasPiece[x][y] == false)
			{
				this.hasPiece[x][y] = true;
				/*if (p.isFire())
				{
					this.firepieces += 1;
				}
				else
				{
					this.waterpieces += 1;
				}*/
			}
			else
			{
				if (oldpiece.isFire())
				{
					this.firepieces -= 1;
					this.waterpieces += 1;
				}
				else
				{
					this.waterpieces -= 1;
					this.firepieces += 1;
				}
			}
		}
	}

	private void place3(Piece p, int x, int y)
	{
		if (x>=0 && y>=0 && x<=7 && y<=7 && p != null)
		{
			Piece oldpiece = null;
			/*this.hasSelectedPiece = true;*/
/*			System.out.println("Current x = " + this.currentx);
			System.out.println("Current y = " + this.currenty);*/
			if (this.currentx >=0 && this.currentx <=7 && this.currenty >=0 && this.currenty <=7 && this.pieces[this.currentx][this.currenty] != null)
			{
				this.pieces[this.currentx][this.currenty] = null;
				this.hasPiece[this.currentx][this.currenty] = false;
				/*System.out.println(this.pieces[this.currentx][this.currenty]);*/
			}
			if (this.pieces[x][y] == null)
			{
				this.hasPiece[x][y] = true;
				/*if (p.isFire())
				{
					this.firepieces += 1;
				}
				else
				{
					this.waterpieces += 1;
				}*/
			}
			else
			{
				oldpiece = this.pieces[x][y];
				if (oldpiece.isFire())
				{ 
					this.firepieces -= 1;
					if (!p.isFire()){
						this.waterpieces += 1;
					}
					
				}
				else
				{
					this.waterpieces -= 1;
					if (p.isFire()){
					this.firepieces += 1;
				}
				}
				this.pieces[x][y] = null;
			}
			// if (this != null && p!= null)
			// {
				this.pieces[x][y] = p;
				this.currentx = x;
				this.currenty = y;
			//}
		}
	}

	public void place(Piece p, int x, int y)
	{
		if (!isWithinBounds(x,y))
		{
			;
		}
		else if (p == null)
		{
			;
		}
		else
		{
			remove(x, y);
			pieces[x][y] = p;
			if (p.isFire())
			{
				firepieces += 1;
			}
			else if (!p.isFire())
			{
				waterpieces += 1;
			}
		}
	}

	private static void drawEmpty()
	{
		int i, j;
		for (i=0; i<8; i+=1)
		{
			for (j=0; j<8; j+=1)
			{
				if ((i+j)%2==0)
				{
					StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				}
				else
				{
					StdDrawPlus.setPenColor(StdDrawPlus.RED);
				}
				StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
	}

	private void drawBoard()
	{
		Board.drawEmpty();
		int x, y;
		String piecetype = "", pieceside = "", piececrowned = "", imgpath="";
		for (y=0; y<8; y++)
		{
			for(x=0; x<8; x++)
			{	imgpath="";
				piececrowned="";
				if (this.pieces[x][y] != null)
				{	
					if (this.pieces[x][y].isBomb())
					{
						piecetype = "bomb";
					}
					else if (this.pieces[x][y].isShield())
					{
						piecetype = "shield";
					}
					else
					{
						piecetype = "pawn";
					}
					if (this.pieces[x][y].isFire())
					{
						pieceside = "-fire";
					}
					else
					{
						pieceside = "-water";
					}
					if (this.pieces[x][y].isKing())
					{
						piececrowned = "-crowned";
					}
					/*if (this.hasSelectedPiece)
					{
						StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
						StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
					}*/
					if (isWithinBounds(currentx, currenty) && pieceAt(currentx, currenty) == this.pieces[x][y])
					{
						StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
						StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
					}
					imgpath = "img/" + piecetype + pieceside + piececrowned + ".png";
					/*System.out.println("(" + x + "," + y + "): "+ imgpath);*/
					StdDrawPlus.picture(x + 0.5, y + 0.5, imgpath, 1, 1);
				}
			}
		}
	}

	public Piece pieceAt(int x, int y)
	{
		if(x<0 || x>7 || y<0 || y>7 || this.pieces[x][y] == null)
		{
			return null;
		}
		
		return this.pieces[x][y];
	}

	public String winner()
	{
		/*if (this.firepieces == 0 && this.waterpieces == 0)
		{
			return "No one";
		}
		else if (this.firepieces == 0)
		{
			return "Water";
		}
		else if (this.waterpieces == 0)
		{
			return "Fire";
		}
		return null;*/
		int x, y, firep, waterp;
		firep = 0;
		waterp = 0;
		for (x=0; x<8; x++)
		{
			for (y=0; y<8; y++)
			{
				if (this.pieces[x][y] != null)
				{
					if (this.pieces[x][y].isFire())
					{
						firep += 1;
					}
					else
					{
						waterp += 1;
					}
				}
			}
		}
		if (firep >0 && waterp>0)
		{
			return null;
		}
		if (firep == 0 && waterp == 0)
		{
			return "No one";
		}
		else if (firep == 0)
		{
			return "Water";
		}
		else if (waterp == 0)
		{
			return "Fire";
		}
		return null;
	}

	public Piece remove (int x, int y)
	{
		if (!isWithinBounds(x, y))
		{
			System.out.println("Out of bounds for remove");
			return null;
		}
		else if (pieceAt(x, y) == null)
		{
			System.out.println("No piece to remove");
			return null;
		}
		Piece oldpiece = pieceAt(x, y);
		if (oldpiece.isFire())
		{
			this.firepieces -= 1;
		}
		else
		{
			this.waterpieces -= 1;
		}
		this.pieces[x][y] = null;
		return oldpiece;

	}

	private Piece remove2 (int x, int y)
	{
		if (x<0 || y<0 || x>7 || y>7)
		{
			System.out.println("Trying to remove a piece at index out of bounds");
			return null;
		}
		if (this.pieces[x][y] == null)
		{
			System.out.println("No piece at " + "(" + x + "," + y + ")");
			return null;
		}
		else
		{
			Piece preturn = this.pieces[x][y];
			if (this.pieces[x][y].isFire())
			{
				this.firepieces -= 1;
			}
			else
			{
				this.waterpieces -= 1;
			}
			/*if (isWithinBounds(currentx, currenty) && this.pieces[x][y] == pieceAt(currentx, currenty))
			{
				this.currentpiece = null;
				this.currentx = -1;
				this.currenty = -1;
				this.hasSelectedPiece = false;
				this.hasMovedPiece = false;*/
				/*this.hasCaptured = false;*/
			/*}*/
			this.pieces[x][y] = null;
			this.hasPiece[x][y] = false;
/*			this.hasSelectedPiece = false;
			this.hasMovedPiece = false;*/
			return preturn;
		}
	}

	private boolean canSelect2(int x, int y)
	{
		/*System.out.println("inside CanSelect");*/
		if (isWithinBounds(x, y))
		{
			/*System.out.println("isWithinBounds");*/
			if (this.pieces[x][y] != null)
			{
				/*System.out.println("There is a piece");*/
				Piece p = this.pieceAt(x, y);
				if (isWithinBounds(currentx, currenty)/* && p.isFire() == pieceAt(currentx, currenty).isFire()*/ && this.hasSelectedPiece && !this.hasMovedPiece && pieceAt(currentx, currenty) == p)
				{
					/*System.out.println("Selected it 1st");*/
					/*System.out.println();*/
					return true;
				}
				if ((this.isFireTurn == p.isFire()) && (this.hasSelectedPiece == false || (this.hasSelectedPiece == true && this.hasMovedPiece == false)))
				{
					/*System.out.println("Selected it 2nd");*/
					/*System.out.println();*/
					return true;
				}
				else
				{
					return false;
				}

			}
			else
			{
				if (this.hasSelectedPiece && !this.hasMovedPiece && this.validMove(this.currentx, this.currenty, x, y))
				{
					return true;
				}
				/*else if (this.hasSelectedPiece && this.currentpiece != null && this.currentpiece.hasCaptured() &&  this.validMove(this.currentx, this.currenty, x, y) && Math.abs(x-this.currentx) > 1 && Math.abs(y-this.currenty)>1)
				{
					return true;
				}*/
				else if (isWithinBounds(currentx, currenty) &&
					this.hasSelectedPiece && this.hasMovedPiece && this.pieces[this.currentx][currenty] != null && 
					this.pieces[currentx][currenty].hasCaptured() && this.validMove(currentx, currenty, x, y))
				{
					return true;
				}
				else
				{
					return false;
				}
			}

		}
		else
		{
			return false;
		}
	}

	public boolean canSelect (int x, int y)
	{
		if (!isWithinBounds(x, y))
		{
			return false;
		}
		Piece p = pieceAt(x, y);
/*		if (p != null && this.isFireTurn != p.isFire())
		{
			return false;
		}*/
		if (p != null/* && this.isFireTurn == p.isFire()*/ && (!this.hasSelectedPiece || (this.hasSelectedPiece && !this.hasMovedPiece)))
		{
			return true;
		}
		else if (p != null && this.isFireTurn != p.isFire())
		{
			return false;
		}
		else if (p == null && hasSelectedPiece && !hasMovedPiece && validMove(currentx, currenty, x, y))
		{
			return true;
		}
		else if (p == null && hasSelectedPiece && hasMovedPiece && pieceAt(currentx, currenty) != null && pieceAt(currentx, currenty).hasCaptured() && 
			validMove(currentx, currenty, x, y))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	private void select2 (int x, int y)
	{
		if (this.pieces[x][y] != null)
		{
			this.currentpiece = this.pieces[x][y];
			this.currentx = x;
			this.currenty = y;
			this.hasSelectedPiece = true;
			this.hasMovedPiece = false;
		}
		else
		{
			if (isWithinBounds(currentx, currenty) && pieceAt(currentx, currenty) == null)
			{
				;
			}
			else
			{
				this.hasPiece[this.currentx][this.currenty] = false;
				this.pieceAt(currentx, currenty).move(x, y);
				this.currentx = x;
				this.currenty = y;
/*				System.out.println("***********" + currentx);
				System.out.println("***********" + currenty);*/
				this.hasSelectedPiece = true;
				this.hasMovedPiece = true;
			}	
		}
	}

	public void select(int x, int y)
	{
		if (pieceAt(x, y) != null)
		{
			hasMovedPiece = false;
			hasSelectedPiece = true;
			currentpiece = pieceAt(x, y);
		}
		else
		{
			pieceAt(currentx, currenty).move(x, y);
			hasMovedPiece = true;
		}
		currenty = y;
		currentx = x;
	}

	public void endTurn()
	{
		this.currentpiece = null;
/*		this.currentx = -1;
		this.currenty = -1;*/
		this.hasSelectedPiece = false;
		this.hasMovedPiece = false;
		///////////********
		if (pieceAt(currentx, currenty) != null) // Test 13
		{
			pieceAt(currentx, currenty).doneCapturing();
		}
		/*this.hasCaptured = false;*/
		this.isFireTurn = !this.isFireTurn;
	}

	public boolean canEndTurn()
	{
		if (this.hasMovedPiece)
		{
			return true;
		}
		if (isWithinBounds(currentx, currenty) && this.pieceAt(currentx, currenty) != null && this.pieceAt(currentx, currenty).hasCaptured())
		{
			return true;
		}

		return false;
	}

	private void printpieces()
	{
		System.out.println("No. of fire pieces: " + this.firepieces);
        System.out.println("No. of water pieces: " +this.waterpieces);
   		/*System.out.println(currentpiece == this.pieces[currentx][currenty]);*/
   		System.out.println("("+this.currentx+", "+this.currenty+")");
	}

	private boolean validMove(int xi, int yi, int xf, int yf)
	{
		if (isWithinBounds(xi, yi) && isWithinBounds(xf, yf) && this.pieces[xi][yi] != null)
		{
			Piece p = pieceAt(xi, yi);
			int dx, dy, ndx, ndy;
			dx = xf - xi;
			dy = yf - yi;
			// ndx = xi - xf;
			// ndy = yi - yf;
			if (p.isKing())
			{
				if (((dx == 1 && dy == 1) || (dx == 1 && dy == -1) || (dx == -1 && dy == -1) || (dx == -1 && dy == 1)) && (!p.hasCaptured()) && (this.pieces[xf][yf] == null))
				{	p.doneCapturing();
					return true;
				}
				else if (((dx == +2 && dy == +2 && this.pieces[xi+1][yi+1] != null && (p.isFire() != this.pieces[xi+1][yi+1].isFire())) || 
						(dx == +2 && dy == -2 && this.pieces[xi+1][yi-1] != null && (p.isFire() != this.pieces[xi+1][yi-1].isFire())) || 
						(dx == -2 && dy == -2 && this.pieces[xi-1][yi-1] != null && (p.isFire() != this.pieces[xi-1][yi-1].isFire())) ||
						(dx == -2 && dy == +2 && this.pieces[xi-1][yi+1] != null && (p.isFire() != this.pieces[xi-1][yi+1].isFire()))) &&
						(this.pieces[xf][yf] == null))
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
				if(p.isFire())
				{
					if (((dx == 1 && dy == 1) || (dx == -1 && dy == 1)) && (this.pieces[xf][yf] == null))
					{	p.doneCapturing();
						return true;
					}
					else if (((dx == 2 && dy == 2 && this.pieces[xi+1][yi+1] != null && (p.isFire() != this.pieces[xi+1][yi+1].isFire())) || 
							(dx == -2 && dy == 2 && this.pieces[xi-1][yi+1] != null && (p.isFire() != this.pieces[xi-1][yi+1].isFire()))) && (this.pieces[xf][yf] == null))
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
					if (((dx == 1 && dy == -1) || (dx == -1 && dy == -1)) && (this.pieces[xf][yf] == null))
					{
						p.doneCapturing();
						return true;
					}
					else if (((dx == 2 && dy == -2 && this.pieces[xi+1][yi-1] != null && this.pieces[xi+1][yi-1].isFire() != p.isFire()) ||
							 (dx == -2 && dy == -2 && this.pieces[xi-1][yi-1] != null && this.pieces[xi-1][yi-1].isFire() != p.isFire())) && (this.pieces[xf][yf] == null))
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

		else
		{
			return false;
		}
	}	

	public static void main(String[] args)
	{
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
		Board gameboard = new Board (false);
		gameboard.drawBoard();
		double x, y;
		x = 0;
		y = 0;
		int intx, inty;
		intx = 0;
		inty = 0;
		boolean gameOn = true;
		while(gameOn)
			{
				gameboard.drawBoard();
				if (StdDrawPlus.mousePressed())
				{
					x = StdDrawPlus.mouseX();
                	y = StdDrawPlus.mouseY();
                	intx = (int) x;
                	inty = (int) y;//typecasting
                	if (gameboard.canSelect(intx, inty))
                	{
                		gameboard.select(intx, inty);
                	}
                }
               	if (StdDrawPlus.isSpacePressed())
               	{
               		if (gameboard.canEndTurn())
               		{
               		gameboard.endTurn();
                	}
            	}
                if (gameboard.winner() != null)
                {
                	gameOn = false;
                }
                StdDrawPlus.show(20);
            }
	}
}