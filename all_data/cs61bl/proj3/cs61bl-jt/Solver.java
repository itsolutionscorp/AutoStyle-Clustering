import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * A solver for slides puzzles
 * 
 * @author Zihao Jing, Pingao Liu, Yuchao Gao
 */
public class Solver {
	public static HashSet<Piece> GOODGAME;
	private static HashSet<Piece> INITGAME;
	private static Path initPath;
	private static Path goalPath;
	private static int height = 0;
	private static int width = 0;
	private static int max = 0;
	public static int inNum = 0;
	public static int goNum = 0;
	public static int ONE;
	public static int UD;
	public static int LR;
	public static int x;
	public static int y;
	private static Board init;
	private static HashSet<Board> visited;
	private static Queue<Board> queue;

	private static boolean isGoal(Board currentBoard) {
		return currentBoard.allPieces.containsAll(GOODGAME);
	}

	private static boolean pq() {
		if (GOODGAME.size() / INITGAME.size() > 0.5 || GOODGAME.size() == 1)
			return true;
		else
			return false;
	}

	public static void main(String[] args) throws IOException {
		isValidFile(args);
		setBoard();
		isValidBoard(inNum, goNum, INITGAME, GOODGAME, height, width);
		solver();
	}

	private static void setBoard() throws IOException {
		GOODGAME = new HashSet<Piece>();
		INITGAME = new HashSet<Piece>();
		Scanner in = new Scanner(initPath);
		Scanner go = new Scanner(goalPath);
		height = in.nextInt();
		width = in.nextInt();
		max = height > width ? height : width;
		while (in.hasNextInt()) {
			int y1 = in.nextInt();
			int x1 = in.nextInt();
			int y2 = in.nextInt();
			int x2 = in.nextInt();
			INITGAME.add(new Piece(x1, y1, x2, y2, max));
			inNum++;
		}
		while (go.hasNextInt()) {
			int y1 = go.nextInt();
			int x1 = go.nextInt();
			int y2 = go.nextInt();
			int x2 = go.nextInt();
			GOODGAME.add(new Piece(x1, y1, x2, y2, max));
			goNum++;
		}
		in.close();
		go.close();
	}

	private static void solver() throws IOException {
		setSolver();
		while (!queue.isEmpty()) {
			Board current = queue.poll();
			if (isGoal(current)) {
				for (String o : current.pathToGG)
					System.out.println(o);
				System.exit(0);
			}
			for (Board neighbor : current.getNeighbors())
				if (!visited.contains(neighbor)) {
					visited.add(neighbor);
					queue.add(neighbor);
				} else
					neighbor = null;
			current = null;
		}
	}

	private static void setSolver() throws IOException {
		visited = new HashSet<Board>();
		init = new Board(width, height, INITGAME, GOODGAME, inNum);
		if (pq())
			queue = new PriorityQueue<Board>();
		else
			queue = new LinkedList<Board>();
		if (init.one())
			ifOne();
		queue.add(init);
	}

	private static void ifOne() throws IOException {
		List<String> sin = Files.readAllLines(initPath);
		List<String> sgo = Files.readAllLines(goalPath);
		sin.remove(0);
		int x1 = 0, y1 = 0;
		int x2 = 0, y2 = 0;
		for (String s : sgo)
			if (!sin.contains(s)) {
				y1 = one(s);
				x1 = two(s);
			}

		for (String s : sin)
			if (!sgo.contains(s)) {
				y2 = one(s);
				x2 = two(s);
			}
		ONE = Math.abs(x2 - x1) + Math.abs(y2 - y1);
		LR = x2 - x1;
		UD = y2 - y1;
		x = x2;
		y = y2;
		queue = new PriorityQueue<Board>();
	}

	public static int one(String s) {
		return Integer.valueOf(s.split(" ")[0]);
	}

	public static int two(String s) {
		return Integer.valueOf(s.split(" ")[1]);
	}

	public static int three(String s) {
		return Integer.valueOf(s.split(" ")[2]);
	}

	public static int four(String s) {
		return Integer.valueOf(s.split(" ")[3]);
	}

	private static boolean isMinus(int i) {
		return i < 0;
	}

	private static boolean isMinus0(int i) {
		return i <= 0;
	}

	private static void error() {
		System.out.println("Invalid init and/or goal file.");
		System.exit(0);
	}

	private static void isValidFile(String[] args) throws IOException {
		if (args.length != 2)
			error();
		initPath = Paths.get(args[0]);
		goalPath = Paths.get(args[1]);
		if (Files.notExists(goalPath) || Files.notExists(goalPath))
			error();
		Scanner in = new Scanner(initPath);
		Scanner go = new Scanner(goalPath);
		if (!in.hasNextInt() || !go.hasNextInt()) {
			in.close();
			go.close();
			error();
		}
		List<String> si = Files.readAllLines(initPath);
		List<String> sg = Files.readAllLines(goalPath);
		String line1 = si.get(0);
		if (!line1.contains(" "))
			error();

		String[] s12 = line1.split(" ");
		int len = s12.length;
		int num = 0;
		int[] it = new int[line1.length()];
		for (int i = 0; i < len; i++)
			if (!s12[i].isEmpty()) {
				it[num] = i;
				num++;
			}

		if (num != 2)
			error();

		Scanner scanner1 = new Scanner(s12[it[0]]);
		Scanner scanner2 = new Scanner(s12[it[1]]);
		if (!scanner1.hasNextInt())
			error();
		if (!scanner2.hasNextInt())
			error();
		scanner1.close();
		scanner2.close();

		int height = in.nextInt();
		int width = in.nextInt();
		if (isMinus0(height) || isMinus0(width))
			error();
		si.remove(0);
		isInts(si, height, width);
		isInts(sg, height, width);
	}

	private static void isInts(List<String> si, int height, int width) {
		int[][] tray = new int[height][width];
		for (String s : si) {
			if (!s.isEmpty()) {
				if (!s.contains(" "))
					error();
				String[] ss = s.split(" ");
				int len = ss.length;
				int num = 0;
				int[] it = new int[s.length()];
				for (int i = 0; i < len; i++)
					if (!ss[i].isEmpty()) {
						it[num] = i;
						num++;
					}

				if (num != 4)
					error();
				Scanner scanner1 = new Scanner(ss[it[0]]);
				Scanner scanner2 = new Scanner(ss[it[1]]);
				Scanner scanner3 = new Scanner(ss[it[2]]);
				Scanner scanner4 = new Scanner(ss[it[3]]);
				if (!scanner1.hasNextInt())
					error();
				if (!scanner2.hasNextInt())
					error();
				if (!scanner3.hasNextInt())
					error();
				if (!scanner4.hasNextInt())
					error();
				int i1 = scanner1.nextInt();
				int i2 = scanner2.nextInt();
				int i3 = scanner3.nextInt();
				int i4 = scanner4.nextInt();
				if (isMinus(i1) || isMinus(i2) || isMinus(i3) || isMinus(i4))
					error();
				if (i1 >= height || i3 >= height || i2 >= width || i4 >= width)
					error();
				if (i1 > i3 || i2 > i4)
					error();
				for (int i = i1; i <= i3; i++)
					for (int j = i2; j <= i4; j++)
						if (tray[i][j] == 1)
							error();
						else
							tray[i][j] = 1;

				scanner1.close();
				scanner2.close();
				scanner3.close();
				scanner4.close();
			}
		}
	}

	private static void isValidBoard(int inNum, int goNum,
			HashSet<Piece> allHashedPieces, HashSet<Piece> gg, int height,
			int width) {

		if (goNum > inNum)
			error();
		Piece p1 = new Piece(1, 1, 1, 1, 0);
		Piece p2 = new Piece(1, 1, 1, 1, 0);
		for (Piece piece : allHashedPieces)
			if (piece.size() >= p1.size())
				p1 = piece;
		for (Piece piece : gg)
			if (piece.size() >= p2.size())
				p2 = piece;
		if (p2.size() > p1.size())
			error();

		int[][] ii = new int[height + 1][width + 1];
		int[][] gi = new int[height + 1][width + 1];
		for (Piece pi : allHashedPieces) {
			ii[pi.high][pi.wide]++;
		}
		for (Piece pi : gg)
			gi[pi.high][pi.wide]++;
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				if (gi[i][j] > ii[i][j])
					error();

	}
}
// 18-20