public class Board {
    // private boolean[][] location; // true or false if square contains a piece
    // private int[][] coordinates; // x y values
    private static Piece[][] pieces; // piece object in given location of matrix
    private boolean fireTurn; // fire or water
    private boolean moveMade;
    private boolean selected;
    private int selectedX;
    private int selectedY;
    private int firePiecesLeft;
    private int waterPiecesLeft;
    private boolean shouldBeEmpty;

    

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        // b.testboard();

        while(b.winner()==null) {
            drawBoard(N);
            // select and canselect
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int a = (int) x;
                int c = (int) y;
                if (b.canSelect(a,c)) {
                    StdDrawPlus.filledSquare(a + .5, c + .5, .5);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    b.select(a,c);
                }
            }
            
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }     

            StdDrawPlus.show(100);
        }
      }
  
    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[8][8];
        this.shouldBeEmpty = shouldBeEmpty;
        if (!shouldBeEmpty) {
            // initialize players turn 
            this.fireTurn = true;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 0) {
                        if (j == 0) 
                            pieces[i][j]= new Piece(true, this, i, j, "pawn");
                        else if (j == 1)
                            pieces[i][j]= new Piece(true, this, i, j, "shield");
                        else if (j == 2)
                            pieces[i][j]= new Piece(true, this, i, j, "bomb");
                        else if (j == 5)
                            pieces[i][j]= new Piece(false, this, i, j, "bomb");
                        else if (j == 6)
                            pieces[i][j]= new Piece(false, this, i, j, "shield");
                        else if (j==7)
                            pieces[i][j]= new Piece(false, this, i, j, "pawn");
                        }
                    }
                }
            this.firePiecesLeft = 12; //initializing default number of pieces for each side
            this.waterPiecesLeft = 12;
            }

        else {
            System.out.println("empty board initialized for testing");
            this.fireTurn = true; // for null boards
            this.firePiecesLeft = 0;
            this.waterPiecesLeft = 0; 
            }
        }

  
    private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                Piece piece = pieces[i][j];
                if (piece!=null) {
                    if (piece.isFire()) {
                        if (piece.isBomb()) {
                            if (piece.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        }

                        else if (piece.isShield()) {
                            if (piece.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            else StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        }
                        else {
                            if (piece.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                    }

                    if (piece.side() == 1) {
                        if (piece.isBomb()) {
                            if (piece.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        }

                        else if (piece.isShield()) {
                            if (piece.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            else StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        }
                        else {
                            if (piece.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }
                    }
                }
            }
        }
    }

        


    // required methods
    public Piece pieceAt(int x, int y) {
        if ((x > 8) || (y > 8) || (x < 0) || (y < 0)) {
            return null;
        }
        return pieces[x][y];
    }

    public void place(Piece p, int x, int y) {
        if ((p != null) && (x < 8) && (y < 8)) { 
            // If p already exists in the current Board, first removes it from its initial position
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (pieces[i][j] == p){
                        pieces[i][j] = null;
                    }
                }
            }
            pieces[x][y] = p;

            if (shouldBeEmpty) {
                System.out.println("new piece placed on board, number of pieces incremented by 1");
                if (p.isFire()) firePiecesLeft+=1;
                if (p.side() == 1) waterPiecesLeft+=1;
            }
        }
    }

    //change back to private
    private boolean validMove(int xi, int yi, int xf, int yf) {
        /* Returns true if the piece at (xi, yi) can either move to (xf, yf) or capture to (xf, yf), 
        strictly from a geometry/piece-race point of view. validMove does not need to to
        ake into consideration whose turn it is or if a move has already been made, 
        but rather can
        */
        Piece origin = pieceAt(xi, yi);
        Piece next = pieceAt(xf, yf);
        int distance = (xf-xi)+(yf-yi);
        int directionX= Math.abs(xf-xi);
        int directionY=(yf-yi);

        if (origin == null) return false;
        
        else if (origin.isKing()) {
            if (distance%2 == 0) {
                if ((directionX==1) && (Math.abs(directionY) == 1)) {
                    if (next == null) return true;
                }
                else if ((directionX==2) && (Math.abs(directionY)==2) && (next==null)) {
                    if ((pieceAt((xi+xf)/2,(yi+yf)/2))!=null) {
                        if ((pieceAt((xi+xf)/2,(yi+yf)/2)).side() != pieceAt(xi, yi).side()) {
                            System.out.println("capturable");
                            return true;
                        }
                        return false;
                    }
                    return false;
                }
            }
        }
    
        else if (origin.isFire()){
            if ((distance%2 == 0) && (directionY > 0)) {
                if ((directionX==1) && (directionY == 1)) {
                    if (next == null) return true;
                }
                else if ((directionX==2) && (directionY==2) && (next==null)) {
                    if ((pieceAt((xi+xf)/2,(yi+yf)/2))!=null) {
                        if ((pieceAt((xi+xf)/2,(yi+yf)/2)).side() != pieceAt(xi, yi).side()) {
                            System.out.println("capturable");
                            return true;
                        }
                    }
                }
            else return false;
            }
        }

        else if (origin.side() == 1){
            if ((distance%2 == 0) && (directionY < 0)) {
                if ((directionX==1) && (directionY == -1)) {
                    if (next == null) return true;
                }
                else if ((directionX==2) && (directionY==-2) && (next==null)) {
                    if ((pieceAt((xi+xf)/2,(yi+yf)/2))!=null) {
                        if ((pieceAt((xi+xf)/2,(yi+yf)/2)).side() != pieceAt(xi, yi).side()) {
                            System.out.println("capturable");
                            return true;
                        }
                    }
                }
            else return false;
        }
    }
    return false; 
    }


    public boolean canSelect(int x, int y) {
        if ((x < 8) && (y < 8) && (x > -1) && (y > -1) && ((x+y)%2==0)) {
            if (pieces[x][y]!=null) { // case 1: there is a piece at the square
                if (!selected && !moveMade) { // if nothing has been selected you can select any piece on your turn
                    if (fireTurn) { 
                        if (pieces[x][y].isFire()) {
                            selected = true;
                            selectedX = x;
                            selectedY = y;
                            System.out.println("can select 1");
                            return true;
                        }
                    }
                    if (!fireTurn) { 
                        if (pieces[x][y].side() == 1) {
                            selected = true;
                            selectedX = x;
                            selectedY = y;
                            System.out.println("can select 2");
                            return true;
                        }
                    }
                } // no previous selection
                
                if (selected && !moveMade){ 
                    if (fireTurn) { 
                        if (pieces[x][y].isFire()) {
                            selected = true;
                            selectedX = x;
                            selectedY = y;
                            System.out.println("can select 3");
                            return true;
                        }
                    }
                    if (!fireTurn) { 
                        if (pieces[x][y].side() == 1) {
                            selected = true;
                            selectedX = x;
                            selectedY = y;
                            System.out.println("can select 4");
                            return true;
                        }
                    }
                    System.out.println("cannot select 1");
                    selected=false;
                    return false;
                } // selected but no moves made
                


                System.out.println("cannot select 2");
                selected = false;
                return false;


            } // non null square container

            if (pieces[x][y]==null && selected) { // if the square is null, a piece must have been previously selected
                    
                System.out.println("X:"+x);
                System.out.println("y:"+y);
                System.out.println("selectedX:"+selectedX);
                System.out.println("selectedy:"+selectedY);
                
                /////////////////////////////////////
                //ADD IMPLMENTATION FOR MULTICAPTURE
                if ((pieces[(x+selectedX)/2][(y+selectedY)/2]!=null) && 
                    (pieces[selectedX][selectedY].hasCaptured())){
                    System.out.println("valid multicapture square");
                    selected=true;
                    return true;
                }

                // END IMPLMENTATION FOR MULTICAPTURE
                /////////////////////////////////////

                if (validMove(selectedX, selectedY, x, y) & !moveMade) {
                    System.out.println("valid move to empty square");
                    return true;
                    }

                System.out.println("A move has already been made, please end your turn");
                selected=false;
                return false;
            } // null square container
        }
    System.out.println("cannot select");
    selected=false;
    return false; // this means square is out of bounds or a red square or empty with no selection
    }

 
    public void select(int x, int y) {
        if (selected == true) {
            if (pieces[x][y]==null) {
                System.out.println("You have selected empty square at ("+x+", "+y+")");

                //MULTICAPTURE SELECT
                /////////////////////////////////////
                if (moveMade) {
                    if (validMove(selectedX, selectedY, x, y)) {
                        System.out.println("moving to multicapture square");
                        pieces[selectedX][selectedY].move(x,y);
                        selectedX=x;
                        selectedY=y;
                        moveMade=true;
                    }
                }
                /////////////////////////////////////
                // END MULTICAPTURE SELECT

                if (!moveMade) {
                    if (validMove(selectedX, selectedY, x, y)) {
                        System.out.println("moving to capture square");
                        pieces[selectedX][selectedY].move(x,y);
                        selectedX=x;
                        selectedY=y;
                        moveMade=true;
                    }
                }
                /*
                if (validMove(selectedX, selectedY, x, y) && !moveMade) {
                    pieces[selectedX][selectedY].move(x,y);
                    selectedX=x;
                    selectedY=y;
                    moveMade=true;
                }
                */
            } 
        }

        else {
            System.out.println("You have selected piece at ("+x+", "+y+")");
            selectedX=x;
            selectedY=y;
            selected=true;
            moveMade=false;
        }
    }


    
    public Piece remove(int x, int y) {

        if ((x > 7) || (y > 7)) {
            System.out.println("(x, y) out of bounds");
            return null;
        }
        else if (pieces[x][y] == null) {
            System.out.println("no piece at (x, y)");
            return null;
        }
        else { 
            Piece removed = pieces[x][y];
            pieces[x][y]=null;
            if (removed.isFire()) firePiecesLeft-=1;
            if (removed.side()==1) waterPiecesLeft-=1;
            return removed;
        }
    }


    public boolean canEndTurn() {
        return (moveMade || ((pieceAt(selectedX,selectedY)!= null) && pieceAt(selectedX,selectedY).hasCaptured()));
    }

    public void endTurn() {
        System.out.println("ending turn last selected piece was "+selectedX+" & "+selectedY);
        if (fireTurn) {
            fireTurn = false;
        }
        else {
            fireTurn = true;
        }
        System.out.println("turn changed");
        selected=false;
        //call done capturing
        if (pieceAt(selectedX,selectedY)!=null) {
            System.out.println("captures set to false");
            pieceAt(selectedX,selectedY).doneCapturing();
        }   
        moveMade = false; 

    }

    public String winner() {
        //System.out.println("firepiecesleft"+firePiecesLeft);
        //System.out.println("waterpiecesleft"+waterPiecesLeft);
        
        // bomb situations
        if ((firePiecesLeft==1 && waterPiecesLeft==0) || (firePiecesLeft==0 && waterPiecesLeft==1)) {
            System.out.println("checking for bomb piece");
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (pieces[i][j] != null){
                        if (pieces[i][j].isBomb()) {
                            System.out.println("bomb piece found");
                            return null;
                        }
                    }
                }
            }
         }

        if ((firePiecesLeft==0) && (waterPiecesLeft==0)) return "No one";
        
        else if (waterPiecesLeft==0) {
            System.out.println("fire wins");
            return "Fire"; }
        else if (firePiecesLeft==0) {
            System.out.println("water wins");
            return "Water"; }
        else return null;
        
    }


}