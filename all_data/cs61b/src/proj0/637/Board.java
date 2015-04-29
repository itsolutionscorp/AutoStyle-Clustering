public class Board {

    private Piece[][] gamePieces;
    private boolean defaultBoard;
    private int N = 8;
    private int player = 1;
    private boolean selected = false;
    private Piece selectedPiece;
    private int sx = -1;
    private int sy = -1;
    private boolean move = false;
    private boolean captured = false;
    private int fireCount = 0;
    private int waterCount = 0;

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                
                if (selectedPiece != null) {
                    if (pieceAt(i, j) == selectedPiece) {
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    }
                }
                
                if (gamePieces[i][j] != null) {
                    if (gamePieces[i][j].isFire()) {
                        if (!gamePieces[i][j].isKing() && !gamePieces[i][j].isBomb() && !gamePieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }
                    } else if (!gamePieces[i][j].isKing() && !gamePieces[i][j].isBomb() && gamePieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    } else if (!gamePieces[i][j].isKing() && gamePieces[i][j].isBomb() && !gamePieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    }
                    if (gamePieces[i][j].isFire()) {
                        if (gamePieces[i][j].isKing() && !gamePieces[i][j].isBomb() && !gamePieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                        }
                    } else if (gamePieces[i][j].isKing() && !gamePieces[i][j].isBomb() && gamePieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                    } else if (gamePieces[i][j].isKing() && gamePieces[i][j].isBomb() && !gamePieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                    }
                    if (!gamePieces[i][j].isFire()) {
                        if (!gamePieces[i][j].isKing() && !gamePieces[i][j].isBomb() && !gamePieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                    } else if (!gamePieces[i][j].isKing() && !gamePieces[i][j].isBomb() && gamePieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    } else if (!gamePieces[i][j].isKing() && gamePieces[i][j].isBomb() && !gamePieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    }
                    if (!gamePieces[i][j].isFire()) {
                        if (gamePieces[i][j].isKing() && !gamePieces[i][j].isBomb() && !gamePieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                        }
                    } else if (gamePieces[i][j].isKing() && !gamePieces[i][j].isBomb() && gamePieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                    } else if (gamePieces[i][j].isKing() && gamePieces[i][j].isBomb() && !gamePieces[i][j].isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                    }
                }
            }   
        }
    }

    public Board(boolean shouldBeEmpty) {
        defaultBoard = shouldBeEmpty;
        gamePieces = new Piece[8][8];
        if (defaultBoard==false) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j ++) {
                    if ((j == 0) && ((i == 0) || (i == 2) || (i == 4) || (i == 6))) {
                        gamePieces[i][j] = new Piece(false, this, i, j, "pawn");
                    } else if ((j == 1) && ((i == 1) || (i == 3) || (i == 5) || (i == 7))) {
                        gamePieces[i][j] = new Piece(false, this, i, j, "shield");
                    } else if ((j == 2) && ((i == 0) || (i == 2) || (i == 4) || (i == 6))) {
                        gamePieces[i][j] = new Piece(false, this, i, j, "bomb");
                    } else if ((j == 7) && ((i == 1) || (i == 3) || (i == 5) || (i == 7))) {
                        gamePieces[i][j] = new Piece(true, this, i, j, "pawn");
                    } else if ((j == 6) && ((i == 0) || (i == 2) || (i == 4) || (i == 6))) {
                        gamePieces[i][j] = new Piece(true, this, i, j, "shield");
                    } else if ((j == 5) && ((i == 1) || (i == 3) || (i == 5) || (i == 7))) {
                        gamePieces[i][j] = new Piece(true, this, i, j, "bomb");
                    }
                }
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        if (x < 0 || y < 0 || x > 7 || y > 7 || (gamePieces[x][y] == null)) {
            return null;
        } else {
            return gamePieces[x][y];
        }
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board board = new Board(false);

        while(true) {
            board.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (board.canSelect((int)x, (int)y)) {
                    board.select((int)x, (int)y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn() == true) {
                    board.endTurn();
                }
            }            
            StdDrawPlus.show(15);
        }
    }

    public boolean canSelect(int x, int y) {
        if (gamePieces[x][y] != null) { 
            if (selected == false) {
                if (player == gamePieces[x][y].side()) {
                    return true;
                }
            } else if ((move == false) && (gamePieces[x][y] == null)) {
                return true;
            } else if ((move == false) && (gamePieces[x][y].side() == player)) {
                return true;
            }
        } else if ((selected == true) && (validMove(sx, sy, x, y) == true)) {
            System.out.println(captured);
            if (move == false) {
                return true;
            } else if (captured == true) {
                return true;
            }
        } 
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (xi < 0 || yi < 0 || xf < 0 || yf < 0 || xi > 7 || yi > 7 || xf > 7 || yf > 7) {
            return false;
        } else if ((xi + 1 == xf) && (yi + 1 == yf)) {
            if (gamePieces[xf][yf] == null) {
                return true;
            } 
        } else if ((xi - 1 == xf) && (yi + 1 == yf)) {
            if (gamePieces[xf][yf] == null) {
                return true;
            } 
        } else if ((xi + 2 == xf) && (yi + 2 == yf)) {
            if (gamePieces[xf][yf] == null) {
                if (gamePieces[xf - 1][yf - 1] != null) {
                    if (player != gamePieces[xf - 1][yf - 1].side()) {
                        return true;
                    }
                }
            }
        } else if ((xi - 2 == xf) && (yi + 2 == yf)) {
            if (gamePieces[xf][yf] == null) {
                if (gamePieces[xf - 1][yf + 1] != null) {
                    if (player != gamePieces[xf - 1][yf + 1].side()) {
                        return true;
                    }
                }       
            } 
        } 

        if (xi < 0 || yi < 0 || xf < 0 || yf < 0 || xi > 7 || yi > 7 || xf > 7 || yf > 7) {
            return false;
        } else if ((xi - 1 == xf) && (yi - 1 == yf)) {
            if (gamePieces[xf][yf] == null) {
                return true;
            }  
        } else if ((xi + 1 == xf) && (yi - 1 == yf)) {
            if (gamePieces[xf][yf] == null) {
                return true;
            } 
        } else if ((xi - 2 == xf) && (yi - 2 == yf)) {
            if (gamePieces[xf][yf] == null) {
                if (gamePieces[xf + 1][yf + 1] != null) {
                    if (player != gamePieces[xf + 1][yf + 1].side()) {
                        return true;
                    }
                }
            }
        } else if ((xi + 2 == xf) && (yi - 2 == yf)) {
            if (gamePieces[xf][yf] == null) {
                if (gamePieces[xf - 1][yf + 1] != null) {
                    if (player != gamePieces[xf - 1][yf + 1].side()) {
                        return true;
                    }
                }       
            } 
        }
        return false;
    }

    public void select(int x, int y) {
        if ((gamePieces[x][y] == null) && (selectedPiece != null)) {
            selectedPiece.move(x, y);
            move = true;
            if (selectedPiece.hasCaptured()) {
                sx = x;
                sy = y;
            }
        } else {
            selectedPiece = gamePieces[x][y];
        } 
        place(selectedPiece, x, y);
        selected = true;
        sx = x;
        sy = y;
    }

    
    public void place(Piece p, int x, int y) {
        if ((x >= 0 || y >= 0 || x <= 7 || y <= 7) || p != null) {
            if (validMove(sx, sy, x, y) == true) {
                gamePieces[x][y] = p;
                move = true;
            } 
        }
    }   

    public Piece remove(int x, int y) {
        if (x < 0 || y < 0 || x > 7 || y > 7) {
            System.out.println("(x,y) is out of bounds");
            return null;
        } else if (gamePieces[x][y] == null) {
            System.out.println("No piece at that location");
            return null;
        } else {
            Piece good = pieceAt(x, y);
            gamePieces[x][y] = null;
            return good;
        }
    }

    public boolean canEndTurn() {
        if ((move == true) || (captured == true)) {
            return true;
        } else {
            return false;
        }
    }

    public void endTurn() {
        if (canEndTurn() == true) {
            sy = -1;
            sx = -1;
            selected = false;
            move = false;
            if (selectedPiece != null) {
                selectedPiece.doneCapturing();
            }
            if (player == 0) {
                player = 1;
            } else {
                player = 0;
            }
        }
    }

    public String winner() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if ((gamePieces[x][y] != null) && (gamePieces[x][y].isFire())) {
                    fireCount += 1;
                }
                if ((gamePieces[x][y] != null) && (!gamePieces[x][y].isFire())) {
                    waterCount += 1;
                }
            }
        }
        if ((waterCount == 0) && (fireCount == 0)) {
            return "No one";
        } else if (waterCount == 0) {
            return "Fire";
        } else if (fireCount == 0) {
            return "Water";
        } else {
            return null;
        }              
    }
}