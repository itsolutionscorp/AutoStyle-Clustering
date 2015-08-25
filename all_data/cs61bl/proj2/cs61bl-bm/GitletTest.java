import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
		String logOutput = gitlet("log");
		assertArrayEquals(extractCommitMessages(logOutput),
				new String[] { "initial commit" });
		String result = gitlet("init");
		assertEquals(result, "A gitlet version control system "
				+ "already exists in the current directory.");
		assertArrayEquals(extractCommitMessages(gitlet("global-log")),
				new String[] { "initial commit" });
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
		gitlet("add", wugFileName);
		gitlet("commit", "added nothing");
		logContent = gitlet("log");
		assertArrayEquals(new String[] { "added nothing", commitMessage2,
				commitMessage1 }, extractCommitMessages(logContent));
		gitlet("reset", "1");
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
	}

	/**
	 * Test the basic functionality of staging a file, or unmarking it if it has
	 * been marked for untracking.
	 */
	@Test
	public void testAddCommand() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		assertEquals(gitlet("add", "dog.txt"), "File does not exist.");
		assertEquals(gitlet("add", "wug.txt"), "File does not exist.");
		gitlet("add", wugFileName);
		writeFile(wugFileName, "This was a wug.");
		assertEquals(gitlet("checkout", wugFileName),
				"File does not exist in the most recent "
						+ "commit, or no such branch exists.");
		gitlet("commit", "hello");
		gitlet("checkout", wugFileName);
		assertEquals(getText(wugFileName), "This is a wug.");
		writeFile(wugFileName, "This could have been a wug.");
		gitlet("rm", wugFileName);
		gitlet("add", wugFileName);
		assertEquals(gitlet("commit", "error"),
				"No changes added to the commit.");
	}

	/**
	 * Checks the commit error messages. Also checks that committing and
	 * checking out works properly.
	 */
	@Test
	public void testCommitCommand() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added");
		assertEquals(gitlet("commit", "add this"),
				"No changes added to the commit.");
		writeFile(wugFileName, "This ain't no wug.");
		gitlet("add", wugFileName);
		writeFile(wugFileName, "changes");
		gitlet("commit", "ain't");
		gitlet("checkout", wugFileName);
		assertEquals(getText(wugFileName), "This ain't no wug.");
		gitlet("rm", wugFileName);
		writeFile(wugFileName, "This was a wug.");
		gitlet("commit", "blah blah");
		assertEquals(gitlet("checkout", wugFileName),
				"File does not exist in the most recent "
						+ "commit, or no such branch exists.");
		String output = gitlet("log");
		assertArrayEquals(extractCommitMessages(output), new String[] {
				"blah blah", "ain't", "added", "initial commit" });
	}

	/**
	 * Checking basic rm functionality in several scenarios.
	 */
	@Test
	public void testRmCommand() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("rm", wugFileName);
		String commitResult = "No changes added to the commit.";
		assertEquals(gitlet("commit", "hello"), commitResult);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("checkout", wugFileName);
		assertEquals(wugText, getText(wugFileName));
		gitlet("rm", wugFileName);
		gitlet("commit", "removed wug");
		String result = gitlet("checkout", wugFileName);
		assertEquals(result,
				"File does not exist in the most recent commit, or no such branch exists.");
		assertEquals(gitlet("rm", wugFileName), "No reason to remove the file.");
	}

	/**
	 * Ensure that global log captures all commits, not just the ones in the
	 * history of the current commit.
	 */
	@Test
	public void testGlobalLog() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		String globalLogContent = gitlet("global-log");
		assertArrayEquals(new String[] { commitMessage1 },
				extractCommitMessages(globalLogContent));
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);
		String commitMessage3 = "added flug";
		String wugText2 = "This is a wug-flug.";
		createFile(wugFileName, wugText2);
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage3);
		globalLogContent = gitlet("global-log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1,
				commitMessage3 }, extractCommitMessages(globalLogContent));
		String dogFileName = TESTING_DIR + "dog.txt";
		String dogText = "This is a dog.";
		createFile(dogFileName, dogText);
		gitlet("reset", "1");
		gitlet("add", dogFileName);
		gitlet("commit", "added dog");
		globalLogContent = gitlet("global-log");
		assertEquals(extractCommitMessages(globalLogContent).length, 4);
		gitlet("reset", "1");
		globalLogContent = gitlet("global-log");
		assertEquals(extractCommitMessages(globalLogContent).length, 4);
	}

	/**
	 * Test that the error messages come up when appropriate. Also makes sure to
	 * list all the commits with the same message.
	 */
	@Test
	public void testFind() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "initial commit");
		writeFile(wugFileName, "Wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "initial commit");
		String findContent = gitlet("find", "initial commit");
		assertEquals(findContent, "0" + "\r\n" + "1" + "\r\n" + "2");
		assertEquals(gitlet("find", "panda"),
				"Found no commit with that message.");
	}

	/**
	 * Basically just a visual test.
	 */
	@Test
	public void testStatus() {
		gitlet("init");
		gitlet("status");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("status");
		gitlet("commit", "clear stage");
		gitlet("status");
		gitlet("rm", wugFileName);
		gitlet("status");
		gitlet("branch", "apprentice");
		gitlet("status");
		gitlet("checkout", "apprentice");
		gitlet("status");
	}

	/**
	 * Make sure we can checkout past commits as well as "future" commits,
	 * further down the commit tree. Access commits using ID.
	 */
	@Test
	public void testCheckoutIDCommand() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "added not");
		writeFile(wugFileName, "This is definitely not a wug.");
		gitlet("checkout", "1", wugFileName);
		assertEquals(wugText, getText(wugFileName));
		gitlet("checkout", "2", wugFileName);
		assertEquals("This is not a wug.", getText(wugFileName));
	}

	/**
	 * Basic functionality of checking out a file from the current commit.
	 * Should overwrite files if needed.
	 */
	@Test
	public void testCheckoutFileCommand() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		assertEquals(gitlet("checkout", wugFileName),
				"File does not exist in the most recent commit, or no such branch exists.");
		gitlet("add", wugFileName);
		assertEquals(gitlet("checkout", wugFileName),
				"File does not exist in the most recent commit, or no such branch exists.");
		gitlet("commit", "hello");
		writeFile(wugFileName, "No more wugs.");
		gitlet("checkout", wugFileName);
		assertEquals(getText(wugFileName), "This is a wug.");
	}

	/**
	 * Test that a file that has been committed at some point can be restored by
	 * checking it out from a commit that tracks that version of the file, even
	 * if the file wasn't staged prior to that commit.
	 */
	@Test
	public void testInheritFiles() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		String dogFileName = TESTING_DIR + "dog.txt";
		String dogText = "This is a dog.";
		createFile(dogFileName, dogText);
		gitlet("add", dogFileName);
		gitlet("commit", "added dog");
		writeFile(dogFileName, "This is a distraction.");
		gitlet("add", dogFileName);
		gitlet("commit", "distract");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", wugFileName);
		assertEquals(wugText, getText(wugFileName));
	}

	/**
	 * Test that you can checkout [id] [file] from an arbitrary commit in the
	 * graph (even one in another branch).
	 */
	@Test
	public void testCheckoutArbitraryBranch() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "apprentice");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This could be a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "potential");
		gitlet("checkout", "apprentice");
		writeFile(wugFileName, "This is actually a dog.");
		gitlet("add", wugFileName);
		gitlet("commit", "revealed dog");
		gitlet("checkout", "2", wugFileName);
		assertEquals(getText(wugFileName), "This is not a wug.");
		gitlet("checkout", "1", wugFileName);
		assertEquals(getText(wugFileName), wugText);
		gitlet("checkout", "3", wugFileName);
		assertEquals(getText(wugFileName), "This could be a wug.");
	}

	/**
	 * Test that the commitIDCommand works correctly. A follow-up to the first
	 * commitIDCommand test.
	 */
	@Test
	public void testCheckoutIDCommand2() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", wugFileName);
		writeFile(wugFileName, "Is this a wug?");
		assertEquals(gitlet("checkout", "2", wugFileName),
				"No commit with that id exists.");
		gitlet("checkout", "1", wugFileName);
		assertEquals(getText(wugFileName), "This is a wug.");
		assertEquals(gitlet("checkout", "1", "test.java"),
				"File does not exist in that commit.");
	}

	/**
	 * Again, testing that any branch can be checked out if it exists.
	 */
	@Test
	public void testCheckoutBranchCommand() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String dogFileName = TESTING_DIR + "dog.txt";
		String dogText = "This is a dog.";
		createFile(dogFileName, dogText);
		gitlet("add", wugFileName);
		gitlet("add", dogFileName);
		gitlet("commit", "first");
		gitlet("branch", "apprentice");
		writeFile(wugFileName, "A wug, this is.");
		writeFile(dogFileName, "This was a dog.");
		gitlet("add", wugFileName);
		gitlet("add", dogFileName);
		gitlet("commit", "second");
		gitlet("checkout", "apprentice");
		writeFile(wugFileName, "This can be a wug.");
		writeFile(dogFileName, "Woof.");
		gitlet("add", wugFileName);
		gitlet("add", dogFileName);
		gitlet("commit", "third");
		writeFile(wugFileName, "I want a wug.");
		writeFile(dogFileName, "I REALLy want a dog.");
		gitlet("checkout", "master");
		assertEquals(getText(wugFileName), "A wug, this is.");
		assertEquals(getText(dogFileName), "This was a dog.");
		assertEquals(gitlet("checkout", "yoda"),
				"File does not exist in the most recent commit, or no such branch exists.");
		assertEquals(gitlet("checkout", "master"),
				"No need to checkout the current branch.");
	}

	/**
	 * Another visual test.
	 */
	@Test
	public void testBranch() {
		gitlet("init");
		assertEquals(gitlet("branch", "master"),
				"A branch with that name already exists.");
		gitlet("status");
		gitlet("branch", "apprentice");
		assertEquals(gitlet("branch", "apprentice"),
				"A branch with that name already exists.");
		gitlet("status");
		gitlet("branch", "pawn");
		gitlet("status");
		gitlet("checkout", "apprentice");
		gitlet("status");
	}

	/**
	 * Test that a branch can be removed correctly. A visual test.
	 */
	@Test
	public void testRmBranch() {
		gitlet("init");
		assertEquals(gitlet("rm-branch", "master"),
				"Cannot remove the current branch.");
		gitlet("branch", "apprentice");
		gitlet("status");
		assertEquals(gitlet("rm-branch", "master"),
				"Cannot remove the current branch.");
		gitlet("checkout", "apprentice");
		gitlet("status");
		gitlet("rm-branch", "master");
		gitlet("status");
		assertEquals(gitlet("rm-branch", "master"),
				"A branch with that name does not exist.");
		assertEquals(gitlet("rm-branch", "wug"),
				"A branch with that name does not exist.");
	}

	/**
	 * Test that resetting backward appropriately changes the output of log.
	 */
	@Test
	public void testResetAndLog() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "A wug, this is.");
		gitlet("add", wugFileName);
		gitlet("commit", "yoda wug");
		writeFile(wugFileName, "Do ya feel wuggy, punk?");
		gitlet("add", wugFileName);
		gitlet("commit", "eastwood wug");
		String logContent = gitlet("log");
		String commitMessage1 = "initial commit";
		String commitMessage2 = "added wug";
		String commitMessage3 = "yoda wug";
		String commitMessage4 = "eastwood wug";
		assertArrayEquals(extractCommitMessages(logContent),
				new String[] { commitMessage4, commitMessage3, commitMessage2,
						commitMessage1 });
		gitlet("reset", "2");
		logContent = gitlet("log");
		assertArrayEquals(extractCommitMessages(logContent), new String[] {
				commitMessage3, commitMessage2, commitMessage1 });
		gitlet("reset", "1");
		assertEquals(getText(wugFileName), "This is a wug.");
		assertArrayEquals(extractCommitMessages(gitlet("log")), new String[] {
				commitMessage2, commitMessage1 });
	}

	/**
	 * Test that log adjusts appropriately when switching from one branch to
	 * another.
	 */
	@Test
	public void testResetCommand() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This was a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "added past tense");
		gitlet("add", wugFileName);
		gitlet("reset", "1");
		gitlet("status");
		gitlet("branch", "apprentice");
		gitlet("branch", "pawn");
		gitlet("checkout", "apprentice");
		writeFile(wugFileName, "Blah blah.");
		gitlet("add", wugFileName);
		gitlet("commit", "blah blah");
		gitlet("reset", "1");
		assertEquals(getText(wugFileName), "This is a wug.");
		writeFile(wugFileName, "bleh bleh");
		gitlet("add", wugFileName);
		gitlet("commit", "bleh bleh");
		gitlet("reset", "2");
		assertArrayEquals(extractCommitMessages(gitlet("log")), new String[] {
				"added past tense", "added wug", "initial commit" });
		gitlet("reset", "3");
		assertArrayEquals(extractCommitMessages(gitlet("log")), new String[] {
				"blah blah", "added wug", "initial commit" });
		gitlet("reset", "4");
		assertArrayEquals(extractCommitMessages(gitlet("log")), new String[] {
				"bleh bleh", "added wug", "initial commit" });
		assertEquals(gitlet("reset", "5"), "No commit with that id exists.");
	}

	/**
	 * Test that merge will generate a .conflicted file if a file has been
	 * modified in both branches since the split point.
	 */
	@Test
	public void testMergeConflict() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String dogFileName = TESTING_DIR + "dog.txt";
		String wugText = "This is a wug.";
		String dogText = "This is a dog.";
		createFile(wugFileName, wugText);
		createFile(dogFileName, dogText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "apprentice");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "added not");
		gitlet("add", dogFileName);
		gitlet("commit", "added dog");
		gitlet("checkout", "apprentice");
		writeFile(wugFileName, "This is probably a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "added probably");
		String mergeResult = gitlet("merge", "master");
		String expected = "Encountered a merge conflict.";
		assertEquals(expected, mergeResult);
		File conflicted = new File(wugFileName + ".conflicted");
		assertTrue(conflicted.exists());
		gitlet("add", dogFileName);
		gitlet("commit", "added dog");
		gitlet("log");
	}

	@Test
	public void testMergeConflict2() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String dogFileName = TESTING_DIR + "dog.txt";
		String wugText = "This is a wug.";
		String dogText = "This is a dog.";
		createFile(wugFileName, wugText);
		createFile(dogFileName, dogText);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "kaetyng");
		gitlet("add", dogFileName);
		gitlet("commit", "added dog");
		gitlet("checkout", "kaetyng");
		writeFile(wugFileName, "I hate wugs");
		gitlet("add", wugFileName);
		gitlet("commit", "hatred");
		gitlet("log");
		gitlet("merge", "master");
		gitlet("checkout", dogFileName);
		assertEquals(getText(dogFileName), "This is a dog.");
		assertEquals(gitlet("merge", "master"), "Encountered a merge conflict.");
		assertTrue(new File(dogFileName + ".conflicted").exists());
	}

	/**
	 * Test that merge will commit with files from the other branch if those
	 * files had been modified in the other branch but not in the current branch
	 * since the split point.
	 */
	@Test
	public void testMerge() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String mergeMessage = "Merged apprentice with master.";
		String commitMessage1 = "initial commit";
		String commitMessage2 = "added wug";
		String commitMessage4 = "added nothing";
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "apprentice");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "added not");
		gitlet("checkout", "apprentice");
		gitlet("add", wugFileName);
		gitlet("commit", "added nothing");
		gitlet("merge", "master");
		String logContent = gitlet("log");
		assertArrayEquals(extractCommitMessages(logContent), new String[] {
				mergeMessage, commitMessage4, commitMessage2, commitMessage1 });
		writeFile(wugFileName, "This should be overwritten.");
		gitlet("checkout", wugFileName);
		assertEquals(getText(wugFileName), "This is not a wug.");
	}

	/**
	 * Test that merge doesn't make a conflicted state if one branch is missing
	 * the file and the other has a modified version.
	 */
	@Test
	public void testMerge2() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "apprentice");
		writeFile(wugFileName, "This was a guw.");
		gitlet("add", wugFileName);
		gitlet("commit", "added confusion");
		gitlet("checkout", "apprentice");
		gitlet("rm", wugFileName);
		gitlet("commit", "removed wug");
		String output = gitlet("merge", "master");
		assertEquals(output, "");
		assertTrue(!new File(wugFileName + ".conflicted").exists());
		gitlet("add", "test.java");
		gitlet("commit", "did nothing");
		writeFile(wugFileName, "blah blah");
		gitlet("checkout", wugFileName);
		assertEquals(getText(wugFileName), "This was a guw.");
	}

	/**
	 * Test proper handling when both branches are missing a file from the split
	 * point.
	 */
	@Test
	public void testMerge3() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String testFileName = TESTING_DIR + "dog.txt";
		String testText = "ha ha ha";
		createFile(testFileName, testText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "hello");
		gitlet("rm", wugFileName);
		gitlet("commit", "removed");
		gitlet("reset", "1");
		gitlet("rm", wugFileName);
		gitlet("commit", "removed");
		gitlet("branch", "apprentice");
		gitlet("reset", "2");
		assertEquals(gitlet("merge", "apprentice"),
				"No changes added to the commit.");
		gitlet("add", testFileName);
		gitlet("merge", "apprentice");
		assertArrayEquals(extractCommitMessages(gitlet("log")), new String[] {
				"Merged master with apprentice.", "removed", "hello",
				"initial commit" });
		gitlet("branch", "pawn");
		writeFile(testFileName, "ha ha ha");
		gitlet("add", testFileName);
		gitlet("commit", "added ha's");
		gitlet("checkout", "pawn");
		gitlet("rm", testFileName);
		gitlet("commit", "no laughs");
		assertEquals(gitlet("merge", "master"),
				"No changes added to the commit.");
	}

	/**
	 * Test that when a file is removed in the current branch and unmodified in
	 * the given branch, no overwriting is done.
	 */
	@Test
	public void testMerge4() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String testFileName = TESTING_DIR + "dog.txt";
		String testText = "ha ha ha";
		createFile(testFileName, testText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added test");
		gitlet("branch", "apprentice");
		gitlet("add", testFileName);
		gitlet("commit", "added more ha's");
		gitlet("checkout", "apprentice");
		gitlet("rm", wugFileName);
		gitlet("commit", "removed ha");
		gitlet("checkout", "master");
		gitlet("merge", "apprentice");
		assertArrayEquals(extractCommitMessages(gitlet("log")), new String[] {
				"Merged master with apprentice.", "added more ha's",
				"added test", "initial commit" });
		assertEquals(gitlet("checkout", wugFileName),
				"File does not exist in the most recent commit, or no such branch exists.");
	}

	/**
	 * More merge functionality. Again test the effect of a missing file.
	 */
	@Test
	public void testMerge5() {
		gitlet("init");
		String testFileName = TESTING_DIR + "dog.txt";
		String testText = "ha ha ha";
		createFile(testFileName, testText);
		gitlet("add", testFileName);
		gitlet("commit", "added ha");
		gitlet("branch", "apprentice");
		writeFile(testFileName, "more ha");
		gitlet("add", testFileName);
		gitlet("commit", "made a change");
		gitlet("checkout", "apprentice");
		gitlet("add", testFileName);
		gitlet("commit", "added nothing");
		gitlet("rm", testFileName);
		gitlet("merge", "master");
		gitlet("checkout", testFileName);
		assertEquals(getText(testFileName), "more ha");
	}

	/**
	 * Continued merge functionality.
	 */
	@Test
	public void testMerge6() {
		gitlet("init");
		String testFileName = TESTING_DIR + "dog.txt";
		String testText = "ha ha ha";
		createFile(testFileName, testText);
		gitlet("add", testFileName);
		gitlet("commit", "added ha");
		gitlet("branch", "apprentice");
		writeFile(testFileName, "hahblah blah");
		gitlet("add", testFileName);
		gitlet("commit", "changed");
		gitlet("checkout", "apprentice");
		gitlet("rm", testFileName);
		gitlet("commit", "removed");
		gitlet("merge", "master");
		gitlet("checkout", testFileName);
		assertEquals(getText(testFileName), "hahblah blah");
	}

	/**
	 * Test that the output of log after a rebase includes the commit messages
	 * from both branches involved in the rebase.
	 */
	@Test
	public void testRebase() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String commitMessage1 = "initial commit";
		String commitMessage2 = "added wug";
		String commitMessage3 = "added not";
		String commitMessage4 = "added nothing";
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "apprentice");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "added not");
		gitlet("checkout", "apprentice");
		gitlet("add", wugFileName);
		gitlet("commit", "added nothing");
		gitlet("add", wugFileName);
		String commitMessage5 = "added nothing again";
		gitlet("commit", commitMessage5);
		writeFile(wugFileName, "That was a wug.");
		gitlet("add", wugFileName);
		String commitMessage6 = "added past tense";
		gitlet("commit", commitMessage6);
		gitlet("rebase", "master");
		String logContent = gitlet("log");
		assertArrayEquals(extractCommitMessages(logContent), new String[] {
				commitMessage6, commitMessage5, commitMessage4, commitMessage3,
				commitMessage2, commitMessage1 });
		writeFile(wugFileName, "Please be overwritten.");
		gitlet("checkout", "7", wugFileName);
		assertEquals(getText(wugFileName), "This is not a wug.");
		gitlet("checkout", "8", wugFileName);
		assertEquals(getText(wugFileName), "That was a wug.");
	}

	/**
	 * Test that removal is considered a modification. Thus, a removal should be
	 * able to propagate.
	 */
	@Test
	public void testRebase2() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "Hello");
		gitlet("branch", "apprentice");
		gitlet("rm", wugFileName);
		gitlet("commit", "removed");
		gitlet("checkout", "apprentice");
		gitlet("add", wugFileName);
		gitlet("commit", "did nothing");
		gitlet("rebase", "master");
		assertArrayEquals(extractCommitMessages(gitlet("log")), new String[] {
				"did nothing", "removed", "Hello", "initial commit" });
		assertEquals(gitlet("checkout", wugFileName),
				"File does not exist in the most recent commit, or no such branch exists.");
		gitlet("status");
		gitlet("checkout", "master");
	}

	/**
	 * Test correct propagation when no files are removed. Propagating should
	 * stop when both branches have modified files.
	 */
	@Test
	public void testRebase3() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("branch", "apprentice");
		writeFile(wugFileName, "changes");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("checkout", "apprentice");
		writeFile(wugFileName, "This is a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "nothing changed");
		gitlet("rebase", "master");
		writeFile(wugFileName, "doesn't matter");
		gitlet("checkout", wugFileName);
		assertEquals(getText(wugFileName), "This is a wug.");
	}

	/**
	 * Test that a modified file stops propagating once it meets a modified file
	 * in the replayed branch. That means it doesn't overwrite the file in the
	 * commit following the modification, even if that file version is
	 * unmodified. Basically, this test ensures that modified files in the
	 * replayed branch serve as "blockades" against further propagation which
	 * would otherwise be appropriate.
	 */
	@Test
	public void testRebase4() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "hello");
		gitlet("branch", "apprentice");
		writeFile(wugFileName, "changes");
		gitlet("add", wugFileName);
		gitlet("commit", "changed");
		gitlet("checkout", "apprentice");
		gitlet("add", wugFileName);
		gitlet("commit", "no change");
		writeFile(wugFileName, "Hello");
		gitlet("add", wugFileName);
		gitlet("commit", "wug says hi");
		writeFile(wugFileName, "This is a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "reverted");
		gitlet("rebase", "master");
		writeFile(wugFileName, "all these changes");
		gitlet("checkout", wugFileName);
		assertEquals(getText(wugFileName), "This is a wug.");
	}

	/**
	 * Tests that a file marked for untracking can also serve as a blockade
	 * against propagation.
	 */
	@Test
	public void testRebase5() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "hello");
		gitlet("branch", "apprentice");
		gitlet("rm", wugFileName);
		gitlet("commit", "erased");
		gitlet("checkout", "apprentice");
		gitlet("add", wugFileName);
		gitlet("commit", "changed nothing");
		gitlet("rm", wugFileName);
		gitlet("commit", "erased too");
		gitlet("add", wugFileName);
		gitlet("commit", "brought it back.");
		gitlet("rebase", "master");
		gitlet("log");
		writeFile(wugFileName, "blah blah");
		gitlet("checkout", wugFileName);
		assertEquals(getText(wugFileName), "This is a wug.");
	}

	/**
	 * Test that changes in the base branch propagate through the replayed
	 * branch during a rebase.
	 */
	@Test
	public void testPropagate() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", wugFileName);
		gitlet("branch", "apprentice");
		writeFile(wugFileName, "propagate");
		gitlet("add", wugFileName);
		gitlet("commit", "propagate");
		gitlet("checkout", "apprentice");
		gitlet("add", wugFileName);
		gitlet("commit", "nothing");
		gitlet("add", wugFileName);
		gitlet("commit", "nothing again");
		writeFile(wugFileName, "will not change");
		gitlet("add", wugFileName);
		gitlet("commit", "unchanged");
		gitlet("rebase", "master");
		writeFile(wugFileName, "nonsense");
		gitlet("checkout", wugFileName);
		assertEquals(getText(wugFileName), "will not change");
		gitlet("reset", "7");
		gitlet("checkout", wugFileName);
		assertEquals(getText(wugFileName), "propagate");
		gitlet("reset", "6");
		gitlet("checkout", wugFileName);
		assertEquals(getText(wugFileName), "propagate");
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