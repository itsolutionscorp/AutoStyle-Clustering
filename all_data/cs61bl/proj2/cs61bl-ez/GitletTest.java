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
 * @author Sam: EF, Frances: BQ, Alex: BR, Chris: EZ, and Joseph Moghadam
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
	 * Tests that log after one commit conforms to the format in the spec. // *
	 * Involves init, add, commit, and log. //
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

	/************************** SElF-TESTS: End to End *******************/

	/**
	 * Test that the printout of log after a rebase includes the commit messages
	 * from the branches involved in the rebase.
	 */
	@Test
	public void testLogRebase() {
		gitlet("init");

		// add file 1 and file 2 to commit 1
		String file2Name = TESTING_DIR + "file2.txt";
		String file2Text = "File 2 initialized!";
		createFile(file2Name, file2Text);
		gitlet("add", file2Name);

		String file1Name = TESTING_DIR + "file1.txt";
		String file1Text = "File 1 initialized!";
		createFile(file1Name, file1Text);
		gitlet("add", file1Name);
		gitlet("commit", "1");

		// add file 3 to commit 2 = split point
		String file3Name = TESTING_DIR + "file3.txt";
		String file3Text = "File 3 initialized!";
		createFile(file3Name, file3Text);
		gitlet("add", file3Name);
		gitlet("commit", "2");

		// create the first branch
		gitlet("branch", "branch1");

		// switch to branch1
		gitlet("checkout", "branch1");

		// add file a to branch 1
		String fileaName = TESTING_DIR + "filea.txt";
		String fileaText = "File a initialized!";
		createFile(fileaName, fileaText);
		gitlet("add", fileaName);
		gitlet("commit", "a");

		// add commit b to branch 1 and change file 2
		file2Text = "File 2 changed";
		createFile(file2Name, file2Text);
		gitlet("add", file2Name);
		gitlet("commit", "b");

		// create the second branch
		gitlet("branch", "branch2");
		gitlet("checkout", "branch2");

		// add file z to commit x
		String filezName = TESTING_DIR + "filez.txt";
		String filezText = "File z initialized!";
		createFile(filezName, filezText);
		gitlet("add", filezName);
		gitlet("commit", "x");

		// switch back to branch 1
		gitlet("checkout", "branch1");

		// create commit c
		String file4Name = TESTING_DIR + "file4.txt";
		String file4Text = "File 4 initialized in branch 1!";
		createFile(file4Name, file4Text);
		gitlet("add", file4Name);
		gitlet("commit", "c");

		// go back to the master branch
		gitlet("checkout", "master");

		// create commit 3
		file4Text = "File 4 initialized in master branch!";
		createFile(file4Name, file4Text);
		gitlet("add", file4Name);
		gitlet("commit", "3");

		// create commit 4
		String file5Name = TESTING_DIR + "file5.txt";
		String file5Text = "File 5 initialized!";
		createFile(file5Name, file5Text);
		gitlet("add", file5Name);
		gitlet("commit", "4");

		// rebase to master
		gitlet("checkout", "branch1");
		gitlet("rebase", "master");

		// print out the log
		System.out.println("The log should contain:");
		System.out
				.println("Commit c, Commit b, Commit a, Commit 3, Commit 2, Commit 1, Commit 0");
		gitlet("log");

	}

		/**
	 * Tests the rebase method. Makes sure it copies over correctly and new
	 * commits are created successfully. Also tests that changes in the base
	 * branch propagate through the replayed branch during a rebase.
	 */
	@Test
	public void testRebase() {
		gitlet("init");

		// add file 1 and file 2 to commit 1
		String file2Name = TESTING_DIR + "file2.txt";
		String file2Text = "File 2 initialized!";
		createFile(file2Name, file2Text);
		gitlet("add", file2Name);

		String file1Name = TESTING_DIR + "file1.txt";
		String file1Text = "File 1 initialized!";
		createFile(file1Name, file1Text);
		gitlet("add", file1Name);
		gitlet("commit", "1");

		// add file 3 to commit 2 = split point
		String file3Name = TESTING_DIR + "file3.txt";
		String file3Text = "File 3 initialized!";
		createFile(file3Name, file3Text);
		gitlet("add", file3Name);
		gitlet("commit", "2");

		// create the first branch
		gitlet("branch", "branch1");

		// switch to branch1
		gitlet("checkout", "branch1");

		// add file a to branch 1
		String fileaName = TESTING_DIR + "filea.txt";
		String fileaText = "File a initialized!";
		createFile(fileaName, fileaText);
		gitlet("add", fileaName);
		gitlet("commit", "a");

		// add commit b to branch 1 and change file 2
		file2Text = "File 2 changed";
		createFile(file2Name, file2Text);
		gitlet("add", file2Name);
		gitlet("commit", "b");

		// create the second branch
		gitlet("branch", "branch2");
		gitlet("checkout", "branch2");

		// add file z to commit x
		String filezName = TESTING_DIR + "filez.txt";
		String filezText = "File z initialized!";
		createFile(filezName, filezText);
		gitlet("add", filezName);
		gitlet("commit", "x");

		// switch back to branch 1
		gitlet("checkout", "branch1");

		// create commit c
		String file4Name = TESTING_DIR + "file4.txt";
		String file4Text = "File 4 initialized in branch 1!";
		createFile(file4Name, file4Text);
		gitlet("add", file4Name);
		gitlet("commit", "c");

		// go back to the master branch
		gitlet("checkout", "master");

		// create commit 3
		file4Text = "File 4 initialized in master branch!";
		createFile(file4Name, file4Text);
		gitlet("add", file4Name);
		gitlet("commit", "3");

		// create commit 4
		String file5Name = TESTING_DIR + "file5.txt";
		String file5Text = "File 5 initialized!";
		createFile(file5Name, file5Text);
		gitlet("add", file5Name);
		gitlet("commit", "4");

		// rebase to master
		gitlet("checkout", "branch1");
		gitlet("rebase", "master");

		// check if commit 3 has the appropriate files
		gitlet("checkout", "7", file4Name);
		assertEquals("File 4 initialized in master branch!", getText(file4Name));

		// check if commit 4 has the appropriate files
		gitlet("checkout", "8", file5Name);
		assertEquals("File 5 initialized!", getText(file5Name));

		// check the replayed commit a which was created from the copy
		gitlet("checkout", "9", fileaName);
		gitlet("checkout", "branch1");

		gitlet("global-log");
		assertEquals("File a initialized!", getText(fileaName));

		gitlet("checkout", "11", file4Name);
		assertEquals("File 4 initialized in master branch!", getText(file4Name));

		gitlet("checkout", "11", file5Name);
		assertEquals("File 5 initialized!", getText(file5Name));

		// check the replayed commit b
		gitlet("checkout", "10", file2Name);
		assertEquals("File 2 changed", getText(file2Name));

		gitlet("checkout", "10", file4Name);
		assertEquals("File 4 initialized in master branch!", getText(file4Name));

		gitlet("checkout", "10", file5Name);
		assertEquals("File 5 initialized!", getText(file5Name));

		// check file x that wasn't copied
		gitlet("checkout", "5", filezName);
		assertEquals("File z initialized!", getText(filezName));

		// check replayed commit c
		gitlet("checkout", "9", file4Name);
		assertEquals("File 4 initialized in branch 1!", getText(file4Name));
	}

	/**
	 * Test that a file that has been committed at some point can be restored by
	 * checking it out from the commit it was committed at.
	 */
	@Test
	public void testCommit() {

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");

		writeFile(wugFileName, "This is not a wug.");
		gitlet("add", wugFileName);
		gitlet("commit", "changed wug");

		gitlet("checkout", "1", wugFileName);
		assertEquals(wugText, getText(wugFileName));

	}

	/**
	 * Test that a file that has been committed at some point can be restored by
	 * checking it out from a commit that tracks that version of the file, even
	 * if the file wasn't staged prior to that commit.
	 */
	@Test
	public void testCommit1() {

		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");

		String lugFilename = TESTING_DIR + "lug.txt";
		String lugText = "This is a lug";
		createFile(lugFilename, lugText);
		gitlet("add", lugFilename);
		gitlet("commit", "added lug");

		gitlet("checkout", "2", wugFileName);
		assertEquals(wugText, getText(wugFileName));

	}

	/**
	 * Test that you can checkout [id] [file] from an arbitrary commit in the
	 * graph (even one in another branch).
	 */
	@Test
	public void testCheckout() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug"); // commit 1

		String lugFilename = TESTING_DIR + "lug.txt";
		String lugText = "This is a lug";
		createFile(lugFilename, lugText);
		gitlet("add", lugFilename);
		gitlet("commit", "added lug"); // commit 2

		// made 2 commits so far on the same branch
		gitlet("branch", "branchONE");
		gitlet("checkout", "branchONE");
		writeFile(wugFileName, "This wug has seen the BranchONE light.");
		// rewrote Wug on batman

		gitlet("add", wugFileName);
		gitlet("commit", "wug commit on branch one");// commit 3

		gitlet("checkout", "master"); // back to master
		gitlet("commit", "changed nothing"); // commit 4

		gitlet("checkout", "3", wugFileName); // getting something from
		// BranchONE, while in master
		assertEquals("This wug has seen the BranchONE light.",
				getText(wugFileName));
	}

	/**
	 * Test that resetting backward appropriately changes the output of log.
	 */
	@Test
	public void testLogReset() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug"); // commit 1

		String lugFilename = TESTING_DIR + "lug.txt";
		String lugText = "This is a lug";
		createFile(lugFilename, lugText);
		gitlet("add", lugFilename);
		gitlet("commit", "added lug!"); // commit 2

		String pugFilename = TESTING_DIR + "pug.txt";
		String pugText = "This is a pug";
		createFile(lugFilename, pugText);
		gitlet("add", pugFilename);
		gitlet("commit", "added pug!"); // commit 3

		gitlet("reset", "2");
		// log should only print 0,1,2
		gitlet("log");

	}

	/**
	 * Test that log adjusts appropriately when switching from one branch to
	 * another
	 */
	@Test
	public void testLogBranchSwtich() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug"); // commit 1

		String lugFilename = TESTING_DIR + "lug.txt";
		String lugText = "This is a lug";
		createFile(lugFilename, lugText);
		gitlet("add", lugFilename);
		gitlet("commit", "added lug"); // commit 2

		// made 2 commits so far on the same branch
		gitlet("branch", "branchONE");
		gitlet("checkout", "branchONE");
		writeFile(wugFileName, "This wug has seen the BranchONE light.");
		// rewrote Wug on batman

		gitlet("add", wugFileName);
		gitlet("commit", "wug commit on branch one");// commit 3

		gitlet("checkout", "master"); // back to master
		gitlet("commit", "changed nothing"); // commit 4

		System.out.println("Should print: 0 1 2 4");
		gitlet("log"); // should print 0,1,2,4 (works)

	}
	

	/**
	 * Test that merge will generate a .conflicted file if a file has been
	 * modified in both branches since the split point.
	 */
	public void testMerge() {
		// add file 1 and file 2 to commit 1
		String file2Name = TESTING_DIR + "file2.txt";
		String file2Text = "File 2 initialized!";
		createFile(file2Name, file2Text);
		gitlet("add", file2Name);

		String file1Name = TESTING_DIR + "file1.txt";
		String file1Text = "File 1 initialized!";
		createFile(file1Name, file1Text);
		gitlet("add", file1Name);
		gitlet("commit", "1");

		// create the first branch
		gitlet("branch", "branch1");

		// switch to branch1
		gitlet("checkout", "branch1");

		// add file a to branch 1
		file1Text = "File 1 is changed in branch1";
		createFile(file1Name, file1Text);
		gitlet("add", file1Name);
		gitlet("commit", "a");

		// add commit b to branch 1 and change file 2
		file2Text = "File 2 changed in branch1";
		createFile(file2Name, file2Text);
		gitlet("add", file2Name);
		gitlet("commit", "b");

		// switch back to master
		gitlet("checkout", "master");

		// create commit 3
		file2Text = "File 2 changed in master branch!";
		createFile(file2Name, file2Text);
		gitlet("add", file2Name);
		gitlet("commit", "2");

		// create commit 4
		String file5Name = TESTING_DIR + "file5.txt";
		String file5Text = "File 5 initialized!";
		createFile(file5Name, file5Text);
		gitlet("add", file5Name);
		gitlet("commit", "4");

		gitlet("merge", "branch1");

		// check to see if there is a .conflicted file in the working directory
		File f = new File(TESTING_DIR + "file2.conflictd");
		assertTrue(f.exists());
	}

	/**
	 * Test that merge will commit with files from the other branch if those
	 * files had been modified in the other branch but not in the current branch
	 * since the split point.
	 */
	@Test
	public void testMerge2() {
		// add file 1 and file 2 to commit 1
		String file2Name = TESTING_DIR + "file2.txt";
		String file2Text = "File 2 initialized!";
		createFile(file2Name, file2Text);
		gitlet("add", file2Name);

		String file1Name = TESTING_DIR + "file1.txt";
		String file1Text = "File 1 initialized!";
		createFile(file1Name, file1Text);
		gitlet("add", file1Name);
		gitlet("commit", "1");

		// create the first branch
		gitlet("branch", "branch1");

		// switch to branch1
		gitlet("checkout", "branch1");

		// add file a to branch 1
		file1Text = "File 1 is changed in branch1";
		createFile(file1Name, file1Text);
		gitlet("add", file1Name);
		gitlet("commit", "a");

		// add commit b to branch 1 and change file 2
		file2Text = "File 2 changed in branch1";
		createFile(file2Name, file2Text);
		gitlet("add", file2Name);
		gitlet("commit", "b");

		// switch back to master
		gitlet("checkout", "master");

		// create commit 3
		file2Text = "File 2 changed in master branch!";
		createFile(file2Name, file2Text);
		gitlet("add", file2Name);
		gitlet("commit", "2");

		// create commit 4
		String file5Name = TESTING_DIR + "file5.txt";
		String file5Text = "File 5 initialized!";
		createFile(file5Name, file5Text);
		gitlet("add", file5Name);
		gitlet("commit", "4");

		gitlet("merge", "branch1");

		// after the merge, file1.txt should contain a different message

		// check the commit out into the working directory to get the message
		gitlet("checkout", "4", file1Name);
		assertEquals("File 1 is changed in branch1", getText(file1Name));
	}

	/************************ SElF-TESTS: Unit-exceptions ***************************************/
	/**
	 * Let's throw some exceptions boys!
	 */
	
	/**
	 * Throw exceptions for the init method
	 */
	 public void testInitExceptions() {
	 gitlet("init");
	 System.out.println("should print the following in red letters");
	 System.out.println("A gitlet version control system already exists in the current directory.");
	 gitlet("init");
	 }
	
	 /**
	  * Throw exceptions for the add method
	  */
	 public void testAddExceptions() {
	 gitlet("init");
	 gitlet("add", "Blah");
	 System.out.println("should print the following in red letters");
	 System.out.println("File does not exist.");
	 }
	
	 public void testCommitError1() {
		 gitlet("init");
		 gitlet("add", "Blah");
		 gitlet("commit", "commit1");
		 
		 System.out.println("should print the following in red letters");
		 System.out.println("No changes added to the commit.");
		 gitlet("commit", "commit2");
	 }
	 
	 public void testCommitError2() {
		 gitlet("init");
		 gitlet("add", "Blah");
		 gitlet("commit", "");
		 System.out.println("should print the following in red letters");
		 System.out.println("Please enter a commit message.");
	 }
	 
	 public void testRMError() {
		 gitlet("init");
		 System.out.println("should print the following in red letters");
		 System.out.println("No reason to remove the file.");
		 gitlet("rm", "Blah");
	 }
	
	 
	 public void testFind() {
		 gitlet("init");
		 System.out.println("should print the following in red letters");
		 System.out.println("Found no commit with that message.");
		 gitlet("find", "commit message N/A");
	 }
	 
	 public void testCheckout1() {
		 gitlet("init");
		 gitlet("add", "Blah");
		 gitlet("commit", "commit1");
		 
		 System.out.println("should print the following in red letters");
		 System.out.println("File does not exist in the most recent commit, or no such branch exists.");
		 gitlet("checkout","File NA");
	 }
	 public void testCheckout2() {
		 gitlet("init");
		 gitlet("add", "Blah");
		 gitlet("commit", "commit1");
		 
		 System.out.println("should print the following");
		 System.out.println("No commit with that id exists.");
		 gitlet("checkout","5","Blah");
	 }
	 public void testCheckout3() {
		 gitlet("init");
		 gitlet("add", "Blah");
		 gitlet("commit", "commit1");
		 
		 System.out.println("should print the following");
		 System.out.println("File does not exist in that commit.");
		 gitlet("checkout","5","File MIA");
	 }
	 public void testCheckout4() {
		 gitlet("init");
		 gitlet("add", "Blah");
		 gitlet("commit", "commit1");
		 
		 System.out.println("should print the following");
		 System.out.println("File does not exist in the most recent commit, or no such branch exists.");
		 gitlet("checkout","daLostBranch");
		 System.out.println("_______________________________________________________");
		 
		 System.out.println("should print the following");
		 System.out.println("No need to checkout the current branch.");
		 gitlet("checkout","master");
	 }
	 
	 public void testrmBranchExceptions() {
		 gitlet("init");
		 gitlet("add", "Blah");
		 gitlet("commit", "commit1");
		 System.out.println("should print the following in red letters");
		 System.out.println("A branch with that name does not exist.");
		 gitlet("rm-branch", "MIA BRANCH");
	 }
	 public void testrmBranchExceptions2() {
		 gitlet("init");
		 gitlet("add", "Blah");
		 gitlet("commit", "commit1");
		 System.out.println("should print the following in red letters");
		 System.out.println("Cannot remove the current branch.");
		 gitlet("rm-branch", "master");
	 }
	 
	 public void testResetException(){
		 gitlet("init");
		 System.out.println("should print the following in red letters");
		 System.out.println("No commit with that id exists.");
		 // this might screw everything up...
		 gitlet("reset","*");
	 }
	 
	 public void testMergeException() {
		 gitlet("init");
		 System.out.println("should print the following in red letters");
		 System.out.println("A branch with that name does not exist.");
		 gitlet("merge","Branch MIA");
	 }
	 public void testMergeException2(){
		 gitlet("init");
		 System.out.println("should print the following in red letters");
		 System.out.println("Cannot merge a branch with itself.");
		 gitlet("merge","master");
	 }
	 public void testRebaseException() {
		 gitlet("init");
		 System.out.println("should print the following in red letters");
		 System.out.println("A branch with that name does not exist.");
		 gitlet("rebase","Branch MIA");
	 }
	 public void testRebaseException2() {
		 gitlet("init");
		 System.out.println("should print the following in red letters");
		 System.out.println("Cannot rebase a branch onto itself.");
		 gitlet("rebase","master");
	 }
	 public void testRebaseException3() {
		 gitlet("init");
		 gitlet("add", "Blah");
		 gitlet("commit", "commit1");
		 
		 gitlet("branch", "branch1");
		 gitlet("checkout", "master");
		 
		 gitlet("add", "happy!");
		 gitlet("commit","commit2");
		 
		 System.out.println("should print the following in red letters");
		 System.out.println("Already up-to-date.");
		 gitlet("rm-branch", "branch1");
	 }

}