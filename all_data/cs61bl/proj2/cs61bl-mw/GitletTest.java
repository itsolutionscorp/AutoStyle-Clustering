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
	private static final String STAGINGPATH = ".gitlet/stage";
	private static final String COMMITPATH = ".gitlet/commit";

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
		String RepeatInit = gitlet("init");
		String FailureCaseMessage = "A gitlet version control system already exists in the current directory.";
		assertEquals(FailureCaseMessage,RepeatInit);
		
	}
	/**
	 * Tests that add a file into stage area
	 * If the file exist, the add method will add this file into stage area
	 * otherwise, return a error message
	 */
	@Test
	public void testBasicAdd(){
		String letterFileName = TESTING_DIR + "a.txt";
		String aText = "This is a letter <a>.";
		createFile(letterFileName, aText);
		gitlet("init");
		gitlet("add",letterFileName);
		String Staging = gitlet("status");
		String ReturnedMessage = "=== Branches ===\n*master\n\n=== Staged Files ===\n" + letterFileName + "\n\n=== Files Marked for Untracking ===";
		assertEquals(Staging,ReturnedMessage);
		String AddEmptyFile = gitlet("add", "hello");
		String FailureCaseMessage = "File does not exist.";
		assertEquals(FailureCaseMessage,AddEmptyFile);	
	}
	/**
	 * 1) Test two Failure Cases
	 */
	@Test
	public void testCommit(){
		String letterFileName = TESTING_DIR + "a.txt";
		String aText = "This is a letter <a>.";
		createFile(letterFileName, aText);
		File f = new File(STAGINGPATH +"/" +letterFileName );
		File g = new File(COMMITPATH +"/"+"1/" +letterFileName );
		gitlet("init");
		String commitMessage1 = "initial commit";
		String NoChangeCommitContent = gitlet("commit", "added hello");
		String FailureCaseMessage1 = "No changes added to the commit.";
		assertEquals(FailureCaseMessage1,NoChangeCommitContent);
		String NoMessageCommitContent = gitlet("commit");
		String FailureCaseMessage2 = "Please enter a commit message.";
		assertEquals(FailureCaseMessage2,NoMessageCommitContent);
		gitlet("add", letterFileName);
		assertTrue(f.exists());
		gitlet("commit", "added letterA");
		assertTrue(!f.exists());
		String commitMessage2 = "added letterA";
		assertTrue(g.exists());
		String logContent = gitlet("log");
		assertArrayEquals(new String[] { commitMessage2, commitMessage1 },
				extractCommitMessages(logContent));	

	}
	/**
	 * Test remove 
	 * one failure case
	 */
	@Test
	public void testRemove(){
		String letterA = TESTING_DIR + "a.txt";
		String AText = "This is a letter A.";
		createFile(letterA, AText);
		String letterB = TESTING_DIR + "b.txt";
		String BText = "This is a letter B.";
		createFile(letterB, BText);
		String letterC = TESTING_DIR + "c.txt";
		String CText = "This is a letter C.";
		createFile(letterC, CText);
		
		
		gitlet("init");
		String NoReasonRMContent = gitlet("rm",letterA);
		String FailureCaseMessage = "No reason to remove the file.";
		assertEquals(FailureCaseMessage,NoReasonRMContent);
		File f = new File(STAGINGPATH +"/" +letterA );
		File g = new File(STAGINGPATH +"/" +letterB );
		gitlet("add", letterA);
		assertTrue(f.exists());
		gitlet("add", letterB);
		assertTrue(g.exists());
		gitlet("rm",letterA);
		assertTrue(!f.exists());
		gitlet("commit", "added letters");
		gitlet("rm", letterB);
		gitlet("add", letterC);
		String StatusContent = gitlet("status");
		String ReturnMessage = "=== Branches ===\n*master\n\n=== Staged Files ===\n"+letterC+"\n\n=== Files Marked for Untracking ===\n"+letterB;
		assertEquals(ReturnMessage,StatusContent);	
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
	 * test creating a new branch
	 * Failure Case - if branch already exist return error message;
	 * Normal Case - Create a new branch, show on the status table;
	 */
	@Test
	public void testBasicBranch(){
		gitlet("init");
		gitlet("branch","Aplan");
		String BranchContent = gitlet("branch","Aplan");
		String ReturnMessage1 = "A branch with that name already exists.";
		assertEquals(BranchContent, ReturnMessage1);
		String statusContent = gitlet("status");
		String ReturnMessage2 = "=== Branches ===\n*master\nAplan\n\n=== Staged Files ===\n\n=== Files Marked for Untracking ===";
		assertEquals(ReturnMessage2,statusContent);
	}
	
	
	/**
	 * test checkout Branch
	 */
	@Test
	public void testCheckoutBranch(){
		String Aplan = "Aplan.txt";
		String AText = "This is a letter A.";
		createFile(Aplan, AText);
		gitlet("init");
		gitlet("branch","Aplan.txt");
		gitlet("branch","Bplan");
		String CkeckBranchConktent = gitlet("checkout","hello");
		String ReturnMessage1 = "File does not exist in the most recent commit, or no such branch exists.";
		assertEquals(CkeckBranchConktent,ReturnMessage1);
		gitlet("add",Aplan);
		gitlet("checkout","Aplan.txt");
		String StatusContent = gitlet("status");
		String ReturnMessage2 = "=== Branches ===\n*Aplan.txt\nBplan\nmaster\n\n=== Staged Files ===\nAplan.txt\n\n=== Files Marked for Untracking ===";
		assertEquals(ReturnMessage2,StatusContent);
	}
	
	/**
	 * test checkout Branch
	 */
	@Test
	public void testCheckoutIdFile(){
		String letterA = TESTING_DIR + "a.txt";
		String AText = "This is a letter A.";
		createFile(letterA, AText);
		String letterB = TESTING_DIR + "b.txt";
		String BText = "This is a letter B.";
		createFile(letterB, BText);
		String letterC = TESTING_DIR + "c.txt";
		String CText = "This is a letter C.";
		createFile(letterC, CText);
		gitlet("init");
		gitlet("add",letterA);
		gitlet("commit","first");
		gitlet("branch","Aplan");
		gitlet("checkout","Aplan");
		gitlet("add",letterB);
		gitlet("commit","second");
		gitlet("checkout","master");
		gitlet("add",letterC);
		gitlet("commit","third");
		writeFile(letterB, "This is not a wug.");
		gitlet("checkout","2",letterB);
		assertEquals(BText, getText(letterB));
		
	}
	
	/**
	 * test global-log
	 */
	@Test
	public void testBasicgloballog(){
		String letterA = TESTING_DIR + "a.txt";
		String AText = "This is a letter A.";
		createFile(letterA, AText);
		String letterB = TESTING_DIR + "b.txt";
		String BText = "This is a letter B.";
		createFile(letterB, BText);
		String letterC = TESTING_DIR + "c.txt";
		String CText = "This is a letter C.";
		createFile(letterC, CText);
		String letterD = TESTING_DIR + "d.txt";
		String DText = "This is a letter D.";
		createFile(letterD, DText);
		gitlet("init");
		String Message1 = "initial commit";
		gitlet("add",letterA);
		gitlet("commit","first");
		String Message2 = "first";
		gitlet("branch","Aplan");
		gitlet("branch","Bplan");
		gitlet("checkout","Aplan");
		gitlet("add",letterB);
		gitlet("commit","second");
		String Message3 = "second";
		gitlet("checkout","Bplan");
		gitlet("add",letterC);
		gitlet("commit","third");
		String Message4 = "third";
		gitlet("checkout","master");
		gitlet("add",letterD);
		gitlet("commit","fourth");
		String Message5 = "fourth";
		
		String logContent = gitlet("global-log");
		assertArrayEquals(new String[] { Message5, Message4,Message3,Message2,Message1 },
				extractCommitMessages(logContent));	
	}
	
	/**
	 * Test find
	 */
	@Test
	public void testBasicFind(){
		String letterA = TESTING_DIR + "a.txt";
		String AText = "This is a letter A.";
		createFile(letterA, AText);
		String letterB = TESTING_DIR + "b.txt";
		String BText = "This is a letter B.";
		createFile(letterB, BText);
		String letterC = TESTING_DIR + "c.txt";
		String CText = "This is a letter C.";
		createFile(letterC, CText);
		String letterD = TESTING_DIR + "d.txt";
		String DText = "This is a letter D.";
		createFile(letterD, DText);
		gitlet("init");
		String finContent1 = gitlet("find","good");
		String ReturnMessage1 = "Found no commit with that message.";
		assertEquals(ReturnMessage1, finContent1);
		gitlet("add", letterA);
		gitlet("commit","good");
		gitlet("branch","Aplan");
		gitlet("checkout","Aplan");
		gitlet("add",letterB);
		gitlet("commit","bad");
		gitlet("branch","Bplan");
		gitlet("checkout","Bplan");
		gitlet("add",letterC);
		gitlet("commit","bad");
		gitlet("checkout","master");
		gitlet("add",letterD);
		gitlet("commit","bad");
		String findContend2 = gitlet("find","bad");
		String ReturnMessage2 = "4\n3\n2";
		assertEquals(ReturnMessage2,findContend2);
		String findContend3 = gitlet("find","initial commit");
		String ReturnMessage3 = "0";
		assertEquals(ReturnMessage3,findContend3);
	}
	
	/**
	 * Test Status
	 */
	@Test
	public void testBasicStatus(){
		String letterA = TESTING_DIR + "a.txt";
		String AText = "This is a letter A.";
		createFile(letterA, AText);
		String letterB = TESTING_DIR + "b.txt";
		String BText = "This is a letter B.";
		createFile(letterB, BText);
		String letterC = TESTING_DIR + "c.txt";
		String CText = "This is a letter C.";
		createFile(letterC, CText);
		String letterD = TESTING_DIR + "d.txt";
		String DText = "This is a letter D.";
		createFile(letterD, DText);
		gitlet("init");
		gitlet("branch","Aplan");
		gitlet("branch","Bplan");
		gitlet("checkout","Aplan");
		gitlet("add",letterA);
		gitlet("add",letterB);
		gitlet("commit","first");
		gitlet("rm",letterA);
		gitlet("rm",letterB);
		gitlet("add",letterC);
		gitlet("add",letterD);
		String status = gitlet("status");
		String ReturnMessage = "=== Branches ===\n*Aplan\nBplan\nmaster\n\n=== Staged Files ===\n"+letterC+"\n"+letterD+"\n\n=== Files Marked for Untracking ===\n"+letterA+"\n"+letterB;
		assertEquals(ReturnMessage, status);
	}
	
	/**
	 * Test rm-branch
	 */
	@Test
	public void testBasicrmbranch(){
		String letterA = TESTING_DIR + "a.txt";
		String AText = "This is a letter A.";
		createFile(letterA, AText);
		String letterB = TESTING_DIR + "b.txt";
		String BText = "This is a letter B.";
		createFile(letterB, BText);
		String letterC = TESTING_DIR + "c.txt";
		String CText = "This is a letter C.";
		createFile(letterC, CText);
		String letterD = TESTING_DIR + "d.txt";
		String DText = "This is a letter D.";
		createFile(letterD, DText);
		gitlet("init");
		String rmbranchContent1 = gitlet("rm-branch","hello");
		String ReturnMessage1 = "A branch with that name does not exist.";
		assertEquals(ReturnMessage1, rmbranchContent1);
		gitlet("branch","Aplan");
		gitlet("checkout","Aplan");
		String rmbranchContent2 = gitlet("rm-branch","Aplan");
		String ReturnMessage2 = "Cannot remove the current branch.";
		assertEquals(ReturnMessage2, rmbranchContent2);
		gitlet("add",letterA);
		gitlet("commit","first");
		gitlet("add",letterB);
		gitlet("commit","second");
		gitlet("checkout","master");
		gitlet("rm-branch","Aplan");
		String statusContent = gitlet("status");
		String ReturnMessage3 = "=== Branches ===\n*master\n\n=== Staged Files ===\n\n=== Files Marked for Untracking ===";
		assertEquals(ReturnMessage3, statusContent);
		String findContent = gitlet("find","first");
		String ReturnMessage4 = "1";
		assertEquals(ReturnMessage4, findContent);
	}
	
	/**
	 * 
	 */
	@Test
	public void testReset(){
		String letterA = TESTING_DIR + "a.txt";
		String AText = "This is a letter A.";
		createFile(letterA, AText);
		String letterB = TESTING_DIR + "b.txt";
		String BText = "This is a letter B.";
		createFile(letterB, BText);
		String letterC = TESTING_DIR + "c.txt";
		String CText = "This is a letter C.";
		createFile(letterC, CText);
		String letterD = TESTING_DIR + "d.txt";
		String DText = "This is a letter D.";
		createFile(letterD, DText);
		gitlet("init");
		String Message1 = "initial commit";
		String ResetContent1 = gitlet("reset","10");
		String ReturnMessage1 = "No commit with that id exists.";
		assertEquals(ReturnMessage1, ResetContent1);
		gitlet("add",letterA);
		gitlet("commit","first");
		String Message2 = "first";
		gitlet("add",letterB);
		gitlet("commit","second");
		gitlet("add",letterC);
		gitlet("commit","third");
		gitlet("add",letterD);
		gitlet("reset","1");
		String logContent = gitlet("log");
		assertArrayEquals(new String[] {Message2,Message1 },
				extractCommitMessages(logContent));		
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
	 * Test Merge
	 */
	@Test
	public void testMerge(){
//		String letterA = "a.txt";
		String letterA = TESTING_DIR + "a.txt";
		String AText = "This is a letter A.";
		createFile(letterA, AText);
//		String letterB = "b.txt";
		String letterB = TESTING_DIR + "b.txt";
		String BText = "This is a letter B.";
		createFile(letterB, BText);
//		String letterC = "c.txt";
		String letterC = TESTING_DIR + "c.txt";
		String CText = "This is a letter C.";
		createFile(letterC, CText);
//		String letterD = "d.txt";
		String letterD = TESTING_DIR + "d.txt";
		String DText = "This is a letter D.";
		createFile(letterD, DText);
//		String letterE = "e.txt";
		String letterE = TESTING_DIR + "e.txt";
		String EText = "This is a letter E.";
		createFile(letterE, EText);
//		String letterF = "f.txt";
		String letterF = TESTING_DIR + "f.txt";
		String FText = "This is a letter F.";
		createFile(letterF, FText);
		String letterG = TESTING_DIR + "g.txt";
//		String letterG = "g.txt";
		String GText = "This is a letter G.";
		createFile(letterG, GText);
		
		gitlet("init");
		gitlet("add",letterA);
		gitlet("add",letterB);
		gitlet("add",letterC);
		gitlet("add",letterG);
		gitlet("commit","original file A B C G");
		gitlet("branch","given branch");
		gitlet("branch","current branch");
		gitlet("checkout","given branch");
		writeFile(letterA, "One change A in given branch");
		gitlet("add",letterA);
		gitlet("add",letterD);
		
		gitlet("commit","Change A and add D in 1st commit in given branch");
		writeFile(letterB, "One change B in given branch");
		gitlet("add", letterB);
		gitlet("commit","Changed B in 2nd commit in given branch");
		gitlet("add",letterG);
		gitlet("commit","add G");
		gitlet("rm",letterG);
		gitlet("commit","remove letterG in given branch");
		gitlet("branch", "branchOfbranch");
		gitlet("checkout","branchOfbranch");
		gitlet("add",letterE);
		gitlet("commit","add E in branchOfbranch");
		gitlet("checkout","current branch");
		writeFile(letterA, "One change A in current branch");
		writeFile(letterE, "this E in current branch");
		gitlet("add",letterA);
		gitlet("add",letterE);
		gitlet("commit","change A and add E in 1st commit of current branch");
		gitlet("add",letterF);
		gitlet("commit","add F in 2nd node of current branch");
		String ConflictContent1 = gitlet("merge","branchOfbranch");
		String ReturnMessage1 = "Encountered a merge conflict.";
		assertEquals(ReturnMessage1,ConflictContent1);
		String StatusContent = gitlet("status");
		String ReturnMessage2 = "=== Branches ===\n*current branch\nbranchOfbranch\ngiven branch\nmaster\n\n=== Staged Files ===\n\n=== Files Marked for Untracking ===";
		assertEquals(ReturnMessage2, StatusContent);
		String ConflictContent2 = gitlet("branch","newbranch");
		String ReturnMessage3 = "Cannot do this command until the merge conflict has been resolved.";
		assertEquals(ConflictContent2,ReturnMessage3);
		String ConflictContent3 = gitlet("checkout","branchOfbranch");
		assertEquals(ConflictContent3,ReturnMessage3);
		String ConflictContent4 = gitlet("rm-branch","branchOfbranch");
		assertEquals(ConflictContent4,ReturnMessage3);
		String ConflictContent5 = gitlet("merge","given branch");
		assertEquals(ReturnMessage3,ConflictContent5);
		gitlet("add","a.txt.conflicted");
		gitlet("commit","finish-conflict-status");
		gitlet("branch","test-branch-command");
		String ReturnMessage6 = "=== Branches ===\n*current branch\nbranchOfbranch\ngiven branch\ntest-branch-command\nmaster\n\n=== Staged Files ===\n\n=== Files Marked for Untracking ===";
		String StatusContent2 = gitlet("status");
		assertEquals(ReturnMessage6, StatusContent2);
		
		String MergeContent1 = gitlet("merge","hello");
		String ReturnMessage4 = "A branch with that name does not exist.";
		assertEquals(ReturnMessage4,MergeContent1);
		String MergeContent2 = gitlet("merge","current branch");
		String ReturnMessage5 = "Cannot merge a branch with itself.";
		assertEquals(ReturnMessage5,MergeContent2);
	}

	
	/**
	 * Test rebase
	 */
	@Test
	public void TestBasicRebase(){
//		String letterA = "a.txt";
		String letterA = TESTING_DIR + "a.txt";
		String AText = "This is a letter A.";
		createFile(letterA, AText);
//		String letterB = "b.txt";
		String letterB = TESTING_DIR + "b.txt";
		String BText = "This is a letter B.";
		createFile(letterB, BText);
//		String letterC = "c.txt";
		String letterC = TESTING_DIR + "c.txt";
		String CText = "This is a letter C.";
		createFile(letterC, CText);
//		String letterD = "d.txt";
		String letterD = TESTING_DIR + "d.txt";
		String DText = "This is a letter D.";
		createFile(letterD, DText);
//		String letterE = "e.txt";
		String letterE = TESTING_DIR + "e.txt";
		String EText = "This is a letter E.";
		createFile(letterE, EText);
//		String letterF = "f.txt";
		String letterF = TESTING_DIR + "f.txt";
		String FText = "This is a letter F.";
		createFile(letterF, FText);
		String letterG = TESTING_DIR + "g.txt";
//		String letterG = "g.txt";
		String GText = "This is a letter G.";
		createFile(letterG, GText);
		gitlet("init");
		String Message0 = "initial commit";
		gitlet("add",letterA);
		gitlet("commit","first");
		String Message1 = "first";
		gitlet("branch","Aplan");
		gitlet("branch","Bplan");
		gitlet("branch","Cplan");
		gitlet("add",letterB);
		gitlet("commit","second");
		String Message2 = "second";
		gitlet("checkout","Aplan");
		gitlet("add",letterC);
		gitlet("commit","third");
		String Message3 = "third";
		gitlet("add",letterD);
		gitlet("commit","fourth");
		String Message4 = "fourth";
		gitlet("rebase","master");
		gitlet("checkout","Bplan");
		gitlet("add",letterE);
		gitlet("commit","fifth");
		String Message5 = "fifth";
		String RebaseContent = gitlet("rebase","hello");
		String ReturnMessage1 = "A branch with that name does not exist.";
		assertEquals(ReturnMessage1,RebaseContent);
		String RebaseContent2 = gitlet("rebase","Bplan");
		String ReturnMessage2 = "Cannot rebase a branch onto itself.";
		assertEquals(ReturnMessage2,RebaseContent2);
		gitlet("checkout","master");

		gitlet("rebase","Bplan");
		String logContent1 = gitlet("log");
		assertArrayEquals(new String[] {Message2,Message5,Message1,Message0 },
				extractCommitMessages(logContent1));	
//		gitlet("checkout","Bplan");
		String RebaseContent3 = gitlet("rebase","Bplan");
		String ReturnMessage3 = "Already up-to-date.";
		assertEquals(ReturnMessage3,RebaseContent3);
		gitlet("checkout","Aplan");
		gitlet("rebase","master");
		String RebaseContent4 = gitlet("rebase","Bplan");
		assertEquals(ReturnMessage3,RebaseContent4);
		String logContent2 = gitlet("log");
		assertArrayEquals(new String[] {Message4,Message3,Message2,Message2,Message5,Message1,Message0 },
				extractCommitMessages(logContent2));	
	}
	
	
	/**
	 * Test bug
	 */
	@Test
	public void testSpecialBug(){
//		String letterA = "a.txt";
		String wug = TESTING_DIR + "wug.txt";
		String wugText = "this is a wg";
		createFile(wug, wugText);

		gitlet("init");
		gitlet("add",wug);
		gitlet("commit","first");
		writeFile(wug, "This is a wug");
		gitlet("add",wug);
		gitlet("commit","second");
		gitlet("branch","branch1");
		gitlet("checkout","branch1");
		writeFile(wug, "This is a wug!");
		gitlet("add",wug);
		gitlet("commit","third");
		gitlet("branch","branch2");
		gitlet("checkout","branch2");
		String recent = "This is a wug!";
		assertEquals(recent, getText(wug));
		
		
		
	}
	
	/**
	 * End to End Test
	 */
	@Test
	public void EndToEnd(){
//		String letterA = "a.txt";
		String letterA = TESTING_DIR + "a.txt";
		String AText = "This is a letter A.";
		createFile(letterA, AText);
//		String letterB = "b.txt";
		String letterB = TESTING_DIR + "b.txt";
		String BText = "This is a letter B.";
		createFile(letterB, BText);
//		String letterC = "c.txt";
		String letterC = TESTING_DIR + "c.txt";
		String CText = "This is a letter C.";
		createFile(letterC, CText);
//		String letterD = "d.txt";
		String letterD = TESTING_DIR + "d.txt";
		String DText = "This is a letter D.";
		createFile(letterD, DText);
//		String letterE = "e.txt";
		String letterE = TESTING_DIR + "e.txt";
		String EText = "This is a letter E.";
		createFile(letterE, EText);
//		String letterF = "f.txt";
		String letterF = TESTING_DIR + "f.txt";
		String FText = "This is a letter F.";
		createFile(letterF, FText);
		String letterG = TESTING_DIR + "g.txt";
//		String letterG = "g.txt";
		String GText = "This is a letter G.";
		createFile(letterG, GText);
		String letterH = TESTING_DIR + "h.txt";
//		String letterG = "g.txt";
		String HText = "This is a letter H.";
		createFile(letterH, HText);
		
		gitlet("init");
		String Message0 = "initial commit";
		gitlet("add",letterA);
		gitlet("commit","first");
		String Message1 = "first";
		gitlet("add",letterB);
		gitlet("commit","second");
		String Message2 = "second";
		gitlet("branch","branch0");
		gitlet("branch","branch1");
		writeFile(letterA,"this is two As");
		gitlet("add",letterA);
		gitlet("add",letterC);
		gitlet("add",letterD);
		String statusContent1 = gitlet("status");
		String ReturnMessage1 = "=== Branches ===\n*master\nbranch1\nbranch0\n\n=== Staged Files ===\n"+letterA+"\n"+letterC+"\n"+letterD+"\n\n=== Files Marked for Untracking ===";
		assertEquals(ReturnMessage1,statusContent1);
		gitlet("rm",letterD);
		String statusContent2 = gitlet("status");
		String ReturnMessage2 = "=== Branches ===\n*master\nbranch1\nbranch0\n\n=== Staged Files ===\n"+letterA+"\n"+letterC+"\n\n=== Files Marked for Untracking ===";
		assertEquals(ReturnMessage2,statusContent2);
		gitlet("commit","third");
		String Message3 = "third";
		gitlet("rm",letterC);
		String statusContent3 = gitlet("status");
		String ReturnMessage3 = "=== Branches ===\n*master\nbranch1\nbranch0\n\n=== Staged Files ===\n\n=== Files Marked for Untracking ===\n"+letterC;
		assertEquals(ReturnMessage3,statusContent3);
		writeFile(letterA,"this is three As");
		gitlet("add",letterA);
		gitlet("commit","fourth");
		String Message4 = "fourth";
		gitlet("checkout","3",letterA);
		assertEquals("this is two As",getText(letterA));
		gitlet("checkout","branch1");
		gitlet("add",letterE);
		gitlet("commit","fifth");
		String Message5 = "fifth";
		gitlet("branch","branch2");
		gitlet("add",letterF);
		gitlet("commit","sixth");
		String Message6 = "sixth";
		gitlet("checkout","branch2");
		writeFile(letterB,"this is two As");
		gitlet("add",letterB);
		gitlet("commit","seventh");
		String Message7 = "seventh";
		writeFile(letterA,"this is fourth As");
		gitlet("add",letterA);
		gitlet("commit","eighth");
		String Message8 = "eighth";
		String mergeContent1 = gitlet("merge","master");
		String ReturnMessage4 = "Encountered a merge conflict.";
		assertEquals(ReturnMessage4,mergeContent1);
		String mergeContent2 = gitlet("branch","branch3");
		String ReturnMessage5 = "Cannot do this command until the merge conflict has been resolved.";
		assertEquals(ReturnMessage5,mergeContent2);
		gitlet("commit","ninth");
		String Message9 = "ninth";
		gitlet("branch","branch3");
		gitlet("checkout","branch3");
//		gitlet("status");
		gitlet("add",letterG);
		gitlet("commit","tenth");
		String Message10 = "tenth";
		gitlet("rebase","master");
		String logContent1 = gitlet("log");
		assertArrayEquals(new String[] {Message10,Message8,Message7,Message5,Message4,Message3,Message2,Message1,Message0 },
				extractCommitMessages(logContent1));
		writeFile(letterG,"this is NOT G");
		gitlet("checkout",letterG);
		assertEquals("This is a letter G.",getText(letterG));
		writeFile(letterE,"this is NOT E");
		gitlet("checkout",letterE);
		assertEquals("This is a letter E.",getText(letterE));
		
		gitlet("reset","3");
		String logContent2 = gitlet("log");
		assertArrayEquals(new String[] {Message3,Message2,Message1,Message0 },
				extractCommitMessages(logContent2));
		gitlet("status");
		gitlet("checkout","branch1");
		String logContent3 = gitlet("log");
		assertArrayEquals(new String[] {Message6,Message5,Message2,Message1,Message0 },
				extractCommitMessages(logContent3));
		gitlet("checkout","branch0");
		gitlet("add",letterH);
		gitlet("commit","eleventh");
		gitlet("merge","branch1");
		writeFile(letterF,"this is NOT F");
		gitlet("checkout",letterF);
		assertEquals("This is a letter F.",getText(letterF));
		gitlet("checkout","branch2");
		gitlet("rebase","branch0");
		gitlet("checkout",letterH);
		assertEquals("This is a letter H.",getText(letterH));
		
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