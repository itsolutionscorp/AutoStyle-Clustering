public class Board {

	private Piece[][] pieces;
    private boolean board_empty;
    private int current_player=0;
    private String[][] images;
    private int[] selected = {9, 9};
    private boolean piece_selected=false;
    private boolean has_moved=false;
    private boolean has_captured=false;
    private int fire_pieces;
    private int water_pieces;
    private Piece temp;
    private boolean bomb_exploded=false;
    private boolean first_move=false;

	/*Creates a new board*/
	public Board(boolean shouldBeEmpty) {
        board_empty=shouldBeEmpty;
        pieces = new Piece[8][8];
        images = new String[8][8];
        if (!board_empty) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i%2==0)&&(j==0)) {
                        pieces[i][j]=new Piece(true, this, i, j, "pawn");
                        images[i][j]= "img/pawn-fire.png";
                    }

                    else if ((i%2==1)&&(j==1)) {
                        pieces[i][j]=new Piece(true, this, i, j, "shield");
                        images[i][j]= "img/shield-fire.png";
                    }

                    else if ((i%2==0)&&(j==2)) {
                        pieces[i][j]=new Piece(true, this, i, j, "bomb");
                        images[i][j]= "img/bomb-fire.png";
                    }

                    else if ((i%2==1)&&(j==5)) {
                        pieces[i][j]=new Piece(false, this, i, j, "bomb");
                        images[i][j]= "img/bomb-water.png";
                    }

                    else if ((i%2==0)&&(j==6)) {
                        pieces[i][j]=new Piece(false, this, i, j, "shield");
                        images[i][j]= "img/shield-water.png";
                    }

                    else if ((i%2==1)&&(j==7)) {
                        pieces[i][j]=new Piece(false, this, i, j, "pawn");
                        images[i][j]= "img/pawn-water.png";
                    }
                }
            }
            countPieces();
        }
	}
	/*Draws the current board*/
    private void drawCurrentBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (selected[0]!=9) {
                    if (i==selected[0] && j==selected[1]) {
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                        if (images[i][j]!=null) {
                            StdDrawPlus.picture(i+.5, j+.5, images[i][j], 1, 1);
                        }
                    }
                    else {
                        if ((i + j) % 2 == 0) {
                            StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                        }
                        else {
                            StdDrawPlus.setPenColor(StdDrawPlus.RED);
                        }
                        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                        if (images[i][j]!=null) {
                           StdDrawPlus.picture(i+.5, j+.5, images[i][j], 1, 1);
                        }                        
                    }
                }
                else {
                    if ((i + j) % 2 == 0) {
                        StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                    }
                    else {
                        StdDrawPlus.setPenColor(StdDrawPlus.RED);
                    }
                    StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    if (images[i][j]!=null) {
                        StdDrawPlus.picture(i+.5, j+.5, images[i][j], 1, 1);
                    }
                }
            }
        }
    }
    /*Returns piece at given coordinates*/
    public Piece pieceAt(int x, int y) {
    	if (x<0||x>7||y<0||y>7) {
    		return null;
    	}

    	else if (pieces[x][y]==null) {
    		return null;
    	}

    	else {
    		return pieces[x][y];
    	}
    }

    /*Checks if a move is valid*/
    private boolean validMove(Piece p, int xi, int yi, int xf, int yf) {
        if (p==null) {
            return false;
        }

        if (p.hasCaptured()) {
            has_captured=true;
        }

        if (has_captured && Math.abs(xf-xi)!=2 && Math.abs(yf-yi)!=2) {
            return false;
        }

        if (p.isKing()) {
            
            if (Math.abs(xi-xf)==1 && Math.abs(yi-yf)==1 && !has_moved && pieces[xf][yf]==null) {
                has_captured=false;
                return true;
            }            

            else if (Math.abs(xi-xf)==2 && Math.abs(yi-yf)==2) {
                if (xf-xi==2 && yf-yi==2) {
                    if (pieces[xi+1][yi+1]==null) {
                        return false;
                    }
                    else if (pieces[xi+1][yi+1].side()!=current_player) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }

                else if (xf-xi==-2 && yf-yi==2) {
                    if (pieces[xi-1][yi+1]==null) {
                        return false;
                    }
                    else if (pieces[xi-1][yi+1].side()!=current_player) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }

                else if (xf-xi==2 && yf-yi==-2) {
                    if (pieces[xi+1][yi-1]==null) {
                        return false;
                    }
                    else if (pieces[xi+1][yi-1].side()!=current_player) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }

                else if (xf-xi==-2 && yf-yi==-2) {
                    if (pieces[xi-1][yi-1]==null) {
                        return false;
                    }
                    else if (pieces[xi-1][yi-1].side()!=current_player) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }

                else {
                    return false;
                }
            }

            else {
                return false;
            }
        }

        else if (p.isFire()) {
            if (Math.abs(xi-xf)==1 && (yf-yi)==1 && !has_moved && pieces[xf][yf]==null) {
                has_captured=false;
                return true;
            }

            else if (Math.abs(xi-xf)==2 && (yf-yi)==2) {
                if (xf-xi==2) {
                    if (pieces[xi+1][yi+1]==null) {
                        return false;
                    }
                    else if (pieces[xi+1][yi+1].side()!=current_player) {
                        return true;
                    }

                    else {
                        return false;
                    }
                }
            

                else if (xf-xi==-2) {
                    if (pieces[xi-1][yi+1]==null) {
                        return false;
                    }

                    else if (pieces[xi-1][yi+1].side()!=current_player) {
                        return true;
                    }

                    else {
                        return false;
                    }
                }

                else {
                    return false;
                }
            }

            else {
                return false;
            }
        }

        else {
            if (Math.abs(xi-xf)==1 && (yi-yf)==1 && !has_moved && pieces[xf][yf]==null) {
                has_captured=false;
                return true;
            }

            else if (Math.abs(xi-xf)==2 && (yi-yf)==2) {
                if (xf-xi==2) {
                    if (pieces[xi+1][yi-1]==null) {
                        return false;
                    }

                    else if (pieces[xi+1][yi-1].side()!=current_player) {
                        return true;
                    }

                    else {
                        return false;
                    }
                }

                else if (xf-xi==-2) {
                    if (pieces[xi-1][yi-1]==null) {
                        return false;
                    }

                    else if (pieces[xi-1][yi-1].side()!=current_player) {
                        return true;
                    }

                    else {
                        return false;
                    }
                }

                else {
                    return false;
                }
            }

            else {
                return false;
            }
        }
    }

    /*Returns true if selected location is valid*/
    public boolean canSelect(int x, int y) {
        if (x<0 || x>7 || y<0 || y>7) {
            return false;
        }

        if (bomb_exploded) {
            return false;
        }

        if (!has_moved && pieces[x][y]!=null) {
            if (pieces[x][y].side()==current_player) {
                return true;
            }
            else {
                return false;
            }
        }

        else if (piece_selected && (Math.abs(x-selected[0])==2 || Math.abs(y-selected[0])==2)) {
            return validMove(pieces[selected[0]][selected[1]], selected[0], selected[1], x, y);
        }

        else if (has_moved && pieces[x][y]!=null) {
            return false;
        }

        else if(piece_selected && pieces[x][y]==null) {
            return validMove(pieces[selected[0]][selected[1]], selected[0], selected[1], x, y);
        }

        else if (pieces[x][y]!=null) {
            if (pieces[x][y].side()!=current_player) {
                return false;
            }
            else {
                return true;
            }
        }

        else if (has_moved==true && has_captured==false) {
            return false;
        }

        else if (has_captured==true) {
            if (pieces[x][y]==null) {
                return validMove(pieces[selected[0]][selected[1]], selected[0], selected[1], x, y);
            }
            else {
                return false;
            }
        }

        else if (!piece_selected) {
            if (pieces[x][y]==null) {
                return false;
            }
            else if (current_player!=pieces[x][y].side()) {
                return false;
            }
            else {
                piece_selected=true;
                selected[0]=x;
                selected[1]=y;
                return true;
            }
        }

        else {
            if (pieces[x][y]==null) {
                return true;
            }
            else if (current_player!=pieces[x][y].side()) {
                return false;
            }
            else {
                return true;
            }
        }
    }

    /*Selects a piece or empty square*/
    public void select(int x, int y) {
        if (x<0 || x>7 || y<0 || y>7) {
            return;
        }
        if(pieces[x][y]!=null) {
            if (pieces[x][y].hasCaptured()) {
                has_captured=true;
            }
            if (!first_move) {
                current_player=pieces[x][y].side();
            }
            if (pieces[x][y].side()==current_player) {
                piece_selected=true;
                selected[0]=x;
                selected[1]=y;
            }
            
        }

        else if(pieces[x][y]==null && selected[0]!=9) {
            if (validMove(pieces[selected[0]][selected[1]], selected[0], selected[1], x, y)) {
                if (!first_move) {
                    first_move=true;
                }
                pieces[selected[0]][selected[1]].move(x, y);
            }
            else{
                return;
            }
        }

        else {
            return;
        }

    }
        
    /*Places given piece at given coordinates*/
    public void place(Piece p, int x, int y) {
        countPieces();
        if (p==null) {
            return;
        }
        if (p.hasCaptured()) {
            has_captured=true;
        }
        if (x<0 || x>7 || y<0 || y>7) {
            return;
        }

        if (selected[0]!=9) {
            if (!p.equals(pieces[selected[0]][selected[1]])) {
                selected[0]=9;
                selected[1]=9;
                piece_selected=false;
                return;
            }
        }

        if (!piece_selected) {
            pieces[x][y]=p;
            return;
        }
        
        pieces[x][y]=p;
        images[x][y]=images[selected[0]][selected[1]];
        remove(selected[0], selected[1]);
        if (p.isBomb() && p.hasCaptured()) {
            for (int i=x-1; i<=x+1; i++) {
                for (int j=y-1; j<=y+1; j++) {
                    if (i>=0 && i<=7 && j>=0 && j<=7) {
                        if (pieces[i][j]!=null) {
                            if (!pieces[i][j].isShield()) {
                                remove(i, j);
                            }
                        }
                    }
                }
            }
            selected[0]=9;
            selected[1]=9;
            has_moved=true;
            bomb_exploded=true;
            countPieces();
            return;
        }
        
        if (p.isFire()) {
            if (p.isKing() && y==7) {
                if (!p.isShield() && !p.isBomb()) {
                    images[x][y]="img/pawn-fire-crowned.png";
                }
                else if (p.isShield()) {
                    images[x][y]="img/shield-fire-crowned.png";
                }
                else {
                    images[x][y]="img/bomb-fire-crowned.png";
                }           
            }
        }

        if (!p.isFire()) {
            if (p.isKing() && y==0) {
                if (!p.isShield() && !p.isBomb()) {
                    images[x][y]="img/pawn-water-crowned.png";
                }
                else if (p.isShield()) {
                    images[x][y]="img/shield-water-crowned.png";
                }
                else {
                    images[x][y]="img/bomb-water-crowned.png";
                }                                
            }

        }


        countPieces();
        selected[0]=x;
        selected[1]=y;
        has_moved=true;        
        return;
    }

    /*Removes a piece from the board*/
    public Piece remove(int x, int y) {
        Piece temp2 = pieces[x][y];
        pieces[x][y]=null;
        images[x][y]=null;
        countPieces();
        return temp2;
    }

    /*Checks if turn can be ended*/
    public boolean canEndTurn() {
        if (has_moved==true) {
            return true;
        }
        else {
            return false;
        }
    }

    /*Ends the current turn*/
    public void endTurn() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j]!=null) {
                    pieces[i][j].doneCapturing();
                }
            }
        }
        has_moved=false;
        piece_selected=false;
        has_captured=false;
        selected[0]=9;
        selected[1]=9;
        current_player=1-current_player;
        bomb_exploded=false;
        winner();
    }

    /*Returns the winner if a winner exists*/
    public String winner() {
        countPieces();
        if (fire_pieces<=0 && water_pieces<=0) {
            return "No one";
        }
        else if (fire_pieces>0 && water_pieces<=0) {
            return "Fire";
        }
        else if (fire_pieces<=0 && water_pieces>0) {
            return "Water";
        }
        else {
            return null;
        }
    }

    /*Checks which pieces are still on the board*/
    private void countPieces() {
        int f=0;
        int w=0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j]!=null) {
                    if (pieces[i][j].side()==0) {
                        f+=1;
                    }
                    else {
                        w+=1;
                    }
                }
            }
        }
        fire_pieces=f;
        water_pieces=w;
    }


	public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		Board b = new Board(false);
        b.drawCurrentBoard(N);
		
        while(true) {
            b.drawCurrentBoard(N);
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                if (b.canSelect((int) x, (int) y)) {
                    b.select((int) x, (int) y);
                }
            }

            else if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }            
            StdDrawPlus.show(10);
        }
    }
}