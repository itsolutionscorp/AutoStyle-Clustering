public class Board {
    private Piece[][] pieces = new Piece[8][8];;
    private boolean fire_turn;
    private int selected_x;
    private int selected_y;
    private Piece selected_piece;
    private boolean has_moved;

    public static void main(String[] args) {

        Board board = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);

        while (true) {
            board.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                int mouseX = (int)StdDrawPlus.mouseX();
                int mouseY = (int)StdDrawPlus.mouseY();
                if (board.canSelect(mouseX, mouseY)) {
                    board.select(mouseX, mouseY);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                    board.endTurn();
                }
            }
            board.drawPiece();
            StdDrawPlus.show(15);
        }
    }

    public Board(boolean shouldBeEmpty) {
        if (!shouldBeEmpty) {
        pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
        pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
        pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
        pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
        pieces[1][1] = new Piece(true, this, 1, 1, "shield");
        pieces[3][1] = new Piece(true, this, 3, 1, "shield");
        pieces[5][1] = new Piece(true, this, 5, 1, "shield");
        pieces[7][1] = new Piece(true, this, 7, 1, "shield");
        pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
        pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
        pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
        pieces[6][2] = new Piece(true, this, 6, 2, "bomb");
        pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
        pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
        pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
        pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
        pieces[0][6] = new Piece(false, this, 0, 6, "shield");
        pieces[2][6] = new Piece(false, this, 2, 6, "shield");
        pieces[4][6] = new Piece(false, this, 4, 6, "shield");
        pieces[6][6] = new Piece(false, this, 6, 6, "shield");
        pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
        pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
        pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
        pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
        }
        selected_piece = null;
        has_moved = false;
        fire_turn = true;
        selected_x = 8;
        selected_y = 8;
        }

    private void drawBoard() {
        int x = 0;
        while (x < 8) {
            int y = 0;
            while (y < 8) {
                if (((x + y) % 2) == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                y += 1;
            }
            x += 1;
        }
    }

    private void drawPiece() {
        for (int i = 0; i < 8; i++) { 
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        if (pieces[i][j].isBomb()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire-crowned.png", 1, 1);
                            } else {
                            StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-fire.png", 1, 1);
                            }
                        } else if (pieces[i][j].isShield()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire-crowned.png", 1, 1);
                            } else {
                            StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-fire.png", 1, 1);
                            }
                        } else {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire-crowned.png", 1, 1);
                            } else {
                            StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                    } else {
                        if (pieces[i][j].isBomb()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water-crowned.png", 1, 1);
                            } else {
                            StdDrawPlus.picture(i + 0.5, j + 0.5, "img/bomb-water.png", 1, 1);
                            }
                        } else if (pieces[i][j].isShield()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water-crowned.png", 1, 1);
                            } else {
                            StdDrawPlus.picture(i + 0.5, j + 0.5, "img/shield-water.png", 1, 1);
                            }
                        } else {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water-crowned.png", 1, 1);
                            } else {
                            StdDrawPlus.picture(i + 0.5, j + 0.5, "img/pawn-water.png", 1, 1);
                            }
                        }
                    }
                }
            }
        }
    }


    public Piece pieceAt(int x, int y) {
        if ((x > 7) || (y > 7) || (x < 0) || (y < 0)) {
            return null;
        } else {
            return pieces[x][y];
        }
    }


    public boolean canSelect(int x, int y) {
        if ((x < 0) || (x > 7) || (y < 0) || (y > 7)) {
            return false;
        }
        if (pieces[x][y] != null) {
            if ((fire_turn) && (!pieces[x][y].isFire())) {
                return false;
            }
            if ((!fire_turn) && (pieces[x][y].isFire())) {
                return false;
            }
            if (selected_piece == null) {
                return true;
            } else if (!has_moved) {
                return true;
            } else {
                return false;
            }
        } else {
            if ((selected_piece != null) && (!has_moved) && (validMove(selected_x, selected_y, x, y))) {
                return true;
            } else if ((selected_piece != null) && (Math.abs(selected_x - x) == 2) && (pieces[selected_x][selected_y] != null) && (has_moved) && (pieces[selected_x][selected_y].hasCaptured()) && (validMove(selected_x, selected_y, x, y))) {
                return true;
        } else {
            return false;
        }
    }
}


    private boolean validMove(int xi, int yi, int xf, int yf) {
        if ((xf < 0) || (xf > 7) || (yf > 7) || (yf < 0) || (xi < 0) || (xi > 7) || (yi < 0) || (yi > 7)) {
            return false;
        }
        Piece p = pieces[xi][yi];
        int x_dif = xf - xi;
        int y_dif = yf - yi;
        if (p.isFire()) {
            if ((((pieces[xf][yf] == null) && 
                        (Math.abs(x_dif) == 1) && 
                        ((yf == yi + 1) || ((p.isKing()) && (yf == yi - 1))))) ||
                    ((Math.abs(x_dif) == 2) && 
                        ((yf == yi + 2) || ((p.isKing()) && (yf == yi - 2))) && 
                        ((pieces[xi + x_dif/2][yi + y_dif/2] != null) && (!pieces[xi + x_dif/2][yi + y_dif/2].isFire())))) {
                    return true; 
                } else {
                    return false;
                }
            } else {
                if ((((pieces[xf][yf] == null) && 
                        (Math.abs(x_dif) == 1) && 
                        ((yf == yi - 1) || ((p.isKing()) && (yf == yi + 1))))) ||
                    ((Math.abs(x_dif) == 2) && 
                        ((yf == yi - 2) || ((p.isKing()) && (yf == yi + 2))) && 
                        ((pieces[xi + x_dif/2][yi + y_dif/2] != null) && (pieces[xi + x_dif/2][yi + y_dif/2].isFire())))) {
                    return true;
                } else {
                    return false;
                }
            }
        }

    public void select(int x, int y) {
            if ((selected_piece != null) && (pieces[x][y] == null)) {
                pieces[selected_x][selected_y].move(x, y);
                has_moved = true;
                }
                if (pieces[x][y] != null) {
                selected_piece = pieces[x][y];
                selected_x = x;
                selected_y = y;
                }
            }

    public void place(Piece p, int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0 || p == null) {
        } else {
                pieces[x][y] = p;
            }
        }

        public Piece remove(int x, int y) {
            if (x > 7 || x < 0 || y > 7 || y < 0) {
                System.out.println("The input (x, y) is out of bounds.");
                return null;
            } else if (pieces[x][y] == null) {
                System.out.println("No piece is at (x, y).");
                return null;
            } else {
                Piece removed = pieces[x][y];
                pieces[x][y] = null;
                return removed;
            }
        }

        public boolean canEndTurn() {
            return has_moved;
        }

        public void endTurn() {
            if (fire_turn) {
                fire_turn = false;
            } else {
                fire_turn = true;
            }
            selected_piece = null;
            if (pieces[selected_x][selected_y] != null) {
            pieces[selected_x][selected_y].doneCapturing();
            } 
            has_moved = false;
            selected_x = 8;
            selected_y = 8;
    }

         public String winner() {
             int number = 0;
             int sides = 0;
             for (int i = 0; i < 8; i++) {
                 for (int j = 0; j < 8; j++) {
                     if (pieces[i][j] != null) {
                         number += 1;
                         sides += pieces[i][j].side();
                     }
                 }
             }
             if (number == 0) {
                 return "No one";
             } else if (sides == number) {
                 return "Water";
             } else if (sides == 0) {
                 return "Fire";
             } else {
                 return null;
             }
         }
}