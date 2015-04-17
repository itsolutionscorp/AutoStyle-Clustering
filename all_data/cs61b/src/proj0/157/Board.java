/** @author zzy
 */

public class Board {
    private int i, j, countFire, countWater;
    private Piece[][] pieceMatrix = new Piece[8][8];
    private int[] selected = new int[2];
    private int turn;
    private boolean moved;
    private boolean gameEnded;

    public Board(boolean shouldBeEmpty) {
        selected[0] = -1;
        selected[1] = -1;
        turn = 0;
        moved = false;
        gameEnded = false;
        if (!shouldBeEmpty) {
            for (i = 0; i < 8; i += 1) {
                for (j = 0; j < 8; j += 1) {
                    if (j == 0 && i % 2 == 0) {
                        this.pieceMatrix[i][j] = new Piece(true, this, i, j,
                                                           "pawn");
                    }
                    if (j == 1 && i % 2 != 0) {
                        this.pieceMatrix[i][j] = new Piece(true, this, i, j,
                                                           "shield");
                    }
                    if (j == 2 && i % 2 == 0) {
                        this.pieceMatrix[i][j] = new Piece(true, this, i, j,
                                                           "bomb");
                    }
                    if (j == 5 && i % 2 != 0) {
                        this.pieceMatrix[i][j] = new Piece(false, this, i, j,
                                                           "bomb");
                    }
                    if (j == 6 && i % 2 == 0) {
                        this.pieceMatrix[i][j] = new Piece(false, this, i, j,
                                                           "shield");
                    }
                    if (j == 7 && i % 2 != 0) {
                        this.pieceMatrix[i][j] = new Piece(false, this, i, j,
                                                           "pawn");
                    }
                }
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            return null;
        } else {
            return this.pieceMatrix[x][y];
        }
    }

    public boolean canSelect(int x, int y) {
        if (this.gameEnded) {
            return false;
        }
        if (this.selected[0] == -1) {
            if (this.moved) {
                return false;
            } else if (this.pieceAt(x, y) != null) {
                if (this.pieceAt(x, y).side() == this.turn) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (this.moved) {
            return this.validMove(this.selected[0], this.selected[1], x, y);
        } else {
            if (this.validMove(this.selected[0], this.selected[1], x, y)) {
                return true;
            } else if (this.pieceAt(x, y) != null) {
                if (this.pieceAt(x, y).side() == this.turn) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        int xDis = xf - xi;
        int yDis = yf - yi;
        Piece tempPiece = this.pieceAt(xi, yi);
        if (tempPiece == null) {
            return false;
        } else if (xf > 7 || xf < 0 || yf > 7 || yf < 0) {
            return false;
        } else if (!tempPiece.hasCaptured() && this.moved) {
            return false;
        } else if (pieceAt(xf, yf) != null) {
            return false;
        } else if (tempPiece.isFire()) {
            if (tempPiece.isKing()) {
                if ((xDis == 1 || xDis == -1) && (yDis == 1 || yDis == -1)) {
                    if (tempPiece.hasCaptured()) {
                        return false;
                    } else {
                        return true;
                    }
                } else if ((xDis == 2 || xDis == -2) &&
                           (yDis == 2 || yDis == -2)) {
                    if (!tempPiece.hasCaptured() && this.moved) {
                        return false;
                    } else {
                        Piece midPiece = this.pieceAt((xi + xf) / 2, (yi + yf) / 2);
                        if (midPiece == null) {
                            return false;
                        } else if (!midPiece.isFire()) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            } else {
                if ((xDis == 1 || xDis == -1) && yDis == 1) {
                    if (tempPiece.hasCaptured()) {
                        return false;
                    } else {
                        return true;
                    }
                } else if ((xDis == 2 || xDis == -2) && yDis == 2) {
                    if (!tempPiece.hasCaptured() && this.moved) {
                        return false;
                    } else {
                        Piece midPiece = this.pieceAt((xi + xf) / 2, (yi + yf) / 2);
                        if (midPiece == null) {
                            return false;
                        } else if (!midPiece.isFire()) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
        } else { if (tempPiece.isKing()) {
                if ((xDis == 1 || xDis == -1) && (yDis == 1 || yDis == -1)) {
                    if (tempPiece.hasCaptured()) {
                        return false;
                    } else {
                        return true;
                    }
                } else if ((xDis == 2 || xDis == -2) &&
                           (yDis == 2 || yDis == -2)) {
                    if (!tempPiece.hasCaptured() && this.moved) {
                        return false;
                    } else {
                        Piece midPiece = this.pieceAt((xi + xf) / 2, (yi + yf) / 2);
                        if (midPiece == null) {
                            return false;
                        } else if (midPiece.isFire()) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            } else {
                if ((xDis == 1 || xDis == -1) && yDis == -1) {
                    if (tempPiece.hasCaptured()) {
                        return false;
                    } else {
                        return true;
                    }
                } else if ((xDis == 2 || xDis == -2) && yDis == -2) {
                    if (!tempPiece.hasCaptured() && this.moved) {
                        return false;
                    } else {
                        Piece midPiece = this.pieceAt((xi + xf) / 2, (yi + yf) / 2);
                        if (midPiece == null) {
                            return false;
                        } else if (midPiece.isFire()) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                } else {
                    return false;
                }
            }
        }
    }

    public void select(int x, int y) {
        if (this.pieceAt(x, y) != null) {
            this.selected[0] = x;
            this.selected[1] = y;
        } else {
            this.pieceAt(selected[0], selected[1]).move(x, y);
            this.moved = true;
            this.selected[0] = x;
            this.selected[1] = y;
            if (this.pieceAt(x, y) == null) {
                this.selected[0] = -1;
                this.selected[1] = -1;
            }
        }
    }

    public void place(Piece p, int x, int y) {
        if (x < 8 && x > -1 && y < 8 && y > -1) {
            this.pieceMatrix[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("Out of bound!");
            return null;
        } else {
            Piece tempPiece = this.pieceAt(x, y);
            if (tempPiece == null) {
                System.out.println("Nothing to remove here!");
                return null;
            } else {
                this.pieceMatrix[x][y] = null;
                return tempPiece;
            }
        }
    }

    public boolean canEndTurn() {
        return this.moved;
    }

    public void endTurn() {
        if (this.selected[0] != -1) {
            this.pieceAt(this.selected[0], 
                         this.selected[1]).doneCapturing();
                this.selected[0] = -1;
                this.selected[1] = -1;
        }
        this.turn = 1 - this.turn;
        this.moved = false;   
    }

    public String winner() {
        this.countFire = 0;
        this.countWater = 0;
        for(i = 0; i < 8; i += 1) {
            for(j = 0; j < 8; j += 1) {
                if (this.pieceAt(i, j) != null) {
                    if (this.pieceAt(i, j).isFire()) {
                        this.countFire += 1;
                    } else {
                        this.countWater += 1;
                    }
                }
            }
        }
        if (this.countWater == 0 && this.countFire == 0) {
            this.gameEnded = true;
            return "No one";
        } else if (this.countWater > 0 && this.countFire == 0) {
            this.gameEnded = true;
            return "Water";
        } else if (this.countWater == 0 && this.countFire > 0) {
            this.gameEnded = true;
            return "Fire";
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Board b = new Board(false);

        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        while(true) {
            for (int i = 0; i < 8; i += 1) {
                for (int j = 0; j < 8; j += 1) {
                    if ((i + j) % 2 == 0) {
                        StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    } else {
                        StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    }
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
            }
            if (b.selected[0] != -1) {
                StdDrawPlus.filledSquare(b.selected[0] + 0.5,
                                         b.selected[1] + 0.5, 0.5);
            }
            for (int i = 0; i < 8; i += 1) {
                for (int j = 0; j < 8; j += 1) {
                    if (b.pieceAt(i, j) != null) {
                        Piece temp = b.pieceAt(i, j);
                        if (temp.isFire()) {
                            if (temp.isBomb()) {
                                if (temp.isKing()) {
                                    StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png", 1, 1);
                                } else {
                                    StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
                                }
                            } else if (temp.isShield()) {
                                if (temp.isKing()) {
                                    StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1, 1);
                                } else {
                                   StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1); 
                                }
                            } else {
                                if (temp.isKing()) {
                                    StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png", 1, 1);
                                } else {
                                    StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
                                }
                            }
                        } else {
                            if (temp.isBomb()) {
                                if (temp.isKing()) {
                                    StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png", 1, 1);
                                } else {
                                    StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
                                } 
                            } else if (temp.isShield()) {
                                if (temp.isKing()) {
                                    StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png", 1, 1);
                                } else {
                                    StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
                                }
                            } else {
                                if (temp.isKing()) {
                                    StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png", 1, 1);
                                } else {
                                    StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
                                }
                            }
                        }
                    }
                }
            }
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    b.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
            StdDrawPlus.show(50);
        }
    }
}

