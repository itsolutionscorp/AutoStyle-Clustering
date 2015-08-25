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
 * @since TUE JUL 28 2015
 * 
 * @author Joseph Moghadam
 * 
 *         <p>
 *         co-author
 *         </p>
 * @author Zihao Jing
 * @author Pingao Liu
 * @author Yuchao Gao
 * 
 *         <p>
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
 *         </p>
 * 
 */
public class GitletTest {
	private static final String GITLET_DIR = ".gitlet/";
	private static final String INDEX_DIR = ".gitlet/.index/.";
	private static final String TESTING_DIR = "test_files/";
	private static final String INITIAL_CMT = "initial commit";

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
	@SuppressWarnings("unused")
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
		// System.out.println(printingResults.toString());
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

	@Test
	public void testFailureInitialize() {
		gitlet("init");
		gitlet("init");
		File f = new File(GITLET_DIR);
		assertTrue(f.exists());
		String error_msg = gitlet("init");
		assertEquals("A gitlet version control system already exists "
				+ "in the current directory.", error_msg);
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
	 * Test the checkout method with given branch name.
	 */
	@Test
	public void testBranchCheckout() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "wug");
		gitlet("branch", "monster");
		gitlet("checkout", "monster");
		writeFile(wugFileName, "This is a monster.");
		gitlet("add", wugFileName);
		gitlet("commit", "monster_wug");
		gitlet("checkout", "master");
		writeFile(wugFileName, "This is a master wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "master_wug");
		gitlet("checkout", "monster");
		assertEquals("This is a monster.", getText(wugFileName));
		String error = gitlet("checkout", "no_branchname");
		assertEquals("File does not exist in the most recent commit, "
				+ "or no such branch exists.", error);
	}

	/**
	 * Test that a file that has been committed at some point can be restored by
	 * checking it out from the commit it was committed at.
	 */
	@Test
	public void testCurrentFileCheckout() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String wugsFileName = TESTING_DIR + "wugs.txt";
		String wugsText = "There are two wugs.";
		createFile(wugsFileName, wugsText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "wug");
		gitlet("checkout", "1", wugFileName);
		assertEquals(wugText, getText(wugFileName));
		gitlet("add", wugsFileName);
		gitlet("commit", "wugs");
		assertEquals(wugsText, getText(wugsFileName));

	}

	/**
	 * Test that a file that has been committed at some point can be restored by
	 * checking it out from a commit that tracks that version of the file, even
	 * if the file wasn't staged prior to that commit.
	 */
	@Test
	public void testUnstagedFileCheckout() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String wugsFileName = TESTING_DIR + "wugs.txt";
		String wugsText = "There are two wugs.";
		createFile(wugsFileName, wugsText);
		String monsterFileName = TESTING_DIR + "monster.txt";
		String monsterText = "This is a monster.";
		createFile(monsterFileName, monsterText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "wug");
		gitlet("add", wugsFileName);
		gitlet("commit", "wugs");
		gitlet("add", monsterFileName);
		gitlet("commit", "monster");
		gitlet("checkout", "1", wugFileName);
		assertEquals(wugText, getText(wugFileName));
		gitlet("checkout", "2", wugFileName);
		assertEquals(wugText, getText(wugFileName));

	}

	/**
	 * Test that the checkout [id] [file] from an arbitrary commit in the graph
	 * (even one in another branch).
	 */
	@Test
	public void testIdFileCheckout() {
		String wugsFileName = TESTING_DIR + "wugs.txt";
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugsText = "There are two wugs.";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "wug");
		String error_id = gitlet("checkout", "5", wugFileName);
		assertEquals("No commit with that id exists.", error_id);
		String error_filename = gitlet("checkout", "1", wugsFileName);
		assertEquals("File does not exist in that commit.", error_filename);
		writeFile(wugFileName, "This is a stupid wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "stupid_wug");
		gitlet("checkout", "1", wugFileName);
		assertEquals("This is a wug.", getText(wugFileName));
		gitlet("branch", "new_master");
		createFile(wugsFileName, wugsText);
		gitlet("add", wugsFileName);
		gitlet("commit", "wugs");
		gitlet("checkout", "1", wugFileName);
		assertEquals("This is a wug.", getText(wugFileName));
	}

	/**
	 * Test if the file name is the same as a branch name, we will make the
	 * branch name take precedence.
	 */
	@Test
	public void testPriorityCheckout() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "wug");

		String wugsFileName = TESTING_DIR + "wugs.txt";
		String wugsText = "There are two wugs.";
		createFile(wugsFileName, wugsText);
		gitlet("add", wugsFileName);
		gitlet("commit", "wugs");

		gitlet("branch", "wug.txt");

		writeFile(wugsFileName, "There are three wugs.");
		gitlet("add", wugsFileName);

		gitlet("checkout", wugFileName);
		assertEquals("There are three wugs.", getText(wugsFileName));

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
	 * Test that resetting backward appropriately changes the output of log.
	 */
	@Test
	public void testResetLog() {
		gitlet("init");
		String commitMessage1 = "initial commit";

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "wug";
		gitlet("commit", commitMessage2);

		String wugsFileName = TESTING_DIR + "wugs.txt";
		String wugsText = "There are two wugs.";
		createFile(wugsFileName, wugsText);
		gitlet("add", wugsFileName);
		String commitMessage3 = "wugs";
		gitlet("commit", commitMessage3);

		String monsterFileName = TESTING_DIR + "monster.txt";
		String monsterText = "This is a monster.";
		createFile(monsterFileName, monsterText);
		gitlet("add", monsterFileName);
		String commitMessage4 = "monster";
		gitlet("commit", commitMessage4);

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage4, commitMessage3,
				commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));

		gitlet("reset", "2");

		String new_logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2,
				commitMessage1 }, extractCommitMessages(new_logContent));
	}

	/**
	 * Test that log adjusts appropriately when switching from one branch to
	 * another.
	 */
	@Test
	public void testSwitchLog() {
		gitlet("init");
		String commitMessage1 = "initial commit";

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "wug";
		gitlet("commit", commitMessage2);

		writeFile(wugFileName, "This is a masterwug.");
		gitlet("add", wugFileName);
		String commitMessage3 = "masterwug";
		gitlet("commit", commitMessage3);

		String old_logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2,
				commitMessage1 }, extractCommitMessages(old_logContent));

		gitlet("branch", "new_master");
		gitlet("checkout", "new_master");

		writeFile(wugFileName, "This is a stupidwug.");
		gitlet("add", wugFileName);
		String commitMessage4 = "stupidwug";
		gitlet("commit", commitMessage4);

		gitlet("checkout", "master");

		writeFile(wugFileName, "This is a funnywug.");
		gitlet("add", wugFileName);
		String commitMessage5 = "funnywug";
		gitlet("commit", commitMessage5);

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage5, commitMessage3,
				commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
	}

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
		assertEquals("File does not exist in the most recent commit, "
				+ "or no such branch exists.", errorMessage.trim());
		assertEquals(changedText, getText(wugFileName));
	}

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

	@Test
	public void testRomoveBranch() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		gitlet("init");

		gitlet("add", wugFileName);
		gitlet("commit", "wug");

		gitlet("branch", "new_master");
		gitlet("checkout", "new_master");
		String error = gitlet("rm-branch", "new_master");
		assertEquals("Cannot remove the current branch.", error);
		gitlet("rm-branch", "master");
		String error_rm = gitlet("checkout", "master");
		assertEquals("File does not exist in the most recent "
				+ "commit, or no such branch exists.", error_rm);
	}

	/**
	 * Test NONConflict merge including removal modification.
	 */
	@Test
	public void testNonConflictMerge() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String wugsFileName = TESTING_DIR + "wugs.txt";
		String wugsText = "There are two wugs.";
		createFile(wugsFileName, wugsText);

		gitlet("init");

		gitlet("add", wugFileName);
		gitlet("add", wugsFileName);
		gitlet("commit", "origin_wugs");

		gitlet("branch", "monster");
		gitlet("checkout", "monster");
		String mons_msg = "ahhhh";
		writeFile(wugFileName, "This is a monster.");
		gitlet("add", wugFileName);
		gitlet("commit", mons_msg);

		gitlet("checkout", "master");
		String nowugFileName = TESTING_DIR + "nowug.txt";
		String nowugText = "There is no wug.";
		createFile(nowugFileName, nowugText);
		gitlet("add", nowugFileName);
		gitlet("rm", wugsFileName);
		gitlet("commit", "chile master");

		gitlet("merge", "monster");

		assertEquals(nowugText, getText(nowugFileName));
		assertEquals("This is a monster.", getText(wugFileName));
		File test_wugs = new File(".gitlet/.index/.4" + TESTING_DIR
				+ "wugs.txt");
		assertTrue(!test_wugs.exists());
	}

	/**
	 * Test a given branch
	 */
	@Test
	public void testGivenBranchMerge() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String wugsFileName = TESTING_DIR + "wugs.txt";
		String wugsText = "There are two wugs.";
		createFile(wugsFileName, wugsText);

		gitlet("init");

		gitlet("add", wugFileName);
		gitlet("add", wugsFileName);
		gitlet("commit", "origin_wugs");

		gitlet("branch", "weakwug");
		gitlet("checkout", "weakwug");

		writeFile(wugFileName, "This is a weak wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "weak_wug");

		gitlet("checkout", "master");

		gitlet("merge", "weakwug");
		File check = new File(".gitlet/.index/.3/" + TESTING_DIR + "wug.txt");
		assertTrue(check.exists());
		assertEquals("This is a weak wug.", getText(wugFileName));

	}

	/**
	 * Test conflicted state merge
	 */
	@Test
	public void testConflictMerge() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String wugsFileName = TESTING_DIR + "wugs.txt";
		String wugsText = "There are two wugs.";
		createFile(wugsFileName, wugsText);

		gitlet("init");

		gitlet("add", wugFileName);
		gitlet("add", wugsFileName);
		gitlet("commit", "origin_wugs");

		gitlet("branch", "weakwugs");
		gitlet("checkout", "weakwugs");

		writeFile(wugsFileName, "There are two weak wugs.");
		gitlet("add", wugsFileName);
		gitlet("commit", wugsFileName);

		gitlet("checkout", "master");

		writeFile(wugsFileName, "There are strong wugs.");
		gitlet("add", wugsFileName);
		gitlet("commit", "strongwug");

		gitlet("merge", "weakwugs");

		String error_command = gitlet("branch", "error");
		assertEquals("Cannot do this command until the merge "
				+ "conflict has been resolved.", error_command);

		File conflict = new File(TESTING_DIR + "wugs.txt.conflicted");
		assertTrue(conflict.exists());

		gitlet("add", TESTING_DIR + "wugs.txt.conflicted");
		gitlet("commit", "merger");

		String nothing = gitlet("branch", "charger");
		assertTrue(nothing.isEmpty());

		File check = new File(".gitlet/.index/.3/" + TESTING_DIR + "wug.txt");
		assertTrue(check.exists());
	}

	/**
	 * Test rebase in basic situations in one method and a basic propagate
	 */
	@Test
	public void testBasicRebase() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String wugsFileName = TESTING_DIR + "wugs.txt";
		String wugsText = "There are two wugs.";
		createFile(wugsFileName, wugsText);

		gitlet("init");

		gitlet("add", wugFileName);
		gitlet("add", wugsFileName);
		String commitMessage1 = "origin_wugs";
		gitlet("commit", commitMessage1);

		gitlet("branch", "new");
		gitlet("checkout", "new");

		String monText = "This is a monster.";
		writeFile(wugFileName, monText);

		gitlet("add", wugFileName);
		String commitMessage2 = "monster";
		gitlet("commit", commitMessage2);

		gitlet("checkout", "master");

		String wgFileName = TESTING_DIR + "wg.txt";
		String wgText = "This is wg.";
		String commitMessage3 = "wg";
		createFile(wgFileName, wgText);
		gitlet("add", wgFileName);
		gitlet("commit", commitMessage3);

		gitlet("rebase", "new");

		String logContent = gitlet("log");
		/**
		 * Test that the output of log after a rebase includes the commit
		 * messages from both branches involved in the rebase.
		 */
		assertArrayEquals(new String[] { commitMessage3, commitMessage2,
				commitMessage1, INITIAL_CMT },
				extractCommitMessages(logContent));

		/**
		 * Test that changes in the base branch propagate through the replayed
		 * branch during a rebase.
		 */
		assertTrue(getText(INDEX_DIR + "4/" + wugFileName).equals(monText));

	}

	/**
	 * Test rebase failure case
	 */
	@Test
	public void testRebaseAlreadyUpToDate() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String wugsFileName = TESTING_DIR + "wugs.txt";
		String wugsText = "There are two wugs.";
		createFile(wugsFileName, wugsText);

		gitlet("init");

		gitlet("add", wugFileName);
		gitlet("add", wugsFileName);
		String commitMessage1 = "origin_wugs";
		gitlet("commit", commitMessage1);

		gitlet("branch", "new");
		gitlet("checkout", "new");

		String monText = "This is a monster.";
		writeFile(wugFileName, monText);

		gitlet("add", wugFileName);
		String commitMessage2 = "monster";
		gitlet("commit", commitMessage2);

		String error = gitlet("rebase", "master");
		assertEquals("Already up-to-date.", error);

	}

	//
	// Okay
	//
	// let's see the blueprint of the next test
	//
	// before rebase:
	// ----/O---O <--this is given branch
	// O---O
	// ----\O---O <--this is current branch
	//
	// this is commit 6...7
	// after rebase: / . /
	// ----/O---O---O---O
	// O---O
	// ----\O---O
	//
	// so, now the file in commit 6 would propagate from the file in given
	// branch, while the file in commit 7 would not propagate because
	// the file in current branch is also modified and
	// is different from the file in split point
	// the same to more commits here
	/**
	 * Test a complicated propagate, in this test, both current files and given
	 * files are modified, so this is a more difficult propagate
	 */
	@Test
	public void testMoreRebase() {

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String wugsFileName = TESTING_DIR + "wugs.txt";
		String wugsText = "There are two wugs.";
		createFile(wugsFileName, wugsText);

		gitlet("init");

		gitlet("add", wugFileName);
		gitlet("add", wugsFileName);
		String commitMessage1 = "origin_wugs";
		gitlet("commit", commitMessage1);

		gitlet("branch", "new");
		gitlet("checkout", "new");

		String monText = "This is a monster.";
		writeFile(wugFileName, monText);
		gitlet("add", wugFileName);
		String commitMessage2 = "monster";
		gitlet("commit", commitMessage2);

		String monText2 = "This is a monster.";
		String monFileName = TESTING_DIR + "mon.txt";
		createFile(monFileName, monText2);
		gitlet("add", monFileName);
		String commitMessage2_2 = "monster2";
		gitlet("commit", commitMessage2_2);

		gitlet("checkout", "master");

		String wgFileName = TESTING_DIR + "wg.txt";
		String wgText = "This is wg.";
		String commitMessage3 = "wg";
		createFile(wgFileName, wgText);
		gitlet("add", wgFileName);
		gitlet("commit", commitMessage3);

		String secText = "This is second wug.";
		writeFile(wugFileName, secText);
		gitlet("add", wugFileName);
		String commitMessage1_2 = "more_wugs";
		gitlet("commit", commitMessage1_2);

		gitlet("rebase", "new");

		String logContent = gitlet("log");
		assertArrayEquals(
				new String[] { commitMessage1_2, commitMessage3,
						commitMessage2_2, commitMessage2, commitMessage1,
						INITIAL_CMT }, extractCommitMessages(logContent));

		String idThreeCmtWug = INDEX_DIR + "3/" + wugFileName;
		assertEquals(monText, getText(idThreeCmtWug));

		String idSixCmtWug = INDEX_DIR + "6/" + wugFileName;
		assertEquals(monText, getText(idSixCmtWug));

		String idSevenCmtWug = INDEX_DIR + "7/" + wugFileName;
		assertEquals(secText, getText(idSevenCmtWug));

		assertEquals(secText, getText(wugFileName));

	}
}