package ngordnet;

import edu.princeton.cs.algs4.Digraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * An object that stores the WordNet graph in a manner useful for extracting all hyponyms of a word.
 */
public class WordNet {
    private Digraph digraph;
    private ArrayList<Synset> synsets = new ArrayList<>();

    /** Creates a WordNet using files form SYNSETFILENAME and HYPONYMFILENAME */
    public WordNet(String synsetFilename, String hyponymFilename) {

        //Read and build synsets
        File synsetFile = new File(synsetFilename);
        try {
            Scanner synsetScanner = new Scanner(synsetFile);
            while (synsetScanner.hasNext()) {
                String line = synsetScanner.nextLine();

                String[] synset = line.split(",");

                int synsetID = Integer.parseInt(synset[0]);
                String[] words = synset[1].split(" ");
                String definition = synset[2];

                synsets.add(synsetID, new Synset(synsetID, words, definition));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Read and build hyponyms
        digraph = new Digraph(synsets.size());
        File hyponymFile = new File(hyponymFilename);
        try {
            Scanner hyponymScanner = new Scanner(hyponymFile);
            while (hyponymScanner.hasNext()) {
                String line = hyponymScanner.nextLine();
                String[] integers = line.split(",");
                int vertex = Integer.parseInt(integers[0]);
                for (int i = 1; i < integers.length; i++) {
                    digraph.addEdge(vertex, Integer.parseInt(integers[i]));
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /* Returns true if NOUN is a word in some synset. */
    public boolean isNoun(String noun) {
        for (Synset synset : synsets) {
            for (String word : synset.getWords()) {
                if (word.equals(noun)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* Returns the set of all nouns. */
    public Set<String> nouns() {
        TreeSet<String> set = new TreeSet<>();

        for (Synset synset : synsets) {
            for (String word : synset.getWords()) {
                set.add(word);
            }
        }

        return set;
    }

    /** Returns the set of all hyponyms of WORD as well as all synonyms of
     * WORD. If WORD belongs to multiple synsets, return all hyponyms of
     * all of these synsets. See http://goo.gl/EGLoys for an example.
     * Do not include hyponyms of synonyms.
     */
    public Set<String> hyponyms(String searchWord) {
        TreeSet<String> hyponymSet = new TreeSet<>();
        Set<Integer> synonymIDSet = new TreeSet<Integer>();

        for (Synset synset : synsets) {
            for (String word : synset.getWords()) {
                if (word.equals(searchWord)) {
                    synonymIDSet.add(synset.getId());
                }
            }
        }

        Set<Integer> descendants = GraphHelper.descendants(digraph, synonymIDSet);
        for (Integer id : descendants) {
            for (String word : synsets.get(id).getWords()) {
                hyponymSet.add(word);
            }
        }

        return hyponymSet;
    }

}
