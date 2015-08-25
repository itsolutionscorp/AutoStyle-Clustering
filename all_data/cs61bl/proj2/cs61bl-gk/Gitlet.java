import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Gitlet {
	private static ListNode header;
	private static String currentBranch;
	private static int globalID = 0;
	private static HashMap<String, ListNode> branchHeader;
	private static HashMap<String, Integer> trackFiles;
	private static ArrayList<ListNode> logList;
	private static HashSet<String> stageList = new HashSet<String>();
	private static HashMap<String, Integer> untrackList = new HashMap<String, Integer>();
	private static boolean isConflicted = false;

	/**
	 * Initiates the .gitlet directory and initializes all the instance variables
	 */
	
	public static void init() {
		if (new File(".gitlet").exists() && new File(".gitlet").isDirectory()) {
			System.out.println("A gitlet version control system already exists in the current directory.");
			return;
		}

		new File(".gitlet").mkdir();
		new File(".gitlet/staging").mkdir();
		header = new ListNode(globalID, null, "initial commit");
		branchHeader = new HashMap<String, ListNode>();
		branchHeader.put("master", header);
		currentBranch = "master";
		trackFiles = new HashMap<String, Integer>();
		isConflicted = false;
		logList = new ArrayList<ListNode>();
		logList.add(header);
		globalID++;
	}

	/**
	 * Stages the given file to the .gitlet/staging directory, using the copyHelper helper method.
	 * @param args
	 * 		argument array passed in from main
	 * @throws IOException
	 * 		from calling File
	 */
	
	public static void add(String[] args) throws IOException {
		if (args.length < 2 || args[1] == null || !(new File(args[1]).exists()) || (new File(args[1]).isDirectory())) {
			System.out.println("File does not exist.");
			return;
		}

		copyHelper(Paths.get(args[1]));
	}

	/**
	 * Copies the file with the given path to the staging folder, recreating the nested structure if necessary.
	 * @param fileName
	 * 		name of file to be staged
	 * @throws IOException
	 * 		from calling File
	 */
	
	private static void copyHelper(Path fileName) throws IOException {
		for (int i = 1; i < fileName.getNameCount(); i++) {
			new File(".gitlet/staging/" + fileName.subpath(0, i).toString()).mkdir();
		}

		Files.copy(fileName, Paths.get(".gitlet/staging/" + fileName), StandardCopyOption.REPLACE_EXISTING);
		stageList.add(fileName.toString());
	}

	/**
	 * Commits the changes made in a new commit and updates instance variables as necessary.
	 * @param args
	 * 		argument array passed in from main
	 * @throws IOException
	 * 		From calling built in methods
	 */

	public static void commit(String[] args) throws IOException {

		if (args.length < 2) {
			System.out.println("Please enter a commit message.");
			return;
		}

		if (stageList.isEmpty()) {
			System.out.println("No changes added to the commit.");
			return;
		}

		File dir = new File(".gitlet/staging");
		dir.renameTo(new File(".gitlet/" + globalID));
		new File(".gitlet/staging").mkdir();

		for (String filename : stageList) {
			trackFiles.put(filename, globalID);
		}

		FileOutputStream fileOut = new FileOutputStream(".gitlet/" + globalID + ".track");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(trackFiles);
		out.close();
		fileOut.close();

		stageList = new HashSet<String>();
		header = new ListNode(globalID, header, args[1]);
		branchHeader.put(currentBranch, header);
		logList.add(header);
		globalID++;
		isConflicted = false;
	}

	/**
	 * If a file is being tracked, untracks it. If the file was just added, remove it from staging.
	 * @param args
	 * 		argument array passed in from main
	 * @throws IOException
	 * 		From calling built in methods
	 */
	
	public static void rm(String[] args) throws IOException {

		File stageFile = new File(".gitlet/staging/" + args[1]);
		if (stageFile.exists()) {
			Files.delete(stageFile.toPath());
			stageList.remove(args[1]);
			return;
		}

		if (trackFiles.get(args[1]) != null) {
			int commitID = trackFiles.get(args[1]);
			trackFiles.remove(args[1]);
			untrackList.put(args[1], commitID);
			return;
		}
		System.out.println("No reason to remove the file.");
	}

	/**
	 * Prints out the log of commits in the current branch
	 */
	
	public static void log() {
		for (ListNode current = header; current != null; current = current.getPrev()) {
			System.out.println("===");
			System.out.print("Commit ");
			System.out.println(current.getID());
			System.out.println(current.getDate());
			System.out.println(current.getMsg());
			System.out.println();
		}
	}

	/**
	 * Prints out a log of all commits ever made
	 */
	
	public static void global_log() {
		for (ListNode current : logList) {
			System.out.println("===");
			System.out.print("Commit ");
			System.out.println(current.getID());
			System.out.println(current.getDate());
			System.out.println(current.getMsg());
			System.out.println();
		}
	}

	/**
	 * Finds and prints all commits with the given message string
	 * @param args
	 * 		argument array passed in from main
	 */
	
	public static void find(String[] args) {
		boolean isFound = false;

		for (ListNode currentNode : logList) {
			if (currentNode.getMsg().equals(args[1])) {
				System.out.println(currentNode.getID());
				isFound = true;
			}
		}

		if (!isFound) {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * Prints out the current status of gitlet, such as branches, files staged, and files untracked
	 */
	
	public static void status() {
		System.out.println("=== Branches ===");
		Set<String> branches = branchHeader.keySet();
		for (String branch : branches) {
			if (branch.equals(currentBranch)) {
				System.out.print("*");
			}
			System.out.println(branch);
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (String file : stageList) {
			System.out.println(file);
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (String file : untrackList.keySet()) {
			System.out.println(file);
		}
	}

	/**
	 * Creates a new branch with the given name
	 * @param args
	 * 		argument array passed in from main
	 */
	
	public static void branch(String[] args){
		if (branchHeader.containsKey(args[1])) {
			System.out.println("A branch with that name already exists.");
			return;
		}
		branchHeader.put(args[1], header);
	}

	/**
	 * Removes the branch with the given name
	 * @param args
	 * 		argument array passed in from main
	 */
	
	public static void rm_branch(String[] args){
		if (!branchHeader.containsKey(args[1])) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (args[1].equals(currentBranch)) {
			System.out.println("Cannot remove the current branch.");
			return;
		}
		branchHeader.remove(args[1]);
	}

	/**
	 * First type of checkout, which copies the given filename from the most recent commit in the current branch to the workspace
	 * @param args
	 * 		argument array passed in from main
	 * @return
	 * 		returns true if successful, false otherwise
	 * @throws IOException
	 * 		from calling built in methods
	 */
	
	public static boolean checkout1(String args[]) throws IOException {
		String fileName = args[1];

		if (!trackFiles.containsKey(fileName)) {
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
			return false;
		}

		checkoutcopyHelper(Paths.get(fileName), trackFiles.get(fileName));
		return true;
	}

	/**
	 * Second type of checkout, which copies the given file from the given commit to the workspace
	 * @param args
	 * 		argument array passed in from main
	 * @throws ClassNotFoundException
	 * 		From calling built in methods
	 * @throws IOException
	 * 		From calling built in methods
	 */
	
	public static void checkout2(String args[]) throws ClassNotFoundException, IOException {
		Integer commitID = Integer.parseInt(args[1]);
		if (commitID >= globalID || commitID < 0) {
			System.out.println("No commit with that id exists.");
			return;
		}

		HashMap<String, Integer> tempTrackFiles = getTrack(commitID);

		Integer value = tempTrackFiles.get(args[2]);
		if (value == null) {
			System.out.println("File does not exist in that commit.");
			return;
		}
		checkoutcopyHelper(Paths.get(args[2]), value);
	}

	/**
	 * Third type of checkout, copies all files from the given branch to the working directory and sets given branch as current
	 * @param args
	 * 		argument array passed in from main
	 * @return
	 * 		return true if successful, false otherwise
	 * @throws ClassNotFoundException
	 * 		from calling built in methods
	 * @throws IOException
	 * 		from calling built in methods
	 */
	
	public static boolean checkout3(String args[]) throws ClassNotFoundException, IOException {
		ListNode tempHead = branchHeader.get(args[1]);

		if (currentBranch.equals(args[1])) {
			System.out.println("No need to checkout the current branch.");
			return true;
		}

		if (tempHead == null) {
			return false;
		}

		if (isConflicted) {
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return true;
		}

		currentBranch = args[1];
		header = tempHead;
		branchHeader.put(currentBranch, header);
		trackFiles = getTrack(tempHead.getID());

		for (String key : trackFiles.keySet()) {
			checkoutcopyHelper(Paths.get(key), trackFiles.get(key));
		}
		return true;
	}

	/**
	 * Helper method for checkout functions, copies the file with given name from the given commit folder to workspace
	 * @param fileName
	 * 		Name of file to be copied
	 * @param ID
	 * 		ID of commit it is stored in
	 * @throws IOException
	 * 		from calling built in methods
	 */
	
	private static void checkoutcopyHelper(Path fileName, Integer ID) throws IOException {
		for (int i = 1; i < fileName.getNameCount(); i++) {
			new File(fileName.subpath(0, i).toString()).mkdir();
		}

		Files.copy(Paths.get(".gitlet/" + ID + "/" + fileName.toString()), Paths.get(fileName.toString()),
				StandardCopyOption.REPLACE_EXISTING);
	}

	/**
	 * Helper method to determine which checkout function to use.
	 * @param args
	 * 		argument array passed in from main
	 * @throws ClassNotFoundException
	 * 		from calling built in methods
	 * @throws IOException
	 * 		from calling built in methods
	 */
	
	public static void checkout(String args[]) throws ClassNotFoundException, IOException {
		if (args.length < 3) {
			if (checkout3(args)) {
				return;
			}

			checkout1(args);
			return;
		}

		checkout2(args);
	}

	/**
	 * Reset the branch header to the given commit, and also checks out all files in that commit
	 * @param args
	 * 		argument array passed in from main
	 * @throws IOException
	 * 		from calling built in methods
	 * @throws ClassNotFoundException
	 * 		from calling built in methods
	 */
	
	public static void reset(String[] args) throws IOException, ClassNotFoundException {
		int commitID = Integer.parseInt(args[1]);
		if (commitID >= globalID || commitID < 0) {
			System.out.println("No commit with that id exists.");
			return;
		}
		header = logList.get(commitID);

		branchHeader.put(currentBranch, header);

		if (commitID != 0) {
			trackFiles = getTrack(commitID);
			for (String key : trackFiles.keySet()) {
				checkoutcopyHelper(Paths.get(key), trackFiles.get(key));
			}
		} else {
			trackFiles = new HashMap<String, Integer>();
		}
	}

	/**
	 * Helper method for merge to meet 60 line/method requirement
	 * @param splitTrackFiles
	 * 		the hashmap of tracked files from the split point
	 * @param givenTrackFiles
	 * 		the hashmap of tracked files from the given branch
	 * @param currTrackFiles
	 * 		the hashmap of tracked files from the current branch
	 * @throws IOException
	 * 		from calling built in methods
	 */
	
	private static void splitLoop(HashMap<String, Integer> splitTrackFiles, HashMap<String, Integer> givenTrackFiles,
			HashMap<String, Integer> currTrackFiles) throws IOException {
		for (String fileName : splitTrackFiles.keySet()) {
			Integer IDSplit = splitTrackFiles.get(fileName);
			String pathSplit = ".gitlet/" + IDSplit + "/" + fileName;
			String pathCurr = "", pathGiven = "";

			boolean isExistInGiven = givenTrackFiles.containsKey(fileName);
			Integer IDGiven = givenTrackFiles.get(fileName);
			if (IDGiven != null) {
				pathGiven = ".gitlet/" + IDGiven + "/" + fileName;
			}

			boolean isExistInCurr = currTrackFiles.containsKey(fileName);
			Integer IDCurr = currTrackFiles.get(fileName);

			if (IDCurr != null) {
				pathCurr = ".gitlet/" + IDCurr + "/" + fileName;
			}

			if (!isExistInGiven && !isExistInCurr) {
				continue;
			}

			if (!isExistInGiven) {
				if (isSame(pathSplit, pathCurr)) {
					untrackList.put(fileName, trackFiles.remove(fileName));
				}
				currTrackFiles.remove(fileName);
			} else if (!isExistInCurr) {
				if (!isSame(pathSplit, pathGiven)) {
					mergeAdd(Paths.get(fileName), givenTrackFiles.get(fileName));
				}
				givenTrackFiles.remove(fileName);
			} else {
				if (!isSame(pathGiven, pathCurr)) {
					if (!isSame(pathGiven, pathSplit)) {
						if (isSame(pathCurr, pathSplit)) {
							mergeAdd(Paths.get(fileName), givenTrackFiles.get(fileName));
						} else {
							conflictedCopy(Paths.get(fileName), givenTrackFiles.get(fileName));
							isConflicted = true;
						}
					}
				}
				currTrackFiles.remove(fileName);
				givenTrackFiles.remove(fileName);
			}
		}
	}

	/**
	 * Merges the given branch with the current branch, with various special circumstances for specific situations
	 * @param args
	 * 		argument array passed in from main
	 * @throws ClassNotFoundException
	 * 		from calling built in methods
	 * @throws IOException
	 * 		from calling built in methods
	 */
	
	public static void merge(String[] args) throws ClassNotFoundException, IOException {
		String givenBranch = args[1];
		if (!branchHeader.containsKey(givenBranch)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (currentBranch.equals(givenBranch)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}

		ListNode givenNode = branchHeader.get(givenBranch);
		int splitID = findSplit(givenNode, header);

		HashMap<String, Integer> splitTrackFiles = getTrack(splitID);
		int givenCommitID = givenNode.getID();
		HashMap<String, Integer> givenTrackFiles = getTrack(givenCommitID);
		HashMap<String, Integer> currTrackFiles = getTrack(header.getID());
		splitLoop(splitTrackFiles, givenTrackFiles, currTrackFiles);

		for (String fileName : givenTrackFiles.keySet()) {
			Integer IDGiven = givenTrackFiles.get(fileName);
			String pathGiven = ".gitlet/" + IDGiven + "/" + fileName;

			boolean isFileinCurr = currTrackFiles.containsKey(fileName);
			Integer IDCurr = currTrackFiles.get(fileName);
			String pathCurr = "";
			if (IDCurr != null) {
				pathCurr = ".gitlet/" + IDCurr + "/" + fileName;
			}

			if (isFileinCurr) {
				if (!isSame(pathGiven, pathCurr)) {
					conflictedCopy(Paths.get(fileName), givenTrackFiles.get(fileName));
					isConflicted = true;
				}
				currTrackFiles.remove(fileName);
			} else {
				mergeAdd(Paths.get(fileName), IDGiven);
			}
		}
		if (isConflicted) {
			System.out.println("Encountered a merge conflict.");
		} else {
			String argu[] = new String[2];
			argu[0] = "commit";
			argu[1] = "Merged " + currentBranch + " with " + givenBranch + ".";
			commit(argu);
		}
	}

	/**
	 * Helper method from merge to stage the given file from the given commit, while recreating the appropriate subdirectories
	 * @param fileName
	 * 		the name of the file as a path
	 * @param ID
	 * 		the commit id of the commit the file is in
	 * @throws IOException
	 * 		from calling built in methods
	 */
	
	private static void mergeAdd(Path fileName, Integer ID) throws IOException {
		for (int i = 1; i < fileName.getNameCount(); i++) {
			new File(".gitlet/staging/" + fileName.subpath(0, i).toString()).mkdir();
		}
		checkoutcopyHelper(fileName, ID);
		Files.copy(Paths.get(".gitlet/" + ID + "/" + fileName.toString()), Paths.get(".gitlet/staging/" + fileName),
				StandardCopyOption.REPLACE_EXISTING);
		stageList.add(fileName.toString());
	}

	/**
	 * Helper method from merge to copy a file with a .conflicted suffix during a merge conflict
	 * @param fileName
	 * 		the name of the file as a path
	 * @param ID
	 * 		the commit id of the commit the file is in 
	 * @throws IOException
	 * 		from calling built in methods
	 */
	
	private static void conflictedCopy(Path fileName, Integer ID) throws IOException{
		for (int i = 1; i < fileName.getNameCount(); i++) {
			new File(fileName.subpath(0, i).toString()).mkdir();
		}

		Files.copy(Paths.get(".gitlet/" + ID + "/" + fileName.toString()),
				Paths.get(fileName.toString() + ".conflicted"), StandardCopyOption.REPLACE_EXISTING);
	}

	/**
	 * Checks if it is appropriate to call rebase
	 * @param args
	 * 		argument array passed in from main
	 * @return
	 * 		returns true if is appropriate, false otherwise
	 */
	
	private static boolean rebaseErrorHandler(String[] args) {
		String givenBranch = args[1];
		ListNode givenHeader = branchHeader.get(givenBranch);

		if (givenHeader == null) {
			System.out.println("A branch with that name does not exist.");
			return true;
		}

		if (givenBranch.equals(currentBranch)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return true;
		}

		if (givenHeader == branchHeader.get(currentBranch)) {
			System.out.println("Already up-to-date.");
			return true;
		}

		return false;
	}

	/**
	 * Helper method for rebase to reset 
	 * @throws ClassNotFoundException
	 * 		from calling built in methods
	 * @throws IOException
	 * 		from calling built in methods
	 */
	
	private static void rebaseReset() throws ClassNotFoundException, IOException {
		String arg[] = new String[2];
		arg[0] = "reset";
		arg[1] = Integer.toString(header.getID());
		reset(arg);
	}

	/**
	 * Helper method to save .track files in rebase
	 * @param givenFile
	 * 		hashmap of files in given branch
	 * @throws IOException
	 * 		from calling built in methods
	 */
	
	private static void rebaseSave(HashMap<String, Integer> givenFile) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(".gitlet/" + globalID + ".track");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(givenFile);
		out.close();
		fileOut.close();
	}

	/**
	 * Finds the head of the given branch, and transfers the entire branch since the split point to the head of the current branch
	 * @param args
	 * 		argument array from main
	 * @throws ClassNotFoundException
	 * 		from calling built in methods
	 * @throws IOException
	 * 		from calling built in methods
	 */
	
	public static void rebase(String[] args) throws ClassNotFoundException, IOException {
		if (rebaseErrorHandler(args)) {
			return;
		}

		String givenBranch = args[1];
		ListNode givenHeader = branchHeader.get(givenBranch);
		ListNode currHeader = branchHeader.get(currentBranch);
		ListNode tempHeader = givenHeader;
		while (tempHeader != null) {
			if (tempHeader.getID() == currHeader.getID()) {
				header = givenHeader;
				branchHeader.put(currentBranch, header);
				rebaseReset();
				return;
			}
			tempHeader = tempHeader.getPrev();
		}

		int splitID = findSplit(givenHeader, currHeader);
		Stack<Integer> stackID = new Stack<Integer>();
		Stack<String> stackMSG = new Stack<String>();

		tempHeader = currHeader;
		while (tempHeader.getID() != splitID) {
			stackID.push(tempHeader.getID());
			stackMSG.push(tempHeader.getMsg());
			tempHeader = tempHeader.getPrev();
		}

		HashMap<String, Integer> splitFile = getTrack(splitID);
		HashMap<String, Integer> givenFile = getTrack(givenHeader.getID());

		header = givenHeader;
		while (!stackID.isEmpty()) {
			HashMap<String, Integer> stackFile = getTrack((Integer) stackID.pop());
			for (String fileName : stackFile.keySet()) {
				Integer tempNumber = stackFile.get(fileName);
				if (splitFile.get(fileName) == null || ((Integer) splitFile.get(fileName)) < tempNumber) {
					givenFile.put(fileName, tempNumber);
				}
			}

			rebaseSave(givenFile);
			header = new ListNode(globalID, header, (String) stackMSG.pop());
			branchHeader.put(currentBranch, header);
			trackFiles = givenFile;
			logList.add(header);
			globalID++;
		}
		rebaseReset();
	}

	/**
	 * Helper method for merge and rebase to find the split point of two branches
	 * @param givenBranch
	 * @param currentBranch
	 * @return
	 */
	
	private static int findSplit(ListNode givenBranch, ListNode currentBranch) {
		ListNode givenPoint = givenBranch;
		ListNode currentPoint = currentBranch;
		while (givenPoint.getID() != currentPoint.getID()) {
			if (givenPoint.getID() > currentPoint.getID()) {
				givenPoint = givenPoint.getPrev();
			} else {
				currentPoint = currentPoint.getPrev();
			}
		}
		return givenPoint.getID();
	}

	/**
	 * Helper method for merge to determine if two files are the same
	 * @param file1
	 * 		first file as a string
	 * @param file2
	 * 		second file as a string
	 * @return
	 * 		returns true if they are the same, false otherwise
	 * @throws IOException
	 * 		from calling built in methods
	 */
	
	private static boolean isSame(String file1, String file2) throws IOException{
		Path f1Path = Paths.get(file1);
		Path f2Path = Paths.get(file2);
		byte[] file1Bytes = Files.readAllBytes(f1Path);
		byte[] file2Bytes = Files.readAllBytes(f2Path);
		if (Arrays.equals(file1Bytes, file2Bytes)) {
			return true;
		}
		return false;
	}

	/**
	 * gets the track file of a given commit
	 * @param commitID
	 * 		the commit id of the commit to look in
	 * @return
	 * 		returns the hashmap of the unserialized .track file
	 * @throws ClassNotFoundException
	 * 		from calling built in methods
	 * @throws IOException
	 * 		from calling built in methods
	 */
	
	private static HashMap<String, Integer> getTrack(int commitID) throws ClassNotFoundException, IOException {
		HashMap<String, Integer> tempTrackFiles;
		FileInputStream fileIn = new FileInputStream(".gitlet/" + commitID + ".track");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		tempTrackFiles = (HashMap<String, Integer>) in.readObject();
		in.close();
		fileIn.close();
		return tempTrackFiles;
	}

	/**
	 * Helper method from main to determine which command to run
	 * @param args
	 * 		argument array from main
	 * @throws IOException
	 * 		from calling built in methods
	 * @throws ClassNotFoundException
	 * 		from calling built in methods
	 */
	
    public static void command(String[] args) throws IOException,
    ClassNotFoundException {
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            return;
        }
        String method = args[0];
        if (!isConflicted) {
            switch (method) {
                case "init":
                    init();
                    break;
                case "add":
                    add(args);
                    break;
                case "commit":
                    commit(args);
                    break;
                case "rm":
                    rm(args);
                    break;
                case "log":
                    log();
                    break;
                case "global-log":
                    global_log();
                    break;
                case "find":
                    find(args);
                    break;
                case "status":
                    status();
                    break;
                case "checkout":
                    checkout(args);
                    break;
                case "branch":
                    branch(args);
                    break;
                case "rm-branch":
                    rm_branch(args);
                    break;
                case "merge":
                	merge(args);
                    break;
                case "reset":
                    reset(args);
                    break;
                case "rebase":
                    rebase(args);
                    break;
                default:
                    System.out.println("No command with that name exists.");
                    break;
            }
        }
    }
    
    /**
     * Helper method to determine which commands to run in case of a merge conflict
     * @param args
     * 		argument array from main
     * @throws IOException
     * 		from calling built in methods
     * @throws ClassNotFoundException
     * 		from callin built in methods
     */
    
    public static void conflictedCommand(String[] args) throws IOException, ClassNotFoundException {
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            return;
        }
        String method = args[0];
        switch (method) {
            case "init":
                System.out.println("Cannot do this command until the merge conflict has been resolved.");
                break;
            case "add":
                add(args);
                break;
            case "commit":
                commit(args);
                break;
            case "rm":
                rm(args);
                break;
            case "log":
                log();
                break;
            case "global-log":
                global_log();
                break;
            case "find":
                find(args);
                break;
            case "status":
                status();
                break;
            case "checkout":
                checkout(args);
                break;
            case "branch":
                System.out
                .println("Cannot do this command until the merge conflict has been resolved.");
                break;
            case "rm-branch":
                System.out
                .println("Cannot do this command until the merge conflict has been resolved.");
                break;
            case "merge":
                System.out.println("Cannot do this command until the merge conflict has been resolved.");
                break;
            case "reset":
                System.out.println("Cannot do this command until the merge conflict has been resolved.");
                break;
            case "rebase":
                System.out.println("Cannot do this command until the merge conflict has been resolved.");
                break;
            default:
                System.out.println("No command with that name exists.");
                break;
        }
    }

    /**
     * Helper method to load the persistent class variables at the beginning of each execution of gitlet
     * @throws ClassNotFoundException
     * 		from calling built in methods
     * @throws IOException
     * 		from calling built in methods
     */
    
	private static void load() throws ClassNotFoundException, IOException {
		if (new File(".gitlet").exists() && new File(".gitlet").isDirectory()) {
			FileInputStream fileIn = new FileInputStream(".gitlet/status.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			InstanceVariable temp;
			temp = (InstanceVariable) in.readObject();

			header = temp.myHeader;
			currentBranch = temp.myCurrentBranch;
			globalID = temp.myGlobalID;
			branchHeader = temp.myBranchHeader;
			trackFiles = temp.myTrackFiles;
			stageList = temp.myStageList;
			logList = temp.myLogList;
			untrackList = temp.myUntrackList;
			isConflicted = temp.isConflicted;

			in.close();
			fileIn.close();
		}
	}

	/**
	 * helper method to save class varibles after each execution of gitlet
	 * @throws IOException
	 * 		from callin built in methods
	 */
	
	private static void save() throws IOException {
		if (new File(".gitlet").exists() && new File(".gitlet").isDirectory()) {
			FileOutputStream fileOut = new FileOutputStream(".gitlet/status.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			InstanceVariable temp = new InstanceVariable();
			temp.myHeader = header;
			temp.myCurrentBranch = currentBranch;
			temp.myGlobalID = globalID;
			temp.myBranchHeader = branchHeader;
			temp.myTrackFiles = trackFiles;
			temp.myStageList = stageList;
			temp.myLogList = logList;
			temp.myUntrackList = untrackList;
			temp.isConflicted = isConflicted;

			out.writeObject(temp);
			out.close();
			fileOut.close();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException{
		load();
		if (isConflicted) {
			conflictedCommand(args);
		} else {
			command(args);
		}
		save();
	}
}
