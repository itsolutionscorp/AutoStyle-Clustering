public class Board{
	private Piece[][] pieces;
	private int N;
	private boolean isFireTurn;
	private Piece selectedPiece;
	private int selectedX;
	private int selectedY;
	private boolean moved;
	private boolean bombExploded;



	public Board(boolean shouldBeEmpty)
	{
		this.N = 8;
		pieces = new Piece[this.N][this.N];
		if (!shouldBeEmpty)
			this.setBoard(N);
		isFireTurn = true;
		moved = false;
		bombExploded = false;
		
	}

	private void setBoard(int N)
	{
		for (int x = 0; x < N/2; x++)
		{
			pieces[2*x][0] = new Piece(true, this, 2*x, 0, "pawn");
			pieces[2*x + 1][1] = new Piece(true, this, 2*x + 1, 1, "shield");
			pieces[2*x][2] = new Piece(true, this, 2*x, 2, "bomb");
			
			pieces[2*x + 1][5] = new Piece(false, this, 2*x + 1, 5, "bomb");
			pieces[2*x][6] = new Piece(false, this, 2*x, 6, "shield");
			pieces[2*x + 1][7] = new Piece(false, this, 2*x + 1, 7, "pawn");
		}

	}

	public Piece pieceAt(int x, int y)
	{
		if (x >= this.N || y >= this.N || x < 0 || y < 0)
				return null;
		return this.pieces[x][y];
	}

	public boolean canSelect(int x, int y)
	{
		if (bombExploded)
			return false;
		Piece place = pieces[x][y];
		// if (place == selectedPiece)
		// 	return false;
		if (this.moved && place == null && Math.abs(selectedX-x) == 2)
		{
			// a piece is able to jump multiple times withhout even killing something
			return validMove(this.selectedX, this.selectedY, x, y);
		}

		if (place != null && place.isFire() == isFireTurn)
		{
			if (selectedPiece == null)
				return true;
			else
				return !moved;
		}
		if (place == null && !moved)
		{
			if (selectedPiece != null)
				return validMove(this.selectedX, this.selectedY, x, y);

		}
		return false;
	}

	private boolean validMove(int xi, int yi, int xf, int yf)
	{
		if (this.outOfBounds(xi,yi) || this.outOfBounds(xf,yf))
			return false;
		int x = xi - xf;
		int y = yi - yf;
		if (Math.abs(x) != Math.abs(y))
			return false;
		Piece piece = pieceAt(xi,yi);
		// fix this, the logic is kind of off
		if (piece != null)
		{
			if(piece.isFire() == this.isFireTurn)
			{

				if (piece.isFire() && yf > yi || (!piece.isFire()) && yi > yf || piece.isKing())
				{
					if (Math.abs(x) == 1)
						return pieceAt(xf,yf) == null;
					if (Math.abs(x) == 2)
						if (pieceAt((xi + xf)/2, (yi + yf)/2) != null && 
							pieceAt((xi + xf)/2, (yi + yf)/2).isFire() != this.isFireTurn)
							return pieceAt(xf,yf) == null;
				}
			}
	
			
		}
		else
		{
			return true;
		}

		// if (b.pieceAt(xi,yi).isKing() || true)
		// {
		// 	if ((pieces[xi][yi].isFire() && yf > yi || (!pieces[xi][yi].isFire()) && yi > yf))
		// 	{
		// 		if (Math.abs(x) == 1)
		// 			return pieces[xf][yf] == null;
		// 		if (Math.abs(x) == 2)
		// 			if (pieces[(xi + xf)/2][(yi + yf)/2] != null && pieces[(xi + xf)/2][(yi + yf)/2].isFire() != this.isFireTurn)
		// 				return pieces[xf][yf] == null;
		// 	}	
		// }
		// else
		// {

		// }
		return false;

	}

	public void select(int x, int y)
	{
		Piece piece = pieceAt(x,y);
		if (!moved)
		{
			if (this.selectedPiece == null)
			{
				this.selectedPiece = pieces[x][y];
				this.selectedX = x;
				this.selectedY = y;
			}
			else if(this.selectedPiece.isFire() == isFireTurn && piece != null)
			{
				this.selectedPiece = pieces[x][y];
				this.selectedX = x;
				this.selectedY = y;
			}
			else
			{
				this.selectedPiece.move(x,y);
				this.selectedX = x;
				this.selectedY = y;
				moved = true;
			}
		}
		else
		{
			if (!bombExploded)
			{
				this.selectedPiece.move(x,y);
				this.selectedX = x;
				this.selectedY = y;
			}
		}

	}

	public void place(Piece p, int x, int y)
	{
		if (!outOfBounds(x,y))
			pieces[x][y] = p;
	}

	public Piece remove(int x, int y)
	{
		if (this.outOfBounds(x,y))
		{
			System.out.println("Out of bounds.");
			return null;
		}
		if (pieces[x][y] == null)
		{
			System.out.println("No piece was found.");
			return null;
		}
		else
		{
			Piece removed = pieces[x][y];
			pieces[x][y] = null;
			if (removed.isBomb() && removed.hasCaptured())
			{
				selectedPiece = null;
				bombExploded = true;
			}
			return removed;
		}
	}

	public boolean canEndTurn()
	{
		boolean result = false;
		if (selectedPiece != null)
			result = selectedPiece.hasCaptured();
		return moved || result;
	}

	public void endTurn()
	{
		isFireTurn = !isFireTurn;
		if (selectedPiece != null)
			selectedPiece.doneCapturing();
		selectedPiece = null;
		moved = false;
		bombExploded = false;
	}

	public String winner()
	{
		int fire = 0;
		int water = 0;
		for (Piece[] x: pieces)
			for (Piece y: x)
				if (y != null)
					if (y.isFire())
						fire += 1;
					else
						water += 1;
		if (fire == 0 && water == 0)
			return "No one";
		else if (fire == 0)
			return "Water";
		else if (water == 0)
			return "Fire";
		else
			return null;

	}

	private boolean outOfBounds(int x, int y)
	{
		return x < 0 || y < 0 || x >= this.N || y >= this.N;
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if (i == selectedX && j == selectedY && selectedPiece != null) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null)
                {
                	String img = "img\\";
                	if (pieces[i][j].isBomb())
                		img += "bomb";
                	else if(pieces[i][j].isShield())
                		img += "shield";
                	else
                		img += "pawn";

                	if (pieces[i][j].isFire())
                		img += "-fire";
                	else
                		img += "-water";
                	if (pieces[i][j].isKing())
                		img += "-crowned";
                	img += ".png";
                	StdDrawPlus.picture(i + .5, j + .5, img, 1, 1);
                }
                
            }
        }
    }

    private boolean isOver()
    {
    	int blue = 0;
    	int red = 0;
    	for (Piece[] x: pieces)
    		for (Piece y: x)
    		{
    			if (y != null)
    				if (y.isFire())
    					red += 1;
    				else
    					blue += 1;
    		}

    	return red == 0 || blue == 0;
    }

	public static void main(String args[])
	{  
		Board board = new Board(false);
        StdDrawPlus.setXscale(0, board.N);
        StdDrawPlus.setYscale(0, board.N);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            board.drawBoard(board.N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if (board.canSelect(x,y))
                	board.select(x,y);
            }
            if (StdDrawPlus.isSpacePressed())
            {
            	if (board.canEndTurn())
            		board.endTurn();
            }

            StdDrawPlus.show(75);
            if (board.isOver())
            {
            	System.out.println(board.winner());
            	break;
            }
        }
	}
}