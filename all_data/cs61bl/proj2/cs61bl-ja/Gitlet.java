CHANGE#3

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class Gitlet {
    /** The path of the gitlet directory */
    private static final Path GITLET_PATH = Paths.get(".gitlet");

    /**
     * The path of the list of changed files
     * The file contains paths to changed files, relative to the repository, staging, or commit roots, one to a line.
     * Example:
     * nuclear_launch_codes.txt
     * cat.gif
     */
    private static final Path CHANGED_FILES_PATH = GITLET_PATH.resolve("changedFiles");

    /**
     * The path of the file containing the paths of files to be removed in the next commit
     * The file follows the same format as the file referred to by CHANGED_FILES_PATH.
     */
    private static final Path REMOVED_FILES_PATH = GITLET_PATH.resolve("removedFiles");

    /** The path to the file whose presence indicates that gitlet is currently in a conflicted state */
    private static final Path CONFLICTED_MARKER = GITLET_PATH.resolve("conflicted");

    /** The path of the staging area, the directory where files are stored before they are committed */
    private static final Path STAGING_AREA_PATH = GITLET_PATH.resolve("staging");

    /** The path of gitlet's serialized CommitTree in the gitlet directory */
    private static final Path COMMIT_TREE_PATH = GITLET_PATH.resolve("gitletTree.ser");

    /**
     * The path to the file that stores the location in the commit tree that the current outside files are based on
     * This file follows the following format:
     * The file is two lines long. The first line is either "branch" or "id"
     * If the first line is "branch", the second line is the name of the current branch. (gitlet is at a branch)
     * If the first line is "id", the second line is the id of the current commit. (gitlet is not at a branch)
     * Examples:
     * branch  |  id
     * master  |  5
     */
    private static final Path TREE_LOCATION_PATH = GITLET_PATH.resolve("treeLocation");

    /** The path containing the commits in the gitlet directory */
    private static final Path COMMITS_PATH = GITLET_PATH.resolve("commits");

    /** The path to the working directory: the directory in which gitlt is tracking files */
    private static final Path WORKING_DIR_PATH = Paths.get(".");

    public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please enter a command.");
			return;
		}
		switch (args[0]) {
		case "init":
			init();
			break;
		case "add":
			if (Files.exists(GITLET_PATH)) {
				if (args.length != 2) {
					System.out.println("java Gitlet add [file name]");
					return;
				}
				add(Paths.get(args[1]));
			} else {
				System.out.println("Cannot complete command, gitlet folder does not exist.");
			}
			break;
		case "commit":
			if (Files.exists(GITLET_PATH)) {
				if (args.length != 2) {
					System.out.println("java Gitlet commit [message]");
					return;
				}
				commit(args[1]);
			} else {
				System.out.println("Cannot complete command, gitlet folder does not exist.");
			}
			break;
		case "rm":
			if (Files.exists(GITLET_PATH)) {
				if (args.length != 2) {
					System.out.println("java Gitlet rm [file name]");
					return;
				}
				remove(Paths.get(args[1]));
			} else {
				System.out.println("Cannot complete command, gitlet folder does not exist.");
			}
			break;
		case "log":
			if (Files.exists(GITLET_PATH)) {
				log();
			} else {
				System.out.println("Cannot complete command, gitlet folder does not exist.");
			}
			break;
		case "global-log":
			if (Files.exists(GITLET_PATH)) {
				globalLog();
			} else {
				System.out.println("Cannot complete command, gitlet folder does not exist.");
			}
			break;
		case "find":
			if (Files.exists(GITLET_PATH)) {
				if (args.length != 2) {
					System.out.println("java Gitlet find [commit message]");
					return;
				}
				find(args[1]);
			} else {
				System.out.println("Cannot complete command, gitlet folder does not exist.");
			}
			break;
		case "status":
			if (Files.exists(GITLET_PATH)) {
				status();
			} else {
				System.out.println("Cannot complete command, gitlet folder does not exist.");
			}
			break;
		case "checkout":
			if (Files.exists(GITLET_PATH)) {
				if (args.length == 2) {
					dispatchCheckout(args[1]);
				} else if (args.length == 3) {
					checkout(Integer.parseInt(args[1]), Paths.get(args[2]));
				} else {
					System.out.println("java Gitlet checkout [file name]");
					System.out.println("java Gitlet checkout [commit id] [file name]");
					System.out.println("java Gitlet checkout [branch name]");
				}
			} else {
				System.out.println("Cannot complete command, gitlet folder does not exist.");
			}
			break;
		case "branch":
			if (Files.exists(GITLET_PATH)) {
				if (Files.exists(CONFLICTED_MARKER)) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
					return;
				}
				if (args.length != 2) {
					System.out.println("java Gitlet branch [branch name]");
					return;
				}  
				branch(args[1]);
			} else {
				System.out.println("Cannot complete command, gitlet folder does not exist.");
			}
			break;
		case "rm-branch":
			if (Files.exists(GITLET_PATH)) {
				if (Files.exists(CONFLICTED_MARKER)) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
					return;
				}
				if (args.length != 2) {
					System.out.println("java Gitlet rm-branch [branch name]");
					return;
				}
				removeBranch(args[1]);
			} else {
				System.out.println("Cannot complete command, gitlet folder does not exist.");
			}
			break;
		case "reset":
			if (Files.exists(GITLET_PATH)) {
				if (Files.exists(CONFLICTED_MARKER)) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
					return;
				}
				if (args.length != 2) {
					System.out.println("java Gitlet reset [commit id]");
					return;
				}
				reset(Integer.parseInt(args[1]));
			} else {
				System.out.println("Cannot complete command, gitlet folder does not exist.");
			}
			break;
		case "merge":
			if (Files.exists(GITLET_PATH)) {
				if (Files.exists(CONFLICTED_MARKER)) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
					return;
				}
				if (args.length != 2) {
					System.out.println("java Gitlet merge [branch name]");
					return;
				}
				merge(args[1]);
			} else {
				System.out.println("Cannot complete command, gitlet folder does not exist.");
			}
			break;
		case "rebase":
			if (Files.exists(GITLET_PATH)) {
				if (Files.exists(CONFLICTED_MARKER)) {
					System.out.println("Cannot do this command until the merge conflict has been resolved.");
					return;
				}
				if (args.length != 2) {
					System.out.println("java Gitlet rebase [branch name]");
					return;
				}
				rebase(args[1]);
			} else {
				System.out.println("Cannot complete command, gitlet folder does not exist.");
			}
			break;
		default:
			System.out.println("No command with that name exists.");
		}
	}

    /**
     * Creates a new Gitlet repository in the current directory.
     */
    private static void init() {
        if (Files.exists(GITLET_PATH)) {
            System.out.println("A gitlet version control system already exists in the current directory.");
            return;
        }

        CommitTree tree = new CommitTree();
        // Create .gitlet structure
        try {
            Files.createDirectory(GITLET_PATH);
            Files.createDirectory(STAGING_AREA_PATH);
            Files.createFile(CHANGED_FILES_PATH);
            Files.createFile(REMOVED_FILES_PATH);
            Files.createFile(TREE_LOCATION_PATH);
            Path commitsDir = Files.createDirectory(COMMITS_PATH);
            Files.createDirectory(commitsDir.resolve(Integer.toString(tree.commitAtBranch("master").getId())));
        } catch (IOException e) {
            System.err.println("Could not create commits directory");
            recursiveDelete(GITLET_PATH);
            return;
        }

        try (PrintWriter writer = new PrintWriter(TREE_LOCATION_PATH.toFile())) {
            writer.println("branch");
            writer.println("master");
        } catch (FileNotFoundException e) {
            throw new AssertionError("We just made this file.");
        }

        // Create fresh CommitTree and save it in .gitlet
        try {
            saveCommitTree(tree);
        } catch (IOException e) {
            System.err.println("Could not create gitlet tree file: " + e);
            recursiveDelete(GITLET_PATH);
        }
    }

    /**
     * Saves the gitlet commit tree in its current state to COMMIT_TREE_PATH.
     * @param tree the tree to save
     * @throws IOException
     */
    private static void saveCommitTree(CommitTree tree) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(COMMIT_TREE_PATH.toFile()))) {
            oos.writeObject(tree);
        }
    }

    /**
     * Load the commit tree for the gitlet repository at the current location.
     * @return the gitlet commit tree
     * @throws IOException
     * @throws IllegalStateException if the file at COMMIT_TREE_PATH contains an object that is not a CommitTree
     */
    private static CommitTree loadCommitTree() throws IOException {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(COMMIT_TREE_PATH.toFile()))) {
            Object tree = ois.readObject();
            if (! (tree instanceof CommitTree))
                throw new IllegalStateException("Object from " + COMMIT_TREE_PATH + " was not a CommitTree!");
            return (CommitTree) tree;
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Object from " + COMMIT_TREE_PATH + " was of an unknown class!", e);
        }
    }

    /**
     * Adds a file as it currently exists in the filesystem to be committed in the next commit.
     * @param toAdd the path of the file to add
     */
    private static void add(Path toAdd) {
        if (Files.exists(toAdd) && toAdd.toAbsolutePath().startsWith(WORKING_DIR_PATH.toAbsolutePath())) {
			System.out.println("File does not exist.");
            return;
        }
        if (Files.isDirectory(toAdd)) {
            // FIXME make directories work. This will take a bit of thought.
            System.out.println("This doesn't work yet.");
            return;
        }

        // If the file was marked for removal, unmark it...
        try {
            Set<Path> removedFiles = loadPathSet(REMOVED_FILES_PATH);
            if (removedFiles.contains(toAdd)) {
                removedFiles.remove(toAdd);
                savePathSet(removedFiles, REMOVED_FILES_PATH);
                return;
            }
        } catch (IOException e) {
            System.err.println("Could not check list of removed files: " + e);
            return;
        }

        // ...Otherwise, add it...
        try {
            copyNested(WORKING_DIR_PATH, STAGING_AREA_PATH, toAdd);
        } catch (IOException e) {
            System.err.println("Could not copy file to staging area: " + e);
            return;
        }

        /// ...and update the list of changed files
        try {
            Set<Path> stagedFiles = loadPathSet(CHANGED_FILES_PATH);
            stagedFiles.add(toAdd);
            savePathSet(stagedFiles, CHANGED_FILES_PATH);
        } catch (IOException e) {
            // FIXME if the list of files can't be updated, remove the new one from staging
            System.err.println("Could not update list of staged files: " + e);
        }
    }
    
    /**
     * If the given file was staged, remove it from staging. Otherwise, if it was tracked, mark it for untracking.
     * @param toRemove the path of the file to remove
     */
    private static void remove(Path toRemove)  {
        // Load files
        CommitTree tree;
        CommitTree.Commit currentCommit;
        Set<Path> stagedFiles;
        Set<Path> removedFiles;
        try {
            tree = loadCommitTree();
            currentCommit = getCurrentCommit(tree);
            stagedFiles = loadPathSet(CHANGED_FILES_PATH);
            removedFiles = loadPathSet(REMOVED_FILES_PATH);
        } catch (IOException e) {
            System.err.println("Could not load required files: " + e);
            return;
        }
        if (stagedFiles.contains(toRemove)) {
            // Remove file from staging area if it was there
            stagedFiles.remove(toRemove);
            try {
                savePathSet(stagedFiles, CHANGED_FILES_PATH);
                Files.delete(STAGING_AREA_PATH.resolve(toRemove));
            } catch (IOException e) {
                System.err.println("Could not delete file from staging area: " + e);
            }
        } else if (currentCommit.getFiles().contains(toRemove)) {
            // Otherwise, if it was tracked, mark it for untracking
            removedFiles.add(toRemove);
            try {
                savePathSet(removedFiles, REMOVED_FILES_PATH);
            } catch (IOException e) {
                System.err.println("Could bot save list of removed files: " + e);
            }
        } else {
			System.out.println("No reason to remove the file.");
        }
    }

    /**
     * Creates a new branch with a head at the current position, and makes it the current branch.
     * Prints an error if a branch with that name exists.
     * @param branchName the branch to add
     */
    private static void branch(String branchName) {
        CommitTree tree;
        CommitTree.Commit currentCommit;
        try {
            tree = loadCommitTree();
            currentCommit = getCurrentCommit(tree);
        } catch (IOException e) {
            System.err.println("Could not load required files: " + e);
            return;
        }
        if (tree.branchNames().contains(branchName)) {
            System.out.println("A branch with that name already exists.");
            return;
        }
        try {
            tree.addBranch(branchName, currentCommit);
            saveCommitTree(tree);
        } catch (IOException e) {
            System.err.println("Could not save modified files: " + e);
        }
    }

    /**
     * Removes the branch with the given name, if it exists. Prints an error if the branch does not exist.
     * @param branchName the name of the branch to delete
     */
    private static void removeBranch(String branchName) {
        CommitTree tree;
        Optional<String> currentBranch;
        try {
            currentBranch = getCurrentBranch();
            tree = loadCommitTree();
        } catch (IOException e) {
            System.err.println("Could not load required files: " + e);
            return;
        }
        if (!tree.branchNames().contains(branchName)) {
            System.out.println("No branch with that name already exists.");
            return;
        }
        if (currentBranch.isPresent() && currentBranch.get().equals(branchName)) {
            System.out.println("Cannot remove the current branch.");
            return;
        }

        tree.removeBranch(branchName);
        try {
            saveCommitTree(tree);
        } catch (IOException e) {
            System.err.println("Could not save commit tree: " + e);
        }
    }

    /**
     * Makes a commit containing the currently staged files, with the given message.
     * @param message the message explaining the new commit
     */
    private static void commit(String message) {
        Optional<String> currentBranch;
        Set<Path> changedFiles;
        Set<Path> removedFiles;
        CommitTree tree;
        try {
            currentBranch = getCurrentBranch();
            if (!currentBranch.isPresent()) {
                System.out.println("There is no branch at the current commit. Try \"java gitlet branch BRANCHNAME\".");
                return;
            }
            changedFiles = loadPathSet(CHANGED_FILES_PATH);
            removedFiles = loadPathSet(REMOVED_FILES_PATH);
            tree = loadCommitTree();
            if (changedFiles.isEmpty() && removedFiles.isEmpty()) {
                System.out.println("No changes to commit.");
                return;
            }
        } catch (IOException e) {
            System.err.println("Couldn't load required files: " + e);
            return;
        }

        // Update the tree
        CommitTree.Commit newCommit = tree.addCommit(currentBranch.get(), message, changedFiles, removedFiles);

        try {
            // Update the files
            Path newCommitPath = COMMITS_PATH.resolve(Integer.toString(newCommit.getId()));
            Files.createDirectory(newCommitPath);
            for (Path changedFile : changedFiles)
                copyNested(STAGING_AREA_PATH, newCommitPath, changedFile);
            // Save the tree
            saveCommitTree(tree);
            // Clear the "changed files" file and the "removed files" file
            savePathSet(Collections.emptySet(), CHANGED_FILES_PATH);
            savePathSet(Collections.emptySet(), REMOVED_FILES_PATH);
            // Clear conflicted status
            Files.deleteIfExists(CONFLICTED_MARKER);
        } catch (IOException e) {
            System.err.println("Couldn't create new commit directory: " + e);
            System.err.println("Attempting to clean up.");
            recursiveDelete(COMMITS_PATH.resolve(Integer.toString(newCommit.getId())));
        }
    }

    /**
     * Helper function. Invokes the correct version of checkout
     * based on whether the given string is the name of a branch in the tree.
     * If it is, the branch checkout is invoked. Otherwise, the file version is invoked.
     * @param branchNameOrPathStr either a path string or the name of a branch.
     */
    private static void dispatchCheckout(String branchNameOrPathStr) {
        CommitTree tree;
        try {
            tree = loadCommitTree();
        } catch (IOException e) {
            System.err.println("Couldn't load commit tree.");
            return;
        }

        if (tree.branchNames().contains(branchNameOrPathStr)) {
            checkout(branchNameOrPathStr, tree);
        } else {
            checkout(Paths.get(branchNameOrPathStr), tree);
        }
    }

    /**
     * Checks out the branch with the given name.
     * Since this function is invoked by dispatchCheckout, which has to load the commit tree,
     * the commit tree is taken as a parameter to avoid loading it twice.
     * @param branchName the branch to check out
     * @param tree the commit tree
     */
    private static void checkout(String branchName, CommitTree tree) {
        Optional<String> currentBranch;
        try {
            currentBranch = getCurrentBranch();
            if (currentBranch.isPresent() && branchName.equals(currentBranch.get())) {
                System.out.println("No need to checkout the current branch.");
                return;
            }
        } catch (IOException e) {
            System.err.println("Could not load required files: " + e);
            return;
        }

        if (!tree.branchNames().contains(branchName)) {
            System.out.println("File does not exist in the most recent commit, or no such branch exists.");
            return;
        }

        // replace working directory files with files within this commit
        try {
            restoreCommitFiles(tree.commitAtBranch(branchName));
            saveCurrentBranch(branchName);
        } catch (IOException e) {
            System.err.println("Couldn't save required files: " + e);
        }
    }

    /**
     * Helper method to load all files from a commit and replace the files in the working directory with them.
     * @param commit the commit to take the files from
     * @throws IOException
     */
    private static void restoreCommitFiles(CommitTree.Commit commit) throws IOException {
        for (Map.Entry<Path, CommitTree.Commit> entry : commit.getLastFileRevisions().entrySet()) {
            Path file = entry.getKey();
            copyNested(getCommitPath(entry.getValue().getLastFileRevisions().get(file)), WORKING_DIR_PATH, file);
        }
    }

    /**
     * Checks out the most recent version of the given file
     * Since this function is invoked by dispatchCheckout, which has to load the commit tree,
     * the commit tree is taken as a parameter to avoid loading it twice.
     * @param toCheckout the path of the file to check out
     * @param tree the commit tree
     */
    private static void checkout(Path toCheckout, CommitTree tree) {
        CommitTree.Commit currentCommit;
        try {
            currentCommit = getCurrentCommit(tree);
        } catch (IOException e) {
            System.err.println("Could not load required files: " + e);
            return;
        }
        if (!currentCommit.getFiles().contains(toCheckout)) {
            System.out.println("File does not exist in the most recent commit.");
            return;
        }

        try {
            copyNested(getCommitPath(currentCommit.getLastFileRevisions().get(toCheckout)), WORKING_DIR_PATH, toCheckout);
        } catch (IOException e) {
            System.err.println("Couldn't copy the saved version of the file: " + e);
        }
    }

    // When commit ID and file are given
    private static void checkout(int commitId, Path toCheckout) {
        CommitTree tree;
        try {
            tree = loadCommitTree();
        } catch (IOException e) {
            System.err.println("Could not load required files: " + e);
            return;
        }

        // make sure the commit and file both exist
        CommitTree.Commit commitAtId = tree.getCommitById(commitId);
        if (commitAtId == null) {
            System.out.println("No commit with that id exists.");
            return;
        }
        if (!commitAtId.getFiles().contains(toCheckout)) {
            System.out.println("File does not exist in that commit.");
            return;
        }

        // perform overwrite
        try {
            copyNested(getCommitPath(commitAtId.getLastFileRevisions().get(toCheckout)), WORKING_DIR_PATH, toCheckout);
        } catch (IOException e) {
            System.err.println("Couldn't copy the saved version of the file: " + e);
        }
    }

    /**
     * Resets the working directory's state to the state from the commit with the given ID
     * @param commitId the ID of the commit to reset to
     */
    private static void reset(int commitId) {
        CommitTree tree;
        Optional<String> currentBranchName;
        try {
            tree = loadCommitTree();
            currentBranchName = getCurrentBranch();
        } catch (IOException e) {
            System.err.println("Could not load Commit Tree: " + e);
            return;
        }
        CommitTree.Commit toReset = tree.getCommitById(commitId);
        if (toReset == null) {
            System.out.println("No commit with that id exists.");
            return;
        }

        try {
            restoreCommitFiles(toReset);
            if (currentBranchName.isPresent())
                tree.moveBranch(currentBranchName.get(), toReset);
            saveCommitTree(tree);
        } catch (IOException e) {
            System.out.println("Couldn't save required files: " + e);
        }
    }

	/**
	 * Merges a given branch to the branch the user is currently checking out,
	 * although there are many different scenarios for this
	 * 1) If given branch and current branch have the same files
	 *   a. If only the given branch is modified, override those files in current branch
	 *   b. If only the current branch is modified, keep the files as they are
	 *   c. If both are modified, keep the files as they are in the current branch, then move the files
	 *   from given branch to working directory, rename their files to have ".conflicted" at the end
	 * @param toBranch is the given branch for merge
	 */
	private static void merge(String toBranch) {
		CommitTree.Commit currentCommit;
        CommitTree.Commit otherHead;
        CommitTree tree;
        Optional<String> currentBranch;
		try {
			tree = loadCommitTree();
			currentCommit = getCurrentCommit(tree);
			currentBranch = getCurrentBranch();
            if (!tree.branchNames().contains(toBranch)) {
                System.out.println("A branch with that name doesn't exist");
                return;
            }
			otherHead = tree.commitAtBranch(toBranch);
		} catch (IOException e) {
			System.err.println("Couldn't load required files: " + e);
			return;
		}

        if (!currentBranch.isPresent()) {
            System.out.println("There is no current branch to merge to");
            return;
        }

        CommitTree.Commit ancestor = currentCommit.findCommonAncestor(otherHead);
        Set<Path> commonFiles = new HashSet<>(currentCommit.getFiles());
        commonFiles.retainAll(otherHead.getFiles());
        Set<Path> conflicted;
        try {
            conflicted = commonFiles.stream().filter(path -> isConflict(currentCommit, otherHead, ancestor, path))
                                          .collect(Collectors.toSet());
        } catch (RuntimeException e) {
            System.err.println("Couldn't compare files: " + e.getCause());
            return;
        }

        // Find changed files in other branch: files that either don't exist in this branch, or
        // files that exist in both branches that were changed in different commits after branching
        Set<Path> otherBranchUpdatedFiles = otherHead.getLastFileRevisions().entrySet().stream()
                .filter(entry -> !currentCommit.hasFile(entry.getKey()) ||
                                (entry.getValue() != currentCommit.lastRevisionOf(entry.getKey()) &&
                                        entry.getValue() != ancestor.lastRevisionOf(entry.getKey()))
                ).map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        if (conflicted.isEmpty()) {
            // Add commit to tree
            CommitTree.Commit mergeCommit = tree.addCommit(currentBranch.get(),
                    "Merged " + toBranch + " with " + currentBranch.get() + ".", otherBranchUpdatedFiles, Collections.emptySet());

            // Create commit directory and save tree
            try {
                for (Path path : otherBranchUpdatedFiles)
                    copyNested(getCommitPath(otherHead.lastRevisionOf(path)), getCommitPath(mergeCommit), path);
                saveCommitTree(tree);
                restoreCommitFiles(mergeCommit);
            } catch (IOException e) {
                System.err.println("Couldn't save required files: " + e);
                System.err.println("Cleaning up");
                recursiveDelete(getCommitPath(mergeCommit));
            }
        } else {
            System.out.println("Encountered a merge conflict.");
            // move things to staging area
            // make changedFiles and conflictedFiles
            try {
                Set<Path> nonconflictingChanges = new HashSet<>();
                Files.createFile(CONFLICTED_MARKER);
                for (Path path : otherBranchUpdatedFiles) {
                    if (conflicted.contains(path)) {
                        // Copy conflicting version to working directory with ".conflicted" at the end of its name.
                        Files.createDirectories(WORKING_DIR_PATH.resolve(path).getParent());
                        String dotConflictedName = path.getFileName().toString() + ".conflicted";
                        Path dotConflicted =  WORKING_DIR_PATH.resolve(path).getParent().resolve(dotConflictedName);
                        Files.copy(getCommitPath(otherHead.lastRevisionOf(path)).resolve(path),
                                dotConflicted, StandardCopyOption.REPLACE_EXISTING);
                    } else {
                        // Copy non-conflicting file to staging area and add it to changedFiles
                        copyNested(getCommitPath(otherHead.lastRevisionOf(path)), STAGING_AREA_PATH, path);
                        // Also bring it out to the working directory
                        copyNested(getCommitPath(otherHead.lastRevisionOf(path)), WORKING_DIR_PATH, path);
                        nonconflictingChanges.add(path);
                    }
                }
                savePathSet(nonconflictingChanges, CHANGED_FILES_PATH);
            } catch (IOException e) {
                System.err.println("Couldn't save required files: " + e);
            }
        }
	}

    /**
     * Helper method for merge. Returns whether the given file would be a merge conflict.
     * @param currentHead the commit at the end of the branch to be merged to
     * @param targetHead the commit at the end of the branch to be merged from
     * @param ancestor the common ancestor of currentHead and targetHead, in order to avoid recalculation.
     * @param path the path to a file that exists in both currentHead and targetHead
     * @return whether the given file would cause a conflict
     */
    private static boolean isConflict(CommitTree.Commit currentHead, CommitTree.Commit targetHead,
                                      CommitTree.Commit ancestor, Path path) {
        // A file may be conflicted if:
        // Ancestor doesn't have file and descendants both do
        // Ancestor has file and ancestor and descendants all have different versions
        boolean mayConflict = !ancestor.hasFile(path) ||
                currentHead.lastRevisionOf(path) != ancestor.lastRevisionOf(path) ||
                targetHead.lastRevisionOf(path) != ancestor.lastRevisionOf(path);
        if (mayConflict) {
            try {
                List<String> currentLines =
                        Files.readAllLines(getCommitPath(currentHead.lastRevisionOf(path)).resolve(path));
                List<String> otherLines =
                        Files.readAllLines(getCommitPath(targetHead.lastRevisionOf(path)).resolve(path));
                if (ancestor.hasFile(path)) {
                    // Compare ancestor's file if it has it. If either new version is the same as the ancestor's,
                    // it's not a conflict.
                    List<String> ancestorLines =
                            Files.readAllLines(getCommitPath(ancestor.lastRevisionOf(path)).resolve(path));
                    return !currentLines.equals(otherLines) &&
                           !currentLines.equals(ancestorLines) &&
                           !otherLines.equals(ancestorLines);

                } else {
                    return !currentLines.equals(otherLines);
                }
            } catch (IOException e) {
                // kinda hackish, but required for lambda to work, since lambdas can't throw checked exceptions
                throw new RuntimeException(e);
            }
        }
        return false;
    }
    
    /**
     * "Rebases" the current branch to the given branch by
     * making copies of all commits from the current branch down to the point where it splits from destBranch,
     * with the same file changes and deletions, but places them at the end of destBranch.
     * @param destBranch the branch to rebase the current branch to
     */
    private static void rebase(String destBranch) {
        CommitTree tree;
        CommitTree.Commit currentCommit;
        Optional<String> currentBranch;
        try {
            tree = loadCommitTree();
            currentCommit = getCurrentCommit(tree);
            currentBranch = getCurrentBranch();
        } catch (IOException e) {
            System.err.println("Couldn't load required files: " + e);
            return;
        }
        if(!tree.branchNames().contains(destBranch)) {
            System.out.println("A branch with that name doesn't exist");
            return;
        }

        // Check whether the destination is valid
        CommitTree.Commit newParent = tree.commitAtBranch(destBranch);
        CommitTree.Commit ancestor = currentCommit.findCommonAncestor(newParent);
        if (ancestor == currentCommit) {
            System.out.println("Can't move a branch to itself");
            return;
        }
        if (ancestor == newParent) {
            System.out.println("Already up-to-date");
            return;
        }

        // Move the branch pointer up if the current commit is a descendant of the destination
        if (newParent.hasDescendant(currentCommit)) {
            tree.moveBranch(destBranch, newParent);
            return;
        }

        // Make a stack of commits to copy with this on the bottom, and the first commit after the ancestor on top
        Deque<CommitTree.Commit> commitsToCopy = new LinkedList<>();
        CommitTree.Commit commitToCopy = currentCommit;
        while (commitToCopy != ancestor) {
            commitsToCopy.push(commitToCopy);
            commitToCopy = commitToCopy.getParent();
        }

        CommitTree.Commit copiedChild = null;

            // Make copies of all commits from the stack, with parents starting at newParent
            List<Integer> copiedIds = new ArrayList<>(commitsToCopy.size()); // in case of failure, so we know what we have to delete
            while (!commitsToCopy.isEmpty()) {
                CommitTree.Commit next = commitsToCopy.pop();
                copiedChild = tree.addCommit(destBranch, next.getMessage(), next.getChangedFiles(), next.getRemovedFiles());
                Path newCommitPath = COMMITS_PATH.resolve(Integer.toString(copiedChild.getId()));
                Path oldCommitPath = COMMITS_PATH.resolve(Integer.toString(next.getId()));
                try {
                    Files.createDirectory(newCommitPath);
                    for (Path changedFile : next.getChangedFiles())
                        copyNested(oldCommitPath, newCommitPath, changedFile);
                    copiedIds.add(copiedChild.getId());
                } catch (IOException e) {
                    System.err.println("Couldn't create directories for new commits: " + e);
                    System.err.println("Cleaning up.");
                    for (Integer id : copiedIds)
                        recursiveDelete(COMMITS_PATH.resolve(id.toString()));
                    return;
                }
            }

        if (currentBranch.isPresent())
            tree.moveBranch(currentBranch.get(), copiedChild);

        try {
            saveCommitTree(tree);
        } catch (IOException e) {
            System.err.println("Couldn't save commit tree: " + e);
            return;
        }

        try {
            restoreCommitFiles(copiedChild);
        } catch (IOException e) {
            System.err.println("Couldn't restore new commit: " + e);
        }
    }

    /**
     * Prints a log of commits starting from the current commit and going back to the initial commit.
     */
    private static void log() {
        CommitTree tree;
        CommitTree.Commit toLog;
        try {
            tree = loadCommitTree();
            toLog = getCurrentCommit(tree);
        } catch (IOException e) {
            System.err.println("Could not load required files: " + e);
            return;
        }
        while (toLog != null) {
            System.out.println("===");
            System.out.println(toLog.toString());
            System.out.println();
            toLog = toLog.getParent();
        }
    }

    /**
     * Prints a log of every commit in the repository.
     */
    private static void globalLog() {
        CommitTree tree;
        try {
            tree = loadCommitTree();
        } catch (IOException e) {
            System.err.println("Could not load commit tree: " + e);
            return;
        }
        for (CommitTree.Commit commit : tree) {
            System.out.println("===");
            System.out.println(commit);
            System.out.println();
        }
    }

	/**
	 * Finds the commit(s) with the given message.
	 * @param message The message associated with the desired commit.
	 */
	private static void find(String message) {
		CommitTree tree;
		try {
			tree = loadCommitTree();
		} catch (IOException e) {
			System.err.println("Could not load Commit Tree: " + e);
			return;
		}
		StringBuilder builder = new StringBuilder();
		for (CommitTree.Commit commit : tree) {
			String comMsg = commit.getMessage();
			if (comMsg.equals(message)) {
				builder.append(commit.getId());
				builder.append('\n');
			}
		}
		String commitIdString = builder.toString();
		if (commitIdString.equals("")) {
			System.out.println("Found no commit with that message.");
		} else {
			System.out.println(commitIdString);
		}
	}
	
	private static void status() {
		CommitTree tree;
		Set<Path> stagedFiles;
		Set<Path> removedFiles;
		Optional<String> currentBranch;
		try {
			tree = loadCommitTree();
			stagedFiles = loadPathSet(CHANGED_FILES_PATH);
			removedFiles = loadPathSet(REMOVED_FILES_PATH);
			currentBranch = getCurrentBranch();
		} catch (IOException e) {
			System.err.println("Could not load required files: " + e);
			return;
		}
		System.out.println("=== Branches ===");
		for (String branch : tree.branchNames()){
			if (currentBranch.isPresent() && currentBranch.get().equals(branch)){
				System.out.print("*");
			}
			System.out.println(branch);
		}
		System.out.println();
		System.out.println("=== Staged Files ===");
		for (Path file : stagedFiles){
			System.out.println(file);
		}
		System.out.println();
		System.out.println("=== Files Marked for Untracking ===");
		for (Path removed : removedFiles){
			System.out.println(removed);
		}
		System.out.println();
	}

    /**
     * Loads a set of paths from a file. Helper method.
     * Useful for places like commit.
     * @param saveLocation the path of the file where the paths were saved
     * @return the set of paths saved in the file
     * @throws IOException
     */
    private static Set<Path> loadPathSet(Path saveLocation) throws IOException {
        return Files.lines(saveLocation).map(Paths::get).collect(Collectors.toSet());
    }

    /**
     * Saves the specified set of paths to a file. Helper method.
     * Useful for places like add and remove.
     * @param paths the set of paths
     * @param saveLocation the path of the file to save the set of paths to
     * @throws IOException
     */
    private static void savePathSet(Set<Path> paths, Path saveLocation) throws IOException {
        try(FileWriter writer = new FileWriter(saveLocation.toFile(), false)) {
            for (Path path : paths) {
                writer.append(path.toString());
                writer.append("\n");
            }
        }
    }

    /**
     * Gets the name of the current branch if gitlet is currently at a branch.
     * @return The name of the current branch if there is one, otherwise empty.
     * @throws IOException
     */
    private static Optional<String> getCurrentBranch() throws IOException {
        List<String> treeLocationLines = Files.lines(TREE_LOCATION_PATH).collect(Collectors.toList());
        if (treeLocationLines.size() != 2)
            throw new IllegalStateException("Bad location file");
        if (treeLocationLines.get(0).equals("branch"))
            return Optional.of(treeLocationLines.get(1));
        return Optional.empty();
    }

    /**
     * Gets the current commit ID if gitlet is currently not at a branch.
     * @return The current commit ID if there is no branch, otherwise empty
     * @throws IOException
     */
    private static Optional<Integer> getCurrentCommitId() throws IOException {
        List<String> treeLocationLines = Files.lines(TREE_LOCATION_PATH).collect(Collectors.toList());
        if (treeLocationLines.size() != 2)
            throw new IllegalStateException("Bad location file");
        if (treeLocationLines.get(0).equals("id"))
            return Optional.of(Integer.parseInt(treeLocationLines.get(1)));
        return Optional.empty();
    }

    /**
     * Stores the given string as the current branch name. This method assumes that the branch exists.
     * @param branchName the branch to save as the current location
     * @throws IOException
     */
    private static void saveCurrentBranch(String branchName) throws IOException {
        try(PrintWriter writer = new PrintWriter(TREE_LOCATION_PATH.toFile())) {
            writer.println("branch");
            writer.println(branchName);
        }
    }

    /**
     * Stores the given number as the current branch ID. This method assumes that a commit with that ID exists.
     * @param commitId the commit ID to save as the current location
     * @throws IOException
     */
    private static void saveCurrentCommitId(int commitId) throws IOException {
        try(PrintWriter writer = new PrintWriter(TREE_LOCATION_PATH.toFile())) {
            writer.println("id");
            writer.println(commitId);
        }
    }

    /**
     * Gets the current commit from the commit tree
     * @param tree the commit tree
     * @return the current commit, as saved in the file at TREE_LOCATION_PATH.
     * @throws IOException
     */
    private static CommitTree.Commit getCurrentCommit(CommitTree tree) throws IOException {
        List<String> treeLocationLines = Files.lines(TREE_LOCATION_PATH).collect(Collectors.toList());
        if (treeLocationLines.size() != 2)
            throw new IllegalStateException("Bad location file");
        if (treeLocationLines.get(0).equals("branch"))
            return tree.commitAtBranch(treeLocationLines.get(1));
        if (treeLocationLines.get(0).equals("id"))
            return tree.getCommitById(Integer.parseInt(treeLocationLines.get(1)));
        throw new IllegalStateException("Illegal first line of tree location file" + treeLocationLines.get(0));
    }

    /**
     * Helper method.
     * If a method fails to completely create a directory for whatever reason, this method attempts to clean up
     * by removing it completely.
     * @param path the directory to delete
     * @throws AssertionError if the given path was outside of .gitlet - We should not delete anything outside of there.
     */
    private static void recursiveDelete(Path path) {
        if (!path.startsWith(GITLET_PATH))
            throw new AssertionError("Gitlet should not delete anything outside of .gitlet");
        if (Files.exists(path)) {
            try {
                Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        Files.delete(dir);
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                System.err.println("Couldn't delete " + path + " completely. Do it yourself.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Copies the given (possibly nested) file from sourceLoc to destLoc
     * @param sourceLoc the location to copy toCopy from
     * @param destLoc the location to copy toCopy to
     * @param toCopy the (possibly nested) file to copy
     * @throws IOException
     */
    private static void copyNested(Path sourceLoc, Path destLoc, Path toCopy) throws IOException{
        Files.createDirectories(destLoc.resolve(toCopy).getParent());
        Files.copy(sourceLoc.resolve(toCopy), destLoc.resolve(toCopy), StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * Helper method to the path of the directory in which the files modified in the given commit are stored
     * @param c the commit to get the changed file direc
     * @return the path to the files from the given commit
     */
    private static Path getCommitPath(CommitTree.Commit c) {
        return COMMITS_PATH.resolve(Integer.toString(c.getId()));
    }
}
