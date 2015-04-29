public class Board {

    private Piece[][] pieces;
    private boolean empty = false;
    private boolean hasMoved = false;
    private int currentPlayer = 0;
    private int selectedX = 8;
    private int selectedY = 8;
    private static int N = 8;
    private Piece toMove;
    private int fireCount = 12;
    private int waterCount = 12;

    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[N][N];
        empty = shouldBeEmpty;
        if (!empty) {
            for (int i = 0; i < N/2; i++) {
                int inline = 2*i;
                int staggered = 2*i + 1;
                pieces[inline][0] = new Piece(true, this, inline, 0, "pawn");
                pieces[staggered][1] = new Piece(true, this, staggered, 1, "shield");
                pieces[inline][2] = new Piece(true, this, inline, 2, "bomb");
                pieces[staggered][5] = new Piece(false, this, staggered, 5, "bomb");
                pieces[inline][6] = new Piece(false, this, inline, 6, "shield");
                pieces[staggered][7] = new Piece(false, this, staggered, 7, "pawn");
            }
        }
    }

    private int countSide(int side) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] != null && pieces[i][j].side() == side) {
                    count = count + 1;
                }
            }
        }
        return count;
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == selectedX && j == selectedY)   StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                    String img = "img/";
                    if (pieces[i][j].isBomb())  img = img.concat("bomb");
                    else if (pieces[i][j].isShield())   img = img.concat("shield");
                    else    img = img.concat("pawn");

                    if (pieces[i][j].isFire())  img = img.concat("-fire");
                    else    img = img.concat("-water");

                    if (pieces[i][j].isKing())  img = img.concat("-crowned.png");
                    else    img = img.concat(".png");
                    StdDrawPlus.picture(i + .5, j + .5, img, 1, 1);
                }
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        if (x >= N || x < 0 || y >= N || y < 0) return null;
        if (pieces[x][y] != null)   return pieces[x][y];
        else                        return null;
    }

    public boolean canSelect(int x, int y) {
        if (toMove != null && !toMove.hasCaptured() && hasMoved)    return false;
        if (toMove != null && toMove.hasCaptured() && pieces[x][y] != null) return false;
        // if (x == selectedX && y == selectedY)   return false;
        if (pieces[x][y] != null && pieces[x][y].side() == currentPlayer) {
            if (toMove == null && !hasMoved) {
                return true;
            }
        }
        else if (pieces[x][y] == null) {
            if (canMove(toMove, x, y) && toMove != null) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
        return true;
    }

    private boolean canMove(Piece p, int x, int y) {
        // check to see if is king
        int xDiff = Math.abs(x - selectedX);
        int yDiff = Math.abs(y - selectedY);
        if (xDiff == 2 && yDiff == 2) {
            Piece cap = pieces[(int)(x + selectedX) / 2][(int)(y + selectedY) / 2];
            if (cap == null || cap.side() == currentPlayer) return false;
        }
        // all moves must change x value
        if (selectedX == x)             return false;
        // can't move to occupied location
        if (pieces[x][y] != null)       return false;
        // moves must be diagonally valid and either 1 or 2 squares away
        if ((xDiff != yDiff) || (xDiff > 2 || yDiff > 2)) return false;
        // if difference in x and y are greater than 2 and you're jumping over
        // your own dude
        if (xDiff == 2 && yDiff == 2
                && pieces[(x + selectedX)/2][(y + selectedY)/2] != null
                && pieces[(x + selectedX)/2][(y + selectedY)/2].side() == currentPlayer)  return false;
        // if you have either captured or moved and you're trying to pull a 1x1
        // diagonal move
        if ((p.hasCaptured() || hasMoved)
                && Math.abs(x - selectedX) == 1
                && Math.abs(y - selectedY) == 1) return false;
        // if you're fire and going down or water and going up, rework for
        // kings
        if (!p.isKing() && ((p.isFire() && selectedY > y) ||
                (!p.isFire() && selectedY < y))) return false;
        return true;
    }

    public void select(int x, int y) {
        selectedX = x;
        selectedY = y;
        if (pieces[x][y] != null) {
            toMove = pieces[x][y];
        }
        else {
            toMove.move(x, y);
            if (toMove.isBomb() && toMove.hasCaptured()) {
                selectedX = 8;
                selectedY = 8;
            }
            hasMoved = true;
        }
    }

    public void place(Piece p, int x, int y) {
        if (0 <= x && x < N && 0 <= y && y < N && p != null) {
            pieces[x][y] = p;
        }
    }

    public static void main(String[] args) {
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        while(b.winner() == null) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) {
                    b.select(x, y);
                }
            }
            else if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
            StdDrawPlus.show(100);
        }
    }

    public Piece remove(int x, int y) {
        if (x >= N || x < 0 || y >= N || y < 0) {
            return null;
        }
        else {
            Piece temp = pieces[x][y];
            pieces[x][y] = null;
            return temp;
        }
    }

    public boolean canEndTurn() {
        if (toMove == null) return hasMoved;
        else                return (hasMoved || toMove.hasCaptured());
    }

    public void endTurn() {
        hasMoved = false;
        if (toMove != null) toMove.doneCapturing();
        selectedX = 8;
        selectedY = 8;
        currentPlayer = Math.abs(currentPlayer - 1);
    }

    public String winner() {
        waterCount = countSide(1);
        fireCount = countSide(0);
        if (waterCount == 0 && fireCount > 0)   return "Fire";
        else if (fireCount == 0 && waterCount > 0)  return "Water";
        else if (fireCount == 0 && waterCount == 0) return "No one";
        else    return null;
    }
}
