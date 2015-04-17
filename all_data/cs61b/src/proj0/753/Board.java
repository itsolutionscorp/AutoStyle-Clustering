

public class Board {
    //playerselect: whether or not the current player has selected something
    //playermove: whether or not the current player has moved a piece
    private Piece[][] pieces = new Piece[8][8];
    private Piece pf1, pf2, pf3, pf4, sf1, sf2, sf3, sf4, bf1, bf2, bf3, bf4, pw1, pw2, pw3, pw4, sw1, sw2, sw3, sw4, bw1, bw2, bw3, bw4;
    private boolean player1turn = true, playerselect = false, playermove = false;
    private int selectedx, selectedy;
    private boolean captured, bombed = false;
    private int watercount, firecount;


	public static void main(String[] args) {
        int n = 8;
        StdDrawPlus.setXscale(0, n);
        StdDrawPlus.setYscale(0, n);
        Board checkers = new Board(false);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j ++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
        //continuously draws the board
        while(true) {
            checkers.drawBoard(n);
            if (StdDrawPlus.mousePressed()) {
                double mousex = StdDrawPlus.mouseX();
                double mousey = StdDrawPlus.mouseY();
                if (checkers.canSelect((int) mousex, (int) mousey)){
                    checkers.select((int) mousex, (int) mousey);
                    checkers.winner();
                }
                
            }
            if (StdDrawPlus.isSpacePressed() && checkers.canEndTurn()) {
                checkers.endTurn();
            }
            StdDrawPlus.show(100);
        }

	}

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j ++) {
                if(pieces[i][j] != null) {
                    if(pieces[i][j].isFire()) {
                        if (pieces[i][j].isShield()) {
                            if (pieces[i][j].isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1);
                            else StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        }
                        else if (pieces[i][j].isBomb()) {
                            if (pieces[i][j].isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1);
                            else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        }
                        else {
                            if (pieces[i][j].isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1);
                            else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }

                    }
                    else{
                        if (pieces[i][j].isShield()) {
                            if (pieces[i][j].isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1);
                            else StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        }
                        else if (pieces[i][j].isBomb()) {
                            if (pieces[i][j].isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1);
                            else StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        }
                        else {
                            if (pieces[i][j].isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1);
                            else StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);   
                        }                 
                    }
                }
                else if ((i + j) % 2 == 0){
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                }
            }
        }

    }


	public Board(boolean shouldBeEmpty) {
        if (!shouldBeEmpty){
            boardInit();
        }			
	}

    private void boardInit() {//puts all pieces in starting position
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pieces[i][j] = null;
            }
        }
        pf1 = new Piece(true, this, 0, 0, "pawn");  
        pf2 = new Piece(true, this, 2, 0, "pawn");  
        pf3 = new Piece(true, this, 4, 0, "pawn");
        pf4 = new Piece(true, this, 6, 0, "pawn");
        sf1 = new Piece(true, this, 1, 1, "shield");
        sf2 = new Piece(true, this, 3, 1, "shield");
        sf3 = new Piece(true, this, 5, 1, "shield");
        sf4 = new Piece(true, this, 7, 1, "shield");
        bf1 = new Piece(true, this, 0, 2, "bomb");
        bf2 = new Piece(true, this, 2, 2, "bomb");
        bf3 = new Piece(true, this, 4, 2, "bomb");
        bf4 = new Piece(true, this, 6, 2, "bomb");
        pw1 = new Piece(false, this, 1, 7, "pawn");
        pw2 = new Piece(false, this, 3, 7, "pawn");
        pw3 = new Piece(false, this, 5, 7, "pawn");
        pw4 = new Piece(false, this, 7, 7, "pawn");
        sw1 = new Piece(false, this, 0, 6, "shield");
        sw2 = new Piece(false, this, 2, 6, "shield");
        sw3 = new Piece(false, this, 4, 6, "shield");
        sw4 = new Piece(false, this, 6, 6, "shield");
        bw1 = new Piece(false, this, 1, 5, "bomb");
        bw2 = new Piece(false, this, 3, 5, "bomb");
        bw3 = new Piece(false, this, 5, 5, "bomb");
        bw4 = new Piece(false, this, 7, 5, "bomb");
        place(pf1, 0, 0);
        place(pf2, 2, 0);
        place(pf3, 4, 0);
        place(pf4, 6, 0);
        place(sf1, 1, 1);
        place(sf2, 3, 1);
        place(sf3, 5, 1);
        place(sf4, 7, 1);
        place(bf1, 0, 2);
        place(bf2, 2, 2);
        place(bf3, 4, 2);
        place(bf4, 6, 2);
        place(pw1, 1, 7);
        place(pw2, 3, 7);
        place(pw3, 5, 7);
        place(pw4, 7, 7);
        place(sw1, 0, 6);
        place(sw2, 2, 6);
        place(sw3, 4, 6);
        place(sw4, 6, 6);
        place(bw1, 1, 5);
        place(bw2, 3, 5);
        place(bw3, 5, 5);
        place(bw4, 7, 5);                  
    }

    public Piece pieceAt(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            return null;
        }
        return pieces[x][y]; 

    }

    public void place(Piece p, int x, int y) {
        if (x < 8 && x >= 0 && y < 8 && y >= 0) {
            pieces[x][y] = p;
        }
    }

    public boolean canSelect(int x, int y) {
        if (bombed == true) return false;
        if ((x < 0) || (y < 0) || (x > 7) || (y > 7)) return false;
        else if ((x + y) % 2 == 1) return false;
        if (pieceAt(x, y) != null) {
            if (playerselect == false || (playerselect == true && playermove == false)) {//selecting a piece
                if (player1turn == true && pieces[x][y].isFire() == true) {//selecting fire
                    return true;
                }
                else if (player1turn == false && pieces[x][y].isFire() == false) {//selecting water
                    return true;
                }
            }
        }

        else if ((playerselect == true && playermove == false) || (playermove == true && canCapture(selectedx, selectedy))) {
            if (validMove(selectedx, selectedy, x, y)) {
                return true;
            }
        }
        return false;


    }

    public void select(int x, int y) {
        if (x < 8 && y < 8) {
            if (playerselect == true) {
                if (pieces[x][y] == null) {
                    if (Math.abs(selectedx - x) == 2) {
                        pieces[selectedx + (x - selectedx)/2][selectedy + (y - selectedy)/2] = null;
                        if (pieces[selectedx][selectedy].isBomb()) {
                            if ((x + 1) < 8 && (y + 1) < 8 && pieces[x + 1][y + 1] != null && !pieces[x + 1][y + 1].isShield()) pieces[x + 1][y + 1] = null;
                            if ((x + 1) < 8 && (y - 1) > -1 && pieces[x + 1][y - 1] != null && !pieces[x + 1][y - 1].isShield()) pieces[x + 1][y - 1] = null;
                            if ((x - 1) > -1 && (y + 1) < 8 && pieces[x - 1][y + 1] != null && !pieces[x - 1][y + 1].isShield()) pieces[x - 1][y + 1] = null;
                            if ((x - 1) > -1 && (y - 1) > -1 && pieces[x - 1][y - 1] != null && !pieces[x - 1][y - 1].isShield()) pieces[x - 1][y - 1] = null;
                            pieces[selectedx][selectedy] = null;
                            playermove = true;
                            playerselect = false;
                            bombed = true;
                        }                     
                        else {
                            pieces[selectedx][selectedy].move(x, y);
                            place(remove(selectedx, selectedy), x, y);
                            playermove = true;
                        }   
                    }
                    else{
                        pieces[selectedx][selectedy].move(x, y);
                        place(remove(selectedx, selectedy), x, y);
                        playermove = true;                        
                    }

                }

            }
            //fills in previously selected square gray
            StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            StdDrawPlus.filledSquare(selectedx + .5, selectedy + .5, .5);
            //fills in selected square white
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            playerselect = true;
            selectedx = x;
            selectedy = y;
        }
    }

    private boolean canCapture(int x, int y) {

        if (pieceAt(x, y).isKing()) {
            if ((pieceAt (x-1, y-1) != null && pieceAt(x - 1, y - 1).isFire() != pieceAt(x, y).isFire()) || (pieceAt(x + 1, y - 1) != null && pieceAt(x + 1, y - 1).isFire() != pieceAt(x, y).isFire()) || (pieceAt(x-1, y+1) != null && pieceAt(x - 1, y + 1).isFire() != pieceAt(x, y).isFire()) || (pieceAt(x+1, y+1) != null && pieceAt(x + 1, y + 1).isFire() != pieceAt(x, y).isFire())){
                return true;
            }

        }
        else {
            if (pieceAt(x, y).isFire()) {
                if ((pieceAt(x-1, y+1) != null && pieceAt(x - 1, y + 1).isFire() == false) || (pieceAt(x + 1, y + 1) != null && pieceAt(x + 1, y + 1).isFire() == false)) {
                    return true;
                }
            }
            else{
                if ((pieceAt(x-1, y-1) != null && pieceAt(x - 1, y - 1).isFire()) || (pieceAt(x+1, y-1) != null && pieceAt(x + 1, y - 1).isFire())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (pieces[xi][yi].hasCaptured()) {//piece has captured something already
            if (pieces[xi][yi].isKing()) {
                if (Math.abs(xf-xi) == 2 && Math.abs(yf-yi) == 2 && canCapture(xi, yi)) {//king piece capturing another piece
                    return true;
                }
            }
            else if (pieces[xi][yi].isFire()) {
                if (Math.abs(xf-xi) == 2 && yf-yi == 2 && canCapture(xi, yi)) {//fire piece capturing another piece
                    return true;
                }
            }
            else {
                if (Math.abs(xf-xi) == 2 && yf-yi == -2 && canCapture(xi, yi)) {//water piece capturing another piece
                    return true;
                }
            }
        }
        //piece hasn't captured anything yet
        //piece is king
        else if (pieces[xi][yi].isKing()) {
            if (Math.abs(xf-xi) == 1 && Math.abs(yf-yi) == 1) {
                if (pieces[xf][yf] == null) return true;//king moving 1 diagonal, any direction
            }
            else if (Math.abs(xf-xi) == 2 && Math.abs(yf-yi) == 2 && canCapture(xi, yi)) {//king piece capturing first piece
                return true;
            }
        }
        else if (pieces[xi][yi].isFire()) {//piece is fire
            if (Math.abs(xf-xi) == 1 && yf-yi == 1) {
                if (pieces[xf][yf] == null) return true;//fire piece moving 1 diagonal up
            }
            else if (Math.abs(xf-xi) == 2 && yf-yi == 2 && canCapture(xi, yi)) {//fire piece capturing first piece
                return true;
            }
        }
        else {//piece is water
            if (Math.abs(xf-xi) == 1 && yf-yi == -1) {
                if (pieces[xf][yf] == null) return true;//water piece moving 1 diagonal down
            }
            else if (Math.abs(xf-xi) == 2 && yf-yi == -2 && canCapture(xi, yi)) {//water piece capturing first piece
                return true;
            }
        }
        return false;
    }


    public boolean canEndTurn() {
        if (playermove == true || captured == true) {
            return true;
        }
        return false;
    }

    public void endTurn() {
        player1turn = !player1turn;
        playerselect = false;
        playermove = false;
        captured = false;
        StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        StdDrawPlus.filledSquare(selectedx + .5, selectedy + .5, .5);
        if (pieces[selectedx][selectedy] != null) pieces[selectedx][selectedy].doneCapturing();
        bombed = false;
    }

    public Piece remove(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("Coordinates are out of bounds!");
            return null;
        }
        else if (pieces[x][y] == null) {
            System.out.println("Nothing here to remove!");
            return null;
        }
        else {
            Piece holder = pieces[x][y];
            pieces[x][y] = null;
            return holder;
        }
    }

    public String winner() {
        watercount = 0;
        firecount = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    if (pieces[i][j].isFire()) firecount += 1;
                    else watercount += 1;
                }
            }
        }
        if (firecount != 0 && watercount == 0) return "Fire";
        else if (firecount == 0 && watercount != 0) return "Water";
        else if (firecount == 0 && watercount == 0) return "No one";
        return null;
    }







}