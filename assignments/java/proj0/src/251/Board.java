public class Board {
	private Piece[][] piecesP = new Piece [8][8];
    private Piece selectPiece; //last piece that is selected; null if nothing is selected;
    private int selectX = -1;
    private int selectY = -1;
    private int player;  //0 for fire, 1 for water;
    private boolean moved = false;
    private int numFire = 0;
    private int numWater = 0;

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private void drawEmptyBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
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
                Piece p = piecesP[i][j];
                String type;
                if (p!=null) {
                    if (p.isBomb()) {
                        type = "bomb";
                    } else if (p.isShield()) { 
                        type = "shield";
                    } else type = "pawn";
                    if (p.isFire()) type = type + "-fire";
                    else            type = type + "-water";
                    if (p.isKing()) type = type + "-crowned";
                    StdDrawPlus.picture(i + .5, j + .5, "img/"+ type + ".png", 1, 1);
                // if ((j == 0) && (i % 2==0)) {
                //     StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                // } else if ((j == 1) && (i % 2!=0)) {
                //     StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                // } else if ((j == 2) && (i % 2==0)) {
                //     StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                // } else if ((j == 5) && (i % 2!=0)) {
                //     StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                // } else if ((j == 6) && (i % 2==0)) {
                //     StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                // } else if ((j == 7) && (i % 2!=0)) {
                //     StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                // }
                } 
            }               
        }
    }

    public Board(boolean shouldBeEmpty) {
        if (!shouldBeEmpty) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((j == 0) && (i % 2==0)) {
                        piecesP[i][j] = new Piece(true, this, i, j, "pawn");
                        numFire = numFire+1;
                    } else if ((j == 1) && (i % 2!=0)) {
                        piecesP[i][j] = new Piece(true, this, i, j, "shield");
                        numFire = numFire+1;
                    } else if ((j == 2) && (i % 2==0)) {
                        piecesP[i][j] = new Piece(true, this, i, j, "bomb");
                        numFire = numFire+1;
                    } else if ((j == 5) && (i % 2!=0)) {
                        piecesP[i][j] = new Piece(false, this, i, j, "bomb");
                        numWater = numWater + 1;
                    } else if ((j == 6) && (i % 2==0)) {
                        piecesP[i][j] = new Piece(false, this, i, j, "shield");
                        numWater = numWater+1;
                    } else if ((j == 7) && (i % 2!=0)) {
                        piecesP[i][j] = new Piece(false, this, i, j, "pawn");
                        numWater = numWater+1;
                    }
                }                
            }
        }     
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        boolean endGame = false;

        // * Monitors for mouse presses. Wherever the mouse is pressed,
        //     a new piece appears. 

        Board newboard = new Board(false);
        newboard.drawBoard();
        //If true, call newboard.drawEmptyBoard instead.
        
        while (!endGame) {           
            if (StdDrawPlus.mousePressed()) {
                Double t = StdDrawPlus.mouseX();
                int x = t.intValue();
                t = StdDrawPlus.mouseY();
                int y = t.intValue();
                Piece p = newboard.piecesP[x][y];
                if (newboard.canSelect(x,y)) {
                    newboard.select(x,y);
                    newboard.drawBoard();
                    if (newboard.selectPiece!=null) {
                       newboard.drawWhitePiece(newboard.selectPiece,newboard.selectX,newboard.selectY);
                    }
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (newboard.canEndTurn()) {
                    newboard.endTurn();
                    newboard.drawBoard();
                }
                String winner = newboard.winner();
                if (winner!=null){
                    System.out.println(winner);
                    endGame = true;
                }
            }
        StdDrawPlus.show(10);           
        }   
    }

    public Piece pieceAt(int x, int y) {
    	if (x>7 || y>7) {
    		return null;
    	} else if (piecesP[x][y]==null) {
    		return null;
    	} else {
    		return piecesP[x][y];
    	}
    }

    public void place(Piece p, int x, int y) {
    	if (x<=7 && x>=0 && y>=0 && y<=7 && p!=null){
    		piecesP[x][y] = p;	
    	}
        if (p.isFire()) numFire = numFire+1;
        else            numWater = numWater+1;
    }

    private void drawWhitePiece(Piece p, int x, int y) {
        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        String type;
        if (p.isBomb()) {
            type = "bomb";
        } else if (p.isShield()) { 
            type = "shield";
        } else type = "pawn";
        if (p.isFire()) type = type + "-fire";
        else type = type + "-water";
        if (p.isKing()) type = type + "-crowned";
        StdDrawPlus.picture(x + .5, y + .5, "img/"+ type + ".png", 1, 1);
    }



    public void select(int x, int y) {
        if (pieceAt(x,y)!=null) {
            selectPiece = piecesP[x][y];
            selectX = x;
            selectY = y;
        } else {
            selectPiece.move(x, y);
            moved = true;
            selectX = x;
            selectY = y;
            if (piecesP[x][y]==null) {
                selectPiece = null;
            }
        }
    }   


    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (piecesP[xf][yf]!=null) return false;
        Piece p = piecesP[xi][yi];
        if (p==null) return false;
        if (p.isKing()) {
            if (Math.abs(yi-yf)==Math.abs(xi-xf)) {
                if (Math.abs(yi-yf)==1) {
                    return true;
                } else if ((Math.abs(yi-yf)==2) && piecesP[(xi+xf)/2][(yi+yf)/2]!=null && piecesP[(xi+xf)/2][(yi+yf)/2].side()!=p.side()) {              
                        return true;
                    }
            } else return false;
        } else {
            if ((yf-yi)==Math.abs(xi-xf) && p.isFire()) {
                if (yf-yi==1) {
                    return true;
                } else if ( yf-yi==2 && piecesP[(xi+xf)/2][(yi+yf)/2]!=null && piecesP[(xi+xf)/2][(yi+yf)/2].side()!=p.side()) {
                    return true;
                }
            } else if ((yi-yf)==Math.abs(xi-xf) && !p.isFire()) {
                if (yi-yf==1) {
                    return true;
                } else if ( yi-yf==2 && piecesP[(xi+xf)/2][(yi+yf)/2]!=null && piecesP[(xi+xf)/2][(yi+yf)/2].side()!=p.side()) {
                    return true;
                }
            } else return false;
        }
        return false;
    }

    private boolean validCapture(int xi, int yi, int xf, int yf) {
        if (validMove(xi, yi, xf, yf)) {
            Piece p = piecesP[xi][yi];
            if (p.isKing()) {
                if (Math.abs(yi-yf)==2 && piecesP[(xi+xf)/2][(yi+yf)/2]!=null && piecesP[(xi+xf)/2][(yi+yf)/2].side()!=p.side()) {             
                    return true;
                }
            } else if (p.isFire()) {
                if (yf-yi == 2 && piecesP[(xi+xf)/2][(yi+yf)/2]!=null && piecesP[(xi+xf)/2][(yi+yf)/2].side()!=p.side()) {
                    return true;
                }
            } else if (!p.isFire()) {
                if (yi-yf == 2 && piecesP[(xi+xf)/2][(yi+yf)/2]!=null && piecesP[(xi+xf)/2][(yi+yf)/2].side()!=p.side()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canSelect(int x, int y) {
        if (piecesP[x][y]!=null) {
            if (piecesP[x][y].side() == player) {
                if (selectPiece == null || (selectPiece != null && moved == false)) 
                return true;
            }
        } else if (selectPiece!=null) {
            if (!moved && validMove(selectX, selectY, x, y)) return true;
            else if (selectPiece.hasCaptured() && validCapture(selectX, selectY, x, y)) return true;
        }
        return false;
    }

    public Piece remove(int x, int y) {
        if (x>7 || y>7) {
            System.out.println("X or Y out of bound!");
            return null;
        } else if (pieceAt(x,y) == null) {
            System.out.println("No piece can be removed!");
            return null;
        }
        Piece p = pieceAt(x,y);
        if (p.isFire()) numFire = numFire-1;
        else            numWater = numWater-1;
        piecesP[x][y] = null;
        return p;
    }

    public boolean canEndTurn() {
        if (moved || (selectPiece!=null && selectPiece.hasCaptured())) {
            return true;
        } else return false;
    }

    public void endTurn() {
        player = 1 - player;
        if (selectPiece!=null) piecesP[selectX][selectY].doneCapturing();
        moved = false;
        selectPiece = null;
        selectX = -1;
        selectY = -1;
    }

    public String winner() {
        if (numWater==0 && numFire == 0) {
            return "No one";
        } else if (numFire==0) {
            return "Water";
        } else if (numWater==0) {
            return "Fire";      
        }
        return null;
    }
}
