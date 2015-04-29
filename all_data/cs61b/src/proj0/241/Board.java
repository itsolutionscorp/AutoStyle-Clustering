import java.lang.System;

public class Board {

    private Piece[][] pieceArray;
    private int player;
    private int[] selected = new int[2];
    private boolean hasMoved;
    private boolean hasCaptured;
    private int numWaterPieces;
    private int numFirePieces;
    /*
    @pieceArray stores an Array of each Piece's location and type.
    @player is which player's turn it is (0 = Fire's turn)
    @selected gives the coordinates of the selected square, and is (-1,-1), if there is no selection made.
     */
    public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty == false) {
            Piece[][] pieceArray = initPieces(this);
            this.pieceArray = pieceArray;
            numWaterPieces = 12;
            numFirePieces = 12;
        }
        else {
            this.pieceArray = new Piece[8][8];
        }
        player = 0;
        selected[0] = -1;
        selected[1] = -1;
        hasMoved = false;
        hasCaptured = false;
    }

    private static void drawBoard(Board board) {
        String address;
        Piece piece;
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        for (int i = 0; i<8;i +=1) {
            for (int j = 0; j<8; j +=1) {
                if (board.selected[0] == i && board.selected[1] == j) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i+j)%2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5,j + .5, .5);

                piece = board.pieceArray[i][j];
                if (piece != null) {
                    address = "img/";
                    if (piece.isBomb()) address += "bomb-";
                    else if (piece.isShield()) address += "shield-";
                    else address += "pawn-";

                    if (piece.isFire()) address += "fire";
                    else address += "water";

                    if (piece.isKing()) address += "-crowned";

                    address += ".png";

                    StdDrawPlus.picture(i + .5, j + .5, address,1,1);
                }

            }
        }
    }

    private Piece[][] initPieces(Board b) {
        int x = 0;
        int y = 0;
        Piece[][] pieceArray = new Piece[8][8];

        while (x < 8) {
            pieceArray[x][y] = new Piece(true, b, x, y, "pawn");
            x += 2;
        }

        y = 1;
        x = 1;
        while (x < 8) {
            pieceArray[x][y] = new Piece(true, b, x, y, "shield");
            x += 2;
        }

        y = 2;
        x = 0;
        while (x < 8) {
            pieceArray[x][y] = new Piece(true, b, x, y, "bomb");
            x += 2;
        }

        y = 5;
        x = 1;
        while (x < 8) {
            pieceArray[x][y] = new Piece(false, b, x, y, "bomb");
            x += 2;
        }

        y = 6;
        x = 0;
        while (x < 8) {
            pieceArray[x][y] = new Piece(false, b, x, y, "shield");
            x += 2;
        }

        y = 7;
        x = 1;
        while (x < 8) {
            pieceArray[x][y] = new Piece(false, b, x, y, "pawn");
            x += 2;
        }

        return pieceArray;
    }

    public Piece pieceAt(int x, int y) {
        if (x<0 || x>7 || y<0 || y>7) return null;

        return this.pieceArray[x][y];
    }

    public void place(Piece p, int x, int y) {
        if (x<0 || x>7 || y<0 || y>7 || p == null) return;
        pieceArray[x][y] = p;
        if (p.isFire()) numFirePieces += 1;
        else numWaterPieces += 1;
    }

    public Piece remove(int x, int y) {
        if (x<0 || x>7 || y<0 || y>7) {
            System.out.println("ERROR in remove: OUT OF BOUNDS");
            return null;
        }
        else if (pieceAt(x,y) == null) {
            System.out.println("ERROR in remove: GOT NULL");
            return null;
        }

        else {
            Piece p =  pieceAt(x,y);
            pieceArray[x][y] = null;
            if (p.isFire()) numFirePieces -= 1;
            else numWaterPieces -= 1;
            return p;
        }
    }

    public boolean canSelect(int x, int y) {
        if (hasMoved == false || hasCaptured == true) {
            if (pieceAt(x,y) == null) {
                if (selected[0] == -1) {
                    return false;
                }
                else return validMove(selected[0], selected[1], x, y);
            }

            else if (hasCaptured == false) {
                if (selected[0] == -1) {
                    if ((player + pieceAt(x,y).side())%2 == 0) return true;
                    else return false;
                }

                else {
                    if ((player + pieceAt(x,y).side())%2 == 0) return true;
                    else return false;
                }
            }
            else return false;
        }
        else return false;
    }

    public void select(int x, int y) {
        if (selected[0] != -1 && (pieceAt(x,y) == null)) {
            // if (selected[0] == x && selected[1] ==y) return; chose the same square
            Piece currPiece = pieceAt(selected[0], selected[1]);
            if (currPiece == null) {
                clearSelected();
                return;
            }
            currPiece.move(x, y);
            hasCaptured = currPiece.hasCaptured();
            currPiece.doneCapturing();
            hasMoved = true;
        }
        selected[0] = x;
        selected[1] = y;
    }

    private void clearSelected() {
        selected[0] = -1;
        selected[1] = -1;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece p = pieceAt(xi,yi);

        if (xf<0 || xf>7 || yf<0 || yf>7) return false; //out-of-bounds movemt
        if (pieceAt(xf,yf) != null) return false; //moving onto another piece
        if (xi == xf && yi == yf) return true;

        //Piece is taking a diagonal step
        if (Math.abs(xf-xi) == 1 && Math.abs(yf-yi) == 1 && hasCaptured == false && hasMoved == false) {
            if (yf < yi) { //piece is moving down
                return (!p.isFire() || p.isKing()); //okay if water or King
            }
            else if (yf > yi) { //piece is moving up
                return (p.isFire() || p.isKing()); //okay if fire or King
            }

            else return false;
        }

        //Piece is taking a diagonal leap
        else if (Math.abs(xf-xi) == 2 && Math.abs(yf-yi) == 2 && (hasCaptured == true || hasMoved == false)) {
            Piece intermediate = pieceAt( (xf+xi)/2, (yf+yi)/2); //the piece (or empty space) that is to be hopped over
            if (intermediate == null) return false; //can't jump over empty spots
            else if (intermediate.side() == p.side()) return false; //can't jump over allies

            if (yf < yi) { //piece is moving down
                return (!p.isFire() || p.isKing()); //okay if water or King
            }
            else if (yf>yi) { //piece is moving up
                return (p.isFire() || p.isKing()); //okay if fire or King
            }

            else return false;
        }

        //Not a step or leap = BAD MOVE
        else return false;
    }


    public boolean canEndTurn() {
        return hasMoved;
    }

    public void endTurn() {
        player = (player + 1)%2;
        clearSelected();
        hasMoved = false;
        hasCaptured = false;
    }



    public String winner() {
        if (numWaterPieces == 0 && numFirePieces == 0) {
            return "No one";
        }
        else if (numFirePieces == 0) return "Water";
        else if (numWaterPieces == 0) return "Fire";

        else return null;
    }

    public static void main(String[] args) {
        Board currBoard = new Board(false);
        double x;
        double y;
        String won = currBoard.winner();
        while (won == null) {
            drawBoard(currBoard);

            if (StdDrawPlus.mousePressed()) {
                x = StdDrawPlus.mouseX();
                y = StdDrawPlus.mouseY();

                if (currBoard.canSelect((int) x, (int) y)) {
                    currBoard.select((int) x, (int) y);
                }
            }

            if (StdDrawPlus.isSpacePressed()) {
                if (currBoard.canEndTurn()) {
                    currBoard.endTurn();
                }
            }

            won = currBoard.winner();

            StdDrawPlus.show(10);
        }

        System.out.println(won);
    }
}