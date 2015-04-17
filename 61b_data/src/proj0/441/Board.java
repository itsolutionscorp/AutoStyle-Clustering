public class Board {

    private boolean shouldBeEmpty = false;
    private int N = 8;
    
    private Piece[][] parr;
    
    private Piece selectedp;
    private boolean hasmoved = false;
    
    private int playerturn = 0;
    
    public static void main(String[] args) {
        Board b = new Board(Boolean.parseBoolean(args[0]));
        
        int N = 8;
        while(true) {
            
            if (StdDrawPlus.isSpacePressed()) {

                b.endTurn();
                
            }
            
            if (StdDrawPlus.mousePressed()) {
                    double x = StdDrawPlus.mouseX();
                    double y = StdDrawPlus.mouseY();

                    if (x < N && x >= 0 && y < N && y >= 0 && b.canSelect((int) x, (int) y) == true) {
                        b.select((int) x, (int) y);
                    }
                }
            
            StdDrawPlus.show(50);

            b.winner();

            b.drawBoard(N);
        }
    }

    public Board(boolean shouldBeEmpty) {
        this.shouldBeEmpty = shouldBeEmpty;
        
        int N = 8;
        parr = new Piece[N][N];
        
        if (this.shouldBeEmpty == false) {
            /* Initiate  Fire Pieces */
            for (int i = 0; i <= (N - 1); i += 2) {
                parr[i][2] = new Piece(true, this, i, 2, "bomb");
            }
            for (int i = 0; i <= (N - 2); i += 2) {
                parr[i + 1][1] = new Piece(true, this, i + 1, 1, "shield");
            }
            for (int i = 0; i <= (N - 1); i += 2) {
                parr[i][0] = new Piece(true, this, i, 0, "pawn");
            }

            /* Initiate Water Pieces */
            if (N % 2 == 0) {
                for (int i = 0; i <= (N - 1); i += 2) { 
                    parr[i + 1][N - 3] = new Piece(false, this, i + 1, N - 3, "bomb");
                }
                for (int i = 0; i <= (N - 2); i += 2) {
                    parr[i][N - 2] = new Piece(false, this, i, N - 2, "shield");
                }
                for (int i = 0; i <= (N - 1); i += 2) {
                    parr[i + 1][N - 1] = new Piece(false, this, i + 1, N - 1, "pawn");
                }
            } else {
                for (int i = 0; i <= (N - 1); i += 2) {
                    parr[i][N - 3] = new Piece(false, this, i, N - 3, "bomb");
                }
                for (int i = 0; i <= (N - 2); i += 2) {
                    parr[i + 1][N - 2] = new Piece(false, this, i + 1, N - 2, "shield");
                }
                for (int i = 0; i <= (N - 1); i += 2) {
                    parr[i][N - 1] = new Piece(false, this, i, N - 1, "pawn");
                }
            }
        }   
    }

    private void drawBoard(int N) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    if (parr[i][j] != null && selectedp == parr[i][j]) {
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    }
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }              
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (parr[i][j] != null && parr[i][j].isKing() == true) {
                    if (parr[i][j].isBomb() == true) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/" + "bomb"
                                + "-" + isFireName(parr[i][j]) + "-crowned.png", 1, 1);
                    } else if (parr[i][j].isShield() == true) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/" + "shield"
                                + "-" + isFireName(parr[i][j]) + "-crowned.png", 1, 1);
                    } else {
                        StdDrawPlus.picture(i + .5, j + .5, "img/" + "pawn"
                                + "-" + isFireName(parr[i][j]) + "-crowned.png", 1, 1);
                    }
                }
                if (parr[i][j] != null && parr[i][j].isKing() == false) {
                    if (parr[i][j].isBomb() == true) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/" + "bomb"
                                + "-" + isFireName(parr[i][j]) + ".png", 1, 1);
                    } else if (parr[i][j].isShield() == true) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/" + "shield"
                                + "-" + isFireName(parr[i][j]) + ".png", 1, 1);
                    } else {
                        StdDrawPlus.picture(i + .5, j + .5, "img/" + "pawn"
                                + "-" + isFireName(parr[i][j]) + ".png", 1, 1);
                    }
                }
            }
        }
    }
    
    private String isFireName(Piece p) { 
        if (p.isFire()) {
            return "fire";
        } else {
            return "water";
        }
    }

    public Piece pieceAt(int x, int y) { 
        if (x < N && x >= 0 && y < N && y >= 0) {
            if (parr[x][y] != null) {
                return parr[x][y];
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void place(Piece p, int x, int y) { 
        if (x < N && x >= 0 && y < N && y >= 0 && p != null) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (parr[i][j] == p) {
                        parr[i][j] = null;
                    }
                }
            }
            parr[x][y] = p;
        } 
    }
    
    public Piece remove(int x, int y) { 
        if (x > N || x < 0 || y > N || y < 0) {
            System.out.println("Input out of bounds.");
            return null;
        } else if (parr[x][y] == null) {
            System.out.println("No piece at given coordinates.");
            return null;
        } else {
            Piece temp = parr[x][y];
            parr[x][y] = null;
            return temp;
        }
    }
    
    public boolean canSelect(int x, int y) {
        if (x < N && x >= 0 && y < N && y >= 0) {
            if (parr[x][y] == null) { 
                if (selectedp == null) {
                    return false;
                } else {
                    if (hasmoved == false && selectedp.hasCaptured() == false
                            && validMove(searchx(selectedp, parr), searchy(selectedp, parr), x, y) == true) {
                        return true;
                    } else if (hasmoved == true && selectedp.hasCaptured() == true 
                            && validMove(searchx(selectedp, parr), searchy(selectedp, parr), x, y) == true 
                            && Math.abs(x - searchx(selectedp, parr)) == 2 && Math.abs(y - searchy(selectedp, parr)) == 2) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } else { 
                if (parr[x][y].side() == playerturn && 
                        (selectedp == null || (selectedp != null & hasmoved == false))) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    public void select(int x, int y) {
        if (parr[x][y] == null && selectedp != null) {
            selectedp.move(x, y);
            hasmoved = true;
        } else {
            selectedp = parr[x][y];
        }
    }

    public void endTurn() { 
        if (canEndTurn() == true) {
            hasmoved = false;
            if (selectedp != null) {
                selectedp.doneCapturing();
            }
            selectedp = null;
            if (playerturn == 1) {
                playerturn = 0;
            } else {
                playerturn = 1;
            }
        }
    }
    
    public boolean canEndTurn() { 
        if (selectedp != null) {
            if (hasmoved == true || selectedp.hasCaptured() == true) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
    private boolean validMove(int xi, int yi, int xf, int yf) { 
        if (parr[xf][yf] == null) {
            if (Math.abs(xf - xi) == 1 && selectedp.isKing() == true) {
                return true;
            } else if (Math.abs(xf - xi) == 2 && selectedp.isKing() == true
                    && parr[xf + (xi - xf) / 2][yf + (yi - yf) / 2] != null 
                    && parr[xf + (xi - xf) / 2][yf + (yi - yf) / 2].isFire() != selectedp.isFire()) {
                return true;
            } else if (Math.abs(xf - xi) == 1) {
                if (playerturn == 0 && yf - yi == 1) {
                    return true;
                } else if (playerturn == 1 && yf - yi == -1) {
                    return true;
                }
            } else if (Math.abs(xf - xi) == 2) {
                if (playerturn == 0 && yf - yi == 2 && parr[xf + (xi - xf) / 2][yf + (yi - yf) / 2] != null) {
                    if (parr[xf + (xi - xf) / 2][yf + (yi - yf) / 2].isFire() == false) {
                        return true;
                    }
                } else if (playerturn == 1 && yf - yi == -2 && parr[xf + (xi - xf) / 2][yf + (yi - yf) / 2] != null) {
                    if (parr[xf + (xi - xf) / 2][yf + (yi - yf) / 2].isFire() == true) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }
    
    public String winner() {
        if (nfire() == 0 && nwater() == 0) {
            return "No one";
        }
        if (nfire() == 0 && nwater() != 0) {
            return "Water";
        } else if (nwater() == 0 && nfire() != 0) {
            return "Fire";
        } else {
            return null;
        }
    }
    
    private int searchx(Piece check, Piece[][] arr) {
        int result = 0;
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < N; j++) {
                if (check == parr[i][j]) {
                    result = i;
                    break;
                }
            }
        }
        return result;
    }
    
    private int searchy(Piece check, Piece[][] arr) {
        int result = 0;
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < N; j++) {
                if (check == parr[i][j]) {
                    result = j;
                    break;
                }
            }
        }
        return result;
    }
    
    private int nfire() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (parr[i][j] != null && parr[i][j].isFire() == true) {
                    count = count + 1;
                }
            }
        }
        return count;
    }
    
    private int nwater() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (parr[i][j] != null && parr[i][j].isFire() == false) {
                    count = count + 1;
                }
            }
        }
        return count;
    }
    
}