public class Board {
    private static int N = 8;
    private Piece[][] pieces = new Piece[N][N];
    private boolean hasMoved = false;
    private boolean isFireTurn = true;
    private boolean hasSelected = false;
    private boolean hasCaptured = false;
    private boolean ifBombExplode = false;
    private int selectedLocX = 10;
    private int selectedLocY = 10;
    private boolean gameEnds = false;

    private void drawBoard(int N) {   
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // highlights selected piece
                if (hasSelected && i == selectedLocX && j == selectedLocY) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                // highlights canSelect piece
                if (canSelect(i, j)) {
                    StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }
    public static void main(String[] args) {    
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board a = new Board(false);
        while(!a.gameEnds) {
            a.drawBoard(N);
            a.redraw();
            StdDrawPlus.show(100); 
            if (StdDrawPlus.mousePressed()) {
                double x_double = StdDrawPlus.mouseX();
                double y_double = StdDrawPlus.mouseY();
                int x = (int) x_double;
                int y = (int) y_double;
                if (a.canSelect(x, y)) {
                    Piece temp = a.pieceAt(x, y);
                    a.select(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (a.canEndTurn()) {
                    a.endTurn();
                    // System.out.println("Is it fire's turn:" + a.isFireTurn);  To test who's turn
                }
            }          
        }
    }
    
    ///////////////////////  Own Code /////////////////////////////
    public Board(boolean shouldBeEmpty) {
        int N = 8;
        if (!shouldBeEmpty) {
            for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j == 0 && i%2 == 0) {
                    pieces[i][j] = new Piece(true, this, i, j, "pawn");
                }
                else if (j == 1 && i%2 == 1) {
                    pieces[i][j] = new Piece(true, this, i, j, "shield");
                }
                else if (j == 2 && i%2 == 0) {
                    pieces[i][j] = new Piece(true, this, i, j, "bomb");
                }
                else if (j == 5 && i%2 == 1) {
                    pieces[i][j] = new Piece(false, this, i, j, "bomb");
                }
                else if (j == 6 && i%2 == 0) {
                    pieces[i][j] = new Piece(false, this, i, j, "shield");
                }
                else if (j == 7 && i%2 == 1) {
                    pieces[i][j] = new Piece(false, this, i, j, "pawn");
                }
            }
            }
        }
    }
    private void redraw() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] == null) {
                }
                else {
                    if (pieces[i][j].isFire() == true) {
                        if (pieces[i][j].isKing() == true) {
                            if (pieces[i][j].isBomb() == true) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else if (pieces[i][j].isShield() == true) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                        }
                        else {
                            if (pieces[i][j].isBomb() == true) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                            else if (pieces[i][j].isShield() == true) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                            else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                    }
                    else {
                        if (pieces[i][j].isKing() == true) {
                            if (pieces[i][j].isBomb() == true) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                            else if (pieces[i][j].isShield() == true) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                            else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            }
                        }
                        else {
                            if (pieces[i][j].isBomb() == true) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                            else if (pieces[i][j].isShield() == true) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                            else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            }
                        }
                    }
                } // if not null
            } // for loop
        }
    }
    
    public Piece pieceAt(int x, int y) {
        if (x >= N || x < 0 || y >= N || y < 0) {  // out of bounds
            return null;
        }
        if (pieces[x][y] == null) {
            return null;
        }
        return pieces[x][y];
    }

    public void place(Piece p, int x, int y) {
        if (x >= N || x < 0 || y >= N || y < 0) {  // out of bounds
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {  // find piece to remove
                if (pieces[i][j] == p) {
                    remove(i, j);
                }
            }
        }
        pieces[x][y] = p;
    }

    public Piece remove(int x, int y) {
        if (x >= N || x < 0 || y >= N || y < 0) {
            System.out.println("this location is out of bounds");
            return null;
        }
        else if (pieces[x][y] == null) {
            System.out.println("no piece at this location");
            return null;
        }
        Piece toReturn = pieces[x][y];
        pieces[x][y] = null;
        return toReturn;
    }

    public boolean canSelect(int x, int y) {
        if (hasMoved && !hasCaptured) {
            return false;
        }
        else if (hasMoved && hasCaptured) {
            // false if bomb hasCaptured and exploded
            if (ifBombExplode) {
                ifBombExplode = false;
                return false;
            }

            else if (pieceAt(selectedLocX, selectedLocY) == null) { 
                return false;
            }

            // your turn, selected piece of your color, must move a difference of (2, 2) to ensure that
            // this move is a capture
            else if ((isFireTurn && pieceAt(selectedLocX, selectedLocY).isFire()) || 
                (!isFireTurn && !pieceAt(selectedLocX, selectedLocY).isFire())) {
                    if (Math.abs(selectedLocY - y) == 2 && Math.abs(selectedLocX - x) == 2) {
                        return validMove(selectedLocX, selectedLocY, x, y); 
                    }
                return false;
            }
            return false;
        }

        else if (!hasSelected) {
            if (pieceAt(x, y) == null) {
                return false;
            }
            else if (pieceAt(x, y) != null) {
                if ((isFireTurn && pieceAt(x, y).isFire()) || (!isFireTurn && !pieceAt(x, y).isFire())) {
                    return true;  // above line checks if fireTurn and type of checker match
                }
            return false;
            }
        }

        else if (hasSelected && !hasMoved) {
            if (selectedLocY == y && selectedLocX == x) {
                return true; //can select if same piece
            }

            // see if you choose piece of same color
            else if ((isFireTurn && pieceAt(selectedLocX, selectedLocY).isFire()) ||   
                (!isFireTurn && !pieceAt(selectedLocX, selectedLocY).isFire())) {

                // can choose piece of same team
                if (pieceAt(x, y) != pieceAt(selectedLocX, selectedLocY) && pieceAt(x, y) != null)
                {    
                    if (pieceAt(selectedLocX, selectedLocY).isFire() == pieceAt(x, y).isFire()) {
                        return true;
                    }
                }
                else {
                    return validMove(selectedLocX, selectedLocY, x, y); 
                }
            }

            else if (pieceAt(x, y) == null && Math.abs(selectedLocY - y) > 2 && Math.abs(selectedLocX - x) > 2) {
                return true;   // if already selected, you can select any blank space
            }
            return false;
        }
        return false;
    }

    public void select(int x, int y) {
        if (pieceAt(x, y) != null) {
            hasSelected = true;  // update selection boolean and location
            selectedLocX = x;
            selectedLocY = y;
        }
        else {
            if (x >= N || x < 0 || y >= N || y < 0) {
                System.out.println("select(x, y) error; has not selected");
                return;
            }
            else if (hasSelected) {
                if (Math.abs(selectedLocY - y) == 2 && Math.abs(selectedLocX - x) == 2) {
                    hasCaptured = true;
                    // difference of (2, 2) ensures this is a capture
                    if (pieceAt(selectedLocX, selectedLocY).isBomb()) {
                        ifBombExplode = true;
                    }
                }
                pieceAt(selectedLocX, selectedLocY).move(x, y);
                hasMoved = true;
                hasSelected = true;
                selectedLocX = x;
                selectedLocY = y;
            }

            else {
            selectedLocX = x;
            selectedLocY = y;    
            hasSelected = true;
            }

        }
    }
    
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if ((xi + yi + xf + yf) % 2 != 0) { // all piece must satisfy x + y = 2n
            return false;
        }
        if ((Math.abs(xi-xf) == 2 && Math.abs(yi-yf) != 2) || (Math.abs(yi-yf) == 2 && Math.abs(xi-xf) != 2)) {
            return false;  // if difference in x = 2, then difference in y must also = 2
        }
        if (Math.abs(xi-xf) > 2 || Math.abs(yi-yf) > 2) {
            return false; // impossible if difference > 2
        }
        if (xi < 0 || xi >= N || yi < 0 || yi >= N || xf < 0 || xf >= N || yf < 0 || yf >= N) {
            System.out.println("validMove out of bounds error");
            return false;
        }
        if (pieces[xi][yi].isFire() && !pieces[xi][yi].isKing()) {
            if (yf < yi) {  // fire only move up
                return false;
            }
        }
        if (!pieces[xi][yi].isFire() && !pieces[xi][yi].isKing()) {
            if (yf > yi) { // water only move down
                return false;
            }
        }
        int x_avg = (xi + xf) / 2;
        int y_avg = (yi + yf) / 2;
        if (Math.abs(xi - xf) == 2 && Math.abs(yf - yi) == 2) {         
            if (pieces[xf][yf] != null) {    // if there is a piece at selected location, then can't move
                return false;
            }

            else if (pieces[x_avg][y_avg] == null) {
                return false;  // nothing to jump over
            }

            else if (pieces[xi][yi].isFire() != pieces[x_avg][y_avg].isFire()) {
                return true;  // has piece, different piece color, therefore can select
            }
            else {
                return false;
            }
        }
        else if (pieces[xf][yf] != null) {
            return false; // if a piece is already there, can't move
        }
        return true;
    }

    public boolean canEndTurn() {
        return hasMoved || hasCaptured;
    }

    public void endTurn() {
        isFireTurn = !isFireTurn; // switch turn
        hasCaptured = false;
        hasMoved = false;
        hasSelected = false;

        if (pieceAt(selectedLocX, selectedLocY) != null) {
            pieceAt(selectedLocX, selectedLocY).doneCapturing(); 
            }
    }

    public String winner() {
        int fireCount = 0;
        int waterCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] != null) {
                    if  (pieces[i][j].isFire()) {
                        fireCount++;
                    }
                    else {
                        waterCount++;
                    }
                }
            }
        }
        if (fireCount == waterCount && fireCount == 0) {
            gameEnds = true;
            return "No one";
        }
        else if (waterCount == 0 && fireCount != 0) {
            gameEnds = true;
            return "Fire";
        }
        else if (fireCount == 0 && waterCount != 0) {
            gameEnds = true;
            return "Water";
        }
        else {
            return null;
        }
        
    }

}
    