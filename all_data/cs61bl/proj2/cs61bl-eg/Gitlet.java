import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;

public class Gitlet implements Serializable {
	/**
	 * Creates the file ".gitlet" in the current directory. Raises error if
	 * ".gitlet" already exists Commit contains no files and has message
	 * "initial commit" Initializes all of the instance variables
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, branch> myEnds;
	private String branch;
	private HashMap<String, File> staged;
	private int idNum = 0;
	private HashMap<String, ArrayList<Integer>> messages;
	private HashMap<String, File> myOrigs;
	private ArrayList<String> untracked;
	private HashMap<Integer, commit> commits;
	private boolean conflicted;
	private HashMap<String, Remote> remoteNames;

	/**
	 * Creates the file ".gitlet" in the current directory. Raises error if
	 * ".gitlet" already exists Commit contains no files and has message
	 * "initial commit" Initializes all of the instance variables
	 */
	public void init() {
		if (!conflicted) {
			File f = new File(".gitlet");
			if (f.exists()) {
				System.err.println("A gitlet version control system already exists in the current directory.");
			} else {
				f.mkdir();
				new File(".gitlet/staged/").mkdir();
				branch = "master";
				myEnds = new HashMap<String, branch>();
				commit c = new commit("initial commit");
				myEnds.put(branch, new branch(c, "master"));
				staged = new HashMap<String, File>();
				messages = new HashMap<String, ArrayList<Integer>>();
				ArrayList<Integer> arr = new ArrayList<Integer>();
				arr.add(c.getId());
				messages.put(c.getMessage(), arr);
				myOrigs = new HashMap<String, File>();
				untracked = new ArrayList<String>();
				commits = new HashMap<Integer, commit>();
				commits.put(c.id, c);
				conflicted = false;
				remoteNames = new HashMap<String, Remote>();
			}
		} else
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
	}

	/**
	 * Checks to see if file exists If file is marked for untracking unmark the
	 * file: untracked.remove(path) If file does not exist: print system error
	 * If file exist: create a file ".gitlet/staged/", put String into HashMap
	 * 
	 * @param path
	 *            takes in a string path that is the name of the file we want to
	 *            add
	 */
	public void add(String path) {
		File f = new File(path);
		if (untracked.contains(path)) {
			untracked.remove(path);
			return;
		}
		if (!f.exists()) {
			System.err.println("File does not exist.");
			System.exit(1);
		}
		myOrigs.put(path, f);
		File f2 = new File(".gitlet/staged/" + path);
		f2.getParentFile().mkdirs();
		staged.put(path, f2);
		try {
			Files.copy(f.toPath(), f2.toPath());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Creates a HashMap file for each commit made into HashMap committed If
	 * staged size = 0, print system error Else Stores files in distinct
	 * directories with IdNum after each commit Change tracking status
	 * 
	 * @param message
	 *            Takes in a string "message" that will be stored for each
	 *            particular commit
	 */
	public void commit(String message) {
		// File[] f = new File(".gitlet/staged/").listRoots();
		HashMap<String, File> committed = new HashMap<String, File>();
		if (staged.values().size() < 1) {
			System.err.println("No changes added to the commit");
		} else {
			File temp = new File(
					".gitlet/" + idNum/*
										 * + branch + "/commit" + myEnds.
										 * get(branch).getCommit() .size()
										 */ + "/");
			temp.mkdirs();
			Object[] t = staged.keySet().toArray();
			for (int i = 0; i < t.length; i++) { // for loop to loop through
													// every
													// single thing in the
													// staged
													// folder
													// String path =
													// f[i].getPath();
				// myEnds.get(branch).addToTracked(name);
				committed.put((String) t[i], new File(temp.toString() + "/" + t[i]));
				committed.get(t[i]).getParentFile().mkdirs();
				try {
					Files.copy(staged.get(t[i]).toPath(), committed.get(t[i]).toPath());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
				new File(staged.get(t[i]).toString()).delete();
			}
			commit c = new commit(message, committed, myEnds.get(branch).getCommit());
			myEnds.get(branch).rePoint(c);
			commits.put(c.id, c);
			staged = new HashMap<String, File>();
			if (messages.containsKey(c.getMessage())) {
				ArrayList<Integer> arr = messages.get(c.getMessage());
				arr.add(c.getId());
				messages.remove(c.getMessage());
				messages.put(c.getMessage(), arr);
			} else {
				ArrayList<Integer> arr = new ArrayList<Integer>();
				arr.add(c.getId());
				messages.put(c.getMessage(), arr);
			}
			untracked = new ArrayList<String>();
			conflicted = false;
		}
	}

	/**
	 * Checks if staged folder contains a specific file If staged foler contains
	 * file, removes it If staged file is empty or does not contain the file:
	 * print system error If the staged folder contains the file: Removes from
	 * staged folder, adds to untracked ArrayList
	 * 
	 * @param s
	 *            a string that is used as the key to locate if the file exists
	 *            in the staged folder
	 */
	public void rm(String s) { // ASK ASK ASK
		if ((myEnds.get(branch).myCommit.myFiles == null && !staged.containsKey(s)) || untracked.contains(s))
			System.out.println("No reason to remove the file.");
		else if (!staged.containsKey(s) && myEnds.get(branch).myCommit.myFiles != null
				&& !myEnds.get(branch).myCommit.myFiles.containsKey(s)) {
			System.out.println("No reason to remove the file.");
		} else if (staged.containsKey(s)) {
			staged.get(s).delete();
			staged.remove(s);
		} else {
			untracked.add(s);
		}
	}

	/**
	 * Returns a printed list of: the commit, date and time made, the commit
	 * message
	 */
	public void log() {
		Iterator<commit> itr = myEnds.get(branch).myCommit.iterator();
		while (itr.hasNext()) {
			((commit) itr.next()).printInfo();
		}
	}

	/**
	 * Returns a printed list of all the commits made inside the entire
	 * ".gitlet" folder Not printed in particular order
	 */
	public void global_log() {
		for (commit c : commits.values()) {
			c.printInfo();
		}
	}

	/**
	 * Prints id of commit that contains the given commit message Recursively
	 * checks all
	 * 
	 * @param message
	 *            a string that is used as a key to find a commit id
	 */
	public void find(String message) {
		if (messages.containsKey(message)) {
			for (int i = 0; i < messages.get(message).size(); i++) {
				System.out.println("id: " + messages.get(message).get(i));
			}
		} else
			System.out.println("Found no commit with that message.");
	}

	/**
	 * Prints out the status of the current existing branches, staged files, amd
	 * untracked files Indicates which branch the user is currently looking into
	 * marked by a * on the branch
	 */
	public void status() {
		System.out.println("=== Branches ===");
		ArrayList<branch> arr = new ArrayList<branch>(myEnds.values());
		for (int i = 0; i < arr.size(); i++) {
			System.out.print(((Gitlet.branch) arr.get(i)).getName());
			if (((Gitlet.branch) arr.get(i)).getName().equals(branch))
				System.out.print("*");
			System.out.println();
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		ArrayList<String> arr2 = new ArrayList<String>(staged.keySet());
		for (String s : arr2) {
			System.out.println(s);
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (String s : untracked) {
			System.out.println(s);
		}
		System.out.println();
	}

	/**
	 * Hepler method for checkout: if Hashmap myEnds is not null continue to
	 * checkout1 if Hashmap myEnds in null continue to checkout
	 * 
	 * @param s
	 *            a string that is used to identify the branch name
	 */
	public void checkoutHelper(String s) {
		if (myEnds.get(s) != null)
			checkout1(s);
		else
			checkout(s);
	}

	/**
	 * 
	 * If conficted print system error If not conflicted checks if HashMap
	 * myEnds does not contain given parameter assosicated with file. if myEnds
	 * does not contain given parameter: print system error else use try catch
	 * get files in commit at head of given branch and copy into working
	 * directory overwrites version of files already there
	 * 
	 * @param name
	 *            a string that is used as a key to indicate which file is being
	 *            used
	 */
	public void checkout(String name) {
		if (!conflicted) {
			if (!myEnds.get(branch).myCommit.myFiles.containsKey(name)) {
				System.out.println("File does not exist in the most recent commit, or no such branch exists.");
			} else {
				try {
					Files.copy(myEnds.get(branch).myCommit.myFiles.get(name).toPath(), myOrigs.get(name).toPath(),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
	}

	/**
	 * Places a file from the givin commited folder into the working directory
	 * If commit id or file name does not exist: print system error If the file
	 * already exists inside the working directory then overides the file with
	 * the file from the commited folder
	 * 
	 * @param id
	 *            an integer that is used to locate the commit folder with that
	 *            id
	 * @param name
	 *            a string that is used to locate the file inside a commit
	 *            folder
	 */
	public void checkout(int id, String name) {
		if (!conflicted) {
			if (!commits.keySet().contains(id)) {
				System.out.println("No commit with that id exists.");
			} else if (!commits.get(id).myFiles.containsKey(name)) {
				System.out.println("File does not exist in that commit.");
			} else {
				try {
					Files.copy(commits.get(id).myFiles.get(name).toPath(), myOrigs.get(name).toPath(),
							StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
	}

	/**
	 * Places all of the files from the head of the given into the working
	 * directory If the branch name does not exist: print system error If the
	 * branch name is equal to the current branch: print system error else
	 * places all of its files into the working directory and overwrites all if
	 * the files if any
	 * 
	 * @param branch
	 *            a string that is used to identify and locate a branch inside
	 *            ".gitlet"
	 */
	public void checkout1(String branch) {
		if (!conflicted) {
			if (!myEnds.keySet().contains(branch)) {
				System.out.println("File does not exist in the most recent commit, or no such branch exists.");
			} else if (branch.equals(this.branch)) {
				System.out.println("No need to checkout the current branch.");
			} else {
				if (myEnds.get(branch).myCommit.myFiles != null) {
					for (Object s : myEnds.get(branch).myCommit.myFiles.keySet().toArray()) {
						try {
							Files.copy(myEnds.get(branch).myCommit.myFiles.get(s).toPath(), myOrigs.get(s).toPath(),
									StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				this.branch = branch;
			}
		} else
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
	}

	/**
	 * Creates a new branch that points to the current head node If not
	 * conflicted and if assosiated branch is not null print system error If not
	 * conflicted and if assosiated branch is null create new branch and point
	 * at current head node If conflicted print system error
	 * 
	 * @param branch
	 *            a string that is used as a name for a branch
	 */
	public void branch(String branch) {
		if (!conflicted) {
			if (myEnds.get(branch) != null)
				System.out.println("A branch with that name already exists.");
			else
				myEnds.put(branch, new branch(myEnds.get(this.branch).myCommit, branch));
		} else
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
	}

	/**
	 *
	 * If not confilcted and if associate branch is null print system error If
	 * not conflicted and are at curent brnch: print system error if conflicted
	 * print system error else removed pointer associated with branch
	 * 
	 * @param branch
	 *            a string that is used as the key when searching for a branch
	 *            to remove
	 */
	public void rm_branch(String branch) {
		if (!conflicted) {
			if (myEnds.get(branch) == null)
				System.out.println("A branch with that name does not exist.");
			else if (branch.equals(this.branch))
				System.out.println("Cannot remove the current branch.");
			else
				myEnds.remove(branch);
		} else
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
	}

	/**
	 * If conflicted print system error If not conflicited and id does not
	 * exist, print system error else checks out all files tracked by given
	 * commit moves current branch's head to commit node
	 * 
	 * @param id
	 *            a string that acts as a key for the commit HashMap
	 */
	public void reset(int id) {
		if (!conflicted) {
			if (commits.get(id) == null)
				System.out.println("No commit with that id exists.");
			else {
				for (Object s : commits.get(id).myFiles.keySet().toArray()) {
					checkout(id, (String) s);
				}
				myEnds.get(branch).rePoint(commits.get(id));
			}
		} else
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
	}

	/**
	 * Takes the head of two branches and creates a new commit with files from
	 * both commits. if conflicted, prints out cannot do this command If not
	 * conflicted and branchFrom does not exist, prints A branch with that name
	 * does not exist If not conflicted and branchFrom is the same as the branch
	 * calling the method, prints Cannot merge a branch with itself else finds
	 * which branch is longer and set a pointer to a position where both are the
	 * same distance away from split point iterates down to split point takes
	 * all files within branch calling method and stores name in a hashset
	 * checks if branchFrom has any files matching any names stored in hashset
	 * if the file is the same as the file in the other branch, stages the file
	 * if file is same as the one at split point and the branch calling the
	 * method has a file thats different from the split point's, continues with
	 * loop if file is different from the one at split point and the branch
	 * calling the method has a file thats the same as the split point's, stages
	 * the file in branchFrom else sets conflicted to true puts file in
	 * branchFrom into working directory and adds .conflicted at the end puts
	 * file from branch calling method into working directory as well else
	 * stages the file that is in branchFrom if conflicted is not true, prints
	 * out a message saying merge is successful else prints out a message saying
	 * a merge conflict has occured
	 * 
	 * @param branchFrom
	 *            a branch taken in and merged into branch calling merge method
	 */
  public void merge(String branchFrom) {
		if (!conflicted) {
			if (!myEnds.keySet().contains(branchFrom))
				System.out.println("A branch with that name does not exist.");
			else if (branchFrom.equals(branch))
				System.out.println("Cannot merge a branch with itself.");
			else {
				int move = myEnds.get(branchFrom).myCommit.size() - myEnds.get(branch).myCommit.size();
				commit pointer1 = myEnds.get(branchFrom).myCommit, pointer2 = myEnds.get(branch).myCommit;
				HashSet<String> fileCheck = new HashSet<String>();
				if (move > 0) {
					for (int i = 0; i < move; i++) {
						pointer1 = pointer1.myParent;
					}
				} else if (move < 0) {
					for (int i = 0; i < Math.abs(move); i++) {
						pointer2 = pointer2.myParent;
					}
				}
				while (pointer1 != pointer2) {
					pointer1 = pointer1.myParent;
					pointer2 = pointer2.myParent;
				}
				if (myEnds.get(branch).myCommit.myFiles != null) {
					for (Object s : myEnds.get(branch).myCommit.myFiles.keySet()) {
						fileCheck.add((String) s);
					}
				}
				if (myEnds.get(branchFrom).myCommit.myFiles != null) {
					for (Object s : myEnds.get(branchFrom).myCommit.myFiles.keySet()) {
						if (fileCheck.contains(s)) {
							if (same(myEnds.get(branchFrom).myCommit.myFiles.get(s), myEnds.get(branch).myCommit.myFiles.get(s))) {
								add((String) s);
							} else if (pointer1.myFiles != null && pointer1.myFiles.containsKey(s)) {
								if (same(myEnds.get(branchFrom).myCommit.myFiles.get(s), pointer1.myFiles.get(s)) && !same(myEnds.get(branch).myCommit.myFiles.get(s), pointer1.myFiles.get(s))) {
									continue;
								} else if (same(myEnds.get(branch).myCommit.myFiles.get(s), pointer1.myFiles.get(s)) && !same(myEnds.get(branchFrom).myCommit.myFiles.get(s), pointer1.myFiles.get(s)))
									add(myEnds.get(branchFrom).myCommit.myFiles.get(s), (String) s);
								else {
									conflicted(branchFrom, s);
								}
							} else {
								conflicted(branchFrom, s);
							}

						} else {
							add((String) s);
						}
					}
				}
				if (!conflicted)
					commit("Merged " + branch + " with " + branchFrom + ".");
				else
					System.out.println("Encountered a merge conflict.");
			}
		} else
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
	}
  
  /**
   * Makes the program go into 'conflicted' mode.
   * 
   * @param branchFrom
   * @param s
   */
  private void conflicted(String branchFrom, Object s){
	  conflicted = true;
		try {
			Files.copy(myEnds.get(branchFrom).myCommit.myFiles.get(s).toPath(), Paths.get(s + ".conflicted"), StandardCopyOption.REPLACE_EXISTING);
			Files.copy(myEnds.get(branch).myCommit.myFiles.get(s).toPath(), Paths.get((String) s), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
	

	/**
	 * Checks if two files are the same based on byte size
	 * 
	 * @param f1
	 * @param f2
	 * @return true if the two files are equal
	 */
	private boolean same(File f1, File f2) {
		byte[] file1 = null;
		byte[] file2 = null;
		try {
			file1 = Files.readAllBytes(f1.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			file2 = Files.readAllBytes(f2.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Arrays.equals(file1, file2);
	}

	/**
	 * Merge's own add method, that stages the file in the commit
	 * 
	 * @param f
	 *            the file being added
	 * @param s
	 *            the name of the file
	 */
	private void add(File f, String s) {
		File f2 = new File(".gitlet/staged/" + s);
		f2.getParentFile().mkdirs();
		staged.put(s, f2);
		try {
			Files.copy(f.toPath(), f2.toPath());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Takes a branch and appends all of the commits unique to it to the end of
	 * another branch
	 * 
	 * @param branchTo
	 *            the branch that the commits are being appended to If not
	 *            conflicted and branchTo does not exist, prints a message
	 *            saying the branch does not exist if not conflicted and the
	 *            branch calling the method is the same as branchTo, prints
	 *            message saying it cannot rebase to itself if not conflicted,
	 *            iterates through the branch calling the method and checks if a
	 *            commit within it has and id that is the same as the id of the
	 *            head of branchTo. if yes, sets boolean b to true and prints
	 *            out that branch is already up to date if boolean b is not
	 *            true, finds which of the two branches is longer and positions
	 *            pointers so that they are the same length away from closest
	 *            split point if the branch calling method is longer, adds files
	 *            along the way to a stack iterates all the way down to closest
	 *            split point adding files in branch calling method to the stack
	 *            sets branch end of branch calling method to branch end of
	 *            branchTo while stack is not empty, appends the most recent
	 *            commit to end of branchTo and set that one to the new head of
	 *            the branch calling the method
	 */
	public void rebase(String branchTo) {
		if (!conflicted) {
			if (!myEnds.keySet().contains(branchTo))
				System.out.println("A branch with that name does not exist.");
			else if (branchTo.equals(branch))
				System.out.println("Cannot rebase a branch onto itself.");
			else {
				Iterator<commit> itr = myEnds.get(branch).myCommit.iterator();
				boolean b = false;
				while (itr.hasNext()) {
					if (itr.next().id == myEnds.get(branchTo).myCommit.id) {
						System.out.println("Already up-to-date.");
						b = true;
					}
				}
				if (!b) {
					int move = myEnds.get(branchTo).myCommit.size() - myEnds.get(branch).myCommit.size();
					commit pointer1 = myEnds.get(branchTo).myCommit;
					commit pointer2 = myEnds.get(branch).myCommit;
					Stack<Integer> ids = new Stack<Integer>();
					if (move > 0) {
						for (int i = 0; i < move; i++) {
							pointer1 = pointer1.myParent;
						}
					}
					if (move < 0) {
						for (int i = 0; i < Math.abs(move); i++) {
							ids.add(pointer2.id);
							pointer2 = pointer2.myParent;
						}
					}
					while (pointer1 != pointer2) {
						ids.add(pointer2.id);
						pointer1 = pointer1.myParent;
						pointer2 = pointer2.myParent;
					}
					myEnds.get(branch).myCommit = myEnds.get(branchTo).myCommit;
					while (!ids.isEmpty()) {
						int id = ids.pop();
						commit c = new commit(commits.get(id).myMessage, commits.get(id).myFiles,
								myEnds.get(branch).myCommit);
						commits.put(c.id, c);
						myEnds.get(branch).rePoint(c);
						if (messages.containsKey(c.getMessage())) {
							ArrayList<Integer> arr = messages.get(c.getMessage());
							arr.add(c.getId());
							messages.remove(c.getMessage());
							messages.put(c.getMessage(), arr);
						} else {
							ArrayList<Integer> arr = new ArrayList<Integer>();
							arr.add(c.getId());
							messages.put(c.getMessage(), arr);
						}

					}
				}
			}
		} else
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
	}

	public void add_remote(String remoteName, String remoteUserName, String remoteServer, String remotePathLocation) {
		if (!remoteNames.containsKey(remoteName)) {
			remoteNames.put(remoteName, new Remote(remoteUserName, remoteServer, remotePathLocation));
			new File(".gitlet/remote/").mkdir();
		} else
			System.out.println("A remote with that name already exists.");
	}

	public void rm_remote(String remoteName) {
		if (!remoteNames.containsKey(remoteName)) {
			System.out.println("A remote with that name does not exist.");
		} else
			remoteNames.remove(remoteName);
	}

	public void push(String remoteName, String remoteBranch) {

	}

	/**
	 * Writes to the file?
	 * 
	 * @param g
	 * @throws IOException
	 */
	public static void writeToFile(Gitlet g) throws IOException {
		@SuppressWarnings("resource")
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(".gitlet/gitlet.ser"));
		objectOutputStream.writeObject(g);
	}

	/**
	 * Reads the file?
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Gitlet readFile() throws IOException, ClassNotFoundException {
		@SuppressWarnings("resource")
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(".gitlet/gitlet.ser"));
		return (Gitlet) objectInputStream.readObject();
	}

	private class commit implements Serializable {
		private static final long serialVersionUID = 1L;
		private String myMessage;
		private HashMap<String, File> myFiles;
		private commit myParent;
		private Date today;
		private String output;
		private SimpleDateFormat formatter;
		private Locale bLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
		private int id;

		/**
		 * Creates an empty commit, assigns it a unique ID, a timestamp, a
		 * message
		 * 
		 * @param message
		 *            a string that the commit is committed with
		 */
		commit(String message) {
			myMessage = message;
			formatter = new SimpleDateFormat("yyyy-MM-dd H:mm:ss", bLocale);
			today = new Date();
			output = formatter.format(today);
			id = idNum;
			idNum++;
			myFiles = null;
			myParent = null;
		}

		/**
		 * creates a commit with files in it and assigns it a timestamp, a
		 * unique ID, and a message. Set its parent to the commit at the end of
		 * the branch being commited to. If parent file is not null, copies it
		 * into the hashmap of files of the new commit (for tracking) If parent
		 * is null, only put files in this commit into the hashmap Checks if any
		 * files are in the untracked hashmap, remove those files from this
		 * commit Puts the files that were passed in into the commit
		 * 
		 * @param message
		 * @param files
		 * @param parent
		 */
		commit(String message, HashMap<String, File> files, commit parent) {
			myMessage = message;
			myParent = parent;
			if (myParent.myFiles != null) {
				myFiles = new HashMap<String, File>(myParent.myFiles);
			} else
				myFiles = new HashMap<String, File>();
			for (int i = 0; i < untracked.size(); i++) {
				myFiles.remove(untracked.get(i));
			}
			for (String s : files.keySet()) {
				myFiles.put(s, files.get(s));
			}

			formatter = new SimpleDateFormat("yyyy-MM-dd H:mm:ss", bLocale);
			today = new Date();
			output = formatter.format(today);
			id = idNum;
			idNum++;
		}

		/**
		 * Prints out the message, timestamp, and commit number
		 */
		public void printInfo() {
			System.out.println("===");
			System.out.println("Commit " + id);
			System.out.println(output);
			System.out.println(getMessage());
			System.out.println();
		}

		/**
		 * Gets the message of the commit
		 * 
		 * @return message of commit
		 */
		public String getMessage() {
			return myMessage;
		}

		/**
		 * keeps track of the size of the branch
		 * 
		 * @return the size of the branch
		 */
		public int size() {
			int counter = 0;
			Iterator<commit> itr = this.iterator();
			while (itr.hasNext()) {
				itr.next();
				counter++;
			}
			return counter;
		}

		/**
		 * gets unique Id of commit
		 * 
		 * @return id of commit
		 */
		public int getId() {
			return id;
		}

		/**
		 * gets and iterator for the commit
		 * 
		 * @return an iterator for the commit
		 */
		public Iterator<commit> iterator() {
			return new commitIterator(this);
		}

		public class commitIterator implements Iterator<commit> {
			private commit pointer;

			/**
			 * Sets the starting point of the iterator to the commit passed in
			 */
			public commitIterator(commit c) {
				pointer = c;
			}

			/**
			 * Checks if there are more commits in the branch
			 * 
			 * @return true if there are more commits
			 * @return false if there are no more commits
			 */
			public boolean hasNext() {
				if (pointer != null)
					return true;
				return false;
			}

			/**
			 * Returns the current commit the iterator is at and moves the
			 * pointer to the next commit
			 * 
			 * @return commit that pointer is pointing to
			 */
			public commit next() {
				commit temp = pointer;
				pointer = pointer.myParent;
				return temp;
			}

			/**
			 * Not used idk
			 */
			public void remove() {
			}
		}
	}

	private class branch implements Serializable {
		private static final long serialVersionUID = 1L;
		private commit myCommit;
		private String myName;

		/**
		 * Sets branch name to string passed in and the branch's commit to the
		 * commit passed in
		 */
		public branch(commit commit, String name) {
			myCommit = commit;
			myName = name;
		}

		/**
		 * Gets the name of the branch
		 */
		public String getName() {
			return myName;
		}

		/**
		 * gets the head commit of the branch
		 */
		public commit getCommit() {
			return myCommit;
		}

		/**
		 * repoints the branch's commit to a given commit
		 */
		public void rePoint(commit commit) {
			myCommit = commit;
		}
	}

	private class Remote {
		String myUserName, myServer, myPathLocation;

		public Remote(String userName, String server, String pathLocation) {
			myUserName = userName;
			myServer = server;
			myPathLocation = pathLocation;
		}
	}

	/**
	 * Allows user to call commands
	 */
	public static void main(String[] args) {
		Gitlet g = new Gitlet();
		if ((new File(".gitlet/gitlet.ser")).exists()) {
			try {
				g = readFile();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		if (args.length == 0) {
			System.err.println("Please enter a command.");
			System.exit(1);
		}
		if (args[0].equals("init")) {
			g.init();
		} else if (args[0].equals("add") && args[1] != null) {
			g.add(args[1]);
		} else if (args[0].equals("commit")) {
			if (args.length <= 1) {
				System.err.println("Please enter a commit message.");
				System.exit(1);
			}
			g.commit(args[1]);

		} else if (args[0].equals("rm")) {
			g.rm(args[1]);
		} else if (args[0].equals("log")) {
			g.log();
		} else if (args[0].equals("global-log")) {
			g.global_log();
		} else if (args[0].equals("find")) {
			String s = "";
			for (int i = 1; i < args.length; i++) {
				s += args[i];
				if (args.length > 2 && i < args.length - 1)
					s += " ";
			}
			g.find(s);
		} else if (args[0].equals("status")) {
			g.status();
		} else if (args[0].equals("checkout")) {
			if (args.length > 2)
				g.checkout(Integer.parseInt(args[1]), args[2]);
			else
				g.checkoutHelper(args[1]);
		} else if (args[0].equals("branch")) {
			g.branch(args[1]);
		} else if (args[0].equals("rm-branch")) {
			g.rm_branch(args[1]);
		} else if (args[0].equals("reset")) {
			g.reset(Integer.parseInt(args[1]));
		} else if (args[0].equals("merge")) {
			g.merge(args[1]);
		} else if (args[0].equals("rebase")) {
			g.rebase(args[1]);
		} else if (args[0].equals("add-remote")) {
			g.add_remote(args[1], args[2], args[3], args[4]);
		} else if (args[0].equals("rm-remove")) {
			g.rm_remote(args[1]);
		} else {
			System.err.println("No command with that name exists.");
			System.exit(1);
		}
		try {
			writeToFile(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}