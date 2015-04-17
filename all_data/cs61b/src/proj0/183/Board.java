public class Board {
    private int player;
    private Piece[][] pieces;
    private Piece selected;
    private int selectedX;
    private int selectedY;
    private boolean moved;
    private boolean canEndTurn;

    public Board(boolean shouldBeEmpty) {
        player = 1;
        // 1 => fire
        // 2 => water

        moved = false;
        canEndTurn = false;

        if (shouldBeEmpty) {
            pieces = new Piece[8][8];
        }
        else {
            pieces = new Piece[8][8];
            for (int i = 0, j = 0; i < 8; i += 2) {
                Piece p = new Piece(true, this, i, j, "pawn");
                pieces[(int) i][(int) j] = p;
            }
            for (int i = 1, j = 1; i < 8; i += 2) {
                Piece p = new Piece(true, this, i, j, "shield");
                pieces[(int) i][(int) j] = p;
            }
            for (int i = 0, j = 2; i < 8; i += 2) {
                Piece p = new Piece(true, this, i, j, "bomb");
                pieces[(int) i][(int) j] = p;
            }     
            for (int i = 1, j = 5; i < 8; i += 2) {
                Piece p = new Piece(false, this, i, j, "bomb");
                pieces[(int) i][(int) j] = p;
            }
            for (int i = 0, j = 6; i < 8; i += 2) {
                Piece p = new Piece(false, this, i, j, "shield");
                pieces[(int) i][(int) j] = p;
            }
            for (int i = 1, j = 7; i < 8; i += 2) {
                Piece p = new Piece(false, this, i, j, "pawn");
                pieces[(int) i][(int) j] = p;
            }              
        }
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                // draw default board
                if (pieces[i][j] == null) continue;
                if (pieces[i][j].isFire()) {
                    if (pieces[i][j].isBomb()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    }
                    else if (pieces[i][j].isShield()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    }
                    else {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);    
                    }
                }   
                else {
                    if (pieces[i][j].isBomb()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    }
                    else if (pieces[i][j].isShield()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    }
                    else {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    }  
                }
            }  
        }
    }

    public static void main (String args[]) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        Board B = new Board(false);
        B.drawBoard(N);
    }

    public Piece pieceAt(int x, int y) {
        if (x >= 8 || y >= 8 || x < 0 || y < 0) {
            return null;
        }
        else if (pieces[x][y] == null) {
            return null;
        }
        else {
            return pieces[x][y];
        }
    }

    public boolean canSelect(int x, int y) {
        // if (x >= 8 || y >= 8 || x < 0 || y < 0) {
        //     System.out.println("Out of bound!!!");
        //     return false;
        // }
        if (pieces[x][y] != null) {
            if ((pieces[x][y].isFire() && player == 1) || (!pieces[x][y].isFire() && player == -1)) {
                if (selected == null || !moved) return true;
            }
        }
        else {
            if (selected != null || !moved) {
                if (pieces[x][y] == null) {
                    if (Math.sqrt(2) == Math.sqrt(Math.pow((selectedX - x), 2))+Math.pow((selectedY - y), 2))
                        return true;
                }
                return true;
            }
            // During this turn, the player has selected a Piece, captured, 
            // and has selected another valid capture destination. When performing multi-captures, 
            // you should only select the active piece once; all other selections should be valid destination points.
            else if (selected != null) {
                return true;             
            }
        }
        return false;
    }

    public void select(int x, int y) {
        if (!canSelect(x, y)) return;
        if (selected == null) {
            selected = pieceAt(x, y);
            selectedX = x;
            selectedY = y;
        }
        else {
            place(remove(selectedX, selectedY), x, y);
            selected = null;
            selectedX = -1;
            selectedY = -1;
        }
    }

    public void place(Piece p, int x, int y) {
        if (x >= 8 || y >= 8 || x < 0 || y < 0 || p == null) {
            return;
        }
        else {
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        if (x >= 8 || y >= 8 || x < 0 || y < 0) {
            System.out.println("Index Out of Bound!!!");
            return null;
        }
        else if (pieces[x][y] == null) {
            System.out.println("No piece at (x, y)!!!");
            return null;                
        }
        Piece removed = pieces[x][y];
        pieces[x][y] = null;
        return removed;
    }

    public boolean canEndTurn() {
        return canEndTurn;
    }

    public void endTurn() {
        player *= -1;
        canEndTurn = false;
    }

    public String winner() {
        boolean noFire = true;
        boolean noWater = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) noFire = false;
                    else if (!pieces[i][j].isFire()) noWater = false;
                }
            }
        }
        if (noWater && !noFire) return "Fire";
        else if (!noWater && noFire) return "Water";
        else if (noWater && noFire) return "No one";
        else return null;
    }
}