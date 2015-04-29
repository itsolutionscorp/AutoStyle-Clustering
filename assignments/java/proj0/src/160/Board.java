
public class Board{
    private Piece [][] p= new Piece[8][8];
    private Piece rem;
    private Piece selp;
    private boolean isFturn= true; 
    private boolean hasMoved= false;  
    private int i;
    private int j;
    private int selpx;
    private int selpy;

    
	

	public Board(boolean shouldBeEmpty) {

                if (shouldBeEmpty == false){
        
                    for (i = 0; i < 4; i++){
                        p[2*i][0]= new Piece(true, this , 2*i , 0 , "pawn");
                     

                        p[(2*i + 1)][7]= new Piece(false, this ,(2*i + 1 ) , 7 , "pawn");
                     
                            }
                    
                    for (i = 4, j=0 ; i < 8; i++, j++){
                        p[(2*j +1)][1]= new Piece(true, this , (2*j +1) , 1 , "shield");
                     
                        p[2*j][6]= new Piece(false, this , 2*j, 6, "shield");
                       
                    }

                    for (i = 8, j=0 ; i < 12; i++, j++){
                        p[2*j][2]= new Piece(true, this, 2*j , 2 , "bomb");
                       
                        p[2*j +1][5]= new Piece(false, this, (2*j +1), 5 , "bomb");
                    }
                    

                }

            

        
        }


    private boolean validMove (Piece sp, int xi, int yi, int xf, int yf){
    if ((Math.abs(xf-xi)==1 && Math.abs(yf-yi)==1) && selp.hasCaptured()== false){    // If not trying to capture
        if(selp.isKing()){
                
            return true;
        }
        else if(selp.isFire()){    //Its fire piece, not crowned
            if((yf-yi)==1){

                return true;
            }
            else{
                return false;
            }
        }
        else{    //Its water piece, not crowned 
            if((yi-yf)==1){
              
                return true;
            }
            else{
                return false;
            }
        }
    }
    else if(Math.abs(xf-xi)==2 && Math.abs(yf-yi)==2){     // if trying to capture
            if(pieceAt((xf+xi)/2,(yf+yi)/2) != null){
                if(pieceAt((xf+xi)/2,(yf+yi)/2).isFire() != selp.isFire()){   // make sure they are not that same kind.
                    if(selp.isKing()){

                        return true;
                     }
                    else if(selp.isFire()){    //Its fire piece, not crowned
                        if((yf-yi)==2){

                          return true;
                        }
                        else{
                         return false;
                        }
                    }
                    else{    //Its water piece, not crowned 
                        if((yi-yf)==2){

                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                        
                }
                else
                    return false;
            }
            else{    // no piece to capture
                return false;
            }
        }
    
    else{  //Distance is wrong or not diagonal.
        return false;
    }
}




     public boolean canSelect(int x, int y){
      if (p[x][y] != null){
            if(isFturn != p[x][y].isFire()){     // Its not your turn                
                return false;
            }
            else if(selp == null){
                if (hasMoved == false){  // In case it used to be a bomb, not really needed
        
                    return true;
                }
                else {
                    return false;
                }
            }
            else if(hasMoved == false){
                
                return true;
            }
            else{
                return false;
            }

      }
      else{    //p[x][y] == null

            if(selp != null){    // Player has selected piece
                if(hasMoved == false && validMove(selp, selpx, selpy, x, y)== true){
                            
                    return true;
                }
                else{           // Player has selected a piece and moved it
                    if(selp.hasCaptured() != true){        // Moved piece hasnt captured last move
                        return false; 
                    }
                    else if (validMove(selp, selpx, selpy, x, y)){
 
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
            else{   //Player has not selected piece
                return false;
            }
            
      }
    }

    public void select(int x, int y){
            if (selp != null && p[x][y] == null){
                p[x][y]= selp;
                hasMoved= true;
                selp.move(x, y); 
                selpx = x;
                selpy = y;
                
            }
            else if (p[x][y] != null){
                selp= p[x][y];
                selpx = x;
                selpy = y;
            } 
    }


    private void drawBoard(){
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                if (i==selpx && j==selpy){
                    StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }
                else if ((i + j) % 2 == 0){
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                 }
                else 
                {
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                }    

                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
 
                    if (p[i][j]!= null){
                        if (p[i][j].isFire() && p[i][j].isBomb() && p[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crowned.png" , 1, 1);
                            }
                            else if (p[i][j].isFire() && p[i][j].isShield() && p[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crowned.png" , 1, 1);
                            }
                            else if (p[i][j].isFire() && !p[i][j].isBomb() && !p[i][j].isShield() && p[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crowned.png" , 1, 1);
                            }
                            else if (p[i][j].isFire() && p[i][j].isBomb() && !p[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png" , 1, 1);
                            }
                            else if (p[i][j].isFire() && p[i][j].isShield() && !p[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png" , 1, 1);
                            }
                            else if (p[i][j].isFire() && p[i][j].isBomb()== false && p[i][j].isShield() == false && !p[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png" , 1, 1);
                            }
                            else if (!p[i][j].isFire() && p[i][j].isShield() && p[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crowned.png" , 1, 1);
                            }
                            else if (!p[i][j].isFire() && p[i][j].isBomb() && p[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crowned.png" , 1, 1);
                            }
                            else if (!p[i][j].isFire() && !p[i][j].isBomb() && !p[i][j].isShield() && p[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crowned.png" , 1, 1);
                            }
                            else if (!p[i][j].isFire() && p[i][j].isBomb() && !p[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png" , 1, 1);
                            }
                            else if (!p[i][j].isFire() && p[i][j].isShield() && !p[i][j].isKing()){
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png" , 1, 1);
                            }
                            else 
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png" , 1, 1);
                            
                        }


                    

                    

                }
            }
    }            	
                		
                 

   public Piece pieceAt(int x, int y){
        if (x> -1 && x<8 && y> -1 && y<8){
            if(p[x][y] != null){
              return p[x][y];
          }
        }
        return null;
    }


    







    public void place(Piece p, int x, int y) {
        if(p != null){
            if(x>=0 && x<=7 && y>=0 && y<=7){
             this.p[x][y]= p;
            }
         }
    }

    public Piece remove(int x, int y){
        if(x>=0 && x<=7 && y>=0 && y<=7){
            if(p[x][y] != null){
                rem = p[x][y];
                p[x][y]= null;
                return rem;
            }
            else{
                System.out.println("p[x][y] = null so it cannot be removed.");
                return null;
            }
        }
        else{
            System.out.println("The input (x, y) to the remove function is out of bounds.");
            return null;
        }
        
    }

    public boolean canEndTurn(){
        return hasMoved;
    }
    
    public void endTurn(){
        isFturn= !isFturn;
        selp.doneCapturing();
        selp= null;
        hasMoved= false;

    }

    public String winner(){
        int sumF= 0;
        int sumW= 0;
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                if (p[i][j] != null)
                 {
                    if(p[i][j].isFire()){
                        sumF += 1;
                    }
                    else{
                      sumW +=1;
                     }
                }
            }
        }
        if (sumF == 0 && sumW == 0){
            return "No one";
        }
        else if (sumF == 0 && sumW > 0){
            return "Water";
        }
        else if (sumF > 0 && sumW == 0){
            return "Fire";
        }
        else
            return null;
    }

    private void startGame(){
        while (this.winner()== null){
            drawBoard();
            if (StdDrawPlus.mousePressed()) {
                double dx = StdDrawPlus.mouseX();
                double dy = StdDrawPlus.mouseY();
                int x= (int)dx;
                int y= (int)dy;
                if  (this.canSelect(x, y)){
                     this.select(x, y);
                }
            }
            if (StdDrawPlus.isSpacePressed()){
                if (this.canEndTurn()){
                    this.endTurn();
                }
            }
            StdDrawPlus.show(10);
            
        }
    }
    
    public static void main(String[] args) {
 
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        boolean testmode= false;
        int i,j;
        Board b;
       
            b = new Board(testmode);  
            b.startGame();

 
    }

}