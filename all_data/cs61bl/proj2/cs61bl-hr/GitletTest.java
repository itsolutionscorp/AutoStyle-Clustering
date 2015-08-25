import static org.junit.Assert.*;

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
	 * Tests that init creates an initial commit.
	 */
	@Test
	public void testInitialCommit() {
		gitlet("init");
		String logContent = gitlet("log");
		File commit0 = new File(GITLET_DIR + "/commits/commit0");
		assertTrue(commit0.exists());
		String[] initCommitMessage = { "initial commit" };
		assertArrayEquals(initCommitMessage, extractCommitMessages(logContent));
	}

	/**
	 * Tests that add puts something in the staging area.
	 */
	@Test
	public void testAdd() {
		String FileName = TESTING_DIR + "Hello.txt";
		String Text = "Hello World.";
		createFile(FileName, Text);
		gitlet("init");
		gitlet("add", FileName);
		File stagedFile = new File(GITLET_DIR + "/stagingarea/");
		assertTrue(stagedFile.exists());
		gitlet("commit", "Hello World");
		File commit1 = new File(GITLET_DIR + "/commits/commit1");
		assertTrue(commit1.exists());
		String[] commitMessages = { "Hello World", "initial commit" };
		String logContent = gitlet("log");
		assertArrayEquals(commitMessages, extractCommitMessages(logContent));
	}

	/**
	 * Tests if commit commits a file in the staging area.
	 */
	@Test
	public void testCommit() {
		String FileName = TESTING_DIR + "Hi.txt";
		String Text = "Hi World.";
		createFile(FileName, Text);
		gitlet("init");
		gitlet("add", FileName);
		gitlet("commit", "Hi World");
		File commit1 = new File(GITLET_DIR + "/commits/commit1");
		assertTrue(commit1.exists());
		String[] commitMessages = { "Hi World", "initial commit" };
		String logContent = gitlet("log");
		assertArrayEquals(commitMessages, extractCommitMessages(logContent));
	}

	/**
	 * Tests if rm removes a file from the staging area.
	 */
	@Test
	public void testRm() {
		String FileName = TESTING_DIR + "Hi.txt";
		String Text = "Hi World.";
		createFile(FileName, Text);
		gitlet("init");
		gitlet("add", FileName);
		gitlet("rm", FileName);
		gitlet("commit", "hello");
		String[] commitMessages = { "initial commit" };
		String logContent = gitlet("log");
		assertArrayEquals(commitMessages, extractCommitMessages(logContent));
	}

	/**
	 * Tests if find returns a commit message.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testFind() throws ClassNotFoundException, IOException {
		String FileName = TESTING_DIR + "Hello.txt";
		String Text = "Hello.";
		createFile(FileName, Text);
		gitletFast("init");
		gitletFast("add", FileName);
		gitletFast("commit", "hello");
		String x = gitletFast("find", "hello");
		assertEquals(x, "1");
	}

	/**
	 * Tests that a file that has been committed at some point can be restored
	 * by checking out from the commit it was committed at.
	 */
	@Test
	public void testCommitRestoration() {
		String FileName1 = "Hello.txt";
		String Text1 = "Hello.";
		createFile(FileName1, Text1);
		gitlet("init");
		gitlet("add", FileName1);
		gitlet("commit", "hello");
		String FileName2 = "Hello.txt";
		String Text2 = "Herro.";
		createFile(FileName2, Text2);
		gitlet("add", FileName2);
		gitlet("commit", "herro");
		writeFile(FileName1, "Goodbye.");
		gitlet("checkout", "1", "Hello.txt");
		assertEquals(Text1, getText(FileName1));
	}

	/**
	 * Test that a file that has been committed at some point can be restored by
	 * checking it out from a commit that tracks that version of the file, even
	 * if the file wasn't staged prior to that commit.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testCheckoutRestoration() throws ClassNotFoundException, IOException {
		String FileName = "Hello.txt";
		String Text = "Hello.";
		createFile(FileName, Text);
		gitletFast("init");
		gitletFast("add", FileName);
		gitletFast("commit", "hello");
		String FileName1 = "Hi.txt";
		String Text1 = "Hi.";
		createFile(FileName1, Text1);
		gitletFast("add", FileName1);
		gitletFast("commit", "hello2");
		writeFile(FileName, "whatever.");
		gitletFast("checkout", "Hello.txt");
		assertEquals(Text, getText(FileName)); // should be Hello.
	}

	/**
	 * Test that you can checkout [id] [file] from an arbitrary commit in the
	 * graph (even one in another branch).
	 */
	@Test
	public void testCheckoutRestorationAtID() {
		String FileName = "Hello.txt";
		String Text = "Hello.";
		createFile(FileName, Text);
		gitlet("init");
		gitlet("add", FileName);
		gitlet("commit", "hello1"); // commit 1
		assertEquals(Text, getText(FileName)); // should be Hello.
		gitlet("branch", "newbranch"); // new branch creation
		gitlet("checkout", "newbranch"); // change branches to "newbranch"
		String FileName1 = "newText.txt";
		String Text1 = "New File";
		createFile(FileName1, Text1);
		gitlet("add", FileName1);
		gitlet("commit", "hello2"); // commit 2 on "newbranch"
		gitlet("checkout", "master"); // change branches to "master"
		String FileName2 = "newText2.txt";
		String Text2 = "Newer File";
		createFile(FileName2, Text2);
		gitlet("add", FileName2);
		gitlet("commit", "hello3"); // commit 3 on "master"
		gitlet("checkout", "newbranch"); // change back to "newbranch"
		writeFile(FileName, "oh my gosh");
		gitlet("checkout", "1", FileName);
		assertEquals(Text, getText(FileName)); // back to original text "Hello."

	}

	/**
	 * Tests that resetting backward appropriately changes the output of log.
	 */
	@Test
	public void testReset() {
		String FileName1 = "Hello.txt";
		String Text1 = "Hello.";
		createFile(FileName1, Text1);
		gitlet("init");
		gitlet("add", FileName1);
		gitlet("commit", "commit1");
		String FileName2 = "Hi.txt";
		String Text2 = "Hi.";
		createFile(FileName2, Text2);
		gitlet("add", FileName2);
		gitlet("commit", "commit2");
		String FileName3 = "Hey.txt";
		String Text3 = "Hey.";
		createFile(FileName3, Text3);
		gitlet("add", FileName3);
		gitlet("commit", "commit3");
		gitlet("reset", "1");
		String[] resetMessages = { "commit1", "initial commit" };
		String logContent = gitlet("log");
		assertArrayEquals(resetMessages, extractCommitMessages(logContent));
	}

	/**
	 * Tests that log adjusts appropriately when switching from one branch to
	 * another.
	 */
	@Test
	public void testBranchLogs() {
		String FileName = "Hello.txt";
		String Text = "Hello.";
		createFile(FileName, Text);
		gitlet("init");
		gitlet("add", FileName);
		gitlet("commit", "hello1"); // commit 1
		assertEquals(Text, getText(FileName)); // should be Hello.
		gitlet("branch", "newbranch"); // new branch creation
		gitlet("checkout", "newbranch"); // change branches to "newbranch"
		String FileName1 = "newText.txt";
		String Text1 = "New File";
		createFile(FileName1, Text1);
		gitlet("add", FileName1);
		gitlet("commit", "hello2"); // commit 2 on "newbranch"
		gitlet("checkout", "master"); // change branches to "master"
		String FileName2 = "newText2.txt";
		String Text2 = "Newer File";
		createFile(FileName2, Text2);
		gitlet("add", FileName2);
		gitlet("commit", "hello3"); // commit 3 on "master"
		gitlet("checkout", "newbranch"); // change back to "newbranch"
		String[] branchMessages = { "hello2", "hello1", "initial commit" };
		String logContent = gitlet("log");
		assertArrayEquals(branchMessages, extractCommitMessages(logContent)); // should
		// not
		// include
		// hello3

	}

	/**
	 * Test that merge will commit with files from the other branch if those
	 * files had been modified in the other branch but not in the current branch
	 * since the split point.
	 */
	@Test
	public void testMergeCommits() {
		String FileName = "Hello.txt";
		String Text = "Hello.";
		createFile(FileName, Text);
		gitlet("init");
		gitlet("add", FileName);
		gitlet("commit", "hello1"); // commit 1
		assertEquals(Text, getText(FileName)); // should be Hello.
		gitlet("branch", "newbranch"); // new branch creation
		gitlet("checkout", "newbranch"); // change branches to "newbranch"
		String FileName1 = "newText.txt";
		String Text1 = "New File";
		createFile(FileName1, Text1);
		gitlet("add", FileName1);
		gitlet("commit", "hello2"); // commit 2 on "newbranch"
		gitlet("checkout", "master"); // change branches to "master"
	}

	/**
	 * Test that merge will generate a .conflicted file if a file has been
	 * modified in both branches since the split point.
	 */
	@Test
	public void testMergeConflicts() {
		gitlet("init");
		String fileName1 = "wug1.txt";
		String text1 = "This is a wug1";
		createFile(fileName1, text1);
		gitlet("add", fileName1);
		gitlet("commit", "commit 1 message");
		String fileName2 = "wug2.txt";
		String text2 = "This is a wug2";
		createFile(fileName2, text2);
		gitlet("add", fileName2);
		gitlet("commit", "commit 2 message");
		gitlet("branch", "branch1");
		String fileName3 = "wug3.txt";
		String text3 = "This is a wug3";
		createFile(fileName3, text3);
		gitlet("add", fileName3);
		gitlet("commit", "commit 3 message");
		gitlet("checkout", "branch1");
		String fileName4 = "wug4.txt";
		String text4 = "This is a wug4";
		createFile(fileName4, text4);
		gitlet("add", fileName4);
		gitlet("commit", "commit 4 message");
		writeFile(".gitlet/commits/commit3/wug1.txt", "This is a wug1 in commit 3");
		writeFile(".gitlet/commits/commit4/wug1.txt", "This is a wug1 in commit 4");
		gitlet("checkout", "master");
		gitlet("merge", "branch1");
		String fileName = "wug1.txt.conflicted";
		File f = new File(fileName);
		assertTrue(Files.exists(f.toPath())); 
	}

	/**
	 * Test that changes in the base branch propagate through the replayed
	 * branch during a rebase.
	 */
	@Test
	public void testRebasePropogation() {
		gitlet("init");
		String fileName1 = "wug1.txt";
		String text1 = "This is a wug";
		createFile(fileName1, text1);
		gitlet("add", fileName1);
		gitlet("commit", "commit 1 message");
		String fileName2 = "wug2.txt";
		String text2 = "This is a wug?!?";
		createFile(fileName2, text2);
		gitlet("add", fileName2);
		gitlet("commit", "commit 2 message");
		gitlet("branch", "new");
		gitlet("checkout", "new");
		String fileName3 = "wug3.txt";
		String text3 = "This is a wug....?!?";
		createFile(fileName3, text3);
		gitlet("add", fileName3);
		gitlet("commit", "commit 3 message");
		gitlet("checkout", "master");
		String fileName4 = "wug4.txt";
		String text4 = "This is a wug....?!?";
		createFile(fileName4, text4);
		gitlet("add", fileName4);
		gitlet("commit", "commit 4 message");
		gitlet("checkout", "new");
		gitlet("rebase", "master");
		assertTrue(Files.exists(Paths.get(fileName4)));
	}

	/**
	 * Test that the output of log after a rebase includes the commit messages
	 * from both branches involved in the rebase.
	 */
	@Test
	public void testRebaseLog() {
		gitlet("init");
		String fileName1 = "wug1.txt";
		String text1 = "This is a wug";
		createFile(fileName1, text1);
		gitlet("add", fileName1);
		gitlet("commit", "commit 1 message");
		String fileName2 = "wug2.txt";
		String text2 = "This is a wug?!?";
		createFile(fileName2, text2);
		gitlet("add", fileName2);
		gitlet("commit", "commit 2 message");
		gitlet("branch", "new");
		gitlet("checkout", "new");
		String fileName3 = "wug3.txt";
		String text3 = "This is a wug....?!?";
		createFile(fileName3, text3);
		gitlet("add", fileName3);
		gitlet("commit", "commit 3 message");
		gitlet("checkout", "master");
		String fileName4 = "wug4.txt";
		String text4 = "This is a wug....?!?";
		createFile(fileName4, text4);
		gitlet("add", fileName4);
		gitlet("commit", "commit 4 message");
		gitlet("checkout", "new");
		gitlet("rebase", "master");
		String[] contents = {"commit 4 message", "commt 3 message", "commit 2 message", "commit 1 message", "initial commit"};
		String logContent = gitlet("log");
		assertArrayEquals(contents, extractCommitMessages(logContent));
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
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private static String gitletFast(String... args)
			throws ClassNotFoundException, IOException {
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