public class Board{
	private Piece[][] pieces;
    private boolean selectP = false;
    private boolean hasMoved = false;
    private boolean turn = true;
    private int xvar = -1;
    private int yvar = -1;
	public static void main(String[] args)
	{ 
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        //pieces = new Piece[N][N]; 
		Board yes = new Board(false);
       while(true)
        {
		    yes.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(yes.canSelect((int) x,(int) y) == true)
                {
                    yes.select((int) x, (int) y);
                }
            }


                /*StdDrawPlus.filledSquare(xvari + .5, yvari + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);*/
            StdDrawPlus.show(100);
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
            	if(pieces[i][j] != null && pieces[i][j].isBomb() == true && pieces[i][j].isFire() == true)
            	{
            		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                }
            	if(pieces[i][j] != null && pieces[i][j].isShield() == true && pieces[i][j].isFire() == true)
            	{
            		StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);

            	}
            	if(pieces[i][j]  != null && pieces[i][j].isShield() == false && pieces[i][j].isBomb() == false && pieces[i][j].isFire() == true)
            	{
            		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);

            	}
            	if(pieces[i][j]  != null  && pieces[i][j].isBomb() == true && pieces[i][j].isFire() != true)
            	{
            		StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);

            	}
            	if(pieces[i][j] != null && pieces[i][j].isShield() == true && pieces[i][j].isFire() != true)
            	{
            		StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);

            	}
            	if(pieces[i][j] != null && pieces[i][j].isBomb() != true && pieces[i][j].isShield() != true && pieces[i][j].isFire() != true)
            	{
            		StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);

            	}

            }
        }
	}

	private void placeEmptyBoard(int N) {
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }

    private void placeBoard(int N)
   	{
   		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(j == 2 && (i == 0 || i == 2 || i == 4 || i == 6)) {
                	pieces[i][j] = new Piece(true, this, i, j, "bomb");
                     
                }
                else if(j == 1 && (i == 1 || i == 3 || i == 5 || i == 7))
                {
                	pieces[i][j] = new Piece(true, this, i, j, "shield");
                	
                }
                else if(j == 0 && (i == 0 || i == 2 || i == 4 || i == 6)) {
                	pieces[i][j] = new Piece(true, this, i, j, "pawn");
                	
                }
                else if(j == 5 && (i == 1 || i == 3 || i == 5 || i == 7))
                {
                	pieces[i][j] = new Piece(false, this, i, j, "bomb");
                	
                }
                else if(j == 6 && (i == 0 || i == 2 || i == 4 || i == 6))
                {
                	pieces[i][j] = new Piece(false, this, i, j, "shield");
                	
                }
                else if(j == 7 && (i == 1 || i == 3 || i == 5 || i == 7))
                {
                	pieces[i][j] = new Piece(false, this, i, j, "pawn");
                	
                }
   			}
		}
	}
	public Board(boolean shouldBeEmpty){
		if(shouldBeEmpty == true)
		{
			placeEmptyBoard(8);
		}
		else
		{
            pieces = new Piece[8][8];
			placeBoard(8);
		}
	}
	public Piece pieceAt(int x, int y)
	{
		if(pieces[x][y] != null && (x < 8) && (y < 8))
		{
			return pieces[x][y];
		}
		else
		{
			return null;
		}
	}
	public boolean canSelect(int x, int y)
	{
        if ((x <= 7) && (y <= 7) && (x >= 0) && (y >= 0)) {
            if (!selectP) {
                if (pieces[x][y].isFire() == turn){
                    if (pieces[x][y] != null){
                        return true;
                    }
                }
            }
            if(hasMoved != true){
            if ((selectP) && (pieces[x][y].isFire() == turn) && (pieces[x][y] == null)){
                return true;
            }
            if ((selectP) && (pieces[x][y].isFire() == turn) && (pieces[x][y] != null)){
                return true;
            }
            }   
            if ((selectP) && (pieces[x][y].hasCaptured() == true)){
                return true;
            }
            else{
                return false;
            }
        }
        return false;
	}
     private boolean validMovePosRed(int xi, int yi, int xf, int yf)
    {
        if(pieces[xi][yi] != null && pieces[xf][yf] == null)
        {
            if(xf == 1+xi && yf == 1+yi)
            {
                return true;
            }
            else if(xf == xi-1 && yf == 1+yi)
            {
                return true;
            }
            else if(pieces[xf-1][yf-1] != null && pieces[xf-1][yf-1].isFire() != pieces[xi][yi].isFire())
            {
                return true;
            }
            else if(pieces[xf+1][yf-1] != null && pieces[xf+1][yf-1].isFire() != pieces[xi][yi].isFire())
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
            return false;
        }
    }
    private boolean validMovePosBlue(int xi, int yi, int xf, int yf)
    {
        if(pieces[xi][yi] != null && pieces[xf][yf] == null)
        {
            if(xf==1+xi && yf==yi-1)
            {
                return true;
            }
            else if(xf==xi-1 && yf==yi-1)
            {
                return true;
            }
            else if(pieces[xf+1][yf+1] != null && pieces[xf+1][yf+1].isFire() != pieces[xi][yi].isFire())
            {
                return true;
            }
            else if(pieces[xf-1][yf+1] != null && pieces[xf-1][yf+1].isFire() != pieces[xi][yi].isFire())
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
            return false;
        }

    }

    public void select(int x, int y)
	{
        
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        xvar = x;
        yvar = y;
        selectP = true;
    }
    public void place(Piece p, int x, int y)
	{
		if(p != null && x < 8 && y < 8 && x >= 0 && y >= 0)
		{
			pieces[x][y] = p;
		}
    }
    public Piece remove(int x, int y)
    {
        Piece p1 = pieces[x][y];
        if(pieces[x][y] == null)
        {
            System.out.println("There is no piece located at this space.");
            return null;
        }
        else if(x > 7 || x < 0 || y > 7 || y < 0)
        {
            System.out.println("This coordinate is out of bounds.");
            return null;
        }
        else
        {
            pieces[x][y] = null;
        }
        return p1;
    }
    public boolean canEndTurn()
    {
        if(hasMoved == true || pieces[xvar][yvar].hasCaptured() == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void endTurn()
    {
        if(canEndTurn()!=false){
        turn = false;
        }
    }
    public String winner(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((pieces[i][j].isFire() == true) && (pieces[i][j].isFire() == false)) {
                    return "The red team wins";
                }
                if (pieces[i][j].isFire() != true){
                    return "The blue team wins";
                }
                if (pieces[i][j] == null){
                    return "Tie";
                }
            }
        }
        return null;
    }

}
