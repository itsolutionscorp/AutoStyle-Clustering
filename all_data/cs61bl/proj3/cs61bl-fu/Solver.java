
/**
 * Class to solve all types of sliding block puzzles with only rectangular pieces.
 * 
 * @author Srinand Balaji, Ted Fujimoto, David Mrdjenovich
 * @version August 5, 2015
 */

import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Solver
{
    //Running flags used to control output for debugging purposes.
    public static final boolean VISUALIZE = false;
    public static final boolean DEBUG = false;
    public static final boolean TIME = false;

    //Goal state of the system.
    Board theBoard, goal;

    //Runs the program given input of a setup and goal file.
    public static void main (String[] args) {
        try {
            long start;
            if (TIME) {
                start = System.currentTimeMillis();
            }

            Scanner scanInit = new Scanner(new File(args[0]));
            Scanner scanGoal = new Scanner(new File(args[1]));

            String[] strDim = scanInit.nextLine().split(" ");
            int dim1 = Integer.parseInt(strDim[0]);
            int dim2 = Integer.parseInt(strDim[1]);

            Solver theSolver = new Solver();
            theSolver.theBoard = theSolver.new Board(dim2, dim1);
            theSolver.goal = theSolver.new Board(dim2, dim1);

            while (scanInit.hasNextLine()) {
                String[] blockDim = scanInit.nextLine().split(" ");
                int bDim1 = Integer.parseInt(blockDim[0]);
                int bDim2 = Integer.parseInt(blockDim[1]);
                int bDim3 = Integer.parseInt(blockDim[2]);
                int bDim4 = Integer.parseInt(blockDim[3]);
                theSolver.theBoard.addBlock(bDim2, bDim1, bDim4, bDim3);
            }

            theSolver.theBoard.findEmptySpaces();
            while (scanGoal.hasNextLine()) {
                String[] blockDim = scanGoal.nextLine().split(" ");
                int bDim1 = Integer.parseInt(blockDim[0]);
                int bDim2 = Integer.parseInt(blockDim[1]);
                int bDim3 = Integer.parseInt(blockDim[2]);
                int bDim4 = Integer.parseInt(blockDim[3]);
                theSolver.goal.addBlock(bDim2, bDim1, bDim4, bDim3);
            }

            ArrayList<Solver.Board.BoardMove> solution = theSolver.theBoard.solveBoard();

            if (solution.isEmpty()) {
                //System.out.println("No Solution.");
                return;
            }

            ArrayList<Solver.Board> boards = new ArrayList<Solver.Board>();
            ArrayList<Solver.Board.Move> moves = new ArrayList<Solver.Board.Move>();
            for (Solver.Board.BoardMove bm : solution) {
                boards.add(bm.theBoard);
                moves.add(bm.theMove);
                if (bm.theMove != null) {
                    System.out.println(bm.theMove);
                }
            }

            if (TIME) {
                System.out.println("\n\n***Time Elapsed***\n");
                System.out.println(System.currentTimeMillis() - start);
            }

            if (VISUALIZE) {
                theSolver.visualize(boards,moves);
            }
            scanInit.close();
            scanGoal.close();
        }
        catch (IOException e) {
            System.out.println("Invalid init and/or goal file.");
        }
        catch (RuntimeException e) {
            System.out.println("Invalid init and/or goal file.");
        }
    }

    /**
     * Will eventually be callable to visualize solutions.
     */
    public void visualize (ArrayList<Solver.Board> boards, ArrayList<Solver.Board.Move> moves) {
        Board.Visualizer viz = theBoard.new Visualizer(boards,moves);
    }

    private class Board {
        private Space[][] spaces;
        private ArrayList<Space> emptySpaces = new ArrayList<Space>();
        private ArrayList<Block> blocks = new ArrayList<Block>();

        private int heuristicCode = 0;
        private boolean heuristicCalc = false;

        /**
         * Constructs an empty Board object with specified width and height.
         * @param width
         *              length of the board along the x direction.
         * @param height
         *              length of the board along the y direction.
         */
        public Board (int width, int height) {
            spaces = new Space[width][height];
            for (int i = 0; i < width; i ++) {
                for (int j = 0; j < height; j ++) {
                    spaces[i][j] = new Space(i,j,null);
                }
            }
        }

        /**
         * Constructs a new copy of the given board with a move made.
         * @param prev
         *              the board to copy.
         * @param chg
         *              the move to make changing the copy of the original board. 
         */
        public Board (Board prev, Move chg) {
            //Copy Contents
            //David Change 8/7 22:37
            HashSet<Block> added = new HashSet<Block>(prev.emptySpaces.size());
            emptySpaces = new ArrayList<Space>(prev.emptySpaces.size());
            blocks = new ArrayList<Block>(prev.blocks.size());
            spaces = new Space[prev.spaces.length][prev.spaces[0].length];
            for (int i = 0; i < prev.spaces.length; i++) {
                for (int j = 0; j < prev.spaces[0].length; j ++) {
                    if (prev.spaces[i][j].occupier == null) {
                        emptySpaces.add(prev.spaces[i][j]);
                        this.spaces[i][j] = prev.spaces[i][j];
                    }
                    else if (!prev.spaces[i][j].occupier.equals(chg.mover)) {
                        this.spaces[i][j] = prev.spaces[i][j];
                        if (!added.contains(spaces[i][j].occupier)) {
                            blocks.add(spaces[i][j].occupier);
                            added.add(spaces[i][j].occupier);
                        }
                    }
                }
            }
            Block newBlock = new Block(chg.mover);
            newBlock.x = chg.ex;
            newBlock.y = chg.ey;
            for (int i = 0; i < chg.mover.width; i ++) {
                for (int j = 0; j < chg.mover.height; j ++) {
                    this.spaces[i+chg.ex][j+chg.ey] = new Space(i+chg.ex,j+chg.ey,null);
                    if (emptySpaces.contains(spaces[i+chg.ex][j+chg.ey])) {
                        emptySpaces.remove(spaces[i+chg.ex][j+chg.ey]);
                    }
                    this.spaces[i+chg.ex][j+chg.ey].occupier = newBlock;
                    blocks.add(newBlock);
                }
            }
            int dx = chg.ex - chg.sx;
            int dy = chg.ey - chg.sy;
            int sx = dx == 0 ? chg.sx : (dx > 0 ? chg.sx : chg.ex + chg.mover.width);
            int sy = dy == 0 ? chg.sy : (dy > 0 ? chg.sy : chg.ey + chg.mover.height);
            for (int i = 0; i < Math.abs(dy * chg.mover.width + dx * chg.mover.height); i ++) {
                Space toAdd = new Space(sx+i*Math.abs(dy),sy+i*Math.abs(dx),null);
                spaces[sx+i*Math.abs(dy)][sy+i*Math.abs(dx)] = toAdd;
                emptySpaces.add(toAdd);
            }

            //End Change
            /*
            this(prev.spaces.length, prev.spaces[0].length);
            for (Block b : prev.blocks) {
            if (!b.equals(chg.mover)) {
            this.addBlock(new Block(b));
            }
            }
            //Make Move
            int dx = chg.ex-chg.sx;
            int dy = chg.ey-chg.sy;
            this.addBlock(chg.sx+dx,chg.sy+dy,
            chg.sx+dx+chg.mover.width-1,chg.sy+dy+chg.mover.height-1);
             */
        }

        /**
         * Method intended to be called after all pieces are added to the board.
         * Searches through all board spaces and finds Empty Spaces, updating
         * the internal structure.
         */
        public void findEmptySpaces () {
            for (int i = 0; i < spaces.length; i ++) {
                for (int j = 0; j < spaces[i].length; j++) {
                    if (spaces[i][j].occupier == null) {
                        emptySpaces.add(spaces[i][j]);
                    }
                }
            }
        }

        /**
         * Adds the Block object with the given dimensions to this Board.
         * @param sx
         *          The top left x coordinate of the block.
         * @param sy
         *          The top left y coordinate of the block.
         * @param ex
         *          The bottom right x coordinate of the block.
         * @param ey
         *          The bottom right y coordinate of the block. 
         */
        public void addBlock (int sx, int sy, int ex, int ey) {
            int ssx = sx <= ex ? sx:ex;
            int ssy = sy <= ey ? sy:ey;
            int width = Math.abs(sx - ex);
            int height = Math.abs(sy - ey);
            addBlock(new Block(ssx,ssy,width + 1, height + 1));
        }

        public void addBlock (Block b) {
            for (int i = 0; i < b.width; i ++) {
                for (int j = 0; j < b.height; j ++) {
                    spaces[b.x + i][b.y + j].occupier = b;
                }
            }
            blocks.add(b);
        }

        /**
         * Finds all possible moves and their resulting Board configurations possible
         * starting from this Board.
         * @returns
         *              ArrayList<BoardMove> of possible moves and their corresponding boards.
         *
         * Algorithm: Go through all emptySpaces. For each empty space, check the 4 spaces
         * to the north, south, east and west. If there is a block in any of the spaces, check
         * if the block can move in those given directions, if so add this to the BoardMove list.
         * Add moves to a HashSet to avoid duplicate processing. 
         */
        public ArrayList<BoardMove> findMoves () {
            ArrayList<BoardMove> toReturn = new ArrayList<BoardMove>();
            HashSet<Move> checked = new HashSet<Move>();
            for (Space s : emptySpaces) {
                for (int i = - 2; i <= 2; i ++) {
                    if (i == 0) {
                        continue;
                    }
                    if (s.x + i/2 < 0 || s.x + i/2 >= spaces.length ||
                    s.y + i%2 < 0 || s.y + i%2 >= spaces[s.x+i/2].length) {
                        continue;
                    }
                    Block toMove = spaces[s.x+i/2][s.y+i%2].occupier;
                    if (toMove == null) {
                        continue;
                    }
                    boolean canMove = true;
                    for (int j = 0; j < Math.max(toMove.width*(Math.abs(i%2)), toMove.height*Math.abs((i/2))); j ++) {
                        canMove &= spaces[(toMove.x + j)*(Math.abs(i%2)) + s.x*Math.abs((i/2))]
                        [(toMove.y + j)*Math.abs((i/2)) + s.y*(Math.abs(i%2))].occupier == null;
                    }
                    if (canMove) {
                        Move toDo = new Move(toMove.x,toMove.y,
                                toMove.x-(i/2),toMove.y-(i%2), toMove);
                        if (!checked.contains(toDo)) {
                            Board toAdd = new Board(this, toDo);
                            //David Change 8/7 10:43
                            //toAdd.findEmptySpaces();
                            //End Change
                            toReturn.add(new BoardMove(toAdd,toDo));
                            checked.add(toDo);
                        }
                    }
                }
            }
            return toReturn;
        }

        public boolean satisfiesSolution () {
            for (Block b : Solver.this.goal.blocks) {
                if (spaces[b.x][b.y].occupier == null) {
                    return false;
                }
                if (!spaces[b.x][b.y].occupier.equals(b)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals (Object other) {
            if (!(other instanceof Board)) {
                return false;
            }
            Board otherB = (Board)other;
            for (int i = 0; i < spaces.length; i ++) {
                for (int j = 0; j < spaces[i].length; j ++) {
                    if (!(spaces[i][j] == otherB.spaces[i][j]) && 
                    !spaces[i][j].equals(otherB.spaces[i][j])) {
                        return false;
                    }
                }
            }
            return true;
        }

        public int hashCode () {
            int toReturn = 0;
            /*
            for (int i = 0; i < blocks.size(); i ++) {
            toReturn *= 31;
            toReturn += blocks.get(i).hashCode();
            }
             */

            for (int i = 0; i < spaces.length; i ++) {
                for (int j = 0; j < spaces[i].length; j++) {
                    toReturn *= 7;
                    if (spaces[i][j].occupier != null) {
                        Block b = spaces[i][j].occupier;
                        toReturn += b.x + spaces.length * b.y;
                        j += b.width;
                    }
                }
            }

            for (int i = 0; i < emptySpaces.size(); i ++) {
                Space s = emptySpaces.get(i);
                toReturn *= ((16*16*16-1) + s.x + s.y * spaces.length);
            }
            return toReturn;
        }

        /**
         * Because the priority queue is a min heap, if this has a better heuristic code
         * it's value will be less and compareTo will show this Board to be smaller.
         */
        public int compareTo (Board other) {
            return this.heuristicCode - other.heuristicCode;
        }

        /**
         * The heuristic algorithm written for this project finds the largest block on the board, and checks
         * the surrounding blocks for their size. The smaller these blocks are, the smaller the heuristic code
         * is. This is based on the idea that the largest block surrounded by smaller blocks will allow the 
         * larger block with more leeway, as the smaller blocks will be easier to manipulate into different
         * spaces. Therefore, the smallest heuristic code is the most promising one. 
         *
         * @return heuristicCode
         * 		the integer representing how promising this board is in regards to the path to the goal board.
         */
        public int heuristicCode () {
            //Largest Block algorithm.
            if (!heuristicCalc) {
                //finds the largest block on the board theBoard.
                Block largest = null;
                int wMax = 0;
                int hMax = 0;
                for (Block b: Solver.this.theBoard.blocks) {
                    if ((b.height * b.width) > (wMax * hMax)) {
                        wMax = b.width;
                        hMax = b.height;
                        largest = b;
                    }
                }

                //coordinates for the largest block
                int xLarge = largest.x; //x-coordinate of the top left vertex of the block.
                int yLarge = largest.y; //y-coordinate of the top left vertex of the block.
                int wLarge = largest.width; //x-coordinate of the bottom right vertex of the block.
                int hLarge = largest.height; //y-coordinate of the bottom right vertex of the block.

                //the next section of code checks the rows and columns around the largest block.
                //the smaller the surrounding blocks are, the smaller the number that is added to the heuristic code.

                //checks NORTH of the largest block.
                if (yLarge > 0) {
                    for (int i = xLarge; i < wLarge; i++) {
                        if (spaces[i][yLarge - 1].occupier != null) {
                            heuristicCode += (spaces[i][yLarge - 1].occupier.height - spaces[i][yLarge - 1].occupier.y)
                            + (spaces[i][yLarge - 1].occupier.width - spaces[i][yLarge - 1].occupier.x); //adds the absolute length and width of the block to the heuristicCode.
                        }
                    }
                }

                //checks SOUTH of the largest block.
                if (hLarge < spaces[0].length - 1) {
                    for (int j = xLarge; j < wLarge; j++) {
                        if (spaces[j][hLarge + 1].occupier != null) {
                            heuristicCode += (spaces[j][hLarge + 1].occupier.height - spaces[j][hLarge + 1].occupier.y)
                            + (spaces[j][hLarge + 1].occupier.width - spaces[j][hLarge + 1].occupier.x); //adds the absolute length and width of the block to the heuristicCode.
                        }
                    }
                }

                //checks WEST of the largest block.
                if (xLarge > 0) {
                    for (int k = yLarge; k < hLarge; k++) {
                        if (spaces[xLarge - 1][k].occupier != null) { 
                            heuristicCode += (spaces[xLarge - 1][k].occupier.height - spaces[xLarge - 1][k].occupier.y)
                            + (spaces[xLarge - 1][k].occupier.width - spaces[xLarge - 1][k].occupier.x); //adds the absolute length and width of the block to the heuristicCode.
                        }
                    }
                }

                //checks EAST of the largest block.
                if (wLarge < spaces.length - 1) {
                    for (int l = yLarge; l < hLarge; l++) {
                        if (spaces[wLarge + 1][l].occupier != null) {
                            heuristicCode += (spaces[wLarge + 1][l].occupier.height - spaces[wLarge + 1][l].occupier.y)
                            + (spaces[wLarge + 1][l].occupier.width - spaces[wLarge + 1][l].occupier.x); //adds the absolute length and width of the block to the heuristicCode.
                        }
                    }
                }

                heuristicCalc = true;
            }
            return heuristicCode;

            //Board Comparison algorithm. 
            //the original, and much slower, heuristic algorithm written for this project.
            /**
            // checks how close all blocks on theBoard are to their respective
            // configuration on the goal board.
            ArrayList<Integer> xValsGoal = new ArrayList<Integer>();
            ArrayList<Integer> yValsGoal = new ArrayList<Integer>();
            for (Block b: Solver.this.goal.blocks) {
            xValsGoal.add(b.x);
            yValsGoal.add(b.y);
            }

            ArrayList<Integer> xValsCur = new ArrayList<Integer>();
            ArrayList<Integer> yValsCur = new ArrayList<Integer>();
            for (Block b: Solver.this.theBoard.blocks) {
            xValsCur.add(b.x);
            yValsCur.add(b.y);
            }

            ArrayList<Integer> xValsComp = new ArrayList<Integer>();
            ArrayList<Integer> yValsComp = new ArrayList<Integer>();
            for (Integer i: xValsGoal) {
            if (!xValsCur.contains(i)) {
            xValsComp.add(i);
            }
            }
            for (Integer i: yValsGoal) {
            if (!yValsCur.contains(i)) {
            yValsComp.add(i);
            }
            }

            int xSum = 0;
            int ySum = 0;
            for (Integer i: xValsComp) {
            xSum *= i;
            }
            for (Integer i: yValsComp) {
            ySum = i;
            }

            heuristicCode = xSum + ySum;
            return heuristicCode;
             **/
        }

        /**
         * Iterates through all possible moves in order of lowest weighted
         * path to find the series of moves to the solution.
         * @returns ArrayList<Move>
         *                              Series of moves required to solve the board. 
         */
        public ArrayList<BoardMove> solveBoard () {
            class PathElement implements Comparable<PathElement> {
                public PathElement previous;
                public Move change;
                public Board value;
                public int pathTotal;

                public PathElement (Board value) {
                    this(value, 0, null, null);
                }

                public PathElement (Board value, PathElement previous, Move change) {
                    this(value, 0, previous, change);
                }

                public PathElement (Board value, int pathTotal, PathElement previous, Move change) {
                    this.value = value;
                    this.pathTotal = pathTotal;
                    this.previous = previous;
                    this.change = change;
                }

                public int compareTo (PathElement other) {
                    return this.pathTotal - other.pathTotal;
                }
            }
            ArrayList<BoardMove> thePath = new ArrayList<BoardMove>(200);
            PriorityQueue<PathElement> fringe = new PriorityQueue<PathElement>(20000);
            HashSet<Board> processed = new HashSet<Board>(10000000,(float)0.6);
            fringe.offer(new PathElement(this, 0, null, null));
            PathElement goal = null;
            int boardsAnalyzed = 0;
            while (!fringe.isEmpty()) {
                boardsAnalyzed ++;
                if (DEBUG) {
                    System.out.println("Current Move Depth: " + fringe.peek().pathTotal + 
                        "\tCurrent Queue Depth: " + fringe.size());
                    System.out.println("Boards Analyzed: " + boardsAnalyzed);
                }
                PathElement current = fringe.poll();
                if (current.value.satisfiesSolution()) {
                    goal = current;
                    break;
                }
                //David Change 8/7 20:26
                //processed.add(current.value);
                //End Change
                for (BoardMove bm : current.value.findMoves()) {
                    if (!processed.contains(bm.theBoard)) {
                        //David Change 8/7 20:26 to prevent duplicate processing of boards.
                        processed.add(bm.theBoard);
                        //System.out.println(bm.theBoard.hashCode());
                        //End Change
                        fringe.offer(new PathElement(bm.theBoard, 
                                current.pathTotal + bm.theBoard.heuristicCode(),
                                current, bm.theMove));
                    }
                }
            }
            if (goal == null) {
                return thePath;
            }
            do {
                thePath.add(0,new BoardMove(goal.value, goal.change));
                goal = goal.previous;
            } while (goal != null);
            return thePath;
        }

        /**
         * Class to represent a location on the board, has an x,y position and a possible
         * Block occupying the space.
         */
        private class Space {
            public int x,y;
            public Block occupier;
            public Space (int x, int y, Block occupier) {
                this.x = x; this.y = y; this.occupier = occupier;
            }

            public boolean equals (Object other) {
                if (!(other instanceof Space)) {
                    return false;
                }
                Space otherS = (Space)other;
                if (occupier == null || otherS.occupier == null) {
                    if (occupier == null && otherS.occupier == null) {
                        return x == otherS.x && y == otherS.y;
                    }
                    return false;
                }
                return x == otherS.x && y == otherS.y && occupier.equals(otherS.occupier);
            }

            public int hashCode () {
                return x + Board.this.spaces.length * y;
            }
        }

        /**
         * Class to represent an object move from one point to another. 
         * Has fields indicating starting and ending positions along with
         * the block that is moving.
         */
        private class Move {
            public int sx, sy, ex, ey;
            Block mover;
            public Move (int sx, int sy, int ex, int ey, Block mover) {
                this.sx = sx; this.sy = sy; this.ex = ex; this.ey = ey;
                this.mover = mover;
            }

            public boolean equals (Object other) {
                if (!(other instanceof Move)) {
                    return false;
                }
                Move otherM = (Move)other;
                return (sx == otherM.sx && sy == otherM.sy && 
                    ex == otherM.ex && ey == otherM.ey &&
                    mover.equals(otherM.mover));
            }

            public String toString () {
                return sy + " " + sx + " " + ey + " " + ex;
            }

            public int hashCode () {
                return mover.hashCode();
            }
        }

        /**
         * Simple class used in the solveBoard() method, groups Move objects with 
         * Board objects that result from making a move from a previous Board.
         */
        private class BoardMove {
            Board theBoard;
            Move theMove;
            public BoardMove (Board b, Move m) {
                theBoard = b; theMove = m;
            }

            public boolean equals (Object other) {
                if (!(other instanceof BoardMove)) {
                    return false;
                }
                BoardMove otherBM = (BoardMove) other;
                return theBoard.equals(otherBM.theBoard) && theMove.equals(otherBM.theMove);
            }

            public int hashCode () {
                return theBoard.hashCode() + theMove.hashCode();
            }
        }
        private class Block {
            int x, y, width, height;

            public Block (int x, int y, int width, int height) {
                this.x = x; this.y = y;
                this.width = width; this.height = height;
            }

            public Block (Block toCopy) {
                this.x = toCopy.x;
                this.y = toCopy.y;
                this.width = toCopy.width;
                this.height = toCopy.height;
            }

            public boolean equals (Object other) {
                if (!(other instanceof Block)) {
                    return false;
                }
                Block otherB = (Block)other;
                return this.x == otherB.x && this.y == otherB.y &&
                this.width == otherB.width && this.height == otherB.height;
            }

            public int hashCode () {
                return (x + y*Board.this.spaces.length)*((16*16-1)*width + (27*27-1)*height);
            }
        }

        /**
         * Class for visualizing solutions and the board.
         */
        private class Visualizer extends JFrame {
            public int SQUARE_SIZE = 700;
            public Visualizer (ArrayList<Board> frames, ArrayList<Move> moves) {
                if (frames.isEmpty() || moves.isEmpty()) {
                    throw new IllegalArgumentException("Can't visualize empty arraylists.");
                }
                setSize(SQUARE_SIZE,SQUARE_SIZE);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                add(new BoardPanel(frames, moves));
                setVisible(true);
            }

            private class BoardPanel extends JPanel {
                public int SLOWNESS = 1; //How many ticks it takes to complete a move.
                //Divide this by 33 for # seconds to complete a move.
                public int PADDING = 6;
                private ArrayList<Board> frames;
                private ArrayList<Move> moves;
                int timerCounter = 0;
                Thread timerThread;
                Timer moveTimer;
                public BoardPanel (ArrayList<Board> frames, ArrayList<Move> moves) {
                    this.frames = frames;
                    this.moves = moves;
                    moveTimer = new Timer();
                    timerThread = new Thread(moveTimer);
                    timerThread.start();
                }

                public void tick () {
                    repaint();
                    timerCounter ++;
                }

                public void paint (Graphics g) {
                    int boardFrame = timerCounter / SLOWNESS;
                    int moveFrame = timerCounter % SLOWNESS;

                    int factorX = this.getWidth()/frames.get(0).spaces.length;
                    int factorY = this.getHeight()/frames.get(0).spaces[0].length;

                    if (boardFrame >= frames.size() || boardFrame + 1 >= moves.size()) {
                        moveTimer.stop = true;
                        boardFrame = frames.size()-2;
                        moveFrame = SLOWNESS - 1;
                    }
                    //Plot everything except for moving block.
                    for (Block b : frames.get(boardFrame).blocks) {
                        g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
                        if (!b.equals(moves.get(boardFrame+1).mover)) {
                            g.fillRect(b.x*factorX+PADDING, b.y*factorY+PADDING, 
                                b.width*factorX - PADDING, b.height*factorY - PADDING);
                        }
                    }
                    Move m = moves.get(boardFrame+1);
                    //g.setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
                    g.setColor(new Color(0,0,0));
                    g.fillRect(((moveFrame)*m.ex*factorX + (SLOWNESS-moveFrame)*m.sx*factorX)/SLOWNESS + PADDING,
                        ((moveFrame)*m.ey*factorY + (SLOWNESS-moveFrame)*m.sy*factorY)/SLOWNESS + PADDING,
                        m.mover.width*factorX - PADDING, m.mover.height*factorY - PADDING);

                    g.setColor(new Color(0,0,0));
                }

                private class Timer implements Runnable {
                    int ticks;
                    int interval; //ms delay between ticks.
                    boolean stop = false;
                    public Timer () {
                        this(-1, 33);
                    }

                    public Timer (int ticks) {
                        this(ticks, 33);
                    }

                    public Timer (int ticks, int interval) {
                        this.ticks = ticks;
                        this.interval = interval;
                    }

                    public void run () {
                        stop = false;
                        int tick = 0;
                        while (!stop && tick != ticks) {
                            BoardPanel.this.tick();
                            try {
                                Thread.sleep(interval);
                            }
                            catch (Exception e) {

                            }
                            tick ++;
                        }
                    }
                }
            }
        }
    }
}


