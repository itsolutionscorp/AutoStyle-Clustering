public class Board {

    // class variables
    private Piece[][] pieces = new Piece[8][8];
    private boolean fireTurn = true;
    private Piece selectedPiece;
    private int selectedX;
    private int selectedY;
    private boolean moved = false;

    // Draws the board, handles selections
    public static void main(String[] args) {
        Board b = new Board(false);

        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        while(true) {
            b.drawBoard();
            StdDrawPlus.show(100);

            // handles select
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int xInt = (int) x;
                int yInt = (int) y;

                if (b.canSelect(xInt, yInt)) {
                    b.select(xInt, yInt);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                System.out.println("Turn: "+b.fireTurn);
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
        }
    }

    // draws the board
    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {StdDrawPlus.setPenColor(StdDrawPlus.GRAY); } 
                else                  {StdDrawPlus.setPenColor(StdDrawPlus.RED);  }
                StdDrawPlus.filledSquare(i+.5, j+.5, .5);

                /*
                if (selectedPiece != null) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(selectedX+.5, selectedY+.5, .5);
                }
                */

                // draws the pieces based on the location of the array
                Piece p = pieceAt(i, j);
                if (p != null) {
                    if (p.isKing()) {
                        if ((p.side() == 0)) {
                            if (p.isBomb())        {StdDrawPlus.picture(i+.5, j+.5, "img/bomb-fire-crowned.png", 1, 1);   }
                            else if (p.isShield()) {StdDrawPlus.picture(i+.5, j+.5, "img/shield-fire-crowned.png", 1, 1); }
                            else                   {StdDrawPlus.picture(i+.5, j+.5, "img/pawn-fire-crowned.png", 1, 1);   }
                        }
                        else {
                            if (p.isBomb())        {StdDrawPlus.picture(i+.5, j+.5, "img/bomb-water-crowned.png", 1, 1);  }
                            else if (p.isShield()) {StdDrawPlus.picture(i+.5, j+.5, "img/shield-water-crowned.png", 1, 1);}
                            else                   {StdDrawPlus.picture(i+.5, j+.5, "img/pawn-water-crowned.png", 1, 1);  }
                        } 
                    }
                    else {
                        if ((p.side() == 0)) {
                            if (p.isBomb())        {StdDrawPlus.picture(i+.5, j+.5, "img/bomb-fire.png", 1, 1);           }
                            else if (p.isShield()) {StdDrawPlus.picture(i+.5, j+.5, "img/shield-fire.png", 1, 1);         }
                            else                   {StdDrawPlus.picture(i+.5, j+.5, "img/pawn-fire.png", 1, 1);           }
                        }
                        else {
                            if (p.isBomb())        {StdDrawPlus.picture(i+.5, j+.5, "img/bomb-water.png", 1, 1);          }
                            else if (p.isShield()) {StdDrawPlus.picture(i+.5, j+.5, "img/shield-water.png", 1, 1);        }
                            else                   {StdDrawPlus.picture(i+.5, j+.5, "img/pawn-water.png", 1, 1);          }
                        }
                    }
                }
            }
        }
    }

    // initializes the default configuration or an empty board
    public Board(boolean shouldBeEmpty) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!shouldBeEmpty) {
                    if ((j==0) && (i%2==0))      {pieces[i][j] = new Piece(true, this, i, j, "pawn");   }
                    else if ((j==1) && (i%2==1)) {pieces[i][j] = new Piece(true, this, i, j, "shield"); }
                    else if ((j==2) && (i%2==0)) {pieces[i][j] = new Piece(true, this, i, j, "bomb");   }
                    else if ((j==5) && (i%2==1)) {pieces[i][j] = new Piece(false, this, i, j, "bomb");  }
                    else if ((j==6) && (i%2==0)) {pieces[i][j] = new Piece(false, this, i, j, "shield");}
                    else if ((j==7) && (i%2==1)) {pieces[i][j] = new Piece(false, this, i, j, "pawn");  }
                else {pieces[i][j] = null;}
                }
            }
        }
    }

    private boolean checkBounds(int x, int y) {
        return 0 <= x && x < 8 && 0 <= y && y < 8;
    }

    // checks to see if there's a piece at the location
    public Piece pieceAt(int x, int y) {
        if (checkBounds(x, y)) {
            return pieces[x][y];
        } 
        else {
            return null;
        }
    }

    // places the piece on the Board
    public void place(Piece p, int x, int y) {
        if (checkBounds(x, y) && p != null) {
            pieces[x][y] = p;
        }
    }

    // removes the piece from the board
    public Piece remove(int x, int y) {
        Piece removed = pieceAt(x, y);
        if (!checkBounds(x, y)) {
            System.out.println("Input out of bounds");
        } 
        else if (removed == null) {
            System.out.println("No piece at (" + x + "," + y + ")");
        } 
        else {
            pieces[x][y] = null;
        }
        return removed;
    }

    // prints out the winner of the game
    public String winner() {
        boolean fireTurn = false;
        boolean waterTurn = false;
        boolean none = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieceAt(i, j) != null) {
                    if (pieceAt(i, j).isFire()) {
                        fireTurn = true;
                    } 
                    else if (pieceAt(i, j).isFire() != true) {
                        waterTurn = true;
                    }
                    else if (pieces[i][j]==null) {
                        none = true;
                    }
                }
            }
        }
        if (none) {
            return "No one";
        } 
        else if (!fireTurn && waterTurn) {
            return "Water";
        } 
        else if (fireTurn && !waterTurn) {
            return "Fire";
        } 
        else {
            return null;
        }
    }

    // checks to see whether a piece can be selected or not 
    public boolean canSelect(int x, int y) {
        // square with a piece
        if ((pieceAt(x, y) != null) && (pieceAt(x, y).isFire() == fireTurn)) {
            return selectedPiece == null ||
                   selectedPiece != null && !moved;
        }
        // empty square
        else {
            return ((selectedPiece != null) &&
                   (!moved) &&
                   (validMove(selectedX, selectedY, x, y)) ||
                   (selectedPiece != null) &&
                   (selectedPiece.hasCaptured()) &&
                   (validMove(selectedX, selectedY, x, y)));
        }
    }

    // checks to see whether a move is valid
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if ((checkBounds(xi, yi) && (checkBounds(xf, yf)) &&
            (pieceAt(xf, yf) == null) && (pieceAt(xi, yi)!=null))) {
            // for the fire player, can only go up
            if (pieceAt(xi, yi).isKing()) {
                return // kings can do whatever any other piece can do, regardless of type
                        ((xi==0) && (xf==xi+1) && (yf==yi+1) || // left column, only right, one space
                        (xi==7) && (xf==xi-1) && (yf==yi+1) || // right column, only left, one space
                        (yi!=7) && (Math.abs(xf-xi)==1) && (yf==yi+1) || // one space
                        (((pieceAt(xi+1, yi+1)!=null) && (!pieceAt(xi+1, yi+1).isFire()) && (pieceAt(xi+2, yi+2)==null))) || // right jump
                        ((pieceAt(xi-1, yi+1)!=null) && (!pieceAt(xi-1, yi+1).isFire()) && (pieceAt(xi-2, yi+2)==null)) || // left jump

                        (xi==7) && (xf==xi-1) && (yf==yi-1) || // right column, only left, one space
                        (xi==0) && (xf==xi+1) && (yf==yi-1) || // left column, only right, one space
                        (yi!=0) && (Math.abs(xf-xi)==1) && (yf==yi-1) || // one space
                        ((pieceAt(xi-1, yi-1)!=null) && (!pieceAt(xi-1, yi-1).isFire()) && (pieceAt(xi-2, yi-2)==null)) || // left jump
                        ((pieceAt(xi+1, yi-1)!=null) && (!pieceAt(xi+1, yi-1).isFire()) && (pieceAt(xi+2, yi-2)==null)) // right jump
                        );
            }
            else if (fireTurn) {
                return ((xi==0) && (xf==xi+1) && (yf==yi+1) || // left column, only right, one space
                        (xi==7) && (xf==xi-1) && (yf==yi+1) || // right column, only left, one space
                        (yi!=7) && (Math.abs(xf-xi)==1) && (yf==yi+1) || // one space
                        (((pieceAt(xi+1, yi+1)!=null) && (!pieceAt(xi+1, yi+1).isFire()) && (pieceAt(xi+2, yi+2)==null))) || // right jump
                        ((pieceAt(xi-1, yi+1)!=null) && (!pieceAt(xi-1, yi+1).isFire()) && (pieceAt(xi-2, yi+2)==null)) // left jump
                        );
            }

            // for the water player, can only go down
            else if (!fireTurn) {
                return ((xi==7) && (xf==xi-1) && (yf==yi-1) || // right column, only left, one space
                        (xi==0) && (xf==xi+1) && (yf==yi-1) || // left column, only right, one space
                        (yi!=0) && (Math.abs(xf-xi)==1) && (yf==yi-1) || // one space
                        ((pieceAt(xi-1, yi-1)!=null) && (!pieceAt(xi-1, yi-1).isFire()) && (pieceAt(xi-2, yi-2)==null)) || // left jump
                        ((pieceAt(xi+1, yi-1)!=null) && (!pieceAt(xi+1, yi-1).isFire()) && (pieceAt(xi+2, yi-2)==null)) // right jump
                        );
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    // actually selects the piece
    public void select(int x, int y) {
        Piece p = pieceAt(x, y);
        if (p != null && p.isFire() == fireTurn) {
            selectedPiece = p;
        }
        else if (selectedPiece != null) {
            selectedPiece.move(x, y);
            moved = true;
        }
        else {
            selectedPiece = p;
        }
        selectedX = x;
        selectedY = y;
    }

    // checks whether you can end your turn
    public boolean canEndTurn() {
        if (selectedPiece!=null) {
            return (moved || selectedPiece.hasCaptured());
        } 
        else {
            return false;
        }
    }

    // calls the end of the turn
    public void endTurn() {
        if (selectedPiece.hasCaptured()) {
            selectedPiece.doneCapturing();
        }
        selectedPiece = null;
        fireTurn = !fireTurn;
        moved = false;
    }


} 