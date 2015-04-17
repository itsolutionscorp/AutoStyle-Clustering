
public class Board {

	private boolean empty;
    private Piece[][] pieces;
    private int turn = 0;
    private boolean moved;
    private boolean captured;
    private int selectedX;
    private int selectedY;
    private boolean selected = false;
    private boolean keepGoing = true;

    public Board (boolean shouldBeEmpty) {
        empty = shouldBeEmpty; 
        pieces = new Piece[8][8];
        if (empty == false) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                        if ((j == 0) && (i%2 ==0)) {
                            pieces[i][j] = new Piece(true, this, i, j, "pawn");
                        }
                        if ((j == 1) && (i%2 !=0)) {
                            pieces[i][j] = new Piece(true, this, i, j, "shield");
                        }
                        if ((j == 2) && (i%2 ==0)) {
                            pieces[i][j] = new Piece(true, this, i, j, "bomb");
                        }
                        if ((j == 5) && (i%2 !=0)) {
                            pieces[i][j] = new Piece(false, this, i, j, "bomb");
                        }
                        if ((j == 6) && (i%2 ==0)) {
                            pieces[i][j] = new Piece(false, this, i, j, "shield");
                        }
                        if ((j == 7) && (i%2 !=0)) {
                            pieces[i][j] = new Piece(false, this, i, j, "pawn");
                        }
                }
            }
        }
        drawBoard(8);
        drawPieces(8);
    }

	public static void main (String args[]) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);
        while(b.keepGoing) {
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                // System.out.println(x);
                // System.out.println(y);
                if (b.pieces[x][y]!=null){
                    System.out.println(b.pieces[x][y]);
                }
                if ((x>=0)&&(y>=0)&&(x<=7)&&(y<=7)) {
                    b.drawBoard(8);
                    if (b.canSelect(x,y)) {
                    b.select(x, y);
                    }
                    b.drawPieces(8);
                }
            }
            StdDrawPlus.show(100);
            if (StdDrawPlus.isSpacePressed()) {
                b.endTurn();
                b.drawBoard(8);
                b.drawPieces(8);
            }
       	}
    }

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
            }
        }
    
    private void drawPieces(int N) {            
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()){
                        if (pieces[i][j].isShield()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                        } else if (pieces[i][j].isBomb()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            }
                        } else {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            }
                        }
                    } else if (pieces[i][j].isFire()==false) {
                        if (pieces[i][j].isBomb()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            }
                        } else if (pieces[i][j].isShield()) {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                        } else {
                            if (pieces[i][j].isKing()) {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            } else {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            }
                        }
                    }
            }
        }
        }
    }


    public Piece pieceAt(int x, int y) {
        if ((x>=0)&&(y>=0)&&(x<=7)&&(y<=7)) {
            if ((pieces[x][y]!=null)) {
                return pieces[x][y];
            }
        }
        return null;
    }

    public boolean canSelect(int x, int y) {
            if (pieces[x][y] != null) {
                if ((moved == false)||(selected==false)) {
                    if (pieces[x][y].side() == turn) {
                        return true;
                    }
                }
            } else if ((pieces[x][y] == null)&&(validMove(selectedX, selectedY, x, y))) {  
                if ((selected == true)&&(moved == false)) {
                    return true;
                } else if (captured == true){
                    return true;
                    }
                }
        return false;
    } 

    private boolean validMove(int xi, int yi, int xf, int yf) {
        int xMinus = xi-1;
        int yMinus = yi-1;
        int xAdd = xi + 1;
        int yAdd = yi + 1;
        // if ((xi<=7)&&(yi<=7)&&(xf<=7)&&(yf<=7)) {
            if ((pieces[xf][yf] == null)&&(pieces[xi][yi]!= null)) {
                if (pieces[xi][yi].isKing()) {
                    if ((xi+1==xf)&&(yi+1==yf)) {
                            if (moved == false) {
                            return true;
                            }
                        }
                        if ((xi-1==xf)&&(yi+1==yf)) {
                            if (moved == false) {
                            return true;
                            }
                        }
                     else if (yi+2==yf){
                        if ((xi+2==xf)&&(pieces[xAdd][yAdd]!=null)&&((pieces[xi][yi].side()) != (pieces[xAdd][yAdd].side()))) {
                            return true;
                        } else if ((xi-2==xf)&&(pieces[xMinus][yAdd]!=null)&&((pieces[xi][yi].side()) != (pieces[xMinus][yAdd].side()))) {
                            return true;
                        }
                    }
                    if ((xi+1==xf)&&(yi-1==yf)){
                            if (moved == false) {
                            return true;
                            }
                        } 
                        else if ((xi-1==xf)&&(yi-1==yf)){
                            if (moved == false) {
                            return true;
                            }
                        }
                    else if (yi-2==yf) {
                    if ((xi+2==xf)&&(pieces[xAdd][yMinus]!=null)&&((pieces[xi][yi].side()) != (pieces[xAdd][yMinus].side()))) {
                        return true;
                    } else if ((xi-2==xf)&&(pieces[xMinus][yMinus]!=null)&&((pieces[xi][yi].side()) != (pieces[xMinus][yMinus].side()))) {
                        return true;
                    }
                }
                }
                else if ((pieces[xi][yi].isFire()==true)){
                    // if ((yi-1>=0)&&(xi-1>=0)&&(xi+1<=7)&&(yi+1<=7)) {
                        if ((xi+1==xf)&&(yi+1==yf)) {
                            if (moved == false) {
                            return true;
                            }
                        }
                        if ((xi-1==xf)&&(yi+1==yf)) {
                            if (moved == false) {
                            return true;
                            }
                        }
                    // }
                    else if (yi+2==yf){
                        if ((xi+2==xf)&&(pieces[xAdd][yAdd]!=null)&&((pieces[xi][yi].side()) != (pieces[xAdd][yAdd].side()))) {
                            return true;
                        } else if ((xi-2==xf)&&(pieces[xMinus][yAdd]!=null)&&((pieces[xi][yi].side()) != (pieces[xMinus][yAdd].side()))) {
                            return true;
                        }
                    }
                } else if ( (pieces[xi][yi].isFire()==false)) {
                    // if ((yi-1>=0)&&(xi-1>=0)&&(xi+1<=7)&&(yi+1<=7)) {
                        if ((xi+1==xf)&&(yi-1==yf)){
                            if (moved == false) {
                            return true;
                            }
                        } 
                        else if ((xi-1==xf)&&(yi-1==yf)){
                            if (moved == false) {
                            return true;
                            }
                        }
                    // } 
                    else if (yi-2==yf) {
                        if ((xi+2==xf)&&(pieces[xAdd][yMinus]!=null)&&((pieces[xi][yi].side()) != (pieces[xAdd][yMinus].side()))) {
                            return true;
                        } else if ((xi-2==xf)&&(pieces[xMinus][yMinus]!=null)&&((pieces[xi][yi].side()) != (pieces[xMinus][yMinus].side()))) {
                            return true;
                        }
                    }
                
                } 
            }
        // }
        return false;
    }

    public void select(int x, int y) {
            if (selected==true) {
                if (pieces[x][y]==null) {
                pieces[selectedX][selectedY].move(x, y);
                remove(selectedX, selectedY);
                if (pieces[x][y]!=null){
                captured = pieces[x][y].hasCaptured();
                }
                moved = true;
            }    
            } else if (pieces[x][y]!=null) { 
                selected = true;
            }         
            selectedX = x;
            selectedY = y;
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
    }

    public void place(Piece p, int x, int y) {
        if ((p!=null)) {
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        if ((x<0)||(y<0)||(x>7)||(y>7)) {
            System.out.println("Out of bounds");
            return null;
        } else if (pieces[x][y] == null) {
            System.out.println("No such piece");
            return null;
        }
        else {
            Piece p = pieces[x][y];
            pieces[x][y] = null;
            return p;
        }
    }

    public boolean canEndTurn() {
        if ((moved == true)||(captured == true)) {
            return true;
        }
        return false;
    }

    public void endTurn() {
        if (canEndTurn()) {
            if (turn == 0) {
                turn = 1;
            } else {
                turn = 0;
            }
            moved = false;
            captured = false;
            selected = false;
            if (pieces[selectedX][selectedY]!=null) {
                pieces[selectedX][selectedY].doneCapturing();
            }
            if (winner() != null) {
                keepGoing = false;
            }
        }
    }

    public String winner() {
        int firePop = 0;
        int waterPop = 0;
         for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j]!=null) {
                    if (pieces[i][j].isFire()==true) {
                        firePop = firePop + 1;
                    } else if (pieces[i][j].isFire()==false) {
                        waterPop = waterPop + 1;
                    }
                }
            }
        }
        if ((firePop == 0)&&(waterPop == 0)) {
            return "No one";
        } else if (firePop == 0) {
            return "Water";
        } else if (waterPop == 0) {
            return "Fire";
        } else {
            return null;
        }
    }

}