import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.HashMap;

public class Gitlet {
	private static CommitList commits;
	
	/**
	 * Loads the CommitList commits from the file commits.ser.
	 * If the file doesn't exist, then the list is set to null.
	 */
	private static void deserialize() {
        commits = null;
        String filename = "commits.ser";
        File commitFile = new File(filename);
        if (commitFile.exists()) {
            try {
                FileInputStream fileIn = new FileInputStream(commitFile);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                commits = (CommitList) objectIn.readObject();
                objectIn.close();
            } catch (IOException e) {
                System.out.println("IOException while deserializing commit list");
            } catch (ClassNotFoundException e) {
                System.out.println("ClassNotFoundException while deserializing commit list");
            }
        }
    }

	/**
	 * Saves the CommitList commits into the file commits.ser.
	 * If the list doesn't exist, doesn't do anything.
	 */
    private static void serialize() {
        if (commits == null) {
            return;
        }
        String filename = "commits.ser";
        try {
            File commitFile = new File(filename);
            FileOutputStream fileOut = new FileOutputStream(commitFile);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(commits);
            objectOut.close();
        } catch (IOException e) {
            System.out.println("Error while serializing commit list");
        }
    }
    
    /**
     * Run this method when the command is init. Creates a new gitlet version control system
     * in current directory. If there is already a gitlet version control system in the
     * current directory, aborts and prints an error message.
     */
    public static void runInit() {
    	File gitletDir = new File(".gitlet/");
    	if (gitletDir.exists()) {
			System.out.println("A gitlet version control system already exists in the current directory");
			return;
		}
		commits = new CommitList();
		gitletDir.mkdir();
    }
    
    /**
     * Run this method when the command is add. Stages a file to .gitlet directory.
     * If the file is marked for untracking, unmarks the file and does not stage it.
     * If the file does not exist, prints an error message and aborts.
     * @param tokens
     * 		string array that should only contain one string which is the filename to add
     */
    public static void runAdd(String[] tokens) {
    	if (tokens == null) {
			return;
		}
		if (tokens.length != 1) {
			return;
		}
		String filename = tokens[0];
		File addedFile = new File(filename);
		if (addedFile.exists()) {
			if (commits.isUntracked(filename)) {
				commits.unmarkUntracked(filename);
			} else {
				try {
					commits.stageFile(filename);
					File stagedFile = new File(".gitlet/" + filename);
					if (stagedFile.getParentFile() != null) {
						stagedFile.getParentFile().mkdirs();
					}
					Files.copy(addedFile.toPath(), stagedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("File does not exist.");
		}
    }
    
    /**
     * Run this method when the command is commit.Create a new directory with the name "Commit" and its id.
     * Call commit on the commit list and create a new commit node.
     * Copy all the files from the staging area into the new directory. Add the files that are tracked but not
     * staged into the commit nodes but do not create new copies. 
     * Delete all the files in the staging area.
     * Clear the staged and untracked HashSet for next commit call.
     * @param tokens
     * 			An array of strings that stores the commit messages;
     */
    public static void runCommit(String[] tokens) {
    	if (tokens == null) {
			System.out.println("Please enter a commit message.");
			return;
		}

		if (commits.nothingStaged() && commits.nothingUntracked()) {
			System.out.println("No changes added to the commit.");
			return;
		}
		
		String message = tokens[0];
		commits.commit(message);
		for (String file : commits.prevCommitFiles()) {
			if (!commits.isStaged(file) && !commits.isUntracked(file)) {
				File gitlet = commits.prevCommitGitlet(file);
				commits.commitFile(file, gitlet);
			}
		}
		
		for (String file : commits.stagedFiles()) {
			try {
				File stagedFile = new File(".gitlet/" + file);
				File gitlet = new File(".gitlet/Commit" + commits.size() + "/" + file);
				if (gitlet.getParentFile() != null) {
					gitlet.getParentFile().mkdirs();
				}
				Files.copy(stagedFile.toPath(), gitlet.toPath());
				commits.commitFile(file, gitlet);
				stagedFile.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		commits.clearFiles();
    	if (commits.isConflicted()) {
			commits.endConflicted();
		}

    }
    
    /**
     * Run this method when the command is rm. 
     * If the file with the given name is staged, unstage the file.
     * Otherwise, mark the file for untracking.
     * @param tokens
     * 			An array of strings that stores the name of the file to be removed. 
     */
    public static void runRemove(String[] tokens) {
    	if (tokens == null || tokens.length != 1) {
			return;
		}
		
		String filename = tokens[0];
		if (!commits.isStaged(filename) && !commits.isTracked(filename)) {
			System.out.println("No reason to remove the file.");
			return;
		}
		if (commits.isStaged(filename)) {
			File stagedFile = new File(".gitlet/" + filename);
			stagedFile.delete();
			commits.unstageFile(filename);
		} else {
			commits.untrackFile(filename);
		}
    }
    
    /**
     * Run this method when the command is checkout.
     * If the length of tokens is two, it will copy the version of the file stored in the commit node with the given id to the working directory.
     * If the length of tokens is one, it will check if the name stored in tokens is the name of a file or a name of a branch.
     * If the name is the file name, it copies the version of the file with that name stored in the current node to the working directory.
     * Otherwise it copies all the files stored in the branch with the given name to the working directory and change the current branch to this branch.
     * @param tokens
     * 			An array of strings that contains the name of the file or branch, or the commit id of a commit node and a file name.
     */
    public static void runCheckout(String[] tokens) {
    	if (tokens.length == 1) {
			if (commits.branchExists(tokens[0])) {
				if (commits.isConflicted()) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
					return;
				}
				String branchName = tokens[0];
				HashMap<String, File> branchFiles = commits.checkoutBranch(branchName);
				if (branchFiles == null) {
					System.out.println("No need to checkout the current branch.");
					return;
				}
				for (String file : branchFiles.keySet()) {
					try {
						File checkoutFile = branchFiles.get(file);
						File workingFile = new File(file);
						if (workingFile.getParentFile() != null) {
							workingFile.getParentFile().mkdirs();
						}
						Files.copy(checkoutFile.toPath(), workingFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else if (commits.isTracked(tokens[0])) {
				try {
					String filename = tokens[0];
					File checkoutFile = commits.checkoutFile(filename);
					File workingFile = new File(filename);
					if (workingFile.getParentFile() != null) {
						workingFile.getParentFile().mkdirs();
					}
					Files.copy(checkoutFile.toPath(), workingFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("File does not exist in the most recent commit, or no such branch exists.");
				return;
			}
		} else if (tokens.length == 2) {
			checkoutIDFilename(tokens);
		}
    }
    
    /**
     * Run this in runCheckout if there are two arguments given to the command checkout. The first argument
     * should represent the commit ID. The second argument should be a string of the filename.
     * @param tokens a String array containing the two arguments for the command checkout
     */
    public static void checkoutIDFilename(String[] tokens) {
    	try {
			int id = Integer.parseInt(tokens[0]);
			String filename = tokens[1];
			if (!commits.idExists(id)) {
				System.out.println("No commit with that id exists.");
				return;
			}
			File checkoutFile = commits.checkoutID(id, filename);
			if (checkoutFile == null) {
				System.out.println("File does not exist in that commit.");
				return;
			}
			File workingFile = new File(filename);
			if (workingFile.getParentFile() != null) {
				workingFile.getParentFile().mkdirs();
			}
			Files.copy(checkoutFile.toPath(), workingFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Run the method when the command is reset.
     * Copy all the files in the commit node with the given id to the working directory.
     * Reset the current branch's head to this commit node.
     * @param tokens
     * 			An array of strings that store the commit id of the commit node that we are resetting to;
     */
    public static void runReset(String[] tokens) {
    	if (commits.isConflicted()) {
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
    	int id = Integer.parseInt(tokens[0]);
		if (!commits.idExists(id)) {
			System.out.println("No commit with that id exists.");
			return;
		}
		HashMap<String, File> resetFiles = commits.reset(id);
		for (String file : resetFiles.keySet()) {
			try {
				File checkoutFile = resetFiles.get(file);
				File workingFile = new File(file);
				if (workingFile.getParentFile() != null) {
					workingFile.getParentFile().mkdirs();
				}
				Files.copy(checkoutFile.toPath(), workingFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
    
    /**
     * Run this method in runMerge when a file is modified in the given branch since
     * the split point, but not modified in the current branch since the split point (with
     * one exception: when a file is untracked in the current branch but its contents are
     * changed in the given branch). Overwrites the files in the working directory with
     * the versions in the given branch. It then stages these files.
     * @param filename a string of the name of the file
     * @param branchName a string of the name of the given branch
     */
    public static void firstCase(String filename, String branchName) {
    	HashMap<String, File> branchFiles = commits.branchFiles(branchName);
    	File branchFile = branchFiles.get(filename);
    	try {
			File workingFile = new File(filename);
			if (workingFile.getParentFile() != null) {
				workingFile.getParentFile().mkdirs();
			}
			Files.copy(branchFile.toPath(), workingFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			if (commits.isUntracked(filename)) {
				commits.unmarkUntracked(filename);
			}
			String[] file = {filename};
			runAdd(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Run this method in runMerge when a file has been modified in both the given branch
     * and the current branch. Creates a conflicted file that contains the contents of the
     * version of the file in the given branch.
     * @param filename a string of the name of the file
     * @param branchName a string of the name of the given branch
     */
    public static void thirdCase(String filename, String branchName) {
    	HashMap<String, File> branchFiles = commits.branchFiles(branchName);
    	File branchFile = branchFiles.get(filename);
    	try {
			File conflictedFile = new File(filename + ".conflicted");
			if (conflictedFile.getParentFile() != null) {
				conflictedFile.getParentFile().mkdirs();
			}
			Files.copy(branchFile.toPath(), conflictedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			commits.makeConflicted();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Run the method when the command is merge. Merges files from the given branch into the current
     * branch.
     * @param tokens a String array containing the string of the given branch name
     */
    public static void runMerge(String[] tokens) {
    	if (commits.isConflicted()) {
			System.out.println("Cannot do this command until the merge conflict has been resolved.");
			return;
		}
    	String branchName = tokens[0];
    	if (!commits.hasBranch(branchName)) {
    		System.out.println("A branch with that name does not exist.");
    		return;
    	}
    	if (commits.isCurrent(branchName)) {
    		System.out.println("Cannot merge a branch with itself.");
    		return;
    	}
    	HashMap<String, File> splitFiles = commits.splitFiles(branchName);
    	HashMap<String, File> branchFiles = commits.branchFiles(branchName);
    	HashMap<String, File> currentFiles = commits.headFiles();
    	for (String file: commits.allFiles()) {
    		File branchFile = branchFiles.get(file);
    		File currentFile = currentFiles.get(file);
    		File splitFile = splitFiles.get(file);
    		if (splitFile == null) {
    			if (currentFile == null && branchFile != null) {
    				firstCase(file, branchName); 				
    			} else if (currentFile != null && branchFile != null) {
    				thirdCase(file, branchName);
    			}
    		} else {
    			if (currentFile == null && branchFile != null) {
    				if (!CommitList.filesEqual(branchFile, splitFile)) {
    					firstCase(file, branchName);
    				}
    			} else if (currentFile != null && branchFile == null) {
    				if (CommitList.filesEqual(currentFile, splitFile)) {
    					if (commits.isStaged(file)) {
    						File stagedFile = new File(".gitlet/" + file);
    						stagedFile.delete();
    						commits.unstageFile(file);
    					}
    					commits.untrackFile(file);
    				}
    			} else if (currentFile != null && branchFile != null) {
    				if (!CommitList.filesEqual(branchFile, splitFile) && CommitList.filesEqual(currentFile, splitFile)) {
    					firstCase(file, branchName);
    				} else if (!CommitList.filesEqual(branchFile, splitFile) && !CommitList.filesEqual(currentFile, splitFile)) {
        				thirdCase(file, branchName);
    				}
    			}
    		}
    	}
    	if (commits.isConflicted()) {
    		System.out.println("Encountered a merge conflict.");
    	} else {
    		String[] message = {"Merged " + commits.currentBranch() + " with " + branchName + "."};
    		runCommit(message);
    	}
    }
    
    /**
     * Run this method when the command is rebase.
     * Find the split point of the branch with the given name and the current branch. 
     * Replay all the commits in the current branch from the head to the node before the split point.
     * Attach the copied commits to the head of the given branch.
     * @param tokens
     * 			An array of strings that stores the name of the branch we are trying to rebase with.
     */
    public static void runRebase(String[] tokens) {
    	String branchName = tokens[0];
    	if (!commits.hasBranch(branchName)) {
    		System.out.println("A branch with that name does not exist.");
    		return;
    	}
    	if (commits.isCurrent(branchName)) {
    		System.out.println("Cannot rebase a branch onto itself.");
    		return;
    	}
    	
    	if (commits.isHistory(branchName)) {
    		commits.changeHead(branchName);
    		System.out.println("Already up-to-date.");
    		return;
    	}
    	commits.rebase(branchName);
    }
    
    /**
     * Run this method whenever a command other than init is given. 
     * This method deals with all the commands and checks which other method should be called using the switch statement.
     * @param command
     * 			A string that is the command given to the Gitlet by the user.
     * @param tokens
     * 			An array of strings that stores the argument given to the command.
     */
    public static void runCommand(String command, String[] tokens) {
    	switch (command) {
			case "add":
				runAdd(tokens);
				break;
			case "commit":
				runCommit(tokens);
				break;
			case "rm":
				runRemove(tokens);
				break;
			case "log":
				commits.printLog();
				return;
			case "global-log":
				commits.printGlobalLog();
				return;
			case "find":
				commits.printFind(tokens[0]);
				return;
			case "status":
				commits.printStatus();
				return;
			case "checkout":
				runCheckout(tokens);
				break;
			case "branch":
				if (commits.isConflicted()) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
					return;
				}
				commits.makeBranch(tokens[0]);
				break;
			case "rm-branch":
				if (commits.isConflicted()) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
					return;
				}
				commits.removeBranch(tokens[0]);
				break;
			case "reset":
				runReset(tokens);
				break;
			case "merge":
				runMerge(tokens);
				break;
			case "rebase":
				runRebase(tokens);
				break;
			default:
				System.out.println("No command with that name exists.");
				return;
    	}
    }

    /**
     * The main method runs when the file is run. 
     * The main method calls runInit which creates a new commit list when the command is init.
     * In other cases it deserialize the stream created by serialization in the directory and assign it to the commits variable.
     * Then it calls runCommand to deal with the command and its arguments.
     * At the end of the main method, the commit list is serialized and stored into the file in the directory.
     * @param args
     */
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please enter a command.");
			return;
		}
		String command = args[0];
		String[] tokens = null;
		
		if (args.length > 1) {
			tokens = new String[args.length - 1];
			System.arraycopy(args, 1, tokens, 0, args.length - 1);
		}
		
		File gitletDir = new File(".gitlet/");
		deserialize();
		if (command.equals("init") && tokens == null) {
			runInit();
		} else if (gitletDir.exists()) {
			runCommand(command, tokens);
		} else {
			return;
		}
		serialize();
	}
}
