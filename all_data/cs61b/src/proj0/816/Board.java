public class Board {


	private boolean[][] pieces; // array of the location of pieces
    private Piece[] allPieces; // array of all pieces
    private int[] locationX; // array of locations of pieces corresponding to allPieces
    private int[] locationY;
    private String[] pictures;

    private boolean isFireTurn; // turn

    private Piece selected; //previously selected piece set in select method

    private int numFire; // number of fire pieces
    private int numWater; // number of water pieces

    private boolean hasMoved; // if player has moved yet during his turn

    public Board(boolean shouldBeEmpty) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        pieces = new boolean[N][N];

        if (!shouldBeEmpty) {
            numWater = 12;
            numFire = 12;
            for (int x = 0; x < N; x = x + 2) {
                for (int y = 0; y < N; y = y + 2) {
                    if (y != 4) {
                        pieces[(int) x][(int) y] = true;
                    } 
                }   
            } 
            for (int x = 1; x < N; x = x + 2) {
                for (int y = 1; y < N; y = y + 2) {
                    if (y != 3) {
                        pieces[(int) x][(int) y] = true; 
                    }
                }   
            } 
        }


        allPieces = new Piece[24];
        locationX = new int[24];
        locationY = new int[24];
        pictures = new String[24];

        int numPieces = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (!shouldBeEmpty) {
                    if (pieces[i][j]) {
                        if (j == 0) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                            Piece p = new Piece(true, this, i, j, "pawn");
                            allPieces[numPieces] = p;
                            locationX[numPieces] = i;
                            locationY[numPieces] = j;
                            pictures[numPieces] = "img/pawn-fire.png";
                            numPieces++;
                        }
                        if (j == 1) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            Piece p = new Piece(true, this, i, j, "shield");
                            allPieces[numPieces] = p;
                            locationX[numPieces] = i;
                            locationY[numPieces] = j;
                            pictures[numPieces] = "img/shield-fire.png";
                            numPieces++;
                        }
                        if (j == 2) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                            Piece p = new Piece(true, this, i, j, "bomb");
                            allPieces[numPieces] = p;
                            locationX[numPieces] = i;
                            locationY[numPieces] = j;
                            pictures[numPieces] = "img/bomb-fire.png";
                            numPieces++;
                        }
                        if (j == 5) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                            Piece p = new Piece(false, this, i, j, "bomb");
                            allPieces[numPieces] = p;
                            locationX[numPieces] = i;
                            locationY[numPieces] = j;
                            pictures[numPieces] = "img/bomb-water.png";
                            numPieces++;
                        }
                        if (j == 6) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            Piece p = new Piece(false, this, i, j, "shield");
                            allPieces[numPieces] = p;
                            locationX[numPieces] = i;
                            locationY[numPieces] = j;
                            pictures[numPieces] = "img/shield-water.png";
                            numPieces++;
                        }
                        if (j == 7) {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                            Piece p = new Piece(false, this, i, j, "pawn");
                            allPieces[numPieces] = p;
                            locationX[numPieces] = i;
                            locationY[numPieces] = j;
                            pictures[numPieces] = "img/pawn-water.png";
                            numPieces++;
                        }
                    }
                }    
            }
        } 
    }

    public static void main(String[] args) {
        Board b = new Board(false);   
        StdDrawPlus.show(100);
        b.isFireTurn = true;
        b.hasMoved = false;

        while(b.winner() == null) {
            
            while(!StdDrawPlus.isSpacePressed()) {
                if (StdDrawPlus.mousePressed()) {
                    double x = StdDrawPlus.mouseX();
                    double y = StdDrawPlus.mouseY();
                    if (b.canSelect((int) x, (int) y)) {
                        b.select((int) x, (int) y);
                    }
                }
            }

            if (b.canEndTurn()) {
                b.endTurn();
            }
        } 

        System.out.println(b.winner());
        return;
    }



    public Piece pieceAt(int x, int y) {
        if ((x < 8) && (y < 8) && (x >= 0) && (y >= 0)) {
            if (pieces[x][y]) {
                for (int i = 0; i < allPieces.length; i++) {
                    Piece p = allPieces[i];
                    if (p != null) {
                        if (locationX[i] == x && locationY[i] == y) {
                            return p;
                        }
                    }
                }
            } 
        } return null;
    }

    private int getLocation(Piece p) { // returns where x and y of the piece are stored in locationX and locationY
        for (int i = 0; i < allPieces.length; i++) {
            if (allPieces[i] == p) {
                return i;
            }
        } return -1;
    }


    private boolean validMove(Piece p, int x, int y) {
        if ((x < 8) && (y < 8) && (x >= 0) && (y >= 0) && getLocation(p) >= 0) {
            int i = getLocation(p);
            if (pieceAt(x, y) == null) {
                System.out.println("validMove, pieceAt is null");
                //valid moves for king pieces
                if (p.isKing()) {
                    System.out.println("validMove, piece is king");
                    if (Math.abs(locationX[i] - x) == Math.abs(locationY[i] - y)) {
                        if (Math.abs(locationX[i] - x) == 1) {
                            return true;
                        } else if (Math.abs(locationX[i] - x) == 2) {
                            Piece temp = pieceAt((x + ((locationX[i] - x)/2)), (y + ((locationY[i] - y)/2)));
                            return ((temp != null) && (p.isFire() != temp.isFire()));
                        }
                    } 
                //valid moves for fire pieces
                } else if (p.isFire()) {
                    System.out.println("validMove, piece is fire");
                    if ((Math.abs(x - locationX[i]) == 1) && ((y - locationY[i]) == 1)) {
                        System.out.println("validMove, firepiece can proceed");
                        return true;
                    } else if ((Math.abs(x - locationX[i]) == 2) && ((y - locationY[i]) == 2)) {
                        Piece temp = pieceAt((x + ((locationX[i] - x)/2)), (y + ((locationY[i] - y)/2)));
                        return ((temp != null) && (!temp.isFire()));
                    }
                //valid moves for water pieces
                } else if (!p.isFire()) {
                    System.out.println("validMove, piece is water");
                    if ((Math.abs(x - locationX[i]) == 1) && ((y - locationY[i]) == -1)) {
                        System.out.println("validMove, waterpiece can proceed");
                        return true;
                    } else if ((Math.abs(x - locationX[i]) == 2) && ((y - locationY[i]) == -2)) {
                        Piece temp = pieceAt((x + ((locationX[i] - x)/2)), (y + ((locationY[i] - y)/2)));
                        return ((temp != null) && (temp.isFire()));
                    }
                }
            }
        } return false;
    }

    public boolean canSelect(int x, int y) {
        if ((x < 8) && (y < 8) && (x >= 0) && (y >= 0)) {
            Piece p = pieceAt(x, y);
            if (p != null) {
                System.out.println("canSelect, piece isn't null: " + " " + p.isFire() + " turn is now: " + isFireTurn);
                if ((isFireTurn == p.isFire()) || (!isFireTurn == !p.isFire())) {
                    if (selected == null) { //The player has not selected a piece yet.
                        System.out.println("canSelect, selected is null");
                        return true;
                    } else if (!hasMoved) { //The player has selected a piece, but did not move it.
                        System.out.println("canSelect, selected has't moved");
                        return true;
                    }
                } else {
                    return false;
                }
            }
            if (selected != null && !hasMoved && validMove(selected, x, y)) { 
                 System.out.println("canSelect, selected will move to " + x + ", " + y);
                return true;
            }
            if (selected != null && selected.hasCaptured() && validMove(selected, x, y)) {
                System.out.println("canSelect, selected has captured and will move to " + x + ", " + y);
                return true;
            } 
        } return false;
    }

    public void select(int x, int y) {
        Piece p = pieceAt(x, y);
        if (p != null) {
            System.out.println("select, piece chosen");
            if (selected != null && selected != p && !hasMoved && getLocation(selected) >= 0) {
                System.out.println("select, selected is already " + selected);
                int i = getLocation(selected);
                if ((locationX[i] + locationY[i]) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(locationX[i] + .5, locationY[i] + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.picture(locationX[i] + .5, locationY[i] + .5, pictures[i], 1, 1);
                StdDrawPlus.show(100); 
                System.out.println("select, Cleared ");  
                selected = null;
            }
            System.out.println("select, selected was " + selected);
            selected = p;
            if (getLocation(selected) >= 0) {
                int i = getLocation(selected);
                 System.out.println("select, selected is now " + selected);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.picture(x + .5, y + .5, pictures[i], 1, 1);
                StdDrawPlus.show(100);
            }
        } else {
            if (selected != null && getLocation(selected) >= 0) {
                int i = getLocation(selected);
                hasMoved = true;
                selected.move(x, y);
                if (selected.isKing()) {
                    if (selected.isBomb()) {
                        if (selected.isFire()) {
                            pictures[i] = "img/bomb-fire-crowned.png";
                        } else {
                            pictures[i] = "img/bomb-water-crowned.png";
                        }
                    } else if (selected.isShield()) {
                        if (selected.isFire()) {
                            pictures[i] = "img/shield-fire-crowned.png";
                        } else {
                            pictures[i] = "img/shield-water-crowned.png";
                        }
                    } else if (selected.isFire()) {
                        pictures[i] = "img/pawn-fire-crowned.png";
                    } else {
                        pictures[i] = "img/pawn-water-crowned.png";
                    }
                }
            }
        } 
    }

    public void place (Piece p, int x, int y) {
        if (getLocation(p) >= 0 ) {
            int k = getLocation(p);
            if ((x < 8) && (y < 8) && (x >= 0) && (y >= 0)) {
                Piece temp = pieceAt(x, y);
                if (temp != null && getLocation(temp) >= 0) {
                    System.out.println("Removing obstacle");
                    int i = getLocation(temp);
                    allPieces[i] = null;
                    remove(x, y);
                }
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.picture(x + .5, y + .5, pictures[k], 1, 1);
                locationX[k] = x;
                locationY[k] = y;
                pieces[x][y] = true;
                StdDrawPlus.show(100);
            }
        }
        if (p.isFire()) {
                    numFire++;
                } else {
                    numWater++;
                }
    }

    public Piece remove(int x, int y) {
        if ((x < 8) && (y < 8) && (x >= 0) && (y >= 0) && pieces[x][y]) {
            System.out.println("remove: piece at " + x + ", " + y);
            for (int i = 0; i < allPieces.length; i++) {
                if (allPieces[i] != null) {
                    if (locationX[i] == x && locationY[i] == y) {
                        Piece p = allPieces[i]; 
                        System.out.println("remove: found piece at " + x + ", " + y);
                        if (p.isFire()) {
                            numFire = numFire - 1;
                        } else {
                            numWater = numWater - 1;
                        }
                        pieces[x][y] = false;
                        allPieces[i] = null;
                        if ((x + y) % 2 == 0) {
                            StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                        } else {
                            StdDrawPlus.setPenColor(StdDrawPlus.RED);
                        }
                        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                        StdDrawPlus.show(100);

                        return p;
                    }
                }
            }
        }
        if ((x + y) % 2 == 0) {
                            StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                        } else {
                            StdDrawPlus.setPenColor(StdDrawPlus.RED);
                        }
                        StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                        StdDrawPlus.show(100);
        return null;
    }

    public boolean canEndTurn() {
        return (selected != null && hasMoved);
    }

    public void endTurn() {
        if (getLocation(selected) >= 0) {
            int i = getLocation(selected);
            if (allPieces[i] != null) {
                if ((locationX[i] + locationY[i]) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                } else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(locationX[i] + .5, locationY[i] + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.picture(locationX[i] + .5, locationY[i] + .5, pictures[i], 1, 1);
                StdDrawPlus.show(100);
            }
        }
        selected.doneCapturing();
        selected = null;
        System.out.println("endTurn, selected should be null: " + selected);
        isFireTurn = !isFireTurn;
        hasMoved = false;
    }

    public String winner() { 
        // int numFire = 0;
        // int numWater = 0;
        // for (int i = 0; i < allPieces.length; i++) {
        //     Piece p = allPieces[i];
        //     if (p != null && p.isFire()) {
        //         numFire++;
        //     }
        //     if (p != null && !p.isFire()) {
        //         numWater++;
        //     }
        // }     

        if ((numWater == 0) && (numFire == 0)) {
            return "No One";
        } else if ((numWater == 0) && (numFire > 0)) {
            return "Fire";
        } else if ((numFire == 0) && (numWater > 0)) {
            return "Water";
        }
        return null;
    }
}

