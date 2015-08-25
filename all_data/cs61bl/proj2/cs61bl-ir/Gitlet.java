import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.Files;


public class Gitlet implements Serializable{
	HashMap<Integer, Commit> CommitMap = new HashMap<Integer, Commit>(30, .7F );
	HashMap<String, Commit> BranchMap = new HashMap<String,Commit>(30, .7F);
	HashMap<File, File> stagingArea;
	HashMap<File, File> trackedFiles;
	HashMap<File, File> untrackedFiles;
	private Commit currentHead;
	private String currentBranchName;
	private boolean conflictedState;

	public Gitlet() {
		trackedFiles = new HashMap<File, File>(30, .7F);
		untrackedFiles = new HashMap<File, File>(30, .7F);
		stagingArea = new HashMap<File, File>(30, .7F);
		conflictedState = false;
	}

	/**
	 * Creates the .gitlet folder in the current directory if one does not already exist. Creates the initial commit and adds it to our commit map. 
	 * Sets the current head to the initial commit. Also creates the Staging Area folder and the commits folder in the .gitlet folder.
	 */
	public void initialize(){
		File gitletFolder = new File(".gitlet");
		if (gitletFolder.exists()){
			System.err.println("A gitlet version control system already exists in the current directory");
		}else{
			currentBranchName = "master";
			Commit firstCommit = new Commit("initial commit", currentBranchName);
			CommitMap.put(firstCommit.getID(), firstCommit);
			BranchMap.put(currentBranchName, firstCommit);
			this.currentHead = firstCommit;
			gitletFolder.mkdir();
			File stagingArea = new File(".gitlet/stagingarea");
			File commits = new File(".gitlet/commits");
			stagingArea.mkdir();
			commits.mkdir();
			File commitFolder = new File(".gitlet/commits/commit"+ firstCommit.getID());
			commitFolder.mkdir();
		}
	}

	/**
	 * Does one of three things: moves a file from untracked to tracked if it is currently untracked, moves a file from tracked to the staging area if it is 
	 * currently tracked, or moves a file to the staging area if it is currently not tracked or untracked. If the file that is supposed to be added does not 
	 * exist, it sends an error message.
	 * 
	 * @param f
	 *     		The file that the user wants to be added
	 */
	public void add (File f){
		if (!f.exists()){
			System.err.println("File does not exist.");
		}else{
			if (untrackedFiles.containsKey(f)){
				trackedFiles.put(f,f);
				untrackedFiles.remove(f);
			}else{
				String source;
				String sourceFileName;
				File sourceFile;
				if (f.getParent() == null){
					stagingArea.put(f, f);
					source = "/";
					sourceFile = new File(".gitlet/stagingarea" + source);
					sourceFile.mkdirs();
				}else{
					stagingArea.put(new File(f.getParent() + "/" + f.getName()), new File(f.getParent() + "/" + f.getName()));
					source = f.getParent();
					sourceFile = new File(".gitlet/stagingarea" + "/" + source);
					sourceFile.mkdirs();
				}
				sourceFileName = f.getName();
				Path dest = sourceFile.toPath().resolve(sourceFileName);
				try{
					Files.copy(f.toPath(), dest, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
				}catch (IOException e){
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Creates a new commit. The commit is placed in the Gitlet object’s commit map, as well as its branch map because this 
	 * new commit becomes its branch’s pointer. A folder is then created for the commit with the files from the staging area 
	 * and tracked files. Updates the tracked files. Also clears out the staging area in the Gitlet object and in the .gitlet 
	 * directory.
	 *
	 * If the staging area and the untracked files are empty, print error that no changes were added to commit. If no message 
	 * is given, print an error as well.
	 *
	 * @param message
	 *           The message that gets included with the commit.
	 */
	public void commit (String message){
		if (stagingArea.isEmpty() && untrackedFiles.isEmpty()){
			System.err.println("No changes added to the commit.");
		}else if (message.isEmpty()){
			System.err.println("Please enter a commit message.");
		}else{
			Commit newCommit = new Commit(message, currentBranchName);
			newCommit.setID(CommitMap.size());
			BranchMap.put(currentBranchName, newCommit);
			CommitMap.put(newCommit.getID(), newCommit);
			newCommit.setParentID(currentHead);
			for (File f : trackedFiles.values()){
				newCommit.snapShot.put(f.getPath(), f);
			}
			for (File f : stagingArea.values()){
				newCommit.snapShot.put(f.getPath(), f);
			}
			File commitFolder = new File(".gitlet/commits/commit"+ newCommit.getID());
			commitFolder.mkdir();
			String source;
			String sourceFileName;
			for (File f : newCommit.snapShot.values()){
				File sourceFile;
				if (f.getParent() != null){
					source = f.getParent();
					sourceFile = new File(".gitlet/commits/commit" + newCommit.getID() + "/" + source);
					sourceFile.mkdirs();
				}else{
					source = "/";
					sourceFile= new File(".gitlet/commits/commit" + newCommit.getID() +  source);
					sourceFile.mkdirs();
				}
				sourceFileName = f.getName();
				Path dest = sourceFile.toPath().resolve(sourceFileName);
				try{
					Files.copy(f.toPath(), dest, StandardCopyOption.COPY_ATTRIBUTES);
				}catch (IOException e){
					e.printStackTrace();
				}
			}
			for (File f : stagingArea.values()){
				trackedFiles.put(f, f);
			}
			untrackedFiles = new HashMap<File, File>(30, .7F);
			currentHead = newCommit;

			for (File f : stagingArea.values()){
				f = new File(".gitlet/stagingarea/" + f.getPath());
				f.delete();
			}
			stagingArea = new HashMap<File, File>(30, .7F);
			conflictedState = false;
		}
	}

	/**
	 * If the staging area contains the given file, it removes it from the staging area. If the 
	 * given file is currently tracked, it makes it untracked. If the file is in neither the 
	 * staging area nor the tracked files it prints out an error.
	 *
	 * @param fileName
	 *           The name of the file that the user wants to remove
	 */
	public void rm (File fileName){
		if(!stagingArea.containsKey(fileName) && !trackedFiles.containsKey(fileName)){
			System.err.println("No reason to remove the file.");
		}else if (stagingArea.containsKey(fileName)){
			stagingArea.remove(fileName);
			File f = new File(".gitlet/stagingarea/" + fileName.getPath());
			f.delete();
		}else if (trackedFiles.containsKey(fileName)){
			untrackedFiles.put(fileName, fileName);
			trackedFiles.remove(fileName);
		}
	}

	/**
	 * Starting at the most recent commit, goes backwards and prints out every commit that is in that branch. 
	 * The information printed out consists of the commit’s ID number, the date and time that it was committed, 
	 * and its message/comment.
	 */
	public void log(){
		Commit current = currentHead;
		while (current.getID() >= 0) {
			System.out.println("===");
			System.out.println("Commit " + current.getID());
			System.out.println(current.getDate());
			System.out.println(current.getComment());
			System.out.println(" ");
			if (current.getID() == 0) {
				return;
			}
			current = CommitMap.get(current.getParentID());
		}
	}

	/**
	 * Prints out every commit that has been committed. The information printed out consists of the commit’s 
	 * ID number, the date and time that it was committed, and its message/comment.
	 */
	public void globalLog(){
		for (Commit c: CommitMap.values()) {
			System.out.println("===");
			System.out.println("Commit " + c.getID());
			System.out.println(c.getDate());
			System.out.println(c.getComment());
			System.out.println(" ");
		}
	}

	/**
	 * Finds the commit with the message inputted by the user and prints out the ID. If there are multiple 
	 * commits with the same message, all of the commits’ ID’s are printed. If no commit exists with that 
	 * message, print an error.
	 *
	 * @param commitMessage
	 *           The message that we are using to search for the commit(s) to print out.
	 */
	public void find(String commitMessage){
		int counter = 0;
		for (Map.Entry<Integer, Commit> commit : CommitMap.entrySet()){
			if (commit.getValue().getComment().equals(commitMessage)){
				System.out.println(commit.getKey());
				counter++;
			}
		}
		if (counter == 0){
			System.err.println("Found no commit with that message.");
		}
	}

	/**
	 * Prints out the branches that exist in the system, and inserts an asterisk next to the branch that the 
	 * user is currently on. Prints out the staged files. Prints out the untracked files.
	 */
	public void status(){
		System.out.println("=== Branches ===");
		for (String name: BranchMap.keySet()) {
			if (name.equals(currentBranchName)) { 
				System.out.println("*" + name);
			} else {
				System.out.println(name);
			}
		}
		System.out.println(" ");
		System.out.println("=== Staged Files ===");
		for (File f : stagingArea.values()){
			System.out.println(f.getPath());
		}
		System.out.println(" ");
		System.out.println("=== Files Marked for Untracking ===");
		for (File f : untrackedFiles.values()){
			System.out.println(f.getPath());
		}
	}

	/**
	 * Based on user input, determines which version of the checkout function should be carried out.
	 *
	 * @param a
	 *			The arguments that the user inputted. These are used to determine what version of checkout to carry out.
	 */
	public void checkout(String[] a){
		if (a.length == 2){
			if (BranchMap.containsKey(a[1])){
				if (conflictedState) {
					System.err.println("Cannot do this command until the merge conflict has been resolved.");
					return;
				}
				checkoutBranchHelper(a[1]);
			}else{
				checkoutFileHelper(a[1]);
			}
		}else if (a.length == 3){
			checkoutCommitHelper(Integer.parseInt(a[1]), a[2]);
		}
	}

	/**
	 * Overwrites the file in the working directory with the version of the file that is in the commit at the head of the 
	 * current branch. If that file doesn’t exist in the working directory, it still copies the file from the current 
	 * commit into the working directory. Prints out an error if the file doesn’t exist in the current commit.
	 *
	 * @param fileName
	 *           The name of the file in the current commit that is copied into the working directory.
	 */
	public void checkoutFileHelper(String fileName){
		if (!currentHead.snapShot.containsKey(fileName)){
			System.err.println("File does not exist in the most recent commit, or no such branch exists.");
		}else{
			File file = currentHead.snapShot.get(fileName);
			if (file.exists()){
				Path source = new File(".gitlet/commits/commit"+ currentHead.getID()).toPath().resolve(fileName);
				Path dest = new File("").toPath().resolve(fileName);
				try {
					Files.copy(source, dest, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				Path source = new File(".gitlet/commits/commit"+ currentHead.getID()).toPath().resolve(fileName);
				Path dest = new File("").toPath().resolve(fileName);
				try {
					Files.copy(source, dest, StandardCopyOption.COPY_ATTRIBUTES);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Overwrites the file in the working directory with the version of the file that is in the commit with the given ID 
	 * number. If that file doesn’t exist in the working directory, it still copies the file from the commit into the working
	 * directory. Prints out an error if the file doesn’t exist in the commit.
	 *
	 * @param commitID
	 *		The ID number of the commit that the user wants to copy the file from.
	 * @param fileName
	 *		The name of the file in the commit that is copied into the working directory.
	 */
	public void checkoutCommitHelper(int commitID, String fileName){
		if (!CommitMap.containsKey(commitID)){
			System.err.println("No commit with that id exist.");
		}else{
			Commit commit = CommitMap.get(commitID);
			if (!commit.snapShot.containsKey(fileName)){
				System.err.println("File does not exist in that commit.");
			}else{
				File file = commit.snapShot.get(fileName);
				if (file.exists()){
					Path source = new File(".gitlet/commits/commit"+ commitID).toPath().resolve(fileName);
					Path dest = new File("").toPath().resolve(fileName);
					try {
						Files.copy(source, dest, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else{
					Path source = new File(".gitlet/commits/commit"+ commitID).toPath().resolve(fileName);
					Path dest = new File("").toPath().resolve(fileName);
					try {
						Files.copy(source, dest, StandardCopyOption.COPY_ATTRIBUTES);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}	
		}
	}

	/**
	 * Overwrites the files in the working directory with the files that are contained within the commit head of the user’s
	 * inputted branch name. If any of the files don’t exist in the working directory, it still copies them from the commit 
	 * into the working directory. The inputted also becomes the current branch. Prints out an error if the file doesn’t 
	 * exist in the commit or the inputted branch does not exist. Prints out an error if the user is currently on the inputted
	 * branch.
	 *
	 * @param branchName
	 * 				The name of branch that the user wants to checkout.  
	 */
	public void checkoutBranchHelper(String branchName){
		if (!BranchMap.containsKey(branchName)){
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
		}else if (currentBranchName.equals(branchName)) {
			System.out.println("No need to checkout the current branch");
		}else{
			Commit branchCommit = BranchMap.get(branchName);
			for (String s : branchCommit.snapShot.keySet()){
				Path source = new File(".gitlet/commits/commit"+ branchCommit.getID()).toPath().resolve(s);
				Path dest = (new File(s)).toPath();
				try {
					Files.copy(source, dest, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			this.currentHead = branchCommit;
			trackedFiles = new HashMap<File,File>(30, .7F);
			for (String s : branchCommit.snapShot.keySet()){
				File f = new File(s);
				trackedFiles.put(f, f);
			}
			currentBranchName = branchName;
		}
	}

	/**
	 * Creates a new branch with the given branch name. If the branch already exists it prints an error message.
	 *
	 * @param branchName
	 *           The branch name inputted by the user that is created.
	 */
	public void branch (String branchName){
		if (BranchMap.containsKey(branchName)){
			System.err.println("A branch with that name already exists.");
		}else{
			BranchMap.put(branchName, currentHead);
		}
	}

	/**
	 * Removes a branch. If the user is currently on the provided branch name, or the branch name does not exist, it 
	 * prints an error statement.
	 *
	 * @param branchName
	 *           The branch name inputted by the user that is removed.
	 */
	public void rmBranch(String branchName){
		if (BranchMap.containsKey(branchName)){
			if (currentBranchName.equals(branchName)){
				System.err.println("Cannot remove the current branch.");
			}else{
				BranchMap.remove(branchName);
			}
		}else{
			System.err.println("A branch with that name does not exist.");
		}
	}

	/**
	 * All of the files in the current directory are replaced by the files in the commit that has the given ID value. 
	 * The current commit is then changed to the commit with the given ID number.
	 *
	 * @param commitID
	 * 			The ID of the commit whose files are to be copied and who the current commit will become.
	 */
	public void reset(int commitID){
		if (!CommitMap.containsKey(commitID)){
			System.err.println("No commit with that id exists.");
		}else{
			Commit commit = CommitMap.get(commitID);
			for (Map.Entry<String, File> file : commit.snapShot.entrySet()){
				checkoutCommitHelper(commitID, file.getValue().getName());
			}
			currentHead = commit;
		}
	}

	/**
	 * Merges files from branchName into the current branch.
	 * 
	 * @param branchName
	 * 				the name of the branch whose files are to be merged into the current branch.
	 */
	public void merge(String branchName) {
		if (!(BranchMap.containsKey(branchName))) {
			System.err.println("A branch with that name does not exist.");
			return;
		} else {
			Commit namedBranch = BranchMap.get(branchName);
			if (namedBranch.equals(currentHead)) {
				System.err.println("Cannot merge a branch with itself.");
				return;
			}
			boolean conflict;
			boolean sameFileName;
			String conflictedFileName;
			for (String s1 : namedBranch.snapShot.keySet()) {
				File f1 = new File(".gitlet/commits/commit" + namedBranch.getID() + "/" + s1);
				conflict = false;
				sameFileName = false;
				for (String s2 : currentHead.snapShot.keySet()) {
					File f2 = new File(".gitlet/commits/commit" + currentHead.getID() + "/" + s2);
					Path p1 = f1.toPath().subpath(3, f1.toPath().getNameCount());
					Path p2 = f2.toPath().subpath(3, f2.toPath().getNameCount());
					sameFileName = p1.equals(p2);
					boolean sameContent = false;
					try {
						byte[] tmp1 = Files.readAllBytes(f1.toPath());
						byte[] tmp2 = Files.readAllBytes(f2.toPath());
						sameContent = Arrays.equals(tmp1, tmp2);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (sameFileName && !sameContent) { //do the files have the same name and different content?	
						conflict = true;
					}
				}
				if (conflict) {
					conflictedState = true;
					conflictedFileName = f1.toPath().subpath(3, f1.toPath().getNameCount()) + ".conflicted";
					Path source = f1.toPath();
					Path dest = new File(conflictedFileName).toPath();
					try {
						Files.copy(source, dest, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					checkoutCommitHelper(namedBranch.getID(), f1.toPath().subpath(3, f1.toPath().getNameCount()).toString());
					add(new File(f1.toPath().subpath(3, f1.toPath().getNameCount()).toString()));
				}
			}
			if (conflictedState) {
				System.out.println("Encountered a merge conflict.");
			} else {
				commit("Merged " + currentHead.getBranchName() + " with " + branchName + ".");
			}
		}
	}

	/**
	 * Finds the split point between the current branch and the branch given, then snaps off the current branch at 
	 * this point, then reattaches the current branch to the head of the given branch. Commits added to the branch 
	 * given will then have new time-stamps and commit numbers, differing themselves from the commits in the original 
	 * place.
	 *
	 * @param branchName
	 *       The branch name that the snapped branch will attach to.
	 */
	public void rebase(String branchName) {
		String originalBranch = currentBranchName;
		if (branchName.equals(currentBranchName)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		} else if (!BranchMap.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		
		LinkedList<Commit> commitCopies = new LinkedList<Commit>();
		Commit branchCommit = BranchMap.get(branchName);
		Commit pointer1 = currentHead;
		while (pointer1.getID() > 0) {
			if (pointer1.equals(branchCommit)) {
				System.out.println("Already up to date.");
				return;
			} pointer1 = CommitMap.get(pointer1.getParentID());
		}
		Commit pointer2 = currentHead;
		Commit pointer3 = branchCommit;
		HashSet<Commit> visited = new HashSet<Commit>();  
		while (pointer3.getID() != 0) {
			visited.add(pointer3);
			pointer3 = CommitMap.get(pointer3.getParentID());
		} while (!visited.contains(pointer2)) {
			commitCopies.addFirst(pointer2);
			pointer2 = CommitMap.get(pointer2.getParentID());
		}
		currentHead = branchCommit;
		for (Commit c : commitCopies) { // re-commit commit copies
			for (String s : c.snapShot.keySet()) {
				File f = new File(".gitlet/commits/commit" + c.getID() + "/" + s);
				add(f);
			}
			Commit newCommit = new Commit(c.getComment());
			newCommit.setID(CommitMap.size());
			BranchMap.put(originalBranch, newCommit);
			CommitMap.put(newCommit.getID(), newCommit);
			newCommit.setParentID(currentHead);
			for (File f : trackedFiles.values()) {
				newCommit.snapShot.put(f.getPath(), f);
			}
			for (File f : stagingArea.values()){
				newCommit.snapShot.put(f.getPath(), f);
			}
			File commitFolder = new File(".gitlet/commits/commit"+ newCommit.getID());
			commitFolder.mkdir();
			String sourceFileName;
			for (File f : newCommit.snapShot.values()){
				sourceFileName = f.getName();
				Path dest = commitFolder.toPath().resolve(sourceFileName);
				try{
					Files.copy(f.toPath(), dest, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
				} catch (DirectoryNotEmptyException d) {
					d.printStackTrace();
				} catch (IOException e){
					e.printStackTrace();
				}
			}
			for (File f : stagingArea.values()){
				trackedFiles.put(f, f);
			}
			untrackedFiles = new HashMap<File, File>(30, .7F);
			currentHead = newCommit;
			for (File f : stagingArea.values()){
				f = new File(".gitlet/stagingarea/" + f.getPath());
				f.delete();
			}
			stagingArea = new HashMap<File, File>(30, .7F);
			conflictedState = false;
			currentHead = newCommit;
		}
	}

	/**
	 * Reads in the data from the previous run of this program, or initializes a new Gitlet object if one does not exist.
	 * Executes the command that the user issued, if possible. Writes the program's data to a file for use in the next 
	 * run of this program.
	 * 
	 * @param args
	 * 				The user's command and its arguments.
	 * @throws IOException
	 * 				This will not happen.
	 * @throws ClassNotFoundException
	 * 				This will not happen.
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		try{
			Gitlet gitlet;
			File f = new File(".gitlet/Object.ser");
			if (f.exists()) {
				FileInputStream fileIn = new FileInputStream (".gitlet/Object.ser");
				ObjectInputStream ois = new ObjectInputStream(fileIn);
				gitlet = (Gitlet) ois.readObject();
				fileIn.close();
			} else {
				gitlet = new Gitlet();
			}
			String command = args[0];
			String[] conflictedStateNotAllowed = {"init", "checkout", "branch", "rm-branch", "reset", "merge", "rebase"}; //the 2 checkout cases allowed are handled in the checkout method
			boolean commandAllowed = true;
			if (gitlet.conflictedState) {
				for (String s : conflictedStateNotAllowed) {
					if (command.equals(s)) {
						commandAllowed = false;
					}
				}
			} 
			if (!commandAllowed) {
				System.err.println("Cannot do this command until the merge conflict has been resolved.");
			} else {
				switch (command) {
				case "init": 
					gitlet.initialize();
					break;
				case "add":
					File toBeAdded = new File(args[1]);
					gitlet.add(toBeAdded);
					break;
				case "commit":
					if (args.length == 1) {
						System.err.println("Please enter a commit message.");
					} else {
						gitlet.commit(args[1]);
					}
					break;
				case "rm":
					File toBeRemoved = new File(args[1]);
					gitlet.rm(toBeRemoved);
					break;
				case "log":
					gitlet.log();
					break;
				case "global-log":
					gitlet.globalLog();
					break;
				case "find":
					gitlet.find(args[1]);
					break;
				case "status":
					gitlet.status();
					break;
				case "checkout":
					gitlet.checkout(args);
					break;
				case "branch":
					gitlet.branch(args[1]);
					break;
				case "rm-branch":
					gitlet.rmBranch(args[1]);
					break;
				case "reset": 
					gitlet.reset(Integer.parseInt(args[1]));
					break;
				case "merge":
					gitlet.merge(args[1]);
					break;
				case "rebase":
					gitlet.rebase(args[1]);
					break;
				default:
					System.err.println("Not a command");
					break;
				}
			}
			FileOutputStream fileOut = new FileOutputStream (".gitlet/Object.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fileOut);
			oos.writeObject(gitlet);
			fileOut.close();
		}catch (IOException e){
			e.printStackTrace();
		} 

	}

}
