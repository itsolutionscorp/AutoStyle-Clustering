public class Board //Discussed drawBoard with David Fang. We tried being unique with our drawBoards but it was difficult creating variations of it.
{
    private static boolean[][] pieces;
    private static Piece[][] pieceObject;
    private int x;
    private int y;
//     private int currentX;
//     private int currentY;
    private int initialX;
    private int initialY;
    private Piece pieceSelected;
    private boolean hasSelected;
    private boolean hasMoved;
    
   public Board(boolean shouldBeEmpty)
   {
        if (shouldBeEmpty == true)
        {
            this.pieceObject = new Piece[8][8];
        }
        
        else if(shouldBeEmpty == false)
        {          
            Piece[][] pieceObject = new Piece[8][8];
            for (int i = 0; i < 8; i++)
            {
                for (int j = 0; j < 8; j++)
                {
                    if (j == 0)
                    {
                        if (i % 2 == 0)
                        {
                            pieceObject[i][j] = new Piece(true, this, j, i, "pawn");
                        }
                    }
                
                    else if (j == 1)
                    {
                        if (i % 2 == 1)
                        {
                            pieceObject[i][j] = new Piece(true, this, j, i, "shield");
                        }
                    }
                    
                    else if (j == 2)
                    {
                        if (i % 2 == 0)
                        {
                            pieceObject[i][j] = new Piece(true, this, j, i, "bomb");
                        }
                    }
                    
                    else if (j == 5)
                    {
                        if (i % 2 == 0)
                        {
                            pieceObject[i][j] = new Piece(false, this, j, i, "pawn");
                        }
                    }
                    
                    else if (j == 6)
                    {
                        if (i % 2 == 1)
                        {
                            pieceObject[i][j] = new Piece(false, this, j, i, "shield");
                        }
                    }
                    
                    else if (j == 7)
                    {
                        if (i % 2 == 0)
                        {
                            pieceObject[i][j] = new Piece(false, this, j, i, "bomb");
                        }
                    }
                }
            }
            
        }
   }
        
   public static void main(String[] args)
   {
      int N = 8;
      StdDrawPlus.setXscale(0, N);
      StdDrawPlus.setYscale(0, N);
      pieces = new boolean[N][N];
      Board.drawBoard(8);
      Board b = new Board(false);
   
      
      /** Monitors for mouse presses. Wherever the mouse is pressed,
       a new piece appears. */
      while (true)
      {
           for (int i = 0; i < 8; i++) 
           {
               for (int j = 0; j < 8; j++) 
               {
                    if (j == 0)
                    {
                        if (i % 2 == 0)
                        {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);                          
                        }
                    }
                    
                    else if (j == 1)
                    {
                        if (i % 2 == 1)
                        {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        }
                    }
                    
                    else if (j == 2)
                    {
                        if (i % 2 == 0)
                        {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        }
                    }
                    
                    else if (j == 5)
                    {
                        if (i % 2 == 0)
                        {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }
                    }
                    
                    else if (j == 6)
                    {
                        if (i % 2 == 1)
                        {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        }
                    }
                    
                    else if (j == 7)
                    {
                        if (i % 2 == 0)
                        {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        }
                    }
                    
                    if (pieces[i][j]) 
                    {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                    }
               }           
           if (StdDrawPlus.mousePressed()) 
           {
               double x = StdDrawPlus.mouseX();
               double y = StdDrawPlus.mouseY();
               pieces[(int) x][(int) y] = true;
               if (b.canSelect((int) x, (int) y) == true)
               {
                   StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                   StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                   b.select((int) x,(int) y);
               }            
           StdDrawPlus.show(100);
           }
           }
      }
   }
      
    private static void drawBoard(int N) 
    {
        for (int i = 0; i < N; i++) 
        {
            for (int j = 0; j < N; j++) 
            {
                if ((i + j) % 2 == 0) 
                {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
               
                else
                {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                
                if (pieces[i][j]) 
                {
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                }
            }
        }
    }
        
    public Piece pieceAt(int x, int y)
    {
        if (x > 7 || x < 0 || y > 7 || y < 0)
        {
            return null;
        }
        
        else if (this.pieceObject[x][y] != null)
        {
            return this.pieceObject[x][y];
        }
        
        else
        {
            return null;
        } 
    }
   
    public boolean canSelect(int x, int y)
    {
        initialX = selectX(pieceSelected);
        initialY = selectY(pieceSelected);
        if (this.pieceObject[x][y] != null && this.validMove(initialX, initialY, x, y))
        {
            if (hasSelected == false && this.pieceObject[x][y].isFire() && hasMoved == false)
            {
                hasSelected = true;
                this.select(x, y);
                return true;
            }
            
            else if (hasSelected == true && this.pieceObject[x][y].isFire() && hasMoved == false)
            {
                //this.pieceObject[x][y].move(x, y);
                return true;
            }
            
            else
            {
                return false;
            }
        }
        
        else
        {
            if (hasSelected == true && hasMoved == false && validMove(initialX, initialY, x, y) == true)
            {
                return true;
            }
            
            //else if (hasSelected == true && this.pieceObject[x][y].hasCaptured() && this.pieceObject[x][y].validMove(x, y))
            else if (hasSelected == true && validMove(initialX, initialY, x, y) == true)
            {
                return true;
            }
            
            else
            {
                return false;
            }
        }
    }
        //Piece can be selected if the corresponding player's turn and either player has not
        //selected piece or has selected piece but not moved it
        
    public void select(int x, int y) //get help on this method
    {
        //Selects the piece at (x,y);        
        
        if (hasSelected == true && this.pieceObject[x][y] == null)
        {
            //this.pieceObject[initialX][initialY].move(x, y);
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
           // this.pieceObject[selectX(pieceSelected)][selectY(pieceSelected)].move(x, y);
        }

        else
        {
            pieceSelected = this.pieceObject[x][y];
//             currentX = x;
//             currentY = y;
            hasSelected = true;
        }
    }
    
    private int selectX(Piece p)
    {
        int iValue = 0;
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (pieceObject[i][j] == p)
                {
                    iValue = i;
                }
            }
        }
        return iValue;
    }
    
    private int selectY(Piece p)
    {
        int jValue = 0;
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (pieceObject[i][j] == p)
                {
                    jValue = j;
                }
            }
        }
        return jValue;
    }
    
    private boolean validMove(int xi, int yi, int xf, int yf)
    {
        if (this.pieceObject[xi][yi] != null && this.pieceObject[xf][yf] == null)
        {
            return true;
        }
        
        if (this.pieceObject[xf][yf] != null && this.pieceObject[x + 2][y + 2] == null)
        {
            return true;
        }
        
        if (this.pieceObject[x - 1][y + 1] != null && this.pieceObject[x -1][y + 1].isFire() == false)
        {
            return true;
        }
        
        else
        {
            return false;
        }
    }
     
    public void place(Piece p, int x, int y)
    {
        
        if ((x <= 7 && x >= 0 && y <= 7 && y >= 0) && (p != null))
        {
            this.pieceObject[x][y] = p;
        }
    }
        
    public Piece remove(int x, int y)
    {
        if (x > 7 || x < 0 || y > 7 || x < 0)
        {
            System.out.println("Input is out of bounds");
            return null;
        }
        
        if (this.pieceAt(x, y) == null)
        {
            System.out.println("There is no piece here.");
            return null;
        }
        
        else
        {
            this.pieceObject[x][y] = null;
            return this.pieceObject[x][y];
        }
    }

    public boolean canEndTurn()
    {
//         if ((this.pieceObject.hasMoved() == true) || (this.pieceObject.hasCaptured() == true))
//         {
//             return;
//         Returns whether or not the current player can end their turn. Can only end if piece has
//         moved or performed a capture.
//         }
        return true;
    } 

    public boolean endTurn()
    {
        //Called at the end of each turn.
        return false;
    }
    
    public String winner()
    {
        return null;
        //Returns the winner of the game.
    }
}
   
