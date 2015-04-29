public class Board {
//Instance variables
    private Piece[][] gameBoard;
    private Piece selected;
    private int whoseTurn;
    private boolean hasMoved;
    private Piece movingPiece;

    public static void main(String args[]) {
        Board game = new Board(false);
        while(true){
            game.drawBoard();
            if(StdDrawPlus.mousePressed()) {
                double xClick = StdDrawPlus.mouseX();
                double yClick = StdDrawPlus.mouseY();
                if(game.canSelect((int) xClick, (int) yClick)) {
                    game.select((int) xClick, (int) yClick);
                    game.drawBoard();
                }
            }
            if(StdDrawPlus.isSpacePressed() && game.canEndTurn()) {
                game.endTurn();
            }
            if(game.winner() != null) {
                break;
            }
            StdDrawPlus.show(10);
            
        }
    }

    public Board(boolean shouldBeEmpty) {
        gameBoard = new Piece[8][8];
        this.whoseTurn = 0;
        this.hasMoved = false;
        selected = null;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(shouldBeEmpty) {
                gameBoard[i][j] = null;
                } else {
                    //set up fire pieces
                    if(i == 0 && (j % 2 == 0)) {
                        gameBoard[i][j] = new Piece(true, this, j, i, "pawn");
                    } else if(i == 1 && (j % 2 != 0)) {
                        gameBoard[i][j] = new Piece(true, this, j, i, "shield");
                    } else if(i == 2 && (j % 2 == 0)) {
                        gameBoard[i][j] = new Piece(true, this, j, i, "bomb");
                    }
                    //set up water pieces
                    else if(i == 5 && (j % 2 != 0)) {
                        gameBoard[i][j] = new Piece(false, this, j, i, "bomb");
                    } else if(i == 6 && (j % 2 == 0)) {
                        gameBoard[i][j] = new Piece(false, this, j, i, "shield");
                    } else if(i == 7 && (j % 2 != 0)) {
                        gameBoard[i][j] = new Piece(false, this, j, i, "pawn");
                    } else {
                        gameBoard[i][j] = null;
                    }
                }
            }
        }
    }

    private void drawBoard() {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                drawPiece(j, i);
            }
        }
        if(selected != null) {
            int xSelect = findPieceX(selected);
            int ySelect = findPieceY(selected);
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(xSelect + .5, ySelect + .5, .5);
            drawPiece(xSelect, ySelect);
        }
    }

    private void drawPiece(int j, int i) {
        if(gameBoard[j][i] != null) {
            if(gameBoard[j][i].isKing()) {
                if(gameBoard[j][i].isFire()) {
                    if(gameBoard[j][i].isBomb()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                    } else if(gameBoard[j][i].isShield()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                    } else {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                    }
                } else {
                    if(gameBoard[j][i].isBomb()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                    } else if(gameBoard[j][i].isShield()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                    } else {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                    }
                }
            } else {
                if(gameBoard[j][i].isFire()) {
                    if(gameBoard[j][i].isBomb()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                    } else if(gameBoard[j][i].isShield()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                    } else {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                    }
                } else {
                    if(gameBoard[j][i].isBomb()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    } else if(gameBoard[j][i].isShield()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    } else {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                    }
                }
            }
        }
    }


    public Piece pieceAt(int x, int y) {
        if(x > 7 || y > 7) {
           return null; 
        } else {
            return gameBoard[y][x];
        }
    }

    public boolean canSelect(int x, int y) {
        if(!hasMoved) {
            if(selected != null) {
                if(validMove(findPieceX(selected), findPieceY(selected), x, y)) {
                    return true;
                }  
            } 
            if(pieceAt(x, y) != null && pieceAt(x,y).side() == whoseTurn) {
                return true;
            } 
        } else {
            if(pieceAt(x, y) == null && selected != null) {
               int xSelect = findPieceX(selected);
               int ySelect = findPieceY(selected);
               if(Math.abs(x-xSelect) == 2 && Math.abs(y - ySelect) == 2) {
                   if(pieceAt(((x-xSelect)/2 + xSelect), ((y-ySelect)/2 + ySelect)) != null) {
                       return true;
                   }
               }
            }
        }
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if(pieceAt(xi, yi) != null) {
            if(pieceAt(xi, yi).isKing()) {
                if(Math.abs(xf - xi) == 2 && Math.abs(yf-yi) == 2) {
                    if(pieceAt((xf - xi)/2 + xi, (yf - yi)/2 + yi) != null) {
                        return true;
                    }
                }
                if(gameBoard[yf][xf] == null) {
                    if(xi - xf == 1 || xi - xf == -1) {
                        if(yi - yf == 1 || yi - yf == -1) {
                            return true;
                        }
                    }
                }
            } else {

                if(Math.abs(xf - xi) == 2 && (yf - yi + 4*pieceAt(xi, yi).side() == 2)) {
                    if(pieceAt((xf - xi)/2 + xi, (yf - yi)/2 + yi) != null) {
                        return true;
                    }
                }
                if(yf - yi + 2*pieceAt(xi, yi).side() == 1 && (xi - xf == 1 || xi - xf == -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int findPieceX(Piece p) {
        for(int i = 0; i < 8; i++) {
            for(int j =0; j < 8; j++) {
                if(gameBoard[i][j] == p) {
                    return j;
                }
            }
        }
        return 8;
    }
    
    private int findPieceY(Piece p) {
        for(int i = 0; i < 8; i++) {
            for(int j =0; j < 8; j++) {
                if(gameBoard[i][j] == p) {
                    return i;
                }
            }
        }
        return 8;
    }

    public void select(int x, int y) {
        if(pieceAt(x, y) != null) {
            movingPiece = pieceAt(x, y);
        }
        if(selected != null && pieceAt(x, y) == null) {
            selected.move(x, y);
            hasMoved = true;
        }
        selected = pieceAt(x, y);
    }

    public void place(Piece p, int x, int y) {
        if(x > 7 || y > 7) {
            return;
        } else {
            gameBoard[y][x] = p;
        }
    }

    public Piece remove(int x, int y) {
        if(x > 7 || y > 7) {
            return null;
        } else {
            Piece removed = gameBoard[y][x];
            gameBoard[y][x] = null;
            return removed;
        }
    }

    public boolean canEndTurn() {
            return hasMoved;
    }

    public void endTurn() {
        movingPiece.doneCapturing();
        selected = null;
        hasMoved = false;
        if(whoseTurn == 0) {
            whoseTurn = 1;
        } else {
            whoseTurn = 0;
        }
    }

    public String winner() {
        int fireCount = 0;
        int waterCount = 0;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(gameBoard[i][j] != null) {
                    if(gameBoard[i][j].isFire()) {
                        fireCount++;
                    }else {
                        waterCount++;
                    }
                }
            }
        }
        if(fireCount == 0 && waterCount == 0) {
            return "No one";
        } else if(fireCount == 0) {
            return "Water";
        } else if(waterCount == 0) {
            return "Fire";
        }else {
            return null;
        }
    }
}
