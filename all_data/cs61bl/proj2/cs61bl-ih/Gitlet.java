import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class Gitlet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id = 0;

	private ArrayList<File> stagingArea;
	private ArrayList<String> stagingAreaString;
	private CommitTree commitTree;
	private String activeBranch = "master";
	private static int state = 0;

	public Gitlet() throws IOException {
		File file = new File(".gitlet");
		try {
			if (!file.exists()) {
				file.mkdir();
				file = new File(".gitlet/stagingArea");
				file.mkdir();
				file = new File(".gitlet/commits");
				file.mkdir();
			} else {
				throw new Exception(
						"A gitlet version control system already exists in the current directory.");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		commitTree = new CommitTree();
		stagingArea = new ArrayList<File>();
		stagingAreaString = new ArrayList<String>();
		commitTree.commit("initial commit");

	}

	/**
	 * delete folder
	 * 
	 * @param folder
	 *            the folder to be deleter
	 */
	public static void deleteFolder(File folder) {
		File[] files = folder.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isDirectory()) {
					deleteFolder(f);
				} else {
					f.delete();
				}
			}
		}
		folder.delete();
	}

	/**
	 * Copy files
	 * 
	 * @param sourceFile
	 *            the file to be copied
	 * @param destFile
	 *            the file to be pasted
	 * @throws IOException
	 */
	public static void copyFile(File sourceFile, File destFile)
			throws IOException {
		if (!destFile.exists()) {
			destFile.getParentFile().mkdirs();
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;

		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}
	
	public static void copyFolder(File src, File dest)
	    	throws IOException{
	 
	    	if(src.isDirectory()){
	 
	    		//if directory not exists, create it
	    		if(!dest.exists()){
	    		   dest.mkdir();
	    		   System.out.println("Directory copied from " 
	                              + src + "  to " + dest);
	    		}
	 
	    		//list all the directory contents
	    		String files[] = src.list();
	 
	    		for (String file : files) {
	    		   //construct the src and dest file structure
	    		   File srcFile = new File(src, file);
	    		   File destFile = new File(dest, file);
	    		   //recursive copy
	    		   copyFolder(srcFile,destFile);
	    		}
	 
	    	}else{
	    		//if file, then copy it
	    		//Use bytes stream to support all file types
	    		    InputStream in = new FileInputStream(src);
	    	        OutputStream out = new FileOutputStream(dest); 
	 
	    	        byte[] buffer = new byte[1024];
	 
	    	        int length;
	    	        //copy the file content in bytes 
	    	        while ((length = in.read(buffer)) > 0){
	    	    	   out.write(buffer, 0, length);
	    	        }
	 
	    	        in.close();
	    	        out.close();
	    	        System.out.println("File copied from " + src + " to " + dest);
	    	}
	    }

	/**
	 * Adds a copy of the file as it currently exists to the staging area. . If
	 * the file had been marked for untracking, then add just unmarks the file,
	 * and does not also add it to the staging area.
	 * 
	 * @param fileName
	 *            the file name to be added
	 * @throws Exception
	 *             If the file does not exist, print the error message File does
	 *             not exist.
	 */
	public void add(String fileName) throws Exception {

		if (commitTree.head != null) {
			if (commitTree.head.myPrev != null) {
				File temp = new File(".gitlet/commits/"
						+ commitTree.head.myPrev.ID + "/" + fileName);
				File newTemp = new File(System.getProperty("user.dir") + "/"
						+ fileName);
				if (commitTree.head.myPrev.nodeFiles.keySet().contains(temp)) {
					if (newTemp.lastModified() != temp.lastModified()) {
						File stagingFile = new File(".gitlet/stagingArea/"
								+ fileName);
						copyFile(newTemp, stagingFile);
						stagingArea.add(stagingFile);
						stagingAreaString.add(fileName);
						return;
					} else {
						commitTree.head.myPrev.nodeFiles.put(temp, true);
						return;
					}
				}
			}
		}
		File temp = new File(System.getProperty("user.dir") + "/" + fileName);
		try {
			if (temp.exists()) {
				File stagingFile = new File(".gitlet/stagingArea/" + fileName);
				copyFile(temp, stagingFile);
				stagingArea.add(stagingFile);
				stagingAreaString.add(fileName);
			} else {
				throw new Exception("File does not exist.");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Saves a backup of certain files so they can be restored at a later time.
	 * 
	 * @param nodeMessage
	 *            the node message
	 * @throws IOException
	 */
	public void commit(String nodeMessage) throws IOException {
		id++;
		commitTree.commit(nodeMessage);
	}

	public void commit(CommitTree.Node toCopy) throws IOException {
		id++;
		commitTree.commit(toCopy);
	}
	
	public void commit(CommitTree.Node toCopy, CommitTree.Node split) throws IOException {
		id++;
		commitTree.commit(toCopy, split);
	}

	/**
	 * Mark the file for untracking. If the file had been staged, instead just
	 * unstage it, and don't also mark it for untracking.
	 * 
	 * @param fileName
	 *            The file to be removed
	 * @throws Exception
	 *             If the file is neither staged nor tracked by the head commit,
	 *             print the error message No reason to remove the file.
	 */
	public void rm(String fileName) throws Exception {
		if (commitTree.head.myPrev != null) {
			File temp = new File(".gitlet/commits/" + commitTree.head.myPrev.ID
					+ "/" + fileName);
			CommitTree.Node tempNode = commitTree.head.myPrev;
			try {
				while (!temp.exists()) {
					if (tempNode.myPrev == null) {
						throw new Exception("No reason to remove the file.");
					} else {
						tempNode = tempNode.myPrev;
						temp = new File(".gitlet/commits/" + tempNode.ID + "/"
								+ fileName);
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			if (commitTree.head.myPrev.nodeFiles.get(temp) != null) {
				if (!commitTree.head.myPrev.nodeFiles.get(temp)) {
					throw new Exception("No reason to remove the file.");
				} else {
					commitTree.head.myPrev.nodeFiles.put(temp, false);
				}
			}
		}
		File temp = new File(".gitlet/stagingArea/" + fileName);
		if (temp.exists()) {
			stagingArea.remove(temp);
			Iterator<String> iter = stagingAreaString.iterator();
			while (iter.hasNext()) {
				String s = iter.next();
				if (s.equals(fileName)) {
					stagingAreaString.remove(s);
					break;
				}
			}
			temp.delete();
		}
	}

	/**
	 * Starting at the current head commit, display information about each
	 * commit backwards along the commit tree until the initial commit.
	 */
	public void log() {
		if (commitTree.head != null) {
			CommitTree.Node temp = commitTree.head.myPrev;
			while (temp != null) {
				logHelper(temp);
				if (temp.myPrev != null) {
					System.out.println();
				}
				temp = temp.myPrev;
			}
		}
	}

	/**
	 * A helper method of log
	 * 
	 * @param n
	 *            the node to be printed
	 */
	public void logHelper(CommitTree.Node n) {
		System.out.println("===");
		System.out.println("Commit " + n.ID);
		System.out.println(n.time);
		System.out.println(n.message);
	}

	/**
	 * displays information about all commits ever made
	 */
	public void globalLog() {
		Collection<CommitTree.Node> c = commitTree.nodes.values();
		Iterator<CommitTree.Node> iter = c.iterator();
		while (iter.hasNext()) {
			CommitTree.Node temp = (CommitTree.Node) iter.next();
			logHelper(temp);
			if (iter.hasNext()) {
				System.out.println();
			}
		}
	}

	/**
	 * Prints out the id of the commit that has the given commit message.
	 * 
	 * @param commitMessage
	 *            message of the node
	 * @throws Exception
	 *             If no such commit exists, prints the error message Found no
	 *             commit with that message.
	 */
	public void find(String commitMessage) throws Exception {
		try {
			ArrayList<Integer> findList = commitTree.messageToID
					.get(commitMessage);
			if (findList == null) {
				throw new Exception("Found no commit with that message.");
			} else {
				Iterator<Integer> iter = findList.iterator();
				while (iter.hasNext()) {
					System.out.println(iter.next());
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Displays what branches currently exist, and marks the current branch with
	 * a *. Also displays what files have been staged or marked for untracking.
	 */
	public void status() {
		System.out.println("=== Branches ===");
		Set<String> s = commitTree.branches.keySet();
		Iterator<String> iter = s.iterator();
		while (iter.hasNext()) {
			String temp = (String) iter.next();
			if (commitTree.head != null) {
				if (commitTree.head == commitTree.branches.get(temp)) {
					System.out.println("*" + temp);
				}
			}
		}
		iter = s.iterator();
		while (iter.hasNext()) {
			String temp = (String) iter.next();
			if (commitTree.head != null) {
				if (commitTree.head != commitTree.branches.get(temp)) {
					System.out.println(temp);
				}
			}
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		Iterator<String> iterS = stagingAreaString.iterator();
		while (iterS.hasNext()) {
			System.out.println(iterS.next());
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		if (commitTree.head != null) {
			if (commitTree.head.myPrev != null) {
				Set<File> sF = commitTree.head.myPrev.nodeFiles.keySet();
				Iterator<File> iterF = sF.iterator();
				while (iterF.hasNext()) {
					File tempF = (File) iterF.next();
					if (!commitTree.head.myPrev.nodeFiles.get(tempF)) {
						System.out.println(tempF.getAbsolutePath().substring(
								tempF.getAbsolutePath().lastIndexOf(
										File.separator)));
					}
				}
			}
		}
	}

	/**
	 * Checkout command1, command3
	 * 
	 * @param name
	 *            the string name
	 * @throws Exception
	 *             command1: If the file does not exist in the previous commit,
	 *             aborts, printing the error message File does not exist in the
	 *             most recent commit, or no such branch exists. command3: If no
	 *             branch with that name exists, print File does not exist in
	 *             the most recent commit, or no such branch exists. If that
	 *             branch is the current branch, print No need to checkout the
	 *             current branch.
	 */
	public void checkout(String name) throws Exception {
		File temp;
		if (commitTree.head == null) {
			return;
		} else if (commitTree.head.myPrev != null) {
			temp = new File(".gitlet/commits/" + commitTree.head.myPrev.ID
					+ "/" + name);
			CommitTree.Node tempNode = commitTree.head.myPrev;
			try {
				while (!temp.exists()) {
					if (tempNode.myPrev == null) {
						break;
					} else {
						tempNode = tempNode.myPrev;
						temp = new File(".gitlet/commits/" + tempNode.ID + "/"
								+ name);
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			if (temp.exists() ) {
				if(commitTree.head.myPrev.nodeFiles.containsKey(temp)){
					if(commitTree.head.myPrev.nodeFiles.get(temp)){
						File checkoutFile = new File(System.getProperty("user.dir")
								+ "/" + name);
						if (checkoutFile.exists()) {
							checkoutFile.delete();
						}
						copyFile(temp, checkoutFile);
					}
				}
				else throw new Exception(
						"File does not exist in the most recent commit, or no such branch exists.");
			} else {
				if(state == 0){
					try {
						if (commitTree.branches.get(name) == null) {
							throw new Exception(
									"File does not exist in the most recent commit, or no such branch exists.");
						}
						CommitTree.Node branchNode = commitTree.branches.get(name).myPrev;
						if (branchNode == null) {
							throw new Exception(
									"File does not exist in the most recent commit, or no such branch exists.");
						} else if (commitTree.branches.get(name) == commitTree.head) {
							throw new Exception(
									"No need to checkout the current branch.");
						} else {
							for (File a : branchNode.nodeFiles.keySet()) {
								if (branchNode.nodeFiles.get(a)) {
									File checkoutFile = new File(
											System.getProperty("user.dir") + "/"
													+ a.getName());
									if (checkoutFile.exists()) {
										checkoutFile.delete();
									}
									copyFile(a, checkoutFile);
								}
							}
						}
						commitTree.head = commitTree.branches.get(name);
						activeBranch = name;
					} catch (Exception e) {
						System.out.println(e);
					}
				}
				else {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
				}
			}
		}
	}

	/**
	 * Checkout command 2
	 * 
	 * @param commitIDS
	 *            id of a node
	 * @param fileName
	 *            name of a file
	 * @throws Exception
	 *             If no commit with the given id exists, print No commit with
	 *             that id exists. Else, if the file does not exist in the given
	 *             commit, print File does not exist in that commit.
	 */
	public void checkout(String commitIDS, String fileName) throws Exception {
		int commitID = Integer.parseInt(commitIDS);
		CommitTree.Node toCheckout = commitTree.nodes.get(commitID);
		if (toCheckout == null) {
			throw new Exception("No commit with that id exists.");
		}
		File temp = new File(".gitlet/commits/" + toCheckout.ID + "/"
				+ fileName);
		CommitTree.Node tempNode = toCheckout;
		while (!temp.exists()) {
			if (tempNode.myPrev == null) {
				throw new Exception("File does not exist in that commit.");
			} else {
				tempNode = tempNode.myPrev;
				temp = new File(".gitlet/commits/" + tempNode.ID + "/"
						+ fileName);
			}
		}
		if(toCheckout.nodeFiles.containsKey(temp)){
			if (!toCheckout.nodeFiles.get(temp)) {
				throw new Exception("File does not exist in that commit.");
			} else {
				File checkoutFile = new File(System.getProperty("user.dir") + "/"
						+ fileName);
				if (checkoutFile.exists()) {
					checkoutFile.delete();
				}
				copyFile(temp, checkoutFile);
			}
		}
		else throw new Exception("File does not exist in that commit.");
	}

	/**
	 * Creates a new branch with the given name, and points it at the current
	 * head node.
	 * 
	 * @param branchName
	 *            branch name
	 * @throws Exception
	 *             If a branch with the given name already exists, print the
	 *             error message A branch with that name already exists.
	 */
	public void branch(String branchName) throws Exception {
		try {
			if (commitTree.branches.get(branchName) != null) {
				throw new Exception("A branch with that name already exists.");
			}
			CommitTree.Node temp = commitTree.new Node(commitTree.head.myPrev);
			commitTree.branches.put(branchName, temp);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Deletes the branch with the given name.
	 * 
	 * @param branchName
	 *            name of the branch to be deleted
	 * @throws Exception
	 *             If a branch with the given name does not exist, aborts. Print
	 *             the error message A branch with that name does not exist. If
	 *             you try to remove the branch you're currently on, aborts,
	 *             printing the error message Cannot remove the current branch.
	 */
	public void rmBranch(String branchName) throws Exception {
		try {
			if (commitTree.branches.get(branchName) == null) {
				throw new Exception("A branch with that name does not exist.");
			}
			if (commitTree.branches.get(branchName) == commitTree.head) {
				throw new Exception("Cannot remove the current branch.");
			}
			commitTree.branches.remove(branchName);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Checks out all the files tracked by the given commit. Also moves the
	 * current branch's head to that commit node.
	 * 
	 * @param commitIDS
	 *            id of a node
	 * @throws Exception
	 *             If no commit with the given id exists, print No commit with
	 *             that id exists.
	 */
	public void reset(String commitIDS) throws Exception {
		int commitID = Integer.parseInt(commitIDS);
		CommitTree.Node temp = commitTree.nodes.get(commitID);
		try {
			if (temp == null) {
				throw new Exception("No commit with that id exists.");
			} else {
				for (File a : temp.nodeFiles.keySet()) {
					if (temp.nodeFiles.get(a)) {
						File checkoutFile = new File(
								System.getProperty("user.dir") + "/"
										+ a.getName());
						copyFile(a, checkoutFile);
					}
				}
				commitTree.head.myPrev = temp;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void merge(String branchName) throws Exception {
		if (commitTree.branches.get(branchName) == null) {
			throw new Exception("A branch with that name does not exist.");
		}
		if (commitTree.branches.get(branchName) == commitTree.head) {
			throw new Exception("Cannot merge a branch with itself.");
		}
		
		CommitTree.Node branchBase = commitTree.branches.get(branchName).myPrev;
		CommitTree.Node currentBase = commitTree.head.myPrev;
		// slow !!!
		int splitID = -1;
		CommitTree.Node splitpoint = null;
		if (branchBase != null && currentBase != null) {
			outerloop:
			while (branchBase.myPrev != null) {
				while (currentBase.myPrev != null) {
					if (branchBase.myPrev == currentBase.myPrev) {
						splitID = currentBase.ID;
						splitpoint = currentBase.myPrev;
						break outerloop;
					} else {
						currentBase = currentBase.myPrev;
					}
				}
				branchBase = branchBase.myPrev;
				currentBase = commitTree.head.myPrev;
			}
		}
		branchBase = commitTree.branches.get(branchName).myPrev;
		currentBase = commitTree.head.myPrev;
		boolean haveConflict = false;
		if(splitpoint != null){
			Set<File> s1 = currentBase.nodeFiles.keySet();
			Set<File> s2 = splitpoint.nodeFiles.keySet();
			Set<File> s3 = branchBase.nodeFiles.keySet();
			String a1 = "" + currentBase.ID;
			String a2 = "" + splitpoint.ID;
			String a3 = "" + branchBase.ID;
			for(File S : s3){
				if(s1.contains(S) && s2.contains(S)){
					if(currentBase.nodeFiles.get(S) == true){
						currentBase.nodeFiles.put(S, splitpoint.nodeFiles.get(S));
					}
				}
				else if(s2.contains(S.getPath().replaceFirst(a3, a2))){
					System.out.println(S.getPath().replaceFirst(a3, a2));
					if(!s1.contains(S.getPath().replaceFirst(a3, a1))){
						currentBase.nodeFiles.remove(S.getPath().replaceFirst(a3, a2));
						currentBase.nodeFiles.put(S, splitpoint.nodeFiles.get(S));
					}
					else{
						String change = S.getPath().substring(S.getPath().indexOf(a3)+a3.length()+1);
						File checkoutFile = new File(System.getProperty("user.dir")
								+ "/" + change + ".confilcted");
						if (checkoutFile.exists()) {
							checkoutFile.delete();
						}
						copyFile(S, checkoutFile);
						add(change + ".confilcted");
						haveConflict = true;
					}
				}
				else {
					String change = S.getPath().substring(S.getPath().indexOf(a3)+a3.length()+1);
					File checkoutFile = new File(System.getProperty("user.dir")
							+ "/" + change);
					if (checkoutFile.exists()) {
						checkoutFile.delete();
					}
					copyFile(S, checkoutFile);
					add(change);
				}
			}
			String currentBranchName = "";
			Set<String> s = commitTree.branches.keySet();
			Iterator<String> iter = s.iterator();
			while (iter.hasNext()) {
				String temp = (String) iter.next();
				if (commitTree.head != null) {
					if (commitTree.head == commitTree.branches.get(temp)) {
						currentBranchName = temp;
					}
				}
			}
			if(!haveConflict){
				commit("Merged " + currentBranchName + " with " + branchName);
			}
			else{
				System.out.println("Encountered a merge conflict.");
				state = 1;
			}
		}
	}

	public void rebase(String branchName) throws Exception {
			if (commitTree.branches.get(branchName) == null) {
				throw new Exception("A branch with that name does not exist.");
			}
			if (commitTree.branches.get(branchName) == commitTree.head) {
				throw new Exception("Cannot rebase a branch onto itself.");
			}

			Set<String> s = commitTree.branches.keySet();
			Iterator<String> iter = s.iterator();
			String temp = "";
			while (iter.hasNext()) {
				temp = (String) iter.next();
				if (commitTree.head != null) {
					if (commitTree.head == commitTree.branches.get(temp)) {
						break;
					}
				}
			}

			CommitTree.Node branchBase = commitTree.branches.get(branchName).myPrev;
			CommitTree.Node currentBase = commitTree.head.myPrev;

			while (currentBase.ID != 0 && branchBase.ID != 0) {
				if (currentBase == branchBase) {
					throw new Exception("Already up-to-date.");
				} else {
					currentBase = currentBase.myPrev;
				}
			}
			branchBase = commitTree.branches.get(branchName).myPrev;
			currentBase = commitTree.head.myPrev;
			while (currentBase.ID != 0 && branchBase.ID != 0) {
				if (currentBase == branchBase) {
					commitTree.branches.get(temp).myPrev = commitTree.branches
							.get(branchName).myPrev;
					return;
				} else {
					branchBase = branchBase.myPrev;
				}
			}

			branchBase = commitTree.branches.get(branchName).myPrev;
			currentBase = commitTree.head.myPrev;
			// slow !!!
			int splitID = -1;
			CommitTree.Node splitpoint = null;
			if (branchBase != null && currentBase != null) {
				outerloop:
				while (branchBase.myPrev != null) {
					while (currentBase.myPrev != null) {
						if (branchBase.myPrev == currentBase.myPrev) {
							splitID = currentBase.ID;
							splitpoint = currentBase.myPrev;
							break outerloop;
						} else {
							currentBase = currentBase.myPrev;
						}
					}
					branchBase = branchBase.myPrev;
					currentBase = commitTree.head.myPrev;
				}
			}

			CommitTree.Node currentStart = commitTree.head.myPrev;
			CommitTree.Node currentStart2 = commitTree.head.myPrev;

			if (splitID != -1) {
				commitTree.branches.get(temp).myPrev = commitTree.branches
						.get(branchName).myPrev;
				commitTree.head = commitTree.branches.get(temp);

				while (splitID != currentStart.ID) {
					currentStart2 = currentStart;
					commit(commitTree.nodes.get(splitID), splitpoint);
					while (currentStart2.myPrev != null) {
						if (currentStart2.myPrev.ID == splitID) {
							splitID = currentStart2.ID;
							break;
						} else {
							currentStart2 = currentStart2.myPrev;
						}
					}
				}
				commit(commitTree.nodes.get(splitID), splitpoint);

			}

			reset("" + commitTree.branches.get(temp).myPrev.ID);

	}

	private class CommitTree implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Node head;
		private HashMap<String, Node> branches;
		private HashMap<String, ArrayList<Integer>> messageToID;
		private HashMap<Integer, Node> nodes;

		/**
		 * Saves a backup of certain files.
		 * 
		 * @param nodeMessage
		 *            name of the node
		 * @throws IOException
		 */
		public void commit(String nodeMessage) throws IOException {
			File folder = new File(".gitlet/commits/" + id);
			folder.mkdirs();
			if (head != null) {
				Node newNode = new Node(nodeMessage, commitTree.head.myPrev);
				head.myPrev = newNode;
				Set<String> s = branches.keySet();
				for (String S : s) {
					if (s.equals(activeBranch)) {
						branches.put(S, new Node(newNode));
						break;
					}
				}
				Iterator<String> iterS = stagingAreaString.iterator();
				while (iterS.hasNext()) {
					String ss = iterS.next();
					File temp = new File(".gitlet/stagingArea/" + ss);
					File toCommit = new File(".gitlet/commits/" + id + "/" + ss);
					copyFile(temp, toCommit);
					File parentCheck = new File(".gitlet/commits/"
							+ newNode.myPrev.ID + "/" + ss);
					if (parentCheck.exists()) {
						newNode.nodeFiles.remove(parentCheck);
					}
					newNode.nodeFiles.put(toCommit, true);
				}
				File deleteStage = new File(".gitlet/stagingArea/");
				deleteFolder(deleteStage);
				deleteStage.mkdirs();
				stagingArea = new ArrayList<File>();
				stagingAreaString = new ArrayList<String>();
				nodes.put(newNode.ID, newNode);
				ArrayList<Integer> intList = null;
				if (messageToID.get(nodeMessage) == null) {
					intList = new ArrayList<Integer>();
				} else {
					intList = messageToID.get(nodeMessage);
				}
				intList.add(newNode.ID);
				messageToID.put(nodeMessage, intList);
			}
			state = 0;
		}

		public void commit(Node toCopy) throws IOException {
			File folder = new File(".gitlet/commits/" + id);
			folder.mkdirs();
			if (head != null) {
				Node newNode = new Node(toCopy, head.myPrev);
				head.myPrev = newNode;
				Set<String> s = branches.keySet();
				for (String S : s) {
					if (branches.get(S).myPrev == newNode.myPrev) {
						branches.put(S, new Node(newNode));
						break;
					}
				}
				nodes.put(newNode.ID, newNode);
				ArrayList<Integer> intList = null;
				if (messageToID.get(newNode.message) == null) {
					intList = new ArrayList<Integer>();
				} else {
					intList = messageToID.get(newNode.message);
				}
				intList.add(newNode.ID);
				messageToID.put(newNode.message, intList);
			}
			state = 0;
		}
		
		public void commit(Node toCopy, Node split) throws IOException {
			File folder = new File(".gitlet/commits/" + id);
			folder.mkdirs();
			if (head != null) {
				Node newNode = new Node(toCopy, head.myPrev, split);
				head.myPrev = newNode;
				Set<String> s = branches.keySet();
				for (String S : s) {
					if (branches.get(S).myPrev == newNode.myPrev) {
						branches.put(S, new Node(newNode));
						break;
					}
				}
				File replace = new File(".gitlet/commits/" + toCopy.ID);
				copyFolder(replace, folder);
				nodes.put(newNode.ID, newNode);
				ArrayList<Integer> intList = null;
				if (messageToID.get(newNode.message) == null) {
					intList = new ArrayList<Integer>();
				} else {
					intList = messageToID.get(newNode.message);
				}
				intList.add(newNode.ID);
				messageToID.put(newNode.message, intList);
			}
			state = 0;
		}

		public CommitTree() {
			branches = new HashMap<String, Node>();
			Node defaultBranch = new Node(null);
			head = defaultBranch;
			branches.put("master", defaultBranch);
			nodes = new HashMap<Integer, Node>();
			messageToID = new HashMap<String, ArrayList<Integer>>();
		}

		private class Node implements Serializable {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			private String message;
			private String time;
			private int ID;
			Node myPrev;

			private HashMap<File, Boolean> nodeFiles;

			public Node(Node prev) {
				myPrev = prev;
			}

			public Node(String message, Node prev) {
				this.message = message;
				ID = id;
				myPrev = prev;
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				time = dateFormat.format(date);
				nodeFiles = new HashMap<File, Boolean>();
				if (prev != null) {
					Set<File> s = myPrev.nodeFiles.keySet();
					Iterator<File> iter = s.iterator();
					while (iter.hasNext()) {
						File temp = iter.next();
						if (myPrev.nodeFiles.get(temp)) {
							nodeFiles.put(temp, true);
						}
					}
				}

			}

			public Node(Node toCopy, Node prev) {
				message = toCopy.message;
				ID = id;
				myPrev = prev;
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				time = dateFormat.format(date);
				nodeFiles = new HashMap<File, Boolean>(toCopy.nodeFiles);
			}
			
			public Node(Node toCopy, Node prev, Node split) {
				message = toCopy.message;
				ID = id;
				myPrev = prev;
				DateFormat dateFormat = new SimpleDateFormat(
						"yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				time = dateFormat.format(date);
				nodeFiles = new HashMap<File, Boolean>(toCopy.nodeFiles);
				Set<File> s1 = nodeFiles.keySet();
				Set<File> s2 = split.nodeFiles.keySet();
				Set<File> s3 = prev.nodeFiles.keySet();
				String a1 = "" + ID;
				String a2 = "" + split.ID;
				String a3 = "" + prev.ID;
				String a4 = "" + toCopy.ID;
				for(File S : s1){
					if(S.getAbsolutePath().contains(a4)){
						nodeFiles.remove(S.getPath());
						nodeFiles.put(new File(S.getPath().replaceFirst(a4, a1)), toCopy.nodeFiles.get(S));
					}
				}
				for(File S : s3){
					if(s1.contains(S) && s2.contains(S)){
						if(nodeFiles.get(S) == true){
							nodeFiles.put(S, prev.nodeFiles.get(S));
						}
					}
					else if(s2.contains(S.getPath().replaceFirst(a3, a2))){
						System.out.println(S.getPath().replaceFirst(a3, a2));
						if(!s1.contains(S.getPath().replaceFirst(a3, a1))){
							nodeFiles.remove(S.getPath().replaceFirst(a3, a2));
								nodeFiles.put(S, prev.nodeFiles.get(S));
						}
					}
				}
			}
		}

	}

	public static void main(String[] args) throws Exception {
		Gitlet git = null;
		File file = new File(".gitlet/data.ser");
		if(state == 0){
			if (args[0].length() != 0) {
				if (args[0].equals("init")) {
					git = new Gitlet();
				} else {
					try {
						FileInputStream fileIn = new FileInputStream(file);
						ObjectInputStream in = new ObjectInputStream(fileIn);
						git = (Gitlet) in.readObject();
						in.close();
						fileIn.close();
					} catch (IOException e) {
						System.out.println("Read Exception!");
					}
					if (git != null) {
						if (args[0].equals("add")) {
							if (args[1].length() != 0) {
								git.add(args[1]);
							}
						} else if (args[0].equals("commit")) {
							if (args[1].length() != 0) {
								git.commit(args[1]);
							}
						} else if (args[0].equals("rm")) {
							if (args[1].length() != 0) {
								git.rm(args[1]);
							}
						} else if (args[0].equals("log")) {
							git.log();
						} else if (args[0].equals("global-log")) {
							git.globalLog();
						} else if (args[0].equals("find")) {
							if (args[1].length() != 0) {
								git.find(args[1]);
							}
						} else if (args[0].equals("status")) {
							git.status();
						} else if (args[0].equals("checkout")) {
							if (args.length > 2) {
								if (args[2].length() != 0) {
									git.checkout(args[1], args[2]);
									return;
								}
							}
							if (args[1].length() != 0) {
								git.checkout(args[1]);
							}
						} else if (args[0].equals("branch")) {
							if (args[1].length() != 0) {
								git.branch(args[1]);
							}
						} else if (args[0].equals("rm-branch")) {
							if (args[1].length() != 0) {
								git.rmBranch(args[1]);
							}
						} else if (args[0].equals("reset")) {
							if (args[1].length() != 0) {
								git.reset(args[1]);
							}
						} else if (args[0].equals("merge")) {
							if (args[1].length() != 0) {
								git.merge(args[1]);
							}
						} else if (args[0].equals("rebase")) {
							if (args[1].length() != 0) {
								git.rebase(args[1]);
							}
						}
					}
				}
				try {
					FileOutputStream fileOut = new FileOutputStream(file);
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(git);
					out.close();
					fileOut.close();
				} catch (IOException i) {
					i.printStackTrace();
					System.out.println("Write Exception!");
				}
			}
		} 
		else {
			if (args[0].length() != 0) {
				if (args[0].equals("init")) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
				} else {
					try {
						FileInputStream fileIn = new FileInputStream(file);
						ObjectInputStream in = new ObjectInputStream(fileIn);
						git = (Gitlet) in.readObject();
						in.close();
						fileIn.close();
					} catch (IOException e) {
						System.out.println("Read Exception!");
					}
					if (git != null) {
						if (args[0].equals("add")) {
							if (args[1].length() != 0) {
								git.add(args[1]);
							}
						} else if (args[0].equals("commit")) {
							if (args[1].length() != 0) {
								git.commit(args[1]);
							}
						} else if (args[0].equals("rm")) {
							if (args[1].length() != 0) {
								git.rm(args[1]);
							}
						} else if (args[0].equals("log")) {
							git.log();
						} else if (args[0].equals("global-log")) {
							git.globalLog();
						} else if (args[0].equals("find")) {
							if (args[1].length() != 0) {
								git.find(args[1]);
							}
						} else if (args[0].equals("status")) {
							git.status();
						} else if (args[0].equals("checkout")) {
							if (args.length > 2) {
								if (args[2].length() != 0) {
									git.checkout(args[1], args[2]);
									return;
								}
							}
							if (args[1].length() != 0) {
								git.checkout(args[1]);
							}
						} else if (args[0].equals("branch")) {
							if (args[1].length() != 0) {
								System.out.println("Cannot do this command until the merge conflict has been resolved.");
							}
						} else if (args[0].equals("rm-branch")) {
							if (args[1].length() != 0) {
								System.out.println("Cannot do this command until the merge conflict has been resolved.");
							}
						} else if (args[0].equals("reset")) {
							if (args[1].length() != 0) {
								System.out.println("Cannot do this command until the merge conflict has been resolved.");
							}
						} else if (args[0].equals("merge")) {
							if (args[1].length() != 0) {
								System.out.println("Cannot do this command until the merge conflict has been resolved.");
							}
						} else if (args[0].equals("rebase")) {
							if (args[1].length() != 0) {
								System.out.println("Cannot do this command until the merge conflict has been resolved.");
							}
						}
					}
				}
				try {
					FileOutputStream fileOut = new FileOutputStream(file);
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(git);
					out.close();
					fileOut.close();
				} catch (IOException i) {
					i.printStackTrace();
					System.out.println("Write Exception!");
				}
			}
		}
	}
}