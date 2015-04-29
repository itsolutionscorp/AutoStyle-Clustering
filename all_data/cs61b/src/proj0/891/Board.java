/** 
 *  @author Bo Zeng
 */

public class Board {
    /** Location of pieces. */
    private Piece[][] pieceArray;
    private static int N = 8;
    private Piece selectedPiece;
    private boolean sideIsFire;
    private boolean selectedPieceHasMoved;
    private int selectedX;
    private int selectedY;
    private boolean bombLanding;

    /** think of where to put those variables, to which is should belong **/


    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    /* generate a board instance */
    public Board(boolean shouldBeEmpty) {
        selectedPiece=null;
        selectedPieceHasMoved=false;
        sideIsFire=true;
        selectedX=-1;
        selectedY=-1;
        bombLanding=false;



        this.pieceArray = new Piece[N][N];

        for (int x=0;x<N;x++) {
            for (int y=0;y<N;y++) {
                this.pieceArray[x][y]= null;
            }
        }

        if (shouldBeEmpty) {
            ;
        }
        else {

            for (int x=0; x<N; x++) {
                for (int y=0; y<N; y++) {
                    if (y==0 && !isOdd(x)) {
                        pieceArray[x][y]=new Piece(true,this,x,y,"pawn");
                    }
                    else if (y==2 && !isOdd(x)) {
                        pieceArray[x][y]=new Piece(true,this,x,y,"bomb");
                    }
                    else if (y==1 && isOdd(x)) {
                        pieceArray[x][y]=new Piece(true,this,x,y,"shield");
                    }
                    else if (y==7 && isOdd(x)) {
                        pieceArray[x][y]=new Piece(false,this,x,y,"pawn");
                    }
                    else if (y==5 && isOdd(x)) {
                        pieceArray[x][y]=new Piece(false,this,x,y,"bomb");
                    }
                    else if (y==6 && !isOdd(x)) {
                        pieceArray[x][y]=new Piece(false,this,x,y,"shield");
                    }
                }
            }
        }
        
    }

    private boolean isOdd(int x) {
        if (x<0) {
            return false;
        }
        else if (x%2==0) {
            return false;
        }
        else {
            return true;
        }
    }

    public Piece pieceAt(int x, int y) {
        if (outOfBounds(x,y)){
            return null;
        }
    
        return pieceArray[x][y];
    }

    public boolean canSelect(int x , int y) {
        Piece p=pieceAt(x, y);
        
        if (selectedPiece==null && p!=null) {  /* case one: select a new piece */
            if (sideIsFire==p.isFire()) {
                return true;
            }
        }

        if (selectedPiece==null && p==null) {  
            return false;
        } 

        if (selectedPiece!=null &&p!=null) {  /* case one: toggle selection in your side */
            if (sideIsFire==p.isFire() &&!selectedPieceHasMoved) {
                return true;
            }
        }

        if (p==null && selectedPiece!=null) { /* case two: move to a neighbour blank square and case three: to make a valid jump*/

            if (outOfBounds(x,y)) {
                return false;
            }

            int sign1 = selectedPiece.isFire()? 1:-1;
            int sign2 = sign1;

            if (selectedPiece.isKing()) {
                sign2=-sign1;
            }
            
            
            if ((x==selectedX+1 || x==selectedX-1) && (y==selectedY+1*sign1 || y==selectedY+1*sign2) && !selectedPieceHasMoved) {
                return true;
            }

            if ((x==selectedX+2 || x==selectedX-2) && (y==selectedY+2*sign1 || (y==selectedY+2*sign2))&& (!selectedPieceHasMoved||selectedPiece.hasCaptured())) {
                Piece middle=pieceAt((selectedX+x)/2, (selectedY+y)/2);
                if (middle!=null) {
                    if (middle.isFire()!=selectedPiece.isFire()) {
                                if(selectedPiece.isBomb()) {
                                    bombLanding=true;
                                }
                                return true;
                    }
                }
                    
            }
        }
        return false;
    }

    private void addPiece(int x, int y, Piece p) {
        if (!outOfBounds(x,y)) {
            pieceArray[x][y]=p;
        }
    }

    private void drawWhitePiece() {
        if (selectedPiece==null) {
            return;
        }
        if (selectedPiece.hasCaptured()) {
            StdDrawPlus.setPenColor(StdDrawPlus.YELLOW);
        }
        else if (selectedPieceHasMoved && bombLanding ) {
            StdDrawPlus.setPenColor(StdDrawPlus.MAGENTA);
        }
        else {
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        }

        StdDrawPlus.filledSquare(selectedX + .5, selectedY + .5, .5);
    }

    public void select(int x, int y) {   /* draw operation is realized inside the class */

            Piece p = pieceAt(x,y);

            if (p!=null) {  // implement the rule when p is not null
                    selectedPiece=p;
                    selectedX=x;
                    selectedY=y;
            }


            else {
                if (selectedPiece!=null) {
                    remove(selectedX,selectedY);
                    addPiece(x,y,selectedPiece);
                    selectedPiece.move(x,y);  // do the capture and everything inside.
                    selectedX=x;
                    selectedY=y;
                    
                    selectedPieceHasMoved=true;
                }
            }


    }

    private boolean outOfBounds(int x, int y) {
        return (x<0 || x>=N || y<0 || y>=N);
    }


    public void place(Piece p, int x, int y) {  // this method is totally useless but to please TA and ag. we did it 
        if (p==null) {
            return;
        }
        if (outOfBounds(x,y)) {
            return;
        } 
        
        // p.move(x,y); /* refill the array when doing the move */

        pieceArray[x][y]=p;
        
        /* do something more to update its position */
    }


    public Piece remove(int x, int y) {
        if (outOfBounds(x,y)) {
            return null;
        }
        Piece p=pieceAt(x,y);
        
        pieceArray[x][y]=null;
        
        return p;
    
    }

    public boolean canEndTurn() {
        if (selectedPiece==null) {
            return false;
        }
        if (selectedPieceHasMoved) {
            return true;
        }
        return false;
    }

    public void endTurn() {
        if (selectedPiece.hasCaptured()) {
            selectedPiece.doneCapturing();
        }
        selectedPiece=null;
        selectedPieceHasMoved=false;
        sideIsFire=!sideIsFire;
        bombLanding=false;

    }

    public String winner() {
        int countFire=0;
        int countWater=0;
        /* count the number of pieces belonging to each team */
        for(int x=0;x<N;x++) {
            for (int y=0;y<N;y++) {
                if (pieceArray[x][y]!=null) {
                    if(pieceArray[x][y].isFire()) {
                        countFire++;
                    }
                    else {
                        countWater++;
                    }
                }
            }
        }

        if (countFire==0 && countWater!=0) {
            return "Water";
        }
        else if (countFire!=0 && countWater==0) {
            return "Fire";
        }
        else if (countFire==0 && countWater==0) {
            return "No one";
        }
        else {
            return null;
        }
    }

    private void drawBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else  {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }

    private void drawPieces() {
        for (int x=0;x<N;x++) {
            for (int y=0;y<N;y++) {
                if (pieceArray[x][y]!=null) {
                    Piece p=pieceArray[x][y];
                    if (p.isKing()) {
                        if(p.isShield()) {
                            StdDrawPlus.picture(x+ .5, y + .5, p.isFire()? "img/shield-fire-crowned.png":"img/shield-water-crowned.png", 1, 1);
                        }
                        else if(p.isBomb()) {
                            StdDrawPlus.picture(x + .5, y + .5, p.isFire()? "img/bomb-fire-crowned.png":"img/bomb-water-crowned.png", 1, 1);
                        }
                        else {
                            StdDrawPlus.picture(x + .5, y + .5, p.isFire()? "img/pawn-fire-crowned.png":"img/pawn-water-crowned.png", 1, 1);
                        }
                    }
                    else {
                        if(p.isShield()) {
                            StdDrawPlus.picture(x + .5, y + .5, p.isFire()? "img/shield-fire.png":"img/shield-water.png", 1, 1);
                        }
                        else if(p.isBomb()) {
                            StdDrawPlus.picture(x + .5, y + .5, p.isFire()? "img/bomb-fire.png":"img/bomb-water.png", 1, 1);
                        }
                        else {
                            StdDrawPlus.picture(x + .5, y + .5, p.isFire()? "img/pawn-fire.png":"img/pawn-water.png", 1, 1);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        int N = 8;

        String winnerName=null;
   
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);


        Board b = new Board(false); /* initialize a new board */
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */

        b.drawBoard();
        b.drawPieces();
        
        while(true) {
            
            if (StdDrawPlus.mousePressed()) { /* can replace so that it only respond if it is pressed not released */
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();

                if (b.canSelect((int)x,(int)y)) {
                    b.select((int)x,(int)y);  /* perform the selection, many different cases have been implemented */
                    b.drawBoard();
                    b.drawWhitePiece();
                    b.drawPieces();
                }
                
            }

            if (StdDrawPlus.isSpacePressed()) {
                winnerName=b.winner();

                if (winnerName!=null) {
                        System.out.println(winnerName);
                        /* then start a new board */
                        b = new Board(false);
                        b.drawBoard();
                        b.drawPieces();
                    }
                    
                if(b.canEndTurn()) {
                    b.endTurn();
                    b.drawBoard();
                    b.drawWhitePiece();
                    b.drawPieces();
                }
            }

            if (StdDrawPlus.isNPressed()) {
                b = new Board(false);
                b.drawBoard();
                b.drawPieces();
            }

            StdDrawPlus.show(20);
        }
    }
}
