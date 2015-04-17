public class Board {
    private Piece[][] pieces;
    private int sideN = 0;
    private boolean selected = false;
    private boolean moved = false;
    private int fiNum = 0;
    private int waNum = 0;
    // private Piece selectedPiecee;
    private int selectedPieceX;
    private int selectedPieceY;
    private String selectedType;
    // private int selectedPieceSide;
    private int placedX;
    private int placedY;
    private boolean goOn = true;


    // public static Piece emptyPiece;

    public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty == true) {
            pieces = new Piece[8][8];
        }
        else {
            pieces = new Piece[8][8];
            for (int y = 0; y < 8; y++) {
                for (int x = 0; x < 8; x++) {
                    if (y == 0 && x % 2 == 0) {
                        pieces[y][x] = new Piece(true, this, x, y, "pawn");
                        fiNum += 1;
                    } 
                    else if (y == 1 && x % 2 == 1) {
                        pieces[y][x] = new Piece(true, this, x, y, "shield");
                        fiNum += 1;
                    }
                    else if (y == 2 && x % 2 == 0) {
                        pieces[y][x] = new Piece(true, this, x, y, "bomb");
                        fiNum += 1;
                    }
                    else if (y == 5 && x % 2 == 1) {
                        pieces[y][x] = new Piece(false, this, x, y, "bomb");
                        waNum += 1;
                    }
                    else if (y == 6 && x % 2 == 0) {
                        pieces[y][x] = new Piece(false, this, x, y, "shield");
                        waNum += 1;
                    }
                    else if (y == 7 && x % 2 == 1) {
                        pieces[y][x] = new Piece(false, this, x, y, "pawn");
                        waNum += 1;
                    }
                }
            }
            

            // }
        }
    }


    private boolean validCapture(int xi, int yi, int xf, int yf) {
        if (pieceAt(xi, yi).isKing()) {
            if ((Math.abs(xi - xf) == 2) && (Math.abs(yi - yf) == 2)) {
                if ((pieceAt(xf, yf) == null) && (pieceAt((xi+xf)/2, (yi+yf)/2) != null)) {
                    if ((pieceAt(xi, yi).side() 
                        != pieceAt((xi+xf)/2, (yi+yf)/2).side())) {
                        return true;
                    }
                    else {return false;}
                }
                else {
                    return false;
                }           
            }
            else {return false;}
        }

        else if (pieceAt(xi, yi).isFire()) {
            if ((Math.abs(xi - xf) == 2) && (yf - yi == 2)) {
                if ((pieceAt(xf, yf) == null) && (pieceAt((xi+xf)/2, (yi+yf)/2) != null)) {
                    if ((pieceAt(xi, yi).side() 
                        != pieceAt((xi+xf)/2, (yi+yf)/2).side())) {
                        return true;
                    }
                    else {return false;}
                }
                else {
                    return false;
                }           
            }
            else {return false;}
        }

        else  {
            if ((Math.abs(xi - xf) == 2) && (yi - yf == 2)) {
                if ((pieceAt(xf, yf) == null) && (pieceAt((xi+xf)/2, (yi+yf)/2) != null)) {
                    if ((pieceAt(xi, yi).side() 
                        != pieceAt((xi+xf)/2, (yi+yf)/2).side())) {
                        return true;
                    }
                    else {return false;}
                }
                else {
                    return false;
                }           
            }
            else {return false;}
        }            
    }


    private boolean validMove(int xi, int yi, int xf, int yf) {
        if (xf > 7 || yf > 7 || xf < 0 || yf < 0) {
            return false;
        }

        else if (pieceAt(xi, yi).isKing()) {
            if ((Math.abs(xi - xf) == 1) && (Math.abs(yi - yf) == 1)) {
                if (pieceAt(xf, yf) == null) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else if ((Math.abs(xi - xf) == 2) && (Math.abs(yi - yf) == 2)) {
                if ((pieceAt(xf, yf) == null) && (pieceAt((xi+xf)/2, (yi+yf)/2) != null)) {
                    if ((pieceAt(xi, yi).side() 
                        != pieceAt((xi+xf)/2, (yi+yf)/2).side())) {
                        return true;
                    }
                    else {return false;}
                }
                else {
                    return false;
                }           
            }
            else {return false;}
        }

        else if (pieceAt(xi, yi).isFire()) {
            if ((Math.abs(xi - xf) == 1) && (yf - yi == 1)) {
                if (pieceAt(xf, yf) == null) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else if ((Math.abs(xi - xf) == 2) && (yf - yi == 2)) {
                if ((pieceAt(xf, yf) == null) && (pieceAt((xi+xf)/2, (yi+yf)/2) != null)) {
                    if ((pieceAt(xi, yi).side() 
                        != pieceAt((xi+xf)/2, (yi+yf)/2).side())) {
                        return true;
                    }
                    else {return false;}
                }
                else {
                    return false;
                }           
            }
            else {return false;}
        }

        else  {
            if ((Math.abs(xi - xf) == 1) && (yi - yf == 1)) {
                if (pieceAt(xf, yf) == null) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else if ((Math.abs(xi - xf) == 2) && (yi - yf == 2)) {
                if ((pieceAt(xf, yf) == null) && (pieceAt((xi+xf)/2, (yi+yf)/2) != null)) {
                    if ((pieceAt(xi, yi).side() 
                        != pieceAt((xi+xf)/2, (yi+yf)/2).side())) {
                        return true;
                    }
                    else {return false;}
                }
                else {
                    return false;
                }           
            }
            else {return false;}
        }            
    }

    public Piece pieceAt(int x, int y) { 
        /* NNNNNNPPPPPP*/
        if ((x > 7) || (x < 0) || (y > 7) || (y < 0)) {
            return null;
        }
        else {return pieces[y][x];}
    }

    public boolean canSelect(int x, int y) {
        if (x > 7 || y > 7 || x < 0 || y < 0) {
            return false;
        }
        else if (pieceAt(x, y) == null) {
            if ((moved == false) && (selected == true)) {
                if (validMove(selectedPieceX, selectedPieceY, x, y)) {
                    return true;
                }
                else {return false;}
            }
            else if ((moved == true) && (pieceAt(placedX, placedY) != null)) {
                if (pieceAt(placedX, placedY).hasCaptured()){
                    if (validCapture(placedX, placedY, x, y)) {
                        return true;
                    }
                    else {return false;} 
                }
                else {return false;}              
            }
            else {return false;}
        }

        else if (sideN == pieceAt(x, y).side()) {
             /* NNNNNNPPPPPP*/
            if (selected == false) {
                return true;
            }
            else if ((selected == true) && (moved == false)) {
                return true;                
            }
            else {return false;}
        }

        else {return false;}
    }

    public void select(int x, int y) {   
            if (pieceAt(x, y) == null) {
                if (moved == false) {
                    place(pieceAt(selectedPieceX, 
                    selectedPieceY), x, y);
                    pieceAt(selectedPieceX, selectedPieceY).move(x, y);
                    remove(selectedPieceX, selectedPieceY);
                    // System.out.println("waNu" + waNum);
                    // System.out.println("fiNu" + fiNum);
                    moved = true;
                }
                else {
                    place(pieceAt(placedX,placedY), x, y);
                    pieceAt(placedX, placedY).move(x, y);
                    remove(placedX, placedY);                    
                }
            }
            else {
                // selectedPiece = pieceAt(x, y);
                selected = true;
                selectedPieceX = x;
                selectedPieceY = y;
            }

               // System.out.println("fiNu" + fiNum);
        		// System.out.println("waNu" + waNum);
    }

    private void drawWhite(int x, int y) {
        if (pieceAt(x, y) != null) {
            // selectedPiece = pieceAt(x, y);
            // selectedPieceSide = pieceAt(x, y).side();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i != x) || (j != y)) {
                        if (pieceAt(i, j) != null) {  
                            drawEmpty(i, j);
                            StdDrawPlus.picture(i + .5, j + .5, getType(pieceAt(i, j))+ ".png", 1, 1);
                        }
                    }
                }
            }
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            StdDrawPlus.picture(x + .5, y + .5, getType(pieceAt(x, y))+ ".png", 1, 1);
        }

    }

    private String getType(Piece x) {
        /* NNNNNNPPPPPP*/
        if (x == null) {
            selectedType = "null";
            }

        else if (x.isShield()) {
            selectedType = "img/shield";
            if (x.isFire()) {
                selectedType += "-fire";
                if (x.isKing()) {
                    selectedType += "-crowned";
                }
            }
            else {
                selectedType += "-water";
                if (x.isKing()) {
                    selectedType += "-crowned";
                }
            }
        }
        else if (x.isBomb()) {
            selectedType = "img/bomb";
            if (x.isFire()) {
                selectedType += "-fire";
                if (x.isKing()) {
                    selectedType += "-crowned";
                }
            }
            else {
                selectedType += "-water";
                if (x.isKing()) {
                    selectedType += "-crowned";
                }
            }
        }
        else {
            selectedType = "img/pawn";
            if (x.isFire()) {
                selectedType += "-fire";
                if (x.isKing()) {
                    selectedType += "-crowned";
                }
            }
            else {
                selectedType += "-water";
                if (x.isKing()) {
                    selectedType += "-crowned";
                }
            }
        }
        // System.out.println(selectedType);
        return selectedType;
    }

    public void place(Piece p, int x, int y) {
        if ((x > -1) && (x < 8) && (y > -1) && (y < 8)) {     
            if (pieceAt(x, y) != null) {
                remove(x, y);
            }
            pieces[y][x] = p;
            if (p.side() == 0) {
                    fiNum += 1;
            }   
            else {
                    waNum += 1;
            }
            // p.move(x, y);
            // moved = true;
            placedX = x;
            placedY = y;
        }
        // System.out.println("at first fire" + fiNum);
        // System.out.println("at first wat" + waNum);
    }

    public Piece remove(int x, int y) {
        /* NNNNNNPPPPPP*/
        Piece removed = pieceAt(x, y);
        if (removed != null) {
            if (removed.side() == 0) {
                fiNum -= 1;
            }
            else if (removed.side() == 1)
            {
                waNum -= 1;
            }
        }
        pieces[y][x] = null;
        // drawEmpty(x, y);
        return removed;
    }

    public boolean canEndTurn() {
        if (moved == true) {
            return true;
        }
        else {
            return false;
        }
    }

    public void endTurn() {
        System.out.println("placedX is " + placedX);
        System.out.println("placedY is " + placedY);
        if (canEndTurn()) {
            if (pieceAt(placedX, placedY) != null) {
                pieces[placedY][placedX].doneCapturing();
            }
                moved = false;
                selected = false;
                sideN = Math.abs(1-sideN);
        }
    }

    public String winner() {
        if ((this.fiNum == 0) && (this.waNum == 0)) {
            return "No one";
        }
        else if ((this.fiNum == 0) && (this.waNum != 0)) {
            return "Water";  
        }
        else if ((this.waNum == 0) && (this.fiNum != 0)) {
            return "Fire";
        }
        else {
            return null;
        }
    }

    private void drawEmpty(int x, int y) {
        /* NNNNNNPPPPPP*/
        if ((x + y) % 2 == 0) {
            StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
        }
        else {
            StdDrawPlus.setPenColor(StdDrawPlus.RED);  
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);       
        }
        StdDrawPlus.filledSquare(x + .5, y + .5, .5);   
    }


    private void drawPiece(int N) {
        /* NNNNNNPPPPPP*/
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if ((x + y) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                // StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (y == 0 && x % 2 == 0) {
                    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
                } 
                else if (y == 1 && x % 2 == 1) {
                    StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
                }
                else if (y == 2 && x % 2 == 0) {
                    StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
                }
                else if (y == 5 && x % 2 == 1) {
                    StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
                }
                else if (y == 6 && x % 2 == 0) {
                    StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
                }
                else if (y == 7 && x % 2 == 1) {
                    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
                }
            }
        }
    }


    private void drawEmptyBoard(int N) {
        /* NNNNNNPPPPPP*/
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }
    private void refresh() {
        drawEmptyBoard(8);
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (pieceAt(x, y) != null) {
                    StdDrawPlus.picture(x + .5, y + .5, 
                        getType(pieceAt(x, y))  + ".png", 1, 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Board newboard = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        newboard.drawPiece(8);
        // newboard.goOn = true;

        /*test remove*/
        // System.out.println(newboard.remove(0, 0).isFire()); 
        while(newboard.goOn) {
            if (StdDrawPlus.mousePressed()) {
                double ix = StdDrawPlus.mouseX();
                double iy = StdDrawPlus.mouseY();
                int x = (int) ix;
                int y = (int) iy; 
                // System.out.println("x is " + x);
                // System.out.println("y is " + y);
                // System.out.println(newboard.pieceAt(x, y).isFire());
                StdDrawPlus.show(50);
                if (newboard.pieceAt(x, y) == null) {
                    if (newboard.canSelect(x, y)) {
                        // newboard.place(newboard.pieceAt(newboard.selectedPieceX, 
                        //     newboard.selectedPieceY), x, y);
                        newboard.select(x, y);
                        newboard.refresh();
                        newboard.drawWhite(x, y);
                        // newboard.remove(x - 1, y - 1);
                    }
                }
                else if (newboard.canSelect(x, y)) {
                        newboard.select(x, y);
                        newboard.drawWhite(x, y);
                        // newboard.remove(x - 1, y - 1);
                }
                if (newboard.winner() != null) {
                	newboard.goOn = false;
                } 

            }

            if (StdDrawPlus.isSpacePressed()) {
                // System.out.println(newboard.fiNum);
                // System.out.println(newboard.waNum);
                if (newboard.canEndTurn()) {
                    newboard.endTurn();
                    System.out.println("water is " + newboard.waNum);
                    System.out.println("fire is " + newboard.fiNum);
                    System.out.println(" winner is " + newboard.winner());

                }
            }
        }
        // System.out.prinln(newboard.winner());

    }
}
