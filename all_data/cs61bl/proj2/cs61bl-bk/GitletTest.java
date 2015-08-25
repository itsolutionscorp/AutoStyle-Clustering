import static org.junit.Assert.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
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
 *         Modified in order to test Gitlet implementation by adding more
 *         comprehensive tests.
 * @author Albert Pham cs61bl-bp
 * @author Henry Gong cs61bl-bk
 * @author Patrick Zhang cs61bl-bo
 * 
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
	 * Test that branch names are properly created and current branch changes.
	 */
	@Test
	public void testBranch() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wg.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName); // add file in master
		gitlet("commit", "wg");
		gitlet("branch", "branch1");
		gitlet("branch", "branch2");
		gitlet("checkout", "branch2");
		HashSet<String> branches = new HashSet<String>();
		branches.add("master");
		branches.add("branch1");
		branches.add("*branch2");
		assertEquals(branches, extractBranchNames(gitlet("status")));

		createFile(wugFileName, "This is a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "wug");
		gitlet("checkout", "master");
		assertEquals(wugText, getText(wugFileName)); // check master's version
														// in working directory
		// check current branch changes
		branches.remove("master");
		branches.remove("*branch2");
		branches.add("*master");
		branches.add("branch2");
		assertEquals(branches, extractBranchNames(gitlet("status")));
	}

	/**
	 * Test that finding messages, even from nonconsecutive commits, works.
	 */
	@Test
	public void testFind() {
		String findResult = "1\n2";
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName); // add file in master
		gitlet("commit", "wg");
		createFile(TESTING_DIR + "wg.txt", "This is a wg.");
		gitlet("add", TESTING_DIR + "wg.txt");
		gitlet("commit", "wg");
		assertEquals(findResult, gitlet("find", "wg"));

		createFile("a", "a");
		gitlet("add", "a");
		gitlet("commit", "a commit");
		createFile("b", "b");
		gitlet("add", "b");
		gitlet("commit", "wg");
		findResult += "\n4";
		assertEquals(findResult, gitlet("find", "wg")); // nonconsecutive
														// commits
	}

	@Test
	public void testReset() {
		gitlet("init");

		// master - commit 1: add file "this is a wug"
		// tracking files: wug.txt - "This is a wug"
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "wug");

		// master - commit 2: add file "this is a cat"
		// tracking files: wug.txt - "This is a wug"
		// cat.txt - "This is a cat"(newly add)
		String catFileName = TESTING_DIR + "cat.txt";
		createFile(catFileName, "This is a cat");
		gitlet("add", catFileName);
		gitlet("commit", "add cat.txt");

		// master - commit 3: modify file wug.txt to "this is a WUG"
		// tracking files: wug.txt - "This is a WUG"
		// cat.txt - "This is a cat"(not changed)
		createFile(wugFileName, "This is a WUG");
		gitlet("add", wugFileName);
		gitlet("commit", "WUG");

		// test commit log before reset
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { "WUG", "add cat.txt", "wug",
				"initial commit" }, extractCommitMessages(logContent));

		// test commit log after reset and test the file should be changed to
		// reseted commit's version
		gitlet("reset", "1");
		assertEquals("This is a wug", getText(wugFileName));
		logContent = gitlet("log");
		assertArrayEquals(new String[] { "wug", "initial commit" },
				extractCommitMessages(logContent));

		// make new commit after reset, then test log
		createFile(catFileName, "CAT");
		gitlet("add", catFileName);
		gitlet("commit", "CAT");
		logContent = gitlet("log");
		assertArrayEquals(new String[] { "CAT", "wug", "initial commit" },
				extractCommitMessages(logContent));
	}

	@Test
	public void testCommit() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName); // add file in master
		String hello = gitlet("commit", "");
		System.out.println(hello == "commit");
		assertEquals("Please enter a commit message.", gitlet("commit", ""));
		gitlet("commit", "wug");
		assertEquals("No changes added to the commit.", gitlet("commit", "wug"));
		createFile(wugFileName, wugText + "!");
		gitlet("add", wugFileName);
		gitlet("commit", "wug2");
		assertEquals(wugText + "!", getText(GITLET_DIR + "2/" + TESTING_DIR
				+ "wug.txt"));
	}

	@Test
	public void testRebase() {
		gitlet("init");

		// master - commit 1: add file "this is a wug"
		// tracking files: wug.txt - "This is a wug"
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "wug");
		gitlet("branch", "branch1");

		// master - commit 2: add file "this is a cat"
		// tracking files: wug.txt - "This is a wug"
		// cat.txt - "This is a cat"(newly add)
		String catFileName = TESTING_DIR + "cat.txt";
		createFile(catFileName, "This is a cat");
		gitlet("add", catFileName);
		gitlet("commit", "add cat.txt");

		// master - commit 3: modify file wug.txt to "this is a WUG"
		// tracking files: wug.txt - "This is a WUG"
		// cat.txt - "This is a cat"(not changed)
		createFile(wugFileName, "This is a WUG");
		gitlet("add", wugFileName);
		gitlet("commit", "modify wug to WUG");

		// checkout - branch1
		gitlet("checkout", "branch1");
		// branch1 - commit 4: modify file wugtxt to "WUG!"
		// tracking files: wug.txt - "WUG!"
		createFile(wugFileName, "WUG!");
		gitlet("add", wugFileName);
		gitlet("commit", "WUG!");

		// branch1 - commit 5: add file cat.txt - "CAT!"
		// tracking files: cat.txt - "CAT!"
		// wug.txt - "WUG!"
		createFile(catFileName, "CAT!");
		gitlet("add", catFileName);
		gitlet("commit", "CAT!");

		gitlet("checkout", "master");
		gitlet("rebase", "branch1");

		// check copied node -- commit 6
		gitlet("checkout", "6", wugFileName);
		gitlet("checkout", "6", catFileName);
		// should be the same as commit 4(propagation rule)
		assertEquals("WUG!", getText(wugFileName));
		// shouldn't change
		assertEquals("This is a cat", getText(catFileName));

		// check copied node -- commit 7
		gitlet("checkout", "7", wugFileName);
		gitlet("checkout", "7", catFileName);
		assertEquals("This is a WUG", getText(wugFileName));
		assertEquals("This is a cat", getText(catFileName));
		// check that the messages have been copied.
		assertArrayEquals(new String[] { "modify wug to WUG", "add cat.txt",
				"CAT!", "WUG!", "wug", "initial commit" },
				extractCommitMessages(gitlet("log")));
	}

	/**
	 * Test if a file can be restored by checking it out from the commit it was
	 * first added to.
	 * 
	 */
	@Test
	public void testRestore() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		createFile(wugFileName, "This is a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "wug1");
		assertEquals("This is a wug.", getText(wugFileName));
		assertEquals("This is a wug.", getText(GITLET_DIR + "1/" + wugFileName));
		writeFile(wugFileName, "blablabla");
		gitlet("add", wugFileName);
		gitlet("commit", "wug2");
		assertEquals("This is a wug.", getText(GITLET_DIR + "1/" + wugFileName));
		assertEquals("blablabla", getText(wugFileName));
		assertEquals("blablabla", getText(GITLET_DIR + "2/" + wugFileName));
		gitlet("checkout", "1", wugFileName);
		assertEquals("blablabla", getText(GITLET_DIR + "2/" + wugFileName));
		assertEquals("This is a wug.", getText(wugFileName));
		gitlet("add", wugFileName);
		gitlet("commit", "wug3");
		assertEquals("This is a wug.", getText(GITLET_DIR + "3/" + wugFileName));
		writeFile(wugFileName, "blablablabla");
		assertEquals("blablablabla", getText(wugFileName));
		gitlet("checkout", wugFileName);
		assertEquals("This is a wug.", getText(wugFileName));
	}

	/**
	 * Tests if a file can be checkout from a different branch. Also tests that
	 * a file can be restored by checking it out from a different commit.
	 */
	@Test
	public void testBranchCheckout() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String catFileName = TESTING_DIR + "cat.txt";
		String dogFileName = TESTING_DIR + "dog.txt";
		createFile(wugFileName, "This is a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "wug1");
		writeFile(wugFileName, "blablabla");
		gitlet("add", wugFileName);
		gitlet("commit", "wug2");
		gitlet("checkout", "1", wugFileName);
		gitlet("add", wugFileName);
		gitlet("commit", "wug3");
		writeFile(wugFileName, "blablablabla");
		gitlet("checkout", wugFileName);
		gitlet("branch", "newBranch");
		gitlet("checkout", "newBranch");
		gitlet("status");
		createFile(catFileName, "This is a cat.");
		gitlet("add", catFileName);
		gitlet("commit", "cat1");
		createFile(dogFileName, "This is dog.");
		gitlet("add", dogFileName);
		gitlet("commit", "dog1");
		gitlet("checkout", "master");
		createFile(dogFileName, "This is dog2.");
		gitlet("add", dogFileName);
		gitlet("commit", "otherdog");
		gitlet("checkout", "5", dogFileName);
		assertEquals("This is dog.", getText(dogFileName));
		gitlet("checkout", "5", catFileName);
		assertEquals("This is a cat.", getText(catFileName));
		gitlet("global-log");
	}

	/**
	 * Test that changing branches will change the output of log.
	 */
	@Test
	public void testChangeBranch() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String catFileName = TESTING_DIR + "cat.txt";
		String dogFileName = TESTING_DIR + "dog.txt";
		createFile(wugFileName, "This is a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "wug1");
		writeFile(wugFileName, "blablabla");
		gitlet("add", wugFileName);
		gitlet("commit", "wug2");
		gitlet("checkout", "1", wugFileName);
		gitlet("add", wugFileName);
		gitlet("commit", "wug3");
		writeFile(wugFileName, "blablablabla");
		gitlet("checkout", wugFileName);
		gitlet("branch", "newBranch");
		gitlet("checkout", "newBranch");
		gitlet("status");
		createFile(catFileName, "This is a cat.");
		gitlet("add", catFileName);
		gitlet("commit", "cat1");
		createFile(dogFileName, "This is dog.");
		gitlet("add", dogFileName);
		gitlet("commit", "dog1");
		assertArrayEquals(new String[] { "dog1", "cat1", "wug3", "wug2",
				"wug1", "initial commit" },
				extractCommitMessages(gitlet("log")));
		gitlet("checkout", "master");
		createFile(dogFileName, "This is dog2.");
		gitlet("add", dogFileName);
		gitlet("commit", "otherdog");
		assertArrayEquals(new String[] { "otherdog", "wug3", "wug2", "wug1",
				"initial commit" }, extractCommitMessages(gitlet("log")));
	}

	@Test
	public void testMerge() {
		gitlet("init");

		// master - commit 1: add file "this is a wug"
		// tracking files: wug.txt - "This is a wug"
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug";
		createFile(wugFileName, wugText);
		createFile(wugFileName + "1", wugText + " 1");
		createFile(wugFileName + "2", wugText + " 2");
		gitlet("add", wugFileName);
		gitlet("add", wugFileName + "1");
		gitlet("add", wugFileName + "2");
		gitlet("commit", "wug");

		createFile(wugFileName + "2", wugText + " 2");
		gitlet("add", wugFileName + "2");
		gitlet("commit", "wug2");

		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		createFile(wugFileName + "3", wugText + " 3");
		gitlet("add", wugFileName + "3");
		writeFile(wugFileName, "wugText modified");
		gitlet("add", wugFileName);
		createFile(wugFileName + "2", "this is conflicted");
		gitlet("add", wugFileName + "2");
		gitlet("commit", "branch1 commit");

		// merge branch1 into master
		gitlet("checkout", "master");

		createFile(wugFileName + "2", "this is conflicted blablabla");
		gitlet("add", wugFileName + "2");
		gitlet("commit", "conflicted merge message");
		gitlet("merge", "branch1");

		// check new file exists, modified file over written, and unmodified
		// file untouched
		assertEquals("wugText modified", getText(wugFileName)); // modification
																// reflected
		assertEquals(wugText + " 3", getText(wugFileName + "3")); // new file
																	// reflected
		assertEquals(wugText + " 1", getText(wugFileName + "1"));// unmodified
																	// reflected
		assertEquals("this is conflicted",
				getText(wugFileName + "2.conflicted"));
		assertEquals("this is conflicted blablabla", getText(wugFileName + "2"));
		assertEquals(
				"Cannot do this command until the merge conflict has been resolved.",
				gitlet("branch", "shouldfail"));
		assertEquals(
				"Cannot do this command until the merge conflict has been resolved.",
				gitlet("merge", "branch1"));
		assertEquals(
				"Cannot do this command until the merge conflict has been resolved.",
				gitlet("rm-branch", "branch1"));
		// make sure there is no commit 5
		gitlet("log");
	}

	@Test
	public void testSuccessfulMerge() {
		gitlet("init");

		// master - commit 1: add file "this is a wug"
		// tracking files: wug.txt - "This is a wug"
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug";
		createFile(wugFileName, wugText);
		createFile(wugFileName + "1", wugText + " 1");
		createFile(wugFileName + "2", wugText + " 2");
		gitlet("add", wugFileName);
		gitlet("add", wugFileName + "1");
		gitlet("add", wugFileName + "2");
		gitlet("commit", "wug");

		createFile(wugFileName + "2", wugText + " 2");
		gitlet("add", wugFileName + "2");
		gitlet("commit", "wug2");

		gitlet("branch", "branch1");
		gitlet("checkout", "branch1");
		createFile(wugFileName + "3", wugText + " 3");
		gitlet("add", wugFileName + "3");
		writeFile(wugFileName, "wugText modified");
		gitlet("add", wugFileName);
		createFile(wugFileName + "2", "this is conflicted");
		gitlet("add", wugFileName + "2");
		gitlet("commit", "branch1 commit");

		// merge branch1 into master
		gitlet("checkout", "master");

		createFile(wugFileName + "6", "this is conflicted blablabla");
		gitlet("add", wugFileName + "6");
		gitlet("commit", "conflicted merge message");
		gitlet("merge", "branch1");

		// check new file exists, modified file over written, and unmodified
		// file untouched
		assertEquals("wugText modified", getText(wugFileName)); // modification
																// reflected
		assertEquals(wugText + " 3", getText(wugFileName + "3")); // new file
																	// reflected
		assertEquals(wugText + " 1", getText(wugFileName + "1"));// unmodified
																	// reflected
		assertEquals("this is conflicted", getText(wugFileName + "2")); // overwrite
		assertEquals("this is conflicted blablabla", getText(wugFileName + "6"));
		// make sure there is commit 5
		gitlet("log");
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

	private static Set<String> extractBranchNames(String statusOutput) {
		String[] statusChunks = statusOutput.split("===");
		String[] branches = statusChunks[2].split(LINE_SEPARATOR);
		HashSet<String> branchSet = new HashSet<String>();
		for (String branch : Arrays.copyOfRange(branches, 1, branches.length)) {
			branchSet.add(branch);
		}
		return branchSet;
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