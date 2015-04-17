public class Board {
	
	/** Location of pieces. */
    private static int N = 8;
    private Piece[][] pieces = new Piece[N][N];
    private boolean fireTurn = true;
    private boolean[][] selected = new boolean[N][N];
    private boolean moved = false;
    private boolean captured = false;
    
    //private Board b;

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */
    private static void drawBoard(int N, Board b) {
        //StdDrawPlus.show(0);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (b.selected[i][j]) {
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
                if (b.pieces[i][j] != null) {
                    if(b.pieces[i][j].isBomb()) {
                        if(b.pieces[i][j].isFire()) {
                            if (b.pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            } else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                        } else{
                            if (b.pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            } else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                        }
                    }
                    else if (b.pieces[i][j].isShield()) {
                        if (b.pieces[i][j].isFire()) {
                            if (b.pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            } else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                        } else{
                            if (b.pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            } else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                        }
                    }
                    else {
                        if (b.pieces[i][j].isFire()) {
                            if (b.pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            } else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        } else{
                            if (b.pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            } else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            }
                        }
                    }
                    
                } 
            }
        }
        //StdDrawPlus.show(0);
        
    }

    public static void main(String[] args) {
        //int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        //pieces = new boolean[N][N];
        Board b = new Board(false);
        //Board b = new Board(true);
        boolean game_in_progress = true;
        
        while(game_in_progress) {
            drawBoard(N, b);
            //b.pieces[0][0] = new Piece(true, b, 0, 0, "pawn");
            //b.place(b.remove(0, 0), 7, 7);
            
            //b = new Board(false);

            if (b.winner() != null) {
                game_in_progress = false;
                System.out.println("Game over! The winner is " + b.winner());
            }

            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }          
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (b.selected[i][j]) {
                            if (b.canSelect(x, y)) {
                                b.select(x, y);
                                /*if (b.pieceAt(i, j) != null) {
                                    b.pieceAt(i, j).move(x, y);
                                }*/
                            }
                        }
                    }
                }
                if (b.canSelect(x, y)) {
                    b.select(x, y);
                }
                //pieces[(int) x][(int) y] = true;
            } 

             
            StdDrawPlus.show(30);
        }
    }
	
	public Board(boolean shouldBeEmpty) {
		//drawBoard(N);
        if(!shouldBeEmpty) {
            //StdDrawPlus.show(0);
            for(int x=0; x < 7; x+=2) {
                StdDrawPlus.picture(x + .5, 0.5, "img/pawn-fire.png", 1, 1);
                pieces[x][0] = new Piece(true, this, x, 0, "pawn");
                StdDrawPlus.picture(x + 1.5, 1.5, "img/shield-fire.png", 1, 1);
                pieces[x+1][1] = new Piece(true, this, x+1, 1, "shield");
                StdDrawPlus.picture(x + .5, 2.5, "img/bomb-fire.png", 1, 1);
                pieces[x][2] = new Piece(true, this, x, 2, "bomb");
                StdDrawPlus.picture(x + 1.5, 5.5, "img/bomb-water.png", 1, 1);
                pieces[x+1][5] = new Piece(false, this, x+1, 5, "bomb");
                StdDrawPlus.picture(x + .5, 6.5, "img/shield-water.png", 1, 1);
                pieces[x][6] = new Piece(false, this, x, 6, "shield");
                StdDrawPlus.picture(x + 1.5, 7.5, "img/pawn-water.png", 1, 1);
                pieces[x+1][7] = new Piece(false, this, x+1, 7, "pawn");
            }
            //StdDrawPlus.show(0);
        }

	}

	public Piece pieceAt(int x, int y) {
        if (x<0 || x>7 || y<0 || y>7 || pieces[x][y] == null) {
            return null;
        }
        if (pieces[x][y] == null){
            return null;
        }
        return pieces[x][y];

	}
    
	public boolean canSelect(int x, int y) {
        if (pieces[x][y] != null) {
            if (pieces[x][y].isFire()) {
                if(!fireTurn) {
                    return false;
                }
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (selected[i][j]) {
                            if (moved) {
                                return false;
                            }
                            return true;
                        }
                    }
                }
                return true;
            }
            if (fireTurn) {
                return false;
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (selected[i][j] == true) {
                        if (moved) {
                            return false;
                        }
                        return true;
                    }
                }
            }
            return true;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (selected[i][j]) {
                    if (!moved) {
                        return validMove(i, j, x, y);
                    }
                    if (pieces[i][j] == null) {
                        return false;
                    }
                    if (pieces[i][j].hasCaptured()) {
                        return validCapture(i, j, x, y);
                    }
                    return false;
                }
            }
        }
        return false;
	}

	
	private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece p = pieces[xi][yi];
        if (Math.abs(xf - xi) == 2 && Math.abs(yf - yi) == 2) {
            return validCapture(xi, yi, xf, yf);
        }
        else {
            if (Math.abs(xf - xi) != 1) {
                return false;
            }
            if (yf - yi == 1) {
                if (p.isKing()) {
                    return true;
                }
                return p.isFire();
            }
            if (yf - yi == -1) {
                if (p.isKing()) {
                    return true;
                }
                return !p.isFire();
            }
            return false;
        }
	} 

    private boolean validCapture(int xi, int yi, int xf, int yf) {
        if (Math.abs(xf - xi) != 2 || Math.abs(yf-yi) != 2) {
            return false;
        }
        if (xf - xi == 2) {
            if (yf - yi == 2) {
                if (pieces[xi+1][yi+1] != null) {
                    if (pieces[xi][yi].isFire()) {
                        return !pieces[xi+1][yi+1].isFire();
                    }
                    if (pieces[xi][yi].isKing()) {
                        return pieces[xi+1][yi+1].isFire();
                    }
                }
                return false;
            }
            //yf - yi == -2
            if (pieces[xi+1][yi-1] != null) {
                if (!pieces[xi][yi].isFire()) {
                    return pieces[xi+1][yi-1].isFire();
                }
                if (pieces[xi][yi].isKing()) {
                    return !pieces[xi+1][yi-1].isFire();
                }
            }
            return false;
        }
        //xf - xi == -2
        if (yf - yi == 2) {
            if (pieces[xi-1][yi+1] != null) {
                if (pieces[xi][yi].isFire()) {
                    return !pieces[xi-1][yi+1].isFire();
                }
                if (pieces[xi][yi].isKing()) {
                    return pieces[xi-1][yi+1].isFire();
                }
            }
            return false;
        }
        //yf - yi == -2
        if (pieces[xi-1][yi-1] != null) {
            if (!pieces[xi][yi].isFire()) {
                return pieces[xi-1][yi-1].isFire();
            }
            if (pieces[xi][yi].isKing()) {
                return !pieces[xi-1][yi-1].isFire(); 
            }
        }
        return false;

    }

	public void select(int x, int y) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (selected[i][j]) {
                    if (pieceAt(i, j) != null) {
                        pieceAt(i, j).move(x, y);
                    }
                }
                if(i == x && j == y) {
                    selected[i][j] = true;
                } else {
                    selected[i][j] = false;
                }
            }
        }
        //selected[x][y] = true;
	}

	public void place(Piece p, int x, int y) {
        if (x>=0 && x<=7 && y>=0 && y<=7 && p!=null) {

            if (pieces[x][y] != null) {
                remove(x, y);
            }
            
            pieces[x][y] = p;
            moved = true;
            captured = p.hasCaptured();
        }
	}

	public Piece remove(int x, int y) {
        if (x<0 || x>7 || y<0 || y>7) {
            System.out.println("Remove Error: input coordinates out of bounds!!");
            return null;
        }
        if (pieces[x][y] == null) {
            System.out.println("Remove Error: no piece to remove at given coordinates (" + x + ", " + y + ")!!");
            return null;
        }
        Piece p = pieces[x][y];
        
        pieces[x][y] = null;
        return p;
	}


	public boolean canEndTurn() {
        return moved;
	}

	public void endTurn() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                selected[i][j] = false;
                if (pieces[i][j] != null) {
                    pieces[i][j].doneCapturing();
                }
            }
        }
        fireTurn = !fireTurn;
        moved = false;
        winner();
	}

	public String winner() {
        int firePieces = 0;
        int waterPieces = 0;
        for (int i = 0; i < N; i ++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) {
                        firePieces++;
                    } else {
                        waterPieces++;
                    }
                } 
            }
        }
        if (firePieces == 0 && waterPieces == 0) {
            return "No one";
        }
        if (firePieces > 0 && waterPieces == 0) {
            return "Fire";
        }
        if (firePieces == 0 && waterPieces > 0) {
            return "Water";
        }
        return null;
	}
}
