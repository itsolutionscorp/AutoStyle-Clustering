public class Board
{
	private Piece[][] piece = new Piece[8][8];
	private boolean[][] used = new boolean[8][8];
	private boolean fireturn, selected, moved, emp;
	private Piece cur;
	private int[] dx = {-1, 1, 1, -1}, dy = {1, 1, -1, -1};
	
	public Board(boolean shouldBeEmpty)
	{
		emp = shouldBeEmpty;
        for(int i = 0; i < 8; i++)
        	for(int j = 0; j < 8; j++)
        		used[i][j] = false;

        if(!emp) initFish();
		
		this.selected = false;
		this.moved = false;
		this.fireturn = true;
		//drawBoard(8);
		if(!shouldBeEmpty)
			initFish();
	}

	private void initFish()
	{
		//StdDrawPlus.setXscale(0, 8);
        //StdDrawPlus.setYscale(0, 8);
		//String imgSrc1[] = {"/img/pawn-water.png","/img/shield-water.png","/img/bomb-water.png"};
		//String imgSrc2[] = {"/img/bomb-fire.png","/img/shield-fire.png","/img/pawn-fire.png"};
		
		for(int i = 0; i < 3; i++)
			for(int j = 0; j < 8; j++)
			{
				if((i+j) %2 == 0)
				{
					this.used[j][i] = true;
					//StdDrawPlus.picture(j + .5, i + .5,imgSrc2[2-i], 1, 1);
				}
				if(i == 0) this.piece[j][i] = new Piece(true, this, j, i, "pawn");
				if(i == 1) this.piece[j][i] = new Piece(true, this, j, i, "shield");
				if(i == 2) this.piece[j][i] = new Piece(true, this, j, i, "bomb");
			}

		for(int i = 5; i < 8; i++)
			for(int j = 0; j < 8; j++)
			{
				if((i+j) %2 == 0)
				{
					this.used[j][i] = true;
				//	StdDrawPlus.picture(j + .5, i + .5,imgSrc1[7-i], 1, 1);
				}

				if(i == 7) this.piece[j][i] = new Piece(false, this, j, i, "pawn");
				if(i == 6) this.piece[j][i] = new Piece(false, this, j, i, "shield");
				if(i == 5) this.piece[j][i] = new Piece(false, this, j, i, "bomb");
			}
	}

	private void drawState()
	{
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++)
				if(used[i][j])
				{
					if(this.selected && (this.cur == piece[i][j]) )
					{
						StdDrawPlus.filledSquare(i + .5, j + .5, .5);
					}
					drawSpec(i, j, piece[i][j]);
				}
	}

	private boolean valpos(int x, int y)
	{
		if (x >= 8 || y >= 8 || x < 0 || y < 0) return false;
		return true;
	}

	public boolean canSelect(int x, int y)
	{
		Piece c = pieceAt(x, y);
		boolean match = false;
		//ok System.out.println(c.side());
		
		if(c != null) // fishka
		{
			if(c.isFire() != fireturn) return false;
			//System.out.println("amk");
			// same color
			if(!this.selected) //not selected
				return true;
			
			else if(!this.moved)
				return true;
		}

		else // already selected a piece, trying to move/cap
		{
			if(!this.selected) return false;
			if(!this.cur.isKing())
			{
				if(!this.cur.isFire()) // water turn
				{
					for(int i = 0; i < 2; i++) // go up and see if there is a water piece
					{
						int nx = x + dx[i], ny = y + dy[i];
						if(pieceAt(nx,ny) == this.cur && !this.moved) // match
							match = true;
						// check capture
						nx = x + dx[i]*2;	ny = y + dy[i]*2;
						int ix = x + dx[i]*1, iy = y + dy[i]*1;
						if(pieceAt(ix, iy) == null) continue;
						if(!this.cur.hasCaptured() && !this.moved) // capture first time att. shouldn't be moved bef
						{
							if(pieceAt(nx, ny) == this.cur && pieceAt(ix, iy).isFire() != this.cur.isFire())
								match = true;
						}
						else if(this.cur.hasCaptured()) // try to multicap
						{
							if(pieceAt(nx, ny) == this.cur && pieceAt(ix, iy).isFire() != this.cur.isFire())
								match = true;
						}
					}
				}

				else // fire turn
				{
					for(int i = 2; i < 4; i++) // go down and see if there is a fire piece
					{
						int nx = x + dx[i], ny = y + dy[i];
						if(pieceAt(nx,ny) == this.cur && !this.moved) // match
							match = true; 
						// check capture
						nx = x + dx[i]*2;	ny = y + dy[i]*2;
						int ix = x + dx[i]*1, iy = y + dy[i]*1;
						if(pieceAt(ix, iy) == null) continue;
						if(!this.cur.hasCaptured() && !this.moved) // capture first time att. shouldn't be moved bef
						{
							if(pieceAt(nx, ny) == this.cur && pieceAt(ix, iy).isFire() != this.cur.isFire())
								match = true;
						}
						else if(this.cur.hasCaptured()) // try to multicap
						{
							if(pieceAt(nx, ny) == this.cur && pieceAt(ix, iy).isFire() != this.cur.isFire())
								match = true;
						}
					}
				}
			}

			else
			{
				for(int i = 0; i < 4; i++) // go down and see if there is a fire piece
				{
					int nx = x + dx[i], ny = y + dy[i];
					if(pieceAt(nx,ny) == this.cur && !this.moved) // match
						match = true; 
					// check capture
					nx = x + dx[i]*2;	ny = y + dy[i]*2;
					int ix = x + dx[i]*1, iy = y + dy[i]*1;
					if(pieceAt(ix, iy) == null) continue;
					if(!this.cur.hasCaptured() && !this.moved) // capture first time att. shouldn't be moved bef
					{
						if(pieceAt(nx, ny) == this.cur && pieceAt(ix, iy).isFire() != this.cur.isFire())
							match = true;
					}
					else if(this.cur.hasCaptured()) // try to multicap
					{
						if(pieceAt(nx, ny) == this.cur && pieceAt(ix, iy).isFire() != this.cur.isFire())
							match = true;
					}
				}
			}

		}
		return match;
	}

	private void drawSpec(int x, int y, Piece p)
	{
		String tt;
		if(p.isBomb()) tt = "bomb";
		else if(p.isShield()) tt = "shield";
		else tt = "pawn";

		String img = "/img/" + tt + "-";
		if (p.isFire())	img += "fire";
		else img += "water";
		if(p.isKing())	img += "-crowned";
		img += ".png";
		StdDrawPlus.picture(x + .5, y + .5, img, 1, 1);
	}

	public void select(int x, int y)
	{
		//StdDrawPlus.filledSquare(x + .5, y + .5, .5);
		if(!this.used[x][y])
		{
			this.cur.move(x, y);
			this.moved = true;
		}
		else
		{
			this.selected = true;
			this.cur = piece[x][y];
		}
		//drawState();
	}

	private void drawBoard(int N)
	{
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < N; j++)
			{
				if((i+j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
				else StdDrawPlus.setPenColor(StdDrawPlus.RED);
				StdDrawPlus.filledSquare(i + .5, j + .5, .5);
				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
			}
		}
	}

	public Piece pieceAt(int x, int y)
	{
 		if (x >= 8 || y >= 8 || x < 0 || y < 0) return null;
 		else if (this.used[x][y]) return this.piece[x][y];
 		else return null;
	}

	public void place(Piece p, int x, int y)
	{
		if(valpos(x, y))
		{
			this.used[x][y] = true;
			this.piece[x][y] = p;
		}
	}

	public Piece remove(int x, int y)
	{
		if(!valpos(x, y))
		{
			System.out.println("out of bounds");
			return null;
		}

		if(this.used[x][y])
		{
			this.used[x][y] = false;
			Piece p = this.piece[x][y];
			this.piece[x][y] = null;
			return p;
		}
		
		System.out.println("no piece!");
		return null;
	}
 

	public String winner()
	{
		int f = 0, w = 0;
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++)
			{
				if(this.used[i][j]) 
				{
					if(this.piece[i][j].isFire()) f += 1;
					else w += 1;
				}
			}
		if(f+w == 0) return "No one";
		else if(f > 0 && w == 0) return "Fire";
		else if(w > 0 && f == 0) return "Water";
		return null;
	}

	public boolean canEndTurn()
	{
		if (this.selected && this.moved)
			return true;
		return false;
	}

	public void endTurn()
	{
		this.fireturn = !this.fireturn;
		this.selected = false;
		this.moved = false;
		this.cur.doneCapturing();
		this.cur = null;
	}

	public static void main(String[] args)
	{
		StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

		Board b = new Board(true);
		while(true) 
		{
            b.drawBoard(8);
            if(!b.emp) b.drawState();
            //System.out.println(b.piece[7][7].side());
            if (StdDrawPlus.mousePressed()) 
            {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
            	if( b.canSelect( (int) x, (int) y ) ) b.select( (int) x, (int) y );
            }

            if(StdDrawPlus.isSpacePressed())
            {
            	if(b.canEndTurn()) b.endTurn();
            	if(b.winner() != null)
            	{
            		System.out.println(b.winner());
            		break;
            	}
            }
            StdDrawPlus.show(100);
        }
	}


}