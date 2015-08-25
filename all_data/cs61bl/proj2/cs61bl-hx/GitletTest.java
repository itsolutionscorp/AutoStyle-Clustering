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
	private static final String DIRECTORY = System.getProperty("user.dir");
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
	/***
	 * Test that a file that has been committed at some point can be restored by checking it out from the commit it was committed at.
	 */
	@Test
	public void testCheckoutAtSomePoint() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);

		String wugFileName1 = TESTING_DIR + "wug1.txt";
		String wugText1 = "This is a wug1.";
		createFile(wugFileName1, wugText1);

		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");

		gitlet("add", wugFileName);
		gitlet("commit", "added not wug");

		gitlet("add", wugFileName1);
		gitlet("commit", "added wug1");

		gitlet("checkout", "1",wugFileName);
		assertEquals(wugText, getText(wugFileName));
	}
	/***Test that a file that has been committed at some point can be restored by checking it out from a commit that tracks that version of the file, even if the file wasn't staged prior to that commit.
	 * 
	 */
	@Test
	public void testCheckoutFromTrackCommit() {
		String wugFileName = TESTING_DIR + "1.txt";
		String wugText = "1.";
		createFile(wugFileName, wugText);

		String wugFileName1 = TESTING_DIR + "2.txt";
		String wugText1 = "2.";
		createFile(wugFileName1, wugText1);

		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added 1");
		writeFile(wugFileName, "1 and .5.");		

		gitlet("add", wugFileName1);
		gitlet("commit", "added 2");

		gitlet("checkout" ,wugFileName);
		assertEquals(wugText, getText(wugFileName));
	}
	/***
	 * Test that you can checkout [id] [file] from an arbitrary commit in the graph (even one in another branch).
	 */
	@Test
	public void testCheckoutFromOtherBranch() {
		String wugFileName = TESTING_DIR + "1.txt";
		String wugText = "1.";
		createFile(wugFileName, wugText);

		String wugFileName1 = TESTING_DIR + "2.txt";
		String wugText1 = "2.";
		createFile(wugFileName1, wugText1);

		gitlet("init");
		gitlet("branch","TA");

		gitlet("add", wugFileName);
		gitlet("commit", "added 1");
		writeFile(wugFileName, "1 and .5.");		

		gitlet("checkout","TA");

		gitlet("add", wugFileName1);
		gitlet("commit", "added 2");

		gitlet("checkout", "1",wugFileName);
		assertEquals(wugText, getText(wugFileName));
	}
	/***
	 * Test that resetting backward appropriately changes the output of log.
	 */
	@Test
	public void testResetLog() {
		gitlet("init");
		String commitMessage1 = "initial commit";

		String wugFileName = TESTING_DIR + "1.txt";
		String wugText = "This is 1.";
		createFile(wugFileName, wugText);

		gitlet("add", wugFileName);		
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);

		String wugFileName1 = TESTING_DIR + "2.txt";
		String wugText1 = "2.";
		createFile(wugFileName1, wugText1);

		gitlet("add", wugFileName1);		
		String commitMessage3 = "added 2";
		gitlet("commit", commitMessage3);

		gitlet("reset", "1");

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
	}
	/***
	 * Test that log adjusts appropriately when switching from one branch to another.
	 */
	@Test
	public void testLogBranches() {
		gitlet("init");
		gitlet("branch","TA");

		String commitMessage1 = "initial commit";

		String wugFileName = TESTING_DIR + "1.txt";
		String wugText = "1.";
		createFile(wugFileName, wugText);

		String wugFileName1 = TESTING_DIR + "2.txt";
		String wugText1 = "2.";
		createFile(wugFileName1, wugText1);

		gitlet("add", wugFileName);		
		String commitMessage2 = "added 1";
		gitlet("commit", commitMessage2);		

		gitlet("checkout", "TA");

		gitlet("add", wugFileName1);		
		String commitMessage3 = "added 2";
		gitlet("commit", commitMessage3);		

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage3, commitMessage1 },
				extractCommitMessages(logContent));
	}
	
	/***
	 * Test that the output of log after a rebase includes the commit messages from 
	 * both branches involved in the rebase.
	 */
	@Test
	public void testOutputAfterRebase() {
		gitlet("init");		
		String commitMessage1 = "initial commit";
		
		String wugFileName = TESTING_DIR + "1.txt";
		String wugText = "1.";
		createFile(wugFileName, wugText);

		String wugFileName1 = TESTING_DIR + "2.txt";
		String wugText1 = "2.";
		createFile(wugFileName1, wugText1);
		
		String wugFileName2 = TESTING_DIR + "3.txt";
		String wugText2 = "3.";
		createFile(wugFileName2, wugText2);
		
		gitlet("add", wugFileName);		
		String commitMessage2 = "added 1";
		gitlet("commit", commitMessage2);
		
		gitlet("branch","TA");		

		gitlet("add", wugFileName1);		
		String commitMessage3 = "added 2";
		gitlet("commit", commitMessage3);	
		
		gitlet("checkout", "TA");
		
		gitlet("add", wugFileName2);		
		String commitMessage4 = "added 3";
		gitlet("commit", commitMessage4);
		
		gitlet("rebase", "master");
		
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage4, commitMessage3,
				commitMessage2, commitMessage1 }, extractCommitMessages(logContent));
		
		gitlet("checkout", "master");
		assertArrayEquals(new String[] { commitMessage4, commitMessage3,
				commitMessage2, commitMessage1 }, extractCommitMessages(logContent));	
	}

	/***
	 * Test that the output of log after a rebase includes the commit messages
	 * from both branches involved in the rebase.
	 */
	@Test
	public void testProgationRebase() {
		gitlet("init");		
		String commitMessage1 = "initial commit";
		
		String wugFileName = TESTING_DIR + "1.txt";
		String wugText = "1";
		createFile(wugFileName, wugText);

		String wugFileName1 = TESTING_DIR + "2.txt";
		String wugText1 = "2";
		createFile(wugFileName1, wugText1);		
		
		gitlet("add", wugFileName);		
		String commitMessage2 = "added 1";
		gitlet("commit", commitMessage2);
		
		gitlet("branch","TA");		

		gitlet("add", wugFileName1);		
		String commitMessage3 = "added 2";
		gitlet("commit", commitMessage3);	
		
		gitlet("checkout", "TA");	
		
		writeFile(wugFileName, "1.5");
		
		gitlet("add", wugFileName);		
		String commitMessage4 = "added 1.5";
		gitlet("commit", commitMessage4);
		
		gitlet("rebase", "master");
		writeFile(wugFileName, "1");
		
		gitlet("checkout" ,wugFileName);
		assertEquals("1.5", getText(wugFileName));		
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
	 * Tests that merge will generate a .conflicted file if a file has been modified
	 * in both branches since the split point.
	 * 
	 * Includes
	 */
	@Test
	public void testMergeFile() {
		gitlet("init");
		File staging = new File(GITLET_DIR+"stagging/");
		File[] files = staging.listFiles();

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "Dis da wug. Wug wug wug wug wug!!!";
		String bugFileName = TESTING_DIR + "bug.txt";
		String bugText = "Dis da bug. Bug bug.";
		String nugFileName = TESTING_DIR + "nug.txt";
		String nugText = "Dis da nug. Nuggie Nuggie Nuggie.";

		String wugConflicted = TESTING_DIR + "wug.txt.conflicted";
		String bugConflicted = TESTING_DIR + "wug.txt.conflicted";
		String nugConflicted = TESTING_DIR + "wug.txt.conflicted";

		createFile(wugFileName, wugText);
		createFile(bugFileName, bugText);
		createFile(nugFileName, bugText);

		gitlet("add", wugFileName);
		gitlet("add", bugFileName);
		gitlet("add", nugFileName);
		gitlet("commit", "master branch");

		writeFile(wugFileName, "Dis da wug. Wugs are da gweatest!!!!");
		writeFile(bugFileName, "Dis da bug. Sigh. Bleah.");
		writeFile(nugFileName, "Dis da nug. Chicken nuggets are the best goddamn foods ever");
		gitlet("add", wugFileName);
		gitlet("add", bugFileName);
		gitlet("add", nugFileName);
		gitlet("commit", "split point");

		gitlet("branch", "wugbugnug");
		gitlet("checkout", "wugbugnug");

		writeFile(wugFileName, "Dis da wug. Wugs will wick yo wass!!!!");
		writeFile(bugFileName, "Dis da bug. Bug bug bug fug fug fug.");
		writeFile(nugFileName, "Dis da nug. Im nuggin tonight oooo yea");
		gitlet("add", wugFileName);
		gitlet("add", bugFileName);
		gitlet("add", nugFileName);
		gitlet("commit", "new branch");

		gitlet("checkout", "master");

		writeFile(wugFileName, "Dis da wug. Wugs are wuggin awesome!!!!");
		writeFile(bugFileName, "Dis da bug. Bug. I am sigh.");
		writeFile(nugFileName, "Dis da nug. Nug it nug it nug it ohhhh yeah");
		gitlet("add", wugFileName);
		gitlet("add", bugFileName);
		gitlet("add", nugFileName);
		gitlet("commit", "should create split");

		gitlet("merge", "wugbugnug");

		System.out.println("MERGE");
		File directory = new File(DIRECTORY+ File.separator+TESTING_DIR);
		System.out.println("COME ON BE TRUE: "+directory.exists());
		File[] files1 = directory.listFiles();
		System.out.println(files.length);
		assertTrue(new File(DIRECTORY + File.separator + wugConflicted).isFile());
		assertTrue(new File(DIRECTORY + File.separator + bugConflicted).isFile());
		assertTrue(new File(DIRECTORY + File.separator + nugConflicted).isFile());
	}

	/**
	 * Test that merge will commit with files from the other branch if those files had
	 * been modified in the other branch but not in the current branch since the split 
	 * point.
	 * 
	 * Includes
	 */
	@Test
	public void testMergeCommit() {
		gitlet("init");
		File staging = new File(GITLET_DIR+"stagging/");
		File[] files = staging.listFiles();

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "Dis da wug. Wug wug wug wug wug!!!";
		String bugFileName = TESTING_DIR + "bug.txt";
		String bugText = "Dis da bug. Bug bug.";
		String nugFileName = TESTING_DIR + "nug.txt";
		String nugText = "Dis da nug. Nuggie Nuggie Nuggie.";
		//maybe this is not how it's supposed to look like:
		String wugConflicted = TESTING_DIR + "wug.conflicted.txt";
		String bugConflicted = TESTING_DIR + "wug.conflicted.txt";
		String nugConflicted = TESTING_DIR + "wug.conflicted.txt";

		createFile(wugFileName, wugText);
		createFile(bugFileName, bugText);
		createFile(nugFileName, bugText);

		gitlet("add", wugFileName);
		gitlet("add", bugFileName);
		gitlet("add", nugFileName);
		gitlet("commit", "master branch");

		writeFile(wugFileName, "Dis da wug. Wugs are da gweatest!!!!");
		writeFile(bugFileName, "Dis da bug. Sigh. Bleah.");
		writeFile(nugFileName, "Dis da nug. Chicken nuggets are the best goddamn foods ever");
		gitlet("add", wugFileName);
		gitlet("add", bugFileName);
		gitlet("add", nugFileName);
		gitlet("commit", "split point");

		gitlet("branch", "wugbugnug");
		gitlet("checkout", "wugbugnug");

		writeFile(wugFileName, "Dis da wug. Wugs will wick yo wass!!!!");
		writeFile(bugFileName, "Dis da bug. Bug bug bug fug fug fug.");
		writeFile(nugFileName, "Dis da nug. Im nuggin tonight oooo yea");
		gitlet("add", wugFileName);
		gitlet("add", bugFileName);
		gitlet("add", nugFileName);
		gitlet("commit", "new branch");

		gitlet("checkout", "master");

		writeFile(wugFileName, "Dis da wug. Wugs are wuggin awesome!!!!");
		gitlet("add", wugFileName);
		gitlet("commit", "should create split");

		gitlet("merge", "wugbugnug");

		assertEquals(getText(GITLET_DIR+"commit/4/"+wugFileName), "Dis da wug. Wugs are wuggin awesome!!!!");	}

	/**
	 * Goes on to test that init creates a new commit, as well as to see
	 * if it will abort (rather than overwrite) if another gitlet version 
	 * control system is already the current directory or not. 
	 * 
	 * Involves init, add, and log.
	 */
	@Test
	public void testInit() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		String logContent = gitlet("log");

		assertArrayEquals(new String[] {commitMessage1},
				extractCommitMessages(logContent));

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "Dis da wug. Wug wug wug wug wug!!!";
		createFile(wugFileName, wugText);

		gitlet("add", wugFileName);
		gitlet("commit", "added wugwug");

		String output = gitlet("init");
		assertEquals(output, "A gitlet version control system already exists in "
				+ "the current directory.");
	}

	/**
	 * Tests that adding files will copy files to the staging area as well as unmarks
	 * files for untracking. Also tests for the case in which a file does not exist.
	 * 
	 * Involves init, add, rm, and log.
	 */
	@Test
	public void testBasicAdd() {
		gitlet("init");
		String commitMessage1 = "initial commit";
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "Dis da wug. Wug wug wug wug wug!!!";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		assertTrue(new File(GITLET_DIR+"stagging/"+wugFileName).isFile());
		gitlet("rm", wugFileName);
		assertFalse(new File(GITLET_DIR+"stagging/"+wugFileName).isFile());
		String output = gitlet("add", TESTING_DIR + "nonexistent.txt");
		assertEquals(output, "File does not exist.");
	}

	/**
	 * Tests commit for the following situations:
	 * - Default commit should be same as its parent
	 * - Basic commit with updated files should only update staged files, by
	 * 	 including the version of the file that was staged instead of its parent file.
	 *   Also make sure that commit will start tracking any files that were staged
	 *   but weren't tracked by parents.
	 * - Files that were marked for untracking mark after commit
	 * - Check that staging area is cleared after every commit
	 */
	@Test
	public void testCommit() {
		gitlet("init");
		File staging = new File(GITLET_DIR+"stagging/");
		File[] files = staging.listFiles();

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "Dis da wug. Wug wug wug wug wug!!!";
		createFile(wugFileName, wugText);

		String bugFileName = TESTING_DIR + "bug.txt";
		String bugText = "Dis da bug. Bug bug.";
		createFile(bugFileName, bugText);

		gitlet("add", wugFileName);
		gitlet("add", bugFileName);
		gitlet("commit", "wug.txt bug.txt commit");
		assertTrue(new File(GITLET_DIR+"commit/1/"+wugFileName).isFile());
		assertEquals(files.length, 0);

		String wugText1 = "More wugs!!! I wuve wugs.";
		writeFile(wugFileName, wugText1);
		gitlet("add", wugFileName);
		gitlet("commit", "changed wug.txt");
		assertEquals(getText(GITLET_DIR+"commit/2/"+wugFileName), wugText1);
		assertEquals(getText(wugFileName), wugText1);


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