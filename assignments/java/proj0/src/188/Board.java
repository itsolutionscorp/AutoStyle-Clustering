public class Board{
	private Piece[][] pieces;
	private boolean isFireTurn, movedYet;
	private int[]  initial;
	private static final int SIZE = 8;
	
	public Board(boolean shouldBeEmpty){
        pieces = new Piece[SIZE][SIZE];
        isFireTurn = true;
		if(!shouldBeEmpty)
			setPieces();
	}
	private void setPieces(){
		for(int i=0;i<SIZE;i++){
        	if(i%2 == 0){
        		pieces[i][0] = new Piece(true,this,i,0,"pawn");
        		pieces[i][2] = new Piece(true,this,i,2,"bomb");
        		pieces[i][6] = new Piece(false,this,i,6,"shield");
        	}
        	else{
        		pieces[i][1] = new Piece(true,this,i,1,"shield");
        		pieces[i][5] = new Piece(false,this,i,5,"bomb");
        		pieces[i][7] = new Piece(false,this,i,7,"pawn");
        	}
        }
	}
	public Piece pieceAt(int x, int y){
		if(oob(x,y))
			return null;
		return pieces[x][y];
	}
	public void select(int x, int y){
        if(pieceAt(x,y) != null)
        	initial = new int[]{x,y};
        else{
        	int a = initial[0];
        	int c = initial[1];
        	initial = new int[]{x,y};
        	pieceAt(a,c).move(x,y);
        	movedYet = true;
        }
	}
	private boolean oob(int x, int y){
		return x < 0 || y < 0 || x >= SIZE || y >= SIZE;
	}
	public boolean canSelect(int x, int y){
		if(oob(x,y))
			return false;
		if(!movedYet){
			if(initial == null){
				if(pieceAt(x,y) == null)
					return false;
				return isFireTurn == pieceAt(x,y).isFire();
			}
			if(pieceAt(x,y) != null && isFireTurn == pieceAt(x,y).isFire())
				return true;
			if(canMove(x,y).compareTo("cant") != 0)
				return true;
		}
		else if(pieceAt(initial[0],initial[1]) != null && canMove(x,y).compareTo("capture") == 0 && pieceAt(initial[0],initial[1]).hasCaptured())
			return true;
		return false;
	}
	private String canMove(int x, int y){
		if(pieceAt(x,y) == null){
			if(Math.abs(x-initial[0]) == 1 && (Math.abs(y-initial[1]) == 1) && forwardsBackwards(y-initial[1]))
				return "normal";
			if(Math.abs(x-initial[0]) == 2 && (Math.abs(y-initial[1]) == 2) && forwardsBackwards(y-initial[1])){
				int tmpX = (x+initial[0])/2;
				int tmpY = (y+initial[1])/2;
				if(pieceAt(tmpX,tmpY) == null)
					return "cant";
				if(isFireTurn != pieceAt(tmpX,tmpY).isFire())
					return "capture";
			}
		}
		return "cant";
	}
	private boolean forwardsBackwards(int diff){
		if(pieceAt(initial[0],initial[1]).isKing())
			return true;
		if(pieceAt(initial[0],initial[1]).isFire())
			return diff>0;
		return diff<0;
	}
	private static String url(Piece p){
		String tmp = "img/";
		if(p.isBomb())
			tmp += "bomb";
		else if(p.isShield())
			tmp += "shield";
		else
			tmp += "pawn";
		if(p.isFire())
			tmp += "-fire";
		else
			tmp += "-water";
		if(p.isKing())
			tmp += "-crowned";
		return tmp+".png";
	}
	public void place(Piece p, int x, int y){
		if(!oob(x,y))
			pieces[x][y] = p;
	}
	private static void drawPiece(Piece p, int x, int y){
		StdDrawPlus.picture(x + .5, y + .5, url(p), 1, 1);
	}
	public Piece remove(int x, int y){
		if(oob(x,y)){
			System.out.println("Out of Bounds");
			return null;
		}
		Piece tmp = pieceAt(x,y);
		if(tmp == null)
			System.out.println("No piece to remove");
		pieces[x][y] = null;
		return tmp;
	}
	public boolean canEndTurn(){
		return movedYet;
	}
	public void endTurn(){
		isFireTurn = !isFireTurn;
		if(initial != null && pieceAt(initial[0],initial[1]) != null)
			pieceAt(initial[0],initial[1]).doneCapturing();
		initial = null;
		movedYet = false;
	}
	public String winner(){
		boolean waterExists = false;
		boolean fireExists = false;
		for(int i=0;i<SIZE;i++){
			for(int j=0;j<SIZE;j++){
				if(pieceAt(i,j) != null){
					if(pieceAt(i,j).isFire())
						fireExists = true;
					else
						waterExists = true;
				}
			}
		}
		if(!waterExists && !fireExists)
			return "No one";
		if(!waterExists)
			return "Fire";
		if(!fireExists)
			return "Water";
		return null;
	}
	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
                if(initial != null && i == initial[0] && j == initial [1])
                	StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (pieces[i][j] != null)
                    drawPiece(pieces[i][j],i,j);
            }
        }
    }

    public static void main(String[] args) {
    	Board b = new Board(false);
    	StdDrawPlus.setXscale(0, SIZE);
        StdDrawPlus.setYscale(0, SIZE);
		while(true) {
            if (StdDrawPlus.mousePressed()) {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if(b.canSelect(x,y))
	                b.select(x, y);
            }
            if(StdDrawPlus.isSpacePressed() && b.canEndTurn())
            	b.endTurn();
			b.drawBoard(SIZE);
            StdDrawPlus.show(25);
            if(b.winner() != null){
            	System.out.println(b.winner()+" wins");   
				b.drawBoard(SIZE);  
				b.endTurn();    
				b.drawBoard(SIZE);
	            StdDrawPlus.show(25); 
            	return;
            }
        }
    }
}//test comment