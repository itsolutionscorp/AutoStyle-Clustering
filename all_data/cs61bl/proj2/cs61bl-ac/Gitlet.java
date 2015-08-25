import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Holds the main method of the Gitlet project, initializes the database class,
 * takes in user commands and calls relevant functions
 */

public class Gitlet {
	/**
	 * Initializes the .gitlet folder, staged, removed and our data-holding
	 * object. No parameters.
	 * 
	 * @return the Database that will later be saved and updated through out
	 * 
	 *         Prints an error message if there already is a .gitlet, but still
	 *         returns a new database.
	 * @throws IOException
	 */
	public static Database init() throws IOException {
		Database myDB = new Database();
		File hiddenGit = new File(".gitlet");
		if (!hiddenGit.exists()) {
			hiddenGit.mkdirs();
			File staged = new File(".gitlet/staged");
			staged.mkdirs();
			myDB.commit("initial commit");
			return myDB;
		} else {
			System.out
					.println("A gitlet version control system already exists in the current directory.");
		}
		return null;
	}

	/**
	 * The main method of the project: It interprets user commands and calls the
	 * Database class to fulfill them It catches various errors It updates
	 * myData.ser to keep track of all our changes
	 * 
	 * @param String
	 *            args[] a list of strings that will be interpreted as method
	 *            calls and their arguments
	 */
	public static void main(String args[]) {
		try {
			String myarg0 = args[0];
			File f = new File(".gitlet/myData.ser");
			if (!f.exists()) {
				if (myarg0.equals("init")) {
					Database a = init();
					ObjectOutputStream out = new ObjectOutputStream(
							new FileOutputStream(".gitlet/myData.ser"));
					out.writeObject(a);
					out.close();
				}
			} else {
				ObjectInputStream in = new ObjectInputStream(
						new FileInputStream(".gitlet/myData.ser"));
				Database a = (Database) in.readObject();
				in.close();
				switch (args[0]) {
				case "init":
					if (a.getConflictedState()) {
						System.out.println("Not allowed in conflicted state.");
					} else {
						init();
					}
					break;
				case "add":
					a.add(args[1]);
					break;
				case "commit":
					if (args.length == 2) {
						a.commit(args[1]);
					} else {
						System.out.println("Please write a commit message.");
					}
					break;
				case "rm":
					a.rm(args[1]);
					break;
				case "log":
					a.log();
					break;
				case "global-log":
					a.global_log();
					break;
				case "find":
					a.find(args[1]);
					break;
				case "status":
					a.status();
					break;
				case "checkout":
					if (args.length == 3)
						a.checkoutFileIndex(args[2], Integer.parseInt(args[1]));
					else if (args.length == 2)
						a.checkout(args[1]);
					break;
				case "branch":
					if (a.getConflictedState()) {
						System.out.println("Not allowed in conflicted state.");
					} else {
						a.branch(args[1]);
					}
					break;
				case "rm-branch":
					if (a.getConflictedState()) {
						System.out.println("Not allowed in conflicted state.");
					} else {
						a.rm_branch(args[1]);
					}
					break;
				case "reset":
					if (a.getConflictedState()) {
						System.out.println("Not allowed in conflicted state.");
					} else {
						a.reset(Integer.parseInt(args[1]));
					}
					break;
				case "merge":
					if (a.getConflictedState()) {
						System.out.println("Not allowed in conflicted state");
					} else {
						a.merge(args[1]);
					}
					break;
				case "rebase":
					if (a.getConflictedState()) {
						System.out.println("Not allowed in conflicted state");
					} else {
						a.rebase(args[1]);
					}
					break;
				default: 
					System.out.println("No command with that name exists.");
				}
				File fileSer = new File(".gitlet/myData.ser");
				Files.delete(fileSer.toPath());
				ObjectOutputStream out = new ObjectOutputStream(
						new FileOutputStream(".gitlet/myData.ser"));
				out.writeObject(a);
				out.close();
			}
		} catch (IOException e) {
			System.out.println("IO exception.");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found exception.");
		} catch (NullPointerException e) {
			System.out.println("Please enter a command.");
			e.printStackTrace();
		}
	}
}