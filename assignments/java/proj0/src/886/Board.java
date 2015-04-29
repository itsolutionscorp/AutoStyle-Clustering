/** 
 *  @author John Sebastian Ospina
 */

public class Board {
    /** Location of pieces. */
    private Piece[][] pieces = new Piece[8][8];
    private Piece selectedpiece; // holds on to piece that you select
    private Piece selected;
    private int hasselected = 0;
    private int turn = 0;
    private int storedxpos;
    private int storedypos;
    private boolean hasmoved = false;

public static void main(String[] args) {
    int N = 8;
    StdDrawPlus.setXscale(0, N);
    StdDrawPlus.setYscale(0, N);
    Board gamer = new Board(false);
    gamer.drawBoard(N);
    // if (StdDrawPlus.mousePressed() == true) {
    //     xofclicked = (int) Math.floor(StdDrawPlus.mouseX());
    //     yofclicked = (int) Math.floor(StdDrawPlus.mouseX());
    // }

    /** Monitors for mouse presses. Wherever the mouse is pressed,
        a new piece appears. */
    while(true) {
        gamer.clicker();
        gamer.drawBoard(N);
        gamer.spacebar();
        gamer.drawBoard(N);
        if (gamer.winner() != null) {
            System.out.print("You is soo done braaahhh!");
        }
        // gamer.drawPieces(N);  
        }   
    }

private void clicker() {
    if (StdDrawPlus.mousePressed()) {
        double x = StdDrawPlus.mouseX();
        double y = StdDrawPlus.mouseY();

        if (this.canSelect((int)x, (int)y)){
            this.select((int)x,(int)y);
        }
    }
    StdDrawPlus.show(10);
}

private void spacebar() {
    if (StdDrawPlus.isSpacePressed()) {
        if (this.canEndTurn()){
            this.endTurn();
        }
    }
}

private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.PINK);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                // if ((hasselected = 1) && (pieceAt(i,j) == selected)) {
                //     StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                // }

                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        if (pieces[i][j].isKing()) {
                            if (pieces[i][j].isBomb()) {
                               StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                               System.out.println("red bomb");}
                            else if (pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);}
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);}
                        }    
                        else {
                            if (pieces[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                            else if (pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }    
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                    }    
                    else {
                        if (pieces[i][j].isKing()) {
                            if (pieces[i][j].isBomb()) {
                               StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            } 
                            else if (pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                                System.out.println("blue pawn");
                            }
                        }
                        else {
                            if (pieces[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                            else if (pieces[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            }
                        }
                    }
            }
        }
    }
}

public Board(boolean shouldBeEmpty) {
    if (shouldBeEmpty == false){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0 && j == 2)
                   pieces[i][j] = new Piece(true, this, i, j, "bomb");
                if ((i + j) % 2 == 0 && j == 1)
                    pieces[i][j] = new Piece(true, this, i, j, "shield");
                if ((i + j) % 2 == 0 && j == 0)
                    pieces[i][j] = new Piece(true, this, i, j, "pawn");
                if ((i + j) % 2 == 0 && j == 5)
                    pieces[i][j] = new Piece(false, this, i, j, "bomb");
                if ((i + j) % 2 == 0 && j == 6)
                    pieces[i][j] = new Piece(false, this, i, j, "shield");
                if ((i + j) % 2 == 0 && j == 7)
                    pieces[i][j] = new Piece(false, this, i, j, "pawn");
            }
        }
    }
}

public Piece pieceAt(int x, int y){
    if (x > 7 || y > 7 || x < 0 || y < 0)
        return null;
    // return pieces[x][y];
    if (pieces[x][y] == null) //redundant
        return null;
    else
        return pieces[x][y];
}

public void place(Piece p, int x, int y) {
    if (x > 7 || y > 7 || x < 0 || y < 0 || p == null) {
        return;
    }
    // else if (p == pieceAt(x, y))  {
    //     remove(p.x,p.y); }
    else if (pieceAt(x, y) == null) {
        System.out.println("ghgh");
        // p.move(x,y);
        pieces[x][y] = p;
        // hasmoved = true;
    }
}        

public Piece remove(int x, int y) {
    if (x > 7 || y > 7 || x < 0 || y < 0) {
        System.out.println("Out of bounds.");
        return null;
    }
    if (pieces[x][y] == null) {
        System.out.println("Nothing here.");
        return null;
    }
    else {
        Piece removedpiece = pieces[x][y]; //save old piece
        pieces[x][y] = null; //remove the old piece
        return removedpiece; //return the piece that was removed
    }
}

public boolean canSelect(int x, int y) {
    if (hasselected == 1) {
        if (pieceAt(x, y) != null){
            if (turn % 2 == 0) {
                if (pieceAt(x, y).isFire()) {
                    return true; }
                else { //if water
                    return false; }
            }
            else if (turn % 2 == 1) { 
                if (pieceAt(x,y).isFire()) {
                    return false; }
                else { //if water
                    return true; }
            }                   
        }
        else { //moving to empty place (pieceAt(x, y) == null)
            if (selected.hasCaptured()) {
                if (validMove(storedxpos, storedypos, x, y)) {
                    return (storedxpos == x +2 || storedxpos == x-2);
                }
                else
                    return false;
            }   
            else {
                if (validMove(storedxpos, storedypos, x, y)) {
                    return !hasmoved;
                }
                else
                    return false;
            }
        }
    }
    else if (hasselected == 0){ //if no piece selected
        if (pieceAt(x, y) != null){
            if (turn % 2 == 0) {
                if (pieceAt(x, y).isFire()) {
                    return true; }
                else { //if water
                    return false; }
            }
            else if (turn % 2 == 1) { 
                if (pieceAt(x,y).isFire()) {
                    return false; }
                else { //if water
                    return true; }
            }
        }
    }    
    return false; 
}

private boolean validMove(int xi, int yi, int xf, int yf) {
    int dx = xf - xi;
    int dy = yf - yi;
    if (pieceAt(xi,yi) != null) {

        if (xi < 8 || yi < 8 || xi >= 0 || yi >= 0 || xf < 8 || yf < 8 || xf >= 0 || yf >= 0) {
            // if (pieceAt(xf,yf) != null)
            //     return;
            if (pieceAt(xf,yf) == null) {
                if (pieceAt(xi,yi).isFire() || pieceAt(xi,yi).isKing()) {
                    // fire moving up & right
                    if ((dx ==1) && (dy == 1)){
                        return true;
                    }
                    // fire moving up & left
                    if ((dx == -1) && (dy == 1)){
                        return true;
                    }
                    //fire capture up right
                    if ((dx == 2) && (dy == 2) && (pieceAt(xf-1,yf-1) != null) && (pieceAt(xf-1,yf-1).isFire() == false)){
                        return true;
                    }
                    //fire capture up left
                    if ((dx == - 2) && (dy == 2) && (pieceAt(xf+1,yf-1) != null) && (pieceAt(xf+1,yf-1).isFire() == false)) {
                        return true;
                    }
                }
                else if (pieceAt(xi,yi).isFire() == false || pieceAt(xi,yi).isKing()){
                    // water moving down & right
                    if ((dx == -1) && (dy == -1)){
                        return true;
                    }
                    // water moving up & left
                    if ((dx == 1) && (dy == -1)){
                        return true;
                    }
                    //fire capture up right
                    if ((dx == -2) && (dy == -2) && (pieceAt(xf+1,yf+1) != null) && (pieceAt(xf+1,yf+1).isFire())){
                        return true;
                    }
                    //fire capture up left
                    if ((dx == 2) && (dy == -2) && (pieceAt(xf-1,yf+1) != null) && (pieceAt(xf-1,yf+1).isFire())) {
                        return true;
                    }
                }
            }
        }
    }
    return false;
}    

public void select(int x, int y) {
    if (x < 8 || y < 8 || x >= 0 || y >= 0) {
    System.out.println(x + ", " + y);
    if (pieceAt(x,y) != null) { //not empty space
        selected = pieceAt(x,y);
        storedxpos = x;
        storedypos = y;
        hasselected = 1;
        
    }
    else {
        place(selected, x, y);
        selected.move(x,y);
        hasmoved = true;
    }
}
}

public boolean canEndTurn(){
    if (hasmoved) {
        return true;
    }
    else
    return false;
}

public void endTurn() {
    hasselected = 0;
    selected.doneCapturing();
    hasmoved = false;
    turn += 1;
}

public String winner() {
    int fire = 0;
    int water = 0;
    for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
               if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire())
                        fire += 1;
                    if (pieces[i][j].isFire() == false)
                        water += 1;               
                }
            }
    }
    if ((water == 0) && (fire > 0))
        return "Fire";
    if ((water > 0) && (fire == 0))
        return "Water";
    if ((water == 0) && (fire == 0))
        return "No one";
    else
        return null;
}

}