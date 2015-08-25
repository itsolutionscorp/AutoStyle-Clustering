import java.io.File;


public class Solver {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Invalid init and/or goal file.");
		} else if (!new File(args[0]).exists() || !new File(args[1]).exists()) {
			System.out.println("Invalid init and/or goal file.");
		} else {
			Tray tray = new Tray (args[0], args[1]);
			for (Move i: Tray.Path(tray)) {
				System.out.println(i.myMove[0]+" "+i.myMove[1]+" "+i.myMove[2]+" "+i.myMove[3]);
			}
		}
	}
}
