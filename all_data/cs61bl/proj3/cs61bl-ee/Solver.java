import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import java.util.Stack;
import java.util.HashSet;

class Solver {
    private Puzzle puzzle;
    private List<Block> goalBlocks;

    public Solver(String initConfig, String goalConfig) throws Exception {
        init(initConfig, goalConfig);
    }

    private void init(String initConfig, String goalConfig) throws Exception {
        File configFile = new File(initConfig);
        File goalFile   = new File(goalConfig);

        Scanner sc = new Scanner(configFile);
        int height = sc.nextInt();
        int width  = sc.nextInt();

        puzzle = new Puzzle(height, width);
        while (sc.hasNext()) {
            puzzle.addBlock(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        sc = new Scanner(goalFile);
        goalBlocks = new LinkedList<Block>();
        while (sc.hasNext()) {
            Block b = new Block(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
            goalBlocks.add(b);
        }
    }

    public boolean goalTest(State s) {
        for (Block b : goalBlocks) {
            if (!s.containsBlock(b)) {
                return false;
            }
        }
        return true;
    }

    public void solve() {
        // Check all required goal blocks
        HashSet<Block> initBlocks = new HashSet<Block>(puzzle.getBlocks());
        for (Block g : goalBlocks) {
            boolean found = false;
            for (Block b : initBlocks) {
                if (b.isSameSize(g)) {
                    initBlocks.remove(b);
                    found = true;
                    break;
                }
            }
            if (!found) {
                return;
            }
        }

        HashSet<State> seen = new HashSet<State>();
        // Fringe fringe = new UCSFringe();
        Fringe fringe = new GreedyFringe(goalBlocks);
        // Fringe fringe = new AStarFringe(goalBlocks);
        fringe.add(new State(puzzle, goalBlocks));

        int total = 1;
        int completed = 0;
        while (!fringe.isEmpty()) {
            State s = fringe.removeFront();
            if (goalTest(s)) {
                s.printMoves();
                return;
            }

            if (!seen.contains(s)) {
                seen.add(s);
                for (State child : s.expand()) {
                    fringe.add(child);
                    total++;
                }
            }
            completed++;
        }
    }

    public void switchFringe(Fringe src, Fringe dst) {
        for (State s : src.toArray()) {
            dst.add(s);
        }
    }

    public void benchmark(List<Fringe> fringes) {
        String header = String.format("%-15s\t%10s\t%10s%15s%15s",
                                       "Method", "Completed", "Expanded",
                                       "Total Moves", "Time (ms)");
        System.out.println(header);
        System.out.println("------------------------------------------------------------------------");
        String result = "";
        long startTime;
        for (Fringe f : fringes) {
            startTime = System.currentTimeMillis();
            HashSet<State> seen = new HashSet<State>();
            f.add(new State(puzzle, goalBlocks));

            int total = 1;
            int completed = 0;
            while (!f.isEmpty()) {
                State s = f.removeFront();
                if (goalTest(s)) {
                    // s.printMoves();
                    String methodName = f.getClass().getName();
                    result = String.format("%-15s\t%10d\t%10d%15d\t%8d",
                                           methodName, completed, total,
                                           s.getMoveCount(), System.currentTimeMillis() - startTime);
                    System.out.println(result);
                    break;
                }

                if (!seen.contains(s)) {
                    seen.add(s);
                    for (State child : s.expand()) {
                        f.add(child);
                        total++;
                    }
                }
                completed++;
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Invalid init and/or goal file.");
            return;
        }


        try {
            Solver solver = new Solver(args[0], args[1]);
            if (args.length == 3 && args[2].equals("-b")) {
                List<Fringe> fringes = new LinkedList<Fringe>();
                // fringes.add(new UCSFringe());
                // fringes.add(new GreedyFringe(solver.goalBlocks));
                fringes.add(new AStarFringe(solver.goalBlocks));
                solver.benchmark(fringes);
            } else {
                solver.solve();
            }
        } catch (Exception e) {
            System.out.println("Invalid init and/or goal file.");
            return;
        }
    }
}
