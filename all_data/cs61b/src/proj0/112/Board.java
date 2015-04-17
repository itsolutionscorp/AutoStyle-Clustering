
//final
public class Board {
	private Piece[][] piecelst = new Piece[8][8];
	private int currentx;
	private int currenty;
	private Piece current;
	private boolean moved = false;
	private int side = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board gen = new Board(false);
		StdDrawPlus.setXscale(0,8);
		StdDrawPlus.setYscale(0,8);
		while(gen.winner()==null){
			gen.drawBoard();
		if (StdDrawPlus.mousePressed()) {
                int x = (int)StdDrawPlus.mouseX();
                int y = (int)StdDrawPlus.mouseY();
                if (gen.canSelect(x,y)){
                	gen.select(x,y);
                }
            }
            if (gen.canEndTurn() && StdDrawPlus.isSpacePressed()){
            	gen.endTurn();
            }
            StdDrawPlus.show(100);
        }
        gen.drawBoard();
}




		
		

	private void initialize(){
		for (int i=0; i<8; i+=2){
			this.piecelst[i][0] = new Piece(true, this, i, 0, "pawn");
			this.piecelst[(i+1)][1] = new Piece(true,this,i+1, 1,"shield");
			this.piecelst[i][2]= new Piece(true, this, i , 2 ,"bomb");
			this.piecelst[i+1][7] = new Piece(false, this, i+1, 7, "pawn");
			this.piecelst[(i)][6] = new Piece(false,this,i, 6,"shield");
			this.piecelst[i+1][5]= new Piece(false, this, i+1 , 5 ,"bomb");
			
		}
	}
	public Board(boolean shouldBeEmpty){
		if (shouldBeEmpty){
			for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
         StdDrawPlus.filledSquare(i + .5, j + .5, .5);
         }
     }
			
		}
		else {
			initialize();
		}
	}
	public Piece pieceAt(int x, int y){
		if (x>7 || y>7 || x<0 || y<0){
			return null;
		}
		return this.piecelst[x][y];
	}
	public boolean canSelect(int x, int y){
		if (x>7 || y>7 || x<0 || y<0){
			return false;
		}
		Piece player = pieceAt(x,y);
		if (this.current == null){
			if(player!=null){
				if(player.side() == this.side){
					return true;
				}
			}
			return false;
		}
		if (player == null){
			if (((this.current.hasCaptured())||(!this.moved))){
				if ((validMove(this.currentx,this.currenty,x,y))){
					return true;
				}

			}
			return false;
		}
		else{
			if(!this.moved){
<<<<<<< HEAD
				if(player.side()==this.side){
=======
				if (player.side() ==  this.side){
>>>>>>> master
					return true;
				}
			}
		return false;
	}
		
		
		
	}
	private boolean validMove(int x, int y, int xf, int yf){
		if ((xf>7 || yf>7 || xf<0 || yf<0) || pieceAt(xf,yf)!=null){
			return false;
		}
		Piece player = pieceAt(x,y);
		boolean checkking = player.isKing();
		int xrange = Math.abs(xf-x);
		int yrange2 = yf-y;
		int yrange = Math.abs(yrange2);
		if ((xrange==1)&&(!player.hasCaptured())){
			if (checkking){
				if (yrange ==1){
					return true;
				}
				return false;
			}
			if (player.isFire()){
				if (yrange2 ==1){
					return true;
				}
				return false;
			}
			else{
				if (yrange2 == -1){
					return true;
				}
				return false;
			}
		}
		if (xrange==2){
			if (yrange!=2){
				return false;
			}
			int midx = (x+xf)/2;
			int midy = (y+yf)/2;
			Piece player2 = pieceAt(midx,midy);
			if (player2==null || player2.side() == player.side()){
				return false;
			}
			else if(checkking){
				return true;
			}
			if (player.isFire()){
				if (yrange2 ==2){
					return true;
				}
				return false;
			}
			else{
				if (yrange2 ==-2){
					return true;
				}
				return false;
			}
		}
		return false;
		
	}
	public void select(int x, int y){
		if (pieceAt(x,y)==null)
		{
			this.current.move(x, y);
			this.moved = true;
		}
		this.current = pieceAt(x,y);
		this.currentx = x;
		this.currenty = y;
	}
	public void place(Piece p, int x, int y){
		this.piecelst[x][y] = p;
		
	}
	public Piece remove(int x, int y){
		if (x>7 || y>7 || x<0 || y<0){
			System.out.println("The place selected is invalid.");
			return null;
		}
		Piece removedp = this.piecelst[x][y];
		if (removedp == null){
			System.out.println("The place selected has no piece");
			return null;
		}
		this.piecelst[x][y] = null;
		return removedp;
		
	}
	public boolean canEndTurn(){
		return this.moved;
	}
	public void endTurn(){
		this.moved = false;
		if(this.side ==0){
			this.side = 1;
		}
		else{
			this.side = 0;
		}
		if (this.current!=null){
			this.current.doneCapturing();
			this.current = null;
		}
	}
	public String winner(){
		
	int countfire =0;
	int countwater = 0;
		for (int i = 0; i<8; i++){
			for (int j=0; j<8; j++){
				if (piecelst[i][j]!=null){
					if (piecelst[i][j].isFire() == true){
						countfire++;
					}
					else{
						countwater++;
					}
				}
			}
		}
		if (countfire == 0 && countwater==0){
			return "No one";
		}
		else if (countfire == 0 && countwater!=0){
			return "Water";
		}
		else if (countwater == 0 && countfire!=0){
			return "Fire";
		}
		return null;
		
	}
	private String imgpath(int x, int y){
		String path = "img"+ "/"+checktype(piecelst[x][y]);
		if (piecelst[x][y].isFire()){
			path = path + "-fire";
		}
		else {
			path = path + "-water";
		}
		if (piecelst[x][y].isKing()){
			path = path + "-crowned";
			
		}
		path = path+".png";
		return path;
	}
	
	private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
         StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                if (piecelst[i][j]!=null) {
                    StdDrawPlus.picture(i + .5, j + .5, imgpath(i,j), 1, 1);
                }
            }
        }
    }

	private String checktype (Piece p){
		if (p.isBomb()){
			return "bomb";
		}
		else if (p.isShield()){
			return "shield";
		}
		else {
			return "pawn";
		} 
	}
	

}
