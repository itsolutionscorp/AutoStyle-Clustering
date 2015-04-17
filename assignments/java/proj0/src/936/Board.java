//Sunny Lee
//0.96 version
public class Board{
                                            
    private Piece pieces[][] = new Piece[8][8];

    private boolean isFireTurn = true;
    private boolean finishTurn = false;
    private boolean hasSelectedPiece = false, hasMoved = false;
    private boolean hasCaptured = false;
    private int recentSelectPieceX, recentSelectPieceY;
    private int SIZE = 8;

/*========================================================================================================================================= /
/                                                           MAIN                                                                            /
/*=========================================================================================================================================*/
    //starts a GUI supported version of the game
    public static void main(String[] args){

        //System.out.println("game start");
        int SIZE = 8;
        StdDrawPlus.setXscale(0, SIZE);
        StdDrawPlus.setYscale(0, SIZE);
        //pieces = new boolean[SIZE][SIZE];
        //pieces[2][3] = true;

        Board test = new Board(false);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            drawBoard(SIZE,test);
            //mouse press
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if(test.canSelect((int)x, (int)y))
                    test.select((int)x,(int)y);
            }
            if(StdDrawPlus.isSpacePressed()){
                if(test.canEndTurn())
                    test.endTurn();
            }         
            StdDrawPlus.show(100);
        }

    }














    //draw board
    private static void drawBoard(int SIZE, Board b) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                b.drawPieces(i,j,b);
            }
        }
        //select -> white
        if(b.hasSelectedPiece){
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(b.recentSelectPieceX + .5, b.recentSelectPieceY + .5, .5);
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            b.drawPieces(b.recentSelectPieceX, b.recentSelectPieceY, b);
        }
    }   

    private void drawPieces(int i, int j, Board b){
        //draw pieces=========
        //fire bomb
        if (b.pieces[i][j] != null && b.pieces[i][j].isBomb() && b.pieces[i][j].isFire())
            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
        //fire shield
        else if (b.pieces[i][j] != null && b.pieces[i][j].isShield() && b.pieces[i][j].isFire())
            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
        //fire pawn
        else if (b.pieces[i][j] != null && b.pieces[i][j].isFire())
            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
        //water bomb
        else if (b.pieces[i][j] != null && b.pieces[i][j].isBomb())
            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
        //fire shield
        else if (b.pieces[i][j] != null && b.pieces[i][j].isShield())
            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
        //fire pawn
        else if (b.pieces[i][j] != null)
            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                
    }


    //
    // constructor
    // if souldBeEmpty is true, initializes an empty board. otherwise, initialize a default one
    public Board(boolean shouldBeEmpty){
        if(shouldBeEmpty)
            ConstructEmptyBoard();
        else
            ConstructBoard();

    }
    //get the piece at position (x,y) on the board, or null if there is no piece.
    //if (x,y) are out of bounds, returns null
    public Piece pieceAt(int x, int y){
        if(x >= SIZE || y >= SIZE || x < 0 || y < 0)
            return null;
        if(pieces[x][y] == null)
          return null;
        else
            return pieces[x][y];
    }
    
    //returns ture if there is the square (x,y) can be select
    public boolean canSelect(int x, int y){
        //make sure mouse click inside the boarder
        if(x<0 || y<0 || x>=SIZE || y>=SIZE)
            return false; 
        //if selected block is a piece
        if(pieces[x][y] != null && xand(pieces[x][y].isFire(),isFireTurn)){
            //if has not selected a piece yet
            if(!hasSelectedPiece)
                return true;
            //selected a piece but has not moved yet => changed his mind
            else if(hasSelectedPiece && !hasMoved && !hasCaptured){
                return true;
            }
            else
                return false;
        }
        //selected block is empty square
        else{
            if(hasSelectedPiece && !hasMoved &&!hasCaptured
                && validMove(recentSelectPieceX,recentSelectPieceY,x,y))
                return true;
            else if(hasSelectedPiece && hasCaptured
                && validMove(recentSelectPieceX,recentSelectPieceY,x,y)
                && Math.abs(recentSelectPieceX - x) == 2)
                return true;
            else
                return false;
        }
    }

    //selects the piece at(x.y) if possible
    public void select(int x, int y){
        //empty square
        if(pieces[x][y] == null){
            pieces[recentSelectPieceX][recentSelectPieceY].move(x,y);
            if(Math.abs(recentSelectPieceX - x) == 1)
                hasMoved = true;
            else if(Math.abs(recentSelectPieceX - x) == 2){
                hasCaptured = true;
                if(pieceAt(x,y) != null && pieceAt(x,y).isBomb())
                    pieces[x][y] = null;
            }
        }
        recentSelectPieceX = x;
        recentSelectPieceY = y;
        hasSelectedPiece = true;

    }

    //valid move
    //didnt not take account of if he has move/capture yet
    private boolean validMove(int xi,int yi,int xf,int yf){
        if(pieces[xi][yi]== null || pieces[xf][yf]!= null)
            return false;
        //KING
        if(pieces[xi][yi].isKing()){
            //move
            if(Math.abs(xi-xf) == 1 && Math.abs(yi-yf) == 1)
                return true;
            if(Math.abs(xi-xf) == 2 && Math.abs(yi-yf) == 2
                && pieces[(xi+xf)/2][(yi+yf)/2] != null
                && pieces[(xi+xf)/2][(yi+yf)/2].side() != pieces[xi][yi].side())
                return true;
            else
                return false;
        }
        //FIRE
        else if(isFireTurn && pieces[xi][yi].isFire()){
            //fire move
            if(Math.abs(xi-xf) == 1 && yf-yi==1)
                return true;
            //fire capture
            else if(Math.abs(xi-xf)==2 && yf-yi==2
                    && pieces[(xi+xf)/2][(yi+yf)/2] != null
                    && pieces[(xi+xf)/2][(yi+yf)/2].side() != pieces[xi][yi].side())
                return true;
            else 
                return false;
        }
        //WATER
        else if(!isFireTurn && pieces[xi][yi].isFire() == false){
            //water move
            if(Math.abs(xi-xf) == 1 && yf-yi == -1)
                return true;
            //water capture
            else if(Math.abs(xi-xf)==2 && yf-yi==-2
                    && pieces[(xi+xf)/2][(yi+yf)/2] != null
                    && pieces[(xi+xf)/2][(yi+yf)/2].side() != pieces[xi][yi].side())
                return true;
            else 
                return false;
        }
        else
            return false;

    }


    //places p at (x,y). If (x,y) is out of bounds or if p is null, does nothing.
    //if p already exits at (x,y), p will replace that piece
    public void place(Piece p, int x , int y){
        if(x >= SIZE || y >= SIZE || x < 0 || y < 0 || p == null)
            return;
        for(int j=0; j <SIZE; j+=1)
            for(int i=0; i<SIZE; i+=1)
                if(pieces[i][j] == p)
                    remove(i,j);

        //assuming p.x, p.y attributes in Piece class are correctly set already
        //from TA Jimma, piazza
        this.pieces[x][y] = p;

    }

    //exectures a remove. Return the piece that was removed.
    //if the input is out of bounds, return null and print error message
    //if there is no piece at (x,y), return null and print error message
    public Piece remove(int x , int y){
        if(x >= SIZE || y >= SIZE || x < 0 || y < 0){
            System.out.println("index out of bounds");
            return null;
        }
        if(pieces[x][y] == null){
            System.out.println("nothing at here to remove");
            return null;
        }
        Piece t = pieces[x][y];
        pieces[x][y] = null;
        return t;
    }
    //returns whether or not hte current player can end thier turn.
    //To be able to tend a turn, a piece must hava moved or perfomed a capture.
    public boolean canEndTurn(){
          //  System.out.println("ERROR, performed both move and capture");
        return hasMoved || hasCaptured;
    }

    //called at the end of each turn.
    //handles switching of players and anything else that should happen at the end of a turn
    public void endTurn(){
        if(isFireTurn)
            isFireTurn = false;
        else
            isFireTurn = true;
        if(pieceAt(recentSelectPieceX, recentSelectPieceY) !=null 
            && pieceAt(recentSelectPieceX, recentSelectPieceY).hasCaptured() )
                pieceAt(recentSelectPieceX, recentSelectPieceY).doneCapturing();
        hasMoved = false;
        hasCaptured = false;
        hasSelectedPiece = false;

    }

    /*
     * Returns the winner of the game. "Fire", "Water", "No one" (tie / no pieces on the board),
     * or null if the game is not yet over.
     * Assume there is no stalemate situation in which the current player has pieces but
     * cannot legally move any of them 
     * (In this event, just leave it at null). 
     * Determine the winner solely by the number of pieces belonging to each team.
     */
    public String winner(){
        if (countFire() == 0 && countWater() == 0)
            return "No one";
        if(countFire() == countWater())
            return null;
        else if(countFire() > countWater())
            return "Fire";
        else // (countFire() < countWater())
            return "Water";
    }

    private int countFire(){
        int ret = 0;
        for(int i=0; i<SIZE; i++)
            for(int j=0; j<SIZE; j++){
                if(pieces[i][j] != null && pieces[i][j].isFire())
                    ret += 1;
            }       
        return ret; 
    }

    private int countWater(){
        int ret = 0;
        for(int i=0; i<SIZE; i++)
            for(int j=0; j<SIZE; j++){
                if(pieces[i][j] != null && pieces[i][j].isFire() != true)
                    ret += 1;
            }       
        return ret; 
    }



    private void ConstructBoard(){

        for(int x = 0; x < SIZE; x++)
            for(int y = 0; y < SIZE; y++)
                this.pieces[x][y] = null;

        //row 0
        int y = 0;
        
        for(int x = 0; x < SIZE; x+=2)
            this.pieces[x][y] = new Piece(true, this, x, y, "pawn");
        //row 1
        
        y = 1;
        for(int x = 1; x < SIZE; x +=2)
            this.pieces[x][y] = new Piece(true, this, x, y, "shield");
        
        //row 2
        y = 2;
        for(int x = 0; x < SIZE; x +=2)
            this.pieces[x][y] = new Piece(true, this, x, y, "bomb");
        //row 5
        y = 5;
        for(int x = 1; x < SIZE; x +=2)
            this.pieces[x][y] = new Piece(false, this, x, y, "bomb");
        //row 6
        y = 6;
        for(int x = 0; x < SIZE; x +=2)
            this.pieces[x][y] = new Piece(false, this, x, y, "shield");       
        //row 7
        y = 7;
        for(int x = 1; x < SIZE; x +=2)
            this.pieces[x][y] = new Piece(false, this, x, y, "pawn");   

    }

    private void ConstructEmptyBoard(){
        for(int i=0; i<SIZE; i+=1)
            for(int j=0; j<SIZE; j+=1)
                this.pieces[i][j] = null;
    }

    //need to fix for valid move
    private boolean xand(boolean a, boolean b){
        if(a == false && b == false)
            return true;
        return a && b;
    }




}