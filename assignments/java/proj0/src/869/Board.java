public class Board{
    private Piece[][] pieces;
    private boolean isFireTurn;
    private int selPieceX;
    private int selPieceY;
    private boolean hasSelectedPiece;
    private boolean hasMoved;

    public static void main(String args[]){
        Board b = new Board(false);
        StdDrawPlus.show(25);
        while(b.winner()==null){
            if(StdDrawPlus.mousePressed()){
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if(b.canSelect(x,y))
                    b.select(x,y);
            }
            if(StdDrawPlus.isSpacePressed()){
                if(b.canEndTurn())
                    b.endTurn();
            }
            b.drawPieces();
        }
    }

    public Board(boolean shouldBeEmpty){
        pieces = new Piece[8][8];
        isFireTurn = true;
        hasMoved = false;
        if(!shouldBeEmpty){
            /* draws board */
            StdDrawPlus.setXscale(0,8);
            StdDrawPlus.setYscale(0,8);
            drawBoard();

            /* instantiates pieces */
            pieces[0][0] = new Piece(true, this, 0, 0, "");
            pieces[2][0] = new Piece(true, this, 2, 0, "");
            pieces[4][0] = new Piece(true, this, 4, 0, "");
            pieces[6][0] = new Piece(true, this, 6, 0, "");
            pieces[1][1] = new Piece(true, this, 1, 1, "shield");
            pieces[3][1] = new Piece(true, this, 3, 1, "shield");
            pieces[5][1] = new Piece(true, this, 5, 1, "shield");
            pieces[7][1] = new Piece(true, this, 7, 1, "shield");
            pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
            pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
            pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
            pieces[6][2] = new Piece(true, this, 6, 2, "bomb");

            pieces[1][7] = new Piece(false, this, 1, 7, "");
            pieces[3][7] = new Piece(false, this, 3, 7, "");
            pieces[5][7] = new Piece(false, this, 5, 7, "");
            pieces[7][7] = new Piece(false, this, 7, 7, "");
            pieces[0][6] = new Piece(false, this, 0, 6, "shield");
            pieces[2][6] = new Piece(false, this, 2, 6, "shield");
            pieces[4][6] = new Piece(false, this, 4, 6, "shield");
            pieces[6][6] = new Piece(false, this, 6, 6, "shield");
            pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
            pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
            pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
            pieces[7][5] = new Piece(false, this, 7, 5, "bomb");

            /* draws pieces */
            drawPieces();
        }
    }

    /****** SHOULD CHECK LATER *****/
    public Piece pieceAt(int x, int y){
        if(x>7||x<0||y<0||y>7)
            return null;
        return pieces[x][y];
    }
    /* BOMBB EXPLODING TEST */
    /* Tested by JUnit tests */
    public boolean canSelect(int x, int y){
        if(x>7||x<0||y<0||y>7)
            return false;

        Piece p = pieces[x][y];
        Piece selPiece = pieces[selPieceX][selPieceY];

        if(p!=null){
            if(p.isFire()==isFireTurn){
                if(!hasMoved&&!hasSelectedPiece){
                    return true;
                }else{
                    if(!hasMoved)
                        return true;
                }
            }
        }else{
            if(hasSelectedPiece)
                return validMove(selPieceX,selPieceY,x,y,selPiece.hasCaptured(),hasMoved);
        }

        return false;
    }

    /** Need to test with JUnit tests */
    public void select(int x, int y){
        if(pieces[x][y]==null){
            /** move or capture something **/
            Piece selPiece = pieces[selPieceX][selPieceY];
            selPiece.move(x,y);
            selPieceX = x;
            selPieceY = y;
            hasMoved = true;
            if(selPiece.isBomb()&&selPiece.hasCaptured())
                hasSelectedPiece = false;
        }else{
            /* Select Piece */
            selPieceX = x;
            selPieceY = y;
            hasSelectedPiece = true;
        }
        drawBoard();
        if(hasSelectedPiece){
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(selPieceX+.5,selPieceY+.5,.5);
        }
        drawPieces();
        StdDrawPlus.show(25);
    }

    /****** SHOULD CHECK LATER *****/
    public void place(Piece p, int x, int y){
        if(x>7||x<0||y<0||y>7)
            return;
        pieces[x][y]=p;
    }

    /** NOT TESTED */
    public Piece remove(int x, int y){
        Piece p = pieceAt(x,y);
        pieces[x][y]=null;
        return p;
    }

    /** NOT TESTED */
    public boolean canEndTurn(){
        return hasMoved; 
    }

    /** NOT TESTED */
    public void endTurn(){
        if(canEndTurn()){
            isFireTurn =! isFireTurn;
            hasSelectedPiece = false;
            hasMoved = false;
            for(int i=0;i<8;i++){
                for(int j=0;j<8;j++){
                    if(pieceAt(i,j)!=null)
                        pieceAt(i,j).doneCapturing();
                }
            }
            drawBoard();
            drawPieces();
            StdDrawPlus.show(25);
        }
    }

    /** NOT TESTED */
    public String winner(){
        int firePieces = 0;
        int waterPieces = 0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                Piece p = pieces[i][j];
                if(p!=null){
                    if(p.isFire())
                        firePieces++;
                    else
                        waterPieces++;
                }
            }
        }
        if(firePieces>0&&waterPieces>0)
            return null;
        else if(firePieces==0&&waterPieces==0)
            return "No one";
        else if(waterPieces==0)
            return "Fire";
        else
            return "Water";
    }

    private void drawPieces(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                Piece p = pieces[i][j];
                if(p!=null){
                    if(p.isFire()){
                        if(p.isBomb()){
                            if(p.isKing())
                                StdDrawPlus.picture(i+.5,j+.5,"img/bomb-fire-crowned.png",1,1);
                            else
                                StdDrawPlus.picture(i+.5,j+.5,"img/bomb-fire.png",1,1);
                        }else if(p.isShield()){
                            if(p.isKing())
                                StdDrawPlus.picture(i+.5,j+.5,"img/shield-fire-crowned.png",1,1);
                            else
                                StdDrawPlus.picture(i+.5,j+.5,"img/shield-fire.png",1,1);
                        }else{
                            if(p.isKing())
                                StdDrawPlus.picture(i+.5,j+.5,"img/pawn-fire-crowned.png",1,1);
                            else
                                StdDrawPlus.picture(i+.5,j+.5,"img/pawn-fire.png",1,1);
                        }
                    }else{
                        if(p.isBomb()){
                            if(p.isKing())
                                StdDrawPlus.picture(i+.5,j+.5,"img/bomb-water-crowned.png",1,1);
                            else
                                StdDrawPlus.picture(i+.5,j+.5,"img/bomb-water.png",1,1);
                        }else if(p.isShield()){
                            if(p.isKing())
                                StdDrawPlus.picture(i+.5,j+.5,"img/shield-water-crowned.png",1,1);
                            else
                                StdDrawPlus.picture(i+.5,j+.5,"img/shield-water.png",1,1);
                        }else{
                            if(p.isKing())
                                StdDrawPlus.picture(i+.5,j+.5,"img/pawn-water-crowned.png",1,1);
                            else
                                StdDrawPlus.picture(i+.5,j+.5,"img/pawn-water.png",1,1);
                        }
                    }
                }
            }
        }
   }

    private void drawBoard(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if((i+j)%2==0)
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i+.5,j+.5,.5);
            }
        }
    }

    /* Does not take into account turn, tested by JUnit tests */
    private boolean validMove(int xi, int yi, int xf, int yf, boolean hasCaptured, boolean moved){
        Piece p = pieceAt(xi,yi);

        if(p!=null&&pieceAt(xf,yf)==null){
            /**check possible moves... kinged? fire or water direction?**/
            int dy=yf-yi;
            int dx=xf-xi;
            if(!hasMoved&&dx*dx*dy*dy==1){
                if(p.isKing())
                    return true;
                else if(p.isFire()&&dy>0)
                    return true;
                else if(!p.isFire()&&dy<0)
                    return true;
            }
            if(!hasMoved||hasCaptured&&(dx==2||dx==-2||dy==2||dy==-2)){
                Piece r = pieces[xi+dx/2][yi+dy/2];
                if(r!=null&&(r.isFire()!=p.isFire())){
                    if(p.isKing())
                        return true;
                    else if(p.isFire()&&dy>0)
                        return true;
                    else if(!p.isFire()&&dy<0)
                        return true;
                }
            }
        }
        return false;
    }
}
