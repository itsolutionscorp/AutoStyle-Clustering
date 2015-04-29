public class Board {
    private Piece[][] pieces;
    private boolean pieceMoved,fireTurn;
    private Piece pieceChosen;
    private int[] pieceChosenPosition;
    
    public static void main(String args[]) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);

        b.drawBoard();
        while(true) {
            if (StdDrawPlus.mousePressed()){
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if(b.canSelect(x,y)){
                    b.select(x,y);
                }
            }    
            if(StdDrawPlus.isSpacePressed()){
                if(b.canEndTurn()){
                    b.endTurn();
                } 
            }
            b.drawBoard();
            StdDrawPlus.show(10);
        }
    }

    public Board(boolean shouldBeEmpty) {
        pieceMoved = false;
        pieceChosen = null; 
        pieceChosenPosition = new int[2];
        fireTurn=true;
        pieces = new Piece[8][8];
        if(!shouldBeEmpty) {
            for (int i =0; i< 8; i++) {
                for (int j =0; j< 8; j++) { 
                    if((i+j) % 2 == 0) {
                        switch(j){
                            case 0:
                                pieces[i][j] = new Piece(true,this,i,j,"pawn");
                                break;
                            case 1:
                                pieces[i][j] = new Piece(true,this,i,j,"shield");
                                break;
                            case 2:
                                pieces[i][j] = new Piece(true,this,i,j,"bomb");
                                break;
                            case 5:
                                pieces[i][j] = new Piece(false,this,i,j,"bomb");
                                break;
                            case 6:
                                pieces[i][j] = new Piece(false,this,i,j,"shield");
                                break;
                            case 7:
                                pieces[i][j] = new Piece(false,this,i,j,"pawn");
                                break;
                            default:
                                pieces[i][j] = null;
                                break;
                        }
                    }
                    else {
                        pieces[i][j] = null;
                    }
                }
            }
        }
    }
    
    private boolean inBounds(int x, int y){
        if (x < 0 || x > 7 || y < 0 || y > 7){
            return false;
        }
        return true;
    }
    public Piece pieceAt(int x, int y) {
        if(inBounds(x,y)) { 
            return pieces[x][y];
        }
        else {
            return null;
        }
    }

    public boolean canSelect(int x, int y) {
        if(!inBounds(x,y)){
            return false;
        }
        if(pieceAt(x,y) != null) {
            Piece p = pieceAt(x,y);
            if (p.isFire()!=fireTurn) {
                return false;
            }
            if (pieceChosen == null) {
                return true;
            }
            else if(!pieceMoved){
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if(pieceChosen != null &&  pieceMoved == false && validMove(pieceChosenPosition[0],
                                        pieceChosenPosition[1],x,y)) {
                return true;
            }
            else if(pieceChosen != null && pieceChosen.hasCaptured() && 
                    Math.abs(pieceChosenPosition[0]-x) == 2 &&
                    validMove(pieceChosenPosition[0], pieceChosenPosition[1], x, y)){
                return true;
            }
            else {
                return false;
            }
        }
    }
    
    private boolean validMove(int xi, int yi, int xf, int yf) {
        Piece p = pieceAt(xi,yi);
        if(p == null){
            return false;
        }
        if (pieceAt(xf,yf)!=null){
            return false;
        }
        if (p.isKing()){
            if(Math.abs(xi-xf) == 1 && Math.abs(yi-yf) == 1) {
                return true;
            }
            else if(Math.abs(xi-xf) == 2 && Math.abs(yi-yf) == 2 
                    && pieceAt((xi+xf)/2,(yi+yf)/2) != null
                    && pieceAt((xi+xf)/2,(yi+yf)/2).isFire() !=p.isFire()) {
                return true;
            }
            else {
                return false;
            }
        }
        if (p.isFire()) {
            if(Math.abs(xf-xi) == 1 && (yf-yi) == 1) {
                return true;
            }
            else if(Math.abs(xi-xf) == 2 && (yf-yi) == 2 
                    && pieceAt((xi+xf)/2,(yi+yf)/2) != null
                    && pieceAt((xi+xf)/2,(yi+yf)/2).isFire() != p.isFire()) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            if(Math.abs(xf-xi) == 1 && (yi-yf) == 1) {
                return true;
            }
            else if(Math.abs(xi-xf) == 2 && (yi-yf) == 2 
                    && pieceAt((xi+xf)/2,(yi+yf)/2) != null
                    && pieceAt((xi+xf)/2,(yi+yf)/2).isFire() != p.isFire()) {
                return true;
            }
            else {
                return false;
            } 
        }
    }

    public void select(int x, int y) {
        if (pieceAt(x,y) != null){
            pieceChosen = pieceAt(x,y);
        }
        else{
            pieceChosen.move(x, y);
            pieceMoved = true;
        }
        pieceChosenPosition[0]=x;
        pieceChosenPosition[1]=y;
    }

    public void place(Piece p, int x, int y) {
        if (inBounds(x,y) && p != null) {
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y) {
        if(inBounds(x,y)){
            Piece p = pieces[x][y];
            pieces[x][y] = null;
            return p;
        }
        else {
            return null;
        }
    }

    public boolean canEndTurn() {
        return pieceMoved;
    }

    public void endTurn() {
       fireTurn = !fireTurn;
       pieceMoved = false;
       pieceChosen.doneCapturing();
       pieceChosen = null; 
    }

    public String winner () {
        boolean existsFire = false;
        boolean existsWater = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) { 
                if (pieces[i][j] != null){
                    if(pieces[i][j].isFire()){
                        existsFire= true;
                    }
                    else {
                        existsWater = true;
                    }
                }
                if (existsFire && existsWater){
                    break;
                }
            }
        }
        if(existsFire && existsWater){
            return null;
        }
        else if (existsFire) {
            return "Fire";
        }
        else if (existsWater) {
            return "Water";
        }
        else {
            return "No one";
        }
    }

    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieceChosen!=null && pieceChosenPosition[0]==i 
                        && pieceChosenPosition[1]==j) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieces[i][j]!=null) {
                    Piece p = pieces[i][j];
                    String name = "img/";
                    if(p.isBomb()) {
                        name = name.concat("bomb");
                    }
                    else if(p.isShield()) {
                        name = name.concat("shield");
                    }
                    else { 
                        name = name.concat("pawn");
                    }
                    if(p.isFire()) {
                        name = name.concat("-fire");
                    }
                    else {
                        name = name.concat("-water");
                    }
                    if(p.isKing()) {
                        name = name.concat("-crowned.png");
                    }
                    else {
                        name = name.concat(".png"); 
                    }
                    StdDrawPlus.picture(i + .5, j + .5, name, 1, 1);
                }
            }
        }
    }

}
