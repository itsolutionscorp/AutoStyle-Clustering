/**
 * Created by ajaykrishnan on 2/9/2015.
 */
public class Board
{
    private Piece[][] pieces;
    private boolean shouldBeEmpty;
    private int turn;
    private Piece selected;
    private boolean moved;
    private boolean captured;

    /** turn = 0 denotes fire's turn, turn = 1 denotes water's turn */

    public static void main(String[] args)
    {
        Board board = new Board(false);
        StdDrawPlus.setXscale(0, 8);
        StdDrawPlus.setYscale(0, 8);
        if (board.shouldBeEmpty)
        {
            board.drawEmpty();
        }
        else
        {
            board.drawBoard();
        }
        while (board.winner() == null)
        {
            if (StdDrawPlus.mousePressed())
            {
                int x = (int) StdDrawPlus.mouseX();
                int y = (int) StdDrawPlus.mouseY();
                if (board.canSelect(x, y))
                    board.select(x, y);
            }
            else if (StdDrawPlus.isSpacePressed())
            {
                if (board.canEndTurn())
                    board.endTurn();
            }
        }
    }

    public Board(boolean shouldBeEmpty)
    {
        this.shouldBeEmpty = shouldBeEmpty;
        pieces = new Piece[8][8];
        if (!shouldBeEmpty)
        {
            for (int i = 0; i < 8; i += 2)
            {
                pieces[i][0] = new Piece(true, this, i, 0, "pawn");
                pieces[i][6] = new Piece(false, this, i, 6, "shield");
                pieces[i][2] = new Piece(true, this, i, 2, "bomb");
            }
            for (int i = 1; i < 8; i += 2)
            {
                pieces[i][7] = new Piece(false, this, i, 7, "pawn");
                pieces[i][1] = new Piece(true, this, i, 1, "shield");
                pieces[i][5] = new Piece(false, this, i, 5, "bomb");
            }
        }
        turn = 0;
        selected = null;
        moved = false;
        captured = false;
    }

    private void drawEmpty()
    {
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if ((i + j) % 2 == 0)
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
    }

    private void drawBoard()
    {
        /** Draw blank board */
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if ((i + j) % 2 == 0)
                    StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else
                    StdDrawPlus.setPenColor(StdDrawPlus.RED);
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
            }
        }
        for (int i = 0; i < 8; i += 2)
        {
            StdDrawPlus.picture(i + .5, .5, "img/pawn-fire.png", 1, 1);
            StdDrawPlus.picture(i + .5, 6.5, "img/shield-water.png", 1, 1);
            StdDrawPlus.picture(i + .5, 2.5, "img/bomb-fire.png", 1, 1);
        }
        for (int i = 1; i < 8; i += 2)
        {
            StdDrawPlus.picture(i + .5, 7.5, "img/pawn-water.png", 1, 1);
            StdDrawPlus.picture(i + .5, 1.5, "img/shield-fire.png", 1, 1);
            StdDrawPlus.picture(i + .5, 5.5, "img/bomb-water.png", 1, 1);
        }
    }

    public Piece pieceAt(int x, int y)
    {
        if (x < 0 || x > 7 || y < 0 || y > 7 || pieces[x][y] == null)
            return null;
        else
            return pieces[x][y];
    }

    public boolean canSelect(int x, int y)
    {
        if (x < 0 || x > 7 || y < 0 || y > 7)
            return false;
        else
        {
            if (pieces[x][y] != null)
            {
                if (selected == null || !moved)
                {
                    if (pieces[x][y].isFire() && turn == 0)
                        return true;
                    else if (!pieces[x][y].isFire() && turn == 1)
                        return true;
                    else
                        return false;
                }
                else
                    return false;
            }
            else
            {
                if (selected != null && !moved && validMove(xposition(coordinates(selected)), yposition(coordinates(selected)), x, y))
                    return true;
                else if (selected != null && captured && validMove(xposition(coordinates(selected)), yposition(coordinates(selected)), x, y))
                    return true;
                else
                    return false;
            }
        }
    }

    private int[] coordinates(Piece p)
    {
        int[] temp = new int[2];
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (p == pieces[i][j])
                {
                    temp[0] = i;
                    temp[1] = j;
                    return temp;
                }
            }
        }
        return null;
    }

    private int xposition(int[] pair)
    {
        return pair[0];
    }

    private int yposition(int[] pair)
    {
        return pair[1];
    }

    private boolean validMove(int xi, int yi, int xf, int yf)
    {
        if (xf < 0 || xf > 7 || yf < 0 || yf > 7)
            return false;
        else
        {
            if (pieces[xi][yi].isFire())
            {
                if (pieces[xi][yi].isKing())
                {
                    if (Math.abs(yf - yi) == 1 && Math.abs(xf - xi) == 1 && pieces[xf][yf] == null && !captured)
                    {
                        return true;
                    }
                    else if ((Math.abs(yf - yi) == 2 && Math.abs(xf - xi) == 2))
                    {
                        if (yf < yi && xf < xi && pieces[xf+1][yf+1] != null && !pieces[xf+1][yf+1].isFire() && pieces[xf][yf] == null)
                            return true;
                        else if (yf < yi && xf > xi && pieces[xf-1][yf+1] != null && !pieces[xf-1][yf+1].isFire() && pieces[xf][yf] == null)
                            return true;
                        else if (yf > yi && xf > xi && pieces[xf-1][yf-1] != null && !pieces[xf-1][yf-1].isFire() && pieces[xf][yf] == null)
                            return true;
                        else if (yf > yi && xf < xi && pieces[xf+1][yf-1] != null && !pieces[xf+1][yf-1].isFire() && pieces[xf][yf] == null)
                            return true;
                        else
                            return false;
                    }
                    else
                        return false;
                }
                else
                {
                    if (yf - yi == 1 && Math.abs(xf - xi) == 1 && pieces[xf][yf] == null && !captured)
                    {
                        return true;
                    }
                    else if (yf - yi == 2 && Math.abs(xf - xi) == 2)
                    {
                        if (yf > yi && xf < xi && pieces[xf+1][yf-1] != null && !pieces[xf+1][yf-1].isFire() && pieces[xf][yf] == null)
                            return true;
                        else if (yf > yi && xf > xi && pieces[xf-1][yf-1] != null && !pieces[xf-1][yf-1].isFire() && pieces[xf][yf] == null)
                            return true;
                        else
                            return false;
                    }
                    else
                        return false;
                }
            }
            else
            {
                if (pieces[xi][yi].isKing())
                {
                    if (Math.abs(yf - yi) == 1 && Math.abs(xf - xi) == 1 && pieces[xf][yf] == null && !captured)
                    {
                        return true;
                    }
                    else if ((Math.abs(yf - yi) == 2 && Math.abs(xf - xi) == 2))
                    {
                        if (yf < yi && xf < xi && pieces[xf+1][yf+1] != null && pieces[xf+1][yf+1].isFire() && pieces[xf][yf] == null)
                            return true;
                        else if (yf < yi && xf > xi && pieces[xf-1][yf+1] != null && pieces[xf-1][yf+1].isFire() && pieces[xf][yf] == null)
                            return true;
                        else if (yf > yi && xf > xi && pieces[xf-1][yf-1] != null && pieces[xf-1][yf-1].isFire() && pieces[xf][yf] == null)
                            return true;
                        else if (yf > yi && xf < xi && pieces[xf+1][yf-1] != null && pieces[xf+1][yf-1].isFire() && pieces[xf][yf] == null)
                            return true;
                        else
                            return false;
                    }
                    else
                        return false;
                }
                else
                {
                    if (yf - yi == -1 && Math.abs(xf - xi) == 1 && pieces[xf][yf] == null && !captured)
                    {
                        return true;
                    }
                    else if (yf - yi == -2 && Math.abs(xf - xi) == 2)
                    {
                        if (yf < yi && xf > xi && pieces[xf-1][yf+1] != null && pieces[xf-1][yf+1].isFire() && pieces[xf][yf] == null)
                            return true;
                        else if (yf < yi && xf < xi && pieces[xf+1][yf+1] != null && pieces[xf+1][yf+1].isFire() && pieces[xf][yf] == null)
                            return true;
                        else
                            return false;
                    }
                    else
                        return false;
                }
            }
        }
    }

    public void select(int x, int y)
    {
        if ((pieces[x][y] != null && turn == 0 && pieces[x][y].isFire()) || (pieces[x][y] != null && turn == 1 && !pieces[x][y].isFire()))
        {
            selected = pieces[x][y];
        }
        else if (pieces[x][y] == null && selected != null)
        {
            selected.move(x, y);
            moved = true;
            if (!selected.isBomb())
                captured = selected.hasCaptured();
        }
    }

    public void place(Piece p, int x, int y)
    {
        if (x < 0 || x > 7 || y < 0 || y > 7 || p == null)
        {
        }
        else
            pieces[x][y] = p;
            if (p.isFire())
            {
                if (p.isKing())
                    if (p.isBomb())
                        StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire-crowned.png", 1, 1);
                    else if (p.isShield())
                        StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire-crowned.png", 1, 1);
                    else
                        StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire-crowned.png", 1, 1);
                else if (p.isShield())
                    StdDrawPlus.picture(x + .5, y + .5, "img/shield-fire.png", 1, 1);
                else if (p.isBomb())
                    StdDrawPlus.picture(x + .5, y + .5, "img/bomb-fire.png", 1, 1);
                else
                    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-fire.png", 1, 1);
            }
            else
            {
                if (p.isKing())
                    if (p.isBomb())
                        StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water-crowned.png", 1, 1);
                    else if (p.isShield())
                        StdDrawPlus.picture(x + .5, y + .5, "img/shield-water-crowned.png", 1, 1);
                    else
                        StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water-crowned.png", 1, 1);
                else if (p.isShield())
                    StdDrawPlus.picture(x + .5, y + .5, "img/shield-water.png", 1, 1);
                else if (p.isBomb())
                    StdDrawPlus.picture(x + .5, y + .5, "img/bomb-water.png", 1, 1);
                else
                    StdDrawPlus.picture(x + .5, y + .5, "img/pawn-water.png", 1, 1);
            }
    }

    public Piece remove(int x, int y)
    {
        if (x < 0 || x > 7 || y < 0 || y > 7)
        {
            System.out.println("Square is out of bounds");
            return null;
        }
        else if (pieces[x][y] == null)
        {
            System.out.println("There is no piece at square (" + x + ", " + y + ")");
            return null;
        }
        else
        {
            Piece temp = pieces[x][y];
            pieces[x][y] = null;
            if ((x + y) % 2 == 0)
                StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
            else
                StdDrawPlus.setPenColor(StdDrawPlus.RED);
            StdDrawPlus.filledSquare(x + .5, y + .5, .5);
            return temp;
        }
    }

    public boolean canEndTurn()
    {
        return moved || captured;
    }

    public void endTurn()
    {
        if (turn == 0)
        {
            turn = 1;
            captured = false;
            /** changed */
            if (selected != null)
            {
                selected.doneCapturing();
            }
            moved = false;
            selected = null;
        }
        else
        {
            turn = 0;
            captured = false;
            /** changed */
            if (selected != null)
            {
                selected.doneCapturing();
            }
            moved = false;
            selected = null;
        }
    }

    private int count()
    {
        return firecount() + watercount();
    }

    private int firecount()
    {
        int count = 0;
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (pieces[i][j] != null && pieces[i][j].isFire())
                    count += 1;
            }
        }
        return count;
    }

    private int watercount()
    {
        int count = 0;
        for (int i = 0; i < 8; i++)
        {
            for (int j = 0; j < 8; j++)
            {
                if (pieces[i][j] != null && !pieces[i][j].isFire())
                    count += 1;
            }
        }
        return count;
    }

    public String winner()
    {
        if (count() == 0)
            return "No one";
        else if (firecount() == 0)
            return "Water";
        else if (watercount() == 0)
            return "Fire";
        else
            return null;
    }
}
