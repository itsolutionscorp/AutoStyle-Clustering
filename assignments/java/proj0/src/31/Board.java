/** Sorry for all the code but this was all i could think of, hard project*/

public class Board
{
    /** Location of pieces. */
    private Piece [][] piece;
    private boolean empty_board;
    private boolean fire_turn = true;
    private boolean has_selected = false;
    private boolean made_a_move = false;
    private boolean made_capture = false;
    private Piece selected_piece;
    private int counter = 1;
    private int x_initial;
    private int y_initial;

    /** Draws an N x N board. Adapted from:
        http://introcs.cs.princeton.edu/java/15inout/CheckerBoard.java.html
     */

    private void drawBoard(int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else                  StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if(selected_piece != null)
                {
                    if (piece[i][j] == selected_piece)
                    {
                        StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                        StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                    }
                }
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
                StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                if (piece[i][j] != null) {
                    if (!piece[i][j].isShield() && !piece[i][j].isBomb())
                    {
                        if (piece[i][j].isFire())
                        {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire.png", 1, 1);
                        }
                        else
                        {
                            StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water.png", 1, 1);
                        }

                    }
                    else if (piece[i][j].isBomb())
                    {
                        if (piece[i][j].isFire())
                        {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire.png", 1, 1);
                        }                   
                        else        
                        {
                            StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water.png", 1, 1);
                        }
                    }
                    else if (piece[i][j].isShield())
                    {
                        if (piece[i][j].isFire())
                        {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire.png", 1, 1);
                        }
                        else
                        {
                            StdDrawPlus.picture(i + .5, j + .5, "img/shield-water.png", 1, 1);
                        }
                    }
                    else if (piece[i][j].isKing())
                    {
                        if (piece[i][j].isFire())
                        {
                            if (piece[i][j].isShield())
                            {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-fire-crown.png", 1, 1);
                            }
                            else if (piece[i][j].isBomb())
                            {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-fire-crown.png", 1, 1);
                            }
                            else
                            {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-fire-crown.png", 1, 1);
                            }
                        }
                        else
                        {
                            if (piece[i][j].isShield())
                            {
                                StdDrawPlus.picture(i + .5, j + .5, "img/shield-water-crown.png", 1, 1);
                            }
                            else if (piece[i][j].isBomb())
                            {
                                StdDrawPlus.picture(i + .5, j + .5, "img/bomb-water-crown.png", 1, 1);
                            }
                            else
                            {
                                StdDrawPlus.picture(i + .5, j + .5, "img/pawn-water-crown.png", 1, 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int N = 8;
        StdDrawPlus.setXscale(0, N);
        StdDrawPlus.setYscale(0, N);
        Board b = new Board(false);
        int counter = 0;
        int only_one = 0;

        while(true)
        {
            b.drawBoard(N);
            if(b.empty_board)
            {

            }
            else
            {   
                if (StdDrawPlus.mousePressed()) 
                {
                    double xi = StdDrawPlus.mouseX();
                    double yi = StdDrawPlus.mouseY();
                    int x_temp = (int) xi;
                    int y_temp = (int) yi;
                    if(b.canSelect((int)xi, (int)yi))
                    {
                        b.select(x_temp, y_temp);
                    }
                }  
                if(StdDrawPlus.isSpacePressed())
                {
                    if(b.canEndTurn())
                    {
                        b.endTurn();
                    }
                }
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if(b.piece[i][j] == null)
                            counter += 1;
                        else
                            only_one += 1;
                    }
                }
                if(counter == 64 || only_one == 1)
                    b.winner();
            }
            StdDrawPlus.show(100); 
        }
    }


    public Board (boolean shouldBeEmpty)
    {
        empty_board = shouldBeEmpty;
        if (empty_board)
        {
            piece = new Piece [8][8];
        }
        else{
        piece = new Piece [8][8];
        for (int x = 0; x < 8; x += 2) 
        {
            int y = 0;
            piece [x][y] = new Piece (true, this, x, y, "pawn");
        }

        for (int x = 1; x < 8; x += 2)
        {
            int y = 1;
            piece [x][y] = new Piece (true, this, x, y, "shield");
        }

        for (int x = 0; x < 8; x += 2) 
        {
            int y = 2;
            piece [x][y] = new Piece (true, this, x, y, "bomb");
        }

        for (int x = 1; x < 8; x += 2)
        {
            int y = 7;
            piece [x][y] = new Piece (false, this, x, y, "pawn");
        }

        for (int x = 0; x < 8; x += 2)
        {
            int y = 6;
            piece [x][y] = new Piece (false, this, x, y, "shield");
        }

        for (int x = 1; x < 8; x += 2)
        {
            int y = 5;
            piece [x][y] = new Piece (false, this, x, y, "bomb");
        }}
    }

    public Piece pieceAt(int x, int y)
    {
        if(x >= 0 && y >= 0 && x < 8 && y < 8)
        {
            if(piece[x][y] == null)
            {
                return null;
            }
            else
            {
                return piece[x][y];
            }
        }
        else
        {
            return null;
        }
    }

    public boolean canSelect(int x, int y)
    {   
        if (x >= 0 && y >= 0 && x < 8 && y < 8)
        {
            if (fire_turn)
            {
                if (piece[x][y] != null && piece[x][y].isFire())
                {
                    return (!has_selected || (has_selected && !made_a_move));
                }
                else if (piece[x][y] == null)
                {
                    if (has_selected && !made_a_move && validMove(x_initial, y_initial, x, y))
                    {
                        return true;
                    }
                    else if(this.pieceAt(x_initial, y_initial) == null)
                    {
                        return false;
                    }
                    else if (has_selected && made_a_move && !this.pieceAt(x_initial, y_initial).hasCaptured())
                    {
                        return false;
                    }                    
                    else if (has_selected && this.pieceAt(x_initial, y_initial).hasCaptured() && validMove(x_initial, y_initial, x, y))
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
            else
            {        
                if (piece[x][y] != null && !piece[x][y].isFire())
                {
                    return (!has_selected || (has_selected && !made_a_move));
                }
                else if (piece[x][y] == null)
                {
                    if (has_selected && !made_a_move && validMove(x_initial, y_initial, x, y))
                    {
                        return true;
                    }
                    else if(this.pieceAt(x_initial, y_initial) == null)
                    {
                        return false;
                    }
                    else if (has_selected && made_a_move && !this.pieceAt(x_initial, y_initial).hasCaptured())
                    {
                        return false;
                    }
                    else if (has_selected && this.pieceAt(x_initial, y_initial).hasCaptured() && validMove(x_initial, y_initial, x, y))
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }

    private boolean validMove(int xi, int yi, int xf, int yf)
    {
        if (xf >= 0 && yf >= 0 && xf < 8 && yf < 8)
        {
            if (this.pieceAt(xi, yi) == null)
            {
                return false;
            }
            else if(piece[xi][yi].isFire())
            {
                if(this.pieceAt(xi, yi).isKing())
                {   // checks if its a valid move for the king and that square is null
                    if(   ((xf==xi+1 && yf==yi+1) || (xf==xi-1 && yf==yi+1) 
                        || (xf==xi+1 && yf==yi-1) || (xf==xi-1 && yf==yi-1)) && piece[xf][yf] == null)
                    {
                        return true;
                    }
                    else if(   ((xf==xi+2 && yf==yi+2 && piece[xf-1][yf-1] != null && !piece[xf-1][yf-1].isFire()) 
                             || (xf==xi-2 && yf==yi+2 && piece[xf+1][yf-1] != null && !piece[xf+1][yf-1].isFire())
                             || (xf==xi+2 && yf==yi-2 && piece[xf-1][yf+1] != null && !piece[xf-1][yf+1].isFire()) 
                             || (xf==xi-2 && yf==yi-2 && piece[xf+1][yf+1] != null && !piece[xf+1][yf+1].isFire())) )
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    if((   ((xf==xi+1) && (yf==yi+1) && (piece[xi+1][yi+1] == null)) 
                        || ((xf==xi-1) && (yf==yi+1) && (piece[xi-1][yi+1] == null))  ))
                    {
                        return true;
                    }
                    else if (this.pieceAt(xi+1,yi+1) != null)
                    {
                        // checks if theres a piece there for it to capture
                        if ((  ((xf==xi+2) && (yf==yi+2) && (piece[xi+1][yi+1] != null) && !piece[xi+1][yi+1].isFire())  ) 
                            || ((xf==xi-2) && (yf==yi+2) && (piece[xi-1][yi+1] != null) && !piece[xi-1][yi+1].isFire())  )
                        {
                            made_capture = true;
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
            }
            else // for the water pieces, same code as above
            {

                if(this.pieceAt(xi, yi).isKing())
                {   // checks if its a valid move for the king and that square is null
                    if(   ((xf==xi+1 && yf==yi+1) || (xf==xi-1 && yf==yi+1) 
                        || (xf==xi+1 && yf==yi-1) || (xf==xi-1 && yf==yi-1)) && piece[xf][yf] == null)
                    {
                        return true;
                    }
                    else if(   ((xf==xi+2 && yf==yi+2 && piece[xf-1][yf-1] != null && piece[xf-1][yf-1].isFire()) 
                             || (xf==xi-2 && yf==yi+2 && piece[xf+1][yf-1] != null && piece[xf+1][yf-1].isFire())
                             || (xf==xi+2 && yf==yi-2 && piece[xf-1][yf+1] != null && piece[xf-1][yf+1].isFire()) 
                             || (xf==xi-2 && yf==yi-2 && piece[xf+1][yf+1] != null && piece[xf+1][yf+1].isFire())) )
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
                    if ((   ((xf==xi-1) && (yf==yi-1) && (piece[xi-1][yi-1] == null)) 
                        ||  ((xf==xi+1) && (yf==yi-1) && (piece[xi+1][yi-1] == null))  ))
                    {
                        return true;
                    }
                    else if (this.pieceAt(xf, yf) == null)
                    {
                        if ((  ((xf==xi-2) && (yf==yi-2) && (piece[xf-1][yf-1] != null)) 
                            || ((xf==xi+2) && (yf==yi-2) && (piece[xf+1][yf-1] != null))  ))
                        {
                            made_capture = true;
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
            }
        }
        else
        {
            return false;
        }
    }

    public void select(int x, int y)
    {
        if(pieceAt(x, y) != null)
        {
            selected_piece = piece[x][y];
            has_selected = true;
            x_initial = x;
            y_initial = y;
        }
        else
        {
            piece[x_initial][y_initial].move(x, y);
            if(validMove(x_initial, y_initial, x, y))
            {
                made_a_move = false;
            }
            else
            {
                made_a_move = true;
                x_initial = x;
                y_initial = y;
            }
        }
    }

    public void place(Piece p, int x, int y)
    {
        if(x>7 || x<0 || y<0 || y >7 || p == null)
        {
        }
        else
        {
            piece[x][y] = p;
        }
    }

    public Piece remove(int x, int y)
    {
        if(x>7 || x<0 || y<0 || y>7)
        {
            System.out.println("These coordinates are out of bounds.");
            return null;
        }
        else if (piece[x][y] == null)
        {
            System.out.println("There's no piece here.");
            return null;
        }
        else
        {
            Piece removed_piece = piece[x][y];
            piece[x][y] = null;
            return removed_piece;
        }
    }

    public boolean canEndTurn()
    {
        if(made_a_move || made_capture)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void endTurn()
    {
        if (counter % 2 != 0)
        {
            fire_turn = false;
        }
        else
        {
            fire_turn = true;
        }
        selected_piece.doneCapturing();
        counter += 1;
        made_a_move = false;
        has_selected = false;
        selected_piece = null;
        made_capture = false;
    }

    public String winner()
    {
        int count = 0;
        int one_piece = 0;
        Piece winner = null;
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 8; j++) 
            {
                if(piece[i][j] == null)
                    count += 1;
                else
                {
                    one_piece += 1;   
                    winner = piece[i][j];
                }
            }
        }
        if (count == 64)
        {
            return ("No one");
        }
        else if (one_piece == 1)
        {
            if (winner != null && winner.isFire())
            {
                return ("Fire");
            }
            else
            {
                return ("Water");
            }
        }
        else
        {
            return null;
        }
    }
}