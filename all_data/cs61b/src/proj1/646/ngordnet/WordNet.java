package ngordnet;
import java.util.Set;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.TreeMap;
import edu.princeton.cs.algs4.Digraph;

/* Stores the WordNet graph for efficient hyponym analysis. 
 * @author Greg Salazar
 */
public class WordNet {
    // Maps each noun to its set of synsets.
    private TreeMap<String, TreeSet<Integer>> nouns;
    // List of sets of strings corresponding to synsets.
    private ArrayList<TreeSet<String>> synsets;
    private Digraph hyponyms;

    // Scanners used in constructor must be defined outside.
    private Scanner synSc;
    private Scanner hypSc;

    /** Creates a WordNet using files from SYNSETFILENAME and 
    HYPONYMFILENAME. */
    public WordNet(String synsetFilename, String hyponymFilename) {
        try {
            synSc = new Scanner(new File(synsetFilename));
        } catch (IOException e) {
            System.out.println("Could not open file" + synsetFilename);
            return;
        }
        try {
            hypSc = new Scanner(new File(hyponymFilename));
        } catch (IOException e) {
            System.out.println("Could not open file" + hyponymFilename);
            return;
        }
        nouns = new TreeMap<String, TreeSet<Integer>>();
        synsets = new ArrayList<TreeSet<String>>();
        String line;
        String[] split;
        String[] nounList;
        TreeSet<Integer> treeSetRef;
        TreeSet<String> synsetRef;
        int synset = 0;
        int i;
        while (synSc.hasNextLine()) {
            /* Read line into LINE. */
            line = synSc.nextLine();
            split = line.split(",");
            /* Synset ID (first string in array) actually doesn't 
             * need to be parsed.
             * It's easier to just keep track of with an incremented
             * integer variable (namely SYNSET). */
            nounList = split[1].split(" ");
            /* NOUNLIST is now an array of nouns. */

            /* Create new set of strings and add it to the list. 
             * The loop fills this set with the nouns of the current
             * synset. */
            synsetRef = new TreeSet<String>();
            synsets.add(synsetRef);
            for (i = 0; i < nounList.length; i += 1) {
                if (nouns.containsKey(nounList[i])) {
                    /* If noun already in NOUNS, access existing
                     * set of synsets. */
                    treeSetRef = nouns.get(nounList[i]);
                } else {
                    /* If first sighting of noun, create a new map 
                     * entry with value set to empty set. */
                    treeSetRef = new TreeSet<Integer>();
                    nouns.put(nounList[i], treeSetRef);
                }
                /* Add synset ID to set. */
                treeSetRef.add(synset);

                /* Add string to current synset. Unfortunately,
                 * I couldn't figure out how to do this without
                 * creating multiple instances of strings that
                 * are in multiple synsets. Oh well. */
                synsetRef.add(nounList[i]);
            }
            synset += 1;
        }

        /* SYNSET is now equal to total number of synsets. */
        hyponyms = new Digraph(synset);
        while (hypSc.hasNextLine()) {
            /* Read line into LINE. */
            line = hypSc.nextLine();
            split = line.split(",");
            synset = Integer.parseInt(split[0]);
            for (i = 1; i < split.length; i += 1) {
                hyponyms.addEdge(synset, Integer.parseInt(split[i]));
            }
        }
    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        return nouns.containsKey(noun);
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        return nouns.keySet();
    }

    /* Returns the set of all hyponyms and synonyms of WORD. */
    public Set<String> hyponyms(String word) {
        Set<String> hyps = new TreeSet<String>();
        if (!isNoun(word)) {
            System.out.printf("Warning: %s is not a noun in WordNet. Set of one returned.\n", word);
            hyps.add(word);
            return hyps;
        }
        Set<Integer> descendants = GraphHelper.descendants(hyponyms, nouns.get(word));
        for (int descendant : descendants) {
            hyps.addAll(synsets.get(descendant));
        }
        return hyps;
    }
}
