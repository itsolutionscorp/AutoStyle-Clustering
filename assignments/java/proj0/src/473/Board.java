public class Board {

    private boolean setup;
    private static Piece[][] fighters;
    private static boolean[][] pieces;

    private Piece selected = null; // Piece that's been selected by current player.
    private Piece moved = null;    // Piece that was moved by current player.
    private Piece captured = null;
    private boolean player = true;  // Will be true if it's Fire player's turn.

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */



    public Board(boolean shouldbeEmpty) {
        setup = shouldbeEmpty;
        fighters = new Piece[8][8];
        pieces = new boolean[8][8];

        int N = 8;

        if (setup) {
            return;
        } else {
            for (int i = 0; i < N; i++) {          
                for (int j = 0; j < N; j++) {     
                    if ((i + j) % 2 == 0) {
                        if (j == 7) {
                            fighters[i][j] = new Piece(false, this, i, j, "pawn");
                            pieces[i][j] = true;
                        } else if (j == 6) {
                            fighters[i][j] = new Piece(false, this, i, j, "shield");
                            pieces[i][j] = true; 
                        } else if (j == 5) {
                            fighters[i][j] = new Piece(false, this, i, j, "bomb");
                            pieces[i][j] = true;                        
                        } else if (j == 2) {
                            fighters[i][j] = new Piece(true, this, i, j, "bomb");
                            pieces[i][j] = true;  
                        } else if (j == 1) {
                            fighters[i][j] = new Piece(true, this, i, j, "shield");
                            pieces[i][j] = true;                        
                        } else if (j == 0) {
                            fighters[i][j] = new Piece(true, this, i, j, "pawn");
                            pieces[i][j] = true; 
                        }
                    } 
                }
            }
        }
    }

    private static void drawBoard(int N) {
        // System.out.println("being called");
        for (int i = 0; i < N; i++) {         // Keeps incrementing i over time. 
            for (int j = 0; j < N; j++) {     // Keeps incrementing j over time.
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);  // Depending on even or odd, 
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);   // make the square red or gray.
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);  // Makes a filled square of 2r length w/ a center (x, y).
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);   
                // if ((i == 2) && (j == 2)) {
                //     System.out.println(pieces[i][j] + " " + fighters[i][j]);
                // }
                if (pieces[i][j]) {
                    if (!(fighters[i][j].isFire()) && !(fighters[i][j].isShield()) && !(fighters[i][j].isBomb())) {
                        if (fighters[i][j].isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            break;
                        }
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    } else if (!(fighters[i][j].isFire()) && (fighters[i][j].isShield())) {
                        if (fighters[i][j].isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            break;
                        } 
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);                       
                    } else if (!(fighters[i][j].isFire()) && (fighters[i][j].isBomb())) {
                        if (fighters[i][j].isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            break;
                        } 
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    } else if ((fighters[i][j].isFire()) && (fighters[i][j].isBomb())) {
                        if (fighters[i][j].isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            break;
                        } 
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    } else if ((fighters[i][j].isFire()) && (fighters[i][j].isShield())) {
                        if (fighters[i][j].isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            break;
                        } 
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    } else if ((fighters[i][j].isFire()) && !(fighters[i][j].isShield()) && !(fighters[i][j].isBomb())) {
                        if (fighters[i][j].isKing()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            break;
                        } 
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board battlefield = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            drawBoard(N);
            if (battlefield.winner() != null) {
                System.out.println("The victor is: " + battlefield.winner());
                break;
            }
            if (StdDrawPlus.mousePressed()) { 
                double x = StdDrawPlus.mouseX();  // Sets x-coordinate where the mouse is.
                double y = StdDrawPlus.mouseY();  // Sets y-coordinate where the mouse is. 
                int xplace = (int) x;
                int yplace = (int) y;
                // System.out.println("before select");
                // FINAL FIX:
                // if ((battlefield.captured == null) && (battlefield.moved == null)) {
                if (battlefield.canSelect(xplace, yplace)) {
                    battlefield.select(xplace, yplace);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(xplace + 0.5, yplace + 0.5, 0.5);
                    System.out.println("in select"); 
                }
                // }
                // System.out.println("after select");
            }
            // System.out.println("still running");
            if (StdDrawPlus.isSpacePressed()) {
                if (battlefield.canEndTurn()) {
                    battlefield.endTurn();
                }
            }            
            StdDrawPlus.show(100);
        }
    }


    public Piece pieceAt(int x, int y) {
        if ((x > 7) || (y > 7)) {
            return null;
        } else if (pieces[x][y]) {
            return fighters[x][y];
        } else {
            return null;
        }
    }

    public void place(Piece p, int x, int y) {
        int[] coordinates = findCoord(p);
        if (((p == null) || (x > 7)) || (y > 7)) {
            return;
        } else if (coordinates.length != 0) {
            remove(coordinates[0], coordinates[1]); 
            fighters[x][y] = p;
            pieces[x][y] = true;
            if ((p.isBomb()) && (Math.abs(x - coordinates[0]) == 2) && (Math.abs(y - coordinates[1]) == 2)) {
                fighters[x][y] = null;
                pieces[x][y] = false;
                int[] radius = new int[] {-1, 0, 1};
                for (int num : radius) {
                    for (int num2 : radius) {
                        int xpos = x + num;
                        int ypos = y + num2;
                        if ((!(xpos > 7) && !(ypos > 7)) && ((!(xpos < 0) && !(ypos < 0)))) {
                            if (pieces[xpos][ypos]) {
                                if (!(fighters[xpos][ypos].isShield())) {
                                    fighters[xpos][ypos] = null;
                                    pieces[xpos][ypos] = false;
                                }
                            }
                        }
                    }
                }
            }
        } else {              // TO DO: make more concise.
            fighters[x][y] = p;
            pieces[x][y] = true;
        }
    }

    private void gameState() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print("x: " + i + " y: " + j + " | ");
                System.out.print(fighters[i][j]);
                System.out.println(" " + pieces[i][j]);
                
            }
        }
    }

    public Piece remove(int x, int y) {
        if ((x > 7) || (y > 7)) {
            System.out.println("Selected square is out of bounds.");
            return null;
        } else if (!(pieces[x][y])) {
            System.out.println("No piece exists at this square.");
            return null;
        } else {
            Piece removed = fighters[x][y];
            fighters[x][y] = null;
            pieces[x][y] = false;
            // gameState();
            return removed;
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {     // TO DO: Make sure deltaX move works.
        System.out.println("calling Valid Move");
        int deltaX = xf - xi;
        int deltaY = yf - yi;
        int deletedX = xi + (deltaX / 2);
        int deletedY = yi + (deltaY / 2);
        if (fighters[xi][yi].isKing()) {
            if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1)) {
                return true;
            } else if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2) && (fighters[deletedX][deletedY] != null)) {
                return (fighters[xi][yi].isFire() != fighters[deletedX][deletedY].isFire());
            } else {
                return false;
            }
        } else {
            if (fighters[xi][yi].isFire()) {
                if ((yf - yi) <= 0) {           // Can make more concise later.
                    return false;
                } else if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1)) {
                    return true;
                } else if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2) && (fighters[deletedX][deletedY] != null)) {
                    // int deltaX = xf - xi;
                    // int deltaY = yf - yi;
                    // int deletedX = xi + (deltaX / 2);
                    // int deletedY = yi + (deltaY / 2);
                    System.out.println("About to error here");
                    return !(fighters[deletedX][deletedY].isFire());
                } else {
                    return false;
                }
            } else {
                if ((yf - yi) >= 0) {
                    return false;
                } else if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1)) {
                    return true;
                } else if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2) && (fighters[deletedX][deletedY] != null)) {
                    // int deltaX = xf - xi;
                    // int deltaY = yf - yi;
                    // int deletedX = xi + (deltaX / 2);
                    // int deletedY = yi + (deltaY / 2);
                    System.out.println("About to error there");
                    return (fighters[deletedX][deletedY].isFire());
                } else {
                    return false;
                }
            }
        }
    }

    private int[] findCoord(Piece p) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((fighters[i][j] != null) && (fighters[i][j] == p)) {
                    int[] result = new int[2];
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        int[] result = new int[0];
        return result;
    }


    public boolean canSelect(int x, int y) {    // If I'm randomly clicking and no movement, that counts?
        if (pieces[x][y]) {
            if (fighters[x][y].isFire() == player) {
                return ((selected == null) || (moved == null));
            } else {
                return false;                                    // TO DO
            }
        } else {
            int[] coordinates = findCoord(selected);
            if ((selected != null) && (moved == null)) {
                return (validMove(coordinates[0], coordinates[1], x, y));
            } else if ((selected != null) && (selected.hasCaptured()) && (coordinates.length != 0)) {
                System.out.println(selected);
                System.out.println(coordinates);
                int deltaX = x - coordinates[0];
                int deltaY = y - coordinates[1];
                if ((Math.abs(deltaX) == 2) && (Math.abs(deltaY) == 2) && (pieces[coordinates[0] + (deltaX / 2)][coordinates[1] + (deltaY / 2)])) {
                    System.out.println("It's working!!!");
                    return (validMove(coordinates[0], coordinates[1], x, y));
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public void select(int x, int y) {
        if (pieces[x][y]) {
            selected = fighters[x][y];
        } else {
            moved = selected;      // Update moved to the piece that was selected.
            
            int [] coordinates = findCoord(selected);
            int deltaX = x - coordinates[0];
            int deltaY = y - coordinates[1];
            if (Math.abs(deltaX) == 2) {
                int deletedX = coordinates[0] + (deltaX / 2);
                int deletedY = coordinates[1] + (deltaY / 2);
                captured = fighters[deletedX][deletedY];
            }
            System.out.println("before moving");
            selected.move(x, y);
        }
    }

    public boolean canEndTurn() {
        return ((moved != null) || (captured != null));
    }

    public void endTurn() {
        player = !(player);
        selected = null;
        moved = null;
        captured = null;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j]) {
                    fighters[i][j].doneCapturing();
                }
            }
        }
    }

    public String winner() {              // TO DO: Implement piece count variables instead.
        int firecount = 0;
        int watercount = 0;
        for (Piece[] array: fighters) {
            for (Piece item: array) {
                if (item != null) {
                    if (item.isFire()) {
                        firecount += 1;
                    } else if (!(item.isFire())) {
                        watercount += 1;
                    }
                }
            }
        }
        if ((firecount != 0) && (watercount != 0))  {
            return null;
        } else if ((firecount == 0) && (watercount == 0)) {
            return "No one";
        } else if (watercount == 0) {
            return "Fire";
        } else {
            return "Water";
        }
    }



} // Last bracket.
