import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import sun.misc.IOUtils;

public class GitletTest {
	private static final String GITLET_DIR = "gitlet/";
	/************* Change later to.gitle/ *****************/
	private static final String TESTING_DIR = "test_files/";

	/* matches either unix/mac or windows line separators */
	private static final String LINE_SEPARATOR = "\r\n|[\r\n]";

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
	 * Test that merge will generate a .conflicted file if a file has been
	 * modified in both branches since the split point.
	 */
	@Test
	// //////////
	public void testMergeGeneratesConflictedFile() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "modified wug0");
		writeFile(wugFileName, "This is not not a wug.");
		gitlet("commit", "modified wug1");

		gitlet("branch", "cool-beans");
		gitlet("checkout", "cool-beans");
		writeFile(wugFileName, "This is not not not a wug.");
		gitlet("commit", "modified wug2");

		gitlet("checkout", "master");
		writeFile(wugFileName, "This is not not a wug!");
		gitlet("commit", "modified wug1!");
		writeFile(wugFileName, "This is not not a wug!!");

		gitlet("checkout", "cool-beans");
		writeFile(wugFileName, "This is not not not not a wug.");

		gitlet("merge", "master");
		File f = new File(wugFileName + ".conflicted");
		assertTrue(f.exists());
	}

	/**
	 * Test that merge will commit with files from the other branch if those
	 * files had been modified in the other branch but not in the current branch
	 * since the split point.
	 */
	@Test
	// //////////
	public void testMergeWilCommitWithFilesFromOtherBranch() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");

		gitlet("add", wugFileName);
		gitlet("commit", "added wug");// first commit

		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "modified wug0");// second commit

		writeFile(wugFileName, "This is not not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "modified wug1");// third commit

		gitlet("branch", "cool-beans");// make a branch "cool-beans"

		gitlet("checkout", "cool-beans");
		writeFile(wugFileName, "This is not not not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "modified wug2");// fourth commit

		gitlet("checkout", "master");
		writeFile(wugFileName, "This is not not a wug!");
		gitlet("commit", "modified wug1!");// Split and commit

		writeFile(wugFileName, "This is not not a wug!!");// Edit master branch

		gitlet("checkout", "cool-beans");
		gitlet("merge", "master");

		gitlet("checkout", "wugFileName");

		assertEquals(getText(wugFileName), "This is not not a wug!!");
	}

	/**
	 * Test that the output of log after a rebase includes the commit messages
	 * from both branches involved in the rebase.
	 */
	@Test
	// //////////
	public void testOutputOfLogAfterRebase() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");

		gitlet("add", wugFileName);
		gitlet("commit", "added wug");// first commit

		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "modified wug0");// second commit

		writeFile(wugFileName, "This is not not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "modified wug1");// third commit

		gitlet("branch", "cool-beans");// make a branch "cool-beans"

		gitlet("checkout", "cool-beans");
		writeFile(wugFileName, "This is not not not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "modified wug2");// fourth commit

		gitlet("checkout", "master");
		writeFile(wugFileName, "This is not not a wug!");
		gitlet("add", wugFileName);
		gitlet("commit", "modified wug1!");// Split and commit

		gitlet("rebase", "cool-beans");

		String logContent = gitlet("log");
		assertArrayEquals(new String[] { "added wug", "modified wug0",
				"modified wug2", "modified wug1!" },
				extractCommitMessages(logContent));
	}

	/**
	 * Test that changes in the base branch propagate through the replayed
	 * branch during a rebase.
	 */
	@Test
	// //////////
	public void testChangesInTheBaseBranchPropagate() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");

		gitlet("add", wugFileName);
		gitlet("commit", "added wug");// first commit

		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "modified wug0");// second commit

		writeFile(wugFileName, "This is not not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "modified wug1");// third commit

		gitlet("branch", "cool-beans");// make a branch "cool-beans"

		gitlet("checkout", "cool-beans");
		writeFile(wugFileName, "This is not not not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "modified wug2");// fourth commit

		gitlet("checkout", "master");
		writeFile(wugFileName, "This is not not a wug!");
		gitlet("add", wugFileName);
		gitlet("commit", "modified wug1!");// Split and commit

		writeFile(wugFileName, "This is not not a wug!!");// change in the base
															// branch

		gitlet("rebase", "cool-beans");
		gitlet("commit", "modified wug2!");

		gitlet("checkout", "4 wugFileName");
		assertEquals(getText("wugFileName"), "This is not not a wug!!");
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

/*
 * 
 * OTHER
 * 
 * 
 * 
 * 
 * @Test
	public void test() {

		Gitlet gitTest = new Gitlet();
		gitTest.init();
		
		System.out.println("**First add");
		gitTest.add("test.rtf");
		System.out.println("commits" + gitTest.commits);
		System.out.println("branches" + gitTest.branches);
		System.out.println("tracking" + gitTest.tempTracking);
		System.out.println("staged" + gitTest.staged);
		System.out.println("");
		
		System.out.println("**STAtUS (added test.rtf for staging)");
		gitTest.status();
		System.out.println("");
		
		gitTest.rm("test.rtf");
		System.out.println("**STAtUS (removed test.rtf from staging)");
		gitTest.status();
		System.out.println("");
		
		gitTest.rm("test.rtf");
		System.out.println("**STAtUS (removed test.rtf -- should not do anything)");
		gitTest.status();
		System.out.println("");
		
		
		gitTest.add("test.rtf");
		System.out.println("**STAtUS (added test.rtf for staging)");
		gitTest.status();
		System.out.println("");		
		
		System.out.println("commits" + gitTest.commits);
		System.out.println("branches" + gitTest.branches);
		System.out.println("tracking" + gitTest.tempTracking);
		System.out.println("staged" + gitTest.staged);
		System.out.println("");
	
		
		System.out.println("**Current head commits instance variables");
		System.out.println(gitTest.head.getID());
		System.out.println(gitTest.head.getFolder());
		System.out.println(gitTest.head.getMessage());
		System.out.println(gitTest.head.getTime());
		System.out.println(gitTest.head.getNext());
		System.out.println(gitTest.head.getPrev());
		System.out.println("");
		
		System.out.println("**STAtUS");
		gitTest.status();
		System.out.println("");
		
		System.out.println("**First commit");
		gitTest.commit("first commit");
		System.out.println("commits" + gitTest.commits.get(1));
		System.out.println("branches" + gitTest.branches);
		System.out.println("tracking" + gitTest.tempTracking);
		System.out.println("staged" + gitTest.staged);
		
		System.out.println("Untracking test");
		gitTest.rm("test.rtf");
		gitTest.commit("2nd commit");
		gitTest.log();
		System.out.println(gitTest.commits.get(2).tracking);
		System.out.println("reTracking test should not stage");
		gitTest.commit("3rd commit");
		gitTest.add("test.rtf");
		gitTest.status();
		gitTest.commit("3rd commit");
		gitTest.log();
		gitTest.add("test.rtf");
		gitTest.status();
		gitTest.commit("4th commit");
		gitTest.status();
		gitTest.log();
//		
//		System.out.println("**STAtUS (DIEEEEE");
//		gitTest.add("test.rtf");
//		gitTest.status();
//		gitTest.rm("test.rtf");
//		System.out.println("**STAtUS (removed test.rtf from staging)");
//		gitTest.status();
//		System.out.println("");
//		gitTest.rm("test.rtf");
//		System.out.println("**STAtUS (removed test.rtf from tracking)");
//		gitTest.status();
//		System.out.println("");
//		
//		gitTest.branch("hi armani");
//		gitTest.branch("hi armani");
//		
//		System.out.println("**LOG");
//		gitTest.log();
//		System.out.println("**GLOBALLOG");
//		gitTest.globalLog();
//		System.out.println("**STAtUS");
//		gitTest.status();
//		System.out.println("**FIND");
//		gitTest.find("first commit");
//		
//		gitTest.rm_branch("hi armani");
//		System.out.println("**STAtUS");
//		gitTest.status();
//		
//		
//		System.out.println("REPEAT");
//		
//		System.out.println("**First add");
//		gitTest.add("test.rtf");
//		System.out.println("commits" + gitTest.commits);
//		System.out.println("branches" + gitTest.branches);
//		System.out.println("tracking" + gitTest.tempTracking);
//		System.out.println("staged" + gitTest.staged);
//		System.out.println("");
//		
//		System.out.println("**STAtUS (added test.rtf for staging)");
//		gitTest.status();
//		System.out.println("");
//		
//		gitTest.rm("test.rtf");
//		System.out.println("**STAtUS (removed test.rtf from staging)");
//		gitTest.status();
//		System.out.println("");
		
//		gitTest.rm("test.rtf");
//		System.out.println("**STAtUS (removed test.rtf -- should not do anything)");
//		gitTest.status();
//		System.out.println("");
//		
//		
//		gitTest.add("test.rtf");
//		System.out.println("**STAtUS (added test.rtf for staging)");
//		gitTest.status();
//		System.out.println("");		
//		
//		System.out.println("commits" + gitTest.commits);
//		System.out.println("branches" + gitTest.branches);
//		System.out.println("tracking" + gitTest.tempTracking);
//		System.out.println("staged" + gitTest.staged);
//		System.out.println("");
//	
//		
//		System.out.println("**Current head commits instance variables");
//		System.out.println(gitTest.head.getID());
//		System.out.println(gitTest.head.getFolder());
//		System.out.println(gitTest.head.getMessage());
//		System.out.println(gitTest.head.getTime());
//		System.out.println(gitTest.head.getNext());
//		System.out.println(gitTest.head.getPrev());
//		System.out.println("");
//		
//		System.out.println("**STAtUS");
//		gitTest.status();
//		System.out.println("");
//		
//		System.out.println("**First commit");
//		gitTest.commit("first commit");
//		System.out.println("commits" + gitTest.commits.get(1));
//		System.out.println("branches" + gitTest.branches);
//		System.out.println("tracking" + gitTest.tempTracking);
//		System.out.println("staged" + gitTest.staged);
//		
//		System.out.println("**STAtUS (DIEEEEE");
//		gitTest.add("test.rtf");
//		gitTest.status();
//		gitTest.rm("test.rtf");
//		System.out.println("**STAtUS (removed test.rtf from staging)");
//		gitTest.status();
//		System.out.println("");
//		gitTest.rm("test.rtf");
//		System.out.println("**STAtUS (removed test.rtf from tracking)");
//		gitTest.status();
//		System.out.println("");
//		
//		gitTest.branch("hi armani");
//		gitTest.branch("hi armani");
//		gitTest.branch("hi armani2");
//		gitTest.branch("hi armani3");
//		gitTest.branch("hi armani4");
//		
//		System.out.println("**LOG");
//		gitTest.log();
//		System.out.println("**GLOBALLOG");
//		gitTest.globalLog();
//		System.out.println("**STAtUS");
//		gitTest.status();
//		System.out.println("**FIND");
//		gitTest.find("first commit");
//		
//		gitTest.rm_branch("hi armani");
//		System.out.println("**STAtUS");
//		gitTest.status();
	}
	
	*/
