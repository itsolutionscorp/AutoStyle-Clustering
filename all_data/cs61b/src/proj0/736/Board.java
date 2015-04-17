public class Board {


    /** Location of pieces. */
    private int piecesWater;
    private  int piecesFire;
    private  Piece[][] pieces;
    private  boolean shouldBeEmpty;
    private  int player;
    private  boolean hasSelected;
    private  boolean captured;
    private  boolean hasMoved;
    private  Piece selected;
    private  int xPositionSelected;
    private  int yPositionSelected;
    private boolean wasBomb;

    //Returns the integer that will dictate whose turn it is to move
    private void switchTurn() {
        hasSelected = false;
        hasMoved = false;
        captured = false;
        wasBomb = false;
        if (player == 0) {
            player = 1;
        }
        else {
            player = 0;
        }
    }

    public Board(boolean empty) {
        player = 0;
        pieces = new Piece[8][8];
        xPositionSelected = -1;
        yPositionSelected =-1;
        this.shouldBeEmpty = empty;
        piecesFire = 0;
        piecesWater = 0;
        this.player = 0;
        if (shouldBeEmpty == false) {
            storePiecePosition(pieces, this);
            piecesFire = 12;
            piecesWater = 12;
        }

    }


    //Returns a string corresponding to the file path of thefor a given piece
    private static String getImage(Piece p) {
            if (p.isFire() == true) {
                if (p.isShield() == true) {
                    if (p.isKing() == true) {
                        return "img/shield-fire-crowned.png";
                    }
                    else {
                        return "img/shield-fire.png";
                    }
                }

                if (p.isBomb() == true) {
                    if (p.isKing() == true) {
                        return "img/bomb-fire-crowned.png";
                    }
                    else {
                        return "img/bomb-fire.png";
                    }
                }

                else {
                    if (p.isKing() == true) {
                        return "img/pawn-fire-crowned.png";
                    }
                    else {
                        return "img/pawn-fire.png";
                    }
                }
            }

            if (p.isFire() == false) {
                if (p.isShield() == true) {
                    if (p.isKing() == true) {
                        return "img/shield-water-crowned.png";
                    }
                    else {
                        return "img/shield-water.png";
                    }
                }

                if (p.isBomb() == true) {
                    if (p.isKing() == true) {
                        return "img/bomb-water-crowned.png";
                    }
                    else {
                        return "img/bomb-water.png";
                    }
                }

                else {
                    if (p.isKing() == true) {
                        return "img/pawn-water-crowned.png";
                    }
                    else {
                        return "img/pawn-water.png";
                    }
                }
            }
            return "lol";
        }


    //Stores the coordinates for the pieces
    private void storePiecePosition(Piece[][] given, Board b) {

        for (int N = 0; N < 8; N = N+2){
            pieces[N][0]= new Piece (true, b, N, 0, "pawn");
            pieces[N][2]= new Piece (true, b, N, 2, "bomb");
            pieces[N][6]= new Piece (false, b, N, 6, "shield");
        }
        for (int N = 1; N < 8; N = N+2) {
            pieces[N][1] = new Piece (true, b, N, 1, "shield");
            pieces[N][5] = new Piece (false, b, N, 5, "bomb");
            pieces[N][7] = new Piece (false, b, N, 7, "pawn");
        }

    }

    //Draws an empty N x N board
    private void drawBoard(int N) {

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if ((i + j) % 2 == 0) {
                        StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    }
                    if ((i == xPositionSelected) && (j == yPositionSelected)) {
                        if (wasBomb == false) {
                          StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                        }
                    }
                    if ((i + j) % 2 != 0) {
                        StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    }
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    if (shouldBeEmpty == false) {
                        if (pieces[i][j] != null) {
                            StdDrawPlus.picture(i + .5, j + .5, this.getImage(pieces[i][j]), 1, 1);
                        }
                    }
                }
            }
    }



    //Gets the piece at the position (x, y) or null if there is no piece
    public Piece pieceAt(int x_pos, int y_pos) {
        if (x_pos < 0 || x_pos > 7 || y_pos < 0 || y_pos > 7) {
            return null;
        }
        if (pieces[x_pos][y_pos] == null) {
            return null;
        }
        else {
            Piece placement = pieces[x_pos][y_pos];
            return placement;
        }
    }


    //Places piece at point (x, y)
    public void place(Piece p, int x_pos, int y_pos) {
        if ((x_pos >= 0 && x_pos < 8) && (y_pos >= 0 && y_pos < 8)) {
            pieces[x_pos][y_pos] = p;
            if (hasMoved == false) {
                if (p.isFire() == true) {
                    piecesFire += 1;
                }
                else {
                    piecesWater += 1;
                }
            }
        }
    }

    //Removes piece from specified location (x, y)
    public Piece remove(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.print("Input coordinates are not located on the board");
            return null;
        }
        if (pieces[x][y] == null) {
            System.out.print("There is no piece at the specified location.");
            return null;
        }
        else {
            Piece toRemove = pieces[x][y];
            pieces[x][y] = null;
            return toRemove;
        }
    }
    //Returns whether the move is valid for a Fire piece
    private boolean fireValid(int xi, int yi, int xf, int yf) {
            if ((yi == yf - 1) && (1 == Math.abs(xf - xi)) && (pieceAt(xf, yf) == null)) {
                if (captured == false) {
                    return true;
                }
            }
            if ((yi == yf - 2) && (xi == (xf - 2)) && (pieceAt(xf - 1, yf -1)!= null)) {
                if (pieceAt(xf -1, yf -1).side() != pieceAt(xi, yi).side()) {
                    return true;
                }
            }
            if ((yi == yf -2 ) && (xi == (xf + 2)) && (pieceAt(xf +1, yf -1)!=null)) {
                if (pieceAt(xf +1, yf -1).side() != pieceAt(xi, yi).side()) {
                    return true;
                }
            }
        return false;
    }


    //Returns whether the move is valid for a Water piece
    private boolean waterValid (int xi, int yi, int xf, int yf) {
            if ((yf == yi - 1) && (1 == Math.abs(xf - xi)) && (pieceAt(xf, yf) == null)) {
                if (captured == false) {
                    return true;
                }
            }
            if ((yf == yi - 2) && (xi == (xf - 2)) && (pieceAt(xf -1, yf +1)!=null)) {
                if (pieceAt(xf -1, yf +1).side() != pieceAt(xi, yi).side()) {
                    return true;
                }
            }
            if ((yf == yi -2 ) && (xi == (xf + 2)) && (pieceAt(xf +1, yf +1) != null)) {
                if (pieceAt(xf +1, yf +1).side() != pieceAt(xi, yi).side()) {
                    return true;
                }
            }
        return false;
    }

    //Returns true if piece can move to a certain place strictly from a geomstric point of view
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (pieceAt(xi, yi).isKing() == true) {
            if ((fireValid(xi, yi, xf, yf) == true) || (waterValid(xi, yi, xf, yf) == true)) {
                return true;
            }
        }
        if (pieceAt(xi, yi).isFire() == true) {
            if (fireValid(xi, yi, xf, yf) == true) {
                return true;
            } 
        }
        if (pieceAt(xi, yi).isFire() == false) {
            if (waterValid(xi, yi, xf, yf) == true) {
                return true;
            }
        }

        return false;

    }

    //Returns true if there is a piece at the specified location and it can be selected
    public boolean canSelect(int x, int y) {
        if (wasBomb == true) {
            return false;
        }
        if (pieceAt(x,y) != null) {
            selected = pieceAt(x,y);
            if (selected.side() == player) {
                if ((hasSelected == false) || (hasMoved == false)) {
                    return true;
                }
            }
        }
        if (pieceAt(x,y) == null) {
            if ((hasSelected == true) && (hasMoved == false) && (validMove(xPositionSelected, yPositionSelected, x, y))) {
                return true;
            }
            if ((hasSelected == true) && (captured == true)  && (validMove(xPositionSelected,yPositionSelected, x, y))) {
                return true;
            }

        }
        return false;
    }

    //Handles captures if they are required in the given move
    private void capturePieces(int xPositionSelected, int yPositionSelected, int x, int y) {
        //if (player == 0) {
            if ((xPositionSelected == x - 2) && (yPositionSelected == y - 2) && (pieceAt(x-1, y-1)!=null)) {
                if (pieceAt(x-1, y-1).side() != pieceAt(x,y).side()) {
                    remove(x - 1, y - 1);
                    piecesWater = piecesWater - 1;
                    captured = true;
                    if (selected.isBomb() == true) {
                        bombCapture(x, y);
                    }

                }
            }

            if ((xPositionSelected == x + 2) && (yPositionSelected == y - 2) && (pieceAt(x+1, y-1)!=null)) {
                if (pieceAt(x+1, y-1).side() != pieceAt(x,y).side()) {
                    remove(x + 1, y - 1);
                    piecesWater = piecesWater - 1;
                    captured = true;
                    if (selected.isBomb() == true) {
                        bombCapture(x, y);
                    }

                }
            }
            if ((xPositionSelected == x - 2) && (yPositionSelected == y + 2) && (pieceAt(x-1, y+1)!=null)) {
                if (pieceAt(x-1, y+1).side() != pieceAt(x,y).side()) {
                    remove(x - 1, y + 1);
                    piecesFire = piecesFire - 1;
                    captured = true;
                    if (selected.isBomb() == true) {
                        bombCapture(x, y);
                    }
                }
            }

            if ((xPositionSelected == x + 2) && (yPositionSelected == y + 2) && (pieceAt(x+1, y+1)!=null)) {
                if (pieceAt(x+1, y+1).side() != pieceAt(x,y).side()) {
                    remove(x + 1, y + 1);
                    piecesFire = piecesFire - 1;
                    captured = true;
                    if (selected.isBomb() == true) {
                        bombCapture(x, y);
                    }

                }
            }
    }

    //Handles captures involving bomb piece detonations 
    private void bombCapture(int x, int y) {
        wasBomb = true;
        for (int N = x - 1; N < x + 2; N = N + 1) {
            for (int M = y - 1; M < y + 2; M = M + 1) {
                if ((pieceAt(N, M) != null) && (pieceAt(N,M).isShield() == false)) {
                    remove(N, M);
                    if (player == 0) {
                        piecesWater = piecesWater - 1;
                    }
                    else {
                        piecesFire = piecesFire - 1;
                    }
                }
            }
             
        }
    }


    // Selects the piece at (x, y) location specified
    public void select(int x, int y) {
            if (pieceAt(x,y) != null) {
                if ((x != xPositionSelected) || (y != yPositionSelected)) {
                hasSelected = true;
                xPositionSelected = x;
                yPositionSelected = y;
                }
            }
            else {
                if ((hasSelected == true) && ((hasMoved == false) || (captured == true))) {
                    hasMoved = true;
                    pieceAt(xPositionSelected,yPositionSelected).move(x, y);
                    selected = pieceAt(x, y);
                    remove(xPositionSelected,yPositionSelected);
                    capturePieces(xPositionSelected, yPositionSelected, x, y);
                }
                xPositionSelected = x;
                yPositionSelected = y;
            }
    }

    // Returns a boolean specifying whether the current player can end their turn
    public boolean canEndTurn() {
        if ((hasMoved == true) || (captured == true)) {
            return true;
        }
        return false;
    }

    //Called at the end of the turn to handle the switching of the players and other logistics
    public void endTurn() {
        switchTurn();
        xPositionSelected = -1;
        yPositionSelected = -1;
        selected.doneCapturing();
    }

    //Returns a string that declares the winner of the game
    public String winner() {
        if ((piecesFire == 0) && (piecesWater==0)) {
            return "No one";
        }
        if (piecesFire == 0) {
            return "Water";
        }
        if (piecesWater == 0) {
            return "Fire";
        }
        return null;
    }

    public static void main (String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board playingBoard = new Board(false);
        while(true) {
            playingBoard.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                int x_pos = (int)x;
                double y = StdDrawPlus.mouseY();
                int y_pos = (int)y;
                if (playingBoard.canSelect(x_pos, y_pos)) {
                    playingBoard.select(x_pos, y_pos);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (playingBoard.canEndTurn()) {
                    playingBoard.endTurn();
                }
            }   
            StdDrawPlus.show(10);
        }
    }
}
