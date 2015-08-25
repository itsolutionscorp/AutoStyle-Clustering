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
	private static final String GITLET_DIR = ".gitlet" + File.separator;
	private static final String TESTING_DIR = "test_files" + File.separator;
	private static final String CONFLICT = ".conflicted";

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
	 * Tests that checking out a file name will restore the version of the file
	 * from specified commits. Involves init, add, commit, and checkout.
	 */
	@Test
	public void testCheckout1() {
		String fileName = TESTING_DIR + "a.txt";
		String text = "hello";
		createFile(fileName, text);
		gitlet("init");
		gitlet("add", fileName);
		gitlet("commit", "added a");

		writeFile(fileName, "goodbye");

		gitlet("add", fileName);
		gitlet("commit", "added a again");

		// checkout from Commit 1
		String[] ids = extractCommitIds("added a");
		gitlet("checkout", ids[0], fileName);
		assertEquals("hello", getText(fileName));

		// checkout from Commit 2
		ids = extractCommitIds("added a again");
		gitlet("checkout", ids[0], fileName);
		assertEquals("goodbye", getText(fileName));

	}

	/**
	 * Tests inheritance 
	 */
	@Test
	public void testCheckout2() {
		gitlet("init"); 
		String commitMessage0 = "initial commit";
		String fileName = TESTING_DIR + "a.txt";
		String text = "hello";
		createFile(fileName, text);
		gitlet("add", fileName);
		String commitMessage1 = "added a1";
		gitlet("commit", commitMessage1);

		String fileName2 = TESTING_DIR + "b.txt";
		String text2 = "goodbye";
		createFile(fileName2, text2);
		gitlet("add", fileName2);
		String commitMessage2 = "added b";
		gitlet("commit", commitMessage2);

		gitlet("checkout", fileName);
		assertEquals(text, getText(fileName));
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));
	}


	/**
	 * Check out file from a specific commit id 
	 */
	@Test
	public void testCheckout3() {
		gitlet("init"); 
		String fileName = TESTING_DIR + "a.txt";
		String text = "hello";
		createFile(fileName, text);
		gitlet("add", fileName);
		gitlet("commit", "added a");

		String fileName2 = TESTING_DIR + "b.txt";
		String text2 = "goodbye";
		createFile(fileName2, text2);
		gitlet("add", fileName2);
		writeFile(fileName, "bonjour");
		gitlet("add", fileName);
		gitlet("commit", "added b");

		gitlet("branch", "strawberry"); 
		String fileName3 = TESTING_DIR + "c.txt";
		String text3 = "see you later";
		createFile(fileName3, text3);
		gitlet("add", fileName3);
		gitlet("commit", "added c");

		String[] ids = extractCommitIds("added a");
		gitlet("checkout", ids[0], fileName);
		assertEquals("hello", getText(fileName));

		ids = extractCommitIds("added b");
		gitlet("checkout", ids[0], fileName);
		assertEquals("bonjour", getText(fileName)); // see if it gets overwritten 
		gitlet("checkout", ids[0], fileName2);
		assertEquals("goodbye", getText(fileName2));
	}

	/**
	 * Check out file from a specific commit ID from another branch 
	 * 
	 */ 
	@Test
	public void testCheckout4() {
		gitlet("init"); 
		String commitMessage0 = "initial commit";
		gitlet("branch", "strawberry"); 
		// we are in master branch 
		String fileName = TESTING_DIR + "a.txt";
		String text = "hello";
		createFile(fileName, text);
		gitlet("add", fileName);
		String commitMessage1 = "added a";
		gitlet("commit", commitMessage1);
		// master branch should contain a.txt with "hello" 

		gitlet("checkout", "strawberry");
		// we are in strawberry branch (has no files) 
		// current branch points to *strawberry 
		writeFile(fileName, "bonjour");
		gitlet("add", fileName);
		String commitMessage2 = "added a2";
		gitlet("commit", commitMessage2);

		gitlet("checkout","master");
		assertEquals("hello", getText(fileName));
		// tests log functionality 
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));

		gitlet("checkout", "strawberry");
		assertEquals("bonjour", getText(fileName));
		// tests log functionality 
		String logContent2 = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage0 },
				extractCommitMessages(logContent2)); 	

		// tests global-log functionality  
		String globalLog = gitlet("global-log");
		assertTrue(globalLog.contains(commitMessage0)); 
		assertTrue(globalLog.contains(commitMessage1)); 
		assertTrue(globalLog.contains(commitMessage2)); 
	}

	/**
	 * Tests all 3 implementations of checkout 
	 */
	@Test
	public void testCheckout5() {
		gitlet("init"); 
		String commitMessage0 = "initial commit";

		// make new branch strawberry 
		gitlet("branch", "strawberry"); 

		String fileName = TESTING_DIR + "a.txt";
		String text = "hello";
		createFile(fileName, text);
		gitlet("add", fileName);
		String commitMessage1 = "added a";
		gitlet("commit", commitMessage1);
		// master branch should contain a.txt with "hello" 

		gitlet("checkout", "strawberry");
		// we are in strawberry branch (has no files)
		// current branch points to *strawberry 
		writeFile(fileName, "bonjour");
		gitlet("add", fileName);
		String commitMessage2 = "added a2";
		gitlet("commit", commitMessage2);

		gitlet("find", "added a");
		String[] ids = extractCommitIds("added a");
		gitlet("checkout", ids[0], fileName);
		assertEquals("hello", getText(fileName));

		gitlet("checkout", fileName);
		assertEquals("bonjour", getText(fileName));
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
	 * Tests rm-branch functionality 
	 */
	@Test
	public void testRmBranch() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "A.txt";
		String wugText = "A";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added A";
		gitlet("commit", commitMessage2);

		//create branches
		gitlet("branch", "strawberry");
		gitlet("branch", "peach");

		gitlet("checkout", "peach");
		createFile(wugFileName, "A prime");
		gitlet("add", wugFileName);
		gitlet("commit", "second commit");

		gitlet("checkout", "master");

		//these should not print error message
		writeFile(wugFileName, "HELLO");
		gitlet("rm-branch", "strawberry");
		gitlet("checkout", "strawberry");
		assertEquals("HELLO", getText(wugFileName));

		gitlet("rm-branch", "peach");
		gitlet("checkout", "peach");
		assertEquals("HELLO", getText(wugFileName));
	}

	/**
	 * Test that resetting backward appropriately changes the output of log.
	 * Test that log adjusts appropriately when switching from one branch to another.
	 */
	@Test
	public void testReset() {
		gitlet("init");
		String commitMessage0 = "initial commit";
		String fileName1 = TESTING_DIR + "1.txt";
		String text1 = "1";
		createFile(fileName1, text1);
		gitlet("add", fileName1);
		String commitMessage1 = "added 1";
		gitlet("commit", commitMessage1);

		gitlet("branch", "strawberry"); 
		String fileName2 = TESTING_DIR + "2.txt";
		String text2 = "2";
		createFile(fileName2, text2);
		gitlet("add", fileName2);
		String commitMessage2 = "added 2";
		gitlet("commit", commitMessage2);

		gitlet("checkout", "strawberry"); 
		String fileName3 = TESTING_DIR + "3.txt";
		String text3 = "3";
		createFile(fileName3, text3);
		gitlet("add", fileName3);
		String commitMessage3 = "added 3";
		gitlet("commit", commitMessage3);

		String[] ids = extractCommitIds("initial commit");
		gitlet("reset", ids[0]);
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage0 },
				extractCommitMessages(logContent));

		ids = extractCommitIds(commitMessage1);
		gitlet("reset", ids[0]); 
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));

		ids = extractCommitIds(commitMessage2);
		gitlet("reset", ids[0]); 
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));

		ids = extractCommitIds(commitMessage3);
		gitlet("reset", ids[0]); 
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));

		// Test that log adjusts appropriately when switching from one branch to another.
		gitlet("checkout", "master"); 
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));
	}

	/**
	 * Test that merge will generate a .conflicted file if a file has been modified in
	 * both branches since the split point.
	 */ 
	@Test
	public void testMerge() {
		gitlet("init");
		gitlet("branch", "strawberry"); 
		// we are in master branch 
		String fileName = TESTING_DIR + "a.txt";
		String text = "hello";
		createFile(fileName, text);
		gitlet("add", fileName);
		gitlet("commit", "added a");
		// master branch should contain a.txt with "hello" 

		gitlet("checkout", "strawberry");
		// we are in strawberry branch (has no files) 
		// current branch points to *strawberry 
		writeFile(fileName, "bonjour");
		gitlet("add", fileName);
		gitlet("commit", "added a2");

		// merging current branch *strawberry with master branch 
		gitlet("merge", "master");
		File f = new File(fileName); 
		File f2 = new File(fileName + CONFLICT); 
		assertTrue(f.exists());
		assertTrue(f2.exists());

	}

	/**
	 * Test that merge will commit with files from the other branch if 
	 *   those files had been modified in the other branch but not in the 
	 *   current branch since the split point.
	 */ 
	@Test
	public void testMerge2() {
		gitlet("init"); 
		String fileName = TESTING_DIR + "a.txt";
		String text = "hello";
		createFile(fileName, text);
		gitlet("add", fileName);
		gitlet("commit", "added a");
		// master branch should contain a.txt with "hello" 

		// we make new branch strawberry but we are still in master branch 
		gitlet("branch", "strawberry"); 
		writeFile(fileName, "bonjour");
		gitlet("add", fileName);
		gitlet("commit", "added2");

		gitlet("checkout", "strawberry");
		assertEquals("hello", getText(fileName));
		// we are in strawberry branch (has no files) 
		// current branch points to *strawberry 
		gitlet("add", fileName);
		gitlet("commit", "added3");

		// merge current branch *strawberry with master branch 
		gitlet("merge", "master"); 
		gitlet("checkout", fileName);
		assertEquals("bonjour", getText(fileName));
	}

	/**
	 * Tests merge. No conflict, and no missing files.
	 */ 
	@Test
	public void testMerge3() {
		gitlet("init"); 
		String commitMessage0 = "initial commit";
		String fileNameA = TESTING_DIR + "A.txt";
		String fileNameB = TESTING_DIR + "B.txt";
		String fileNameC = TESTING_DIR + "C.txt";
		String textA = "A";
		String textB = "B";
		String textC = "C";
		createFile(fileNameA, textA);
		createFile(fileNameB, textB);
		createFile(fileNameC, textC);

		gitlet("add", fileNameA);
		gitlet("add", fileNameB);
		gitlet("add", fileNameC);
		gitlet("commit", "added1");

		// we make new branch strawberry but we are still in master branch 
		gitlet("branch", "strawberry"); 
		writeFile(fileNameC, "C prime");
		gitlet("add", fileNameA);
		gitlet("add", fileNameB);
		gitlet("add", fileNameC);
		gitlet("commit", "added2");

		gitlet("checkout", "strawberry");
		writeFile(fileNameA, "A prime");
		gitlet("add", fileNameA);
		gitlet("add", fileNameB);
		gitlet("add", fileNameC);
		gitlet("commit", "added3");

		writeFile(fileNameA, "A");
		writeFile(fileNameB, "B prime");
		gitlet("add", fileNameA);
		gitlet("add", fileNameB);
		gitlet("add", fileNameC);
		gitlet("commit", "added4");

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { "added4", "added3", "added1", "initial commit" },
				extractCommitMessages(logContent)); 

		// switch to master
		gitlet("checkout", "master");

		// merge current branch *master with strawberry branch 
		gitlet("merge", "strawberry"); 
		String logContent2 = gitlet("log");
		assertArrayEquals(new String[] { "Merged master with strawberry.", "added2", "added1", 
		"initial commit" }, extractCommitMessages(logContent2));

		gitlet("checkout", "strawberry");
		gitlet("checkout", "master");
		assertEquals("A", getText(fileNameA));
		assertEquals("B prime", getText(fileNameB));
		assertEquals("C prime", getText(fileNameC));

		// tests global-log functionality  
		String globalLog = gitlet("global-log");
		// tests global-log functionality  
		assertTrue(globalLog.contains("initial commit")); 
		assertTrue(globalLog.contains("added1")); 
		assertTrue(globalLog.contains("added2")); 
		assertTrue(globalLog.contains("added3")); 
		assertTrue(globalLog.contains("added4")); 
		assertTrue(globalLog.contains("Merged master with strawberry.")); 
	}

	/**
	 * Tests merge. Has conflicts, but no missing files.
	 */ 
	@Test
	public void testMerge4() {
		gitlet("init"); 
		String fileNameH = TESTING_DIR + "H.txt";
		String fileNameJ = TESTING_DIR + "J.txt";
		String textH = "H";
		String textJ = "J";
		createFile(fileNameH, textH);
		createFile(fileNameJ, textJ);

		gitlet("add", fileNameH);
		gitlet("add", fileNameJ);
		gitlet("commit", "H & J");

		// we make new branch strawberry but we are still in master branch 
		gitlet("branch", "strawberry"); 
		writeFile(fileNameH, "Hilfinger");
		writeFile(fileNameJ, "Joseph");
		gitlet("add", fileNameH);
		gitlet("add", fileNameJ);
		gitlet("commit", "Hilfinger");

		gitlet("checkout", "strawberry");
		writeFile(fileNameH, "Hilfinger");
		writeFile(fileNameJ, "Joseph\n Joseph");
		gitlet("add", fileNameH);
		gitlet("add", fileNameJ);
		gitlet("commit", "Joseph_&_Joseph");

		// switch to master
		gitlet("checkout", "master");

		// merge current branch *master with strawberry branch 
		gitlet("merge", "strawberry"); 
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { "Hilfinger", "H & J", "initial commit" },
				extractCommitMessages(logContent));
		File H = new File(fileNameH); 
		File H_conf = new File(fileNameH + CONFLICT); 
		File J = new File(fileNameJ); 
		File J_conf = new File(fileNameJ + CONFLICT); 
		assertTrue(H.exists());
		assertTrue(H_conf.exists());
		assertTrue(J.exists());
		assertTrue(J_conf.exists());
		assertEquals("Hilfinger", getText(fileNameH));
		assertEquals("Hilfinger", getText(fileNameH + CONFLICT));
		assertEquals("Joseph", getText(fileNameJ));
		assertEquals("Joseph\n Joseph", getText(fileNameJ + CONFLICT));
	}

	/**
	 * tests merge for missing files, under the assumption that 
	 * missing files from the split point is a modification
	 */ 
	@Test
	public void testMerge5() {
		gitlet("init");
		// we are in master branch 
		String fileNameA = TESTING_DIR + "A.txt";
		String textA = "A";
		String fileNameB = TESTING_DIR + "B.txt";
		String textB = "B";
		createFile(fileNameA, textA);
		createFile(fileNameB, textB);
		gitlet("add", fileNameA);
		gitlet("add", fileNameB);
		gitlet("commit", "This");

		//create new branch
		gitlet("branch", "given");

		//rm fileA from master branch and create two commits
		gitlet("rm", fileNameA);
		gitlet("commit", "class");
		gitlet("add", fileNameA);
		gitlet("commit", "rocks");

		//switch to given branch and rm B, create two commits
		gitlet("rm", fileNameB);
		createFile(fileNameA, "A prime");
		gitlet("commit", "is");
		createFile(fileNameA, "A prime prime");
		gitlet("add", fileNameA);
		gitlet("commit", "Sparta");

		//switch to master, and merge
		gitlet("checkout", "master");
		gitlet("merge", "given");
		gitlet("find", "Merged master with given.");

		//check if stuff has been changed
		gitlet("checkout", "given");
		writeFile(fileNameA, "aifjsd;fdjk");
		writeFile(fileNameB, "fs;djfksa.dlf");
		gitlet("checkout", "master");
		assertEquals(getText(fileNameA), "A prime prime");
		assertEquals(getText(fileNameB), "fs;djfksa.dlf");
	}

	/**
	 * Tests rebase. No missing files.
	 */ 
	@Test
	public void testRebase1() {
		gitlet("init"); 
		String fileNameA = TESTING_DIR + "A.txt";
		String fileNameB = TESTING_DIR + "B.txt";
		String fileNameC = TESTING_DIR + "C.txt";
		String fileNameD = TESTING_DIR + "D.txt";
		String fileNameE = TESTING_DIR + "E.txt";
		String fileNameP = TESTING_DIR + "P.txt"; // tests propagating
		String textA = "A";
		String textB = "B";
		String textC = "C";
		String textD = "D";
		String textE = "E";
		String textP = "P";
		createFile(fileNameA, textA);
		createFile(fileNameB, textB);
		createFile(fileNameC, textC);
		createFile(fileNameD, textD);
		createFile(fileNameE, textE);
		createFile(fileNameP, textP);

		gitlet("add", fileNameA);
		gitlet("add", fileNameB);
		gitlet("add", fileNameC);
		gitlet("add", fileNameD);
		gitlet("add", fileNameE);
		gitlet("add", fileNameP);
		gitlet("commit", "I");

		// we make new branch strawberry but we are still in master branch 
		gitlet("branch", "strawberry"); 
		writeFile(fileNameC, "C prime");
		writeFile(fileNameD, "D prime");
		writeFile(fileNameP, "P prime prime prime");
		gitlet("add", fileNameA);
		gitlet("add", fileNameB);
		gitlet("add", fileNameC);
		gitlet("add", fileNameD);
		gitlet("add", fileNameE);
		gitlet("add", fileNameP);
		gitlet("commit", "am");

		writeFile(fileNameE, "E prime");
		writeFile(fileNameP, "P prime prime prime prime");
		gitlet("add", fileNameA);
		gitlet("add", fileNameB);
		gitlet("add", fileNameC);
		gitlet("add", fileNameD);
		gitlet("add", fileNameE);
		gitlet("add", fileNameP);
		gitlet("commit", "from");

		// switch to the strawberry branch and create a split point
		gitlet("checkout", "strawberry");
		writeFile(fileNameC, "C prime");
		writeFile(fileNameD, "D prime");
		writeFile(fileNameP, "P prime");
		gitlet("add", fileNameA);
		gitlet("add", fileNameB);
		gitlet("add", fileNameC);
		gitlet("add", fileNameD);
		gitlet("add", fileNameE);
		gitlet("add", fileNameP);
		gitlet("commit", "UC / UM");

		writeFile(fileNameB, "B prime");
		writeFile(fileNameC, "C");
		writeFile(fileNameE, "E prime prime");
		writeFile(fileNameP, "P prime prime");
		gitlet("add", fileNameA);
		gitlet("add", fileNameB);
		gitlet("add", fileNameC);
		gitlet("add", fileNameD);
		gitlet("add", fileNameE);
		gitlet("add", fileNameP);
		gitlet("commit", "Berkeley / Ann Arbor");

		// rebase the current branch *strawberry to the master branch 
		System.out.println("fejwailfe");
		gitlet("rebase", "master"); 
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { "Berkeley / Ann Arbor", "UC / UM", "from", "am", "I", 
		"initial commit" }, extractCommitMessages(logContent));

		// reset
		String[] ids = extractCommitIds("UC / UM");
		gitlet("reset", ids[1]); 
		assertEquals("A", getText(fileNameA));
		assertEquals("B", getText(fileNameB));
		assertEquals("C prime", getText(fileNameC));
		assertEquals("D prime", getText(fileNameD));
		assertEquals("E prime", getText(fileNameE));
		assertEquals("P prime", getText(fileNameP));

		// reset
		ids = extractCommitIds("Berkeley / Ann Arbor");
		gitlet("reset", ids[1]);
		assertEquals("A", getText(fileNameA));
		assertEquals("B prime", getText(fileNameB));
		assertEquals("C", getText(fileNameC));
		assertEquals("D prime", getText(fileNameD));
		assertEquals("E prime prime", getText(fileNameE));
		assertEquals("P prime prime", getText(fileNameP));
	}

	/**
	 * Tests rebase. Has missing files.
	 */ 
	@Test
	public void testRebase2() {
		gitlet("init"); 
		String fileNameA = TESTING_DIR + "A.txt";
		String fileNameB = TESTING_DIR + "B.txt";
		String fileNameC = TESTING_DIR + "C.txt";
		String fileNameD = TESTING_DIR + "D.txt";
		String fileNameE = TESTING_DIR + "E.txt";
		String textA = "A";
		String textB = "B";
		String textC = "C";
		String textD = "D";
		String textE = "E";
		createFile(fileNameA, textA);
		createFile(fileNameB, textB);
		createFile(fileNameC, textC);
		createFile(fileNameD, textD);
		createFile(fileNameE, textE);

		gitlet("add", fileNameA);
		gitlet("add", fileNameB);
		gitlet("add", fileNameC);
		gitlet("add", fileNameD);
		gitlet("add", fileNameE);
		gitlet("commit", "I");

		// we make new branch strawberry but we are still in master branch 
		gitlet("branch", "strawberry"); 
		writeFile(fileNameB, "B prime");
		gitlet("add", fileNameA);
		gitlet("add", fileNameB);
		gitlet("rm", fileNameC);
		gitlet("rm", fileNameD);
		gitlet("rm", fileNameE);
		gitlet("commit", "love");

		// switch to the strawberry branch and create a split point
		gitlet("checkout", "strawberry");
		writeFile(fileNameD, "D prime");
		gitlet("rm", fileNameA);
		gitlet("rm", fileNameB);
		gitlet("add", fileNameC);
		gitlet("add", fileNameD);
		gitlet("rm", fileNameE);
		gitlet("commit", "this course");

		//change back to D to check if rebase head has D'
		writeFile(fileNameD, "D");
		// rebase the current branch *strawberry to the master branch 
		gitlet("rebase", "master"); 
		String logContent = gitlet("log");
		System.out.println(logContent);
		assertArrayEquals(new String[] { "this course", "love", "I", 
		"initial commit" }, extractCommitMessages(logContent));

		// checkout
		// in order to check that the commit "this course" does not contain A, B, C, or E,
		// first change the files and then checkout from the commit "this course"
		writeFile(fileNameA, "totally changed");
		writeFile(fileNameB, "totally changed as well");
		writeFile(fileNameC, "totally changed too");
		writeFile(fileNameD, "totally changed, yes");
		writeFile(fileNameE, "totally changed......");

		String[] ids = extractCommitIds("this course");
		gitlet("reset", ids[1]);
		assertEquals("totally changed", getText(fileNameA));
		assertEquals("totally changed as well", getText(fileNameB));
		assertEquals("totally changed too", getText(fileNameC));
		assertEquals("D prime", getText(fileNameD));
		assertEquals("totally changed......", getText(fileNameE));
	}

	/**
	 * Tests rebase when the current branch is in the other branch's history 
	 */
	@Test
	public void testRebase3() {

		gitlet("init"); 
		String fileNameA = TESTING_DIR + "A.txt";
		String fileNameB = TESTING_DIR + "B.txt";
		String textA = "A";
		String textB = "B";
		createFile(fileNameA, textA);
		createFile(fileNameB, textB);

		gitlet("add", fileNameA);
		gitlet("commit", "I");

		gitlet("add", fileNameB);
		gitlet("commit", "am");


		// we make new branch strawberry but we are still in master branch 
		gitlet("branch", "strawberry"); 
		writeFile(fileNameB, "B prime");
		gitlet("add", fileNameA);
		gitlet("add", fileNameB);
		gitlet("commit", "Arnav");

		// switch to the strawberry branch and create a split point

		gitlet("rm", fileNameA);
		gitlet("commit", "Vaid");

		gitlet("checkout", "strawberry"); 
		// the addition to the staging area shouldn't be effected by rebase
		// removed files shoudn't be affected either
		writeFile(fileNameA, "AOJ");
		gitlet("add", fileNameA);
		gitlet("rm", fileNameB);
		gitlet("rebase", "master");

		gitlet("checkout", "master");
		// to check that the checkout returns B prime
		writeFile(fileNameB, "BBIB");
		gitlet("checkout", "strawberry");
		assertEquals("B prime", getText(fileNameB));

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { "Vaid", "Arnav", "am", "I", 
		"initial commit" }, extractCommitMessages(logContent));

		// commit
		gitlet("commit", "check staging area and removed files are not affected by rebase");
		gitlet("checkout", "master");
		// in order to check that the commit is empty, first totally change A and B, then checkout
		writeFile(fileNameA, "iecgmehwgehn");
		writeFile(fileNameB, "cwekhcj");
		gitlet("checkout", "strawberry");
		assertEquals("AOJ", getText(fileNameA));
		assertEquals("cwekhcj", getText(fileNameB));

	}

	/**
	 * Tests that rebase only copies one branch and not others that may point to the split point. Also tests propegation for long lists
	 */
	@Test
	public void testRebase4() {
		gitlet("init"); 
		String fileNameA = TESTING_DIR + "A.txt";
		String fileNameB = TESTING_DIR + "B.txt";
		String textA = "A";
		String textB = "B";
		createFile(fileNameA, textA);
		createFile(fileNameB, textB);

		gitlet("add", fileNameA);
		gitlet("add", fileNameB);
		gitlet("commit", "I");


		// we make new branches strawberry and grape but we are still in master branch 
		gitlet("branch", "strawberry"); 
		gitlet("branch", "grape");

		//make another commit on master
		writeFile(fileNameA, "A prime");
		writeFile(fileNameB, "B prime");
		gitlet("add", fileNameA);
		gitlet("add", fileNameB);
		gitlet("commit", "Arnav");

		// switch to the strawberry branch and create a commit
		gitlet("checkout", "strawberry");
		gitlet("rm", fileNameA);
		gitlet("rm", fileNameB);
		gitlet("commit", "Vaid");

		// switch to the grape branch and create three commits
		gitlet("checkout", "grape");
		writeFile(fileNameA, "A");
		writeFile(fileNameB, "B");
		gitlet("add", fileNameA);
		gitlet("add", fileNameB);
		gitlet("commit", "CS");

		writeFile(fileNameB, "B prime prime");
		gitlet("add", fileNameB);
		gitlet("commit", "61bl");

		writeFile(fileNameA, "A prime prime");
		gitlet("add", fileNameA);
		gitlet("commit", "Hi");

		//rebase
		gitletFast("rebase", "master");

		//Checkout commits to see if the correct files are there

		//First check if master has right files
		// to check that the checkout returns B prime
		writeFile(fileNameB, "FEHDSLF>HSD");
		writeFile(fileNameA, "D:OSHJDSNCDS");
		gitlet("checkout", "master");
		assertEquals("A prime", getText(fileNameA));
		assertEquals("B prime", getText(fileNameB));

		//then check if strawberry doesnt have any files
		writeFile(fileNameB, "FEHDSLF>HSD");
		writeFile(fileNameA, "D:OSHJDSNCDS");
		gitlet("checkout", "strawberry");
		assertEquals("FEHDSLF>HSD", getText(fileNameB));
		assertEquals("D:OSHJDSNCDS", getText(fileNameA));

		// then if grape has correct files
		gitlet("checkout", "grape");
		assertEquals("A prime prime", getText(fileNameA));
		assertEquals("B prime prime", getText(fileNameB));

		// Now check other commits: 
		// "CS" should have A prime and B prime
		// "61bl" should have A prime and B prime prime

		writeFile(fileNameB, "FEHDSLF>HSD");
		writeFile(fileNameA, "D:OSHJDSNCDS");
		gitlet("find", "CS");
		gitlet("find", "Hi");
		gitlet("find", "61bl");
		String[] ids = extractCommitIds("CS");
		gitlet("reset", ids[1]); // CS rebased
		assertEquals("A prime", getText(fileNameA));
		assertEquals("B prime", getText(fileNameB));

		writeFile(fileNameB, "FEHDSLF>HSD");
		writeFile(fileNameA, "D:OSHJDSNCDS");
		ids = extractCommitIds("61bl");
		gitlet("reset", ids[1]); //61bl rebased
		assertEquals("A prime", getText(fileNameA));
		assertEquals("B prime prime", getText(fileNameB));

		//check log from grape
		ids = extractCommitIds("Hi");
		gitlet("reset", ids[1]); // Hi rebased
		gitlet("checkout", "grape");
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { "Hi", "61bl", "CS", "Arnav", "I", 
		"initial commit" }, extractCommitMessages(logContent));
	}

	/**
	 *  Tests variations on modifications and propagation 
	 */
	@Test
	public void testRebase5() {
		gitlet("init");
		for (int i = 1; i <= 13; i++) {
			createFile(TESTING_DIR + i + ".txt", "number " + i);
		}
		for (int i = 1; i <= 10; i++) {
			gitlet("add", TESTING_DIR + i + ".txt");
		}
		gitlet("commit", "corgis");

		// make a new branch strawberry but still stay in master
		gitlet("branch", "strawberry");

		writeFile(TESTING_DIR + "3.txt", "number 3 prime");
		writeFile(TESTING_DIR + "4.txt", "number 4 prime");
		writeFile(TESTING_DIR + "5.txt", "number 5 prime");
		writeFile(TESTING_DIR + "7.txt", "number 7 prime");
		writeFile(TESTING_DIR + "13.txt", "number 13 prime");
		gitlet("add", TESTING_DIR + "3.txt");
		gitlet("add", TESTING_DIR + "4.txt");
		gitlet("add", TESTING_DIR + "5.txt");
		gitlet("add", TESTING_DIR + "7.txt");
		for (int i = 8; i <= 10; i++) {
			gitlet("rm", TESTING_DIR + i + ".txt");
		}
		gitlet("add", TESTING_DIR + "12.txt");
		gitlet("add", TESTING_DIR + "13.txt");
		gitlet("commit", "are");

		gitlet("checkout", "strawberry");

		writeFile(TESTING_DIR + "2.txt", "number 2 prime");
		writeFile(TESTING_DIR + "4.txt", "number 4 prime");
		writeFile(TESTING_DIR + "5.txt", "number 5 prime prime");
		writeFile(TESTING_DIR + "9.txt", "number 9 prime");
		writeFile(TESTING_DIR + "13.txt", "number 13 prime prime");
		gitlet("add", TESTING_DIR + "2.txt");
		gitlet("add", TESTING_DIR + "4.txt");
		gitlet("add", TESTING_DIR + "5.txt");
		gitlet("rm", TESTING_DIR + "6.txt");
		gitlet("rm", TESTING_DIR + "7.txt");
		gitlet("add", TESTING_DIR + "9.txt");
		gitlet("rm", TESTING_DIR + "10.txt");	
		gitlet("add", TESTING_DIR + "11.txt");
		gitlet("add", TESTING_DIR + "12.txt");
		gitlet("add", TESTING_DIR + "13.txt");
		gitlet("commit", "the"); // this commit will test variations on modifications after rebasing

		for (int i = 1; i <= 10; i++) {
			writeFile(TESTING_DIR + i + ".txt", "number " + i);
			gitlet("add", TESTING_DIR + i + ".txt");
		}
		gitlet("commit", "best"); // this commit will test propagation after rebasing

		gitletFast("rebase", "master");

		//change the contents of the working directory before resetting
		for (int i = 1; i <= 13; i++) {
			writeFile(TESTING_DIR + i + ".txt", "changed " + i);
		}  
		String[] ids = extractCommitIds("the");
		gitlet("reset", ids[1]); // the rebased commit with message "the"
		assertEquals("number 1", getText(TESTING_DIR + "1.txt"));
		assertEquals("number 2 prime", getText(TESTING_DIR + "2.txt"));
		assertEquals("number 3 prime", getText(TESTING_DIR + "3.txt"));
		assertEquals("number 4 prime", getText(TESTING_DIR + "4.txt"));
		assertEquals("number 5 prime prime", getText(TESTING_DIR + "5.txt"));
		assertEquals("changed 6", getText(TESTING_DIR + "6.txt"));
		assertEquals("changed 7", getText(TESTING_DIR + "7.txt"));
		assertEquals("changed 8", getText(TESTING_DIR + "8.txt"));
		assertEquals("number 9 prime", getText(TESTING_DIR + "9.txt"));
		assertEquals("changed 10", getText(TESTING_DIR + "10.txt"));
		assertEquals("number 11", getText(TESTING_DIR + "11.txt"));
		assertEquals("number 12", getText(TESTING_DIR + "12.txt"));
		assertEquals("number 13 prime prime", getText(TESTING_DIR + "13.txt"));

		//change the contents of the working directory before resetting again
		for (int i = 1; i <= 13; i++) {
			writeFile(TESTING_DIR + i + ".txt", "changed " + i + " again");
		}
		String[] ids2 = extractCommitIds("best");
		gitlet("reset", ids2[1]); // the rebased commit with message "best"
		assertEquals("number 1", getText(TESTING_DIR + "1.txt"));
		assertEquals("number 2", getText(TESTING_DIR + "2.txt"));
		assertEquals("number 3 prime", getText(TESTING_DIR + "3.txt"));
		assertEquals("number 4", getText(TESTING_DIR + "4.txt"));
		assertEquals("number 5", getText(TESTING_DIR + "5.txt"));
		assertEquals("number 6", getText(TESTING_DIR + "6.txt"));
		// assertEquals("number 7", getText(TESTING_DIR + "7.txt"));
		assertEquals("changed 8 again", getText(TESTING_DIR + "8.txt"));
		assertEquals("number 9", getText(TESTING_DIR + "9.txt"));
		assertEquals("changed 10 again", getText(TESTING_DIR + "10.txt"));
		assertEquals("number 11", getText(TESTING_DIR + "11.txt"));
		assertEquals("number 12", getText(TESTING_DIR + "12.txt"));
		// assertEquals("number 13", getText(TESTING_DIR + "13.txt"));
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { "best", "the", "are", "corgis", "initial commit"},
				extractCommitMessages(logContent));
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
	 * A convenience method that acts like gitletFast but doesn't print anything
	 */
	private static String gitletFast2(String... args) {
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
	 * Returns an array of the commit ids of commits with the given message
	 * as strings in sequential order with the oldest commit first.
	 */
	private static String[] extractCommitIds(String msg) {
		String findOutput = gitletFast2("find", msg);
		String[] ids = findOutput.split(LINE_SEPARATOR);
		return ids;
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
