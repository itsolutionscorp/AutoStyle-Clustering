//git hub has been changing my files and refusing to let me push the changes back
public class Board {
    private Piece[][] pieces;
    private int turn = 0;  
    private int xSelect = -1; 
    private int ySelect = -1;
    private int fire_pieces; 
    private int water_pieces; 
    private boolean Selected = false;
    private boolean has_moved = false;  

    public static void main(String args[]) {
        int N = 8; 
        StdDrawPlus.setXscale(0, N); 
        StdDrawPlus.setYscale(0, N); 
        Board b = new Board(false);
        b.numPieces(); 
        b.drawBoard(N); 
        while (b.winner() == null) {
            b.numPieces(); 
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX(); 
                double y = StdDrawPlus.mouseY(); 
                if (b.canSelect((int) x, (int) y)) {
                   b.select((int) x, (int) y);
                   b.drawBoard(N);
                   b.numPieces(); 
                }
            b.numPieces(); 
            }
            if (StdDrawPlus.isSpacePressed()) {
                if(b.canEndTurn()) {
                    b.numPieces();
                    b.endTurn();  
                    b.drawBoard(N); 
                    for (int i = 0; i < N; i++) {
                        for (int j = 0; j < N; j++) {
                            if (b.pieces[i][j] != null) {
                                b.pieces[i][j].doneCapturing(); 
                            }
                        }
                    }
                }
            }
        b.numPieces(); 
        StdDrawPlus.show(10); 
        } 
    }             

    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[8][8];
        if (shouldBeEmpty == true) {
            for (int i = 0; i < 8; i ++) {
                for (int j = 0; j < 8; j ++) {
                    pieces[i][j] = null; 
                }
            }
        }         
        else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i % 2 == 0) && (j == 0)) {
                        pieces[i][j] = new Piece(true, this, i, j, "Pawn"); 
                    }
                    if ((i % 2 != 0) && (j == 1)) {
                        pieces[i][j] = new Piece(true, this, i, j, "Shield"); 
                    }
                    if ((i % 2 == 0) && (j == 2)) {
                        pieces[i][j] = new Piece(true, this, i, j, "Bomb"); 
                    }
                    if ((i % 2 != 0) && (j == 5)) {
                        pieces[i][j] = new Piece(false, this,i, j, "Bomb"); 
                    }
                    if ((i % 2 == 0) && (j == 6)) {
                        pieces[i][j] = new Piece(false, this, i, j, "Shield"); 
                    }
                    if ((i % 2 != 0) && (j == 7)) {
                        pieces[i][j] = new Piece(false, this, i,j, "Pawn"); 
                    }
                }
            }
        }
    }

    private int switchTurn(int t) {
        if (turn == 0) {
            return 1; 
        }
        else {
            return 0;
        }
    }
 
    private void numPieces() {
        int tempfire_pieces = 0; 
        int tempwater_pieces = 0; 
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((pieces[i][j] != null) && (pieces[i][j].isFire())) {
                    tempfire_pieces += 1; 
                }
                if ((pieces[i][j] != null) && (pieces[i][j].isFire() == false)) {
                    tempwater_pieces += 1; 
                }
            }
        } 
        fire_pieces = tempfire_pieces; 
        water_pieces = tempwater_pieces; 
    }                            
                    
    private boolean game_over(int fp, int wp) {
        if ((fp == 0) || (wp == 0)) {
            return true; 
        }
        return false; 
    }

    private void drawBoard(int N) {
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j ++) {
                if ((i == xSelect) && (j == ySelect) && (pieces[i][j] != null)) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE); 
                }
                else if (((i + j) % 2) == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED); 
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5); 
                if ((pieces[i][j] != null) && (pieces[i][j].isFire() == true)) {
                    if (pieces[i][j].isBomb() == true) {
                        if (pieces[i][j].isKing() == true) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                        }
                        else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1); 
                        }
                    }
                    else if (pieces[i][j].isShield() == true) {
                        if (pieces[i][j].isKing() == true) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                        }
                        else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        }
                    }
                    else {
                        if (pieces[i][j].isKing() == true) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                        }
                        else {    
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1); 
                        }
                    }
                }
                if ((pieces[i][j] != null) && (pieces[i][j].isFire() == false)) {
                   if (pieces[i][j].isBomb() == true) {
                       if (pieces[i][j].isKing() == true) {
                           StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1); 
                       }
                       else {
                           StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1); 
                       }
                   }
                   else if (pieces[i][j].isShield() == true) {
                       if (pieces[i][j].isKing() == true) {
                           StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1); 
                       }
                       else {
                           StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1); 
                       }
                   }
                   else { 
                       if (pieces[i][j].isKing() == true) {
                           StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1); 
                       }
                       else {
                           StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1); 
                       }
                   }
                }                     
            }            
        }
    }

    private  boolean out_of_bounds(int x, int y, int N) {
        if ((x < 0) || (x > N) || (y > N) || (y < 0)) {
            return true; }
        else {
            return false; }
    }

    private  boolean piece_there(int x, int y) {
        if (pieces[x][y] == null) {
            return false;
        }
        else {
            return true;
        } 
    }

    public Piece pieceAt(int x, int y) {
        if ((out_of_bounds(x, y, 7) == true) || (pieces[x][y] == null)) {
            return null;
        } 
        else  {
            return pieces[x][y]; 
        }
    }
    public boolean canSelect(int x, int y) {
   
        if ((pieces[x][y] != null) && (Selected == false) && (pieces[x][y].side() == turn)) {  
            return true; 
        }
        else if ((pieces[x][y] != null) && (Selected) && (has_moved == false) && (pieces[x][y].side() == turn)) {
            return true; 
        }

        else if ((pieces[x][y] == null) && (Selected)) {
            return validMove(xSelect, ySelect, x, y); 
        }
        else {
                return false; 
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        double xDist = (xf - xi); 
        double yDist = (yf - yi);
        int xMid = (xi + xf) >>> 1; // was previously "/2" which caused a findBug flag, used Allen Zhu's piazza post's
        int yMid = (yi + yf) >>> 1; // sugggestion instead.  
        if ((pieces[xf][yf] != null) && (pieces[xf][yf].side() == turn)) {
            return false;
        }
        if ((pieces[xi][yi] != null) && (pieces[xi][yi].isKing() == false)) {
            if (pieces[xi][yi].isFire()) {
                if (yDist < 0) {
                    return false; 
                }
            }
            if (pieces[xi][yi].isFire() == false) {
                if (yDist > 0) {
                    return false; 
                }
            }
        }
        if ((Math.abs(xDist) == 1) && (Math.abs(yDist) == 1) && (pieces[xf][yf] == null)) {
            if (has_moved) {
                return false; 
            }
            return true; 
        }
        if ((Math.abs(xDist) == 2) && (Math.abs(yDist) == 2)) {
            if (pieces[xMid][yMid] == null) {
                return false; 
            }
            if (pieces[xMid][yMid] != null) {
                if (pieces[xMid][yMid].side() == turn) {
                    return false; 
                }
                return true;  
            }
        }
        if ((has_moved) || (pieces[xi][yi].hasCaptured() == false)) {
            return false;  
        }
        return false;  
    }

    public void select(int x, int y) {
        if (pieces[x][y] != null) {
            xSelect = x; 
            ySelect = y; 
            Selected = true;
        }
        else {
            pieces[xSelect][ySelect].move(x, y);
            xSelect = x; 
            ySelect = y; 
            has_moved = true;  
            Selected = true;  
// need special condition for bombs/captured pieces
        }
    }
    public void place(Piece p, int x, int y) {
        if ((p != null) && (out_of_bounds(x, y, 7) == false)) {
                pieces[x][y] = p; 
                pieces[x][y] = p;
        }
    }       
    public Piece remove(int x, int y) {
        if (out_of_bounds(x, y, 7)) {
            System.out.println("index out of bounds"); 
            return null; }
        else if (piece_there(x, y) == false) {
            System.out.println("cannot remove: no existing piece on tile"); 
            return null; }
        else {
            Piece temp = pieces[x][y]; 
            pieces[x][y] = null;
            return temp;             
        }
    }
    public boolean canEndTurn() {
        if (has_moved) {
            return true; 
        }
        return false; 
    }

    public void endTurn() {
        Selected = false; 
        has_moved = false; 
        xSelect = -1; 
        ySelect = -1;
        turn = switchTurn(turn);  
    }
    public String winner() {
        numPieces(); 
        if (pieces == null) {
            return "No one"; 
        }
/*        boolean fire_piece = false; 
        boolean water_piece = false;;
        for (int i = 0; i < 8; i++ ) {
            for (int j = 0; j < 8; j++ ) {
                if (pieces[i][j].isFire()) {
                    fire_piece = true; 
                }
                else if (pieces[i][j].isFire() == false) {
                    water_piece = true; 
                }
            }
        }
*/  
//I simply cannot get the bomb tie situation to work                      
        if ((fire_pieces == 0) && (water_pieces == 0)) {
//        if ((fire_piece == false) && (water_piece == false)) {  
            return "No one"; 
        }
        if (water_pieces == 0) {
//        if (water_piece == false) {
            return "Fire"; 
        }
        if (fire_pieces == 0) {
//        if (fire_piece == false) {
            return "Water";
        }
        else {
            return null; 
        }     
    }

}


