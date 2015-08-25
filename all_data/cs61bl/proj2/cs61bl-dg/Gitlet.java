import java.util.Arrays;

public class Gitlet {

	/**
	 * Parses input and calls methods of CommitPath based on command.
	 *
	 * @author Joseph Simonian, Yiding Jiang, Dan March, Kinsa Durst
	 */

	public static void main(String... args) {

		if (args == null) {
			System.out.println("Please enter a command.");
			return;
		}

		CommitPath path = CommitPath.serialRead();

		switch (args[0]) {
		case "init":
			path.init();
			break;
		case "add":
			path.add(args[1]);
			break;
		case "commit":
			if (args.length == 0) {
				System.out.println("Please enter a commit message.");
			} else {
				path.commit(args[1]);
			}
			break;
		case "rm":
			path.remove(args[1]);
			break;
		case "log":
			path.log();
			break;
		case "global-log":
			path.globalLog();
			break;
		case "find":
			path.find(args[1]);
			break;
		case "status":
			path.status();
			break;
		case "branch":
			path.branch(args[1]);
			break;
		case "checkout":
			if (args.length > 2) {
				path.checkout(args[1], args[2]);
			} else {
				path.checkout(args[1]);
			}
			break;
		case "rm-branch":
			path.removeBranch(args[1]);
			break;
		case "reset":
			path.reset(args[1]);
			break;
		case "merge":
			path.merge(args[1]);
			break;
		case "rebase":
			path.rebase(args[1]);
			break;
		default:
			System.out.println("No command with that name exists.");
			break;
		}

		CommitPath.serialWrite(path);

	}

}
