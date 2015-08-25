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
import java.util.ArrayList;
import java.util.Arrays;

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
		assertEquals("This is not a wug.", getText(wugFileName));
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
	 * Tests that init fails when gitlet has already been initialized in the
	 * current directory. Also checks that the initialization creates a commit
	 * called "initial commit".
	 */
	public void testInit() {
		gitlet("init");
		assertEquals(
				"A gitlet version control system already exists in the current directory.",
				gitlet("init"));
		String currentHistory = gitlet("log");
		assertArrayEquals(new String[] { "initial commit" },
				extractCommitMessages(currentHistory));
	}

	/**
	 * - Detailed test of add and rm - Tests that add will either add the file
	 * to the stage, or removes it from "marked for untracking" - Tests that rm
	 * will either remove the file from the stage, or mark it for untracking -
	 * Checks that add and rm will print error messages when appropriate
	 * 
	 */
	@Test
	public void testAddRm() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String catFileName = TESTING_DIR + "cat.txt";
		String catText = "This is a cat.";
		createFile(catFileName, catText);

		String output = gitlet("add", TESTING_DIR + "tuatara.txt");
		assertEquals("File does not exist.", output); // Tests error message
														// when adding a file
														// that doesn't exist
		output = gitlet("rm", TESTING_DIR + "tuatara.txt");
		assertEquals("No reason to remove the file.", output); // Tests error
																// message when
																// file doesn't
																// exist
		createFile(TESTING_DIR + "tuatara.txt", "This is a tuatara.");
		output = gitlet("rm", TESTING_DIR + "tuatara.txt");
		assertEquals("No reason to remove the file.", output); // Tests error
																// message when
																// adding a file
																// that isn't
																// tracked/staged

		gitlet("add", wugFileName); // Tests that add will add the file to the
									// stage
		gitlet("add", catFileName);
		gitlet("rm", catFileName); // Tests that rm will remove the file from
									// the stage
		gitlet("commit", "first commit"); // 10001

		writeFile(wugFileName, "Where has my little wug gone?");
		gitlet("checkout", wugFileName);
		assertEquals("This is a wug.", getText(wugFileName)); // Should return
																// the original
																// contents of
																// wug.txt
		String expectedOutput = "File does not exist in the most recent commit, or no such branch exists.";
		assertEquals(expectedOutput, gitlet("checkout", catFileName)); // Shouldn't
																		// be
																		// able
																		// to
																		// find
																		// cat.txt

		writeFile(wugFileName, "Where has my little wug gone?");
		gitlet("add", wugFileName);
		gitlet("add", catFileName);
		gitlet("rm", wugFileName);
		gitlet("commit", "second commit"); // 10002
		gitlet("checkout", wugFileName);
		gitlet("checkout", catFileName);
		assertEquals("This is a wug.", getText(wugFileName));
		assertEquals("This is a cat.", getText(catFileName));

		writeFile(wugFileName, "My wug is named Jeff.");
		writeFile(catFileName, "My cat is named Asphault.");
		gitlet("rm", wugFileName); // Tests that rm will mark file for
									// untracking
		gitlet("rm", catFileName);
		gitlet("add", catFileName); // Tests that add will remove the mark for
									// untracking, but not stage the file
		gitlet("commit", "third commit"); // 10003

		gitlet("checkout", catFileName);
		assertEquals("This is a cat.", getText(catFileName));
		output = gitlet("checkout", wugFileName);
		assertEquals(
				"File does not exist in the most recent commit, or no such branch exists.",
				output);
	}

	/**
	 * Test that files are untracked if called on by rm
	 */

	@Test
	public void testrmtracking() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String catFileName = TESTING_DIR + "cat.txt";
		String catText = "This is a cat.";
		createFile(catFileName, catText);
		String commitMessage2 = "added wug";
		gitlet("add", wugFileName);
		gitlet("add", catFileName);
		gitlet("commit", commitMessage1);
		writeFile(wugFileName, "This is not a wug.");
		gitlet("rm", wugFileName); // untracks wug
		gitlet("add", catFileName);
		gitlet("commit", commitMessage2);
		gitlet("checkout", "10002", wugFileName);
		assertEquals("This is not a wug.", getText(wugFileName)); // if this fails,
																	// the wugText in directory
																	// was changed
	}

	/**
	 * Test branch, checkout [branch name], global log
	 */

	@Test
	public void testglog() {
		gitlet("init");
		String commitMessage1 = "added wug";
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage1);
		gitlet("branch", "branch-test");

		writeFile(wugFileName, "This is not a wug.");
		String commitMessage2 = "changed wug";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage2);

		gitlet("checkout", "branch-test");
		writeFile(wugFileName, "JUST DO IT");
		gitlet("add", wugFileName);
		String commitMessage3 = "Wug is doing it";
		gitlet("commit", commitMessage3);

		String logContent = gitlet("global-log");
		ArrayList<String> global = new ArrayList<String>(
				Arrays.asList(extractCommitMessages(logContent)));
		assertTrue(global.contains(commitMessage1));
		assertTrue(global.contains(commitMessage2));
		assertTrue(global.contains(commitMessage3));
	}

	/**
	 * Testing find, should return the correct ID number TEST FIND WITH MULTIPLE
	 * OF THE SAME COMMIT MESSAGE IN TERMINAL
	 */

	@Test
	public void testfind() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String commitMessage1 = "added wug";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage1);
		String output = gitlet("find", "added wug");
		int outputInt = Integer.parseInt(output);
		assertTrue(outputInt == 10001);
		output = gitlet("find", "parsrdfj");
		assertEquals("Found no commit with that message.", output);
		gitlet("rm", wugFileName);
		gitlet("commit", "added wug");
		output = gitlet("find", "added wug");
		assertEquals("10001\n10002", output);
	}

	/**
	 * If a branch is removed, the commit should still be there Tests that the
	 * branch is removed, and that rm-branch prints out the correct error
	 * messages
	 */

	@Test
	public void testRmBranch() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String commitMessage1 = "added wug";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage1); // 10001
		gitlet("branch", "tester");
		gitlet("checkout", "tester");
		writeFile(wugFileName, "These are wugs");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage1); // 10002
		gitlet("checkout", "master");
		gitlet("rm-branch", "tester");
		gitlet("checkout", "10002", wugFileName);
		String output = gitlet("checkout", "tester");
		assertEquals(
				"File does not exist in the most recent commit, or no such branch exists.",
				output);
		assertEquals("These are wugs", getText(wugFileName));
		output = gitlet("rm-branch", "superman");
		assertEquals("A branch with that name does not exist.", output);
		output = gitlet("rm-branch", "master");
		assertEquals("Cannot remove the current branch.", output);
	}

	/**
	 * Tests the error messages for commit, and that commit clears the stage
	 * after committing
	 */
	@Test
	public void testCommit() {
		gitlet("init");
		String output = gitlet("commit", "message");
		assertEquals("No changes added to the commit.", output);
		output = gitlet("commit");
		assertEquals("Please enter a commit message.", output);
		createFile(TESTING_DIR + "wug.txt", "This is a wug.");
		gitlet("add", TESTING_DIR + "wug.txt");
		gitlet("commit", "message");
	}

	/**
	 * Tests the last use of checkout [commitID] [fileName]
	 * 
	 */
	@Test
	public void testCheckout() {
		gitlet("init");
		createFile(TESTING_DIR + "wug.txt", "This is a wug.");
		gitlet("add", TESTING_DIR + "wug.txt");
		gitlet("commit", "added wug"); // 10001

		writeFile(TESTING_DIR + "wug.txt", "This is not a wug.");
		gitlet("add", TESTING_DIR + "wug.txt");
		gitlet("commit", "changed wug"); // 10002

		gitlet("branch", "side-branch");

		writeFile(TESTING_DIR + "wug.txt", "Oh geez, this is a wug...Oops");
		gitlet("add", TESTING_DIR + "wug.txt");
		gitlet("commit", "really changed the wug"); // 10003

		assertEquals("Oh geez, this is a wug...Oops", getText(TESTING_DIR
				+ "wug.txt"));
		gitlet("checkout", "10001", TESTING_DIR + "wug.txt");
		assertEquals("This is a wug.", getText(TESTING_DIR + "wug.txt"));

		gitlet("checkout", "side-branch");

		writeFile(TESTING_DIR + "wug.txt", "This is definitely a wug.");
		gitlet("add", TESTING_DIR + "wug.txt");
		gitlet("commit", "really, really changed the wug"); // 10004

		assertEquals("This is definitely a wug.", getText(TESTING_DIR
				+ "wug.txt"));
		gitlet("checkout", "10002", TESTING_DIR + "wug.txt"); // Tests
																// retrieving
																// file from
																// commit using
																// its id
		assertEquals("This is not a wug.", getText(TESTING_DIR + "wug.txt"));
		gitlet("checkout", TESTING_DIR + "wug.txt"); // Tests retrieving file
														// from head commit
		assertEquals("This is definitely a wug.", getText(TESTING_DIR
				+ "wug.txt"));
		gitlet("checkout", "master"); // Tests checking out and entire branch
										// pointer's commit
		assertEquals("Oh geez, this is a wug...Oops", getText(TESTING_DIR
				+ "wug.txt"));

		// Test the error messages
		String output = gitlet("checkout", "1234389", TESTING_DIR + "wug.txt");
		assertEquals("No commit with that id exists.", output);
		output = gitlet("checkout", "10002", "cat.txt");
		assertEquals("File does not exist in that commit.", output);
		output = gitlet("checkout", "master");
		assertEquals("No need to checkout the current branch.", output);
		output = gitlet("checkout", "super.txt");
		assertEquals(
				"File does not exist in the most recent commit, or no such branch exists.",
				output);

	}

	/**
	 * Tests checking out a file from the current branch that was inherited from
	 * the parent
	 */
	@Test
	public void testCheckout2() {
		gitlet("init");
		createFile(TESTING_DIR + "wug.txt", "This is a wug.");
		gitlet("add", TESTING_DIR + "wug.txt");
		gitlet("commit", "added wug"); // 10001

		createFile(TESTING_DIR + "capybara.txt", "This is a capybara.");
		gitlet("add", TESTING_DIR + "capybara.txt");
		gitlet("commit", "added capybara"); // 10002

		writeFile(TESTING_DIR + "capybara.txt", "This is a purple capybara");
		gitlet("add", TESTING_DIR + "capybara.txt");
		gitlet("commit", "made the capybara purple"); // 10003

		writeFile(TESTING_DIR + "wug.txt", "Is this a capybara?");
		assertEquals("Is this a capybara?", getText(TESTING_DIR + "wug.txt"));
		gitlet("checkout", TESTING_DIR + "wug.txt");
		assertEquals("This is a wug.", getText(TESTING_DIR + "wug.txt"));
	}

	/**
	 * Tests that gitlet can merge the histories of two branches together, properly dealing with the errors,
	 * properly deals with .conflicted files, puts the gitlet into a conflicted state, etc.
	 */
	@Test
	public void testmerge() {
		gitlet("init");
		createFile(TESTING_DIR + "wug.txt", "This is a wug.");
		createFile(TESTING_DIR + "platypus.txt", "This is a platypus.");
		createFile(TESTING_DIR + "tuatara.txt", "This is a tuatara.");
		gitlet("add", TESTING_DIR + "wug.txt");
		gitlet("add", TESTING_DIR + "platypus.txt");
		gitlet("add", TESTING_DIR + "tuatara.txt");
		gitlet("commit", "first-commit"); // 10001

		gitlet("branch", "side-branch");

		writeFile(TESTING_DIR + "wug.txt", "This is a blue wug.");
		writeFile(TESTING_DIR + "tuatara.txt", "What is a tuatara?");
		gitlet("add", TESTING_DIR + "wug.txt");
		gitlet("add", TESTING_DIR + "tuatara.txt");
		gitlet("commit", "second-commit"); // 10002

		gitlet("checkout", "side-branch");
		writeFile(TESTING_DIR + "platypus.txt", "Platypus. Perry the platypus.");
		writeFile(TESTING_DIR + "tuatara.txt", "I love tuatara!");
		gitlet("add", TESTING_DIR + "platypus.txt");
		gitlet("add", TESTING_DIR + "tuatara.txt");
		gitlet("commit", "third-commit"); // 10003

		assertEquals("A branch with that name does not exist.",
				gitlet("merge", "bananas"));
		assertEquals("Cannot merge a branch with itself.",
				gitlet("merge", "side-branch"));

		assertEquals("Encountered a merge conflict.", gitlet("merge", "master"));
		File conflicted = new File(TESTING_DIR + "tuatara.conflicted");
		assertTrue(conflicted.exists());
		assertEquals("This is a blue wug.", getText(TESTING_DIR + "wug.txt"));
		assertEquals("Platypus. Perry the platypus.", getText(TESTING_DIR
				+ "platypus.txt"));
		assertEquals("I love tuatara!", getText(TESTING_DIR + "tuatara.txt"));

		assertEquals(
				"Cannot do this command until the merge conflict has been resolved.",
				gitlet("merge", "master"));
		conflicted.delete();
		gitlet("commit", "fourth-commit"); // 10004
		writeFile(TESTING_DIR + "tuatara.txt", "Where'd the tuatara go?");
		gitlet("checkout", TESTING_DIR + "tuatara.txt");
		assertEquals("I love tuatara!", getText(TESTING_DIR + "tuatara.txt"));

	}

	/**
	 * Tests the special cases where the modifications merge must handle are
	 * removals
	 */
	@Test
	public void testMerge2() {
		gitlet("init");
		createFile(TESTING_DIR + "wug.txt", "This is a wug.");
		createFile(TESTING_DIR + "platypus.txt", "This is a platypus.");
		createFile(TESTING_DIR + "tuatara.txt", "This is a tuatara.");
		gitlet("add", TESTING_DIR + "wug.txt");
		gitlet("add", TESTING_DIR + "platypus.txt");
		gitlet("add", TESTING_DIR + "tuatara.txt");
		gitlet("commit", "first-commit"); // 10001
		gitlet("branch", "branch");

		writeFile(TESTING_DIR + "platypus.txt", "This is a blue platypus.");
		createFile(TESTING_DIR + "pangolin.txt", "This is a pangolin.");
		gitlet("rm", TESTING_DIR + "wug.txt");
		gitlet("add", TESTING_DIR + "platypus.txt");
		gitlet("add", TESTING_DIR + "pangolin.txt");
		gitlet("commit", "master commit"); // 10002

		gitlet("checkout", "branch");
		gitlet("rm", TESTING_DIR + "platypus.txt");
		gitlet("rm", TESTING_DIR + "tuatara.txt");
		gitlet("commit", "branch commit"); // 10003

		gitlet("merge", "master"); // 10004
		assertEquals("File does not exist in that commit.",
				gitlet("checkout", "10004", TESTING_DIR + "wug.txt"));
		assertEquals("File does not exist in that commit.",
				gitlet("checkout", "10004", TESTING_DIR + "tuatara.txt"));
		writeFile(TESTING_DIR + "platypus.txt.", "This is not a platypus.");
		writeFile(TESTING_DIR + "pangolin.txt.", "This is not a pangolin.");
		gitlet("checkout", TESTING_DIR + "platypus.txt");
		gitlet("checkout", TESTING_DIR + "pangolin.txt");
		assertEquals("This is a blue platypus.", getText(TESTING_DIR
				+ "platypus.txt"));
		assertEquals("This is a pangolin.", getText(TESTING_DIR
				+ "pangolin.txt"));

	}

	/**
	 * Test that the output of log after a rebase includes the commit messages
	 * from both branches involved in the rebase.
	 */
	@Test 
	public void rebase1(){
		gitlet("init"); //init a new gitlet system
		String commitMessage0 = "initial commit";		
		String file1 = TESTING_DIR + "test.txt";//create file1
		String file1text = "master1";
		createFile(file1, file1text);
		String file2 = TESTING_DIR + "test2.txt";
		String file2text = "branch1";
		createFile(file2, file2text);//create file2	
		gitlet("add", file1);//add file1 to stage		
		String commitMessage1 = "a";
		gitlet("commit", commitMessage1);//commit		
		writeFile(file1, "master2");		
		gitlet("add", file1);	//add file1 and file2 to stage	
		gitlet("add", file2);		
		String commitMessage2 = "b";
		gitlet("commit", commitMessage2); //commit	
		gitlet("branch", "branch");//create a new branch		
		gitlet("checkout", "branch");//checkout to the new branch		
		writeFile(file2, "branch2");		//change file2 contents
		writeFile(file1, "master3");
		gitlet("add", file2);	
		gitlet("add", file1);
		String commitMessage3 = "c";
		gitlet("commit", commitMessage3); // commit changes		
		writeFile(file2, "branch3");
		writeFile(file1, "master2");
		gitlet("add", file2); 
		gitlet("add", file1); 		
		String commitMessage4 = "d";
		gitlet("commit", commitMessage4);				
		gitlet("checkout","master");//checkout to master				
		writeFile(file1, "master3");
		gitlet("add", file1);		
		String commitMessage5 = "e";
		gitlet("commit", commitMessage5);		
		writeFile(file1, "master4");
		gitlet("add", file1);		
		String commitMessage6 = "f";
		gitlet("commit", commitMessage6);		
		gitlet("checkout", "branch"); //checkout to master		
		gitlet("rebase", "master");
		
		String logContent = gitlet("log");
		assertArrayEquals(new String[] {commitMessage4, commitMessage3, commitMessage6, commitMessage5, commitMessage2, commitMessage1, commitMessage0},
				extractCommitMessages(logContent));
		gitlet("checkout", "10007" ,file1); //test whether the changes propagate
		assertEquals(getText(file1), "master3");
		gitlet("checkout", "10008" ,file2);
		assertEquals(getText(file2), "branch3");
		
	}

	/**
	 * This test is for special case in rebase, that the current branch is in 
	 * the history of given branch.
	 */
	 @Test
	 public void rebase2(){
	 gitlet("init"); //init a new gitlet system
	 String commitMessage0 = "initial commit";
	 String file1 = "test.txt";//create file1
	 String file1text = "master1";
	 createFile(file1, file1text);

	 gitlet("add", file1);//add file1 to stage
	 String commitMessage1 = "a";
	 gitlet("commit", commitMessage1);//commit 10001

	 writeFile(file1, "master2");
	 gitlet("add", file1);
	 String commitMessage2 = "b";
	 gitlet("commit", commitMessage2); //commit 10002 (master)

	 gitlet("branch", "branch");//create a new branch
	 gitlet("checkout", "branch");//checkout to the new branch
	 writeFile(file1, "master3");
	 gitlet("add", file1);
	 String commitMessage3 = "c";
	 gitlet("commit", commitMessage3); // commit 10003

	 writeFile(file1, "master4");
	 gitlet("add", file1); 
	 String commitMessage4 = "d";
	 gitlet("commit", commitMessage4);// commit 10004 (branch)
	 

	 String failure1 = gitlet("rebase", "others");	 
	 assertEquals(failure1, "A branch with that name does not exist.");
	 
	 String failure2 = gitlet("rebase", "branch");
	 assertEquals(failure2, "Cannot rebase a branch onto itself.");
	 
	 
	 gitlet("checkout", "master");
	 String logContent1 = gitlet("log");
	 assertArrayEquals(new String[] {commitMessage2, commitMessage1, commitMessage0},
				extractCommitMessages(logContent1));
	 
	 gitlet("rebase", "branch");
	 String logContent = gitlet("log");
	 assertArrayEquals(new String[] {commitMessage4, commitMessage3, commitMessage2, commitMessage1, commitMessage0},
				extractCommitMessages(logContent));
	 }
	 
	 /**
	  * Tests rebase error message when the given branch is in the history of the current branch
	  */
	 @Test 
	 public void rebase3(){
		 gitlet("init"); //init a new gitlet system
		 String commitMessage0 = "initial commit";
		 String file1 = "test.txt"; //create file1
		 String file1text = "master1";
		 createFile(file1, file1text);

		 gitlet("add", file1); //add file1 to stage
		 String commitMessage1 = "a";
		 gitlet("commit", commitMessage1); //commit 10001

		 writeFile(file1, "master2");
		 gitlet("add", file1);
		 String commitMessage2 = "b";
		 gitlet("commit", commitMessage2); //commit 10002 (master)
		 
		 gitlet("branch", "branch"); //create a new branch
		 gitlet("checkout", "branch"); //checkout to the new branch
		 writeFile(file1, "master3");
		 gitlet("add", file1);
		 String commitMessage3 = "c";
		 gitlet("commit", commitMessage3); // commit 10003

		 writeFile(file1, "master4");
		 gitlet("add", file1); 
		 String commitMessage4 = "d";
		 gitlet("commit", commitMessage4); // commit 10004 (branch)
		 
		 String failure3 = gitlet("rebase", "master");
		 assertEquals(failure3, "Already up-to-date.");

		 
	 }

	 /**
	 * Test of basic functionality for Checkpoint 2
	 */
	@Test
	public void test2() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String commitMessage1 = "initial commit";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage1);
		writeFile(wugFileName, "hi.");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage1); // 10002
		writeFile(wugFileName, "hi!");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage1);
		gitlet("reset", "10002");
		System.out.println("TEST2LOG");
		gitlet("log");
		System.out.println("***************");
		System.out.println();
		assertEquals(getText(wugFileName), "hi.");
	}

	/**
	 * Test of basic functionality for Checkpoint 2
	 */
	@Test
	public void test3() {
		gitlet("init");
		createFile(TESTING_DIR + "wug.txt", "hi");
		gitlet("add", TESTING_DIR + "wug.txt");
		gitlet("commit", "a"); // 10001 "a"

		gitlet("branch", "yo");
		gitlet("checkout", "yo");
		writeFile(TESTING_DIR + "wug.txt", "hi.");
		gitlet("add", TESTING_DIR + "wug.txt");
		gitlet("commit", "b"); // 10002, "b"

		gitlet("checkout", "master");
		assertEquals(getText(TESTING_DIR + "wug.txt"), "hi");
		writeFile(TESTING_DIR + "wug.txt", "hi!");
		gitlet("add", TESTING_DIR + "wug.txt");
		gitlet("commit", "c"); // 10003, "c"

		String id = gitlet("find", "b");
		gitlet("checkout", id, TESTING_DIR + "wug.txt");
		assertEquals(getText(TESTING_DIR + "wug.txt"), "hi.");

		System.out.println("test3 Global-Log");
		gitlet("global-log");
		System.out.println("**********************");
		System.out.println();

	}

	/**
	 * Test of basic functionality for Checkpoint 2
	 */
	@Test
	public void test4() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "file1.txt";
		String wugText = "hi";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "first-commit"); // 10001
		createFile(TESTING_DIR + "file2.txt", "yo");
		gitlet("rm", wugFileName);
		gitlet("add", TESTING_DIR + "file2.txt");
		gitlet("status");
		gitlet("commit", "second-commit"); // 10002
		writeFile(TESTING_DIR + "file2.txt", "yo.");
		gitlet("add", wugFileName);
		gitlet("add", TESTING_DIR + "file2.txt");
		gitlet("commit", "third-commit"); // 10003
		gitlet("reset", "10002");
		assertEquals("hi", getText(wugFileName));
		assertEquals("yo", getText(TESTING_DIR + "file2.txt"));

	}

	/**
	 * Reset the files in the directory, Checks out all the files TRACKED by the
	 * given commit.
	 */
	@Test
	public void testreset() {
		gitlet("init");
		String commitMessage1 = "commiting";
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String catFileName = TESTING_DIR + "cat.txt";
		String catText = "This is a cat.";
		createFile(catFileName, catText);
		gitlet("add", wugFileName);
		gitlet("add", catFileName);
		gitlet("commit", commitMessage1); // 10001
		writeFile(wugFileName, "This is a wug!!");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage1); // 10002
		writeFile(wugFileName, "This is a fat wug!!");
		writeFile(catFileName, "This is a fat cat!!");
		gitlet("add", wugFileName);
		gitlet("add", catFileName);
		gitlet("commit", commitMessage1); // 10003
		gitlet("reset", "10002");
		assertEquals(getText(wugFileName), "This is a wug!!");
		assertEquals(getText(catFileName), "This is a cat."); // This was not in the commit
																// but it was tracked

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