public class Board {    
    private static final int N = 8; 
    private static final String[] TYPES = {"pawn", "shield", "bomb"};

    private Piece[][] pieces;
    private int numFire;
    private int numWater;

    private Piece selectPiece;
    private int selectX;
    private int selectY;
    private boolean isFireTurn;
    private boolean hasMoved;
    
    public Board(boolean shouldBeEmpty) {
        pieces = new Piece[N][N];
        numFire = 0;
        numWater = 0;
        
        selectPiece = null;
        selectX = -1;
        selectY = -1;
        isFireTurn = true;
        hasMoved = false;

        if (!shouldBeEmpty) {
            this.initPieces();
        }
    }

    private void initPieces() {
        int xPos = 0;
        int yPos = 0;
        
        for (String type : TYPES) {
            for (int i = 0; i < 4; i++) {
                //Fill in a fire piece at (xPos, yPos), and a corresponding
                //water piece at the reciprocal location
                this.place(new Piece(true, this, xPos, yPos, type), xPos, yPos);
                this.place(new Piece(false, this, N-xPos-1, N-yPos-1, type), N-xPos-1, N-yPos-1);
                xPos += 2;
            }
            yPos += 1; //Increment a row
            xPos = (xPos + 1) % 2; //To form the echelon offset
        }
    }

    private void drawBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == selectX && j == selectY) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                } else if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null) {
                    StdDrawPlus.picture(i + .5, j + .5, getPieceURL(pieces[i][j]), 1, 1);
                }
            }
        }
    }

    //No public methods = no toString() :sadface:
    //Gets the URL of the image for the type of Piece p
    private String getPieceURL(Piece p) {
        String ret = "img/";
        if (p.isBomb()) {
            ret += "bomb";
        } else if (p.isShield()) {
            ret += "shield";
        } else {
            ret += "pawn";
        }

        ret += p.isFire() ? "-fire" : "-water";
        ret += p.isKing() ? "-crowned" : "";

        return ret + ".png";
    }

    private boolean isInBounds(int x, int y) {
        return !(x >= N || x < 0 || y >= N || y < 0);
    }

    public Piece pieceAt(int x, int y) {
        if(!isInBounds(x, y)) {
            return null;
        }
        return pieces[x][y];
    }

    public boolean canSelect(int x, int y) {
        Piece p = this.pieceAt(x, y);
        if (selectPiece == null) { //If no piece selected yet
            return p != null && isFireTurn == p.isFire();
        }
        if (!hasMoved && p != null) { //If a piece was selected, but it hasn't moved and selected square contains a piece
            return isFireTurn == p.isFire();
        }
        if (!hasMoved && this.validMove(selectX, selectY, x, y)) { //If a piece was selected, but it hasn't moved and the square is empty
            return true;
        }
        return selectPiece.hasCaptured() && this.validMove(selectX, selectY, x, y) && Math.abs(x-selectX) == 2; //If a piece was selected, but it has captured and can capture again
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece pi = this.pieceAt(xi, yi);
        Piece pf = this.pieceAt(xf, yf);

        if (pi == null || pf != null || !isInBounds(xf, yf)) {
            return false;
        }

        int dx = xf-xi;
        int dy = yf-yi;

        if (Math.abs(dx) != Math.abs(dy)) { //Not diagonal
            return false;
        }
        if (!pi.isKing() && (pi.isFire() == dy < 0)) { //Wrong direction
            return false;
        }
        if (Math.abs(dx) > 2 || Math.abs(dx) < 1) { //Too far
            return false;
        }
        
        Piece eat = this.pieceAt(xi + dx/2, yi + dy/2);
        //Trying to eat a non-existant or ally piece
        if (Math.abs(dx) == 2 && (eat == null || eat.isFire() == pi.isFire())) {
            return false;
        }
        
        return true;
    }

    public void select(int x, int y) {
        if (this.pieceAt(x, y) == null) {
            this.hasMoved = true;
            selectPiece.move(x, y);
        } else {
            selectPiece = this.pieceAt(x, y);
        }
        selectX = x;
        selectY = y;
    }

    public void place(Piece p, int x, int y) { 
        if(!isInBounds(x, y) || p == null) {
            return;
        }
        
        // Can't be having duplicate pieces now can we?
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (this.pieceAt(i, j) == p) {
                    this.remove(i, j);
                }
            }
        }

        this.pieces[x][y] = p;
        if (p.isFire()) {
            numFire += 1;
        }
        else {
            numWater += 1;
        }
    }

    public Piece remove(int x, int y) {
        Piece p = this.pieceAt(x, y);
        if(p == null) {
            System.err.println("ERR: Invalid remove attempt");
        } else {
            this.pieces[x][y] = null;
            if (p.isFire()) {
                numFire -= 1;
            } else {
                numWater -= 1;
            }
        }
        return p;
    }

    public boolean canEndTurn() {
        return hasMoved;
    }

    public void endTurn() {
        selectPiece.doneCapturing();
        selectPiece = null;
        selectX = -1;
        selectY = -1;
        hasMoved = false;
        isFireTurn = !isFireTurn;
    }

    public String winner() {
        if (numFire == 0 && numWater == 0) {
            return "No one";
        }
        if (numFire == 0) {
            return "Water";
        }
        if (numWater == 0) {
            return "Fire";
        }
        return null;
    }

    public static void main(String[] args) {
        Board board;
        if (args.length > 0) {
            board = new Board(Boolean.parseBoolean(args[0]));
        } else {
            board = new Board(false);
        }
 
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        while(true) {
            if (board.winner() != null) {
                System.out.println(board.winner() + " wins!");
                return;
            }
            board.drawBoard();
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if(board.canSelect(x, y)) {
                    board.select(x, y);
                }
            }
            else if (StdDrawPlus.isSpacePressed() && board.canEndTurn()) {
                board.endTurn();
            }
            StdDrawPlus.show(10);
        }
    }
}
