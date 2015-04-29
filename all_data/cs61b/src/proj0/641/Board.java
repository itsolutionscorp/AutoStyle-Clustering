public class Board {
    private boolean[][] piecesexist;
    private Piece[][] pieces;
    private static int N = 8;
    private boolean empty;
    private boolean selected;
    private boolean fireturn;
    private boolean moved;
    private int selectx;
    private int selecty;
    private boolean captured;
    
    public Board(boolean shouldBeEmpty) {
        if (shouldBeEmpty == false) {
            piecesexist = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    piecesexist[i][j] = false;
                }
            }
            
            pieces = new Piece[N][N];
            for (int i = 0; i < N; i = i + 2){
                pieces[i][0] = new Piece(true, this, i, 0, "pawn");
                pieces[i+1][1] = new Piece(true, this, i+1, 1, "shield");
                pieces[i][2] = new Piece(true, this, i, 2, "bomb");
                pieces[i+1][N-3] = new Piece(false, this, i+1, N-3, "bomb");
                pieces[i][N-2] = new Piece(false, this, i, N-2, "shield");
                pieces[i+1][N-1] = new Piece(false,this, i+1, N-1, "pawn");
                piecesexist[i][0] = true;
                piecesexist[i+1][1] = true;
                piecesexist[i][2] = true;
                piecesexist[i+1][N-3] = true;
                piecesexist[i][N-2] = true;
                piecesexist[i+1][N-1] = true;
            }
            
            empty = false;
            selected = false;
            fireturn = true;
            moved = false;
            selectx = -1;
            selecty = -1;
            captured = false;
        } else {
            empty = true;
            piecesexist = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    piecesexist[i][j] = false;
                }
            }
            selected = false;
            fireturn = true;
            moved = false;
            selectx = -1;
            selecty = -1;
            pieces = new Piece[N][N];
            captured = false;
        }
    }
    
    private void drawBoard() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            }
        }
        
        if (selected) {
            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.filledSquare(selectx + .5, selecty + .5, .5);
        }
        
        for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (pieceAt(i,j) != null) {
                        String filename = "img/";
                        if (pieces[i][j].isBomb()) filename += "bomb";
                        else if (pieces[i][j].isShield()) filename += "shield";
                        else filename += "pawn";
                        if (pieces[i][j].isFire()) filename += "-fire";
                            else filename += "-water";
                        if (pieces[i][j].isKing()) filename += "-crowned";
                        filename += ".png";
                        StdDrawPlus.picture(i + 0.5, j + 0.5, filename, 1, 1);
                    }
                }
            }
    }
    
    public Piece pieceAt(int x, int y) {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (piecesexist[i][j]) empty = false;
            }
        }
        if (empty) return null;
        if (x >= N || x < 0) return null;
        if (y >= N || y < 0) return null;
        if (piecesexist[x][y]) return pieces[x][y];
        else return null;
    }
    
    public void place(Piece p, int x, int y) {
        if (x >= N || x < 0) return;
        if (y >= N || y < 0) return;
        if (p == null) return;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (pieces[i][j] == p) {
                    remove(i, j);
                }
            }
        }
        if (piecesexist[x][y]) {
            remove(x, y);
        }
        piecesexist[x][y] = true;
        pieces[x][y] = p;
    }
    
    public Piece remove(int x, int y) {
        if (x >= N || x < 0) {
            System.out.println("coordinate out of bounds!");
            return null;
        }
        if (y >= N || y < 0) {
            System.out.println("coordinate out of bounds!");
            return null;
        }
        if (piecesexist[x][y] == false) {
            System.out.println("nothing to remove!");
            return null;
        }
        piecesexist[x][y] = false;
        return pieces[x][y];
    }
    
    public void select(int x, int y) {
        if (pieceAt(x, y) != null) {
            selected = true;
            selectx = x;
            selecty = y;
        } else {
            Piece p = pieceAt(selectx, selecty);
            p.move(x, y);
            selected = true;
            selectx = x;
            selecty = y;
            if (canSelect(x, y)) select(x, y);
            else selected = false;
            moved = true;
            captured = p.hasCaptured();           
        }
    }
    
    public boolean canSelect(int x, int y) {
        if (fireturn && piecesexist[x][y] && pieces[x][y].isFire()) {
            if (!selected || (selected && !moved)) {
                return true;
            }
        }
        
        if (!fireturn && piecesexist[x][y] && !pieces[x][y].isFire()) {
            if (!selected || (selected && !moved)) {
                return true;
            }
        }
        
        if (selected && !moved && !piecesexist[x][y] && validMove(selectx, selecty, x, y)) {
            return true;
        }
        
        if (selected && captured && validMove(selectx, selecty, x, y)) {
            return true;
        }
        
        return false;
    }
    
    private boolean validMove(int xi, int yi, int xf, int yf){
        if (xi < 0 || xi >= N) return false;
        if (yi < 0 || yi >= N) return false;
        if (xf < 0 || xf >= N) return false;
        if (yf < 0 || yf >= N) return false;
        
        if (pieces[xi][yi].isFire() && !captured) {
            if (xf == xi + 1 && yf == yi + 1) return true;
            
            if (xf == xi + 1 && yf == yi - 1) {
                if (pieces[xi][yi].isKing()) return true;
            }
            
            if (xf == xi - 1 && yf == yi + 1) return true;
            
            if (xf == xi - 1 && yf == yi - 1) {
                if (pieces[xi][yi].isKing()) return true;
            }
        }
        
        if (!captured && !pieces[xi][yi].isFire()) {
            if (xf == xi + 1 && yf == yi - 1) return true;
            
            if (xf == xi + 1 && yf == yi + 1) {
                if (pieces[xi][yi].isKing()) return true;
            }
            
            if (xf == xi - 1 && yf == yi - 1) return true;
            
            if (xf == xi - 1 && yf == yi + 1) {
                if (pieces[xi][yi].isKing()) return true;
            }
        }
        
        if (pieces[xi][yi].isFire()) {
            if (xf == xi + 2 && yf == yi + 2) {
                if (piecesexist[xi+1][yi+1]) {
                    if (!pieces[xi+1][yi+1].isFire()) {
                        return true;
                    }
                }
            }
            
            if (xf == xi + 2 && yf == yi - 2) {
                if (piecesexist[xi+1][yi-1] && pieces[xi][yi].isKing()) {
                    if (!pieces[xi+1][yi-1].isFire()) {
                        return true;
                    }
                }
            }
            
            if (xf == xi - 2 && yf == yi + 2) {
                if (piecesexist[xi-1][yi+1]) {
                    if (!pieces[xi-1][yi+1].isFire()) {
                        return true;
                    }
                }
            }
            
            if (xf == xi - 2 && yf == yi - 2) {
                if (piecesexist[xi-1][yi-1] && pieces[xi][yi].isKing()) {
                    if (!pieces[xi-1][yi-1].isFire()) {
                        return true;
                    }
                }
            }
        }
        
        if (!pieces[xi][yi].isFire()) {
            if (xf == xi + 2 && yf == yi + 2) {
                if (piecesexist[xi+1][yi+1] && pieces[xi][yi].isKing()) {
                    if (pieces[xi+1][yi+1].isFire()) {
                        return true;
                    }
                }
            }
            
            if (xf == xi + 2 && yf == yi - 2) {
                if (piecesexist[xi+1][yi-1]) {
                    if (pieces[xi+1][yi-1].isFire()) {
                        return true;
                    }
                }
            }
            
            if (xf == xi - 2 && yf == yi + 2) {
                if (piecesexist[xi-1][yi+1] && pieces[xi][yi].isKing()) {
                    if (pieces[xi-1][yi+1].isFire()) {
                        return true;
                    }
                }
            }
            
            if (xf == xi - 2 && yf == yi - 2) {
                if (piecesexist[xi-1][yi-1]) {
                    if (pieces[xi-1][yi-1].isFire()) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public boolean canEndTurn() {
        if (moved) return true;
        return false;
    }
    
    public void endTurn() {
        if (selected) pieces[selectx][selecty].doneCapturing();
        fireturn = !fireturn;
        selected = false;
        selectx = -1;
        selecty = -1;
        captured = false;
        moved = false;
    }
    
    public String winner() {
        int waters = 0;
        int fires = 0;
        
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                if (piecesexist[i][j]) {
                    if (pieces[i][j].isFire()) fires += 1;
                    else waters += 1;
                }
            }
        }
        
        if (fires == 0 & waters == 0) return "No one";
        if (fires == 0) return "Water";
        if (waters == 0) return "Fire";
        return null;   
    }
    
    public static void main(String[] args) {
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        
        Board b = new Board(false);
        boolean end = false;
        /** Monitors for mouse presses. Wherever the mouse is pressed,
            a new piece appears. */
        while(true) {
            b.drawBoard();

            StdDrawPlus.show(25);
            
            if (end) return;
            if (b.winner() != null) {
                System.out.println(b.winner());
                end = true;
            }
            
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    /*if (b.pieceAt((int) x, (int) y) != null) {
                        b.select((int) x, (int) y);
                    } else {
                        int sx = b.selectx;
                        int sy = b.selecty;
                        Piece p = b.pieceAt(sx, sy);
                        p.move((int) x, (int) y);
                        if (b.canSelect((int) x, (int) y)) b.select((int) x, (int) y);
                        else b.selected = false;
                        b.moved = true;
                        b.captured = p.hasCaptured();
                    }*/
                    b.select((int) x, (int) y);
                }
            }
            
            if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) b.endTurn();
            }
                
            
        }
        
    }
}
