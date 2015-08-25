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
	private static final String TESTING_DIR = ""; // test_files/

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

	/**
	 * Test that a file that has been committed at some point can be restored by
	 * checking it out from the commit it was committed at.
	 */
	@Test
	public void testOne() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");

		String dogFileName = TESTING_DIR + "dog.txt";
		String dogText = "This is a dog.";
		createFile(dogFileName, dogText);
		gitlet("add", dogFileName);
		gitlet("commit", "added dog");

		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", wugFileName);
		assertEquals(wugText, getText(wugFileName));
	}

	/**
	 * Test that a file that has been committed at some point can be restored by
	 * checking it out from a commit that tracks that version of the file, even
	 * if the file wasn't staged prior to that commit
	 */
	@Test
	public void testTwo() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");

		String dogFileName = TESTING_DIR + "dog.txt";
		String dogText = "This is a dog.";
		createFile(dogFileName, dogText);
		gitlet("add", dogFileName);
		gitlet("commit", "added dog");

		gitlet("rm", dogFileName);
		gitlet("commit", "removed dog");

		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", wugFileName);
		assertEquals(wugText, getText(wugFileName));

	}

	/**
	 * Test that your can checkout [id] [file] from an arbitrary commit in the
	 * graph
	 */
	@Test
	public void testThree() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug one.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		gitlet("commit", "added wug one");

		writeFile(wugFileName, "This is a wug two.");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug two.");

		writeFile(wugFileName, "This is a wug three.");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug three.");

		writeFile(wugFileName, "This is a wug four.");
		gitlet("checkout", "1", "wug.txt");
		assertEquals("This is a wug one.", getText(wugFileName));
		gitlet("checkout", "2", "wug.txt");
		assertEquals("This is a wug two.", getText(wugFileName));

	}

	/**
	 * Test that resetting backward appropriately changes the output of log.
	 */
	@Test
	public void testFour() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String dogFileName = TESTING_DIR + "dog.txt";
		String dogText = "This is a dog.";
		createFile(dogFileName, dogText);

		// first
		gitlet("add", wugFileName);
		gitlet("commit", "added wug one");

		gitlet("branch", "another");
		gitlet("checkout", "another");
		// second
		gitlet("add", dogFileName);
		gitlet("commit", "added dog");
		gitlet("reset", "1");

		assertArrayEquals(new String[] { "added wug one", "initial commit" }, extractCommitMessages(gitlet("log")));

	}

	/**
	 * Test that log adjusts appropriately when switching from one branch to
	 * another
	 */
	@Test
	public void testFive() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String dogFileName = TESTING_DIR + "dog.txt";
		String dogText = "This is a dog.";
		createFile(dogFileName, dogText);

		String catFileName = TESTING_DIR + "cat.txt";
		String catText = "This is a cat.";
		createFile(catFileName, catText);
		// first
		gitlet("add", wugFileName);
		gitlet("commit", "added wug one");

		gitlet("branch", "another");
		gitlet("checkout", "another");
		// second
		gitlet("add", dogFileName);
		gitlet("commit", "added dog");
		gitlet("reset", "1");
		// third
		gitlet("add", catFileName);
		gitlet("commit", "added cat");
		assertArrayEquals(new String[] { "added cat", "added wug one", "initial commit" },
				extractCommitMessages(gitlet("log")));
	}

	/**
	 * Test that merge will generate a .conflicted file if a file has been
	 * modified in both branches since the split point.
	 */
	@Test
	public void testSix() {
		gitlet("init");

		String dogFileName = TESTING_DIR + "dog.txt";
		String dogText = "This is a dog.";
		createFile(dogFileName, dogText);

		String catFileName = TESTING_DIR + "cat.txt";
		String catText = "This is a cat.";
		createFile(catFileName, catText);
		// first
		gitlet("add", catFileName);
		gitlet("commit", "added cat");

		gitlet("branch", "another");
		gitlet("checkout", "another");
		// second
		gitlet("add", dogFileName);
		writeFile(catFileName, "This is a cat two.");
		gitlet("add", catFileName);
		gitlet("commit", "added dog and modified cat");
		// third
		gitlet("reset", "1");
		// fourth
		writeFile(catFileName, "This is a cat three.");
		gitlet("add", catFileName);
		gitlet("commit", "modified cat");
		gitlet("merge", "another");
		File temp = new File("cat.conflicted.txt");
		assertTrue(temp.exists());
	}

	/**
	 * Test that merge will commit with files from the other branch if those
	 * files had been modified in the other branch but not in the current branch
	 * since the split point.
	 */
	@Test
	public void testSeven() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String dogFileName = TESTING_DIR + "dog.txt";
		String dogText = "This is a dog.";
		createFile(dogFileName, dogText);

		String catFileName = TESTING_DIR + "cat.txt";
		String catText = "This is a cat.";
		createFile(catFileName, catText);
		// first
		gitlet("add", catFileName);
		gitlet("commit", "added cat one");

		gitlet("branch", "another");
		gitlet("checkout", "another");
		// second
		gitlet("add", dogFileName);
		writeFile(catFileName, "This is a cat two.");
		gitlet("add", catFileName);
		gitlet("commit", "added dog and modified cat");
		// third
		gitlet("reset", "1");
		gitlet("add", wugFileName);
		gitlet("rm", catFileName);
		gitlet("commit", "removed cat and added wug");
		gitlet("merge", "another");
		File temp = new File(".gitlet/commit 4/cat.txt");
		File temp2 = new File(".gitlet/commit 4/dog.txt");
		assertTrue(temp.exists());
		assertTrue(temp2.exists());
	}

	/**
	 * Test that the output of log after a rebase includes the commit messages
	 * from both branches involved in the rebase.
	 */
	@Test
	public void testEight() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String catFileName = TESTING_DIR + "cat.txt";
		String catText = "This is a cat.";
		createFile(catFileName, catText);
		// first
		gitlet("add", catFileName);
		gitlet("commit", "added cat");

		// second
		writeFile(catFileName, "This is a cat two.");
		gitlet("add", catFileName);
		gitlet("commit", "modified cat");
		
		gitlet("reset", "1");
		gitlet("branch", "another");
		gitlet("checkout", "another");
		// third
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		// fourth
		gitlet("rm", "cat.txt");
		gitlet("commit","removed cat");
		
		gitlet("rebase","master");
		assertArrayEquals(new String[] { "removed cat", "added wug", "modified cat", "added cat",
				"initial commit" }, extractCommitMessages(gitlet("log")));
	}

	/**
	 * Test that changes in the base branch propagate through the replayed
	 * branch during a rebase.
	 */
	@ Test
	public void testNine() {
		gitlet("init");

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String catFileName = TESTING_DIR + "cat.txt";
		String catText = "This is a cat.";
		createFile(catFileName, catText);
		// first
		gitlet("add", catFileName);
		gitlet("commit", "added cat");

		// second
		writeFile(catFileName, "This is a cat two.");
		gitlet("add", catFileName);
		gitlet("commit", "modified cat");
		
		gitlet("reset", "1");
		gitlet("branch", "another");
		gitlet("checkout", "another");
		// third
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		// fourth
		gitlet("rm", "cat.txt");
		gitlet("commit","removed cat");
		
		gitlet("rebase","master");
		gitlet("checkout", catFileName);
		assertEquals("This is a cat two.", getText(catFileName));
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