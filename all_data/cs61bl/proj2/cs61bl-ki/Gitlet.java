import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

public class Gitlet implements Serializable {

	private static final long serialVersionUID = 1L;

	HashMap<Integer, CommitNode> commitNodes; //maps key: Integer CommitId, value: CommitNode 
	HashMap<String, CommitNode> branches; // maps key: String branchName, value: CommitNode
	HashMap<String, String> filesToTrack; 	// maps key: original file location, value; staged file location
	HashMap<String, LinkedList<Integer>> messageToId; 
	ArrayList<String> filesToUntrack; //array with elements: list of files to un-track in the next commit
	File gitletFolder; // i might nto need these
	File serializedGitlet;
	File stagingArea;
	File workingDirectory;
	String currentBranch;
	int nextId; //keeps track of ID that next CommitNode will use
	boolean conflicted;

	/**
	 * Creates a Gitlet object and serializes it to gitlet.ser file in the .gitlet directory
	 * 
	 * @throws FileNotFoundException
	 * 		throws when file isnt found
	 * @throws
	 * 		throws when writeObject fails (something in Gitlet class not serializable.
	 */

	public static void init() {
		if (new File(".gitlet").exists()) {
			System.out
					.println("Gitlet Version Control System already exists in the current directory ");
			return;
		}
		Gitlet myGitlet = new Gitlet();
		try {			
			FileOutputStream fout = new FileOutputStream(
					myGitlet.serializedGitlet);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(myGitlet);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Actually initializes Gitlet in the current working directory (creates a .gitlet directory and gitlet.ser)
	 * Constructor for Gitlet Class, initializes
	 * branches/commitNodes/nextId
	 * filesToTrack/Untrack
	 */

	public Gitlet() {
		gitletFolder = new File(".gitlet");
		gitletFolder.mkdir();
		stagingArea = new File(gitletFolder, "stagingArea");
		stagingArea.mkdir();
		workingDirectory = new File("");
		serializedGitlet = new File(gitletFolder, "gitlet.ser");
		try {
			serializedGitlet.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		branches = new HashMap<String, CommitNode>();
		nextId = 0;
		commitNodes = new HashMap<Integer, CommitNode>();
		filesToTrack = new HashMap<String, String>();
		messageToId = new HashMap<String,  LinkedList<Integer>>();
		filesToUntrack = new ArrayList<String>();
		conflicted = false;
		CommitNode initialCommit = new CommitNode("initial commit", setId());
		File commitFolder = new File(gitletFolder, initialCommit.getId());
		commitFolder.mkdir();
		commitNodes.put(initialCommit.id, initialCommit);
		branches.put("master", initialCommit);
		currentBranch = "master";
		messageToIdHelper(initialCommit);
	}

	
	/**
	 * Take in command line string arguments and feeds them into parseArgs
	 * @param args
	 */
	
	public static void main(String[] args) {
		Gitlet.parseArgs(args);
	}

	/**
	 * Deserializes Gitlet object from gitler.ser
	 * only works if java Gitlet init has been called
	 * @return Gitlet Object from gitlet.ser
	 * 
	 */
	
	public static Gitlet readInObject() {
		File gitletFolder = new File(".gitlet");
		File gitletSer = new File(gitletFolder, "gitlet.ser");
		try {
			FileInputStream fin = new FileInputStream(gitletSer);
			ObjectInputStream ois = new ObjectInputStream(fin);
			Gitlet myGitlet = (Gitlet) ois.readObject();
			ois.close();
			return myGitlet;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException(
				"Please initialize gitlet with command java Gitlet init");
	}

	/**
	 * Serializes current Gitlet object
	 * (writes gitlet object to gitler.ser)
	 */
	
	
	public void writeGitlet() {
		try {
			FileOutputStream fout = new FileOutputStream(this.serializedGitlet);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(this);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * takes in a String array and decides which Gitlet method to use 
	 * 
	 * calls static method readInObject to deserialize a Gitlet Method
	 * probably needs some cleaning up!
	 * 
	 * @param s
	 */
	public static void parseArgs(String[] args) {
		if (args.length == 0){
			System.out.println("Please enter a command.");
			return;
		}
		if (args[0].equals("init")) {
			Gitlet.init();
			return;
		}
		Gitlet gitlet = Gitlet.readInObject();
		if (gitlet.conflicted) {
			if (!(args[0].equals("rm")) && !(args[0].equals("add"))
					&& !(args[0].equals("commit")) && !(args[0].equals("log"))
					&& !(args[0].equals("global-log"))) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}
		} else if (args[0].equals("add") && args.length >= 2) {
			gitlet.add(args[1]);
		} else 	if (args[0].equals("commit") && args.length >= 2) {
			gitlet.commit(args[1]);
		} else if (args[0].equals("commit") && args.length < 2) {
			System.out.println("Please enter a commit message");
			return;
		} else if (args[0].equals("rm") && args.length >= 2) {
			gitlet.rm(args[1]);
		} else if (args[0].equals("status")) {
			gitlet.status();
		} else if (args[0].equals("branch") && args.length >= 2) {
			gitlet.branch(args[1]);
		} else if (args[0].equals("checkout")) {
			if (args.length == 2) {
				gitlet.checkout(args[1]);
			} else if (args.length==3){
				try {
					Integer.parseInt(args[1]);
					gitlet.checkout(Integer.parseInt(args[1]), args[2]);
				} catch (NumberFormatException e){
				}
			}
		} else if (args[0].equals("log")) {
			gitlet.log();			
		} else if (args[0].equals("global-log")){
			gitlet.globalLog();			
		} else if (args[0].equals("find")) {
			gitlet.find(args[1]);
		} else if (args[0].equals("rm-branch") && args.length>=2) {
			gitlet.rmBranch(args[1]);
		} else if (args[0].equals("reset") && args.length>=2) {
			gitlet.reset(Integer.parseInt(args[1]));
		} else if (args[0].equals("merge") && args.length>=2) {
				gitlet.merge(args[1]);
		} else if (args[0].equals("rebase") && args.length>=2) {
				gitlet.rebase(args[1]);
		} else {
			System.out.println("No command with that name exists.");
		}
		gitlet.writeGitlet();
	}

	/**
	 * Copies file into staging folder and marks file for
	 * tracking. If the file is marked for tracking, add instead
	 * unmarks file marked for untracking 
	 * 
	 * @param filename:path to the file user wants to add
	 */

	public void add(String file) {
		if (filesToUntrack.contains(file)) {
			filesToUntrack.remove(file);
		} else {
			copyToStaging(file);
		}
	}

	/**
	 * helperMethod for add. given a fileName, makes a copy of the file and puts the 
	 * copy in the staging area then saves the files ORIGINAL path and STAGINGAREA path
	 * as a key value pair in the filesToTrack HashMap
	 * @param fileName: user provided string that SHOULD be a path to the file
	 */
	
	private void copyToStaging(String file) {
		Path toCopy = Paths.get(file);
		Path targetDirectory = Paths.get(stagingArea.toString());
		Path targetFile = Paths.get(stagingArea.toString(), toCopy.toString());
		try {
			if (!(new File(targetFile.getParent().toString()).exists())) {
				new File(targetFile.getParent().toString()).mkdirs();
			}
			Path copied = Files.copy(toCopy, targetDirectory.resolve(toCopy),
					StandardCopyOption.REPLACE_EXISTING);

			filesToTrack.put(toCopy.toString(), copied.toString());
		} catch (IOException e) {
			System.out.println("File does not exist.");
		}
	}

	/**
	 * sets conflicted state to false;
	 * checks if anything needs to be Tracked or Untracked
	 * gets the currentbranches Head Commit and make a new CommitNode that points to it
	 * 		as well as copying over the Old Head's trackedFiles
	 * creates a commitfolder to hold files that have been marked for tracking
	 * iterates through the filesToTrack hashmap,
	 * moves the file at filesToTrack's entry's value to the newly created commit folder
	 * puts the files ORIGINAL path and COMMIT FOLDER path into the commitNodes hashMap trackedFiles
	 * THEN iterates through filesToUntrack and removes entries in trackedFiles
	 * clears filesToTrack and filesToUntrack
	 * sets currentBranches head to the newly created commit node
	 * adds entry into commitNodes hashmap with key commitID and value CommitNode
	 * 
	 * @param message - user provided commmit message
	 */
	
	public void commit(String message) {
		conflicted = false;
		if (filesToTrack.size() == 0 && filesToUntrack.size() == 0) {
			System.out.println("No changes added to the commit");
			return;
		}
		CommitNode oldHead = branches.get(currentBranch);
		CommitNode newHead = new CommitNode(message, oldHead, setId());
		
		File commitFolder = new File(gitletFolder, newHead.getId());
		commitFolder.mkdir();
		
		Iterator<Entry<String, String>> toTrackIter2 = filesToTrack.entrySet()
				.iterator();
		while (toTrackIter2.hasNext()) {
			Entry<String, String> entry2 = toTrackIter2.next();
			Path pathToFile = Paths.get(entry2.getValue());
			try {
				Path target = Paths.get(commitFolder.toString(),
						entry2.getKey());
				if (!(new File(target.getParent().toString()).exists())) {
					new File(target.getParent().toString()).mkdirs();
				}
				Path a = Files.move(pathToFile, target);
				newHead.trackedFiles.put(entry2.getKey(), a.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// a,b,c, in trackedFiles 
		Iterator<String> toUntrackIter = filesToUntrack.iterator();
		while (toUntrackIter.hasNext()) {
			newHead.trackedFiles.remove(toUntrackIter.next());
		}
		filesToTrack = new HashMap<String,String>();
		filesToUntrack = new ArrayList<String>();
		branches.put(currentBranch, newHead);
		// note to self possible bug?
		commitNodes.put(newHead.id, newHead);
		clearStagingArea();
		conflicted = false;
		messageToIdHelper(newHead);
	}

	private void messageToIdHelper(CommitNode node){
		if (messageToId.containsKey(node.commitMessage)){
			messageToId.get(node.commitMessage).add(node.id);
		} else {
			messageToId.put(node.commitMessage, new LinkedList<Integer>());
			messageToId.get(node.commitMessage).add(node.id);
		}
	}
	/**
	 * removes any empty folders from the staging Area
	 */
	
	private void clearStagingArea(){
		File[] files = stagingArea.listFiles();
		if (files!=null && files.length>0){
			for (File aFile: files){
				clearChild(aFile);
			}
		} 
	}
	
	/**
	 * helperMethod for clearStagingArea
	 * recursively removes any empty directories
	 * if directory isn't empty, goes insides and removes files inside of it
	 * @param file provided by clearStagingArea (a directory inside stagingArea)
	 */
	private void clearChild(File file){
		if (file.isDirectory()){
			File[] files = file.listFiles();
			if (files!=null && files.length>0){
				for (File aFile: files){
					clearChild(aFile);
				}
			} 
			file.delete();
		} else {
			file.delete();
		}
	}
	
	/**
	 * provides an ID for CommitNode object and then increments nextId
	 * @return id for commitNodes
	 */
	
	public int setId() {
		int id = nextId;
		nextId++;
		return id;
	}

	/**
	 * If file is staged for commit (marked for tracking) unstages/unmarks file for tracking
	 * if file exists in current commit and is NOT staged for commit/tracking mark the file for
	 * UNTRACKING as an element in filesToUntrack
	 * @param file userProvided fileName
	 */

	public void rm(String file) {
		CommitNode headCommit = branches.get(currentBranch);
		if (!headCommit.trackedFiles.containsKey(file) && !filesToTrack.containsKey(file)) {
			System.out.println("No reason to remove the file");
		}
		if (filesToTrack.containsKey(file)) {
			Path pathToFile = Paths.get(stagingArea.toString() + File.separator + file);
			try {
				Files.delete(pathToFile);
				clearStagingArea();
			} catch (IOException e) {
				e.printStackTrace();
			}
			filesToTrack.remove(file);
		} else if (headCommit.trackedFiles.containsKey(file)) {
			filesToUntrack.add(file);
		}
	}

	/**
	 * gets current branches head and iterates backward until the initial commit
	 * is reached
	 * prints info from each CommitNode while iterating
	 */

	public void log() {
		CommitNode currentNode = branches.get(currentBranch);
		while (currentNode != null) {
			currentNode.printLog();
			currentNode = currentNode.myPrevious;
		}
	}

	/**
	 * iterate through all entries in commitNodes, printing out information from each commitNode
	 */

	public void globalLog() {
		Set<Entry<Integer, CommitNode>> allNodes = commitNodes.entrySet();
		Iterator<Entry<Integer, CommitNode>> nodesIter = allNodes.iterator();
		while (nodesIter.hasNext()) {
			CommitNode node = nodesIter.next().getValue();
			node.printLog();
		}
	}

	/**
	 * Search for commit nodes in the commitNodes HashMap based on matching commit messages
	 * print out the IDs of each commit node that had the same message
	 * @param msg compare this message to messages in each commitNode 
	 */

	public void find(String msg) {
		if (messageToId.containsKey(msg)){
			LinkedList<Integer> ids = messageToId.get(msg);
			for (Integer id: ids){
				System.out.println(id);
			}
		} else {
			System.out.println("Found no commit with that message");
		}
	}

	/**
	 * Go through Branch hashset Has to check the last state add was at the
	 * stuff marked for tracking & untracking under commit Create a global
	 * variable that tracks which branch it's on so that it can be displayed
	 * here.
	 */

	public void status() {
		printBranches();
		printStagedFiles();
		printUntrackedFiles();
	}

	private void printBranches() {
		System.out.println("=== Branches ===");
		Iterator<Entry<String, CommitNode>> branchesIter = branches.entrySet()
				.iterator();
		while (branchesIter.hasNext()) {
			String toPrint = branchesIter.next().getKey();
			if (toPrint.equals(currentBranch))
				toPrint = "*" + toPrint;
			System.out.println(toPrint);
		}
		System.out.println();

	}

	private void printStagedFiles() {
		System.out.println("=== Staged Files ===");
		Iterator<Entry<String, String>> stagedIter = filesToTrack.entrySet()
				.iterator();
		while (stagedIter.hasNext()) {
			System.out.println(stagedIter.next().getKey());
		}
		System.out.println();
	}

	private void printUntrackedFiles() {
		System.out.println("=== Files Marked for Untracking ===");
		Iterator<String> untrackIter = filesToUntrack.iterator();
		while (untrackIter.hasNext()) {
			System.out.println(untrackIter.next());
		}
		System.out.println();
	}

	/**
	 * Checkout - overloaded method Coordinate with parse_args to determine
	 * argument type & edit accordingly Look for a file_name in tracked files
	 * from the latest commit/ head pointer that may or may not be implemented
	 * 
	 * When checkout is used on a branch_name, if the files are being tracked,
	 * it takes it out of the working directory. If not, it brings copies of tht
	 * files in branch_name into the current directory Need to update the global
	 * current branch variable to this new branch you're checking into
	 * 
	 * @param s
	 */

	public void checkout(String toCheckout) {
		if (branches.containsKey(toCheckout)
				&& toCheckout.equals(currentBranch)) {
			System.out.println("No need to checkout the current branch");
			return;
		}
		if (branches.containsKey(toCheckout)) {
			if (conflicted) {
				System.out
						.println("Cannot do this command until the merge conflict has been resolved.");
				return;
			}
			CommitNode node = branches.get(toCheckout);
			Iterator<Entry<String, String>> filesIter2 = node.trackedFiles
					.entrySet().iterator();
			while (filesIter2.hasNext()) {
				Entry<String, String> entry = filesIter2.next();
				copyToWD(entry.getKey(), entry.getValue());
			}
			currentBranch = toCheckout;
		} else if (branches.get(currentBranch).trackedFiles.containsKey(toCheckout)) {
			String commitSource = branches.get(currentBranch).trackedFiles
					.get(toCheckout);
			copyToWD(toCheckout, commitSource);
		} else {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
			return;
		}

	}

	private void copyToWD(String trgt, String src) {
		try {
			Path source = Paths.get(src);
			Path target = Paths.get(trgt);
			if (!(new File(target.getParent().toString()).exists())) {
				new File(target.getParent().toString()).mkdirs();
			}
			Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void conflictedCopyToWD(String trgt, String src) {
		try {
			Path source = Paths.get(src);
			Path target = Paths.get(trgt+".conflicted");
			if (!(new File(target.getParent().toString()).exists())) {
				new File(target.getParent().toString()).mkdirs();
			}
			Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
//	
//		Path toCopy = Paths.get(location);
//		Path target = Paths.get(workingDirectory.toString());
//		try {
//			if (!(new File(toCopy.getParent().toString()).exists())){
//				new File(target.getParent().toString()).mkdirs();
//			}			
//			Files.copy(toCopy,
//					target.resolve(toCopy.getFileName() + ".conflicted"),
//					StandardCopyOption.REPLACE_EXISTING);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	public void checkout(Integer id, String target) {
		if (commitNodes.containsKey(id)){
			CommitNode checkoutCommit = commitNodes.get(id);
			String source = checkoutCommit.trackedFiles.get(target);
			copyToWD(target, source);
		} else {
			System.out.println("No commit with that id exists. ");
		}
	}

	/**
	 * Branch needs to point at current head node. Check current head node with
	 * latest commit
	 * 
	 * @param branch_name
	 */
	public void branch(String branch) {
		if (branches.containsKey(branch)) {
			System.out.println("A branch with that name already exists.");
		} else {
			CommitNode head = branches.get(currentBranch);
			branches.put(branch, head);
		}
	}

	/**
	 * Redirects itself to that node using file_storer and then checks out each
	 * file using checkout Should also update global current branch variable to
	 * point to that commit node
	 * 
	 * @param ID
	 */

	public void rmBranch(String branch) {
		if (branch.equals(currentBranch)) {
			System.out.println("Cannot remove the current branch");
		} else if (branches.containsKey(branch)) {
			branches.remove(branch);
		} else {
			System.out.println("A branch with that name does not exist");
		}
	}

	public void reset(Integer Id) {
		if (commitNodes.containsKey(Id)) {
			CommitNode node = commitNodes.get(Id);
			branches.put(currentBranch, node);
			Set<Map.Entry<String, String>> filesToWorkingDirectory = node.trackedFiles
					.entrySet();
			Iterator<Entry<String, String>> filesIter2 = filesToWorkingDirectory
					.iterator();
			while (filesIter2.hasNext()) {
				Entry<String, String> entry = filesIter2.next();
				copyToWD(entry.getKey(), entry.getValue());
			}
		} else {
			System.out.println("No commit with that id exists");
		}
	}

	/**
	 * compare tracked files in master head, given branch head as well as
	 * splitpoint if current branch file is unchanged from split but given
	 * branch file IS modified, use the modified version if file is modified in
	 * current branch but not modified in given branch, keep current branch file
	 * if both currrent and given have modified version, move givens version
	 * into current branch with conflicted tag added to its name when in
	 * conflicted stage, user must delete one of the files and then commit these
	 * changes
	 * 
	 * @param branch__name
	 */

	public void merge(String branch) {
		if (currentBranch.equals(branch)){
			System.out.println("Cannot merge a branch with itself.");
		} else if (branches.containsKey(branch)) {
			CommitNode splitNode = getSplitNode(branch).myPrevious;
			HashMap<String, String> splitFiles = splitNode.trackedFiles;
			HashMap<String, String> currentFiles = branches.get(currentBranch).trackedFiles;
			HashMap<String, String> branchFiles = branches.get(branch).trackedFiles;
			Iterator<Entry<String, String>> branchFilesIter = branchFiles
					.entrySet().iterator();
			while (branchFilesIter.hasNext()) {
				Entry<String, String> nodeEntry = branchFilesIter.next();
				if (currentFiles.containsKey(nodeEntry.getKey())) { // theres the same file, check if the values are the same?
					if (!currentFiles.get(nodeEntry.getKey()).equals(nodeEntry.getValue()) && !nodeEntry.getValue().equals(splitFiles.get(nodeEntry.getKey()))){ // A NOT B BUT WHAT IF B IS OLD?  
						conflicted = true;
						conflictedCopyToWD(nodeEntry.getKey(), nodeEntry.getValue());
					} else if (currentFiles.get(nodeEntry.getKey()).equals(
							splitFiles.get(nodeEntry.getKey()))) { //Currentfiles version is "old" overwrite 
						copyToWD(nodeEntry.getKey(), nodeEntry.getValue());
						copyToStaging(nodeEntry.getKey());
					} 
				} else {
					copyToWD(nodeEntry.getKey(),nodeEntry.getValue());
					copyToStaging(nodeEntry.getKey());
				}
			}
			if (!conflicted) {
				commit("Merged " + currentBranch + " with " + branch + ".");
			} else {
				System.out.println("Encountered a merge conflict.");
			}
		} else {
			System.out.println("A branch with that name does not exist.");
		}
	}
	
	/**
	 * gets the first "collision" between 2 branches
	 * @param branch
	 * @return
	 */

	private CommitNode getSplitNode(String branch) {
		CommitNode currentHead = branches.get(currentBranch);
		HashSet<Integer> tempIDs = new HashSet<Integer>();
		while (currentHead != null) {
			tempIDs.add(currentHead.id);
			currentHead = currentHead.myPrevious;
		}
		currentHead = branches.get(currentBranch);
		while (!tempIDs.contains(currentHead.id)) {
			currentHead = currentHead.myPrevious;
		}
		return currentHead;
	}

	/**
	 * find split, make a new commitnodes (all with unique ids) (but the
	 * trackedFiles are the same) of all commit nodes leading up to but not
	 * including the split. take these new nodes and attach to given branchnames
	 * head. move current branches head to "start" of the new nodes created
	 * 
	 * @param branch_name
	 */

	public void rebase(String branch) {
		if (currentBranch.equals(branch)){
			System.out.println("Cannot merge a branch with itself.");
		} else if (branches.containsKey(branch)){
			CommitNode newBase = branches.get(branch);
			HashSet<Integer> tempIDs = new HashSet<Integer>();
			while (newBase != null) {
				tempIDs.add(newBase.id);
				newBase = newBase.myPrevious;
			}
			CommitNode newHead = branches.get(branch);
			Stack<CommitNode> nodeStack = new Stack<CommitNode>();
			CommitNode currentHead = branches.get(currentBranch);
			while (!tempIDs.contains(currentHead.id)) {
				nodeStack.push(currentHead);
				currentHead = currentHead.myPrevious;
			} 
			while (!nodeStack.empty()) {
				CommitNode replayNode = nodeStack.pop();
				CommitNode replayedNode = new CommitNode(replayNode, newHead,
						setId());
				messageToIdHelper(replayedNode);
				newHead = replayedNode;
			}
			branches.put(currentBranch, newHead);			
		} else {
			System.out.println("A branch with that name does not exist.");
		}
	}

}
