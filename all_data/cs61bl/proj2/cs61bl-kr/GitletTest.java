import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;


public class GitletTest {

	private static final String GITLET_DIR = ".gitlet/";
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
	@Test
	public void testBasicInitialize() {
		gitlet("init");
		File f = new File(GITLET_DIR);
		assertTrue(f.exists());
		File g = new File(GITLET_DIR + "commits/commit0");
		assertTrue(g.exists());
		
		gitlet("init");
		File h = new File(GITLET_DIR);
		assertTrue(h.exists());
		File i = new File(GITLET_DIR + "commits/commit0");
		assertTrue(i.exists());
	}
	
	
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
	
	@Test
	public void testLog() {
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
	public void testGlobalLog() {
		gitlet("init");
		
		gitlet("branch", "one");
		gitlet("add","b.txt");
		gitlet("commit", "commit1");
		writeFile("b.txt","bb");
		gitlet("add","b.txt");
		gitlet("checkout", "one");
		gitlet("commit", "commit2");
		gitlet("add","a.txt");
		gitlet("commit","commit3");
		gitlet("add","c.txt");
		writeFile("a.txt","aa");
		gitlet("add","a.txt");
		gitlet("commit","commit4");
		gitlet("add", "a.txt");
		gitlet("commit", "commit5");
		
		String glogContent = gitlet("global-log");

		assertArrayEquals(new String[] {"initial commit", "commit1","commit2","commit3","commit4","commit5"},
				extractCommitMessages(glogContent));
		}
	
		
		
	
	
	@Test
	public void testStatus() {
		gitlet("init");
		gitlet("branch", "firstCommit");
		gitlet("branch", "secondCommit");
		gitlet("branch", "thirdCommit");
		
		String statusContent = gitlet("status");
		
		String[] statusLines;
		statusLines = statusContent.split(LINE_SEPARATOR);
		
		System.out.println(statusLines[0]);
		
		assertEquals(statusLines[0],"=== Branches ===");
		assertEquals(statusLines[1], "firstCommit");
		assertEquals(statusLines[2], "secondCommit");
		assertEquals(statusLines[3], "*master");

		assertEquals(statusLines[4], "thirdCommit");
		
		gitlet("checkout", "firstCommit");
		createFile("a.txt","a");
		gitlet("add","a.txt");
		gitlet("commit","commit10");
		gitlet("checkout", "secondCommit");
		gitlet("rm-branch", "firstCommit");
		
		statusContent = gitlet("status");
		statusLines = statusContent.split(LINE_SEPARATOR);
		assertEquals(statusLines[0],"=== Branches ===");
		assertEquals(statusLines[1],"*secondCommit");
		assertEquals(statusLines[2],"master");
		assertEquals(statusLines[3],"thirdCommit");
	}
	
	@Test
	public void testReset() {
		gitlet("init");
		
		gitlet("add","b.txt");
		gitlet("commit", "commit1");
		writeFile("b.txt","bb");
		gitlet("add","b.txt");
		gitlet("commit", "commit2");
		gitlet("add","a.txt");
		gitlet("commit","commit3");
		gitlet("add","c.txt");
		writeFile("a.txt","aa");
		gitlet("add","a.txt");
		gitlet("commit","commit4");
		gitlet("add", "a.txt");
		gitlet("commit", "commit5");
		
		String logContent = gitlet("log");
		assertArrayEquals(new String[] {"commit5","commit4","commit3","commit2","commit1", "initial commit"},
				extractCommitMessages(logContent));
		
		gitlet("reset","2");
		
		String logTSTContent = gitlet("log");
		assertArrayEquals(new String[] {"commit2","commit1", "initial commit"},
				extractCommitMessages(logTSTContent));
	}
	
	@Test
	public void superTest() {
		gitlet("init");
		
		createFile("a.txt","a");
		createFile("b.txt","b");
		createFile("c.txt","c");
		
		gitlet("branch", "branchBottom");
		gitlet("branch", "branchTop");
		gitlet("branch", "branchMiddle");
		
		gitlet("checkout", "branchBottom");
		gitlet("add","b.txt");
		gitlet("commit", "commit1");
		
		writeFile("b.txt","bb");
		gitlet("checkout", "branchTop");
		gitlet("add","b.txt");
		gitlet("commit", "commit2");
		
		gitlet("checkout","branchMiddle");
		gitlet("add","a.txt");
		gitlet("commit","commit3");
		
		gitlet("branch","branchUnderMiddle");
		gitlet("add","c.txt");
		writeFile("a.txt","aa");
		gitlet("add","a.txt");
		gitlet("commit","commit4");
		
		gitlet("checkout","branchUnderMiddle");
		gitlet("add", "a.txt");
		gitlet("commit", "commit5");
		
		gitlet("merge","branchBottom");
		
		File h = new File(GITLET_DIR+ "commits/commit6/a.txt");
		assertTrue(h.exists());
		File i = new File(GITLET_DIR + "commits/commit6/b.txt");
		assertTrue(i.exists());
	
		gitlet("checkout","branchUnderMiddle");
		gitlet("merge","branchMiddle");
		
		File r = new File(GITLET_DIR+ "commits/commit7/b.txt");
		assertTrue(r.exists());
		File t = new File(GITLET_DIR+ "commits/commit7/c.txt");
		assertTrue(t.exists());
		File f = new File(GITLET_DIR+ "commits/commit7/a.txt");
		assertTrue(f.exists());
	
		assertEquals("aa",getText(GITLET_DIR+ "commits/commit7/a.txt"));
		assertEquals("b",getText(GITLET_DIR+ "commits/commit7/b.txt"));
		assertEquals("c",getText(GITLET_DIR+ "commits/commit7/c.txt"));
		assertEquals("aa",getText(f.getPath()));
		
		gitlet("checkout", "branchTop");
		gitlet("merge","branchBottom");
		
		gitlet("rebase", "master");
		gitlet("reset", "2");
		
	}
	
	@Test
	public void testRebase() {
		gitlet("init");
		createFile("a.txt","a");
		createFile("b.txt","b");
		createFile("c.txt","c");
		
		gitlet("branch", "branchBottom");
		gitlet("branch", "branchTop");
		gitlet("branch", "branchMiddle");
		
		gitlet("checkout", "branchBottom");
		gitlet("add","b.txt");
		gitlet("commit", "commit1");
		
		writeFile("b.txt","bb");
		gitlet("checkout", "branchTop");
		gitlet("add","b.txt");
		gitlet("commit", "commit2");
		
		gitlet("checkout","branchMiddle");
		gitlet("add","a.txt");
		gitlet("commit","commit3");
		
		gitlet("branch","branchUnderMiddle");
		gitlet("add","c.txt");
		writeFile("a.txt","aa");
		gitlet("add","a.txt");
		gitlet("commit","commit4");
		
		gitlet("checkout","branchUnderMiddle");
		gitlet("add", "a.txt");
		gitlet("commit", "commit5");
		
		gitlet("checkout", "branchBottom");
		gitlet("rebase","branchUnderMiddle");
		String findContent = gitlet("find", "commit1");
		String [] findLines = findContent.split(LINE_SEPARATOR);
		for(int j = 0; j < findLines.length; j++) {
			System.out.println(findLines[j]);
		}
		String logContent = gitlet("log");
		String [] logLines = logContent.split(LINE_SEPARATOR);
		for(int j = 0; j < logLines.length; j++) {
			System.out.println(logLines[j]);
		}
		assertEquals("1" + "\n"+ "6",gitlet("find","commit1"));
		
	}
	
	@Test
	public void testRemove() {
		gitlet("init");
		File f = new File("folder");
		f.mkdir(); 
		createFile("folder/a.txt","a");
		createFile("b.txt","b");
		createFile("c.txt","c");
		
		gitlet("add", "folder/a.txt");
		gitlet("rm", "folder/a.txt");
		File aliens = new File(GITLET_DIR + "staging-area/"+"folder/a.txt");
		assertTrue(!aliens.exists());
		gitlet("add", "folder/a.txt");
		gitlet("commit", "this");
		gitlet("add", "b.txt");
		gitlet("rm", "folder/a.txt");
		gitlet("commit","then");
		File ring = new File(GITLET_DIR + "commits/commit2" + "/folder/a.txt");
		assertTrue(!ring.exists());
		File marriage = new File(GITLET_DIR + "commits/commit2" + "/b.txt");
		assertTrue(marriage.exists());
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
	
	@Test
	public void testMerge2() {
		gitlet("init"); 
		
		createFile("a.txt","a");
		createFile("b.txt","b");
		createFile("c.txt","c");
		createFile("d.txt", "d");
		createFile("e.txt", "e"); 
		createFile("f.txt", "f");
		
		gitlet("add", "a.txt");
		gitlet("commit", "c1");
		gitlet("add", "b.txt");
		gitlet("commit", "c2");
		
		gitlet("branch", "one"); 
				
		gitlet("add", "c.txt");
		gitlet("commit", "c3");
		
		writeFile("b.txt", "bb");
		
		gitlet("add", "b.txt");
		gitlet("commit", "c4");
		
		gitlet("checkout", "one");
		
		gitlet("add", "d.txt"); 
		gitlet("commit", "c5");
		
		gitlet("add", "e.txt");
		gitlet("commit", "c6");
		
		gitlet("branch", "two");
		
		gitlet("add", "f.txt");
		gitlet("commit", "c7");
		
		gitlet("checkout", "two");
		
		writeFile("b.txt", "bbb");
		
		gitlet("add", "b.txt");
		gitlet("commit", "c8"); 
		
		writeFile("a.txt", "aa");
		
		gitlet("add", "a.txt");
		gitlet("commit", "c9");
		
		gitlet("merge", "master");
		
		gitlet("add", "b.txt");
		gitlet("commit", "c10"); 
		
		gitlet("checkout", "b.txt");
		
		assertEquals("bbb",getText("b.txt"));
		
		gitlet("checkout", "a.txt");
		
		assertEquals("aa", getText("a.txt"));
		
		gitlet("checkout", "c.txt");
		
		assertEquals("c", getText("c.txt"));
		
	}
	
	@Test
	public void testRebase2() {
		gitlet("init"); 
		
		createFile("a.txt","a");
		createFile("b.txt","b");
		createFile("c.txt","c");
		createFile("d.txt", "d");
		createFile("e.txt", "e"); 
		createFile("f.txt", "f");
		createFile("g.txt", "g");
		
		gitlet("add", "a.txt");
		gitlet("commit", "c1");
		gitlet("add", "b.txt");
		gitlet("commit", "c2");
		
		gitlet("branch", "one"); 
				
		gitlet("add", "c.txt");
		gitlet("commit", "c3");
				
		gitlet("add", "d.txt");
		gitlet("commit", "c4");
		
		gitlet("checkout", "one");
		
		gitlet("add", "e.txt"); 
		gitlet("commit", "c5");
		
		gitlet("add", "f.txt");
		gitlet("commit", "c6");
		
		gitlet("branch", "two");
		
		gitlet("add", "g.txt");
		gitlet("commit", "c7");
		
		gitlet("checkout", "two");
		
		writeFile("c.txt", "cc");
		
		gitlet("add", "c.txt");
		gitlet("commit", "c8"); 
		
		writeFile("d.txt", "dd");
		
		gitlet("add", "d.txt");
		gitlet("commit", "c9");
		
		gitlet("rebase", "master");
		
		gitlet("checkout", "11", "c.txt");
		assertEquals("c",getText("c.txt"));
		
		gitlet("checkout", "12", "c.txt");
		assertEquals("cc",getText("c.txt"));

		gitlet("checkout", "11", "d.txt");
		assertEquals("d", getText("d.txt"));
		
		gitlet("checkout", "13", "d.txt");
		assertEquals("dd", getText("d.txt"));
	}
	

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
	
	

}
