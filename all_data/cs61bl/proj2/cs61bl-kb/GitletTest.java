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
		assertTrue(f.exists());
	}

	/**
	 * Tests that checking out a file name will restore the version of the file
	 * from the previous commit. Involves init, add, commit, and checkout.
	 * Also tests if checkout works for a file that is inherited.
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

	/** Tests creation of new branch, branch checkout, removing a branch and branch checkout failure. */
	@Test
	public void testBranchCheckout() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		String wugBranch = "wug";
		String oneBranch = "one";
		gitlet("branch", wugBranch);
		gitlet("branch", oneBranch);
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug again");

		gitlet("rm-branch", oneBranch);
		String oneFail = gitlet("checkout", oneBranch);
		assertEquals("File does not exist in the most recent commit, or no such branch exists.", oneFail);

		gitlet("checkout", wugBranch);
		assertEquals(wugText, getText(wugFileName));
		gitlet("checkout", "master");
		assertEquals("This is not a wug.", getText(wugFileName));
	}

	/** Tests ID and file name checkout. */
	@Test
	public void testIDCheckout() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug again");
		gitlet("checkout", "1", wugFileName);
		assertEquals(wugText, getText(wugFileName));
		gitlet("checkout", "2", wugFileName);
		assertEquals("This is not a wug.", getText(wugFileName));
	}

	/** Tests that reset checks out all the files of given commit. */
	@Test
	public void testReset() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String oneFileName = "one.txt";
		String wugText = "This is a wug.";
		String oneText = "Content of one.";
		createFile(wugFileName, wugText);
		createFile(oneFileName, oneText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		String oneNewText = "Contents of one changed.";
		writeFile(oneFileName, oneNewText);
		gitlet("add", wugFileName);
		gitlet("add", oneFileName);
		gitlet("commit", "wug and one");
		gitlet("reset", "1");
		assertEquals(wugText, getText(wugFileName));
		assertEquals(oneNewText, getText(oneFileName));
		File one = new File(".gitlet/1/one.txt");
		assertTrue(!one.exists());
	}

	/** Tests reset to an ID in a different branch and back, removing a file from being tracked.
	  * Log of two different branches.
	  * Find of two different IDS from different branches.
	  * Checkout of inherited files for branch checkout.
	  */
	@Test
	public void testRest() {
		String wugFileName = "wug.txt";
		String oneFileName = "one.txt";
		String twoFileName = "two.txt";
		String wugText = "This is a wug.";
		String oneText = "Contents of one.";
		String twoText = "Contents of two.";
		createFile(wugFileName, wugText);
		createFile(oneFileName, oneText);
		createFile(twoFileName, twoText);

		String branchOne = "branch";

		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("add", twoFileName);
		gitlet("commit", "added wug"); // Commit 1 has wug.txt, two.txt.
		gitlet("branch", branchOne);
		gitlet("add", oneFileName);
		gitlet("rm", twoFileName);
		gitlet("commit", "added one"); // Commit 2 has one.txt, inherits wug.txt but not two.txt. Part of master branch.

		String masterContent = gitlet("log");
		assertArrayEquals(new String[] { "added one", "added wug", "initial commit" },
				extractCommitMessages(masterContent));

		gitlet("checkout", branchOne); // Pointer is Commit 1.
		writeFile(wugFileName, "This is not a wug.");
		writeFile(twoFileName, "Contents of two changed.");
		writeFile(oneFileName, "Contents of one changed."); // Not added. Only changed in current directory.
		gitlet("add", wugFileName);
		gitlet("add", twoFileName);
		gitlet("commit", "added wug"); // Commit 3 has wug.txt, two.txt, does not inherit wug.txt from Commit 1. Part of branch.

		String branchContent = gitlet("log");
		assertArrayEquals(new String[] { "added wug", "added wug", "initial commit" },
				extractCommitMessages(branchContent));

		String findWug = gitlet("find", "added wug");
		// assertEquals("1\n3", findWug);

		gitlet("reset", "2"); // Branch pointer to Commit 2.
		assertEquals(wugText, getText(wugFileName));
		assertEquals(oneText, getText(oneFileName));
		assertEquals("Contents of two changed.", getText(twoFileName));

		gitlet("reset", "3"); // Branch pointer to Commit 3.
		assertEquals("This is not a wug.", getText(wugFileName));
		assertEquals(oneText, getText(oneFileName));
	}

	/**
	 * Tests that log after one commit conforms to the format in the spec.
	 * Involves init, add, commit, and log.
	 */
	@Test
	public void testBasicLogs() {
		gitlet("init");
		String commitMessage1 = "initial commit";

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);

		String logContent = gitlet("log");
		String gLogContent = gitlet("global-log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
		assertTrue(2 == extractCommitMessages(gLogContent).length);
	}

	/** Tests that find prints out IDs based off of commit messages. */
	@Test
	public void testFind() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		String commitMessage2 = "wugs";

		String wugOneFile = TESTING_DIR + "wug.txt";
		String wugTwoFile = "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugOneFile, wugText);
		createFile(wugTwoFile, wugText);
		gitlet("add", wugOneFile);
		gitlet("commit", commitMessage2);
		gitlet("add", wugTwoFile);
		gitlet("commit", commitMessage2);

		String findMsg1 = gitlet("find", commitMessage1);
		String findMsg2 = gitlet("find", commitMessage2);
		assertEquals("0", findMsg1);
		//assertEquals("1\n2", findMsg2);
	}

	/** Tests merge. */
	@Test
	public void testMerge() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		String wugOneFile = "wug1.txt";
		String oneText = "wugger";
		String wugTwoFile = "wug2.txt";
		String twoText = "wugga";
		createFile(wugFileName, wugText);
		createFile(wugOneFile, oneText);
		createFile(wugTwoFile, twoText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("add", wugOneFile);
		gitlet("commit", "added wugFileName"); // master branch.

		String wugBranch = "wug";
		gitlet("branch", wugBranch); // wug = commit 1.

		writeFile(wugOneFile, "change wug1");
		gitlet("add", wugOneFile);
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "added wugOneFile"); // master branch.

		
		gitlet("add", wugTwoFile);
		gitlet("commit", "added wugTwoFile"); // master branch.

		gitlet("checkout", wugBranch);
		writeFile(wugOneFile, "change wug1 again");
		writeFile(wugTwoFile, "wugga changed");
		gitlet("add", wugOneFile);
		gitlet("commit", "wugBranch1 commit"); // wug branch.

		gitlet("merge", "master");
		assertEquals("This is not a wug.", getText(wugFileName));
		assertEquals("change wug1", getText("wug1.txt.conflicted"));
	}

	/** Test rebase. */
	@Test
	public void testRebase() {
		String wugFileName = "wug.txt";
		String wugText = "This is a wug.";
		String wugOneFile = "wug1.txt";
		String oneText = "This is wug one.";
		String wugTwoFile = "wug2.txt";
		String twoText = "This is wug two.";
		createFile(wugFileName, wugText);
		createFile(wugOneFile, oneText);
		createFile(wugTwoFile, twoText);
		gitlet("init");

		gitlet("add", wugFileName);
		gitlet("commit", "added wug"); // Commit 1.

		gitlet("branch", "branchOne");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("add", wugOneFile);
		gitlet("commit", "added wug1"); // Commit 2.

		gitlet("checkout", "branchOne");
		gitlet("add", wugTwoFile);
		gitlet("commit", "added wug2"); // Commit 3.

		gitlet("rebase", "master"); // Commit 4.
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

	public static void main(String[] args) {
		jh61b.junit.textui.runClasses(GitletTest.class);
	}
}