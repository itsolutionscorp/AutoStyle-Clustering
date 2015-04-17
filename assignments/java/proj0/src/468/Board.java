public class Board {
    private boolean[][] location_pieces;
    private Piece[][] pieces;
    private boolean fire_turn, moved;
    private Piece selected_piece;
    private int num_f, num_w;
    private int [] xy;
    private boolean white_box;
    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (i==xy[0] && j==xy[1]){
                    if (white_box){
                        StdDrawPlus.filledSquare(xy[0] + .5, xy[1] + .5, .5); 
                    }
                }
                if (location_pieces[i][j]) {
                    Piece p= pieces[i][j];
                    if (p.isFire()){
                        if (p.isBomb()){
                            if(p.isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png", 1, 1); 
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1); 
                            }
                        }
                        else if (p.isShield()){
                            if(p.isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png", 1, 1); 
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                            }
                        }
                        else{
                            if(p.isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png", 1, 1); 
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);                            
                            }
                        }
                    }
                    else{
                        if (p.isBomb()){
                            if(p.isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png", 1, 1); 
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);    
                            }
                        }
                        else if (p.isShield()){
                            if(p.isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png", 1, 1); 
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                            }
                        }
                        else{
                            if(p.isKing()){
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png", 1, 1); 
                            }
                            else{
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);                            
                            }
                        }
                    }
                }
            }
        }
     
    }

    public Board(boolean shouldBeEmpty){       
        location_pieces = new boolean[8][8];
        pieces= new Piece[8][8];
        fire_turn= true;
        moved=false;
        selected_piece= null;
        xy= new int[2];
        white_box=false;
        num_f=0;
        num_w=0;
        if(!shouldBeEmpty){
	        int x=0;
	        int y=0;
	        for(int i=0; i<4; i+=1){
	            place(new Piece(true,this,x,y,"pawn"), x,y);
	            x+=2;
	        }
	        x=1;
	        y=1;
	        for(int i=0; i<4; i+=1){
	            place(new Piece(true,this,x,y,"shield"), x,y);
	            x+=2;     
	        }
	        x=0;
	        y=2;
	        for(int i=0; i<4; i+=1){
	            place(new Piece(true,this,x,y,"bomb"), x,y);  
	            x+=2;     
	        }
	        x=1;
	        y=5;
	        for(int i=0; i<4; i+=1){
	            place(new Piece(false,this,x,y,"bomb"),x,y);
	            
	            x+=2;     
	        }
	        x=0;
	        y=6;
	        for(int i=0; i<4; i+=1){
	            place(new Piece(false,this,x,y,"shield"),x,y);
	            x+=2;      
	        }
	        x=1;
	        y=7;
	        for(int i=0; i<4; i+=1){
	            place(new Piece(false,this,x,y,"pawn"),x,y);
	            x+=2;     
	        }
        }
    }
    
    public Piece pieceAt(int x, int y){
        if (x>= 8 || y>=8 || x<0 || y <0){
            return null;
        }
        else if (!location_pieces[x][y]){
            return null;
        }
        return pieces[x][y];
    }
    
    public void place(Piece p, int x, int y){
        if(x >= 8|| y >=8 || p== null || x<0 || y<0){
            return;
        }
        location_pieces[x][y]=true;
        pieces[x][y]=p;
        if (p.isFire()){
        	num_f+=1;
        }
        else{
        	num_w+=1;
        }
        
    }
    public Piece remove(int x, int y){
        if (x>=8|| y >=8 || x<0 || y<0){
            System.out.println("Out of bounds, can't remove");
            return null;
        }
        else if(pieces[x][y]==null){
            System.out.println("No piece to remove");
            return null;
        }
        else{
            Piece p= pieces[x][y];
            location_pieces[x][y]=false;
            pieces[x][y]=null;
            if (p.isFire()){
            	num_f-=1;
            }
            else{
            	num_w-=1;
            }
            return p;
        }
    }
    
    public boolean canSelect(int x, int y){
    	if (x>=8|| y >=8 || x<0 || y<0){
    		return false;
    	}
        if (location_pieces[x][y]){
            if (!moved && pieces[x][y].isFire()==fire_turn){
                return true;
            }
        }
        else{
            if(!moved && selected_piece!=null){
                if(validMove(xy[0],xy[1], x,y)){
                    return true;
                }
            }
            if(moved && selected_piece!=null 
                    && selected_piece.hasCaptured()){
                if(validMove(xy[0],xy[1], x,y)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public void select(int x, int y){
    	if (pieceAt(x,y)!=null){
            selected_piece=pieces[x][y];
            xy[0]=x;
            xy[1]=y;
        }
        else {
        	moved=true;
            selected_piece.move(x, y);
            xy[0]=x;
            xy[1]=y; 
        }
    }
    
    public boolean canEndTurn(){
        return moved;
    }
    
    public void endTurn(){
        moved=false;
        if(selected_piece!=null){
        	selected_piece.doneCapturing();
        }
        selected_piece= null;
        white_box=false;       
        if (fire_turn){
            fire_turn=false;
        }
        else{
            fire_turn=true;
        }
    }
    
    public String winner(){
//    	int num_f=0;
//        int num_w=0;
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                    if (location_pieces[i][j]) {
//                        Piece p= pieces[i][j];
//                        if(p.isFire()){
//                            num_f+=1;
//                        }
//                        else{
//                            num_w+=1;
//                        }
//                    }
//                    
//                }
//            }
        if(num_f==0 && num_w!=0){
            return "Water";
        }
        else if(num_w==0 && num_f!=0){
            return "Fire";
        }
        else if(num_f==0 && num_w==0){
            return "No one";
        }
        return null;
    }
    
    private boolean validMove(int xi, int yi, int xf, int yf){
        if(location_pieces[xf][yf]){
            return false;
        }
        Piece p= pieces[xi][yi];
        if(p.isKing()){
            if(!moved){
                if((xi+1== xf || xi-1==xf) && (yi-1==yf||yi+1==yf)){
                    return true;
                }
                return (validWater2(xi,yi,xf,yf)||validFire2(xi,yi,xf,yf));
            }
            return (validWater2(xi,yi,xf,yf)||validFire2(xi,yi,xf,yf));
            
        }
        else if(!p.isFire()){
            if(!moved){
                if((xi+1== xf || xi-1==xf) && yi-1==yf){
                    return true;
                }
                return validWater2(xi,yi,xf,yf);
            }
            return validWater2(xi,yi,xf,yf);
            
        }
        else {
            if(!moved){
                if((xi+1== xf || xi-1==xf) && yi+1==yf){
                    return true;
                }
                return validFire2(xi,yi,xf,yf);
            }
            return validFire2(xi,yi,xf,yf);
        }
    }
    private boolean validFire2(int xi, int yi, int xf, int yf){
        Piece p= pieces[xi][yi];
        if(xi+2==xf && yi+2==yf){
            if(location_pieces[xi+1][yi+1] 
                  && p.isFire()!=pieces[xi+1][yi+1].isFire()){
              return true;
            }
            return false;
        }
        if(xi-2==xf && yi+2==yf){
            if(location_pieces[xi-1][yi+1] 
                  && p.isFire()!=pieces[xi-1][yi+1].isFire()){
              return true;
            }
            return false;
        }
        return false;
    }
    
    private boolean validWater2(int xi, int yi, int xf, int yf){
        Piece p= pieces[xi][yi];
        if(xi+2==xf && yi-2==yf){
            if(location_pieces[xi+1][yi-1] 
                  && p.isFire()!=pieces[xi+1][yi-1].isFire()){
              return true;
            }
            return false;
        }
        if(xi-2==xf && yi-2==yf){
            if(location_pieces[xi-1][yi-1] 
                  && p.isFire()!=pieces[xi-1][yi-1].isFire()){
              return true;
            }
            return false;
        }
        return false;
    }
    
    public static void main(String[] args) {
        Board board = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        board.drawBoard();
        while(board.winner()==null){
        	if(StdDrawPlus.mousePressed()){
                double mx = StdDrawPlus.mouseX();
                double my = StdDrawPlus.mouseY();
                if((int)mx<8 && (int)my<8){
                	if(!board.moved || 
                			(!board.selected_piece.isBomb() && board.selected_piece.hasCaptured())){
    	                if (board.canSelect((int)mx, (int)my) ){
    	                    board.select((int)mx,(int)my);
    	                    board.white_box=true;
    	                }
                	}
                }
                	
            }
            board.drawBoard();
            StdDrawPlus.show(10);
            if(StdDrawPlus.isSpacePressed()){
                if(board.canEndTurn()){
                    board.endTurn();
                }
            }
            
        } 
    }
}