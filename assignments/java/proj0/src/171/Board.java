public class Board {

    // all boards are 8 x 8
    private static final int N = 8;

    // keeps track of the pieces
    private Piece[][] pieces;

    // keep track of game events
    private boolean fireTurn;
    private Point selected;
    private boolean hasMoved;


    /**
     *  Constructor for a new board
     *  We can make either an empty board
     *  or one with the default setup
     **/

    public Board(boolean shouldBeEmpty) {
        fireTurn = true;
        hasMoved = false;
        if (shouldBeEmpty) {
            makeEmptyBoard();
        } else {
            makeDefaultBoard();
        }
    }


    /**
     *  Returns the piece at a specific coordinate
     **/

    public Piece pieceAt(int x, int y) {
        if (isOnBoard(x, y)) return pieces[x][y];
        return null;
    }


    /**
     *  Returns true if the current player can select the
     *  square at the given coordinate
     **/

    public boolean canSelect(int x, int y) {
        Piece p = pieceAt(x, y);

        // case for empty square
        if (p == null) {
            if (isValidMove(x, y)) return true;
            return false;

        } else {

            // cannot select the other team's pieces
            if (fireTurn != p.isFire()) return false;

            if (selected == null || (selected != null && !hasMoved)) {
                return true;
            }
            return false;
        }
    }


    /**
     *  Selects the square at the location
     **/

    public void select(int x, int y) {
        // handle moving a piece
        if (pieceAt(x, y) == null) {
            pieceAt(selected.x, selected.y).move(x, y);
            hasMoved = true;
            if (pieceAt(selected.x, selected.y) != null) {
                place(remove(selected.x, selected.y), x, y);
            } else {
                selected = null;
                return;
            }
        }
        selected = new Point(x, y);
    }


    /**
     *  Places a piece at the location
     **/

    public void place(Piece p, int x, int y) {

        //  return if the piece cannot be placed
        if (p == null || !isOnBoard(x, y)) return;

        pieces[x][y] = p;
    }


    /**
     *  Removes and returns the piece at a specific location
     **/

    public Piece remove(int x, int y) {

        //  make sure the coordinates are within the bounds of the board
        if (!isOnBoard(x, y)) {
            System.out.printf("Location (%d,%d) is out of range on this board with dimensions(%d,%d)%n", x, y, N, N);
            return null;
        }

        //  check if there is a piece at this location
        if (pieces[x][y] == null) {
            System.out.printf("There is no piece at location (%d,%d)%n", x, y);
            return null;
        }

        //  remove and return the piece
        Piece p = pieces[x][y];
        pieces[x][y] = null;
        return p;

    }


    /**
     *  True if the current player can end their turn
     **/

    public boolean canEndTurn() {
        if (hasMoved) return true;
        return false;
    }


    /**
     *  Ends the current player's turn
     **/

    public void endTurn() {
        if (selected != null) {
            pieceAt(selected.x, selected.y).doneCapturing();
        }
        selected = null;
        hasMoved = false;
        fireTurn = !fireTurn;
    }


    /**
     *  Returns a string representing the winner of the game
     **/

    public String winner() {
        int numFire = 0;
        int numWater = 0;

        // count the number of fire and water pieces
        for (Piece[] piece : pieces) {
            for (Piece p : piece) {
                if (p != null) {
                    if (p.isFire()) numFire++;
                    else numWater++;
                }
            }
        }

        // return the winner
        if (numFire == 0) {
            if (numWater == 0) return "No one";
            return "Water";
        } else if (numWater == 0) {
            return "Fire";
        }

        // default (game not over yet or no valid moves)
        return null;
    }


    /**
     *  Returns true if the point is within the range of the board
     **/

    private boolean isOnBoard(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }


    /**
     *  Returns true if the location is a valid move for
     *  the selected piece
     **/

    private boolean isValidMove(int x, int y) {
        Piece current = null;

        // current selected piece
        if (selected != null)
            current = pieceAt(selected.x, selected.y);

        // cannot move without selecting a piece first
        // cannot move twice
        if (current == null || (hasMoved && !pieceAt(selected.x, selected.y).hasCaptured())) return false;

        Point[] valid = getValidPoints();
        for (Point p : valid) {
            if (p != null && isOnBoard(p.x, p.y) && pieceAt(p.x, p.y) == null && p.x==x && p.y==y) {
                return true;
            }
        }

        return false;
    }


    /**
     *  Returns an array of points representing valid moves
     *  not including jumps (capturing other pieces)
     **/

    private Point[] getValidPoints() {
        Piece current = pieceAt(selected.x, selected.y);

        // pieces have at most 8 valid locations to move to (including jumps)
        int numPossibleMoves = 8;

        // fire pieces move up, water pieces move down
        int dir = -1;
        if (fireTurn) dir = 1;

        Point[] validLocs = new Point[numPossibleMoves];
        Point p0 = new Point(selected.x-1, selected.y+dir);
        Point p1 = new Point(selected.x+1, selected.y+dir);

        if (!current.hasCaptured()) {
            validLocs[0] = p0;
            validLocs[1] = p1;
        }
        if (canJump(p0)) validLocs[2] = new Point(selected.x-2, selected.y+2*dir);
        if (canJump(p1)) validLocs[3] = new Point(selected.x+2, selected.y+2*dir);

        // kings can move backwards
        if (current != null && current.isKing()) {
            Point p4 = new Point(selected.x-1, selected.y-dir);
            Point p5 = new Point(selected.x+1, selected.y-dir);
            if (!current.hasCaptured()) {
                validLocs[4] = p4;
                validLocs[5] = p5;
            }
            if (canJump(p4)) validLocs[6] = new Point(selected.x-2, selected.y-2*dir);
            if (canJump(p5)) validLocs[7] = new Point(selected.x+2, selected.y-2*dir);
        }

        return validLocs;
    }


    /**
     *  Returns true if we can jump over a given point
     **/

    private boolean canJump(Point p) {
        return pieceAt(p.x, p.y) != null && pieceAt(p.x, p.y).isFire() != fireTurn;
    }


    /**
     *  Makes an empty board with no pieces
     **/

    private void makeEmptyBoard() {
        pieces = new Piece[N][N];
    }


    /**
     *  Makes a board with the defalt configuration
     **/

    private void makeDefaultBoard() {
        pieces = new Piece[N][N];

        // fire pieces
        for (int i = 0; i < N/2; i++) pieces[2*i][0] = new Piece(true, this, 2*i, 0, "pawn");
        for (int i = 0; i < N/2; i++) pieces[2*i+1][1] = new Piece(true, this, 2*i+1, 1, "shield");
        for (int i = 0; i < N/2; i++) pieces[2*i][2] = new Piece(true, this, 2*i, 2, "bomb");

        //  water pieces
        for (int i = 0; i < N/2; i++) pieces[2*i+1][N-1] = new Piece(false, this, 2*i+1, N-1, "pawn");
        for (int i = 0; i < N/2; i++) pieces[2*i][N-2] = new Piece(false, this, 2*i, N-2, "shield");
        for (int i = 0; i < N/2; i++) pieces[2*i+1][N-3] = new Piece(false, this, 2*i+1, N-3, "bomb");
    }


    /**
     *  Continually raws the current 8 x 8 board with pieces onto the screen
     *  and allows for user interaction
     **/

    private void drawBoard() {
        while(true) {
            StdDrawPlus.setXscale(0, N);
            StdDrawPlus.setYscale(0, N);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (selected != null && selected.x == i && selected.y == j) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    drawPiece(i, j);
                    handleMouseEvents();
                    if (StdDrawPlus.isSpacePressed() && canEndTurn()) endTurn();
                }
            }
            StdDrawPlus.show(100);      
        }
    }


    /**
     *  Draws the pieces onto the board
     **/

    private void drawPiece(int i, int j) {
        if (pieceAt(i, j) != null) {
            StdDrawPlus.picture(i + .5, j + .5, getFile(i,j), 1, 1);
        }
    }


    /**
     *  Handles user mouse events
     **/

    private void handleMouseEvents() {
        if (StdDrawPlus.mousePressed()) {
            int x = (int)StdDrawPlus.mouseX();
            int y = (int)StdDrawPlus.mouseY();
            if (canSelect(x, y)) {
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                select(x, y);
            }
        } 
    }


    /**
     *  Gets the filename of the image corresponding
     *  to the piece at location (x,y)
     **/

    private String getFile(int x, int y) {
        Piece p = pieces[x][y];
        String f = "";
        if (p.isFire()) {
            if (p.isBomb()) f = "img/bomb-fire";
            else if (p.isShield()) f = "img/shield-fire";
            else f = "img/pawn-fire";
        } else {
            if (p.isBomb()) f = "img/bomb-water";
            else if (p.isShield()) f = "img/shield-water";
            else f = "img/pawn-water";
        }
        // kinged pieces get crowned
        if (p.isKing()) f += "-crowned";
        return f + ".png";
    }


    /**
     *  A point represents a specific location on the grid
     **/

    private class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    /**
     *  Creates the board and plays
     *  the checkers game
     **/

    public static void main(String[] args) {
        Board b = new Board(false);
        b.drawBoard();
    }
}