public class Board{

    private boolean empty;
    private Piece[][] gameboard;
    private boolean player = true; 
    private Piece selected;
    private boolean move = false;


    public Board(boolean shouldBeEmpty) {
        empty = shouldBeEmpty;
        if (empty) {
            gameboard = new Piece[8][8];
        } else {
            gameboard = new Piece[8][8];
            for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 0 && i % 2 == 0) {
                    gameboard[i][j] = new Piece(true, this, i, j, "pawn");
                } else if (j == 2 && i % 2 == 0) {
                    gameboard[i][j] = new Piece(true, this, i, j, "bomb");
                } else if (j == 7 && i % 2 == 1) {
                    gameboard[i][j] = new Piece(false, this, i, j, "pawn");
                } else if (j == 5 && i % 2 == 1) {
                    gameboard[i][j] = new Piece(false, this, i, j, "bomb");
                } else if (j == 1 && i % 2 == 1) {
                    gameboard[i][j] = new Piece(true, this, i, j, "shield");
                } else if (j == 6 && i % 2 == 0) {
                    gameboard[i][j] = new Piece(false, this, i, j, "shield");
                }
            }
        }
        }
    }

    public Piece pieceAt(int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0) {
            return null;
        }
        return gameboard[x][y];
    }
    
    private int[] find_p(Piece p) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieceAt(i, j) == p) {
                    return new int[] {i,j};
                }
            }
        }
        return null;
    }

    public void place(Piece p, int x, int y) {


        if (x > 7 || x < 0 || y > 7 || y < 0 || ((x+y) % 2 != 0)) {
            return;
        } else if (p == null) {
            return;
        }
        int[] pos = find_p(p);
        if (pieceAt(x, y) != null) {
            
            remove(x, y);
            gameboard[x][y] = p;
        } else  if (pos == null) {
            gameboard[x][y] = p;

        } else {
            move = true;
            remove(pos[0], pos[1]);
            gameboard[x][y] = p;
            p.move(x, y);
        }

        empty = false;

    }

    public boolean canSelect(int x, int y) {
        if (move && (selected == null)) {
            return false;
        } else if (pieceAt(x, y) != null) {
            if (((player && pieceAt(x, y).isFire()) || 
            (!player && !pieceAt(x, y).isFire())) 
            && ( selected == null || (selected != null && move == false))) {
                return true;
                }
        } else if (selected != null) {
            int[] coord = find_p(selected);
            if(move == false && validMove(coord[0], coord[1], x, y)) {
                return true;
            } else if (selected.hasCaptured() && validMove(coord[0], coord[1], x, y)) {
                return true;
            }
        }
        return false;
    }

    public void select(int x, int y) {
        if (pieceAt(x, y) == null) {
            place(selected, x, y);
            }   
            selected = pieceAt(x, y);
    }

    public Piece remove(int x, int y) {
        if (x > 7 || x < 0 || y > 7 || y < 0) {
            System.out.println ("Out of Bounds!");
            return null;
        } else if (pieceAt(x, y) == null) {
            System.out.println ("There is no piece here.");
            return null;
        }
        Piece p = pieceAt(x, y);
        gameboard[x][y] = null;
        return p;
    }

    public boolean canEndTurn() {
        if (selected != null){
            if (move == true || selected.hasCaptured()) {
            return true;
            }
        }
        return move;
    }

    public void endTurn() {
        if (selected != null) {
            selected.doneCapturing();
        }
        selected = null;
        move = false;
        player = !player;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if ((xf + yf) % 2 == 1)  {
            return false;
        } else if (pieceAt(xi, yi).isFire()) {
            if (Math.abs(xf - xi) == 1 && ((yi + 1 == yf) || ((yi -1==yf) && pieceAt(xi, yi).isKing()))) {
                return !move;
            } if (Math.abs(xf-xi) == 2 && ((yi + 2 == yf) || ((yi -2==yf) && pieceAt(xi, yi).isKing()))
                && pieceAt((int)((xf + xi) / 2), (int)((yf + yi) / 2)) != null &&
                !pieceAt((int)((xf + xi) / 2), (int)((yf + yi) / 2)).isFire()) {
                return true;
            }

        } else {
            if (Math.abs(xf - xi) == 1 && ((yi - 1 == yf) || ((yi +1==yf) && pieceAt(xi, yi).isKing()))) {
                return !move;
            }
            if (Math.abs(xf-xi) == 2 && ((yi - 2== yf) || ((yi +2==yf) && pieceAt(xi, yi).isKing()))
                && pieceAt((int)((xf + xi) / 2), (int)((yf + yi) / 2)) != null &&
                pieceAt((int)((xf + xi) / 2), (int)((yf + yi) / 2)).isFire()) {
                return true;
            }
        } 
        return false;
}

    public String winner() {
        int numFire = 0;
        int numWater = 0;
        int total = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameboard[i][j] != null) {
                    if (gameboard[i][j].isFire()) {
                        total ++;
                        numFire ++;
                    } else {
                        total ++;
                        numWater ++;
                }
                }
            }
    
        }

        if (numFire == 0 && numWater == 0) {
            return "No one";
        } else if (numWater == total) {
            return "Water";
        } else if (numFire == total) {
            return "Fire";
        }

           return null;
    }


     // Got idea from Jeremy Wan for drawBoard

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieceAt(i,j)==selected && selected != null) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                // if (pieces[i][j]) {
                //     StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                // }
                if (gameboard[i][j] != null) {
                    if (gameboard[i][j].isKing()) {
                        if (gameboard[i][j].isFire()) {
                            if (!gameboard[i][j].isShield() && !gameboard[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                            else if (gameboard[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            else if (gameboard[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                        } else {
                             if (!gameboard[i][j].isShield() && !gameboard[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            }
                            else if (gameboard[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                            else if (gameboard[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                        } 
                    } else {
                        if (gameboard[i][j].isFire()) {
                            if (!gameboard[i][j].isShield() && !gameboard[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                            else if (gameboard[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                            else if (gameboard[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                        } else {
                             if (!gameboard[i][j].isShield() && !gameboard[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            }
                            else if (gameboard[i][j].isShield()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                            else if (gameboard[i][j].isBomb()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                        } 
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);


        while(true) {
            b.drawBoard(N);
            if (b.winner() != null) {
                b.endTurn();
                StdDrawPlus.show(20);
                b.drawBoard(N);
                return;
            }
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int)x, (int)y)) {
                    b.select((int)x, (int)y);
                }
                
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
                
            }            
            StdDrawPlus.show(20);
        }
    }

    // Sources: got the majority ideas from Jennifer Wu, which includes validMove, remove, winner, and canSelect.
    // Daniel Choi helped me when debugging and giving me ideas for capturing. Debugging help from Grace P.
    // Colaborate with Tina, Joyce, and Emily.
    
}