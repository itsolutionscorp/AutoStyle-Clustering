import java.io.*;
import java.util.Arrays;

/**
 * Class created in order to run the Gitlet program. Deserializes the gitlet
 * object from the folder if it exists. Takes in commands from the command
 * prompt and executes the appropriate method in GitTree. Lastly, serializes the
 * object in the .gitlet folder to save the state for the next command.
 * 
 * @author Albert Pham cs61bl-bp
 * @author Henry Gong cs61bl-bk
 * @author Patrick Zhang cs61bl-bo
 */
public class Gitlet {
	public static void main(String[] args) {
		GitTree gitlet = deserialize();
		if (args.length == 0) {
			System.out.println("Please enter a command.");
		} else {
			String command = args[0];
			try {
				if (command.equals("init")) {
					if (gitlet == null) {
						gitlet = new GitTree();
					} else {
						System.out.println("A gitlet version control system "
								+ "already exists in the current directory.");
					}
				} else if (command.equals("add")) {
					gitlet.add(args[1]);
				} else if (command.equals("commit")) {
					if (args.length == 2 && args[1].length() > 0)
						gitlet.commit(args[1]);
					else
						System.out.println("Please enter a commit message.");
				} else if (command.equals("rm")) {
					gitlet.rm(args[1]);
				} else if (command.equals("log")) {
					gitlet.log();
				} else if (command.equals("global-log")) {
					gitlet.globalLog();
				} else if (command.equals("find")) {
					gitlet.find(args[1]);
				} else if (command.equals("status")) {
					gitlet.status();
				} else if (command.equals("checkout")) {
					if (args.length == 3) {
						gitlet.checkout(Integer.parseInt(args[1]), args[2]);
					} else {
						gitlet.checkout(args[1]);
					}
				} else if (gitlet.getConflicted()) {
					System.out
							.println("Cannot do this command until the merge conflict has been resolved.");
				} else if (command.equals("branch")) {
					gitlet.branch(args[1]);
				} else if (command.equals("rm-branch")) {
					gitlet.rmBranch(args[1]);
				} else if (command.equals("reset")) {
					gitlet.reset(Integer.parseInt(args[1]));
				} else if (command.equals("merge")) {
					gitlet.merge(args[1]);
				} else if (command.equals("rebase")) {
					gitlet.rebase(args[1]);
				} else {
					System.out.println("No command with that name exists.");
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid arguments to command.");
			}
			if (gitlet != null) {
				serialize(gitlet);
			}
		}
	}

	/**
	 * Deserializes the gitlet.ser file if it exists and returns null otherwise.
	 * 
	 * @return The previous GitTree since Gitlet has been last executed.
	 */
	public static GitTree deserialize() {
		File file = new File(".gitlet/gitlet.ser");
		if (file.exists()) {
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				GitTree gitlet = (GitTree) ois.readObject();
				ois.close();
				fis.close();
				return gitlet;
			} catch (IOException e) {
				System.err.println("Could not create ObjectInputStream");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.err.println(".ser file is not a GitTree");
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Saves the state of the GitTree at the end of the command to gitlet.ser.
	 * 
	 * @param gitlet
	 *            GitTree to save in gitlet.ser.
	 */
	public static void serialize(GitTree gitlet) {
		try {
			FileOutputStream fos = new FileOutputStream(".gitlet/gitlet.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(gitlet);
			oos.close();
			fos.close();
		} catch (IOException e) {
			System.err.println("Could not serialize the GitTree.");
			e.printStackTrace();
		}
	}

}
