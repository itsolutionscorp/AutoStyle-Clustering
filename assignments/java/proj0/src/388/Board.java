public class Board {
    //private boolean shouldBeEmpty;
    private int firePieces;
    private int waterPieces;
    private boolean fireTurn = true;
    private boolean select = false;
    private int selectX;
    private int selectY;
    private boolean turnMoved = false;
    private Piece selectedPiece;
    private Piece[][] pieces = new Piece[8][8];
    
    public Board(boolean shouldBeEmpty) {
            //this.shouldBeEmpty = shouldBeEmpty;
            this.selectedPiece = null;
            if (shouldBeEmpty == true) {
                this.firePieces = 0;
                this.waterPieces = 0;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        pieces[i][j] = null;
                    }
                }
            }
            else {
                this.firePieces = 12;
                this.waterPieces = 12;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if ((j == 0) && (i % 2 == 0)) {
                            pieces[i][j] = new Piece(true, this, i, j, "pawn");
                        }
                        else if ((j == 1) && (i % 2 == 1)) {
                            pieces[i][j] = new Piece(true, this, i, j, "shield");
                        }
                        else if ((j == 2) && (i % 2 == 0)) {
                            pieces[i][j] = new Piece(true, this, i, j, "bomb");
                        }
                        else if ((j == 5) && (i % 2 == 1)) {
                            pieces[i][j] = new Piece(false, this, i, j, "bomb");
                        }
                        else if ((j == 6) && (i % 2 == 0)) {
                            pieces[i][j] = new Piece(false, this, i, j, "shield");
                        }
                        else if ((j == 7) && (i % 2 == 1)) {
                            pieces[i][j] = new Piece(false, this, i, j, "pawn");
                        }
                        else {
                            pieces[i][j] = null;
                        }
                    }
                }
            }
        }

    public Piece pieceAt(int x, int y) {
        if ((x < 0) || (x > 7)) {
            return null;
        }
        if ((y < 0) || (y > 7)) {
            return null;
        }
        return pieces[x][y];
    }

    public boolean canSelect(int x, int y) {
        if (pieces[x][y] != null) { 
            if (((pieceAt(x, y).isFire() == true) && (fireTurn == true)) || ((pieceAt(x, y).isFire() == false) && (fireTurn == false))) {
                if ((select == false) || ((select == true) && (turnMoved == false))) {
                    return true;
                }
            }
        }
        if ((select == true) && (turnMoved == false)) {
            if (validMove(selectX, selectY, x, y) == true) {
                return true;
            }
        }
        if ((select == true) && (selectedPiece.hasCaptured() == true)) {
            if (validMove(selectX, selectY, x, y) == true) {
                return true;
            }
        }
        return false;
    }

    public void select(int x, int y) {
        if (pieces[x][y] != null) {
            selectedPiece = pieceAt(x, y);
            select = true;
            selectX = x;
            selectY = y;
        }
        else {
            select = true;
            turnMoved = true;
            selectedPiece.move(x, y);
            selectX = x;
            selectY = y;
        }
        
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (((xf >= 0) && (xf <= 7)) && ((yf >= 0) && (yf <= 7)) && ((xi >= 0) && (xi <= 7)) && ((yi >= 0) && (yi <= 7))) {
            if (pieceAt(xi, yi).hasCaptured() == false) {
                if ((pieceAt(xi, yi).isKing() == true) && (pieceAt(xi, yi).isFire() == true)) {
                    if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1) && (pieces[yf][xf] == null)) {
                        return true;
                    }
                    if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2) && (pieces[yf][xf] == null) && (pieces[((xi + xf) / 2)][((yi + yf) / 2)] != null) && (pieces[((xi + xf) / 2)][((yi + yf) / 2)].isFire() == false)) {
                        return true;
                    }
                    return false;
                }
                if ((pieceAt(xi, yi).isKing() == true) && (pieceAt(xi, yi).isFire() == false)) {
                    if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1) && (pieces[xf][yf] == null)) {
                        return true;
                    }
                    if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2) && (pieces[xf][yf] == null) && (pieces[((xi + xf) / 2)][((yi + yf) / 2)] != null) && (pieces[((xi + xf) / 2)][((yi + yf) / 2)].isFire() == true)) {
                        return true;
                    }
                    return false;
                }
                if (fireTurn == true) {
                    if ((Math.abs(xf - xi) == 1) && ((yf - yi) == 1) && (pieces[xf][yf] == null)) {
                        return true;
                    }
                    if ((Math.abs(xf - xi) == 2) && ((yf - yi) == 2) && (pieces[xf][yf] == null) && (pieces[((xi + xf) / 2)][((yi + yf) / 2)] != null) && (pieces[((xi + xf) / 2)][((yi + yf) / 2)].isFire() == false)) {
                        return true;
                    }
                }
                if (fireTurn == false) {
                    if ((Math.abs(xf - xi) == 1) && ((yf - yi) == -1) && (pieces[xf][yf] == null)) {
                        return true;
                    }
                    if ((Math.abs(xf - xi) == 2) && ((yf - yi) == -2) && (pieces[xf][yf] == null) && (pieces[((xi + xf) / 2)][((yi + yf) / 2)] != null) && (pieces[((xi + xf) / 2)][((yi + yf) / 2)].isFire() == true)) {
                        return true;
                    }
                }
                return false;  
            }
            else {
                if ((pieceAt(xi, yi).isKing() == true) && (pieceAt(xi, yi).isFire() == true)) {
                    if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2) && (pieces[xf][yf] == null) && (pieces[((xi + xf) / 2)][((yi + yf) / 2)] != null) && (pieces[((xi + xf) / 2)][((yi + yf) / 2)].isFire() == false)) {
                        return true;
                    }
                    return false;
                }
                if ((pieceAt(xi, yi).isKing() == true) && (pieceAt(xi, yi).isFire() == false)) {
                    if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2) && (pieces[xf][yf] == null) && (pieces[((xi + xf) / 2)][((yi + yf) / 2)] != null) && (pieces[((xi + xf) / 2)][((yi + yf) / 2)].isFire() == true)) {
                        return true;
                    }
                    return false;
                }
                if (fireTurn == true) {
                    if ((Math.abs(xf - xi) == 2) && ((yf - yi) == 2) && (pieces[xf][yf] == null) && (pieces[((xi + xf) / 2)][((yi + yf) / 2)] != null) && (pieces[((xi + xf) / 2)][((yi + yf) / 2)].isFire() == false)) {
                        return true;
                    }
                }
                if (fireTurn == false) {
                    if ((Math.abs(xf - xi) == 2) && ((yf - yi) == -2) && (pieces[xf][yf] == null) && (pieces[((xi + xf) / 2)][((yi + yf) / 2)] != null) && (pieces[((xi + xf) / 2)][((yi + yf) / 2)].isFire() == true)) {
                        return true;
                    }
                }  
                return false;
            }  
        }
        return false;
    }

    public void place(Piece p, int x, int y) {
        if ((x >= 0) && (x <= 7)) {
            if ((y >= 0) && (y <= 7)) {
                if (p.isFire() == true) {
                    firePieces = firePieces + 1;
                }
                else {
                    waterPieces = waterPieces + 1;
                }
                pieces[x][y] = p;
            }
        }
    }

    public Piece remove(int x, int y) {
        if ((x < 0) || (x > 7)) {
            System.out.println("This position is not on the board.");
            return null;
        }
        if ((y < 0) || (y > 7)) {
            System.out.println("This position is not on the board.");
            return null;
        }
        if (pieces[x][y] == null) {
            System.out.println("There is no piece at this position");
            return null;
        }  
        Piece removed = pieces[x][y];
        if (removed.isFire()) {
            firePieces = firePieces - 1;
        }
        else {
            waterPieces = waterPieces - 1;
        }
        pieces[x][y] = null;
        return removed;
    }

    public boolean canEndTurn() {
        if (selectedPiece != null) {
            if ((turnMoved == true) || (selectedPiece.hasCaptured() == true)) {
                return true;
            }
            return false;
        }
        return false;
    }

    public void endTurn() {
        if (fireTurn == true) {
            fireTurn = false;
        }
        else if (fireTurn == false) {
            fireTurn = true;
        }
        select = false;
        turnMoved = false;
        selectedPiece.doneCapturing();
        selectedPiece = null;
    }

    public String winner() {
        if ((firePieces == 0) && (waterPieces == 0)) {
            return "No one";
        }
        if (firePieces == 0) {
            return "Water";
        }
        if (waterPieces == 0) {
            return "Fire";
        }
        return null;
    }


    

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if ((i == selectX) && (j == selectY) && (selectedPiece != null)) {
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
            }
        }
        for (int i = 0; i < N; i++) { //GUI help from Min Tseng for starting board
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] != null) {
                    String img = "img/";
                    if (pieces[i][j].isBomb()) {
                        img += "bomb";  
                    }
                    else if (pieces[i][j].isShield()) {
                        img += "shield";  
                    }
                    else {
                        img += "pawn";  
                    }

                    if (pieces[i][j].side() == 0) {
                        img += "-fire";  
                    }
                    else {
                        img += "-water";
                    }
                    img += ".png";
                    StdDrawPlus.picture(i + .5, j + .5, img, 1, 1);
                } 
            }
        }
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board play = new Board(false);
        
        while(true) {
            play.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (play.canSelect((int) x, (int) y)) {
                    play.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (play.canEndTurn() == true) {
                    play.endTurn();
                }
            }            
            StdDrawPlus.show(100);
        }
    }
}
