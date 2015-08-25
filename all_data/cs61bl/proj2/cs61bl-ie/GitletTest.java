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
		File g = new File(GITLET_DIR + "/stage");
		File h = new File(GITLET_DIR + "/Commit0");
		assertTrue(f.exists());
		assertTrue(g.exists());
		assertTrue(h.exists());
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
	 * Basic test checking commit can move files around
	 */
	@Test
	public void testCommit() {
		gitlet("init");
		String poopFile = TESTING_DIR + "poop.txt";
		createFile(poopFile, "Poop");
		gitlet("add", poopFile);
		File poopStage = new File(".gitlet/stage/" + poopFile);
		assertTrue(poopStage.exists());
		gitlet("commit", "Committed poop");
		assertTrue(!poopStage.exists());
		File poo = new File(poopFile);
		assertTrue(poo.exists());
		File pooCommit = new File(".gitlet/Commit1/" + poopFile);
		assertTrue(pooCommit.exists());
		String dongFile = TESTING_DIR + "dong.txt";
		createFile(dongFile, "Dong");
		gitlet("add", dongFile);
		File dongStage = new File(".gitlet/stage/" + dongFile);
		assertTrue(dongStage.exists());
		gitlet("commit", "Committed dong");
		assertTrue(!dongStage.exists());
		File dong = new File(dongFile);
		assertTrue(dong.exists());
		File dongCommit = new File(".gitlet/Commit2/" + dongFile);
		assertTrue(dongCommit.exists());

		String commitMessage1 = "initial commit";
		String commitMessage2 = "Committed poop";
		String commitMessage3 = "Committed dong";
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2,
				commitMessage1 }, extractCommitMessages(logContent));
	}

	/**
	 * Test checking that commit fails with no changes made
	 */
	@Test
	public void testCommitFail() {
		gitlet("init");
		String poopFile = TESTING_DIR + "poop.txt";
		createFile(poopFile, "Poop");
		File poo = new File(poopFile);
		assertTrue(poo.exists());
		File poopStage = new File(".gitlet/stage/" + poopFile);
		File stage = new File(".gitlet/stage/");
		assertTrue(stage.listFiles().length == 0);
		assertTrue(!poopStage.exists());
		String fail = gitlet("commit", "Failed commit");
		assertEquals("No changes added to the commit.", fail);
	}

	/**
	 * Test that files exist properly when file should be taken from parent
	 */
	@Test
	public void testCommitTakeFromParent() {
		gitlet("init");
		String poopFile = TESTING_DIR + "poop.txt";
		createFile(poopFile, "Poop");
		File poop = new File(poopFile);
		File poopStage = new File(".gitlet/stage/" + poopFile);
		String dongFile = TESTING_DIR + "dong.txt";
		createFile(dongFile, "Dong");
		File dongStage = new File(".gitlet/stage/" + dongFile);
		gitlet("add", poopFile);
		gitlet("add", dongFile);
		assertTrue(poopStage.exists());
		assertTrue(dongStage.exists());
		gitlet("commit", "Committed poop and dong");
		File poopCommit1 = new File(".gitlet/Commit1/" + poopFile);
		File dongCommit1 = new File(".gitlet/Commit1/" + dongFile);
		assertTrue(poopCommit1.exists());
		assertTrue(dongCommit1.exists());
		gitlet("add", poopFile);
		gitlet("commit", "Committed poop, retrieve dong");
		File poopCommit2 = new File(".gitlet/Commit2/" + poopFile);
		File dongCommit2 = new File(".gitlet/Commit2/" + dongFile);
		assertTrue(poopCommit2.exists());
		assertTrue(!dongCommit2.exists());
		writeFile(poopFile, "p");
		writeFile(dongFile, "p");
		gitlet("checkout", poopFile);
		gitlet("checkout", dongFile);
		assertEquals("Poop", getText(poopFile));
		assertEquals("Dong", getText(dongFile));
	}

	/**
	 * Test basic remove functionality
	 */
	@Test
	public void testRemove() {
		gitlet("init");
		String poopFile = TESTING_DIR + "poop.txt";
		createFile(poopFile, "Poop");
		gitlet("add", poopFile);
		File poopStage = new File(".gitlet/stage/" + poopFile);
		File poopCommit = new File(".gitlet/Commit1/" + poopFile);
		assertTrue(poopStage.exists());
		gitlet("commit", "Committed poop");
		assertTrue(!poopStage.exists());
		gitlet("add", poopFile);
		assertTrue(poopStage.exists());
		gitlet("rm", poopFile);
		assertTrue(!poopStage.exists());
		assertTrue(poopCommit.exists());
		gitlet("rm", poopFile);
		String dongFile = TESTING_DIR + "dong.txt";
		createFile(dongFile, "Dong");
		File dongStage = new File(".gitlet/stage/" + dongFile);
		File dongCommit = new File(".gitlet/Commit2/" + dongFile);
		File poopCommit2 = new File(".gitlet/Commit2/" + poopFile);
		gitlet("add", dongFile);
		assertTrue(dongStage.exists());
		gitlet("commit", "Committed dong, not poop");
		assertTrue(dongCommit.exists());
		assertTrue(!poopCommit2.exists());
	}

	/**
	 * Test basic find functionality and checks find when branched off
	 */
	@Test
	public void testBasicFind() {
		String testFileName1 = TESTING_DIR + "test1";
		String testContent1 = "this is test1";
		String testFileName2 = TESTING_DIR + "test2";
		String testContent2 = "this is test2";
		createFile(testFileName1, testContent1);
		createFile(testFileName2, testContent2);
		gitlet("init");
		gitlet("add", testFileName1);
		gitlet("add", testFileName2);
		gitlet("commit", testContent1);
		gitlet("add", testFileName1);
		gitlet("add", testFileName2);
		gitlet("commit", testContent2);
		String findMsg1 = gitlet("find", testContent1);
		String findMsg2 = gitlet("find", testContent2);
		assertEquals("1", findMsg1);
		assertEquals("2", findMsg2);
		gitlet("branch", "scrub");
		gitlet("add", testFileName1);
		gitlet("commit", "master branched off");
		gitlet("checkout", "scrub");
		gitlet("add", testFileName1);
		gitlet("commit", "scrub branched off");
		String findMsg3 = gitlet("find", "master branched off");
		String findMsg4 = gitlet("find", "scrub branched off");
		assertEquals("3", findMsg3);
		assertEquals("4", findMsg4);
	}

	/**
	 * Testing checkout of files and previous branch
	 */
	@Test
	public void testDaniel1() {
		createFile(TESTING_DIR + "a.txt", "1");
		createFile(TESTING_DIR + "b.txt", "4");
		gitlet("init");
		String logContent = gitlet("log");
		String commitMessage1 = "initial commit";
		assertArrayEquals(new String[] { commitMessage1 },
				extractCommitMessages(logContent));
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("commit", "added 1 a");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "added 4 to b");
		writeFile(TESTING_DIR + "a.txt", "changed this");
		writeFile(TESTING_DIR + "b.txt", "changed this b");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "changed a");
		writeFile(TESTING_DIR + "b.txt", "changed again");
		gitlet("checkout", "1", TESTING_DIR + "a.txt");
		gitlet("checkout", TESTING_DIR + "b.txt");
		assertEquals("1", getText(TESTING_DIR + "a.txt"));
		assertEquals("changed this b", getText(TESTING_DIR + "b.txt"));
		String log = gitlet("log");
		String commitMessage2 = "added 1 a";
		String commitMessage3 = "added 4 to b";
		String commitMessage4 = "changed a";
		assertArrayEquals(new String[] { commitMessage4, commitMessage3,
				commitMessage2, commitMessage1 }, extractCommitMessages(log));
	}

	/**
	 * Testing reset
	 */
	@Test
	public void testDaniel2() {
		createFile(TESTING_DIR + "a.txt", "1");
		createFile(TESTING_DIR + "b.txt", "4");
		createFile(TESTING_DIR + "c.txt", "5");

		gitlet("init");

		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "first commit");

		gitlet("rm", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "second commit");

		writeFile(TESTING_DIR + "a.txt", "blah");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("commit", "third commit");

		writeFile(TESTING_DIR + "b.txt", "changed");
		writeFile(TESTING_DIR + "c.txt", "changed c");
		gitlet("reset", "2");

		assertEquals("blah", getText(TESTING_DIR + "a.txt"));
		assertEquals("4", getText(TESTING_DIR + "b.txt"));
		assertEquals("5", getText(TESTING_DIR + "c.txt"));

		String log = gitlet("log");
		String commitMessage1 = "initial commit";
		String commitMessage2 = "first commit";
		String commitMessage3 = "second commit";
		assertArrayEquals(new String[] { commitMessage3, commitMessage2,
				commitMessage1 }, extractCommitMessages(log));
	}

	/**
	 * Testing nested dirs with checkout
	 */
	@Test
	public void testDaniel3() {
		File a = new File(TESTING_DIR + "nest1/nest2/nest3/nest4");
		a.mkdirs();

		String noNest = TESTING_DIR + "a.txt";
		String nest1 = TESTING_DIR + "nest1/a.txt";
		String nest4 = TESTING_DIR + "nest1/nest2/nest3/nest4/a.txt";

		createFile(noNest, "1");
		createFile(nest1, "2");
		createFile(nest4, "3");

		gitlet("init");
		gitlet("add", noNest);
		gitlet("commit", "first a");

		gitlet("add", nest1);
		gitlet("add", nest4);
		gitlet("commit", "nest1 nest4 a");

		writeFile(noNest, "changed no nest");
		writeFile(nest1, "changed nest1");
		writeFile(nest4, "changed nest 4");

		gitlet("add", noNest);
		gitlet("add", nest1);
		gitlet("add", nest4);

		gitlet("commit", "changed everything");

		gitlet("checkout", "2", noNest);
		gitlet("checkout", "2", nest1);
		gitlet("checkout", "2", nest4);

		assertEquals("1", getText(noNest));
		assertEquals("2", getText(nest1));
		assertEquals("3", getText(nest4));

		String log = gitlet("log");
		String commitMessage1 = "initial commit";
		String commitMessage2 = "first a";
		String commitMessage3 = "nest1 nest4 a";
		String commitMessage4 = "changed everything";
		assertArrayEquals(new String[] { commitMessage4, commitMessage3,
				commitMessage2, commitMessage1 }, extractCommitMessages(log));
	}

	/**
	 * Testing checkout on branch
	 */
	@Test
	public void testDaniel4() {
		createFile(TESTING_DIR + "a.txt", "1");
		createFile(TESTING_DIR + "b.txt", "2");

		gitlet("init");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "first on master");

		gitlet("branch", "branch2");

		writeFile(TESTING_DIR + "a.txt", "1 master");
		writeFile(TESTING_DIR + "b.txt", "2 master");

		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");

		gitlet("commit", "master branch off");

		gitlet("checkout", "branch2");

		assertEquals("1", getText(TESTING_DIR + "a.txt"));
		assertEquals("2", getText(TESTING_DIR + "b.txt"));

		writeFile(TESTING_DIR + "a.txt", "1 branch2");
		writeFile(TESTING_DIR + "b.txt", "2 branch2");

		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "branch2 now off");

		String log = gitlet("log");
		String commitMessage1 = "initial commit";
		String commitMessage2 = "first on master";
		String commitMessage3 = "branch2 now off";
		assertArrayEquals(new String[] { commitMessage3, commitMessage2,
				commitMessage1 }, extractCommitMessages(log));

		gitlet("checkout", "master");
		assertEquals("1 master", getText(TESTING_DIR + "a.txt"));
		assertEquals("2 master", getText(TESTING_DIR + "b.txt"));

		String log2 = gitlet("log");
		String commitMessage4 = "master branch off";
		assertArrayEquals(new String[] { commitMessage4, commitMessage2,
				commitMessage1 }, extractCommitMessages(log2));
	}

	/**
	 * Test if split point correctly found
	 */
	@Test
	public void testSplit() {
		String a = TESTING_DIR + "a.txt";
		createFile(a, "a");
		Gitlet git = new Gitlet();
		git.init();
		git.add(a);
		git.commit("first on master");
		git.add(a);
		git.commit("second on master");
		git.branch("branch2");
		git.add(a);
		git.commit("master off");
		git.checkout3("branch2");
		git.add(a);
		git.commit("branch2 off");
		git.add(a);
		git.commit("branch2 futher off");
		assertTrue(git.splitPoint("master", "branch2") == 2);
	}

	/**
	 * Test if files are correctly determined to be the same
	 * 
	 * @throws IOException
	 */
	@Test
	public void testSameFiles() throws IOException {
		String a = TESTING_DIR + "a.txt";
		createFile(a, "a");
		Gitlet git = new Gitlet();
		git.init();
		git.add(a);
		assertTrue(git.sameFiles(
				Paths.get(a),
				Paths.get(".gitlet" + File.separator + "stage" + File.separator
						+ TESTING_DIR + File.separator + "a.txt")));
		git.commit("commit a");
		assertTrue(git.sameFiles(
				Paths.get(a),
				Paths.get(".gitlet" + File.separator + "Commit1"
						+ File.separator + TESTING_DIR + File.separator
						+ "a.txt")));
	}

	/**
	 * Test merging two branches when both branches change
	 */
	@Test
	public void testMergeBothChange() {
		createFile(TESTING_DIR + "a.txt", "1");
		createFile(TESTING_DIR + "b.txt", "2");

		gitlet("init");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "first on master");

		gitlet("branch", "branch2");

		writeFile(TESTING_DIR + "a.txt", "1 master");
		writeFile(TESTING_DIR + "b.txt", "2 master");

		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");

		gitlet("commit", "master branch off");

		gitlet("checkout", "branch2");

		assertEquals("1", getText(TESTING_DIR + "a.txt"));
		assertEquals("2", getText(TESTING_DIR + "b.txt"));

		writeFile(TESTING_DIR + "a.txt", "1 branch2");
		writeFile(TESTING_DIR + "b.txt", "2 branch2");

		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "branch2 now off");

		String msg = gitlet("merge", "master");
		assertEquals(msg, "Encountered a merge conflict.");
	}

	/**
	 * Test merge only one branch changes
	 */
	@Test
	public void testMergeOneChange() {
		createFile(TESTING_DIR + "a.txt", "1");
		createFile(TESTING_DIR + "b.txt", "2");

		gitlet("init");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "first on master"); // make initial commit

		gitlet("branch", "branch2");
		gitlet("checkout", "branch2"); // now on branch2

		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");

		gitlet("commit", "branch2 branch off"); // branch2 committed with no
												// change

		gitlet("checkout", "master"); // change to master branch
		writeFile(TESTING_DIR + "a.txt", "1 master2");
		writeFile(TESTING_DIR + "b.txt", "2 master2");

		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "master now off"); // master branch committed and
											// changed text

		gitlet("checkout", "branch2"); // change back to branch 2

		gitlet("merge", "master");
		String log = gitlet("log");
		String commitMessage1 = "initial commit";
		String commitMessage2 = "first on master";
		String commitMessage3 = "branch2 branch off";
		String commitMessage4 = "Merged branch2 with master.";
		assertArrayEquals(new String[] { commitMessage4, commitMessage3,
				commitMessage2, commitMessage1 }, extractCommitMessages(log));

		assertEquals("1 master2", getText(TESTING_DIR + "a.txt"));
		assertEquals("2 master2", getText(TESTING_DIR + "b.txt"));
	}

	/**
	 * Test merge two branches that change, with only one conflicted file
	 */
	@Test
	public void testMergeBothChangeOneFile() {
		createFile(TESTING_DIR + "a.txt", "1");
		createFile(TESTING_DIR + "b.txt", "2");

		gitlet("init");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "first on master");

		gitlet("branch", "branch2");

		writeFile(TESTING_DIR + "a.txt", "1 master");
		writeFile(TESTING_DIR + "b.txt", "2 master");

		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");

		gitlet("commit", "master branch off"); // master branch committed with
												// two changes

		gitlet("checkout", "branch2");

		assertEquals("1", getText(TESTING_DIR + "a.txt"));
		assertEquals("2", getText(TESTING_DIR + "b.txt"));

		writeFile(TESTING_DIR + "a.txt", "1 branch2");

		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "branch2 now off"); // branch2 committed with one
												// change

		String msg = gitlet("merge", "master");
		assertEquals("2 master", getText(TESTING_DIR + "b.txt"));
		File conf = new File(TESTING_DIR + "a.txt.conflicted");
		assertTrue(conf.exists());
		assertEquals(msg, "Encountered a merge conflict.");

		String err = gitlet("branch", "scrub");
		assertEquals(err,
				"Cannot do this command until the merge conflict has been resolved.");
	}

	/**
	 * Test merge rm special case
	 */
	@Test
	public void testMergeRm() {
		createFile(TESTING_DIR + "a.txt", "1");
		createFile(TESTING_DIR + "b.txt", "2");

		gitlet("init");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "first on master");

		gitlet("branch", "branch2");

		writeFile(TESTING_DIR + "a.txt", "1 master");
		writeFile(TESTING_DIR + "b.txt", "2 master");

		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");

		gitlet("commit", "master branch off"); // master branch committed with
												// two changes

		gitlet("checkout", "branch2");

		assertEquals("1", getText(TESTING_DIR + "a.txt"));
		assertEquals("2", getText(TESTING_DIR + "b.txt"));

		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("rm", TESTING_DIR + "b.txt");
		gitlet("commit", "branch2 now off"); // branch2 committed with one
												// change

		writeFile(TESTING_DIR + "a.txt", "1 branch2");
		writeFile(TESTING_DIR + "b.txt", "2 branch2");
		gitlet("merge", "master");
		assertEquals("1 master", getText(TESTING_DIR + "a.txt"));
		assertEquals("2 master", getText(TESTING_DIR + "b.txt"));
	}

	/**
	 * Test basic rebase
	 */
	@Test
	public void testBasicRebase() {
		createFile(TESTING_DIR + "a.txt", "1");
		createFile(TESTING_DIR + "b.txt", "2");
		gitlet("init");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "first on master");
		gitlet("branch", "branch2");
		writeFile(TESTING_DIR + "a.txt", "1 master");
		writeFile(TESTING_DIR + "b.txt", "2 master");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "master branch off");
		gitlet("checkout", "branch2");
		writeFile(TESTING_DIR + "b.txt", "2 branch2");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "branch2 now off");
		assertEquals("2 branch2", getText(TESTING_DIR + "b.txt"));
		assertEquals("1", getText(TESTING_DIR + "a.txt"));
		gitlet("rebase", "master");
		assertEquals("1 master", getText(TESTING_DIR + "a.txt"));
		assertEquals("2 branch2", getText(TESTING_DIR + "b.txt"));
	}

	/**
	 * Testing rebase with longer branches
	 */
	@Test
	public void testRebaseLongerCurrentBranch() {
		createFile(TESTING_DIR + "a.txt", "a");
		createFile(TESTING_DIR + "b.txt", "b");
		createFile(TESTING_DIR + "c.txt", "c");
		gitlet("init");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "first on master");
		gitlet("branch", "branch");
		writeFile(TESTING_DIR + "a.txt", "a master");
		writeFile(TESTING_DIR + "b.txt", "b master");
		writeFile(TESTING_DIR + "c.txt", "c master");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "master branch off");
		gitlet("checkout", "branch");
		writeFile(TESTING_DIR + "b.txt", "b branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "branch now off");
		assertEquals("b branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("a", getText(TESTING_DIR + "a.txt"));
		assertEquals("c", getText(TESTING_DIR + "c.txt"));
		writeFile(TESTING_DIR + "b.txt", "b2 branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "branch off 2");
		gitlet("rebase", "master");
		assertEquals("a master", getText(TESTING_DIR + "a.txt"));
		assertEquals("b2 branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("c master", getText(TESTING_DIR + "c.txt"));
	}

	/**
	 * Test rebase file not in current branch, but in given branch
	 */
	@Test
	public void testRebaseFileInOneNotOther() {
		createFile(TESTING_DIR + "a.txt", "a");
		createFile(TESTING_DIR + "b.txt", "b");
		createFile(TESTING_DIR + "c.txt", "c");
		gitlet("init");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "first on master");
		gitlet("branch", "branch");
		writeFile(TESTING_DIR + "a.txt", "a master");
		writeFile(TESTING_DIR + "b.txt", "b master");
		writeFile(TESTING_DIR + "c.txt", "c master");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "master branch off");
		gitlet("checkout", "branch");
		writeFile(TESTING_DIR + "b.txt", "b branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "branch now off");
		assertEquals("b branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("a", getText(TESTING_DIR + "a.txt"));
		assertEquals("c", getText(TESTING_DIR + "c.txt"));
		writeFile(TESTING_DIR + "b.txt", "b2 branch");
		writeFile(TESTING_DIR + "c.txt", "c2 branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("rm", TESTING_DIR + "c.txt");
		gitlet("commit", "branch off 2");
		gitlet("rebase", "master");
		assertEquals("a master", getText(TESTING_DIR + "a.txt"));
		assertEquals("b2 branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("c master", getText(TESTING_DIR + "c.txt"));
	}

	/**
	 * Extended branching on current branch
	 */
	@Test
	public void testRebaseExtendedBranching() {
		createFile(TESTING_DIR + "a.txt", "a");
		createFile(TESTING_DIR + "b.txt", "b");
		createFile(TESTING_DIR + "c.txt", "c");
		gitlet("init");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "first on master");
		gitlet("branch", "branch");
		writeFile(TESTING_DIR + "a.txt", "a master");
		writeFile(TESTING_DIR + "b.txt", "b master");
		writeFile(TESTING_DIR + "c.txt", "c master");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "master branch off");
		gitlet("checkout", "branch");
		writeFile(TESTING_DIR + "b.txt", "b branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "branch now off");
		assertEquals("b branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("a", getText(TESTING_DIR + "a.txt"));
		assertEquals("c", getText(TESTING_DIR + "c.txt"));
		writeFile(TESTING_DIR + "b.txt", "b2 branch");
		writeFile(TESTING_DIR + "c.txt", "c2 branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "branch off 2");
		gitlet("branch", "branch again");
		writeFile(TESTING_DIR + "a.txt", "a2 branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("commit", "branch off 3");
		assertEquals("a2 branch", getText(TESTING_DIR + "a.txt"));
		assertEquals("b2 branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("c2 branch", getText(TESTING_DIR + "c.txt"));
		gitlet("checkout", "branch again");
		assertEquals("b2 branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("c2 branch", getText(TESTING_DIR + "c.txt"));
		assertEquals("a", getText(TESTING_DIR + "a.txt"));
		writeFile(TESTING_DIR + "b.txt", "b3 branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "branch again off");
		assertEquals("b3 branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("c2 branch", getText(TESTING_DIR + "c.txt"));
		assertEquals("a", getText(TESTING_DIR + "a.txt"));
		gitlet("checkout", "branch");
		assertEquals("a2 branch", getText(TESTING_DIR + "a.txt"));
		assertEquals("b2 branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("c2 branch", getText(TESTING_DIR + "c.txt"));
		gitlet("rebase", "master");
		assertEquals("a2 branch", getText(TESTING_DIR + "a.txt"));
		assertEquals("b2 branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("c2 branch", getText(TESTING_DIR + "c.txt"));
		gitlet("reset", "8");
		assertEquals("a master", getText(TESTING_DIR + "a.txt"));
		assertEquals("b2 branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("c2 branch", getText(TESTING_DIR + "c.txt"));
		gitlet("reset", "7");
		assertEquals("a master", getText(TESTING_DIR + "a.txt"));
		assertEquals("b branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("c master", getText(TESTING_DIR + "c.txt"));
	}

	/**
	 * Test rebase log
	 */
	@Test
	public void testBasicRebaseLog() {
		createFile(TESTING_DIR + "a.txt", "1");
		createFile(TESTING_DIR + "b.txt", "2");
		gitlet("init");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "first on master");
		gitlet("branch", "branch2");
		writeFile(TESTING_DIR + "a.txt", "1 master");
		writeFile(TESTING_DIR + "b.txt", "2 master");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "master branch off");
		gitlet("checkout", "branch2");
		writeFile(TESTING_DIR + "b.txt", "2 branch2");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "branch2 now off");
		assertEquals("2 branch2", getText(TESTING_DIR + "b.txt"));
		assertEquals("1", getText(TESTING_DIR + "a.txt"));
		gitlet("rebase", "master");
		String log3 = gitlet("log");
		String commitMessage7 = "initial commit";
		String commitMessage8 = "first on master";
		String commitMessage9 = "master branch off";
		String commitMessage10 = "branch2 now off";
		assertArrayEquals(new String[] { commitMessage10, commitMessage9,
				commitMessage8, commitMessage7 }, extractCommitMessages(log3));
	}

	/**
	 * Test extended branching rebase log
	 */
	@Test
	public void testExtendedBranchingRebaseLog() {
		createFile(TESTING_DIR + "a.txt", "a");
		createFile(TESTING_DIR + "b.txt", "b");
		createFile(TESTING_DIR + "c.txt", "c");
		gitlet("init");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "first on master");
		gitlet("branch", "branch");
		writeFile(TESTING_DIR + "a.txt", "a master");
		writeFile(TESTING_DIR + "b.txt", "b master");
		writeFile(TESTING_DIR + "c.txt", "c master");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "master branch off");
		gitlet("checkout", "branch");
		writeFile(TESTING_DIR + "b.txt", "b branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "branch now off");
		assertEquals("b branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("a", getText(TESTING_DIR + "a.txt"));
		assertEquals("c", getText(TESTING_DIR + "c.txt"));
		writeFile(TESTING_DIR + "b.txt", "b2 branch");
		writeFile(TESTING_DIR + "c.txt", "c2 branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "branch off 2");
		gitlet("branch", "branch again");
		writeFile(TESTING_DIR + "a.txt", "a2 branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("commit", "branch off 3");
		assertEquals("a2 branch", getText(TESTING_DIR + "a.txt"));
		assertEquals("b2 branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("c2 branch", getText(TESTING_DIR + "c.txt"));
		gitlet("checkout", "branch again");
		assertEquals("b2 branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("c2 branch", getText(TESTING_DIR + "c.txt"));
		assertEquals("a", getText(TESTING_DIR + "a.txt"));
		writeFile(TESTING_DIR + "b.txt", "b3 branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "branch again off");
		assertEquals("b3 branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("c2 branch", getText(TESTING_DIR + "c.txt"));
		assertEquals("a", getText(TESTING_DIR + "a.txt"));
		gitlet("checkout", "branch");
		assertEquals("a2 branch", getText(TESTING_DIR + "a.txt"));
		assertEquals("b2 branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("c2 branch", getText(TESTING_DIR + "c.txt"));
		gitlet("rebase", "master");
		String log3 = gitlet("log");
		String commitMessage7 = "initial commit";
		String commitMessage8 = "first on master";
		String commitMessage9 = "master branch off";
		String commitMessage10 = "branch now off";
		String commitMessage11 = "branch off 2";
		String commitMessage12 = "branch off 3";
		assertArrayEquals(
				new String[] { commitMessage12, commitMessage11,
						commitMessage10, commitMessage9, commitMessage8,
						commitMessage7 }, extractCommitMessages(log3));
	}

	@Test
	public void testCheckoutID() {
		createFile(TESTING_DIR + "a.txt", "text a");
		createFile(TESTING_DIR + "b.txt", "text b");
		gitlet("init");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("commit", "commit msg 1");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "commit msg 2");
		writeFile(TESTING_DIR + "b.txt", "changed b");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "commit msg 3");
		gitlet("branch", "newbranch");
		gitlet("checkout", "2", TESTING_DIR + "b.txt");
		assertEquals("text b", getText(TESTING_DIR + "b.txt"));
		createFile(TESTING_DIR + "c.txt", "text c");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "commit msg 4");
		gitlet("checkout", "newbranch");
		writeFile(TESTING_DIR + "c.txt", "c changed");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "commit msg 3 at newbranch");
		gitlet("checkout", "4", TESTING_DIR + "c.txt");
		assertEquals("text c", getText(TESTING_DIR + "c.txt"));
		assertEquals("changed b", getText(TESTING_DIR + "b.txt"));
		gitlet("checkout", "2", TESTING_DIR + "b.txt");
		assertEquals("text b", getText(TESTING_DIR + "b.txt"));
	}

	@Test
	public void testLogChangeReset() {
		createFile(TESTING_DIR + "a.txt", "text 1");
		createFile(TESTING_DIR + "b.txt", "text 2");
		gitlet("init");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("commit", "commit msg 1");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "commit msg 2");
		writeFile(TESTING_DIR + "b.txt", "changed b");
		gitlet("commit", "commit msg 3");
		gitlet("reset", "2");
		assertEquals("text 2", getText(TESTING_DIR + "b.txt"));
		String log = gitlet("log");
		String commitMessage0 = "initial commit";
		String commitMessage1 = "commit msg 1";
		String commitMessage2 = "commit msg 2";
		assertArrayEquals(new String[] { commitMessage2, commitMessage1,
				commitMessage0 }, extractCommitMessages(log));
	}

	/**
	 * Test failure/special case of rebase
	 */
	@Test
	public void testRebaseSpecial() {
		String a = TESTING_DIR + "a.txt";
		String b = TESTING_DIR + "b.txt";
		createFile(a, "a");
		createFile(b, "b");
		gitlet("init");
		gitlet("add", a);
		gitlet("add", b);
		gitlet("commit", "master 1");
		gitlet("branch", "branch");
		gitlet("add", a);
		gitlet("commit", "master off branch 1");
		String error = gitlet("rebase", "branch");
		assertEquals("Already up-to-date.", error);
		gitlet("checkout", "branch");
		gitlet("rebase", "master");
		gitlet("status");
		String error2 = gitlet("rebase", "master");
		assertEquals("Cannot rebase a branch onto itself.", error2);
	}

	/**
	 * Test log branch switching
	 */
	@Test
	public void testLogBranch() {
		createFile(TESTING_DIR + "a.txt", "a");
		createFile(TESTING_DIR + "b.txt", "b");
		createFile(TESTING_DIR + "c.txt", "c");
		gitlet("init");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "first on master");
		gitlet("branch", "branch");
		writeFile(TESTING_DIR + "a.txt", "a master");
		writeFile(TESTING_DIR + "b.txt", "b master");
		writeFile(TESTING_DIR + "c.txt", "c master");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "master branch off");
		String log = gitlet("log");
		String commitMessage1 = "initial commit";
		String commitMessage2 = "first on master";
		String commitMessage3 = "master branch off";
		assertArrayEquals(new String[] { commitMessage3, commitMessage2,
				commitMessage1 }, extractCommitMessages(log));
		gitlet("checkout", "branch");
		writeFile(TESTING_DIR + "b.txt", "b branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "branch now off");
		String log2 = gitlet("log");
		String commitMessage4 = "initial commit";
		String commitMessage5 = "first on master";
		String commitMessage6 = "branch now off";
		assertArrayEquals(new String[] { commitMessage6, commitMessage5,
				commitMessage4 }, extractCommitMessages(log2));
		writeFile(TESTING_DIR + "b.txt", "b2 branch");
		writeFile(TESTING_DIR + "c.txt", "c2 branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "branch off 2");
		gitlet("branch", "branch again");
		writeFile(TESTING_DIR + "a.txt", "a2 branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("commit", "branch off 3");
		String log3 = gitlet("log");
		String commitMessage7 = "initial commit";
		String commitMessage8 = "first on master";
		String commitMessage9 = "branch now off";
		String commitMessage10 = "branch off 2";
		String commitMessage11 = "branch off 3";
		assertArrayEquals(new String[] { commitMessage11, commitMessage10,
				commitMessage9, commitMessage8, commitMessage7 },
				extractCommitMessages(log3));
		gitlet("checkout", "branch again");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("commit", "branch again off");
		String log4 = gitlet("log");
		String commitMessage12 = "initial commit";
		String commitMessage13 = "first on master";
		String commitMessage14 = "branch now off";
		String commitMessage15 = "branch off 2";
		String commitMessage16 = "branch again off";
		assertArrayEquals(new String[] { commitMessage16, commitMessage15,
				commitMessage14, commitMessage13, commitMessage12 },
				extractCommitMessages(log4));
	}

	/**
	 * Test global log
	 */
	@Test
	public void testGlobalLog() {
		createFile(TESTING_DIR + "a.txt", "a");
		createFile(TESTING_DIR + "b.txt", "b");
		createFile(TESTING_DIR + "c.txt", "c");
		gitlet("init");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "first on master");
		gitlet("branch", "branch");
		writeFile(TESTING_DIR + "a.txt", "a master");
		writeFile(TESTING_DIR + "b.txt", "b master");
		writeFile(TESTING_DIR + "c.txt", "c master");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "master branch off");
		gitlet("checkout", "branch");
		writeFile(TESTING_DIR + "b.txt", "b branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "branch now off");
		assertEquals("b branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("a", getText(TESTING_DIR + "a.txt"));
		assertEquals("c", getText(TESTING_DIR + "c.txt"));
		writeFile(TESTING_DIR + "b.txt", "b2 branch");
		writeFile(TESTING_DIR + "c.txt", "c2 branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("commit", "branch off 2");
		gitlet("branch", "branch again");
		writeFile(TESTING_DIR + "a.txt", "a2 branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("add", TESTING_DIR + "c.txt");
		gitlet("add", TESTING_DIR + "a.txt");
		gitlet("commit", "branch off 3");
		assertEquals("a2 branch", getText(TESTING_DIR + "a.txt"));
		assertEquals("b2 branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("c2 branch", getText(TESTING_DIR + "c.txt"));
		gitlet("checkout", "branch again");
		assertEquals("b2 branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("c2 branch", getText(TESTING_DIR + "c.txt"));
		assertEquals("a", getText(TESTING_DIR + "a.txt"));
		writeFile(TESTING_DIR + "b.txt", "b3 branch");
		gitlet("add", TESTING_DIR + "b.txt");
		gitlet("commit", "branch again off");
		assertEquals("b3 branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("c2 branch", getText(TESTING_DIR + "c.txt"));
		assertEquals("a", getText(TESTING_DIR + "a.txt"));
		gitlet("checkout", "branch");
		assertEquals("a2 branch", getText(TESTING_DIR + "a.txt"));
		assertEquals("b2 branch", getText(TESTING_DIR + "b.txt"));
		assertEquals("c2 branch", getText(TESTING_DIR + "c.txt"));
		gitlet("rebase", "master");
		String log = gitlet("global-log");
		String commitMessage0 = "initial commit";
		String commitMessage1 = "first on master";
		String commitMessage2 = "master branch off";
		String commitMessage3 = "branch now off";
		String commitMessage4 = "branch off 2";
		String commitMessage5 = "branch off 3";
		String commitMessage6 = "branch again off";
		String commitMessage7 = "branch now off";
		String commitMessage8 = "branch off 2";
		String commitMessage9 = "branch off 3";
		assertArrayEquals(
				new String[] { commitMessage0, commitMessage1, commitMessage2,
						commitMessage3, commitMessage4, commitMessage5,
						commitMessage6, commitMessage7, commitMessage8,
						commitMessage9 }, extractCommitMessages(log));
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