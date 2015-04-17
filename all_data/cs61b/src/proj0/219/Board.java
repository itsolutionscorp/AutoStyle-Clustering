public class Board {
	private boolean[][] pieces;
	private boolean empty;
	private Piece[][] stored;
    private int player0 = 0; //fire ... side = 0
    private int player1 = 1; //water .. side = 1
    private int current = player0;
    private int next = player1;
    private boolean movedPiece = false;
    private boolean selected =  false;
    private int Xs;
    private int Ys;
    private int firePieces = 12;
    private int waterPieces = 12;
//GRADE//

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */ private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j]) {
                    select(i, j);
                    StdDrawPlus.filledSquare(i + .5, j + .5,.5);
                }
            }
        }
    }

/* PUBLIC METHODS ***********************************************/        
    public Board(boolean shouldBeEmpty) {
        empty = shouldBeEmpty;
        if (!empty) {
            this.storePieces(8);
        }
        else {
            this.storeNull(8);
        }
    }


    public Piece pieceAt(int x, int y) {
        if (x >= stored.length || y >= stored.length) {
            return null;
        }
        return stored[x][y];
    }

    public void place(Piece p, int x, int y) {
        int N = stored.length;

        if (x >= N || y >= N || p == null) { }
        else {
            stored[x][y] = p;
            if(selected) 
                movedPiece = true;

        } 
    }

    public Piece remove(int x, int y) {
        //if (x>7 || y> 7) { }
        Piece removed = stored[x][y];
        stored[x][y] = null;
        return removed;

    }

    public boolean canSelect(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            return false;
        }
        if(pieceAt(x, y) != null) {
            if (!movedPiece) {
                if (pieceAt(x, y).side() == current) {
                    return true;
                }

            }
        }
        else {
            if(selected) {
                if (validMoveSimple(Xs, Ys, x, y)) {
                    if(!movedPiece) {
                        return true;
                    }
                }

                if (validMoveCapture(Xs, Ys, x, y)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void select(int x, int y) {
        if(selected) {
            if(validMoveSimple(Xs, Ys, x, y) || validMoveCapture(Xs, Ys, x, y)) {
                pieceAt(Xs, Ys).move(x, y);
                movedPiece = true;
            }
        }
        selected = true;
        Xs = x;
        Ys = y;
    }

    public boolean canEndTurn() {
        if (movedPiece) {
            return true;
        }
        return false;
    }

    public void endTurn() {
        /*for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    pieces[i][j] = false;
                } 
                    
            }
        }*/
        switchTurn();
        movedPiece = false;
        selected = false;
        for(int i=0;i<8;i+=1) {
            for(int j=0;j<8;j+=1) {
                if((j+i) % 2 == 0) {
                    if(stored[i][j] != null && stored[i][j].hasCaptured()) {
                        stored[i][j].doneCapturing();

                    }
                }
            }
        }


    }

/*******PLAYER FUNCTIONALITY*************************/
    private void switchTurn() {
        int temp = current;
        current = next;
        next = temp;
    }
    private void indicateTurn () {
        StdDrawPlus.setPenColor(StdDrawPlus.GREEN);
        if (current == player0) {
            StdDrawPlus.filledSquare(7.5, .5, .25);
        }
        else {
            StdDrawPlus.filledSquare(.5, 7.5, .25);
        }
       // StdDrawPlus.show(100);

    }

/* DRAWING AND INITIALIZING **************************************************/


     private void drawPieces(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (stored[i][j] != null) {
                    Piece p = stored[i][j];
                    StdDrawPlus.picture(i + .5, j + .5, getPath(p), 1, 1);
                }
            }
        }
    }


    private void storeNull(int N) {
        Piece[][] s = new Piece[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s[i][j] = null;
            }
        }
        stored = s;
    }

    private String getPath(Piece p) {
        String team, type;

        if (p.isFire())   
            team = "fire";
        else              
            team = "water";
        if (p.isShield())    
            type = "shield";
        else if (p.isBomb())
            type = "bomb";
        else                 
            type = "pawn";
        if(p.isKing()) {
            return String.format("img/%s-%s-crowned.png", type, team);
        }
        else {
           
        return String.format("img/%s-%s.png", type, team);
        }
    }

    private void storePieces(int N) {
        Piece[][] s = new Piece[N][N];
          for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) {
                    if (j == 0) {
                     s[i][j] = new Piece(true, this, i, j, "pawn");
                 }
                    if (j == 1) {
                     s[i][j] = new Piece(true, this, i, j, "shield");
                 }
                    if (j == 2) {
                     s[i][j] = new Piece(true, this, i, j, "bomb");
                 }
                    if (j == N-3) {
                     s[i][j] = new Piece(false, this, i, j, "bomb");
                 }
                    if (j == N-2) {
                     s[i][j] = new Piece(false, this, i, j, "shield");
                 }
                    if (j == N-1) {
                     s[i][j] = new Piece(false, this, i, j, "pawn"); 
                     }                  
                }
                else {
                    s[i][j] = null;
                }
            }
        }
        stored = s;
    }

////////////////////////////////////////////////////////
    /** 
     *@PIECE_MOVEMENT
     */
///////////////////////////////////////////////////////
    private boolean edgeLeftFire(int x, int y) {
        if (y != stored.length -1 && x == 0) {
            return true;
        }

        return false;
    }


    private boolean edgeLeftWater(int x, int y) {
        if (y != 0 && x == 0) {
            return true;
        }
        
        return false;
    }

    private boolean edgeLeftCapture(int x) {
        if(x < 2) {
            return true;
        }
        return false;
    }

    private boolean edgeRightFire(int x, int y) {
        if (y != stored.length-1 && x == stored.length-1){
            return true;
        }
       
        return false;
    }

    private boolean edgeRightCapture(int x) {
        if (x > 5) {
            return true;
        }
        return false;
    }

    private boolean edgeRightWater(int x, int y) {
        if (y != 0 && x == stored.length-1) {
            return true;
        }
        
        return false;
    }


    private boolean rightFireSimple(int xi, int yi, int xf, int yf) {
        Piece p = pieceAt(xi+1, yi+1);
        if((p == null) && (xi+1 == xf) && (yi+1 == yf)){
            return true;
        }
        return false;
    }

    private boolean rightFireCapture(int xi, int yi, int xf, int yf) {
        if (yi < 6) {
            Piece capturing = pieceAt(xi, yi);
            Piece p1 = pieceAt(xi+1, yi+1);
            Piece p2 = pieceAt(xi+2, yi+2);
            if (capturing.isFire()){
                if (p1 != null && !p1.isFire()){
                    if (p2 == null && xi+2 == xf && yi+2 == yf) {
                        return true;
                    }
                 }
            }
            else {
                if (p1 != null && p1.isFire()){
                    if (p2 == null && xi+2 == xf && yi+2 == yf) {
                        return true;
                    }
                 }
             }
        }
        return false;
    }


    private boolean rightWaterSimple(int xi, int yi, int xf, int yf) {
        Piece p = pieceAt(xi+1, yi-1);
        if((p == null) && (xi+1 == xf) && (yi-1 == yf)) {
            return true;
        }
        return false;
    }

    private boolean rightWaterCapture(int xi, int yi, int xf, int yf) {
        if (yi > 1) {
            Piece capturing = pieceAt(xi, yi);
            Piece p1 = pieceAt(xi+1, yi-1);
            Piece p2 = pieceAt(xi+2, yi-2);
            if(!capturing.isFire()) {
                if (p1 != null && p1.isFire()){
                    if (p2 == null && xi+2 == xf && yi-2 == yf) {
                        return true;
                    }
                }
            }
            else {
                if (p1 != null && !p1.isFire()){
                    if (p2 == null && xi+2 == xf && yi-2 == yf) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean leftFireSimple(int xi, int yi, int xf, int yf) {
        Piece p = pieceAt(xi-1 ,yi+1);
        if((p == null) && (xi-1 == xf) && (yi+1 == yf)) {
            return true;
        }
        return false;
    }

    private boolean leftFireCapture(int xi, int yi, int xf, int yf) {
        Piece capturing = pieceAt(xi, yi);
        Piece p1 = pieceAt(xi-1, yi+1);
        Piece p2 = pieceAt(xi-2, yi+2);
        if(capturing.isFire()) {
            if (p1 != null && !p1.isFire()){
                if (p2 == null && xi-2 == xf && yi+2 == yf) {
                    return true;
                }
            }
        }
        else {
            if (p1 != null && p1.isFire()){
                if (p2 == null && xi-2 == xf && yi+2 == yf) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean leftWaterSimple(int xi, int yi, int xf, int yf) {
        Piece p = pieceAt(xi-1, yi-1);
        if ((p == null) && (xi-1 == xf) && (yi-1 == yf)) {
            return true;
        }
        return false;
    }

    private boolean leftWaterCapture(int xi, int yi, int xf, int yf) {
        Piece p1 = pieceAt(xi-1, yi-1);
        Piece p2 = pieceAt(xi-2, yi-2);
        Piece capturing = pieceAt(xi, yi);
        if(!capturing.isFire()) {
            if (p1 != null && p1.isFire()){
                if (p2 == null && xi-2 == xf && yi-2 == yf) {
                    return true;
                }
            }
        }
        else {
            if (p1 != null && !p1.isFire()){
                if (p2 == null && xi-2 == xf && yi-2 == yf) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean fireSimple(int xi, int yi, int xf, int yf) {
        Piece p1 = pieceAt(xi-1, yi+1);
        Piece p2 = pieceAt(xi+1, yi+1);
        if ((p1 == null && xi-1 == xf && yi+1 == yf) || 
            (p2 == null && xi+1 == xf && yi+1 == yf)) {
                return true;
        }
        return false;
    }

    private boolean fireCapture(int xi, int yi, int xf, int yf) {
        Piece p1 = pieceAt(xi+1, yi+1);
        Piece p2 = pieceAt(xi-1, yi+1);
        Piece c1 = pieceAt(xi+2, yi+2);
        Piece c2 = pieceAt(xi-2, yi+2);
        Piece capturing = pieceAt(xi, yi);

        if(capturing.isFire()) {
            if (p1 != null && !p1.isFire()) {
                if(c1 == null && xi+2 == xf && yi+2 == yf) {
                    return true;
                }
            } else if (p2 != null && !p2.isFire()) {
                if (c2 == null && xi-2 == xf && yi+2 == yf) {
                    return true;
                }
            }
        }
        else {
            if (p1 != null && p1.isFire()) {
                if(c1 == null && xi+2 == xf && yi+2 == yf) {
                    return true;
                }
            } else if (p2 != null && p2.isFire()) {
                if (c2 == null && xi-2 == xf && yi+2 == yf) {
                    return true;
                }
            }

        }
        return false;
    }

    private boolean waterSimple(int xi, int yi, int xf, int yf) {
        Piece p1 = pieceAt(xi-1, yi-1);
        Piece p2 = pieceAt(xi+1, yi-1);
        if ((p1 == null && xi-1 == xf && yi-1 == yf) || 
            (p2 == null && xi+1 == xf && yi-1 == yf)) {
                return true;
        }
        return false;
    }

    private boolean waterCapture(int xi, int yi, int xf, int yf) {
        Piece p1 = pieceAt(xi+1, yi-1);
        Piece p2 = pieceAt(xi-1, yi-1);
        Piece c1 = pieceAt(xi+2, yi-2);
        Piece c2 = pieceAt(xi-2, yi-2);
        Piece capturing = pieceAt(xi, yi);
        if(!capturing.isFire()) {        
            if (p1 != null && p1.isFire()) {
                if(c1 == null && xi+2 == xf && yi-2 == yf) {
                    return true;
                }
            } else if (p2 != null && p2.isFire()) {
                if (c2 == null && xi-2 == xf && yi-2 == yf) {
                    return true;
                }
            }
        }
        else {
            if (p1 != null && !p1.isFire()) {
                if(c1 == null && xi+2 == xf && yi-2 == yf) {
                    return true;
                }
            } else if (p2 != null && !p2.isFire()) {
                if (c2 == null && xi-2 == xf && yi-2 == yf) {
                    return true;
                }
            }
        }
        return false;
    }


///////////////////////////////////////////////////////
    /**
     * @VALID_MOVES
     */
///////////////////////////////////////////////////////
    private boolean kingMovesSimple(int xi, int yi, int xf, int yf) {
        if (yi == 0) {
            if (xi == 0) {
                return rightFireSimple(xi, yi, xf, yf);
            }
            return fireSimple(xi, yi, xf, yf);
        }
        if (yi == 7) {
            if (xi == 7) {
                return leftWaterSimple(xi, yi, xf, yf);
            }
            return waterSimple(xi, yi, xf, yf);
        }

        if (xi == 0) {
            Piece pUp = pieceAt(xi+1, yi+1);
            Piece pDown = pieceAt(xi+1, yi-1);
            if ((pUp == null && xi+1 == xf && yi+1 == yf) ||
                (pDown == null && xi+1 == xf && yi-1 == yf)) {
                return true;
            }
        }
        if (xi == 7) {
            Piece pUp = pieceAt(xi-1, yi+1);
            Piece pDown = pieceAt(xi-1, yi-1);
            if((pUp == null && xi-1 == xf && yi+1 == yf) ||
               (pDown == null && xi-1 == xf && yi-1 == yf)) {
                return true;
            }
        }
        else {
            Piece pUpR = pieceAt(xi+1, yi+1);
            Piece pUpL = pieceAt(xi-1, yi+1);
            Piece pDownR = pieceAt(xi+1, yi-1);
            Piece pDownL = pieceAt(xi-1, yi-1);
            if ((pUpR == null && xi+1 == xf && yi+1 == yf) ||
                (pUpL == null && xi-1 == xf && yi+1 == yf) ||
                (pDownR == null && xi+1 == xf && yi-1 == yf)||
                (pDownL == null && xi-1 == xf && yi-1 == yf)) {
                return true;
            }
        }
        return false;

    }

    private boolean kingMovesCapture(int xi, int yi, int xf, int yf) {
        if(yi == 0 || yi ==1) {
            if(xi == 0 || xi == 1) {
                return rightFireCapture(xi, yi, xf, yf);
            }
            if(xi == 6 || xi == 7) {
                return leftFireCapture(xi, yi, xf, yf);
            }
            else {
                return fireCapture(xi, yi, xf, yf);
            }
        }
        if(yi == 7 || yi == 6) {
            if(xi == 1 || xi == 0) {
                return rightWaterCapture(xi, yi, xf, yf);
            }
            if(xi == 7 || xi == 6) {
                return leftWaterCapture(xi, yi, xf, yf);
            }
            else {
                return waterCapture(xi, yi, xf, yf);
            }
        }
        if(xi == 0 || xi == 1) {
            boolean F = rightFireCapture(xi, yi, xf, yf);
            boolean W = rightWaterCapture(xi, yi, xf, yf);
            if(F && W) {
                return true;
            }
            if(F && !W) {
                return F;
            }
            else {
                return W;
            }
        }
        if(xi == 7 || xi == 6) {
            boolean F = leftFireCapture(xi, yi, xf, yf);
            boolean W = leftWaterCapture(xi, yi, xf, yf);
            if(F&&W) {
                return true;
            }
            if(F && !W) {
                return F;
            }
            else {
                return W;
            }
        }
        else {
            boolean F = fireCapture(xi, yi, xf, yf);
            boolean W = waterCapture(xi, yi, xf, yf);
            if (F && W) {
                return true;
            }
            if(F && !W) {
                return F;
            }
            else {
                return W;
            }
        }
    }


    private boolean validMoveSimple(int xi, int yi, int xf, int yf) {
        Piece place = pieceAt(xi,yi);
        if (place != null) {
            if (movedPiece && !place.hasCaptured()) {
                return false;
            }
            if (place.isKing()) {
                return kingMovesSimple(xi, yi, xf, yf);
            }
            if(place.isFire() || place.isKing()) {
                if (edgeLeftFire(xi, yi)) {
                    return rightFireSimple(xi, yi, xf, yf);
                    }
                else if (edgeRightFire(xi, yi)) {
                    return leftFireSimple(xi, yi, xf, yf);
                    }
                else {
                    return fireSimple(xi, yi, xf, yf);
                }
            } else if (!place.isFire() || place.isKing()) {
                if (edgeLeftWater(xi, yi)) {
                    return rightWaterSimple(xi, yi, xf, yf);
                }
                else if (edgeRightWater(xi, yi)) {
                    return leftWaterSimple(xi, yi, xf, yf);
                }
                else {
                    return waterSimple(xi, yi, xf, yf);
                }
            }
        }
        return false;
    }

    private boolean validMoveCapture(int xi, int yi, int xf, int yf) {
        Piece place = pieceAt(xi, yi);
        if (place != null) {
            if (place.isKing()) {
                return kingMovesCapture(xi, yi, xf, yf);
            }
            if(place.isFire() || place.isKing()) {
                if (yi > 5) {
                    return false;
                }
                if (edgeLeftCapture(xi)) {
                    return rightFireCapture(xi, yi, xf, yf);
                } 
                else if (edgeRightCapture(xi)) {
                    return leftFireCapture(xi, yi, xf, yf);
                } else {
                    return fireCapture(xi, yi, xf, yf);
                }
            }
            else if (!place.isFire() || place.isKing()) {
                if (yi < 2) {
                    return false;
                }
                if (edgeLeftCapture(xi)) {
                    return rightWaterCapture(xi, yi, xf, yf);
                }
                else if (edgeRightCapture(xi)) {
                    return leftWaterCapture(xi, yi, xf, yf);
                } else {
                    return waterCapture(xi, yi, xf, yf);
                }
            }
        }
        return false;
    }
    private void countPieces() {
        int w = 0, f = 0;
        for(int i=0;i<8;i+=1) {
            for(int j=0;j<8;j+=1) {
                if((i+j)%2 == 0) {
                    if (stored[i][j] != null) {
                        if (stored[i][j].isFire()) {
                            f+=1;
                        }
                        else if (!stored[i][j].isFire()) {
                            w += 1;
                        }
                    }
                }
            }
        }
        waterPieces = w;
        firePieces = f;
    }

    public String winner() {
        countPieces();
        if (waterPieces == 0 && firePieces > 0)
            return "Fire";
        else if (firePieces == 0 && waterPieces > 0)
            return "Water";
        else if (firePieces == 0 && waterPieces == 0)
            return "No one";
        else 
            return null;
    }


/* MAIN ***************************************/
    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        b.pieces = new boolean[N][N];

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.winner();
            b.drawBoard(N);
            b.indicateTurn();
            if(!b.empty) {
                b.drawPieces(N);
            }
            //System.out.println(b.firePieces);
            //b.countPieces();
            if (StdDrawPlus.isSpacePressed()) {
                if(b.canEndTurn()) {
                    b.endTurn();
                }
            }

            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                for (int i = 0; i < N; i++) {
                  for (int j = 0; j < N; j++) {
                    b.pieces[i][j] = false;
                 }
                }     
                if (b.canSelect((int) x, (int) y)) {
                    b.pieces[(int) x][(int) y] = true;
                }
            } 

                    
            StdDrawPlus.show(10);
        }
    }
}
