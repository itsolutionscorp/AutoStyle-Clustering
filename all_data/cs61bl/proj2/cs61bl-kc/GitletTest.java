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
	 * Tests that remove will unstage files that had been staged. Involves 
	 * init, add, commit, and rm.
	 */
	@Test
	public void testBasicRemove() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		
		gitlet("add", wugFileName);
		File staged = new File(".gitlet/stagingArea/" + wugFileName);
		assertTrue(staged.exists());
		
		gitlet("commit", "added wug");
		File commited = new File(".gitlet/files/commit1/" + wugFileName);
		assertTrue(!staged.exists());
		assertTrue(commited.exists());
		
		gitlet("add", wugFileName);
		assertTrue(staged.exists());
		
		gitlet("rm", wugFileName);
		assertFalse(staged.exists());
		assertTrue(commited.exists());
	}
	
	/**
	 * Tests that find prints out the id of the commit that has a specific 
	 * commit message. If there are multiple ones, print all the ids in
	 * separate lines. Involves init, add, commit, and find.
	 */
	@Test
	public void testBasicFind() {
		gitlet("init");
		
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		
		String find1 = gitlet("find", "added wug");
		assertEquals("1", find1);
		
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "changed to not a wug");
		
		writeFile(wugFileName, "This is a wug!");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		
		String find2 = gitlet("find", "added wug");
		System.out.print("This should print 1 and 3: " + find2);
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
	
	/**
	 * Tests that rebase finds the split point of the current branch and the given
	 * branch and will snap off the current branch at the point. It will then
	 * reattach the current branch to the head of the given branch.
	 * Involves init, add, commit, and rebase.
	 */
	@Test
	public void testBasicRebase() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		
		String pugFileName = TESTING_DIR + "pug.txt";
		String pugText = "This is a pug.";
		createFile(pugFileName, pugText);
		gitlet("add", pugFileName);
		
		String commitMessage2 = "added wug and pug";
		gitlet("commit", commitMessage2);
		
		gitlet("branch", "branch1");

		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		String commitMessage3 = "added not wug";
		gitlet("commit", commitMessage3);
		
		gitlet("checkout", "branch1");
		
		writeFile(pugFileName, "This is not a pug.");
		gitlet("add", pugFileName);
		String commitMessage4 = "added not pug";
		gitlet("commit", commitMessage4);
		
		writeFile(pugFileName, "This is a mug.");
		gitlet("add", pugFileName);
		String commitMessage5 = "added mug";
		gitlet("commit", commitMessage5);
		
		writeFile(pugFileName, "This is a tug.");
		gitlet("add", pugFileName);
		String commitMessage6 = "added tug";
		gitlet("commit", commitMessage6);
		
		gitlet("rebase", "master");
		
		String logContent = gitlet("log");
		System.out.println("Should print out Commit 0, 1, 2, 6, 7, 8: " + logContent);
	}
	
	/**
	 * Tests that rm-branch deletes the branch with the specified name
	 * (deleting the pointer only). Involves init, add, commit, branch,
	 * and rm-branch.
	 */
	@Test
	public void testBasicRmBranch() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		
		gitlet("add", wugFileName);
		String commitMessage1 = "added wug";
		gitlet("commit", commitMessage1);
		
		gitlet("branch", "branch1");

		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		String commitMessage2 = "changed to not a wug";
		gitlet("commit", commitMessage2);
		
		gitlet("rm-branch", "branch1");
		
		String status = gitlet("status");
		System.out.println("There should only be *master branch in branches: ");
		System.out.println(status);
		
	}
	
	
	/**
	 * Tests that reset checks out all the files tracked by a specific
	 * commit. The current branch's head will also move to the commit node.
	 * Involves init, add, commit, branch,
	 * and rm-branch.
	 */
	@Test
	public void testBasicReset() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		
		gitlet("add", wugFileName);
		String commitMessage1 = "added wug";
		gitlet("commit", commitMessage1);
		

		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		String commitMessage2 = "changed to not a wug";
		gitlet("commit", commitMessage2);
		
		writeFile(wugFileName, "This is a wug!");
		gitlet("add", wugFileName);
		String commitMessage3 = "changed to wug again";
		gitlet("commit", commitMessage3);
		
		writeFile(wugFileName, "This is not a wug..");
		gitlet("add", wugFileName);
		String commitMessage4 = "changed to not a wug..";
		gitlet("commit", commitMessage4);
		
		gitlet("reset", "1");
		assertEquals(wugText, getText(wugFileName));
		
		gitlet("reset", "2");
		assertEquals("This is not a wug.", getText(wugFileName));
		
		gitlet("reset", "3");
		assertEquals("This is a wug!", getText(wugFileName));
		
		gitlet("reset", "4");
		assertEquals("This is not a wug..", getText(wugFileName));
		
	}
	
	/**
	 * Test that init will only create a gitlet version control system in the
	 * current directory only if there is not an existing system already.
	 * Involves init.
	 */
	@Test
	public void testInitFailure(){
		gitlet("init");
		String initalize = gitlet("init");
		assertEquals(initalize, "A gitlet version control system already exists in the current directory.");
	}
	
	/**
	 * Test that the file being added to the staging area need to exist in
	 * order for it to be added. Involves init, add.
	 */
	@Test
	public void testAddFailure() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		String failure = gitlet("add", wugFileName);
		assertEquals(failure, "File does not exist.");
	}
	
	/**
	 * Test that commit only happens if file has been staged and/or a valid
	 * commit message has been entered. Involves init, add, and commit.
	 */
	@Test
	public void testCommitFailure() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		
		String commitMessage2 = "added wug";
		String failure = gitlet("commit", commitMessage2);
		assertEquals(failure, "No changes added to the commit.");
		
		gitlet("add", wugFileName);
		String failure2 = gitlet("commit");
		assertEquals(failure2, "Please enter a commit message.");
		
		String failure3 = gitlet("commit", "");
		assertEquals(failure3, "Please enter a commit message.");
	}
	
	/**
	 * Test that remove only works when the file is staged or tracked by
	 * head commit. Involves init, add, commit, rm.
	 */
	@Test
	public void testRemoveFailure() {
		gitlet("init");
		
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
	
		String failure = gitlet("rm", wugFileName);
		assertEquals("No reason to remove the file.", failure);
		}
	
	
	/**
	 * Test that find only works when there is a commit with that message. 
	 * Involves init, add, commit, find.
	 */
	@Test
	public void testFindFailure() {
		gitlet("init");
		
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		
		String failure = gitlet("find", "added mug");
		assertEquals("Found no commit with that message.", failure);
		}
	
	/**
	 * Test that branch will only create a new branch if a 
	 * branch with that given name doesn't already exist.
	 * Involves init, add, commit, and branch.
	 */
	@Test
	public void testBranchFailure() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		
		gitlet("add", wugFileName);
		String commitMessage1 = "added wug";
		gitlet("commit", commitMessage1);
		
		gitlet("branch", "branch1");
		
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		String commitMessage2 = "changed to not a wug";
		gitlet("commit", commitMessage2);
		
		String failure = gitlet("branch", "branch1");
		assertEquals(failure, "A branch with that name already exists.");
		
	}
	
	
	/**
	 * Test that rm-branch only works when the given name exists and 
	 * the branch being removed is not the current one we are on.
	 * Involves init, add, commit, rm-branch and branch.
	 */
	@Test
	public void testRmBranchFailure() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		
		gitlet("add", wugFileName);
		String commitMessage1 = "added wug";
		gitlet("commit", commitMessage1);
		
		gitlet("branch", "branch1");

		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		String commitMessage2 = "changed to not a wug";
		gitlet("commit", commitMessage2);
		
		String failure = gitlet("rm-branch", "master");
		assertEquals(failure, "Cannot remove the current branch.");
		
		String failure2 = gitlet("rm-branch", "branch2");
		assertEquals(failure2, "A branch with that name does not exist.");
		
	}
	
	/**
	 * Test that merge will only merge files if a branch with the specific name
	 * does not already exist and the branch is not trying to merge with itself. 
	 * Involves init, add, commit, branch, checkout, merge.
	 */
	@Test
	public void testMergeFailure() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		
		gitlet("add", wugFileName);
		
		String bugFileName = TESTING_DIR + "bug.txt";
		String bugText = "This is a bug.";
		createFile(bugFileName, bugText);
		
		gitlet("add", bugFileName);

		String commitMessage1 = "added wug and bug";
		gitlet("commit", commitMessage1);
		
		gitlet("branch", "branch1");
		
		String failure1 = gitlet("merge", "branch2");
		assertEquals(failure1, "A branch with that name does not exist.");
		
		gitlet("checkout", "branch1");
		
		String failure2 = gitlet("merge", "branch1");
		assertEquals(failure2, "Cannot merge a branch with itself.");
		
	}
	
	/**
	 * Test that rebase will only split the point of the current branch and
	 * the given branch if the given branch's name is not the same as the
	 * current branch's name. Also rebase will only work if the input branch's 
	 * head is not in history of the current branch's head.
	 * Involves init, add, commit, branch, checkout, rebase.
	 */
	@Test
	public void testRebaseFailure1() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		
		gitlet("add", wugFileName);
		
		String bugFileName = TESTING_DIR + "bug.txt";
		String bugText = "This is a bug.";
		createFile(bugFileName, bugText);
		
		gitlet("add", bugFileName);
		
		String commitMessage1 = "added wug and bug";
		gitlet("commit", commitMessage1);
		
		gitlet("branch", "branch1");
		
		gitlet("checkout", "branch1");
		
		String failure1 = gitlet("rebase", "branch1");
		assertEquals(failure1, "Cannot rebase a branch onto itself.");
	}
	
	/**
	 * Test that rebase will only work if the input branch's head is not 
	 * in history of the current branch's head.
	 * Involves init, add, commit, branch, checkout, rebase.
	 */
	@Test
	public void testRebaseFailure2() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		
		gitlet("add", wugFileName);
		
		String bugFileName = TESTING_DIR + "bug.txt";
		String bugText = "This is a bug.";
		createFile(bugFileName, bugText);
		
		gitlet("add", bugFileName);
		
		String commitMessage1 = "added wug and bug";
		gitlet("commit", commitMessage1);
		
		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		
		writeFile(bugFileName, "This is not a bug.");
		gitlet("add", bugFileName);
		
		String commitMessage2 = "added not wug and bug";
		gitlet("commit", commitMessage2);
		
		String failure1 = gitlet("rebase", "master");
		assertEquals(failure1, "Already up-to-date.");
		
	}
	
	
	
	/**
	 * Test that a file that has been committed at some point can be restored 
	 * by checking it out from the commit it was committed at. Involves
	 * init, add, commit, checkout (id, fileName).
	 */
	@Test
	public void testCheckOut1() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "changed to not a wug");
		writeFile(wugFileName, "This is a wug again!");
		gitlet("checkout", "1", wugFileName);
		assertEquals("This is a wug.", getText(wugFileName));
	}
	
	/**
	 * Test that a file that has been committed at some point can be restored
	 * by checking it out from a commit that tracks that version of the file,
	 * even if the file wasn't staged prior to that commit. Involves init,
	 * add, commit, checkout (id, fileName).
	 */
	
	@Test
	public void testCheckOut2() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		
		writeFile(wugFileName, "This is not a wug.");
		gitlet("commit", "changed to not a wug");
		
		writeFile(wugFileName, "This is a wug again!");
		gitlet("checkout", "1", wugFileName);
		assertEquals("This is a wug.", getText(wugFileName));
	}
	
	/**
	 * Test that you can checkout with commit ID from an arbitrary 
	 * commit in the graph (even one in another branch). Also checks
	 * failure cases where no such branch exists and no need to 
	 * checkout current branch.
	 * Involves init, add, commit, checkout (id, fileName).
	 */
	@Test
	public void testCheckOut3() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
	
		String failure1 = gitlet("checkout", "master");
		assertEquals (failure1, "No need to checkout the current branch.");
		
		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "changed to not a wug");
		assertEquals("This is not a wug.", getText(wugFileName));
		
		String failure2 = gitlet("checkout", "branch2");
		assertEquals (failure2, "File does not exist in the most recent commit, or no such branch exists.");
		
		
		writeFile(wugFileName, "This is a wug again!");
		gitlet("add", wugFileName);
		gitlet("commit", "changed to wug again!");
		gitlet("checkout", "master");
		assertEquals(wugText, getText(wugFileName));
		
		gitlet("checkout", "3",  wugFileName);
		assertEquals("This is a wug again!", getText(wugFileName));
		
		gitlet("checkout", "2",  wugFileName);
		assertEquals("This is not a wug.", getText(wugFileName));
	}
	
	/**
	 * Test that resetting backward appropriately changes the output of log.
	 * Also checks failure case where no commit with that ID exists.
	 * Involves init, add, commit, log, global-log, and reset.
	 */
	@Test
	public void testResetBW() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);
		assertEquals(wugText, getText(wugFileName));
		
		String logContent = gitlet("log");
		assertArrayEquals(new String[] {commitMessage2, commitMessage1},
				extractCommitMessages(logContent));
		
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		String commitMessage3 = "changed to not a wug";
		gitlet("commit", commitMessage3);
		assertEquals("This is not a wug.", getText(wugFileName));
		logContent = gitlet("log");
		assertArrayEquals(new String[] {commitMessage3, commitMessage2, commitMessage1},
				extractCommitMessages(logContent));
		
		String failure = gitlet("reset", "3");
		assertEquals("No commit with that id exists.", failure);
		
		gitlet("reset", "2");
		assertEquals("This is not a wug.", getText(wugFileName));
		logContent = gitlet("log");
		assertArrayEquals(new String[] {commitMessage3, commitMessage2, commitMessage1},
				extractCommitMessages(logContent));
		
		gitlet("reset", "1");
		assertEquals(wugText, getText(wugFileName));
		logContent = gitlet("log");
		assertArrayEquals(new String[] {commitMessage2, commitMessage1},
				extractCommitMessages(logContent));
		
		gitlet("reset", "0");
		logContent = gitlet("log");
		assertArrayEquals(new String[] {commitMessage1},
				extractCommitMessages(logContent));
		
		logContent = gitlet("global-log");
		assertArrayEquals(new String[] {commitMessage1, commitMessage2, commitMessage3},
				extractCommitMessages(logContent));
		
		
	}
	
	/**
	 * Test that log adjusts appropriately when switching from one branch to another.
	 * Involves init, add, commit, log, branch.
	 */
	@Test
	public void testLogBranchSwitch() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);
		
		String logContent = gitlet("log");
		assertArrayEquals(new String[] {commitMessage2, commitMessage1},
				extractCommitMessages(logContent));
		
		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		String commitMessage3 = "changed to not a wug";
		gitlet("commit", commitMessage3);
		
		writeFile(wugFileName, "This is not a wug!");
		gitlet("add", wugFileName);
		String commitMessage4 = "changed to not a wug!";
		gitlet("commit", commitMessage4);
		
		logContent = gitlet("log");
		assertArrayEquals(new String[] {commitMessage4, commitMessage3, commitMessage2, commitMessage1},
				extractCommitMessages(logContent));
		
		gitlet("checkout", "master");
		logContent = gitlet("log");
		assertArrayEquals(new String[] {commitMessage2, commitMessage1},
				extractCommitMessages(logContent));
	}
	
	/**
	 * Test that merge will generate a .conflicted file if a file has been 
	 * modified in both branches since the split point. Involves init, add, 
	 * commit, checkout, merge, branch.
	 */
	@Test
	public void testMerge() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is wu";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added wu");
		
		wugText = "This is a wug";
		writeFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "branch");
		
		wugText = "This is a wug.";
		writeFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug.");
		gitlet("checkout", "branch");
		
		wugText = "This is a wug!";
		writeFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug!");

		gitlet("checkout", "master");
		gitlet("merge", "branch");
		
		String conflicted = wugFileName + ".conflicted";
		
		File con = new File(conflicted);
		assertTrue(con.exists());
		
		assertEquals("This is a wug.", getText(wugFileName));
		assertEquals("This is a wug!", getText(conflicted));
		
		
	}
	
	/**
	 * Test that merge will commit with files from the other branch if 
	 * those files had been modified in the other branch but not in the 
	 * current branch since the split point. Involves init, add, log,
	 * commit, checkout, merge, branch.
	 */
	@Test
	public void testMerge2() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String bugFileName = TESTING_DIR + "bug.txt";
		String bugText = "This is a bug.";
		createFile(bugFileName, bugText);
		
		gitlet("add", wugFileName);
		gitlet("add", bugFileName);
		String commitMessage2 = "added wug and bug";
		gitlet("commit", commitMessage2);
		
		gitlet("branch", "branch");
		gitlet("checkout", "branch");
		
		wugText = "This is not a wug.";
		writeFile(wugFileName, wugText);
		bugText = "This is not a bug.";
		writeFile(bugFileName, bugText);
		gitlet("add", wugFileName);
		gitlet("add", bugFileName);
		
		String commitMessage3 = "Merged master with branch.";
		gitlet("commit", commitMessage3);
		
		gitlet("checkout", "master");
		gitlet("merge", "branch");
		
		assertEquals("This is not a wug.", getText(wugFileName));
		assertEquals("This is not a bug.", getText(bugFileName));
		
		String logContent = gitlet("log");
		assertArrayEquals(new String[] {commitMessage3, commitMessage2, commitMessage1},
				extractCommitMessages(logContent));
	}
	
	/**
	 * Test that the output of log after a rebase includes the commit messages
	 * from both branches involved in the rebase & test that changes in the base branch propagate through the replayed 
	 * branch during a rebase. Also tests that the branch name exists. Involves init, add, 
	 * commit, log, branch, rebase.
	 */
	@Test
	public void testRebase() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);
		
		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		
		String failure3 = gitlet("rebase", "branch1");
		assertEquals(failure3, "Cannot rebase a branch onto itself.");
		
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		String commitMessage3 = "changed to not a wug";
		gitlet("commit", commitMessage3);
		
		String logContent = gitlet("log");
		assertArrayEquals(new String[] {commitMessage3, commitMessage2, commitMessage1},
				extractCommitMessages(logContent));
		
		String pugFileName = TESTING_DIR + "pug.txt";
		String pugText = "This is a pug.";
		createFile(pugFileName, pugText);
		gitlet("add", pugFileName);
		String commitMessage4 = "added pug";
		gitlet("commit", commitMessage4);
		String logContent2 = gitlet("log");
		assertArrayEquals(new String[] {commitMessage4, commitMessage3, commitMessage2, commitMessage1},
				extractCommitMessages(logContent2));
		
		String failure1 = gitlet("rebase", "master");
		assertEquals(failure1, "Already up-to-date.");
		
		gitlet("checkout", "master");
		String bugFileName = TESTING_DIR + "bug.txt";
		String bugText = "This is a bug.";
		createFile(bugFileName, bugText);
		gitlet("add", bugFileName);
		String commitMessage5 = "added bug";
		gitlet("commit", commitMessage5);
		
		String failure2 = gitlet("rebase", "branch2");
		assertEquals(failure2, "A branch with that name does not exist.");
		
		gitlet("rebase", "branch1");
		String logContent3 = gitlet("log");
				assertArrayEquals(new String[] {commitMessage4, commitMessage3, commitMessage2, commitMessage1},
						extractCommitMessages(logContent3));
		
		gitlet("checkout", "master");
		String logContent4 = gitlet("log");
		assertArrayEquals(new String[] {commitMessage5, commitMessage4, commitMessage3, commitMessage2, commitMessage1},
				extractCommitMessages(logContent4));
		
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