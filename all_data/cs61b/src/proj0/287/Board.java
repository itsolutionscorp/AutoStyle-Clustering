//Inspiration for highlight array drawn from Justin Liu.
public class Board{
    private static Piece[][] pieces;
    // private boolean hasSelected;
    // private boolean hasMoved;
    private int turnPlayer;
    // private boolean hasCaptured;
    // private boolean hasBegin = false;
    private int selectedXPos;
    private int selectedYPos;
    private boolean canEndTurn; //turns to true after moving;
    private boolean[][] highlight;
    private boolean movedAlready;

    //highlight 2d array boolean all false
    //unhighlight changes to false
    //highlight(x, y) changes to true



	public static void main(String[] args) {
        Board drawBoard = new Board(false);
        while(true){
            drawBoard.updateBoard();
            if (StdDrawPlus.mousePressed()) {
                double xDouble = StdDrawPlus.mouseX();
                double yDouble = StdDrawPlus.mouseY();
                int x = (int)xDouble;
                int y = (int)yDouble;
                Piece p = drawBoard.pieces[y][x];
                if (drawBoard.canSelect(x, y)) {
                    drawBoard.select(x, y);
                }
            }

            if (StdDrawPlus.isSpacePressed()){
                if (drawBoard.canEndTurn()) {
                    drawBoard.endTurn();
                }
            }

            StdDrawPlus.show(10);
        }

    }

    private void highlightBoard(int x, int y) { //DONE
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                highlight[j][i] = false;
            }
        }
        highlight[y][x] = true;
    }

    private void updateBoard() {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (highlight[j][i]) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                } else if ((i + j) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {                 
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                Piece p = this.pieces[j][i];
                String type = "";
                String king = "";
                String element = "";
                if (p != null) {
                    if (p.isFire()) {
                        element = "-fire";
                    } else {
                        element = "-water";
                    }

                    if (p.isKing()) {
                        king = "-crowned";
                    }

                    if (p.isShield()) {
                        type = "shield";
                    } else if (p.isBomb()) {
                        type = "bomb";
                    } else {
                        type = "pawn";
                    }
                    StdDrawPlus.picture(i + .5, j + .5, "img/" + type + element + king + ".png", 1, 1);
                }
            }
        }
    }

    public Board(boolean shouldBeEmpty) {
        /**
          * Code for drawing board from Princeton checkerboard as well as StdDrawDemo.
          */
        // this.hasSelected = false;
        // this.hasMoved = false;
        // this.hasCaptured = false;
        this.pieces = new Piece[8][8];
        this.turnPlayer = 0;
        this.canEndTurn = false;
        this.selectedXPos = -20;
        this.selectedYPos = -20;
        this.highlight = new boolean[8][8];
        this.movedAlready = false;

        if(shouldBeEmpty == false){
            for (int j = 0; j < 8; j++) {
                for (int i = 0; i < 8; i++) {
                    if (j == 0){
                        if (i % 2 == 0){
                            Piece pawnFire = new Piece(true, this, i, j, "pawn");
                            this.pieces[j][i] = pawnFire;
                        }
                    } else if (j == 1){
                        if (i % 2 == 1){
                            Piece shieldFire = new Piece(true, this, i, j, "shield");
                            this.pieces[j][i] = shieldFire;
                        }
                    } else if (j == 2){
                        if (i % 2 == 0){
                            Piece bombFire = new Piece(true, this, i, j, "bomb");
                            this.pieces[j][i] = bombFire;
                        }
                    } else if (j == 5){
                        if (i % 2 == 1){
                            Piece bombWater = new Piece(false, this, i, j, "bomb");
                            this.pieces[j][i] = bombWater;
                        }
                    } else if (j == 6){
                        if (i % 2 == 0){
                            Piece shieldWater = new Piece(false, this, i, j, "shield");
                            this.pieces[j][i] = shieldWater;
                        }
                    } else if (j == 7){
                        if (i % 2 == 1){
                            Piece pawnWater = new Piece(false, this, i, j, "pawn");
                            this.pieces[j][i] = pawnWater;
                        }
                    }
                }
            }
        }
    }

    public Piece pieceAt(int x, int y) { //DONE
        if ((x > 7) || (y > 7)) {
            return null;
        }
        return this.pieces[y][x];
    }

 	public boolean canSelect(int x, int y) {
        Piece p = this.pieces[y][x];
        if (p != null && !canEndTurn) { //ALL CASES FOR CLICKING A PIECE COMPLETED
            if (p.side() == this.turnPlayer) {
                return true;
            }
        } else if (p == null && selectedYPos >= 0 && selectedXPos >= 0) { //I CLICKED A BLANK
            return validMove(selectedXPos, selectedYPos, x, y);
        } 
       return false;
    }

        // if (p != null) {
        //     if (p.side() == this.turnPlayer) {
        //         if (!this.hasSelected) {
        //             return true;
        //         } else if ((this.hasSelected) && (!this.hasMoved)) {
        //             return true;
        //         }
        //     } else {
        //         return false;
        //     }
        // } else if ((this.hasSelected) && (this.hasMoved == false) 
        //             && validMove(this.selectedXPos, this.selectedYPos, x, y)) {
        //                 return true;
        // } else if ((this.hasSelected) && (this.hasCaptured) 
        //             && validMove(this.selectedXPos, this.selectedYPos, x, y)) {
        //                 return true;
        // }
        // return false;

    //Remember to make it private before submitting!!!
    private boolean validMove(int xi, int yi, int xf, int yf){
        Piece piece = this.pieces[yi][xi];
        if (piece != null) {
            if ((Math.abs(xi - xf) == 1) && (Math.abs(yi - yf) == 1 && !canEndTurn)){
                if (piece.isKing()){ //WE ARE DONE WITH KINGS MOVING 1 SPACE
                    return true;
                } else if (piece.isFire() && yf > yi) { //DONE WITH FIRE MOVING 1
                    return true;
                } else if (!piece.isFire() && yf < yi) { //DONE WITH WATER MOVING 1
                    return true;
                }
            } else if (!movedAlready) {
                if ((Math.abs(xi - xf) == 2) && (Math.abs(yi - yf) == 2)) {
                    if (yf > yi && xf > xi && pieces[yi+1][xi+1] != null && piece.isFire() != pieces[yi+1][xi+1].isFire()) {
                        if (piece.isKing() || piece.isFire()) {
                            return true;
                        }
                    } else if (yf > yi && xf < xi && pieces[yi+1][xi-1] != null && piece.isFire() != pieces[yi+1][xi-1].isFire()) {
                        if (piece.isKing() || piece.isFire()) {
                            return true;
                        }
                    } else if (yf < yi && xf < xi && pieces[yi-1][xi-1] != null && piece.isFire() != pieces[yi-1][xi-1].isFire()) {
                        if (piece.isKing() || !piece.isFire()) {
                            return true;
                        }
                    } else if (yf < yi && xf > xi && pieces[yi-1][xi+1] != null && piece.isFire() != pieces[yi-1][xi+1].isFire()) {
                        if (piece.isKing() || !piece.isFire()) {
                            return true;
                        }
                    }
                }   
            }
        }
        return false;
    } 

    public void select(int x, int y) {
        highlightBoard(x, y);
        if (pieces[y][x] != null) {
            this.selectedXPos = x;
            this.selectedYPos = y;
        } else if (this.pieces[y][x] == null) { //Clicking empty space
            Piece p = this.pieces[selectedYPos][selectedXPos];
            if ((Math.abs(this.selectedXPos - x) == 2) && (Math.abs(this.selectedYPos - y) == 2)) { //Capturing
                int middleX = ((this.selectedXPos + x) / 2);
                int middleY = ((this.selectedYPos + y) / 2);
                remove(middleX, middleY);
                if (p.isBomb()) {
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if ((y + i >= 0) && (y + i <= 7) && (x + j >= 0) && (x + j <= 7)) {
                                Piece removeBomb = this.pieces[y + i][x + j];
                                if (removeBomb != null){
                                    if (!removeBomb.isShield()) {
                                        remove(x + j, y + i);
                                    }
                                }
                            }
                        }   
                    }
                }
            } else if ((Math.abs(this.selectedXPos - x) == 1) && (Math.abs(this.selectedYPos - y) == 1)) {
                this.movedAlready = true;
            }
            p.move(x, y);
            remove(selectedXPos, selectedYPos);
            this.selectedXPos = x;
            this.selectedYPos = y;
            if (p.isBomb() && p.hasCaptured()) {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        highlight[j][i] = false;
                    }
                }
            } else {
                place(p, x, y);
            }
            
            this.canEndTurn = true;
        }

    }
    

    public void place(Piece p, int x, int y) { //DONE
        if ((x > 7) || (y > 7)) {
            return;
        }
        this.pieces[y][x] = p;
    }

    public Piece remove(int x, int y) { // DONE
        if ((x > 7) || (y > 7)) {
            System.out.println("Position out of bounds!");
            return null;
        }

        Piece p = this.pieces[y][x];

        if(p == null) {
            System.out.println("No piece at given position!");
            return null;

        } else {
            this.pieces[y][x] = null;
            return p;
        }
    }

    public boolean canEndTurn() { //DONE
        return canEndTurn;
    }

    public void endTurn() {
        this.turnPlayer = 1 - this.turnPlayer;
        this.selectedXPos = -20;
        this.selectedYPos = -20;
        this.canEndTurn = false;
        this.movedAlready = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.pieces[i][j] != null) {
                    this.pieces[i][j].doneCapturing();
                }
                this.highlight[i][j] = false;
            }
        }
    }

    public String winner() { //DONE
        int fireCount = 0;
        int waterCount = 0;
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                Piece p = pieceAt(i, j);
                if (p != null) {
                    if (this.pieces[j][i].isFire()) {
                        fireCount += 1;
                    } else {
                        waterCount += 1;
                    }
                }
            }
        }

   	    if ((waterCount + fireCount) == 0) {
            return "No one";
        } else if (waterCount == 0) {
            return "Fire";
        } else if (fireCount == 0) {
            return "Water";
        } else {
            return null;
        }
    }
}