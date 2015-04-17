public class Board{
    private Piece[][] pieceArray = new Piece[8][8];
    private boolean empty;
    private int selectedx, selectedy;
    private boolean selected = false;
    private int endx = -1;
    private int endy = -1;
    private boolean move = false;
    private boolean turn = true;
    private int fireCount;
    private int waterCount;
    public Board(boolean shouldBeEmpty){
        if (shouldBeEmpty == false){
            empty = false;
            for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j == 0 && i % 2 == 0){
                    pieceArray[i][j] = new Piece(true, this, i, j, "pawn");
                }
                if (j == 1 && i % 2 == 1){
                    pieceArray[i][j] = new Piece(true, this, i, j, "shield");
                }
                if (j == 2 && i % 2 == 0){
                    pieceArray[i][j] = new Piece(true, this, i, j, "bomb");
                }
                if (j == 5 && i % 2 == 1){
                    pieceArray[i][j] = new Piece(false, this, i, j, "bomb");
                }
                if (j == 6 && i % 2 == 0){
                    pieceArray[i][j] = new Piece(false, this, i, j, "shield");
                }
                if (j == 7 && i % 2 == 1){
                    pieceArray[i][j] = new Piece(false, this, i, j, "pawn");
                }

        }}}
        else{
            empty = true;
        }
    }
    private void drawBoard(int N){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                Piece piece = pieceArray[i][j];
                if(piece != null){
                    if (piece.isFire()) {
                        if (piece.isKing() && piece.isBomb()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                        } else if(piece.isKing() && piece.isShield()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                        } else if(piece.isKing() && !piece.isBomb() && !piece.isShield()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                        } else if (piece.isBomb()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        } else if (piece.isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                    } else {
                        if (piece.isKing() && piece.isBomb()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                        } else if(piece.isKing() && piece.isShield()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                        } else if(piece.isKing() && !piece.isBomb() && !piece.isShield()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                        } else if (piece.isBomb()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        } else if (piece.isShield()) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        } else {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }
                    }
                        
                
                    }
                    }
                
                }
            }
        
    

    public static void main(String[] args){
        Board b = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        while(b.winner() == null) {
            b.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int)(StdDrawPlus.mouseX());
                int y = (int)(StdDrawPlus.mouseY());
                if (b.canSelect(x, y)) {
                    b.select(x, y);
                    }
                }
            if(StdDrawPlus.isSpacePressed()){
                if(b.canEndTurn()){
                b.endTurn();
            }
                
            }            
            StdDrawPlus.show(100);
        }
    }
    
    public Piece pieceAt(int x, int y){
        if (x > 7 || y > 7 || x < 0 || y < 0){
            return null;
        }
        if (pieceArray[x][y] == null){
            return null;
        }
        if (pieceArray[x][y] != null){
            return pieceArray[x][y];
        }
        return null;
        
    }

    public void place(Piece p, int x, int y){
        if (p != null && (x <= 7 && y <= 7 && x >= 0 && y >= 0)) {
            pieceArray[x][y] = p;
            p.isKing();
        }
    }


    public Piece remove(int x, int y){
        if ((x > 7 || y > 7 || x < 0 || y < 0)){
            System.out.println("out of bound");
            return null;
        }

        if ((pieceAt(x, y) == null)){
            System.out.println("no piece");
            return null;
        }
        else{
            Piece temp = pieceAt(x, y);
            pieceArray[x][y] = null;
            return temp;

        }
    }

    public boolean canSelect(int x, int y){
        Piece tempPiece = pieceAt(x, y);
        if (tempPiece != null) {
            if ((turn == tempPiece.isFire()) && (!selected ||!move) && endx == -1) {
                return true;
            }
            return false;
        } else {
            if (selected && validMove(selectedx, selectedy, x, y)) {
                return true;
            }
            else if(pieceAt(endx, endy) != null){
                Piece selectedPiece = pieceAt(endx, endy);
                Piece middlePiece = pieceAt((x+endx)/2, (y+endy)/2);
                if(middlePiece == null){
                    return false;
                }
                else if (!selected && move && pieceArray[endx][endy].hasCaptured() 
                    && (pieceArray[(x+endx)/2][(y+endy)/2].isFire()!= turn) &&
                ((Math.abs(endx-x) == 2 && Math.abs(endy-y) == 2 && selectedPiece.isKing()) ||
                 (Math.abs(endx-x) == 2 && (y-endy) == 2 && selectedPiece.isFire()) || 
                 (Math.abs(endx-x) == 2 && (endy-y) == 2 && !selectedPiece.isFire()))) {
                    return true;
                }
                else{
                return false;}}
            return false;
        }
    }

    public void select(int x, int y){
        if (pieceAt(x, y) != null){
            selected = true;
            selectedx = x;
            selectedy = y;
            move = false;
        }
        else{
            if (!move) {
                Piece selectedPiece = pieceAt(selectedx, selectedy);
                selectedPiece.move(x, y);
            } else {
                Piece selectedPiece = pieceAt(endx, endy);
                selectedPiece.move(x, y);
            }
            this.endx = x;
            this.endy = y;
            this.move = true;
            this.selected = false;
        }

    }

    private boolean validMove(int xi, int yi, int xf, int yf){
        Piece position1 = pieceAt(xi, yi);
        Piece position3 = pieceAt(xf, yf);
        if(xf < 0 || yf < 0 || xi < 0 || yi < 0 || xi > 7|| yi > 7 || xf > 7 || yf >7 ){
            return false;
        }
        else{
            if (position1.isKing()){
                    if(Math.abs(xi-xf)==1 && Math.abs(yi-yf)==1 && position3 == null){
                        return true;}
                    else if(Math.abs(xi-xf)==2 && Math.abs(yi-yf)==2 && position3 == null){
                        Piece position2 = pieceAt((xi+xf)/2, (yf+yi)/2);
                        if(position2 == null){
                            return false;
                        }
                        else{
                            boolean isFirep2 = position2.isFire();
                            boolean isFirep1 = position1.isFire();
                            if(isFirep1 != isFirep2 ){
                                return true;}
                            else{
                                return false;
                            }
                            }
                        } else{
                            return false;
                        }}
            
            else{
                if(position1.isFire()){
                    if(Math.abs(xi-xf)==1 && (yf-yi)==1 && position3 == null){
                        return true;}
                    else if(Math.abs(xi-xf)==2 && (yf-yi) ==2 && position3 == null){
                        Piece position2 = pieceAt((xi+xf)/2, (yf+yi)/2);
                        if(position2 == null){
                            return false;
                        }
                        else{
                            boolean isFirep2 = position2.isFire();
                            boolean isFirep1 = position1.isFire();
                            if(isFirep1 != isFirep2 ){
                                return true;}
                            else{
                                return false;
                            }
                            }
                        } else{
                            return false;
                        }}
                else if(!position1.isFire()){
                    if(Math.abs(xi-xf)==1 && (yi-yf)==1 && position3 == null){
                        return true;}
                    else if(Math.abs(xi-xf)==2 && (yi-yf) ==2 && position3 == null){
                        Piece position2 = pieceAt((xi+xf)/2, (yf+yi)/2);
                        if(position2 == null){
                            return false;
                        }
                        else{
                            boolean isFirep2 = position2.isFire();
                            boolean isFirep1 = position1.isFire();
                            if(isFirep1 != isFirep2 ){
                                return true;}
                            else{
                                return false;
                            }
                            }
                        } else{
                            return false;
                        }}
                return false;
                }
                }

                }
            
    

    public boolean canEndTurn(){
        return this.move;
    }

    public void endTurn(){
        if (endx != -1 && endy != -1){
            if (this.pieceAt(endx, endy) != null) {
                this.pieceAt(endx, endy).doneCapturing();
            }
            endx = -1;
            endy = -1;
        }
        this.move = false;
        this.turn = !this.turn;
    }

    public String winner(){
        fireCount = 0;
        waterCount = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieceAt(i, j) != null){
                    if (pieceAt(i, j).isFire()){
                        fireCount = fireCount + 1;
                }
                    else{
                        waterCount = waterCount + 1;
                    }
            }
        }
    }
        if (fireCount == 0 && waterCount != 0){
            return "Water";
        }
        else if(fireCount == 0 && waterCount == 0){
            return "No one";
        }
        else if(fireCount != 0 && waterCount == 0){
            return "Fire";
        }
        else{
            return null;
        }
    }
}


