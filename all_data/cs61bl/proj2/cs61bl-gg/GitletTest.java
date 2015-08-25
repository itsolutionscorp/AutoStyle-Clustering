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
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

/**
 * Class that provides JUnit tests for Gitlet, as well as a couple of utility
 * methods.
 * 
 * @author Ignatius Ho, Madeline Wu, Tailai Lu
 * 			
 * 		   Adapted from Joseph Moghadam's GitletTest.java provided on proj2 webpage.
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
	 * Tests that init creates a .gitlet directory. Tests init
	 * creates an initial commit folder Commit-0.  Tests that Commit-0 is 
	 * an empty directory. Involves init, commit, log.
	 */
	@Test
	public void testInitialize() {
		gitlet("init");
		File f = new File(GITLET_DIR);
		assertTrue(f.exists());
		File commit0 = new File(GITLET_DIR + "/Commit-0/");
		assertTrue(commit0.exists());
		assertTrue(commit0.isDirectory());
		assertTrue(commit0.list().length ==0);
		String commitMessage0 = "initial commit";
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage0 },
				extractCommitMessages(logContent));
		String errorInit = gitlet("init");
		assertEquals("A gitlet version control system already exists in the "
				+ "current directory.", errorInit);

	}

	/**
	 * Tests that branch creates a new branch.
	 */
	@Test
	public void testBranch() {
		gitlet("init");
		gitlet("branch", "branch");
		String idBranch = gitlet("testBranch", "branch");
		assertEquals("0", idBranch);
		String error1 = gitlet("branch", "branch");
		String existingErrorMessage = "A branch with that name already exists.";
		assertEquals(error1, existingErrorMessage);
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "hi wug!";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "wug, on master branch");
		idBranch = gitlet("testBranch", "branch");
		assertEquals("0", idBranch);
		String idMaster = gitlet("testBranch", "master");
		assertEquals("1", idMaster);
		gitlet("checkout", "branch");
		gitlet("add", wugFileName);
		gitlet("commit", "nothing to commit, on branch branch");
		idBranch = gitlet("testBranch", "branch");
		assertEquals("2", idBranch);
		gitlet("branch", "second_branch");
		String idSecondBranch = gitlet("testBranch", "second_branch");
		assertEquals("2", idSecondBranch);


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

	@Test
	public void testMergeDNE() {
		gitlet("init"); 
		String wugOne = TESTING_DIR + "wug.txt"; 
		String wugTwo = TESTING_DIR + "wug2.txt"; 
		String wugOneText = "wug1"; 
		String wugTwoText = "wug2"; 
		createFile(wugOne, wugOneText); 
		createFile(wugTwo, wugTwoText); 
		gitlet("add", wugOne); 
		gitlet("add", wugTwo); 
		gitlet("commit", "Added both files");
		gitlet("branch", "branch1"); 
		gitlet("checkout", "branch1"); 
		writeFile(wugOne, "wug1 is on branch1"); 
		gitlet("add", wugOne); 
		gitlet("commit","2"); 
		String error = gitlet("merge", "branch2");
		assertEquals(error, "A branch with that name does not exist.");
	}
	
	@Test
	public void testMergeSelf() {
		gitlet("init"); 
		String wugOne = TESTING_DIR + "wug.txt"; 
		String wugTwo = TESTING_DIR + "wug2.txt"; 
		String wugOneText = "wug1"; 
		String wugTwoText = "wug2"; 
		createFile(wugOne, wugOneText); 
		createFile(wugTwo, wugTwoText); 
		gitlet("add", wugOne); 
		gitlet("add", wugTwo); 
		gitlet("commit", "Added both files");
		gitlet("branch", "branch1"); 
		gitlet("checkout", "branch1"); 
		writeFile(wugOne, "wug1 is on branch1"); 
		gitlet("add", wugOne); 
		gitlet("commit","2"); 
		String error = gitlet("merge", "branch1");
		assertEquals(error, "Cannot merge a branch with itself.");
	}
	
	/**
	 * No changes to files, no merge conflicts
	 * Merge master with branch1. Master has no updated files.
	 */
	@Test
	public void testMerge1() {
		gitlet("init"); 
		String wugOne = TESTING_DIR + "wug.txt"; 
		String wugTwo = TESTING_DIR + "wug2.txt"; 
		String wugOneText = "wug1"; 
		String wugTwoText = "wug2"; 
		createFile(wugOne, wugOneText); 
		createFile(wugTwo, wugTwoText); 
		gitlet("add", wugOne); 
		gitlet("add", wugTwo); 
		gitlet("commit", "Added both files");
		gitlet("branch", "branch1"); 
		gitlet("checkout", "branch1"); 
		writeFile(wugOne, "wug1 is on branch1"); 
		gitlet("add", wugOne); 
		gitlet("commit","2"); 
		gitlet("checkout", "master");
		assertEquals(getText(wugOne), "wug1");
		gitlet("add", wugOne);
		gitlet("add", wugTwo);
		gitlet("commit", "3");
		gitlet("checkout", "branch1");
		String noChange = gitlet("merge", "master");
		assertEquals(noChange, "No changes added to the commit.");
		assertEquals(getText(wugOne), "wug1 is on branch1");
		assertEquals(getText(wugTwo), "wug2");

		
	}
	
	/**
	 * Updated files, no merge conflicts
	 * Merge branch1 with master. Branch1 has no updated files, master does.
	 */
	@Test
	public void testMerge2() {
		gitlet("init"); 
		String wugOne = TESTING_DIR + "wug.txt"; 
		String wugTwo = TESTING_DIR + "wug2.txt"; 
		String wugOneText = "wug1"; 
		String wugTwoText = "wug2"; 
		createFile(wugOne, wugOneText); 
		createFile(wugTwo, wugTwoText); 
		gitlet("add", wugOne); 
		gitlet("add", wugTwo); 
		gitlet("commit", "Added both files");
		gitlet("branch", "branch1"); 
		gitlet("checkout", "branch1"); 
		writeFile(wugOne, "wug1 is on branch1"); 
		gitlet("add", wugOne); 
		gitlet("commit","2"); 
		gitlet("checkout", "master");
		assertEquals(getText(wugOne), "wug1");
		gitlet("add", wugOne);
		gitlet("add", wugTwo);
		gitlet("commit", "3");
		gitlet("merge", "branch1");
		assertEquals(getText(wugOne), "wug1 is on branch1");
		assertEquals(getText(wugTwo), "wug2");	
		String log = gitlet("log");
		String [] commitMessages = extractCommitMessages(log);
		String mergedSuccess = "Merged master with branch1.";
		assertTrue(Arrays.asList(commitMessages).contains(mergedSuccess));
	}
	
	@Test
	public void testMergeUntrackedToTracked() {
		gitlet("init");
		String wugFile = TESTING_DIR + "wug.txt";
		String mugFile = TESTING_DIR + "mug.txt"; 
		
		String wug1 = "wug";
		String mug1 = "mug";
		String wug2 = "branch wug";
		String wug3 = "master wug";
		String mug2 = "branch mug";
		
		createFile(wugFile, wug1);
		createFile(mugFile, mug1);
		gitlet("add", wugFile);
		gitlet("add", mugFile);
		gitlet("commit", "1");
		gitlet("branch", "branch");
		gitlet("checkout", "branch");
		writeFile(wugFile, wug2);
		writeFile(mugFile, mug2);
		gitlet("add", wugFile);
		gitlet("add", mugFile);
		gitlet("commit", "2");
		gitlet("checkout", "master");
		gitlet("add", wugFile);
		gitlet("commit", "3");
		gitlet("add", wugFile);
		gitlet("commit", "4");
		gitlet("merge", "branch");
		String commit5 = GITLET_DIR + "/Commit-5/";
		String content5 = getText(commit5 + wugFile);
		assertEquals(content5, "branch wug");
		File wug5 = new File(GITLET_DIR + "/Commit-5/" + mugFile);
		assertTrue(wug5.exists());
		content5 = getText(commit5 + mugFile);
		assertEquals(content5, "branch mug");
	}
	
	@Test
	public void testMergeTrackedToUntracked() {
		gitlet("init");
		String wugFile = TESTING_DIR + "wug.txt";
		String mugFile = TESTING_DIR + "mug.txt"; 
		
		String wug1 = "wug";
		String mug1 = "mug";
		String wug2 = "branch wug";
		String wug3 = "master wug";
		String mug2 = "branch mug";
		
		createFile(wugFile, wug1);
		createFile(mugFile, mug1);
		gitlet("add", wugFile);
		gitlet("add", mugFile);
		gitlet("commit", "1");
		gitlet("branch", "branch");
		gitlet("checkout", "branch");
		writeFile(mugFile, mug2);
		gitlet("add", wugFile);
		gitlet("add", mugFile);
		gitlet("commit", "2");
		gitlet("checkout", "master");
		writeFile(wugFile, wug3);
		gitlet("add", wugFile);
		gitlet("commit", "3");
		gitlet("add", wugFile);
		gitlet("commit", "4");
		gitlet("checkout", "branch");
		gitlet("merge", "master");
		String commit5 = GITLET_DIR + "/Commit-5/";
		String content5 = getText(commit5 + wugFile);
		assertEquals(content5, "master wug");
		File wug5 = new File(GITLET_DIR + "/Commit-5/" + mugFile);
		assertTrue(wug5.exists());
	}
	
	@Test
	public void testMergeAllowedCommands() {
		gitlet("init"); 
		String wugOne = TESTING_DIR + "wug.txt"; 
		String wugTwo = TESTING_DIR + "wug2.txt"; 
		String wugOneText = "wug1"; 
		String wugTwoText = "wug2"; 
		createFile(wugOne, wugOneText); 
		createFile(wugTwo, wugTwoText); 
		gitlet("add", wugOne); 
		gitlet("add", wugTwo); 
		gitlet("commit", "Added both files");
		gitlet("branch", "branch1"); 
		gitlet("checkout", "branch1"); 
		writeFile(wugOne, "wug1 is on branch1"); 
		gitlet("add", wugOne); 
		gitlet("commit","2"); 
		gitlet("checkout", "master");
		assertEquals(getText(wugOne), "wug1");
		writeFile(wugOne, "wug1 is on master"); 
		gitlet("add", wugOne);
		gitlet("add", wugTwo);
		gitlet("commit", "3");
		String conflict = gitlet("merge", "branch1");
		assertEquals(conflict, "Encountered a merge conflict.");
		String [] notAllowed= {"rm-branch", "branch", "reset", "rebase", "merge"};
		String error;
		for (String s:notAllowed) {
			 error = gitlet(s, "branch1");
			 assertEquals(error, "Cannot do this command until the merge conflict has been resolved.");
		}
		error = gitlet("checkout", "branch1");
		assertEquals(error, "Cannot do this command until the merge conflict has been resolved.");
		
	}

	@Test
	public void testRebaseModified() {
		gitlet("init"); 
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugtext1 = "This is a wug";
		String wugtext2 = "This is a new wug";
		createFile(wugFileName, wugtext1);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "1");
		gitlet("branch", "branch");
		gitlet("checkout", "branch");
		gitlet("add", wugFileName);
		gitlet("commit", "2");
		gitlet("checkout", "master");
		writeFile(wugFileName, wugtext2);
		gitlet("add", wugFileName);
		gitlet("commit", "3");
		gitlet("checkout", "branch");
		gitlet("rebase", "master");

		assertEquals(wugtext2, getText(wugFileName));
		String result1 = gitlet("getFileText", "2");
		assertEquals(result1, "This is a wug");
		String result2 = gitlet("getFileText", "4");
		assertEquals(result2, "This is a new wug");
	}

	@Test
	public void testRebaseModified2() {
		gitlet("init"); 
		String wugFileName = TESTING_DIR + "wug.txt";
		String hugFileName = TESTING_DIR + "hug.txt";

		String wugtext1 = "This is a wug";
		String wugtext2 = "This is a new wug";
		String hugtext1 = "This is a hug";
		String hugtext2 = "This is a new hug";
		createFile(wugFileName, wugtext1);
		createFile(hugFileName, hugtext1);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("add", hugFileName);
		gitlet("commit", "1");
		gitlet("branch", "branch");
		gitlet("checkout", "branch");
		gitlet("add", wugFileName);
		gitlet("add", hugFileName);
		gitlet("commit", "2");
		gitlet("checkout", "master");
		writeFile(wugFileName, wugtext2);
		writeFile(hugFileName, hugtext2);
		gitlet("add", wugFileName);
		gitlet("add", hugFileName);
		gitlet("commit", "3");
		gitlet("checkout", "branch");
		gitlet("rebase", "master");

		assertEquals(wugtext2, getText(wugFileName));
		String result1 = gitlet("getFileText", "2");
		assertEquals(result1, "This is a wug\nThis is a hug");
		String result2 = gitlet("getFileText", "4");
		assertEquals(result2, "This is a new wug\nThis is a new hug");
	}

	@Test
	public void testRebaseModified3() {
		gitlet("init"); 
		String wugFileName = TESTING_DIR + "wug.txt";
		String hugFileName = TESTING_DIR + "hug.txt";

		String wugtext1 = "This is a wug";
		String wugtext2 = "This is a new wug";
		String hugtext1 = "This is a hug";
		String hugtext2 = "This is a new hug";
		createFile(wugFileName, wugtext1);
		createFile(hugFileName, hugtext1);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("add", hugFileName);
		gitlet("commit", "1");
		gitlet("branch", "branch");
		gitlet("checkout", "branch");
		writeFile(wugFileName, wugtext2);
		writeFile(hugFileName, hugtext2);
		gitlet("add", wugFileName);
		gitlet("add", hugFileName);
		gitlet("commit", "2");
		gitlet("checkout", "master");
		gitlet("add", wugFileName);
		gitlet("add", hugFileName);
		gitlet("commit", "3");
		gitlet("checkout", "branch");
		gitlet("rebase", "master");

		assertEquals(wugtext2, getText(wugFileName));
		String result1 = gitlet("getFileText", "3");
		assertEquals(result1, "This is a wug\nThis is a hug");
		String result2 = gitlet("getFileText", "4");
		assertEquals(result2, "This is a new wug\nThis is a new hug");
	}

	@Test
	public void testRebaseModified4() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String hugFileName = TESTING_DIR + "hug.txt";

		String wugtext1 = "This is a wug";
		String wugtext2 = "This is a new wug";
		String hugtext1 = "This is a hug";
		String hugtext2 = "This is a new hug";
		createFile(wugFileName, wugtext1);
		createFile(hugFileName, hugtext1);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("add", hugFileName);
		gitlet("commit", "1");
		gitlet("branch", "branch");
		gitlet("checkout", "branch");
		writeFile(wugFileName, wugtext2);
		gitlet("add", wugFileName);
		gitlet("commit", "2");
		gitlet("checkout", "master");
		writeFile(hugFileName, hugtext2);
		gitlet("add", hugFileName);
		gitlet("commit", "3");
		gitlet("checkout", "branch");
		gitlet("rebase", "master");

		assertEquals(wugtext2, getText(wugFileName));
		String result2 = gitlet("getFileText", "2");
		assertEquals(result2, "This is a new wug\nThis is a hug");
		String result3 = gitlet("getFileText", "3");
		assertEquals(result3, "This is a new hug\nThis is a wug");
		String result4 = gitlet("getFileText", "4");
		assertEquals(result4, "This is a new wug\nThis is a new hug");
	}


	@Test
	public void testRebaseModified5() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String hugFileName = TESTING_DIR + "hug.txt";
		String mugFileName = TESTING_DIR + "mug.txt";

		String wugtext1 = "This is a wug";
		String wugtext2 = "This is a new wug";
		String hugtext1 = "This is a hug";
		String hugtext2 = "This is a new hug";
		String mugtext1 = "This is a mug";
		String mugtext2 = "This is a new mug";
		createFile(wugFileName, wugtext1);
		createFile(hugFileName, hugtext1);
		createFile(mugFileName, mugtext1);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("add", hugFileName);
		gitlet("add", mugFileName);
		gitlet("commit", "1");
		gitlet("branch", "branch");
		gitlet("checkout", "branch");
		writeFile(mugFileName, mugtext2);
		gitlet("add", mugFileName);
		gitlet("commit", "2");
		gitlet("checkout", "master");

		writeFile(wugFileName, wugtext2);
		writeFile(hugFileName, hugtext2);
		gitlet("add", wugFileName);
		gitlet("add", hugFileName);
		gitlet("commit", "3");
		gitlet("checkout", "branch");
		gitlet("rebase", "master");

		assertEquals(wugtext2, getText(wugFileName));
		assertEquals(mugtext2, getText(mugFileName));
		assertEquals(hugtext2, getText(hugFileName));
		String logContent = gitlet("log");
		String[] allCommitMessages = extractCommitMessages(logContent);
		assertTrue(allCommitMessages.length == 4);
		String[] branch = {"2", "3", "1", "initial commit"};
		assertEquals(allCommitMessages, branch);

		String result1 = gitlet("getFileText", "2");
		assertEquals(result1, "This is a new mug\nThis is a wug\nThis is a hug");
		String result2 = gitlet("getFileText", "4");
		assertEquals(result2, "This is a new mug\nThis is a new wug\nThis is a new hug");
	}
	
	@Test
	public void testRebaseUntracked() {
		gitlet("init");
		String wugFile = TESTING_DIR + "wug.txt";
		String mugFile = TESTING_DIR + "mug.txt"; 
		
		String wug1 = "wug";
		String mug1 = "mug";
		String wug2 = "branch wug";
		String wug3 = "master wug";
		String mug2 = "branch mug";
		
		createFile(wugFile, wug1);
		createFile(mugFile, mug1);
		gitlet("add", wugFile);
		gitlet("add", mugFile);
		gitlet("commit", "1");
		gitlet("branch", "branch");
		gitlet("checkout", "branch");
		writeFile(wugFile, wug2);
		writeFile(mugFile, mug2);
		gitlet("add", wugFile);
		gitlet("add", mugFile);
		gitlet("commit", "2");
		gitlet("checkout", "master");
		gitlet("add", wugFile);
		gitlet("commit", "3");
		writeFile(wugFile, wug3);
		gitlet("add", wugFile);
		gitlet("commit", "4");
		gitlet("checkout", "branch");
		gitlet("rebase", "master");
		String commit5 = GITLET_DIR + "/Commit-5/" + wugFile;
		String content5 = getText(commit5);
		assertEquals(content5, "branch wug");
		File wug5 = new File(GITLET_DIR + "/Commit-5/" + mugFile);
		assertTrue(wug5.exists());
		
	}

	@Test
	public void testRebaseMultiple() {
		gitlet("init"); 
		String wugFileName = TESTING_DIR + "wug.txt";

		String wugtext0 = "version 0";
		String wugtext1 = "version 1, branch1";
		String wugtext2 = "version 2, master";
		String wugtext3 = "version 3, branch2";
		String wugtext4 = "version 4, master";
		String wugtext5 = "version 5, branch1";
		createFile(wugFileName, wugtext0);

		gitlet("add", wugFileName);
		gitlet("commit", "1");
		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		gitlet("add", wugFileName);
		gitlet("commit", "2");
		gitlet("branch", "branch2");
		gitlet("checkout", "branch2");
		gitlet("add", wugFileName);
		gitlet("commit", "3");
		gitlet("checkout", "branch1");
		writeFile(wugFileName, wugtext1);
		gitlet("add", wugFileName);
		gitlet("commit", "4");
		gitlet("checkout", "master");
		writeFile(wugFileName, wugtext2);
		gitlet("add", wugFileName);
		gitlet("commit", "5");
		gitlet("checkout", "branch2");
		writeFile(wugFileName, wugtext3);
		gitlet("add", wugFileName);
		gitlet("commit", "6");
		gitlet("checkout", "master");
		writeFile(wugFileName, wugtext4);
		gitlet("add", wugFileName);
		gitlet("commit", "7");
		gitlet("checkout", "branch1");
		writeFile(wugFileName, wugtext5);
		gitlet("add", wugFileName);
		gitlet("commit", "8");
		gitlet("checkout", "master");
		gitlet("add", wugFileName);
		gitlet("commit", "9");
		String logContent = gitlet("log");
		String[] allCommitMessages = extractCommitMessages(logContent);
		assertEquals(allCommitMessages.length, 5);
		assertEquals(allCommitMessages[2], "5");
		assertEquals(allCommitMessages[1], "7");
		assertEquals(allCommitMessages[0], "9");
		String[] branch0 = {"9", "7", "5", "1", "initial commit"};
		assertEquals(allCommitMessages, (branch0));
		gitlet("checkout", "branch1");

		gitlet("rebase", "master");

		// test that subsequent branches are not copied
		logContent = gitlet("log");
		allCommitMessages = extractCommitMessages(logContent);
		assertEquals(allCommitMessages.length, 8);
		assertEquals(allCommitMessages[2], "2");
		assertEquals(allCommitMessages[1], "4");
		assertEquals(allCommitMessages[0], "8");
		String[] branch1 = {"8", "4", "2", "9", "7", "5", "1", "initial commit"};
		for (String s: allCommitMessages)
			System.out.println(s);
		assertEquals(allCommitMessages, (branch1));

		// test unchanged, modified in replayed nodes
		// make sure that commit 10 wug matches commit 9 wug
		String commit10 = GITLET_DIR + "/Commit-10/" + wugFileName;
		String commit9 = GITLET_DIR + "/Commit-9/" + wugFileName;
		String content10 = getText(commit10);
		String content9 = getText(commit9);
		assertEquals(content10, content9);

		// tests that commit 11 matches commit 4 wug
		String commit11 = GITLET_DIR + "/Commit-11/" + wugFileName;
		String commit4 = GITLET_DIR + "/Commit-4/" + wugFileName;
		String content11 = getText(commit11);
		String content4 = getText(commit4);
		assertEquals(content11, content4);

		// tests that commit 12 matches commit 8 wug
		String commit12 = GITLET_DIR + "/Commit-12/" + wugFileName;
		String commit8 = GITLET_DIR + "/Commit-8/" + wugFileName;
		String content12 = getText(commit12);
		String content8 = getText(commit8);
		assertEquals(content12, content8);

		gitlet("checkout", "branch2");
		gitlet("log");

		System.out.println("rebasing branch2");
		gitlet("rebase", "branch1");

		// test that subsequent branches are not copied
		logContent = gitlet("log");
		allCommitMessages = extractCommitMessages(logContent);
		assertEquals(allCommitMessages.length, 11);
		assertEquals(allCommitMessages[2], "2");
		assertEquals(allCommitMessages[1], "3");
		assertEquals(allCommitMessages[0], "6");
		String[] branch2 = {"6", "3", "2", "8", "4", "2", "9", "7", "5", "1", "initial commit"};
		assertEquals(allCommitMessages, (branch2));

		// make sure that commit 13 wug matches commit 12 wug
		String commit13 = GITLET_DIR + "/Commit-13/" + wugFileName;
		String content13 = getText(commit13);
		assertEquals(content13, content12);
		
		// make sure that commit 14 wug matches commit 12 wug
		String commit14 = GITLET_DIR + "/Commit-14/" + wugFileName;
		String content14 = getText(commit13);
		assertEquals(content14, content12);

		// tests that commit 15 matches commit 6 wug
		String commit15 = GITLET_DIR + "/Commit-15/" + wugFileName;
		String commit6 = GITLET_DIR + "/Commit-6/" + wugFileName;
		String content15 = getText(commit15);
		String content6 = getText(commit6);
		assertEquals(content15, content6);

		gitlet("checkout", "branch1");
		logContent = gitlet("log");
		allCommitMessages = extractCommitMessages(logContent);
		assertEquals(allCommitMessages.length, 8);
		assertEquals(allCommitMessages[2], "2");
		assertEquals(allCommitMessages[1], "4");
		assertEquals(allCommitMessages[0], "8");
		assertEquals(allCommitMessages, (branch1));
	}

	
	@Test
	public void testRebaseMultiple2() {
		gitlet("init"); 
		String wugFileName = TESTING_DIR + "wug.txt";

		String wugtext0 = "version 0";
		String wugtext1 = "version 1, branch1";
		String wugtext2 = "version 2, master";
		String wugtext3 = "version 3, branch2";
		String wugtext4 = "version 4, master";
		String wugtext5 = "version 5, branch1";
		createFile(wugFileName, wugtext0);

		gitlet("add", wugFileName);
		gitlet("commit", "1");
		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		gitlet("add", wugFileName);
		gitlet("commit", "2");
		gitlet("branch", "branch2");
		gitlet("checkout", "branch2");
		gitlet("add", wugFileName);
		gitlet("commit", "3");
		gitlet("checkout", "branch1");
		writeFile(wugFileName, wugtext1);
		gitlet("add", wugFileName);
		gitlet("commit", "4");
		gitlet("checkout", "master");
		writeFile(wugFileName, wugtext2);
		gitlet("add", wugFileName);
		gitlet("commit", "5");
		gitlet("checkout", "branch2");
		writeFile(wugFileName, wugtext3);
		gitlet("add", wugFileName);
		gitlet("commit", "6");
		gitlet("checkout", "master");
		writeFile(wugFileName, wugtext4);
		gitlet("add", wugFileName);
		gitlet("commit", "7");
		gitlet("checkout", "branch1");
		writeFile(wugFileName, wugtext5);
		gitlet("add", wugFileName);
		gitlet("commit", "8");
		gitlet("checkout", "master");
		gitlet("add", wugFileName);
		gitlet("commit", "9");
		String logContent = gitlet("log");
		String[] allCommitMessages = extractCommitMessages(logContent);
		assertEquals(allCommitMessages.length, 5);
		assertEquals(allCommitMessages[2], "5");
		assertEquals(allCommitMessages[1], "7");
		assertEquals(allCommitMessages[0], "9");
		String[] branch0 = {"9", "7", "5", "1", "initial commit"};
		assertEquals(allCommitMessages, (branch0));
		gitlet("checkout", "branch2");

		gitlet("rebase", "branch1");

		// test that subsequent branches are not copied
		logContent = gitlet("log");
		allCommitMessages = extractCommitMessages(logContent);
		assertEquals(allCommitMessages.length, 7);
		assertEquals(allCommitMessages[1], "3");
		assertEquals(allCommitMessages[0], "6");
		String[] branch2 = {"6", "3", "8", "4", "2", "1", "initial commit"};
		assertEquals(allCommitMessages, (branch2));

		// test unchanged, modified in replayed nodes
		// make sure that commit 10 wug matches commit 9 wug
		String commit10 = GITLET_DIR + "/Commit-10/" + wugFileName;
		String commit8 = GITLET_DIR + "/Commit-8/" + wugFileName;
		String content10 = getText(commit10);
		String content8 = getText(commit8);
		assertEquals(content10, content8);

		// tests that commit 11 matches commit 4 wug
		String commit11 = GITLET_DIR + "/Commit-11/" + wugFileName;
		String commit6 = GITLET_DIR + "/Commit-6/" + wugFileName;
		String content11 = getText(commit11);
		String content6 = getText(commit6);
		assertEquals(content11, content6);

		gitlet("log");

		gitlet("checkout", "branch1");
		gitlet("rebase", "master");

		// test that subsequent branches are not copied
		logContent = gitlet("log");
		allCommitMessages = extractCommitMessages(logContent);
		assertEquals(allCommitMessages.length, 8);
		assertEquals(allCommitMessages[2], "2");
		assertEquals(allCommitMessages[1], "4");
		assertEquals(allCommitMessages[0], "8");
		String[] branch1 = {"8", "4", "2", "9", "7", "5", "1", "initial commit"};
		assertEquals(allCommitMessages, (branch1));

		// make sure that commit 12 wug matches commit 9 wug
		String commit12 = GITLET_DIR + "/Commit-12/" + wugFileName;
		String commit9 = GITLET_DIR + "/Commit-9/" + wugFileName;
		String content12 = getText(commit12);
		String content9 = getText(commit9);
		assertEquals(content9, content12);
		
		// make sure that commit 13 wug matches commit 4 wug
		String commit13 = GITLET_DIR + "/Commit-13/" + wugFileName;
		String commit4 = GITLET_DIR + "/Commit-4/" + wugFileName;
		String content13 = getText(commit13);
		String content4 = getText(commit4);
		assertEquals(content13, content4);

		// tests that commit 14 matches commit 8 wug
		String commit14 = GITLET_DIR + "/Commit-14/" + wugFileName;
		String content14 = getText(commit14);
		assertEquals(content14, content8);

		gitlet("checkout", "master");
		logContent = gitlet("log");
		allCommitMessages = extractCommitMessages(logContent);
		assertEquals(allCommitMessages.length, 5);
		assertEquals(allCommitMessages[2], "5");
		assertEquals(allCommitMessages[1], "7");
		assertEquals(allCommitMessages[0], "9");
	}
	
	@Test
	public void testRebaseDNE() {
		gitlet("init"); 
		String wugFileName = TESTING_DIR + "wug.txt";

		String wugtext0 = "version 0";
		String wugtext1 = "version 1, branch1";
		String wugtext2 = "version 2, master";
		String wugtext3 = "version 3, branch2";
		String wugtext4 = "version 4, master";
		String wugtext5 = "version 5, branch1";
		createFile(wugFileName, wugtext0);

		gitlet("add", wugFileName);
		gitlet("commit", "1");
		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		gitlet("add", wugFileName);
		gitlet("commit", "2");
		String error = gitlet("rebase", "branch2");
		assertEquals(error, "A branch with that name does not exist.");
	}
	
	@Test
	public void testRebaseCurrentBranch() {
		gitlet("init"); 
		String wugFileName = TESTING_DIR + "wug.txt";

		String wugtext0 = "version 0";
		String wugtext1 = "version 1, branch1";
		String wugtext2 = "version 2, master";
		String wugtext3 = "version 3, branch2";
		String wugtext4 = "version 4, master";
		String wugtext5 = "version 5, branch1";
		createFile(wugFileName, wugtext0);

		gitlet("add", wugFileName);
		gitlet("commit", "1");
		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		gitlet("add", wugFileName);
		gitlet("commit", "2");
		String error = gitlet("rebase", "branch1");
		assertEquals(error, "Cannot rebase a branch onto itself.");
	}
	
	@Test
	public void testRebaseUpToDate() {
		gitlet("init"); 
		String wugFileName = TESTING_DIR + "wug.txt";

		String wugtext0 = "version 0";
		String wugtext1 = "version 1, branch1";
		String wugtext2 = "version 2, master";
		String wugtext3 = "version 3, branch2";
		String wugtext4 = "version 4, master";
		String wugtext5 = "version 5, branch1";
		createFile(wugFileName, wugtext0);

		gitlet("add", wugFileName);
		gitlet("commit", "1");
		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		gitlet("add", wugFileName);
		gitlet("commit", "2");
		String error = gitlet("rebase", "master");
		assertEquals(error, "Already up-to-date.");
	}

	@Test
	public void testInitLog() {
		gitlet("init");
		String message = "initial commit";
		String log = gitlet("log");
		String expected = extractCommitMessages(log)[0];
		assertTrue(message.equals(expected));
	}

	@Test
	public void testBranchLog() {
		gitlet("init");
		String message = "initial commit";
		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");
		gitlet("add", test1FileName);
		String commitMessage1 = "first test file";
		gitlet("commit", commitMessage1);
		String test2FileName = TESTING_DIR + "test2.txt";
		createFile(test2FileName, "testing 4 5 6");
		gitlet("add", test2FileName);
		String commitMessage2 = "second test file";
		gitlet("commit", commitMessage2);
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1, message },
				extractCommitMessages(logContent));
		gitlet("branch", "testBranch");
		gitlet("checkout", "testBranch");
		String branchFileName = TESTING_DIR + "branch.txt";
		createFile(branchFileName, "branched here");
		gitlet("add", branchFileName);
		String commitMessage3 = "switched to testBranch here";
		gitlet("commit", commitMessage3);
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1, message },
				extractCommitMessages(logContent));
		gitlet("checkout", "master");
		String back2MasterFileName = TESTING_DIR + "master.txt";
		createFile(back2MasterFileName, "switched back to master");
		gitlet("add", back2MasterFileName);
		String commitMessage4 = "switched back to master here";
		gitlet("commit", commitMessage4);
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage4, commitMessage2, commitMessage1, message },
				extractCommitMessages(logContent));
		String masterTestFileName = TESTING_DIR + "master2.txt";
		createFile(masterTestFileName, "switched back to master");
		gitlet("add", masterTestFileName);
		String commitMessage5 = "added another file to master here";
		gitlet("commit", commitMessage5);
		gitlet("checkout", "testBranch");
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1, message },
				extractCommitMessages(logContent));
	}

	@Test
	public void testGlobalLog() {
		gitlet("init");
		String message = "initial commit";
		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");
		gitlet("add", test1FileName);
		String commitMessage1 = "first test file";
		gitlet("commit", commitMessage1);
		String test2FileName = TESTING_DIR + "test2.txt";
		createFile(test2FileName, "testing 4 5 6");
		gitlet("add", test2FileName);
		String commitMessage2 = "second test file";
		gitlet("commit", commitMessage2);
		String logContent = gitlet("global-log");
		assertArrayEquals(new String[] { message, commitMessage1, commitMessage2 },
				extractCommitMessages(logContent));
		String test3FileName = TESTING_DIR + "test3.txt";
		createFile(test3FileName, "testing 7 8 9");
		gitlet("add", test3FileName);
		String commitMessage3 = "third test file";
		gitlet("commit", commitMessage3);
		logContent = gitlet("global-log");
		assertArrayEquals(new String[] { message, commitMessage1, commitMessage2, commitMessage3 },
				extractCommitMessages(logContent));
	}

	@Test
	public void testGlobalLogWithBranches() {
		gitlet("init");
		String message = "initial commit";
		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");
		File f1 = new File(test1FileName);
		gitlet("add", test1FileName);
		String commitMessage1 = "first test file";
		gitlet("commit", commitMessage1);
		String test2FileName = TESTING_DIR + "test2.txt";
		createFile(test2FileName, "testing 4 5 6");
		gitlet("add", test2FileName);
		String commitMessage2 = "second test file";
		gitlet("commit", commitMessage2);
		String logContent = gitlet("global-log");
		assertArrayEquals(new String[] { message, commitMessage1, commitMessage2 },
				extractCommitMessages(logContent));
		gitlet("branch", "testBranch");
		gitlet("checkout", "testBranch");
		String branchFileName = TESTING_DIR + "branch.txt";
		createFile(branchFileName, "branched here");
		gitlet("add", branchFileName);
		String commitMessage3 = "switched to testBranch here";
		gitlet("commit", commitMessage3);
		logContent = gitlet("global-log");
		assertArrayEquals(new String[] { message, commitMessage1, commitMessage2, commitMessage3 },
				extractCommitMessages(logContent));
		gitlet("checkout", "master");
		String back2MasterFileName = TESTING_DIR + "master.txt";
		createFile(back2MasterFileName, "switched back to master");
		gitlet("add", back2MasterFileName);
		String commitMessage4 = "switched back to master here";
		gitlet("commit", commitMessage4);
		logContent = gitlet("global-log");
		assertArrayEquals(new String[] { message, commitMessage1, commitMessage2, commitMessage3, commitMessage4 },
				extractCommitMessages(logContent));
		String masterTestFileName = TESTING_DIR + "master2.txt";
		createFile(masterTestFileName, "switched back to master");
		gitlet("add", masterTestFileName);
		String commitMessage5 = "added another file to master here";
		gitlet("commit", commitMessage5);
		gitlet("checkout", "testBranch");
		logContent = gitlet("global-log");
		assertArrayEquals(new String[] { message, commitMessage1, commitMessage2, commitMessage3, commitMessage4, commitMessage5 },
				extractCommitMessages(logContent));
	}

	@Test
	public void testInitAndBasicCommit() {
		gitlet("init");
		File f0 = new File(GITLET_DIR+"/Commit-0/");
		assertTrue(f0.exists());
		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");
		gitlet("add", test1FileName);
		String commitMessage1 = "first test file";
		gitlet("commit", commitMessage1);
		File f1 = new File(GITLET_DIR+"/Commit-1/");
		File t1 = new File(GITLET_DIR+"/Commit-1/"+test1FileName);
		assertTrue(f1.exists());
		assertTrue(t1.exists());
	}

	@Test
	public void testMultipleFileCommit() {
		gitlet("init");
		File f0 = new File(GITLET_DIR+"/Commit-0/");
		assertTrue(f0.exists());
		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");
		gitlet("add", test1FileName);
		String test2FileName = TESTING_DIR + "test2.txt";
		createFile(test2FileName, "testing 4 5 6");
		gitlet("add", test2FileName);
		String test3FileName = TESTING_DIR + "test2.txt";
		createFile(test3FileName, "testing 7 8 9");
		gitlet("add", test3FileName);
		String test4FileName = TESTING_DIR + "test2.txt";
		createFile(test4FileName, "testing 10 11 12");
		gitlet("add", test4FileName);
		String commitMessage1 = "commited 4 files";
		gitlet("commit", commitMessage1);
		File f1 = new File(GITLET_DIR+"/Commit-1/");
		File t1 = new File(GITLET_DIR+"/Commit-1/"+test1FileName);
		File t2 = new File(GITLET_DIR+"/Commit-1/"+test2FileName);
		File t3 = new File(GITLET_DIR+"/Commit-1/"+test3FileName);
		File t4 = new File(GITLET_DIR+"/Commit-1/"+test4FileName);
		assertTrue(f1.exists());
		assertTrue(t1.exists());
		assertTrue(t2.exists());
		assertTrue(t3.exists());
		assertTrue(t4.exists());
	}

	@Test
	public void testUntrackedFileCommit() {
		gitlet("init");
		File f0 = new File(GITLET_DIR+"/Commit-0/");
		assertTrue(f0.exists());

		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");
		gitlet("add", test1FileName);

		String test2FileName = TESTING_DIR + "test2.txt";
		createFile(test2FileName, "testing 4 5 6");
		gitlet("add", test2FileName);

		String commitMessage1 = "commited 2 files";
		gitlet("commit", commitMessage1);

		String test3FileName = TESTING_DIR + "test3.txt";
		createFile(test3FileName, "testing 7 8 9");
		gitlet("add", test3FileName);

		String test4FileName = TESTING_DIR + "test4.txt";
		createFile(test4FileName, "testing 10 11 12");
		gitlet("add", test4FileName);

		gitlet("rm",test1FileName);
		gitlet("status");

		String commitMessage2 = "commited 2 files and removed 1 file";
		gitlet("commit", commitMessage2);
		gitlet("log");

		File f1 = new File(GITLET_DIR+"/Commit-2/");
		File t1 = new File(GITLET_DIR+"/Commit-2/"+test1FileName);
		File t2 = new File(GITLET_DIR+"/Commit-2/"+test2FileName);
		File t3 = new File(GITLET_DIR+"/Commit-2/"+test3FileName);
		File t4 = new File(GITLET_DIR+"/Commit-2/"+test4FileName);
		if(t1.exists())
			System.out.println(t1.getAbsolutePath());
		assertTrue(f1.exists());
		assertTrue(!t1.exists());
		assertTrue(t2.exists());
		assertTrue(t3.exists());
		assertTrue(t4.exists());
	}


	@Test
	public void testUntrackedThenAddAgainCommit() {
		gitlet("init");
		File f0 = new File(GITLET_DIR+"/Commit-0/");
		assertTrue(f0.exists());

		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");
		gitlet("add", test1FileName);

		String test2FileName = TESTING_DIR + "test2.txt";
		createFile(test2FileName, "testing 4 5 6");
		gitlet("add", test2FileName);

		String commitMessage1 = "commited 2 files";
		gitlet("commit", commitMessage1);

		String test3FileName = TESTING_DIR + "test3.txt";
		createFile(test3FileName, "testing 7 8 9");
		gitlet("add", test3FileName);

		String test4FileName = TESTING_DIR + "test4.txt";
		createFile(test4FileName, "testing 10 11 12");
		gitlet("add", test4FileName);

		gitlet("rm",test1FileName);

		String commitMessage2 = "commited 2 files and removed 1 file";
		gitlet("commit", commitMessage2);

		File f1 = new File(GITLET_DIR+"/Commit-2/");
		File t1 = new File(GITLET_DIR+"/Commit-2/"+test1FileName);
		File t2 = new File(GITLET_DIR+"/Commit-2/"+test2FileName);
		File t3 = new File(GITLET_DIR+"/Commit-2/"+test3FileName);
		File t4 = new File(GITLET_DIR+"/Commit-2/"+test4FileName);
		assertTrue(f1.exists());
		assertTrue(!t1.exists());
		assertTrue(t2.exists());
		assertTrue(t3.exists());
		assertTrue(t4.exists());

		String test5FileName = TESTING_DIR + "test5.txt";
		createFile(test5FileName, "testing 13 14 15");
		gitlet("add", test5FileName);
		gitlet("commit", "added a 5th file but there should only be 4 files still");

		f1 = new File(GITLET_DIR+"/Commit-3/");
		t1 = new File(GITLET_DIR+"/Commit-3/"+test1FileName);
		t2 = new File(GITLET_DIR+"/Commit-3/"+test2FileName);
		t3 = new File(GITLET_DIR+"/Commit-3/"+test3FileName);
		t4 = new File(GITLET_DIR+"/Commit-3/"+test4FileName);
		File t5 = new File(GITLET_DIR+"/Commit-3/"+test5FileName);
		assertTrue(f1.exists());
		assertTrue(!t1.exists());
		assertTrue(t2.exists());
		assertTrue(t3.exists());
		assertTrue(t4.exists());
		assertTrue(t5.exists());

		gitlet("add", test1FileName);
		gitlet("commit", "re-added test.txt, should have 5 files now");

		f1 = new File(GITLET_DIR+"/Commit-4/");
		t1 = new File(GITLET_DIR+"/Commit-4/"+test1FileName);
		t2 = new File(GITLET_DIR+"/Commit-4/"+test2FileName);
		t3 = new File(GITLET_DIR+"/Commit-4/"+test3FileName);
		t4 = new File(GITLET_DIR+"/Commit-4/"+test4FileName);
		t5 = new File(GITLET_DIR+"/Commit-4/"+test5FileName);
		assertTrue(f1.exists());
		assertTrue(t1.exists());
		assertTrue(t2.exists());
		assertTrue(t3.exists());
		assertTrue(t4.exists());
		assertTrue(t5.exists());
	}

	/**
	 * Tests that a commit without a message prints out "Please enter a commit message."
	 * referenced from http://stackoverflow.com/questions/1119385/junit-test-for-system-out-%20*%20println
	 */
	@Test
	public void testNoMessageCommit(){
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		gitlet("init");
		File f0 = new File(GITLET_DIR+"/Commit-0/");
		assertTrue(f0.exists());
		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");
		gitlet("add", test1FileName);
		gitlet("commit");
		assertEquals("Please enter a commit message.", outContent.toString().trim());
	}

	/**
	 * Tests that a commit without any changes prints out "No changes added to the commit."
	 * referenced from http://stackoverflow.com/questions/1119385/junit-test-for-system-out-%20*%20println
	 */
	@Test
	public void testEmptyCommit(){
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		gitlet("init");
		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");
		gitlet("add", test1FileName);
		gitlet("commit", "added test.txt");
		gitlet("commit", "I wanna be a tryhard.");
		assertEquals("No changes added to the commit.", outContent.toString().trim());

	}

	@Test
	public void testCheckoutBranch(){
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		gitlet("init");
		File f0 = new File(GITLET_DIR+"/Commit-0/");
		assertTrue(f0.exists());

		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");
		gitlet("add", test1FileName);
		gitlet("commit", "first commit");

		gitlet("branch", "new branch pl0x");
		gitlet("checkout", "new branch pl0x");
		gitlet("get-branch");
		assertEquals("new branch pl0x",outContent.toString().trim());
	}

	@Test
	public void testBranchSwitcher(){
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		gitlet("init");
		File f0 = new File(GITLET_DIR+"/Commit-0/");
		assertTrue(f0.exists());

		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");
		gitlet("add", test1FileName);
		gitlet("commit", "first commit");

		gitlet("branch", "new branch pl0x");
		gitlet("checkout", "new branch pl0x");
		gitlet("get-branch");
		assertEquals("new branch pl0x",outContent.toString().trim());
		outContent.reset();

		gitlet("checkout", "master");
		gitlet("get-branch");
		assertEquals("master",outContent.toString().trim());
	}

	@Test
	public void testCheckoutWithBranches(){
		gitlet("init");
		File f0 = new File(GITLET_DIR+"/Commit-0/");
		assertTrue(f0.exists());

		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");
		gitlet("add", test1FileName);
		gitlet("commit", "first commit");

		String test2FileName = TESTING_DIR + "test2.txt";
		createFile(test2FileName, "testing 4 5 6");
		gitlet("add", test2FileName);
		gitlet("commit", "second commit");

		gitlet("branch", "slave");
		gitlet("checkout", "slave");

		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "slave wug");
		gitlet("add", wugFileName);
		gitlet("commit", "noooo why is the wug a slave");

		String hugFileName = TESTING_DIR + "hug.txt";
		createFile(hugFileName, "free hugs 4 every1");
		gitlet("add", hugFileName);
		gitlet("commit", "you want a hug? you get a hug!");

		writeFile(test1FileName, "humpty dumpty");
		writeFile(test2FileName, "sat on a wall");

		gitlet("checkout", "master");
		File t1 = new File(test1FileName);
		File t2 = new File(test2FileName);
		File t3 = new File(wugFileName);
		File t4 = new File(hugFileName);

		assertTrue(t1.exists());
		assertTrue(t2.exists());
		assertTrue(t3.exists());
		assertTrue(t4.exists());
		assertEquals("testing 1 2 3",getText(test1FileName));
		assertEquals("testing 4 5 6",getText(test2FileName));

	}

	@Test
	public void testCheckoutWithMultipleBranches(){
		gitlet("init");
		File f0 = new File(GITLET_DIR+"/Commit-0/");
		assertTrue(f0.exists());

		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");
		gitlet("add", test1FileName);
		gitlet("commit", "first commit");

		String test2FileName = TESTING_DIR + "test2.txt";
		createFile(test2FileName, "testing 4 5 6");
		gitlet("add", test2FileName);
		gitlet("commit", "second commit");

		gitlet("branch", "slave");
		gitlet("checkout", "slave");

		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "slave wug");
		gitlet("add", wugFileName);
		gitlet("commit", "noooo why is the wug a slave");

		String hugFileName = TESTING_DIR + "hug.txt";
		createFile(hugFileName, "free hugs 4 every1");
		gitlet("add", hugFileName);
		gitlet("commit", "you want a hug? you get a hug!");

		writeFile(test1FileName, "humpty dumpty");
		writeFile(test2FileName, "sat on a wall");

		gitlet("add", test1FileName);
		gitlet("add", test2FileName);
		gitlet("commit", "third commit");

		gitlet("branch", "driver");
		gitlet("checkout", "driver");

		String bmwFileName = TESTING_DIR + "bmw.txt";
		createFile(bmwFileName, "i'm a bimmer");

		String teslaFileName = TESTING_DIR + "tesla.txt";
		createFile(teslaFileName, "i'm a tesla, i don't need no gas.");
		writeFile(wugFileName, "wug is now a driver");
		writeFile(hugFileName, "free hug for swag cars");

		gitlet("add", wugFileName);
		gitlet("add", hugFileName);
		gitlet("add", bmwFileName);
		gitlet("add", teslaFileName);
		gitlet("commit", "fourth commit.");

		gitlet("checkout","master");
		File t1 = new File(test1FileName);
		File t2 = new File(test2FileName);
		File t3 = new File(wugFileName);
		File t4 = new File(hugFileName);
		File t5 = new File(bmwFileName);
		File t6 = new File(teslaFileName);
		assertTrue(t1.exists());
		assertTrue(t2.exists());
		assertTrue(t3.exists());
		assertTrue(t4.exists());
		assertTrue(t5.exists());
		assertTrue(t6.exists());

		assertEquals("testing 1 2 3",getText(test1FileName));
		assertEquals("testing 4 5 6",getText(test2FileName));
		assertEquals("wug is now a driver",getText(wugFileName));
		assertEquals("free hug for swag cars",getText(hugFileName));
		assertEquals("i'm a bimmer",getText(bmwFileName));
		assertEquals("i'm a tesla, i don't need no gas.",getText(teslaFileName));


		gitlet("checkout","slave");
		t1 = new File(test1FileName);
		t2 = new File(test2FileName);
		t3 = new File(wugFileName);
		t4 = new File(hugFileName);
		t5 = new File(bmwFileName);
		t6 = new File(teslaFileName);
		assertTrue(t1.exists());
		assertTrue(t2.exists());
		assertTrue(t3.exists());
		assertTrue(t4.exists());
		assertTrue(t5.exists());
		assertTrue(t6.exists());

		assertEquals("humpty dumpty",getText(test1FileName));
		assertEquals("sat on a wall",getText(test2FileName));
		assertEquals("slave wug",getText(wugFileName));
		assertEquals("free hugs 4 every1",getText(hugFileName));
		assertEquals("i'm a bimmer",getText(bmwFileName));
		assertEquals("i'm a tesla, i don't need no gas.",getText(teslaFileName));

		gitlet("checkout","driver");
		t1 = new File(test1FileName);
		t2 = new File(test2FileName);
		t3 = new File(wugFileName);
		t4 = new File(hugFileName);
		t5 = new File(bmwFileName);
		t6 = new File(teslaFileName);
		assertTrue(t1.exists());
		assertTrue(t2.exists());
		assertTrue(t3.exists());
		assertTrue(t4.exists());
		assertTrue(t5.exists());
		assertTrue(t6.exists());

		assertEquals("humpty dumpty",getText(test1FileName));
		assertEquals("sat on a wall",getText(test2FileName));
		assertEquals("wug is now a driver",getText(wugFileName));
		assertEquals("free hug for swag cars",getText(hugFileName));
		assertEquals("i'm a bimmer",getText(bmwFileName));
		assertEquals("i'm a tesla, i don't need no gas.",getText(teslaFileName));
	}

	@Test
	public void testCheckoutByFileNameAlone(){
		gitlet("init");

		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");

		String test2FileName = TESTING_DIR + "test2.txt";
		createFile(test2FileName, "testing 4 5 6");

		gitlet("add", test1FileName);
		gitlet("add", test2FileName);
		gitlet("commit", "first commit");

		writeFile(test1FileName, "testing 7 8 9");
		gitlet("add", test1FileName);
		gitlet("commit", "second commit");

		writeFile(test1FileName, "testing 10 11 12");
		gitlet("add", test1FileName);
		gitlet("commit", "third commit");

		writeFile(test1FileName, "done with testing!");
		assertEquals("done with testing!", getText(test1FileName));

		gitlet("checkout", test1FileName);
		File t1 = new File(test1FileName);
		assertTrue(t1.exists());
		assertEquals("testing 10 11 12", getText(test1FileName));
	}

	@Test
	public void testCheckoutMultipleFileName(){
		gitlet("init");

		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");

		String test2FileName = TESTING_DIR + "test2.txt";
		createFile(test2FileName, "testing 4 5 6");

		gitlet("add", test1FileName);
		gitlet("add", test2FileName);
		gitlet("commit", "first commit");

		writeFile(test1FileName, "testing 7 8 9");
		gitlet("add", test1FileName);
		gitlet("commit", "second commit");

		writeFile(test1FileName, "testing 10 11 12");
		writeFile(test2FileName, "fly like a G6");
		String test3FileName = TESTING_DIR + "test3.txt";
		createFile(test3FileName, "shut up and dance with me");
		gitlet("add", test1FileName);
		gitlet("add", test2FileName);
		gitlet("add", test3FileName);
		gitlet("commit", "third commit");

		writeFile(test1FileName, "done with testing!");
		writeFile(test2FileName, "are you sure?");
		writeFile(test3FileName, "you might have forgotten stuff...");
		assertEquals("done with testing!", getText(test1FileName));
		assertEquals("are you sure?", getText(test2FileName));
		assertEquals("you might have forgotten stuff...", getText(test3FileName));

		gitlet("checkout", test1FileName);
		gitlet("checkout", test2FileName);
		gitlet("checkout", test3FileName);
		File t1 = new File(test1FileName);
		assertTrue(t1.exists());
		File t2 = new File(test2FileName);
		assertTrue(t2.exists());
		File t3 = new File(test3FileName);
		assertTrue(t3.exists());
		assertEquals("testing 10 11 12", getText(test1FileName));
		assertEquals("fly like a G6", getText(test2FileName));
		assertEquals("shut up and dance with me", getText(test3FileName));
	}

	@Test
	public void testCheckoutByIDAndFileName(){
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		gitlet("init");

		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");
		gitlet("add", test1FileName);
		gitlet("commit", "id = 1");

		writeFile(test1FileName, "testing 4 5 6");
		gitlet("add", test1FileName);
		gitlet("commit", "id = 2");

		writeFile(test1FileName, "testing 7 8 9");
		gitlet("add", test1FileName);
		gitlet("commit", "id = 3");

		writeFile(test1FileName, "testing 10 11 12");
		gitlet("add", test1FileName);
		gitlet("commit", "id = 4");

		File t1 = new File(test1FileName);
		assertTrue(t1.exists());
		assertEquals("testing 10 11 12", getText(test1FileName));

		gitlet("checkout", "1", test1FileName);
		assertTrue(t1.exists());
		assertEquals("testing 1 2 3", getText(test1FileName));

		gitlet("checkout", "2", test1FileName);
		assertTrue(t1.exists());
		assertEquals("testing 4 5 6", getText(test1FileName));

		gitlet("checkout", "3", test1FileName);
		assertTrue(t1.exists());
		assertEquals("testing 7 8 9", getText(test1FileName));

		gitlet("checkout", "4", test1FileName);
		assertTrue(t1.exists());
		assertEquals("testing 10 11 12", getText(test1FileName));

		gitlet("checkout", "5", test1FileName);
		assertEquals("No commit with that id exists.",outContent.toString().trim());
	}

	@Test
	public void testCheckoutAcrossAllBranchesAndCommits(){
		gitlet("init");

		String numbersFileName = TESTING_DIR + "nums.txt";
		String carsFileName = TESTING_DIR + "cars.txt";
		String planesFileName = TESTING_DIR + "planes.txt";
		String alphasFileName = TESTING_DIR + "alphas.txt";
		String gamesFileName = TESTING_DIR + "games.txt";
		String bandsFileName = TESTING_DIR + "bands.txt";
		String djsFileName = TESTING_DIR + "djs.txt";

		//commit 1
		createFile(numbersFileName,"1 2 3");
		createFile(carsFileName,"bmw honda mazda");
		createFile(planesFileName,"boeing airbus lockheed");
		createFile(alphasFileName,"a b c");
		createFile(gamesFileName,"checkers chess cards");
		createFile(bandsFileName,"muse coldplay chvrches");
		createFile(djsFileName,"hardwell alesso zedd");
		gitlet("add", numbersFileName);
		gitlet("add", carsFileName);
		gitlet("add", planesFileName);
		gitlet("add", alphasFileName);
		gitlet("add", gamesFileName);
		gitlet("add", bandsFileName);
		gitlet("add", djsFileName);
		gitlet("commit", "id=1");

		//commit 2
		writeFile(numbersFileName,"4 5 6");
		writeFile(carsFileName,"tesla volvo toyota");
		writeFile(planesFileName,"B747 A380 F117");
		writeFile(alphasFileName,"d e f");
		writeFile(gamesFileName,"dota2 > LoL");
		writeFile(bandsFileName,"echosmith duck sauce yeah yeah yeahs");
		writeFile(djsFileName,"deadmau5 david guetta avicii");
		gitlet("add", numbersFileName);
		gitlet("add", carsFileName);
		gitlet("add", planesFileName);
		gitlet("add", alphasFileName);
		gitlet("add", gamesFileName);
		gitlet("add", bandsFileName);
		gitlet("add", djsFileName);
		gitlet("commit", "id=2");

		//commit 3
		writeFile(numbersFileName,"7 8 9");
		writeFile(carsFileName,"acura nissan infiniti");
		writeFile(planesFileName,"B737 A340 F22");
		writeFile(alphasFileName,"g h j");
		writeFile(gamesFileName,"fifa madden nba");
		writeFile(bandsFileName,"foals bombay bicycle club two door cinema club");
		writeFile(djsFileName,"tritonal kaskade kygo");
		gitlet("add", numbersFileName);
		gitlet("add", carsFileName);
		gitlet("add", planesFileName);
		gitlet("add", alphasFileName);
		gitlet("add", gamesFileName);
		gitlet("add", bandsFileName);
		gitlet("add", djsFileName);
		gitlet("commit", "id=3");

		gitlet("branch", "new branch");
		gitlet("checkout", "new branch");

		//commit 4
		writeFile(numbersFileName,"10 11 12");
		writeFile(carsFileName,"saturn pontiac ford");
		writeFile(planesFileName,"B777 A330 F35");
		writeFile(alphasFileName,"k l m");
		writeFile(gamesFileName,"gta nfs runescape");
		writeFile(bandsFileName,"killers krewella kodaline");
		writeFile(djsFileName,"cash cash krewella ferry corsten");
		gitlet("add", numbersFileName);
		gitlet("add", carsFileName);
		gitlet("add", planesFileName);
		gitlet("add", alphasFileName);
		gitlet("add", gamesFileName);
		gitlet("add", bandsFileName);
		gitlet("add", djsFileName);
		gitlet("commit", "id=4");

		//commit 5
		writeFile(numbersFileName,"13 14 15");
		writeFile(carsFileName,"chevy subaru mitsubishi");
		writeFile(planesFileName,"B787 A320 F15");
		writeFile(alphasFileName,"n o p");
		writeFile(gamesFileName,"maplestory pes nhl");
		writeFile(bandsFileName,"mr. big mumford & sons the naked and famous");
		writeFile(djsFileName,"MAKJ nicky romero odesza");
		gitlet("add", numbersFileName);
		gitlet("add", carsFileName);
		gitlet("add", planesFileName);
		gitlet("add", alphasFileName);
		gitlet("add", gamesFileName);
		gitlet("add", bandsFileName);
		gitlet("add", djsFileName);
		gitlet("commit", "id=5");

		gitlet("branch", "branch 3");
		gitlet("checkout", "branch 3");

		//commit 6
		writeFile(numbersFileName,"16 17 18");
		writeFile(carsFileName,"lambo maserati vauxhall");
		writeFile(planesFileName,"B757 A321 F14");
		writeFile(alphasFileName,"q r s");
		writeFile(gamesFileName,"icy tower super mario smash bros");
		writeFile(bandsFileName,"shm dashboard-c timeflies");
		writeFile(djsFileName,"tiesto porter robinson overwerk");
		gitlet("add", numbersFileName);
		gitlet("add", carsFileName);
		gitlet("add", planesFileName);
		gitlet("add", alphasFileName);
		gitlet("add", gamesFileName);
		gitlet("add", bandsFileName);
		gitlet("add", djsFileName);
		gitlet("commit", "id=6");

		//commit 7
		writeFile(numbersFileName,"19 20 121");
		writeFile(carsFileName,"mini audi volkswagon");
		writeFile(planesFileName,"B747 A35X F16");
		writeFile(alphasFileName,"t u v");
		writeFile(gamesFileName,"contra kingdom hearts raving rabbits");
		writeFile(bandsFileName,"omom onerepublic magic!");
		writeFile(djsFileName,"martin garrix feenixpawl john dahlback");
		gitlet("add", numbersFileName);
		gitlet("add", carsFileName);
		gitlet("add", planesFileName);
		gitlet("add", alphasFileName);
		gitlet("add", gamesFileName);
		gitlet("add", bandsFileName);
		gitlet("add", djsFileName);
		gitlet("commit", "id=7");

		//clears all files
		writeFile(numbersFileName," ");
		writeFile(carsFileName," ");
		writeFile(planesFileName," ");
		writeFile(alphasFileName," ");
		writeFile(gamesFileName," ");
		writeFile(bandsFileName," ");
		writeFile(djsFileName," ");

		File t1 = new File(numbersFileName);
		File t2 = new File(carsFileName);
		File t3 = new File(planesFileName);
		File t4 = new File(alphasFileName);
		File t5 = new File(gamesFileName);
		File t6 = new File(bandsFileName);
		File t7 = new File(djsFileName);
		assertTrue(t1.exists());
		assertTrue(t2.exists());
		assertTrue(t3.exists());
		assertTrue(t4.exists());
		assertTrue(t5.exists());
		assertTrue(t6.exists());
		assertTrue(t7.exists());
		assertEquals(" ", getText(numbersFileName));
		assertEquals(" ", getText(carsFileName));
		assertEquals(" ", getText(planesFileName));
		assertEquals(" ", getText(alphasFileName));
		assertEquals(" ", getText(gamesFileName));
		assertEquals(" ", getText(bandsFileName));
		assertEquals(" ", getText(djsFileName));

		//checkouts
		gitlet("checkout", "1", numbersFileName);
		gitlet("checkout", "2", carsFileName);
		gitlet("checkout", "3", planesFileName);
		gitlet("checkout", "4", alphasFileName);
		gitlet("checkout", "5", gamesFileName);
		gitlet("checkout", "6", bandsFileName);
		gitlet("checkout", "7", djsFileName);
		assertEquals("1 2 3", getText(numbersFileName));
		assertEquals("tesla volvo toyota", getText(carsFileName));
		assertEquals("B737 A340 F22", getText(planesFileName));
		assertEquals("k l m", getText(alphasFileName));
		assertEquals("maplestory pes nhl", getText(gamesFileName));
		assertEquals("shm dashboard-c timeflies", getText(bandsFileName));
		assertEquals("martin garrix feenixpawl john dahlback", getText(djsFileName));
	}

	@Test
	public void testCheckoutCurrentBranch(){
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		gitlet("init");
		File f0 = new File(GITLET_DIR+"/Commit-0/");
		assertTrue(f0.exists());

		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");
		gitlet("add", test1FileName);
		gitlet("commit", "first commit");

		gitlet("branch", "new branch pl0x");
		gitlet("checkout", "new branch pl0x");
		gitlet("checkout", "new branch pl0x");
		assertEquals("No need to checkout the current branch.",outContent.toString().trim());
	}

	@Test
	public void testCheckoutNonexistentBranch(){
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		gitlet("init");
		File f0 = new File(GITLET_DIR+"/Commit-0/");
		assertTrue(f0.exists());

		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");
		gitlet("add", test1FileName);
		gitlet("commit", "first commit");

		gitlet("branch", "new branch pl0x");
		gitlet("checkout", "new branch pl0x");
		gitlet("checkout", "non existent branch");
		assertEquals("File does not exist in the most recent commit, or no such branch exists.",outContent.toString().trim());
	}

	@Test
	public void testCheckoutNonexistentID(){
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		gitlet("init");
		File f0 = new File(GITLET_DIR+"/Commit-0/");
		assertTrue(f0.exists());

		String test1FileName = TESTING_DIR + "test.txt";
		createFile(test1FileName, "testing 1 2 3");
		gitlet("add", test1FileName);
		gitlet("commit", "first commit");

		gitlet("checkout", "2", test1FileName);
		assertEquals("No commit with that id exists.",outContent.toString().trim());
	}

	@Test
	public void testCheckoutNonexistentFileByID(){
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		gitlet("init");
		File f0 = new File(GITLET_DIR+"/Commit-0/");
		assertTrue(f0.exists());

		String test1FileName = TESTING_DIR + "test.txt";
		String test2FileName = TESTING_DIR + "test2.txt";
		createFile(test1FileName, "testing 1 2 3");
		gitlet("add", test1FileName);
		gitlet("commit", "first commit");

		gitlet("checkout", "1", test2FileName);
		assertEquals("File does not exist in that commit.",outContent.toString().trim());
	}

	@Test
	public void testCheckoutNonexistentFileByName(){
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		gitlet("init");
		File f0 = new File(GITLET_DIR+"/Commit-0/");
		assertTrue(f0.exists());

		String test1FileName = TESTING_DIR + "test.txt";
		String test2FileName = TESTING_DIR + "test2.txt";
		createFile(test1FileName, "testing 1 2 3");
		gitlet("add", test1FileName);
		gitlet("commit", "first commit");

		gitlet("checkout", test2FileName);
		String error = "File does not exist in the most recent commit, or no such branch exists.";
		assertEquals(error,outContent.toString().trim());
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