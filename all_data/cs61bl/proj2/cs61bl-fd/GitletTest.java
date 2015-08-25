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
 * methods. Adapted from Joseph Moghadam's GitletTest.java example tests.
 * 
 * @author Rohan Nijhawan, Rocky Kamen-Rubio, Jeffrey Tang, and Mira Chiu
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
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 }, extractCommitMessages(logContent));
	}

	@Test
	public void testCommit() {
		String testFileName = "testfile.txt";
		String testText = "commit node one";
		createFile(testFileName, testText);
		gitlet("init");
		gitlet("add", testFileName);
		gitlet("commit", "added testFile");
		writeFile(testFileName, "changed local text");
		gitlet("checkout", testFileName);
		assertEquals(testText, getText(testFileName));
	}

	@Test
	public void testResetLog() {
		gitlet("init");
		String fileName1 = "file1.txt";
		String fileText1 = "file1 one";
		createFile(fileName1, fileText1);

		String c1 = "commit node one";
		String c2 = "commit node two";
		String c3 = "commit node three";
		String i = "initial commit";

		gitlet("add", fileName1);
		gitlet("commit", c1);

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { c1, i }, extractCommitMessages(logContent));

		gitlet("add", fileName1);
		gitlet("commit", c2);

		logContent = gitlet("log");
		assertArrayEquals(new String[] { c2, c1, i }, extractCommitMessages(logContent));

		gitlet("add", fileName1);
		gitlet("commit", "commit node three");

		logContent = gitlet("log");
		assertArrayEquals(new String[] { c3, c2, c1, i }, extractCommitMessages(logContent));

		gitlet("reset", "2");

		logContent = gitlet("log");
		assertArrayEquals(new String[] { c2, c1, i }, extractCommitMessages(logContent));

	}

	@Test
	public void testMergeRohan() {
		gitlet("init");
		String fileName1 = "file1.txt";
		String fileText1 = "file1 one";
		String fileName2 = "file2.txt";
		String fileText2 = "file2 one";
		createFile(fileName1, fileText1);
		createFile(fileName2, fileText2);
		gitlet("add", fileName1);
		gitlet("add", fileName2);
		gitlet("commit", "commit node one");

		writeFile(fileName1, "file1 two");
		writeFile(fileName2, "file2 two");
		gitlet("add", fileName1);
		gitlet("add", fileName2);
		gitlet("commit", "commit node two");

		gitlet("branch", "testbranch");
		gitlet("checkout", "testbranch");

		writeFile(fileName1, "file1 three");
		gitlet("add", fileName1);
		gitlet("commit", "commit node three");

		writeFile(fileName1, "file1 four");
		gitlet("add", fileName1);
		gitlet("commit", "commit node four");

		gitlet("checkout", "master");

		writeFile(fileName2, "file2 five");
		gitlet("add", fileName2);
		gitlet("commit", "commit node five");

		gitletFast("merge", "testbranch");
		gitletFast("checkout", "testbranch");
		gitletFast("checkout", "master");
		assertEquals("file1 four", getText(fileName1));
		assertEquals("file2 five", getText(fileName2));
	}

	// =======
	@Test
	public void testInit() {
		String message = "A gitlet version control system already exists in the current directory.";
		gitlet("init");
		File f = new File(".gitlet");
		File g = new File(".gitlet/staged");
		File h = new File(".gitlet/marked");
		File i = new File(".gitlet/storage");
		assertTrue(f.exists());
		assertTrue(g.exists());
		assertTrue(h.exists());
		assertTrue(i.exists());
		gitlet("init");
		assertEquals(message, gitlet("init"));
	}

	@Test
	public void testLog() {
		gitlet("init");
		String commitMessage1 = "initial commit";

		String present = "B.txt";
		String presentText = "BBB";
		createFile(present, presentText);
		String commitMessage2 = "Successfully removed " + present + " from the marked for untracking folder.";
		gitlet("add", present);
		gitlet("commit", commitMessage2);

		String fileName = "A.txt";
		String fileText = "ABC";
		createFile(fileName, fileText);
		gitlet("add", fileName);
		String commitMessage3 = "Successfully added " + fileName + " to the staging area.";
		gitlet("commit", commitMessage3);

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
	}

	@Test
	public void testCheckoutBranch() {
		gitlet("init");
		String fileName = "A.txt";
		String fileText = "ABC";
		createFile(fileName, fileText);
		gitlet("add", fileName);
		String commitMessage1 = "Successfully added " + fileName + " to the staging area.";
		gitlet("commit", commitMessage1);
		String branch2 = "fun_branch";
		gitlet("branch", branch2);
		gitlet("checkout", branch2);
		writeFile(fileName, "BCD");
		gitlet("add", fileName);
		String commitMessage2 = "Successfully added " + fileName + " to the staging area.";
		gitlet("commit", commitMessage2);
		gitlet("checkout", "master");
		assertEquals("ABC", getText(fileName));
		gitlet("checkout", branch2);
		assertEquals("BCD", getText(fileName));
	}

	@Test
	public void testReset() {
		gitlet("init");
		String fileName = ".gitlet/" + "A.txt";
		String fileText = "ABC";
		createFile(fileName, fileText);
		gitlet("add", fileName);
		String commitMessage2 = "Successfully added " + fileName + " to the staging area.";
		gitlet("commit", commitMessage2);
		writeFile(fileName, "BCD");
		gitlet("add", fileName);
		String commitMessage3 = "Successfully added " + fileName + " to the staging area.";
		gitlet("commit", commitMessage3);
		gitlet("reset", "1");
		assertEquals("ABC", fileText);
	}

	@Test
	public void testBulletPoint8() {
		gitlet("init");
		String commitMessage0 = "initial commit";
		String fileName = "A.txt";
		String fileText = "ABC";
		createFile(fileName, fileText);
		gitlet("add", fileName);
		String commitMessage1 = "Commit 1";
		gitlet("commit", commitMessage1);
		String branch2 = "fun_branch";
		gitlet("branch", branch2);
		gitlet("checkout", branch2);
		writeFile(fileName, "BCD");
		gitlet("add", fileName);
		String commitMessage2 = "2nd Commit";
		gitlet("commit", commitMessage2);
		writeFile(fileName, "CCC");
		String commitMessage3 = "3rd Commit";
		gitlet("add", fileName);
		gitlet("commit", commitMessage3);
		gitlet("checkout", "master");
		writeFile(fileName, "DDD");
		gitlet("add", fileName);
		String commitMessage4 = "4th Commit";
		gitlet("commit", commitMessage4);
		gitlet("rebase", branch2);
		String logContent = gitlet("log");
		assertArrayEquals(
				new String[] { commitMessage4, commitMessage3, commitMessage2, commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));
	}

	// ===========

	@Test
	public void testAddAndStatus() { // Modeled off of testBasicCheckout
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("commit", "commit 1");

		String s = "=== Branches ===";
		s += ("\n");
		s += ("*master");
		s += ("\n");
		s += ("\n");
		s += ("=== Staged Files ===");
		s += ("\n");
		s += ("test_files/wug.txt");
		s += ("\n");
		s += ("\n");
		s += ("=== Files Marked for Untracking ===");
		gitlet("add", wugFileName);
		assertEquals(s, gitlet("status"));

		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		gitlet("commit", "commit 2");

		s = "=== Branches ===";
		s += ("\n");
		s += ("*cool-beans");
		s += ("\n");
		s += ("master");
		s += ("\n");
		s += ("\n");
		s += ("=== Staged Files ===");
		s += ("\n");
		s += ("\n");
		s += ("=== Files Marked for Untracking ===");
		assertEquals(s, gitlet("status"));

	}

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Test
	public void testCheckout() { // Modeled off of testBasicCheckout
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);

		assertEquals("File does not exist in the most recent commit, or no such branch exists.",
				gitlet("checkout", wugFileName));
		// assertEquals(
		// "File does not exist in the most recent commit, or no such branch
		// exists.",
		// outContent.toString());
		// Test correct error message is printed

		gitlet("commit", "Commit 1");
		writeFile(wugFileName, "This is not a wug anymore.");
		gitlet("add", wugFileName);
		gitlet("checkout", wugFileName);
		assertTrue(getText(wugFileName).equals("This is a wug."));
		// Check that previously commited version overwrites current one.
	}

	@Test
	public void testMerge2() {
		// Test it doesn't modify if another branch with split point version is
		// merged into current one which is modified
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "split point commit");
		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		gitlet("add", wugFileName);
		gitlet("commit", "cool-beans commit 1"); // make a branch called
													// cool-beans and keep wug
													// the same in that branch
		gitlet("checkout", "master");
		writeFile(wugFileName, "This is a master wug"); // Modify the version in
														// the master branch
		gitlet("add", wugFileName);
		gitlet("commit");
		gitlet("merge", "cool-beans"); // should print
										// "Merged cool-beans with master"
		gitlet("checkout", "Merged cool-beans with master"); // confirms a
																// commit with
																// this message
																// was made
		gitlet("merge", "cool-beans");
		assertEquals("This is a master wug", getText(wugFileName));
	}

	@Test
	public void testMerge3() {
		// Test merging two files, both modified from split point version. They
		// should become conflicted and git should enter a conflicted state, and
		// printed "Encountered a merge conflict."
		// Check only add, rm, commit, checkout [file], checkout [id] [file],
		// log, global-log, find, and status are allowed.
		// Should print
		// " Cannot do this command until the merge conflict has been resolved."
		// if any other commands are attempted
		// Check conflict state ends with a commit
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "split point commit");
		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		writeFile(wugFileName, "This is a cool-beans wug");
		gitlet("add", wugFileName);
		gitlet("commit", "cool-beans commit 1");
		gitlet("checkout", "master");
		writeFile(wugFileName, "This is a master wug");
		gitlet("add", wugFileName);
		gitlet("commit", "master commit 1");
		gitlet("merge", "cool-beans");
		gitlet("checkout", wugFileName);
		gitlet("checkout", "3", wugFileName);
		gitlet("rm", wugFileName);

		assertEquals("Cannot do this command until the merge conflict has been resolved.",
				gitlet("branch", "test-branch"));
		// expectedEx.expect(RuntimeException.class);
		// expectedEx
		// .expectMessage("Cannot do this command until the merge conflict has
		// been resolved.");

		String pangolenFileName = TESTING_DIR + "pangolen.txt"; // These should
																// all be legal
																// in a
																// conflicted
																// state
		String pangolenText = "This is a pangolen.";
		createFile(pangolenFileName, pangolenText);
		gitlet("add", pangolenFileName);
		gitlet("commit", "after merge commit 1"); // Should now have exited the
													// conflicted state

		assertEquals("A branch with that name does not exist.", gitlet("merge", "non-existant-branch"));
		assertEquals("Cannot merge a branch with itself.", gitlet("merge", "master"));
	}

	// Check giving a branch that does not exist prints
	// "A branch with that name does not exist."
	// Check merging a branch with itself errors
	// "Cannot merge a branch with itself."

	@Test
	public void testBulletPoint9() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "commit 1");
		gitlet("branch", "cool-beans");
		gitlet("add", wugFileName);
		gitlet("commit", "commit 2");
		writeFile(wugFileName, "This is a second wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "commit 3");
		gitlet("checkout", "cool-beans");
		gitlet("add", wugFileName);
		gitlet("commit", "commit 4");
		gitlet("add", wugFileName);
		gitlet("commit", "commit 5");
		gitlet("checkout", "master");
		gitlet("rebase", "cool-beans");
		gitlet("checkout", "3", wugFileName);
		assertEquals(("This is a second wug."), getText(wugFileName));
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
			try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));) {
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