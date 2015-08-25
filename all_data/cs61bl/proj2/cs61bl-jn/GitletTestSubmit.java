import static org.junit.Assert.*;

import org.junit.Test;
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

public class GitletTestSubmit {

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

	private static String getFirstId(String message) {
		try {
			String content = gitlet("find", message);
			return content.split(LINE_SEPARATOR)[0];
		} catch (Exception e) {
			return "";
		}
	}

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

	// TEST ON THE SPEC, END TO END TEST

	/**
	 * Test that a file that has been committed at some point can be restored by
	 * checking it out from the commit it was committed at.
	 */
	@Test
	public void testOldCheckoutOldCommit() {

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String wugsFileName = TESTING_DIR + "wugs.txt";
		createFile(wugsFileName, "There are two wugs.");
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugsFileName);
		String wugsMessage = "added wugs";
		gitlet("commit", wugsMessage);
		writeFile(wugFileName, "This is definitely not a wug.");
		String commitId = getFirstId(wugsMessage);
		gitlet("checkout", commitId, wugFileName);
		assertEquals(wugText, getText(wugFileName));
	}

	/**
	 * Test that a file that has been committed at some point can be restored by
	 * checking it out from a commit that tracks that version of the file, even
	 * if the file wasn't staged prior to that commit.
	 */
	@Test
	public void testUnstage() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String wugsFileName = TESTING_DIR + "wugs.txt";
		createFile(wugsFileName, "There are two wugs.");
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("add", wugsFileName);
		gitlet("rm", wugFileName);
		gitlet("commit", "added wugs but not wug");
		gitlet("checkout", wugFileName);
		assertEquals(wugText, getText(wugFileName));
	}
	
	@Test
	/**
	 * Basic initialize
	 */
		public void testInitialize() {
			gitlet("init");
			File file = new File(GITLET_DIR);
			assertTrue(file.exists());
		}
	/**
	 * Basic add
	 */
	@Test
		public void add(){
			String file = TESTING_DIR + "file.txt";
			String fileText = "file";
			createFile(file, fileText);
			gitlet("init");
			gitlet("add", file);
			File f = new File(GITLET_DIR+"stage/"+ file);
			assertTrue(f.exists());
		}
	@Test
	/**
	 * Testing that add just removes tracking and not added to stage
	 */
		public void addAndRemove(){
			String file = TESTING_DIR + "file.txt";
			String fileText = "file";
			createFile(file, fileText);
			gitlet("init");
			gitlet("add", file);
			gitlet("commit", "test");
			gitlet("rm", file);
			gitlet("add", file);
			File f = new File(GITLET_DIR+"stage/"+ file);
			assertFalse(f.exists());
		}
	/**
	 * Add failure cases
	 */
	@Test
		public void addFailureCase(){
			String file = TESTING_DIR + "file.txt";
			String fileText = "file";
			createFile(file, fileText);
			gitlet("init");
			String errormsg=gitlet("add", "hi"+file);
			assertEquals("File does not exist.",errormsg);
			File f = new File(GITLET_DIR+"stage/hi"+ file);
			assertFalse(f.exists());
		}
		
		@Test
		/**
		 * basic commit test
		 */
		public void commit(){
			String file = TESTING_DIR + "file.txt";
			String fileText = "file";
			String file2 = TESTING_DIR + "file2.txt";
			String file2Text = "file2";
			createFile(file, fileText);
			createFile(file2, file2Text);
			gitlet("init");
			String befour=gitlet("status");
			gitlet("add", file);
			String middle=gitlet("status");
			gitlet("commit", "test");
			String affter=gitlet("status");
			assertEquals(befour, affter);
			assertNotEquals(middle, affter);
			gitlet("rm", file);
			String before=gitlet("status");
			gitlet("add", file2);
			gitlet("commit", "test2");
			String after=gitlet("status");
			assertNotEquals(before, after);
		}
		@Test
		/**
		 * commit failure cases
		 */
		public void commitTestFailure(){
			String file = TESTING_DIR + "file.txt";
			String fileText = "file";
			String files = TESTING_DIR + "files.txt";
			String filesText = "files";
			createFile(file, fileText);
			createFile(files, filesText);
			gitlet("init");
			String error0=gitlet("commit", file);
			assertEquals(error0,"No changes added to the commit.");
			gitlet("add", file);
			String error1=gitlet("commit");
			assertEquals(error1,"Please enter a commit message.");
			gitlet("commit", "splitpoint");
			gitlet("branch", "hi");
			gitlet("add", file);
			gitlet("commit", "hi1");
			gitlet("rm", file);
			gitlet("checkout", "hi");
			gitlet("add", files);
			String llun=gitlet("commit", "specialcaes");
			assertEquals(llun, "");
			gitlet("log");
		}
		
		/**
		 * Test remove branch with failure case messages
		 */
		@Test
		public void removeBranch(){
			String file = TESTING_DIR + "file.txt";
			String fileText = "file";
			String file2 = TESTING_DIR + "file2.txt";
			String file2Text = "file2";
			createFile(file, fileText);
			createFile(file2, file2Text);
			gitlet("init");
			writeFile(file, "Changed file1");
			gitlet("add", file);
			gitlet("commit", "test");
			writeFile(file2, "shouldn't be returned");
			gitlet("add", file2);
			gitlet("commit", "test");
			gitlet("branch", "branch");
			gitlet("checkout", "branch");
			writeFile(file, "btanch");
			gitlet("add", file);
			gitlet("commit", "test");
			writeFile(file2, "branch");
			gitlet("add", file2);
			gitlet("commit", "test");
			writeFile(file, "b");
			writeFile(file2, "b");
			gitlet("checkout", "master");
			assertEquals(getText(file), "Changed file1");
			assertEquals(getText(file2), "shouldn't be returned");
			String x = gitlet("rm-branch", "master");
			assertEquals(x,"Cannot remove the current branch.");
			String y = gitlet("rm-branch", "mastr");
			assertEquals(y,"A branch with that name does not exist.");
			gitlet("rm-branch", "branch");
			String z=gitlet("checkout", "branch");
			assertEquals(z,"File does not exist in the most recent commit, or no such branch exists.");
		}
		
		/**
		 * Test that tracking doesn't affect previous commits
		 */
		@Test
		public void testTracking() {
			String file = TESTING_DIR + "file.txt";
			String fileText = "file";
			String files = TESTING_DIR + "files.txt";
			String filesText = "files";
			createFile(file, fileText);
			createFile(files, filesText);
			gitlet("init");
			gitlet("add", file);
			gitlet("commit", "swag");
			gitlet("add", files);
			gitlet("rm", file);
			gitlet("commit", "two");
			gitlet("checkout", "1", file);
		}
		
		@Test
		/**
		 * Remove removes stage
		 */
		public void RemoveAndAdd(){
			String file = TESTING_DIR + "file.txt";
			String fileText = "file";
			createFile(file, fileText);
			gitlet("init");
			String error=gitlet("rm", file);
			assertEquals(error,"No reason to remove the file.");
			gitlet("add", file);
			gitlet("commit", "test");
			gitlet("rm", file);
			gitlet("status");
			gitlet("add", file);
			File f = new File(GITLET_DIR+"stage/"+ file);
			assertFalse(f.exists());
			gitlet("add", file);
			assertTrue(f.exists());
			gitlet("rm", file);
			assertFalse(f.exists());
		}
		@Test
		/**
		 * checkout with file
		 */
		public void checkoutA(){
			String file = TESTING_DIR + "file.txt";
			String fileText = "file";
			String file2 = TESTING_DIR + "file2.txt";
			String file2Text = "file2";
			createFile(file, fileText);
			createFile(file2, file2Text);
			gitlet("init");
			writeFile(file, "Changed file1");
			gitlet("add", file);
			gitlet("commit", "test");
			writeFile(file, "shouldn't be printed");
			gitlet("checkout", file);
			assertEquals(getText(file), "Changed file1");
		}
		@Test
		/**
		 * checkout with id and file
		 */
		public void checkoutB(){
			String file = TESTING_DIR + "file.txt";
			String fileText = "file";
			String file2 = TESTING_DIR + "file2.txt";
			String file2Text = "file2";
			createFile(file, fileText);
			createFile(file2, file2Text);
			gitlet("init");
			writeFile(file, "Changed file1");
			gitlet("add", file);
			gitlet("commit", "test");
			writeFile(file, "shouldn't be returned");
			gitlet("add", file);
			gitlet("commit", "test");
			gitlet("checkout", "1", file);
			assertEquals(getText(file), "Changed file1");
		}
		@Test
		/**
		 * checkout with branch name
		 */
		public void checkoutC(){
			String file = TESTING_DIR + "file.txt";
			String fileText = "file";
			String file2 = TESTING_DIR + "file2.txt";
			String file2Text = "file2";
			createFile(file, fileText);
			createFile(file2, file2Text);
			gitlet("init");
			writeFile(file, "Changed file1");
			gitlet("add", file);
			gitlet("commit", "test");
			writeFile(file2, "shouldn't be returned");
			gitlet("add", file2);
			gitlet("commit", "test");
			gitlet("branch", "branch");
			gitlet("checkout", "branch");
			writeFile(file, "btanch");
			gitlet("add", file);
			gitlet("commit", "test");
			writeFile(file2, "branch");
			gitlet("add", file2);
			gitlet("commit", "test");
			writeFile(file, "b");
			writeFile(file2, "b");
			gitlet("checkout", "master");
			assertEquals(getText(file), "Changed file1");
			assertEquals(getText(file2), "shouldn't be returned");
		}

	/**
	 * All the failure cases in rebase.
	 */
	@Test
	public void allAboutRebase() {
		gitlet("init");
		String commitMessage0 = "initial commit";
		String commitMessage1 = "first commit";
		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "This is a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage1);
		String commitMessage2 = "second commit";
		writeFile(wugFileName, "This is a wug? ");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage2);
		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		String commitMessage3 = "third commit";
		writeFile(wugFileName, "This is a cool wug.");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage3);
		gitlet("checkout", "master");
		writeFile(wugFileName, "This is definitely a wug!");
		String commitMessage4 = "forth commit";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage4);
		gitlet("branch", "master1");
		gitlet("checkout", "master1");
		writeFile(wugFileName, "This is definitely a cool wug!");
		String commitMessage5 = "fifth commit";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage5);
		gitlet("checkout", "master");
		writeFile(wugFileName, "This is definitely a awesome wug!");
		String commitMessage6 = "sixth commit";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage6);
		String nobranchname = gitlet("rebase", "cool-bean");
		assertEquals("A branch with that name does not exist.", nobranchname);
		String rebaseitself = gitlet("rebase", "master");
		assertEquals("Cannot rebase a branch onto itself.", rebaseitself);
		gitlet("rebase", "cool-beans");
		gitlet("global-log");
		String log2 = gitlet("log");
		assertArrayEquals(
				new String[] { commitMessage6, commitMessage4, commitMessage3,
						commitMessage2, commitMessage1, commitMessage0 },
				extractCommitMessages(log2));

		gitlet("log");

	}

	/**
	 * Test that you can checkout [id] [file] from an arbitrary commit in the
	 * graph (even one in another branch).
	 */
	@Test
	public void testCheckoutFromAnotherBranch() {
		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "This is a wug.");
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		String coolMessage = "cool branch!";
		writeFile(wugFileName, "This is a cool wug.");
		gitlet("add", wugFileName);
		gitlet("commit", coolMessage);
		gitlet("checkout", "master");
		assertEquals("This is a wug.", getText(wugFileName));
		writeFile(wugFileName, "This is the master of all wugs.");
		gitlet("add", wugFileName);
		gitlet("commit", "mastered wugs");
		writeFile(wugFileName, "Wug is mastered.");
		String commitId = getFirstId(coolMessage);
		gitlet("checkout", commitId, wugFileName);
		assertEquals("This is a cool wug.", getText(wugFileName));
	}

	@Test
	/**
	 * Test that resetting backward appropriately changes the output of log.
	 */
	public void testResetChangeLog() {
		gitlet("init");
		String commitMessage0 = "initial commit";
		String commitMessage1 = "first commit";
		String fileName = TESTING_DIR + "testResetChange.txt";
		String txtText1 = "resetting";
		createFile(fileName, txtText1);
		gitlet("add", fileName);
		gitlet("commit", commitMessage1);
		String commitMessage2 = "second commit";
		String txtText2 = " changes";
		createFile(fileName, txtText2);
		gitlet("add", fileName);
		gitlet("commit", commitMessage2);
		String log1 = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1,
				commitMessage0 }, extractCommitMessages(log1));
		gitlet("reset", "1");
		String log2 = gitlet("log");
		assertArrayEquals(new String[] { commitMessage1, commitMessage0 },
				extractCommitMessages(log2));
	}

	@Test
	/**
	 * Test that log adjusts appropriately when switching from one branch to another.
	 */
	public void logSwitchBranch() {
		gitlet("init");
		String commitMessage0 = "initial commit";
		String commitMessage1 = "first commit";
		String wugFileName = TESTING_DIR + "wug.txt";
		String txtText1 = "This is a wug.";
		createFile(wugFileName, txtText1);
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage1);
		String commitMessage2 = "second commit";
		String txtText2 = "This is a wug? ";
		writeFile(wugFileName, txtText2);
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage2);
		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		String commitMessage3 = "third commit";
		String txtText3 = "This is a cool wug.";
		writeFile(wugFileName, txtText3);
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage3);
		gitlet("checkout", "master");
		writeFile(wugFileName, "This is definitely a cool wug!");
		String commitMessage4 = "forth commit";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage4);
		String log1 = gitlet("log");
		assertArrayEquals(new String[] { commitMessage4, commitMessage2,
				commitMessage1, commitMessage0 }, extractCommitMessages(log1));
		gitlet("checkout", "cool-beans");
		String log2 = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2,
				commitMessage1, commitMessage0 }, extractCommitMessages(log2));
	}

	/**
	 * Test that merge will generate a .conflicted file if a file has been
	 * modified in both branches since the split point.
	 */
	@Test
	public void testMergeConflicted() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String commitMessage1 = "added wug";
		String commitMessage2 = "made wug cool";
		String commitMessage3 = "mastered wugs";
		createFile(wugFileName, "This is a wug.");
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage1);
		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		writeFile(wugFileName, "This is a cool wug.");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage2);
		gitlet("log");
		gitlet("checkout", "master");
		writeFile(wugFileName, "This is a wug?");
		gitlet("add", wugFileName);
		gitlet("commit", "mastered wugs?");
		String merge1 = gitlet("merge", "cool-beans");
		String log = gitlet("log");
		assertEquals(merge1, "Encountered a merge conflict.");
		assertEquals("This is a cool wug.", getText(TESTING_DIR
				+ "wug.txt.conflicted"));
	}

	/**
	 * Test that merge will commit with files from the other branch if those
	 * files had been modified in the other branch but not in the current branch
	 * since the split point.
	 */
	@Test
	public void mergeCommit() {
		gitlet("init");
		String commitMessage0 = "initial commit";
		String commitMessage1 = "first commit";
		String wugFileName = TESTING_DIR + "wug.txt";
		String txtText1 = "This is a wug.";
		createFile(wugFileName, txtText1);
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage1);
		String commitMessage2 = "second commit";
		String txtText2 = "This is a wug? ";
		writeFile(wugFileName, txtText2);
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage2);
		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		String commitMessage3 = "third commit";
		String txtText3 = "This is a cool wug.";
		writeFile(wugFileName, txtText3);
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage3);
		writeFile(wugFileName, "This is an awesome wug.");
		gitlet("add", wugFileName);
		String log1 = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2,
				commitMessage1, commitMessage0 }, extractCommitMessages(log1));
		gitlet("checkout", "master");
		String wugFileName1 = TESTING_DIR + "wugs.txt";
		createFile(wugFileName1, "This is definitely a wug!");
		String commitMessage4 = "forth commit";
		gitlet("add", wugFileName1);
		gitlet("commit", commitMessage4);
		writeFile(wugFileName1, "This is definitely a cool wug!");
		String commitMessage5 = "fifth commit";
		gitlet("add", wugFileName1);
		String log4 = gitlet("log");
		assertArrayEquals(new String[] { commitMessage4, commitMessage2,
				commitMessage1, commitMessage0 }, extractCommitMessages(log4));
		gitlet("merge", "cool-beans");
		String log3 = gitlet("log");
		String commitMessage6 = "Merged master with cool-beans.";
		assertArrayEquals(new String[] { commitMessage6, commitMessage4,
				commitMessage2, commitMessage1, commitMessage0 },
				extractCommitMessages(log3));
	}

	@Test
	public void testCheckout() {
		gitlet("init");
		String commitMessage1 = "first real commit";
		String fileName = TESTING_DIR + "txt.txt";
		String txtText = "CLEAN and CLEAR";
		createFile(fileName, txtText);
		gitlet("add", fileName);
		gitlet("commit", commitMessage1);
		writeFile(fileName, "MESSAY AND SAD :(");
		createFile(fileName + "stff", txtText);
		gitlet("branch", "swag");
		gitlet("checkout", "mySwag");
		gitlet("add", fileName + "stff");
		gitlet("commit", "second commit");
		gitlet("checkout", "1", fileName);
		assertEquals(txtText, getText(fileName));

	}

	/**
	 * Do more test on checkout and branch. Involves init, add, commit, checkout
	 * and branch.
	 */
	@Test
	public void testCheckoutWithTonsOfBranching() {
		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "This is a wug.");
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		String coolMessage = "cool branch!";
		writeFile(wugFileName, "This is a cool wug.");
		gitlet("add", wugFileName);
		gitlet("commit", coolMessage);
		gitlet("checkout", "master");
		assertEquals("This is a wug.", getText(wugFileName));
		writeFile(wugFileName, "This is the master of all wugs.");
		gitlet("add", wugFileName);
		gitlet("commit", "mastered wugs");
		gitlet("checkout", "cool-beans");
		writeFile(wugFileName, "Wug is mastered.");
		gitlet("add", wugFileName);
		gitlet("commit", wugFileName);
		gitlet("branch", "cool");
		gitlet("checkout", "cool");

		writeFile(wugFileName, "cool");
		gitlet("add", wugFileName);
		gitlet("commit", "cool");
		gitlet("branch", "cruel");
		gitlet("checkout", "cruel");

		writeFile(wugFileName, "cruel");
		gitlet("add", wugFileName);
		gitlet("commit", "cruel");
		gitlet("branch", "cruelty");
		gitlet("checkout", "cruelty");

		writeFile(wugFileName, "cruelty");
		gitlet("add", wugFileName);
		gitlet("commit", "cruelty");
		gitlet("checkout", "cool-beans");
		gitlet("branch", "tentacruelty");
		gitlet("checkout", "tentacruelty");

		writeFile(wugFileName, "tentacruelty");
		gitlet("add", wugFileName);
		gitlet("commit", "tentacruelty");
		gitlet("global-log");
		gitlet("status");

		String commitId = getFirstId(coolMessage);
		gitlet("checkout", commitId, wugFileName);
		assertEquals("This is a cool wug.", getText(wugFileName));
	}

	/**
	 * Tests that we cannot do merge again if there are already conflicted.
	 * Involves init, add, commit, merge, checkout and log.
	 */
	@Test
	public void testMergeDoubleConflict() {
		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "This is a wug.");
		String wugFileNames = TESTING_DIR + "wugs.txt";
		createFile(wugFileNames, "These are wugs.");

		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug	");

		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		String coolMessage = "cool branch!";
		writeFile(wugFileName, "This is a cool wug.");
		gitlet("add", wugFileName);
		gitlet("commit", coolMessage);

		gitlet("checkout", "master");
		assertEquals("This is a wug.", getText(wugFileName));
		gitlet("checkout", "cool-beans");
		assertEquals("This is a cool wug.", getText(wugFileName));
		gitlet("checkout", "master");

		writeFile(wugFileName, "This is the master of all wugs.");
		gitlet("add", wugFileName);
		gitlet("commit", "mastered wugs");
		gitlet("log");

		writeFile(wugFileName, "Wug is mastered.");
		String commitId = getFirstId(coolMessage);
		gitlet("merge", "master");
		gitlet("merge", "masters");
		assertEquals("Wug is mastered.", getText(wugFileName));
		gitlet("merge", "cool-beans");
		String x = gitlet("merge", "cool-beans");
		assertEquals(x,
				"Cannot do this command until the merge conflict has been resolved.");
	}

	/**
	 * Tests that if we do merge again after merge the file should be replaced.
	 * Involves init, add, commit, merge, checkout and log.
	 */
	@Test
	public void testMergeShouldReplace() {
		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "This is a wug.");
		String wugFileNames = TESTING_DIR + "wugs.txt";
		createFile(wugFileNames, "These are wugs.");

		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug	");

		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		String coolMessage = "cool branch!";
		writeFile(wugFileName, "This is a cool wug.");
		gitlet("add", wugFileName);
		gitlet("commit", coolMessage);

		gitlet("checkout", "master");
		assertEquals("This is a wug.", getText(wugFileName));
		gitlet("checkout", "cool-beans");
		assertEquals("This is a cool wug.", getText(wugFileName));
		gitlet("checkout", "master");

		String commitId = getFirstId(coolMessage);
		gitlet("merge", "master");
		gitlet("merge", "masters");
		assertEquals("This is a wug.", getText(wugFileName));
		gitlet("merge", "cool-beans");
		assertEquals("This is a cool wug.", getText(wugFileName));
	}

	@Test
	public void testMergeShouldntAndShouldReplace() {
		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "This is a wug.");
		String wugFileNames = TESTING_DIR + "wugs.txt";
		createFile(wugFileNames, "These are wugs.");

		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("add", wugFileNames);
		gitlet("commit", "added wug and wugs");

		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		String coolMessage = "cool branch!";
		writeFile(wugFileName, "This is a cool wug.");
		gitlet("add", wugFileName);
		gitlet("commit", coolMessage);

		gitlet("checkout", "master");
		assertEquals("This is a wug.", getText(wugFileName));
		gitlet("checkout", "cool-beans");
		assertEquals("This is a cool wug.", getText(wugFileName));
		gitlet("checkout", "master");

		writeFile(wugFileNames, "This is the master of all wugs.");
		gitlet("add", wugFileNames);
		gitlet("commit", "mastered wugs");

		String commitId = getFirstId(coolMessage);
		gitlet("merge", "master");
		gitlet("merge", "masters");
		assertEquals("This is a wug.", getText(wugFileName));
		gitlet("merge", "cool-beans");
		assertEquals("This is a cool wug.", getText(wugFileName));
		assertEquals("This is the master of all wugs.", getText(wugFileNames));

	}

	@Test
	public void testMergeConflictAndDoubleFiles() {
		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "This is a wug.");
		String wugFileNames = TESTING_DIR + "wugs.txt";
		createFile(wugFileNames, "These are wugs.");

		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("add", wugFileNames);
		gitlet("commit", "added wug and wugs");

		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		String coolMessage = "cool branch!";
		writeFile(wugFileName, "This is a cool wug.");
		gitlet("add", wugFileName);
		gitlet("commit", coolMessage);

		gitlet("checkout", "master");
		assertEquals("This is a wug.", getText(wugFileName));
		gitlet("checkout", "cool-beans");
		assertEquals("This is a cool wug.", getText(wugFileName));
		gitlet("checkout", "master");

		writeFile(wugFileName, "This is the master of all wugs.");
		gitlet("add", wugFileName);
		gitlet("commit", "mastered wugs");
		gitlet("log");

		writeFile(wugFileName, "Wug is mastered.");
		writeFile(wugFileNames, "Wug is alpha pinene oxide.");
		String commitId = getFirstId(coolMessage);
		gitlet("merge", "master");
		gitlet("merge", "masters");
		assertEquals("Wug is mastered.", getText(wugFileName));
		gitlet("merge", "cool-beans");
		assertEquals("Wug is alpha pinene oxide.", getText(wugFileNames));
		String x = gitlet("merge", "cool-beans");
		assertEquals(x,
				"Cannot do this command until the merge conflict has been resolved.");
	}
	/**
	 * Test checkoutA and checkoutB works after remove
	 */
	@Test
	public void testAddRemoveCommitCheckout(){
		String wugFileName1 = TESTING_DIR + "wug.txt";
		createFile(wugFileName1, "This is a wug.");
		String wugFileName2 = TESTING_DIR + "wugs.txt";
		createFile(wugFileName2, "There are two wugs.");
		
		Gitlet git = new Gitlet();
		git.init();
		git.add("test_files/wug.txt");
		git.add("test_files/wugs.txt");
		git.commit("first commit");
		
		writeFile(wugFileName1, "This is a cool wug.");
		writeFile(wugFileName2, "There are two cool wugs.");
		git.add("test_files/wug.txt");
		git.commit("second commit");
		git.add("test_files/wugs.txt");
		git.checkoutA("test_files/wugs.txt");
		
		assertEquals("This is a cool wug.", getText(wugFileName1));
		assertEquals("There are two wugs.", getText(wugFileName2));
		
		writeFile(wugFileName2, "There are two cool wugs.");
		git.add("test_files/wugs.txt");
		git.remove("test_files/wugs.txt");
		git.remove("test_files/wugs.txt");
		git.remove("test_files/wugs.txt");
		git.commit("third commit");
		git.checkoutB(3, "test_files/wugs.txt");
		assertEquals("There are two cool wugs.", getText(wugFileName2));
		
		writeFile(wugFileName2, "There are two cool wugs.");
		git.add("test_files/wugs.txt");
		git.remove("test_files/wugs.txt");
		git.add("test_files/wugs.txt");
		git.commit("real third commit?");
		git.checkoutB(3, "test_files/wugs.txt");
		assertEquals("There are two cool wugs.", getText(wugFileName2));
		
		writeFile(wugFileName2, "There are two cool wugs.");
		git.add("test_files/wugs.txt");
		git.remove("test_files/wugs.txt");
		git.add("test_files/wugs.txt");
		git.add("test_files/wugs.txt");
		git.commit("real third commit!");
		writeFile(wugFileName2, "There are two awesome wugs.");
		git.checkoutB(3, "test_files/wugs.txt");
		assertEquals("There are two cool wugs.", getText(wugFileName2));
	}

	@Test
	public void testMergeDoubleFilesNoConflict() {
		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "This is a wug.");
		String wugFileNames = TESTING_DIR + "wugs.txt";
		createFile(wugFileNames, "These are wugs.");

		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("add", wugFileNames);
		gitlet("commit", "added wug and wugs");

		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		String coolMessage = "cool branch!";
		writeFile(wugFileName, "This is a cool wug.");
		writeFile(wugFileNames, "Wug is alpha pinene oxide.");
		gitlet("add", wugFileName);
		gitlet("add", wugFileNames);
		gitlet("commit", coolMessage);

		gitlet("checkout", "master");
		assertEquals("This is a wug.", getText(wugFileName));
		gitlet("checkout", "cool-beans");
		assertEquals("This is a cool wug.", getText(wugFileName));

		gitlet("checkout", "master");
		String commitId = getFirstId(coolMessage);
		gitlet("merge", "master");
		gitlet("merge", "masters");
		assertEquals("This is a wug.", getText(wugFileName));
		gitlet("merge", "cool-beans");
		assertEquals("Wug is alpha pinene oxide.", getText(wugFileNames));
		assertEquals("This is a cool wug.", getText(wugFileName));
		String x = gitlet("merge", "cool-beans");
		System.out.println(x);
	}

	/**
	 * Do more test on checkout and branch. Involves init, add, commit,
	 * checkout, branch, global-log and log.
	 */
	@Test
	public void testCheckoutWithTonsOfBranching2() {
		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "This is a wug.");

		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");

		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		String coolMessage = "cool branch!";
		writeFile(wugFileName, "This is a cool wug.");
		gitlet("add", wugFileName);
		gitlet("commit", coolMessage);

		gitlet("checkout", "master");
		assertEquals("This is a wug.", getText(wugFileName));

		writeFile(wugFileName, "This is the master of all wugs.");
		gitlet("add", wugFileName);
		gitlet("commit", "mastered wugs");
		writeFile(wugFileName, "This is the master of all wugs!");
		gitlet("add", wugFileName);
		gitlet("commit", "mastered wugs!");
		writeFile(wugFileName, "This is the master of all wugs!?");
		gitlet("add", wugFileName);
		gitlet("commit", "mastered wugs!?");
		gitlet("checkout", "cool-beans");

		writeFile(wugFileName, "Wug is mastered.");
		gitlet("add", wugFileName);
		gitlet("commit", wugFileName);
		gitlet("branch", "cool");
		gitlet("checkout", "cool");

		writeFile(wugFileName, "cool");
		gitlet("add", wugFileName);
		gitlet("commit", "cool");
		gitlet("branch", "cruel");
		gitlet("checkout", "cruel");

		writeFile(wugFileName, "cruel");
		gitlet("add", wugFileName);
		gitlet("commit", "cruel");
		gitlet("branch", "cruelty");
		gitlet("checkout", "cruelty");

		writeFile(wugFileName, "cruelty");
		gitlet("add", wugFileName);
		gitlet("commit", "cruelty");
		gitlet("checkout", "cool-beans");
		gitlet("branch", "tentacruelty");
		gitlet("checkout", "tentacruelty");

		writeFile(wugFileName, "tentacruelty");
		gitlet("add", wugFileName);
		gitlet("commit", "tentacruelty");
		gitlet("global-log");
		gitlet("status");

		String commitId = getFirstId(coolMessage);
		gitlet("checkout", commitId, wugFileName);
		gitlet("global-log");
		assertEquals("This is a cool wug.", getText(wugFileName));
		gitlet("checkout", "7", wugFileName);
		assertEquals("cool", getText(wugFileName));
		gitlet("checkout", "8", wugFileName);
		assertEquals("cruel", getText(wugFileName));
		gitlet("checkout", "cruelty");
		gitlet("log");
		gitlet("rebase", "master");
		gitlet("log");
		gitlet("checkout", "cruelty");
		gitlet("log");
	}

	/**
	 * Test that the output of log after a rebase includes the commit messages
	 * from both branches involved in the rebase.
	 */
	@Test
	public void logAfterRebase() {
		gitlet("init");
		String commitMessage0 = "initial commit";
		String commitMessage1 = "first commit";
		String wugFileName = TESTING_DIR + "wug.txt";
		String txtText1 = "This is a wug.";
		createFile(wugFileName, txtText1);
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage1);

		String commitMessage2 = "second commit";
		String txtText2 = "This is a wug? ";
		createFile(wugFileName, txtText2);
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage2);

		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");

		String commitMessage3 = "third commit";
		writeFile(wugFileName, "This is a cool wug.");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage3);
		gitlet("checkout", "master");
		writeFile(wugFileName, "This is definitely a cool wug!");
		String commitMessage4 = "forth commit";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage4);

		gitlet("rebase", "cool-beans");
		String log = gitlet("log");
		assertArrayEquals(new String[] { commitMessage4, commitMessage3,
				commitMessage2, commitMessage1, commitMessage0 },
				extractCommitMessages(log));
	}

	/**
	 * Test that changes in the base branch propagate through the replayed
	 * branch during a rebase.
	 */
	@Test
	public void rebaseChangeBranch() {
		gitlet("init");
		String commitMessage0 = "initial commit";
		String commitMessage1 = "first commit";
		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "This is a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage1);

		String commitMessage2 = "second commit";
		writeFile(wugFileName, "This is a wug? ");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage2);

		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");

		String commitMessage3 = "third commit";
		writeFile(wugFileName, "This is a cool wug.");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage3);
		gitlet("log");

		gitlet("checkout", "master");
		writeFile(wugFileName, "This is definitely a wug!");
		String commitMessage4 = "forth commit";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage4);
		gitlet("branch", "master1");
		gitlet("checkout", "master1");

		writeFile(wugFileName, "This is definitely a cool wug!");
		String commitMessage5 = "fifth commit";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage5);

		gitlet("checkout", "master");
		writeFile(wugFileName, "This is definitely a awesome wug!");
		String commitMessage6 = "sixth commit";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage6);
		gitlet("rebase", "cool-beans");
		String log2 = gitlet("log");
		assertArrayEquals(
				new String[] { commitMessage6, commitMessage4, commitMessage3,
						commitMessage2, commitMessage1, commitMessage0 },
				extractCommitMessages(log2));
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
