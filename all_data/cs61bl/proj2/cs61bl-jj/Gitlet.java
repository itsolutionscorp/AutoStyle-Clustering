import java.io.*;


public class Gitlet {

	public static Repository repo;

	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {

		File repodata = new File(".gitlet/repodata");
		if (repodata.exists()) {
			ObjectInputStream ois = null;

			try {

				ois = new ObjectInputStream(new FileInputStream(repodata));
				repo = (Repository) (ois.readObject());
			} finally {
				ois.close();
			}

		}

		try {
			if (args[0].equals("init")) {
				if (repo == null) {
					repo = new Repository();
					repo.init();
				} else {
					System.out.println("Already a Repository");
				}
			}

			if (args[0].equals("add")) {

				repo.add(args[1]);
			}

			if (args[0].equals("commit")) {

				repo.commit(args[1]);
			}
			if (args[0].equals("rm")) {

				repo.rm(args[1]);
			}

			if (args[0].equals("log")) {

				repo.log();
			}

			if (args[0].equals("global-log")) {

				repo.globalLog();
			}
			if (args[0].equals("find")) {
				repo.find(args[1]);

			}
			if (args[0].equals("status")) {

				repo.status();
			}

			if (args[0].equals("checkout")) {

				if (args.length > 2) {
					repo.checkout(Integer.parseInt(args[1]), args[2]);
				} else {
					repo.checkout(args[1]);
				}

			}
			if (args[0].equals("branch")) {

				if (repo.conflicted()) {
					System.err
							.println("Cannot do this command until the merge conflict has been resolved");
				} else {
					repo.branch(args[1]);

				}

			}
			if (args[0].equals("rmBranch")) {
				// repo.rmBranch(args[1]); Come back later
				if (repo.conflicted()) {
					System.err
							.println("Cannot do this command until the merge conflict has been resolved");
				} else {
					repo.rmBranch(args[1]);

				}
			}

			if (args[0].equals("reset")) {

				if (repo.conflicted()) {
					System.err
							.println("Cannot do this command until the merge conflict has been resolved");
				} else {
					repo.reset(Integer.parseInt(args[1]));

				}
			}
			if (args[0].equals("merge")) {

				// repo.merge(args[1]);
				if (repo.conflicted()) {
					System.err
							.println("Cannot do this command until the merge conflict has been resolved");
				} else {
					repo.merge((args[1]));
				}
			}
			if (args[0].equals("rebase")) {

				// repo.rebase(args[1]);

				if (repo.conflicted()) {
					System.err
							.println("Cannot do this command until the merge conflict has been resolved");
				} else {
					repo.rebase(args[1]);
				}
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("commands Required");
		}

		if (repo != null) {
			ObjectOutputStream out = null;
			try {
				out = new ObjectOutputStream(new FileOutputStream(repodata));
				out.writeObject(repo);
			} finally {
				out.close();
			}

		}
	}

}
