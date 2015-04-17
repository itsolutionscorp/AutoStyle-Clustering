public class Board {
    private Piece[][] boardPieces = new Piece[8][8];
    private static boolean[][] pieces;
    private boolean selected = false;
    private boolean fireturn = true;
    private boolean waterturn = false;
    private int currentX = -1;
    private int currentY = -1; 
    private boolean ihavemoved = false; 
    private boolean pieces_on_the_board = false;
    private int countFire = 0;
    private int countTotal = 0;
    private int countWater = 0;
    private int jumpX;
    private int jumpY;
    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (selected && currentX == i && currentY == j) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                if (canEndTurn() && StdDrawPlus.isSpacePressed()) {
                    if (boardPieces[i][j] != null) {
                        boardPieces[i][j].doneCapturing();
                    }
                    endTurn();

                }
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (findImgString(i, j) != null) {
                StdDrawPlus.picture(i + .5, j + 0.5, findImgString(i, j), 1, 1);
                }
            }
        }
    }
public Board(boolean shouldBeEmpty) {
    if (!shouldBeEmpty) {
        createPieces();
    }
}

public Piece pieceAt(int x, int y) {
    if (outOfBounds(x, y) || this.boardPieces[x][y] == null) {
        return null;
    }
    return this.boardPieces[x][y];
}

private boolean outOfBounds(int x, int y) {
    return (x < 0) || (x > 7) || (y < 0) || (y > 7);
  }

public void place(Piece p, int x, int y) {
    if (!outOfBounds(x, y) && p != null) {
        this.boardPieces[x][y] = p;
    }
}

private void createPieces() {
            this.boardPieces[0][0] = new Piece(true, this, 0, 0, "pawn");
            this.boardPieces[2][0] = new Piece(true, this, 2, 0, "pawn");
            this.boardPieces[4][0] = new Piece(true, this, 4, 0, "pawn");
            this.boardPieces[6][0] = new Piece(true, this, 6, 0, "pawn");
            this.boardPieces[0][2] = new Piece(true, this, 0, 2, "bomb");
            this.boardPieces[2][2] = new Piece(true, this, 2, 2, "bomb");
            this.boardPieces[4][2] = new Piece(true, this, 4, 2, "bomb");
            this.boardPieces[6][2] = new Piece(true, this, 6, 2, "bomb");

    for(int i = 0; i < 8; i +=2) {
            this.boardPieces[i +1][1] = new Piece(true, this, i + 1, 1, "shield");
            this.boardPieces[i+1][5] = new Piece(false, this, i + 1, 5, "bomb");
            this.boardPieces[i][6] = new Piece(false, this, i, 6, "shield" );  
            this.boardPieces[i + 1][7] = new Piece(false, this, i + 1, 7, "pawn");
    }   
  
}

    public static void main(String[] args) {
        Board notEmptyBoard= new Board(false); 
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        pieces = new boolean[N][N]; 
        while(true) {
            notEmptyBoard.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int) (StdDrawPlus.mouseX());
                int y = (int) (StdDrawPlus.mouseY());
                if (notEmptyBoard.canSelect(x, y)) {
                    notEmptyBoard.select(x,y);
                    }
                }
            StdDrawPlus.show(100);
        }
    }

 private String findImgString(int i, int j) { 
    if (this.boardPieces[i][j] != null) {
        String str = "img/";
    if (this.boardPieces[i][j].isBomb()) {
        str += "bomb";
    }
    else if (this.boardPieces[i][j].isShield()) {
        str += "shield";
    }
    else {
        str += "pawn";
    }
    if (this.boardPieces[i][j].isFire()) {
        str = str + "-fire";
    }
    else {
        str = str + "-water";
    }
    if (this.boardPieces[i][j].isKing()) {
      str = str + "-crowned";
    }
    str = str + ".png";
    return str;
    }
    return null;
}

private boolean validMove(int xi, int yi, int xf, int yf) {
    if (this.boardPieces[xi][yi].isFire() && fireturn || !this.boardPieces[xi][yi].isFire() && waterturn) {    
        if (xf < 0 || xf >= 8 || yf < 0 || yf >= 8) {
             return false; 
        } 
        if (this.boardPieces[xf][yf] != null) {
                 return false;  
        }
        if (Math.abs(xi - xf) == 1  &&  Math.abs(yi - yf) == 1) {   
            if (this.boardPieces[xi][yi].isFire()) {
                if (!this.boardPieces[xi][yi].isKing() && yf < yi) {
                        return false;
                }  
            }
            else if (!this.boardPieces[xi][yi].isFire()) {
                if (!this.boardPieces[xi][yi].isKing() && yf > yi) {
                    return false;  
                }
            }
            return true;
        }
        if (Math.abs(xi - xf) == 2  &&  Math.abs(yi - yf) == 2) {   
            jumpX = (xi + xf)/2;
            jumpY = (yi + yf)/2;
            if (this.boardPieces[xi][yi].isFire()) {
                if (!this.boardPieces[xi][yi].isKing() && yf < yi) {
                    return false;
                }  
                if (this.boardPieces[jumpX][jumpY] == null) {
                    return false;
                }
                if (this.boardPieces[jumpX][jumpY] != null && this.boardPieces[jumpX][jumpY].isFire()) {
                    return false;
                }     
            }
            else if (!this.boardPieces[xi][yi].isFire()) {
                if (!this.boardPieces[xi][yi].isKing() && yf > yi) {
                    return false;  
                        }
                if (this.boardPieces[jumpX][jumpY] == null) {
                    return false;
                }
                if (this.boardPieces[jumpX][jumpY] != null && !this.boardPieces[jumpX][jumpY].isFire()) {
                    return false;
                        } 
            }
            return true;
        }
    }
    return false;
}

public Piece remove(int x, int y) {
    if (outOfBounds(x, y)) {
        String errorMessage = "Can't remove because the piece is out of Bounds";
        System.out.println(errorMessage);
        return null;
    }
    if (this.boardPieces[x][y] == null) {
      String str = "Tried to remove a null piece";
      System.out.println(str);
      return null;
    }
    Piece removed_piece = this.boardPieces[x][y]; 
    this.boardPieces[x][y] = null;
    return removed_piece;
  }


public boolean canSelect(int x, int y) {
    if (!outOfBounds(x, y)) {
        if (this.boardPieces[x][y] != null) {
            if (this.boardPieces[x][y].isFire() && fireturn || !this.boardPieces[x][y].isFire() && waterturn) {    
                if (!this.selected) {
                    return true;
            }
            else if (this.selected && !this.ihavemoved) {
                return true;
            }
            }
            return false;
        }
        else if (this.boardPieces[x][y] == null) {
                if (this.selected && !this.ihavemoved && validMove(currentX, currentY, x, y)) {
                    return true; 
                }
                if (this.selected && this.boardPieces[currentX][currentY] != null && this.boardPieces[currentX][currentY].hasCaptured() && validMove(currentX, currentY, x, y) && Math.abs(currentX - x) == 2) {
                    return true; 
                }
            }
            return false;
    }
    return false;
}    

public void select(int x, int y) {
    if (selected && boardPieces[x][y] == null) {
            boardPieces[currentX][currentY].move(x, y);
            ihavemoved = true; 
    }
    if (this.boardPieces[x][y] != null) {
    currentX = x;
    currentY = y;
    selected = true;
    }
}

public boolean canEndTurn() {
    if (this.ihavemoved) {
        return true;
    }
    return false;
}

private void changeTurns() {
    if (waterturn && !fireturn) {
        waterturn = false;
        fireturn = true;
    }
    else {
        waterturn = true;
        fireturn = false;
    }
}

public void endTurn() {
        changeTurns();
        ihavemoved = false;
        selected = false;
}

public String winner() {
           for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (boardPieces[i][j] != null) {
                         if (boardPieces[i][j].isFire()) {
                            pieces_on_the_board = true;
                            countFire += 1;
                            countTotal +=1;
                        }
                        if (!boardPieces[i][j].isFire()) {
                            pieces_on_the_board = true;
                            countWater +=1;
                            countTotal += 1;
                        }
                    }
                }
            }
    if  (!pieces_on_the_board && countTotal == 0) {
        return "No one";        
    }
    if (countFire == countTotal && countTotal > 0) {
        return "Fire";
    }
    else if (countTotal == countWater && countTotal > 0) {
        return "Water";
    }
    countFire = 0;
    countTotal = 0;
    countWater = 0;
    pieces_on_the_board = false;
    return null;
}
}



