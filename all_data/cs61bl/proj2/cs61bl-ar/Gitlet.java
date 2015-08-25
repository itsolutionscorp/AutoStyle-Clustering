import java.awt.List;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Gitlet implements Serializable {
	private HashMap<String, Version> myBranch;
	// key = branch name; value = point to the front of branch
	private HashMap<Integer, Version> myCommit;
	// key = commit ID; value = pointer to Version instance
	private HashMap<String, ArrayList<Integer>> messageMap;
	// key = commit message; value = arrayList of ids with that message
	private Version myHead;
	// most recently committed Version
	private ArrayList<String> stagedFiles;
	// names of files in stage folder
	private HashMap<String, String> tracked;
	// key = currently tracked file name; value = path to the commit folder that
	// stores it
	String stagePath;
	// path to stage folder
	String currentBranch;
	// name of the working branch;
	private int ID; // commit ID so far
	private ArrayList<String> untrackedFiles;
	// keep track of the untracked files from rm command
	private boolean conflictState;

	/**
	 * constructor
	 */
	public Gitlet() {
		conflictState = false;
		ID = 1;
		myBranch = new HashMap<String, Version>();
		myCommit = new HashMap<Integer, Version>();
		stagedFiles = new ArrayList<String>();
		tracked = new HashMap<String, String>();
		myHead = null;
		untrackedFiles = new ArrayList<String>();
		messageMap = new HashMap<String, ArrayList<Integer>>();
		File gitletFolder = new File(".gitlet");
		if (gitletFolder.exists()) {
			DeserializeGitlet();
		}
	}

	/**
	 * Empty constructor: create a default Gitlet instance. Primarily used for
	 * deserialization.
	 * 
	 * @param a
	 *            can be an arbitrary number, just in order to distinguish from
	 *            the normal constructor
	 */
	public Gitlet(int a) {

	}

	/**
	 * Initialize a Gitlet instance and create the .gitlet folder.
	 * 
	 * @throws IOException
	 */
	public static void init() throws IOException {
		Gitlet myGit = new Gitlet();
		File gitletFolder = new File(".gitlet");
		gitletFolder.mkdir();
		File initialCommit = new File(".gitlet/0");
		initialCommit.mkdir();
		// create a gitlet folder and an initial commit folder
		Version initCommit = new Version(0, "initial commit", null, true,
				"master");
		// create Version object
		myGit.myHead = initCommit;
		myGit.currentBranch = "master";
		myGit.myBranch.put("master", initCommit);
		myGit.myCommit.put(0, initCommit);
		// bind variables
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(0);
		myGit.messageMap.put("initial commit", a);

		File stage = new File(".gitlet/stage");
		stage.mkdir();
		myGit.stagePath = stage.getCanonicalPath();// create it and bind its
													// path to stagePath
		myGit.Serialization();

	}

	/**
	 * extract the real file name from a given expression in the format
	 * "folder/doc"
	 * 
	 * @param withFolder
	 *            filename passed in by the user; may or may not contain folder
	 *            name in the front
	 * 
	 * @return real file name without any "/" sign
	 */
	private String filenameHelper(String withFolder) {
		int pos = -1;
		for (int k = 0; k < withFolder.length() - 1; k++) {
			if (withFolder.charAt(k) == '/') {
				pos = k;
			}
		}
		if (pos < 0)
			return withFolder;
		else
			return withFolder.substring(pos + 1);
	}

	/**
	 * if @param name has been untracked, add it to the tracked list; else stage
	 * it.
	 * 
	 * @param name
	 *            name of the file; can include folder name
	 * @throws IOException
	 */
	public void add(String name) throws IOException {
		File f2 = new File(name);
		if (!f2.exists()) {
			System.out.println("File does not exist.");
		}
		// check whether the file exists

		File gitletFolder = new File(".gitlet");
		if (!gitletFolder.exists()) {
			init();
		}
		// check whether we have initialized gitlet folder

		if (untrackedFiles.contains(name)) {
			untrackedFiles.remove(name);
			String path = myHead.file().get(name);
			tracked.put(name, path);
		} else {
			File beCopy = new File(stagePath + "/" + filenameHelper(name));
			beCopy.createNewFile();
			copyFile(f2.getCanonicalPath(), beCopy.getCanonicalPath());
			stagedFiles.add(name);
		}
		Serialization();
		// copy file and add it to the stagedFiles arrayList
	}

	/**
	 * Helper method for copying files; copy file from old path to new path;
	 * overwrite if the file already exists in the new path
	 * 
	 * @param oldPath
	 * 
	 * @param newPath
	 */
	public void copyFile(String oldPath, String newPath) {
		try {
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) {
				FileInputStream oldStream = new FileInputStream(oldPath);
				FileOutputStream newStream = new FileOutputStream(newPath);
				byte[] buffer = new byte[1024];
				while ((byteread = oldStream.read(buffer)) != -1) {
					newStream.write(buffer, 0, byteread);
				}
				newStream.close();
				oldStream.close();
			}
		} catch (Exception e) {
			System.out.println("");
			e.printStackTrace();
		}
	}

	/**
	 * check whether we can commit under the current condition
	 * 
	 * @return True if we can commit, (which means that we either staged
	 *         something or untracked something) False else
	 */
	private boolean commitChecker() {
		File file = new File(stagePath);
		File[] files = file.listFiles();
		if (files.length == 0 && untrackedFiles.size() == 0) {
			System.out.println("No changes added to the commit.");
			return false;
		}
		return true;
	}

	/**
	 * first check the condition for commit, if we can commit, copy the staged
	 * files into the new commit folder and add all the tracked files to the
	 * tracked file list in this new Version object
	 * 
	 * @param message
	 *            commit message
	 * 
	 * @throws IOException
	 */
	public void commit(String message) throws IOException {
		// check whether stage folder is empty
		boolean check = commitChecker();
		if (check == false)
			return;// check whether we can commit now
		Version currentCommit = new Version(ID, message, myHead, true,
				currentBranch);// create a new Version instance
		File current = new File(".gitlet/" + ID);
		current.mkdir();// create a folder for this commit
		if (messageMap.keySet().contains(message)) {
			ArrayList<Integer> lst = messageMap.get(message);
			lst.add(ID);
		} else {
			ArrayList<Integer> lst = new ArrayList<Integer>();
			lst.add(ID);
			messageMap.put(message, lst);// add to the message map
		}
		myCommit.put(ID, currentCommit);// add to commit map
		for (String s : stagedFiles) {// find the staged folder and files inside
			File beCopy = new File(".gitlet/" + ID + "/" + filenameHelper(s));
			beCopy.createNewFile();
			copyFile(".gitlet/stage/" + filenameHelper(s),
					beCopy.getCanonicalPath());
			currentCommit
					.myMapPut(s, ".gitlet/" + ID + "/" + filenameHelper(s));
			File fl = new File(".gitlet/stage/" + filenameHelper(s));
			fl.delete();// copy the staged file into the new commit folder and
						// delete the file in stage folder
		}
		for (Map.Entry<String, String> f : tracked.entrySet()) {
			if (!stagedFiles.contains(f.getKey())) {
				currentCommit.myMapPut(f.getKey(), f.getValue());// copy tracked
																	// files
																	// into the
																	// tracked
																	// Hashmap
																	// of the
																	// new
																	// Version
			}
		}
		stagedFiles = new ArrayList<String>();
		untrackedFiles = new ArrayList<String>();
		tracked = currentCommit.file();// reset tracked files, untracked files
										// and staged files
		myHead = currentCommit;
		myBranch.put(currentBranch, currentCommit);
		ID++;
		conflictState = false;// other settings
		Serialization();
	}

	/**
	 * Remove the staged file from staging area and mark the file for untracking
	 * if it is only in the tracking list Error message otherwise
	 * 
	 * @param fileName
	 *            file name(might contain path to that file from the working
	 *            directory)
	 */
	public void rm(String fileName) {
		if (stagedFiles.contains(fileName)) {
			stagedFiles.remove(fileName);
			File f = new File(".gitlet/stage/" + filenameHelper(fileName));
			f.delete();
			// should also delete this file from the staged folder
		} else if (tracked.keySet().contains(fileName)) {
			tracked.remove(fileName);
			untrackedFiles.add(fileName);
			// remove from tracked files if not modified, which means we will no
			// longer track it in later commits
		} else {
			System.out.println("No reason to remove the file.");
		}
		Serialization();
	}

	/**
	 * return the commit history of the current head commit node
	 */
	public void log() {
		Version currVersion = myHead;
		while (currVersion.getParent() != null) {
			currVersion.log();
			currVersion = currVersion.getParent();
		}
		myCommit.get(0).log();
	}

	/**
	 * display information about all commits ever made
	 * 
	 */
	public void globalLog() {
		Iterator iter = myCommit.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			Version currVersion = (Version) entry.getValue();
			currVersion.log();
		}
	}

	/**
	 * prints out the commits that have the given commit message
	 * 
	 * @param commitmsg
	 *            given commit message
	 */
	public void find(String commitmsg) {
		if (!messageMap.containsKey(commitmsg)) {
			System.out.println("Found no commit with that message.");
		}
		ArrayList<Integer> commitID = messageMap.get(commitmsg);
		for (int ID : commitID) {
			System.out.println(ID);
		}
	}

	/**
	 * display what branches currently exist, what files have been staged or
	 * marked for untracking also mark the current branch with a "*"
	 * 
	 */
	public void status() {
		String bName;
		System.out.println("=== Branches ===");
		Iterator iter = myBranch.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			bName = (String) entry.getKey();

			if (currentBranch.equals(bName)) {
				System.out.println("*" + bName);
			} else {
				System.out.println(bName);
			}
		}
		System.out.println("");
		System.out.println("=== Staged Files ===");
		for (int i = 0; i < stagedFiles.size(); i++) {
			System.out.println(stagedFiles.get(i));
		}
		System.out.println("");
		System.out.println("=== Files Marked for Untracking ===");
		for (int i = 0; i < untrackedFiles.size(); i++) {
			System.out.println(untrackedFiles.get(i));
		}
	}

	/**
	 * When the user is trying to recover/chekout a file, create the path if the
	 * user somehow deleted the original folder does nothing otherwise
	 * 
	 * @param fileName
	 *            the path to check
	 */
	public void checkoutHelper(String fileName) {
		int posafter = 0;
		for (int k = 0; k < fileName.length(); k++) {
			if (fileName.charAt(k) == '/') {
				posafter = k;
				File f = new File(fileName.substring(0, posafter));
				if (!f.exists()) {
					f.mkdir();
				}
			}
		}
	}

	/**
	 * takes all files in the commit at the head of the given branch and put
	 * then in the working directory, move head to this branch If not a branch
	 * name, takes the version of the file as it exists in head commit
	 * 
	 * @param name
	 *            branch name/file name
	 * 
	 * @throws IOException
	 */
	public void checkout(String name) throws IOException {
		if (myBranch.keySet().contains(name)) {
			if (conflictState) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}
			if (name == currentBranch) {
				System.out.println("No need to checkout the current branch.");
				return;
			}
			for (String s : myBranch.get(name).file().keySet()) {
				checkoutHelper(s);
				File before = new File(myBranch.get(name).file().get(s));
				File after = new File("./" + s);
				copyFile(before.getCanonicalPath(), after.getCanonicalPath());
			}
			currentBranch = name;
			myHead = myBranch.get(name);
			tracked = myHead.file();

		} else if (myHead.file().keySet().contains(name)) {
			checkoutHelper(name);
			String path = myHead.file().get(name);
			File workingDirectory = new File(".");
			File result = new File(workingDirectory.getCanonicalPath() + "/"
					+ name);
			copyFile(path, result.getCanonicalPath());
		} else {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
		}
		Serialization();
	}

	/**
	 * takes the version of the file as it exists in the commit with the given
	 * id and puts it in the working directory
	 * 
	 * @param commitID
	 *            look for files in that commit
	 * @param fileName
	 *            file to look for
	 * 
	 * @throws IOException
	 */
	public void checkout(int commitID, String fileName) throws IOException {
		if (!myCommit.containsKey(commitID)) {
			System.out.println("No commit with that id exists.");
			return;
		} else {
			if (!myCommit.get(commitID).file().containsKey(fileName)) {
				System.out.println("File does not exist in that commit.");
				return;
			} else {
				File before = new File(myCommit.get(commitID).file()
						.get(fileName));
				checkoutHelper(fileName);
				File after = new File("./" + fileName);

				copyFile(before.getCanonicalPath(), after.getCanonicalPath());
			}
		}
		Serialization();
	}

	/**
	 * create a new branch with the given name, actually a pointer to the
	 * current head node
	 * 
	 * @param branchName
	 *            name of the new branch
	 */
	public void branch(String branchName) {
		if (myBranch.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
		} else {
			myBranch.put(branchName, myHead);
		}
		Serialization();
	}

	/**
	 * Deletes the branch pointer with the given name
	 * 
	 * @param branchName
	 *            branch to delete
	 */
	public void rmBranch(String branchName) {
		if (branchName.equals(myHead.branchName())) {
			System.out.println("Cannot remove the current branch.");
		} else if (myBranch.containsKey(branchName)) {
			myBranch.remove(branchName);
		} else {
			System.out.println("A branch with that name does not exist.");
		}
		Serialization();
	}

	/**
	 * checks out the files tracked by the given commit and move the branch head
	 * pointer to that commit
	 * 
	 * @param commitId
	 *            commit to checkout
	 * 
	 * @throws IOException
	 */
	public void reset(int commitId) throws IOException {
		if (!myCommit.containsKey(commitId)) {
			System.out.println("No commit with that id exists.");
		} else {
			for (String fileName : myCommit.get(commitId).file().keySet()) {
				File before = new File(myCommit.get(commitId).file()
						.get(fileName));
				File after = new File("./" + fileName);
				copyFile(before.getCanonicalPath(), after.getCanonicalPath());
			}
			myHead = myCommit.get(commitId);
			myBranch.put(currentBranch, myHead);
			tracked = myHead.file();
			stagedFiles = new ArrayList<String>();
			Serialization();
		}
	}

	/**
	 * Merges files from the given branch into the current branch and deal with
	 * conflicting state problems
	 * 
	 * @param branch
	 *            branch to move to
	 * 
	 * @throws IOException
	 */
	public void merge(String branch) throws IOException {
		Version splitPoint = splitHelper(myHead, myBranch.get(branch));

		for (String s : myBranch.get(branch).file().keySet()) {
			String curr = myHead.file().get(s);
			String splitFile = splitPoint.file().get(s);
			String branchFile = myBranch.get(branch).file().get(s);
			if (!branchFile.equals(splitFile)) {
				if (curr == null) {
					// whether or not splitFile==null. we do the same thing:
					// copy the file in the branch to the working directory and
					// stage it!
					checkout(myBranch.get(branch).getID(), s);
					add(s);
				} else {
					if (curr.equals(splitFile)) {
						// current branch: unmodified,
						checkout(myBranch.get(branch).getID(), s);
						add(s);
					} else {
						// current:modified, branch: modified
						File before = new File(myBranch.get(branch).file()
								.get(s));
						File after = new File("./" + s + ".conflicted");
						conflictState = true;
						copyFile(before.getCanonicalPath(),
								after.getCanonicalPath());
					}
				}
			}
			// else branch is unmodified, do not do anything
		}
		if (!conflictState) {
			// if there is no conflicting files
			commit("Merged [" + myHead.branchName() + "] with [" + branch
					+ "].");
		} else {
			System.out.println("Encountered a merge conflict.");
		}
		Serialization();
	}

	/**
	 * finds the split point of two given commit nodes
	 * 
	 * @param commit1
	 * @param commit2
	 * 
	 * @return the nearest common parent (a commit node)
	 */
	private Version splitHelper(Version commit1, Version commit2) {
		if (commit1.getID() == commit2.getID()) {
			return commit1;
		} else if (commit1.getID() < commit2.getID()) {
			return splitHelper(commit1, commit2.getParent());
		} else
			return splitHelper(commit1.getParent(), commit2);
	}

	/**
	 * copy the current branch and attach it to the head of the given branch and
	 * inherit all the propagated files
	 * 
	 * @param branch
	 *            name of the branch to move to
	 * 
	 * @throws IOException
	 */
	public void rebase(String branch) throws IOException {
		// failure cases
		if (!myBranch.containsKey(branch)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (myBranch.get(branch).getID() == myHead.getID()) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		Version splitPoint = splitHelper(myHead, myBranch.get(branch));

		if (splitPoint.getID() == myBranch.get(branch).getID()) {
			System.out.println("Already up-to-date.");
			return;
		} else if (splitPoint.getID() == myHead.getID()) {
			// move the pointer of the given branch to myHead b/c given branch
			// is in myHead's history
			myBranch.put(myHead.branchName(), myBranch.get(branch));
			myHead = myBranch.get(branch);
			for (String f : myHead.file().keySet()) {
				File before = new File(myHead.file().get(f));
				checkoutHelper(f);
				File after = new File(f);
				copyFile(before.getCanonicalPath(), after.getCanonicalPath());
			}
		} else {
			// copy all the versions in current branch and attach them to given
			// branch, move myHead to the new myHead version
			Version count = myHead;
			int Counter = 0;
			HashMap<String, String> propagate = new HashMap<String, String>();
			// get the propagated tracked files
			for (String s : myBranch.get(branch).file().keySet()) {
				if (splitPoint.file().get(s) != myBranch.get(branch).file()
						.get(s)) {
					propagate.put(s, myBranch.get(branch).file().get(s));
				}
			}
			while (splitPoint.getID() != count.getID()) {
				Counter++;
				count = count.getParent();
			}// count the number of nodes to copy
			int finalID = ID + Counter - 1;
			// assigning ID in reverse order
			Version curr = myHead;
			int i = 0;
			Version result = new Version(finalID - i, curr.message(), null,
					curr.isBranchHead(), curr.branchName());
			myHead = result;// set myHead to the front of the rebased branch
			myBranch.put(currentBranch, result);
			rebaseCopyHelper(finalID - i, curr, result, propagate, splitPoint);
			// start to copy node
			for (String f : result.file().keySet()) {
				File before = new File(result.file().get(f));
				checkoutHelper(f);
				File after = new File(f);
				copyFile(before.getCanonicalPath(), after.getCanonicalPath());
			}
			while (curr.getParent().getID() != splitPoint.getID()) {
				i++;
				curr = curr.getParent();// iterate to the new version to be
										// copied
				Version temp = new Version(finalID - i, curr.message(), null,
						curr.isBranchHead(), curr.branchName());// initiate
																// version(did
																// not create
																// folder in the
																// folder
				rebaseCopyHelper(finalID - i, curr, temp, propagate, splitPoint);
				result.setParent(temp);// make the newly created version the
										// parent of the old result
				result = temp;// update the result to the new version

			}
			result.setParent(myBranch.get(branch));
			ID = finalID;// update ID in Gitlet
		}

		Serialization();
	}

	/**
	 * copy the files from curr commit folder to temp commit folder and add the
	 * tracked and propagated files and paths to that new temp commit node
	 * 
	 * @param id
	 *            new id that the temp commit should have
	 * @param curr
	 *            commit node to copy
	 * @param temp
	 *            new commit node to be constructed
	 * @param propagate
	 *            Hashmap of the possible propagated files with name as key,
	 *            path in commit folder as value
	 * @param splitPoint
	 *            the split point Commit node, parameterized in order to track
	 *            file modifications in head and given branch since split point
	 * 
	 * @throws IOException
	 */
	private void rebaseCopyHelper(int id, Version curr, Version temp,
			HashMap<String, String> propagate, Version splitPoint)
			throws IOException {
		File folder = new File(".gitlet/" + ((Integer) (id)).toString());
		folder.mkdir();// create folder
		// copy files into the new version

		for (String f : curr.file().keySet()) {
			if (!curr.file().get(f).equals(curr.getParent().file().get(f))) {
				File after = new File(".gitlet/" + ((Integer) id).toString()
						+ "/" + filenameHelper(f));
				File before = new File(curr.file().get(f));
				copyFile(before.getCanonicalPath(), after.getCanonicalPath());
				temp.file().put(
						f,
						".gitlet/" + ((Integer) id).toString() + "/"
								+ filenameHelper(f));
			} else {
				temp.file().put(f, curr.file().get(f));
			}
		}
		// copy file tracking information (propagation)
		for (String name : propagate.keySet()) {
			if (curr.file().get(name) == null
					|| curr.file().get(name)
							.equals(splitPoint.file().get(name))) {
				temp.file().put(name, propagate.get(name));
			}
		}
	}

	/**
	 * deserialize a Gitlet object from ".gitlet/Serialization.ser" and the
	 * Version instances associated with it
	 * 
	 * @return the deserialized gitlet obj
	 */
	public static Gitlet DeserializeGitlet() {
		Gitlet myGitlet = null;
		try {
			FileInputStream fileIn = new FileInputStream(
					".gitlet/Serialization.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			myGitlet = (Gitlet) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("comloyee class not found");
			c.printStackTrace();
			return null;
		}

		return myGitlet;
	}

	/**
	 * serialize a gitlet obj and Version objs associated, to be called at the
	 * end of each operation
	 */
	public void Serialization() {
		Gitlet myGitlet = new Gitlet(1);
		myGitlet.myBranch = this.myBranch;
		myGitlet.myCommit = this.myCommit;
		myGitlet.messageMap = this.messageMap;
		myGitlet.myHead = this.myHead;
		myGitlet.stagedFiles = this.stagedFiles;
		myGitlet.tracked = this.tracked;
		myGitlet.stagePath = this.stagePath;
		myGitlet.ID = this.ID;
		myGitlet.currentBranch = this.currentBranch;
		myGitlet.untrackedFiles = this.untrackedFiles;
		myGitlet.conflictState = this.conflictState;
		try {
			FileOutputStream fileOut = new FileOutputStream(
					".gitlet/Serialization.ser");
			ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
			outStream.writeObject(myGitlet);
			outStream.close();
			fileOut.close();

		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	/**
	 * check whether .gitlet folder exists and switch command lines into actual
	 * Gitlet operation also check conflicting states
	 * 
	 * @param args
	 *            command lines that the user passes in
	 */
	public static void main(String[] args) {
		File folder = new File(".gitlet");
		if (folder.exists() && args[0].equals("init")) {
			System.out
					.println("A gitlet version control system already exists in the current directory.");
		}
		if (!folder.exists()) {
			if (!args[0].equals("init")) {
				System.out.println("have not initialized yet!");
				return;
			} else {
				try {
					Gitlet.init();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
		}
		Gitlet myGit = Gitlet.DeserializeGitlet();

		if (myGit.conflictState) {
			String[] allowedCommands = { "add", "rm", "commit", "checkout",
					"log", "global-log", "find", "status" };
			ArrayList<String> allowed = new ArrayList<String>();
			for (String s : allowedCommands) {
				allowed.add(s);
			}
			if (!allowed.contains(args[0])) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}
		}
		try {
			switch (args[0]) {
			case "add":
				myGit.add(args[1]);
				break;
			case "commit":
				if (args.length < 2)
					System.out.println("Please enter a commit message.");
				else
					myGit.commit(args[1]);
				break;
			case "rm":
				myGit.rm(args[1]);
				break;
			case "log":
				myGit.log();
				break;
			case "global-log":
				myGit.globalLog();
				break;
			case "find":
				myGit.find(args[1]);
				break;
			case "status":
				myGit.status();
				break;
			case "checkout":
				if (args.length == 2) {
					myGit.checkout(args[1]);
				} else {
					myGit.checkout(Integer.parseInt(args[1]), args[2]);
				}
				break;
			case "branch":
				myGit.branch(args[1]);
				break;
			case "rm-branch":
				myGit.rmBranch(args[1]);
				break;
			case "reset":
				myGit.reset(Integer.parseInt(args[1]));
				break;
			case "merge":
				myGit.merge(args[1]);
				break;
			case "rebase":
				myGit.rebase(args[1]);
				break;
			}
		} catch (IOException e) {
			return;
		}

	}
}
