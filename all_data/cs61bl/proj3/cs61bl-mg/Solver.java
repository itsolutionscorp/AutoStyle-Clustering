import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Solver {

	public IntArray2D getGoal(List<String> goal) {
		IntArray2D rtn = new IntArray2D(goal.size(), 4);
		for (int index = 0; index < goal.size(); index++) {
			String[] toCopy = goal.get(index).split("\\s+");
			for (int subIndex = 0; subIndex < 4; subIndex++)
				rtn.setCoord(index, subIndex, Integer.parseInt(toCopy[subIndex]));
		}
		return rtn;
	}

	// http://stackoverflow.com/questions/326390/how-to-create-a-java-string-from-the-contents-of-a-file
	static List<String> readFile(String path, Charset encoding) throws IOException {
		return Files.readAllLines(Paths.get(path), encoding);
	}

	public static void main(String[] args) throws Exception {

		try {
			Solver s = new Solver();
			List<String> init = readFile(args[0], StandardCharsets.US_ASCII);
			List<String> goal = readFile(args[1], StandardCharsets.US_ASCII);
			IntArray2D goalConfig = s.getGoal(goal);
			Board initialBoard = new Board(init);
			// Constructor will crash if malformatted
			Board goalBoard = new Board(goalConfig, initialBoard.height, initialBoard.width);
			for (int goalIndex = 0; goalIndex < goalConfig.length; goalIndex++) {
				boolean hasGoal = false;
				for (int initialIndex = 1; initialIndex < initialBoard.blocks.length; initialIndex++) {
					int[] eachInitial = initialBoard.blocks.getPiece(initialIndex);
					int[] eachGoal = goalBoard.blocks.getPiece(goalIndex);
					if ((eachGoal[2] - eachGoal[0]) == (eachInitial[2] - eachInitial[0])) {
						if ((eachGoal[3] - eachGoal[1]) == (eachInitial[3] - eachInitial[1])) {
							hasGoal = true;
						}
					}
				}
				if (!hasGoal) {
					throw new Exception();
				}
			}

			Graph g1 = new Graph(initialBoard, goalConfig);
			LinkedList<String> solution;
			solution = g1.findSolutionDepth();
			if (solution == null) {
				return;
			} else {
				for (String move : solution)
					if (move != null) {

						System.out.println(move);
					}
			}
		} catch (Exception e) { // Because we're not graded on style =)
			System.out.println("Invalid init and/or goal file.");
			return;
		}
	}
}
