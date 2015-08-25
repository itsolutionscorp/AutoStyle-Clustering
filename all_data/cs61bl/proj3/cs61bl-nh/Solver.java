import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
      
public class Solver {
      
    private Tray initialTray; //the initial Tray that we start with
    private List<String> goalConfig; //the goal Tray that we want to get to
    private int width; //width of Trays
    private int height; //height of Trays
    private Set<Tray> visited; //set of visited Trays, so we don't repeat ourselves
      
    /**
     * Constructor for a Solver object; it takes in two files and read them to initialize necessary structures and objects
     * @param initialTrayFile
     * @param goalTrayFile
     */
    public Solver(String initialTrayFile, String goalTrayFile) {
        BufferedReader initialBR, goalBR;
        visited = new HashSet<Tray>();
        goalConfig = new ArrayList<String>();
        try {
            //read in initial and goal files
            initialBR = new BufferedReader(new FileReader(initialTrayFile));
            goalBR = new BufferedReader(new FileReader(goalTrayFile));
            String[] dimension = initialBR.readLine().split(" ");
            if (dimension.length != 2) {
            	System.out.println("Invalid init and/or goal file.");
            	initialBR.close();
        		goalBR.close();
                return;
            }
            width = parse(dimension[1]);
            height = parse(dimension[0]);
            String line = initialBR.readLine();
                  
            //make an initial Tray from the specified lines in the initial file
            initialTray = new Tray();
            while (line != null) {
            	String[] splitLine = line.split(" ");
            	if (splitLine.length != 4) {
            		System.out.println("Invalid init and/or goal file.");
            		initialBR.close();
            		goalBR.close();
                    return;
            	}
                initialTray.addLine(line);
                line = initialBR.readLine();
            }
            if (!initialTray.makeSpaces()) {
            	System.out.println("Invalid init and/or goal file.");
            	initialBR.close();
        		goalBR.close();
            	return;
            };
              
            //make the goal Tray from the specified lines in the goal file
            line = goalBR.readLine();
            while (line != null) {
            	String[] splitLine = line.split(" ");
            	if (splitLine.length != 4) {
            		System.out.println("Invalid init and/or goal file.");
            		initialBR.close();
            		goalBR.close();
                    return;
            	}
                goalConfig.add(line);
                line = goalBR.readLine();
            }
                  
        } catch (FileNotFoundException e) {
            System.out.println("Invalid init and/or goal file.");
            return;
        } catch (IOException e) {
            System.out.println("Invalid init and/or goal file.");
            return;
        } catch (NumberFormatException e) {
        	System.out.println("Invalid init and/or goal file.");
            return;
        }
    }
      
    /**
     * Parses a string to an integer, because I'm too lazy to type
     * @param integer Integer in String form
     * @return int Integer in int form
     */
    public static int parse(String integer) {
        return Integer.parseInt(integer);
    }
         
    /**
     * Prints the path of moves after a solve has been made
     * If found to be impossible, the 
     * Current algorithm: Prioritizes lower Manhattan Distance and higher matches with goal configuration
     */
    public void printPath() {
        Stack<Tray> fringe = new Stack<Tray>();
        Map<Tray, Tray> steps = new HashMap<Tray, Tray>();
        fringe.push(initialTray);
        boolean solved = false;
        Tray curTray = null; //declared here so that after the while loop, we have curTray as the Tray with the goal config
        while (!fringe.isEmpty()) {
            curTray = fringe.pop();
            visited.add(curTray);
            if (curTray.config.containsAll(goalConfig)) {
                solved = true;
                break;
            }
            curTray.possibleMoves();
            while (!curTray.moves.isEmpty()) {
                Tray nextTray = curTray.moves.poll();
                if (!visited.contains(nextTray)) {
                    fringe.push(nextTray);
                    steps.put(nextTray, curTray);
                }
            }
        }
        if (!solved) {
            return;
        }
        Stack<String> reversePath = new Stack<String>();
        while (curTray.lastMove != null) {
            reversePath.push(curTray.lastMove);
            curTray = steps.get(curTray);
        }
        while (!reversePath.isEmpty()) {
            System.out.println(reversePath.pop());
        }
    }
          
    public static void main (String[] args) {
        if (args.length != 2) {
            System.out.println("Invalid init and/or goal file.");
            return;
        }
        File initialFile = new File(args[0]);
        File goalFile = new File(args[1]);
        if (args[0].equals(args[1]) || !initialFile.exists() || !goalFile.exists()) {
            System.out.println("Invalid init and/or goal file.");
            return;
        }
        Solver solver = new Solver(args[0], args[1]);
        solver.printPath();
    }
      
    /**
     * Customized Comparator object that compares Trays for use in the Priority Queue
     *
     */
    private class TrayComparator implements Comparator<Solver.Tray> {
    
        @Override
        public int compare(Solver.Tray o1, Solver.Tray o2) {
            return o1.score - o2.score;
        }
    }
      
    /**
     * Private class representing an instance of the board (called the Tray)
     */
    public class Tray implements Comparable<Tray> {
        private String lastMove; //the move that was just made to get to this config
        private boolean[][] spaces; //representation of board, each space is either empty (true) or filled (false)
        private Queue<Tray> moves; //set of Trays possible to get to from this Tray
        private Set<String> config; //configuration of this Tray
        private int score = 1;
          
        /**
         * Constructor of the initial Tray object
         */
        public Tray() {
            config = new HashSet<String>();
            spaces = new boolean[height][width];
            moves = new PriorityQueue<Tray>(new TrayComparator());
            lastMove = null;
        }
              
        /**
         * Constructor for non-initial Trays
         * We get the trayConfig and prevMove from parent Tray
         * @param trayConfig An ArrayList specifying the configuration of this Tray
         * @param prevMove The move made by the parent Tray to get to this Tray
         */
        public Tray(Set<String> trayConfig, String prevMove) {
            config = trayConfig;
            moves = new PriorityQueue<Tray>(new TrayComparator());
            lastMove = prevMove;
            spaces = new boolean[height][width];
            makeSpaces();
        }
              
        /**
         * Create the spaces array based on the configuration described in config
         */
        public boolean makeSpaces() {
            for (boolean[] row : spaces) {
                Arrays.fill(row, true);
            }
            for (String block : config) {
                int topLeftX = parse(block.split(" ")[1]);
                int topLeftY = parse(block.split(" ")[0]);
                for (int x = 0; x < width(block); x++) {
                    for (int y = 0; y < height(block); y++) {
                        if (!spaces[(topLeftY + y)][topLeftX + x]) {
                            //space looking at is already false -> overlapping blocks -> exits the solver
                            return false;
                        }
                        spaces[topLeftY + y][topLeftX + x] = false;
                    }
                }
            }
            return true;
        }
              
        /**
         * Add block configuration to the Tray configuration
         * @param line Line description to be added
         */
        public void addLine(String line) {
            config.add(line);
        }
              
        /**
         * Return the width of the block using the coordinate format
         * @param block String representation of the block
         * @return Width of block
         */
        public int width(String block) {
            String[] splitNumbers = block.split(" ");
            return parse(splitNumbers[3]) - parse(splitNumbers[1]) + 1;
        }
            
        /**
         * Return the height of the block using the coordinate format
         * @param block String representation of the block
         * @return Height of block
         */
        public int height(String block) {
            String[] splitNumbers = block.split(" ");
            return parse(splitNumbers[2]) - parse(splitNumbers[0]) + 1;
        }
              
        /**
         * Check if two Trays are equal. Two Tray objects are equal if their config contains the same blocks in the same places
         * @param other Tray object for comparison
         * @return true if two Trays are equal
         */
        public boolean equals(Object other) {
            Tray otherTray = (Tray) other;
            return config.containsAll(otherTray.config) && otherTray.config.containsAll(config);
        }
              
        /**
         * Return the hashCode of this Tray object
         * Hash code is dependent on width, height, and blocks configuration
         * NEEDS REFINEMENT
         */
        public int hashCode() {
            int hash = width*31 + height*35;
            for (String block : config) {
                hash += block.hashCode() % width*height;
            }
            return hash;
        }
              
        /**
         * Generate possible configurations by making a move
         * Put these configurations in HashSet moves
         */
        public void possibleMoves() {
            int space = 1;
            for (String block : config) {
                boolean[] movableDirections = canMove(block, space);
                if (movableDirections[0]) { //if moving top
                    moveUpDown(block, -space);
                }
                if (movableDirections[2]) { //if moving bottom
                    moveUpDown(block, space);
                }
                if (movableDirections[1]) { //if moving right
                    moveSideway(block, space);
                }
                if (movableDirections[3]) { //if moving left
                    moveSideway(block, -space);
                }
            }
        }
              
        /**
         * Move a block sideway by space (space can be negative or positive)
         * Also add the new Tray to HashSet moves
         * @param block Block to move
         * @param space Distance of movement
         */
        public void moveSideway(String block, int space) {
            Set<String> newConfig = copyConfig(block);
            String[] splitBlock = block.split(" ");
            String moveMade = splitBlock[0] + " " + splitBlock[1]  + " " + splitBlock[0] + " " + (parse(splitBlock[1])+space);
            String newBlock = moveMade.split(" ")[2] + " " + moveMade.split(" ")[3] + " " + splitBlock[2] + " " + (parse(splitBlock[3])+space);
            newConfig.add(newBlock);
            Tray newTray = new Tray(newConfig, moveMade);
            newTray.calculateScore();
            if (!visited.contains(newTray)) {
                moves.add(newTray);
            }
        }
              
        /**
         * Move a block up or down by space (space can be negative or positive)
         * Also add the new Tray to HashSet moves
         * @param block Block to move
         * @param space Distance of movement
         */
        public void moveUpDown(String block, int space) {
            Set<String> newConfig = copyConfig(block);
            String[] splitBlock = block.split(" ");
            String moveMade = splitBlock[0] + " " + splitBlock[1] + " " + (parse(splitBlock[0])+space) + " " + splitBlock[1];
            String newBlock = moveMade.split(" ")[2] + " " + moveMade.split(" ")[3] + " " + (parse(splitBlock[2])+space) + " " + splitBlock[3];
            newConfig.add(newBlock);
            Tray newTray = new Tray(newConfig, moveMade);
            newTray.calculateScore();
            if (!visited.contains(newTray)) {
                moves.add(newTray);
            }
        }
            
        /**
         * Create a new Set of blocks that are not yet solved by the current configuration (blocks in goal that aren't in place yet)
         * @return Set of blocks
         */
        public Set<String> eliminateCompleted() {
            Set<String> uncompletedGoalSet = new HashSet<String>();
            for (String block : goalConfig) {
                if (!config.contains(block)) {
                    uncompletedGoalSet.add(block);
                    score += config.size() / goalConfig.size();
                }
            }
            return uncompletedGoalSet;
        }
          
        /**
         * Calculate a score for the current Tray
         * Score is determined by the unsolved blocks
         * For each unsolved block, it is matched up with a block in the current configuration, their distance is calculated, and the score is calculated by the formula (widht+height)*distance
         */
        public void calculateScore() {
            Set<String> uncompleted = eliminateCompleted();
            for (String block : config) {
                for (String uncompletedBlock : uncompleted) {
                    int blockWidth = width(block);
                    int blockHeight = height(block);
                    int goalBlockWidth = width(uncompletedBlock);
                    int goalBlockHeight = height(uncompletedBlock);
                    if (blockWidth == goalBlockWidth && blockHeight == goalBlockHeight) {
                        int dist = manhattanDist(block, uncompletedBlock);
                        score += (width+height) / dist;
                    }
                }
            }
        }
          
        /**
         * Return the Manhattan Distance of a block to the specified block
         * @param block First block
         * @param otherBlock Second block
         * @return Distance between two blocks (width difference + height difference)
         */
        public int manhattanDist(String block, String otherBlock) {
            String[] splitBlock = block.split(" ");
            String[] splitOtherBlock = otherBlock.split(" ");
            int y1 = parse(splitBlock[0]);
            int x1 = parse(splitBlock[1]);
            int y2 = parse(splitOtherBlock[0]);
            int x2 = parse(splitOtherBlock[1]);
            return Math.abs(x1-x2) + Math.abs(y1-y2);
        }
          
        /**
         * Return an array of booleans on which directions a block can move in the given distance
         * @param block Block trying to move
         * @param space How far the block wants to move
         * @return A boolean array in the form of [top, right, bottom, left] specifying which direction the block can move
         */
        public boolean[] canMove(String block, int space) {
            int blockWidth = width(block);
            int blockHeight = height(block);
            boolean[] movable = new boolean[4]; // [top, right, bottom, left]
            int[] topLeft = {parse(block.split(" ")[1]), parse(block.split(" ")[0])};
            int[] bottomRight = {parse(block.split(" ")[3]), parse(block.split(" ")[2])};
            //test moving top
            for (int x = topLeft[0]; x < topLeft[0] + blockWidth; x++) {
                if (!isInbound(x, topLeft[1] - space) || topLeft[1]-space < 0) {
                    movable[0] = false;
                    break;
                }
                if (spaces[topLeft[1] - space][x]) {
                    if (x + 1 == topLeft[0] + blockWidth) {
                        movable[0] = true;
                    }
                } else {
                    movable[0] = false;
                    break;
                }
            }
            //test moving bottom
            for (int x = bottomRight[0]; x > bottomRight[0] - blockWidth; x--) {
                if (!isInbound(x, bottomRight[1] + space) || bottomRight[1]+space >= height) {
                    movable[2] = false;
                    break;
                }
                if (spaces[bottomRight[1] + space][x]) {
                    if (x -1 == bottomRight[0] - blockWidth) {
                        movable[2] = true;
                    }
                } else {
                    movable[2] = false;
                    break;
                }
            }
            //test moving left
            for (int y = topLeft[1]; y < topLeft[1] + blockHeight; y++) {
                if (!isInbound(topLeft[0] - space, y) || topLeft[0]-space < 0) {
                    movable[3] = false;
                    break;
                }
                if (spaces[y][topLeft[0] - space]) {
                    if (y + 1 == topLeft[1] + blockHeight) {
                        movable[3] = true;
                    }
                } else {
                    movable[3] = false;
                    break;
                }
            }
            //test moving right
            for (int y = bottomRight[1]; y > bottomRight[1] - blockHeight; y--) {
                if (!isInbound(bottomRight[0] + space, y) || bottomRight[0]+space >= width) {
                    movable[1] = false;
                    break;
                }
                if (spaces[y][bottomRight[0] + space]) {
                    if (y - 1 == bottomRight[1] - blockHeight) {
                        movable[1] = true;
                    }
                } else {
                    movable[1] = false;
                    break;
                }
            }
                  
            return movable;
        }
              
        /**
         * Copy a configuration EXCEPT for the block that we are excluding (we're changing it)
         * @param config Original configuration
         * @return A copy of the configuration
         */
        public Set<String> copyConfig (String blockToExclude) {
            Set<String> copy = new HashSet<String>();
            for (String block : config) {
                if (!block.equals(blockToExclude)) {
                    copy.add(block);
                }
            }
            return copy;
        }
              
        /**
         * Check that the space given is within the board
         * @param position Array position of space (in form y*width + x)
         * @return true if (x, y) is in bound
         */
        public boolean isInbound(int x, int y) {
            return x >= 0 && y >= 0 && x < width && y < height;
        }
  
        /**
         * Overrideen compareTo method from Comparable interface
         */
        @Override
        public int compareTo(Tray o) {
            return Integer.compare(score, o.score);
        }
    }
}