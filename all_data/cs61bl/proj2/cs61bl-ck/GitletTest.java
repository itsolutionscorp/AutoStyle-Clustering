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
    public void testMergeZeroBranch() {
        String one = TESTING_DIR + "1.txt";
        String oneText = "1";
        createFile(one, oneText);
        String two = TESTING_DIR + "2.txt";
        String twoText = "2";
        createFile(two, twoText);
        String three = TESTING_DIR + "3.txt";
        String threeText = "3";
        createFile(three, threeText);
        gitlet("init");
        gitlet("add", two);
        gitlet("commit", "1");
        writeFile(two, "2 master");
        assertEquals("2 master", getText(two));
        gitlet("reset", "1");
        gitlet("merge", "master");
        assertEquals("2 master", getText(two));
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
		gitlet("branch", "k");
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));
		
		String oneFileName = TESTING_DIR + "one.txt";
        String oneText = "1";
        createFile(oneFileName, oneText);
        gitlet("add", oneFileName);
        String commitMessage3 = "added one";
        gitlet("commit", commitMessage3);
        String logContent1 = gitlet("log");
        assertArrayEquals(new String[] {commitMessage3, commitMessage2, commitMessage1 },
				extractCommitMessages(logContent1));
        
        gitlet("checkout", "k");
        String logContent2 = gitlet("log");
        assertArrayEquals(new String[] { commitMessage2, commitMessage1 },
                extractCommitMessages(logContent2));
        
        gitlet("checkout", "master");
        String logContent3 = gitlet("log");
        assertArrayEquals(new String[] { commitMessage3, commitMessage2, commitMessage1 },
                extractCommitMessages(logContent3));
	}
	
//	
//    @Test
//    public void testMergeOne() {
//        String one = TESTING_DIR + "1.txt";
//        String oneText = "1";
//        createFile(one, oneText);
//        String two = TESTING_DIR + "2.txt";
//        String twoText = "2";
//        createFile(two, twoText);
//        String three = TESTING_DIR + "3.txt";
//        String threeText = "3";
//        createFile(three, threeText);
//        String four = TESTING_DIR + "4.txt";
//        String fourText = "4";
//        createFile(four, fourText);
//        gitlet("init");
//        gitlet("add", one);
//        gitlet("commit", "1");
//        gitlet("branch", "PI");
//        gitlet("branch", "PII");
//        gitlet("checkout", "PI");
//        gitlet("rm", one);
//        gitlet("commit", "2");
//        gitlet("branch", "C");
//        gitlet("checkout", "C");
//        gitlet("add", two);
//        gitlet("commit", "3");
//        gitlet("checkout", "PII");
//        gitlet("add", three);
//        gitlet("commit", "4");
//        gitlet("branch", "Q");
//        gitlet("checkout", "Q");
//        gitlet("add", two);
//        gitlet("commit", "5");
//        gitlet("branch", "A");
//        gitlet("checkout", "A");
//        gitlet("add", four);
//        gitlet("commit", "6");
//        gitlet("checkout", "C");
//        writeFile(one, "asd");
//        writeFile(three, "wds");
//        writeFile(four, "wdw");
//        gitlet("merge", "A");
//
////        assertTrue(new File(two + ".conflicted").exists());
//        assertEquals(threeText, getText(three));
//        assertEquals(fourText, getText(four));
//        assertEquals("asd", getText(one));
//    }
    
    
//    @Test
//    public void testMergeTwo() {
//        String one = TESTING_DIR + "1.txt";
//        String oneText = "1";
//        createFile(one, oneText);
//        String two = TESTING_DIR + "2.txt";
//        String twoText = "2";
//        createFile(two, twoText);
//        String three = TESTING_DIR + "3.txt";
//        String threeText = "3";
//        createFile(three, threeText);
//        String four = TESTING_DIR + "4.txt";
//        String fourText = "4";
//        createFile(four, fourText);
//        gitlet("init");
//        gitlet("add", one);
//        gitlet("commit", "1");
//        gitlet("branch", "PI");
//        gitlet("branch", "PII");
//        gitlet("checkout", "PI");
//        writeFile(one, "wqwe");
//        gitlet("add", one);
//        gitlet("commit", "9");
//        gitlet("branch", "C");
//        gitlet("checkout", "C");
//        gitlet("rm", one);
//        gitlet("commit", "2");
//        gitlet("checkout", "PII");
//        gitlet("add", three);
//        gitlet("commit", "4");
//        gitlet("branch", "Q");
//        gitlet("checkout", "Q");
//        gitlet("add", two);
//        gitlet("commit", "5");
//        gitlet("branch", "A");
//        gitlet("checkout", "A");
//        gitlet("add", four);
//        gitlet("commit", "6");
//        writeFile(three, "wds");
//        writeFile(four, "wdw");
//        gitlet("merge", "C");
//        
//        assertEquals(oneText, getText(one));
//    }

//    @Test
//    public void mergeTest() {
//        gitlet("init");
//        String dogFile = TESTING_DIR + "dog.txt";
//        String dogText = "woof";
//        createFile(dogFile, dogText);
//        String catFile = TESTING_DIR + "cat.txt";
//        String catText = "meow";
//        createFile(catFile, catText);
//        String cowFile = TESTING_DIR + "cow.txt";
//        String cowText = "moo";
//        createFile(cowFile, cowText);
//        String mouseFile = TESTING_DIR + "mouse.txt";
//        String mouseText = "squeak";
//        createFile(mouseFile, mouseText);
//        gitlet("add", catFile);
//        gitlet("add", dogFile);
//        gitlet("add", mouseFile);
//        String commit1 = "Added cat,dog,mouse";
//        gitlet("commit", commit1);
//        gitlet("branch", "branch1");
//        gitlet("checkout", "branch1");
//        gitlet("add", cowFile);
//        String dogB1Text = "b1 dog";
//        writeFile(dogFile, dogB1Text);
//        String catB1Text = "b1 cat";
//        writeFile(catFile, catB1Text);
//        String name = gitlet("add", dogFile);
//        gitlet("add", catFile);
//        String mouseB1Text = "b1 mouse";
//        writeFile(mouseFile, mouseB1Text);
//        gitlet("add", mouseFile);
//        String b1Message = "Changed cat, dog, and added cow";
//        gitlet("commit", b1Message);
//        gitlet("checkout", "master");
//        assertEquals(dogText, getText(dogFile));
//        assertEquals(catText, getText(catFile));
//        assertEquals(mouseText, getText(mouseFile));
//        gitlet("rm", mouseFile);
//        String masterCatText = "master cat";
//        writeFile(catFile, masterCatText);
//        gitlet("add", catFile);
//        String masterCommit = "Changed cat and removed mouse";
//        gitlet("commit", masterCommit);
//        assertEquals(masterCatText, getText(catFile));
//        gitlet("merge", "branch1");
//       
//
//        assertEquals(masterCatText, getText(catFile));
//        assertEquals(dogB1Text, getText(dogFile));
//        assertEquals(mouseB1Text, getText(mouseFile));
//        assertEquals(cowText, getText(cowFile));
//
//    }
	

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