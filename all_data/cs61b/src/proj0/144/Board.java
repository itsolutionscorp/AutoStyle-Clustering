public class Board
{
	private Piece[][] pieces=new Piece[8][8];
    private boolean fireTurn;
    private boolean piecemoved;
    private Piece piecemoving;
    private Piece lastpiecemoved;
    private int lastpiecemovedx;
    private int lastpiecemovedy;
    private boolean multicapture;
    private boolean explosion;
    private int piecemovingx;
    private int piecemovingy;
    private boolean captured;
    private boolean justCrowned;

    private boolean willCapture(int x, int y, int xcoord, int ycoord)
    {
        Piece piece = pieces[x][y];
        if (piece == null)
        {
            return false;
        }
        Piece captured = null;
        if (!piece.isKing() && Math.sqrt(Math.pow((x-xcoord),2) + Math.pow((y-ycoord),2))==Math.sqrt(2))
        {
            if (piece.isFire() && ycoord>y || !piece.isFire() && y>ycoord)
            {
                x=xcoord;
                y=ycoord;
            }
        }
        else if (piece.isKing() && Math.sqrt(Math.pow((x-xcoord),2) + Math.pow((y-ycoord),2))==Math.sqrt(2) )
        {
            x=xcoord;
            y=ycoord;
        }
        else if (!piece.isKing() && Math.sqrt(Math.pow((x-xcoord),2) + Math.pow((y-ycoord),2))==Math.sqrt(8) && piece.isFire() && ycoord>y || !piece.isFire() && y>ycoord)
        {
            if(x<xcoord && y<ycoord && pieceAt(x+1,y+1)!=null && pieceAt(x+1,y+1).isFire()!=piece.isFire())
            {
                captured=pieceAt(x+1,y+1);
            }
            else if (x>xcoord && y>ycoord && pieceAt(x-1,y-1)!=null && pieceAt(x-1,y-1).isFire()!=piece.isFire())
            {
                captured=pieceAt(x-1,y-1);
            }
            else if (x<xcoord && y>ycoord && pieceAt(x+1,y-1)!=null && pieceAt(x+1,y-1).isFire()!=piece.isFire())
            {
                captured=pieceAt(x+1,y-1); 
            }
            else if (x>xcoord && y<ycoord && pieceAt(x-1,y+1)!=null && pieceAt(x-1,y+1).isFire()!=piece.isFire())
            {
                captured=pieceAt(x-1,y+1);   
            }
            x=xcoord;
            y=ycoord;
        }
        else if (piece.isKing() && Math.sqrt(Math.pow((x-xcoord),2) + Math.pow((y-ycoord),2))==Math.sqrt(8))
        {
            if(x<xcoord && y<ycoord && pieceAt(x+1,y+1)!=null && pieceAt(x+1,y+1).isFire()!=piece.isFire())
            {
                captured=pieceAt(x+1,y+1);
            }
            else if (x>xcoord && y>ycoord && pieceAt(x-1,y-1)!=null && pieceAt(x-1,y-1).isFire()!=piece.isFire())
            {
                captured=pieceAt(x-1,y-1);
            }
            else if (x<xcoord && y>ycoord && pieceAt(x+1,y-1)!=null && pieceAt(x+1,y-1).isFire()!=piece.isFire())
            {
                captured=pieceAt(x+1,y-1); 
            }
            else if (x>xcoord && y<ycoord && pieceAt(x-1,y+1)!=null && pieceAt(x-1,y+1).isFire()!=piece.isFire())
            {
                captured=pieceAt(x-1,y+1);   
            }
            x=xcoord;
            y=ycoord;
        }

        return captured != null;
    }

	public Board(boolean shouldBeEmpty)
	{

        if (!shouldBeEmpty)
        {
            pieces[0][0]= new Piece(true, this, 0, 0, "pawn");
            pieces[2][0]=new Piece(true, this, 2, 0, "pawn");
            pieces[4][0]=new Piece(true, this, 4, 0, "pawn");
            pieces[6][0]=new Piece(true, this, 6, 0, "pawn");

            pieces[1][1]=new Piece(true, this, 1, 1, "shield");
            pieces[3][1]=new Piece(true, this, 3, 1, "shield");
            pieces[5][1]=new Piece(true, this, 5, 1, "shield");
            pieces[7][1]=new Piece(true, this, 7, 1, "shield");

            pieces[0][2]= new Piece(true, this, 0, 2, "bomb");
            pieces[2][2]=new Piece(true, this, 2, 2, "bomb");
            pieces[4][2]=new Piece(true, this, 4, 2, "bomb");
            pieces[6][2]=new Piece(true, this, 6, 2, "bomb");

            pieces[1][7]=new Piece(false, this, 1, 7, "pawn");
            pieces[3][7]=new Piece(false, this, 3, 7, "pawn");
            pieces[5][7]=new Piece(false, this, 5, 7, "pawn");
            pieces[7][7]=new Piece(false, this, 7, 7, "pawn");

            pieces[1][5]=new Piece(false, this, 1, 5, "bomb");
            pieces[3][5]=new Piece(false, this, 3, 5, "bomb");
            pieces[5][5]=new Piece(false, this, 5, 5, "bomb");
            pieces[7][5]=new Piece(false, this, 7, 5, "bomb");

            pieces[0][6]=new Piece(false, this, 0, 6, "shield");
            pieces[2][6]=new Piece(false, this, 2, 6, "shield");
            pieces[4][6]=new Piece(false, this, 4, 6, "shield");
            pieces[6][6]=new Piece(false, this, 6, 6, "shield");
        }
        fireTurn=true;
        captured = false;
        explosion = false;
        multicapture = false;
        justCrowned = false;
	}

    public static void main(String[] args) {


         int N = 8;
         StdDrawPlus.setXscale(0, N);
         StdDrawPlus.setYscale(0, N);
         Board b=new Board(false);
         
         while(true) {
            b.drawBoard(N);
             if (StdDrawPlus.mousePressed()) {
                 double x = StdDrawPlus.mouseX();
                 double y = StdDrawPlus.mouseY();
                 if (b.canSelect((int)x, (int)y))
                 {
                    Piece piece = b.pieceAt((int)x, (int)y);
                    b.select((int)x, (int)y);
                    if (piece != null)
                    {
                        b.captured = piece.hasCaptured();
                    }
                 }
             }
             if (StdDrawPlus.isSpacePressed()) {
                 if (b.canEndTurn())
                 {
                    b.endTurn();
                 }
             }             
            StdDrawPlus.show(15);
         }

    }

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) 
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieceAt(i,j)!=null && !pieceAt(i,j).isKing()) {
                    if (pieceAt(i,j).isFire())
                    {
                        if (pieceAt(i,j).isBomb())
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        else if (pieceAt(i,j).isShield())
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        else
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    }
                    else
                    {
                        if (pieceAt(i,j).isBomb())
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        else if (pieceAt(i,j).isShield())
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        else
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    }
                }
                else if (pieceAt(i,j)!=null)
                {
                    if (pieceAt(i,j).isFire())
                    {
                        if (pieceAt(i,j).isBomb())
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                        else if (pieceAt(i,j).isShield())
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                        else
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                    }
                    else
                    {
                        if (pieceAt(i,j).isBomb())
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                        else if (pieceAt(i,j).isShield())
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                        else
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png-crowned", 1, 1);
                    }
                }
            }
        }
    }

    public boolean canSelect(int x, int y)
    {
        if (!captured && piecemoved)
        {
            return false;
        }
        else if (captured && pieceAt(x, y) != null)
        {
            return false;
        }
        else if (pieceAt(x, y) != null && pieceAt(x, y).isKing())
        {
            return multicapture;
        }
        else if (pieceAt(x, y) == null && willCapture(lastpiecemovedx, lastpiecemovedy, x, y))
        {
            return true;
        }
        else if (captured)
        {
            return true;
        }
        // Not your piece to move.
        else if (!piecemoved && piecemoving == null && (pieceAt(x,y) == null || pieceAt(x,y).isFire() != fireTurn))
        {
            return false;
        }
        else if (pieceAt(x,y) != null && pieceAt(x,y).isFire() != fireTurn)
        {
            return false;
        }
        // Select same piece
        else if (!captured && pieceAt(x,y) != null && pieceAt(x,y) == piecemoving)
        {
            return true;
        }
        // Your piece to move, no previous moves
        else if (piecemoving == null && !piecemoved && pieceAt(x, y) != null)
        {
            if (pieceAt(x,y).isFire() == fireTurn)
            {
                return true;
            }
            return false;
        }
        else if (piecemoving != null)
        {
            if (pieceAt(x, y) == null)
            {
                return true;
            }
            return false;
        }
        else if (pieceAt(x,y) == null && !piecemoved)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void select (int x, int y)
    {
        boolean wasKing = true;
        Piece piece = pieceAt(x,y);
        if (piece != null)
        {

            piecemovingx = x;
            piecemovingy = y;
            piecemoving=pieceAt(x,y);
            wasKing = piecemoving.isKing();
        }
        else
        {
            piecemoving.move(x,y);
            lastpiecemoved = piecemoving;
            lastpiecemovedx = x;
            lastpiecemovedy = y;
            if (piecemoving.isBomb() && piecemoving.hasCaptured())
            {
                explosion = true;
            }
            else
            {
                explosion = false;
            }
            piecemoved = true;
        }
        multicapture = captured;
        captured = piecemoving.hasCaptured();
        if (!wasKing && piecemoving.isKing())
        {
            justCrowned = true;
        }
    }

    public boolean canEndTurn()
    {
        return piecemoved;
    }

    public void endTurn()
    {
        if (!canEndTurn())
        {
            return;
        }
        fireTurn=!fireTurn;
        piecemoving.doneCapturing();
        captured = false;
        piecemoving = null;
        piecemoved = false;
        explosion = false;
        multicapture = false;
        justCrowned = false;
    }

    public String winner()
    {
        boolean firewins = true;
        boolean waterwins = true;

        for (int i=0; i<8; i++)
        {
            for (int j=0; j<8; j++)
            {
                if (pieceAt(i, j) == null)
                {
                    continue;
                }
                if (pieceAt(i,j).isFire())
                {
                    waterwins=false;
                }
                else
                {
                    firewins=false;
                }
            }
        }
        if (explosion)
        {
            return "No one";
        }
        else if (firewins && !waterwins)
        {
            return "Fire";
        }
        else if (waterwins && !firewins)
        {
            return "Water";
        }
        else
        {
            return null;
        }
    }

    
    public Piece pieceAt(int x, int y){
        if (x>=8 || x<0 || y>=8 || y<0)
        {
            return null;
        }
    	return pieces[x][y];
    }

    public void place(Piece p, int x, int y)
    {
    	if (p != null && x<8 && x>=0 && y<8 && y>=0)
    	{
    		for (int i=0; i<8; i++)
    		{
    			for (int j=0; j<8; j++)
    			{
    				if (pieces[i][j]==p)
    				{
    					pieces[i][j]=null;
    				}
    			}
    		}
    		pieces[x][y]=p;
    	}
    }
    public Piece remove(int x, int y)
    {
        if (x<0 || x>8 || y<0 || y>8)
        {
            System.out.println("Piece attempted to be removed is out of bounds");
            return null;
        }
        else if (pieces[x][y]==null)
        {
            System.out.println("There is no piece to be removed at this location");
        }
        else
        {
            Piece returnpiece=pieces[x][y];
            pieces[x][y]=null;
            return returnpiece;
        }
        return null;
    }
}