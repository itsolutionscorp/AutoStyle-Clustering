public class Board {
    /** Location of pieces. */
    private boolean isP1 =true; 
    private int prevx = -1;
    private int prevy= -1;
    private Piece[][] pieceslist; // change to pieces! 
    private boolean didselect = false;
    // private boolean exploded = false;
    private boolean didmove = false;
//THIS IS THE FINAL
    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
 //GUI and main method
    public static void main(String[] args) {
        boolean isP1 = true;
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);
        b.dBoard();
        while (b.winner() == null) {
        // while (true) {
            b.draw();
            if (StdDrawPlus.mousePressed()) {
                int xm = (int)StdDrawPlus.mouseX();
                int ym = (int)StdDrawPlus.mouseY();
                if (b.canSelect(xm, ym)){
                    b.select(xm, ym);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn() == true) {
                    b.endTurn();
            }

            // System.out.println(b.winner() + "won!");
            StdDrawPlus.show(1000000);
        }
    }
}

    //Instantiates the peices into an array
    private void makePieces() {
        pieceslist = new Piece[8][8];
        for (int col = 0; col < 8; col +=1) {
            for (int row = 0; row < 8; row += 1){
                if ((row == 0) && (col % 2 == 0)) {
                    pieceslist[col][row] = new Piece(true, this, col, row, "Pawn"); 
                } else if ((row == 1) && (col % 2 != 0)) {
                    pieceslist[col][row] = new Piece(true, this, col, row, "Shield");
                } else if ((row == 2) && (col % 2 == 0)) {
                    pieceslist[col][row] = new Piece(true, this, col, row, "Bomb");
                } else if ((row == 5) && (col % 2 != 0)) {
                    pieceslist[col][row] = new Piece(false, this, col, row, "Bomb");
                } else if ((row == 6) && (col % 2 == 0)) {
                    pieceslist[col][row] = new Piece(false, this, col, row, "Shield");
                } else if ((row == 7) && (col % 2 != 0)) {
                    pieceslist[col][row] = new Piece(false, this, col, row, "Pawn");
                }
            }
        }
    }

    //draws the pieces
    private void draw() {
        for (int col = 0; col<8; col +=1) {
            for (int row = 0; row <8; row +=1) {
                if ((col + row) % 2 == 0) 
                    StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
                else                
                    StdDrawPlus.setPenColor(StdDrawPlus.BOOK_RED);
                    StdDrawPlus.filledSquare(col + .5, row + .5, .5);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieceslist[col][row] != null) {
                    if (pieceslist[col][row].isKing() == false) { // THIS IS FOR ALL NORMAL PIECE
                        if ((pieceslist[col][row].isShield() != true) && ((pieceslist[col][row].isBomb() != true) && (pieceslist[col][row].isFire() == true))) {
                            StdDrawPlus.picture(col + .5, row + .5, "img/pawn-fire.png", 1, 1);
                        } else if ((pieceslist[col][row].isShield() != true) && ((pieceslist[col][row].isBomb() != true) && (pieceslist[col][row].isFire() == false))) {
                            StdDrawPlus.picture(col + .5, row + .5, "img/pawn-water.png", 1, 1);
                        } else if ((pieceslist[col][row].isShield() == true) && (pieceslist[col][row].isFire() == true)) {
                            StdDrawPlus.picture(col + .5, row + .5, "img/shield-fire.png", 1, 1);
                        }else if ((pieceslist[col][row].isShield() == true) && (pieceslist[col][row].isFire() == false)) {
                            StdDrawPlus.picture(col + .5, row + .5, "img/shield-water.png", 1, 1);
                        }else if ((pieceslist[col][row].isBomb() == true) && (pieceslist[col][row].isFire() == true)) {
                            StdDrawPlus.picture(col + .5, row + .5, "img/bomb-fire.png", 1, 1);
                        }else if ((pieceslist[col][row].isBomb() == true) && (pieceslist[col][row].isFire() == false)) {
                            StdDrawPlus.picture(col + .5, row + .5, "img/bomb-water.png", 1, 1);
                        }
                    }else { //THIS IS TO REDRAW IF YOUVE BEEN KINGED
                        if ((pieceslist[col][row].isShield() != true) && ((pieceslist[col][row].isBomb() != true) && (pieceslist[col][row].isFire() == true))) {
                            StdDrawPlus.picture(col + .5, row + .5, "img/pawn-fire-crowned.png", 1, 1);
                        } else if ((pieceslist[col][row].isShield() != true) && ((pieceslist[col][row].isBomb() != true) && (pieceslist[col][row].isFire() == false))) {
                            StdDrawPlus.picture(col + .5, row + .5, "img/pawn-water-crowned.png", 1, 1);
                        } else if ((pieceslist[col][row].isShield() == true) && (pieceslist[col][row].isFire() == true)) {
                            StdDrawPlus.picture(col + .5, row + .5, "img/shield-fire-crowned.png", 1, 1);
                        }else if ((pieceslist[col][row].isShield() == true) && (pieceslist[col][row].isFire() == false)) {
                            StdDrawPlus.picture(col + .5, row + .5, "img/shield-water-crowned.png", 1, 1);
                        }else if ((pieceslist[col][row].isBomb() == true) && (pieceslist[col][row].isFire() == true)) {
                            StdDrawPlus.picture(col + .5, row + .5, "img/bomb-fire-crowned.png", 1, 1);
                        }else if ((pieceslist[col][row].isBomb() == true) && (pieceslist[col][row].isFire() == false)) {
                            StdDrawPlus.picture(col + .5, row + .5, "img/bomb-water-crowned.png", 1, 1);
                        }
                    }
                }
            }
        }
    }


    

    //draw board
    private void dBoard() {
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                if ((col + row) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.LIGHT_GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.BOOK_RED);
                StdDrawPlus.filledSquare(col + .5, row + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }

    //Board Constructor
    public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty != true){ // draws an empty board
            dBoard(); 
            makePieces();          
        } else {
            dBoard();
            makePieces();
            draw(); 
        }
    }



    public Piece pieceAt(int x, int y) {
        if (((x < 0) || (x > 7)) || ((y < 0) || (y > 7))) {
            return null;
        } else if (pieceslist[x][y] != null) {
            return pieceslist[x][y];
        } else {
            return null;
        }
    }

    //Tori Cabot - bjs, helped with the debugging of canSelect
    public boolean canSelect(int x, int y) {
        if (pieceAt(x,y) != null){
            if (isP1 == pieceAt(x, y).isFire()) {
                if (didselect == false) {
                    return true;
                } else if ((didmove == false) && (didselect==true)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }else if ((pieceAt(x, y) == null))  { // empty space
            if (didselect == false) {
                return false;
            }else if (didmove == false) {
                if (validMove(prevx, prevy, x, y) == true) {
                    return true;
                }else if ((didmove == true) && (validMove(prevx, prevy, x, y))) { //double capture
                    return true;
                }else {
                    return false;
                }
            }else {
                return false;
            }
        }else {
            return false;
        }
    } 
           

/* tests at least five cases: 
    one) can the piece move forward into an empty space? (is xf, yf empty)
    two) can the piece move forward and jump because the pieces in xf yf are the enemny
    three) is the piece a king? if yes, test the first two but backwards
    four) check if the next space is not empty (not moving into a space that is occupied) 
    five) check that the empty space is not too far away 
        (not trying to do a double jump wihtout going through the first jump)
        (not trying to simply go over like 2 pieces at a time (illegal jump))
    six) you've been kinged and can do a double jump backwards
    seven) you have to keep track of what "Race" you are and what youre attacking
    eight) spacebar - whose turn is it
    
    if xf - xi > 0, moving up 
    else moving down
    if type lines up and moving right way- good
    */

    //Cesar Villalobos- ayf, helped to debug validMove
    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece current = pieceslist[xi][yi];
        Piece future = pieceslist[xf][yf];
        if ((xf < 0) || (xf > 7) || (yf < 0) || (yf > 7)){ //checks everytime if youre trying to go out of bounds
            return false;
        }else if (current != null) {
            if (future == null) {
                if ((current.isFire() != true) || (current.isKing() == true)) {
                    if ((((xf - xi) == 2) || ((xf- xi) == -2)) && ((yf - yi) == -2)) {
                        if ((pieceAt(xi+1,yi-1) != null) && (pieceAt(xi+1, yi-1).isFire() == true)) { // a fire piece
                            return true;
                        }else if ((pieceAt(xi-1, yi-1) != null) && (pieceAt(xi-1, yi-1).isFire() == true)) {
                            return true;
                        }else {
                            return false;
                        }
                    }else if ((((xf- xi) == 1) || ((xf - xi) == -1)) && ((yf - yi) == -1)){
                        return true;
                    }else {
                        return false; // TOO FAR
                    }
                } else if ((current.isFire() == true) || (current.isKing() == true)) {
                    if ((((xf - xi) == 2) || ((xf- xi) == -2)) && ((yf - yi) == 2)) {
                        if ((pieceAt(xi+1,yi+1) != null) && (pieceAt(xi+1, yi+1).isFire() != true)) { // a fire piece
                            return true;
                        }else if ((pieceAt(xi-1, yi+1) != null) && (pieceAt(xi-1, yi+1).isFire() != true)) {
                            return true;
                        }else {
                            return false;
                        }
                    } else if ((((xf- xi) == 1) || ((xf - xi) == -1)) && ((yf - yi) == 1)) {
                        return true;
                    }else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
                }
        } else {
            return false;
            }  
    }     
        /* }else if (current.isFire() == true){
            if (future == null) { // can the piece move
                if ((((xf - xi) == 1) || ((xf- xi) == -1)) && ((Math.abs(yf - yi) == 1) && ((yf - yi) > 0))) { //is NOT a capture move  // fire pieces move up only
                    if (didmove == false){
                        return true;
                    }else{
                        return false;
                    }
                }else if ((((xf - xi) == 2) || ((xf- xi) == -2)) && ((Math.abs(yf - yi) == 2) && ((yf - yi) > 0))) { // capture move
                    if ((pieceAt(xi+1,yi+1) != null) && (pieceAt(xi+1, yi+1).isFire() != true)) { // a water piece
                        return true;
                    }else if ((pieceAt(xi-1, yi+1) != null) && (pieceAt(xi-1, yi+1).isFire() != true)) {
                        return true;
                    }else {
                        return false;
                    }
                } else {
                    return false; //too far
                }
            } else {
                return false; //dont crash into a piece
            }
        }else if (current.isFire() != true) {
            if (future == null) { // can the piece move
                if ((((xf - xi) == 1) || ((xf- xi) == -1)) && ((Math.abs(yf - yi) == 1) && ((yf - yi) < 0))) { //is NOT a capture move  // fire pieces move up only
                    if (didmove == false){
                        return true;
                    }else{
                        return false;
                    }
                }else if ((((xf - xi) == 2) || ((xf- xi) == -2)) && ((Math.abs(yf - yi) == 2) && ((yf - yi) < 0))) { // capture move
                    if ((pieceAt(xi+1,yi-1) != null) && (pieceAt(xi+1, yi-1).isFire() == true)) { // a fire piece
                        return true;
                    }else if ((pieceAt(xi-1, yi-1) != null) && (pieceAt(xi-1, yi-1).isFire() == true)) {
                        return true;
                    }else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false; 
            }
        } else if ((current.isKing() == true) && (current.isFire() == true)) {
            if (future == null){
                if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1))   {
                    if (didmove == false){
                        return true;
                    }else{
                        return false;
                    }
                }else if ((Math.abs(xf- xi) == 2) && ((Math.abs(yf-yi) ==2))) {
                    if (pieceAt(xi+1,yi+1).isFire() != true) {
                        return true;
                    }else if (pieceAt(xi+1,yi-1).isFire() != true) {
                        return true;
                    }else if (pieceAt(xi-1,yi+1).isFire() != true) {
                        return true;
                    }else if (pieceAt(xi-1,yi-1).isFire() != true) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false; // too far
                }
            }else {
                return false; //crash into a piece
            }
        } else if ((current.isKing() == true) && (current.isFire() != true)) {
            if (future == null){
                if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1))   {
                    if (didmove == false){
                        return true;
                    }else{
                        return false;
                    }
                }else if ((Math.abs(xf- xi) == 2) && ((Math.abs(yf-yi) ==2))) {
                    if (pieceAt(xi+1,yi+1).isFire() != true) {
                        return true;
                    }else if (pieceAt(xi+1,yi-1).isFire() == true) {
                        return true;
                    }else if (pieceAt(xi-1,yi+1).isFire() == true) {
                        return true;
                    }else if (pieceAt(xi-1,yi-1).isFire() == true) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false; // too far
                }
            }else {
                return false; //crash into a piece
            }
        } else {
            return false;
        }
    }
*/
                
    public void select(int x, int y) {
        if (pieceAt(x, y) != null) {
            // StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            // StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            // draw();
            System.out.println("this is weird");
            // place(pieceAt(x, y), x, y);
            if ((prevx < 0) && (prevy < 0)) {
                
                prevx = x;
                prevy = y;
            }else {
                pieceAt(prevx, prevy).move(x,y);
                prevx = x;
                prevy = y;
                // didselect = true;
                didmove = true;
            }
        }else if ((pieceAt(x, y) == null) && (((prevx > -1) && (prevy > -1)) && (pieceAt(prevx, prevy) != null))) {
            // StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            // StdDrawPlus.filledSquare(x + .5, y + .5, .5);
           
            // System.out.println(prevx);

            // System.out.println(prevy);

            didselect = true;
            prevx = x;
            prevy = y;
          

            // System.out.println(storageUnit);

            // System.out.println(prevy);
        }
        
    }

   
    public void place(Piece p, int x, int y) {
        if (((x > 0) && (x < 7)) && ((y > 0) && (y < 7)) && (p != null)) {
            // pieceAt(x, y) = p;
            pieceslist[x][y] = p;
            didmove = true;
        }
    }

    public Piece remove(int x, int y) {
        if (((x < 0) || (x > 7)) || ((y < 0) || (y > 7))) {
            System.out.println("Not currently on playing board");
            return null;
        } else if ((pieceAt(x, y) == null)) {
            System.out.println("No piece to remove");
            return null;
        } else if (pieceslist[x][y] != null) {
            pieceslist[x][y] = null;
            return pieceslist[x][y];
        } else {
            return null;
        }
    }


    public boolean canEndTurn() {
        if (didmove == true){
            return true;
        }else {
            return false;
        }
    }

    public void endTurn() {
        if (isP1 == true){
            isP1 = false;
        }else {
            isP1 = true;
        }
        // exploded = false;
        didselect = false;
        didmove = false;
        prevx = -1;
        prevy = -1;
    }

//HELLO AG

    //Tori Cabot - bjs, helped with debugging this too
    public String winner() {
        boolean fireleft = false;
        boolean waterleft = false;
        boolean dead = true; 
        for (int row = 0; row < 8; row += 1){
            for (int col = 0; col<8; col +=1){
                if (pieceslist[row][col] != null) {
                    if (pieceslist[row][col].isFire() == true) {
                        fireleft = true;
                        dead = false;
                    } else {
                        waterleft = true;
                        dead = false;
                    }
                }else {
                    continue;
                }           
            }  
        }

        if (dead == true){ //no pieces on board
            return "No one";
        } else if ((fireleft) && (waterleft)) { //game in progress
            return null;
        } else if ((fireleft == true) && (waterleft == false)) { // fire yes water no
            return "Fire";
        } else {
            return "Water";
        }
    }
}



