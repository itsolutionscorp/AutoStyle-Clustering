package ngordnet;
import java.util.Set;
import java.util.TreeSet;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
    /** Store the Synset objects in an array */
    private Synset[] entries;
    /** Store the hyponym relations by ID number in a Digraph */
    private Digraph directory;

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {
        /** Creates new In objects to read in the lines from file */
        In synsetIn = new In(synsetFilename);
        In hyponymIn = new In(hyponymFilename);
        /** Count the words by the number of lines in the synset file
         *  and create a synset array with size of the word count */
        int wordCount = (new In(synsetFilename)).readAllLines().length;
        entries = new Synset[wordCount];
        directory = new Digraph(wordCount);
        /** Enter synset information into the entries array. The index
         *  of each synset will serve to keep track of the ID, since each
         *  line is ordered. */
        for (int i = 0; i < wordCount; i += 1) {
            String[] currentLine = synsetIn.readLine().split(",");
            entries[i] = new Synset(currentLine[1], currentLine[2]);
        }
        /** Enter the hyponym relations into the directory Digraph */
        while (hyponymIn.hasNextLine()) {
            String[] strIntList = hyponymIn.readLine().split(",");
            int headVertex = Integer.parseInt(strIntList[0]);
            for (int k = 1; k < strIntList.length; k += 1) {
                int tailVertex = Integer.parseInt(strIntList[k]);
                directory.addEdge(headVertex, tailVertex);
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (int i = 0; i < entries.length; i += 1) {
            if (entries[i].wordSet.contains(noun)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        Set<String> toReturnSet = new TreeSet<String>();
        for (int i = 0; i < entries.length; i += 1) {
            for (String word : entries[i].wordSet) {
                toReturnSet.add(word);
            }
        }
        return toReturnSet;
    }

    /** Returns the set of all hyponyms of WORD including WORD itself.
     *  Assumes that the WordNet contains the word. */
    public Set<String> hyponyms(String word) {
        Set<Integer> indexSet = GraphHelper.descendants(directory, getIds(word));
        Set<String> hyponymSet = new TreeSet<String>();
        hyponymSet.add(word);
        for (int id : indexSet) {
            for (String hyponym : entries[id].wordSet) {
                hyponymSet.add(hyponym);
            }
        }
        return hyponymSet;
    }

    /** Returns a set of ids of synsets that contain a certain word. */
    private Set<Integer> getIds(String word) {
        Set<Integer> idSet = new TreeSet<Integer>();
        for (int i = 0; i < entries.length; i += 1) {
            if (entries[i].wordSet.contains(word)) {
                idSet.add(i);
            }
        }
        return idSet;
    }
    
    /** An Abstract Data Type consisting of a set of strings with each element
     *  representing a word in the synset. The definition is also stored as a
     *  string. Note that the id is found through the array index the synset is
     *  stored in.
     */
    private class Synset {
        Set<String> wordSet;
        String definition;

        public Synset(String w, String def) {
            String[] words = w.split(" ");
            wordSet = new TreeSet<String>();
            for (int i = 0; i < words.length; i += 1) {
                wordSet.add(words[i]);
            }
            definition = def;
        }
    }
}
