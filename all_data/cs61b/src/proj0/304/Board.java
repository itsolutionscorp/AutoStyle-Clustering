public class Board{
    
    
    
    private Piece[][] pieces;
    private int size;
    private boolean firesTurn;
    private int xSelected;
    private int ySelected;
    private boolean hasMoved;
    
    
    
    public static void main(String[] args) {
        Board gameBoard = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        while(true) {
            gameBoard.drawBoard();
            if (StdDrawPlus.mousePressed()){
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if(gameBoard.canSelect(x,y)){
                    gameBoard.select(x,y);
                }
            }
            if(StdDrawPlus.isSpacePressed()){
                if(gameBoard.canEndTurn()){
                    gameBoard.endTurn();
                }
            }
            StdDrawPlus.show(10);
        }
    }
    
    
    
    public Board(boolean shouldBeEmpty){
            this.size = 8;
            this.pieces = new Piece[8][8];
            this.firesTurn = true;
            this.xSelected = -5;
            this.ySelected = -5;
            if(!shouldBeEmpty){
                for(int x=0; x<this.size; x=x+2){
                    this.pieces[x+1][7] = new Piece(false, this, x+1, 7, "pawn");
                    this.pieces[x][6] = new Piece(false, this, x, 6, "shield");
                    this.pieces[x+1][5] = new Piece(false, this, x+1, 5, "bomb");
                    this.pieces[x][2] = new Piece(true, this, x, 2, "bomb");
                    this.pieces[x+1][1] = new Piece(true, this, x+1, 1, "shield");
                    this.pieces[x][0] = new Piece(true, this, x, 0, "pawn");
                }
            }
    } 
    
    
    
    public Piece pieceAt(int x, int y){
        if (inBounds(x,y)){
            return pieces[x][y];
        }else{
            return null;
        } 
    }
    
    
    
    public boolean canSelect(int x, int y){
        if(!hasMoved && pieces[x][y] != null && pieces[x][y].isFire() == firesTurn) {
            return true;
        }else if(!inBounds(x,y)) {
            return false;
        }else if(pieces[xSelected][ySelected] == null){
            return false;
        }else if(pieces[x][y] == null && validMove(xSelected, ySelected, x, y)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Checks whether move is valid. Assumes that destination is empty. If so, returns true. If not, returns false.
     * First check: Is the move on the board and diagonal?
     * Second check: If a fire piece tries to move down: Is it crowned?
     * Third check: If a water piece tries to move up: Is it crowned?
     * Fourth check: Regular, non-capture move? Allow, if player hasn't moved yet.
     * Fifth check: Capture move? Checks whether square in between is occupied by animous figure.
     *              Allows if player has not moved yet or selected piece has captured this turn.
     * @param xi    current x coordinate of piece
     * @param yi    current y coordinate of piece
     * @param xf    goal x coordinate of piece
     * @param yf    goal y coordinate of piece
     * @return      whether attempted move is valid or not
     */
    
    private boolean validMove(int xi, int yi, int xf, int yf){
        int xif = Math.min(xi,xf)+1;
        int yif = Math.min(yi,yf)+1;
        if(!inBounds(xf, yf) || Math.abs(xi-xf) != Math.abs(yi-yf)){
            return false;
        }else if(firesTurn && yi > yf && !pieces[xi][yi].isKing()){
            return false;
        }else if(!firesTurn && yi < yf && !pieces[xi][yi].isKing()){
            return false;
        }else if(Math.abs(xi-xf) == 1 && !hasMoved){
            return true;
        }else if(Math.abs(xi-xf) == 2 && pieces[xif][yif] != null && pieces[xif][yif].isFire() != firesTurn){
            if(!hasMoved || pieces[xSelected][ySelected].hasCaptured()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    
    
    
    public void select(int x, int y){
            if(pieces[x][y] != null){
                xSelected = x;
                ySelected = y;
            }else{
                pieces[xSelected][ySelected].move(x,y);
                hasMoved = true;
                xSelected = x;
                ySelected = y;
            }
    }
    
    
    
    public void place(Piece p, int x, int y){
        if (inBounds(x,y)){
            pieces[x][y] = p;
        }else{
            System.out.println("Error: Index out of bounds.");
        }
    }
    
    
    
    public Piece remove(int x, int y){
        if (inBounds(x,y)){
            if(pieces[x][y] == null){
                System.out.println("ERROR: No piece at ("+x+","+y+")");
            }
            Piece p = pieces[x][y];
            pieces[x][y] = null;
            return p;
        }else{
            System.out.println("ERROR: Index out of bounds.");
            return null;
        }
    }
    
    
    
    public boolean canEndTurn(){
        if (hasMoved){
            return true;
        }else{
            return false;
        }
    }
    
    
    
    public void endTurn(){
        if(pieces[xSelected][ySelected] != null){
            pieces[xSelected][ySelected].doneCapturing();
        }
        xSelected = -5;
        ySelected = -5;
        hasMoved = false;
        firesTurn = !firesTurn;
    }
    
    
    
    public String winner(){
        int firePieces = 0;
        int waterPieces = 0;
        int x = 0;
        while(x < size) {
            int y = 0;
            while (y < size) {
                if (pieces[x][y] != null) {
                    if (pieces[x][y].isFire()) {
                        firePieces += 1;
                    }else{
                        waterPieces += 1;
                    }
                }
                y += 1;
            }
            x += 1;
        }
        if(firePieces == 0 && waterPieces == 0){
            return "No one";
        }else if(firePieces == 0){
            return "Water";
        }else if(waterPieces == 0){
            return "Fire";
        }else{
            //if(waterPieces == 1 && firePieces == 1){
              //  return "No one";
            //}else{
                return null;
            //}

        }
    }

    
    
    private void drawBoard() {
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                if (pieces[x][y] != null) {
                    String picture = determinePicture(pieces[x][y]);
                    StdDrawPlus.picture(x + .5, y + .5, picture, 1, 1);
                }
            }
        }
    }
    
    
    private String determinePicture(Piece P){
        if(P.isFire() == true){
            if(P.isBomb() == true){
                if(P.isKing() == true){
                    return "img/bomb-fire-crowned.png";
                }else{
                    return "img/bomb-fire.png";
                }
            }else if(P.isShield() == true){
                if(P.isKing() == true){
                    return "img/shield-fire-crowned.png";
                }else{
                    return "img/shield-fire.png";
                }
            }else{
                if(P.isKing() == true){
                    return "img/pawn-fire-crowned.png";
                }else{
                    return "img/pawn-fire.png";
                }
            }
        }else{
            if(P.isBomb() == true){
                if(P.isKing() == true){
                    return "img/bomb-water-crowned.png";
                }else{
                    return "img/bomb-water.png";
                }
            }else if(P.isShield() == true){
                if(P.isKing() == true){
                    return "img/shield-water-crowned.png";
                }else{
                    return "img/shield-water.png";
                }
            }else{
                if(P.isKing() == true){
                    return "img/pawn-water-crowned.png";
                }else{
                    return "img/pawn-water.png";
                }
            }
        }
    }


    private boolean inBounds(int x, int y){
        if (0 <= x && x <= this.size-1 && 0 <= y && y <= this.size-1){
            return true;
        }else{
            return false;
        }
    }
}