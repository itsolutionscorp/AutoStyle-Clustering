public class Board {

    private Piece[][] pieces;
    private static int N = 8;
    private boolean isFirePlaying;
    private boolean hasMoved;
    private int selectedX;
    private int selectedY;
    private Piece selectedPiece;
    private Piece lastSelectedPiece;
    private static final int dumby = Integer.MIN_VALUE;

    //MAIN METHOD\\

    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        //main loop
        while (true) {
            //draw the board
            b.drawBoard(N);

            //what if mouse pressed?
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (b.canSelect(x,y)) b.select(x,y);
            }
            
            //what if space is pressed?
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) b.endTurn();
            }

            if (StdDrawPlus.isNPressed()) b = new Board(false);

        }
    }

    //CONSTRUCTOR\\

    public Board(boolean shouldBeEmpty) {
        this.pieces = new Piece[N][N];
        this.isFirePlaying = true;
        this.selectedX = dumby;
        this.selectedY = dumby;
        this.selectedPiece = null;
        this.hasMoved = false;
        if (!shouldBeEmpty) {
            for (int i = 0; i < N; i ++) {
                for (int j = 0; j < N; j++) {
                    if ((i+j) % 2 == 0) {
                        if (j == 0) this.pieces[i][j] = new Piece(true,this,i,j,"pawn");
                        if (j == 1) this.pieces[i][j] = new Piece(true,this,i,j,"shield");
                        if (j == 2) this.pieces[i][j] = new Piece(true,this,i,j,"bomb");
                        if (j == 5) this.pieces[i][j] = new Piece(false,this,i,j,"bomb");
                        if (j == 6) this.pieces[i][j] = new Piece(false,this,i,j,"shield");
                        if (j == 7) this.pieces[i][j] = new Piece(false,this,i,j,"pawn");
                    }
                }
            }
        }
    }

    //OTHER METHODS\\

    //draws the squares on the board
    private void drawBoard(int N) {
        String type;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Piece piece = pieces[i][j];
                if (i == selectedX && j == selectedY) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                else if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.DARK_GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (piece != null) {
                    if (piece.isShield()) type = "shield";
                    else if (piece.isBomb()) type = "bomb";
                    else type = "pawn";
                    if (piece.isFire()) {
                        if (piece.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/" + type + "-fire-crowned.png", 1, 1);
                        else StdDrawPlus.picture(i + .5, j + .5, "img/" + type + "-fire.png", 1, 1);
                    } else {
                        if (piece.isKing()) StdDrawPlus.picture(i + .5, j + .5, "img/" + type + "-water-crowned.png", 1, 1);
                        else StdDrawPlus.picture(i + .5, j + .5, "img/" + type + "-water.png", 1, 1);
                    }

                }
            }
        }
        StdDrawPlus.show(50);
    }

    //return the piece at the given x,y coordinates
    public Piece pieceAt(int x, int y) {
        if (x > N-1 || x < 0 || y > N-1 || y < 0) return null;
        else return pieces[x][y];
    }

    //return true if piece is selectable
    public boolean canSelect(int x, int y) {
        if (x > N-1 || x < 0 || y > N-1 || y < 0) return false;
        if (pieceAt(x,y) == null) {
            if (selectedPiece != null) {
                if (validMove(selectedPiece, selectedX, selectedY, x, y)) return true;
                else return false;
            } else return false;
        } else if (isFirePlaying && pieceAt(x,y).isFire()) {
            if (selectedPiece == null) return true;
            else if (!hasMoved) return true;
            else return false;
        } else if (!isFirePlaying && !pieceAt(x,y).isFire()) {
            if (selectedPiece == null) return true;
            else if (!hasMoved) return true;
            else return false;
        } else return false;
    }

    //checks valid move
    private boolean validMove(Piece p, int px, int py, int x, int y) {
        if (Math.abs(x-px) > 2 || Math.abs(y-py) > 2 || (x+y) % 2 != 0) return false;
        else if (p.isFire()) {
            if (p.isKing() || py < y) {
                if (Math.abs(y-py) == 1 && Math.abs(x-px) == 1 && !hasMoved) return true;
                Piece p_capt = pieceAt((int) ((x+px)/2), (int) ((y+py)/2));
                if (p_capt != null && !p_capt.isFire()) return true;
                else return false;
            } else return false;
        } else if (!p.isFire()) {
            if (p.isKing() || py > y) {
                if (Math.abs(y-py) == 1 && Math.abs(x-px) == 1 && !hasMoved) return true;
                Piece p_capt = pieceAt((int) ((x+px)/2), (int) ((y+py)/2));
                if (p_capt != null && p_capt.isFire()) return true;
                else return false;
            } else return false;
        } else return false;
    }

    //select the piece at given x,y coordinates
    public void select(int x, int y) {
        lastSelectedPiece = selectedPiece;
        selectedX = x;
        selectedY = y;
        selectedPiece = pieceAt(x,y);
        if (selectedPiece == null && selectedX != dumby) {
            selectedPiece = lastSelectedPiece;
            selectedPiece.move(x,y);
            hasMoved = true;
        }
    }

    //place a Piece at the given x,y coordinates
    public void place(Piece p, int x, int y) {
        if (!(x > N-1 || x < 0 || y > N-1 || y < 0)) pieces[x][y] = p;
    }

    //remove the Piece at the given x,y coordinates and return it
    public Piece remove(int x, int y) {
        if (x > N-1 || x < 0 || y > N-1 || y < 0) {
            System.out.println("values x and y are out of bounds");
            return null;
        } else if (pieceAt(x,y) == null) {
            System.out.println("no piece at (" + x + "," + y + ")");
            return null;
        } else {
            Piece piece = pieces[x][y];
            pieces[x][y] = null;
            return piece;
        }
    }

    //return wheather or not the current player can end their turn
    public boolean canEndTurn() {
        return hasMoved;
    }

    //procced to next turn
    public void endTurn() {
        isFirePlaying = !isFirePlaying;
        hasMoved = false;
        if (selectedPiece != null) selectedPiece.doneCapturing();
        selectedX = dumby;
        selectedY = dumby;
        selectedPiece = null;
        lastSelectedPiece = null;
    }

    //return winner or 'no one'
    public String winner() {
        int num_water = 0;
        int num_fire = 0;
        for (Piece[] p_list : pieces) {
            for (Piece p : p_list) {
                if (p == null) continue;
                else if (p.isFire()) num_fire += 1;
                else num_water += 1;
            }
        }
        if (num_water == 0 && num_fire == 0) return "No one";
        else if (num_water == 0) return "Fire";
        else if (num_fire == 0) return "Water";
        else return null;
    }

}
