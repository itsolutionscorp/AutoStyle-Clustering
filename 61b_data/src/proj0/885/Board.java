public class Board  {

    boolean shouldBeEmpty;

    private boolean turn = true; // true means fires turn
    private boolean hasMoved = false;
    private Piece pieceSelected = null;
    private static boolean toggle = true;

    int boardSize = 8;

    private Piece[][] pieces;
    private boolean[][] selected;


    /** Draws an N x N board. Adalted from:gg
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */



    public static void main(String[] args) 
    {
        int N = 8;
        Board newBoard = new Board(false);
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        boolean[][] selected = new boolean[N][N];
        // Board newBoard = new Board(false);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while( toggle == true) 
        {
            // newBoard.selectedInitializeFalse();// for some reason is breadking it
            newBoard.drawBoard(N);

            if (StdDrawPlus.isSpacePressed())
            {

                if (newBoard.canEndTurn()) 
                {

                    if (newBoard.winner() != null)
                    
                    {
                        newBoard.winner();
                        toggle = false;
                    }

                    else 
                    {
                        newBoard.endTurn();
                    }


                }
        
            }

        
            else if (StdDrawPlus.mousePressed()) 
            {
                double xmouse = StdDrawPlus.mouseX();
                double ymouse = StdDrawPlus.mouseY();
                int x = (int) xmouse;
                int y = (int) ymouse;


                // ask ta if this is a good Idea on Friday...
                if (newBoard.canSelect(x, y) )
                {
                    newBoard.select(x,y); 
                }
                /* if canSelect then selct the piece and set has Selected to true*/

            }            
            
            StdDrawPlus.show(100);
        }
    }

    public Board(boolean isEmpty){ //<-- asdf should this be void
        shouldBeEmpty = isEmpty;
        if (shouldBeEmpty)
        {
            pieces = new Piece[boardSize][boardSize];
            selected = new boolean[boardSize][boardSize];//asdf look at this again
        }

        else
        {
            pieces = new Piece[boardSize][boardSize];
            int yCounter = 0;
            while (yCounter < boardSize){
                int xCounter = 0;
                while (xCounter < boardSize)
                {
                    if (yCounter == 0 && xCounter % 2 == 0)
                    {
                        pieces[xCounter][yCounter] = new Piece(true, this, xCounter, yCounter, "pawn");
                        xCounter += 1;
                    }
                    
                    else if (yCounter == 1 && xCounter % 2 == 1)
                    {
                        pieces[xCounter][yCounter] = new Piece(true, this, xCounter, yCounter, "shield"); 
                        xCounter += 1;
                    }
                    
                    else if(yCounter == 2 && xCounter % 2 == 0)
                    {
                        pieces[xCounter][yCounter] = new Piece(true, this, xCounter, yCounter, "bomb"); 
                        xCounter += 1;
                    }
                    
                    else if (yCounter == (boardSize - 1) && xCounter % 2 == 1)
                    {
                        pieces[xCounter][yCounter] = new Piece(false, this, xCounter, yCounter, "pawn"); 
                        xCounter += 1;
                    }
                    else if (yCounter == (boardSize - 2) && xCounter % 2 == 0)
                    {
                        pieces[xCounter][yCounter] = new Piece(false, this, xCounter, yCounter, "shield"); 
                        xCounter += 1;
                    }
                    
                    else if(yCounter == (boardSize - 3) && xCounter % 2 == 1)
                    {
                        pieces[xCounter][yCounter] = new Piece(false, this, xCounter, yCounter, "bomb"); 
                        xCounter += 1;
                    }
                    else{
                        pieces[xCounter][yCounter] = null; 
                    }
                    yCounter += 1;
                }
            }
        }
    }

        


        private void drawBoard(int N)
        {
            for (int i = 0; i < N; i++) 
            {
                for (int j = 0; j < N; j++) 
                {
                    if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null)
                {
                    if (pieces[i][j].isFire())
                    {
                        if (pieces[i][j].isBomb())
                        {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        }
                        
                        else if (pieces[i][j].isShield())
                        {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        }
                        
                        else 
                        {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                    }
                    
                    else 
                    {
                        if (pieces[i][j].isBomb())
                        {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        }
                        
                        else if (pieces[i][j].isShield()) 
                        {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        }
                        else 
                        {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }
                    }
                }
            

            }        
        }
    }



    private boolean withinBounds(int x,int y)
        {
            if (x >= boardSize || y >= boardSize)
            {
                return false;
            }
            
            else
            {
                return true;
            }
        }


// 2 Public Methods that the spec suggests to use
//
//          WILL BE USED FOR TESTING
//                                              
//          
        public Piece pieceAt(int x, int y)
        {
            if (pieces[x][y] == null) 
            {
                return null;
            }

            else
            {
                return pieces[x][y];
            }
        }


        public void place(Piece p, int x, int y)
        { //improvement from original w/helpers
            if ( this.withinBounds(x,y) && this.pieces[x][y] == null) 
            { 
                pieces[x][y] = p;
            }
        }

    private boolean pieceCanGoUp (int x, int y)
        {
            if (pieces[x][y].isFire() || pieces[x][y].isKing())
            {
                return true;
            }
           
            else
            {
                return false;
            }
        }

    private boolean pieceCanGoDown (int x, int y)
        {
            if (pieces[x][y].isFire() || pieces[x][y].isKing()) 
            {
                return false;
            }
            
            else
            {
                return true;
            }
        }

    private boolean areDifferentType(int x1, int y1, int x2, int y2)
        {
            if( pieceAt(x1,y1).isFire() ==  pieceAt(x2,y2).isFire())
            {
                return false;
            }
            
            else
            {
                return true;
            }
        }


        
 public boolean canSelect(int x, int y)
        {
            if( !withinBounds(x,y))
            {
                return false;
            }

            else if (pieces[x][y] ==  null)
            {
                return canSelectEmpty(x,y);
            }
            
            else
            {
                return canSelectPiece(x,y);
            }
        }

            private boolean canSelectPiece(int x, int y)
            {
                int move_left     = x-1;
                int move_right    = x+1;
                int move_up       = y+1;
                int move_down     = y-1; 
                int capture_left  = x-2;
                int capture_right = x+2;
                int capture_up    = y+2;
                int capture_down  = y-2; 
                //********* for any  piece***********
                return validMove(x,y,move_left,move_up,false)           || validMove(x,y,move_right,move_up,false)        ||// normal move up...   no capture
                       validMove(x,y,move_left,move_down,false)         || validMove(x,y,move_right,move_down,false)      ||// normal move down... no capture
                       validMove(x,y,capture_left,capture_up,false)     || validMove(x,y,capture_right,capture_up,false)  ||// capture up
                       validMove(x,y,move_left,move_down,false)         || validMove(x,y,move_right,move_down,false);       // capture down
            }

            private boolean canSelectEmpty(int x, int y)
            {
                int left    = x-1;
                int right   = x+1;
                int up      = y+1;
                int down    = y-1; 
                int left_2  = x-2;
                int right_2 = x+2;
                int up_2    = y+2;
                int down_2  = y-2;
                // only sending "nice" numbers to validMove where a piece could in principle exist 
                if( validMove(left,   up,x,y,true)    || validMove(left ,down,    x,y,true)      || // piece can move left to this square
                    validMove(right,  up,x,y,true)    || validMove(right,down,    x,y,true)      || // piece can move right to this square
                    validMove(left_2, up_2,x,y,true)  || validMove(left_2,down_2, x,y,true)      || // piece can capture left to this square
                    validMove(right_2,up_2,x,y,true)  || validMove(right_2,down_2,x,y,true)      )

                { // piece can capture right to this square
                    return true;
                }

                else{
                    return false;
                }
            }



            
            private boolean validMove(int xi, int yi, int xf, int yf, boolean needToCheckIfMoverHasBeenSelected) 
            { // this last booolean is especially made for
                int diff = yf - yi;

                if (     yf < 0 || yi < 0 || xf < 0 || xi < 0||xi >7 || xf > 7 || yi > 7 || yf > 7      || !withinBounds(xf,yf)                || 
                    pieces[xi][yi] == null                      || pieces[xf][yf] != null               ||
                    ( !pieces[xi][yi].isFire() && turn )        || (pieces[xi][yi].isFire() && (!turn))  )
                    // pieceSelected != null ) need to redo cases anyhows did some thinds with pi
                { // last one should never be used anyhow
                    return false;
                }

                // else if(needToCheckIfMoverHasBeenSelected && selectedPiece != pieces[xi][]) asdf
                // {
                //     return false;
                // }

                else if ( diff == 1 && pieceCanGoUp(xi,yi)   && !pieces[xi][yi].hasCaptured()  ) 
                { //already checked if piece exists at destination
                    return true;
                }
                
                else if (diff == -1 && pieceCanGoDown(xi,yi) && !pieces[xi][yi].hasCaptured()  ) 
                { //remember to also check if piece hasCaptured
                    return true;
                }
                
                else if (diff == 2 && pieceCanGoUp(xi,yi) ) 
                { //
                        if (pieces[(xf + xi)/2][(yf + yi)/2] != null) 
                        {
                        
                            if  ( pieces[xi][yi].isFire()  && (pieces[(xf + xi)/2][(yf + yi)/2].isFire()  ) || 
                                 ( !pieces[xi][yi].isFire() && (!pieces[(xf + xi)/2][(yf + yi)/2].isFire() )  ) )    
                            {  // annoingly we are unable to use midpoint label here due to it being a double
                                return true;
                            }
                            
                            else 
                            {
                                return false;
                            }
                        }
                }
                
                else if (diff == -2 && pieceCanGoDown(xi,yi))  
                { // annoingly we are unable to use midpoint label here due to it being a double
                            if (pieces [(xf + xi)/2][(yf + yi)/2] != null )
                            {
                        
                                if ( pieces[xi][yi].isFire()   && !pieces[(xf + xi)/2][(yf + yi)/2].isFire()   || 
                                   ( !pieces[xi][yi].isFire()  && pieces[(xf + xi)/2][(yf + yi)/2].isFire())    )  
                                {  // annoingly we are unable to use midpoint label here due to it being a double
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
                return false;
                
            }
        

    public void select(int x, int y) 
    {
            
        if (pieces[x][y] == null) 
        { 

        // IT SHOULD AUTOMATICALLY BE THE CASE THAT NOTHING IS sELECTED HERE!!

            if (pieceSelected == null || (!pieceSelected.isFire() && turn) || (pieceSelected.isFire() && !turn)) 
            {
                System.out.println("Selection Error");
            }

            else  
            {
                pieceSelected.move(x,y);
                selected = new boolean [boardSize][boardSize];
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE); // asdf order may matter more here
                selected[x][y] = true;
            }
        }

        else
        {
            pieceSelected = pieces[x][y];

        }
    }
    

    public Piece remove(int x, int y)
    {
        if (!withinBounds(x,y))
        {
            System.out.println("OutOfBoundsError: can't remove");
            return null;
        }

        else if (pieces[x][y] == null)
        {
            System.out.println("No piece located here");
            return null;
        }

        else 
        {
       
            selected[x][y] = false;
            Piece result = pieces[x][y]; // asdf pointing at the same thing seems 
                                        // removed pop??
            pieces[x][y] = null;
            return result;
        }

    }

    public boolean canEndTurn()
    {
        if (hasMoved) 
        { //asdf double check that this also works for case when player has captured a piece
            return true;
        }
        
        else
        {
            return false;
        }

    }

    public void endTurn()
    {
        selected = new boolean[boardSize][boardSize]; //asdf maybe problem with null
        if(turn == true)
        {
            turn = false;
        }

        else
        {
            turn = true;
        }
    }
   
    public String winner()
    { // asdf will only work under specific circcumstances 
            int fireCounter  = 0;
            int waterCounter = 0;
            int yCounter = 0;
            while (yCounter < boardSize)
            {
                 int xCounter = 0;
                while (xCounter < boardSize)
                {
                        if (pieces[xCounter][yCounter].isFire())
                        {
                            fireCounter += 1;
                            xCounter += 1;
                        }
                        
                        else if(pieces[xCounter][yCounter] != null)
                        {
                            waterCounter += 1;
                            xCounter += 1;
                        }
                        else 
                        {
                            xCounter += 1;
                        }
                    
                }
                yCounter += 1;
            }

        return winnerHelper(fireCounter,waterCounter);
    }    

    private String winnerHelper( int fireCounter, int waterCounter)
    {
            
            if (fireCounter == 0 && waterCounter != 0)
            
            {
                return "water";
            }

            else if (fireCounter != 0 && waterCounter == 0)
            
            {
                return "fire";
            }

            else if (fireCounter == 0 && waterCounter == 0)
            
            {
                return "No one";
            }

            else
            {
                return null;
            }

    }


} // end of file


































































        
            



        

