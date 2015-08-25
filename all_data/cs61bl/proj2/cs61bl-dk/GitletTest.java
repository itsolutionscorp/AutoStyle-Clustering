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
	 * Tests that log after multiple commits conforms to the format in the spec.
	 * Involves init, add, commit, log, branch, and checkout. 
	 */
	@Test
	public void testLog() {
		gitlet("init");
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", "wugFileName");
		gitlet("commit", "initial commit");

		String wugFileName2 = TESTING_DIR + "wug0.txt";
		String wugText2 = "This is a wug2.";
		createFile(wugFileName2, wugText2);
		gitlet("add", wugFileName2);
		gitlet("commit", "added wug0");

		String wugFileName3 = TESTING_DIR + "wug1.txt";
		String wugText3 = "This is a wug3.";
		createFile(wugFileName3, wugText3);
		gitlet("add", wugFileName3);
		gitlet("commit", "added wug1");

		String logT = gitlet("log");
		String[] cMessages = new String[] { "added wug1", "added wug0",
				"initial commit" };
		assertArrayEquals(cMessages, extractCommitMessages(logT));

		gitlet("branch", "nbranch");
		gitlet("checkout", "nbranch");

		String wugFileName4 = TESTING_DIR + "wuggy.txt";
		String wugText4 = "This is a wug4.";
		createFile(wugFileName4, wugText4);
		gitlet("add", wugFileName4);
		gitlet("commit", "added wuggy");
		
		String newBLog = gitlet("log");
		String[] newBCommits = new String[] { "added wuggy", "added wug1",
				"added wug0", "initial commit" };
		assertArrayEquals(newBCommits, extractCommitMessages(newBLog));
	}

	/**
	 * Tests that all files have been checkout out of the commit and
	 * moves the current branch's head to the commit node.
	 */
	@Test
	public void testReset() {

		gitlet("init");

		String fileA = TESTING_DIR + "fileA.txt";
		createFile(fileA, "fileA1");

		String fileB = TESTING_DIR + "fileB.txt";
		createFile(fileB, "fileB1");

		gitlet("add", fileA);
		gitlet("add", fileB);
		gitlet("commit", "first commit");

		createFile(fileA, "fileA2");
		gitlet("add", fileA);
		gitlet("commit", "second commit");

		assertEquals(getText(fileA), "fileA2");
		assertEquals(getText(fileB), "fileB1");
		gitlet("reset", gitlet("find", "first commit"));
		assertEquals(getText(fileA), "fileA1");
		assertEquals(getText(fileB), "fileB1");

	}
	
	/**
	 * Tests if rebase and checkout method works according to spec. Tests if we are 
	 * currently finding split point of the current and given branch and snaps
	 * it off the current branch at the split point and reattaches at the head
	 * of the given branch.
	 */
	@Test
	public void testRebase() {
		gitlet("init");

		String fileA = TESTING_DIR + "fileA.txt";
		createFile(fileA, "fileA1");
		gitlet("add", fileA);

		String fileB = TESTING_DIR + "fileB.txt";
		createFile(fileB, "fileB1");
		gitlet("add", fileB);

		gitlet("commit", "master branch - fileA1 and fileB1");

		writeFile(fileA, "fileA2");
		gitlet("add", fileA);
		gitlet("commit", "master branch - fileA2");
		gitlet("branch", "second branch");
		gitlet("checkout", "second branch");

		writeFile(fileA, "fileA3");
		gitlet("add", fileA);
		gitlet("commit", "second branch - fileA3");
		gitlet("checkout", "master");

		writeFile(fileB, "fileB2");
		gitlet("add", fileB);
		gitlet("commit", "master branch - fileB2");
		
		gitlet("checkout", "second branch");
		assertEquals(getText(fileB), "fileB1");

		gitlet("rebase", "master");

		assertEquals(getText(fileA), "fileA3");
		assertEquals(getText(fileB), "fileB2");
	}
	
	/**
	 * Tests if rebase and checkout method works according to spec. Tests if we are 
	 * currently finding split point of the current and given branch and snaps
	 * it off the current branch at the split point and reattaches at the head
	 * of the given branch.
	 */
	@Test
	public void testRebase2() {
		gitlet("init");

		String fileA = TESTING_DIR + "fileA.txt";
		String fileAText = "A1";
		createFile(fileA, fileAText);
		gitlet("add", fileA);

		String fileB = TESTING_DIR + "fileB.txt";
		String fileBText = "B1";
		createFile(fileB, fileBText);
		gitlet("add", fileB);

		gitlet("commit", "master branch - A1 and B1");

		writeFile(fileA, "A2");
		gitlet("add", fileA);
		gitlet("commit", "master branch - A2");
		gitlet("branch", "second branch");
		gitlet("checkout", "second branch");

		writeFile(fileA, "A3");
		gitlet("add", fileA);
		gitlet("commit", "second branch - A3");
		gitlet("checkout", "master");

		writeFile(fileB, "B2");
		gitlet("add", fileB);
		gitlet("commit", "master branch - B2");
		gitlet("checkout", "second_branch");

		writeFile(fileA, "A4");
		gitlet("add", fileA);
		writeFile(fileB, "B3");
		gitlet("add", fileB);
		gitlet("commit", "second branch - A4 and B3");

		writeFile(fileB, "B4");
		gitlet("add", fileB);
		gitlet("commit", "second branch - B4");

		gitlet("rebase", "second_branch");
		assertEquals(getText(fileA), "A4");
		assertEquals(getText(fileB), "B4");
	}
			
/**
 * Tests if commit works according to the spec.
 * Tests that commiting will work on a new branch.
 */

	@Test
	public void testBranchCommit() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugMaster = "master";
		String wugNew = "newbranch";
		createFile(wugFileName, wugMaster);
		gitlet("init");
		gitlet("branch", "newbranch");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		gitlet("checkout", "newbranch");
		writeFile(wugFileName, wugNew);
		gitlet("add", wugFileName);
		gitlet("commit", "this is the newbranch");

		String BWug = getText(wugFileName);
		assertTrue(BWug.equals(wugNew));

		gitlet("checkout", "master");
		BWug = getText(wugFileName);
		assertTrue(BWug.equals(wugMaster));

		gitlet("checkout", "newbranch");
		BWug = getText(wugFileName);
		assertTrue(BWug.equals(wugNew));
	}
	
	/**
	 * Tests if merge works according to the spec.
	 * Tests that when merging branches with conflicts, conflicts are caught.
	 */

	@Test
	public void testMergeWithConflicts() {
		gitlet("init");

		String file1 = TESTING_DIR + "1.txt";
		String fileText1 = "1";
		createFile(file1, fileText1);

		String file2 = TESTING_DIR + "2.txt";
		String fileText2 = "2";
		createFile(file2, fileText2);

		String file3 = TESTING_DIR + "3.txt";
		String fileText3 = "3";
		createFile(file3, fileText3);

		String file4 = TESTING_DIR + "4.txt";
		String fileText4 = "4";
		createFile(file4, fileText4);

		gitlet("add", file1);
		gitlet("add", file2);
		gitlet("add", file3);
		gitlet("add", file4);

		String splitpoint = "split point";
		String masterCommit2 = "master commit 2";
		String newbranchCommit2 = "newbranch commit 2";

		gitlet("commit", splitpoint);

		gitlet("branch", "newbranch");

		writeFile(file1, "1 in master");
		writeFile(file2, "2 in master");

		gitlet("add", file1);
		gitlet("add", file2);
		gitlet("commit", masterCommit2);

		gitlet("checkout", "newbranch");

		writeFile(file2, "2 in newbranch");
		writeFile(file3, "3 in newbranch");
		writeFile(file4, "4 in newbranch");

		gitlet("add", file1);
		gitlet("add", file2);
		gitlet("add", file3);
		gitlet("rm", file4);
		gitlet("commit", newbranchCommit2);

		gitlet("merge", "master");

		assertEquals("1 in master", getText(file1));
		assertEquals("2 in newbranch", getText(file2));
		assertEquals("2 in master", getText(file2 + ".conflicted"));
		assertEquals("3 in newbranch", getText(file3));
		assertEquals("4", getText(file4));

		String commitMessage1 = "initial commit";

		String logContent = gitlet("log");
		System.out.println(extractCommitMessages(logContent));
		assertArrayEquals(new String[] { newbranchCommit2, splitpoint,
				commitMessage1 }, extractCommitMessages(logContent));
	}
	
	/**
	 * Tests if merge works according to the spec.
	 * Tests that merging branches works successfully.
	 */

	@Test
	public void testMergeSuccessful() {
		gitlet("init");

		String file1 = TESTING_DIR + "1.txt";
		String fileText1 = "1";
		createFile(file1, fileText1);

		String file2 = TESTING_DIR + "2.txt";
		String fileText2 = "2";
		createFile(file2, fileText2);

		String file3 = TESTING_DIR + "3.txt";
		String fileText3 = "3";
		createFile(file3, fileText3);

		String file4 = TESTING_DIR + "4.txt";
		String fileText4 = "4";
		createFile(file4, fileText4);

		gitlet("add", file1);
		gitlet("add", file2);
		gitlet("add", file3);
		gitlet("add", file4);

		String splitpoint = "split point";
		String masterCommit2 = "master commit 2";
		String newbranchCommit2 = "newbranch commit 2";

		gitlet("commit", splitpoint);

		gitlet("branch", "newbranch");

		writeFile(file1, "1 in master");
		writeFile(file2, "2 in master");

		gitlet("add", file1);
		gitlet("add", file2);
		gitlet("commit", masterCommit2);

		gitlet("checkout", "newbranch");

		writeFile(file3, "3 in newbranch");
		writeFile(file4, "4 in newbranch");

		gitlet("add", file1);
		gitlet("add", file2);
		gitlet("add", file3);
		gitlet("rm", file4);
		gitlet("commit", newbranchCommit2);

		gitlet("merge", "master");

		assertEquals("1 in master", getText(file1));
		assertEquals("2 in master", getText(file2));
		assertEquals("3 in newbranch", getText(file3));
		assertEquals("4", getText(file4));

		String commitMessage1 = "initial commit";
		String mergeCommitMsg = "Merged newbranch with master.";

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { mergeCommitMsg, newbranchCommit2,
				splitpoint, commitMessage1 }, extractCommitMessages(logContent));
	}

	/**
	 * Tests if the method remove works according to the spec. Tests if
	 * the file has been marked for untracking or if the file had been 
	 * unstaged.
	 */
	@Test
	public void testRemove() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);

		gitlet("commit", "added wug");

		writeFile(wugFileName, "This is not a wug.");

		gitlet("rm", wugFileName);
		String thisCommit = gitlet("commit", "removed");

		assertTrue(!thisCommit.contains(wugFileName));
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