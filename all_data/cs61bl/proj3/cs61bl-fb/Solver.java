import java.io.File;


public class Solver {
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Invalid init and/or goal file.");
		} else if (!new File(args[0]).exists() || !new File(args[1]).exists()) {
			System.out.println("Invalid init and/or goal file.");
		} else {
			Tray tray = new Tray (args[0], args[1]);
			for (Motion i: Tray.Path(tray)) {
				StringBuilder X = new StringBuilder();
				X.append(i.myMove[0]);
				X.append(" ");
				X.append(i.myMove[1]);
				X.append(" ");
				X.append(i.myMove[2]);
				X.append(" ");
				X.append(i.myMove[3]);	
				System.out.println(X.toString());
			}
		}
	}
}
;