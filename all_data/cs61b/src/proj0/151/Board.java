
public class Board{
	private Piece [][] pieces;
	private Piece selected;
	private int selectx;
	private int selecty;
	private int turn; //1 for water, -1 for fire cuz technical reasons
	private boolean hasgone;
	public static void main(String[] args){
		int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
		Board B=new Board(false);
        while(true) {
            B.drawBoard(N);
			//get user input
            if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
				if(B.canSelect(x,y)){
					B.select(x,y);
				}
            }
			if(StdDrawPlus.isSpacePressed()&&B.canEndTurn()){
				B.endTurn();
			}
            StdDrawPlus.show(100);
        }
	}

	
	public Board(boolean shouldBeEmpty){
		pieces=new Piece[8][8];
		selectx=-1;
		selecty=-1;
		if(shouldBeEmpty){
		}
		else{
			for(int i=0;i<12;i++){
				pieces[2*(i%4)+((int)(i/4))%2][(int)(i/4)]=new Piece(true,this,2*(i%4)+((int)(i/4))%2,(int)(i/4),pieceName(i));
			}
			for(int i=12;i<24;i++){
				pieces[2*(i%4)+((int)(i/4))%2][(int)(i/4)+2]=new Piece(false,this,2*(i%4)+((int)(i/4))%2,(int)(i/4)+2,pieceName(i));
			}
		}
		turn=-1;
		hasgone=false;
	}
	
	private String pieceName(int i){
		if(i<4||i>19){
			return "pawn";
		}
		else if(i<8||i>15){
			return "shield";
		}
		else{
			return "bomb";
		}
	}
	
	public Piece pieceAt(int x, int y){
		if(-1<x&&8>x&&-1<x&&8>y){
			return pieces[x][y];
		}
		return null;
	}
	
	public boolean canSelect(int x, int y){
		if(-1<x&&8>x&&-1<x&&8>y){
			if(pieceAt(x,y)!=null&&pieceAt(x,y).side()*2-1==turn&&!hasgone){
				return true;
			}
			else if(validMove(selectx,selecty,x,y)&&selected!=null&&(!hasgone||selected.hasCaptured())){
				return true;
			}
		}
		return false;
	}
	
	private boolean validMove(int xi, int yi, int xf, int yf){
		//basically checks if distance is one and in the right direction/isking, or if distance is 2, in the right direction, or isking, and also that there is an opposing piece to hop over. sorry for really long expression :(
		if(Math.abs(xf-xi)==1&&yf-yi==-turn&&pieceAt(xf,yf)==null&&!selected.hasCaptured()){// if its just a standard move and doesn't collide with shit
			return true;
		}
		else if (Math.abs(xf-xi)==2&&yf-yi==-2*turn&&pieceAt((xi+xf)/2,(yi+yf)/2)!=null&&pieceAt((xi+xf)/2,(yi+yf)/2).side()!=pieceAt(xi,yi).side()&&pieceAt(xf,yf)==null){//if its a jumpy thingy over an enemy
			return true;
		}
		else if(selected!=null&&selected.isKing()){
			if(Math.abs(xf-xi)==1&&Math.abs(yf-yi)==1&&pieceAt(xf,yf)==null&&!selected.hasCaptured()){// if its just a standard move and doesn't collide with shit
				return true;
			}
			else if (Math.abs(xf-xi)==2&&Math.abs(yf-yi)==2&&pieceAt((xi+xf)/2,(yi+yf)/2)!=null&&pieceAt((xi+xf)/2,(yi+yf)/2).side()!=pieceAt(xi,yi).side()&&pieceAt(xf,yf)==null){//if its a jumpy thingy over an enemy
				return true;
			}
		}
		return false;
	}
	
	public void select(int x, int y){
		if(-1<x&&x<8&&-1<y&&y<8){
			if(pieceAt(x,y)!=null){
				selected=pieceAt(x,y);
				selectx=x;
				selecty=y;
			}
			else{
				if(selected!=null){
					selected.move(x,y);
					hasgone=true;
				}
			}
		}
	}
	
	public void place(Piece P, int x, int y){
		if(-1<x&&x<8&&-1<y&&y<8){
			pieces[x][y]=P;
			if(P==selected){
				selectx=x;
				selecty=y;
			}
		}
	}
	
	public Piece remove(int x, int y){
		if(-1<x&&x<8&&-1<y&&y<8){
			Piece r=pieces[x][y];
			pieces[x][y]=null;
			return r;
		}
		return null;
	}
	
	public boolean canEndTurn(){
		return hasgone;
	}
	
	public void endTurn(){
		hasgone=false;
		selected.doneCapturing();
		selected=null;
		selectx=-1;
		selecty=-1;
		turn*=-1;
	}
	
	public String winner(){
		int firesum=0;
		int watersum=0;
		for(Piece[] p: pieces){
			for(Piece q:p){
				if (q!=null){
					watersum+=q.side();
					firesum+=1-q.side();
				}
			}
		}
		if(firesum==0){
			if(watersum==0){
				return "No One";
			}
			return "Water";
		}
		else if (watersum==0){
			return "Fire";
		}
		else{
			return null;
		}
	}
	
	private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(selectx+.5,selecty+.5,.5);
		for (int x=0;x<pieces.length;x++) {
			for (int y=0;y<pieces.length;y++){
				Piece p=pieces[x][y];
				String picurl="img/";
				if(p!=null){
					if(p.isBomb()){
						picurl=picurl.concat("bomb");
					}
					else if(p.isShield()){
						picurl=picurl.concat("shield");
					}
					else{
						picurl=picurl.concat("pawn");
					}
					if(p.isFire()){
						picurl=picurl.concat("-fire");
					}
					else{
						picurl=picurl.concat("-water");
					}
					if(p.isKing()){
						picurl=picurl.concat("-crowned");
					}
					StdDrawPlus.picture(x + .5, y + .5, picurl.concat(".png"), 1, 1);
				}
			}
        }
    }
}