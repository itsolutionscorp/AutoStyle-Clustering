public class Board {

    private Piece[][] pieces = new Piece[8][8];
    private Piece selected;
    private int selectedX = 8;
    private int selectedY = 8;
    private boolean actionPerformed = false;
    private boolean fireTurn = true;
    private boolean bombed = false;

    public static void main (String args[]) {
        // start GUI version of game
        Board board = new Board(false);
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        while (board.winner() == null) {
            board.drawBoard(N);
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (board.canSelect(x, y)) {
                    board.select(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed()) {
                if (board.canEndTurn()) {
                    board.endTurn();
                }
            }
        StdDrawPlus.show(10);
        }
    }

    public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty == false) {
            for (int x = 0; x <= 7; x++) {
                for (int y = 0; y <= 7; y++) {
                    if (y == 0 && x % 2 == 0) {
                        place(new Piece(true, this, x, y, "pawn"), x, y);
                    }
                    if (y == 1 && x % 2 != 0) {
                        place(new Piece(true, this, x, y, "shield"), x, y);
                    }
                    if (y == 2 && x % 2 == 0) {
                        place(new Piece(true, this, x, y, "bomb"), x, y);
                    }
                    if (y == 7 && x % 2 != 0) {
                        place(new Piece(false, this, x, y, "pawn"), x, y);
                    }
                    if (y == 6 && x % 2 == 0) {
                        place(new Piece(false, this, x, y, "shield"), x, y);
                    }
                    if (y == 5 && x % 2 != 0) {
                        place(new Piece(false, this, x, y, "bomb"), x, y);
                    }
                }
            }
        }
    }

    private void drawBoard(int N) {
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                if ((x + y) % 2 == 0) {
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                if (x == selectedX && y == selectedY) {
                   StdDrawPlus.setPenColor(StdDrawPlus.WHITE); 
                }
                StdDrawPlus.filledSquare(x + .5, y + .5, .5);
                if (pieceAt(x, y) != null) {
                    if (pieceAt(x, y).isFire()) {
                        if (pieceAt(x, y).isKing()) {
                            if (pieceAt(x, y).isBomb()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
                            }
                            else if (pieceAt(x, y).isShield()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
                            }
                        }
                        else {
                            if (pieceAt(x, y).isBomb()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
                            }
                            else if (pieceAt(x, y).isShield()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
                            }   
                        }
                    }
                    else {
                        if (pieceAt(x, y).isKing()) {
                            if (pieceAt(x, y).isBomb()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
                            }
                            else if (pieceAt(x, y).isShield()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
                            }
                        }
                        else {
                            if (pieceAt(x, y).isBomb()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
                            }
                            else if (pieceAt(x, y).isShield()) {
                                StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
                            }
                            else {
                                StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
                            }   
                        }   
                    }
                }
            }
        }
    }

    public Piece pieceAt(int x, int y) {
        if ((x < 0 || x > 7) || (y < 0 || y > 7)) {
            return null;
        }
        return pieces[x][y];
    }

    public boolean canSelect(int x, int y) {
        if (bombed) {
            return false;
        }
        if (selected != null && selected.hasCaptured() && pieceAt(x, y) != null) {
            return false;
        }
        if (selected != null && !selected.hasCaptured() && actionPerformed) {
            return false;
        }
        if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == fireTurn) {
            return true;
        }
        if (pieceAt(x, y) != null) {
            if (selected == null && fireTurn == pieceAt(x, y).isFire()) {
                return true;
            }
        }
        else {
            if (selected != null && validMove(x, y)) {
                return true;
            }
        }
        return false;
    }

    private boolean validMove(int x, int y) {
        if (pieceAt(x, y) != null) {
            return false;
        }
        if (selected == null && actionPerformed) {
            return false;
        }
        int temp = 1;
        if (!selected.isFire()) {
            temp = -temp;
        }
        if (pieceAt((selectedX + ((x - selectedX) / 2)), (selectedY + ((y - selectedY) / 2))) != null && pieceAt((selectedX + ((x - selectedX) / 2)), (selectedY + ((y - selectedY) / 2))).isFire() != selected.isFire()) {
            if ((Math.abs(x - selectedX) == 2) && (temp *(y - selectedY) == 2) || (selected.isKing() && temp * (y - selectedY) == -2)) {
                return true;
            }
        }
        if (((Math.abs(x - selectedX) == 1) && (temp * (y - selectedY)) == 1) || (selected.isKing() && temp * (y - selectedY) == -1)) {
            if (selected.hasCaptured()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public void select(int x, int y) {
        if (selected != null) {
            if (pieceAt(x, y) != null && pieceAt(x, y).isFire() == fireTurn) {
                selectedX = x;
                selectedY = y;
                selected = pieceAt(x, y);
            }
            else {
                selected.move(x, y);
                selectedX = x;
                selectedY = y;
                if (selected != null && selected.isBomb() && selected.hasCaptured()) {
                    selectedX = 8;
                    selectedY = 8;
                    bombed = true;
                    pieces[x][y] = null;
                    System.out.println(selectedX + " " + selectedY + " " + selected);
                }
                selected = pieceAt(x, y);
                actionPerformed = true;
            }
        }
        else {
            selected = pieceAt(x, y);
            selectedX = x;
            selectedY = y;
        }
    }

    public void place(Piece p, int x, int y) {
        if ((x < 0 || x > 7) || (y < 0 || y > 7)) {
            return;
        }
        pieces[x][y] = p;
    }

    public Piece remove(int x, int y) {
        if (pieceAt(x, y) == null) {
            System.out.println("Out of bounds");
            return null;
        }
        Piece temp = pieceAt(x, y);
        pieces[x][y] = null;
        return temp;
    }

    public boolean canEndTurn() {
        return actionPerformed;
    }

    public void endTurn() {
        actionPerformed = false;
        fireTurn = !fireTurn;
        selectedX = 8;
        selectedY = 8;
        if (selected != null) {
            selected.doneCapturing();
        }
        bombed = false;
        selected = null;
        return;
    }

    public String winner() {
        Piece fire = null;
        Piece water = null;
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                if (pieceAt(x, y) != null) {
                    if (pieceAt(x, y).isFire()) {
                        fire = pieceAt(x, y);
                    }
                    else {
                        water = pieceAt(x, y);
                    }
                }
            }
        }
        if (fire == null && water == null) {
            return "No one";
        }
        if (fire == null) {
            return "Water";
        }
        if (water == null) {
            return "Fire";
        }
        return null;
    }
}
