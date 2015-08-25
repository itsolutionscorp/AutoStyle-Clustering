import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Perfoms file system manipulation - copies and deletes files between
 * directories. All methods are called by methods of CommitPath. Note that
 * GitletController does not store the tree of commits in any way, that is the
 * job of CommitPath.
 */
public class GitletController {

	/**
	 * Moves files from place to place in the directory system. Is used to move
	 * files both into and out of the .gitlet directory. If the file already in
	 * the directory it is to be copied to, delete it.
	 *
	 * @param from
	 *            a string representing the path to the file to be copied
	 * @param to
	 *            a string representing the path to the location the file is to
	 *            be copied to
	 */
	public static void fileCopy(String from, String to) {
		File original = new File(from);
		File copy = new File(to);
		if (!(copy.exists())) {
			copy.mkdirs();
		}
		try {
			Files.copy(original.toPath(), copy.toPath(),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (original.listFiles() == null) {
			return;
		}
		for (File file : original.listFiles()) {
			String newFrom = file.toString();
			String newTo = to + newFrom.substring(from.length());
			fileCopy(newFrom, newTo);
		}
	}

	/**
	 * Clears out a directory in anticipation for copying files in. Generally
	 * used to clear the .staging directory, also is used in the remove and
	 * checkout methods. Note that this method does not delete the directory
	 * itself, only all of its files and subdirectories.
	 *
	 * @param file
	 *            a string representing the path to the directory to be cleared
	 */
	public static void clearDirectory(String file) {
		File toClear = new File(file);
		if (toClear.listFiles() == null) {
			return;
		}
		for (File f : toClear.listFiles()) {
			clearDirectory(f.toString());
			try {
				Files.delete(f.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Initializes the .gitlet directory by creating it and creating the
	 * .staging directory within it. Also creates an initial commit, with no
	 * files.
	 *
	 * @param file
	 *            a string representing the id of the initial commit
	 */
	public static void init(String initialID) {
		File gitlet = new File(".gitlet/.staging/");
		File initialcommit = new File(".gitlet/" + initialID);
		gitlet.mkdirs();
		initialcommit.mkdir();
	}

	/**
	 * Called by the add method of CommitPath. Copies files from the working
	 * directory to the staging area (the .gitlet/.staging directory).
	 *
	 * @param file
	 *            a string representing the path to the file to be staged.
	 */
	public static void add(String file) {
		fileCopy(file, ".gitlet/.staging/" + file);
	}

	/**
	 * Called by the commit method of CommitPath. Copies files from the staging
	 * area into a new subdirectory of .gitlet, with a directory name that is
	 * the same as the string representation of the id of the new commit.
	 *
	 * @param commit
	 *            a string representing the id of the new commit
	 */
	public static void commit(String commit) {
		File staging = new File(".gitlet/.staging");
		File location = new File(".gitlet/" + commit);
		location.mkdir();
		File[] contents = staging.listFiles();
		for (int i = 0; i < contents.length; i++) {
			String name = contents[i].getName();
			fileCopy(".gitlet/.staging/" + name, ".gitlet/" + commit + name);
		}
		clearDirectory(".gitlet/.staging/");
	}

	/**
	 * Called by the remove method of CommitPath, if the file to be removed is
	 * already staged. Deletes the file from the staging area (the
	 * .gitlet/.staging directory), does nothing else. else.
	 *
	 * @param file
	 *            a string representing the name of the file to be removed.
	 */
	public static void remove(String file) {
		clearDirectory(".gitlet/.staging/" + file);
		File toremove = new File(".gitlet/.staging/" + file);
		toremove.delete();
	}

	/**
	 * Called by the checkout method of CommitPath. Copies a file from a
	 * specified commit to the working directory, by clearing the directory in
	 * the working directory and then copying the file over.
	 *
	 * @param commitID
	 *            an integer representing the id of the commit that the file
	 *            will be pulled from
	 * @param file
	 *            a string representing the name of the file to be copied
	 */
	public static void checkout(int commitID, String file) {
		clearDirectory(file);
		fileCopy(".gitlet/" + commitID + "/" + file, file);
	}

	/**
	 * Called by the merge method of CommitPath, if .conflicted files are
	 * generated. Creates a .conflicted version of a file in the working
	 * directory with data identical to that of the file in the specified
	 * commit. A file named wug.txt in the specified commit would thus be copied
	 * onto the working directory as wug.txt.conflicted.
	 *
	 * @param commitID
	 *            an integer representing the id of the commit that the file
	 *            will be pulled from
	 * @param file
	 *            a string representing the name of the file to be copied
	 */
	public static void mergeConflicted(int commitID, String file) {
		fileCopy(".gitlet/" + commitID + "/" + file, file + ".conflicted");
	}

}
