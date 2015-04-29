public class Board{
	
    private static Piece[][] pieces = new Piece[8][8];
    //when true player 1 turn
    //when false player 2 turn
    private boolean player = true;
    private boolean hasselected = false;
    private boolean hasmoved = false;
    private int originalx;
    private int originaly;
    private Piece selected_piece;

	private static void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    if(pieces[i][j] == null){
                        continue;

                    }
                    if (pieces[i][j].isBomb()) {
                        //System.out.println("in bomb");
                        if(pieces[i][j].isFire()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png",1,1);
                        }
                        else{
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png",1,1);
                    }
                    }

                    if (!pieces[i][j].isBomb() && !pieces[i][j].isShield() && pieces[i][j] != null ) {
                        //System.out.println("in pawn");
                        if(pieces[i][j].isFire()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png",1,1);}
                        else
                        {StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png",1,1);}
                    }
                
                    if (pieces[i][j].isShield()) {
                        //System.out.println("in shield");
                        if(pieces[i][j].isFire()) {
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png",1,1);}
                        else
                        {StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png",1,1);}
                    }


              	
            }
        }
    } // end of DrawBoard Method 


    public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty) {
           for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pieces[i][j] = null;
                    }
                }
        }

        else{

        //pieces[column][row]

        //Pawns Fire:
        pieces[0][0] = new Piece(true, this, 0, 0, "pawn");
        pieces[2][0] = new Piece(true, this, 2, 0, "pawn");
        pieces[4][0] = new Piece(true, this, 4, 0, "pawn");
        pieces[6][0] = new Piece(true, this, 6, 0, "pawn");

        //Shields Fire:

        pieces[1][1] = new Piece(true, this, 1, 1, "shield");
        pieces[3][1] = new Piece(true, this, 3, 1, "shield");
        pieces[5][1] = new Piece(true, this, 5, 1, "shield");
        pieces[7][1] = new Piece(true, this, 7, 1, "shield");


        //Bombs Fire:
        pieces[0][2] = new Piece(true, this, 0, 2, "bomb");
        pieces[2][2] = new Piece(true, this, 2, 2, "bomb");
        pieces[4][2] = new Piece(true, this, 4, 2, "bomb");
        pieces[6][2] = new Piece(true, this, 6, 2, "bomb");

        //Pawns Water:
        pieces[1][7] = new Piece(false, this, 1, 7, "pawn");
        pieces[3][7] = new Piece(false, this, 3, 7, "pawn");
        pieces[5][7] = new Piece(false, this, 5, 7, "pawn");
        pieces[7][7] = new Piece(false, this, 7, 7, "pawn");

        //Shields Water:
        pieces[0][6] = new Piece(false, this, 0, 6, "shield");
        pieces[2][6] = new Piece(false, this, 2, 6, "shield");
        pieces[4][6] = new Piece(false, this, 4, 6, "shield");
        pieces[6][6] = new Piece(false, this, 6, 6, "shield");

        //Bombs Water:

        pieces[1][5] = new Piece(false, this, 1, 5, "bomb");
        pieces[3][5] = new Piece(false, this, 3, 5, "bomb");
        pieces[5][5] = new Piece(false, this, 5, 5, "bomb");
        pieces[7][5] = new Piece(false, this, 7, 5, "bomb");

            }
        
        } //end of Board Method


    public Piece pieceAt(int x, int y) {
        if (x >= 8) {
            return null;
        }

        if (y >= 8) {
            return null;
        }

        return pieces[x][y];

    }

    public void place(Piece p, int x, int y){
        if ((x >=8) || (y >=8) || (p == null)) {
            return;
        }

        pieces[x][y] = p;


    }

    public Piece remove(int x, int y){
        if ((x >=8) || (y >=8)) {
            System.out.println(" Error: ***Coordinates out of bounds.*** ");
            return null;
        }

        if (pieces[x][y] == null) {
            System.out.println(" Error: No piece at x: " + x + " y: " + y + ".");
            return null;
        }

        Piece removedpiece = pieceAt(x,y);
        pieces[x][y] = null;
        return removedpiece;
    }

    
    public void select(int x, int y) {

        System.out.println("selected_piece: " + pieceAt(x,y));

        if(pieceAt(x,y) != null ) {
            selected_piece = pieces[x][y];
            originalx = x;
            originaly = y;
            System.out.println("original piece in select: " + selected_piece);

           


        } 

        else {
           selected_piece.move(x,y);
           pieces[x][y] = selected_piece;
           remove(originalx, originaly);
           hasmoved = true;

           
        }


    }



    public boolean canSelect(int x, int y){
        

        if (pieces[x][y] != null) {
            System.out.println("on place with piece" );
            if (hasselected == false || hasmoved == false) {
                return true;
            }
                
             return false; 
        }


        else
            
        {
            System.out.println("selected piece" + selected_piece);
            if (hasselected == true && hasmoved == false) {return true;}
            else if (hasselected == true && selected_piece.hasCaptured() == true){
                return true;}
            else {return false; }


    }
}


    public boolean canEndTurn(){
        //if piece moved or performed capture return true
        //else return false
        if (hasmoved == true){
            return true;
        }

        return false;
    }

    public void endTurn() {
        player = false;

    }

    public String winner () {
        if (player == true){
            return "Fire";
        }

        return "Water";


    }

        

        




    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8); 
        Board board = new Board(false);

        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
        	board.drawBoard();
            
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int xcord = (int)x;
                int ycord = (int)y;
                board.canSelect(xcord, ycord);
                board.select(xcord, ycord);
            

                
            }            
            StdDrawPlus.show(100);
        }
    } //end of MAIN METHOD. 



   



	} //end of Board CLASS. 
