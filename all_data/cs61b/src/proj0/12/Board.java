public class Board  {

    private Piece pieces [][] = new Piece [8][8];
    private Piece selected, checkforcount;
    private boolean redsturn = true;
    private boolean movecompleted = false;
    private int numoffire, numofwater;
    private boolean hascaptured = false;

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        Board b = new Board(false);
        while(true) {
            b.drawBoard();
            StdDrawPlus.show(100);
        }
    }

    public Board(boolean shouldBeEmpty) {
        if(shouldBeEmpty)   {
            //Make empty board

        }
        else    {
            //Make full board
            //              fire pieces
            pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
            pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
            pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
            pieces[6][0] = new Piece(true, this, 6, 0, "pawn");
            pieces[1][1] = new Piece(true, this, 1, 1, "shield");
            pieces[3][1] = new Piece(true, this, 3, 1, "shield");
            pieces[5][1] = new Piece(true, this, 5, 1, "shield");
            pieces[7][1] = new Piece(true, this, 7, 1, "shield");
            pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
            pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
            pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
            pieces[6][2] = new Piece(true, this, 6, 2, "bomb");

            //              water pieces
            pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
            pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
            pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
            pieces[7][7] = new Piece(false, this, 7, 7, "pawn");
            pieces[0][6] = new Piece(false, this, 0, 6, "shield");
            pieces[2][6] = new Piece(false, this, 2, 6, "shield");
            pieces[4][6] = new Piece(false, this, 4, 6, "shield");
            pieces[6][6] = new Piece(false, this, 6, 6, "shield");
            pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
            pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
            pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
            pieces[7][5] = new Piece(false, this, 7, 5, "bomb");
        }
    }

    public Piece pieceAt(int x, int y)  {
        if(x<0||y<0||x>7||y>7)  {
            return null;
        }
        return pieces[x][y];
    }

    public boolean canSelect(int x, int y)  {
        if(pieceAt(x,y)!=null)  {
            if(selected==null||!movecompleted)  {
                return true;
            }
        }
        else    {
            if(!validMove()&&!movecompleted)    { // COME BACK
                return true;
            }
            if(hascaptured) {
                if(validMove())  {  //COME BACK TO THIS
                    return true;
                }
            }
        }
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf)    {
        if(redsturn)    { //red turn immediate diagonal
            if(yf-yi==1 && Math.abs(xf-x1)==1)  {
                return true;
            }
            else if (yf-yi==2 && Math.abs(xf-x1==2)) { //capture
                return true;
            }
        }
        else    {
            if(yf-yi==-1 && Math.abs(xf-x1)==1)  { //blue diagonal
                return true;
            }
            else if (yf-yi==-2 && Math.abs(xf-x1==2)) { //blue capture
                return true;
            }
        }
    }

    public void select(int x, int y)    {
        if(pieceAt(x,y)!=null)  {
            selected = pieceAt(x,y);
        }
        else    {
            selected.move(x,y);
            movecompleted = true;
        }
    }

    public void place(Piece p, int x, int y)    {
        if(p==null||x<0||y<0||x>7||y>7) {

        }
        else    {
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y)   {
        if(x<0||y<0||x>7||y>7)  {
            System.out.println("M8, yur x n y r ouda bounz.");
            return null;
        }
        else if (pieceAt(x,y)==null) {
            System.out.println("M8, thur aint nudin dur.")
            return null;
        }
        else    {
            Piece holditbreh = pieces[x][y];
            pieces[x][y] = null;
            return holditbreh;
        }
    }

    public boolean canEndTurn() {
        return movecompleted;
    }

    public void endTurn()   {
        redsturn = !redsturn;
        movecompleted = false;
        selected = null;
    }

    public String winner()  {
        numofwater = 0;
        numoffire = 0;
        for (int i = 0; i<8; i++) {
            for (int j = 0; j<8; j++) {
                checkforcount = pieceAt(i,j)
                if(checkforcount!=null)  {
                    if(checkforcount.isFire())  {
                        numoffire+=1;
                    }
                    else    {
                        numofwater+=1;
                    }
                }
            }
            
        }
        if(numofwater>0&&numoffire==0)  {
            return "Water";
        }
        else if (numofwater==0&&numoffire>0) {
            return "Fire";
        }
        else if (numofwater==0&&numoffire==0) {
            return "No one";
        }
        else    {
            return null;
        }
    }

    private void drawBoard()    {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (pieces[i][j] != null)   {
                    String imgName = imageName(pieces[i][j]);
                    StdDrawPlus.picture(i + .5, j + .5, imgName, 1, 1);
                }
                //Let's put some images up
                // if(j == 0 && i%2 == 0)    {
                //     StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                // }

            }
        }
    }

    private String imageName(Piece p)   {
        String result = "img/";
        if(p.isBomb())  {
            result+="bomb";
        }
        else if (p.isShield()) {
            result+="shield";
        }
        else    {
            result+="pawn";
        }
        if (p.isFire()) {
            result+="-fire";
        }
        else    {
            result+="-water";
        }
        if (p.isKing()) {
            result+="-crowned";
        }
        result+=".png";
        return result;
    }
}