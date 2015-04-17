/**
 *  @author Warren Shen
 */

public class Board {

  private int scale;
  private int player;

  private boolean moved;

  private int[] selection;

  private Piece selected;
  private Piece[][] pieces;

  public Board(boolean shouldBeEmpty) {
    this.scale = 8;
    this.player = 0;

    this.moved = false;

    this.selection = new int[2];
    this.selection[0] = 0;
    this.selection[1] = 0;

    this.selected = null;
    this.pieces = new Piece[scale][scale];

    if (!shouldBeEmpty) {
      for (int i = 0; i < scale; i++) {
        for (int j = 0; j < scale; j++) {
          Piece p = null;
          if (i >= 0 && j >= 0) {
            if (j == 0 && i % 2 == 0)
              p = new Piece(true, this, i, j, "pawn");
            else if (j == 1 && i % 2 == 1)
              p = new Piece(true, this, i, j, "shield");
            else if (j == 2 && i % 2 == 0)
              p = new Piece(true, this, i, j, "bomb");
            else if (j == 5 && i % 2 == 1)
              p = new Piece(false, this, i, j, "bomb");
            else if (j == 6 && i % 2 == 0)
              p = new Piece(false, this, i, j, "shield");
            else if (j == 7 && i % 2 == 1)
              p = new Piece(false, this, i, j, "pawn");
            this.pieces[i][j] = p;
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    Board b = new Board(false);
    StdDrawPlus.setXscale(0, b.scale);
    StdDrawPlus.setYscale(0, b.scale);

    while(true) {
      b.drawBoard(b.scale);
      if (StdDrawPlus.mousePressed()) {
        int x = (int)StdDrawPlus.mouseX();
        int y = (int)StdDrawPlus.mouseY();
        if (b.canSelect(x, y))
          b.select(x, y);
      }
      else if (StdDrawPlus.isSpacePressed()) {
        if (b.canEndTurn())
          b.endTurn();
      }
      StdDrawPlus.show(25);
    }
  }

  private void drawBoard(int n) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (selected != null && i == selection[0] && j == selection[1])
          StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
        else if ((i + j) % 2 == 0)
          StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
        else
          StdDrawPlus.setPenColor(StdDrawPlus.RED);
        StdDrawPlus.filledSquare(i + .5, j + .5, .5);

        Piece p = pieces[i][j];
        String img = "";
        if (p != null) {
          if (p.isBomb())
            img = (p.isFire()) ? "img/bomb-fire" : "img/bomb-water";
          else if(p.isShield())
            img = (p.isFire()) ? "img/shield-fire" : "img/shield-water";
          else
            img = (p.isFire()) ? "img/pawn-fire" : "img/pawn-water";

          if (p.isKing())
            img += "-crowned";
          StdDrawPlus.picture(i + .5, j + .5, img + ".png", 1, 1);
        }
      }
    }
  }

  public boolean canSelect(int x, int y) {
    if (x < scale && y < scale && x >= 0 && y >= 0) {
      Piece p = pieceAt(x, y);
      if (p == null && selected != null) {
        int xDif = x - selection[0];
        int yDif = y - selection[1];
        if (moved)
          if (Math.abs(xDif) < 2 && Math.abs(yDif) < 2)
            return false;

        if (yDif < 0 && selected.isFire() && !selected.isKing())
          return false;
        if (yDif > 0 && !selected.isFire() && !selected.isKing())
          return false;

        if (Math.abs(xDif) == 2 && Math.abs(yDif) == 2) {
          Piece other = pieceAt(x - (xDif / 2), y - (yDif / 2));
          if (other == null || other.isFire() == selected.isFire())
            return false;
        }

        xDif = Math.abs(xDif);
        yDif = Math.abs(yDif);
        if (xDif < 3 && yDif < 3 && xDif == yDif)
          return true;
      }
      if (p != null && !moved) {
        if ((p.isFire() && player == 0) || (!p.isFire() && player == 1))
          return true;
        return false;
      }
    }
    return false;
  }

  public void select(int x, int y) {
    Piece p = pieceAt(x, y);
    if (!moved) {
      if (selected != null && p == null)
        place(selected, x, y);
      else {
        selection[0] = x;
        selection[1] = y;
        selected = pieceAt(x, y);
      }
    }
    else {
      if (selected != null && selected.hasCaptured() && p == null)
        place(selected, x, y);
    }
  }

  public Piece pieceAt(int x, int y) {
    if (x < scale && y < scale && x >= 0 && y >= 0)
      return pieces[x][y];
    return null;
  }

  public void place(Piece p, int x, int y) {
    if (x < scale && y < scale && x >= 0 && y >= 0 && p != null) {
      if (selected != null) {
        int xDif = x - selection[0];
        int yDif = y - selection[1];

        if (moved)
          if (Math.abs(xDif) == 1 && Math.abs(yDif) == 1)
            return;

        if (Math.abs(xDif) > 1 && Math.abs(yDif) > 1) {
          Piece potential = pieceAt(x - (xDif / 2), y - (yDif / 2));
          if (potential == null)
            return;
        }

        pieces[selection[0]][selection[1]] = null;
        pieces[x][y] = p;

        p.move(x, y);
        selected = pieces[x][y];
        selection[0] = x;
        selection[1] = y;
        moved = true;

        if (p.hasCaptured() && p.isBomb()) {
          for (int i = x - 1; i < x + 2; i++)
            for (int j = y + 1; j > y - 2; j--)
              explode(i, j);
          selected = null;
        }
      }
      else
        pieces[x][y] = p;
    }
  }

  public Piece remove(int x, int y) {
    if (x > scale || y > scale || x < 0 || y < 0) {
      System.out.print("Invalid remove parameters!");
      return null;
    }

    Piece p = pieceAt(x, y);
    if (p == null) {
      System.out.println("No piece at specified position!");
      return null;
    }
    else if (selected != null && selected.isFire() == p.isFire())
      return null;
    else {
      pieces[x][y] = null;
      return p;
    }
  }

  private void explode(int x, int y) {
    if (x < scale && y < scale && x >= 0 && y >= 0) {
      Piece potential = pieceAt(x, y);
      if (potential != null && !potential.isShield()) {
        pieces[x][y] = null;
      }
    }
  }

  public boolean canEndTurn() {
    return moved;
  }

  public void endTurn() {
    if (selected != null) {
      selected.doneCapturing();
      selected = null;
    }
    moved = false;
    player = (player + 1) % 2;
  }

  public String winner() {
    int fire = 0;
    int water = 0;

    for (int i = 0; i < scale; i++) {
      for (int j = 0; j < scale; j++) {
        Piece p = pieces[i][j];
        if (p != null) {
          if (p.isFire())
            fire += 1;
          else
            water += 1;
        }
      }
    }

    if (fire > 0 && water > 0)
      return null;
    else if (fire > water)
      return "Fire";
    else if (water > fire)
      return "Water";
    else
      return "No one";
  }
}
