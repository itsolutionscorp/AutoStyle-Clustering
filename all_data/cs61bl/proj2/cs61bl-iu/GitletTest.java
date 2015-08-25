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
 * @author Joseph Moghadam & Team Gitlit
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
		System.out.println("=== BEGINNING OF TEST BASIC INITIALIZE ===");
		gitlet("init");
		File f = new File(GITLET_DIR);
		assertTrue(f.exists());
		System.out.println("=== END OF TEST ===");
	}

	/**
	 * Test with empty Gitlet.
	 */
	@Test
	public void testEmptyGit() {
		System.out.println("=== BEGINNING OF TEST EMPTY GIT ===");
		gitlet("log");
		System.out.println("initialization commit ^");
		System.out.println("Trying global-log");
		gitlet("global-log");
		System.out.println("NOTHING SHOULD HAVE HAPPENED.");
		System.out.println("trying random command");
		gitlet("destruction");
		System.out.println("^ Error message should be here.");
		System.out.println("=== END OF TEST ===");
	}

	/**
	 * Test remove method.
	 */
	@Test
	public void testRemove() {
		System.out.println("=== BEGINNING OF TEST REMOVE ===");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("rm", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", wugFileName);
		assertEquals("This is not a wug.", getText(wugFileName));
		writeFile(wugFileName, "This is not a wug.");
		gitlet("commit", "added wug1");
		writeFile(wugFileName, "This is a wug.");
		gitlet("add", wugFileName);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug2");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", wugFileName);
		assertEquals("This is a wug.", getText(wugFileName));
		gitlet("status");
		System.out.println("=== END OF TEST ===");

	}

	/**
	 * Tests that status is working correctly.
	 */
	@Test
	public void testStatus() {
		System.out.println("=== BEGINNING OF TEST STATUS ===");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("status");
		System.out.println("=== END OF TEST ===");
	}

	/**
	 * Test global log method.
	 */
	@Test
	public void testGlobalLog() {
		System.out.println("=== BEGINNING OF GLOBAL LOG TEST ===");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		System.out.println("Before the global-log");
		gitlet("global-log");
		System.out.println("Nothing should be here but this line");
		System.out.println("After the global-log");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		System.out.println("Before the global-log");
		gitlet("global-log");
		System.out
				.println("There should be a commit with message 'added wug' above ^^");
		System.out.println("After the global-log");
		System.out.println("=== END OF TEST ===");
	}

	/**
	 * Test find method.
	 */
	@Test
	public void testFind() {
		System.out.println("=== BEGINNING OF FIND TEST ===");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("find", "added wug");
		System.out.println("^ Should say no such commits exist");
		gitlet("commit", "added wug");
		gitlet("find", "added wug");
		System.out.println("^ Should be a commit ID");
		gitlet("find", "addeded wug");
		System.out.println("^ Should say no such commits exist");
		System.out.println("=== END OF TEST ===");
	}

	/**
	 * Test branch method.
	 */
	@Test
	public void testBranch() {
		System.out.println("=== BEGINNING OF BRANCH TEST ===");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "master");
		System.out.println("^ Should say branch exists.");
		gitlet("branch", "fantasticbranch");
		gitlet("status");
		System.out.println("you should see 'fantastic branch' up there.");
		gitlet("rm-branch", "fantastic");
		System.out
				.println("^ should say a branch with that name doesn't exist");
		gitlet("rm-branch", "fantasticbranch");
		gitlet("status");
		System.out.println("you shouldn't see 'fantastic branch' up there.");
		System.out.println("=== END OF TEST ===");
	}

	/**
	 * Tests that checking out a file name will restore the version of the file
	 * from the previous commit. Involves init, add, commit, and checkout.
	 * Includes testing that a committed file can be restored by checking it out
	 * from the same commit and from a previous commit.
	 */
	@Test
	public void testBasicCheckout() {
		System.out.println("=== BEGINNING OF CHECKOUT TEST ===");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", wugFileName);
		assertEquals(wugText, getText(wugFileName));
		System.out.println("=== END OF TEST ===");
		try {
			recursiveDelete(new File(wugFileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gitlet("checkout", wugFileName);
		assertEquals(wugText, getText(wugFileName));
		File wug = new File(wugFileName);
		try {
			recursiveDelete(wug);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(!wug.exists());
		gitlet("rm", wugFileName);
		gitlet("commit", "removed wug");
		gitlet("checkout", "1", wugFileName);
		assertEquals(wugText, getText(wugFileName));

	}

	/**
	 * Test checking out to a branch.
	 */
	@Test
	public void testBranchCheckout() {
		System.out.println("=== BEGINNING OF CHECKOUT BRANCH TEST ===");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "greatbranch");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", "greatbranch");
		assertEquals(wugText, getText(wugFileName));
		System.out.println("=== END OF TEST ===");
	}

	/**
	 * Tests checking out to an ID and file name.
	 */
	@Test
	public void testIDCheckout() {
		System.out.println("=== BEGINNING OF CHECKOUT ID TEST ===");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", "1", wugFileName);
		assertEquals(wugText, getText(wugFileName));
		System.out.println("=== END OF TEST ===");
	}

	/**
	 * Test reset works appropriately.
	 */
	@Test
	public void testReset() {
		System.out.println("=== BEGINNING OF RESET TEST ===");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("reset", "1");
		assertEquals(wugText, getText(wugFileName));
		gitlet("reset", "3");
		System.out.println("^ Should say 'No commit with that id exists.'");
		System.out.println("=== END OF TEST ===");
	}

	/**
	 * Tests that log after one commit conforms to the format in the spec.
	 * Involves init, add, commit, and log. Also tests that resetting backwards
	 * appropriately changes the output of the log. Tests that log adjusts
	 * appropriately when switching from one branch to another.
	 */
	@Test
	public void testBasicLog() {
		System.out.println("=== BEGINNING OF LOG TEST ===");
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
		gitlet("branch", "other");
		String asdfFileName = TESTING_DIR + "asdf.txt";
		String asdfText = "asdf";
		createFile(asdfFileName, asdfText);
		gitlet("add", asdfFileName);
		String commitMessage3 = "added asdf";
		gitlet("commit", commitMessage2);
		gitlet("reset", "1");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
		gitlet("checkout", "other");
		String qwerFileName = TESTING_DIR + "qwer.txt";
		String qwerText = "qwer";
		createFile(qwerFileName, qwerText);
		gitlet("add", qwerFileName);
		String commitMessage4 = "added qwer";
		gitlet("commit", commitMessage4);
		gitlet("checkout", "master");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
		System.out.println("=== END OF TEST ===");
	}

	/**
	 * Test for merge.
	 */
	@Test
	public void testMerge() {
		System.out.println("=== BEGINNING OF MERGE TEST ===");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		// test passes when files are modified in given branch and not in the
		// current branch
		gitlet("add", wugFileName);
		gitlet("branch", "changed1");
		gitlet("branch", "notchanged1");
		gitlet("checkout", "changed1");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("checkout", "notchanged");
		gitlet("merge", "changed1");
		assertEquals("This is not a wug.", getText(wugFileName));
		// test passes files are modified in current branch and not in the given
		// branch
		gitlet("branch", "changed2");
		gitlet("branch", "notchanged2");
		gitlet("add", wugFileName);
		gitlet("checkout", "changed2");
		writeFile(wugFileName, "This might be a wug.");
		gitlet("add", wugFileName);
		gitlet("merge", "notchanged2");
		assertEquals("This might be a wug.", getText(wugFileName));
		// testing merge when given branch is the current branch
		gitlet("merge", "changed2");
		// testing when given branch doesn't exist;
		gitlet("merge", "nonexistent");
		// Testing when files that have been modified in both branches
		writeFile(wugFileName, "This is most definitely a wug.");
		gitlet("add", wugFileName);
		gitlet("checkout", "changed1");
		writeFile(wugFileName, "This is most definitely not a wug.");
		gitlet("add", wugFileName);
		gitlet("merge", "changed2");
		assertEquals("This is most definitely not a wug.", getText(wugFileName));
		gitlet("branch", "conflicted");
		System.out.println("=== END OF TEST ===");
	}

	/**
	 * Even more tests that checkout works properly.
	 */
	@Test
	public void testCheckoutRestore() {
		System.out.println("=== BEGINNING OF CHECKOUT TEST ===");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", wugFileName);
		assertEquals(wugText, getText(wugFileName));
		System.out.println("=== END OF TEST ===");
	}

	/**
	 * Tests that log prints out appropriately after rebase. Tests that changes
	 * in the base branch propagate through replayed branch.
	 */
	@Test
	public void testRebase() {
		String wug = "wug.txt";
		String wugText = "wug.";
		String wugOne = "wug1.txt";
		String oneText = "wug1.";
		String wugTwo = "wug2.txt";
		String twoText = "wug2.";
		createFile(wug, wugText);
		createFile(wugOne, oneText);
		createFile(wugTwo, twoText);
		gitlet("init");
		gitlet("add", wug);
		gitlet("commit", "added wug");
		gitlet("branch", "branch1");
		writeFile(wug, "This aint a wug.");
		gitlet("add", wug);
		gitlet("add", wugOne);
		gitlet("commit", "added wug1");
		gitlet("checkout", "branch1");
		gitlet("add", wugTwo);
		gitlet("commit", "added wug2");
		gitlet("rebase", "master");
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { "added wug2", "added wug1",
				"added wug", "initial commit" },
				extractCommitMessages(logContent));
		assertEquals(getText(wug), "This aint a wug.");

	}

	/**
	 * More tests for merge.
	 */
	@Test
	public void testMerge2() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "tis og wug.";
		String wug1 = "wug1.txt";
		String oneText = "tis wug1";
		String wug2 = "wug2.txt";
		String twoText = "tis wug2";
		createFile(wugFileName, wugText);
		createFile(wug1, oneText);
		createFile(wug2, twoText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("add", wug1);
		gitlet("commit", "added wugFileName");
		String wugBranch = "wug";
		gitlet("branch", wugBranch);
		writeFile(wug1, "xin is op");
		gitlet("add", wug1);
		writeFile(wugFileName, "This aint a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "added wugOneFile");
		gitlet("add", wug2);
		gitlet("commit", "added wugTwoFile");
		gitlet("checkout", wugBranch);
		writeFile(wug1, "until devourer gets nerfed");
		writeFile(wug2, "wug2 changed");
		gitlet("add", wug1);
		gitlet("commit", "wugBranch1 commit");
		gitlet("merge", "master");
		assertEquals("This aint a wug.", getText(wugFileName));
		assertEquals("xin is op", getText("wug1.txt.conflicted"));
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