import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Gitlet's tree of commits (commitlets?)
 * Gitlet's tree tracks revisions of files as they are added in commits.
 * Files must be relative paths, all relative to the same root.
 */
public class CommitTree implements Serializable, Iterable<CommitTree.Commit> {
    /** The next unique ID to give out to a Commit. This should not be accessed directly. */
    private final AtomicInteger nextId;

    /** The heads of each named branch in this tree. */
    private final Map<String, Commit> branches;

    /** The initial Commit in the tree */
    private final Commit initialCommit;

    /** A list of all commits. This allows O(1) access to commits using an ID number. */
    private final List<Commit> commitsById;

    /**
     * Creates a new CommitTree that contains an initial commit.
     */
    public CommitTree() {
        nextId = new AtomicInteger(0);
        commitsById = new ArrayList<>();
        initialCommit = new Commit();
        commitsById.add(initialCommit);
        branches = new HashMap<>();
        branches.put("master", initialCommit);
    }

    /**
     * Reconstructs a CommitTree from a serialized proxy instance.
     * @param proxy the proxy to reassemble from
     * @throws InvalidObjectException if the constructor detects that invariants have been broken
     */
    private CommitTree(SerializationProxy proxy) throws InvalidObjectException {
        branches = new HashMap<>();
        commitsById = new ArrayList<>(proxy.nextId);
        for (int i = 0; i < proxy.nextId; i++)
            commitsById.add(null);
        initialCommit = new Commit(proxy.initialCommit, null, proxy.branches, new HashSet<>());
        if (!proxy.branches.isEmpty())
            throw new InvalidObjectException("Not all serialized branches were restored. " +
                    "Serialized tree had extra branches or was missing commits.");
        nextId = new AtomicInteger(proxy.nextId);
    }

    /**
     * Generates a unique ID for a new Commit
     * @implNote with the current implementation, commit IDs are sequential.
     * @return an ID for a commit
     */
    private int generateId() {
        return nextId.getAndIncrement();
    }

    /**
     * Gets the number of commits in this tree
     * @return the size of this tree
     */
    public int size() {
        return nextId.get();
    }

    /**
     * Adds a branch at the given commit in the tree
     * @param name the name of the new branch
     * @param commit the commit to branch from
     * @throws IllegalArgumentException if the commit does not belong to this tree or the branch already exists
     */
    public void addBranch(String name, Commit commit) {
        if (commit.tree != this)
            throw new IllegalArgumentException("commit does not belong to this tree");
        if (branches.containsKey(name))
            throw new IllegalArgumentException("A branch with the name" + name + " already exists");
        branches.put(name, commit);
    }

    /**
     * Moves an existing branch in the tree
     * @param name the name of the branch to move
     * @param commit the commit to move the branch to
     * @throws IllegalArgumentException if the commit does not belong to this tree or the branch doesn't exist
     */
    public void moveBranch(String name, Commit commit) {
        if (commit.tree != this)
            throw new IllegalArgumentException("commit does not belong to this tree");
        if (!branches.containsKey(name))
            throw new IllegalArgumentException("No branch with the name" + name + " exists");
        branches.put(name, commit);
    }

    /**
     * Removes the branch with the given name from this commit tree.
     * @param name the name of the branch to remove.
     */
    public void removeBranch(String name) {
        if (!branches.containsKey(name))
            throw new IllegalArgumentException("No branch with the name " + name + " exists");
        branches.remove(name);
    }

    /**
     * Gets the commit at the end of the given branch, or null if no such branch exists
     * @param branchName the name of the branch to get
     * @return the commit at the end of the branch with the given name
     */
    public Commit commitAtBranch(String branchName) {
        return branches.get(branchName);
    }

    /**
     * Gets the set of all branch names in this tree
     * @return the names of this tree's branches
     */
    public Set<String> branchNames() {
        return branches.keySet();
    }

    /**
     * Gets the commit in this CommitTree with the specified ID, or null if there is no such commit.
     * @param id the id of the commit to get
     * @return the commit from this tree with the specified ID
     */
    public Commit getCommitById(int id) {
        return commitsById.get(id);
    }

    /**
     * Creates a new commit in the tree.
     * Files should be paths relative to the same root for one tree.
     * @param branchName the name of the branch to put the
     * @param commitMsg the message for the commit to add
     * @param changedFiles the files changed in the commit to add
     * @param removedFiles the files removed in the commit to add
     * @return the newly created commit
     */
    public Commit addCommit(String branchName, String commitMsg, Set<Path> changedFiles, Set<Path> removedFiles) {
        if (branchName == null)
            throw new NullPointerException("branchName is null");
        if (!branches.containsKey(branchName))
            throw new IllegalArgumentException("No such branch: " + branchName);

        Commit oldBranchHead = branches.get(branchName);
        Commit newCommit = new Commit(commitMsg, oldBranchHead, changedFiles, removedFiles);
        branches.put(branchName, newCommit);
        return newCommit;
    }

    @Override
    public Iterator<Commit> iterator() {
        return commitsById.iterator();
    }

    /**
     * A single commit in the Gitlet commit tree.
     */
    public class Commit implements Serializable {
        /** The tree that this commit belongs to; its enclosing instance */
        private final CommitTree tree = CommitTree.this;

        /** The unique ID of the commit */
        private final int id;

        /** The date and time that this commit was made */
        private final Instant creationTime;

        /** The description of this commit */
        private final String message;

        /** The parent of this commit in the commit tree: the commit immediately before this one. */
        private final Commit parent;

        /** The children of this commit in the commit tree: all commits that immediately follow from this commit */
        private final List<Commit> children;

        /**
         * A map from all of the files included in this Commit, to the last Commit they were changed in,
         * up to and including this one.
         */
        private final Map<Path, Commit> lastFileRevisions;

        /*
        The following 2 fields, changedFiles and removedFiles, represent the changes made in this node specifically.
        They may or may not be useful. If not, we can remove them
        */

        /** The set of files that were changed in this commit */
        private final Set<Path> changedFiles;

        /** The set of files that were removed in this commit */
        private final Set<Path> removedFiles;

        /**
         * Gets the unique ID of this commit.
         * @return this commit's ID
         */
        public int getId() {
            return id;
        }

        /**
         * Gets the time and date that this commit was made on
         * @return the time that this commit was made
         */
        public Instant getCreationTime() {
            return creationTime;
        }

        /**
         * Gets the message explaining the changes made in this commit
         * @return this commit's message
         */
        public String getMessage() {
            return message;
        }

        /**
         * Gets a this commit's children as an unmodifiable list
         * @return this commit's children
         */
        public List<Commit> getChildren() {
            return Collections.unmodifiableList(children);
        }

        /**
         * Gets the map from all files in this commit to the commit that they were most recently updated in,
         * as of this commit.
         * This method returns an unmodifiable map.
         * @return the map from files to their most recent
         */
        public Map<Path, Commit> getLastFileRevisions() {
            return Collections.unmodifiableMap(lastFileRevisions);
        }

        /**
         * Convenience method. Gets the latest commit that changed the given file, up to and including this one.
         * Equivalent to .getLastFileRevisions().get(file)
         * @param file the file to find the commit containing the most recent change of
         * @return the most recent commit that changed file
         */
        public Commit lastRevisionOf(Path file) {
            if (!lastFileRevisions.containsKey(file))
                throw new IllegalArgumentException("This commit does not contain " + file);
            return lastFileRevisions.get(file);
        }

        /**
         * Gets the set of files present in this commit
         * @return the set of files in this commit
         */
        public Set<Path> getFiles() {
            return lastFileRevisions.keySet();
        }

        /**
         * Convenience method. Checks whether this commit contains the given file
         * Equivalent to .getFiles().contains(file)
         * @param file the file to check
         * @return whether this commit contains file
         */
        public boolean hasFile(Path file) {
            return lastFileRevisions.containsKey(file);
        }

        /**
         * Gets the parent of this commit
         * @return ths commit's parent
         */
        public Commit getParent() {
            return parent;
        }

        /**
         * Gets the set of files that were changed in this commit as an unmodifiable set
         * @return the set of files changed in this commit
         */
        public Set<Path> getChangedFiles() {
            return Collections.unmodifiableSet(changedFiles);
        }

        /**
         * Gets the set of files that were removed in this commit as an unmodifiable set
         * @return the set of files removed in this commit
         */
        public Set<Path> getRemovedFiles() {
            return Collections.unmodifiableSet(removedFiles);
        }

        /** Gets the tree that this commit belongs to */
        public CommitTree getTree() {
            return tree;
        }

        /**
         * Creates a new new Commit with the given parent, file changes between it and its parent,
         * and message explaining those changes.
         * Files should be paths relative to the same root for all commits.
         * @param message The message that explains the changes made in the new Commit. Cannot be empty or null.
         * @param parent The parent of the new Commit. Cannot be null.
         * @param changedFiles The files that were changed in this commit
         * @param removedFiles The files that were removed (untracked) in this commit
         * @throws NullPointerException if parent or message is null
         * @throws IllegalArgumentException if message is empty
         */
        private Commit(String message, Commit parent, Set<Path> changedFiles, Set<Path> removedFiles) {
            if (parent == null)
                throw new NullPointerException("Parent is null");
            if (message == null)
                throw new NullPointerException("Message is null");
            if (changedFiles == null)
                throw new NullPointerException("changedFiles is null");
            if (removedFiles == null)
                throw new NullPointerException("removedFiles is null");
            if (message.isEmpty())
                throw new IllegalArgumentException("Message cannot be empty");
            if (changedFiles.isEmpty() && removedFiles.isEmpty())
                throw new IllegalArgumentException("No changes to commit");

            this.changedFiles = changedFiles;
            this.removedFiles = removedFiles;
            creationTime = Instant.now();
            id = generateId();
            this.message = message;
            this.parent = parent;
            children = new ArrayList<>();
            lastFileRevisions = new HashMap<>(parent.lastFileRevisions);
            parent.children.add(this); // TODO this is the only operation in here that isn't thread-safe, if we care.
            for (Path file : changedFiles)
                lastFileRevisions.put(file, this);
            for (Path file : removedFiles)
                lastFileRevisions.remove(file);
            commitsById.add(this);
        }

        /**
         * Creates the initial commit, with a null parent.
         */
        private Commit() {
            changedFiles = removedFiles = Collections.emptySet();
            creationTime = Instant.now();
            message = "Initial commit.";
            id = generateId();
            lastFileRevisions = new HashMap<>();
            children = new ArrayList<>();
            parent = null;
            commitsById.add(this);
        }

        /**
         * Finds the common ancestor of this commit and another node.
         * @param other another node from the same tree. Probably a sibling or cousin of this node.
         * @return the common ancestor of this node and other.
         */
        Commit findCommonAncestor(Commit other) {
            if (other.tree != tree)
                throw new IllegalArgumentException("Commits are not from the same tree");
            return findCommonAncestor(other, null);
        }

        /**
         * Finds the common ancestor of this commit and another node by checking whether this commit is an ancestor
         * of that other node, and if not, recursively searching down the tree.
         * @param other another node from the same tree. Probably a sibling or cousin of this node.
         * @param lastChild the child of this commit that called this method. Used to avoid checking the same commit
         *                  more than once.
         * @return the common ancestor of this node and other.
         */
        private Commit findCommonAncestor(Commit other, Commit lastChild) {
            // In case other is a parent of this; you will find it here (eventually) after some recursion
            if (this == other)
                return this;

            // Search all children except the one we came from for other --
            // if they have it as a descendant, this is the common ancestor
            for (Commit child : children)
                if (child != lastChild && child.hasDescendant(other))
                    return this;

            // If it wasn't this one, recurse backwards through the tree, searching more of it.
            // This will hit every node eventually.
            if (parent != null)
                return parent.findCommonAncestor(other, this);
            throw new AssertionError("Could not find common ancestor. This should never happen in a consistent tree.");
        }

        /**
         * Checks whether the given commit is a descendant of this node.
         * @param descendant the node to check
         * @return whether the given node is a descendant of this node
         */
        boolean hasDescendant(Commit descendant) {
            return this == descendant || children.stream().anyMatch(child -> child.hasDescendant(descendant));
        }

        /**
         * Reconstruct a Commit from a serialized proxy object
         * @param proxy the proxy to reconstruct from
         * @param parent the (newly deserialized) parent of this commit
         * @param proxyBranches the branches from the CommitTreeProxy, to use to fill in the real tree's branches
         * @param usedIds the set of IDs that have been used in this tree so far. Used to check consistency.
         * @throws InvalidObjectException if the constructor detects that invariants have been broken
         */
        private Commit(CommitSerializationProxy proxy, Commit parent, Map<Integer, List<String>> proxyBranches,
                       Set<Integer> usedIds) throws InvalidObjectException {
            id = proxy.id;
            creationTime = proxy.creationTime;
            message = proxy.message;
            this.parent = parent;
            children = new ArrayList<>(proxy.children.size() + 1); // room for one more in case of commit

            lastFileRevisions = parent == null ? new HashMap<>() : new HashMap<>(parent.lastFileRevisions);

            changedFiles = proxy.changedFiles.stream().map(Paths::get).collect(Collectors.toSet());
            removedFiles = proxy.removedFiles.stream().map(Paths::get).collect(Collectors.toSet());
            for (Path changedFile : changedFiles)
                lastFileRevisions.put(changedFile, this);
            for (Path removedFile : removedFiles)
                lastFileRevisions.remove(removedFile);

            if (proxyBranches.containsKey(id)) {
                for (String branchName : proxyBranches.remove(id)) {
                    branches.put(branchName, this);
                }
            }

            // Check invariants
            if (usedIds.contains(id))
                throw new InvalidObjectException("Serialized tree contained duplicate ID: " + proxy.id);

            if (parent == null) {
                if (!changedFiles.isEmpty() || !removedFiles.isEmpty())
                    throw new InvalidObjectException("Initial commit was nonempty: changedFiles = " + changedFiles +
                            ", removedFiles = " + removedFiles);
            } else {
                if (parent.creationTime.isAfter(proxy.creationTime))
                    throw new InvalidObjectException("Serialized commit has creation time before its parent's");
            }

            commitsById.set(id, this);
            for (CommitSerializationProxy childProxy : proxy.children)
                children.add(new Commit(childProxy, this, proxyBranches, usedIds));
        }

        @Override
        public String toString() {
            /* Format:
             *
             * Commit 0
             * 2015-07-17 08:15:38 PM PDT
             * Initial commit.
             */
            DateTimeFormatter dtf =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss zzz").withZone(ZoneId.systemDefault());
            return "Commit " + id + "\n" + dtf.format(creationTime) + "\n" + message;
        }

        // Crazy serialization stuff below ------------------------------

        /**
         * This method changes the behavior of serialization for this class.
         * In the case that someone somehow tries to deserialize this class directly,
         * inform them that they cannot, since this class is serialized by proxy.
         * @param stream unused
         * @throws InvalidObjectException always
         */
        private void readObject(ObjectInputStream stream) throws InvalidObjectException {
            throw new InvalidObjectException("Proxy required");
        }

        /**
         * This method changes the behavior of serialization for this class. Instead of serializing this class,
         * a CommitSerializationProxy instance will be created and serialized in its place.
         * This avoids serializing a non-static inner class, and several non-serializable fields.
         * @return the CommitSerializationProxy to serialize in place of this instance.
         */
        private Object writeReplace() {
            return new CommitSerializationProxy(this);
        }
    }

    /**
     * This method changes the behavior of serialization for this class.
     * In the case that someone somehow tries to deserialize this class directly,
     * inform them that they cannot, since this class is serialized by proxy.
     * @param stream unused
     * @throws InvalidObjectException always
     */
    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException("");
    }

    /**
     * This method changes the behavior of serialization for this class. Instead of serializing this class,
     * a SerializationProxy instance will be created and serialized in its place.
     * @return the SerializationProxy to serialize in place of this instance.
     */
    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    /**
     * Proxy class for serialization of CommitTree. CommitTree contains Commits, which are not serializable (see below),
     * so it cannot be serialized. This class get serialized in its place, and is used to reconstruct CommitTrees upon
     * deserialization.
     */
    private static class SerializationProxy implements Serializable {
        // Change this if you change the way this class, Commit, or CommitTree works.
        private static final long serialVersionUID = 2L;
        private final int nextId;
        // Maps from ID to branch (yes, backwards) for ease of reconstruction
        private final Map<Integer, List<String>> branches;
        private final CommitSerializationProxy initialCommit;

        /**
         * Creates a new proxy object to serialize in place of the given CommitTree object.
         * @param target the CommitTree to create a serialization proxy for.
         */
        private SerializationProxy(CommitTree target) {
            nextId = target.nextId.get();
            branches = new HashMap<>();
            for (Map.Entry<String, Commit> entry : target.branches.entrySet()) {
                int id = entry.getValue().getId();
                if (branches.containsKey(id)) {
                    branches.get(id).add(entry.getKey());
                } else {
                    List<String> associatedBranches = new LinkedList<>();
                    associatedBranches.add(entry.getKey());
                    branches.put(id, associatedBranches);
                }
            }
            initialCommit = new CommitSerializationProxy(target.initialCommit);
        }

        /**
         * This method changes the behavior of serialization for this class.
         * Instead of returning a deserialized instance of this class upon deserialization,
         * a CommitTree will be reconstructed from it and returned.
         *
         * @return a reconstructed CommitTree, based on this proxy.
         * @throws InvalidObjectException if the proxy object represented an invalid CommitTree
         */
        private Object readResolve() throws InvalidObjectException {
            return new CommitTree(this);
        }
    }

    /**
     * Proxy class for serialization of Commit. Commit is a non-static inner class and contains Path objects,
     * so it should not and cannot be serialized. This class gets serialized in its place, and is used to reconstruct
     * Commits upon deserialization.
     */
    private static class CommitSerializationProxy implements Serializable {
        // Change this if you change the way this class or Commit works.
        private static final long serialVersionUID = 1L;
        private final int id;
        private final Instant creationTime;
        private final String message;
        private final List<CommitSerializationProxy> children;
        // These two fields store strings instead of paths since paths are not serializable
        private final Set<String> changedFiles;
        private final Set<String> removedFiles;

        /**
         * Creates a new proxy object to serialize in place of the given Commit object.
         * Since Commits include their own children, this method creates proxies for an entire (sub)tree of commits.
         * @param target the Commit object to create a serialization proxy for
         */
        private CommitSerializationProxy(Commit target) {
            id = target.id;
            creationTime = target.creationTime;
            message = target.message;
            children = target.children.stream().map(CommitSerializationProxy::new).collect(Collectors.toList());
            changedFiles = target.changedFiles.stream().map(Path::toString).collect(Collectors.toSet());
            removedFiles = target.removedFiles.stream().map(Path::toString).collect(Collectors.toSet());
        }

        // no readResolve here; These should be dealt with by CommitTree's proxy constructor.
    }
}
