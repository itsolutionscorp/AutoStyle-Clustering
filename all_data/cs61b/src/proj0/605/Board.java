public class Board {
    private static Piece[][] pieces;
    private Piece selected; //recently selected piece
    private boolean status; // player selected or not
    private boolean moved; // piece moved or not
    private boolean justKing;
    private boolean exploded;
    private int side; // turn now
    private int totalF;
    private int totalW;
    private int selectedX;
    private int selectedY;

    public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty) {
            pieces = new Piece[8][8];
        } else {
            side = 0;
            pieces = new Piece[8][8];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 0) {
                        if (j == 0) pieces[i][j] = new Piece(true, this, i, j, "pawn");
                        else if (j == 1) pieces[i][j] = new Piece(true, this, i, j, "shield");
                        else if (j == 2) pieces[i][j] = new Piece(true, this, i, j, "bomb");
                        else if (j == 5) pieces[i][j] = new Piece(false, this, i, j, "bomb");
                        else if (j == 6) pieces[i][j] = new Piece(false, this, i, j, "shield");
                        else if (j == 7) pieces[i][j] = new Piece(false, this, i, j, "pawn");
                    }
                }
            }
        }
    }
    private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
        for (int a = 0; a < N; a++) {
            for (int b = 0; b < N; b++) {
                if (pieces[a][b] != null) {
                    if (pieces[a][b].isFire()) {
                        if (pieces[a][b].isKing()) {                        
                            if (pieces[a][b].isShield()) {
                                StdDrawPlus.picture(a + .5, b + .5, "img/shield-fire-crowned.png", 1, 1);
                            } else if (pieces[a][b].isBomb()) {
                                StdDrawPlus.picture(a + .5, b + .5, "img/bomb-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(a + .5, b + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                        } else            
                        if (pieces[a][b].isShield()) {
                            StdDrawPlus.picture(a + .5, b + .5, "img/shield-fire.png", 1, 1);
                        } else if (pieces[a][b].isBomb()) {
                            StdDrawPlus.picture(a + .5, b + .5, "img/bomb-fire.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(a + .5, b + .5, "img/pawn-fire.png", 1, 1);
                        }
                    }
                        if (pieces[a][b].isFire() != true) {
                            if (pieces[a][b].isKing()) {
                                if (pieces[a][b].isBomb()) {
                                    StdDrawPlus.picture(a + .5, b + .5, "img/bomb-water-crowned.png", 1, 1);
                                } else if (pieces[a][b].isShield()) {
                                    StdDrawPlus.picture(a + .5, b + .5, "img/shield-water-crowned.png", 1, 1);
                                } else {
                                    StdDrawPlus.picture(a + .5, b + .5, "img/pawn-water-crowned.png", 1, 1);
                                }
                            }
                        else if (pieces[a][b].isBomb()) {
                            StdDrawPlus.picture(a + .5, b + .5, "img/bomb-water.png", 1, 1);
                        } else if (pieces[a][b].isShield()) {
                            StdDrawPlus.picture(a + .5, b + .5, "img/shield-water.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(a + .5, b + .5, "img/pawn-water.png", 1, 1);
                        }
                    }
                }
            }
        }
    }
    public Piece pieceAt(int x, int y) {
        if ((x > 7 || y > 7) || pieces[x][y] == null) {
            return null;
        }
        else return pieces[x][y];
    }
    public void place(Piece p, int x, int y) {
        if ((x <= 7 && y <= 7) || (p != null)) {
            if (pieceAt(x, y) != null) {
                this.remove(x, y);
                pieces[x][y] = p;
            } else {
                pieces[x][y] = p;
            }
        }
    }
    public Piece remove(int x, int y) {
        if ((x > 7 || y > 7) || (pieceAt(x, y) == null)) {
            System.out.println("Out of bounds or no piece to remove");
            return null;
        } else {
            Piece removed = pieces[x][y];
            pieces[x][y] = null;
            return removed;
        }
    }
    public boolean canSelect(int x, int y) {
        if (pieceAt(x, y) != null) {
            if (moved != true && exploded != true) {
                if (pieceAt(x, y).isKing() && justKing) {
                    return false;
                } else if (pieceAt(x, y).side() == side) {
                    if (status != true || (selected != null && moved == false)) {
                        return true;
                    } else return false;
                } else return false;
            } else return false;
        } else if (status && moved && (((selectedX + 1 == x) && (selectedY + 1 == y)) || ((selectedX + 1 == x) && (selectedY - 1 == y))
                  || ((selectedX - 1 == x) && (selectedY - 1 == y)) || ((selectedX - 1 == x) && (selectedY + 1 == y)))) {
                return false;
            } else if ((status && moved == false && validMove(selectedX, selectedY, x, y)) || 
                   (status && moved && validMove(selectedX, selectedY, x, y))) {
                return true;
            } else return false; 
    }
    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (pieceAt(xi, yi) != null) {
            if (pieceAt(xi, yi).isKing()) {
                if (justKing == false) {
                    if (((xi > 0 && xi < 7) && (yi > 0 && yi < 7)) && 
                        (((xf == xi + 1) && (yf == yi + 1)) || ((xf == xi - 1) && (yf == yi - 1)) 
                            || ((xf == xi - 1) && (yf == yi + 1)) || ((xf == xi + 1) && (yf == yi - 1)))) {
                        return true;
                    } else if ((xi == 0) && (yi != 0) && (xf == xi + 1) && ((yf == yi + 1) || (yf == yi - 1))) {
                        return true;
                    } else if ((xi == 7) && (yi != 7) && (xf == xi - 1) && ((yf == yi + 1) || (yf == yi - 1))) {
                        return true;
                    } else if ((yi == 0) && (xi != 0) && (yf == yi + 1) && ((xf == xi + 1) || (xf == xi - 1))) {
                        return true;
                    } else if ((yi == 7) && (xi != 7) && (yf == yi - 1) && ((xf == xi - 1) || (xf == xi + 1))) {
                        return true;
                    } else if ((xi == 0) && (yi == 0) && (yf == yi + 1) && (xf == xi + 1)) {
                        return true;
                    } else if ((xi == 7) && (yi == 7) && (yf == yi - 1) && (yf == yi - 1)) {
                        return true;
                    } else if ((xi > 1 && xi < 6 && yi > 1 && yi < 6) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && ((xf == xi + 2) && (yf == yi + 2)) ||
                           ((xf == xi - 2) && (yf == yi - 2)) || ((xf == xi - 2) && (yf == yi + 2)) 
                           || ((xf == xi + 2) && (yf == yi - 2))) {
                        return true;
                } else if ((xi > 1 && xi < 6 && yi >= 0 && yi < 2) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (((xf == xi - 2) && (yf == yi + 2)) || ((xf == xi + 2) && (yf == yi + 2)))) {
                    return true;
                } else if ((xi > 1 && xi < 6 && yi <= 7 && yi > 5) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (((xf == xi + 2) && (yf == yi - 2)) || ((xf == xi - 2) && (yf == yi - 2)))) {
                    return true;
                } else if ((xi >= 0 && xi < 2 && yi > 1 && yi < 6) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (xf == xi + 2) && ((yf == yi + 2) || (yf == yi - 2))) {
                    return true;
                } else if ((xi > 5 && xi <= 7 && yi > 1 && yi < 6) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (xf == xi - 2) && ((yf == yi + 2) || (yf == yi - 2))) {
                    return true;
                } else if ((xi >= 0 && xi < 2 && yi >= 0 && yi < 2) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (xf == xi + 2) && (yf == yi + 2)) {
                    return true;
                } else if ((xi >= 0 && xi < 2 && yi > 5 && yi <= 7) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (xf == xi + 2) && (yf == yi - 2)) {
                    return true;
                } else if ((xi > 5 && xi <= 7 && yi >= 0 && yi < 2) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (xf == xi - 2) && (yf == yi + 2)) {
                    return true;
                } else if ((xi > 5 && xi <= 7 && yi > 5 && yi <= 7) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (xf == xi - 2) && (yf == yi - 2)) {
                    return true;
                } else return false;
                } else {
                    if ((xi > 1 && xi < 6 && yi > 1 && yi < 6) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && ((xf == xi + 2) && (yf == yi + 2)) ||
                           ((xf == xi - 2) && (yf == yi - 2)) || ((xf == xi - 2) && (yf == yi + 2)) 
                           || ((xf == xi + 2) && (yf == yi - 2))) {
                    return true;
                } else if ((xi > 1 && xi < 6 && yi >= 0 && yi < 2) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (((xf == xi - 2) && (yf == yi + 2)) || ((xf == xi + 2) && (yf == yi + 2)))) {
                    return true;
                } else if ((xi > 1 && xi < 6 && yi <= 7 && yi > 5) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (((xf == xi + 2) && (yf == yi - 2)) || ((xf == xi - 2) && (yf == yi - 2)))) {
                    return true;
                } else if ((xi >= 0 && xi < 2 && yi > 1 && yi < 6) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (xf == xi + 2) && ((yf == yi + 2) || (yf == yi - 2))) {
                    return true;
                } else if ((xi > 5 && xi <= 7 && yi > 1 && yi < 6) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (xf == xi - 2) && ((yf == yi + 2) || (yf == yi - 2))) {
                    return true;
                } else if ((xi >= 0 && xi < 2 && yi >= 0 && yi < 2) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (xf == xi + 2) && (yf == yi + 2)) {
                    return true;
                } else if ((xi >= 0 && xi < 2 && yi > 5 && yi <= 7) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (xf == xi + 2) && (yf == yi - 2)) {
                    return true;
                } else if ((xi > 5 && xi <= 7 && yi >= 0 && yi < 2) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (xf == xi - 2) && (yf == yi + 2)) {
                    return true;
                } else if ((xi > 5 && xi <= 7 && yi > 5 && yi <= 7) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (xf == xi - 2) && (yf == yi - 2)) {
                    return true;
                } return false; 
                }
        } else if (pieceAt(xi, yi).isFire() && yi < yf) {
                if ((xi > 0 && xi < 7) &&
                    ((xf == (xi - 1) && yf == (yi + 1)) || (xf == (xi + 1) && yf == (yi + 1)))) {
                        return true;
                    } else if ((xi == 0) && (xf == (xi + 1) && yf == (yi + 1))) {
                        return true;
                    } else if ((xi == 7) && (xf == (xi - 1) && yf == (yi + 1))) {
                        return true;
                    } else if ((xi > 1 && xi < 6) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && 
                             ((xf == (xi - 2) && yf == (yi + 2)) || (xf == (xi + 2) && yf == (yi + 2)))) {
                            if (pieceAt(xi - 1, yi + 1) != null && (pieceAt(xi - 1, yi + 1).isFire() == false)) {
                                return true;
                            } else if (pieceAt(xi + 1, yi + 1) != null && (pieceAt(xi + 1, yi + 1).isFire() == false)) {
                                return true;
                            } else return false;           
                    } else if ((xi >= 0 && xi <= 1) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && 
                               (xf == (xi + 2) && yf == (yi + 2)) &&
                               (pieceAt(xi + 1, yi + 1).isFire() == false)) {
                                return true;
                    } else if ((xi >= 6 && xi <= 7) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && 
                               (xf == (xi - 2) && yf == (yi + 2)) &&
                               (pieceAt(xi - 1, yi + 1).isFire() == false)) {
                                return true;
                    } else return false;
            } else if (pieceAt(xi, yi).isFire() == false && yi > yf) {
                        if ((xi > 0 && xi < 7) &&
                            ((xf == (xi - 1) && yf == (yi - 1)) || (xf == (xi + 1) && yf == (yi - 1)))) {
                            return true;
                        } else if ((xi == 0) && (xf == (xi + 1) && yf == (yi - 1))) {
                            return true;
                        } else if ((xi == 7) && (xf == (xi - 1) && yf == (yi - 1))) {
                            return true;
                        } else if ((xi > 1 && xi < 6)) {
                            if (pieceAt(xi - 1, yi - 1) != null && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (xf == (xi - 2) && yf == (yi - 2)) && pieceAt(xi - 1, yi - 1).isFire()) {
                                return true;
                            } else if (pieceAt(xi + 1, yi - 1) != null && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && (xf == (xi + 2) && yf == (yi - 2))) {
                                return true;
                            } else return false;
                        } else if ((xi >= 0 && xi <= 1) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && 
                                   (xf == (xi + 2) && yf == (yi - 2)) &&
                                   (pieceAt(xi + 1, yi - 1).isFire())) {
                                    return true;
                        } else if ((xi >= 6 && xi <= 7) && (pieceAt((xf + xi) / 2, (yf + yi) / 2) != null) && 
                                   (xf == (xi - 2) && yf == (yi - 2)) &&
                                   (pieceAt(xi - 1, yi - 1).isFire())) {
                                    return true;
                        } else return false;
            } else return false;
        } else return false;
    }
    public boolean canEndTurn() {
        if (selected != null) {
            if (moved || selected.hasCaptured()) {
                return true;
            } else return false;
        } else return false;
    }
    public void endTurn() {
        if (this.canEndTurn()) {
            if (selected.side() == 0) {
                side = 1;
            } else {
                side = 0;
            }
            selected.doneCapturing();
            moved = false;
            status = false;
            selected = null;
            justKing = false;
            exploded = false;
        }
    }
    public void select(int x, int y) {
            if (pieceAt(x, y) != null) {
                selected = pieceAt(x, y);
                selectedX = x;
                selectedY = y;                    
                status = true;
            } else { 
                selected.move(x, y);
                selectedX = x;
                selectedY = y;
                moved = true;
                selected.doneCapturing();
                if (selected.isKing() && ((selected.isFire() && y == 7) || (selected.isFire() != true && y == 0))) {
                    justKing = true;
                }
                if (selected.isBomb()) {
                    exploded = true;
                }
            }
    }
    public String winner() {
        totalF = 0;
        totalW = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieceAt(i, j) == null) {
                } else if (pieceAt(i, j).isFire()) {
                    totalF = totalF + 1;
                } else {
                    totalW = totalW + 1;
                }
            }
        }
        if (totalW == 0 || totalF == 0) { 
            if (totalW + totalF == 0) {
                return "No one";
            } if (totalF > totalW) {
                return "Fire";
            } if (totalF < totalW) {
                return "Water";
            } 
        } 
        return null;
    }
    public static void main(String[] args) {
        Board b = new Board(false);        
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        while(true) {
            drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int cordX = ((int) x);
                int cordY = ((int) y);
                if (b.canSelect(cordX, cordY)) {
                    b.select(cordX, cordY);
                }
            } else if (StdDrawPlus.isSpacePressed()) {
                b.endTurn();
            }            
            StdDrawPlus.show(100);
            if(b.winner() != null){
                break;
            }
    }
    System.out.println(b.winner());
}
}