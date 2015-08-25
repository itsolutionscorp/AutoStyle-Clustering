import static org.junit.Assert.*;

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
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class GitletTest {

	/**
	 * Class that provides JUnit tests for Gitlet, as well as a couple of
	 * utility methods.
	 *
	 * @author Andrew Hild (AH)
	 * @author Arshia Malkani (AX)
	 * @author Katie Jiang (BI)
	 * @author Nathaniel Low (AG)
	 * 
	 *         Some code adapted from StackOverflow and from whoever posted this
	 *         on cs61b website.
	 * 
	 *         http://stackoverflow.com/questions
	 *         /779519/delete-files-recursively-in-java
	 * 
	 *         http://stackoverflow.com/questions/326390/how-to-create-a-java-
	 *         string -from-the-contents-of-a-file
	 * 
	 *         http://stackoverflow.com/questions/1119385/junit-test-for-system-
	 *         out- println
	 * 
	 */

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

	/****************************************************************/
	/************************ END-TO-END TESTS ************************/
	/****************************************************************/

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
	 * from the previous commit.
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
	 * Tests that a file that has been committed at some point can be restored
	 * by checking it out from the commit it was committed at.
	 */
	@Test
	public void testRestoringCheckout() {

		gitlet("init");
		String wug = TESTING_DIR + "wug.txt";
		createFile(wug, "This is a wug.");
		gitlet("add", wug);
		gitlet("commit", "added wug");
		writeFile(wug, "This is a cute wug.");
		gitlet("add", wug);
		gitlet("commit", "made the wug cute");
		writeFile(wug, "This is an ugly wug.");
		gitlet("checkout", "1", wug);
		assertEquals("This is a wug.", getText(wug));
	}

	/**
	 * Test that a file that has been committed at some point can be restored by
	 * checking it out from a commit that tracks that version of the file, even
	 * if the file wasn't staged prior to that commit.
	 */
	@Test
	public void testRestoringCheckout2() {
		gitlet("init");
		String wug = TESTING_DIR + "wug.txt";
		createFile(wug, "This is a wug.");
		gitlet("add", wug);
		gitlet("commit", "added wug");
		String capybara = TESTING_DIR + "capybara.txt";
		writeFile(capybara, "This is a capybara.");
		gitlet("add", capybara);
		gitlet("commit", "added a capybara");
		writeFile(wug, "This is not a wug.");
		gitlet("checkout", "2", wug);
		assertEquals("This is a wug.", getText(wug));
	}

	/**
	 * Tests that you can checkout [id] [file] from an arbitrary commit in the
	 * graph (even one in another branch).
	 */
	@Test
	public void testBranchCheckout() {

		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);

		String branchName = "Branch1";
		gitlet("branch", branchName);

		gitlet("checkout", branchName);

		String wugText2 = "This is not a wug.";
		writeFile(wugFileName, wugText2);
		gitlet("add", wugFileName);
		String commitMessage3 = "added wug again";
		gitlet("commit", commitMessage3);

		gitlet("checkout", "master");

		String branchName2 = "Branch2";
		gitlet("branch", branchName2);

		gitlet("checkout", branchName2);

		String wugText3 = "This is THE SUPREME WUG LORD";
		writeFile(wugFileName, wugText3);
		gitlet("add", wugFileName);
		String commitMessage4 = "added the SUPREME WUG LORD";
		gitlet("commit", commitMessage4);

		gitlet("checkout", "master");
		assertEquals(wugText, getText(wugFileName));

		gitlet("checkout", branchName);
		assertEquals(wugText2, getText(wugFileName));

		gitlet("checkout", branchName2);
		assertEquals(wugText3, getText(wugFileName));
	}

	/**
	 * Tests that checkout works with the folders.
	 */
	@Test
	public void testCheckoutWithFolders() {
		File folder = new File(TESTING_DIR + "animal");
		folder.mkdirs();

		String wugFileName = TESTING_DIR + "animal/wug.txt";
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
	 * Tests that reset will change the version of the file based on the given
	 * commit we are resetting to. Also tests that resetting backward
	 * appropriately changes the output of log.
	 */
	@Test
	public void testResetLog() {
		gitlet("init");

		// 1
		String wug = TESTING_DIR + "wug.txt";
		createFile(wug, "This is a wug.");
		gitlet("add", wug);
		gitlet("commit", "added a wug");

		// 2
		writeFile(wug, "This is a hungry wug.");
		gitlet("add", wug);
		gitlet("commit", "wug wants food");

		// 3
		writeFile(wug, "This is a full wug.");
		gitlet("add", wug);
		gitlet("commit", "fed the wug");

		gitlet("reset", "1");
		String[] commitMessages = extractCommitMessages(gitlet("log"));
		assertTrue(commitMessages.length == 2);
		assertTrue(commitMessages[0].equals("added a wug"));
		assertTrue(commitMessages[1].equals("initial commit"));
	}

	/**
	 * Tests that reset will not change the version of a file if the file was
	 * marked for untracking in the commit we are resetting to.
	 */
	@Test
	public void testResetUntracked() {
		gitlet("init");

		// 1
		String wug = TESTING_DIR + "wug.txt";
		createFile(wug, "This is a wug.");
		String capybara = TESTING_DIR + "capybara.txt";
		createFile(capybara, "This is an annoying capybara.");
		gitlet("add", wug);
		gitlet("add", capybara);
		gitlet("commit", "added a wug and capybara");

		// 2
		writeFile(wug, "This is an enraged wug.");
		gitlet("add", wug);
		gitlet("rm", capybara);
		gitlet("commit", "enraged the wug, who then killed the capybara.");

		// 3
		writeFile(capybara, "This is a reincarnated capybara.");
		writeFile(wug, "This is a displeased wug.");
		gitlet("add", capybara);
		gitlet("add", capybara);
		gitlet("add", wug);
		gitlet("commit",
				"The capybara was brought to life again, much to the wug's displeasure.");

		gitlet("reset", "2");
		assertEquals("This is an enraged wug.", getText(wug));
		assertEquals("This is a reincarnated capybara.", getText(capybara));

		gitlet("reset", "1");
		assertEquals("This is a wug.", getText(wug));
		assertEquals("This is an annoying capybara.", getText(capybara));

		gitlet("reset", "3");
		assertEquals("This is a displeased wug.", getText(wug));
		assertEquals("This is a reincarnated capybara.", getText(capybara));
	}

	/**
	 * Tests that log adjusts appropriately when switching from one branch to
	 * another.
	 */
	@Test
	public void testBranchLog() {
		gitlet("init");
		String wug = TESTING_DIR + "wug.txt";

		writeFile(wug, "This is a wug");
		gitlet("add", wug);
		gitlet("commit", "This is a wug");

		gitlet("branch", "branch");

		writeFile(wug, "This is a wug.");
		gitlet("add", wug);
		gitlet("commit", "This is a wug.");

		String[] commitMessages = extractCommitMessages(gitlet("log"));
		assertTrue(commitMessages[0].equals("This is a wug."));
		assertTrue(commitMessages[1].equals("This is a wug"));
		assertTrue(commitMessages[2].equals("initial commit"));

		gitlet("checkout", "branch");

		writeFile(wug, "This is a wug!");
		gitlet("add", wug);
		gitlet("commit", "This is a wug!");

		commitMessages = extractCommitMessages(gitlet("log"));
		assertTrue(commitMessages[0].equals("This is a wug!"));
		assertTrue(commitMessages[1].equals("This is a wug"));
		assertTrue(commitMessages[2].equals("initial commit"));
	}

	/**
	 * Tests that merge works when new files are added.
	 */
	@Test
	public void testMergeWithNewFiles() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String commitMessage = "commit";

		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage);

		String branchName = "Branch1";
		gitlet("branch", branchName);

		gitlet("checkout", branchName);

		String wugText2 = "This is a THUG";
		writeFile(wugFileName, wugText2);
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage);

		gitlet("checkout", "master");

		String branchName2 = "Branch2";
		gitlet("branch", branchName2);

		gitlet("checkout", branchName2);

		String wugText3 = "This is a SUPREME WUG LORD";
		String wugFileName2 = TESTING_DIR + "thug.txt";
		createFile(wugFileName2, wugText3);
		gitlet("add", wugFileName2);
		gitlet("commit", commitMessage);
		assertEquals(wugText3, getText(wugFileName2));

		gitlet("checkout", branchName);
		gitlet("merge", branchName2);
		assertEquals(wugText3, getText(wugFileName2));
		assertEquals(wugText2, getText(wugFileName));

	}

	/**
	 * Tests that merging branches where the same file has been modified in each
	 * branch generates a .conflicted file with the contents of the file on the
	 * given branch
	 */
	@Test
	public void testConflictedMerge() {
		String wugFileName = "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "draenor");
		gitlet("checkout", "draenor");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "inverted wug");
		assertEquals("This is not a wug.", getText(wugFileName));
		gitlet("checkout", "master");
		assertEquals(wugText, getText(wugFileName));
		writeFile(wugFileName, "I want to be a wugLord");
		gitlet("add", wugFileName);
		gitlet("commit", "ambitious wug");
		assertEquals("I want to be a wugLord", getText(wugFileName));
		gitlet("merge", "draenor");
		File conflicted = new File(wugFileName + ".conflicted");
		assertTrue(conflicted.exists());
		System.out.println(getText(wugFileName));
		assertEquals("I want to be a wugLord", getText(wugFileName));
		assertEquals("This is not a wug.", getText(conflicted.getPath()));
	}

	/**
	 * Tests that basic, fast-forward merging with one file works as it should.
	 */
	@Test
	public void testFastForwardMerge() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "draenor");
		gitlet("checkout", "draenor");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "inverted wug");
		gitlet("checkout", "master");
		gitlet("merge", "draenor");
		assertEquals("This is not a wug.", getText(wugFileName));
	}

	/**
	 * Tests that merging branches where the version of the file in the head of
	 * the current branch is more updated than the incoming head
	 */
	@Test
	public void testPointlessMerge() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "draenor");
		gitlet("checkout", "draenor");
		gitlet("checkout", "master");
		assertEquals(wugText, getText(wugFileName));
		writeFile(wugFileName, "I want to be a wugLord");
		gitlet("add", wugFileName);
		gitlet("commit", "ambitious wug");
		assertEquals("I want to be a wugLord", getText(wugFileName));
		gitlet("merge", "draenor");
		File conflicted = new File(wugFileName + ".conflicted");
		assertTrue(!conflicted.exists());
		assertEquals("I want to be a wugLord", getText(wugFileName));
	}

	/**
	 * Tests that merge works if called twice in a row on the same branch.
	 */
	@Test
	public void testTwoMergesInARow() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String commitMessage = "commit";

		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage);

		String branchName = "Branch1";
		gitlet("branch", branchName);

		gitlet("checkout", branchName);

		String wugText2 = "This is a THUG";
		writeFile(wugFileName, wugText2);
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage);

		gitlet("checkout", "master");

		String branchName2 = "Branch2";
		gitlet("branch", branchName2);

		gitlet("checkout", branchName2);

		String wugText3 = "This is a SUPREME WUG LORD";
		String wugFileName2 = TESTING_DIR + "thug.txt";
		createFile(wugFileName2, wugText3);
		gitlet("add", wugFileName2);
		gitlet("commit", commitMessage);
		assertEquals(wugText3, getText(wugFileName2));

		gitlet("merge", branchName);
		assertEquals(wugText3, getText(wugFileName2));
		assertEquals(wugText2, getText(wugFileName));

		String wugText4 = "This is a SUPREME WUG LORD";
		createFile(wugFileName2, wugText4);
		gitlet("add", wugFileName2);
		gitlet("commit", commitMessage);
		assertEquals(wugText3, getText(wugFileName2));

		gitlet("checkout", branchName);
		gitlet("merge", branchName2);
		assertEquals(wugText4, getText(wugFileName2));
		assertEquals(wugText2, getText(wugFileName));

	}

	/**
	 * Tests that merge works with conflicted directories.
	 */
	@Test
	public void testConflictedMergeDirectories() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "draenor");
		gitlet("checkout", "draenor");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "inverted wug");
		assertEquals("This is not a wug.", getText(wugFileName));
		gitlet("checkout", "master");
		assertEquals(wugText, getText(wugFileName));
		writeFile(wugFileName, "I want to be a wugLord");
		gitlet("add", wugFileName);
		gitlet("commit", "ambitious wug");
		assertEquals("I want to be a wugLord", getText(wugFileName));
		gitlet("merge", "draenor");
		File conflicted = new File(wugFileName + ".conflicted");
		assertTrue(conflicted.exists());
		assertEquals("I want to be a wugLord", getText(wugFileName));
		assertEquals("This is not a wug.", getText(conflicted.getPath()));
	}

	/**
	 * Tests checkout error cases
	 */
	@Test
	public void testCheckoutErrorMessages() {
		gitlet("init");
		String wug = TESTING_DIR + "wug.txt";
		createFile(wug, "This is a wug");
		gitlet("add", wug);
		gitlet("commit", "added a wug");
		gitlet("branch", "wuglife");
		gitlet("checkout", "wuglife");
		gitlet("rm", wug);
		assertEquals("", gitlet("checkout", "master"));
	
	}
	
	
	/**
	 * Tests that methods work correctly after merge has been called.
	 */
	@Test
	public void testFunctionsAfterMerge() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "draenor");
		gitlet("checkout", "draenor");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "inverted wug");
		assertEquals("This is not a wug.", getText(wugFileName));
		gitlet("checkout", "master");
		assertEquals(wugText, getText(wugFileName));
		writeFile(wugFileName, "I want to be a wugLord");
		gitlet("add", wugFileName);
		gitlet("commit", "ambitious wug");
		assertEquals("I want to be a wugLord", getText(wugFileName));
		gitlet("merge", "draenor");
		File conflicted = new File(wugFileName + ".conflicted");
		assertTrue(conflicted.exists());
		assertEquals("I want to be a wugLord", getText(wugFileName));
		assertEquals("This is not a wug.", getText(conflicted.getPath()));

		// Should create 4 print statements
		gitlet("branch");
		gitlet("rm-branch");
		gitlet("merge");
		gitlet("rebase");

		gitlet("add", wugFileName);
		gitlet("commit", wugFileName);

		// Should not create 4 print statements
		gitlet("branch");
		gitlet("rm-branch");
		gitlet("merge");
		gitlet("rebase");

	}

	/**
	 * Tests that changes in the base branch propagate through the replayed
	 * branch during a rebase.
	 */
	@Test
	public void testRebase() {
		gitlet("init");

		String wug = TESTING_DIR + "wug.txt";
		createFile(wug, "This is a wug");
		gitlet("add", wug);
		String thug = TESTING_DIR + "thug.txt";
		createFile(thug, "This is a thug");
		gitlet("add", thug);
		gitlet("commit", "added a wug and thug");

		gitlet("branch", "wuglife");

		writeFile(thug, "This is a super cool thug");
		gitlet("add", thug);
		gitlet("commit", "made the thug super cool");

		gitlet("checkout", "wuglife");
		writeFile(wug, "This is a super cool wug");
		gitlet("add", wug);
		gitlet("commit", "made the wug super cool");

		gitlet("rebase", "master");

		assertEquals(getText(wug), "This is a super cool wug");
		assertEquals(getText(thug), "This is a super cool thug");

		gitlet("checkout", "1", wug);
		assertEquals(getText(wug), "This is a wug");
		gitlet("checkout", "3", thug);
		assertEquals(getText(thug), "This is a thug");

	}

	/**
	 * Tests that the output of log after a rebase includes the commit messages
	 * from both branches involved in the rebase.
	 */
	@Test
	public void testRebaseLog() {
		gitlet("init");

		String wug = TESTING_DIR + "wug.txt";
		createFile(wug, "This is a wg");
		gitlet("add", wug);
		gitlet("commit", "This is a wg");

		writeFile(wug, "This is a wug");
		gitlet("add", wug);
		gitlet("commit", "This is a wug");

		gitlet("branch", "branch");

		writeFile(wug, "This is a wug.");
		gitlet("add", wug);
		gitlet("commit", "This is a wug.");

		writeFile(wug, "This is a wug...");
		gitlet("add", wug);
		gitlet("commit", "This is a wug...");

		gitlet("checkout", "branch");

		writeFile(wug, "This is a wug!");
		gitlet("add", wug);
		gitlet("commit", "This is a wug!");

		writeFile(wug, "This is a wug!!!");

		gitlet("add", wug);
		gitlet("commit", "This is a wug!!!");

		gitlet("rebase", "master");

		String[] commitMessages = extractCommitMessages(gitlet("log"));
		assertTrue(commitMessages.length == 7);
		assertEquals("This is a wug!!!", commitMessages[0]);
		assertEquals("This is a wug!", commitMessages[1]);
		assertEquals("This is a wug...", commitMessages[2]);
		assertEquals("This is a wug.", commitMessages[3]);
		assertEquals("This is a wug", commitMessages[4]);
		assertEquals("This is a wg", commitMessages[5]);
		assertEquals("initial commit", commitMessages[6]);

		assertEquals(getText(wug), "This is a wug!!!");

	}

	/**
	 * Tests that merge and rebase work one after another.
	 */
	@Test
	public void testMergeRebase() {
		gitlet("init");

		String wug = TESTING_DIR + "wug.txt";
		writeFile(wug, "This is a wug");
		gitlet("add", wug);
		gitlet("commit", "This is a wug");

		gitlet("branch", "one");
		gitlet("branch", "two");

		String pangolin = TESTING_DIR + "pangolin.txt";
		writeFile(pangolin, "This is a pangolin");
		gitlet("add", pangolin);
		gitlet("commit", "This is a pangolin");

		gitlet("checkout", "one");

		writeFile(wug, "This is an improved wug");
		gitlet("add", wug);
		gitlet("commit", "This is an improved wug");

		gitlet("checkout", "master");
		gitlet("merge", "one");

		System.out.println(getText(wug));
		assertEquals(getText(wug), "This is an improved wug");

		gitlet("checkout", "two");

		String capybara = TESTING_DIR + "capybara.txt";
		writeFile(capybara, "This is a capybara");
		gitlet("add", capybara);
		gitlet("commit", "This is a capybara");

		gitlet("rebase", "master");

		System.out.println(getText(wug));
		assertEquals(getText(wug), "This is an improved wug");
	}
	
	@Test
	public void testRmAndCheckout2() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "This is a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("rm", wugFileName);
		String capybaraFileName = TESTING_DIR + "capybara.txt";
		createFile(capybaraFileName, "This is a capybara.");
		gitlet("add", capybaraFileName);
		gitlet("commit", "removed wug, added capybara");
		gitlet("checkout", "1", wugFileName);
		assertEquals("This is a wug.", getText(wugFileName));
		assertEquals("File does not exist in that commit.", gitlet("checkout", "2", wugFileName));
	}
	
	/****************************************************************/
	/************************** UNIT TESTS **************************/
	/****************************************************************/

	@Test
	public void testRmAndCheckout() {
		Gitlet.init();
		String wugFileName = TESTING_DIR + "wug.txt";
		File wug = createFile(wugFileName, "This is a wug.");
		Gitlet.add(wugFileName);
		Gitlet.commit("added a wug");
		String capybaraFileName = TESTING_DIR + "capybara.txt";
		File capybara = createFile(capybaraFileName, "This is a capybara.");
		Gitlet.rm(wug);
		Gitlet.add(capybaraFileName);
		Gitlet.checkout(1, wugFileName);
	}
	

	
	/**
	 * Tests that remove and add will remove or add files to the stagingArea and
	 * untracking ArrayLists appropriately.
	 */
	@Test
	public void testRemoveAndAdd() {
		Gitlet.init();
		String wugFileName = TESTING_DIR + "wug.txt";
		File wug = createFile(wugFileName, "This is a wug.");

		Gitlet.rm(wug);
		assertFalse(Gitlet.stagingArea.contains(wug));
		assertFalse(Gitlet.untracking.contains(wug));
		
		Gitlet.add(wugFileName);
		assertTrue(Gitlet.stagingArea.contains(wug));
		assertFalse(Gitlet.untracking.contains(wug));
		
		Gitlet.commit("added the wug");
		Gitlet.add(wugFileName);

		Gitlet.rm(wug);
		assertFalse(Gitlet.stagingArea.contains(wug));
		assertFalse(Gitlet.untracking.contains(wug));

		Gitlet.rm(wug);
		assertFalse(Gitlet.stagingArea.contains(wug));
		assertFalse(Gitlet.untracking.contains(wug));

		Gitlet.add(wugFileName);
		assertTrue(Gitlet.stagingArea.contains(wug));
		assertFalse(Gitlet.untracking.contains(wug));
	}
	
	/**
	 * Tests that commits are correctly added to the commitTree.
	 */
	@Test
	public void testCommitTree() {
		Gitlet.init();
		assertTrue(Gitlet.commitTree.size() == 1);
		String wugFileName = TESTING_DIR + "wug.txt";
		File wug = createFile(wugFileName, "This is a wug.");

		Gitlet.add(wugFileName);
		Gitlet.commit("added a wug");
		assertTrue(Gitlet.commitTree.size() == 2);
		Commit commit0 = Gitlet.commitTree.get(0);
		Commit commit1 = Gitlet.commitTree.get(1);
		assertTrue(commit0.getComment().equals("initial commit"));
		assertTrue(commit1.getComment().equals("added a wug"));	
		assertTrue(commit1.getParent().equals(commit0));
	}

	/**
	 * Tests that the branch head and split point are set correctly with branches
	 * and commits.
	 */
	@Test
	public void testBranchHead() {
		Gitlet.init();
		Commit commit0 = Gitlet.commitTree.get(0);
		assertTrue(Gitlet.currentBranch == "master");
		assertTrue(Gitlet.branches.get("master")[0].equals(commit0));
		assertTrue(Gitlet.branches.get("master")[1].equals(commit0));
		
		String wugFileName = TESTING_DIR + "wug.txt";
		File wug = createFile(wugFileName, "This is a wug.");
		
		Gitlet.add(wugFileName);
		Gitlet.commit("added a wug");
		Commit commit1 = Gitlet.commitTree.get(1);
		assertTrue(Gitlet.currentBranch == "master");
		assertTrue(Gitlet.branches.get("master")[0].equals(commit0));
		assertTrue(Gitlet.branches.get("master")[1].equals(commit1));
		
		Gitlet.branch("twig");

		writeFile(wugFileName, "This wug is bowing to its master.");
		Gitlet.add(wugFileName);
		Gitlet.commit("the wug worships its master");
		Commit commit2 = Gitlet.commitTree.get(2);
		assertTrue(Gitlet.currentBranch == "master");
		assertTrue(Gitlet.branches.get("master")[0].equals(commit0));
		assertTrue(Gitlet.branches.get("master")[1].equals(commit2));
		assertTrue(Gitlet.branches.get("twig")[0].equals(commit1));
		assertTrue(Gitlet.branches.get("twig")[1].equals(commit1));
		
		Gitlet.checkout("twig");
		writeFile(wugFileName, "This wug is hanging ominously from a tree.");
		Gitlet.add(wugFileName);
		Gitlet.commit("the wug is on a tree");
		Commit commit3 = Gitlet.commitTree.get(3);
		assertTrue(Gitlet.branches.get("master")[0].equals(commit0));
		assertTrue(Gitlet.branches.get("master")[1].equals(commit2));
		assertTrue(Gitlet.branches.get("twig")[0].equals(commit1));
		assertTrue(Gitlet.branches.get("twig")[1].equals(commit3));
	}

	/****************************************************************/
	/************************* STATIC METHODS *************************/
	/****************************************************************/

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
	private static File createFile(String fileName, String fileText) {
		File f = new File(fileName);
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		writeFile(fileName, fileText);
		return f;
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