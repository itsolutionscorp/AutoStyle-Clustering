// ===============================TESTING========================================

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

/**
 * Class that provides JUnit tests for Gitlet, as well as a couple of utility
 * methods.
 * 
 * @author Joseph Moghadam
 * 
 *         Some code adapted from StackOverflow:
 * 
 *         http://stackoverflow.com/questions
 *         /779519/delete-files-recursively-in-java
 * 
 *         http://stackoverflow.com/questions/326390/how-to-create-a-java-string
 *         -from-the-contents-of-a-file
 * 
 *         http://stackoverflow.com/questions/1119385/junit-test-for-system-out-
 *         println
 * 
 */
public class GitletTest {
	private static final String GITLET_DIR = ".gitlet/";
	private static final String STAGING_DIR = ".gitlet/.staging/";
	private static final String COMMIT_DIR = ".gitlet/.commit/";
	private static final String TESTING_DIR = "test_files/";

	/* matches either unix/mac or windows line separators */
	private static final String LINE_SEPARATOR = "\r\n|[\r\n]";

	/**
	 * Deletes existing gitlet system, resets the folder that stores files used
	 * in testing.
	 * 
	 * This method runs before every @Test method. This is important to enforce
	 * that all tests are independent and do not interact with one another.
	 */
	@Before
	public void setUp() {
		File f = new File(GITLET_DIR);
		if (f.exists()) {
			try {
				recursiveDelete(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		f = new File(TESTING_DIR);
		if (f.exists()) {
			try {
				recursiveDelete(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		f.mkdirs();
	}

	/**
	 * Tests that init creates a .gitlet directory. Does NOT test that init
	 * creates an initial commit, which is the other functionality of init.
	 */
	@Test
	public void testBasicInitialize() {
		gitlet("init");
		File f = new File(GITLET_DIR);
		assertTrue(f.exists());
	}

	/**
	 * Tests that checking out a file name will restore the version of the file
	 * from the previous commit. Involves init, add, commit, and checkout.
	 */
	@Test
	public void testBasicCheckout() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", wugFileName);
		assertEquals(wugText, getText(wugFileName));
	}

	/**
	 * Tests that log after one commit conforms to the format in the spec.
	 * Involves init, add, commit, and log.
	 */
	@Test
	public void testBasicLog() {
		gitlet("init");
		String commitMessage1 = "initial commit";

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
	}

	// OUR TESTS

	/**
	 * Tests that after init, the directory .gitlet is created, the directories
	 * .gitlet/.staging and .gitlet/.commit are created, the initial commit is
	 * committed, and the initial commit is stored in a folder named by its
	 * unique Integer ID.
	 * 
	 * Also tests that there is no IOException or ClassNotFoundException. Tests
	 * that if a .gitlet directory already exists, an error message is printed.
	 */
	@Test
	public void testInit() {
		command("java", "Gitlet", "init");
		File e = new File(GITLET_DIR);
		assertTrue(e.exists());
		File f = new File(STAGING_DIR);
		File g = new File(COMMIT_DIR);
		assertTrue(f.exists());
		assertTrue(g.exists());

		File[] commits = g.listFiles();
		File[] initCommit = commits[0].listFiles();

		Commit input = null;
		try {
			FileInputStream fileIn = new FileInputStream(initCommit[0]);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			input = (Commit) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException err) {
			fail("IOException occurred during init: " + err.getMessage());
		} catch (ClassNotFoundException err) {
			fail("ClassNotFoundException occurred during init: "
					+ err.getMessage());
		}
		assertEquals(input.getMessage(), "initial commit");

		Integer ID = input.getID();
		assertEquals(commits[0].getPath(),
				COMMIT_DIR + Integer.toString(ID));

		String result = gitlet("init");
		assertEquals(result,
				"A gitlet version control system already exists in the current directory.");
	}

	/**
	 * Tests that files not previously marked for untracking are added to the
	 * staging area in .gitlet. Tests that files which had been marked for
	 * untracking are not added to the staging area, but are now included in the
	 * future commit. Tests that when a file does not exist, the correct error
	 * message is printed.
	 */
	@Test
	public void testAdd() {
		gitlet("init");
		String file1Name = TESTING_DIR + "File1.txt";
		createFile(file1Name, "This is file 1.");
		gitlet("add", file1Name);
		File file1 = new File(STAGING_DIR + file1Name);
		// file added to staging area
		assertTrue(file1.exists());

		gitlet("commit", "Commiting File1.txt.");

		// marking File1 for untracking
		gitlet("rm", file1Name);
		// should not be added to the staging area
		gitlet("add", file1Name);
		assertFalse(file1.exists());

		String result = gitlet("add", "Nonexistent.txt");
		assertEquals(result, "File does not exist.");
	}

	/**
	 * Tests that previously untracked (new) files are included in a current
	 * commit folder and tracked in upcoming commits, that any updated files
	 * should now be tracked and tied to a new commit ID, and that any old
	 * tracked files are not added to the new commit folder. Tests that staging
	 * area is cleared after the commit is made.
	 */

	@Test
	public void testCommit() {
		// Previously untracked, or new files should be included in folder for
		// current commit
		// and tracked in future commits
		gitlet("init");
		createFile("File1.txt", "This is file 1.");
		File nest1 = new File("nest1/");
		nest1.mkdir();
		createFile("nest1/File2.txt", "This is nested file 2.");
		gitlet("add", "File1.txt");
		gitlet("add", "nest1/File2.txt");

		gitlet("commit", "Committing two new files.");
		String ID = gitlet("find", "Committing two new files.");

		String commitFolder = COMMIT_DIR + ID;

		File file = new File(commitFolder + "/" + ID + ".ser");
		Commit theCommit = readObj(file);

		// Check that updated files are copied into folder for new commit
		assertTrue(new File(commitFolder + "/File1.txt").exists());
		assertTrue(new File(commitFolder + "/nest1/File2.txt").exists());

		// Check that updated files are now being tracked
		assertTrue(theCommit.getFileToCommit().get("File1.txt") == Integer
				.parseInt(ID));
		assertTrue(theCommit.getFileToCommit().get("nest1/File2.txt") == Integer
				.parseInt(ID));

		// Updated files included in folder for current commit, update tracking
		// to track to appropriate ID (not tracking the old one)

		// Update a file and stage it (by adding it)
		writeFile("File1.txt", "New text for File1.");
		gitlet("add", "File1.txt");
		// make a new commit
		gitlet("commit", "Committing File1.");
		// check that the changed file is in new commit folder and tracked
		// appropriately
		String secondID = gitlet("find", "Committing File1.");

		String secondCommitFolder = COMMIT_DIR + secondID;

		File secondFile1 = new File(secondCommitFolder + "/" + secondID
				+ ".ser");
		Commit theSecondCommit = readObj(secondFile1);
		assertTrue(new File(secondCommitFolder + "/File1.txt").exists());
		assertTrue(theSecondCommit.getFileToCommit().get("File1.txt") == Integer
				.parseInt(secondID));

		// Old tracked files NOT copied to commit folder
		assertFalse(new File(secondCommitFolder + "/nest1/File2.txt").exists());
		// Make sure File1 is the new File1, not the old File1
		assertEquals(getText(secondCommitFolder + "/File1.txt"),
				"New text for File1.");

		// Staging area empty after a commit
		File stagingDirectory = new File(STAGING_DIR);
		assertTrue(stagingDirectory.listFiles().length == 0);

		// Files marked for untracking no longer tracked
		gitlet("rm", "nest1/File2.txt");
		gitlet("commit", "Untracked File2.");

		// File2 no longer tracked
		String thirdID = gitlet("find", "Untracked File2.");
		String thirdCommitFolder = COMMIT_DIR + thirdID;
		File thirdFile = new File(thirdCommitFolder + "/" + thirdID + ".ser");
		Commit theThirdCommit = readObj(thirdFile);
		assertFalse(theThirdCommit.getFileToCommit().containsKey(
				"nest1/File2.txt"));
	}

	/**
	 * Tests that when rm is called on staged files, they are removed from the
	 * staging area in .gitlet. Tests that when rm is called on tracked files,
	 * they are not tracked in later commits/descendants of that commit. Tests
	 * that when rm is called on files neither staged nor tracked by the head
	 * commit, the appropriate error message is printed.
	 */
	@Test
	public void testRm2() {
		gitlet("init");
		createFile("File1.txt", "This is file 1.");
		File nest1 = new File("nest1/");
		nest1.mkdir();
		createFile("nest1/File2.txt", "This is nested file 2.");
		gitlet("add", "File1.txt");
		gitlet("add", "nest1/File2.txt");

		// Staging area contains the newly added files
		File stagingDirectory = new File(STAGING_DIR);
		String[] stagingLst = stagingDirectory.list();
		assertTrue(Arrays.asList(stagingLst).contains("File1.txt"));

		String[] nest = new File(STAGING_DIR + "nest1").list();
		assertTrue(Arrays.asList(nest).contains("File2.txt"));

		// Unstage File1
		gitlet("rm", "File1.txt");

		// Staging area should no longer contain File1
		String[] stagingLst2 = stagingDirectory.list();
		assertFalse(Arrays.asList(stagingLst2).contains("File1.txt"));

		// File1 shouldn't be included in upcoming commit
		gitlet("commit", "Added file2, but not file1.");

		// Check that File1 is not in the latest commit folder (though File2 is)
		String commitID = gitlet("find", "Added file2, but not file1.");

		String commitFolder = COMMIT_DIR + commitID;

		File commitObjFile = new File(commitFolder + "/" + commitID + ".ser");
		assertTrue(new File(commitFolder + "/nest1/File2.txt").exists());
		assertFalse(new File(commitFolder + "File1.txt").exists());

		// File1 also not being internally tracked, but File2 is being tracked
		// (in fileToCommit)
		Commit commitObj = readObj(commitObjFile);
		HashMap<String, Integer> file = commitObj.getFileToCommit();
		assertFalse(file.containsKey("File1.txt"));
		assertTrue(file.containsKey("nest1/File2.txt"));

		// rm a file that was tracked previously --> no longer included in
		// upcoming commits
		gitlet("rm", "nest1/File2.txt");
		gitlet("commit", "Removed File2.");

		String commitID2 = gitlet("find", "Removed File2.");
		String commitFolder2 = COMMIT_DIR + commitID2;

		File commitFolder2Dir = new File(commitFolder2);
		String[] commitFolder2Files = commitFolder2Dir.list();
		assertFalse(Arrays.asList(commitFolder2Files).contains("nest1"));

		// rm a file neither staged nor tracked --> error message
		createFile("UnusedFile.txt", "Ladida"); // so it really does exist
		String result = gitlet("rm", "UnusedFile.txt");
		assertEquals(result, "No reason to remove the file.");

	}

	// OUR END TO END TESTS 

	/**
	 * Test that a file that has been committed at some point can be restored by
	 * checking it out from the commit it was committed at.
	 */
	@Test
	public void testRestoreCommit() {
		gitlet("init");
		// Add some files
		createFile("File1.txt", "This is File 1.");
		gitlet("add", "File1.txt");
		// Make some commit
		gitlet("commit", "Committing File 1.");
		// Change the files in the working directory
		writeFile("File1.txt", "Boom chicka wow File 1.");
		assertEquals(getText("File1.txt"), "Boom chicka wow File 1.");
		gitlet("add", "File1.txt");
		createFile("File2.txt", "This is File 2.");
		gitlet("add", "File2.txt");
		gitlet("commit", "Changed File 1 and added File 2.");
		// Check out files from a commit they were committed at
		// Ensure that the file in working directory is the same as the
		// file in the commit it was being checked out from
		gitlet("checkout", "File1.txt"); // checking out from the head commit
		assertEquals(getText("File1.txt"), "Boom chicka wow File 1.");

		String ID = gitlet("find", "Committing File 1.");
		gitlet("checkout", ID, "File1.txt"); // checking out from an earlier
												// commit
		assertEquals(getText("File1.txt"), "This is File 1.");

	}

	/**
	 * Test that a file that has been committed at some point can be restored by
	 * checking it out from a commit that tracks that version of the file, even
	 * if the file wasn't staged prior to that commit.
	 */
	@Test
	public void testRestoreOldCommit() {
		gitlet("init");
		// Add some files
		createFile("File1.txt", "This is File 1.");
		// Make commit
		gitlet("commit", "Committing File1.");
		// Add some other files, modify one of the previously added files
		createFile("File3.txt", "This is File 3.");
		writeFile("File1.txt", "Modified file 1.");
		gitlet("add", "File3.txt");
		gitlet("add", "File1.txt");
		// Make some commit
		gitlet("commit", "Added file 3 and modified file 1.");
		createFile("File2.txt", "This is file 2.");
		gitlet("add", "File2.txt");
		gitlet("commit", "Added a File 2."); // File 1 is not staged in this
												// commit
		// check out some files tracked by third commit, but not staged prior to
		// third commit
		String ID = gitlet("find", "Added a File 2.");
		gitlet("checkout", ID, "File1.txt");
		// Ensure that those are the same as the files in the commit they were
		// last staged in
		assertEquals(getText("File1.txt"), "Modified file 1.");
	}

	/**
	 * Test that you can checkout [id] [file] from an arbitrary commit in the
	 * graph (even one in another branch).
	 */
	@Test
	public void testCheckoutArbitraryCommit() {
		gitlet("init");
		// Add some files
		createFile("File1.txt", "This is File 1.");
		gitlet("add", "File1.txt");
		createFile("File2.txt", "This is file 2.");
		gitlet("add", "File2.txt");
		// Make commit
		gitlet("commit", "Committing File1 and 2.");
		// make branch of commits
		gitlet("branch", "Cats");
		// check out that new branch
		gitlet("checkout", "Cats");
		// add some files and make commits there
		createFile("FileCat.txt", "This is FileCat.");
		gitlet("add", "FileCat.txt");
		gitlet("commit", "First commit in FileCat branch!");

		// check out original branch (master)
		gitlet("checkout", "master");
		// add some files and make commit
		createFile("A.txt", "A.");
		gitlet("add", "A.txt");
		// see if we can check out files from the other branch

	}

	/**
	 * Test that resetting backward appropriately changes the output of log.
	 */
	@Test
	public void testLogAfterBackReset() {
		gitlet("init");
		// do the usual adding making a commit
		createFile("File1.txt", "This is File 1.");
		createFile("File2.txt", "This is File 2.");
		createFile("File3.txt", "This is File 3.");

		gitlet("add", "File1.txt");
		gitlet("commit", "Committing file 1.");

		// make another commit
		gitlet("add", "File2.txt");
		gitlet("commit", "Committing file 2.");
		// make yet another commit
		gitlet("add", "File3.txt");
		gitlet("commit", "Committing file 3.");

		// see that log has all three of these messages
		String[] msgs = extractCommitMessages(gitlet("log"));
		assertEquals(msgs[3], "initial commit");
		assertEquals(msgs[2], "Committing file 1.");
		assertEquals(msgs[1], "Committing file 2.");
		assertEquals(msgs[0], "Committing file 3.");

		// reset to the second commit (should move internal head pointer)
		String ID = gitlet("find", "Committing file 2.");
		gitlet("reset", ID);

		// test that log's messages now start at the second commit and go to the
		// oldest commit (doesn't include third commit's messages)
		String[] msgs2 = extractCommitMessages(gitlet("log"));

		assertFalse(Arrays.asList(msgs2).contains("Committing file 3."));
	}

	/**
	 * Test that log adjusts appropriately when switching from one branch to
	 * another.
	 */
	@Test
	public void testLogAfterSwitchBranch() {
		gitlet("init");
		// do the usual adding making a commit
		createFile("File1.txt", "This is File 1.");
		gitlet("add", "File1.txt");
		gitlet("commit", "Committing file 1.");

		// make a branch and check it out
		gitlet("branch", "Fluffybranch");
		gitlet("checkout", "Fluffybranch");
		// commit some stuff
		writeFile("File1.txt", "THIS IS FILE 1!!!");
		gitlet("add", "File1.txt");
		gitlet("commit", "Changed File 1.");
		// test log, should be from that branch backward
		String[] msgs = extractCommitMessages(gitlet("log"));
		assertEquals(msgs[0], "Changed File 1.");
		assertEquals(msgs[2], "initial commit");

		// check out the other branch
		gitlet("checkout", "master");
		// log should only have stuff from that branch backward
		String[] msgs2 = extractCommitMessages(gitlet("log"));
		assertEquals(msgs2[0], "Committing file 1.");
		assertEquals(msgs2[1], "initial commit");

	}

	/**
	 * Test that merge will generate a .conflicted file if a file has been
	 * modified in both branches since the split point.
	 */
	@Test
	public void testMergeConflicts() {
		gitlet("init");
		createFile("File1.txt", "This is File 1.");
		gitlet("add", "File1.txt");

		// commit
		gitlet("commit", "Committing file 1.");
		gitlet("branch", "NewBranch");
		writeFile("File1.txt", "This is File 1 Junior.");
		gitlet("add", "File1.txt");
		gitlet("commit", "File1 had a son in this commit."); // master branch

		// create another branch
		gitlet("checkout", "NewBranch");
		writeFile("File1.txt", "This is File 1 Twin.");
		gitlet("add", "File1.txt");
		gitlet("commit", "File 1 discovered its twin in this commit."); // NewBranch

		// merge the branches
		gitlet("merge", "master");
		// check for .conflicted file in working directory
		assertTrue(new File("File1.txt.conflicted").exists());

	}

	/**
	 * Test that merge will commit with files from the other branch if those
	 * files had been modified in the other branch but not in the current branch
	 * since the split point.
	 */
	@Test
	public void testMergeCommitsAppropriate() {
		gitlet("init");
		createFile("File1.txt", "This is File 1.");
		gitlet("add", "File1.txt");

		// commit
		gitlet("commit", "Committing file 1.");
		gitlet("branch", "newBranch");

		writeFile("File1.txt", "File 1 is different.");
		gitlet("add", "File1.txt");
		gitlet("commit", "File1 is modified."); // master branch

		// switch to another branch
		gitlet("checkout", "newBranch");
		createFile("File2.txt", "This is File 2.");
		gitlet("add", "File2.txt");
		gitlet("commit", "Added a File 2, did not modify File 1."); // newBranch

		// merge the branches
		gitlet("merge", "master");
		String ID = gitlet("find", "Merged newBranch with master");
		System.out.println("ID: " + ID);
		// make sure the File1 is the file 1 after the split point from the
		// master branch (text should be latest)
		assertEquals(getText(COMMIT_DIR + ID + "/File1.txt"),
				"File 1 is different.");

	}

	/**
	 * Test that the output of log after a rebase includes the commit messages
	 * from both branches involved in the rebase.
	 */
	@Test
	public void testLogAfterRebase() {
		gitlet("init");
		createFile("File1.txt", "This is File 1.");
		createFile("File2.txt", "This is File 2.");
		createFile("File3.txt", "This is File 3.");
		gitlet("add", "File1.txt");
		// commit
		gitlet("commit", "Added File 1.");
		// make two branches
		gitlet("branch", "newBranch");
		gitlet("add", "File2.txt");
		gitlet("commit", "Added File 2.");

		gitlet("checkout", "newBranch");
		gitlet("add", "File3.txt");
		gitlet("commit", "Added File 3.");

		// rebase the new branch on top of the master
		gitlet("rebase", "master");

		// check that log goes from new branch head down to master head down to
		// their split point
		String[] msgs = extractCommitMessages(gitlet("log"));
		assertEquals(msgs[3], "initial commit");
		assertEquals(msgs[2], "Added File 1.");
		assertEquals(msgs[1], "Added File 2.");
		assertEquals(msgs[0], "Added File 3.");

	}

	/**
	 * Test that changes in the base branch propagate through the replayed
	 * branch during a rebase.
	 */
	@Test
	public void testChangesInReplayedBranch() {
		// create a commit with File 1, this will be the split point
		gitlet("init");
		createFile("File1.txt", "This is File 1.");
		gitlet("add", "File1.txt");
		gitlet("commit", "Added File 1.");
		// make a new branch
		gitlet("branch", "newBranch");
		// modify File 1 in the master branch
		writeFile("File1.txt", "New text for File 1.");
		gitlet("add", "File1.txt");
		gitlet("commit", "Modified File 1.");

		// switch to newBranch and make a commit that does not change File 1
		gitlet("checkout", "newBranch");
		createFile("File2.txt", "This is File 2.");
		gitlet("add", "File2.txt");
		gitlet("commit", "Added File 2, newBranch's first commit.");
		String origID = gitlet("find",
				"Added File 2, newBranch's first commit.");

		// still in newBranch, modify File1 and make another commit
		writeFile("File1.txt", "Yet more new text for File 1.");
		gitlet("add", "File1.txt");
		gitlet("commit", "Modified File 1 in newBranch.");

		// call rebase
		gitlet("rebase", "master");

		// check that if it was modified in current branch, it's unchanged
		gitlet("checkout", "File1.txt");
		assertEquals(getText("File1.txt"), "Yet more new text for File 1.");

		// get the ID of the replayed first node of newBranch after the split
		// point
		String pastIDs = gitlet("find",
				"Added File 2, newBranch's first commit.");
		String oneID = pastIDs.substring(pastIDs.indexOf("\n") + 1);
		String anotherID = pastIDs.substring(0, pastIDs.indexOf("\n"));
		String theID = oneID;
		if (oneID.equals(origID))
			theID = anotherID;

		// check that File1 from master branch took place of File1
		// in newBranch's first commit (where it did not modify File1)
		gitlet("checkout", theID, "File1.txt");
		assertEquals(getText("File1.txt"), "New text for File 1.");
	}

	/*
	 * Tests that any files that have been removed in the given branch since the
	 * split point, but not modified in the current branch since the split point
	 * are not deleted from the working directory, only marked for untracking --
	 * not included in the merge commit.
	 */
	@Test
	public void testMergeRemovals() {
		// make commit
		gitlet("init");
		createFile("File1.txt", "This is File 1.");
		gitlet("add", "File1.txt");
		gitlet("commit", "Added File 1.");

		// make a new branch
		gitlet("branch", "newBranch");

		// make commits in each branch (removing a file in the master branch)
		gitlet("rm", "File1.txt");
		gitlet("commit", "Removed file 1 in master branch.");
		gitlet("checkout", "newBranch");
		createFile("File2.txt", "This is File 2.");
		gitlet("add", "File2.txt");
		gitlet("commit", "Added File 2 to newBranch.");

		// merge
		gitlet("merge", "master");
		// check that working directory still contains the file
		File file1 = new File("File1.txt");
		assertTrue(file1.exists());
		// check that the merge commit does not track the file
		String ID = gitlet("find", "Merged newBranch with master");
		String filePath = COMMIT_DIR + ID + "/File1.txt";
		File file1Commit = new File(filePath);
		assertFalse(file1Commit.exists());

	}

	/**
	 * Tests that if merge generated at least one .conflicted file, then merge
	 * does not automatically make a commit, but instead prints the message
	 * "Encountered a merge conflict.", and then puts gitlet into a conflicted
	 * state. Tests that during a conflicted state, the user can only use the
	 * commands add, rm, commit, checkout [file], checkout [id] [file], log,
	 * global-log, find, and status. Tests that if a user tries to do a command
	 * that's not allowed while in a conflicted state, Gitlet does not perform
	 * the command and prints the appropriate error message. Tests that
	 * conflicted state ends once the user commits.
	 */
	@Test
	public void testMergeConflict() {

		// make commit
		gitlet("init");
		createFile("File1.txt", "This is File 1.");
		gitlet("add", "File1.txt");
		gitlet("commit", "Added File 1.");

		// make a new branch
		gitlet("branch", "newBranch");
		// (modify both files)
		writeFile("File1.txt", "File 1's new text.");
		gitlet("add", "File1.txt");
		gitlet("commit", "Modified File1 in the master branch.");
		gitlet("checkout", "newBranch");
		writeFile("File1.txt", "File 1's new text in newBranch.");
		gitlet("add", "File1.txt");
		gitlet("commit", "Modified File1 in newBranch.");

		// merge -- should generate conflicted file -- should print error
		// message, and
		// there should be no commit made saying "Merged blah blah"
		assertEquals(gitlet("merge", "master"), "Encountered a merge conflict.");
		assertEquals(gitlet("find", "Merged newBranch with master"),
				"Found no commit with that message.");
		// should be in conflicted state
		// in which user cannot call rebase, branch, etc. but can call log, etc.
		assertEquals(gitlet("branch", "coolBranch"),
				"Cannot do this command until the merge conflict has been resolved.");
		assertFalse(gitlet("log")
				.equals("Cannot do this command until the merge conflict has been resolved."));
		createFile("File2.txt", "This is File 2.");
		gitlet("add", "File2.txt"); // should still work in conflicted state

		// commit
		gitlet("commit", "I don't care I'm committing this merge.");
		// should not be in conflicted state anymore, can now call branch and
		// other commands
		assertTrue(gitlet("branch", "coolerBranch").isEmpty());

	}

	/**
	 * Tests rebase special case. If the current branch pointer is in the
	 * history of the given branch, rebase just moves the current branch to
	 * point to the same commit that the given branch points to. No commits are
	 * replayed in this case.
	 */
	@Test
	public void testRebaseEdgeCase() {
		// make commit
		gitlet("init");
		createFile("File1.txt", "This is File 1.");
		gitlet("add", "File1.txt");
		gitlet("commit", "Added File 1.");

		// make a new branch
		gitlet("branch", "newBranch");
		// keep working on master branch
		createFile("File2.txt", "This is File 2.");
		gitlet("add", "File2.txt");
		gitlet("commit", "Added File 2.");

		// save the log of the master branch (to be given branch)
		String[] msgs = extractCommitMessages(gitlet("log"));

		// check out current branch
		gitlet("checkout", "newBranch");
		// rebase
		gitlet("rebase", "master");
		// should not replay anything, current branch pointer should point to
		// same commit that given branch pointed to
		// in which case their log outputs are now the same
		String[] msgs2 = extractCommitMessages(gitlet("log"));
		assertTrue(msgs.length == msgs2.length);
		for (int i = 0; i < msgs.length; i++) {
			assertEquals(msgs[i], msgs2[i]);
		}
	}

	// UTILITY METHODS 
  
  /**
   * Read in a .ser file and return a Commit object.
   */
	private static Commit readObj(File fileObject) {
		Commit input = null;
		try {
			FileInputStream fileIn = new FileInputStream(fileObject);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			input = (Commit) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException err) {
		} catch (ClassNotFoundException err) {
		}
		return input;

	}

	/**
	 * Calls a gitlet command using the terminal.
	 * 
	 * Warning: Gitlet will not print out anything _while_ it runs through this
	 * command, though it will print out things at the end of this command. It
	 * will also return this as a string.
	 * 
	 * The '...' syntax allows you to pass in an arbitrary number of String
	 * arguments, which are packaged into a String[].
	 */
	private static String gitlet(String... args) {

		String[] commandLineArgs = new String[args.length + 2];
		commandLineArgs[0] = "java";
		commandLineArgs[1] = "Gitlet";
		for (int i = 0; i < args.length; i++) {
			commandLineArgs[i + 2] = args[i];
		}
		String results = command(commandLineArgs);
		System.out.println(results);
		return results.trim();
	}

	/**
	 * Another convenience method for calling Gitlet's main. This calls Gitlet's
	 * main directly, rather than through the terminal. This is slightly
	 * cheating the concept of end-to-end tests. But, this allows you to
	 * actually use the debugger during the tests, which you might find helpful.
	 * It's also a lot faster.
	 * 
	 * Warning: Like the other version of this method, Gitlet will not print out
	 * anything _while_ it runs through this command, though it will print out
	 * things at the end of this command. It will also return what it prints as
	 * a string.
	 */
	private static String gitletFast(String... args) {
		PrintStream originalOut = System.out;
		ByteArrayOutputStream printingResults = new ByteArrayOutputStream();
		try {
			/*
			 * Below we change System.out, so that when you call
			 * System.out.println(), it won't print to the screen, but will
			 * instead be added to the printingResults object.
			 */
			System.setOut(new PrintStream(printingResults));
			Gitlet.main(args);
		} finally {
			/*
			 * Restores System.out (So you can print normally).
			 */
			System.setOut(originalOut);
		}
		System.out.println(printingResults.toString());
		return printingResults.toString().trim();
	}

	/**
	 * Returns the text from a standard text file.
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
	 * Creates a new file with the given fileName and gives it the text
	 * fileText.
	 */
	private static void createFile(String fileName, String fileText) {
		File f = new File(fileName);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		writeFile(fileName, fileText);
	}

	/**
	 * Replaces all text in the existing file with the given text.
	 */
	private static void writeFile(String fileName, String fileText) {
		FileWriter fw = null;
		try {
			File f = new File(fileName);
			fw = new FileWriter(f, false);
			fw.write(fileText);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Deletes the file and all files inside it, if it is a directory.
	 * 
	 * @throws IOException
	 */
	private static void recursiveDelete(File d) throws IOException {
		if (d.isDirectory()) {
			for (File f : d.listFiles()) {
				recursiveDelete(f);
			}
		}
		if (!d.delete()) {
			throw new IOException("Failed to delete file " + d.getPath());
		}
	}

	/**
	 * Returns an array of commit messages associated with what log has printed
	 * out.
	 */
	private static String[] extractCommitMessages(String logOutput) {
		String[] logChunks = logOutput.split("===");
		int numMessages = logChunks.length - 1;
		String[] messages = new String[numMessages];
		for (int i = 0; i < numMessages; i++) {
			String[] logLines = logChunks[i + 1].split(LINE_SEPARATOR);
			messages[i] = logLines[3];
		}
		return messages;
	}

	/**
	 * Executes the given command on the terminal, and return what it prints out
	 * as a string.
	 * 
	 * Example: If you want to call the terminal command `java Gitlet add
	 * wug.txt` you would call the method like so: `command("java", "Gitlet",
	 * "add", "wug.txt");` The `...` syntax allows you to pass in however many
	 * strings you want.
	 */
	private static String command(String... args) {
		try {
			StringBuilder results = new StringBuilder();
			Process p = Runtime.getRuntime().exec(args);
			p.waitFor();
			try (BufferedReader br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));) {
				String line = null;
				while ((line = br.readLine()) != null) {
					results.append(line).append(System.lineSeparator());
				}
				return results.toString();
			}
		} catch (IOException e) {
			return e.getMessage();
		} catch (InterruptedException e) {
			return e.getMessage();
		}
	}
}