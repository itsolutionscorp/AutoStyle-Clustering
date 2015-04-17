public class Board{
    private int N = 8;
	private	boolean[][] bool_pieces = new boolean[N][N];
	private	Piece[][] object_pieces = new Piece[N][N];
    private int turn_player = 0;
    private boolean has_selected = false, moved = false;
    // private boolean captured = false;
    private int prev_x, prev_y;
    private boolean correct_piece = false;
    private boolean draw_white = false;
    public static void main(String[] args) {
        Board newBoard = new Board(false);
        while(true) {
                if (newBoard.winner() != null){
                    System.out.println(newBoard.winner());
                    break;
                }
                if (StdDrawPlus.mousePressed()) {
                    int x = (int) StdDrawPlus.mouseX();
                    int y = (int) StdDrawPlus.mouseY();
                    if (newBoard.canSelect(x,y)){
                        newBoard.select(x,y);
                    }

                }    
                if (StdDrawPlus.isSpacePressed()){
                    // System.out.println("space pressed");
                    if (newBoard.canEndTurn()){
                        newBoard.endTurn();
                    }
                }    
                StdDrawPlus.show(100);
            }
    }
    public Board(boolean shouldBeEmpty){        
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);

        if (!shouldBeEmpty){
            default_board(this);  
        }
        drawBoard(N);
    }   

	
	
	public Piece pieceAt(int x, int y){
        if (x >= N || x < 0 || y >= N || y<0){
            return null;
        }
        else if (bool_pieces[x][y]){
            return object_pieces[x][y];
        }
        else{
            return null;
        }

	}

    //place/CheckArray helper variable
    private int xx, yy;

    //place_helper function
    private boolean CheckArray(Piece p){
        for (int i = 0; i < N; i+=1){
            for (int j = 0; j < N; j+=1)
            if (object_pieces[i][j] == p
                && bool_pieces[i][j] == true){
                xx = i;
                yy = j;
                return true;
            }   
        }
        return false;
    }


    public void place(Piece p, int x, int y){
        if (x < N && x >= 0 && 
            y < N && y >= 0 && p != null){
            if (CheckArray(p)){

                remove(xx,yy);
            }
            bool_pieces[x][y] = true;
            object_pieces[x][y] = p;
        }

    }  
   

	public boolean canSelect(int x, int y){
        //square with piece
        correct_piece = false;
        if (bool_pieces[x][y]){
            boolean isfire = pieceAt(x,y).isFire();
            if ((isfire && turn_player == 0) || (!isfire && turn_player == 1)){
                correct_piece = true;
            }
            // System.out.print("Selected correct_piece: ");
            // System.out.println(correct_piece);
            if(correct_piece && (!has_selected || (has_selected && !moved))){
                return true;
            }
            else{
                return false;
            }
        }
        // empty square
        else{
            if (has_selected && !moved
            && validMove(prev_x, prev_y, x, y)){                
                return true;
            }
            else if (has_selected && object_pieces[prev_x][prev_y].hasCaptured()
            && validMove(prev_x, prev_y, x, y) && !object_pieces[prev_x][prev_y].isBomb()){
                return true;
            
            }
            else{
                return false;
            }

        }
	}


    private boolean validMove(int x_a, int y_a, int x_b, int y_b){
        Piece holding_piece = object_pieces[x_a][y_a];
        boolean king = holding_piece.isKing();
        boolean isfire = holding_piece.isFire();
        int space_moved_x = Math.abs(x_b - x_a);
        int left_OR_right = x_b - x_a;
        int space_moved_y = y_b - y_a;
      
        // checks if no piece is at (x_b, y_b)
        if (!bool_pieces[x_b][y_b]){
            // **NORMAL MOVEMENT
            // if player 0 moved diagonaly moved 1 space up
            boolean captured = object_pieces[x_a][y_a].hasCaptured();
            if (isfire && space_moved_x == 1 && space_moved_y == 1 && !captured
                ){
                return true;
            }
            // if player 1 moved diagonaly moved 1 space down
            else if (!isfire && space_moved_x == 1 && space_moved_y == -1  && !captured
                ){
                return true;
            }

            //**BACKWARD MOVEMENT
            // if player 0 diagonally moved 1 space down and is king
            else if (isfire && space_moved_x == 1 && space_moved_y == -1  && !captured
            && king){
                return true;
            }

            // if player 1 diagonaly moved 1 space up and is king
            else if (!isfire && space_moved_x == 1 && space_moved_y == 1  && !captured
            && king){
                return true;
            }


            //**FOWARD CAPTURING
            // if player 0 diagonally moved 2 space up
            else if (isfire && space_moved_x == 2 && space_moved_y == 2){
                int captured_x = x_a + left_OR_right/2;
                int captured_y = y_a + 1;

                //if piece between jump exists
                if (bool_pieces[captured_x][captured_y]){
                    return true;
                }
                else{
                    return false;
                }
            }

              // if player 1 diagonally moved 2 space down
            else if (!isfire && space_moved_x == 2 && space_moved_y == -2){
                int captured_x = x_a + left_OR_right/2;
                int captured_y = y_a - 1;

                //if piece between jump exists
                if (bool_pieces[captured_x][captured_y]){
                    return true;
                }
                else{
                    // System.out.print("invalid at: (");
                    // System.out.print(captured_x);
                    // System.out.print(", ");
                    // System.out.print(captured_y);
                    // System.out.println(")");
                    return false;
                }
            }


            //**BACKWARDS CAPTURING
            // if player 0 diagonally moved 2 space down and is king
            else if (isfire && space_moved_x == 2 && space_moved_y == -2
            && king){
                int captured_x = x_a + left_OR_right/2;
                int captured_y = y_a - 1;
                //if piece between jump exists
                if (bool_pieces[captured_x][captured_y]){
                    return true;
                }
                else{
                    return false;
                }
            }
            // if player 1 diagonally moved 2 space up and is king
            else if (!isfire && space_moved_x == 2 && space_moved_y == 2
            && king){
                int captured_x = x_a + left_OR_right /2;
                int captured_y = y_a + 1;
                //if piece between jump exists
                if (bool_pieces[captured_x][captured_y]){
                    return true;
                }
                else{
                    return false;
                }
            }
            //INVALID MOVE
            else {
                // System.out.println("INVALID");
                // System.out.println(space_moved_y);
                return false;
            }
        }
        // if piece exist at (x_b, y_b) return false
        // because you cannot move there
        else {
            return false;
        }
    }

	public void select(int x, int y){

        has_selected = true;
        draw_white = true;
        // System.out.print("correct_piece: ");
        // System.out.println(correct_piece);
        Piece selected = pieceAt(x,y);
        String side, type;

        //if select a piece

        if (!bool_pieces[x][y]){

            Piece selected_p = object_pieces[prev_x][prev_y];
            selected_p.move(x,y);
            // System.out.print("Moved to: (");
            // System.out.print(x);
            // System.out.print(", ");
            // System.out.print(y);
            // System.out.println(")");
            moved = true;
            if (selected_p.hasCaptured()){

                // captured = true;            
                if (selected_p.isBomb()){
                    bool_pieces[x][y] = false;
                    int temp_x = x + 1, temp_y = y + 1;
                    int change = -2;
                    for (int i = 0; i < 4; i+= 1){



                        if (temp_y < N && temp_y > 0 && temp_x < N && temp_x > 0){
                            boolean adj_piece = bool_pieces[temp_x][temp_y];
                            // System.out.print("check: (");
                            // System.out.print(temp_x);
                            // System.out.print(", ");
                            // System.out.print(temp_y);  
                            // System.out.println(")"); 
                            // System.out.println(adj_piece);  
                            if (adj_piece && !pieceAt(temp_x, temp_y).isShield()){
                                bool_pieces[temp_x][temp_y] = false;
                            }
                        }           

                        if (i % 2 ==0){
                            temp_y += change;
    
                        }   
                        else {
                            temp_x += change;
                            change = 2;
                        }

                    }
                }
            }
            
        }
        prev_x = x;
        prev_y = y;
        drawBoard(N);
        draw_white = false;

	}

	public Piece remove(int x, int y){

        String stringX = Integer.toString(x);
        String stringY = Integer.toString(y);
        if (x >= N || x < 0 || y >= N || y<0){
            System.out.println("Out of bound error at (" +
                stringX + (", ") + stringY +").");
            return null;
        }
        else if(bool_pieces[x][y] == false){
            System.out.println("There is not piece at (" 
                + stringX + (", ") + stringY +").");
            return null;
        }
        else{
            bool_pieces[x][y] = false;
            return object_pieces[x][y];

        }
	}

	public boolean canEndTurn(){
        //
        if (moved || object_pieces[prev_x][prev_y].hasCaptured()){
            return true;
        }
        else{
            return false;
        }     
	}

	public void endTurn(){

        // System.out.println("TURN END");
        // System.out.println("__________________");
        turn_player = Math.abs(turn_player - 1);
        // System.out.print("PLAYER ");
        // System.out.print(turn_player);
        // System.out.println("'s turn");
        moved = false;
        // captured = false;
        object_pieces[prev_x][prev_y].doneCapturing();
        has_selected = false;
        drawBoard(N);
	}

	public String winner(){
        int fire_count = 0;
        int water_count = 0;
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Piece current = object_pieces[i][j];
                if (bool_pieces[i][j]){
                    if (current.isFire()){
                        fire_count += 1;
                    }
                    else{
                        water_count += 1;
                    }
                }
            }
        } 

        if (fire_count == 0 && water_count ==0){
            return "No one";
        }       

        else if (water_count == 0){
            // System.out.println(fire_count);
            return "Fire";
        }
        else if (fire_count == 0){
            return "Water";
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

                if (bool_pieces[i][j]) {
                    Piece curr = object_pieces[i][j];
                    String type = "", side = "", rank = "";
                    boolean isfire = curr.isFire();
                    boolean isking = curr.isKing();
                    if (draw_white && i == prev_x && j == prev_y){
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                    }

                    if (isking){
                        rank = "-crowned";
                    }
                
                    if (isfire){
                        side = "fire";
                    }
                    else{
                        side = "water";
                    }
                    if (curr.isShield()){
                        type = "shield";
                    }
                    else if (curr.isBomb()){
                        type = "bomb";
                    }
                    else{
                        type = "pawn";
                    }

                    StdDrawPlus.picture(i + .5, j + .5, "img/" + type + "-" 
                        + side + rank + ".png", 1, 1);
                }
            }
        }
    }

 
    private void default_board(Board b){
         for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0 && j !=3  && j!=4){
                    String img_type ="", type ="";
                    boolean isfire = true;

                    if (j == 0){
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        type = "pawn";
                        img_type = "img/pawn-fire.png";
                    }
                    else if (j == 1){
                        type = "sheild";
                        img_type = "img/shield-fire.png";
          
                    }
                    else if (j == 2){
                        type = "bomb";
                        img_type = "img/bomb-fire.png";
                        
                    }
                    else if (j == 7){
                        StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        type = "pawn";
                        img_type = "img/pawn-water.png";
                        isfire = false;
                    }
                    else if (j == 6){
                        StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        type = "shield";
                        img_type = "img/shield-water.png";
                        isfire = false;
                    }
                    else if (j == 5){
                        StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        type = "bomb";
                        img_type = "img/bomb-water.png";
                        isfire = false;
                    }   
                    StdDrawPlus.picture(i + .5, j + .5, img_type, 1, 1);
                    object_pieces[i][j] = new Piece(isfire, b, i, j, type);
                    bool_pieces[i][j] = true;

                }
            }
        }
    }


}