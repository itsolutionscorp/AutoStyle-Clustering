//Final
public class Board {
	private Piece[][] pieces;
    private boolean empty;
    private String turn;
    private boolean selected;
    private Piece selected_piece;
    private int selected_coordinates_x;
    private int selected_coordinates_y;
    private boolean moved;
	public static void main(String[] args) {
        Board b = new Board(false);
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        boolean over = b.winner() != null;
        while(!over) {
            b.drawBoard(N);   
            if (StdDrawPlus.mousePressed()) {
                double x = StdDrawPlus.mouseX();
                double y = StdDrawPlus.mouseY();
                int cast_x = (int)x;
                int cast_y = (int)y;
                if(b.canSelect(cast_x,cast_y)) {
                    b.select(cast_x,cast_y);
                }  
            }
            else if (StdDrawPlus.isSpacePressed()) {
                if (b.canEndTurn()) {
                    b.endTurn();
                }
            }     
            over = b.winner() != null;
            StdDrawPlus.show(30);
        }
        b.drawBoard(N);
        StdDrawPlus.show(30);
        System.out.println(b.winner());
	}
    public Board(boolean shouldBeEmpty) {
        empty = shouldBeEmpty;
        turn = "fire";
        selected_coordinates_x = -1;
        selected_coordinates_y = -1;
        int N = 8;
        pieces = new Piece[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(!empty) {
                    if (j==0 && i%2==0) {
                        pieces[i][j] = new Piece(true,this,i,j,"pawn");
                    }
                    else if (j==7 && i%2==1) {
                        pieces[i][j] = new Piece(false,this,i,j,"pawn");
                    }
                    else if (j==2 && i%2==0) {
                        pieces[i][j] = new Piece(true,this,i,j,"bomb");
                    }
                    else if (j==5 && i%2==1) {
                        pieces[i][j] = new Piece(false,this,i,j,"bomb");
                    }
                    else if (j==1 && i%2==1) {
                        pieces[i][j] = new Piece(true,this,i,j,"shield");
                    }
                    else if (j==6 && i%2==0) {
                        pieces[i][j] = new Piece(false,this,i,j,"shield");
                    }
                    else {
                        pieces[i][j] = null;
                    }
                }
            }
        }
    }
	public Piece pieceAt(int x, int y) {
		if (x > 7 || x < 0 || y > 7 || y < 0) {
			return null;
		}
		return pieces[x][y];
	}
    private boolean validCapture(int xi, int yi, int xf, int yf) {
        int[] holder = {xi,yi,xf,yf};
        for(int x : holder) {
            if(x<0 || x>7) {
                return false;
            }
        }
        boolean distance = ((Math.abs(xi-xf)==2) && (Math.abs(yi-yf)==2));
        boolean occupied = pieceAt(xf,yf)!= null;
        if (distance && !occupied) {
            int x_coord = (int)(xi+xf)/2;
            int y_coord = (int)(yi+yf)/2;
            boolean capture = (pieceAt(x_coord,y_coord) != null) && (pieceAt(x_coord,y_coord).isFire() != pieceAt(xi,yi).isFire());
            if (pieceAt(xi,yi).isKing()) {
                if (capture) {
                    return true;
                }
                return false;
            }
            else if(capture) {
                if (pieceAt(xi,yi).isFire() && yf>yi) {
                    return true;
                }
                else if(!pieceAt(xi,yi).isFire() && yf<yi) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    private boolean validOne(int xi, int yi, int xf, int yf) {
        int[] holder = {xi,yi,xf,yf};
        for(int x : holder) {
            if(x<0 || x>7) {
                return false;
            }
        }
        boolean distance = ((Math.abs(xi-xf)==1) && (Math.abs(yi-yf)==1));
        boolean occupied = pieceAt(xf,yf) != null;
        distance = ((Math.abs(xi-xf)==1) && (Math.abs(yi-yf)==1));
        if (distance && !occupied) {
            if (pieceAt(xi,yi).isKing()) {
                return true;
            }
            else {
                if (pieceAt(xi,yi).isFire() && yf>yi) {
                    return true;
                }
                else if(!pieceAt(xi,yi).isFire() && yf<yi) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    private boolean validMove(int xi, int yi, int xf, int yf) {
        return validOne(xi,yi,xf,yf) || validCapture(xi,yi,xf,yf);
    }
    public boolean canSelect(int x, int y) {
        if (x<0 || x>7 || y<0 || y>7) {
            return false;
        }
        else if (pieceAt(x,y) != null) {
            if (!selected || !moved) {
                if(turn.equals("fire")) {
                    return pieceAt(x,y).isFire();
                }
                return !pieceAt(x,y).isFire();
            }
        }
        else {
            if (selected && !moved && validMove(selected_coordinates_x,selected_coordinates_y,x,y)) {
                return true;
            }
            else if(selected && moved && selected_piece != null) {
                if(pieceAt(selected_coordinates_x,selected_coordinates_y).isFire()) {
                    if (selected_piece.hasCaptured() && validCapture(selected_coordinates_x,selected_coordinates_y,x,y)) {
                        return true;
                    }
                }
                else {
                    if (selected_piece.hasCaptured() && validCapture(selected_coordinates_x,selected_coordinates_y,x,y)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }
    public void select(int x, int y) {
        boolean checker = false;
        if (pieceAt(x,y) == null) {
            if(Math.abs(selected_coordinates_x-x)==2) {
                if(selected_piece.isBomb()) {
                    checker = true;
                }
            }
            pieceAt(selected_coordinates_x,selected_coordinates_y).move(x,y);
            moved = true;
        }
        selected = true;
        selected_piece = pieceAt(x,y);
        selected_coordinates_x = x;
        selected_coordinates_y = y;
        if (checker) {
            endTurn();
        }
    }
	public void place(Piece p, int x, int y) {
		if (!(x > 7 || x < 0 || y > 7 || y < 0)) {
			pieces[x][y] = p;
		}
	}
    public Piece remove(int x, int y) {
        if (pieceAt(x,y) != null) {
            String type = "";
            if (pieceAt(x,y).isShield()) {
                type = "shield";
            }
            else if (pieceAt(x,y).isBomb()) {
                type = "bomb";
            }
            else {
                type = "pawn";
            }
            Piece holder_piece = new Piece(pieceAt(x,y).isFire(),this,x,y,type);
            pieces[x][y] = null;
            return holder_piece;
        }
        else {
            System.out.println("Invalid remove.");
        }
        return null;
    }
    public boolean canEndTurn() {
        return moved;
    }
    public void endTurn() {
        if (selected_piece != null) {
            selected_piece.doneCapturing();
        }
        selected = false;
        selected_piece = null;
        moved = false;
        selected_coordinates_x = -1;
        selected_coordinates_y = -1;
        if (turn.equals("fire")) {
            turn = "water";
        }
        else {
            turn = "fire";
        }
    }
    public String winner() {
        int fire_count = 0;
        int water_count = 0;
        for(int x=0;x<8;x++) {
            for(int y=0;y<8;y++) {
                if (pieceAt(x,y)!=null) {
                    if(pieceAt(x,y).isFire()) {
                        fire_count ++;
                    }
                    else {
                        water_count ++;
                    }
                }
            }
        }
        if ((water_count == 0) && (fire_count==0)) {
            return "No one";
        }
        else if(water_count ==0) {
            return "Fire";
        }
        else if(fire_count == 0) {
            return "Water";
        }
        return null;

    }
	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if((i == selected_coordinates_x) && (j == selected_coordinates_y)) {
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                else if ((i + j) % 2 == 0) {
                	StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                }
                else {
                	StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                Piece helper = pieceAt(i,j);
                StringBuilder output = new StringBuilder("img/");
                if(helper != null) {
                    if(helper.isBomb()) {
                        output.append("bomb-");
                    }
                    else if(helper.isShield()) {
                        output.append("shield-");
                    }
                    else {
                        output.append("pawn-");
                    }
                    if(helper.isFire()) {
                        output.append("fire");
                    }
                    else {
                        output.append("water");
                    }
                    if(helper.isKing()) {
                        output.append("-crowned");
                    }
                    StdDrawPlus.picture(i + .5, j + .5, output.append(".png").toString(), 1, 1);
                }
            }
        }
    }
}