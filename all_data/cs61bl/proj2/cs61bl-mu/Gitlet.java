import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.*;

public class Gitlet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private final String STAGINGPATH = ".gitlet/stage";
	private final String COMMITPATH = ".gitlet/commit";
	private final StandardCopyOption OVERRIDE = StandardCopyOption.REPLACE_EXISTING;
	private Node head;
	private int id;
	private HashSet<File> files;
	private HashSet<File> trackedFiles;
	private HashSet<File> untrackedFiles;
	private HashMap<String, Integer> idMatcher;
	private HashMap<Integer, Node> globalLog;
	private HashMap<Integer, String> idMessagePair;
	private Branch currentBranch;
	private HashSet<String> branchNames;
	private HashSet<Branch> branches;
	private HashMap<String, Branch> nameMatchingBranch;
	private HashMap<String, String> trackedFilesMap;
	private HashMap<File, String> untrackedFilesMap;
	private HashSet<String> givenCommonCompare, givenCurrentCompare, currentCommonCompare;
	private HashMap<String, String> filesAtCommonMap; 
	private HashMap<String, String> givenFilesMap; 
	private HashMap<String, String> currentFilesMap; 
	private boolean conflictedState;
	private LinkedNumber listId;
	
	/**
	 *  Gitlet's constructor
	 */
	public Gitlet() {
		head = null;
		id = 0;
		files = new HashSet<File>();
		idMatcher = new HashMap<String, Integer>();
		globalLog = new HashMap<Integer, Node>();
		idMessagePair = new HashMap<Integer, String>();
		trackedFiles = new HashSet<File>();
		untrackedFiles = new HashSet<File>();
		currentBranch = new Branch("master", null);
		branchNames = new HashSet<String>();
		branches = new HashSet<Branch>();
		untrackedFilesMap = new HashMap<File, String>();
		nameMatchingBranch = new HashMap<String, Branch>();
		trackedFilesMap = new HashMap<String, String>();
		nameMatchingBranch.put(currentBranch.getName(), currentBranch);
		branchNames.add(currentBranch.getName());
		branches.add(currentBranch);
		givenCommonCompare = new HashSet<String>();
		givenCurrentCompare = new HashSet<String>();
		currentCommonCompare = new HashSet<String>();
		filesAtCommonMap = new HashMap<String, String>();
		givenFilesMap = new HashMap<String, String>();
		currentFilesMap = new HashMap<String, String>(); 
		conflictedState = false;
		listId = null;
	}
	
	/**
	 * initialize the Gitlet version control system 
	 */
	public void init() {
		File gitlet = new File(".gitlet");
		
		if (gitlet.exists() && gitlet.isDirectory()) {
			System.out.println("A gitlet version control system "
					+ "already exists in the current directory.");
		} else {
			gitlet.mkdir();
			File stagingArea = new File(STAGINGPATH);
			stagingArea.mkdir();
			File commitFolder = new File(COMMITPATH);
			commitFolder.mkdir();
			commit("initial commit");
		}
	}
	
	/**
	 * add a file or a file in some directly
	 * 
	 * @param name    file or file location
	 */
	public void add(String name) {
		File file = new File(COMMITPATH+"/"+idMatcher.get(name)+"/"+name);

		if (untrackedFiles.contains(file)) {
			untrackedFiles.remove(file);
			untrackedFilesMap.remove(file);
			return;
		}
	
		File dir = new File(name);
		if (!dir.exists()) {
			System.out.println("File does not exist.");
			return;
		}
		String path = dir.getParent();
				
		if (path != null) {	
			File src  = new File(name);
            File dst = new File(STAGINGPATH + "/" + name);
            
            if (dst.getParentFile() != null) {
            	dst.getParentFile().mkdirs();
            }
            
            try {
                Files.copy(src.toPath(), dst.toPath());
                files.add(dst);
            } catch (IOException e) {
                System.out.println(e);
            }
		} 
		else {
			File src  = new File(name);
            File dst = new File(STAGINGPATH + "/" + name);
           
            try {
                Files.copy(src.toPath(), dst.toPath());
                files.add(dst);
            } catch (IOException e) {
            	
            }
		}
	}
	
	/**
	 * Committing a message.
	 * 
	 * @param message   Pass in an message for committing
	 */
	public void commit(String message) {
		if (files.size() == 0 && untrackedFiles.size() == 0 && head != null) {
			System.out.println("No changes added to the commit.");
			return;
		}
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		Node curNode = null;
		File dst = null;
		String commitFileName = COMMITPATH+"/"+Integer.toString(id);
		
		if (head == null) {
			curNode = new Node(id, time, message, null, null, null);
		} else {
			File newCommit = new File(commitFileName);
			newCommit.mkdir();
			HashSet<File> tempfiles = new HashSet<File>();
			for(File f: files) {
				dst = new File(commitFileName+"/"+f.getPath().substring(14));
				if (dst.getParent().length() != (COMMITPATH.length() + Integer.toString(id).length()+1)) {
					dst.getParentFile().mkdirs();
				}
				try {
					Files.copy(f.toPath(), dst.toPath());
					idMatcher.put(f.getPath().substring(14), id);		
					tempfiles.add(dst);
					trackedFiles.add(dst);
					trackedFilesMap.put(dst.getPath(), f.getPath().substring(14));
				} catch (IOException e) {
					
				}
			}
			
			HashSet<String> fileSet = new HashSet<String> ();
	
			if (tempfiles != null) {
				for (File f: tempfiles) {
					fileSet.add(trackedFilesMap.get(f.getPath()));
				}
			}
			if (head.getFiles() != null) {
				for (File file: head.getFiles()) {				
					String fileTmpName = file.getPath().substring(Integer.toString(id-1).length()+16);
					if (!fileSet.contains(fileTmpName)) {
						if (!untrackedFiles.contains(file)) {
							tempfiles.add(file);
						}
					}
				}
			}
			curNode = new Node(id, time, message, tempfiles, head, idMatcher);
		}
		globalLog.put(id, curNode);
		this.idMessagePair.put(id, curNode.getMessage());
		files.clear();
		untrackedFiles.clear();
		untrackedFilesMap.clear();
		cleanStageArea();
		head = curNode;
		currentBranch.setPointNode(head);
		id++;
	}
	
	/**
	 *  Clean the files in staging area (Except directories)
	 */
	public void cleanStageArea() {
		File files[] = new File(STAGINGPATH).listFiles();
		if (files != null) {
			for(File f: files) {
	            if(f.isDirectory()) {
	            	deleteDir(f);
	            } else {
	                f.delete();
	            }
	        }
		}
	}
	
	/**
	 * Delete the content and folder for the passing directory 
	 * 
	 * @param file  Directory that wants to be deleted
	 */
	public void deleteDir(File file) {
		File[] contents = file.listFiles();
	    if (contents != null) {
	        for (File f : contents) {
	            deleteDir(f);
	        }
	    }
	    file.delete();
	}
	
	/**
	 * It removes a name from the current list 
	 * 
	 * @param name: taking in a file name 
	 */
	public void remove (String name) {
		String filePath = STAGINGPATH+"/"+name;
		File file = new File(filePath);	
		
		if (files.contains(file)) {
			deleteDir(file);
			files.remove(file);
		} else {
			if (head.getPrevNode() == null) {
				System.out.println("No reason to remove the file.");
			} else {
				if (idMatcher.get(name) != null) {
					file = new File(COMMITPATH+"/"+idMatcher.get(name)+"/"+name);
				} else {
					System.out.println("No reason to remove the file.");
					return;
				}
				
				if (trackedFiles.contains(file)) {
					trackedFiles.remove(file);
					untrackedFiles.add(file);
					untrackedFilesMap.put(file, name);
				} else {
					System.out.println("No reason to remove the file.");
				}
			}
		}
	}
	
	/**
	 *  Starting at the current head commit, display information 
	 *  about each commit backwards along the commit tree until 
	 *  the initial commit. 
	 *  
	 * @param head   The head of current commit.
	 */
	public void log(Node head) {
		if (head == null) {
			return;
		}
		
		Node cur = head;
		
		while (cur != null) {
			System.out.println("===");
			System.out.println("Commit " + cur.getId());
			System.out.println(cur.getTime());
			System.out.println(cur.getMessage());
			cur = cur.getPrevNode();
			if (cur != null) {
				System.out.println();
			}
		}
	}
	
	/**
	 * Print out all the previous commit information. 
	 * 
	 */
	public void globalLog() {
		int i = id-1;
		
		while (i >= 0) {
			System.out.println("===");
			System.out.println("Commit " + i);
			System.out.println(globalLog.get(i).getTime());
			System.out.println(globalLog.get(i).getMessage());
			i--;
			if (i >= 0) {
				System.out.println();
			}
		}
	}
	
	/**
	 * Find id or ids commit with corresponding message 
	 * 
	 * @param message    commit message
	 */
	public void find(String message) {
		int i = id-1;
		if (this.idMessagePair.containsValue(message)) {
			while (i >= 0) {
				if (idMessagePair.get(i).equals(message)) {
					System.out.println(i);
				}
				i--;
			}
		} else {
			System.out.println("Found no commit with that message.");
		}
	}
	
	/**
	 * Displays what branches currently exist, and marks the 
	 * current branch with a *. Also displays what files have 
	 * been staged or marked for untracking. An example of 
	 * the exact format it should follow is as follows.
	 */
	public void status() {
		System.out.println("=== Branches ===");
		System.out.println("*"+currentBranch.getName());
		for (String name: branchNames) {
			if (name != currentBranch.getName()) {
				System.out.println(name);
			}
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (File file: files) {
			System.out.println(file.toString().substring(14));
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (File file: untrackedFiles) {
			System.out.println(untrackedFilesMap.get(file));
		}
	}
	
	/**
	 * Takes the version of the file as it exists in the head commit, 
	 * the front of the current branch, and puts it in the working 
	 * directory, overwriting the version of the file that's already 
	 * there if there is one.
	 * OR
	 * Takes all files in the commit at the head of the given branch, 
	 * and puts them in the working directory, overwriting the versions 
	 * of the files that are already there if they exist. Also, at the 
	 * end of this command, the given branch will now be considered 
	 * the current branch.
	 * 
	 * @param name  The name of a branch or a file
	 */
	public void checkoutOneArg(String name) {
		if (currentBranch.getName().equals(name)) {
			System.out.println("No need to checkout the current branch.");
			return;
		}
				
		if (branchNames.contains(name)) {
			this.currentBranch = this.nameMatchingBranch.get(name);
			
			head = this.currentBranch.getNode();
			if (head.getFiles() == null) {
				return;
			}
			for (File from: head.getFiles()) {
				File to = new File(trackedFilesMap.get(from.getPath()));
				if (!to.exists()) {
					to.mkdirs();
				}
				try {
					Files.copy(from.toPath(), to.toPath(), OVERRIDE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			}
			return;
		}

		if (head.getIdMatcher() == null) {
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
			return;
		}
		
		if (head.getIdMatcher().get(name) != null) {
			File from = new File(COMMITPATH+"/"+head.getIdMatcher().get(name)+"/"+name);
			File to = new File(name);
			if (!to.exists()) {
				to.mkdirs();
			}
			try {
				Files.copy(from.toPath(), to.toPath(), OVERRIDE);
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
		} else {
			System.out.println("File does not exist in the most recent commit, or no such branch exists.");
		}
	}
	
	/**
	 * Takes the version of the file as it exists in the commit with 
	 * the given id, and puts it in the working directory, overwriting 
	 * the version of the file that's already there if there is one.
	 * 
	 * @param commitId  The id corresponding on each commit
	 * @param name      The name of a file
	 */
	public void checkoutTwoArgs(int i, String name) {		
		if (!globalLog.containsKey(i)) {
			System.out.println("No commit with that id exists.");
			return;
		}
		
		int curId = i;
		File to = new File(name);
		if (!to.exists()) {
			to.mkdirs();
		}
		
		while (curId != 0) {
			File from = new File(COMMITPATH+"/"+curId+"/"+name);
			if (from.exists()) {
				try {
					Files.copy(from.toPath(), to.toPath(), OVERRIDE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
				return;
			}
			curId = globalLog.get(curId).getPrevNode().getId() ;
		}
		
		System.out.println("File does not exist in that commit.");
	}
	
	/**
	 * Creates a new branch with the given name, and points at the current head node.
	 * 
	 * @param name   The name of current branch
	 */
	public void createBranch(String name) {
		if (branchNames.contains(name)) {
			System.out.println("A branch with that name already exists.");
		} else {
			Branch newBr = null;
			if (currentBranch.getSplitPoint() == null)
				newBr = new Branch(name, head, head.getId(), head);
			else 
				newBr = new Branch(name, head, head.getId(), currentBranch.getSplitPoint());
			branches.add(newBr);
			branchNames.add(name);
			nameMatchingBranch.put(name, newBr);
			currentBranch.setSplitPoint(head);
		}
	}
	
	/**
	 * Deletes the branch with the given name. 
	 * 
	 * @param name  The name of the branch that want to be removed.
	 */
	public void rmBranch(String name) {
		if (branchNames.contains(name)) {
			if (currentBranch.getName().equals(name)) {
				System.out.println("Cannot remove the current branch.");
			} else {
				Branch branch = nameMatchingBranch.get(name);
				branch.setPointNode(null);
				branchNames.remove(name);
				nameMatchingBranch.remove(name);
			}
		} else {
			System.out.println("A branch with that name does not exist.");
		}
	}
	
	/**
	 * Check out all the files tracked by the given commit. 
	 * Also moves the current branch's head to that commit 
	 * node.
	 * 
	 * @param id  The commit id.
	 */
	public void reset(int id) {
		if (!globalLog.containsKey(id)) {
			System.out.println("No commit with that id exists.");
		} else {
			Node curNode = globalLog.get(id);
			
			for (File from: curNode.getFiles()) {
				File to = new File(trackedFilesMap.get(from.getPath()));
				try {
					Files.copy(from.toPath(), to.toPath(), OVERRIDE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			}
			currentBranch.setPointNode(curNode);
			head = curNode;
		}
	}
	
	/**
	 * Merges files from the given branch into the current branch. 
	 * 
	 * @param name  The name of a branch
	 */
	public void merge(String name) {
		if (!branchNames.contains(name)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (currentBranch.getName().equals(name)) {
			System.out.println("Cannot merge a branch with itself.");
			return;
		} else {	
			Node givenSplitPoint = nameMatchingBranch.get(name).getSplitPoint();
			Node currentSplitPoint = nameMatchingBranch.get(currentBranch.getName()).getSplitPoint();
			Node commonSplitPoint = findCommonSplitPoint(givenSplitPoint, currentSplitPoint);
			HashSet<File> filesAtCommon = globalLog.get(commonSplitPoint.getId()).getFiles();
			HashSet<File> givenFiles = nameMatchingBranch.get(name).getNode().getFiles();
			HashSet<File> currentFiles = nameMatchingBranch.get(currentBranch.getName()).getNode().getFiles();
			
			addFilesToMap(filesAtCommon, givenFiles, currentFiles);
			fileComparer(filesAtCommonMap, givenFilesMap, currentFilesMap);
			
			if (this.givenCurrentCompare.size() == 0) {
				if (givenFiles != null) {
					for (File from: givenFiles) {
						File to = new File(trackedFilesMap.get(from.getPath()));
						try {
							Files.copy(from.toPath(), to.toPath(), OVERRIDE);
							add(to.getPath());
						} catch (IOException e) {
							// TODO Auto-generated catch block
						}
					}
					commit("Merged " + currentBranch.getName() +" with " + name +".");
				}
			}else {
				this.conflictedState = true;
				for (File from: givenFiles) {
					File to = null;
					if (givenCurrentCompare.contains(trackedFilesMap.get(from.getPath()))) {
						to = new File(trackedFilesMap.get(from.getPath())+".conflicted");
					} else {
						to = new File(trackedFilesMap.get(from.getPath()));
					}
					if (!to.exists())
						to.mkdirs();
					try {
						Files.copy(from.toPath(), to.toPath(), OVERRIDE);
					} catch (IOException e) {
						// TODO Auto-generated catch block
					}
				}
				System.out.println("Encountered a merge conflict.");
			}
		}
		clearFileComparer(givenCommonCompare, givenCurrentCompare, currentCommonCompare, 
				filesAtCommonMap, givenFilesMap, currentFilesMap);
	}

	/**
	 * Add file's name and content pair for common split point, given branch's head and current branch's head.
	 * 
	 * @param filesAtCommon     The files at common split point.
	 * @param givenFiles        The files at given branch's head.
	 * @param currentFiles      The files at current branch's head. 
	 */
	public void addFilesToMap(HashSet<File> filesAtCommon, 
			HashSet<File> givenFiles, HashSet<File> currentFiles) {
		HashSet<File> given = new HashSet<File>();
		HashSet<File> current = new HashSet<File>();
		for (File f: givenFiles) {
			if (!filesAtCommon.contains(f))
				given.add(f);
		}
		
		for(File f: currentFiles) {
			if (!filesAtCommon.contains(f))
				current.add(f);
		}
		if (filesAtCommon != null) {
			for (File from: filesAtCommon) 
				filesAtCommonMap.put(trackedFilesMap.get(from.getPath()), getText(from.getPath()));		
		} 
		if (given.size() != 0) {
			for (File from: given) 
				givenFilesMap.put(trackedFilesMap.get(from.getPath()), getText(from.getPath()));
		}
		if (current.size() != 0) {
			for (File from: current) 
				currentFilesMap.put(trackedFilesMap.get(from.getPath()), getText(from.getPath()));		
		}
	}
	
	/**
	 * Compare the contents between common split point, given branch's head and current branch's head.
	 * 
	 * @param commonMap     File name and content pair at common split point stored in hash map.
	 * @param givenMap      File name and content pair stored at given branch's head in hash map.
	 * @param currentMap    File name and content pair stored at current branch's head in hash map.
	 */
	public void fileComparer(HashMap<String, String> commonMap, 
			HashMap<String, String> givenMap, HashMap<String, String> currentMap) {
		if (commonMap.size() == 0) {
			for (String from: givenMap.keySet()) {
				if (currentMap.containsKey(from)) {
					givenCurrentCompare.add(from);
				}
			}
		} else {
			for (String from: givenMap.keySet()) {
				if (commonMap.containsKey(from)) {
					if (!commonMap.get(from).equals(givenMap.get(from))) {
						this.givenCommonCompare.add(from);
					}
				}
			}
			for (String from: currentMap.keySet()) {
				if (commonMap.containsKey(from)) {
					if (!commonMap.get(from).equals(currentMap.get(from))) {
						this.currentCommonCompare.add(from);
					}
				}
			}
			for (String from: givenCommonCompare) {
				if (currentCommonCompare.contains(from)) {
					givenCurrentCompare.add(from);
				}
			}
		}
	}
	
	/**
	 * Clean up all the saving after each call to merge.
	 * 
	 * @param givenCommonSet      The files changed from the common split point to given branch's head. 
	 * @param givenCurrentSet     Find the conflicted files.
	 * @param currentCommonSet    The files changed from the common split point to current branch's head
	 * @param commonMap           File name and content pair at common split point stored in hash map.
	 * @param givenMap            File name and content pair stored at given branch's head in hash map.
	 * @param currentMap          File name and content pair stored at current branch's head in hash map.
	 */
	public void clearFileComparer(HashSet<String> givenCommonSet, HashSet<String> givenCurrentSet, 
			HashSet<String> currentCommonSet, HashMap<String, String> commonMap, 
			HashMap<String, String> givenMap, HashMap<String, String> currentMap) {
		givenCurrentSet.clear();
		givenCommonSet.clear();
		currentCommonSet.clear();
		commonMap.clear();
		givenMap.clear();
		currentMap.clear();
	}
	
	/**
	 * Find the nearest common split point between two branches.
	 * 
	 * @param s1   Given branch
	 * @param s2   Current branch
	 * @return     The nearest common split point between two branches.
	 */
	public Node findCommonSplitPoint(Node s1, Node s2) {
		if (s1 == null || s2 == null) {
			return null;
		} 
		
		if (s1.getId() == s2.getId()) {
			return s1;
		} else if (s1.getId() < s2.getId()) {
			return findCommonSplitPoint(s1, s2.getPrevNode());
		} else {
			return findCommonSplitPoint(s1.getPrevNode(), s2);
		}
	}
	
	/**
	 * Returns the text from a standard text file.
	 * 
	 * @param fileName    The name of the file to read.
	 */
	private static String getText(String fileName) {
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(fileName));
			return new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException e) {
			return "";
		}
	}
	
	/**
	 * Find the split point of the current branch and the given branch, 
	 * then snaps off the current branch at this point, then reattaches 
	 * the current branch to the head of the given branch. 
	 * 
	 * @param name   The name of branch.
	 */
	public void rebase(String name) {
		if (!branchNames.contains(name)) {
			System.out.println("A branch with that name does not exist.");
			return;
		} else if (currentBranch.getName().equals(name)) {
			System.out.println("Cannot rebase a branch onto itself.");
			return;
		}
		if (findExist(name)) return;
		
		int curId = currentBranch.getNode().getId();
		
		Node given = new Node();
		given = nameMatchingBranch.get(name).getNode();
		while (given != null) {
			if (given.getId() == curId) {
				currentBranch.setPointNode(nameMatchingBranch.get(name).getNode());
				head = nameMatchingBranch.get(name).getNode();
				return;
			}
			given = given.getPrevNode();
		}
		
		Node givenSplitPoint = nameMatchingBranch.get(name).getSplitPoint();
		Node currentSplitPoint = nameMatchingBranch.get(currentBranch.getName()).getSplitPoint();
		Node commonSplitPoint = findCommonSplitPoint(givenSplitPoint, currentSplitPoint);
		createList(commonSplitPoint);
		
		if (listId != null) {
			addNodesToCurrentBranch(name);
		}
		listId = null;
	}
	
	/**
	 * Add the node between current branch and split point to given branch.
	 * 
	 * @param name  The name of the given branch
	 */
	public void addNodesToCurrentBranch(String name) {
		Node h = nameMatchingBranch.get(name).getNode();
		LinkedNumber list = listId;
		while (list != null) {
			Node n = globalLog.get(list.getId());
			String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
			String mes = n.getMessage();
			
			File dst = null;
			String commitFileName = COMMITPATH+"/"+Integer.toString(id);
			File newCommit = new File(commitFileName);
			newCommit.mkdir();
			HashSet<File> tempfiles = new HashSet<File>();
			if (h.getFiles() != null) {
				for (File file: h.getFiles()) {
					if (!untrackedFiles.contains(file))
						tempfiles.add(file);
				}
			}
			for(File f: new File(COMMITPATH+"/"+n.getId()).listFiles()) {
				dst = new File(commitFileName+ "/" + f.getName());
				if (dst.getParent().length() != (COMMITPATH.length() + Integer.toString(id).length()+1)) {
					dst.getParentFile().mkdirs();
				}
				try {
					Files.copy(f.toPath(), dst.toPath());
					idMatcher.put(f.getPath().substring(15+1+Integer.toString(id).length()), id);
					tempfiles.add(dst);
					trackedFiles.add(dst);
					trackedFilesMap.put(dst.getPath(), f.getName());
				} catch (IOException e) {
					
				}
			}
			h = new Node(id, time, mes, tempfiles, h, idMatcher);
			list = list.next();
			globalLog.put(id, h);
			this.idMessagePair.put(id, h.getMessage());
			untrackedFiles.clear();
			untrackedFilesMap.clear();
			id++;
		}
		head = h;
		currentBranch.setPointNode(h);
	}
	
	/**
	 * Find all the ids between node in current branch and node at split point, 
	 * and then create a list with those ids in reverse order. 
	 * 
	 * @param splitPoint  The split point node between two branches
	 */
	public void createList(Node splitPoint) {
		int idAtSplitPoint = splitPoint.getId();
		Node cur = head;
		
		while ((cur.getId() != idAtSplitPoint)) {
			if (listId == null) {
				listId = new LinkedNumber(cur.getId());
			} else {
				listId = new LinkedNumber(cur.getId(), listId);
			}
			cur = cur.getPrevNode();
		}
	}
	
	/**
	 * Check if if given branch's head is in the history 
	 * of the current branch's head
	 * 
	 * @param name   The name of given branch
	 * @return       Whether if given branch's head is in 
	 * 				      the history of the current branch's head
	 */
	public boolean findExist(String name) {
		int givenId = nameMatchingBranch.get(name).getNode().getId();
		Node cur = currentBranch.getNode();
		while (cur != null) {
			if (givenId == cur.getId()) {
				System.out.println("Already up-to-date.");
				return true;
			}
			cur = cur.getPrevNode();
		}
		return false;
	}
	/**
	 * Checking the if current state is conflicted state, 
	 * print out error message if it is in a conflicted state.
	 * 
	 * @return    Whether the current state is in a conflicted state.
	 */
	public boolean checkState() {
		if (this.conflictedState == true)
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
		return this.conflictedState;
	}
	
	/**
	 * Main method to handle the operation for the whole project
	 * 
	 * @param args   The name of the corresponding command
	 */
	public static void main(String[] args) {
		String gitFile = ".gitlet/gitFile.ser";
		File gitSer = new File(gitFile);
		Gitlet git = null;
		
		if (gitSer.exists()) {
			try {
                FileInputStream fileIn = new FileInputStream(gitSer);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                git = (Gitlet) objectIn.readObject();
                objectIn.close();
                fileIn.close();
            } catch (IOException e0) {
            
            } catch (ClassNotFoundException e1) {
            	
            }
		} else {
			git = new Gitlet();
		}
		argumentControl(git, args);
		try {
			FileOutputStream fileOut = new FileOutputStream(gitSer);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(git);
            objectOut.close();
            fileOut.close();
		} catch (IOException e) {
			
		}
	}
	
	
	/**
	 * Controlling the given arguments to call different method 
	 * corresponding different commands
	 * 
	 * @param git      The git object 
	 * @param args     All the arguments 
	 */
	public static void argumentControl(Gitlet git, String...args) {
		if (args.length == 0) {
			System.out.println("Please enter a command.");	
		} else if (args[0].equals("init") && args.length == 1) {
			if (git.checkState()) return;
			git.init();
		} else if (args[0].equals("add") && args.length == 2) {
			git.add(args[1]);
		} else if (args[0].equals("commit")) {
			if (args.length != 2) {
				System.out.println("Please enter a commit message.");
				return;
			} 
			if (args[1] == null || args[1].length() == 0 ) {
				System.out.println("Please enter a commit message.");
				return;
			}
			git.commit(args[1]);
			if (git.conflictedState) git.conflictedState = false;
		} else if (args[0].equals("log") && args.length == 1) {
			git.log(git.head);
		} else if (args[0].equals("global-log") && args.length == 1){
			git.globalLog();
		} else if (args[0].equals("find") && args.length == 2){
			git.find(args[1]);
		} else if (args[0].equals("rm") && (args.length == 2)) {
			git.remove(args[1]);
		} else if (args[0].equals("status") && (args.length == 1)) {
			git.status();
		} else if (args[0].equals("checkout") && (args.length == 2 || args.length == 3)) {
			if (args.length == 2) {
				if (git.branchNames.contains(args[1])) {
					if (git.checkState()) return;
				}
				git.checkoutOneArg(args[1]);
			} else {
				if (args[1].matches("-?\\d+(.\\d+)?")) 
					git.checkoutTwoArgs(Integer.parseInt(args[1]), args[2]);
			}		
		} else if (args[0].equals("branch") && (args.length == 2)){
			if (git.checkState()) return;
			git.createBranch(args[1]);
		} else if (args[0].equals("rm-branch") && (args.length == 2)){
			if (git.checkState()) return;
			git.rmBranch(args[1]);
		} else if (args[0].equals("reset") && (args.length == 2)) {
			if (git.checkState()) return;
			git.reset(Integer.parseInt(args[1]));
		} else if (args[0].equals("merge") && (args.length == 2)) {
			if (git.checkState()) return;
			git.merge(args[1]);
		} else if (args[0].equals("rebase") && (args.length == 2)) { 
			if (git.checkState()) return;
			git.rebase(args[1]);
		} else {
			System.out.println("No command with that name exists.");
		}
	}
}
