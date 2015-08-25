import junit.framework.TestCase;

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
public class GitletTest extends TestCase {
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
	 * Test that a file that has been committed can be restored by checking it out from a commit
	 * that tracks that version of the file, even if the file wasn't staged prior to that commit.
	*/
	@Test
	public void testTrackingCheckout() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		
		String secondFileName = TESTING_DIR + "wuglit.txt";
		String wuglitText = "This is a lil wuglit";
		createFile(secondFileName, wuglitText);
		gitlet("add", secondFileName);
		gitlet("commit", "added wuglit");
		
		gitlet("checkout", "2", wugFileName);
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
	
	//test that merge will generate a conflicted file (merging two files both modified after splitpoint)
	public void testMergeConflict(){
		String FileName = TESTING_DIR + "movies.txt";
		String text = "The Shining";
		createFile(FileName, text);
		gitlet("init");
		String commitMessage0 = "initial commit";
		gitlet("add", FileName);
		String commitMessage1 = "Psycho";
		gitlet("commit", commitMessage1);
		
		gitlet("branch", "newBr");
		gitlet("checkout", "newBr");

		writeFile(FileName, "The Exorcist");
		gitlet("add", FileName);
		String commitMessage2 = "Carrie";
		gitlet("commit", commitMessage2);
		
		gitlet("checkout", "master");

		writeFile(FileName, "Full Metal Jacket");
		gitlet("add", FileName);
		String commitMessage3 = "The Haunting";
		gitlet("commit", commitMessage3);
		
		String result = gitlet("merge", "newBr");
		
		assertEquals(result, "Encountered a merge conflict.");
	}
	
	
	//test merge will commit w files modified in nonCurrent branch since splitPoint
	public void testMergeCommits(){
		String FileName = TESTING_DIR + "movies.txt";
		String text = "The Shining";
		createFile(FileName, text);
		gitlet("init");
		String commitMessage0 = "initial commit";
		gitlet("add", FileName);
		String commitMessage1 = "Psycho";
		gitlet("commit", commitMessage1);
		
		writeFile(FileName, "The Exorcist");
		gitlet("add", FileName);
		String commitMessage2 = "Carrie";
		gitlet("commit", commitMessage2);
		
		gitlet("branch", "newBr");
		gitlet("checkout", "newBr");
		
		String fileName2 = TESTING_DIR + "books.txt";
		text = "";
		createFile(fileName2, text);
		writeFile(fileName2, "Dune");
		gitlet("add", fileName2);
		String commitMessage5 = "Girl, Interrupted";
		gitlet("commit", commitMessage5);
		
		writeFile(FileName, "Silence of the Lambs");
		gitlet("add", FileName);
		String commitMessage3 = "The Ring";
		gitlet("commit", commitMessage3);
		
		
		gitlet("checkout", "master");

		writeFile(FileName, "Full Metal Jacket");
		gitlet("add", FileName);
		String commitMessage4 = "The Haunting";
		gitlet("commit", commitMessage4);
		
		gitlet("merge", "newBr");

		assertEquals(getText(FileName), "Full Metal Jacket");
	}
	
	
	@Test
	public void testRebasePropogating(){
		String FileName = TESTING_DIR + "Cities.txt";
		String HelloText = "Chicago";
		createFile(FileName, HelloText);
		gitlet("init");
		String commitMessage0 = "initial commit";
		gitlet("add", FileName);
		String commitMessage1 = "Illinois";
		gitlet("commit", commitMessage1);
		
		gitlet("branch", "newBr");
		gitlet("checkout", "newBr");

		writeFile(FileName, "Providence");
		gitlet("add", FileName);
		String commitMessage2 = "Rhode Island";
		gitlet("commit", commitMessage2);
		
		gitlet("checkout", "master");

		writeFile(FileName, "Boston");
		gitlet("add", FileName);
		String commitMessage3 = "Massachusetts";
		gitlet("commit", commitMessage3);
		writeFile(FileName, "Pittsburgh");
		gitlet("add", FileName);
		String commitMessage4 = "Pennsylvania";
		gitlet("commit", commitMessage4);
		gitlet("rebase", "master");

		String logContent = gitlet("log");
		assertEquals(getText(FileName), "Pittsburgh");

	}
	
	@Test
	public void testLogAfterReset() {
		gitlet("init");
		String commitMessage0 = "initial commit";

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		String commitMessage1 = "added wug";
		gitlet("commit", commitMessage1);
		
		writeFile(wugFileName, "This is a wug!");
		gitlet("add", wugFileName);
		String commitMessage2 = "added another wug";
		gitlet("commit", commitMessage2);
		
		gitlet("add", wugFileName);
		String commitMessage3 = "added one more wug";
		gitlet("commit", commitMessage3);

		gitlet("reset", "1");
		String logContent = gitlet("log");
		assertArrayEquals(new String[] {commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));
	}
	
	
	public void testLogAfterRebase(){
		String FileName = TESTING_DIR + "Cities.txt";
		String HelloText = "Chicago";
		createFile(FileName, HelloText);
		gitlet("init");
		String commitMessage0 = "initial commit";
		gitlet("add", FileName);
		String commitMessage1 = "Illinois";
		gitlet("commit", commitMessage1);
		
		gitlet("branch", "newBr");
		gitlet("checkout", "newBr");

		writeFile(FileName, "Providence");
		gitlet("add", FileName);
		String commitMessage2 = "Rhode Island";
		gitlet("commit", commitMessage2);
		
		gitlet("checkout", "master");

		writeFile(FileName, "Boston");
		gitlet("add", FileName);
		String commitMessage3 = "Massachusetts";
		gitlet("commit", commitMessage3);
		writeFile(FileName, "Pittsburgh");
		gitlet("add", FileName);
		String commitMessage4 = "Pennsylvania";
		gitlet("commit", commitMessage4);
		gitlet("rebase", "master");

		String logContent = gitlet("log");
		System.out.println(logContent);
		System.out.println(getText(FileName));
		assertArrayEquals(new String[] { commitMessage4, commitMessage3, commitMessage1, commitMessage0 },
				extractCommitMessages(logContent));
	}
	
	
}