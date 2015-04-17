import java.awt.Color;
import java.applet.Applet;
import java.io.File;
import java.net.URL;
import java.applet.AudioClip;
import javax.sound.sampled.*;

public class Board
{
    private Piece pieces[][];
    private boolean moved;
    private boolean selected;
    private String player;
    private int thisx, thisy, nextx, nexty;
    private Piece movedPiece;
    private float huehue;
    private float hue2;


    private void boardMaker()
    {

        for (int i = 0; i < 8; i++)
        {
            if (i % 2 == 0)
            {
                place(new Piece(true, this, i, 0, "pawn"), i,
                      0);
                place(new Piece(true, this, i, 2, "bomb"), i,
                      2);
                place(new Piece(false, this, i, 6, "shield"), i,
                      6);
            }

            else
            {
                place(new Piece(false, this, i, 7, "pawn"), i,
                      7);
                place(new Piece(false, this, i, 5, "bomb"), i,
                      5);
                place(new Piece(true, this, i, 1, "shield"), i,
                      1);
            }
        }
    }


    private static void squareMaker()
    {
        for (int m = 0; m < 8; m++)
        {
            for (int d = 0; d < 4; d++)
            {
                StdDrawPlus.filledSquare(m + 0.5,
                                         (d * 2) + (m % 2) + 0.5,
                                         0.5);
            }
        }
    }

    private static String getPath(Piece p, boolean g)
    {
        String t = "", r = "", k = "";
        if (p == null)
        {
            return "";
        }

        if (!(p.isBomb() || p.isShield()))
        {
            t = "pawn-";
        }
        else
            if (p.isBomb())
            {
                t = "bomb-";
            }
        if (p.isShield())
        {
            t = "shield-";
        }

        if (p.isFire())
        {
            r = "fire";
        }
        else
        {
            r = "water";
        }

        if (p.isKing())
        {
            k = "-crowned";
        }
        else
        {
            k = "";
        }

        if (g == true)
        {
            return "img/gs/" + t + r + k + ".png";
        }

        return "img/" + t + r + k + ".png";

    }

    // Citation: From internet sources, Joseph Chen for help
    private static void player(String filename)
    {
        // Initializa the URL that will store the location of the file.
        AudioInputStream audioInputStream = null;

        // If URI is not found - print what went wrong.
        try
        {
            File file = new File(filename);
            audioInputStream =
                AudioSystem.getAudioInputStream(file);
            if (audioInputStream != null)
            {
                Clip audio = AudioSystem.getClip();
                audio.open(audioInputStream);
                audio.loop(10);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    // Introducing transparency (feat. Joseph Chen)

    private static Color colorMaker(float hue, float sat, float bri,
                                    float alpha)
    {
        // Allows colors to be procedurally generated.

        if (hue < 0)
        {
            hue *= -1;
        }
        if (sat < 0)
        {
            sat *= -1;
        }
        if (bri < 0)
        {
            bri *= -1;
        }
        if (alpha < 0)
        {
            alpha *= -1;
        }

        if (hue > 1)
        {
            hue %= 1;
        }
        if (sat < 0)
        {
            sat %= 1;
        }
        if (bri < 0)
        {
            bri %= 1;
        }
        if (alpha < 0)
        {
            alpha %= 1;
        }

        Color col = Color.getHSBColor(hue, sat, bri);
        Color finalcolor = new Color(col.getRed() / 256F,
                                     col.getGreen() / 256F,
                                     col.getBlue() / 256F, alpha);
        return finalcolor;
    }

    public static void main(String[] args)
    {
        int delay = 10;
        long startTime = System.currentTimeMillis();
        long startTimeO = System.currentTimeMillis();
        int frames = 0;
        int lastFPS = 0;
        double xmin = 0;
        double xmax = 8;
        double ymin = 0;
        double ymax = 8;
        double xmouse = 4;
        double ymouse = 4;
        double scale = 1;

        player("audio/Exploration.wav");
        // MUSIC COMPOSED BY TUSHAR SINGAL AND JOSEPH CHEN

        Board b = new Board(false);
        StdDrawPlus.setScale(0, 8);

        b.huehue = 0;
        b.hue2 = 0.1F;

        while (true)
        {
            startTime = System.currentTimeMillis() - startTimeO;

            double x = StdDrawPlus.mouseX();
            double y = StdDrawPlus.mouseY();

            if (x < -2)
            {
                x = -2;
            }
            if (x > 10)
            {
                x = 10;
            }
            if (y < -2)
            {
                y = -2;
            }
            if (y > 10)
            {
                y = 10;
            }

            // Draw back.
            // StdDrawPlus.setPenColor(StdDrawPlus.BLACK);
            // StdDrawPlus.filledSquare(4, 4, 4);

            double goalx = -2 + (8 - ((x - 4) + (xmin + xmax) / 2) / 2);
            double goaly = -2 + (8 - ((y - 4) + (ymin + ymax) / 2) / 2);
            double speed = 0.1;
            xmouse = (speed * goalx + xmouse) / (speed + 1);
            ymouse = (speed * goaly + ymouse) / (speed + 1);
            StdDrawPlus.picture(xmouse,
                                ymouse,
                                "img/space-background.jpg",
                                (25.6 * 1.5), (16 * 1.5));

            // if (!b.selected)
            //     {
            //         StdDrawPlus.setPenColor(colorMaker(b.hue2, 1F, 0.5F, 0.2F));
            //     }
            // else
            //     {
            //         StdDrawPlus.setPenColor(colorMaker(b.hue2, 1F, 0.5F, 0.2F));
            //     }
            //
            // squareMaker();
            StdDrawPlus.picture(4, 4, "img/grid.png", 10, 10);

            // Highlighting mouse location
            StdDrawPlus.setPenColor(colorMaker(b.hue2, 1F, 0.5F, 0.2F));
            StdDrawPlus.filledSquare((int) x + 0.5,
                                     (int) y + 0.5,
                                     0.5);
            b.hue2 += 0.02F;

            // Draw possible moves
            for (int i = 0; i < 8; i++)
            {
                for (int j = 0; j < 8; j++)
                {
                    if (b.validMove(b.thisx, b.thisy, i, j))
                    {
                        StdDrawPlus.setPenColor(colorMaker(b.hue2, 0.5F, 0.7F, 0.5F));
                        StdDrawPlus.filledSquare(i + 0.5, j + 0.5, 0.5);
                    }
                }
            }


            // Draw selected square
            if (b.selected)
            {
                StdDrawPlus.setPenColor(colorMaker(b.hue2, 0.8F, 0.9F, 0.6F));
                StdDrawPlus.filledSquare(b.thisx + 0.5,
                                         b.thisy + 0.5,
                                         0.5);

            }

            // Draw current mouse
            StdDrawPlus.setPenColor(colorMaker(b.huehue, 1F, 0.5F, 0.5F));
            if (b.selected)
            {
                StdDrawPlus.filledSquare(b.nextx + 0.5,
                                         b.nexty + 0.5,
                                         0.5);
            }

            b.huehue += 0.04;

            // Draw pieces
            for (int i = 0; i < 8; i++)
            {
                for (int j = 0; j < 8; j++)
                {
                    if (b.pieceAt(i, j) != null && (b.thisx != i || b.thisy != j))
                        StdDrawPlus.picture(i + .5, j + .5,
                                            getPath(b.pieceAt(i, j), false), 1, 1);
                }
            }

            double factor = 0.5;
            double minx = factor * (x - 1);
            double maxx = factor * (x - 1) + 5;
            double miny = factor * (y - 1);
            double maxy = factor * (y - 1) + 5;

            if (StdDrawPlus.mousePressed())
            {

                b.nextx = (int) x;
                b.nexty = (int) y;


                if (!b.validMove(b.thisx, b.thisy, (int) x, (int) y))
                {
                    StdDrawPlus.picture(x, y,
                                        "img/sill.png",
                                        20, 20);

                }

                if (b.canSelect((int) x, (int) y))
                {
                    if (b.selected &&
                            (b.pieceAt(b.thisx, b.thisy) != null))
                    {
                        StdDrawPlus.picture(x, y,
                                            getPath(b.pieceAt(b.thisx, b.thisy), false), scale, scale);
                        scale = (3 * scale + 2) / 4;
                    }

                    if (!b.selected &&
                            b.canSelect(b.nextx, b.nexty))
                    {
                        b.select(b.nextx, b.nexty);
                    }
                }

                // Draw Grayscale
                else
                {
                    StdDrawPlus.picture(x, y,
                                        getPath(b.pieceAt(b.thisx, b.thisy), true), scale, scale);
                    scale = (3 * scale + 2) / 4;
                }

            }
            else     // release mouse
            {

                scale = (3 * scale + 1) / 4;
                minx = 0;
                miny = 0;
                maxx = 8;
                maxy = 8;
                if (b.pieceAt(b.nextx, b.nexty) != null)
                {
                    b.selected = false;
                    b.thisx = -1;
                    b.thisy = -1;
                    b.nextx = -1;
                    b.nexty = -1;
                }

                else
                    if (b.canSelect(b.nextx, b.nexty))
                    {
                        b.select(b.nextx, b.nexty);
                        int possibleMoves = 0;

                        // Draw possible moves
                        for (int i = 0; i < 8; i++)
                        {
                            for (int j = 0; j < 8; j++)
                            {
                                if (i != b.nextx && j != b.nexty && b.canSelect(i, j))
                                {
                                    possibleMoves++;
                                }
                            }
                        }
                        if (possibleMoves == 0)
                        {
                            b.endTurn();
                        }
                    }
                    else
                    {
                        b.selected = false;
                        b.thisx = -1;
                        b.thisy = -1;
                        b.nextx = -1;
                        b.nexty = -1;
                    }
            }

            if (StdDrawPlus.isSpacePressed() &&
                    b.canEndTurn())
            {
                b.endTurn();
            }

            if (StdDrawPlus.isNPressed())
            {
                b = new Board(false);
            }

            frames++;

            if (startTime >= 1000)
            {
                startTimeO = System.currentTimeMillis();
                lastFPS = frames;
                frames = 0;
            }

            StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
            StdDrawPlus.text(xmin + 0.05 * (xmax - xmin),
                             ymin + 0.05 + (ymax - ymin), "FPS: " + lastFPS);

            int smoothness = 15;

            xmin = (minx + smoothness * xmin) / (smoothness + 1);
            xmax = (maxx + smoothness * xmax) / (smoothness + 1);
            ymin = (miny + smoothness * ymin) / (smoothness + 1);
            ymax = (maxy + smoothness * ymax) / (smoothness + 1);

            StdDrawPlus.setXscale(xmin, xmax);
            StdDrawPlus.setYscale(ymin, ymax);

            StdDrawPlus.show(delay);
        }
    }

    public Board(boolean shouldBeEmpty)
    {
        pieces = new Piece[8][8];
        moved = false;
        selected = false;
        player  = "fire";
        nextx = -1;
        nexty = -1;

        if (!shouldBeEmpty)
        {
            boardMaker();
        }
    }


    public Piece pieceAt(int xin, int yin)
    {
        //System.out.println("Checking for piece at " + xin + ", " + yin);
        if ((xin < 0) || (yin < 0) || (xin >= 8) ||
                (yin >= 8))
        {
            //System.out.println("pieceAt check out of bounds.");
            return null;
        }

        if (pieces[xin][yin] != null)
        {
            //System.out.println("pieceAt found.");
            return pieces[xin][yin];
        }

        //System.out.println("No pieceAt conditions met.");
        return null;
    }

    public void place(Piece p, int x, int y)
    {
        //System.out.println("Attempting to place piece " + p + "at " +
        //x + ", " + y);
        if (((x >= 0) && (x < 8)) &&
                ((y >= 0) && (y < 8)) && (p != null))
        {
            pieces[x][y] = p;
        }
    }

    public Piece remove(int x, int y)
    {
        if ((x < 0) || (y < 0) || (x >= 8) || (y >= 8))
        {
            //System.out.println("Invalid remove.");
            return null;
        }

        if (pieces[x][y] == null)
        {
            //System.out.println("No piece in remove area.");
            return null;
        }

        Piece temp = pieces[x][y];
        pieces[x][y] = null;
        return temp;
    }

    private boolean validMove(int xi, int yi,
                              int xf, int yf)
    {

        if (Math.abs(xf - xi) != Math.abs(yf - yi))
        {
            //System.out.println("Non-diagonal move.");
            return false;
        }

        if (pieceAt(xi, yi) == null)
        {
            //System.out.println("No piece to move!");
            return false;
        }

        if (pieceAt(xf, yf) != null)
        {
            //System.out.println("Piece at move location.");
            return false;
        }

        if ((xi >= 8 || xi < 0 || yi >= 8 || yi < 0) ||
                (xf >= 8 || xf < 0 || yf >= 8 || yf < 0))
        {
            //System.out.println("Out of bounds selection.");
            return false;
        }

        if ((pieceAt(xi, yi).isFire() && player.equals("water")) ||
                (!pieceAt(xi, yi).isFire() && player.equals("fire")))
        {
            //System.out.println("Can not move opposite race.");
            return false;
        }

        int xs = ((xi + xf) / 2);
        int ys = ((yi + yf) / 2);

        if (pieceAt(xi, yi).hasCaptured())
        {
            if ((Math.abs(xf - xi) != 2) ||
                    Math.abs(yf - yi) != 2)
            {
                //System.out.println("Invalid post-capture move.");
                return false;
            }

            if (pieceAt(xf, yf) != null)
            {
                //System.out.println("Piece at move location.");
                return false;
            }
        }

        if (Math.abs(xf - xi) == 2)
        {
            if (pieceAt(xs, ys) != null)
            {
                if (((pieceAt(xs, ys).side() == 0) &&
                        player.equals("fire")) ||
                        ((pieceAt(xs, ys).side() == 1) &&
                         player.equals("water")))
                {
                    //System.out.println("No friendly fire.");
                    return false;
                }
                else
                {
                    //System.out.println("OK to capture " + (pieceAt(xs,
                    //ys).isFire()
                    //? "fire"
                    //: "water") + " piece at " + xs + ", " + ys);
                }
            }
            else
            {
                //System.out.println("No piece to capture at " + xs + ", " + ys);
                return false;
            }
        }

        else
            if (Math.abs(xf - xi) != 1 && !pieceAt(xi, yi).hasCaptured())
            {
                //System.out.println(
                //String.format("%d, %d is too far to move from %d, %d%n",
                //              xf, yf, xi, yi));
                return false;
            }

        if (pieceAt(xi, yi).isKing())
        {
            return true;
        }


        if (!pieceAt(xi, yi).isKing())
        {
            if (pieceAt(xi, yi).isFire())
            {
                if ((yf - yi) < 0)
                {
                    //System.out.println("Fire can not move downwards.");
                    //System.out.println("Not a king.");
                    return false;
                }

                if (moved && !pieceAt(xi, yi).hasCaptured())
                {
                    return false;
                }

                else
                {
                    return true;
                }
            }

            else
                if (!pieceAt(xi, yi).isFire())
                {
                    if ((yf - yi) > 0)
                    {
                        //System.out.println("Water can not move upwards.");
                        //System.out.println("Not a king.");
                        return false;
                    }

                    if (moved && !pieceAt(xi, yi).hasCaptured())
                    {
                        return false;
                    }

                    else
                    {
                        return true;
                    }
                }
        }

        //System.out.println("No conditions met!");
        return false;

    }

    public boolean canSelect(int x, int y)
    {
        if (pieceAt(x, y) == null)
        {
            if (!moved && validMove(thisx, thisy, x, y))
            {
                // System.out.println("canSelect TRUE :: moved FALSE; validMove TRUE");
                return true;
            }
            if (selected &&
                    pieceAt(thisx, thisy).hasCaptured() &&
                    validMove(thisx, thisy, x, y))
            {
                // System.out.println("canSelect TRUE :: captured TRUE; validMove TRUE");
                return true;
            }
        }

        if (pieceAt(x, y) != null)
        {
            if ((pieceAt(x, y).isFire() && player.equals("water")) ||
                    (!pieceAt(x, y).isFire() && player.equals("fire")))
            {
                // System.out.println("canSelect FALSE :: opposite sides");
                return false;
            }

            if (!selected)
            {
                // System.out.println("canSelect TRUE :: NO CURRENT SELECTION");
                return true;
            }
            if (!moved && selected)
            {
                // System.out.println("canSelect TRUE :: moved TRUE; selected TRUE");
                return true;
            }
        }

        // System.out.println("canSelect FALSE :: end of checks");
        return false;
    }

    public void select(int x, int y)
    {
        if (selected)
        {
            if (pieces[thisx][thisy] != null &&
                    pieceAt(x, y) == null)
            {
                moved = true;
                pieces[thisx][thisy].move(x, y);
                if (pieceAt(x, y) == null)
                {
                    //System.out.println("select: condition 1.");
                    movedPiece = null;
                    selected = false;
                    thisx = -1;
                    thisy = -1;
                    // endTurn();
                    return;
                }
            }

        }

        if (!selected)
        {
            //System.out.println("Select condition 2.");
            selected = true;
        }

        movedPiece = pieceAt(x, y);
        thisx = x;
        thisy = y;
    }

    public boolean canEndTurn()
    {
        if (moved == false)
        {
            //System.out.println("Can not end turn: have not moved.");
            return false;
        }
        else
        {
            //System.out.println("Can end; moved.");
            return true;
        }
    }

    public void endTurn()
    {
        moved = false;
        if (movedPiece != null)
        {
            //System.out.println("Calling doneCapturing().");
            movedPiece.doneCapturing();
        }
        selected = false;
        thisx = -1;
        thisy = -1;
        if (player.equals("fire"))
        {
            player = "water";
        }
        else
            if (player.equals("water"))
            {
                player = "fire";
            }
        //System.out.println("Player is now " + player + ".");
    }

    public String winner()
    {
        //System.out.println("Finding winner.");
        int x = 0;
        int y = 0;
        int firetally = 0;
        int watertally = 0;

        while (x < 8)
        {
            while (y < 8)
            {
                if (pieceAt(x, y) != null)
                {
                    if (pieceAt(x, y).isFire())
                    {
                        firetally += 1;
                    }
                    if (!pieceAt(x, y).isFire())
                    {
                        watertally += 1;
                    }
                }
                y++;
            }

            y = 0;
            x += 1;
        }

        //System.out.println("xtally is " + x + ", ytally is " + y);
        if ((firetally == 0) && (watertally == 0))
        {
            return "No one";
        }
        if ((firetally > watertally) &&
                (watertally == 0))
        {
            return "Fire";
        }
        if ((watertally > firetally) &&
                (firetally == 0))
        {
            return "Water";
        }

        return null;
    }
}