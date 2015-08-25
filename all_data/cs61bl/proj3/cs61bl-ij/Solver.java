import java.util.*;
import java.io.*;
 
 
 
public class Solver {
    HashSet<Setup> visited = new HashSet<Setup>();
    Setup inital;
    Setup goal;
    int[] size;
    PriorityQueue<Setup> frontier = new PriorityQueue<Setup>();
 
    public static void main(String[] args) {
        if (args.length != 2) {
            crash(); return;
        } 
        Solver S = new Solver();
        Setup.owner = S;
        try {
            S.run(args);
        } catch (Exception e) {
            crash(); return;
        }
         
    }
     
    public void run(String[] args) throws Exception {
        readInitial(args[0]);
        readGoal(args[1]); 
        frontier.offer(inital);
        Setup end = solve();
        if (end == null) return;
        traceBack(end);
    }
     
 
 
    public Setup solve() throws Exception {
        Setup currentSetup = null;
        while ((currentSetup = frontier.poll()) != null) {
            visited.add(currentSetup);
            currentSetup.updatePotentials();
            if (goal.equals(currentSetup)) {
                return currentSetup;
            }
            for (Setup i : currentSetup.nextSetups()) {
                if (!visited.contains(i)) {
                    frontier.add(i);
                }
            }
        }
        return null;
    }
     
    public void traceBack(Setup end) {
        Stack<String> result = new Stack<String>();
        while (end.parent() != null) {
            result.push(end.toString());
            end = end.parent();
        }
        while (!result.isEmpty()) {
            System.out.println(result.pop());
        }
    }
     
    public static void crash() {
        System.out.println("Invalid init and/or goal file.");
        System.exit(0);
    }
     
    public void readGoal(String fileName) throws Exception {
        BufferedReader toRead = new BufferedReader(new FileReader(fileName));
        goal = new Setup(toRead, size, false);
    }
     
    public void readInitial(String fileName) throws Exception {
        BufferedReader toRead = new BufferedReader(new FileReader(fileName));
        String[] lineOne = toRead.readLine().split(" ");
        if (lineOne == null) crash();
        if (lineOne.length == 2) {
            size = new int[]{Integer.parseInt(lineOne[0]), Integer.parseInt(lineOne[1])};
        } else {
            crash();
        }
        inital = new Setup(toRead, size, true);
    }
     
    public static FileInputStream loadFile(String path) throws Exception {
        FileInputStream toUpdate = new FileInputStream(path);
        return toUpdate;
    }
     
     
     
     
 
}