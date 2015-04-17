/** 
 *  @author Taylor Wong
 */

public class Board {
    private static final int N = 8;
    private Piece[][] pieces = new Piece[N][N];
    private boolean isFireTurn = true; //fire team makes the first move
    private boolean moved;

    private Piece selectedPiece;

    private int xi = -1;
    private int yi = -1;

    public Board(boolean shouldBeEmpty) {
        if (!shouldBeEmpty) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if ((i + j) % 2 == 0) {
                    switch (j) {
                        case 0: pieces[i][j] = new Piece(true, this, i, j, "pawn");
                                break;
                        case 1: pieces[i][j] = new Piece(true, this, i, j, "shield");
                                break;
                        case 2: pieces[i][j] = new Piece(true, this, i, j, "bomb");
                                break;
                        case N - 3: pieces[i][j] = new Piece(false, this, i, j, "bomb");
                                break;
                        case N - 2: pieces[i][j] = new Piece(false, this, i, j, "shield");
                                break;
                        case N - 1: pieces[i][j] = new Piece(false, this, i, j, "pawn");
                                break;
                        default:
                                break;
                    }
                    }

                }
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        Piece piece = null;
        if (x > -1 && x < N && y > -1 && y < N) piece = pieces[x][y];
        return piece;
    }

    public boolean canSelect(int x, int y) {
        boolean selectable = false;
        if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == isFireTurn && !moved) {
        //space is occupied && piece corresponds to turn && can't select a new piece after you have moved
            selectable = true;
        } else if (selectedPiece != null && pieceAt(x, y) == null && validMove(xi, yi, x, y) && (!moved || (selectedPiece.hasCaptured() && Math.abs(x - xi) == 2))) {
        //a piece has been selected && current selection is empty space && move or capture is valid && can't move twice unless another capture is being performed
            selectable = true;
        }
        return selectable;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        boolean valid = true;
        int yDis = Math.abs(xf - xi);

        if (yDis == 2) { //capture
            if (pieceAt((xf + xi)/2, (yf + yi)/2) == null || pieceAt((xf + xi)/2, (yf + yi)/2).isFire() == selectedPiece.isFire()) valid = false;
        } else if (yDis != 1) { //if yDis is not 2, it must be 1; otherwise, move is not valid
            valid = false;
        }

        if (selectedPiece.isKing()) {
            if (Math.abs(yf - yi) != yDis) valid = false;
        } else { //not King
            if (selectedPiece.isFire()) {
                if (yf - yi != yDis) valid = false;
            } else { //is Water
                if (yf - yi != -yDis) valid = false;
            }
        }

        return valid;
    }

    public void select(int x, int y) {
        if (pieceAt(x, y) != null) { //selected a piece
            selectedPiece = pieceAt(x, y);
            xi = x;
            yi = y;
        } else if (pieceAt(x, y) == null) { //piece has been selected; now check if space is unoccupied
            xi = x;
            yi = y;
            selectedPiece.move(x, y);
            moved = true;
        }
    }

    public void place(Piece p, int x, int y) {
        pieces[x][y] = p;
    }

    public Piece remove(int x, int y) {
        if (x == xi && y == yi) {
            //reset selectedPiece, xi and yi
            selectedPiece = null;
            xi = -1;
            yi = -1;
        }
        Piece removedPiece = pieces[x][y];
        pieces[x][y] = null;
        return removedPiece;
    }

    public boolean canEndTurn() {
        return moved;
    }

    public void endTurn() { //handles switching of players and anything else that happens at the end of a turn
        isFireTurn = !isFireTurn;
        if (selectedPiece != null) selectedPiece.doneCapturing();
        //reset selectedPiece, xi and yi
        selectedPiece = null;
        xi = -1;
        yi = -1;
        moved = false;

    }

    public String winner() {
        String w = null;
        int scoreFire = 0;
        int scoreWater = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieceAt(i, j) != null) {
                    if (pieceAt(i, j).isFire()) scoreFire++;
                    else scoreWater++;
                }
            }
        }

        if (scoreWater > 0 && scoreFire == 0) {
            w = "Water";
        } else if (scoreFire > 0 && scoreWater == 0) {
            w = "Fire";
        } else if (scoreWater == 0 && scoreFire == 0) {
            w = "No one";
        } else {
            w = null;
        }

        return w;
    }

    /** Location of pieces. */
/*    private static boolean[][] pieces;*/

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if (i == xi && j == yi) StdDrawPlus.setPenColor(StdDrawPlus.WHITE); //rewrite this later
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
/*                if (pieces[i][j]) {
                    StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                }*/
            }
        }
    }

    private String getImage(Piece p) {
    String image = "img/";
        
        if (p.isBomb()) {
            image += "bomb";
        } else if (p.isShield()) {
            image += "shield";
        } else {
            image += "pawn";
        }

        if (p.isFire()) {
            image += "-fire";
        } else { //Water
            image += "-water";
        }

        if (p.isKing()) {
            image += "-crowned";
        }

        image += ".png";
        return image;
    }

    public static void main(String[] args) {
        Board game = new Board(false); //initializes board with default configuration

        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
/*        pieces = new boolean[N][N];*/

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            game.drawBoard(N);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (game.pieceAt(i, j) != null) { //is there a better way to do this?
                        StdDrawPlus.picture(i + .5, j + .5, game.getImage(game.pieceAt(i, j)), 1, 1);
                    }
                }
            }
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if (game.canSelect(x, y)) game.select(x, y);
            }
            if (StdDrawPlus.isSpacePressed() && game.canEndTurn()) {
                game.endTurn();
            }
            StdDrawPlus.show(10);
        }
    }


}