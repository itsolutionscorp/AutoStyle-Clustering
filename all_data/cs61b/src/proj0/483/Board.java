public class Board
{
    public static Piece[][] pieces;
    public static boolean shouldBeEmpty;
    private Piece selectedPiece;
    private Piece pieceToSelect;
    private boolean hasMoved;
    private boolean hasPiece;
    private boolean hasSelected;
    private String player;
    

    public static void main (String args[])
    {
        
        StdDrawPlus.setYscale(0, 8);
        StdDrawPlus.setXscale(0, 8);

        Board b = new Board(false);

        b.createPieces();
        b.player = "Fire";

        while(true)
        {
            b.drawBoard(b);

            while (b.hasSelected == false)
            {
                if (StdDrawPlus.mousePressed())
                {
                    int y = ((int) StdDrawPlus.mouseY());
                    int x = ((int) StdDrawPlus.mouseX());
                    
                    if (b.canSelect(x, y))
                    {
                        b.select(x, y);
                        b.hasSelected = true;
                    }
                }
            }  
            while (b.hasMoved == false)
            {
                if (StdDrawPlus.mousePressed())
                {
                    int y = ((int) StdDrawPlus.mouseY());
                    int x = ((int) StdDrawPlus.mouseX());
                    if (b.canSelect(x, y))
                    {
                        b.select(x, y);
                        b.selectedPiece.move(x, y);
                        b.hasMoved = true;
                    }    
                } 
            } 

            StdDrawPlus.show(200);
        }
 
    }

    public Board(boolean shouldBeEmpty)
    {
        this.shouldBeEmpty = shouldBeEmpty;
        hasMoved = false;
        hasSelected = false;
        pieces = new Piece[8][8];
    }


    private void drawBoard(Board board)
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
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (!shouldBeEmpty)
                {
                    drawPiece(i, j, this);
                }
            }
        }
    }

    private static void drawPiece(int i, int j, Board b)
    {
        String path = "";
        
        if (i % 2 == 0)
        {
            if (j == 0)
            {
                path = "img/pawn-fire.png";
            }
            
            if (j == 2)
            {
                path = "img/bomb-fire.png";
            }
            
            if (j == 6)
            {
                path = "img/shield-water.png";
            }
        }
        else if (i % 2 != 0)
        {
            if (j == 1)
            {
                path = "img/shield-fire.png";
            }

            if (j == 5)
            {
                path = "img/bomb-water.png";
            }

            if (j == 7)
            {
                path = "img/pawn-water.png";
            }
        }

        StdDrawPlus.picture(i + .5, j + .5, path, 1, 1);
    }


    private void createPieces()
    {
        if (shouldBeEmpty)
            return;

        for (int i = 0; i < 8; i++)
        {

            for (int j = 0; j < 8; j++)
            {

                if (i % 2 != 0)
                {

                    if (j == 1)
                    {

                        pieces[i][j] = new Piece(true, this, i, j, "Shield");
                    }

                    if (j == 5)
                    {

                        pieces[i][j] = new Piece(false, this, i, j, "Bomb");
                    }

                    if (j == 7)
                    {

                        pieces[i][j] = new Piece(false, this, i, j, "Pawn");
                    }
                }

                else if (i % 2 == 0)
                {

                    if (j == 0)
                    {

                        pieces[i][j] = new Piece(true, this, i, j, "Pawn");
                    }

                    if (j == 2)
                    {

                        pieces[i][j] = new Piece(true, this, i, j, "Bomb");
                    }

                    if (j == 6)
                    {

                        pieces[i][j] = new Piece(false, this, i, j, "Shield");
                    }

                    }
            }
        }
    }
    

    
    public Piece pieceAt(int x, int y)
    {
        if ((y > 7) || (x > 7))
        {
            return null;
        }

        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if ((x == i) && (x == j))
                {
                    return pieces[i][j];
                }
            }
        }

        return null;
    }


    
    public void select(int x, int y) 
    { 
        selectedPiece = pieceAt(x, y); //να φτιαξω των κωδικ
        hasSelected = true;

    }

    public String winner()
    {
        int waterPieces = 0;
        int firePieces = 0;
        return null;
    }

    public void place(Piece p, int x, int y)
    {
        if ((x > 7) || (y > 7) || (p == null))
        {
            return;
        }
        
        if (pieceAt(x, y) != null)
        {
            remove(x, y);
        }

        pieces[x][y] = p;

    }

    public Piece remove(int x, int y)
    {
        if ((y > 7) || (x > 7)) {
            System.out.println("The X and Y are out of bounds. Sorry!");
            return null;
        }

        Piece p = pieceAt(x, y);

        if (p == null)
        {
            System.out.println("At places X and Y, there are no pieces.");
            return null;
        }

        pieces[x][y] = null;
        return p;
    }

    public boolean canEndTurn()
    {
        if (hasMoved)
            return true;
        return false;
    }

    public boolean canSelect(int x, int y)
    {
    pieceToSelect = pieceAt(x, y);

        
        if ((!hasMoved) && (pieceToSelect != null))
        {
            return true;
        }

        
        if ((hasSelected) && (!hasMoved) && (pieceToSelect == null))
            if (((x == selectedPiece.x + 1) && (y == selectedPiece.y - 1)) ||

                ((x == selectedPiece.x + 1) && (y == selectedPiece.y + 1)) ||

                ((x == selectedPiece.x - 1) && (y == selectedPiece.y + 1)) ||

                ((x == selectedPiece.x - 1) && (y == selectedPiece.y - 1)))
                return true;


        return false;
    }


    public void endTurn()
    {
        if (canEndTurn())
        {
            hasMoved = false;
            hasSelected = false;            
            player = other(player);
        }           
    }


    private String other(String player)
    {
        if (player == "fire")
            return "water";
        return "fire";
    }
}