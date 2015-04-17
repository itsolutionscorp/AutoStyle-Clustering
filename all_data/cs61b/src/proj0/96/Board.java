public class Board {
    
    private Piece[][] pieces = new Piece[8][8];
    private int turnTracker = 0;  //change endturn
    private boolean moved = false; //changed in select
    private Piece selectedP = null; //changed in select, contains location of piece
    private Piece removedP;
    
    private void drawBoard(int N) {
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    if ((selectedP != null) && (selectedP == pieces[i][j])) {
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    }
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null && !pieces[i][j].isKing()) {
                    StdDrawPlus.picture(i + .5, j + .5, "/img/" + this.helperType(pieces[i][j]) + "-" + this.helperFireOrWater(pieces[i][j]) + ".png", 1, 1);
                }
                if ((pieces[i][j] != null) && pieces[i][j].isKing()) {
                    StdDrawPlus.picture(i + .5, j + .5, "/img/" + this.helperType(pieces[i][j]) + "-" + this.helperFireOrWater(pieces[i][j]) + "-crowned.png", 1, 1);
                }
            }
        }
    }
        
    private String helperType(Piece p) {
        if (p.isBomb()) {
            return "bomb";
        }
        if (p.isShield()) {
            return "shield";
        }
        else {
            return "pawn";
        }
    }

    private String helperFireOrWater(Piece p) {
        if (p.side() == 0) {
            return "fire";
        } else {
            return "water";
        }
    }

    private int helperFindX(Piece p) {
        int val = 0;
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                if (p == pieces[i][j]) {
                    val = i;
                }
            }
        }
        return val;
    }

    private int helperFindY(Piece p) {
        int val = 0;
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                if (p == pieces[i][j]) {
                    val = j;
                }
            }
        }
        return val;
    }    

    public Piece pieceAt(int x, int y) {
        if ((x > 8) || (x < 0) || (y > 8) || (y < 0)) {
            return null;
        } 
        return pieces[x][y];
    }


    public boolean canSelect(int x, int y) {
        if ((pieces[x][y]) == null) {
            if ((x + y) % 2 == 0) {
                if (selectedP != null) {
                    if (turnTracker == selectedP.side()) {
                        if (pieces[x][y] == null) {
                            if ((selectedP != null) && (moved == false) 
                                && (validMove(helperFindX(selectedP), helperFindY(selectedP), x, y)) == true) {
                                return true;
                            }
                            if ((selectedP != null) && (selectedP.hasCaptured() == true) 
                                && (validMove(helperFindX(selectedP), helperFindY(selectedP), x, y)) == true) {
                                return true;
                            }
                        }
                    } return false;
                }
            }
        }
        else {
            if (turnTracker == pieces[x][y].side()) {
                if ((selectedP == null) || ((selectedP != null) && (moved == false))) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public void select(int x, int y) { //x, y final dest
        if ((selectedP != null) && (pieces[x][y] == null)) {
            selectedP.move(x, y);
            moved = true;
            System.out.println("check");
        }
        else {
            selectedP = pieces[x][y];
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if ((xf > 8) || (xf < 0) || (yf > 8) || (yf > 8)) {
            return false;
        }
        if ((pieces[xi][yi] != null) && (pieces[xi][yi].isKing() == true)) {
            if ((yf - yi == 1) || (yi - yf == 1)) {
                if ((pieces[xf][yf] == null) && ((xf == xi + 1) || (xf == xi - 1))) {
                    return true;
                }
                return false;
            }
            if ((yf - yi == 2) || (yi - yf == 2)) {
                int middKingPieceX = (xi + xf)/2;
                int middKingPieceY = (yi + yf)/2;
                if (pieces[middKingPieceX][middKingPieceY].side() != pieces[xi][yi].side()) {
                    if ((pieces[xf][yf] == null) && ((xf == xi + 2) || (xf == xi - 2))) {
                        return true;
                    }
                    return false;
                }
            }
        }

        if ((pieces[xi][yi] != null) && (pieces[xi][yi].side() == 0)) { //checks fire piece
            if (yf - yi == 1) { //checks that there is an immediate space above to check
                if ((pieces[xi][yi] != null) && (pieces[xf][yf] == null) && ((xf == xi + 1) || (xf == xi - 1))) { //checks that the final position is null, and thus valid
                    return true; //if it is null, the piece can move
                }
                return false; //if it isn't null, the piece cannot move there
            }
            if (yf - yi == 2) { //checks that there is a space two places above to check
                int middlePieceX = xi + (xf - xi)/2; //finds the xcoordinate of the space between both places
                int middlePieceY = yi + (yf - yi)/2; // ditto but ycoord
                if ((pieces[middlePieceX][middlePieceY] != null) && (pieces[middlePieceX][middlePieceY].side() == 1)) { //checks that the middle piece is a water piece
                    if ((pieces[xf][yf] == null) && ((xf == xi + 2) || (xf == xi - 2))) { //checks that the final position is null, and thus valid
                        return true; //piece can move
                    }
                    return false;
                }
            }
        }   
        if ((pieces[xi][yi] != null) && (pieces[xi][yi].side() == 1)) {
            if (yi - yf == 1) {
                if ((pieces[xi][yi] != null) && (pieces[xf][yf] == null) && ((xf == xi + 1) || (xf == xi - 1))) { //checks that the final position is null, and thus valid
                    return true; //if it is null, the piece can move
                }
                return false; //if it isn't null, the piece cannot move there
            }
            if (yi - yf == 2) { //checks that there is a space two places above to check
                int middPieceX = xi - (xi - xf)/2; //finds the xcoordinate of the space between both places
                int middPieceY = yi - (yi - yf)/2; // ditto but ycoord
                if ((pieces[middPieceX][middPieceY] != null) && (pieces[middPieceX][middPieceY].side() == 0)) { //checks that the middle piece is a fire piece
                    if ((pieces[xf][yf] == null) && ((xf == xi + 2) || (xf == xi - 2))) { //checks that the final position is null, and thus valid
                        return true; //piece can move
                    }
                    return false;
                }
            }
        }
        return false;
    }

    public void place(Piece p, int x, int y) {
        if ((p == null) || (x > 8) || (x < 0) || (y > 8) || (y < 0)) {
            pieces[x][y] = null; 
        }
        pieces[x][y] = p;
    }

    public Piece remove(int x, int y) {
        if ((x > 8) || (x < 0) || (y > 8) || (y < 0)) {
            System.out.println("Piece is out of bounds");
            return null;
        }
        if (pieces[x][y] == null) {
            System.out.println("No piece found");
            return null;
        }
        removedP = pieces[x][y];
        pieces[x][y] = null;
        return removedP;
    }

    public boolean canEndTurn() {
        if (selectedP != null) {
                   System.out.println("inside here");
            if ((moved == true) || (selectedP.hasCaptured() == true)) {
                return true;
            }
            else {
                return false;
            }
        }

        return false;
    }

    public void endTurn() {
        System.out.println("ending turn");
        if (selectedP != null) {
            selectedP.doneCapturing();
        }
        moved = false;
        selectedP = null;
        if ((turnTracker == 0)) {
            turnTracker = 1;
        } else {
            turnTracker = 0; 
        }  
    }

    public String winner() {
        if (tallyFire() == 0 && tallyWater() == 0) {
            return "No one";
        } 
        else if (tallyFire() != 0 && tallyWater() == 0) {
            return "Fire";
        }
        else if (tallyWater() != 0 && tallyFire() == 0) {
            return "Water";
        }
        return null;
    }

    private int tallyFire() {
        int countFire = 0;
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                if ((pieces[i][j] != null) && (pieces[i][j].isFire() == true)) {
                    countFire += 1;
                }
            }
        }
        return countFire; 
    }

    private int tallyWater() {
        int countWater = 0;
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 8; j += 1) {
                if ((pieces[i][j] != null) && (pieces[i][j].isFire() == false) ) {
                    countWater += 1;
                }
            }
        }
        return countWater; 
    }
    
    public Board(boolean shouldBeEmpty) {
        int N = 8;
        if (shouldBeEmpty == true) {
           return; 
        }
        else {
            for (int i = 0; i < 8; i += 2) {
                pieces[i][0] = new Piece(true, this, i, 0, "pawn");
            }
            for (int i = 1; i < 8; i += 2) {
                pieces[i][1] = new Piece(true, this, i, 1, "shield");
            }
            for (int i = 0; i < 8; i += 2) {
                pieces[i][2] = new Piece(true, this, i, 2, "bomb");
            }
            for (int i = 1; i < 8; i += 2) {
                pieces[i][5] = new Piece(false, this, i, 5, "bomb");
            }
            for (int i = 0; i < 8; i += 2) {
                pieces[i][6] = new Piece(false, this, i, 6, "shield");
            }
            for (int i = 1; i < 8; i += 2) {
                pieces[i][7] = new Piece(false, this, i, 7, "pawn");
            }
        }
    }



    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        
        Board b = new Board(Boolean.parseBoolean(args[0]));
        
        while(true) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    b.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed() == true) {
                if (b.canEndTurn() == true) {
                  b.endTurn();
                }
            }           
            StdDrawPlus.show(100);

            b.winner();
        }
    }
}