public class Board {

	private Piece[][] pieces;
    private Piece selected;
    private int x0;
    private int y0;
    private boolean moved;
    private int turn;

	public static void main(String args[]) {
        Board b = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        b.drawBoard(8);
        while (true) {
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                Piece p = b.pieces[x][y];
                if (b.canSelect(x, y)) {
                    b.select(x, y);
                    if (p != null) {
                        if (b.selected != null) b.drawBoard(8);
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                        b.drawPieces(p, x, y);
                    } else {
                        b.drawBoard(8);
                    }
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
            String winner = b.winner();
            if (winner != null) {
                System.out.println("Winner is " + winner);
                return;
            }
        }
	}

	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                Piece p = pieces[i][j];
                if (p != null) drawPieces(p, i, j);
            }
        }
    }

    private void drawPieces(Piece p, int i, int j) {
        if (p.isFire()) {
            if (p.isBomb()) {
                if (p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
            } else if (p.isShield()) {
                if (p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                else StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
            } else {
                if (p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
            }
        } else {
            if (p.isBomb()) {
                if (p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
            } else if (p.isShield()) {
                if (p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                else StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
            } else {
                if (p.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
            }
        }
    }

	public Board(boolean shouldBeEmpty) {
        turn = 0;
        moved = false;
        selected = null;
		pieces = new Piece[8][8];
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j ++) {
                pieces[i][j] = null;
            }
        }
        if (!shouldBeEmpty) {
    		for (int i = 0; i < 8; i += 2) {
    			pieces[i][0] = new Piece(true, this, i, 0, "pawn");
    			pieces[i][2] = new Piece(true, this, i, 2, "bomb");
    			pieces[i][6] = new Piece(false, this, i, 6, "shield");
    		}
    		for (int j = 1; j < 8; j += 2) {
    			pieces[j][1] = new Piece(true, this, j, 1, "shield");
    			pieces[j][5] = new Piece(false, this, j, 5, "bomb");
    			pieces[j][7] = new Piece(false, this, j, 7, "pawn");
    		}
        }
	}

	public Piece pieceAt(int x, int y) {
        if (!isWithinBounds(x, y)) return null;
        return pieces[x][y];
	}

    private boolean isWithinBounds(int x, int y) {
        return (x >= 0) && (y >= 0) && (x < 8) && (x < 8);
    }

    public boolean canSelect(int x, int y) {
        if (!isWithinBounds(x, y)) return false;
        if ((pieces[x][y] != null) && (pieces[x][y].side() == turn)) return ((selected == null) || (!moved));
        else return ((selected != null) && (validMove(selected, x, y)) && 
                    ((!moved) || (selected.hasCaptured()) && canCapture(selected, x0, y0, x, y)));
    }

    private boolean canCapture(Piece p, int x0, int y0, int x, int y) {
        int dx = x - x0;
        int dy = y - y0;
        return ((((p.isFire()) && (dy == 2)) || 
               ((!p.isFire()) && (dy == -2)) || 
               ((p.isKing()) && (Math.abs(dy) == 2))) && 
               (Math.abs(dx) == 2) && (pieces[x0 + dx / 2][y0 + dy / 2] != null) && (pieces[x0 + dx / 2][y0 + dy / 2].side() != turn));
    }

    private boolean validMove(Piece p, int x, int y) {
        int dx = x - x0;
        int dy = y - y0;
        if ((((p.isFire()) && (dy == 1)) || ((!p.isFire()) && (dy == -1))) && (Math.abs(dx) == 1)
            && (pieces[x][y] == null)) 
            return true;
        else if ((p.isKing()) && (Math.abs(dy) == 1) && (Math.abs(dx) == 1)) return true;
        else return canCapture(p, x0, y0, x, y);
    }

    public void select(int x, int y) {
        Piece p = pieces[x][y];
        if (p != null) {
            selected = p;
            x0 = x; y0 = y;
        } else {
            selected.move(x, y);
            x0 = x; y0 = y;
            moved = true;
        }
    }

    public void place(Piece p, int x, int y) {
        if ((isWithinBounds(x, y)) && (p != null))
            pieces[x][y] = p;
    }

    public Piece remove(int x, int y) {
        if (!isWithinBounds(x, y)) {
            System.out.println("Position out of bounds");
            return null;
        } else if (pieces[x][y] == null) {
            System.out.println("Selected position is empty");
            return null;
        } else {
            Piece removed = pieces[x][y];
            pieces[x][y] = null;
            return removed;
        }
    }

    public boolean canEndTurn() {
        return moved;
    }

    public void endTurn() {
        turn = 1 - turn;
        moved = false;
        if (selected.hasCaptured()) {
            selected.doneCapturing();
        }
        selected = null;
    }

    public String winner() {
        int fire = 0;
        int water = 0;
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j ++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) fire += 1;
                    else water += 1;
                }
            }
        }
        if ((fire == 0) && (water != 0)) return "water";
        if ((water == 0) && (fire != 0)) return "Fire";
        if ((water == 0) && (fire == 0)) return "No one";
        return null;
    }
}