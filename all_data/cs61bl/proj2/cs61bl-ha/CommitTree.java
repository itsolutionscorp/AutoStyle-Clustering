import java.util.HashMap;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

public class CommitTree implements Serializable {
	boolean hasConflict;
	private int currentCommit;
	private String currentBranch;
	private HashMap<String, Commit> branches;
	private ArrayList<String> markedFiles;
	private ArrayList<String> virtualStage;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public CommitTree() {
		currentCommit = 0;
		branches = new HashMap<String, Commit>();
		currentBranch = "master";
		markedFiles = new ArrayList<String>();
		virtualStage = new ArrayList<String>();
		hasConflict = false;
	}

	/**
	 * Adds file path to virtual stage, virtual stage helps find file paths for
	 * tracking and commit
	 * 
	 * @param filename
	 *            String of the filename with the path if nested
	 */
	public void addToVirtualStage(String filename) {
		virtualStage.add(filename);
	}

	/**
	 * Removes a file path from the virtual stage
	 * 
	 * @param filename
	 *            String of filename with the path through nested directories
	 */
	public void removeFromVirtualStage(String filename) {
		virtualStage.remove(filename);
	}

	/**
	 * Checks if any files have been added/staged
	 * 
	 * @return returns true if virtualStage.size() > 0
	 */
	public boolean hasStagedFiles() {
		return virtualStage.size() > 0;
	}

	/**
	 * Checks if a file has been marked or removal
	 * 
	 * @param filename
	 *            String name/path of a file
	 * @return true if the file has been marked for removal
	 */
	public boolean isMarked(String filename) {
		return markedFiles.contains(filename);
	}

	/**
	 * "marks" a file for removal by adding it to a list of marked files
	 * 
	 * @param filePath
	 *            String filename/path that will be removed from the upcoming
	 *            commit
	 */
	public void markFile(String filePath) {
		markedFiles.add(filePath);
	}

	/**
	 * "unmarks" a file for removal by removing it from the list of marked files
	 * 
	 * @param fileName
	 *            String filename/path of file that should be removed from the
	 *            marked files
	 */
	public void unmarkFile(String fileName) {
		markedFiles.remove(fileName);
	}

	/**
	 * Checks if any files have been marked for removal
	 * 
	 * @return true if files have been marked for removal
	 */
	public boolean hasMarkedFiles() {
		return markedFiles.size() > 0;
	}

	/**
	 * Creates a new Commit object, copies all the files from the staging area
	 * into the commit folder
	 * 
	 * @param message
	 *            String for the message that goes along with the commit
	 */
	public void newCommit(String message) {
		Date date = new Date();
		Commit commit;
		if (currentCommit == 0) {
			commit = new Commit(currentCommit, message, dateFormat.format(date));
		} else {
			Commit parentCommit = branches.get(currentBranch);
			commit = new Commit(currentCommit, message,
					dateFormat.format(date), parentCommit);
		}
		branches.put(currentBranch, commit);
		File commitDir = new File(".gitlet/commits/" + currentCommit);
		commitDir.mkdir();
		String serPath = commitDir + "/commitSer.txt";
		File stagingArea = new File(".gitlet/.stagingArea");
		File[] stagingFiles = stagingArea.listFiles();
		for (String marked : markedFiles) {
			commit.untrack(marked);
		}
		markedFiles = new ArrayList<String>();
		for (String virtualStageFiles : virtualStage) {
			commit.track(virtualStageFiles);
		}
		virtualStage = new ArrayList<String>();
		for (File f : stagingFiles) {
			File toBeAdded = new File(".gitlet/.stagingArea/" + f.getName());
			File dest = new File(".gitlet/commits/" + currentCommit + "/"
					+ f.getName());
			toBeAdded.renameTo(dest);
		}
		serialize((Object) commit, serPath);
		currentCommit++;
		if (hasConflict()) {
			falseConflictState();
		}
	}

	/**
	 * Prints out the log of all the commits associated with the current branch
	 * 
	 */
	public void log() {
		Commit current = branches.get(currentBranch);
		while (current != null) {
			System.out.println("===" + "\n" + "Commit " + current.getID()
					+ "\n" + current.getTime() + "\n" + current.getMessage()
					+ "\n");
			current = current.getParent();
		}
	}

	/**
	 * Prints a log of all commits ever made
	 * 
	 */
	public void globalLog() {
		int head = currentCommit - 1;
		// File commitFile = new File(".gitlet/commits/" + head);
		while (head >= 0) {
			String serPath = ".gitlet/commits/" + head + "/commitSer.txt";
			Commit current = (Commit) deserialize(serPath);
			System.out.println("===" + "\n" + "Commit " + current.getID()
					+ "\n" + current.getTime() + "\n" + current.getMessage()
					+ "\n");
			head -= 1;
		}
	}

	/**
	 * Finds all the commits that have the given message
	 * 
	 * @param message
	 *            String of a message associated with a commit
	 */
	public void find(String message) {
		int count = 0;
		int head = currentCommit - 1;
		// File commitFile = new File(".gitlet/commits/" + head);
		while (head >= 0) {
			String serPath = ".gitlet/commits/" + head + "/commitSer.txt";
			Commit current = (Commit) deserialize(serPath);
			if (current.getMessage().equals(message)) {
				System.out.println(current.getID());
				count += 1;
			}
			head -= 1;
		}
		if (count == 0) {
			System.out.println("Found no commit with that message.");
		}
	}

	/**
	 * Creates a new branch
	 * 
	 * @param branchName
	 *            String that the new branch will be named after
	 */
	public void branch(String branchName) {
		if (branches.containsKey(branchName)) {
			System.out.println("A branch with that name already exists.");
		} else {
			branches.put(branchName, branches.get(currentBranch));
		}
	}

	/**
	 * Prints out all the branches that currently exists, files that have been
	 * staged, and files that have been unmarked if they exist
	 * 
	 */
	public void status() {
		System.out.println("=== Branches ===");
		for (String branch : branches.keySet()) {
			if (branch.equals(currentBranch)) {
				System.out.print("*");
			}
			System.out.println(branch);
		}
		System.out.println("\n=== Staged Files ===");
		for (String stagedFiles : virtualStage) {
			System.out.println(stagedFiles);
		}
		System.out.println("\n=== Files Marked for Untracking ===");
		for (String s : markedFiles) {
			System.out.println(s);
		}
	}

	/**
	 * Checks if a file is currently being tracked by the head commit in the
	 * current branch
	 * 
	 * @param fileName
	 *            String of the filename/path to check
	 * @return true if the file is being tracked
	 */
	public boolean checkTrackedFiles(String fileName) {
		return branches.get(currentBranch).trackedFiles.containsKey(fileName);
	}

	/**
	 * Checks if a file exists in a commit, then copies it into the working
	 * directory
	 * 
	 * @param ID
	 *            ID in string form, is then converted into an integer
	 * @param fileName
	 *            String filename with the path if it is nested
	 */
	public void checkout(String ID, String fileName) {
		try {
			int intID = Integer.parseInt(ID);
			if (intID >= currentCommit) {
				System.out.println("No commit with that id exists.");
			} else {
				String commitPath = ".gitlet/commits/" + intID
						+ "/commitSer.txt";
				Commit commit = (Commit) deserialize(commitPath);
				if (commit.trackedFiles.keySet().contains(fileName)) {
					copyToWorkingDirectory(commit, fileName);
				} else {
					System.out.println("File does not exist in that commit.");
				}
			}
		} catch (NumberFormatException e) {
			System.out.println("No commit with that id exists.");
		}

	}

	/**
	 * Copies all tracked files in the head commit of a branch into the working
	 * directory
	 * 
	 * @param branchName
	 *            String branch name
	 */
	public void checkout(String name) {
		if (branches.containsKey(name)) {
			if (!hasConflict()) {
				if (name.equals(currentBranch)) {
					System.out
							.println("No need to checkout the current branch.");
				} else {
					Commit c = branches.get(name);
					for (String s : c.trackedFiles.keySet()) {
						copyToWorkingDirectory(c, s);
					}
					currentBranch = name;
				}
			} else {
				System.out.println("Cannot do this command until the merge conflict has been resolved.");
			}
		} else if (branches.get(currentBranch).trackedFiles.keySet().contains(
				name)) {
			checkout(Integer.toString(branches.get(currentBranch).getID()),
					name);
		} else {
			System.out
					.println("File does not exist in the most recent commit, or no such branch exists.");
		}
	}

	/**
	 * Method used in checkout and reset, copies files from a commit to the
	 * working directory. Physical files are located and copied by using the
	 * trackedFiles hashmap
	 * 
	 * @param c
	 *            commit where the file is located
	 * @param filepath
	 *            String of the file name
	 */
	private void copyToWorkingDirectory(Commit c, String filepath) {
		int fileLocationID = c.trackedFiles.get(filepath).getID();
		File trackedFile = new File(filepath);
		File commitFile = new File(".gitlet/commits/" + fileLocationID + "/"
				+ filepath);
		try {
			if (trackedFile.getParent() != null) {
				File parent = new File(trackedFile.getParent());
				parent.mkdirs();
			}
			CopyOption[] options = new CopyOption[] {
					StandardCopyOption.REPLACE_EXISTING,
					StandardCopyOption.COPY_ATTRIBUTES };
			Files.copy(commitFile.toPath(), trackedFile.toPath(), options);
		} catch (IOException e) {
		}
	}

	/**
	 * Deletes a branch if it is not the current branch
	 * 
	 * @param branchName
	 *            String branch name
	 */
	public void removeBranch(String branchName) {
		if (branches.containsKey(branchName)) {
			if (branchName.equals(currentBranch)) {
				System.out.println("Cannot remove the current branch.");
			} else {
				branches.remove(branchName);
			}
		} else {
			System.out.println("A branch with that name does not exist.");
		}
	}

	/**
	 * Resets the working directory by copying all files from a commit
	 * 
	 * @param id
	 *            String id of the commit
	 */
	public void reset(String id) {
		try {
			int intID = Integer.parseInt(id);
			if (intID >= currentCommit) {
				System.out.println("No commit with that id exists.");
			} else {
				String commitPath = ".gitlet/commits/" + intID
						+ "/commitSer.txt";
				Commit c = (Commit) deserialize(commitPath);
				for (String s : c.trackedFiles.keySet()) {
					copyToWorkingDirectory(c, s);
				}
				branches.put(currentBranch, c);
			}
		} catch (NumberFormatException e) {
			System.out.println("No commit with that id exists.");
		}
	}

	/**
	 * Creates a serialized file of an object
	 * 
	 * @param o
	 *            Object to be serialized
	 * @param filePath
	 *            name of the file after serialization
	 */
	private void serialize(Object o, String filePath) {
		try {
			ObjectOutput output = new ObjectOutputStream(new FileOutputStream(
					filePath));
			output.writeObject(o);
			output.close();
		} catch (IOException e) {
		}
	}

	/**
	 * De-serializes a file back into an object
	 * 
	 * @param filePath
	 *            name of file to be de-serialized
	 * @return object
	 */
	private Object deserialize(String filePath) {
		try {
			ObjectInput input = new ObjectInputStream(new FileInputStream(
					filePath));
			Object file = input.readObject();
			input.close();
			return file;
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e2) {
			return null;
		}
	}

	/**
	 * Identifies the current branch
	 * 
	 * @return String current branch
	 */
	public String currentBranch() {
		return currentBranch;
	}

	/**
	 * Finds the split point between the current branch and a given branch, then
	 * copies the commits of the current branch behind the head commit of the
	 * given commit
	 * 
	 * @param branch
	 *            String of the given branch name to move the commits to
	 */
	public void rebase(String branch) {
		Commit current = branches.get(currentBranch());
		Commit given = branches.get(branch);
		Commit splitPoint = current.findSplit(given);

		if (!branches.keySet().contains(branch)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (currentBranch().equals(branch)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		} else if (splitPoint == given) {
			System.out.println("Already up-to-date.");
			return;
		}
		ArrayList<Commit> curBranchCom = new ArrayList<Commit>();
		for (Commit c = current; c.getID() > splitPoint.getID(); c = c
				.getParent()) {
			curBranchCom.add(c);
		}
		if (splitPoint == current) {
			branches.put(currentBranch(), given);
		} else {
			for (int i = curBranchCom.size() - 1; i > -1; i--) {
				Commit com = curBranchCom.get(i);
				String message = com.getMessage();
				HashMap<String, Commit> hMap = new HashMap<String, Commit>();
				hMap.putAll(com.getTrackedFiles());

				if (i == curBranchCom.size() - 1) {
					rebaseCommit(message, given, given, splitPoint, hMap);
				} else {
					rebaseCommit(message, branches.get(currentBranch()), given,
							splitPoint, hMap);
				}
			}
		}
		reset(Integer.toString(currentCommit - 1));
	}

	/**
	 * Merges files from a given branch to current branch only if the files have
	 * been modified
	 * 
	 * @param branch
	 *            String name of the given branch
	 * @return ArrayList<String> of files that are to be staged in the new
	 *         commit
	 */
	public ArrayList<String> merge(String branch) {
		Commit current = branches.get(currentBranch);
		Commit given = branches.get(branch);
		Commit split = current.findSplit(given);
		ArrayList<String> currentLst = new ArrayList<String>();
		ArrayList<String> givenLst = new ArrayList<String>();
		current.filesUpTo(currentLst, split);
		given.filesUpTo(givenLst, split);
		currentLst = current.trim(currentLst);
		givenLst = given.trim(givenLst);
		ArrayList<String> toStage = new ArrayList<String>();
		File splitDir = new File(".gitlet/commits/" + split.getID());
		ArrayList<String> splitLst = new ArrayList<String>(
				Arrays.asList(splitDir.list()));
		for (int i = 0; i < givenLst.size(); i++) {
			String looking = givenLst.get(i);
			File gLooking = new File(".gitlet/commits/" + given.getID() + "/"
					+ looking);
			if (!currentLst.contains(looking)) {
				checkout(Integer.toString(given.getID()), looking);
				toStage.add(looking);
			} else {
				File cLooking = new File(".gitlet/commits/" + current.getID()
						+ "/" + looking);
				if (splitLst.contains(looking)) {
					File splitFile = new File(".gitlet/commits/"
							+ split.getID() + "/" + looking);
					if (isFileModified(gLooking.toPath(), cLooking.toPath())) {
						if (!isFileModified(splitFile.toPath(),
								cLooking.toPath())) {
							checkout(Integer.toString(given.getID()), looking);
							toStage.add(looking);
						} else {
							if (isFileModified(splitFile.toPath(),
									gLooking.toPath())) {
								generateConflict(looking, given);
							}
						}
					}
				} else {
					if (isFileModified(gLooking.toPath(), cLooking.toPath())) {
						generateConflict(looking, given);
					}
				}
			}
		}
		return toStage;
	}

	/**
	 * Creates and checks out to the working directory a file with
	 * ".conflicted" added to the end of the original file name.
	 * @param filename
	 * 			the file which will be copied, with ".conflicted" added
	 * 			to the end of its name
	 * @param branch
	 * 			the branch from which the file comes from
	 */
	public void generateConflict(String filename, Commit branch) {
		trueConflictState();
		File file = new File(filename);
		File rename = new File(filename + ".conflicted");
		checkout(Integer.toString(branch.getID()), filename);
		file.renameTo(rename);
	}

	/**
	 * Checks if Gitlet is in a conflicted stage
	 * 
	 * @return true if Gitlet is conflicted
	 */
	public boolean hasConflict() {
		return hasConflict;
	}

	/**
	 * Puts gitlet into a conflicted state
	 */
	public void trueConflictState() {
		hasConflict = true;
	}

	/**
	 * Ends gitlet conflicted state
	 */
	public void falseConflictState() {
		hasConflict = false;
	}

	/**
	 * Creates a new commit for rebased branch
	 * 
	 * @param message
	 *            Message of commit
	 * @param parentCommit
	 *            Parent commit of new commit
	 * @param givenPoint
	 *            Head commit of the given branch
	 * @param splitPoint
	 *            Split point commit of given branch and current branch
	 * @param trackfiles
	 *            Tracked files of the commit of current branch
	 */
	private void rebaseCommit(String message, Commit parentCommit,
			Commit givenPoint, Commit splitPoint,
			HashMap<String, Commit> trackfiles) {
		Date date = new Date();
		Commit commit = new Commit(currentCommit, message,
				dateFormat.format(date), parentCommit);
		commit.trackedFiles = trackfiles;
		branches.put(currentBranch(), commit);
		for (String key : givenPoint.getTrackedFiles().keySet()) {
			if (givenPoint.getTrackedFiles().get(key) != splitPoint) {
				if (commit.getTrackedFiles().containsKey(key)
						&& commit.getTrackedFiles().get(key) == splitPoint) {
					commit.getTrackedFiles().put(key,
							givenPoint.getTrackedFiles().get(key));
				}
			}
		}
		File commitDir = new File(".gitlet/commits/" + currentCommit);
		commitDir.mkdir();
		String serPath = commitDir + "/commitSer.txt";
		serialize((Object) commit, serPath);
		currentCommit++;
	}

	/**
	 * Checks if two file paths are equal
	 * 
	 * @param path1
	 *            Path of one version of file
	 * @param path2
	 *            Path of another version of file
	 * @return true if the files are not the same
	 */
	public static boolean isFileModified(Path path1, Path path2) {
		try {
			byte[] file1 = Files.readAllBytes(path1);
			byte[] file2 = Files.readAllBytes(path2);
			return !Arrays.equals(file1, file2);
		} catch (IOException e) {
			return false;
		}
	}

	private class Commit implements Serializable {
		private int commitID;
		private String commitMessage;
		private String commitTime;
		private Commit parentCommit;
		private HashMap<String, Commit> trackedFiles;

		private Commit(int id, String message, String time) {
			commitID = id;
			commitMessage = message;
			commitTime = time;
			parentCommit = null;
			trackedFiles = new HashMap<String, Commit>();
		}

		private Commit(int id, String message, String time, Commit parent) {
			commitID = id;
			commitMessage = message;
			commitTime = time;
			parentCommit = parent;
			trackedFiles = new HashMap<String, Commit>();
			trackParent();
		}

		/**
		 * Gets the list of tracked files
		 * 
		 * @return HashMap containing all the tracked files and the commits they
		 *         are located in
		 */
		private HashMap<String, Commit> getTrackedFiles() {
			return trackedFiles;
		}

		/**
		 * Returns the ID of the commit
		 * 
		 * @return int id of commit
		 */
		private int getID() {
			return commitID;
		}

		/**
		 * Returns the message logged with the commit
		 * 
		 * @return String message of commit
		 */
		private String getMessage() {
			return commitMessage;
		}

		/**
		 * Returns the time the commit was made
		 * 
		 * @return String time
		 */
		private String getTime() {
			return commitTime;
		}

		/**
		 * Returns the parent commit of the current commit
		 * 
		 * @return Commit parentCommit
		 */
		private Commit getParent() {
			return parentCommit;
		}

		/**
		 * Adds a file to the track map of the current commit
		 * 
		 * @param filePath
		 *            String file path to be added to the list
		 */
		private void track(String filePath) {
			trackedFiles.put(filePath, this);
		}

		/**
		 * Takes all the tracked files from parent commit to current commit
		 */
		private void trackParent() {
			if (parentCommit != null) {
				trackedFiles.putAll(parentCommit.trackedFiles);
			}
		}

		/**
		 * Untracks a file by removing it from the map of tracked files
		 * 
		 * @param fileName
		 *            String filename to be untracked
		 */
		private void untrack(String fileName) {
			trackedFiles.remove(fileName);
		}

		/**
		 * Finds the split point, the most recent commit shared between two
		 * branches
		 * 
		 * @param given
		 *            Commit of the given branch
		 * @return most recently shared ancestor commit
		 */
		public Commit findSplit(Commit given) {
			ArrayList<Commit> lst1 = new ArrayList<Commit>();
			ArrayList<Commit> lst2 = new ArrayList<Commit>();
			Commit p1 = this;
			Commit p2 = given;
			while (p1 != null && p2 != null) {
				if (p1.getID() == p2.getID()) {
					return p1;
				}
				if (lst1.contains(p2)) {
					return p2;
				}
				if (lst2.contains(p1)) {
					return p1;
				}
				lst1.add(p1);
				lst2.add(p2);
				p1 = p1.getParent();
				p2 = p2.getParent();
			}
			return null;
		}

		/**
		 * effectively removes elements from a given list of file strings if the
		 * files are not in the head files of the head commit, on which this
		 * method will be called
		 * 
		 * @param lst
		 *            given list of file string names, to be compared to
		 *            commit's tracked files
		 * @return
		 */
		private ArrayList<String> trim(ArrayList<String> lst) {
			ArrayList<String> result = new ArrayList<String>();
			for (String s : trackedFiles.keySet()) {
				if (lst.contains(s)) {
					result.add(s);
				}
			}
			return result;
		}

		/**
		 * Modifies a given string list of files to contain all the files
		 * physically inside a sequence of commit folders, up until a specified
		 * commit.
		 * 
		 * @param lst
		 *            the ArrayList to which strings of file names will be added
		 * @param until
		 *            files in commits will be added to lst, up until this
		 *            commit
		 */
		private void filesUpTo(ArrayList<String> lst, Commit until) {
			Commit p = this;
			while (p.getID() != until.getID()) {
				File curDir = new File(".gitlet/commits/" + p.getID());
				for (int i = 0; i < curDir.list().length; i++) {
					if (!curDir.list()[i].equals("commitSer.txt")) {
						if (!lst.contains(curDir.list()[i])) {
							lst.add(curDir.list()[i]);
						}
					}
				}
				p = p.getParent();
			}
		}

	}
}
