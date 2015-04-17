//adapted from StdDrawDemo

public class Board {
    /** Location of pieces. */
    private Piece[][] pieces;
    private Piece selected = null;
    private boolean moved = false;
    private int[] coordinates = new int[]{61,61};
    private int current_player = 0;
    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private void drawBoard(int N) {
        for (int i = 0; i < N; i+=1) {
            for (int j = 0; j < N; j+=1) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(coordinates[0] + .5, coordinates[1] + .5, .5);
                }
            }
        }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.drawBoard(N);
            for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (b.pieces[i][j] != null){
                if (b.pieces[i][j].isKing()){
                    if (b.pieces[i][j].side() == 0){
                        if (b.pieces[i][j].isBomb()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                        } else if (b.pieces[i][j].isShield()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                        }
                    } else {
                        if (b.pieces[i][j].isBomb()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                        } else if (b.pieces[i][j].isShield()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                        }
                    }
                } else {
                    if (b.pieces[i][j].side() == 0){
                        if (b.pieces[i][j].isBomb()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        } else if (b.pieces[i][j].isShield()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                    } else {
                        if (b.pieces[i][j].isBomb()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        } else if (b.pieces[i][j].isShield()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }
                    }

                }
            }}}
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int intx = (int) x;
                int inty = (int) y;
                if (b.canSelect(intx , inty)){
                    b.select(intx, inty);
                }}
                if (StdDrawPlus.isSpacePressed()){
                    if (b.canEndTurn()){
                        b.endTurn();
                    }
                }
            StdDrawPlus.show(10);
        }

    }
    

    public Board(boolean shouldBeEmpty){
        pieces = new Piece[8][8];
        if (shouldBeEmpty == false){
            for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i % 2) == 0 && j == 0){
                    pieces[i][j] = new Piece(true, this, i, j, "pawn");
                } else if ((i % 2) == 1 && j == 1){
                    pieces[i][j] = new Piece(true, this, i, j, "shield");
                } else if ((i % 2) == 0 && j == 2){
                    pieces[i][j] = new Piece(true, this, i, j, "bomb");
                } else if ((i % 2) == 1 && j == 5){
                    pieces[i][j] = new Piece(false, this, i, j, "bomb");
                } else if ((i % 2) == 0 && j == 6){
                    pieces[i][j] = new Piece(false, this, i, j, "shield");
                } else if ((i % 2) == 1 && j == 7){
                    pieces[i][j] = new Piece(false, this, i, j, "pawn");
                }
    }}}}

    public Piece pieceAt(int x, int y){
        if (x < 0 || y < 0 || x > 7 || y > 7){
            return null;
        } else if (pieces[x][y] != null){
            return pieces[x][y];
        } else {
            return null;
        }
    }

    public void place(Piece p, int x, int y){
        if ((p != null) && (x > -1 && y > -1 && x < 8 && y < 8)){
            pieces[x][y] = p;
        }
    }   

    public boolean canSelect(int x, int y){
        if (x > -1 && y > -1 && x < 8 && y < 8){
        if (pieces[x][y] != null){
            if ((moved == false) && (pieces[x][y].side() == current_player)){
                return true;
            }}  else if (selected != null){
            if ((moved == false) && validMove(coordinates[0], coordinates[1], x, y)){
                return true;
            }
            else if ((selected.hasCaptured() == true) && (Math.abs(x- coordinates[0] ) == 2)&&(Math.abs(y - coordinates[1] ) == 2) && validMove(coordinates[0], coordinates[1], x, y)){
                return true;
            }
        } return false;
    }return false;}

    private boolean validMove(int xi, int yi, int xf, int yf){
        if (xf < 0 || yf < 0 || xf > 7 || yf > 7){
            return false;
        }
        if (pieces[xi][yi] != null){
        Piece q = pieces[xi][yi];
        if (q.isKing()){
            if ((Math.abs(xf-xi) == 1)&&(Math.abs(yf-yi) == 1)&&(pieces[xf][yf] == null)){
                return true;
            } else if ((Math.abs(xf-xi) == 2)&&(Math.abs(yf-yi) == 2)&&(pieces[xf][yf] == null)&&(pieces[(xi+xf)/2][(yi+yf)/2] != null)&&(pieces[(xi+xf)/2][(yi+yf)/2].side() != q.side())){
                return true;
            }
        } else if (q.side() == 0){
            if ((Math.abs(xf-xi) == 1)&&((yf-yi) == 1)&&(pieces[xf][yf] == null)){
                return true;
            } else if ((Math.abs(xf-xi) == 2)&&((yf-yi) == 2)&&(pieces[xf][yf] == null)&&(pieces[(xi+xf)/2][(yi+yf)/2] != null)&&(pieces[(xi+xf)/2][(yi+yf)/2].side() != q.side())){
                return true;
            }
        } else if (q.side() == 1){
            if ((Math.abs(xf-xi) == 1)&&((yf-yi) == -1)&&(pieces[xf][yf] == null)){
                return true;
            } else if ((Math.abs(xf-xi) == 2)&&((yf-yi) == -2)&&(pieces[xf][yf] == null)&&(pieces[(xi+xf)/2][(yi+yf)/2] != null)&&(pieces[(xi+xf)/2][(yi+yf)/2].side() != q.side())){
                return true;
            }
        }} return false;
    }

    public void select(int x, int y){
        if (pieces[x][y] == null){
            place(selected, x, y);
            selected.move(x, y);
            remove(coordinates[0],coordinates[1]);
            coordinates[0] = x;
            coordinates[1] = y;
            moved = true;
        } else {
            selected = pieces[x][y];
            coordinates[0] = x;
            coordinates[1] = y;
        }

    }

    public Piece remove(int x, int y){
        if (x < 0 || y < 0 || x > 7 || y > 7){
            System.out.println("index out of boundary");
            return null;
        } else if (pieces[x][y] == null){
            System.out.println("no piece at this position");
            return null;
        } else {
            Piece copy = pieces[x][y];
            pieces[x][y] = null;
            return copy;
        }
    }

    public boolean canEndTurn(){
        if (moved){
            return true;
        }
        return false;
    }

    public void endTurn(){
        current_player = 1 - current_player;
        if (selected != null){
            selected.doneCapturing();
        }
        selected = null;
        coordinates = new int[]{61,61};
        moved = false;
    }

    public String winner(){
        int countfire = 0;
        int countwater = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null){
                    if (pieces[i][j].side() == 0){
                        countfire = countfire + 1;
                    } else {
                        countwater = countwater + 1;
                    }
                }
            }
        }
        if ((countwater == countfire) && (countwater == 0)){
            return "No one";
        } else if (countfire > countwater){
            return "Fire";
        } else if (countfire < countwater){
            return "Water";
        } else {
            return null;
        }
    }}