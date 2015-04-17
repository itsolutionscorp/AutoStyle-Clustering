
public class Board {
    
    private Piece[][] pArray;
    private boolean playerTurnFire = true; //start fire first
    private Piece pieceSelected;
    private int currSelectedX;
    private int currSelectedY;
    private boolean currSelectedhasMoved = false;
    
    public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty == true) {
            pArray = new Piece[8][8];
        } else {
            pArray = new Piece[8][8];
            
            //initialize red pawns and red bombs and blue shields
            for(int i = 0; i <= 6; i = i + 2) { 
                pArray[i][0] = new Piece(true, this, i, 0, "pawn");
                pArray[i][2] = new Piece(true, this, i, 2, "bomb");
                pArray[i][6] = new Piece(false, this, i, 6, "shield");
            }
            
            //initialize red shields and blue bombs and blue pawns
            for(int i = 1; i <= 7; i = i + 2) { 
                pArray[i][1] = new Piece(true, this, i, 1, "shield");
                pArray[i][5] = new Piece(false, this, i, 5, "bomb");
                pArray[i][7] = new Piece(false, this, i, 7, "pawn");
            }

        }
    }
     
    public Piece pieceAt(int x, int y) {
        if (pArray[x][y] == null) {
            return null;
        } else {
            return pArray[x][y];
        }
    }
    
    public boolean canSelect(int x, int y) {    
            //empty space selection
            if((pArray[x][y] == null)) { //selecting empty and piece is held
                if (pieceSelected != null) {
                    if(!((currSelectedhasMoved == true) && (pieceSelected.hasCaptured() == false))) {//if not no more moves
                        if ((validMove(currSelectedX, currSelectedY, x, y) == true) && (canEndTurn() == false)) {
                            return true;
                        } else if((pieceSelected.hasCaptured() == true) && (validMove(currSelectedX, currSelectedY, x, y) == true)) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
                
            //piece selection
            } else if((pArray[x][y].isFire() == playerTurnFire) && (canEndTurn() == false)) { //position is piece of same color as player's turn and player has not moved a piece
                return true;
            } else { //otherwise cannot select
                return false;
            } 
    }
    
    public void select(int x, int y) {
        if(canSelect(x, y) == true) {
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            if(pArray[x][y] == null) {
                pieceSelected.move(x, y);
                currSelectedhasMoved = true;
            } else if (pArray[x][y] != null) {
                if (pArray[x][y].isFire() == playerTurnFire) {
                    pieceSelected = pArray[x][y];
                    currSelectedX = x;
                    currSelectedY = y;
                }
            }
        }
    }
    
    private boolean validMove(int xi, int yi, int xf, int yf) { //xi, yi will always be coordinates of a piece
        if ((pieceSelected.isKing()) == false) { //given piece is not a king
            if(pArray[xf][yf] == null) { //moving to space
                if (!(xi == xf)) {
                    if((Math.abs(yf-yi) == 1) && (Math.abs(xf-xi) == 1)) { //moving one space
                        
                        if (((yf-yi) <= 0) && (playerTurnFire == true)) { //cannot move behind in y or sideways
                            return false;
                        } else if (((yf-yi) >= 0) && (playerTurnFire == false)) {
                            return false;
                        } else {
                            return true;
                        }
                        
                    } else if ((Math.abs(yf-yi) == 2) && (Math.abs(xf-xi) == 2)) { //moving two spaces
                    
                        if(pArray[Math.abs((xf+xi)/2)][Math.abs(yf+yi)/2].side() != pieceSelected.side()) {//for piece capturing
                        
                            if (((yf-yi) <= 0) && (playerTurnFire == true)) { //cannot move behind in y or sideways
                                return false;
                            } else if (((yf-yi) >= 0) && (playerTurnFire == false)) {
                                return false;
                            } else {
                                return true;
                            }
                        } else
                            return false;
                        
                    } else //may not move more than two spaces;
                        return false;
                } else //no moving in a straight line
                    return false;
     
            } else  //invalid move of piece to piece or piece to other
                return false;
            
        } else if((pieceSelected.isKing()) == true) {
            if(pArray[xf][yf] == null) { //moving to space
                if (!((xi == xf) || (yi == yf))) {
                    if((Math.abs(yf-yi) == 1) && (Math.abs(xf-xi) == 1)) { //moving one space
                        return true; 
                    } else if ((Math.abs(yf-yi) == 2) && (Math.abs(xf-xi) == 2)) { //moving two spaces
                    
                        if(pArray[Math.abs((xf+xi)/2)][Math.abs(yf+yi)/2].side() != pieceSelected.side()) {//for piece capturing
                            return true;
                        } else
                            return false;
                        
                    } else //may not move more than two spaces;
                        return false;
                } else //no moving in a straight line
                    return false;
     
            } else  //invalid move of piece to piece or piece to other
                return false;
        } else 
            return false;
        
    }
    
    public void place(Piece p, int x, int y) {
        if ((x < 0) || (y < 0) || (x > 7) || (y > 7)) {    
        } else if (p == null) {  
        } else {
            pArray[x][y] = p;
        }
    }
    
    public Piece remove(int x, int y) {
        if ((x < 0) || (y < 0) || (x > 7) || (y > 7)) {
            System.out.println("Position out of bounds.");
            return null;
        } else if (pArray[x][y] == null) {
            System.out.println("No piece exists in this position.");
            return null;
        } else {
            Piece toreturn = pArray[x][y];
            pArray[x][y] = null;
            return toreturn;
        }
    }
    
    public boolean canEndTurn(){
        if(pieceSelected != null) {
            if(pieceSelected.hasCaptured() == true) {
                return true;
            } else if(currSelectedhasMoved == true) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public void endTurn() {
        pieceSelected.doneCapturing();
        currSelectedhasMoved = false;
        pieceSelected = null;
        currSelectedX = -1;
        currSelectedY = -1;
        if(playerTurnFire == true) {
            playerTurnFire = false;
        } else if(playerTurnFire == false) {
            playerTurnFire = true;
        }
    }
    
    public String winner() {
        int firecount = 0;
        int watercount = 0;
        for(int i = 0; i < 8; i = i + 1) {
            for(int j = 0; j < 8; j = j + 1) {
                if(pArray[i][j] != null) {
                    if(pArray[i][j].isFire() == true) {
                        firecount = firecount + 1;
                    } else if(pArray[i][j].isFire() == false) {
                        watercount = watercount + 1;
                    } 
                }
            }
        }
        if((firecount == 0) && (watercount == 0)) {
            return "No one";
        } else if ((firecount > 0) && (watercount > 0)) {
            return null;
        } else if (firecount > watercount) {
            return "Fire";
        } else if (watercount > firecount) {
            return "Water";
        } else {
            return "asdf";
        }
    }
    
    
    private void whosTurn() {//debug turn purposes
        if(playerTurnFire == true)
            System.out.println("Fire");
        else
            System.out.println("Water");
    }
    
    private static void drawBoard(int N, Board b) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if(b.pArray[i][j] != null) { //if board space is not empty, then draw
                    int side = b.pArray[i][j].side();
                    if (side == 0) { //fire side draw
                        if (b.pArray[i][j].isBomb() == true) { //draw bombs
                            if (b.pArray[i][j].isKing() == true) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                        } else if (b.pArray[i][j].isShield() == true) { //draw shields
                            if (b.pArray[i][j].isKing() == true) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                        } else { //draw pawns
                            if (b.pArray[i][j].isKing() == true) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                    } else if (side == 1){ //water side draw
                        if (b.pArray[i][j].isBomb() == true) { //draw bombs
                            if (b.pArray[i][j].isKing() == true) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                        } else if (b.pArray[i][j].isShield() == true) { //draw shields
                            if (b.pArray[i][j].isKing() == true) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                        } else { //draw pawns
                            if (b.pArray[i][j].isKing() == true) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            }
                        }
                    } 
                } else { //if empty board space, do nothing
                }
            
            }
        }
    }
    
    public static void main(String[] args) {
        Board b = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            drawBoard(N, b);
            
                if (StdDrawPlus.mousePressed()) {
                    double x = StdDrawPlus.mouseX();
                    double y = StdDrawPlus.mouseY();
                    if(b.canSelect((int) x, (int) y) == true) {
                        b.select((int) x, (int) y);
                    }
                }
            
            
            if (StdDrawPlus.isSpacePressed()) {
                if(b.canEndTurn()) {
                    b.endTurn();
                }
            }
            StdDrawPlus.show(100);
        } 
        
        
    }
}
