public class Board {
    private Piece[][] pieces = new Piece[8][];
    private Piece prev, curr, cancurr, capturing;
    private boolean hasmoved;
    private int currplayer, currx, curry, numfire, numwater;

    public Board(boolean shouldbeEmpty) {
        if (!shouldbeEmpty) {
            this.numfire = 12;
            this.numwater = 12;
            for (int i = 0; i < pieces.length; i++) {
                if (i%2==0) {
                    pieces[i] = new Piece[] {
                        new Piece(true, this, i, 0, "pawn"),
                        null,
                        new Piece(true, this, i, 2, "bomb"),
                        null, null, null,
                        new Piece(false, this, i, 6, "shield"),
                        null
                    };
                } else {
                    pieces[i] = new Piece[] {
                        null,
                        new Piece(true, this, i, 1, "shield"),
                        null, null, null,
                        new Piece(false, this, i, 5, "bomb"),
                        null,
                        new Piece(false, this, i, 7, "pawn")
                    };
                }
            } 
      } else {
        this.numfire = 0;
        this.numwater = 0;
        this.pieces = new Piece[8][8];
      }
    }

    public Piece pieceAt(int x, int y) {
        try {
            return this.pieces[x][y];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public boolean canSelect(int x, int y) {
        cancurr = pieceAt(x,y); //check if I'm clicking a Piece

        if (cancurr != null) { //selecting a Piece
            if (cancurr.side() != this.currplayer) { //correct turn
                return false;
            }
            if (hasmoved) { // && !(curr != null && curr.hasCaptured() )
                return false;
            }
            return true;
        } else { //selecting an empty square
            if ((curr != null) && (validMove(curr, currx, curry, x, y) || 
                canCapture(curr, currx, curry, x, y))) {
                if (!hasmoved || (curr != null && curr.hasCaptured() )) {
                    return true;
                }
            }
            return false;
        }
    }

    private boolean canCapture(Piece from, int xi, int yi, int xf, int yf) {
        int dir;
        if (from.isFire()) {
            dir = 2;
        } else {
            dir = -2;
        }

        if (((xf - xi == dir) || (xf - xi == -dir)) && 
            ((yf - yi == dir) || (from.isKing() && (yf - yi == -dir)))) {
            capturing = this.pieceAt((xi+xf)/2, (yi+yf)/2);
            if ((capturing != null) && (capturing.side() != from.side())) {
                return true;
            }
        }
        return false;        
    }

    private boolean validMove(Piece from, int xi, int yi, int xf, int yf) {
        //only for one step, no capture
        int dir;
        if (from.isFire()) {
            dir = 1;
        } else {
            dir = -1;
        }

        if (hasmoved) {
            return false;
        }

        if (((xf - xi == 1) || (xf - xi == -1)) 
            && ((yf - yi == dir) || (from.isKing() && (yf - yi == -dir)))) {
            return true;
        }
        return false;
    }

    public void select(int x, int y) {
        prev = curr; //maybe modify with an if curr == null
        curr = this.pieceAt(x,y);
        if (curr == null) {
            prev.move(x,y);
            this.hasmoved = true;
            curr = this.pieceAt(x, y); //for multicapture
            currx = x;
            curry = y;
            prev = null;
        } else { //selected a Piece
            currx = x;
            curry = y;
            // don't need to do anything to prev, only shows up in this func
        }
    }

    public void place(Piece p, int x, int y) {
        if ((p != null) && (x < 8) && (y < 8) && (x >= 0) && (y >= 0)) {
            this.pieces[x][y] = p;

            if (p.isFire()) {
                this.numfire += 1;
            } else {
                this.numwater += 1;
            }
        }
    }

    public Piece remove(int x, int y) {
        try{
        Piece pop = this.pieceAt(x, y);
        if (pop != null) {
            this.pieces[x][y] = null;

            if (pop.isFire()) {
                this.numfire -= 1;
            } else {
                this.numwater -= 1;
            }
            return pop;
        } else {
            System.out.println("There's no piece there!");
            return null;
        }
        } catch(IndexOutOfBoundsException e) {
                System.out.println("That position is outside the board!");
                return null;
        }
    }

    public boolean canEndTurn() {
        if (hasmoved || ((curr != null) && curr.hasCaptured())) {
            return true;
        }
        return false;
    }

    public void endTurn() {
        this.currplayer = 1 - this.currplayer;
        this.hasmoved = false;
        if (curr != null) {
        this.curr.doneCapturing(); // reset hasCaptured()
        }
        this.prev = null;
        this.curr = null;
    }

    public String winner() {
        if ((this.numwater == 0) && (this.numfire == 0)) {
            return "No one";
        } else if (this.numwater == 0) {
            return "Fire";
        } else if (this.numfire == 0) {
            return "Water";
        } else {
            return null;
        }
    }

    private static void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
    }

    private static void drawPieces(int N, Board b) {
        Piece currtd;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (b.pieces[i][j] != null) {
                    currtd = b.pieces[i][j];
                    if (currtd.isFire()) {
                        if (currtd.isBomb()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        } else if (currtd.isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                } else{
                    if (currtd.isBomb()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                    } else if (currtd.isShield()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                    } else {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
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
            drawBoard(N);
            drawPieces(N, b);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    b.select((int) x, (int) y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }
            StdDrawPlus.show(100);
            if (b.winner() != null) {
                System.out.println(b.winner());
            }
        }
    }
}