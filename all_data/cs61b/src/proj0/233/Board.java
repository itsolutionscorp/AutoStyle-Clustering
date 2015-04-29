public class Board {
    
    private Piece[][] grid = new Piece[8][8];
    private boolean turn;
    private boolean firstSelection;
    private int sourceX, sourceY;
    private int destinationX, destinationY;
    private boolean isSelected, isPlaced;
    
    public static void main (String args[]) {
        Board boardTest = new Board(false);
        
        while (true) {
            
            double x=0.0,y=0.0;
            if ( StdDrawPlus.mousePressed() )
            {
                x = StdDrawPlus.mouseX();
                y = StdDrawPlus.mouseY();
               // System.out.println("mouse press gets: " + x + ", " + y);
            }
            
            if (boardTest.canSelect( (int) x, (int) y ) )
            {
                            boardTest.select( (int) x, (int) y);
                System.out.println("can select: " + (int) x + ", " + (int) y);
            }
            
        }
    }
    
    
    
    public Board(boolean shouldBeEmpty) {
            int N = 8;
            StdDrawPlus.setXscale(0, N);
            StdDrawPlus.setYscale(0, N);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
            }
            
            turn = true;
            firstSelection = true;
            isSelected = false;
            isPlaced = false;
            
            if (shouldBeEmpty == false)
            {
            
                // FIRE SIDE (rows 0 - 2)
                
                // row 0 : normal piece
                for (int j=0; j<8; j+=2)
                {
                    StdDrawPlus.picture(j + .5, 0.5, "img/pawn-fire.png", 1, 1);
                    grid[j][0] = new Piece(true, this, 0, j, "Normal");
                }
                
                // row 1 : shield
                for (int j=1; j<8; j+=2)
                {
                    StdDrawPlus.picture(j + .5, 1.5, "img/shield-fire.png", 1, 1);            
                    grid[j][1] = new Piece(true, this, 1, j, "Shield");   
                }
                
                // row 2 : bomb
                for (int j=0; j<8; j+=2)
                {
                    StdDrawPlus.picture(j + .5, 2.5, "img/bomb-fire.png", 1, 1);
                    grid[j][2] = new Piece(true, this, 2, j, "Bomb");   
                }
                    
                    
                // WATER SIDE (rows 5  - 7)
                
                // row 5 : bomb
                for (int j=1; j<8; j+=2)   
                {
                    StdDrawPlus.picture(j + .5, 5.5, "img/bomb-water.png", 1, 1);
                    grid[j][5] = new Piece(false, this, 5, j, "Bomb");
                }
                    
                // row 6 : shield
                for (int j=0; j<8; j+=2)
                {
                    StdDrawPlus.picture(j + .5, 6.5, "img/shield-water.png", 1, 1);  
                    grid[j][6] = new Piece(false, this, 6, j, "Shield"); 
                }
                    
                // row 7 : normal piece
                for (int j=1; j<8; j+=2)
                {
                    StdDrawPlus.picture(j + .5, 7.5, "img/pawn-water.png", 1, 1);            
                    grid[j][7] = new Piece(false, this, 7, j, "Normal");
                }
            }
            
    }
        
        public Piece pieceAt(int x, int y) {
            return grid[x][y];
        }
        
        public boolean canSelect(int x, int y) {
            
            if (grid[x][y] == null)
            {
                if (firstSelection == false)
                { // empty piece
            
                    // destination
                    destinationX = x;
                    destinationY = y;
                        
                    return true;
                }
                else
                    return false;
            }
            
            if (grid[x][y].isFire() == turn && firstSelection == true) {
                firstSelection = false;
                 // source
                sourceX = x;
                sourceY = y;
                return true;
            }
            else
                return false;
        }
        
        public void select(int x, int y) {
            
            if ( canSelect(x,y) ) {
                
                grid[sourceX][sourceY].move(x, y);
                isSelected = true;
            }
        }
        
        public void place(Piece p, int x, int y) {
            if ( p == null || x < 0 || x > 7 || y<0 || y>7 )
            {
                // do nothing
            }
            else
            {
                grid[x][y] = p;
                isPlaced = true;
            }
        }
        
        public Piece remove(int x, int y) {
            // check for out of bounds
           if ( x < 0 || x > 7 || y < 0 || y > 7)
           {
            System.out.println("Out of Bounds.");   
            return null;
           }
           if (pieceAt(x, y) == null ) // no piece case
           {
             System.out.println("No Piece At This Postion.");
             return null;
           }

        // valid case wherein we can remove the piece
           Piece removedPiece = grid[x][y];
           grid[x][y] = null;
           
           return removedPiece;
        }
        
        public boolean canEndTurn(int x, int y) {
            if ( isSelected == true && isPlaced == true)
            {
                isSelected = isPlaced = false;
                return true;
            }
            else
                return false;
        }
        
        
        
        public void endTurn() {
            
            turn = !turn;
        }
        
        
        public String winner() {
            int firePieces = 0, waterPieces = 0;
            String winnerIs = "";
            for (int i=0; i<8; i++) {
                
                for (int j=0; j<8; j++) {
                    
                    if (grid[i][j]!=null) {
                        
                        if (grid[i][j].isFire() == true)
                            firePieces++;
                        else // not true (i.e. water piece)
                            waterPieces++;
                            
                    }   
                }
            }
            
            if (firePieces > 0 && waterPieces == 0)
                winnerIs = "FIRE";
            else if (firePieces == 0 && waterPieces > 0)
                winnerIs = "WATER";
            else if (firePieces > 0 && waterPieces > 0)
                winnerIs = "null";
            else if (firePieces == 0 && waterPieces == 0)
                winnerIs = "no one";
            else
                winnerIs = "DRAW";
            
            
            return winnerIs;
        }
        
}
        
        
        
        
        
        