

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

	private static String getFirstId(String message) {
		try {
			String content = gitlet("find", message);
			return content.split(LINE_SEPARATOR)[0];
		} catch (Exception e) {
			return "";
		}
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
	 * Tests that add makes a copy of a file from working dir to stage folder
	 */
	@Test
	public void testAdd(){
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		File f = new File(wugFileName);
		File f2 = new File(".gitlet/stage/" + wugFileName);
		gitlet("init");
		gitlet("add", wugFileName);
		assertTrue(f.exists());
		assertTrue(f2.exists());
	}
	
	/**
	 * Tests that commit creates a commit folder && copies files from stage
	 * involves add, which has been tested above & it works
	 */
	@Test
	public void testCommit(){
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "work?");
		File fileincommit = new File(".gitlet/2/" + wugFileName);
		File stage = new File(".gitlet/stage");
		assertEquals(0, stage.listFiles().length);
		assertTrue(fileincommit.exists());
	}
	
	/**
	 * Tests that checking out a file name will restore the version of the file
	 * from the previous commit. Involves init, add, commit, and checkout.
	 */
	@Test
	public void testFileCheckout() {
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
	 * Test that a file that has been committed at some point can be 
	 * restored by checking it out from a commit that tracks that 
	 * version of the file, even if the file wasn't staged prior to that commit.
	 * Test that you can checkout [id] [file] from an arbitrary commit
	 * in the graph (even one in another branch).
	 * 
	 * involves init, add, commit checkout
	 */
	
	@Test
	public void testIDCheckout() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		String wug1FileName = TESTING_DIR + "wug1.txt";
		String wug1Text = "This is a wug1.";
		String wug2FileName = TESTING_DIR + "wug2.txt";
		String wug2Text = "This is a wug2.";
		createFile(wugFileName, wugText);
		createFile(wug1FileName, wug1Text);
		createFile(wug2FileName, wug2Text);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug"); 
		gitlet("add", wug1FileName);
		gitlet("commit", "added wug1"); 
		gitlet("add", wug2FileName);
		gitlet("commit", "added wug2"); 
		writeFile(wugFileName, "random");
		gitlet("checkout", "3", wugFileName);
		assertEquals(wugText, getText(wugFileName));
		
		gitlet("checkout", "cools");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug"); 
		gitlet("add", wug1FileName);
		gitlet("commit", "added wug1"); 
		writeFile(wugFileName, "This is not a wug.");
		String commitId = getFirstId("added wug");
		gitlet("checkout", commitId, wugFileName);
		assertEquals(wugText, getText(wugFileName));
	}
	
	/*
	 * Test that a file that has been committed at some 
	 * point can be restored by checking it out from the commit
	 * it was committed at.
	 * 
	 * involves init, add, commit, checkout
	 */
	
	@Test
	public void testCheckoutPastCommit() {

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
	
	/*
	 * Test that you can checkout [id] [file] from an arbitrary commit
	 * in the graph (even one in another branch).
	 * 
	 * involves init, add, commit, branch, checkout
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
	

	/**
	 * Test that resetting backward appropriately changes the output of log.
	 * involves init, add, commit, reset
	 */
	@Test
	public void testResetHistory() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String commitMessage0 = "initial commit";
		String commitMessage1 = "added wug";
		String commitMessage2 = "made wug awesome";
		String commitMessage3 = "what... what happened?!";
		createFile(wugFileName, "This is a wug.");

		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage1);

		writeFile(wugFileName, "This is an awesome wug.");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage2);

		writeFile(wugFileName, "It's... it's a...?!");
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage3);
		gitlet("reset", getFirstId(commitMessage2));
		assertArrayEquals(new String[] { commitMessage2, commitMessage1,
		commitMessage0 }, extractCommitMessages(gitlet("log")));
	}
	
	
	/**
	 * Test that rm removes given file from stage folder && the 
	 * the commit does not include that given file (check this by
	 * checking out the commit
	 * 
	 * involves init, add, commit, rm, checkout
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
	
	/**
	 * Test that rm untracks given file && the 
	 * the commit does not include that given file (check this by
	 * checking out the commit
	 * 
	 * involves init, add, commit, rm, checkout
	 */
	
	@Test
	public void testRemove() {
		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "This is a wug.");

		String wugsFileName = TESTING_DIR + "wugs.txt";
		createFile(wugsFileName, "There are two wugs.");

		gitlet("init");

		gitlet("add", wugFileName);
		gitlet("commit", "added wug");

		String changedText = "This is definitely a wug.";
		writeFile(wugFileName, changedText);
		gitlet("rm", wugFileName);
		gitlet("add", wugsFileName);
		gitlet("commit", "added wugs and removed wug");

		String errorMessage = gitlet("checkout", wugFileName);
		assertEquals(
				"File does not exist in the most recent commit, or no such branch exists.",
				errorMessage.trim());
		assertEquals(changedText, getText(wugFileName));
	}
	
	
	/**
	 * Test that status prints out in the proper format
	 * 
	 * involves init, add, commit, rm
	 */
	
	@Test
	public void testBasicStatus() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String wugsFileName = TESTING_DIR + "wugs.txt";
		String wugsText = "There are two wugs.";
		createFile(wugsFileName, wugsText);

		gitlet("init");

		gitlet("add", wugFileName);
		gitlet("commit", "added wug");

		gitlet("add", wugsFileName);
		gitlet("rm", wugFileName);

		String statusText = "";
		PrintStream originalOut = System.out;
		try {
			/*
			 * I'm constructing statusText this way so I don't have to deal with
			 * operating system-dependent new lines... thanks Windows!
			 */
			ByteArrayOutputStream printingResults = new ByteArrayOutputStream();
			System.setOut(new PrintStream(printingResults));
			System.out.println("=== Branches ===");
			System.out.println("*master");
			System.out.println();
			System.out.println("=== Staged Files ===");
			System.out.println(wugsFileName);
			System.out.println();
			System.out.println("=== Files Marked for Untracking ===");
			System.out.println(wugFileName);
			System.out.println();
			statusText = printingResults.toString();
		} finally {
			System.setOut(originalOut);
		}
		assertEquals(statusText.trim(), gitlet("status").trim());
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
	 * Test that all failure cases of merge prints out
	 * appropriate error messages && 
	 * 
	 * Test that merge will generate 
	 * a .conflicted file if a file has been modified in 
	 * both branches since the split point. &&
	 * 
	 * Test that merge will commit with files from 
	 * the other branch if those files had been modified 
	 * in the other branch but not in the current 
	 * branch since the split point.
	 * 
	 * involves init, add, commit, branch, checkout, log, merge
	 */
	@Test
	public void testMerge() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String wugsFileName = TESTING_DIR + "wugs.txt";
		String wugsText = "There are two wugs.";
		createFile(wugsFileName, wugsText);
		
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		
		gitlet("branch", "more wugs");
		gitlet("checkout", "more wugs");
		writeFile (wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "jk not a wug");
		
		String errorMessage = gitlet("merge", "more wugs");
		assertEquals(
				"Cannot merge a branch with itself.", errorMessage.trim());
		
		errorMessage = gitlet("merge", "master");
		assertEquals(
				"No changes added to the commit.", errorMessage.trim());
		
		
		gitlet("checkout", "master");
		gitlet("branch", "temp");
		writeFile(wugFileName, "This is a mug.");
		gitlet("add", wugFileName);
		gitlet("commit", "mug");
		

		gitlet("checkout", "temp");
		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		gitlet("add", wugsFileName);
		gitlet("commit", "cool wugs");
		
		gitlet("merge", "master"); //
		gitlet("log");
		
		gitlet("checkout", "master");
		
		errorMessage = gitlet("merge", "more wugs");
		assertEquals(
				"Encountered a merge conflict.", errorMessage.trim());
		
		errorMessage = gitlet("rebase", "more wugs");
		assertEquals(
				"Cannot do this command until the merge conflict has been resolved.",
				 errorMessage.trim());
		
		errorMessage = gitlet("checkout", "more wugs");
		assertEquals(
				"Cannot do this command until the merge conflict has been resolved.",
				 errorMessage.trim());
		
		errorMessage = gitlet("checkout", "2", wugFileName);
		assertEquals("", errorMessage);
	}


	/**
	 * Test that all failure cases prints out appropriate messages &&
	 * 
	 * Test that the output of log after a rebase includes the 
	 * commit messages from both branches involved in the rebase.
	 * Test that changes in the base branch propagate through 
	 * the replayed branch during a rebase.
	 * 
	 * Involves init, add, commit, and log.
	 * 
	 */
	@Test
	public void testRebase() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "Dis wug.";
		createFile(wugFileName, wugText);

		String helloFileName = TESTING_DIR + "hello.txt";
		String helloText = "hi.";
		createFile(helloFileName, helloText);
		
		String catFileName = TESTING_DIR + "cat.txt";
		String catText = "KITTY.";
		createFile(catFileName, catText);
		
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("add", helloFileName);
		gitlet("commit", "added wug & hello");
		
		gitlet("branch", "temp");
		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		writeFile (wugFileName, "NO.");
		gitlet("add", wugFileName);
		gitlet("commit", "Changed wug to NO");
		
		gitlet("checkout", "master");
		gitlet("add", catFileName);
		gitlet("commit", "added kitty");
		
		writeFile (helloFileName, "BYE.");
		gitlet("add", helloFileName);
		gitlet("commit", "changed hi to bye");
		//end set up
		
		//Test Failure Case: No branch with given name
		String errorMessage = gitlet("rebase", "more wugs");
		assertEquals(
				"A branch with that name does not exist.", errorMessage.trim());
		
		//Test Failure Case: Rebase onto itself
		errorMessage = gitlet("rebase", "master");
		assertEquals(
				"Cannot rebase a branch onto itself.", errorMessage.trim());
		
		//Test Failure Case: Up to Date
		errorMessage = gitlet("rebase", "temp");
		assertEquals(
				"Already up-to-date.", errorMessage.trim());
		
		//Test Successful
		gitlet("rebase", "cool-beans");
		gitlet("log");
		
		gitlet("checkout", "cool-beans");
		gitlet("log");
		
		//Test when current is a history of given branch (current: cool-beans)
		gitlet("rebase", "master");
		String log1 = gitlet("log");
		
		gitlet("checkout", "master");
		String log2 = gitlet("log");
		
		assertEquals(log1, log2);
		
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