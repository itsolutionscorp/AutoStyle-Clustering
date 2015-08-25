import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class Commit implements Serializable {
	private ArrayList<Commit> childCommits;
	private ArrayList<Commit> parentCommits;
	private HashMap<String, SavedFile> myFiles;

	private String commitMsg;
	private int commitID;
	private Date commitTime;

	/**
	 * Basic constructor for instantiating a new Commit.
	 * 
	 * @param msg
	 *            The Commit message.
	 * @param myID
	 *            The new Commit's ID.
	 */
	public Commit(String msg, int myID) {
		commitID = myID;
		commitMsg = msg;
		commitTime = new Date();

		childCommits = new ArrayList<Commit>();
		parentCommits = new ArrayList<Commit>();
		myFiles = new HashMap<String, SavedFile>();

		buildDirectories();
	}

	/**
	 * Calls the original constructor.
	 */
	public Commit(String msg, int myID, Commit parent) {
		this(msg, myID);
		parentCommits.add(parent);
		inheritFiles(parent);
	}

	/**
	 * Calls the original constructor
	 */
	public Commit(String msg, int myID, ArrayList<Commit> parents) {
		this(msg, myID);
		for (Commit c : parents) {
			parentCommits.add(c);
		}
	}

	/**
	 * Getter function.
	 * 
	 * @return Returns this Commit's message.
	 */
	public String getCommitMsg() {
		return commitMsg;
	}

	/**
	 * Called when initializing a new commit. Sets up the directory structure.
	 */
	private void buildDirectories() {
		String[] dirsToCreate = { ".gitlet/commits/commit" + commitID };
		for (String p : dirsToCreate) {
			new File(p).mkdir();
		}
	}

	/**
	 * Given a file path, builds out the intermediate directories if necessary.
	 * 
	 * @param path
	 *            The desired path
	 */
	public static void buildPath(String path) {
		File f = new File(path);
		File parentFile = f.getParentFile();
		Path checkPath;

		checkPath = Paths.get(path);
		if (Files.exists(checkPath)) {
			return;
		} else {
			if (parentFile == null) {
				f.mkdir();
			} else {
				buildPath(parentFile.toString());
				f.mkdir();
			}
		}
	}

	/**
	 * For testing purposes. Prints out a list of all of the tracked files and
	 * their origins.
	 */
	public void printMyFilesInfo() {
		Set<String> ks = myFiles.keySet();

		if (ks.isEmpty()) {
			System.out.println("Commit " + commitID
					+ " isn't tracking any files.");
			return;
		}

		System.out.println("Printing myFiles info for commit " + commitID);
		for (String key : ks) {
			System.out.println("    " + myFiles.get(key));
		}
	}

	/**
	 * Updates the Commit's date to NOW. Was used for testing.
	 */
	public void updateDate() {
		commitTime = new Date();
	}

	/**
	 * Creates a copy of the commit.
	 * 
	 * @param id
	 *            The new ID, since IDs are unique.
	 * @return The new instance.
	 */
	public Commit copyCommit(int id) {
		Commit rtn = new Commit(commitMsg, id);
		rtn.updateDate();
		rtn.inheritFiles(this);

		return rtn;
	}

	/**
	 * Given a path in the working directory, copies the file to this the target
	 * directory. Directory structure is preserved!
	 * 
	 * @param filename
	 *            The file that we're trying to save
	 * @param targetDirectory
	 *            The target directory we're trying to save fileName into.
	 * 
	 */
	public static void saveFile(String fileName, String targetDirectory) {
		// Makes sure that all intermediate directories exist.
		File f = new File(targetDirectory + "/" + fileName);
		File f2 = new File(targetDirectory + "/" + fileName);
		if (f2.exists()) {
			f2.delete();
		}

		File p = f.getParentFile();
		buildPath(p.toString());

		try (BufferedReader reader = new BufferedReader(
				new FileReader(fileName))) {
			FileWriter fw = new FileWriter(targetDirectory + "/" + fileName,
					true);
			String curr, next;
			while ((curr = reader.readLine()) != null) {
				fw.write(curr);
			}
			fw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates a .conflicted file.
	 * 
	 * @param fileName
	 *            The file with the conflict.
	 * @param sourceDirectory
	 *            The source of the conflicted file. Need the source so that we
	 *            can make a copy.
	 */
	public static void createConflictedFile(String fileName,
			String sourceDirectory) {
		// Makes sure that all intermediate directories exist.
		String targetPath = fileName + ".conflicted";
		File target = new File(targetPath);
		try (BufferedReader reader = new BufferedReader(new FileReader(
				sourceDirectory + "/" + fileName))) {
			FileWriter fw = new FileWriter(target, true);
			String curr;
			while ((curr = reader.readLine()) != null) {
				fw.write(curr);
			}
			fw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Returns the source of the file. If the file is inherited, the path will
	 * go into another commit's file storage folder.
	 * 
	 * @param fileName
	 *            The target file.
	 * @return A path that can be used to access the file.
	 */
	public String getFileSource(String fileName) {
		if (myFiles.containsKey(fileName)) {
			return myFiles.get(fileName).getSource();
		}
		return null;
	}

	/**
	 * Given a path to something that's being staged, will extract the file and
	 * save it to the commit's storage folder.
	 * 
	 * @param stageFilePath
	 *            The target file path.
	 */
	public void saveFileToCommit(String stageFilePath) {
		String fileName = stageFilePath.split(".gitlet/stage/")[1];
		String commitFilePath = ".gitlet/commits/commit" + commitID + "/"
				+ fileName;
		File f = new File(commitFilePath);

		// build intermediate directories
		File p = f.getParentFile();
		buildPath(p.toString());

		try (BufferedReader reader = new BufferedReader(new FileReader(
				stageFilePath))) {
			FileWriter fw = new FileWriter(commitFilePath, true);
			String curr, next;
			while ((curr = reader.readLine()) != null) {
				fw.write(curr);
			}
			fw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		// Add a new SavedFile to our myFiles list.
		myFiles.put(fileName, new SavedFile(fileName, "commit" + commitID));
	}

	/**
	 * Displays commit info starting from the head and ending at the initCommit.
	 * Uses a logHelper function to recurse through parentCommits.
	 */
	public String log() {
		String rtn = "";
		rtn += toString();
		if (!parentCommits.isEmpty()) {
			rtn += "\n";
		}
		return rtn += logHelper();
	}

	private String logHelper() {
		String rtn = "";
		for (Commit e : parentCommits) {
			rtn += e.toString();
			if (!e.parentCommits.isEmpty()) {
				rtn += "\n";
			}
			rtn += e.logHelper();
		}
		return rtn;
	}

	public String toString() {
		String rtn = "";
		rtn += "===\n" + "Commit " + commitID + "\n" + commitTime + "\n"
				+ commitMsg + "\n";
		return rtn;
	}

	/**
	 * Makes a shallow copy of the parent's myFiles ArrayList.
	 * 
	 * @param parent
	 */
	public void inheritFiles(Commit parent) {
		myFiles = (HashMap<String, SavedFile>) parent.myFiles.clone();
	}

	/**
	 * Similar to getFileSource.
	 */
	public String fileSource(String fileName) {
		if (!myFiles.containsKey(fileName)) {
			throw new IllegalArgumentException();
		} else {
			return myFiles.get(fileName).getSource();
		}
	}

	/**
	 * Given a file, adds it as a file that's being tracked.
	 * 
	 * @param filePath
	 *            The file that we're trying to track.
	 */
	public void addFile(String filePath) {
		String src;
		String fileName;

		String rest = filePath.split(".gitlet/commits/")[1];
		int pos;

		for (pos = 0; pos < rest.length(); pos++) {
			if (rest.charAt(pos) == '/') {
				break;
			}
		}

		src = rest.substring(0, pos);
		fileName = rest.substring(pos + 1, rest.length());

		addFile(fileName, src);
	}

	/**
	 * The two-argument version of the file above. Starts tracking the file.
	 * 
	 * @param fileName
	 *            Name of the file (e.g. files/A.txt)
	 * @param src
	 *            Source of the file (e.g. "commit5")
	 */
	public void addFile(String fileName, String src) {
		// System.out.println("Putting " + fileName + "(" + src + ")" +
		// "into commit" + getID());
		myFiles.put(fileName, new SavedFile(fileName, src));
	}

	/**
	 * Returns the names of all the files that are being tracked by this commit.
	 * 
	 * @return Keyset of myFiles.
	 */
	public Set<String> myFileNames() {
		return ((HashMap<String, SavedFile>) myFiles.clone()).keySet();
	}

	/**
	 * Untracks a file.
	 * 
	 * @param fileName
	 *            The file that we're trying to untrack.
	 */
	public void removeFile(String fileName) {
		myFiles.remove(fileName);
	}

	/**
	 * Adds a child
	 * 
	 * @param c
	 *            The Commit we're trying to add as a child.
	 */
	public void addChild(Commit c) {
		childCommits.add(c);
	}

	/**
	 * Adds a parent.
	 * 
	 * @param p
	 *            The Commit we're trying to add as a parent.
	 */
	public void addParent(Commit p) {
		parentCommits.add(p);
	}

	/**
	 * Getter function for this Commit's ID.
	 * 
	 * @return commitID
	 */
	public int getID() {
		return commitID;
	}

	/**
	 * Returns an ArrayList containing this Commit's parents.
	 * 
	 * @return List of parents.
	 */
	public ArrayList<Commit> getParentCommits() {
		return parentCommits;
	}

	/**
	 * Returns the HashMap<String, SavedFile>.
	 * 
	 * @return Above.
	 */
	public HashMap<String, SavedFile> getMyFiles() {
		return myFiles;
	}

	/**
	 * To aid with debugging
	 * 
	 * @param indents
	 *            The level of indentation for this level.
	 */
	public void printCommitTree(int indents) {
		for (int i = 0; i < indents; i++) {
			System.out.print("    ");
		}
		System.out.println(commitID);

		Stack children;
		for (Commit c : childCommits) {
			c.printCommitTree(indents + 1);
		}
	}

	private class SavedFile implements Serializable {
		private String source;
		private String name;
		private Date date;

		/**
		 * The base constructor for SavedFile.
		 * 
		 * @param name
		 *            The file's name.
		 */
		public SavedFile(String name) {
			this(name, null);
		}

		/**
		 * Saves the name AND source if a file.
		 * 
		 * @param name
		 *            The name of the file, eg A.txt
		 * @param source
		 *            The source of the file, eg "commit5"
		 */
		public SavedFile(String name, String source) {
			this.name = name;
			this.source = source;
		}

		/* Getter method for source */
		public String getSource() {
			return source;
		}

		/* Getter method for name */
		public String getName() {
			return name;
		}

		/**
		 * @return the file path to access this file.
		 */
		public String toString() {
			String rtn = "";
			rtn += "[" + name + " (" + source + ")]";
			return rtn;
		}

		/**
		 * Copies a SavedFile.
		 * 
		 * @return A copy of this saved file
		 */
		public SavedFile copy() {
			return new SavedFile(this.name, this.source);
		}
	}
}
