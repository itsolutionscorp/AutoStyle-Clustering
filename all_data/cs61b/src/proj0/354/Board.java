public class Board  {
    private static final int DIMENSION = 8; //board dimension
    
    private Piece[][] piecePositions = new Piece[DIMENSION][DIMENSION];
    public Board(boolean shouldBeEmpty)  {
        if (!shouldBeEmpty) {
            for(int i = 0; i < DIMENSION/2; i++)
                piecePositions[2*i][0] = new Piece(true, this, 2*i, 0, "pawn");
            for(int i = 0; i < DIMENSION/2; i++)
                piecePositions[2*i+1][1] = new Piece(true, this, 2*i+1, 1, "shield");
            for(int i = 0; i < DIMENSION/2; i++)
                piecePositions[2*i][2] = new Piece(true, this, 2*i, 2, "bomb");
            
            for(int i = 0; i < DIMENSION/2; i++)
                piecePositions[2*i+1][DIMENSION-3] = new Piece(false, this, 2*i+1, DIMENSION-3, "bomb");
            for(int i = 0; i < DIMENSION/2; i++)
                piecePositions[2*i][DIMENSION-2] = new Piece(false, this, 2*i, DIMENSION-2, "shield");
            for(int i = 0; i < DIMENSION/2; i++)
                piecePositions[2*i+1][DIMENSION-1] = new Piece(false, this, 2*i+1, DIMENSION-1, "pawn");
        }
    }
    
    /* 
    #################
    Game flow methods
    #################
    */
    private int playerTurn = 0;
    private boolean moved = false;
    public static void main (String args[])    {
        StdDrawPlus.setXscale(0, DIMENSION);
        StdDrawPlus.setYscale(0, DIMENSION);
        
        Board gameBoard = new Board(false);
        while(true) {
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if(gameBoard.canSelect(x, y))
                    gameBoard.select(x, y);
            }
            if (StdDrawPlus.isSpacePressed()) {
                if(gameBoard.canEndTurn())
                    gameBoard.endTurn();
            }
            gameBoard.drawBoard();
            if(gameBoard.winner() != null)  {
                gameBoard.drawBoard();
                System.out.println(gameBoard.winner()+" wins!");
                return;
            }
            StdDrawPlus.show(100);
        }
    }
    
    private Piece selectedPiece = null;
    private int selectedX, selectedY;
    public boolean canSelect(int x, int y) {
        Piece target = pieceAt(x, y);
        if (x < 0 || y < 0 || x >= DIMENSION || y >= DIMENSION)
            return false;
        if (target == null) {
            if (selectedPiece != null && moved == false && validStep(selectedX, selectedY, x, y))
                return true;
            if (selectedPiece != null && (selectedPiece.hasCaptured() || moved == false) && validCapture(selectedX, selectedY, x, y))
                return true;
            return false;
        }
        else {
            if (moved == false && target.side() == playerTurn)
                return true;
            return false;
        }
    }
    public void select(int x, int y)    {
        Piece target = piecePositions[x][y];
        if (target == null) {
            moved = true;
            selectedPiece.move(x, y);
            selectedX = x;
            selectedY = y;
        }
        else    {
            selectedPiece = target;
            selectedX = x;
            selectedY = y;
        }
    }
    
    private boolean validMove(int xi, int yi, int xf, int yf)    {
        Piece piece = pieceAt(xi, yi);
        if (xf < 0 || yf < 0 || xf >= DIMENSION || yf >= DIMENSION || piece == null)
            return false;
        Piece target = pieceAt(xf, yf);
        if(target != null)
            return false;
        
        if (validStep(xi, yi, xf, yf) || validCapture(xi, yi, xf, yf))
            return true;
        else
            return false;
    }
    private boolean validStep(int xi, int yi, int xf, int yf)    {
        Piece piece = pieceAt(xi, yi);
        if (piece.isKing()) {
            if(Math.abs(xf-xi) == 1 && Math.abs(yf-yi) == 1)
                return true;
        }
        else    {
            if(Math.abs(xf-xi) == 1 && yf-yi == ((piece.side()^1) - piece.side()))
                return true;
        }
        return false;
    }
    private boolean validCapture(int xi, int yi, int xf, int yf)    {
        Piece piece = pieceAt(xi, yi);
        if (xf < 0 || yf < 0 || xf >= DIMENSION || yf >= DIMENSION || piece == null)
            return false;
        if (piece.isKing()) {
            if(Math.abs(xf-xi) == 2 && Math.abs(yf-yi) == 2)   {
                Piece jump = pieceAt(((xi+xf)/2), ((yi+yf)/2));
                if(jump != null && jump.side() != piece.side())
                    return true;
            }
        }
        else    {
            if(Math.abs(xf-xi) == 2 && yf-yi == 2*((piece.side()^1) - piece.side()))  {
                Piece jump = pieceAt(((xi+xf)/2), ((yi+yf)/2));  
                if(jump != null && jump.side() != piece.side())
                    return true;
            }
        }
        return false;
    }  
    
    public boolean canEndTurn()    {
        return moved;
    }
    public void endTurn()   {
        if (selectedPiece != null)
            selectedPiece.doneCapturing();
        selectedPiece = null;
        playerTurn = 1-playerTurn;
        moved = false;
    }
    
    public String winner()  {
        boolean fire = true;
        boolean water = true;
        Piece piece;
        for(int i = 0; i < DIMENSION; i++)  {
            for(int j = 0; j < DIMENSION; j++)  {
                piece = pieceAt(i, j);
                if (piece != null)   {
                    if (piece.side() == 0)
                        fire = false;
                    if (piece.side() == 1)
                        water = false;
                }
            }
        }
        if(fire && water)
            return "No one";
        else if (fire)
            return "Water";
        else if (water)
            return "Fire";
        else
            return null;
    }
    
    /* 
    ####################################
    Board and piece manipulation methods
    ####################################
    */
    public Piece pieceAt(int x, int y)  {
        if (x < 0 || y < 0 || x >= DIMENSION || y >= DIMENSION)
            return null;
        return piecePositions[x][y];
    }
    public void place(Piece p, int x, int y)    {
        if (x < 0 || y < 0 || x >= DIMENSION || y >= DIMENSION || p == null)
            return;
        remove(p);
        piecePositions[x][y] = p;
        // p.move(x, y);       
    }
    public Piece remove(int x, int y)   {
        if (x < 0 || y < 0 || x >= DIMENSION || y >= DIMENSION) {
            System.out.println("Attempted to remove an out-of-bounds position!");
            return null;
        }
        else if(piecePositions[x][y] == null)   {
            System.out.println("No piece to remove at <"+x+", "+y+">!");
            return null;
        }
        else    {
            Piece piece = piecePositions[x][y];
            piecePositions[x][y] = null;
            return piece;
        }
    }
    private Piece remove(Piece p)   {
        for(int i = 0; i < DIMENSION; i++)  {
            for(int j = 0; j < DIMENSION; j++)  {
                if (piecePositions[i][j] == p)
                    remove(i, j);
            }
        }
        return p;
    }
    private void capture(int xi, int yi, int xf, int yf)    {
        Piece jump = pieceAt(((xi+xf)/2), ((yi+yf)/2));
        remove(jump);
        if(selectedPiece.isBomb())  {
            explode(xf, yf);
            selectedPiece = null;
        }        
    }
    private void bombRemove(int x, int y)  {
        Piece piece = pieceAt(x, y);
        if (x < 0 || y < 0 || x >= DIMENSION || y >= DIMENSION || piece == null)
            return;
        else    {
            if (!(piece.isShield()))
                piecePositions[x][y] = null;
            return;
        }
    }
    private void explode(int x, int y)  {;
        bombRemove(x, y);
        bombRemove(x+1, y+1);
        bombRemove(x-1, y+1);
        bombRemove(x+1, y-1);
        bombRemove(x-1, y-1);
    }
    
    /*
    ###########################
    Board visualization methods
    ###########################
    */
    private void drawBoard() {
        for(int i = 0; i < DIMENSION; i++)  {
            for(int j = 0; j < DIMENSION; j++)  {
                drawSquare(i, j);
                if (piecePositions[i][j] != null)
                    drawPiece(piecePositions[i][j], i, j);
            }
        }
        if(selectedPiece != null)
            highlightSquare(selectedX, selectedY);
    }

    private static void drawSquare(int x, int y)    {
        if((x+y) % 2 == 0)  StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else                StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
    }
    private void highlightSquare(int x, int y)    {
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        if(piecePositions[x][y] != null)
            drawPiece(piecePositions[x][y], x, y);
    }
    private static void drawPiece(Piece P, int x, int y)  {
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.picture(x + .5, y + .5, imgPath(P), 1, 1);
    }
    private static final String IMAGEPATH = "img/";
    private static String imgPath(Piece P)    {
        String path = IMAGEPATH;
        if (P.isBomb())         path += "bomb";
        else if (P.isShield())  path += "shield";
        else                    path += "pawn";
        
        if (P.isFire())         path += "-fire";
        else                    path += "-water";
        
        if (P.isKing())         path += "-crowned";
        
        path += ".png";
        return path;
    }
    
}