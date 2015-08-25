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
 * (Modified/adapted from the project specifications: )
 * 
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
	 * Test that a file that has been committed at some point can be restored by
	 * checking it out from the commit it was committed at, even if the file
	 * wasn't staged prior to that commit.
	 */
	@Test
	public void testDelayedCheckout() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");

		String fooFileName = TESTING_DIR + "foo.txt";
		createFile(fooFileName, "This is a foo.");
		gitlet("add", fooFileName);
		gitlet("commit", "added foo");

		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", wugFileName);
		assertEquals(wugText, getText(wugFileName));
	}

	/**
	 * Test that a file that a file can be checked out from an arbitrary commit
	 * in the graph (even one in another branch).
	 */
	@Test
	public void testCheckoutFromID() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "This is a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");

		String fooFileName = TESTING_DIR + "foo.txt";
		createFile(fooFileName, "This is a foo.");
		gitlet("add", fooFileName);
		gitlet("commit", "added foo");

		writeFile(wugFileName, "This is not a wug.");
		String wugID = gitlet("find", "added wug");
		gitlet("checkout", wugID, wugFileName);
		assertEquals("This is a wug.", getText(wugFileName));

		String barFileName = TESTING_DIR + "bar.txt";
		createFile(barFileName, "This is a bar.");
		gitlet("branch", barFileName);
		gitlet("checkout", barFileName);
		gitlet("add", barFileName);
		gitlet("commit", "added bar");
		gitlet("checkout", "master");

		createFile(barFileName, "This is another bar.");
		gitlet("add", barFileName);
		gitlet("commit", "added another bar");

		String barID = gitlet("find", "added bar");
		gitlet("checkout", barID, barFileName);
		assertEquals("This is a bar.", getText(barFileName));
	}

	/**
	 * Test that resetting backward appropriately changes the output of log.
	 */
	@Test
	public void testReset() {
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

		String commit1ID = gitlet("find", commitMessage1);
		gitlet("reset", commit1ID);

		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage1 },
				extractCommitMessages(logContent));
	}

	/**
	 * Test that log adjusts appropriately when switching from one branch to
	 * another.
	 */
	@Test
	public void testBranchLog() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "This is a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");

		String fooFileName = TESTING_DIR + "foo.txt";
		createFile(fooFileName, "This is a foo.");
		gitlet("add", fooFileName);
		gitlet("commit", "added foo");

		String barFileName = TESTING_DIR + "bar.txt";
		createFile(barFileName, "This is a bar.");
		gitlet("branch", barFileName);
		gitlet("checkout", barFileName);
		gitlet("add", barFileName);
		gitlet("commit", "added bar");

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { "added bar", "added foo", "added wug",
				"initial commit" }, extractCommitMessages(logContent));

		gitlet("checkout", "master");

		createFile(barFileName, "This is another bar.");
		gitlet("add", barFileName);
		gitlet("commit", "added another bar");

		logContent = gitlet("log");
		assertArrayEquals(new String[] { "added another bar", "added foo",
				"added wug", "initial commit" },
				extractCommitMessages(logContent));
	}

	/**
	 * Test that merge will generate a .conflicted file if a file has been
	 * modified in both branches since the split point.
	 */
	@Test
	public void testMergeConflict() {
		setUpBranches();
		String wugFileName = TESTING_DIR + "wug.txt";
		String fooFileName = TESTING_DIR + "foo.txt";
		String fizzFileName = TESTING_DIR + "fizz.txt";
		String fuzzFileName = TESTING_DIR + "fuzz.txt";
		String buzzFileName = TESTING_DIR + "buzz.txt";
		String barFileName = TESTING_DIR + "bar.txt";

		gitlet("merge", barFileName);

		assertEquals("", getText(wugFileName + ".conflicted"));
		assertEquals("", getText(fooFileName + ".conflicted"));
		assertEquals("", getText(fizzFileName + ".conflicted"));
		assertEquals("", getText(fuzzFileName + ".conflicted"));
		assertEquals("", getText(buzzFileName + ".conflicted"));
		assertEquals("This is another bar.", getText(barFileName));
		assertEquals("This is a bar.", getText(barFileName + ".conflicted"));

		gitlet("commit", "ignoring conflicts");
		gitlet("checkout", barFileName);
		File f = new File(TESTING_DIR);
		if (f.exists()) {
			try {
				recursiveDelete(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		gitlet("checkout", "master");

		assertEquals("This is a wug.", getText(wugFileName));
		assertEquals("", getText(fooFileName));
		assertEquals("", getText(fizzFileName));
		assertEquals("This is a rewritten fuzz.", getText(fuzzFileName));
		assertEquals("This is a rewritten buzz.", getText(buzzFileName));

	}

	/**
	 * Test that the output of log after a rebase includes the commit messages
	 * from both branches involved in the rebase.
	 */
	@Test
	public void testRebaseLog() {
		setUpBranches();

		gitlet("checkout", TESTING_DIR + "bar.txt");
		gitlet("rebase", "master");

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { "added bar", "rewrote fuzz",
				"removed foo", "added another bar", "rewrote buzz",
				"removed fizz", "added buzz", "added fuzz", "added fizz",
				"added foo", "added wug", "initial commit" },
				extractCommitMessages(logContent));

	}

	/**
	 * Test that changes in the base branch propagate through the replayed
	 * branch during a rebase.
	 */
	@Test
	public void testRebasePropagate() {
		setUpBranches();
		String wugFileName = TESTING_DIR + "wug.txt";
		String fooFileName = TESTING_DIR + "foo.txt";
		String fizzFileName = TESTING_DIR + "fizz.txt";
		String fuzzFileName = TESTING_DIR + "fuzz.txt";
		String buzzFileName = TESTING_DIR + "buzz.txt";
		String barFileName = TESTING_DIR + "bar.txt";

		gitlet("checkout", barFileName);
		gitlet("rebase", "master");

		gitlet("checkout", "master");
		File f = new File(TESTING_DIR);
		if (f.exists()) {
			try {
				recursiveDelete(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		gitlet("checkout", barFileName);

		assertEquals("This is a wug.", getText(wugFileName));
		assertEquals("", getText(fooFileName));
		assertEquals("", getText(fizzFileName));
		assertEquals("This is a rewritten fuzz.", getText(fuzzFileName));
		assertEquals("This is a rewritten buzz.", getText(buzzFileName));
		assertEquals("This is a bar.", getText(barFileName));

	}

	/**
	 * Helper method to set up the gitlet environment for some tests.
	 */
	private void setUpBranches() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "This is a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");

		String fooFileName = TESTING_DIR + "foo.txt";
		createFile(fooFileName, "This is a foo.");
		gitlet("add", fooFileName);
		gitlet("commit", "added foo");

		String fizzFileName = TESTING_DIR + "fizz.txt";
		createFile(fizzFileName, "This is a fizz.");
		gitlet("add", fizzFileName);
		gitlet("commit", "added fizz");

		String fuzzFileName = TESTING_DIR + "fuzz.txt";
		createFile(fuzzFileName, "This is a fuzz.");
		gitlet("add", fuzzFileName);
		gitlet("commit", "added fuzz");

		String buzzFileName = TESTING_DIR + "buzz.txt";
		createFile(buzzFileName, "This is a buzz.");
		gitlet("add", buzzFileName);
		gitlet("commit", "added buzz");

		String barFileName = TESTING_DIR + "bar.txt";
		gitlet("branch", barFileName);
		gitlet("checkout", barFileName);

		gitlet("rm", fooFileName);
		gitlet("commit", "removed foo");

		writeFile(fuzzFileName, "This is a rewritten fuzz.");
		gitlet("add", fuzzFileName);
		gitlet("commit", "rewrote fuzz");

		createFile(barFileName, "This is a bar.");
		gitlet("add", barFileName);
		gitlet("commit", "added bar");

		gitlet("checkout", "master");

		gitlet("rm", fizzFileName);
		gitlet("commit", "removed fizz");

		writeFile(buzzFileName, "This is a rewritten buzz.");
		gitlet("add", buzzFileName);
		gitlet("commit", "rewrote buzz");

		createFile(barFileName, "This is another bar.");
		gitlet("add", barFileName);
		gitlet("commit", "added another bar");
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
		// System.out.println(results);
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
/*
	private void assertTrue(boolean toAssert) {
		System.out.println("Asserting true:\t" + toAssert + "\n\t\t\t-   "
				+ (toAssert ? "PASS" : "FAIL"));
	}

	private void assertEquals(Object expected, Object actual) {
		System.out.println("Asserting equals:\t" + expected + "\n\t\t\t"
				+ actual + "\t-   "
				+ (expected.equals(actual) ? "PASS" : "FAIL"));
	}

	private void assertArrayEquals(Object[] expected, Object[] actual) {
		assertEquals(Arrays.toString(expected), Arrays.toString(actual));
	}
*/
	public static void main(String[] args) {
		GitletTest test = new GitletTest();
		if (args.length > 0) {
			test.setUp();
			return;
		}
		System.out.println("Testing basic initialize:");
		test.setUp();
		test.testBasicInitialize();
		System.out.println();
		System.out.println("Testing basic checkout:");
		test.setUp();
		test.testBasicCheckout();
		System.out.println();
		System.out.println("Testing basic log:");
		test.setUp();
		test.testBasicLog();
		System.out.println();
		System.out.println("Testing delayed checkout:");
		test.setUp();
		test.testDelayedCheckout();
		System.out.println();
		System.out.println("Testing checkout from id:");
		test.setUp();
		test.testCheckoutFromID();
		System.out.println();
		System.out.println("Testing reset:");
		test.setUp();
		test.testReset();
		System.out.println();
		System.out.println("Testing branch log:");
		test.setUp();
		test.testBranchLog();
		System.out.println();
		System.out.println("Testing merge conflict:");
		test.setUp();
		test.testMergeConflict();
		System.out.println();
		System.out.println("Testing rebase log:");
		test.setUp();
		test.testRebaseLog();
		System.out.println();
		System.out.println("Testing rebase propagate:");
		test.setUp();
		test.testRebasePropagate();
	}
}