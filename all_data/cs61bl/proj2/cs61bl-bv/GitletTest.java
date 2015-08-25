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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

		System.out.println(wugFileName);
		System.out.println(wugText);
		createFile(wugFileName, wugText);
		gitlet("init");
		gitlet("add", wugFileName);
		gitlet("commit", "added wug");
		writeFile(wugFileName, "This is not a wug.");
		gitlet("checkout", wugFileName);
		System.out.println(getText(wugFileName));
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
	 * Tests that you can checkout an arbitrary commit given the desired commit's ID 
	 * and file name from anywhere in the commit graph (can checkout a file from 
	 * a different branch).
	 */
	@Test 
	public void testArbitraryCheckout() {
		start_TEST("testArbitraryCheckout");

		gitlet("init");
		// account for initial commit  
		String commitMessage1 = "initial commit";

		// create and add a file: wug.txt 
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		// commit 1
		String commitMessage2 = "added wug";

		// need access to our git object 
		String work_dir = System.getProperty("user.dir");
		Gitlet g = Gitlet.deserialize(work_dir);

		// commit 
		gitlet("commit", commitMessage2);

		// check what master branch is for comparison later 
		System.out.println("Master branch: " + g.getCurrentBranch());
		// create another branch 
		String branch_Name = "branch_1";
		gitlet("branch", branch_Name);						// create new branch branch_1 but we're still on master 

		// change branch 
		gitlet("checkout", branch_Name);					// myHead pointer points to branch_1 now 

		// update wug file 
		String newWugText = "Yoloswag gun it.";
		createFile(wugFileName, newWugText); 				// modified wug.txt in branch_1 - should be different from the wug.txt in master 

		gitlet("add", wugFileName);
		// commit in branch_1
		String commitMessage3 = "added wug in branch_1";
		gitlet("commit", commitMessage3);

		// need to get commit id 
		g = Gitlet.deserialize(work_dir);
		Integer commit_ID = g.getCommitsByMessage().get(commitMessage2).element(); 		// gets the ID for the commit made in master for wug.txt 

		System.out.println();
		System.out.println("Printing text in wug.txt of branch_1 before checkout");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(getText(wugFileName));

		// checkout wug.txt from master
		gitlet("checkout", commit_ID.toString(), wugFileName);
		
		System.out.println("Current branch (BRANCH1): " + g.getCurrentBranch());
		System.out.println("CURRENT HEAD: " + g.getMyHead());
		System.out.println("B1's ROOT: " + g.getCurrentBranch().getRoot());
		System.out.println("B1's LEAF: " + g.getCurrentBranch().getLeaf());

		System.out.println();
		System.out.println("Printing text in wug.txt of branch_1 after checkout");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(getText(wugFileName));

		// now check that the wug.txt in branch_1 is the same as the wug.txt in master since we checked out 
		assertTrue(g.getMyHead() == g.getCurrentBranch().getLeaf());
				// this asserts that our current HEAD is pointed to the same commit as the current branch we're on since branch is a pointer to a commit node	

		end_TEST("testArbitraryCheckout");
	}

	/**
	 * Tests that logging changes appropriately when in different branches. 
	 */
	@Test
	public void testLoggingInDifferentBranches() {
		System.out.println();
		System.out.println("START testLoggingInDifferentBranches!!!");
		gitlet("init");
		// account for initial commit  
		String commitMessage1 = "initial commit";

		// create and add a file: wug.txt 
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		// commit 1
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);

		// create another branch 
		String branch_Name = "branch_1";
		gitlet("branch", branch_Name);						// create new branch branch_1 but we're still on master 

		// change branch 
		gitlet("checkout", branch_Name);					// myHead pointer points to branch_1 now 

		// update wug file 
		String newWugText = "Yoloswag gun it.";
		createFile(wugFileName, newWugText); 				// modified wug.txt in branch_1 - should be different from the wug.txt in master 
		System.out.println();
		System.out.println("Text in wug.txt file in branch_1 is: " + getText(wugFileName));					// should be newWugText
		// commit in branch_1
		gitlet("add", wugFileName);
		String commitMessage3 = "added wug in branch_1";
		gitlet("commit", commitMessage3);

		// log in branch_1 
		System.out.println();
		System.out.println("LOG IN BRANCH_1");
		System.out.println(gitlet("log"));
		String[] log_BRANCH_1 = extractCommitMessages(gitlet("log"));

		// checkout to master and log in master 
		gitlet("checkout", "master");
		System.out.println();
		System.out.println("LOG IN MASTER");
		System.out.println(gitlet("log"));
		String[] log_MASTER = extractCommitMessages(gitlet("log"));

		// compare logs of master and branch_1
		assertFalse(Arrays.equals(log_BRANCH_1, log_MASTER));
		System.out.println();
		System.out.println("END testLoggingInDifferentBranches");
	}

	/**
	 * Tests that add command stages files properly.
	 * If tracked or potentially tracked folder is within a file, the folder should be staged as well.
	 */
	@Test
	public void testAdd() {
		System.out.println();
		start_TEST("testAdd");

		gitlet("init");
		// make our own stage for testing purposes 
		String STAGE = ".gitlet/stage/";										
		File c = new File (STAGE);
		if (c.exists()) {
			try {
				recursiveDelete(c);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		c.mkdirs();
		
		// account for initial commit 
		String commitMessage1 = "initial commit";

		// elementary case for adding: a file is created in the working directory and then staged and commited 
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);
		File z = new File(STAGE + wugFileName);
		assertTrue(z.exists());
		String commitMessage2 = "added wug in working directory";
		gitlet("commit", commitMessage2);
		assertFalse(z.exists());

		// complicated adding case: a file is created in a directory ("comp_folder") in the working directory, staged, then committed
		String COMP_FOLDER = "comp_folder/";
		// check if this comp_folder exists, if it does, delete it and make a new empty one 
		File f = new File(COMP_FOLDER);
		if (f.exists()) {
			try {
				recursiveDelete(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		f.mkdirs();
		// now add a file into comp_folder
		String compFileName = COMP_FOLDER + "compAdd.txt";
		String compFileText = "this is the complicated case";
		createFile(compFileName, compFileText);
		gitlet("add", compFileName);																	// stage the compFile 

		// check that comp_folder/comp_file is in our stage folder 
		String COMP_FILE_IN_STAGE = STAGE + compFileName;						// should make ".gitlet/stage/comp_folder/compAdd.txt"
		String COMP_FOLDER_IN_STAGE = STAGE + COMP_FOLDER;						// should make ".gitlet/stage/comp_folder/"
		System.out.println();
		System.out.println("Checking path strings for staged comp_folder and comp_file are correct");
		System.out.println(COMP_FILE_IN_STAGE);
		System.out.println(COMP_FOLDER_IN_STAGE);

		File a = new File(COMP_FILE_IN_STAGE);
		File b = new File(COMP_FOLDER_IN_STAGE);

		// list all folders and files in stage
		System.out.println("Listing stuff in stage then stage/comp_folder");
		System.out.println(c.list()); 						// should see comp_folder
		System.out.println(b.list()); 						// should see comp_file  
		
		
		assertTrue(a.exists());									
		assertTrue(b.exists());
		
		// commit and this should clear our stage folder 
		String commitMessage3 = "made complicated test case, a file is nested in a directory";		    // compFIle is now committed and part of our commit history 
		gitlet("commit", commitMessage3);
		// because stage is cleared, a and b shouldn' exist anymore 
		assertFalse(a.exists());
		assertFalse(b.exists()); 	
		System.out.println("END OF: testAdd");		

		System.out.println();		
		end_TEST("testAdd");

	}

	@Test
	public void testComplexCheckout_1() {
		System.out.println();
		start_TEST("testComplexCheckout_1");

		// make folders to nest a file "2 folders deep"
		String COMP_FOLDER =  TESTING_DIR + "comp_folder_1/comp_folder_2/";

		File comp_folder = new File(COMP_FOLDER);
		File[] folders = {comp_folder};

		// remove folders if they already exist to reset 
		for (File folder : folders) {
			System.out.println(folder);
			if (folder.exists()) {
				try {
					recursiveDelete(folder);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// now make the folders in a "clean" directory 
		comp_folder.mkdirs();

		// now add a file nested in the comp folder system 
		String wugFileName = COMP_FOLDER + "wug.txt";
		String wugText = "This is a wug";
		System.out.println("DEBUGGING fw is null problem: checking arguments for when we call createFile");
		System.out.println(wugFileName);
		System.out.println(wugText);
		createFile(wugFileName, wugText);						// should create a file in testing directory comp1/comp2/wug.txt 

		//  initialize gitlet 
		gitlet("init");
		gitlet("add", wugFileName);
		// first commit 
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);
		System.out.println(getText(wugFileName));
		assertEquals(wugText, getText(wugFileName));

		// now branch, commit, and then try to checkout the wug.txt in previous commit 
		String branch_Name = "branch_1";
		gitlet("branch", branch_Name);
		gitlet("checkout", branch_Name);

		// update the file in branch_1 
		String wugText_2 = "This is not a wug";
		createFile(wugFileName, wugText_2);					// updates the wug.txt file in branch_1
		// commit in branch_1 
		String commitMessage3 = "added wug in branch_1 and updated it";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage3);

		// start comparing logs 
		System.out.println();
		System.out.println("CHECKPOINT 1");
		System.out.println("START COMPARING LOGS IN BRANCH_1 and MASTER AT CHECKPOINT 1");
		// log in branch_1
		System.out.println(gitlet("log"));
		String [] log_BRANCH_1 = extractCommitMessages(gitlet("log"));

		// checkout and log in master to compare that logs are different 
		gitlet("checkout", "master");
		System.out.println();
		System.out.println(gitlet("log"));
		String[] log_MASTER = extractCommitMessages(gitlet("log"));

//		assertFalse(Arrays.equals(log_BRANCH_1, log_MASTER));

		// get commit_id of the commit made in master to wug.txt 
		System.out.println();
		System.out.println("GETTING COMMIT ID");
		String work_dir = System.getProperty("user.dir");
		System.out.println(work_dir);
		Gitlet g = Gitlet.deserialize(work_dir);
		Integer commit_ID = g.getCommitsByMessage().get(commitMessage2).element(); 			// gets the ID for the commit made to wug.txt in master 
		// go back to branch_1 and checkout the commit to wug.txt made in master 
		gitlet("checkout", branch_Name);
		System.out.println("Text for wug.txt in branch_1 before checking out");
		System.out.println(getText(wugFileName));				// should be this is not a wug 
		String branch_1_WUG = getText(wugFileName);
		System.out.println();
		System.out.println("Checking out wug.txt from master");
		gitlet("checkout", commit_ID.toString(), wugFileName);
		System.out.println();
		System.out.println("CHECKED OUT");
		System.out.println();
		System.out.println("Text for wug.txt in branch_1 AFTER checking out");
		System.out.println(getText(wugFileName));			// should be this is a wug 
		String master_WUG = getText(wugFileName);
		assertFalse(branch_1_WUG.equals(master_WUG));
		assertEquals(master_WUG, wugText);
		System.out.println();
		
		end_TEST("testComplexCheckout_1");

		System.out.println();
	}

	@Test 
	public void testComplexCheckout_2() {
		System.out.println();
		start_TEST("testComplexCheckout_2");
		// make folders to nest a file "2 folders deep"
		String COMP_FOLDER =  TESTING_DIR + "comp_folder_1/comp_folder_2/";

		File comp_folder = new File(COMP_FOLDER);
		File[] folders = {comp_folder};


		// remove folders if they already exist to reset 
		for (File folder : folders) {
			if (folder.exists()) {
				try {
					recursiveDelete(folder);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// now make the folders in a "clean" directory 

		comp_folder.mkdirs();

		// now add a file nested in the comp folder system 
		String wugFileName = COMP_FOLDER + "wug.txt";
		String wugText = "This is a wug";
		createFile(wugFileName, wugText);						// should create a file in testing directory comp1/comp2/wug.txt 

		//  initialize gitlet 
		gitlet("init");
		gitlet("add", wugFileName);
		// first commit 
		String commitMessage2 = "added wug";
		gitlet("commit", commitMessage2);
		System.out.println(getText(wugFileName));
		assertEquals(wugText, getText(wugFileName));

		// now branch, commit, and then try to checkout the wug.txt in previous commit 
		String branch_Name = "branch_1";
		gitlet("branch", branch_Name);
		// we want to make sure that when we checkout the wug.txt from master, its folders show up too 
		// delete the folders in branch_1 so that when we checkout the folders should be created 




		for (File folder : folders) {
			if (folder.exists()) {
				try {
					recursiveDelete(folder);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		// folders should be deleted and branch_1 should be clean 
		
		// get commit_id of the commit made in master to wug.txt 
		// need to be in master to get commit ID 
		gitlet("checkout", "master");
		System.out.println(gitlet("log"));
		System.out.println();
		System.out.println("GETTING COMMIT ID");
		String work_dir = System.getProperty("user.dir");
		System.out.println(work_dir);
		Gitlet g = Gitlet.deserialize(work_dir);
		Integer commit_ID = g.getCommitsByMessage().get(commitMessage2).element(); 			// gets the ID for the commit made to wug.txt in master 
		// go back into branch_1 and checkout the commit to wug.txt made in master 
		gitlet("checkout", branch_Name);
		System.out.println();
		System.out.println("Checking out wug.txt from master");
		gitlet("checkout", commit_ID.toString(), wugFileName);
		System.out.println();
		System.out.println("CHECKED OUT");
		System.out.println();
		System.out.println("Text for wug.txt in branch_1 AFTER checking out");
		System.out.println(getText(wugFileName));			// should be this is a wug 
		String master_WUG = getText(wugFileName);
		assertEquals(master_WUG, wugText);
		System.out.println();
		end_TEST("testComplexCheckout_2");

	}

	@Test 
	public void testRemove() {
		System.out.println();
		start_TEST("testRemove");

		// case 1: set up gitlet testiing directory with a file and then remove it 
		gitlet("init");

		String file_1_name = TESTING_DIR + "file_1.txt";
		String file_1_text = "this is file_1";
		createFile(file_1_name, file_1_text);					// creates file_1 in current dir 

		// try to remove, should print out that there's no reason to remove the file because it's not tracked yet 
		gitlet("rm", "file_1_name");

		// now add the file and commit it 
		gitlet("add", file_1_name);								// marked for tracking 
		String commitMessage2 = "added file_1 to the testing dir";
		gitlet("commit", commitMessage2);
		// create a nest folder here 
		String nest_folder_name = TESTING_DIR + "nestfolder/";
		String nest_file_name = nest_folder_name + "nestfile.txt";
		String nest_file_text = "this is the nested file";
		File nest_folder = new File(nest_folder_name);
		nest_folder.mkdirs();
		// add file to nest folder 
		createFile(nest_file_name, nest_file_text);

		// now testing directory looks like: file_1.txt ; nestfolder/nestfile.txt 
		
		// untrack file_1.txt by removing it 
		gitlet("rm", file_1_name);
		// now nothing is being tracked 
		System.out.println();
		System.out.println("Just removed file_1.txt from tracking");
		String commitMessage3 = "just removed file_1.txt from tracking, shouldn't show up in this commit";
		gitlet("commit", commitMessage3);

		// now add the nested file to the stage and then rm it, it should just be unstaged
		gitlet("add", nest_file_name);
		// check that it's in the stage 
		String STAGE = ".gitlet/stage/";
		String nest_file_in_stage = STAGE + nest_file_name; // should make .gitlet/stage/nestfolder/nestfile.txt 
		System.out.println();
		System.out.println("Check that stage path is correct");
		System.out.println(nest_file_in_stage);
		File nest_stage = new File(nest_file_in_stage);
		assertTrue(nest_stage.exists());
		gitlet("rm", nest_file_name);
		// nest_stage should not be in the stage anymore 
		assertFalse(nest_stage.exists());

		System.out.println();
		end_TEST("testRemove");

	}

	@Test 
	public void testCommitTracking() {
		System.out.println();
		start_TEST("testCommitTracking");

		// init 
		gitlet("init");
		String STAGE = ".gitlet/stage/";
		File stage = new File(STAGE);
		if (stage.exists()) {
			try {
				recursiveDelete(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		stage.mkdirs();

		String wugFileName = "wug.txt";
		String wugText = "this is a wug";
		createFile(wugFileName, wugText);
		// add and commit
		gitlet("add", wugFileName);
		String commitMessage2 = commit("added wug.txt");
		// check that file is in stage 
		
		String wugFileName_INSTAGE = STAGE + wugFileName;
		System.out.println();
		System.out.println("Check that stage path is correct");
		System.out.println((wugFileName_INSTAGE));						// should be .gitlet/stage/wug.txt 
		File wugFile_INSTAGE = new File(wugFileName_INSTAGE);
		System.out.println();
		System.out.println("PRINTING OUT wugFile_INSTAGE");
		System.out.println(wugFile_INSTAGE);
//		assertTrue(wugFile_INSTAGE.exists());

		// make another file for a second commit and update wug.txt to show that only the previous one is being tracked 
		String wugText_UPDATED_1 = "THIS IS AN UPDATED WUG";
		// update the wug file 
		createFile(wugFileName, wugText_UPDATED_1);
		// make second file 
		String secondFileName = "second.txt";
		String secondText = "this is the second file";
		String secondFileName_INSTAGE = STAGE + secondFileName;
		File secondFile_INSTAGE = new File(secondFileName_INSTAGE);
		createFile(secondFileName, secondText);
		gitlet("add", secondFileName);									// staged (only second file)
		assertTrue(secondFile_INSTAGE.exists());
		assertFalse(wugFile_INSTAGE.exists());
		// make the commit - only secondFile is staged, the wug.txt file is tracked 
		String commitMessage3 = commit("added second.txt");				// commited and stage should be cleared 

		assertFalse(secondFile_INSTAGE.exists());

		// use gitlog and our 2 commits should be in the log 

		System.out.println();
		System.out.println("Log should have \"this is a wug\" and \"added second.txt\"");
		String [] LOG = extractCommitMessages(gitlet("log"));
		printLOG(LOG);

		// make a new branch and update the wug.txt file in that branch just for example 
		String branch_Name = "branch1";
		gitlet("branch", branch_Name);
		gitlet("checkout", branch_Name);

		String wugText_UPDATED_2 = "UPDATED WUG IN BRANCH1";
		createFile(wugFileName, wugText_UPDATED_2);
		System.out.println("Here is content of wug file in branch1: " + getText(wugFileName));
		assertTrue(getText(wugFileName).equals(wugText_UPDATED_2));
		assertFalse(getText(wugFileName).equals(wugText_UPDATED_1));

		// add and commit 
		gitlet("add", wugFileName);
		String commitMessage4_branch1 = commit("updated wug in branch1");

		// checkout the last commit made in master 
		String work_dir = System.getProperty("user.dir");
		Gitlet g = Gitlet.deserialize(work_dir);
		Integer commit_ID = g.getCommitsByMessage().get(commitMessage3).element(); 			// gets ID for last commit made in master 

		System.out.println("FILE CONTENTS IN BRANCH1 BEFORE CHECKOUT");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Text in wug file: BRANCH1: " + getText(wugFileName));
		System.out.println("Text in second file: BRANCH1: " + getText(secondFileName));

		gitlet("checkout", commit_ID.toString(), wugFileName);

		// now branch_1 should have wug.txt containing "this is a wug" and second.txt containing "this is the second file"
		System.out.println("PRINTING OUT FILE CONTENTS AFTER CHECKOUT");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("Text in wug file: BRANCH1: " + getText(wugFileName));
		System.out.println("Text in second file: BRANCH1: " + getText(secondFileName));

		assertFalse(getText(wugFileName).equals(wugText_UPDATED_2));
		assertFalse(getText(wugFileName).equals(wugText_UPDATED_1));
		assertTrue(getText(wugFileName).equals(wugText));
		assertTrue(getText(secondFileName).equals(secondText));
		


		end_TEST("testCommitTracking");
	}

	@Test 
	public void testCommit() {
		System.out.println();
		start_TEST("testCommit");

		// init 
		gitlet("init");
		String STAGE = ".gitlet/stage/";
		File stage = new File(STAGE);
		if (stage.exists()) {
			try {
				recursiveDelete(stage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		stage.mkdirs();

		String wugFileName =  "wug.txt";
		String wugText = "this is a wug";
		String commitMessage2 = "added wug.txt";
		createFile(wugFileName, wugText);
		// add and commit 
		gitlet("add", wugFileName);
		// check that file is in stage 

		String wugFileName_INSTAGE = STAGE + wugFileName;
		System.out.println();
		System.out.println("Check that stage path is correct");
		System.out.println((wugFileName_INSTAGE));						// should be .gitlet/stage/wug.txt 
		File wugFile_INSTAGE = new File(wugFileName_INSTAGE);
		System.out.println();
		System.out.println("PRINTING OUT wugFile_INSTAGE");
		System.out.println(wugFile_INSTAGE);
		assertTrue(wugFile_INSTAGE.exists());

		gitlet("commit", commitMessage2);
		// now the stage should be cleared 
		assertFalse(wugFile_INSTAGE.exists());

		// use git log and our commit should be in the log 
		System.out.println();
		System.out.println("Printing out the log, commit1 should have message: added wug.txt");
		System.out.println(gitlet("log"));
		String[] LOG = extractCommitMessages(gitlet("log"));

		printLOG(LOG);

		assertEquals(LOG[0], commitMessage2);

		end_TEST("testCommit");
		
	}

	@Test 
	public void testFind() {
		System.out.println();
		start_TEST("testFind");



		System.out.println();
		end_TEST("testFind");

	}

	@Test 
	public void testReset() {
		start_TEST("testReset");
		// should make sure that the log changes 
		
		// Stage 1: git init, make a file  and make a couple commits 
		gitlet("init");
		String wugFileName = create_Wug_File();
		String commitMessage2_master = "added wug file: 0";
		gitlet("add", wugFileName);
		gitlet("commit", commitMessage2_master);											// first commit made 
		// update wug.txt file 
		String wugText_UPDATED = "This is a wug 1";
		createFile(wugFileName, wugText_UPDATED);
		gitlet("add", wugFileName);
		String commitMessage3_master = commit("modified wug file: 1");						// second commit made 

		// Stage 2: make a new branch, branch off, and modify wug.txt and make commits 
		String branch1 = "branch1";
		gitlet("branch", branch1);
		gitlet("checkout", branch1);
		wugText_UPDATED = "This is a wug in branch1: 1";
		createFile(wugFileName, wugText_UPDATED);
		gitlet("add", wugFileName);
		String commitMessage4_branch1 = commit("modified wug file in branch1: 1");
		wugText_UPDATED = "This is a wug in branch1: 2";
		createFile(wugFileName, wugText_UPDATED);
		gitlet("add", wugFileName);
		String commitMessage5_branch1 = commit("modified wug file in branch1: 2");
		// log in branch1 and the log should have commitMessages 4 and 5 
		String[] log_BRANCH_1 = extractCommitMessages(gitlet("log"));
		System.out.println();
		System.out.println("Printing log from branch 1");
		printLOG(log_BRANCH_1);

		// checkout back to master and log in master, it should be different from branch_1 
		gitlet("checkout", "master");
		String[] log_MASTER = extractCommitMessages(gitlet("log"));
		System.out.println();
		System.out.println("Printing log from master");
		printLOG(log_MASTER);

		// make sure that log_BRANCH_1 != log_MASTER
		assertFalse(Arrays.equals(log_BRANCH_1, log_MASTER));

		// STAGE 3: reset from branch_1 to the second commit made : message is commitMessage3_master
		gitlet("checkout", branch1);
		// need to get ID of commit2 
		System.out.println();
		System.out.println("GETTING COMMIT2(commitMessage3_master) ID");
		String work_dir = System.getProperty("user.dir");
		System.out.println("This is our work_dir: "+ work_dir);
		System.out.println();
		Gitlet g = Gitlet.deserialize(work_dir);
		Integer commit2_ID = g.getCommitsByMessage().get(commitMessage3_master).element();			// gets the ID for commit made 
		System.out.println("Printing the id we're resetting to");
		System.out.println(commit2_ID);

		System.out.println(">>>>>>>>>> RESETTING >>>>>>>>>>");
		gitlet("reset", commit2_ID.toString());
		System.out.println(">>>>>>>>>> RESETTED >>>>>>>>>>");
		// now branch1 is pointed to the second commit made (excluding initial commit)
		// check that branch1 log is same as the log in MASTER recorded before 
		String[] log_BRANCH_1_AFTER_RESET = extractCommitMessages(gitlet("log"));
		printLOG(log_BRANCH_1_AFTER_RESET);
		assertFalse(Arrays.equals(log_BRANCH_1, log_BRANCH_1_AFTER_RESET));
		assertTrue(Arrays.equals(log_BRANCH_1_AFTER_RESET, log_MASTER));




		end_TEST("testReset");
	}
	/**
	 * Test that merge will generate a .conflicted file if a file has been
	 * modified in both branches since the split point.
	 */

	@Test 
	public void testMergeConflict() {
		start_TEST("testMergeConflict");
		gitlet("init");
		// account for initial commit  
		String commitMessage1 = "initial commit";

		// create and add a file: wug.txt 
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);

		// create and add a file: cat.txt 
		String catFileName = TESTING_DIR + "cat.txt";
		String catText = "This is a cat.";
		createFile(catFileName, catText);
		gitlet("add", catFileName);

		//commit 1
		String commitMessage2 = "added wug and cat";
		gitlet("commit", commitMessage2);

		// create another branch called branch_1
		String branch_Name1 = "branch_1";
		gitlet("branch", branch_Name1);	
		
		gitlet("checkout",branch_Name1);

		//update the file.
		String newCatText = "This is modified cat";
		writeFile(catFileName, newCatText); 

		//add
		gitlet("add", catFileName);

		// commit 2 in branch_1
		String commitMessage3 = "wug is not modified, cat is modified";
		gitlet("commit", commitMessage3);

		// go back to master 
		gitlet("checkout", "master"); // so cat got back to the original one. 

		// create another branch called branch_2
		String branch_Name2 = "branch_2";
		gitlet("branch", branch_Name2);	
		gitlet("checkout",branch_Name2);

		//add (did not update the file)
		String dogFileName = TESTING_DIR + "dog.txt";
		String dogText = "This is a dog.";
		createFile(dogFileName, dogText);
		gitlet("add", dogFileName);
		String newCatText2 = "This is other modified cat";
		writeFile(catFileName, newCatText2); 
		gitlet("add", catFileName); // ADDING THE SAME FILE. BUT gitlet RECOGNIZES AS IF IT'S MODIFIED.

		//commit 3 in branch_2
		String commitMessage4 = "wug and cat are still tracked. dog is newly added";
		gitlet("commit", commitMessage4);

		//MERGE branch_1 : given branch(branch_1) modified the file but current branch(branch_2) did not modify the file.
		gitlet("merge", branch_Name1);
		
		//see conflicted file exists
		File f = new File(TESTING_DIR+"/"+"cat.txt.conflicted");
		assertTrue(f.exists()); // Is there a conflicted file?????
		
		end_TEST("testMergeConflict");
	}

/**
	 * Tests the first case on merge
	 * Test that merge will commit with files from the other branch if those files had been modified
	 *  in the other branch but not in the current branch since the split point.
	 */
	@Test
	public void testMergeCommits() {
		start_TEST("testMergeCommits");
		gitlet("init");
		// account for initial commit  
		String commitMessage1 = "initial commit";

		// create and add a file: wug.txt 
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);

		// create and add a file: cat.txt 
		String catFileName = TESTING_DIR + "cat.txt";
		String catText = "This is a cat.";
		createFile(catFileName, catText);
		gitlet("add", catFileName);

		//commit 1
		String commitMessage2 = "added wug and cat";
		gitlet("commit", commitMessage2);

		// create another branch called branch_1
		String branch_Name1 = "branch_1";
		gitlet("branch", branch_Name1);	
		
		gitlet("checkout", branch_Name1);

		//update the file.
		String newCatText = "This is modified cat";
		writeFile(catFileName, newCatText); 
		//add
		gitlet("add", catFileName);

		// commit 2 in branch_1
		String commitMessage3 = "wug is not modified, cat is modified";
		gitlet("commit", commitMessage3);

		// go back to master 
		gitlet("checkout", "master"); // so cat got back to the original one. 

		// create another branch called branch_2
		String branch_Name2 = "branch_2";
		gitlet("branch", branch_Name2);	
		gitlet("checkout", branch_Name2);	


		//add (did not update the file)
		String dogFileName = TESTING_DIR + "dog.txt";
		String dogText = "This is a dog.";
		createFile(dogFileName, dogText);
		gitlet("add", dogFileName);

		//commit 3 in branch_2
		String commitMessage4 = "wug and cat are still tracked. dog is newly added";
		gitlet("commit", commitMessage4);
		
		//MERGE branch_1 : given branch(branch_1) modified the file but current branch(branch_2) did not modify the file.
		gitlet("merge", branch_Name1);

		// check the merged message "Merged 'current' with 'given'".
		String[] log_BRANCH_2 = extractCommitMessages(gitlet("log"));
		String mergedMessage = log_BRANCH_2[0];
		System.out.println(mergedMessage);
		assertEquals(mergedMessage,"Merged "+branch_Name2+" with "+branch_Name1+".");

		// check ID first
		// need to get the files in the merged commit 
		String work_dir = System.getProperty("user.dir");
		Gitlet g = Gitlet.deserialize(work_dir);
		Integer commit_ID = g.getCommitsByMessage().get(mergedMessage).element(); //get ID
		Commit mergedCommit = g.getMyCommits(commit_ID); // get merged commit
		ArrayList<String> fileNames=new ArrayList<String>();
		
		Set<String> myCommitsSet = mergedCommit.getMyTracked().keySet(); 
        Iterator<String> iter2 = myCommitsSet.iterator();
        while (iter2.hasNext()){
        	String a = iter2.next();
        	gitlet("checkout",((Integer)commit_ID).toString(), a);
        	File b = mergedCommit.getMyTracked().get(a);
        	List<String> one_string;
			try {
				one_string = Files.readAllLines(b.toPath());
				String readline="";
				for (String line : one_string) {
					readline+=line;   
	            }
				fileNames.add(readline);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        System.out.println(fileNames);
		

		assertTrue(fileNames.contains(wugText));
		assertTrue(fileNames.contains(newCatText));
		assertTrue(fileNames.contains(dogText));
		
		
		

		end_TEST("testMergeCommits");
	}
	
	@Test
	public void testRebase1() {
		start_TEST("testRebase1");

		gitlet("init");
		// account for initial commit  
		String commitMessage1 = "initial commit";

		// create and add a file: wug.txt 
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug.";
		createFile(wugFileName, wugText);
		gitlet("add", wugFileName);

		// create and add a file: cat.txt 
		String catFileName = TESTING_DIR + "cat.txt";
		String catText = "This is a cat.";
		createFile(catFileName, catText);
		gitlet("add", catFileName);

		//commit 1
		String commitMessage2 = "added wug and cat";
		gitlet("commit", commitMessage2);

		// create another branch called branch_1
		String branch_Name1 = "branch_1";
		gitlet("branch", branch_Name1);	
		gitlet("checkout",branch_Name1);

		//update the file.
		String newCatText = "This is modified cat";
		writeFile(catFileName, newCatText); 
		//add
		gitlet("add", catFileName);

		// commit 2 in branch_1
		String commitMessage3 = "wug is not modified, cat is modified";
		gitlet("commit", commitMessage3);
		
		String newWugText = "This is modified wug";
		writeFile(wugFileName, newWugText); 
		//add
		gitlet("add", wugFileName);
		
		String commitMessage4 = "wug is modified, cat is not modified";
		gitlet("commit", commitMessage4);
		
		String racoonFileName = TESTING_DIR + "racoon.txt";
		String racoonText = "This is a racoon.";
		createFile(racoonFileName, racoonText);
		gitlet("add", racoonFileName);
				
		String commitMessage5 = "racoon is newly commited";
		gitlet("commit", commitMessage5);

		// go back to master 
		gitlet("checkout", "master"); // so cat got back to the original one. 

		// create another branch called branch_2
		String branch_Name2 = "branch_2";
		gitlet("branch", branch_Name2);	
		gitlet("checkout", branch_Name2);

		//add (did not update the file)
		String dogFileName = TESTING_DIR + "dog.txt";
		String dogText = "This is a dog.";
		createFile(dogFileName, dogText);
		gitlet("add", dogFileName);
		
		String commitMessage6 = "dog is newly commited";
		gitlet("commit", commitMessage6);

		//Rebase
		gitlet("rebase",branch_Name1);
		
		//check log (check the messages of replayed commits after the head commit are same as branch_2's orginal commit messages.


		String[] log_BRANCH_1 = extractCommitMessages(gitlet("log"));
		System.out.println("IN FOR LOOP");
		for (String commit : log_BRANCH_1) {
			System.out.println(commit);
		}
		
		String dogMessage1 = log_BRANCH_1[0];
		assertEquals(dogMessage1, commitMessage6);
		
		String racoonMessage1 = log_BRANCH_1[1];
		assertTrue(racoonMessage1.equals(commitMessage5));
		
		String modifiedwugMessage1 = log_BRANCH_1[2];
		assertTrue(modifiedwugMessage1.equals(commitMessage4));
		
		String modifiedcatMessage1 = log_BRANCH_1[3];
		assertTrue(modifiedcatMessage1.equals(commitMessage3));
		
		String splitMessage1 = log_BRANCH_1[4];
		assertTrue(splitMessage1.equals(commitMessage2));
		
		//check log (check the original messages are still there)
		gitlet("checkout", branch_Name2);

		
		String[] log_BRANCH_2 = extractCommitMessages(gitlet("log"));
		System.out.println("IN FOR LOOP");
		for (String commit : log_BRANCH_2) {
			System.out.println(commit);
		}
		String dogMessage = log_BRANCH_2[0];
		assertEquals(dogMessage, commitMessage6);
		
		String splitMessage = log_BRANCH_2[1];
		assertEquals(commitMessage5, splitMessage);

		
	
		
		end_TEST("testRebase1");
	}
	
		@Test
	public void testRebase2(){
		start_TEST("testRebase2");
		gitlet("init");
		String commitMessage0 = "initial commit";
		
		String aFileName = TESTING_DIR + "A.txt";
		String aText = "A";
		createFile(aFileName, aText);
		gitlet("add", aFileName);
		
		String bFileName = TESTING_DIR + "B.txt";
		String bText = "B";
		createFile(bFileName, bText);
		//gitlet("add", bFileName);
		
		String cFileName = TESTING_DIR + "C.txt";
		String cText = "C";
		createFile(cFileName, cText);
		gitlet("add", cFileName);
		
		String dFileName = TESTING_DIR + "D.txt";
		String dText = "D";
		createFile(dFileName, dText);
		gitlet("add", dFileName);
		
		String eFileName = TESTING_DIR + "e.txt";
		String eText = "e";
		createFile(eFileName, eText);
		gitlet("add", eFileName);
		
		String commitMessage1 = "Split_Point";
		gitlet("commit", commitMessage1);
		
		String branch1Name = "branch1";
		gitlet("branch", branch1Name);
		
		String commitMessage2 = "PARENT_OF_GIVENLEAF";
		gitlet("rm", dFileName);
		gitlet("add", bFileName);
		gitlet("commit", commitMessage2);
		
		String commitMessage3 = "THIS_IS_GIVENLEAF";
		gitlet("rm", cFileName);
		String dModText = "Modified D";
		createFile(dFileName, dModText);
		gitlet("add", dFileName);
		gitlet("commit", commitMessage3);
		
		gitlet("checkout", branch1Name);
		
		String commitMessage4= "PARENT_OF_CURRENTLEAF";
		gitlet("add", bFileName);
		gitlet("commit", commitMessage4);
		
		String commitMessage5 = "THIS_IS_CURRENTLEAF";
		String eModText = "Modified E";
		createFile(eFileName, eModText);
		gitlet("add", eFileName);
		gitlet("commit", commitMessage5);
		
		//do some tests here to make sure we have the right structure
		
		gitlet("rebase", "master");
		//gitlet("checkout", branch1Name);
		
		//check that in current commit (6) the versions are the following: A, B, D*, E*, [C does not exist]
		
		gitlet("checkout", aFileName);
		assertTrue(getText(aFileName).equals(aText));
		
		gitlet("checkout", bFileName);
		assertTrue(getText(bFileName).equals(bText));
		
		gitlet("checkout", cFileName); //TODO check that this prints the error message
		
		gitlet("checkout", dFileName);
		assertTrue(getText(dFileName).equals(dModText));
		
		gitlet("checkout", eFileName);
		assertTrue(getText(eFileName).equals(eModText));
		
		//check that in commit 7 the versions are the following: A, B, D*, E   [C does not exist]
		
		gitlet("checkout", "7", aFileName);
		assertTrue(getText(aFileName).equals(aText));
		
		gitlet("checkout", "7", bFileName);
		assertTrue(getText(bFileName).equals(bText));
		
		gitlet("checkout", "7", cFileName); //TODO check that this prints the error message
		
		gitlet("checkout", "7", dFileName);
		assertTrue(getText(dFileName).equals(dModText));
		
		gitlet("checkout", "7", eFileName);
		assertTrue(getText(eFileName).equals(eText));
		
		//check that log goes 6: CurrentLeaf, 7: ParentofCurrentLeaf, 3,2,1,0
		//check that global-log contains commits 0-7
		//check that checking out to master and then log gives you: 3, 2, 1, 0
		//check that checking out to branch1 and then log gives you: 6, 7, 3, 2, 1
		
		gitlet("log");
		gitlet("global-log");
		gitlet("status");
		
		end_TEST("testRebase2");
	}
		
		//TODO please note that I did not add any assert statements.
	
	
	

	private static void start_TEST(String methodName) {
		System.out.println();
		System.out.println();
		System.out.println("============================================================================================");
		System.out.println("START TEST: " + methodName);
		System.out.println("============================================================================================");
	}

	private static void end_TEST(String methodName) {
		System.out.println();
		System.out.println();
		System.out.println("============================================================================================");
		System.out.println("END TEST: " + methodName);
		System.out.println("============================================================================================");		
	}

	private static String create_Wug_File() {
		String wugFileName = TESTING_DIR + "wug.txt";
		String wugText = "This is a wug 0";
		createFile(wugFileName, wugText);
		return wugFileName;
	}

	private static String commit(String message) {
		gitlet("commit", message);
		return message;
	}

	private static void printLOG(String[] log_string) {
		System.out.println();
		System.out.println();
		System.out.println("Printing log messages");
		System.out.println();
		int i = 1;
		for (String message: log_string) {
			System.out.println(i);
			System.out.println("---------------");
			System.out.println(message);
			System.out.println("---------------");
		}
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