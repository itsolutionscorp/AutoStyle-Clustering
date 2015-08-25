import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This contains the functions the serialize the variables in Gitlet
 * 
 * @author Andrew Hild (AH)
 * @author Arshia Malkani (AX)
 * @author Katie Jiang (BI)
 * @author Nathaniel Low (AG)
 *
 *         All methods in this class were at least partly inspired by code
 *         acquired from Sarah Kim's CS61B website;
 *         http://www.sarahjikim.com/cs61b.html
 * 
 */

public class Serialize {

	// Methods for serializing objects
	
	/**
	 * 
	 * @param t
	 *            ArrayList of all Commits 
	 * 
	 */
	static void saveCommitTree(ArrayList<Commit> t) {
		if (t.isEmpty()) {
			return;
		}
		try {
			File commitTreeFile = new File(".gitlet/commitTree.ser");
			FileOutputStream fileOut = new FileOutputStream(commitTreeFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(t);
			objectOut.close();
		} catch (IOException e) {
		}
	}

	/**
	 * 
	 * @param h 
	 * 			head commit to be saved
	 * 
	 */
	static void saveCommitHead(Commit h) {
		if (h == null) {
			return;
		}
		try {
			File myCommitHeadFile = new File(".gitlet/myCommitHead.ser");
			FileOutputStream fileOut = new FileOutputStream(myCommitHeadFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(h);
			objectOut.close();
		} catch (IOException e) {
		}
	}
	/**
	 * 
	 * @param current
	 * 				String name or current branch
	 * 
	 */
	static void saveCurrentBranch(String current) {
		if (current == null) {
			return;
		}
		try {
			File myCurrentBranchFile = new File(".gitlet/myCurrentBranch.ser");
			FileOutputStream fileOut = new FileOutputStream(myCurrentBranchFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(current);
			objectOut.close();
		} catch (IOException e) {
		}
	}
	/**
	 * 
	 * @param b
	 * 			HashMap storing the names of all branches as well as information relevant to each branch
	 * 
	 */
	static void saveBranches(HashMap<String, Commit[]> b) {
		if (b == null) {
			return;
		}
		try {
			File myBranchesFile = new File(".gitlet/myBranches.ser");
			FileOutputStream fileOut = new FileOutputStream(myBranchesFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(b);
			objectOut.close();
		} catch (IOException e) {
		}
	}
	
	/**
	 * 
	 * @param staging
	 * 				ArrayList of files contained in the staging area
	 * 
	 */
	static void saveStaging(ArrayList<File> staging) {
		if (staging == null) {
			return;
		}
		try {
			File myStagingFile = new File(".gitlet/stagingArea.ser");
			FileOutputStream fileOut = new FileOutputStream(myStagingFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(staging);
			objectOut.close();
		} catch (IOException e) {
		}
	}

	/**
	 * 
	 * @param untracked
	 * 				ArrayList of files marked for untracking
	 * 
	 */
	static void saveUntracked(ArrayList<File> untracked) {
		if (untracked == null) {
			return;
		}
		try {
			File myUntrackedFile = new File(".gitlet/untracked.ser");
			FileOutputStream fileOut = new FileOutputStream(myUntrackedFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(untracked);
			objectOut.close();
		} catch (IOException e) {
		}
	}

	/**
	 * 
	 * @param conflict
	 * 				Boolean indicating whether Gitlet is in a conflicted state
	 * 
	 */
	static void saveConflicted(Boolean conflict) {
		if (conflict == null) {
			return;
		}
		try {
			File myConflictFile = new File(".gitlet/conflicted.ser");
			FileOutputStream fileOut = new FileOutputStream(myConflictFile);
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(conflict);
			objectOut.close();
		} catch (IOException e) {
		}
	}

	// methods for unpacking serialized objects

	/**
	 * 
	 * @return head Commit
	 * 
	 */

	static Commit tryLoadingMyCommitHead() {
		Commit myCommitHead = null;
		File myCommitHeadFile = new File(".gitlet/myCommitHead.ser");
		if (myCommitHeadFile.exists()) {
			try {
				FileInputStream fileIn = new FileInputStream(myCommitHeadFile);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				myCommitHead = (Commit) objectIn.readObject();
				objectIn.close();
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
			}
		}
		return myCommitHead;
	}

	/**
	 * 
	 * @return String name of current branch
	 * 
	 */
	static String tryLoadingMyCurrentBranch() {
		String myCurrentBranch = null;
		File myCurrentBranchFile = new File(".gitlet/myCurrentBranch.ser");
		if (myCurrentBranchFile.exists()) {
			try {
				FileInputStream fileIn = new FileInputStream(
						myCurrentBranchFile);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				myCurrentBranch = (String) objectIn.readObject();
				objectIn.close();
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
			}
		}
		return myCurrentBranch;
	}

	/**
	 * 
	 * @return HashMap of names of branches as keys and other relevant
	 *         information as values
	 *         
	 */
	@SuppressWarnings("unchecked")
	static HashMap<String, Commit[]> tryLoadingMyBranches() {
		HashMap<String, Commit[]> myBranches = null;
		File myBranchesFile = new File(".gitlet/myBranches.ser");
		if (myBranchesFile.exists()) {
			try {
				FileInputStream fileIn = new FileInputStream(myBranchesFile);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				myBranches = (HashMap<String, Commit[]>) objectIn.readObject();
				objectIn.close();
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
			}
		}
		return myBranches;
	}

	/**
	 * 
	 * @return ArrayList of files that are in the staging area
	 * 
	 */
	@SuppressWarnings("unchecked")
	static ArrayList<File> tryLoadingStaging() {
		ArrayList<File> stagingArea = new ArrayList<>();
		File myStagingFile = new File(".gitlet/stagingArea.ser");
		if (myStagingFile.exists()) {
			try {
				FileInputStream fileIn = new FileInputStream(myStagingFile);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				stagingArea = (ArrayList<File>) objectIn.readObject();
				objectIn.close();
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
			}
		}
		return stagingArea;
	}

	/**
	 * 
	 * @return ArrayList of files marked for untracking
	 * 
	 */
	@SuppressWarnings("unchecked")
	static ArrayList<File> tryLoadingUntracked() {
		ArrayList<File> untracking = new ArrayList<>();
		File myUntrackedFile = new File(".gitlet/untracked.ser");
		if (myUntrackedFile.exists()) {
			try {
				FileInputStream fileIn = new FileInputStream(myUntrackedFile);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				untracking = (ArrayList<File>) objectIn.readObject();
				objectIn.close();
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
			}
		}
		return untracking;
	}

	/**
	 * 
	 * @return ArrayList of all Commits
	 * 
	 */
	@SuppressWarnings("unchecked")
	static ArrayList<Commit> tryLoadingCommitTree() {
		ArrayList<Commit> tree = new ArrayList<Commit>();
		File commitTreeFile = new File(".gitlet/commitTree.ser");
		if (commitTreeFile.exists()) {
			try {
				FileInputStream fileIn = new FileInputStream(commitTreeFile);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				tree = (ArrayList<Commit>) objectIn.readObject();
				objectIn.close();
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
			}
		}
		return tree;
	}

	/**
	 * 
	 * @return Boolean indicating conflicted state
	 * 
	 */
	static Boolean tryLoadingConflicted() {
		File myUntrackedFile = new File(".gitlet/conflicted.ser");
		Boolean conflicted = false;
		if (myUntrackedFile.exists()) {
			try {
				FileInputStream fileIn = new FileInputStream(myUntrackedFile);
				ObjectInputStream objectIn = new ObjectInputStream(fileIn);
				conflicted = (Boolean) objectIn.readObject();
				objectIn.close();
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
			}
		}
		return conflicted;
	}

}
