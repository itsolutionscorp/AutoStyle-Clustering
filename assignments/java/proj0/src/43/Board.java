/**
*   @author Kelly Cho
*/

public class Board {

    private Piece[][] pieces;
    private int size = 8;

    /** game information */
    private String turn;
    private Piece current;
    private boolean playerHasSelected;
    private boolean playerHasMoved;
    private int[] currentPos = new int[2];
    private int[] selectedPos = new int[2];

    private int waterPieces;
    private int firePieces;


    public static void main(String[] args) {
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, b.size);
        StdDrawPlus.setYscale(0, b.size);


        while (true) {

            b.drawBoard(b.size);

            if (b.winner() != null) {
                b.winner();
            }

            if (StdDrawPlus.mousePressed()) {
                int x = ((int) StdDrawPlus.mouseX());
                int y = ((int) StdDrawPlus.mouseY());

                if (b.canSelect(x, y)) {
                    b.select(x, y);
                }
            }

            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
            StdDrawPlus.show(10);

        }
    } 

    public Board(boolean shouldBeEmpty) {
        /* initialize game */
        this.pieces = new Piece[size][size];
        this.playerHasMoved = false;
        this.playerHasSelected = false;
        this.firePieces = 0;
        this.waterPieces = 0;
        this.turn = "fire";

        /* initialize board */

        if (!shouldBeEmpty) {
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    if (y == 0 && x % 2 == 0) {
                        place(new Piece(true, this, x, y, "pawn"), x, y);
                    } else if (y == 1 && x % 2 != 0) {
                        place(new Piece(true, this, x, y, "shield"), x, y);
                    } else if (y == 2 && x % 2 == 0) {
                        place(new Piece(true, this, x, y, "bomb"), x, y);
                    } else if (y == size - 3 && x % 2 != 0) {
                        place(new Piece(false, this, x, y, "bomb"), x, y);
                    } else if (y == size - 2 && x % 2 == 0) {
                        place(new Piece(false, this, x, y, "shield"), x, y);
                    } else if (y == size - 1 && x % 2 != 0) {
                        place(new Piece(false, this, x, y, "pawn"), x, y);
                    }
                }
            }
        }
    }
        
    private void drawBoard(int n) {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (playerHasSelected && (x == selectedPos[0] && y == selectedPos[1])) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                } else if ((x + y) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }             
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);

                if (pieces[x][y] != null) {
                    Piece selectedPiece = pieces[x][y];
                    String img_name = "img/";

                    /* loop to get piece type */
                    if (selectedPiece.isShield()) {
                        img_name += "shield";
                    } else if (selectedPiece.isBomb()) {
                        img_name += "bomb";
                    } else {
                        img_name += "pawn";
                    }

                    /* loop to get piece side */
                    if (selectedPiece.isFire()) {
                        img_name += "-fire";
                    } else {
                        img_name += "-water";
                    }

                    /* loop to get piece crowned */
                    if (selectedPiece.isKing()) {
                        img_name += "-crowned";
                    }

                    StdDrawPlus.picture(x + .5, y + .5, img_name + ".png", 1, 1);
                }

            }
        }
    }

    public Piece pieceAt(int x, int y) {
        if (x < 8 && y < 8) {
            return pieces[x][y];
        } else {
            return null;
        }
    }

    public boolean canSelect(int x, int y) {
        if ((x >= 0 && x < size) && (y >= 0 && y < size)) {
            /* if there is a piece */
            if (pieceAt(x, y) != null && !pieceAt(x, y).hasCaptured()) {
                current = pieces[x][y];
                if (turn.equals("fire") && (!playerHasMoved || current.hasCaptured())
                    && pieceAt(x, y).isFire()) {
                    return true;
                } else if (turn.equals("water") && (!playerHasMoved || current.hasCaptured())
                    && !pieceAt(x, y).isFire()) {
                    return true;
                } return false;

            /* empty square and a piece has already been selected */
            } else if (playerHasSelected
                        && (!playerHasMoved || current.hasCaptured())
                        &&  validMoveSetup(currentPos[0], currentPos[1], x, y)) {
                            return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void select(int x, int y) {
        if (pieceAt(x, y) != null) {
            current = pieceAt(x, y);
        } else {
            pieces[currentPos[0]][currentPos[1]].move(x, y);
            playerHasMoved = true;

        } 
        playerHasSelected = true;
        currentPos[0] = x;
        currentPos[1] = y;
        selectedPos[0] = x;
        selectedPos[1] = y;
    }

    public void place(Piece p, int x, int y) {
        if (x < 8 && y < 8) {
            pieces[x][y] = p;
        }
        if (p.isFire()) {
            firePieces += 1;
        } else {
            waterPieces += 1;
        }
    }    

    /* makes sure validMove can work regardless of direction */
    private boolean validMoveSetup(int xi, int yi, int xf, int yf) {
        boolean validPathsFire = validMove(xi, yi, xf, yf);
        boolean validPathsWater = validMove(xf, yf, xi, yi);
        if (current.isKing()) {
            if (yf > yi) {
                return validPathsFire;
            } else {
                return validPathsWater;
            }
        } else if (turn.equals("fire")) {
            return validPathsFire;
        } else {
            return validPathsWater;
        }
    }

    /* checks whether the current piece can move to a selected position */
    private boolean validMove(int xi, int yi, int xf, int yf) {
        int side;
        if (current.isKing()) {
            side = 2;
        } else {
            side = current.side();
        }

        if ((xf - xi == 1 || xf-xi == -1) && yf - yi == 1 && !current.hasCaptured()) {
            return true;
        } else if (yf - yi == 2) {
            if (xf - xi == 2 && pieces[xi+1][yi+1] != null
                && pieces[xi+1][yi+1].side() != side) {
                    return true;
            } else if (xf - xi == -2 && pieces[xi-1][yi+1] != null
                && pieces[xi-1][yi+1].side() != side) {
                    return true;
            }
        }
        return false;
    }

    public Piece remove(int x, int y) {
        if (x > 7 || y > 7) {
            System.out.println("Error: position out of bounds");
            return null;
        } else if (pieceAt(x, y) == null) {
            System.out.println("Error: no piece to remove");
            return null;
        } else {
            Piece temp = pieceAt(x, y);
            pieces[x][y] = null;
            if (temp.hasCaptured() && temp.isBomb() && temp.equals(current)) {
                bombRemove(x, y);
            }
            if (temp.isFire()) {
                firePieces -= 1;
            } else {
                waterPieces -= 1;
            }
            return temp;
        } 
    }

    private void bombRemove(int x, int y) {
        for (int i : new int[] {x-1, x, x+1}) {
            for (int j : new int[] {y-1, y, y+1}) {
                if ((i >= 0 && j >= 0) &&
                    (i <= size && j <= size) &&
                    pieceAt(i, j) != null && 
                    !pieceAt(i, j).isShield()) {
                    remove(i, j);
                }
            }
        } 
        playerHasSelected = false;
    }

    public boolean canEndTurn() {
        return playerHasMoved;
    }
    
    public void endTurn() {
        if (turn.equals("fire")) {
            turn = "water";
        } else {
            turn = "fire";
        }
        playerHasMoved = false;
        playerHasSelected = false;
        if (current != null) {
            current.doneCapturing();
        }
    }

    public String winner() {
        if (waterPieces == 0 && firePieces == 0) {
            return "No one";
        } else if (waterPieces == 0) {
            return "Fire";
        } else if (firePieces == 0) {
            return "Water";
        } else {
            return null;
        }
    }


}