import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;



public abstract class Solver {
	

	
	public static void main(String arg[]) {
		Scanner configScr = null;
		Scanner goalScr = null;
		try {
			configScr = new Scanner(new FileReader(arg[0]));
			goalScr = new Scanner(new FileReader(arg[1]));
		} catch (Exception e) {
			System.out.println("Invalid init and/or goal file.");
			System.exit(1);
		}
		int[] size= new int[2];
		int counts = 0;
		try {
			size[0] = Integer.parseInt(configScr.next());
			size[1] = Integer.parseInt(configScr.next());
			while (configScr.hasNext()) {
				if (counts > 2000){
					break;
				}
				Integer.parseInt(configScr.next());
				counts ++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Invalid init and/or goal file.");
			System.exit(1);
		}
		if (counts > 2000 && size[0] >90){
			SolverBig s = new SolverBig();
			s.init(arg[0], arg[1]);

			String result = s.solverBig(s.begin, s.end);

			if (result == null) {
				System.exit(1);
			}

			System.out.print(result);
		}else{
			SolverSmall s = new SolverSmall();
			s.init(arg[0], arg[1]);


			String result = s.solverSmall(s.begin, s.end);

			if (result == null) {
				System.exit(1);
			}

			System.out.print(result);
		}
	}
	
	public class Coordinate {
		int myX;
		int myY;

		public Coordinate(int x, int y) {
			myX = x;
			myY = y;
		}

		@Override
		public boolean equals(Object other) {
			Coordinate otherC = (Coordinate) other;
			return myX == otherC.myX && myY == otherC.myY;
		}
	}
	
	public class Block {
		Coordinate upLeftCoord;
		Coordinate bottomRightCoord;

		public Block(int a, int b, int c, int d) {
			Coordinate up = new Coordinate(a, b);
			upLeftCoord = up;
			Coordinate down = new Coordinate(c, d);
			bottomRightCoord = down;
		}

		@Override
		public boolean equals(Object other) {
			Block otherB = (Block) other;
			return upLeftCoord.equals(otherB.upLeftCoord)
					&& bottomRightCoord.equals(otherB.bottomRightCoord);
		}

		@Override
		public int hashCode() {
			return upLeftCoord.myX * upLeftCoord.myY + bottomRightCoord.myX * bottomRightCoord.myY;
		}

		public Block copy() {
			Block newB = new Block(upLeftCoord.myX, upLeftCoord.myY,
					bottomRightCoord.myX, bottomRightCoord.myY);
			return newB;
		}
	}
}
