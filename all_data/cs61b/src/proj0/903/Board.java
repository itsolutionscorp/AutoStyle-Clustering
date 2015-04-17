public class Board {
    private Piece[][] pieces;
    private Piece selection;    
    private boolean movedThisTurn;
    private int currentPlayer;

    static public void main(String[] args) {
        StdDrawPlus.setScale(0, 7);

        Board checkers = new Board(false);
        Piece selectedPiece = null;

        while(true) {
            // INPUT
            if(StdDrawPlus.mousePressed()) {
                int x = (int)(StdDrawPlus.mouseX() + 0.5);
                int y = (int)(StdDrawPlus.mouseY() + 0.5);
                if(checkers.canSelect(x, y)) {
                    checkers.select(x, y);
                    selectedPiece = checkers.pieceAt(x, y);
                }
            }

            if(StdDrawPlus.isSpacePressed() && checkers.canEndTurn()) {
                checkers.endTurn();
                selectedPiece = null;
                System.out.println("Ended turn");
            }


            // DRAW BOARD + PIECES
            for(int y = 0; y < 8; y++) {
                for(int x = 0; x < 8; x++) {

                    if(x % 2 + y % 2  == 1) {
                        StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    } else {
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    }
                    if(checkers.pieceAt(x, y) == selectedPiece && selectedPiece != null) {
                        StdDrawPlus.setPenColor(StdDrawPlus.YELLOW); 
                    }

                    StdDrawPlus.filledSquare(x, y, 0.5);

                    Piece p = checkers.pieceAt(x, y);
                    if(p != null)
                    {
                        String filename = "pawn";
                        if(p.isBomb()) {
                            filename = "bomb";
                        }
                        if(p.isShield()) {
                            filename = "shield";
                        }

                        if(p.isFire()) {
                            filename += "-fire";
                        } else {
                            filename += "-water";
                        }

                        if(p.isKing()) {
                            filename += "-crowned";
                        }

                        filename += ".png";

                        StdDrawPlus.picture(x, y, "img/" + filename, 1, 1);
                    }
                }
            }

            StdDrawPlus.show(10);
        }
    }

    public Board(boolean shouldBeEmpty) {
        currentPlayer = 0;
        selection = null;
        movedThisTurn = false;
        pieces = new Piece[8][8];

        String[] pieceTypes = {null,
                               "pawn",
                               "shield",
                               "bomb"};

        if(!shouldBeEmpty) {
           int[][] boardTemplate = {{0, 1, 0, 1, 0, 1, 0, 1},
                                    {2, 0, 2, 0, 2, 0, 2, 0},
                                    {0, 3, 0, 3, 0, 3, 0, 3},
                                    {0, 0, 0, 0, 0, 0, 0, 0},
                                    {0, 0, 0, 0, 0, 0, 0, 0},
                                    {3, 0, 3, 0, 3, 0, 3, 0},
                                    {0, 2, 0, 2, 0, 2, 0, 2},
                                    {1, 0, 1, 0, 1, 0, 1, 0}};
            for(int y = 0; y < 8; y++) {
                for(int x = 0; x < 8; x++) {
                    if(boardTemplate[7-y][x] != 0) {
                        pieces[y][x] = new Piece(y < 3, this, x, y, pieceTypes[boardTemplate[7-y][x]]);
                    }
                }
            }

        }
    }

    public Piece pieceAt(int x, int y) {
        try {
            return pieces[y][x];
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("Error: (" + x + ", " + y + ") is out of bounds");
            return null;
        }
    }

    public boolean canSelect(int x, int y) {
        if(x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }

        if(pieceAt(x, y) != null) {
            if(pieceAt(x, y).side() == currentPlayer)
            {
                return selection == null || movedThisTurn == false;
            }
        } else if(selection != null) {
            int selectionX = 0;
            int selectionY = 0;

            for(int _y = 0; _y < 8; _y++) {
                for(int _x = 0; _x < 8; _x++) {
                    if(pieceAt(_x, _y) == selection)
                    {
                         selectionX = _x;
                         selectionY = _y;
                    }
                }
            }

            // Piece was moved but no capture
            if(movedThisTurn && !selection.hasCaptured()) {
                return false;
            }

            // Basic move
            if(!selection.hasCaptured() && Math.abs(x - selectionX) == 1 &&
               y - selectionY == selection.side()*-2 + 1) {
                return true;
            }
          
            // Capture move
            if((Math.abs(x - selectionX) == 2 && // Diagonal capture
                y - selectionY == selection.side()*-4 + 2) ||
               (selection.isKing() &&
                ((Math.abs(x - selectionX) == 2 && // Diagonal capture for king
                  Math.abs(y - selectionY) == 2) ||
                 (Math.abs(x - selectionX) == 2 && // Horizontal capture for king
                  y - selectionY == 0) ||
                 (x - selectionX == 0 && // Vertical capture for king
                  Math.abs(y - selectionY) == 2)))) {

                int skipX = (selectionX + x) / 2;
                int skipY = (selectionY + y) / 2;
                if(pieceAt(skipX, skipY) != null) {
                    if(pieceAt(skipX, skipY).side() != selection.side())
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void select(int x, int y) {
        if(pieceAt(x, y) == null) {
            selection.move(x, y);
            movedThisTurn = true;
        } else {
            selection = pieceAt(x, y);     
        }
    }

    public void place(Piece p, int x, int y) {
        if(p != null) {
            try {
                pieces[y][x] = p;
            }
            catch(IndexOutOfBoundsException e)
            {
                System.out.println("Error: (" + x + ", " + y + ") is out of bounds");
                return;
            }
        }
    }

    public Piece remove(int x, int y) {
        try {
            if(pieces[y][x] == null)
            {
                System.out.println("Warning: no piece exists at (" + x + ", " + y + ")");
            }
            Piece p = pieces[y][x];
            pieces[y][x] = null;
            return p;
        }
        catch(IndexOutOfBoundsException e)
        {
            System.out.println("Error: (" + x + ", " + y + ") is out of bounds");
            return null;
        }
    }

    public boolean canEndTurn() {
        return movedThisTurn;
    }

    public void endTurn() {
        if(selection != null)
        {
            selection.doneCapturing();
        }
        selection = null;
        currentPlayer = 1 - currentPlayer;
        movedThisTurn = false;
    }

    public String winner() {
        int fireCount = 0;
        int waterCount = 0;
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                if(pieceAt(x, y) != null)
                {
                    if(pieceAt(x, y).isFire())
                    {
                        fireCount++;
                    } else {
                        waterCount++;
                    }
                }
            }
        }
        
        if(waterCount == 0 && fireCount > 0) {
            return "Fire";
        }
        if(fireCount == 0 && waterCount > 0) {
            return "Water";
        }
        if(fireCount == 0 && waterCount == 0) {
            return "No one";
        }
        return null;
    }
}