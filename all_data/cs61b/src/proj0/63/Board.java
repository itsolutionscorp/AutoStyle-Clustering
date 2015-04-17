/* Work cited:
 * http://findbugs.sourceforge.net/bugDescriptions.html#URF_UNREAD_FIELD
 * I used this website to fix the errors from my FINDBUGS. I wasn't sure
 * what each of the error messages meant and how to fix them until I looked
 * it up.
 */

public class Board {
	private Piece[][] pieces;
    private int x1 = -1;
    private int y1 = -1;
    private boolean fireTurn = true;
    private boolean moved = false;

	public static void main(String[] args) {
        Board b = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        b.drawBoard();
        while(b.winner() == null) {
            b.play();
            StdDrawPlus.show(100);
        }
	}

	public Board(boolean shouldBeEmpty) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        pieces = new Piece[8][8];
    	if (shouldBeEmpty) {
    		drawBoard();
    	} else {
    		for (int i = 0; i < 8; i++) {
    			for (int j = 0; j < 3; j++) {
    				// draw fire pieces
    				if (i % 2 == 0 && j == 0) {
    					pieces[i][j] = new Piece(true, this, i, j, "pawn");
    				} else if (i % 2 != 0 && j == 1) {
    					pieces[i][j] = new Piece(true, this, i, j, "shield");
    				} else if (i % 2 == 0 && j == 2) {
    					pieces[i][j] = new Piece(true, this, i, j, "bomb");
    				}
    			}
    			for (int k = 5; k < 8; k++) {
    				// draw water pieces
    				if (i % 2 != 0 && k == 7) {
    					pieces[i][k] = new Piece(false, this, i, k, "pawn");
    				} else if (i % 2 == 0 && k == 6) {
    					pieces[i][k] = new Piece(false, this, i, k, "shield");
    				} else if (i % 2 != 0 && k == 5) {
    					pieces[i][k] = new Piece(false, this, i, k, "bomb");
    				}
    			}
    		}
    	}
    }

    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, selectImg(pieces[i][j]), 1, 1);
                }
            }
        }
    }

    private String selectImg(Piece p) {
        if (p.isKing()) {
            if (p.isFire()) {
                if (p.isBomb()) {
                    return "img/bomb-fire-crowned.png";
                } else if (p.isShield()) {
                    return "img/shield-fire-crowned.png";
                } else {
                    return "img/pawn-fire-crowned.png";
                }
            } else {
                if (p.isBomb()) {
                    return "img/bomb-water-crowned.png";
                } else if (p.isShield()) {
                    return "img/shield-water-crowned.png";
                } else {
                    return "img/pawn-water-crowned.png";
                }
            }
        }
        if (p.isFire()) {
            if (p.isBomb()) {
                return "img/bomb-fire.png";
            } else if (p.isShield()) {
                return "img/shield-fire.png";
            } else {
                return "img/pawn-fire.png";
            }
        } else {
            if (p.isBomb()) {
                return "img/bomb-water.png";
            } else if (p.isShield()) {
                return "img/shield-water.png";
            } else {
                return "img/pawn-water.png";
            }
        }
    }

    private void play() {
        if (StdDrawPlus.mousePressed()) {
            int x = (int) StdDrawPlus.mouseX();
            int y = (int) StdDrawPlus.mouseY();
            if (canSelect(x, y)) {
                select(x, y);
            }
        } else if (StdDrawPlus.isSpacePressed()) {
            if (canEndTurn()) {
                endTurn();
                drawBoard();
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        if ((x < 8 && x >= 0) && (y < 8 && y >= 0)) {
            return pieces[x][y];
        }
        return null;
    }

    public boolean canSelect(int x, int y) {
        if (pieces[x][y] != null && !moved) {
            if ((fireTurn && (pieces[x][y].isFire())) || (!fireTurn && (!pieces[x][y].isFire()))) {
                if ((x1 != -1) && (y1 != -1) && !moved) {
                    x1 = -1;
                    y1 = -1;
                }
                return (x1 == -1) && (y1 == -1);
            }
            return false;
        } else {
            if (moved && pieces[x1][y1].hasCaptured()) {
                return validCapture(x1, y1, x, y);
            }
            return !moved && validMove(x1, y1, x, y);
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if ((xi == -1) && (yi == -1)) {
            return false;
        }
        if (pieces[xi][yi].isFire() && !pieces[xi][yi].isKing()) {
            if (yf < yi) {
                return false;
            }
        } else if (!pieces[xi][yi].isFire() && !pieces[xi][yi].isKing()) {
            if (yf > yi) {
                return false;
            }
        }
        if (validCapture(x1, y1, xf, yf)) {
            return true;
        } else if ((Math.abs(xf - xi) == 1) && (Math.abs(yf - yi) == 1)) {
            return true;
        }
        return false;
    }

    private boolean validCapture(int xi, int yi, int xf, int yf) {
        int midX = (xi + xf) >>> 1;
        int midY = (yi + yf) >>> 1;
        if ((Math.abs(xf - xi) == 2) && (Math.abs(yf - yi) == 2)) {
            if (pieceAt(midX, midY).side() != pieceAt(xi, yi).side()) {
                if (pieces[midX][midY] != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public void select(int x, int y) {
        if ((x1 == -1) && (y1 == -1)) {
            x1 = x;
            y1 = y;
            selectGUI(x, y);
        } else {
            pieces[x1][y1].move(x, y);
            selectGUI(x, y);
            moved = true;
            x1 = x;
            y1 = y;
        }
    }

    private void selectGUI(int x, int y) {
        drawBoard();
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        if (pieces[x][y] != null) {
            StdDrawPlus.picture(x + .5, y + .5, selectImg(pieces[x][y]), 1, 1);
        }
    }

    public void place(Piece p, int x, int y) {
        if ((x < 8) || (y < 8) || (p != null)) {
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
    	if ((x > 8) || (y > 8)) {
            System.out.println("Error: out of bounds");
            return null;
        } else if (pieces[x][y] == null) {
            System.out.println("Error: no piece at location");
            return null;
        } else {
            Piece remove = pieces[x][y];
            pieces[x][y] = null;
            drawBoard();
            return remove;
        }
    }

    public boolean canEndTurn() {
    	return moved;
    }

    public void endTurn() {
        if (fireTurn) {
            fireTurn = false;
        } else {
            fireTurn = true;
        }
        if (pieces[x1][y1] != null) {
            pieces[x1][y1].doneCapturing();
        }
        x1 = y1 = -1;
        moved = false;
    }

    public String winner() {
        boolean fire = false;
        boolean water = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        fire = true;
                    } else {
                        water = true;
                    }
                }
            }
        }

        if (fire && water) {
            return null;
        } else if (fire && !water) {
            return "Fire";
        } else if (!fire && water) {
            return "Water";
        } else {
            return "No one";
        }
    }
}