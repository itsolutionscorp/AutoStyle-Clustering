public class Board
{
	private Piece[][] pieces;
	private Piece selected;
	private boolean moved = false;
	private int selected_x;
	private int selected_y;
	private int player; //0 = fire, 1 = water
	private int fire_pieces = 0;
	private int water_pieces = 0;

	public Board(boolean shouldBeEmpty)
	{
		pieces = new Piece[8][8];
		if (!shouldBeEmpty)
		{
			for (int i = 0; i < 8; i += 2)
	    	{
	    		Piece p_f = new Piece(true, this, i, 0, "pawn");
	    		pieces[i][0] = p_f;
	    		Piece s_f = new Piece(true, this, i + 1, 1, "shield");
	    		pieces[i + 1][1] = s_f;
	    		Piece b_f = new Piece(true, this, i, 2, "bomb");
	    		pieces[i][2] = b_f;

	    		Piece b_w = new Piece(false, this, i + 1, 5, "bomb");
	    		pieces[i + 1][5] = b_w;
	    		Piece s_w = new Piece(false, this, i, 6, "shield");
	    		pieces[i][6] = s_w;
	    		Piece p_w = new Piece(false, this, i + 1, 7, "pawn");
	    		pieces[i + 1][7] = p_w;
	    	}
            fire_pieces = 12;
            water_pieces = 12;
		}
		player = 0;
	}

    private void drawBoard() 
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if ((i + j) % 2 == 0) 
                {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else
                {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
            }
        }
    }

    private void drawPieces()
    {
    	for (int i = 0; i < 8; i++)
    	{
    		for (int j = 0; j < 8; j++)
    		{
    			Piece p = pieces[i][j];
    			if (p != null)
    			{
    				String img = pieceImage(p);
    				StdDrawPlus.picture(i + 0.5, j + 0.5, img, 1, 1);
    			}
    		}
    	}
    }

    private String pieceImage(Piece p)
    {
    	String str = "";
    	if (p.isBomb())
    	{
    		if (p.isFire())
    		{
    			str = "img/bomb-fire-crowned.png";
    		}
    		else
    		{
    			str = "img/bomb-water-crowned.png";
    		}
    	}
    	else if (p.isShield())
    	{
    		if (p.isFire())
    		{
    			str = "img/shield-fire-crowned.png";
    		}
    		else
    		{
    			str = "img/shield-water-crowned.png";
    		}
    	}
    	else
    	{
    		if (p.isFire())
    		{
    			str = "img/pawn-fire-crowned.png";
    		}
    		else
    		{
    			str = "img/pawn-water-crowned.png";
    		}
    	}
    	if (!p.isKing())
    	{
    		str = str.substring(0, str.length()-12) + ".png";
    	}
    	return str;
    }

    private boolean inBounds(int x, int y)
    {
    	return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public Piece pieceAt(int x, int y)
    {
    	if (inBounds(x, y) && pieces[x][y] != null)
    	{
	    	return pieces[x][y];
	    }
	    return null; 
    }

    public boolean canSelect(int x, int y)
    {
    	if (inBounds(x,y) && (x+y)%2 == 0)
    	{
    		Piece p = pieceAt(x,y);
    		if (p != null && p.side() == player)
    		{
                if ((selected != null && !moved) || selected == null)
                {
                    return true;
                }
    		}
            else if (p == null && selected != null)
            {
                if (validMove(selected_x, selected_y, x, y) && !moved)
                {
                    return true;
                }
                else if (moved && selected != null && selected.hasCaptured()
                        && validMove(selected_x, selected_y, x, y))
                {
                    return true;
                }
            }
    	}
    	return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf)
    {
    	if (inBounds(xi, yi) && inBounds(xf, yf) && (xi + yi)%2 == 0 && (xf + yf)%2 == 0)
    	{
    		Piece p_i = pieces[xi][yi];
    		Piece p_f = pieces[xf][yf];

            int x_diff = Math.abs(xf - xi);
            int y_diff = Math.abs(xf - xi);
            int x_bw = (xi + xf)/2;
            int y_bw = (yi + yf)/2;

            Piece capPiece = pieceAt(x_bw, y_bw);

    		if (p_i != null && p_f == null)
    		{
				if (player == 0)
	    		{
	    			if (yf == yi + 1 && x_diff == 1 && !p_i.isKing() && !p_i.hasCaptured())
	    			{
	    				return true;
	    			}
                    else if (yf == yi + 2 && x_diff == 2 && capPiece != null && capPiece.side() != player)
                    {
                        return true;
                    }
                    else if (p_i.isKing())
                    {
                        if (y_diff == 1 && x_diff == 1 && !p_i.hasCaptured())
                        {
                            return true;
                        }
                        else if (x_diff == 2 && y_diff == 2 && capPiece != null && capPiece.side() != player)
                        {
                            return true;
                        }
                    }                   
	    		}
	    		else
	    		{
	    			if (yi == yf + 1 && x_diff == 1 && !p_i.isKing() && !p_i.hasCaptured())
	    			{
	    				return true;
	    			}
                    else if (yi == yf + 2 && x_diff == 2 && capPiece != null && capPiece.side() != player)
                    {
                        return true;
                    }
                    else if (p_i.isKing())
                    {
                        if (y_diff == 1 && x_diff == 1 && !p_i.hasCaptured())
                        {
                            return true;
                        }
                        else if (y_diff == 2 && x_diff == 2 && capPiece != null && capPiece.side() != player)
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
        Piece p = pieceAt(x,y);
        if (selected != null && p == null)
        {
            selected.move(x,y);
            moved = true;
        }
        else if (p != null)
        {
            selected = pieces[x][y];
        }
        selected_x = x;
        selected_y = y;
    }

    public void place(Piece p, int x, int y)
    {
    	if (inBounds(x,y) && p != null)
    	{
            if (pieceAt(x,y) != null)
            {
                remove(x,y);
            }
    		pieces[x][y] = p;

            boolean bool = p.isFire();
            if (bool)
            {
                fire_pieces += 1;
            }
            else
            {
                water_pieces += 1;
            }
    	}
    }

    public Piece remove(int x, int y)
    {
    	if (!inBounds(x,y))
    	{
    		System.out.println("You're trying to place a piece out of bounds!");
    		return null;
    	}
    	else if (pieces[x][y] == null)
    	{
    		System.out.println("There is no piece here!");
    		return null;
    	}
    	Piece p = pieces[x][y];
        if (p.isFire())
        {
            fire_pieces -= 1;
        }
        else
        {
            water_pieces -= 1;
        }
    	pieces[x][y] = null;
    	return p;
    }

    public boolean canEndTurn()
    {
        return moved;
    }

    public void endTurn()
    {
        if (selected != null)
        {
    	   selected.doneCapturing();
        }
    	if (player == 0)
    	{
    		player = 1;
    	}
    	else
    	{
    		player = 0;
    	}
    	moved = false;
        selected = null;
    	selected_x = -1;
    	selected_y = -1;
    }

    public String winner()
    {
        if (fire_pieces == 0 && water_pieces > 0)
        {
            return "Water";
        }
        else if (fire_pieces > 0 && water_pieces == 0)
        {
            return "Fire";
        }
        else if (fire_pieces == 0 && water_pieces == 0)
        {
            return "No one";
        }
    	return null;
    }

    public static void main(String[] args) 
    {
    	Board b = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
    	b.drawBoard();
    	b.drawPieces();
    	String winner = b.winner();

    	while (winner == null)
    	{
	    	if (StdDrawPlus.mousePressed())
	   		{
	  			double x = StdDrawPlus.mouseX();
    			double y = StdDrawPlus.mouseY();
                
    			if (b.canSelect((int)x, (int)y))
    			{
                    b.drawBoard();
    				StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
    				StdDrawPlus.filledSquare((int)x + 0.5, (int)y + 0.5, 0.5);
    				b.select((int)x, (int)y);
    			}
      		}
            if (StdDrawPlus.isSpacePressed())
            {
                if (b.canEndTurn())
                {
                    b.endTurn();
                }
            }
	    	winner = b.winner();

            b.drawPieces();
            StdDrawPlus.show(100);
    	}

    	System.out.println("The winner is " + winner + "!");
    }
}