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
 * Class that provides JUn
 * it tests for Gitlet, as well as a couple of utility
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

	@Test
	public void testInitIfExisted() {
		gitlet("init");
		String stdout = gitlet("init");
		String errorMsg = "A gitlet version control system already exists " + "in the current directory.";
		assertEquals(errorMsg, stdout);
	}

	/**
	 * Test if "add" will add file does not exist.
	 */
	@Test
	public void testBasicAdd() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		String stdout = gitlet("add", "wug2.txt");
		assertTrue(stdout.contains("File does not exist."));

		gitlet("add", wugFileName);
		stdout = gitlet("status");
		assertTrue(stdout.contains(wugFileName));
	}

	/**
	 * If the file had been removed, when we add it again, it shouldnot show up.
	 * in Staged area.
	 */
	@Test
	public void testAddAfterRemoved() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("rm", wugFileName);
		String stdout = gitlet("status");
		gitlet("add", wugFileName);
		assertTrue(!stdout.contains(wugFileName));
	}

	/**
	 * Test initial Commit content is "initial commit"
	 * 
	 */
	@Test
	public void testInititCommit() {
		gitlet("init");
		String commitMessage = "initial commit";

		String log = gitlet("log");
		assertArrayEquals(new String[] { commitMessage }, extractCommitMessages(log));
	}

	/**
	 * 1. Test if can commit without a message 2. Test if can commit without add
	 * 3. Test if log contains the committed message
	 */
	@Test
	public void testBasicCommit() {
		gitlet("init");

		String stdout = gitlet("commit");
		assertTrue(stdout.contains("Please enter a commit message."));
		stdout = gitlet("commit", "second commit");
		assertTrue(stdout.contains("No changes added to the commit."));
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "second commit.");
		stdout = gitlet("log");
		assertTrue(stdout.contains("second commit."));
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
	 * 1. Test that can checkout [id] [file] from an arbitrary commit in the
	 * graph (even one in another branch). 2. Test that a file that has been
	 * committed at some point can be restored by checking it out from the
	 * commit it was committed at.
	 */
	@Test
	public void testCheckoutFunctionality() {
		gitlet("init");
		String stdout;

		// Commit A
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");

		// Commit B
		String AnotherFile = TESTING_DIR + "another.txt";
		String AnotherText = "This is another file";
		createFile(AnotherFile, AnotherText);
		gitlet("add", AnotherFile);
		gitlet("commit", "added another.txt");

		// Commit C (Split point)
		String wugTextAtC = "I am at commit C (Split point)";
		writeFile(wugFileName, wugTextAtC);
		gitlet("add", wugFileName);
		gitlet("commit", "modified wug.txt at commit C (Split)");

		// Create and switch to the branch "dev"
		gitlet("branch", "dev");
		stdout = gitlet("checkout", "dev");

		// Commit D (in dev branch)
		String wugTextAtD = "I am at commit D (dev branch)";
		writeFile(wugFileName, wugTextAtD);
		gitlet("add", wugFileName);
		gitlet("commit", "modified wug.txt in dev branch (D)");

		// Checkout master branch and make commit E
		stdout = gitlet("checkout", "master");
		String wugTextAtE = "I am at commit E (master branch)";
		writeFile(wugFileName, wugTextAtE);
		gitlet("add", wugFileName);
		gitlet("commit", "modified wug.txt in master branch (E)");
		assertEquals(wugTextAtE, getText(wugFileName));

		// Checkout dev branch should change wug.txt content

		stdout = gitlet("global-log");
		System.out.println(stdout);
		gitlet("checkout", "dev");
		assertEquals(wugTextAtD, getText(wugFileName));
		gitlet("checkout", "master");

		// Test that a file that has been committed at some point can be
		// restored by
		// checking it out from the commit it was committed at.
		gitlet("checkout", "1", wugFileName);
		assertEquals(wugText, getText(wugFileName));

		// Test that can checkout [id] [file] from an arbitrary
		// commit in the graph (even one in another branch).
		gitlet("checkout", "4", wugFileName);
		assertEquals(wugTextAtD, getText(wugFileName));

	}

	@Test
	/**
	 * Test status can show up current branch with asterick in front of it
	 */
	public void testStatusBasic() {
		gitlet("init");
		gitlet("branch", "dev");
		String stdout = gitlet("status");
		assertTrue(stdout.contains("*master"));
		assertTrue(!stdout.contains("*dev"));

		gitlet("checkout", "dev");
		stdout = gitlet("status");
		assertTrue(!stdout.contains("*master"));
		assertTrue(stdout.contains("*dev"));
	}

	/**
	 * 1. Tests that log after one commit conforms to the format in the spec.
	 * Involves init, add, commit, and log. 2. Also test log is branch-oriented
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

		gitlet("branch", "mysecondBranch");
		gitlet("checkout", "mysecondBranch");

		gitlet("add", wugFileName);
		String commitMessage3 = "added wug again";
		gitlet("commit", commitMessage3);
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));

		gitlet("checkout", "master");
		logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 }, extractCommitMessages(logContent));

	}

	/**
	 * 1. Test if removing added but without committed file does not show up in
	 * status 2. Test if removing added but with committed file does show up in
	 * status
	 */
	@Test
	public void testBasicRemove() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String stdout = gitlet("status");
		gitlet("rm", wugFileName);
		stdout = gitlet("status");
		assertTrue(!stdout.contains(wugFileName));

		gitlet("add", wugFileName);
		gitlet("commit", "add wug again");
		stdout = gitlet("log");
		stdout = gitlet("rm", wugFileName);
		stdout = gitlet("status");
		assertTrue(stdout.contains(wugFileName));
	}

	/**
	 * Test that if it is invalid to have same branch names. Also test if status
	 * have new branch name.
	 */
	@Test
	public void testBasicBranch() {
		gitlet("init");
		String stdout = gitlet("branch", "master");
		assertTrue(stdout.contains("A branch with that name already exists."));
		gitlet("branch", "dev");
		stdout = gitlet("status");
		assertTrue(stdout.contains("dev"));
	}

	@Test
	/**
	 * Test global log can include log from other branches
	 */
	public void testBasicGlobalLog() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage2 = "added wug.txt";
		gitlet("commit", commitMessage2);

		gitlet("branch", "dev");
		gitlet("checkout", "dev");
		writeFile(wugFileName, "modified in the other branch");
		gitlet("add", wugFileName);
		String commitMessage3 = "modified wug.txt";
		gitlet("commit", commitMessage3);

		gitlet("checkout", "master");
		writeFile(wugFileName, "modified in master branch");
		gitlet("add", wugFileName);
		String commitMessage4 = "modified wug.txt in master branch";
		gitlet("commit", commitMessage4);

		String stdout;
		stdout = gitlet("global-log");
		assertTrue(stdout.contains(commitMessage3));
	}

	@Test
	/**
	 * Test that merge will generate a .conflicted file if a file has been
	 * modified in both branches since the split point.
	 */
	public void testMergeConflict() {
		gitlet("init");
		String wugFileName = "wug.txt";
		String wugText = "This is the original wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");

		gitlet("branch", "dev");

		String wugTextInMaster = "Version of Master.";
		writeFile(wugFileName, wugTextInMaster);
		gitlet("add", wugFileName);
		gitlet("commit", "modify wug in master!!");

		gitlet("checkout", "dev");
		String wugTextInDev = "Version of Dev.";
		writeFile(wugFileName, wugTextInDev);
		gitlet("add", wugFileName);
		gitlet("commit", "modify wug in dev!!");

		gitlet("merge", "master");
		assertEquals(wugTextInDev, getText(wugFileName));
		assertEquals(wugTextInMaster, getText(wugFileName + ".conflicted"));
	}

	/**
	 * Test that merge will commit with files from the other branch if those
	 * files had been modified in the other branch but not in the current branch
	 * since the split point.
	 */
	public void testMerge() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is the original wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");

		gitlet("branch", "dev");
		gitlet("checkout", "dev");
		String wugTextInDev = "Version of Dev.";
		writeFile(wugFileName, wugTextInDev);
		gitlet("add", wugFileName);
		gitlet("commit", "modify wug in dev!!");
		gitlet("checkout", "master");
		assertEquals(wugTextInDev, getText(wugFileName));

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
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	@Test
	public void resetLog() {
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
		gitlet("reset", "0");
		logContent = gitlet("log");
		assertArrayEquals(new String[] {commitMessage1}, extractCommitMessages(logContent));
	}
	
	@Test
	public void testRebase () {
		gitlet("init");
		createFile("a.txt", "a");
		createFile("b.txt", "b");
		createFile("c.txt", "c");
		gitlet("add", "a.txt");
		gitlet("add", "b.txt");
		gitlet("add", "c.txt");
		gitlet("commit", "splitpoint");
		gitlet("branch", "abranch");
		writeFile("a.txt", "a'");
		gitlet("add", "a.txt");
		gitlet("commit", "master1");
		writeFile("b.txt", "b'");
		gitlet("add", "b.txt");
		gitlet("commit", "master2");
		gitlet("checkout", "abranch");
		writeFile("a.txt", "a1");
		writeFile("c.txt", "c1");
		gitlet("add", "a.txt");
		gitlet("add", "c.txt");
		gitlet("commit", "branch1");
		writeFile("b.txt", "b1");
		writeFile("c.txt", "c2");
		gitlet("add", "b.txt");
		gitlet("add", "c.txt");
		gitlet("commit", "branch2");
		gitlet("rebase", "master");
	}
	
	
}