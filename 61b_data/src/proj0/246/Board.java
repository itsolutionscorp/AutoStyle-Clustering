import java.awt.*;

/**
 * Created by TK on 2/9/15.
 */

public class Board {
    private Piece[][] positions = new Piece[8][8];
    private boolean FireTurn = true;
    private boolean hasSelected = false;
    private Piece selected;
    private int px;
    private int py;
    private int numFirePieces = 0;
    private int numWaterPieces = 0;
    private boolean moved = false;

    public static void main(String[] args) {
    /* start new game in GUI; doesn't return until the game is over.*/
        Board game = new Board(false);
        /*System.out.println("starting new game with: "+game.numFirePieces+" "+game.numWaterPieces);*/
        startCanvas();
        drawBoard();
        game.update();


        while (game.winner()==null) { /* start game */
            if (mousePressed()){
                double SelX = StdDrawPlus.mouseX();
                double SelY = StdDrawPlus.mouseY();
                if (game.canSelect((int) SelX,(int)SelY)) {
                    game.select((int)SelX, (int) SelY);
                }
            } /* do nothing until mouse is pressed */

            StdDrawPlus.show(100);
            if (StdDrawPlus.isSpacePressed()&&game.canEndTurn()) {/* next player turn.*/
                game.endTurn();
            }
        }
//        System.out.println("game over");
    }
    private static void startCanvas() {
        StdDrawPlus.setCanvasSize();
        StdDrawPlus.setScale(0, 8);
    }
    private static boolean mousePressed() {
        return StdDrawPlus.mousePressed();
    }
    private static void drawBoard() { /* draw the checker board without any pieces */
        StdDrawPlus.setPenColor(Color.RED);
        StdDrawPlus.filledSquare(4,4,4);
        StdDrawPlus.setPenColor(Color.LIGHT_GRAY);
        double x=0.5;
        double y=0.5;
        while (y<8) {
            while (x<8) {
                StdDrawPlus.filledSquare(x, y, 0.5);
                x+=2;
            }
            x=0.5;
            y+=2;
        }
        x = 1.5;
        y = 1.5;
        while (y<8) {
            while (x<8) {
                StdDrawPlus.filledSquare(x, y, 0.5);
                x+=2;
            }
            x=1.5;
            y+=2;
        }
    }

    public Board(boolean shouldBeEmpty) {
        emptyBoard();
        if (!shouldBeEmpty) {/* initialize board with all the pieces */
            resetPieces();
            //testBoard();
        }
    }

    public Piece pieceAt(int x, int y) {
        if (x<=7 && y<=7) {
            return positions[x][y];
        }
        else {
            return null;
        }
    }

    public boolean canSelect(int x, int y) {
        if (!inBound(x,y)) {
            return false;
        }
        if ((this.pieceAt(x,y)!=null)&&(this.pieceAt(x,y).isFire()==FireTurn)&&(hasSelected==false || (hasSelected&&!moved))) {/* if selecting a piece for the first time*/
            return true;
        } else if ((this.pieceAt(x,y)==null)&&hasSelected) { /* if choosing a destination */
            if (validmove(selected,x,y)&&(canCapture(selected,x,y)||!moved)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void select(int x, int y) {
        if (hasSelected&&pieceAt(x,y)==null) { /* move the last selected piece */
            selected.move(x,y);
            moved = true;
            px = x;
            py = y;
            update();
        } else {
//            System.out.println("selected a new piece");
            update();
            //StdDrawPlus.setPenColor(Color.WHITE);
            //StdDrawPlus.square(x + 0.5, y + 0.5, 0.5);
            hasSelected = true;
            selected = pieceAt(x,y);
            px = x;
            py =y;
        }
    }

    public void place(Piece p, int x, int y) {
        if (inBound(x, y)) {
            positions[x][y] = p;
//            p.px = x;
//            p.py = y;
            increase(p);
        }
    }

    public Piece remove(int x, int y) { /* removes a piece at x,y and reduce num fire or water piece on board */
        if (inBound(x,y)) {
            Piece respiece = pieceAt(x,y);
            if (respiece==null) {
                System.out.println("There is nothing there!");
                return null;
            }
            reduce(respiece);
            positions[x][y] = null;
            return respiece;
        } else {
            System.out.println("That is out of bounds");
            return null;
        }
    }

    public boolean canEndTurn() {
        return (hasSelected && selected!=null && moved==true);
    }

    public void endTurn() {
        moved = false;
        hasSelected = false;
        FireTurn = !FireTurn;
        selected.doneCapturing();
        selected = null;
        update();
//        System.out.println("It is fire's turn: "+FireTurn);
    }

    public String winner() {
        if (numFirePieces==0||numWaterPieces==0) {
            /* game over*/
//            System.out.println(numFirePieces+" vs. "+numWaterPieces);
            if (numFirePieces<numWaterPieces) {
                return "Water";
            } else if (numFirePieces>numWaterPieces) {
                return "Fire";
            } else {
                return "No one";
            }
        }
        return null; /* game isn't over */
    }

    /* place all pieces to their respective positions */
    private void emptyBoard() {
        numFirePieces = 0;
        numWaterPieces = 0;
        positions = new Piece[8][8];
    }

    private void resetPieces() {
        /*  Piece(boolean isFire, Board b, int x, int y, String type) */
        int[] odds = {1,3,5,7};
        int[] evens = {0,2,4,6};
        for (int i: evens) { /* place the pawns and bombs*/
            place(new Piece(true, this, i, 0, "pawn"),i,0);
            place(new Piece(true, this, i, 2, "bomb"), i, 2);
            place(new Piece(false, this, i, 6, "shield"),i,6);
        }
        for (int i: odds) { /* place the shields */
            place(new Piece(true, this, i, 1, "shield"),i,1);
            place(new Piece(false, this, i, 7, "pawn"),i,7);
            place(new Piece(false, this, i, 5, "bomb"), i, 5);
        }
    }
    private void testBoard() {
        /*  Piece(boolean isFire, Board b, int x, int y, String type) */
            place(new Piece(true, this, 0, 0, "shield"),0,0);
            place(new Piece(false, this, 1, 1, "pawn"),1,1);
            place(new Piece(false, this, 3, 3, "pawn"), 3, 3);

    }

    private boolean inBound(int x, int y) {
        return (x>=0 && x <= 7 && y>=0 && y <= 7);
    }

    private void reduce(Piece piece) {
        if (piece.isFire()) {
            numFirePieces-=1;
        } else {
            numWaterPieces-=1;
        }
    }
    private void increase(Piece piece) {
        if (piece.isFire()) {
            numFirePieces+=1;
        } else {
            numWaterPieces+=1;
        }
    }

    private boolean validmove(Piece piece, int x, int y) {
        /* fire's dy can only be positive.
           water's dy can only be negative.
           king can be either.
           can only jump over enemies.
         */
        int dx = x - px;
        int dy = y - py;
        if (validDir(piece, dy)) { /*is the piece going towards the right direction? */
            if (Math.abs(dx) == 1 && Math.abs(dy) == 1 && pieceAt(x, y) == null) { /* is the piece going 1 step diagonally and is the destination empty?*/
                return true;
            } else if (Math.abs(dx) == 2 && Math.abs(dy) == 2 && pieceAt(x, y) == null) { /*is the piece trying to capture?*/
                return canCapture(piece, x, y);
            } else {
                return false;
            }
        } else { return false; }
    }
    private boolean validDir(Piece piece, int dy) {
        if (piece.isKing()) {
            return true;
        } else if (piece.isFire()) {
//            System.out.println("this is a valid direction: "+(dy>0));
            return (dy>0);
        } else {
//            System.out.println("this is a valid direction: "+(dy<0));
            return (dy<0);
        }
    }
    private boolean isEnemy(Piece piece, int x, int y) {
        return pieceAt(x,y).side()!=piece.side();
    }

    private boolean canCapture(Piece piece, int x, int y) {
        int dx0 = Math.abs(x-px);
        int dy0 = Math.abs(y-py);
        if (dx0!=2||dy0!=2) { return false; }

        int dx = (x-px)/2;
        int dy = (y-py)/2;
        int enx = px+dx; /* coordinate between selected piece and destination */
        int eny = py+dy;
        return (this.pieceAt(enx, eny)!=null&&isEnemy(piece, enx,eny)); /*is there an enemy piece between initial & destination*/
    }
    private void update() {
        drawBoard();
        int x = 0;
        int y = 0;
        while (x<8){
            while (y<8) {
                if (positions[x][y]!=null) {
                    draw(positions[x][y], x, y);
                }
                y++;
            }
            y = 0;
            x++;
        }
    }

    private void draw(Piece piece, int x, int y) {
        String piece_type;
        if (piece.isBomb()){piece_type = "bomb";}
        else if (piece.isShield()) {piece_type = "shield";}
        else {piece_type = "pawn";}
        String side;
        if (piece.isFire()) {
            side = "fire";
        } else {
            side = "water";
        }
        String image = "img/"+piece_type+"-"+side;
        if (piece.isKing()) {
            image = image+"-crowned";
        }
        image = image+".png";
        StdDrawPlus.picture(x+0.5, y+0.5, image, 1, 1);
    }
}



