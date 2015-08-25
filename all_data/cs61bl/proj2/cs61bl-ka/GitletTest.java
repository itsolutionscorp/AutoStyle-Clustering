import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
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
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 }, extractCommitMessages(logContent));
	}

	/**
	 * Tests that a file that has been committed at some point can be restored
	 * by checking it out from the commit it was committed at.
	 */
	@Test
	public void testRestore() throws IOException {
		gitlet("init");
		String commitMessage1 = "initial commit";

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);

		// testing checkout [filename]

		File myFile = new File(wugFileName);
		recursiveDelete(myFile);
		assertFalse(myFile.exists());
		gitlet("checkout", wugFileName);
		assertTrue(myFile.exists());

	}

	/**
	 * Test that a file that has been committed at some point can be restored by
	 * checking it out from a commit that tracks that version of the file, even
	 * if the file wasn't staged prior to that commit.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testUnstagedRestore() throws IOException {
		gitlet("init");
		String commitMessage1 = "initial commit";

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);
		// wugFileName is now tracked

		String wugFileName2 = TESTING_DIR + "wug2.txt";
		String wugText2 = "This is a wug2.";
		createFile(wugFileName2, wugText2);
		gitlet("add", wugFileName2);
		String commitMessage3 = "added wug3";
		gitlet("commit", commitMessage3);
		// committed another file wihtout the first one,
		// first one should still be tracked

		File myFile = new File(wugFileName);
		recursiveDelete(myFile);
		assertFalse(myFile.exists());
		gitlet("checkout", wugFileName);
		assertTrue(myFile.exists());

	}

	/**
	 * Test that you can checkout [id] [file] from an arbitrary commit in the
	 * graph (even one in another branch).
	 */
	@Test
	public void testCheckoutIDFile() {
		// add code here
		gitlet("init"); // commit 0
		String commitMessage1 = "initial commit";

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2); // commit 1

		// create new branch and checkout to new branch
		String branchName = "branch1";
		gitlet("branch", branchName);
		gitlet("checkout", branchName);

		// create new file and commit it to new branch
		String branchWugFN = TESTING_DIR + "branchwug.txt";
		String branchWugTXT = "This is branch1's wug";
		createFile(branchWugFN, branchWugTXT);
		gitlet("add", branchWugFN);
		String commitMsg3 = "added brach1's wug";
		gitlet("commit", commitMsg3); // commit 2

		// checkout to master branch

		writeFile(branchWugFN, "This is not a wug.");
		gitlet("checkout", "master");
		assertEquals("This is not a wug.", getText(branchWugFN));

		// checkout new file from new branch to master branch
		gitlet("checkout", "2", branchWugFN); // "this is branch1's wug"
		gitlet("status");

		assertEquals(branchWugTXT, getText(branchWugFN));
	}

	@Test
	/**
	 * Tests find's funcionality and with a branch.
	 */
	public void testFind() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);

		assertEquals(gitlet("find", commitMessage2), "" + 1);

		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		gitlet("add", wugFileName);
		gitlet("commit", "commit2");

		assertEquals(gitlet("find", "commit2"), "" + 2);

	}

	@Test
	/**
	 * Tests remove-branch
	 */
	public void testRm_Branch() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);

		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		gitlet("checkout", "master");
		gitlet("rm-branch", "branch1");

		gitlet("checkout", "branch1");

	}

	@Test
	public void testRMthenMerge() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);

		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		gitlet("checkout", "master");
		gitlet("branch", "branch2");
		gitlet("rm-branch", "branch1");

		gitlet("merge", "branch1"); // print error
		gitlet("rebase", "branch1");
	}

	/**
	 * Test that resetting backward appropriately changes the output of log.
	 */
	@Test
	public void testResetLog() {
		// add code here
		gitlet("init"); // commit 0
		String commitMessage1 = "initial commit";

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2); // commit 1

		String file2Name = TESTING_DIR + "file2.txt";
		String file2Text = "This is a new file.";
		createFile(file2Name, file2Text);
		gitlet("add", file2Name);
		String commitMessage3 = "added new file";
		gitlet("commit", commitMessage3); // commit 2

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));

		// reset backward
		gitlet("reset", "1");
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 }, extractCommitMessages(logContent));
	}

	/**
	 * Test that log adjusts appropriately when switching from one branch to
	 * another.
	 */
	@Test
	public void testSwitchLog() {
		// add code here
		gitlet("init"); // commit 0
		String commitMessage1 = "initial commit";

		// MASTER BRANCH
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2); // commit 1

		String file2Name = TESTING_DIR + "file2.txt";
		String file2Text = "This is a new file.";
		createFile(file2Name, file2Text);
		gitlet("add", file2Name);
		String commitMessage3 = "added new file";
		gitlet("commit", commitMessage3); // commit 2

		// BRANCH 1
		// create new branch and checkout to new branch
		String branchName = "branch1";
		gitlet("branch", branchName);
		gitlet("checkout", branchName);

		// create new file and commit it to new branch
		String branchWugFN = TESTING_DIR + "branchwug.txt";
		String branchWugTXT = "This is branch1's wug";
		createFile(branchWugFN, branchWugTXT);
		gitlet("add", branchWugFN);
		String commitMsg4 = "added brach1's wug";
		gitlet("commit", commitMsg4); // commit 3

		// MASTER
		gitlet("checkout", "master");
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));

		// BRANCH 1
		gitlet("checkout", branchName);
		logContent = gitlet("log");

		assertArrayEquals(new String[] { commitMsg4, commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
	}

	/**
	 * Test that merge will generate a .conflicted file if a file has been
	 * modified in both branches since the split point.
	 */
	@Test
	public void testConflictedBranches() {
		// add code here
		gitlet("init");
		String commitMessage1 = "initial commit";

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);

		gitlet("branch", "branch1");
		String wugTextMaster = "This is NOT a wug.";
		writeFile(wugFileName, wugTextMaster);
		gitlet("add", wugFileName);
		gitlet("commit", "commitMaster");
		// made change from split point in master branch

		gitlet("checkout", "branch1");
		writeFile(wugFileName, "This is TOTALLY a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "commitBranch1");
		// made change form split point in branch1

		File myCompare = new File(wugFileName + ".conflicted");
		assertFalse(myCompare.exists());
		gitlet("merge", "branch1");
		assertFalse(myCompare.exists()); // This is TOTALLY a wug
		// merge same branch does nothing but print error

		gitlet("merge", "master"); // merge master to your current branch

		assertTrue(myCompare.exists());

	}

	/**
	 * Tests the case where the given branch has a changed file but current
	 * branch isn't tracking it
	 */
	@Test
	public void testConflictedBranches2() {
		// add code here
		gitlet("init");
		String commitMessage1 = "initial commit";

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);
		gitlet("branch", "branch1");
		writeFile(wugFileName, "This is NOT a wug");
		gitlet("add", wugFileName);
		gitlet("commit", "commitMaster");
		// made change from split point in master branch

		gitlet("checkout", "branch1");

		gitlet("rm", wugFileName);

		gitlet("add", "WILLY.txt");
		gitlet("commit", "notTrackingWug");
		// made change form split point in branch1

		File myCompare = new File(wugFileName + ".conflicted");
		assertFalse(myCompare.exists());
		gitlet("merge", "branch1");
		assertFalse(myCompare.exists()); // This is TOTALLY a wug
		// merge same branch does nothing but print error

		gitlet("merge", "master"); // merge master to your current branch
		File myCompare2 = new File(wugFileName);
		assertTrue(myCompare2.exists());
		gitlet("log");

	}

	/**
	 * Tests the merge case where there is a new file in the given branch that
	 * is not in the current branch
	 */
	@Test
	public void testConflictedBranches3() {
		// add code here
		gitlet("init");
		String commitMessage1 = "initial commit";

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);
		gitlet("branch", "branch1");
		createFile("WILLY.txt", "hi");
		gitlet("add", "WILLY.txt");
		gitlet("commit", "notInOthe");
		gitlet("checkout", "branch1");

		writeFile(wugFileName, "This is NOT a wug");

		gitlet("add", wugFileName);
		gitlet("commit", "commitMaster");

		File myCompare = new File(wugFileName + ".conflicted");
		assertFalse(myCompare.exists());
		gitlet("merge", "branch1");
		assertFalse(myCompare.exists()); // This is TOTALLY a wug
		// merge same branch does nothing but print error

		gitlet("merge", "master"); // merge master to your current branch
		File myCompare2 = new File("WILLY.txt");
		assertTrue(myCompare2.exists());
		gitlet("log");

	}

	/**
	 * Tests the merge case where the split point does not have a file but
	 * different versions of a new file are committed to given and current
	 * branches; should make a conflicted file
	 */
	@Test
	public void testConflictedBranches4() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		writeFile("WILLY.txt", "Willy is not gay");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);
		gitlet("branch", "branch1");
		gitlet("add", "WILLY.txt");
		gitlet("commit", "notInOthe");
		gitlet("checkout", "branch1");

		writeFile("WILLY.txt", "willy is So gay");

		gitlet("add", "WILLY.txt");
		gitlet("commit", "commitMaster");

		File myCompare = new File(wugFileName + ".conflicted");
		File myCompare2 = new File("WILLY.txt.conflicted");
		assertFalse(myCompare.exists());
		assertFalse(myCompare2.exists());
		gitlet("merge", "branch1");
		assertFalse(myCompare.exists()); // This is TOTALLY a wug
		assertFalse(myCompare2.exists());

		gitlet("merge", "master"); // merge master to your current branch
		assertFalse(myCompare.exists());
		assertTrue(myCompare2.exists());
		gitlet("log");
		gitlet("merge", "master"); // should print error message

		myCompare2.delete();

	}

	/**
	 * Test that merge will commit with files from the other branch if those
	 * files had been modified in the other branch but not in the current branch
	 * since the split point.
	 */
	@Test
	public void testCommit() {
		// add code here
		gitlet("init");
		String commitMessage1 = "initial commit";

		// MASTER (add 2 files: wug & del)
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";

		String delName = TESTING_DIR + "del.txt";
		String delTxt = "gonna delete this";
		createFile(delName, delTxt);
		gitlet("add", delName);
		String commitMessage3 = "added del file";

		gitlet("commit", commitMessage2); // commmit 1

		// Branch1 contains wug & del
		gitlet("branch", "branch1"); // new branch

		// MASTER - change wug and remove del
		String wugTextMaster = "This is NOT a wug."; // change file in master
		writeFile(wugFileName, wugTextMaster);
		gitlet("add", wugFileName);
		gitlet("rm", delName);
		gitlet("commit", "commitMaster"); // commit 2

		// BRANCH 1 has old wug and del
		gitlet("checkout", "branch1"); // switch to branch w/ old version
		assertEquals(wugText, getText(wugFileName));

		gitlet("merge", "branch1");
		assertEquals(wugText, getText(wugFileName)); // can't merge with
														// yourself

		File del = new File(delName);
		del.delete(); // delete from workspace to make sure merge doesn't bring
						// it back
		// branch1 should hae new wug and no del

		gitlet("merge", "master"); // merge master to your current branch
		assertEquals(wugTextMaster, getText(wugFileName)); // make sure it's new
															// wug

		gitlet("checkout", delName);
		assertFalse(del.exists()); // make sure del file was removed
	}

	/**
	 * Makes six commits of the same file whose content changes from 1 to 2 to 3
	 * to 4 to 5 to 6
	 * 
	 */
	@Test
	public void testCommit2() {
		gitlet("init");
		// makes six commits of the same file whose content changes from 1 to 2
		// to 3 to 4 to 5 to 6.
		String wugFileName = "WILLY.txt";
		String commitMessage2 = "added wug";
		writeFile(wugFileName, "1");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage2);

		writeFile(wugFileName, "2");
		gitlet("branch", "branch1");
		gitlet("add", wugFileName);
		gitlet("commit", "commitMaster");

		gitlet("checkout", "branch1");
		writeFile(wugFileName, "3");
		gitlet("add", wugFileName);
		gitlet("commit", "branch1");

		writeFile(wugFileName, "4");
		gitlet("add", wugFileName);
		gitlet("commit", "commit3");
		writeFile(wugFileName, "5");
		gitlet("add", wugFileName);
		gitlet("commit", "commit4");
		gitlet("checkout", "master");
		writeFile(wugFileName, "6");
		gitlet("add", wugFileName);
		gitlet("commit", "commit5");
	}

	/**
	 * Test that the output of log after a rebase includes the commit messages
	 * from both branches involved in the rebase.
	 */
	@Test
	public void testRebaseLog() {
		// add code here
		gitlet("init"); // commit 0
		String init = "initial commit";

		// BRANCH 1
		// create new branch and checkout to new branch
		String branchName = "branch1";
		gitlet("branch", branchName);
		gitlet("checkout", branchName);

		// create new file and commit it to new branch
		String branchWugFN = TESTING_DIR + "branchwug.txt";
		String branchWugTXT = "This is branch1's wug";
		createFile(branchWugFN, branchWugTXT);
		gitlet("add", branchWugFN);
		String commitMsg1 = "added brach1's wug";
		gitlet("commit", commitMsg1); // commit 1

		// MASTER BRANCH
		gitlet("checkout", "master");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2); // commit 2

		String file2Name = TESTING_DIR + "file2.txt";
		String file2Text = "This is a new file.";
		createFile(file2Name, file2Text);
		gitlet("add", file2Name);
		String commitMessage3 = "added new file";
		gitlet("commit", commitMessage3); // commit 3

		// MASTER
		// gitlet("checkout", "master");
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2, init }, extractCommitMessages(logContent));

		// BRANCH 1
		gitlet("checkout", branchName);
		logContent = gitlet("log");

		assertArrayEquals(new String[] { commitMsg1, init }, extractCommitMessages(logContent));

		// REBASE
		gitlet("rebase", "master");
		logContent = gitlet("log");
		System.out.println(logContent);
		assertArrayEquals(new String[] { commitMsg1, commitMessage3, commitMessage2, init },
				extractCommitMessages(logContent));

	}

	/**
	 * Test that changes in the base branch propagate through the replayed
	 * branch during a rebase.
	 */
	@Test
	public void testBaseBranchChange() {
		// add code here
		gitlet("init"); // commit 0
		String init = "initial commit";

		// new files in master
		String a = TESTING_DIR + "a.txt";
		String aTxt = "a";
		createFile(a, aTxt);
		gitlet("add", a);

		String b = TESTING_DIR + "b.txt";
		String bTxt = "b";
		createFile(b, bTxt);
		gitlet("add", b);
		String commitMsg1 = "added a & b";
		gitlet("commit", commitMsg1); // commit 1

		// BRANCH 1
		// create new branch containing a & b
		String branchName = "branch1";
		gitlet("branch", branchName);
		
		//MASTER
		//change a, remove b, & add c
		gitlet("rm", b);
		writeFile(a, "a'");
		gitlet("add", a);
		String c = TESTING_DIR + "c.txt";
		String cTxt = "c";
		createFile(c, cTxt);
		gitlet("add", c);

		String commitMsg2 = "changed a, removed b, added c";
		gitlet("commit", commitMsg2); // commit 2
		
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMsg2, commitMsg1, init },
				extractCommitMessages(logContent));
		assertEquals("a'", getText(a));
		
		//Checkout to BRANCH1 & add d
		gitlet("checkout", branchName); //contains a & b
		String d = TESTING_DIR + "d.txt";
		String dTxt = "d";
		createFile(d, dTxt);
		gitlet("add", d);
		String commitMsg3 = "added d";
		gitlet("commit", commitMsg3); // commit 3
		
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMsg3, commitMsg1, init },
				extractCommitMessages(logContent));
		
		
		
		//Check branch1 contents before rebase
		File aF = new File(a);
		File bF = new File(b);
		File cF = new File(c);
		File dF = new File(d);
		assertEquals("a", getText(a)); 	//branch 1 should have old copy
		assertTrue(bF.exists());		//b exists in branch 1
		
		//delete all files in workspace to see what we can checkout after rebase
		bF.delete();
		cF.delete();
		aF.delete();
		dF.delete();
		
		//REBASE branch1 to master
		gitlet("rebase", "master");
		logContent = gitlet("log");
		//System.out.println("LOG HERE");
		System.out.println(logContent);
		assertArrayEquals(new String[] { commitMsg3, commitMsg2, commitMsg1, init },
				extractCommitMessages(logContent));
		
		
		//Check branch1 contents after rebase
		assertEquals("a'", getText(a)); //updated file
		assertFalse(bF.exists());		//removed b
		assertTrue(cF.exists());		//added c
		assertTrue(dF.exists());		//d stayed
	}

	/**
	 * Test that resetting forward appropriately changes the output of log.
	 */
	@Test
	public void testResetForwardLog() {
		// add code here
		gitlet("init"); // commit 0
		String commitMessage1 = "initial commit";

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2); // commit 1

		String file2Name = TESTING_DIR + "file2.txt";
		String file2Text = "This is a new file.";
		createFile(file2Name, file2Text);
		gitlet("add", file2Name);
		String commitMessage3 = "added new file";
		gitlet("commit", commitMessage3); // commit 2

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));

		// reset backward
		gitlet("reset", "1");
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 }, extractCommitMessages(logContent));

		// reset forward
		gitlet("reset", "2");
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
	}

	/**
	 * Test resetting to commit in other branch.
	 */
	@Test
	public void testResetBranch() {
		// add code here
		gitlet("init"); // commit 0
		String init = "initial commit";

		// BRANCH 1
		// create new branch and checkout to new branch
		String branchName = "branch1";
		gitlet("branch", branchName);
		gitlet("checkout", branchName);

		// create new file and commit it to new branch
		String branchWugFN = TESTING_DIR + "branchwug.txt";
		String branchWugTXT = "This is branch1's wug";
		createFile(branchWugFN, branchWugTXT);
		gitlet("add", branchWugFN);
		String commitMsg1 = "added brach1's wug";
		gitlet("commit", commitMsg1); // commit 1

		// MASTER BRANCH
		gitlet("checkout", "master");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2); // commit 2

		String file2Name = TESTING_DIR + "file2.txt";
		String file2Text = "This is a new file.";
		createFile(file2Name, file2Text);
		gitlet("add", file2Name);
		String commitMessage3 = "added new file";
		gitlet("commit", commitMessage3); // commit 3

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2, init }, extractCommitMessages(logContent));

		gitlet("reset", "1"); // reset to commit from master branch to branch1
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMsg1, init }, extractCommitMessages(logContent));
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
			try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));) {
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
