
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class Gitlet implements Serializable {
	public String principalPath;
	private Commit latestCommit;
	private String latestCommitPath;
	private Branch activeBranch;
	private int commitId = 0;
	private String dotgitlet;
	private String staging;
	private boolean conflicted = false;

	private HashSet<String> changedFiles = new HashSet<String>();
	private HashSet<String> untrackedFiles = new HashSet<String>();
	private HashMap<String, String> stagedFiles = new HashMap<String, String>();
	private HashMap<Integer, Commit> commitByID = new HashMap<Integer, Commit>();
	private HashMap<String, ArrayList<Commit>> commitByMsg = new HashMap<String, ArrayList<Commit>>();
	private HashMap<String, Branch> branchByName = new HashMap<String, Branch>();

	public static void main(String arg[]) {
		try {
			Path dGitlet = Paths.get(".gitlet");
			Gitlet project = null;
			if (Files.exists(dGitlet)) {
				project = (Gitlet) deserialize(dGitlet.resolve("Gitlet.ser"));
				if (arg[0].equals("init")) {
					System.out
							.println("A gitlet version control system already exists in the current directory.");
				}
				if (project.conflicted)
					mainConflictedStateHandler(arg, project);
				if (arg[0].equals("log"))
					System.out.println(project.log());
				if (arg[0].equals("global-log"))
					System.out.println(project.global_log());
				if (arg[0].equals("add"))
					project.add(arg[1]);
				if (arg[0].equals("commit")) {
					if (arg.length != 2) {
						System.out.println("Please enter a commit message.");
					}
					project.commit(arg[1]);
				}
				if (arg[0].equals("rm"))
					project.rm(arg[1]);
				if (arg[0].equals("find"))
					project.find(arg[1]);
				if (arg[0].equals("status"))
					project.status();
				if (arg[0].equals("checkout"))
					mainCheckoutShortener(arg, project);
				if (arg[0].equals("branch"))
					project.branch(arg[1]);
				if (arg[0].equals("rm-branch"))
					project.rm_branch(arg[1]);
				if (arg[0].equals("reset"))
					project.reset(Integer.parseInt(arg[1]));
				if (arg[0].equals("merge")) {
					if (!project.branchByName.containsKey(arg[1])) {
						System.out
								.println("A branch with that name does not exist.");
						return;
					}
					project.merge(project.branchByName.get(arg[1]));
				}
				if (arg[0].equals("rebase")) {
					if (!project.branchByName.containsKey(arg[1])) {
						System.out
								.println("A branch with that name does not exist.");
						return;
					}
					project.rebase(project.branchByName.get(arg[1]));
				}

			} else if (arg[0].equals("init")) {
				project = new Gitlet();
				project.initialize();
			}
			serialize(project, dGitlet.resolve("Gitlet.ser"));
		} catch (IOException e) {
		}
	}

	/**
	 * Used when calling "java Gitlet init". This sets up both java environment
	 * and file system for gitlet repo
	 * 
	 * @throws IOException
	 */
	public void initialize() throws IOException {
		principalPath = Paths.get("").toString();
		Files.createDirectories(Paths.get(principalPath));
		dotgitlet = Paths.get(principalPath).resolve(".gitlet").toString();
		Files.createDirectories(Paths.get(dotgitlet));
		Files.createFile(Paths.get(dotgitlet).resolve("Gitlet.ser"));
		staging = Paths.get(dotgitlet).resolve("staging").toString();
		Files.createDirectories(Paths.get(staging));
		Branch newBranch = makeBranch("master", this);
		newBranch.setBranchPath(Paths.get(this.dotgitlet).resolve("master")
				.toString());
		branchByName.put(newBranch.name(), newBranch);
		activeBranch = newBranch;
		latestCommit = makeCommit("initial commit", this);
		commitByID.put(latestCommit.getID(), latestCommit);
		commitByMsg.put(latestCommit.getMsg(), ALMaker(latestCommit, null));
		latestCommit.finishingCommit();
		activeBranch.setBranchHead(latestCommit);
	}

	/**
	 * Used when calling "java Gitlet add". Add file to staging area or remove
	 * untracking label.
	 * 
	 * !!!!Unchanged files will NOT be added!!!
	 * 
	 * @param fileToAdd
	 *            relative string path of the file to be added.
	 * @throws IOException
	 */
	public void add(String fileToAdd) throws IOException {
		Path filePath = Paths.get(fileToAdd);
		if (!Files.exists(filePath)) {
			System.out.println("File does not exist.");
		} else if (Files.isDirectory(filePath)) {
			System.out.println("File does not exist.");
		} else if (untrackedFiles.contains(fileToAdd)) {
			untrackedFiles.remove(fileToAdd);
		} else {
			boolean changed = false;
			if (!latestCommit.containsKey(fileToAdd)) {
				changed = true;
			} else {
				Path oldFilePath = Paths.get(latestCommit.trackedPaths
						.get(fileToAdd));
				if (!isSameFile(filePath, oldFilePath)) {
					changed = true;
				}
			}
			if (changed) {
				StagingHelper(filePath.toString(), this);
			}
		}
	}

	/**
	 * Used when calling "java Gitlet commit". Creates the commit objects and
	 * move updated files in its storage folder.
	 * 
	 * @param msg
	 *            string message attached to the commit
	 * @throws IOException
	 */
	public void commit(String msg) throws IOException {
		if (stagedFiles.isEmpty() && untrackedFiles.isEmpty()) {
			System.out.println("No changes added to the commit.");
			return;
		}
		Commit temp = latestCommit;
		latestCommit = makeCommit(msg, this);
		latestCommit.setPrevCommit(temp);
		latestCommit.inheritTrackedPaths(temp.getTrackedPaths());
		for (String fileName : untrackedFiles) {
			if (latestCommit.getTrackedPaths().containsKey(fileName)) {
				latestCommit.getTrackedPaths().remove(fileName);
			}
		}
		for (HashMap.Entry<String, String> fileSet : stagedFiles.entrySet()) {
			Path file = Paths.get(fileSet.getValue());
			Path newPath = Paths.get(latestCommitPath).resolve(
					file.subpath(Paths.get(staging).getNameCount(),
							file.getNameCount()));
			String fileName = newPath.subpath(
					Paths.get(latestCommitPath).getNameCount(),
					newPath.getNameCount()).toString();
			if (!latestCommit.containsKey(file.toString())) {
				if (newPath.getNameCount()
						- Paths.get(latestCommitPath).getNameCount() > 1) {
					Files.createDirectories(newPath);
				}
			}
			Files.move(file, newPath, StandardCopyOption.REPLACE_EXISTING);
			if (Files.exists(newPath)) {
				latestCommit.addToTracked(fileName, newPath);
			}
		}

		stagedFiles.clear();
		deleteDirectory(Paths.get(staging)); // delete the staging folder
		Files.createDirectories(Paths.get(staging));// create an empty
													// staging
		untrackedFiles.clear();
		commitByID.put(latestCommit.getID(), latestCommit);
		ArrayList source = commitByMsg.get(latestCommit.getMsg());
		commitByMsg.put(latestCommit.getMsg(), ALMaker(latestCommit, source));
		latestCommit.finishingCommit();
		activeBranch.setBranchHead(latestCommit);
		conflicted = false;
	}

	/**
	 * Used when calling "java Gitlet rm". Make files tracked by the parent
	 * commit as untracking. Or remove files from staging if it has been staged.
	 * 
	 * @param fileName
	 *            relative string file path to be removed.
	 * @throws IOException
	 */
	public void rm(String fileName) throws IOException {
		if (!latestCommit.getTrackedPaths().containsKey(fileName) // unstage if
																	// staged
				&& !Files.exists(Paths.get(staging).resolve(fileName))) {
			System.out.println("No reason to remove the file.");
			System.exit(1);
		}
		if (Files.exists(Paths.get(staging).resolve(fileName))) {
			Files.deleteIfExists(Paths.get(staging).resolve(fileName));
			stagedFiles.remove(fileName);
		} else {
			untrackedFiles.add(fileName); // untrack if tracked by parents
		}
	}

	/**
	 * Used when calling "java Gitlet log". Report the commit history on current
	 * branch until initial commit
	 * 
	 * @return
	 */
	public String log() {
		String result = "";
		Commit p = latestCommit;
		while (p != null) {
			result += log(p);
			p = p.getPrev();
		}
		return result;
	}

	/**
	 * Used when calling "java Gitlet global-log". Report the commit history of
	 * all commits.
	 * 
	 * @return
	 */
	public String global_log() {
		String result = "";
		for (Entry<Integer, Commit> commitIDSet : commitByID.entrySet()) {
			result += log(commitIDSet.getValue());
		}
		return result;
	}

	/**
	 * Used when calling "java Gitlet find". Print commit ids of commit with
	 * matching commit msg.
	 * 
	 * @param commitMsg
	 *            the string massage of the desired commit
	 */
	public void find(String commitMsg) {
		if (commitByMsg.containsKey(commitMsg)) {
			ArrayList commits = commitByMsg.get(commitMsg);
			String result = "";
			for (int i = 0; i < commits.size(); i++) {
				result += ((Commit) commits.get(i)).getID() + "\n";
			}
			System.out.println(result);
			return;
		} else {
			System.out.println("Found no commit with that message.");
			return;
		}
	}

	/**
	 * Used when calling "java Gitlet status". Report branches, added files and
	 * files marked for untracking in current state.
	 */
	public void status() {

		String printout = "";
		printout = printout + "=== Branches ===\n";
		for (HashMap.Entry<String, Branch> branchNameSet : branchByName
				.entrySet()) {
			if (branchNameSet.getValue().name().equals(activeBranch.name())) {
				printout += "*" + branchNameSet.getValue().name() + "\n";
			} else {
				printout += branchNameSet.getValue().name() + "\n";
			}
		}
		printout += "\n=== Staged Files ===\n";
		for (HashMap.Entry<String, String> stagedFileSet : stagedFiles
				.entrySet()) {
			printout += stagedFileSet.getKey() + "\n";
		}
		printout += "\n=== Files Marked for Untracking ===\n";
		for (String untrackedFile : untrackedFiles) {
			printout += untrackedFile + "\n";
		}
		System.out.println(printout);
	}

	/**
	 * Used when calling "java Gitlet branch". Create a branch object
	 * 
	 * @param branchName
	 *            name of the branch object.
	 */
	public void branch(String branchName) {
		if (branchByName.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
			System.exit(1);
		}
		Branch newBranch = makeBranch(branchName, this);
		newBranch.setBranchPath(Paths.get(this.dotgitlet).resolve(branchName)
				.toString());
		newBranch.setBranchHead(latestCommit);
		branchByName.put(newBranch.name(), newBranch);
	}

	/**
	 * Used when calling "java Gitlet checkout [branch]" and "java Gitlet reset"
	 * Moves files tracked by the given commit into working directory.
	 * 
	 * @param c
	 *            commit of interest.
	 * @throws IOException
	 */
	public void checkoutCommitFileMover(Commit c) throws IOException {
		for (HashMap.Entry<String, String> trackedPath : c.getTrackedPaths()
				.entrySet()) {
			Path filePathStored = Paths.get(trackedPath.getValue());
			int commitPathLength = Paths.get(".gitlet/master").getNameCount() + 1;
			Path filePathRelative = filePathStored.subpath(commitPathLength,
					filePathStored.getNameCount());
			if (filePathRelative.getNameCount() > 2) {
				Files.createDirectories(filePathRelative);// a trick handles the
															// case when user
															// inputs
															// someDir/someFile
			}
			Files.copy(filePathStored,
					Paths.get(principalPath).resolve(filePathRelative),
					StandardCopyOption.REPLACE_EXISTING);
		}
	}

	/**
	 * Used when calling "java Gitlet checkout [branch]" Move files tracked by
	 * the head of the given branch into working directory
	 * 
	 * @param branchName
	 *            name of the branch to check out
	 * @throws IOException
	 */
	public void checkoutBranch(String branchName) throws IOException {
		Branch switchTo = branchByName.get(branchName);
		activeBranch = switchTo;
		latestCommit = switchTo.getBranchHead();
		checkoutCommitFileMover(latestCommit);
	}

	/**
	 * Used when calling "java Gitlet checkout [id] [fileName]" and
	 * "java Gitlet checkout [fileName]" Move the file specified by file path to
	 * working directory.
	 * 
	 * @param filePath
	 *            the path to the file of interest.
	 * @throws IOException
	 */
	public void checkoutFileFileMover(String filePath) throws IOException {
		Path filePathStored = Paths.get(filePath);
		int commitPathLength = Paths.get(activeBranch.getBranchPath())
				.getNameCount() + 1;
		Path filePathRelative = filePathStored.subpath(commitPathLength,
				filePathStored.getNameCount());
		if (filePathRelative.getNameCount() > 1
				&& !Files.exists(filePathRelative)) {
			Files.createDirectories(filePathRelative);
		}
		Files.copy(filePathStored,
				Paths.get(principalPath).resolve(filePathRelative),
				StandardCopyOption.REPLACE_EXISTING);
	}

	/**
	 * Used when calling "java Gitlet checkout [id] [fileName]" Move the file in
	 * commit of given id to working directory
	 * 
	 * @param id
	 *            id of the commit to which the file is tracked
	 * @param filePath
	 *            path of the file to be checked out
	 * @throws IOException
	 */
	public void checkoutFileByID(int id, String filePath) throws IOException {
		Commit wanted = commitByID.get(id);
		String fileAddress = wanted.getTrackedPaths().get(filePath);
		checkoutFileFileMover(fileAddress);
	}

	/**
	 * Used when calling "java Gitlet checkout [fileName]" Move the file in the
	 * last commit to working directory
	 * 
	 * @param filePath
	 *            name of the file we desire
	 * @throws IOException
	 */
	public void checkoutFileLastCommit(String filePath) throws IOException {
		String fileAddress = latestCommit.getTrackedPaths().get(filePath);
		checkoutFileFileMover(fileAddress);
	}

	/**
	 * Used when calling "java Gitlet rm" Delete a branch object.
	 * 
	 * @param branchName
	 *            name of the branch to delete
	 */
	public void rm_branch(String branchName) {
		if (branchName.equals(activeBranch.name())) {
			System.out.println("Cannot remove the current branch.");
			return;
		}
		if (!branchByName.containsKey(branchName)) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		branchByName.remove(branchName);
	}

	/**
	 * Used when calling "java Gitlet reset" check out all the files tracked by
	 * the commit of given id into working directory
	 * 
	 * @param id
	 *            id of the commit
	 * @throws IOException
	 */
	public void reset(int id) throws IOException {
		if (!commitByID.containsKey(id)) {
			System.out.println("No commit with that id exists.");
			return;
		}
		Commit wanted = commitByID.get(id);
		latestCommit = wanted;
		activeBranch.setBranchHead(wanted);
		checkoutCommitFileMover(wanted);
	}

	/**
	 * Used when calling "java Gitlet merge" Merge two branches
	 * 
	 * @param Br
	 *            the name of the branch to merge with current branch
	 * @throws IOException
	 */
	public void merge(Branch Br) throws IOException {
		mergeArgIsOk(Br, this);
		Commit splitPoint = splitFinder(activeBranch, Br);
		Commit A = activeBranch.getBranchHead();
		Commit B = Br.getBranchHead();
		for (HashMap.Entry<String, String> fileSetA : A.getTrackedPaths()
				.entrySet()) {
			if (!B.getTrackedPaths().containsKey(fileSetA.getKey())) {
				untrackedFiles.add(fileSetA.getKey());
			}
		}
		for (HashMap.Entry<String, String> fileSetB : B.getTrackedPaths()
				.entrySet()) {
			if (!A.getTrackedPaths().containsKey(fileSetB.getKey())) {
				StagingHelper(fileSetB.getValue(), this);
			} else if (splitPoint.getTrackedPaths().containsKey(
					fileSetB.getKey())) {
				Path splitPath = Paths.get(splitPoint.getTrackedPaths().get(
						fileSetB.getKey()));
				Path BPath = Paths.get(B.getTrackedPaths().get(
						fileSetB.getKey()));
				if (isSameFile(splitPath, BPath)) {// file in b is not changed
					continue;
				} else {
					Path APath = Paths.get(A.getTrackedPaths().get(
							fileSetB.getKey()));
					if (isSameFile(splitPath, APath)) {// file in B is changed,
														// but not in A
						MergeStagingHelper(fileSetB.getValue(), this);
					} else {// file is changed in both A and B, conflicted
							// state!!!
						conflicted = true;
						Path filePathStored = Paths.get(fileSetB.getValue());
						int commitPathLength = Paths.get(Br.getBranchPath())
								.getNameCount() + 1;
						Path filePathRelative = filePathStored
								.subpath(commitPathLength,
										filePathStored.getNameCount());
						if (filePathRelative.getNameCount() > 1
								&& !Files.exists(filePathRelative)) {
							Files.createDirectories(filePathRelative);
						}
						Path conflictedFilePath = Paths.get(filePathRelative
								.toString() + ".conflicted");
						Files.createDirectories(conflictedFilePath);
						Files.copy(filePathStored, conflictedFilePath,
								StandardCopyOption.REPLACE_EXISTING);
					}
				}
			}
		}
		if (conflicted) {
			System.out.println("Encountered a merge conflict.");
			return;
		} else {
			String msg = "Merged " + activeBranch.name() + " with " + Br.name();
			commit(msg);
		}
	}

	/**
	 * Used when calling "java Gitlet rebase" Rebase the current branch to the
	 * front of the given branch
	 * 
	 * @param branch
	 *            the name of the given branch to which current branch rebase
	 *            to.
	 * @throws IOException
	 */
	public void rebase(Branch branch) throws IOException {
		if (activeBranch == branch) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		Commit endOfGivenBranch = branch.getBranchHead();
		Commit splitPoint = splitFinder(activeBranch, branch);
		ArrayList<Commit> rebasedCommits = new ArrayList();
		rebaseCaseChecker(branch, this, splitPoint);
		// copying commit objects and file structure
		Commit p2 = activeBranch.getBranchHead();
		Commit copiedCommit = reBaseCopyHelper(p2, branch, this);
		activeBranch.setBranchHead(copiedCommit);
		rebasedCommits.add(copiedCommit);
		p2 = p2.getPrev();
		while (p2 != splitPoint) {
			Commit temp = reBaseCopyHelper(p2, branch, this);
			copiedCommit.setPrevCommit(temp);
			copiedCommit = temp;
			rebasedCommits.add(copiedCommit);
			p2 = p2.getPrev();
		}
		copiedCommit.setPrevCommit(branch.getBranchHead());
		// prepare a list of files in head of given branch for scanning
		ArrayList<String> filesToCheck = new ArrayList<String>();
		for (HashMap.Entry<String, String> fileSet : endOfGivenBranch
				.getTrackedPaths().entrySet()) {
			filesToCheck.add(fileSet.getKey());
		}
		// propagate changes
		ArrayList<String> changedInRebase = new ArrayList<String>();
		for (int i = rebasedCommits.size() - 1; i >= 0; i--) {
			Commit thisRoundC = rebasedCommits.get(i);
			for (String fileName : filesToCheck) {
				String inRebase = thisRoundC.getTrackedPaths().get(fileName);
				String inSplit = splitPoint.getTrackedPaths().get(fileName);
				Path newFilePath = Paths.get(branch.getBranchPath()).resolve(
						"commit" + thisRoundC.getID() + "/" + fileName);
				if (inRebase == null) {
					if (newFilePath.getNameCount() > 4) {
						Files.createDirectories(newFilePath);
					}
					Files.copy(
							Paths.get(endOfGivenBranch.getTrackedPaths().get(
									fileName)), newFilePath,
							StandardCopyOption.REPLACE_EXISTING);
					thisRoundC.addToTracked(fileName, newFilePath);
				} else if (inSplit != null) {
					Path inRebaseP = Paths.get(inRebase);
					Path inSplitP = Paths.get(inSplit);
					if (isSameFile(inRebaseP, inSplitP)) {
						// replace file, do not check it again for next commit
						if (!changedInRebase.contains(fileName)) {
							Files.copy(Paths.get(endOfGivenBranch
									.getTrackedPaths().get(fileName)),
									newFilePath,
									StandardCopyOption.REPLACE_EXISTING);
							thisRoundC.addToTracked(fileName, newFilePath);
						}
					} else
						changedInRebase.add(fileName);
				} else
					changedInRebase.add(fileName);
			}
		}
		updateWorkingDir(this);
	}

	/*
	 * 
	 * 
	 * 
	 * STATIC FUNCTIONS BELOW
	 */
	/**
	 * Used in rebase determine if the given case can be simplified. Possible
	 * cases are given branch is in histroy of current branch.(already
	 * up-to-date) Or current branch is in history of given branch.(Move branch
	 * head pointer)
	 * 
	 * @param branch
	 *            given branch
	 * @param project
	 *            project reference pointer
	 * @param splitPoint
	 *            the split of current and given branch
	 * @throws IOException
	 */
	public static void rebaseCaseChecker(Branch branch, Gitlet project,
			Commit splitPoint) throws IOException {
		// Check for non-replaying commit case
		Commit endOfGivenBranch = branch.getBranchHead();
		Commit p1 = endOfGivenBranch;
		while (p1 != splitPoint.getPrev()) {
			if (project.activeBranch.getBranchHead() == p1) {
				project.activeBranch.setBranchHead(branch.getBranchHead());
				updateWorkingDir(project);
				return;
			}
			p1 = p1.getPrev();
		}
		// check for already up-to-date case
		Commit pointer = project.activeBranch.getBranchHead();
		while (pointer != splitPoint.getPrev()) {
			if (endOfGivenBranch == pointer) {
				System.out.println("Already up-to-date.");
				return;
			}
			pointer = pointer.getPrev();
		}
	}

	/**
	 * check if the argument to merge is valid
	 * 
	 * @param Br
	 *            given branch to merge
	 * @param project
	 *            project reference pointer
	 */
	public static void mergeArgIsOk(Branch Br, Gitlet project) {
		if (!project.branchByName.containsKey(Br.name())) {
			System.out.println("A branch with that name does not exist.");
			return;
		}
		if (Br.name().equals(project.activeBranch.name())) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		}
	}

	/**
	 * handles conflicted states in main method.
	 * 
	 * @param arg
	 *            arguments passed into main.
	 * @param project
	 *            project reference pointer
	 */
	public static void mainConflictedStateHandler(String arg[], Gitlet project) {
		Path dGitlet = Paths.get(".gitlet");
		if (arg[0].equals("branch") || arg[0].equals("rm-branch")
				|| arg[0].equals("reset") || arg[0].equals("merge")
				|| arg[0].equals("rebase")) {
			System.out
					.print("Cannot do this command until the merge conflict has been resolved.");
			serialize(project, dGitlet.resolve("Gitlet.ser"));
			System.exit(1);
		}
		if (arg[0].equals("branch")) {
			if (arg.length > 1) {
				if (project.branchByName.containsKey(arg[1])) {
					System.out
							.println("Cannot do this command until the merge conflict has been resolved.");
					serialize(project, dGitlet.resolve("Gitlet.ser"));
					System.exit(1);
				}
			}
		}
	}

	/**
	 * handles different types of checkout in main method
	 * 
	 * @param arg
	 *            arguments passed into main
	 * @param project
	 *            project reference pointer
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void mainCheckoutShortener(String arg[], Gitlet project)
			throws NumberFormatException, IOException {
		if (arg.length == 2) {
			if (project.activeBranch.name().equals(arg[1])) {
				System.out.println("No need to checkout the current branch.");
				return;
			}
			if (project.branchByName.containsKey(arg[1])) {
				project.checkoutBranch(arg[1]);
			} else if (project.latestCommit.trackedPaths.containsKey(arg[1])) {
				project.checkoutFileLastCommit(arg[1]);
			} else {
				System.out
						.println("File does not exist in the most recent commit, or no such branch exists.");
			}
		} else if (arg.length == 3) {
			project.checkoutFileByID(Integer.parseInt(arg[1]), arg[2]);
		}
	}

	/**
	 * update the files in working directory to be consistent the front of
	 * current branch
	 * 
	 * @param project
	 *            project reference pointer
	 * @throws IOException
	 */
	public static void updateWorkingDir(Gitlet project) throws IOException {
		Commit mostRecent = project.activeBranch.getBranchHead();
		for (HashMap.Entry<String, String> fileSet : mostRecent
				.getTrackedPaths().entrySet()) {
			Path namePath = Paths.get(fileSet.getKey());
			Path origAddress = Paths.get(fileSet.getValue());
			if (namePath.getNameCount() > 1
					&& !Files.exists(Paths.get(project.principalPath).resolve(
							namePath))) {
				Files.createDirectories(Paths.get(project.principalPath)
						.resolve(namePath));
			}
			Files.copy(origAddress,
					Paths.get(project.principalPath).resolve(namePath),
					StandardCopyOption.REPLACE_EXISTING);
		}
	}

	/**
	 * a staging helper specific for merge
	 * 
	 * @param comingFrom
	 *            the address of the commit storage folder
	 * @param project
	 *            project reference pointer
	 * @throws IOException
	 */
	public static void MergeStagingHelper(String comingFrom, Gitlet project)
			throws IOException {
		Path origAddress = Paths.get(comingFrom);
		Path filePath = Paths.get(comingFrom);
		if (filePath.startsWith(".gitlet")) {// remove
												// .gitlet/branchName/commitName
												// part in merge
			filePath = filePath.subpath(3, filePath.getNameCount());
		}
		if (filePath.getNameCount() > 1
				&& !Files.exists(Paths.get(project.principalPath).resolve(
						filePath))) {
			Files.createDirectories(Paths.get(project.principalPath).resolve(
					filePath));
		}
		Files.copy(origAddress, filePath, StandardCopyOption.REPLACE_EXISTING);
		StagingHelper(filePath.toString(), project);
	}

	/**
	 * helper that copy files to staging
	 * 
	 * @param comingFrom
	 *            path to the origin of files
	 * @param project
	 *            project reference pointer
	 * @throws IOException
	 */
	public static void StagingHelper(String comingFrom, Gitlet project) // fileToAdd,
																		// two
																		// senarios,
																		// one
																		// from
																		// working
																		// directory(normal
																		// staging),
																		// one
																		// from
																		// commit
																		// files
																		// (merge
																		// staging)
			throws IOException {
		Path filePath = Paths.get(comingFrom);

		if (filePath.getNameCount() > 1
				&& !Files.exists(Paths.get(project.staging).resolve(filePath))) {
			Files.createDirectories(Paths.get(project.staging)
					.resolve(filePath));
		}

		project.stagedFiles.put(filePath.toString(), Paths.get(project.staging)
				.resolve(filePath).toString());
		Files.copy(filePath, Paths.get(project.staging).resolve(filePath),
				StandardCopyOption.REPLACE_EXISTING,
				StandardCopyOption.COPY_ATTRIBUTES);
		if (project.untrackedFiles.contains(filePath.toString())) {
			project.untrackedFiles.remove(filePath.toString());
		}
	}

	/**
	 * find the split point between two branches
	 * 
	 * @param A
	 *            branch a
	 * @param B
	 *            branch b
	 * @return
	 */
	public static Commit splitFinder(Branch A, Branch B) {
		HashSet<Integer> historyB = new HashSet<Integer>();
		Commit pB = B.getBranchHead();
		while (pB != null) {
			historyB.add(pB.getID());
			pB = pB.getPrev();
		}
		Commit pA = A.getBranchHead();
		while (pA != null) {
			if (historyB.contains(pA.getID())) {
				return pA;
			}
			pA = pA.getPrev();
		}
		return null;
	}

	/**
	 * a copier used in rebase. copies the given commit and establish new commit
	 * storage folder in appropriate place.
	 * 
	 * @param c
	 *            commit to be copied.
	 * @param onBranch
	 *            branch for new commit storage folder to be on
	 * @param project
	 *            project reference pointer
	 * @return copied commit
	 * @throws IOException
	 */
	public static Commit reBaseCopyHelper(Commit c, Branch onBranch,
			Gitlet project) throws IOException {
		// System.out.println("calling reBase copier");// TODO
		Commit rtn = new Commit(c.getMsg(), project.commitId);
		rtn.getTrackedPaths().putAll(c.getTrackedPaths());
		project.commitByID.put(rtn.getID(), rtn);

		Path branchPath = Paths.get(onBranch.getBranchPath());
		Path currPath = branchPath.resolve("commit" + project.commitId);
		// System.out.println("Creating new commit" + rtn.getID() + " at "
		// + currPath);
		Files.createDirectories(currPath);

		for (HashMap.Entry<String, String> fileSet : c.getTrackedPaths()
				.entrySet()) {
			Path oldCommitFileName = Paths.get(fileSet.getKey());
			if (oldCommitFileName.getNameCount() > 1
					&& !Files.exists(currPath.resolve(fileSet.getKey()))) {
				Files.createDirectories(currPath.resolve(fileSet.getKey()));
			}

			// System.out.println("Copying " + fileSet.getKey() + "form to :");
			// System.out.println(Paths.get(fileSet.getValue()));
			// System.out.println(currPath.resolve(fileSet.getKey()));
			Files.copy(Paths.get(fileSet.getValue()),
					currPath.resolve(fileSet.getKey()),
					StandardCopyOption.REPLACE_EXISTING);

		}
		project.commitId++;
		rtn.finishingCommit();
		return rtn;
	}

	/**
	 * Adding object to an arraylist, create a new arraylist is there is none.
	 * 
	 * @param obj
	 *            object to be added.
	 * @param source
	 *            arraylist to receive object.
	 * @return
	 */
	public static ArrayList ALMaker(Object obj, ArrayList source) {
		if (source == null) {
			ArrayList arr = new ArrayList();
			arr.add(obj);
			return arr;
		} else {
			source.add(obj);
			return source;
		}

	}

	/**
	 * helper function that process commit object and return string with desired
	 * format
	 * 
	 * @param c
	 *            commit object to be processed.
	 * @return string of the information about commit
	 */
	public static String log(Commit c) { // helper function to print out log
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatted = myFormat.format(c.commitTime());

//		String trackedPathsS = "tracked files Names are: \n";
//		for (HashMap.Entry<String, String> pathSet : c.trackedPaths.entrySet()) { // TODO
//			String fileContent = null;
//			try {
//				BufferedReader br = new BufferedReader(new FileReader(
//						pathSet.getValue()));
//				for (String line; (line = br.readLine()) != null;) {
//					fileContent += "\n" + "\'" + line + "\'" + "\n";
//				}
//			} catch (IOException e) {
//			}
//			trackedPathsS = trackedPathsS + "\n" + pathSet.getKey() + "\n"
//					+ "with content: \n" + fileContent;
//
//		}

		String result = "===\n" + "commit " + c.getID() + "\n" + formatted
				+ "\n" + c.getMsg() + "\n" + "\n"; // TODO
		return result;

	}

	/**
	 * helper that instantiate new commit object and make corresponding commit
	 * storage folder
	 * 
	 * @param commitMsg
	 *            message for the new commit.
	 * @param project
	 *            project reference pointer
	 * @return a new commit object
	 */
	public static Commit makeCommit(String commitMsg, Gitlet project) {
		Commit curr = new Commit(commitMsg, project.commitId);
		Path branchPath = Paths.get(project.activeBranch.getBranchPath());
		Path currPath = branchPath.resolve("commit" + project.commitId);
		project.commitId++;
		project.latestCommitPath = currPath.toString();
		try {
			Files.createDirectories(currPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return curr;
		// TODO unfinished makeCommit method
	}

	/**
	 * helper that instantiate new branch object and make corresponding branch
	 * storage folder.
	 * 
	 * @param branchName
	 *            name of the branch to be created.
	 * @param project
	 *            project reference pointer
	 * @return
	 */
	public static Branch makeBranch(String branchName, Gitlet project) {
		Branch curr = new Branch(branchName, project.latestCommit);

		Path currPath = Paths.get(project.dotgitlet).resolve(branchName);
		try {
			Files.createDirectories(currPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return curr;
	}

	/**
	 * Check if the content of files are the same, byte by byte.
	 * Acknowledgement:
	 * http://stackoverflow.com/questions/27379059/determine-if-
	 * two-files-store-the-same-content
	 * 
	 * @param filea
	 *            file A
	 * @param fileb
	 *            file B
	 * @return true if A and B are the same.
	 * @throws IOException
	 */
	public static final boolean isSameFile(final Path filea, final Path fileb)
			throws IOException { // TODO cite this.
		if (Files.size(filea) != Files.size(fileb)) {
			return false;
		}

		final long size = Files.size(filea);
		final int mapspan = 4 * 1024 * 1024;

		try (FileChannel chana = (FileChannel) Files.newByteChannel(filea);
				FileChannel chanb = (FileChannel) Files.newByteChannel(fileb)) {

			for (long position = 0; position < size; position += mapspan) {
				MappedByteBuffer mba = mapChannel(chana, position, size,
						mapspan);
				MappedByteBuffer mbb = mapChannel(chanb, position, size,
						mapspan);

				if (mba.compareTo(mbb) != 0) {
					return false;
				}

			}

		}
		return true;
	}

	/**
	 * Acknowledgement:
	 * http://stackoverflow.com/questions/27379059/determine-if-
	 * two-files-store-the-same-content
	 * http://docs.oracle.com/javase/7/docs/api/
	 * java/nio/channels/package-summary.html
	 * 
	 * @param channel input stream channel
	 * @param position position pointer in file content
	 * @param size file size
	 * @param mapspan 
	 * @return a channel for file input
	 * @throws IOException
	 */
	private static MappedByteBuffer mapChannel(FileChannel channel,
			long position, long size, int mapspan) throws IOException {
		final long end = Math.min(size, position + mapspan);
		final long maplen = (int) (end - position);
		return channel.map(MapMode.READ_ONLY, position, maplen);
	}
/**
 * recursive delete a directory
 * Acknowledgement: joey`s GitletTest example
 * @param dir path to the directory to be deleted
 * @throws IOException
 */
	public static void deleteDirectory(Path dir) throws IOException {
		if (Files.exists(dir)) {
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
				for (Path file : stream) {
					if (Files.isDirectory(file)) {
						deleteDirectory(file);
					} else {
						Files.delete(file);
					}
				}
			} catch (IOException | DirectoryIteratorException x) {
				System.err.println(x);
			}
		}
		Files.delete(dir);
	}
/**
 * Helper that serialize an object into .ser file
 * Acknowledgement:
 * YouTube uploader MargretPosch
 * https://www.youtube.com/watch?v=6MisF1sxBTo
 * @param toBeSer object to be serialized.
 * @param testingpath place to store .ser file.
 */
	public static void serialize(Object toBeSer, Path testingpath) {
		try (ObjectOutputStream out = new ObjectOutputStream(
				new FileOutputStream(testingpath.toString()))) {
			out.writeObject(toBeSer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
	}
/**
 * Helper that deserialize .ser to its object
 * @param path place that .ser is stored.
 * @return object from deserialization
 */
	public static Object deserialize(Path path) {
		Object deserialized = null;
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(
				path.toString()))) {
			deserialized = in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return deserialized;
	}

}