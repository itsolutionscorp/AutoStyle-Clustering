public class ConflictedMode extends Gitlet {
	public static void execute(String[] args) {
		if (args[0].equals("add") && args.length == 2) {
			add(args[1]);
		} else if (args[0].equals("commit")) {
			if (args.length == 1) {
				System.out.println("Please enter a commit message.");
			} else if (args.length != 2) {
				System.out.println("Incorrect commit command format");
			} else {
				commit(args[1]);
			}
		} else if (args[0].equals("rm") && args.length != 2) {
			remove(args[1]);
		} else if (args[0].equals("log") && args.length == 1) {
			log();
		} else if (args[0].equals("global-log") && args.length == 1) {
			globalLog();
		} else if (args[0].equals("find") && args.length == 2) {
			find(args[1]);
		} else if (args[0].equals("status") && args.length == 1) {
			status();
		} else if (args[0].equals("checkout")) {
			checkout(args);
		} else if (args[0].equals("branch") && args.length == 2) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		} else if (args[0].equals("rm-branch") && args.length == 2) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		} else if (args[0].equals("reset") && args.length == 2) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		} else if (args[0].equals("merge") && args.length == 2) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		} else if (args[0].equals("rebase") && args.length == 2) {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		} else {
			System.out.println("No command with that name exists");
		}
	}

	public static void checkout(String[] args) {
		Status myStatus = (Status) FileOperation.read(".gitlet/myStatus");
		if (args.length == 3
				&& checkoutFileFromCommit(Integer.parseInt(args[1]), args[2])) {
		} else if (checkoutFileFromCommit(myStatus.getActiveBranch()
				.getCommitId(), args[1])) {
		} else {
			System.out
					.println("Cannot do this command until the merge conflict has been resolved.");
		}
	}
}
