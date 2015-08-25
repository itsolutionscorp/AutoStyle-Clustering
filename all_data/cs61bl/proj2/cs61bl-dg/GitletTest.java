import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
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
	public void testBasicInitialize() {
		System.out.println("test init");
		setUp();
		gitlet("init");
		File f = new File(GITLET_DIR);
		System.out.println(f.exists());
	}

	/**
	 * Tests that checking out a file name will restore the version of the file
	 * from the previous commit. Involves init, add, commit, and checkout.
	 */
	@Test
	public void testBasicCheckout() {
		System.out.println("test checkout");
		setUp();
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

	@Test
	public void testCheckout() {
		System.out.println("test other checkout functionalities");
		setUp();
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String wigFileName = TESTING_DIR + "wig.txt";
		String wigText = "This is a wig.";
		createFile(wigFileName, wigText);
		String bwugFileName = TESTING_DIR + "bigwug.txt";
		String bwugText = "This is a big wug.";
		createFile(bwugFileName, bwugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("add", wigFileName);
		gitlet("commit", "added wug and wig");
		gitlet("reset", "0");
		gitlet("branch", "bigwug");
		gitlet("checkout", "bigwug");
		gitlet("add", bwugFileName);
		gitlet("commit", "a big wug");
		gitlet("checkout", "master");
		assertEquals(wugText, getText(wugFileName));
		assertEquals(wigText, getText(wigFileName));
	}

	@Test
	public void testAdvCheckout() {
		System.out.println("testing AdvCheckout");
		setUp();
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		String tugFileName = TESTING_DIR + "tug.txt";
		String tugText = "This is a tug.";
		String trickFileName = "develop.txt";
		String trickText = "This is a trick.";
		createFile(wugFileName, wugText);
		createFile(tugFileName, tugText);
		createFile(trickFileName, trickText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("add", tugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "wug is no longer");
		String commit1 = "This tests if checkout keeps staged files.";
		String commit2 = "This will say something different.";
		writeFile(tugFileName, "This tests if checkout keeps staged files.");
		gitlet("add", tugFileName);
		gitlet("branch", "develop.txt");
		gitlet("checkout", "develop.txt"); // should prioritize the branch
											// name...
		gitlet("commit", "this works");
		assertEquals(tugText, getText(tugFileName));
		writeFile(tugFileName, "This tests if checkout keeps staged files.");
		gitlet("add", tugFileName);
		gitlet("commit", "changed tug");
		assertEquals(commit1, getText(tugFileName));
		gitlet("checkout", "master");
		writeFile(tugFileName, "This will say something different.");
		gitlet("add", tugFileName);
		gitlet("commit", "this will create a cool tree thing");
		assertEquals(commit2, getText(tugFileName));
		gitlet("checkout", "develop.txt");
		assertEquals(commit1, getText(tugFileName));
		gitlet("checkout", "master");
		assertEquals(commit2, getText(tugFileName));
		gitlet("checkout", "1", tugFileName);
		assertEquals(getText(tugFileName), "This is a tug.");

	}

	@Test
	public void testRm() {
		System.out.println("testing remove");
		setUp();
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String wigFileName = TESTING_DIR + "wig.txt";
		String wigText = "This is a wig.";
		createFile(wigFileName, wigText);
		String wwugFileName = TESTING_DIR + "wagwug.txt";
		String wwugText = "This is a wag wug.";
		createFile(wwugFileName, wwugText);
		gitlet("add", wugFileName);
		gitlet("add", wigFileName);
		gitlet("commit", "added wug and wig");
		gitlet("checkout", wigFileName);
		gitlet("rm", wigFileName);
		gitlet("commit", "removed wig");
		gitlet("checkout", wigFileName);
		gitlet("checkout", "1", wigFileName);
		assertEquals(getText(wigFileName), "This is a wig.");
	}

	@Test
	public void testRmBranch() {
		System.out.println("testing remove branch");
		setUp();
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		String wigFileName = TESTING_DIR + "wig.txt";
		String wigText = "This is a wig.";
		createFile(wigFileName, wigText);
		String wwugFileName = TESTING_DIR + "wagwug.txt";
		String wwugText = "This is a wag wug.";
		createFile(wwugFileName, wwugText);
		gitlet("rm-branch", "master");
		gitlet("add", wugFileName);
		gitlet("add", wigFileName);
		gitlet("commit", "add wug and wig");
		gitlet("branch", "swag");
		gitlet("checkout", "swag");
		gitlet("add", wwugFileName);
		gitlet("commit", "swag wug");
		gitlet("checkout", "master");
		gitlet("rm-branch", "swag");
		gitlet("checkout", "swag");
	}

	@Test
	public void testReset() {
		System.out.println("test reset");
		setUp();
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		String tugFileName = TESTING_DIR + "tug.txt";
		String tugText = "This is a tug.";
		String trickFileName = TESTING_DIR + "develop.txt";
		String trickText = "This is a trick.";
		createFile(wugFileName, wugText);
		createFile(tugFileName, tugText);
		createFile(trickFileName, trickText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("add", tugFileName);
		gitlet("commit", "added wug"); // should be commit 1
		writeFile(wugFileName, "This is not a wug.");
		writeFile(tugFileName, "This is not a tug.");
		gitlet("add", tugFileName);
		gitlet("add", wugFileName);
		gitlet("commit", "wug and tug are no longer"); // commit 2
		String commit1 = "This tests if checkout keeps staged files.";
		String commit2 = "This will say something different.";
		writeFile(tugFileName, "This tests if checkout keeps staged files.");
		gitlet("add", tugFileName);
		gitlet("branch", "develop.txt");
		gitlet("checkout", "develop.txt"); // should prioritize the branch
											// name...
		gitlet("commit", "this works"); // commit 3
		writeFile(tugFileName, "This tests if checkout keeps staged files.");
		gitlet("add", tugFileName);
		gitlet("commit", "changed tug"); // commit 4
		gitlet("checkout", "master");
		writeFile(tugFileName, "This will say something different.");
		gitlet("add", tugFileName);
		gitlet("commit", "this will create a cool tree thing"); // commit 5
		String log5 = gitlet("log");
		assertArrayEquals(new String[] { "this will create a cool tree thing",
				"wug and tug are no longer", "added wug", "initial commit" },
				(extractCommitMessages(log5)));
		gitlet("reset", "3");
		String log3 = gitlet("log");
		assertArrayEquals(new String[] { "this works",
				"wug and tug are no longer", "added wug", "initial commit" },
				(extractCommitMessages(log3)));
		assertEquals(getText(tugFileName),
				"This tests if checkout keeps staged files.");
		gitlet("reset", "2");
		String log2 = gitlet("log");
		assertArrayEquals(new String[] { "wug and tug are no longer",
				"added wug", "initial commit" }, (extractCommitMessages(log2)));
		assertEquals(getText(wugFileName), "This is not a wug.");
		gitlet("reset", "2");
		assertEquals(getText(tugFileName), "This is not a tug.");
	}

	/**
	 * Tests that log after one commit conforms to the format in the spec.
	 * Involves init, add, commit, and log.
	 */
	@Test
	public void testBasicLog() {
		System.out.println("test log");
		setUp();
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

	@Test
	public void testBranches() {
		System.out.println("test branching");
		setUp();
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		String notwugText = "This is not a wug.";
		String bigwugText = "This is a huge wug!";
		String masterwugText = "This is the master wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("branch", "not wug");
		gitlet("checkout", "not wug");
		writeFile(wugFileName, notwugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added no wug");
		gitlet("checkout", "master");
		gitlet("branch", "big wug");
		gitlet("checkout", "big wug");
		writeFile(wugFileName, bigwugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added big wug");
		gitlet("checkout", "master");
		assertEquals(wugText, (getText(wugFileName)));
		gitlet("add", wugFileName);
		gitlet("commit", "added master wug");
		gitlet("checkout", "not wug");
		assertEquals(notwugText, getText(wugFileName));
		gitlet("checkout", "big wug");
		assertEquals(bigwugText, getText(wugFileName));
		gitlet("checkout", "1", wugFileName);
		assertEquals(wugText, getText(wugFileName));
	}

	@Test
	public void testMerge() {
		System.out.println("test Merge");
		setUp();
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		String tugFileName = TESTING_DIR + "tug.txt";
		String tugText = "This is a tug.";
		String trickFileName = "develop.txt";
		String trickText = "This is a trick.";
		createFile(wugFileName, wugText);
		createFile(tugFileName, tugText);
		createFile(trickFileName, trickText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("add", tugFileName);
		gitlet("commit", "added wug"); // should be commit 1
		writeFile(wugFileName, "This is not a wug.");
		writeFile(tugFileName, "This is not a tug.");
		gitlet("add", tugFileName);
		gitlet("add", wugFileName);
		gitlet("commit", "wug and tug are no longer"); // commit 2
		String commit1 = "This tests if checkout keeps staged files.";
		String commit2 = "This will say something different.";
		writeFile(tugFileName, "This tests if checkout keeps staged files.");
		gitlet("add", tugFileName);
		gitlet("branch", "develop.txt");
		gitlet("checkout", "develop.txt"); // should prioritize the branch
											// name...
		gitlet("commit", "this works"); // commit 3
		writeFile(tugFileName, "This tests if checkout keeps staged files.");
		gitlet("add", tugFileName);
		gitlet("commit", "changed tug"); // commit 4
		gitlet("add", trickFileName);
		gitlet("commit", "added a completely new file");
		writeFile(trickFileName, "This is a trick.");
		gitlet("checkout", "master");
		writeFile(tugFileName, "This will say something different.");
		gitlet("add", tugFileName);
		gitlet("commit", "this will create a cool tree thing"); // commit 5
		writeFile(wugFileName, "Changed wugFileName");
		gitlet("add", wugFileName);
		gitlet("commit", "changed wugFileName");
		// current branch is master
		gitlet("merge", "develop.txt");
		gitlet("commit", "merged develop.txt into master");
		assertEquals(getText(tugFileName + ".conflicted"),
				("This tests if checkout keeps staged files."));
		assertEquals(trickText, (getText(trickFileName)));
		assertEquals(getText(tugFileName),
				("This will say something different."));
		assertEquals(getText(wugFileName), ("Changed wugFileName"));
	}

	@Test
	public void testRebase() {
		System.out.println("test Rebase");
		setUp();
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		String tugFileName = TESTING_DIR + "tug.txt";
		String tugText = "This is a tug.";
		String trickFileName = "develop.txt";
		String trickText = "This is a trick.";
		String lugFileName = "lug.txt";
		String lugText = "This is a lug.";
		createFile(wugFileName, wugText);
		createFile(tugFileName, tugText);
		createFile(trickFileName, trickText);
		createFile(lugFileName, lugText);
		String commit0 = "initial commit";
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("add", tugFileName);
		String commit1 = "added wug";
		gitlet("commit", commit1); // should be commit 1
		writeFile(wugFileName, "This is not a wug.");
		writeFile(tugFileName, "This is not a tug.");
		gitlet("add", tugFileName);
		gitlet("add", wugFileName);
		String commit2 = "wug and tug are no longer";
		gitlet("commit", "wug and tug are no longer"); // commit 2
		writeFile(tugFileName, "This tests if checkout keeps staged files.");
		gitlet("add", tugFileName);
		gitlet("branch", "develop.txt");
		gitlet("checkout", "develop.txt"); // should prioritize the branch
											// name...
		String commit3 = "this works";
		gitlet("commit", "this works"); // commit 3
		writeFile(tugFileName, "This tests if checkout keeps staged files.");
		gitlet("add", tugFileName);
		String commit4 = "changed tug";
		gitlet("commit", commit4); // commit 4
		gitlet("add", trickFileName);
		String commit5 = "added a completely new file";
		gitlet("commit", commit5);
		gitlet("add", lugFileName);
		String commit6 = "added another file";
		gitlet("commit", commit6);
		gitlet("checkout", "master");
		writeFile(tugFileName, "This will say something different.");
		gitlet("add", tugFileName);
		String commit7 = "this will create a cool tree thing";
		gitlet("commit", commit7); // commit 7
		gitlet("branch", "extra"); // new branch extra at commit 5
		gitlet("checkout", "extra");
		writeFile(tugFileName, "Wow, I am changed often...");
		gitlet("add", tugFileName);
		String commit8 = "Changed tug again!";
		gitlet("commit", commit8);
		gitlet("checkout", "master");
		writeFile(wugFileName, "Changed wugFileName");
		gitlet("add", wugFileName);
		String commit9 = "changed wugFileName";
		gitlet("commit", commit9);
		String commit10 = commit7;
		String commit11 = commit9;
		// current branch is master
		gitlet("rebase", "develop.txt");
		assertEquals(getText(trickFileName), ("This is a trick."));
		assertEquals(getText(tugFileName),
				("This will say something different."));
		assertEquals(getText(wugFileName), ("Changed wugFileName"));
		String logContent = gitlet("log");
		String[] lContent = extractCommitMessages(logContent);
		String[] trueContent = new String[] { commit11, commit10, commit6,
				commit5, commit4, commit3, commit2, commit1, commit0 };
		assertArrayEquals(lContent, trueContent);
		String logContent2 = gitlet("global-log");
		String[] lContent2 = extractCommitMessages(logContent2);
		String[] trueContent2 = new String[] { commit0, commit1, commit2,
				commit3, commit4, commit5, commit6, commit7, commit8, commit9,
				commit10, commit11 };
		assertArrayEquals(lContent2, trueContent2);
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
