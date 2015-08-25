import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;


public class Solver {

	private Solver() {}
	
	public static void solve(ArrayList<String> start, ArrayList<String> goal)
			throws IllegalStateException {

		Board board = new Board(start, goal);
		Tray current = board.currentTray();
		HashSet<String> visited = new HashSet<String>(5000);
		Stack<Tray> fringe = new Stack<Tray>();
	
		fringe.push(current);
		visited.add(board.stringCode());
		
		while (!fringe.isEmpty()) {
			
			current = fringe.peek();
			board.redo(current);
			
			if (board.isSolution()) {
				Stack<String> solution = new Stack<String>();
				while (current.move() != null) {
					solution.push(current.move());
					current = current.parent();
				}
				while (solution.isEmpty() == false) {
					System.out.println(solution.pop());
				}
				return;
		
			} else {
				if (!current.processed()) {
					Iterator<Tray> children = board.makeChildren().iterator();
					while (children.hasNext()) {
						Tray child = children.next();
						board.redo(child);
						if (visited.add(board.stringCode())) {
							fringe.push(child);
						} else {
							children.remove();
						}
						board.undo(child);
					}
					current.process();
				}
				if (current.children().isEmpty()) {
					fringe.pop();
					board.undo(current);
				}
				current.parent().children().remove(current);
			}
		}
	}
	
	
	public static void main(String[] args) {
		try {
			if (args.length > 2) throw new IllegalStateException();
			ArrayList<String> initialConfig = FileReader.readFile(args[0]);
			ArrayList<String> goalConfig = FileReader.readFile(args[1]);
			solve(initialConfig, goalConfig);
		} catch (IllegalStateException e) {
			System.out.println("Invalid init and/or goal file.");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Invalid init and/or goal file.");
		}
	}

}
