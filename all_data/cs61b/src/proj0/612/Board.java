public class Board {

    private boolean shouldBeEmpty;
    private Piece[][] Checkers = new Piece[8][8];
    private int player = 0;
    private boolean hasMoved = false;
    private boolean Selected = false;
    private int SelectedX = -1;
    private int SelectedY = -1;

    public Board(boolean empty) {
        this.shouldBeEmpty = empty;
        if (!this.shouldBeEmpty) {
            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    if ((j == 0) && (i % 2 == 0)) {
                        this.Checkers[i][j] = new Piece(true, this, i, j, "pawn");
                    } else if ((j == 1) && (i % 2 != 0)) {
                        this.Checkers[i][j] = new Piece(true, this, i, j, "shield");
                    } else if ((j == 2) && (i % 2 == 0)) {
                        this.Checkers[i][j] = new Piece(true, this, i, j, "bomb");
                    } else if ((j == 5) && (i % 2 != 0)) {
                        this.Checkers[i][j] = new Piece(false, this, i, j, "bomb");
                    } else if ((j == 6) && (i % 2 == 0)) {
                        this.Checkers[i][j] = new Piece(false, this, i, j, "shield");
                    } else if ((j == 7) && (i % 2 != 0)) {
                        this.Checkers[i][j] = new Piece(false, this, i, j, "pawn");
                    } else {
                        this.Checkers[i][j] = null;
                    }
                }
            }
        }            
    }

    private String getImage(Piece p) {
        if (p.isFire()) {
            if (p.isKing()) {
                if (p.isBomb()) {
                    return "img/bomb-fire-crowned.png";
                } else if (p.isShield()) {
                    return "img/shield-fire-crowned.png";
                } else {
                    return "img/pawn-fire-crowned.png";
                }
            }
            else if (p.isBomb()) {
                return "img/bomb-fire.png";
            } else if (p.isShield()) {
                return "img/shield-fire.png";
            } else {
                return "img/pawn-fire.png";
            }
        } else {
            if (p.isKing()) {
                if (p.isBomb()) {
                    return "img/bomb-water-crowned.png";
                } else if (p.isShield()) {
                    return "img/shield-water-crowned.png";
                } else {
                    return "img/pawn-water-crowned.png";
                }
            } else if (p.isBomb()) {
                return "img/bomb-water.png";
            } else if (p.isShield()) {
                return "img/shield-water.png";
            } else {
                return "img/pawn-water.png";
            }
        }
    }

    public boolean canEndTurn() {
        if (this.hasMoved == true) {
            return true;
        }
        return false;
    }

    public void endTurn() {
        if (this.player == 0) {
            this.player++;
        } else {
            this.player--;
        }
        this.hasMoved = false;
        this.Selected = false;
        this.SelectedX = -1;
        this.SelectedY = -1;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (this.pieceAt(i, j) != null) {
                    this.pieceAt(i, j).doneCapturing();
                }
            }
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (xf < 0 || xf > 7 || yf < 0 || yf > 7 || this.pieceAt(xi, yi) == null) {
            return false;
        } else {
            Piece p = this.pieceAt(xi, yi);
            Piece topright = this.pieceAt(xi+1, yi+1);
            Piece topright2 = this.pieceAt(xi+2, yi+2);
            Piece topleft = this.pieceAt(xi-1, yi+1);
            Piece topleft2 = this.pieceAt(xi-2, yi+2);
            Piece bottomright = this.pieceAt(xi+1, yi-1);
            Piece bottomright2 = this.pieceAt(xi+2, yi-2);
            Piece bottomleft = this.pieceAt(xi-1, yi-1);
            Piece bottomleft2 = this.pieceAt(xi-2, yi-2);
            boolean Fire = p.isFire();
            boolean King = p.isKing();
            if (Fire) {
                if ((topright == null) && (xf==xi+1) && (yf==yi+1) && !this.hasMoved) {
                    return true;
                } else if ((topleft == null) && (xf==xi-1) && (yf==yi+1) && !this.hasMoved) {
                    return true;
                } else if ((topright != null) && (!topright.isFire()) && (topright2 == null) && (xf==xi+2) && (yf==yi+2)) {
                    return true;
                } else if ((topleft != null) && (!topleft.isFire()) && (topleft2 == null) && (xf==xi-2) && (yf==yi+2)) {
                    return true;
                } else if (King && (bottomright == null) && (xf==xi+1) && (yf==yi-1) && !this.hasMoved) {
                    return true;
                } else if (King && (bottomleft == null) && (xf==xi-1) && (yf==yi-1) && !this.hasMoved) {
                    return true;
                } else if (King && (bottomright != null) && (!bottomright.isFire()) && (bottomright2 == null) && (xf==xi+2) && (yf==yi-2)) {
                    return true;
                } else if (King && (bottomleft != null) && (!bottomleft.isFire()) && (bottomleft2 == null) && (xf==xi-2) && (yf==yi-2)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if ((bottomright == null) && (xf==xi+1) && (yf==yi-1) && !this.hasMoved) {
                    return true;
                } else if ((bottomleft == null) && (xf==xi-1) && (yf==yi-1) && !this.hasMoved) {
                    return true;
                } else if ((bottomright != null) && (bottomright.isFire()) && (bottomright2 == null) && (xf==xi+2) && (yf==yi-2)) {
                    return true;
                } else if ((bottomleft != null) && (bottomleft.isFire()) && (bottomleft2 == null) && (xf==xi-2) && (yf==yi-2)) {
                    return true;
                } else if (King && (topright == null) && (xf==xi+1) && (yf==yi+1) && !this.hasMoved) {
                    return true;
                } else if (King && (topleft == null) && (xf==xi-1) && (yf==yi+1) && !this.hasMoved) {
                    return true;
                } else if (King && (topright != null) && (topright.isFire()) && (topright2 == null) && (xf==xi+2) && (yf==yi+2)) {
                    return true;
                } else if (King && (topleft != null) && (topleft.isFire()) && (topleft2 == null) && (xf==xi-2) && (yf==yi+2)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public boolean canSelect(int x, int y) {
        Piece p = this.pieceAt(x, y);
        if (p != null) {
            if (p.side() != this.player) {
                return false;
            } else {
                if (!this.hasMoved) {
                    return true;
                }
                return false;
            }
        } else {
            if (!this.hasMoved && this.Selected && this.validMove(this.SelectedX, this.SelectedY, x, y)) {
                return true;
            } else if (this.hasMoved && this.validMove(this.SelectedX, this.SelectedY, x, y)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void select(int x, int y) {
        Piece p = this.pieceAt(x, y);
        if ((p == null) && this.Selected) {
            this.Checkers[this.SelectedX][this.SelectedY].move(x, y);
            this.SelectedX = x;
            this.SelectedY = y;
            this.hasMoved = true;
        } else if (p != null) {
            this.Selected = true;
            this.SelectedX = x;
            this.SelectedY = y;
        }
    }

    private void GUIselect(int x, int y) {
        if (this.Selected) {
            this.unselect(SelectedX, SelectedY);
        }
        if (this.pieceAt(x, y) == null) {
            return;
        } else {
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
            StdDrawPlus.picture(x + 0.5, y + 0.5, this.getImage(this.pieceAt(x, y)), 1, 1);
        }
    }

    private void unselect(int x, int y) {
        Piece p = this.pieceAt(x, y);
        if (p == null) {
            return;
        } else {
            if ((x + y) % 2 == 0) {
                StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            } else {
                StdDrawPlus.setPenColor(StdDrawPlus.RED);
            }
            StdDrawPlus.filledSquare(x + 0.5, y + 0.5, 0.5);
            StdDrawPlus.picture(x + 0.5, y + 0.5, this.getImage(this.pieceAt(x, y)), 1, 1);
        }
    }

    public Piece pieceAt(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return null;
        } else {
            return this.Checkers[x][y];
        }
    }

    public void place(Piece p, int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7 || p == null) {
            return;
        } else {
            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    if (this.pieceAt(i, j) == p) {
                        this.remove(i, j);
                    }
                }
            }
            this.Checkers[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("Can't Remove: Out of Bounds");
            return null;
        } else if (this.pieceAt(x, y) == null) {
            System.out.println("No piece to be removed");
            return null;
        } else {
            Piece removed = this.pieceAt(x, y);
            this.Checkers[x][y] = null;
            return removed;
        }
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                if (!this.shouldBeEmpty) {
                    if ((j == 0) && (i % 2 == 0)) {
                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
                    } else if ((j == 1) && (i % 2 != 0)) {
                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
                    } else if ((j == 2) && (i % 2 == 0)) {
                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
                    } else if ((j == 5) && (i % 2 != 0)) {
                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
                    } else if ((j == 6) && (i % 2 == 0)) {
                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
                    } else if ((j == 7) && (i % 2 != 0)) {
                        StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
                    }
                }
            }
        }
    }

    private void DRAW() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                if (!this.shouldBeEmpty) {
                    if (this.pieceAt(i, j) != null) {
                        StdDrawPlus.picture(i + 0.5, j + 0.5, this.getImage(this.pieceAt(i, j)), 1, 1);
                    }
                }
            }
        }
    }

    public String winner() {
        int Firecount = 0;
        int Watercount = 0;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if (this.Checkers[i][j] == null) {
                    continue;
                } else if (this.Checkers[i][j].isFire()) {
                    Firecount++;
                } else {
                    Watercount++;
                }
            }
        }
        if ((Firecount > 0) && (Watercount > 0)) {
            return null;
        } else if ((Firecount > 0) && (Watercount == 0)) {
            return "Fire";
        } else if ((Firecount == 0) && (Watercount > 0)) {
            return "Water";
        } else {
            return "No one";
        }
    }

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);
        b.drawBoard(8);
        while (b.winner() == null) {
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x, y)) {
                    b.select(x, y);
                    b.DRAW();
                    b.GUIselect(x, y);
                }
            }
            StdDrawPlus.show(10);
            if (StdDrawPlus.isSpacePressed() && b.canEndTurn()) {
                b.endTurn();
                for(int i = 0; i < 8; i++) {
                    for(int j = 0; j < 8; j++) {
                        b.unselect(i, j);
                    }
                }
            }
        }
        return;
    }
}
