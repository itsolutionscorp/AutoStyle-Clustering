import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * The CommitGraph class holds a series of information about itself and its relationships including: 
 * commitID, its parentID, its childrens'IDs and branches, where its branch last was split, all the branches in
 * its history, its commit message, its branchName, and the Files it should keep track of along with where they are.
 * It also know when it was created.
 */
public class CommitGraph implements Serializable {
	private Database myDB; 
	private int myCommitID; // index in DBase
	private int myParentID; // the commit id of this commit's parent
	private HashMap<String,Integer> myChildren; 
	private int mySplitID; //where the branch last split
	private HashSet<String> myHistoryBranch; //keeps track of branches along the same branch as it were
	String myCommitMsg;
	private String myBranchName;
	private HashMap<String, Integer> myFiles; // A list of the current revisions for each file in the current node
	private String myCommitTime; // ­The date/time when the node was created.

	/**
	 * Initial Commit Constructor
	 * 
	 * @param DB
	 * 	-takes only DB. Same for every initial commit.
	 */
	public CommitGraph(Database DB) { // initializes a initial commit
		myDB = DB;
		myCommitID = 0;
		myParentID = -1; // useless value because it has no parent 
		mySplitID = 0;
		myChildren = new HashMap<String,Integer>();
		DB.getDbase().put(myCommitID, this);
		myCommitMsg = "initial commit";
		myBranchName = "master"; // default name
		myDB.getBranchNames().add(myBranchName);
		myFiles = new HashMap<String, Integer>(); // name and location(via CommitID) of file
		myHistoryBranch = new HashSet<String>();
		myHistoryBranch.add(myBranchName);
		
		//sets Date
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		myCommitTime = dateFormat.format(date);
		File commit = new File(".gitlet/commit" + myCommitID);
		if (!commit.exists()) {
			commit.mkdirs();
		}
	}

	/**
	 * Normal Commit Constructor
	 * 
	 * @param DB
	 * 		-Database to keep track of states.
	 * 
	 * @param parentID
	 * 		-to get information from its parentID
	 * 
	 * @param msg
	 * 		-its commit message from the user
	 */
	public CommitGraph(Database DB, int parentID, String msg)
			throws IOException {
		myDB = DB;
		myCommitID = myDB.getDbase().size();
		myParentID = parentID;
		myChildren = new HashMap<String,Integer>();
		HashMap<String,Integer> parentChildren= myDB.getDbase().get(myParentID).getChildren();
		parentChildren.put(myDB.getCurrentBranch(),myCommitID);

		
		myCommitMsg = msg;
		myBranchName = myDB.getCurrentBranch();
		myHistoryBranch = myDB.getDbase().get(myParentID).getHistoryBranch();
		File commit = new File(".gitlet/commit" + myCommitID);
		if (!commit.exists()) {
			commit.mkdirs();
		}
		myFiles = new HashMap<String, Integer>();
		HashMap<String, Integer> parentFiles = DB.getDbase().get(myParentID).getFiles();
		myFiles.putAll(parentFiles);
		

		if (myDB.getSplitMemory(myBranchName) == true) {
			mySplitID = myCommitID;
			myDB.setSplitMemory(myBranchName, false);
		} else {
			mySplitID = DB.getDbase().get(myParentID).getSplit();
			
		}
		// myFiles is an ArrayList that holds all the file names that this
		// commit should memorize,
		// and it should include the following two things:
		// 1. Inherits all of its parents files
		// 2.Remove the files that are in untracked from myFiles
		// 3.the file names in the staged area and if the file exists in the
		// parent's myFiles, change the version number in myFilesplaces (using the HashMap myFiles)

		HashSet<String> toUntrack = myDB.getUntracked();
		Iterator<String> toUntrackIter = toUntrack.iterator();
		while (toUntrackIter.hasNext()) {
			String next = toUntrackIter.next();
			myFiles.remove(next);
		}

		HashSet<String> toStaged = myDB.getStaged();
		Iterator<String> toStagedIter = toStaged.iterator();

		while (toStagedIter.hasNext()) {
			String filename = toStagedIter.next();
			// puts the file into the commit folder
			File baby = new File(filename);
			if (baby.exists() && baby.isFile()) {
				File dest = new File(".gitlet/commit" + myCommitID + "/",
						filename);
				// newly added to make it able to copy a file inside a subfolder
				dest.getParentFile().mkdirs();
				Files.copy(baby.toPath(), dest.toPath(),
						StandardCopyOption.REPLACE_EXISTING);
			}
			myFiles.put(filename, myCommitID);
		}
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		myCommitTime = dateFormat.format(date);
	}

	

	/**
	 * Rebase CommitGraph Constructor. FOR CLONING >:)
	 * 
	 * @param destID
	 * 	-where it will copy to, (the BranchEnd of the current branch);
	 * 
	 * @param cloneMom
	 * 	-a commitGraph that will be copied over
	 * 
	 */
	public CommitGraph(CommitGraph cloneMom, int destID) { // for copying
		myDB = cloneMom.getDbase(); 
		myCommitID = myDB.getDbase().size();
		myParentID = destID;
		HashMap<String,Integer> parentChildren= myDB.getDbase().get(myParentID).getChildren();
		parentChildren.put(cloneMom.getBranchName(),myCommitID);

		mySplitID = myDB.getDbase().get(myParentID).getSplit();
		myCommitMsg = cloneMom.getMessage();
		myChildren= new HashMap<String,Integer>();
		myBranchName= cloneMom.getBranchName();
		myFiles = cloneMom.getFiles();
	
		File commit = new File(".gitlet/commit" + myCommitID);
		if (!commit.exists()) {
			commit.mkdirs();
		} 
		File source = new File(".gitlet/commit"+cloneMom.getCommitID());
		File dest = new File(".gitlet/commit"+myCommitID );
		try {
			copyDirectory(source, dest);
		} catch (IOException e) {
			System.out.println("problem copying directory");
		}
		//time stamp
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		myCommitTime = dateFormat.format(date);
		
		myHistoryBranch = myDB.getDbase().get(myParentID).getHistoryBranch();
		myHistoryBranch.add(myBranchName);
	}
	
	
	/**
	 * 
	 * To copy all the contents in a directory. Used for cloned CommitGraphs from rebase.
	 * 
	 * from http://www.java-tips.org/java-se-tips-100019/18-java-io/854-how-to-copy-a-directory-from-one-location-to-another-location.html
	 * 
	 * @param sourceLocation 
	 * 		-where you copy from 
	 * @param targetLocation
	 * 		-where you copy to
	 * @throws IOException
	 */
	public void copyDirectory(File sourceLocation, File targetLocation)
			throws IOException {
		if (sourceLocation.isDirectory()) {
			String[] children = sourceLocation.list();
			for (int i = 0; i < children.length; i++) {
				copyDirectory(new File(sourceLocation, children[i]), new File(
						targetLocation, children[i]));
			}
		} else {
			InputStream in = new FileInputStream(sourceLocation);
			OutputStream out = new FileOutputStream(targetLocation);
			// Copy the bits from instream to outstream
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		}
	}
	
	
/**
 * 	GetMethods for every instance variable ever. Accessed by parents and children and Database.
 */
	
	public HashSet<String> getHistoryBranch() {
		return myHistoryBranch;
	}

	public void changeBranchName(String newName) {
		myBranchName = newName;
	}

	public String getBranchName() {
		return myBranchName;
	}

	public HashMap<String,Integer> getChildren() {
		return myChildren;
	}

	public HashMap<String, Integer> getFiles() {
		return myFiles;
	}

	public int getCommitID() {
		return myCommitID;
	}

	public String getCommitTime() {
		return myCommitTime;
	}

	public String getMessage() {
		return myCommitMsg;
	}

	public int getParent() {
		return myParentID;
	}

	public int getSplit() {
		return mySplitID;
	}

	public Database getDbase() {
		return myDB;
	}
	


}