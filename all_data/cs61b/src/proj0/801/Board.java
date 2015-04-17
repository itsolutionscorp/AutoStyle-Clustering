public class Board {

	private Piece[][] pieces= new Piece[8][8];
	private boolean shouldBeEmpty;
	private boolean player = true;
	private int fireteam= 12;
	private int waterteam= 12;

	private Piece selected=null;
	private int xselect;
	private int yselect;
	private boolean moved;
	private boolean loopB=true;


	public static void main(String[] args){
		Board one = new Board(false);
		int xmouse;
		int ymouse;
		
		while(true){ 
			if (one.checkwinner()==true) {
				System.out.print(one.winner());
				return;
			}
			if(StdDrawPlus.mousePressed()) {
				xmouse=(int)StdDrawPlus.mouseX();
				ymouse=(int)StdDrawPlus.mouseY();
				if (one.canSelect(xmouse,ymouse)){
					one.highlight(xmouse,ymouse);
					one.select(xmouse,ymouse);
				}
			}
			if(StdDrawPlus.isSpacePressed()) {
				if(one.canEndTurn()) {
					one.endTurn();
				}
			}

			one.reDrawBoard();
			StdDrawPlus.show(10);

		}
		

	}

	private boolean checkwinner(){
		if ((fireteam<=0)||(waterteam<=0)){
			return true;
		}else{
			return false;
		}
	}

	public Board(boolean shouldBeEmpty){
		this.shouldBeEmpty=shouldBeEmpty;
		drawBoard();
	}

	private void highlight(int x, int y) {
		StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		StdDrawPlus.filledSquare(x+.5,y+.5,.5);
		this.drawPiece(x,y);	
	}


	private void reDrawBoard() {
		int N = 8;
		
		
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                this.drawPiece(i,j);
            }
        }
    }


    public void select(int x, int y){
		highlight(x,y);
    	System.out.println("selected");
		Piece temp = pieceAt(x,y);
		if ((selected!=null)&&(selected==temp)){
		}
		else if (((selected!=null)&& (temp==null))&&((moved==false)||(selected.hasCaptured()))) {
			moved=true;
			selected.move(x,y); 
			xselect=x;
			yselect=y;
			if((temp!=null) && (temp.hasCaptured())) {
				loopB=false;
			}
		}
		selected=pieceAt(x,y);
		if(selected!=null){
			xselect=x;
			yselect=y;
		}
	}

	public boolean canSelect( int x, int y) { //Finish
		Piece temp = pieceAt(x,y);
		if (((x>7)||(y>7))||((x<0)||(y<0))) {
			return false;
		}
		//Square with piece
		if (loopB){
		if ((temp!=null) && (player==temp.isFire())) {
			if ((selected==null) ^ ((selected!=null)&& (moved==false))) {
				return true;   
			}
		}
		
		if(temp==null) {
			if (((selected!=null)&&(moved==false)) && 
			((validMove(xselect,yselect,x,y))^(validKill(xselect,yselect,x,y)))) {
				return true;
			}
		}  } // Only allows captures

		if(temp==null) {	
			if (((selected!=null)&&(selected.hasCaptured())) && (validKill(xselect,yselect,x,y))) {
				return true;
			}

		}

		return false;
	}

	private boolean validMove(int px, int py,int mx, int my){
		Piece temp = pieceAt(px,py);
		if (temp.isFire()!=player){
			return false;
		}
		if ((pieceAt(mx,my))==null){
			//Fire
			if ((temp.isFire())||(temp.isKing())) {
				if (((Math.abs(px-mx))==1) && ((my-py)==1)){
					return true;
				}
			}
			//Water
			if((!(temp.isFire()))||(temp.isKing())) {
				if (((Math.abs(px-mx))==1) && ((py-my)==1)) {
					return true;
				}
			}
		}
		return false;
	}


	private boolean validKill(int px, int py, int mx, int my){
		Piece temp = pieceAt(px,py);
		int minus=-1;
		if ((px-mx)<0){
			minus=1;
		}
		if ((pieceAt(mx,my))==null) {
			//Fire
			if ((temp.isFire())||(temp.isKing())) {
				if(((Math.abs(px-mx))==2) && ((my-py)==2)) {
					if( (pieceAt((px+minus),(py+1)))!=null ) {
						if ( (pieceAt((px+minus),(py+1)).isFire())!=(temp.isFire())) {
							return true;
						}
					}
				}	
			}
			//Water
			if((!(temp.isFire()))||(temp.isKing())) {
				if(((Math.abs(px-mx))==2) && ((py-my)==2)) {
					if( (pieceAt((px+minus),(py-1)))!=null ) {
						if ( (pieceAt( (px+minus) ,(py-1)).isFire())!=(temp.isFire())) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}



	public Piece pieceAt(int x, int y){
		if ((x>7)||(y>7)){
			return null;
		} 
		if ((x<0)||(y<0)){
			return null;
		}
		return this.pieces[x][y];


	}

	public void place(Piece p, int x , int y) {

		if (((x>7)||(y>7))||(p==null)){
		}
		else if((x<0)||(y<0)){
		}
		else{
			System.out.println("Calling place");
			if(p.isFire()){
				fireteam=fireteam+1;
			}
			else{
				waterteam=waterteam+1;
			}
		

		this.pieces[x][y]=p;
		}

	

	}
	public Piece remove(int x, int y){
		if (((x>7)||(y>7))||((x<0)||(y<0))) {
			System.out.println("Target location is out of bounds.");
			return null;
		}
		if (this.pieces[x][y]==null){
			System.out.println("There is no piece at the target location.");
			return null;
		}

		System.out.println("Calling remove");

		Piece temp= this.pieces[x][y];
		if(temp.isFire()){
			fireteam=fireteam-1;
		}
		if(!(temp.isFire())){
			waterteam=waterteam-1;
		}
		pieces[x][y]=null;
		return temp;
	}

	public boolean canEndTurn(){
		if (moved==true){
			return true;
		}
		else{
			return false;
		}

	}

	public void endTurn(){
		player = (!(player));
		moved=false;
		loopB=true;
		if (selected!=null){
			selected.doneCapturing();
		}
		selected=null;
		xselect=-40;
		yselect=-40;


	}

	public String winner(){
		if ( (fireteam<=0) && (waterteam<=0)){
			return "No one";
		}
		else if (fireteam<=0){
			return "Water";
		}
		else if(waterteam<=0){
			return "Fire";
		}
		else{
			return null;
		}	
	}




    private void drawPiece(int x, int y) {
		Piece temp = pieces[x][y];
		if (temp!=null){
            String crown="";
			String type="";
			String side="water";
		
			if (temp.isFire()){
				side="fire";
			}
			if (temp.isKing()){
				crown= "-crowned";
			}
			if (temp.isBomb()){
				type="bomb";
			}
			else if (temp.isShield()) {
				type="shield";
			} 
			else {
				type="pawn";
			}
			StdDrawPlus.picture(x+.5,y+.5, "img/" +type+ "-" + side + crown + ".png",1,1);
		}

	}

	private void drawBoard() {
		int N = 8;
		

		StdDrawPlus.setCanvasSize();
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (((i + j) % 2 == 0) && (!(shouldBeEmpty))){
                	if(j==0){
                		pieces[i][j]= new Piece(true,this,i,j,"Pawn");
                		StdDrawPlus.picture(i+.5,j+.5, "img/pawn-fire.png",1,1);
                	}
                	if(j==1){
                		pieces[i][j]= new Piece(true,this,i,j,"Shield");
                		StdDrawPlus.picture(i+.5,j+.5, "img/shield-fire.png",1,1);
                	}
                	if(j==2){
                		pieces[i][j]= new Piece(true,this,i,j,"Bomb");
                		StdDrawPlus.picture(i+.5,j+.5, "img/bomb-fire.png",1,1);
                	}
                	if(j==5){
                		pieces[i][j]= new Piece(false,this,i,j,"Bomb");
                		StdDrawPlus.picture(i+.5,j+.5, "img/bomb-water.png",1,1);
                	}
                	if(j==6){
                		pieces[i][j]= new Piece(false,this,i,j,"Shield");
                		StdDrawPlus.picture(i+.5,j+.5, "img/shield-water.png",1,1);

                	}
                	if(j==7){
                		pieces[i][j]= new Piece(false,this,i,j,"Pawn");
                		StdDrawPlus.picture(i+.5,j+.5, "img/pawn-water.png",1,1);
                	}
                }
            }
        }

    }
	
}